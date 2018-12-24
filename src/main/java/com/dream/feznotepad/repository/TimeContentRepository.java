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
     * @param create_time yyyy-MM-dd
     * @param userId 用户名
     */
    @Query(value = "select a from TimeContent a where a.createTime = ?1 and a.userId = ?2 order by a.startTime desc ")
    List<TimeContent> findOnDay(String create_time, String userId);

    /**
     * 根据时间查询中间的记录
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param userId    用户ID
     */
    @Query(value = "select a from TimeContent a where a.startTime> ?1 and a.endTime < ?2 and a.userId = ?3")
    List<TimeContent> findByDays(Date startTime, Date endTime, String userId);

    /**
     * 查询一天中，最后的结束时间
     * @param createTime 某一天 yyyy-mm-dd
     */
    @Query(value = "select max(endTime) from TimeContent where createTime = ?1")
    Date findMaxEndTime(String createTime);

    /**
     *  查询用户下的记录的天数
     */
    @Query(value = "select count(distinct createTime) from TimeContent  where userId = ?1")
    int findCountByUserId(String userId);

}
