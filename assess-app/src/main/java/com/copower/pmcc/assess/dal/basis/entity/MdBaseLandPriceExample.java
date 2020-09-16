package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdBaseLandPriceExample {
    /**
     * tb_md_base_land_price
     */
    protected String orderByClause;

    /**
     * tb_md_base_land_price
     */
    protected boolean distinct;

    /**
     * tb_md_base_land_price
     */
    protected List<Criteria> oredCriteria;

    public MdBaseLandPriceExample() {
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
     * tb_md_base_land_price
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

        public Criteria andProcessInsIdIsNull() {
            addCriterion("process_ins_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdIsNotNull() {
            addCriterion("process_ins_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdEqualTo(String value) {
            addCriterion("process_ins_id =", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotEqualTo(String value) {
            addCriterion("process_ins_id <>", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdGreaterThan(String value) {
            addCriterion("process_ins_id >", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdGreaterThanOrEqualTo(String value) {
            addCriterion("process_ins_id >=", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLessThan(String value) {
            addCriterion("process_ins_id <", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLessThanOrEqualTo(String value) {
            addCriterion("process_ins_id <=", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLike(String value) {
            addCriterion("process_ins_id like", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotLike(String value) {
            addCriterion("process_ins_id not like", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdIn(List<String> values) {
            addCriterion("process_ins_id in", values, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotIn(List<String> values) {
            addCriterion("process_ins_id not in", values, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdBetween(String value1, String value2) {
            addCriterion("process_ins_id between", value1, value2, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotBetween(String value1, String value2) {
            addCriterion("process_ins_id not between", value1, value2, "processInsId");
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

        public Criteria andRewardRateIsNull() {
            addCriterion("reward_rate is null");
            return (Criteria) this;
        }

        public Criteria andRewardRateIsNotNull() {
            addCriterion("reward_rate is not null");
            return (Criteria) this;
        }

        public Criteria andRewardRateEqualTo(String value) {
            addCriterion("reward_rate =", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateNotEqualTo(String value) {
            addCriterion("reward_rate <>", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateGreaterThan(String value) {
            addCriterion("reward_rate >", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateGreaterThanOrEqualTo(String value) {
            addCriterion("reward_rate >=", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateLessThan(String value) {
            addCriterion("reward_rate <", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateLessThanOrEqualTo(String value) {
            addCriterion("reward_rate <=", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateLike(String value) {
            addCriterion("reward_rate like", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateNotLike(String value) {
            addCriterion("reward_rate not like", value, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateIn(List<String> values) {
            addCriterion("reward_rate in", values, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateNotIn(List<String> values) {
            addCriterion("reward_rate not in", values, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateBetween(String value1, String value2) {
            addCriterion("reward_rate between", value1, value2, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andRewardRateNotBetween(String value1, String value2) {
            addCriterion("reward_rate not between", value1, value2, "rewardRate");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumIsNull() {
            addCriterion("standard_premium is null");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumIsNotNull() {
            addCriterion("standard_premium is not null");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumEqualTo(BigDecimal value) {
            addCriterion("standard_premium =", value, "standardPremium");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumNotEqualTo(BigDecimal value) {
            addCriterion("standard_premium <>", value, "standardPremium");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumGreaterThan(BigDecimal value) {
            addCriterion("standard_premium >", value, "standardPremium");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("standard_premium >=", value, "standardPremium");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumLessThan(BigDecimal value) {
            addCriterion("standard_premium <", value, "standardPremium");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("standard_premium <=", value, "standardPremium");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumIn(List<BigDecimal> values) {
            addCriterion("standard_premium in", values, "standardPremium");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumNotIn(List<BigDecimal> values) {
            addCriterion("standard_premium not in", values, "standardPremium");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("standard_premium between", value1, value2, "standardPremium");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("standard_premium not between", value1, value2, "standardPremium");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumRemarkIsNull() {
            addCriterion("standard_premium_remark is null");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumRemarkIsNotNull() {
            addCriterion("standard_premium_remark is not null");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumRemarkEqualTo(String value) {
            addCriterion("standard_premium_remark =", value, "standardPremiumRemark");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumRemarkNotEqualTo(String value) {
            addCriterion("standard_premium_remark <>", value, "standardPremiumRemark");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumRemarkGreaterThan(String value) {
            addCriterion("standard_premium_remark >", value, "standardPremiumRemark");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("standard_premium_remark >=", value, "standardPremiumRemark");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumRemarkLessThan(String value) {
            addCriterion("standard_premium_remark <", value, "standardPremiumRemark");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumRemarkLessThanOrEqualTo(String value) {
            addCriterion("standard_premium_remark <=", value, "standardPremiumRemark");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumRemarkLike(String value) {
            addCriterion("standard_premium_remark like", value, "standardPremiumRemark");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumRemarkNotLike(String value) {
            addCriterion("standard_premium_remark not like", value, "standardPremiumRemark");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumRemarkIn(List<String> values) {
            addCriterion("standard_premium_remark in", values, "standardPremiumRemark");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumRemarkNotIn(List<String> values) {
            addCriterion("standard_premium_remark not in", values, "standardPremiumRemark");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumRemarkBetween(String value1, String value2) {
            addCriterion("standard_premium_remark between", value1, value2, "standardPremiumRemark");
            return (Criteria) this;
        }

        public Criteria andStandardPremiumRemarkNotBetween(String value1, String value2) {
            addCriterion("standard_premium_remark not between", value1, value2, "standardPremiumRemark");
            return (Criteria) this;
        }

        public Criteria andLegalAgeIsNull() {
            addCriterion("legal_age is null");
            return (Criteria) this;
        }

        public Criteria andLegalAgeIsNotNull() {
            addCriterion("legal_age is not null");
            return (Criteria) this;
        }

        public Criteria andLegalAgeEqualTo(BigDecimal value) {
            addCriterion("legal_age =", value, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeNotEqualTo(BigDecimal value) {
            addCriterion("legal_age <>", value, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeGreaterThan(BigDecimal value) {
            addCriterion("legal_age >", value, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("legal_age >=", value, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeLessThan(BigDecimal value) {
            addCriterion("legal_age <", value, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("legal_age <=", value, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeIn(List<BigDecimal> values) {
            addCriterion("legal_age in", values, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeNotIn(List<BigDecimal> values) {
            addCriterion("legal_age not in", values, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("legal_age between", value1, value2, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("legal_age not between", value1, value2, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeRemarkIsNull() {
            addCriterion("legal_age_remark is null");
            return (Criteria) this;
        }

        public Criteria andLegalAgeRemarkIsNotNull() {
            addCriterion("legal_age_remark is not null");
            return (Criteria) this;
        }

        public Criteria andLegalAgeRemarkEqualTo(String value) {
            addCriterion("legal_age_remark =", value, "legalAgeRemark");
            return (Criteria) this;
        }

        public Criteria andLegalAgeRemarkNotEqualTo(String value) {
            addCriterion("legal_age_remark <>", value, "legalAgeRemark");
            return (Criteria) this;
        }

        public Criteria andLegalAgeRemarkGreaterThan(String value) {
            addCriterion("legal_age_remark >", value, "legalAgeRemark");
            return (Criteria) this;
        }

        public Criteria andLegalAgeRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("legal_age_remark >=", value, "legalAgeRemark");
            return (Criteria) this;
        }

        public Criteria andLegalAgeRemarkLessThan(String value) {
            addCriterion("legal_age_remark <", value, "legalAgeRemark");
            return (Criteria) this;
        }

        public Criteria andLegalAgeRemarkLessThanOrEqualTo(String value) {
            addCriterion("legal_age_remark <=", value, "legalAgeRemark");
            return (Criteria) this;
        }

        public Criteria andLegalAgeRemarkLike(String value) {
            addCriterion("legal_age_remark like", value, "legalAgeRemark");
            return (Criteria) this;
        }

        public Criteria andLegalAgeRemarkNotLike(String value) {
            addCriterion("legal_age_remark not like", value, "legalAgeRemark");
            return (Criteria) this;
        }

        public Criteria andLegalAgeRemarkIn(List<String> values) {
            addCriterion("legal_age_remark in", values, "legalAgeRemark");
            return (Criteria) this;
        }

        public Criteria andLegalAgeRemarkNotIn(List<String> values) {
            addCriterion("legal_age_remark not in", values, "legalAgeRemark");
            return (Criteria) this;
        }

        public Criteria andLegalAgeRemarkBetween(String value1, String value2) {
            addCriterion("legal_age_remark between", value1, value2, "legalAgeRemark");
            return (Criteria) this;
        }

        public Criteria andLegalAgeRemarkNotBetween(String value1, String value2) {
            addCriterion("legal_age_remark not between", value1, value2, "legalAgeRemark");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearIsNull() {
            addCriterion("land_surplus_year is null");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearIsNotNull() {
            addCriterion("land_surplus_year is not null");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearEqualTo(BigDecimal value) {
            addCriterion("land_surplus_year =", value, "landSurplusYear");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearNotEqualTo(BigDecimal value) {
            addCriterion("land_surplus_year <>", value, "landSurplusYear");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearGreaterThan(BigDecimal value) {
            addCriterion("land_surplus_year >", value, "landSurplusYear");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_surplus_year >=", value, "landSurplusYear");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearLessThan(BigDecimal value) {
            addCriterion("land_surplus_year <", value, "landSurplusYear");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_surplus_year <=", value, "landSurplusYear");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearIn(List<BigDecimal> values) {
            addCriterion("land_surplus_year in", values, "landSurplusYear");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearNotIn(List<BigDecimal> values) {
            addCriterion("land_surplus_year not in", values, "landSurplusYear");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_surplus_year between", value1, value2, "landSurplusYear");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_surplus_year not between", value1, value2, "landSurplusYear");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearRemarkIsNull() {
            addCriterion("land_surplus_year_remark is null");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearRemarkIsNotNull() {
            addCriterion("land_surplus_year_remark is not null");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearRemarkEqualTo(String value) {
            addCriterion("land_surplus_year_remark =", value, "landSurplusYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearRemarkNotEqualTo(String value) {
            addCriterion("land_surplus_year_remark <>", value, "landSurplusYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearRemarkGreaterThan(String value) {
            addCriterion("land_surplus_year_remark >", value, "landSurplusYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("land_surplus_year_remark >=", value, "landSurplusYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearRemarkLessThan(String value) {
            addCriterion("land_surplus_year_remark <", value, "landSurplusYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearRemarkLessThanOrEqualTo(String value) {
            addCriterion("land_surplus_year_remark <=", value, "landSurplusYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearRemarkLike(String value) {
            addCriterion("land_surplus_year_remark like", value, "landSurplusYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearRemarkNotLike(String value) {
            addCriterion("land_surplus_year_remark not like", value, "landSurplusYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearRemarkIn(List<String> values) {
            addCriterion("land_surplus_year_remark in", values, "landSurplusYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearRemarkNotIn(List<String> values) {
            addCriterion("land_surplus_year_remark not in", values, "landSurplusYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearRemarkBetween(String value1, String value2) {
            addCriterion("land_surplus_year_remark between", value1, value2, "landSurplusYearRemark");
            return (Criteria) this;
        }

        public Criteria andLandSurplusYearRemarkNotBetween(String value1, String value2) {
            addCriterion("land_surplus_year_remark not between", value1, value2, "landSurplusYearRemark");
            return (Criteria) this;
        }

        public Criteria andPeriodAmendIsNull() {
            addCriterion("period_amend is null");
            return (Criteria) this;
        }

        public Criteria andPeriodAmendIsNotNull() {
            addCriterion("period_amend is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodAmendEqualTo(BigDecimal value) {
            addCriterion("period_amend =", value, "periodAmend");
            return (Criteria) this;
        }

        public Criteria andPeriodAmendNotEqualTo(BigDecimal value) {
            addCriterion("period_amend <>", value, "periodAmend");
            return (Criteria) this;
        }

        public Criteria andPeriodAmendGreaterThan(BigDecimal value) {
            addCriterion("period_amend >", value, "periodAmend");
            return (Criteria) this;
        }

        public Criteria andPeriodAmendGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("period_amend >=", value, "periodAmend");
            return (Criteria) this;
        }

        public Criteria andPeriodAmendLessThan(BigDecimal value) {
            addCriterion("period_amend <", value, "periodAmend");
            return (Criteria) this;
        }

        public Criteria andPeriodAmendLessThanOrEqualTo(BigDecimal value) {
            addCriterion("period_amend <=", value, "periodAmend");
            return (Criteria) this;
        }

        public Criteria andPeriodAmendIn(List<BigDecimal> values) {
            addCriterion("period_amend in", values, "periodAmend");
            return (Criteria) this;
        }

        public Criteria andPeriodAmendNotIn(List<BigDecimal> values) {
            addCriterion("period_amend not in", values, "periodAmend");
            return (Criteria) this;
        }

        public Criteria andPeriodAmendBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("period_amend between", value1, value2, "periodAmend");
            return (Criteria) this;
        }

        public Criteria andPeriodAmendNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("period_amend not between", value1, value2, "periodAmend");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectIsNull() {
            addCriterion("develop_correct is null");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectIsNotNull() {
            addCriterion("develop_correct is not null");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectEqualTo(BigDecimal value) {
            addCriterion("develop_correct =", value, "developCorrect");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectNotEqualTo(BigDecimal value) {
            addCriterion("develop_correct <>", value, "developCorrect");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectGreaterThan(BigDecimal value) {
            addCriterion("develop_correct >", value, "developCorrect");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("develop_correct >=", value, "developCorrect");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectLessThan(BigDecimal value) {
            addCriterion("develop_correct <", value, "developCorrect");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectLessThanOrEqualTo(BigDecimal value) {
            addCriterion("develop_correct <=", value, "developCorrect");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectIn(List<BigDecimal> values) {
            addCriterion("develop_correct in", values, "developCorrect");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectNotIn(List<BigDecimal> values) {
            addCriterion("develop_correct not in", values, "developCorrect");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("develop_correct between", value1, value2, "developCorrect");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("develop_correct not between", value1, value2, "developCorrect");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectRemarkIsNull() {
            addCriterion("develop_correct_remark is null");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectRemarkIsNotNull() {
            addCriterion("develop_correct_remark is not null");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectRemarkEqualTo(String value) {
            addCriterion("develop_correct_remark =", value, "developCorrectRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectRemarkNotEqualTo(String value) {
            addCriterion("develop_correct_remark <>", value, "developCorrectRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectRemarkGreaterThan(String value) {
            addCriterion("develop_correct_remark >", value, "developCorrectRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("develop_correct_remark >=", value, "developCorrectRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectRemarkLessThan(String value) {
            addCriterion("develop_correct_remark <", value, "developCorrectRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectRemarkLessThanOrEqualTo(String value) {
            addCriterion("develop_correct_remark <=", value, "developCorrectRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectRemarkLike(String value) {
            addCriterion("develop_correct_remark like", value, "developCorrectRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectRemarkNotLike(String value) {
            addCriterion("develop_correct_remark not like", value, "developCorrectRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectRemarkIn(List<String> values) {
            addCriterion("develop_correct_remark in", values, "developCorrectRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectRemarkNotIn(List<String> values) {
            addCriterion("develop_correct_remark not in", values, "developCorrectRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectRemarkBetween(String value1, String value2) {
            addCriterion("develop_correct_remark between", value1, value2, "developCorrectRemark");
            return (Criteria) this;
        }

        public Criteria andDevelopCorrectRemarkNotBetween(String value1, String value2) {
            addCriterion("develop_correct_remark not between", value1, value2, "developCorrectRemark");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateIsNull() {
            addCriterion("volumetric_rate is null");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateIsNotNull() {
            addCriterion("volumetric_rate is not null");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateEqualTo(BigDecimal value) {
            addCriterion("volumetric_rate =", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateNotEqualTo(BigDecimal value) {
            addCriterion("volumetric_rate <>", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateGreaterThan(BigDecimal value) {
            addCriterion("volumetric_rate >", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("volumetric_rate >=", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateLessThan(BigDecimal value) {
            addCriterion("volumetric_rate <", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("volumetric_rate <=", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateIn(List<BigDecimal> values) {
            addCriterion("volumetric_rate in", values, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateNotIn(List<BigDecimal> values) {
            addCriterion("volumetric_rate not in", values, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("volumetric_rate between", value1, value2, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("volumetric_rate not between", value1, value2, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateRemarkIsNull() {
            addCriterion("volumetric_rate_remark is null");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateRemarkIsNotNull() {
            addCriterion("volumetric_rate_remark is not null");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateRemarkEqualTo(String value) {
            addCriterion("volumetric_rate_remark =", value, "volumetricRateRemark");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateRemarkNotEqualTo(String value) {
            addCriterion("volumetric_rate_remark <>", value, "volumetricRateRemark");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateRemarkGreaterThan(String value) {
            addCriterion("volumetric_rate_remark >", value, "volumetricRateRemark");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("volumetric_rate_remark >=", value, "volumetricRateRemark");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateRemarkLessThan(String value) {
            addCriterion("volumetric_rate_remark <", value, "volumetricRateRemark");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateRemarkLessThanOrEqualTo(String value) {
            addCriterion("volumetric_rate_remark <=", value, "volumetricRateRemark");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateRemarkLike(String value) {
            addCriterion("volumetric_rate_remark like", value, "volumetricRateRemark");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateRemarkNotLike(String value) {
            addCriterion("volumetric_rate_remark not like", value, "volumetricRateRemark");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateRemarkIn(List<String> values) {
            addCriterion("volumetric_rate_remark in", values, "volumetricRateRemark");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateRemarkNotIn(List<String> values) {
            addCriterion("volumetric_rate_remark not in", values, "volumetricRateRemark");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateRemarkBetween(String value1, String value2) {
            addCriterion("volumetric_rate_remark between", value1, value2, "volumetricRateRemark");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateRemarkNotBetween(String value1, String value2) {
            addCriterion("volumetric_rate_remark not between", value1, value2, "volumetricRateRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaIsNull() {
            addCriterion("evaluation_area is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaIsNotNull() {
            addCriterion("evaluation_area is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaEqualTo(BigDecimal value) {
            addCriterion("evaluation_area =", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaNotEqualTo(BigDecimal value) {
            addCriterion("evaluation_area <>", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaGreaterThan(BigDecimal value) {
            addCriterion("evaluation_area >", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("evaluation_area >=", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaLessThan(BigDecimal value) {
            addCriterion("evaluation_area <", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("evaluation_area <=", value, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaIn(List<BigDecimal> values) {
            addCriterion("evaluation_area in", values, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaNotIn(List<BigDecimal> values) {
            addCriterion("evaluation_area not in", values, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("evaluation_area between", value1, value2, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("evaluation_area not between", value1, value2, "evaluationArea");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaRemarkIsNull() {
            addCriterion("evaluation_area_remark is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaRemarkIsNotNull() {
            addCriterion("evaluation_area_remark is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaRemarkEqualTo(String value) {
            addCriterion("evaluation_area_remark =", value, "evaluationAreaRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaRemarkNotEqualTo(String value) {
            addCriterion("evaluation_area_remark <>", value, "evaluationAreaRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaRemarkGreaterThan(String value) {
            addCriterion("evaluation_area_remark >", value, "evaluationAreaRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("evaluation_area_remark >=", value, "evaluationAreaRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaRemarkLessThan(String value) {
            addCriterion("evaluation_area_remark <", value, "evaluationAreaRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaRemarkLessThanOrEqualTo(String value) {
            addCriterion("evaluation_area_remark <=", value, "evaluationAreaRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaRemarkLike(String value) {
            addCriterion("evaluation_area_remark like", value, "evaluationAreaRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaRemarkNotLike(String value) {
            addCriterion("evaluation_area_remark not like", value, "evaluationAreaRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaRemarkIn(List<String> values) {
            addCriterion("evaluation_area_remark in", values, "evaluationAreaRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaRemarkNotIn(List<String> values) {
            addCriterion("evaluation_area_remark not in", values, "evaluationAreaRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaRemarkBetween(String value1, String value2) {
            addCriterion("evaluation_area_remark between", value1, value2, "evaluationAreaRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluationAreaRemarkNotBetween(String value1, String value2) {
            addCriterion("evaluation_area_remark not between", value1, value2, "evaluationAreaRemark");
            return (Criteria) this;
        }

        public Criteria andDateAmendIsNull() {
            addCriterion("date_amend is null");
            return (Criteria) this;
        }

        public Criteria andDateAmendIsNotNull() {
            addCriterion("date_amend is not null");
            return (Criteria) this;
        }

        public Criteria andDateAmendEqualTo(BigDecimal value) {
            addCriterion("date_amend =", value, "dateAmend");
            return (Criteria) this;
        }

        public Criteria andDateAmendNotEqualTo(BigDecimal value) {
            addCriterion("date_amend <>", value, "dateAmend");
            return (Criteria) this;
        }

        public Criteria andDateAmendGreaterThan(BigDecimal value) {
            addCriterion("date_amend >", value, "dateAmend");
            return (Criteria) this;
        }

        public Criteria andDateAmendGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("date_amend >=", value, "dateAmend");
            return (Criteria) this;
        }

        public Criteria andDateAmendLessThan(BigDecimal value) {
            addCriterion("date_amend <", value, "dateAmend");
            return (Criteria) this;
        }

        public Criteria andDateAmendLessThanOrEqualTo(BigDecimal value) {
            addCriterion("date_amend <=", value, "dateAmend");
            return (Criteria) this;
        }

        public Criteria andDateAmendIn(List<BigDecimal> values) {
            addCriterion("date_amend in", values, "dateAmend");
            return (Criteria) this;
        }

        public Criteria andDateAmendNotIn(List<BigDecimal> values) {
            addCriterion("date_amend not in", values, "dateAmend");
            return (Criteria) this;
        }

        public Criteria andDateAmendBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("date_amend between", value1, value2, "dateAmend");
            return (Criteria) this;
        }

        public Criteria andDateAmendNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("date_amend not between", value1, value2, "dateAmend");
            return (Criteria) this;
        }

        public Criteria andVolumeFractionAmendIsNull() {
            addCriterion("volume_fraction_amend is null");
            return (Criteria) this;
        }

        public Criteria andVolumeFractionAmendIsNotNull() {
            addCriterion("volume_fraction_amend is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeFractionAmendEqualTo(BigDecimal value) {
            addCriterion("volume_fraction_amend =", value, "volumeFractionAmend");
            return (Criteria) this;
        }

        public Criteria andVolumeFractionAmendNotEqualTo(BigDecimal value) {
            addCriterion("volume_fraction_amend <>", value, "volumeFractionAmend");
            return (Criteria) this;
        }

        public Criteria andVolumeFractionAmendGreaterThan(BigDecimal value) {
            addCriterion("volume_fraction_amend >", value, "volumeFractionAmend");
            return (Criteria) this;
        }

        public Criteria andVolumeFractionAmendGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("volume_fraction_amend >=", value, "volumeFractionAmend");
            return (Criteria) this;
        }

        public Criteria andVolumeFractionAmendLessThan(BigDecimal value) {
            addCriterion("volume_fraction_amend <", value, "volumeFractionAmend");
            return (Criteria) this;
        }

        public Criteria andVolumeFractionAmendLessThanOrEqualTo(BigDecimal value) {
            addCriterion("volume_fraction_amend <=", value, "volumeFractionAmend");
            return (Criteria) this;
        }

        public Criteria andVolumeFractionAmendIn(List<BigDecimal> values) {
            addCriterion("volume_fraction_amend in", values, "volumeFractionAmend");
            return (Criteria) this;
        }

        public Criteria andVolumeFractionAmendNotIn(List<BigDecimal> values) {
            addCriterion("volume_fraction_amend not in", values, "volumeFractionAmend");
            return (Criteria) this;
        }

        public Criteria andVolumeFractionAmendBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("volume_fraction_amend between", value1, value2, "volumeFractionAmend");
            return (Criteria) this;
        }

        public Criteria andVolumeFractionAmendNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("volume_fraction_amend not between", value1, value2, "volumeFractionAmend");
            return (Criteria) this;
        }

        public Criteria andAreaAndSeveralAmendIsNull() {
            addCriterion("area_and_several_amend is null");
            return (Criteria) this;
        }

        public Criteria andAreaAndSeveralAmendIsNotNull() {
            addCriterion("area_and_several_amend is not null");
            return (Criteria) this;
        }

        public Criteria andAreaAndSeveralAmendEqualTo(BigDecimal value) {
            addCriterion("area_and_several_amend =", value, "areaAndSeveralAmend");
            return (Criteria) this;
        }

        public Criteria andAreaAndSeveralAmendNotEqualTo(BigDecimal value) {
            addCriterion("area_and_several_amend <>", value, "areaAndSeveralAmend");
            return (Criteria) this;
        }

        public Criteria andAreaAndSeveralAmendGreaterThan(BigDecimal value) {
            addCriterion("area_and_several_amend >", value, "areaAndSeveralAmend");
            return (Criteria) this;
        }

        public Criteria andAreaAndSeveralAmendGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("area_and_several_amend >=", value, "areaAndSeveralAmend");
            return (Criteria) this;
        }

        public Criteria andAreaAndSeveralAmendLessThan(BigDecimal value) {
            addCriterion("area_and_several_amend <", value, "areaAndSeveralAmend");
            return (Criteria) this;
        }

        public Criteria andAreaAndSeveralAmendLessThanOrEqualTo(BigDecimal value) {
            addCriterion("area_and_several_amend <=", value, "areaAndSeveralAmend");
            return (Criteria) this;
        }

        public Criteria andAreaAndSeveralAmendIn(List<BigDecimal> values) {
            addCriterion("area_and_several_amend in", values, "areaAndSeveralAmend");
            return (Criteria) this;
        }

        public Criteria andAreaAndSeveralAmendNotIn(List<BigDecimal> values) {
            addCriterion("area_and_several_amend not in", values, "areaAndSeveralAmend");
            return (Criteria) this;
        }

        public Criteria andAreaAndSeveralAmendBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("area_and_several_amend between", value1, value2, "areaAndSeveralAmend");
            return (Criteria) this;
        }

        public Criteria andAreaAndSeveralAmendNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("area_and_several_amend not between", value1, value2, "areaAndSeveralAmend");
            return (Criteria) this;
        }

        public Criteria andParcelPriceIsNull() {
            addCriterion("parcel_price is null");
            return (Criteria) this;
        }

        public Criteria andParcelPriceIsNotNull() {
            addCriterion("parcel_price is not null");
            return (Criteria) this;
        }

        public Criteria andParcelPriceEqualTo(BigDecimal value) {
            addCriterion("parcel_price =", value, "parcelPrice");
            return (Criteria) this;
        }

        public Criteria andParcelPriceNotEqualTo(BigDecimal value) {
            addCriterion("parcel_price <>", value, "parcelPrice");
            return (Criteria) this;
        }

        public Criteria andParcelPriceGreaterThan(BigDecimal value) {
            addCriterion("parcel_price >", value, "parcelPrice");
            return (Criteria) this;
        }

        public Criteria andParcelPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("parcel_price >=", value, "parcelPrice");
            return (Criteria) this;
        }

        public Criteria andParcelPriceLessThan(BigDecimal value) {
            addCriterion("parcel_price <", value, "parcelPrice");
            return (Criteria) this;
        }

        public Criteria andParcelPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("parcel_price <=", value, "parcelPrice");
            return (Criteria) this;
        }

        public Criteria andParcelPriceIn(List<BigDecimal> values) {
            addCriterion("parcel_price in", values, "parcelPrice");
            return (Criteria) this;
        }

        public Criteria andParcelPriceNotIn(List<BigDecimal> values) {
            addCriterion("parcel_price not in", values, "parcelPrice");
            return (Criteria) this;
        }

        public Criteria andParcelPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("parcel_price between", value1, value2, "parcelPrice");
            return (Criteria) this;
        }

        public Criteria andParcelPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("parcel_price not between", value1, value2, "parcelPrice");
            return (Criteria) this;
        }

        public Criteria andParcelBhouPriceIsNull() {
            addCriterion("parcel_bhou_price is null");
            return (Criteria) this;
        }

        public Criteria andParcelBhouPriceIsNotNull() {
            addCriterion("parcel_bhou_price is not null");
            return (Criteria) this;
        }

        public Criteria andParcelBhouPriceEqualTo(BigDecimal value) {
            addCriterion("parcel_bhou_price =", value, "parcelBhouPrice");
            return (Criteria) this;
        }

        public Criteria andParcelBhouPriceNotEqualTo(BigDecimal value) {
            addCriterion("parcel_bhou_price <>", value, "parcelBhouPrice");
            return (Criteria) this;
        }

        public Criteria andParcelBhouPriceGreaterThan(BigDecimal value) {
            addCriterion("parcel_bhou_price >", value, "parcelBhouPrice");
            return (Criteria) this;
        }

        public Criteria andParcelBhouPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("parcel_bhou_price >=", value, "parcelBhouPrice");
            return (Criteria) this;
        }

        public Criteria andParcelBhouPriceLessThan(BigDecimal value) {
            addCriterion("parcel_bhou_price <", value, "parcelBhouPrice");
            return (Criteria) this;
        }

        public Criteria andParcelBhouPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("parcel_bhou_price <=", value, "parcelBhouPrice");
            return (Criteria) this;
        }

        public Criteria andParcelBhouPriceIn(List<BigDecimal> values) {
            addCriterion("parcel_bhou_price in", values, "parcelBhouPrice");
            return (Criteria) this;
        }

        public Criteria andParcelBhouPriceNotIn(List<BigDecimal> values) {
            addCriterion("parcel_bhou_price not in", values, "parcelBhouPrice");
            return (Criteria) this;
        }

        public Criteria andParcelBhouPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("parcel_bhou_price between", value1, value2, "parcelBhouPrice");
            return (Criteria) this;
        }

        public Criteria andParcelBhouPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("parcel_bhou_price not between", value1, value2, "parcelBhouPrice");
            return (Criteria) this;
        }

        public Criteria andParcelTotalPriceIsNull() {
            addCriterion("parcel_total_price is null");
            return (Criteria) this;
        }

        public Criteria andParcelTotalPriceIsNotNull() {
            addCriterion("parcel_total_price is not null");
            return (Criteria) this;
        }

        public Criteria andParcelTotalPriceEqualTo(BigDecimal value) {
            addCriterion("parcel_total_price =", value, "parcelTotalPrice");
            return (Criteria) this;
        }

        public Criteria andParcelTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("parcel_total_price <>", value, "parcelTotalPrice");
            return (Criteria) this;
        }

        public Criteria andParcelTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("parcel_total_price >", value, "parcelTotalPrice");
            return (Criteria) this;
        }

        public Criteria andParcelTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("parcel_total_price >=", value, "parcelTotalPrice");
            return (Criteria) this;
        }

        public Criteria andParcelTotalPriceLessThan(BigDecimal value) {
            addCriterion("parcel_total_price <", value, "parcelTotalPrice");
            return (Criteria) this;
        }

        public Criteria andParcelTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("parcel_total_price <=", value, "parcelTotalPrice");
            return (Criteria) this;
        }

        public Criteria andParcelTotalPriceIn(List<BigDecimal> values) {
            addCriterion("parcel_total_price in", values, "parcelTotalPrice");
            return (Criteria) this;
        }

        public Criteria andParcelTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("parcel_total_price not in", values, "parcelTotalPrice");
            return (Criteria) this;
        }

        public Criteria andParcelTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("parcel_total_price between", value1, value2, "parcelTotalPrice");
            return (Criteria) this;
        }

        public Criteria andParcelTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("parcel_total_price not between", value1, value2, "parcelTotalPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumIsNull() {
            addCriterion("floor_premium is null");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumIsNotNull() {
            addCriterion("floor_premium is not null");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumEqualTo(BigDecimal value) {
            addCriterion("floor_premium =", value, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumNotEqualTo(BigDecimal value) {
            addCriterion("floor_premium <>", value, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumGreaterThan(BigDecimal value) {
            addCriterion("floor_premium >", value, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_premium >=", value, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumLessThan(BigDecimal value) {
            addCriterion("floor_premium <", value, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_premium <=", value, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumIn(List<BigDecimal> values) {
            addCriterion("floor_premium in", values, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumNotIn(List<BigDecimal> values) {
            addCriterion("floor_premium not in", values, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_premium between", value1, value2, "floorPremium");
            return (Criteria) this;
        }

        public Criteria andFloorPremiumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_premium not between", value1, value2, "floorPremium");
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

    /**
     * tb_md_base_land_price
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