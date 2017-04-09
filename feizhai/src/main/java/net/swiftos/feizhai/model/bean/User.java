package net.swiftos.feizhai.model.bean;

import net.swiftos.common.model.entity.Session;
import net.swiftos.usermodule.IUser;

import java.io.Serializable;

/**
 * Created by ganyao on 2017/3/14.
 */

public class User implements IUser {

    @Override
    public Session getSession() {
        return null;
    }

    @Override
    public void setSession(Session session) {

    }

}
