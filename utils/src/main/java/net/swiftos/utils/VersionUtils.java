package net.swiftos.utils;

import android.os.Build;

/**
 * Created by ganyao on 2017/8/14.
 */

public class VersionUtils {
    public static boolean isAndroidO (){
        return Build.VERSION.SDK_INT >= 26;
    }
}
