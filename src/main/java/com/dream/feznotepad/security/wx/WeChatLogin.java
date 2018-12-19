package com.dream.feznotepad.security.wx;

import com.google.gson.Gson;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by H.J
 * 2018/12/12
 */
@Data
public class WeChatLogin {

    @Value(value = "${wx.appid}")
    public String APPID = "wxc9c4cac7180438f5";

    @Value("${wx.secret}")
    private String SECRET = "fe49531867a13bb85afc08668a03fa40";

    private String code;

    public WeChatLogin(String code){
        this.code = code;
    }

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
