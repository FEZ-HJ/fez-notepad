package com.dream.feznotepad.utils;

import lombok.Data;

/**
 * Created by H.J
 * 2018/12/5
 */
@Data
public class SimpleReturn {

    private Object content;
    private String status;
    private String message;

    public SimpleReturn(Object content,String status,String message){
        this.content = content;
        this.status = status;
        this.message = message;
    }

    public SimpleReturn(String status,String message){
        this.status = status;
        this.message = message;
    }
}
