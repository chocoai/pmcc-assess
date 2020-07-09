package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicApplyExample() {
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

        public Criteria andApplyBatchIdIsNull() {
            addCriterion("apply_batch_id is null");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdIsNotNull() {
            addCriterion("apply_batch_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdEqualTo(Integer value) {
            addCriterion("apply_batch_id =", value, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdNotEqualTo(Integer value) {
            addCriterion("apply_batch_id <>", value, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdGreaterThan(Integer value) {
            addCriterion("apply_batch_id >", value, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("apply_batch_id >=", value, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdLessThan(Integer value) {
            addCriterion("apply_batch_id <", value, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdLessThanOrEqualTo(Integer value) {
            addCriterion("apply_batch_id <=", value, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdIn(List<Integer> values) {
            addCriterion("apply_batch_id in", values, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdNotIn(List<Integer> values) {
            addCriterion("apply_batch_id not in", values, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdBetween(Integer value1, Integer value2) {
            addCriterion("apply_batch_id between", value1, value2, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("apply_batch_id not between", value1, value2, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andBatchDetailIdIsNull() {
            addCriterion("batch_detail_id is null");
            return (Criteria) this;
        }

        public Criteria andBatchDetailIdIsNotNull() {
            addCriterion("batch_detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andBatchDetailIdEqualTo(Integer value) {
            addCriterion("batch_detail_id =", value, "batchDetailId");
            return (Criteria) this;
        }

        public Criteria andBatchDetailIdNotEqualTo(Integer value) {
            addCriterion("batch_detail_id <>", value, "batchDetailId");
            return (Criteria) this;
        }

        public Criteria andBatchDetailIdGreaterThan(Integer value) {
            addCriterion("batch_detail_id >", value, "batchDetailId");
            return (Criteria) this;
        }

        public Criteria andBatchDetailIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("batch_detail_id >=", value, "batchDetailId");
            return (Criteria) this;
        }

        public Criteria andBatchDetailIdLessThan(Integer value) {
            addCriterion("batch_detail_id <", value, "batchDetailId");
            return (Criteria) this;
        }

        public Criteria andBatchDetailIdLessThanOrEqualTo(Integer value) {
            addCriterion("batch_detail_id <=", value, "batchDetailId");
            return (Criteria) this;
        }

        public Criteria andBatchDetailIdIn(List<Integer> values) {
            addCriterion("batch_detail_id in", values, "batchDetailId");
            return (Criteria) this;
        }

        public Criteria andBatchDetailIdNotIn(List<Integer> values) {
            addCriterion("batch_detail_id not in", values, "batchDetailId");
            return (Criteria) this;
        }

        public Criteria andBatchDetailIdBetween(Integer value1, Integer value2) {
            addCriterion("batch_detail_id between", value1, value2, "batchDetailId");
            return (Criteria) this;
        }

        public Criteria andBatchDetailIdNotBetween(Integer value1, Integer value2) {
            addCriterion("batch_detail_id not between", value1, value2, "batchDetailId");
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

        public Criteria andLandCategoryIdIsNull() {
            addCriterion("land_category_id is null");
            return (Criteria) this;
        }

        public Criteria andLandCategoryIdIsNotNull() {
            addCriterion("land_category_id is not null");
            return (Criteria) this;
        }

        public Criteria andLandCategoryIdEqualTo(Integer value) {
            addCriterion("land_category_id =", value, "landCategoryId");
            return (Criteria) this;
        }

        public Criteria andLandCategoryIdNotEqualTo(Integer value) {
            addCriterion("land_category_id <>", value, "landCategoryId");
            return (Criteria) this;
        }

        public Criteria andLandCategoryIdGreaterThan(Integer value) {
            addCriterion("land_category_id >", value, "landCategoryId");
            return (Criteria) this;
        }

        public Criteria andLandCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("land_category_id >=", value, "landCategoryId");
            return (Criteria) this;
        }

        public Criteria andLandCategoryIdLessThan(Integer value) {
            addCriterion("land_category_id <", value, "landCategoryId");
            return (Criteria) this;
        }

        public Criteria andLandCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("land_category_id <=", value, "landCategoryId");
            return (Criteria) this;
        }

        public Criteria andLandCategoryIdIn(List<Integer> values) {
            addCriterion("land_category_id in", values, "landCategoryId");
            return (Criteria) this;
        }

        public Criteria andLandCategoryIdNotIn(List<Integer> values) {
            addCriterion("land_category_id not in", values, "landCategoryId");
            return (Criteria) this;
        }

        public Criteria andLandCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("land_category_id between", value1, value2, "landCategoryId");
            return (Criteria) this;
        }

        public Criteria andLandCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("land_category_id not between", value1, value2, "landCategoryId");
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

        public Criteria andStructuralInfoIsNull() {
            addCriterion("structural_info is null");
            return (Criteria) this;
        }

        public Criteria andStructuralInfoIsNotNull() {
            addCriterion("structural_info is not null");
            return (Criteria) this;
        }

        public Criteria andStructuralInfoEqualTo(String value) {
            addCriterion("structural_info =", value, "structuralInfo");
            return (Criteria) this;
        }

        public Criteria andStructuralInfoNotEqualTo(String value) {
            addCriterion("structural_info <>", value, "structuralInfo");
            return (Criteria) this;
        }

        public Criteria andStructuralInfoGreaterThan(String value) {
            addCriterion("structural_info >", value, "structuralInfo");
            return (Criteria) this;
        }

        public Criteria andStructuralInfoGreaterThanOrEqualTo(String value) {
            addCriterion("structural_info >=", value, "structuralInfo");
            return (Criteria) this;
        }

        public Criteria andStructuralInfoLessThan(String value) {
            addCriterion("structural_info <", value, "structuralInfo");
            return (Criteria) this;
        }

        public Criteria andStructuralInfoLessThanOrEqualTo(String value) {
            addCriterion("structural_info <=", value, "structuralInfo");
            return (Criteria) this;
        }

        public Criteria andStructuralInfoLike(String value) {
            addCriterion("structural_info like", value, "structuralInfo");
            return (Criteria) this;
        }

        public Criteria andStructuralInfoNotLike(String value) {
            addCriterion("structural_info not like", value, "structuralInfo");
            return (Criteria) this;
        }

        public Criteria andStructuralInfoIn(List<String> values) {
            addCriterion("structural_info in", values, "structuralInfo");
            return (Criteria) this;
        }

        public Criteria andStructuralInfoNotIn(List<String> values) {
            addCriterion("structural_info not in", values, "structuralInfo");
            return (Criteria) this;
        }

        public Criteria andStructuralInfoBetween(String value1, String value2) {
            addCriterion("structural_info between", value1, value2, "structuralInfo");
            return (Criteria) this;
        }

        public Criteria andStructuralInfoNotBetween(String value1, String value2) {
            addCriterion("structural_info not between", value1, value2, "structuralInfo");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(BigDecimal value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(BigDecimal value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(BigDecimal value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(BigDecimal value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<BigDecimal> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<BigDecimal> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andBasicEstateIdIsNull() {
            addCriterion("basic_estate_id is null");
            return (Criteria) this;
        }

        public Criteria andBasicEstateIdIsNotNull() {
            addCriterion("basic_estate_id is not null");
            return (Criteria) this;
        }

        public Criteria andBasicEstateIdEqualTo(Integer value) {
            addCriterion("basic_estate_id =", value, "basicEstateId");
            return (Criteria) this;
        }

        public Criteria andBasicEstateIdNotEqualTo(Integer value) {
            addCriterion("basic_estate_id <>", value, "basicEstateId");
            return (Criteria) this;
        }

        public Criteria andBasicEstateIdGreaterThan(Integer value) {
            addCriterion("basic_estate_id >", value, "basicEstateId");
            return (Criteria) this;
        }

        public Criteria andBasicEstateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("basic_estate_id >=", value, "basicEstateId");
            return (Criteria) this;
        }

        public Criteria andBasicEstateIdLessThan(Integer value) {
            addCriterion("basic_estate_id <", value, "basicEstateId");
            return (Criteria) this;
        }

        public Criteria andBasicEstateIdLessThanOrEqualTo(Integer value) {
            addCriterion("basic_estate_id <=", value, "basicEstateId");
            return (Criteria) this;
        }

        public Criteria andBasicEstateIdIn(List<Integer> values) {
            addCriterion("basic_estate_id in", values, "basicEstateId");
            return (Criteria) this;
        }

        public Criteria andBasicEstateIdNotIn(List<Integer> values) {
            addCriterion("basic_estate_id not in", values, "basicEstateId");
            return (Criteria) this;
        }

        public Criteria andBasicEstateIdBetween(Integer value1, Integer value2) {
            addCriterion("basic_estate_id between", value1, value2, "basicEstateId");
            return (Criteria) this;
        }

        public Criteria andBasicEstateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("basic_estate_id not between", value1, value2, "basicEstateId");
            return (Criteria) this;
        }

        public Criteria andBasicBuildingIdIsNull() {
            addCriterion("basic_building_id is null");
            return (Criteria) this;
        }

        public Criteria andBasicBuildingIdIsNotNull() {
            addCriterion("basic_building_id is not null");
            return (Criteria) this;
        }

        public Criteria andBasicBuildingIdEqualTo(Integer value) {
            addCriterion("basic_building_id =", value, "basicBuildingId");
            return (Criteria) this;
        }

        public Criteria andBasicBuildingIdNotEqualTo(Integer value) {
            addCriterion("basic_building_id <>", value, "basicBuildingId");
            return (Criteria) this;
        }

        public Criteria andBasicBuildingIdGreaterThan(Integer value) {
            addCriterion("basic_building_id >", value, "basicBuildingId");
            return (Criteria) this;
        }

        public Criteria andBasicBuildingIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("basic_building_id >=", value, "basicBuildingId");
            return (Criteria) this;
        }

        public Criteria andBasicBuildingIdLessThan(Integer value) {
            addCriterion("basic_building_id <", value, "basicBuildingId");
            return (Criteria) this;
        }

        public Criteria andBasicBuildingIdLessThanOrEqualTo(Integer value) {
            addCriterion("basic_building_id <=", value, "basicBuildingId");
            return (Criteria) this;
        }

        public Criteria andBasicBuildingIdIn(List<Integer> values) {
            addCriterion("basic_building_id in", values, "basicBuildingId");
            return (Criteria) this;
        }

        public Criteria andBasicBuildingIdNotIn(List<Integer> values) {
            addCriterion("basic_building_id not in", values, "basicBuildingId");
            return (Criteria) this;
        }

        public Criteria andBasicBuildingIdBetween(Integer value1, Integer value2) {
            addCriterion("basic_building_id between", value1, value2, "basicBuildingId");
            return (Criteria) this;
        }

        public Criteria andBasicBuildingIdNotBetween(Integer value1, Integer value2) {
            addCriterion("basic_building_id not between", value1, value2, "basicBuildingId");
            return (Criteria) this;
        }

        public Criteria andBasicUnitIdIsNull() {
            addCriterion("basic_unit_id is null");
            return (Criteria) this;
        }

        public Criteria andBasicUnitIdIsNotNull() {
            addCriterion("basic_unit_id is not null");
            return (Criteria) this;
        }

        public Criteria andBasicUnitIdEqualTo(Integer value) {
            addCriterion("basic_unit_id =", value, "basicUnitId");
            return (Criteria) this;
        }

        public Criteria andBasicUnitIdNotEqualTo(Integer value) {
            addCriterion("basic_unit_id <>", value, "basicUnitId");
            return (Criteria) this;
        }

        public Criteria andBasicUnitIdGreaterThan(Integer value) {
            addCriterion("basic_unit_id >", value, "basicUnitId");
            return (Criteria) this;
        }

        public Criteria andBasicUnitIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("basic_unit_id >=", value, "basicUnitId");
            return (Criteria) this;
        }

        public Criteria andBasicUnitIdLessThan(Integer value) {
            addCriterion("basic_unit_id <", value, "basicUnitId");
            return (Criteria) this;
        }

        public Criteria andBasicUnitIdLessThanOrEqualTo(Integer value) {
            addCriterion("basic_unit_id <=", value, "basicUnitId");
            return (Criteria) this;
        }

        public Criteria andBasicUnitIdIn(List<Integer> values) {
            addCriterion("basic_unit_id in", values, "basicUnitId");
            return (Criteria) this;
        }

        public Criteria andBasicUnitIdNotIn(List<Integer> values) {
            addCriterion("basic_unit_id not in", values, "basicUnitId");
            return (Criteria) this;
        }

        public Criteria andBasicUnitIdBetween(Integer value1, Integer value2) {
            addCriterion("basic_unit_id between", value1, value2, "basicUnitId");
            return (Criteria) this;
        }

        public Criteria andBasicUnitIdNotBetween(Integer value1, Integer value2) {
            addCriterion("basic_unit_id not between", value1, value2, "basicUnitId");
            return (Criteria) this;
        }

        public Criteria andBasicHouseIdIsNull() {
            addCriterion("basic_house_id is null");
            return (Criteria) this;
        }

        public Criteria andBasicHouseIdIsNotNull() {
            addCriterion("basic_house_id is not null");
            return (Criteria) this;
        }

        public Criteria andBasicHouseIdEqualTo(Integer value) {
            addCriterion("basic_house_id =", value, "basicHouseId");
            return (Criteria) this;
        }

        public Criteria andBasicHouseIdNotEqualTo(Integer value) {
            addCriterion("basic_house_id <>", value, "basicHouseId");
            return (Criteria) this;
        }

        public Criteria andBasicHouseIdGreaterThan(Integer value) {
            addCriterion("basic_house_id >", value, "basicHouseId");
            return (Criteria) this;
        }

        public Criteria andBasicHouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("basic_house_id >=", value, "basicHouseId");
            return (Criteria) this;
        }

        public Criteria andBasicHouseIdLessThan(Integer value) {
            addCriterion("basic_house_id <", value, "basicHouseId");
            return (Criteria) this;
        }

        public Criteria andBasicHouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("basic_house_id <=", value, "basicHouseId");
            return (Criteria) this;
        }

        public Criteria andBasicHouseIdIn(List<Integer> values) {
            addCriterion("basic_house_id in", values, "basicHouseId");
            return (Criteria) this;
        }

        public Criteria andBasicHouseIdNotIn(List<Integer> values) {
            addCriterion("basic_house_id not in", values, "basicHouseId");
            return (Criteria) this;
        }

        public Criteria andBasicHouseIdBetween(Integer value1, Integer value2) {
            addCriterion("basic_house_id between", value1, value2, "basicHouseId");
            return (Criteria) this;
        }

        public Criteria andBasicHouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("basic_house_id not between", value1, value2, "basicHouseId");
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