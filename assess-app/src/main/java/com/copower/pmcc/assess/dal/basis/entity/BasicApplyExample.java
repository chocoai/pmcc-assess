package com.copower.pmcc.assess.dal.basis.entity;

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

        public Criteria andCaseEstateIdIsNull() {
            addCriterion("case_estate_id is null");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdIsNotNull() {
            addCriterion("case_estate_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdEqualTo(Integer value) {
            addCriterion("case_estate_id =", value, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdNotEqualTo(Integer value) {
            addCriterion("case_estate_id <>", value, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdGreaterThan(Integer value) {
            addCriterion("case_estate_id >", value, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_estate_id >=", value, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdLessThan(Integer value) {
            addCriterion("case_estate_id <", value, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdLessThanOrEqualTo(Integer value) {
            addCriterion("case_estate_id <=", value, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdIn(List<Integer> values) {
            addCriterion("case_estate_id in", values, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdNotIn(List<Integer> values) {
            addCriterion("case_estate_id not in", values, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdBetween(Integer value1, Integer value2) {
            addCriterion("case_estate_id between", value1, value2, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("case_estate_id not between", value1, value2, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingIdIsNull() {
            addCriterion("case_building_id is null");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingIdIsNotNull() {
            addCriterion("case_building_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingIdEqualTo(Integer value) {
            addCriterion("case_building_id =", value, "caseBuildingId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingIdNotEqualTo(Integer value) {
            addCriterion("case_building_id <>", value, "caseBuildingId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingIdGreaterThan(Integer value) {
            addCriterion("case_building_id >", value, "caseBuildingId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_building_id >=", value, "caseBuildingId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingIdLessThan(Integer value) {
            addCriterion("case_building_id <", value, "caseBuildingId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingIdLessThanOrEqualTo(Integer value) {
            addCriterion("case_building_id <=", value, "caseBuildingId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingIdIn(List<Integer> values) {
            addCriterion("case_building_id in", values, "caseBuildingId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingIdNotIn(List<Integer> values) {
            addCriterion("case_building_id not in", values, "caseBuildingId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingIdBetween(Integer value1, Integer value2) {
            addCriterion("case_building_id between", value1, value2, "caseBuildingId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingIdNotBetween(Integer value1, Integer value2) {
            addCriterion("case_building_id not between", value1, value2, "caseBuildingId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdIsNull() {
            addCriterion("case_unit_id is null");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdIsNotNull() {
            addCriterion("case_unit_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdEqualTo(Integer value) {
            addCriterion("case_unit_id =", value, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdNotEqualTo(Integer value) {
            addCriterion("case_unit_id <>", value, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdGreaterThan(Integer value) {
            addCriterion("case_unit_id >", value, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_unit_id >=", value, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdLessThan(Integer value) {
            addCriterion("case_unit_id <", value, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdLessThanOrEqualTo(Integer value) {
            addCriterion("case_unit_id <=", value, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdIn(List<Integer> values) {
            addCriterion("case_unit_id in", values, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdNotIn(List<Integer> values) {
            addCriterion("case_unit_id not in", values, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdBetween(Integer value1, Integer value2) {
            addCriterion("case_unit_id between", value1, value2, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdNotBetween(Integer value1, Integer value2) {
            addCriterion("case_unit_id not between", value1, value2, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdIsNull() {
            addCriterion("case_house_id is null");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdIsNotNull() {
            addCriterion("case_house_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdEqualTo(Integer value) {
            addCriterion("case_house_id =", value, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdNotEqualTo(Integer value) {
            addCriterion("case_house_id <>", value, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdGreaterThan(Integer value) {
            addCriterion("case_house_id >", value, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_house_id >=", value, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdLessThan(Integer value) {
            addCriterion("case_house_id <", value, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("case_house_id <=", value, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdIn(List<Integer> values) {
            addCriterion("case_house_id in", values, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdNotIn(List<Integer> values) {
            addCriterion("case_house_id not in", values, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdBetween(Integer value1, Integer value2) {
            addCriterion("case_house_id between", value1, value2, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("case_house_id not between", value1, value2, "caseHouseId");
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

        public Criteria andBuildingNumberIsNull() {
            addCriterion("building_number is null");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberIsNotNull() {
            addCriterion("building_number is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberEqualTo(String value) {
            addCriterion("building_number =", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberNotEqualTo(String value) {
            addCriterion("building_number <>", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberGreaterThan(String value) {
            addCriterion("building_number >", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberGreaterThanOrEqualTo(String value) {
            addCriterion("building_number >=", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberLessThan(String value) {
            addCriterion("building_number <", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberLessThanOrEqualTo(String value) {
            addCriterion("building_number <=", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberLike(String value) {
            addCriterion("building_number like", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberNotLike(String value) {
            addCriterion("building_number not like", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberIn(List<String> values) {
            addCriterion("building_number in", values, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberNotIn(List<String> values) {
            addCriterion("building_number not in", values, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberBetween(String value1, String value2) {
            addCriterion("building_number between", value1, value2, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberNotBetween(String value1, String value2) {
            addCriterion("building_number not between", value1, value2, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberIsNull() {
            addCriterion("unit_number is null");
            return (Criteria) this;
        }

        public Criteria andUnitNumberIsNotNull() {
            addCriterion("unit_number is not null");
            return (Criteria) this;
        }

        public Criteria andUnitNumberEqualTo(String value) {
            addCriterion("unit_number =", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberNotEqualTo(String value) {
            addCriterion("unit_number <>", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberGreaterThan(String value) {
            addCriterion("unit_number >", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberGreaterThanOrEqualTo(String value) {
            addCriterion("unit_number >=", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberLessThan(String value) {
            addCriterion("unit_number <", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberLessThanOrEqualTo(String value) {
            addCriterion("unit_number <=", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberLike(String value) {
            addCriterion("unit_number like", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberNotLike(String value) {
            addCriterion("unit_number not like", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberIn(List<String> values) {
            addCriterion("unit_number in", values, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberNotIn(List<String> values) {
            addCriterion("unit_number not in", values, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberBetween(String value1, String value2) {
            addCriterion("unit_number between", value1, value2, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberNotBetween(String value1, String value2) {
            addCriterion("unit_number not between", value1, value2, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberIsNull() {
            addCriterion("house_number is null");
            return (Criteria) this;
        }

        public Criteria andHouseNumberIsNotNull() {
            addCriterion("house_number is not null");
            return (Criteria) this;
        }

        public Criteria andHouseNumberEqualTo(String value) {
            addCriterion("house_number =", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberNotEqualTo(String value) {
            addCriterion("house_number <>", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberGreaterThan(String value) {
            addCriterion("house_number >", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberGreaterThanOrEqualTo(String value) {
            addCriterion("house_number >=", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberLessThan(String value) {
            addCriterion("house_number <", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberLessThanOrEqualTo(String value) {
            addCriterion("house_number <=", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberLike(String value) {
            addCriterion("house_number like", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberNotLike(String value) {
            addCriterion("house_number not like", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberIn(List<String> values) {
            addCriterion("house_number in", values, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberNotIn(List<String> values) {
            addCriterion("house_number not in", values, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberBetween(String value1, String value2) {
            addCriterion("house_number between", value1, value2, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberNotBetween(String value1, String value2) {
            addCriterion("house_number not between", value1, value2, "houseNumber");
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

        public Criteria andEstatePartInModeIsNull() {
            addCriterion("estate_part_in_mode is null");
            return (Criteria) this;
        }

        public Criteria andEstatePartInModeIsNotNull() {
            addCriterion("estate_part_in_mode is not null");
            return (Criteria) this;
        }

        public Criteria andEstatePartInModeEqualTo(String value) {
            addCriterion("estate_part_in_mode =", value, "estatePartInMode");
            return (Criteria) this;
        }

        public Criteria andEstatePartInModeNotEqualTo(String value) {
            addCriterion("estate_part_in_mode <>", value, "estatePartInMode");
            return (Criteria) this;
        }

        public Criteria andEstatePartInModeGreaterThan(String value) {
            addCriterion("estate_part_in_mode >", value, "estatePartInMode");
            return (Criteria) this;
        }

        public Criteria andEstatePartInModeGreaterThanOrEqualTo(String value) {
            addCriterion("estate_part_in_mode >=", value, "estatePartInMode");
            return (Criteria) this;
        }

        public Criteria andEstatePartInModeLessThan(String value) {
            addCriterion("estate_part_in_mode <", value, "estatePartInMode");
            return (Criteria) this;
        }

        public Criteria andEstatePartInModeLessThanOrEqualTo(String value) {
            addCriterion("estate_part_in_mode <=", value, "estatePartInMode");
            return (Criteria) this;
        }

        public Criteria andEstatePartInModeLike(String value) {
            addCriterion("estate_part_in_mode like", value, "estatePartInMode");
            return (Criteria) this;
        }

        public Criteria andEstatePartInModeNotLike(String value) {
            addCriterion("estate_part_in_mode not like", value, "estatePartInMode");
            return (Criteria) this;
        }

        public Criteria andEstatePartInModeIn(List<String> values) {
            addCriterion("estate_part_in_mode in", values, "estatePartInMode");
            return (Criteria) this;
        }

        public Criteria andEstatePartInModeNotIn(List<String> values) {
            addCriterion("estate_part_in_mode not in", values, "estatePartInMode");
            return (Criteria) this;
        }

        public Criteria andEstatePartInModeBetween(String value1, String value2) {
            addCriterion("estate_part_in_mode between", value1, value2, "estatePartInMode");
            return (Criteria) this;
        }

        public Criteria andEstatePartInModeNotBetween(String value1, String value2) {
            addCriterion("estate_part_in_mode not between", value1, value2, "estatePartInMode");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInModeIsNull() {
            addCriterion("building_part_in_mode is null");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInModeIsNotNull() {
            addCriterion("building_part_in_mode is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInModeEqualTo(String value) {
            addCriterion("building_part_in_mode =", value, "buildingPartInMode");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInModeNotEqualTo(String value) {
            addCriterion("building_part_in_mode <>", value, "buildingPartInMode");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInModeGreaterThan(String value) {
            addCriterion("building_part_in_mode >", value, "buildingPartInMode");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInModeGreaterThanOrEqualTo(String value) {
            addCriterion("building_part_in_mode >=", value, "buildingPartInMode");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInModeLessThan(String value) {
            addCriterion("building_part_in_mode <", value, "buildingPartInMode");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInModeLessThanOrEqualTo(String value) {
            addCriterion("building_part_in_mode <=", value, "buildingPartInMode");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInModeLike(String value) {
            addCriterion("building_part_in_mode like", value, "buildingPartInMode");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInModeNotLike(String value) {
            addCriterion("building_part_in_mode not like", value, "buildingPartInMode");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInModeIn(List<String> values) {
            addCriterion("building_part_in_mode in", values, "buildingPartInMode");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInModeNotIn(List<String> values) {
            addCriterion("building_part_in_mode not in", values, "buildingPartInMode");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInModeBetween(String value1, String value2) {
            addCriterion("building_part_in_mode between", value1, value2, "buildingPartInMode");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInModeNotBetween(String value1, String value2) {
            addCriterion("building_part_in_mode not between", value1, value2, "buildingPartInMode");
            return (Criteria) this;
        }

        public Criteria andUnitPartInModeIsNull() {
            addCriterion("unit_part_in_mode is null");
            return (Criteria) this;
        }

        public Criteria andUnitPartInModeIsNotNull() {
            addCriterion("unit_part_in_mode is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPartInModeEqualTo(String value) {
            addCriterion("unit_part_in_mode =", value, "unitPartInMode");
            return (Criteria) this;
        }

        public Criteria andUnitPartInModeNotEqualTo(String value) {
            addCriterion("unit_part_in_mode <>", value, "unitPartInMode");
            return (Criteria) this;
        }

        public Criteria andUnitPartInModeGreaterThan(String value) {
            addCriterion("unit_part_in_mode >", value, "unitPartInMode");
            return (Criteria) this;
        }

        public Criteria andUnitPartInModeGreaterThanOrEqualTo(String value) {
            addCriterion("unit_part_in_mode >=", value, "unitPartInMode");
            return (Criteria) this;
        }

        public Criteria andUnitPartInModeLessThan(String value) {
            addCriterion("unit_part_in_mode <", value, "unitPartInMode");
            return (Criteria) this;
        }

        public Criteria andUnitPartInModeLessThanOrEqualTo(String value) {
            addCriterion("unit_part_in_mode <=", value, "unitPartInMode");
            return (Criteria) this;
        }

        public Criteria andUnitPartInModeLike(String value) {
            addCriterion("unit_part_in_mode like", value, "unitPartInMode");
            return (Criteria) this;
        }

        public Criteria andUnitPartInModeNotLike(String value) {
            addCriterion("unit_part_in_mode not like", value, "unitPartInMode");
            return (Criteria) this;
        }

        public Criteria andUnitPartInModeIn(List<String> values) {
            addCriterion("unit_part_in_mode in", values, "unitPartInMode");
            return (Criteria) this;
        }

        public Criteria andUnitPartInModeNotIn(List<String> values) {
            addCriterion("unit_part_in_mode not in", values, "unitPartInMode");
            return (Criteria) this;
        }

        public Criteria andUnitPartInModeBetween(String value1, String value2) {
            addCriterion("unit_part_in_mode between", value1, value2, "unitPartInMode");
            return (Criteria) this;
        }

        public Criteria andUnitPartInModeNotBetween(String value1, String value2) {
            addCriterion("unit_part_in_mode not between", value1, value2, "unitPartInMode");
            return (Criteria) this;
        }

        public Criteria andHousePartInModeIsNull() {
            addCriterion("house_part_in_mode is null");
            return (Criteria) this;
        }

        public Criteria andHousePartInModeIsNotNull() {
            addCriterion("house_part_in_mode is not null");
            return (Criteria) this;
        }

        public Criteria andHousePartInModeEqualTo(String value) {
            addCriterion("house_part_in_mode =", value, "housePartInMode");
            return (Criteria) this;
        }

        public Criteria andHousePartInModeNotEqualTo(String value) {
            addCriterion("house_part_in_mode <>", value, "housePartInMode");
            return (Criteria) this;
        }

        public Criteria andHousePartInModeGreaterThan(String value) {
            addCriterion("house_part_in_mode >", value, "housePartInMode");
            return (Criteria) this;
        }

        public Criteria andHousePartInModeGreaterThanOrEqualTo(String value) {
            addCriterion("house_part_in_mode >=", value, "housePartInMode");
            return (Criteria) this;
        }

        public Criteria andHousePartInModeLessThan(String value) {
            addCriterion("house_part_in_mode <", value, "housePartInMode");
            return (Criteria) this;
        }

        public Criteria andHousePartInModeLessThanOrEqualTo(String value) {
            addCriterion("house_part_in_mode <=", value, "housePartInMode");
            return (Criteria) this;
        }

        public Criteria andHousePartInModeLike(String value) {
            addCriterion("house_part_in_mode like", value, "housePartInMode");
            return (Criteria) this;
        }

        public Criteria andHousePartInModeNotLike(String value) {
            addCriterion("house_part_in_mode not like", value, "housePartInMode");
            return (Criteria) this;
        }

        public Criteria andHousePartInModeIn(List<String> values) {
            addCriterion("house_part_in_mode in", values, "housePartInMode");
            return (Criteria) this;
        }

        public Criteria andHousePartInModeNotIn(List<String> values) {
            addCriterion("house_part_in_mode not in", values, "housePartInMode");
            return (Criteria) this;
        }

        public Criteria andHousePartInModeBetween(String value1, String value2) {
            addCriterion("house_part_in_mode between", value1, value2, "housePartInMode");
            return (Criteria) this;
        }

        public Criteria andHousePartInModeNotBetween(String value1, String value2) {
            addCriterion("house_part_in_mode not between", value1, value2, "housePartInMode");
            return (Criteria) this;
        }

        public Criteria andWriteBackBlockFlagIsNull() {
            addCriterion("write_back_block_flag is null");
            return (Criteria) this;
        }

        public Criteria andWriteBackBlockFlagIsNotNull() {
            addCriterion("write_back_block_flag is not null");
            return (Criteria) this;
        }

        public Criteria andWriteBackBlockFlagEqualTo(Boolean value) {
            addCriterion("write_back_block_flag =", value, "writeBackBlockFlag");
            return (Criteria) this;
        }

        public Criteria andWriteBackBlockFlagNotEqualTo(Boolean value) {
            addCriterion("write_back_block_flag <>", value, "writeBackBlockFlag");
            return (Criteria) this;
        }

        public Criteria andWriteBackBlockFlagGreaterThan(Boolean value) {
            addCriterion("write_back_block_flag >", value, "writeBackBlockFlag");
            return (Criteria) this;
        }

        public Criteria andWriteBackBlockFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("write_back_block_flag >=", value, "writeBackBlockFlag");
            return (Criteria) this;
        }

        public Criteria andWriteBackBlockFlagLessThan(Boolean value) {
            addCriterion("write_back_block_flag <", value, "writeBackBlockFlag");
            return (Criteria) this;
        }

        public Criteria andWriteBackBlockFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("write_back_block_flag <=", value, "writeBackBlockFlag");
            return (Criteria) this;
        }

        public Criteria andWriteBackBlockFlagIn(List<Boolean> values) {
            addCriterion("write_back_block_flag in", values, "writeBackBlockFlag");
            return (Criteria) this;
        }

        public Criteria andWriteBackBlockFlagNotIn(List<Boolean> values) {
            addCriterion("write_back_block_flag not in", values, "writeBackBlockFlag");
            return (Criteria) this;
        }

        public Criteria andWriteBackBlockFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("write_back_block_flag between", value1, value2, "writeBackBlockFlag");
            return (Criteria) this;
        }

        public Criteria andWriteBackBlockFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("write_back_block_flag not between", value1, value2, "writeBackBlockFlag");
            return (Criteria) this;
        }

        public Criteria andCopyIdIsNull() {
            addCriterion("copy_id is null");
            return (Criteria) this;
        }

        public Criteria andCopyIdIsNotNull() {
            addCriterion("copy_id is not null");
            return (Criteria) this;
        }

        public Criteria andCopyIdEqualTo(Integer value) {
            addCriterion("copy_id =", value, "copyId");
            return (Criteria) this;
        }

        public Criteria andCopyIdNotEqualTo(Integer value) {
            addCriterion("copy_id <>", value, "copyId");
            return (Criteria) this;
        }

        public Criteria andCopyIdGreaterThan(Integer value) {
            addCriterion("copy_id >", value, "copyId");
            return (Criteria) this;
        }

        public Criteria andCopyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("copy_id >=", value, "copyId");
            return (Criteria) this;
        }

        public Criteria andCopyIdLessThan(Integer value) {
            addCriterion("copy_id <", value, "copyId");
            return (Criteria) this;
        }

        public Criteria andCopyIdLessThanOrEqualTo(Integer value) {
            addCriterion("copy_id <=", value, "copyId");
            return (Criteria) this;
        }

        public Criteria andCopyIdIn(List<Integer> values) {
            addCriterion("copy_id in", values, "copyId");
            return (Criteria) this;
        }

        public Criteria andCopyIdNotIn(List<Integer> values) {
            addCriterion("copy_id not in", values, "copyId");
            return (Criteria) this;
        }

        public Criteria andCopyIdBetween(Integer value1, Integer value2) {
            addCriterion("copy_id between", value1, value2, "copyId");
            return (Criteria) this;
        }

        public Criteria andCopyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("copy_id not between", value1, value2, "copyId");
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