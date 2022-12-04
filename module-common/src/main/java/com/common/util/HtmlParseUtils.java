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
        elements.forEach(e->{
            try {
                String title = e.text();
                String path = e.getElementsByTag("a").eq(0).attr("href");
                Document doc = Jsoup.parse(new URL(baseUrl+path), 30000);
                Element textContent = doc.getElementById("TextContent");
                Elements p = textContent.getElementsByTag("p");
                Element remove = p.remove(0);
                String content = p.html();
                FileUtils.uploadString(title+"\r\n"+content+"\r\n","F:\\outPut\\yunche.txt");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

    }
}
