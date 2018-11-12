package test;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
/**
 * 登录接口
 */
public class Login {
    private Logger logger = LoggerFactory.getLogger(Login.class);
    @Test
    // iOS-登录：post
    public void test1() throws Exception {
        Response res = given().params("platform", 2, "device_type", 3,
                "app_id", 1,
                "os_version", "11.300000",
                "app_version_name", "3.6.6",
                "app_version", 94,
                "device_id", "C3BEA5A7-1FDB-41D9-B141-706A9A4A47CB",
                "mobile", "xx", "password", "xxx").post("http://api.tengyue360.com/api/user/signin/mobilelogin");
        System.out.println("nihao");
        res.getBody().prettyPrint();
        if (res.getStatusCode() != 200) {
            logger.error("https状态码异常");

        }
        int error=res.getBody().jsonPath().getInt("errno");
        System.out.println(error);
        if (error!=0){
            //throw new AssertionError("接口异常啦");
            logger.error("接口异常");
        }
    }
}

