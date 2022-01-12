package com.wong.testdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * 类注释 完善它
 *
 * @author : wangyumou
 * @date : 2021/9/23 16:28
 */
@Configuration
public class RestfulConfig {

    private static final Logger logger = LoggerFactory.getLogger(RestfulConfig.class);


    @Bean("httpTemplate")
    public RestTemplate restTemplate() {

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(3000);
        requestFactory.setReadTimeout(7000);
//        requestFactory.setProxy(new Proxy(Proxy.Type.HTTP,new InetSocketAddress("8.131.54.229",18888)));
//        logger.info("加载代理配置了.....");
        RestTemplate template = new RestTemplate(requestFactory);
        return template;
    }


    /**
     * https请求的restTemplate
     * @return
     */
    @Bean(name = "httpsTemplate")
    public RestTemplate httpsTemplate() {
        //使用自定义的https工厂
        HttpsClientRequestFactory requestFactory = new HttpsClientRequestFactory();
        requestFactory.setConnectTimeout(3000);
        requestFactory.setReadTimeout(5000);
        RestTemplate template = new RestTemplate(requestFactory);
        template.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return template;
    }
}
