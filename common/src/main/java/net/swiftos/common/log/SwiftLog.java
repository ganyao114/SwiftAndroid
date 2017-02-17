package net.swiftos.common.log;

import android.support.design.BuildConfig;
import android.text.TextUtils;
import android.util.Log;

public class SwiftLog {

    public static final boolean DEBUGGING_ENABLED = BuildConfig.DEBUG;

    public static void LOGV(String tag, String message) {
        if (DEBUGGING_ENABLED && !TextUtils.isEmpty(message)) {
            Log.v(tag, message);
        }
    }

    public static void LOGD(String tag, String message) {
        if (DEBUGGING_ENABLED && !TextUtils.isEmpty(message)) {
            Log.d(tag, message);
        }
    }

    public static void LOGI(String tag, String message) {
        if (DEBUGGING_ENABLED && !TextUtils.isEmpty(message)) {
            Log.i(tag, message);
        }
    }

    public static void LOGW(String tag, String message) {
        if (DEBUGGING_ENABLED && !TextUtils.isEmpty(message)) {
            Log.w(tag, message);
        }
    }

    public static void LOGE(String tag, String message) {
        if (DEBUGGING_ENABLED && !TextUtils.isEmpty(message)) {
            Log.e(tag, message);
        }
    }
}
