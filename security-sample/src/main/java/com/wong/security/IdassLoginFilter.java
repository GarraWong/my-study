package com.wong.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 短信登录的鉴权过滤器，模仿 UsernamePasswordAuthenticationFilter 实现
 * @author jitwxs
 * @since 2019/1/9 13:52
 */

public class IdassLoginFilter extends AbstractAuthenticationProcessingFilter {
    /**
     * form表单中手机号码的字段name
     */
    public static final String ID_TOKEN_KEY = "id_token";

    private String idTokenParameter = ID_TOKEN_KEY;

    private static final Logger logger = LoggerFactory.getLogger(IdassLoginFilter.class);

    static {
        logger.info("IdassLoginFilter 实例化中...");
    }

//    @Autowired
//    @Lazy   //依赖于AuthenticationManager，AuthenticationManager依赖于IdassSecurityConfig,IdassSecurityConfig依赖于本类，循环依赖
    public IdassLoginFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/login", "GET"), authenticationManager);
    }

    // @Override
    // // @Autowired
    // public void setAuthenticationManager(AuthenticationManager authenticationManager) {
    //     super.setAuthenticationManager(authenticationManager);
    // }

    //TODO 研究为什么会调用两次。不是因为Once的原因，doFiter的时候已经设置了只调用一次，感觉是因为设置了两个once
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String idToken = obtainIdToken(request);

        if (idToken == null) {
            idToken = "";
        }

        idToken = idToken.trim();

        IdassAuthentiactionToken authRequest = new IdassAuthentiactionToken(idToken);

        // Allow subclasses to set the "details" property
        //这里是个重点，不然子类或新生成的获取不到,根据源代码获知
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected String obtainIdToken(HttpServletRequest request) {
        return request.getParameter(idTokenParameter);
    }

    protected void setDetails(HttpServletRequest request, IdassAuthentiactionToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }



}
