package com.yechaoa.wanandroidclient.module.search;

import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseObserver;
import com.yechaoa.wanandroidclient.base.BasePresenter;
import com.yechaoa.wanandroidclient.bean.Article;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/5/31.
 * Describe :
 */
public class SearchPresenter extends BasePresenter<ISearchView> {


    SearchPresenter(ISearchView searchView) {
        super(searchView);
    }

    public void getSearchArticleList(String key) {
        addDisposable(apiServer.search(0, key), new BaseObserver<BaseBean<Article>>(baseView) {
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

    public void getSearchArticleListByMore(int page, String key) {
        addDisposable(apiServer.search(page, key), new BaseObserver<BaseBean<Article>>(baseView) {
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

    public void collect(int id) {
        addDisposable(apiServer.collectIn(id), new BaseObserver<BaseBean>(baseView) {
            @Override
            public void onSuccess(BaseBean bean) {
                baseView.showCollectSuccess("收藏成功（￣▽￣）");
            }

            @Override
            public void onError(String msg) {
                baseView.showCollectError(msg + "(°∀°)ﾉ");
            }
        });
    }

    public void uncollect(int id) {
        addDisposable(apiServer.uncollect(id), new BaseObserver<BaseBean>(baseView) {
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
