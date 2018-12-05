package com.dream.feznotepad.controller;

import com.dream.feznotepad.entity.TimeContent;
import com.dream.feznotepad.service.TimeContentService;
import com.dream.feznotepad.utils.DateUtils;
import com.dream.feznotepad.utils.SimpleReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by H.J
 * 2018/12/5
 */
@RestController
@RequestMapping("timeContent")
public class TimeContentController {

    @Autowired
    private TimeContentService service;

    @PostMapping("save")
    public SimpleReturn save(String content, String startTime, String endTime, String type, String userId){
        TimeContent timeContent = new TimeContent();
        timeContent.setUserId(userId);
        timeContent.setCreateTime(DateUtils.dateToStrshort(new Date()));
        timeContent.setContent(content);
        timeContent.setStartTime(DateUtils.strToDateLong(startTime));
        timeContent.setEndTime(DateUtils.strToDateLong(endTime));
        timeContent.setDuration(DateUtils.getDuration(startTime,endTime));
        timeContent.setType(type);
        service.save(timeContent);
        return new SimpleReturn("200","添加成功!");

    }

    /**
     * 查询某一天的所有记录
     * @param createTime yyyy-MM-dd
     * @param userId 用户名
     */
    @GetMapping("findOnDay")
    public SimpleReturn findOnDay(String createTime,String userId){
        return new SimpleReturn(service.findOnDay(createTime,userId),"200","查询成功!");
    }

    @PostMapping("deleteById")
    public SimpleReturn deleteById(String id){
        service.deleteById(id);
        return new SimpleReturn("200","删除成功!");
    }

}
