package com.dream.feznotepad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by H.J
 * 2018/12/3
 */
@Controller
public class web {

    @RequestMapping("ss")
    public String ss(){
        System.out.println("s");
        return "index";
    }
}
