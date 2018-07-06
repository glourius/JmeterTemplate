# JmeterTemplate
#### 用于生成测试用例、jmeter的测试脚本以及测试脚本使用到的测试数据

**一、操作步骤**
* 使用 mvn package -DskipTests 命令生成jar包
* 将command包下面的两个运行脚本拷贝到与jar包、config配置文件夹同级别的目录下
* 文件的级别大致为
    * 最外层文件夹
        * run.sh
        * run.bat
        * jmeter.jar
        * config文件夹
            * 配置文件.properties   


**二、以 .properties 结尾的配置文件的解析(范本请见bdcsc.auto.examples文件夹中的文件)**
* 服务项名称和编码，即需求列表中对应需求的服务项名称和编码
* 接口的输入参数要用 "," 分隔
* 接口输入参数的类型(variables_type)需要与上面的(variables)一一对应
* 接口输入参数的类型暂时支持三种：string、date、datescop
    * string类型没有过多处理，但是会对mdn、type命名的参数添加额外的用例
    * date类型的格式暂定为 "201807" ，会有位数的判断用例，以及超出时间范围的判断用例
    * datescop类型的格式暂定为 "20501230,20510101"，这里只添加了时间范围的用例
* delimiter 即测试数据(csv)的数据分隔符
* correct_data 是正确的入参测试数据，注意这里的分隔符要使用上面定义的delimiter分隔
* tokenUrl 是通过浏览器获取tokenid的url地址
* excel表格中的sheet页下标从0开始



