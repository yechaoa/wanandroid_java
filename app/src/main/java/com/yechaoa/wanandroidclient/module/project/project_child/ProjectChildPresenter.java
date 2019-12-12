package com.yechaoa.wanandroidclient.module.project.project_child;

import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseObserver;
import com.yechaoa.wanandroidclient.base.BasePresenter;
import com.yechaoa.wanandroidclient.bean.ProjectChild;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/27.
 * Describe :
 */
class ProjectChildPresenter extends BasePresenter<IProjectChildView> {

    ProjectChildPresenter(IProjectChildView projectChildView) {
        super(projectChildView);
    }

    void getProjectChildList(int page, int cid) {

        addDisposable(apiServer.getProjectChildList(page, cid), new BaseObserver<BaseBean<ProjectChild>>(baseView) {
            @Override
            public void onSuccess(BaseBean<ProjectChild> bean) {
                baseView.setProjectChildData(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.showProjectChildError(msg + "(°∀°)ﾉ");
            }
        });
    }

}
