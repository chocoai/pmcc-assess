package com.copower.pmcc.assess.common.aliyun;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.*;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.common.enums.aliyun.AccessKeyEnum;
import com.copower.pmcc.assess.common.enums.aliyun.dysms.APICodeEnum;
import com.copower.pmcc.assess.common.enums.aliyun.dysms.SignSourceTypeEnum;
import com.copower.pmcc.assess.common.enums.aliyun.dysms.TemplateStatusEnum;
import com.copower.pmcc.assess.common.enums.aliyun.dysms.TemplateTypeEnum;
import com.copower.pmcc.assess.dto.input.aliyun.SmsSignDto;
import com.copower.pmcc.assess.dto.input.aliyun.SmsTemplateDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.google.common.base.Objects;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * 短信发送工具类
 * 使用需知 短信签名 和  短信模板 必须匹配  如: 验证码签名匹配验证码模板 短信通知
 */
public class SendSmsUtils {
  /*
    *
    * <!-- 短信模板 开发包 -->
        <dependency>
            <groupId>com.aliyun</groupId>
            <artifactId>aliyun-java-sdk-core</artifactId>
            <version>4.0.3</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.aliyun/aliyun-java-sdk-dysmsapi -->
        <dependency>
            <groupId>com.aliyun</groupId>
            <artifactId>aliyun-java-sdk-dysmsapi</artifactId>
            <version>2.1.0</version>
        </dependency>
    * */

    //阿里云 签名和模板设置地址
    //https://dysms.console.aliyun.com/dysms.htm?spm=5176.8195934.1283918..153e30c9VsuEZy&accounttraceid=c53a9bdbf4dc447db8345ddf04ff5ce7pjrk#/domestic/text/sign

    //阿里云 密钥 设置地址
    //https://usercenter.console.aliyun.com/?accounttraceid=1ca86cc7f1e2441baac3118d0b3e99c2rerb#/manage/ak

    public final static String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
    public final static String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）

    public final static String VERSION = "2017-05-25";//不要修改这个  官方例子就是这个

    /**
     * 签名修改
     * @param accessKeyEnum
     * @param smsSignDto
     * @param paths
     * @return
     * @throws Exception
     */
    public static SmsSignDto modifySmsSign(AccessKeyEnum accessKeyEnum, SmsSignDto smsSignDto, List<String> paths) throws Exception {
        return modifySmsSign(accessKeyEnum, smsSignDto, null, paths);
    }

