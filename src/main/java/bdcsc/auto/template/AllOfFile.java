package bdcsc.auto.template;

/**
 * 用于生成 测试脚本、jmx、csv文件
 * Created by mawenrui on 2018/6/10.
 */
public class AllOfFile implements FileTemplate{
    @Override
    public void createFile(String fileUrl) {
        // 生成脚本文件
        new XlsxFile().createFile(fileUrl);
        // 生成 jmx
        new JmxFile().createFile(fileUrl);
        // 生成 csv
        new CsvFile().createFile(fileUrl);
    }
}
