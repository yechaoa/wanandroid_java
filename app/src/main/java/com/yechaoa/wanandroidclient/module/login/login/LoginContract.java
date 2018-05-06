package com.yechaoa.wanandroidclient.module.login.login;

import com.yechaoa.wanandroidclient.base.BasePresenter;
import com.yechaoa.wanandroidclient.base.BaseView;
import com.yechaoa.wanandroidclient.bean.Article;
import com.yechaoa.wanandroidclient.bean.Banner;
import com.yechaoa.wanandroidclient.bean.User;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/5/6.
 * Describe :
 */
public interface LoginContract {

    interface ILoginView extends BaseView{

        void showLoginLoading();

        void showLoginSuccess(String successMessage);

        void showLoginFailed(String errorMessage);

        void doSuccess(User user);

    }

    interface ILoginPresenter extends BasePresenter{

        void submit(String username,String password);

    }

}
