package net.swiftos.common.user.di;

import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.di.scope.ServiceScope;
import net.swiftos.common.user.UserManager;

import dagger.Component;

/**
 * Created by ganyao on 2017/4/28.
 */
@Component(modules = UserManagerModule.class, dependencies = AppComponent.class)
@ServiceScope
public interface UserManagerComponent {
    UserManager userManager();
}
