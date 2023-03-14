package com.wong.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class MyFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);

    private static  int count = 1;

    static {
        logger.info("MyFilter 初始化....");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        logger.info("执行了Myfilter {} 次" ,count);
        count++;
        chain.doFilter(request,response);
        logger.info("执行了Myfilter后置 {} 次", count - 1);
    }
}
