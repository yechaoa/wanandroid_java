package com.yechaoa.wanandroidclient.module.search;

import com.yechaoa.wanandroidclient.base.BasePresenter;
import com.yechaoa.wanandroidclient.base.BaseView;
import com.yechaoa.wanandroidclient.bean.Article;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/5/31.
 * Describe :
 */
public interface SearchContract {

    interface ISearchView extends BaseView {

        void setArticleData(List<Article.DataBean.DataDetailBean> list);

        void showArticleError(String errorMessage);

        void setArticleDataByMore(List<Article.DataBean.DataDetailBean> list);

        void showArticleErrorByMore(String errorMessage);

        void showCollectSuccess(String successMessage);

        void showCollectError(String errorMessage);

        void showUncollectSuccess(String successMessage);

        void showUncollectError(String errorMessage);

    }

    interface ISearchPresenter extends BasePresenter {

        void getSearchArticleList(String key);

        void getSearchArticleListByMore(int page, String key);

        void collect(int id);

        void uncollect(int id);
    }

}
