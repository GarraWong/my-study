package com.wong.redisdemo.controller;

import com.wong.redisdemo.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
