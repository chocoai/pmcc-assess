package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicEstateLandStateExample {
    /**
     * tb_basic_estate_land_state
     */
    protected String orderByClause;

    /**
     * tb_basic_estate_land_state
     */
    protected boolean distinct;

    /**
     * tb_basic_estate_land_state
     */
    protected List<Criteria> oredCriteria;

    public BasicEstateLandStateExample() {
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
     * tb_basic_estate_land_state
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

        public Criteria andLandUseTypeEqualTo(String value) {
            addCriterion("land_use_type =", value, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeNotEqualTo(String value) {
            addCriterion("land_use_type <>", value, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeGreaterThan(String value) {
            addCriterion("land_use_type >", value, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeGreaterThanOrEqualTo(String value) {
            addCriterion("land_use_type >=", value, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeLessThan(String value) {
            addCriterion("land_use_type <", value, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeLessThanOrEqualTo(String value) {
            addCriterion("land_use_type <=", value, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeLike(String value) {
            addCriterion("land_use_type like", value, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeNotLike(String value) {
            addCriterion("land_use_type not like", value, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeIn(List<String> values) {
            addCriterion("land_use_type in", values, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeNotIn(List<String> values) {
            addCriterion("land_use_type not in", values, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeBetween(String value1, String value2) {
            addCriterion("land_use_type between", value1, value2, "landUseType");
            return (Criteria) this;
        }

        public Criteria andLandUseTypeNotBetween(String value1, String value2) {
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

        public Criteria andLandUseCategoryEqualTo(String value) {
            addCriterion("land_use_category =", value, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryNotEqualTo(String value) {
            addCriterion("land_use_category <>", value, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryGreaterThan(String value) {
            addCriterion("land_use_category >", value, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("land_use_category >=", value, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryLessThan(String value) {
            addCriterion("land_use_category <", value, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryLessThanOrEqualTo(String value) {
            addCriterion("land_use_category <=", value, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryLike(String value) {
            addCriterion("land_use_category like", value, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryNotLike(String value) {
            addCriterion("land_use_category not like", value, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryIn(List<String> values) {
            addCriterion("land_use_category in", values, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryNotIn(List<String> values) {
            addCriterion("land_use_category not in", values, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryBetween(String value1, String value2) {
            addCriterion("land_use_category between", value1, value2, "landUseCategory");
            return (Criteria) this;
        }

        public Criteria andLandUseCategoryNotBetween(String value1, String value2) {
            addCriterion("land_use_category not between", value1, value2, "landUseCategory");
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

        public Criteria andLandFactorTotalScoreIsNull() {
            addCriterion("land_factor_total_score is null");
            return (Criteria) this;
        }

        public Criteria andLandFactorTotalScoreIsNotNull() {
            addCriterion("land_factor_total_score is not null");
            return (Criteria) this;
        }

        public Criteria andLandFactorTotalScoreEqualTo(BigDecimal value) {
            addCriterion("land_factor_total_score =", value, "landFactorTotalScore");
            return (Criteria) this;
        }

        public Criteria andLandFactorTotalScoreNotEqualTo(BigDecimal value) {
            addCriterion("land_factor_total_score <>", value, "landFactorTotalScore");
            return (Criteria) this;
        }

        public Criteria andLandFactorTotalScoreGreaterThan(BigDecimal value) {
            addCriterion("land_factor_total_score >", value, "landFactorTotalScore");
            return (Criteria) this;
        }

        public Criteria andLandFactorTotalScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_factor_total_score >=", value, "landFactorTotalScore");
            return (Criteria) this;
        }

        public Criteria andLandFactorTotalScoreLessThan(BigDecimal value) {
            addCriterion("land_factor_total_score <", value, "landFactorTotalScore");
            return (Criteria) this;
        }

        public Criteria andLandFactorTotalScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_factor_total_score <=", value, "landFactorTotalScore");
            return (Criteria) this;
        }

        public Criteria andLandFactorTotalScoreIn(List<BigDecimal> values) {
            addCriterion("land_factor_total_score in", values, "landFactorTotalScore");
            return (Criteria) this;
        }

        public Criteria andLandFactorTotalScoreNotIn(List<BigDecimal> values) {
            addCriterion("land_factor_total_score not in", values, "landFactorTotalScore");
            return (Criteria) this;
        }

        public Criteria andLandFactorTotalScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_factor_total_score between", value1, value2, "landFactorTotalScore");
            return (Criteria) this;
        }

        public Criteria andLandFactorTotalScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_factor_total_score not between", value1, value2, "landFactorTotalScore");
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

        public Criteria andEastToNameIsNull() {
            addCriterion("east_to_name is null");
            return (Criteria) this;
        }

        public Criteria andEastToNameIsNotNull() {
            addCriterion("east_to_name is not null");
            return (Criteria) this;
        }

        public Criteria andEastToNameEqualTo(String value) {
            addCriterion("east_to_name =", value, "eastToName");
            return (Criteria) this;
        }

        public Criteria andEastToNameNotEqualTo(String value) {
            addCriterion("east_to_name <>", value, "eastToName");
            return (Criteria) this;
        }

        public Criteria andEastToNameGreaterThan(String value) {
            addCriterion("east_to_name >", value, "eastToName");
            return (Criteria) this;
        }

        public Criteria andEastToNameGreaterThanOrEqualTo(String value) {
            addCriterion("east_to_name >=", value, "eastToName");
            return (Criteria) this;
        }

        public Criteria andEastToNameLessThan(String value) {
            addCriterion("east_to_name <", value, "eastToName");
            return (Criteria) this;
        }

        public Criteria andEastToNameLessThanOrEqualTo(String value) {
            addCriterion("east_to_name <=", value, "eastToName");
            return (Criteria) this;
        }

        public Criteria andEastToNameLike(String value) {
            addCriterion("east_to_name like", value, "eastToName");
            return (Criteria) this;
        }

        public Criteria andEastToNameNotLike(String value) {
            addCriterion("east_to_name not like", value, "eastToName");
            return (Criteria) this;
        }

        public Criteria andEastToNameIn(List<String> values) {
            addCriterion("east_to_name in", values, "eastToName");
            return (Criteria) this;
        }

        public Criteria andEastToNameNotIn(List<String> values) {
            addCriterion("east_to_name not in", values, "eastToName");
            return (Criteria) this;
        }

        public Criteria andEastToNameBetween(String value1, String value2) {
            addCriterion("east_to_name between", value1, value2, "eastToName");
            return (Criteria) this;
        }

        public Criteria andEastToNameNotBetween(String value1, String value2) {
            addCriterion("east_to_name not between", value1, value2, "eastToName");
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

        public Criteria andSouthToNameIsNull() {
            addCriterion("south_to_name is null");
            return (Criteria) this;
        }

        public Criteria andSouthToNameIsNotNull() {
            addCriterion("south_to_name is not null");
            return (Criteria) this;
        }

        public Criteria andSouthToNameEqualTo(String value) {
            addCriterion("south_to_name =", value, "southToName");
            return (Criteria) this;
        }

        public Criteria andSouthToNameNotEqualTo(String value) {
            addCriterion("south_to_name <>", value, "southToName");
            return (Criteria) this;
        }

        public Criteria andSouthToNameGreaterThan(String value) {
            addCriterion("south_to_name >", value, "southToName");
            return (Criteria) this;
        }

        public Criteria andSouthToNameGreaterThanOrEqualTo(String value) {
            addCriterion("south_to_name >=", value, "southToName");
            return (Criteria) this;
        }

        public Criteria andSouthToNameLessThan(String value) {
            addCriterion("south_to_name <", value, "southToName");
            return (Criteria) this;
        }

        public Criteria andSouthToNameLessThanOrEqualTo(String value) {
            addCriterion("south_to_name <=", value, "southToName");
            return (Criteria) this;
        }

        public Criteria andSouthToNameLike(String value) {
            addCriterion("south_to_name like", value, "southToName");
            return (Criteria) this;
        }

        public Criteria andSouthToNameNotLike(String value) {
            addCriterion("south_to_name not like", value, "southToName");
            return (Criteria) this;
        }

        public Criteria andSouthToNameIn(List<String> values) {
            addCriterion("south_to_name in", values, "southToName");
            return (Criteria) this;
        }

        public Criteria andSouthToNameNotIn(List<String> values) {
            addCriterion("south_to_name not in", values, "southToName");
            return (Criteria) this;
        }

        public Criteria andSouthToNameBetween(String value1, String value2) {
            addCriterion("south_to_name between", value1, value2, "southToName");
            return (Criteria) this;
        }

        public Criteria andSouthToNameNotBetween(String value1, String value2) {
            addCriterion("south_to_name not between", value1, value2, "southToName");
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

        public Criteria andWestToNameIsNull() {
            addCriterion("west_to_name is null");
            return (Criteria) this;
        }

        public Criteria andWestToNameIsNotNull() {
            addCriterion("west_to_name is not null");
            return (Criteria) this;
        }

        public Criteria andWestToNameEqualTo(String value) {
            addCriterion("west_to_name =", value, "westToName");
            return (Criteria) this;
        }

        public Criteria andWestToNameNotEqualTo(String value) {
            addCriterion("west_to_name <>", value, "westToName");
            return (Criteria) this;
        }

        public Criteria andWestToNameGreaterThan(String value) {
            addCriterion("west_to_name >", value, "westToName");
            return (Criteria) this;
        }

        public Criteria andWestToNameGreaterThanOrEqualTo(String value) {
            addCriterion("west_to_name >=", value, "westToName");
            return (Criteria) this;
        }

        public Criteria andWestToNameLessThan(String value) {
            addCriterion("west_to_name <", value, "westToName");
            return (Criteria) this;
        }

        public Criteria andWestToNameLessThanOrEqualTo(String value) {
            addCriterion("west_to_name <=", value, "westToName");
            return (Criteria) this;
        }

        public Criteria andWestToNameLike(String value) {
            addCriterion("west_to_name like", value, "westToName");
            return (Criteria) this;
        }

        public Criteria andWestToNameNotLike(String value) {
            addCriterion("west_to_name not like", value, "westToName");
            return (Criteria) this;
        }

        public Criteria andWestToNameIn(List<String> values) {
            addCriterion("west_to_name in", values, "westToName");
            return (Criteria) this;
        }

        public Criteria andWestToNameNotIn(List<String> values) {
            addCriterion("west_to_name not in", values, "westToName");
            return (Criteria) this;
        }

        public Criteria andWestToNameBetween(String value1, String value2) {
            addCriterion("west_to_name between", value1, value2, "westToName");
            return (Criteria) this;
        }

        public Criteria andWestToNameNotBetween(String value1, String value2) {
            addCriterion("west_to_name not between", value1, value2, "westToName");
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

        public Criteria andNorthToNameIsNull() {
            addCriterion("north_to_name is null");
            return (Criteria) this;
        }

        public Criteria andNorthToNameIsNotNull() {
            addCriterion("north_to_name is not null");
            return (Criteria) this;
        }

        public Criteria andNorthToNameEqualTo(String value) {
            addCriterion("north_to_name =", value, "northToName");
            return (Criteria) this;
        }

        public Criteria andNorthToNameNotEqualTo(String value) {
            addCriterion("north_to_name <>", value, "northToName");
            return (Criteria) this;
        }

        public Criteria andNorthToNameGreaterThan(String value) {
            addCriterion("north_to_name >", value, "northToName");
            return (Criteria) this;
        }

        public Criteria andNorthToNameGreaterThanOrEqualTo(String value) {
            addCriterion("north_to_name >=", value, "northToName");
            return (Criteria) this;
        }

        public Criteria andNorthToNameLessThan(String value) {
            addCriterion("north_to_name <", value, "northToName");
            return (Criteria) this;
        }

        public Criteria andNorthToNameLessThanOrEqualTo(String value) {
            addCriterion("north_to_name <=", value, "northToName");
            return (Criteria) this;
        }

        public Criteria andNorthToNameLike(String value) {
            addCriterion("north_to_name like", value, "northToName");
            return (Criteria) this;
        }

        public Criteria andNorthToNameNotLike(String value) {
            addCriterion("north_to_name not like", value, "northToName");
            return (Criteria) this;
        }

        public Criteria andNorthToNameIn(List<String> values) {
            addCriterion("north_to_name in", values, "northToName");
            return (Criteria) this;
        }

        public Criteria andNorthToNameNotIn(List<String> values) {
            addCriterion("north_to_name not in", values, "northToName");
            return (Criteria) this;
        }

        public Criteria andNorthToNameBetween(String value1, String value2) {
            addCriterion("north_to_name between", value1, value2, "northToName");
            return (Criteria) this;
        }

        public Criteria andNorthToNameNotBetween(String value1, String value2) {
            addCriterion("north_to_name not between", value1, value2, "northToName");
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

        public Criteria andAcquisitionTimeIsNull() {
            addCriterion("acquisition_time is null");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTimeIsNotNull() {
            addCriterion("acquisition_time is not null");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTimeEqualTo(Date value) {
            addCriterion("acquisition_time =", value, "acquisitionTime");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTimeNotEqualTo(Date value) {
            addCriterion("acquisition_time <>", value, "acquisitionTime");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTimeGreaterThan(Date value) {
            addCriterion("acquisition_time >", value, "acquisitionTime");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("acquisition_time >=", value, "acquisitionTime");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTimeLessThan(Date value) {
            addCriterion("acquisition_time <", value, "acquisitionTime");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTimeLessThanOrEqualTo(Date value) {
            addCriterion("acquisition_time <=", value, "acquisitionTime");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTimeIn(List<Date> values) {
            addCriterion("acquisition_time in", values, "acquisitionTime");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTimeNotIn(List<Date> values) {
            addCriterion("acquisition_time not in", values, "acquisitionTime");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTimeBetween(Date value1, Date value2) {
            addCriterion("acquisition_time between", value1, value2, "acquisitionTime");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTimeNotBetween(Date value1, Date value2) {
            addCriterion("acquisition_time not between", value1, value2, "acquisitionTime");
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

        public Criteria andCompatibleRatioTypeIsNull() {
            addCriterion("compatible_ratio_type is null");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioTypeIsNotNull() {
            addCriterion("compatible_ratio_type is not null");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioTypeEqualTo(String value) {
            addCriterion("compatible_ratio_type =", value, "compatibleRatioType");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioTypeNotEqualTo(String value) {
            addCriterion("compatible_ratio_type <>", value, "compatibleRatioType");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioTypeGreaterThan(String value) {
            addCriterion("compatible_ratio_type >", value, "compatibleRatioType");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioTypeGreaterThanOrEqualTo(String value) {
            addCriterion("compatible_ratio_type >=", value, "compatibleRatioType");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioTypeLessThan(String value) {
            addCriterion("compatible_ratio_type <", value, "compatibleRatioType");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioTypeLessThanOrEqualTo(String value) {
            addCriterion("compatible_ratio_type <=", value, "compatibleRatioType");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioTypeLike(String value) {
            addCriterion("compatible_ratio_type like", value, "compatibleRatioType");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioTypeNotLike(String value) {
            addCriterion("compatible_ratio_type not like", value, "compatibleRatioType");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioTypeIn(List<String> values) {
            addCriterion("compatible_ratio_type in", values, "compatibleRatioType");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioTypeNotIn(List<String> values) {
            addCriterion("compatible_ratio_type not in", values, "compatibleRatioType");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioTypeBetween(String value1, String value2) {
            addCriterion("compatible_ratio_type between", value1, value2, "compatibleRatioType");
            return (Criteria) this;
        }

        public Criteria andCompatibleRatioTypeNotBetween(String value1, String value2) {
            addCriterion("compatible_ratio_type not between", value1, value2, "compatibleRatioType");
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

        public Criteria andBuildingHeightLimitIsNull() {
            addCriterion("building_height_limit is null");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitIsNotNull() {
            addCriterion("building_height_limit is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitEqualTo(BigDecimal value) {
            addCriterion("building_height_limit =", value, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitNotEqualTo(BigDecimal value) {
            addCriterion("building_height_limit <>", value, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitGreaterThan(BigDecimal value) {
            addCriterion("building_height_limit >", value, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("building_height_limit >=", value, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitLessThan(BigDecimal value) {
            addCriterion("building_height_limit <", value, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("building_height_limit <=", value, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitIn(List<BigDecimal> values) {
            addCriterion("building_height_limit in", values, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitNotIn(List<BigDecimal> values) {
            addCriterion("building_height_limit not in", values, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("building_height_limit between", value1, value2, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("building_height_limit not between", value1, value2, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andInvestmentIntensityIsNull() {
            addCriterion("investment_intensity is null");
            return (Criteria) this;
        }

        public Criteria andInvestmentIntensityIsNotNull() {
            addCriterion("investment_intensity is not null");
            return (Criteria) this;
        }

        public Criteria andInvestmentIntensityEqualTo(BigDecimal value) {
            addCriterion("investment_intensity =", value, "investmentIntensity");
            return (Criteria) this;
        }

        public Criteria andInvestmentIntensityNotEqualTo(BigDecimal value) {
            addCriterion("investment_intensity <>", value, "investmentIntensity");
            return (Criteria) this;
        }

        public Criteria andInvestmentIntensityGreaterThan(BigDecimal value) {
            addCriterion("investment_intensity >", value, "investmentIntensity");
            return (Criteria) this;
        }

        public Criteria andInvestmentIntensityGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("investment_intensity >=", value, "investmentIntensity");
            return (Criteria) this;
        }

        public Criteria andInvestmentIntensityLessThan(BigDecimal value) {
            addCriterion("investment_intensity <", value, "investmentIntensity");
            return (Criteria) this;
        }

        public Criteria andInvestmentIntensityLessThanOrEqualTo(BigDecimal value) {
            addCriterion("investment_intensity <=", value, "investmentIntensity");
            return (Criteria) this;
        }

        public Criteria andInvestmentIntensityIn(List<BigDecimal> values) {
            addCriterion("investment_intensity in", values, "investmentIntensity");
            return (Criteria) this;
        }

        public Criteria andInvestmentIntensityNotIn(List<BigDecimal> values) {
            addCriterion("investment_intensity not in", values, "investmentIntensity");
            return (Criteria) this;
        }

        public Criteria andInvestmentIntensityBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("investment_intensity between", value1, value2, "investmentIntensity");
            return (Criteria) this;
        }

        public Criteria andInvestmentIntensityNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("investment_intensity not between", value1, value2, "investmentIntensity");
            return (Criteria) this;
        }

        public Criteria andSpecialRestrictionsIsNull() {
            addCriterion("special_restrictions is null");
            return (Criteria) this;
        }

        public Criteria andSpecialRestrictionsIsNotNull() {
            addCriterion("special_restrictions is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialRestrictionsEqualTo(String value) {
            addCriterion("special_restrictions =", value, "specialRestrictions");
            return (Criteria) this;
        }

        public Criteria andSpecialRestrictionsNotEqualTo(String value) {
            addCriterion("special_restrictions <>", value, "specialRestrictions");
            return (Criteria) this;
        }

        public Criteria andSpecialRestrictionsGreaterThan(String value) {
            addCriterion("special_restrictions >", value, "specialRestrictions");
            return (Criteria) this;
        }

        public Criteria andSpecialRestrictionsGreaterThanOrEqualTo(String value) {
            addCriterion("special_restrictions >=", value, "specialRestrictions");
            return (Criteria) this;
        }

        public Criteria andSpecialRestrictionsLessThan(String value) {
            addCriterion("special_restrictions <", value, "specialRestrictions");
            return (Criteria) this;
        }

        public Criteria andSpecialRestrictionsLessThanOrEqualTo(String value) {
            addCriterion("special_restrictions <=", value, "specialRestrictions");
            return (Criteria) this;
        }

        public Criteria andSpecialRestrictionsLike(String value) {
            addCriterion("special_restrictions like", value, "specialRestrictions");
            return (Criteria) this;
        }

        public Criteria andSpecialRestrictionsNotLike(String value) {
            addCriterion("special_restrictions not like", value, "specialRestrictions");
            return (Criteria) this;
        }

        public Criteria andSpecialRestrictionsIn(List<String> values) {
            addCriterion("special_restrictions in", values, "specialRestrictions");
            return (Criteria) this;
        }

        public Criteria andSpecialRestrictionsNotIn(List<String> values) {
            addCriterion("special_restrictions not in", values, "specialRestrictions");
            return (Criteria) this;
        }

        public Criteria andSpecialRestrictionsBetween(String value1, String value2) {
            addCriterion("special_restrictions between", value1, value2, "specialRestrictions");
            return (Criteria) this;
        }

        public Criteria andSpecialRestrictionsNotBetween(String value1, String value2) {
            addCriterion("special_restrictions not between", value1, value2, "specialRestrictions");
            return (Criteria) this;
        }

        public Criteria andPresentSituationLandUseIsNull() {
            addCriterion("present_situation_land_use is null");
            return (Criteria) this;
        }

        public Criteria andPresentSituationLandUseIsNotNull() {
            addCriterion("present_situation_land_use is not null");
            return (Criteria) this;
        }

        public Criteria andPresentSituationLandUseEqualTo(String value) {
            addCriterion("present_situation_land_use =", value, "presentSituationLandUse");
            return (Criteria) this;
        }

        public Criteria andPresentSituationLandUseNotEqualTo(String value) {
            addCriterion("present_situation_land_use <>", value, "presentSituationLandUse");
            return (Criteria) this;
        }

        public Criteria andPresentSituationLandUseGreaterThan(String value) {
            addCriterion("present_situation_land_use >", value, "presentSituationLandUse");
            return (Criteria) this;
        }

        public Criteria andPresentSituationLandUseGreaterThanOrEqualTo(String value) {
            addCriterion("present_situation_land_use >=", value, "presentSituationLandUse");
            return (Criteria) this;
        }

        public Criteria andPresentSituationLandUseLessThan(String value) {
            addCriterion("present_situation_land_use <", value, "presentSituationLandUse");
            return (Criteria) this;
        }

        public Criteria andPresentSituationLandUseLessThanOrEqualTo(String value) {
            addCriterion("present_situation_land_use <=", value, "presentSituationLandUse");
            return (Criteria) this;
        }

        public Criteria andPresentSituationLandUseLike(String value) {
            addCriterion("present_situation_land_use like", value, "presentSituationLandUse");
            return (Criteria) this;
        }

        public Criteria andPresentSituationLandUseNotLike(String value) {
            addCriterion("present_situation_land_use not like", value, "presentSituationLandUse");
            return (Criteria) this;
        }

        public Criteria andPresentSituationLandUseIn(List<String> values) {
            addCriterion("present_situation_land_use in", values, "presentSituationLandUse");
            return (Criteria) this;
        }

        public Criteria andPresentSituationLandUseNotIn(List<String> values) {
            addCriterion("present_situation_land_use not in", values, "presentSituationLandUse");
            return (Criteria) this;
        }

        public Criteria andPresentSituationLandUseBetween(String value1, String value2) {
            addCriterion("present_situation_land_use between", value1, value2, "presentSituationLandUse");
            return (Criteria) this;
        }

        public Criteria andPresentSituationLandUseNotBetween(String value1, String value2) {
            addCriterion("present_situation_land_use not between", value1, value2, "presentSituationLandUse");
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

        public Criteria andDevelopmentTimeIsNull() {
            addCriterion("development_time is null");
            return (Criteria) this;
        }

        public Criteria andDevelopmentTimeIsNotNull() {
            addCriterion("development_time is not null");
            return (Criteria) this;
        }

        public Criteria andDevelopmentTimeEqualTo(Date value) {
            addCriterion("development_time =", value, "developmentTime");
            return (Criteria) this;
        }

        public Criteria andDevelopmentTimeNotEqualTo(Date value) {
            addCriterion("development_time <>", value, "developmentTime");
            return (Criteria) this;
        }

        public Criteria andDevelopmentTimeGreaterThan(Date value) {
            addCriterion("development_time >", value, "developmentTime");
            return (Criteria) this;
        }

        public Criteria andDevelopmentTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("development_time >=", value, "developmentTime");
            return (Criteria) this;
        }

        public Criteria andDevelopmentTimeLessThan(Date value) {
            addCriterion("development_time <", value, "developmentTime");
            return (Criteria) this;
        }

        public Criteria andDevelopmentTimeLessThanOrEqualTo(Date value) {
            addCriterion("development_time <=", value, "developmentTime");
            return (Criteria) this;
        }

        public Criteria andDevelopmentTimeIn(List<Date> values) {
            addCriterion("development_time in", values, "developmentTime");
            return (Criteria) this;
        }

        public Criteria andDevelopmentTimeNotIn(List<Date> values) {
            addCriterion("development_time not in", values, "developmentTime");
            return (Criteria) this;
        }

        public Criteria andDevelopmentTimeBetween(Date value1, Date value2) {
            addCriterion("development_time between", value1, value2, "developmentTime");
            return (Criteria) this;
        }

        public Criteria andDevelopmentTimeNotBetween(Date value1, Date value2) {
            addCriterion("development_time not between", value1, value2, "developmentTime");
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
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * tb_basic_estate_land_state
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