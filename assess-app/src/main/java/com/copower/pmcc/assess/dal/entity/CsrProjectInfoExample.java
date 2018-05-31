package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CsrProjectInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CsrProjectInfoExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIsNull() {
            addCriterion("customer_type is null");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIsNotNull() {
            addCriterion("customer_type is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeEqualTo(Integer value) {
            addCriterion("customer_type =", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNotEqualTo(Integer value) {
            addCriterion("customer_type <>", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeGreaterThan(Integer value) {
            addCriterion("customer_type >", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_type >=", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeLessThan(Integer value) {
            addCriterion("customer_type <", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeLessThanOrEqualTo(Integer value) {
            addCriterion("customer_type <=", value, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIn(List<Integer> values) {
            addCriterion("customer_type in", values, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNotIn(List<Integer> values) {
            addCriterion("customer_type not in", values, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeBetween(Integer value1, Integer value2) {
            addCriterion("customer_type between", value1, value2, "customerType");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_type not between", value1, value2, "customerType");
            return (Criteria) this;
        }

        public Criteria andEntrustmentUnitIsNull() {
            addCriterion("entrustment_unit is null");
            return (Criteria) this;
        }

        public Criteria andEntrustmentUnitIsNotNull() {
            addCriterion("entrustment_unit is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustmentUnitEqualTo(String value) {
            addCriterion("entrustment_unit =", value, "entrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustmentUnitNotEqualTo(String value) {
            addCriterion("entrustment_unit <>", value, "entrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustmentUnitGreaterThan(String value) {
            addCriterion("entrustment_unit >", value, "entrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustmentUnitGreaterThanOrEqualTo(String value) {
            addCriterion("entrustment_unit >=", value, "entrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustmentUnitLessThan(String value) {
            addCriterion("entrustment_unit <", value, "entrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustmentUnitLessThanOrEqualTo(String value) {
            addCriterion("entrustment_unit <=", value, "entrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustmentUnitLike(String value) {
            addCriterion("entrustment_unit like", value, "entrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustmentUnitNotLike(String value) {
            addCriterion("entrustment_unit not like", value, "entrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustmentUnitIn(List<String> values) {
            addCriterion("entrustment_unit in", values, "entrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustmentUnitNotIn(List<String> values) {
            addCriterion("entrustment_unit not in", values, "entrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustmentUnitBetween(String value1, String value2) {
            addCriterion("entrustment_unit between", value1, value2, "entrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustmentUnitNotBetween(String value1, String value2) {
            addCriterion("entrustment_unit not between", value1, value2, "entrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeIsNull() {
            addCriterion("entrust_purpose is null");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeIsNotNull() {
            addCriterion("entrust_purpose is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeEqualTo(Integer value) {
            addCriterion("entrust_purpose =", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNotEqualTo(Integer value) {
            addCriterion("entrust_purpose <>", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeGreaterThan(Integer value) {
            addCriterion("entrust_purpose >", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeGreaterThanOrEqualTo(Integer value) {
            addCriterion("entrust_purpose >=", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLessThan(Integer value) {
            addCriterion("entrust_purpose <", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLessThanOrEqualTo(Integer value) {
            addCriterion("entrust_purpose <=", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeIn(List<Integer> values) {
            addCriterion("entrust_purpose in", values, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNotIn(List<Integer> values) {
            addCriterion("entrust_purpose not in", values, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeBetween(Integer value1, Integer value2) {
            addCriterion("entrust_purpose between", value1, value2, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNotBetween(Integer value1, Integer value2) {
            addCriterion("entrust_purpose not between", value1, value2, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andValuationDateIsNull() {
            addCriterion("valuation_date is null");
            return (Criteria) this;
        }

        public Criteria andValuationDateIsNotNull() {
            addCriterion("valuation_date is not null");
            return (Criteria) this;
        }

        public Criteria andValuationDateEqualTo(Date value) {
            addCriterionForJDBCDate("valuation_date =", value, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andValuationDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("valuation_date <>", value, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andValuationDateGreaterThan(Date value) {
            addCriterionForJDBCDate("valuation_date >", value, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andValuationDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("valuation_date >=", value, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andValuationDateLessThan(Date value) {
            addCriterionForJDBCDate("valuation_date <", value, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andValuationDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("valuation_date <=", value, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andValuationDateIn(List<Date> values) {
            addCriterionForJDBCDate("valuation_date in", values, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andValuationDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("valuation_date not in", values, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andValuationDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("valuation_date between", value1, value2, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andValuationDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("valuation_date not between", value1, value2, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andDistributionUserIsNull() {
            addCriterion("distribution_user is null");
            return (Criteria) this;
        }

        public Criteria andDistributionUserIsNotNull() {
            addCriterion("distribution_user is not null");
            return (Criteria) this;
        }

        public Criteria andDistributionUserEqualTo(String value) {
            addCriterion("distribution_user =", value, "distributionUser");
            return (Criteria) this;
        }

        public Criteria andDistributionUserNotEqualTo(String value) {
            addCriterion("distribution_user <>", value, "distributionUser");
            return (Criteria) this;
        }

        public Criteria andDistributionUserGreaterThan(String value) {
            addCriterion("distribution_user >", value, "distributionUser");
            return (Criteria) this;
        }

        public Criteria andDistributionUserGreaterThanOrEqualTo(String value) {
            addCriterion("distribution_user >=", value, "distributionUser");
            return (Criteria) this;
        }

        public Criteria andDistributionUserLessThan(String value) {
            addCriterion("distribution_user <", value, "distributionUser");
            return (Criteria) this;
        }

        public Criteria andDistributionUserLessThanOrEqualTo(String value) {
            addCriterion("distribution_user <=", value, "distributionUser");
            return (Criteria) this;
        }

        public Criteria andDistributionUserLike(String value) {
            addCriterion("distribution_user like", value, "distributionUser");
            return (Criteria) this;
        }

        public Criteria andDistributionUserNotLike(String value) {
            addCriterion("distribution_user not like", value, "distributionUser");
            return (Criteria) this;
        }

        public Criteria andDistributionUserIn(List<String> values) {
            addCriterion("distribution_user in", values, "distributionUser");
            return (Criteria) this;
        }

        public Criteria andDistributionUserNotIn(List<String> values) {
            addCriterion("distribution_user not in", values, "distributionUser");
            return (Criteria) this;
        }

        public Criteria andDistributionUserBetween(String value1, String value2) {
            addCriterion("distribution_user between", value1, value2, "distributionUser");
            return (Criteria) this;
        }

        public Criteria andDistributionUserNotBetween(String value1, String value2) {
            addCriterion("distribution_user not between", value1, value2, "distributionUser");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andStartRowNumberIsNull() {
            addCriterion("start_row_number is null");
            return (Criteria) this;
        }

        public Criteria andStartRowNumberIsNotNull() {
            addCriterion("start_row_number is not null");
            return (Criteria) this;
        }

        public Criteria andStartRowNumberEqualTo(Integer value) {
            addCriterion("start_row_number =", value, "startRowNumber");
            return (Criteria) this;
        }

        public Criteria andStartRowNumberNotEqualTo(Integer value) {
            addCriterion("start_row_number <>", value, "startRowNumber");
            return (Criteria) this;
        }

        public Criteria andStartRowNumberGreaterThan(Integer value) {
            addCriterion("start_row_number >", value, "startRowNumber");
            return (Criteria) this;
        }

        public Criteria andStartRowNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("start_row_number >=", value, "startRowNumber");
            return (Criteria) this;
        }

        public Criteria andStartRowNumberLessThan(Integer value) {
            addCriterion("start_row_number <", value, "startRowNumber");
            return (Criteria) this;
        }

        public Criteria andStartRowNumberLessThanOrEqualTo(Integer value) {
            addCriterion("start_row_number <=", value, "startRowNumber");
            return (Criteria) this;
        }

        public Criteria andStartRowNumberIn(List<Integer> values) {
            addCriterion("start_row_number in", values, "startRowNumber");
            return (Criteria) this;
        }

        public Criteria andStartRowNumberNotIn(List<Integer> values) {
            addCriterion("start_row_number not in", values, "startRowNumber");
            return (Criteria) this;
        }

        public Criteria andStartRowNumberBetween(Integer value1, Integer value2) {
            addCriterion("start_row_number between", value1, value2, "startRowNumber");
            return (Criteria) this;
        }

        public Criteria andStartRowNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("start_row_number not between", value1, value2, "startRowNumber");
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

        public Criteria andProjectStatusIsNull() {
            addCriterion("project_status is null");
            return (Criteria) this;
        }

        public Criteria andProjectStatusIsNotNull() {
            addCriterion("project_status is not null");
            return (Criteria) this;
        }

        public Criteria andProjectStatusEqualTo(String value) {
            addCriterion("project_status =", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusNotEqualTo(String value) {
            addCriterion("project_status <>", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusGreaterThan(String value) {
            addCriterion("project_status >", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusGreaterThanOrEqualTo(String value) {
            addCriterion("project_status >=", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusLessThan(String value) {
            addCriterion("project_status <", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusLessThanOrEqualTo(String value) {
            addCriterion("project_status <=", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusLike(String value) {
            addCriterion("project_status like", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusNotLike(String value) {
            addCriterion("project_status not like", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusIn(List<String> values) {
            addCriterion("project_status in", values, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusNotIn(List<String> values) {
            addCriterion("project_status not in", values, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusBetween(String value1, String value2) {
            addCriterion("project_status between", value1, value2, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusNotBetween(String value1, String value2) {
            addCriterion("project_status not between", value1, value2, "projectStatus");
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