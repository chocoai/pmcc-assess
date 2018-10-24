package com.copower.pmcc.assess.dal.basic.entity;

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

        public Criteria andInformationTypeIsNull() {
            addCriterion("information_type is null");
            return (Criteria) this;
        }

        public Criteria andInformationTypeIsNotNull() {
            addCriterion("information_type is not null");
            return (Criteria) this;
        }

        public Criteria andInformationTypeEqualTo(String value) {
            addCriterion("information_type =", value, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeNotEqualTo(String value) {
            addCriterion("information_type <>", value, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeGreaterThan(String value) {
            addCriterion("information_type >", value, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeGreaterThanOrEqualTo(String value) {
            addCriterion("information_type >=", value, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeLessThan(String value) {
            addCriterion("information_type <", value, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeLessThanOrEqualTo(String value) {
            addCriterion("information_type <=", value, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeLike(String value) {
            addCriterion("information_type like", value, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeNotLike(String value) {
            addCriterion("information_type not like", value, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeIn(List<String> values) {
            addCriterion("information_type in", values, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeNotIn(List<String> values) {
            addCriterion("information_type not in", values, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeBetween(String value1, String value2) {
            addCriterion("information_type between", value1, value2, "informationType");
            return (Criteria) this;
        }

        public Criteria andInformationTypeNotBetween(String value1, String value2) {
            addCriterion("information_type not between", value1, value2, "informationType");
            return (Criteria) this;
        }

        public Criteria andTradingPriceIsNull() {
            addCriterion("trading_price is null");
            return (Criteria) this;
        }

        public Criteria andTradingPriceIsNotNull() {
            addCriterion("trading_price is not null");
            return (Criteria) this;
        }

        public Criteria andTradingPriceEqualTo(BigDecimal value) {
            addCriterion("trading_price =", value, "tradingPrice");
            return (Criteria) this;
        }

        public Criteria andTradingPriceNotEqualTo(BigDecimal value) {
            addCriterion("trading_price <>", value, "tradingPrice");
            return (Criteria) this;
        }

        public Criteria andTradingPriceGreaterThan(BigDecimal value) {
            addCriterion("trading_price >", value, "tradingPrice");
            return (Criteria) this;
        }

        public Criteria andTradingPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trading_price >=", value, "tradingPrice");
            return (Criteria) this;
        }

        public Criteria andTradingPriceLessThan(BigDecimal value) {
            addCriterion("trading_price <", value, "tradingPrice");
            return (Criteria) this;
        }

        public Criteria andTradingPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trading_price <=", value, "tradingPrice");
            return (Criteria) this;
        }

        public Criteria andTradingPriceIn(List<BigDecimal> values) {
            addCriterion("trading_price in", values, "tradingPrice");
            return (Criteria) this;
        }

        public Criteria andTradingPriceNotIn(List<BigDecimal> values) {
            addCriterion("trading_price not in", values, "tradingPrice");
            return (Criteria) this;
        }

        public Criteria andTradingPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trading_price between", value1, value2, "tradingPrice");
            return (Criteria) this;
        }

        public Criteria andTradingPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trading_price not between", value1, value2, "tradingPrice");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxFeeIsNull() {
            addCriterion("buyer_extra_tax_fee is null");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxFeeIsNotNull() {
            addCriterion("buyer_extra_tax_fee is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxFeeEqualTo(String value) {
            addCriterion("buyer_extra_tax_fee =", value, "buyerExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxFeeNotEqualTo(String value) {
            addCriterion("buyer_extra_tax_fee <>", value, "buyerExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxFeeGreaterThan(String value) {
            addCriterion("buyer_extra_tax_fee >", value, "buyerExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxFeeGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_extra_tax_fee >=", value, "buyerExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxFeeLessThan(String value) {
            addCriterion("buyer_extra_tax_fee <", value, "buyerExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxFeeLessThanOrEqualTo(String value) {
            addCriterion("buyer_extra_tax_fee <=", value, "buyerExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxFeeLike(String value) {
            addCriterion("buyer_extra_tax_fee like", value, "buyerExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxFeeNotLike(String value) {
            addCriterion("buyer_extra_tax_fee not like", value, "buyerExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxFeeIn(List<String> values) {
            addCriterion("buyer_extra_tax_fee in", values, "buyerExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxFeeNotIn(List<String> values) {
            addCriterion("buyer_extra_tax_fee not in", values, "buyerExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxFeeBetween(String value1, String value2) {
            addCriterion("buyer_extra_tax_fee between", value1, value2, "buyerExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andBuyerExtraTaxFeeNotBetween(String value1, String value2) {
            addCriterion("buyer_extra_tax_fee not between", value1, value2, "buyerExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxFeeIsNull() {
            addCriterion("renting_extra_tax_fee is null");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxFeeIsNotNull() {
            addCriterion("renting_extra_tax_fee is not null");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxFeeEqualTo(String value) {
            addCriterion("renting_extra_tax_fee =", value, "rentingExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxFeeNotEqualTo(String value) {
            addCriterion("renting_extra_tax_fee <>", value, "rentingExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxFeeGreaterThan(String value) {
            addCriterion("renting_extra_tax_fee >", value, "rentingExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxFeeGreaterThanOrEqualTo(String value) {
            addCriterion("renting_extra_tax_fee >=", value, "rentingExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxFeeLessThan(String value) {
            addCriterion("renting_extra_tax_fee <", value, "rentingExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxFeeLessThanOrEqualTo(String value) {
            addCriterion("renting_extra_tax_fee <=", value, "rentingExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxFeeLike(String value) {
            addCriterion("renting_extra_tax_fee like", value, "rentingExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxFeeNotLike(String value) {
            addCriterion("renting_extra_tax_fee not like", value, "rentingExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxFeeIn(List<String> values) {
            addCriterion("renting_extra_tax_fee in", values, "rentingExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxFeeNotIn(List<String> values) {
            addCriterion("renting_extra_tax_fee not in", values, "rentingExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxFeeBetween(String value1, String value2) {
            addCriterion("renting_extra_tax_fee between", value1, value2, "rentingExtraTaxFee");
            return (Criteria) this;
        }

        public Criteria andRentingExtraTaxFeeNotBetween(String value1, String value2) {
            addCriterion("renting_extra_tax_fee not between", value1, value2, "rentingExtraTaxFee");
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

        public Criteria andPaymentMethodIsNull() {
            addCriterion("payment_method is null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIsNotNull() {
            addCriterion("payment_method is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodEqualTo(String value) {
            addCriterion("payment_method =", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotEqualTo(String value) {
            addCriterion("payment_method <>", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodGreaterThan(String value) {
            addCriterion("payment_method >", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodGreaterThanOrEqualTo(String value) {
            addCriterion("payment_method >=", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLessThan(String value) {
            addCriterion("payment_method <", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLessThanOrEqualTo(String value) {
            addCriterion("payment_method <=", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLike(String value) {
            addCriterion("payment_method like", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotLike(String value) {
            addCriterion("payment_method not like", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIn(List<String> values) {
            addCriterion("payment_method in", values, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotIn(List<String> values) {
            addCriterion("payment_method not in", values, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodBetween(String value1, String value2) {
            addCriterion("payment_method between", value1, value2, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotBetween(String value1, String value2) {
            addCriterion("payment_method not between", value1, value2, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andNormalTransactionIsNull() {
            addCriterion("normal_transaction is null");
            return (Criteria) this;
        }

        public Criteria andNormalTransactionIsNotNull() {
            addCriterion("normal_transaction is not null");
            return (Criteria) this;
        }

        public Criteria andNormalTransactionEqualTo(String value) {
            addCriterion("normal_transaction =", value, "normalTransaction");
            return (Criteria) this;
        }

        public Criteria andNormalTransactionNotEqualTo(String value) {
            addCriterion("normal_transaction <>", value, "normalTransaction");
            return (Criteria) this;
        }

        public Criteria andNormalTransactionGreaterThan(String value) {
            addCriterion("normal_transaction >", value, "normalTransaction");
            return (Criteria) this;
        }

        public Criteria andNormalTransactionGreaterThanOrEqualTo(String value) {
            addCriterion("normal_transaction >=", value, "normalTransaction");
            return (Criteria) this;
        }

        public Criteria andNormalTransactionLessThan(String value) {
            addCriterion("normal_transaction <", value, "normalTransaction");
            return (Criteria) this;
        }

        public Criteria andNormalTransactionLessThanOrEqualTo(String value) {
            addCriterion("normal_transaction <=", value, "normalTransaction");
            return (Criteria) this;
        }

        public Criteria andNormalTransactionLike(String value) {
            addCriterion("normal_transaction like", value, "normalTransaction");
            return (Criteria) this;
        }

        public Criteria andNormalTransactionNotLike(String value) {
            addCriterion("normal_transaction not like", value, "normalTransaction");
            return (Criteria) this;
        }

        public Criteria andNormalTransactionIn(List<String> values) {
            addCriterion("normal_transaction in", values, "normalTransaction");
            return (Criteria) this;
        }

        public Criteria andNormalTransactionNotIn(List<String> values) {
            addCriterion("normal_transaction not in", values, "normalTransaction");
            return (Criteria) this;
        }

        public Criteria andNormalTransactionBetween(String value1, String value2) {
            addCriterion("normal_transaction between", value1, value2, "normalTransaction");
            return (Criteria) this;
        }

        public Criteria andNormalTransactionNotBetween(String value1, String value2) {
            addCriterion("normal_transaction not between", value1, value2, "normalTransaction");
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

        public Criteria andTaxBurdenEqualTo(String value) {
            addCriterion("tax_burden =", value, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenNotEqualTo(String value) {
            addCriterion("tax_burden <>", value, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenGreaterThan(String value) {
            addCriterion("tax_burden >", value, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenGreaterThanOrEqualTo(String value) {
            addCriterion("tax_burden >=", value, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenLessThan(String value) {
            addCriterion("tax_burden <", value, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenLessThanOrEqualTo(String value) {
            addCriterion("tax_burden <=", value, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenLike(String value) {
            addCriterion("tax_burden like", value, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenNotLike(String value) {
            addCriterion("tax_burden not like", value, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenIn(List<String> values) {
            addCriterion("tax_burden in", values, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenNotIn(List<String> values) {
            addCriterion("tax_burden not in", values, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenBetween(String value1, String value2) {
            addCriterion("tax_burden between", value1, value2, "taxBurden");
            return (Criteria) this;
        }

        public Criteria andTaxBurdenNotBetween(String value1, String value2) {
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

        public Criteria andScopePropertyEqualTo(String value) {
            addCriterion("scope_property =", value, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyNotEqualTo(String value) {
            addCriterion("scope_property <>", value, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyGreaterThan(String value) {
            addCriterion("scope_property >", value, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyGreaterThanOrEqualTo(String value) {
            addCriterion("scope_property >=", value, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyLessThan(String value) {
            addCriterion("scope_property <", value, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyLessThanOrEqualTo(String value) {
            addCriterion("scope_property <=", value, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyLike(String value) {
            addCriterion("scope_property like", value, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyNotLike(String value) {
            addCriterion("scope_property not like", value, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyIn(List<String> values) {
            addCriterion("scope_property in", values, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyNotIn(List<String> values) {
            addCriterion("scope_property not in", values, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyBetween(String value1, String value2) {
            addCriterion("scope_property between", value1, value2, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andScopePropertyNotBetween(String value1, String value2) {
            addCriterion("scope_property not between", value1, value2, "scopeProperty");
            return (Criteria) this;
        }

        public Criteria andFinancingConditionsIsNull() {
            addCriterion("financing_conditions is null");
            return (Criteria) this;
        }

        public Criteria andFinancingConditionsIsNotNull() {
            addCriterion("financing_conditions is not null");
            return (Criteria) this;
        }

        public Criteria andFinancingConditionsEqualTo(String value) {
            addCriterion("financing_conditions =", value, "financingConditions");
            return (Criteria) this;
        }

        public Criteria andFinancingConditionsNotEqualTo(String value) {
            addCriterion("financing_conditions <>", value, "financingConditions");
            return (Criteria) this;
        }

        public Criteria andFinancingConditionsGreaterThan(String value) {
            addCriterion("financing_conditions >", value, "financingConditions");
            return (Criteria) this;
        }

        public Criteria andFinancingConditionsGreaterThanOrEqualTo(String value) {
            addCriterion("financing_conditions >=", value, "financingConditions");
            return (Criteria) this;
        }

        public Criteria andFinancingConditionsLessThan(String value) {
            addCriterion("financing_conditions <", value, "financingConditions");
            return (Criteria) this;
        }

        public Criteria andFinancingConditionsLessThanOrEqualTo(String value) {
            addCriterion("financing_conditions <=", value, "financingConditions");
            return (Criteria) this;
        }

        public Criteria andFinancingConditionsLike(String value) {
            addCriterion("financing_conditions like", value, "financingConditions");
            return (Criteria) this;
        }

        public Criteria andFinancingConditionsNotLike(String value) {
            addCriterion("financing_conditions not like", value, "financingConditions");
            return (Criteria) this;
        }

        public Criteria andFinancingConditionsIn(List<String> values) {
            addCriterion("financing_conditions in", values, "financingConditions");
            return (Criteria) this;
        }

        public Criteria andFinancingConditionsNotIn(List<String> values) {
            addCriterion("financing_conditions not in", values, "financingConditions");
            return (Criteria) this;
        }

        public Criteria andFinancingConditionsBetween(String value1, String value2) {
            addCriterion("financing_conditions between", value1, value2, "financingConditions");
            return (Criteria) this;
        }

        public Criteria andFinancingConditionsNotBetween(String value1, String value2) {
            addCriterion("financing_conditions not between", value1, value2, "financingConditions");
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

        public Criteria andTotalSaleIsNull() {
            addCriterion("total_sale is null");
            return (Criteria) this;
        }

        public Criteria andTotalSaleIsNotNull() {
            addCriterion("total_sale is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSaleEqualTo(String value) {
            addCriterion("total_sale =", value, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNotEqualTo(String value) {
            addCriterion("total_sale <>", value, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleGreaterThan(String value) {
            addCriterion("total_sale >", value, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleGreaterThanOrEqualTo(String value) {
            addCriterion("total_sale >=", value, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleLessThan(String value) {
            addCriterion("total_sale <", value, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleLessThanOrEqualTo(String value) {
            addCriterion("total_sale <=", value, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleLike(String value) {
            addCriterion("total_sale like", value, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNotLike(String value) {
            addCriterion("total_sale not like", value, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleIn(List<String> values) {
            addCriterion("total_sale in", values, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNotIn(List<String> values) {
            addCriterion("total_sale not in", values, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleBetween(String value1, String value2) {
            addCriterion("total_sale between", value1, value2, "totalSale");
            return (Criteria) this;
        }

        public Criteria andTotalSaleNotBetween(String value1, String value2) {
            addCriterion("total_sale not between", value1, value2, "totalSale");
            return (Criteria) this;
        }

        public Criteria andInformationIsNull() {
            addCriterion("information is null");
            return (Criteria) this;
        }

        public Criteria andInformationIsNotNull() {
            addCriterion("information is not null");
            return (Criteria) this;
        }

        public Criteria andInformationEqualTo(String value) {
            addCriterion("information =", value, "information");
            return (Criteria) this;
        }

        public Criteria andInformationNotEqualTo(String value) {
            addCriterion("information <>", value, "information");
            return (Criteria) this;
        }

        public Criteria andInformationGreaterThan(String value) {
            addCriterion("information >", value, "information");
            return (Criteria) this;
        }

        public Criteria andInformationGreaterThanOrEqualTo(String value) {
            addCriterion("information >=", value, "information");
            return (Criteria) this;
        }

        public Criteria andInformationLessThan(String value) {
            addCriterion("information <", value, "information");
            return (Criteria) this;
        }

        public Criteria andInformationLessThanOrEqualTo(String value) {
            addCriterion("information <=", value, "information");
            return (Criteria) this;
        }

        public Criteria andInformationLike(String value) {
            addCriterion("information like", value, "information");
            return (Criteria) this;
        }

        public Criteria andInformationNotLike(String value) {
            addCriterion("information not like", value, "information");
            return (Criteria) this;
        }

        public Criteria andInformationIn(List<String> values) {
            addCriterion("information in", values, "information");
            return (Criteria) this;
        }

        public Criteria andInformationNotIn(List<String> values) {
            addCriterion("information not in", values, "information");
            return (Criteria) this;
        }

        public Criteria andInformationBetween(String value1, String value2) {
            addCriterion("information between", value1, value2, "information");
            return (Criteria) this;
        }

        public Criteria andInformationNotBetween(String value1, String value2) {
            addCriterion("information not between", value1, value2, "information");
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