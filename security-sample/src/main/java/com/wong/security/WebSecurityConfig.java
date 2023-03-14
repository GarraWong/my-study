package com.wong.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * @author jitwxs
 * @date 2018/3/29 16:57
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired(required = false)
    @Lazy   //这里也是个傻逼位置，WebSecurityConfigurerAdapter注入AuthenticationManager有bug，
    // 导致了必须WebSecurityConfig初始化好以后才能获取到父类抽象方法的builder，但是其他类又依赖AuthenticationManager
    private IdassSecurityConfig idassSecurityConfig;
    @Autowired
    private LogoutSuccessImpl logoutSuccess;
    @Autowired
    private IdassConfig idassConfig;
    @Autowired(required = false)
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/login").anonymous()
                .anyRequest().authenticated();
        http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccess);
        if (idassConfig.getDev()) {
            http.addFilterBefore(jwtAuthenticationFilter, LogoutFilter.class);
        } else {
            http.apply(idassSecurityConfig);
            // 关闭CSRF跨域
            http.csrf().disable();
        }
    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 为什么写这么个傻逼玩意儿？
     * 因为如果没有一个这样的UserDetailsService，将会有一个循环依赖启动报错
     * 参考下面的傻逼解决办法，找了我三个小时
     * <br>
     * <a href="https://github.com/spring-projects/spring-security/issues/8369#issuecomment-614862388">傻逼解决办法</a>
     * @
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username -> {
            throw new UnsupportedOperationException("unsupported mode");
        };
    }
}