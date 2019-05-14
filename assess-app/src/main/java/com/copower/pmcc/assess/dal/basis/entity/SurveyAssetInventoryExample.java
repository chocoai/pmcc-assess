package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SurveyAssetInventoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SurveyAssetInventoryExample() {
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

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Integer value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Integer value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Integer value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Integer value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Integer> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Integer> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdIsNull() {
            addCriterion("plan_detail_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdIsNotNull() {
            addCriterion("plan_detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdEqualTo(Integer value) {
            addCriterion("plan_detail_id =", value, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdNotEqualTo(Integer value) {
            addCriterion("plan_detail_id <>", value, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdGreaterThan(Integer value) {
            addCriterion("plan_detail_id >", value, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_detail_id >=", value, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdLessThan(Integer value) {
            addCriterion("plan_detail_id <", value, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdLessThanOrEqualTo(Integer value) {
            addCriterion("plan_detail_id <=", value, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdIn(List<Integer> values) {
            addCriterion("plan_detail_id in", values, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdNotIn(List<Integer> values) {
            addCriterion("plan_detail_id not in", values, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdBetween(Integer value1, Integer value2) {
            addCriterion("plan_detail_id between", value1, value2, "planDetailId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailIdNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_detail_id not between", value1, value2, "planDetailId");
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

        public Criteria andDeclareRecordIdIsNull() {
            addCriterion("declare_record_id is null");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdIsNotNull() {
            addCriterion("declare_record_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdEqualTo(Integer value) {
            addCriterion("declare_record_id =", value, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdNotEqualTo(Integer value) {
            addCriterion("declare_record_id <>", value, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdGreaterThan(Integer value) {
            addCriterion("declare_record_id >", value, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("declare_record_id >=", value, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdLessThan(Integer value) {
            addCriterion("declare_record_id <", value, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdLessThanOrEqualTo(Integer value) {
            addCriterion("declare_record_id <=", value, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdIn(List<Integer> values) {
            addCriterion("declare_record_id in", values, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdNotIn(List<Integer> values) {
            addCriterion("declare_record_id not in", values, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdBetween(Integer value1, Integer value2) {
            addCriterion("declare_record_id between", value1, value2, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andDeclareRecordIdNotBetween(Integer value1, Integer value2) {
            addCriterion("declare_record_id not between", value1, value2, "declareRecordId");
            return (Criteria) this;
        }

        public Criteria andEvaluatorIsNull() {
            addCriterion("evaluator is null");
            return (Criteria) this;
        }

        public Criteria andEvaluatorIsNotNull() {
            addCriterion("evaluator is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluatorEqualTo(String value) {
            addCriterion("evaluator =", value, "evaluator");
            return (Criteria) this;
        }

        public Criteria andEvaluatorNotEqualTo(String value) {
            addCriterion("evaluator <>", value, "evaluator");
            return (Criteria) this;
        }

        public Criteria andEvaluatorGreaterThan(String value) {
            addCriterion("evaluator >", value, "evaluator");
            return (Criteria) this;
        }

        public Criteria andEvaluatorGreaterThanOrEqualTo(String value) {
            addCriterion("evaluator >=", value, "evaluator");
            return (Criteria) this;
        }

        public Criteria andEvaluatorLessThan(String value) {
            addCriterion("evaluator <", value, "evaluator");
            return (Criteria) this;
        }

        public Criteria andEvaluatorLessThanOrEqualTo(String value) {
            addCriterion("evaluator <=", value, "evaluator");
            return (Criteria) this;
        }

        public Criteria andEvaluatorLike(String value) {
            addCriterion("evaluator like", value, "evaluator");
            return (Criteria) this;
        }

        public Criteria andEvaluatorNotLike(String value) {
            addCriterion("evaluator not like", value, "evaluator");
            return (Criteria) this;
        }

        public Criteria andEvaluatorIn(List<String> values) {
            addCriterion("evaluator in", values, "evaluator");
            return (Criteria) this;
        }

        public Criteria andEvaluatorNotIn(List<String> values) {
            addCriterion("evaluator not in", values, "evaluator");
            return (Criteria) this;
        }

        public Criteria andEvaluatorBetween(String value1, String value2) {
            addCriterion("evaluator between", value1, value2, "evaluator");
            return (Criteria) this;
        }

        public Criteria andEvaluatorNotBetween(String value1, String value2) {
            addCriterion("evaluator not between", value1, value2, "evaluator");
            return (Criteria) this;
        }

        public Criteria andCheckDateIsNull() {
            addCriterion("check_date is null");
            return (Criteria) this;
        }

        public Criteria andCheckDateIsNotNull() {
            addCriterion("check_date is not null");
            return (Criteria) this;
        }

        public Criteria andCheckDateEqualTo(Date value) {
            addCriterion("check_date =", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotEqualTo(Date value) {
            addCriterion("check_date <>", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateGreaterThan(Date value) {
            addCriterion("check_date >", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateGreaterThanOrEqualTo(Date value) {
            addCriterion("check_date >=", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLessThan(Date value) {
            addCriterion("check_date <", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLessThanOrEqualTo(Date value) {
            addCriterion("check_date <=", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateIn(List<Date> values) {
            addCriterion("check_date in", values, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotIn(List<Date> values) {
            addCriterion("check_date not in", values, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateBetween(Date value1, Date value2) {
            addCriterion("check_date between", value1, value2, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotBetween(Date value1, Date value2) {
            addCriterion("check_date not between", value1, value2, "checkDate");
            return (Criteria) this;
        }

        public Criteria andBisCheckOriginalIsNull() {
            addCriterion("bis_check_original is null");
            return (Criteria) this;
        }

        public Criteria andBisCheckOriginalIsNotNull() {
            addCriterion("bis_check_original is not null");
            return (Criteria) this;
        }

        public Criteria andBisCheckOriginalEqualTo(Boolean value) {
            addCriterion("bis_check_original =", value, "bisCheckOriginal");
            return (Criteria) this;
        }

        public Criteria andBisCheckOriginalNotEqualTo(Boolean value) {
            addCriterion("bis_check_original <>", value, "bisCheckOriginal");
            return (Criteria) this;
        }

        public Criteria andBisCheckOriginalGreaterThan(Boolean value) {
            addCriterion("bis_check_original >", value, "bisCheckOriginal");
            return (Criteria) this;
        }

        public Criteria andBisCheckOriginalGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_check_original >=", value, "bisCheckOriginal");
            return (Criteria) this;
        }

        public Criteria andBisCheckOriginalLessThan(Boolean value) {
            addCriterion("bis_check_original <", value, "bisCheckOriginal");
            return (Criteria) this;
        }

        public Criteria andBisCheckOriginalLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_check_original <=", value, "bisCheckOriginal");
            return (Criteria) this;
        }

        public Criteria andBisCheckOriginalIn(List<Boolean> values) {
            addCriterion("bis_check_original in", values, "bisCheckOriginal");
            return (Criteria) this;
        }

        public Criteria andBisCheckOriginalNotIn(List<Boolean> values) {
            addCriterion("bis_check_original not in", values, "bisCheckOriginal");
            return (Criteria) this;
        }

        public Criteria andBisCheckOriginalBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_check_original between", value1, value2, "bisCheckOriginal");
            return (Criteria) this;
        }

        public Criteria andBisCheckOriginalNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_check_original not between", value1, value2, "bisCheckOriginal");
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

        public Criteria andSpecialCaseIsNull() {
            addCriterion("special_case is null");
            return (Criteria) this;
        }

        public Criteria andSpecialCaseIsNotNull() {
            addCriterion("special_case is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialCaseEqualTo(String value) {
            addCriterion("special_case =", value, "specialCase");
            return (Criteria) this;
        }

        public Criteria andSpecialCaseNotEqualTo(String value) {
            addCriterion("special_case <>", value, "specialCase");
            return (Criteria) this;
        }

        public Criteria andSpecialCaseGreaterThan(String value) {
            addCriterion("special_case >", value, "specialCase");
            return (Criteria) this;
        }

        public Criteria andSpecialCaseGreaterThanOrEqualTo(String value) {
            addCriterion("special_case >=", value, "specialCase");
            return (Criteria) this;
        }

        public Criteria andSpecialCaseLessThan(String value) {
            addCriterion("special_case <", value, "specialCase");
            return (Criteria) this;
        }

        public Criteria andSpecialCaseLessThanOrEqualTo(String value) {
            addCriterion("special_case <=", value, "specialCase");
            return (Criteria) this;
        }

        public Criteria andSpecialCaseLike(String value) {
            addCriterion("special_case like", value, "specialCase");
            return (Criteria) this;
        }

        public Criteria andSpecialCaseNotLike(String value) {
            addCriterion("special_case not like", value, "specialCase");
            return (Criteria) this;
        }

        public Criteria andSpecialCaseIn(List<String> values) {
            addCriterion("special_case in", values, "specialCase");
            return (Criteria) this;
        }

        public Criteria andSpecialCaseNotIn(List<String> values) {
            addCriterion("special_case not in", values, "specialCase");
            return (Criteria) this;
        }

        public Criteria andSpecialCaseBetween(String value1, String value2) {
            addCriterion("special_case between", value1, value2, "specialCase");
            return (Criteria) this;
        }

        public Criteria andSpecialCaseNotBetween(String value1, String value2) {
            addCriterion("special_case not between", value1, value2, "specialCase");
            return (Criteria) this;
        }

        public Criteria andSegmentationLimitIsNull() {
            addCriterion("segmentation_limit is null");
            return (Criteria) this;
        }

        public Criteria andSegmentationLimitIsNotNull() {
            addCriterion("segmentation_limit is not null");
            return (Criteria) this;
        }

        public Criteria andSegmentationLimitEqualTo(String value) {
            addCriterion("segmentation_limit =", value, "segmentationLimit");
            return (Criteria) this;
        }

        public Criteria andSegmentationLimitNotEqualTo(String value) {
            addCriterion("segmentation_limit <>", value, "segmentationLimit");
            return (Criteria) this;
        }

        public Criteria andSegmentationLimitGreaterThan(String value) {
            addCriterion("segmentation_limit >", value, "segmentationLimit");
            return (Criteria) this;
        }

        public Criteria andSegmentationLimitGreaterThanOrEqualTo(String value) {
            addCriterion("segmentation_limit >=", value, "segmentationLimit");
            return (Criteria) this;
        }

        public Criteria andSegmentationLimitLessThan(String value) {
            addCriterion("segmentation_limit <", value, "segmentationLimit");
            return (Criteria) this;
        }

        public Criteria andSegmentationLimitLessThanOrEqualTo(String value) {
            addCriterion("segmentation_limit <=", value, "segmentationLimit");
            return (Criteria) this;
        }

        public Criteria andSegmentationLimitLike(String value) {
            addCriterion("segmentation_limit like", value, "segmentationLimit");
            return (Criteria) this;
        }

        public Criteria andSegmentationLimitNotLike(String value) {
            addCriterion("segmentation_limit not like", value, "segmentationLimit");
            return (Criteria) this;
        }

        public Criteria andSegmentationLimitIn(List<String> values) {
            addCriterion("segmentation_limit in", values, "segmentationLimit");
            return (Criteria) this;
        }

        public Criteria andSegmentationLimitNotIn(List<String> values) {
            addCriterion("segmentation_limit not in", values, "segmentationLimit");
            return (Criteria) this;
        }

        public Criteria andSegmentationLimitBetween(String value1, String value2) {
            addCriterion("segmentation_limit between", value1, value2, "segmentationLimit");
            return (Criteria) this;
        }

        public Criteria andSegmentationLimitNotBetween(String value1, String value2) {
            addCriterion("segmentation_limit not between", value1, value2, "segmentationLimit");
            return (Criteria) this;
        }

        public Criteria andCanUseIsNull() {
            addCriterion("can_use is null");
            return (Criteria) this;
        }

        public Criteria andCanUseIsNotNull() {
            addCriterion("can_use is not null");
            return (Criteria) this;
        }

        public Criteria andCanUseEqualTo(String value) {
            addCriterion("can_use =", value, "canUse");
            return (Criteria) this;
        }

        public Criteria andCanUseNotEqualTo(String value) {
            addCriterion("can_use <>", value, "canUse");
            return (Criteria) this;
        }

        public Criteria andCanUseGreaterThan(String value) {
            addCriterion("can_use >", value, "canUse");
            return (Criteria) this;
        }

        public Criteria andCanUseGreaterThanOrEqualTo(String value) {
            addCriterion("can_use >=", value, "canUse");
            return (Criteria) this;
        }

        public Criteria andCanUseLessThan(String value) {
            addCriterion("can_use <", value, "canUse");
            return (Criteria) this;
        }

        public Criteria andCanUseLessThanOrEqualTo(String value) {
            addCriterion("can_use <=", value, "canUse");
            return (Criteria) this;
        }

        public Criteria andCanUseLike(String value) {
            addCriterion("can_use like", value, "canUse");
            return (Criteria) this;
        }

        public Criteria andCanUseNotLike(String value) {
            addCriterion("can_use not like", value, "canUse");
            return (Criteria) this;
        }

        public Criteria andCanUseIn(List<String> values) {
            addCriterion("can_use in", values, "canUse");
            return (Criteria) this;
        }

        public Criteria andCanUseNotIn(List<String> values) {
            addCriterion("can_use not in", values, "canUse");
            return (Criteria) this;
        }

        public Criteria andCanUseBetween(String value1, String value2) {
            addCriterion("can_use between", value1, value2, "canUse");
            return (Criteria) this;
        }

        public Criteria andCanUseNotBetween(String value1, String value2) {
            addCriterion("can_use not between", value1, value2, "canUse");
            return (Criteria) this;
        }

        public Criteria andApplicationIsNull() {
            addCriterion("application is null");
            return (Criteria) this;
        }

        public Criteria andApplicationIsNotNull() {
            addCriterion("application is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationEqualTo(Integer value) {
            addCriterion("application =", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationNotEqualTo(Integer value) {
            addCriterion("application <>", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationGreaterThan(Integer value) {
            addCriterion("application >", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationGreaterThanOrEqualTo(Integer value) {
            addCriterion("application >=", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationLessThan(Integer value) {
            addCriterion("application <", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationLessThanOrEqualTo(Integer value) {
            addCriterion("application <=", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationIn(List<Integer> values) {
            addCriterion("application in", values, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationNotIn(List<Integer> values) {
            addCriterion("application not in", values, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationBetween(Integer value1, Integer value2) {
            addCriterion("application between", value1, value2, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationNotBetween(Integer value1, Integer value2) {
            addCriterion("application not between", value1, value2, "application");
            return (Criteria) this;
        }

        public Criteria andCertificateIsNull() {
            addCriterion("certificate is null");
            return (Criteria) this;
        }

        public Criteria andCertificateIsNotNull() {
            addCriterion("certificate is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateEqualTo(String value) {
            addCriterion("certificate =", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotEqualTo(String value) {
            addCriterion("certificate <>", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateGreaterThan(String value) {
            addCriterion("certificate >", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateGreaterThanOrEqualTo(String value) {
            addCriterion("certificate >=", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateLessThan(String value) {
            addCriterion("certificate <", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateLessThanOrEqualTo(String value) {
            addCriterion("certificate <=", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateLike(String value) {
            addCriterion("certificate like", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotLike(String value) {
            addCriterion("certificate not like", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateIn(List<String> values) {
            addCriterion("certificate in", values, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotIn(List<String> values) {
            addCriterion("certificate not in", values, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateBetween(String value1, String value2) {
            addCriterion("certificate between", value1, value2, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotBetween(String value1, String value2) {
            addCriterion("certificate not between", value1, value2, "certificate");
            return (Criteria) this;
        }

        public Criteria andRimIsNormalIsNull() {
            addCriterion("rim_is_normal is null");
            return (Criteria) this;
        }

        public Criteria andRimIsNormalIsNotNull() {
            addCriterion("rim_is_normal is not null");
            return (Criteria) this;
        }

        public Criteria andRimIsNormalEqualTo(String value) {
            addCriterion("rim_is_normal =", value, "rimIsNormal");
            return (Criteria) this;
        }

        public Criteria andRimIsNormalNotEqualTo(String value) {
            addCriterion("rim_is_normal <>", value, "rimIsNormal");
            return (Criteria) this;
        }

        public Criteria andRimIsNormalGreaterThan(String value) {
            addCriterion("rim_is_normal >", value, "rimIsNormal");
            return (Criteria) this;
        }

        public Criteria andRimIsNormalGreaterThanOrEqualTo(String value) {
            addCriterion("rim_is_normal >=", value, "rimIsNormal");
            return (Criteria) this;
        }

        public Criteria andRimIsNormalLessThan(String value) {
            addCriterion("rim_is_normal <", value, "rimIsNormal");
            return (Criteria) this;
        }

        public Criteria andRimIsNormalLessThanOrEqualTo(String value) {
            addCriterion("rim_is_normal <=", value, "rimIsNormal");
            return (Criteria) this;
        }

        public Criteria andRimIsNormalLike(String value) {
            addCriterion("rim_is_normal like", value, "rimIsNormal");
            return (Criteria) this;
        }

        public Criteria andRimIsNormalNotLike(String value) {
            addCriterion("rim_is_normal not like", value, "rimIsNormal");
            return (Criteria) this;
        }

        public Criteria andRimIsNormalIn(List<String> values) {
            addCriterion("rim_is_normal in", values, "rimIsNormal");
            return (Criteria) this;
        }

        public Criteria andRimIsNormalNotIn(List<String> values) {
            addCriterion("rim_is_normal not in", values, "rimIsNormal");
            return (Criteria) this;
        }

        public Criteria andRimIsNormalBetween(String value1, String value2) {
            addCriterion("rim_is_normal between", value1, value2, "rimIsNormal");
            return (Criteria) this;
        }

        public Criteria andRimIsNormalNotBetween(String value1, String value2) {
            addCriterion("rim_is_normal not between", value1, value2, "rimIsNormal");
            return (Criteria) this;
        }

        public Criteria andZoneDamageIsNull() {
            addCriterion("zone_damage is null");
            return (Criteria) this;
        }

        public Criteria andZoneDamageIsNotNull() {
            addCriterion("zone_damage is not null");
            return (Criteria) this;
        }

        public Criteria andZoneDamageEqualTo(String value) {
            addCriterion("zone_damage =", value, "zoneDamage");
            return (Criteria) this;
        }

        public Criteria andZoneDamageNotEqualTo(String value) {
            addCriterion("zone_damage <>", value, "zoneDamage");
            return (Criteria) this;
        }

        public Criteria andZoneDamageGreaterThan(String value) {
            addCriterion("zone_damage >", value, "zoneDamage");
            return (Criteria) this;
        }

        public Criteria andZoneDamageGreaterThanOrEqualTo(String value) {
            addCriterion("zone_damage >=", value, "zoneDamage");
            return (Criteria) this;
        }

        public Criteria andZoneDamageLessThan(String value) {
            addCriterion("zone_damage <", value, "zoneDamage");
            return (Criteria) this;
        }

        public Criteria andZoneDamageLessThanOrEqualTo(String value) {
            addCriterion("zone_damage <=", value, "zoneDamage");
            return (Criteria) this;
        }

        public Criteria andZoneDamageLike(String value) {
            addCriterion("zone_damage like", value, "zoneDamage");
            return (Criteria) this;
        }

        public Criteria andZoneDamageNotLike(String value) {
            addCriterion("zone_damage not like", value, "zoneDamage");
            return (Criteria) this;
        }

        public Criteria andZoneDamageIn(List<String> values) {
            addCriterion("zone_damage in", values, "zoneDamage");
            return (Criteria) this;
        }

        public Criteria andZoneDamageNotIn(List<String> values) {
            addCriterion("zone_damage not in", values, "zoneDamage");
            return (Criteria) this;
        }

        public Criteria andZoneDamageBetween(String value1, String value2) {
            addCriterion("zone_damage between", value1, value2, "zoneDamage");
            return (Criteria) this;
        }

        public Criteria andZoneDamageNotBetween(String value1, String value2) {
            addCriterion("zone_damage not between", value1, value2, "zoneDamage");
            return (Criteria) this;
        }

        public Criteria andAbnormalityIsNull() {
            addCriterion("abnormality is null");
            return (Criteria) this;
        }

        public Criteria andAbnormalityIsNotNull() {
            addCriterion("abnormality is not null");
            return (Criteria) this;
        }

        public Criteria andAbnormalityEqualTo(String value) {
            addCriterion("abnormality =", value, "abnormality");
            return (Criteria) this;
        }

        public Criteria andAbnormalityNotEqualTo(String value) {
            addCriterion("abnormality <>", value, "abnormality");
            return (Criteria) this;
        }

        public Criteria andAbnormalityGreaterThan(String value) {
            addCriterion("abnormality >", value, "abnormality");
            return (Criteria) this;
        }

        public Criteria andAbnormalityGreaterThanOrEqualTo(String value) {
            addCriterion("abnormality >=", value, "abnormality");
            return (Criteria) this;
        }

        public Criteria andAbnormalityLessThan(String value) {
            addCriterion("abnormality <", value, "abnormality");
            return (Criteria) this;
        }

        public Criteria andAbnormalityLessThanOrEqualTo(String value) {
            addCriterion("abnormality <=", value, "abnormality");
            return (Criteria) this;
        }

        public Criteria andAbnormalityLike(String value) {
            addCriterion("abnormality like", value, "abnormality");
            return (Criteria) this;
        }

        public Criteria andAbnormalityNotLike(String value) {
            addCriterion("abnormality not like", value, "abnormality");
            return (Criteria) this;
        }

        public Criteria andAbnormalityIn(List<String> values) {
            addCriterion("abnormality in", values, "abnormality");
            return (Criteria) this;
        }

        public Criteria andAbnormalityNotIn(List<String> values) {
            addCriterion("abnormality not in", values, "abnormality");
            return (Criteria) this;
        }

        public Criteria andAbnormalityBetween(String value1, String value2) {
            addCriterion("abnormality between", value1, value2, "abnormality");
            return (Criteria) this;
        }

        public Criteria andAbnormalityNotBetween(String value1, String value2) {
            addCriterion("abnormality not between", value1, value2, "abnormality");
            return (Criteria) this;
        }

        public Criteria andEntityIsDamageIsNull() {
            addCriterion("entity_is_damage is null");
            return (Criteria) this;
        }

        public Criteria andEntityIsDamageIsNotNull() {
            addCriterion("entity_is_damage is not null");
            return (Criteria) this;
        }

        public Criteria andEntityIsDamageEqualTo(String value) {
            addCriterion("entity_is_damage =", value, "entityIsDamage");
            return (Criteria) this;
        }

        public Criteria andEntityIsDamageNotEqualTo(String value) {
            addCriterion("entity_is_damage <>", value, "entityIsDamage");
            return (Criteria) this;
        }

        public Criteria andEntityIsDamageGreaterThan(String value) {
            addCriterion("entity_is_damage >", value, "entityIsDamage");
            return (Criteria) this;
        }

        public Criteria andEntityIsDamageGreaterThanOrEqualTo(String value) {
            addCriterion("entity_is_damage >=", value, "entityIsDamage");
            return (Criteria) this;
        }

        public Criteria andEntityIsDamageLessThan(String value) {
            addCriterion("entity_is_damage <", value, "entityIsDamage");
            return (Criteria) this;
        }

        public Criteria andEntityIsDamageLessThanOrEqualTo(String value) {
            addCriterion("entity_is_damage <=", value, "entityIsDamage");
            return (Criteria) this;
        }

        public Criteria andEntityIsDamageLike(String value) {
            addCriterion("entity_is_damage like", value, "entityIsDamage");
            return (Criteria) this;
        }

        public Criteria andEntityIsDamageNotLike(String value) {
            addCriterion("entity_is_damage not like", value, "entityIsDamage");
            return (Criteria) this;
        }

        public Criteria andEntityIsDamageIn(List<String> values) {
            addCriterion("entity_is_damage in", values, "entityIsDamage");
            return (Criteria) this;
        }

        public Criteria andEntityIsDamageNotIn(List<String> values) {
            addCriterion("entity_is_damage not in", values, "entityIsDamage");
            return (Criteria) this;
        }

        public Criteria andEntityIsDamageBetween(String value1, String value2) {
            addCriterion("entity_is_damage between", value1, value2, "entityIsDamage");
            return (Criteria) this;
        }

        public Criteria andEntityIsDamageNotBetween(String value1, String value2) {
            addCriterion("entity_is_damage not between", value1, value2, "entityIsDamage");
            return (Criteria) this;
        }

        public Criteria andEntityDamageIsNull() {
            addCriterion("entity_damage is null");
            return (Criteria) this;
        }

        public Criteria andEntityDamageIsNotNull() {
            addCriterion("entity_damage is not null");
            return (Criteria) this;
        }

        public Criteria andEntityDamageEqualTo(String value) {
            addCriterion("entity_damage =", value, "entityDamage");
            return (Criteria) this;
        }

        public Criteria andEntityDamageNotEqualTo(String value) {
            addCriterion("entity_damage <>", value, "entityDamage");
            return (Criteria) this;
        }

        public Criteria andEntityDamageGreaterThan(String value) {
            addCriterion("entity_damage >", value, "entityDamage");
            return (Criteria) this;
        }

        public Criteria andEntityDamageGreaterThanOrEqualTo(String value) {
            addCriterion("entity_damage >=", value, "entityDamage");
            return (Criteria) this;
        }

        public Criteria andEntityDamageLessThan(String value) {
            addCriterion("entity_damage <", value, "entityDamage");
            return (Criteria) this;
        }

        public Criteria andEntityDamageLessThanOrEqualTo(String value) {
            addCriterion("entity_damage <=", value, "entityDamage");
            return (Criteria) this;
        }

        public Criteria andEntityDamageLike(String value) {
            addCriterion("entity_damage like", value, "entityDamage");
            return (Criteria) this;
        }

        public Criteria andEntityDamageNotLike(String value) {
            addCriterion("entity_damage not like", value, "entityDamage");
            return (Criteria) this;
        }

        public Criteria andEntityDamageIn(List<String> values) {
            addCriterion("entity_damage in", values, "entityDamage");
            return (Criteria) this;
        }

        public Criteria andEntityDamageNotIn(List<String> values) {
            addCriterion("entity_damage not in", values, "entityDamage");
            return (Criteria) this;
        }

        public Criteria andEntityDamageBetween(String value1, String value2) {
            addCriterion("entity_damage between", value1, value2, "entityDamage");
            return (Criteria) this;
        }

        public Criteria andEntityDamageNotBetween(String value1, String value2) {
            addCriterion("entity_damage not between", value1, value2, "entityDamage");
            return (Criteria) this;
        }

        public Criteria andDamageRemarkIsNull() {
            addCriterion("damage_remark is null");
            return (Criteria) this;
        }

        public Criteria andDamageRemarkIsNotNull() {
            addCriterion("damage_remark is not null");
            return (Criteria) this;
        }

        public Criteria andDamageRemarkEqualTo(String value) {
            addCriterion("damage_remark =", value, "damageRemark");
            return (Criteria) this;
        }

        public Criteria andDamageRemarkNotEqualTo(String value) {
            addCriterion("damage_remark <>", value, "damageRemark");
            return (Criteria) this;
        }

        public Criteria andDamageRemarkGreaterThan(String value) {
            addCriterion("damage_remark >", value, "damageRemark");
            return (Criteria) this;
        }

        public Criteria andDamageRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("damage_remark >=", value, "damageRemark");
            return (Criteria) this;
        }

        public Criteria andDamageRemarkLessThan(String value) {
            addCriterion("damage_remark <", value, "damageRemark");
            return (Criteria) this;
        }

        public Criteria andDamageRemarkLessThanOrEqualTo(String value) {
            addCriterion("damage_remark <=", value, "damageRemark");
            return (Criteria) this;
        }

        public Criteria andDamageRemarkLike(String value) {
            addCriterion("damage_remark like", value, "damageRemark");
            return (Criteria) this;
        }

        public Criteria andDamageRemarkNotLike(String value) {
            addCriterion("damage_remark not like", value, "damageRemark");
            return (Criteria) this;
        }

        public Criteria andDamageRemarkIn(List<String> values) {
            addCriterion("damage_remark in", values, "damageRemark");
            return (Criteria) this;
        }

        public Criteria andDamageRemarkNotIn(List<String> values) {
            addCriterion("damage_remark not in", values, "damageRemark");
            return (Criteria) this;
        }

        public Criteria andDamageRemarkBetween(String value1, String value2) {
            addCriterion("damage_remark between", value1, value2, "damageRemark");
            return (Criteria) this;
        }

        public Criteria andDamageRemarkNotBetween(String value1, String value2) {
            addCriterion("damage_remark not between", value1, value2, "damageRemark");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusIsNull() {
            addCriterion("payment_status is null");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusIsNotNull() {
            addCriterion("payment_status is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusEqualTo(String value) {
            addCriterion("payment_status =", value, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusNotEqualTo(String value) {
            addCriterion("payment_status <>", value, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusGreaterThan(String value) {
            addCriterion("payment_status >", value, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusGreaterThanOrEqualTo(String value) {
            addCriterion("payment_status >=", value, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusLessThan(String value) {
            addCriterion("payment_status <", value, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusLessThanOrEqualTo(String value) {
            addCriterion("payment_status <=", value, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusLike(String value) {
            addCriterion("payment_status like", value, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusNotLike(String value) {
            addCriterion("payment_status not like", value, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusIn(List<String> values) {
            addCriterion("payment_status in", values, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusNotIn(List<String> values) {
            addCriterion("payment_status not in", values, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusBetween(String value1, String value2) {
            addCriterion("payment_status between", value1, value2, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentStatusNotBetween(String value1, String value2) {
            addCriterion("payment_status not between", value1, value2, "paymentStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentContentIsNull() {
            addCriterion("payment_content is null");
            return (Criteria) this;
        }

        public Criteria andPaymentContentIsNotNull() {
            addCriterion("payment_content is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentContentEqualTo(String value) {
            addCriterion("payment_content =", value, "paymentContent");
            return (Criteria) this;
        }

        public Criteria andPaymentContentNotEqualTo(String value) {
            addCriterion("payment_content <>", value, "paymentContent");
            return (Criteria) this;
        }

        public Criteria andPaymentContentGreaterThan(String value) {
            addCriterion("payment_content >", value, "paymentContent");
            return (Criteria) this;
        }

        public Criteria andPaymentContentGreaterThanOrEqualTo(String value) {
            addCriterion("payment_content >=", value, "paymentContent");
            return (Criteria) this;
        }

        public Criteria andPaymentContentLessThan(String value) {
            addCriterion("payment_content <", value, "paymentContent");
            return (Criteria) this;
        }

        public Criteria andPaymentContentLessThanOrEqualTo(String value) {
            addCriterion("payment_content <=", value, "paymentContent");
            return (Criteria) this;
        }

        public Criteria andPaymentContentLike(String value) {
            addCriterion("payment_content like", value, "paymentContent");
            return (Criteria) this;
        }

        public Criteria andPaymentContentNotLike(String value) {
            addCriterion("payment_content not like", value, "paymentContent");
            return (Criteria) this;
        }

        public Criteria andPaymentContentIn(List<String> values) {
            addCriterion("payment_content in", values, "paymentContent");
            return (Criteria) this;
        }

        public Criteria andPaymentContentNotIn(List<String> values) {
            addCriterion("payment_content not in", values, "paymentContent");
            return (Criteria) this;
        }

        public Criteria andPaymentContentBetween(String value1, String value2) {
            addCriterion("payment_content between", value1, value2, "paymentContent");
            return (Criteria) this;
        }

        public Criteria andPaymentContentNotBetween(String value1, String value2) {
            addCriterion("payment_content not between", value1, value2, "paymentContent");
            return (Criteria) this;
        }

        public Criteria andTransferLimitIsNull() {
            addCriterion("transfer_limit is null");
            return (Criteria) this;
        }

        public Criteria andTransferLimitIsNotNull() {
            addCriterion("transfer_limit is not null");
            return (Criteria) this;
        }

        public Criteria andTransferLimitEqualTo(String value) {
            addCriterion("transfer_limit =", value, "transferLimit");
            return (Criteria) this;
        }

        public Criteria andTransferLimitNotEqualTo(String value) {
            addCriterion("transfer_limit <>", value, "transferLimit");
            return (Criteria) this;
        }

        public Criteria andTransferLimitGreaterThan(String value) {
            addCriterion("transfer_limit >", value, "transferLimit");
            return (Criteria) this;
        }

        public Criteria andTransferLimitGreaterThanOrEqualTo(String value) {
            addCriterion("transfer_limit >=", value, "transferLimit");
            return (Criteria) this;
        }

        public Criteria andTransferLimitLessThan(String value) {
            addCriterion("transfer_limit <", value, "transferLimit");
            return (Criteria) this;
        }

        public Criteria andTransferLimitLessThanOrEqualTo(String value) {
            addCriterion("transfer_limit <=", value, "transferLimit");
            return (Criteria) this;
        }

        public Criteria andTransferLimitLike(String value) {
            addCriterion("transfer_limit like", value, "transferLimit");
            return (Criteria) this;
        }

        public Criteria andTransferLimitNotLike(String value) {
            addCriterion("transfer_limit not like", value, "transferLimit");
            return (Criteria) this;
        }

        public Criteria andTransferLimitIn(List<String> values) {
            addCriterion("transfer_limit in", values, "transferLimit");
            return (Criteria) this;
        }

        public Criteria andTransferLimitNotIn(List<String> values) {
            addCriterion("transfer_limit not in", values, "transferLimit");
            return (Criteria) this;
        }

        public Criteria andTransferLimitBetween(String value1, String value2) {
            addCriterion("transfer_limit between", value1, value2, "transferLimit");
            return (Criteria) this;
        }

        public Criteria andTransferLimitNotBetween(String value1, String value2) {
            addCriterion("transfer_limit not between", value1, value2, "transferLimit");
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

        public Criteria andOtherProjectIsNull() {
            addCriterion("other_project is null");
            return (Criteria) this;
        }

        public Criteria andOtherProjectIsNotNull() {
            addCriterion("other_project is not null");
            return (Criteria) this;
        }

        public Criteria andOtherProjectEqualTo(String value) {
            addCriterion("other_project =", value, "otherProject");
            return (Criteria) this;
        }

        public Criteria andOtherProjectNotEqualTo(String value) {
            addCriterion("other_project <>", value, "otherProject");
            return (Criteria) this;
        }

        public Criteria andOtherProjectGreaterThan(String value) {
            addCriterion("other_project >", value, "otherProject");
            return (Criteria) this;
        }

        public Criteria andOtherProjectGreaterThanOrEqualTo(String value) {
            addCriterion("other_project >=", value, "otherProject");
            return (Criteria) this;
        }

        public Criteria andOtherProjectLessThan(String value) {
            addCriterion("other_project <", value, "otherProject");
            return (Criteria) this;
        }

        public Criteria andOtherProjectLessThanOrEqualTo(String value) {
            addCriterion("other_project <=", value, "otherProject");
            return (Criteria) this;
        }

        public Criteria andOtherProjectLike(String value) {
            addCriterion("other_project like", value, "otherProject");
            return (Criteria) this;
        }

        public Criteria andOtherProjectNotLike(String value) {
            addCriterion("other_project not like", value, "otherProject");
            return (Criteria) this;
        }

        public Criteria andOtherProjectIn(List<String> values) {
            addCriterion("other_project in", values, "otherProject");
            return (Criteria) this;
        }

        public Criteria andOtherProjectNotIn(List<String> values) {
            addCriterion("other_project not in", values, "otherProject");
            return (Criteria) this;
        }

        public Criteria andOtherProjectBetween(String value1, String value2) {
            addCriterion("other_project between", value1, value2, "otherProject");
            return (Criteria) this;
        }

        public Criteria andOtherProjectNotBetween(String value1, String value2) {
            addCriterion("other_project not between", value1, value2, "otherProject");
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