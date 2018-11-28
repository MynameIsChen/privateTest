package com.chen.core.bean;

import javax.inject.Inject;

/**
 * Created by chenxianglin on 2018/5/22.
 * Class note:
 */

public class DaggerEntity implements BaseEntity {
    private int id;
    private String title;

    @Inject
    public DaggerEntity() {
    }


    public DaggerEntity(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
