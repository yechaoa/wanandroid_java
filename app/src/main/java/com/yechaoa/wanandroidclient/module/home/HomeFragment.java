package com.yechaoa.wanandroidclient.module.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.adapter.ArticleAdapter;
import com.yechaoa.wanandroidclient.base.BaseFragment;
import com.yechaoa.wanandroidclient.bean.Article;
import com.yechaoa.wanandroidclient.bean.Banner;
import com.yechaoa.wanandroidclient.common.GlideImageLoader;
import com.yechaoa.yutils.LogUtil;
import com.yechaoa.yutils.ToastUtil;
import com.yechaoa.yutils.YUtils;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements HomeContract.IHomeView, OnBannerListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.home_recycler_view)
    RecyclerView mHomeRecyclerView;
    private HomePresenter mHomePresenter = null;
    private List<Banner.DataBean> mBanners;
    private List<Article.DataBean.DataDetailBean> mArticles = new ArrayList<>();
    private ArticleAdapter mArticleAdapter;
    private com.youth.banner.Banner mBanner;
    private int mCurrentCounter;//上一次加载的数量
    private int TOTAL_COUNTER = 20;//每一次加载的数量
    private int page = 0;//记录分页

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        mBanner = new com.youth.banner.Banner(mContext);

        mHomeRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mHomePresenter = new HomePresenter(this);

    }

    @Override
    protected void initData() {
        //订阅
        mHomePresenter.subscribe();
    }

    @Override
    public void showProgress() {
        YUtils.showLoading(getActivity(), "加载中");
        LogUtil.i("showProgress");
    }

    @Override
    public void hideProgress() {
        YUtils.dismissLoading();
        LogUtil.i("hideProgress");
    }

    @Override
    public void setBannerData(List<Banner.DataBean> list) {
        LogUtil.i("页面拿到的数据" + list.toString());

        mBanners = list;

        List<String> images = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            images.add(list.get(i).imagePath);
            titles.add(list.get(i).title);
        }

        //图片宽高比例是1.8，所以动态设置banner的高度
        ViewGroup.LayoutParams lp = mBanner.getLayoutParams();
        lp.height = (int) (YUtils.getScreenWidth() / 1.8);

        //因为是头布局的方式添加，所以不显示标题和指示器。。
        mBanner.setImages(images).setImageLoader(new GlideImageLoader()).start();

        //设置点击事件
        mBanner.setOnBannerListener(this);

    }

    @Override
    public void OnBannerClick(int position) {
        if (0 != mBanners.size()) {
            // TODO: 2018/4/22 处理点击事件
            LogUtil.i(mBanners.get(position).title);
            ToastUtil.showToast(mBanners.get(position).title);
        }
    }

    @Override
    public void showBannerError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void setArticleData(List<Article.DataBean.DataDetailBean> list) {

        mArticles = list;
        mCurrentCounter = mArticles.size();

        mArticleAdapter = new ArticleAdapter(R.layout.item_article_list, list);

        mArticleAdapter.addHeaderView(mBanner);

        //
        mHomePresenter.getBannerData();

        mHomeRecyclerView.setAdapter(mArticleAdapter);
        //开启加载动画
        mArticleAdapter.openLoadAnimation();
        //item点击
        mArticleAdapter.setOnItemClickListener(this);
        //item子view点击
        mArticleAdapter.setOnItemChildClickListener(this);
        //加载更多
        mArticleAdapter.setOnLoadMoreListener(this);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (0 != mArticles.size()) {
            // TODO: 2018/4/22 处理点击事件 下拉刷新上拉加载
            LogUtil.i(mArticles.get(position).title);
            ToastUtil.showToast(mArticles.get(position).title);
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.article_favorite:
                // TODO: 2018/4/24 账号收藏
                ToastUtil.showToast("收藏---" + mArticles.get(position).title);
                break;
        }
    }

    @Override
    public void onLoadMoreRequested() {

        mHomeRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mCurrentCounter < TOTAL_COUNTER) {
                    //Data are all loaded.
                    mArticleAdapter.loadMoreEnd();
                } else {
                    page++;
                    mHomePresenter.getArticleListByMore(page);
                }
            }

        }, 1000);
    }

    @Override
    public void showArticleError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void setArticleDataByMore(List<Article.DataBean.DataDetailBean> list) {
        mArticles.addAll(list);
        mCurrentCounter = list.size();
        mArticleAdapter.addData(list);
        //加载完成
        mArticleAdapter.loadMoreComplete();
    }

    @Override
    public void showArticleErrorByMore(String errorMessage) {
        ToastUtil.showToast("加载失败");
        mArticleAdapter.loadMoreFail();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //取消订阅
        if (null != mHomePresenter) {
            mHomePresenter.unSubscribe();
        }
    }

}
