package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SurveyExaminePurenessLandExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SurveyExaminePurenessLandExample() {
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

        public Criteria andPlanDetailsIdIsNull() {
            addCriterion("plan_details_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdIsNotNull() {
            addCriterion("plan_details_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdEqualTo(Integer value) {
            addCriterion("plan_details_id =", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdNotEqualTo(Integer value) {
            addCriterion("plan_details_id <>", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdGreaterThan(Integer value) {
            addCriterion("plan_details_id >", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_details_id >=", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdLessThan(Integer value) {
            addCriterion("plan_details_id <", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdLessThanOrEqualTo(Integer value) {
            addCriterion("plan_details_id <=", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdIn(List<Integer> values) {
            addCriterion("plan_details_id in", values, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdNotIn(List<Integer> values) {
            addCriterion("plan_details_id not in", values, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdBetween(Integer value1, Integer value2) {
            addCriterion("plan_details_id between", value1, value2, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_details_id not between", value1, value2, "planDetailsId");
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

        public Criteria andDeclareIdIsNull() {
            addCriterion("declare_id is null");
            return (Criteria) this;
        }

        public Criteria andDeclareIdIsNotNull() {
            addCriterion("declare_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeclareIdEqualTo(Integer value) {
            addCriterion("declare_id =", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdNotEqualTo(Integer value) {
            addCriterion("declare_id <>", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdGreaterThan(Integer value) {
            addCriterion("declare_id >", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("declare_id >=", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdLessThan(Integer value) {
            addCriterion("declare_id <", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdLessThanOrEqualTo(Integer value) {
            addCriterion("declare_id <=", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdIn(List<Integer> values) {
            addCriterion("declare_id in", values, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdNotIn(List<Integer> values) {
            addCriterion("declare_id not in", values, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdBetween(Integer value1, Integer value2) {
            addCriterion("declare_id between", value1, value2, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdNotBetween(Integer value1, Integer value2) {
            addCriterion("declare_id not between", value1, value2, "declareId");
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

        public Criteria andLandUseTypeIsNull() {
            addCriterion("land_use_type is null");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeIsNotNull() {
            addCriterion("land_use_type is not null");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeEqualTo(Integer value) {
            addCriterion("land_use_type =", value, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeNotEqualTo(Integer value) {
            addCriterion("land_use_type <>", value, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeGreaterThan(Integer value) {
            addCriterion("land_use_type >", value, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("land_use_type >=", value, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeLessThan(Integer value) {
            addCriterion("land_use_type <", value, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeLessThanOrEqualTo(Integer value) {
            addCriterion("land_use_type <=", value, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeIn(List<Integer> values) {
            addCriterion("land_use_type in", values, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeNotIn(List<Integer> values) {
            addCriterion("land_use_type not in", values, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeBetween(Integer value1, Integer value2) {
            addCriterion("land_use_type between", value1, value2, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("land_use_type not between", value1, value2, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryIsNull() {
            addCriterion("land_use_category is null");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryIsNotNull() {
            addCriterion("land_use_category is not null");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryEqualTo(Integer value) {
            addCriterion("land_use_category =", value, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryNotEqualTo(Integer value) {
            addCriterion("land_use_category <>", value, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryGreaterThan(Integer value) {
            addCriterion("land_use_category >", value, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("land_use_category >=", value, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryLessThan(Integer value) {
            addCriterion("land_use_category <", value, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryLessThanOrEqualTo(Integer value) {
            addCriterion("land_use_category <=", value, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryIn(List<Integer> values) {
            addCriterion("land_use_category in", values, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryNotIn(List<Integer> values) {
            addCriterion("land_use_category not in", values, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryBetween(Integer value1, Integer value2) {
            addCriterion("land_use_category between", value1, value2, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("land_use_category not between", value1, value2, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandLevelIsNull() {
            addCriterion("land_level is null");
            return (Criteria) this;
        }

        public Criteria andLandLevelIsNotNull() {
            addCriterion("land_level is not null");
            return (Criteria) this;
        }

        public Criteria andLandLevelEqualTo(Integer value) {
            addCriterion("land_level =", value, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandLevelNotEqualTo(Integer value) {
            addCriterion("land_level <>", value, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandLevelGreaterThan(Integer value) {
            addCriterion("land_level >", value, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("land_level >=", value, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandLevelLessThan(Integer value) {
            addCriterion("land_level <", value, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandLevelLessThanOrEqualTo(Integer value) {
            addCriterion("land_level <=", value, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandLevelIn(List<Integer> values) {
            addCriterion("land_level in", values, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandLevelNotIn(List<Integer> values) {
            addCriterion("land_level not in", values, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandLevelBetween(Integer value1, Integer value2) {
            addCriterion("land_level between", value1, value2, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("land_level not between", value1, value2, "landLevel");
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

        public Criteria andLandAreaEqualTo(String value) {
            addCriterion("land_area =", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotEqualTo(String value) {
            addCriterion("land_area <>", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaGreaterThan(String value) {
            addCriterion("land_area >", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaGreaterThanOrEqualTo(String value) {
            addCriterion("land_area >=", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaLessThan(String value) {
            addCriterion("land_area <", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaLessThanOrEqualTo(String value) {
            addCriterion("land_area <=", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaLike(String value) {
            addCriterion("land_area like", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotLike(String value) {
            addCriterion("land_area not like", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaIn(List<String> values) {
            addCriterion("land_area in", values, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotIn(List<String> values) {
            addCriterion("land_area not in", values, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaBetween(String value1, String value2) {
            addCriterion("land_area between", value1, value2, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotBetween(String value1, String value2) {
            addCriterion("land_area not between", value1, value2, "landArea");
            return (Criteria) this;
        }

        public Criteria andEastToIsNull() {
            addCriterion("east_to is null");
            return (Criteria) this;
        }

        public Criteria andEastToIsNotNull() {
            addCriterion("east_to is not null");
            return (Criteria) this;
        }

        public Criteria andEastToEqualTo(String value) {
            addCriterion("east_to =", value, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToNotEqualTo(String value) {
            addCriterion("east_to <>", value, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToGreaterThan(String value) {
            addCriterion("east_to >", value, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToGreaterThanOrEqualTo(String value) {
            addCriterion("east_to >=", value, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToLessThan(String value) {
            addCriterion("east_to <", value, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToLessThanOrEqualTo(String value) {
            addCriterion("east_to <=", value, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToLike(String value) {
            addCriterion("east_to like", value, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToNotLike(String value) {
            addCriterion("east_to not like", value, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToIn(List<String> values) {
            addCriterion("east_to in", values, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToNotIn(List<String> values) {
            addCriterion("east_to not in", values, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToBetween(String value1, String value2) {
            addCriterion("east_to between", value1, value2, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToNotBetween(String value1, String value2) {
            addCriterion("east_to not between", value1, value2, "eastTo");
            return (Criteria) this;
        }

        public Criteria andSouthToIsNull() {
            addCriterion("south_to is null");
            return (Criteria) this;
        }

        public Criteria andSouthToIsNotNull() {
            addCriterion("south_to is not null");
            return (Criteria) this;
        }

        public Criteria andSouthToEqualTo(String value) {
            addCriterion("south_to =", value, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToNotEqualTo(String value) {
            addCriterion("south_to <>", value, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToGreaterThan(String value) {
            addCriterion("south_to >", value, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToGreaterThanOrEqualTo(String value) {
            addCriterion("south_to >=", value, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToLessThan(String value) {
            addCriterion("south_to <", value, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToLessThanOrEqualTo(String value) {
            addCriterion("south_to <=", value, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToLike(String value) {
            addCriterion("south_to like", value, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToNotLike(String value) {
            addCriterion("south_to not like", value, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToIn(List<String> values) {
            addCriterion("south_to in", values, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToNotIn(List<String> values) {
            addCriterion("south_to not in", values, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToBetween(String value1, String value2) {
            addCriterion("south_to between", value1, value2, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToNotBetween(String value1, String value2) {
            addCriterion("south_to not between", value1, value2, "southTo");
            return (Criteria) this;
        }

        public Criteria andWestToIsNull() {
            addCriterion("west_to is null");
            return (Criteria) this;
        }

        public Criteria andWestToIsNotNull() {
            addCriterion("west_to is not null");
            return (Criteria) this;
        }

        public Criteria andWestToEqualTo(String value) {
            addCriterion("west_to =", value, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToNotEqualTo(String value) {
            addCriterion("west_to <>", value, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToGreaterThan(String value) {
            addCriterion("west_to >", value, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToGreaterThanOrEqualTo(String value) {
            addCriterion("west_to >=", value, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToLessThan(String value) {
            addCriterion("west_to <", value, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToLessThanOrEqualTo(String value) {
            addCriterion("west_to <=", value, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToLike(String value) {
            addCriterion("west_to like", value, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToNotLike(String value) {
            addCriterion("west_to not like", value, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToIn(List<String> values) {
            addCriterion("west_to in", values, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToNotIn(List<String> values) {
            addCriterion("west_to not in", values, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToBetween(String value1, String value2) {
            addCriterion("west_to between", value1, value2, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToNotBetween(String value1, String value2) {
            addCriterion("west_to not between", value1, value2, "westTo");
            return (Criteria) this;
        }

        public Criteria andNorthToIsNull() {
            addCriterion("north_to is null");
            return (Criteria) this;
        }

        public Criteria andNorthToIsNotNull() {
            addCriterion("north_to is not null");
            return (Criteria) this;
        }

        public Criteria andNorthToEqualTo(String value) {
            addCriterion("north_to =", value, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToNotEqualTo(String value) {
            addCriterion("north_to <>", value, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToGreaterThan(String value) {
            addCriterion("north_to >", value, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToGreaterThanOrEqualTo(String value) {
            addCriterion("north_to >=", value, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToLessThan(String value) {
            addCriterion("north_to <", value, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToLessThanOrEqualTo(String value) {
            addCriterion("north_to <=", value, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToLike(String value) {
            addCriterion("north_to like", value, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToNotLike(String value) {
            addCriterion("north_to not like", value, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToIn(List<String> values) {
            addCriterion("north_to in", values, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToNotIn(List<String> values) {
            addCriterion("north_to not in", values, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToBetween(String value1, String value2) {
            addCriterion("north_to between", value1, value2, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToNotBetween(String value1, String value2) {
            addCriterion("north_to not between", value1, value2, "northTo");
            return (Criteria) this;
        }

        public Criteria andShapeStateIsNull() {
            addCriterion("shape_state is null");
            return (Criteria) this;
        }

        public Criteria andShapeStateIsNotNull() {
            addCriterion("shape_state is not null");
            return (Criteria) this;
        }

        public Criteria andShapeStateEqualTo(Integer value) {
            addCriterion("shape_state =", value, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateNotEqualTo(Integer value) {
            addCriterion("shape_state <>", value, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateGreaterThan(Integer value) {
            addCriterion("shape_state >", value, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("shape_state >=", value, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateLessThan(Integer value) {
            addCriterion("shape_state <", value, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateLessThanOrEqualTo(Integer value) {
            addCriterion("shape_state <=", value, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateIn(List<Integer> values) {
            addCriterion("shape_state in", values, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateNotIn(List<Integer> values) {
            addCriterion("shape_state not in", values, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateBetween(Integer value1, Integer value2) {
            addCriterion("shape_state between", value1, value2, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateNotBetween(Integer value1, Integer value2) {
            addCriterion("shape_state not between", value1, value2, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateRemarkIsNull() {
            addCriterion("shape_state_remark is null");
            return (Criteria) this;
        }

        public Criteria andShapeStateRemarkIsNotNull() {
            addCriterion("shape_state_remark is not null");
            return (Criteria) this;
        }

        public Criteria andShapeStateRemarkEqualTo(String value) {
            addCriterion("shape_state_remark =", value, "shapeStateRemark");
            return (Criteria) this;
        }

        public Criteria andShapeStateRemarkNotEqualTo(String value) {
            addCriterion("shape_state_remark <>", value, "shapeStateRemark");
            return (Criteria) this;
        }

        public Criteria andShapeStateRemarkGreaterThan(String value) {
            addCriterion("shape_state_remark >", value, "shapeStateRemark");
            return (Criteria) this;
        }

        public Criteria andShapeStateRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("shape_state_remark >=", value, "shapeStateRemark");
            return (Criteria) this;
        }

        public Criteria andShapeStateRemarkLessThan(String value) {
            addCriterion("shape_state_remark <", value, "shapeStateRemark");
            return (Criteria) this;
        }

        public Criteria andShapeStateRemarkLessThanOrEqualTo(String value) {
            addCriterion("shape_state_remark <=", value, "shapeStateRemark");
            return (Criteria) this;
        }

        public Criteria andShapeStateRemarkLike(String value) {
            addCriterion("shape_state_remark like", value, "shapeStateRemark");
            return (Criteria) this;
        }

        public Criteria andShapeStateRemarkNotLike(String value) {
            addCriterion("shape_state_remark not like", value, "shapeStateRemark");
            return (Criteria) this;
        }

        public Criteria andShapeStateRemarkIn(List<String> values) {
            addCriterion("shape_state_remark in", values, "shapeStateRemark");
            return (Criteria) this;
        }

        public Criteria andShapeStateRemarkNotIn(List<String> values) {
            addCriterion("shape_state_remark not in", values, "shapeStateRemark");
            return (Criteria) this;
        }

        public Criteria andShapeStateRemarkBetween(String value1, String value2) {
            addCriterion("shape_state_remark between", value1, value2, "shapeStateRemark");
            return (Criteria) this;
        }

        public Criteria andShapeStateRemarkNotBetween(String value1, String value2) {
            addCriterion("shape_state_remark not between", value1, value2, "shapeStateRemark");
            return (Criteria) this;
        }

        public Criteria andPlanenessIsNull() {
            addCriterion("planeness is null");
            return (Criteria) this;
        }

        public Criteria andPlanenessIsNotNull() {
            addCriterion("planeness is not null");
            return (Criteria) this;
        }

        public Criteria andPlanenessEqualTo(Integer value) {
            addCriterion("planeness =", value, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessNotEqualTo(Integer value) {
            addCriterion("planeness <>", value, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessGreaterThan(Integer value) {
            addCriterion("planeness >", value, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessGreaterThanOrEqualTo(Integer value) {
            addCriterion("planeness >=", value, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessLessThan(Integer value) {
            addCriterion("planeness <", value, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessLessThanOrEqualTo(Integer value) {
            addCriterion("planeness <=", value, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessIn(List<Integer> values) {
            addCriterion("planeness in", values, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessNotIn(List<Integer> values) {
            addCriterion("planeness not in", values, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessBetween(Integer value1, Integer value2) {
            addCriterion("planeness between", value1, value2, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessNotBetween(Integer value1, Integer value2) {
            addCriterion("planeness not between", value1, value2, "planeness");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainIsNull() {
            addCriterion("topographic_terrain is null");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainIsNotNull() {
            addCriterion("topographic_terrain is not null");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainEqualTo(Integer value) {
            addCriterion("topographic_terrain =", value, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainNotEqualTo(Integer value) {
            addCriterion("topographic_terrain <>", value, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainGreaterThan(Integer value) {
            addCriterion("topographic_terrain >", value, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainGreaterThanOrEqualTo(Integer value) {
            addCriterion("topographic_terrain >=", value, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainLessThan(Integer value) {
            addCriterion("topographic_terrain <", value, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainLessThanOrEqualTo(Integer value) {
            addCriterion("topographic_terrain <=", value, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainIn(List<Integer> values) {
            addCriterion("topographic_terrain in", values, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainNotIn(List<Integer> values) {
            addCriterion("topographic_terrain not in", values, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainBetween(Integer value1, Integer value2) {
            addCriterion("topographic_terrain between", value1, value2, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainNotBetween(Integer value1, Integer value2) {
            addCriterion("topographic_terrain not between", value1, value2, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeIsNull() {
            addCriterion("development_degree is null");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeIsNotNull() {
            addCriterion("development_degree is not null");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeEqualTo(Integer value) {
            addCriterion("development_degree =", value, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeNotEqualTo(Integer value) {
            addCriterion("development_degree <>", value, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeGreaterThan(Integer value) {
            addCriterion("development_degree >", value, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeGreaterThanOrEqualTo(Integer value) {
            addCriterion("development_degree >=", value, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeLessThan(Integer value) {
            addCriterion("development_degree <", value, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeLessThanOrEqualTo(Integer value) {
            addCriterion("development_degree <=", value, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeIn(List<Integer> values) {
            addCriterion("development_degree in", values, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeNotIn(List<Integer> values) {
            addCriterion("development_degree not in", values, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeBetween(Integer value1, Integer value2) {
            addCriterion("development_degree between", value1, value2, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeNotBetween(Integer value1, Integer value2) {
            addCriterion("development_degree not between", value1, value2, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRemarkIsNull() {
            addCriterion("development_degree_remark is null");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRemarkIsNotNull() {
            addCriterion("development_degree_remark is not null");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRemarkEqualTo(String value) {
            addCriterion("development_degree_remark =", value, "developmentDegreeRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRemarkNotEqualTo(String value) {
            addCriterion("development_degree_remark <>", value, "developmentDegreeRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRemarkGreaterThan(String value) {
            addCriterion("development_degree_remark >", value, "developmentDegreeRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("development_degree_remark >=", value, "developmentDegreeRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRemarkLessThan(String value) {
            addCriterion("development_degree_remark <", value, "developmentDegreeRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRemarkLessThanOrEqualTo(String value) {
            addCriterion("development_degree_remark <=", value, "developmentDegreeRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRemarkLike(String value) {
            addCriterion("development_degree_remark like", value, "developmentDegreeRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRemarkNotLike(String value) {
            addCriterion("development_degree_remark not like", value, "developmentDegreeRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRemarkIn(List<String> values) {
            addCriterion("development_degree_remark in", values, "developmentDegreeRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRemarkNotIn(List<String> values) {
            addCriterion("development_degree_remark not in", values, "developmentDegreeRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRemarkBetween(String value1, String value2) {
            addCriterion("development_degree_remark between", value1, value2, "developmentDegreeRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRemarkNotBetween(String value1, String value2) {
            addCriterion("development_degree_remark not between", value1, value2, "developmentDegreeRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeContentIsNull() {
            addCriterion("development_degree_content is null");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeContentIsNotNull() {
            addCriterion("development_degree_content is not null");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeContentEqualTo(String value) {
            addCriterion("development_degree_content =", value, "developmentDegreeContent");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeContentNotEqualTo(String value) {
            addCriterion("development_degree_content <>", value, "developmentDegreeContent");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeContentGreaterThan(String value) {
            addCriterion("development_degree_content >", value, "developmentDegreeContent");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeContentGreaterThanOrEqualTo(String value) {
            addCriterion("development_degree_content >=", value, "developmentDegreeContent");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeContentLessThan(String value) {
            addCriterion("development_degree_content <", value, "developmentDegreeContent");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeContentLessThanOrEqualTo(String value) {
            addCriterion("development_degree_content <=", value, "developmentDegreeContent");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeContentLike(String value) {
            addCriterion("development_degree_content like", value, "developmentDegreeContent");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeContentNotLike(String value) {
            addCriterion("development_degree_content not like", value, "developmentDegreeContent");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeContentIn(List<String> values) {
            addCriterion("development_degree_content in", values, "developmentDegreeContent");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeContentNotIn(List<String> values) {
            addCriterion("development_degree_content not in", values, "developmentDegreeContent");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeContentBetween(String value1, String value2) {
            addCriterion("development_degree_content between", value1, value2, "developmentDegreeContent");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeContentNotBetween(String value1, String value2) {
            addCriterion("development_degree_content not between", value1, value2, "developmentDegreeContent");
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

        public Criteria andPlotRatioEqualTo(String value) {
            addCriterion("plot_ratio =", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioNotEqualTo(String value) {
            addCriterion("plot_ratio <>", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioGreaterThan(String value) {
            addCriterion("plot_ratio >", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioGreaterThanOrEqualTo(String value) {
            addCriterion("plot_ratio >=", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioLessThan(String value) {
            addCriterion("plot_ratio <", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioLessThanOrEqualTo(String value) {
            addCriterion("plot_ratio <=", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioLike(String value) {
            addCriterion("plot_ratio like", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioNotLike(String value) {
            addCriterion("plot_ratio not like", value, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioIn(List<String> values) {
            addCriterion("plot_ratio in", values, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioNotIn(List<String> values) {
            addCriterion("plot_ratio not in", values, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioBetween(String value1, String value2) {
            addCriterion("plot_ratio between", value1, value2, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andPlotRatioNotBetween(String value1, String value2) {
            addCriterion("plot_ratio not between", value1, value2, "plotRatio");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityIsNull() {
            addCriterion("building_density is null");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityIsNotNull() {
            addCriterion("building_density is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityEqualTo(String value) {
            addCriterion("building_density =", value, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityNotEqualTo(String value) {
            addCriterion("building_density <>", value, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityGreaterThan(String value) {
            addCriterion("building_density >", value, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityGreaterThanOrEqualTo(String value) {
            addCriterion("building_density >=", value, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityLessThan(String value) {
            addCriterion("building_density <", value, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityLessThanOrEqualTo(String value) {
            addCriterion("building_density <=", value, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityLike(String value) {
            addCriterion("building_density like", value, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityNotLike(String value) {
            addCriterion("building_density not like", value, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityIn(List<String> values) {
            addCriterion("building_density in", values, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityNotIn(List<String> values) {
            addCriterion("building_density not in", values, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityBetween(String value1, String value2) {
            addCriterion("building_density between", value1, value2, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityNotBetween(String value1, String value2) {
            addCriterion("building_density not between", value1, value2, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateIsNull() {
            addCriterion("green_space_rate is null");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateIsNotNull() {
            addCriterion("green_space_rate is not null");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateEqualTo(String value) {
            addCriterion("green_space_rate =", value, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateNotEqualTo(String value) {
            addCriterion("green_space_rate <>", value, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateGreaterThan(String value) {
            addCriterion("green_space_rate >", value, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateGreaterThanOrEqualTo(String value) {
            addCriterion("green_space_rate >=", value, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateLessThan(String value) {
            addCriterion("green_space_rate <", value, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateLessThanOrEqualTo(String value) {
            addCriterion("green_space_rate <=", value, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateLike(String value) {
            addCriterion("green_space_rate like", value, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateNotLike(String value) {
            addCriterion("green_space_rate not like", value, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateIn(List<String> values) {
            addCriterion("green_space_rate in", values, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateNotIn(List<String> values) {
            addCriterion("green_space_rate not in", values, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateBetween(String value1, String value2) {
            addCriterion("green_space_rate between", value1, value2, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateNotBetween(String value1, String value2) {
            addCriterion("green_space_rate not between", value1, value2, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioIsNull() {
            addCriterion("compatible_ratio is null");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioIsNotNull() {
            addCriterion("compatible_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioEqualTo(String value) {
            addCriterion("compatible_ratio =", value, "compatibleRatio");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioNotEqualTo(String value) {
            addCriterion("compatible_ratio <>", value, "compatibleRatio");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioGreaterThan(String value) {
            addCriterion("compatible_ratio >", value, "compatibleRatio");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioGreaterThanOrEqualTo(String value) {
            addCriterion("compatible_ratio >=", value, "compatibleRatio");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioLessThan(String value) {
            addCriterion("compatible_ratio <", value, "compatibleRatio");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioLessThanOrEqualTo(String value) {
            addCriterion("compatible_ratio <=", value, "compatibleRatio");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioLike(String value) {
            addCriterion("compatible_ratio like", value, "compatibleRatio");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioNotLike(String value) {
            addCriterion("compatible_ratio not like", value, "compatibleRatio");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioIn(List<String> values) {
            addCriterion("compatible_ratio in", values, "compatibleRatio");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioNotIn(List<String> values) {
            addCriterion("compatible_ratio not in", values, "compatibleRatio");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioBetween(String value1, String value2) {
            addCriterion("compatible_ratio between", value1, value2, "compatibleRatio");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioNotBetween(String value1, String value2) {
            addCriterion("compatible_ratio not between", value1, value2, "compatibleRatio");
            return (Criteria) this;
        }

        public Criteria andBearingCapacityIsNull() {
            addCriterion("bearing_capacity is null");
            return (Criteria) this;
        }

        public Criteria andBearingCapacityIsNotNull() {
            addCriterion("bearing_capacity is not null");
            return (Criteria) this;
        }

        public Criteria andBearingCapacityEqualTo(String value) {
            addCriterion("bearing_capacity =", value, "bearingCapacity");
            return (Criteria) this;
        }

        public Criteria andBearingCapacityNotEqualTo(String value) {
            addCriterion("bearing_capacity <>", value, "bearingCapacity");
            return (Criteria) this;
        }

        public Criteria andBearingCapacityGreaterThan(String value) {
            addCriterion("bearing_capacity >", value, "bearingCapacity");
            return (Criteria) this;
        }

        public Criteria andBearingCapacityGreaterThanOrEqualTo(String value) {
            addCriterion("bearing_capacity >=", value, "bearingCapacity");
            return (Criteria) this;
        }

        public Criteria andBearingCapacityLessThan(String value) {
            addCriterion("bearing_capacity <", value, "bearingCapacity");
            return (Criteria) this;
        }

        public Criteria andBearingCapacityLessThanOrEqualTo(String value) {
            addCriterion("bearing_capacity <=", value, "bearingCapacity");
            return (Criteria) this;
        }

        public Criteria andBearingCapacityLike(String value) {
            addCriterion("bearing_capacity like", value, "bearingCapacity");
            return (Criteria) this;
        }

        public Criteria andBearingCapacityNotLike(String value) {
            addCriterion("bearing_capacity not like", value, "bearingCapacity");
            return (Criteria) this;
        }

        public Criteria andBearingCapacityIn(List<String> values) {
            addCriterion("bearing_capacity in", values, "bearingCapacity");
            return (Criteria) this;
        }

        public Criteria andBearingCapacityNotIn(List<String> values) {
            addCriterion("bearing_capacity not in", values, "bearingCapacity");
            return (Criteria) this;
        }

        public Criteria andBearingCapacityBetween(String value1, String value2) {
            addCriterion("bearing_capacity between", value1, value2, "bearingCapacity");
            return (Criteria) this;
        }

        public Criteria andBearingCapacityNotBetween(String value1, String value2) {
            addCriterion("bearing_capacity not between", value1, value2, "bearingCapacity");
            return (Criteria) this;
        }

        public Criteria andContaminatedIsNull() {
            addCriterion("contaminated is null");
            return (Criteria) this;
        }

        public Criteria andContaminatedIsNotNull() {
            addCriterion("contaminated is not null");
            return (Criteria) this;
        }

        public Criteria andContaminatedEqualTo(String value) {
            addCriterion("contaminated =", value, "contaminated");
            return (Criteria) this;
        }

        public Criteria andContaminatedNotEqualTo(String value) {
            addCriterion("contaminated <>", value, "contaminated");
            return (Criteria) this;
        }

        public Criteria andContaminatedGreaterThan(String value) {
            addCriterion("contaminated >", value, "contaminated");
            return (Criteria) this;
        }

        public Criteria andContaminatedGreaterThanOrEqualTo(String value) {
            addCriterion("contaminated >=", value, "contaminated");
            return (Criteria) this;
        }

        public Criteria andContaminatedLessThan(String value) {
            addCriterion("contaminated <", value, "contaminated");
            return (Criteria) this;
        }

        public Criteria andContaminatedLessThanOrEqualTo(String value) {
            addCriterion("contaminated <=", value, "contaminated");
            return (Criteria) this;
        }

        public Criteria andContaminatedLike(String value) {
            addCriterion("contaminated like", value, "contaminated");
            return (Criteria) this;
        }

        public Criteria andContaminatedNotLike(String value) {
            addCriterion("contaminated not like", value, "contaminated");
            return (Criteria) this;
        }

        public Criteria andContaminatedIn(List<String> values) {
            addCriterion("contaminated in", values, "contaminated");
            return (Criteria) this;
        }

        public Criteria andContaminatedNotIn(List<String> values) {
            addCriterion("contaminated not in", values, "contaminated");
            return (Criteria) this;
        }

        public Criteria andContaminatedBetween(String value1, String value2) {
            addCriterion("contaminated between", value1, value2, "contaminated");
            return (Criteria) this;
        }

        public Criteria andContaminatedNotBetween(String value1, String value2) {
            addCriterion("contaminated not between", value1, value2, "contaminated");
            return (Criteria) this;
        }

        public Criteria andPhIsNull() {
            addCriterion("ph is null");
            return (Criteria) this;
        }

        public Criteria andPhIsNotNull() {
            addCriterion("ph is not null");
            return (Criteria) this;
        }

        public Criteria andPhEqualTo(String value) {
            addCriterion("ph =", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhNotEqualTo(String value) {
            addCriterion("ph <>", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhGreaterThan(String value) {
            addCriterion("ph >", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhGreaterThanOrEqualTo(String value) {
            addCriterion("ph >=", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhLessThan(String value) {
            addCriterion("ph <", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhLessThanOrEqualTo(String value) {
            addCriterion("ph <=", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhLike(String value) {
            addCriterion("ph like", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhNotLike(String value) {
            addCriterion("ph not like", value, "ph");
            return (Criteria) this;
        }

        public Criteria andPhIn(List<String> values) {
            addCriterion("ph in", values, "ph");
            return (Criteria) this;
        }

        public Criteria andPhNotIn(List<String> values) {
            addCriterion("ph not in", values, "ph");
            return (Criteria) this;
        }

        public Criteria andPhBetween(String value1, String value2) {
            addCriterion("ph between", value1, value2, "ph");
            return (Criteria) this;
        }

        public Criteria andPhNotBetween(String value1, String value2) {
            addCriterion("ph not between", value1, value2, "ph");
            return (Criteria) this;
        }

        public Criteria andFertilityIsNull() {
            addCriterion("fertility is null");
            return (Criteria) this;
        }

        public Criteria andFertilityIsNotNull() {
            addCriterion("fertility is not null");
            return (Criteria) this;
        }

        public Criteria andFertilityEqualTo(String value) {
            addCriterion("fertility =", value, "fertility");
            return (Criteria) this;
        }

        public Criteria andFertilityNotEqualTo(String value) {
            addCriterion("fertility <>", value, "fertility");
            return (Criteria) this;
        }

        public Criteria andFertilityGreaterThan(String value) {
            addCriterion("fertility >", value, "fertility");
            return (Criteria) this;
        }

        public Criteria andFertilityGreaterThanOrEqualTo(String value) {
            addCriterion("fertility >=", value, "fertility");
            return (Criteria) this;
        }

        public Criteria andFertilityLessThan(String value) {
            addCriterion("fertility <", value, "fertility");
            return (Criteria) this;
        }

        public Criteria andFertilityLessThanOrEqualTo(String value) {
            addCriterion("fertility <=", value, "fertility");
            return (Criteria) this;
        }

        public Criteria andFertilityLike(String value) {
            addCriterion("fertility like", value, "fertility");
            return (Criteria) this;
        }

        public Criteria andFertilityNotLike(String value) {
            addCriterion("fertility not like", value, "fertility");
            return (Criteria) this;
        }

        public Criteria andFertilityIn(List<String> values) {
            addCriterion("fertility in", values, "fertility");
            return (Criteria) this;
        }

        public Criteria andFertilityNotIn(List<String> values) {
            addCriterion("fertility not in", values, "fertility");
            return (Criteria) this;
        }

        public Criteria andFertilityBetween(String value1, String value2) {
            addCriterion("fertility between", value1, value2, "fertility");
            return (Criteria) this;
        }

        public Criteria andFertilityNotBetween(String value1, String value2) {
            addCriterion("fertility not between", value1, value2, "fertility");
            return (Criteria) this;
        }

        public Criteria andConclusionIsNull() {
            addCriterion("conclusion is null");
            return (Criteria) this;
        }

        public Criteria andConclusionIsNotNull() {
            addCriterion("conclusion is not null");
            return (Criteria) this;
        }

        public Criteria andConclusionEqualTo(String value) {
            addCriterion("conclusion =", value, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionNotEqualTo(String value) {
            addCriterion("conclusion <>", value, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionGreaterThan(String value) {
            addCriterion("conclusion >", value, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionGreaterThanOrEqualTo(String value) {
            addCriterion("conclusion >=", value, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionLessThan(String value) {
            addCriterion("conclusion <", value, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionLessThanOrEqualTo(String value) {
            addCriterion("conclusion <=", value, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionLike(String value) {
            addCriterion("conclusion like", value, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionNotLike(String value) {
            addCriterion("conclusion not like", value, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionIn(List<String> values) {
            addCriterion("conclusion in", values, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionNotIn(List<String> values) {
            addCriterion("conclusion not in", values, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionBetween(String value1, String value2) {
            addCriterion("conclusion between", value1, value2, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionNotBetween(String value1, String value2) {
            addCriterion("conclusion not between", value1, value2, "conclusion");
            return (Criteria) this;
        }

        public Criteria andHoldOnIsNull() {
            addCriterion("hold_on is null");
            return (Criteria) this;
        }

        public Criteria andHoldOnIsNotNull() {
            addCriterion("hold_on is not null");
            return (Criteria) this;
        }

        public Criteria andHoldOnEqualTo(String value) {
            addCriterion("hold_on =", value, "holdOn");
            return (Criteria) this;
        }

        public Criteria andHoldOnNotEqualTo(String value) {
            addCriterion("hold_on <>", value, "holdOn");
            return (Criteria) this;
        }

        public Criteria andHoldOnGreaterThan(String value) {
            addCriterion("hold_on >", value, "holdOn");
            return (Criteria) this;
        }

        public Criteria andHoldOnGreaterThanOrEqualTo(String value) {
            addCriterion("hold_on >=", value, "holdOn");
            return (Criteria) this;
        }

        public Criteria andHoldOnLessThan(String value) {
            addCriterion("hold_on <", value, "holdOn");
            return (Criteria) this;
        }

        public Criteria andHoldOnLessThanOrEqualTo(String value) {
            addCriterion("hold_on <=", value, "holdOn");
            return (Criteria) this;
        }

        public Criteria andHoldOnLike(String value) {
            addCriterion("hold_on like", value, "holdOn");
            return (Criteria) this;
        }

        public Criteria andHoldOnNotLike(String value) {
            addCriterion("hold_on not like", value, "holdOn");
            return (Criteria) this;
        }

        public Criteria andHoldOnIn(List<String> values) {
            addCriterion("hold_on in", values, "holdOn");
            return (Criteria) this;
        }

        public Criteria andHoldOnNotIn(List<String> values) {
            addCriterion("hold_on not in", values, "holdOn");
            return (Criteria) this;
        }

        public Criteria andHoldOnBetween(String value1, String value2) {
            addCriterion("hold_on between", value1, value2, "holdOn");
            return (Criteria) this;
        }

        public Criteria andHoldOnNotBetween(String value1, String value2) {
            addCriterion("hold_on not between", value1, value2, "holdOn");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessIsNull() {
            addCriterion("infrastructure_completeness is null");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessIsNotNull() {
            addCriterion("infrastructure_completeness is not null");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessEqualTo(Integer value) {
            addCriterion("infrastructure_completeness =", value, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessNotEqualTo(Integer value) {
            addCriterion("infrastructure_completeness <>", value, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessGreaterThan(Integer value) {
            addCriterion("infrastructure_completeness >", value, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessGreaterThanOrEqualTo(Integer value) {
            addCriterion("infrastructure_completeness >=", value, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessLessThan(Integer value) {
            addCriterion("infrastructure_completeness <", value, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessLessThanOrEqualTo(Integer value) {
            addCriterion("infrastructure_completeness <=", value, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessIn(List<Integer> values) {
            addCriterion("infrastructure_completeness in", values, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessNotIn(List<Integer> values) {
            addCriterion("infrastructure_completeness not in", values, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessBetween(Integer value1, Integer value2) {
            addCriterion("infrastructure_completeness between", value1, value2, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessNotBetween(Integer value1, Integer value2) {
            addCriterion("infrastructure_completeness not between", value1, value2, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingIsNull() {
            addCriterion("supply_heating is null");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingIsNotNull() {
            addCriterion("supply_heating is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingEqualTo(Integer value) {
            addCriterion("supply_heating =", value, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingNotEqualTo(Integer value) {
            addCriterion("supply_heating <>", value, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingGreaterThan(Integer value) {
            addCriterion("supply_heating >", value, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingGreaterThanOrEqualTo(Integer value) {
            addCriterion("supply_heating >=", value, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingLessThan(Integer value) {
            addCriterion("supply_heating <", value, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingLessThanOrEqualTo(Integer value) {
            addCriterion("supply_heating <=", value, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingIn(List<Integer> values) {
            addCriterion("supply_heating in", values, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingNotIn(List<Integer> values) {
            addCriterion("supply_heating not in", values, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingBetween(Integer value1, Integer value2) {
            addCriterion("supply_heating between", value1, value2, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingNotBetween(Integer value1, Integer value2) {
            addCriterion("supply_heating not between", value1, value2, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerIsNull() {
            addCriterion("supply_power is null");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerIsNotNull() {
            addCriterion("supply_power is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerEqualTo(Integer value) {
            addCriterion("supply_power =", value, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerNotEqualTo(Integer value) {
            addCriterion("supply_power <>", value, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerGreaterThan(Integer value) {
            addCriterion("supply_power >", value, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerGreaterThanOrEqualTo(Integer value) {
            addCriterion("supply_power >=", value, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerLessThan(Integer value) {
            addCriterion("supply_power <", value, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerLessThanOrEqualTo(Integer value) {
            addCriterion("supply_power <=", value, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerIn(List<Integer> values) {
            addCriterion("supply_power in", values, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerNotIn(List<Integer> values) {
            addCriterion("supply_power not in", values, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerBetween(Integer value1, Integer value2) {
            addCriterion("supply_power between", value1, value2, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerNotBetween(Integer value1, Integer value2) {
            addCriterion("supply_power not between", value1, value2, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterIsNull() {
            addCriterion("supply_water is null");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterIsNotNull() {
            addCriterion("supply_water is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterEqualTo(Integer value) {
            addCriterion("supply_water =", value, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterNotEqualTo(Integer value) {
            addCriterion("supply_water <>", value, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterGreaterThan(Integer value) {
            addCriterion("supply_water >", value, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterGreaterThanOrEqualTo(Integer value) {
            addCriterion("supply_water >=", value, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterLessThan(Integer value) {
            addCriterion("supply_water <", value, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterLessThanOrEqualTo(Integer value) {
            addCriterion("supply_water <=", value, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterIn(List<Integer> values) {
            addCriterion("supply_water in", values, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterNotIn(List<Integer> values) {
            addCriterion("supply_water not in", values, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterBetween(Integer value1, Integer value2) {
            addCriterion("supply_water between", value1, value2, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterNotBetween(Integer value1, Integer value2) {
            addCriterion("supply_water not between", value1, value2, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterIsNull() {
            addCriterion("drain_water is null");
            return (Criteria) this;
        }

        public Criteria andDrainWaterIsNotNull() {
            addCriterion("drain_water is not null");
            return (Criteria) this;
        }

        public Criteria andDrainWaterEqualTo(Integer value) {
            addCriterion("drain_water =", value, "drainWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterNotEqualTo(Integer value) {
            addCriterion("drain_water <>", value, "drainWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterGreaterThan(Integer value) {
            addCriterion("drain_water >", value, "drainWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterGreaterThanOrEqualTo(Integer value) {
            addCriterion("drain_water >=", value, "drainWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterLessThan(Integer value) {
            addCriterion("drain_water <", value, "drainWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterLessThanOrEqualTo(Integer value) {
            addCriterion("drain_water <=", value, "drainWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterIn(List<Integer> values) {
            addCriterion("drain_water in", values, "drainWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterNotIn(List<Integer> values) {
            addCriterion("drain_water not in", values, "drainWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterBetween(Integer value1, Integer value2) {
            addCriterion("drain_water between", value1, value2, "drainWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterNotBetween(Integer value1, Integer value2) {
            addCriterion("drain_water not between", value1, value2, "drainWater");
            return (Criteria) this;
        }

        public Criteria andSupplyGasIsNull() {
            addCriterion("supply_gas is null");
            return (Criteria) this;
        }

        public Criteria andSupplyGasIsNotNull() {
            addCriterion("supply_gas is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyGasEqualTo(Integer value) {
            addCriterion("supply_gas =", value, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andSupplyGasNotEqualTo(Integer value) {
            addCriterion("supply_gas <>", value, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andSupplyGasGreaterThan(Integer value) {
            addCriterion("supply_gas >", value, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andSupplyGasGreaterThanOrEqualTo(Integer value) {
            addCriterion("supply_gas >=", value, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andSupplyGasLessThan(Integer value) {
            addCriterion("supply_gas <", value, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andSupplyGasLessThanOrEqualTo(Integer value) {
            addCriterion("supply_gas <=", value, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andSupplyGasIn(List<Integer> values) {
            addCriterion("supply_gas in", values, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andSupplyGasNotIn(List<Integer> values) {
            addCriterion("supply_gas not in", values, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andSupplyGasBetween(Integer value1, Integer value2) {
            addCriterion("supply_gas between", value1, value2, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andSupplyGasNotBetween(Integer value1, Integer value2) {
            addCriterion("supply_gas not between", value1, value2, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andTradingTimeIsNull() {
            addCriterion("trading_time is null");
            return (Criteria) this;
        }

        public Criteria andTradingTimeIsNotNull() {
            addCriterion("trading_time is not null");
            return (Criteria) this;
        }

        public Criteria andTradingTimeEqualTo(Date value) {
            addCriterion("trading_time =", value, "tradingTime");
            return (Criteria) this;
        }

        public Criteria andTradingTimeNotEqualTo(Date value) {
            addCriterion("trading_time <>", value, "tradingTime");
            return (Criteria) this;
        }

        public Criteria andTradingTimeGreaterThan(Date value) {
            addCriterion("trading_time >", value, "tradingTime");
            return (Criteria) this;
        }

        public Criteria andTradingTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("trading_time >=", value, "tradingTime");
            return (Criteria) this;
        }

        public Criteria andTradingTimeLessThan(Date value) {
            addCriterion("trading_time <", value, "tradingTime");
            return (Criteria) this;
        }

        public Criteria andTradingTimeLessThanOrEqualTo(Date value) {
            addCriterion("trading_time <=", value, "tradingTime");
            return (Criteria) this;
        }

        public Criteria andTradingTimeIn(List<Date> values) {
            addCriterion("trading_time in", values, "tradingTime");
            return (Criteria) this;
        }

        public Criteria andTradingTimeNotIn(List<Date> values) {
            addCriterion("trading_time not in", values, "tradingTime");
            return (Criteria) this;
        }

        public Criteria andTradingTimeBetween(Date value1, Date value2) {
            addCriterion("trading_time between", value1, value2, "tradingTime");
            return (Criteria) this;
        }

        public Criteria andTradingTimeNotBetween(Date value1, Date value2) {
            addCriterion("trading_time not between", value1, value2, "tradingTime");
            return (Criteria) this;
        }

        public Criteria andTradingTotalPriceIsNull() {
            addCriterion("trading_total_price is null");
            return (Criteria) this;
        }

        public Criteria andTradingTotalPriceIsNotNull() {
            addCriterion("trading_total_price is not null");
            return (Criteria) this;
        }

        public Criteria andTradingTotalPriceEqualTo(BigDecimal value) {
            addCriterion("trading_total_price =", value, "tradingTotalPrice");
            return (Criteria) this;
        }

        public Criteria andTradingTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("trading_total_price <>", value, "tradingTotalPrice");
            return (Criteria) this;
        }

        public Criteria andTradingTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("trading_total_price >", value, "tradingTotalPrice");
            return (Criteria) this;
        }

        public Criteria andTradingTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trading_total_price >=", value, "tradingTotalPrice");
            return (Criteria) this;
        }

        public Criteria andTradingTotalPriceLessThan(BigDecimal value) {
            addCriterion("trading_total_price <", value, "tradingTotalPrice");
            return (Criteria) this;
        }

        public Criteria andTradingTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trading_total_price <=", value, "tradingTotalPrice");
            return (Criteria) this;
        }

        public Criteria andTradingTotalPriceIn(List<BigDecimal> values) {
            addCriterion("trading_total_price in", values, "tradingTotalPrice");
            return (Criteria) this;
        }

        public Criteria andTradingTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("trading_total_price not in", values, "tradingTotalPrice");
            return (Criteria) this;
        }

        public Criteria andTradingTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trading_total_price between", value1, value2, "tradingTotalPrice");
            return (Criteria) this;
        }

        public Criteria andTradingTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trading_total_price not between", value1, value2, "tradingTotalPrice");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationIsNull() {
            addCriterion("price_connotation is null");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationIsNotNull() {
            addCriterion("price_connotation is not null");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationEqualTo(Integer value) {
            addCriterion("price_connotation =", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationNotEqualTo(Integer value) {
            addCriterion("price_connotation <>", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationGreaterThan(Integer value) {
            addCriterion("price_connotation >", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationGreaterThanOrEqualTo(Integer value) {
            addCriterion("price_connotation >=", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationLessThan(Integer value) {
            addCriterion("price_connotation <", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationLessThanOrEqualTo(Integer value) {
            addCriterion("price_connotation <=", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationIn(List<Integer> values) {
            addCriterion("price_connotation in", values, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationNotIn(List<Integer> values) {
            addCriterion("price_connotation not in", values, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationBetween(Integer value1, Integer value2) {
            addCriterion("price_connotation between", value1, value2, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationNotBetween(Integer value1, Integer value2) {
            addCriterion("price_connotation not between", value1, value2, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andTradingUnitPriceIsNull() {
            addCriterion("trading_unit_price is null");
            return (Criteria) this;
        }

        public Criteria andTradingUnitPriceIsNotNull() {
            addCriterion("trading_unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andTradingUnitPriceEqualTo(BigDecimal value) {
            addCriterion("trading_unit_price =", value, "tradingUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTradingUnitPriceNotEqualTo(BigDecimal value) {
            addCriterion("trading_unit_price <>", value, "tradingUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTradingUnitPriceGreaterThan(BigDecimal value) {
            addCriterion("trading_unit_price >", value, "tradingUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTradingUnitPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trading_unit_price >=", value, "tradingUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTradingUnitPriceLessThan(BigDecimal value) {
            addCriterion("trading_unit_price <", value, "tradingUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTradingUnitPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trading_unit_price <=", value, "tradingUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTradingUnitPriceIn(List<BigDecimal> values) {
            addCriterion("trading_unit_price in", values, "tradingUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTradingUnitPriceNotIn(List<BigDecimal> values) {
            addCriterion("trading_unit_price not in", values, "tradingUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTradingUnitPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trading_unit_price between", value1, value2, "tradingUnitPrice");
            return (Criteria) this;
        }

        public Criteria andTradingUnitPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trading_unit_price not between", value1, value2, "tradingUnitPrice");
            return (Criteria) this;
        }

        public Criteria andInformationTypeIsNull() {
            addCriterion("information_type is null");
            return (Criteria) this;
        }

        public Criteria andInformationTypeIsNotNull() {
            addCriterion("information_type is not null");
            return (Criteria) this;
        }

        public Criteria andInformationTypeEqualTo(Integer value) {
            addCriterion("information_type =", value, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeNotEqualTo(Integer value) {
            addCriterion("information_type <>", value, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeGreaterThan(Integer value) {
            addCriterion("information_type >", value, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("information_type >=", value, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeLessThan(Integer value) {
            addCriterion("information_type <", value, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeLessThanOrEqualTo(Integer value) {
            addCriterion("information_type <=", value, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeIn(List<Integer> values) {
            addCriterion("information_type in", values, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeNotIn(List<Integer> values) {
            addCriterion("information_type not in", values, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeBetween(Integer value1, Integer value2) {
            addCriterion("information_type between", value1, value2, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("information_type not between", value1, value2, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationCategoryIsNull() {
            addCriterion("information_category is null");
            return (Criteria) this;
        }

        public Criteria andInformationCategoryIsNotNull() {
            addCriterion("information_category is not null");
            return (Criteria) this;
        }

        public Criteria andInformationCategoryEqualTo(Integer value) {
            addCriterion("information_category =", value, "informationCategory");
            return (Criteria) this;
        }

        public Criteria andInformationCategoryNotEqualTo(Integer value) {
            addCriterion("information_category <>", value, "informationCategory");
            return (Criteria) this;
        }

        public Criteria andInformationCategoryGreaterThan(Integer value) {
            addCriterion("information_category >", value, "informationCategory");
            return (Criteria) this;
        }

        public Criteria andInformationCategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("information_category >=", value, "informationCategory");
            return (Criteria) this;
        }

        public Criteria andInformationCategoryLessThan(Integer value) {
            addCriterion("information_category <", value, "informationCategory");
            return (Criteria) this;
        }

        public Criteria andInformationCategoryLessThanOrEqualTo(Integer value) {
            addCriterion("information_category <=", value, "informationCategory");
            return (Criteria) this;
        }

        public Criteria andInformationCategoryIn(List<Integer> values) {
            addCriterion("information_category in", values, "informationCategory");
            return (Criteria) this;
        }

        public Criteria andInformationCategoryNotIn(List<Integer> values) {
            addCriterion("information_category not in", values, "informationCategory");
            return (Criteria) this;
        }

        public Criteria andInformationCategoryBetween(Integer value1, Integer value2) {
            addCriterion("information_category between", value1, value2, "informationCategory");
            return (Criteria) this;
        }

        public Criteria andInformationCategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("information_category not between", value1, value2, "informationCategory");
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