package com.chen.router;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by chenxianglin on 2018/5/21.
 * Class note:
 */

public class RouteBean extends BaseObservable {
    private String id;
    private String title;

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.id);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.title);
    }
}
