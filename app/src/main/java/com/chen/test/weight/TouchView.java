package com.chen.test.weight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by chenxianglin on 2017/12/4.
 * Class note:
 */

public class TouchView extends android.support.v7.widget.AppCompatTextView {
    public static final String TAG = "TouchView";

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
