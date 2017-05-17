package net.swiftos.feizhai.model.bean;

import net.swiftos.apiservice.model.UserInfo;
import net.swiftos.common.model.entity.Session;
import net.swiftos.common.user.entity.IUser;

/**
 * Created by ganyao on 2017/3/14.
 */

public class User implements IUser {

    private String sessionId;
    private String key;
    private String name;
    private UserInfo userInfo;
    private Session session;


    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void processSession(Session session) {
        session.getHeaders().put("Cookie", "JSESSIONID=" + getSessionId());
    }

}
