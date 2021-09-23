package com.wong.redisdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

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

}
