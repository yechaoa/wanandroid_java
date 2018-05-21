package com.yechaoa.wanandroidclient.module.tree;

import com.yechaoa.wanandroidclient.bean.Tree;
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
 * Created by yechao on 2018/4/25.
 * Describe :
 */
public class TreePresenter implements TreeContract.ITreePresenter {

    private Subscription mSubscription = null;
    private TreeContract.ITreeView mITreeView;

    TreePresenter(TreeContract.ITreeView treeView) {
        mITreeView = treeView;
    }

    @Override
    public void subscribe() {
        getTreeList();
    }

    @Override
    public void unSubscribe() {
        if (null != mSubscription && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void getTreeList() {

        mSubscription = RetrofitService.create(API.WAZApi.class)
                .getTreeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Tree>() {

                    @Override
                    public void onCompleted() {
                        mITreeView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(e.toString());
                        mITreeView.hideProgress();
                        mITreeView.showTreeError("加载失败(°∀°)ﾉ");
                    }

                    @Override
                    public void onNext(Tree tree) {
                        mITreeView.showProgress();
                        mITreeView.setTreeData(tree.data);
                    }
                });
    }

}
