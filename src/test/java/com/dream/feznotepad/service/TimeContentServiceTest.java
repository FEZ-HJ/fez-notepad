package com.dream.feznotepad.service;

import com.dream.feznotepad.entity.TimeContent;
import com.dream.feznotepad.utils.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


/**
 * Created by H.J
 * 2018/12/5
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TimeContentServiceTest {

    @Autowired
    TimeContentService service;

    @Test
    public void save() {
        TimeContent timeContent = new TimeContent();
        timeContent.setUserId("zhansan");
        timeContent.setContent("打豆豆");
        timeContent.setCreateTime(DateUtils.dateToStrshort(new Date()));
        timeContent.setStartTime(DateUtils.strToDateLong("2018-11-22 10:22:00"));
        timeContent.setEndTime(DateUtils.strToDateLong("2018-11-22 10:24:00"));
        timeContent.setType("0");
        timeContent.setDuration(DateUtils.getDuration("2018-11-22 10:22:00","2018-11-22 10:24:00"));
        service.save(timeContent);
    }

    @Test
    public void findOnDay() {
        String userId = "zhansan";
        List<TimeContent> list = service.findOnDay(DateUtils.dateToStrshort(new Date()),userId);
        Assert.assertEquals(list.size(),1);
    }

    @Test
    public void deleteById() {
        service.deleteById("1");
    }
}