package com.wong.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

public class IdassSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private SuccessHandler successHandler;
    private FailureHandler failureHandler;
    private IdassProvider idassProvider;
    private IdassLoginFilter idassLoginFilter;

    public IdassSecurityConfig(SuccessHandler successHandler,
                               FailureHandler failureHandler,
                               IdassProvider idassProvider,
                               IdassLoginFilter idassLoginFilter) {
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.idassProvider = idassProvider;
        this.idassLoginFilter = idassLoginFilter;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        idassLoginFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        idassLoginFilter.setAuthenticationSuccessHandler(successHandler);   //必须写这里
        idassLoginFilter.setAuthenticationFailureHandler(failureHandler);

        http.authenticationProvider(idassProvider)
//                .addFilterAfter(idassLoginFilter, UsernamePasswordAuthenticationFilter.class);
                .addFilterBefore(idassLoginFilter, LogoutFilter.class);
    }
}
