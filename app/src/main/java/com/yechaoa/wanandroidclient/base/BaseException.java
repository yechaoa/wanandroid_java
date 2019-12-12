package com.yechaoa.wanandroidclient.base;

import java.io.IOException;

/**
 * Created by yechao on 2019/11/18/018.
 * Describe :
 */
public class BaseException extends IOException {

    /**
     * 解析数据失败
     */
    public static final String PARSE_ERROR_MSG = "解析数据失败";
    /**
     * 网络问题
     */
    public static final String BAD_NETWORK_MSG = "网络问题";
    /**
     * 连接错误
     */
    public static final String CONNECT_ERROR_MSG = "连接错误";
    /**
     * 连接超时
     */
    public static final String CONNECT_TIMEOUT_MSG = "连接超时";
    /**
     * 未知错误
     */
    public static final String OTHER_MSG = "未知错误";

    private String errorMsg;
    private int errorCode;

    public String getErrorMsg() {
        return errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public BaseException(String message) {
        this.errorMsg = message;
    }

    public BaseException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorMsg = errorMsg;
    }

    public BaseException(int errorCode, String message) {
        this.errorMsg = message;
        this.errorCode = errorCode;
    }

}