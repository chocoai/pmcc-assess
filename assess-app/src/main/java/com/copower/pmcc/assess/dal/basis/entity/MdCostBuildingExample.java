package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdCostBuildingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MdCostBuildingExample() {
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

        public Criteria andNewRateIsNull() {
            addCriterion("new_rate is null");
            return (Criteria) this;
        }

        public Criteria andNewRateIsNotNull() {
            addCriterion("new_rate is not null");
            return (Criteria) this;
        }

        public Criteria andNewRateEqualTo(String value) {
            addCriterion("new_rate =", value, "newRate");
            return (Criteria) this;
        }

        public Criteria andNewRateNotEqualTo(String value) {
            addCriterion("new_rate <>", value, "newRate");
            return (Criteria) this;
        }

        public Criteria andNewRateGreaterThan(String value) {
            addCriterion("new_rate >", value, "newRate");
            return (Criteria) this;
        }

        public Criteria andNewRateGreaterThanOrEqualTo(String value) {
            addCriterion("new_rate >=", value, "newRate");
            return (Criteria) this;
        }

        public Criteria andNewRateLessThan(String value) {
            addCriterion("new_rate <", value, "newRate");
            return (Criteria) this;
        }

        public Criteria andNewRateLessThanOrEqualTo(String value) {
            addCriterion("new_rate <=", value, "newRate");
            return (Criteria) this;
        }

        public Criteria andNewRateLike(String value) {
            addCriterion("new_rate like", value, "newRate");
            return (Criteria) this;
        }

        public Criteria andNewRateNotLike(String value) {
            addCriterion("new_rate not like", value, "newRate");
            return (Criteria) this;
        }

        public Criteria andNewRateIn(List<String> values) {
            addCriterion("new_rate in", values, "newRate");
            return (Criteria) this;
        }

        public Criteria andNewRateNotIn(List<String> values) {
            addCriterion("new_rate not in", values, "newRate");
            return (Criteria) this;
        }

        public Criteria andNewRateBetween(String value1, String value2) {
            addCriterion("new_rate between", value1, value2, "newRate");
            return (Criteria) this;
        }

        public Criteria andNewRateNotBetween(String value1, String value2) {
            addCriterion("new_rate not between", value1, value2, "newRate");
            return (Criteria) this;
        }

        public Criteria andAssessPriceIsNull() {
            addCriterion("assess_price is null");
            return (Criteria) this;
        }

        public Criteria andAssessPriceIsNotNull() {
            addCriterion("assess_price is not null");
            return (Criteria) this;
        }

        public Criteria andAssessPriceEqualTo(BigDecimal value) {
            addCriterion("assess_price =", value, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessPriceNotEqualTo(BigDecimal value) {
            addCriterion("assess_price <>", value, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessPriceGreaterThan(BigDecimal value) {
            addCriterion("assess_price >", value, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_price >=", value, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessPriceLessThan(BigDecimal value) {
            addCriterion("assess_price <", value, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_price <=", value, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessPriceIn(List<BigDecimal> values) {
            addCriterion("assess_price in", values, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessPriceNotIn(List<BigDecimal> values) {
            addCriterion("assess_price not in", values, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_price between", value1, value2, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_price not between", value1, value2, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeIsNull() {
            addCriterion("construction_installation_engineering_fee is null");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeIsNotNull() {
            addCriterion("construction_installation_engineering_fee is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeEqualTo(BigDecimal value) {
            addCriterion("construction_installation_engineering_fee =", value, "constructionInstallationEngineeringFee");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeNotEqualTo(BigDecimal value) {
            addCriterion("construction_installation_engineering_fee <>", value, "constructionInstallationEngineeringFee");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeGreaterThan(BigDecimal value) {
            addCriterion("construction_installation_engineering_fee >", value, "constructionInstallationEngineeringFee");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("construction_installation_engineering_fee >=", value, "constructionInstallationEngineeringFee");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeLessThan(BigDecimal value) {
            addCriterion("construction_installation_engineering_fee <", value, "constructionInstallationEngineeringFee");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("construction_installation_engineering_fee <=", value, "constructionInstallationEngineeringFee");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeIn(List<BigDecimal> values) {
            addCriterion("construction_installation_engineering_fee in", values, "constructionInstallationEngineeringFee");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeNotIn(List<BigDecimal> values) {
            addCriterion("construction_installation_engineering_fee not in", values, "constructionInstallationEngineeringFee");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("construction_installation_engineering_fee between", value1, value2, "constructionInstallationEngineeringFee");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("construction_installation_engineering_fee not between", value1, value2, "constructionInstallationEngineeringFee");
            return (Criteria) this;
        }

        public Criteria andJsonContentIsNull() {
            addCriterion("json_content is null");
            return (Criteria) this;
        }

        public Criteria andJsonContentIsNotNull() {
            addCriterion("json_content is not null");
            return (Criteria) this;
        }

        public Criteria andJsonContentEqualTo(String value) {
            addCriterion("json_content =", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentNotEqualTo(String value) {
            addCriterion("json_content <>", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentGreaterThan(String value) {
            addCriterion("json_content >", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentGreaterThanOrEqualTo(String value) {
            addCriterion("json_content >=", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentLessThan(String value) {
            addCriterion("json_content <", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentLessThanOrEqualTo(String value) {
            addCriterion("json_content <=", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentLike(String value) {
            addCriterion("json_content like", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentNotLike(String value) {
            addCriterion("json_content not like", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentIn(List<String> values) {
            addCriterion("json_content in", values, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentNotIn(List<String> values) {
            addCriterion("json_content not in", values, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentBetween(String value1, String value2) {
            addCriterion("json_content between", value1, value2, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentNotBetween(String value1, String value2) {
            addCriterion("json_content not between", value1, value2, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andCostIdIsNull() {
            addCriterion("cost_id is null");
            return (Criteria) this;
        }

        public Criteria andCostIdIsNotNull() {
            addCriterion("cost_id is not null");
            return (Criteria) this;
        }

        public Criteria andCostIdEqualTo(Integer value) {
            addCriterion("cost_id =", value, "costId");
            return (Criteria) this;
        }

        public Criteria andCostIdNotEqualTo(Integer value) {
            addCriterion("cost_id <>", value, "costId");
            return (Criteria) this;
        }

        public Criteria andCostIdGreaterThan(Integer value) {
            addCriterion("cost_id >", value, "costId");
            return (Criteria) this;
        }

        public Criteria andCostIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cost_id >=", value, "costId");
            return (Criteria) this;
        }

        public Criteria andCostIdLessThan(Integer value) {
            addCriterion("cost_id <", value, "costId");
            return (Criteria) this;
        }

        public Criteria andCostIdLessThanOrEqualTo(Integer value) {
            addCriterion("cost_id <=", value, "costId");
            return (Criteria) this;
        }

        public Criteria andCostIdIn(List<Integer> values) {
            addCriterion("cost_id in", values, "costId");
            return (Criteria) this;
        }

        public Criteria andCostIdNotIn(List<Integer> values) {
            addCriterion("cost_id not in", values, "costId");
            return (Criteria) this;
        }

        public Criteria andCostIdBetween(Integer value1, Integer value2) {
            addCriterion("cost_id between", value1, value2, "costId");
            return (Criteria) this;
        }

        public Criteria andCostIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cost_id not between", value1, value2, "costId");
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