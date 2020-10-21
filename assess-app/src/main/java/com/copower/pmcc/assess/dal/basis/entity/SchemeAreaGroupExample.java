package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SchemeAreaGroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SchemeAreaGroupExample() {
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

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNull() {
            addCriterion("district is null");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNotNull() {
            addCriterion("district is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictEqualTo(String value) {
            addCriterion("district =", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotEqualTo(String value) {
            addCriterion("district <>", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThan(String value) {
            addCriterion("district >", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThanOrEqualTo(String value) {
            addCriterion("district >=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThan(String value) {
            addCriterion("district <", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThanOrEqualTo(String value) {
            addCriterion("district <=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLike(String value) {
            addCriterion("district like", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotLike(String value) {
            addCriterion("district not like", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictIn(List<String> values) {
            addCriterion("district in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotIn(List<String> values) {
            addCriterion("district not in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictBetween(String value1, String value2) {
            addCriterion("district between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotBetween(String value1, String value2) {
            addCriterion("district not between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNull() {
            addCriterion("area_name is null");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNotNull() {
            addCriterion("area_name is not null");
            return (Criteria) this;
        }

        public Criteria andAreaNameEqualTo(String value) {
            addCriterion("area_name =", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotEqualTo(String value) {
            addCriterion("area_name <>", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThan(String value) {
            addCriterion("area_name >", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("area_name >=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThan(String value) {
            addCriterion("area_name <", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThanOrEqualTo(String value) {
            addCriterion("area_name <=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLike(String value) {
            addCriterion("area_name like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotLike(String value) {
            addCriterion("area_name not like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameIn(List<String> values) {
            addCriterion("area_name in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotIn(List<String> values) {
            addCriterion("area_name not in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameBetween(String value1, String value2) {
            addCriterion("area_name between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotBetween(String value1, String value2) {
            addCriterion("area_name not between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andValueTimePointIsNull() {
            addCriterion("value_time_point is null");
            return (Criteria) this;
        }

        public Criteria andValueTimePointIsNotNull() {
            addCriterion("value_time_point is not null");
            return (Criteria) this;
        }

        public Criteria andValueTimePointEqualTo(Date value) {
            addCriterion("value_time_point =", value, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andValueTimePointNotEqualTo(Date value) {
            addCriterion("value_time_point <>", value, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andValueTimePointGreaterThan(Date value) {
            addCriterion("value_time_point >", value, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andValueTimePointGreaterThanOrEqualTo(Date value) {
            addCriterion("value_time_point >=", value, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andValueTimePointLessThan(Date value) {
            addCriterion("value_time_point <", value, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andValueTimePointLessThanOrEqualTo(Date value) {
            addCriterion("value_time_point <=", value, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andValueTimePointIn(List<Date> values) {
            addCriterion("value_time_point in", values, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andValueTimePointNotIn(List<Date> values) {
            addCriterion("value_time_point not in", values, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andValueTimePointBetween(Date value1, Date value2) {
            addCriterion("value_time_point between", value1, value2, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andValueTimePointNotBetween(Date value1, Date value2) {
            addCriterion("value_time_point not between", value1, value2, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainIsNull() {
            addCriterion("time_point_explain is null");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainIsNotNull() {
            addCriterion("time_point_explain is not null");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainEqualTo(String value) {
            addCriterion("time_point_explain =", value, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainNotEqualTo(String value) {
            addCriterion("time_point_explain <>", value, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainGreaterThan(String value) {
            addCriterion("time_point_explain >", value, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainGreaterThanOrEqualTo(String value) {
            addCriterion("time_point_explain >=", value, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainLessThan(String value) {
            addCriterion("time_point_explain <", value, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainLessThanOrEqualTo(String value) {
            addCriterion("time_point_explain <=", value, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainLike(String value) {
            addCriterion("time_point_explain like", value, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainNotLike(String value) {
            addCriterion("time_point_explain not like", value, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainIn(List<String> values) {
            addCriterion("time_point_explain in", values, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainNotIn(List<String> values) {
            addCriterion("time_point_explain not in", values, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainBetween(String value1, String value2) {
            addCriterion("time_point_explain between", value1, value2, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainNotBetween(String value1, String value2) {
            addCriterion("time_point_explain not between", value1, value2, "timePointExplain");
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

        public Criteria andEntrustAimTypeIsNull() {
            addCriterion("entrust_aim_type is null");
            return (Criteria) this;
        }

        public Criteria andEntrustAimTypeIsNotNull() {
            addCriterion("entrust_aim_type is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustAimTypeEqualTo(Integer value) {
            addCriterion("entrust_aim_type =", value, "entrustAimType");
            return (Criteria) this;
        }

        public Criteria andEntrustAimTypeNotEqualTo(Integer value) {
            addCriterion("entrust_aim_type <>", value, "entrustAimType");
            return (Criteria) this;
        }

        public Criteria andEntrustAimTypeGreaterThan(Integer value) {
            addCriterion("entrust_aim_type >", value, "entrustAimType");
            return (Criteria) this;
        }

        public Criteria andEntrustAimTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("entrust_aim_type >=", value, "entrustAimType");
            return (Criteria) this;
        }

        public Criteria andEntrustAimTypeLessThan(Integer value) {
            addCriterion("entrust_aim_type <", value, "entrustAimType");
            return (Criteria) this;
        }

        public Criteria andEntrustAimTypeLessThanOrEqualTo(Integer value) {
            addCriterion("entrust_aim_type <=", value, "entrustAimType");
            return (Criteria) this;
        }

        public Criteria andEntrustAimTypeIn(List<Integer> values) {
            addCriterion("entrust_aim_type in", values, "entrustAimType");
            return (Criteria) this;
        }

        public Criteria andEntrustAimTypeNotIn(List<Integer> values) {
            addCriterion("entrust_aim_type not in", values, "entrustAimType");
            return (Criteria) this;
        }

        public Criteria andEntrustAimTypeBetween(Integer value1, Integer value2) {
            addCriterion("entrust_aim_type between", value1, value2, "entrustAimType");
            return (Criteria) this;
        }

        public Criteria andEntrustAimTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("entrust_aim_type not between", value1, value2, "entrustAimType");
            return (Criteria) this;
        }

        public Criteria andRemarkEntrustPurposeIsNull() {
            addCriterion("remark_entrust_purpose is null");
            return (Criteria) this;
        }

        public Criteria andRemarkEntrustPurposeIsNotNull() {
            addCriterion("remark_entrust_purpose is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEntrustPurposeEqualTo(String value) {
            addCriterion("remark_entrust_purpose =", value, "remarkEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andRemarkEntrustPurposeNotEqualTo(String value) {
            addCriterion("remark_entrust_purpose <>", value, "remarkEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andRemarkEntrustPurposeGreaterThan(String value) {
            addCriterion("remark_entrust_purpose >", value, "remarkEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andRemarkEntrustPurposeGreaterThanOrEqualTo(String value) {
            addCriterion("remark_entrust_purpose >=", value, "remarkEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andRemarkEntrustPurposeLessThan(String value) {
            addCriterion("remark_entrust_purpose <", value, "remarkEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andRemarkEntrustPurposeLessThanOrEqualTo(String value) {
            addCriterion("remark_entrust_purpose <=", value, "remarkEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andRemarkEntrustPurposeLike(String value) {
            addCriterion("remark_entrust_purpose like", value, "remarkEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andRemarkEntrustPurposeNotLike(String value) {
            addCriterion("remark_entrust_purpose not like", value, "remarkEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andRemarkEntrustPurposeIn(List<String> values) {
            addCriterion("remark_entrust_purpose in", values, "remarkEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andRemarkEntrustPurposeNotIn(List<String> values) {
            addCriterion("remark_entrust_purpose not in", values, "remarkEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andRemarkEntrustPurposeBetween(String value1, String value2) {
            addCriterion("remark_entrust_purpose between", value1, value2, "remarkEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andRemarkEntrustPurposeNotBetween(String value1, String value2) {
            addCriterion("remark_entrust_purpose not between", value1, value2, "remarkEntrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLimitIsNull() {
            addCriterion("entrust_purpose_limit is null");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLimitIsNotNull() {
            addCriterion("entrust_purpose_limit is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLimitEqualTo(String value) {
            addCriterion("entrust_purpose_limit =", value, "entrustPurposeLimit");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLimitNotEqualTo(String value) {
            addCriterion("entrust_purpose_limit <>", value, "entrustPurposeLimit");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLimitGreaterThan(String value) {
            addCriterion("entrust_purpose_limit >", value, "entrustPurposeLimit");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLimitGreaterThanOrEqualTo(String value) {
            addCriterion("entrust_purpose_limit >=", value, "entrustPurposeLimit");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLimitLessThan(String value) {
            addCriterion("entrust_purpose_limit <", value, "entrustPurposeLimit");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLimitLessThanOrEqualTo(String value) {
            addCriterion("entrust_purpose_limit <=", value, "entrustPurposeLimit");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLimitLike(String value) {
            addCriterion("entrust_purpose_limit like", value, "entrustPurposeLimit");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLimitNotLike(String value) {
            addCriterion("entrust_purpose_limit not like", value, "entrustPurposeLimit");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLimitIn(List<String> values) {
            addCriterion("entrust_purpose_limit in", values, "entrustPurposeLimit");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLimitNotIn(List<String> values) {
            addCriterion("entrust_purpose_limit not in", values, "entrustPurposeLimit");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLimitBetween(String value1, String value2) {
            addCriterion("entrust_purpose_limit between", value1, value2, "entrustPurposeLimit");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLimitNotBetween(String value1, String value2) {
            addCriterion("entrust_purpose_limit not between", value1, value2, "entrustPurposeLimit");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionIsNull() {
            addCriterion("value_definition is null");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionIsNotNull() {
            addCriterion("value_definition is not null");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionEqualTo(Integer value) {
            addCriterion("value_definition =", value, "valueDefinition");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionNotEqualTo(Integer value) {
            addCriterion("value_definition <>", value, "valueDefinition");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionGreaterThan(Integer value) {
            addCriterion("value_definition >", value, "valueDefinition");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionGreaterThanOrEqualTo(Integer value) {
            addCriterion("value_definition >=", value, "valueDefinition");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionLessThan(Integer value) {
            addCriterion("value_definition <", value, "valueDefinition");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionLessThanOrEqualTo(Integer value) {
            addCriterion("value_definition <=", value, "valueDefinition");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionIn(List<Integer> values) {
            addCriterion("value_definition in", values, "valueDefinition");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionNotIn(List<Integer> values) {
            addCriterion("value_definition not in", values, "valueDefinition");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionBetween(Integer value1, Integer value2) {
            addCriterion("value_definition between", value1, value2, "valueDefinition");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionNotBetween(Integer value1, Integer value2) {
            addCriterion("value_definition not between", value1, value2, "valueDefinition");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionDescIsNull() {
            addCriterion("value_definition_desc is null");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionDescIsNotNull() {
            addCriterion("value_definition_desc is not null");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionDescEqualTo(String value) {
            addCriterion("value_definition_desc =", value, "valueDefinitionDesc");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionDescNotEqualTo(String value) {
            addCriterion("value_definition_desc <>", value, "valueDefinitionDesc");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionDescGreaterThan(String value) {
            addCriterion("value_definition_desc >", value, "valueDefinitionDesc");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionDescGreaterThanOrEqualTo(String value) {
            addCriterion("value_definition_desc >=", value, "valueDefinitionDesc");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionDescLessThan(String value) {
            addCriterion("value_definition_desc <", value, "valueDefinitionDesc");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionDescLessThanOrEqualTo(String value) {
            addCriterion("value_definition_desc <=", value, "valueDefinitionDesc");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionDescLike(String value) {
            addCriterion("value_definition_desc like", value, "valueDefinitionDesc");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionDescNotLike(String value) {
            addCriterion("value_definition_desc not like", value, "valueDefinitionDesc");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionDescIn(List<String> values) {
            addCriterion("value_definition_desc in", values, "valueDefinitionDesc");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionDescNotIn(List<String> values) {
            addCriterion("value_definition_desc not in", values, "valueDefinitionDesc");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionDescBetween(String value1, String value2) {
            addCriterion("value_definition_desc between", value1, value2, "valueDefinitionDesc");
            return (Criteria) this;
        }

        public Criteria andValueDefinitionDescNotBetween(String value1, String value2) {
            addCriterion("value_definition_desc not between", value1, value2, "valueDefinitionDesc");
            return (Criteria) this;
        }

        public Criteria andValueConnotationIsNull() {
            addCriterion("value_connotation is null");
            return (Criteria) this;
        }

        public Criteria andValueConnotationIsNotNull() {
            addCriterion("value_connotation is not null");
            return (Criteria) this;
        }

        public Criteria andValueConnotationEqualTo(String value) {
            addCriterion("value_connotation =", value, "valueConnotation");
            return (Criteria) this;
        }

        public Criteria andValueConnotationNotEqualTo(String value) {
            addCriterion("value_connotation <>", value, "valueConnotation");
            return (Criteria) this;
        }

        public Criteria andValueConnotationGreaterThan(String value) {
            addCriterion("value_connotation >", value, "valueConnotation");
            return (Criteria) this;
        }

        public Criteria andValueConnotationGreaterThanOrEqualTo(String value) {
            addCriterion("value_connotation >=", value, "valueConnotation");
            return (Criteria) this;
        }

        public Criteria andValueConnotationLessThan(String value) {
            addCriterion("value_connotation <", value, "valueConnotation");
            return (Criteria) this;
        }

        public Criteria andValueConnotationLessThanOrEqualTo(String value) {
            addCriterion("value_connotation <=", value, "valueConnotation");
            return (Criteria) this;
        }

        public Criteria andValueConnotationLike(String value) {
            addCriterion("value_connotation like", value, "valueConnotation");
            return (Criteria) this;
        }

        public Criteria andValueConnotationNotLike(String value) {
            addCriterion("value_connotation not like", value, "valueConnotation");
            return (Criteria) this;
        }

        public Criteria andValueConnotationIn(List<String> values) {
            addCriterion("value_connotation in", values, "valueConnotation");
            return (Criteria) this;
        }

        public Criteria andValueConnotationNotIn(List<String> values) {
            addCriterion("value_connotation not in", values, "valueConnotation");
            return (Criteria) this;
        }

        public Criteria andValueConnotationBetween(String value1, String value2) {
            addCriterion("value_connotation between", value1, value2, "valueConnotation");
            return (Criteria) this;
        }

        public Criteria andValueConnotationNotBetween(String value1, String value2) {
            addCriterion("value_connotation not between", value1, value2, "valueConnotation");
            return (Criteria) this;
        }

        public Criteria andValueConnotationDescIsNull() {
            addCriterion("value_connotation_desc is null");
            return (Criteria) this;
        }

        public Criteria andValueConnotationDescIsNotNull() {
            addCriterion("value_connotation_desc is not null");
            return (Criteria) this;
        }

        public Criteria andValueConnotationDescEqualTo(String value) {
            addCriterion("value_connotation_desc =", value, "valueConnotationDesc");
            return (Criteria) this;
        }

        public Criteria andValueConnotationDescNotEqualTo(String value) {
            addCriterion("value_connotation_desc <>", value, "valueConnotationDesc");
            return (Criteria) this;
        }

        public Criteria andValueConnotationDescGreaterThan(String value) {
            addCriterion("value_connotation_desc >", value, "valueConnotationDesc");
            return (Criteria) this;
        }

        public Criteria andValueConnotationDescGreaterThanOrEqualTo(String value) {
            addCriterion("value_connotation_desc >=", value, "valueConnotationDesc");
            return (Criteria) this;
        }

        public Criteria andValueConnotationDescLessThan(String value) {
            addCriterion("value_connotation_desc <", value, "valueConnotationDesc");
            return (Criteria) this;
        }

        public Criteria andValueConnotationDescLessThanOrEqualTo(String value) {
            addCriterion("value_connotation_desc <=", value, "valueConnotationDesc");
            return (Criteria) this;
        }

        public Criteria andValueConnotationDescLike(String value) {
            addCriterion("value_connotation_desc like", value, "valueConnotationDesc");
            return (Criteria) this;
        }

        public Criteria andValueConnotationDescNotLike(String value) {
            addCriterion("value_connotation_desc not like", value, "valueConnotationDesc");
            return (Criteria) this;
        }

        public Criteria andValueConnotationDescIn(List<String> values) {
            addCriterion("value_connotation_desc in", values, "valueConnotationDesc");
            return (Criteria) this;
        }

        public Criteria andValueConnotationDescNotIn(List<String> values) {
            addCriterion("value_connotation_desc not in", values, "valueConnotationDesc");
            return (Criteria) this;
        }

        public Criteria andValueConnotationDescBetween(String value1, String value2) {
            addCriterion("value_connotation_desc between", value1, value2, "valueConnotationDesc");
            return (Criteria) this;
        }

        public Criteria andValueConnotationDescNotBetween(String value1, String value2) {
            addCriterion("value_connotation_desc not between", value1, value2, "valueConnotationDesc");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeIsNull() {
            addCriterion("property_scope is null");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeIsNotNull() {
            addCriterion("property_scope is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeEqualTo(Integer value) {
            addCriterion("property_scope =", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNotEqualTo(Integer value) {
            addCriterion("property_scope <>", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeGreaterThan(Integer value) {
            addCriterion("property_scope >", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeGreaterThanOrEqualTo(Integer value) {
            addCriterion("property_scope >=", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeLessThan(Integer value) {
            addCriterion("property_scope <", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeLessThanOrEqualTo(Integer value) {
            addCriterion("property_scope <=", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeIn(List<Integer> values) {
            addCriterion("property_scope in", values, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNotIn(List<Integer> values) {
            addCriterion("property_scope not in", values, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeBetween(Integer value1, Integer value2) {
            addCriterion("property_scope between", value1, value2, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNotBetween(Integer value1, Integer value2) {
            addCriterion("property_scope not between", value1, value2, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeIsNull() {
            addCriterion("scope_include is null");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeIsNotNull() {
            addCriterion("scope_include is not null");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeEqualTo(String value) {
            addCriterion("scope_include =", value, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeNotEqualTo(String value) {
            addCriterion("scope_include <>", value, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeGreaterThan(String value) {
            addCriterion("scope_include >", value, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeGreaterThanOrEqualTo(String value) {
            addCriterion("scope_include >=", value, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeLessThan(String value) {
            addCriterion("scope_include <", value, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeLessThanOrEqualTo(String value) {
            addCriterion("scope_include <=", value, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeLike(String value) {
            addCriterion("scope_include like", value, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeNotLike(String value) {
            addCriterion("scope_include not like", value, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeIn(List<String> values) {
            addCriterion("scope_include in", values, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeNotIn(List<String> values) {
            addCriterion("scope_include not in", values, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeBetween(String value1, String value2) {
            addCriterion("scope_include between", value1, value2, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeNotBetween(String value1, String value2) {
            addCriterion("scope_include not between", value1, value2, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeIsNull() {
            addCriterion("scope_not_include is null");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeIsNotNull() {
            addCriterion("scope_not_include is not null");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeEqualTo(String value) {
            addCriterion("scope_not_include =", value, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeNotEqualTo(String value) {
            addCriterion("scope_not_include <>", value, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeGreaterThan(String value) {
            addCriterion("scope_not_include >", value, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeGreaterThanOrEqualTo(String value) {
            addCriterion("scope_not_include >=", value, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeLessThan(String value) {
            addCriterion("scope_not_include <", value, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeLessThanOrEqualTo(String value) {
            addCriterion("scope_not_include <=", value, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeLike(String value) {
            addCriterion("scope_not_include like", value, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeNotLike(String value) {
            addCriterion("scope_not_include not like", value, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeIn(List<String> values) {
            addCriterion("scope_not_include in", values, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeNotIn(List<String> values) {
            addCriterion("scope_not_include not in", values, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeBetween(String value1, String value2) {
            addCriterion("scope_not_include between", value1, value2, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeNotBetween(String value1, String value2) {
            addCriterion("scope_not_include not between", value1, value2, "scopeNotInclude");
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

        public Criteria andBestUseDescIsNull() {
            addCriterion("best_use_desc is null");
            return (Criteria) this;
        }

        public Criteria andBestUseDescIsNotNull() {
            addCriterion("best_use_desc is not null");
            return (Criteria) this;
        }

        public Criteria andBestUseDescEqualTo(String value) {
            addCriterion("best_use_desc =", value, "bestUseDesc");
            return (Criteria) this;
        }

        public Criteria andBestUseDescNotEqualTo(String value) {
            addCriterion("best_use_desc <>", value, "bestUseDesc");
            return (Criteria) this;
        }

        public Criteria andBestUseDescGreaterThan(String value) {
            addCriterion("best_use_desc >", value, "bestUseDesc");
            return (Criteria) this;
        }

        public Criteria andBestUseDescGreaterThanOrEqualTo(String value) {
            addCriterion("best_use_desc >=", value, "bestUseDesc");
            return (Criteria) this;
        }

        public Criteria andBestUseDescLessThan(String value) {
            addCriterion("best_use_desc <", value, "bestUseDesc");
            return (Criteria) this;
        }

        public Criteria andBestUseDescLessThanOrEqualTo(String value) {
            addCriterion("best_use_desc <=", value, "bestUseDesc");
            return (Criteria) this;
        }

        public Criteria andBestUseDescLike(String value) {
            addCriterion("best_use_desc like", value, "bestUseDesc");
            return (Criteria) this;
        }

        public Criteria andBestUseDescNotLike(String value) {
            addCriterion("best_use_desc not like", value, "bestUseDesc");
            return (Criteria) this;
        }

        public Criteria andBestUseDescIn(List<String> values) {
            addCriterion("best_use_desc in", values, "bestUseDesc");
            return (Criteria) this;
        }

        public Criteria andBestUseDescNotIn(List<String> values) {
            addCriterion("best_use_desc not in", values, "bestUseDesc");
            return (Criteria) this;
        }

        public Criteria andBestUseDescBetween(String value1, String value2) {
            addCriterion("best_use_desc between", value1, value2, "bestUseDesc");
            return (Criteria) this;
        }

        public Criteria andBestUseDescNotBetween(String value1, String value2) {
            addCriterion("best_use_desc not between", value1, value2, "bestUseDesc");
            return (Criteria) this;
        }

        public Criteria andCurrentSituationIsNull() {
            addCriterion("current_situation is null");
            return (Criteria) this;
        }

        public Criteria andCurrentSituationIsNotNull() {
            addCriterion("current_situation is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentSituationEqualTo(String value) {
            addCriterion("current_situation =", value, "currentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentSituationNotEqualTo(String value) {
            addCriterion("current_situation <>", value, "currentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentSituationGreaterThan(String value) {
            addCriterion("current_situation >", value, "currentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentSituationGreaterThanOrEqualTo(String value) {
            addCriterion("current_situation >=", value, "currentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentSituationLessThan(String value) {
            addCriterion("current_situation <", value, "currentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentSituationLessThanOrEqualTo(String value) {
            addCriterion("current_situation <=", value, "currentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentSituationLike(String value) {
            addCriterion("current_situation like", value, "currentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentSituationNotLike(String value) {
            addCriterion("current_situation not like", value, "currentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentSituationIn(List<String> values) {
            addCriterion("current_situation in", values, "currentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentSituationNotIn(List<String> values) {
            addCriterion("current_situation not in", values, "currentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentSituationBetween(String value1, String value2) {
            addCriterion("current_situation between", value1, value2, "currentSituation");
            return (Criteria) this;
        }

        public Criteria andCurrentSituationNotBetween(String value1, String value2) {
            addCriterion("current_situation not between", value1, value2, "currentSituation");
            return (Criteria) this;
        }

        public Criteria andSplitFromIsNull() {
            addCriterion("split_from is null");
            return (Criteria) this;
        }

        public Criteria andSplitFromIsNotNull() {
            addCriterion("split_from is not null");
            return (Criteria) this;
        }

        public Criteria andSplitFromEqualTo(Integer value) {
            addCriterion("split_from =", value, "splitFrom");
            return (Criteria) this;
        }

        public Criteria andSplitFromNotEqualTo(Integer value) {
            addCriterion("split_from <>", value, "splitFrom");
            return (Criteria) this;
        }

        public Criteria andSplitFromGreaterThan(Integer value) {
            addCriterion("split_from >", value, "splitFrom");
            return (Criteria) this;
        }

        public Criteria andSplitFromGreaterThanOrEqualTo(Integer value) {
            addCriterion("split_from >=", value, "splitFrom");
            return (Criteria) this;
        }

        public Criteria andSplitFromLessThan(Integer value) {
            addCriterion("split_from <", value, "splitFrom");
            return (Criteria) this;
        }

        public Criteria andSplitFromLessThanOrEqualTo(Integer value) {
            addCriterion("split_from <=", value, "splitFrom");
            return (Criteria) this;
        }

        public Criteria andSplitFromIn(List<Integer> values) {
            addCriterion("split_from in", values, "splitFrom");
            return (Criteria) this;
        }

        public Criteria andSplitFromNotIn(List<Integer> values) {
            addCriterion("split_from not in", values, "splitFrom");
            return (Criteria) this;
        }

        public Criteria andSplitFromBetween(Integer value1, Integer value2) {
            addCriterion("split_from between", value1, value2, "splitFrom");
            return (Criteria) this;
        }

        public Criteria andSplitFromNotBetween(Integer value1, Integer value2) {
            addCriterion("split_from not between", value1, value2, "splitFrom");
            return (Criteria) this;
        }

        public Criteria andBisNewIsNull() {
            addCriterion("bis_new is null");
            return (Criteria) this;
        }

        public Criteria andBisNewIsNotNull() {
            addCriterion("bis_new is not null");
            return (Criteria) this;
        }

        public Criteria andBisNewEqualTo(Boolean value) {
            addCriterion("bis_new =", value, "bisNew");
            return (Criteria) this;
        }

        public Criteria andBisNewNotEqualTo(Boolean value) {
            addCriterion("bis_new <>", value, "bisNew");
            return (Criteria) this;
        }

        public Criteria andBisNewGreaterThan(Boolean value) {
            addCriterion("bis_new >", value, "bisNew");
            return (Criteria) this;
        }

        public Criteria andBisNewGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_new >=", value, "bisNew");
            return (Criteria) this;
        }

        public Criteria andBisNewLessThan(Boolean value) {
            addCriterion("bis_new <", value, "bisNew");
            return (Criteria) this;
        }

        public Criteria andBisNewLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_new <=", value, "bisNew");
            return (Criteria) this;
        }

        public Criteria andBisNewIn(List<Boolean> values) {
            addCriterion("bis_new in", values, "bisNew");
            return (Criteria) this;
        }

        public Criteria andBisNewNotIn(List<Boolean> values) {
            addCriterion("bis_new not in", values, "bisNew");
            return (Criteria) this;
        }

        public Criteria andBisNewBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_new between", value1, value2, "bisNew");
            return (Criteria) this;
        }

        public Criteria andBisNewNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_new not between", value1, value2, "bisNew");
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