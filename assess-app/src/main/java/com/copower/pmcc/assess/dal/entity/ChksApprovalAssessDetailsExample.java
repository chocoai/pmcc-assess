package com.copower.pmcc.assess.dal.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChksApprovalAssessDetailsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChksApprovalAssessDetailsExample() {
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

        public Criteria andAssessModelIdIsNull() {
            addCriterion("assess_model_id is null");
            return (Criteria) this;
        }

        public Criteria andAssessModelIdIsNotNull() {
            addCriterion("assess_model_id is not null");
            return (Criteria) this;
        }

        public Criteria andAssessModelIdEqualTo(Integer value) {
            addCriterion("assess_model_id =", value, "assessModelId");
            return (Criteria) this;
        }

        public Criteria andAssessModelIdNotEqualTo(Integer value) {
            addCriterion("assess_model_id <>", value, "assessModelId");
            return (Criteria) this;
        }

        public Criteria andAssessModelIdGreaterThan(Integer value) {
            addCriterion("assess_model_id >", value, "assessModelId");
            return (Criteria) this;
        }

        public Criteria andAssessModelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("assess_model_id >=", value, "assessModelId");
            return (Criteria) this;
        }

        public Criteria andAssessModelIdLessThan(Integer value) {
            addCriterion("assess_model_id <", value, "assessModelId");
            return (Criteria) this;
        }

        public Criteria andAssessModelIdLessThanOrEqualTo(Integer value) {
            addCriterion("assess_model_id <=", value, "assessModelId");
            return (Criteria) this;
        }

        public Criteria andAssessModelIdIn(List<Integer> values) {
            addCriterion("assess_model_id in", values, "assessModelId");
            return (Criteria) this;
        }

        public Criteria andAssessModelIdNotIn(List<Integer> values) {
            addCriterion("assess_model_id not in", values, "assessModelId");
            return (Criteria) this;
        }

        public Criteria andAssessModelIdBetween(Integer value1, Integer value2) {
            addCriterion("assess_model_id between", value1, value2, "assessModelId");
            return (Criteria) this;
        }

        public Criteria andAssessModelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("assess_model_id not between", value1, value2, "assessModelId");
            return (Criteria) this;
        }

        public Criteria andAssessModelTitleIsNull() {
            addCriterion("assess_model_title is null");
            return (Criteria) this;
        }

        public Criteria andAssessModelTitleIsNotNull() {
            addCriterion("assess_model_title is not null");
            return (Criteria) this;
        }

        public Criteria andAssessModelTitleEqualTo(String value) {
            addCriterion("assess_model_title =", value, "assessModelTitle");
            return (Criteria) this;
        }

        public Criteria andAssessModelTitleNotEqualTo(String value) {
            addCriterion("assess_model_title <>", value, "assessModelTitle");
            return (Criteria) this;
        }

        public Criteria andAssessModelTitleGreaterThan(String value) {
            addCriterion("assess_model_title >", value, "assessModelTitle");
            return (Criteria) this;
        }

        public Criteria andAssessModelTitleGreaterThanOrEqualTo(String value) {
            addCriterion("assess_model_title >=", value, "assessModelTitle");
            return (Criteria) this;
        }

        public Criteria andAssessModelTitleLessThan(String value) {
            addCriterion("assess_model_title <", value, "assessModelTitle");
            return (Criteria) this;
        }

        public Criteria andAssessModelTitleLessThanOrEqualTo(String value) {
            addCriterion("assess_model_title <=", value, "assessModelTitle");
            return (Criteria) this;
        }

        public Criteria andAssessModelTitleLike(String value) {
            addCriterion("assess_model_title like", value, "assessModelTitle");
            return (Criteria) this;
        }

        public Criteria andAssessModelTitleNotLike(String value) {
            addCriterion("assess_model_title not like", value, "assessModelTitle");
            return (Criteria) this;
        }

        public Criteria andAssessModelTitleIn(List<String> values) {
            addCriterion("assess_model_title in", values, "assessModelTitle");
            return (Criteria) this;
        }

        public Criteria andAssessModelTitleNotIn(List<String> values) {
            addCriterion("assess_model_title not in", values, "assessModelTitle");
            return (Criteria) this;
        }

        public Criteria andAssessModelTitleBetween(String value1, String value2) {
            addCriterion("assess_model_title between", value1, value2, "assessModelTitle");
            return (Criteria) this;
        }

        public Criteria andAssessModelTitleNotBetween(String value1, String value2) {
            addCriterion("assess_model_title not between", value1, value2, "assessModelTitle");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(BigDecimal value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(BigDecimal value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(BigDecimal value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(BigDecimal value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<BigDecimal> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<BigDecimal> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksIsNull() {
            addCriterion("score_remarks is null");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksIsNotNull() {
            addCriterion("score_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksEqualTo(String value) {
            addCriterion("score_remarks =", value, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksNotEqualTo(String value) {
            addCriterion("score_remarks <>", value, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksGreaterThan(String value) {
            addCriterion("score_remarks >", value, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("score_remarks >=", value, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksLessThan(String value) {
            addCriterion("score_remarks <", value, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksLessThanOrEqualTo(String value) {
            addCriterion("score_remarks <=", value, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksLike(String value) {
            addCriterion("score_remarks like", value, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksNotLike(String value) {
            addCriterion("score_remarks not like", value, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksIn(List<String> values) {
            addCriterion("score_remarks in", values, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksNotIn(List<String> values) {
            addCriterion("score_remarks not in", values, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksBetween(String value1, String value2) {
            addCriterion("score_remarks between", value1, value2, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksNotBetween(String value1, String value2) {
            addCriterion("score_remarks not between", value1, value2, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andChksApprovalAssessIdIsNull() {
            addCriterion("chks_approval_assess_id is null");
            return (Criteria) this;
        }

        public Criteria andChksApprovalAssessIdIsNotNull() {
            addCriterion("chks_approval_assess_id is not null");
            return (Criteria) this;
        }

        public Criteria andChksApprovalAssessIdEqualTo(Integer value) {
            addCriterion("chks_approval_assess_id =", value, "chksApprovalAssessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalAssessIdNotEqualTo(Integer value) {
            addCriterion("chks_approval_assess_id <>", value, "chksApprovalAssessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalAssessIdGreaterThan(Integer value) {
            addCriterion("chks_approval_assess_id >", value, "chksApprovalAssessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalAssessIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("chks_approval_assess_id >=", value, "chksApprovalAssessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalAssessIdLessThan(Integer value) {
            addCriterion("chks_approval_assess_id <", value, "chksApprovalAssessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalAssessIdLessThanOrEqualTo(Integer value) {
            addCriterion("chks_approval_assess_id <=", value, "chksApprovalAssessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalAssessIdIn(List<Integer> values) {
            addCriterion("chks_approval_assess_id in", values, "chksApprovalAssessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalAssessIdNotIn(List<Integer> values) {
            addCriterion("chks_approval_assess_id not in", values, "chksApprovalAssessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalAssessIdBetween(Integer value1, Integer value2) {
            addCriterion("chks_approval_assess_id between", value1, value2, "chksApprovalAssessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalAssessIdNotBetween(Integer value1, Integer value2) {
            addCriterion("chks_approval_assess_id not between", value1, value2, "chksApprovalAssessId");
            return (Criteria) this;
        }

        public Criteria andAssessModelMaxIsNull() {
            addCriterion("assess_model_max is null");
            return (Criteria) this;
        }

        public Criteria andAssessModelMaxIsNotNull() {
            addCriterion("assess_model_max is not null");
            return (Criteria) this;
        }

        public Criteria andAssessModelMaxEqualTo(BigDecimal value) {
            addCriterion("assess_model_max =", value, "assessModelMax");
            return (Criteria) this;
        }

        public Criteria andAssessModelMaxNotEqualTo(BigDecimal value) {
            addCriterion("assess_model_max <>", value, "assessModelMax");
            return (Criteria) this;
        }

        public Criteria andAssessModelMaxGreaterThan(BigDecimal value) {
            addCriterion("assess_model_max >", value, "assessModelMax");
            return (Criteria) this;
        }

        public Criteria andAssessModelMaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_model_max >=", value, "assessModelMax");
            return (Criteria) this;
        }

        public Criteria andAssessModelMaxLessThan(BigDecimal value) {
            addCriterion("assess_model_max <", value, "assessModelMax");
            return (Criteria) this;
        }

        public Criteria andAssessModelMaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_model_max <=", value, "assessModelMax");
            return (Criteria) this;
        }

        public Criteria andAssessModelMaxIn(List<BigDecimal> values) {
            addCriterion("assess_model_max in", values, "assessModelMax");
            return (Criteria) this;
        }

        public Criteria andAssessModelMaxNotIn(List<BigDecimal> values) {
            addCriterion("assess_model_max not in", values, "assessModelMax");
            return (Criteria) this;
        }

        public Criteria andAssessModelMaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_model_max between", value1, value2, "assessModelMax");
            return (Criteria) this;
        }

        public Criteria andAssessModelMaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_model_max not between", value1, value2, "assessModelMax");
            return (Criteria) this;
        }

        public Criteria andAssessModelMinIsNull() {
            addCriterion("assess_model_min is null");
            return (Criteria) this;
        }

        public Criteria andAssessModelMinIsNotNull() {
            addCriterion("assess_model_min is not null");
            return (Criteria) this;
        }

        public Criteria andAssessModelMinEqualTo(BigDecimal value) {
            addCriterion("assess_model_min =", value, "assessModelMin");
            return (Criteria) this;
        }

        public Criteria andAssessModelMinNotEqualTo(BigDecimal value) {
            addCriterion("assess_model_min <>", value, "assessModelMin");
            return (Criteria) this;
        }

        public Criteria andAssessModelMinGreaterThan(BigDecimal value) {
            addCriterion("assess_model_min >", value, "assessModelMin");
            return (Criteria) this;
        }

        public Criteria andAssessModelMinGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_model_min >=", value, "assessModelMin");
            return (Criteria) this;
        }

        public Criteria andAssessModelMinLessThan(BigDecimal value) {
            addCriterion("assess_model_min <", value, "assessModelMin");
            return (Criteria) this;
        }

        public Criteria andAssessModelMinLessThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_model_min <=", value, "assessModelMin");
            return (Criteria) this;
        }

        public Criteria andAssessModelMinIn(List<BigDecimal> values) {
            addCriterion("assess_model_min in", values, "assessModelMin");
            return (Criteria) this;
        }

        public Criteria andAssessModelMinNotIn(List<BigDecimal> values) {
            addCriterion("assess_model_min not in", values, "assessModelMin");
            return (Criteria) this;
        }

        public Criteria andAssessModelMinBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_model_min between", value1, value2, "assessModelMin");
            return (Criteria) this;
        }

        public Criteria andAssessModelMinNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_model_min not between", value1, value2, "assessModelMin");
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