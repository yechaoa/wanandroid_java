package com.yechaoa.wanandroidclient.module.home;

import com.yechaoa.wanandroidclient.base.BaseBean;
import com.yechaoa.wanandroidclient.base.BaseObserver;
import com.yechaoa.wanandroidclient.base.BasePresenter;
import com.yechaoa.wanandroidclient.bean.Article;
import com.yechaoa.wanandroidclient.bean.Banner;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/22.
 * Describe :
 */
public class HomePresenter extends BasePresenter<IHomeView> {

    HomePresenter(IHomeView baseView) {
        super(baseView);
    }

    public void getBannerData() {
        addDisposable(apiServer.getBanner(), new BaseObserver<BaseBean<List<Banner>>>(baseView) {
            @Override
            public void onSuccess(BaseBean<List<Banner>> bean) {
                baseView.setBannerData(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.showBannerError(msg + "(°∀°)ﾉ");
            }
        });
    }

    /**
     * 第一次加载文章列表
     */
    public void getArticleList() {
        addDisposable(apiServer.getArticleList(0), new BaseObserver<BaseBean<Article>>(baseView) {
            @Override
            public void onSuccess(BaseBean<Article> bean) {
                baseView.setArticleData(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.showArticleError(msg + "(°∀°)ﾉ");
            }
        });
    }

    /**
     * 加载更多
     *
     * @param page 分页参数
     */
    public void getArticleListByMore(int page) {
        addDisposable(apiServer.getArticleList(page), new BaseObserver<BaseBean<Article>>(baseView) {
            @Override
            public void onSuccess(BaseBean<Article> bean) {
                baseView.setArticleDataByMore(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.showArticleErrorByMore(msg + "(°∀°)ﾉ");
            }
        });
    }

    /**
     * 收藏
     *
     * @param id 文章id
     */
    public void collect(int id) {
        addDisposable(apiServer.collectIn(id), new BaseObserver<BaseBean>(baseView) {
            @Override
            public void onSuccess(BaseBean bean) {
                baseView.showCollectSuccess("收藏成功（￣▽￣）");
            }

            @Override
            public void onError(String msg) {
                baseView.showCollectError(msg + "(°∀°)ﾉ");
            }
        });
    }

    /**
     * 取消收藏
     *
     * @param id 文章id
     */
    public void uncollect(int id) {
        addDisposable(apiServer.uncollect(id), new BaseObserver<BaseBean>(baseView) {
            @Override
            public void onSuccess(BaseBean bean) {
                baseView.showUncollectSuccess("取消收藏成功（￣▽￣）");
            }

            @Override
            public void onError(String msg) {
                baseView.showUncollectError(msg + "(°∀°)ﾉ");
            }
        });
    }

}
