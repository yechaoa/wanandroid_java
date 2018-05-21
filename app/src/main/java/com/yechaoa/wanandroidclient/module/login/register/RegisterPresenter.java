package com.yechaoa.wanandroidclient.module.login.register;

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
public class RegisterPresenter implements RegisterContract.IRegisterPresenter {

    private Subscription mSubscription = null;
    private RegisterContract.IRegisterView mIRegisterView;

    RegisterPresenter(RegisterContract.IRegisterView registerView) {
        mIRegisterView = registerView;
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
    public void submit(String username, String password, String repassword) {

        mIRegisterView.showRegisterLoading();

        mSubscription = RetrofitService.create(API.WAZApi.class)
                .register(username, password, repassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {

                    @Override
                    public void onCompleted() {
                        mIRegisterView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mIRegisterView.hideProgress();
                        mIRegisterView.showRegisterFailed("注册失败(°∀°)ﾉ" + e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        if (-1 == user.errorCode)
                            mIRegisterView.showRegisterFailed("注册失败(°∀°)ﾉ" + user.errorMsg);
                        else {
                            mIRegisterView.showProgress();
                            mIRegisterView.showRegisterSuccess("注册成功（￣▽￣）");
                            mIRegisterView.doSuccess(user);
                        }
                    }
                });
    }
}
