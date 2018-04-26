package com.yechaoa.wanandroidclient.module.navi;

import com.yechaoa.wanandroidclient.base.BasePresenter;
import com.yechaoa.wanandroidclient.base.BaseView;
import com.yechaoa.wanandroidclient.bean.Navi;
import com.yechaoa.wanandroidclient.bean.Tree;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/26.
 * Describe :
 */
public interface NaviContract {

    interface INaviView extends BaseView {

        void setNaviData(List<Navi.DataBean> list);

        void showNaviError(String errorMessage);

    }

    interface INaviPresenter extends BasePresenter {

        void getNaviList();

    }

}
