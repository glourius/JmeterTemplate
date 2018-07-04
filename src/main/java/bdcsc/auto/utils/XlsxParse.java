package bdcsc.auto.utils;

import bdcsc.auto.config.Config;
import org.apache.poi.hssf.usermodel.HSSFRow;

import java.util.List;

/**
 * 解析脚本文件
 * Created by mawenrui on 2018/7/4.
 */
public class XlsxParse {

    private static final String delimiter = Config.get("delimiter");
    private static final String[] variables = Config.get("variables").split(",");
    private static final String[] correctData = Config.get("correct_data").split(delimiter);

    // 解析功能测试
    public static void parseFunction(StringBuilder cache, List<HSSFRow> rows, StringBuilder begin){
        for (HSSFRow r : rows) {
            String theme = r.getCell(5).getStringCellValue();
            cache.append(begin);
            cache.append(checkTheme(theme));
            cache.append("\n");
        }
    }

    // 检验测试用例中的标题
    private static String checkTheme(String theme) {
        String result = Config.get("correct_data");
        int length = variables.length;
        // 第一步，获取标题中参数在variables中的下标
        int index = -1;
        for (int i = 0; i < length; i++) {
            if (variables[i].equals(theme.substring(0, theme.indexOf("参数")))) {
                index = i;
            }
        }
        // 第二步，获取检验环境下所对应的数据
        String data = purification(theme);

        // 第三步，截取正确数据中的参数
        for (int j = 0; j < length; j++) {
            if (j == index) {
                result += data + ",";
            } else {
                result += correctData[j];
            }
        }

        return result.substring(0, result.length() - 1);
    }

    private static String purification(String theme){
        if (theme.contains("为空")) {
            return "";
        } else if (theme.contains("为空格")) {
            return " ";
        } else if (theme.contains("包含中文")) {
            return "测试";
        } else if (theme.contains("包含英文")) {
            return "test";
        } else if (theme.contains("包含特殊字符")) {
            return "%2f";
        } else if (theme.contains("缺省")) {
            return "";
        } else if (theme.contains("非clear/MD5")) {
            return "testMd5";
        } else if (theme.contains("错误号码")) {
            return "23333333333";
        } else if (theme.contains("异网号码")) {
            return "18333333333";
        } else if (theme.contains("10位")) {
            return "1777777777";
        } else if (theme.contains("12位")) {
            return "177777777777";
        } else if (theme.contains("31位")) {
            return "2946B65B2D059BA5098BC7C77B5C723";
        } else if (theme.contains("33位")) {
            return "2946B65B2D059BA5098BC7C77B5C723AB";
        } else if (theme.contains("固网号码")) {
            return "01082196633";
        } else if (theme.contains("年月为") || theme.contains("年月日为")) {
            return theme.substring(theme.lastIndexOf("为") + 1);
        } else if (theme.contains("7位")) {
            return "2018077";
        } else if (theme.contains("5位")) {
            return "20180";
        } else if (theme.contains("type") && theme.contains("大小写混合验证")) {
            return "Clear";
        } else {
            return "";
        }
    }

}
