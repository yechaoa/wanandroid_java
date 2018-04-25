package com.yechaoa.wanandroidclient.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yechaoa.yutils.ActivityUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/22.
 * Describe :
 */
public abstract class BaseFragment extends Fragment {

    Unbinder unbinder;
    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutId(), container, false);

        unbinder = ButterKnife.bind(this, view);

        mContext = ActivityUtil.getCurrentActivity();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        initView();

        initData();
    }

    @Override
    public void onResume() {
        super.onResume();

        initListener();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //do something
        unbinder.unbind();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected void initListener() {
    }

}
