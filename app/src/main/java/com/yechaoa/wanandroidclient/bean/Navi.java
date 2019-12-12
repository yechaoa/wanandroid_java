package com.yechaoa.wanandroidclient.bean;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/26.
 * Describe :
 */
public class Navi {


    /**
     * articles : [{"apkLink":"","author":"小编",...,"type":0,"visible":1,"zan":0}]
     * cid : 272
     * name : 常用网站
     */

    public int cid;
    public String name;
    public List<ArticlesBean> articles;

    public static class ArticlesBean {
        /**
         * apkLink :
         * author : 小编
         * chapterId : 272
         * chapterName : 常用网站
         * collect : false
         * courseId : 13
         * desc :
         * envelopePic :
         * fresh : false
         * id : 1848
         * link : https://developers.google.cn/
         * niceDate : 2018-01-07
         * origin :
         * projectLink :
         * publishTime : 1515322795000
         * superChapterId : 0
         * superChapterName :
         * tags : []
         * title : Google开发者
         * type : 0
         * visible : 0
         * zan : 0
         */

        public String apkLink;
        public String author;
        public int chapterId;
        public String chapterName;
        public boolean collect;
        public int courseId;
        public String desc;
        public String envelopePic;
        public boolean fresh;
        public int id;
        public String link;
        public String niceDate;
        public String origin;
        public String projectLink;
        public long publishTime;
        public int superChapterId;
        public String superChapterName;
        public String title;
        public int type;
        public int visible;
        public int zan;
        public List<?> tags;

    }
}
