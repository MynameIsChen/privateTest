package com.chen.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chen.test.weight.AutoLinearLayoutManager;
import com.chen.test.R;
import com.chen.test.weight.ScrollInterceptScrollView;

/**
 * Created by chenxianglin on 2017/11/8.
 * Class note:
 */

public class ScrollActivity extends Activity {
    private RecyclerView mList;
    private ScrollInterceptScrollView mScrollView;
    private AutoLinearLayoutManager mManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        initView();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mManager = new AutoLinearLayoutManager(this);
        mList = this.findViewById(R.id.list);
        mScrollView = findViewById(R.id.scroll);
        mList.setLayoutManager(linearLayoutManager);
        mList.setNestedScrollingEnabled(false);
//        mScrollView.smoothScrollTo(0,0);
        mList.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scroll, parent, false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 50;
            }
        });
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        public ItemHolder(View itemView) {
            super(itemView);
        }
    }
}
