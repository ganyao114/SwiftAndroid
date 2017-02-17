package net.swiftos.eventposter.impls.decorator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 此注解标记该方法需要使用装饰器调用
 * Created by swift_gan on 2017/2/17.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface ForDecoratorInvoke {

}
