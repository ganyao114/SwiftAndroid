package net.swiftos.common.model.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ganyao on 2017/3/10.
 */

public class Session {

    private Map<String,String> pars, headers;

    public Session() {
        pars = new HashMap<>();
        headers = new HashMap<>();
    }

    public Map<String, String> getPars() {
        return pars;
    }

    public void setPars(Map<String, String> pars) {
        this.pars = pars;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
