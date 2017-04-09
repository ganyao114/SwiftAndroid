package net.swiftos.usermodule;

import net.swiftos.common.di.module.BaseAPIModule;
import net.swiftos.common.model.entity.Session;

import java.io.Serializable;

/**
 * Created by gy on 2017/2/28.
 */

public interface IUser extends Serializable {
    Session getSession();
    void setSession(Session session);
}
