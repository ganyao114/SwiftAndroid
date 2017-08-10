package net.swiftos.feizhai.view.adapter;

import android.content.Context;

import net.swiftos.feizhai.model.bean.Topic;
import net.swiftos.view.recyclerview.CommonAdapter;
import net.swiftos.view.recyclerview.ViewHolder;

import java.util.List;

/**
 * Created by ganyao on 2017/5/1.
 */

public class TopicListAdapter extends CommonAdapter<Topic> {


    public TopicListAdapter(Context context, int layoutId, List<Topic> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, Topic topic) {

    }
}
