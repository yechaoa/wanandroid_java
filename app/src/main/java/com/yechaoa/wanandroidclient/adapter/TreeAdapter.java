package com.yechaoa.wanandroidclient.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.bean.Tree;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/22.
 * Describe :
 */
public class TreeAdapter extends BaseQuickAdapter<Tree.DataBean, BaseViewHolder> {

    public TreeAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Tree.DataBean item) {
        helper.setText(R.id.tree_name, item.name);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < item.children.size(); i++)
            stringBuilder.append(String.valueOf(item.children.get(i).name + "\t\t\t"));
        helper.setText(R.id.tree_child_name, stringBuilder);
    }
}
