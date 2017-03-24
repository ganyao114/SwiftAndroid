package net.swiftos.common.model.net;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.log.SwiftLog;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by ganyao on 2017/3/13.
 */

public class PicassoLoader implements IImageLoader {


    @Override
    public IInstance context(Context context) {
        return new IInstance() {

            private Picasso picasso = Picasso.with(context);

            @Override
            public void load(String url, ImageView imageView) {
                picasso.load(url).into(imageView);
            }

            @Override
            public void load(File src, ImageView imageView) {
                picasso.load(src).into(imageView);
            }

            @Override
            public void load(String url, ImageView imageView, CallBack callBack) {
                picasso.load(url).fetch(new Callback() {
                    @Override
                    public void onSuccess() {
                        load(url, imageView);
                        callBack.loadSuccess(null);
                    }

                    @Override
                    public void onError() {
                        callBack.loadFailure(null);
                    }
                });
            }
        };
    }
}
