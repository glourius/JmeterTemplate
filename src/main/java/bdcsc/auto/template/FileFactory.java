package bdcsc.auto.template;

/**
 * 生成文件的工厂类
 * Created by mawenrui on 2018/6/10.
 */
public class FileFactory {
    private FileTemplate template;
    public void createFile(String type, String fileUrl){
        if ("0".equals(type)) { // 生成全部文件
            template = new AllOfFile();
            template.createFile(fileUrl);
        } else if ("1".equals(type)){ // 生成脚本文件
            template = new XlsxFile();
            template.createFile(fileUrl);
        } else if ("2".equals(type)){ // 生成 jmx
            template = new JmxFile();
            template.createFile(fileUrl);
        } else if ("3".equals(type)){ // 生成 csv
            template = new CsvFile();
            template.createFile(fileUrl);
        }
    }
}
