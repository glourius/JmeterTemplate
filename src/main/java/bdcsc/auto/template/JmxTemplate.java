package bdcsc.auto.template;

import bdcsc.auto.config.Config;
import org.apache.commons.lang.StringEscapeUtils;

/**
 * Created by mawenrui on 2018/6/11.
 */
public class JmxTemplate {
    private static String interfaceName = Config.get("interfaceName");
    private static String variables = Config.get("variables");
    private static String domain = Config.get("domain");
    private static String port = Config.get("port");
    private static String delimiter = Config.get("delimiter");
    private static String fileEncoding = Config.get("fileEncoding");
    public static String preJmx(){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<jmeterTestPlan version=\"1.2\" properties=\"2.9\" jmeter=\"3.0 r1743807\">\n" +
                "  <hashTree>\n" +
                "    <TestPlan guiclass=\"TestPlanGui\" testclass=\"TestPlan\" testname=\"测试计划\" enabled=\"true\">\n" +
                "      <stringProp name=\"TestPlan.comments\"></stringProp>\n" +
                "      <boolProp name=\"TestPlan.functional_mode\">false</boolProp>\n" +
                "      <boolProp name=\"TestPlan.serialize_threadgroups\">true</boolProp>\n" +
                "      <elementProp name=\"TestPlan.user_defined_variables\" elementType=\"Arguments\" guiclass=\"ArgumentsPanel\" testclass=\"Arguments\" testname=\"用户定义的变量\" enabled=\"true\">\n" +
                "        <collectionProp name=\"Arguments.arguments\"/>\n" +
                "      </elementProp>\n" +
                "      <stringProp name=\"TestPlan.user_define_classpath\"></stringProp>\n" +
                "    </TestPlan>\n" +
                "    <hashTree>\n" +
                "      <CSVDataSet guiclass=\"TestBeanGUI\" testclass=\"CSVDataSet\" testname=\"CSV Data Set Config\" enabled=\"true\">";
    }

    public static String lastJmx(){
        return "<ResultCollector guiclass=\"StatVisualizer\" testclass=\"ResultCollector\" testname=\"聚合报告\" enabled=\"true\">\n" +
                "        <boolProp name=\"ResultCollector.error_logging\">false</boolProp>\n" +
                "        <objProp>\n" +
                "          <name>saveConfig</name>\n" +
                "          <value class=\"SampleSaveConfiguration\">\n" +
                "            <time>true</time>\n" +
                "            <latency>true</latency>\n" +
                "            <timestamp>true</timestamp>\n" +
                "            <success>true</success>\n" +
                "            <label>true</label>\n" +
                "            <code>true</code>\n" +
                "            <message>true</message>\n" +
                "            <threadName>true</threadName>\n" +
                "            <dataType>true</dataType>\n" +
                "            <encoding>false</encoding>\n" +
                "            <assertions>true</assertions>\n" +
                "            <subresults>true</subresults>\n" +
                "            <responseData>false</responseData>\n" +
                "            <samplerData>false</samplerData>\n" +
                "            <xml>false</xml>\n" +
                "            <fieldNames>false</fieldNames>\n" +
                "            <responseHeaders>false</responseHeaders>\n" +
                "            <requestHeaders>false</requestHeaders>\n" +
                "            <responseDataOnError>false</responseDataOnError>\n" +
                "            <saveAssertionResultsFailureMessage>false</saveAssertionResultsFailureMessage>\n" +
                "            <assertionsResultsToSave>0</assertionsResultsToSave>\n" +
                "            <bytes>true</bytes>\n" +
                "            <threadCounts>true</threadCounts>\n" +
                "          </value>\n" +
                "        </objProp>\n" +
                "        <stringProp name=\"filename\"></stringProp>\n" +
                "      </ResultCollector>\n" +
                "      <hashTree/>\n" +
                "    </hashTree>\n" +
                "  </hashTree>\n" +
                "</jmeterTestPlan>\n";
    }

