package com.yechaoa.wanandroidclient.module.login.register;

import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseView;
import com.yechaoa.wanandroidclient.bean.User;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/5/6.
 * Describe :
 */
public interface IRegisterView extends BaseView {

    void showRegisterSuccess(String successMessage);

    void showRegisterFailed(String errorMessage);

    void doSuccess(BaseBean<User> user);

}
