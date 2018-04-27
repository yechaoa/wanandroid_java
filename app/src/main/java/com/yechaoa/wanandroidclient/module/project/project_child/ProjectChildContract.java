package com.yechaoa.wanandroidclient.module.project.project_child;

import com.yechaoa.wanandroidclient.base.BasePresenter;
import com.yechaoa.wanandroidclient.base.BaseView;
import com.yechaoa.wanandroidclient.bean.ProjectChild;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/27.
 * Describe :
 */
public interface ProjectChildContract {

    interface IProjectChildView extends BaseView {

        void setProjectChildData(List<ProjectChild.DataBean.DatasBean> list);

        void showProjectChildError(String errorMessage);

    }

    interface IProjectChildPresenter extends BasePresenter {

        void getProjectChildList(int page, int cid);

    }

}
