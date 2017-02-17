package net.swiftos.feizhai.application;

import net.swiftos.common.application.BaseApplication;
import net.swiftos.feizhai.presenter.MainPresenter;

/**
 * Created by gy939 on 2017/1/16.
 */

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        init(MainPresenter.class);
    }
}
