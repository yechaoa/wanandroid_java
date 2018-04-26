package com.yechaoa.wanandroidclient.module.navi;

import com.yechaoa.wanandroidclient.bean.Navi;
import com.yechaoa.wanandroidclient.bean.Tree;
import com.yechaoa.wanandroidclient.http.API;
import com.yechaoa.wanandroidclient.http.RetrofitService;
import com.yechaoa.wanandroidclient.module.tree.TreeContract;
import com.yechaoa.yutils.LogUtil;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/26.
 * Describe :
 */
public class NaviPresenter implements NaviContract.INaviPresenter {

    private Subscription mSubscription = null;
    private NaviContract.INaviView mINaviView;

    NaviPresenter(NaviContract.INaviView naviView) {
        mINaviView = naviView;
    }

    @Override
    public void subscribe() {
        getNaviList();
    }

    @Override
    public void unSubscribe() {
        if (null != mSubscription && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void getNaviList() {

        mSubscription = RetrofitService.create(API.WAZApi.class)
                .getNaviList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Navi>() {

                    @Override
                    public void onCompleted() {
                        mINaviView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(e.toString());
                        mINaviView.hideProgress();
                        mINaviView.showNaviError("加载失败");
                    }

                    @Override
                    public void onNext(Navi navi) {
                        mINaviView.showProgress();
                        mINaviView.setNaviData(navi.data);
                    }
                });
    }

}
