package com.jumper.open.sdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jumper.open.sdk.client.OpenClient;
import com.jumper.open.sdk.common.RequestMethod;
import com.jumper.open.sdk.common.UploadFile;
import com.jumper.open.sdk.model.DemoFileUploadModel;
import com.jumper.open.sdk.model.GetStoryModel;
import com.jumper.open.sdk.request.CommonRequest;
import com.jumper.open.sdk.request.DemoFileUploadRequest;
import com.jumper.open.sdk.request.GetStoryRequest;
import com.jumper.open.sdk.response.CommonResponse;
import com.jumper.open.sdk.response.DemoFileUploadResponse;
import com.jumper.open.sdk.response.GetStoryResponse;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SdkTest extends TestCase {
    String url = "http://open-gateway.jumper-health.com";
    String appId = "20211207917871952369025024";
    /** 开发者私钥 */
    String privateKeyIsv = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALnkIP4a+ch3q/LN ECtVU+kCKo4Z/M9paJisXRLIQ9FdfuhwoCpnHN/79r3R9TY7aj2VOioRXfyyfjQ2 2lQ6nfzZKJcP48lajmWVkKpu5EFVHWY7UOgE723Mzi4sq2sJ7ih5p+Pq02MzWHj5 gGTjRMSYJhqxzosxXLw+fjl9eL4vAgMBAAECgYBr5GLZ2aJcnkjo1eiqng3j0eQN MjwvzkhtRK2Itz/VhuSf2K69rbdusXmW1wPyBC43rCNkQ0w/3QGvIJ88AAGtwLxQ zbpPdakzAjwpQOfylJ5sti0VFBZIfKvlNnzOW/rWdce7tFqVdQJ5YCNkyjv0rUqR Hxcru5p5UMgEAKRdAQJBAN6j+2JUQsvkZy4jNZ1DHANabYKx9KYLuZMdh/1hDPNr QqHbaWkZvc5hy36YafHog4LuzcFBNWqBeesjAY03cm8CQQDVvoRgxqoGcS/mrDKn mTwQN44ZREYu/SPUw0CA7TeSrIAbPyLd28Td/CPReibrfJfP6hcFMrcdrGy+aPTE flBBAkEAza2wDqbJDpNyvqzIWwAkYPSpNpQv7zXluoMNnoaJ8Famt+B7AR1Gu+Kc m9cyCMlEtTDn5CDZuArlFtTAGjWfAQJAIt8GMdO3gX2N1xBY40b4qZymrjCZUqkx 41BHgcExw9govkk6ov9rytuL7G0FVH3rNpx2TP7NKSYsR5RP+o6xAQJAYL9k5EpC XXpgSGfOGzzW8Xa2I9fGFVL1E6LbI9N8ynObhduFv8DmaC9avOFMeH2rgrL1qJzY MxmBHXfJ/m8geg==";
    /** 开放平台提供的公钥
     * 前往SOP-ADMIN，ISV管理--秘钥管理，生成平台提供的公私钥，然后把【平台公钥】放到这里 */
    String publicKeyPlatform = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApIY3GqpKZTG3/v4h+HPsVd0Zj2sloNSTjFmfKPy83M4gV5jQqUDnZ3clwHfgMIyX2cA0jW1iJRNk4qmL8z7dwZVth6BjSCdv4VECwmkT0XRCW6k8PsDoAac/fQCm6UIRmnGPa9HVy8o89oVorI2IzOp/1pqxpxOkHGIUhfc42o8KqzAbcpOuEAH10HUCpeM7nSs/0I8jT+MArzCwURMrsYhUGBmOQBs/Bp+UZdalheROfY2/+skgoJ3uxwuoYZBHAnpDtb22bJfW49rTbhmmOsgS11YXPorwAZ/rlhUOhoq5Tgq6z5QyLnEkyDbmzfLAsKiSapCx3l3BGVEFohzqfwIDAQAB";

    // 声明一个就行
    OpenClient client = new OpenClient(url, appId, privateKeyIsv, publicKeyPlatform);

    // 标准用法
    @Test
    public void testGet() {
        // 创建请求对象
        GetStoryRequest request = new GetStoryRequest();
        // 请求参数
        GetStoryModel model = new GetStoryModel();
        model.setName("白雪公主");
        request.setBizModel(model);

        // 发送请求
        GetStoryResponse response = client.execute(request);

        if (response.isSuccess()) {
            // 返回结果
            System.out.println(String.format("response:%s",
                    JSON.toJSONString(response)));
        } else {
            System.out.println("错误，subCode:" + response.getSubCode() + ", subMsg:" + response.getSubMsg());
        }
    }


    // 懒人版，如果不想添加Request,Response,可以用这种方式，返回全部是String，后续自己处理json
    @Test
    public void testLazy() {
        // 创建请求对象
        CommonRequest request = new CommonRequest("test001");
        // 请求参数
        Map<String, Object> bizModel = new HashMap<>();
        bizModel.put("name", "白雪公主");
        bizModel.put("id", "111");
        bizModel.put("remark", "xx");
        request.setVersion("1.0.1");
        request.setBizModel(bizModel);
        request.setRequestMethod(RequestMethod.POST);
        // 发送请求
        CommonResponse response = client.execute(request);
        if (response.isSuccess()) {
            // 返回结果
            String body = response.getBody();
            JSONObject jsonObject = JSON.parseObject(body);
            System.out.println(jsonObject);
        } else {
            System.out.println("错误，subCode:" + response.getSubCode() + ", subMsg:" + response.getSubMsg());
        }
    }

    // 文件上传
    @Test
    public void testUpload() throws IOException {
        DemoFileUploadRequest request = new DemoFileUploadRequest();

        DemoFileUploadModel model = new DemoFileUploadModel();
        model.setRemark("上传文件参数");
        request.setBizModel(model);

        String root = System.getProperty("user.dir");
        System.out.println(root);
        // 这里演示将resources下的两个文件上传到服务器
        request.addFile(new UploadFile("file1", new File(root + "/src/main/resources/file1.txt")));
        request.addFile(new UploadFile("file2", new File(root + "/src/main/resources/file2.txt")));

        DemoFileUploadResponse response = client.execute(request);

        System.out.println("--------------------");
        if (response.isSuccess()) {
            List<DemoFileUploadResponse.FileMeta> responseFiles = response.getFiles();
            System.out.println("您上传的文件信息：");
            responseFiles.forEach(System.out::println);
        } else {
            System.out.println(JSON.toJSONString(response));
        }
        System.out.println("--------------------");
    }

}
