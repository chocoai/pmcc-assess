package com.copower.aliyun;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.copower.pmcc.assess.common.aliyun.SendSmsUtils;
import com.copower.pmcc.assess.common.enums.aliyun.AccessKeyEnum;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SendSms {

    /**
     * 一个号码发短信
     * @throws Exception
     */
    public static void runC() throws Exception {
        //注意模板里面有的变量这里必须设置 ,如 您的注册码：${code}，如非本人操作，请忽略本短信！
        Map<String, String> map = Maps.newHashMap();
        map.put("name", "sdsdjdjs");
        map.put("code", RandomStringUtils.randomNumeric(5));

//        SendSmsUtils.send(AccessKeyEnum.ZCHAccessKey,"18380479453","zdjdjadj" ,"SMS_189711881" ,map);//测试非法签名的情况
        SendSmsUtils.send(AccessKeyEnum.ZCHAccessKey, "18380479453", "zdjdjadj", "SMS_189712261", map);
    }

    /**
     * 多个号码  多个 签名模式  多个 模板的情况
     * 这里需要说明的签名和模板必须匹配, 这里存在多个签名,一个签名模板 ,多个签名必须是一致的类型如都是验证码签名或者都是通用签名,签名类型必须一致
     * @throws Exception
     */
    public static void runD() throws Exception {
        List<String> phoneNumbers = Arrays.asList("15756238830", "18380479453");
        List<String> signNames = Arrays.asList("zdjdjadj" ,"zdjdjadj") ;//签名
        List<Map<String, String>> mapList = new ArrayList<>(phoneNumbers.size());
        for (int i = 0; i < phoneNumbers.size(); i++) {
            Map<String, String> map = Maps.newHashMap();
            map.put("code", RandomStringUtils.randomNumeric(5));
            mapList.add(map) ;
        }
        SendSmsUtils.sendBatch(AccessKeyEnum.ZCHAccessKey , phoneNumbers,signNames ,"SMS_189712261" ,mapList) ;//模板SMS_189712261
    }

    public static void runE()throws Exception{
        SendSmsUtils.getQuerySendDetailsSize(AccessKeyEnum.ZCHAccessKey ,"15756238830", DateUtils.convertDate("20200508" ,DateUtils.DATE_SHORT_PATTERN));
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(() -> {
            try {
//                runA();
//                runB();
//                runC();
//                runD();
                runE();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

//        System.out.println("[\"1500000000\",\"1500000001\"]");
    }

    public static void runA() throws Exception {
        AccessKeyEnum zch = AccessKeyEnum.ZCHAccessKey;
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = zch.getId();//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = zch.getSecret();//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile(zch.getRegionId(), accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint(zch.getRegionId(), zch.getRegionId(), product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
//        request.setPhoneNumbers("18380479453");
        request.setPhoneNumbers("18380479453");
        //必填:短信签名-可在短信控制台中找到
//        request.setSignName("云通信");
        request.setSignName("zdjdjadj");
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode("SMS_189712261");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
//        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
//        request.setTemplateParam("{\"name\":\"周陈华\"}");


        Map<String, String> map = Maps.newHashMap();
        map.put("code", "47347");
        String string = JSONObject.toJSONString(map);
//        request.setTemplateParam("{ \"code\":\"456789\"}");
        request.setTemplateParam(string);


        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
            System.out.println("SendSms.runA");
        }

        System.out.println("run fulfill ...");
        System.out.println(sendSmsResponse.getMessage());
        System.out.println(sendSmsResponse.getCode());
        System.out.println(sendSmsResponse.getRequestId());
    }

    public static void runB() throws Exception {
        AccessKeyEnum zch = AccessKeyEnum.ZCHAccessKey;
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = zch.getId();//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = zch.getSecret();//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile(zch.getRegionId(), accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint(zch.getRegionId(), zch.getRegionId(), product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendBatchSmsRequest request = new SendBatchSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持JSON格式的批量调用，批量上限为100个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumberJson("[\"18380479453\",\"15756238830\"]");
        //必填:短信签名-支持不同的号码发送不同的短信签名
//        request.setSignNameJson("[\"云通信\",\"云通信\"]");
        request.setSignNameJson("[\"zdjdjadj\",\"zdjdjadj\"]");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_189712261");
        //必填:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParamJson("[{\"code\":\"123\"},{\"code\":\"456\"}]");
        request.setTemplateParamJson("[{\"name\":\"Tom\", \"code\":\"123\"},{\"name\":\"Jack\", \"code\":\"456\"}]");

        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCodeJson("[\"90997\",\"90998\"]");
        //请求失败这里会抛ClientException异常
        SendBatchSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
        }

        System.out.println("run fulfill ...");
        System.out.println(sendSmsResponse.getMessage());
        System.out.println(sendSmsResponse.getCode());
        System.out.println(sendSmsResponse.getRequestId());
    }
}
