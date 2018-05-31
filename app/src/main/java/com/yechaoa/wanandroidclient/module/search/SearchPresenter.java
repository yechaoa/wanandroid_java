package com.yechaoa.wanandroidclient.module.search;

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
 * Created by yechao on 2018/5/31.
 * Describe :
 */
public class SearchPresenter implements SearchContract.ISearchPresenter {

    private Subscription mSubscription = null;
    private SearchContract.ISearchView mISearchView;

    SearchPresenter(SearchContract.ISearchView searchView) {
        mISearchView = searchView;
    }

    @Override
    public void subscribe() {
        // getArticleList();
    }

    @Override
    public void unSubscribe() {
        if (null != mSubscription && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void getSearchArticleList(String key) {

        mSubscription = RetrofitService.create(API.WAZApi.class)
                .search(0, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Article>() {

                    @Override
                    public void onCompleted() {
                        mISearchView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(e.toString());
                        mISearchView.hideProgress();
                        mISearchView.showArticleError("加载失败(°∀°)ﾉ");
                    }

                    @Override
                    public void onNext(Article article) {
                        mISearchView.showProgress();
                        if (-1 == article.errorCode)
                            mISearchView.showArticleError(article.errorMsg);
                        else
                            mISearchView.setArticleData(article.data.datas);
                    }
                });
    }

    @Override
    public void getSearchArticleListByMore(int page, String key) {

        mSubscription = RetrofitService.create(API.WAZApi.class)
                .search(page, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Article>() {

                    @Override
                    public void onCompleted() {
                        mISearchView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mISearchView.hideProgress();
                        mISearchView.showArticleErrorByMore("加载失败(°∀°)ﾉ");
                    }

                    @Override
                    public void onNext(Article article) {
                        mISearchView.showProgress();
                        mISearchView.setArticleDataByMore(article.data.datas);
                    }
                });
    }

    @Override
    public void collect(int id) {

        mSubscription = RetrofitService.create(API.WAZApi.class)
                .collectIn(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Common>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mISearchView.showCollectError("收藏失败(°∀°)ﾉ" + e.toString());
                    }

                    @Override
                    public void onNext(Common common) {
                        if (-1 == common.errorCode)
                            mISearchView.showCollectError(common.errorMsg);
                        else
                            mISearchView.showCollectSuccess("收藏成功（￣▽￣）");
                    }
                });

    }

    @Override
    public void uncollect(int id) {
        mSubscription = RetrofitService.create(API.WAZApi.class)
                .uncollect(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Common>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mISearchView.showUncollectError("取消收藏失败(°∀°)ﾉ" + e.toString());
                    }

                    @Override
                    public void onNext(Common common) {
                        if (-1 == common.errorCode)
                            mISearchView.showUncollectError(common.errorMsg);
                        else
                            mISearchView.showUncollectSuccess("取消收藏成功（￣▽￣）");
                    }
                });
    }

}
