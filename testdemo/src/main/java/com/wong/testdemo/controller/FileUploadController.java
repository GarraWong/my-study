package com.wong.testdemo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.wong.testdemo.model.BatchUploadVo;
import com.wong.testdemo.model.UploadFile;
import com.wong.testdemo.service.MyService;
import com.wong.testdemo.utils.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

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
        File file = new File("/111.doc");
        FileInputStream is = new FileInputStream(file);
        long length = file.length();
        byte[] bytes = new byte[(int) length];
        is.read(bytes);
        is.close();
        MultiValueMap httpHeaders = new LinkedMultiValueMap();
        httpHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
        httpHeaders.add("Content-Disposition", "attachment; filename=" + new String(file.getAbsolutePath().getBytes(StandardCharsets.UTF_8), "ISO8859-1"));
        httpHeaders.add("Pragma", "no-cache");
        httpHeaders.add("Expires", "0");
        httpHeaders.add("Last-Modified", new Date().toString());
        httpHeaders.add("file-type", FileUtil.getSuffix(file));
        httpHeaders.add("ETag", String.valueOf(System.currentTimeMillis()));

        HttpHeaders headers = new HttpHeaders(httpHeaders);
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(length)
//                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .contentType(MediaType.parseMediaType("application/msword"))
                .body(new FileSystemResource(file));
    }


    @GetMapping("/say")
    public String sayHi(@RequestParam int number) {
        return "number" + "hello";
    }

    @PostMapping("/batchinsert")
    public String batchInsert(@RequestParam("vos") BatchUploadVo uploadVo) {
        uploadVo.getVos().forEach(e->{
            logger.info("文件名:{},关键字:{},正文:{},适用范围:{},原始文件名:{},原始文件大小:{}"
                    , e.getFilename(), e.getKeyword(), e.getText(), e.getScopes(), e.getAttachment().getOriginalFilename(), e.getAttachment().getSize());
        });
        return "number" + "hello";
    }

    public static void main(String[] args) {
        BufferedInputStream in = FileUtil.getInputStream("/1.doc");
        BufferedOutputStream out = FileUtil.getOutputStream("/1.docx");
        long copySize = IoUtil.copy(in, out, IoUtil.DEFAULT_BUFFER_SIZE);
        System.out.println("搞完了");
    }

}
