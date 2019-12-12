package com.yechaoa.wanandroidclient.module.navi;

import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseView;
import com.yechaoa.wanandroidclient.bean.Navi;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/26.
 * Describe :
 */
public interface INaviView extends BaseView {

    void setNaviData(BaseBean<List<Navi>> list);

    void showNaviError(String errorMessage);

}
