package com.bottle.controller;

import org.springframework.util.FileCopyUtils;
import com.bottle.model.entity.UploadFile;
import com.bottle.service.post.UploadFileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class UploadController {
    private UploadFileService uploadFileService;

    @Autowired
    public UploadController(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity uploadFile(MultipartHttpServletRequest request) {
        UploadFile uploadFile = new UploadFile();
        try {
            Iterator<String> itr = request.getFileNames();
            while (itr.hasNext()) {
                String uploadedFile = itr.next();
                MultipartFile file = request.getFile(uploadedFile);
                if (!file.isEmpty()) {
                    try {
                        uploadFile = uploadFileService.saveFile(file);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(uploadFile, HttpStatus.OK);
    }

    @RequestMapping(value = "/multiUpload", method = RequestMethod.POST)
    public ResponseEntity<List<UploadFile>> uploadedFiles(MultipartHttpServletRequest request) throws IOException {
        MultiValueMap<String, MultipartFile> filesMap = request.getMultiFileMap();
        List<UploadFile> uploadFiles = uploadFileService.saveFiles(filesMap);
        return new ResponseEntity<>(uploadFiles, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{fileId}", method = RequestMethod.GET)
    public void getFile(HttpServletResponse response, @PathVariable UUID fileId) {
        UploadFile dataFile = uploadFileService.getFile(fileId);
        File file = new File(dataFile.getLocation(), dataFile.getName());
        try {
            response.setContentType(dataFile.getType());
            response.setHeader("Content-disposition", "attachment; filename=\"" + dataFile.getName() + "\"");
            FileCopyUtils.copy(FileUtils.readFileToByteArray(file),
                    response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

