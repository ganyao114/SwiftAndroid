package net.swiftos.common.view.activity;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import net.swiftos.common.R;
import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.di.component.ComponentManager;
import net.swiftos.common.navigation.Navigater;
import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.eventposter.core.EventPoster;
import net.swiftos.utils.ValidateUtil;

import butterknife.ButterKnife;

/**
 * T = component type
 * Created by ganyao on 2016/10/26.
 */
public abstract class BaseActivity<T> extends AppCompatActivity implements Navigater.INavigate {

    public BasePresenter basePresenter;

    private boolean isFront = false;

    public T component;

    protected View[] viewsNeedLock;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            //此处可以重新指定状态栏颜色
            tintManager.setStatusBarTintResource(barColor());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(getContentLayout());
        BaseApplication.getAppComponent()
                .eventHub()
                .register(this);
        ButterKnife.bind(this);
        component = setupActivityComponent();
        basePresenter = setPresenter();
        initView();
        if (basePresenter != null) {
            basePresenter.attachView(viewType(), this);
            basePresenter.onViewInited();
        }
        String navigateKey = getIntent().getStringExtra(Navigater.NAVI_CODE);
        if (!ValidateUtil.isEmpty(navigateKey)) {
            onNavigate(Navigater.navigateIn(navigateKey));
        }
        initData();
    }

    protected Class viewType() {
        return getClass();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isFront = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isFront = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.getAppComponent()
                .eventHub()
                .unRegister(this);
        if (basePresenter != null) {
            basePresenter.detachView(viewType());
            basePresenter.onViewDestroyed();
        }
    }

    public boolean isFront() {
        return isFront;
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

    public void lockUI() {
        if (viewsNeedLock == null) return;
        for (View view:viewsNeedLock) {
            view.setEnabled(false);
        }
    }

    public void unLockUI() {
        if (viewsNeedLock == null) return;
        for (View view:viewsNeedLock) {
            view.setEnabled(true);
        }
    }

    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
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

    public T getComponent() {
        return component;
    }

    public BasePresenter getPresenter() {
        return basePresenter;
    }

    protected abstract void initData();

    protected abstract @IdRes int barColor();

    protected abstract T setupActivityComponent();

    protected abstract BasePresenter setPresenter();

}
