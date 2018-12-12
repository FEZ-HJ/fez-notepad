package com.dream.feznotepad.config.wx;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by H.J
 * 2018/12/12
 */
public class WeChatLogin {

    @Value("${wx.appid}")
    private String APPID;

    @Value("${wx.secret}")
    private String SECRET;

    private String code;

    public String login(){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+
                "&secret="+SECRET+"&js_code="+ code +"&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.exchange(url,HttpMethod.GET, null, String.class);

        if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK){
            String sessionData = responseEntity.getBody();

            Gson gson = new Gson();

//            WeChatSession
        }
        return null;
    }
}
