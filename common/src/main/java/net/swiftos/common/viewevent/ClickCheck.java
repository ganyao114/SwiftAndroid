package net.swiftos.common.viewevent;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, METHOD, CONSTRUCTOR})
@Retention(RUNTIME)
public @interface ClickCheck {
    String[] value() default {};
}
