package net.swiftos.common.user;

import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.cache.IKVDiskCache;
import net.swiftos.common.user.di.DaggerUserManagerComponent;
import net.swiftos.common.user.di.UserManagerModule;
import net.swiftos.common.user.entity.IUserInfo;
import net.swiftos.common.user.entity.Users;
import net.swiftos.common.user.di.UserManagerComponent;
import net.swiftos.common.user.entity.IUser;

import java.util.Map;

/**
 * Created by swift_gan on 2017/2/17.
 */

public class UserManager {

    private Users users;

    IKVDiskCache diskCache;

    public UserManager() {
        diskCache = BaseApplication.getAppComponent().kvDiskCache();
        users = (Users) diskCache.get(Users.DISK_KEY);
        if (users == null) {
            users = new Users();
        }
    }

    public boolean isLogin(){
        if (users.currentUser == null)
            return false;
        else
            return true;
    }

    public synchronized void login(IUser user) {
        if (users.currentUser != null) {
            users.currentUser.onDestroy();
        }
        users.currentUser = user;
        refreshCache();
        BaseApplication.getAppComponent()
                .eventHub()
                .post(new Logined());
    }

    public synchronized void logout() {
        if (users.currentUser != null) {
            users.currentUser.onDestroy();
        }
        users.currentUser = null;
        refreshCache();
        BaseApplication.getAppComponent()
                .eventHub()
                .post(new Logoutted(users.currentUser));
    }

    public void saveUserInfo(IUserInfo userInfo) {
        users.userSaved.put(userInfo.getKey(), userInfo);
        refreshCache();
    }

    public Map<String,IUserInfo> getUserInfos() {
        return users.userSaved;
    }

    private synchronized void refreshCache() {
        diskCache.save(Users.DISK_KEY, users);
    }

    public static UserManagerComponent init() {
        return DaggerUserManagerComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .userManagerModule(new UserManagerModule())
                .build();
    }

}
