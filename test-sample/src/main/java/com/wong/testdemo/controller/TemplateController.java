package com.wong.testdemo.controller;

import com.wong.testdemo.model.CallbackVo;
import com.wong.testdemo.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * 类注释 完善它
 *
 * @author : wangyumou
 * @date : 2021/9/23 16:29
 */
@RestController
public class TemplateController {

    private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);


    @Autowired
    @Qualifier("httpTemplate")
    private RestTemplate template;
    @Autowired
    @Qualifier("httpsTemplate")
    private RestTemplate httpsTemplate;
    @Autowired
    private MyService myService;


    @GetMapping("/proxyget")
    public String testProxy() {
        String forObject = "{\"code\":\"400\"}";
        try {
//            forObject = template.getForObject("http://www.httpbin.org/ip", String.class);
            forObject = template.getForObject("https://httpbin.org/get", String.class);
        } catch (RestClientException e) {
            logger.error("调用失败", e);
        }
        logger.info(forObject);
        return forObject;
    }

    @PostMapping("/callback")
    public String testCallback(@RequestBody CallbackVo url) {
        String forObject = "https://httpbin.org/get";
        logger.info("收到的地址:{}", url.getUrl());
        try {
            ResponseEntity<String> entity = httpsTemplate.postForEntity(url.getUrl(), null, String.class);
           logger.info(entity.getBody());
            return entity.getBody();
        } catch (RestClientException e) {
            logger.error("调用失败", e);
        }
        return "失败";
    }

    @GetMapping("/proxyip")
    public String testProxyIp() {
        String forObject = "{\"code\":\"400\"}";
        try {
            forObject = template.getForObject("http://www.httpbin.org/ip", String.class);
            System.out.println();
//            forObject = template.getForObject("https://httpbin.org/get", String.class);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        } catch (RestClientException e) {
            logger.error("调用失败", e);
        }
        logger.info(forObject);
        return forObject;
    }
}
