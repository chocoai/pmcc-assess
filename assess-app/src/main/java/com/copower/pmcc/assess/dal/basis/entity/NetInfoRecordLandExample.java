package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NetInfoRecordLandExample {
    /**
     * tb_net_info_record_land
     */
    protected String orderByClause;

    /**
     * tb_net_info_record_land
     */
    protected boolean distinct;

    /**
     * tb_net_info_record_land
     */
    protected List<Criteria> oredCriteria;

    public NetInfoRecordLandExample() {
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
     * tb_net_info_record_land
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

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
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

        public Criteria andLandPurposeIsNull() {
            addCriterion("land_purpose is null");
            return (Criteria) this;
        }

        public Criteria andLandPurposeIsNotNull() {
            addCriterion("land_purpose is not null");
            return (Criteria) this;
        }

        public Criteria andLandPurposeEqualTo(String value) {
            addCriterion("land_purpose =", value, "landPurpose");
            return (Criteria) this;
        }

        public Criteria andLandPurposeNotEqualTo(String value) {
            addCriterion("land_purpose <>", value, "landPurpose");
            return (Criteria) this;
        }

        public Criteria andLandPurposeGreaterThan(String value) {
            addCriterion("land_purpose >", value, "landPurpose");
            return (Criteria) this;
        }

        public Criteria andLandPurposeGreaterThanOrEqualTo(String value) {
            addCriterion("land_purpose >=", value, "landPurpose");
            return (Criteria) this;
        }

        public Criteria andLandPurposeLessThan(String value) {
            addCriterion("land_purpose <", value, "landPurpose");
            return (Criteria) this;
        }

        public Criteria andLandPurposeLessThanOrEqualTo(String value) {
            addCriterion("land_purpose <=", value, "landPurpose");
            return (Criteria) this;
        }

        public Criteria andLandPurposeLike(String value) {
            addCriterion("land_purpose like", value, "landPurpose");
            return (Criteria) this;
        }

        public Criteria andLandPurposeNotLike(String value) {
            addCriterion("land_purpose not like", value, "landPurpose");
            return (Criteria) this;
        }

        public Criteria andLandPurposeIn(List<String> values) {
            addCriterion("land_purpose in", values, "landPurpose");
            return (Criteria) this;
        }

        public Criteria andLandPurposeNotIn(List<String> values) {
            addCriterion("land_purpose not in", values, "landPurpose");
            return (Criteria) this;
        }

        public Criteria andLandPurposeBetween(String value1, String value2) {
            addCriterion("land_purpose between", value1, value2, "landPurpose");
            return (Criteria) this;
        }

        public Criteria andLandPurposeNotBetween(String value1, String value2) {
            addCriterion("land_purpose not between", value1, value2, "landPurpose");
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

        public Criteria andAreaUnitIsNull() {
            addCriterion("area_unit is null");
            return (Criteria) this;
        }

        public Criteria andAreaUnitIsNotNull() {
            addCriterion("area_unit is not null");
            return (Criteria) this;
        }

        public Criteria andAreaUnitEqualTo(String value) {
            addCriterion("area_unit =", value, "areaUnit");
            return (Criteria) this;
        }

        public Criteria andAreaUnitNotEqualTo(String value) {
            addCriterion("area_unit <>", value, "areaUnit");
            return (Criteria) this;
        }

        public Criteria andAreaUnitGreaterThan(String value) {
            addCriterion("area_unit >", value, "areaUnit");
            return (Criteria) this;
        }

        public Criteria andAreaUnitGreaterThanOrEqualTo(String value) {
            addCriterion("area_unit >=", value, "areaUnit");
            return (Criteria) this;
        }

        public Criteria andAreaUnitLessThan(String value) {
            addCriterion("area_unit <", value, "areaUnit");
            return (Criteria) this;
        }

        public Criteria andAreaUnitLessThanOrEqualTo(String value) {
            addCriterion("area_unit <=", value, "areaUnit");
            return (Criteria) this;
        }

        public Criteria andAreaUnitLike(String value) {
            addCriterion("area_unit like", value, "areaUnit");
            return (Criteria) this;
        }

        public Criteria andAreaUnitNotLike(String value) {
            addCriterion("area_unit not like", value, "areaUnit");
            return (Criteria) this;
        }

        public Criteria andAreaUnitIn(List<String> values) {
            addCriterion("area_unit in", values, "areaUnit");
            return (Criteria) this;
        }

        public Criteria andAreaUnitNotIn(List<String> values) {
            addCriterion("area_unit not in", values, "areaUnit");
            return (Criteria) this;
        }

        public Criteria andAreaUnitBetween(String value1, String value2) {
            addCriterion("area_unit between", value1, value2, "areaUnit");
            return (Criteria) this;
        }

        public Criteria andAreaUnitNotBetween(String value1, String value2) {
            addCriterion("area_unit not between", value1, value2, "areaUnit");
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

        public Criteria andParcelNumberIsNull() {
            addCriterion("parcel_number is null");
            return (Criteria) this;
        }

        public Criteria andParcelNumberIsNotNull() {
            addCriterion("parcel_number is not null");
            return (Criteria) this;
        }

        public Criteria andParcelNumberEqualTo(String value) {
            addCriterion("parcel_number =", value, "parcelNumber");
            return (Criteria) this;
        }

        public Criteria andParcelNumberNotEqualTo(String value) {
            addCriterion("parcel_number <>", value, "parcelNumber");
            return (Criteria) this;
        }

        public Criteria andParcelNumberGreaterThan(String value) {
            addCriterion("parcel_number >", value, "parcelNumber");
            return (Criteria) this;
        }

        public Criteria andParcelNumberGreaterThanOrEqualTo(String value) {
            addCriterion("parcel_number >=", value, "parcelNumber");
            return (Criteria) this;
        }

        public Criteria andParcelNumberLessThan(String value) {
            addCriterion("parcel_number <", value, "parcelNumber");
            return (Criteria) this;
        }

        public Criteria andParcelNumberLessThanOrEqualTo(String value) {
            addCriterion("parcel_number <=", value, "parcelNumber");
            return (Criteria) this;
        }

        public Criteria andParcelNumberLike(String value) {
            addCriterion("parcel_number like", value, "parcelNumber");
            return (Criteria) this;
        }

        public Criteria andParcelNumberNotLike(String value) {
            addCriterion("parcel_number not like", value, "parcelNumber");
            return (Criteria) this;
        }

        public Criteria andParcelNumberIn(List<String> values) {
            addCriterion("parcel_number in", values, "parcelNumber");
            return (Criteria) this;
        }

        public Criteria andParcelNumberNotIn(List<String> values) {
            addCriterion("parcel_number not in", values, "parcelNumber");
            return (Criteria) this;
        }

        public Criteria andParcelNumberBetween(String value1, String value2) {
            addCriterion("parcel_number between", value1, value2, "parcelNumber");
            return (Criteria) this;
        }

        public Criteria andParcelNumberNotBetween(String value1, String value2) {
            addCriterion("parcel_number not between", value1, value2, "parcelNumber");
            return (Criteria) this;
        }

        public Criteria andParcelSiteIsNull() {
            addCriterion("parcel_site is null");
            return (Criteria) this;
        }

        public Criteria andParcelSiteIsNotNull() {
            addCriterion("parcel_site is not null");
            return (Criteria) this;
        }

        public Criteria andParcelSiteEqualTo(String value) {
            addCriterion("parcel_site =", value, "parcelSite");
            return (Criteria) this;
        }

        public Criteria andParcelSiteNotEqualTo(String value) {
            addCriterion("parcel_site <>", value, "parcelSite");
            return (Criteria) this;
        }

        public Criteria andParcelSiteGreaterThan(String value) {
            addCriterion("parcel_site >", value, "parcelSite");
            return (Criteria) this;
        }

        public Criteria andParcelSiteGreaterThanOrEqualTo(String value) {
            addCriterion("parcel_site >=", value, "parcelSite");
            return (Criteria) this;
        }

        public Criteria andParcelSiteLessThan(String value) {
            addCriterion("parcel_site <", value, "parcelSite");
            return (Criteria) this;
        }

        public Criteria andParcelSiteLessThanOrEqualTo(String value) {
            addCriterion("parcel_site <=", value, "parcelSite");
            return (Criteria) this;
        }

        public Criteria andParcelSiteLike(String value) {
            addCriterion("parcel_site like", value, "parcelSite");
            return (Criteria) this;
        }

        public Criteria andParcelSiteNotLike(String value) {
            addCriterion("parcel_site not like", value, "parcelSite");
            return (Criteria) this;
        }

        public Criteria andParcelSiteIn(List<String> values) {
            addCriterion("parcel_site in", values, "parcelSite");
            return (Criteria) this;
        }

        public Criteria andParcelSiteNotIn(List<String> values) {
            addCriterion("parcel_site not in", values, "parcelSite");
            return (Criteria) this;
        }

        public Criteria andParcelSiteBetween(String value1, String value2) {
            addCriterion("parcel_site between", value1, value2, "parcelSite");
            return (Criteria) this;
        }

        public Criteria andParcelSiteNotBetween(String value1, String value2) {
            addCriterion("parcel_site not between", value1, value2, "parcelSite");
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

        public Criteria andConsultPriceMuIsNull() {
            addCriterion("consult_price_mu is null");
            return (Criteria) this;
        }

        public Criteria andConsultPriceMuIsNotNull() {
            addCriterion("consult_price_mu is not null");
            return (Criteria) this;
        }

        public Criteria andConsultPriceMuEqualTo(BigDecimal value) {
            addCriterion("consult_price_mu =", value, "consultPriceMu");
            return (Criteria) this;
        }

        public Criteria andConsultPriceMuNotEqualTo(BigDecimal value) {
            addCriterion("consult_price_mu <>", value, "consultPriceMu");
            return (Criteria) this;
        }

        public Criteria andConsultPriceMuGreaterThan(BigDecimal value) {
            addCriterion("consult_price_mu >", value, "consultPriceMu");
            return (Criteria) this;
        }

        public Criteria andConsultPriceMuGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("consult_price_mu >=", value, "consultPriceMu");
            return (Criteria) this;
        }

        public Criteria andConsultPriceMuLessThan(BigDecimal value) {
            addCriterion("consult_price_mu <", value, "consultPriceMu");
            return (Criteria) this;
        }

        public Criteria andConsultPriceMuLessThanOrEqualTo(BigDecimal value) {
            addCriterion("consult_price_mu <=", value, "consultPriceMu");
            return (Criteria) this;
        }

        public Criteria andConsultPriceMuIn(List<BigDecimal> values) {
            addCriterion("consult_price_mu in", values, "consultPriceMu");
            return (Criteria) this;
        }

        public Criteria andConsultPriceMuNotIn(List<BigDecimal> values) {
            addCriterion("consult_price_mu not in", values, "consultPriceMu");
            return (Criteria) this;
        }

        public Criteria andConsultPriceMuBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("consult_price_mu between", value1, value2, "consultPriceMu");
            return (Criteria) this;
        }

        public Criteria andConsultPriceMuNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("consult_price_mu not between", value1, value2, "consultPriceMu");
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

        public Criteria andUnitPriceMuIsNull() {
            addCriterion("unit_price_mu is null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceMuIsNotNull() {
            addCriterion("unit_price_mu is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceMuEqualTo(BigDecimal value) {
            addCriterion("unit_price_mu =", value, "unitPriceMu");
            return (Criteria) this;
        }

        public Criteria andUnitPriceMuNotEqualTo(BigDecimal value) {
            addCriterion("unit_price_mu <>", value, "unitPriceMu");
            return (Criteria) this;
        }

        public Criteria andUnitPriceMuGreaterThan(BigDecimal value) {
            addCriterion("unit_price_mu >", value, "unitPriceMu");
            return (Criteria) this;
        }

        public Criteria andUnitPriceMuGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unit_price_mu >=", value, "unitPriceMu");
            return (Criteria) this;
        }

        public Criteria andUnitPriceMuLessThan(BigDecimal value) {
            addCriterion("unit_price_mu <", value, "unitPriceMu");
            return (Criteria) this;
        }

        public Criteria andUnitPriceMuLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unit_price_mu <=", value, "unitPriceMu");
            return (Criteria) this;
        }

        public Criteria andUnitPriceMuIn(List<BigDecimal> values) {
            addCriterion("unit_price_mu in", values, "unitPriceMu");
            return (Criteria) this;
        }

        public Criteria andUnitPriceMuNotIn(List<BigDecimal> values) {
            addCriterion("unit_price_mu not in", values, "unitPriceMu");
            return (Criteria) this;
        }

        public Criteria andUnitPriceMuBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unit_price_mu between", value1, value2, "unitPriceMu");
            return (Criteria) this;
        }

        public Criteria andUnitPriceMuNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unit_price_mu not between", value1, value2, "unitPriceMu");
            return (Criteria) this;
        }

        public Criteria andFloorPriceIsNull() {
            addCriterion("floor_price is null");
            return (Criteria) this;
        }

        public Criteria andFloorPriceIsNotNull() {
            addCriterion("floor_price is not null");
            return (Criteria) this;
        }

        public Criteria andFloorPriceEqualTo(BigDecimal value) {
            addCriterion("floor_price =", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceNotEqualTo(BigDecimal value) {
            addCriterion("floor_price <>", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceGreaterThan(BigDecimal value) {
            addCriterion("floor_price >", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_price >=", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceLessThan(BigDecimal value) {
            addCriterion("floor_price <", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_price <=", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceIn(List<BigDecimal> values) {
            addCriterion("floor_price in", values, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceNotIn(List<BigDecimal> values) {
            addCriterion("floor_price not in", values, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_price between", value1, value2, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_price not between", value1, value2, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andLandRealizationRatiosIsNull() {
            addCriterion("land_realization_ratios is null");
            return (Criteria) this;
        }

        public Criteria andLandRealizationRatiosIsNotNull() {
            addCriterion("land_realization_ratios is not null");
            return (Criteria) this;
        }

        public Criteria andLandRealizationRatiosEqualTo(BigDecimal value) {
            addCriterion("land_realization_ratios =", value, "landRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andLandRealizationRatiosNotEqualTo(BigDecimal value) {
            addCriterion("land_realization_ratios <>", value, "landRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andLandRealizationRatiosGreaterThan(BigDecimal value) {
            addCriterion("land_realization_ratios >", value, "landRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andLandRealizationRatiosGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_realization_ratios >=", value, "landRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andLandRealizationRatiosLessThan(BigDecimal value) {
            addCriterion("land_realization_ratios <", value, "landRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andLandRealizationRatiosLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_realization_ratios <=", value, "landRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andLandRealizationRatiosIn(List<BigDecimal> values) {
            addCriterion("land_realization_ratios in", values, "landRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andLandRealizationRatiosNotIn(List<BigDecimal> values) {
            addCriterion("land_realization_ratios not in", values, "landRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andLandRealizationRatiosBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_realization_ratios between", value1, value2, "landRealizationRatios");
            return (Criteria) this;
        }

        public Criteria andLandRealizationRatiosNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_realization_ratios not between", value1, value2, "landRealizationRatios");
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

        public Criteria andLandAreaIsNull() {
            addCriterion("land_area is null");
            return (Criteria) this;
        }

        public Criteria andLandAreaIsNotNull() {
            addCriterion("land_area is not null");
            return (Criteria) this;
        }

        public Criteria andLandAreaEqualTo(BigDecimal value) {
            addCriterion("land_area =", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotEqualTo(BigDecimal value) {
            addCriterion("land_area <>", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaGreaterThan(BigDecimal value) {
            addCriterion("land_area >", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_area >=", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaLessThan(BigDecimal value) {
            addCriterion("land_area <", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_area <=", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaIn(List<BigDecimal> values) {
            addCriterion("land_area in", values, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotIn(List<BigDecimal> values) {
            addCriterion("land_area not in", values, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_area between", value1, value2, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_area not between", value1, value2, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaUnitIsNull() {
            addCriterion("land_area_unit is null");
            return (Criteria) this;
        }

        public Criteria andLandAreaUnitIsNotNull() {
            addCriterion("land_area_unit is not null");
            return (Criteria) this;
        }

        public Criteria andLandAreaUnitEqualTo(String value) {
            addCriterion("land_area_unit =", value, "landAreaUnit");
            return (Criteria) this;
        }

        public Criteria andLandAreaUnitNotEqualTo(String value) {
            addCriterion("land_area_unit <>", value, "landAreaUnit");
            return (Criteria) this;
        }

        public Criteria andLandAreaUnitGreaterThan(String value) {
            addCriterion("land_area_unit >", value, "landAreaUnit");
            return (Criteria) this;
        }

        public Criteria andLandAreaUnitGreaterThanOrEqualTo(String value) {
            addCriterion("land_area_unit >=", value, "landAreaUnit");
            return (Criteria) this;
        }

        public Criteria andLandAreaUnitLessThan(String value) {
            addCriterion("land_area_unit <", value, "landAreaUnit");
            return (Criteria) this;
        }

        public Criteria andLandAreaUnitLessThanOrEqualTo(String value) {
            addCriterion("land_area_unit <=", value, "landAreaUnit");
            return (Criteria) this;
        }

        public Criteria andLandAreaUnitLike(String value) {
            addCriterion("land_area_unit like", value, "landAreaUnit");
            return (Criteria) this;
        }

        public Criteria andLandAreaUnitNotLike(String value) {
            addCriterion("land_area_unit not like", value, "landAreaUnit");
            return (Criteria) this;
        }

        public Criteria andLandAreaUnitIn(List<String> values) {
            addCriterion("land_area_unit in", values, "landAreaUnit");
            return (Criteria) this;
        }

        public Criteria andLandAreaUnitNotIn(List<String> values) {
            addCriterion("land_area_unit not in", values, "landAreaUnit");
            return (Criteria) this;
        }

        public Criteria andLandAreaUnitBetween(String value1, String value2) {
            addCriterion("land_area_unit between", value1, value2, "landAreaUnit");
            return (Criteria) this;
        }

        public Criteria andLandAreaUnitNotBetween(String value1, String value2) {
            addCriterion("land_area_unit not between", value1, value2, "landAreaUnit");
            return (Criteria) this;
        }

        public Criteria andPlotRatioIsNull() {
            addCriterion("plot_ratio is null");
            return (Criteria) this;
        }

        public Criteria andPlotRatioIsNotNull() {
            addCriterion("plot_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andPlotRatioEqualTo(BigDecimal value) {
            addCriterion("plot_ratio =", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioNotEqualTo(BigDecimal value) {
            addCriterion("plot_ratio <>", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioGreaterThan(BigDecimal value) {
            addCriterion("plot_ratio >", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("plot_ratio >=", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioLessThan(BigDecimal value) {
            addCriterion("plot_ratio <", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("plot_ratio <=", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioIn(List<BigDecimal> values) {
            addCriterion("plot_ratio in", values, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioNotIn(List<BigDecimal> values) {
            addCriterion("plot_ratio not in", values, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plot_ratio between", value1, value2, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plot_ratio not between", value1, value2, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioRemarkIsNull() {
            addCriterion("plot_ratio_remark is null");
            return (Criteria) this;
        }

        public Criteria andPlotRatioRemarkIsNotNull() {
            addCriterion("plot_ratio_remark is not null");
            return (Criteria) this;
        }

        public Criteria andPlotRatioRemarkEqualTo(String value) {
            addCriterion("plot_ratio_remark =", value, "plotRatioRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioRemarkNotEqualTo(String value) {
            addCriterion("plot_ratio_remark <>", value, "plotRatioRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioRemarkGreaterThan(String value) {
            addCriterion("plot_ratio_remark >", value, "plotRatioRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("plot_ratio_remark >=", value, "plotRatioRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioRemarkLessThan(String value) {
            addCriterion("plot_ratio_remark <", value, "plotRatioRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioRemarkLessThanOrEqualTo(String value) {
            addCriterion("plot_ratio_remark <=", value, "plotRatioRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioRemarkLike(String value) {
            addCriterion("plot_ratio_remark like", value, "plotRatioRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioRemarkNotLike(String value) {
            addCriterion("plot_ratio_remark not like", value, "plotRatioRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioRemarkIn(List<String> values) {
            addCriterion("plot_ratio_remark in", values, "plotRatioRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioRemarkNotIn(List<String> values) {
            addCriterion("plot_ratio_remark not in", values, "plotRatioRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioRemarkBetween(String value1, String value2) {
            addCriterion("plot_ratio_remark between", value1, value2, "plotRatioRemark");
            return (Criteria) this;
        }

        public Criteria andPlotRatioRemarkNotBetween(String value1, String value2) {
            addCriterion("plot_ratio_remark not between", value1, value2, "plotRatioRemark");
            return (Criteria) this;
        }

        public Criteria andGreeningRateIsNull() {
            addCriterion("greening_rate is null");
            return (Criteria) this;
        }

        public Criteria andGreeningRateIsNotNull() {
            addCriterion("greening_rate is not null");
            return (Criteria) this;
        }

        public Criteria andGreeningRateEqualTo(BigDecimal value) {
            addCriterion("greening_rate =", value, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateNotEqualTo(BigDecimal value) {
            addCriterion("greening_rate <>", value, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateGreaterThan(BigDecimal value) {
            addCriterion("greening_rate >", value, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("greening_rate >=", value, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateLessThan(BigDecimal value) {
            addCriterion("greening_rate <", value, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("greening_rate <=", value, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateIn(List<BigDecimal> values) {
            addCriterion("greening_rate in", values, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateNotIn(List<BigDecimal> values) {
            addCriterion("greening_rate not in", values, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("greening_rate between", value1, value2, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("greening_rate not between", value1, value2, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateRemarkIsNull() {
            addCriterion("greening_rate_remark is null");
            return (Criteria) this;
        }

        public Criteria andGreeningRateRemarkIsNotNull() {
            addCriterion("greening_rate_remark is not null");
            return (Criteria) this;
        }

        public Criteria andGreeningRateRemarkEqualTo(String value) {
            addCriterion("greening_rate_remark =", value, "greeningRateRemark");
            return (Criteria) this;
        }

        public Criteria andGreeningRateRemarkNotEqualTo(String value) {
            addCriterion("greening_rate_remark <>", value, "greeningRateRemark");
            return (Criteria) this;
        }

        public Criteria andGreeningRateRemarkGreaterThan(String value) {
            addCriterion("greening_rate_remark >", value, "greeningRateRemark");
            return (Criteria) this;
        }

        public Criteria andGreeningRateRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("greening_rate_remark >=", value, "greeningRateRemark");
            return (Criteria) this;
        }

        public Criteria andGreeningRateRemarkLessThan(String value) {
            addCriterion("greening_rate_remark <", value, "greeningRateRemark");
            return (Criteria) this;
        }

        public Criteria andGreeningRateRemarkLessThanOrEqualTo(String value) {
            addCriterion("greening_rate_remark <=", value, "greeningRateRemark");
            return (Criteria) this;
        }

        public Criteria andGreeningRateRemarkLike(String value) {
            addCriterion("greening_rate_remark like", value, "greeningRateRemark");
            return (Criteria) this;
        }

        public Criteria andGreeningRateRemarkNotLike(String value) {
            addCriterion("greening_rate_remark not like", value, "greeningRateRemark");
            return (Criteria) this;
        }

        public Criteria andGreeningRateRemarkIn(List<String> values) {
            addCriterion("greening_rate_remark in", values, "greeningRateRemark");
            return (Criteria) this;
        }

        public Criteria andGreeningRateRemarkNotIn(List<String> values) {
            addCriterion("greening_rate_remark not in", values, "greeningRateRemark");
            return (Criteria) this;
        }

        public Criteria andGreeningRateRemarkBetween(String value1, String value2) {
            addCriterion("greening_rate_remark between", value1, value2, "greeningRateRemark");
            return (Criteria) this;
        }

        public Criteria andGreeningRateRemarkNotBetween(String value1, String value2) {
            addCriterion("greening_rate_remark not between", value1, value2, "greeningRateRemark");
            return (Criteria) this;
        }

        public Criteria andBuildDensityIsNull() {
            addCriterion("build_density is null");
            return (Criteria) this;
        }

        public Criteria andBuildDensityIsNotNull() {
            addCriterion("build_density is not null");
            return (Criteria) this;
        }

        public Criteria andBuildDensityEqualTo(String value) {
            addCriterion("build_density =", value, "buildDensity");
            return (Criteria) this;
        }

        public Criteria andBuildDensityNotEqualTo(String value) {
            addCriterion("build_density <>", value, "buildDensity");
            return (Criteria) this;
        }

        public Criteria andBuildDensityGreaterThan(String value) {
            addCriterion("build_density >", value, "buildDensity");
            return (Criteria) this;
        }

        public Criteria andBuildDensityGreaterThanOrEqualTo(String value) {
            addCriterion("build_density >=", value, "buildDensity");
            return (Criteria) this;
        }

        public Criteria andBuildDensityLessThan(String value) {
            addCriterion("build_density <", value, "buildDensity");
            return (Criteria) this;
        }

        public Criteria andBuildDensityLessThanOrEqualTo(String value) {
            addCriterion("build_density <=", value, "buildDensity");
            return (Criteria) this;
        }

        public Criteria andBuildDensityLike(String value) {
            addCriterion("build_density like", value, "buildDensity");
            return (Criteria) this;
        }

        public Criteria andBuildDensityNotLike(String value) {
            addCriterion("build_density not like", value, "buildDensity");
            return (Criteria) this;
        }

        public Criteria andBuildDensityIn(List<String> values) {
            addCriterion("build_density in", values, "buildDensity");
            return (Criteria) this;
        }

        public Criteria andBuildDensityNotIn(List<String> values) {
            addCriterion("build_density not in", values, "buildDensity");
            return (Criteria) this;
        }

        public Criteria andBuildDensityBetween(String value1, String value2) {
            addCriterion("build_density between", value1, value2, "buildDensity");
            return (Criteria) this;
        }

        public Criteria andBuildDensityNotBetween(String value1, String value2) {
            addCriterion("build_density not between", value1, value2, "buildDensity");
            return (Criteria) this;
        }

        public Criteria andBuildDensityRemarkIsNull() {
            addCriterion("build_density_remark is null");
            return (Criteria) this;
        }

        public Criteria andBuildDensityRemarkIsNotNull() {
            addCriterion("build_density_remark is not null");
            return (Criteria) this;
        }

        public Criteria andBuildDensityRemarkEqualTo(String value) {
            addCriterion("build_density_remark =", value, "buildDensityRemark");
            return (Criteria) this;
        }

        public Criteria andBuildDensityRemarkNotEqualTo(String value) {
            addCriterion("build_density_remark <>", value, "buildDensityRemark");
            return (Criteria) this;
        }

        public Criteria andBuildDensityRemarkGreaterThan(String value) {
            addCriterion("build_density_remark >", value, "buildDensityRemark");
            return (Criteria) this;
        }

        public Criteria andBuildDensityRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("build_density_remark >=", value, "buildDensityRemark");
            return (Criteria) this;
        }

        public Criteria andBuildDensityRemarkLessThan(String value) {
            addCriterion("build_density_remark <", value, "buildDensityRemark");
            return (Criteria) this;
        }

        public Criteria andBuildDensityRemarkLessThanOrEqualTo(String value) {
            addCriterion("build_density_remark <=", value, "buildDensityRemark");
            return (Criteria) this;
        }

        public Criteria andBuildDensityRemarkLike(String value) {
            addCriterion("build_density_remark like", value, "buildDensityRemark");
            return (Criteria) this;
        }

        public Criteria andBuildDensityRemarkNotLike(String value) {
            addCriterion("build_density_remark not like", value, "buildDensityRemark");
            return (Criteria) this;
        }

        public Criteria andBuildDensityRemarkIn(List<String> values) {
            addCriterion("build_density_remark in", values, "buildDensityRemark");
            return (Criteria) this;
        }

        public Criteria andBuildDensityRemarkNotIn(List<String> values) {
            addCriterion("build_density_remark not in", values, "buildDensityRemark");
            return (Criteria) this;
        }

        public Criteria andBuildDensityRemarkBetween(String value1, String value2) {
            addCriterion("build_density_remark between", value1, value2, "buildDensityRemark");
            return (Criteria) this;
        }

        public Criteria andBuildDensityRemarkNotBetween(String value1, String value2) {
            addCriterion("build_density_remark not between", value1, value2, "buildDensityRemark");
            return (Criteria) this;
        }

        public Criteria andBuildHeightIsNull() {
            addCriterion("build_height is null");
            return (Criteria) this;
        }

        public Criteria andBuildHeightIsNotNull() {
            addCriterion("build_height is not null");
            return (Criteria) this;
        }

        public Criteria andBuildHeightEqualTo(BigDecimal value) {
            addCriterion("build_height =", value, "buildHeight");
            return (Criteria) this;
        }

        public Criteria andBuildHeightNotEqualTo(BigDecimal value) {
            addCriterion("build_height <>", value, "buildHeight");
            return (Criteria) this;
        }

        public Criteria andBuildHeightGreaterThan(BigDecimal value) {
            addCriterion("build_height >", value, "buildHeight");
            return (Criteria) this;
        }

        public Criteria andBuildHeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("build_height >=", value, "buildHeight");
            return (Criteria) this;
        }

        public Criteria andBuildHeightLessThan(BigDecimal value) {
            addCriterion("build_height <", value, "buildHeight");
            return (Criteria) this;
        }

        public Criteria andBuildHeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("build_height <=", value, "buildHeight");
            return (Criteria) this;
        }

        public Criteria andBuildHeightIn(List<BigDecimal> values) {
            addCriterion("build_height in", values, "buildHeight");
            return (Criteria) this;
        }

        public Criteria andBuildHeightNotIn(List<BigDecimal> values) {
            addCriterion("build_height not in", values, "buildHeight");
            return (Criteria) this;
        }

        public Criteria andBuildHeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("build_height between", value1, value2, "buildHeight");
            return (Criteria) this;
        }

        public Criteria andBuildHeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("build_height not between", value1, value2, "buildHeight");
            return (Criteria) this;
        }

        public Criteria andBuildHeightRemarkIsNull() {
            addCriterion("build_height_remark is null");
            return (Criteria) this;
        }

        public Criteria andBuildHeightRemarkIsNotNull() {
            addCriterion("build_height_remark is not null");
            return (Criteria) this;
        }

        public Criteria andBuildHeightRemarkEqualTo(String value) {
            addCriterion("build_height_remark =", value, "buildHeightRemark");
            return (Criteria) this;
        }

        public Criteria andBuildHeightRemarkNotEqualTo(String value) {
            addCriterion("build_height_remark <>", value, "buildHeightRemark");
            return (Criteria) this;
        }

        public Criteria andBuildHeightRemarkGreaterThan(String value) {
            addCriterion("build_height_remark >", value, "buildHeightRemark");
            return (Criteria) this;
        }

        public Criteria andBuildHeightRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("build_height_remark >=", value, "buildHeightRemark");
            return (Criteria) this;
        }

        public Criteria andBuildHeightRemarkLessThan(String value) {
            addCriterion("build_height_remark <", value, "buildHeightRemark");
            return (Criteria) this;
        }

        public Criteria andBuildHeightRemarkLessThanOrEqualTo(String value) {
            addCriterion("build_height_remark <=", value, "buildHeightRemark");
            return (Criteria) this;
        }

        public Criteria andBuildHeightRemarkLike(String value) {
            addCriterion("build_height_remark like", value, "buildHeightRemark");
            return (Criteria) this;
        }

        public Criteria andBuildHeightRemarkNotLike(String value) {
            addCriterion("build_height_remark not like", value, "buildHeightRemark");
            return (Criteria) this;
        }

        public Criteria andBuildHeightRemarkIn(List<String> values) {
            addCriterion("build_height_remark in", values, "buildHeightRemark");
            return (Criteria) this;
        }

        public Criteria andBuildHeightRemarkNotIn(List<String> values) {
            addCriterion("build_height_remark not in", values, "buildHeightRemark");
            return (Criteria) this;
        }

        public Criteria andBuildHeightRemarkBetween(String value1, String value2) {
            addCriterion("build_height_remark between", value1, value2, "buildHeightRemark");
            return (Criteria) this;
        }

        public Criteria andBuildHeightRemarkNotBetween(String value1, String value2) {
            addCriterion("build_height_remark not between", value1, value2, "buildHeightRemark");
            return (Criteria) this;
        }

        public Criteria andIndexAmountIsNull() {
            addCriterion("index_amount is null");
            return (Criteria) this;
        }

        public Criteria andIndexAmountIsNotNull() {
            addCriterion("index_amount is not null");
            return (Criteria) this;
        }

        public Criteria andIndexAmountEqualTo(BigDecimal value) {
            addCriterion("index_amount =", value, "indexAmount");
            return (Criteria) this;
        }

        public Criteria andIndexAmountNotEqualTo(BigDecimal value) {
            addCriterion("index_amount <>", value, "indexAmount");
            return (Criteria) this;
        }

        public Criteria andIndexAmountGreaterThan(BigDecimal value) {
            addCriterion("index_amount >", value, "indexAmount");
            return (Criteria) this;
        }

        public Criteria andIndexAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("index_amount >=", value, "indexAmount");
            return (Criteria) this;
        }

        public Criteria andIndexAmountLessThan(BigDecimal value) {
            addCriterion("index_amount <", value, "indexAmount");
            return (Criteria) this;
        }

        public Criteria andIndexAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("index_amount <=", value, "indexAmount");
            return (Criteria) this;
        }

        public Criteria andIndexAmountIn(List<BigDecimal> values) {
            addCriterion("index_amount in", values, "indexAmount");
            return (Criteria) this;
        }

        public Criteria andIndexAmountNotIn(List<BigDecimal> values) {
            addCriterion("index_amount not in", values, "indexAmount");
            return (Criteria) this;
        }

        public Criteria andIndexAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("index_amount between", value1, value2, "indexAmount");
            return (Criteria) this;
        }

        public Criteria andIndexAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("index_amount not between", value1, value2, "indexAmount");
            return (Criteria) this;
        }

        public Criteria andIndexAmountRemarkIsNull() {
            addCriterion("index_amount_remark is null");
            return (Criteria) this;
        }

        public Criteria andIndexAmountRemarkIsNotNull() {
            addCriterion("index_amount_remark is not null");
            return (Criteria) this;
        }

        public Criteria andIndexAmountRemarkEqualTo(String value) {
            addCriterion("index_amount_remark =", value, "indexAmountRemark");
            return (Criteria) this;
        }

        public Criteria andIndexAmountRemarkNotEqualTo(String value) {
            addCriterion("index_amount_remark <>", value, "indexAmountRemark");
            return (Criteria) this;
        }

        public Criteria andIndexAmountRemarkGreaterThan(String value) {
            addCriterion("index_amount_remark >", value, "indexAmountRemark");
            return (Criteria) this;
        }

        public Criteria andIndexAmountRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("index_amount_remark >=", value, "indexAmountRemark");
            return (Criteria) this;
        }

        public Criteria andIndexAmountRemarkLessThan(String value) {
            addCriterion("index_amount_remark <", value, "indexAmountRemark");
            return (Criteria) this;
        }

        public Criteria andIndexAmountRemarkLessThanOrEqualTo(String value) {
            addCriterion("index_amount_remark <=", value, "indexAmountRemark");
            return (Criteria) this;
        }

        public Criteria andIndexAmountRemarkLike(String value) {
            addCriterion("index_amount_remark like", value, "indexAmountRemark");
            return (Criteria) this;
        }

        public Criteria andIndexAmountRemarkNotLike(String value) {
            addCriterion("index_amount_remark not like", value, "indexAmountRemark");
            return (Criteria) this;
        }

        public Criteria andIndexAmountRemarkIn(List<String> values) {
            addCriterion("index_amount_remark in", values, "indexAmountRemark");
            return (Criteria) this;
        }

        public Criteria andIndexAmountRemarkNotIn(List<String> values) {
            addCriterion("index_amount_remark not in", values, "indexAmountRemark");
            return (Criteria) this;
        }

        public Criteria andIndexAmountRemarkBetween(String value1, String value2) {
            addCriterion("index_amount_remark between", value1, value2, "indexAmountRemark");
            return (Criteria) this;
        }

        public Criteria andIndexAmountRemarkNotBetween(String value1, String value2) {
            addCriterion("index_amount_remark not between", value1, value2, "indexAmountRemark");
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

    /**
     * tb_net_info_record_land
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