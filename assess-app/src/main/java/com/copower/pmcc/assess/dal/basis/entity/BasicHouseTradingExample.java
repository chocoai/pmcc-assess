package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicHouseTradingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicHouseTradingExample() {
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

        public Criteria andTradingTypeIsNull() {
            addCriterion("trading_type is null");
            return (Criteria) this;
        }

        public Criteria andTradingTypeIsNotNull() {
            addCriterion("trading_type is not null");
            return (Criteria) this;
        }

        public Criteria andTradingTypeEqualTo(Integer value) {
            addCriterion("trading_type =", value, "tradingType");
            return (Criteria) this;
        }

        public Criteria andTradingTypeNotEqualTo(Integer value) {
            addCriterion("trading_type <>", value, "tradingType");
            return (Criteria) this;
        }

        public Criteria andTradingTypeGreaterThan(Integer value) {
            addCriterion("trading_type >", value, "tradingType");
            return (Criteria) this;
        }

        public Criteria andTradingTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("trading_type >=", value, "tradingType");
            return (Criteria) this;
        }

        public Criteria andTradingTypeLessThan(Integer value) {
            addCriterion("trading_type <", value, "tradingType");
            return (Criteria) this;
        }

        public Criteria andTradingTypeLessThanOrEqualTo(Integer value) {
            addCriterion("trading_type <=", value, "tradingType");
            return (Criteria) this;
        }

        public Criteria andTradingTypeIn(List<Integer> values) {
            addCriterion("trading_type in", values, "tradingType");
            return (Criteria) this;
        }

        public Criteria andTradingTypeNotIn(List<Integer> values) {
            addCriterion("trading_type not in", values, "tradingType");
            return (Criteria) this;
        }

        public Criteria andTradingTypeBetween(Integer value1, Integer value2) {
            addCriterion("trading_type between", value1, value2, "tradingType");
            return (Criteria) this;
        }

        public Criteria andTradingTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("trading_type not between", value1, value2, "tradingType");
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

        public Criteria andBuyerExtraTaxIsNull() {
            addCriterion("buyer_extra_tax is null");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxIsNotNull() {
            addCriterion("buyer_extra_tax is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxEqualTo(String value) {
            addCriterion("buyer_extra_tax =", value, "buyerExtraTax");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxNotEqualTo(String value) {
            addCriterion("buyer_extra_tax <>", value, "buyerExtraTax");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxGreaterThan(String value) {
            addCriterion("buyer_extra_tax >", value, "buyerExtraTax");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_extra_tax >=", value, "buyerExtraTax");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxLessThan(String value) {
            addCriterion("buyer_extra_tax <", value, "buyerExtraTax");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxLessThanOrEqualTo(String value) {
            addCriterion("buyer_extra_tax <=", value, "buyerExtraTax");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxLike(String value) {
            addCriterion("buyer_extra_tax like", value, "buyerExtraTax");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxNotLike(String value) {
            addCriterion("buyer_extra_tax not like", value, "buyerExtraTax");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxIn(List<String> values) {
            addCriterion("buyer_extra_tax in", values, "buyerExtraTax");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxNotIn(List<String> values) {
            addCriterion("buyer_extra_tax not in", values, "buyerExtraTax");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxBetween(String value1, String value2) {
            addCriterion("buyer_extra_tax between", value1, value2, "buyerExtraTax");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxNotBetween(String value1, String value2) {
            addCriterion("buyer_extra_tax not between", value1, value2, "buyerExtraTax");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraFeeIsNull() {
            addCriterion("buyer_extra_fee is null");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraFeeIsNotNull() {
            addCriterion("buyer_extra_fee is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraFeeEqualTo(String value) {
            addCriterion("buyer_extra_fee =", value, "buyerExtraFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraFeeNotEqualTo(String value) {
            addCriterion("buyer_extra_fee <>", value, "buyerExtraFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraFeeGreaterThan(String value) {
            addCriterion("buyer_extra_fee >", value, "buyerExtraFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraFeeGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_extra_fee >=", value, "buyerExtraFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraFeeLessThan(String value) {
            addCriterion("buyer_extra_fee <", value, "buyerExtraFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraFeeLessThanOrEqualTo(String value) {
            addCriterion("buyer_extra_fee <=", value, "buyerExtraFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraFeeLike(String value) {
            addCriterion("buyer_extra_fee like", value, "buyerExtraFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraFeeNotLike(String value) {
            addCriterion("buyer_extra_fee not like", value, "buyerExtraFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraFeeIn(List<String> values) {
            addCriterion("buyer_extra_fee in", values, "buyerExtraFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraFeeNotIn(List<String> values) {
            addCriterion("buyer_extra_fee not in", values, "buyerExtraFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraFeeBetween(String value1, String value2) {
            addCriterion("buyer_extra_fee between", value1, value2, "buyerExtraFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraFeeNotBetween(String value1, String value2) {
            addCriterion("buyer_extra_fee not between", value1, value2, "buyerExtraFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxIsNull() {
            addCriterion("renting_extra_tax is null");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxIsNotNull() {
            addCriterion("renting_extra_tax is not null");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxEqualTo(String value) {
            addCriterion("renting_extra_tax =", value, "rentingExtraTax");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxNotEqualTo(String value) {
            addCriterion("renting_extra_tax <>", value, "rentingExtraTax");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxGreaterThan(String value) {
            addCriterion("renting_extra_tax >", value, "rentingExtraTax");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxGreaterThanOrEqualTo(String value) {
            addCriterion("renting_extra_tax >=", value, "rentingExtraTax");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxLessThan(String value) {
            addCriterion("renting_extra_tax <", value, "rentingExtraTax");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxLessThanOrEqualTo(String value) {
            addCriterion("renting_extra_tax <=", value, "rentingExtraTax");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxLike(String value) {
            addCriterion("renting_extra_tax like", value, "rentingExtraTax");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxNotLike(String value) {
            addCriterion("renting_extra_tax not like", value, "rentingExtraTax");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxIn(List<String> values) {
            addCriterion("renting_extra_tax in", values, "rentingExtraTax");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxNotIn(List<String> values) {
            addCriterion("renting_extra_tax not in", values, "rentingExtraTax");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxBetween(String value1, String value2) {
            addCriterion("renting_extra_tax between", value1, value2, "rentingExtraTax");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxNotBetween(String value1, String value2) {
            addCriterion("renting_extra_tax not between", value1, value2, "rentingExtraTax");
            return (Criteria) this;
        }

        public Criteria andRentingExtraFeeIsNull() {
            addCriterion("renting_extra_fee is null");
            return (Criteria) this;
        }

        public Criteria andRentingExtraFeeIsNotNull() {
            addCriterion("renting_extra_fee is not null");
            return (Criteria) this;
        }

        public Criteria andRentingExtraFeeEqualTo(String value) {
            addCriterion("renting_extra_fee =", value, "rentingExtraFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraFeeNotEqualTo(String value) {
            addCriterion("renting_extra_fee <>", value, "rentingExtraFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraFeeGreaterThan(String value) {
            addCriterion("renting_extra_fee >", value, "rentingExtraFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraFeeGreaterThanOrEqualTo(String value) {
            addCriterion("renting_extra_fee >=", value, "rentingExtraFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraFeeLessThan(String value) {
            addCriterion("renting_extra_fee <", value, "rentingExtraFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraFeeLessThanOrEqualTo(String value) {
            addCriterion("renting_extra_fee <=", value, "rentingExtraFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraFeeLike(String value) {
            addCriterion("renting_extra_fee like", value, "rentingExtraFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraFeeNotLike(String value) {
            addCriterion("renting_extra_fee not like", value, "rentingExtraFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraFeeIn(List<String> values) {
            addCriterion("renting_extra_fee in", values, "rentingExtraFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraFeeNotIn(List<String> values) {
            addCriterion("renting_extra_fee not in", values, "rentingExtraFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraFeeBetween(String value1, String value2) {
            addCriterion("renting_extra_fee between", value1, value2, "rentingExtraFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraFeeNotBetween(String value1, String value2) {
            addCriterion("renting_extra_fee not between", value1, value2, "rentingExtraFee");
            return (Criteria) this;
        }

        public Criteria andDescriptionTypeIsNull() {
            addCriterion("description_type is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionTypeIsNotNull() {
            addCriterion("description_type is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionTypeEqualTo(Integer value) {
            addCriterion("description_type =", value, "descriptionType");
            return (Criteria) this;
        }

        public Criteria andDescriptionTypeNotEqualTo(Integer value) {
            addCriterion("description_type <>", value, "descriptionType");
            return (Criteria) this;
        }

        public Criteria andDescriptionTypeGreaterThan(Integer value) {
            addCriterion("description_type >", value, "descriptionType");
            return (Criteria) this;
        }

        public Criteria andDescriptionTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("description_type >=", value, "descriptionType");
            return (Criteria) this;
        }

        public Criteria andDescriptionTypeLessThan(Integer value) {
            addCriterion("description_type <", value, "descriptionType");
            return (Criteria) this;
        }

        public Criteria andDescriptionTypeLessThanOrEqualTo(Integer value) {
            addCriterion("description_type <=", value, "descriptionType");
            return (Criteria) this;
        }

        public Criteria andDescriptionTypeIn(List<Integer> values) {
            addCriterion("description_type in", values, "descriptionType");
            return (Criteria) this;
        }

        public Criteria andDescriptionTypeNotIn(List<Integer> values) {
            addCriterion("description_type not in", values, "descriptionType");
            return (Criteria) this;
        }

        public Criteria andDescriptionTypeBetween(Integer value1, Integer value2) {
            addCriterion("description_type between", value1, value2, "descriptionType");
            return (Criteria) this;
        }

        public Criteria andDescriptionTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("description_type not between", value1, value2, "descriptionType");
            return (Criteria) this;
        }

        public Criteria andDescriptionContentIsNull() {
            addCriterion("description_content is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionContentIsNotNull() {
            addCriterion("description_content is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionContentEqualTo(String value) {
            addCriterion("description_content =", value, "descriptionContent");
            return (Criteria) this;
        }

        public Criteria andDescriptionContentNotEqualTo(String value) {
            addCriterion("description_content <>", value, "descriptionContent");
            return (Criteria) this;
        }

        public Criteria andDescriptionContentGreaterThan(String value) {
            addCriterion("description_content >", value, "descriptionContent");
            return (Criteria) this;
        }

        public Criteria andDescriptionContentGreaterThanOrEqualTo(String value) {
            addCriterion("description_content >=", value, "descriptionContent");
            return (Criteria) this;
        }

        public Criteria andDescriptionContentLessThan(String value) {
            addCriterion("description_content <", value, "descriptionContent");
            return (Criteria) this;
        }

        public Criteria andDescriptionContentLessThanOrEqualTo(String value) {
            addCriterion("description_content <=", value, "descriptionContent");
            return (Criteria) this;
        }

        public Criteria andDescriptionContentLike(String value) {
            addCriterion("description_content like", value, "descriptionContent");
            return (Criteria) this;
        }

        public Criteria andDescriptionContentNotLike(String value) {
            addCriterion("description_content not like", value, "descriptionContent");
            return (Criteria) this;
        }

        public Criteria andDescriptionContentIn(List<String> values) {
            addCriterion("description_content in", values, "descriptionContent");
            return (Criteria) this;
        }

        public Criteria andDescriptionContentNotIn(List<String> values) {
            addCriterion("description_content not in", values, "descriptionContent");
            return (Criteria) this;
        }

        public Criteria andDescriptionContentBetween(String value1, String value2) {
            addCriterion("description_content between", value1, value2, "descriptionContent");
            return (Criteria) this;
        }

        public Criteria andDescriptionContentNotBetween(String value1, String value2) {
            addCriterion("description_content not between", value1, value2, "descriptionContent");
            return (Criteria) this;
        }

        public Criteria andInstallmentInterestRateIsNull() {
            addCriterion("installment_interest_rate is null");
            return (Criteria) this;
        }

        public Criteria andInstallmentInterestRateIsNotNull() {
            addCriterion("installment_interest_rate is not null");
            return (Criteria) this;
        }

        public Criteria andInstallmentInterestRateEqualTo(String value) {
            addCriterion("installment_interest_rate =", value, "installmentInterestRate");
            return (Criteria) this;
        }

        public Criteria andInstallmentInterestRateNotEqualTo(String value) {
            addCriterion("installment_interest_rate <>", value, "installmentInterestRate");
            return (Criteria) this;
        }

        public Criteria andInstallmentInterestRateGreaterThan(String value) {
            addCriterion("installment_interest_rate >", value, "installmentInterestRate");
            return (Criteria) this;
        }

        public Criteria andInstallmentInterestRateGreaterThanOrEqualTo(String value) {
            addCriterion("installment_interest_rate >=", value, "installmentInterestRate");
            return (Criteria) this;
        }

        public Criteria andInstallmentInterestRateLessThan(String value) {
            addCriterion("installment_interest_rate <", value, "installmentInterestRate");
            return (Criteria) this;
        }

        public Criteria andInstallmentInterestRateLessThanOrEqualTo(String value) {
            addCriterion("installment_interest_rate <=", value, "installmentInterestRate");
            return (Criteria) this;
        }

        public Criteria andInstallmentInterestRateLike(String value) {
            addCriterion("installment_interest_rate like", value, "installmentInterestRate");
            return (Criteria) this;
        }

        public Criteria andInstallmentInterestRateNotLike(String value) {
            addCriterion("installment_interest_rate not like", value, "installmentInterestRate");
            return (Criteria) this;
        }

        public Criteria andInstallmentInterestRateIn(List<String> values) {
            addCriterion("installment_interest_rate in", values, "installmentInterestRate");
            return (Criteria) this;
        }

        public Criteria andInstallmentInterestRateNotIn(List<String> values) {
            addCriterion("installment_interest_rate not in", values, "installmentInterestRate");
            return (Criteria) this;
        }

        public Criteria andInstallmentInterestRateBetween(String value1, String value2) {
            addCriterion("installment_interest_rate between", value1, value2, "installmentInterestRate");
            return (Criteria) this;
        }

        public Criteria andInstallmentInterestRateNotBetween(String value1, String value2) {
            addCriterion("installment_interest_rate not between", value1, value2, "installmentInterestRate");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIsNull() {
            addCriterion("payment_method is null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIsNotNull() {
            addCriterion("payment_method is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodEqualTo(Integer value) {
            addCriterion("payment_method =", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotEqualTo(Integer value) {
            addCriterion("payment_method <>", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodGreaterThan(Integer value) {
            addCriterion("payment_method >", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodGreaterThanOrEqualTo(Integer value) {
            addCriterion("payment_method >=", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLessThan(Integer value) {
            addCriterion("payment_method <", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLessThanOrEqualTo(Integer value) {
            addCriterion("payment_method <=", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIn(List<Integer> values) {
            addCriterion("payment_method in", values, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotIn(List<Integer> values) {
            addCriterion("payment_method not in", values, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodBetween(Integer value1, Integer value2) {
            addCriterion("payment_method between", value1, value2, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotBetween(Integer value1, Integer value2) {
            addCriterion("payment_method not between", value1, value2, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andTransactionSituationIsNull() {
            addCriterion("transaction_situation is null");
            return (Criteria) this;
        }

        public Criteria andTransactionSituationIsNotNull() {
            addCriterion("transaction_situation is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionSituationEqualTo(Integer value) {
            addCriterion("transaction_situation =", value, "transactionSituation");
            return (Criteria) this;
        }

        public Criteria andTransactionSituationNotEqualTo(Integer value) {
            addCriterion("transaction_situation <>", value, "transactionSituation");
            return (Criteria) this;
        }

        public Criteria andTransactionSituationGreaterThan(Integer value) {
            addCriterion("transaction_situation >", value, "transactionSituation");
            return (Criteria) this;
        }

        public Criteria andTransactionSituationGreaterThanOrEqualTo(Integer value) {
            addCriterion("transaction_situation >=", value, "transactionSituation");
            return (Criteria) this;
        }

        public Criteria andTransactionSituationLessThan(Integer value) {
            addCriterion("transaction_situation <", value, "transactionSituation");
            return (Criteria) this;
        }

        public Criteria andTransactionSituationLessThanOrEqualTo(Integer value) {
            addCriterion("transaction_situation <=", value, "transactionSituation");
            return (Criteria) this;
        }

        public Criteria andTransactionSituationIn(List<Integer> values) {
            addCriterion("transaction_situation in", values, "transactionSituation");
            return (Criteria) this;
        }

        public Criteria andTransactionSituationNotIn(List<Integer> values) {
            addCriterion("transaction_situation not in", values, "transactionSituation");
            return (Criteria) this;
        }

        public Criteria andTransactionSituationBetween(Integer value1, Integer value2) {
            addCriterion("transaction_situation between", value1, value2, "transactionSituation");
            return (Criteria) this;
        }

        public Criteria andTransactionSituationNotBetween(Integer value1, Integer value2) {
            addCriterion("transaction_situation not between", value1, value2, "transactionSituation");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenIsNull() {
            addCriterion("tax_burden is null");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenIsNotNull() {
            addCriterion("tax_burden is not null");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenEqualTo(Integer value) {
            addCriterion("tax_burden =", value, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenNotEqualTo(Integer value) {
            addCriterion("tax_burden <>", value, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenGreaterThan(Integer value) {
            addCriterion("tax_burden >", value, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenGreaterThanOrEqualTo(Integer value) {
            addCriterion("tax_burden >=", value, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenLessThan(Integer value) {
            addCriterion("tax_burden <", value, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenLessThanOrEqualTo(Integer value) {
            addCriterion("tax_burden <=", value, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenIn(List<Integer> values) {
            addCriterion("tax_burden in", values, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenNotIn(List<Integer> values) {
            addCriterion("tax_burden not in", values, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenBetween(Integer value1, Integer value2) {
            addCriterion("tax_burden between", value1, value2, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenNotBetween(Integer value1, Integer value2) {
            addCriterion("tax_burden not between", value1, value2, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andScopePropertyIsNull() {
            addCriterion("scope_property is null");
            return (Criteria) this;
        }

        public Criteria andScopePropertyIsNotNull() {
            addCriterion("scope_property is not null");
            return (Criteria) this;
        }

        public Criteria andScopePropertyEqualTo(Integer value) {
            addCriterion("scope_property =", value, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyNotEqualTo(Integer value) {
            addCriterion("scope_property <>", value, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyGreaterThan(Integer value) {
            addCriterion("scope_property >", value, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyGreaterThanOrEqualTo(Integer value) {
            addCriterion("scope_property >=", value, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyLessThan(Integer value) {
            addCriterion("scope_property <", value, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyLessThanOrEqualTo(Integer value) {
            addCriterion("scope_property <=", value, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyIn(List<Integer> values) {
            addCriterion("scope_property in", values, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyNotIn(List<Integer> values) {
            addCriterion("scope_property not in", values, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyBetween(Integer value1, Integer value2) {
            addCriterion("scope_property between", value1, value2, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyNotBetween(Integer value1, Integer value2) {
            addCriterion("scope_property not between", value1, value2, "scopeProperty");
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

        public Criteria andScopePropertyExplainIsNull() {
            addCriterion("scope_property_explain is null");
            return (Criteria) this;
        }

        public Criteria andScopePropertyExplainIsNotNull() {
            addCriterion("scope_property_explain is not null");
            return (Criteria) this;
        }

        public Criteria andScopePropertyExplainEqualTo(String value) {
            addCriterion("scope_property_explain =", value, "scopePropertyExplain");
            return (Criteria) this;
        }

        public Criteria andScopePropertyExplainNotEqualTo(String value) {
            addCriterion("scope_property_explain <>", value, "scopePropertyExplain");
            return (Criteria) this;
        }

        public Criteria andScopePropertyExplainGreaterThan(String value) {
            addCriterion("scope_property_explain >", value, "scopePropertyExplain");
            return (Criteria) this;
        }

        public Criteria andScopePropertyExplainGreaterThanOrEqualTo(String value) {
            addCriterion("scope_property_explain >=", value, "scopePropertyExplain");
            return (Criteria) this;
        }

        public Criteria andScopePropertyExplainLessThan(String value) {
            addCriterion("scope_property_explain <", value, "scopePropertyExplain");
            return (Criteria) this;
        }

        public Criteria andScopePropertyExplainLessThanOrEqualTo(String value) {
            addCriterion("scope_property_explain <=", value, "scopePropertyExplain");
            return (Criteria) this;
        }

        public Criteria andScopePropertyExplainLike(String value) {
            addCriterion("scope_property_explain like", value, "scopePropertyExplain");
            return (Criteria) this;
        }

        public Criteria andScopePropertyExplainNotLike(String value) {
            addCriterion("scope_property_explain not like", value, "scopePropertyExplain");
            return (Criteria) this;
        }

        public Criteria andScopePropertyExplainIn(List<String> values) {
            addCriterion("scope_property_explain in", values, "scopePropertyExplain");
            return (Criteria) this;
        }

        public Criteria andScopePropertyExplainNotIn(List<String> values) {
            addCriterion("scope_property_explain not in", values, "scopePropertyExplain");
            return (Criteria) this;
        }

        public Criteria andScopePropertyExplainBetween(String value1, String value2) {
            addCriterion("scope_property_explain between", value1, value2, "scopePropertyExplain");
            return (Criteria) this;
        }

        public Criteria andScopePropertyExplainNotBetween(String value1, String value2) {
            addCriterion("scope_property_explain not between", value1, value2, "scopePropertyExplain");
            return (Criteria) this;
        }

        public Criteria andDownPaymentRatioIsNull() {
            addCriterion("down_payment_ratio is null");
            return (Criteria) this;
        }

        public Criteria andDownPaymentRatioIsNotNull() {
            addCriterion("down_payment_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andDownPaymentRatioEqualTo(String value) {
            addCriterion("down_payment_ratio =", value, "downPaymentRatio");
            return (Criteria) this;
        }

        public Criteria andDownPaymentRatioNotEqualTo(String value) {
            addCriterion("down_payment_ratio <>", value, "downPaymentRatio");
            return (Criteria) this;
        }

        public Criteria andDownPaymentRatioGreaterThan(String value) {
            addCriterion("down_payment_ratio >", value, "downPaymentRatio");
            return (Criteria) this;
        }

        public Criteria andDownPaymentRatioGreaterThanOrEqualTo(String value) {
            addCriterion("down_payment_ratio >=", value, "downPaymentRatio");
            return (Criteria) this;
        }

        public Criteria andDownPaymentRatioLessThan(String value) {
            addCriterion("down_payment_ratio <", value, "downPaymentRatio");
            return (Criteria) this;
        }

        public Criteria andDownPaymentRatioLessThanOrEqualTo(String value) {
            addCriterion("down_payment_ratio <=", value, "downPaymentRatio");
            return (Criteria) this;
        }

        public Criteria andDownPaymentRatioLike(String value) {
            addCriterion("down_payment_ratio like", value, "downPaymentRatio");
            return (Criteria) this;
        }

        public Criteria andDownPaymentRatioNotLike(String value) {
            addCriterion("down_payment_ratio not like", value, "downPaymentRatio");
            return (Criteria) this;
        }

        public Criteria andDownPaymentRatioIn(List<String> values) {
            addCriterion("down_payment_ratio in", values, "downPaymentRatio");
            return (Criteria) this;
        }

        public Criteria andDownPaymentRatioNotIn(List<String> values) {
            addCriterion("down_payment_ratio not in", values, "downPaymentRatio");
            return (Criteria) this;
        }

        public Criteria andDownPaymentRatioBetween(String value1, String value2) {
            addCriterion("down_payment_ratio between", value1, value2, "downPaymentRatio");
            return (Criteria) this;
        }

        public Criteria andDownPaymentRatioNotBetween(String value1, String value2) {
            addCriterion("down_payment_ratio not between", value1, value2, "downPaymentRatio");
            return (Criteria) this;
        }

        public Criteria andLendingRateIsNull() {
            addCriterion("lending_rate is null");
            return (Criteria) this;
        }

        public Criteria andLendingRateIsNotNull() {
            addCriterion("lending_rate is not null");
            return (Criteria) this;
        }

        public Criteria andLendingRateEqualTo(String value) {
            addCriterion("lending_rate =", value, "lendingRate");
            return (Criteria) this;
        }

        public Criteria andLendingRateNotEqualTo(String value) {
            addCriterion("lending_rate <>", value, "lendingRate");
            return (Criteria) this;
        }

        public Criteria andLendingRateGreaterThan(String value) {
            addCriterion("lending_rate >", value, "lendingRate");
            return (Criteria) this;
        }

        public Criteria andLendingRateGreaterThanOrEqualTo(String value) {
            addCriterion("lending_rate >=", value, "lendingRate");
            return (Criteria) this;
        }

        public Criteria andLendingRateLessThan(String value) {
            addCriterion("lending_rate <", value, "lendingRate");
            return (Criteria) this;
        }

        public Criteria andLendingRateLessThanOrEqualTo(String value) {
            addCriterion("lending_rate <=", value, "lendingRate");
            return (Criteria) this;
        }

        public Criteria andLendingRateLike(String value) {
            addCriterion("lending_rate like", value, "lendingRate");
            return (Criteria) this;
        }

        public Criteria andLendingRateNotLike(String value) {
            addCriterion("lending_rate not like", value, "lendingRate");
            return (Criteria) this;
        }

        public Criteria andLendingRateIn(List<String> values) {
            addCriterion("lending_rate in", values, "lendingRate");
            return (Criteria) this;
        }

        public Criteria andLendingRateNotIn(List<String> values) {
            addCriterion("lending_rate not in", values, "lendingRate");
            return (Criteria) this;
        }

        public Criteria andLendingRateBetween(String value1, String value2) {
            addCriterion("lending_rate between", value1, value2, "lendingRate");
            return (Criteria) this;
        }

        public Criteria andLendingRateNotBetween(String value1, String value2) {
            addCriterion("lending_rate not between", value1, value2, "lendingRate");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodIsNull() {
            addCriterion("loan_period is null");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodIsNotNull() {
            addCriterion("loan_period is not null");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodEqualTo(Integer value) {
            addCriterion("loan_period =", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodNotEqualTo(Integer value) {
            addCriterion("loan_period <>", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodGreaterThan(Integer value) {
            addCriterion("loan_period >", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("loan_period >=", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLessThan(Integer value) {
            addCriterion("loan_period <", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("loan_period <=", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodIn(List<Integer> values) {
            addCriterion("loan_period in", values, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodNotIn(List<Integer> values) {
            addCriterion("loan_period not in", values, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodBetween(Integer value1, Integer value2) {
            addCriterion("loan_period between", value1, value2, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("loan_period not between", value1, value2, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andDepositIsNull() {
            addCriterion("deposit is null");
            return (Criteria) this;
        }

        public Criteria andDepositIsNotNull() {
            addCriterion("deposit is not null");
            return (Criteria) this;
        }

        public Criteria andDepositEqualTo(String value) {
            addCriterion("deposit =", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotEqualTo(String value) {
            addCriterion("deposit <>", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThan(String value) {
            addCriterion("deposit >", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThanOrEqualTo(String value) {
            addCriterion("deposit >=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThan(String value) {
            addCriterion("deposit <", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThanOrEqualTo(String value) {
            addCriterion("deposit <=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLike(String value) {
            addCriterion("deposit like", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotLike(String value) {
            addCriterion("deposit not like", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositIn(List<String> values) {
            addCriterion("deposit in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotIn(List<String> values) {
            addCriterion("deposit not in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositBetween(String value1, String value2) {
            addCriterion("deposit between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotBetween(String value1, String value2) {
            addCriterion("deposit not between", value1, value2, "deposit");
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

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andLandBuyerSellerIsNull() {
            addCriterion("land_buyer_seller is null");
            return (Criteria) this;
        }

        public Criteria andLandBuyerSellerIsNotNull() {
            addCriterion("land_buyer_seller is not null");
            return (Criteria) this;
        }

        public Criteria andLandBuyerSellerEqualTo(String value) {
            addCriterion("land_buyer_seller =", value, "landBuyerSeller");
            return (Criteria) this;
        }

        public Criteria andLandBuyerSellerNotEqualTo(String value) {
            addCriterion("land_buyer_seller <>", value, "landBuyerSeller");
            return (Criteria) this;
        }

        public Criteria andLandBuyerSellerGreaterThan(String value) {
            addCriterion("land_buyer_seller >", value, "landBuyerSeller");
            return (Criteria) this;
        }

        public Criteria andLandBuyerSellerGreaterThanOrEqualTo(String value) {
            addCriterion("land_buyer_seller >=", value, "landBuyerSeller");
            return (Criteria) this;
        }

        public Criteria andLandBuyerSellerLessThan(String value) {
            addCriterion("land_buyer_seller <", value, "landBuyerSeller");
            return (Criteria) this;
        }

        public Criteria andLandBuyerSellerLessThanOrEqualTo(String value) {
            addCriterion("land_buyer_seller <=", value, "landBuyerSeller");
            return (Criteria) this;
        }

        public Criteria andLandBuyerSellerLike(String value) {
            addCriterion("land_buyer_seller like", value, "landBuyerSeller");
            return (Criteria) this;
        }

        public Criteria andLandBuyerSellerNotLike(String value) {
            addCriterion("land_buyer_seller not like", value, "landBuyerSeller");
            return (Criteria) this;
        }

        public Criteria andLandBuyerSellerIn(List<String> values) {
            addCriterion("land_buyer_seller in", values, "landBuyerSeller");
            return (Criteria) this;
        }

        public Criteria andLandBuyerSellerNotIn(List<String> values) {
            addCriterion("land_buyer_seller not in", values, "landBuyerSeller");
            return (Criteria) this;
        }

        public Criteria andLandBuyerSellerBetween(String value1, String value2) {
            addCriterion("land_buyer_seller between", value1, value2, "landBuyerSeller");
            return (Criteria) this;
        }

        public Criteria andLandBuyerSellerNotBetween(String value1, String value2) {
            addCriterion("land_buyer_seller not between", value1, value2, "landBuyerSeller");
            return (Criteria) this;
        }

        public Criteria andPriceTypeIsNull() {
            addCriterion("price_type is null");
            return (Criteria) this;
        }

        public Criteria andPriceTypeIsNotNull() {
            addCriterion("price_type is not null");
            return (Criteria) this;
        }

        public Criteria andPriceTypeEqualTo(Integer value) {
            addCriterion("price_type =", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeNotEqualTo(Integer value) {
            addCriterion("price_type <>", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeGreaterThan(Integer value) {
            addCriterion("price_type >", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("price_type >=", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeLessThan(Integer value) {
            addCriterion("price_type <", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("price_type <=", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeIn(List<Integer> values) {
            addCriterion("price_type in", values, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeNotIn(List<Integer> values) {
            addCriterion("price_type not in", values, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeBetween(Integer value1, Integer value2) {
            addCriterion("price_type between", value1, value2, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("price_type not between", value1, value2, "priceType");
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

        public Criteria andPriceConnotationUnitIsNull() {
            addCriterion("price_connotation_unit is null");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationUnitIsNotNull() {
            addCriterion("price_connotation_unit is not null");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationUnitEqualTo(String value) {
            addCriterion("price_connotation_unit =", value, "priceConnotationUnit");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationUnitNotEqualTo(String value) {
            addCriterion("price_connotation_unit <>", value, "priceConnotationUnit");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationUnitGreaterThan(String value) {
            addCriterion("price_connotation_unit >", value, "priceConnotationUnit");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationUnitGreaterThanOrEqualTo(String value) {
            addCriterion("price_connotation_unit >=", value, "priceConnotationUnit");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationUnitLessThan(String value) {
            addCriterion("price_connotation_unit <", value, "priceConnotationUnit");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationUnitLessThanOrEqualTo(String value) {
            addCriterion("price_connotation_unit <=", value, "priceConnotationUnit");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationUnitLike(String value) {
            addCriterion("price_connotation_unit like", value, "priceConnotationUnit");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationUnitNotLike(String value) {
            addCriterion("price_connotation_unit not like", value, "priceConnotationUnit");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationUnitIn(List<String> values) {
            addCriterion("price_connotation_unit in", values, "priceConnotationUnit");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationUnitNotIn(List<String> values) {
            addCriterion("price_connotation_unit not in", values, "priceConnotationUnit");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationUnitBetween(String value1, String value2) {
            addCriterion("price_connotation_unit between", value1, value2, "priceConnotationUnit");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationUnitNotBetween(String value1, String value2) {
            addCriterion("price_connotation_unit not between", value1, value2, "priceConnotationUnit");
            return (Criteria) this;
        }

        public Criteria andPerMuPriceIsNull() {
            addCriterion("per_mu_price is null");
            return (Criteria) this;
        }

        public Criteria andPerMuPriceIsNotNull() {
            addCriterion("per_mu_price is not null");
            return (Criteria) this;
        }

        public Criteria andPerMuPriceEqualTo(BigDecimal value) {
            addCriterion("per_mu_price =", value, "perMuPrice");
            return (Criteria) this;
        }

        public Criteria andPerMuPriceNotEqualTo(BigDecimal value) {
            addCriterion("per_mu_price <>", value, "perMuPrice");
            return (Criteria) this;
        }

        public Criteria andPerMuPriceGreaterThan(BigDecimal value) {
            addCriterion("per_mu_price >", value, "perMuPrice");
            return (Criteria) this;
        }

        public Criteria andPerMuPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("per_mu_price >=", value, "perMuPrice");
            return (Criteria) this;
        }

        public Criteria andPerMuPriceLessThan(BigDecimal value) {
            addCriterion("per_mu_price <", value, "perMuPrice");
            return (Criteria) this;
        }

        public Criteria andPerMuPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("per_mu_price <=", value, "perMuPrice");
            return (Criteria) this;
        }

        public Criteria andPerMuPriceIn(List<BigDecimal> values) {
            addCriterion("per_mu_price in", values, "perMuPrice");
            return (Criteria) this;
        }

        public Criteria andPerMuPriceNotIn(List<BigDecimal> values) {
            addCriterion("per_mu_price not in", values, "perMuPrice");
            return (Criteria) this;
        }

        public Criteria andPerMuPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("per_mu_price between", value1, value2, "perMuPrice");
            return (Criteria) this;
        }

        public Criteria andPerMuPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("per_mu_price not between", value1, value2, "perMuPrice");
            return (Criteria) this;
        }

        public Criteria andBisMarkIsNull() {
            addCriterion("bis_mark is null");
            return (Criteria) this;
        }

        public Criteria andBisMarkIsNotNull() {
            addCriterion("bis_mark is not null");
            return (Criteria) this;
        }

        public Criteria andBisMarkEqualTo(Boolean value) {
            addCriterion("bis_mark =", value, "bisMark");
            return (Criteria) this;
        }

        public Criteria andBisMarkNotEqualTo(Boolean value) {
            addCriterion("bis_mark <>", value, "bisMark");
            return (Criteria) this;
        }

        public Criteria andBisMarkGreaterThan(Boolean value) {
            addCriterion("bis_mark >", value, "bisMark");
            return (Criteria) this;
        }

        public Criteria andBisMarkGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_mark >=", value, "bisMark");
            return (Criteria) this;
        }

        public Criteria andBisMarkLessThan(Boolean value) {
            addCriterion("bis_mark <", value, "bisMark");
            return (Criteria) this;
        }

        public Criteria andBisMarkLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_mark <=", value, "bisMark");
            return (Criteria) this;
        }

        public Criteria andBisMarkIn(List<Boolean> values) {
            addCriterion("bis_mark in", values, "bisMark");
            return (Criteria) this;
        }

        public Criteria andBisMarkNotIn(List<Boolean> values) {
            addCriterion("bis_mark not in", values, "bisMark");
            return (Criteria) this;
        }

        public Criteria andBisMarkBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_mark between", value1, value2, "bisMark");
            return (Criteria) this;
        }

        public Criteria andBisMarkNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_mark not between", value1, value2, "bisMark");
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