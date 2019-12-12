package com.yechaoa.wanandroidclient.module.tree.tree_child;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.adapter.TreeChildAdapter;
import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseFragment;
import com.yechaoa.wanandroidclient.bean.TreeChild;
import com.yechaoa.wanandroidclient.module.article_detail.ArticleDetailActivity;
import com.yechaoa.yutils.ToastUtil;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class TreeChildFragment extends BaseFragment<TreeChildPresenter> implements ITreeChildView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    public static final String CID = "com.yechaoa.wanandroidclient.module.tree.TreeChildFragment.CID";

    @BindView(R.id.project_child_recycler_view)
    RecyclerView mPCRecyclerView;
    private List<TreeChild.DatasBean> mTCList;
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
    protected TreeChildPresenter createPresenter() {
        return new TreeChildPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        mCid = getArguments().getInt(CID);
        return R.layout.fragment_project_child;
    }

    @Override
    protected void initView() {
        mCid = getArguments().getInt(CID);
        mPCRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initData() {
        presenter.getTreeChildList(0, mCid);
    }

    @Override
    public void setTreeChildData(BaseBean<TreeChild> list) {
        mTCList = list.data.datas;
        mTreeChildAdapter = new TreeChildAdapter(R.layout.item_article_list, list.data.datas);
        mPCRecyclerView.setAdapter(mTreeChildAdapter);
        mTreeChildAdapter.setOnItemClickListener(this);
        mTreeChildAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void showTreeChildError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
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
                    presenter.uncollect(mTCList.get(position).id);
                else
                    presenter.collect(mTCList.get(position).id);
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
