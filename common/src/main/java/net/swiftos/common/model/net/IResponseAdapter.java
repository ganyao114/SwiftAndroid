package net.swiftos.common.model.net;

import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by ganyao on 2017/3/15.
 */
@FunctionalInterface
public interface IResponseAdapter<I,O> {
    O adapter(I i);
}
