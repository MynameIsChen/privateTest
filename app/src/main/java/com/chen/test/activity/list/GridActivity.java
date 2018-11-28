package com.chen.test.activity.list;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.chen.test.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenxianglin on 2017/11/27.
 * Class note:
 */

public class GridActivity extends Activity {
    @BindView(R.id.list)
    RecyclerView mList;

    private GridLayoutManager mGridLayoutManager;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private ListAdapter mAdapter;
    private List<String> list = new ArrayList<>();
    private boolean stagger = true;

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, GridActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        getData();
        initView();
    }

    private void initView() {
        mAdapter = new ListAdapter(this);
        mGridLayoutManager = new GridLayoutManager(this, 12);
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position >= 0 && position <= 2) {
                    return 4;
                } else if (position > 2 && position <= 6) {
                    return 3;
                } else if (position > 6 && position <= 12) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });

        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mList.setAdapter(mAdapter);
        mList.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setListener(new OnItemListener() {
            @Override
            public void onItem(int position) {
                Toast.makeText(GridActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
            }
        });
        changeState();
    }

    private void changeState() {
        if (stagger) {
            mList.setLayoutManager(mGridLayoutManager);
        } else {
            mList.addItemDecoration(new SpaceItemDecoration(4));
            mList.setLayoutManager(mStaggeredGridLayoutManager);
        }
        mAdapter.setStagger(stagger);
        mAdapter.setData(list);
    }

    @OnClick({R.id.change})
    public void onItemClick(View v) {
        if (v.getId() == R.id.change) {
            stagger = !stagger;
            changeState();
        }
    }

    private void getData() {
        for (int i = 0; i < 24; i++) {
            list.add("title" + i);
        }
    }

    class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int spanIndex;
            int spanCount;

            if (!stagger) {
                StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
                spanIndex = lp.getSpanIndex();
                StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
                spanCount = layoutManager.getSpanCount();
            } else {
                GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                spanIndex = lp.getSpanIndex();
                GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
                spanCount = layoutManager.getSpanCount();
            }

            outRect.left = 0;
            outRect.bottom = 0;
            if (spanIndex == 0) {
                outRect.right = space;
            }
            if (position > 1) {
                outRect.top = space;
            }
        }
    }
}
