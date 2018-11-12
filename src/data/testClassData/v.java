package data.testClassData;
import common.LoginWeb;
import datatest.TestAESCipher;
import io.restassured.response.ValidatableResponse;
//import net.sf.json.JSONObject;
import org.testng.annotations.Test;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.testng.AssertJUnit.assertEquals;
import static io.restassured.path.json.JsonPath.from;

/**
 * Created by cuishuaishuai on 2018/10/31.
 */
public class v {
    @Test
    public void testgetInfo() throws Exception {

        ValidatableResponse response = given()
                .contentType("text/encode;charset=UTF-8")
                .header("header-encrypt-code","LBMxTLKkuke9J5GpI0mpGjYgMedscDLSWBSwFdebK23PkMuP7lHg2MUaaMKFuppSY7baClOemgwg0Ro26N5RILBfvE6EFE3PSfzDHRSV+A/vSHE3348WgFn3IV8Kqf1L3aSfAC2pWMKspJZmeJJM8PfofX6/Ekb7fLX9Pq+x9DLgqwDRrYdZx5gd5oe2lerhGPupDRMkSmUshWL0qaiZpyIbFAk9Yhq0l+8xh4yM3lLXPCYIWr/wXYPg66q7Y7k3YH5J92rNAGmxya1Js4sNniopMCbOqpESI8hHD5aY0LSFFo14dSmkYHxH9qbaKA84jQxFUBCM4It81Rn84lKU/7y9rODFuPdOOlD6GjeB0BF3a/QarbpGmvEygOK16/js4ewOshCKwtClag2M7D+ktqXnWztoWzPuPREpKQ8eTJNOHmhdP//0qDvNTC9+e4o1yI4waStNALvdRpdLcKuPIQ==")
                .body("uQb6mBeoUwQWJlpNtHTTwg==")
                .post("http://192.168.20.31:20100/vmi-video-service/api/v/anchor/search").then();
        System.err.println(response.extract().body().asString());
        //System.err.println(TestAESCipher.aesDecryptString(response.extract().body().asString()));
        String res= TestAESCipher.aesDecryptString(response.extract().body().asString());
        System.out.println(res);
        JSONObject object = JSONObject.parseObject(res);
        //String data = object.getString("data");
        JSONObject data=object.getJSONObject("data");
        JSONArray array=data.getJSONArray("anchors");
        System.out.println(array);

        for(int i=0;i<array.size();i++){
            JSONObject anchor=array.getJSONObject(i);
            //System.out.println(anchor);
            String aa=anchor.getString("nickName");
            System.out.println(aa);
            //assertEquals(aa,"0");
            //System.out.println("成功");
        }


        //res.equals();


        }




    }




