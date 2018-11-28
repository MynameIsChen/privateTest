package com.chen.core.bean;

/**
 * Created by chenxianglin on 2017/11/24.
 * Class note:
 */

public class TitleEntity implements BaseEntity {
    private String title;

    public TitleEntity(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
