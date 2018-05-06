package com.yechaoa.wanandroidclient.module.login.register;

import com.yechaoa.wanandroidclient.base.BasePresenter;
import com.yechaoa.wanandroidclient.base.BaseView;
import com.yechaoa.wanandroidclient.bean.User;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/5/6.
 * Describe :
 */
public interface RegisterContract {

    interface IRegisterView extends BaseView {

        void showRegisterLoading();

        void showRegisterSuccess(String successMessage);

        void showRegisterFailed(String errorMessage);

        void doSuccess(User user);

    }

    interface IRegisterPresenter extends BasePresenter {

        void submit(String username, String password, String repassword);

    }

}
