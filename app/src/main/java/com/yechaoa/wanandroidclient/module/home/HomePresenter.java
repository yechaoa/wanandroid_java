package com.yechaoa.wanandroidclient.module.home;

import com.yechaoa.wanandroidclient.bean.Article;
import com.yechaoa.wanandroidclient.bean.Banner;
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
 * Created by yechao on 2018/4/22.
 * Describe :
 */
public class HomePresenter implements HomeContract.IHomePresenter {

    private Subscription mSubscription = null;
    private HomeContract.IHomeView mIHomeView;

    HomePresenter(HomeContract.IHomeView homeView) {
        mIHomeView = homeView;
    }

    @Override
    public void subscribe() {
        //getBannerData();
        getArticleList();
    }

    @Override
    public void unSubscribe() {
        if (null != mSubscription && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void getBannerData() {

        //Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
        //Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作
        //Schedulers.newThread() 代表一个常规的新线程
        //AndroidSchedulers.mainThread() 代表Android的主线程

        mSubscription = RetrofitService.create(API.WAZApi.class)
                .getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Banner>() {

                    //执行结束
                    @Override
                    public void onCompleted() {
                        LogUtil.i("onCompleted");
                        mIHomeView.hideProgress();
                    }

                    //异常，onError与onCompleted只会触发其中一个
                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i("onError");
                        mIHomeView.hideProgress();
                        mIHomeView.showBannerError("加载失败");
                    }

                    //普通事件（相当于 onClick() / onEvent()）
                    @Override
                    public void onNext(Banner banner) {
                        LogUtil.i("onNext");
                        mIHomeView.showProgress();
                        mIHomeView.setBannerData(banner.data);
                    }
                });
    }

    /**
     * 第一次加载文章列表
     */
    @Override
    public void getArticleList() {

        mSubscription = RetrofitService.create(API.WAZApi.class)
                .getArticleList(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Article>() {

                    //执行结束
                    @Override
                    public void onCompleted() {
                        mIHomeView.hideProgress();
                    }

                    //异常，onError与onCompleted只会触发其中一个
                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(e.toString());
                        mIHomeView.hideProgress();
                        mIHomeView.showArticleError("加载失败");
                    }

                    //普通事件（相当于 onClick() / onEvent()）
                    @Override
                    public void onNext(Article article) {
                        mIHomeView.showProgress();
                        mIHomeView.setArticleData(article.data.datas);
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
                        mIHomeView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mIHomeView.hideProgress();
                        mIHomeView.showArticleErrorByMore("加载失败");
                    }

                    @Override
                    public void onNext(Article article) {
                        mIHomeView.showProgress();
                        mIHomeView.setArticleDataByMore(article.data.datas);
                    }
                });
    }

    /**
     * 收藏
     *
     * @param id 文章id
     */
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
                        mIHomeView.showCollectError("收藏失败" + e.toString());
                    }

                    @Override
                    public void onNext(Common common) {
                        if (-1 == common.errorCode)
                            mIHomeView.showCollectError(common.errorMsg);
                        else
                            mIHomeView.showCollectSuccess("收藏成功");
                    }
                });

    }

    /**
     * 取消收藏
     *
     * @param id 文章id
     */
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
                        mIHomeView.showUncollectError("取消收藏失败" + e.toString());
                    }

                    @Override
                    public void onNext(Common common) {
                        if (-1 == common.errorCode)
                            mIHomeView.showUncollectError(common.errorMsg);
                        else
                            mIHomeView.showUncollectSuccess("取消收藏成功");
                    }
                });
    }

}
