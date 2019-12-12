package com.yechaoa.wanandroidclient.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.bean.Navi;
import com.yechaoa.wanandroidclient.bean.Tree;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/26.
 * Describe :
 */
public class NaviChildAdapter extends BaseQuickAdapter<Navi.ArticlesBean, BaseViewHolder> {

    public NaviChildAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Navi.ArticlesBean item) {
        helper.setText(R.id.navi_name, item.title);
    }
}
