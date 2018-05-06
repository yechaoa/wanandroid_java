package com.yechaoa.wanandroidclient.bean;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/5/2.
 * Describe :
 */
public class User {

    /**
     * data : {"collectIds":[],"email":"","icon":"","id":3,"password":"111111","type":0,"username":"111111"}
     */
    public DataBean data;
    public int errorCode;
    public String errorMsg;

    public static class DataBean {
        /**
         * collectIds : []
         * email :
         * icon :
         * id : 3
         * password : 111111
         * type : 0
         * username : 111111
         */

        public String email;
        public String icon;
        public int id;
        public String password;
        public int type;
        public String username;
        public List<?> collectIds;
        public String repassword;
    }
}
