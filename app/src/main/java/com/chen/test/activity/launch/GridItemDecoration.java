package com.chen.test.activity.launch;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chen.common.util.UIUtils;

/**
 * Created by chenxianglin on 2018/5/24.
 * Class note:
 */

public class GridItemDecoration extends RecyclerView.ItemDecoration {
    public GridItemDecoration() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //item 65*30
        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        int spanIndex = lp.getSpanIndex();
        int position = parent.getChildAdapterPosition(view);
        int spanCount = layoutManager.getSpanCount();

        int h = (int) UIUtils.dpToPx(parent.getContext(), 10);
        float item = UIUtils.dpToPx(parent.getContext(), 65);
        float sw = UIUtils.getScreenWidth(parent.getContext()) - (item * spanCount);
        int w = (int) (sw / (spanCount + 1));

        outRect.left = w;
        if (position == 3) {
            outRect.right = w;
        }
        if (position / spanCount == 0) {
            outRect.top = h;
        }
        outRect.bottom = h;

        //判断总的数量是否可以整除
//            int totalCount = layoutManager.getItemCount();
//            int surplusCount = totalCount % layoutManager.getSpanCount();
//            int childPosition = parent.getChildAdapterPosition(view);
//            if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {//竖直方向的
//                if (surplusCount == 0 && childPosition > totalCount - layoutManager.getSpanCount() - 1) {
//                    //后面几项需要bottom
//                    outRect.bottom = height;
//                } else if (surplusCount != 0 && childPosition > totalCount - surplusCount - 1) {
//                    outRect.bottom = height;
//                }
//                if ((childPosition + 1) % layoutManager.getSpanCount() == 0) {//被整除的需要右边
//                    outRect.right = width;
//                }
//                outRect.top = height;
//                outRect.left = width;
//            } else {
//                if (surplusCount == 0 && childPosition > totalCount - layoutManager.getSpanCount() - 1) {
//                    //后面几项需要右边
//                    outRect.right = width;
//                } else if (surplusCount != 0 && childPosition > totalCount - surplusCount - 1) {
//                    outRect.right = width;
//                }
//                if ((childPosition + 1) % layoutManager.getSpanCount() == 0) {//被整除的需要下边
//                    outRect.bottom = height;
//                }
//                outRect.top = height;
//                outRect.left = width;
//            }

    }
}
