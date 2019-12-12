package com.yechaoa.wanandroidclient.module.search;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.adapter.ArticleAdapter;
import com.yechaoa.wanandroidclient.base.BaseActivity;
import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.bean.Article;
import com.yechaoa.wanandroidclient.module.article_detail.ArticleDetailActivity;
import com.yechaoa.yutils.LogUtil;
import com.yechaoa.yutils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class SearchActivity extends BaseActivity<SearchPresenter> implements ISearchView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.search_recycler_view)
    RecyclerView mSearchRecyclerView;

    private List<Article.DataDetailBean> mArticles = new ArrayList<>();
    private ArticleAdapter mArticleAdapter;
    private int mCurrentCounter;//上一次加载的数量
    private int TOTAL_COUNTER = 20;//每一次加载的数量
    private int page = 0;//记录分页
    private int mPosition;
    private String key = "";
    private SearchView mSearchView;

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        if (null != getSupportActionBar()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    protected void initData() {
        mSearchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSearchRecyclerView.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        //找到SearchView并配置相关参数
        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //设置展开搜索框并获取焦点
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setFocusable(true);
        mSearchView.setIconified(false);
        mSearchView.requestFocusFromTouch();
        mSearchView.setQueryHint("请输入关键字(~^O^~)");
        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                key = query;
                presenter.getSearchArticleList(key);
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                //do something
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_settings:

                break;
            case R.id.action_search:
                LogUtil.i("搜索");
                break;
        }
        return super.onOptionsItemSelected(item);
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
        switch (view.getId()) {
            case R.id.article_favorite:
                mPosition = position;
                if (mArticles.get(position).collect)
                    presenter.uncollect(mArticles.get(position).id);
                else
                    presenter.collect(mArticles.get(position).id);
                break;
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mSearchRecyclerView.postDelayed(() -> {
            if (mCurrentCounter < TOTAL_COUNTER) {
                //Data are all loaded.
                mArticleAdapter.loadMoreEnd();
            } else {
                page++;
                presenter.getSearchArticleListByMore(page, key);
            }
        }, 1000);
    }

    @Override
    public void setArticleData(BaseBean<Article> list) {
        if (list.data.datas.size() == 0) {
            //空数据的情况下才显示setEmptyView，且RecyclerView要match_parent才显示全
            mArticleAdapter = new ArticleAdapter(R.layout.item_article_list, new ArrayList<Article.DataDetailBean>());
            mSearchRecyclerView.setAdapter(mArticleAdapter);
            View view = getLayoutInflater().inflate(R.layout.layout_empty, mSearchRecyclerView, false);
            TextView emptyText = view.findViewById(R.id.empty_text);
            emptyText.setText("大胸弟，换个关键词试试(･∀･)");
            mArticleAdapter.setEmptyView(view);
        } else {
            mArticles = list.data.datas;
            mArticleAdapter = new ArticleAdapter(R.layout.item_article_list, list.data.datas);
            mSearchRecyclerView.setAdapter(mArticleAdapter);
            mArticleAdapter.setType(false);
            mCurrentCounter = mArticles.size();
            mArticleAdapter.openLoadAnimation();
            mArticleAdapter.setOnItemClickListener(this);
            mArticleAdapter.setOnItemChildClickListener(this);
            mArticleAdapter.setOnLoadMoreListener(this, mSearchRecyclerView);
        }
        //收起软键盘
        mSearchView.clearFocus();
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
        mArticleAdapter.remove(mPosition);
        mArticleAdapter.notifyDataSetChanged();
        if (mArticles.size() == 0)
            presenter.getSearchArticleList(key);
    }

    @Override
    public void showUncollectError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

}
