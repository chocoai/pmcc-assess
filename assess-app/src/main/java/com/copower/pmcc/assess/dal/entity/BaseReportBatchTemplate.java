package com.copower.pmcc.assess.dal.entity;

public class BaseReportBatchTemplate {
    private Integer id;

    private String customerId;

    private String customerName;

    private Integer entrustId;

    private Object customContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Integer getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(Integer entrustId) {
        this.entrustId = entrustId;
    }

    public Object getCustomContent() {
        return customContent;
    }

    public void setCustomContent(Object customContent) {
        this.customContent = customContent;
    }
}