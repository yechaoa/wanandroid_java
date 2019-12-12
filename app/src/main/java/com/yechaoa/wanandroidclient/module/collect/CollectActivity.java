package com.yechaoa.wanandroidclient.module.collect;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.adapter.ArticleAdapter;
import com.yechaoa.wanandroidclient.base.BaseActivity;
import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.bean.Article;
import com.yechaoa.wanandroidclient.module.article_detail.ArticleDetailActivity;
import com.yechaoa.yutils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class CollectActivity extends BaseActivity<CollectPresenter> implements ICollectView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.collect_recycler_view)
    RecyclerView mCollectRecyclerView;

    private List<Article.DataDetailBean> mArticles = new ArrayList<>();
    private ArticleAdapter mArticleAdapter;
    private int mCurrentCounter;//上一次加载的数量
    private int TOTAL_COUNTER = 20;//每一次加载的数量
    private int page = 0;//记录分页
    private int mPosition;

    @Override
    protected CollectPresenter createPresenter() {
        return new CollectPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collect;
    }

    @Override
    protected void initView() {
        if (null != getSupportActionBar()) {
            getSupportActionBar().setTitle("我的收藏");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        mCollectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCollectRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void initData() {
        presenter.getArticleList();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (0 != mArticles.size()) {
            Intent intent = new Intent(this, ArticleDetailActivity.class);
            intent.putExtra(ArticleDetailActivity.WEB_URL, mArticles.get(position).link);
            intent.putExtra(ArticleDetailActivity.WEB_TITLE, mArticles.get(position).title);
            startActivity(intent);
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.article_favorite) {
            mPosition = position;
            if (-1 < mArticles.get(position).originId)
                presenter.uncollect(mArticles.get(position).id, mArticles.get(position).originId);
            else
                presenter.uncollect(mArticles.get(position).id, -1);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mCollectRecyclerView.postDelayed(() -> {
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
    public void setArticleData(BaseBean<Article> list) {
        mArticles = list.data.datas;
        if (list.data.datas.size() == 0) {
            //空数据的情况下才显示setEmptyView，且RecyclerView要match_parent才显示全
            mArticleAdapter = new ArticleAdapter(R.layout.item_article_list, new ArrayList<Article.DataDetailBean>());
            mCollectRecyclerView.setAdapter(mArticleAdapter);
            View view = getLayoutInflater().inflate(R.layout.layout_empty, mCollectRecyclerView, false);
            mArticleAdapter.setEmptyView(view);
        } else {
            mArticleAdapter = new ArticleAdapter(R.layout.item_article_list, list.data.datas);
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
    public void showUncollectSuccess(String successMessage) {
        ToastUtil.showToast(successMessage);
        mArticles.get(mPosition).collect = false;
        mArticleAdapter.remove(mPosition);
        mArticleAdapter.notifyDataSetChanged();
        if (mArticles.size() == 0)
            presenter.getArticleList();
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

}
