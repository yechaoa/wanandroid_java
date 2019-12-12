package com.yechaoa.wanandroidclient.module.tree.tree_child;

import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseView;
import com.yechaoa.wanandroidclient.bean.TreeChild;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/30.
 * Describe :
 */
public interface ITreeChildView extends BaseView {

    void setTreeChildData(BaseBean<TreeChild> list);

    void showTreeChildError(String errorMessage);

    void showCollectSuccess(String successMessage);

    void showCollectError(String errorMessage);

    void showUncollectSuccess(String successMessage);

    void showUncollectError(String errorMessage);

}
