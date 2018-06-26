package bdcsc.auto.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by mawenrui on 2018/6/12.
 */
public class FileUtil {
    public static boolean writeOut(String content, String fileUrl) {
        FileOutputStream fos = null;
        FileChannel fc = null;
        try {
            // 获取文件输出流
            fos = new FileOutputStream(fileUrl);
            // 获取输出管道
            fc = fos.getChannel();
            ByteBuffer buffer = ByteBuffer.wrap(content.getBytes(Charset.forName("utf-8")));
            // 通过管道写到文件
            fc.write(buffer);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.out.println("数据导出到 " + fileUrl + " 文件时出错！");
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fc != null) {
                    fc.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String param(int num, String text){
        String tmp = text;
        switch (num) {
            case 0:
                tmp = "";
                break;
            case 1:
                tmp = " ";
                break;
            case 2:
                if (Character.isUpperCase(text.charAt(0))){
                    tmp = text.toLowerCase();
                } else  {
                    tmp = text.toUpperCase();
                }
                break;
            case 3:
                tmp = text + "%2f";
                break;
            case 4:
                tmp = "test";
                break;
        }
        return tmp;
    }
}
