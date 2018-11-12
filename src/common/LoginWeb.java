package common;
import io.restassured.response.ValidatableResponse;
import java.io.IOException;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class LoginWeb {
    public static Map testLogin() throws IOException {
        ValidatableResponse myresponse = given()
                .queryParam("Username", "18600528463")
                .queryParam("Password", "css910cui")
                .queryParam("LoginType", "0")
                .queryParam("geetest_challenge", "")
                .queryParam("geetest_validate", "")
                .queryParam("geetest_seccode", "")
                .queryParam("Remember1", "false")
                .queryParam("LoginCode", "")
                .queryParam("Remember2", "false")
                .queryParam("Remember2", "false")
                .post("https://www.huodongxing.com/login").then();
        Map response001 = myresponse.extract().cookies();
        System.out.println(response001);
        return response001;
    }
}
