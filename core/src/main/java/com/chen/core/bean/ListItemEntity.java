package com.chen.core.bean;

/**
 * Created by chenxianglin on 2018/1/10.
 * Class note:
 */

public class ListItemEntity implements BaseEntity {
    private int type;
    private String value;

    public ListItemEntity() {
    }

    public ListItemEntity(int type, String value) {
        this.type = type;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
