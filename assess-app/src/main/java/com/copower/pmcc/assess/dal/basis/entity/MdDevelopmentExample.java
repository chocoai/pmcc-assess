package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdDevelopmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MdDevelopmentExample() {
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

        public Criteria andPlanDetailsIdIsNull() {
            addCriterion("plan_details_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdIsNotNull() {
            addCriterion("plan_details_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdEqualTo(Integer value) {
            addCriterion("plan_details_id =", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdNotEqualTo(Integer value) {
            addCriterion("plan_details_id <>", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdGreaterThan(Integer value) {
            addCriterion("plan_details_id >", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_details_id >=", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdLessThan(Integer value) {
            addCriterion("plan_details_id <", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdLessThanOrEqualTo(Integer value) {
            addCriterion("plan_details_id <=", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdIn(List<Integer> values) {
            addCriterion("plan_details_id in", values, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdNotIn(List<Integer> values) {
            addCriterion("plan_details_id not in", values, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdBetween(Integer value1, Integer value2) {
            addCriterion("plan_details_id between", value1, value2, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_details_id not between", value1, value2, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andConstructionCostSubtotalIsNull() {
            addCriterion("construction_cost_subtotal is null");
            return (Criteria) this;
        }

        public Criteria andConstructionCostSubtotalIsNotNull() {
            addCriterion("construction_cost_subtotal is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionCostSubtotalEqualTo(BigDecimal value) {
            addCriterion("construction_cost_subtotal =", value, "constructionCostSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionCostSubtotalNotEqualTo(BigDecimal value) {
            addCriterion("construction_cost_subtotal <>", value, "constructionCostSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionCostSubtotalGreaterThan(BigDecimal value) {
            addCriterion("construction_cost_subtotal >", value, "constructionCostSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionCostSubtotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("construction_cost_subtotal >=", value, "constructionCostSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionCostSubtotalLessThan(BigDecimal value) {
            addCriterion("construction_cost_subtotal <", value, "constructionCostSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionCostSubtotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("construction_cost_subtotal <=", value, "constructionCostSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionCostSubtotalIn(List<BigDecimal> values) {
            addCriterion("construction_cost_subtotal in", values, "constructionCostSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionCostSubtotalNotIn(List<BigDecimal> values) {
            addCriterion("construction_cost_subtotal not in", values, "constructionCostSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionCostSubtotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("construction_cost_subtotal between", value1, value2, "constructionCostSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionCostSubtotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("construction_cost_subtotal not between", value1, value2, "constructionCostSubtotal");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentIsNull() {
            addCriterion("interest_investment is null");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentIsNotNull() {
            addCriterion("interest_investment is not null");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentEqualTo(BigDecimal value) {
            addCriterion("interest_investment =", value, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentNotEqualTo(BigDecimal value) {
            addCriterion("interest_investment <>", value, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentGreaterThan(BigDecimal value) {
            addCriterion("interest_investment >", value, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("interest_investment >=", value, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentLessThan(BigDecimal value) {
            addCriterion("interest_investment <", value, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("interest_investment <=", value, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentIn(List<BigDecimal> values) {
            addCriterion("interest_investment in", values, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentNotIn(List<BigDecimal> values) {
            addCriterion("interest_investment not in", values, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("interest_investment between", value1, value2, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("interest_investment not between", value1, value2, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitIsNull() {
            addCriterion("investment_profit is null");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitIsNotNull() {
            addCriterion("investment_profit is not null");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitEqualTo(BigDecimal value) {
            addCriterion("investment_profit =", value, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitNotEqualTo(BigDecimal value) {
            addCriterion("investment_profit <>", value, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitGreaterThan(BigDecimal value) {
            addCriterion("investment_profit >", value, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("investment_profit >=", value, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitLessThan(BigDecimal value) {
            addCriterion("investment_profit <", value, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("investment_profit <=", value, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitIn(List<BigDecimal> values) {
            addCriterion("investment_profit in", values, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitNotIn(List<BigDecimal> values) {
            addCriterion("investment_profit not in", values, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("investment_profit between", value1, value2, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("investment_profit not between", value1, value2, "investmentProfit");
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

        public Criteria andHeadContentIsNull() {
            addCriterion("head_content is null");
            return (Criteria) this;
        }

        public Criteria andHeadContentIsNotNull() {
            addCriterion("head_content is not null");
            return (Criteria) this;
        }

        public Criteria andHeadContentEqualTo(String value) {
            addCriterion("head_content =", value, "headContent");
            return (Criteria) this;
        }

        public Criteria andHeadContentNotEqualTo(String value) {
            addCriterion("head_content <>", value, "headContent");
            return (Criteria) this;
        }

        public Criteria andHeadContentGreaterThan(String value) {
            addCriterion("head_content >", value, "headContent");
            return (Criteria) this;
        }

        public Criteria andHeadContentGreaterThanOrEqualTo(String value) {
            addCriterion("head_content >=", value, "headContent");
            return (Criteria) this;
        }

        public Criteria andHeadContentLessThan(String value) {
            addCriterion("head_content <", value, "headContent");
            return (Criteria) this;
        }

        public Criteria andHeadContentLessThanOrEqualTo(String value) {
            addCriterion("head_content <=", value, "headContent");
            return (Criteria) this;
        }

        public Criteria andHeadContentLike(String value) {
            addCriterion("head_content like", value, "headContent");
            return (Criteria) this;
        }

        public Criteria andHeadContentNotLike(String value) {
            addCriterion("head_content not like", value, "headContent");
            return (Criteria) this;
        }

        public Criteria andHeadContentIn(List<String> values) {
            addCriterion("head_content in", values, "headContent");
            return (Criteria) this;
        }

        public Criteria andHeadContentNotIn(List<String> values) {
            addCriterion("head_content not in", values, "headContent");
            return (Criteria) this;
        }

        public Criteria andHeadContentBetween(String value1, String value2) {
            addCriterion("head_content between", value1, value2, "headContent");
            return (Criteria) this;
        }

        public Criteria andHeadContentNotBetween(String value1, String value2) {
            addCriterion("head_content not between", value1, value2, "headContent");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andProjectConstructionPeriodIsNull() {
            addCriterion("project_construction_period is null");
            return (Criteria) this;
        }

        public Criteria andProjectConstructionPeriodIsNotNull() {
            addCriterion("project_construction_period is not null");
            return (Criteria) this;
        }

        public Criteria andProjectConstructionPeriodEqualTo(BigDecimal value) {
            addCriterion("project_construction_period =", value, "projectConstructionPeriod");
            return (Criteria) this;
        }

        public Criteria andProjectConstructionPeriodNotEqualTo(BigDecimal value) {
            addCriterion("project_construction_period <>", value, "projectConstructionPeriod");
            return (Criteria) this;
        }

        public Criteria andProjectConstructionPeriodGreaterThan(BigDecimal value) {
            addCriterion("project_construction_period >", value, "projectConstructionPeriod");
            return (Criteria) this;
        }

        public Criteria andProjectConstructionPeriodGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("project_construction_period >=", value, "projectConstructionPeriod");
            return (Criteria) this;
        }

        public Criteria andProjectConstructionPeriodLessThan(BigDecimal value) {
            addCriterion("project_construction_period <", value, "projectConstructionPeriod");
            return (Criteria) this;
        }

        public Criteria andProjectConstructionPeriodLessThanOrEqualTo(BigDecimal value) {
            addCriterion("project_construction_period <=", value, "projectConstructionPeriod");
            return (Criteria) this;
        }

        public Criteria andProjectConstructionPeriodIn(List<BigDecimal> values) {
            addCriterion("project_construction_period in", values, "projectConstructionPeriod");
            return (Criteria) this;
        }

        public Criteria andProjectConstructionPeriodNotIn(List<BigDecimal> values) {
            addCriterion("project_construction_period not in", values, "projectConstructionPeriod");
            return (Criteria) this;
        }

        public Criteria andProjectConstructionPeriodBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("project_construction_period between", value1, value2, "projectConstructionPeriod");
            return (Criteria) this;
        }

        public Criteria andProjectConstructionPeriodNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("project_construction_period not between", value1, value2, "projectConstructionPeriod");
            return (Criteria) this;
        }

        public Criteria andDevelopedYearIsNull() {
            addCriterion("developed_year is null");
            return (Criteria) this;
        }

        public Criteria andDevelopedYearIsNotNull() {
            addCriterion("developed_year is not null");
            return (Criteria) this;
        }

        public Criteria andDevelopedYearEqualTo(BigDecimal value) {
            addCriterion("developed_year =", value, "developedYear");
            return (Criteria) this;
        }

        public Criteria andDevelopedYearNotEqualTo(BigDecimal value) {
            addCriterion("developed_year <>", value, "developedYear");
            return (Criteria) this;
        }

        public Criteria andDevelopedYearGreaterThan(BigDecimal value) {
            addCriterion("developed_year >", value, "developedYear");
            return (Criteria) this;
        }

        public Criteria andDevelopedYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("developed_year >=", value, "developedYear");
            return (Criteria) this;
        }

        public Criteria andDevelopedYearLessThan(BigDecimal value) {
            addCriterion("developed_year <", value, "developedYear");
            return (Criteria) this;
        }

        public Criteria andDevelopedYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("developed_year <=", value, "developedYear");
            return (Criteria) this;
        }

        public Criteria andDevelopedYearIn(List<BigDecimal> values) {
            addCriterion("developed_year in", values, "developedYear");
            return (Criteria) this;
        }

        public Criteria andDevelopedYearNotIn(List<BigDecimal> values) {
            addCriterion("developed_year not in", values, "developedYear");
            return (Criteria) this;
        }

        public Criteria andDevelopedYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("developed_year between", value1, value2, "developedYear");
            return (Criteria) this;
        }

        public Criteria andDevelopedYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("developed_year not between", value1, value2, "developedYear");
            return (Criteria) this;
        }

        public Criteria andRemainingDevelopmentYearIsNull() {
            addCriterion("remaining_development_year is null");
            return (Criteria) this;
        }

        public Criteria andRemainingDevelopmentYearIsNotNull() {
            addCriterion("remaining_development_year is not null");
            return (Criteria) this;
        }

        public Criteria andRemainingDevelopmentYearEqualTo(BigDecimal value) {
            addCriterion("remaining_development_year =", value, "remainingDevelopmentYear");
            return (Criteria) this;
        }

        public Criteria andRemainingDevelopmentYearNotEqualTo(BigDecimal value) {
            addCriterion("remaining_development_year <>", value, "remainingDevelopmentYear");
            return (Criteria) this;
        }

        public Criteria andRemainingDevelopmentYearGreaterThan(BigDecimal value) {
            addCriterion("remaining_development_year >", value, "remainingDevelopmentYear");
            return (Criteria) this;
        }

        public Criteria andRemainingDevelopmentYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("remaining_development_year >=", value, "remainingDevelopmentYear");
            return (Criteria) this;
        }

        public Criteria andRemainingDevelopmentYearLessThan(BigDecimal value) {
            addCriterion("remaining_development_year <", value, "remainingDevelopmentYear");
            return (Criteria) this;
        }

        public Criteria andRemainingDevelopmentYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("remaining_development_year <=", value, "remainingDevelopmentYear");
            return (Criteria) this;
        }

        public Criteria andRemainingDevelopmentYearIn(List<BigDecimal> values) {
            addCriterion("remaining_development_year in", values, "remainingDevelopmentYear");
            return (Criteria) this;
        }

        public Criteria andRemainingDevelopmentYearNotIn(List<BigDecimal> values) {
            addCriterion("remaining_development_year not in", values, "remainingDevelopmentYear");
            return (Criteria) this;
        }

        public Criteria andRemainingDevelopmentYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("remaining_development_year between", value1, value2, "remainingDevelopmentYear");
            return (Criteria) this;
        }

        public Criteria andRemainingDevelopmentYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("remaining_development_year not between", value1, value2, "remainingDevelopmentYear");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdIsNull() {
            addCriterion("reward_rate_id is null");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdIsNotNull() {
            addCriterion("reward_rate_id is not null");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdEqualTo(Integer value) {
            addCriterion("reward_rate_id =", value, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdNotEqualTo(Integer value) {
            addCriterion("reward_rate_id <>", value, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdGreaterThan(Integer value) {
            addCriterion("reward_rate_id >", value, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reward_rate_id >=", value, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdLessThan(Integer value) {
            addCriterion("reward_rate_id <", value, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdLessThanOrEqualTo(Integer value) {
            addCriterion("reward_rate_id <=", value, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdIn(List<Integer> values) {
            addCriterion("reward_rate_id in", values, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdNotIn(List<Integer> values) {
            addCriterion("reward_rate_id not in", values, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdBetween(Integer value1, Integer value2) {
            addCriterion("reward_rate_id between", value1, value2, "rewardRateId");
            return (Criteria) this;
        }

        public Criteria andRewardRateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reward_rate_id not between", value1, value2, "rewardRateId");
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