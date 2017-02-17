package net.swiftos.eventposter.impls.viewevent.template;

import android.view.View;

/**
 * Created by gy939 on 2016/10/16.
 */

public interface OnViewAttachListener {
    public void onViewAttached(String context,View view);
    public void onViewDettached(String context,View view);
}
