package net.swiftos.common.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;


import net.swiftos.common.R;
import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.navigation.Navigater;
import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.utils.ValidateUtil;

import butterknife.ButterKnife;

/**
 * Created by ganyao on 2016/10/26.
 */
public abstract class BaseActivity extends AppCompatActivity implements Navigater.INavigate {

    private BasePresenter basePresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(getContentLayout());
        ButterKnife.bind(this);
        basePresenter = setupActivityComponent(BaseApplication.getAppComponent());
        initView();
        if (basePresenter != null) {
            basePresenter.onViewInited();
        }
        String navigateKey = getIntent().getStringExtra(Navigater.NAVI_CODE);
        if (!ValidateUtil.isEmpty(navigateKey)) {
            onNavigate(Navigater.navigateIn(navigateKey));
        }
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (basePresenter != null) {
            basePresenter.onViewDestoryed();
        }
    }

    protected void showProgressDialog(ProgressDialog dialog) {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    protected void dismissProgressDialog(ProgressDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.push_up_leave_in,
                    R.anim.push_up_leave_out);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected abstract
    @LayoutRes
    int getContentLayout();

    protected abstract void initView();

    public <T> void onNavigate(T par) {
        if (basePresenter != null) {
            basePresenter.onNavigate(par);
        }
    }

    protected abstract void initData();

    protected abstract BasePresenter setupActivityComponent(AppComponent appComponent);

}
