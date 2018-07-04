package bdcsc.auto.template;

import bdcsc.auto.config.Config;
import bdcsc.auto.utils.FileUtil;
import bdcsc.auto.utils.MessageUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 生成jmx文件
 * Created by mawenrui on 2018/6/10.
 */
public class JmxFile implements FileTemplate{
    private static final String delimiter = Config.get("delimiter");
    private final String interfaceName = Config.get("demand_code").trim() + "_" + Config.get("interfaceName");
    private final String variables = Config.get("variables").trim();
    private final String[] commonParas = {"Product","Module","Method","apikey","tokenid"};
    private String[] commonStatus = {"为空","空格","大写","乱码","错值"};
    private Map<String, String> messages = MessageUtil.initMessage();
    private Map<String, Integer> codes = MessageUtil.initCode();

    @Override
    public void createFile(String fileUrl) {
        int sheetIndex = Integer.parseInt(Config.get("sheet_index"));
        // 读取xlsx脚本文件
        String xlsxUrl = Config.getResultsUrl() + interfaceName + ".xlsx";
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(xlsxUrl));
            HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            int lastRowNum = sheet.getLastRowNum();
            // 遍历每一行，获取测试项（4）
            List<HSSFRow> function = new ArrayList<>();
            List<HSSFRow> data = new ArrayList<>();
            for (int i = 2; i <= lastRowNum; i++){
                HSSFRow row = sheet.getRow(i);
                if ("功能测试".equals(row.getCell(4).getStringCellValue().trim())) {
                    function.add(row);
                } else if ("数据测试".equals(row.getCell(4).getStringCellValue().trim())) {
                    data.add(row);
                }
            }

            // 分别生成jmx文件
            functionJmx(function);
            dataJmx(data);
            performanceJmx();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 功能
    private void functionJmx(List<HSSFRow> rows) {
        String functionUrl = Config.getFunctionUrl() + interfaceName + ".csv";
        // 先自动生成常用固定参数的校验
        StringBuilder cache = new StringBuilder();
        cache.append(JmxTemplate.pre(functionUrl));
        threadGroup(cache, "1.功能性验证-正常值", 200, "status\":\"SUCCEED\"");

        int num = 2;
        String p = ".功能性验证-";
        for (int i = 0; i < commonParas.length; i++) {
            for (int j = 0; j < commonStatus.length; j++) {
                String para = commonParas[i];
                String status = commonStatus[j];
                String title = num + p + para + "参数-" + status;
                if ("tokenid".equals(para)) {
                    threadGroup(cache, title, codes.get(para + status), messages.get(para + status));
                } else {
                    threadGroup(cache, title, 404, messages.get(para + status));
                }
                num++;
            }
        }

        // TODO 还没有写输入参数的校验
        // 遍历每一行，获取测试项（4）、标题（5）、预期结果（8）、执行结果（10）
        for (int j = 0; j < rows.size(); j++) {
            HSSFRow row = rows.get(j);
            // 测试的标题
            String theme = row.getCell(5).getStringCellValue();
            if ("返回结果格式验证（xml,json)|日志验证|服务项编码验证".contains(theme)) {
                continue;
            }
            String title = num + p + theme;
            // 测试的预期结果（jmeter匹配的编码）
            int code = Integer.parseInt(row.getCell(8).getStringCellValue());
            // 测试的预期执行结果（jmeter匹配的文本）
            String result = row.getCell(10).getStringCellValue();
            threadGroup(cache, title, code, result);
            num++;
        }

        cache.append(JmxTemplate.lastJmx());

        //将数据写入到文件中
        String jmxUrl = Config.getFunctionUrl() + interfaceName + ".jmx";
        boolean flag = FileUtil.writeOut(cache.toString(), jmxUrl);
        if (flag) {
            System.out.println(jmxUrl + " 文件导出成功！");
        }

    }

    // 数据
    private void dataJmx(List<HSSFRow> rows) {
        String dataUrl = Config.getDataUrl() + interfaceName + ".csv";
        StringBuilder cache = new StringBuilder();
        cache.append(JmxTemplate.pre(dataUrl));
        threadGroup(cache, "1.数据验证-正常值", 200, "status\":\"SUCCEED\"");
        // TODO 这里只生成了一个jmx文件，并没有创建线程组


        cache.append(JmxTemplate.lastJmx());
        // 输出文件
        String jmxUrl = Config.getDataUrl() + interfaceName + ".jmx";
        boolean flag = FileUtil.writeOut(cache.toString(), jmxUrl);
        if (flag) {
            System.out.println(jmxUrl + " 文件导出成功！");
        }
    }

