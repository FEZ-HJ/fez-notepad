package com.dream.feznotepad.security.weChat;

import com.dream.feznotepad.entity.WebUserDetail;
import com.dream.feznotepad.service.WebUserDetailService;
import com.google.gson.Gson;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Created by H.J
 * 2018/12/12
 * 验证微信小程序合法性
 */
@Data
@Component
public class WeChatLogin {

    @Value("${wx.appid}")
    private String APPID;

    @Value("${wx.secret}")
    private String SECRET;

    @Autowired
    private WebUserDetailService webUserDetailService;

    public WeChatSession login(String code,String nickName){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+
                "&secret="+SECRET+"&js_code="+ code +"&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.exchange(url,HttpMethod.GET, null, String.class);

        if(responseEntity.getStatusCode() == HttpStatus.OK){
            String sessionData = responseEntity.getBody();

            //{"session_key":"iTCiU6k9V1akUZs4\/YwyLA==","openid":"oj4mK5VuqTnTh4zYpl3X7tYcPR6U"}
            Gson gson = new Gson();
            WeChatSession weChatSession = gson.fromJson(sessionData,WeChatSession.class);

            WebUserDetail userDetail = webUserDetailService.findByUserId(weChatSession.getOpenid());

            //向数据库插入用户信息
            if(userDetail != null){
                userDetail.setOptTime(new Date());
                webUserDetailService.save(userDetail);
            }else{
                webUserDetailService.save(new WebUserDetail(weChatSession.getOpenid(),weChatSession.getSession_key(),nickName));
            }

            return weChatSession;
        }
        return null;
    }
}
