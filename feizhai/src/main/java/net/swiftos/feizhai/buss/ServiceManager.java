package net.swiftos.feizhai.buss;

import android.os.Bundle;
import android.support.annotation.UiThread;

import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.di.component.ComponentManager;
import net.swiftos.feizhai.di.component.DaggerFeiZhaiAPIComponent;
import net.swiftos.feizhai.di.component.FeiZhaiAPIComponent;
import net.swiftos.feizhai.di.module.FeiZhaiAPIModule;

/**
 * Created by ganyao on 2017/3/14.
 */

public class ServiceManager {

    @UiThread
    public static FeiZhaiAPIComponent getFeiZhaiAPIComponent() {
        FeiZhaiAPIComponent feiZhaiAPIComponent = ComponentManager.getStaticComponent(FeiZhaiAPIComponent.class);
        if (feiZhaiAPIComponent == null) {
            feiZhaiAPIComponent = DaggerFeiZhaiAPIComponent.builder()
                    .appComponent(BaseApplication.getAppComponent())
                    .feiZhaiAPIModule(new FeiZhaiAPIModule())
                    .build();
            ComponentManager.registerStaticComponent(FeiZhaiAPIComponent.class, feiZhaiAPIComponent);
        }
        return feiZhaiAPIComponent;
    }


}
