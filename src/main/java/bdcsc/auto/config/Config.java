package bdcsc.auto.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 配置信息的处理
 * Created by mawenrui on 2018/6/10.
 */
public class Config {
    private static Properties props = new Properties();
    private static String resultsUrl = "";
    private static String functionUrl = "";
    private static String dataUrl = "";
    private static String performanceUrl = "";
    private static String separator = File.separator;


    /**
     * 初始化加载配置信息
     * @param rootUrl 配置文件的路径
     */
    public static void init(String rootUrl){
        try {
            // 配置文件的路径，加载配置文件
            String configFile = rootUrl + "conf.properties";
            props.load(new InputStreamReader(new FileInputStream(configFile), "UTF-8")); // TODO 这里处理了从配置文件获取中文出现乱码的问题

            // 生成结果的文件夹路径
            resultsUrl = rootUrl + "result" + separator;
            File resultsDir = new File(resultsUrl);
            if (!resultsDir.exists()) {
                resultsDir.mkdir();
            }
            functionUrl = resultsUrl + "功能" + separator;
            dataUrl = resultsUrl + "数据" + separator;
            performanceUrl = resultsUrl + "性能" + separator;
            File fanctionDir = new File(functionUrl);
            File dataDir = new File(dataUrl);
            File performanceDir = new File(performanceUrl);
            if (!fanctionDir.exists()){
                fanctionDir.mkdir();
            }
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }
            if (!performanceDir.exists()){
                performanceDir.mkdir();
            }
        } catch (IOException e) {
            System.out.println("配置文件\"conf.properties\"未找到，请将配置文件放置在jar包的同级别目录中！");
            e.printStackTrace();
        }
    }

    /**
     * 获取配置参数
     * @param key key值
     * @return value值
     */
    public static String get(String key) {
        return props.getProperty(key);
    }

    public static String getResultsUrl() {
        return resultsUrl;
    }

    public static String getFunctionUrl() {
        return functionUrl;
    }

    public static String getDataUrl() {
        return dataUrl;
    }

    public static String getPerformanceUrl() {
        return performanceUrl;
    }

}
