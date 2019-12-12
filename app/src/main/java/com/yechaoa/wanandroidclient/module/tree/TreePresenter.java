package com.yechaoa.wanandroidclient.module.tree;

import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseObserver;
import com.yechaoa.wanandroidclient.base.BasePresenter;
import com.yechaoa.wanandroidclient.bean.Tree;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/25.
 * Describe :
 */
public class TreePresenter extends BasePresenter<ITreeView> {

    TreePresenter(ITreeView treeView) {
        super(treeView);
    }

    public void getTreeList() {
        addDisposable(apiServer.getTreeList(), new BaseObserver<BaseBean<List<Tree>>>(baseView) {
            @Override
            public void onSuccess(BaseBean<List<Tree>> bean) {
                baseView.setTreeData(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.showTreeError(msg + "(°∀°)ﾉ");
            }
        });
    }

}
