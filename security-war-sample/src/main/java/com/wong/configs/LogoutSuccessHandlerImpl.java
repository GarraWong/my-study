package com.wong.configs;

import com.alibaba.fastjson2.JSON;
import com.wong.entity.AjaxResult;
import com.wong.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 *
 * @author ruoyi
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {


    private static final Logger logger = LoggerFactory.getLogger(LogoutSuccessHandlerImpl.class);


    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        logger.info("退出成功");
        HttpUtils.renderString(response, JSON.toJSONString(AjaxResult.success("退出成功")));
    }
}
