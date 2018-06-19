package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsrCalculationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CsrCalculationExample() {
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

        public Criteria andCsrProjectIdIsNull() {
            addCriterion("csr_project_id is null");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdIsNotNull() {
            addCriterion("csr_project_id is not null");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdEqualTo(Integer value) {
            addCriterion("csr_project_id =", value, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdNotEqualTo(Integer value) {
            addCriterion("csr_project_id <>", value, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdGreaterThan(Integer value) {
            addCriterion("csr_project_id >", value, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("csr_project_id >=", value, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdLessThan(Integer value) {
            addCriterion("csr_project_id <", value, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("csr_project_id <=", value, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdIn(List<Integer> values) {
            addCriterion("csr_project_id in", values, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdNotIn(List<Integer> values) {
            addCriterion("csr_project_id not in", values, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("csr_project_id between", value1, value2, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("csr_project_id not between", value1, value2, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdIsNull() {
            addCriterion("borrower_id is null");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdIsNotNull() {
            addCriterion("borrower_id is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdEqualTo(String value) {
            addCriterion("borrower_id =", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdNotEqualTo(String value) {
            addCriterion("borrower_id <>", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdGreaterThan(String value) {
            addCriterion("borrower_id >", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdGreaterThanOrEqualTo(String value) {
            addCriterion("borrower_id >=", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdLessThan(String value) {
            addCriterion("borrower_id <", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdLessThanOrEqualTo(String value) {
            addCriterion("borrower_id <=", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdLike(String value) {
            addCriterion("borrower_id like", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdNotLike(String value) {
            addCriterion("borrower_id not like", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdIn(List<String> values) {
            addCriterion("borrower_id in", values, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdNotIn(List<String> values) {
            addCriterion("borrower_id not in", values, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdBetween(String value1, String value2) {
            addCriterion("borrower_id between", value1, value2, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdNotBetween(String value1, String value2) {
            addCriterion("borrower_id not between", value1, value2, "borrowerId");
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

        public Criteria andAppraisalMethodIsNull() {
            addCriterion("appraisal_method is null");
            return (Criteria) this;
        }

        public Criteria andAppraisalMethodIsNotNull() {
            addCriterion("appraisal_method is not null");
            return (Criteria) this;
        }

        public Criteria andAppraisalMethodEqualTo(String value) {
            addCriterion("appraisal_method =", value, "appraisalMethod");
            return (Criteria) this;
        }

        public Criteria andAppraisalMethodNotEqualTo(String value) {
            addCriterion("appraisal_method <>", value, "appraisalMethod");
            return (Criteria) this;
        }

        public Criteria andAppraisalMethodGreaterThan(String value) {
            addCriterion("appraisal_method >", value, "appraisalMethod");
            return (Criteria) this;
        }

        public Criteria andAppraisalMethodGreaterThanOrEqualTo(String value) {
            addCriterion("appraisal_method >=", value, "appraisalMethod");
            return (Criteria) this;
        }

        public Criteria andAppraisalMethodLessThan(String value) {
            addCriterion("appraisal_method <", value, "appraisalMethod");
            return (Criteria) this;
        }

        public Criteria andAppraisalMethodLessThanOrEqualTo(String value) {
            addCriterion("appraisal_method <=", value, "appraisalMethod");
            return (Criteria) this;
        }

        public Criteria andAppraisalMethodLike(String value) {
            addCriterion("appraisal_method like", value, "appraisalMethod");
            return (Criteria) this;
        }

        public Criteria andAppraisalMethodNotLike(String value) {
            addCriterion("appraisal_method not like", value, "appraisalMethod");
            return (Criteria) this;
        }

        public Criteria andAppraisalMethodIn(List<String> values) {
            addCriterion("appraisal_method in", values, "appraisalMethod");
            return (Criteria) this;
        }

        public Criteria andAppraisalMethodNotIn(List<String> values) {
            addCriterion("appraisal_method not in", values, "appraisalMethod");
            return (Criteria) this;
        }

        public Criteria andAppraisalMethodBetween(String value1, String value2) {
            addCriterion("appraisal_method between", value1, value2, "appraisalMethod");
            return (Criteria) this;
        }

        public Criteria andAppraisalMethodNotBetween(String value1, String value2) {
            addCriterion("appraisal_method not between", value1, value2, "appraisalMethod");
            return (Criteria) this;
        }

        public Criteria andPawnAreaIsNull() {
            addCriterion("pawn_area is null");
            return (Criteria) this;
        }

        public Criteria andPawnAreaIsNotNull() {
            addCriterion("pawn_area is not null");
            return (Criteria) this;
        }

        public Criteria andPawnAreaEqualTo(String value) {
            addCriterion("pawn_area =", value, "pawnArea");
            return (Criteria) this;
        }

        public Criteria andPawnAreaNotEqualTo(String value) {
            addCriterion("pawn_area <>", value, "pawnArea");
            return (Criteria) this;
        }

        public Criteria andPawnAreaGreaterThan(String value) {
            addCriterion("pawn_area >", value, "pawnArea");
            return (Criteria) this;
        }

        public Criteria andPawnAreaGreaterThanOrEqualTo(String value) {
            addCriterion("pawn_area >=", value, "pawnArea");
            return (Criteria) this;
        }

        public Criteria andPawnAreaLessThan(String value) {
            addCriterion("pawn_area <", value, "pawnArea");
            return (Criteria) this;
        }

        public Criteria andPawnAreaLessThanOrEqualTo(String value) {
            addCriterion("pawn_area <=", value, "pawnArea");
            return (Criteria) this;
        }

        public Criteria andPawnAreaLike(String value) {
            addCriterion("pawn_area like", value, "pawnArea");
            return (Criteria) this;
        }

        public Criteria andPawnAreaNotLike(String value) {
            addCriterion("pawn_area not like", value, "pawnArea");
            return (Criteria) this;
        }

        public Criteria andPawnAreaIn(List<String> values) {
            addCriterion("pawn_area in", values, "pawnArea");
            return (Criteria) this;
        }

        public Criteria andPawnAreaNotIn(List<String> values) {
            addCriterion("pawn_area not in", values, "pawnArea");
            return (Criteria) this;
        }

        public Criteria andPawnAreaBetween(String value1, String value2) {
            addCriterion("pawn_area between", value1, value2, "pawnArea");
            return (Criteria) this;
        }

        public Criteria andPawnAreaNotBetween(String value1, String value2) {
            addCriterion("pawn_area not between", value1, value2, "pawnArea");
            return (Criteria) this;
        }

        public Criteria andPawnPriceIsNull() {
            addCriterion("pawn_price is null");
            return (Criteria) this;
        }

        public Criteria andPawnPriceIsNotNull() {
            addCriterion("pawn_price is not null");
            return (Criteria) this;
        }

        public Criteria andPawnPriceEqualTo(String value) {
            addCriterion("pawn_price =", value, "pawnPrice");
            return (Criteria) this;
        }

        public Criteria andPawnPriceNotEqualTo(String value) {
            addCriterion("pawn_price <>", value, "pawnPrice");
            return (Criteria) this;
        }

        public Criteria andPawnPriceGreaterThan(String value) {
            addCriterion("pawn_price >", value, "pawnPrice");
            return (Criteria) this;
        }

        public Criteria andPawnPriceGreaterThanOrEqualTo(String value) {
            addCriterion("pawn_price >=", value, "pawnPrice");
            return (Criteria) this;
        }

        public Criteria andPawnPriceLessThan(String value) {
            addCriterion("pawn_price <", value, "pawnPrice");
            return (Criteria) this;
        }

        public Criteria andPawnPriceLessThanOrEqualTo(String value) {
            addCriterion("pawn_price <=", value, "pawnPrice");
            return (Criteria) this;
        }

        public Criteria andPawnPriceLike(String value) {
            addCriterion("pawn_price like", value, "pawnPrice");
            return (Criteria) this;
        }

        public Criteria andPawnPriceNotLike(String value) {
            addCriterion("pawn_price not like", value, "pawnPrice");
            return (Criteria) this;
        }

        public Criteria andPawnPriceIn(List<String> values) {
            addCriterion("pawn_price in", values, "pawnPrice");
            return (Criteria) this;
        }

        public Criteria andPawnPriceNotIn(List<String> values) {
            addCriterion("pawn_price not in", values, "pawnPrice");
            return (Criteria) this;
        }

        public Criteria andPawnPriceBetween(String value1, String value2) {
            addCriterion("pawn_price between", value1, value2, "pawnPrice");
            return (Criteria) this;
        }

        public Criteria andPawnPriceNotBetween(String value1, String value2) {
            addCriterion("pawn_price not between", value1, value2, "pawnPrice");
            return (Criteria) this;
        }

        public Criteria andPawnAmountIsNull() {
            addCriterion("pawn_amount is null");
            return (Criteria) this;
        }

        public Criteria andPawnAmountIsNotNull() {
            addCriterion("pawn_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPawnAmountEqualTo(String value) {
            addCriterion("pawn_amount =", value, "pawnAmount");
            return (Criteria) this;
        }

        public Criteria andPawnAmountNotEqualTo(String value) {
            addCriterion("pawn_amount <>", value, "pawnAmount");
            return (Criteria) this;
        }

        public Criteria andPawnAmountGreaterThan(String value) {
            addCriterion("pawn_amount >", value, "pawnAmount");
            return (Criteria) this;
        }

        public Criteria andPawnAmountGreaterThanOrEqualTo(String value) {
            addCriterion("pawn_amount >=", value, "pawnAmount");
            return (Criteria) this;
        }

        public Criteria andPawnAmountLessThan(String value) {
            addCriterion("pawn_amount <", value, "pawnAmount");
            return (Criteria) this;
        }

        public Criteria andPawnAmountLessThanOrEqualTo(String value) {
            addCriterion("pawn_amount <=", value, "pawnAmount");
            return (Criteria) this;
        }

        public Criteria andPawnAmountLike(String value) {
            addCriterion("pawn_amount like", value, "pawnAmount");
            return (Criteria) this;
        }

        public Criteria andPawnAmountNotLike(String value) {
            addCriterion("pawn_amount not like", value, "pawnAmount");
            return (Criteria) this;
        }

        public Criteria andPawnAmountIn(List<String> values) {
            addCriterion("pawn_amount in", values, "pawnAmount");
            return (Criteria) this;
        }

        public Criteria andPawnAmountNotIn(List<String> values) {
            addCriterion("pawn_amount not in", values, "pawnAmount");
            return (Criteria) this;
        }

        public Criteria andPawnAmountBetween(String value1, String value2) {
            addCriterion("pawn_amount between", value1, value2, "pawnAmount");
            return (Criteria) this;
        }

        public Criteria andPawnAmountNotBetween(String value1, String value2) {
            addCriterion("pawn_amount not between", value1, value2, "pawnAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationCoefficientIsNull() {
            addCriterion("pawn_realization_coefficient is null");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationCoefficientIsNotNull() {
            addCriterion("pawn_realization_coefficient is not null");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationCoefficientEqualTo(String value) {
            addCriterion("pawn_realization_coefficient =", value, "pawnRealizationCoefficient");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationCoefficientNotEqualTo(String value) {
            addCriterion("pawn_realization_coefficient <>", value, "pawnRealizationCoefficient");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationCoefficientGreaterThan(String value) {
            addCriterion("pawn_realization_coefficient >", value, "pawnRealizationCoefficient");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationCoefficientGreaterThanOrEqualTo(String value) {
            addCriterion("pawn_realization_coefficient >=", value, "pawnRealizationCoefficient");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationCoefficientLessThan(String value) {
            addCriterion("pawn_realization_coefficient <", value, "pawnRealizationCoefficient");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationCoefficientLessThanOrEqualTo(String value) {
            addCriterion("pawn_realization_coefficient <=", value, "pawnRealizationCoefficient");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationCoefficientLike(String value) {
            addCriterion("pawn_realization_coefficient like", value, "pawnRealizationCoefficient");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationCoefficientNotLike(String value) {
            addCriterion("pawn_realization_coefficient not like", value, "pawnRealizationCoefficient");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationCoefficientIn(List<String> values) {
            addCriterion("pawn_realization_coefficient in", values, "pawnRealizationCoefficient");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationCoefficientNotIn(List<String> values) {
            addCriterion("pawn_realization_coefficient not in", values, "pawnRealizationCoefficient");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationCoefficientBetween(String value1, String value2) {
            addCriterion("pawn_realization_coefficient between", value1, value2, "pawnRealizationCoefficient");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationCoefficientNotBetween(String value1, String value2) {
            addCriterion("pawn_realization_coefficient not between", value1, value2, "pawnRealizationCoefficient");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationIsNull() {
            addCriterion("pawn_realization is null");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationIsNotNull() {
            addCriterion("pawn_realization is not null");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationEqualTo(String value) {
            addCriterion("pawn_realization =", value, "pawnRealization");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationNotEqualTo(String value) {
            addCriterion("pawn_realization <>", value, "pawnRealization");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationGreaterThan(String value) {
            addCriterion("pawn_realization >", value, "pawnRealization");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationGreaterThanOrEqualTo(String value) {
            addCriterion("pawn_realization >=", value, "pawnRealization");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationLessThan(String value) {
            addCriterion("pawn_realization <", value, "pawnRealization");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationLessThanOrEqualTo(String value) {
            addCriterion("pawn_realization <=", value, "pawnRealization");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationLike(String value) {
            addCriterion("pawn_realization like", value, "pawnRealization");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationNotLike(String value) {
            addCriterion("pawn_realization not like", value, "pawnRealization");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationIn(List<String> values) {
            addCriterion("pawn_realization in", values, "pawnRealization");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationNotIn(List<String> values) {
            addCriterion("pawn_realization not in", values, "pawnRealization");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationBetween(String value1, String value2) {
            addCriterion("pawn_realization between", value1, value2, "pawnRealization");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationNotBetween(String value1, String value2) {
            addCriterion("pawn_realization not between", value1, value2, "pawnRealization");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationAmountIsNull() {
            addCriterion("pawn_realization_amount is null");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationAmountIsNotNull() {
            addCriterion("pawn_realization_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationAmountEqualTo(String value) {
            addCriterion("pawn_realization_amount =", value, "pawnRealizationAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationAmountNotEqualTo(String value) {
            addCriterion("pawn_realization_amount <>", value, "pawnRealizationAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationAmountGreaterThan(String value) {
            addCriterion("pawn_realization_amount >", value, "pawnRealizationAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationAmountGreaterThanOrEqualTo(String value) {
            addCriterion("pawn_realization_amount >=", value, "pawnRealizationAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationAmountLessThan(String value) {
            addCriterion("pawn_realization_amount <", value, "pawnRealizationAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationAmountLessThanOrEqualTo(String value) {
            addCriterion("pawn_realization_amount <=", value, "pawnRealizationAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationAmountLike(String value) {
            addCriterion("pawn_realization_amount like", value, "pawnRealizationAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationAmountNotLike(String value) {
            addCriterion("pawn_realization_amount not like", value, "pawnRealizationAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationAmountIn(List<String> values) {
            addCriterion("pawn_realization_amount in", values, "pawnRealizationAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationAmountNotIn(List<String> values) {
            addCriterion("pawn_realization_amount not in", values, "pawnRealizationAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationAmountBetween(String value1, String value2) {
            addCriterion("pawn_realization_amount between", value1, value2, "pawnRealizationAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationAmountNotBetween(String value1, String value2) {
            addCriterion("pawn_realization_amount not between", value1, value2, "pawnRealizationAmount");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionCoefficientIsNull() {
            addCriterion("dispose_auction_coefficient is null");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionCoefficientIsNotNull() {
            addCriterion("dispose_auction_coefficient is not null");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionCoefficientEqualTo(String value) {
            addCriterion("dispose_auction_coefficient =", value, "disposeAuctionCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionCoefficientNotEqualTo(String value) {
            addCriterion("dispose_auction_coefficient <>", value, "disposeAuctionCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionCoefficientGreaterThan(String value) {
            addCriterion("dispose_auction_coefficient >", value, "disposeAuctionCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionCoefficientGreaterThanOrEqualTo(String value) {
            addCriterion("dispose_auction_coefficient >=", value, "disposeAuctionCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionCoefficientLessThan(String value) {
            addCriterion("dispose_auction_coefficient <", value, "disposeAuctionCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionCoefficientLessThanOrEqualTo(String value) {
            addCriterion("dispose_auction_coefficient <=", value, "disposeAuctionCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionCoefficientLike(String value) {
            addCriterion("dispose_auction_coefficient like", value, "disposeAuctionCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionCoefficientNotLike(String value) {
            addCriterion("dispose_auction_coefficient not like", value, "disposeAuctionCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionCoefficientIn(List<String> values) {
            addCriterion("dispose_auction_coefficient in", values, "disposeAuctionCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionCoefficientNotIn(List<String> values) {
            addCriterion("dispose_auction_coefficient not in", values, "disposeAuctionCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionCoefficientBetween(String value1, String value2) {
            addCriterion("dispose_auction_coefficient between", value1, value2, "disposeAuctionCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionCoefficientNotBetween(String value1, String value2) {
            addCriterion("dispose_auction_coefficient not between", value1, value2, "disposeAuctionCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionIsNull() {
            addCriterion("dispose_auction is null");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionIsNotNull() {
            addCriterion("dispose_auction is not null");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionEqualTo(String value) {
            addCriterion("dispose_auction =", value, "disposeAuction");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionNotEqualTo(String value) {
            addCriterion("dispose_auction <>", value, "disposeAuction");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionGreaterThan(String value) {
            addCriterion("dispose_auction >", value, "disposeAuction");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionGreaterThanOrEqualTo(String value) {
            addCriterion("dispose_auction >=", value, "disposeAuction");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionLessThan(String value) {
            addCriterion("dispose_auction <", value, "disposeAuction");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionLessThanOrEqualTo(String value) {
            addCriterion("dispose_auction <=", value, "disposeAuction");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionLike(String value) {
            addCriterion("dispose_auction like", value, "disposeAuction");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionNotLike(String value) {
            addCriterion("dispose_auction not like", value, "disposeAuction");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionIn(List<String> values) {
            addCriterion("dispose_auction in", values, "disposeAuction");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionNotIn(List<String> values) {
            addCriterion("dispose_auction not in", values, "disposeAuction");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionBetween(String value1, String value2) {
            addCriterion("dispose_auction between", value1, value2, "disposeAuction");
            return (Criteria) this;
        }

        public Criteria andDisposeAuctionNotBetween(String value1, String value2) {
            addCriterion("dispose_auction not between", value1, value2, "disposeAuction");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitCoefficientIsNull() {
            addCriterion("dispose_lawsuit_coefficient is null");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitCoefficientIsNotNull() {
            addCriterion("dispose_lawsuit_coefficient is not null");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitCoefficientEqualTo(String value) {
            addCriterion("dispose_lawsuit_coefficient =", value, "disposeLawsuitCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitCoefficientNotEqualTo(String value) {
            addCriterion("dispose_lawsuit_coefficient <>", value, "disposeLawsuitCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitCoefficientGreaterThan(String value) {
            addCriterion("dispose_lawsuit_coefficient >", value, "disposeLawsuitCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitCoefficientGreaterThanOrEqualTo(String value) {
            addCriterion("dispose_lawsuit_coefficient >=", value, "disposeLawsuitCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitCoefficientLessThan(String value) {
            addCriterion("dispose_lawsuit_coefficient <", value, "disposeLawsuitCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitCoefficientLessThanOrEqualTo(String value) {
            addCriterion("dispose_lawsuit_coefficient <=", value, "disposeLawsuitCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitCoefficientLike(String value) {
            addCriterion("dispose_lawsuit_coefficient like", value, "disposeLawsuitCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitCoefficientNotLike(String value) {
            addCriterion("dispose_lawsuit_coefficient not like", value, "disposeLawsuitCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitCoefficientIn(List<String> values) {
            addCriterion("dispose_lawsuit_coefficient in", values, "disposeLawsuitCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitCoefficientNotIn(List<String> values) {
            addCriterion("dispose_lawsuit_coefficient not in", values, "disposeLawsuitCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitCoefficientBetween(String value1, String value2) {
            addCriterion("dispose_lawsuit_coefficient between", value1, value2, "disposeLawsuitCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitCoefficientNotBetween(String value1, String value2) {
            addCriterion("dispose_lawsuit_coefficient not between", value1, value2, "disposeLawsuitCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitIsNull() {
            addCriterion("dispose_lawsuit is null");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitIsNotNull() {
            addCriterion("dispose_lawsuit is not null");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitEqualTo(String value) {
            addCriterion("dispose_lawsuit =", value, "disposeLawsuit");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitNotEqualTo(String value) {
            addCriterion("dispose_lawsuit <>", value, "disposeLawsuit");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitGreaterThan(String value) {
            addCriterion("dispose_lawsuit >", value, "disposeLawsuit");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitGreaterThanOrEqualTo(String value) {
            addCriterion("dispose_lawsuit >=", value, "disposeLawsuit");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitLessThan(String value) {
            addCriterion("dispose_lawsuit <", value, "disposeLawsuit");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitLessThanOrEqualTo(String value) {
            addCriterion("dispose_lawsuit <=", value, "disposeLawsuit");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitLike(String value) {
            addCriterion("dispose_lawsuit like", value, "disposeLawsuit");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitNotLike(String value) {
            addCriterion("dispose_lawsuit not like", value, "disposeLawsuit");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitIn(List<String> values) {
            addCriterion("dispose_lawsuit in", values, "disposeLawsuit");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitNotIn(List<String> values) {
            addCriterion("dispose_lawsuit not in", values, "disposeLawsuit");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitBetween(String value1, String value2) {
            addCriterion("dispose_lawsuit between", value1, value2, "disposeLawsuit");
            return (Criteria) this;
        }

        public Criteria andDisposeLawsuitNotBetween(String value1, String value2) {
            addCriterion("dispose_lawsuit not between", value1, value2, "disposeLawsuit");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteCoefficientIsNull() {
            addCriterion("dispose_execute_coefficient is null");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteCoefficientIsNotNull() {
            addCriterion("dispose_execute_coefficient is not null");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteCoefficientEqualTo(String value) {
            addCriterion("dispose_execute_coefficient =", value, "disposeExecuteCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteCoefficientNotEqualTo(String value) {
            addCriterion("dispose_execute_coefficient <>", value, "disposeExecuteCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteCoefficientGreaterThan(String value) {
            addCriterion("dispose_execute_coefficient >", value, "disposeExecuteCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteCoefficientGreaterThanOrEqualTo(String value) {
            addCriterion("dispose_execute_coefficient >=", value, "disposeExecuteCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteCoefficientLessThan(String value) {
            addCriterion("dispose_execute_coefficient <", value, "disposeExecuteCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteCoefficientLessThanOrEqualTo(String value) {
            addCriterion("dispose_execute_coefficient <=", value, "disposeExecuteCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteCoefficientLike(String value) {
            addCriterion("dispose_execute_coefficient like", value, "disposeExecuteCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteCoefficientNotLike(String value) {
            addCriterion("dispose_execute_coefficient not like", value, "disposeExecuteCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteCoefficientIn(List<String> values) {
            addCriterion("dispose_execute_coefficient in", values, "disposeExecuteCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteCoefficientNotIn(List<String> values) {
            addCriterion("dispose_execute_coefficient not in", values, "disposeExecuteCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteCoefficientBetween(String value1, String value2) {
            addCriterion("dispose_execute_coefficient between", value1, value2, "disposeExecuteCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteCoefficientNotBetween(String value1, String value2) {
            addCriterion("dispose_execute_coefficient not between", value1, value2, "disposeExecuteCoefficient");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteIsNull() {
            addCriterion("dispose_execute is null");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteIsNotNull() {
            addCriterion("dispose_execute is not null");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteEqualTo(String value) {
            addCriterion("dispose_execute =", value, "disposeExecute");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteNotEqualTo(String value) {
            addCriterion("dispose_execute <>", value, "disposeExecute");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteGreaterThan(String value) {
            addCriterion("dispose_execute >", value, "disposeExecute");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteGreaterThanOrEqualTo(String value) {
            addCriterion("dispose_execute >=", value, "disposeExecute");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteLessThan(String value) {
            addCriterion("dispose_execute <", value, "disposeExecute");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteLessThanOrEqualTo(String value) {
            addCriterion("dispose_execute <=", value, "disposeExecute");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteLike(String value) {
            addCriterion("dispose_execute like", value, "disposeExecute");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteNotLike(String value) {
            addCriterion("dispose_execute not like", value, "disposeExecute");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteIn(List<String> values) {
            addCriterion("dispose_execute in", values, "disposeExecute");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteNotIn(List<String> values) {
            addCriterion("dispose_execute not in", values, "disposeExecute");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteBetween(String value1, String value2) {
            addCriterion("dispose_execute between", value1, value2, "disposeExecute");
            return (Criteria) this;
        }

        public Criteria andDisposeExecuteNotBetween(String value1, String value2) {
            addCriterion("dispose_execute not between", value1, value2, "disposeExecute");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateCofficientIsNull() {
            addCriterion("dispose_authenticate_cofficient is null");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateCofficientIsNotNull() {
            addCriterion("dispose_authenticate_cofficient is not null");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateCofficientEqualTo(String value) {
            addCriterion("dispose_authenticate_cofficient =", value, "disposeAuthenticateCofficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateCofficientNotEqualTo(String value) {
            addCriterion("dispose_authenticate_cofficient <>", value, "disposeAuthenticateCofficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateCofficientGreaterThan(String value) {
            addCriterion("dispose_authenticate_cofficient >", value, "disposeAuthenticateCofficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateCofficientGreaterThanOrEqualTo(String value) {
            addCriterion("dispose_authenticate_cofficient >=", value, "disposeAuthenticateCofficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateCofficientLessThan(String value) {
            addCriterion("dispose_authenticate_cofficient <", value, "disposeAuthenticateCofficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateCofficientLessThanOrEqualTo(String value) {
            addCriterion("dispose_authenticate_cofficient <=", value, "disposeAuthenticateCofficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateCofficientLike(String value) {
            addCriterion("dispose_authenticate_cofficient like", value, "disposeAuthenticateCofficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateCofficientNotLike(String value) {
            addCriterion("dispose_authenticate_cofficient not like", value, "disposeAuthenticateCofficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateCofficientIn(List<String> values) {
            addCriterion("dispose_authenticate_cofficient in", values, "disposeAuthenticateCofficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateCofficientNotIn(List<String> values) {
            addCriterion("dispose_authenticate_cofficient not in", values, "disposeAuthenticateCofficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateCofficientBetween(String value1, String value2) {
            addCriterion("dispose_authenticate_cofficient between", value1, value2, "disposeAuthenticateCofficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateCofficientNotBetween(String value1, String value2) {
            addCriterion("dispose_authenticate_cofficient not between", value1, value2, "disposeAuthenticateCofficient");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateIsNull() {
            addCriterion("dispose_authenticate is null");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateIsNotNull() {
            addCriterion("dispose_authenticate is not null");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateEqualTo(String value) {
            addCriterion("dispose_authenticate =", value, "disposeAuthenticate");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateNotEqualTo(String value) {
            addCriterion("dispose_authenticate <>", value, "disposeAuthenticate");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateGreaterThan(String value) {
            addCriterion("dispose_authenticate >", value, "disposeAuthenticate");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateGreaterThanOrEqualTo(String value) {
            addCriterion("dispose_authenticate >=", value, "disposeAuthenticate");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateLessThan(String value) {
            addCriterion("dispose_authenticate <", value, "disposeAuthenticate");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateLessThanOrEqualTo(String value) {
            addCriterion("dispose_authenticate <=", value, "disposeAuthenticate");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateLike(String value) {
            addCriterion("dispose_authenticate like", value, "disposeAuthenticate");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateNotLike(String value) {
            addCriterion("dispose_authenticate not like", value, "disposeAuthenticate");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateIn(List<String> values) {
            addCriterion("dispose_authenticate in", values, "disposeAuthenticate");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateNotIn(List<String> values) {
            addCriterion("dispose_authenticate not in", values, "disposeAuthenticate");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateBetween(String value1, String value2) {
            addCriterion("dispose_authenticate between", value1, value2, "disposeAuthenticate");
            return (Criteria) this;
        }

        public Criteria andDisposeAuthenticateNotBetween(String value1, String value2) {
            addCriterion("dispose_authenticate not between", value1, value2, "disposeAuthenticate");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherCofficientIsNull() {
            addCriterion("dispoet_other_cofficient is null");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherCofficientIsNotNull() {
            addCriterion("dispoet_other_cofficient is not null");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherCofficientEqualTo(String value) {
            addCriterion("dispoet_other_cofficient =", value, "dispoetOtherCofficient");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherCofficientNotEqualTo(String value) {
            addCriterion("dispoet_other_cofficient <>", value, "dispoetOtherCofficient");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherCofficientGreaterThan(String value) {
            addCriterion("dispoet_other_cofficient >", value, "dispoetOtherCofficient");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherCofficientGreaterThanOrEqualTo(String value) {
            addCriterion("dispoet_other_cofficient >=", value, "dispoetOtherCofficient");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherCofficientLessThan(String value) {
            addCriterion("dispoet_other_cofficient <", value, "dispoetOtherCofficient");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherCofficientLessThanOrEqualTo(String value) {
            addCriterion("dispoet_other_cofficient <=", value, "dispoetOtherCofficient");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherCofficientLike(String value) {
            addCriterion("dispoet_other_cofficient like", value, "dispoetOtherCofficient");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherCofficientNotLike(String value) {
            addCriterion("dispoet_other_cofficient not like", value, "dispoetOtherCofficient");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherCofficientIn(List<String> values) {
            addCriterion("dispoet_other_cofficient in", values, "dispoetOtherCofficient");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherCofficientNotIn(List<String> values) {
            addCriterion("dispoet_other_cofficient not in", values, "dispoetOtherCofficient");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherCofficientBetween(String value1, String value2) {
            addCriterion("dispoet_other_cofficient between", value1, value2, "dispoetOtherCofficient");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherCofficientNotBetween(String value1, String value2) {
            addCriterion("dispoet_other_cofficient not between", value1, value2, "dispoetOtherCofficient");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherIsNull() {
            addCriterion("dispoet_other is null");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherIsNotNull() {
            addCriterion("dispoet_other is not null");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherEqualTo(String value) {
            addCriterion("dispoet_other =", value, "dispoetOther");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherNotEqualTo(String value) {
            addCriterion("dispoet_other <>", value, "dispoetOther");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherGreaterThan(String value) {
            addCriterion("dispoet_other >", value, "dispoetOther");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherGreaterThanOrEqualTo(String value) {
            addCriterion("dispoet_other >=", value, "dispoetOther");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherLessThan(String value) {
            addCriterion("dispoet_other <", value, "dispoetOther");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherLessThanOrEqualTo(String value) {
            addCriterion("dispoet_other <=", value, "dispoetOther");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherLike(String value) {
            addCriterion("dispoet_other like", value, "dispoetOther");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherNotLike(String value) {
            addCriterion("dispoet_other not like", value, "dispoetOther");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherIn(List<String> values) {
            addCriterion("dispoet_other in", values, "dispoetOther");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherNotIn(List<String> values) {
            addCriterion("dispoet_other not in", values, "dispoetOther");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherBetween(String value1, String value2) {
            addCriterion("dispoet_other between", value1, value2, "dispoetOther");
            return (Criteria) this;
        }

        public Criteria andDispoetOtherNotBetween(String value1, String value2) {
            addCriterion("dispoet_other not between", value1, value2, "dispoetOther");
            return (Criteria) this;
        }

        public Criteria andDispoetAmountIsNull() {
            addCriterion("dispoet_amount is null");
            return (Criteria) this;
        }

        public Criteria andDispoetAmountIsNotNull() {
            addCriterion("dispoet_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDispoetAmountEqualTo(String value) {
            addCriterion("dispoet_amount =", value, "dispoetAmount");
            return (Criteria) this;
        }

        public Criteria andDispoetAmountNotEqualTo(String value) {
            addCriterion("dispoet_amount <>", value, "dispoetAmount");
            return (Criteria) this;
        }

        public Criteria andDispoetAmountGreaterThan(String value) {
            addCriterion("dispoet_amount >", value, "dispoetAmount");
            return (Criteria) this;
        }

        public Criteria andDispoetAmountGreaterThanOrEqualTo(String value) {
            addCriterion("dispoet_amount >=", value, "dispoetAmount");
            return (Criteria) this;
        }

        public Criteria andDispoetAmountLessThan(String value) {
            addCriterion("dispoet_amount <", value, "dispoetAmount");
            return (Criteria) this;
        }

        public Criteria andDispoetAmountLessThanOrEqualTo(String value) {
            addCriterion("dispoet_amount <=", value, "dispoetAmount");
            return (Criteria) this;
        }

        public Criteria andDispoetAmountLike(String value) {
            addCriterion("dispoet_amount like", value, "dispoetAmount");
            return (Criteria) this;
        }

        public Criteria andDispoetAmountNotLike(String value) {
            addCriterion("dispoet_amount not like", value, "dispoetAmount");
            return (Criteria) this;
        }

        public Criteria andDispoetAmountIn(List<String> values) {
            addCriterion("dispoet_amount in", values, "dispoetAmount");
            return (Criteria) this;
        }

        public Criteria andDispoetAmountNotIn(List<String> values) {
            addCriterion("dispoet_amount not in", values, "dispoetAmount");
            return (Criteria) this;
        }

        public Criteria andDispoetAmountBetween(String value1, String value2) {
            addCriterion("dispoet_amount between", value1, value2, "dispoetAmount");
            return (Criteria) this;
        }

        public Criteria andDispoetAmountNotBetween(String value1, String value2) {
            addCriterion("dispoet_amount not between", value1, value2, "dispoetAmount");
            return (Criteria) this;
        }

        public Criteria andTaxStampCofficientIsNull() {
            addCriterion("tax_stamp_cofficient is null");
            return (Criteria) this;
        }

        public Criteria andTaxStampCofficientIsNotNull() {
            addCriterion("tax_stamp_cofficient is not null");
            return (Criteria) this;
        }

        public Criteria andTaxStampCofficientEqualTo(String value) {
            addCriterion("tax_stamp_cofficient =", value, "taxStampCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxStampCofficientNotEqualTo(String value) {
            addCriterion("tax_stamp_cofficient <>", value, "taxStampCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxStampCofficientGreaterThan(String value) {
            addCriterion("tax_stamp_cofficient >", value, "taxStampCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxStampCofficientGreaterThanOrEqualTo(String value) {
            addCriterion("tax_stamp_cofficient >=", value, "taxStampCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxStampCofficientLessThan(String value) {
            addCriterion("tax_stamp_cofficient <", value, "taxStampCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxStampCofficientLessThanOrEqualTo(String value) {
            addCriterion("tax_stamp_cofficient <=", value, "taxStampCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxStampCofficientLike(String value) {
            addCriterion("tax_stamp_cofficient like", value, "taxStampCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxStampCofficientNotLike(String value) {
            addCriterion("tax_stamp_cofficient not like", value, "taxStampCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxStampCofficientIn(List<String> values) {
            addCriterion("tax_stamp_cofficient in", values, "taxStampCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxStampCofficientNotIn(List<String> values) {
            addCriterion("tax_stamp_cofficient not in", values, "taxStampCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxStampCofficientBetween(String value1, String value2) {
            addCriterion("tax_stamp_cofficient between", value1, value2, "taxStampCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxStampCofficientNotBetween(String value1, String value2) {
            addCriterion("tax_stamp_cofficient not between", value1, value2, "taxStampCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxStampIsNull() {
            addCriterion("tax_stamp is null");
            return (Criteria) this;
        }

        public Criteria andTaxStampIsNotNull() {
            addCriterion("tax_stamp is not null");
            return (Criteria) this;
        }

        public Criteria andTaxStampEqualTo(String value) {
            addCriterion("tax_stamp =", value, "taxStamp");
            return (Criteria) this;
        }

        public Criteria andTaxStampNotEqualTo(String value) {
            addCriterion("tax_stamp <>", value, "taxStamp");
            return (Criteria) this;
        }

        public Criteria andTaxStampGreaterThan(String value) {
            addCriterion("tax_stamp >", value, "taxStamp");
            return (Criteria) this;
        }

        public Criteria andTaxStampGreaterThanOrEqualTo(String value) {
            addCriterion("tax_stamp >=", value, "taxStamp");
            return (Criteria) this;
        }

        public Criteria andTaxStampLessThan(String value) {
            addCriterion("tax_stamp <", value, "taxStamp");
            return (Criteria) this;
        }

        public Criteria andTaxStampLessThanOrEqualTo(String value) {
            addCriterion("tax_stamp <=", value, "taxStamp");
            return (Criteria) this;
        }

        public Criteria andTaxStampLike(String value) {
            addCriterion("tax_stamp like", value, "taxStamp");
            return (Criteria) this;
        }

        public Criteria andTaxStampNotLike(String value) {
            addCriterion("tax_stamp not like", value, "taxStamp");
            return (Criteria) this;
        }

        public Criteria andTaxStampIn(List<String> values) {
            addCriterion("tax_stamp in", values, "taxStamp");
            return (Criteria) this;
        }

        public Criteria andTaxStampNotIn(List<String> values) {
            addCriterion("tax_stamp not in", values, "taxStamp");
            return (Criteria) this;
        }

        public Criteria andTaxStampBetween(String value1, String value2) {
            addCriterion("tax_stamp between", value1, value2, "taxStamp");
            return (Criteria) this;
        }

        public Criteria andTaxStampNotBetween(String value1, String value2) {
            addCriterion("tax_stamp not between", value1, value2, "taxStamp");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueCofficientIsNull() {
            addCriterion("tax_addedvalue_cofficient is null");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueCofficientIsNotNull() {
            addCriterion("tax_addedvalue_cofficient is not null");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueCofficientEqualTo(String value) {
            addCriterion("tax_addedvalue_cofficient =", value, "taxAddedvalueCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueCofficientNotEqualTo(String value) {
            addCriterion("tax_addedvalue_cofficient <>", value, "taxAddedvalueCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueCofficientGreaterThan(String value) {
            addCriterion("tax_addedvalue_cofficient >", value, "taxAddedvalueCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueCofficientGreaterThanOrEqualTo(String value) {
            addCriterion("tax_addedvalue_cofficient >=", value, "taxAddedvalueCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueCofficientLessThan(String value) {
            addCriterion("tax_addedvalue_cofficient <", value, "taxAddedvalueCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueCofficientLessThanOrEqualTo(String value) {
            addCriterion("tax_addedvalue_cofficient <=", value, "taxAddedvalueCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueCofficientLike(String value) {
            addCriterion("tax_addedvalue_cofficient like", value, "taxAddedvalueCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueCofficientNotLike(String value) {
            addCriterion("tax_addedvalue_cofficient not like", value, "taxAddedvalueCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueCofficientIn(List<String> values) {
            addCriterion("tax_addedvalue_cofficient in", values, "taxAddedvalueCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueCofficientNotIn(List<String> values) {
            addCriterion("tax_addedvalue_cofficient not in", values, "taxAddedvalueCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueCofficientBetween(String value1, String value2) {
            addCriterion("tax_addedvalue_cofficient between", value1, value2, "taxAddedvalueCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueCofficientNotBetween(String value1, String value2) {
            addCriterion("tax_addedvalue_cofficient not between", value1, value2, "taxAddedvalueCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueIsNull() {
            addCriterion("tax_addedvalue is null");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueIsNotNull() {
            addCriterion("tax_addedvalue is not null");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueEqualTo(String value) {
            addCriterion("tax_addedvalue =", value, "taxAddedvalue");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueNotEqualTo(String value) {
            addCriterion("tax_addedvalue <>", value, "taxAddedvalue");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueGreaterThan(String value) {
            addCriterion("tax_addedvalue >", value, "taxAddedvalue");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueGreaterThanOrEqualTo(String value) {
            addCriterion("tax_addedvalue >=", value, "taxAddedvalue");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueLessThan(String value) {
            addCriterion("tax_addedvalue <", value, "taxAddedvalue");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueLessThanOrEqualTo(String value) {
            addCriterion("tax_addedvalue <=", value, "taxAddedvalue");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueLike(String value) {
            addCriterion("tax_addedvalue like", value, "taxAddedvalue");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueNotLike(String value) {
            addCriterion("tax_addedvalue not like", value, "taxAddedvalue");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueIn(List<String> values) {
            addCriterion("tax_addedvalue in", values, "taxAddedvalue");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueNotIn(List<String> values) {
            addCriterion("tax_addedvalue not in", values, "taxAddedvalue");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueBetween(String value1, String value2) {
            addCriterion("tax_addedvalue between", value1, value2, "taxAddedvalue");
            return (Criteria) this;
        }

        public Criteria andTaxAddedvalueNotBetween(String value1, String value2) {
            addCriterion("tax_addedvalue not between", value1, value2, "taxAddedvalue");
            return (Criteria) this;
        }

        public Criteria andTaxLandCofficientIsNull() {
            addCriterion("tax_land_cofficient is null");
            return (Criteria) this;
        }

        public Criteria andTaxLandCofficientIsNotNull() {
            addCriterion("tax_land_cofficient is not null");
            return (Criteria) this;
        }

        public Criteria andTaxLandCofficientEqualTo(String value) {
            addCriterion("tax_land_cofficient =", value, "taxLandCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxLandCofficientNotEqualTo(String value) {
            addCriterion("tax_land_cofficient <>", value, "taxLandCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxLandCofficientGreaterThan(String value) {
            addCriterion("tax_land_cofficient >", value, "taxLandCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxLandCofficientGreaterThanOrEqualTo(String value) {
            addCriterion("tax_land_cofficient >=", value, "taxLandCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxLandCofficientLessThan(String value) {
            addCriterion("tax_land_cofficient <", value, "taxLandCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxLandCofficientLessThanOrEqualTo(String value) {
            addCriterion("tax_land_cofficient <=", value, "taxLandCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxLandCofficientLike(String value) {
            addCriterion("tax_land_cofficient like", value, "taxLandCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxLandCofficientNotLike(String value) {
            addCriterion("tax_land_cofficient not like", value, "taxLandCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxLandCofficientIn(List<String> values) {
            addCriterion("tax_land_cofficient in", values, "taxLandCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxLandCofficientNotIn(List<String> values) {
            addCriterion("tax_land_cofficient not in", values, "taxLandCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxLandCofficientBetween(String value1, String value2) {
            addCriterion("tax_land_cofficient between", value1, value2, "taxLandCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxLandCofficientNotBetween(String value1, String value2) {
            addCriterion("tax_land_cofficient not between", value1, value2, "taxLandCofficient");
            return (Criteria) this;
        }

        public Criteria andTaxLandIsNull() {
            addCriterion("tax_land is null");
            return (Criteria) this;
        }

        public Criteria andTaxLandIsNotNull() {
            addCriterion("tax_land is not null");
            return (Criteria) this;
        }

        public Criteria andTaxLandEqualTo(String value) {
            addCriterion("tax_land =", value, "taxLand");
            return (Criteria) this;
        }

        public Criteria andTaxLandNotEqualTo(String value) {
            addCriterion("tax_land <>", value, "taxLand");
            return (Criteria) this;
        }

        public Criteria andTaxLandGreaterThan(String value) {
            addCriterion("tax_land >", value, "taxLand");
            return (Criteria) this;
        }

        public Criteria andTaxLandGreaterThanOrEqualTo(String value) {
            addCriterion("tax_land >=", value, "taxLand");
            return (Criteria) this;
        }

        public Criteria andTaxLandLessThan(String value) {
            addCriterion("tax_land <", value, "taxLand");
            return (Criteria) this;
        }

        public Criteria andTaxLandLessThanOrEqualTo(String value) {
            addCriterion("tax_land <=", value, "taxLand");
            return (Criteria) this;
        }

        public Criteria andTaxLandLike(String value) {
            addCriterion("tax_land like", value, "taxLand");
            return (Criteria) this;
        }

        public Criteria andTaxLandNotLike(String value) {
            addCriterion("tax_land not like", value, "taxLand");
            return (Criteria) this;
        }

        public Criteria andTaxLandIn(List<String> values) {
            addCriterion("tax_land in", values, "taxLand");
            return (Criteria) this;
        }

        public Criteria andTaxLandNotIn(List<String> values) {
            addCriterion("tax_land not in", values, "taxLand");
            return (Criteria) this;
        }

        public Criteria andTaxLandBetween(String value1, String value2) {
            addCriterion("tax_land between", value1, value2, "taxLand");
            return (Criteria) this;
        }

        public Criteria andTaxLandNotBetween(String value1, String value2) {
            addCriterion("tax_land not between", value1, value2, "taxLand");
            return (Criteria) this;
        }

        public Criteria andTaxOtherIsNull() {
            addCriterion("tax_other is null");
            return (Criteria) this;
        }

        public Criteria andTaxOtherIsNotNull() {
            addCriterion("tax_other is not null");
            return (Criteria) this;
        }

        public Criteria andTaxOtherEqualTo(String value) {
            addCriterion("tax_other =", value, "taxOther");
            return (Criteria) this;
        }

        public Criteria andTaxOtherNotEqualTo(String value) {
            addCriterion("tax_other <>", value, "taxOther");
            return (Criteria) this;
        }

        public Criteria andTaxOtherGreaterThan(String value) {
            addCriterion("tax_other >", value, "taxOther");
            return (Criteria) this;
        }

        public Criteria andTaxOtherGreaterThanOrEqualTo(String value) {
            addCriterion("tax_other >=", value, "taxOther");
            return (Criteria) this;
        }

        public Criteria andTaxOtherLessThan(String value) {
            addCriterion("tax_other <", value, "taxOther");
            return (Criteria) this;
        }

        public Criteria andTaxOtherLessThanOrEqualTo(String value) {
            addCriterion("tax_other <=", value, "taxOther");
            return (Criteria) this;
        }

        public Criteria andTaxOtherLike(String value) {
            addCriterion("tax_other like", value, "taxOther");
            return (Criteria) this;
        }

        public Criteria andTaxOtherNotLike(String value) {
            addCriterion("tax_other not like", value, "taxOther");
            return (Criteria) this;
        }

        public Criteria andTaxOtherIn(List<String> values) {
            addCriterion("tax_other in", values, "taxOther");
            return (Criteria) this;
        }

        public Criteria andTaxOtherNotIn(List<String> values) {
            addCriterion("tax_other not in", values, "taxOther");
            return (Criteria) this;
        }

        public Criteria andTaxOtherBetween(String value1, String value2) {
            addCriterion("tax_other between", value1, value2, "taxOther");
            return (Criteria) this;
        }

        public Criteria andTaxOtherNotBetween(String value1, String value2) {
            addCriterion("tax_other not between", value1, value2, "taxOther");
            return (Criteria) this;
        }

        public Criteria andTaxAmountIsNull() {
            addCriterion("tax_amount is null");
            return (Criteria) this;
        }

        public Criteria andTaxAmountIsNotNull() {
            addCriterion("tax_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTaxAmountEqualTo(String value) {
            addCriterion("tax_amount =", value, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountNotEqualTo(String value) {
            addCriterion("tax_amount <>", value, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountGreaterThan(String value) {
            addCriterion("tax_amount >", value, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountGreaterThanOrEqualTo(String value) {
            addCriterion("tax_amount >=", value, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountLessThan(String value) {
            addCriterion("tax_amount <", value, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountLessThanOrEqualTo(String value) {
            addCriterion("tax_amount <=", value, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountLike(String value) {
            addCriterion("tax_amount like", value, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountNotLike(String value) {
            addCriterion("tax_amount not like", value, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountIn(List<String> values) {
            addCriterion("tax_amount in", values, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountNotIn(List<String> values) {
            addCriterion("tax_amount not in", values, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountBetween(String value1, String value2) {
            addCriterion("tax_amount between", value1, value2, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andTaxAmountNotBetween(String value1, String value2) {
            addCriterion("tax_amount not between", value1, value2, "taxAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountIsNull() {
            addCriterion("cost_amount is null");
            return (Criteria) this;
        }

        public Criteria andCostAmountIsNotNull() {
            addCriterion("cost_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCostAmountEqualTo(String value) {
            addCriterion("cost_amount =", value, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountNotEqualTo(String value) {
            addCriterion("cost_amount <>", value, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountGreaterThan(String value) {
            addCriterion("cost_amount >", value, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountGreaterThanOrEqualTo(String value) {
            addCriterion("cost_amount >=", value, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountLessThan(String value) {
            addCriterion("cost_amount <", value, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountLessThanOrEqualTo(String value) {
            addCriterion("cost_amount <=", value, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountLike(String value) {
            addCriterion("cost_amount like", value, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountNotLike(String value) {
            addCriterion("cost_amount not like", value, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountIn(List<String> values) {
            addCriterion("cost_amount in", values, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountNotIn(List<String> values) {
            addCriterion("cost_amount not in", values, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountBetween(String value1, String value2) {
            addCriterion("cost_amount between", value1, value2, "costAmount");
            return (Criteria) this;
        }

        public Criteria andCostAmountNotBetween(String value1, String value2) {
            addCriterion("cost_amount not between", value1, value2, "costAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationValueAmountIsNull() {
            addCriterion("pawn_realization_value_amount is null");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationValueAmountIsNotNull() {
            addCriterion("pawn_realization_value_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationValueAmountEqualTo(String value) {
            addCriterion("pawn_realization_value_amount =", value, "pawnRealizationValueAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationValueAmountNotEqualTo(String value) {
            addCriterion("pawn_realization_value_amount <>", value, "pawnRealizationValueAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationValueAmountGreaterThan(String value) {
            addCriterion("pawn_realization_value_amount >", value, "pawnRealizationValueAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationValueAmountGreaterThanOrEqualTo(String value) {
            addCriterion("pawn_realization_value_amount >=", value, "pawnRealizationValueAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationValueAmountLessThan(String value) {
            addCriterion("pawn_realization_value_amount <", value, "pawnRealizationValueAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationValueAmountLessThanOrEqualTo(String value) {
            addCriterion("pawn_realization_value_amount <=", value, "pawnRealizationValueAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationValueAmountLike(String value) {
            addCriterion("pawn_realization_value_amount like", value, "pawnRealizationValueAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationValueAmountNotLike(String value) {
            addCriterion("pawn_realization_value_amount not like", value, "pawnRealizationValueAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationValueAmountIn(List<String> values) {
            addCriterion("pawn_realization_value_amount in", values, "pawnRealizationValueAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationValueAmountNotIn(List<String> values) {
            addCriterion("pawn_realization_value_amount not in", values, "pawnRealizationValueAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationValueAmountBetween(String value1, String value2) {
            addCriterion("pawn_realization_value_amount between", value1, value2, "pawnRealizationValueAmount");
            return (Criteria) this;
        }

        public Criteria andPawnRealizationValueAmountNotBetween(String value1, String value2) {
            addCriterion("pawn_realization_value_amount not between", value1, value2, "pawnRealizationValueAmount");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeOrdinaryIsNull() {
            addCriterion("recovery_income_ordinary is null");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeOrdinaryIsNotNull() {
            addCriterion("recovery_income_ordinary is not null");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeOrdinaryEqualTo(String value) {
            addCriterion("recovery_income_ordinary =", value, "recoveryIncomeOrdinary");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeOrdinaryNotEqualTo(String value) {
            addCriterion("recovery_income_ordinary <>", value, "recoveryIncomeOrdinary");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeOrdinaryGreaterThan(String value) {
            addCriterion("recovery_income_ordinary >", value, "recoveryIncomeOrdinary");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeOrdinaryGreaterThanOrEqualTo(String value) {
            addCriterion("recovery_income_ordinary >=", value, "recoveryIncomeOrdinary");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeOrdinaryLessThan(String value) {
            addCriterion("recovery_income_ordinary <", value, "recoveryIncomeOrdinary");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeOrdinaryLessThanOrEqualTo(String value) {
            addCriterion("recovery_income_ordinary <=", value, "recoveryIncomeOrdinary");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeOrdinaryLike(String value) {
            addCriterion("recovery_income_ordinary like", value, "recoveryIncomeOrdinary");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeOrdinaryNotLike(String value) {
            addCriterion("recovery_income_ordinary not like", value, "recoveryIncomeOrdinary");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeOrdinaryIn(List<String> values) {
            addCriterion("recovery_income_ordinary in", values, "recoveryIncomeOrdinary");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeOrdinaryNotIn(List<String> values) {
            addCriterion("recovery_income_ordinary not in", values, "recoveryIncomeOrdinary");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeOrdinaryBetween(String value1, String value2) {
            addCriterion("recovery_income_ordinary between", value1, value2, "recoveryIncomeOrdinary");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeOrdinaryNotBetween(String value1, String value2) {
            addCriterion("recovery_income_ordinary not between", value1, value2, "recoveryIncomeOrdinary");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeAllIsNull() {
            addCriterion("recovery_income_all is null");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeAllIsNotNull() {
            addCriterion("recovery_income_all is not null");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeAllEqualTo(String value) {
            addCriterion("recovery_income_all =", value, "recoveryIncomeAll");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeAllNotEqualTo(String value) {
            addCriterion("recovery_income_all <>", value, "recoveryIncomeAll");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeAllGreaterThan(String value) {
            addCriterion("recovery_income_all >", value, "recoveryIncomeAll");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeAllGreaterThanOrEqualTo(String value) {
            addCriterion("recovery_income_all >=", value, "recoveryIncomeAll");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeAllLessThan(String value) {
            addCriterion("recovery_income_all <", value, "recoveryIncomeAll");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeAllLessThanOrEqualTo(String value) {
            addCriterion("recovery_income_all <=", value, "recoveryIncomeAll");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeAllLike(String value) {
            addCriterion("recovery_income_all like", value, "recoveryIncomeAll");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeAllNotLike(String value) {
            addCriterion("recovery_income_all not like", value, "recoveryIncomeAll");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeAllIn(List<String> values) {
            addCriterion("recovery_income_all in", values, "recoveryIncomeAll");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeAllNotIn(List<String> values) {
            addCriterion("recovery_income_all not in", values, "recoveryIncomeAll");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeAllBetween(String value1, String value2) {
            addCriterion("recovery_income_all between", value1, value2, "recoveryIncomeAll");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeAllNotBetween(String value1, String value2) {
            addCriterion("recovery_income_all not between", value1, value2, "recoveryIncomeAll");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeThirdPartyIsNull() {
            addCriterion("recovery_income_third_party is null");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeThirdPartyIsNotNull() {
            addCriterion("recovery_income_third_party is not null");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeThirdPartyEqualTo(String value) {
            addCriterion("recovery_income_third_party =", value, "recoveryIncomeThirdParty");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeThirdPartyNotEqualTo(String value) {
            addCriterion("recovery_income_third_party <>", value, "recoveryIncomeThirdParty");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeThirdPartyGreaterThan(String value) {
            addCriterion("recovery_income_third_party >", value, "recoveryIncomeThirdParty");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeThirdPartyGreaterThanOrEqualTo(String value) {
            addCriterion("recovery_income_third_party >=", value, "recoveryIncomeThirdParty");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeThirdPartyLessThan(String value) {
            addCriterion("recovery_income_third_party <", value, "recoveryIncomeThirdParty");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeThirdPartyLessThanOrEqualTo(String value) {
            addCriterion("recovery_income_third_party <=", value, "recoveryIncomeThirdParty");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeThirdPartyLike(String value) {
            addCriterion("recovery_income_third_party like", value, "recoveryIncomeThirdParty");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeThirdPartyNotLike(String value) {
            addCriterion("recovery_income_third_party not like", value, "recoveryIncomeThirdParty");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeThirdPartyIn(List<String> values) {
            addCriterion("recovery_income_third_party in", values, "recoveryIncomeThirdParty");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeThirdPartyNotIn(List<String> values) {
            addCriterion("recovery_income_third_party not in", values, "recoveryIncomeThirdParty");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeThirdPartyBetween(String value1, String value2) {
            addCriterion("recovery_income_third_party between", value1, value2, "recoveryIncomeThirdParty");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomeThirdPartyNotBetween(String value1, String value2) {
            addCriterion("recovery_income_third_party not between", value1, value2, "recoveryIncomeThirdParty");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomAmountIsNull() {
            addCriterion("recovery_incom_amount is null");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomAmountIsNotNull() {
            addCriterion("recovery_incom_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomAmountEqualTo(String value) {
            addCriterion("recovery_incom_amount =", value, "recoveryIncomAmount");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomAmountNotEqualTo(String value) {
            addCriterion("recovery_incom_amount <>", value, "recoveryIncomAmount");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomAmountGreaterThan(String value) {
            addCriterion("recovery_incom_amount >", value, "recoveryIncomAmount");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomAmountGreaterThanOrEqualTo(String value) {
            addCriterion("recovery_incom_amount >=", value, "recoveryIncomAmount");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomAmountLessThan(String value) {
            addCriterion("recovery_incom_amount <", value, "recoveryIncomAmount");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomAmountLessThanOrEqualTo(String value) {
            addCriterion("recovery_incom_amount <=", value, "recoveryIncomAmount");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomAmountLike(String value) {
            addCriterion("recovery_incom_amount like", value, "recoveryIncomAmount");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomAmountNotLike(String value) {
            addCriterion("recovery_incom_amount not like", value, "recoveryIncomAmount");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomAmountIn(List<String> values) {
            addCriterion("recovery_incom_amount in", values, "recoveryIncomAmount");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomAmountNotIn(List<String> values) {
            addCriterion("recovery_incom_amount not in", values, "recoveryIncomAmount");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomAmountBetween(String value1, String value2) {
            addCriterion("recovery_incom_amount between", value1, value2, "recoveryIncomAmount");
            return (Criteria) this;
        }

        public Criteria andRecoveryIncomAmountNotBetween(String value1, String value2) {
            addCriterion("recovery_incom_amount not between", value1, value2, "recoveryIncomAmount");
            return (Criteria) this;
        }

        public Criteria andRecoveryBadLoansIsNull() {
            addCriterion("recovery_bad_loans is null");
            return (Criteria) this;
        }

        public Criteria andRecoveryBadLoansIsNotNull() {
            addCriterion("recovery_bad_loans is not null");
            return (Criteria) this;
        }

        public Criteria andRecoveryBadLoansEqualTo(String value) {
            addCriterion("recovery_bad_loans =", value, "recoveryBadLoans");
            return (Criteria) this;
        }

        public Criteria andRecoveryBadLoansNotEqualTo(String value) {
            addCriterion("recovery_bad_loans <>", value, "recoveryBadLoans");
            return (Criteria) this;
        }

        public Criteria andRecoveryBadLoansGreaterThan(String value) {
            addCriterion("recovery_bad_loans >", value, "recoveryBadLoans");
            return (Criteria) this;
        }

        public Criteria andRecoveryBadLoansGreaterThanOrEqualTo(String value) {
            addCriterion("recovery_bad_loans >=", value, "recoveryBadLoans");
            return (Criteria) this;
        }

        public Criteria andRecoveryBadLoansLessThan(String value) {
            addCriterion("recovery_bad_loans <", value, "recoveryBadLoans");
            return (Criteria) this;
        }

        public Criteria andRecoveryBadLoansLessThanOrEqualTo(String value) {
            addCriterion("recovery_bad_loans <=", value, "recoveryBadLoans");
            return (Criteria) this;
        }

        public Criteria andRecoveryBadLoansLike(String value) {
            addCriterion("recovery_bad_loans like", value, "recoveryBadLoans");
            return (Criteria) this;
        }

        public Criteria andRecoveryBadLoansNotLike(String value) {
            addCriterion("recovery_bad_loans not like", value, "recoveryBadLoans");
            return (Criteria) this;
        }

        public Criteria andRecoveryBadLoansIn(List<String> values) {
            addCriterion("recovery_bad_loans in", values, "recoveryBadLoans");
            return (Criteria) this;
        }

        public Criteria andRecoveryBadLoansNotIn(List<String> values) {
            addCriterion("recovery_bad_loans not in", values, "recoveryBadLoans");
            return (Criteria) this;
        }

        public Criteria andRecoveryBadLoansBetween(String value1, String value2) {
            addCriterion("recovery_bad_loans between", value1, value2, "recoveryBadLoans");
            return (Criteria) this;
        }

        public Criteria andRecoveryBadLoansNotBetween(String value1, String value2) {
            addCriterion("recovery_bad_loans not between", value1, value2, "recoveryBadLoans");
            return (Criteria) this;
        }

        public Criteria andRecoveryLimitIsNull() {
            addCriterion("recovery_limit is null");
            return (Criteria) this;
        }

        public Criteria andRecoveryLimitIsNotNull() {
            addCriterion("recovery_limit is not null");
            return (Criteria) this;
        }

        public Criteria andRecoveryLimitEqualTo(String value) {
            addCriterion("recovery_limit =", value, "recoveryLimit");
            return (Criteria) this;
        }

        public Criteria andRecoveryLimitNotEqualTo(String value) {
            addCriterion("recovery_limit <>", value, "recoveryLimit");
            return (Criteria) this;
        }

        public Criteria andRecoveryLimitGreaterThan(String value) {
            addCriterion("recovery_limit >", value, "recoveryLimit");
            return (Criteria) this;
        }

        public Criteria andRecoveryLimitGreaterThanOrEqualTo(String value) {
            addCriterion("recovery_limit >=", value, "recoveryLimit");
            return (Criteria) this;
        }

        public Criteria andRecoveryLimitLessThan(String value) {
            addCriterion("recovery_limit <", value, "recoveryLimit");
            return (Criteria) this;
        }

        public Criteria andRecoveryLimitLessThanOrEqualTo(String value) {
            addCriterion("recovery_limit <=", value, "recoveryLimit");
            return (Criteria) this;
        }

        public Criteria andRecoveryLimitLike(String value) {
            addCriterion("recovery_limit like", value, "recoveryLimit");
            return (Criteria) this;
        }

        public Criteria andRecoveryLimitNotLike(String value) {
            addCriterion("recovery_limit not like", value, "recoveryLimit");
            return (Criteria) this;
        }

        public Criteria andRecoveryLimitIn(List<String> values) {
            addCriterion("recovery_limit in", values, "recoveryLimit");
            return (Criteria) this;
        }

        public Criteria andRecoveryLimitNotIn(List<String> values) {
            addCriterion("recovery_limit not in", values, "recoveryLimit");
            return (Criteria) this;
        }

        public Criteria andRecoveryLimitBetween(String value1, String value2) {
            addCriterion("recovery_limit between", value1, value2, "recoveryLimit");
            return (Criteria) this;
        }

        public Criteria andRecoveryLimitNotBetween(String value1, String value2) {
            addCriterion("recovery_limit not between", value1, value2, "recoveryLimit");
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