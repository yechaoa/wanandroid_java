package com.yechaoa.wanandroidclient.module.tree;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.adapter.TreeAdapter;
import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseFragment;
import com.yechaoa.wanandroidclient.bean.Tree;
import com.yechaoa.wanandroidclient.module.tree.tree_child.TreeChildActivity;
import com.yechaoa.yutils.ToastUtil;

import java.io.Serializable;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class TreeFragment extends BaseFragment<TreePresenter> implements ITreeView, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.tree_recycler_view)
    RecyclerView mTreeRecyclerView;
    private List<Tree> mTreeList;

    @Override
    protected TreePresenter createPresenter() {
        return new TreePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tree;
    }

    @Override
    protected void initView() {
        mTreeRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initData() {
        presenter.getTreeList();
    }

    @Override
    public void setTreeData(BaseBean<List<Tree>> list) {
        mTreeList = list.data;
        TreeAdapter treeAdapter = new TreeAdapter(R.layout.item_tree_list, list.data);
        mTreeRecyclerView.setAdapter(treeAdapter);
        treeAdapter.setOnItemClickListener(this);
    }

    @Override
    public void showTreeError(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(mContext, TreeChildActivity.class);
        intent.putExtra(TreeChildActivity.CID, (Serializable) mTreeList.get(position).children);
        intent.putExtra(TreeChildActivity.TITLE, mTreeList.get(position).name);
        startActivity(intent);
    }
}
