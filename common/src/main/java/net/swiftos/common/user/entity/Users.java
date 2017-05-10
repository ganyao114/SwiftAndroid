package net.swiftos.common.user.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ganyao on 2017/4/28.
 */

public class Users implements Serializable {

    public final static String DISK_KEY = "user_manager";

    public volatile IUser currentUser;

    public Map<String,IUserInfo> userSaved = new ConcurrentHashMap<>();

}
