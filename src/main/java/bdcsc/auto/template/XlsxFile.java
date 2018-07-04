package bdcsc.auto.template;

import bdcsc.auto.config.Config;
import bdcsc.auto.utils.ConditionalJudgment;
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
        String resultXlsx = fileUrl + code + "_" + interfaceName + ".xlsx";
        // 生成测试脚本
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 获取接口参数个数（即计算参数名称的个数）、参数名称、参数类型
        String[] variables = Config.get("variables").split(",");
        String[] types = Config.get("variables_type").split(",");
        if (variables.length != types.length) {
            System.out.println("配置文件中的参数(variables)个数与类型(variables_type)个数不匹配！");
            return;
        }

        HSSFSheet sheet = workbook.createSheet("测试脚本");
        // 循环生成每一行的的用例
        createRow(workbook, sheet);
        // 设置首行（标题行）
        String[] headerNames = new String[]{"用例编号","需求模块","对应需求编号","子需求编号",
                "测试项","标题","前提条件","执行步骤","预期结果",
                "优先级","执行结果","bug","bug状态",};
        HSSFRow header = sheet.createRow(0);
        // 配置单元格格式，设置单元格背景颜色和边框
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

        // 输出文件
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(resultXlsx);
            // 保存excel文件
            workbook.write(out);
            workbook.close();
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
        // 固定的，不用改
        XlsxTemplate.functionBegin(sheet);
        // TODO 功能测试的入参
        ConditionalJudgment.functionCheckVaraibles(sheet);
        // 添加功能测试的末尾三行，固定的，不用改
        XlsxTemplate.functionEnd(sheet);

        // 数据测试，固定的，不用改
        XlsxTemplate.dataBegin(sheet);
        // TODO 数据测试入参
        ConditionalJudgment.dataCheckVariables(sheet);

        // 性能测试，固定的，不用改
        XlsxTemplate.performance(sheet);
    }
}
