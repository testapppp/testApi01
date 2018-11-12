package ApiTest;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.get;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.assertEquals;
public class newsList {
    String url = "https://api.tengyue360.com/api/content/document/encyclopedia?tag_id=111&document_type=1&pn=1&rn=20&_exps=%7B%7D&platform=2&device_type=3&app_id=1&os_version=8.200000&app_version_name=3.4.0&app_version=82&device_id=6FA310DC-8181-4C26-ADEF-371FF31FC557";
    @Test
    public void test1() throws IOException {
        /*通过JSONSCHEMA校验json格式以及类似是否正常通过工具https://jsonschema.net/可以生成*/
        String url = "https://api.tengyue360.com/api/content/document/encyclopedia?tag_id=111&document_type=1&pn=1&rn=20&_exps=%7B%7D&platform=2&device_type=3&app_id=1&os_version=8.200000&app_version_name=3.4.0&app_version=82&device_id=6FA310DC-8181-4C26-ADEF-371FF31FC557";
        ValidatableResponse response = get(url).then();
        System.out.println(response.extract().body().asString());
        //https://jsonschema.net/#/editor
        response.assertThat().body(matchesJsonSchemaInClasspath("jsonSchema.json"));
    }
    @Test
    public void test2() {
        Response response = get("https://api.tengyue360.com/api/content/document/encyclopedia?tag_id=111&document_type=1&pn=1&rn=20&_exps=%7B%7D&platform=2&device_type=3&app_id=1&os_version=8.200000&app_version_name=3.4.0&app_version=82&device_id=6FA310DC-8181-4C26-ADEF-371FF31FC557");
        response.then().statusCode(200);
        String isnew=response.andReturn().jsonPath().getString("data.list[0].is_new");
        assertEquals(isnew,"true");

    }

    @Test
    public void test3() {
        //请求的URL
        Response response = get(url);//发起get请求，并获取响应
        //验证状态是否是200
        response.then().statusCode(200);
        //打印接口返回的JSON结果
        String jsonStr = response.getBody().jsonPath().prettyPrint();
        List<String> dspnameStr = from(jsonStr).getList("data.list.findAll { it.is_new == true }.title");//得到所有的is_new=true的list数组元素，并获取其中titel的值,放在一个集合里
        for (String str : dspnameStr) {//遍历这个集合，获取每个title的值
            Assert.assertNotNull(str);//断言，title的值不能为NULL，但是可以是空字符串
        }
    }

    @Test
    public void test4() {
        Response response = get("https://api.tengyue360.com/api/content/document/encyclopedia?tag_id=111&document_type=1&pn=1&rn=20&_exps=%7B%7D&platform=2&device_type=3&app_id=1&os_version=8.200000&app_version_name=3.4.0&app_version=82&device_id=6FA310DC-8181-4C26-ADEF-371FF31FC557");
        response.getBody().prettyPrint();
        Assert.assertEquals(response.andReturn().jsonPath().getString("data.list[1].price"),"0");
        response.then().body("data.list[0].title.length()", greaterThan(1));//大于比较
        response.then().body("data.list[0].title.length()", lessThan(100));//小于
        //response.then().body("data.list[0].title.length()", equalTo(1)); //是否等于
        response.then().body("data.list[0].description", equalTo("做到这3点，让你的家长群“起死回生”"));
        response.then().body("data.list[1].description", endsWith("境"));
        response.then().body("data.list[0].description", containsString("你")); //查找字符串
    }

    @Test
    public void test6() {
        String url = "https://api.tengyue360.com/api/content/document/encyclopedia?tag_id=111&document_type=1&pn=1&rn=20&_exps=%7B%7D&platform=2&device_type=3&app_id=1&os_version=8.200000&app_version_name=3.4.0&app_version=82&device_id=6FA310DC-8181-4C26-ADEF-371FF31FC557";
        Response response = get(url);
        List test = response.andReturn().jsonPath().getList("data.list");
        if (test.size() > 0) {
            for (int i = 0; i < test.size(); i++) {
                String title = response.andReturn().jsonPath().getString("data.list[" + i + "].title");
                String titlea = response.andReturn().jsonPath().getString("data.list[" + i + "].score");
                response.then().body("data.list["+i+"].title.length()", greaterThan(1));//大于比较

                System.out.println(title+"1111");
            }
        }
    }
    @Test
    //获取响应的时间
    public void test7() {
        Long time=get("https://api.tengyue360.com/api/content/document/encyclopedia?tag_id=111&document_type=1&pn=1&rn=20&_exps=%7B%7D&platform=2&device_type=3&app_id=1&os_version=8.200000&app_version_name=3.4.0&app_version=82&device_id=6FA310DC-8181-4C26-ADEF-371FF31FC557").timeIn(TimeUnit.MILLISECONDS);
        System.out.print(time+"dddddd");
    }
}
