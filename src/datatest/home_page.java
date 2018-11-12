package datatest;

import com.alibaba.fastjson.JSONObject;
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

import static io.restassured.RestAssured.given;

/**
 * Created by cuishuaishuai on 2018/11/3.
 */
public class home_page {
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
    //请求首页列表
    public void testgetInfo() throws Exception {
        JSONObject tt = new JSONObject();
        tt.put("stamp", "");
        System.err.println(tt.toJSONString());
        String stamp=TestAESCipher.aesEncryptString(tt.toJSONString());
        ValidatableResponse response = given()
                .contentType("text/encode;charset=UTF-8")
                .header("header-encrypt-code", hearderEncode)
                .body(stamp)
                .post("http://192.168.20.31:20100/vmi-video-service/api/v/index/anchor").then();
        System.err.println(response.extract().body().asString());
        //System.err.println(TestAESCipher.aesDecryptString(response.extract().body().asString()));
        String res = TestAESCipher.aesDecryptString(response.extract().body().asString());
        System.out.println(res);
    }
    @Test
    //首页搜索
    public void search() throws NoSuchPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        JSONObject aa = new JSONObject();
        aa.put("word", "小小");
        System.out.println(aa.toJSONString());
        String word;
        word = TestAESCipher.aesEncryptString(aa.toJSONString());
        ValidatableResponse response = given()
                .contentType("text/encode;charset=UTF-8")
                .header("header-encrypt-code", hearderEncode)
                .body(word)
                .post("http://192.168.20.31:20100/vmi-video-service/api/v/anchor/search").then();
       // System.err.println(response.extract().body().asString());
        //System.err.println(TestAESCipher.aesDecryptString(response.extract().body().asString()));
        String res = TestAESCipher.aesDecryptString(response.extract().body().asString());
        System.out.println(res);

    }
    @Test
    //申请大V
    public void apply() throws NoSuchPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        JSONObject tt = new JSONObject();
        tt.put("nickName", "nainai");
        tt.put("mobile", "18600522222");
        tt.put("stature", "167");
        tt.put("weight", "48");
        tt.put("zodiac", "天秤");
        tt.put("cityId", "131");
        tt.put("signature", "aaaa");
        tt.put("intro", "aaawwww");
        System.err.println(tt.toJSONString());
        String stamp=TestAESCipher.aesEncryptString(tt.toJSONString());
        ValidatableResponse response = given()
                .contentType("text/encode;charset=UTF-8")
                .header("header-encrypt-code", hearderEncode)
                .body(stamp)
                .post("http://192.168.20.31:20100/vmi-video-service/api/v/anchor/apply").then();
        //System.err.println(response.extract().body().asString());
        //System.err.println(TestAESCipher.aesDecryptString(response.extract().body().asString()));
        String res = TestAESCipher.aesDecryptString(response.extract().body().asString());
        System.out.println(res);

    }

}
