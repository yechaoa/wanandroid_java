package com.yechaoa.wanandroidclient.module.project;

import com.yechaoa.wanandroidclient.base.BaseBean;
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
public interface IProjectView extends BaseView {

    void setProjectData(BaseBean<List<Project>> list);

    void showProjectError(String errorMessage);

}
