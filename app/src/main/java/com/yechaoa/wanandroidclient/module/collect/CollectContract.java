package com.yechaoa.wanandroidclient.module.collect;

import com.yechaoa.wanandroidclient.base.BasePresenter;
import com.yechaoa.wanandroidclient.base.BaseView;
import com.yechaoa.wanandroidclient.bean.Article;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/5/19.
 * Describe :
 */
public interface CollectContract {

    interface ICollectView extends BaseView {

        void setArticleData(List<Article.DataBean.DataDetailBean> list);

        void showArticleError(String errorMessage);

        void setArticleDataByMore(List<Article.DataBean.DataDetailBean> list);

        void showArticleErrorByMore(String errorMessage);

        void showUncollectSuccess(String successMessage);

        void showUncollectError(String errorMessage);

    }

    interface ICollectPresenter extends BasePresenter {

        void getArticleList();

        void getArticleListByMore(int page);

        void uncollect(int id, int originId);

    }

}
