package com.bottle.service.post;

import com.bottle.model.entity.Post;
import com.bottle.model.entity.UploadFile;
import com.bottle.model.entity.User;
import com.bottle.model.repository.UploadFileRepository;
import com.bottle.model.repository.UserRepository;
import com.bottle.service.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UploadFileService {
    private UploadFileRepository uploadFileRepository;
    private Builder builder;

    @Value("${upload.filePath}")
    private String filePath;

    @Value("${upload.uploadFolder}")
    private String uploadFolder;

    @Value("${upload.usersIcoUploadFolder}")
    private String usersIcoUploadFolder;

    @Autowired
    public UploadFileService(UploadFileRepository uploadFileRepository, Builder builder) {
        this.uploadFileRepository = uploadFileRepository;
        this.builder = builder;
    }

    public List<UploadFile> saveFiles(MultiValueMap<String, MultipartFile> multiValueMap) throws IOException {
        List<UploadFile> uploadFiles = new ArrayList<>();
        for (List<MultipartFile> files : multiValueMap.values()) {
            for (MultipartFile multipartFile : files) {
                UploadFile fileInfo = saveFile( multipartFile );
                uploadFiles.add( fileInfo );
            }
        }
        return uploadFiles;
    }

    public UploadFile saveFile(MultipartFile multipartFile) throws IOException {
        saveFileToFolder( multipartFile );
        String path = getPath( multipartFile.getOriginalFilename() );
        String location = getUploadedFolder();
        UploadFile fileInfo = builder.buildFileInfo( multipartFile, path, location );
        saveUploadFileToDatabace( fileInfo );
        return fileInfo;
    }

    @Transactional
    public UploadFile getFile(UUID id) {
        return uploadFileRepository.findOne( id );
    }

    private void saveFileToFolder(MultipartFile multipartFile) throws IOException {
        String outputFileName = getFilename( multipartFile );
        BufferedOutputStream stream =
                new BufferedOutputStream( new FileOutputStream( new File( outputFileName ) ) );
        stream.write( multipartFile.getBytes() );
        stream.close();
    }

    @Transactional
    public UploadFile getFileByName(String name) throws IOException {
        return uploadFileRepository.getByName( name );
    }

    @Transactional
    public void saveUploadFileToDatabace(UploadFile fileInfo) {
        uploadFileRepository.save( fileInfo );
    }

    @Transactional
    public void linkFilesWithPost(List<UploadFile> uploadFiles, Post post) {
        for (UploadFile uploadFile : uploadFiles) {
            uploadFile.setPost( post );
            uploadFileRepository.save( uploadFile );
        }
    }

    private String getPath(String name) {
        return filePath + name;
    }

    private String getUploadedFolder() {
        return uploadFolder;
    }

    private String getFilename(MultipartFile multipartFile) {
        return getUploadedFolder() + multipartFile.getOriginalFilename();
    }

    public UploadFile writeAvatar(String data) throws IOException {
        String base64Image = data.split( "," )[1];
        String type = data.split( "," )[0].split( ";" )[0].split( ":" )[1].split( "/" )[1];
        String randomName = UUID.randomUUID().toString();
        String nameFile = randomName + "." + type;
        String location = usersIcoUploadFolder + nameFile;
        String path = filePath + nameFile;
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary( base64Image );
        BufferedOutputStream stream =
                new BufferedOutputStream( new FileOutputStream( new File( location ) ) );
        stream.write( imageBytes );
        stream.close();
        UploadFile fileInfo = builder.buildAvatarInfo( path, usersIcoUploadFolder, nameFile, type, imageBytes.length );
        saveUploadFileToDatabace( fileInfo );
        return fileInfo;
    }
}