package com.copower.pmcc.assess.dal.basis.custom.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kings on 2018-7-16.
 */
public class CustomReportGongShangBank {
    private Integer id;
    private Integer projectId;
    private Integer reportType;//报告类型
    //支行	工行系统最终推送日期
    // 工行系统推送项目编号	客户经理	联系电话	委托方
    // 评估标的 （土地、房地产、机器设备、其他）	现场查看时间	评估价值	预估号
    // 报告号 	收费金额（元）	开票时间	备注
    private String unitName;//支行
    private Date pushTime;//工行系统最终推送日期
    private String pushNumber;//工行系统推送项目编号
    private String clientManager;//客户经理(报告使用单位联系人)
    private String phone;//联系电话
    private String consignor;//委托方
    private Integer projectCategoryId;
    private String projectCategoryName;//评估标的
    private Date checkTime;//现场查看时间
    private BigDecimal assessTotal;//评估价值
    private String previewsNumber;//预估号
    private String resultNumber;//报告号
    private BigDecimal assessCost;//收费金额
    private Date makeOutDate;//开票时间
    private String remark;//备注
    private String numberValue;//文号
    private Integer areaId;//

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public String getPushNumber() {
        return pushNumber;
    }

    public void setPushNumber(String pushNumber) {
        this.pushNumber = pushNumber;
    }

    public String getClientManager() {
        return clientManager;
    }

    public void setClientManager(String clientManager) {
        this.clientManager = clientManager;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConsignor() {
        return consignor;
    }

    public void setConsignor(String consignor) {
        this.consignor = consignor;
    }

    public Integer getProjectCategoryId() {
        return projectCategoryId;
    }

    public void setProjectCategoryId(Integer projectCategoryId) {
        this.projectCategoryId = projectCategoryId;
    }

    public String getProjectCategoryName() {
        return projectCategoryName;
    }

    public void setProjectCategoryName(String projectCategoryName) {
        this.projectCategoryName = projectCategoryName;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public BigDecimal getAssessTotal() {
        return assessTotal;
    }

    public void setAssessTotal(BigDecimal assessTotal) {
        this.assessTotal = assessTotal;
    }

    public String getPreviewsNumber() {
        return previewsNumber;
    }

    public void setPreviewsNumber(String previewsNumber) {
        this.previewsNumber = previewsNumber;
    }

    public String getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(String resultNumber) {
        this.resultNumber = resultNumber;
    }

    public BigDecimal getAssessCost() {
        return assessCost;
    }

    public void setAssessCost(BigDecimal assessCost) {
        this.assessCost = assessCost;
    }

    public Date getMakeOutDate() {
        return makeOutDate;
    }

    public void setMakeOutDate(Date makeOutDate) {
        this.makeOutDate = makeOutDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(String numberValue) {
        this.numberValue = numberValue;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
}
