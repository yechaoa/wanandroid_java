package com.yechaoa.wanandroidclient.http;

import android.support.annotation.NonNull;

import com.yechaoa.wanandroidclient.common.GlobalConstant;
import com.yechaoa.yutils.SpUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action1;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/5/17.
 * Describe :
 */
public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        Observable.just(SpUtil.getString(GlobalConstant.COOKIES))
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String cookie) {
                        //添加cookie
                        builder.addHeader("cookie", cookie);
                    }
                });
        return chain.proceed(builder.build());
    }

}
