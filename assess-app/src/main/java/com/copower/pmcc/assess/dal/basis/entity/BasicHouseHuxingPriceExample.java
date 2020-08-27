package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicHouseHuxingPriceExample {
    /**
     * tb_basic_house_huxing_price
     */
    protected String orderByClause;

    /**
     * tb_basic_house_huxing_price
     */
    protected boolean distinct;

    /**
     * tb_basic_house_huxing_price
     */
    protected List<Criteria> oredCriteria;

    public BasicHouseHuxingPriceExample() {
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
     * tb_basic_house_huxing_price
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

        public Criteria andHuxingIdIsNull() {
            addCriterion("huxing_id is null");
            return (Criteria) this;
        }

        public Criteria andHuxingIdIsNotNull() {
            addCriterion("huxing_id is not null");
            return (Criteria) this;
        }

        public Criteria andHuxingIdEqualTo(Integer value) {
            addCriterion("huxing_id =", value, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingIdNotEqualTo(Integer value) {
            addCriterion("huxing_id <>", value, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingIdGreaterThan(Integer value) {
            addCriterion("huxing_id >", value, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("huxing_id >=", value, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingIdLessThan(Integer value) {
            addCriterion("huxing_id <", value, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingIdLessThanOrEqualTo(Integer value) {
            addCriterion("huxing_id <=", value, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingIdIn(List<Integer> values) {
            addCriterion("huxing_id in", values, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingIdNotIn(List<Integer> values) {
            addCriterion("huxing_id not in", values, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingIdBetween(Integer value1, Integer value2) {
            addCriterion("huxing_id between", value1, value2, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingIdNotBetween(Integer value1, Integer value2) {
            addCriterion("huxing_id not between", value1, value2, "huxingId");
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

        public Criteria andFloorIsNull() {
            addCriterion("floor is null");
            return (Criteria) this;
        }

        public Criteria andFloorIsNotNull() {
            addCriterion("floor is not null");
            return (Criteria) this;
        }

        public Criteria andFloorEqualTo(String value) {
            addCriterion("floor =", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotEqualTo(String value) {
            addCriterion("floor <>", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorGreaterThan(String value) {
            addCriterion("floor >", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorGreaterThanOrEqualTo(String value) {
            addCriterion("floor >=", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLessThan(String value) {
            addCriterion("floor <", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLessThanOrEqualTo(String value) {
            addCriterion("floor <=", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLike(String value) {
            addCriterion("floor like", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotLike(String value) {
            addCriterion("floor not like", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorIn(List<String> values) {
            addCriterion("floor in", values, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotIn(List<String> values) {
            addCriterion("floor not in", values, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorBetween(String value1, String value2) {
            addCriterion("floor between", value1, value2, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotBetween(String value1, String value2) {
            addCriterion("floor not between", value1, value2, "floor");
            return (Criteria) this;
        }

        public Criteria andAdjustFactorIsNull() {
            addCriterion("adjust_factor is null");
            return (Criteria) this;
        }

        public Criteria andAdjustFactorIsNotNull() {
            addCriterion("adjust_factor is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustFactorEqualTo(String value) {
            addCriterion("adjust_factor =", value, "adjustFactor");
            return (Criteria) this;
        }

        public Criteria andAdjustFactorNotEqualTo(String value) {
            addCriterion("adjust_factor <>", value, "adjustFactor");
            return (Criteria) this;
        }

        public Criteria andAdjustFactorGreaterThan(String value) {
            addCriterion("adjust_factor >", value, "adjustFactor");
            return (Criteria) this;
        }

        public Criteria andAdjustFactorGreaterThanOrEqualTo(String value) {
            addCriterion("adjust_factor >=", value, "adjustFactor");
            return (Criteria) this;
        }

        public Criteria andAdjustFactorLessThan(String value) {
            addCriterion("adjust_factor <", value, "adjustFactor");
            return (Criteria) this;
        }

        public Criteria andAdjustFactorLessThanOrEqualTo(String value) {
            addCriterion("adjust_factor <=", value, "adjustFactor");
            return (Criteria) this;
        }

        public Criteria andAdjustFactorLike(String value) {
            addCriterion("adjust_factor like", value, "adjustFactor");
            return (Criteria) this;
        }

        public Criteria andAdjustFactorNotLike(String value) {
            addCriterion("adjust_factor not like", value, "adjustFactor");
            return (Criteria) this;
        }

        public Criteria andAdjustFactorIn(List<String> values) {
            addCriterion("adjust_factor in", values, "adjustFactor");
            return (Criteria) this;
        }

        public Criteria andAdjustFactorNotIn(List<String> values) {
            addCriterion("adjust_factor not in", values, "adjustFactor");
            return (Criteria) this;
        }

        public Criteria andAdjustFactorBetween(String value1, String value2) {
            addCriterion("adjust_factor between", value1, value2, "adjustFactor");
            return (Criteria) this;
        }

        public Criteria andAdjustFactorNotBetween(String value1, String value2) {
            addCriterion("adjust_factor not between", value1, value2, "adjustFactor");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
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

        public Criteria andDeclareNameIsNull() {
            addCriterion("declare_name is null");
            return (Criteria) this;
        }

        public Criteria andDeclareNameIsNotNull() {
            addCriterion("declare_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeclareNameEqualTo(String value) {
            addCriterion("declare_name =", value, "declareName");
            return (Criteria) this;
        }

        public Criteria andDeclareNameNotEqualTo(String value) {
            addCriterion("declare_name <>", value, "declareName");
            return (Criteria) this;
        }

        public Criteria andDeclareNameGreaterThan(String value) {
            addCriterion("declare_name >", value, "declareName");
            return (Criteria) this;
        }

        public Criteria andDeclareNameGreaterThanOrEqualTo(String value) {
            addCriterion("declare_name >=", value, "declareName");
            return (Criteria) this;
        }

        public Criteria andDeclareNameLessThan(String value) {
            addCriterion("declare_name <", value, "declareName");
            return (Criteria) this;
        }

        public Criteria andDeclareNameLessThanOrEqualTo(String value) {
            addCriterion("declare_name <=", value, "declareName");
            return (Criteria) this;
        }

        public Criteria andDeclareNameLike(String value) {
            addCriterion("declare_name like", value, "declareName");
            return (Criteria) this;
        }

        public Criteria andDeclareNameNotLike(String value) {
            addCriterion("declare_name not like", value, "declareName");
            return (Criteria) this;
        }

        public Criteria andDeclareNameIn(List<String> values) {
            addCriterion("declare_name in", values, "declareName");
            return (Criteria) this;
        }

        public Criteria andDeclareNameNotIn(List<String> values) {
            addCriterion("declare_name not in", values, "declareName");
            return (Criteria) this;
        }

        public Criteria andDeclareNameBetween(String value1, String value2) {
            addCriterion("declare_name between", value1, value2, "declareName");
            return (Criteria) this;
        }

        public Criteria andDeclareNameNotBetween(String value1, String value2) {
            addCriterion("declare_name not between", value1, value2, "declareName");
            return (Criteria) this;
        }

        public Criteria andSeatIsNull() {
            addCriterion("seat is null");
            return (Criteria) this;
        }

        public Criteria andSeatIsNotNull() {
            addCriterion("seat is not null");
            return (Criteria) this;
        }

        public Criteria andSeatEqualTo(String value) {
            addCriterion("seat =", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatNotEqualTo(String value) {
            addCriterion("seat <>", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatGreaterThan(String value) {
            addCriterion("seat >", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatGreaterThanOrEqualTo(String value) {
            addCriterion("seat >=", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatLessThan(String value) {
            addCriterion("seat <", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatLessThanOrEqualTo(String value) {
            addCriterion("seat <=", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatLike(String value) {
            addCriterion("seat like", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatNotLike(String value) {
            addCriterion("seat not like", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatIn(List<String> values) {
            addCriterion("seat in", values, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatNotIn(List<String> values) {
            addCriterion("seat not in", values, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatBetween(String value1, String value2) {
            addCriterion("seat between", value1, value2, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatNotBetween(String value1, String value2) {
            addCriterion("seat not between", value1, value2, "seat");
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

        public Criteria andLengthIsNull() {
            addCriterion("length is null");
            return (Criteria) this;
        }

        public Criteria andLengthIsNotNull() {
            addCriterion("length is not null");
            return (Criteria) this;
        }

        public Criteria andLengthEqualTo(String value) {
            addCriterion("length =", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotEqualTo(String value) {
            addCriterion("length <>", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthGreaterThan(String value) {
            addCriterion("length >", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthGreaterThanOrEqualTo(String value) {
            addCriterion("length >=", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLessThan(String value) {
            addCriterion("length <", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLessThanOrEqualTo(String value) {
            addCriterion("length <=", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLike(String value) {
            addCriterion("length like", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotLike(String value) {
            addCriterion("length not like", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthIn(List<String> values) {
            addCriterion("length in", values, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotIn(List<String> values) {
            addCriterion("length not in", values, "length");
            return (Criteria) this;
        }

        public Criteria andLengthBetween(String value1, String value2) {
            addCriterion("length between", value1, value2, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotBetween(String value1, String value2) {
            addCriterion("length not between", value1, value2, "length");
            return (Criteria) this;
        }

        public Criteria andWidthIsNull() {
            addCriterion("width is null");
            return (Criteria) this;
        }

        public Criteria andWidthIsNotNull() {
            addCriterion("width is not null");
            return (Criteria) this;
        }

        public Criteria andWidthEqualTo(String value) {
            addCriterion("width =", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotEqualTo(String value) {
            addCriterion("width <>", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThan(String value) {
            addCriterion("width >", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThanOrEqualTo(String value) {
            addCriterion("width >=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThan(String value) {
            addCriterion("width <", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThanOrEqualTo(String value) {
            addCriterion("width <=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLike(String value) {
            addCriterion("width like", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotLike(String value) {
            addCriterion("width not like", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthIn(List<String> values) {
            addCriterion("width in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotIn(List<String> values) {
            addCriterion("width not in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthBetween(String value1, String value2) {
            addCriterion("width between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotBetween(String value1, String value2) {
            addCriterion("width not between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andAdjacentPositionIsNull() {
            addCriterion("adjacent_position is null");
            return (Criteria) this;
        }

        public Criteria andAdjacentPositionIsNotNull() {
            addCriterion("adjacent_position is not null");
            return (Criteria) this;
        }

        public Criteria andAdjacentPositionEqualTo(String value) {
            addCriterion("adjacent_position =", value, "adjacentPosition");
            return (Criteria) this;
        }

        public Criteria andAdjacentPositionNotEqualTo(String value) {
            addCriterion("adjacent_position <>", value, "adjacentPosition");
            return (Criteria) this;
        }

        public Criteria andAdjacentPositionGreaterThan(String value) {
            addCriterion("adjacent_position >", value, "adjacentPosition");
            return (Criteria) this;
        }

        public Criteria andAdjacentPositionGreaterThanOrEqualTo(String value) {
            addCriterion("adjacent_position >=", value, "adjacentPosition");
            return (Criteria) this;
        }

        public Criteria andAdjacentPositionLessThan(String value) {
            addCriterion("adjacent_position <", value, "adjacentPosition");
            return (Criteria) this;
        }

        public Criteria andAdjacentPositionLessThanOrEqualTo(String value) {
            addCriterion("adjacent_position <=", value, "adjacentPosition");
            return (Criteria) this;
        }

        public Criteria andAdjacentPositionLike(String value) {
            addCriterion("adjacent_position like", value, "adjacentPosition");
            return (Criteria) this;
        }

        public Criteria andAdjacentPositionNotLike(String value) {
            addCriterion("adjacent_position not like", value, "adjacentPosition");
            return (Criteria) this;
        }

        public Criteria andAdjacentPositionIn(List<String> values) {
            addCriterion("adjacent_position in", values, "adjacentPosition");
            return (Criteria) this;
        }

        public Criteria andAdjacentPositionNotIn(List<String> values) {
            addCriterion("adjacent_position not in", values, "adjacentPosition");
            return (Criteria) this;
        }

        public Criteria andAdjacentPositionBetween(String value1, String value2) {
            addCriterion("adjacent_position between", value1, value2, "adjacentPosition");
            return (Criteria) this;
        }

        public Criteria andAdjacentPositionNotBetween(String value1, String value2) {
            addCriterion("adjacent_position not between", value1, value2, "adjacentPosition");
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

        public Criteria andDistanceIsNull() {
            addCriterion("distance is null");
            return (Criteria) this;
        }

        public Criteria andDistanceIsNotNull() {
            addCriterion("distance is not null");
            return (Criteria) this;
        }

        public Criteria andDistanceEqualTo(String value) {
            addCriterion("distance =", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotEqualTo(String value) {
            addCriterion("distance <>", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceGreaterThan(String value) {
            addCriterion("distance >", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceGreaterThanOrEqualTo(String value) {
            addCriterion("distance >=", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLessThan(String value) {
            addCriterion("distance <", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLessThanOrEqualTo(String value) {
            addCriterion("distance <=", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLike(String value) {
            addCriterion("distance like", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotLike(String value) {
            addCriterion("distance not like", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceIn(List<String> values) {
            addCriterion("distance in", values, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotIn(List<String> values) {
            addCriterion("distance not in", values, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceBetween(String value1, String value2) {
            addCriterion("distance between", value1, value2, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotBetween(String value1, String value2) {
            addCriterion("distance not between", value1, value2, "distance");
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

        public Criteria andSpanLengthEqualTo(String value) {
            addCriterion("span_length =", value, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthNotEqualTo(String value) {
            addCriterion("span_length <>", value, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthGreaterThan(String value) {
            addCriterion("span_length >", value, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthGreaterThanOrEqualTo(String value) {
            addCriterion("span_length >=", value, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthLessThan(String value) {
            addCriterion("span_length <", value, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthLessThanOrEqualTo(String value) {
            addCriterion("span_length <=", value, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthLike(String value) {
            addCriterion("span_length like", value, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthNotLike(String value) {
            addCriterion("span_length not like", value, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthIn(List<String> values) {
            addCriterion("span_length in", values, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthNotIn(List<String> values) {
            addCriterion("span_length not in", values, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthBetween(String value1, String value2) {
            addCriterion("span_length between", value1, value2, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanLengthNotBetween(String value1, String value2) {
            addCriterion("span_length not between", value1, value2, "spanLength");
            return (Criteria) this;
        }

        public Criteria andSpanNumIsNull() {
            addCriterion("span_num is null");
            return (Criteria) this;
        }

        public Criteria andSpanNumIsNotNull() {
            addCriterion("span_num is not null");
            return (Criteria) this;
        }

        public Criteria andSpanNumEqualTo(String value) {
            addCriterion("span_num =", value, "spanNum");
            return (Criteria) this;
        }

        public Criteria andSpanNumNotEqualTo(String value) {
            addCriterion("span_num <>", value, "spanNum");
            return (Criteria) this;
        }

        public Criteria andSpanNumGreaterThan(String value) {
            addCriterion("span_num >", value, "spanNum");
            return (Criteria) this;
        }

        public Criteria andSpanNumGreaterThanOrEqualTo(String value) {
            addCriterion("span_num >=", value, "spanNum");
            return (Criteria) this;
        }

        public Criteria andSpanNumLessThan(String value) {
            addCriterion("span_num <", value, "spanNum");
            return (Criteria) this;
        }

        public Criteria andSpanNumLessThanOrEqualTo(String value) {
            addCriterion("span_num <=", value, "spanNum");
            return (Criteria) this;
        }

        public Criteria andSpanNumLike(String value) {
            addCriterion("span_num like", value, "spanNum");
            return (Criteria) this;
        }

        public Criteria andSpanNumNotLike(String value) {
            addCriterion("span_num not like", value, "spanNum");
            return (Criteria) this;
        }

        public Criteria andSpanNumIn(List<String> values) {
            addCriterion("span_num in", values, "spanNum");
            return (Criteria) this;
        }

        public Criteria andSpanNumNotIn(List<String> values) {
            addCriterion("span_num not in", values, "spanNum");
            return (Criteria) this;
        }

        public Criteria andSpanNumBetween(String value1, String value2) {
            addCriterion("span_num between", value1, value2, "spanNum");
            return (Criteria) this;
        }

        public Criteria andSpanNumNotBetween(String value1, String value2) {
            addCriterion("span_num not between", value1, value2, "spanNum");
            return (Criteria) this;
        }

        public Criteria andMaxSpanIsNull() {
            addCriterion("max_span is null");
            return (Criteria) this;
        }

        public Criteria andMaxSpanIsNotNull() {
            addCriterion("max_span is not null");
            return (Criteria) this;
        }

        public Criteria andMaxSpanEqualTo(String value) {
            addCriterion("max_span =", value, "maxSpan");
            return (Criteria) this;
        }

        public Criteria andMaxSpanNotEqualTo(String value) {
            addCriterion("max_span <>", value, "maxSpan");
            return (Criteria) this;
        }

        public Criteria andMaxSpanGreaterThan(String value) {
            addCriterion("max_span >", value, "maxSpan");
            return (Criteria) this;
        }

        public Criteria andMaxSpanGreaterThanOrEqualTo(String value) {
            addCriterion("max_span >=", value, "maxSpan");
            return (Criteria) this;
        }

        public Criteria andMaxSpanLessThan(String value) {
            addCriterion("max_span <", value, "maxSpan");
            return (Criteria) this;
        }

        public Criteria andMaxSpanLessThanOrEqualTo(String value) {
            addCriterion("max_span <=", value, "maxSpan");
            return (Criteria) this;
        }

        public Criteria andMaxSpanLike(String value) {
            addCriterion("max_span like", value, "maxSpan");
            return (Criteria) this;
        }

        public Criteria andMaxSpanNotLike(String value) {
            addCriterion("max_span not like", value, "maxSpan");
            return (Criteria) this;
        }

        public Criteria andMaxSpanIn(List<String> values) {
            addCriterion("max_span in", values, "maxSpan");
            return (Criteria) this;
        }

        public Criteria andMaxSpanNotIn(List<String> values) {
            addCriterion("max_span not in", values, "maxSpan");
            return (Criteria) this;
        }

        public Criteria andMaxSpanBetween(String value1, String value2) {
            addCriterion("max_span between", value1, value2, "maxSpan");
            return (Criteria) this;
        }

        public Criteria andMaxSpanNotBetween(String value1, String value2) {
            addCriterion("max_span not between", value1, value2, "maxSpan");
            return (Criteria) this;
        }

        public Criteria andMinSpanIsNull() {
            addCriterion("min_span is null");
            return (Criteria) this;
        }

        public Criteria andMinSpanIsNotNull() {
            addCriterion("min_span is not null");
            return (Criteria) this;
        }

        public Criteria andMinSpanEqualTo(String value) {
            addCriterion("min_span =", value, "minSpan");
            return (Criteria) this;
        }

        public Criteria andMinSpanNotEqualTo(String value) {
            addCriterion("min_span <>", value, "minSpan");
            return (Criteria) this;
        }

        public Criteria andMinSpanGreaterThan(String value) {
            addCriterion("min_span >", value, "minSpan");
            return (Criteria) this;
        }

        public Criteria andMinSpanGreaterThanOrEqualTo(String value) {
            addCriterion("min_span >=", value, "minSpan");
            return (Criteria) this;
        }

        public Criteria andMinSpanLessThan(String value) {
            addCriterion("min_span <", value, "minSpan");
            return (Criteria) this;
        }

        public Criteria andMinSpanLessThanOrEqualTo(String value) {
            addCriterion("min_span <=", value, "minSpan");
            return (Criteria) this;
        }

        public Criteria andMinSpanLike(String value) {
            addCriterion("min_span like", value, "minSpan");
            return (Criteria) this;
        }

        public Criteria andMinSpanNotLike(String value) {
            addCriterion("min_span not like", value, "minSpan");
            return (Criteria) this;
        }

        public Criteria andMinSpanIn(List<String> values) {
            addCriterion("min_span in", values, "minSpan");
            return (Criteria) this;
        }

        public Criteria andMinSpanNotIn(List<String> values) {
            addCriterion("min_span not in", values, "minSpan");
            return (Criteria) this;
        }

        public Criteria andMinSpanBetween(String value1, String value2) {
            addCriterion("min_span between", value1, value2, "minSpan");
            return (Criteria) this;
        }

        public Criteria andMinSpanNotBetween(String value1, String value2) {
            addCriterion("min_span not between", value1, value2, "minSpan");
            return (Criteria) this;
        }

        public Criteria andStandardSpanIsNull() {
            addCriterion("standard_span is null");
            return (Criteria) this;
        }

        public Criteria andStandardSpanIsNotNull() {
            addCriterion("standard_span is not null");
            return (Criteria) this;
        }

        public Criteria andStandardSpanEqualTo(String value) {
            addCriterion("standard_span =", value, "standardSpan");
            return (Criteria) this;
        }

        public Criteria andStandardSpanNotEqualTo(String value) {
            addCriterion("standard_span <>", value, "standardSpan");
            return (Criteria) this;
        }

        public Criteria andStandardSpanGreaterThan(String value) {
            addCriterion("standard_span >", value, "standardSpan");
            return (Criteria) this;
        }

        public Criteria andStandardSpanGreaterThanOrEqualTo(String value) {
            addCriterion("standard_span >=", value, "standardSpan");
            return (Criteria) this;
        }

        public Criteria andStandardSpanLessThan(String value) {
            addCriterion("standard_span <", value, "standardSpan");
            return (Criteria) this;
        }

        public Criteria andStandardSpanLessThanOrEqualTo(String value) {
            addCriterion("standard_span <=", value, "standardSpan");
            return (Criteria) this;
        }

        public Criteria andStandardSpanLike(String value) {
            addCriterion("standard_span like", value, "standardSpan");
            return (Criteria) this;
        }

        public Criteria andStandardSpanNotLike(String value) {
            addCriterion("standard_span not like", value, "standardSpan");
            return (Criteria) this;
        }

        public Criteria andStandardSpanIn(List<String> values) {
            addCriterion("standard_span in", values, "standardSpan");
            return (Criteria) this;
        }

        public Criteria andStandardSpanNotIn(List<String> values) {
            addCriterion("standard_span not in", values, "standardSpan");
            return (Criteria) this;
        }

        public Criteria andStandardSpanBetween(String value1, String value2) {
            addCriterion("standard_span between", value1, value2, "standardSpan");
            return (Criteria) this;
        }

        public Criteria andStandardSpanNotBetween(String value1, String value2) {
            addCriterion("standard_span not between", value1, value2, "standardSpan");
            return (Criteria) this;
        }

        public Criteria andStandardMeasureIsNull() {
            addCriterion("standard_measure is null");
            return (Criteria) this;
        }

        public Criteria andStandardMeasureIsNotNull() {
            addCriterion("standard_measure is not null");
            return (Criteria) this;
        }

        public Criteria andStandardMeasureEqualTo(String value) {
            addCriterion("standard_measure =", value, "standardMeasure");
            return (Criteria) this;
        }

        public Criteria andStandardMeasureNotEqualTo(String value) {
            addCriterion("standard_measure <>", value, "standardMeasure");
            return (Criteria) this;
        }

        public Criteria andStandardMeasureGreaterThan(String value) {
            addCriterion("standard_measure >", value, "standardMeasure");
            return (Criteria) this;
        }

        public Criteria andStandardMeasureGreaterThanOrEqualTo(String value) {
            addCriterion("standard_measure >=", value, "standardMeasure");
            return (Criteria) this;
        }

        public Criteria andStandardMeasureLessThan(String value) {
            addCriterion("standard_measure <", value, "standardMeasure");
            return (Criteria) this;
        }

        public Criteria andStandardMeasureLessThanOrEqualTo(String value) {
            addCriterion("standard_measure <=", value, "standardMeasure");
            return (Criteria) this;
        }

        public Criteria andStandardMeasureLike(String value) {
            addCriterion("standard_measure like", value, "standardMeasure");
            return (Criteria) this;
        }

        public Criteria andStandardMeasureNotLike(String value) {
            addCriterion("standard_measure not like", value, "standardMeasure");
            return (Criteria) this;
        }

        public Criteria andStandardMeasureIn(List<String> values) {
            addCriterion("standard_measure in", values, "standardMeasure");
            return (Criteria) this;
        }

        public Criteria andStandardMeasureNotIn(List<String> values) {
            addCriterion("standard_measure not in", values, "standardMeasure");
            return (Criteria) this;
        }

        public Criteria andStandardMeasureBetween(String value1, String value2) {
            addCriterion("standard_measure between", value1, value2, "standardMeasure");
            return (Criteria) this;
        }

        public Criteria andStandardMeasureNotBetween(String value1, String value2) {
            addCriterion("standard_measure not between", value1, value2, "standardMeasure");
            return (Criteria) this;
        }

        public Criteria andStorageRequestIsNull() {
            addCriterion("storage_request is null");
            return (Criteria) this;
        }

        public Criteria andStorageRequestIsNotNull() {
            addCriterion("storage_request is not null");
            return (Criteria) this;
        }

        public Criteria andStorageRequestEqualTo(String value) {
            addCriterion("storage_request =", value, "storageRequest");
            return (Criteria) this;
        }

        public Criteria andStorageRequestNotEqualTo(String value) {
            addCriterion("storage_request <>", value, "storageRequest");
            return (Criteria) this;
        }

        public Criteria andStorageRequestGreaterThan(String value) {
            addCriterion("storage_request >", value, "storageRequest");
            return (Criteria) this;
        }

        public Criteria andStorageRequestGreaterThanOrEqualTo(String value) {
            addCriterion("storage_request >=", value, "storageRequest");
            return (Criteria) this;
        }

        public Criteria andStorageRequestLessThan(String value) {
            addCriterion("storage_request <", value, "storageRequest");
            return (Criteria) this;
        }

        public Criteria andStorageRequestLessThanOrEqualTo(String value) {
            addCriterion("storage_request <=", value, "storageRequest");
            return (Criteria) this;
        }

        public Criteria andStorageRequestLike(String value) {
            addCriterion("storage_request like", value, "storageRequest");
            return (Criteria) this;
        }

        public Criteria andStorageRequestNotLike(String value) {
            addCriterion("storage_request not like", value, "storageRequest");
            return (Criteria) this;
        }

        public Criteria andStorageRequestIn(List<String> values) {
            addCriterion("storage_request in", values, "storageRequest");
            return (Criteria) this;
        }

        public Criteria andStorageRequestNotIn(List<String> values) {
            addCriterion("storage_request not in", values, "storageRequest");
            return (Criteria) this;
        }

        public Criteria andStorageRequestBetween(String value1, String value2) {
            addCriterion("storage_request between", value1, value2, "storageRequest");
            return (Criteria) this;
        }

        public Criteria andStorageRequestNotBetween(String value1, String value2) {
            addCriterion("storage_request not between", value1, value2, "storageRequest");
            return (Criteria) this;
        }

        public Criteria andSpecialFactorsIsNull() {
            addCriterion("special_factors is null");
            return (Criteria) this;
        }

        public Criteria andSpecialFactorsIsNotNull() {
            addCriterion("special_factors is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialFactorsEqualTo(String value) {
            addCriterion("special_factors =", value, "specialFactors");
            return (Criteria) this;
        }

        public Criteria andSpecialFactorsNotEqualTo(String value) {
            addCriterion("special_factors <>", value, "specialFactors");
            return (Criteria) this;
        }

        public Criteria andSpecialFactorsGreaterThan(String value) {
            addCriterion("special_factors >", value, "specialFactors");
            return (Criteria) this;
        }

        public Criteria andSpecialFactorsGreaterThanOrEqualTo(String value) {
            addCriterion("special_factors >=", value, "specialFactors");
            return (Criteria) this;
        }

        public Criteria andSpecialFactorsLessThan(String value) {
            addCriterion("special_factors <", value, "specialFactors");
            return (Criteria) this;
        }

        public Criteria andSpecialFactorsLessThanOrEqualTo(String value) {
            addCriterion("special_factors <=", value, "specialFactors");
            return (Criteria) this;
        }

        public Criteria andSpecialFactorsLike(String value) {
            addCriterion("special_factors like", value, "specialFactors");
            return (Criteria) this;
        }

        public Criteria andSpecialFactorsNotLike(String value) {
            addCriterion("special_factors not like", value, "specialFactors");
            return (Criteria) this;
        }

        public Criteria andSpecialFactorsIn(List<String> values) {
            addCriterion("special_factors in", values, "specialFactors");
            return (Criteria) this;
        }

        public Criteria andSpecialFactorsNotIn(List<String> values) {
            addCriterion("special_factors not in", values, "specialFactors");
            return (Criteria) this;
        }

        public Criteria andSpecialFactorsBetween(String value1, String value2) {
            addCriterion("special_factors between", value1, value2, "specialFactors");
            return (Criteria) this;
        }

        public Criteria andSpecialFactorsNotBetween(String value1, String value2) {
            addCriterion("special_factors not between", value1, value2, "specialFactors");
            return (Criteria) this;
        }

        public Criteria andHouseShapeIsNull() {
            addCriterion("house_shape is null");
            return (Criteria) this;
        }

        public Criteria andHouseShapeIsNotNull() {
            addCriterion("house_shape is not null");
            return (Criteria) this;
        }

        public Criteria andHouseShapeEqualTo(String value) {
            addCriterion("house_shape =", value, "houseShape");
            return (Criteria) this;
        }

        public Criteria andHouseShapeNotEqualTo(String value) {
            addCriterion("house_shape <>", value, "houseShape");
            return (Criteria) this;
        }

        public Criteria andHouseShapeGreaterThan(String value) {
            addCriterion("house_shape >", value, "houseShape");
            return (Criteria) this;
        }

        public Criteria andHouseShapeGreaterThanOrEqualTo(String value) {
            addCriterion("house_shape >=", value, "houseShape");
            return (Criteria) this;
        }

        public Criteria andHouseShapeLessThan(String value) {
            addCriterion("house_shape <", value, "houseShape");
            return (Criteria) this;
        }

        public Criteria andHouseShapeLessThanOrEqualTo(String value) {
            addCriterion("house_shape <=", value, "houseShape");
            return (Criteria) this;
        }

        public Criteria andHouseShapeLike(String value) {
            addCriterion("house_shape like", value, "houseShape");
            return (Criteria) this;
        }

        public Criteria andHouseShapeNotLike(String value) {
            addCriterion("house_shape not like", value, "houseShape");
            return (Criteria) this;
        }

        public Criteria andHouseShapeIn(List<String> values) {
            addCriterion("house_shape in", values, "houseShape");
            return (Criteria) this;
        }

        public Criteria andHouseShapeNotIn(List<String> values) {
            addCriterion("house_shape not in", values, "houseShape");
            return (Criteria) this;
        }

        public Criteria andHouseShapeBetween(String value1, String value2) {
            addCriterion("house_shape between", value1, value2, "houseShape");
            return (Criteria) this;
        }

        public Criteria andHouseShapeNotBetween(String value1, String value2) {
            addCriterion("house_shape not between", value1, value2, "houseShape");
            return (Criteria) this;
        }

        public Criteria andShapeRemarkIsNull() {
            addCriterion("shape_remark is null");
            return (Criteria) this;
        }

        public Criteria andShapeRemarkIsNotNull() {
            addCriterion("shape_remark is not null");
            return (Criteria) this;
        }

        public Criteria andShapeRemarkEqualTo(String value) {
            addCriterion("shape_remark =", value, "shapeRemark");
            return (Criteria) this;
        }

        public Criteria andShapeRemarkNotEqualTo(String value) {
            addCriterion("shape_remark <>", value, "shapeRemark");
            return (Criteria) this;
        }

        public Criteria andShapeRemarkGreaterThan(String value) {
            addCriterion("shape_remark >", value, "shapeRemark");
            return (Criteria) this;
        }

        public Criteria andShapeRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("shape_remark >=", value, "shapeRemark");
            return (Criteria) this;
        }

        public Criteria andShapeRemarkLessThan(String value) {
            addCriterion("shape_remark <", value, "shapeRemark");
            return (Criteria) this;
        }

        public Criteria andShapeRemarkLessThanOrEqualTo(String value) {
            addCriterion("shape_remark <=", value, "shapeRemark");
            return (Criteria) this;
        }

        public Criteria andShapeRemarkLike(String value) {
            addCriterion("shape_remark like", value, "shapeRemark");
            return (Criteria) this;
        }

        public Criteria andShapeRemarkNotLike(String value) {
            addCriterion("shape_remark not like", value, "shapeRemark");
            return (Criteria) this;
        }

        public Criteria andShapeRemarkIn(List<String> values) {
            addCriterion("shape_remark in", values, "shapeRemark");
            return (Criteria) this;
        }

        public Criteria andShapeRemarkNotIn(List<String> values) {
            addCriterion("shape_remark not in", values, "shapeRemark");
            return (Criteria) this;
        }

        public Criteria andShapeRemarkBetween(String value1, String value2) {
            addCriterion("shape_remark between", value1, value2, "shapeRemark");
            return (Criteria) this;
        }

        public Criteria andShapeRemarkNotBetween(String value1, String value2) {
            addCriterion("shape_remark not between", value1, value2, "shapeRemark");
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

    /**
     * tb_basic_house_huxing_price
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