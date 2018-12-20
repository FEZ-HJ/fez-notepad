package com.dream.feznotepad.security.wx;

import com.dream.feznotepad.security.weChat.WeChatUserDetailsService;
import com.dream.feznotepad.security.web.WebUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * Created by H.J
 * 2018/12/19
 */
@Component
public class WeChatAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

//    @Autowired
//    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
//
//    @Autowired
//    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private WeChatUserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        WeChatAuthenticationFilter smsCodeAuthenticationFilter = new WeChatAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
//        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
//        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);

        WeChatAuthenticationProvider smsCodeAuthenticationProvider = new WeChatAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterBefore(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
