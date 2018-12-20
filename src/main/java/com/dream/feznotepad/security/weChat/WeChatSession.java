package com.dream.feznotepad.security.weChat;

import lombok.Data;

/**
 * Created by H.J
 * 2018/12/20
 * 微信小程序的session和ID
 */
@Data
public class WeChatSession {

    private String session_key;
    private String openid;
}
