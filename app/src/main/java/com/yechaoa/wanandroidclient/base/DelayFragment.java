package com.yechaoa.wanandroidclient.base;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/22.
 * Describe : 懒加载
 */
public abstract class DelayFragment extends BaseFragment {

    protected boolean isVisible;a

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            delayLoad();
        } else {
            isVisible = false;
        }
    }

    protected abstract void delayLoad();

}
