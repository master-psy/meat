package com.service.reptile;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashMap;
import java.util.Map;

public class BookProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        Map<String, Object> extras = page.getRequest().getExtras();
        String level = extras.get("level").toString();

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        String url = "https://www.ddyueshu.com/";
//        String url = "https://www.1718k.com/files/article/html/1/1459/";
        Request request = new Request(url);
        Map<String, Object> extraMap = new HashMap<>();
        extraMap.put("level","index");
        request.setExtras(extraMap);
        Spider.create(new BookProcessor())
                .addRequest(request)
                .thread(5)
                .start();

    }
}
