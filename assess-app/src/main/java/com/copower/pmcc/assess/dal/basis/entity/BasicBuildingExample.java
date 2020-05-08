package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicBuildingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicBuildingExample() {
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

        public Criteria andApplyIdIsNull() {
            addCriterion("apply_id is null");
            return (Criteria) this;
        }

        public Criteria andApplyIdIsNotNull() {
            addCriterion("apply_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplyIdEqualTo(Integer value) {
            addCriterion("apply_id =", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotEqualTo(Integer value) {
            addCriterion("apply_id <>", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThan(Integer value) {
            addCriterion("apply_id >", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("apply_id >=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThan(Integer value) {
            addCriterion("apply_id <", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThanOrEqualTo(Integer value) {
            addCriterion("apply_id <=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdIn(List<Integer> values) {
            addCriterion("apply_id in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotIn(List<Integer> values) {
            addCriterion("apply_id not in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdBetween(Integer value1, Integer value2) {
            addCriterion("apply_id between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("apply_id not between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andEstateIdIsNull() {
            addCriterion("estate_id is null");
            return (Criteria) this;
        }

        public Criteria andEstateIdIsNotNull() {
            addCriterion("estate_id is not null");
            return (Criteria) this;
        }

        public Criteria andEstateIdEqualTo(Integer value) {
            addCriterion("estate_id =", value, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdNotEqualTo(Integer value) {
            addCriterion("estate_id <>", value, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdGreaterThan(Integer value) {
            addCriterion("estate_id >", value, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("estate_id >=", value, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdLessThan(Integer value) {
            addCriterion("estate_id <", value, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdLessThanOrEqualTo(Integer value) {
            addCriterion("estate_id <=", value, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdIn(List<Integer> values) {
            addCriterion("estate_id in", values, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdNotIn(List<Integer> values) {
            addCriterion("estate_id not in", values, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdBetween(Integer value1, Integer value2) {
            addCriterion("estate_id between", value1, value2, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("estate_id not between", value1, value2, "estateId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdIsNull() {
            addCriterion("quote_id is null");
            return (Criteria) this;
        }

        public Criteria andQuoteIdIsNotNull() {
            addCriterion("quote_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuoteIdEqualTo(Integer value) {
            addCriterion("quote_id =", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdNotEqualTo(Integer value) {
            addCriterion("quote_id <>", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdGreaterThan(Integer value) {
            addCriterion("quote_id >", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("quote_id >=", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdLessThan(Integer value) {
            addCriterion("quote_id <", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdLessThanOrEqualTo(Integer value) {
            addCriterion("quote_id <=", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdIn(List<Integer> values) {
            addCriterion("quote_id in", values, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdNotIn(List<Integer> values) {
            addCriterion("quote_id not in", values, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdBetween(Integer value1, Integer value2) {
            addCriterion("quote_id between", value1, value2, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("quote_id not between", value1, value2, "quoteId");
            return (Criteria) this;
        }

        public Criteria andResidenceUseYearIsNull() {
            addCriterion("residence_use_year is null");
            return (Criteria) this;
        }

        public Criteria andResidenceUseYearIsNotNull() {
            addCriterion("residence_use_year is not null");
            return (Criteria) this;
        }

        public Criteria andResidenceUseYearEqualTo(Integer value) {
            addCriterion("residence_use_year =", value, "residenceUseYear");
            return (Criteria) this;
        }

        public Criteria andResidenceUseYearNotEqualTo(Integer value) {
            addCriterion("residence_use_year <>", value, "residenceUseYear");
            return (Criteria) this;
        }

        public Criteria andResidenceUseYearGreaterThan(Integer value) {
            addCriterion("residence_use_year >", value, "residenceUseYear");
            return (Criteria) this;
        }

        public Criteria andResidenceUseYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("residence_use_year >=", value, "residenceUseYear");
            return (Criteria) this;
        }

        public Criteria andResidenceUseYearLessThan(Integer value) {
            addCriterion("residence_use_year <", value, "residenceUseYear");
            return (Criteria) this;
        }

        public Criteria andResidenceUseYearLessThanOrEqualTo(Integer value) {
            addCriterion("residence_use_year <=", value, "residenceUseYear");
            return (Criteria) this;
        }

        public Criteria andResidenceUseYearIn(List<Integer> values) {
            addCriterion("residence_use_year in", values, "residenceUseYear");
            return (Criteria) this;
        }

        public Criteria andResidenceUseYearNotIn(List<Integer> values) {
            addCriterion("residence_use_year not in", values, "residenceUseYear");
            return (Criteria) this;
        }

        public Criteria andResidenceUseYearBetween(Integer value1, Integer value2) {
            addCriterion("residence_use_year between", value1, value2, "residenceUseYear");
            return (Criteria) this;
        }

        public Criteria andResidenceUseYearNotBetween(Integer value1, Integer value2) {
            addCriterion("residence_use_year not between", value1, value2, "residenceUseYear");
            return (Criteria) this;
        }

        public Criteria andIndustryUseYearIsNull() {
            addCriterion("industry_use_year is null");
            return (Criteria) this;
        }

        public Criteria andIndustryUseYearIsNotNull() {
            addCriterion("industry_use_year is not null");
            return (Criteria) this;
        }

        public Criteria andIndustryUseYearEqualTo(Integer value) {
            addCriterion("industry_use_year =", value, "industryUseYear");
            return (Criteria) this;
        }

        public Criteria andIndustryUseYearNotEqualTo(Integer value) {
            addCriterion("industry_use_year <>", value, "industryUseYear");
            return (Criteria) this;
        }

        public Criteria andIndustryUseYearGreaterThan(Integer value) {
            addCriterion("industry_use_year >", value, "industryUseYear");
            return (Criteria) this;
        }

        public Criteria andIndustryUseYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("industry_use_year >=", value, "industryUseYear");
            return (Criteria) this;
        }

        public Criteria andIndustryUseYearLessThan(Integer value) {
            addCriterion("industry_use_year <", value, "industryUseYear");
            return (Criteria) this;
        }

        public Criteria andIndustryUseYearLessThanOrEqualTo(Integer value) {
            addCriterion("industry_use_year <=", value, "industryUseYear");
            return (Criteria) this;
        }

        public Criteria andIndustryUseYearIn(List<Integer> values) {
            addCriterion("industry_use_year in", values, "industryUseYear");
            return (Criteria) this;
        }

        public Criteria andIndustryUseYearNotIn(List<Integer> values) {
            addCriterion("industry_use_year not in", values, "industryUseYear");
            return (Criteria) this;
        }

        public Criteria andIndustryUseYearBetween(Integer value1, Integer value2) {
            addCriterion("industry_use_year between", value1, value2, "industryUseYear");
            return (Criteria) this;
        }

        public Criteria andIndustryUseYearNotBetween(Integer value1, Integer value2) {
            addCriterion("industry_use_year not between", value1, value2, "industryUseYear");
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

        public Criteria andBuildingNameIsNull() {
            addCriterion("building_name is null");
            return (Criteria) this;
        }

        public Criteria andBuildingNameIsNotNull() {
            addCriterion("building_name is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingNameEqualTo(String value) {
            addCriterion("building_name =", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotEqualTo(String value) {
            addCriterion("building_name <>", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameGreaterThan(String value) {
            addCriterion("building_name >", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameGreaterThanOrEqualTo(String value) {
            addCriterion("building_name >=", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameLessThan(String value) {
            addCriterion("building_name <", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameLessThanOrEqualTo(String value) {
            addCriterion("building_name <=", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameLike(String value) {
            addCriterion("building_name like", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotLike(String value) {
            addCriterion("building_name not like", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameIn(List<String> values) {
            addCriterion("building_name in", values, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotIn(List<String> values) {
            addCriterion("building_name not in", values, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameBetween(String value1, String value2) {
            addCriterion("building_name between", value1, value2, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotBetween(String value1, String value2) {
            addCriterion("building_name not between", value1, value2, "buildingName");
            return (Criteria) this;
        }

        public Criteria andFullNameIsNull() {
            addCriterion("full_name is null");
            return (Criteria) this;
        }

        public Criteria andFullNameIsNotNull() {
            addCriterion("full_name is not null");
            return (Criteria) this;
        }

        public Criteria andFullNameEqualTo(String value) {
            addCriterion("full_name =", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotEqualTo(String value) {
            addCriterion("full_name <>", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameGreaterThan(String value) {
            addCriterion("full_name >", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameGreaterThanOrEqualTo(String value) {
            addCriterion("full_name >=", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameLessThan(String value) {
            addCriterion("full_name <", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameLessThanOrEqualTo(String value) {
            addCriterion("full_name <=", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameLike(String value) {
            addCriterion("full_name like", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotLike(String value) {
            addCriterion("full_name not like", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameIn(List<String> values) {
            addCriterion("full_name in", values, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotIn(List<String> values) {
            addCriterion("full_name not in", values, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameBetween(String value1, String value2) {
            addCriterion("full_name between", value1, value2, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotBetween(String value1, String value2) {
            addCriterion("full_name not between", value1, value2, "fullName");
            return (Criteria) this;
        }

        public Criteria andFloorCountIsNull() {
            addCriterion("floor_count is null");
            return (Criteria) this;
        }

        public Criteria andFloorCountIsNotNull() {
            addCriterion("floor_count is not null");
            return (Criteria) this;
        }

        public Criteria andFloorCountEqualTo(String value) {
            addCriterion("floor_count =", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountNotEqualTo(String value) {
            addCriterion("floor_count <>", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountGreaterThan(String value) {
            addCriterion("floor_count >", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountGreaterThanOrEqualTo(String value) {
            addCriterion("floor_count >=", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountLessThan(String value) {
            addCriterion("floor_count <", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountLessThanOrEqualTo(String value) {
            addCriterion("floor_count <=", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountLike(String value) {
            addCriterion("floor_count like", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountNotLike(String value) {
            addCriterion("floor_count not like", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountIn(List<String> values) {
            addCriterion("floor_count in", values, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountNotIn(List<String> values) {
            addCriterion("floor_count not in", values, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountBetween(String value1, String value2) {
            addCriterion("floor_count between", value1, value2, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountNotBetween(String value1, String value2) {
            addCriterion("floor_count not between", value1, value2, "floorCount");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andBuilderIsNull() {
            addCriterion("builder is null");
            return (Criteria) this;
        }

        public Criteria andBuilderIsNotNull() {
            addCriterion("builder is not null");
            return (Criteria) this;
        }

        public Criteria andBuilderEqualTo(Integer value) {
            addCriterion("builder =", value, "builder");
            return (Criteria) this;
        }

        public Criteria andBuilderNotEqualTo(Integer value) {
            addCriterion("builder <>", value, "builder");
            return (Criteria) this;
        }

        public Criteria andBuilderGreaterThan(Integer value) {
            addCriterion("builder >", value, "builder");
            return (Criteria) this;
        }

        public Criteria andBuilderGreaterThanOrEqualTo(Integer value) {
            addCriterion("builder >=", value, "builder");
            return (Criteria) this;
        }

        public Criteria andBuilderLessThan(Integer value) {
            addCriterion("builder <", value, "builder");
            return (Criteria) this;
        }

        public Criteria andBuilderLessThanOrEqualTo(Integer value) {
            addCriterion("builder <=", value, "builder");
            return (Criteria) this;
        }

        public Criteria andBuilderIn(List<Integer> values) {
            addCriterion("builder in", values, "builder");
            return (Criteria) this;
        }

        public Criteria andBuilderNotIn(List<Integer> values) {
            addCriterion("builder not in", values, "builder");
            return (Criteria) this;
        }

        public Criteria andBuilderBetween(Integer value1, Integer value2) {
            addCriterion("builder between", value1, value2, "builder");
            return (Criteria) this;
        }

        public Criteria andBuilderNotBetween(Integer value1, Integer value2) {
            addCriterion("builder not between", value1, value2, "builder");
            return (Criteria) this;
        }

        public Criteria andBuilderNameIsNull() {
            addCriterion("builder_name is null");
            return (Criteria) this;
        }

        public Criteria andBuilderNameIsNotNull() {
            addCriterion("builder_name is not null");
            return (Criteria) this;
        }

        public Criteria andBuilderNameEqualTo(String value) {
            addCriterion("builder_name =", value, "builderName");
            return (Criteria) this;
        }

        public Criteria andBuilderNameNotEqualTo(String value) {
            addCriterion("builder_name <>", value, "builderName");
            return (Criteria) this;
        }

        public Criteria andBuilderNameGreaterThan(String value) {
            addCriterion("builder_name >", value, "builderName");
            return (Criteria) this;
        }

        public Criteria andBuilderNameGreaterThanOrEqualTo(String value) {
            addCriterion("builder_name >=", value, "builderName");
            return (Criteria) this;
        }

        public Criteria andBuilderNameLessThan(String value) {
            addCriterion("builder_name <", value, "builderName");
            return (Criteria) this;
        }

        public Criteria andBuilderNameLessThanOrEqualTo(String value) {
            addCriterion("builder_name <=", value, "builderName");
            return (Criteria) this;
        }

        public Criteria andBuilderNameLike(String value) {
            addCriterion("builder_name like", value, "builderName");
            return (Criteria) this;
        }

        public Criteria andBuilderNameNotLike(String value) {
            addCriterion("builder_name not like", value, "builderName");
            return (Criteria) this;
        }

        public Criteria andBuilderNameIn(List<String> values) {
            addCriterion("builder_name in", values, "builderName");
            return (Criteria) this;
        }

        public Criteria andBuilderNameNotIn(List<String> values) {
            addCriterion("builder_name not in", values, "builderName");
            return (Criteria) this;
        }

        public Criteria andBuilderNameBetween(String value1, String value2) {
            addCriterion("builder_name between", value1, value2, "builderName");
            return (Criteria) this;
        }

        public Criteria andBuilderNameNotBetween(String value1, String value2) {
            addCriterion("builder_name not between", value1, value2, "builderName");
            return (Criteria) this;
        }

        public Criteria andPropertyIsNull() {
            addCriterion("property is null");
            return (Criteria) this;
        }

        public Criteria andPropertyIsNotNull() {
            addCriterion("property is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyEqualTo(Integer value) {
            addCriterion("property =", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyNotEqualTo(Integer value) {
            addCriterion("property <>", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyGreaterThan(Integer value) {
            addCriterion("property >", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyGreaterThanOrEqualTo(Integer value) {
            addCriterion("property >=", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyLessThan(Integer value) {
            addCriterion("property <", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyLessThanOrEqualTo(Integer value) {
            addCriterion("property <=", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyIn(List<Integer> values) {
            addCriterion("property in", values, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyNotIn(List<Integer> values) {
            addCriterion("property not in", values, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyBetween(Integer value1, Integer value2) {
            addCriterion("property between", value1, value2, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyNotBetween(Integer value1, Integer value2) {
            addCriterion("property not between", value1, value2, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyNameIsNull() {
            addCriterion("property_name is null");
            return (Criteria) this;
        }

        public Criteria andPropertyNameIsNotNull() {
            addCriterion("property_name is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyNameEqualTo(String value) {
            addCriterion("property_name =", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotEqualTo(String value) {
            addCriterion("property_name <>", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameGreaterThan(String value) {
            addCriterion("property_name >", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameGreaterThanOrEqualTo(String value) {
            addCriterion("property_name >=", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameLessThan(String value) {
            addCriterion("property_name <", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameLessThanOrEqualTo(String value) {
            addCriterion("property_name <=", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameLike(String value) {
            addCriterion("property_name like", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotLike(String value) {
            addCriterion("property_name not like", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameIn(List<String> values) {
            addCriterion("property_name in", values, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotIn(List<String> values) {
            addCriterion("property_name not in", values, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameBetween(String value1, String value2) {
            addCriterion("property_name between", value1, value2, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotBetween(String value1, String value2) {
            addCriterion("property_name not between", value1, value2, "propertyName");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalIsNull() {
            addCriterion("unit_interval is null");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalIsNotNull() {
            addCriterion("unit_interval is not null");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalEqualTo(String value) {
            addCriterion("unit_interval =", value, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalNotEqualTo(String value) {
            addCriterion("unit_interval <>", value, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalGreaterThan(String value) {
            addCriterion("unit_interval >", value, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalGreaterThanOrEqualTo(String value) {
            addCriterion("unit_interval >=", value, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalLessThan(String value) {
            addCriterion("unit_interval <", value, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalLessThanOrEqualTo(String value) {
            addCriterion("unit_interval <=", value, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalLike(String value) {
            addCriterion("unit_interval like", value, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalNotLike(String value) {
            addCriterion("unit_interval not like", value, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalIn(List<String> values) {
            addCriterion("unit_interval in", values, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalNotIn(List<String> values) {
            addCriterion("unit_interval not in", values, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalBetween(String value1, String value2) {
            addCriterion("unit_interval between", value1, value2, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalNotBetween(String value1, String value2) {
            addCriterion("unit_interval not between", value1, value2, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitCountIsNull() {
            addCriterion("unit_count is null");
            return (Criteria) this;
        }

        public Criteria andUnitCountIsNotNull() {
            addCriterion("unit_count is not null");
            return (Criteria) this;
        }

        public Criteria andUnitCountEqualTo(Integer value) {
            addCriterion("unit_count =", value, "unitCount");
            return (Criteria) this;
        }

        public Criteria andUnitCountNotEqualTo(Integer value) {
            addCriterion("unit_count <>", value, "unitCount");
            return (Criteria) this;
        }

        public Criteria andUnitCountGreaterThan(Integer value) {
            addCriterion("unit_count >", value, "unitCount");
            return (Criteria) this;
        }

        public Criteria andUnitCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("unit_count >=", value, "unitCount");
            return (Criteria) this;
        }

        public Criteria andUnitCountLessThan(Integer value) {
            addCriterion("unit_count <", value, "unitCount");
            return (Criteria) this;
        }

        public Criteria andUnitCountLessThanOrEqualTo(Integer value) {
            addCriterion("unit_count <=", value, "unitCount");
            return (Criteria) this;
        }

        public Criteria andUnitCountIn(List<Integer> values) {
            addCriterion("unit_count in", values, "unitCount");
            return (Criteria) this;
        }

        public Criteria andUnitCountNotIn(List<Integer> values) {
            addCriterion("unit_count not in", values, "unitCount");
            return (Criteria) this;
        }

        public Criteria andUnitCountBetween(Integer value1, Integer value2) {
            addCriterion("unit_count between", value1, value2, "unitCount");
            return (Criteria) this;
        }

        public Criteria andUnitCountNotBetween(Integer value1, Integer value2) {
            addCriterion("unit_count not between", value1, value2, "unitCount");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeIsNull() {
            addCriterion("property_fee is null");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeIsNotNull() {
            addCriterion("property_fee is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeEqualTo(BigDecimal value) {
            addCriterion("property_fee =", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeNotEqualTo(BigDecimal value) {
            addCriterion("property_fee <>", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeGreaterThan(BigDecimal value) {
            addCriterion("property_fee >", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("property_fee >=", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeLessThan(BigDecimal value) {
            addCriterion("property_fee <", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("property_fee <=", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeIn(List<BigDecimal> values) {
            addCriterion("property_fee in", values, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeNotIn(List<BigDecimal> values) {
            addCriterion("property_fee not in", values, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("property_fee between", value1, value2, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("property_fee not between", value1, value2, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeIsNull() {
            addCriterion("facilities_use_fee is null");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeIsNotNull() {
            addCriterion("facilities_use_fee is not null");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeEqualTo(BigDecimal value) {
            addCriterion("facilities_use_fee =", value, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeNotEqualTo(BigDecimal value) {
            addCriterion("facilities_use_fee <>", value, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeGreaterThan(BigDecimal value) {
            addCriterion("facilities_use_fee >", value, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("facilities_use_fee >=", value, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeLessThan(BigDecimal value) {
            addCriterion("facilities_use_fee <", value, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("facilities_use_fee <=", value, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeIn(List<BigDecimal> values) {
            addCriterion("facilities_use_fee in", values, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeNotIn(List<BigDecimal> values) {
            addCriterion("facilities_use_fee not in", values, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("facilities_use_fee between", value1, value2, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("facilities_use_fee not between", value1, value2, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightIsNull() {
            addCriterion("building_height is null");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightIsNotNull() {
            addCriterion("building_height is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightEqualTo(String value) {
            addCriterion("building_height =", value, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightNotEqualTo(String value) {
            addCriterion("building_height <>", value, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightGreaterThan(String value) {
            addCriterion("building_height >", value, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightGreaterThanOrEqualTo(String value) {
            addCriterion("building_height >=", value, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLessThan(String value) {
            addCriterion("building_height <", value, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLessThanOrEqualTo(String value) {
            addCriterion("building_height <=", value, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLike(String value) {
            addCriterion("building_height like", value, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightNotLike(String value) {
            addCriterion("building_height not like", value, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightIn(List<String> values) {
            addCriterion("building_height in", values, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightNotIn(List<String> values) {
            addCriterion("building_height not in", values, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightBetween(String value1, String value2) {
            addCriterion("building_height between", value1, value2, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightNotBetween(String value1, String value2) {
            addCriterion("building_height not between", value1, value2, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaIsNull() {
            addCriterion("building_area is null");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaIsNotNull() {
            addCriterion("building_area is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaEqualTo(BigDecimal value) {
            addCriterion("building_area =", value, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaNotEqualTo(BigDecimal value) {
            addCriterion("building_area <>", value, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaGreaterThan(BigDecimal value) {
            addCriterion("building_area >", value, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("building_area >=", value, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaLessThan(BigDecimal value) {
            addCriterion("building_area <", value, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("building_area <=", value, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaIn(List<BigDecimal> values) {
            addCriterion("building_area in", values, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaNotIn(List<BigDecimal> values) {
            addCriterion("building_area not in", values, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("building_area between", value1, value2, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("building_area not between", value1, value2, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaIsNull() {
            addCriterion("cover_an_area is null");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaIsNotNull() {
            addCriterion("cover_an_area is not null");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaEqualTo(BigDecimal value) {
            addCriterion("cover_an_area =", value, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaNotEqualTo(BigDecimal value) {
            addCriterion("cover_an_area <>", value, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaGreaterThan(BigDecimal value) {
            addCriterion("cover_an_area >", value, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cover_an_area >=", value, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaLessThan(BigDecimal value) {
            addCriterion("cover_an_area <", value, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cover_an_area <=", value, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaIn(List<BigDecimal> values) {
            addCriterion("cover_an_area in", values, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaNotIn(List<BigDecimal> values) {
            addCriterion("cover_an_area not in", values, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cover_an_area between", value1, value2, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cover_an_area not between", value1, value2, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIsNull() {
            addCriterion("property_type is null");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIsNotNull() {
            addCriterion("property_type is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeEqualTo(Integer value) {
            addCriterion("property_type =", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeNotEqualTo(Integer value) {
            addCriterion("property_type <>", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeGreaterThan(Integer value) {
            addCriterion("property_type >", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("property_type >=", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeLessThan(Integer value) {
            addCriterion("property_type <", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeLessThanOrEqualTo(Integer value) {
            addCriterion("property_type <=", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIn(List<Integer> values) {
            addCriterion("property_type in", values, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeNotIn(List<Integer> values) {
            addCriterion("property_type not in", values, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeBetween(Integer value1, Integer value2) {
            addCriterion("property_type between", value1, value2, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("property_type not between", value1, value2, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyCategoryIsNull() {
            addCriterion("property_category is null");
            return (Criteria) this;
        }

        public Criteria andPropertyCategoryIsNotNull() {
            addCriterion("property_category is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyCategoryEqualTo(Integer value) {
            addCriterion("property_category =", value, "propertyCategory");
            return (Criteria) this;
        }

        public Criteria andPropertyCategoryNotEqualTo(Integer value) {
            addCriterion("property_category <>", value, "propertyCategory");
            return (Criteria) this;
        }

        public Criteria andPropertyCategoryGreaterThan(Integer value) {
            addCriterion("property_category >", value, "propertyCategory");
            return (Criteria) this;
        }

        public Criteria andPropertyCategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("property_category >=", value, "propertyCategory");
            return (Criteria) this;
        }

        public Criteria andPropertyCategoryLessThan(Integer value) {
            addCriterion("property_category <", value, "propertyCategory");
            return (Criteria) this;
        }

        public Criteria andPropertyCategoryLessThanOrEqualTo(Integer value) {
            addCriterion("property_category <=", value, "propertyCategory");
            return (Criteria) this;
        }

        public Criteria andPropertyCategoryIn(List<Integer> values) {
            addCriterion("property_category in", values, "propertyCategory");
            return (Criteria) this;
        }

        public Criteria andPropertyCategoryNotIn(List<Integer> values) {
            addCriterion("property_category not in", values, "propertyCategory");
            return (Criteria) this;
        }

        public Criteria andPropertyCategoryBetween(Integer value1, Integer value2) {
            addCriterion("property_category between", value1, value2, "propertyCategory");
            return (Criteria) this;
        }

        public Criteria andPropertyCategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("property_category not between", value1, value2, "propertyCategory");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIsNull() {
            addCriterion("open_time is null");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIsNotNull() {
            addCriterion("open_time is not null");
            return (Criteria) this;
        }

        public Criteria andOpenTimeEqualTo(Date value) {
            addCriterion("open_time =", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotEqualTo(Date value) {
            addCriterion("open_time <>", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeGreaterThan(Date value) {
            addCriterion("open_time >", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("open_time >=", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLessThan(Date value) {
            addCriterion("open_time <", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLessThanOrEqualTo(Date value) {
            addCriterion("open_time <=", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIn(List<Date> values) {
            addCriterion("open_time in", values, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotIn(List<Date> values) {
            addCriterion("open_time not in", values, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeBetween(Date value1, Date value2) {
            addCriterion("open_time between", value1, value2, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotBetween(Date value1, Date value2) {
            addCriterion("open_time not between", value1, value2, "openTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeIsNull() {
            addCriterion("room_time is null");
            return (Criteria) this;
        }

        public Criteria andRoomTimeIsNotNull() {
            addCriterion("room_time is not null");
            return (Criteria) this;
        }

        public Criteria andRoomTimeEqualTo(Date value) {
            addCriterion("room_time =", value, "roomTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeNotEqualTo(Date value) {
            addCriterion("room_time <>", value, "roomTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeGreaterThan(Date value) {
            addCriterion("room_time >", value, "roomTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("room_time >=", value, "roomTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeLessThan(Date value) {
            addCriterion("room_time <", value, "roomTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeLessThanOrEqualTo(Date value) {
            addCriterion("room_time <=", value, "roomTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeIn(List<Date> values) {
            addCriterion("room_time in", values, "roomTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeNotIn(List<Date> values) {
            addCriterion("room_time not in", values, "roomTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeBetween(Date value1, Date value2) {
            addCriterion("room_time between", value1, value2, "roomTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeNotBetween(Date value1, Date value2) {
            addCriterion("room_time not between", value1, value2, "roomTime");
            return (Criteria) this;
        }

        public Criteria andCompletedTimeTypeIsNull() {
            addCriterion("completed_time_type is null");
            return (Criteria) this;
        }

        public Criteria andCompletedTimeTypeIsNotNull() {
            addCriterion("completed_time_type is not null");
            return (Criteria) this;
        }

        public Criteria andCompletedTimeTypeEqualTo(Integer value) {
            addCriterion("completed_time_type =", value, "completedTimeType");
            return (Criteria) this;
        }

        public Criteria andCompletedTimeTypeNotEqualTo(Integer value) {
            addCriterion("completed_time_type <>", value, "completedTimeType");
            return (Criteria) this;
        }

        public Criteria andCompletedTimeTypeGreaterThan(Integer value) {
            addCriterion("completed_time_type >", value, "completedTimeType");
            return (Criteria) this;
        }

        public Criteria andCompletedTimeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("completed_time_type >=", value, "completedTimeType");
            return (Criteria) this;
        }

        public Criteria andCompletedTimeTypeLessThan(Integer value) {
            addCriterion("completed_time_type <", value, "completedTimeType");
            return (Criteria) this;
        }

        public Criteria andCompletedTimeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("completed_time_type <=", value, "completedTimeType");
            return (Criteria) this;
        }

        public Criteria andCompletedTimeTypeIn(List<Integer> values) {
            addCriterion("completed_time_type in", values, "completedTimeType");
            return (Criteria) this;
        }

        public Criteria andCompletedTimeTypeNotIn(List<Integer> values) {
            addCriterion("completed_time_type not in", values, "completedTimeType");
            return (Criteria) this;
        }

        public Criteria andCompletedTimeTypeBetween(Integer value1, Integer value2) {
            addCriterion("completed_time_type between", value1, value2, "completedTimeType");
            return (Criteria) this;
        }

        public Criteria andCompletedTimeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("completed_time_type not between", value1, value2, "completedTimeType");
            return (Criteria) this;
        }

        public Criteria andBeCompletedTimeIsNull() {
            addCriterion("be_completed_time is null");
            return (Criteria) this;
        }

        public Criteria andBeCompletedTimeIsNotNull() {
            addCriterion("be_completed_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeCompletedTimeEqualTo(Date value) {
            addCriterion("be_completed_time =", value, "beCompletedTime");
            return (Criteria) this;
        }

        public Criteria andBeCompletedTimeNotEqualTo(Date value) {
            addCriterion("be_completed_time <>", value, "beCompletedTime");
            return (Criteria) this;
        }

        public Criteria andBeCompletedTimeGreaterThan(Date value) {
            addCriterion("be_completed_time >", value, "beCompletedTime");
            return (Criteria) this;
        }

        public Criteria andBeCompletedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("be_completed_time >=", value, "beCompletedTime");
            return (Criteria) this;
        }

        public Criteria andBeCompletedTimeLessThan(Date value) {
            addCriterion("be_completed_time <", value, "beCompletedTime");
            return (Criteria) this;
        }

        public Criteria andBeCompletedTimeLessThanOrEqualTo(Date value) {
            addCriterion("be_completed_time <=", value, "beCompletedTime");
            return (Criteria) this;
        }

        public Criteria andBeCompletedTimeIn(List<Date> values) {
            addCriterion("be_completed_time in", values, "beCompletedTime");
            return (Criteria) this;
        }

        public Criteria andBeCompletedTimeNotIn(List<Date> values) {
            addCriterion("be_completed_time not in", values, "beCompletedTime");
            return (Criteria) this;
        }

        public Criteria andBeCompletedTimeBetween(Date value1, Date value2) {
            addCriterion("be_completed_time between", value1, value2, "beCompletedTime");
            return (Criteria) this;
        }

        public Criteria andBeCompletedTimeNotBetween(Date value1, Date value2) {
            addCriterion("be_completed_time not between", value1, value2, "beCompletedTime");
            return (Criteria) this;
        }

        public Criteria andFloorHeightIsNull() {
            addCriterion("floor_height is null");
            return (Criteria) this;
        }

        public Criteria andFloorHeightIsNotNull() {
            addCriterion("floor_height is not null");
            return (Criteria) this;
        }

        public Criteria andFloorHeightEqualTo(String value) {
            addCriterion("floor_height =", value, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightNotEqualTo(String value) {
            addCriterion("floor_height <>", value, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightGreaterThan(String value) {
            addCriterion("floor_height >", value, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightGreaterThanOrEqualTo(String value) {
            addCriterion("floor_height >=", value, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightLessThan(String value) {
            addCriterion("floor_height <", value, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightLessThanOrEqualTo(String value) {
            addCriterion("floor_height <=", value, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightLike(String value) {
            addCriterion("floor_height like", value, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightNotLike(String value) {
            addCriterion("floor_height not like", value, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightIn(List<String> values) {
            addCriterion("floor_height in", values, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightNotIn(List<String> values) {
            addCriterion("floor_height not in", values, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightBetween(String value1, String value2) {
            addCriterion("floor_height between", value1, value2, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightNotBetween(String value1, String value2) {
            addCriterion("floor_height not between", value1, value2, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthIsNull() {
            addCriterion("diameter_depth is null");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthIsNotNull() {
            addCriterion("diameter_depth is not null");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthEqualTo(BigDecimal value) {
            addCriterion("diameter_depth =", value, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthNotEqualTo(BigDecimal value) {
            addCriterion("diameter_depth <>", value, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthGreaterThan(BigDecimal value) {
            addCriterion("diameter_depth >", value, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("diameter_depth >=", value, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthLessThan(BigDecimal value) {
            addCriterion("diameter_depth <", value, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthLessThanOrEqualTo(BigDecimal value) {
            addCriterion("diameter_depth <=", value, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthIn(List<BigDecimal> values) {
            addCriterion("diameter_depth in", values, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthNotIn(List<BigDecimal> values) {
            addCriterion("diameter_depth not in", values, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("diameter_depth between", value1, value2, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("diameter_depth not between", value1, value2, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andLandUseYearIsNull() {
            addCriterion("land_use_year is null");
            return (Criteria) this;
        }

        public Criteria andLandUseYearIsNotNull() {
            addCriterion("land_use_year is not null");
            return (Criteria) this;
        }

        public Criteria andLandUseYearEqualTo(BigDecimal value) {
            addCriterion("land_use_year =", value, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLandUseYearNotEqualTo(BigDecimal value) {
            addCriterion("land_use_year <>", value, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLandUseYearGreaterThan(BigDecimal value) {
            addCriterion("land_use_year >", value, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLandUseYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_use_year >=", value, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLandUseYearLessThan(BigDecimal value) {
            addCriterion("land_use_year <", value, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLandUseYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_use_year <=", value, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLandUseYearIn(List<BigDecimal> values) {
            addCriterion("land_use_year in", values, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLandUseYearNotIn(List<BigDecimal> values) {
            addCriterion("land_use_year not in", values, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLandUseYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_use_year between", value1, value2, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLandUseYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_use_year not between", value1, value2, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andNetHeightIsNull() {
            addCriterion("net_height is null");
            return (Criteria) this;
        }

        public Criteria andNetHeightIsNotNull() {
            addCriterion("net_height is not null");
            return (Criteria) this;
        }

        public Criteria andNetHeightEqualTo(BigDecimal value) {
            addCriterion("net_height =", value, "netHeight");
            return (Criteria) this;
        }

        public Criteria andNetHeightNotEqualTo(BigDecimal value) {
            addCriterion("net_height <>", value, "netHeight");
            return (Criteria) this;
        }

        public Criteria andNetHeightGreaterThan(BigDecimal value) {
            addCriterion("net_height >", value, "netHeight");
            return (Criteria) this;
        }

        public Criteria andNetHeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("net_height >=", value, "netHeight");
            return (Criteria) this;
        }

        public Criteria andNetHeightLessThan(BigDecimal value) {
            addCriterion("net_height <", value, "netHeight");
            return (Criteria) this;
        }

        public Criteria andNetHeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("net_height <=", value, "netHeight");
            return (Criteria) this;
        }

        public Criteria andNetHeightIn(List<BigDecimal> values) {
            addCriterion("net_height in", values, "netHeight");
            return (Criteria) this;
        }

        public Criteria andNetHeightNotIn(List<BigDecimal> values) {
            addCriterion("net_height not in", values, "netHeight");
            return (Criteria) this;
        }

        public Criteria andNetHeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("net_height between", value1, value2, "netHeight");
            return (Criteria) this;
        }

        public Criteria andNetHeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("net_height not between", value1, value2, "netHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureTypeIsNull() {
            addCriterion("building_structure_type is null");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureTypeIsNotNull() {
            addCriterion("building_structure_type is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureTypeEqualTo(Integer value) {
            addCriterion("building_structure_type =", value, "buildingStructureType");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureTypeNotEqualTo(Integer value) {
            addCriterion("building_structure_type <>", value, "buildingStructureType");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureTypeGreaterThan(Integer value) {
            addCriterion("building_structure_type >", value, "buildingStructureType");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_structure_type >=", value, "buildingStructureType");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureTypeLessThan(Integer value) {
            addCriterion("building_structure_type <", value, "buildingStructureType");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureTypeLessThanOrEqualTo(Integer value) {
            addCriterion("building_structure_type <=", value, "buildingStructureType");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureTypeIn(List<Integer> values) {
            addCriterion("building_structure_type in", values, "buildingStructureType");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureTypeNotIn(List<Integer> values) {
            addCriterion("building_structure_type not in", values, "buildingStructureType");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureTypeBetween(Integer value1, Integer value2) {
            addCriterion("building_structure_type between", value1, value2, "buildingStructureType");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("building_structure_type not between", value1, value2, "buildingStructureType");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureCategoryIsNull() {
            addCriterion("building_structure_category is null");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureCategoryIsNotNull() {
            addCriterion("building_structure_category is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureCategoryEqualTo(Integer value) {
            addCriterion("building_structure_category =", value, "buildingStructureCategory");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureCategoryNotEqualTo(Integer value) {
            addCriterion("building_structure_category <>", value, "buildingStructureCategory");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureCategoryGreaterThan(Integer value) {
            addCriterion("building_structure_category >", value, "buildingStructureCategory");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureCategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_structure_category >=", value, "buildingStructureCategory");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureCategoryLessThan(Integer value) {
            addCriterion("building_structure_category <", value, "buildingStructureCategory");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureCategoryLessThanOrEqualTo(Integer value) {
            addCriterion("building_structure_category <=", value, "buildingStructureCategory");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureCategoryIn(List<Integer> values) {
            addCriterion("building_structure_category in", values, "buildingStructureCategory");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureCategoryNotIn(List<Integer> values) {
            addCriterion("building_structure_category not in", values, "buildingStructureCategory");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureCategoryBetween(Integer value1, Integer value2) {
            addCriterion("building_structure_category between", value1, value2, "buildingStructureCategory");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureCategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("building_structure_category not between", value1, value2, "buildingStructureCategory");
            return (Criteria) this;
        }

        public Criteria andFirstFloorIsNull() {
            addCriterion("first_floor is null");
            return (Criteria) this;
        }

        public Criteria andFirstFloorIsNotNull() {
            addCriterion("first_floor is not null");
            return (Criteria) this;
        }

        public Criteria andFirstFloorEqualTo(Integer value) {
            addCriterion("first_floor =", value, "firstFloor");
            return (Criteria) this;
        }

        public Criteria andFirstFloorNotEqualTo(Integer value) {
            addCriterion("first_floor <>", value, "firstFloor");
            return (Criteria) this;
        }

        public Criteria andFirstFloorGreaterThan(Integer value) {
            addCriterion("first_floor >", value, "firstFloor");
            return (Criteria) this;
        }

        public Criteria andFirstFloorGreaterThanOrEqualTo(Integer value) {
            addCriterion("first_floor >=", value, "firstFloor");
            return (Criteria) this;
        }

        public Criteria andFirstFloorLessThan(Integer value) {
            addCriterion("first_floor <", value, "firstFloor");
            return (Criteria) this;
        }

        public Criteria andFirstFloorLessThanOrEqualTo(Integer value) {
            addCriterion("first_floor <=", value, "firstFloor");
            return (Criteria) this;
        }

        public Criteria andFirstFloorIn(List<Integer> values) {
            addCriterion("first_floor in", values, "firstFloor");
            return (Criteria) this;
        }

        public Criteria andFirstFloorNotIn(List<Integer> values) {
            addCriterion("first_floor not in", values, "firstFloor");
            return (Criteria) this;
        }

        public Criteria andFirstFloorBetween(Integer value1, Integer value2) {
            addCriterion("first_floor between", value1, value2, "firstFloor");
            return (Criteria) this;
        }

        public Criteria andFirstFloorNotBetween(Integer value1, Integer value2) {
            addCriterion("first_floor not between", value1, value2, "firstFloor");
            return (Criteria) this;
        }

        public Criteria andMaxFloorIsNull() {
            addCriterion("max_floor is null");
            return (Criteria) this;
        }

        public Criteria andMaxFloorIsNotNull() {
            addCriterion("max_floor is not null");
            return (Criteria) this;
        }

        public Criteria andMaxFloorEqualTo(Integer value) {
            addCriterion("max_floor =", value, "maxFloor");
            return (Criteria) this;
        }

        public Criteria andMaxFloorNotEqualTo(Integer value) {
            addCriterion("max_floor <>", value, "maxFloor");
            return (Criteria) this;
        }

        public Criteria andMaxFloorGreaterThan(Integer value) {
            addCriterion("max_floor >", value, "maxFloor");
            return (Criteria) this;
        }

        public Criteria andMaxFloorGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_floor >=", value, "maxFloor");
            return (Criteria) this;
        }

        public Criteria andMaxFloorLessThan(Integer value) {
            addCriterion("max_floor <", value, "maxFloor");
            return (Criteria) this;
        }

        public Criteria andMaxFloorLessThanOrEqualTo(Integer value) {
            addCriterion("max_floor <=", value, "maxFloor");
            return (Criteria) this;
        }

        public Criteria andMaxFloorIn(List<Integer> values) {
            addCriterion("max_floor in", values, "maxFloor");
            return (Criteria) this;
        }

        public Criteria andMaxFloorNotIn(List<Integer> values) {
            addCriterion("max_floor not in", values, "maxFloor");
            return (Criteria) this;
        }

        public Criteria andMaxFloorBetween(Integer value1, Integer value2) {
            addCriterion("max_floor between", value1, value2, "maxFloor");
            return (Criteria) this;
        }

        public Criteria andMaxFloorNotBetween(Integer value1, Integer value2) {
            addCriterion("max_floor not between", value1, value2, "maxFloor");
            return (Criteria) this;
        }

        public Criteria andInJacketAreaIsNull() {
            addCriterion("in_jacket_area is null");
            return (Criteria) this;
        }

        public Criteria andInJacketAreaIsNotNull() {
            addCriterion("in_jacket_area is not null");
            return (Criteria) this;
        }

        public Criteria andInJacketAreaEqualTo(String value) {
            addCriterion("in_jacket_area =", value, "inJacketArea");
            return (Criteria) this;
        }

        public Criteria andInJacketAreaNotEqualTo(String value) {
            addCriterion("in_jacket_area <>", value, "inJacketArea");
            return (Criteria) this;
        }

        public Criteria andInJacketAreaGreaterThan(String value) {
            addCriterion("in_jacket_area >", value, "inJacketArea");
            return (Criteria) this;
        }

        public Criteria andInJacketAreaGreaterThanOrEqualTo(String value) {
            addCriterion("in_jacket_area >=", value, "inJacketArea");
            return (Criteria) this;
        }

        public Criteria andInJacketAreaLessThan(String value) {
            addCriterion("in_jacket_area <", value, "inJacketArea");
            return (Criteria) this;
        }

        public Criteria andInJacketAreaLessThanOrEqualTo(String value) {
            addCriterion("in_jacket_area <=", value, "inJacketArea");
            return (Criteria) this;
        }

        public Criteria andInJacketAreaLike(String value) {
            addCriterion("in_jacket_area like", value, "inJacketArea");
            return (Criteria) this;
        }

        public Criteria andInJacketAreaNotLike(String value) {
            addCriterion("in_jacket_area not like", value, "inJacketArea");
            return (Criteria) this;
        }

        public Criteria andInJacketAreaIn(List<String> values) {
            addCriterion("in_jacket_area in", values, "inJacketArea");
            return (Criteria) this;
        }

        public Criteria andInJacketAreaNotIn(List<String> values) {
            addCriterion("in_jacket_area not in", values, "inJacketArea");
            return (Criteria) this;
        }

        public Criteria andInJacketAreaBetween(String value1, String value2) {
            addCriterion("in_jacket_area between", value1, value2, "inJacketArea");
            return (Criteria) this;
        }

        public Criteria andInJacketAreaNotBetween(String value1, String value2) {
            addCriterion("in_jacket_area not between", value1, value2, "inJacketArea");
            return (Criteria) this;
        }

        public Criteria andUseAreaIsNull() {
            addCriterion("use_area is null");
            return (Criteria) this;
        }

        public Criteria andUseAreaIsNotNull() {
            addCriterion("use_area is not null");
            return (Criteria) this;
        }

        public Criteria andUseAreaEqualTo(String value) {
            addCriterion("use_area =", value, "useArea");
            return (Criteria) this;
        }

        public Criteria andUseAreaNotEqualTo(String value) {
            addCriterion("use_area <>", value, "useArea");
            return (Criteria) this;
        }

        public Criteria andUseAreaGreaterThan(String value) {
            addCriterion("use_area >", value, "useArea");
            return (Criteria) this;
        }

        public Criteria andUseAreaGreaterThanOrEqualTo(String value) {
            addCriterion("use_area >=", value, "useArea");
            return (Criteria) this;
        }

        public Criteria andUseAreaLessThan(String value) {
            addCriterion("use_area <", value, "useArea");
            return (Criteria) this;
        }

        public Criteria andUseAreaLessThanOrEqualTo(String value) {
            addCriterion("use_area <=", value, "useArea");
            return (Criteria) this;
        }

        public Criteria andUseAreaLike(String value) {
            addCriterion("use_area like", value, "useArea");
            return (Criteria) this;
        }

        public Criteria andUseAreaNotLike(String value) {
            addCriterion("use_area not like", value, "useArea");
            return (Criteria) this;
        }

        public Criteria andUseAreaIn(List<String> values) {
            addCriterion("use_area in", values, "useArea");
            return (Criteria) this;
        }

        public Criteria andUseAreaNotIn(List<String> values) {
            addCriterion("use_area not in", values, "useArea");
            return (Criteria) this;
        }

        public Criteria andUseAreaBetween(String value1, String value2) {
            addCriterion("use_area between", value1, value2, "useArea");
            return (Criteria) this;
        }

        public Criteria andUseAreaNotBetween(String value1, String value2) {
            addCriterion("use_area not between", value1, value2, "useArea");
            return (Criteria) this;
        }

        public Criteria andConstructionQualityIsNull() {
            addCriterion("construction_quality is null");
            return (Criteria) this;
        }

        public Criteria andConstructionQualityIsNotNull() {
            addCriterion("construction_quality is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionQualityEqualTo(Integer value) {
            addCriterion("construction_quality =", value, "constructionQuality");
            return (Criteria) this;
        }

        public Criteria andConstructionQualityNotEqualTo(Integer value) {
            addCriterion("construction_quality <>", value, "constructionQuality");
            return (Criteria) this;
        }

        public Criteria andConstructionQualityGreaterThan(Integer value) {
            addCriterion("construction_quality >", value, "constructionQuality");
            return (Criteria) this;
        }

        public Criteria andConstructionQualityGreaterThanOrEqualTo(Integer value) {
            addCriterion("construction_quality >=", value, "constructionQuality");
            return (Criteria) this;
        }

        public Criteria andConstructionQualityLessThan(Integer value) {
            addCriterion("construction_quality <", value, "constructionQuality");
            return (Criteria) this;
        }

        public Criteria andConstructionQualityLessThanOrEqualTo(Integer value) {
            addCriterion("construction_quality <=", value, "constructionQuality");
            return (Criteria) this;
        }

        public Criteria andConstructionQualityIn(List<Integer> values) {
            addCriterion("construction_quality in", values, "constructionQuality");
            return (Criteria) this;
        }

        public Criteria andConstructionQualityNotIn(List<Integer> values) {
            addCriterion("construction_quality not in", values, "constructionQuality");
            return (Criteria) this;
        }

        public Criteria andConstructionQualityBetween(Integer value1, Integer value2) {
            addCriterion("construction_quality between", value1, value2, "constructionQuality");
            return (Criteria) this;
        }

        public Criteria andConstructionQualityNotBetween(Integer value1, Integer value2) {
            addCriterion("construction_quality not between", value1, value2, "constructionQuality");
            return (Criteria) this;
        }

        public Criteria andAppearanceStyleIsNull() {
            addCriterion("appearance_style is null");
            return (Criteria) this;
        }

        public Criteria andAppearanceStyleIsNotNull() {
            addCriterion("appearance_style is not null");
            return (Criteria) this;
        }

        public Criteria andAppearanceStyleEqualTo(Integer value) {
            addCriterion("appearance_style =", value, "appearanceStyle");
            return (Criteria) this;
        }

        public Criteria andAppearanceStyleNotEqualTo(Integer value) {
            addCriterion("appearance_style <>", value, "appearanceStyle");
            return (Criteria) this;
        }

        public Criteria andAppearanceStyleGreaterThan(Integer value) {
            addCriterion("appearance_style >", value, "appearanceStyle");
            return (Criteria) this;
        }

        public Criteria andAppearanceStyleGreaterThanOrEqualTo(Integer value) {
            addCriterion("appearance_style >=", value, "appearanceStyle");
            return (Criteria) this;
        }

        public Criteria andAppearanceStyleLessThan(Integer value) {
            addCriterion("appearance_style <", value, "appearanceStyle");
            return (Criteria) this;
        }

        public Criteria andAppearanceStyleLessThanOrEqualTo(Integer value) {
            addCriterion("appearance_style <=", value, "appearanceStyle");
            return (Criteria) this;
        }

        public Criteria andAppearanceStyleIn(List<Integer> values) {
            addCriterion("appearance_style in", values, "appearanceStyle");
            return (Criteria) this;
        }

        public Criteria andAppearanceStyleNotIn(List<Integer> values) {
            addCriterion("appearance_style not in", values, "appearanceStyle");
            return (Criteria) this;
        }

        public Criteria andAppearanceStyleBetween(Integer value1, Integer value2) {
            addCriterion("appearance_style between", value1, value2, "appearanceStyle");
            return (Criteria) this;
        }

        public Criteria andAppearanceStyleNotBetween(Integer value1, Integer value2) {
            addCriterion("appearance_style not between", value1, value2, "appearanceStyle");
            return (Criteria) this;
        }

        public Criteria andAppearanceNewAndOldIsNull() {
            addCriterion("appearance_new_and_old is null");
            return (Criteria) this;
        }

        public Criteria andAppearanceNewAndOldIsNotNull() {
            addCriterion("appearance_new_and_old is not null");
            return (Criteria) this;
        }

        public Criteria andAppearanceNewAndOldEqualTo(Integer value) {
            addCriterion("appearance_new_and_old =", value, "appearanceNewAndOld");
            return (Criteria) this;
        }

        public Criteria andAppearanceNewAndOldNotEqualTo(Integer value) {
            addCriterion("appearance_new_and_old <>", value, "appearanceNewAndOld");
            return (Criteria) this;
        }

        public Criteria andAppearanceNewAndOldGreaterThan(Integer value) {
            addCriterion("appearance_new_and_old >", value, "appearanceNewAndOld");
            return (Criteria) this;
        }

        public Criteria andAppearanceNewAndOldGreaterThanOrEqualTo(Integer value) {
            addCriterion("appearance_new_and_old >=", value, "appearanceNewAndOld");
            return (Criteria) this;
        }

        public Criteria andAppearanceNewAndOldLessThan(Integer value) {
            addCriterion("appearance_new_and_old <", value, "appearanceNewAndOld");
            return (Criteria) this;
        }

        public Criteria andAppearanceNewAndOldLessThanOrEqualTo(Integer value) {
            addCriterion("appearance_new_and_old <=", value, "appearanceNewAndOld");
            return (Criteria) this;
        }

        public Criteria andAppearanceNewAndOldIn(List<Integer> values) {
            addCriterion("appearance_new_and_old in", values, "appearanceNewAndOld");
            return (Criteria) this;
        }

        public Criteria andAppearanceNewAndOldNotIn(List<Integer> values) {
            addCriterion("appearance_new_and_old not in", values, "appearanceNewAndOld");
            return (Criteria) this;
        }

        public Criteria andAppearanceNewAndOldBetween(Integer value1, Integer value2) {
            addCriterion("appearance_new_and_old between", value1, value2, "appearanceNewAndOld");
            return (Criteria) this;
        }

        public Criteria andAppearanceNewAndOldNotBetween(Integer value1, Integer value2) {
            addCriterion("appearance_new_and_old not between", value1, value2, "appearanceNewAndOld");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceIsNull() {
            addCriterion("between_distance is null");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceIsNotNull() {
            addCriterion("between_distance is not null");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceEqualTo(Integer value) {
            addCriterion("between_distance =", value, "betweenDistance");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceNotEqualTo(Integer value) {
            addCriterion("between_distance <>", value, "betweenDistance");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceGreaterThan(Integer value) {
            addCriterion("between_distance >", value, "betweenDistance");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("between_distance >=", value, "betweenDistance");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceLessThan(Integer value) {
            addCriterion("between_distance <", value, "betweenDistance");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceLessThanOrEqualTo(Integer value) {
            addCriterion("between_distance <=", value, "betweenDistance");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceIn(List<Integer> values) {
            addCriterion("between_distance in", values, "betweenDistance");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceNotIn(List<Integer> values) {
            addCriterion("between_distance not in", values, "betweenDistance");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceBetween(Integer value1, Integer value2) {
            addCriterion("between_distance between", value1, value2, "betweenDistance");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceNotBetween(Integer value1, Integer value2) {
            addCriterion("between_distance not between", value1, value2, "betweenDistance");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceDescriptionIsNull() {
            addCriterion("between_distance_description is null");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceDescriptionIsNotNull() {
            addCriterion("between_distance_description is not null");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceDescriptionEqualTo(String value) {
            addCriterion("between_distance_description =", value, "betweenDistanceDescription");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceDescriptionNotEqualTo(String value) {
            addCriterion("between_distance_description <>", value, "betweenDistanceDescription");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceDescriptionGreaterThan(String value) {
            addCriterion("between_distance_description >", value, "betweenDistanceDescription");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("between_distance_description >=", value, "betweenDistanceDescription");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceDescriptionLessThan(String value) {
            addCriterion("between_distance_description <", value, "betweenDistanceDescription");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceDescriptionLessThanOrEqualTo(String value) {
            addCriterion("between_distance_description <=", value, "betweenDistanceDescription");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceDescriptionLike(String value) {
            addCriterion("between_distance_description like", value, "betweenDistanceDescription");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceDescriptionNotLike(String value) {
            addCriterion("between_distance_description not like", value, "betweenDistanceDescription");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceDescriptionIn(List<String> values) {
            addCriterion("between_distance_description in", values, "betweenDistanceDescription");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceDescriptionNotIn(List<String> values) {
            addCriterion("between_distance_description not in", values, "betweenDistanceDescription");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceDescriptionBetween(String value1, String value2) {
            addCriterion("between_distance_description between", value1, value2, "betweenDistanceDescription");
            return (Criteria) this;
        }

        public Criteria andBetweenDistanceDescriptionNotBetween(String value1, String value2) {
            addCriterion("between_distance_description not between", value1, value2, "betweenDistanceDescription");
            return (Criteria) this;
        }

        public Criteria andVStructuraIsNull() {
            addCriterion("v_structura is null");
            return (Criteria) this;
        }

        public Criteria andVStructuraIsNotNull() {
            addCriterion("v_structura is not null");
            return (Criteria) this;
        }

        public Criteria andVStructuraEqualTo(String value) {
            addCriterion("v_structura =", value, "vStructura");
            return (Criteria) this;
        }

        public Criteria andVStructuraNotEqualTo(String value) {
            addCriterion("v_structura <>", value, "vStructura");
            return (Criteria) this;
        }

        public Criteria andVStructuraGreaterThan(String value) {
            addCriterion("v_structura >", value, "vStructura");
            return (Criteria) this;
        }

        public Criteria andVStructuraGreaterThanOrEqualTo(String value) {
            addCriterion("v_structura >=", value, "vStructura");
            return (Criteria) this;
        }

        public Criteria andVStructuraLessThan(String value) {
            addCriterion("v_structura <", value, "vStructura");
            return (Criteria) this;
        }

        public Criteria andVStructuraLessThanOrEqualTo(String value) {
            addCriterion("v_structura <=", value, "vStructura");
            return (Criteria) this;
        }

        public Criteria andVStructuraLike(String value) {
            addCriterion("v_structura like", value, "vStructura");
            return (Criteria) this;
        }

        public Criteria andVStructuraNotLike(String value) {
            addCriterion("v_structura not like", value, "vStructura");
            return (Criteria) this;
        }

        public Criteria andVStructuraIn(List<String> values) {
            addCriterion("v_structura in", values, "vStructura");
            return (Criteria) this;
        }

        public Criteria andVStructuraNotIn(List<String> values) {
            addCriterion("v_structura not in", values, "vStructura");
            return (Criteria) this;
        }

        public Criteria andVStructuraBetween(String value1, String value2) {
            addCriterion("v_structura between", value1, value2, "vStructura");
            return (Criteria) this;
        }

        public Criteria andVStructuraNotBetween(String value1, String value2) {
            addCriterion("v_structura not between", value1, value2, "vStructura");
            return (Criteria) this;
        }

        public Criteria andVSpecificationsIsNull() {
            addCriterion("v_specifications is null");
            return (Criteria) this;
        }

        public Criteria andVSpecificationsIsNotNull() {
            addCriterion("v_specifications is not null");
            return (Criteria) this;
        }

        public Criteria andVSpecificationsEqualTo(String value) {
            addCriterion("v_specifications =", value, "vSpecifications");
            return (Criteria) this;
        }

        public Criteria andVSpecificationsNotEqualTo(String value) {
            addCriterion("v_specifications <>", value, "vSpecifications");
            return (Criteria) this;
        }

        public Criteria andVSpecificationsGreaterThan(String value) {
            addCriterion("v_specifications >", value, "vSpecifications");
            return (Criteria) this;
        }

        public Criteria andVSpecificationsGreaterThanOrEqualTo(String value) {
            addCriterion("v_specifications >=", value, "vSpecifications");
            return (Criteria) this;
        }

        public Criteria andVSpecificationsLessThan(String value) {
            addCriterion("v_specifications <", value, "vSpecifications");
            return (Criteria) this;
        }

        public Criteria andVSpecificationsLessThanOrEqualTo(String value) {
            addCriterion("v_specifications <=", value, "vSpecifications");
            return (Criteria) this;
        }

        public Criteria andVSpecificationsLike(String value) {
            addCriterion("v_specifications like", value, "vSpecifications");
            return (Criteria) this;
        }

        public Criteria andVSpecificationsNotLike(String value) {
            addCriterion("v_specifications not like", value, "vSpecifications");
            return (Criteria) this;
        }

        public Criteria andVSpecificationsIn(List<String> values) {
            addCriterion("v_specifications in", values, "vSpecifications");
            return (Criteria) this;
        }

        public Criteria andVSpecificationsNotIn(List<String> values) {
            addCriterion("v_specifications not in", values, "vSpecifications");
            return (Criteria) this;
        }

        public Criteria andVSpecificationsBetween(String value1, String value2) {
            addCriterion("v_specifications between", value1, value2, "vSpecifications");
            return (Criteria) this;
        }

        public Criteria andVSpecificationsNotBetween(String value1, String value2) {
            addCriterion("v_specifications not between", value1, value2, "vSpecifications");
            return (Criteria) this;
        }

        public Criteria andVStructuralConstructionIsNull() {
            addCriterion("v_structural_construction is null");
            return (Criteria) this;
        }

        public Criteria andVStructuralConstructionIsNotNull() {
            addCriterion("v_structural_construction is not null");
            return (Criteria) this;
        }

        public Criteria andVStructuralConstructionEqualTo(String value) {
            addCriterion("v_structural_construction =", value, "vStructuralConstruction");
            return (Criteria) this;
        }

        public Criteria andVStructuralConstructionNotEqualTo(String value) {
            addCriterion("v_structural_construction <>", value, "vStructuralConstruction");
            return (Criteria) this;
        }

        public Criteria andVStructuralConstructionGreaterThan(String value) {
            addCriterion("v_structural_construction >", value, "vStructuralConstruction");
            return (Criteria) this;
        }

        public Criteria andVStructuralConstructionGreaterThanOrEqualTo(String value) {
            addCriterion("v_structural_construction >=", value, "vStructuralConstruction");
            return (Criteria) this;
        }

        public Criteria andVStructuralConstructionLessThan(String value) {
            addCriterion("v_structural_construction <", value, "vStructuralConstruction");
            return (Criteria) this;
        }

        public Criteria andVStructuralConstructionLessThanOrEqualTo(String value) {
            addCriterion("v_structural_construction <=", value, "vStructuralConstruction");
            return (Criteria) this;
        }

        public Criteria andVStructuralConstructionLike(String value) {
            addCriterion("v_structural_construction like", value, "vStructuralConstruction");
            return (Criteria) this;
        }

        public Criteria andVStructuralConstructionNotLike(String value) {
            addCriterion("v_structural_construction not like", value, "vStructuralConstruction");
            return (Criteria) this;
        }

        public Criteria andVStructuralConstructionIn(List<String> values) {
            addCriterion("v_structural_construction in", values, "vStructuralConstruction");
            return (Criteria) this;
        }

        public Criteria andVStructuralConstructionNotIn(List<String> values) {
            addCriterion("v_structural_construction not in", values, "vStructuralConstruction");
            return (Criteria) this;
        }

        public Criteria andVStructuralConstructionBetween(String value1, String value2) {
            addCriterion("v_structural_construction between", value1, value2, "vStructuralConstruction");
            return (Criteria) this;
        }

        public Criteria andVStructuralConstructionNotBetween(String value1, String value2) {
            addCriterion("v_structural_construction not between", value1, value2, "vStructuralConstruction");
            return (Criteria) this;
        }

        public Criteria andVBasicPracticeIsNull() {
            addCriterion("v_basic_practice is null");
            return (Criteria) this;
        }

        public Criteria andVBasicPracticeIsNotNull() {
            addCriterion("v_basic_practice is not null");
            return (Criteria) this;
        }

        public Criteria andVBasicPracticeEqualTo(String value) {
            addCriterion("v_basic_practice =", value, "vBasicPractice");
            return (Criteria) this;
        }

        public Criteria andVBasicPracticeNotEqualTo(String value) {
            addCriterion("v_basic_practice <>", value, "vBasicPractice");
            return (Criteria) this;
        }

        public Criteria andVBasicPracticeGreaterThan(String value) {
            addCriterion("v_basic_practice >", value, "vBasicPractice");
            return (Criteria) this;
        }

        public Criteria andVBasicPracticeGreaterThanOrEqualTo(String value) {
            addCriterion("v_basic_practice >=", value, "vBasicPractice");
            return (Criteria) this;
        }

        public Criteria andVBasicPracticeLessThan(String value) {
            addCriterion("v_basic_practice <", value, "vBasicPractice");
            return (Criteria) this;
        }

        public Criteria andVBasicPracticeLessThanOrEqualTo(String value) {
            addCriterion("v_basic_practice <=", value, "vBasicPractice");
            return (Criteria) this;
        }

        public Criteria andVBasicPracticeLike(String value) {
            addCriterion("v_basic_practice like", value, "vBasicPractice");
            return (Criteria) this;
        }

        public Criteria andVBasicPracticeNotLike(String value) {
            addCriterion("v_basic_practice not like", value, "vBasicPractice");
            return (Criteria) this;
        }

        public Criteria andVBasicPracticeIn(List<String> values) {
            addCriterion("v_basic_practice in", values, "vBasicPractice");
            return (Criteria) this;
        }

        public Criteria andVBasicPracticeNotIn(List<String> values) {
            addCriterion("v_basic_practice not in", values, "vBasicPractice");
            return (Criteria) this;
        }

        public Criteria andVBasicPracticeBetween(String value1, String value2) {
            addCriterion("v_basic_practice between", value1, value2, "vBasicPractice");
            return (Criteria) this;
        }

        public Criteria andVBasicPracticeNotBetween(String value1, String value2) {
            addCriterion("v_basic_practice not between", value1, value2, "vBasicPractice");
            return (Criteria) this;
        }

        public Criteria andVStructuralPracticeIsNull() {
            addCriterion("v_structural_practice is null");
            return (Criteria) this;
        }

        public Criteria andVStructuralPracticeIsNotNull() {
            addCriterion("v_structural_practice is not null");
            return (Criteria) this;
        }

        public Criteria andVStructuralPracticeEqualTo(String value) {
            addCriterion("v_structural_practice =", value, "vStructuralPractice");
            return (Criteria) this;
        }

        public Criteria andVStructuralPracticeNotEqualTo(String value) {
            addCriterion("v_structural_practice <>", value, "vStructuralPractice");
            return (Criteria) this;
        }

        public Criteria andVStructuralPracticeGreaterThan(String value) {
            addCriterion("v_structural_practice >", value, "vStructuralPractice");
            return (Criteria) this;
        }

        public Criteria andVStructuralPracticeGreaterThanOrEqualTo(String value) {
            addCriterion("v_structural_practice >=", value, "vStructuralPractice");
            return (Criteria) this;
        }

        public Criteria andVStructuralPracticeLessThan(String value) {
            addCriterion("v_structural_practice <", value, "vStructuralPractice");
            return (Criteria) this;
        }

        public Criteria andVStructuralPracticeLessThanOrEqualTo(String value) {
            addCriterion("v_structural_practice <=", value, "vStructuralPractice");
            return (Criteria) this;
        }

        public Criteria andVStructuralPracticeLike(String value) {
            addCriterion("v_structural_practice like", value, "vStructuralPractice");
            return (Criteria) this;
        }

        public Criteria andVStructuralPracticeNotLike(String value) {
            addCriterion("v_structural_practice not like", value, "vStructuralPractice");
            return (Criteria) this;
        }

        public Criteria andVStructuralPracticeIn(List<String> values) {
            addCriterion("v_structural_practice in", values, "vStructuralPractice");
            return (Criteria) this;
        }

        public Criteria andVStructuralPracticeNotIn(List<String> values) {
            addCriterion("v_structural_practice not in", values, "vStructuralPractice");
            return (Criteria) this;
        }

        public Criteria andVStructuralPracticeBetween(String value1, String value2) {
            addCriterion("v_structural_practice between", value1, value2, "vStructuralPractice");
            return (Criteria) this;
        }

        public Criteria andVStructuralPracticeNotBetween(String value1, String value2) {
            addCriterion("v_structural_practice not between", value1, value2, "vStructuralPractice");
            return (Criteria) this;
        }

        public Criteria andPropertySocialPrestigeIsNull() {
            addCriterion("property_social_prestige is null");
            return (Criteria) this;
        }

        public Criteria andPropertySocialPrestigeIsNotNull() {
            addCriterion("property_social_prestige is not null");
            return (Criteria) this;
        }

        public Criteria andPropertySocialPrestigeEqualTo(Integer value) {
            addCriterion("property_social_prestige =", value, "propertySocialPrestige");
            return (Criteria) this;
        }

        public Criteria andPropertySocialPrestigeNotEqualTo(Integer value) {
            addCriterion("property_social_prestige <>", value, "propertySocialPrestige");
            return (Criteria) this;
        }

        public Criteria andPropertySocialPrestigeGreaterThan(Integer value) {
            addCriterion("property_social_prestige >", value, "propertySocialPrestige");
            return (Criteria) this;
        }

        public Criteria andPropertySocialPrestigeGreaterThanOrEqualTo(Integer value) {
            addCriterion("property_social_prestige >=", value, "propertySocialPrestige");
            return (Criteria) this;
        }

        public Criteria andPropertySocialPrestigeLessThan(Integer value) {
            addCriterion("property_social_prestige <", value, "propertySocialPrestige");
            return (Criteria) this;
        }

        public Criteria andPropertySocialPrestigeLessThanOrEqualTo(Integer value) {
            addCriterion("property_social_prestige <=", value, "propertySocialPrestige");
            return (Criteria) this;
        }

        public Criteria andPropertySocialPrestigeIn(List<Integer> values) {
            addCriterion("property_social_prestige in", values, "propertySocialPrestige");
            return (Criteria) this;
        }

        public Criteria andPropertySocialPrestigeNotIn(List<Integer> values) {
            addCriterion("property_social_prestige not in", values, "propertySocialPrestige");
            return (Criteria) this;
        }

        public Criteria andPropertySocialPrestigeBetween(Integer value1, Integer value2) {
            addCriterion("property_social_prestige between", value1, value2, "propertySocialPrestige");
            return (Criteria) this;
        }

        public Criteria andPropertySocialPrestigeNotBetween(Integer value1, Integer value2) {
            addCriterion("property_social_prestige not between", value1, value2, "propertySocialPrestige");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyNatureIsNull() {
            addCriterion("property_company_nature is null");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyNatureIsNotNull() {
            addCriterion("property_company_nature is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyNatureEqualTo(Integer value) {
            addCriterion("property_company_nature =", value, "propertyCompanyNature");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyNatureNotEqualTo(Integer value) {
            addCriterion("property_company_nature <>", value, "propertyCompanyNature");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyNatureGreaterThan(Integer value) {
            addCriterion("property_company_nature >", value, "propertyCompanyNature");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyNatureGreaterThanOrEqualTo(Integer value) {
            addCriterion("property_company_nature >=", value, "propertyCompanyNature");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyNatureLessThan(Integer value) {
            addCriterion("property_company_nature <", value, "propertyCompanyNature");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyNatureLessThanOrEqualTo(Integer value) {
            addCriterion("property_company_nature <=", value, "propertyCompanyNature");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyNatureIn(List<Integer> values) {
            addCriterion("property_company_nature in", values, "propertyCompanyNature");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyNatureNotIn(List<Integer> values) {
            addCriterion("property_company_nature not in", values, "propertyCompanyNature");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyNatureBetween(Integer value1, Integer value2) {
            addCriterion("property_company_nature between", value1, value2, "propertyCompanyNature");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyNatureNotBetween(Integer value1, Integer value2) {
            addCriterion("property_company_nature not between", value1, value2, "propertyCompanyNature");
            return (Criteria) this;
        }

        public Criteria andMapIdIsNull() {
            addCriterion("map_id is null");
            return (Criteria) this;
        }

        public Criteria andMapIdIsNotNull() {
            addCriterion("map_id is not null");
            return (Criteria) this;
        }

        public Criteria andMapIdEqualTo(Integer value) {
            addCriterion("map_id =", value, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdNotEqualTo(Integer value) {
            addCriterion("map_id <>", value, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdGreaterThan(Integer value) {
            addCriterion("map_id >", value, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("map_id >=", value, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdLessThan(Integer value) {
            addCriterion("map_id <", value, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdLessThanOrEqualTo(Integer value) {
            addCriterion("map_id <=", value, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdIn(List<Integer> values) {
            addCriterion("map_id in", values, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdNotIn(List<Integer> values) {
            addCriterion("map_id not in", values, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdBetween(Integer value1, Integer value2) {
            addCriterion("map_id between", value1, value2, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdNotBetween(Integer value1, Integer value2) {
            addCriterion("map_id not between", value1, value2, "mapId");
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

        public Criteria andDisplayCaseIdIsNull() {
            addCriterion("display_case_id is null");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdIsNotNull() {
            addCriterion("display_case_id is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdEqualTo(Integer value) {
            addCriterion("display_case_id =", value, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdNotEqualTo(Integer value) {
            addCriterion("display_case_id <>", value, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdGreaterThan(Integer value) {
            addCriterion("display_case_id >", value, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("display_case_id >=", value, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdLessThan(Integer value) {
            addCriterion("display_case_id <", value, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdLessThanOrEqualTo(Integer value) {
            addCriterion("display_case_id <=", value, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdIn(List<Integer> values) {
            addCriterion("display_case_id in", values, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdNotIn(List<Integer> values) {
            addCriterion("display_case_id not in", values, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdBetween(Integer value1, Integer value2) {
            addCriterion("display_case_id between", value1, value2, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("display_case_id not between", value1, value2, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdIsNull() {
            addCriterion("relevance_id is null");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdIsNotNull() {
            addCriterion("relevance_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdEqualTo(Integer value) {
            addCriterion("relevance_id =", value, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdNotEqualTo(Integer value) {
            addCriterion("relevance_id <>", value, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdGreaterThan(Integer value) {
            addCriterion("relevance_id >", value, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("relevance_id >=", value, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdLessThan(Integer value) {
            addCriterion("relevance_id <", value, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdLessThanOrEqualTo(Integer value) {
            addCriterion("relevance_id <=", value, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdIn(List<Integer> values) {
            addCriterion("relevance_id in", values, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdNotIn(List<Integer> values) {
            addCriterion("relevance_id not in", values, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdBetween(Integer value1, Integer value2) {
            addCriterion("relevance_id between", value1, value2, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("relevance_id not between", value1, value2, "relevanceId");
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

        public Criteria andBisCaseIsNull() {
            addCriterion("bis_case is null");
            return (Criteria) this;
        }

        public Criteria andBisCaseIsNotNull() {
            addCriterion("bis_case is not null");
            return (Criteria) this;
        }

        public Criteria andBisCaseEqualTo(Boolean value) {
            addCriterion("bis_case =", value, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseNotEqualTo(Boolean value) {
            addCriterion("bis_case <>", value, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseGreaterThan(Boolean value) {
            addCriterion("bis_case >", value, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_case >=", value, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseLessThan(Boolean value) {
            addCriterion("bis_case <", value, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_case <=", value, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseIn(List<Boolean> values) {
            addCriterion("bis_case in", values, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseNotIn(List<Boolean> values) {
            addCriterion("bis_case not in", values, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_case between", value1, value2, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_case not between", value1, value2, "bisCase");
            return (Criteria) this;
        }

        public Criteria andReferenceIsNull() {
            addCriterion("reference is null");
            return (Criteria) this;
        }

        public Criteria andReferenceIsNotNull() {
            addCriterion("reference is not null");
            return (Criteria) this;
        }

        public Criteria andReferenceEqualTo(String value) {
            addCriterion("reference =", value, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceNotEqualTo(String value) {
            addCriterion("reference <>", value, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceGreaterThan(String value) {
            addCriterion("reference >", value, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceGreaterThanOrEqualTo(String value) {
            addCriterion("reference >=", value, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceLessThan(String value) {
            addCriterion("reference <", value, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceLessThanOrEqualTo(String value) {
            addCriterion("reference <=", value, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceLike(String value) {
            addCriterion("reference like", value, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceNotLike(String value) {
            addCriterion("reference not like", value, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceIn(List<String> values) {
            addCriterion("reference in", values, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceNotIn(List<String> values) {
            addCriterion("reference not in", values, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceBetween(String value1, String value2) {
            addCriterion("reference between", value1, value2, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceNotBetween(String value1, String value2) {
            addCriterion("reference not between", value1, value2, "reference");
            return (Criteria) this;
        }

        public Criteria andOrientationIsNull() {
            addCriterion("orientation is null");
            return (Criteria) this;
        }

        public Criteria andOrientationIsNotNull() {
            addCriterion("orientation is not null");
            return (Criteria) this;
        }

        public Criteria andOrientationEqualTo(String value) {
            addCriterion("orientation =", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationNotEqualTo(String value) {
            addCriterion("orientation <>", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationGreaterThan(String value) {
            addCriterion("orientation >", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationGreaterThanOrEqualTo(String value) {
            addCriterion("orientation >=", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationLessThan(String value) {
            addCriterion("orientation <", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationLessThanOrEqualTo(String value) {
            addCriterion("orientation <=", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationLike(String value) {
            addCriterion("orientation like", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationNotLike(String value) {
            addCriterion("orientation not like", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationIn(List<String> values) {
            addCriterion("orientation in", values, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationNotIn(List<String> values) {
            addCriterion("orientation not in", values, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationBetween(String value1, String value2) {
            addCriterion("orientation between", value1, value2, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationNotBetween(String value1, String value2) {
            addCriterion("orientation not between", value1, value2, "orientation");
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

        public Criteria andBisDeleteIsNull() {
            addCriterion("bis_delete is null");
            return (Criteria) this;
        }

        public Criteria andBisDeleteIsNotNull() {
            addCriterion("bis_delete is not null");
            return (Criteria) this;
        }

        public Criteria andBisDeleteEqualTo(Boolean value) {
            addCriterion("bis_delete =", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteNotEqualTo(Boolean value) {
            addCriterion("bis_delete <>", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteGreaterThan(Boolean value) {
            addCriterion("bis_delete >", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_delete >=", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteLessThan(Boolean value) {
            addCriterion("bis_delete <", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_delete <=", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteIn(List<Boolean> values) {
            addCriterion("bis_delete in", values, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteNotIn(List<Boolean> values) {
            addCriterion("bis_delete not in", values, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_delete between", value1, value2, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_delete not between", value1, value2, "bisDelete");
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

        public Criteria andMinimumFloorDistanceIsNull() {
            addCriterion("minimum_floor_distance is null");
            return (Criteria) this;
        }

        public Criteria andMinimumFloorDistanceIsNotNull() {
            addCriterion("minimum_floor_distance is not null");
            return (Criteria) this;
        }

        public Criteria andMinimumFloorDistanceEqualTo(String value) {
            addCriterion("minimum_floor_distance =", value, "minimumFloorDistance");
            return (Criteria) this;
        }

        public Criteria andMinimumFloorDistanceNotEqualTo(String value) {
            addCriterion("minimum_floor_distance <>", value, "minimumFloorDistance");
            return (Criteria) this;
        }

        public Criteria andMinimumFloorDistanceGreaterThan(String value) {
            addCriterion("minimum_floor_distance >", value, "minimumFloorDistance");
            return (Criteria) this;
        }

        public Criteria andMinimumFloorDistanceGreaterThanOrEqualTo(String value) {
            addCriterion("minimum_floor_distance >=", value, "minimumFloorDistance");
            return (Criteria) this;
        }

        public Criteria andMinimumFloorDistanceLessThan(String value) {
            addCriterion("minimum_floor_distance <", value, "minimumFloorDistance");
            return (Criteria) this;
        }

        public Criteria andMinimumFloorDistanceLessThanOrEqualTo(String value) {
            addCriterion("minimum_floor_distance <=", value, "minimumFloorDistance");
            return (Criteria) this;
        }

        public Criteria andMinimumFloorDistanceLike(String value) {
            addCriterion("minimum_floor_distance like", value, "minimumFloorDistance");
            return (Criteria) this;
        }

        public Criteria andMinimumFloorDistanceNotLike(String value) {
            addCriterion("minimum_floor_distance not like", value, "minimumFloorDistance");
            return (Criteria) this;
        }

        public Criteria andMinimumFloorDistanceIn(List<String> values) {
            addCriterion("minimum_floor_distance in", values, "minimumFloorDistance");
            return (Criteria) this;
        }

        public Criteria andMinimumFloorDistanceNotIn(List<String> values) {
            addCriterion("minimum_floor_distance not in", values, "minimumFloorDistance");
            return (Criteria) this;
        }

        public Criteria andMinimumFloorDistanceBetween(String value1, String value2) {
            addCriterion("minimum_floor_distance between", value1, value2, "minimumFloorDistance");
            return (Criteria) this;
        }

        public Criteria andMinimumFloorDistanceNotBetween(String value1, String value2) {
            addCriterion("minimum_floor_distance not between", value1, value2, "minimumFloorDistance");
            return (Criteria) this;
        }

        public Criteria andStreetInfoIdIsNull() {
            addCriterion("street_info_id is null");
            return (Criteria) this;
        }

        public Criteria andStreetInfoIdIsNotNull() {
            addCriterion("street_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andStreetInfoIdEqualTo(Integer value) {
            addCriterion("street_info_id =", value, "streetInfoId");
            return (Criteria) this;
        }

        public Criteria andStreetInfoIdNotEqualTo(Integer value) {
            addCriterion("street_info_id <>", value, "streetInfoId");
            return (Criteria) this;
        }

        public Criteria andStreetInfoIdGreaterThan(Integer value) {
            addCriterion("street_info_id >", value, "streetInfoId");
            return (Criteria) this;
        }

        public Criteria andStreetInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("street_info_id >=", value, "streetInfoId");
            return (Criteria) this;
        }

        public Criteria andStreetInfoIdLessThan(Integer value) {
            addCriterion("street_info_id <", value, "streetInfoId");
            return (Criteria) this;
        }

        public Criteria andStreetInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("street_info_id <=", value, "streetInfoId");
            return (Criteria) this;
        }

        public Criteria andStreetInfoIdIn(List<Integer> values) {
            addCriterion("street_info_id in", values, "streetInfoId");
            return (Criteria) this;
        }

        public Criteria andStreetInfoIdNotIn(List<Integer> values) {
            addCriterion("street_info_id not in", values, "streetInfoId");
            return (Criteria) this;
        }

        public Criteria andStreetInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("street_info_id between", value1, value2, "streetInfoId");
            return (Criteria) this;
        }

        public Criteria andStreetInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("street_info_id not between", value1, value2, "streetInfoId");
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