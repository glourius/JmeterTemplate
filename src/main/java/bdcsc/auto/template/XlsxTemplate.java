package bdcsc.auto.template;

import bdcsc.auto.config.Config;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * 测试用例文件模版
 * Created by mawenrui on 2018/7/3.
 */
public class XlsxTemplate {
    // 需求名称、编号
    private static final String demandName = Config.get("demand_name");
    private static final String demandCode = Config.get("demand_code");

    public static void functionBegin(HSSFSheet sheet){

        // 第一行，常规参数
        HSSFRow row = sheet.createRow(1);
        row.createCell(0).setCellValue(demandCode + "-1");
        row.createCell(1).setCellValue(demandName);
        row.createCell(2).setCellValue(demandCode);
        row.createCell(3).setCellValue("0");
        row.createCell(4).setCellValue("功能测试");
        row.createCell(5).setCellValue("常用固定参数校验");
        row.createCell(6).setCellValue("");
        row.createCell(7).setCellValue("常用固定参数校验");
        row.createCell(8).setCellValue("402/404/406");
        row.createCell(9).setCellValue("高");
        row.createCell(10).setCellValue("");
        row.createCell(11).setCellValue("");
        row.createCell(12).setCellValue("");

        // 第二行，参数正常值
        HSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue(demandCode + "-2");
        row2.createCell(1).setCellValue(demandName);
        row2.createCell(2).setCellValue(demandCode);
        row2.createCell(3).setCellValue("0");
        row2.createCell(4).setCellValue("功能测试");
        row2.createCell(5).setCellValue("参数正常值测试");
        row2.createCell(6).setCellValue("");
        row2.createCell(7).setCellValue("参数正常值测试");
        row2.createCell(8).setCellValue("200");
        row2.createCell(9).setCellValue("高");
        row2.createCell(10).setCellValue("");
        row2.createCell(11).setCellValue("");
        row2.createCell(12).setCellValue("");
    }

    /**
     * 为测试用例添加功能测试的最后三行
     * @param sheet 标签页
     */
    public static void functionEnd(HSSFSheet sheet){
        String[] args = {"返回结果格式验证（xml,json)", "日志验证", "服务项编码验证"};
        String[] result = {"200", "正确", "正确"};

        int begin = sheet.getLastRowNum() + 1;
        int end = begin + 3;
        int count = 0;
        for (int i = begin; i < end; i++) {
            HSSFRow row = sheet.createRow(i);
            row.createCell(0).setCellValue(demandCode + "-" + i);
            row.createCell(1).setCellValue(demandName);
            row.createCell(2).setCellValue(demandCode);
            row.createCell(3).setCellValue("0");
            row.createCell(4).setCellValue("功能测试");
            row.createCell(5).setCellValue(args[count]);
            row.createCell(6).setCellValue("");
            row.createCell(7).setCellValue(args[count]);
            row.createCell(8).setCellValue(result[count]);
            row.createCell(9).setCellValue("高");
            row.createCell(10).setCellValue("");
            row.createCell(11).setCellValue("");
            row.createCell(12).setCellValue("");
            count++;
        }
    }

    public static void dataBegin(HSSFSheet sheet){
        int num = sheet.getLastRowNum() + 1;
        HSSFRow row2 = sheet.createRow(num);
        row2.createCell(0).setCellValue(demandCode + "-" + num);
        row2.createCell(1).setCellValue(demandName);
        row2.createCell(2).setCellValue(demandCode);
        row2.createCell(3).setCellValue("0");
        row2.createCell(4).setCellValue("数据测试");
        row2.createCell(5).setCellValue("参数正常值测试");
        row2.createCell(6).setCellValue("");
        row2.createCell(7).setCellValue("参数正常值测试");
        row2.createCell(8).setCellValue("200");
        row2.createCell(9).setCellValue("高");
        row2.createCell(10).setCellValue("");
        row2.createCell(11).setCellValue("");
        row2.createCell(12).setCellValue("");
    }

    public static void performance(HSSFSheet sheet){
        // 性能测试
        int lastNum = sheet.getLastRowNum() + 1;
        HSSFRow lastRow = sheet.createRow(lastNum);
        lastRow.createCell(0).setCellValue(demandCode + "-" + lastNum);
        lastRow.createCell(1).setCellValue(demandName);
        lastRow.createCell(2).setCellValue(demandCode);
        lastRow.createCell(3).setCellValue("0");
        lastRow.createCell(4).setCellValue("性能测试");
        lastRow.createCell(5).setCellValue("并发400 1万调用量");
        lastRow.createCell(6).setCellValue("");
        lastRow.createCell(7).setCellValue("并发400 1万调用量");
        lastRow.createCell(8).setCellValue("200");
        lastRow.createCell(9).setCellValue("高");
        lastRow.createCell(10).setCellValue("");
        lastRow.createCell(11).setCellValue("");
        lastRow.createCell(12).setCellValue("");
    }
}
