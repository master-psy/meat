package com.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @author PanShanYou
 */
public class FileUtils {

    /**
     * 字符串写在本地
     */
    public static void uploadString(String message, String fileUrl) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileUrl, true);
            fileOutputStream.write(message.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param urlString
     * @param targetUrl "E:\outPut\wallpaper1\"
     */
    public static void downloadFile(String urlString,String targetUrl) {
        try {

            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            //byte[] bs = new byte[1024];
            int len = 0;
            String fileName = targetUrl + System.currentTimeMillis() + ".jpg";
            File file = new File(fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            while ((len = inputStream.read()) != -1) {
                fileOutputStream.write(len);
                //fileOutputStream.write(bs, 0, len);
            }
            fileOutputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        //uploadString(" 砸瓦鲁多", "E:\\outPut\\a.txt");
        downloadFile("https://pic.netbian.com//uploads/allimg/221202/002011-16699116112302.jpg","E:\\outPut\\wallpaper1\\");
    }


}
