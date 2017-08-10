package net.swiftos.feizhai.application;


import android.app.Application;

import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.feizhai.di.MainBuilderMap;
import net.swiftos.feizhai.presenter.MainPresenter;

/**
 * Created by gy939 on 2017/1/16.
 */

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected Class<? extends BasePresenter> setMainPresenter() {
        return MainPresenter.class;
    }

    @Override
    protected Class[] setComponentsBuilderMap() {
        return new Class[]{MainBuilderMap.class};
    }
}
