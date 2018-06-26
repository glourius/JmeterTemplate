package bdcsc.auto.template;

import bdcsc.auto.config.Config;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * 生成脚本文件
 * Created by mawenrui on 2018/6/10.
 */
public class XlsxFile implements FileTemplate{

    @Override
    public void createFile(String fileUrl) {
        // 生成测试用例的路径
        String interfaceName = Config.get("interfaceName").trim();
        String code = Config.get("demand_code").trim();
        String resultXlsx = fileUrl + interfaceName + ".xlsx";
        // 生成测试脚本
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 获取接口参数个数（即计算参数名称的个数）、参数名称、参数类型
//        String[] variables = Config.get("variables").split(",");
//        String[] types = Config.get("variables_type").split(",");
//        if (variables.length != types.length) {
//            System.out.println("配置文件中的参数(variables)个数与类型(variables_type)个数不匹配！");
//            return;
//        }

        HSSFSheet sheet = workbook.createSheet("测试脚本");
        // 循环生成每一行的的用例
        createRow(workbook, sheet);

        // 设置首行（标题行）
        String[] headerNames = new String[]{"用例编号","需求模块","对应需求编号","子需求编号",
                "测试项","标题","前提条件","执行步骤","预期结果",
                "优先级","执行结果","bug","bug状态",};
        HSSFRow header = sheet.createRow(0);
        // 配置单元格格式
        HSSFCellStyle cellStyle = baseStyle(workbook);
        cellStyle.setFillForegroundColor(IndexedColors.ORANGE.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置单元格
        HSSFCell cell;
        for (int i = 0; i < headerNames.length; i++){
            cell = header.createCell(i);
            cell.setCellValue(headerNames[i]);
            cell.setCellStyle(cellStyle);
        }
        // TODO 测试用例里面还没写数据测试相关






        FileOutputStream out = null;
        try {
            out = new FileOutputStream(resultXlsx);
            // 保存excel文件
            workbook.write(out);
            System.out.println(resultXlsx + " 测试用例生成成功！");
        } catch (FileNotFoundException e) {
            System.out.println("文件\""+ resultXlsx +"\"未找到！");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("生成文件\"" + resultXlsx + "\"时出错！");
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createRow(HSSFWorkbook workbook, HSSFSheet sheet){
        // 设置单元格数据
        setCellValue(sheet);

        // 设置所有单元格的格式
        HSSFCellStyle cellStyle = baseStyle(workbook);
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Iterator<Cell> cellIterator = rowIterator.next().cellIterator();
            while (cellIterator.hasNext()) {
                cellIterator.next().setCellStyle(cellStyle);
            }
        }

    }

    private HSSFCellStyle baseStyle(HSSFWorkbook workbook){
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        // 设置cell单元格的边框
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        return cellStyle;
    }

    private void setCellValue(HSSFSheet sheet){
        String[] variables = Config.get("variables").split(",");
        // 需求名称、编号
        String demandName = Config.get("demand_name");
        String demandCode = Config.get("demand_code");
        // 每个入参固定的测试用例
        String[] strCases = new String[]{"参数-为空","参数-为空格","参数-包含中文","参数-包含英文","参数-包含特殊符号","参数-缺省"};

        // 第一行
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

        // 功能测试
        int count = 1;
        for (String variable : variables) {
            for (int i = 0; i < strCases.length; i++) {
                count++;
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
            }
        }

        // 数据测试

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
