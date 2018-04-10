package com.copower.pmcc.assess.dal.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChksApprovalAssessExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChksApprovalAssessExample() {
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

        public Criteria andUserAccountIsNull() {
            addCriterion("user_account is null");
            return (Criteria) this;
        }

        public Criteria andUserAccountIsNotNull() {
            addCriterion("user_account is not null");
            return (Criteria) this;
        }

        public Criteria andUserAccountEqualTo(String value) {
            addCriterion("user_account =", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountNotEqualTo(String value) {
            addCriterion("user_account <>", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountGreaterThan(String value) {
            addCriterion("user_account >", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountGreaterThanOrEqualTo(String value) {
            addCriterion("user_account >=", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountLessThan(String value) {
            addCriterion("user_account <", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountLessThanOrEqualTo(String value) {
            addCriterion("user_account <=", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountLike(String value) {
            addCriterion("user_account like", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountNotLike(String value) {
            addCriterion("user_account not like", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountIn(List<String> values) {
            addCriterion("user_account in", values, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountNotIn(List<String> values) {
            addCriterion("user_account not in", values, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountBetween(String value1, String value2) {
            addCriterion("user_account between", value1, value2, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountNotBetween(String value1, String value2) {
            addCriterion("user_account not between", value1, value2, "userAccount");
            return (Criteria) this;
        }

        public Criteria andChksApprovalBusinessIdIsNull() {
            addCriterion("chks_approval_business_id is null");
            return (Criteria) this;
        }

        public Criteria andChksApprovalBusinessIdIsNotNull() {
            addCriterion("chks_approval_business_id is not null");
            return (Criteria) this;
        }

        public Criteria andChksApprovalBusinessIdEqualTo(Integer value) {
            addCriterion("chks_approval_business_id =", value, "chksApprovalBusinessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalBusinessIdNotEqualTo(Integer value) {
            addCriterion("chks_approval_business_id <>", value, "chksApprovalBusinessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalBusinessIdGreaterThan(Integer value) {
            addCriterion("chks_approval_business_id >", value, "chksApprovalBusinessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalBusinessIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("chks_approval_business_id >=", value, "chksApprovalBusinessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalBusinessIdLessThan(Integer value) {
            addCriterion("chks_approval_business_id <", value, "chksApprovalBusinessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalBusinessIdLessThanOrEqualTo(Integer value) {
            addCriterion("chks_approval_business_id <=", value, "chksApprovalBusinessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalBusinessIdIn(List<Integer> values) {
            addCriterion("chks_approval_business_id in", values, "chksApprovalBusinessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalBusinessIdNotIn(List<Integer> values) {
            addCriterion("chks_approval_business_id not in", values, "chksApprovalBusinessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalBusinessIdBetween(Integer value1, Integer value2) {
            addCriterion("chks_approval_business_id between", value1, value2, "chksApprovalBusinessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalBusinessIdNotBetween(Integer value1, Integer value2) {
            addCriterion("chks_approval_business_id not between", value1, value2, "chksApprovalBusinessId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalInfoIdIsNull() {
            addCriterion("chks_approval_info_id is null");
            return (Criteria) this;
        }

        public Criteria andChksApprovalInfoIdIsNotNull() {
            addCriterion("chks_approval_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andChksApprovalInfoIdEqualTo(Integer value) {
            addCriterion("chks_approval_info_id =", value, "chksApprovalInfoId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalInfoIdNotEqualTo(Integer value) {
            addCriterion("chks_approval_info_id <>", value, "chksApprovalInfoId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalInfoIdGreaterThan(Integer value) {
            addCriterion("chks_approval_info_id >", value, "chksApprovalInfoId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("chks_approval_info_id >=", value, "chksApprovalInfoId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalInfoIdLessThan(Integer value) {
            addCriterion("chks_approval_info_id <", value, "chksApprovalInfoId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("chks_approval_info_id <=", value, "chksApprovalInfoId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalInfoIdIn(List<Integer> values) {
            addCriterion("chks_approval_info_id in", values, "chksApprovalInfoId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalInfoIdNotIn(List<Integer> values) {
            addCriterion("chks_approval_info_id not in", values, "chksApprovalInfoId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("chks_approval_info_id between", value1, value2, "chksApprovalInfoId");
            return (Criteria) this;
        }

        public Criteria andChksApprovalInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("chks_approval_info_id not between", value1, value2, "chksApprovalInfoId");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNull() {
            addCriterion("total_score is null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNotNull() {
            addCriterion("total_score is not null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreEqualTo(BigDecimal value) {
            addCriterion("total_score =", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotEqualTo(BigDecimal value) {
            addCriterion("total_score <>", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThan(BigDecimal value) {
            addCriterion("total_score >", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_score >=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThan(BigDecimal value) {
            addCriterion("total_score <", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_score <=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIn(List<BigDecimal> values) {
            addCriterion("total_score in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotIn(List<BigDecimal> values) {
            addCriterion("total_score not in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_score between", value1, value2, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_score not between", value1, value2, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalVaildScoreIsNull() {
            addCriterion("total_vaild_score is null");
            return (Criteria) this;
        }

        public Criteria andTotalVaildScoreIsNotNull() {
            addCriterion("total_vaild_score is not null");
            return (Criteria) this;
        }

        public Criteria andTotalVaildScoreEqualTo(BigDecimal value) {
            addCriterion("total_vaild_score =", value, "totalVaildScore");
            return (Criteria) this;
        }

        public Criteria andTotalVaildScoreNotEqualTo(BigDecimal value) {
            addCriterion("total_vaild_score <>", value, "totalVaildScore");
            return (Criteria) this;
        }

        public Criteria andTotalVaildScoreGreaterThan(BigDecimal value) {
            addCriterion("total_vaild_score >", value, "totalVaildScore");
            return (Criteria) this;
        }

        public Criteria andTotalVaildScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_vaild_score >=", value, "totalVaildScore");
            return (Criteria) this;
        }

        public Criteria andTotalVaildScoreLessThan(BigDecimal value) {
            addCriterion("total_vaild_score <", value, "totalVaildScore");
            return (Criteria) this;
        }

        public Criteria andTotalVaildScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_vaild_score <=", value, "totalVaildScore");
            return (Criteria) this;
        }

        public Criteria andTotalVaildScoreIn(List<BigDecimal> values) {
            addCriterion("total_vaild_score in", values, "totalVaildScore");
            return (Criteria) this;
        }

        public Criteria andTotalVaildScoreNotIn(List<BigDecimal> values) {
            addCriterion("total_vaild_score not in", values, "totalVaildScore");
            return (Criteria) this;
        }

        public Criteria andTotalVaildScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_vaild_score between", value1, value2, "totalVaildScore");
            return (Criteria) this;
        }

        public Criteria andTotalVaildScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_vaild_score not between", value1, value2, "totalVaildScore");
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

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andBisRepairIsNull() {
            addCriterion("bis_repair is null");
            return (Criteria) this;
        }

        public Criteria andBisRepairIsNotNull() {
            addCriterion("bis_repair is not null");
            return (Criteria) this;
        }

        public Criteria andBisRepairEqualTo(Boolean value) {
            addCriterion("bis_repair =", value, "bisRepair");
            return (Criteria) this;
        }

        public Criteria andBisRepairNotEqualTo(Boolean value) {
            addCriterion("bis_repair <>", value, "bisRepair");
            return (Criteria) this;
        }

        public Criteria andBisRepairGreaterThan(Boolean value) {
            addCriterion("bis_repair >", value, "bisRepair");
            return (Criteria) this;
        }

        public Criteria andBisRepairGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_repair >=", value, "bisRepair");
            return (Criteria) this;
        }

        public Criteria andBisRepairLessThan(Boolean value) {
            addCriterion("bis_repair <", value, "bisRepair");
            return (Criteria) this;
        }

        public Criteria andBisRepairLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_repair <=", value, "bisRepair");
            return (Criteria) this;
        }

        public Criteria andBisRepairIn(List<Boolean> values) {
            addCriterion("bis_repair in", values, "bisRepair");
            return (Criteria) this;
        }

        public Criteria andBisRepairNotIn(List<Boolean> values) {
            addCriterion("bis_repair not in", values, "bisRepair");
            return (Criteria) this;
        }

        public Criteria andBisRepairBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_repair between", value1, value2, "bisRepair");
            return (Criteria) this;
        }

        public Criteria andBisRepairNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_repair not between", value1, value2, "bisRepair");
            return (Criteria) this;
        }

        public Criteria andBisEnableIsNull() {
            addCriterion("bis_enable is null");
            return (Criteria) this;
        }

        public Criteria andBisEnableIsNotNull() {
            addCriterion("bis_enable is not null");
            return (Criteria) this;
        }

        public Criteria andBisEnableEqualTo(Boolean value) {
            addCriterion("bis_enable =", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableNotEqualTo(Boolean value) {
            addCriterion("bis_enable <>", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableGreaterThan(Boolean value) {
            addCriterion("bis_enable >", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_enable >=", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableLessThan(Boolean value) {
            addCriterion("bis_enable <", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_enable <=", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableIn(List<Boolean> values) {
            addCriterion("bis_enable in", values, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableNotIn(List<Boolean> values) {
            addCriterion("bis_enable not in", values, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_enable between", value1, value2, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_enable not between", value1, value2, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andModifyUserIsNull() {
            addCriterion("modify_user is null");
            return (Criteria) this;
        }

        public Criteria andModifyUserIsNotNull() {
            addCriterion("modify_user is not null");
            return (Criteria) this;
        }

        public Criteria andModifyUserEqualTo(String value) {
            addCriterion("modify_user =", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserNotEqualTo(String value) {
            addCriterion("modify_user <>", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserGreaterThan(String value) {
            addCriterion("modify_user >", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserGreaterThanOrEqualTo(String value) {
            addCriterion("modify_user >=", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserLessThan(String value) {
            addCriterion("modify_user <", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserLessThanOrEqualTo(String value) {
            addCriterion("modify_user <=", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserLike(String value) {
            addCriterion("modify_user like", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserNotLike(String value) {
            addCriterion("modify_user not like", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserIn(List<String> values) {
            addCriterion("modify_user in", values, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserNotIn(List<String> values) {
            addCriterion("modify_user not in", values, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserBetween(String value1, String value2) {
            addCriterion("modify_user between", value1, value2, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserNotBetween(String value1, String value2) {
            addCriterion("modify_user not between", value1, value2, "modifyUser");
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