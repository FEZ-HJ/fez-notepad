package com.dream.feznotepad.repository;

import com.dream.feznotepad.entity.TimeContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Created by H.J
 * 2018/12/5
 */
public interface TimeContentRepository extends JpaRepository<TimeContent,String> {

    @Query(value = "select id,userId,createTime,startTime,endTime,content,duration,type " +
            "from TimeContent where createTime = ?1 and userId = ?2")
    List<TimeContent> findOnDay(String create_time, String userId);
}
