package net.swiftos.feizhai.presenter.home;

import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.feizhai.protocol.HomeProtocol;

/**
 * Created by ganyao on 2017/4/26.
 */

public class HomePresenter extends BasePresenter implements HomeProtocol.Presenter {

    HomeProtocol.Model model;
    HomeProtocol.View view;

    public HomePresenter(HomeProtocol.Model model, HomeProtocol.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public <T> void onNavigate(T data) {

    }
}
