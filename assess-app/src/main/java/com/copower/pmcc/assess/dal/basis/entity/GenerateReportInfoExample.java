package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenerateReportInfoExample {
    /**
     * tb_generate_report_info
     */
    protected String orderByClause;

    /**
     * tb_generate_report_info
     */
    protected boolean distinct;

    /**
     * tb_generate_report_info
     */
    protected List<Criteria> oredCriteria;

    public GenerateReportInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * tb_generate_report_info
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Integer value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Integer value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Integer value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Integer value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Integer> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Integer> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdIsNull() {
            addCriterion("project_plan_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdIsNotNull() {
            addCriterion("project_plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdEqualTo(Integer value) {
            addCriterion("project_plan_id =", value, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdNotEqualTo(Integer value) {
            addCriterion("project_plan_id <>", value, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdGreaterThan(Integer value) {
            addCriterion("project_plan_id >", value, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_plan_id >=", value, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdLessThan(Integer value) {
            addCriterion("project_plan_id <", value, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_plan_id <=", value, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdIn(List<Integer> values) {
            addCriterion("project_plan_id in", values, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdNotIn(List<Integer> values) {
            addCriterion("project_plan_id not in", values, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdBetween(Integer value1, Integer value2) {
            addCriterion("project_plan_id between", value1, value2, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andProjectPlanIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_plan_id not between", value1, value2, "projectPlanId");
            return (Criteria) this;
        }

        public Criteria andQualificationTypeIsNull() {
            addCriterion("qualification_type is null");
            return (Criteria) this;
        }

        public Criteria andQualificationTypeIsNotNull() {
            addCriterion("qualification_type is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationTypeEqualTo(String value) {
            addCriterion("qualification_type =", value, "qualificationType");
            return (Criteria) this;
        }

        public Criteria andQualificationTypeNotEqualTo(String value) {
            addCriterion("qualification_type <>", value, "qualificationType");
            return (Criteria) this;
        }

        public Criteria andQualificationTypeGreaterThan(String value) {
            addCriterion("qualification_type >", value, "qualificationType");
            return (Criteria) this;
        }

        public Criteria andQualificationTypeGreaterThanOrEqualTo(String value) {
            addCriterion("qualification_type >=", value, "qualificationType");
            return (Criteria) this;
        }

        public Criteria andQualificationTypeLessThan(String value) {
            addCriterion("qualification_type <", value, "qualificationType");
            return (Criteria) this;
        }

        public Criteria andQualificationTypeLessThanOrEqualTo(String value) {
            addCriterion("qualification_type <=", value, "qualificationType");
            return (Criteria) this;
        }

        public Criteria andQualificationTypeLike(String value) {
            addCriterion("qualification_type like", value, "qualificationType");
            return (Criteria) this;
        }

        public Criteria andQualificationTypeNotLike(String value) {
            addCriterion("qualification_type not like", value, "qualificationType");
            return (Criteria) this;
        }

        public Criteria andQualificationTypeIn(List<String> values) {
            addCriterion("qualification_type in", values, "qualificationType");
            return (Criteria) this;
        }

        public Criteria andQualificationTypeNotIn(List<String> values) {
            addCriterion("qualification_type not in", values, "qualificationType");
            return (Criteria) this;
        }

        public Criteria andQualificationTypeBetween(String value1, String value2) {
            addCriterion("qualification_type between", value1, value2, "qualificationType");
            return (Criteria) this;
        }

        public Criteria andQualificationTypeNotBetween(String value1, String value2) {
            addCriterion("qualification_type not between", value1, value2, "qualificationType");
            return (Criteria) this;
        }

        public Criteria andInvestigationsStartDateIsNull() {
            addCriterion("investigations_start_date is null");
            return (Criteria) this;
        }

        public Criteria andInvestigationsStartDateIsNotNull() {
            addCriterion("investigations_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andInvestigationsStartDateEqualTo(Date value) {
            addCriterion("investigations_start_date =", value, "investigationsStartDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsStartDateNotEqualTo(Date value) {
            addCriterion("investigations_start_date <>", value, "investigationsStartDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsStartDateGreaterThan(Date value) {
            addCriterion("investigations_start_date >", value, "investigationsStartDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("investigations_start_date >=", value, "investigationsStartDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsStartDateLessThan(Date value) {
            addCriterion("investigations_start_date <", value, "investigationsStartDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsStartDateLessThanOrEqualTo(Date value) {
            addCriterion("investigations_start_date <=", value, "investigationsStartDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsStartDateIn(List<Date> values) {
            addCriterion("investigations_start_date in", values, "investigationsStartDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsStartDateNotIn(List<Date> values) {
            addCriterion("investigations_start_date not in", values, "investigationsStartDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsStartDateBetween(Date value1, Date value2) {
            addCriterion("investigations_start_date between", value1, value2, "investigationsStartDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsStartDateNotBetween(Date value1, Date value2) {
            addCriterion("investigations_start_date not between", value1, value2, "investigationsStartDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsEndDateIsNull() {
            addCriterion("investigations_end_date is null");
            return (Criteria) this;
        }

        public Criteria andInvestigationsEndDateIsNotNull() {
            addCriterion("investigations_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andInvestigationsEndDateEqualTo(Date value) {
            addCriterion("investigations_end_date =", value, "investigationsEndDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsEndDateNotEqualTo(Date value) {
            addCriterion("investigations_end_date <>", value, "investigationsEndDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsEndDateGreaterThan(Date value) {
            addCriterion("investigations_end_date >", value, "investigationsEndDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("investigations_end_date >=", value, "investigationsEndDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsEndDateLessThan(Date value) {
            addCriterion("investigations_end_date <", value, "investigationsEndDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsEndDateLessThanOrEqualTo(Date value) {
            addCriterion("investigations_end_date <=", value, "investigationsEndDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsEndDateIn(List<Date> values) {
            addCriterion("investigations_end_date in", values, "investigationsEndDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsEndDateNotIn(List<Date> values) {
            addCriterion("investigations_end_date not in", values, "investigationsEndDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsEndDateBetween(Date value1, Date value2) {
            addCriterion("investigations_end_date between", value1, value2, "investigationsEndDate");
            return (Criteria) this;
        }

        public Criteria andInvestigationsEndDateNotBetween(Date value1, Date value2) {
            addCriterion("investigations_end_date not between", value1, value2, "investigationsEndDate");
            return (Criteria) this;
        }

        public Criteria andReportIssuanceDateIsNull() {
            addCriterion("report_issuance_date is null");
            return (Criteria) this;
        }

        public Criteria andReportIssuanceDateIsNotNull() {
            addCriterion("report_issuance_date is not null");
            return (Criteria) this;
        }

        public Criteria andReportIssuanceDateEqualTo(Date value) {
            addCriterion("report_issuance_date =", value, "reportIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andReportIssuanceDateNotEqualTo(Date value) {
            addCriterion("report_issuance_date <>", value, "reportIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andReportIssuanceDateGreaterThan(Date value) {
            addCriterion("report_issuance_date >", value, "reportIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andReportIssuanceDateGreaterThanOrEqualTo(Date value) {
            addCriterion("report_issuance_date >=", value, "reportIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andReportIssuanceDateLessThan(Date value) {
            addCriterion("report_issuance_date <", value, "reportIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andReportIssuanceDateLessThanOrEqualTo(Date value) {
            addCriterion("report_issuance_date <=", value, "reportIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andReportIssuanceDateIn(List<Date> values) {
            addCriterion("report_issuance_date in", values, "reportIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andReportIssuanceDateNotIn(List<Date> values) {
            addCriterion("report_issuance_date not in", values, "reportIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andReportIssuanceDateBetween(Date value1, Date value2) {
            addCriterion("report_issuance_date between", value1, value2, "reportIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andReportIssuanceDateNotBetween(Date value1, Date value2) {
            addCriterion("report_issuance_date not between", value1, value2, "reportIssuanceDate");
            return (Criteria) this;
        }

        public Criteria andHomeWorkStartTimeIsNull() {
            addCriterion("home_work_start_time is null");
            return (Criteria) this;
        }

        public Criteria andHomeWorkStartTimeIsNotNull() {
            addCriterion("home_work_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andHomeWorkStartTimeEqualTo(Date value) {
            addCriterion("home_work_start_time =", value, "homeWorkStartTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkStartTimeNotEqualTo(Date value) {
            addCriterion("home_work_start_time <>", value, "homeWorkStartTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkStartTimeGreaterThan(Date value) {
            addCriterion("home_work_start_time >", value, "homeWorkStartTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("home_work_start_time >=", value, "homeWorkStartTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkStartTimeLessThan(Date value) {
            addCriterion("home_work_start_time <", value, "homeWorkStartTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("home_work_start_time <=", value, "homeWorkStartTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkStartTimeIn(List<Date> values) {
            addCriterion("home_work_start_time in", values, "homeWorkStartTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkStartTimeNotIn(List<Date> values) {
            addCriterion("home_work_start_time not in", values, "homeWorkStartTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkStartTimeBetween(Date value1, Date value2) {
            addCriterion("home_work_start_time between", value1, value2, "homeWorkStartTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("home_work_start_time not between", value1, value2, "homeWorkStartTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkEndTimeIsNull() {
            addCriterion("home_work_end_time is null");
            return (Criteria) this;
        }

        public Criteria andHomeWorkEndTimeIsNotNull() {
            addCriterion("home_work_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andHomeWorkEndTimeEqualTo(Date value) {
            addCriterion("home_work_end_time =", value, "homeWorkEndTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkEndTimeNotEqualTo(Date value) {
            addCriterion("home_work_end_time <>", value, "homeWorkEndTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkEndTimeGreaterThan(Date value) {
            addCriterion("home_work_end_time >", value, "homeWorkEndTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("home_work_end_time >=", value, "homeWorkEndTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkEndTimeLessThan(Date value) {
            addCriterion("home_work_end_time <", value, "homeWorkEndTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("home_work_end_time <=", value, "homeWorkEndTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkEndTimeIn(List<Date> values) {
            addCriterion("home_work_end_time in", values, "homeWorkEndTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkEndTimeNotIn(List<Date> values) {
            addCriterion("home_work_end_time not in", values, "homeWorkEndTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkEndTimeBetween(Date value1, Date value2) {
            addCriterion("home_work_end_time between", value1, value2, "homeWorkEndTime");
            return (Criteria) this;
        }

        public Criteria andHomeWorkEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("home_work_end_time not between", value1, value2, "homeWorkEndTime");
            return (Criteria) this;
        }

        public Criteria andRealEstateAppraiserIsNull() {
            addCriterion("real_estate_appraiser is null");
            return (Criteria) this;
        }

        public Criteria andRealEstateAppraiserIsNotNull() {
            addCriterion("real_estate_appraiser is not null");
            return (Criteria) this;
        }

        public Criteria andRealEstateAppraiserEqualTo(String value) {
            addCriterion("real_estate_appraiser =", value, "realEstateAppraiser");
            return (Criteria) this;
        }

        public Criteria andRealEstateAppraiserNotEqualTo(String value) {
            addCriterion("real_estate_appraiser <>", value, "realEstateAppraiser");
            return (Criteria) this;
        }

        public Criteria andRealEstateAppraiserGreaterThan(String value) {
            addCriterion("real_estate_appraiser >", value, "realEstateAppraiser");
            return (Criteria) this;
        }

        public Criteria andRealEstateAppraiserGreaterThanOrEqualTo(String value) {
            addCriterion("real_estate_appraiser >=", value, "realEstateAppraiser");
            return (Criteria) this;
        }

        public Criteria andRealEstateAppraiserLessThan(String value) {
            addCriterion("real_estate_appraiser <", value, "realEstateAppraiser");
            return (Criteria) this;
        }

        public Criteria andRealEstateAppraiserLessThanOrEqualTo(String value) {
            addCriterion("real_estate_appraiser <=", value, "realEstateAppraiser");
            return (Criteria) this;
        }

        public Criteria andRealEstateAppraiserLike(String value) {
            addCriterion("real_estate_appraiser like", value, "realEstateAppraiser");
            return (Criteria) this;
        }

        public Criteria andRealEstateAppraiserNotLike(String value) {
            addCriterion("real_estate_appraiser not like", value, "realEstateAppraiser");
            return (Criteria) this;
        }

        public Criteria andRealEstateAppraiserIn(List<String> values) {
            addCriterion("real_estate_appraiser in", values, "realEstateAppraiser");
            return (Criteria) this;
        }

        public Criteria andRealEstateAppraiserNotIn(List<String> values) {
            addCriterion("real_estate_appraiser not in", values, "realEstateAppraiser");
            return (Criteria) this;
        }

        public Criteria andRealEstateAppraiserBetween(String value1, String value2) {
            addCriterion("real_estate_appraiser between", value1, value2, "realEstateAppraiser");
            return (Criteria) this;
        }

        public Criteria andRealEstateAppraiserNotBetween(String value1, String value2) {
            addCriterion("real_estate_appraiser not between", value1, value2, "realEstateAppraiser");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdIsNull() {
            addCriterion("area_group_id is null");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdIsNotNull() {
            addCriterion("area_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdEqualTo(Integer value) {
            addCriterion("area_group_id =", value, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdNotEqualTo(Integer value) {
            addCriterion("area_group_id <>", value, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdGreaterThan(Integer value) {
            addCriterion("area_group_id >", value, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("area_group_id >=", value, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdLessThan(Integer value) {
            addCriterion("area_group_id <", value, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("area_group_id <=", value, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdIn(List<Integer> values) {
            addCriterion("area_group_id in", values, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdNotIn(List<Integer> values) {
            addCriterion("area_group_id not in", values, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("area_group_id between", value1, value2, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("area_group_id not between", value1, value2, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdIsNull() {
            addCriterion("process_ins_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdIsNotNull() {
            addCriterion("process_ins_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdEqualTo(String value) {
            addCriterion("process_ins_id =", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotEqualTo(String value) {
            addCriterion("process_ins_id <>", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdGreaterThan(String value) {
            addCriterion("process_ins_id >", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdGreaterThanOrEqualTo(String value) {
            addCriterion("process_ins_id >=", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLessThan(String value) {
            addCriterion("process_ins_id <", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLessThanOrEqualTo(String value) {
            addCriterion("process_ins_id <=", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLike(String value) {
            addCriterion("process_ins_id like", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotLike(String value) {
            addCriterion("process_ins_id not like", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdIn(List<String> values) {
            addCriterion("process_ins_id in", values, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotIn(List<String> values) {
            addCriterion("process_ins_id not in", values, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdBetween(String value1, String value2) {
            addCriterion("process_ins_id between", value1, value2, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotBetween(String value1, String value2) {
            addCriterion("process_ins_id not between", value1, value2, "processInsId");
            return (Criteria) this;
        }

        public Criteria andQueryCodeIsNull() {
            addCriterion("query_code is null");
            return (Criteria) this;
        }

        public Criteria andQueryCodeIsNotNull() {
            addCriterion("query_code is not null");
            return (Criteria) this;
        }

        public Criteria andQueryCodeEqualTo(String value) {
            addCriterion("query_code =", value, "queryCode");
            return (Criteria) this;
        }

        public Criteria andQueryCodeNotEqualTo(String value) {
            addCriterion("query_code <>", value, "queryCode");
            return (Criteria) this;
        }

        public Criteria andQueryCodeGreaterThan(String value) {
            addCriterion("query_code >", value, "queryCode");
            return (Criteria) this;
        }

        public Criteria andQueryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("query_code >=", value, "queryCode");
            return (Criteria) this;
        }

        public Criteria andQueryCodeLessThan(String value) {
            addCriterion("query_code <", value, "queryCode");
            return (Criteria) this;
        }

        public Criteria andQueryCodeLessThanOrEqualTo(String value) {
            addCriterion("query_code <=", value, "queryCode");
            return (Criteria) this;
        }

        public Criteria andQueryCodeLike(String value) {
            addCriterion("query_code like", value, "queryCode");
            return (Criteria) this;
        }

        public Criteria andQueryCodeNotLike(String value) {
            addCriterion("query_code not like", value, "queryCode");
            return (Criteria) this;
        }

        public Criteria andQueryCodeIn(List<String> values) {
            addCriterion("query_code in", values, "queryCode");
            return (Criteria) this;
        }

        public Criteria andQueryCodeNotIn(List<String> values) {
            addCriterion("query_code not in", values, "queryCode");
            return (Criteria) this;
        }

        public Criteria andQueryCodeBetween(String value1, String value2) {
            addCriterion("query_code between", value1, value2, "queryCode");
            return (Criteria) this;
        }

        public Criteria andQueryCodeNotBetween(String value1, String value2) {
            addCriterion("query_code not between", value1, value2, "queryCode");
            return (Criteria) this;
        }

        public Criteria andRecordDateIsNull() {
            addCriterion("record_date is null");
            return (Criteria) this;
        }

        public Criteria andRecordDateIsNotNull() {
            addCriterion("record_date is not null");
            return (Criteria) this;
        }

        public Criteria andRecordDateEqualTo(Date value) {
            addCriterion("record_date =", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateNotEqualTo(Date value) {
            addCriterion("record_date <>", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateGreaterThan(Date value) {
            addCriterion("record_date >", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateGreaterThanOrEqualTo(Date value) {
            addCriterion("record_date >=", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateLessThan(Date value) {
            addCriterion("record_date <", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateLessThanOrEqualTo(Date value) {
            addCriterion("record_date <=", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateIn(List<Date> values) {
            addCriterion("record_date in", values, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateNotIn(List<Date> values) {
            addCriterion("record_date not in", values, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateBetween(Date value1, Date value2) {
            addCriterion("record_date between", value1, value2, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateNotBetween(Date value1, Date value2) {
            addCriterion("record_date not between", value1, value2, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordNoIsNull() {
            addCriterion("record_no is null");
            return (Criteria) this;
        }

        public Criteria andRecordNoIsNotNull() {
            addCriterion("record_no is not null");
            return (Criteria) this;
        }

        public Criteria andRecordNoEqualTo(String value) {
            addCriterion("record_no =", value, "recordNo");
            return (Criteria) this;
        }

        public Criteria andRecordNoNotEqualTo(String value) {
            addCriterion("record_no <>", value, "recordNo");
            return (Criteria) this;
        }

        public Criteria andRecordNoGreaterThan(String value) {
            addCriterion("record_no >", value, "recordNo");
            return (Criteria) this;
        }

        public Criteria andRecordNoGreaterThanOrEqualTo(String value) {
            addCriterion("record_no >=", value, "recordNo");
            return (Criteria) this;
        }

        public Criteria andRecordNoLessThan(String value) {
            addCriterion("record_no <", value, "recordNo");
            return (Criteria) this;
        }

        public Criteria andRecordNoLessThanOrEqualTo(String value) {
            addCriterion("record_no <=", value, "recordNo");
            return (Criteria) this;
        }

        public Criteria andRecordNoLike(String value) {
            addCriterion("record_no like", value, "recordNo");
            return (Criteria) this;
        }

        public Criteria andRecordNoNotLike(String value) {
            addCriterion("record_no not like", value, "recordNo");
            return (Criteria) this;
        }

        public Criteria andRecordNoIn(List<String> values) {
            addCriterion("record_no in", values, "recordNo");
            return (Criteria) this;
        }

        public Criteria andRecordNoNotIn(List<String> values) {
            addCriterion("record_no not in", values, "recordNo");
            return (Criteria) this;
        }

        public Criteria andRecordNoBetween(String value1, String value2) {
            addCriterion("record_no between", value1, value2, "recordNo");
            return (Criteria) this;
        }

        public Criteria andRecordNoNotBetween(String value1, String value2) {
            addCriterion("record_no not between", value1, value2, "recordNo");
            return (Criteria) this;
        }

        public Criteria andReportTypeIsNull() {
            addCriterion("report_type is null");
            return (Criteria) this;
        }

        public Criteria andReportTypeIsNotNull() {
            addCriterion("report_type is not null");
            return (Criteria) this;
        }

        public Criteria andReportTypeEqualTo(String value) {
            addCriterion("report_type =", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotEqualTo(String value) {
            addCriterion("report_type <>", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeGreaterThan(String value) {
            addCriterion("report_type >", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeGreaterThanOrEqualTo(String value) {
            addCriterion("report_type >=", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeLessThan(String value) {
            addCriterion("report_type <", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeLessThanOrEqualTo(String value) {
            addCriterion("report_type <=", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeLike(String value) {
            addCriterion("report_type like", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotLike(String value) {
            addCriterion("report_type not like", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeIn(List<String> values) {
            addCriterion("report_type in", values, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotIn(List<String> values) {
            addCriterion("report_type not in", values, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeBetween(String value1, String value2) {
            addCriterion("report_type between", value1, value2, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotBetween(String value1, String value2) {
            addCriterion("report_type not between", value1, value2, "reportType");
            return (Criteria) this;
        }

        public Criteria andAssessCategoryIsNull() {
            addCriterion("assess_category is null");
            return (Criteria) this;
        }

        public Criteria andAssessCategoryIsNotNull() {
            addCriterion("assess_category is not null");
            return (Criteria) this;
        }

        public Criteria andAssessCategoryEqualTo(Integer value) {
            addCriterion("assess_category =", value, "assessCategory");
            return (Criteria) this;
        }

        public Criteria andAssessCategoryNotEqualTo(Integer value) {
            addCriterion("assess_category <>", value, "assessCategory");
            return (Criteria) this;
        }

        public Criteria andAssessCategoryGreaterThan(Integer value) {
            addCriterion("assess_category >", value, "assessCategory");
            return (Criteria) this;
        }

        public Criteria andAssessCategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("assess_category >=", value, "assessCategory");
            return (Criteria) this;
        }

        public Criteria andAssessCategoryLessThan(Integer value) {
            addCriterion("assess_category <", value, "assessCategory");
            return (Criteria) this;
        }

        public Criteria andAssessCategoryLessThanOrEqualTo(Integer value) {
            addCriterion("assess_category <=", value, "assessCategory");
            return (Criteria) this;
        }

        public Criteria andAssessCategoryIn(List<Integer> values) {
            addCriterion("assess_category in", values, "assessCategory");
            return (Criteria) this;
        }

        public Criteria andAssessCategoryNotIn(List<Integer> values) {
            addCriterion("assess_category not in", values, "assessCategory");
            return (Criteria) this;
        }

        public Criteria andAssessCategoryBetween(Integer value1, Integer value2) {
            addCriterion("assess_category between", value1, value2, "assessCategory");
            return (Criteria) this;
        }

        public Criteria andAssessCategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("assess_category not between", value1, value2, "assessCategory");
            return (Criteria) this;
        }

        public Criteria andSymbolOperationIsNull() {
            addCriterion("symbol_operation is null");
            return (Criteria) this;
        }

        public Criteria andSymbolOperationIsNotNull() {
            addCriterion("symbol_operation is not null");
            return (Criteria) this;
        }

        public Criteria andSymbolOperationEqualTo(String value) {
            addCriterion("symbol_operation =", value, "symbolOperation");
            return (Criteria) this;
        }

        public Criteria andSymbolOperationNotEqualTo(String value) {
            addCriterion("symbol_operation <>", value, "symbolOperation");
            return (Criteria) this;
        }

        public Criteria andSymbolOperationGreaterThan(String value) {
            addCriterion("symbol_operation >", value, "symbolOperation");
            return (Criteria) this;
        }

        public Criteria andSymbolOperationGreaterThanOrEqualTo(String value) {
            addCriterion("symbol_operation >=", value, "symbolOperation");
            return (Criteria) this;
        }

        public Criteria andSymbolOperationLessThan(String value) {
            addCriterion("symbol_operation <", value, "symbolOperation");
            return (Criteria) this;
        }

        public Criteria andSymbolOperationLessThanOrEqualTo(String value) {
            addCriterion("symbol_operation <=", value, "symbolOperation");
            return (Criteria) this;
        }

        public Criteria andSymbolOperationLike(String value) {
            addCriterion("symbol_operation like", value, "symbolOperation");
            return (Criteria) this;
        }

        public Criteria andSymbolOperationNotLike(String value) {
            addCriterion("symbol_operation not like", value, "symbolOperation");
            return (Criteria) this;
        }

        public Criteria andSymbolOperationIn(List<String> values) {
            addCriterion("symbol_operation in", values, "symbolOperation");
            return (Criteria) this;
        }

        public Criteria andSymbolOperationNotIn(List<String> values) {
            addCriterion("symbol_operation not in", values, "symbolOperation");
            return (Criteria) this;
        }

        public Criteria andSymbolOperationBetween(String value1, String value2) {
            addCriterion("symbol_operation between", value1, value2, "symbolOperation");
            return (Criteria) this;
        }

        public Criteria andSymbolOperationNotBetween(String value1, String value2) {
            addCriterion("symbol_operation not between", value1, value2, "symbolOperation");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIsNull() {
            addCriterion("gmt_created is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIsNotNull() {
            addCriterion("gmt_created is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedEqualTo(Date value) {
            addCriterion("gmt_created =", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotEqualTo(Date value) {
            addCriterion("gmt_created <>", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedGreaterThan(Date value) {
            addCriterion("gmt_created >", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_created >=", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedLessThan(Date value) {
            addCriterion("gmt_created <", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_created <=", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIn(List<Date> values) {
            addCriterion("gmt_created in", values, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotIn(List<Date> values) {
            addCriterion("gmt_created not in", values, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedBetween(Date value1, Date value2) {
            addCriterion("gmt_created between", value1, value2, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_created not between", value1, value2, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * tb_generate_report_info
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}