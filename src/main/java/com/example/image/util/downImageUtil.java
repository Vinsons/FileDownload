package com.example.image.util;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @ClassName downImage
 * @Description
 * @Author 黄文聪
 * @Date 2019-11-20 14:42
 * @Version 1.0
 **/
public class downImageUtil {
    public static void download(String url, String localPath) {
        URL url1 = null;
        try {
            url1 = new URL(url);
            int lastgang = url.lastIndexOf("/");
            int begin = url.lastIndexOf(".");
            String names = url.substring(lastgang + 1, begin);
            String suffix = url.substring(url.lastIndexOf("."));
            String fileName = names + suffix;


            File file = new File(localPath);
            File[] listFile = file.listFiles();
            int count = 0;
            for (File file1 : listFile) {
                if (fileName.equals(file1.getName())) {
                    count++;
                }
            }
            if (count > 0) {
                localPath = localPath + "/" + names + "(" + count + ")" + suffix;
            } else {
                localPath = localPath + "/" + fileName;
            }
            DataInputStream dataInputStream = new DataInputStream(url1.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(new File(localPath));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(buffer);
            System.out.println(encode);
            fileOutputStream.write(outputStream.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException es) {
            es.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String url = "http://pic.cssn.cn/tp/tp_tpqh/201911/W020191114592042611208.jpg";
        String localPath = "H:/123/";
        download(url, localPath);

    }
}