    /**
     * 签名修改
     * @param accessKeyEnum
     * @param smsSignDto
     * @param version
     * @param paths
     * @return
     * @throws Exception
     */
    private static SmsSignDto modifySmsSign(AccessKeyEnum accessKeyEnum, SmsSignDto smsSignDto, String version, List<String> paths) throws Exception {
        IClientProfile profile = DefaultProfile.getProfile(accessKeyEnum.getRegionId(), accessKeyEnum.getId(), accessKeyEnum.getSecret());
        DefaultProfile.addEndpoint(accessKeyEnum.getRegionId(), accessKeyEnum.getRegionId(), SendSmsUtils.product, SendSmsUtils.domain);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(SendSmsUtils.domain);
        request.setVersion(StringUtils.isNotBlank(version) ? version : VERSION);
        request.setAction("ModifySmsSign");
        request.putQueryParameter("RegionId", accessKeyEnum.getRegionId());
        request.putQueryParameter("SignName", smsSignDto.getSignName());
        request.putQueryParameter("SignSource", smsSignDto.getSignSource());
        request.putQueryParameter("Remark", smsSignDto.getRemark());
        Iterator<String> iterator = paths.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            String path = iterator.next();
            String base64Encode = FileUtils.base64Encode(path);
            request.putQueryParameter(StringUtils.join(Arrays.asList("SignFileList", String.valueOf(count), "FileSuffix", ".")), FilenameUtils.getExtension(path));
            request.putQueryParameter(StringUtils.join(Arrays.asList("SignFileList", String.valueOf(count), "FileContents", ".")), base64Encode);
        }
        CommonResponse response = client.getCommonResponse(request);
        SmsSignDto signDto = JSONObject.parseObject(response.getData(), SmsSignDto.class);
        return signDto;
    }

    /**
     * 签名 删除
     *
     * @param accessKeyEnum
     * @param SignName 签名名称
     * @return
     * @throws Exception
     */
    public static SmsSignDto deleteSmsSign(AccessKeyEnum accessKeyEnum, String SignName) throws Exception {
        IClientProfile profile = DefaultProfile.getProfile(accessKeyEnum.getRegionId(), accessKeyEnum.getId(), accessKeyEnum.getSecret());
        DefaultProfile.addEndpoint(accessKeyEnum.getRegionId(), accessKeyEnum.getRegionId(), SendSmsUtils.product, SendSmsUtils.domain);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(SendSmsUtils.domain);
        request.setAction("DeleteSmsSign");
        request.putQueryParameter("RegionId", accessKeyEnum.getRegionId());
        request.putQueryParameter("SignName", SignName);
        CommonResponse response = client.getCommonResponse(request);
        SmsSignDto signDto = JSONObject.parseObject(response.getData(), SmsSignDto.class);
        return signDto;
    }

    /**
     * 签名  查询
     *
     * @param accessKeyEnum key
     * @param SignName      名称
     * @return
     * @throws Exception
     */
    public static SmsSignDto querySmsSign(AccessKeyEnum accessKeyEnum, String SignName) throws Exception {
        IClientProfile profile = DefaultProfile.getProfile(accessKeyEnum.getRegionId(), accessKeyEnum.getId(), accessKeyEnum.getSecret());
        DefaultProfile.addEndpoint(accessKeyEnum.getRegionId(), accessKeyEnum.getRegionId(), SendSmsUtils.product, SendSmsUtils.domain);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(SendSmsUtils.domain);
        request.setAction("QuerySmsSign");
        request.putQueryParameter("RegionId", accessKeyEnum.getRegionId());
        request.putQueryParameter("SignName", SignName);
        CommonResponse response = client.getCommonResponse(request);
        SmsSignDto signDto = JSONObject.parseObject(response.getData(), SmsSignDto.class);
        return signDto;
    }

    /**
     * 添加签名
     *
     * @param accessKeyEnum      key
     * @param smsSignDto         签名对象数据
     * @param paths              资质文件 如  营业执照等图片
     * @param signSourceTypeEnum 签名来源
     * @return
     * @throws Exception
     */
    public static SmsSignDto addSmsSign(AccessKeyEnum accessKeyEnum, SmsSignDto smsSignDto, List<String> paths, SignSourceTypeEnum signSourceTypeEnum) throws Exception {
        smsSignDto.setSignSource(signSourceTypeEnum.getKey().toString());
        return addSmsSign(accessKeyEnum, smsSignDto, null, paths);
    }

    /**
     * 添加签名
     *
     * @param accessKeyEnum key
     * @param smsSignDto    签名对象数据
     * @param paths         资质文件 如  营业执照等图片
     * @return
     * @throws Exception
     */
    public static SmsSignDto addSmsSign(AccessKeyEnum accessKeyEnum, SmsSignDto smsSignDto, List<String> paths) throws Exception {
        return addSmsSign(accessKeyEnum, smsSignDto, null, paths);
    }

    /**
     * 添加签名
     *
     * @param accessKeyEnum
     * @param smsSignDto
     * @param version
     * @param paths         资质文件
     * @return
     * @throws Exception
     */
    private static SmsSignDto addSmsSign(AccessKeyEnum accessKeyEnum, SmsSignDto smsSignDto, String version, List<String> paths) throws Exception {
        IClientProfile profile = DefaultProfile.getProfile(accessKeyEnum.getRegionId(), accessKeyEnum.getId(), accessKeyEnum.getSecret());
        DefaultProfile.addEndpoint(accessKeyEnum.getRegionId(), accessKeyEnum.getRegionId(), SendSmsUtils.product, SendSmsUtils.domain);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(SendSmsUtils.domain);
        request.setVersion(StringUtils.isNotBlank(version) ? version : VERSION);
        request.setAction("AddSmsSign");
        request.putQueryParameter("RegionId", accessKeyEnum.getRegionId());
        request.putQueryParameter("SignName", smsSignDto.getSignName());
        request.putQueryParameter("SignSource", smsSignDto.getSignSource());
        request.putQueryParameter("Remark", smsSignDto.getRemark());
        Iterator<String> iterator = paths.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            String path = iterator.next();
            String base64Encode = FileUtils.base64Encode(path);
            request.putQueryParameter(StringUtils.join(Arrays.asList("SignFileList", String.valueOf(count), "FileSuffix", ".")), FilenameUtils.getExtension(path));
            request.putQueryParameter(StringUtils.join(Arrays.asList("SignFileList", String.valueOf(count), "FileContents", ".")), base64Encode);
        }
        //假如一共两个资质文件图片
