package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SchemeJudgeFunctionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SchemeJudgeFunctionExample() {
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

        public Criteria andJudgeObjectIdIsNull() {
            addCriterion("judge_object_id is null");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdIsNotNull() {
            addCriterion("judge_object_id is not null");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdEqualTo(Integer value) {
            addCriterion("judge_object_id =", value, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdNotEqualTo(Integer value) {
            addCriterion("judge_object_id <>", value, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdGreaterThan(Integer value) {
            addCriterion("judge_object_id >", value, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("judge_object_id >=", value, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdLessThan(Integer value) {
            addCriterion("judge_object_id <", value, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("judge_object_id <=", value, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdIn(List<Integer> values) {
            addCriterion("judge_object_id in", values, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdNotIn(List<Integer> values) {
            addCriterion("judge_object_id not in", values, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdBetween(Integer value1, Integer value2) {
            addCriterion("judge_object_id between", value1, value2, "judgeObjectId");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("judge_object_id not between", value1, value2, "judgeObjectId");
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

        public Criteria andMethodTypeIsNull() {
            addCriterion("method_type is null");
            return (Criteria) this;
        }

        public Criteria andMethodTypeIsNotNull() {
            addCriterion("method_type is not null");
            return (Criteria) this;
        }

        public Criteria andMethodTypeEqualTo(Integer value) {
            addCriterion("method_type =", value, "methodType");
            return (Criteria) this;
        }

        public Criteria andMethodTypeNotEqualTo(Integer value) {
            addCriterion("method_type <>", value, "methodType");
            return (Criteria) this;
        }

        public Criteria andMethodTypeGreaterThan(Integer value) {
            addCriterion("method_type >", value, "methodType");
            return (Criteria) this;
        }

        public Criteria andMethodTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("method_type >=", value, "methodType");
            return (Criteria) this;
        }

        public Criteria andMethodTypeLessThan(Integer value) {
            addCriterion("method_type <", value, "methodType");
            return (Criteria) this;
        }

        public Criteria andMethodTypeLessThanOrEqualTo(Integer value) {
            addCriterion("method_type <=", value, "methodType");
            return (Criteria) this;
        }

        public Criteria andMethodTypeIn(List<Integer> values) {
            addCriterion("method_type in", values, "methodType");
            return (Criteria) this;
        }

        public Criteria andMethodTypeNotIn(List<Integer> values) {
            addCriterion("method_type not in", values, "methodType");
            return (Criteria) this;
        }

        public Criteria andMethodTypeBetween(Integer value1, Integer value2) {
            addCriterion("method_type between", value1, value2, "methodType");
            return (Criteria) this;
        }

        public Criteria andMethodTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("method_type not between", value1, value2, "methodType");
            return (Criteria) this;
        }

        public Criteria andBisApplicableIsNull() {
            addCriterion("bis_applicable is null");
            return (Criteria) this;
        }

        public Criteria andBisApplicableIsNotNull() {
            addCriterion("bis_applicable is not null");
            return (Criteria) this;
        }

        public Criteria andBisApplicableEqualTo(Boolean value) {
            addCriterion("bis_applicable =", value, "bisApplicable");
            return (Criteria) this;
        }

        public Criteria andBisApplicableNotEqualTo(Boolean value) {
            addCriterion("bis_applicable <>", value, "bisApplicable");
            return (Criteria) this;
        }

        public Criteria andBisApplicableGreaterThan(Boolean value) {
            addCriterion("bis_applicable >", value, "bisApplicable");
            return (Criteria) this;
        }

        public Criteria andBisApplicableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_applicable >=", value, "bisApplicable");
            return (Criteria) this;
        }

        public Criteria andBisApplicableLessThan(Boolean value) {
            addCriterion("bis_applicable <", value, "bisApplicable");
            return (Criteria) this;
        }

        public Criteria andBisApplicableLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_applicable <=", value, "bisApplicable");
            return (Criteria) this;
        }

        public Criteria andBisApplicableIn(List<Boolean> values) {
            addCriterion("bis_applicable in", values, "bisApplicable");
            return (Criteria) this;
        }

        public Criteria andBisApplicableNotIn(List<Boolean> values) {
            addCriterion("bis_applicable not in", values, "bisApplicable");
            return (Criteria) this;
        }

        public Criteria andBisApplicableBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_applicable between", value1, value2, "bisApplicable");
            return (Criteria) this;
        }

        public Criteria andBisApplicableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_applicable not between", value1, value2, "bisApplicable");
            return (Criteria) this;
        }

        public Criteria andApplicableReasonIsNull() {
            addCriterion("applicable_reason is null");
            return (Criteria) this;
        }

        public Criteria andApplicableReasonIsNotNull() {
            addCriterion("applicable_reason is not null");
            return (Criteria) this;
        }

        public Criteria andApplicableReasonEqualTo(String value) {
            addCriterion("applicable_reason =", value, "applicableReason");
            return (Criteria) this;
        }

        public Criteria andApplicableReasonNotEqualTo(String value) {
            addCriterion("applicable_reason <>", value, "applicableReason");
            return (Criteria) this;
        }

        public Criteria andApplicableReasonGreaterThan(String value) {
            addCriterion("applicable_reason >", value, "applicableReason");
            return (Criteria) this;
        }

        public Criteria andApplicableReasonGreaterThanOrEqualTo(String value) {
            addCriterion("applicable_reason >=", value, "applicableReason");
            return (Criteria) this;
        }

        public Criteria andApplicableReasonLessThan(String value) {
            addCriterion("applicable_reason <", value, "applicableReason");
            return (Criteria) this;
        }

        public Criteria andApplicableReasonLessThanOrEqualTo(String value) {
            addCriterion("applicable_reason <=", value, "applicableReason");
            return (Criteria) this;
        }

        public Criteria andApplicableReasonLike(String value) {
            addCriterion("applicable_reason like", value, "applicableReason");
            return (Criteria) this;
        }

        public Criteria andApplicableReasonNotLike(String value) {
            addCriterion("applicable_reason not like", value, "applicableReason");
            return (Criteria) this;
        }

        public Criteria andApplicableReasonIn(List<String> values) {
            addCriterion("applicable_reason in", values, "applicableReason");
            return (Criteria) this;
        }

        public Criteria andApplicableReasonNotIn(List<String> values) {
            addCriterion("applicable_reason not in", values, "applicableReason");
            return (Criteria) this;
        }

        public Criteria andApplicableReasonBetween(String value1, String value2) {
            addCriterion("applicable_reason between", value1, value2, "applicableReason");
            return (Criteria) this;
        }

        public Criteria andApplicableReasonNotBetween(String value1, String value2) {
            addCriterion("applicable_reason not between", value1, value2, "applicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonIsNull() {
            addCriterion("not_applicable_reason is null");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonIsNotNull() {
            addCriterion("not_applicable_reason is not null");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonEqualTo(String value) {
            addCriterion("not_applicable_reason =", value, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonNotEqualTo(String value) {
            addCriterion("not_applicable_reason <>", value, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonGreaterThan(String value) {
            addCriterion("not_applicable_reason >", value, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonGreaterThanOrEqualTo(String value) {
            addCriterion("not_applicable_reason >=", value, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonLessThan(String value) {
            addCriterion("not_applicable_reason <", value, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonLessThanOrEqualTo(String value) {
            addCriterion("not_applicable_reason <=", value, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonLike(String value) {
            addCriterion("not_applicable_reason like", value, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonNotLike(String value) {
            addCriterion("not_applicable_reason not like", value, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonIn(List<String> values) {
            addCriterion("not_applicable_reason in", values, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonNotIn(List<String> values) {
            addCriterion("not_applicable_reason not in", values, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonBetween(String value1, String value2) {
            addCriterion("not_applicable_reason between", value1, value2, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andNotApplicableReasonNotBetween(String value1, String value2) {
            addCriterion("not_applicable_reason not between", value1, value2, "notApplicableReason");
            return (Criteria) this;
        }

        public Criteria andApplicableThinkingIsNull() {
            addCriterion("applicable_thinking is null");
            return (Criteria) this;
        }

        public Criteria andApplicableThinkingIsNotNull() {
            addCriterion("applicable_thinking is not null");
            return (Criteria) this;
        }

        public Criteria andApplicableThinkingEqualTo(String value) {
            addCriterion("applicable_thinking =", value, "applicableThinking");
            return (Criteria) this;
        }

        public Criteria andApplicableThinkingNotEqualTo(String value) {
            addCriterion("applicable_thinking <>", value, "applicableThinking");
            return (Criteria) this;
        }

        public Criteria andApplicableThinkingGreaterThan(String value) {
            addCriterion("applicable_thinking >", value, "applicableThinking");
            return (Criteria) this;
        }

        public Criteria andApplicableThinkingGreaterThanOrEqualTo(String value) {
            addCriterion("applicable_thinking >=", value, "applicableThinking");
            return (Criteria) this;
        }

        public Criteria andApplicableThinkingLessThan(String value) {
            addCriterion("applicable_thinking <", value, "applicableThinking");
            return (Criteria) this;
        }

        public Criteria andApplicableThinkingLessThanOrEqualTo(String value) {
            addCriterion("applicable_thinking <=", value, "applicableThinking");
            return (Criteria) this;
        }

        public Criteria andApplicableThinkingLike(String value) {
            addCriterion("applicable_thinking like", value, "applicableThinking");
            return (Criteria) this;
        }

        public Criteria andApplicableThinkingNotLike(String value) {
            addCriterion("applicable_thinking not like", value, "applicableThinking");
            return (Criteria) this;
        }

        public Criteria andApplicableThinkingIn(List<String> values) {
            addCriterion("applicable_thinking in", values, "applicableThinking");
            return (Criteria) this;
        }

        public Criteria andApplicableThinkingNotIn(List<String> values) {
            addCriterion("applicable_thinking not in", values, "applicableThinking");
            return (Criteria) this;
        }

        public Criteria andApplicableThinkingBetween(String value1, String value2) {
            addCriterion("applicable_thinking between", value1, value2, "applicableThinking");
            return (Criteria) this;
        }

        public Criteria andApplicableThinkingNotBetween(String value1, String value2) {
            addCriterion("applicable_thinking not between", value1, value2, "applicableThinking");
            return (Criteria) this;
        }

        public Criteria andNotApplicableThinkingIsNull() {
            addCriterion("not_applicable_thinking is null");
            return (Criteria) this;
        }

        public Criteria andNotApplicableThinkingIsNotNull() {
            addCriterion("not_applicable_thinking is not null");
            return (Criteria) this;
        }

        public Criteria andNotApplicableThinkingEqualTo(String value) {
            addCriterion("not_applicable_thinking =", value, "notApplicableThinking");
            return (Criteria) this;
        }

        public Criteria andNotApplicableThinkingNotEqualTo(String value) {
            addCriterion("not_applicable_thinking <>", value, "notApplicableThinking");
            return (Criteria) this;
        }

        public Criteria andNotApplicableThinkingGreaterThan(String value) {
            addCriterion("not_applicable_thinking >", value, "notApplicableThinking");
            return (Criteria) this;
        }

        public Criteria andNotApplicableThinkingGreaterThanOrEqualTo(String value) {
            addCriterion("not_applicable_thinking >=", value, "notApplicableThinking");
            return (Criteria) this;
        }

        public Criteria andNotApplicableThinkingLessThan(String value) {
            addCriterion("not_applicable_thinking <", value, "notApplicableThinking");
            return (Criteria) this;
        }

        public Criteria andNotApplicableThinkingLessThanOrEqualTo(String value) {
            addCriterion("not_applicable_thinking <=", value, "notApplicableThinking");
            return (Criteria) this;
        }

        public Criteria andNotApplicableThinkingLike(String value) {
            addCriterion("not_applicable_thinking like", value, "notApplicableThinking");
            return (Criteria) this;
        }

        public Criteria andNotApplicableThinkingNotLike(String value) {
            addCriterion("not_applicable_thinking not like", value, "notApplicableThinking");
            return (Criteria) this;
        }

        public Criteria andNotApplicableThinkingIn(List<String> values) {
            addCriterion("not_applicable_thinking in", values, "notApplicableThinking");
            return (Criteria) this;
        }

        public Criteria andNotApplicableThinkingNotIn(List<String> values) {
            addCriterion("not_applicable_thinking not in", values, "notApplicableThinking");
            return (Criteria) this;
        }

        public Criteria andNotApplicableThinkingBetween(String value1, String value2) {
            addCriterion("not_applicable_thinking between", value1, value2, "notApplicableThinking");
            return (Criteria) this;
        }

        public Criteria andNotApplicableThinkingNotBetween(String value1, String value2) {
            addCriterion("not_applicable_thinking not between", value1, value2, "notApplicableThinking");
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