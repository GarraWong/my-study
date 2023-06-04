package com.wong.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * 如果不是CAS单点登录，直接放行
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/6/4 17:13
 */
public class NoCasFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
