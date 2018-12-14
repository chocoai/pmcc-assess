package com.copower.pmcc.assess.dal.basic.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicHouseRoomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicHouseRoomExample() {
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

        public Criteria andHouseIdIsNull() {
            addCriterion("house_id is null");
            return (Criteria) this;
        }

        public Criteria andHouseIdIsNotNull() {
            addCriterion("house_id is not null");
            return (Criteria) this;
        }

        public Criteria andHouseIdEqualTo(Integer value) {
            addCriterion("house_id =", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotEqualTo(Integer value) {
            addCriterion("house_id <>", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdGreaterThan(Integer value) {
            addCriterion("house_id >", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("house_id >=", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdLessThan(Integer value) {
            addCriterion("house_id <", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("house_id <=", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdIn(List<Integer> values) {
            addCriterion("house_id in", values, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotIn(List<Integer> values) {
            addCriterion("house_id not in", values, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdBetween(Integer value1, Integer value2) {
            addCriterion("house_id between", value1, value2, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("house_id not between", value1, value2, "houseId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIsNull() {
            addCriterion("room_type is null");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIsNotNull() {
            addCriterion("room_type is not null");
            return (Criteria) this;
        }

        public Criteria andRoomTypeEqualTo(Integer value) {
            addCriterion("room_type =", value, "roomType");
            return (Criteria) this;
        }

        public Criteria andRoomTypeNotEqualTo(Integer value) {
            addCriterion("room_type <>", value, "roomType");
            return (Criteria) this;
        }

        public Criteria andRoomTypeGreaterThan(Integer value) {
            addCriterion("room_type >", value, "roomType");
            return (Criteria) this;
        }

        public Criteria andRoomTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("room_type >=", value, "roomType");
            return (Criteria) this;
        }

        public Criteria andRoomTypeLessThan(Integer value) {
            addCriterion("room_type <", value, "roomType");
            return (Criteria) this;
        }

        public Criteria andRoomTypeLessThanOrEqualTo(Integer value) {
            addCriterion("room_type <=", value, "roomType");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIn(List<Integer> values) {
            addCriterion("room_type in", values, "roomType");
            return (Criteria) this;
        }

        public Criteria andRoomTypeNotIn(List<Integer> values) {
            addCriterion("room_type not in", values, "roomType");
            return (Criteria) this;
        }

        public Criteria andRoomTypeBetween(Integer value1, Integer value2) {
            addCriterion("room_type between", value1, value2, "roomType");
            return (Criteria) this;
        }

        public Criteria andRoomTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("room_type not between", value1, value2, "roomType");
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

        public Criteria andAerationIsNull() {
            addCriterion("aeration is null");
            return (Criteria) this;
        }

        public Criteria andAerationIsNotNull() {
            addCriterion("aeration is not null");
            return (Criteria) this;
        }

        public Criteria andAerationEqualTo(String value) {
            addCriterion("aeration =", value, "aeration");
            return (Criteria) this;
        }

        public Criteria andAerationNotEqualTo(String value) {
            addCriterion("aeration <>", value, "aeration");
            return (Criteria) this;
        }

        public Criteria andAerationGreaterThan(String value) {
            addCriterion("aeration >", value, "aeration");
            return (Criteria) this;
        }

        public Criteria andAerationGreaterThanOrEqualTo(String value) {
            addCriterion("aeration >=", value, "aeration");
            return (Criteria) this;
        }

        public Criteria andAerationLessThan(String value) {
            addCriterion("aeration <", value, "aeration");
            return (Criteria) this;
        }

        public Criteria andAerationLessThanOrEqualTo(String value) {
            addCriterion("aeration <=", value, "aeration");
            return (Criteria) this;
        }

        public Criteria andAerationLike(String value) {
            addCriterion("aeration like", value, "aeration");
            return (Criteria) this;
        }

        public Criteria andAerationNotLike(String value) {
            addCriterion("aeration not like", value, "aeration");
            return (Criteria) this;
        }

        public Criteria andAerationIn(List<String> values) {
            addCriterion("aeration in", values, "aeration");
            return (Criteria) this;
        }

        public Criteria andAerationNotIn(List<String> values) {
            addCriterion("aeration not in", values, "aeration");
            return (Criteria) this;
        }

        public Criteria andAerationBetween(String value1, String value2) {
            addCriterion("aeration between", value1, value2, "aeration");
            return (Criteria) this;
        }

        public Criteria andAerationNotBetween(String value1, String value2) {
            addCriterion("aeration not between", value1, value2, "aeration");
            return (Criteria) this;
        }

        public Criteria andIlluminationIsNull() {
            addCriterion("Illumination is null");
            return (Criteria) this;
        }

        public Criteria andIlluminationIsNotNull() {
            addCriterion("Illumination is not null");
            return (Criteria) this;
        }

        public Criteria andIlluminationEqualTo(String value) {
            addCriterion("Illumination =", value, "illumination");
            return (Criteria) this;
        }

        public Criteria andIlluminationNotEqualTo(String value) {
            addCriterion("Illumination <>", value, "illumination");
            return (Criteria) this;
        }

        public Criteria andIlluminationGreaterThan(String value) {
            addCriterion("Illumination >", value, "illumination");
            return (Criteria) this;
        }

        public Criteria andIlluminationGreaterThanOrEqualTo(String value) {
            addCriterion("Illumination >=", value, "illumination");
            return (Criteria) this;
        }

        public Criteria andIlluminationLessThan(String value) {
            addCriterion("Illumination <", value, "illumination");
            return (Criteria) this;
        }

        public Criteria andIlluminationLessThanOrEqualTo(String value) {
            addCriterion("Illumination <=", value, "illumination");
            return (Criteria) this;
        }

        public Criteria andIlluminationLike(String value) {
            addCriterion("Illumination like", value, "illumination");
            return (Criteria) this;
        }

        public Criteria andIlluminationNotLike(String value) {
            addCriterion("Illumination not like", value, "illumination");
            return (Criteria) this;
        }

        public Criteria andIlluminationIn(List<String> values) {
            addCriterion("Illumination in", values, "illumination");
            return (Criteria) this;
        }

        public Criteria andIlluminationNotIn(List<String> values) {
            addCriterion("Illumination not in", values, "illumination");
            return (Criteria) this;
        }

        public Criteria andIlluminationBetween(String value1, String value2) {
            addCriterion("Illumination between", value1, value2, "illumination");
            return (Criteria) this;
        }

        public Criteria andIlluminationNotBetween(String value1, String value2) {
            addCriterion("Illumination not between", value1, value2, "illumination");
            return (Criteria) this;
        }

        public Criteria andSoundInsulationIsNull() {
            addCriterion("sound_insulation is null");
            return (Criteria) this;
        }

        public Criteria andSoundInsulationIsNotNull() {
            addCriterion("sound_insulation is not null");
            return (Criteria) this;
        }

        public Criteria andSoundInsulationEqualTo(String value) {
            addCriterion("sound_insulation =", value, "soundInsulation");
            return (Criteria) this;
        }

        public Criteria andSoundInsulationNotEqualTo(String value) {
            addCriterion("sound_insulation <>", value, "soundInsulation");
            return (Criteria) this;
        }

        public Criteria andSoundInsulationGreaterThan(String value) {
            addCriterion("sound_insulation >", value, "soundInsulation");
            return (Criteria) this;
        }

        public Criteria andSoundInsulationGreaterThanOrEqualTo(String value) {
            addCriterion("sound_insulation >=", value, "soundInsulation");
            return (Criteria) this;
        }

        public Criteria andSoundInsulationLessThan(String value) {
            addCriterion("sound_insulation <", value, "soundInsulation");
            return (Criteria) this;
        }

        public Criteria andSoundInsulationLessThanOrEqualTo(String value) {
            addCriterion("sound_insulation <=", value, "soundInsulation");
            return (Criteria) this;
        }

        public Criteria andSoundInsulationLike(String value) {
            addCriterion("sound_insulation like", value, "soundInsulation");
            return (Criteria) this;
        }

        public Criteria andSoundInsulationNotLike(String value) {
            addCriterion("sound_insulation not like", value, "soundInsulation");
            return (Criteria) this;
        }

        public Criteria andSoundInsulationIn(List<String> values) {
            addCriterion("sound_insulation in", values, "soundInsulation");
            return (Criteria) this;
        }

        public Criteria andSoundInsulationNotIn(List<String> values) {
            addCriterion("sound_insulation not in", values, "soundInsulation");
            return (Criteria) this;
        }

        public Criteria andSoundInsulationBetween(String value1, String value2) {
            addCriterion("sound_insulation between", value1, value2, "soundInsulation");
            return (Criteria) this;
        }

        public Criteria andSoundInsulationNotBetween(String value1, String value2) {
            addCriterion("sound_insulation not between", value1, value2, "soundInsulation");
            return (Criteria) this;
        }

        public Criteria andSunshineIsNull() {
            addCriterion("sunshine is null");
            return (Criteria) this;
        }

        public Criteria andSunshineIsNotNull() {
            addCriterion("sunshine is not null");
            return (Criteria) this;
        }

        public Criteria andSunshineEqualTo(String value) {
            addCriterion("sunshine =", value, "sunshine");
            return (Criteria) this;
        }

        public Criteria andSunshineNotEqualTo(String value) {
            addCriterion("sunshine <>", value, "sunshine");
            return (Criteria) this;
        }

        public Criteria andSunshineGreaterThan(String value) {
            addCriterion("sunshine >", value, "sunshine");
            return (Criteria) this;
        }

        public Criteria andSunshineGreaterThanOrEqualTo(String value) {
            addCriterion("sunshine >=", value, "sunshine");
            return (Criteria) this;
        }

        public Criteria andSunshineLessThan(String value) {
            addCriterion("sunshine <", value, "sunshine");
            return (Criteria) this;
        }

        public Criteria andSunshineLessThanOrEqualTo(String value) {
            addCriterion("sunshine <=", value, "sunshine");
            return (Criteria) this;
        }

        public Criteria andSunshineLike(String value) {
            addCriterion("sunshine like", value, "sunshine");
            return (Criteria) this;
        }

        public Criteria andSunshineNotLike(String value) {
            addCriterion("sunshine not like", value, "sunshine");
            return (Criteria) this;
        }

        public Criteria andSunshineIn(List<String> values) {
            addCriterion("sunshine in", values, "sunshine");
            return (Criteria) this;
        }

        public Criteria andSunshineNotIn(List<String> values) {
            addCriterion("sunshine not in", values, "sunshine");
            return (Criteria) this;
        }

        public Criteria andSunshineBetween(String value1, String value2) {
            addCriterion("sunshine between", value1, value2, "sunshine");
            return (Criteria) this;
        }

        public Criteria andSunshineNotBetween(String value1, String value2) {
            addCriterion("sunshine not between", value1, value2, "sunshine");
            return (Criteria) this;
        }

        public Criteria andLightingIsNull() {
            addCriterion("lighting is null");
            return (Criteria) this;
        }

        public Criteria andLightingIsNotNull() {
            addCriterion("lighting is not null");
            return (Criteria) this;
        }

        public Criteria andLightingEqualTo(String value) {
            addCriterion("lighting =", value, "lighting");
            return (Criteria) this;
        }

        public Criteria andLightingNotEqualTo(String value) {
            addCriterion("lighting <>", value, "lighting");
            return (Criteria) this;
        }

        public Criteria andLightingGreaterThan(String value) {
            addCriterion("lighting >", value, "lighting");
            return (Criteria) this;
        }

        public Criteria andLightingGreaterThanOrEqualTo(String value) {
            addCriterion("lighting >=", value, "lighting");
            return (Criteria) this;
        }

        public Criteria andLightingLessThan(String value) {
            addCriterion("lighting <", value, "lighting");
            return (Criteria) this;
        }

        public Criteria andLightingLessThanOrEqualTo(String value) {
            addCriterion("lighting <=", value, "lighting");
            return (Criteria) this;
        }

        public Criteria andLightingLike(String value) {
            addCriterion("lighting like", value, "lighting");
            return (Criteria) this;
        }

        public Criteria andLightingNotLike(String value) {
            addCriterion("lighting not like", value, "lighting");
            return (Criteria) this;
        }

        public Criteria andLightingIn(List<String> values) {
            addCriterion("lighting in", values, "lighting");
            return (Criteria) this;
        }

        public Criteria andLightingNotIn(List<String> values) {
            addCriterion("lighting not in", values, "lighting");
            return (Criteria) this;
        }

        public Criteria andLightingBetween(String value1, String value2) {
            addCriterion("lighting between", value1, value2, "lighting");
            return (Criteria) this;
        }

        public Criteria andLightingNotBetween(String value1, String value2) {
            addCriterion("lighting not between", value1, value2, "lighting");
            return (Criteria) this;
        }

        public Criteria andOpeningIsNull() {
            addCriterion("opening is null");
            return (Criteria) this;
        }

        public Criteria andOpeningIsNotNull() {
            addCriterion("opening is not null");
            return (Criteria) this;
        }

        public Criteria andOpeningEqualTo(String value) {
            addCriterion("opening =", value, "opening");
            return (Criteria) this;
        }

        public Criteria andOpeningNotEqualTo(String value) {
            addCriterion("opening <>", value, "opening");
            return (Criteria) this;
        }

        public Criteria andOpeningGreaterThan(String value) {
            addCriterion("opening >", value, "opening");
            return (Criteria) this;
        }

        public Criteria andOpeningGreaterThanOrEqualTo(String value) {
            addCriterion("opening >=", value, "opening");
            return (Criteria) this;
        }

        public Criteria andOpeningLessThan(String value) {
            addCriterion("opening <", value, "opening");
            return (Criteria) this;
        }

        public Criteria andOpeningLessThanOrEqualTo(String value) {
            addCriterion("opening <=", value, "opening");
            return (Criteria) this;
        }

        public Criteria andOpeningLike(String value) {
            addCriterion("opening like", value, "opening");
            return (Criteria) this;
        }

        public Criteria andOpeningNotLike(String value) {
            addCriterion("opening not like", value, "opening");
            return (Criteria) this;
        }

        public Criteria andOpeningIn(List<String> values) {
            addCriterion("opening in", values, "opening");
            return (Criteria) this;
        }

        public Criteria andOpeningNotIn(List<String> values) {
            addCriterion("opening not in", values, "opening");
            return (Criteria) this;
        }

        public Criteria andOpeningBetween(String value1, String value2) {
            addCriterion("opening between", value1, value2, "opening");
            return (Criteria) this;
        }

        public Criteria andOpeningNotBetween(String value1, String value2) {
            addCriterion("opening not between", value1, value2, "opening");
            return (Criteria) this;
        }

        public Criteria andDepthIsNull() {
            addCriterion("depth is null");
            return (Criteria) this;
        }

        public Criteria andDepthIsNotNull() {
            addCriterion("depth is not null");
            return (Criteria) this;
        }

        public Criteria andDepthEqualTo(String value) {
            addCriterion("depth =", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotEqualTo(String value) {
            addCriterion("depth <>", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthGreaterThan(String value) {
            addCriterion("depth >", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthGreaterThanOrEqualTo(String value) {
            addCriterion("depth >=", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLessThan(String value) {
            addCriterion("depth <", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLessThanOrEqualTo(String value) {
            addCriterion("depth <=", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLike(String value) {
            addCriterion("depth like", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotLike(String value) {
            addCriterion("depth not like", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthIn(List<String> values) {
            addCriterion("depth in", values, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotIn(List<String> values) {
            addCriterion("depth not in", values, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthBetween(String value1, String value2) {
            addCriterion("depth between", value1, value2, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotBetween(String value1, String value2) {
            addCriterion("depth not between", value1, value2, "depth");
            return (Criteria) this;
        }

        public Criteria andLayerHeightIsNull() {
            addCriterion("layer_height is null");
            return (Criteria) this;
        }

        public Criteria andLayerHeightIsNotNull() {
            addCriterion("layer_height is not null");
            return (Criteria) this;
        }

        public Criteria andLayerHeightEqualTo(BigDecimal value) {
            addCriterion("layer_height =", value, "layerHeight");
            return (Criteria) this;
        }

        public Criteria andLayerHeightNotEqualTo(BigDecimal value) {
            addCriterion("layer_height <>", value, "layerHeight");
            return (Criteria) this;
        }

        public Criteria andLayerHeightGreaterThan(BigDecimal value) {
            addCriterion("layer_height >", value, "layerHeight");
            return (Criteria) this;
        }

        public Criteria andLayerHeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("layer_height >=", value, "layerHeight");
            return (Criteria) this;
        }

        public Criteria andLayerHeightLessThan(BigDecimal value) {
            addCriterion("layer_height <", value, "layerHeight");
            return (Criteria) this;
        }

        public Criteria andLayerHeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("layer_height <=", value, "layerHeight");
            return (Criteria) this;
        }

        public Criteria andLayerHeightIn(List<BigDecimal> values) {
            addCriterion("layer_height in", values, "layerHeight");
            return (Criteria) this;
        }

        public Criteria andLayerHeightNotIn(List<BigDecimal> values) {
            addCriterion("layer_height not in", values, "layerHeight");
            return (Criteria) this;
        }

        public Criteria andLayerHeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("layer_height between", value1, value2, "layerHeight");
            return (Criteria) this;
        }

        public Criteria andLayerHeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("layer_height not between", value1, value2, "layerHeight");
            return (Criteria) this;
        }

        public Criteria andClearHeightIsNull() {
            addCriterion("clear_height is null");
            return (Criteria) this;
        }

        public Criteria andClearHeightIsNotNull() {
            addCriterion("clear_height is not null");
            return (Criteria) this;
        }

        public Criteria andClearHeightEqualTo(BigDecimal value) {
            addCriterion("clear_height =", value, "clearHeight");
            return (Criteria) this;
        }

        public Criteria andClearHeightNotEqualTo(BigDecimal value) {
            addCriterion("clear_height <>", value, "clearHeight");
            return (Criteria) this;
        }

        public Criteria andClearHeightGreaterThan(BigDecimal value) {
            addCriterion("clear_height >", value, "clearHeight");
            return (Criteria) this;
        }

        public Criteria andClearHeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("clear_height >=", value, "clearHeight");
            return (Criteria) this;
        }

        public Criteria andClearHeightLessThan(BigDecimal value) {
            addCriterion("clear_height <", value, "clearHeight");
            return (Criteria) this;
        }

        public Criteria andClearHeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("clear_height <=", value, "clearHeight");
            return (Criteria) this;
        }

        public Criteria andClearHeightIn(List<BigDecimal> values) {
            addCriterion("clear_height in", values, "clearHeight");
            return (Criteria) this;
        }

        public Criteria andClearHeightNotIn(List<BigDecimal> values) {
            addCriterion("clear_height not in", values, "clearHeight");
            return (Criteria) this;
        }

        public Criteria andClearHeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("clear_height between", value1, value2, "clearHeight");
            return (Criteria) this;
        }

        public Criteria andClearHeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("clear_height not between", value1, value2, "clearHeight");
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