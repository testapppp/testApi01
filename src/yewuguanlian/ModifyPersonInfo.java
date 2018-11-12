package yewuguanlian;

import common.LoginWeb;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class ModifyPersonInfo {
    /*登录后添加主办方*/
    @Test
    public void testgetInfo() throws Exception {
        Map cookies = LoginWeb.testLogin();//调用登录接口获取cookies
        //文件上传接口
        ValidatableResponse res01 = given().cookies(cookies)
                .queryParam("orgId", "0")
                .multiPart("org_logo_upload_file", new File("D:/ac.jpg")).when()
                .post("https://www.huodongxing.com/file/UploadOrgLogo").then();
        System.out.println(res01.extract().body().asString());
        String logo = res01.extract().body().asString();//获取图片上传后的 logo地址
        ValidatableResponse res = given().cookies(cookies)
                .queryParam("Id", "0")
                .queryParam("type", "0")
                .queryParam("act", "0")
                .queryParam("status", "0")
                .queryParam("Logo", logo)
                .queryParam("theme", "")
                .queryParam("Name", "你会发发")
                .queryParam("ContactName", "张三四AAAAA")
                .queryParam("ContactPhone", "x138485")
                .queryParam("ContactEmail", "123@qq.com")
                .queryParam("Description", "我的简介啊")
                .queryParam("Subdomain", "")
                .queryParam("Homepage", "").when()
                .post("https://www.huodongxing.com/myorg/SaveOrg").then();
        res.assertThat().statusCode(200);
        assertEquals(res.extract().body().asString(),"true");
    }
}
