package com.yechaoa.wanandroidclient.module.collect;

import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseObserver;
import com.yechaoa.wanandroidclient.base.BasePresenter;
import com.yechaoa.wanandroidclient.bean.Article;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/5/19.
 * Describe :
 */
public class CollectPresenter extends BasePresenter<ICollectView> {


    CollectPresenter(ICollectView collectView) {
        super(collectView);
    }

    public void getArticleList() {
        addDisposable(apiServer.getCollectList(0), new BaseObserver<BaseBean<Article>>(baseView) {
            @Override
            public void onSuccess(BaseBean<Article> bean) {
                baseView.setArticleData(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.showArticleError(msg + "(°∀°)ﾉ");
            }
        });
    }

    /**
     * 加载更多
     *
     * @param page 分页参数
     */
    public void getArticleListByMore(int page) {
        addDisposable(apiServer.getCollectList(page), new BaseObserver<BaseBean<Article>>(baseView) {
            @Override
            public void onSuccess(BaseBean<Article> bean) {
                baseView.setArticleDataByMore(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.showArticleErrorByMore(msg + "(°∀°)ﾉ");
            }
        });
    }

    public void uncollect(int id, int originId) {
        addDisposable(apiServer.uncollect1(id, originId), new BaseObserver<BaseBean>(baseView) {
            @Override
            public void onSuccess(BaseBean bean) {
                baseView.showUncollectSuccess("取消收藏成功（￣▽￣）");
            }

            @Override
            public void onError(String msg) {
                baseView.showUncollectError(msg + "(°∀°)ﾉ");
            }
        });
    }

}
