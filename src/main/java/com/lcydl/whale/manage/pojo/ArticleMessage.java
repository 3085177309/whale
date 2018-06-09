package com.lcydl.whale.manage.pojo;

import com.lcydl.whale.common.pojo.Article;

import java.util.List;

public class ArticleMessage {

    private Integer code;

    private String msg;

    private Integer count;

    private List<Article> data;

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

    public List<Article> getData() {
        return data;
    }

    public void setData(List<Article> data) {
        this.data = data;
    }
}
