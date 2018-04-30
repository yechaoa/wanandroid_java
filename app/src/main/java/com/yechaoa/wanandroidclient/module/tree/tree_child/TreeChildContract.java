package com.yechaoa.wanandroidclient.module.tree.tree_child;

import com.yechaoa.wanandroidclient.base.BasePresenter;
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
public interface TreeChildContract {

    interface ITreeChildView extends BaseView {

        void setTreeChildData(List<TreeChild.DataBean.DatasBean> list);

        void showTreeChildError(String errorMessage);

    }

    interface ITreeChildPresenter extends BasePresenter {

        void getTreeChildList(int page, int cid);

    }

}
