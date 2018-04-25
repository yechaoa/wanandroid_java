package com.yechaoa.wanandroidclient.module.tree;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.adapter.TreeAdapter;
import com.yechaoa.wanandroidclient.base.DelayFragment;
import com.yechaoa.wanandroidclient.bean.Tree;
import com.yechaoa.yutils.ToastUtil;
import com.yechaoa.yutils.YUtils;

import java.util.List;

import butterknife.BindView;

public class TreeFragment extends DelayFragment implements TreeContract.ITreeView, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.tree_recycler_view)
    RecyclerView mTreeRecyclerView;
    private TreePresenter mTreePresenter;
    private List<Tree.DataBean> mTreeList;

    @Override
    protected int getLayoutId() {
        isReady = true;
        delayLoad();
        return R.layout.fragment_tree;
    }

    @Override
    protected void initView() {
        mTreeRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mTreePresenter = new TreePresenter(this);
    }

    @Override
    protected void initData() {

    }

    private boolean isReady = false;

    @Override
    protected void delayLoad() {
        if (!isReady || !isVisible) {
            return;
        }
        mTreePresenter.subscribe();
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
    public void setTreeData(List<Tree.DataBean> list) {
        mTreeList = list;
        TreeAdapter treeAdapter = new TreeAdapter(R.layout.item_tree_list, list);
        mTreeRecyclerView.setAdapter(treeAdapter);
        treeAdapter.setOnItemClickListener(this);
    }

    @Override
    public void showTreeError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        // TODO: 2018/4/25 处理点击事件
        ToastUtil.showToast(mTreeList.get(position).name);
    }
}
