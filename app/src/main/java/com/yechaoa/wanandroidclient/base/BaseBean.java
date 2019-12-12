package com.yechaoa.wanandroidclient.base;

import java.io.Serializable;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/5/17.
 * Describe :
 */
public class BaseBean<T> implements Serializable {

    public BaseBean(int code, String data) {
        this.errorCode = code;
        this.data = (T) data;
    }

    /**
     * data :
     * errorCode : 0
     * errorMsg :
     */

    public int errorCode;
    public String errorMsg;
    public T data;



}
