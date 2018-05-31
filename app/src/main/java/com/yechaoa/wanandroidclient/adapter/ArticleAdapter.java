package com.yechaoa.wanandroidclient.adapter;

import android.text.Html;
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

    private boolean mTypeIsCollect = false;

    public void setType(boolean typeIsCollect) {
        mTypeIsCollect = typeIsCollect;
    }

    @Override
    protected void convert(BaseViewHolder helper, Article.DataBean.DataDetailBean item) {
        //fromHtml，因为搜索结果中的title中含有html标签
        helper.setText(R.id.article_title, Html.fromHtml(item.title));
        helper.setText(R.id.article_chapter, item.chapterName);
        helper.setText(R.id.article_author, item.author);
        helper.addOnClickListener(R.id.article_favorite);

        //先判断类型是不是收藏列表，因为收藏列表不返回item.collect字段，所以没法判断
        if (mTypeIsCollect)
            Glide.with(mContext).load(R.drawable.ic_like_checked).into((ImageView) helper.getView(R.id.article_favorite));
        else {
            if (item.collect)
                Glide.with(mContext).load(R.drawable.ic_like_checked).into((ImageView) helper.getView(R.id.article_favorite));
            else
                Glide.with(mContext).load(R.drawable.ic_like_normal).into((ImageView) helper.getView(R.id.article_favorite));
        }

    }
}
