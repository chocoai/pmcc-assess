package com.copower.aliyun;


//import com.aliyuncs.ocr.model.v20191230.*;

import com.aliyun.ocr20191230.Client;
import com.aliyun.ocr20191230.models.RecognizeBankCardAdvanceRequest;
import com.aliyun.ocr20191230.models.RecognizeBankCardResponse;
import com.aliyun.ocr20191230.models.RecognizeIdentityCardRequest;
import com.aliyun.ocr20191230.models.RecognizeIdentityCardResponse;
import com.aliyun.ossutil.models.RuntimeOptions;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.copower.pmcc.assess.common.enums.aliyun.AccessKeyEnum;
import com.google.gson.Gson;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class IdentityCardDemo {

    private final String imgUrl = "http://explorer-image.oss-cn-shanghai.aliyuncs.com/1936164761444528/images.jpg?OSSAccessKeyId=LTAI4Fk9FstqSEYnqKJ5Dpeo&Expires=1588991978&Signature=wL3NEphI6E93bYzVKqiN%2BL%2BDjSM%3D";

    @Test
    public void testA() throws Exception {
        AccessKeyEnum zch = AccessKeyEnum.ZCHAccessKey;
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", zch.getId(), zch.getSecret());
        DefaultAcsClient client = new DefaultAcsClient(profile);

        RecognizeIdentityCardRequest request = new RecognizeIdentityCardRequest();
//        request.setRegionId("cn-shanghai");
//        request.setImageURL("http://explorer-image.oss-cn-shanghai.aliyuncs.com/1936164761444528/images.jpg?OSSAccessKeyId=LTAI4Fk9FstqSEYnqKJ5Dpeo&Expires=1588994331&Signature=wcZMGtbdqBllUb1qzLmAp0S5SQs%3D");
//        request.setImageURL("D:\\images.jpg");
//        request.setSide("face");

//        try {
//            RecognizeIdentityCardResponse response = client.getAcsResponse(request);
//            System.out.println(new Gson().toJson(response));
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            System.out.println("ErrCode:" + e.getErrCode());
//            System.out.println("ErrMsg:" + e.getErrMsg());
//            System.out.println("RequestId:" + e.getRequestId());
//        }
    }

    @Test
    public void testB() throws Exception {

        com.aliyun.tearpc.models.Config config = new com.aliyun.tearpc.models.Config();

        // 1 Create and initialize a Client instance.
        com.aliyun.tearpc.models.Config authConfig = new com.aliyun.tearpc.models.Config();
        authConfig.accessKeyId = "your accessKeyId";
        authConfig.accessKeySecret = "your accessKeySecret";
        authConfig.type = "access_key";
        authConfig.endpoint = "your endpoint";
        authConfig.regionId = "cn-beijing";
        Client authClient = new Client(authConfig);
        // 2 Create and set up parameters RuntimeObject instance.
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        // 3 Create an API request and set parameters.
        RecognizeBankCardAdvanceRequest request = new RecognizeBankCardAdvanceRequest();
        InputStream inputStream = new FileInputStream("your file");
        request.imageURLObject = inputStream;
        // 4 Initiate the request and handle the response or exceptions.
        RecognizeBankCardResponse authResponse = authClient.recognizeBankCardAdvance(request, runtime);
    }

    @Test
    public void testC() throws Exception {

        AccessKeyEnum zch = AccessKeyEnum.ZCHAccessKey;
        // 1 创建DefaultAcsClient实例并初始化。
        com.aliyun.tearpc.models.Config authConfig = new com.aliyun.tearpc.models.Config();
        authConfig.accessKeyId = zch.getId();
        authConfig.accessKeySecret = zch.getSecret();
        authConfig.regionId = "cn-shanghai";
        Client authClient = new Client(authConfig);
        // 2 创建RuntimeObject实例并设置运行参数
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        // 3 创建API请求并设置参数
        RecognizeIdentityCardRequest request = new RecognizeIdentityCardRequest();
        request.imageURL = "D:\\images.jpg";
        request.side = "face" ;
        // 4 发起请求并处理应答或异常。
        RecognizeIdentityCardResponse authResponse = authClient.recognizeIdentityCard(request, runtime);
        if (authResponse != null){
            RecognizeIdentityCardResponse.RecognizeIdentityCardResponseData data = authResponse.data;
            if (data != null){

            }
        }
    }


}
