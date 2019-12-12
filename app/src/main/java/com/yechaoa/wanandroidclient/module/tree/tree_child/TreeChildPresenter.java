package com.yechaoa.wanandroidclient.module.tree.tree_child;

import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseObserver;
import com.yechaoa.wanandroidclient.base.BasePresenter;
import com.yechaoa.wanandroidclient.bean.TreeChild;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/30.
 * Describe :
 */
public class TreeChildPresenter extends BasePresenter<ITreeChildView> {


    TreeChildPresenter(ITreeChildView treeChildView) {
        super(treeChildView);
    }

    public void getTreeChildList(int page, int cid) {
        addDisposable(apiServer.getTreeChildList(page, cid), new BaseObserver<BaseBean<TreeChild>>(baseView) {
            @Override
            public void onSuccess(BaseBean<TreeChild> bean) {
                baseView.setTreeChildData(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.showTreeChildError(msg + "(°∀°)ﾉ");
            }
        });
    }

    public void collect(int id) {
        addDisposable(apiServer.collectIn(id), new BaseObserver<BaseBean>(baseView) {
            @Override
            public void onSuccess(BaseBean bean) {
                baseView.showCollectSuccess("收藏成功（￣▽￣）");
            }

            @Override
            public void onError(String msg) {
                baseView.showCollectError(msg + "(°∀°)ﾉ");
            }
        });
    }

    public void uncollect(int id) {
        addDisposable(apiServer.uncollect(id), new BaseObserver<BaseBean>(baseView) {
            @Override
            public void onSuccess(BaseBean bean) {
                baseView.showUncollectSuccess("取消收藏成功（￣▽￣）");
            }

            @Override
            public void onError(String msg) {
                baseView.showUncollectError(msg + "(°∀°)ﾉ");
            }
        });
    }

}
