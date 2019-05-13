package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SchemeJudgeObjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SchemeJudgeObjectExample() {
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

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
            addCriterion("pid not between", value1, value2, "pid");
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

        public Criteria andOriginalAreaGroupIdIsNull() {
            addCriterion("original_area_group_id is null");
            return (Criteria) this;
        }

        public Criteria andOriginalAreaGroupIdIsNotNull() {
            addCriterion("original_area_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalAreaGroupIdEqualTo(Integer value) {
            addCriterion("original_area_group_id =", value, "originalAreaGroupId");
            return (Criteria) this;
        }

        public Criteria andOriginalAreaGroupIdNotEqualTo(Integer value) {
            addCriterion("original_area_group_id <>", value, "originalAreaGroupId");
            return (Criteria) this;
        }

        public Criteria andOriginalAreaGroupIdGreaterThan(Integer value) {
            addCriterion("original_area_group_id >", value, "originalAreaGroupId");
            return (Criteria) this;
        }

        public Criteria andOriginalAreaGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("original_area_group_id >=", value, "originalAreaGroupId");
            return (Criteria) this;
        }

        public Criteria andOriginalAreaGroupIdLessThan(Integer value) {
            addCriterion("original_area_group_id <", value, "originalAreaGroupId");
            return (Criteria) this;
        }

        public Criteria andOriginalAreaGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("original_area_group_id <=", value, "originalAreaGroupId");
            return (Criteria) this;
        }

        public Criteria andOriginalAreaGroupIdIn(List<Integer> values) {
            addCriterion("original_area_group_id in", values, "originalAreaGroupId");
            return (Criteria) this;
        }

        public Criteria andOriginalAreaGroupIdNotIn(List<Integer> values) {
            addCriterion("original_area_group_id not in", values, "originalAreaGroupId");
            return (Criteria) this;
        }

        public Criteria andOriginalAreaGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("original_area_group_id between", value1, value2, "originalAreaGroupId");
            return (Criteria) this;
        }

        public Criteria andOriginalAreaGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("original_area_group_id not between", value1, value2, "originalAreaGroupId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdIsNull() {
            addCriterion("declare_record_id is null");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdIsNotNull() {
            addCriterion("declare_record_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdEqualTo(Integer value) {
            addCriterion("declare_record_id =", value, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdNotEqualTo(Integer value) {
            addCriterion("declare_record_id <>", value, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdGreaterThan(Integer value) {
            addCriterion("declare_record_id >", value, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("declare_record_id >=", value, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdLessThan(Integer value) {
            addCriterion("declare_record_id <", value, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdLessThanOrEqualTo(Integer value) {
            addCriterion("declare_record_id <=", value, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdIn(List<Integer> values) {
            addCriterion("declare_record_id in", values, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdNotIn(List<Integer> values) {
            addCriterion("declare_record_id not in", values, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdBetween(Integer value1, Integer value2) {
            addCriterion("declare_record_id between", value1, value2, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdNotBetween(Integer value1, Integer value2) {
            addCriterion("declare_record_id not between", value1, value2, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(String value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(String value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(String value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(String value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(String value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(String value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLike(String value) {
            addCriterion("number like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotLike(String value) {
            addCriterion("number not like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<String> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<String> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(String value1, String value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(String value1, String value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andOriginalNumberIsNull() {
            addCriterion("original_number is null");
            return (Criteria) this;
        }

        public Criteria andOriginalNumberIsNotNull() {
            addCriterion("original_number is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalNumberEqualTo(String value) {
            addCriterion("original_number =", value, "originalNumber");
            return (Criteria) this;
        }

        public Criteria andOriginalNumberNotEqualTo(String value) {
            addCriterion("original_number <>", value, "originalNumber");
            return (Criteria) this;
        }

        public Criteria andOriginalNumberGreaterThan(String value) {
            addCriterion("original_number >", value, "originalNumber");
            return (Criteria) this;
        }

        public Criteria andOriginalNumberGreaterThanOrEqualTo(String value) {
            addCriterion("original_number >=", value, "originalNumber");
            return (Criteria) this;
        }

        public Criteria andOriginalNumberLessThan(String value) {
            addCriterion("original_number <", value, "originalNumber");
            return (Criteria) this;
        }

        public Criteria andOriginalNumberLessThanOrEqualTo(String value) {
            addCriterion("original_number <=", value, "originalNumber");
            return (Criteria) this;
        }

        public Criteria andOriginalNumberLike(String value) {
            addCriterion("original_number like", value, "originalNumber");
            return (Criteria) this;
        }

        public Criteria andOriginalNumberNotLike(String value) {
            addCriterion("original_number not like", value, "originalNumber");
            return (Criteria) this;
        }

        public Criteria andOriginalNumberIn(List<String> values) {
            addCriterion("original_number in", values, "originalNumber");
            return (Criteria) this;
        }

        public Criteria andOriginalNumberNotIn(List<String> values) {
            addCriterion("original_number not in", values, "originalNumber");
            return (Criteria) this;
        }

        public Criteria andOriginalNumberBetween(String value1, String value2) {
            addCriterion("original_number between", value1, value2, "originalNumber");
            return (Criteria) this;
        }

        public Criteria andOriginalNumberNotBetween(String value1, String value2) {
            addCriterion("original_number not between", value1, value2, "originalNumber");
            return (Criteria) this;
        }

        public Criteria andSplitNumberIsNull() {
            addCriterion("split_number is null");
            return (Criteria) this;
        }

        public Criteria andSplitNumberIsNotNull() {
            addCriterion("split_number is not null");
            return (Criteria) this;
        }

        public Criteria andSplitNumberEqualTo(Integer value) {
            addCriterion("split_number =", value, "splitNumber");
            return (Criteria) this;
        }

        public Criteria andSplitNumberNotEqualTo(Integer value) {
            addCriterion("split_number <>", value, "splitNumber");
            return (Criteria) this;
        }

        public Criteria andSplitNumberGreaterThan(Integer value) {
            addCriterion("split_number >", value, "splitNumber");
            return (Criteria) this;
        }

        public Criteria andSplitNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("split_number >=", value, "splitNumber");
            return (Criteria) this;
        }

        public Criteria andSplitNumberLessThan(Integer value) {
            addCriterion("split_number <", value, "splitNumber");
            return (Criteria) this;
        }

        public Criteria andSplitNumberLessThanOrEqualTo(Integer value) {
            addCriterion("split_number <=", value, "splitNumber");
            return (Criteria) this;
        }

        public Criteria andSplitNumberIn(List<Integer> values) {
            addCriterion("split_number in", values, "splitNumber");
            return (Criteria) this;
        }

        public Criteria andSplitNumberNotIn(List<Integer> values) {
            addCriterion("split_number not in", values, "splitNumber");
            return (Criteria) this;
        }

        public Criteria andSplitNumberBetween(Integer value1, Integer value2) {
            addCriterion("split_number between", value1, value2, "splitNumber");
            return (Criteria) this;
        }

        public Criteria andSplitNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("split_number not between", value1, value2, "splitNumber");
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

        public Criteria andCertNameIsNull() {
            addCriterion("cert_name is null");
            return (Criteria) this;
        }

        public Criteria andCertNameIsNotNull() {
            addCriterion("cert_name is not null");
            return (Criteria) this;
        }

        public Criteria andCertNameEqualTo(String value) {
            addCriterion("cert_name =", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameNotEqualTo(String value) {
            addCriterion("cert_name <>", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameGreaterThan(String value) {
            addCriterion("cert_name >", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameGreaterThanOrEqualTo(String value) {
            addCriterion("cert_name >=", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameLessThan(String value) {
            addCriterion("cert_name <", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameLessThanOrEqualTo(String value) {
            addCriterion("cert_name <=", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameLike(String value) {
            addCriterion("cert_name like", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameNotLike(String value) {
            addCriterion("cert_name not like", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameIn(List<String> values) {
            addCriterion("cert_name in", values, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameNotIn(List<String> values) {
            addCriterion("cert_name not in", values, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameBetween(String value1, String value2) {
            addCriterion("cert_name between", value1, value2, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameNotBetween(String value1, String value2) {
            addCriterion("cert_name not between", value1, value2, "certName");
            return (Criteria) this;
        }

        public Criteria andOwnershipIsNull() {
            addCriterion("ownership is null");
            return (Criteria) this;
        }

        public Criteria andOwnershipIsNotNull() {
            addCriterion("ownership is not null");
            return (Criteria) this;
        }

        public Criteria andOwnershipEqualTo(String value) {
            addCriterion("ownership =", value, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipNotEqualTo(String value) {
            addCriterion("ownership <>", value, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipGreaterThan(String value) {
            addCriterion("ownership >", value, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipGreaterThanOrEqualTo(String value) {
            addCriterion("ownership >=", value, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipLessThan(String value) {
            addCriterion("ownership <", value, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipLessThanOrEqualTo(String value) {
            addCriterion("ownership <=", value, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipLike(String value) {
            addCriterion("ownership like", value, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipNotLike(String value) {
            addCriterion("ownership not like", value, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipIn(List<String> values) {
            addCriterion("ownership in", values, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipNotIn(List<String> values) {
            addCriterion("ownership not in", values, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipBetween(String value1, String value2) {
            addCriterion("ownership between", value1, value2, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipNotBetween(String value1, String value2) {
            addCriterion("ownership not between", value1, value2, "ownership");
            return (Criteria) this;
        }

        public Criteria andSeatIsNull() {
            addCriterion("seat is null");
            return (Criteria) this;
        }

        public Criteria andSeatIsNotNull() {
            addCriterion("seat is not null");
            return (Criteria) this;
        }

        public Criteria andSeatEqualTo(String value) {
            addCriterion("seat =", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatNotEqualTo(String value) {
            addCriterion("seat <>", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatGreaterThan(String value) {
            addCriterion("seat >", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatGreaterThanOrEqualTo(String value) {
            addCriterion("seat >=", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatLessThan(String value) {
            addCriterion("seat <", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatLessThanOrEqualTo(String value) {
            addCriterion("seat <=", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatLike(String value) {
            addCriterion("seat like", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatNotLike(String value) {
            addCriterion("seat not like", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatIn(List<String> values) {
            addCriterion("seat in", values, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatNotIn(List<String> values) {
            addCriterion("seat not in", values, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatBetween(String value1, String value2) {
            addCriterion("seat between", value1, value2, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatNotBetween(String value1, String value2) {
            addCriterion("seat not between", value1, value2, "seat");
            return (Criteria) this;
        }

        public Criteria andCertUseIsNull() {
            addCriterion("cert_use is null");
            return (Criteria) this;
        }

        public Criteria andCertUseIsNotNull() {
            addCriterion("cert_use is not null");
            return (Criteria) this;
        }

        public Criteria andCertUseEqualTo(String value) {
            addCriterion("cert_use =", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotEqualTo(String value) {
            addCriterion("cert_use <>", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseGreaterThan(String value) {
            addCriterion("cert_use >", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseGreaterThanOrEqualTo(String value) {
            addCriterion("cert_use >=", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseLessThan(String value) {
            addCriterion("cert_use <", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseLessThanOrEqualTo(String value) {
            addCriterion("cert_use <=", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseLike(String value) {
            addCriterion("cert_use like", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotLike(String value) {
            addCriterion("cert_use not like", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseIn(List<String> values) {
            addCriterion("cert_use in", values, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotIn(List<String> values) {
            addCriterion("cert_use not in", values, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseBetween(String value1, String value2) {
            addCriterion("cert_use between", value1, value2, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotBetween(String value1, String value2) {
            addCriterion("cert_use not between", value1, value2, "certUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseIsNull() {
            addCriterion("practical_use is null");
            return (Criteria) this;
        }

        public Criteria andPracticalUseIsNotNull() {
            addCriterion("practical_use is not null");
            return (Criteria) this;
        }

        public Criteria andPracticalUseEqualTo(String value) {
            addCriterion("practical_use =", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseNotEqualTo(String value) {
            addCriterion("practical_use <>", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseGreaterThan(String value) {
            addCriterion("practical_use >", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseGreaterThanOrEqualTo(String value) {
            addCriterion("practical_use >=", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseLessThan(String value) {
            addCriterion("practical_use <", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseLessThanOrEqualTo(String value) {
            addCriterion("practical_use <=", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseLike(String value) {
            addCriterion("practical_use like", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseNotLike(String value) {
            addCriterion("practical_use not like", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseIn(List<String> values) {
            addCriterion("practical_use in", values, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseNotIn(List<String> values) {
            addCriterion("practical_use not in", values, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseBetween(String value1, String value2) {
            addCriterion("practical_use between", value1, value2, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseNotBetween(String value1, String value2) {
            addCriterion("practical_use not between", value1, value2, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andLandCertUseIsNull() {
            addCriterion("land_cert_use is null");
            return (Criteria) this;
        }

        public Criteria andLandCertUseIsNotNull() {
            addCriterion("land_cert_use is not null");
            return (Criteria) this;
        }

        public Criteria andLandCertUseEqualTo(String value) {
            addCriterion("land_cert_use =", value, "landCertUse");
            return (Criteria) this;
        }

        public Criteria andLandCertUseNotEqualTo(String value) {
            addCriterion("land_cert_use <>", value, "landCertUse");
            return (Criteria) this;
        }

        public Criteria andLandCertUseGreaterThan(String value) {
            addCriterion("land_cert_use >", value, "landCertUse");
            return (Criteria) this;
        }

        public Criteria andLandCertUseGreaterThanOrEqualTo(String value) {
            addCriterion("land_cert_use >=", value, "landCertUse");
            return (Criteria) this;
        }

        public Criteria andLandCertUseLessThan(String value) {
            addCriterion("land_cert_use <", value, "landCertUse");
            return (Criteria) this;
        }

        public Criteria andLandCertUseLessThanOrEqualTo(String value) {
            addCriterion("land_cert_use <=", value, "landCertUse");
            return (Criteria) this;
        }

        public Criteria andLandCertUseLike(String value) {
            addCriterion("land_cert_use like", value, "landCertUse");
            return (Criteria) this;
        }

        public Criteria andLandCertUseNotLike(String value) {
            addCriterion("land_cert_use not like", value, "landCertUse");
            return (Criteria) this;
        }

        public Criteria andLandCertUseIn(List<String> values) {
            addCriterion("land_cert_use in", values, "landCertUse");
            return (Criteria) this;
        }

        public Criteria andLandCertUseNotIn(List<String> values) {
            addCriterion("land_cert_use not in", values, "landCertUse");
            return (Criteria) this;
        }

        public Criteria andLandCertUseBetween(String value1, String value2) {
            addCriterion("land_cert_use between", value1, value2, "landCertUse");
            return (Criteria) this;
        }

        public Criteria andLandCertUseNotBetween(String value1, String value2) {
            addCriterion("land_cert_use not between", value1, value2, "landCertUse");
            return (Criteria) this;
        }

        public Criteria andLandPracticalUseIsNull() {
            addCriterion("land_practical_use is null");
            return (Criteria) this;
        }

        public Criteria andLandPracticalUseIsNotNull() {
            addCriterion("land_practical_use is not null");
            return (Criteria) this;
        }

        public Criteria andLandPracticalUseEqualTo(String value) {
            addCriterion("land_practical_use =", value, "landPracticalUse");
            return (Criteria) this;
        }

        public Criteria andLandPracticalUseNotEqualTo(String value) {
            addCriterion("land_practical_use <>", value, "landPracticalUse");
            return (Criteria) this;
        }

        public Criteria andLandPracticalUseGreaterThan(String value) {
            addCriterion("land_practical_use >", value, "landPracticalUse");
            return (Criteria) this;
        }

        public Criteria andLandPracticalUseGreaterThanOrEqualTo(String value) {
            addCriterion("land_practical_use >=", value, "landPracticalUse");
            return (Criteria) this;
        }

        public Criteria andLandPracticalUseLessThan(String value) {
            addCriterion("land_practical_use <", value, "landPracticalUse");
            return (Criteria) this;
        }

        public Criteria andLandPracticalUseLessThanOrEqualTo(String value) {
            addCriterion("land_practical_use <=", value, "landPracticalUse");
            return (Criteria) this;
        }

        public Criteria andLandPracticalUseLike(String value) {
            addCriterion("land_practical_use like", value, "landPracticalUse");
            return (Criteria) this;
        }

        public Criteria andLandPracticalUseNotLike(String value) {
            addCriterion("land_practical_use not like", value, "landPracticalUse");
            return (Criteria) this;
        }

        public Criteria andLandPracticalUseIn(List<String> values) {
            addCriterion("land_practical_use in", values, "landPracticalUse");
            return (Criteria) this;
        }

        public Criteria andLandPracticalUseNotIn(List<String> values) {
            addCriterion("land_practical_use not in", values, "landPracticalUse");
            return (Criteria) this;
        }

        public Criteria andLandPracticalUseBetween(String value1, String value2) {
            addCriterion("land_practical_use between", value1, value2, "landPracticalUse");
            return (Criteria) this;
        }

        public Criteria andLandPracticalUseNotBetween(String value1, String value2) {
            addCriterion("land_practical_use not between", value1, value2, "landPracticalUse");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateIsNull() {
            addCriterion("land_use_end_date is null");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateIsNotNull() {
            addCriterion("land_use_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateEqualTo(Date value) {
            addCriterion("land_use_end_date =", value, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateNotEqualTo(Date value) {
            addCriterion("land_use_end_date <>", value, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateGreaterThan(Date value) {
            addCriterion("land_use_end_date >", value, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("land_use_end_date >=", value, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateLessThan(Date value) {
            addCriterion("land_use_end_date <", value, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateLessThanOrEqualTo(Date value) {
            addCriterion("land_use_end_date <=", value, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateIn(List<Date> values) {
            addCriterion("land_use_end_date in", values, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateNotIn(List<Date> values) {
            addCriterion("land_use_end_date not in", values, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateBetween(Date value1, Date value2) {
            addCriterion("land_use_end_date between", value1, value2, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateNotBetween(Date value1, Date value2) {
            addCriterion("land_use_end_date not between", value1, value2, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearIsNull() {
            addCriterion("land_remaining_year is null");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearIsNotNull() {
            addCriterion("land_remaining_year is not null");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearEqualTo(BigDecimal value) {
            addCriterion("land_remaining_year =", value, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearNotEqualTo(BigDecimal value) {
            addCriterion("land_remaining_year <>", value, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearGreaterThan(BigDecimal value) {
            addCriterion("land_remaining_year >", value, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_remaining_year >=", value, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearLessThan(BigDecimal value) {
            addCriterion("land_remaining_year <", value, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_remaining_year <=", value, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearIn(List<BigDecimal> values) {
            addCriterion("land_remaining_year in", values, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearNotIn(List<BigDecimal> values) {
            addCriterion("land_remaining_year not in", values, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_remaining_year between", value1, value2, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andLandRemainingYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_remaining_year not between", value1, value2, "landRemainingYear");
            return (Criteria) this;
        }

        public Criteria andSetUseIsNull() {
            addCriterion("set_use is null");
            return (Criteria) this;
        }

        public Criteria andSetUseIsNotNull() {
            addCriterion("set_use is not null");
            return (Criteria) this;
        }

        public Criteria andSetUseEqualTo(Integer value) {
            addCriterion("set_use =", value, "setUse");
            return (Criteria) this;
        }

        public Criteria andSetUseNotEqualTo(Integer value) {
            addCriterion("set_use <>", value, "setUse");
            return (Criteria) this;
        }

        public Criteria andSetUseGreaterThan(Integer value) {
            addCriterion("set_use >", value, "setUse");
            return (Criteria) this;
        }

        public Criteria andSetUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("set_use >=", value, "setUse");
            return (Criteria) this;
        }

        public Criteria andSetUseLessThan(Integer value) {
            addCriterion("set_use <", value, "setUse");
            return (Criteria) this;
        }

        public Criteria andSetUseLessThanOrEqualTo(Integer value) {
            addCriterion("set_use <=", value, "setUse");
            return (Criteria) this;
        }

        public Criteria andSetUseIn(List<Integer> values) {
            addCriterion("set_use in", values, "setUse");
            return (Criteria) this;
        }

        public Criteria andSetUseNotIn(List<Integer> values) {
            addCriterion("set_use not in", values, "setUse");
            return (Criteria) this;
        }

        public Criteria andSetUseBetween(Integer value1, Integer value2) {
            addCriterion("set_use between", value1, value2, "setUse");
            return (Criteria) this;
        }

        public Criteria andSetUseNotBetween(Integer value1, Integer value2) {
            addCriterion("set_use not between", value1, value2, "setUse");
            return (Criteria) this;
        }

        public Criteria andBestUseIsNull() {
            addCriterion("best_use is null");
            return (Criteria) this;
        }

        public Criteria andBestUseIsNotNull() {
            addCriterion("best_use is not null");
            return (Criteria) this;
        }

        public Criteria andBestUseEqualTo(Integer value) {
            addCriterion("best_use =", value, "bestUse");
            return (Criteria) this;
        }

        public Criteria andBestUseNotEqualTo(Integer value) {
            addCriterion("best_use <>", value, "bestUse");
            return (Criteria) this;
        }

        public Criteria andBestUseGreaterThan(Integer value) {
            addCriterion("best_use >", value, "bestUse");
            return (Criteria) this;
        }

        public Criteria andBestUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("best_use >=", value, "bestUse");
            return (Criteria) this;
        }

        public Criteria andBestUseLessThan(Integer value) {
            addCriterion("best_use <", value, "bestUse");
            return (Criteria) this;
        }

        public Criteria andBestUseLessThanOrEqualTo(Integer value) {
            addCriterion("best_use <=", value, "bestUse");
            return (Criteria) this;
        }

        public Criteria andBestUseIn(List<Integer> values) {
            addCriterion("best_use in", values, "bestUse");
            return (Criteria) this;
        }

        public Criteria andBestUseNotIn(List<Integer> values) {
            addCriterion("best_use not in", values, "bestUse");
            return (Criteria) this;
        }

        public Criteria andBestUseBetween(Integer value1, Integer value2) {
            addCriterion("best_use between", value1, value2, "bestUse");
            return (Criteria) this;
        }

        public Criteria andBestUseNotBetween(Integer value1, Integer value2) {
            addCriterion("best_use not between", value1, value2, "bestUse");
            return (Criteria) this;
        }

        public Criteria andFloorAreaIsNull() {
            addCriterion("floor_area is null");
            return (Criteria) this;
        }

        public Criteria andFloorAreaIsNotNull() {
            addCriterion("floor_area is not null");
            return (Criteria) this;
        }

        public Criteria andFloorAreaEqualTo(BigDecimal value) {
            addCriterion("floor_area =", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotEqualTo(BigDecimal value) {
            addCriterion("floor_area <>", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaGreaterThan(BigDecimal value) {
            addCriterion("floor_area >", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_area >=", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaLessThan(BigDecimal value) {
            addCriterion("floor_area <", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_area <=", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaIn(List<BigDecimal> values) {
            addCriterion("floor_area in", values, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotIn(List<BigDecimal> values) {
            addCriterion("floor_area not in", values, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_area between", value1, value2, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_area not between", value1, value2, "floorArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaIsNull() {
            addCriterion("evaluation_area is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaIsNotNull() {
            addCriterion("evaluation_area is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaEqualTo(BigDecimal value) {
            addCriterion("evaluation_area =", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaNotEqualTo(BigDecimal value) {
            addCriterion("evaluation_area <>", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaGreaterThan(BigDecimal value) {
            addCriterion("evaluation_area >", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("evaluation_area >=", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaLessThan(BigDecimal value) {
            addCriterion("evaluation_area <", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("evaluation_area <=", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaIn(List<BigDecimal> values) {
            addCriterion("evaluation_area in", values, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaNotIn(List<BigDecimal> values) {
            addCriterion("evaluation_area not in", values, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("evaluation_area between", value1, value2, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("evaluation_area not between", value1, value2, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIsNull() {
            addCriterion("original_price is null");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIsNotNull() {
            addCriterion("original_price is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceEqualTo(BigDecimal value) {
            addCriterion("original_price =", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotEqualTo(BigDecimal value) {
            addCriterion("original_price <>", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceGreaterThan(BigDecimal value) {
            addCriterion("original_price >", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("original_price >=", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceLessThan(BigDecimal value) {
            addCriterion("original_price <", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("original_price <=", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIn(List<BigDecimal> values) {
            addCriterion("original_price in", values, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotIn(List<BigDecimal> values) {
            addCriterion("original_price not in", values, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("original_price between", value1, value2, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("original_price not between", value1, value2, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andSetPlotRatioIsNull() {
            addCriterion("set_plot_ratio is null");
            return (Criteria) this;
        }

        public Criteria andSetPlotRatioIsNotNull() {
            addCriterion("set_plot_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andSetPlotRatioEqualTo(BigDecimal value) {
            addCriterion("set_plot_ratio =", value, "setPlotRatio");
            return (Criteria) this;
        }

        public Criteria andSetPlotRatioNotEqualTo(BigDecimal value) {
            addCriterion("set_plot_ratio <>", value, "setPlotRatio");
            return (Criteria) this;
        }

        public Criteria andSetPlotRatioGreaterThan(BigDecimal value) {
            addCriterion("set_plot_ratio >", value, "setPlotRatio");
            return (Criteria) this;
        }

        public Criteria andSetPlotRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("set_plot_ratio >=", value, "setPlotRatio");
            return (Criteria) this;
        }

        public Criteria andSetPlotRatioLessThan(BigDecimal value) {
            addCriterion("set_plot_ratio <", value, "setPlotRatio");
            return (Criteria) this;
        }

        public Criteria andSetPlotRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("set_plot_ratio <=", value, "setPlotRatio");
            return (Criteria) this;
        }

        public Criteria andSetPlotRatioIn(List<BigDecimal> values) {
            addCriterion("set_plot_ratio in", values, "setPlotRatio");
            return (Criteria) this;
        }

        public Criteria andSetPlotRatioNotIn(List<BigDecimal> values) {
            addCriterion("set_plot_ratio not in", values, "setPlotRatio");
            return (Criteria) this;
        }

        public Criteria andSetPlotRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("set_plot_ratio between", value1, value2, "setPlotRatio");
            return (Criteria) this;
        }

        public Criteria andSetPlotRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("set_plot_ratio not between", value1, value2, "setPlotRatio");
            return (Criteria) this;
        }

        public Criteria andPlanPlotRatioIsNull() {
            addCriterion("plan_plot_ratio is null");
            return (Criteria) this;
        }

        public Criteria andPlanPlotRatioIsNotNull() {
            addCriterion("plan_plot_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andPlanPlotRatioEqualTo(BigDecimal value) {
            addCriterion("plan_plot_ratio =", value, "planPlotRatio");
            return (Criteria) this;
        }

        public Criteria andPlanPlotRatioNotEqualTo(BigDecimal value) {
            addCriterion("plan_plot_ratio <>", value, "planPlotRatio");
            return (Criteria) this;
        }

        public Criteria andPlanPlotRatioGreaterThan(BigDecimal value) {
            addCriterion("plan_plot_ratio >", value, "planPlotRatio");
            return (Criteria) this;
        }

        public Criteria andPlanPlotRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("plan_plot_ratio >=", value, "planPlotRatio");
            return (Criteria) this;
        }

        public Criteria andPlanPlotRatioLessThan(BigDecimal value) {
            addCriterion("plan_plot_ratio <", value, "planPlotRatio");
            return (Criteria) this;
        }

        public Criteria andPlanPlotRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("plan_plot_ratio <=", value, "planPlotRatio");
            return (Criteria) this;
        }

        public Criteria andPlanPlotRatioIn(List<BigDecimal> values) {
            addCriterion("plan_plot_ratio in", values, "planPlotRatio");
            return (Criteria) this;
        }

        public Criteria andPlanPlotRatioNotIn(List<BigDecimal> values) {
            addCriterion("plan_plot_ratio not in", values, "planPlotRatio");
            return (Criteria) this;
        }

        public Criteria andPlanPlotRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plan_plot_ratio between", value1, value2, "planPlotRatio");
            return (Criteria) this;
        }

        public Criteria andPlanPlotRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plan_plot_ratio not between", value1, value2, "planPlotRatio");
            return (Criteria) this;
        }

        public Criteria andActualPlotRatioIsNull() {
            addCriterion("actual_plot_ratio is null");
            return (Criteria) this;
        }

        public Criteria andActualPlotRatioIsNotNull() {
            addCriterion("actual_plot_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andActualPlotRatioEqualTo(BigDecimal value) {
            addCriterion("actual_plot_ratio =", value, "actualPlotRatio");
            return (Criteria) this;
        }

        public Criteria andActualPlotRatioNotEqualTo(BigDecimal value) {
            addCriterion("actual_plot_ratio <>", value, "actualPlotRatio");
            return (Criteria) this;
        }

        public Criteria andActualPlotRatioGreaterThan(BigDecimal value) {
            addCriterion("actual_plot_ratio >", value, "actualPlotRatio");
            return (Criteria) this;
        }

        public Criteria andActualPlotRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_plot_ratio >=", value, "actualPlotRatio");
            return (Criteria) this;
        }

        public Criteria andActualPlotRatioLessThan(BigDecimal value) {
            addCriterion("actual_plot_ratio <", value, "actualPlotRatio");
            return (Criteria) this;
        }

        public Criteria andActualPlotRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_plot_ratio <=", value, "actualPlotRatio");
            return (Criteria) this;
        }

        public Criteria andActualPlotRatioIn(List<BigDecimal> values) {
            addCriterion("actual_plot_ratio in", values, "actualPlotRatio");
            return (Criteria) this;
        }

        public Criteria andActualPlotRatioNotIn(List<BigDecimal> values) {
            addCriterion("actual_plot_ratio not in", values, "actualPlotRatio");
            return (Criteria) this;
        }

        public Criteria andActualPlotRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_plot_ratio between", value1, value2, "actualPlotRatio");
            return (Criteria) this;
        }

        public Criteria andActualPlotRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_plot_ratio not between", value1, value2, "actualPlotRatio");
            return (Criteria) this;
        }

        public Criteria andRentalPossessionDescIsNull() {
            addCriterion("rental_possession_desc is null");
            return (Criteria) this;
        }

        public Criteria andRentalPossessionDescIsNotNull() {
            addCriterion("rental_possession_desc is not null");
            return (Criteria) this;
        }

        public Criteria andRentalPossessionDescEqualTo(String value) {
            addCriterion("rental_possession_desc =", value, "rentalPossessionDesc");
            return (Criteria) this;
        }

        public Criteria andRentalPossessionDescNotEqualTo(String value) {
            addCriterion("rental_possession_desc <>", value, "rentalPossessionDesc");
            return (Criteria) this;
        }

        public Criteria andRentalPossessionDescGreaterThan(String value) {
            addCriterion("rental_possession_desc >", value, "rentalPossessionDesc");
            return (Criteria) this;
        }

        public Criteria andRentalPossessionDescGreaterThanOrEqualTo(String value) {
            addCriterion("rental_possession_desc >=", value, "rentalPossessionDesc");
            return (Criteria) this;
        }

        public Criteria andRentalPossessionDescLessThan(String value) {
            addCriterion("rental_possession_desc <", value, "rentalPossessionDesc");
            return (Criteria) this;
        }

        public Criteria andRentalPossessionDescLessThanOrEqualTo(String value) {
            addCriterion("rental_possession_desc <=", value, "rentalPossessionDesc");
            return (Criteria) this;
        }

        public Criteria andRentalPossessionDescLike(String value) {
            addCriterion("rental_possession_desc like", value, "rentalPossessionDesc");
            return (Criteria) this;
        }

        public Criteria andRentalPossessionDescNotLike(String value) {
            addCriterion("rental_possession_desc not like", value, "rentalPossessionDesc");
            return (Criteria) this;
        }

        public Criteria andRentalPossessionDescIn(List<String> values) {
            addCriterion("rental_possession_desc in", values, "rentalPossessionDesc");
            return (Criteria) this;
        }

        public Criteria andRentalPossessionDescNotIn(List<String> values) {
            addCriterion("rental_possession_desc not in", values, "rentalPossessionDesc");
            return (Criteria) this;
        }

        public Criteria andRentalPossessionDescBetween(String value1, String value2) {
            addCriterion("rental_possession_desc between", value1, value2, "rentalPossessionDesc");
            return (Criteria) this;
        }

        public Criteria andRentalPossessionDescNotBetween(String value1, String value2) {
            addCriterion("rental_possession_desc not between", value1, value2, "rentalPossessionDesc");
            return (Criteria) this;
        }

        public Criteria andCollateralFoundIsNull() {
            addCriterion("collateral_found is null");
            return (Criteria) this;
        }

        public Criteria andCollateralFoundIsNotNull() {
            addCriterion("collateral_found is not null");
            return (Criteria) this;
        }

        public Criteria andCollateralFoundEqualTo(String value) {
            addCriterion("collateral_found =", value, "collateralFound");
            return (Criteria) this;
        }

        public Criteria andCollateralFoundNotEqualTo(String value) {
            addCriterion("collateral_found <>", value, "collateralFound");
            return (Criteria) this;
        }

        public Criteria andCollateralFoundGreaterThan(String value) {
            addCriterion("collateral_found >", value, "collateralFound");
            return (Criteria) this;
        }

        public Criteria andCollateralFoundGreaterThanOrEqualTo(String value) {
            addCriterion("collateral_found >=", value, "collateralFound");
            return (Criteria) this;
        }

        public Criteria andCollateralFoundLessThan(String value) {
            addCriterion("collateral_found <", value, "collateralFound");
            return (Criteria) this;
        }

        public Criteria andCollateralFoundLessThanOrEqualTo(String value) {
            addCriterion("collateral_found <=", value, "collateralFound");
            return (Criteria) this;
        }

        public Criteria andCollateralFoundLike(String value) {
            addCriterion("collateral_found like", value, "collateralFound");
            return (Criteria) this;
        }

        public Criteria andCollateralFoundNotLike(String value) {
            addCriterion("collateral_found not like", value, "collateralFound");
            return (Criteria) this;
        }

        public Criteria andCollateralFoundIn(List<String> values) {
            addCriterion("collateral_found in", values, "collateralFound");
            return (Criteria) this;
        }

        public Criteria andCollateralFoundNotIn(List<String> values) {
            addCriterion("collateral_found not in", values, "collateralFound");
            return (Criteria) this;
        }

        public Criteria andCollateralFoundBetween(String value1, String value2) {
            addCriterion("collateral_found between", value1, value2, "collateralFound");
            return (Criteria) this;
        }

        public Criteria andCollateralFoundNotBetween(String value1, String value2) {
            addCriterion("collateral_found not between", value1, value2, "collateralFound");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioIsNull() {
            addCriterion("liquid_ratio is null");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioIsNotNull() {
            addCriterion("liquid_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioEqualTo(String value) {
            addCriterion("liquid_ratio =", value, "liquidRatio");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioNotEqualTo(String value) {
            addCriterion("liquid_ratio <>", value, "liquidRatio");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioGreaterThan(String value) {
            addCriterion("liquid_ratio >", value, "liquidRatio");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioGreaterThanOrEqualTo(String value) {
            addCriterion("liquid_ratio >=", value, "liquidRatio");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioLessThan(String value) {
            addCriterion("liquid_ratio <", value, "liquidRatio");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioLessThanOrEqualTo(String value) {
            addCriterion("liquid_ratio <=", value, "liquidRatio");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioLike(String value) {
            addCriterion("liquid_ratio like", value, "liquidRatio");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioNotLike(String value) {
            addCriterion("liquid_ratio not like", value, "liquidRatio");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioIn(List<String> values) {
            addCriterion("liquid_ratio in", values, "liquidRatio");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioNotIn(List<String> values) {
            addCriterion("liquid_ratio not in", values, "liquidRatio");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioBetween(String value1, String value2) {
            addCriterion("liquid_ratio between", value1, value2, "liquidRatio");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioNotBetween(String value1, String value2) {
            addCriterion("liquid_ratio not between", value1, value2, "liquidRatio");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioExplainIsNull() {
            addCriterion("liquid_ratio_explain is null");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioExplainIsNotNull() {
            addCriterion("liquid_ratio_explain is not null");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioExplainEqualTo(String value) {
            addCriterion("liquid_ratio_explain =", value, "liquidRatioExplain");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioExplainNotEqualTo(String value) {
            addCriterion("liquid_ratio_explain <>", value, "liquidRatioExplain");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioExplainGreaterThan(String value) {
            addCriterion("liquid_ratio_explain >", value, "liquidRatioExplain");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioExplainGreaterThanOrEqualTo(String value) {
            addCriterion("liquid_ratio_explain >=", value, "liquidRatioExplain");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioExplainLessThan(String value) {
            addCriterion("liquid_ratio_explain <", value, "liquidRatioExplain");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioExplainLessThanOrEqualTo(String value) {
            addCriterion("liquid_ratio_explain <=", value, "liquidRatioExplain");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioExplainLike(String value) {
            addCriterion("liquid_ratio_explain like", value, "liquidRatioExplain");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioExplainNotLike(String value) {
            addCriterion("liquid_ratio_explain not like", value, "liquidRatioExplain");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioExplainIn(List<String> values) {
            addCriterion("liquid_ratio_explain in", values, "liquidRatioExplain");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioExplainNotIn(List<String> values) {
            addCriterion("liquid_ratio_explain not in", values, "liquidRatioExplain");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioExplainBetween(String value1, String value2) {
            addCriterion("liquid_ratio_explain between", value1, value2, "liquidRatioExplain");
            return (Criteria) this;
        }

        public Criteria andLiquidRatioExplainNotBetween(String value1, String value2) {
            addCriterion("liquid_ratio_explain not between", value1, value2, "liquidRatioExplain");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeIdIsNull() {
            addCriterion("standard_judge_id is null");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeIdIsNotNull() {
            addCriterion("standard_judge_id is not null");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeIdEqualTo(Integer value) {
            addCriterion("standard_judge_id =", value, "standardJudgeId");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeIdNotEqualTo(Integer value) {
            addCriterion("standard_judge_id <>", value, "standardJudgeId");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeIdGreaterThan(Integer value) {
            addCriterion("standard_judge_id >", value, "standardJudgeId");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("standard_judge_id >=", value, "standardJudgeId");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeIdLessThan(Integer value) {
            addCriterion("standard_judge_id <", value, "standardJudgeId");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeIdLessThanOrEqualTo(Integer value) {
            addCriterion("standard_judge_id <=", value, "standardJudgeId");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeIdIn(List<Integer> values) {
            addCriterion("standard_judge_id in", values, "standardJudgeId");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeIdNotIn(List<Integer> values) {
            addCriterion("standard_judge_id not in", values, "standardJudgeId");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeIdBetween(Integer value1, Integer value2) {
            addCriterion("standard_judge_id between", value1, value2, "standardJudgeId");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("standard_judge_id not between", value1, value2, "standardJudgeId");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeExplainIsNull() {
            addCriterion("standard_judge_explain is null");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeExplainIsNotNull() {
            addCriterion("standard_judge_explain is not null");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeExplainEqualTo(String value) {
            addCriterion("standard_judge_explain =", value, "standardJudgeExplain");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeExplainNotEqualTo(String value) {
            addCriterion("standard_judge_explain <>", value, "standardJudgeExplain");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeExplainGreaterThan(String value) {
            addCriterion("standard_judge_explain >", value, "standardJudgeExplain");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeExplainGreaterThanOrEqualTo(String value) {
            addCriterion("standard_judge_explain >=", value, "standardJudgeExplain");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeExplainLessThan(String value) {
            addCriterion("standard_judge_explain <", value, "standardJudgeExplain");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeExplainLessThanOrEqualTo(String value) {
            addCriterion("standard_judge_explain <=", value, "standardJudgeExplain");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeExplainLike(String value) {
            addCriterion("standard_judge_explain like", value, "standardJudgeExplain");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeExplainNotLike(String value) {
            addCriterion("standard_judge_explain not like", value, "standardJudgeExplain");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeExplainIn(List<String> values) {
            addCriterion("standard_judge_explain in", values, "standardJudgeExplain");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeExplainNotIn(List<String> values) {
            addCriterion("standard_judge_explain not in", values, "standardJudgeExplain");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeExplainBetween(String value1, String value2) {
            addCriterion("standard_judge_explain between", value1, value2, "standardJudgeExplain");
            return (Criteria) this;
        }

        public Criteria andStandardJudgeExplainNotBetween(String value1, String value2) {
            addCriterion("standard_judge_explain not between", value1, value2, "standardJudgeExplain");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonIsNull() {
            addCriterion("not_applicable_reason is null");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonIsNotNull() {
            addCriterion("not_applicable_reason is not null");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonEqualTo(String value) {
            addCriterion("not_applicable_reason =", value, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonNotEqualTo(String value) {
            addCriterion("not_applicable_reason <>", value, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonGreaterThan(String value) {
            addCriterion("not_applicable_reason >", value, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonGreaterThanOrEqualTo(String value) {
            addCriterion("not_applicable_reason >=", value, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonLessThan(String value) {
            addCriterion("not_applicable_reason <", value, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonLessThanOrEqualTo(String value) {
            addCriterion("not_applicable_reason <=", value, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonLike(String value) {
            addCriterion("not_applicable_reason like", value, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonNotLike(String value) {
            addCriterion("not_applicable_reason not like", value, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonIn(List<String> values) {
            addCriterion("not_applicable_reason in", values, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonNotIn(List<String> values) {
            addCriterion("not_applicable_reason not in", values, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonBetween(String value1, String value2) {
            addCriterion("not_applicable_reason between", value1, value2, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonNotBetween(String value1, String value2) {
            addCriterion("not_applicable_reason not between", value1, value2, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andBisSplitIsNull() {
            addCriterion("bis_split is null");
            return (Criteria) this;
        }

        public Criteria andBisSplitIsNotNull() {
            addCriterion("bis_split is not null");
            return (Criteria) this;
        }

        public Criteria andBisSplitEqualTo(Boolean value) {
            addCriterion("bis_split =", value, "bisSplit");
            return (Criteria) this;
        }

        public Criteria andBisSplitNotEqualTo(Boolean value) {
            addCriterion("bis_split <>", value, "bisSplit");
            return (Criteria) this;
        }

        public Criteria andBisSplitGreaterThan(Boolean value) {
            addCriterion("bis_split >", value, "bisSplit");
            return (Criteria) this;
        }

        public Criteria andBisSplitGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_split >=", value, "bisSplit");
            return (Criteria) this;
        }

        public Criteria andBisSplitLessThan(Boolean value) {
            addCriterion("bis_split <", value, "bisSplit");
            return (Criteria) this;
        }

        public Criteria andBisSplitLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_split <=", value, "bisSplit");
            return (Criteria) this;
        }

        public Criteria andBisSplitIn(List<Boolean> values) {
            addCriterion("bis_split in", values, "bisSplit");
            return (Criteria) this;
        }

        public Criteria andBisSplitNotIn(List<Boolean> values) {
            addCriterion("bis_split not in", values, "bisSplit");
            return (Criteria) this;
        }

        public Criteria andBisSplitBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_split between", value1, value2, "bisSplit");
            return (Criteria) this;
        }

        public Criteria andBisSplitNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_split not between", value1, value2, "bisSplit");
            return (Criteria) this;
        }

        public Criteria andBisMergeIsNull() {
            addCriterion("bis_merge is null");
            return (Criteria) this;
        }

        public Criteria andBisMergeIsNotNull() {
            addCriterion("bis_merge is not null");
            return (Criteria) this;
        }

        public Criteria andBisMergeEqualTo(Boolean value) {
            addCriterion("bis_merge =", value, "bisMerge");
            return (Criteria) this;
        }

        public Criteria andBisMergeNotEqualTo(Boolean value) {
            addCriterion("bis_merge <>", value, "bisMerge");
            return (Criteria) this;
        }

        public Criteria andBisMergeGreaterThan(Boolean value) {
            addCriterion("bis_merge >", value, "bisMerge");
            return (Criteria) this;
        }

        public Criteria andBisMergeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_merge >=", value, "bisMerge");
            return (Criteria) this;
        }

        public Criteria andBisMergeLessThan(Boolean value) {
            addCriterion("bis_merge <", value, "bisMerge");
            return (Criteria) this;
        }

        public Criteria andBisMergeLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_merge <=", value, "bisMerge");
            return (Criteria) this;
        }

        public Criteria andBisMergeIn(List<Boolean> values) {
            addCriterion("bis_merge in", values, "bisMerge");
            return (Criteria) this;
        }

        public Criteria andBisMergeNotIn(List<Boolean> values) {
            addCriterion("bis_merge not in", values, "bisMerge");
            return (Criteria) this;
        }

        public Criteria andBisMergeBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_merge between", value1, value2, "bisMerge");
            return (Criteria) this;
        }

        public Criteria andBisMergeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_merge not between", value1, value2, "bisMerge");
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

        public Criteria andBisSetFunctionIsNull() {
            addCriterion("bis_set_function is null");
            return (Criteria) this;
        }

        public Criteria andBisSetFunctionIsNotNull() {
            addCriterion("bis_set_function is not null");
            return (Criteria) this;
        }

        public Criteria andBisSetFunctionEqualTo(Boolean value) {
            addCriterion("bis_set_function =", value, "bisSetFunction");
            return (Criteria) this;
        }

        public Criteria andBisSetFunctionNotEqualTo(Boolean value) {
            addCriterion("bis_set_function <>", value, "bisSetFunction");
            return (Criteria) this;
        }

        public Criteria andBisSetFunctionGreaterThan(Boolean value) {
            addCriterion("bis_set_function >", value, "bisSetFunction");
            return (Criteria) this;
        }

        public Criteria andBisSetFunctionGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_set_function >=", value, "bisSetFunction");
            return (Criteria) this;
        }

        public Criteria andBisSetFunctionLessThan(Boolean value) {
            addCriterion("bis_set_function <", value, "bisSetFunction");
            return (Criteria) this;
        }

        public Criteria andBisSetFunctionLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_set_function <=", value, "bisSetFunction");
            return (Criteria) this;
        }

        public Criteria andBisSetFunctionIn(List<Boolean> values) {
            addCriterion("bis_set_function in", values, "bisSetFunction");
            return (Criteria) this;
        }

        public Criteria andBisSetFunctionNotIn(List<Boolean> values) {
            addCriterion("bis_set_function not in", values, "bisSetFunction");
            return (Criteria) this;
        }

        public Criteria andBisSetFunctionBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_set_function between", value1, value2, "bisSetFunction");
            return (Criteria) this;
        }

        public Criteria andBisSetFunctionNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_set_function not between", value1, value2, "bisSetFunction");
            return (Criteria) this;
        }

        public Criteria andSortingIsNull() {
            addCriterion("sorting is null");
            return (Criteria) this;
        }

        public Criteria andSortingIsNotNull() {
            addCriterion("sorting is not null");
            return (Criteria) this;
        }

        public Criteria andSortingEqualTo(Integer value) {
            addCriterion("sorting =", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotEqualTo(Integer value) {
            addCriterion("sorting <>", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingGreaterThan(Integer value) {
            addCriterion("sorting >", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingGreaterThanOrEqualTo(Integer value) {
            addCriterion("sorting >=", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingLessThan(Integer value) {
            addCriterion("sorting <", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingLessThanOrEqualTo(Integer value) {
            addCriterion("sorting <=", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingIn(List<Integer> values) {
            addCriterion("sorting in", values, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotIn(List<Integer> values) {
            addCriterion("sorting not in", values, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingBetween(Integer value1, Integer value2) {
            addCriterion("sorting between", value1, value2, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotBetween(Integer value1, Integer value2) {
            addCriterion("sorting not between", value1, value2, "sorting");
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