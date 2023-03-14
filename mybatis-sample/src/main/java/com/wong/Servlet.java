package com.wong;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code code in MacBook pro 2020 Silicon
 * @date : 2022/12/29 12:51
 */
public class Servlet extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MybatisApp.class);
    }

}
