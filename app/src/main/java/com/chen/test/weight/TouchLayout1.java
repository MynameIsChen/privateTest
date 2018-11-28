package com.chen.test.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by chenxianglin on 2017/12/4.
 * Class note:
 */

public class TouchLayout1 extends RelativeLayout {
    public static final String TAG = "TouchLayout1";

    public TouchLayout1(Context context) {
        super(context);
    }

    public TouchLayout1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchLayout1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent  action:" + ev.getAction());
        boolean state = super.onInterceptTouchEvent(ev);
        return state;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onTouchEvent  action:" + ev.getAction());
        boolean state = super.onTouchEvent(ev);
        return state;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "dispatchTouchEvent  action:" + ev.getAction());
        boolean state = super.dispatchTouchEvent(ev);
        return state;
    }
}
