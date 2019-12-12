package com.yechaoa.wanandroidclient.module.project;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;

import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.adapter.CommonViewPagerAdapter;
import com.yechaoa.wanandroidclient.base.DelayFragment;
import com.yechaoa.wanandroidclient.bean.Project;
import com.yechaoa.wanandroidclient.module.project.project_child.ProjectChildFragment;
import com.yechaoa.yutils.ToastUtil;
import com.yechaoa.yutils.YUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ProjectFragment extends DelayFragment implements ProjectContract.IProjectView {

    @BindView(R.id.project_tab_layout)
    TabLayout mProjectTabLayout;
    @BindView(R.id.project_view_pager)
    ViewPager mProjectViewPager;
    private ProjectPresenter mProjectPresenter;

    @Override
    protected int getLayoutId() {
        isReady = true;
        delayLoad();
        return R.layout.fragment_project;
    }

    @Override
    protected void initView() {
        //关联viewpager
        mProjectTabLayout.setupWithViewPager(mProjectViewPager);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showProgress() {
        YUtils.showLoading(getActivity(), getResources().getString(R.string.loading));
    }

    @Override
    public void hideProgress() {
        YUtils.dismissLoading();
    }

    @Override
    public void setProjectData(List<Project.DataBean> list) {
        //获取title的数据集
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            titles.add(list.get(i).name);
        }
        CommonViewPagerAdapter commonViewPagerAdapter = new CommonViewPagerAdapter(getFragmentManager(), titles);
        //动态创建fragment
        for (int i = 0; i < titles.size(); i++) {
            commonViewPagerAdapter.addFragment(ProjectChildFragment.newInstance(list.get(i).id));
        }
        mProjectViewPager.setAdapter(commonViewPagerAdapter);
        mProjectViewPager.setCurrentItem(0);
    }

    @Override
    public void showProjectError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    private boolean isReady = false;

    @Override
    protected void delayLoad() {
        if (!isReady || !isVisible) {
            return;
        }
        mProjectPresenter = new ProjectPresenter(this);
        mProjectPresenter.subscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mProjectPresenter) {
            mProjectPresenter.unSubscribe();
        }
    }

}
