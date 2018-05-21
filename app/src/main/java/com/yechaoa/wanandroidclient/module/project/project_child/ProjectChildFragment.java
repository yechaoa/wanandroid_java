package com.yechaoa.wanandroidclient.module.project.project_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.adapter.ProjectChildAdapter;
import com.yechaoa.wanandroidclient.base.DelayFragment;
import com.yechaoa.wanandroidclient.bean.ProjectChild;
import com.yechaoa.wanandroidclient.module.article_detail.ArticleDetailActivity;
import com.yechaoa.yutils.ToastUtil;
import com.yechaoa.yutils.YUtils;

import java.util.List;

import butterknife.BindView;


public class ProjectChildFragment extends DelayFragment implements ProjectChildContract.IProjectChildView, BaseQuickAdapter.OnItemClickListener {

    public static final String CID = "com.yechaoa.wanandroidclient.module.project.project_child.CID";

    @BindView(R.id.project_child_recycler_view)
    RecyclerView mPCRecyclerView;
    private ProjectChildPresenter mProjectChildPresenter;
    private List<ProjectChild.DataBean.DatasBean> mPCList;
    private int mCid;

    /**
     * 创建fragment
     *
     * @param cid 分类的id
     * @return fragment
     */
    public static ProjectChildFragment newInstance(int cid) {
        ProjectChildFragment categoryFragment = new ProjectChildFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CID, cid);
        categoryFragment.setArguments(bundle);
        return categoryFragment;
    }

    @Override
    protected int getLayoutId() {
        isReady = true;
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
    public void setProjectChildData(List<ProjectChild.DataBean.DatasBean> list) {
        mPCList = list;
        ProjectChildAdapter projectChildAdapter = new ProjectChildAdapter(R.layout.item_project_child, list);
        mPCRecyclerView.setAdapter(projectChildAdapter);
        projectChildAdapter.setOnItemClickListener(this);
    }

    @Override
    public void showProjectChildError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    private boolean isReady = false;

    @Override
    protected void delayLoad() {
        if (!isReady || !isVisible) {
            return;
        }
        mProjectChildPresenter = new ProjectChildPresenter(this);
        mProjectChildPresenter.getProjectChildList(0, mCid);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mProjectChildPresenter) {
            mProjectChildPresenter.unSubscribe();
        }
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(mContext, ArticleDetailActivity.class);
        intent.putExtra(ArticleDetailActivity.WEB_URL, mPCList.get(position).link);
        intent.putExtra(ArticleDetailActivity.WEB_TITLE, mPCList.get(position).title);
        startActivity(intent);
    }
}
