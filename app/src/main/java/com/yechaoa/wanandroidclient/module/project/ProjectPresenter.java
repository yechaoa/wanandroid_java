package com.yechaoa.wanandroidclient.module.project;

import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseObserver;
import com.yechaoa.wanandroidclient.base.BasePresenter;
import com.yechaoa.wanandroidclient.bean.Project;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/27.
 * Describe :
 */
public class ProjectPresenter extends BasePresenter<IProjectView> {

    ProjectPresenter(IProjectView projectView) {
        super(projectView);
    }

    public void getProjectList() {
        addDisposable(apiServer.getProjectList(), new BaseObserver<BaseBean<List<Project>>>(baseView) {
            @Override
            public void onSuccess(BaseBean<List<Project>> bean) {
                baseView.setProjectData(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.showProjectError(msg + "(°∀°)ﾉ");
            }
        });
    }

}
