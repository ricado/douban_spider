package com.creatchen.spider.pageProcessor;

import com.creatchen.spider.config.UrlType;
import com.creatchen.spider.config.DoubanSite;
import com.creatchen.spider.util.BloomFilter;
import org.assertj.core.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * 豆瓣小组爬虫页面分析
 * Created by creatchen on 2018/1/19.
 */
public class GroupPageProcessor implements PageProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(GroupPageProcessor.class);

    private static final Logger URL_LOG = LoggerFactory.getLogger("URL_INFO");
    /**
     * 一个小时的毫秒数
     */
    private static final int HOUR_MS = 60 * 60 * 1000;
    private static BloomFilter bloomFilter = new BloomFilter();
    /**
     * 配置浏览器信息
     */
    private static Site site = DoubanSite.newDoubanSite().setRetryTimes(3).setSleepTime(1000);

    public static void main(String[] args) {
        Spider.create(new GroupPageProcessor()).thread(1).addUrl("https://www.douban.com/group/?start=0").run();
    }

    public static void runSpider() {
        bloomFilter.initBloomFilter("E:/log/douban_spider");
        Spider.create(new GroupPageProcessor()).thread(1).addUrl("https://www.douban.com/group/?start=0").run();
    }

    public void process(Page page) {
        UrlType urlType = UrlType.urlType(page.getUrl().toString());

        switch (urlType) {
            case MY_GROUP:
                //我的小组话题列表
                hanlerMyGroup(page);
                break;
            case GROUP_DISCUSSION:
                // 小组话题讨论
                handlerGroupDiscussion(page);
                break;
            case GROUP_TOPIC:
                // 小组话题内容解析
                // handlerGroupTopic(page);
                break;
            default:
                LOG.info("未知 URL = {}", page.getUrl().toString());
        }
    }

    /**
     * 我的小组解析
     *
     * @param page page
     */
    private void hanlerMyGroup(Page page) {
        List<Selectable> selectables = page.getHtml().xpath("//html/body/div[3]/div[1]/div/div[1]/div[1]/table/tbody/tr").nodes();
        // 是否下一页
        boolean next = true;
        // 获取列表信息
        for (int i = 0; i < selectables.size(); i++) {
            Selectable selectable = selectables.get(i);
            String title = selectable.xpath("//td[1]/a/@title").get();
            String url = selectable.xpath("//td[1]/a/@href").get();
            String time = selectable.xpath("//td[3]/@title").get();
            LOG.info("title = {},url = {},time = {}", title, url, time);

            try {
                Calendar topicCalendar = Calendar.getInstance();
                topicCalendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time));
                Calendar now = Calendar.getInstance();
                now.add(Calendar.HOUR_OF_DAY, -1);
                // 一个小时
                if (now.before(topicCalendar)) {
                    //  page.addTargetRequest(url);
                } else {
                    next = false;
                }
            } catch (ParseException e) {
                next = false;
                LOG.error("title = {},url = {},time = {},e = {}", title, url, time, e);
            }
        }
        if (next) {
            try {
                String nextUrl = page.getHtml().xpath("//html/body/div[3]/div[1]/div/div[1]/div[2]/span[4]/a/@href").get();
                if (!Strings.isNullOrEmpty(nextUrl) && !nextUrl.equals("null")) {
                    page.addTargetRequest(nextUrl);
                }
            } catch (Exception e) {
                LOG.info("没有下一页 URL = {}", page.getUrl().get());
            }
        }
    }

    /**
     * 小组话题内容解析
     *
     * @param page page
     */
    private void handlerGroupTopic(Page page) {
        LOG.info("小组话题 暂不处理");
    }

    /**
     * 小组讨论列表解析
     *
     * @param page page
     */
    private void handlerGroupDiscussion(Page page) {

    }

    public Site getSite() {
        return this.site;
    }

}
