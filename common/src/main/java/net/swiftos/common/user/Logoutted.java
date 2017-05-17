package net.swiftos.common.user;

import net.swiftos.common.event.BaseCustomerEvent;
import net.swiftos.common.user.entity.IUser;

/**
 * Created by gy on 2017/2/28.
 */

public class Logoutted extends BaseCustomerEvent {

    public IUser user;

    public Logoutted(IUser user) {
        this.user = user;
    }
}
