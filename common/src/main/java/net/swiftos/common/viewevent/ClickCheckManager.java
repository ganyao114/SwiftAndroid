package net.swiftos.common.viewevent;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by ganyao on 2017/3/15.
 */
public class ClickCheckManager {

    public static boolean enable = true;
    public static long lastClick = 0;
    public static long clickInterval = (long) (1.5 * 1000); //ms
    public static ViewEventCallback callback;

}
