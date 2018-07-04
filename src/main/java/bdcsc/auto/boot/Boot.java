package bdcsc.auto.boot;

import bdcsc.auto.config.Config;
import bdcsc.auto.template.FileFactory;

import java.io.File;
import java.util.Date;

/**
 * 主要启动类
 * Created by mawenrui on 2018/6/9.
 */
public class Boot {
    public static void main(String[] args) {
        String separator = File.separator;
        Date begin = new Date();
        if (args.length != 0 && "help".equals(args[0])) {
            System.out.println("参数为0时，自动生成所有文件；");
            System.out.println("参数为1时，自动生成脚本文件；");
            System.out.println("参数为2时，自动生成jmx文件；");
            System.out.println("参数为3时，自动生成csv文件；");
            return;
        }

        System.out.println("程序启动……");
        // 获取当前项目(或者jar包)的绝对路径
        String product = "/Users/mawenrui/Desktop/";
//        String product = System.getProperty("user.dir") + separator;
        System.out.println("正在读取 " + product + " 文件夹下的配置文件…………");
        // 获取当前路径下的后缀为properties
        File[] files = new File(product).listFiles();



        int count = 0;
        FileFactory factory = new FileFactory();
        // 当入参为空时，默认生成全部结果
        if (args.length == 0){
            for (File f : files) {
                String path = f.getPath();
                if (".properties".equals(path.substring(path.length() - 11))) {
                    // 初始化配置信息
                    Config.init(product, path);
                    // 生成测试脚本
                    factory.createFile("0", Config.getResultsUrl());
                    count++;
                }
            }
        } else {
            for (File f : files) {
                String path = f.getPath();
                if (".properties".equals(path.substring(path.length() - 11))) {
                    // 初始化配置信息
                    Config.init(product, path);
                    // 生成测试脚本
                    factory.createFile(args[0], Config.getResultsUrl());
                    count++;
                }
            }

        }
        System.out.println("程序结束……");
        System.out.println("共生成 " + count + " 个接口用例");
        Date end = new Date();
        System.out.println("程序耗费时间：" + (end.getTime() - begin.getTime()) + " ms");
    }

    private static void creating(String url){


    }
}
