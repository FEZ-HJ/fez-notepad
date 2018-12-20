package com.dream.feznotepad.service;

import com.dream.feznotepad.entity.WebUserDetail;
import com.dream.feznotepad.repository.WebUserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by H.J
 * 2018/12/20
 */
@Service
public class WebUserDetailService {

    @Autowired
    WebUserDetailRepository repository;

    public void save(WebUserDetail webUserDetail){
        repository.save(webUserDetail);
    }

    public WebUserDetail findByUserId(String userId){
        return repository.findByUserId(userId);
    }

}
