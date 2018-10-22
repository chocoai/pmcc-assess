package com.copower.pmcc.assess.dto.input.ocr;

import java.math.BigDecimal;

/**
 * @Auther: zch
 * @Date: 2018/10/22 14:34
 * @Description:
 */
public class RealtyLandCertOcrDto {
    public final static String TEST = "{\"sid\":\"87d5fea41bd6d42e799aaf49865375a200e394fa619e0b8b95598c7eab300ded0781dd4d\",\"data\":{\"房产证号\":\"1117\",\"权利人\":\"陈训番\",\"共有情况\":\"武侯区太平园横一街289号5栋1单元1层1号\",\"坐落\":\"武侯区太平园横一街289号5栋1单元1层1号\",\"不动产单元号\":\"\",\"土地权利类型\":\"土地使用权人\",\"房屋权利类型\":\"土地使用权人\",\"土地权利性质/取得方式\":\"记事证为分摊登记所发证书，不附宗地目为本幢建(构)筑物所占土地面内共同使用土地登记注册给全体证所分地使用权为出让取得，享有原土地合同定的心林利和义务。土地登记专用章国有土地使用证国有工地假证国有土地使用用\",\"房屋权利性质/取得方式\":\"记事证为分摊登记所发证书，不附宗地目为本幢建(构)筑物所占土地面内共同使用土地登记注册给全体证所分地使用权为出让取得，享有原土地合同定的心林利和义务。土地登记专用章国有土地使用证国有工地假证国有土地使用用\",\"土地用途\":\"住宝用地\",\"房屋用途\":\"住宝用地\",\"建筑面积\":\"69.84\",\"使用期限\":\"年5月\"},\"angle\":0}\n" ;
    private String sid;
    private Integer angle;
    //土地证编号
    private String number;
    //权利人
    private String ownership;
    //坐落
    private String beLocated;
    //记事
    private String memo;
    //用途
    private String purpose;
    //使用权面积
    private BigDecimal useRightArea;
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getAngle() {
        return angle;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getBeLocated() {
        return beLocated;
    }

    public void setBeLocated(String beLocated) {
        this.beLocated = beLocated;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public BigDecimal getUseRightArea() {
        return useRightArea;
    }

    public void setUseRightArea(BigDecimal useRightArea) {
        this.useRightArea = useRightArea;
    }

    @Override
    public String toString() {
        return "RealtyLandCertOcrDto{" +
                "sid='" + sid + '\'' +
                ", angle=" + angle +
                ", number='" + number + '\'' +
                ", ownership='" + ownership + '\'' +
                ", beLocated='" + beLocated + '\'' +
                ", memo='" + memo + '\'' +
                ", purpose='" + purpose + '\'' +
                ", useRightArea=" + useRightArea +
                '}';
    }
}
