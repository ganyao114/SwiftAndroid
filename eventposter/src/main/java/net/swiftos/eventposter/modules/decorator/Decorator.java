package net.swiftos.eventposter.modules.decorator;

/**
 * 装饰器
 * Created by swift_gan on 2017/2/17.
 */

public class Decorator {

    public static <T> T invoke(Function<T> function, Object... pars) {
        return function.invoke(pars);
    }

}
