package com.common.util;

import com.common.config.CustomThreadPoolExecutor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;

/**
 * @Desc
 */
public class Test {
    public static void main(String[] args) throws Exception {
        //yunche();
        wallpaper();
    }

    private static void yunche() throws Exception {
        String baseUrl = "https://www.1718k.com";
        String url = "https://www.1718k.com/files/article/html/1/1459/";
        Document document = Jsoup.parse(new URL(url), 30000);
        Element element = document.getElementById("chapterList");
        Elements elements = element.getElementsByTag("li");
        elements.forEach(e -> {
            try {
                String title = e.text();
                String path = e.getElementsByTag("a").eq(0).attr("href");
                String content = getContent(baseUrl, path, true);
                FileUtils.uploadString("\r" + title + "\r\n" + content + "\r\n", "E:\\outPut\\yunche1.txt");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
    }

    private static String getContent(String baseUrl, String url, boolean first) throws Exception {
        Document doc = Jsoup.parse(new URL(baseUrl + url), 30000);
        Element textContent = doc.getElementById("TextContent");
        Elements p = textContent.getElementsByTag("p");
        if (first) {
            Element remove = p.remove(0);
        } else {
            Element remove = p.remove(p.size() - 1);
        }
        String content = p.html();
        String nextUrl = doc.getElementById("next_url").getElementsByTag("a").eq(0).attr("href");
        if (first) {
            if (StringUtils.isNotBlank(nextUrl)) {
                return content + "\r\n" + getContent(baseUrl, nextUrl, false);
            } else {
                return content;
            }
        } else {
            return content;
        }
    }

    private static void wallpaper() {
        try {
            String url = "https://pic.netbian.com/";
            for (int i = 2; i < 1065; i++) {
                int finalI = i;
                CustomThreadPoolExecutor.getInstance().execute(() -> {
                    try {
                        getImg(url, url + "index_" + finalI + ".html");
                    } catch (Exception e) {

                    }
                });
            }
        } catch (Exception e) {
        }
    }

    private static void getImg(String baseUrl, String url) throws Exception {
        Document document = Jsoup.parse(new URL(url), 5000);
        Elements slist = document.getElementsByClass("slist");
        Elements elements = slist.get(0).getElementsByTag("li");
        elements.forEach(e -> {
            try {
                String img = e.getElementsByTag("img").attr("src");
                FileUtils.downloadFile(baseUrl + img,"E:\\outPut\\wallpaper1\\");
            } catch (Exception ex) {
            }
        });
    }
}
