package com.dream.feznotepad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by H.J
 * 2018/12/3
 */
@Controller
public class web {

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("reg")
    public String reg(){
        return "reg";
    }
}
