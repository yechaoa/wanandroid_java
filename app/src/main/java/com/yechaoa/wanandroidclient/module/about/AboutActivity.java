package com.yechaoa.wanandroidclient.module.about;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.module.article_detail.ArticleDetailActivity;
import com.yechaoa.yutils.YUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_app_info)
    TextView mTvAppInfo;
    @BindView(R.id.tv_github)
    TextView mTvGithub;
    @BindView(R.id.tv_api)
    TextView mTvApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mTvAppInfo.setText(String.valueOf(getResources().getString(R.string.app_name) + "  V" + YUtils.getVersionName()));

        mTvGithub.setText(getResources().getString(R.string.github_title));
        mTvGithub.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        mTvApi.setText(getResources().getString(R.string.api_title));
        mTvApi.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

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

    @OnClick({R.id.tv_app_info, R.id.tv_github, R.id.tv_api})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_app_info:
                break;
            case R.id.tv_github:
                intent = new Intent(this, ArticleDetailActivity.class);
                intent.putExtra(ArticleDetailActivity.WEB_URL, getResources().getString(R.string.github_link));
                intent.putExtra(ArticleDetailActivity.WEB_TITLE, getResources().getString(R.string.github_title));
                startActivity(intent);
                break;
            case R.id.tv_api:
                intent = new Intent(this, ArticleDetailActivity.class);
                intent.putExtra(ArticleDetailActivity.WEB_URL, getResources().getString(R.string.api_link));
                intent.putExtra(ArticleDetailActivity.WEB_TITLE, getResources().getString(R.string.api_title));
                startActivity(intent);
                break;
        }
    }

}
