package data.testClassData;
import static io.restassured.RestAssured.get;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;

import java.io.IOException;
import java.util.List;

/**
 * Created by cuishuaishuai on 2018/11/3.
 */
public class test1 {
    @Test
    public void test1() throws IOException{
        String url="https://cnodejs.org/api/v1/topics";
        ValidatableResponse response=get(url).then();
        System.out.println(response.extract().body().asString());
        Response res=get(url);
        res.jsonPath().prettyPrint();
    }
    @Test
    public void test2(){
        Response response =get("https://api.tengyue360.com/api/content/document/encyclopedia?tag_id=111&document_type=1&pn=1&rn=20&_exps=%7B%7D&platform=2&device_type=3&app_id=1&os_version=8.200000&app_version_name=3.4.0&app_version=82&device_id=6FA310DC-8181-4C26-ADEF-371FF31FC557");
        response.then().statusCode(200);
        String isnew=response.andReturn().jsonPath().getString("data.list[0].is_new");
        assertEquals(isnew,"true");
    }
    @Test
    public void test3(){
        Response response =get("https://api.tengyue360.com/api/content/document/encyclopedia?tag_id=111&document_type=1&pn=1&rn=20&_exps=%7B%7D&platform=2&device_type=3&app_id=1&os_version=8.200000&app_version_name=3.4.0&app_version=82&device_id=6FA310DC-8181-4C26-ADEF-371FF31FC557");
        response.then().statusCode(200);
        String jsonStr=response.getBody().jsonPath().prettyPrint();
        List<String> dspnmeStr= from(jsonStr).getList("data.list.findAll { it.is_new == true }.title");
        for(String str:dspnmeStr){
            Assert.assertNotNull(str);
        }

    }
    @Test
    public void test4(){
        Response response=get("https://api.tengyue360.com/api/content/document/encyclopedia?tag_id=111&document_type=1&pn=1&rn=20&_exps=%7B%7D&platform=2&device_type=3&app_id=1&os_version=8.200000&app_version_name=3.4.0&app_version=82&device_id=6FA310DC-8181-4C26-ADEF-371FF31FC557");
        response.getBody().prettyPrint();
        Assert.assertEquals(response.andReturn().jsonPath().getString("data.list[1].price"),"0");
        //Assert.assertEquals(response.andReturn().jsonPath().getString("data.list[1].price"),"0"));
        response.then().body("data.list[0].title.length()",greaterThan(1));
        response.then().body("data.list[0].title.length()",lessThan(100));
        response.then().body("data.list[0].description",equalTo("做到这3点，让你的家长群“起死回生”"));
        response.then().body("data.list[1].description",endsWith("境"));
        response.then().body("data.list[1].description",contains("你"));
    }
    @Test
    public void test5(){
        String url = "https://api.tengyue360.com/api/content/document/encyclopedia?tag_id=111&document_type=1&pn=1&rn=20&_exps=%7B%7D&platform=2&device_type=3&app_id=1&os_version=8.200000&app_version_name=3.4.0&app_version=82&device_id=6FA310DC-8181-4C26-ADEF-371FF31FC557";
        Response response = get(url);
        List test=response.andReturn().jsonPath().getList("data.list");
        if(test.size()>0){
            for(int i=0;i<test.size();i++){
                String little=response.andReturn().jsonPath().getString("data.list[" + i + "].title");
                String litta=response.andReturn().jsonPath().getString("data.list[" + i + "].score");
                System.out.println(little+"111");
                System.out.println(litta+"2222");

            }
        }
    }
    @Test
    public void test6(){
        Long time=get("https://api.tengyue360.com/api/content/document/encyclopedia?tag_id=111&document_type=1&pn=1&rn=20&_exps=%7B%7D&platform=2&device_type=3&app_id=1&os_version=8.200000&app_version_name=3.4.0&app_version=82&device_id=6FA310DC-8181-4C26-ADEF-371FF31FC557").timeIn(TimeUnit.MILLISECONDS);
        System.out.print(time+"111");
    }
}
