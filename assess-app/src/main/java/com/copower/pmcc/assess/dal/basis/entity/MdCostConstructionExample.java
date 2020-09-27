package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdCostConstructionExample {
    /**
     * tb_md_cost_construction
     */
    protected String orderByClause;

    /**
     * tb_md_cost_construction
     */
    protected boolean distinct;

    /**
     * tb_md_cost_construction
     */
    protected List<Criteria> oredCriteria;

    public MdCostConstructionExample() {
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
     * tb_md_cost_construction
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

        public Criteria andConstructionAssessmentPriceCorrectingIsNull() {
            addCriterion("construction_assessment_price_correcting is null");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentPriceCorrectingIsNotNull() {
            addCriterion("construction_assessment_price_correcting is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentPriceCorrectingEqualTo(BigDecimal value) {
            addCriterion("construction_assessment_price_correcting =", value, "constructionAssessmentPriceCorrecting");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentPriceCorrectingNotEqualTo(BigDecimal value) {
            addCriterion("construction_assessment_price_correcting <>", value, "constructionAssessmentPriceCorrecting");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentPriceCorrectingGreaterThan(BigDecimal value) {
            addCriterion("construction_assessment_price_correcting >", value, "constructionAssessmentPriceCorrecting");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentPriceCorrectingGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("construction_assessment_price_correcting >=", value, "constructionAssessmentPriceCorrecting");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentPriceCorrectingLessThan(BigDecimal value) {
            addCriterion("construction_assessment_price_correcting <", value, "constructionAssessmentPriceCorrecting");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentPriceCorrectingLessThanOrEqualTo(BigDecimal value) {
            addCriterion("construction_assessment_price_correcting <=", value, "constructionAssessmentPriceCorrecting");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentPriceCorrectingIn(List<BigDecimal> values) {
            addCriterion("construction_assessment_price_correcting in", values, "constructionAssessmentPriceCorrecting");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentPriceCorrectingNotIn(List<BigDecimal> values) {
            addCriterion("construction_assessment_price_correcting not in", values, "constructionAssessmentPriceCorrecting");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentPriceCorrectingBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("construction_assessment_price_correcting between", value1, value2, "constructionAssessmentPriceCorrecting");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentPriceCorrectingNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("construction_assessment_price_correcting not between", value1, value2, "constructionAssessmentPriceCorrecting");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValueIsNull() {
            addCriterion("construction_assessment_value is null");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValueIsNotNull() {
            addCriterion("construction_assessment_value is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValueEqualTo(String value) {
            addCriterion("construction_assessment_value =", value, "constructionAssessmentValue");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValueNotEqualTo(String value) {
            addCriterion("construction_assessment_value <>", value, "constructionAssessmentValue");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValueGreaterThan(String value) {
            addCriterion("construction_assessment_value >", value, "constructionAssessmentValue");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValueGreaterThanOrEqualTo(String value) {
            addCriterion("construction_assessment_value >=", value, "constructionAssessmentValue");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValueLessThan(String value) {
            addCriterion("construction_assessment_value <", value, "constructionAssessmentValue");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValueLessThanOrEqualTo(String value) {
            addCriterion("construction_assessment_value <=", value, "constructionAssessmentValue");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValueLike(String value) {
            addCriterion("construction_assessment_value like", value, "constructionAssessmentValue");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValueNotLike(String value) {
            addCriterion("construction_assessment_value not like", value, "constructionAssessmentValue");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValueIn(List<String> values) {
            addCriterion("construction_assessment_value in", values, "constructionAssessmentValue");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValueNotIn(List<String> values) {
            addCriterion("construction_assessment_value not in", values, "constructionAssessmentValue");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValueBetween(String value1, String value2) {
            addCriterion("construction_assessment_value between", value1, value2, "constructionAssessmentValue");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValueNotBetween(String value1, String value2) {
            addCriterion("construction_assessment_value not between", value1, value2, "constructionAssessmentValue");
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

        public Criteria andInvestmentProfitEqualTo(String value) {
            addCriterion("investment_profit =", value, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitNotEqualTo(String value) {
            addCriterion("investment_profit <>", value, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitGreaterThan(String value) {
            addCriterion("investment_profit >", value, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitGreaterThanOrEqualTo(String value) {
            addCriterion("investment_profit >=", value, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitLessThan(String value) {
            addCriterion("investment_profit <", value, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitLessThanOrEqualTo(String value) {
            addCriterion("investment_profit <=", value, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitLike(String value) {
            addCriterion("investment_profit like", value, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitNotLike(String value) {
            addCriterion("investment_profit not like", value, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitIn(List<String> values) {
            addCriterion("investment_profit in", values, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitNotIn(List<String> values) {
            addCriterion("investment_profit not in", values, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitBetween(String value1, String value2) {
            addCriterion("investment_profit between", value1, value2, "investmentProfit");
            return (Criteria) this;
        }

        public Criteria andInvestmentProfitNotBetween(String value1, String value2) {
            addCriterion("investment_profit not between", value1, value2, "investmentProfit");
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

        public Criteria andInterestInvestmentEqualTo(String value) {
            addCriterion("interest_investment =", value, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentNotEqualTo(String value) {
            addCriterion("interest_investment <>", value, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentGreaterThan(String value) {
            addCriterion("interest_investment >", value, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentGreaterThanOrEqualTo(String value) {
            addCriterion("interest_investment >=", value, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentLessThan(String value) {
            addCriterion("interest_investment <", value, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentLessThanOrEqualTo(String value) {
            addCriterion("interest_investment <=", value, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentLike(String value) {
            addCriterion("interest_investment like", value, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentNotLike(String value) {
            addCriterion("interest_investment not like", value, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentIn(List<String> values) {
            addCriterion("interest_investment in", values, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentNotIn(List<String> values) {
            addCriterion("interest_investment not in", values, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentBetween(String value1, String value2) {
            addCriterion("interest_investment between", value1, value2, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andInterestInvestmentNotBetween(String value1, String value2) {
            addCriterion("interest_investment not between", value1, value2, "interestInvestment");
            return (Criteria) this;
        }

        public Criteria andConstructionSubtotalIsNull() {
            addCriterion("construction_subtotal is null");
            return (Criteria) this;
        }

        public Criteria andConstructionSubtotalIsNotNull() {
            addCriterion("construction_subtotal is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionSubtotalEqualTo(String value) {
            addCriterion("construction_subtotal =", value, "constructionSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionSubtotalNotEqualTo(String value) {
            addCriterion("construction_subtotal <>", value, "constructionSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionSubtotalGreaterThan(String value) {
            addCriterion("construction_subtotal >", value, "constructionSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionSubtotalGreaterThanOrEqualTo(String value) {
            addCriterion("construction_subtotal >=", value, "constructionSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionSubtotalLessThan(String value) {
            addCriterion("construction_subtotal <", value, "constructionSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionSubtotalLessThanOrEqualTo(String value) {
            addCriterion("construction_subtotal <=", value, "constructionSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionSubtotalLike(String value) {
            addCriterion("construction_subtotal like", value, "constructionSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionSubtotalNotLike(String value) {
            addCriterion("construction_subtotal not like", value, "constructionSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionSubtotalIn(List<String> values) {
            addCriterion("construction_subtotal in", values, "constructionSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionSubtotalNotIn(List<String> values) {
            addCriterion("construction_subtotal not in", values, "constructionSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionSubtotalBetween(String value1, String value2) {
            addCriterion("construction_subtotal between", value1, value2, "constructionSubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionSubtotalNotBetween(String value1, String value2) {
            addCriterion("construction_subtotal not between", value1, value2, "constructionSubtotal");
            return (Criteria) this;
        }

        public Criteria andLandGetCostTotalIsNull() {
            addCriterion("land_get_cost_total is null");
            return (Criteria) this;
        }

        public Criteria andLandGetCostTotalIsNotNull() {
            addCriterion("land_get_cost_total is not null");
            return (Criteria) this;
        }

        public Criteria andLandGetCostTotalEqualTo(String value) {
            addCriterion("land_get_cost_total =", value, "landGetCostTotal");
            return (Criteria) this;
        }

        public Criteria andLandGetCostTotalNotEqualTo(String value) {
            addCriterion("land_get_cost_total <>", value, "landGetCostTotal");
            return (Criteria) this;
        }

        public Criteria andLandGetCostTotalGreaterThan(String value) {
            addCriterion("land_get_cost_total >", value, "landGetCostTotal");
            return (Criteria) this;
        }

        public Criteria andLandGetCostTotalGreaterThanOrEqualTo(String value) {
            addCriterion("land_get_cost_total >=", value, "landGetCostTotal");
            return (Criteria) this;
        }

        public Criteria andLandGetCostTotalLessThan(String value) {
            addCriterion("land_get_cost_total <", value, "landGetCostTotal");
            return (Criteria) this;
        }

        public Criteria andLandGetCostTotalLessThanOrEqualTo(String value) {
            addCriterion("land_get_cost_total <=", value, "landGetCostTotal");
            return (Criteria) this;
        }

        public Criteria andLandGetCostTotalLike(String value) {
            addCriterion("land_get_cost_total like", value, "landGetCostTotal");
            return (Criteria) this;
        }

        public Criteria andLandGetCostTotalNotLike(String value) {
            addCriterion("land_get_cost_total not like", value, "landGetCostTotal");
            return (Criteria) this;
        }

        public Criteria andLandGetCostTotalIn(List<String> values) {
            addCriterion("land_get_cost_total in", values, "landGetCostTotal");
            return (Criteria) this;
        }

        public Criteria andLandGetCostTotalNotIn(List<String> values) {
            addCriterion("land_get_cost_total not in", values, "landGetCostTotal");
            return (Criteria) this;
        }

        public Criteria andLandGetCostTotalBetween(String value1, String value2) {
            addCriterion("land_get_cost_total between", value1, value2, "landGetCostTotal");
            return (Criteria) this;
        }

        public Criteria andLandGetCostTotalNotBetween(String value1, String value2) {
            addCriterion("land_get_cost_total not between", value1, value2, "landGetCostTotal");
            return (Criteria) this;
        }

        public Criteria andDevelopLandAreaTaxIsNull() {
            addCriterion("develop_land_area_tax is null");
            return (Criteria) this;
        }

        public Criteria andDevelopLandAreaTaxIsNotNull() {
            addCriterion("develop_land_area_tax is not null");
            return (Criteria) this;
        }

        public Criteria andDevelopLandAreaTaxEqualTo(BigDecimal value) {
            addCriterion("develop_land_area_tax =", value, "developLandAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopLandAreaTaxNotEqualTo(BigDecimal value) {
            addCriterion("develop_land_area_tax <>", value, "developLandAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopLandAreaTaxGreaterThan(BigDecimal value) {
            addCriterion("develop_land_area_tax >", value, "developLandAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopLandAreaTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("develop_land_area_tax >=", value, "developLandAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopLandAreaTaxLessThan(BigDecimal value) {
            addCriterion("develop_land_area_tax <", value, "developLandAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopLandAreaTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("develop_land_area_tax <=", value, "developLandAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopLandAreaTaxIn(List<BigDecimal> values) {
            addCriterion("develop_land_area_tax in", values, "developLandAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopLandAreaTaxNotIn(List<BigDecimal> values) {
            addCriterion("develop_land_area_tax not in", values, "developLandAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopLandAreaTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("develop_land_area_tax between", value1, value2, "developLandAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopLandAreaTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("develop_land_area_tax not between", value1, value2, "developLandAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopBuildAreaTaxIsNull() {
            addCriterion("develop_build_area_tax is null");
            return (Criteria) this;
        }

        public Criteria andDevelopBuildAreaTaxIsNotNull() {
            addCriterion("develop_build_area_tax is not null");
            return (Criteria) this;
        }

        public Criteria andDevelopBuildAreaTaxEqualTo(BigDecimal value) {
            addCriterion("develop_build_area_tax =", value, "developBuildAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopBuildAreaTaxNotEqualTo(BigDecimal value) {
            addCriterion("develop_build_area_tax <>", value, "developBuildAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopBuildAreaTaxGreaterThan(BigDecimal value) {
            addCriterion("develop_build_area_tax >", value, "developBuildAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopBuildAreaTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("develop_build_area_tax >=", value, "developBuildAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopBuildAreaTaxLessThan(BigDecimal value) {
            addCriterion("develop_build_area_tax <", value, "developBuildAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopBuildAreaTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("develop_build_area_tax <=", value, "developBuildAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopBuildAreaTaxIn(List<BigDecimal> values) {
            addCriterion("develop_build_area_tax in", values, "developBuildAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopBuildAreaTaxNotIn(List<BigDecimal> values) {
            addCriterion("develop_build_area_tax not in", values, "developBuildAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopBuildAreaTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("develop_build_area_tax between", value1, value2, "developBuildAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopBuildAreaTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("develop_build_area_tax not between", value1, value2, "developBuildAreaTax");
            return (Criteria) this;
        }

        public Criteria andDevelopYearNumberTaxIsNull() {
            addCriterion("develop_year_number_tax is null");
            return (Criteria) this;
        }

        public Criteria andDevelopYearNumberTaxIsNotNull() {
            addCriterion("develop_year_number_tax is not null");
            return (Criteria) this;
        }

        public Criteria andDevelopYearNumberTaxEqualTo(BigDecimal value) {
            addCriterion("develop_year_number_tax =", value, "developYearNumberTax");
            return (Criteria) this;
        }

        public Criteria andDevelopYearNumberTaxNotEqualTo(BigDecimal value) {
            addCriterion("develop_year_number_tax <>", value, "developYearNumberTax");
            return (Criteria) this;
        }

        public Criteria andDevelopYearNumberTaxGreaterThan(BigDecimal value) {
            addCriterion("develop_year_number_tax >", value, "developYearNumberTax");
            return (Criteria) this;
        }

        public Criteria andDevelopYearNumberTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("develop_year_number_tax >=", value, "developYearNumberTax");
            return (Criteria) this;
        }

        public Criteria andDevelopYearNumberTaxLessThan(BigDecimal value) {
            addCriterion("develop_year_number_tax <", value, "developYearNumberTax");
            return (Criteria) this;
        }

        public Criteria andDevelopYearNumberTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("develop_year_number_tax <=", value, "developYearNumberTax");
            return (Criteria) this;
        }

        public Criteria andDevelopYearNumberTaxIn(List<BigDecimal> values) {
            addCriterion("develop_year_number_tax in", values, "developYearNumberTax");
            return (Criteria) this;
        }

        public Criteria andDevelopYearNumberTaxNotIn(List<BigDecimal> values) {
            addCriterion("develop_year_number_tax not in", values, "developYearNumberTax");
            return (Criteria) this;
        }

        public Criteria andDevelopYearNumberTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("develop_year_number_tax between", value1, value2, "developYearNumberTax");
            return (Criteria) this;
        }

        public Criteria andDevelopYearNumberTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("develop_year_number_tax not between", value1, value2, "developYearNumberTax");
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

        public Criteria andBaseLandIdIsNull() {
            addCriterion("base_land_id is null");
            return (Criteria) this;
        }

        public Criteria andBaseLandIdIsNotNull() {
            addCriterion("base_land_id is not null");
            return (Criteria) this;
        }

        public Criteria andBaseLandIdEqualTo(Integer value) {
            addCriterion("base_land_id =", value, "baseLandId");
            return (Criteria) this;
        }

        public Criteria andBaseLandIdNotEqualTo(Integer value) {
            addCriterion("base_land_id <>", value, "baseLandId");
            return (Criteria) this;
        }

        public Criteria andBaseLandIdGreaterThan(Integer value) {
            addCriterion("base_land_id >", value, "baseLandId");
            return (Criteria) this;
        }

        public Criteria andBaseLandIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("base_land_id >=", value, "baseLandId");
            return (Criteria) this;
        }

        public Criteria andBaseLandIdLessThan(Integer value) {
            addCriterion("base_land_id <", value, "baseLandId");
            return (Criteria) this;
        }

        public Criteria andBaseLandIdLessThanOrEqualTo(Integer value) {
            addCriterion("base_land_id <=", value, "baseLandId");
            return (Criteria) this;
        }

        public Criteria andBaseLandIdIn(List<Integer> values) {
            addCriterion("base_land_id in", values, "baseLandId");
            return (Criteria) this;
        }

        public Criteria andBaseLandIdNotIn(List<Integer> values) {
            addCriterion("base_land_id not in", values, "baseLandId");
            return (Criteria) this;
        }

        public Criteria andBaseLandIdBetween(Integer value1, Integer value2) {
            addCriterion("base_land_id between", value1, value2, "baseLandId");
            return (Criteria) this;
        }

        public Criteria andBaseLandIdNotBetween(Integer value1, Integer value2) {
            addCriterion("base_land_id not between", value1, value2, "baseLandId");
            return (Criteria) this;
        }

        public Criteria andApproachIdIsNull() {
            addCriterion("approach_id is null");
            return (Criteria) this;
        }

        public Criteria andApproachIdIsNotNull() {
            addCriterion("approach_id is not null");
            return (Criteria) this;
        }

        public Criteria andApproachIdEqualTo(Integer value) {
            addCriterion("approach_id =", value, "approachId");
            return (Criteria) this;
        }

        public Criteria andApproachIdNotEqualTo(Integer value) {
            addCriterion("approach_id <>", value, "approachId");
            return (Criteria) this;
        }

        public Criteria andApproachIdGreaterThan(Integer value) {
            addCriterion("approach_id >", value, "approachId");
            return (Criteria) this;
        }

        public Criteria andApproachIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("approach_id >=", value, "approachId");
            return (Criteria) this;
        }

        public Criteria andApproachIdLessThan(Integer value) {
            addCriterion("approach_id <", value, "approachId");
            return (Criteria) this;
        }

        public Criteria andApproachIdLessThanOrEqualTo(Integer value) {
            addCriterion("approach_id <=", value, "approachId");
            return (Criteria) this;
        }

        public Criteria andApproachIdIn(List<Integer> values) {
            addCriterion("approach_id in", values, "approachId");
            return (Criteria) this;
        }

        public Criteria andApproachIdNotIn(List<Integer> values) {
            addCriterion("approach_id not in", values, "approachId");
            return (Criteria) this;
        }

        public Criteria andApproachIdBetween(Integer value1, Integer value2) {
            addCriterion("approach_id between", value1, value2, "approachId");
            return (Criteria) this;
        }

        public Criteria andApproachIdNotBetween(Integer value1, Integer value2) {
            addCriterion("approach_id not between", value1, value2, "approachId");
            return (Criteria) this;
        }

        public Criteria andMcIdIsNull() {
            addCriterion("mc_id is null");
            return (Criteria) this;
        }

        public Criteria andMcIdIsNotNull() {
            addCriterion("mc_id is not null");
            return (Criteria) this;
        }

        public Criteria andMcIdEqualTo(Integer value) {
            addCriterion("mc_id =", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdNotEqualTo(Integer value) {
            addCriterion("mc_id <>", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdGreaterThan(Integer value) {
            addCriterion("mc_id >", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mc_id >=", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdLessThan(Integer value) {
            addCriterion("mc_id <", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdLessThanOrEqualTo(Integer value) {
            addCriterion("mc_id <=", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdIn(List<Integer> values) {
            addCriterion("mc_id in", values, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdNotIn(List<Integer> values) {
            addCriterion("mc_id not in", values, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdBetween(Integer value1, Integer value2) {
            addCriterion("mc_id between", value1, value2, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mc_id not between", value1, value2, "mcId");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceIsNull() {
            addCriterion("land_purchase_price is null");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceIsNotNull() {
            addCriterion("land_purchase_price is not null");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceEqualTo(BigDecimal value) {
            addCriterion("land_purchase_price =", value, "landPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceNotEqualTo(BigDecimal value) {
            addCriterion("land_purchase_price <>", value, "landPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceGreaterThan(BigDecimal value) {
            addCriterion("land_purchase_price >", value, "landPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_purchase_price >=", value, "landPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceLessThan(BigDecimal value) {
            addCriterion("land_purchase_price <", value, "landPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_purchase_price <=", value, "landPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceIn(List<BigDecimal> values) {
            addCriterion("land_purchase_price in", values, "landPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceNotIn(List<BigDecimal> values) {
            addCriterion("land_purchase_price not in", values, "landPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_purchase_price between", value1, value2, "landPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_purchase_price not between", value1, value2, "landPurchasePrice");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceExplainIsNull() {
            addCriterion("land_purchase_price_explain is null");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceExplainIsNotNull() {
            addCriterion("land_purchase_price_explain is not null");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceExplainEqualTo(String value) {
            addCriterion("land_purchase_price_explain =", value, "landPurchasePriceExplain");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceExplainNotEqualTo(String value) {
            addCriterion("land_purchase_price_explain <>", value, "landPurchasePriceExplain");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceExplainGreaterThan(String value) {
            addCriterion("land_purchase_price_explain >", value, "landPurchasePriceExplain");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceExplainGreaterThanOrEqualTo(String value) {
            addCriterion("land_purchase_price_explain >=", value, "landPurchasePriceExplain");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceExplainLessThan(String value) {
            addCriterion("land_purchase_price_explain <", value, "landPurchasePriceExplain");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceExplainLessThanOrEqualTo(String value) {
            addCriterion("land_purchase_price_explain <=", value, "landPurchasePriceExplain");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceExplainLike(String value) {
            addCriterion("land_purchase_price_explain like", value, "landPurchasePriceExplain");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceExplainNotLike(String value) {
            addCriterion("land_purchase_price_explain not like", value, "landPurchasePriceExplain");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceExplainIn(List<String> values) {
            addCriterion("land_purchase_price_explain in", values, "landPurchasePriceExplain");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceExplainNotIn(List<String> values) {
            addCriterion("land_purchase_price_explain not in", values, "landPurchasePriceExplain");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceExplainBetween(String value1, String value2) {
            addCriterion("land_purchase_price_explain between", value1, value2, "landPurchasePriceExplain");
            return (Criteria) this;
        }

        public Criteria andLandPurchasePriceExplainNotBetween(String value1, String value2) {
            addCriterion("land_purchase_price_explain not between", value1, value2, "landPurchasePriceExplain");
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

        public Criteria andConstructionAssessmentValue2IsNull() {
            addCriterion("construction_assessment_value2 is null");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValue2IsNotNull() {
            addCriterion("construction_assessment_value2 is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValue2EqualTo(BigDecimal value) {
            addCriterion("construction_assessment_value2 =", value, "constructionAssessmentValue2");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValue2NotEqualTo(BigDecimal value) {
            addCriterion("construction_assessment_value2 <>", value, "constructionAssessmentValue2");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValue2GreaterThan(BigDecimal value) {
            addCriterion("construction_assessment_value2 >", value, "constructionAssessmentValue2");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValue2GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("construction_assessment_value2 >=", value, "constructionAssessmentValue2");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValue2LessThan(BigDecimal value) {
            addCriterion("construction_assessment_value2 <", value, "constructionAssessmentValue2");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValue2LessThanOrEqualTo(BigDecimal value) {
            addCriterion("construction_assessment_value2 <=", value, "constructionAssessmentValue2");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValue2In(List<BigDecimal> values) {
            addCriterion("construction_assessment_value2 in", values, "constructionAssessmentValue2");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValue2NotIn(List<BigDecimal> values) {
            addCriterion("construction_assessment_value2 not in", values, "constructionAssessmentValue2");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValue2Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("construction_assessment_value2 between", value1, value2, "constructionAssessmentValue2");
            return (Criteria) this;
        }

        public Criteria andConstructionAssessmentValue2NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("construction_assessment_value2 not between", value1, value2, "constructionAssessmentValue2");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionIsNull() {
            addCriterion("additional_cost_land_acquisition is null");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionIsNotNull() {
            addCriterion("additional_cost_land_acquisition is not null");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionEqualTo(BigDecimal value) {
            addCriterion("additional_cost_land_acquisition =", value, "additionalCostLandAcquisition");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionNotEqualTo(BigDecimal value) {
            addCriterion("additional_cost_land_acquisition <>", value, "additionalCostLandAcquisition");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionGreaterThan(BigDecimal value) {
            addCriterion("additional_cost_land_acquisition >", value, "additionalCostLandAcquisition");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("additional_cost_land_acquisition >=", value, "additionalCostLandAcquisition");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionLessThan(BigDecimal value) {
            addCriterion("additional_cost_land_acquisition <", value, "additionalCostLandAcquisition");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("additional_cost_land_acquisition <=", value, "additionalCostLandAcquisition");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionIn(List<BigDecimal> values) {
            addCriterion("additional_cost_land_acquisition in", values, "additionalCostLandAcquisition");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionNotIn(List<BigDecimal> values) {
            addCriterion("additional_cost_land_acquisition not in", values, "additionalCostLandAcquisition");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("additional_cost_land_acquisition between", value1, value2, "additionalCostLandAcquisition");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("additional_cost_land_acquisition not between", value1, value2, "additionalCostLandAcquisition");
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

        public Criteria andAdditionalCostLandAcquisitionExplainIsNull() {
            addCriterion("additional_cost_land_acquisition_explain is null");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionExplainIsNotNull() {
            addCriterion("additional_cost_land_acquisition_explain is not null");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionExplainEqualTo(String value) {
            addCriterion("additional_cost_land_acquisition_explain =", value, "additionalCostLandAcquisitionExplain");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionExplainNotEqualTo(String value) {
            addCriterion("additional_cost_land_acquisition_explain <>", value, "additionalCostLandAcquisitionExplain");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionExplainGreaterThan(String value) {
            addCriterion("additional_cost_land_acquisition_explain >", value, "additionalCostLandAcquisitionExplain");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionExplainGreaterThanOrEqualTo(String value) {
            addCriterion("additional_cost_land_acquisition_explain >=", value, "additionalCostLandAcquisitionExplain");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionExplainLessThan(String value) {
            addCriterion("additional_cost_land_acquisition_explain <", value, "additionalCostLandAcquisitionExplain");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionExplainLessThanOrEqualTo(String value) {
            addCriterion("additional_cost_land_acquisition_explain <=", value, "additionalCostLandAcquisitionExplain");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionExplainLike(String value) {
            addCriterion("additional_cost_land_acquisition_explain like", value, "additionalCostLandAcquisitionExplain");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionExplainNotLike(String value) {
            addCriterion("additional_cost_land_acquisition_explain not like", value, "additionalCostLandAcquisitionExplain");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionExplainIn(List<String> values) {
            addCriterion("additional_cost_land_acquisition_explain in", values, "additionalCostLandAcquisitionExplain");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionExplainNotIn(List<String> values) {
            addCriterion("additional_cost_land_acquisition_explain not in", values, "additionalCostLandAcquisitionExplain");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionExplainBetween(String value1, String value2) {
            addCriterion("additional_cost_land_acquisition_explain between", value1, value2, "additionalCostLandAcquisitionExplain");
            return (Criteria) this;
        }

        public Criteria andAdditionalCostLandAcquisitionExplainNotBetween(String value1, String value2) {
            addCriterion("additional_cost_land_acquisition_explain not between", value1, value2, "additionalCostLandAcquisitionExplain");
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

        public Criteria andResidueRatioIdIsNull() {
            addCriterion("residue_ratio_id is null");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdIsNotNull() {
            addCriterion("residue_ratio_id is not null");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdEqualTo(Integer value) {
            addCriterion("residue_ratio_id =", value, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdNotEqualTo(Integer value) {
            addCriterion("residue_ratio_id <>", value, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdGreaterThan(Integer value) {
            addCriterion("residue_ratio_id >", value, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("residue_ratio_id >=", value, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdLessThan(Integer value) {
            addCriterion("residue_ratio_id <", value, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdLessThanOrEqualTo(Integer value) {
            addCriterion("residue_ratio_id <=", value, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdIn(List<Integer> values) {
            addCriterion("residue_ratio_id in", values, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdNotIn(List<Integer> values) {
            addCriterion("residue_ratio_id not in", values, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdBetween(Integer value1, Integer value2) {
            addCriterion("residue_ratio_id between", value1, value2, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdNotBetween(Integer value1, Integer value2) {
            addCriterion("residue_ratio_id not between", value1, value2, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioRemarkIsNull() {
            addCriterion("residue_ratio_remark is null");
            return (Criteria) this;
        }

        public Criteria andResidueRatioRemarkIsNotNull() {
            addCriterion("residue_ratio_remark is not null");
            return (Criteria) this;
        }

        public Criteria andResidueRatioRemarkEqualTo(String value) {
            addCriterion("residue_ratio_remark =", value, "residueRatioRemark");
            return (Criteria) this;
        }

        public Criteria andResidueRatioRemarkNotEqualTo(String value) {
            addCriterion("residue_ratio_remark <>", value, "residueRatioRemark");
            return (Criteria) this;
        }

        public Criteria andResidueRatioRemarkGreaterThan(String value) {
            addCriterion("residue_ratio_remark >", value, "residueRatioRemark");
            return (Criteria) this;
        }

        public Criteria andResidueRatioRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("residue_ratio_remark >=", value, "residueRatioRemark");
            return (Criteria) this;
        }

        public Criteria andResidueRatioRemarkLessThan(String value) {
            addCriterion("residue_ratio_remark <", value, "residueRatioRemark");
            return (Criteria) this;
        }

        public Criteria andResidueRatioRemarkLessThanOrEqualTo(String value) {
            addCriterion("residue_ratio_remark <=", value, "residueRatioRemark");
            return (Criteria) this;
        }

        public Criteria andResidueRatioRemarkLike(String value) {
            addCriterion("residue_ratio_remark like", value, "residueRatioRemark");
            return (Criteria) this;
        }

        public Criteria andResidueRatioRemarkNotLike(String value) {
            addCriterion("residue_ratio_remark not like", value, "residueRatioRemark");
            return (Criteria) this;
        }

        public Criteria andResidueRatioRemarkIn(List<String> values) {
            addCriterion("residue_ratio_remark in", values, "residueRatioRemark");
            return (Criteria) this;
        }

        public Criteria andResidueRatioRemarkNotIn(List<String> values) {
            addCriterion("residue_ratio_remark not in", values, "residueRatioRemark");
            return (Criteria) this;
        }

        public Criteria andResidueRatioRemarkBetween(String value1, String value2) {
            addCriterion("residue_ratio_remark between", value1, value2, "residueRatioRemark");
            return (Criteria) this;
        }

        public Criteria andResidueRatioRemarkNotBetween(String value1, String value2) {
            addCriterion("residue_ratio_remark not between", value1, value2, "residueRatioRemark");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIsNull() {
            addCriterion("residue_ratio is null");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIsNotNull() {
            addCriterion("residue_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andResidueRatioEqualTo(BigDecimal value) {
            addCriterion("residue_ratio =", value, "residueRatio");
            return (Criteria) this;
        }

        public Criteria andResidueRatioNotEqualTo(BigDecimal value) {
            addCriterion("residue_ratio <>", value, "residueRatio");
            return (Criteria) this;
        }

        public Criteria andResidueRatioGreaterThan(BigDecimal value) {
            addCriterion("residue_ratio >", value, "residueRatio");
            return (Criteria) this;
        }

        public Criteria andResidueRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("residue_ratio >=", value, "residueRatio");
            return (Criteria) this;
        }

        public Criteria andResidueRatioLessThan(BigDecimal value) {
            addCriterion("residue_ratio <", value, "residueRatio");
            return (Criteria) this;
        }

        public Criteria andResidueRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("residue_ratio <=", value, "residueRatio");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIn(List<BigDecimal> values) {
            addCriterion("residue_ratio in", values, "residueRatio");
            return (Criteria) this;
        }

        public Criteria andResidueRatioNotIn(List<BigDecimal> values) {
            addCriterion("residue_ratio not in", values, "residueRatio");
            return (Criteria) this;
        }

        public Criteria andResidueRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("residue_ratio between", value1, value2, "residueRatio");
            return (Criteria) this;
        }

        public Criteria andResidueRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("residue_ratio not between", value1, value2, "residueRatio");
            return (Criteria) this;
        }

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
            addCriterion("pid not between", value1, value2, "pid");
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
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * tb_md_cost_construction
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