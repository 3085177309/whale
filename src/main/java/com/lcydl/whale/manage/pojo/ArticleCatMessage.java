package com.lcydl.whale.manage.pojo;

import com.lcydl.whale.common.pojo.ArticleCat;

import java.util.List;

public class ArticleCatMessage {

    private Integer code;

    private String msg;

    private Integer count;

    private List<ArticleCat> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ArticleCat> getData() {
        return data;
    }

    public void setData(List<ArticleCat> data) {
        this.data = data;
    }
}
