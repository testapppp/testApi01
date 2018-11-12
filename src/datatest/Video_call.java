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
public class Video_call {
    private String hearderEncode = null;
    @BeforeMethod
    public void init() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        //System.out.println("111111");
        RequestHeader header = new RequestHeader();
        header.setChannel("yoyo");
        header.setClientId("3cabaf4afc6af308aad6fae912a7fcdf");
        header.setImei("860928032075807");
        header.setMobile("");
        header.setMobile_brand("GiONEE");
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
    //拨打对方和获得视频通话接口信息
    public void dialing() throws Exception {
        HashMap<String, Object> hsmp=new HashMap<>();
        hsmp.put("otherId", 66138512797270272L);
        hsmp.put("sponsor",0);
        String stamp=TestAESCipher.aesEncryptString(JsonHelper.toJson(hsmp));
        ValidatableResponse response = given()
                .contentType("text/encode;charset=UTF-8")
                .header("header-encrypt-code", hearderEncode)
                .body(stamp)
                .post("http://192.168.20.31:20100/vmi-video-service/api/vchatYX/dialing").then();
        String res = TestAESCipher.aesDecryptString(response.extract().body().asString());
        System.out.println(res);
    }
    @Test
    //扣费接口，进房间每分种调用
    public void payOrder() throws Exception {
        HashMap<String, Object> hsmp=new HashMap<>();
        hsmp.put("otherId", 66138512797270272L);
        hsmp.put("sponsor",0);
        hsmp.put("state",0);
        hsmp.put("type",2);
        hsmp.put("serialNum",94369772040421632L);
        String stamp=TestAESCipher.aesEncryptString(JsonHelper.toJson(hsmp));
        ValidatableResponse response = given()
                .contentType("text/encode;charset=UTF-8")
                .header("header-encrypt-code", hearderEncode)
                .body(stamp)
                .post("http://192.168.20.31:20100/vmi-video-service/api/vchatYX/payOrder").then();
        String res = TestAESCipher.aesDecryptString(response.extract().body().asString());
        System.out.println(res);
    }
    @Test
    //摄像头状态通知
    public void cameraStatus() throws Exception {
        HashMap<String, Object> hsmp=new HashMap<>();
        hsmp.put("otherId", 92584844218925312L);
        hsmp.put("state",1);
        String stamp=TestAESCipher.aesEncryptString(JsonHelper.toJson(hsmp));
        ValidatableResponse response = given()
                .contentType("text/encode;charset=UTF-8")
                .header("header-encrypt-code", hearderEncode)
                .body(stamp)
                .post("http://192.168.20.31:20100/vmi-video-service/api/vchatYX/cameraStatus").then();
        String res = TestAESCipher.aesDecryptString(response.extract().body().asString());
        System.out.println(res);
    }
    @Test
    //获取视频通话结束评价列表
    public void anchorEvaluationList() throws Exception {

        //String stamp=TestAESCipher.aesEncryptString(JsonHelper.toJson(hsmp));
        ValidatableResponse response = given()
                .contentType("text/encode;charset=UTF-8")
                .header("header-encrypt-code", hearderEncode)
                .body(" ")
                .post("http://192.168.20.31:20100/vmi-video-service/api/vchatYX/anchorEvaluationList").then();
        String res = TestAESCipher.aesDecryptString(response.extract().body().asString());
        System.out.println(res);
    }
    @Test
    //视频通话结束评价
    public void anchorEvaluation() throws Exception {
        HashMap<String, Object> hsmp=new HashMap<>();
        hsmp.put("anchorId", 87152674235023616L);
        hsmp.put("evaluationIdList", new Long[]{25L,26L});
        String stamp=TestAESCipher.aesEncryptString(JsonHelper.toJson(hsmp));
        ValidatableResponse response = given()
                .contentType("text/encode;charset=UTF-8")
                .header("header-encrypt-code", hearderEncode)
                .body(stamp)
                .post("http://192.168.20.31:20100/vmi-video-service/api/vchatYX/anchorEvaluation").then();
        String res = TestAESCipher.aesDecryptString(response.extract().body().asString());
        System.out.println(res);
    }
    @Test
    //视频通话前检查
    public void dialingCheck() throws Exception {
        HashMap<String, Object> hsmp=new HashMap<>();
        hsmp.put("otherId", 92584844218925312L);
        String stamp=TestAESCipher.aesEncryptString(JsonHelper.toJson(hsmp));
        ValidatableResponse response = given()
                .contentType("text/encode;charset=UTF-8")
                .header("header-encrypt-code", hearderEncode)
                .body(stamp)
                .post("http://192.168.20.31:20100/vmi-video-service/api/vchatYX/dialingCheck").then();
        String res = TestAESCipher.aesDecryptString(response.extract().body().asString());
        System.out.println(res);
    }
}
