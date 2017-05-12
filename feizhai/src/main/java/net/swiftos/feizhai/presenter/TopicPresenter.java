package net.swiftos.feizhai.presenter;

import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.feizhai.protocol.TopicProtocol;

/**
 * Created by ganyao on 2017/4/27.
 */

public class TopicPresenter extends BasePresenter implements TopicProtocol.Presenter {

    TopicProtocol.Model model;
    TopicProtocol.View view;

    public TopicPresenter(TopicProtocol.View view, TopicProtocol.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public <T> void onNavigate(T data) {

    }
}
