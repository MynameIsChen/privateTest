package com.chen.core.bean;

/**
 * Created by chenxianglin on 2018/5/29.
 * Class note:
 */

public class MinaNetEntity implements BaseEntity {
    private String sendBody;
    private String responseBody;

    public String getSendBody() {
        return sendBody;
    }

    public void setSendBody(String sendBody) {
        this.sendBody = sendBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
