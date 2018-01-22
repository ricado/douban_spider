package com.creatchen.spider.config;

/**
 * Created by creatchen on 2018/1/22.
 */
public enum UrlType {
    MY_GROUP,
    GROUP_DISCUSSION,
    GROUP_TOPIC,
    UNKNOW;

    public static UrlType urlType(String url) {
        if (url.startsWith("https://www.douban.com/group/?start=")) {
            return MY_GROUP;
        } else if (url.startsWith("https://www.douban.com/group/498004/discussion?start=")) {
            return GROUP_DISCUSSION;
        } else if (url.startsWith("https://www.douban.com/group/topic/")) {
            return GROUP_TOPIC;
        } else {
            return UNKNOW;
        }
    }

}
