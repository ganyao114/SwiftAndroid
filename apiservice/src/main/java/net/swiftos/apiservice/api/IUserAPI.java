package net.swiftos.apiservice.api;

import net.swiftos.apiservice.model.UserInfo;
import net.swiftos.common.model.bean.BaseResponse;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by gy939 on 2017/1/16.
 */

public interface IUserAPI {
    /**
     * 登陆
     * @param loginName
     * @param pass
     * @return
     */
    @POST()
    Observable<BaseResponse<UserInfo>> login(@Query("loginname")String loginName, @Query("pass")String pass);
}
