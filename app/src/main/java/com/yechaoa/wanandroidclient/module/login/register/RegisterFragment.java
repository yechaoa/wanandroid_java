package com.yechaoa.wanandroidclient.module.login.register;

import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseFragment;
import com.yechaoa.wanandroidclient.bean.User;
import com.yechaoa.yutils.ToastUtil;
import com.yechaoa.yutils.YUtils;

import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment<RegisterPresenter> implements IRegisterView {

    @BindView(R.id.til_username)
    TextInputLayout mTilUsername;
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.til_password)
    TextInputLayout mTilPassword;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    @BindView(R.id.et_repassword)
    EditText mEtRepassword;
    @BindView(R.id.til_repassword)
    TextInputLayout mTilRepassword;

    private String mUsername, mPassword, mRepassword;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initView() {
        mEtUsername.addTextChangedListener(mTextWatcher);
        mEtPassword.addTextChangedListener(mTextWatcher);
        mEtRepassword.addTextChangedListener(mTextWatcher);
    }

    @Override
    protected void initData() {
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (mTilUsername.getEditText().getText().length() > mTilUsername.getCounterMaxLength())
                mTilUsername.setError("输入内容超过上限");
            else if (mTilUsername.getEditText().getText().length() < mTilUsername.getCounterMaxLength() / 2)
                mTilUsername.setError("最少6位");
            else
                mTilUsername.setError(null);

            if (mTilPassword.getEditText().getText().length() > mTilPassword.getCounterMaxLength())
                mTilPassword.setError("输入内容超过上限");
            else if (mTilPassword.getEditText().getText().length() < mTilPassword.getCounterMaxLength() / 2)
                mTilPassword.setError("最少6位");
            else
                mTilPassword.setError(null);

            if (mTilRepassword.getEditText().getText().length() > mTilRepassword.getCounterMaxLength())
                mTilRepassword.setError("输入内容超过上限");
            else if (mTilRepassword.getEditText().getText().length() < mTilRepassword.getCounterMaxLength() / 2)
                mTilRepassword.setError("最少6位");
            else
                mTilRepassword.setError(null);
        }
    };

    @OnClick({R.id.btn_register})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.btn_register) {
            YUtils.closeSoftKeyboard();
            if (isUsernameValid() && isPasswordValid() && isRepasswordValid()) {
                if (mPassword.equals(mRepassword)) {
                    showLoading();
                    presenter.submit(mUsername, mPassword, mRepassword);
                } else
                    ToastUtil.showToast("两次密码不一样( ⊙ o ⊙ ) ");
            } else {
                ToastUtil.showToast("Failed (°∀°)ﾉ");
            }
        }
    }

    private boolean isUsernameValid() {
        mUsername = mEtUsername.getText().toString().trim();
        return !TextUtils.isEmpty(mUsername) && mUsername.length() <= mTilUsername.getCounterMaxLength() && mUsername.length() >= mTilUsername.getCounterMaxLength() / 2;
    }

    private boolean isPasswordValid() {
        mPassword = mEtPassword.getText().toString().trim();
        return !TextUtils.isEmpty(mPassword) && mPassword.length() <= mTilPassword.getCounterMaxLength() && mPassword.length() >= mTilPassword.getCounterMaxLength() / 2;
    }

    private boolean isRepasswordValid() {
        mRepassword = mEtRepassword.getText().toString().trim();
        return !TextUtils.isEmpty(mRepassword) && mRepassword.length() <= mTilRepassword.getCounterMaxLength() && mRepassword.length() >= mTilRepassword.getCounterMaxLength() / 2;
    }

    @Override
    public void showRegisterSuccess(String successMessage) {
        hideLoading();
        ToastUtil.showToast(successMessage);
    }

    @Override
    public void showRegisterFailed(String errorMessage) {
        hideLoading();
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void doSuccess(BaseBean<User> user) {
        new Handler().postDelayed(() -> {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.show(getActivity().getSupportFragmentManager().findFragmentByTag("login"));
            fragmentTransaction.hide(getActivity().getSupportFragmentManager().findFragmentByTag("register"));
            fragmentTransaction.commit();
        }, 1500);
    }

}
