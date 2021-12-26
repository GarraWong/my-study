package com.wong.redisdemo.controller;

import cn.hutool.core.io.FileUtil;
import com.wong.redisdemo.model.UploadFile;
import com.wong.redisdemo.service.MyService;
import com.wong.redisdemo.utils.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Queue;

/**
 * 输入类描述
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/10/11 14:37
 */
@RestController
@RequestMapping("/file")
@Validated
@CrossOrigin
public class FileUploadController {

    @Autowired
    private MyService myService;

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @PostMapping("/upload")
    public String fileUpload() throws Exception{
        HttpServletRequest request = ServletUtils.getRequest();
        MultipartHttpServletRequest multipart = new StandardMultipartHttpServletRequest(request);
        MultipartFile file = multipart.getFile("binFile");
        logger.info("file.getOriginalFilename():{}",file.getOriginalFilename());
        logger.info("file.getName():{}",file.getName());
        logger.info("file.getSize():{}",file.getSize());
        logger.info("file.getBytes():{}",file.getBytes());
        String str;
        try {
            byte[] bytes = file.getBytes();
            str = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            str = null;
        }
        return str;
    }

    @PostMapping("/upload2")
    public String fileUpload2(@RequestParam("files") List<MultipartFile> files, @Valid UploadFile model) throws Exception{
        logger.info("实体:{}", model);
        files.forEach(e->{
            logger.info("file.getOriginalFilename():{}",e.getOriginalFilename());
            logger.info("file.getName():{}",e.getName());
            logger.info("file.getSize():{}",e.getSize());
//            logger.info("file.getBytes():{}",e.getBytes());
        });

//        logger.info("file.getOriginalFilename():{}",file.getOriginalFilename());
//        logger.info("file.getName():{}",file.getName());
//        logger.info("file.getSize():{}",file.getSize());
//        logger.info("file.getBytes():{}",file.getBytes());
        String str = "";
//        try {
//            byte[] bytes = file.getBytes();
//            str = new String(bytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//            str = null;
//        }
        return str;
    }

    @GetMapping("/fileuse")
    public ResponseEntity<?> fileUse() throws Exception{
        logger.info("收到请求");
        File file = new File("C:\\Users\\WangYumou\\Desktop\\123.jpeg");
        FileInputStream is = new FileInputStream(file);
        long length = file.length();
        byte[] bytes = new byte[(int) length];
        is.read(bytes);
        is.close();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + new String(file.getAbsolutePath().getBytes(StandardCharsets.UTF_8), "ISO8859-1"));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("file-type", FileUtil.getSuffix(file));
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
    }


    @GetMapping("/say")
    public String sayHi(@RequestParam int number) {
        return "number" + "hello";
    }
}
