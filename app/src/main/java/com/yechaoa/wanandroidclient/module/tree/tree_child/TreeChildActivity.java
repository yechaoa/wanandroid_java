package com.yechaoa.wanandroidclient.module.tree.tree_child;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import android.text.TextUtils;
import android.view.MenuItem;

import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.adapter.CommonViewPagerAdapter;
import com.yechaoa.wanandroidclient.base.BaseActivity;
import com.yechaoa.wanandroidclient.bean.Tree;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TreeChildActivity extends BaseActivity {

    public static final String CID = "com.yechaoa.wanandroidclient.module.tree.TreeChildActivity.CID";
    public static final String TITLE = "com.yechaoa.wanandroidclient.module.tree.TreeChildActivity.TITLE";

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

        if (null != getSupportActionBar()) {
            String title = getIntent().getStringExtra(TITLE);
            if (!TextUtils.isEmpty(title))
                getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

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
            commonViewPagerAdapter.addFragment(TreeChildFragment.newInstance(childList.get(i).id));
        }
        mTreeViewPager.setAdapter(commonViewPagerAdapter);
        mTreeViewPager.setCurrentItem(0);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