    public static String resultAndAssertion(){
        return "<ResultCollector guiclass=\"ViewResultsFullVisualizer\" testclass=\"ResultCollector\" testname=\"察看结果树\" enabled=\"true\">\n" +
                "          <boolProp name=\"ResultCollector.error_logging\">false</boolProp>\n" +
                "          <objProp>\n" +
                "            <name>saveConfig</name>\n" +
                "            <value class=\"SampleSaveConfiguration\">\n" +
                "              <time>true</time>\n" +
                "              <latency>true</latency>\n" +
                "              <timestamp>true</timestamp>\n" +
                "              <success>true</success>\n" +
                "              <label>true</label>\n" +
                "              <code>true</code>\n" +
                "              <message>true</message>\n" +
                "              <threadName>true</threadName>\n" +
                "              <dataType>true</dataType>\n" +
                "              <encoding>false</encoding>\n" +
                "              <assertions>true</assertions>\n" +
                "              <subresults>true</subresults>\n" +
                "              <responseData>false</responseData>\n" +
                "              <samplerData>false</samplerData>\n" +
                "              <xml>true</xml>\n" +
                "              <fieldNames>false</fieldNames>\n" +
                "              <responseHeaders>false</responseHeaders>\n" +
                "              <requestHeaders>false</requestHeaders>\n" +
                "              <responseDataOnError>false</responseDataOnError>\n" +
                "              <saveAssertionResultsFailureMessage>false</saveAssertionResultsFailureMessage>\n" +
                "              <assertionsResultsToSave>0</assertionsResultsToSave>\n" +
                "              <bytes>true</bytes>\n" +
                "              <threadCounts>true</threadCounts>\n" +
                "            </value>\n" +
                "          </objProp>\n" +
                "          <stringProp name=\"filename\"></stringProp>\n" +
                "        </ResultCollector>\n" +
                "        <hashTree/>\n" +
                "        <ResultCollector guiclass=\"AssertionVisualizer\" testclass=\"ResultCollector\" testname=\"实际输出与预期结果对比\" enabled=\"true\">\n" +
                "          <boolProp name=\"ResultCollector.error_logging\">false</boolProp>\n" +
                "          <objProp>\n" +
                "            <name>saveConfig</name>\n" +
                "            <value class=\"SampleSaveConfiguration\">\n" +
                "              <time>true</time>\n" +
                "              <latency>true</latency>\n" +
                "              <timestamp>true</timestamp>\n" +
                "              <success>true</success>\n" +
                "              <label>true</label>\n" +
                "              <code>true</code>\n" +
                "              <message>true</message>\n" +
                "              <threadName>true</threadName>\n" +
                "              <dataType>true</dataType>\n" +
                "              <encoding>false</encoding>\n" +
                "              <assertions>true</assertions>\n" +
                "              <subresults>true</subresults>\n" +
                "              <responseData>false</responseData>\n" +
                "              <samplerData>false</samplerData>\n" +
                "              <xml>true</xml>\n" +
                "              <fieldNames>false</fieldNames>\n" +
                "              <responseHeaders>false</responseHeaders>\n" +
                "              <requestHeaders>false</requestHeaders>\n" +
                "              <responseDataOnError>false</responseDataOnError>\n" +
                "              <saveAssertionResultsFailureMessage>false</saveAssertionResultsFailureMessage>\n" +
                "              <assertionsResultsToSave>0</assertionsResultsToSave>\n" +
                "              <bytes>true</bytes>\n" +
                "              <threadCounts>true</threadCounts>\n" +
                "            </value>\n" +
                "          </objProp>\n" +
                "          <stringProp name=\"filename\"></stringProp>\n" +
                "        </ResultCollector>\n" +
                "        <hashTree/>\n" +
                "      </hashTree>";
    }

