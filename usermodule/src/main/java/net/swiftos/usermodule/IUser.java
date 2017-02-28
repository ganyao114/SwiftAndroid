package net.swiftos.usermodule;

import net.swiftos.common.di.module.BaseAPIModule;

/**
 * Created by gy on 2017/2/28.
 */

public interface IUser {
    BaseAPIModule.Session getSession();
    void setSession(BaseAPIModule.Session session);
}
