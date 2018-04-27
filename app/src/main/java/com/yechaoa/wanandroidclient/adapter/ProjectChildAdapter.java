package com.yechaoa.wanandroidclient.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.bean.ProjectChild;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/27.
 * Describe :
 */
public class ProjectChildAdapter extends BaseQuickAdapter<ProjectChild.DataBean.DatasBean, BaseViewHolder> {

    public ProjectChildAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectChild.DataBean.DatasBean item) {
        if(item.envelopePic.endsWith(".gif"))
            Glide.with(mContext).asGif().load(item.envelopePic).into((ImageView) helper.getView(R.id.item_project_child_pic));
        else
            Glide.with(mContext).load(item.envelopePic).into((ImageView) helper.getView(R.id.item_project_child_pic));
        helper.setText(R.id.item_project_child_title, item.title);
        helper.setText(R.id.item_project_child_desc, item.desc);
        helper.setText(R.id.item_project_child_date, item.niceDate);
        helper.setText(R.id.item_project_child_author, item.author);
    }
}
