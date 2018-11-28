package com.chen.core.bean;

/**
 * Created by chenxianglin on 2018/5/29.
 * Class note:
 */

public class MinaEntity implements BaseEntity {
    private String responseHeader;
    private String responseBody;

    public String getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(String responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
