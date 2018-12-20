package com.dream.feznotepad.controller;

import com.dream.feznotepad.entity.TimeContent;
import com.dream.feznotepad.service.TimeContentService;
import com.dream.feznotepad.utils.DateUtils;
import com.dream.feznotepad.utils.SimpleReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by H.J
 * 2018/12/5
 */
@RestController
@RequestMapping("timeContent")
public class TimeContentController {

    @Autowired
    private TimeContentService service;

    /**
     * 保存记录
     * @param content   记录内容
     * @param startTime 开始时间 yyyy-mm-dd hh:mm:ss
     * @param endTime   结束时间 yyyy-mm-dd hh:mm:ss
     * @param type      类型
     * @param userId    用户ID
     */
    @GetMapping("save")
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
     * @param userId     用户名
     */
    @GetMapping("findOnDay")
    public SimpleReturn findOnDay(String createTime,String userId){
        List<TimeContent> list = service.findOnDay(createTime,userId);
        return new SimpleReturn(list,"200","查询成功!");
    }

    /**
     * 按照ID删除记录
     * @param id  Long
     */
    @DeleteMapping("deleteById")
    public SimpleReturn deleteById(Long id){
        service.deleteById(id);
        return new SimpleReturn("200","删除成功!");
    }

    /**
     * 根据时间查询中间的记录的各类型的时长总和
     * @param startTime 开始时间 yyyy-mm-dd
     * @param endTime   结束时间 yyyy-mm-dd
     * @param userId    用户ID
     */
    @GetMapping("findByDays")
    public SimpleReturn findByDays(String startTime,String endTime,String userId){
        List<TimeContent> list = service.findByDays(startTime,endTime,userId);
        Map<String,Long> map = service.durationOfType(list);
        return new SimpleReturn(map,"200","查询成功!");
    }

}
