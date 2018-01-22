package com.creatchen.spider.util;

import java.io.*;
import java.util.stream.Stream;

/**
 * 文件的相关操作
 * Created by creatchen on 2017/3/1.
 */
public class FileUtil {
    /**
     * 获取文本文件的每一行
     *
     * @param filepath 文件路径
     * @return 由文本文件的每一行组成的字符串数组
     * @throws FileNotFoundException
     */
    public static String[] readFileLines(String filepath) throws FileNotFoundException {
        File file = new File(filepath);
        BufferedReader reader = null;
//        System.out.println("以行为单位读取文件内容，一次读一整行：");
        reader = new BufferedReader(new FileReader(file));
        String[] lines = reader.lines().toArray(String[]::new);
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void main(String[] args) {
        String path = FileUtil.class.getClassLoader().getResource("").getPath();
        System.out.println("path = " + path);
    }

    public static boolean writeToFile(String filePath, Stream<String> stream) throws IOException {
        File file = new File(filePath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        stream.forEach(str -> {
            try {
                writer.newLine();
                writer.write(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
        return true;
    }
}
