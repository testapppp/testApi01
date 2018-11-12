package test;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.get;

/**
 * Created by Lenovo on 2017/7/11.
 */
public class AdTest {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());//日志
    @Test //标记一个类或方法作为测试的一部分
    public void test0() throws IOException {
        String url = "https://adnewnc.app.autohome.com.cn/advert_v7.9.5/ad/jingxuanad.ashx?pageindex=1&networkid=7012&idfa=0B6A6F30-ECB2-4C25-A955-12564BA3FD5B&deviceid=becdd983940434dffceda980b26886df42bc8b0f&mac=0&appid=2&platform=1&version=8.2.5&cityid=0&devicebrand=apple&devicemodel=iPhone&random=3573313442&userid=&gps_city=110100&lng=116.312233&lat=39.979408&pageid=becdd983940434dffceda980b26886df42bc8b0f1498728326040&isretry=0&adtype=3&conn=1&scori=0&osver=10.0.1&scden=3.000000&aaid&aid&size=1242_2208";
        Response response = get(url);
        response.jsonPath().prettyPrint();
        if (response.getStatusCode() != 200) {
            throw new AssertionError("状态码异常");
        }
        //打印json
        if (response.andReturn().jsonPath().getInt("result.banneradlist[0].ishavead") == 1) {
            Assert.assertEquals(2080,response.andReturn().jsonPath().getInt("result.banneradlist[0].areaid"));
            String rdposturl=response.andReturn().jsonPath().getString("result.banneradlist[0].rdposturl");
            System.out.println();
            if(!rdposturl.startsWith("https://rdx")){
                throw new AssertionError("url前缀是错误的");
            }else if (response.andReturn().jsonPath().getString("result.banneradlist[0].pvid")==null){
                throw new AssertionError("pvid内容不能为空");
            }else  if (response.andReturn().jsonPath().getString("result.banneradlist[0].addata.url")==null){
                throw new AssertionError("url内容不能为空");
            }else  if (response.andReturn().jsonPath().getString("result.banneradlist[0].addata.imgpath")==null) {
                throw new AssertionError("imgpath内容不能为空");
            }else  if (response.andReturn().jsonPath().getString("result.banneradlist[0].addata.thirdadurl")==null) {
                throw new AssertionError("thirdadurl内容不能为空");
            }
        }
    }
}
