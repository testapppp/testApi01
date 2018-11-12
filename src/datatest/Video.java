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
import java.util.HashMap;

import static io.restassured.RestAssured.given;
/**
 * Created by cuishuaishuai on 2018/11/7.
 */
public class Video {
    private String hearderEncode = null;
    @BeforeMethod
    public void init() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        //System.out.println("111111");
        RequestHeader header = new RequestHeader();
        header.setChannel("yoyo");
        header.setClientId("3cabaf4afc6af308aad6fae912a7fcdf");
        header.setImei("860928032075807");
        header.setMobile("");
        header.setMobile_brand("Xiaomi");
        header.setMobile_model("F103");
        header.setOs_type(1);
        header.setPackageName("com.ydwx.yoyo");
        header.setToken("293aa4e5c5aab49c134958d5c7cd20e5");
        header.setUserid(87152674235023616L);
        header.setVersion("2.3.6");
        header.setVersioncode(19);
        String headerCode =  JsonHelper.toJson(header);
        hearderEncode = TestAESCipher.aesEncryptString(headerCode);
    }
    @Test
    //短视频首页
    public void shortVideo_home() throws Exception {
        JSONObject tt = new JSONObject();
        tt.put("stamp", "");
        System.err.println(tt.toJSONString());
        String stamp=TestAESCipher.aesEncryptString(tt.toJSONString());
        ValidatableResponse response = given()
                .contentType("text/encode;charset=UTF-8")
                .header("header-encrypt-code", hearderEncode)
                .body(stamp)
                .post("http://192.168.20.31:20100/vmi-video-service/api/shortVideo/home").then();
        System.err.println(response.extract().body().asString());
        //System.err.println(TestAESCipher.aesDecryptString(response.extract().body().asString()));
        String res = TestAESCipher.aesDecryptString(response.extract().body().asString());
        System.out.println(res);
    }
    @Test
    //短视频详情页
    public void videoDesc() throws NoSuchPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        HashMap<String, Object> hsmp=new HashMap<>();
        hsmp.put("videoId", 35);
        //String res=TestAPPHttpClientUtils.doPost(header,"http://127.0.0.1:8080/api/userwatch/anchorDynamic", hsmp);
        //JSONObject tt = new JSONObject();
        //tt.put("videoId", 42l);
        //System.err.println(tt.toJSONString());
        String stamp=TestAESCipher.aesEncryptString(JsonHelper.toJson(hsmp));
        ValidatableResponse response = given()
                .contentType("text/encode;charset=UTF-8")
                .header("header-encrypt-code", hearderEncode)
                .body(stamp)
                .post("http://192.168.20.31:20100/vmi-video-service/api/shortVideo/videoDesc").then();
        System.err.println(response.extract().body().asString());
        //System.err.println(TestAESCipher.aesDecryptString(response.extract().body().asString()));
        String res = TestAESCipher.aesDecryptString(response.extract().body().asString());
        System.out.println(res);
    }
    @Test
    //个人视频列表
    public void videoList() throws NoSuchPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        HashMap<String, Object> hsmp=new HashMap<>();
        hsmp.put("userId", 87152674235023616L);
        //String res=TestAPPHttpClientUtils.doPost(header,"http://127.0.0.1:8080/api/userwatch/anchorDynamic", hsmp);
        //JSONObject tt = new JSONObject();
        //tt.put("videoId", 42l);
        //System.err.println(tt.toJSONString());
        String stamp=TestAESCipher.aesEncryptString(JsonHelper.toJson(hsmp));
        ValidatableResponse response = given()
                .contentType("text/encode;charset=UTF-8")
                .header("header-encrypt-code", hearderEncode)
                .body(stamp)
                .post("http://192.168.20.31:20100/vmi-video-service/api/shortVideo/anchorVideoList").then();
        //System.err.println(response.extract().body().asString());
        //System.err.println(TestAESCipher.aesDecryptString(response.extract().body().asString()));
        String res = TestAESCipher.aesDecryptString(response.extract().body().asString());
        System.out.println(res);
    }
    @Test
    //视频详情页
    public void videoDelete() throws NoSuchPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        HashMap<String, Object> hsmp=new HashMap<>();
        hsmp.put("videoId", 35);
        String stamp=TestAESCipher.aesEncryptString(JsonHelper.toJson(hsmp));
        ValidatableResponse response = given()
                .contentType("text/encode;charset=UTF-8")
                .header("header-encrypt-code", hearderEncode)
                .body(stamp)
                .post("http://192.168.20.31:20100/vmi-video-service/api/shortVideo/videoDelete").then();
        String res = TestAESCipher.aesDecryptString(response.extract().body().asString());
        System.out.println(res);
    }
}
