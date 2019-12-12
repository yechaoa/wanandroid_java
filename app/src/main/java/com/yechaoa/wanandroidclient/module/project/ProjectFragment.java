package com.yechaoa.wanandroidclient.module.project;

import com.google.android.material.tabs.TabLayout;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.adapter.CommonViewPagerAdapter;
import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseFragment;
import com.yechaoa.wanandroidclient.bean.Project;
import com.yechaoa.wanandroidclient.module.project.project_child.ProjectChildFragment;
import com.yechaoa.yutils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;


public class ProjectFragment extends BaseFragment<ProjectPresenter> implements IProjectView {

    @BindView(R.id.project_tab_layout)
    TabLayout mProjectTabLayout;
    @BindView(R.id.project_view_pager)
    ViewPager mProjectViewPager;

    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initView() {
        //关联viewpager
        mProjectTabLayout.setupWithViewPager(mProjectViewPager);
    }

    @Override
    protected void initData() {
        presenter.getProjectList();
    }

    @Override
    public void setProjectData(BaseBean<List<Project>> list) {
        //获取title的数据集
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < list.data.size(); i++) {
            titles.add(list.data.get(i).name);
        }
        CommonViewPagerAdapter commonViewPagerAdapter = new CommonViewPagerAdapter(getFragmentManager(), titles);
        //动态创建fragment
        for (int i = 0; i < titles.size(); i++) {
            commonViewPagerAdapter.addFragment(ProjectChildFragment.newInstance(list.data.get(i).id));
        }
        mProjectViewPager.setAdapter(commonViewPagerAdapter);
        mProjectViewPager.setCurrentItem(0);
    }

    @Override
    public void showProjectError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

}
