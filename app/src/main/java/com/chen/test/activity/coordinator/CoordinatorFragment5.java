package com.chen.test.activity.coordinator;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chen.test.R;
import com.chen.test.base.BaseFragment;
import com.chen.test.databinding.FragmentCoordinator5Binding;
import com.chen.test.holder.CoordinatorHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author:Chenxianglin
 * Date:2018/11/28上午11:48
 */
public class CoordinatorFragment5 extends BaseFragment {
    private FragmentCoordinator5Binding mBinding;


    public static CoordinatorFragment5 newInstance() {
        return new CoordinatorFragment5();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_coordinator5, null, false);
        return mBinding.getRoot();
    }

    @Override
    protected void initView() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("test" + i);
        }

        RecyclerView.Adapter adapter = new RecyclerView.Adapter<CoordinatorHolder>() {
            @Override
            public CoordinatorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new CoordinatorHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false));
            }

            @Override
            public void onBindViewHolder(CoordinatorHolder holder, int position) {
                holder.mText.setText(list.get(position));
            }

            @Override
            public int getItemCount() {
                return list.size();
            }
        };
        mBinding.list.setAdapter(adapter);
        mBinding.list.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}
