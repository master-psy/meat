package com.common.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;

public class HtmlParseUtils {
    public static void main(String[] args) throws Exception {
        String baseUrl = "https://www.1718k.com";
        String url = "https://www.1718k.com/files/article/html/1/1459/";

        Document document = Jsoup.parse(new URL(url), 30000);
        Element element = document.getElementById("chapterList");
        Elements elements = element.getElementsByTag("li");
        elements.forEach(e -> {
            try {
                String title = e.text();
                String path = e.getElementsByTag("a").eq(0).attr("href");
                Document doc = Jsoup.parse(new URL(baseUrl + path), 30000);
                Element textContent = doc.getElementById("TextContent");
                Elements p = textContent.getElementsByTag("p");
                Element remove = p.remove(0);
                String content = p.html();
                //下一页
                String nextUrl = doc.getElementById("next_url").getElementsByTag("a").eq(0).attr("href");
                String content1 = getContent(baseUrl, nextUrl);
                FileUtils.uploadString("\r"+title + "\r\n" + content + "\r\n"+content1+"\r\n", "E:\\outPut\\yunche.txt");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

    }

    private static String getContent(String baseUrl,String url) throws Exception{
        Document doc = Jsoup.parse(new URL(baseUrl + url), 30000);
        Element textContent = doc.getElementById("TextContent");
        Elements p = textContent.getElementsByTag("p");
        Element remove = p.remove(0);
        String content = p.html();
        return content;
    }
}
