package com.copower.pmcc.assess.common.enums.aliyun;


/**
 * AccessKey ID和AccessKey Secret是您访问阿里云API的密钥，具有该账户完全的权限，请您妥善保管
 */
public enum AccessKeyEnum {

    ZCHAccessKey("LTAI4G2qXoKCjCeYacJGoU7p","BVth93F82WYlDdlZwl3lmN9y1GNjzO","" ,"cn-hangzhou") ,//注意我自己上面只有1元钱 ,大概可以发好像是100多条数据
    CompanyAccessKey("","","" ,"cn-hangzhou") //我们公司申请的密钥
    ;

    private String id;
    private String secret;
    private String status;

    private String regionId ;


    AccessKeyEnum(String id, String secret, String status ,String regionId) {
        this.id = id;
        this.secret = secret;
        this.status = status;
        this.regionId = regionId;
    }

    public String getId() {
        return id;
    }

    public String getSecret() {
        return secret;
    }

    public String getStatus() {
        return status;
    }

    public String getRegionId() {
        return regionId;
    }

}
