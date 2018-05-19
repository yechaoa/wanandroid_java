package com.yechaoa.wanandroidclient.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.bean.Article;
import com.yechaoa.wanandroidclient.bean.TreeChild;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/30.
 * Describe :
 */
public class TreeChildAdapter extends BaseQuickAdapter<TreeChild.DataBean.DatasBean, BaseViewHolder> {

    public TreeChildAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TreeChild.DataBean.DatasBean item) {
        helper.setText(R.id.article_title, item.title);
        helper.setText(R.id.article_chapter, item.chapterName);
        helper.setText(R.id.article_author, item.author);
        helper.addOnClickListener(R.id.article_favorite);

        if (item.collect)
            Glide.with(mContext).load(R.drawable.ic_like_checked).into((ImageView) helper.getView(R.id.article_favorite));
        else
            Glide.with(mContext).load(R.drawable.ic_like_normal).into((ImageView) helper.getView(R.id.article_favorite));

        Glide.with(mContext).load(R.mipmap.ic_launcher).into((ImageView) helper.getView(R.id.article_image));

    }
}
