package com.bottle.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
    public class DownloadController {

    private final static String UPLOADED_FOLDER = "src/main/resources/static/com/bottle/wall/images/postsFiles";

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
           @ResponseBody
           public ResponseEntity<?> uploadFile(
                   @RequestParam("file") MultipartFile file) {
        String name = null;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                name = file.getOriginalFilename();
                String rootpath = UPLOADED_FOLDER;
                File dir = new File( rootpath +File.separator);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File uploadedFile =new File( rootpath + File.separator + name );
                BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.close();
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }

}


