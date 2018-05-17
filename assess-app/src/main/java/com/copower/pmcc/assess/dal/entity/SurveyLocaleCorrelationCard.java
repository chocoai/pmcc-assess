package com.copower.pmcc.assess.dal.entity;

public class SurveyLocaleCorrelationCard {
    private Integer id;

    private String correlationCard;

    private String houseName;

    private String processInsId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorrelationCard() {
        return correlationCard;
    }

    public void setCorrelationCard(String correlationCard) {
        this.correlationCard = correlationCard == null ? null : correlationCard.trim();
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName == null ? null : houseName.trim();
    }

    public String getProcessInsId() {
        return processInsId;
    }

    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }
}