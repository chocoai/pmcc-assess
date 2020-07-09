package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicHouseTradingLeaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicHouseTradingLeaseExample() {
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

        public Criteria andRentPaymentTimeStartIsNull() {
            addCriterion("rent_payment_time_start is null");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeStartIsNotNull() {
            addCriterion("rent_payment_time_start is not null");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeStartEqualTo(Date value) {
            addCriterion("rent_payment_time_start =", value, "rentPaymentTimeStart");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeStartNotEqualTo(Date value) {
            addCriterion("rent_payment_time_start <>", value, "rentPaymentTimeStart");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeStartGreaterThan(Date value) {
            addCriterion("rent_payment_time_start >", value, "rentPaymentTimeStart");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeStartGreaterThanOrEqualTo(Date value) {
            addCriterion("rent_payment_time_start >=", value, "rentPaymentTimeStart");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeStartLessThan(Date value) {
            addCriterion("rent_payment_time_start <", value, "rentPaymentTimeStart");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeStartLessThanOrEqualTo(Date value) {
            addCriterion("rent_payment_time_start <=", value, "rentPaymentTimeStart");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeStartIn(List<Date> values) {
            addCriterion("rent_payment_time_start in", values, "rentPaymentTimeStart");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeStartNotIn(List<Date> values) {
            addCriterion("rent_payment_time_start not in", values, "rentPaymentTimeStart");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeStartBetween(Date value1, Date value2) {
            addCriterion("rent_payment_time_start between", value1, value2, "rentPaymentTimeStart");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeStartNotBetween(Date value1, Date value2) {
            addCriterion("rent_payment_time_start not between", value1, value2, "rentPaymentTimeStart");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeEndIsNull() {
            addCriterion("rent_payment_time_end is null");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeEndIsNotNull() {
            addCriterion("rent_payment_time_end is not null");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeEndEqualTo(Date value) {
            addCriterion("rent_payment_time_end =", value, "rentPaymentTimeEnd");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeEndNotEqualTo(Date value) {
            addCriterion("rent_payment_time_end <>", value, "rentPaymentTimeEnd");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeEndGreaterThan(Date value) {
            addCriterion("rent_payment_time_end >", value, "rentPaymentTimeEnd");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeEndGreaterThanOrEqualTo(Date value) {
            addCriterion("rent_payment_time_end >=", value, "rentPaymentTimeEnd");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeEndLessThan(Date value) {
            addCriterion("rent_payment_time_end <", value, "rentPaymentTimeEnd");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeEndLessThanOrEqualTo(Date value) {
            addCriterion("rent_payment_time_end <=", value, "rentPaymentTimeEnd");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeEndIn(List<Date> values) {
            addCriterion("rent_payment_time_end in", values, "rentPaymentTimeEnd");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeEndNotIn(List<Date> values) {
            addCriterion("rent_payment_time_end not in", values, "rentPaymentTimeEnd");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeEndBetween(Date value1, Date value2) {
            addCriterion("rent_payment_time_end between", value1, value2, "rentPaymentTimeEnd");
            return (Criteria) this;
        }

        public Criteria andRentPaymentTimeEndNotBetween(Date value1, Date value2) {
            addCriterion("rent_payment_time_end not between", value1, value2, "rentPaymentTimeEnd");
            return (Criteria) this;
        }

        public Criteria andRentGrowthRateIsNull() {
            addCriterion("rent_growth_rate is null");
            return (Criteria) this;
        }

        public Criteria andRentGrowthRateIsNotNull() {
            addCriterion("rent_growth_rate is not null");
            return (Criteria) this;
        }

        public Criteria andRentGrowthRateEqualTo(String value) {
            addCriterion("rent_growth_rate =", value, "rentGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentGrowthRateNotEqualTo(String value) {
            addCriterion("rent_growth_rate <>", value, "rentGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentGrowthRateGreaterThan(String value) {
            addCriterion("rent_growth_rate >", value, "rentGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentGrowthRateGreaterThanOrEqualTo(String value) {
            addCriterion("rent_growth_rate >=", value, "rentGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentGrowthRateLessThan(String value) {
            addCriterion("rent_growth_rate <", value, "rentGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentGrowthRateLessThanOrEqualTo(String value) {
            addCriterion("rent_growth_rate <=", value, "rentGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentGrowthRateLike(String value) {
            addCriterion("rent_growth_rate like", value, "rentGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentGrowthRateNotLike(String value) {
            addCriterion("rent_growth_rate not like", value, "rentGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentGrowthRateIn(List<String> values) {
            addCriterion("rent_growth_rate in", values, "rentGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentGrowthRateNotIn(List<String> values) {
            addCriterion("rent_growth_rate not in", values, "rentGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentGrowthRateBetween(String value1, String value2) {
            addCriterion("rent_growth_rate between", value1, value2, "rentGrowthRate");
            return (Criteria) this;
        }

        public Criteria andRentGrowthRateNotBetween(String value1, String value2) {
            addCriterion("rent_growth_rate not between", value1, value2, "rentGrowthRate");
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

        public Criteria andTradingIdIsNull() {
            addCriterion("trading_id is null");
            return (Criteria) this;
        }

        public Criteria andTradingIdIsNotNull() {
            addCriterion("trading_id is not null");
            return (Criteria) this;
        }

        public Criteria andTradingIdEqualTo(Integer value) {
            addCriterion("trading_id =", value, "tradingId");
            return (Criteria) this;
        }

        public Criteria andTradingIdNotEqualTo(Integer value) {
            addCriterion("trading_id <>", value, "tradingId");
            return (Criteria) this;
        }

        public Criteria andTradingIdGreaterThan(Integer value) {
            addCriterion("trading_id >", value, "tradingId");
            return (Criteria) this;
        }

        public Criteria andTradingIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("trading_id >=", value, "tradingId");
            return (Criteria) this;
        }

        public Criteria andTradingIdLessThan(Integer value) {
            addCriterion("trading_id <", value, "tradingId");
            return (Criteria) this;
        }

        public Criteria andTradingIdLessThanOrEqualTo(Integer value) {
            addCriterion("trading_id <=", value, "tradingId");
            return (Criteria) this;
        }

        public Criteria andTradingIdIn(List<Integer> values) {
            addCriterion("trading_id in", values, "tradingId");
            return (Criteria) this;
        }

        public Criteria andTradingIdNotIn(List<Integer> values) {
            addCriterion("trading_id not in", values, "tradingId");
            return (Criteria) this;
        }

        public Criteria andTradingIdBetween(Integer value1, Integer value2) {
            addCriterion("trading_id between", value1, value2, "tradingId");
            return (Criteria) this;
        }

        public Criteria andTradingIdNotBetween(Integer value1, Integer value2) {
            addCriterion("trading_id not between", value1, value2, "tradingId");
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