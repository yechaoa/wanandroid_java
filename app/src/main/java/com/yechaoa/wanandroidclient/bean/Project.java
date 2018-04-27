package com.yechaoa.wanandroidclient.bean;

import java.util.List;

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 * <p>
 * Created by yechao on 2018/4/27.
 * Describe :
 */
public class Project {

    /**
     * data : [{"children":[],"courseId":13,"id":294,"name":"完整项目","order":145000,"parentChapterId":293,"visible":0},{"children":[],"courseId":13,"id":310,"name":"下拉刷新","order":145002,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":312,"name":"富文本编辑器","order":145003,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":314,"name":"RV列表动效","order":145004,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":316,"name":"系统源码分析","order":145005,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":323,"name":"动画","order":145007,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":324,"name":"组件化","order":145008,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":325,"name":"PickerView","order":145009,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":327,"name":"ShapeView","order":145010,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":328,"name":"文件下载","order":145011,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":330,"name":"OCR","order":145012,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":331,"name":"TextView","order":145013,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":333,"name":"性能优化","order":145014,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":336,"name":"键盘","order":145015,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":337,"name":"快应用","order":145016,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":338,"name":"日历","order":145017,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":339,"name":"K线图","order":145018,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":340,"name":"硬件相关","order":145019,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":344,"name":"Fragment","order":145020,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":347,"name":"图层引导","order":145021,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":357,"name":"表格类","order":145022,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":358,"name":"项目基础功能","order":145023,"parentChapterId":293,"visible":1}]
     * errorCode : 0
     * errorMsg :
     */

    public int errorCode;
    public String errorMsg;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * children : []
         * courseId : 13
         * id : 294
         * name : 完整项目
         * order : 145000
         * parentChapterId : 293
         * visible : 0
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
