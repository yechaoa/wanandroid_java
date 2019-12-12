package com.yechaoa.wanandroidclient.module.article_detail;

import android.annotation.SuppressLint;
import android.graphics.Color;
import androidx.appcompat.app.ActionBar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.base.BaseActivity;

import butterknife.BindView;

public class ArticleDetailActivity extends BaseActivity {

    public static final String WEB_URL = "com.yechaoa.wanandroidclient.module.ArticleDetailActivity.WEB_URL";
    public static final String WEB_TITLE = "com.yechaoa.wanandroidclient.module.ArticleDetailActivity.WEB_TITLE";

    @BindView(R.id.web_content)
    FrameLayout mWebContent;

    private AgentWeb mAgentWeb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initView() {
        
        if (null != getSupportActionBar()) {
            String title = getIntent().getStringExtra(WEB_TITLE);
            if (!TextUtils.isEmpty(title))
                getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        initAgentWeb();
    }

    @Override
    protected void initData() {

    }

    public String getLoadUrl() {
        return getIntent().getStringExtra(WEB_URL);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initAgentWeb() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((FrameLayout) mWebContent, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(getLoadUrl());


        WebView webView = mAgentWeb.getWebCreator().getWebView();
        WebSettings settings = webView.getSettings();
        //获取手势焦点
        webView.requestFocusFromTouch();
        //支持JS
        settings.setJavaScriptEnabled(true);
        //是否支持缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        //是否显示缩放按钮
        settings.setDisplayZoomControls(false);
        //自适应屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //设置布局方式,支持内容重新布局
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


        addBGChild((FrameLayout) mAgentWeb.getWebCreator().getWebParentLayout()); // 得到 AgentWeb 最底层的控件

    }

    protected void addBGChild(FrameLayout frameLayout) {
        TextView mTextView = new TextView(frameLayout.getContext());
        mTextView.setText("技术由 AgentWeb 提供");
        mTextView.setTextSize(16);
        mTextView.setTextColor(Color.parseColor("#727779"));
        frameLayout.setBackgroundColor(Color.parseColor("#272b2d"));
        FrameLayout.LayoutParams mFlp = new FrameLayout.LayoutParams(-2, -2);
        mFlp.gravity = Gravity.CENTER_HORIZONTAL;
        final float scale = frameLayout.getContext().getResources().getDisplayMetrics().density;
        mFlp.topMargin = (int) (15 * scale + 0.5f);
        frameLayout.addView(mTextView, 0, mFlp);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mAgentWeb.handleKeyEvent(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
