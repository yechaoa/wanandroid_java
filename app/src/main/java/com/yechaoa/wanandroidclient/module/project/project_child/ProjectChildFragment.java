package com.yechaoa.wanandroidclient.module.project.project_child;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.adapter.ProjectChildAdapter;
import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseFragment;
import com.yechaoa.wanandroidclient.bean.ProjectChild;
import com.yechaoa.wanandroidclient.module.article_detail.ArticleDetailActivity;
import com.yechaoa.yutils.ToastUtil;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class ProjectChildFragment extends BaseFragment<ProjectChildPresenter> implements IProjectChildView, BaseQuickAdapter.OnItemClickListener {

    public static final String CID = "com.yechaoa.wanandroidclient.module.project.project_child.CID";

    @BindView(R.id.project_child_recycler_view)
    RecyclerView mPCRecyclerView;
    private List<ProjectChild.DatasBean> mPCList;

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
    protected ProjectChildPresenter createPresenter() {
        return new ProjectChildPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project_child;
    }

    @Override
    protected void initView() {
        mPCRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initData() {
        int cid = getArguments().getInt(CID);
        presenter.getProjectChildList(0, cid);
    }

    @Override
    public void setProjectChildData(BaseBean<ProjectChild> list) {
        mPCList = list.data.datas;
        ProjectChildAdapter projectChildAdapter = new ProjectChildAdapter(R.layout.item_project_child, list.data.datas);
        mPCRecyclerView.setAdapter(projectChildAdapter);
        projectChildAdapter.setOnItemClickListener(this);
    }

    @Override
    public void showProjectChildError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(mContext, ArticleDetailActivity.class);
        intent.putExtra(ArticleDetailActivity.WEB_URL, mPCList.get(position).link);
        intent.putExtra(ArticleDetailActivity.WEB_TITLE, mPCList.get(position).title);
        startActivity(intent);
    }
}