    public static String pre(String csvUrl){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<jmeterTestPlan version=\"1.2\" properties=\"2.9\" jmeter=\"3.0 r1743807\">\n" +
                "  <hashTree>\n" +
                "    <TestPlan guiclass=\"TestPlanGui\" testclass=\"TestPlan\" testname=\"测试计划\" enabled=\"true\">\n" +
                "      <stringProp name=\"TestPlan.comments\"></stringProp>\n" +
                "      <boolProp name=\"TestPlan.functional_mode\">false</boolProp>\n" +
                "      <boolProp name=\"TestPlan.serialize_threadgroups\">true</boolProp>\n" +
                "      <elementProp name=\"TestPlan.user_defined_variables\" elementType=\"Arguments\" guiclass=\"ArgumentsPanel\" testclass=\"Arguments\" testname=\"用户定义的变量\" enabled=\"true\">\n" +
                "        <collectionProp name=\"Arguments.arguments\"/>\n" +
                "      </elementProp>\n" +
                "      <stringProp name=\"TestPlan.user_define_classpath\"></stringProp>\n" +
                "    </TestPlan>\n" +
                "    <hashTree>\n" +
                "      <CSVDataSet guiclass=\"TestBeanGUI\" testclass=\"CSVDataSet\" testname=\"CSV Data Set Config\" enabled=\"true\">\n" +
                "        <stringProp name=\"delimiter\">"+delimiter+"</stringProp>\n" +
                "        <stringProp name=\"fileEncoding\">"+fileEncoding+"</stringProp>\n" +
                "        <stringProp name=\"filename\">"+csvUrl+"</stringProp>\n" +
                "        <boolProp name=\"quotedData\">false</boolProp>\n" +
                "        <boolProp name=\"recycle\">true</boolProp>\n" +
                "        <stringProp name=\"shareMode\">shareMode.all</stringProp>\n" +
                "        <boolProp name=\"stopThread\">true</boolProp>\n" +
                "        <stringProp name=\"variableNames\">Product,Module,Method,apikey,tokenid,"+variables+"</stringProp>\n" +
                "      </CSVDataSet>\n" +
                "      <hashTree/>\n" +
                "      <ConfigTestElement guiclass=\"HttpDefaultsGui\" testclass=\"ConfigTestElement\" testname=\""+interfaceName+"接口\" enabled=\"true\">\n" +
                "        <elementProp name=\"HTTPsampler.Arguments\" elementType=\"Arguments\" guiclass=\"HTTPArgumentsPanel\" testclass=\"Arguments\" testname=\"用户定义的变量\" enabled=\"true\">\n" +
                "          <collectionProp name=\"Arguments.arguments\"/>\n" +
                "        </elementProp>\n" +
                "        <stringProp name=\"HTTPSampler.domain\">"+domain+"</stringProp>\n" +
                "        <stringProp name=\"HTTPSampler.port\">"+port+"</stringProp>\n" +
                "        <stringProp name=\"HTTPSampler.connect_timeout\"></stringProp>\n" +
                "        <stringProp name=\"HTTPSampler.response_timeout\"></stringProp>\n" +
                "        <stringProp name=\"HTTPSampler.protocol\"></stringProp>\n" +
                "        <stringProp name=\"HTTPSampler.contentEncoding\"></stringProp>\n" +
                "        <stringProp name=\"HTTPSampler.path\">/restful/${Product}/${Module}/${Method}/${apikey}/${tokenid}</stringProp>\n" +
                "        <stringProp name=\"HTTPSampler.concurrentPool\">4</stringProp>\n" +
                "      </ConfigTestElement>\n" +
                "      <hashTree/>\n" +
                "      <Arguments guiclass=\"ArgumentsPanel\" testclass=\"Arguments\" testname=\"用户定义的变量-Module\" enabled=\"true\">\n" +
                "        <collectionProp name=\"Arguments.arguments\">\n" +
                "          <elementProp name=\"Product\" elementType=\"Argument\">\n" +
                "            <stringProp name=\"Argument.name\">Product</stringProp>\n" +
                "            <stringProp name=\"Argument.value\">${Product}</stringProp>\n" +
                "            <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "          </elementProp>\n" +
                "          <elementProp name=\"Module\" elementType=\"Argument\">\n" +
                "            <stringProp name=\"Argument.name\">Module</stringProp>\n" +
                "            <stringProp name=\"Argument.value\">${module}</stringProp>\n" +
                "            <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "          </elementProp>\n" +
                "          <elementProp name=\"Method\" elementType=\"Argument\">\n" +
                "            <stringProp name=\"Argument.name\">Method</stringProp>\n" +
                "            <stringProp name=\"Argument.value\">${Method}</stringProp>\n" +
                "            <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "          </elementProp>\n" +
                "          <elementProp name=\"apikey\" elementType=\"Argument\">\n" +
                "            <stringProp name=\"Argument.name\">apikey</stringProp>\n" +
                "            <stringProp name=\"Argument.value\">${apikey}</stringProp>\n" +
                "            <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "          </elementProp>\n" +
                "          <elementProp name=\"tokenid\" elementType=\"Argument\">\n" +
                "            <stringProp name=\"Argument.name\">tokenid</stringProp>\n" +
                "            <stringProp name=\"Argument.value\">${tokenid}</stringProp>\n" +
                "            <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "          </elementProp>\n" +
                "        </collectionProp>\n" +
                "      </Arguments>\n" +
                "      <hashTree/>\n";
    }

    public static String check(int code, String message){
        String tmp = StringEscapeUtils.escapeHtml(message);

        if (code != 200) {
            return "        <ResponseAssertion guiclass=\"AssertionGui\" testclass=\"ResponseAssertion\" testname=\"检查message\" enabled=\"true\">\n" +
                    "          <collectionProp name=\"Asserion.test_strings\">\n" +
                    "            <stringProp name=\"723337899\">&quot;code&quot;:" + code + "</stringProp>\n" +
                    "            <stringProp name=\"-305413130\">" + tmp + "</stringProp>\n" +
                    "          </collectionProp>\n" +
                    "          <stringProp name=\"Assertion.test_field\">Assertion.response_data</stringProp>\n" +
                    "          <boolProp name=\"Assertion.assume_success\">true</boolProp>\n" +
                    "          <intProp name=\"Assertion.test_\">2</intProp>\n" +
                    "          <intProp name=\"Assertion.test_type\">2</intProp>\n" +
                    "        </ResponseAssertion>\n" +
                    "        <hashTree/>";
        } else {
            return "        <ResponseAssertion guiclass=\"AssertionGui\" testclass=\"ResponseAssertion\" testname=\"检查message\" enabled=\"true\">\n" +
                    "          <collectionProp name=\"Asserion.test_strings\">\n" +
                    "            <stringProp name=\"723337899\">&quot;code&quot;:200</stringProp>\n" +
                    "            <stringProp name=\"-305413130\">&quot;status&quot;:&quot;SUCCEED&quot;</stringProp>\n" +
                    "          </collectionProp>\n" +
                    "          <stringProp name=\"Assertion.test_field\">Assertion.response_data</stringProp>\n" +
                    "          <boolProp name=\"Assertion.assume_success\">false</boolProp>\n" +
                    "          <intProp name=\"Assertion.test_\">2</intProp>\n" +
                    "          <intProp name=\"Assertion.test_type\">2</intProp>\n" +
                    "        </ResponseAssertion>\n" +
                    "        <hashTree/>";
        }
    }
}
