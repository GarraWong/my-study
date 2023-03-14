package com.wong.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 短信登录 AuthenticationToken，模仿 UsernamePasswordAuthenticationToken 实现
 * @author jitwxs
 * @since 2019/1/9 13:47
 */
public class IdassAuthentiactionToken extends AbstractAuthenticationToken {

    /**
     * 门户跳转token
     */
    private final Object principal;


    public IdassAuthentiactionToken(Object principal) {
        super(null);
        this.principal = principal;
        setAuthenticated(false);
    }

    /**
     * Creates a token with the supplied array of authorities.
     * <p>
     * 创建认证成功用户对象
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public IdassAuthentiactionToken(Object principal,
                                    Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }


    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }


    @Override
    public String toString() {
        return "自定义token{" +
                "principal=" + principal +','
                +"hashcode:" + this.hashCode()+'}';
    }
}
