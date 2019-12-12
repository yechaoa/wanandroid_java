package com.yechaoa.wanandroidclient.module.collect;

import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseView;
import com.yechaoa.wanandroidclient.bean.Article;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/5/19.
 * Describe :
 */
public interface ICollectView extends BaseView {

    void setArticleData(BaseBean<Article> list);

    void showArticleError(String errorMessage);

    void setArticleDataByMore(BaseBean<Article> list);

    void showArticleErrorByMore(String errorMessage);

    void showUncollectSuccess(String successMessage);

    void showUncollectError(String errorMessage);

}
