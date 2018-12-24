package com.dream.feznotepad.service;

import com.dream.feznotepad.dto.DurationDTO;
import com.dream.feznotepad.entity.TimeContent;
import com.dream.feznotepad.repository.TimeContentRepository;
import com.dream.feznotepad.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;

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

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    /**
     * 根据时间查询中间的记录
     * @param startTime 开始时间 yyyy-mm-dd
     * @param endTime   结束时间 yyyy-mm-dd
     * @param userId    用户ID
     */
    public List<TimeContent> findByDays(String startTime,String endTime,String userId){
        return repository.findByDays(
                DateUtils.strToDateLong(startTime + " 00:00:00")
                ,DateUtils.strToDateLong(endTime + " 23:59:59")
                ,userId);
    }

    /**
     * 计算记录中各类型的时长总和
     * @param list 记录集合
     */
    public List<DurationDTO> durationOfType(List<TimeContent> list){
        Map<String,Long> map = new HashMap<>();
        for(TimeContent timeContent : list){
            if(!map.containsKey(timeContent.getType())){
                map.put(timeContent.getType(),timeContent.getDuration());
            }else{
                map.put(timeContent.getType(),map.get(timeContent.getType()) + timeContent.getDuration());
            }
        }
        return durationOfType(map);
    }


    private List<DurationDTO> durationOfType(Map<String,Long> map){
        List<DurationDTO> dtos = new ArrayList<>();
        for (String key : map.keySet()) {
            DurationDTO dto = new DurationDTO();
            String type = "";
            switch (key){
                case "0" :
                    type = "娱乐";
                    break;
                case "1" :
                    type = "成长";
                    break;
                case "2" :
                    type = "吃饭,睡觉";
                    break;
                case "3" :
                    type = "工作";
                    break;
                case "4" :
                    type = "浪费";
                    break;
            }
            dto.setName(type);
            dto.setData(map.get(key));
            dtos.add(dto);
        }
        return dtos;
    }

    /**
     * 查询一天中，最后的结束时间
     * @param createTime 某一天 yyyy-mm-dd
     */
    public Date findMaxEndTime(String createTime){
        return repository.findMaxEndTime(createTime);
    }

    /**
     *  查询用户下的记录的天数
     */
    public int findCountByUserId(String userId){
        return repository.findCountByUserId(userId);
    }
}
