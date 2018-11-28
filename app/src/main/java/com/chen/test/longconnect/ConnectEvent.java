package com.chen.test.longconnect;

/**
 * Created by chenxianglin on 2018/6/1.
 * Class note:
 */

public class ConnectEvent {
    private int type;
    private String info;
    private long time;

    public ConnectEvent(String info) {
        this.info = info;
        setType(-1);
        setTime(System.currentTimeMillis());
    }

    public ConnectEvent(int type, String info) {
        this.type = type;
        this.info = info;
        setTime(System.currentTimeMillis());
    }

    public ConnectEvent(int type, String info, long time) {
        this.type = type;
        this.info = info;
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
