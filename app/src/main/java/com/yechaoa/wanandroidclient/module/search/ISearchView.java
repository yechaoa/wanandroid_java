package com.yechaoa.wanandroidclient.module.search;

import com.yechaoa.wanandroidclient.base.BaseBean;
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
public interface ISearchView extends BaseView {

    void setArticleData(BaseBean<Article> list);

    void showArticleError(String errorMessage);

    void setArticleDataByMore(BaseBean<Article> list);

    void showArticleErrorByMore(String errorMessage);

    void showCollectSuccess(String successMessage);

    void showCollectError(String errorMessage);

    void showUncollectSuccess(String successMessage);

    void showUncollectError(String errorMessage);

}
