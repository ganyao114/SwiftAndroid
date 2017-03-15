package net.swiftos.feizhai.model.net;

import net.swiftos.common.model.entity.Session;
import net.swiftos.common.model.net.BaseHttpModel;
import net.swiftos.common.model.net.CommonResponseAdapter;
import net.swiftos.common.model.net.IResponseAdapter;
import net.swiftos.feizhai.api.IFeiZhaiAPI;
import net.swiftos.feizhai.buss.ServiceManager;

import javax.inject.Inject;

/**
 * Created by ganyao on 2017/3/14.
 */

public class FeiZhaiHttpModel extends BaseHttpModel {

    public IFeiZhaiAPI api;
    public Session session;

    public FeiZhaiHttpModel() {
        api = ServiceManager.getFeiZhaiAPIComponent().getAPI();
        session = ServiceManager.getFeiZhaiAPIComponent().getSession();
    }

    @Override
    protected IResponseAdapter setBaseReponse() {
        return CommonResponseAdapter.getInstance();
    }
}
