package com.bottle.controller;

import com.bottle.service.auth.AuthService;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.bottle.model.entity.UploadFile;
import com.bottle.service.post.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;

import org.springframework.util.FileCopyUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.*;


@RestController
public class UploadController {
    private UploadFileService uploadFileService;
    private AuthService authService;

    @Autowired
    public UploadController(UploadFileService uploadFileService, AuthService authService) {
        this.uploadFileService = uploadFileService;
        this.authService = authService;
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

    @RequestMapping(value = "/uploadFiles/{fileName}.{fileType}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getFileForVision(@PathVariable("fileName") String name,
                                                   @PathVariable("fileType") String type) throws IOException {
        String fileName = name + "." + type;
        UploadFile uploadFile = uploadFileService.getFileByName( fileName );
        String location =uploadFile.getLocation()+uploadFile.getName();
        File content = new File( location );
        byte[] image = FileUtils.readFileToByteArray( content );
        if (image.length == 0) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set( "content-type", uploadFile.getType() );
        responseHeaders.setContentLength( image.length );
        responseHeaders.setContentType( MediaType.parseMediaType( uploadFile.getType() ) );
        responseHeaders.setCacheControl( "max-age=2592000" );
        responseHeaders.set( "accept-ranges", "bytes" );
        return new ResponseEntity<>( image, responseHeaders, HttpStatus.OK );
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

    @RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST)
    public ResponseEntity<?> uploadAvatar(@RequestBody String base64,
                                          @RequestParam(name = "access_token") String token) throws IOException {
        if (!authService.isValidToken( token )) {
            return ErrorResponse.getErrorResponse( "Non-valid token" );
        }
        UploadFile uploadFile=uploadFileService.writeAvatar(base64);

        return new ResponseEntity<>(uploadFile, HttpStatus.OK );
    }
}

