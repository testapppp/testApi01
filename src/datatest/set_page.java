package datatest;
import io.restassured.response.ValidatableResponse;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
/**
 * Created by cuishuaishuai on 2018/11/7.
 */
public class set_page {
    private String hearderEncode = null;
    @BeforeMethod
    public void init() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        //System.out.println("111111");
        RequestHeader header = new RequestHeader();
        header.setChannel("yoyo");
        header.setClientId("5546e19dc781bd343ce158127f86586f");
        header.setImei("860928032075807");
        header.setMobile("");
        header.setMobile_brand("GiONEE");
        header.setMobile_model("F103");
        header.setOs_type(1);
        header.setPackageName("com.ydwx.yoyo");
        header.setToken("70589780e179661c5a4d8ca9f514abb8");
        header.setUserid(92584844218925312L);
        header.setVersion("2.3.6");
        header.setVersioncode(19);
        String headerCode =  JsonHelper.toJson(header);
        hearderEncode = TestAESCipher.aesEncryptString(headerCode);
    }
    @Test
    //请求设置页面
    public void param() throws Exception {
        HashMap<String, Object> hsmp=new HashMap<>();
        hsmp.put("userid", 92584844218925312L);
        String stamp=TestAESCipher.aesEncryptString(JsonHelper.toJson(hsmp));
        ValidatableResponse response = given()
                .contentType("text/encode;charset=UTF-8")
                .header("header-encrypt-code", hearderEncode)
                .body(stamp)
                .post("http://192.168.20.31:20100/vmi-video-service/api/setting/param").then();
        String res = TestAESCipher.aesDecryptString(response.extract().body().asString());
        System.out.println(res);
    }
    @Test
    //请求设置界面设置勿扰模式开关接口
    public void disturb() throws Exception {
        HashMap<String, Object> hsmp=new HashMap<>();
        hsmp.put("disturb", 1);
        String stamp=TestAESCipher.aesEncryptString(JsonHelper.toJson(hsmp));
        ValidatableResponse response = given()
                .contentType("text/encode;charset=UTF-8")
                .header("header-encrypt-code", hearderEncode)
                .body(stamp)
                .post("http://192.168.20.31:20100/vmi-video-service/api/setting/disturb").then();
        String res = TestAESCipher.aesDecryptString(response.extract().body().asString());
        System.out.println(res);
    }
}
