package com.yechaoa.wanandroidclient.module.navi;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.adapter.NaviChildAdapter;
import com.yechaoa.wanandroidclient.base.DelayFragment;
import com.yechaoa.wanandroidclient.bean.Navi;
import com.yechaoa.yutils.ToastUtil;
import com.yechaoa.yutils.YUtils;

import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.TabView;

public class NaviFragment extends DelayFragment implements NaviContract.INaviView, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.tab_layout)
    VerticalTabLayout mTabLayout;
    @BindView(R.id.navi_recycler_view)
    RecyclerView mNaviRecyclerView;
    private NaviPresenter mNaviPresenter;
    private List<Navi.DataBean> mNaviList;
    private List<Navi.DataBean.ArticlesBean> mArticles;

    @Override
    protected int getLayoutId() {
        isReady = true;
        delayLoad();
        return R.layout.fragment_navi;
    }

    @Override
    protected void initView() {
        mNaviRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
//        mNaviRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
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
    public void setNaviData(List<Navi.DataBean> list) {
        mNaviList = list;

        mTabLayout.setTabAdapter((new TabAdapter() {
            @Override
            public int getCount() {
                return mNaviList.size();
            }

            @Override
            public TabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public TabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public TabView.TabTitle getTitle(int position) {
                return new TabView.TabTitle.Builder()
                        .setContent(mNaviList.get(position).name)
                        .setTextColor(0xFF2196F3, 0xFF757575)
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return 0;
            }
        }));

        mTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {

                ToastUtil.showToast(mNaviList.get(position).name);

                final List<Navi.DataBean.ArticlesBean> articles = mNaviList.get(position).articles;
                NaviChildAdapter naviChildAdapter = new NaviChildAdapter(R.layout.item_navi_list, articles);
                mNaviRecyclerView.setAdapter(naviChildAdapter);
                naviChildAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        ToastUtil.showToast(articles.get(position).title);
                    }
                });
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });

        //设置第一次加载数据
        mArticles = mNaviList.get(0).articles;
        NaviChildAdapter naviChildAdapter = new NaviChildAdapter(R.layout.item_navi_list, mArticles);
        mNaviRecyclerView.setAdapter(naviChildAdapter);
        naviChildAdapter.setOnItemClickListener(this);

    }

    @Override
    public void showNaviError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    private boolean isReady = false;

    @Override
    protected void delayLoad() {
        if (!isReady || !isVisible) {
            return;
        }
        mNaviPresenter = new NaviPresenter(this);
        mNaviPresenter.subscribe();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ToastUtil.showToast(mArticles.get(position).title);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mNaviPresenter) {
            mNaviPresenter.unSubscribe();
        }
    }
}
