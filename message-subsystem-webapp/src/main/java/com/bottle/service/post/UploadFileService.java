package com.bottle.service.post;

import com.bottle.config.UploadConfig;
import com.bottle.model.entity.Post;
import com.bottle.model.entity.UploadFile;
import com.bottle.model.repository.UploadFileRepository;
import com.bottle.service.Builder;
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
    private UploadConfig uploadConfig;
    private Builder builder;

    @Autowired
    public UploadFileService(UploadFileRepository uploadFileRepository, UploadConfig uploadConfig, Builder builder) {
        this.uploadFileRepository = uploadFileRepository;
        this.uploadConfig = uploadConfig;
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
        FileCopyUtils.copy( multipartFile.getBytes(), new FileOutputStream( outputFileName ) );
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
        return uploadConfig.getFilePath() + name;
    }

    private String getUploadedFolder() {
        return uploadConfig.getUploadFolder();
    }

    private String getFilename(MultipartFile multipartFile) {
        return getUploadedFolder() + multipartFile.getOriginalFilename();
    }
}