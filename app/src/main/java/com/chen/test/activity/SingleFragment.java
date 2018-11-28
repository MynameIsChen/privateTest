package com.chen.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chen.test.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxianglin on 2018/5/17.
 * Class note:
 */

public class SingleFragment extends Fragment {
    @BindView(R.id.list)
    RecyclerView mList;

    private List<String> list = new ArrayList<>();
    private String mTitle;

    public SingleFragment() {

    }

    public static SingleFragment getInstance(String title) {
        SingleFragment fragment = new SingleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView() {
        for (int i = 0; i < 20; i++) {
            list.add("test" + i);
        }
        RecyclerView.Adapter adapter = new RecyclerView.Adapter<CoordinatorActivity.TestHolder>() {
            @Override
            public CoordinatorActivity.TestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new CoordinatorActivity.TestHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false));
            }

            @Override
            public void onBindViewHolder(CoordinatorActivity.TestHolder holder, int position) {
                holder.mText.setText(list.get(position));
            }

            @Override
            public int getItemCount() {
                return list.size();
            }
        };
        mList.setAdapter(adapter);
        mList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hi, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }
}
