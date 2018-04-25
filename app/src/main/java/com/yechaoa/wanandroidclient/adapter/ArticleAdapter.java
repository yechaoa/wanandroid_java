package com.yechaoa.wanandroidclient.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.bean.Article;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/22.
 * Describe :
 */
public class ArticleAdapter extends BaseQuickAdapter<Article.DataBean.DataDetailBean, BaseViewHolder> {

    public ArticleAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Article.DataBean.DataDetailBean item) {
        helper.setText(R.id.article_title, item.title);
        helper.setText(R.id.article_chapter, item.chapterName);
        helper.setText(R.id.article_author, item.author);
        helper.addOnClickListener(R.id.article_favorite);
        Glide.with(mContext).load(R.mipmap.ic_launcher).into((ImageView) helper.getView(R.id.article_image));

    }
}
