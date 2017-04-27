package net.swiftos.view.nineimagegroupview;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Lazh on 2016/3/17.
 */
public abstract class NineImageAdapter {
    public ImageView initImageView(Context context){
        ImageView imageView = new ImageView(context);
        return imageView ;
    }
    public abstract void  displayImageView(Context context,ImageView imageView , int position, String url);
}
