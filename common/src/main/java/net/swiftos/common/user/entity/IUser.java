package net.swiftos.common.user.entity;

import net.swiftos.common.model.entity.Session;

import java.io.Serializable;

/**
 * Created by gy on 2017/2/28.
 */

public interface IUser extends Serializable {

    String getSessionId();
    Session getSession();
    void setSession(Session session);
    String getKey();
    void onDestroy();

    void processSession(Session session);

}
