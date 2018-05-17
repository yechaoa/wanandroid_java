package com.yechaoa.wanandroidclient.bean;

import java.io.Serializable;
import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/25.
 * Describe :
 */
public class Tree implements Serializable{

    /**
     * data : [{"children":[{"children":[],"courseId":13,..."visible":1}]
     * errorCode : 0
     * errorMsg :
     */

    public int errorCode;
    public String errorMsg;
    public List<DataBean> data;

    public static class DataBean implements Serializable{
        /**
         * children : [{"children":[],"courseId":13,"id":60,"name":"Android Studio相关","order":1000,"parentChapterId":150,"visible":1},{"children":[],"courseId":13,"id":169,"name":"gradle","order":1001,"parentChapterId":150,"visible":1},{"children":[],"courseId":13,"id":269,"name":"官方发布","order":1002,"parentChapterId":150,"visible":1}]
         * courseId : 13
         * id : 150
         * name : 开发环境
         * order : 1
         * parentChapterId : 0
         * visible : 1
         */

        public int courseId;
        public int id;
        public String name;
        public int order;
        public int parentChapterId;
        public int visible;
        public List<ChildrenBean> children;

        public static class ChildrenBean implements Serializable{
            /**
             * children : []
             * courseId : 13
             * id : 60
             * name : Android Studio相关
             * order : 1000
             * parentChapterId : 150
             * visible : 1
             */

            public int courseId;
            public int id;
            public String name;
            public int order;
            public int parentChapterId;
            public int visible;
            public List<?> children;
        }
    }
}
