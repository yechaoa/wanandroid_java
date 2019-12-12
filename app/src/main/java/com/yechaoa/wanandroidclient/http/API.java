package com.yechaoa.wanandroidclient.http;

import com.yechaoa.wanandroidclient.bean.Article;
import com.yechaoa.wanandroidclient.bean.Banner;
import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.bean.Navi;
import com.yechaoa.wanandroidclient.bean.Project;
import com.yechaoa.wanandroidclient.bean.ProjectChild;
import com.yechaoa.wanandroidclient.bean.Tree;
import com.yechaoa.wanandroidclient.bean.TreeChild;
import com.yechaoa.wanandroidclient.bean.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/11.
 * Describe : 接口的管理类
 */

public class API {

    static final String BASE_URL = "https://www.wanandroid.com/";

    public interface WAZApi {

        //-----------------------【首页相关】----------------------

        //首页banner
        @GET("banner/json")
        Observable<BaseBean<List<Banner>>> getBanner();

        //首页文章列表
        @GET("article/list/{page}/json")
        Observable<BaseBean<Article>> getArticleList(@Path("page") Integer page);


        //-----------------------【  体系  】----------------------

        //体系数据
        @GET("tree/json")
        Observable<BaseBean<List<Tree>>> getTreeList();

        //知识体系下的文章
        @GET("article/list/{page}/json?")
        Observable<BaseBean<TreeChild>> getTreeChildList(@Path("page") Integer page, @Query("cid") Integer cid);


        //-----------------------【  导航  】----------------------

        //导航数据
        @GET("navi/json")
        Observable<BaseBean<List<Navi>>> getNaviList();


        //-----------------------【  项目  】----------------------

        //项目分类
        @GET("project/tree/json")
        Observable<BaseBean<List<Project>>> getProjectList();

        //项目列表数据  ?后面的要用@Query，且不在url后面拼接
        @GET("project/list/{page}/json?")
        Observable<BaseBean<ProjectChild>> getProjectChildList(@Path("page") Integer page, @Query("cid") Integer cid);


        //-----------------------【登录注册】----------------------

        //登录
        @FormUrlEncoded
        @POST("user/login")
        Observable<BaseBean<User>> login(@Field("username") String username, @Field("password") String password);

        //注册
        @FormUrlEncoded
        @POST("user/register")
        Observable<BaseBean<User>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);


        //-----------------------【  收藏  】----------------------

        //收藏文章列表
        @GET("lg/collect/list/{page}/json")
        Observable<BaseBean<Article>> getCollectList(@Path("page") Integer page);

        //收藏站内文章
        @POST("lg/collect/{id}/json")
        Observable<BaseBean> collectIn(@Path("id") Integer id);

        //收藏站外文章
        @FormUrlEncoded
        @POST("lg/collect/add/json")
        Observable<BaseBean> collectOut(@Field("title") String title, @Field("author") String author, @Field("link") String link);

        //取消收藏---文章列表
        @POST("lg/uncollect_originId/{id}/json")
        Observable<BaseBean> uncollect(@Path("id") Integer id);

        //取消收藏---我的收藏页面
        @FormUrlEncoded
        @POST("lg/uncollect/{id}/json")
        Observable<BaseBean> uncollect1(@Path("id") Integer id, @Field("originId") Integer originId);


        //-----------------------【  搜索  】----------------------

        //搜索
        @FormUrlEncoded
        @POST("article/query/{page}/json?")
        Observable<Article> search(@Path("page") Integer page, @Field("k") String k);

    }

}
