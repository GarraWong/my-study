package com.wong.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token过滤器 验证token有效性
 *
 * @author ruoyi
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    @Qualifier("handlerExceptionResolver")
    HandlerExceptionResolver resolver;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            if (true) {
                throw new DisabledException("这里报错咯");
            }
        } catch (Exception e) {
            logger.error("JwtAuthenticationTokenFilter报错,请求转发");
//            request.getRequestDispatcher("/relogin").forward(request, response);
            resolver.resolveException(request, response, null, new AccessDeniedException("我自己定义的"));
            return;
        }

        chain.doFilter(request, response);
    }
}
