package com.common.util;

import java.io.FileOutputStream;
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
            FileOutputStream fileOutputStream = new FileOutputStream(fileUrl);
            fileOutputStream.write(message.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        uploadString(" 砸瓦鲁多", "F:\\outPut\\a.txt");
    }


}
