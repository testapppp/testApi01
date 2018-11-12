package datatest;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class TestDataDriver {
    @Test /*测试类*/
    @Parameters({"version", "device_id"})
    public void testLogin(String version, String device_id) throws Exception {
        ValidatableResponse res = given().queryParam("platform", "2")
                .queryParam("device_type", "3")
                .queryParam("app_id", "1")
                .queryParam("os_version", version)
                .queryParam("device_id", device_id).when()
                .post("https://api.tengyue360.com/api/user/signin/mobilelogin").then();
    }
}
