package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NetInfoRecordHouseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NetInfoRecordHouseExample() {
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

        public Criteria andAssignTaskIdIsNull() {
            addCriterion("assign_task_id is null");
            return (Criteria) this;
        }

        public Criteria andAssignTaskIdIsNotNull() {
            addCriterion("assign_task_id is not null");
            return (Criteria) this;
        }

        public Criteria andAssignTaskIdEqualTo(Integer value) {
            addCriterion("assign_task_id =", value, "assignTaskId");
            return (Criteria) this;
        }

        public Criteria andAssignTaskIdNotEqualTo(Integer value) {
            addCriterion("assign_task_id <>", value, "assignTaskId");
            return (Criteria) this;
        }

        public Criteria andAssignTaskIdGreaterThan(Integer value) {
            addCriterion("assign_task_id >", value, "assignTaskId");
            return (Criteria) this;
        }

        public Criteria andAssignTaskIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("assign_task_id >=", value, "assignTaskId");
            return (Criteria) this;
        }

        public Criteria andAssignTaskIdLessThan(Integer value) {
            addCriterion("assign_task_id <", value, "assignTaskId");
            return (Criteria) this;
        }

        public Criteria andAssignTaskIdLessThanOrEqualTo(Integer value) {
            addCriterion("assign_task_id <=", value, "assignTaskId");
            return (Criteria) this;
        }

        public Criteria andAssignTaskIdIn(List<Integer> values) {
            addCriterion("assign_task_id in", values, "assignTaskId");
            return (Criteria) this;
        }

        public Criteria andAssignTaskIdNotIn(List<Integer> values) {
            addCriterion("assign_task_id not in", values, "assignTaskId");
            return (Criteria) this;
        }

        public Criteria andAssignTaskIdBetween(Integer value1, Integer value2) {
            addCriterion("assign_task_id between", value1, value2, "assignTaskId");
            return (Criteria) this;
        }

        public Criteria andAssignTaskIdNotBetween(Integer value1, Integer value2) {
            addCriterion("assign_task_id not between", value1, value2, "assignTaskId");
            return (Criteria) this;
        }

        public Criteria andMasterIdIsNull() {
            addCriterion("master_id is null");
            return (Criteria) this;
        }

        public Criteria andMasterIdIsNotNull() {
            addCriterion("master_id is not null");
            return (Criteria) this;
        }

        public Criteria andMasterIdEqualTo(Integer value) {
            addCriterion("master_id =", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotEqualTo(Integer value) {
            addCriterion("master_id <>", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdGreaterThan(Integer value) {
            addCriterion("master_id >", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("master_id >=", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdLessThan(Integer value) {
            addCriterion("master_id <", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdLessThanOrEqualTo(Integer value) {
            addCriterion("master_id <=", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdIn(List<Integer> values) {
            addCriterion("master_id in", values, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotIn(List<Integer> values) {
            addCriterion("master_id not in", values, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdBetween(Integer value1, Integer value2) {
            addCriterion("master_id between", value1, value2, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotBetween(Integer value1, Integer value2) {
            addCriterion("master_id not between", value1, value2, "masterId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andBelongTypeIsNull() {
            addCriterion("belong_type is null");
            return (Criteria) this;
        }

        public Criteria andBelongTypeIsNotNull() {
            addCriterion("belong_type is not null");
            return (Criteria) this;
        }

        public Criteria andBelongTypeEqualTo(String value) {
            addCriterion("belong_type =", value, "belongType");
            return (Criteria) this;
        }

        public Criteria andBelongTypeNotEqualTo(String value) {
            addCriterion("belong_type <>", value, "belongType");
            return (Criteria) this;
        }

        public Criteria andBelongTypeGreaterThan(String value) {
            addCriterion("belong_type >", value, "belongType");
            return (Criteria) this;
        }

        public Criteria andBelongTypeGreaterThanOrEqualTo(String value) {
            addCriterion("belong_type >=", value, "belongType");
            return (Criteria) this;
        }

        public Criteria andBelongTypeLessThan(String value) {
            addCriterion("belong_type <", value, "belongType");
            return (Criteria) this;
        }

        public Criteria andBelongTypeLessThanOrEqualTo(String value) {
            addCriterion("belong_type <=", value, "belongType");
            return (Criteria) this;
        }

        public Criteria andBelongTypeLike(String value) {
            addCriterion("belong_type like", value, "belongType");
            return (Criteria) this;
        }

        public Criteria andBelongTypeNotLike(String value) {
            addCriterion("belong_type not like", value, "belongType");
            return (Criteria) this;
        }

        public Criteria andBelongTypeIn(List<String> values) {
            addCriterion("belong_type in", values, "belongType");
            return (Criteria) this;
        }

        public Criteria andBelongTypeNotIn(List<String> values) {
            addCriterion("belong_type not in", values, "belongType");
            return (Criteria) this;
        }

        public Criteria andBelongTypeBetween(String value1, String value2) {
            addCriterion("belong_type between", value1, value2, "belongType");
            return (Criteria) this;
        }

        public Criteria andBelongTypeNotBetween(String value1, String value2) {
            addCriterion("belong_type not between", value1, value2, "belongType");
            return (Criteria) this;
        }

        public Criteria andBelongCategoryIsNull() {
            addCriterion("belong_category is null");
            return (Criteria) this;
        }

        public Criteria andBelongCategoryIsNotNull() {
            addCriterion("belong_category is not null");
            return (Criteria) this;
        }

        public Criteria andBelongCategoryEqualTo(String value) {
            addCriterion("belong_category =", value, "belongCategory");
            return (Criteria) this;
        }

        public Criteria andBelongCategoryNotEqualTo(String value) {
            addCriterion("belong_category <>", value, "belongCategory");
            return (Criteria) this;
        }

        public Criteria andBelongCategoryGreaterThan(String value) {
            addCriterion("belong_category >", value, "belongCategory");
            return (Criteria) this;
        }

        public Criteria andBelongCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("belong_category >=", value, "belongCategory");
            return (Criteria) this;
        }

        public Criteria andBelongCategoryLessThan(String value) {
            addCriterion("belong_category <", value, "belongCategory");
            return (Criteria) this;
        }

        public Criteria andBelongCategoryLessThanOrEqualTo(String value) {
            addCriterion("belong_category <=", value, "belongCategory");
            return (Criteria) this;
        }

        public Criteria andBelongCategoryLike(String value) {
            addCriterion("belong_category like", value, "belongCategory");
            return (Criteria) this;
        }

        public Criteria andBelongCategoryNotLike(String value) {
            addCriterion("belong_category not like", value, "belongCategory");
            return (Criteria) this;
        }

        public Criteria andBelongCategoryIn(List<String> values) {
            addCriterion("belong_category in", values, "belongCategory");
            return (Criteria) this;
        }

        public Criteria andBelongCategoryNotIn(List<String> values) {
            addCriterion("belong_category not in", values, "belongCategory");
            return (Criteria) this;
        }

        public Criteria andBelongCategoryBetween(String value1, String value2) {
            addCriterion("belong_category between", value1, value2, "belongCategory");
            return (Criteria) this;
        }

        public Criteria andBelongCategoryNotBetween(String value1, String value2) {
            addCriterion("belong_category not between", value1, value2, "belongCategory");
            return (Criteria) this;
        }

        public Criteria andStreetIsNull() {
            addCriterion("street is null");
            return (Criteria) this;
        }

        public Criteria andStreetIsNotNull() {
            addCriterion("street is not null");
            return (Criteria) this;
        }

        public Criteria andStreetEqualTo(String value) {
            addCriterion("street =", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotEqualTo(String value) {
            addCriterion("street <>", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetGreaterThan(String value) {
            addCriterion("street >", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetGreaterThanOrEqualTo(String value) {
            addCriterion("street >=", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetLessThan(String value) {
            addCriterion("street <", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetLessThanOrEqualTo(String value) {
            addCriterion("street <=", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetLike(String value) {
            addCriterion("street like", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotLike(String value) {
            addCriterion("street not like", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetIn(List<String> values) {
            addCriterion("street in", values, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotIn(List<String> values) {
            addCriterion("street not in", values, "street");
            return (Criteria) this;
        }

        public Criteria andStreetBetween(String value1, String value2) {
            addCriterion("street between", value1, value2, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotBetween(String value1, String value2) {
            addCriterion("street not between", value1, value2, "street");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(BigDecimal value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(BigDecimal value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(BigDecimal value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(BigDecimal value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<BigDecimal> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<BigDecimal> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("area not between", value1, value2, "area");
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

        public Criteria andDealTypeIsNull() {
            addCriterion("deal_type is null");
            return (Criteria) this;
        }

        public Criteria andDealTypeIsNotNull() {
            addCriterion("deal_type is not null");
            return (Criteria) this;
        }

        public Criteria andDealTypeEqualTo(Integer value) {
            addCriterion("deal_type =", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeNotEqualTo(Integer value) {
            addCriterion("deal_type <>", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeGreaterThan(Integer value) {
            addCriterion("deal_type >", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("deal_type >=", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeLessThan(Integer value) {
            addCriterion("deal_type <", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeLessThanOrEqualTo(Integer value) {
            addCriterion("deal_type <=", value, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeIn(List<Integer> values) {
            addCriterion("deal_type in", values, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeNotIn(List<Integer> values) {
            addCriterion("deal_type not in", values, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeBetween(Integer value1, Integer value2) {
            addCriterion("deal_type between", value1, value2, "dealType");
            return (Criteria) this;
        }

        public Criteria andDealTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("deal_type not between", value1, value2, "dealType");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceIsNull() {
            addCriterion("current_price is null");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceIsNotNull() {
            addCriterion("current_price is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceEqualTo(BigDecimal value) {
            addCriterion("current_price =", value, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceNotEqualTo(BigDecimal value) {
            addCriterion("current_price <>", value, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceGreaterThan(BigDecimal value) {
            addCriterion("current_price >", value, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("current_price >=", value, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceLessThan(BigDecimal value) {
            addCriterion("current_price <", value, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("current_price <=", value, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceIn(List<BigDecimal> values) {
            addCriterion("current_price in", values, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceNotIn(List<BigDecimal> values) {
            addCriterion("current_price not in", values, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("current_price between", value1, value2, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("current_price not between", value1, value2, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateIsNull() {
            addCriterion("negotiated_date is null");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateIsNotNull() {
            addCriterion("negotiated_date is not null");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateEqualTo(Date value) {
            addCriterion("negotiated_date =", value, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateNotEqualTo(Date value) {
            addCriterion("negotiated_date <>", value, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateGreaterThan(Date value) {
            addCriterion("negotiated_date >", value, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("negotiated_date >=", value, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateLessThan(Date value) {
            addCriterion("negotiated_date <", value, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateLessThanOrEqualTo(Date value) {
            addCriterion("negotiated_date <=", value, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateIn(List<Date> values) {
            addCriterion("negotiated_date in", values, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateNotIn(List<Date> values) {
            addCriterion("negotiated_date not in", values, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateBetween(Date value1, Date value2) {
            addCriterion("negotiated_date between", value1, value2, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateNotBetween(Date value1, Date value2) {
            addCriterion("negotiated_date not between", value1, value2, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andConsultPriceIsNull() {
            addCriterion("consult_price is null");
            return (Criteria) this;
        }

        public Criteria andConsultPriceIsNotNull() {
            addCriterion("consult_price is not null");
            return (Criteria) this;
        }

        public Criteria andConsultPriceEqualTo(BigDecimal value) {
            addCriterion("consult_price =", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceNotEqualTo(BigDecimal value) {
            addCriterion("consult_price <>", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceGreaterThan(BigDecimal value) {
            addCriterion("consult_price >", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("consult_price >=", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceLessThan(BigDecimal value) {
            addCriterion("consult_price <", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("consult_price <=", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceIn(List<BigDecimal> values) {
            addCriterion("consult_price in", values, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceNotIn(List<BigDecimal> values) {
            addCriterion("consult_price not in", values, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("consult_price between", value1, value2, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("consult_price not between", value1, value2, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andAssessStandardDateIsNull() {
            addCriterion("assess_standard_date is null");
            return (Criteria) this;
        }

        public Criteria andAssessStandardDateIsNotNull() {
            addCriterion("assess_standard_date is not null");
            return (Criteria) this;
        }

        public Criteria andAssessStandardDateEqualTo(Date value) {
            addCriterion("assess_standard_date =", value, "assessStandardDate");
            return (Criteria) this;
        }

        public Criteria andAssessStandardDateNotEqualTo(Date value) {
            addCriterion("assess_standard_date <>", value, "assessStandardDate");
            return (Criteria) this;
        }

        public Criteria andAssessStandardDateGreaterThan(Date value) {
            addCriterion("assess_standard_date >", value, "assessStandardDate");
            return (Criteria) this;
        }

        public Criteria andAssessStandardDateGreaterThanOrEqualTo(Date value) {
            addCriterion("assess_standard_date >=", value, "assessStandardDate");
            return (Criteria) this;
        }

        public Criteria andAssessStandardDateLessThan(Date value) {
            addCriterion("assess_standard_date <", value, "assessStandardDate");
            return (Criteria) this;
        }

        public Criteria andAssessStandardDateLessThanOrEqualTo(Date value) {
            addCriterion("assess_standard_date <=", value, "assessStandardDate");
            return (Criteria) this;
        }

        public Criteria andAssessStandardDateIn(List<Date> values) {
            addCriterion("assess_standard_date in", values, "assessStandardDate");
            return (Criteria) this;
        }

        public Criteria andAssessStandardDateNotIn(List<Date> values) {
            addCriterion("assess_standard_date not in", values, "assessStandardDate");
            return (Criteria) this;
        }

        public Criteria andAssessStandardDateBetween(Date value1, Date value2) {
            addCriterion("assess_standard_date between", value1, value2, "assessStandardDate");
            return (Criteria) this;
        }

        public Criteria andAssessStandardDateNotBetween(Date value1, Date value2) {
            addCriterion("assess_standard_date not between", value1, value2, "assessStandardDate");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNull() {
            addCriterion("unit_price is null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNotNull() {
            addCriterion("unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceEqualTo(BigDecimal value) {
            addCriterion("unit_price =", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotEqualTo(BigDecimal value) {
            addCriterion("unit_price <>", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThan(BigDecimal value) {
            addCriterion("unit_price >", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unit_price >=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThan(BigDecimal value) {
            addCriterion("unit_price <", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unit_price <=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIn(List<BigDecimal> values) {
            addCriterion("unit_price in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotIn(List<BigDecimal> values) {
            addCriterion("unit_price not in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unit_price between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unit_price not between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andHouseRealizationRatiosIsNull() {
            addCriterion("house_realization_ratios is null");
            return (Criteria) this;
        }

        public Criteria andHouseRealizationRatiosIsNotNull() {
            addCriterion("house_realization_ratios is not null");
            return (Criteria) this;
        }

        public Criteria andHouseRealizationRatiosEqualTo(BigDecimal value) {
            addCriterion("house_realization_ratios =", value, "houseRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andHouseRealizationRatiosNotEqualTo(BigDecimal value) {
            addCriterion("house_realization_ratios <>", value, "houseRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andHouseRealizationRatiosGreaterThan(BigDecimal value) {
            addCriterion("house_realization_ratios >", value, "houseRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andHouseRealizationRatiosGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("house_realization_ratios >=", value, "houseRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andHouseRealizationRatiosLessThan(BigDecimal value) {
            addCriterion("house_realization_ratios <", value, "houseRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andHouseRealizationRatiosLessThanOrEqualTo(BigDecimal value) {
            addCriterion("house_realization_ratios <=", value, "houseRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andHouseRealizationRatiosIn(List<BigDecimal> values) {
            addCriterion("house_realization_ratios in", values, "houseRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andHouseRealizationRatiosNotIn(List<BigDecimal> values) {
            addCriterion("house_realization_ratios not in", values, "houseRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andHouseRealizationRatiosBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("house_realization_ratios between", value1, value2, "houseRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andHouseRealizationRatiosNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("house_realization_ratios not between", value1, value2, "houseRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andRealizationCycleIsNull() {
            addCriterion("realization_cycle is null");
            return (Criteria) this;
        }

        public Criteria andRealizationCycleIsNotNull() {
            addCriterion("realization_cycle is not null");
            return (Criteria) this;
        }

        public Criteria andRealizationCycleEqualTo(String value) {
            addCriterion("realization_cycle =", value, "realizationCycle");
            return (Criteria) this;
        }

        public Criteria andRealizationCycleNotEqualTo(String value) {
            addCriterion("realization_cycle <>", value, "realizationCycle");
            return (Criteria) this;
        }

        public Criteria andRealizationCycleGreaterThan(String value) {
            addCriterion("realization_cycle >", value, "realizationCycle");
            return (Criteria) this;
        }

        public Criteria andRealizationCycleGreaterThanOrEqualTo(String value) {
            addCriterion("realization_cycle >=", value, "realizationCycle");
            return (Criteria) this;
        }

        public Criteria andRealizationCycleLessThan(String value) {
            addCriterion("realization_cycle <", value, "realizationCycle");
            return (Criteria) this;
        }

        public Criteria andRealizationCycleLessThanOrEqualTo(String value) {
            addCriterion("realization_cycle <=", value, "realizationCycle");
            return (Criteria) this;
        }

        public Criteria andRealizationCycleLike(String value) {
            addCriterion("realization_cycle like", value, "realizationCycle");
            return (Criteria) this;
        }

        public Criteria andRealizationCycleNotLike(String value) {
            addCriterion("realization_cycle not like", value, "realizationCycle");
            return (Criteria) this;
        }

        public Criteria andRealizationCycleIn(List<String> values) {
            addCriterion("realization_cycle in", values, "realizationCycle");
            return (Criteria) this;
        }

        public Criteria andRealizationCycleNotIn(List<String> values) {
            addCriterion("realization_cycle not in", values, "realizationCycle");
            return (Criteria) this;
        }

        public Criteria andRealizationCycleBetween(String value1, String value2) {
            addCriterion("realization_cycle between", value1, value2, "realizationCycle");
            return (Criteria) this;
        }

        public Criteria andRealizationCycleNotBetween(String value1, String value2) {
            addCriterion("realization_cycle not between", value1, value2, "realizationCycle");
            return (Criteria) this;
        }

        public Criteria andDealPartInfoIsNull() {
            addCriterion("deal_part_info is null");
            return (Criteria) this;
        }

        public Criteria andDealPartInfoIsNotNull() {
            addCriterion("deal_part_info is not null");
            return (Criteria) this;
        }

        public Criteria andDealPartInfoEqualTo(String value) {
            addCriterion("deal_part_info =", value, "dealPartInfo");
            return (Criteria) this;
        }

        public Criteria andDealPartInfoNotEqualTo(String value) {
            addCriterion("deal_part_info <>", value, "dealPartInfo");
            return (Criteria) this;
        }

        public Criteria andDealPartInfoGreaterThan(String value) {
            addCriterion("deal_part_info >", value, "dealPartInfo");
            return (Criteria) this;
        }

        public Criteria andDealPartInfoGreaterThanOrEqualTo(String value) {
            addCriterion("deal_part_info >=", value, "dealPartInfo");
            return (Criteria) this;
        }

        public Criteria andDealPartInfoLessThan(String value) {
            addCriterion("deal_part_info <", value, "dealPartInfo");
            return (Criteria) this;
        }

        public Criteria andDealPartInfoLessThanOrEqualTo(String value) {
            addCriterion("deal_part_info <=", value, "dealPartInfo");
            return (Criteria) this;
        }

        public Criteria andDealPartInfoLike(String value) {
            addCriterion("deal_part_info like", value, "dealPartInfo");
            return (Criteria) this;
        }

        public Criteria andDealPartInfoNotLike(String value) {
            addCriterion("deal_part_info not like", value, "dealPartInfo");
            return (Criteria) this;
        }

        public Criteria andDealPartInfoIn(List<String> values) {
            addCriterion("deal_part_info in", values, "dealPartInfo");
            return (Criteria) this;
        }

        public Criteria andDealPartInfoNotIn(List<String> values) {
            addCriterion("deal_part_info not in", values, "dealPartInfo");
            return (Criteria) this;
        }

        public Criteria andDealPartInfoBetween(String value1, String value2) {
            addCriterion("deal_part_info between", value1, value2, "dealPartInfo");
            return (Criteria) this;
        }

        public Criteria andDealPartInfoNotBetween(String value1, String value2) {
            addCriterion("deal_part_info not between", value1, value2, "dealPartInfo");
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

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
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

        public Criteria andBuildingNumberIsNull() {
            addCriterion("building_number is null");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberIsNotNull() {
            addCriterion("building_number is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberEqualTo(String value) {
            addCriterion("building_number =", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberNotEqualTo(String value) {
            addCriterion("building_number <>", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberGreaterThan(String value) {
            addCriterion("building_number >", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberGreaterThanOrEqualTo(String value) {
            addCriterion("building_number >=", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberLessThan(String value) {
            addCriterion("building_number <", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberLessThanOrEqualTo(String value) {
            addCriterion("building_number <=", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberLike(String value) {
            addCriterion("building_number like", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberNotLike(String value) {
            addCriterion("building_number not like", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberIn(List<String> values) {
            addCriterion("building_number in", values, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberNotIn(List<String> values) {
            addCriterion("building_number not in", values, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberBetween(String value1, String value2) {
            addCriterion("building_number between", value1, value2, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberNotBetween(String value1, String value2) {
            addCriterion("building_number not between", value1, value2, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberIsNull() {
            addCriterion("unit_number is null");
            return (Criteria) this;
        }

        public Criteria andUnitNumberIsNotNull() {
            addCriterion("unit_number is not null");
            return (Criteria) this;
        }

        public Criteria andUnitNumberEqualTo(String value) {
            addCriterion("unit_number =", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberNotEqualTo(String value) {
            addCriterion("unit_number <>", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberGreaterThan(String value) {
            addCriterion("unit_number >", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberGreaterThanOrEqualTo(String value) {
            addCriterion("unit_number >=", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberLessThan(String value) {
            addCriterion("unit_number <", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberLessThanOrEqualTo(String value) {
            addCriterion("unit_number <=", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberLike(String value) {
            addCriterion("unit_number like", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberNotLike(String value) {
            addCriterion("unit_number not like", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberIn(List<String> values) {
            addCriterion("unit_number in", values, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberNotIn(List<String> values) {
            addCriterion("unit_number not in", values, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberBetween(String value1, String value2) {
            addCriterion("unit_number between", value1, value2, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberNotBetween(String value1, String value2) {
            addCriterion("unit_number not between", value1, value2, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberIsNull() {
            addCriterion("house_number is null");
            return (Criteria) this;
        }

        public Criteria andHouseNumberIsNotNull() {
            addCriterion("house_number is not null");
            return (Criteria) this;
        }

        public Criteria andHouseNumberEqualTo(String value) {
            addCriterion("house_number =", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberNotEqualTo(String value) {
            addCriterion("house_number <>", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberGreaterThan(String value) {
            addCriterion("house_number >", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberGreaterThanOrEqualTo(String value) {
            addCriterion("house_number >=", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberLessThan(String value) {
            addCriterion("house_number <", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberLessThanOrEqualTo(String value) {
            addCriterion("house_number <=", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberLike(String value) {
            addCriterion("house_number like", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberNotLike(String value) {
            addCriterion("house_number not like", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberIn(List<String> values) {
            addCriterion("house_number in", values, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberNotIn(List<String> values) {
            addCriterion("house_number not in", values, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberBetween(String value1, String value2) {
            addCriterion("house_number between", value1, value2, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberNotBetween(String value1, String value2) {
            addCriterion("house_number not between", value1, value2, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andApproverIsNull() {
            addCriterion("approver is null");
            return (Criteria) this;
        }

        public Criteria andApproverIsNotNull() {
            addCriterion("approver is not null");
            return (Criteria) this;
        }

        public Criteria andApproverEqualTo(String value) {
            addCriterion("approver =", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverNotEqualTo(String value) {
            addCriterion("approver <>", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverGreaterThan(String value) {
            addCriterion("approver >", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverGreaterThanOrEqualTo(String value) {
            addCriterion("approver >=", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverLessThan(String value) {
            addCriterion("approver <", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverLessThanOrEqualTo(String value) {
            addCriterion("approver <=", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverLike(String value) {
            addCriterion("approver like", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverNotLike(String value) {
            addCriterion("approver not like", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverIn(List<String> values) {
            addCriterion("approver in", values, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverNotIn(List<String> values) {
            addCriterion("approver not in", values, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverBetween(String value1, String value2) {
            addCriterion("approver between", value1, value2, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverNotBetween(String value1, String value2) {
            addCriterion("approver not between", value1, value2, "approver");
            return (Criteria) this;
        }

        public Criteria andTradingTypeIsNull() {
            addCriterion("trading_type is null");
            return (Criteria) this;
        }

        public Criteria andTradingTypeIsNotNull() {
            addCriterion("trading_type is not null");
            return (Criteria) this;
        }

        public Criteria andTradingTypeEqualTo(Integer value) {
            addCriterion("trading_type =", value, "tradingType");
            return (Criteria) this;
        }

        public Criteria andTradingTypeNotEqualTo(Integer value) {
            addCriterion("trading_type <>", value, "tradingType");
            return (Criteria) this;
        }

        public Criteria andTradingTypeGreaterThan(Integer value) {
            addCriterion("trading_type >", value, "tradingType");
            return (Criteria) this;
        }

        public Criteria andTradingTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("trading_type >=", value, "tradingType");
            return (Criteria) this;
        }

        public Criteria andTradingTypeLessThan(Integer value) {
            addCriterion("trading_type <", value, "tradingType");
            return (Criteria) this;
        }

        public Criteria andTradingTypeLessThanOrEqualTo(Integer value) {
            addCriterion("trading_type <=", value, "tradingType");
            return (Criteria) this;
        }

        public Criteria andTradingTypeIn(List<Integer> values) {
            addCriterion("trading_type in", values, "tradingType");
            return (Criteria) this;
        }

        public Criteria andTradingTypeNotIn(List<Integer> values) {
            addCriterion("trading_type not in", values, "tradingType");
            return (Criteria) this;
        }

        public Criteria andTradingTypeBetween(Integer value1, Integer value2) {
            addCriterion("trading_type between", value1, value2, "tradingType");
            return (Criteria) this;
        }

        public Criteria andTradingTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("trading_type not between", value1, value2, "tradingType");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitStatusIsNull() {
            addCriterion("purchase_limit_status is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitStatusIsNotNull() {
            addCriterion("purchase_limit_status is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitStatusEqualTo(String value) {
            addCriterion("purchase_limit_status =", value, "purchaseLimitStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitStatusNotEqualTo(String value) {
            addCriterion("purchase_limit_status <>", value, "purchaseLimitStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitStatusGreaterThan(String value) {
            addCriterion("purchase_limit_status >", value, "purchaseLimitStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitStatusGreaterThanOrEqualTo(String value) {
            addCriterion("purchase_limit_status >=", value, "purchaseLimitStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitStatusLessThan(String value) {
            addCriterion("purchase_limit_status <", value, "purchaseLimitStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitStatusLessThanOrEqualTo(String value) {
            addCriterion("purchase_limit_status <=", value, "purchaseLimitStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitStatusLike(String value) {
            addCriterion("purchase_limit_status like", value, "purchaseLimitStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitStatusNotLike(String value) {
            addCriterion("purchase_limit_status not like", value, "purchaseLimitStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitStatusIn(List<String> values) {
            addCriterion("purchase_limit_status in", values, "purchaseLimitStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitStatusNotIn(List<String> values) {
            addCriterion("purchase_limit_status not in", values, "purchaseLimitStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitStatusBetween(String value1, String value2) {
            addCriterion("purchase_limit_status between", value1, value2, "purchaseLimitStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitStatusNotBetween(String value1, String value2) {
            addCriterion("purchase_limit_status not between", value1, value2, "purchaseLimitStatus");
            return (Criteria) this;
        }

        public Criteria andBisNewestIsNull() {
            addCriterion("bis_newest is null");
            return (Criteria) this;
        }

        public Criteria andBisNewestIsNotNull() {
            addCriterion("bis_newest is not null");
            return (Criteria) this;
        }

        public Criteria andBisNewestEqualTo(Boolean value) {
            addCriterion("bis_newest =", value, "bisNewest");
            return (Criteria) this;
        }

        public Criteria andBisNewestNotEqualTo(Boolean value) {
            addCriterion("bis_newest <>", value, "bisNewest");
            return (Criteria) this;
        }

        public Criteria andBisNewestGreaterThan(Boolean value) {
            addCriterion("bis_newest >", value, "bisNewest");
            return (Criteria) this;
        }

        public Criteria andBisNewestGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_newest >=", value, "bisNewest");
            return (Criteria) this;
        }

        public Criteria andBisNewestLessThan(Boolean value) {
            addCriterion("bis_newest <", value, "bisNewest");
            return (Criteria) this;
        }

        public Criteria andBisNewestLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_newest <=", value, "bisNewest");
            return (Criteria) this;
        }

        public Criteria andBisNewestIn(List<Boolean> values) {
            addCriterion("bis_newest in", values, "bisNewest");
            return (Criteria) this;
        }

        public Criteria andBisNewestNotIn(List<Boolean> values) {
            addCriterion("bis_newest not in", values, "bisNewest");
            return (Criteria) this;
        }

        public Criteria andBisNewestBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_newest between", value1, value2, "bisNewest");
            return (Criteria) this;
        }

        public Criteria andBisNewestNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_newest not between", value1, value2, "bisNewest");
            return (Criteria) this;
        }

        public Criteria andBeUpgradeIdIsNull() {
            addCriterion("be_upgrade_id is null");
            return (Criteria) this;
        }

        public Criteria andBeUpgradeIdIsNotNull() {
            addCriterion("be_upgrade_id is not null");
            return (Criteria) this;
        }

        public Criteria andBeUpgradeIdEqualTo(Integer value) {
            addCriterion("be_upgrade_id =", value, "beUpgradeId");
            return (Criteria) this;
        }

        public Criteria andBeUpgradeIdNotEqualTo(Integer value) {
            addCriterion("be_upgrade_id <>", value, "beUpgradeId");
            return (Criteria) this;
        }

        public Criteria andBeUpgradeIdGreaterThan(Integer value) {
            addCriterion("be_upgrade_id >", value, "beUpgradeId");
            return (Criteria) this;
        }

        public Criteria andBeUpgradeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("be_upgrade_id >=", value, "beUpgradeId");
            return (Criteria) this;
        }

        public Criteria andBeUpgradeIdLessThan(Integer value) {
            addCriterion("be_upgrade_id <", value, "beUpgradeId");
            return (Criteria) this;
        }

        public Criteria andBeUpgradeIdLessThanOrEqualTo(Integer value) {
            addCriterion("be_upgrade_id <=", value, "beUpgradeId");
            return (Criteria) this;
        }

        public Criteria andBeUpgradeIdIn(List<Integer> values) {
            addCriterion("be_upgrade_id in", values, "beUpgradeId");
            return (Criteria) this;
        }

        public Criteria andBeUpgradeIdNotIn(List<Integer> values) {
            addCriterion("be_upgrade_id not in", values, "beUpgradeId");
            return (Criteria) this;
        }

        public Criteria andBeUpgradeIdBetween(Integer value1, Integer value2) {
            addCriterion("be_upgrade_id between", value1, value2, "beUpgradeId");
            return (Criteria) this;
        }

        public Criteria andBeUpgradeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("be_upgrade_id not between", value1, value2, "beUpgradeId");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
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