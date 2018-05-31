package com.bottle.service.post;

import com.bottle.model.entity.UploadFile;
import com.bottle.model.repository.UploadFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
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

    @Autowired
    public UploadFileService(UploadFileRepository uploadFileRepository) {
        this.uploadFileRepository = uploadFileRepository;
    }

    public UploadFile getFile(MultipartFile file) throws IOException {
        saveFileToFolder( file );
        UploadFile fileInfo=getUploadedFileInfo( file );
        return saveFileToDatabase( fileInfo );
    }

    public List<UploadFile> getListFiles(MultiValueMap<String, MultipartFile> multiValueMap) throws IOException {
        List<UploadFile> uploadFiles = new ArrayList<>();
        for (List<MultipartFile> files : multiValueMap.values()) {
            for (MultipartFile multipartFile : files) {
                saveFileToFolder( multipartFile );
                UploadFile fileInfo = getUploadedFileInfo( multipartFile );
                uploadFiles.add( saveFileToDatabase( fileInfo ) );
            }
        }
        return uploadFiles;
    }

    @Transactional
    public UploadFile getFile(UUID id) {
        return uploadFileRepository.findOne(id);
    }

    private void saveFileToFolder(MultipartFile multipartFile) throws IOException {
        String outputFileName = getFilename( multipartFile );
        FileCopyUtils.copy( multipartFile.getBytes(), new FileOutputStream( outputFileName ) );
    }

    private UploadFile saveFileToDatabase(UploadFile uploadFile) {
        uploadFileRepository.save(uploadFile);
        return uploadFile;
    }

    private String getPath(String name) {
        return "http://localhost:8083//com/bottle/wall/images/" + name;
    }

    private String getUploadedFolder() {
        return "message-subsystem-webapp/src/main/resources/static/com/bottle/wall/images/";
    }

    private UploadFile getUploadedFileInfo(MultipartFile multipartFile)
            throws IOException {
        UploadFile fileInfo = new UploadFile();
        fileInfo.setName( multipartFile.getOriginalFilename() );
        fileInfo.setSize( multipartFile.getSize() );
        fileInfo.setType( multipartFile.getContentType() );
        fileInfo.setUrl( getPath( multipartFile.getOriginalFilename() ) );
        fileInfo.setLocation( getUploadedFolder() );
        return fileInfo;
    }

    private String getFilename(MultipartFile multipartFile) {
        return getUploadedFolder() + multipartFile.getOriginalFilename();
    }
}















