package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicUnitHuxingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicUnitHuxingExample() {
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

        public Criteria andUnitIdIsNull() {
            addCriterion("unit_id is null");
            return (Criteria) this;
        }

        public Criteria andUnitIdIsNotNull() {
            addCriterion("unit_id is not null");
            return (Criteria) this;
        }

        public Criteria andUnitIdEqualTo(Integer value) {
            addCriterion("unit_id =", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotEqualTo(Integer value) {
            addCriterion("unit_id <>", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdGreaterThan(Integer value) {
            addCriterion("unit_id >", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("unit_id >=", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdLessThan(Integer value) {
            addCriterion("unit_id <", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdLessThanOrEqualTo(Integer value) {
            addCriterion("unit_id <=", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdIn(List<Integer> values) {
            addCriterion("unit_id in", values, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotIn(List<Integer> values) {
            addCriterion("unit_id not in", values, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdBetween(Integer value1, Integer value2) {
            addCriterion("unit_id between", value1, value2, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotBetween(Integer value1, Integer value2) {
            addCriterion("unit_id not between", value1, value2, "unitId");
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

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andSpanLengthIsNull() {
            addCriterion("span_length is null");
            return (Criteria) this;
        }

        public Criteria andSpanLengthIsNotNull() {
            addCriterion("span_length is not null");
            return (Criteria) this;
        }

        public Criteria andSpanLengthEqualTo(BigDecimal value) {
            addCriterion("span_length =", value, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthNotEqualTo(BigDecimal value) {
            addCriterion("span_length <>", value, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthGreaterThan(BigDecimal value) {
            addCriterion("span_length >", value, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("span_length >=", value, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthLessThan(BigDecimal value) {
            addCriterion("span_length <", value, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthLessThanOrEqualTo(BigDecimal value) {
            addCriterion("span_length <=", value, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthIn(List<BigDecimal> values) {
            addCriterion("span_length in", values, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthNotIn(List<BigDecimal> values) {
            addCriterion("span_length not in", values, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("span_length between", value1, value2, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("span_length not between", value1, value2, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanWidthIsNull() {
            addCriterion("span_width is null");
            return (Criteria) this;
        }

        public Criteria andSpanWidthIsNotNull() {
            addCriterion("span_width is not null");
            return (Criteria) this;
        }

        public Criteria andSpanWidthEqualTo(Integer value) {
            addCriterion("span_width =", value, "spanWidth");
            return (Criteria) this;
        }

        public Criteria andSpanWidthNotEqualTo(Integer value) {
            addCriterion("span_width <>", value, "spanWidth");
            return (Criteria) this;
        }

        public Criteria andSpanWidthGreaterThan(Integer value) {
            addCriterion("span_width >", value, "spanWidth");
            return (Criteria) this;
        }

        public Criteria andSpanWidthGreaterThanOrEqualTo(Integer value) {
            addCriterion("span_width >=", value, "spanWidth");
            return (Criteria) this;
        }

        public Criteria andSpanWidthLessThan(Integer value) {
            addCriterion("span_width <", value, "spanWidth");
            return (Criteria) this;
        }

        public Criteria andSpanWidthLessThanOrEqualTo(Integer value) {
            addCriterion("span_width <=", value, "spanWidth");
            return (Criteria) this;
        }

        public Criteria andSpanWidthIn(List<Integer> values) {
            addCriterion("span_width in", values, "spanWidth");
            return (Criteria) this;
        }

        public Criteria andSpanWidthNotIn(List<Integer> values) {
            addCriterion("span_width not in", values, "spanWidth");
            return (Criteria) this;
        }

        public Criteria andSpanWidthBetween(Integer value1, Integer value2) {
            addCriterion("span_width between", value1, value2, "spanWidth");
            return (Criteria) this;
        }

        public Criteria andSpanWidthNotBetween(Integer value1, Integer value2) {
            addCriterion("span_width not between", value1, value2, "spanWidth");
            return (Criteria) this;
        }

        public Criteria andSpanNumberIsNull() {
            addCriterion("span_number is null");
            return (Criteria) this;
        }

        public Criteria andSpanNumberIsNotNull() {
            addCriterion("span_number is not null");
            return (Criteria) this;
        }

        public Criteria andSpanNumberEqualTo(BigDecimal value) {
            addCriterion("span_number =", value, "spanNumber");
            return (Criteria) this;
        }

        public Criteria andSpanNumberNotEqualTo(BigDecimal value) {
            addCriterion("span_number <>", value, "spanNumber");
            return (Criteria) this;
        }

        public Criteria andSpanNumberGreaterThan(BigDecimal value) {
            addCriterion("span_number >", value, "spanNumber");
            return (Criteria) this;
        }

        public Criteria andSpanNumberGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("span_number >=", value, "spanNumber");
            return (Criteria) this;
        }

        public Criteria andSpanNumberLessThan(BigDecimal value) {
            addCriterion("span_number <", value, "spanNumber");
            return (Criteria) this;
        }

        public Criteria andSpanNumberLessThanOrEqualTo(BigDecimal value) {
            addCriterion("span_number <=", value, "spanNumber");
            return (Criteria) this;
        }

        public Criteria andSpanNumberIn(List<BigDecimal> values) {
            addCriterion("span_number in", values, "spanNumber");
            return (Criteria) this;
        }

        public Criteria andSpanNumberNotIn(List<BigDecimal> values) {
            addCriterion("span_number not in", values, "spanNumber");
            return (Criteria) this;
        }

        public Criteria andSpanNumberBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("span_number between", value1, value2, "spanNumber");
            return (Criteria) this;
        }

        public Criteria andSpanNumberNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("span_number not between", value1, value2, "spanNumber");
            return (Criteria) this;
        }

        public Criteria andBayIsNull() {
            addCriterion("bay is null");
            return (Criteria) this;
        }

        public Criteria andBayIsNotNull() {
            addCriterion("bay is not null");
            return (Criteria) this;
        }

        public Criteria andBayEqualTo(BigDecimal value) {
            addCriterion("bay =", value, "bay");
            return (Criteria) this;
        }

        public Criteria andBayNotEqualTo(BigDecimal value) {
            addCriterion("bay <>", value, "bay");
            return (Criteria) this;
        }

        public Criteria andBayGreaterThan(BigDecimal value) {
            addCriterion("bay >", value, "bay");
            return (Criteria) this;
        }

        public Criteria andBayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bay >=", value, "bay");
            return (Criteria) this;
        }

        public Criteria andBayLessThan(BigDecimal value) {
            addCriterion("bay <", value, "bay");
            return (Criteria) this;
        }

        public Criteria andBayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bay <=", value, "bay");
            return (Criteria) this;
        }

        public Criteria andBayIn(List<BigDecimal> values) {
            addCriterion("bay in", values, "bay");
            return (Criteria) this;
        }

        public Criteria andBayNotIn(List<BigDecimal> values) {
            addCriterion("bay not in", values, "bay");
            return (Criteria) this;
        }

        public Criteria andBayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bay between", value1, value2, "bay");
            return (Criteria) this;
        }

        public Criteria andBayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bay not between", value1, value2, "bay");
            return (Criteria) this;
        }

        public Criteria andDeepIsNull() {
            addCriterion("deep is null");
            return (Criteria) this;
        }

        public Criteria andDeepIsNotNull() {
            addCriterion("deep is not null");
            return (Criteria) this;
        }

        public Criteria andDeepEqualTo(BigDecimal value) {
            addCriterion("deep =", value, "deep");
            return (Criteria) this;
        }

        public Criteria andDeepNotEqualTo(BigDecimal value) {
            addCriterion("deep <>", value, "deep");
            return (Criteria) this;
        }

        public Criteria andDeepGreaterThan(BigDecimal value) {
            addCriterion("deep >", value, "deep");
            return (Criteria) this;
        }

        public Criteria andDeepGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deep >=", value, "deep");
            return (Criteria) this;
        }

        public Criteria andDeepLessThan(BigDecimal value) {
            addCriterion("deep <", value, "deep");
            return (Criteria) this;
        }

        public Criteria andDeepLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deep <=", value, "deep");
            return (Criteria) this;
        }

        public Criteria andDeepIn(List<BigDecimal> values) {
            addCriterion("deep in", values, "deep");
            return (Criteria) this;
        }

        public Criteria andDeepNotIn(List<BigDecimal> values) {
            addCriterion("deep not in", values, "deep");
            return (Criteria) this;
        }

        public Criteria andDeepBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deep between", value1, value2, "deep");
            return (Criteria) this;
        }

        public Criteria andDeepNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deep not between", value1, value2, "deep");
            return (Criteria) this;
        }

        public Criteria andHouseCategoryIsNull() {
            addCriterion("house_category is null");
            return (Criteria) this;
        }

        public Criteria andHouseCategoryIsNotNull() {
            addCriterion("house_category is not null");
            return (Criteria) this;
        }

        public Criteria andHouseCategoryEqualTo(String value) {
            addCriterion("house_category =", value, "houseCategory");
            return (Criteria) this;
        }

        public Criteria andHouseCategoryNotEqualTo(String value) {
            addCriterion("house_category <>", value, "houseCategory");
            return (Criteria) this;
        }

        public Criteria andHouseCategoryGreaterThan(String value) {
            addCriterion("house_category >", value, "houseCategory");
            return (Criteria) this;
        }

        public Criteria andHouseCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("house_category >=", value, "houseCategory");
            return (Criteria) this;
        }

        public Criteria andHouseCategoryLessThan(String value) {
            addCriterion("house_category <", value, "houseCategory");
            return (Criteria) this;
        }

        public Criteria andHouseCategoryLessThanOrEqualTo(String value) {
            addCriterion("house_category <=", value, "houseCategory");
            return (Criteria) this;
        }

        public Criteria andHouseCategoryLike(String value) {
            addCriterion("house_category like", value, "houseCategory");
            return (Criteria) this;
        }

        public Criteria andHouseCategoryNotLike(String value) {
            addCriterion("house_category not like", value, "houseCategory");
            return (Criteria) this;
        }

        public Criteria andHouseCategoryIn(List<String> values) {
            addCriterion("house_category in", values, "houseCategory");
            return (Criteria) this;
        }

        public Criteria andHouseCategoryNotIn(List<String> values) {
            addCriterion("house_category not in", values, "houseCategory");
            return (Criteria) this;
        }

        public Criteria andHouseCategoryBetween(String value1, String value2) {
            addCriterion("house_category between", value1, value2, "houseCategory");
            return (Criteria) this;
        }

        public Criteria andHouseCategoryNotBetween(String value1, String value2) {
            addCriterion("house_category not between", value1, value2, "houseCategory");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
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

        public Criteria andSpatialDistributionIsNull() {
            addCriterion("spatial_distribution is null");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionIsNotNull() {
            addCriterion("spatial_distribution is not null");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionEqualTo(String value) {
            addCriterion("spatial_distribution =", value, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionNotEqualTo(String value) {
            addCriterion("spatial_distribution <>", value, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionGreaterThan(String value) {
            addCriterion("spatial_distribution >", value, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionGreaterThanOrEqualTo(String value) {
            addCriterion("spatial_distribution >=", value, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionLessThan(String value) {
            addCriterion("spatial_distribution <", value, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionLessThanOrEqualTo(String value) {
            addCriterion("spatial_distribution <=", value, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionLike(String value) {
            addCriterion("spatial_distribution like", value, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionNotLike(String value) {
            addCriterion("spatial_distribution not like", value, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionIn(List<String> values) {
            addCriterion("spatial_distribution in", values, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionNotIn(List<String> values) {
            addCriterion("spatial_distribution not in", values, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionBetween(String value1, String value2) {
            addCriterion("spatial_distribution between", value1, value2, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionNotBetween(String value1, String value2) {
            addCriterion("spatial_distribution not between", value1, value2, "spatialDistribution");
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