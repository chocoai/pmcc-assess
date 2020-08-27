package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class GenerateReportInfo implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 
     */
    private Integer projectPlanId;

    /**
     * 资质类型
     */
    private String qualificationType;

    /**
     * 现场查勘开始日期
     */
    private Date investigationsStartDate;

    /**
     * 现场查勘结束日期
     */
    private Date investigationsEndDate;

    /**
     * 报告出具日期
     */
    private Date reportIssuanceDate;

    /**
     * 作业开始时间
     */
    private Date homeWorkStartTime;

    /**
     * 作业结束时间
     */
    private Date homeWorkEndTime;

    /**
     * 房地产估价师
     */
    private String realEstateAppraiser;

    /**
     * 区域分组id
     */
    private Integer areaGroupId;

    /**
     * 流程id
     */
    private String processInsId;

    /**
     * 查询码
     */
    private String queryCode;

    /**
     * 备案日期
     */
    private Date recordDate;

    /**
     * 备案号
     */
    private String recordNo;

    /**
     * 报告类型 (这的类型不要改为int,因为是select多选)
     */
    private String reportType;

    /**
     * 评估类别
     */
    private Integer assessCategory;

    /**
     * 文号操作(get表示拿号,reset表示重新拿号,none表示不拿号)
     */
    private String symbolOperation;

    /**
     * 流程状态
     */
    private String status;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     */
    private Date gmtModified;

    /**
     * tb_generate_report_info
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 项目id
     * @return project_id 项目id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 项目id
     * @param projectId 项目id
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 
     * @return project_plan_id 
     */
    public Integer getProjectPlanId() {
        return projectPlanId;
    }

    /**
     * 
     * @param projectPlanId 
     */
    public void setProjectPlanId(Integer projectPlanId) {
        this.projectPlanId = projectPlanId;
    }

    /**
     * 资质类型
     * @return qualification_type 资质类型
     */
    public String getQualificationType() {
        return qualificationType;
    }

    /**
     * 资质类型
     * @param qualificationType 资质类型
     */
    public void setQualificationType(String qualificationType) {
        this.qualificationType = qualificationType == null ? null : qualificationType.trim();
    }

    /**
     * 现场查勘开始日期
     * @return investigations_start_date 现场查勘开始日期
     */
    public Date getInvestigationsStartDate() {
        return investigationsStartDate;
    }

    /**
     * 现场查勘开始日期
     * @param investigationsStartDate 现场查勘开始日期
     */
    public void setInvestigationsStartDate(Date investigationsStartDate) {
        this.investigationsStartDate = investigationsStartDate;
    }

    /**
     * 现场查勘结束日期
     * @return investigations_end_date 现场查勘结束日期
     */
    public Date getInvestigationsEndDate() {
        return investigationsEndDate;
    }

    /**
     * 现场查勘结束日期
     * @param investigationsEndDate 现场查勘结束日期
     */
    public void setInvestigationsEndDate(Date investigationsEndDate) {
        this.investigationsEndDate = investigationsEndDate;
    }

    /**
     * 报告出具日期
     * @return report_issuance_date 报告出具日期
     */
    public Date getReportIssuanceDate() {
        return reportIssuanceDate;
    }

    /**
     * 报告出具日期
     * @param reportIssuanceDate 报告出具日期
     */
    public void setReportIssuanceDate(Date reportIssuanceDate) {
        this.reportIssuanceDate = reportIssuanceDate;
    }

    /**
     * 作业开始时间
     * @return home_work_start_time 作业开始时间
     */
    public Date getHomeWorkStartTime() {
        return homeWorkStartTime;
    }

    /**
     * 作业开始时间
     * @param homeWorkStartTime 作业开始时间
     */
    public void setHomeWorkStartTime(Date homeWorkStartTime) {
        this.homeWorkStartTime = homeWorkStartTime;
    }

    /**
     * 作业结束时间
     * @return home_work_end_time 作业结束时间
     */
    public Date getHomeWorkEndTime() {
        return homeWorkEndTime;
    }

    /**
     * 作业结束时间
     * @param homeWorkEndTime 作业结束时间
     */
    public void setHomeWorkEndTime(Date homeWorkEndTime) {
        this.homeWorkEndTime = homeWorkEndTime;
    }

    /**
     * 房地产估价师
     * @return real_estate_appraiser 房地产估价师
     */
    public String getRealEstateAppraiser() {
        return realEstateAppraiser;
    }

    /**
     * 房地产估价师
     * @param realEstateAppraiser 房地产估价师
     */
    public void setRealEstateAppraiser(String realEstateAppraiser) {
        this.realEstateAppraiser = realEstateAppraiser == null ? null : realEstateAppraiser.trim();
    }

    /**
     * 区域分组id
     * @return area_group_id 区域分组id
     */
    public Integer getAreaGroupId() {
        return areaGroupId;
    }

    /**
     * 区域分组id
     * @param areaGroupId 区域分组id
     */
    public void setAreaGroupId(Integer areaGroupId) {
        this.areaGroupId = areaGroupId;
    }

    /**
     * 流程id
     * @return process_ins_id 流程id
     */
    public String getProcessInsId() {
        return processInsId;
    }

    /**
     * 流程id
     * @param processInsId 流程id
     */
    public void setProcessInsId(String processInsId) {
        this.processInsId = processInsId == null ? null : processInsId.trim();
    }

    /**
     * 查询码
     * @return query_code 查询码
     */
    public String getQueryCode() {
        return queryCode;
    }

    /**
     * 查询码
     * @param queryCode 查询码
     */
    public void setQueryCode(String queryCode) {
        this.queryCode = queryCode == null ? null : queryCode.trim();
    }

    /**
     * 备案日期
     * @return record_date 备案日期
     */
    public Date getRecordDate() {
        return recordDate;
    }

    /**
     * 备案日期
     * @param recordDate 备案日期
     */
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    /**
     * 备案号
     * @return record_no 备案号
     */
    public String getRecordNo() {
        return recordNo;
    }

    /**
     * 备案号
     * @param recordNo 备案号
     */
    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo == null ? null : recordNo.trim();
    }

    /**
     * 报告类型 (这的类型不要改为int,因为是select多选)
     * @return report_type 报告类型 (这的类型不要改为int,因为是select多选)
     */
    public String getReportType() {
        return reportType;
    }

    /**
     * 报告类型 (这的类型不要改为int,因为是select多选)
     * @param reportType 报告类型 (这的类型不要改为int,因为是select多选)
     */
    public void setReportType(String reportType) {
        this.reportType = reportType == null ? null : reportType.trim();
    }

    /**
     * 评估类别
     * @return assess_category 评估类别
     */
    public Integer getAssessCategory() {
        return assessCategory;
    }

    /**
     * 评估类别
     * @param assessCategory 评估类别
     */
    public void setAssessCategory(Integer assessCategory) {
        this.assessCategory = assessCategory;
    }

    /**
     * 文号操作(get表示拿号,reset表示重新拿号,none表示不拿号)
     * @return symbol_operation 文号操作(get表示拿号,reset表示重新拿号,none表示不拿号)
     */
    public String getSymbolOperation() {
        return symbolOperation;
    }

    /**
     * 文号操作(get表示拿号,reset表示重新拿号,none表示不拿号)
     * @param symbolOperation 文号操作(get表示拿号,reset表示重新拿号,none表示不拿号)
     */
    public void setSymbolOperation(String symbolOperation) {
        this.symbolOperation = symbolOperation == null ? null : symbolOperation.trim();
    }

    /**
     * 流程状态
     * @return status 流程状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 流程状态
     * @param status 流程状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 创建人
     * @return creator 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间
     * @return gmt_created 创建时间
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * 创建时间
     * @param gmtCreated 创建时间
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     * @return gmt_modified 最后更新时间，记录变化后会自动更新时间戳
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     * @param gmtModified 最后更新时间，记录变化后会自动更新时间戳
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public static GenerateReportInfo.Builder builder() {
        return new GenerateReportInfo.Builder();
    }

    /**
     * tb_generate_report_info
     */
    public static class Builder {
        /**
         * tb_generate_report_info
         */
        private GenerateReportInfo obj;

        public Builder() {
            this.obj = new GenerateReportInfo();
        }

        /**
         * 
         * @param id 
         */
        public Builder id(Integer id) {
            obj.setId(id);
            return this;
        }

        /**
         * 项目id
         * @param projectId 项目id
         */
        public Builder projectId(Integer projectId) {
            obj.setProjectId(projectId);
            return this;
        }

        /**
         * 
         * @param projectPlanId 
         */
        public Builder projectPlanId(Integer projectPlanId) {
            obj.setProjectPlanId(projectPlanId);
            return this;
        }

        /**
         * 资质类型
         * @param qualificationType 资质类型
         */
        public Builder qualificationType(String qualificationType) {
            obj.setQualificationType(qualificationType);
            return this;
        }

        /**
         * 现场查勘开始日期
         * @param investigationsStartDate 现场查勘开始日期
         */
        public Builder investigationsStartDate(Date investigationsStartDate) {
            obj.setInvestigationsStartDate(investigationsStartDate);
            return this;
        }

        /**
         * 现场查勘结束日期
         * @param investigationsEndDate 现场查勘结束日期
         */
        public Builder investigationsEndDate(Date investigationsEndDate) {
            obj.setInvestigationsEndDate(investigationsEndDate);
            return this;
        }

        /**
         * 报告出具日期
         * @param reportIssuanceDate 报告出具日期
         */
        public Builder reportIssuanceDate(Date reportIssuanceDate) {
            obj.setReportIssuanceDate(reportIssuanceDate);
            return this;
        }

        /**
         * 作业开始时间
         * @param homeWorkStartTime 作业开始时间
         */
        public Builder homeWorkStartTime(Date homeWorkStartTime) {
            obj.setHomeWorkStartTime(homeWorkStartTime);
            return this;
        }

        /**
         * 作业结束时间
         * @param homeWorkEndTime 作业结束时间
         */
        public Builder homeWorkEndTime(Date homeWorkEndTime) {
            obj.setHomeWorkEndTime(homeWorkEndTime);
            return this;
        }

        /**
         * 房地产估价师
         * @param realEstateAppraiser 房地产估价师
         */
        public Builder realEstateAppraiser(String realEstateAppraiser) {
            obj.setRealEstateAppraiser(realEstateAppraiser);
            return this;
        }

        /**
         * 区域分组id
         * @param areaGroupId 区域分组id
         */
        public Builder areaGroupId(Integer areaGroupId) {
            obj.setAreaGroupId(areaGroupId);
            return this;
        }

        /**
         * 流程id
         * @param processInsId 流程id
         */
        public Builder processInsId(String processInsId) {
            obj.setProcessInsId(processInsId);
            return this;
        }

        /**
         * 查询码
         * @param queryCode 查询码
         */
        public Builder queryCode(String queryCode) {
            obj.setQueryCode(queryCode);
            return this;
        }

        /**
         * 备案日期
         * @param recordDate 备案日期
         */
        public Builder recordDate(Date recordDate) {
            obj.setRecordDate(recordDate);
            return this;
        }

        /**
         * 备案号
         * @param recordNo 备案号
         */
        public Builder recordNo(String recordNo) {
            obj.setRecordNo(recordNo);
            return this;
        }

        /**
         * 报告类型 (这的类型不要改为int,因为是select多选)
         * @param reportType 报告类型 (这的类型不要改为int,因为是select多选)
         */
        public Builder reportType(String reportType) {
            obj.setReportType(reportType);
            return this;
        }

        /**
         * 评估类别
         * @param assessCategory 评估类别
         */
        public Builder assessCategory(Integer assessCategory) {
            obj.setAssessCategory(assessCategory);
            return this;
        }

        /**
         * 文号操作(get表示拿号,reset表示重新拿号,none表示不拿号)
         * @param symbolOperation 文号操作(get表示拿号,reset表示重新拿号,none表示不拿号)
         */
        public Builder symbolOperation(String symbolOperation) {
            obj.setSymbolOperation(symbolOperation);
            return this;
        }

        /**
         * 流程状态
         * @param status 流程状态
         */
        public Builder status(String status) {
            obj.setStatus(status);
            return this;
        }

        /**
         * 创建人
         * @param creator 创建人
         */
        public Builder creator(String creator) {
            obj.setCreator(creator);
            return this;
        }

        /**
         * 创建时间
         * @param gmtCreated 创建时间
         */
        public Builder gmtCreated(Date gmtCreated) {
            obj.setGmtCreated(gmtCreated);
            return this;
        }

        /**
         * 最后更新时间，记录变化后会自动更新时间戳
         * @param gmtModified 最后更新时间，记录变化后会自动更新时间戳
         */
        public Builder gmtModified(Date gmtModified) {
            obj.setGmtModified(gmtModified);
            return this;
        }

        public GenerateReportInfo build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        projectId("project_id", "projectId", "INTEGER", false),
        projectPlanId("project_plan_id", "projectPlanId", "INTEGER", false),
        qualificationType("qualification_type", "qualificationType", "VARCHAR", false),
        investigationsStartDate("investigations_start_date", "investigationsStartDate", "TIMESTAMP", false),
        investigationsEndDate("investigations_end_date", "investigationsEndDate", "TIMESTAMP", false),
        reportIssuanceDate("report_issuance_date", "reportIssuanceDate", "TIMESTAMP", false),
        homeWorkStartTime("home_work_start_time", "homeWorkStartTime", "TIMESTAMP", false),
        homeWorkEndTime("home_work_end_time", "homeWorkEndTime", "TIMESTAMP", false),
        realEstateAppraiser("real_estate_appraiser", "realEstateAppraiser", "VARCHAR", false),
        areaGroupId("area_group_id", "areaGroupId", "INTEGER", false),
        processInsId("process_ins_id", "processInsId", "VARCHAR", false),
        queryCode("query_code", "queryCode", "VARCHAR", false),
        recordDate("record_date", "recordDate", "TIMESTAMP", false),
        recordNo("record_no", "recordNo", "VARCHAR", false),
        reportType("report_type", "reportType", "VARCHAR", false),
        assessCategory("assess_category", "assessCategory", "INTEGER", false),
        symbolOperation("symbol_operation", "symbolOperation", "VARCHAR", false),
        status("status", "status", "VARCHAR", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_generate_report_info
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_generate_report_info
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_generate_report_info
         */
        private final String column;

        /**
         * tb_generate_report_info
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_generate_report_info
         */
        private final String javaProperty;

        /**
         * tb_generate_report_info
         */
        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}