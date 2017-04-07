package net.swiftos.common.ospermission;

import org.aspectj.lang.Signature;

/**
 * Created by ganyao on 2017/4/5.
 */

public class PermissionDenyEvent {

    public String[] permissions;
    public Signature signature;

    public PermissionDenyEvent(String[] permissions, Signature signature) {
        this.permissions = permissions;
        this.signature = signature;
    }
}
