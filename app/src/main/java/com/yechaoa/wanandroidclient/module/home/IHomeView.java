package com.yechaoa.wanandroidclient.module.home;

import com.yechaoa.wanandroidclient.base.BaseBean;
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
public interface IHomeView extends BaseView {

    void setBannerData(BaseBean<List<Banner>> list);

    void showBannerError(String errorMessage);

    void setArticleData(BaseBean<Article> list);

    void showArticleError(String errorMessage);

    void setArticleDataByMore(BaseBean<Article> list);

    void showArticleErrorByMore(String errorMessage);

    void showCollectSuccess(String successMessage);

    void showCollectError(String errorMessage);

    void showUncollectSuccess(String successMessage);

    void showUncollectError(String errorMessage);

}
