package com.copower.pmcc.assess.dto.input.ocr;

import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2018/10/22 13:57
 * @Description:
 */
public class RealtyHouseCertOcrDto {
    public final static String test = "{\"sid\":\"4a73677b1c8bdf6687950f4b34e21fa77a6ed03059c4ebc25ffa52e7d4aa93f78cb89a43\",\"data\":{\"房产证号\":\"3821962\",\"权利人\":\"陈训番\",\"共有情况\":\"单独所有\",\"坐落\":\"武侯区太平园横一街289号5栋1单元1层1号\",\"登记时间\":\"2013-12-4\",\"房屋性质\":\"\",\"房屋用途\":\"住宅\",\"建筑面积\":\"255.19\",\"土地权利类型/取得方式\":\"\"},\"angle\":0}\n" ;
    private String sid;
    private Integer angle;
    //房产证号 (编号)
    private String number;
    //权利人
    private String ownership;
    //共有情况
    private String publicSituation;
    //坐落
    private String beLocated;
    //登记时间
    private Date registrationTime;
    //房屋性质
    private String nature;
    //房屋用途
    private String planningUse;
    //建筑面积
    private String floorArea;
    //土地权利类型/取得方式
    private String landAcquisition;
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

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getPlanningUse() {
        return planningUse;
    }

    public void setPlanningUse(String planningUse) {
        this.planningUse = planningUse;
    }

    public String getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(String floorArea) {
        this.floorArea = floorArea;
    }

    public String getLandAcquisition() {
        return landAcquisition;
    }

    public void setLandAcquisition(String landAcquisition) {
        this.landAcquisition = landAcquisition;
    }

    @Override
    public String toString() {
        return "RealtyHouseCertOcrDto{" +
                "sid='" + sid + '\'' +
                ", angle=" + angle +
                ", number='" + number + '\'' +
                ", ownership='" + ownership + '\'' +
                ", publicSituation='" + publicSituation + '\'' +
                ", beLocated='" + beLocated + '\'' +
                ", registrationTime=" + registrationTime +
                ", nature='" + nature + '\'' +
                ", planningUse='" + planningUse + '\'' +
                ", floorArea='" + floorArea + '\'' +
                ", landAcquisition='" + landAcquisition + '\'' +
                '}';
    }
}
