package net.swiftos.eventposter.impls.decorator;

/**
 * Created by swift_gan on 2017/2/17.
 */
@FunctionalInterface
public interface Function<T> {
    public T invoke(Object... pars);
}
