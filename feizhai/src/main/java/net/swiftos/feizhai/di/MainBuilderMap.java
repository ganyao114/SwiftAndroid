package net.swiftos.feizhai.di;

import net.swiftos.feizhai.buss.ServiceManager;
import net.swiftos.feizhai.di.component.DaggerHomeComponent;
import net.swiftos.feizhai.di.component.HomeComponent;
import net.swiftos.feizhai.di.module.HomeModule;
import net.swiftos.feizhai.protocol.HomeProtocol;

/**
 * Created by ganyao on 2017/8/10.
 */

public class MainBuilderMap {

    public static HomeComponent buildHomeComponent(HomeProtocol.View view) {
        HomeComponent component = DaggerHomeComponent.builder()
                .feiZhaiAPIComponent(ServiceManager.getFeiZhaiAPIComponent())
                .homeModule(new HomeModule(view))
                .build();
        return component;
    }

}
