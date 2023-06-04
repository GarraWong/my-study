package com.wong.filters;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * CAS过滤器配置
 *
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/6/2 17:29
 */
@Configuration
public class CasFilterConfigs {

    private static final Logger log = LoggerFactory.getLogger(CasFilterConfigs.class);

    /**
     * 单点登录退出
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean singleSignOutFilter() {
        log.info(" servletListenerRegistrationBean ");
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new SingleSignOutFilter());
        registrationBean.addUrlPatterns("/*");
        // registrationBean.addInitParameter("casServerUrlPrefix", casServerUrl);
        registrationBean.setName("CAS Single Sign Out Filter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    /**
     * 单点登录认证
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean authenticationFilter() {
        log.info(" AuthenticationFilter ");
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new AuthenticationFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("CAS Filter");
        registrationBean.addInitParameter("casServerLoginUrl", "http://47.92.125.222:8080/cas/login");
        // registrationBean.addInitParameter("casServerLoginUrl", "https://www.baidu.com");
        registrationBean.addInitParameter("serverName", "http://47.92.125.222:8084");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    /**
     * 该过滤器负责对 Ticket 的校验工作
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean cas20ProxyReceivingTicketValidationFilter() {
        log.info(" cas20ProxyReceivingTicketValidationFilter ");
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new Cas20ProxyReceivingTicketValidationFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("casServerUrlPrefix", "http://47.92.125.222:8080/cas");
        registrationBean.addInitParameter("serverName", "http://47.92.125.222:8084");
        registrationBean.addInitParameter("redirectAfterValidation", "true");
        registrationBean.setName("CAS Validation Filter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    /**
     * 该过滤器使得开发者可以通过 org.jasig.cas.client.util.AssertionHolder 来获取用
     * 户的登录名。 比如 AssertionHolder.getAssertion().getPrincipal().getName()
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean assertionThreadLocalFilter() {
        log.info(" assertionThreadLocalFilter ");
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new AssertionThreadLocalFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("CAS Validation Filter");
        registrationBean.setOrder(1);
        return registrationBean;
    }


    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        log.info(" servletListenerRegistrationBean ");
        ServletListenerRegistrationBean listenerRegistrationBean = new ServletListenerRegistrationBean();
        listenerRegistrationBean.setListener(new SingleSignOutHttpSessionListener());
        listenerRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return listenerRegistrationBean;
    }

}
