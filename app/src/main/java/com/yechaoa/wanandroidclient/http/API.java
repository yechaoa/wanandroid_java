package com.yechaoa.wanandroidclient.http;

import com.yechaoa.wanandroidclient.bean.Article;
import com.yechaoa.wanandroidclient.bean.Banner;
import com.yechaoa.wanandroidclient.bean.Collect;
import com.yechaoa.wanandroidclient.bean.Common;
import com.yechaoa.wanandroidclient.bean.Navi;
import com.yechaoa.wanandroidclient.bean.Project;
import com.yechaoa.wanandroidclient.bean.ProjectChild;
import com.yechaoa.wanandroidclient.bean.Tree;
import com.yechaoa.wanandroidclient.bean.TreeChild;
import com.yechaoa.wanandroidclient.bean.User;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/11.
 * Describe : 接口的管理类
 */

public class API {

    static final String BASE_URL = "http://www.wanandroid.com/";

    public interface WAZApi {

        //-----------------------【首页相关】----------------------

        //首页banner
        @GET("banner/json")
        Observable<Banner> getBanner();

        //首页文章列表
        @GET("article/list/{page}/json")
        Observable<Article> getArticleList(@Path("page") Integer page);


        //-----------------------【  体系  】----------------------

        //体系数据
        @GET("tree/json")
        Observable<Tree> getTreeList();

        //知识体系下的文章
        @GET("article/list/{page}/json?")
        Observable<TreeChild> getTreeChildList(@Path("page") Integer page, @Query("cid") Integer cid);


        //-----------------------【  导航  】----------------------

        //导航数据
        @GET("navi/json")
        Observable<Navi> getNaviList();


        //-----------------------【  项目  】----------------------

        //项目分类
        @GET("project/tree/json")
        Observable<Project> getProjectList();

        //项目列表数据  ?后面的要用@Query，且不在url后面拼接
        @GET("project/list/{page}/json?")
        Observable<ProjectChild> getProjectChildList(@Path("page") Integer page, @Query("cid") Integer cid);


        //-----------------------【登录注册】----------------------

        //登录
        @FormUrlEncoded
        @POST("user/login")
        Observable<User> login(@Field("username") String username, @Field("password") String password);

        //注册
        @FormUrlEncoded
        @POST("user/register")
        Observable<User> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);


        //-----------------------【登录注册】----------------------

        //首页文章列表
        @GET("lg/collect/list/{page}/json")
        Observable<Collect> getCollectList(@Path("page") Integer page);

        //收藏站内文章
        @POST("lg/collect/{id}/json")
        Observable<Common> collectIn(@Path("id") Integer id);

        //收藏站外文章
        @FormUrlEncoded
        @POST("lg/collect/add/json")
        Observable<Common> collectOut(@Field("title") String title, @Field("author") String author,@Field("link") String link);

        //取消收藏---文章列表
        @POST("lg/uncollect_originId/{id}/json")
        Observable<Common> uncollect(@Path("id") Integer id);

        //取消收藏---我的收藏页面
        @POST("lg/uncollect/{id}/json")
        Observable<Common> uncollect1(@Path("id") Integer id);

    }

}
