package com.yechaoa.wanandroidclient.module.home;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.adapter.ArticleAdapter;
import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseFragment;
import com.yechaoa.wanandroidclient.bean.Article;
import com.yechaoa.wanandroidclient.bean.Banner;
import com.yechaoa.wanandroidclient.common.GlideImageLoader;
import com.yechaoa.wanandroidclient.module.article_detail.ArticleDetailActivity;
import com.yechaoa.yutils.ToastUtil;
import com.yechaoa.yutils.YUtils;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView, OnBannerListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.home_recycler_view)
    RecyclerView mHomeRecyclerView;
    @BindView(R.id.banner)
    com.youth.banner.Banner mBanner;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    private List<Banner> mBanners;
    private List<Article.DataDetailBean> mArticles = new ArrayList<>();
    private ArticleAdapter mArticleAdapter;
    private int mCurrentCounter;//上一次加载的数量
    private int TOTAL_COUNTER = 20;//每一次加载的数量
    private int page = 0;//记录分页
    private int mPosition;

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        mHomeRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getBannerData();
        presenter.getArticleList();
    }

    @Override
    public void setBannerData(BaseBean<List<Banner>> list) {
        mBanners = list.data;

        List<String> images = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < list.data.size(); i++) {
            images.add(list.data.get(i).imagePath);
            titles.add(list.data.get(i).title);
        }

        //图片宽高比例是1.8，所以动态设置banner的高度
        ViewGroup.LayoutParams lp = mAppBar.getLayoutParams();
        lp.height = (int) (YUtils.getScreenWidth() / 1.8);
        ViewGroup.LayoutParams lp1 = mBanner.getLayoutParams();
        lp1.height = (int) (YUtils.getScreenWidth() / 1.8);

        mBanner.setImages(images).setBannerTitles(titles).setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE).setImageLoader(new GlideImageLoader()).start();
        mBanner.setOnBannerListener(this);
    }

    @Override
    public void showBannerError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void setArticleData(BaseBean<Article> list) {
        mArticles = list.data.datas;
        mCurrentCounter = mArticles.size();
        mArticleAdapter = new ArticleAdapter(R.layout.item_article_list, list.data.datas);
        mHomeRecyclerView.setAdapter(mArticleAdapter);
        mArticleAdapter.openLoadAnimation();
        mArticleAdapter.setOnItemClickListener(this);
        mArticleAdapter.setOnItemChildClickListener(this);
        mArticleAdapter.setOnLoadMoreListener(this, mHomeRecyclerView);
    }

    @Override
    public void OnBannerClick(int position) {
        if (0 != mBanners.size()) {
            Intent intent = new Intent(mContext, ArticleDetailActivity.class);
            intent.putExtra(ArticleDetailActivity.WEB_URL, mBanners.get(position).url);
            intent.putExtra(ArticleDetailActivity.WEB_TITLE, mArticles.get(position).title);
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (0 != mArticles.size()) {
            Intent intent = new Intent(mContext, ArticleDetailActivity.class);
            intent.putExtra(ArticleDetailActivity.WEB_URL, mArticles.get(position).link);
            intent.putExtra(ArticleDetailActivity.WEB_TITLE, mArticles.get(position).title);
            startActivity(intent);
//            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(),view,ArticleDetailActivity.WEB_TITLE).toBundle());
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.article_favorite) {
            mPosition = position;
            if (mArticles.get(position).collect)
                presenter.uncollect(mArticles.get(position).id);
            else
                presenter.collect(mArticles.get(position).id);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mHomeRecyclerView.postDelayed(() -> {
            if (mCurrentCounter < TOTAL_COUNTER) {
                //Data are all loaded.
                mArticleAdapter.loadMoreEnd();
            } else {
                page++;
                presenter.getArticleListByMore(page);
            }
        }, 1000);
    }

    @Override
    public void showArticleError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void setArticleDataByMore(BaseBean<Article> list) {
        mArticles.addAll(list.data.datas);
        mCurrentCounter = list.data.datas.size();
        mArticleAdapter.addData(list.data.datas);
        //加载完成
        mArticleAdapter.loadMoreComplete();
    }

    @Override
    public void showArticleErrorByMore(String errorMessage) {
        ToastUtil.showToast(errorMessage);
        mArticleAdapter.loadMoreFail();
    }

    @Override
    public void showCollectSuccess(String successMessage) {
        ToastUtil.showToast(successMessage);
        mArticles.get(mPosition).collect = true;
        mArticleAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCollectError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void showUncollectSuccess(String successMessage) {
        ToastUtil.showToast(successMessage);
        mArticles.get(mPosition).collect = false;
        mArticleAdapter.notifyDataSetChanged();
    }

    @Override
    public void showUncollectError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }

}
