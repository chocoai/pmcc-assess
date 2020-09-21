package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataHousePriceIndexDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataHousePriceIndexDetailExample() {
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

        public Criteria andHousePriceIdIsNull() {
            addCriterion("house_price_id is null");
            return (Criteria) this;
        }

        public Criteria andHousePriceIdIsNotNull() {
            addCriterion("house_price_id is not null");
            return (Criteria) this;
        }

        public Criteria andHousePriceIdEqualTo(Integer value) {
            addCriterion("house_price_id =", value, "housePriceId");
            return (Criteria) this;
        }

        public Criteria andHousePriceIdNotEqualTo(Integer value) {
            addCriterion("house_price_id <>", value, "housePriceId");
            return (Criteria) this;
        }

        public Criteria andHousePriceIdGreaterThan(Integer value) {
            addCriterion("house_price_id >", value, "housePriceId");
            return (Criteria) this;
        }

        public Criteria andHousePriceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("house_price_id >=", value, "housePriceId");
            return (Criteria) this;
        }

        public Criteria andHousePriceIdLessThan(Integer value) {
            addCriterion("house_price_id <", value, "housePriceId");
            return (Criteria) this;
        }

        public Criteria andHousePriceIdLessThanOrEqualTo(Integer value) {
            addCriterion("house_price_id <=", value, "housePriceId");
            return (Criteria) this;
        }

        public Criteria andHousePriceIdIn(List<Integer> values) {
            addCriterion("house_price_id in", values, "housePriceId");
            return (Criteria) this;
        }

        public Criteria andHousePriceIdNotIn(List<Integer> values) {
            addCriterion("house_price_id not in", values, "housePriceId");
            return (Criteria) this;
        }

        public Criteria andHousePriceIdBetween(Integer value1, Integer value2) {
            addCriterion("house_price_id between", value1, value2, "housePriceId");
            return (Criteria) this;
        }

        public Criteria andHousePriceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("house_price_id not between", value1, value2, "housePriceId");
            return (Criteria) this;
        }

        public Criteria andIndexNumberIsNull() {
            addCriterion("index_number is null");
            return (Criteria) this;
        }

        public Criteria andIndexNumberIsNotNull() {
            addCriterion("index_number is not null");
            return (Criteria) this;
        }

        public Criteria andIndexNumberEqualTo(BigDecimal value) {
            addCriterion("index_number =", value, "indexNumber");
            return (Criteria) this;
        }

        public Criteria andIndexNumberNotEqualTo(BigDecimal value) {
            addCriterion("index_number <>", value, "indexNumber");
            return (Criteria) this;
        }

        public Criteria andIndexNumberGreaterThan(BigDecimal value) {
            addCriterion("index_number >", value, "indexNumber");
            return (Criteria) this;
        }

        public Criteria andIndexNumberGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("index_number >=", value, "indexNumber");
            return (Criteria) this;
        }

        public Criteria andIndexNumberLessThan(BigDecimal value) {
            addCriterion("index_number <", value, "indexNumber");
            return (Criteria) this;
        }

        public Criteria andIndexNumberLessThanOrEqualTo(BigDecimal value) {
            addCriterion("index_number <=", value, "indexNumber");
            return (Criteria) this;
        }

        public Criteria andIndexNumberIn(List<BigDecimal> values) {
            addCriterion("index_number in", values, "indexNumber");
            return (Criteria) this;
        }

        public Criteria andIndexNumberNotIn(List<BigDecimal> values) {
            addCriterion("index_number not in", values, "indexNumber");
            return (Criteria) this;
        }

        public Criteria andIndexNumberBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("index_number between", value1, value2, "indexNumber");
            return (Criteria) this;
        }

        public Criteria andIndexNumberNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("index_number not between", value1, value2, "indexNumber");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterion("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterion("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterion("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterion("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterion("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterion("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterion("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterion("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterion("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andUnitPremiumIsNull() {
            addCriterion("unit_premium is null");
            return (Criteria) this;
        }

        public Criteria andUnitPremiumIsNotNull() {
            addCriterion("unit_premium is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPremiumEqualTo(BigDecimal value) {
            addCriterion("unit_premium =", value, "unitPremium");
            return (Criteria) this;
        }

        public Criteria andUnitPremiumNotEqualTo(BigDecimal value) {
            addCriterion("unit_premium <>", value, "unitPremium");
            return (Criteria) this;
        }

        public Criteria andUnitPremiumGreaterThan(BigDecimal value) {
            addCriterion("unit_premium >", value, "unitPremium");
            return (Criteria) this;
        }

        public Criteria andUnitPremiumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unit_premium >=", value, "unitPremium");
            return (Criteria) this;
        }

        public Criteria andUnitPremiumLessThan(BigDecimal value) {
            addCriterion("unit_premium <", value, "unitPremium");
            return (Criteria) this;
        }

        public Criteria andUnitPremiumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unit_premium <=", value, "unitPremium");
            return (Criteria) this;
        }

        public Criteria andUnitPremiumIn(List<BigDecimal> values) {
            addCriterion("unit_premium in", values, "unitPremium");
            return (Criteria) this;
        }

        public Criteria andUnitPremiumNotIn(List<BigDecimal> values) {
            addCriterion("unit_premium not in", values, "unitPremium");
            return (Criteria) this;
        }

        public Criteria andUnitPremiumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unit_premium between", value1, value2, "unitPremium");
            return (Criteria) this;
        }

        public Criteria andUnitPremiumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unit_premium not between", value1, value2, "unitPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumIsNull() {
            addCriterion("floor_premium is null");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumIsNotNull() {
            addCriterion("floor_premium is not null");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumEqualTo(BigDecimal value) {
            addCriterion("floor_premium =", value, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumNotEqualTo(BigDecimal value) {
            addCriterion("floor_premium <>", value, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumGreaterThan(BigDecimal value) {
            addCriterion("floor_premium >", value, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_premium >=", value, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumLessThan(BigDecimal value) {
            addCriterion("floor_premium <", value, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_premium <=", value, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumIn(List<BigDecimal> values) {
            addCriterion("floor_premium in", values, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumNotIn(List<BigDecimal> values) {
            addCriterion("floor_premium not in", values, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_premium between", value1, value2, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_premium not between", value1, value2, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andBisBaseIsNull() {
            addCriterion("bis_base is null");
            return (Criteria) this;
        }

        public Criteria andBisBaseIsNotNull() {
            addCriterion("bis_base is not null");
            return (Criteria) this;
        }

        public Criteria andBisBaseEqualTo(Boolean value) {
            addCriterion("bis_base =", value, "bisBase");
            return (Criteria) this;
        }

        public Criteria andBisBaseNotEqualTo(Boolean value) {
            addCriterion("bis_base <>", value, "bisBase");
            return (Criteria) this;
        }

        public Criteria andBisBaseGreaterThan(Boolean value) {
            addCriterion("bis_base >", value, "bisBase");
            return (Criteria) this;
        }

        public Criteria andBisBaseGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_base >=", value, "bisBase");
            return (Criteria) this;
        }

        public Criteria andBisBaseLessThan(Boolean value) {
            addCriterion("bis_base <", value, "bisBase");
            return (Criteria) this;
        }

        public Criteria andBisBaseLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_base <=", value, "bisBase");
            return (Criteria) this;
        }

        public Criteria andBisBaseIn(List<Boolean> values) {
            addCriterion("bis_base in", values, "bisBase");
            return (Criteria) this;
        }

        public Criteria andBisBaseNotIn(List<Boolean> values) {
            addCriterion("bis_base not in", values, "bisBase");
            return (Criteria) this;
        }

        public Criteria andBisBaseBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_base between", value1, value2, "bisBase");
            return (Criteria) this;
        }

        public Criteria andBisBaseNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_base not between", value1, value2, "bisBase");
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