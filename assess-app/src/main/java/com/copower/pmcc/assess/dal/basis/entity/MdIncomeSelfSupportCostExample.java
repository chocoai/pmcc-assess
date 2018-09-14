package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdIncomeSelfSupportCostExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MdIncomeSelfSupportCostExample() {
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

        public Criteria andSupportIdIsNull() {
            addCriterion("support_id is null");
            return (Criteria) this;
        }

        public Criteria andSupportIdIsNotNull() {
            addCriterion("support_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupportIdEqualTo(Integer value) {
            addCriterion("support_id =", value, "supportId");
            return (Criteria) this;
        }

        public Criteria andSupportIdNotEqualTo(Integer value) {
            addCriterion("support_id <>", value, "supportId");
            return (Criteria) this;
        }

        public Criteria andSupportIdGreaterThan(Integer value) {
            addCriterion("support_id >", value, "supportId");
            return (Criteria) this;
        }

        public Criteria andSupportIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("support_id >=", value, "supportId");
            return (Criteria) this;
        }

        public Criteria andSupportIdLessThan(Integer value) {
            addCriterion("support_id <", value, "supportId");
            return (Criteria) this;
        }

        public Criteria andSupportIdLessThanOrEqualTo(Integer value) {
            addCriterion("support_id <=", value, "supportId");
            return (Criteria) this;
        }

        public Criteria andSupportIdIn(List<Integer> values) {
            addCriterion("support_id in", values, "supportId");
            return (Criteria) this;
        }

        public Criteria andSupportIdNotIn(List<Integer> values) {
            addCriterion("support_id not in", values, "supportId");
            return (Criteria) this;
        }

        public Criteria andSupportIdBetween(Integer value1, Integer value2) {
            addCriterion("support_id between", value1, value2, "supportId");
            return (Criteria) this;
        }

        public Criteria andSupportIdNotBetween(Integer value1, Integer value2) {
            addCriterion("support_id not between", value1, value2, "supportId");
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

        public Criteria andAccountingSubjectIsNull() {
            addCriterion("accounting_subject is null");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectIsNotNull() {
            addCriterion("accounting_subject is not null");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectEqualTo(Integer value) {
            addCriterion("accounting_subject =", value, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectNotEqualTo(Integer value) {
            addCriterion("accounting_subject <>", value, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectGreaterThan(Integer value) {
            addCriterion("accounting_subject >", value, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectGreaterThanOrEqualTo(Integer value) {
            addCriterion("accounting_subject >=", value, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectLessThan(Integer value) {
            addCriterion("accounting_subject <", value, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectLessThanOrEqualTo(Integer value) {
            addCriterion("accounting_subject <=", value, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectIn(List<Integer> values) {
            addCriterion("accounting_subject in", values, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectNotIn(List<Integer> values) {
            addCriterion("accounting_subject not in", values, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectBetween(Integer value1, Integer value2) {
            addCriterion("accounting_subject between", value1, value2, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andAccountingSubjectNotBetween(Integer value1, Integer value2) {
            addCriterion("accounting_subject not between", value1, value2, "accountingSubject");
            return (Criteria) this;
        }

        public Criteria andCostTypeIsNull() {
            addCriterion("cost_type is null");
            return (Criteria) this;
        }

        public Criteria andCostTypeIsNotNull() {
            addCriterion("cost_type is not null");
            return (Criteria) this;
        }

        public Criteria andCostTypeEqualTo(Integer value) {
            addCriterion("cost_type =", value, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeNotEqualTo(Integer value) {
            addCriterion("cost_type <>", value, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeGreaterThan(Integer value) {
            addCriterion("cost_type >", value, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("cost_type >=", value, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeLessThan(Integer value) {
            addCriterion("cost_type <", value, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeLessThanOrEqualTo(Integer value) {
            addCriterion("cost_type <=", value, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeIn(List<Integer> values) {
            addCriterion("cost_type in", values, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeNotIn(List<Integer> values) {
            addCriterion("cost_type not in", values, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeBetween(Integer value1, Integer value2) {
            addCriterion("cost_type between", value1, value2, "costType");
            return (Criteria) this;
        }

        public Criteria andCostTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("cost_type not between", value1, value2, "costType");
            return (Criteria) this;
        }

        public Criteria andCostCategoryIsNull() {
            addCriterion("cost_category is null");
            return (Criteria) this;
        }

        public Criteria andCostCategoryIsNotNull() {
            addCriterion("cost_category is not null");
            return (Criteria) this;
        }

        public Criteria andCostCategoryEqualTo(Integer value) {
            addCriterion("cost_category =", value, "costCategory");
            return (Criteria) this;
        }

        public Criteria andCostCategoryNotEqualTo(Integer value) {
            addCriterion("cost_category <>", value, "costCategory");
            return (Criteria) this;
        }

        public Criteria andCostCategoryGreaterThan(Integer value) {
            addCriterion("cost_category >", value, "costCategory");
            return (Criteria) this;
        }

        public Criteria andCostCategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("cost_category >=", value, "costCategory");
            return (Criteria) this;
        }

        public Criteria andCostCategoryLessThan(Integer value) {
            addCriterion("cost_category <", value, "costCategory");
            return (Criteria) this;
        }

        public Criteria andCostCategoryLessThanOrEqualTo(Integer value) {
            addCriterion("cost_category <=", value, "costCategory");
            return (Criteria) this;
        }

        public Criteria andCostCategoryIn(List<Integer> values) {
            addCriterion("cost_category in", values, "costCategory");
            return (Criteria) this;
        }

        public Criteria andCostCategoryNotIn(List<Integer> values) {
            addCriterion("cost_category not in", values, "costCategory");
            return (Criteria) this;
        }

        public Criteria andCostCategoryBetween(Integer value1, Integer value2) {
            addCriterion("cost_category between", value1, value2, "costCategory");
            return (Criteria) this;
        }

        public Criteria andCostCategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("cost_category not between", value1, value2, "costCategory");
            return (Criteria) this;
        }

        public Criteria andMonthlyIsNull() {
            addCriterion("monthly is null");
            return (Criteria) this;
        }

        public Criteria andMonthlyIsNotNull() {
            addCriterion("monthly is not null");
            return (Criteria) this;
        }

        public Criteria andMonthlyEqualTo(String value) {
            addCriterion("monthly =", value, "monthly");
            return (Criteria) this;
        }

        public Criteria andMonthlyNotEqualTo(String value) {
            addCriterion("monthly <>", value, "monthly");
            return (Criteria) this;
        }

        public Criteria andMonthlyGreaterThan(String value) {
            addCriterion("monthly >", value, "monthly");
            return (Criteria) this;
        }

        public Criteria andMonthlyGreaterThanOrEqualTo(String value) {
            addCriterion("monthly >=", value, "monthly");
            return (Criteria) this;
        }

        public Criteria andMonthlyLessThan(String value) {
            addCriterion("monthly <", value, "monthly");
            return (Criteria) this;
        }

        public Criteria andMonthlyLessThanOrEqualTo(String value) {
            addCriterion("monthly <=", value, "monthly");
            return (Criteria) this;
        }

        public Criteria andMonthlyLike(String value) {
            addCriterion("monthly like", value, "monthly");
            return (Criteria) this;
        }

        public Criteria andMonthlyNotLike(String value) {
            addCriterion("monthly not like", value, "monthly");
            return (Criteria) this;
        }

        public Criteria andMonthlyIn(List<String> values) {
            addCriterion("monthly in", values, "monthly");
            return (Criteria) this;
        }

        public Criteria andMonthlyNotIn(List<String> values) {
            addCriterion("monthly not in", values, "monthly");
            return (Criteria) this;
        }

        public Criteria andMonthlyBetween(String value1, String value2) {
            addCriterion("monthly between", value1, value2, "monthly");
            return (Criteria) this;
        }

        public Criteria andMonthlyNotBetween(String value1, String value2) {
            addCriterion("monthly not between", value1, value2, "monthly");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNull() {
            addCriterion("unit_price is null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNotNull() {
            addCriterion("unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceEqualTo(BigDecimal value) {
            addCriterion("unit_price =", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotEqualTo(BigDecimal value) {
            addCriterion("unit_price <>", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThan(BigDecimal value) {
            addCriterion("unit_price >", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unit_price >=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThan(BigDecimal value) {
            addCriterion("unit_price <", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unit_price <=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIn(List<BigDecimal> values) {
            addCriterion("unit_price in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotIn(List<BigDecimal> values) {
            addCriterion("unit_price not in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unit_price between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unit_price not between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Integer value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Integer value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Integer value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Integer value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Integer value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Integer> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Integer> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Integer value1, Integer value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyIsNull() {
            addCriterion("amount_money is null");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyIsNotNull() {
            addCriterion("amount_money is not null");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyEqualTo(BigDecimal value) {
            addCriterion("amount_money =", value, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyNotEqualTo(BigDecimal value) {
            addCriterion("amount_money <>", value, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyGreaterThan(BigDecimal value) {
            addCriterion("amount_money >", value, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount_money >=", value, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyLessThan(BigDecimal value) {
            addCriterion("amount_money <", value, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount_money <=", value, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyIn(List<BigDecimal> values) {
            addCriterion("amount_money in", values, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyNotIn(List<BigDecimal> values) {
            addCriterion("amount_money not in", values, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount_money between", value1, value2, "amountMoney");
            return (Criteria) this;
        }

        public Criteria andAmountMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount_money not between", value1, value2, "amountMoney");
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