    // 性能
    private void performanceJmx() {
        String csvUrl = Config.getPerformanceUrl() + interfaceName + ".csv";
        StringBuilder cache = new StringBuilder();
        cache.append(JmxTemplate.pre(csvUrl));
        cache.append("<ThreadGroup guiclass=\"ThreadGroupGui\" testclass=\"ThreadGroup\" testname=\"1.并发400，一万调用量\" enabled=\"true\">\n" +
                "        <stringProp name=\"ThreadGroup.on_sample_error\">continue</stringProp>\n" +
                "        <elementProp name=\"ThreadGroup.main_controller\" elementType=\"LoopController\" guiclass=\"LoopControlPanel\" testclass=\"LoopController\" testname=\"循环控制器\" enabled=\"true\">\n" +
                "          <boolProp name=\"LoopController.continue_forever\">false</boolProp>\n" +
                "          <stringProp name=\"LoopController.loops\">30</stringProp>\n" +
                "        </elementProp>\n" +
                "        <stringProp name=\"ThreadGroup.num_threads\">400</stringProp>\n" +
                "        <stringProp name=\"ThreadGroup.ramp_time\">1</stringProp>\n" +
                "        <longProp name=\"ThreadGroup.start_time\">1421389364000</longProp>\n" +
                "        <longProp name=\"ThreadGroup.end_time\">1421389364000</longProp>\n" +
                "        <boolProp name=\"ThreadGroup.scheduler\">false</boolProp>\n" +
                "        <stringProp name=\"ThreadGroup.duration\"></stringProp>\n" +
                "        <stringProp name=\"ThreadGroup.delay\"></stringProp>\n" +
                "      </ThreadGroup>\n" +
                "      <hashTree>");
        cache.append("<HTTPSamplerProxy guiclass=\"HttpTestSampleGui\" testclass=\"HTTPSamplerProxy\" testname=\""+interfaceName+"接口\" enabled=\"true\">\n" +
                "          <elementProp name=\"HTTPsampler.Arguments\" elementType=\"Arguments\" guiclass=\"HTTPArgumentsPanel\" testclass=\"Arguments\" testname=\"用户定义的变量\" enabled=\"true\">\n" +
                "            <collectionProp name=\"Arguments.arguments\">");
        for (String variable : variables.split(",")) {
            cache.append("<elementProp name=\""+variable+"\" elementType=\"HTTPArgument\">\n" +
                    "                <boolProp name=\"HTTPArgument.always_encode\">true</boolProp>\n" +
                    "                <stringProp name=\"Argument.value\">${"+variable+"}</stringProp>\n" +
                    "                <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                    "                <boolProp name=\"HTTPArgument.use_equals\">true</boolProp>\n" +
                    "                <stringProp name=\"Argument.name\">"+variable+"</stringProp>\n" +
                    "              </elementProp>");
        }
        cache.append("</collectionProp>\n" +
                "          </elementProp>\n" +
                "          <stringProp name=\"HTTPSampler.domain\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.port\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.connect_timeout\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.response_timeout\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.protocol\">http</stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.contentEncoding\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.path\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.method\">GET</stringProp>\n" +
                "          <boolProp name=\"HTTPSampler.follow_redirects\">true</boolProp>\n" +
                "          <boolProp name=\"HTTPSampler.auto_redirects\">false</boolProp>\n" +
                "          <boolProp name=\"HTTPSampler.use_keepalive\">true</boolProp>\n" +
                "          <boolProp name=\"HTTPSampler.DO_MULTIPART_POST\">false</boolProp>\n" +
                "          <stringProp name=\"HTTPSampler.implementation\">HttpClient4</stringProp>\n" +
                "          <boolProp name=\"HTTPSampler.monitor\">false</boolProp>\n" +
                "          <stringProp name=\"HTTPSampler.embedded_url_re\"></stringProp>\n" +
                "        </HTTPSamplerProxy>\n" +
                "        <hashTree/>\n");
        cache.append(JmxTemplate.check(200, ""));
        cache.append(JmxTemplate.resultAndAssertion());
        cache.append(JmxTemplate.lastJmx());

        //将数据写入到文件中
        String jmxUrl = Config.getPerformanceUrl() + interfaceName + ".jmx";
        boolean flag = FileUtil.writeOut(cache.toString(), jmxUrl);
        if (flag) {
            System.out.println(jmxUrl + " 文件导出成功！");
        }
    }

