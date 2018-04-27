package com.yechaoa.wanandroidclient.module.project.project_child;

import com.yechaoa.wanandroidclient.bean.ProjectChild;
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
 * Created by yechao on 2018/4/27.
 * Describe :
 */
public class ProjectChildPresenter implements ProjectChildContract.IProjectChildPresenter {

    private Subscription mSubscription = null;
    private ProjectChildContract.IProjectChildView mIProjectChildView;

    ProjectChildPresenter(ProjectChildContract.IProjectChildView projectChildView) {
        mIProjectChildView = projectChildView;
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
    public void getProjectChildList(int page, int cid) {

        mSubscription = RetrofitService.create(API.WAZApi.class)
                .getProjectChildList(page, cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectChild>() {

                    @Override
                    public void onCompleted() {
                        mIProjectChildView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(e.toString());
                        mIProjectChildView.hideProgress();
                        mIProjectChildView.showProjectChildError("加载失败");
                    }

                    @Override
                    public void onNext(ProjectChild project) {
                        mIProjectChildView.showProgress();
                        mIProjectChildView.setProjectChildData(project.data.datas);
                    }
                });
    }

}
