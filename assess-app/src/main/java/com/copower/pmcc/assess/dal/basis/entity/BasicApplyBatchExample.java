package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicApplyBatchExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicApplyBatchExample() {
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

        public Criteria andReferenceApplyBatchIdIsNull() {
            addCriterion("reference_apply_batch_id is null");
            return (Criteria) this;
        }

        public Criteria andReferenceApplyBatchIdIsNotNull() {
            addCriterion("reference_apply_batch_id is not null");
            return (Criteria) this;
        }

        public Criteria andReferenceApplyBatchIdEqualTo(Integer value) {
            addCriterion("reference_apply_batch_id =", value, "referenceApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andReferenceApplyBatchIdNotEqualTo(Integer value) {
            addCriterion("reference_apply_batch_id <>", value, "referenceApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andReferenceApplyBatchIdGreaterThan(Integer value) {
            addCriterion("reference_apply_batch_id >", value, "referenceApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andReferenceApplyBatchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reference_apply_batch_id >=", value, "referenceApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andReferenceApplyBatchIdLessThan(Integer value) {
            addCriterion("reference_apply_batch_id <", value, "referenceApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andReferenceApplyBatchIdLessThanOrEqualTo(Integer value) {
            addCriterion("reference_apply_batch_id <=", value, "referenceApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andReferenceApplyBatchIdIn(List<Integer> values) {
            addCriterion("reference_apply_batch_id in", values, "referenceApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andReferenceApplyBatchIdNotIn(List<Integer> values) {
            addCriterion("reference_apply_batch_id not in", values, "referenceApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andReferenceApplyBatchIdBetween(Integer value1, Integer value2) {
            addCriterion("reference_apply_batch_id between", value1, value2, "referenceApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andReferenceApplyBatchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reference_apply_batch_id not between", value1, value2, "referenceApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andCaseApplyBatchIdIsNull() {
            addCriterion("case_apply_batch_id is null");
            return (Criteria) this;
        }

        public Criteria andCaseApplyBatchIdIsNotNull() {
            addCriterion("case_apply_batch_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaseApplyBatchIdEqualTo(Integer value) {
            addCriterion("case_apply_batch_id =", value, "caseApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andCaseApplyBatchIdNotEqualTo(Integer value) {
            addCriterion("case_apply_batch_id <>", value, "caseApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andCaseApplyBatchIdGreaterThan(Integer value) {
            addCriterion("case_apply_batch_id >", value, "caseApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andCaseApplyBatchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_apply_batch_id >=", value, "caseApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andCaseApplyBatchIdLessThan(Integer value) {
            addCriterion("case_apply_batch_id <", value, "caseApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andCaseApplyBatchIdLessThanOrEqualTo(Integer value) {
            addCriterion("case_apply_batch_id <=", value, "caseApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andCaseApplyBatchIdIn(List<Integer> values) {
            addCriterion("case_apply_batch_id in", values, "caseApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andCaseApplyBatchIdNotIn(List<Integer> values) {
            addCriterion("case_apply_batch_id not in", values, "caseApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andCaseApplyBatchIdBetween(Integer value1, Integer value2) {
            addCriterion("case_apply_batch_id between", value1, value2, "caseApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andCaseApplyBatchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("case_apply_batch_id not between", value1, value2, "caseApplyBatchId");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andClassifyIsNull() {
            addCriterion("classify is null");
            return (Criteria) this;
        }

        public Criteria andClassifyIsNotNull() {
            addCriterion("classify is not null");
            return (Criteria) this;
        }

        public Criteria andClassifyEqualTo(Integer value) {
            addCriterion("classify =", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotEqualTo(Integer value) {
            addCriterion("classify <>", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyGreaterThan(Integer value) {
            addCriterion("classify >", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyGreaterThanOrEqualTo(Integer value) {
            addCriterion("classify >=", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyLessThan(Integer value) {
            addCriterion("classify <", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyLessThanOrEqualTo(Integer value) {
            addCriterion("classify <=", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyIn(List<Integer> values) {
            addCriterion("classify in", values, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotIn(List<Integer> values) {
            addCriterion("classify not in", values, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyBetween(Integer value1, Integer value2) {
            addCriterion("classify between", value1, value2, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotBetween(Integer value1, Integer value2) {
            addCriterion("classify not between", value1, value2, "classify");
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

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andEstateIdIsNull() {
            addCriterion("estate_id is null");
            return (Criteria) this;
        }

        public Criteria andEstateIdIsNotNull() {
            addCriterion("estate_id is not null");
            return (Criteria) this;
        }

        public Criteria andEstateIdEqualTo(Integer value) {
            addCriterion("estate_id =", value, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdNotEqualTo(Integer value) {
            addCriterion("estate_id <>", value, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdGreaterThan(Integer value) {
            addCriterion("estate_id >", value, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("estate_id >=", value, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdLessThan(Integer value) {
            addCriterion("estate_id <", value, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdLessThanOrEqualTo(Integer value) {
            addCriterion("estate_id <=", value, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdIn(List<Integer> values) {
            addCriterion("estate_id in", values, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdNotIn(List<Integer> values) {
            addCriterion("estate_id not in", values, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdBetween(Integer value1, Integer value2) {
            addCriterion("estate_id between", value1, value2, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("estate_id not between", value1, value2, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateNameIsNull() {
            addCriterion("estate_name is null");
            return (Criteria) this;
        }

        public Criteria andEstateNameIsNotNull() {
            addCriterion("estate_name is not null");
            return (Criteria) this;
        }

        public Criteria andEstateNameEqualTo(String value) {
            addCriterion("estate_name =", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameNotEqualTo(String value) {
            addCriterion("estate_name <>", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameGreaterThan(String value) {
            addCriterion("estate_name >", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameGreaterThanOrEqualTo(String value) {
            addCriterion("estate_name >=", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameLessThan(String value) {
            addCriterion("estate_name <", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameLessThanOrEqualTo(String value) {
            addCriterion("estate_name <=", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameLike(String value) {
            addCriterion("estate_name like", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameNotLike(String value) {
            addCriterion("estate_name not like", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameIn(List<String> values) {
            addCriterion("estate_name in", values, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameNotIn(List<String> values) {
            addCriterion("estate_name not in", values, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameBetween(String value1, String value2) {
            addCriterion("estate_name between", value1, value2, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameNotBetween(String value1, String value2) {
            addCriterion("estate_name not between", value1, value2, "estateName");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusIsNull() {
            addCriterion("building_status is null");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusIsNotNull() {
            addCriterion("building_status is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusEqualTo(Integer value) {
            addCriterion("building_status =", value, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusNotEqualTo(Integer value) {
            addCriterion("building_status <>", value, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusGreaterThan(Integer value) {
            addCriterion("building_status >", value, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_status >=", value, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusLessThan(Integer value) {
            addCriterion("building_status <", value, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusLessThanOrEqualTo(Integer value) {
            addCriterion("building_status <=", value, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusIn(List<Integer> values) {
            addCriterion("building_status in", values, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusNotIn(List<Integer> values) {
            addCriterion("building_status not in", values, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusBetween(Integer value1, Integer value2) {
            addCriterion("building_status between", value1, value2, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andBuildingStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("building_status not between", value1, value2, "buildingStatus");
            return (Criteria) this;
        }

        public Criteria andQuoteIdIsNull() {
            addCriterion("quote_id is null");
            return (Criteria) this;
        }

        public Criteria andQuoteIdIsNotNull() {
            addCriterion("quote_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuoteIdEqualTo(Integer value) {
            addCriterion("quote_id =", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdNotEqualTo(Integer value) {
            addCriterion("quote_id <>", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdGreaterThan(Integer value) {
            addCriterion("quote_id >", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("quote_id >=", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdLessThan(Integer value) {
            addCriterion("quote_id <", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdLessThanOrEqualTo(Integer value) {
            addCriterion("quote_id <=", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdIn(List<Integer> values) {
            addCriterion("quote_id in", values, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdNotIn(List<Integer> values) {
            addCriterion("quote_id not in", values, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdBetween(Integer value1, Integer value2) {
            addCriterion("quote_id between", value1, value2, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("quote_id not between", value1, value2, "quoteId");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIsNull() {
            addCriterion("base_type is null");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIsNotNull() {
            addCriterion("base_type is not null");
            return (Criteria) this;
        }

        public Criteria andBaseTypeEqualTo(String value) {
            addCriterion("base_type =", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNotEqualTo(String value) {
            addCriterion("base_type <>", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeGreaterThan(String value) {
            addCriterion("base_type >", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeGreaterThanOrEqualTo(String value) {
            addCriterion("base_type >=", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeLessThan(String value) {
            addCriterion("base_type <", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeLessThanOrEqualTo(String value) {
            addCriterion("base_type <=", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeLike(String value) {
            addCriterion("base_type like", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNotLike(String value) {
            addCriterion("base_type not like", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIn(List<String> values) {
            addCriterion("base_type in", values, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNotIn(List<String> values) {
            addCriterion("base_type not in", values, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeBetween(String value1, String value2) {
            addCriterion("base_type between", value1, value2, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNotBetween(String value1, String value2) {
            addCriterion("base_type not between", value1, value2, "baseType");
            return (Criteria) this;
        }

        public Criteria andShowTabIsNull() {
            addCriterion("show_tab is null");
            return (Criteria) this;
        }

        public Criteria andShowTabIsNotNull() {
            addCriterion("show_tab is not null");
            return (Criteria) this;
        }

        public Criteria andShowTabEqualTo(Boolean value) {
            addCriterion("show_tab =", value, "showTab");
            return (Criteria) this;
        }

        public Criteria andShowTabNotEqualTo(Boolean value) {
            addCriterion("show_tab <>", value, "showTab");
            return (Criteria) this;
        }

        public Criteria andShowTabGreaterThan(Boolean value) {
            addCriterion("show_tab >", value, "showTab");
            return (Criteria) this;
        }

        public Criteria andShowTabGreaterThanOrEqualTo(Boolean value) {
            addCriterion("show_tab >=", value, "showTab");
            return (Criteria) this;
        }

        public Criteria andShowTabLessThan(Boolean value) {
            addCriterion("show_tab <", value, "showTab");
            return (Criteria) this;
        }

        public Criteria andShowTabLessThanOrEqualTo(Boolean value) {
            addCriterion("show_tab <=", value, "showTab");
            return (Criteria) this;
        }

        public Criteria andShowTabIn(List<Boolean> values) {
            addCriterion("show_tab in", values, "showTab");
            return (Criteria) this;
        }

        public Criteria andShowTabNotIn(List<Boolean> values) {
            addCriterion("show_tab not in", values, "showTab");
            return (Criteria) this;
        }

        public Criteria andShowTabBetween(Boolean value1, Boolean value2) {
            addCriterion("show_tab between", value1, value2, "showTab");
            return (Criteria) this;
        }

        public Criteria andShowTabNotBetween(Boolean value1, Boolean value2) {
            addCriterion("show_tab not between", value1, value2, "showTab");
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

        public Criteria andDraftFlagIsNull() {
            addCriterion("draft_flag is null");
            return (Criteria) this;
        }

        public Criteria andDraftFlagIsNotNull() {
            addCriterion("draft_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDraftFlagEqualTo(Boolean value) {
            addCriterion("draft_flag =", value, "draftFlag");
            return (Criteria) this;
        }

        public Criteria andDraftFlagNotEqualTo(Boolean value) {
            addCriterion("draft_flag <>", value, "draftFlag");
            return (Criteria) this;
        }

        public Criteria andDraftFlagGreaterThan(Boolean value) {
            addCriterion("draft_flag >", value, "draftFlag");
            return (Criteria) this;
        }

        public Criteria andDraftFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("draft_flag >=", value, "draftFlag");
            return (Criteria) this;
        }

        public Criteria andDraftFlagLessThan(Boolean value) {
            addCriterion("draft_flag <", value, "draftFlag");
            return (Criteria) this;
        }

        public Criteria andDraftFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("draft_flag <=", value, "draftFlag");
            return (Criteria) this;
        }

        public Criteria andDraftFlagIn(List<Boolean> values) {
            addCriterion("draft_flag in", values, "draftFlag");
            return (Criteria) this;
        }

        public Criteria andDraftFlagNotIn(List<Boolean> values) {
            addCriterion("draft_flag not in", values, "draftFlag");
            return (Criteria) this;
        }

        public Criteria andDraftFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("draft_flag between", value1, value2, "draftFlag");
            return (Criteria) this;
        }

        public Criteria andDraftFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("draft_flag not between", value1, value2, "draftFlag");
            return (Criteria) this;
        }

        public Criteria andBisQueryCaseIsNull() {
            addCriterion("bis_query_case is null");
            return (Criteria) this;
        }

        public Criteria andBisQueryCaseIsNotNull() {
            addCriterion("bis_query_case is not null");
            return (Criteria) this;
        }

        public Criteria andBisQueryCaseEqualTo(Boolean value) {
            addCriterion("bis_query_case =", value, "bisQueryCase");
            return (Criteria) this;
        }

        public Criteria andBisQueryCaseNotEqualTo(Boolean value) {
            addCriterion("bis_query_case <>", value, "bisQueryCase");
            return (Criteria) this;
        }

        public Criteria andBisQueryCaseGreaterThan(Boolean value) {
            addCriterion("bis_query_case >", value, "bisQueryCase");
            return (Criteria) this;
        }

        public Criteria andBisQueryCaseGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_query_case >=", value, "bisQueryCase");
            return (Criteria) this;
        }

        public Criteria andBisQueryCaseLessThan(Boolean value) {
            addCriterion("bis_query_case <", value, "bisQueryCase");
            return (Criteria) this;
        }

        public Criteria andBisQueryCaseLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_query_case <=", value, "bisQueryCase");
            return (Criteria) this;
        }

        public Criteria andBisQueryCaseIn(List<Boolean> values) {
            addCriterion("bis_query_case in", values, "bisQueryCase");
            return (Criteria) this;
        }

        public Criteria andBisQueryCaseNotIn(List<Boolean> values) {
            addCriterion("bis_query_case not in", values, "bisQueryCase");
            return (Criteria) this;
        }

        public Criteria andBisQueryCaseBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_query_case between", value1, value2, "bisQueryCase");
            return (Criteria) this;
        }

        public Criteria andBisQueryCaseNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_query_case not between", value1, value2, "bisQueryCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseIsNull() {
            addCriterion("bis_case is null");
            return (Criteria) this;
        }

        public Criteria andBisCaseIsNotNull() {
            addCriterion("bis_case is not null");
            return (Criteria) this;
        }

        public Criteria andBisCaseEqualTo(Boolean value) {
            addCriterion("bis_case =", value, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseNotEqualTo(Boolean value) {
            addCriterion("bis_case <>", value, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseGreaterThan(Boolean value) {
            addCriterion("bis_case >", value, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_case >=", value, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseLessThan(Boolean value) {
            addCriterion("bis_case <", value, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_case <=", value, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseIn(List<Boolean> values) {
            addCriterion("bis_case in", values, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseNotIn(List<Boolean> values) {
            addCriterion("bis_case not in", values, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_case between", value1, value2, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_case not between", value1, value2, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisDeleteIsNull() {
            addCriterion("bis_delete is null");
            return (Criteria) this;
        }

        public Criteria andBisDeleteIsNotNull() {
            addCriterion("bis_delete is not null");
            return (Criteria) this;
        }

        public Criteria andBisDeleteEqualTo(Boolean value) {
            addCriterion("bis_delete =", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteNotEqualTo(Boolean value) {
            addCriterion("bis_delete <>", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteGreaterThan(Boolean value) {
            addCriterion("bis_delete >", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_delete >=", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteLessThan(Boolean value) {
            addCriterion("bis_delete <", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_delete <=", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteIn(List<Boolean> values) {
            addCriterion("bis_delete in", values, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteNotIn(List<Boolean> values) {
            addCriterion("bis_delete not in", values, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_delete between", value1, value2, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_delete not between", value1, value2, "bisDelete");
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