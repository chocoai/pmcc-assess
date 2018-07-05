package com.copower.pmcc.assess.dto.input.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/8/31
 * @time: 17:34
 * 2018-05-09 进行过一次添加修改
 */
public class ProjectInfoDto extends ProjectInfo {
    public static String ATTACHMENT_PROJECTINFO_ID = "attachmentProjectInfoId"; //附件字段
    private String userAccountManager;//项目经理
    private String userAccountMember;//项目成员 或者说下级成员
    private Integer urgency;
    private String attachmentProjectInfoId;
    private Integer customerId;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date valuationDate;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date completeDatePlan;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date completeDateActual;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date completeDateStart;

    private String departmentIdName;

    private Integer boxId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getCompleteDatePlan() {
        return completeDatePlan;
    }

    public void setCompleteDatePlan(Date completeDatePlan) {
        this.completeDatePlan = completeDatePlan;
    }

    public Date getCompleteDateActual() {
        return completeDateActual;
    }

    public void setCompleteDateActual(Date completeDateActual) {
        this.completeDateActual = completeDateActual;
    }

    public Date getCompleteDateStart() {
        return completeDateStart;
    }

    public void setCompleteDateStart(Date completeDateStart) {
        this.completeDateStart = completeDateStart;
    }

    public String getDepartmentIdName() {
        return departmentIdName;
    }

    public void setDepartmentIdName(String departmentIdName) {
        this.departmentIdName = departmentIdName;
    }

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    public Integer getUrgency() {
        return urgency;
    }

    public void setUrgency(Integer urgency) {
        this.urgency = urgency;
    }

    public String getAttachmentProjectInfoId() {
        return attachmentProjectInfoId;
    }

    public void setAttachmentProjectInfoId(String attachmentProjectInfoId) {
        this.attachmentProjectInfoId = attachmentProjectInfoId;
    }

    public String getUserAccountManager() {
        return userAccountManager;
    }

    public void setUserAccountManager(String userAccountManager) {
        this.userAccountManager = userAccountManager;
    }

    public String getUserAccountMember() {
        return userAccountMember;
    }

    public void setUserAccountMember(String userAccountMember) {
        this.userAccountMember = userAccountMember;
    }

    public Date getValuationDate() {
        return valuationDate;
    }

    public void setValuationDate(Date valuationDate) {
        this.valuationDate = valuationDate;
    }
}
