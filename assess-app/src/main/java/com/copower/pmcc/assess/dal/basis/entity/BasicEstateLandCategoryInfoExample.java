package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicEstateLandCategoryInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicEstateLandCategoryInfoExample() {
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

        public Criteria andLandIdIsNull() {
            addCriterion("land_id is null");
            return (Criteria) this;
        }

        public Criteria andLandIdIsNotNull() {
            addCriterion("land_id is not null");
            return (Criteria) this;
        }

        public Criteria andLandIdEqualTo(Integer value) {
            addCriterion("land_id =", value, "landId");
            return (Criteria) this;
        }

        public Criteria andLandIdNotEqualTo(Integer value) {
            addCriterion("land_id <>", value, "landId");
            return (Criteria) this;
        }

        public Criteria andLandIdGreaterThan(Integer value) {
            addCriterion("land_id >", value, "landId");
            return (Criteria) this;
        }

        public Criteria andLandIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("land_id >=", value, "landId");
            return (Criteria) this;
        }

        public Criteria andLandIdLessThan(Integer value) {
            addCriterion("land_id <", value, "landId");
            return (Criteria) this;
        }

        public Criteria andLandIdLessThanOrEqualTo(Integer value) {
            addCriterion("land_id <=", value, "landId");
            return (Criteria) this;
        }

        public Criteria andLandIdIn(List<Integer> values) {
            addCriterion("land_id in", values, "landId");
            return (Criteria) this;
        }

        public Criteria andLandIdNotIn(List<Integer> values) {
            addCriterion("land_id not in", values, "landId");
            return (Criteria) this;
        }

        public Criteria andLandIdBetween(Integer value1, Integer value2) {
            addCriterion("land_id between", value1, value2, "landId");
            return (Criteria) this;
        }

        public Criteria andLandIdNotBetween(Integer value1, Integer value2) {
            addCriterion("land_id not between", value1, value2, "landId");
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

        public Criteria andLandLevelContentResultIsNull() {
            addCriterion("land_level_content_result is null");
            return (Criteria) this;
        }

        public Criteria andLandLevelContentResultIsNotNull() {
            addCriterion("land_level_content_result is not null");
            return (Criteria) this;
        }

        public Criteria andLandLevelContentResultEqualTo(String value) {
            addCriterion("land_level_content_result =", value, "landLevelContentResult");
            return (Criteria) this;
        }

        public Criteria andLandLevelContentResultNotEqualTo(String value) {
            addCriterion("land_level_content_result <>", value, "landLevelContentResult");
            return (Criteria) this;
        }

        public Criteria andLandLevelContentResultGreaterThan(String value) {
            addCriterion("land_level_content_result >", value, "landLevelContentResult");
            return (Criteria) this;
        }

        public Criteria andLandLevelContentResultGreaterThanOrEqualTo(String value) {
            addCriterion("land_level_content_result >=", value, "landLevelContentResult");
            return (Criteria) this;
        }

        public Criteria andLandLevelContentResultLessThan(String value) {
            addCriterion("land_level_content_result <", value, "landLevelContentResult");
            return (Criteria) this;
        }

        public Criteria andLandLevelContentResultLessThanOrEqualTo(String value) {
            addCriterion("land_level_content_result <=", value, "landLevelContentResult");
            return (Criteria) this;
        }

        public Criteria andLandLevelContentResultLike(String value) {
            addCriterion("land_level_content_result like", value, "landLevelContentResult");
            return (Criteria) this;
        }

        public Criteria andLandLevelContentResultNotLike(String value) {
            addCriterion("land_level_content_result not like", value, "landLevelContentResult");
            return (Criteria) this;
        }

        public Criteria andLandLevelContentResultIn(List<String> values) {
            addCriterion("land_level_content_result in", values, "landLevelContentResult");
            return (Criteria) this;
        }

        public Criteria andLandLevelContentResultNotIn(List<String> values) {
            addCriterion("land_level_content_result not in", values, "landLevelContentResult");
            return (Criteria) this;
        }

        public Criteria andLandLevelContentResultBetween(String value1, String value2) {
            addCriterion("land_level_content_result between", value1, value2, "landLevelContentResult");
            return (Criteria) this;
        }

        public Criteria andLandLevelContentResultNotBetween(String value1, String value2) {
            addCriterion("land_level_content_result not between", value1, value2, "landLevelContentResult");
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

        public Criteria andLandLevelNameIsNull() {
            addCriterion("land_level_name is null");
            return (Criteria) this;
        }

        public Criteria andLandLevelNameIsNotNull() {
            addCriterion("land_level_name is not null");
            return (Criteria) this;
        }

        public Criteria andLandLevelNameEqualTo(String value) {
            addCriterion("land_level_name =", value, "landLevelName");
            return (Criteria) this;
        }

        public Criteria andLandLevelNameNotEqualTo(String value) {
            addCriterion("land_level_name <>", value, "landLevelName");
            return (Criteria) this;
        }

        public Criteria andLandLevelNameGreaterThan(String value) {
            addCriterion("land_level_name >", value, "landLevelName");
            return (Criteria) this;
        }

        public Criteria andLandLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("land_level_name >=", value, "landLevelName");
            return (Criteria) this;
        }

        public Criteria andLandLevelNameLessThan(String value) {
            addCriterion("land_level_name <", value, "landLevelName");
            return (Criteria) this;
        }

        public Criteria andLandLevelNameLessThanOrEqualTo(String value) {
            addCriterion("land_level_name <=", value, "landLevelName");
            return (Criteria) this;
        }

        public Criteria andLandLevelNameLike(String value) {
            addCriterion("land_level_name like", value, "landLevelName");
            return (Criteria) this;
        }

        public Criteria andLandLevelNameNotLike(String value) {
            addCriterion("land_level_name not like", value, "landLevelName");
            return (Criteria) this;
        }

        public Criteria andLandLevelNameIn(List<String> values) {
            addCriterion("land_level_name in", values, "landLevelName");
            return (Criteria) this;
        }

        public Criteria andLandLevelNameNotIn(List<String> values) {
            addCriterion("land_level_name not in", values, "landLevelName");
            return (Criteria) this;
        }

        public Criteria andLandLevelNameBetween(String value1, String value2) {
            addCriterion("land_level_name between", value1, value2, "landLevelName");
            return (Criteria) this;
        }

        public Criteria andLandLevelNameNotBetween(String value1, String value2) {
            addCriterion("land_level_name not between", value1, value2, "landLevelName");
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