//        request.putQueryParameter("SignFileList.1.FileSuffix", "jpg");
//        request.putQueryParameter("SignFileList.1.FileContents", "sdjsdjsdjdjssdjds");
//        request.putQueryParameter("SignFileList.2.FileSuffix", "png");
//        request.putQueryParameter("SignFileList.2.FileContents", "dhsdjsdjdsj");
        CommonResponse response = client.getCommonResponse(request);
        SmsSignDto signDto = JSONObject.parseObject(response.getData(), SmsSignDto.class);
        return signDto;
    }

    /**
     * 修改模板 TemplateType , TemplateName ,TemplateContent , Remark , TemplateCode 必填
     *
     * @param accessKeyEnum
     * @param smsTemplateDto
     * @return
     * @throws Exception
     */
    public static SmsTemplateDto modifySmsTemplate(AccessKeyEnum accessKeyEnum, SmsTemplateDto smsTemplateDto) throws Exception {
        return modifySmsTemplate(accessKeyEnum, smsTemplateDto, null);
    }


    /**
     * 修改模板 TemplateType , TemplateName ,TemplateContent , Remark , TemplateCode 必填
     *
     * @param accessKeyEnum
     * @param smsTemplateDto
     * @return
     * @throws Exception
     */
    private static SmsTemplateDto modifySmsTemplate(AccessKeyEnum accessKeyEnum, SmsTemplateDto smsTemplateDto, String version) throws Exception {
        IClientProfile profile = DefaultProfile.getProfile(accessKeyEnum.getRegionId(), accessKeyEnum.getId(), accessKeyEnum.getSecret());
        DefaultProfile.addEndpoint(accessKeyEnum.getRegionId(), accessKeyEnum.getRegionId(), SendSmsUtils.product, SendSmsUtils.domain);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(SendSmsUtils.domain);
        request.setVersion(StringUtils.isNotBlank(version) ? version : VERSION);
        request.setAction("ModifySmsTemplate");
        request.putQueryParameter("RegionId", accessKeyEnum.getRegionId());
        request.putQueryParameter("TemplateType", smsTemplateDto.getTemplateType().toString());
        request.putQueryParameter("TemplateName", smsTemplateDto.getTemplateName());
        request.putQueryParameter("TemplateCode", smsTemplateDto.getTemplateCode());
        request.putQueryParameter("TemplateContent", smsTemplateDto.getTemplateContent());
        request.putQueryParameter("Remark", smsTemplateDto.getRemark());
        request.putQueryParameter("TemplateCode", smsTemplateDto.getTemplateCode());
        CommonResponse response = client.getCommonResponse(request);
        SmsTemplateDto templateDto = JSONObject.parseObject(response.getData(), SmsTemplateDto.class);
        return templateDto;
    }


    /**
     * 获取模板审核状态
     *
     * @param accessKeyEnum 阿里云 key
     * @param templateCode  模板id
     * @return
     * @throws Exception
     */
    public static TemplateStatusEnum querySmsTemplateStatus(AccessKeyEnum accessKeyEnum, String templateCode) throws Exception {
        SmsTemplateDto smsTemplateDto = querySmsTemplate(accessKeyEnum, templateCode);
        return TemplateStatusEnum.getEnumByName(smsTemplateDto.getTemplateStatus());
    }


    /**
     * 获取模板数据
     *
     * @param accessKeyEnum 阿里云 key
     * @param templateCode  模板id
     * @return
     * @throws Exception
     */
    public static SmsTemplateDto querySmsTemplate(AccessKeyEnum accessKeyEnum, String templateCode) throws Exception {
        return querySmsTemplate(accessKeyEnum, templateCode, null);
    }

    /**
     * 获取模板数据
     *
     * @param accessKeyEnum 阿里云 key
     * @param templateCode  模板id
     * @param version       版本
     * @return
     * @throws Exception
     */
    private static SmsTemplateDto querySmsTemplate(AccessKeyEnum accessKeyEnum, String templateCode, String version) throws Exception {
        IClientProfile profile = DefaultProfile.getProfile(accessKeyEnum.getRegionId(), accessKeyEnum.getId(), accessKeyEnum.getSecret());
        DefaultProfile.addEndpoint(accessKeyEnum.getRegionId(), accessKeyEnum.getRegionId(), SendSmsUtils.product, SendSmsUtils.domain);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(SendSmsUtils.domain);
        request.setVersion(StringUtils.isNotBlank(version) ? version : VERSION);
        request.setAction("QuerySmsTemplate");
        request.putQueryParameter("RegionId", accessKeyEnum.getRegionId());
        request.putQueryParameter("TemplateCode", templateCode);
        CommonResponse response = client.getCommonResponse(request);
        SmsTemplateDto smsTemplateDto = JSONObject.parseObject(response.getData(), SmsTemplateDto.class);
        return smsTemplateDto;
    }

    /**
     * 模板删除
     *
     * @param accessKeyEnum 阿里云 key
     * @param templateCode  模板id
     * @return
     * @throws Exception
     */
    public static SmsTemplateDto deleteSmsTemplate(AccessKeyEnum accessKeyEnum, String templateCode) throws Exception {
        return deleteSmsTemplate(accessKeyEnum, templateCode, null);
    }

    /**
     * 模板删除
     *
     * @param accessKeyEnum 阿里云 key
     * @param templateCode  模板id
     * @param version       版本
     * @return
     * @throws Exception
     */
    private static SmsTemplateDto deleteSmsTemplate(AccessKeyEnum accessKeyEnum, String templateCode, String version) throws Exception {
        IClientProfile profile = DefaultProfile.getProfile(accessKeyEnum.getRegionId(), accessKeyEnum.getId(), accessKeyEnum.getSecret());
        DefaultProfile.addEndpoint(accessKeyEnum.getRegionId(), accessKeyEnum.getRegionId(), SendSmsUtils.product, SendSmsUtils.domain);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(SendSmsUtils.domain);
        request.setVersion(StringUtils.isNotBlank(version) ? version : VERSION);
        request.setAction("DeleteSmsTemplate");
        request.putQueryParameter("RegionId", accessKeyEnum.getRegionId());
        request.putQueryParameter("TemplateCode", templateCode);
        CommonResponse response = client.getCommonResponse(request);
        SmsTemplateDto smsTemplateDto = JSONObject.parseObject(response.getData(), SmsTemplateDto.class);
        return smsTemplateDto;
    }

    /**
     * 模板添加
     *
     * @param accessKeyEnum    key 必填
     * @param remark           短信模板申请说明 必填
     * @param templateTypeEnum 短信模板类型 必填
     * @param templateName     模板名称 必填 (建议英文字母)
     * @param templateContent  模板内容
     * @return
     * @throws Exception
     */
    public static SmsTemplateDto addSmsTemplate(AccessKeyEnum accessKeyEnum, String remark, TemplateTypeEnum templateTypeEnum, String templateName, String templateContent) throws Exception {
        return addSmsTemplate(accessKeyEnum, remark, templateTypeEnum, templateName, templateContent, null);
    }


    /**
     * 短信模板添加
     *
     * @param accessKeyEnum    key 必填
     * @param remark           短信模板申请说明 必填
     * @param templateTypeEnum 短信模板类型 必填
     * @param templateName     模板名称 必填 (建议英文字母)
     * @param templateContent  模板内容
     * @param version          模板版本 可以不填
     * @return
     * @throws Exception
     */
    private static SmsTemplateDto addSmsTemplate(AccessKeyEnum accessKeyEnum, String remark, TemplateTypeEnum templateTypeEnum, String templateName, String templateContent, String version) throws Exception {
        IClientProfile profile = DefaultProfile.getProfile(accessKeyEnum.getRegionId(), accessKeyEnum.getId(), accessKeyEnum.getSecret());
        DefaultProfile.addEndpoint(accessKeyEnum.getRegionId(), accessKeyEnum.getRegionId(), SendSmsUtils.product, SendSmsUtils.domain);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion(StringUtils.isNotBlank(version) ? version : VERSION);
        request.setAction("AddSmsTemplate");
        request.putQueryParameter("RegionId", accessKeyEnum.getRegionId());
        request.putQueryParameter("TemplateType", templateTypeEnum.getKey().toString());
        request.putQueryParameter("TemplateName", templateName);
        request.putQueryParameter("TemplateContent", templateContent);
        request.putQueryParameter("Remark", remark);
        CommonResponse response = client.getCommonResponse(request);
        SmsTemplateDto smsTemplateDto = JSONObject.parseObject(response.getData(), SmsTemplateDto.class);
        return smsTemplateDto;
    }


    /**
     * @param accessKeyEnum 阿里云API的密钥
     * @param phoneNumber   号码
     * @param signName      短信签名
     * @param templateCode  短信模板代码 如: 您的注册码：${code}，如非本人操作，请忽略本短信！
     * @param map           Map<String,String> map = Maps.newHashMap(); map.put("code","47347") ;
     * @throws Exception
     */
    public static void send(AccessKeyEnum accessKeyEnum, String phoneNumber, String signName, String templateCode, Map<String, String> map) throws Exception {
        send(accessKeyEnum, phoneNumber, signName, templateCode, JSONObject.toJSONString(map));
    }

    /**
     * 如果你拼接不好json串那么使用上面的方法
     *
     * @param accessKeyEnum 阿里云API的密钥
     * @param phoneNumber   号码
     * @param signName      短信签名
     * @param templateCode  短信模板代码 如: 您的注册码：${code}，如非本人操作，请忽略本短信！
     * @param templateParam 短信模板代码 里面拼接成的json串 "{ \"code\":\"456789\"}"
     * @throws Exception
     */
    public static void send(AccessKeyEnum accessKeyEnum, String phoneNumber, String signName, String templateCode, String templateParam) throws Exception {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //替换成你的AK
        final String accessKeyId = accessKeyEnum.getId();//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = accessKeyEnum.getSecret();//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile(accessKeyEnum.getRegionId(), accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint(accessKeyEnum.getRegionId(), accessKeyEnum.getRegionId(), product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers(phoneNumber);
        //必填:短信签名-可在短信控制台中找到
//        request.setSignName("云通信");
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
//        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
        request.setTemplateParam(templateParam);
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//        request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        handleError(sendSmsResponse);
    }

    /**
     * 批量发送短信
     * 不需要 自己拼json的方法
     *
     * @param accessKeyEnum accessKeyEnum  阿里云API的密钥
     * @param phoneNumbers  号码
     * @param signNames     短信签名
     * @param templateCode  短信模板代码 一个
     * @param mapList       短信模板代码和参数 列表 ,这里一个map就是一个号码拼接成的json串
     * @throws Exception
     */
    public static void sendBatch(AccessKeyEnum accessKeyEnum, List<String> phoneNumbers, List<String> signNames, String templateCode, List<Map<String, String>> mapList) throws Exception {
        sendBatch(accessKeyEnum, JSONObject.toJSONString(phoneNumbers), JSONObject.toJSONString(signNames), templateCode, JSONObject.toJSONString(mapList));
    }

    /**
     * 批量发送短信
     *
     * @param accessKeyEnum accessKeyEnum  阿里云API的密钥
     * @param phoneNumber   号码,注意是json串 号码数量必须和签名一致
     * @param signName      短信签名 , 注意是json串 号码数量必须和签名一致
     * @param templateCode  短信模板代码 一个
     * @param templateParam 短信模板代码和参数 拼接成的json串 json对象数组必须和号码数量一致
     * @throws Exception
     */
    public static void sendBatch(AccessKeyEnum accessKeyEnum, String phoneNumber, String signName, String templateCode, String templateParam) throws Exception {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //替换成你的AK
        final String accessKeyId = accessKeyEnum.getId();//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = accessKeyEnum.getSecret();//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile(accessKeyEnum.getRegionId(), accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint(accessKeyEnum.getRegionId(), accessKeyEnum.getRegionId(), product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendBatchSmsRequest request = new SendBatchSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持JSON格式的批量调用，批量上限为100个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
//        request.setPhoneNumberJson("[\"18380479453\",\"15756238830\"]");
        request.setPhoneNumberJson(phoneNumber);
        //必填:短信签名-支持不同的号码发送不同的短信签名
//        request.setSignNameJson("[\"云通信\",\"云通信\"]");
        request.setSignNameJson(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //必填:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParamJson(templateParam);
//        request.setTemplateParamJson("[{\"name\":\"Tom\", \"code\":\"123\"},{\"name\":\"Jack\", \"code\":\"456\"}]");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCodeJson("[\"90997\",\"90998\"]");
        //请求失败这里会抛ClientException异常
        SendBatchSmsResponse sendBatchSmsResponse = acsClient.getAcsResponse(request);
        SendSmsResponse sendSmsResponse = new SendSmsResponse();
        //这里两个返回状态的对象 成员变量一致因此可以这么做
        BeanUtils.copyProperties(sendBatchSmsResponse, sendSmsResponse);
        handleError(sendSmsResponse);
    }

    private static void handleError(SendSmsResponse sendSmsResponse) throws BusinessException {
        if (StringUtils.isBlank(sendSmsResponse.getCode())) {
            throw new BusinessException("未知错误");
        }
        if (Objects.equal(sendSmsResponse.getCode(), APICodeEnum.OK.getCode())) {
            //成功后调用

        } else {
            APICodeEnum codeEnum = APICodeEnum.getEnumByName(sendSmsResponse.getCode());
            if (codeEnum != null) {
                throw new BusinessException(codeEnum.getMessage());
            } else {
                throw new BusinessException("错误");
            }
        }
    }

    /**
     * 获取某个时间点发送的短信数量
     *
     * @param accessKeyEnum 阿里云API的密钥
     * @param phoneNumber   号码一个
     * @param date          发送的时间
     * @return
     * @throws Exception
     */
    public static int getQuerySendDetailsSize(AccessKeyEnum accessKeyEnum, String phoneNumber, Date date) throws Exception {
        List<QuerySendDetailsResponse.SmsSendDetailDTO> smsSendDetailDTOS = getSmsSendDetailDTOList(accessKeyEnum, phoneNumber, date);
        return smsSendDetailDTOS.size();
    }

    /**
     * 获取某个时间点发送的短信集合
     *
     * @param accessKeyEnum 阿里云API的密钥
     * @param phoneNumber   号码一个
     * @param date          发送的时间
     * @return
     * @throws Exception
     */
    public static List<QuerySendDetailsResponse.SmsSendDetailDTO> getSmsSendDetailDTOList(AccessKeyEnum accessKeyEnum, String phoneNumber, Date date) throws Exception {
        //设置超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient
        IClientProfile profile = DefaultProfile.getProfile(accessKeyEnum.getRegionId(), accessKeyEnum.getId(), accessKeyEnum.getSecret());
        DefaultProfile.addEndpoint(accessKeyEnum.getRegionId(), accessKeyEnum.getRegionId(), product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber(phoneNumber);
        //可选-调用发送短信接口时返回的BizId
//        request.setBizId("1234567^8901234");
        //必填-短信发送的日期 支持30天内记录查询（可查其中一天的发送数据），格式yyyyMMdd
        request.setSendDate(DateUtils.formatDate(date, DateUtils.DATE_SHORT_PATTERN));
        //必填-页大小
//        request.setPageSize(10L);
        request.setPageSize(49L);//页大小Max=50
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);
        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
        //获取返回结果
        if (querySendDetailsResponse.getCode() != null && Objects.equal(querySendDetailsResponse.getCode(), APICodeEnum.OK.getCode())) {
            //代表请求成功
            return querySendDetailsResponse.getSmsSendDetailDTOs();
        }
        return new ArrayList<>();
    }

}
