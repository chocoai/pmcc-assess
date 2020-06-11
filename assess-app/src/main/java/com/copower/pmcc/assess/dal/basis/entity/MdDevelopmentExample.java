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

        public Criteria andParcelSettingOuterIsNull() {
            addCriterion("parcel_setting_outer is null");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterIsNotNull() {
            addCriterion("parcel_setting_outer is not null");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterEqualTo(String value) {
            addCriterion("parcel_setting_outer =", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterNotEqualTo(String value) {
            addCriterion("parcel_setting_outer <>", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterGreaterThan(String value) {
            addCriterion("parcel_setting_outer >", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterGreaterThanOrEqualTo(String value) {
            addCriterion("parcel_setting_outer >=", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterLessThan(String value) {
            addCriterion("parcel_setting_outer <", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterLessThanOrEqualTo(String value) {
            addCriterion("parcel_setting_outer <=", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterLike(String value) {
            addCriterion("parcel_setting_outer like", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterNotLike(String value) {
            addCriterion("parcel_setting_outer not like", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterIn(List<String> values) {
            addCriterion("parcel_setting_outer in", values, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterNotIn(List<String> values) {
            addCriterion("parcel_setting_outer not in", values, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterBetween(String value1, String value2) {
            addCriterion("parcel_setting_outer between", value1, value2, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterNotBetween(String value1, String value2) {
            addCriterion("parcel_setting_outer not between", value1, value2, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerIsNull() {
            addCriterion("parcel_setting_inner is null");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerIsNotNull() {
            addCriterion("parcel_setting_inner is not null");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerEqualTo(String value) {
            addCriterion("parcel_setting_inner =", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerNotEqualTo(String value) {
            addCriterion("parcel_setting_inner <>", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerGreaterThan(String value) {
            addCriterion("parcel_setting_inner >", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerGreaterThanOrEqualTo(String value) {
            addCriterion("parcel_setting_inner >=", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerLessThan(String value) {
            addCriterion("parcel_setting_inner <", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerLessThanOrEqualTo(String value) {
            addCriterion("parcel_setting_inner <=", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerLike(String value) {
            addCriterion("parcel_setting_inner like", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerNotLike(String value) {
            addCriterion("parcel_setting_inner not like", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerIn(List<String> values) {
            addCriterion("parcel_setting_inner in", values, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerNotIn(List<String> values) {
            addCriterion("parcel_setting_inner not in", values, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerBetween(String value1, String value2) {
            addCriterion("parcel_setting_inner between", value1, value2, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerNotBetween(String value1, String value2) {
            addCriterion("parcel_setting_inner not between", value1, value2, "parcelSettingInner");
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

        public Criteria andEconomicIdIsNull() {
            addCriterion("economic_id is null");
            return (Criteria) this;
        }

        public Criteria andEconomicIdIsNotNull() {
            addCriterion("economic_id is not null");
            return (Criteria) this;
        }

        public Criteria andEconomicIdEqualTo(Integer value) {
            addCriterion("economic_id =", value, "economicId");
            return (Criteria) this;
        }

        public Criteria andEconomicIdNotEqualTo(Integer value) {
            addCriterion("economic_id <>", value, "economicId");
            return (Criteria) this;
        }

        public Criteria andEconomicIdGreaterThan(Integer value) {
            addCriterion("economic_id >", value, "economicId");
            return (Criteria) this;
        }

        public Criteria andEconomicIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("economic_id >=", value, "economicId");
            return (Criteria) this;
        }

        public Criteria andEconomicIdLessThan(Integer value) {
            addCriterion("economic_id <", value, "economicId");
            return (Criteria) this;
        }

        public Criteria andEconomicIdLessThanOrEqualTo(Integer value) {
            addCriterion("economic_id <=", value, "economicId");
            return (Criteria) this;
        }

        public Criteria andEconomicIdIn(List<Integer> values) {
            addCriterion("economic_id in", values, "economicId");
            return (Criteria) this;
        }

        public Criteria andEconomicIdNotIn(List<Integer> values) {
            addCriterion("economic_id not in", values, "economicId");
            return (Criteria) this;
        }

        public Criteria andEconomicIdBetween(Integer value1, Integer value2) {
            addCriterion("economic_id between", value1, value2, "economicId");
            return (Criteria) this;
        }

        public Criteria andEconomicIdNotBetween(Integer value1, Integer value2) {
            addCriterion("economic_id not between", value1, value2, "economicId");
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

        public Criteria andTotalSaleableAreaPriceIsNull() {
            addCriterion("total_saleable_area_price is null");
            return (Criteria) this;
        }

        public Criteria andTotalSaleableAreaPriceIsNotNull() {
            addCriterion("total_saleable_area_price is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSaleableAreaPriceEqualTo(BigDecimal value) {
            addCriterion("total_saleable_area_price =", value, "totalSaleableAreaPrice");
            return (Criteria) this;
        }

        public Criteria andTotalSaleableAreaPriceNotEqualTo(BigDecimal value) {
            addCriterion("total_saleable_area_price <>", value, "totalSaleableAreaPrice");
            return (Criteria) this;
        }

        public Criteria andTotalSaleableAreaPriceGreaterThan(BigDecimal value) {
            addCriterion("total_saleable_area_price >", value, "totalSaleableAreaPrice");
            return (Criteria) this;
        }

        public Criteria andTotalSaleableAreaPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_saleable_area_price >=", value, "totalSaleableAreaPrice");
            return (Criteria) this;
        }

        public Criteria andTotalSaleableAreaPriceLessThan(BigDecimal value) {
            addCriterion("total_saleable_area_price <", value, "totalSaleableAreaPrice");
            return (Criteria) this;
        }

        public Criteria andTotalSaleableAreaPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_saleable_area_price <=", value, "totalSaleableAreaPrice");
            return (Criteria) this;
        }

        public Criteria andTotalSaleableAreaPriceIn(List<BigDecimal> values) {
            addCriterion("total_saleable_area_price in", values, "totalSaleableAreaPrice");
            return (Criteria) this;
        }

        public Criteria andTotalSaleableAreaPriceNotIn(List<BigDecimal> values) {
            addCriterion("total_saleable_area_price not in", values, "totalSaleableAreaPrice");
            return (Criteria) this;
        }

        public Criteria andTotalSaleableAreaPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_saleable_area_price between", value1, value2, "totalSaleableAreaPrice");
            return (Criteria) this;
        }

        public Criteria andTotalSaleableAreaPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_saleable_area_price not between", value1, value2, "totalSaleableAreaPrice");
            return (Criteria) this;
        }

        public Criteria andSaleableAreaIsNull() {
            addCriterion("saleable_area is null");
            return (Criteria) this;
        }

        public Criteria andSaleableAreaIsNotNull() {
            addCriterion("saleable_area is not null");
            return (Criteria) this;
        }

        public Criteria andSaleableAreaEqualTo(BigDecimal value) {
            addCriterion("saleable_area =", value, "saleableArea");
            return (Criteria) this;
        }

        public Criteria andSaleableAreaNotEqualTo(BigDecimal value) {
            addCriterion("saleable_area <>", value, "saleableArea");
            return (Criteria) this;
        }

        public Criteria andSaleableAreaGreaterThan(BigDecimal value) {
            addCriterion("saleable_area >", value, "saleableArea");
            return (Criteria) this;
        }

        public Criteria andSaleableAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("saleable_area >=", value, "saleableArea");
            return (Criteria) this;
        }

        public Criteria andSaleableAreaLessThan(BigDecimal value) {
            addCriterion("saleable_area <", value, "saleableArea");
            return (Criteria) this;
        }

        public Criteria andSaleableAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("saleable_area <=", value, "saleableArea");
            return (Criteria) this;
        }

        public Criteria andSaleableAreaIn(List<BigDecimal> values) {
            addCriterion("saleable_area in", values, "saleableArea");
            return (Criteria) this;
        }

        public Criteria andSaleableAreaNotIn(List<BigDecimal> values) {
            addCriterion("saleable_area not in", values, "saleableArea");
            return (Criteria) this;
        }

        public Criteria andSaleableAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("saleable_area between", value1, value2, "saleableArea");
            return (Criteria) this;
        }

        public Criteria andSaleableAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("saleable_area not between", value1, value2, "saleableArea");
            return (Criteria) this;
        }

        public Criteria andPlannedBuildingAreaIsNull() {
            addCriterion("planned_building_area is null");
            return (Criteria) this;
        }

        public Criteria andPlannedBuildingAreaIsNotNull() {
            addCriterion("planned_building_area is not null");
            return (Criteria) this;
        }

        public Criteria andPlannedBuildingAreaEqualTo(BigDecimal value) {
            addCriterion("planned_building_area =", value, "plannedBuildingArea");
            return (Criteria) this;
        }

        public Criteria andPlannedBuildingAreaNotEqualTo(BigDecimal value) {
            addCriterion("planned_building_area <>", value, "plannedBuildingArea");
            return (Criteria) this;
        }

        public Criteria andPlannedBuildingAreaGreaterThan(BigDecimal value) {
            addCriterion("planned_building_area >", value, "plannedBuildingArea");
            return (Criteria) this;
        }

        public Criteria andPlannedBuildingAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("planned_building_area >=", value, "plannedBuildingArea");
            return (Criteria) this;
        }

        public Criteria andPlannedBuildingAreaLessThan(BigDecimal value) {
            addCriterion("planned_building_area <", value, "plannedBuildingArea");
            return (Criteria) this;
        }

        public Criteria andPlannedBuildingAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("planned_building_area <=", value, "plannedBuildingArea");
            return (Criteria) this;
        }

        public Criteria andPlannedBuildingAreaIn(List<BigDecimal> values) {
            addCriterion("planned_building_area in", values, "plannedBuildingArea");
            return (Criteria) this;
        }

        public Criteria andPlannedBuildingAreaNotIn(List<BigDecimal> values) {
            addCriterion("planned_building_area not in", values, "plannedBuildingArea");
            return (Criteria) this;
        }

        public Criteria andPlannedBuildingAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("planned_building_area between", value1, value2, "plannedBuildingArea");
            return (Criteria) this;
        }

        public Criteria andPlannedBuildingAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("planned_building_area not between", value1, value2, "plannedBuildingArea");
            return (Criteria) this;
        }

        public Criteria andUnsaleableBuildingAreaIsNull() {
            addCriterion("unsaleable_building_area is null");
            return (Criteria) this;
        }

        public Criteria andUnsaleableBuildingAreaIsNotNull() {
            addCriterion("unsaleable_building_area is not null");
            return (Criteria) this;
        }

        public Criteria andUnsaleableBuildingAreaEqualTo(BigDecimal value) {
            addCriterion("unsaleable_building_area =", value, "unsaleableBuildingArea");
            return (Criteria) this;
        }

        public Criteria andUnsaleableBuildingAreaNotEqualTo(BigDecimal value) {
            addCriterion("unsaleable_building_area <>", value, "unsaleableBuildingArea");
            return (Criteria) this;
        }

        public Criteria andUnsaleableBuildingAreaGreaterThan(BigDecimal value) {
            addCriterion("unsaleable_building_area >", value, "unsaleableBuildingArea");
            return (Criteria) this;
        }

        public Criteria andUnsaleableBuildingAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unsaleable_building_area >=", value, "unsaleableBuildingArea");
            return (Criteria) this;
        }

        public Criteria andUnsaleableBuildingAreaLessThan(BigDecimal value) {
            addCriterion("unsaleable_building_area <", value, "unsaleableBuildingArea");
            return (Criteria) this;
        }

        public Criteria andUnsaleableBuildingAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unsaleable_building_area <=", value, "unsaleableBuildingArea");
            return (Criteria) this;
        }

        public Criteria andUnsaleableBuildingAreaIn(List<BigDecimal> values) {
            addCriterion("unsaleable_building_area in", values, "unsaleableBuildingArea");
            return (Criteria) this;
        }

        public Criteria andUnsaleableBuildingAreaNotIn(List<BigDecimal> values) {
            addCriterion("unsaleable_building_area not in", values, "unsaleableBuildingArea");
            return (Criteria) this;
        }

        public Criteria andUnsaleableBuildingAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unsaleable_building_area between", value1, value2, "unsaleableBuildingArea");
            return (Criteria) this;
        }

        public Criteria andUnsaleableBuildingAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unsaleable_building_area not between", value1, value2, "unsaleableBuildingArea");
            return (Criteria) this;
        }

        public Criteria andTransferAreaIsNull() {
            addCriterion("transfer_area is null");
            return (Criteria) this;
        }

        public Criteria andTransferAreaIsNotNull() {
            addCriterion("transfer_area is not null");
            return (Criteria) this;
        }

        public Criteria andTransferAreaEqualTo(BigDecimal value) {
            addCriterion("transfer_area =", value, "transferArea");
            return (Criteria) this;
        }

        public Criteria andTransferAreaNotEqualTo(BigDecimal value) {
            addCriterion("transfer_area <>", value, "transferArea");
            return (Criteria) this;
        }

        public Criteria andTransferAreaGreaterThan(BigDecimal value) {
            addCriterion("transfer_area >", value, "transferArea");
            return (Criteria) this;
        }

        public Criteria andTransferAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("transfer_area >=", value, "transferArea");
            return (Criteria) this;
        }

        public Criteria andTransferAreaLessThan(BigDecimal value) {
            addCriterion("transfer_area <", value, "transferArea");
            return (Criteria) this;
        }

        public Criteria andTransferAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("transfer_area <=", value, "transferArea");
            return (Criteria) this;
        }

        public Criteria andTransferAreaIn(List<BigDecimal> values) {
            addCriterion("transfer_area in", values, "transferArea");
            return (Criteria) this;
        }

        public Criteria andTransferAreaNotIn(List<BigDecimal> values) {
            addCriterion("transfer_area not in", values, "transferArea");
            return (Criteria) this;
        }

        public Criteria andTransferAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transfer_area between", value1, value2, "transferArea");
            return (Criteria) this;
        }

        public Criteria andTransferAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transfer_area not between", value1, value2, "transferArea");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignIsNull() {
            addCriterion("reconnaissance_design is null");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignIsNotNull() {
            addCriterion("reconnaissance_design is not null");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignEqualTo(BigDecimal value) {
            addCriterion("reconnaissance_design =", value, "reconnaissanceDesign");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignNotEqualTo(BigDecimal value) {
            addCriterion("reconnaissance_design <>", value, "reconnaissanceDesign");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignGreaterThan(BigDecimal value) {
            addCriterion("reconnaissance_design >", value, "reconnaissanceDesign");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("reconnaissance_design >=", value, "reconnaissanceDesign");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignLessThan(BigDecimal value) {
            addCriterion("reconnaissance_design <", value, "reconnaissanceDesign");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignLessThanOrEqualTo(BigDecimal value) {
            addCriterion("reconnaissance_design <=", value, "reconnaissanceDesign");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignIn(List<BigDecimal> values) {
            addCriterion("reconnaissance_design in", values, "reconnaissanceDesign");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignNotIn(List<BigDecimal> values) {
            addCriterion("reconnaissance_design not in", values, "reconnaissanceDesign");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("reconnaissance_design between", value1, value2, "reconnaissanceDesign");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("reconnaissance_design not between", value1, value2, "reconnaissanceDesign");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeIdsIsNull() {
            addCriterion("construction_installation_engineering_fee_ids is null");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeIdsIsNotNull() {
            addCriterion("construction_installation_engineering_fee_ids is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeIdsEqualTo(String value) {
            addCriterion("construction_installation_engineering_fee_ids =", value, "constructionInstallationEngineeringFeeIds");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeIdsNotEqualTo(String value) {
            addCriterion("construction_installation_engineering_fee_ids <>", value, "constructionInstallationEngineeringFeeIds");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeIdsGreaterThan(String value) {
            addCriterion("construction_installation_engineering_fee_ids >", value, "constructionInstallationEngineeringFeeIds");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeIdsGreaterThanOrEqualTo(String value) {
            addCriterion("construction_installation_engineering_fee_ids >=", value, "constructionInstallationEngineeringFeeIds");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeIdsLessThan(String value) {
            addCriterion("construction_installation_engineering_fee_ids <", value, "constructionInstallationEngineeringFeeIds");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeIdsLessThanOrEqualTo(String value) {
            addCriterion("construction_installation_engineering_fee_ids <=", value, "constructionInstallationEngineeringFeeIds");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeIdsLike(String value) {
            addCriterion("construction_installation_engineering_fee_ids like", value, "constructionInstallationEngineeringFeeIds");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeIdsNotLike(String value) {
            addCriterion("construction_installation_engineering_fee_ids not like", value, "constructionInstallationEngineeringFeeIds");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeIdsIn(List<String> values) {
            addCriterion("construction_installation_engineering_fee_ids in", values, "constructionInstallationEngineeringFeeIds");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeIdsNotIn(List<String> values) {
            addCriterion("construction_installation_engineering_fee_ids not in", values, "constructionInstallationEngineeringFeeIds");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeIdsBetween(String value1, String value2) {
            addCriterion("construction_installation_engineering_fee_ids between", value1, value2, "constructionInstallationEngineeringFeeIds");
            return (Criteria) this;
        }

        public Criteria andConstructionInstallationEngineeringFeeIdsNotBetween(String value1, String value2) {
            addCriterion("construction_installation_engineering_fee_ids not between", value1, value2, "constructionInstallationEngineeringFeeIds");
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

        public Criteria andInfrastructureCostIsNull() {
            addCriterion("infrastructure_cost is null");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostIsNotNull() {
            addCriterion("infrastructure_cost is not null");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostEqualTo(BigDecimal value) {
            addCriterion("infrastructure_cost =", value, "infrastructureCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostNotEqualTo(BigDecimal value) {
            addCriterion("infrastructure_cost <>", value, "infrastructureCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostGreaterThan(BigDecimal value) {
            addCriterion("infrastructure_cost >", value, "infrastructureCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("infrastructure_cost >=", value, "infrastructureCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostLessThan(BigDecimal value) {
            addCriterion("infrastructure_cost <", value, "infrastructureCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("infrastructure_cost <=", value, "infrastructureCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostIn(List<BigDecimal> values) {
            addCriterion("infrastructure_cost in", values, "infrastructureCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostNotIn(List<BigDecimal> values) {
            addCriterion("infrastructure_cost not in", values, "infrastructureCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("infrastructure_cost between", value1, value2, "infrastructureCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("infrastructure_cost not between", value1, value2, "infrastructureCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostIsNull() {
            addCriterion("infrastructure_matching_cost is null");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostIsNotNull() {
            addCriterion("infrastructure_matching_cost is not null");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostEqualTo(BigDecimal value) {
            addCriterion("infrastructure_matching_cost =", value, "infrastructureMatchingCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostNotEqualTo(BigDecimal value) {
            addCriterion("infrastructure_matching_cost <>", value, "infrastructureMatchingCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostGreaterThan(BigDecimal value) {
            addCriterion("infrastructure_matching_cost >", value, "infrastructureMatchingCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("infrastructure_matching_cost >=", value, "infrastructureMatchingCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostLessThan(BigDecimal value) {
            addCriterion("infrastructure_matching_cost <", value, "infrastructureMatchingCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("infrastructure_matching_cost <=", value, "infrastructureMatchingCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostIn(List<BigDecimal> values) {
            addCriterion("infrastructure_matching_cost in", values, "infrastructureMatchingCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostNotIn(List<BigDecimal> values) {
            addCriterion("infrastructure_matching_cost not in", values, "infrastructureMatchingCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("infrastructure_matching_cost between", value1, value2, "infrastructureMatchingCost");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("infrastructure_matching_cost not between", value1, value2, "infrastructureMatchingCost");
            return (Criteria) this;
        }

        public Criteria andDevDuringIsNull() {
            addCriterion("dev_during is null");
            return (Criteria) this;
        }

        public Criteria andDevDuringIsNotNull() {
            addCriterion("dev_during is not null");
            return (Criteria) this;
        }

        public Criteria andDevDuringEqualTo(BigDecimal value) {
            addCriterion("dev_during =", value, "devDuring");
            return (Criteria) this;
        }

        public Criteria andDevDuringNotEqualTo(BigDecimal value) {
            addCriterion("dev_during <>", value, "devDuring");
            return (Criteria) this;
        }

        public Criteria andDevDuringGreaterThan(BigDecimal value) {
            addCriterion("dev_during >", value, "devDuring");
            return (Criteria) this;
        }

        public Criteria andDevDuringGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("dev_during >=", value, "devDuring");
            return (Criteria) this;
        }

        public Criteria andDevDuringLessThan(BigDecimal value) {
            addCriterion("dev_during <", value, "devDuring");
            return (Criteria) this;
        }

        public Criteria andDevDuringLessThanOrEqualTo(BigDecimal value) {
            addCriterion("dev_during <=", value, "devDuring");
            return (Criteria) this;
        }

        public Criteria andDevDuringIn(List<BigDecimal> values) {
            addCriterion("dev_during in", values, "devDuring");
            return (Criteria) this;
        }

        public Criteria andDevDuringNotIn(List<BigDecimal> values) {
            addCriterion("dev_during not in", values, "devDuring");
            return (Criteria) this;
        }

        public Criteria andDevDuringBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dev_during between", value1, value2, "devDuring");
            return (Criteria) this;
        }

        public Criteria andDevDuringNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("dev_during not between", value1, value2, "devDuring");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostIsNull() {
            addCriterion("other_engineering_cost is null");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostIsNotNull() {
            addCriterion("other_engineering_cost is not null");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostEqualTo(BigDecimal value) {
            addCriterion("other_engineering_cost =", value, "otherEngineeringCost");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostNotEqualTo(BigDecimal value) {
            addCriterion("other_engineering_cost <>", value, "otherEngineeringCost");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostGreaterThan(BigDecimal value) {
            addCriterion("other_engineering_cost >", value, "otherEngineeringCost");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("other_engineering_cost >=", value, "otherEngineeringCost");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostLessThan(BigDecimal value) {
            addCriterion("other_engineering_cost <", value, "otherEngineeringCost");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("other_engineering_cost <=", value, "otherEngineeringCost");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostIn(List<BigDecimal> values) {
            addCriterion("other_engineering_cost in", values, "otherEngineeringCost");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostNotIn(List<BigDecimal> values) {
            addCriterion("other_engineering_cost not in", values, "otherEngineeringCost");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_engineering_cost between", value1, value2, "otherEngineeringCost");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_engineering_cost not between", value1, value2, "otherEngineeringCost");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesIsNull() {
            addCriterion("unforeseen_expenses is null");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesIsNotNull() {
            addCriterion("unforeseen_expenses is not null");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesEqualTo(BigDecimal value) {
            addCriterion("unforeseen_expenses =", value, "unforeseenExpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesNotEqualTo(BigDecimal value) {
            addCriterion("unforeseen_expenses <>", value, "unforeseenExpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesGreaterThan(BigDecimal value) {
            addCriterion("unforeseen_expenses >", value, "unforeseenExpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unforeseen_expenses >=", value, "unforeseenExpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesLessThan(BigDecimal value) {
            addCriterion("unforeseen_expenses <", value, "unforeseenExpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unforeseen_expenses <=", value, "unforeseenExpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesIn(List<BigDecimal> values) {
            addCriterion("unforeseen_expenses in", values, "unforeseenExpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesNotIn(List<BigDecimal> values) {
            addCriterion("unforeseen_expenses not in", values, "unforeseenExpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unforeseen_expenses between", value1, value2, "unforeseenExpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unforeseen_expenses not between", value1, value2, "unforeseenExpenses");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignExplainIsNull() {
            addCriterion("reconnaissance_design_explain is null");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignExplainIsNotNull() {
            addCriterion("reconnaissance_design_explain is not null");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignExplainEqualTo(String value) {
            addCriterion("reconnaissance_design_explain =", value, "reconnaissanceDesignExplain");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignExplainNotEqualTo(String value) {
            addCriterion("reconnaissance_design_explain <>", value, "reconnaissanceDesignExplain");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignExplainGreaterThan(String value) {
            addCriterion("reconnaissance_design_explain >", value, "reconnaissanceDesignExplain");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignExplainGreaterThanOrEqualTo(String value) {
            addCriterion("reconnaissance_design_explain >=", value, "reconnaissanceDesignExplain");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignExplainLessThan(String value) {
            addCriterion("reconnaissance_design_explain <", value, "reconnaissanceDesignExplain");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignExplainLessThanOrEqualTo(String value) {
            addCriterion("reconnaissance_design_explain <=", value, "reconnaissanceDesignExplain");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignExplainLike(String value) {
            addCriterion("reconnaissance_design_explain like", value, "reconnaissanceDesignExplain");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignExplainNotLike(String value) {
            addCriterion("reconnaissance_design_explain not like", value, "reconnaissanceDesignExplain");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignExplainIn(List<String> values) {
            addCriterion("reconnaissance_design_explain in", values, "reconnaissanceDesignExplain");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignExplainNotIn(List<String> values) {
            addCriterion("reconnaissance_design_explain not in", values, "reconnaissanceDesignExplain");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignExplainBetween(String value1, String value2) {
            addCriterion("reconnaissance_design_explain between", value1, value2, "reconnaissanceDesignExplain");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceDesignExplainNotBetween(String value1, String value2) {
            addCriterion("reconnaissance_design_explain not between", value1, value2, "reconnaissanceDesignExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostExplainIsNull() {
            addCriterion("infrastructure_cost_explain is null");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostExplainIsNotNull() {
            addCriterion("infrastructure_cost_explain is not null");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostExplainEqualTo(String value) {
            addCriterion("infrastructure_cost_explain =", value, "infrastructureCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostExplainNotEqualTo(String value) {
            addCriterion("infrastructure_cost_explain <>", value, "infrastructureCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostExplainGreaterThan(String value) {
            addCriterion("infrastructure_cost_explain >", value, "infrastructureCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostExplainGreaterThanOrEqualTo(String value) {
            addCriterion("infrastructure_cost_explain >=", value, "infrastructureCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostExplainLessThan(String value) {
            addCriterion("infrastructure_cost_explain <", value, "infrastructureCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostExplainLessThanOrEqualTo(String value) {
            addCriterion("infrastructure_cost_explain <=", value, "infrastructureCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostExplainLike(String value) {
            addCriterion("infrastructure_cost_explain like", value, "infrastructureCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostExplainNotLike(String value) {
            addCriterion("infrastructure_cost_explain not like", value, "infrastructureCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostExplainIn(List<String> values) {
            addCriterion("infrastructure_cost_explain in", values, "infrastructureCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostExplainNotIn(List<String> values) {
            addCriterion("infrastructure_cost_explain not in", values, "infrastructureCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostExplainBetween(String value1, String value2) {
            addCriterion("infrastructure_cost_explain between", value1, value2, "infrastructureCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCostExplainNotBetween(String value1, String value2) {
            addCriterion("infrastructure_cost_explain not between", value1, value2, "infrastructureCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostExplainIsNull() {
            addCriterion("infrastructure_matching_cost_explain is null");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostExplainIsNotNull() {
            addCriterion("infrastructure_matching_cost_explain is not null");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostExplainEqualTo(String value) {
            addCriterion("infrastructure_matching_cost_explain =", value, "infrastructureMatchingCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostExplainNotEqualTo(String value) {
            addCriterion("infrastructure_matching_cost_explain <>", value, "infrastructureMatchingCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostExplainGreaterThan(String value) {
            addCriterion("infrastructure_matching_cost_explain >", value, "infrastructureMatchingCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostExplainGreaterThanOrEqualTo(String value) {
            addCriterion("infrastructure_matching_cost_explain >=", value, "infrastructureMatchingCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostExplainLessThan(String value) {
            addCriterion("infrastructure_matching_cost_explain <", value, "infrastructureMatchingCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostExplainLessThanOrEqualTo(String value) {
            addCriterion("infrastructure_matching_cost_explain <=", value, "infrastructureMatchingCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostExplainLike(String value) {
            addCriterion("infrastructure_matching_cost_explain like", value, "infrastructureMatchingCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostExplainNotLike(String value) {
            addCriterion("infrastructure_matching_cost_explain not like", value, "infrastructureMatchingCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostExplainIn(List<String> values) {
            addCriterion("infrastructure_matching_cost_explain in", values, "infrastructureMatchingCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostExplainNotIn(List<String> values) {
            addCriterion("infrastructure_matching_cost_explain not in", values, "infrastructureMatchingCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostExplainBetween(String value1, String value2) {
            addCriterion("infrastructure_matching_cost_explain between", value1, value2, "infrastructureMatchingCostExplain");
            return (Criteria) this;
        }

        public Criteria andInfrastructureMatchingCostExplainNotBetween(String value1, String value2) {
            addCriterion("infrastructure_matching_cost_explain not between", value1, value2, "infrastructureMatchingCostExplain");
            return (Criteria) this;
        }

        public Criteria andDevDuringExplainIsNull() {
            addCriterion("dev_during_explain is null");
            return (Criteria) this;
        }

        public Criteria andDevDuringExplainIsNotNull() {
            addCriterion("dev_during_explain is not null");
            return (Criteria) this;
        }

        public Criteria andDevDuringExplainEqualTo(String value) {
            addCriterion("dev_during_explain =", value, "devDuringExplain");
            return (Criteria) this;
        }

        public Criteria andDevDuringExplainNotEqualTo(String value) {
            addCriterion("dev_during_explain <>", value, "devDuringExplain");
            return (Criteria) this;
        }

        public Criteria andDevDuringExplainGreaterThan(String value) {
            addCriterion("dev_during_explain >", value, "devDuringExplain");
            return (Criteria) this;
        }

        public Criteria andDevDuringExplainGreaterThanOrEqualTo(String value) {
            addCriterion("dev_during_explain >=", value, "devDuringExplain");
            return (Criteria) this;
        }

        public Criteria andDevDuringExplainLessThan(String value) {
            addCriterion("dev_during_explain <", value, "devDuringExplain");
            return (Criteria) this;
        }

        public Criteria andDevDuringExplainLessThanOrEqualTo(String value) {
            addCriterion("dev_during_explain <=", value, "devDuringExplain");
            return (Criteria) this;
        }

        public Criteria andDevDuringExplainLike(String value) {
            addCriterion("dev_during_explain like", value, "devDuringExplain");
            return (Criteria) this;
        }

        public Criteria andDevDuringExplainNotLike(String value) {
            addCriterion("dev_during_explain not like", value, "devDuringExplain");
            return (Criteria) this;
        }

        public Criteria andDevDuringExplainIn(List<String> values) {
            addCriterion("dev_during_explain in", values, "devDuringExplain");
            return (Criteria) this;
        }

        public Criteria andDevDuringExplainNotIn(List<String> values) {
            addCriterion("dev_during_explain not in", values, "devDuringExplain");
            return (Criteria) this;
        }

        public Criteria andDevDuringExplainBetween(String value1, String value2) {
            addCriterion("dev_during_explain between", value1, value2, "devDuringExplain");
            return (Criteria) this;
        }

        public Criteria andDevDuringExplainNotBetween(String value1, String value2) {
            addCriterion("dev_during_explain not between", value1, value2, "devDuringExplain");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostExplainIsNull() {
            addCriterion("other_engineering_cost_explain is null");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostExplainIsNotNull() {
            addCriterion("other_engineering_cost_explain is not null");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostExplainEqualTo(String value) {
            addCriterion("other_engineering_cost_explain =", value, "otherEngineeringCostExplain");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostExplainNotEqualTo(String value) {
            addCriterion("other_engineering_cost_explain <>", value, "otherEngineeringCostExplain");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostExplainGreaterThan(String value) {
            addCriterion("other_engineering_cost_explain >", value, "otherEngineeringCostExplain");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostExplainGreaterThanOrEqualTo(String value) {
            addCriterion("other_engineering_cost_explain >=", value, "otherEngineeringCostExplain");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostExplainLessThan(String value) {
            addCriterion("other_engineering_cost_explain <", value, "otherEngineeringCostExplain");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostExplainLessThanOrEqualTo(String value) {
            addCriterion("other_engineering_cost_explain <=", value, "otherEngineeringCostExplain");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostExplainLike(String value) {
            addCriterion("other_engineering_cost_explain like", value, "otherEngineeringCostExplain");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostExplainNotLike(String value) {
            addCriterion("other_engineering_cost_explain not like", value, "otherEngineeringCostExplain");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostExplainIn(List<String> values) {
            addCriterion("other_engineering_cost_explain in", values, "otherEngineeringCostExplain");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostExplainNotIn(List<String> values) {
            addCriterion("other_engineering_cost_explain not in", values, "otherEngineeringCostExplain");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostExplainBetween(String value1, String value2) {
            addCriterion("other_engineering_cost_explain between", value1, value2, "otherEngineeringCostExplain");
            return (Criteria) this;
        }

        public Criteria andOtherEngineeringCostExplainNotBetween(String value1, String value2) {
            addCriterion("other_engineering_cost_explain not between", value1, value2, "otherEngineeringCostExplain");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesExplainIsNull() {
            addCriterion("unforeseen_expenses_explain is null");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesExplainIsNotNull() {
            addCriterion("unforeseen_expenses_explain is not null");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesExplainEqualTo(String value) {
            addCriterion("unforeseen_expenses_explain =", value, "unforeseenExpensesExplain");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesExplainNotEqualTo(String value) {
            addCriterion("unforeseen_expenses_explain <>", value, "unforeseenExpensesExplain");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesExplainGreaterThan(String value) {
            addCriterion("unforeseen_expenses_explain >", value, "unforeseenExpensesExplain");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesExplainGreaterThanOrEqualTo(String value) {
            addCriterion("unforeseen_expenses_explain >=", value, "unforeseenExpensesExplain");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesExplainLessThan(String value) {
            addCriterion("unforeseen_expenses_explain <", value, "unforeseenExpensesExplain");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesExplainLessThanOrEqualTo(String value) {
            addCriterion("unforeseen_expenses_explain <=", value, "unforeseenExpensesExplain");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesExplainLike(String value) {
            addCriterion("unforeseen_expenses_explain like", value, "unforeseenExpensesExplain");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesExplainNotLike(String value) {
            addCriterion("unforeseen_expenses_explain not like", value, "unforeseenExpensesExplain");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesExplainIn(List<String> values) {
            addCriterion("unforeseen_expenses_explain in", values, "unforeseenExpensesExplain");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesExplainNotIn(List<String> values) {
            addCriterion("unforeseen_expenses_explain not in", values, "unforeseenExpensesExplain");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesExplainBetween(String value1, String value2) {
            addCriterion("unforeseen_expenses_explain between", value1, value2, "unforeseenExpensesExplain");
            return (Criteria) this;
        }

        public Criteria andUnforeseenExpensesExplainNotBetween(String value1, String value2) {
            addCriterion("unforeseen_expenses_explain not between", value1, value2, "unforeseenExpensesExplain");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateIsNull() {
            addCriterion("deed_tax_rate is null");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateIsNotNull() {
            addCriterion("deed_tax_rate is not null");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateEqualTo(BigDecimal value) {
            addCriterion("deed_tax_rate =", value, "deedTaxRate");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateNotEqualTo(BigDecimal value) {
            addCriterion("deed_tax_rate <>", value, "deedTaxRate");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateGreaterThan(BigDecimal value) {
            addCriterion("deed_tax_rate >", value, "deedTaxRate");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deed_tax_rate >=", value, "deedTaxRate");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateLessThan(BigDecimal value) {
            addCriterion("deed_tax_rate <", value, "deedTaxRate");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deed_tax_rate <=", value, "deedTaxRate");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateIn(List<BigDecimal> values) {
            addCriterion("deed_tax_rate in", values, "deedTaxRate");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateNotIn(List<BigDecimal> values) {
            addCriterion("deed_tax_rate not in", values, "deedTaxRate");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deed_tax_rate between", value1, value2, "deedTaxRate");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deed_tax_rate not between", value1, value2, "deedTaxRate");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateExplainIsNull() {
            addCriterion("deed_tax_rate_explain is null");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateExplainIsNotNull() {
            addCriterion("deed_tax_rate_explain is not null");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateExplainEqualTo(String value) {
            addCriterion("deed_tax_rate_explain =", value, "deedTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateExplainNotEqualTo(String value) {
            addCriterion("deed_tax_rate_explain <>", value, "deedTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateExplainGreaterThan(String value) {
            addCriterion("deed_tax_rate_explain >", value, "deedTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateExplainGreaterThanOrEqualTo(String value) {
            addCriterion("deed_tax_rate_explain >=", value, "deedTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateExplainLessThan(String value) {
            addCriterion("deed_tax_rate_explain <", value, "deedTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateExplainLessThanOrEqualTo(String value) {
            addCriterion("deed_tax_rate_explain <=", value, "deedTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateExplainLike(String value) {
            addCriterion("deed_tax_rate_explain like", value, "deedTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateExplainNotLike(String value) {
            addCriterion("deed_tax_rate_explain not like", value, "deedTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateExplainIn(List<String> values) {
            addCriterion("deed_tax_rate_explain in", values, "deedTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateExplainNotIn(List<String> values) {
            addCriterion("deed_tax_rate_explain not in", values, "deedTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateExplainBetween(String value1, String value2) {
            addCriterion("deed_tax_rate_explain between", value1, value2, "deedTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andDeedTaxRateExplainNotBetween(String value1, String value2) {
            addCriterion("deed_tax_rate_explain not between", value1, value2, "deedTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateIsNull() {
            addCriterion("transaction_tax_rate is null");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateIsNotNull() {
            addCriterion("transaction_tax_rate is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateEqualTo(BigDecimal value) {
            addCriterion("transaction_tax_rate =", value, "transactionTaxRate");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateNotEqualTo(BigDecimal value) {
            addCriterion("transaction_tax_rate <>", value, "transactionTaxRate");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateGreaterThan(BigDecimal value) {
            addCriterion("transaction_tax_rate >", value, "transactionTaxRate");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("transaction_tax_rate >=", value, "transactionTaxRate");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateLessThan(BigDecimal value) {
            addCriterion("transaction_tax_rate <", value, "transactionTaxRate");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("transaction_tax_rate <=", value, "transactionTaxRate");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateIn(List<BigDecimal> values) {
            addCriterion("transaction_tax_rate in", values, "transactionTaxRate");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateNotIn(List<BigDecimal> values) {
            addCriterion("transaction_tax_rate not in", values, "transactionTaxRate");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transaction_tax_rate between", value1, value2, "transactionTaxRate");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transaction_tax_rate not between", value1, value2, "transactionTaxRate");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateExplainIsNull() {
            addCriterion("transaction_tax_rate_explain is null");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateExplainIsNotNull() {
            addCriterion("transaction_tax_rate_explain is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateExplainEqualTo(String value) {
            addCriterion("transaction_tax_rate_explain =", value, "transactionTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateExplainNotEqualTo(String value) {
            addCriterion("transaction_tax_rate_explain <>", value, "transactionTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateExplainGreaterThan(String value) {
            addCriterion("transaction_tax_rate_explain >", value, "transactionTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateExplainGreaterThanOrEqualTo(String value) {
            addCriterion("transaction_tax_rate_explain >=", value, "transactionTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateExplainLessThan(String value) {
            addCriterion("transaction_tax_rate_explain <", value, "transactionTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateExplainLessThanOrEqualTo(String value) {
            addCriterion("transaction_tax_rate_explain <=", value, "transactionTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateExplainLike(String value) {
            addCriterion("transaction_tax_rate_explain like", value, "transactionTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateExplainNotLike(String value) {
            addCriterion("transaction_tax_rate_explain not like", value, "transactionTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateExplainIn(List<String> values) {
            addCriterion("transaction_tax_rate_explain in", values, "transactionTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateExplainNotIn(List<String> values) {
            addCriterion("transaction_tax_rate_explain not in", values, "transactionTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateExplainBetween(String value1, String value2) {
            addCriterion("transaction_tax_rate_explain between", value1, value2, "transactionTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andTransactionTaxRateExplainNotBetween(String value1, String value2) {
            addCriterion("transaction_tax_rate_explain not between", value1, value2, "transactionTaxRateExplain");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseIsNull() {
            addCriterion("management_expense is null");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseIsNotNull() {
            addCriterion("management_expense is not null");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseEqualTo(BigDecimal value) {
            addCriterion("management_expense =", value, "managementExpense");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseNotEqualTo(BigDecimal value) {
            addCriterion("management_expense <>", value, "managementExpense");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseGreaterThan(BigDecimal value) {
            addCriterion("management_expense >", value, "managementExpense");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("management_expense >=", value, "managementExpense");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseLessThan(BigDecimal value) {
            addCriterion("management_expense <", value, "managementExpense");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseLessThanOrEqualTo(BigDecimal value) {
            addCriterion("management_expense <=", value, "managementExpense");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseIn(List<BigDecimal> values) {
            addCriterion("management_expense in", values, "managementExpense");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseNotIn(List<BigDecimal> values) {
            addCriterion("management_expense not in", values, "managementExpense");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("management_expense between", value1, value2, "managementExpense");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("management_expense not between", value1, value2, "managementExpense");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseExplainIsNull() {
            addCriterion("management_expense_explain is null");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseExplainIsNotNull() {
            addCriterion("management_expense_explain is not null");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseExplainEqualTo(String value) {
            addCriterion("management_expense_explain =", value, "managementExpenseExplain");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseExplainNotEqualTo(String value) {
            addCriterion("management_expense_explain <>", value, "managementExpenseExplain");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseExplainGreaterThan(String value) {
            addCriterion("management_expense_explain >", value, "managementExpenseExplain");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseExplainGreaterThanOrEqualTo(String value) {
            addCriterion("management_expense_explain >=", value, "managementExpenseExplain");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseExplainLessThan(String value) {
            addCriterion("management_expense_explain <", value, "managementExpenseExplain");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseExplainLessThanOrEqualTo(String value) {
            addCriterion("management_expense_explain <=", value, "managementExpenseExplain");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseExplainLike(String value) {
            addCriterion("management_expense_explain like", value, "managementExpenseExplain");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseExplainNotLike(String value) {
            addCriterion("management_expense_explain not like", value, "managementExpenseExplain");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseExplainIn(List<String> values) {
            addCriterion("management_expense_explain in", values, "managementExpenseExplain");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseExplainNotIn(List<String> values) {
            addCriterion("management_expense_explain not in", values, "managementExpenseExplain");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseExplainBetween(String value1, String value2) {
            addCriterion("management_expense_explain between", value1, value2, "managementExpenseExplain");
            return (Criteria) this;
        }

        public Criteria andManagementExpenseExplainNotBetween(String value1, String value2) {
            addCriterion("management_expense_explain not between", value1, value2, "managementExpenseExplain");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantIsNull() {
            addCriterion("land_get_relevant is null");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantIsNotNull() {
            addCriterion("land_get_relevant is not null");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantEqualTo(BigDecimal value) {
            addCriterion("land_get_relevant =", value, "landGetRelevant");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantNotEqualTo(BigDecimal value) {
            addCriterion("land_get_relevant <>", value, "landGetRelevant");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantGreaterThan(BigDecimal value) {
            addCriterion("land_get_relevant >", value, "landGetRelevant");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_get_relevant >=", value, "landGetRelevant");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantLessThan(BigDecimal value) {
            addCriterion("land_get_relevant <", value, "landGetRelevant");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_get_relevant <=", value, "landGetRelevant");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantIn(List<BigDecimal> values) {
            addCriterion("land_get_relevant in", values, "landGetRelevant");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantNotIn(List<BigDecimal> values) {
            addCriterion("land_get_relevant not in", values, "landGetRelevant");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_get_relevant between", value1, value2, "landGetRelevant");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_get_relevant not between", value1, value2, "landGetRelevant");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantExplainIsNull() {
            addCriterion("land_get_relevant_explain is null");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantExplainIsNotNull() {
            addCriterion("land_get_relevant_explain is not null");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantExplainEqualTo(String value) {
            addCriterion("land_get_relevant_explain =", value, "landGetRelevantExplain");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantExplainNotEqualTo(String value) {
            addCriterion("land_get_relevant_explain <>", value, "landGetRelevantExplain");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantExplainGreaterThan(String value) {
            addCriterion("land_get_relevant_explain >", value, "landGetRelevantExplain");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantExplainGreaterThanOrEqualTo(String value) {
            addCriterion("land_get_relevant_explain >=", value, "landGetRelevantExplain");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantExplainLessThan(String value) {
            addCriterion("land_get_relevant_explain <", value, "landGetRelevantExplain");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantExplainLessThanOrEqualTo(String value) {
            addCriterion("land_get_relevant_explain <=", value, "landGetRelevantExplain");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantExplainLike(String value) {
            addCriterion("land_get_relevant_explain like", value, "landGetRelevantExplain");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantExplainNotLike(String value) {
            addCriterion("land_get_relevant_explain not like", value, "landGetRelevantExplain");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantExplainIn(List<String> values) {
            addCriterion("land_get_relevant_explain in", values, "landGetRelevantExplain");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantExplainNotIn(List<String> values) {
            addCriterion("land_get_relevant_explain not in", values, "landGetRelevantExplain");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantExplainBetween(String value1, String value2) {
            addCriterion("land_get_relevant_explain between", value1, value2, "landGetRelevantExplain");
            return (Criteria) this;
        }

        public Criteria andLandGetRelevantExplainNotBetween(String value1, String value2) {
            addCriterion("land_get_relevant_explain not between", value1, value2, "landGetRelevantExplain");
            return (Criteria) this;
        }

        public Criteria andSalesFeeIsNull() {
            addCriterion("sales_fee is null");
            return (Criteria) this;
        }

        public Criteria andSalesFeeIsNotNull() {
            addCriterion("sales_fee is not null");
            return (Criteria) this;
        }

        public Criteria andSalesFeeEqualTo(BigDecimal value) {
            addCriterion("sales_fee =", value, "salesFee");
            return (Criteria) this;
        }

        public Criteria andSalesFeeNotEqualTo(BigDecimal value) {
            addCriterion("sales_fee <>", value, "salesFee");
            return (Criteria) this;
        }

        public Criteria andSalesFeeGreaterThan(BigDecimal value) {
            addCriterion("sales_fee >", value, "salesFee");
            return (Criteria) this;
        }

        public Criteria andSalesFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sales_fee >=", value, "salesFee");
            return (Criteria) this;
        }

        public Criteria andSalesFeeLessThan(BigDecimal value) {
            addCriterion("sales_fee <", value, "salesFee");
            return (Criteria) this;
        }

        public Criteria andSalesFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sales_fee <=", value, "salesFee");
            return (Criteria) this;
        }

        public Criteria andSalesFeeIn(List<BigDecimal> values) {
            addCriterion("sales_fee in", values, "salesFee");
            return (Criteria) this;
        }

        public Criteria andSalesFeeNotIn(List<BigDecimal> values) {
            addCriterion("sales_fee not in", values, "salesFee");
            return (Criteria) this;
        }

        public Criteria andSalesFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sales_fee between", value1, value2, "salesFee");
            return (Criteria) this;
        }

        public Criteria andSalesFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sales_fee not between", value1, value2, "salesFee");
            return (Criteria) this;
        }

        public Criteria andSalesFeeExplainIsNull() {
            addCriterion("sales_fee_explain is null");
            return (Criteria) this;
        }

        public Criteria andSalesFeeExplainIsNotNull() {
            addCriterion("sales_fee_explain is not null");
            return (Criteria) this;
        }

        public Criteria andSalesFeeExplainEqualTo(String value) {
            addCriterion("sales_fee_explain =", value, "salesFeeExplain");
            return (Criteria) this;
        }

        public Criteria andSalesFeeExplainNotEqualTo(String value) {
            addCriterion("sales_fee_explain <>", value, "salesFeeExplain");
            return (Criteria) this;
        }

        public Criteria andSalesFeeExplainGreaterThan(String value) {
            addCriterion("sales_fee_explain >", value, "salesFeeExplain");
            return (Criteria) this;
        }

        public Criteria andSalesFeeExplainGreaterThanOrEqualTo(String value) {
            addCriterion("sales_fee_explain >=", value, "salesFeeExplain");
            return (Criteria) this;
        }

        public Criteria andSalesFeeExplainLessThan(String value) {
            addCriterion("sales_fee_explain <", value, "salesFeeExplain");
            return (Criteria) this;
        }

        public Criteria andSalesFeeExplainLessThanOrEqualTo(String value) {
            addCriterion("sales_fee_explain <=", value, "salesFeeExplain");
            return (Criteria) this;
        }

        public Criteria andSalesFeeExplainLike(String value) {
            addCriterion("sales_fee_explain like", value, "salesFeeExplain");
            return (Criteria) this;
        }

        public Criteria andSalesFeeExplainNotLike(String value) {
            addCriterion("sales_fee_explain not like", value, "salesFeeExplain");
            return (Criteria) this;
        }

        public Criteria andSalesFeeExplainIn(List<String> values) {
            addCriterion("sales_fee_explain in", values, "salesFeeExplain");
            return (Criteria) this;
        }

        public Criteria andSalesFeeExplainNotIn(List<String> values) {
            addCriterion("sales_fee_explain not in", values, "salesFeeExplain");
            return (Criteria) this;
        }

        public Criteria andSalesFeeExplainBetween(String value1, String value2) {
            addCriterion("sales_fee_explain between", value1, value2, "salesFeeExplain");
            return (Criteria) this;
        }

        public Criteria andSalesFeeExplainNotBetween(String value1, String value2) {
            addCriterion("sales_fee_explain not between", value1, value2, "salesFeeExplain");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxIsNull() {
            addCriterion("interest_investment_tax is null");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxIsNotNull() {
            addCriterion("interest_investment_tax is not null");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxEqualTo(BigDecimal value) {
            addCriterion("interest_investment_tax =", value, "interestInvestmentTax");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxNotEqualTo(BigDecimal value) {
            addCriterion("interest_investment_tax <>", value, "interestInvestmentTax");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxGreaterThan(BigDecimal value) {
            addCriterion("interest_investment_tax >", value, "interestInvestmentTax");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("interest_investment_tax >=", value, "interestInvestmentTax");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxLessThan(BigDecimal value) {
            addCriterion("interest_investment_tax <", value, "interestInvestmentTax");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("interest_investment_tax <=", value, "interestInvestmentTax");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxIn(List<BigDecimal> values) {
            addCriterion("interest_investment_tax in", values, "interestInvestmentTax");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxNotIn(List<BigDecimal> values) {
            addCriterion("interest_investment_tax not in", values, "interestInvestmentTax");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("interest_investment_tax between", value1, value2, "interestInvestmentTax");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("interest_investment_tax not between", value1, value2, "interestInvestmentTax");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxExplainIsNull() {
            addCriterion("interest_investment_tax_explain is null");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxExplainIsNotNull() {
            addCriterion("interest_investment_tax_explain is not null");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxExplainEqualTo(String value) {
            addCriterion("interest_investment_tax_explain =", value, "interestInvestmentTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxExplainNotEqualTo(String value) {
            addCriterion("interest_investment_tax_explain <>", value, "interestInvestmentTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxExplainGreaterThan(String value) {
            addCriterion("interest_investment_tax_explain >", value, "interestInvestmentTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxExplainGreaterThanOrEqualTo(String value) {
            addCriterion("interest_investment_tax_explain >=", value, "interestInvestmentTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxExplainLessThan(String value) {
            addCriterion("interest_investment_tax_explain <", value, "interestInvestmentTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxExplainLessThanOrEqualTo(String value) {
            addCriterion("interest_investment_tax_explain <=", value, "interestInvestmentTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxExplainLike(String value) {
            addCriterion("interest_investment_tax_explain like", value, "interestInvestmentTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxExplainNotLike(String value) {
            addCriterion("interest_investment_tax_explain not like", value, "interestInvestmentTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxExplainIn(List<String> values) {
            addCriterion("interest_investment_tax_explain in", values, "interestInvestmentTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxExplainNotIn(List<String> values) {
            addCriterion("interest_investment_tax_explain not in", values, "interestInvestmentTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxExplainBetween(String value1, String value2) {
            addCriterion("interest_investment_tax_explain between", value1, value2, "interestInvestmentTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentTaxExplainNotBetween(String value1, String value2) {
            addCriterion("interest_investment_tax_explain not between", value1, value2, "interestInvestmentTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxIsNull() {
            addCriterion("investment_profit_tax is null");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxIsNotNull() {
            addCriterion("investment_profit_tax is not null");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxEqualTo(BigDecimal value) {
            addCriterion("investment_profit_tax =", value, "investmentProfitTax");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxNotEqualTo(BigDecimal value) {
            addCriterion("investment_profit_tax <>", value, "investmentProfitTax");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxGreaterThan(BigDecimal value) {
            addCriterion("investment_profit_tax >", value, "investmentProfitTax");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("investment_profit_tax >=", value, "investmentProfitTax");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxLessThan(BigDecimal value) {
            addCriterion("investment_profit_tax <", value, "investmentProfitTax");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("investment_profit_tax <=", value, "investmentProfitTax");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxIn(List<BigDecimal> values) {
            addCriterion("investment_profit_tax in", values, "investmentProfitTax");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxNotIn(List<BigDecimal> values) {
            addCriterion("investment_profit_tax not in", values, "investmentProfitTax");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("investment_profit_tax between", value1, value2, "investmentProfitTax");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("investment_profit_tax not between", value1, value2, "investmentProfitTax");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxExplainIsNull() {
            addCriterion("investment_profit_tax_explain is null");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxExplainIsNotNull() {
            addCriterion("investment_profit_tax_explain is not null");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxExplainEqualTo(String value) {
            addCriterion("investment_profit_tax_explain =", value, "investmentProfitTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxExplainNotEqualTo(String value) {
            addCriterion("investment_profit_tax_explain <>", value, "investmentProfitTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxExplainGreaterThan(String value) {
            addCriterion("investment_profit_tax_explain >", value, "investmentProfitTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxExplainGreaterThanOrEqualTo(String value) {
            addCriterion("investment_profit_tax_explain >=", value, "investmentProfitTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxExplainLessThan(String value) {
            addCriterion("investment_profit_tax_explain <", value, "investmentProfitTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxExplainLessThanOrEqualTo(String value) {
            addCriterion("investment_profit_tax_explain <=", value, "investmentProfitTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxExplainLike(String value) {
            addCriterion("investment_profit_tax_explain like", value, "investmentProfitTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxExplainNotLike(String value) {
            addCriterion("investment_profit_tax_explain not like", value, "investmentProfitTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxExplainIn(List<String> values) {
            addCriterion("investment_profit_tax_explain in", values, "investmentProfitTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxExplainNotIn(List<String> values) {
            addCriterion("investment_profit_tax_explain not in", values, "investmentProfitTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxExplainBetween(String value1, String value2) {
            addCriterion("investment_profit_tax_explain between", value1, value2, "investmentProfitTaxExplain");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitTaxExplainNotBetween(String value1, String value2) {
            addCriterion("investment_profit_tax_explain not between", value1, value2, "investmentProfitTaxExplain");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalIsNull() {
            addCriterion("sales_tax_and_additional is null");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalIsNotNull() {
            addCriterion("sales_tax_and_additional is not null");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalEqualTo(BigDecimal value) {
            addCriterion("sales_tax_and_additional =", value, "salesTaxAndAdditional");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalNotEqualTo(BigDecimal value) {
            addCriterion("sales_tax_and_additional <>", value, "salesTaxAndAdditional");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalGreaterThan(BigDecimal value) {
            addCriterion("sales_tax_and_additional >", value, "salesTaxAndAdditional");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sales_tax_and_additional >=", value, "salesTaxAndAdditional");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalLessThan(BigDecimal value) {
            addCriterion("sales_tax_and_additional <", value, "salesTaxAndAdditional");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sales_tax_and_additional <=", value, "salesTaxAndAdditional");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalIn(List<BigDecimal> values) {
            addCriterion("sales_tax_and_additional in", values, "salesTaxAndAdditional");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalNotIn(List<BigDecimal> values) {
            addCriterion("sales_tax_and_additional not in", values, "salesTaxAndAdditional");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sales_tax_and_additional between", value1, value2, "salesTaxAndAdditional");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sales_tax_and_additional not between", value1, value2, "salesTaxAndAdditional");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalExplainIsNull() {
            addCriterion("sales_tax_and_additional_explain is null");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalExplainIsNotNull() {
            addCriterion("sales_tax_and_additional_explain is not null");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalExplainEqualTo(String value) {
            addCriterion("sales_tax_and_additional_explain =", value, "salesTaxAndAdditionalExplain");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalExplainNotEqualTo(String value) {
            addCriterion("sales_tax_and_additional_explain <>", value, "salesTaxAndAdditionalExplain");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalExplainGreaterThan(String value) {
            addCriterion("sales_tax_and_additional_explain >", value, "salesTaxAndAdditionalExplain");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalExplainGreaterThanOrEqualTo(String value) {
            addCriterion("sales_tax_and_additional_explain >=", value, "salesTaxAndAdditionalExplain");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalExplainLessThan(String value) {
            addCriterion("sales_tax_and_additional_explain <", value, "salesTaxAndAdditionalExplain");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalExplainLessThanOrEqualTo(String value) {
            addCriterion("sales_tax_and_additional_explain <=", value, "salesTaxAndAdditionalExplain");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalExplainLike(String value) {
            addCriterion("sales_tax_and_additional_explain like", value, "salesTaxAndAdditionalExplain");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalExplainNotLike(String value) {
            addCriterion("sales_tax_and_additional_explain not like", value, "salesTaxAndAdditionalExplain");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalExplainIn(List<String> values) {
            addCriterion("sales_tax_and_additional_explain in", values, "salesTaxAndAdditionalExplain");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalExplainNotIn(List<String> values) {
            addCriterion("sales_tax_and_additional_explain not in", values, "salesTaxAndAdditionalExplain");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalExplainBetween(String value1, String value2) {
            addCriterion("sales_tax_and_additional_explain between", value1, value2, "salesTaxAndAdditionalExplain");
            return (Criteria) this;
        }

        public Criteria andSalesTaxAndAdditionalExplainNotBetween(String value1, String value2) {
            addCriterion("sales_tax_and_additional_explain not between", value1, value2, "salesTaxAndAdditionalExplain");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxIsNull() {
            addCriterion("land_value_added_tax is null");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxIsNotNull() {
            addCriterion("land_value_added_tax is not null");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxEqualTo(BigDecimal value) {
            addCriterion("land_value_added_tax =", value, "landValueAddedTax");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxNotEqualTo(BigDecimal value) {
            addCriterion("land_value_added_tax <>", value, "landValueAddedTax");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxGreaterThan(BigDecimal value) {
            addCriterion("land_value_added_tax >", value, "landValueAddedTax");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_value_added_tax >=", value, "landValueAddedTax");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxLessThan(BigDecimal value) {
            addCriterion("land_value_added_tax <", value, "landValueAddedTax");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_value_added_tax <=", value, "landValueAddedTax");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxIn(List<BigDecimal> values) {
            addCriterion("land_value_added_tax in", values, "landValueAddedTax");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxNotIn(List<BigDecimal> values) {
            addCriterion("land_value_added_tax not in", values, "landValueAddedTax");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_value_added_tax between", value1, value2, "landValueAddedTax");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_value_added_tax not between", value1, value2, "landValueAddedTax");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxExplainIsNull() {
            addCriterion("land_value_added_tax_explain is null");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxExplainIsNotNull() {
            addCriterion("land_value_added_tax_explain is not null");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxExplainEqualTo(String value) {
            addCriterion("land_value_added_tax_explain =", value, "landValueAddedTaxExplain");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxExplainNotEqualTo(String value) {
            addCriterion("land_value_added_tax_explain <>", value, "landValueAddedTaxExplain");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxExplainGreaterThan(String value) {
            addCriterion("land_value_added_tax_explain >", value, "landValueAddedTaxExplain");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxExplainGreaterThanOrEqualTo(String value) {
            addCriterion("land_value_added_tax_explain >=", value, "landValueAddedTaxExplain");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxExplainLessThan(String value) {
            addCriterion("land_value_added_tax_explain <", value, "landValueAddedTaxExplain");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxExplainLessThanOrEqualTo(String value) {
            addCriterion("land_value_added_tax_explain <=", value, "landValueAddedTaxExplain");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxExplainLike(String value) {
            addCriterion("land_value_added_tax_explain like", value, "landValueAddedTaxExplain");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxExplainNotLike(String value) {
            addCriterion("land_value_added_tax_explain not like", value, "landValueAddedTaxExplain");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxExplainIn(List<String> values) {
            addCriterion("land_value_added_tax_explain in", values, "landValueAddedTaxExplain");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxExplainNotIn(List<String> values) {
            addCriterion("land_value_added_tax_explain not in", values, "landValueAddedTaxExplain");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxExplainBetween(String value1, String value2) {
            addCriterion("land_value_added_tax_explain between", value1, value2, "landValueAddedTaxExplain");
            return (Criteria) this;
        }

        public Criteria andLandValueAddedTaxExplainNotBetween(String value1, String value2) {
            addCriterion("land_value_added_tax_explain not between", value1, value2, "landValueAddedTaxExplain");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxIsNull() {
            addCriterion("project_development_income_tax is null");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxIsNotNull() {
            addCriterion("project_development_income_tax is not null");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxEqualTo(BigDecimal value) {
            addCriterion("project_development_income_tax =", value, "projectDevelopmentIncomeTax");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxNotEqualTo(BigDecimal value) {
            addCriterion("project_development_income_tax <>", value, "projectDevelopmentIncomeTax");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxGreaterThan(BigDecimal value) {
            addCriterion("project_development_income_tax >", value, "projectDevelopmentIncomeTax");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("project_development_income_tax >=", value, "projectDevelopmentIncomeTax");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxLessThan(BigDecimal value) {
            addCriterion("project_development_income_tax <", value, "projectDevelopmentIncomeTax");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("project_development_income_tax <=", value, "projectDevelopmentIncomeTax");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxIn(List<BigDecimal> values) {
            addCriterion("project_development_income_tax in", values, "projectDevelopmentIncomeTax");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxNotIn(List<BigDecimal> values) {
            addCriterion("project_development_income_tax not in", values, "projectDevelopmentIncomeTax");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("project_development_income_tax between", value1, value2, "projectDevelopmentIncomeTax");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("project_development_income_tax not between", value1, value2, "projectDevelopmentIncomeTax");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxExplainIsNull() {
            addCriterion("project_development_income_tax_explain is null");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxExplainIsNotNull() {
            addCriterion("project_development_income_tax_explain is not null");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxExplainEqualTo(String value) {
            addCriterion("project_development_income_tax_explain =", value, "projectDevelopmentIncomeTaxExplain");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxExplainNotEqualTo(String value) {
            addCriterion("project_development_income_tax_explain <>", value, "projectDevelopmentIncomeTaxExplain");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxExplainGreaterThan(String value) {
            addCriterion("project_development_income_tax_explain >", value, "projectDevelopmentIncomeTaxExplain");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxExplainGreaterThanOrEqualTo(String value) {
            addCriterion("project_development_income_tax_explain >=", value, "projectDevelopmentIncomeTaxExplain");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxExplainLessThan(String value) {
            addCriterion("project_development_income_tax_explain <", value, "projectDevelopmentIncomeTaxExplain");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxExplainLessThanOrEqualTo(String value) {
            addCriterion("project_development_income_tax_explain <=", value, "projectDevelopmentIncomeTaxExplain");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxExplainLike(String value) {
            addCriterion("project_development_income_tax_explain like", value, "projectDevelopmentIncomeTaxExplain");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxExplainNotLike(String value) {
            addCriterion("project_development_income_tax_explain not like", value, "projectDevelopmentIncomeTaxExplain");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxExplainIn(List<String> values) {
            addCriterion("project_development_income_tax_explain in", values, "projectDevelopmentIncomeTaxExplain");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxExplainNotIn(List<String> values) {
            addCriterion("project_development_income_tax_explain not in", values, "projectDevelopmentIncomeTaxExplain");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxExplainBetween(String value1, String value2) {
            addCriterion("project_development_income_tax_explain between", value1, value2, "projectDevelopmentIncomeTaxExplain");
            return (Criteria) this;
        }

        public Criteria andProjectDevelopmentIncomeTaxExplainNotBetween(String value1, String value2) {
            addCriterion("project_development_income_tax_explain not between", value1, value2, "projectDevelopmentIncomeTaxExplain");
            return (Criteria) this;
        }

        public Criteria andRemunerationRateIsNull() {
            addCriterion("remuneration_rate is null");
            return (Criteria) this;
        }

        public Criteria andRemunerationRateIsNotNull() {
            addCriterion("remuneration_rate is not null");
            return (Criteria) this;
        }

        public Criteria andRemunerationRateEqualTo(BigDecimal value) {
            addCriterion("remuneration_rate =", value, "remunerationRate");
            return (Criteria) this;
        }

        public Criteria andRemunerationRateNotEqualTo(BigDecimal value) {
            addCriterion("remuneration_rate <>", value, "remunerationRate");
            return (Criteria) this;
        }

        public Criteria andRemunerationRateGreaterThan(BigDecimal value) {
            addCriterion("remuneration_rate >", value, "remunerationRate");
            return (Criteria) this;
        }

        public Criteria andRemunerationRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("remuneration_rate >=", value, "remunerationRate");
            return (Criteria) this;
        }

        public Criteria andRemunerationRateLessThan(BigDecimal value) {
            addCriterion("remuneration_rate <", value, "remunerationRate");
            return (Criteria) this;
        }

        public Criteria andRemunerationRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("remuneration_rate <=", value, "remunerationRate");
            return (Criteria) this;
        }

        public Criteria andRemunerationRateIn(List<BigDecimal> values) {
            addCriterion("remuneration_rate in", values, "remunerationRate");
            return (Criteria) this;
        }

        public Criteria andRemunerationRateNotIn(List<BigDecimal> values) {
            addCriterion("remuneration_rate not in", values, "remunerationRate");
            return (Criteria) this;
        }

        public Criteria andRemunerationRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("remuneration_rate between", value1, value2, "remunerationRate");
            return (Criteria) this;
        }

        public Criteria andRemunerationRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("remuneration_rate not between", value1, value2, "remunerationRate");
            return (Criteria) this;
        }

        public Criteria andStatutoryLifeIsNull() {
            addCriterion("statutory_life is null");
            return (Criteria) this;
        }

        public Criteria andStatutoryLifeIsNotNull() {
            addCriterion("statutory_life is not null");
            return (Criteria) this;
        }

        public Criteria andStatutoryLifeEqualTo(BigDecimal value) {
            addCriterion("statutory_life =", value, "statutoryLife");
            return (Criteria) this;
        }

        public Criteria andStatutoryLifeNotEqualTo(BigDecimal value) {
            addCriterion("statutory_life <>", value, "statutoryLife");
            return (Criteria) this;
        }

        public Criteria andStatutoryLifeGreaterThan(BigDecimal value) {
            addCriterion("statutory_life >", value, "statutoryLife");
            return (Criteria) this;
        }

        public Criteria andStatutoryLifeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("statutory_life >=", value, "statutoryLife");
            return (Criteria) this;
        }

        public Criteria andStatutoryLifeLessThan(BigDecimal value) {
            addCriterion("statutory_life <", value, "statutoryLife");
            return (Criteria) this;
        }

        public Criteria andStatutoryLifeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("statutory_life <=", value, "statutoryLife");
            return (Criteria) this;
        }

        public Criteria andStatutoryLifeIn(List<BigDecimal> values) {
            addCriterion("statutory_life in", values, "statutoryLife");
            return (Criteria) this;
        }

        public Criteria andStatutoryLifeNotIn(List<BigDecimal> values) {
            addCriterion("statutory_life not in", values, "statutoryLife");
            return (Criteria) this;
        }

        public Criteria andStatutoryLifeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("statutory_life between", value1, value2, "statutoryLife");
            return (Criteria) this;
        }

        public Criteria andStatutoryLifeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("statutory_life not between", value1, value2, "statutoryLife");
            return (Criteria) this;
        }

        public Criteria andRemainingYearsIsNull() {
            addCriterion("remaining_years is null");
            return (Criteria) this;
        }

        public Criteria andRemainingYearsIsNotNull() {
            addCriterion("remaining_years is not null");
            return (Criteria) this;
        }

        public Criteria andRemainingYearsEqualTo(BigDecimal value) {
            addCriterion("remaining_years =", value, "remainingYears");
            return (Criteria) this;
        }

        public Criteria andRemainingYearsNotEqualTo(BigDecimal value) {
            addCriterion("remaining_years <>", value, "remainingYears");
            return (Criteria) this;
        }

        public Criteria andRemainingYearsGreaterThan(BigDecimal value) {
            addCriterion("remaining_years >", value, "remainingYears");
            return (Criteria) this;
        }

        public Criteria andRemainingYearsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("remaining_years >=", value, "remainingYears");
            return (Criteria) this;
        }

        public Criteria andRemainingYearsLessThan(BigDecimal value) {
            addCriterion("remaining_years <", value, "remainingYears");
            return (Criteria) this;
        }

        public Criteria andRemainingYearsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("remaining_years <=", value, "remainingYears");
            return (Criteria) this;
        }

        public Criteria andRemainingYearsIn(List<BigDecimal> values) {
            addCriterion("remaining_years in", values, "remainingYears");
            return (Criteria) this;
        }

        public Criteria andRemainingYearsNotIn(List<BigDecimal> values) {
            addCriterion("remaining_years not in", values, "remainingYears");
            return (Criteria) this;
        }

        public Criteria andRemainingYearsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("remaining_years between", value1, value2, "remainingYears");
            return (Criteria) this;
        }

        public Criteria andRemainingYearsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("remaining_years not between", value1, value2, "remainingYears");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsIsNull() {
            addCriterion("amendment_status_rights is null");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsIsNotNull() {
            addCriterion("amendment_status_rights is not null");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsEqualTo(BigDecimal value) {
            addCriterion("amendment_status_rights =", value, "amendmentStatusRights");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsNotEqualTo(BigDecimal value) {
            addCriterion("amendment_status_rights <>", value, "amendmentStatusRights");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsGreaterThan(BigDecimal value) {
            addCriterion("amendment_status_rights >", value, "amendmentStatusRights");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amendment_status_rights >=", value, "amendmentStatusRights");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsLessThan(BigDecimal value) {
            addCriterion("amendment_status_rights <", value, "amendmentStatusRights");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amendment_status_rights <=", value, "amendmentStatusRights");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsIn(List<BigDecimal> values) {
            addCriterion("amendment_status_rights in", values, "amendmentStatusRights");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsNotIn(List<BigDecimal> values) {
            addCriterion("amendment_status_rights not in", values, "amendmentStatusRights");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amendment_status_rights between", value1, value2, "amendmentStatusRights");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amendment_status_rights not between", value1, value2, "amendmentStatusRights");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsExplainIsNull() {
            addCriterion("amendment_status_rights_explain is null");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsExplainIsNotNull() {
            addCriterion("amendment_status_rights_explain is not null");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsExplainEqualTo(String value) {
            addCriterion("amendment_status_rights_explain =", value, "amendmentStatusRightsExplain");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsExplainNotEqualTo(String value) {
            addCriterion("amendment_status_rights_explain <>", value, "amendmentStatusRightsExplain");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsExplainGreaterThan(String value) {
            addCriterion("amendment_status_rights_explain >", value, "amendmentStatusRightsExplain");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsExplainGreaterThanOrEqualTo(String value) {
            addCriterion("amendment_status_rights_explain >=", value, "amendmentStatusRightsExplain");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsExplainLessThan(String value) {
            addCriterion("amendment_status_rights_explain <", value, "amendmentStatusRightsExplain");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsExplainLessThanOrEqualTo(String value) {
            addCriterion("amendment_status_rights_explain <=", value, "amendmentStatusRightsExplain");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsExplainLike(String value) {
            addCriterion("amendment_status_rights_explain like", value, "amendmentStatusRightsExplain");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsExplainNotLike(String value) {
            addCriterion("amendment_status_rights_explain not like", value, "amendmentStatusRightsExplain");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsExplainIn(List<String> values) {
            addCriterion("amendment_status_rights_explain in", values, "amendmentStatusRightsExplain");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsExplainNotIn(List<String> values) {
            addCriterion("amendment_status_rights_explain not in", values, "amendmentStatusRightsExplain");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsExplainBetween(String value1, String value2) {
            addCriterion("amendment_status_rights_explain between", value1, value2, "amendmentStatusRightsExplain");
            return (Criteria) this;
        }

        public Criteria andAmendmentStatusRightsExplainNotBetween(String value1, String value2) {
            addCriterion("amendment_status_rights_explain not between", value1, value2, "amendmentStatusRightsExplain");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsIsNull() {
            addCriterion("other_amendments is null");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsIsNotNull() {
            addCriterion("other_amendments is not null");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsEqualTo(BigDecimal value) {
            addCriterion("other_amendments =", value, "otherAmendments");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsNotEqualTo(BigDecimal value) {
            addCriterion("other_amendments <>", value, "otherAmendments");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsGreaterThan(BigDecimal value) {
            addCriterion("other_amendments >", value, "otherAmendments");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("other_amendments >=", value, "otherAmendments");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsLessThan(BigDecimal value) {
            addCriterion("other_amendments <", value, "otherAmendments");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("other_amendments <=", value, "otherAmendments");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsIn(List<BigDecimal> values) {
            addCriterion("other_amendments in", values, "otherAmendments");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsNotIn(List<BigDecimal> values) {
            addCriterion("other_amendments not in", values, "otherAmendments");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_amendments between", value1, value2, "otherAmendments");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_amendments not between", value1, value2, "otherAmendments");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsExplainIsNull() {
            addCriterion("other_amendments_explain is null");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsExplainIsNotNull() {
            addCriterion("other_amendments_explain is not null");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsExplainEqualTo(String value) {
            addCriterion("other_amendments_explain =", value, "otherAmendmentsExplain");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsExplainNotEqualTo(String value) {
            addCriterion("other_amendments_explain <>", value, "otherAmendmentsExplain");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsExplainGreaterThan(String value) {
            addCriterion("other_amendments_explain >", value, "otherAmendmentsExplain");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsExplainGreaterThanOrEqualTo(String value) {
            addCriterion("other_amendments_explain >=", value, "otherAmendmentsExplain");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsExplainLessThan(String value) {
            addCriterion("other_amendments_explain <", value, "otherAmendmentsExplain");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsExplainLessThanOrEqualTo(String value) {
            addCriterion("other_amendments_explain <=", value, "otherAmendmentsExplain");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsExplainLike(String value) {
            addCriterion("other_amendments_explain like", value, "otherAmendmentsExplain");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsExplainNotLike(String value) {
            addCriterion("other_amendments_explain not like", value, "otherAmendmentsExplain");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsExplainIn(List<String> values) {
            addCriterion("other_amendments_explain in", values, "otherAmendmentsExplain");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsExplainNotIn(List<String> values) {
            addCriterion("other_amendments_explain not in", values, "otherAmendmentsExplain");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsExplainBetween(String value1, String value2) {
            addCriterion("other_amendments_explain between", value1, value2, "otherAmendmentsExplain");
            return (Criteria) this;
        }

        public Criteria andOtherAmendmentsExplainNotBetween(String value1, String value2) {
            addCriterion("other_amendments_explain not between", value1, value2, "otherAmendmentsExplain");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionIsNull() {
            addCriterion("development_degree_revision is null");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionIsNotNull() {
            addCriterion("development_degree_revision is not null");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionEqualTo(BigDecimal value) {
            addCriterion("development_degree_revision =", value, "developmentDegreeRevision");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionNotEqualTo(BigDecimal value) {
            addCriterion("development_degree_revision <>", value, "developmentDegreeRevision");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionGreaterThan(BigDecimal value) {
            addCriterion("development_degree_revision >", value, "developmentDegreeRevision");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("development_degree_revision >=", value, "developmentDegreeRevision");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionLessThan(BigDecimal value) {
            addCriterion("development_degree_revision <", value, "developmentDegreeRevision");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("development_degree_revision <=", value, "developmentDegreeRevision");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionIn(List<BigDecimal> values) {
            addCriterion("development_degree_revision in", values, "developmentDegreeRevision");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionNotIn(List<BigDecimal> values) {
            addCriterion("development_degree_revision not in", values, "developmentDegreeRevision");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("development_degree_revision between", value1, value2, "developmentDegreeRevision");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("development_degree_revision not between", value1, value2, "developmentDegreeRevision");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionExplainIsNull() {
            addCriterion("development_degree_revision_explain is null");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionExplainIsNotNull() {
            addCriterion("development_degree_revision_explain is not null");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionExplainEqualTo(String value) {
            addCriterion("development_degree_revision_explain =", value, "developmentDegreeRevisionExplain");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionExplainNotEqualTo(String value) {
            addCriterion("development_degree_revision_explain <>", value, "developmentDegreeRevisionExplain");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionExplainGreaterThan(String value) {
            addCriterion("development_degree_revision_explain >", value, "developmentDegreeRevisionExplain");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionExplainGreaterThanOrEqualTo(String value) {
            addCriterion("development_degree_revision_explain >=", value, "developmentDegreeRevisionExplain");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionExplainLessThan(String value) {
            addCriterion("development_degree_revision_explain <", value, "developmentDegreeRevisionExplain");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionExplainLessThanOrEqualTo(String value) {
            addCriterion("development_degree_revision_explain <=", value, "developmentDegreeRevisionExplain");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionExplainLike(String value) {
            addCriterion("development_degree_revision_explain like", value, "developmentDegreeRevisionExplain");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionExplainNotLike(String value) {
            addCriterion("development_degree_revision_explain not like", value, "developmentDegreeRevisionExplain");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionExplainIn(List<String> values) {
            addCriterion("development_degree_revision_explain in", values, "developmentDegreeRevisionExplain");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionExplainNotIn(List<String> values) {
            addCriterion("development_degree_revision_explain not in", values, "developmentDegreeRevisionExplain");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionExplainBetween(String value1, String value2) {
            addCriterion("development_degree_revision_explain between", value1, value2, "developmentDegreeRevisionExplain");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeRevisionExplainNotBetween(String value1, String value2) {
            addCriterion("development_degree_revision_explain not between", value1, value2, "developmentDegreeRevisionExplain");
            return (Criteria) this;
        }

        public Criteria andCenterIdIsNull() {
            addCriterion("center_id is null");
            return (Criteria) this;
        }

        public Criteria andCenterIdIsNotNull() {
            addCriterion("center_id is not null");
            return (Criteria) this;
        }

        public Criteria andCenterIdEqualTo(Integer value) {
            addCriterion("center_id =", value, "centerId");
            return (Criteria) this;
        }

        public Criteria andCenterIdNotEqualTo(Integer value) {
            addCriterion("center_id <>", value, "centerId");
            return (Criteria) this;
        }

        public Criteria andCenterIdGreaterThan(Integer value) {
            addCriterion("center_id >", value, "centerId");
            return (Criteria) this;
        }

        public Criteria andCenterIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("center_id >=", value, "centerId");
            return (Criteria) this;
        }

        public Criteria andCenterIdLessThan(Integer value) {
            addCriterion("center_id <", value, "centerId");
            return (Criteria) this;
        }

        public Criteria andCenterIdLessThanOrEqualTo(Integer value) {
            addCriterion("center_id <=", value, "centerId");
            return (Criteria) this;
        }

        public Criteria andCenterIdIn(List<Integer> values) {
            addCriterion("center_id in", values, "centerId");
            return (Criteria) this;
        }

        public Criteria andCenterIdNotIn(List<Integer> values) {
            addCriterion("center_id not in", values, "centerId");
            return (Criteria) this;
        }

        public Criteria andCenterIdBetween(Integer value1, Integer value2) {
            addCriterion("center_id between", value1, value2, "centerId");
            return (Criteria) this;
        }

        public Criteria andCenterIdNotBetween(Integer value1, Integer value2) {
            addCriterion("center_id not between", value1, value2, "centerId");
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

        public Criteria andLandAreaIsNull() {
            addCriterion("land_area is null");
            return (Criteria) this;
        }

        public Criteria andLandAreaIsNotNull() {
            addCriterion("land_area is not null");
            return (Criteria) this;
        }

        public Criteria andLandAreaEqualTo(BigDecimal value) {
            addCriterion("land_area =", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotEqualTo(BigDecimal value) {
            addCriterion("land_area <>", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaGreaterThan(BigDecimal value) {
            addCriterion("land_area >", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_area >=", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaLessThan(BigDecimal value) {
            addCriterion("land_area <", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_area <=", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaIn(List<BigDecimal> values) {
            addCriterion("land_area in", values, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotIn(List<BigDecimal> values) {
            addCriterion("land_area not in", values, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_area between", value1, value2, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_area not between", value1, value2, "landArea");
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