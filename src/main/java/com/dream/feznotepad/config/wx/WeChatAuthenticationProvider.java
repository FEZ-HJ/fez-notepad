package com.dream.feznotepad.config.wx;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 微信登录身份验证
 * Created by H.J
 * 2018/12/14
 */
public class WeChatAuthenticationProvider implements AuthenticationProvider {

    private WeChatUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WeChatAuthenticationToken authenticationToken = (WeChatAuthenticationToken)authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());

        if(userDetails == null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        WeChatAuthenticationToken authenticationTokenResult = new WeChatAuthenticationToken(userDetails,userDetails.getAuthorities());

        authenticationTokenResult.setDetails(authenticationToken.getDetails());

        return authenticationTokenResult;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return WeChatAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
