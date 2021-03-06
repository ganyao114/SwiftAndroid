package net.swiftos.common.navigation;

import android.content.Intent;
import android.support.annotation.UiThread;

import net.swiftos.utils.IDFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gy939 on 2017/1/15.
 */

public class Navigater {

    public final static String NAVI_CODE = "navi_code";

    public static Map<String,Object> dataStack = new HashMap<>();

    @UiThread
    public static <T> T navigateIn(String key) {
        return (T) dataStack.remove(key);
    }

    @UiThread
    public static <T> void navigateOut(Intent intent, T data) {
        String key = "navi_key" + IDFactory.getId();
        dataStack.put(key, data);
        intent.putExtra(NAVI_CODE, key);
    }

    public interface INavigate {
        <T> void onNavigate(T data);
    }

}
