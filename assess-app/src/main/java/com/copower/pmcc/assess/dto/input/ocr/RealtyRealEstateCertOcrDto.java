package com.copower.pmcc.assess.dto.input.ocr;

import com.copower.pmcc.assess.common.DateHelp;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2018/10/16 18:25
 * @Description:
 */
public class RealtyRealEstateCertOcrDto {
    private String sid;
    private Integer angle;
    //房产证编号
    private String number;
    //权利人
    private String ownership;
    //共有情况
    private String publicSituation;
    //坐落
    private String beLocated;
    //不动产单元号
    private String realEstateUnitNumber;
    //土地权利类型-房屋权利类型
    private String useRightType;
    private BigDecimal floorArea;
    //性质
    private String nature;
    //用途
    private String purpose;
    //使用期限
    private Date terminationDate;
    /**
     *
     * 功能描述: 测试数据
     *
     * @auther: zch
     * @date: 2018/10/17 9:09
     */
    public final static String test =  "{\"sid\":\"218b069fe162a6d826632a550bb7d6bb4581c68062858bd05211b47f153cd6c586ab1597\",\"data\":{\"房产证号\":\"0047142\",\"权利人\":\"段毅莉\",\"共有情况\":\"共同共有\",\"坐落\":\"高新区雍翠路211号16栋1单元13层1302号\",\"不动产单元号\":\"510109002005GB00035F00010014\",\"土地权利类型\":\"国有建设用地使用权\",\"房屋权利类型\":\"房屋(构筑物)所有权\",\"土地权利性质/取得方式\":\"出让\",\"房屋权利性质/取得方式\":\"普通\",\"土地用途\":\"城镇住宅用地\",\"房屋用途\":\"住宅\",\"建筑面积\":\"\",\"使用期限\":\"国有建设用地使用权：2074年11月10日止\"},\"angle\":2}\n" ;

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



    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getPublicSituation() {
        return publicSituation;
    }

    public void setPublicSituation(String publicSituation) {
        this.publicSituation = publicSituation;
    }

    public String getBeLocated() {
        return beLocated;
    }

    public void setBeLocated(String beLocated) {
        this.beLocated = beLocated;
    }

    public String getRealEstateUnitNumber() {
        return realEstateUnitNumber;
    }

    public void setRealEstateUnitNumber(String realEstateUnitNumber) {
        this.realEstateUnitNumber = realEstateUnitNumber;
    }

    public String getUseRightType() {
        return useRightType;
    }

    public void setUseRightType(String useRightType) {
        this.useRightType = useRightType;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public BigDecimal getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    @Override
    public String toString() {
        return "RealtyRealEstateCertOcrDto{" +
                "sid='" + sid + '\'' +
                ", angle=" + angle +
                ", number='" + number + '\'' +
                ", ownership='" + ownership + '\'' +
                ", publicSituation='" + publicSituation + '\'' +
                ", beLocated='" + beLocated + '\'' +
                ", realEstateUnitNumber='" + realEstateUnitNumber + '\'' +
                ", useRightType='" + useRightType + '\'' +
                ", floorArea=" + floorArea +
                ", nature='" + nature + '\'' +
                ", purpose='" + purpose + '\'' +
                ", terminationDate=" + DateHelp.getDateHelp().printDate(terminationDate) +
                '}';
    }
}
