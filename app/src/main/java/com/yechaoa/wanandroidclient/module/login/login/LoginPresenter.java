package com.yechaoa.wanandroidclient.module.login.login;

import com.yechaoa.wanandroidclient.bean.User;
import com.yechaoa.wanandroidclient.http.API;
import com.yechaoa.wanandroidclient.http.RetrofitService;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/5/6.
 * Describe :
 */
public class LoginPresenter implements LoginContract.ILoginPresenter {

    private Subscription mSubscription = null;
    private LoginContract.ILoginView mILoginView;

    LoginPresenter(LoginContract.ILoginView loginView) {
        mILoginView = loginView;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        if (null != mSubscription && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void submit(String username, String password) {

        mILoginView.showLoginLoading();

        mSubscription = RetrofitService.create(API.WAZApi.class)
                .login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {

                    @Override
                    public void onCompleted() {
                        mILoginView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mILoginView.hideProgress();
                        mILoginView.showLoginFailed("登录失败:" + e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        if (-1 == user.errorCode)
                            mILoginView.showLoginFailed("登录失败:" + user.errorMsg);
                        else {
                            mILoginView.showProgress();
                            mILoginView.showLoginSuccess("登录成功");
                            mILoginView.doSuccess(user);
                        }
                    }
                });
    }
}
