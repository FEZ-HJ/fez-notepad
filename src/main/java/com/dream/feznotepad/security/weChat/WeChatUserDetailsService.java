package com.dream.feznotepad.security.weChat;

import com.dream.feznotepad.entity.WebUserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by H.J
 * 2018/12/6
 * 小程序通过JWT验证用户
 */
@Component
public class WeChatUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("微信小程序登录用户名： " + username);
        return new WebUserDetail(username);
    }

}
