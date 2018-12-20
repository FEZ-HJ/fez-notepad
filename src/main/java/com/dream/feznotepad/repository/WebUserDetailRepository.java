package com.dream.feznotepad.repository;

import com.dream.feznotepad.entity.WebUserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by H.J
 * 2018/12/20
 */
public interface WebUserDetailRepository extends JpaRepository<WebUserDetail,String> {

    WebUserDetail findByUserId(String userId);
}
