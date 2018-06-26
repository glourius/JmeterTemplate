package bdcsc.auto.template;

import bdcsc.auto.config.Config;
import bdcsc.auto.utils.FileUtil;
import bdcsc.auto.utils.TokenUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 生成 csv 文件
 * Created by mawenrui on 2018/6/10.
 */
public class CsvFile implements FileTemplate{
    private String delimiter = Config.get("delimiter");
    private String product = Config.get("product");
    private String module = Config.get("module");
    private String interfaceName = Config.get("interfaceName");
    private String apikey = Config.get("apikey");
    private String method = "";
    private String correctData = Config.get("correct_data");
    private String tokenid = "";

    CsvFile(){
        super();
        if ("get".equals(interfaceName.substring(0, 3))) {
            method = interfaceName.substring(3,4).toLowerCase() + interfaceName.substring(4);
        } else {
            method = interfaceName;
        }
        tokenid = TokenUtil.getToken();
    }

    @Override
    public void createFile(String fileUrl) {
        String[] commonParams = {product, module, method, apikey, tokenid};
        int sheetIndex = Integer.parseInt(Config.get("sheet_index"));
        // 读取xlsx脚本文件
        String xlsxUrl = Config.getResultsUrl() + interfaceName + ".xlsx";
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(xlsxUrl));
            HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            int lastRowNum = sheet.getLastRowNum();
            // 遍历每一行，获取测试项（4）、标题（5）、预期结果（8）
            List<HSSFRow> function = new ArrayList<>();
            List<HSSFRow> data = new ArrayList<>();
            for (int i = 1; i <= lastRowNum; i++) {
                HSSFRow row = sheet.getRow(i);
                if ("功能测试".equals(row.getCell(4).getStringCellValue().trim())) {
                    function.add(row);
                } else if ("数据测试".equals(row.getCell(4).getStringCellValue().trim())) {
                    data.add(row);
                }
            }

            functionCsv(function, commonParams);
            dataCsv(data);
            performanceCsv();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 功能
    private void functionCsv(List<HSSFRow> rows, String[] commonParams){
        StringBuilder cache = new StringBuilder();
        // 生成固定参数的数据
        cache.append(product).append(delimiter).append(module).append(delimiter)
                .append(method).append(delimiter).append(apikey).append(delimiter)
                .append(tokenid).append(".json").append(delimiter)
                .append(correctData).append("\n");
        for (int i = 0; i < commonParams.length; i++) {
            for (int j = 0; j < 5; j++) {
                String tmp = FileUtil.param(j, commonParams[i]);
                for (int k = 0; k < commonParams.length; k++) {
                    if (k == i) {
                        cache.append(tmp).append(delimiter);
                    } else {
                        cache.append(commonParams[k]).append(delimiter);
                    }
                }
                cache.append(".json").append(delimiter).append(correctData).append("\n");
            }
        }

        // TODO 还没写生成输入参数的数据





        //将数据写入到文件中
        String functionUrl = Config.getFunctionUrl() + interfaceName + ".csv";
        boolean flag = FileUtil.writeOut(cache.toString(), functionUrl);
        if (flag) {
            System.out.println(functionUrl + " 文件导出成功！");
        }
    }

    // 数据
    private void dataCsv(List<HSSFRow> rows){
        StringBuilder cache = new StringBuilder();
        // 生成固定参数的数据
        cache.append(product).append(delimiter).append(module).append(delimiter)
                .append(method).append(delimiter).append(apikey).append(delimiter)
                .append(tokenid).append(".json").append(delimiter)
                .append(correctData).append("\n");
        // TODO 还没有写数据测试的测试数据






        //将数据写入到文件中
        String dataUrl = Config.getDataUrl() + interfaceName + ".csv";
        boolean flag = FileUtil.writeOut(cache.toString(), dataUrl);
        if (flag) {
            System.out.println(dataUrl + " 文件导出成功！");
        }
    }

    // 性能
    private void performanceCsv(){
        StringBuilder cache = new StringBuilder();
        cache.append(product).append(delimiter).append(module).append(delimiter)
                .append(method).append(delimiter).append(apikey).append(delimiter)
                .append(tokenid).append(".json").append(delimiter)
                .append(correctData);
        //将数据写入到文件中
        String csvUrl = Config.getPerformanceUrl() + Config.get("interfaceName") + ".csv";
        boolean flag = FileUtil.writeOut(cache.toString(), csvUrl);
        if (flag) {
            System.out.println(csvUrl + " 文件导出成功！");
        }
    }
}