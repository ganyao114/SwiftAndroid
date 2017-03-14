package net.swiftos.feizhai.api;

import net.swiftos.apiservice.model.UserInfo;
import net.swiftos.common.model.bean.BaseResponse;
import net.swiftos.feizhai.application.Constant;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ganyao on 2017/3/14.
 */

public interface IFeiZhaiAPI {

    /**
     * login api
     * @param name
     * @param pass
     * @return
     */
    @POST("/login")
    Observable<BaseResponse<UserInfo>> login(@Query("name")String name, @Query("pass")String pass);

}
