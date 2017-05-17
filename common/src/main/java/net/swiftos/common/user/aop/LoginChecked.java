package net.swiftos.common.user.aop;

/**
 * Created by gy on 2017/2/28.
 */

public class LoginChecked {

    private LoginCheckedResult result;
    private String tag;

    public LoginChecked(LoginCheckedResult result, String tag) {
        this.result = result;
        this.tag = tag;
    }

    public LoginCheckedResult getResult() {
        return result;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setResult(LoginCheckedResult result) {
        this.result = result;
    }

    public enum LoginCheckedResult {
        Login,
        UnLogin
    }
}
