package com.yechaoa.wanandroidclient.module.tree;

import com.yechaoa.wanandroidclient.base.BasePresenter;
import com.yechaoa.wanandroidclient.base.BaseView;
import com.yechaoa.wanandroidclient.bean.Tree;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/25.
 * Describe :
 */
public interface TreeContract {

    interface ITreeView extends BaseView {

        void setTreeData(List<Tree.DataBean> list);

        void showTreeError(String errorMessage);

    }

    interface ITreePresenter extends BasePresenter {

        void getTreeList();

    }

}
