package net.swiftos.feizhai.model.bean;

import net.swiftos.common.model.entity.Session;
import net.swiftos.common.user.entity.IUser;

/**
 * Created by ganyao on 2017/3/14.
 */

public class User implements IUser {

    @Override
    public String getSessionId() {
        return null;
    }

    @Override
    public Session getSession() {
        return null;
    }

    @Override
    public void setSession(Session session) {

    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public void onDestroy() {

    }

}
