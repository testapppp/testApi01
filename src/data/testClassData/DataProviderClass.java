package data.testClassData;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
    //无指定数据名称，默认使用方法名
    //标记一种方法来提供测试方法的数据
    @DataProvider
    public static Object[][] LoginTest(){
        return new Object[][]{
                {"8.40000","6FA310DC-8181-4C26-ADEF-371FF31FC557"},
                {"8.60000","6FA310DC-8181-4C26-ADEF-371FF31FC553"},
                {"8.70000","6FA310DC-8181-4C26-ADEF-371FF31FC554"}

        };
    }
    //指定名称
    @DataProvider(name="dataproviderMy")
    public static Object[][] dataProvider1(){
        return new Object[][]{
                {"https://api.tengyue360.com/api/content/document/encyclopedia?tag_id=111&document_type=1&pn=1&rn=20&_exps=%7B%7D&platform=2&device_type=3&app_id=1&os_version=8.200000&app_version_name=3.4.0&app_version=82&device_id=6FA310DC-8181-4C26-ADEF-371FF31FC557"}
        };
    }
}