package net.swiftos.common.user.di;

import net.swiftos.common.di.scope.ServiceScope;
import net.swiftos.common.user.UserManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ganyao on 2017/4/28.
 */
@Module
public class UserManagerModule {

    private UserManager userManager;

    public UserManagerModule() {
        this.userManager = new UserManager();
    }

    @Provides
    @ServiceScope
    public UserManager providerUserManager() {
        return userManager;
    }

}
