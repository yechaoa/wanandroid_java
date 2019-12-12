package com.yechaoa.wanandroidclient.module.login.login;

import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseObserver;
import com.yechaoa.wanandroidclient.base.BasePresenter;
import com.yechaoa.wanandroidclient.bean.User;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/5/6.
 * Describe :
 */
class LoginPresenter extends BasePresenter<ILoginView> {

    LoginPresenter(ILoginView baseView) {
        super(baseView);
    }

    void submit(String username, String password) {

        addDisposable(apiServer.login(username, password), new BaseObserver<BaseBean<User>>(baseView) {
            @Override
            public void onSuccess(BaseBean<User> bean) {
                baseView.showLoginSuccess("登录成功（￣▽￣）");
                baseView.doSuccess(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.showLoginFailed(msg + "(°∀°)ﾉ");
            }
        });
    }
}
