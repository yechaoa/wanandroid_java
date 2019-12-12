package com.yechaoa.wanandroidclient.module.navi;

import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseObserver;
import com.yechaoa.wanandroidclient.base.BasePresenter;
import com.yechaoa.wanandroidclient.bean.Navi;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/26.
 * Describe :
 */
public class NaviPresenter extends BasePresenter<INaviView> {

    NaviPresenter(INaviView naviView) {
        super(naviView);
    }

    public void getNaviList() {
        addDisposable(apiServer.getNaviList(), new BaseObserver<BaseBean<List<Navi>>>(baseView) {
            @Override
            public void onSuccess(BaseBean<List<Navi>> bean) {
                baseView.setNaviData(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.showNaviError(msg + "(°∀°)ﾉ");
            }
        });
    }

}
