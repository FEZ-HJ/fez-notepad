package com.dream.feznotepad.security.wx;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by H.J
 * 2018/12/19
 */
public class WeChatAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    // 请求参数key
    private String wxParameter = "code";
    // 是否只支持POST
    private boolean postOnly = true;

    public WeChatAuthenticationFilter() {
        // 请求接口的url
        super(new AntPathRequestMatcher("/wxLogin", "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        // 根据请求参数名，获取请求value
        String code = obtainMobile(request);
        if (code == null) {
            code = "";
        }
        code = code.trim();

        WeChatLogin weChatLogin = new WeChatLogin(code);
        weChatLogin.login();
        // 生成对应的AuthenticationToken
        WeChatAuthenticationToken authRequest = new WeChatAuthenticationToken(code);

//        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 微信登录的code
     */
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(wxParameter);
    }
}
