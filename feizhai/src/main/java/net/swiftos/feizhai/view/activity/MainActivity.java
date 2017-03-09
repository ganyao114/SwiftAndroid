package net.swiftos.feizhai.view.activity;

import android.widget.Toast;

import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.common.view.activity.BaseActivity;
import net.swiftos.eventposter.impls.customevent.annotation.InjectEvent;
import net.swiftos.feizhai.R;
import net.swiftos.usermodule.aop.DebugLog;
import net.swiftos.usermodule.aop.LoginChecked;
import net.swiftos.usermodule.aop.LoginRequired;


public class MainActivity extends BaseActivity {

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @DebugLog
    @Override
    protected void initView() {
        test("nihhao");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter setupActivityComponent(AppComponent appComponent) {
        return null;
    }

    @LoginRequired
    public void test(String str) {
        Toast.makeText(this, str , Toast.LENGTH_LONG).show();
    }

    @InjectEvent
    public void unlogined(LoginChecked event) {
        Toast.makeText(this, event.getResult().toString() , Toast.LENGTH_LONG).show();
    }

}
