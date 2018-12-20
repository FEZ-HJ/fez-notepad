package com.dream.feznotepad.service;

import com.dream.feznotepad.entity.WebUserDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by H.J
 * 2018/12/20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WebUserDetailServiceTest {

    @Autowired
    private WebUserDetailService webUserDetailService;

    @Test
    public void save() {
        webUserDetailService.save(new WebUserDetail("zhangsan","lisi","sss"));
    }
}