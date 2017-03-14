package net.swiftos.feizhai.buss;

import android.os.Bundle;
import android.support.annotation.UiThread;

import net.swiftos.common.application.BaseApplication;
import net.swiftos.feizhai.di.component.DaggerFeiZhaiAPIComponent;
import net.swiftos.feizhai.di.component.FeiZhaiAPIComponent;
import net.swiftos.feizhai.di.module.FeiZhaiAPIModule;

/**
 * Created by ganyao on 2017/3/14.
 */

public class ServiceManager {


    public static FeiZhaiAPIComponent feiZhaiAPIComponent;

    @UiThread
    public static FeiZhaiAPIComponent getFeiZhaiAPIComponent() {
        if (feiZhaiAPIComponent == null) {
            feiZhaiAPIComponent = DaggerFeiZhaiAPIComponent.builder()
                    .appComponent(BaseApplication.getAppComponent())
                    .feiZhaiAPIModule(new FeiZhaiAPIModule())
                    .build();
        }
        return feiZhaiAPIComponent;
    }


}
