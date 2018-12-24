package com.dream.feznotepad.security.web;

import com.dream.feznotepad.entity.WebUserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by H.J
 * 2018/12/4
 */
@Component
public class WebUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录用户名： " + username);
        return buildUser(username);
    }

    private UserDetails buildUser(String userId) {
        String password = passwordEncoder.encode("123456");
        logger.info("数据库密码是：" + password);
        return new User(userId,password,true,true,true,true
                ,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//        return new WebUserDetail(userId);
    }
}
