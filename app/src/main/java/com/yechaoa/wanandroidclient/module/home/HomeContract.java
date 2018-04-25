package com.yechaoa.wanandroidclient.module.home;

import com.yechaoa.wanandroidclient.base.BasePresenter;
import com.yechaoa.wanandroidclient.base.BaseView;
import com.yechaoa.wanandroidclient.bean.Article;
import com.yechaoa.wanandroidclient.bean.Banner;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/22.
 * Describe :
 */
public interface HomeContract {

    interface IHomeView extends BaseView {

        void setBannerData(List<Banner.DataBean> list);

        void showBannerError(String errorMessage);

        void setArticleData(List<Article.DataBean.DataDetailBean> list);

        void showArticleError(String errorMessage);

        void setArticleDataByMore(List<Article.DataBean.DataDetailBean> list);

        void showArticleErrorByMore(String errorMessage);

    }

    interface IHomePresenter extends BasePresenter {

        void getBannerData();

        void getArticleList();

        void getArticleListByMore(int page);

    }

}
