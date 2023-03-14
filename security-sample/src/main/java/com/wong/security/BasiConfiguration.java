package com.wong.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class BasiConfiguration {



    @Bean
    @ConditionalOnExpression("@idassConfig.getDev().equals(false)")
    public IdassProvider idassProvider() {
        return new IdassProvider();
    }

    @Bean
    @ConditionalOnExpression("@idassConfig.getDev().equals(false)")
    public SuccessHandler successHandler() {
        return new SuccessHandler();
    }

    @Bean
    @ConditionalOnExpression("@idassConfig.getDev().equals(false)")
    public FailureHandler failureHandler() {
        return new FailureHandler();
    }

    @Bean
    @ConditionalOnExpression("@idassConfig.getDev().equals(false)")
    public IdassLoginFilter idassLoginFilter(AuthenticationManager authenticationManager) {
        return new IdassLoginFilter(authenticationManager);
    }

    @Bean
    @ConditionalOnExpression("@idassConfig.getDev().equals(true)")
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    @ConditionalOnExpression("@idassConfig.getDev().equals(false)")
    public IdassSecurityConfig idassSecurityConfig(IdassLoginFilter idassLoginFilter) {
        return new IdassSecurityConfig(successHandler(),
                failureHandler(),
                idassProvider(),
                idassLoginFilter);
    }


}
