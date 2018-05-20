package com.yechaoa.wanandroidclient.module.collect;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.adapter.ArticleAdapter;
import com.yechaoa.wanandroidclient.base.BaseActivity;
import com.yechaoa.wanandroidclient.bean.Article;
import com.yechaoa.wanandroidclient.module.article_detail.ArticleDetailActivity;
import com.yechaoa.yutils.ToastUtil;
import com.yechaoa.yutils.YUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CollectActivity extends BaseActivity implements CollectContract.ICollectView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.collect_recycler_view)
    RecyclerView mCollectRecyclerView;

    private CollectPresenter mCollectPresenter = null;
    private List<Article.DataBean.DataDetailBean> mArticles = new ArrayList<>();
    private ArticleAdapter mArticleAdapter;
    private int mCurrentCounter;//上一次加载的数量
    private int TOTAL_COUNTER = 20;//每一次加载的数量
    private int page = 0;//记录分页
    private int mPosition;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collect;
    }

    @Override
    protected void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mCollectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCollectRecyclerView.setHasFixedSize(true);
        mCollectPresenter = new CollectPresenter(this);
    }

    @Override
    protected void initData() {
        mCollectPresenter.subscribe();
    }

    @Override
    public void showProgress() {
        YUtils.showLoading(this, getResources().getString(R.string.loading));
    }

    @Override
    public void hideProgress() {
        YUtils.dismissLoading();
    }

    @Override
    public void setArticleData(List<Article.DataBean.DataDetailBean> list) {
        mArticles = list;
        if (list.size() == 0) {
            //空数据的情况下才显示setEmptyView，且RecyclerView要match_parent才显示全
            mArticleAdapter = new ArticleAdapter(R.layout.item_article_list, new ArrayList<Article.DataBean.DataDetailBean>());
            mCollectRecyclerView.setAdapter(mArticleAdapter);
            View view = getLayoutInflater().inflate(R.layout.layout_empty, mCollectRecyclerView, false);
            mArticleAdapter.setEmptyView(view);
        } else {
            mArticleAdapter = new ArticleAdapter(R.layout.item_article_list, list);
            mCollectRecyclerView.setAdapter(mArticleAdapter);
            mArticleAdapter.setType(true);
            mCurrentCounter = mArticles.size();
            mArticleAdapter.openLoadAnimation();
            mArticleAdapter.setOnItemClickListener(this);
            mArticleAdapter.setOnItemChildClickListener(this);
            mArticleAdapter.setOnLoadMoreListener(this, mCollectRecyclerView);
        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (0 != mArticles.size()) {
            Intent intent = new Intent(this, ArticleDetailActivity.class);
            intent.putExtra(ArticleDetailActivity.WEB_URL, mArticles.get(position).link);
            startActivity(intent);
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.article_favorite:
                mPosition = position;
                if (-1 < mArticles.get(position).originId)
                    mCollectPresenter.uncollect(mArticles.get(position).id, mArticles.get(position).originId);
                else
                    mCollectPresenter.uncollect(mArticles.get(position).id, -1);
                break;
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mCollectRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mCurrentCounter < TOTAL_COUNTER) {
                    //Data are all loaded.
                    mArticleAdapter.loadMoreEnd();
                } else {
                    page++;
                    mCollectPresenter.getArticleListByMore(page);
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
        ToastUtil.showToast(errorMessage);
        mArticleAdapter.loadMoreFail();
    }

    @Override
    public void showUncollectSuccess(String successMessage) {
        ToastUtil.showToast(successMessage);
        mArticles.get(mPosition).collect = false;
        mArticleAdapter.remove(mPosition);
        mArticleAdapter.notifyDataSetChanged();
        if (mArticles.size() == 0)
            mCollectPresenter.getArticleList();
    }

    @Override
    public void showUncollectError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mCollectPresenter) {
            mCollectPresenter.unSubscribe();
        }
    }

}
