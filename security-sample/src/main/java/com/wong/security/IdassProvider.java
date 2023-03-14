package com.wong.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;

/**
 * 短信登陆鉴权 Provider，要求实现 AuthenticationProvider 接口
 * @author jitwxs
 * @since 2019/1/9 13:59
 */
public class IdassProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(IdassProvider.class);

    private static int count = 1;

    static {
        logger.info("IdassProvider 实例化中...");
    }



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        IdassAuthentiactionToken authenticationToken = (IdassAuthentiactionToken) authentication;

        String idToken = (String) authenticationToken.getPrincipal();
        // 校验token是可用的，这一步是跳转过来使用的。

        logger.info("调用了provider方法 执行了 {} 次, Authentication :{}", count, authentication);
        count++;

//        //有异常抛出即为失败
//        //没有异常抛出即为token有效
//        Claims claims = idassUserService.valid(idToken);

        //调用接口查询当前token的权限，包装成GrantedAuthorityjihe

//        List<IdassAuthResult> authrities = idassUserService.queryUserAuthrities(claims);

        // 此时鉴权成功后，应当重新 new 一个拥有鉴权的 authenticationResult 返回
        IdassAuthentiactionToken authenticationResult = new IdassAuthentiactionToken(idToken, new ArrayList<>());

        authenticationResult.setDetails(authenticationToken.getDetails());  //{@link IdassAuthenticationProcessingFilter#attemptAuthentication}
        // SecurityContextHolder.getContext().setAuthentication(authenticationResult); //放入上下文 方便读取
        return authenticationResult;
    }

    /**
     * 必须重写此方法，用来给AuthenticationProvider循环判断采用哪一个provider实现
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 IdassAuthentiactionToken 的子类或子接口
        logger.info("执行了 IdassProvider support判断 {} 次", count);
        return IdassAuthentiactionToken.class.isAssignableFrom(authentication);
    }
}