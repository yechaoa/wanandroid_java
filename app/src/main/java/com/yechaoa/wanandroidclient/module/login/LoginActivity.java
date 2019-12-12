package com.yechaoa.wanandroidclient.module.login;

import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.KeyEvent;

import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.base.BaseActivity;
import com.yechaoa.wanandroidclient.common.GlobalConstant;
import com.yechaoa.wanandroidclient.module.MainActivity;
import com.yechaoa.wanandroidclient.module.login.login.LoginFragment;
import com.yechaoa.yutils.SpUtil;


public class LoginActivity extends BaseActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

        if (SpUtil.getBoolean(GlobalConstant.IS_LOGIN)) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        mFragmentManager = getSupportFragmentManager();
        addFragment(new LoginFragment(), "login");
    }

    public void addFragment(Fragment fragment, String tag) {
        FragmentTransaction beginTransaction = mFragmentManager.beginTransaction();
        beginTransaction.add(R.id.login_content, fragment, tag);
        // 添加到回退栈,并定义标记
        beginTransaction.addToBackStack(tag);
        beginTransaction.commit();
    }

    @Override
    protected void initData() {

    }

    private boolean isLogin = true;//如果是login页面，再次返回的时候直接finish

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 获取当前回退栈中的Fragment个数
            int backStackEntryCount = mFragmentManager.getBackStackEntryCount();
            // 回退栈中至少有多个fragment,栈底部是首页
            if (backStackEntryCount > 1) {
                // 回退一步
                mFragmentManager.popBackStackImmediate();
                // 获取当前退到了哪一个Fragment上,重新获取当前的Fragment回退栈中的个数
                FragmentManager.BackStackEntry backStack = mFragmentManager.getBackStackEntryAt(mFragmentManager.getBackStackEntryCount() - 1);
                // 获取当前栈顶的Fragment的标记值
                String tag = backStack.getName();
                if (tag.equals("login")) {
                    if (isLogin) {
                        addFragment(new LoginFragment(), "login");
                        isLogin = false;
                    } else
                        finish();
                }
            } else {
                finish();
            }
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

