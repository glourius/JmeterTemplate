package bdcsc.auto.utils;

import bdcsc.auto.config.Config;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * 条件判断类，用于判断输入参数，而生成不同的测试用例
 * Created by mawenrui on 2018/6/27.
 */
public class ConditionalJudgment {
    /**
     * 功能测试的入参检验
     * @param sheet 标签页
     */
    public static void functionCheckVaraibles(HSSFSheet sheet){
        String[] variables = Config.get("variables").split(",");
        String[] variablesTypes = Config.get("variables_type").split(",");
        String[] strCases = new String[]{"参数-为空","参数-为空格","参数-包含中文","参数-包含英文","参数-包含特殊字符","参数-缺省"};
        String demandName = Config.get("demand_name");
        String demandCode = Config.get("demand_code");

        // 功能测试的入参检验
        int count = sheet.getLastRowNum() + 1;
        for (int m = 0; m < variables.length; m++) {
            String variable = variables[m];
            String type = variablesTypes[m];
            for (int i = 0; i < strCases.length; i++) {
                HSSFRow rowNext = sheet.createRow(count);
                rowNext.createCell(0).setCellValue(demandCode + "-" + count);
                rowNext.createCell(1).setCellValue(demandName);
                rowNext.createCell(2).setCellValue(demandCode);
                rowNext.createCell(3).setCellValue("0");
                rowNext.createCell(4).setCellValue("功能测试");
                rowNext.createCell(5).setCellValue(variable + strCases[i]);
                rowNext.createCell(6).setCellValue("");
                rowNext.createCell(7).setCellValue(variable + strCases[i]);
                rowNext.createCell(8).setCellValue("400");
                rowNext.createCell(9).setCellValue("中");
                rowNext.createCell(10).setCellValue("");
                rowNext.createCell(11).setCellValue("");
                rowNext.createCell(12).setCellValue("");
                count++;
            }
            count = functionJudgment(sheet, variable, type, count);
        }
    }

    private static int functionJudgment(HSSFSheet sheet, String variable, String variablesType, int count) {
        String demandName = Config.get("demand_name");
        String demandCode = Config.get("demand_code");
        if ("mdn".equals(variable)) {
            String[] conditionals = {"mdn参数-mdn错误号码", "mdn参数-mdn异网号码", "mdn参数-mdn边界-10位",
                    "mdn参数-mdn边界-12位", "mdn参数-mdn边界-31位", "mdn参数-mdn边界-33位", "mdn参数-mdn为固网号码"};
            for (int i = 0; i < conditionals.length; i++) {
                HSSFRow rowNext = sheet.createRow(count);
                rowNext.createCell(0).setCellValue(demandCode + "-" + count);
                rowNext.createCell(1).setCellValue(demandName);
                rowNext.createCell(2).setCellValue(demandCode);
                rowNext.createCell(3).setCellValue("0");
                rowNext.createCell(4).setCellValue("功能测试");
                rowNext.createCell(5).setCellValue(conditionals[i]);
                rowNext.createCell(6).setCellValue("");
                rowNext.createCell(7).setCellValue(conditionals[i]);
                rowNext.createCell(8).setCellValue("400");
                rowNext.createCell(9).setCellValue("中");
                rowNext.createCell(10).setCellValue("");
                rowNext.createCell(11).setCellValue("");
                rowNext.createCell(12).setCellValue("");
                count++;
            }
        } else if ("type".equals(variable)) {
            String[] conditionals = {"type参数-错误（非clear/MD5）", "type参数-内容-大小写混合验证"};
            String[] results = {"400", "200"};
            for (int i = 0; i < conditionals.length; i++) {
                HSSFRow rowNext = sheet.createRow(count);
                rowNext.createCell(0).setCellValue(demandCode + "-" + count);
                rowNext.createCell(1).setCellValue(demandName);
                rowNext.createCell(2).setCellValue(demandCode);
                rowNext.createCell(3).setCellValue("0");
                rowNext.createCell(4).setCellValue("功能测试");
                rowNext.createCell(5).setCellValue(conditionals[i]);
                rowNext.createCell(6).setCellValue("");
                rowNext.createCell(7).setCellValue(conditionals[i]);
                rowNext.createCell(8).setCellValue(results[i]);
                rowNext.createCell(9).setCellValue("中");
                rowNext.createCell(10).setCellValue("");
                rowNext.createCell(11).setCellValue("");
                rowNext.createCell(12).setCellValue("");
                count++;
            }
        } else if ("date".equalsIgnoreCase(variablesType)) {
            String[] conditionals = {"参数-年月为197512", "参数-年月为197601", "参数-年月为205101",
                    "参数-年月为205012", "参数-位数为7位", "参数-位数为5位"};
            String[] results = {"400", "200", "400", "200", "400", "400"};
            for (int i = 0; i < conditionals.length; i++) {
                HSSFRow rowNext = sheet.createRow(count);
                rowNext.createCell(0).setCellValue(demandCode + "-" + count);
                rowNext.createCell(1).setCellValue(demandName);
                rowNext.createCell(2).setCellValue(demandCode);
                rowNext.createCell(3).setCellValue("0");
                rowNext.createCell(4).setCellValue("功能测试");
                rowNext.createCell(5).setCellValue(variable + conditionals[i]);
                rowNext.createCell(6).setCellValue("");
                rowNext.createCell(7).setCellValue(variable + conditionals[i]);
                rowNext.createCell(8).setCellValue(results[i]);
                rowNext.createCell(9).setCellValue("中");
                rowNext.createCell(10).setCellValue("");
                rowNext.createCell(11).setCellValue("");
                rowNext.createCell(12).setCellValue("");
                count++;
            }
        } else if ("datescope".equalsIgnoreCase(variable)) {
            String[] conditionals = {"参数-年月日为19751231,19760103", "参数-年月日为19760101,19760114",
                    "参数-年月日为20501230,20510101", "参数-年月日为20501221,20501231"};
            String[] results = {"400", "200", "400", "200"};
            for (int i = 0; i < conditionals.length; i++) {
                HSSFRow rowNext = sheet.createRow(count);
                rowNext.createCell(0).setCellValue(demandCode + "-" + count);
                rowNext.createCell(1).setCellValue(demandName);
                rowNext.createCell(2).setCellValue(demandCode);
                rowNext.createCell(3).setCellValue("0");
                rowNext.createCell(4).setCellValue("功能测试");
                rowNext.createCell(5).setCellValue(variable + conditionals[i]);
                rowNext.createCell(6).setCellValue("");
                rowNext.createCell(7).setCellValue(variable + conditionals[i]);
                rowNext.createCell(8).setCellValue(results[i]);
                rowNext.createCell(9).setCellValue("中");
                rowNext.createCell(10).setCellValue("");
                rowNext.createCell(11).setCellValue("");
                rowNext.createCell(12).setCellValue("");
                count++;
            }
        }
        return count;
    }

    public static void dataCheckVariables(HSSFSheet sheet){

    }

}
