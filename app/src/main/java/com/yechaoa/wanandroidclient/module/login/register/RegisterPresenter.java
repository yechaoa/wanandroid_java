package com.yechaoa.wanandroidclient.module.login.register;

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
class RegisterPresenter extends BasePresenter<IRegisterView> {

    RegisterPresenter(IRegisterView baseView) {
        super(baseView);
    }

    void submit(String username, String password, String repassword) {

        addDisposable(apiServer.register(username, password, repassword), new BaseObserver<BaseBean<User>>(baseView) {
            @Override
            public void onSuccess(BaseBean<User> bean) {
                baseView.showRegisterSuccess("注册成功（￣▽￣）");
                baseView.doSuccess(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.showRegisterFailed(msg + "(°∀°)ﾉ");
            }
        });

    }
}