    /**
     * 线程组拼接
     * @param cache 字符串
     * @param title 线程组标题
     * @param code 检查message的code
     * @param message 检查message的结果语句
     */
    private void threadGroup(StringBuilder cache, String title, int code, String message){
        cache.append("<ThreadGroup guiclass=\"ThreadGroupGui\" testclass=\"ThreadGroup\" testname=\""+title+"\" enabled=\"true\">\n" +
                "        <stringProp name=\"ThreadGroup.on_sample_error\">continue</stringProp>\n" +
                "        <elementProp name=\"ThreadGroup.main_controller\" elementType=\"LoopController\" guiclass=\"LoopControlPanel\" testclass=\"LoopController\" testname=\"循环控制器\" enabled=\"true\">\n" +
                "          <boolProp name=\"LoopController.continue_forever\">false</boolProp>\n" +
                "          <stringProp name=\"LoopController.loops\">1</stringProp>\n" +
                "        </elementProp>\n" +
                "        <stringProp name=\"ThreadGroup.num_threads\">1</stringProp>\n" +
                "        <stringProp name=\"ThreadGroup.ramp_time\">1</stringProp>\n" +
                "        <longProp name=\"ThreadGroup.start_time\">1421389364000</longProp>\n" +
                "        <longProp name=\"ThreadGroup.end_time\">1421389364000</longProp>\n" +
                "        <boolProp name=\"ThreadGroup.scheduler\">false</boolProp>\n" +
                "        <stringProp name=\"ThreadGroup.duration\"></stringProp>\n" +
                "        <stringProp name=\"ThreadGroup.delay\"></stringProp>\n" +
                "      </ThreadGroup>\n" +
                "      <hashTree>");
        cache.append("<HTTPSamplerProxy guiclass=\"HttpTestSampleGui\" testclass=\"HTTPSamplerProxy\" testname=\""+interfaceName+"接口\" enabled=\"true\">\n" +
                "          <elementProp name=\"HTTPsampler.Arguments\" elementType=\"Arguments\" guiclass=\"HTTPArgumentsPanel\" testclass=\"Arguments\" testname=\"用户定义的变量\" enabled=\"true\">\n" +
                "            <collectionProp name=\"Arguments.arguments\">");
        for (String variable : variables.split(",")) {
            if (title.contains(variable) && title.contains("缺省")) {
                cache.append("<elementProp name=\"\" elementType=\"HTTPArgument\">\n" +
                        "                <boolProp name=\"HTTPArgument.always_encode\">true</boolProp>\n" +
                        "                <stringProp name=\"Argument.value\">${"+variable+"}</stringProp>\n" +
                        "                <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                        "                <boolProp name=\"HTTPArgument.use_equals\">true</boolProp>\n" +
                        "                <stringProp name=\"Argument.name\"></stringProp>\n" +
                        "              </elementProp>");
            } else {
                cache.append("<elementProp name=\""+variable+"\" elementType=\"HTTPArgument\">\n" +
                        "                <boolProp name=\"HTTPArgument.always_encode\">true</boolProp>\n" +
                        "                <stringProp name=\"Argument.value\">${"+variable+"}</stringProp>\n" +
                        "                <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                        "                <boolProp name=\"HTTPArgument.use_equals\">true</boolProp>\n" +
                        "                <stringProp name=\"Argument.name\">"+variable+"</stringProp>\n" +
                        "              </elementProp>");
            }
        }
        cache.append("</collectionProp>\n" +
                "          </elementProp>\n" +
                "          <stringProp name=\"HTTPSampler.domain\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.port\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.connect_timeout\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.response_timeout\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.protocol\">http</stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.contentEncoding\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.path\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.method\">GET</stringProp>\n" +
                "          <boolProp name=\"HTTPSampler.follow_redirects\">true</boolProp>\n" +
                "          <boolProp name=\"HTTPSampler.auto_redirects\">false</boolProp>\n" +
                "          <boolProp name=\"HTTPSampler.use_keepalive\">true</boolProp>\n" +
                "          <boolProp name=\"HTTPSampler.DO_MULTIPART_POST\">false</boolProp>\n" +
                "          <stringProp name=\"HTTPSampler.implementation\">HttpClient4</stringProp>\n" +
                "          <boolProp name=\"HTTPSampler.monitor\">false</boolProp>\n" +
                "          <stringProp name=\"HTTPSampler.embedded_url_re\"></stringProp>\n" +
                "        </HTTPSamplerProxy>\n" +
                "        <hashTree/>\n");
        cache.append(JmxTemplate.check(code, message));
        cache.append(JmxTemplate.resultAndAssertion());
    }
}
