package net.swiftos.common.model.net;

import android.content.Context;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by ganyao on 2017/3/13.
 */

public interface IImageLoader {

    IInstance context(Context context);


    interface IInstance {
        void load(String url, ImageView imageView);
        void load(File src, ImageView imageView);
        void load(String url, ImageView imageView, CallBack callBack);
    }

    interface CallBack<S,E> {
        void loadSuccess(S s);
        void loadFailure(E e);
    }

}
