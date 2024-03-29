package com.wong.configs;

import com.alibaba.fastjson2.JSON;
import com.wong.entity.AjaxResult;
import com.wong.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 *
 * @author ruoyi
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -8970718410437077606L;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationEntryPointImpl.class);


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        int code = 401;
        String msg = String.format("请求访问：{%s}，认证失败，无法访问系统资源", request.getRequestURI());
        HttpUtils.renderString(response, JSON.toJSONString(AjaxResult.error(code, msg)));
    }

}
