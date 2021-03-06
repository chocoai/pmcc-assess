package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdMarketCompareResultExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MdMarketCompareResultExample() {
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

        public Criteria andMcItemIdIsNull() {
            addCriterion("mc_item_id is null");
            return (Criteria) this;
        }

        public Criteria andMcItemIdIsNotNull() {
            addCriterion("mc_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andMcItemIdEqualTo(Integer value) {
            addCriterion("mc_item_id =", value, "mcItemId");
            return (Criteria) this;
        }

        public Criteria andMcItemIdNotEqualTo(Integer value) {
            addCriterion("mc_item_id <>", value, "mcItemId");
            return (Criteria) this;
        }

        public Criteria andMcItemIdGreaterThan(Integer value) {
            addCriterion("mc_item_id >", value, "mcItemId");
            return (Criteria) this;
        }

        public Criteria andMcItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mc_item_id >=", value, "mcItemId");
            return (Criteria) this;
        }

        public Criteria andMcItemIdLessThan(Integer value) {
            addCriterion("mc_item_id <", value, "mcItemId");
            return (Criteria) this;
        }

        public Criteria andMcItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("mc_item_id <=", value, "mcItemId");
            return (Criteria) this;
        }

        public Criteria andMcItemIdIn(List<Integer> values) {
            addCriterion("mc_item_id in", values, "mcItemId");
            return (Criteria) this;
        }

        public Criteria andMcItemIdNotIn(List<Integer> values) {
            addCriterion("mc_item_id not in", values, "mcItemId");
            return (Criteria) this;
        }

        public Criteria andMcItemIdBetween(Integer value1, Integer value2) {
            addCriterion("mc_item_id between", value1, value2, "mcItemId");
            return (Criteria) this;
        }

        public Criteria andMcItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mc_item_id not between", value1, value2, "mcItemId");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceIsNull() {
            addCriterion("specific_price is null");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceIsNotNull() {
            addCriterion("specific_price is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceEqualTo(String value) {
            addCriterion("specific_price =", value, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceNotEqualTo(String value) {
            addCriterion("specific_price <>", value, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceGreaterThan(String value) {
            addCriterion("specific_price >", value, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceGreaterThanOrEqualTo(String value) {
            addCriterion("specific_price >=", value, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceLessThan(String value) {
            addCriterion("specific_price <", value, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceLessThanOrEqualTo(String value) {
            addCriterion("specific_price <=", value, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceLike(String value) {
            addCriterion("specific_price like", value, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceNotLike(String value) {
            addCriterion("specific_price not like", value, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceIn(List<String> values) {
            addCriterion("specific_price in", values, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceNotIn(List<String> values) {
            addCriterion("specific_price not in", values, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceBetween(String value1, String value2) {
            addCriterion("specific_price between", value1, value2, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceNotBetween(String value1, String value2) {
            addCriterion("specific_price not between", value1, value2, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceIsNull() {
            addCriterion("correction_difference is null");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceIsNotNull() {
            addCriterion("correction_difference is not null");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceEqualTo(String value) {
            addCriterion("correction_difference =", value, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceNotEqualTo(String value) {
            addCriterion("correction_difference <>", value, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceGreaterThan(String value) {
            addCriterion("correction_difference >", value, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceGreaterThanOrEqualTo(String value) {
            addCriterion("correction_difference >=", value, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceLessThan(String value) {
            addCriterion("correction_difference <", value, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceLessThanOrEqualTo(String value) {
            addCriterion("correction_difference <=", value, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceLike(String value) {
            addCriterion("correction_difference like", value, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceNotLike(String value) {
            addCriterion("correction_difference not like", value, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceIn(List<String> values) {
            addCriterion("correction_difference in", values, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceNotIn(List<String> values) {
            addCriterion("correction_difference not in", values, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceBetween(String value1, String value2) {
            addCriterion("correction_difference between", value1, value2, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceNotBetween(String value1, String value2) {
            addCriterion("correction_difference not between", value1, value2, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceIsNull() {
            addCriterion("case_difference is null");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceIsNotNull() {
            addCriterion("case_difference is not null");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceEqualTo(String value) {
            addCriterion("case_difference =", value, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceNotEqualTo(String value) {
            addCriterion("case_difference <>", value, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceGreaterThan(String value) {
            addCriterion("case_difference >", value, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceGreaterThanOrEqualTo(String value) {
            addCriterion("case_difference >=", value, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceLessThan(String value) {
            addCriterion("case_difference <", value, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceLessThanOrEqualTo(String value) {
            addCriterion("case_difference <=", value, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceLike(String value) {
            addCriterion("case_difference like", value, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceNotLike(String value) {
            addCriterion("case_difference not like", value, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceIn(List<String> values) {
            addCriterion("case_difference in", values, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceNotIn(List<String> values) {
            addCriterion("case_difference not in", values, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceBetween(String value1, String value2) {
            addCriterion("case_difference between", value1, value2, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceNotBetween(String value1, String value2) {
            addCriterion("case_difference not between", value1, value2, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(BigDecimal value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(BigDecimal value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(BigDecimal value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(BigDecimal value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<BigDecimal> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<BigDecimal> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionIsNull() {
            addCriterion("weight_description is null");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionIsNotNull() {
            addCriterion("weight_description is not null");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionEqualTo(String value) {
            addCriterion("weight_description =", value, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionNotEqualTo(String value) {
            addCriterion("weight_description <>", value, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionGreaterThan(String value) {
            addCriterion("weight_description >", value, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("weight_description >=", value, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionLessThan(String value) {
            addCriterion("weight_description <", value, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionLessThanOrEqualTo(String value) {
            addCriterion("weight_description <=", value, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionLike(String value) {
            addCriterion("weight_description like", value, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionNotLike(String value) {
            addCriterion("weight_description not like", value, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionIn(List<String> values) {
            addCriterion("weight_description in", values, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionNotIn(List<String> values) {
            addCriterion("weight_description not in", values, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionBetween(String value1, String value2) {
            addCriterion("weight_description between", value1, value2, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionNotBetween(String value1, String value2) {
            addCriterion("weight_description not between", value1, value2, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andAveragePriceIsNull() {
            addCriterion("average_price is null");
            return (Criteria) this;
        }

        public Criteria andAveragePriceIsNotNull() {
            addCriterion("average_price is not null");
            return (Criteria) this;
        }

        public Criteria andAveragePriceEqualTo(String value) {
            addCriterion("average_price =", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceNotEqualTo(String value) {
            addCriterion("average_price <>", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceGreaterThan(String value) {
            addCriterion("average_price >", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceGreaterThanOrEqualTo(String value) {
            addCriterion("average_price >=", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceLessThan(String value) {
            addCriterion("average_price <", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceLessThanOrEqualTo(String value) {
            addCriterion("average_price <=", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceLike(String value) {
            addCriterion("average_price like", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceNotLike(String value) {
            addCriterion("average_price not like", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceIn(List<String> values) {
            addCriterion("average_price in", values, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceNotIn(List<String> values) {
            addCriterion("average_price not in", values, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceBetween(String value1, String value2) {
            addCriterion("average_price between", value1, value2, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceNotBetween(String value1, String value2) {
            addCriterion("average_price not between", value1, value2, "averagePrice");
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