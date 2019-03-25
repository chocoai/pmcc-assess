package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicMatchingTrafficExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicMatchingTrafficExample() {
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

        public Criteria andNatureIsNull() {
            addCriterion("nature is null");
            return (Criteria) this;
        }

        public Criteria andNatureIsNotNull() {
            addCriterion("nature is not null");
            return (Criteria) this;
        }

        public Criteria andNatureEqualTo(Integer value) {
            addCriterion("nature =", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureNotEqualTo(Integer value) {
            addCriterion("nature <>", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureGreaterThan(Integer value) {
            addCriterion("nature >", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureGreaterThanOrEqualTo(Integer value) {
            addCriterion("nature >=", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureLessThan(Integer value) {
            addCriterion("nature <", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureLessThanOrEqualTo(Integer value) {
            addCriterion("nature <=", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureIn(List<Integer> values) {
            addCriterion("nature in", values, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureNotIn(List<Integer> values) {
            addCriterion("nature not in", values, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureBetween(Integer value1, Integer value2) {
            addCriterion("nature between", value1, value2, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureNotBetween(Integer value1, Integer value2) {
            addCriterion("nature not between", value1, value2, "nature");
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

        public Criteria andDistanceIsNull() {
            addCriterion("distance is null");
            return (Criteria) this;
        }

        public Criteria andDistanceIsNotNull() {
            addCriterion("distance is not null");
            return (Criteria) this;
        }

        public Criteria andDistanceEqualTo(Integer value) {
            addCriterion("distance =", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotEqualTo(Integer value) {
            addCriterion("distance <>", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceGreaterThan(Integer value) {
            addCriterion("distance >", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("distance >=", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLessThan(Integer value) {
            addCriterion("distance <", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLessThanOrEqualTo(Integer value) {
            addCriterion("distance <=", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceIn(List<Integer> values) {
            addCriterion("distance in", values, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotIn(List<Integer> values) {
            addCriterion("distance not in", values, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceBetween(Integer value1, Integer value2) {
            addCriterion("distance between", value1, value2, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotBetween(Integer value1, Integer value2) {
            addCriterion("distance not between", value1, value2, "distance");
            return (Criteria) this;
        }

        public Criteria andLineNameIsNull() {
            addCriterion("line_name is null");
            return (Criteria) this;
        }

        public Criteria andLineNameIsNotNull() {
            addCriterion("line_name is not null");
            return (Criteria) this;
        }

        public Criteria andLineNameEqualTo(String value) {
            addCriterion("line_name =", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameNotEqualTo(String value) {
            addCriterion("line_name <>", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameGreaterThan(String value) {
            addCriterion("line_name >", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameGreaterThanOrEqualTo(String value) {
            addCriterion("line_name >=", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameLessThan(String value) {
            addCriterion("line_name <", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameLessThanOrEqualTo(String value) {
            addCriterion("line_name <=", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameLike(String value) {
            addCriterion("line_name like", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameNotLike(String value) {
            addCriterion("line_name not like", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameIn(List<String> values) {
            addCriterion("line_name in", values, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameNotIn(List<String> values) {
            addCriterion("line_name not in", values, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameBetween(String value1, String value2) {
            addCriterion("line_name between", value1, value2, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameNotBetween(String value1, String value2) {
            addCriterion("line_name not between", value1, value2, "lineName");
            return (Criteria) this;
        }

        public Criteria andTheLineIsNull() {
            addCriterion("the_line is null");
            return (Criteria) this;
        }

        public Criteria andTheLineIsNotNull() {
            addCriterion("the_line is not null");
            return (Criteria) this;
        }

        public Criteria andTheLineEqualTo(String value) {
            addCriterion("the_line =", value, "theLine");
            return (Criteria) this;
        }

        public Criteria andTheLineNotEqualTo(String value) {
            addCriterion("the_line <>", value, "theLine");
            return (Criteria) this;
        }

        public Criteria andTheLineGreaterThan(String value) {
            addCriterion("the_line >", value, "theLine");
            return (Criteria) this;
        }

        public Criteria andTheLineGreaterThanOrEqualTo(String value) {
            addCriterion("the_line >=", value, "theLine");
            return (Criteria) this;
        }

        public Criteria andTheLineLessThan(String value) {
            addCriterion("the_line <", value, "theLine");
            return (Criteria) this;
        }

        public Criteria andTheLineLessThanOrEqualTo(String value) {
            addCriterion("the_line <=", value, "theLine");
            return (Criteria) this;
        }

        public Criteria andTheLineLike(String value) {
            addCriterion("the_line like", value, "theLine");
            return (Criteria) this;
        }

        public Criteria andTheLineNotLike(String value) {
            addCriterion("the_line not like", value, "theLine");
            return (Criteria) this;
        }

        public Criteria andTheLineIn(List<String> values) {
            addCriterion("the_line in", values, "theLine");
            return (Criteria) this;
        }

        public Criteria andTheLineNotIn(List<String> values) {
            addCriterion("the_line not in", values, "theLine");
            return (Criteria) this;
        }

        public Criteria andTheLineBetween(String value1, String value2) {
            addCriterion("the_line between", value1, value2, "theLine");
            return (Criteria) this;
        }

        public Criteria andTheLineNotBetween(String value1, String value2) {
            addCriterion("the_line not between", value1, value2, "theLine");
            return (Criteria) this;
        }

        public Criteria andLimitSpeedIsNull() {
            addCriterion("limit_speed is null");
            return (Criteria) this;
        }

        public Criteria andLimitSpeedIsNotNull() {
            addCriterion("limit_speed is not null");
            return (Criteria) this;
        }

        public Criteria andLimitSpeedEqualTo(String value) {
            addCriterion("limit_speed =", value, "limitSpeed");
            return (Criteria) this;
        }

        public Criteria andLimitSpeedNotEqualTo(String value) {
            addCriterion("limit_speed <>", value, "limitSpeed");
            return (Criteria) this;
        }

        public Criteria andLimitSpeedGreaterThan(String value) {
            addCriterion("limit_speed >", value, "limitSpeed");
            return (Criteria) this;
        }

        public Criteria andLimitSpeedGreaterThanOrEqualTo(String value) {
            addCriterion("limit_speed >=", value, "limitSpeed");
            return (Criteria) this;
        }

        public Criteria andLimitSpeedLessThan(String value) {
            addCriterion("limit_speed <", value, "limitSpeed");
            return (Criteria) this;
        }

        public Criteria andLimitSpeedLessThanOrEqualTo(String value) {
            addCriterion("limit_speed <=", value, "limitSpeed");
            return (Criteria) this;
        }

        public Criteria andLimitSpeedLike(String value) {
            addCriterion("limit_speed like", value, "limitSpeed");
            return (Criteria) this;
        }

        public Criteria andLimitSpeedNotLike(String value) {
            addCriterion("limit_speed not like", value, "limitSpeed");
            return (Criteria) this;
        }

        public Criteria andLimitSpeedIn(List<String> values) {
            addCriterion("limit_speed in", values, "limitSpeed");
            return (Criteria) this;
        }

        public Criteria andLimitSpeedNotIn(List<String> values) {
            addCriterion("limit_speed not in", values, "limitSpeed");
            return (Criteria) this;
        }

        public Criteria andLimitSpeedBetween(String value1, String value2) {
            addCriterion("limit_speed between", value1, value2, "limitSpeed");
            return (Criteria) this;
        }

        public Criteria andLimitSpeedNotBetween(String value1, String value2) {
            addCriterion("limit_speed not between", value1, value2, "limitSpeed");
            return (Criteria) this;
        }

        public Criteria andLimitTimeIsNull() {
            addCriterion("limit_time is null");
            return (Criteria) this;
        }

        public Criteria andLimitTimeIsNotNull() {
            addCriterion("limit_time is not null");
            return (Criteria) this;
        }

        public Criteria andLimitTimeEqualTo(String value) {
            addCriterion("limit_time =", value, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeNotEqualTo(String value) {
            addCriterion("limit_time <>", value, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeGreaterThan(String value) {
            addCriterion("limit_time >", value, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeGreaterThanOrEqualTo(String value) {
            addCriterion("limit_time >=", value, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeLessThan(String value) {
            addCriterion("limit_time <", value, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeLessThanOrEqualTo(String value) {
            addCriterion("limit_time <=", value, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeLike(String value) {
            addCriterion("limit_time like", value, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeNotLike(String value) {
            addCriterion("limit_time not like", value, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeIn(List<String> values) {
            addCriterion("limit_time in", values, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeNotIn(List<String> values) {
            addCriterion("limit_time not in", values, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeBetween(String value1, String value2) {
            addCriterion("limit_time between", value1, value2, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeNotBetween(String value1, String value2) {
            addCriterion("limit_time not between", value1, value2, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitSpeialIsNull() {
            addCriterion("limit_speial is null");
            return (Criteria) this;
        }

        public Criteria andLimitSpeialIsNotNull() {
            addCriterion("limit_speial is not null");
            return (Criteria) this;
        }

        public Criteria andLimitSpeialEqualTo(Integer value) {
            addCriterion("limit_speial =", value, "limitSpeial");
            return (Criteria) this;
        }

        public Criteria andLimitSpeialNotEqualTo(Integer value) {
            addCriterion("limit_speial <>", value, "limitSpeial");
            return (Criteria) this;
        }

        public Criteria andLimitSpeialGreaterThan(Integer value) {
            addCriterion("limit_speial >", value, "limitSpeial");
            return (Criteria) this;
        }

        public Criteria andLimitSpeialGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_speial >=", value, "limitSpeial");
            return (Criteria) this;
        }

        public Criteria andLimitSpeialLessThan(Integer value) {
            addCriterion("limit_speial <", value, "limitSpeial");
            return (Criteria) this;
        }

        public Criteria andLimitSpeialLessThanOrEqualTo(Integer value) {
            addCriterion("limit_speial <=", value, "limitSpeial");
            return (Criteria) this;
        }

        public Criteria andLimitSpeialIn(List<Integer> values) {
            addCriterion("limit_speial in", values, "limitSpeial");
            return (Criteria) this;
        }

        public Criteria andLimitSpeialNotIn(List<Integer> values) {
            addCriterion("limit_speial not in", values, "limitSpeial");
            return (Criteria) this;
        }

        public Criteria andLimitSpeialBetween(Integer value1, Integer value2) {
            addCriterion("limit_speial between", value1, value2, "limitSpeial");
            return (Criteria) this;
        }

        public Criteria andLimitSpeialNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_speial not between", value1, value2, "limitSpeial");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(Boolean value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(Boolean value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(Boolean value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(Boolean value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Boolean> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Boolean> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andCostStandardIsNull() {
            addCriterion("cost_standard is null");
            return (Criteria) this;
        }

        public Criteria andCostStandardIsNotNull() {
            addCriterion("cost_standard is not null");
            return (Criteria) this;
        }

        public Criteria andCostStandardEqualTo(String value) {
            addCriterion("cost_standard =", value, "costStandard");
            return (Criteria) this;
        }

        public Criteria andCostStandardNotEqualTo(String value) {
            addCriterion("cost_standard <>", value, "costStandard");
            return (Criteria) this;
        }

        public Criteria andCostStandardGreaterThan(String value) {
            addCriterion("cost_standard >", value, "costStandard");
            return (Criteria) this;
        }

        public Criteria andCostStandardGreaterThanOrEqualTo(String value) {
            addCriterion("cost_standard >=", value, "costStandard");
            return (Criteria) this;
        }

        public Criteria andCostStandardLessThan(String value) {
            addCriterion("cost_standard <", value, "costStandard");
            return (Criteria) this;
        }

        public Criteria andCostStandardLessThanOrEqualTo(String value) {
            addCriterion("cost_standard <=", value, "costStandard");
            return (Criteria) this;
        }

        public Criteria andCostStandardLike(String value) {
            addCriterion("cost_standard like", value, "costStandard");
            return (Criteria) this;
        }

        public Criteria andCostStandardNotLike(String value) {
            addCriterion("cost_standard not like", value, "costStandard");
            return (Criteria) this;
        }

        public Criteria andCostStandardIn(List<String> values) {
            addCriterion("cost_standard in", values, "costStandard");
            return (Criteria) this;
        }

        public Criteria andCostStandardNotIn(List<String> values) {
            addCriterion("cost_standard not in", values, "costStandard");
            return (Criteria) this;
        }

        public Criteria andCostStandardBetween(String value1, String value2) {
            addCriterion("cost_standard between", value1, value2, "costStandard");
            return (Criteria) this;
        }

        public Criteria andCostStandardNotBetween(String value1, String value2) {
            addCriterion("cost_standard not between", value1, value2, "costStandard");
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