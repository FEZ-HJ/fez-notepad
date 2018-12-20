package com.dream.feznotepad.controller;

import com.dream.feznotepad.security.weChat.JwtToken;
import com.dream.feznotepad.security.weChat.WeChatLogin;
import com.dream.feznotepad.security.weChat.WeChatSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by H.J
 * 2018/12/3
 */
@Controller
public class LoginController {

    @Autowired
    private WeChatLogin weChatLogin;

    //首页
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    //登录页面
    @RequestMapping("login")
    public String login(){
        return "login";
    }

    //注册页面
    @RequestMapping("reg")
    public String reg(){
        return "reg";
    }

    //微信登录，获取密钥
    @PostMapping("wxLogin")
    @ResponseBody
    public String wx(String code, String nickName) throws Exception {
        WeChatSession weChatSession = weChatLogin.login(code,nickName);
        if(weChatSession != null){
            return JwtToken.createToken(weChatSession.getOpenid());
        }else {
            return "";
        }
    }
}