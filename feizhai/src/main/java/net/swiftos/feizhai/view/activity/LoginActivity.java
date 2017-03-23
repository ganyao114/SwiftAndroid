package net.swiftos.feizhai.view.activity;

import android.os.Looper;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.common.thread.Async;
import net.swiftos.common.thread.AsyncSuccessCallback;
import net.swiftos.common.view.activity.BaseActivity;
import net.swiftos.eventposter.core.EventPoster;
import net.swiftos.eventposter.impls.viewevent.annotation.OnClick;
import net.swiftos.eventposter.impls.viewevent.handler.ViewEventHandler;
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

public class LoginActivity extends BaseActivity<LoginComponent> implements LoginProtocol.View {

    public LoginProtocol.Presenter presenter;

    @Bind(R.id.btn_login)
    AppCompatButton loginBtn;
    @Bind(R.id.login_progress)
    ProgressBar loginProgress;

    @Bind(R.id.login_name)
    EditText loginName;
    @Bind(R.id.login_pass)
    EditText loginPass;


    @Override
    protected int getContentLayout() {
        return R.layout.activity_login_layout;
    }

    @Override
    protected void initView() {
        viewsNeedLock = new View[]{loginBtn, loginName, loginPass};
//        loginBtn.setOnClickListener( v -> presenter.login(loginName.getText().toString(), loginPass.getText().toString()));
        loginBtn.setOnClickListener( v -> {
            new Thread( () -> {
                try {
                    loginBtn.setText("更新了 UI");
                } catch (Throwable e) {

                }
            }).start();
        });
    }

    @Override
    protected void initData() {
        asyncTest(this::doShow);
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
        CircularAnim.hide(loginBtn).go( () -> loginProgress.setVisibility(View.VISIBLE));
    }

    @WorkerThread
    public String asyncTest(AsyncSuccessCallback<String> successCallback) {
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "你好";
    }

    public void doShow(String str){
        loginBtn.setText(str);
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
