package com.yechaoa.wanandroidclient.module.tree.tree_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.adapter.TreeChildAdapter;
import com.yechaoa.wanandroidclient.base.DelayFragment;
import com.yechaoa.wanandroidclient.bean.TreeChild;
import com.yechaoa.wanandroidclient.module.article_detail.ArticleDetailActivity;
import com.yechaoa.yutils.ToastUtil;
import com.yechaoa.yutils.YUtils;

import java.util.List;

import butterknife.BindView;


public class TreeChildFragment extends DelayFragment implements TreeChildContract.ITreeChildView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    public static final String CID = "com.yechaoa.wanandroidclient.module.tree.TreeChildFragment.CID";

    @BindView(R.id.project_child_recycler_view)
    RecyclerView mPCRecyclerView;
    private TreeChildPresenter mTreeChildPresenter;
    private List<TreeChild.DataBean.DatasBean> mTCList;
    private int mCid;
    private int mPosition;
    private TreeChildAdapter mTreeChildAdapter;

    /**
     * 创建fragment
     *
     * @param cid 分类的id
     * @return fragment
     */
    public static TreeChildFragment newInstance(int cid) {
        TreeChildFragment categoryFragment = new TreeChildFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CID, cid);
        categoryFragment.setArguments(bundle);
        return categoryFragment;
    }

    @Override
    protected int getLayoutId() {
        isReady = true;
        mCid = getArguments().getInt(CID);
        delayLoad();
        return R.layout.fragment_project_child;
    }

    @Override
    protected void initView() {
        mCid = getArguments().getInt(CID);
        mPCRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showProgress() {
        YUtils.showLoading(getActivity(), getResources().getString(R.string.loading));
    }

    @Override
    public void hideProgress() {
        YUtils.dismissLoading();
    }

    @Override
    public void setTreeChildData(List<TreeChild.DataBean.DatasBean> list) {
        mTCList = list;
        mTreeChildAdapter = new TreeChildAdapter(R.layout.item_article_list, list);
        mPCRecyclerView.setAdapter(mTreeChildAdapter);
        mTreeChildAdapter.setOnItemClickListener(this);
        mTreeChildAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void showTreeChildError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    private boolean isReady = false;

    @Override
    protected void delayLoad() {
        if (!isReady || !isVisible) {
            return;
        }
        mTreeChildPresenter = new TreeChildPresenter(this);
        mTreeChildPresenter.getTreeChildList(0, mCid);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mTreeChildPresenter) {
            mTreeChildPresenter.unSubscribe();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(mContext, ArticleDetailActivity.class);
        intent.putExtra(ArticleDetailActivity.WEB_URL, mTCList.get(position).link);
        intent.putExtra(ArticleDetailActivity.WEB_TITLE, mTCList.get(position).title);
        startActivity(intent);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.article_favorite:
                mPosition = position;
                if (mTCList.get(position).collect)
                    mTreeChildPresenter.uncollect(mTCList.get(position).id);
                else
                    mTreeChildPresenter.collect(mTCList.get(position).id);
                break;
        }
    }

    @Override
    public void showCollectSuccess(String successMessage) {
        ToastUtil.showToast(successMessage);
        mTCList.get(mPosition).collect = true;
        mTreeChildAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCollectError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void showUncollectSuccess(String successMessage) {
        ToastUtil.showToast(successMessage);
        mTCList.get(mPosition).collect = false;
        mTreeChildAdapter.notifyDataSetChanged();
    }

    @Override
    public void showUncollectError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }
}
