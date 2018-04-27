package com.yechaoa.wanandroidclient.module.project;

import com.yechaoa.wanandroidclient.base.BasePresenter;
import com.yechaoa.wanandroidclient.base.BaseView;
import com.yechaoa.wanandroidclient.bean.Project;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/27.
 * Describe :
 */
public interface ProjectContract {

    interface IProjectView extends BaseView {

        void setProjectData(List<Project.DataBean> list);

        void showProjectError(String errorMessage);

    }

    interface IProjectPresenter extends BasePresenter {

        void getProjectList();

    }

}
