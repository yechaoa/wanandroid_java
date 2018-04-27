package com.yechaoa.wanandroidclient.http;

import com.yechaoa.wanandroidclient.bean.Article;
import com.yechaoa.wanandroidclient.bean.Banner;
import com.yechaoa.wanandroidclient.bean.Navi;
import com.yechaoa.wanandroidclient.bean.Project;
import com.yechaoa.wanandroidclient.bean.ProjectChild;
import com.yechaoa.wanandroidclient.bean.Tree;

import retrofit2.http.GET;
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

    }

}
