package net.swiftos.apiservice.model;

import java.io.Serializable;

/**
 * Created by gy939 on 2017/1/16.
 */

public class UserInfo implements Serializable {

    private String loginname;
    private String name;
    private String email;
    private String phone;
    private String sessionId;

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
