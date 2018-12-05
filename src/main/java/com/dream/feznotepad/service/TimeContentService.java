package com.dream.feznotepad.service;

import com.dream.feznotepad.entity.TimeContent;
import com.dream.feznotepad.repository.TimeContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by H.J
 * 2018/12/5
 */
@Service
public class TimeContentService {

    @Autowired
    TimeContentRepository repository;

    public void save(TimeContent timeContent){
        repository.save(timeContent);
    }

    /**
     * 查询某一天的所有记录
     * @param createTime yyyy-MM-dd
     * @param userId 用户名
     */
    public List<TimeContent> findOnDay(String createTime,String userId){
        return repository.findOnDay(createTime,userId);
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }
}
