package net.swiftos.common.user.aop;

/**
 * Created by gy on 2017/2/28.
 */

public class LoginChecked {

    private LoginCheckedResult result;

    public LoginChecked(LoginCheckedResult result) {
        this.result = result;
    }

    public LoginCheckedResult getResult() {
        return result;
    }

    public void setResult(LoginCheckedResult result) {
        this.result = result;
    }

    public enum LoginCheckedResult {
        Login,
        UnLogin
    }
}
