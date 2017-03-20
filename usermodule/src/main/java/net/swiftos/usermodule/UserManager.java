package net.swiftos.usermodule;

import net.swiftos.eventposter.core.EventPoster;
import net.swiftos.eventposter.impls.customevent.handler.CustomEventHandler;

/**
 * Created by swift_gan on 2017/2/17.
 */

public class UserManager {

    private static IUser currentUser;

    public static boolean isLogin(){
        if (currentUser == null)
            return false;
        else
            return true;
    }

    public static void login(IUser user) {
        currentUser = user;
        EventPoster.with(CustomEventHandler.class).broadcast(new Logined());
    }

    public static void logout() {
        currentUser = null;
        EventPoster.with(CustomEventHandler.class).broadcast(new Logoutted());
    }

}
