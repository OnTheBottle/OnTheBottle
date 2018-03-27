package com.bottle.userWall.home.controller;

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

 //       @Autowired
 //       private Environment env;
//
 //       @RequestMapping("/")
 //       public String index() {
 //           return "index.html";
 //       }
//
 //       /**
 //        * POST /uploadFile -> receive and locally save a file.
 //        *
 //        * @param file The uploaded file as Multipart file parameter in the
 //        * HTTP request. The RequestParam name must be the same of the attribute
 //        * "name" in the input tag with type file.
 //        *
 //        * @return An http OK status in case of success, an http 4xx status in case
 //        * of errors.
 //        */
 //       @RequestMapping(value = "/upload", method = RequestMethod.POST)
 //       @ResponseBody
 //       public ResponseEntity<?> uploadFile(
 //               @RequestParam("file") MultipartFile file) {
//
//
//
//
 //           try {
 //               // Get the filename and build the local file path
 //               String filename = file.getOriginalFilename();
 //               String directory = env.getProperty("spring.http.multipart.location");
 //               String filepath = Paths.get(directory, filename).toString();
 //               String name = null;
////
////        if (!file.isEmpty()) {
////            try {
////                byte[] bytes = file.getBytes();
////                name = file.getOriginalFilename();
////                String rootpath = System.getProperty( "catalina.home" );
////                File dir = new File( rootpath + File.separator + "tmpFiles" );
////
////                if (!dir.exists()) {
////                    dir.mkdirs();
////                }
////
////                File uploadedFile =new File( dir.getAbsolutePath() + File.separator + name );
//
//
 //               // Save the file locally
 //               BufferedOutputStream stream =
 //                       new BufferedOutputStream(new FileOutputStream(new File(filepath)));
 //               stream.write(file.getBytes());
 //               stream.flush();
 //               stream.close();
 //           }
 //           catch (Exception e) {
 //               System.out.println(e.getMessage());
 //               return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
 //           }
//
 //           return new ResponseEntity<>(HttpStatus.OK);
 //       } // method uploadFile
//
 //   } // class MainController
//
//
//

