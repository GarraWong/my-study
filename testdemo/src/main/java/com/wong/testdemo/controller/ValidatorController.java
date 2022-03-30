package com.wong.testdemo.controller;

import com.alibaba.fastjson.JSON;
import com.wong.testdemo.model.validator.PayVo;
import com.wong.testdemo.model.validator.SubClass;
import com.wong.testdemo.model.validator.TestPath;
import com.wong.testdemo.service.MyService;
import com.wong.testdemo.service.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

/**
 * 输入类描述
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/26 10:01
 */
@RestController
@RequestMapping("/root")
@Validated
public class ValidatorController {

    private static final Logger logger = LoggerFactory.getLogger(ValidatorController.class);

    @Autowired
    private ValidatorService validatorService;
    @Autowired
    private MyService myService;

    @PostMapping("/pay")
    public String pay(HttpServletRequest request) {
        PayVo binfile = getBinfile(request, PayVo.class);
        logger.info("控制器方法内接收到的参数binfile:{}", binfile.toString());
        String pay = validatorService.pay(binfile);
        return pay;
    }

    @PostMapping("/inheritevalidate")
    public String testInheritValidate(@Valid SubClass vo) {
        logger.info("接收参数:{}", vo);
        return vo.toString();
    }

    @PutMapping("/path/{flag}")
    public String testPathVariable(@PathVariable("flag") Boolean flag, @RequestBody TestPath path) {
        logger.info("flag-->{}", flag);
        logger.info("path-->{}", path.getIds());
        return String.valueOf(flag);
    }


     /**
     * 获取请求里的binFile,将其转换成对应的javaBean
     * @param request 请求
     * @param type    javaBean类型
     * @return binFile对应的javaBean
     */
     public static <T> T getBinfile(HttpServletRequest request, Class<T> type) {
        MultipartHttpServletRequest params = (MultipartHttpServletRequest) request;
        MultipartFile file = params.getFile("binFile");
        if (file == null) {
            logger.error("request对象不包含binFile,文件名:{},目标类型{}", request.getParameter("filename"), type.getSimpleName());
            throw new RuntimeException("未获取到binFile");
        }
        byte[] bytes;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            String msg = String.format("获取文件的字节失败{%s},文件名{%s},异常信息:", file.getOriginalFilename(),request.getParameter("filename"));
            logger.error(msg, e);
            throw new RuntimeException(String.format("获取文件的字节数组失败{%s}", file.getOriginalFilename()));
        }
        String str = new String(bytes);
        T t;
        try {
            t = JSON.parseObject(str, type);
        } catch (Exception e) {
            logger.error("getBinfile方法转换json对象失败,待转换字符串:{%s},目标转换类型:{%s},文件名:{%s}",
                    str, type.getName(), request.getParameter("filename"));
            throw new RuntimeException("转换文件内容为json发生异常");
        }
        return t;
    }
}
