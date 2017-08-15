package net.swiftos.common.model.net;

import com.google.gson.Gson;

/**
 * Created by ganyao on 2017/8/15.
 */

public class GsonImpl implements IJson {

    private Gson gson;

    public GsonImpl() {
        this.gson = new Gson();
    }

    @Override
    public String toJson(Object object) {
        return gson.toJson(object);
    }

    @Override
    public <T> T fromJson(String string, Class<T> type) {
        return gson.fromJson(string, type);
    }

}
