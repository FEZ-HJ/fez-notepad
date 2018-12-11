package com.dream.feznotepad.repository;

import com.dream.feznotepad.entity.TimeContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


/**
 * Created by H.J
 * 2018/12/5
 */
public interface TimeContentRepository extends JpaRepository<TimeContent,Long> {

    /**
     * 查询某一天的所有记录
     * @param createTime yyyy-MM-dd
     * @param userId 用户名
     */
    @Query(value = "select id,userId,createTime,startTime,endTime,content,duration,type " +
            "from TimeContent where createTime = ?1 and userId = ?2")
    List<TimeContent> findOnDay(String create_time, String userId);

    /**
     * 根据时间查询中间的记录
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param userId    用户ID
     */
    @Query(value = "select id,userId,createTime,startTime,endTime,content,duration,type " +
            "from TimeContent where startTime> ?1 and endTime < ?2 and userId = ?3")
    List<TimeContent> findByDays(Date startTime, Date endTime, String userId);
}
