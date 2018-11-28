package com.chen.core.Event;

/**
 * Created by chenxianglin on 2018/5/24.
 * Class note:
 */

public class ServiceEvent {
    private String state;
    private int progress;

    public ServiceEvent(String state, int progress) {
        this.state = state;
        this.progress = progress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
