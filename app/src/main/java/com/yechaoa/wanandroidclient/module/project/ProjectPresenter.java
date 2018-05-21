package com.yechaoa.wanandroidclient.module.project;

import com.yechaoa.wanandroidclient.bean.Project;
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
public class ProjectPresenter implements ProjectContract.IProjectPresenter {

    private Subscription mSubscription = null;
    private ProjectContract.IProjectView mIProjectView;

    ProjectPresenter(ProjectContract.IProjectView projectView) {
        mIProjectView = projectView;
    }

    @Override
    public void subscribe() {
        getProjectList();
    }

    @Override
    public void unSubscribe() {
        if (null != mSubscription && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void getProjectList() {

        mSubscription = RetrofitService.create(API.WAZApi.class)
                .getProjectList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Project>() {

                    @Override
                    public void onCompleted() {
                        mIProjectView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(e.toString());
                        mIProjectView.hideProgress();
                        mIProjectView.showProjectError("加载失败(°∀°)ﾉ");
                    }

                    @Override
                    public void onNext(Project project) {
                        mIProjectView.showProgress();
                        mIProjectView.setProjectData(project.data);
                    }
                });
    }

}
