package net.swiftos.feizhai.api;

import net.swiftos.apiservice.model.UserInfo;
import net.swiftos.common.model.bean.BaseResponse;
import net.swiftos.feizhai.application.Constant;
import net.swiftos.feizhai.model.bean.Article;

import java.util.List;

import retrofit2.http.GET;
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
    @POST("user/login-by-name")
    Observable<BaseResponse<UserInfo>> login(@Query("loginname")String name, @Query("pass")String pass);

    @POST("user/register")
    Observable<BaseResponse<String>> register(@Query("loginname")String name, @Query("pass")String pass);


    @POST("article/articles")
    Observable<BaseResponse<List<Article>>> getArticles(@Query("topicId") int topicId, @Query("lastId") int lastId
            , @Query("limit") int limit);

    @POST("article/hot-articles")
    Observable<BaseResponse<List<Article>>> hotArticles(@Query("lastId") int lastId
            , @Query("limit") int limit);

    @POST("article/save-article")
    Observable<BaseResponse<String>> saveArticle(@Query("title")String title, @Query("content") String content
            , @Query("topicId") int topicId, @Query("articleType") String type);


}
