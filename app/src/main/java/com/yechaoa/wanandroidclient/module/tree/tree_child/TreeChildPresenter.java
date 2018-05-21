package com.yechaoa.wanandroidclient.module.tree.tree_child;

import com.yechaoa.wanandroidclient.bean.Common;
import com.yechaoa.wanandroidclient.bean.TreeChild;
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
 * Created by yechao on 2018/4/30.
 * Describe :
 */
public class TreeChildPresenter implements TreeChildContract.ITreeChildPresenter {

    private Subscription mSubscription = null;
    private TreeChildContract.ITreeChildView mITreeChildView;

    TreeChildPresenter(TreeChildContract.ITreeChildView treeChildView) {
        mITreeChildView = treeChildView;
    }

    @Override
    public void subscribe() {
        //getProjectChildList();
    }

    @Override
    public void unSubscribe() {
        if (null != mSubscription && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void getTreeChildList(int page, int cid) {

        mSubscription = RetrofitService.create(API.WAZApi.class)
                .getTreeChildList(page, cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TreeChild>() {

                    @Override
                    public void onCompleted() {
                        mITreeChildView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(e.toString());
                        mITreeChildView.hideProgress();
                        mITreeChildView.showTreeChildError("加载失败(°∀°)ﾉ");
                    }

                    @Override
                    public void onNext(TreeChild treeChild) {
                        mITreeChildView.showProgress();
                        mITreeChildView.setTreeChildData(treeChild.data.datas);
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
                        mITreeChildView.showCollectError("收藏失败(°∀°)ﾉ" + e.toString());
                    }

                    @Override
                    public void onNext(Common common) {
                        if (-1 == common.errorCode)
                            mITreeChildView.showCollectError(common.errorMsg);
                        else
                            mITreeChildView.showCollectSuccess("收藏成功（￣▽￣）");
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
                        mITreeChildView.showUncollectError("取消收藏失败(°∀°)ﾉ" + e.toString());
                    }

                    @Override
                    public void onNext(Common common) {
                        if (-1 == common.errorCode)
                            mITreeChildView.showUncollectError(common.errorMsg);
                        else
                            mITreeChildView.showUncollectSuccess("取消收藏成功（￣▽￣）");
                    }
                });
    }

}
