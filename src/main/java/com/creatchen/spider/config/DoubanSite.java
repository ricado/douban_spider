package com.creatchen.spider.config;

import us.codecraft.webmagic.Site;

/**
 * Created by creatchen on 2018/1/22.
 */
public class DoubanSite {

    public static Site newDoubanSite() {
        return Site.me()
                .addHeader("Host", "www.douban.com")
                .addHeader("Referer", "https://www.douban.com/")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/57.0")
                .addCookie("bid", "gDRylPCFzGs")
                .addCookie(" __utma", "30149280.1677663196.1516247605.1516581929.1516603999.14")
                .addCookie(" __utmz=30149280.1516603999.14.5.utmcsr=baidu|utmccn=(organic)|utmcmd", "organic")
                .addCookie(" ll", "118282")
                .addCookie(" _pk_ref.100001.8cb4", "%5B%22%22%2C%22%22%2C1516603995%2C%22https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DSvjgN-tDkogaOMT4yoW3HDQvVu-noGlvDhuTkGA8vFi%26ck%3D2957.1.89.150.176.151.174.148%26shh%3Dwww.baidu.com%26sht%3Dmonline_3_dg%26wd%3D%26eqid%3D8243ad510001a8d1000000065a658a59%22%5D")
                .addCookie(" _pk_id.100001.8cb4", "5c9f94f466f41024.1516247635.13.1516604027.1516581924.")
                .addCookie(" ps", "y")
                .addCookie(" dbcl2", "154297606:Ei/3b/Nu2Bk")
                .addCookie(" push_noty_num", "0")
                .addCookie(" push_doumail_num", "0")
                .addCookie(" __utmv", "30149280.15429")
                .addCookie(" ap", "1")
                .addCookie(" ct", "y")
                .addCookie(" __utmc", "30149280")
                .addCookie(" _pk_ses.100001.8cb4", "*")
                .addCookie(" __utmb", "30149280.13.6.1516604028184")
                .addCookie(" __utmt", "1")
                .addCookie(" ck", "dsq2");
    }

}
