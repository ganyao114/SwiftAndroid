package net.swiftos.eventposter.invoker;

/**
 * Created by gy939 on 2016/10/6.
 */
public class DefaultInvoker extends IInvokeDirect{
    @Override
    public Object invoke(String methodname, Object... pars) {
        return proxy;
    }
}
