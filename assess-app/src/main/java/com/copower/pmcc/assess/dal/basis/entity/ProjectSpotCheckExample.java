package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectSpotCheckExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectSpotCheckExample() {
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andBySpotUserIsNull() {
            addCriterion("by_spot_user is null");
            return (Criteria) this;
        }

        public Criteria andBySpotUserIsNotNull() {
            addCriterion("by_spot_user is not null");
            return (Criteria) this;
        }

        public Criteria andBySpotUserEqualTo(String value) {
            addCriterion("by_spot_user =", value, "bySpotUser");
            return (Criteria) this;
        }

        public Criteria andBySpotUserNotEqualTo(String value) {
            addCriterion("by_spot_user <>", value, "bySpotUser");
            return (Criteria) this;
        }

        public Criteria andBySpotUserGreaterThan(String value) {
            addCriterion("by_spot_user >", value, "bySpotUser");
            return (Criteria) this;
        }

        public Criteria andBySpotUserGreaterThanOrEqualTo(String value) {
            addCriterion("by_spot_user >=", value, "bySpotUser");
            return (Criteria) this;
        }

        public Criteria andBySpotUserLessThan(String value) {
            addCriterion("by_spot_user <", value, "bySpotUser");
            return (Criteria) this;
        }

        public Criteria andBySpotUserLessThanOrEqualTo(String value) {
            addCriterion("by_spot_user <=", value, "bySpotUser");
            return (Criteria) this;
        }

        public Criteria andBySpotUserLike(String value) {
            addCriterion("by_spot_user like", value, "bySpotUser");
            return (Criteria) this;
        }

        public Criteria andBySpotUserNotLike(String value) {
            addCriterion("by_spot_user not like", value, "bySpotUser");
            return (Criteria) this;
        }

        public Criteria andBySpotUserIn(List<String> values) {
            addCriterion("by_spot_user in", values, "bySpotUser");
            return (Criteria) this;
        }

        public Criteria andBySpotUserNotIn(List<String> values) {
            addCriterion("by_spot_user not in", values, "bySpotUser");
            return (Criteria) this;
        }

        public Criteria andBySpotUserBetween(String value1, String value2) {
            addCriterion("by_spot_user between", value1, value2, "bySpotUser");
            return (Criteria) this;
        }

        public Criteria andBySpotUserNotBetween(String value1, String value2) {
            addCriterion("by_spot_user not between", value1, value2, "bySpotUser");
            return (Criteria) this;
        }

        public Criteria andSpotMonthIsNull() {
            addCriterion("spot_month is null");
            return (Criteria) this;
        }

        public Criteria andSpotMonthIsNotNull() {
            addCriterion("spot_month is not null");
            return (Criteria) this;
        }

        public Criteria andSpotMonthEqualTo(String value) {
            addCriterion("spot_month =", value, "spotMonth");
            return (Criteria) this;
        }

        public Criteria andSpotMonthNotEqualTo(String value) {
            addCriterion("spot_month <>", value, "spotMonth");
            return (Criteria) this;
        }

        public Criteria andSpotMonthGreaterThan(String value) {
            addCriterion("spot_month >", value, "spotMonth");
            return (Criteria) this;
        }

        public Criteria andSpotMonthGreaterThanOrEqualTo(String value) {
            addCriterion("spot_month >=", value, "spotMonth");
            return (Criteria) this;
        }

        public Criteria andSpotMonthLessThan(String value) {
            addCriterion("spot_month <", value, "spotMonth");
            return (Criteria) this;
        }

        public Criteria andSpotMonthLessThanOrEqualTo(String value) {
            addCriterion("spot_month <=", value, "spotMonth");
            return (Criteria) this;
        }

        public Criteria andSpotMonthLike(String value) {
            addCriterion("spot_month like", value, "spotMonth");
            return (Criteria) this;
        }

        public Criteria andSpotMonthNotLike(String value) {
            addCriterion("spot_month not like", value, "spotMonth");
            return (Criteria) this;
        }

        public Criteria andSpotMonthIn(List<String> values) {
            addCriterion("spot_month in", values, "spotMonth");
            return (Criteria) this;
        }

        public Criteria andSpotMonthNotIn(List<String> values) {
            addCriterion("spot_month not in", values, "spotMonth");
            return (Criteria) this;
        }

        public Criteria andSpotMonthBetween(String value1, String value2) {
            addCriterion("spot_month between", value1, value2, "spotMonth");
            return (Criteria) this;
        }

        public Criteria andSpotMonthNotBetween(String value1, String value2) {
            addCriterion("spot_month not between", value1, value2, "spotMonth");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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

        public Criteria andPlanDetailsCountIsNull() {
            addCriterion("plan_details_count is null");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsCountIsNotNull() {
            addCriterion("plan_details_count is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsCountEqualTo(Integer value) {
            addCriterion("plan_details_count =", value, "planDetailsCount");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsCountNotEqualTo(Integer value) {
            addCriterion("plan_details_count <>", value, "planDetailsCount");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsCountGreaterThan(Integer value) {
            addCriterion("plan_details_count >", value, "planDetailsCount");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_details_count >=", value, "planDetailsCount");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsCountLessThan(Integer value) {
            addCriterion("plan_details_count <", value, "planDetailsCount");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsCountLessThanOrEqualTo(Integer value) {
            addCriterion("plan_details_count <=", value, "planDetailsCount");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsCountIn(List<Integer> values) {
            addCriterion("plan_details_count in", values, "planDetailsCount");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsCountNotIn(List<Integer> values) {
            addCriterion("plan_details_count not in", values, "planDetailsCount");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsCountBetween(Integer value1, Integer value2) {
            addCriterion("plan_details_count between", value1, value2, "planDetailsCount");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsCountNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_details_count not between", value1, value2, "planDetailsCount");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsContentIsNull() {
            addCriterion("plan_details_content is null");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsContentIsNotNull() {
            addCriterion("plan_details_content is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsContentEqualTo(String value) {
            addCriterion("plan_details_content =", value, "planDetailsContent");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsContentNotEqualTo(String value) {
            addCriterion("plan_details_content <>", value, "planDetailsContent");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsContentGreaterThan(String value) {
            addCriterion("plan_details_content >", value, "planDetailsContent");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsContentGreaterThanOrEqualTo(String value) {
            addCriterion("plan_details_content >=", value, "planDetailsContent");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsContentLessThan(String value) {
            addCriterion("plan_details_content <", value, "planDetailsContent");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsContentLessThanOrEqualTo(String value) {
            addCriterion("plan_details_content <=", value, "planDetailsContent");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsContentLike(String value) {
            addCriterion("plan_details_content like", value, "planDetailsContent");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsContentNotLike(String value) {
            addCriterion("plan_details_content not like", value, "planDetailsContent");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsContentIn(List<String> values) {
            addCriterion("plan_details_content in", values, "planDetailsContent");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsContentNotIn(List<String> values) {
            addCriterion("plan_details_content not in", values, "planDetailsContent");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsContentBetween(String value1, String value2) {
            addCriterion("plan_details_content between", value1, value2, "planDetailsContent");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsContentNotBetween(String value1, String value2) {
            addCriterion("plan_details_content not between", value1, value2, "planDetailsContent");
            return (Criteria) this;
        }

        public Criteria andWorkHourStandardScoreIsNull() {
            addCriterion("work_hour_standard_score is null");
            return (Criteria) this;
        }

        public Criteria andWorkHourStandardScoreIsNotNull() {
            addCriterion("work_hour_standard_score is not null");
            return (Criteria) this;
        }

        public Criteria andWorkHourStandardScoreEqualTo(BigDecimal value) {
            addCriterion("work_hour_standard_score =", value, "workHourStandardScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourStandardScoreNotEqualTo(BigDecimal value) {
            addCriterion("work_hour_standard_score <>", value, "workHourStandardScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourStandardScoreGreaterThan(BigDecimal value) {
            addCriterion("work_hour_standard_score >", value, "workHourStandardScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourStandardScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("work_hour_standard_score >=", value, "workHourStandardScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourStandardScoreLessThan(BigDecimal value) {
            addCriterion("work_hour_standard_score <", value, "workHourStandardScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourStandardScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("work_hour_standard_score <=", value, "workHourStandardScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourStandardScoreIn(List<BigDecimal> values) {
            addCriterion("work_hour_standard_score in", values, "workHourStandardScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourStandardScoreNotIn(List<BigDecimal> values) {
            addCriterion("work_hour_standard_score not in", values, "workHourStandardScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourStandardScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("work_hour_standard_score between", value1, value2, "workHourStandardScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourStandardScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("work_hour_standard_score not between", value1, value2, "workHourStandardScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourRatioIsNull() {
            addCriterion("work_hour_ratio is null");
            return (Criteria) this;
        }

        public Criteria andWorkHourRatioIsNotNull() {
            addCriterion("work_hour_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andWorkHourRatioEqualTo(BigDecimal value) {
            addCriterion("work_hour_ratio =", value, "workHourRatio");
            return (Criteria) this;
        }

        public Criteria andWorkHourRatioNotEqualTo(BigDecimal value) {
            addCriterion("work_hour_ratio <>", value, "workHourRatio");
            return (Criteria) this;
        }

        public Criteria andWorkHourRatioGreaterThan(BigDecimal value) {
            addCriterion("work_hour_ratio >", value, "workHourRatio");
            return (Criteria) this;
        }

        public Criteria andWorkHourRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("work_hour_ratio >=", value, "workHourRatio");
            return (Criteria) this;
        }

        public Criteria andWorkHourRatioLessThan(BigDecimal value) {
            addCriterion("work_hour_ratio <", value, "workHourRatio");
            return (Criteria) this;
        }

        public Criteria andWorkHourRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("work_hour_ratio <=", value, "workHourRatio");
            return (Criteria) this;
        }

        public Criteria andWorkHourRatioIn(List<BigDecimal> values) {
            addCriterion("work_hour_ratio in", values, "workHourRatio");
            return (Criteria) this;
        }

        public Criteria andWorkHourRatioNotIn(List<BigDecimal> values) {
            addCriterion("work_hour_ratio not in", values, "workHourRatio");
            return (Criteria) this;
        }

        public Criteria andWorkHourRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("work_hour_ratio between", value1, value2, "workHourRatio");
            return (Criteria) this;
        }

        public Criteria andWorkHourRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("work_hour_ratio not between", value1, value2, "workHourRatio");
            return (Criteria) this;
        }

        public Criteria andWorkHourScoreIsNull() {
            addCriterion("work_hour_score is null");
            return (Criteria) this;
        }

        public Criteria andWorkHourScoreIsNotNull() {
            addCriterion("work_hour_score is not null");
            return (Criteria) this;
        }

        public Criteria andWorkHourScoreEqualTo(BigDecimal value) {
            addCriterion("work_hour_score =", value, "workHourScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourScoreNotEqualTo(BigDecimal value) {
            addCriterion("work_hour_score <>", value, "workHourScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourScoreGreaterThan(BigDecimal value) {
            addCriterion("work_hour_score >", value, "workHourScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("work_hour_score >=", value, "workHourScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourScoreLessThan(BigDecimal value) {
            addCriterion("work_hour_score <", value, "workHourScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("work_hour_score <=", value, "workHourScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourScoreIn(List<BigDecimal> values) {
            addCriterion("work_hour_score in", values, "workHourScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourScoreNotIn(List<BigDecimal> values) {
            addCriterion("work_hour_score not in", values, "workHourScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("work_hour_score between", value1, value2, "workHourScore");
            return (Criteria) this;
        }

        public Criteria andWorkHourScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("work_hour_score not between", value1, value2, "workHourScore");
            return (Criteria) this;
        }

        public Criteria andQualityStandardScoreIsNull() {
            addCriterion("quality_standard_score is null");
            return (Criteria) this;
        }

        public Criteria andQualityStandardScoreIsNotNull() {
            addCriterion("quality_standard_score is not null");
            return (Criteria) this;
        }

        public Criteria andQualityStandardScoreEqualTo(BigDecimal value) {
            addCriterion("quality_standard_score =", value, "qualityStandardScore");
            return (Criteria) this;
        }

        public Criteria andQualityStandardScoreNotEqualTo(BigDecimal value) {
            addCriterion("quality_standard_score <>", value, "qualityStandardScore");
            return (Criteria) this;
        }

        public Criteria andQualityStandardScoreGreaterThan(BigDecimal value) {
            addCriterion("quality_standard_score >", value, "qualityStandardScore");
            return (Criteria) this;
        }

        public Criteria andQualityStandardScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("quality_standard_score >=", value, "qualityStandardScore");
            return (Criteria) this;
        }

        public Criteria andQualityStandardScoreLessThan(BigDecimal value) {
            addCriterion("quality_standard_score <", value, "qualityStandardScore");
            return (Criteria) this;
        }

        public Criteria andQualityStandardScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("quality_standard_score <=", value, "qualityStandardScore");
            return (Criteria) this;
        }

        public Criteria andQualityStandardScoreIn(List<BigDecimal> values) {
            addCriterion("quality_standard_score in", values, "qualityStandardScore");
            return (Criteria) this;
        }

        public Criteria andQualityStandardScoreNotIn(List<BigDecimal> values) {
            addCriterion("quality_standard_score not in", values, "qualityStandardScore");
            return (Criteria) this;
        }

        public Criteria andQualityStandardScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("quality_standard_score between", value1, value2, "qualityStandardScore");
            return (Criteria) this;
        }

        public Criteria andQualityStandardScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("quality_standard_score not between", value1, value2, "qualityStandardScore");
            return (Criteria) this;
        }

        public Criteria andQualityRatioIsNull() {
            addCriterion("quality_ratio is null");
            return (Criteria) this;
        }

        public Criteria andQualityRatioIsNotNull() {
            addCriterion("quality_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andQualityRatioEqualTo(BigDecimal value) {
            addCriterion("quality_ratio =", value, "qualityRatio");
            return (Criteria) this;
        }

        public Criteria andQualityRatioNotEqualTo(BigDecimal value) {
            addCriterion("quality_ratio <>", value, "qualityRatio");
            return (Criteria) this;
        }

        public Criteria andQualityRatioGreaterThan(BigDecimal value) {
            addCriterion("quality_ratio >", value, "qualityRatio");
            return (Criteria) this;
        }

        public Criteria andQualityRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("quality_ratio >=", value, "qualityRatio");
            return (Criteria) this;
        }

        public Criteria andQualityRatioLessThan(BigDecimal value) {
            addCriterion("quality_ratio <", value, "qualityRatio");
            return (Criteria) this;
        }

        public Criteria andQualityRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("quality_ratio <=", value, "qualityRatio");
            return (Criteria) this;
        }

        public Criteria andQualityRatioIn(List<BigDecimal> values) {
            addCriterion("quality_ratio in", values, "qualityRatio");
            return (Criteria) this;
        }

        public Criteria andQualityRatioNotIn(List<BigDecimal> values) {
            addCriterion("quality_ratio not in", values, "qualityRatio");
            return (Criteria) this;
        }

        public Criteria andQualityRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("quality_ratio between", value1, value2, "qualityRatio");
            return (Criteria) this;
        }

        public Criteria andQualityRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("quality_ratio not between", value1, value2, "qualityRatio");
            return (Criteria) this;
        }

        public Criteria andQualityScoreIsNull() {
            addCriterion("quality_score is null");
            return (Criteria) this;
        }

        public Criteria andQualityScoreIsNotNull() {
            addCriterion("quality_score is not null");
            return (Criteria) this;
        }

        public Criteria andQualityScoreEqualTo(BigDecimal value) {
            addCriterion("quality_score =", value, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreNotEqualTo(BigDecimal value) {
            addCriterion("quality_score <>", value, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreGreaterThan(BigDecimal value) {
            addCriterion("quality_score >", value, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("quality_score >=", value, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreLessThan(BigDecimal value) {
            addCriterion("quality_score <", value, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("quality_score <=", value, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreIn(List<BigDecimal> values) {
            addCriterion("quality_score in", values, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreNotIn(List<BigDecimal> values) {
            addCriterion("quality_score not in", values, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("quality_score between", value1, value2, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("quality_score not between", value1, value2, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
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