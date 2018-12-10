package com.chen.test.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.chen.common.util.Lg;
import com.chen.common.util.UIUtils;
import com.chen.test.R;

/**
 * Description:
 * Author:Chenxianglin
 * Date:2018/12/6下午2:17
 */
public class WebListBehavior extends CoordinatorLayout.Behavior<View> {
    private static final String TAG = "WebListBehavior";
    private int sinceDirectionChange;

    public WebListBehavior() {
    }

    public WebListBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Lg.d(TAG, "onDependentViewChanged: y=" + dependency.getTop() + "=ScrollY=" + dependency.getScrollY()
                + "=Y=" + dependency.getY() + "=TranslationY=" + dependency.getTranslationY() + "=PivotY=" + dependency.getPivotY()
                + "=RotationY=" + dependency.getRotationY());
        TextView headView = parent.findViewById(R.id.head);
        TextView backView = parent.findViewById(R.id.back);
        TextView addView = parent.findViewById(R.id.add);
        SwipeRefreshLayout refreshLayout = parent.findViewById(R.id.swipe_refresh);
        int h = (int) UIUtils.dpToPx(parent.getContext(), 150);

        if (h == -dependency.getTop()) {
            parent.setLayoutTransition(null);//控制view变化的动画没有
            headView.setVisibility(View.GONE);
            backView.setVisibility(View.VISIBLE);
            addView.setVisibility(View.VISIBLE);
            refreshLayout.setEnabled(true);
        }
        Lg.d(TAG, "headView: y=" + headView.getTop() + "=ScrollY=" + headView.getScrollY()
                + "=Y=" + headView.getY() + "=TranslationY=" + headView.getTranslationY() + "=PivotY=" + headView.getPivotY()
                + "=RotationY=" + headView.getRotationY());
        return true;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        Lg.d(TAG, "onStartNestedScroll: nestedScrollAxes=" + axes + "=type=" + type);
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        Lg.d(TAG, "onNestedPreScroll: dy=" + dy + "=type=" + type);
    }

}
