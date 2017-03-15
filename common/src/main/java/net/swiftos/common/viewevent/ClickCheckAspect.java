package net.swiftos.common.viewevent;

import net.swiftos.eventposter.utils.LOG;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by ganyao on 2017/3/15.
 */
@Aspect
public class ClickCheckAspect {

    @Pointcut("within(@net.swiftos.common.viewevent.ClickCheck *)")
    public void withinAnnotatedClass() {}

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {}

    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType() {}

    @Pointcut("execution(@net.swiftos.common.viewevent.ClickCheck * *(..)) || methodInsideAnnotatedType()")
    public void method() {}

    @Pointcut("execution(@net.swiftos.common.viewevent.ClickCheck *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {}

    @Pointcut("execution(@net.swiftos.common.viewevent.ClickCheck * *(..)) && @annotation(clickCheck)")
    public void pointcutClickCheckMethod(ClickCheck clickCheck) {

    }

    @Around("pointcutClickCheckMethod(clickCheck)")
    public Object clickCheckAndExecute(ProceedingJoinPoint joinPoint, ClickCheck clickCheck) throws Throwable {

        if (System.currentTimeMillis() - ClickCheckManager.lastClick > ClickCheckManager.clickInterval || ClickCheckManager.enable == false) {
            Object ret = joinPoint.proceed();
            if (ClickCheckManager.callback != null) {
                ClickCheckManager.callback.onViewEvent(clickCheck.value());
            }
            ClickCheckManager.lastClick = System.currentTimeMillis();
            return ret;
        }


        return null;

    }

}
