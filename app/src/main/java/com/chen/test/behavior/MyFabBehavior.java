package com.chen.test.behavior;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

import com.chen.common.util.Lg;

/**
 * Created by chenxianglin on 2017/12/11.
 * Class note:
 */

public class MyFabBehavior extends CoordinatorLayout.Behavior<View> {
    private static final String TAG = "MyFabBehavior";
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    private float viewY;//控件距离coordinatorLayout底部距离
    private float top;
    private boolean isAnimate;//动画是否在进行

    public MyFabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //在嵌套滑动开始前回调
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes, int type) {

        Lg.d(TAG, "onStartNestedScroll: nestedScrollAxes=" + nestedScrollAxes + "=type=" + type);
        if (child.getVisibility() == View.VISIBLE && viewY == 0) {
            //获取控件距离父布局（coordinatorLayout）底部距离
            viewY = coordinatorLayout.getHeight() - child.getY();
        }

        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;//判断是否竖直滚动
    }

    //在嵌套滑动进行时，对象消费滚动距离前回调
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed, int type) {
        //dy大于0是向上滚动 小于0是向下滚动
        Lg.d(TAG, "onNestedPreScroll: dy=" + dy + "=dx=" + dx + "=type=" + type);
//        if (dy >= 0 && !isAnimate && child.getVisibility() == View.VISIBLE) {
//            hide(child);
//        } else if (dy < 0 && !isAnimate && child.getVisibility() == View.GONE) {
//            show(child);
//        }
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float translationY = dependency.getTop();//获取更随布局的顶部位置
        if (translationY - top > 0 && !isAnimate && child.getVisibility() == View.GONE) {
            //向上
            show(child);
        } else if (translationY - top < 0 && !isAnimate && child.getVisibility() == View.VISIBLE) {
            //向下
            hide(child);
        }
        Lg.d(TAG, "onDependentViewChanged: y=" + translationY + "=ScrollY=" + dependency.getScrollY()
                + "=Y=" + dependency.getY() + "=TranslationY=" + dependency.getTranslationY() + "=PivotY=" + dependency.getPivotY()
                + "=RotationY=" + dependency.getRotationY());
//
//        child.setTranslationY(translationY);
        top = translationY;
        return true;
    }

    //隐藏时的动画
    private void hide(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(viewY).setInterpolator(INTERPOLATOR).setDuration(200);

        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.GONE);
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                show(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animator.start();
    }

    //显示时的动画
    private void show(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(0).setInterpolator(INTERPOLATOR).setDuration(200);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                view.setVisibility(View.VISIBLE);
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                hide(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animator.start();
    }
}
