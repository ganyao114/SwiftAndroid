package net.swiftos.common.model.bean;

/**
 * Created by gy939 on 2017/1/15.
 */

public class ErrorResponse {

    private Object tag;
    private int code;
    private String msg;

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
