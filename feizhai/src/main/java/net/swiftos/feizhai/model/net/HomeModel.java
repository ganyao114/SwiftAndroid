package net.swiftos.feizhai.model.net;

import net.swiftos.common.model.entity.BaseAsyncCallback;
import net.swiftos.common.presenter.IAsyncSubject;
import net.swiftos.feizhai.model.bean.Article;
import net.swiftos.feizhai.protocol.HomeProtocol;

import java.util.List;

/**
 * Created by ganyao on 2017/4/26.
 */

public class HomeModel extends FeiZhaiHttpModel implements HomeProtocol.Model {

    public HomeModel() {
        super();
    }


    @Override
    public IAsyncSubject hotArticles(int lastId, BaseAsyncCallback<List<Article>> callback) {
        return baseModel.getAsyncSubject(api.hotArticles(lastId, 20).cache(), callback);
    }
}
