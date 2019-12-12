package com.yechaoa.wanandroidclient.base;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/22.
 * Describe :
 */
public interface BaseView {

    void showLoading();

    void hideLoading();

    void onErrorCode(BaseBean bean);

}
