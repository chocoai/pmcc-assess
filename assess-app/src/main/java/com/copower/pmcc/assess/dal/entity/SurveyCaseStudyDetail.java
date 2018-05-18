package com.copower.pmcc.assess.dal.entity;

import java.util.Date;

public class SurveyCaseStudyDetail {
    private Integer id;

    private Integer planDetailsId;

    private String correlationCard;

    private String caseLocation;

    private String houseName;

    private Integer caseType;

    private Integer price;

    private String dealCaondition;

    private Date dealTime;

    private String paymentMethod;

    private Integer informationSource;

    private String linkman;

    private String contactWay;

    private String creator;

    private Date gmtCreated;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(Integer planDetailsId) {
        this.planDetailsId = planDetailsId;
    }

    public String getCorrelationCard() {
        return correlationCard;
    }

    public void setCorrelationCard(String correlationCard) {
        this.correlationCard = correlationCard == null ? null : correlationCard.trim();
    }

    public String getCaseLocation() {
        return caseLocation;
    }

    public void setCaseLocation(String caseLocation) {
        this.caseLocation = caseLocation == null ? null : caseLocation.trim();
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName == null ? null : houseName.trim();
    }

    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDealCaondition() {
        return dealCaondition;
    }

    public void setDealCaondition(String dealCaondition) {
        this.dealCaondition = dealCaondition == null ? null : dealCaondition.trim();
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod == null ? null : paymentMethod.trim();
    }

    public Integer getInformationSource() {
        return informationSource;
    }

    public void setInformationSource(Integer informationSource) {
        this.informationSource = informationSource;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay == null ? null : contactWay.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}