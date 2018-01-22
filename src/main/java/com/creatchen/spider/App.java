package com.creatchen.spider;

import com.creatchen.spider.pageProcessor.GroupPageProcessor;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        // 加载布隆过滤器
        GroupPageProcessor.runSpider();
    }
}
