package ApiTest;
import io.restassured.response.ValidatableResponse;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class requestJson {

    //因为此项目保密所以只给大家演示下json形式的怎么请求，大家可以拿自己项目进行测试
    @Test
    public void testRequestJson() throws Exception{
        String bodyString = "{\"isvpid\": \"365478232242534\",\"pid\": \"8764326563424456\",\"out\": \"76543242\"}";
        ValidatableResponse res = given().contentType("application/json").body(bodyString)
                .post("https://**********.com/dummyisv/orderQuery").then();
        System.out.println(res.extract().body().asString());
        String datr = res.extract().body().jsonPath().getString("response");
        JSONObject jsonObj = JSONObject.fromObject(datr);
        System.out.println(jsonObj);
        String id = jsonObj.getString("status");
        System.out.println(id);

    }
}
