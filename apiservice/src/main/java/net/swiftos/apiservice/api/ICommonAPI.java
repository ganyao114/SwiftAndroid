package net.swiftos.apiservice.api;

import net.swiftos.apiservice.model.UserInfo;
import net.swiftos.common.model.bean.BaseResponse;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ganyao on 2017/3/9.
 */

public interface ICommonAPI {

    /**
     * 会话开始
     * @return String sessionId
     */
    @POST("/session_start")
    Observable<BaseResponse<String>> sessionStart();

}
