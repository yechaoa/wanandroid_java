package com.yechaoa.wanandroidclient.module.tree.tree_child;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.adapter.CommonViewPagerAdapter;
import com.yechaoa.wanandroidclient.base.BaseActivity;
import com.yechaoa.wanandroidclient.bean.Tree;
import com.yechaoa.yutils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TreeChildActivity extends BaseActivity {

    public static final String CID = "com.yechaoa.wanandroidclient.module.tree.TreeChildActivity.CID";

    @BindView(R.id.tree_tab_layout)
    TabLayout mTreeTabLayout;
    @BindView(R.id.tree_view_pager)
    ViewPager mTreeViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tree_child;
    }

    @Override
    protected void initView() {
        mTreeTabLayout.setupWithViewPager(mTreeViewPager);
    }

    @Override
    protected void initData() {

        List<Tree.DataBean.ChildrenBean> childList = (List<Tree.DataBean.ChildrenBean>) getIntent().getSerializableExtra(CID);

        //获取title的数据集
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < childList.size(); i++) {
            titles.add(childList.get(i).name);
        }
        CommonViewPagerAdapter commonViewPagerAdapter = new CommonViewPagerAdapter(getSupportFragmentManager(), titles);
        //动态创建fragment
        for (int i = 0; i < titles.size(); i++) {
            LogUtil.i("TreeChildActivity--" + childList.get(i).id);
            commonViewPagerAdapter.addFragment(TreeChildFragment.newInstance(childList.get(i).id));
        }
        mTreeViewPager.setAdapter(commonViewPagerAdapter);
        mTreeViewPager.setCurrentItem(0);

    }

}
