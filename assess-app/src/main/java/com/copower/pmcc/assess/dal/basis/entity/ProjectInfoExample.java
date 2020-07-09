package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectInfoExample() {
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

        public Criteria andProjectClassIdIsNull() {
            addCriterion("project_class_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdIsNotNull() {
            addCriterion("project_class_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdEqualTo(Integer value) {
            addCriterion("project_class_id =", value, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdNotEqualTo(Integer value) {
            addCriterion("project_class_id <>", value, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdGreaterThan(Integer value) {
            addCriterion("project_class_id >", value, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_class_id >=", value, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdLessThan(Integer value) {
            addCriterion("project_class_id <", value, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_class_id <=", value, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdIn(List<Integer> values) {
            addCriterion("project_class_id in", values, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdNotIn(List<Integer> values) {
            addCriterion("project_class_id not in", values, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdBetween(Integer value1, Integer value2) {
            addCriterion("project_class_id between", value1, value2, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectClassIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_class_id not between", value1, value2, "projectClassId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdIsNull() {
            addCriterion("project_type_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdIsNotNull() {
            addCriterion("project_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdEqualTo(Integer value) {
            addCriterion("project_type_id =", value, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdNotEqualTo(Integer value) {
            addCriterion("project_type_id <>", value, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdGreaterThan(Integer value) {
            addCriterion("project_type_id >", value, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_type_id >=", value, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdLessThan(Integer value) {
            addCriterion("project_type_id <", value, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_type_id <=", value, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdIn(List<Integer> values) {
            addCriterion("project_type_id in", values, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdNotIn(List<Integer> values) {
            addCriterion("project_type_id not in", values, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("project_type_id between", value1, value2, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_type_id not between", value1, value2, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdIsNull() {
            addCriterion("project_category_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdIsNotNull() {
            addCriterion("project_category_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdEqualTo(Integer value) {
            addCriterion("project_category_id =", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdNotEqualTo(Integer value) {
            addCriterion("project_category_id <>", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdGreaterThan(Integer value) {
            addCriterion("project_category_id >", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_category_id >=", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdLessThan(Integer value) {
            addCriterion("project_category_id <", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_category_id <=", value, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdIn(List<Integer> values) {
            addCriterion("project_category_id in", values, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdNotIn(List<Integer> values) {
            addCriterion("project_category_id not in", values, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("project_category_id between", value1, value2, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_category_id not between", value1, value2, "projectCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andUrgencyIsNull() {
            addCriterion("urgency is null");
            return (Criteria) this;
        }

        public Criteria andUrgencyIsNotNull() {
            addCriterion("urgency is not null");
            return (Criteria) this;
        }

        public Criteria andUrgencyEqualTo(Integer value) {
            addCriterion("urgency =", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyNotEqualTo(Integer value) {
            addCriterion("urgency <>", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyGreaterThan(Integer value) {
            addCriterion("urgency >", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyGreaterThanOrEqualTo(Integer value) {
            addCriterion("urgency >=", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyLessThan(Integer value) {
            addCriterion("urgency <", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyLessThanOrEqualTo(Integer value) {
            addCriterion("urgency <=", value, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyIn(List<Integer> values) {
            addCriterion("urgency in", values, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyNotIn(List<Integer> values) {
            addCriterion("urgency not in", values, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyBetween(Integer value1, Integer value2) {
            addCriterion("urgency between", value1, value2, "urgency");
            return (Criteria) this;
        }

        public Criteria andUrgencyNotBetween(Integer value1, Integer value2) {
            addCriterion("urgency not between", value1, value2, "urgency");
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

        public Criteria andValuationDateIsNull() {
            addCriterion("valuation_date is null");
            return (Criteria) this;
        }

        public Criteria andValuationDateIsNotNull() {
            addCriterion("valuation_date is not null");
            return (Criteria) this;
        }

        public Criteria andValuationDateEqualTo(Date value) {
            addCriterion("valuation_date =", value, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andValuationDateNotEqualTo(Date value) {
            addCriterion("valuation_date <>", value, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andValuationDateGreaterThan(Date value) {
            addCriterion("valuation_date >", value, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andValuationDateGreaterThanOrEqualTo(Date value) {
            addCriterion("valuation_date >=", value, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andValuationDateLessThan(Date value) {
            addCriterion("valuation_date <", value, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andValuationDateLessThanOrEqualTo(Date value) {
            addCriterion("valuation_date <=", value, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andValuationDateIn(List<Date> values) {
            addCriterion("valuation_date in", values, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andValuationDateNotIn(List<Date> values) {
            addCriterion("valuation_date not in", values, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andValuationDateBetween(Date value1, Date value2) {
            addCriterion("valuation_date between", value1, value2, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andValuationDateNotBetween(Date value1, Date value2) {
            addCriterion("valuation_date not between", value1, value2, "valuationDate");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameIsNull() {
            addCriterion("entrust_purpose_name is null");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameIsNotNull() {
            addCriterion("entrust_purpose_name is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameEqualTo(String value) {
            addCriterion("entrust_purpose_name =", value, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameNotEqualTo(String value) {
            addCriterion("entrust_purpose_name <>", value, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameGreaterThan(String value) {
            addCriterion("entrust_purpose_name >", value, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameGreaterThanOrEqualTo(String value) {
            addCriterion("entrust_purpose_name >=", value, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameLessThan(String value) {
            addCriterion("entrust_purpose_name <", value, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameLessThanOrEqualTo(String value) {
            addCriterion("entrust_purpose_name <=", value, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameLike(String value) {
            addCriterion("entrust_purpose_name like", value, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameNotLike(String value) {
            addCriterion("entrust_purpose_name not like", value, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameIn(List<String> values) {
            addCriterion("entrust_purpose_name in", values, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameNotIn(List<String> values) {
            addCriterion("entrust_purpose_name not in", values, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameBetween(String value1, String value2) {
            addCriterion("entrust_purpose_name between", value1, value2, "entrustPurposeName");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNameNotBetween(String value1, String value2) {
            addCriterion("entrust_purpose_name not between", value1, value2, "entrustPurposeName");
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

        public Criteria andValueTypeIsNull() {
            addCriterion("value_type is null");
            return (Criteria) this;
        }

        public Criteria andValueTypeIsNotNull() {
            addCriterion("value_type is not null");
            return (Criteria) this;
        }

        public Criteria andValueTypeEqualTo(Integer value) {
            addCriterion("value_type =", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotEqualTo(Integer value) {
            addCriterion("value_type <>", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeGreaterThan(Integer value) {
            addCriterion("value_type >", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("value_type >=", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLessThan(Integer value) {
            addCriterion("value_type <", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLessThanOrEqualTo(Integer value) {
            addCriterion("value_type <=", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeIn(List<Integer> values) {
            addCriterion("value_type in", values, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotIn(List<Integer> values) {
            addCriterion("value_type not in", values, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeBetween(Integer value1, Integer value2) {
            addCriterion("value_type between", value1, value2, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("value_type not between", value1, value2, "valueType");
            return (Criteria) this;
        }

        public Criteria andRemarkValueTypeIsNull() {
            addCriterion("remark_value_type is null");
            return (Criteria) this;
        }

        public Criteria andRemarkValueTypeIsNotNull() {
            addCriterion("remark_value_type is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkValueTypeEqualTo(String value) {
            addCriterion("remark_value_type =", value, "remarkValueType");
            return (Criteria) this;
        }

        public Criteria andRemarkValueTypeNotEqualTo(String value) {
            addCriterion("remark_value_type <>", value, "remarkValueType");
            return (Criteria) this;
        }

        public Criteria andRemarkValueTypeGreaterThan(String value) {
            addCriterion("remark_value_type >", value, "remarkValueType");
            return (Criteria) this;
        }

        public Criteria andRemarkValueTypeGreaterThanOrEqualTo(String value) {
            addCriterion("remark_value_type >=", value, "remarkValueType");
            return (Criteria) this;
        }

        public Criteria andRemarkValueTypeLessThan(String value) {
            addCriterion("remark_value_type <", value, "remarkValueType");
            return (Criteria) this;
        }

        public Criteria andRemarkValueTypeLessThanOrEqualTo(String value) {
            addCriterion("remark_value_type <=", value, "remarkValueType");
            return (Criteria) this;
        }

        public Criteria andRemarkValueTypeLike(String value) {
            addCriterion("remark_value_type like", value, "remarkValueType");
            return (Criteria) this;
        }

        public Criteria andRemarkValueTypeNotLike(String value) {
            addCriterion("remark_value_type not like", value, "remarkValueType");
            return (Criteria) this;
        }

        public Criteria andRemarkValueTypeIn(List<String> values) {
            addCriterion("remark_value_type in", values, "remarkValueType");
            return (Criteria) this;
        }

        public Criteria andRemarkValueTypeNotIn(List<String> values) {
            addCriterion("remark_value_type not in", values, "remarkValueType");
            return (Criteria) this;
        }

        public Criteria andRemarkValueTypeBetween(String value1, String value2) {
            addCriterion("remark_value_type between", value1, value2, "remarkValueType");
            return (Criteria) this;
        }

        public Criteria andRemarkValueTypeNotBetween(String value1, String value2) {
            addCriterion("remark_value_type not between", value1, value2, "remarkValueType");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNull() {
            addCriterion("department_id is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNotNull() {
            addCriterion("department_id is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdEqualTo(Integer value) {
            addCriterion("department_id =", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotEqualTo(Integer value) {
            addCriterion("department_id <>", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThan(Integer value) {
            addCriterion("department_id >", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("department_id >=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThan(Integer value) {
            addCriterion("department_id <", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("department_id <=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIn(List<Integer> values) {
            addCriterion("department_id in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotIn(List<Integer> values) {
            addCriterion("department_id not in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdBetween(Integer value1, Integer value2) {
            addCriterion("department_id between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("department_id not between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanIsNull() {
            addCriterion("complete_date_plan is null");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanIsNotNull() {
            addCriterion("complete_date_plan is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanEqualTo(Date value) {
            addCriterion("complete_date_plan =", value, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanNotEqualTo(Date value) {
            addCriterion("complete_date_plan <>", value, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanGreaterThan(Date value) {
            addCriterion("complete_date_plan >", value, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanGreaterThanOrEqualTo(Date value) {
            addCriterion("complete_date_plan >=", value, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanLessThan(Date value) {
            addCriterion("complete_date_plan <", value, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanLessThanOrEqualTo(Date value) {
            addCriterion("complete_date_plan <=", value, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanIn(List<Date> values) {
            addCriterion("complete_date_plan in", values, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanNotIn(List<Date> values) {
            addCriterion("complete_date_plan not in", values, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanBetween(Date value1, Date value2) {
            addCriterion("complete_date_plan between", value1, value2, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDatePlanNotBetween(Date value1, Date value2) {
            addCriterion("complete_date_plan not between", value1, value2, "completeDatePlan");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualIsNull() {
            addCriterion("complete_date_actual is null");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualIsNotNull() {
            addCriterion("complete_date_actual is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualEqualTo(Date value) {
            addCriterion("complete_date_actual =", value, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualNotEqualTo(Date value) {
            addCriterion("complete_date_actual <>", value, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualGreaterThan(Date value) {
            addCriterion("complete_date_actual >", value, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualGreaterThanOrEqualTo(Date value) {
            addCriterion("complete_date_actual >=", value, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualLessThan(Date value) {
            addCriterion("complete_date_actual <", value, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualLessThanOrEqualTo(Date value) {
            addCriterion("complete_date_actual <=", value, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualIn(List<Date> values) {
            addCriterion("complete_date_actual in", values, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualNotIn(List<Date> values) {
            addCriterion("complete_date_actual not in", values, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualBetween(Date value1, Date value2) {
            addCriterion("complete_date_actual between", value1, value2, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateActualNotBetween(Date value1, Date value2) {
            addCriterion("complete_date_actual not between", value1, value2, "completeDateActual");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartIsNull() {
            addCriterion("complete_date_start is null");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartIsNotNull() {
            addCriterion("complete_date_start is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartEqualTo(Date value) {
            addCriterion("complete_date_start =", value, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartNotEqualTo(Date value) {
            addCriterion("complete_date_start <>", value, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartGreaterThan(Date value) {
            addCriterion("complete_date_start >", value, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartGreaterThanOrEqualTo(Date value) {
            addCriterion("complete_date_start >=", value, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartLessThan(Date value) {
            addCriterion("complete_date_start <", value, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartLessThanOrEqualTo(Date value) {
            addCriterion("complete_date_start <=", value, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartIn(List<Date> values) {
            addCriterion("complete_date_start in", values, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartNotIn(List<Date> values) {
            addCriterion("complete_date_start not in", values, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartBetween(Date value1, Date value2) {
            addCriterion("complete_date_start between", value1, value2, "completeDateStart");
            return (Criteria) this;
        }

        public Criteria andCompleteDateStartNotBetween(Date value1, Date value2) {
            addCriterion("complete_date_start not between", value1, value2, "completeDateStart");
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

        public Criteria andPropertyScopeIsNull() {
            addCriterion("property_scope is null");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeIsNotNull() {
            addCriterion("property_scope is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeEqualTo(String value) {
            addCriterion("property_scope =", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNotEqualTo(String value) {
            addCriterion("property_scope <>", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeGreaterThan(String value) {
            addCriterion("property_scope >", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeGreaterThanOrEqualTo(String value) {
            addCriterion("property_scope >=", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeLessThan(String value) {
            addCriterion("property_scope <", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeLessThanOrEqualTo(String value) {
            addCriterion("property_scope <=", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeLike(String value) {
            addCriterion("property_scope like", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNotLike(String value) {
            addCriterion("property_scope not like", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeIn(List<String> values) {
            addCriterion("property_scope in", values, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNotIn(List<String> values) {
            addCriterion("property_scope not in", values, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeBetween(String value1, String value2) {
            addCriterion("property_scope between", value1, value2, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNotBetween(String value1, String value2) {
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

        public Criteria andLoanTypeIsNull() {
            addCriterion("loan_type is null");
            return (Criteria) this;
        }

        public Criteria andLoanTypeIsNotNull() {
            addCriterion("loan_type is not null");
            return (Criteria) this;
        }

        public Criteria andLoanTypeEqualTo(Integer value) {
            addCriterion("loan_type =", value, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNotEqualTo(Integer value) {
            addCriterion("loan_type <>", value, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeGreaterThan(Integer value) {
            addCriterion("loan_type >", value, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("loan_type >=", value, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeLessThan(Integer value) {
            addCriterion("loan_type <", value, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeLessThanOrEqualTo(Integer value) {
            addCriterion("loan_type <=", value, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeIn(List<Integer> values) {
            addCriterion("loan_type in", values, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNotIn(List<Integer> values) {
            addCriterion("loan_type not in", values, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeBetween(Integer value1, Integer value2) {
            addCriterion("loan_type between", value1, value2, "loanType");
            return (Criteria) this;
        }

        public Criteria andLoanTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("loan_type not between", value1, value2, "loanType");
            return (Criteria) this;
        }

        public Criteria andContractIdIsNull() {
            addCriterion("contract_id is null");
            return (Criteria) this;
        }

        public Criteria andContractIdIsNotNull() {
            addCriterion("contract_id is not null");
            return (Criteria) this;
        }

        public Criteria andContractIdEqualTo(String value) {
            addCriterion("contract_id =", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdNotEqualTo(String value) {
            addCriterion("contract_id <>", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdGreaterThan(String value) {
            addCriterion("contract_id >", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdGreaterThanOrEqualTo(String value) {
            addCriterion("contract_id >=", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdLessThan(String value) {
            addCriterion("contract_id <", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdLessThanOrEqualTo(String value) {
            addCriterion("contract_id <=", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdLike(String value) {
            addCriterion("contract_id like", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdNotLike(String value) {
            addCriterion("contract_id not like", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdIn(List<String> values) {
            addCriterion("contract_id in", values, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdNotIn(List<String> values) {
            addCriterion("contract_id not in", values, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdBetween(String value1, String value2) {
            addCriterion("contract_id between", value1, value2, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdNotBetween(String value1, String value2) {
            addCriterion("contract_id not between", value1, value2, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractNameIsNull() {
            addCriterion("contract_name is null");
            return (Criteria) this;
        }

        public Criteria andContractNameIsNotNull() {
            addCriterion("contract_name is not null");
            return (Criteria) this;
        }

        public Criteria andContractNameEqualTo(String value) {
            addCriterion("contract_name =", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotEqualTo(String value) {
            addCriterion("contract_name <>", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameGreaterThan(String value) {
            addCriterion("contract_name >", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameGreaterThanOrEqualTo(String value) {
            addCriterion("contract_name >=", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameLessThan(String value) {
            addCriterion("contract_name <", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameLessThanOrEqualTo(String value) {
            addCriterion("contract_name <=", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameLike(String value) {
            addCriterion("contract_name like", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotLike(String value) {
            addCriterion("contract_name not like", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameIn(List<String> values) {
            addCriterion("contract_name in", values, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotIn(List<String> values) {
            addCriterion("contract_name not in", values, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameBetween(String value1, String value2) {
            addCriterion("contract_name between", value1, value2, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotBetween(String value1, String value2) {
            addCriterion("contract_name not between", value1, value2, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractPriceIsNull() {
            addCriterion("contract_price is null");
            return (Criteria) this;
        }

        public Criteria andContractPriceIsNotNull() {
            addCriterion("contract_price is not null");
            return (Criteria) this;
        }

        public Criteria andContractPriceEqualTo(BigDecimal value) {
            addCriterion("contract_price =", value, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceNotEqualTo(BigDecimal value) {
            addCriterion("contract_price <>", value, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceGreaterThan(BigDecimal value) {
            addCriterion("contract_price >", value, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("contract_price >=", value, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceLessThan(BigDecimal value) {
            addCriterion("contract_price <", value, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("contract_price <=", value, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceIn(List<BigDecimal> values) {
            addCriterion("contract_price in", values, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceNotIn(List<BigDecimal> values) {
            addCriterion("contract_price not in", values, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("contract_price between", value1, value2, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andContractPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("contract_price not between", value1, value2, "contractPrice");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromIsNull() {
            addCriterion("service_come_from is null");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromIsNotNull() {
            addCriterion("service_come_from is not null");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromEqualTo(String value) {
            addCriterion("service_come_from =", value, "serviceComeFrom");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromNotEqualTo(String value) {
            addCriterion("service_come_from <>", value, "serviceComeFrom");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromGreaterThan(String value) {
            addCriterion("service_come_from >", value, "serviceComeFrom");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromGreaterThanOrEqualTo(String value) {
            addCriterion("service_come_from >=", value, "serviceComeFrom");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromLessThan(String value) {
            addCriterion("service_come_from <", value, "serviceComeFrom");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromLessThanOrEqualTo(String value) {
            addCriterion("service_come_from <=", value, "serviceComeFrom");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromLike(String value) {
            addCriterion("service_come_from like", value, "serviceComeFrom");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromNotLike(String value) {
            addCriterion("service_come_from not like", value, "serviceComeFrom");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromIn(List<String> values) {
            addCriterion("service_come_from in", values, "serviceComeFrom");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromNotIn(List<String> values) {
            addCriterion("service_come_from not in", values, "serviceComeFrom");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromBetween(String value1, String value2) {
            addCriterion("service_come_from between", value1, value2, "serviceComeFrom");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromNotBetween(String value1, String value2) {
            addCriterion("service_come_from not between", value1, value2, "serviceComeFrom");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromExplainIsNull() {
            addCriterion("service_come_from_explain is null");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromExplainIsNotNull() {
            addCriterion("service_come_from_explain is not null");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromExplainEqualTo(String value) {
            addCriterion("service_come_from_explain =", value, "serviceComeFromExplain");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromExplainNotEqualTo(String value) {
            addCriterion("service_come_from_explain <>", value, "serviceComeFromExplain");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromExplainGreaterThan(String value) {
            addCriterion("service_come_from_explain >", value, "serviceComeFromExplain");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromExplainGreaterThanOrEqualTo(String value) {
            addCriterion("service_come_from_explain >=", value, "serviceComeFromExplain");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromExplainLessThan(String value) {
            addCriterion("service_come_from_explain <", value, "serviceComeFromExplain");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromExplainLessThanOrEqualTo(String value) {
            addCriterion("service_come_from_explain <=", value, "serviceComeFromExplain");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromExplainLike(String value) {
            addCriterion("service_come_from_explain like", value, "serviceComeFromExplain");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromExplainNotLike(String value) {
            addCriterion("service_come_from_explain not like", value, "serviceComeFromExplain");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromExplainIn(List<String> values) {
            addCriterion("service_come_from_explain in", values, "serviceComeFromExplain");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromExplainNotIn(List<String> values) {
            addCriterion("service_come_from_explain not in", values, "serviceComeFromExplain");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromExplainBetween(String value1, String value2) {
            addCriterion("service_come_from_explain between", value1, value2, "serviceComeFromExplain");
            return (Criteria) this;
        }

        public Criteria andServiceComeFromExplainNotBetween(String value1, String value2) {
            addCriterion("service_come_from_explain not between", value1, value2, "serviceComeFromExplain");
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

        public Criteria andPublicProjectIdIsNull() {
            addCriterion("public_project_id is null");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdIsNotNull() {
            addCriterion("public_project_id is not null");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdEqualTo(Integer value) {
            addCriterion("public_project_id =", value, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdNotEqualTo(Integer value) {
            addCriterion("public_project_id <>", value, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdGreaterThan(Integer value) {
            addCriterion("public_project_id >", value, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("public_project_id >=", value, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdLessThan(Integer value) {
            addCriterion("public_project_id <", value, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("public_project_id <=", value, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdIn(List<Integer> values) {
            addCriterion("public_project_id in", values, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdNotIn(List<Integer> values) {
            addCriterion("public_project_id not in", values, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("public_project_id between", value1, value2, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andPublicProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("public_project_id not between", value1, value2, "publicProjectId");
            return (Criteria) this;
        }

        public Criteria andAssignProcessInsIdIsNull() {
            addCriterion("assign_process_ins_id is null");
            return (Criteria) this;
        }

        public Criteria andAssignProcessInsIdIsNotNull() {
            addCriterion("assign_process_ins_id is not null");
            return (Criteria) this;
        }

        public Criteria andAssignProcessInsIdEqualTo(String value) {
            addCriterion("assign_process_ins_id =", value, "assignProcessInsId");
            return (Criteria) this;
        }

        public Criteria andAssignProcessInsIdNotEqualTo(String value) {
            addCriterion("assign_process_ins_id <>", value, "assignProcessInsId");
            return (Criteria) this;
        }

        public Criteria andAssignProcessInsIdGreaterThan(String value) {
            addCriterion("assign_process_ins_id >", value, "assignProcessInsId");
            return (Criteria) this;
        }

        public Criteria andAssignProcessInsIdGreaterThanOrEqualTo(String value) {
            addCriterion("assign_process_ins_id >=", value, "assignProcessInsId");
            return (Criteria) this;
        }

        public Criteria andAssignProcessInsIdLessThan(String value) {
            addCriterion("assign_process_ins_id <", value, "assignProcessInsId");
            return (Criteria) this;
        }

        public Criteria andAssignProcessInsIdLessThanOrEqualTo(String value) {
            addCriterion("assign_process_ins_id <=", value, "assignProcessInsId");
            return (Criteria) this;
        }

        public Criteria andAssignProcessInsIdLike(String value) {
            addCriterion("assign_process_ins_id like", value, "assignProcessInsId");
            return (Criteria) this;
        }

        public Criteria andAssignProcessInsIdNotLike(String value) {
            addCriterion("assign_process_ins_id not like", value, "assignProcessInsId");
            return (Criteria) this;
        }

        public Criteria andAssignProcessInsIdIn(List<String> values) {
            addCriterion("assign_process_ins_id in", values, "assignProcessInsId");
            return (Criteria) this;
        }

        public Criteria andAssignProcessInsIdNotIn(List<String> values) {
            addCriterion("assign_process_ins_id not in", values, "assignProcessInsId");
            return (Criteria) this;
        }

        public Criteria andAssignProcessInsIdBetween(String value1, String value2) {
            addCriterion("assign_process_ins_id between", value1, value2, "assignProcessInsId");
            return (Criteria) this;
        }

        public Criteria andAssignProcessInsIdNotBetween(String value1, String value2) {
            addCriterion("assign_process_ins_id not between", value1, value2, "assignProcessInsId");
            return (Criteria) this;
        }

        public Criteria andAssignStatusIsNull() {
            addCriterion("assign_status is null");
            return (Criteria) this;
        }

        public Criteria andAssignStatusIsNotNull() {
            addCriterion("assign_status is not null");
            return (Criteria) this;
        }

        public Criteria andAssignStatusEqualTo(String value) {
            addCriterion("assign_status =", value, "assignStatus");
            return (Criteria) this;
        }

        public Criteria andAssignStatusNotEqualTo(String value) {
            addCriterion("assign_status <>", value, "assignStatus");
            return (Criteria) this;
        }

        public Criteria andAssignStatusGreaterThan(String value) {
            addCriterion("assign_status >", value, "assignStatus");
            return (Criteria) this;
        }

        public Criteria andAssignStatusGreaterThanOrEqualTo(String value) {
            addCriterion("assign_status >=", value, "assignStatus");
            return (Criteria) this;
        }

        public Criteria andAssignStatusLessThan(String value) {
            addCriterion("assign_status <", value, "assignStatus");
            return (Criteria) this;
        }

        public Criteria andAssignStatusLessThanOrEqualTo(String value) {
            addCriterion("assign_status <=", value, "assignStatus");
            return (Criteria) this;
        }

        public Criteria andAssignStatusLike(String value) {
            addCriterion("assign_status like", value, "assignStatus");
            return (Criteria) this;
        }

        public Criteria andAssignStatusNotLike(String value) {
            addCriterion("assign_status not like", value, "assignStatus");
            return (Criteria) this;
        }

        public Criteria andAssignStatusIn(List<String> values) {
            addCriterion("assign_status in", values, "assignStatus");
            return (Criteria) this;
        }

        public Criteria andAssignStatusNotIn(List<String> values) {
            addCriterion("assign_status not in", values, "assignStatus");
            return (Criteria) this;
        }

        public Criteria andAssignStatusBetween(String value1, String value2) {
            addCriterion("assign_status between", value1, value2, "assignStatus");
            return (Criteria) this;
        }

        public Criteria andAssignStatusNotBetween(String value1, String value2) {
            addCriterion("assign_status not between", value1, value2, "assignStatus");
            return (Criteria) this;
        }

        public Criteria andEstateNameIsNull() {
            addCriterion("estate_name is null");
            return (Criteria) this;
        }

        public Criteria andEstateNameIsNotNull() {
            addCriterion("estate_name is not null");
            return (Criteria) this;
        }

        public Criteria andEstateNameEqualTo(String value) {
            addCriterion("estate_name =", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameNotEqualTo(String value) {
            addCriterion("estate_name <>", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameGreaterThan(String value) {
            addCriterion("estate_name >", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameGreaterThanOrEqualTo(String value) {
            addCriterion("estate_name >=", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameLessThan(String value) {
            addCriterion("estate_name <", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameLessThanOrEqualTo(String value) {
            addCriterion("estate_name <=", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameLike(String value) {
            addCriterion("estate_name like", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameNotLike(String value) {
            addCriterion("estate_name not like", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameIn(List<String> values) {
            addCriterion("estate_name in", values, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameNotIn(List<String> values) {
            addCriterion("estate_name not in", values, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameBetween(String value1, String value2) {
            addCriterion("estate_name between", value1, value2, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameNotBetween(String value1, String value2) {
            addCriterion("estate_name not between", value1, value2, "estateName");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberDateIsNull() {
            addCriterion("preaudit_number_date is null");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberDateIsNotNull() {
            addCriterion("preaudit_number_date is not null");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberDateEqualTo(Date value) {
            addCriterion("preaudit_number_date =", value, "preauditNumberDate");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberDateNotEqualTo(Date value) {
            addCriterion("preaudit_number_date <>", value, "preauditNumberDate");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberDateGreaterThan(Date value) {
            addCriterion("preaudit_number_date >", value, "preauditNumberDate");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberDateGreaterThanOrEqualTo(Date value) {
            addCriterion("preaudit_number_date >=", value, "preauditNumberDate");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberDateLessThan(Date value) {
            addCriterion("preaudit_number_date <", value, "preauditNumberDate");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberDateLessThanOrEqualTo(Date value) {
            addCriterion("preaudit_number_date <=", value, "preauditNumberDate");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberDateIn(List<Date> values) {
            addCriterion("preaudit_number_date in", values, "preauditNumberDate");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberDateNotIn(List<Date> values) {
            addCriterion("preaudit_number_date not in", values, "preauditNumberDate");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberDateBetween(Date value1, Date value2) {
            addCriterion("preaudit_number_date between", value1, value2, "preauditNumberDate");
            return (Criteria) this;
        }

        public Criteria andPreauditNumberDateNotBetween(Date value1, Date value2) {
            addCriterion("preaudit_number_date not between", value1, value2, "preauditNumberDate");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNameIsNull() {
            addCriterion("property_scope_name is null");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNameIsNotNull() {
            addCriterion("property_scope_name is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNameEqualTo(String value) {
            addCriterion("property_scope_name =", value, "propertyScopeName");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNameNotEqualTo(String value) {
            addCriterion("property_scope_name <>", value, "propertyScopeName");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNameGreaterThan(String value) {
            addCriterion("property_scope_name >", value, "propertyScopeName");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNameGreaterThanOrEqualTo(String value) {
            addCriterion("property_scope_name >=", value, "propertyScopeName");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNameLessThan(String value) {
            addCriterion("property_scope_name <", value, "propertyScopeName");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNameLessThanOrEqualTo(String value) {
            addCriterion("property_scope_name <=", value, "propertyScopeName");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNameLike(String value) {
            addCriterion("property_scope_name like", value, "propertyScopeName");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNameNotLike(String value) {
            addCriterion("property_scope_name not like", value, "propertyScopeName");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNameIn(List<String> values) {
            addCriterion("property_scope_name in", values, "propertyScopeName");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNameNotIn(List<String> values) {
            addCriterion("property_scope_name not in", values, "propertyScopeName");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNameBetween(String value1, String value2) {
            addCriterion("property_scope_name between", value1, value2, "propertyScopeName");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNameNotBetween(String value1, String value2) {
            addCriterion("property_scope_name not between", value1, value2, "propertyScopeName");
            return (Criteria) this;
        }

        public Criteria andResultNumberDateIsNull() {
            addCriterion("result_number_date is null");
            return (Criteria) this;
        }

        public Criteria andResultNumberDateIsNotNull() {
            addCriterion("result_number_date is not null");
            return (Criteria) this;
        }

        public Criteria andResultNumberDateEqualTo(Date value) {
            addCriterion("result_number_date =", value, "resultNumberDate");
            return (Criteria) this;
        }

        public Criteria andResultNumberDateNotEqualTo(Date value) {
            addCriterion("result_number_date <>", value, "resultNumberDate");
            return (Criteria) this;
        }

        public Criteria andResultNumberDateGreaterThan(Date value) {
            addCriterion("result_number_date >", value, "resultNumberDate");
            return (Criteria) this;
        }

        public Criteria andResultNumberDateGreaterThanOrEqualTo(Date value) {
            addCriterion("result_number_date >=", value, "resultNumberDate");
            return (Criteria) this;
        }

        public Criteria andResultNumberDateLessThan(Date value) {
            addCriterion("result_number_date <", value, "resultNumberDate");
            return (Criteria) this;
        }

        public Criteria andResultNumberDateLessThanOrEqualTo(Date value) {
            addCriterion("result_number_date <=", value, "resultNumberDate");
            return (Criteria) this;
        }

        public Criteria andResultNumberDateIn(List<Date> values) {
            addCriterion("result_number_date in", values, "resultNumberDate");
            return (Criteria) this;
        }

        public Criteria andResultNumberDateNotIn(List<Date> values) {
            addCriterion("result_number_date not in", values, "resultNumberDate");
            return (Criteria) this;
        }

        public Criteria andResultNumberDateBetween(Date value1, Date value2) {
            addCriterion("result_number_date between", value1, value2, "resultNumberDate");
            return (Criteria) this;
        }

        public Criteria andResultNumberDateNotBetween(Date value1, Date value2) {
            addCriterion("result_number_date not between", value1, value2, "resultNumberDate");
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

        public Criteria andBisAssignIsNull() {
            addCriterion("bis_assign is null");
            return (Criteria) this;
        }

        public Criteria andBisAssignIsNotNull() {
            addCriterion("bis_assign is not null");
            return (Criteria) this;
        }

        public Criteria andBisAssignEqualTo(Boolean value) {
            addCriterion("bis_assign =", value, "bisAssign");
            return (Criteria) this;
        }

        public Criteria andBisAssignNotEqualTo(Boolean value) {
            addCriterion("bis_assign <>", value, "bisAssign");
            return (Criteria) this;
        }

        public Criteria andBisAssignGreaterThan(Boolean value) {
            addCriterion("bis_assign >", value, "bisAssign");
            return (Criteria) this;
        }

        public Criteria andBisAssignGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_assign >=", value, "bisAssign");
            return (Criteria) this;
        }

        public Criteria andBisAssignLessThan(Boolean value) {
            addCriterion("bis_assign <", value, "bisAssign");
            return (Criteria) this;
        }

        public Criteria andBisAssignLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_assign <=", value, "bisAssign");
            return (Criteria) this;
        }

        public Criteria andBisAssignIn(List<Boolean> values) {
            addCriterion("bis_assign in", values, "bisAssign");
            return (Criteria) this;
        }

        public Criteria andBisAssignNotIn(List<Boolean> values) {
            addCriterion("bis_assign not in", values, "bisAssign");
            return (Criteria) this;
        }

        public Criteria andBisAssignBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_assign between", value1, value2, "bisAssign");
            return (Criteria) this;
        }

        public Criteria andBisAssignNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_assign not between", value1, value2, "bisAssign");
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