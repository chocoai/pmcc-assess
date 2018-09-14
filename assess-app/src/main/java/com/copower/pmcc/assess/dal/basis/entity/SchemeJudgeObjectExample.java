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

        public Criteria andSourceIdIsNull() {
            addCriterion("source_id is null");
            return (Criteria) this;
        }

        public Criteria andSourceIdIsNotNull() {
            addCriterion("source_id is not null");
            return (Criteria) this;
        }

        public Criteria andSourceIdEqualTo(Integer value) {
            addCriterion("source_id =", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdNotEqualTo(Integer value) {
            addCriterion("source_id <>", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdGreaterThan(Integer value) {
            addCriterion("source_id >", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("source_id >=", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdLessThan(Integer value) {
            addCriterion("source_id <", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdLessThanOrEqualTo(Integer value) {
            addCriterion("source_id <=", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdIn(List<Integer> values) {
            addCriterion("source_id in", values, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdNotIn(List<Integer> values) {
            addCriterion("source_id not in", values, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdBetween(Integer value1, Integer value2) {
            addCriterion("source_id between", value1, value2, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("source_id not between", value1, value2, "sourceId");
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

        public Criteria andCertUseEqualTo(Integer value) {
            addCriterion("cert_use =", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotEqualTo(Integer value) {
            addCriterion("cert_use <>", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseGreaterThan(Integer value) {
            addCriterion("cert_use >", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("cert_use >=", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseLessThan(Integer value) {
            addCriterion("cert_use <", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseLessThanOrEqualTo(Integer value) {
            addCriterion("cert_use <=", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseIn(List<Integer> values) {
            addCriterion("cert_use in", values, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotIn(List<Integer> values) {
            addCriterion("cert_use not in", values, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseBetween(Integer value1, Integer value2) {
            addCriterion("cert_use between", value1, value2, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotBetween(Integer value1, Integer value2) {
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

        public Criteria andPracticalUseEqualTo(Integer value) {
            addCriterion("practical_use =", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseNotEqualTo(Integer value) {
            addCriterion("practical_use <>", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseGreaterThan(Integer value) {
            addCriterion("practical_use >", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("practical_use >=", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseLessThan(Integer value) {
            addCriterion("practical_use <", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseLessThanOrEqualTo(Integer value) {
            addCriterion("practical_use <=", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseIn(List<Integer> values) {
            addCriterion("practical_use in", values, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseNotIn(List<Integer> values) {
            addCriterion("practical_use not in", values, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseBetween(Integer value1, Integer value2) {
            addCriterion("practical_use between", value1, value2, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseNotBetween(Integer value1, Integer value2) {
            addCriterion("practical_use not between", value1, value2, "practicalUse");
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

        public Criteria andEvaluationAreaEqualTo(String value) {
            addCriterion("evaluation_area =", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaNotEqualTo(String value) {
            addCriterion("evaluation_area <>", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaGreaterThan(String value) {
            addCriterion("evaluation_area >", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaGreaterThanOrEqualTo(String value) {
            addCriterion("evaluation_area >=", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaLessThan(String value) {
            addCriterion("evaluation_area <", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaLessThanOrEqualTo(String value) {
            addCriterion("evaluation_area <=", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaLike(String value) {
            addCriterion("evaluation_area like", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaNotLike(String value) {
            addCriterion("evaluation_area not like", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaIn(List<String> values) {
            addCriterion("evaluation_area in", values, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaNotIn(List<String> values) {
            addCriterion("evaluation_area not in", values, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaBetween(String value1, String value2) {
            addCriterion("evaluation_area between", value1, value2, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaNotBetween(String value1, String value2) {
            addCriterion("evaluation_area not between", value1, value2, "evaluationArea");
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