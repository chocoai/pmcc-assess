package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseReportTemplateFilesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaseReportTemplateFilesExample() {
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

        public Criteria andFilesRemarksIsNull() {
            addCriterion("files_remarks is null");
            return (Criteria) this;
        }

        public Criteria andFilesRemarksIsNotNull() {
            addCriterion("files_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andFilesRemarksEqualTo(String value) {
            addCriterion("files_remarks =", value, "filesRemarks");
            return (Criteria) this;
        }

        public Criteria andFilesRemarksNotEqualTo(String value) {
            addCriterion("files_remarks <>", value, "filesRemarks");
            return (Criteria) this;
        }

        public Criteria andFilesRemarksGreaterThan(String value) {
            addCriterion("files_remarks >", value, "filesRemarks");
            return (Criteria) this;
        }

        public Criteria andFilesRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("files_remarks >=", value, "filesRemarks");
            return (Criteria) this;
        }

        public Criteria andFilesRemarksLessThan(String value) {
            addCriterion("files_remarks <", value, "filesRemarks");
            return (Criteria) this;
        }

        public Criteria andFilesRemarksLessThanOrEqualTo(String value) {
            addCriterion("files_remarks <=", value, "filesRemarks");
            return (Criteria) this;
        }

        public Criteria andFilesRemarksLike(String value) {
            addCriterion("files_remarks like", value, "filesRemarks");
            return (Criteria) this;
        }

        public Criteria andFilesRemarksNotLike(String value) {
            addCriterion("files_remarks not like", value, "filesRemarks");
            return (Criteria) this;
        }

        public Criteria andFilesRemarksIn(List<String> values) {
            addCriterion("files_remarks in", values, "filesRemarks");
            return (Criteria) this;
        }

        public Criteria andFilesRemarksNotIn(List<String> values) {
            addCriterion("files_remarks not in", values, "filesRemarks");
            return (Criteria) this;
        }

        public Criteria andFilesRemarksBetween(String value1, String value2) {
            addCriterion("files_remarks between", value1, value2, "filesRemarks");
            return (Criteria) this;
        }

        public Criteria andFilesRemarksNotBetween(String value1, String value2) {
            addCriterion("files_remarks not between", value1, value2, "filesRemarks");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(Integer value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(Integer value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(Integer value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(Integer value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(Integer value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<Integer> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<Integer> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(Integer value1, Integer value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdIsNull() {
            addCriterion("entrust_id is null");
            return (Criteria) this;
        }

        public Criteria andEntrustIdIsNotNull() {
            addCriterion("entrust_id is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustIdEqualTo(Integer value) {
            addCriterion("entrust_id =", value, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdNotEqualTo(Integer value) {
            addCriterion("entrust_id <>", value, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdGreaterThan(Integer value) {
            addCriterion("entrust_id >", value, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("entrust_id >=", value, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdLessThan(Integer value) {
            addCriterion("entrust_id <", value, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdLessThanOrEqualTo(Integer value) {
            addCriterion("entrust_id <=", value, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdIn(List<Integer> values) {
            addCriterion("entrust_id in", values, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdNotIn(List<Integer> values) {
            addCriterion("entrust_id not in", values, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdBetween(Integer value1, Integer value2) {
            addCriterion("entrust_id between", value1, value2, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdNotBetween(Integer value1, Integer value2) {
            addCriterion("entrust_id not between", value1, value2, "entrustId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdIsNull() {
            addCriterion("report_type_id is null");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdIsNotNull() {
            addCriterion("report_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdEqualTo(Integer value) {
            addCriterion("report_type_id =", value, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdNotEqualTo(Integer value) {
            addCriterion("report_type_id <>", value, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdGreaterThan(Integer value) {
            addCriterion("report_type_id >", value, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("report_type_id >=", value, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdLessThan(Integer value) {
            addCriterion("report_type_id <", value, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("report_type_id <=", value, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdIn(List<Integer> values) {
            addCriterion("report_type_id in", values, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdNotIn(List<Integer> values) {
            addCriterion("report_type_id not in", values, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("report_type_id between", value1, value2, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("report_type_id not between", value1, value2, "reportTypeId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdIsNull() {
            addCriterion("classify_id is null");
            return (Criteria) this;
        }

        public Criteria andClassifyIdIsNotNull() {
            addCriterion("classify_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassifyIdEqualTo(Integer value) {
            addCriterion("classify_id =", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdNotEqualTo(Integer value) {
            addCriterion("classify_id <>", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdGreaterThan(Integer value) {
            addCriterion("classify_id >", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("classify_id >=", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdLessThan(Integer value) {
            addCriterion("classify_id <", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdLessThanOrEqualTo(Integer value) {
            addCriterion("classify_id <=", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdIn(List<Integer> values) {
            addCriterion("classify_id in", values, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdNotIn(List<Integer> values) {
            addCriterion("classify_id not in", values, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdBetween(Integer value1, Integer value2) {
            addCriterion("classify_id between", value1, value2, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("classify_id not between", value1, value2, "classifyId");
            return (Criteria) this;
        }

        public Criteria andCsTypeIsNull() {
            addCriterion("cs_type is null");
            return (Criteria) this;
        }

        public Criteria andCsTypeIsNotNull() {
            addCriterion("cs_type is not null");
            return (Criteria) this;
        }

        public Criteria andCsTypeEqualTo(Integer value) {
            addCriterion("cs_type =", value, "csType");
            return (Criteria) this;
        }

        public Criteria andCsTypeNotEqualTo(Integer value) {
            addCriterion("cs_type <>", value, "csType");
            return (Criteria) this;
        }

        public Criteria andCsTypeGreaterThan(Integer value) {
            addCriterion("cs_type >", value, "csType");
            return (Criteria) this;
        }

        public Criteria andCsTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("cs_type >=", value, "csType");
            return (Criteria) this;
        }

        public Criteria andCsTypeLessThan(Integer value) {
            addCriterion("cs_type <", value, "csType");
            return (Criteria) this;
        }

        public Criteria andCsTypeLessThanOrEqualTo(Integer value) {
            addCriterion("cs_type <=", value, "csType");
            return (Criteria) this;
        }

        public Criteria andCsTypeIn(List<Integer> values) {
            addCriterion("cs_type in", values, "csType");
            return (Criteria) this;
        }

        public Criteria andCsTypeNotIn(List<Integer> values) {
            addCriterion("cs_type not in", values, "csType");
            return (Criteria) this;
        }

        public Criteria andCsTypeBetween(Integer value1, Integer value2) {
            addCriterion("cs_type between", value1, value2, "csType");
            return (Criteria) this;
        }

        public Criteria andCsTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("cs_type not between", value1, value2, "csType");
            return (Criteria) this;
        }

        public Criteria andInsertRowIndexIsNull() {
            addCriterion("insert_row_index is null");
            return (Criteria) this;
        }

        public Criteria andInsertRowIndexIsNotNull() {
            addCriterion("insert_row_index is not null");
            return (Criteria) this;
        }

        public Criteria andInsertRowIndexEqualTo(Integer value) {
            addCriterion("insert_row_index =", value, "insertRowIndex");
            return (Criteria) this;
        }

        public Criteria andInsertRowIndexNotEqualTo(Integer value) {
            addCriterion("insert_row_index <>", value, "insertRowIndex");
            return (Criteria) this;
        }

        public Criteria andInsertRowIndexGreaterThan(Integer value) {
            addCriterion("insert_row_index >", value, "insertRowIndex");
            return (Criteria) this;
        }

        public Criteria andInsertRowIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("insert_row_index >=", value, "insertRowIndex");
            return (Criteria) this;
        }

        public Criteria andInsertRowIndexLessThan(Integer value) {
            addCriterion("insert_row_index <", value, "insertRowIndex");
            return (Criteria) this;
        }

        public Criteria andInsertRowIndexLessThanOrEqualTo(Integer value) {
            addCriterion("insert_row_index <=", value, "insertRowIndex");
            return (Criteria) this;
        }

        public Criteria andInsertRowIndexIn(List<Integer> values) {
            addCriterion("insert_row_index in", values, "insertRowIndex");
            return (Criteria) this;
        }

        public Criteria andInsertRowIndexNotIn(List<Integer> values) {
            addCriterion("insert_row_index not in", values, "insertRowIndex");
            return (Criteria) this;
        }

        public Criteria andInsertRowIndexBetween(Integer value1, Integer value2) {
            addCriterion("insert_row_index between", value1, value2, "insertRowIndex");
            return (Criteria) this;
        }

        public Criteria andInsertRowIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("insert_row_index not between", value1, value2, "insertRowIndex");
            return (Criteria) this;
        }

        public Criteria andBisEnableIsNull() {
            addCriterion("bis_enable is null");
            return (Criteria) this;
        }

        public Criteria andBisEnableIsNotNull() {
            addCriterion("bis_enable is not null");
            return (Criteria) this;
        }

        public Criteria andBisEnableEqualTo(Boolean value) {
            addCriterion("bis_enable =", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableNotEqualTo(Boolean value) {
            addCriterion("bis_enable <>", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableGreaterThan(Boolean value) {
            addCriterion("bis_enable >", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_enable >=", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableLessThan(Boolean value) {
            addCriterion("bis_enable <", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_enable <=", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableIn(List<Boolean> values) {
            addCriterion("bis_enable in", values, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableNotIn(List<Boolean> values) {
            addCriterion("bis_enable not in", values, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_enable between", value1, value2, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_enable not between", value1, value2, "bisEnable");
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