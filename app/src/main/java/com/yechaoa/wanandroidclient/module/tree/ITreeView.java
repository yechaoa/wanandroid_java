package com.yechaoa.wanandroidclient.module.tree;

import com.yechaoa.wanandroidclient.base.BaseBean;
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
public interface ITreeView extends BaseView {

    void setTreeData(BaseBean<List<Tree>> list);

    void showTreeError(String errorMessage);

}
