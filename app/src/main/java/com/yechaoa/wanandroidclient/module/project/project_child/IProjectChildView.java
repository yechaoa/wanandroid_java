package com.yechaoa.wanandroidclient.module.project.project_child;

import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseView;
import com.yechaoa.wanandroidclient.bean.ProjectChild;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/27.
 * Describe :
 */
public interface IProjectChildView extends BaseView {

    void setProjectChildData(BaseBean<ProjectChild> list);

    void showProjectChildError(String errorMessage);

}
