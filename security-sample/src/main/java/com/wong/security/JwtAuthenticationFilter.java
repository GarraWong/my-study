package com.wong.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static int count = 1;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    static {
        logger.info("JwtAuthenticationFilter 实例化中...");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        logger.info("JwtAuthenticationFilter 执行了 {}", count);
//        LoginUser loginUser = tokenService.getLoginUser(request);
//        if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
//            tokenService.verifyToken(loginUser);
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
//            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);  //后续发现上下文有用户信息，就不会校验了
//        }
        logger.info("第一次断点");
        logger.info("第二次断点");
        count++;
        chain.doFilter(request, response);
    }
}
