package com.dream.feznotepad.controller;

import com.dream.feznotepad.repository.TimeContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by H.J
 * 2018/12/3
 */
@Controller
public class web {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private TimeContentRepository repository;

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        redirectStrategy.sendRedirect(request,response,"/index");
        return "login";
    }

    @RequestMapping("reg")
    public String reg(){
        return "reg";
    }

//    @RequestMapping("add")
//    public String add(){
//
//    }
}
