package com.chen.test.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Description:
 * Author:Chenxianglin
 * Date:2018/12/6下午2:17
 */
public class TouchBehavior extends CoordinatorLayout.Behavior<Button> {
    private int sinceDirectionChange;

    public TouchBehavior() {
    }

    public TouchBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Button child, View dependency) {
        return dependency instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Button child, View dependency) {
        int top = dependency.getTop();
        int left = dependency.getLeft();

        int width = parent.getContext().getResources().getDisplayMetrics().widthPixels;
        int x = width - left - child.getWidth();
        int y = top;
        setPosition(child, x, y);
        return true;
    }

    //1.判断滑动的方向 我们需要垂直滑动
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Button child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    //2.根据滑动的距离显示和隐藏footer view
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Button child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        if (dy > 0 && sinceDirectionChange < 0 || dy < 0 && sinceDirectionChange > 0) {
            child.animate().cancel();
            sinceDirectionChange = 0;
        }
        sinceDirectionChange += dy;
        int visibility = child.getVisibility();
        if (sinceDirectionChange > child.getHeight() && visibility == View.VISIBLE) {
            child.setVisibility(View.GONE);
        } else {
            if (sinceDirectionChange < 0 && (visibility == View.GONE || visibility == View.INVISIBLE)) {
                child.setVisibility(View.VISIBLE);
            }
        }
    }

    private void setPosition(View v, int x, int y) {
        CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) v.getLayoutParams();
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y;
        v.setLayoutParams(layoutParams);
    }
}
