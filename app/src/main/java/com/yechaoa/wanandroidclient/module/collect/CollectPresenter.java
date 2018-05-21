package com.yechaoa.wanandroidclient.module.collect;

import com.yechaoa.wanandroidclient.bean.Article;
import com.yechaoa.wanandroidclient.bean.Common;
import com.yechaoa.wanandroidclient.http.API;
import com.yechaoa.wanandroidclient.http.RetrofitService;
import com.yechaoa.yutils.LogUtil;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/5/19.
 * Describe :
 */
public class CollectPresenter implements CollectContract.ICollectPresenter {

    private Subscription mSubscription = null;
    private CollectContract.ICollectView mICollectView;

    CollectPresenter(CollectContract.ICollectView collectView) {
        mICollectView = collectView;
    }

    @Override
    public void subscribe() {
        getArticleList();
    }

    @Override
    public void unSubscribe() {
        if (null != mSubscription && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void getArticleList() {

        mSubscription = RetrofitService.create(API.WAZApi.class)
                .getCollectList(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Article>() {

                    @Override
                    public void onCompleted() {
                        mICollectView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(e.toString());
                        mICollectView.hideProgress();
                        mICollectView.showArticleError("加载失败(°∀°)ﾉ");
                    }

                    @Override
                    public void onNext(Article article) {
                        mICollectView.showProgress();
                        if (-1 == article.errorCode)
                            mICollectView.showArticleError(article.errorMsg);
                        else
                            mICollectView.setArticleData(article.data.datas);
                    }
                });
    }

    /**
     * 加载更多
     *
     * @param page 分页参数
     */
    @Override
    public void getArticleListByMore(int page) {

        mSubscription = RetrofitService.create(API.WAZApi.class)
                .getArticleList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Article>() {

                    @Override
                    public void onCompleted() {
                        mICollectView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mICollectView.hideProgress();
                        mICollectView.showArticleErrorByMore("加载失败(°∀°)ﾉ");
                    }

                    @Override
                    public void onNext(Article article) {
                        mICollectView.showProgress();
                        mICollectView.setArticleDataByMore(article.data.datas);
                    }
                });
    }

    @Override
    public void uncollect(int id, int originId) {

        mSubscription = RetrofitService.create(API.WAZApi.class)
                .uncollect1(id, originId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Common>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mICollectView.showUncollectError("取消收藏失败(°∀°)ﾉ" + e.toString());
                    }

                    @Override
                    public void onNext(Common common) {
                        if (-1 == common.errorCode)
                            mICollectView.showUncollectError(common.errorMsg);
                        else
                            mICollectView.showUncollectSuccess("取消收藏成功（￣▽￣）");
                    }
                });

    }

}
