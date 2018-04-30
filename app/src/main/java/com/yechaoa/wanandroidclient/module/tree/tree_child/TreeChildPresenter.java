package com.yechaoa.wanandroidclient.module.tree.tree_child;

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
                        mITreeChildView.showTreeChildError("加载失败");
                    }

                    @Override
                    public void onNext(TreeChild treeChild) {
                        mITreeChildView.showProgress();
                        mITreeChildView.setTreeChildData(treeChild.data.datas);
                    }
                });
    }

}
