package com.example.image.util;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            if (!file.exists()) {
                file.mkdirs();
            }
            File[] listFile = file.listFiles();
            int count = 0;
            for (File file1 : listFile) {
                while (file1.getName().equals(fileName)) {
                    count++;
                    fileName = names + "(" + count + ")" + suffix;
                    continue;
                }
            }
            if (count > 0) {
                localPath = localPath + "/" + names + "(" + count + ")" + suffix;
            } else {
                localPath = localPath + "/" + fileName;
            }
            DataInputStream dataInputStream = new DataInputStream(url1.openStream());
            File files = new File(localPath);
            FileOutputStream fileOutputStream = new FileOutputStream(files, true);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(buffer);
            System.out.println(encode);
            System.out.println(localPath);
            System.out.println(names + suffix);
            fileOutputStream.write(outputStream.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException es) {
            es.printStackTrace();
        }
    }

    public static String checkZipName(String localPath, File[] listFile, String names, String suffix, Integer count, String fileName) {
        if (0 == count) {
            fileName = names + suffix;
        } else {
            fileName = names + "(" + count + ")" + suffix;
        }
        for (File file : listFile) {
            if (file.getName().equals(fileName)) {
                count++;
                fileName = names + "(" + count + ")" + suffix;
            }
        }
        File file = new File(localPath + fileName);
        if (file.isFile()) {
            checkZipName(localPath, listFile, names, suffix, count, fileName);
        } else {
            return fileName;
        }

        return fileName;

    }

    public static String checkFileName(List<String> list, String names, String suffix, Integer count) {
         String fileName;

        if (0 == count) {
            fileName = names + suffix;
        } else {
            fileName = names + "(" + count + ")" + suffix;
        }
        boolean flag = list.contains(fileName);
        if (flag) {
            count++;
            checkFileName(list, names, suffix, count);
        }
        return fileName;
    }

    public static void main(String[] args) {
        String url = "http://pic2.sc.chinaz.com/files/pic/pic9/201911/zzpic21266.jpg";
        String localPath = "H:/123/";
//        download(url, localPath);
        String fileName = "zzpic21266.jpg";
        String fileName1 = "zzpic21266(1).jpg";
//        File file = new File(localPath);
//        File[] files = file.listFiles();
//        String zzpic21266 = checkZipName(localPath,files, "zzpic21266", ".jpg", 0,fileName);
//        System.out.println(zzpic21266);
        List<String> list =new ArrayList<>();
        list.add(fileName);
        list.add(fileName1);
        String fileName2 = checkFileName(list, "zzpic21266", ".jpg", 0);
        System.out.println(fileName2);
    }
}
