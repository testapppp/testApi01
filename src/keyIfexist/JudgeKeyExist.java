package keyIfexist;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.get;

public class JudgeKeyExist {
    /*办学百科接口*/
    @Test
    public void test1() throws IOException {
        String url = "https://api.tengyue360.com/api/content/document/encyclopedia?tag_id=111&document_type=1&pn=1&rn=20&_exps=%7B%7D&platform=2&device_type=3&app_id=1&os_version=8.200000&app_version_name=3.4.0&app_version=82&device_id=6FA310DC-8181-4C26-ADEF-371FF31FC557";
        Response response = get(url);
        if (response.getStatusCode() != 200) {
            throw new AssertionError("状态码异常");
        }
        response.prettyPrint();
        if (response.jsonPath().getInt("errno") != 0) {
            throw new AssertionError("errno值不为0");
        }
        if (response.jsonPath().getString("data.list[0].title") == null) {
            throw new AssertionError("title节点缺失");
        }
        if (response.jsonPath().getString("data.list[0].description") == null) {
            throw new AssertionError("description节点缺失");
        }
    }

}
