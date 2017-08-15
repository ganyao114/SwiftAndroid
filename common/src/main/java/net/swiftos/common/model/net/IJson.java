package net.swiftos.common.model.net;

/**
 * Created by ganyao on 2017/8/15.
 */

public interface IJson {
    String toJson(Object object);
    <T> T fromJson(String string, Class<T> type);
}
