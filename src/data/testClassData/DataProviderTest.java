package data.testClassData;

import data.testClassData.DataProviderClass;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class DataProviderTest {
    @Test(dataProvider="LoginTest",dataProviderClass=DataProviderClass.class)
    public void doTestLogin(String version ,String device_id) {
        System.out.println("数据源LoginTest:"+version);
        System.out.println("数据源LoginTest:"+device_id);
        ValidatableResponse res = given().queryParam("loginSource", "4")
                .queryParam("platform", "2")
                .queryParam("device_type", "3")
                .queryParam("app_id", "1")
                .queryParam("os_version", version)
                .queryParam("device_id", device_id).when()
                .post("https://api.tengyue360.com/api/user/signin/mobilelogin").then();
        res.assertThat().statusCode(200);

    }

    @Test(dataProvider="dataproviderMy",dataProviderClass=DataProviderClass.class)
    public void doUser(String datas){
        System.out.println(datas);
        ValidatableResponse response = get(datas).then();
        response.assertThat().body(matchesJsonSchemaInClasspath("jsonSchema.json"));
    }

}
