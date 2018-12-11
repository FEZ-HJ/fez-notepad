package com.dream.feznotepad.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by H.J
 * 2018/12/4
 */
@Entity
@Data
@Table(name = "time_content")
public class TimeContent implements Serializable {

    private static final long serialVersionUID = 1L;

    //自动生成id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //用户ID
    @Column(nullable = false)
    private String userId;

    //创建日期yyyy-MM-dd
    @Column(nullable = false)
    private String createTime;

    //开始时间yyyy-MM-dd HH:mm:ss
    @Column(nullable = false)
    private Date startTime;

    //结束时间yyyy-MM-dd HH:mm:ss
    @Column(nullable = false)
    private Date endTime;

    //活动内容
    @Column(nullable = false,length = 1000)
    private String content;

    //活动类型 0娱乐 1成长 2吃饭睡觉 3工作 4浪费
    @Column(nullable = false)
    private String type;

    //持续时间 单位/分钟
    @Column(nullable = false,precision = 12)
    private long duration;

    //创建时间yyyy-MM-dd HH:mm:ss
    @Column(nullable = false)
    private Date optTime = new Date();

}
