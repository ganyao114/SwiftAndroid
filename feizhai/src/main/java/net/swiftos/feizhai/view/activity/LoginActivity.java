package net.swiftos.feizhai.view.activity;

import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.feizhai.R;
import net.swiftos.feizhai.buss.ServiceManager;
import net.swiftos.feizhai.di.component.DaggerLoginComponent;
import net.swiftos.feizhai.di.component.LoginComponent;
import net.swiftos.feizhai.di.module.LoginModule;
import net.swiftos.feizhai.protocol.LoginProtocol;
import net.swiftos.view.anime.CircularAnim;

import butterknife.Bind;

/**
 * Created by ganyao on 2017/3/10.
 */

public class LoginActivity extends BaseFeizhaiActivity<LoginComponent> implements LoginProtocol.View {

    public LoginProtocol.Presenter presenter;

    @Bind(R.id.btn_login)
    AppCompatButton loginBtn;
    @Bind(R.id.login_progress)
    ProgressBar loginProgress;

    @Bind(R.id.login_name)
    EditText loginName;
    @Bind(R.id.login_pass)
    EditText loginPass;
    @Bind(R.id.link_regist)
    TextView registerPageLink;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_login_layout;
    }

    @Override
    protected void initView() {
        viewsNeedLock = new View[]{loginBtn, loginName, loginPass};
        loginBtn.setOnClickListener( v -> presenter.login(loginName.getText().toString(), loginPass.getText().toString()));
        registerPageLink.setOnClickListener( v -> startActivity(new Intent(this, RegisterActivity.class)));
    }

    @Override
    protected void initData() {
    }

    @Override
    protected int barColor() {
        return R.color.themeColor;
    }

    @Override
    protected LoginComponent setupActivityComponent() {
        return DaggerLoginComponent.builder()
                    .feiZhaiAPIComponent(ServiceManager.getFeiZhaiAPIComponent())
                    .loginModule(new LoginModule(this))
                    .build();
    }

    @Override
    protected BasePresenter setPresenter() {
        presenter = component.presenter();
        return (BasePresenter) presenter;
    }

    @Override
    public void showProgress() {
        CircularAnim.hide(loginBtn).go(() -> loginProgress.setVisibility(View.VISIBLE));
    }

    @Override
    public void dismissProgress() {
        CircularAnim.show(loginBtn).go( () -> loginProgress.setVisibility(View.GONE));
    }

    @Override
    public void showLoginSuccess() {

    }

    @Override
    public void showLoginFailure(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
