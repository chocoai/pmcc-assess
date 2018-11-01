package com.copower.pmcc.assess.dal.cases.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaseBuildingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CaseBuildingExample() {
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

        public Criteria andPartIsNull() {
            addCriterion("part is null");
            return (Criteria) this;
        }

        public Criteria andPartIsNotNull() {
            addCriterion("part is not null");
            return (Criteria) this;
        }

        public Criteria andPartEqualTo(Integer value) {
            addCriterion("part =", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartNotEqualTo(Integer value) {
            addCriterion("part <>", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartGreaterThan(Integer value) {
            addCriterion("part >", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartGreaterThanOrEqualTo(Integer value) {
            addCriterion("part >=", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartLessThan(Integer value) {
            addCriterion("part <", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartLessThanOrEqualTo(Integer value) {
            addCriterion("part <=", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartIn(List<Integer> values) {
            addCriterion("part in", values, "part");
            return (Criteria) this;
        }

        public Criteria andPartNotIn(List<Integer> values) {
            addCriterion("part not in", values, "part");
            return (Criteria) this;
        }

        public Criteria andPartBetween(Integer value1, Integer value2) {
            addCriterion("part between", value1, value2, "part");
            return (Criteria) this;
        }

        public Criteria andPartNotBetween(Integer value1, Integer value2) {
            addCriterion("part not between", value1, value2, "part");
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

        public Criteria andCaseBuildingMainIdIsNull() {
            addCriterion("case_building_main_id is null");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdIsNotNull() {
            addCriterion("case_building_main_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdEqualTo(Integer value) {
            addCriterion("case_building_main_id =", value, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdNotEqualTo(Integer value) {
            addCriterion("case_building_main_id <>", value, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdGreaterThan(Integer value) {
            addCriterion("case_building_main_id >", value, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_building_main_id >=", value, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdLessThan(Integer value) {
            addCriterion("case_building_main_id <", value, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdLessThanOrEqualTo(Integer value) {
            addCriterion("case_building_main_id <=", value, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdIn(List<Integer> values) {
            addCriterion("case_building_main_id in", values, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdNotIn(List<Integer> values) {
            addCriterion("case_building_main_id not in", values, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdBetween(Integer value1, Integer value2) {
            addCriterion("case_building_main_id between", value1, value2, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdNotBetween(Integer value1, Integer value2) {
            addCriterion("case_building_main_id not between", value1, value2, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andBuilderIdIsNull() {
            addCriterion("builder_id is null");
            return (Criteria) this;
        }

        public Criteria andBuilderIdIsNotNull() {
            addCriterion("builder_id is not null");
            return (Criteria) this;
        }

        public Criteria andBuilderIdEqualTo(Integer value) {
            addCriterion("builder_id =", value, "builderId");
            return (Criteria) this;
        }

        public Criteria andBuilderIdNotEqualTo(Integer value) {
            addCriterion("builder_id <>", value, "builderId");
            return (Criteria) this;
        }

        public Criteria andBuilderIdGreaterThan(Integer value) {
            addCriterion("builder_id >", value, "builderId");
            return (Criteria) this;
        }

        public Criteria andBuilderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("builder_id >=", value, "builderId");
            return (Criteria) this;
        }

        public Criteria andBuilderIdLessThan(Integer value) {
            addCriterion("builder_id <", value, "builderId");
            return (Criteria) this;
        }

        public Criteria andBuilderIdLessThanOrEqualTo(Integer value) {
            addCriterion("builder_id <=", value, "builderId");
            return (Criteria) this;
        }

        public Criteria andBuilderIdIn(List<Integer> values) {
            addCriterion("builder_id in", values, "builderId");
            return (Criteria) this;
        }

        public Criteria andBuilderIdNotIn(List<Integer> values) {
            addCriterion("builder_id not in", values, "builderId");
            return (Criteria) this;
        }

        public Criteria andBuilderIdBetween(Integer value1, Integer value2) {
            addCriterion("builder_id between", value1, value2, "builderId");
            return (Criteria) this;
        }

        public Criteria andBuilderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("builder_id not between", value1, value2, "builderId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdIsNull() {
            addCriterion("property_Id is null");
            return (Criteria) this;
        }

        public Criteria andPropertyIdIsNotNull() {
            addCriterion("property_Id is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyIdEqualTo(Integer value) {
            addCriterion("property_Id =", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdNotEqualTo(Integer value) {
            addCriterion("property_Id <>", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdGreaterThan(Integer value) {
            addCriterion("property_Id >", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("property_Id >=", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdLessThan(Integer value) {
            addCriterion("property_Id <", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdLessThanOrEqualTo(Integer value) {
            addCriterion("property_Id <=", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdIn(List<Integer> values) {
            addCriterion("property_Id in", values, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdNotIn(List<Integer> values) {
            addCriterion("property_Id not in", values, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdBetween(Integer value1, Integer value2) {
            addCriterion("property_Id between", value1, value2, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("property_Id not between", value1, value2, "propertyId");
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

        public Criteria andBuildingNameIsNull() {
            addCriterion("building_name is null");
            return (Criteria) this;
        }

        public Criteria andBuildingNameIsNotNull() {
            addCriterion("building_name is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingNameEqualTo(String value) {
            addCriterion("building_name =", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotEqualTo(String value) {
            addCriterion("building_name <>", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameGreaterThan(String value) {
            addCriterion("building_name >", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameGreaterThanOrEqualTo(String value) {
            addCriterion("building_name >=", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameLessThan(String value) {
            addCriterion("building_name <", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameLessThanOrEqualTo(String value) {
            addCriterion("building_name <=", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameLike(String value) {
            addCriterion("building_name like", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotLike(String value) {
            addCriterion("building_name not like", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameIn(List<String> values) {
            addCriterion("building_name in", values, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotIn(List<String> values) {
            addCriterion("building_name not in", values, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameBetween(String value1, String value2) {
            addCriterion("building_name between", value1, value2, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotBetween(String value1, String value2) {
            addCriterion("building_name not between", value1, value2, "buildingName");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIsNull() {
            addCriterion("property_type is null");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIsNotNull() {
            addCriterion("property_type is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeEqualTo(Integer value) {
            addCriterion("property_type =", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeNotEqualTo(Integer value) {
            addCriterion("property_type <>", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeGreaterThan(Integer value) {
            addCriterion("property_type >", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("property_type >=", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeLessThan(Integer value) {
            addCriterion("property_type <", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeLessThanOrEqualTo(Integer value) {
            addCriterion("property_type <=", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIn(List<Integer> values) {
            addCriterion("property_type in", values, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeNotIn(List<Integer> values) {
            addCriterion("property_type not in", values, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeBetween(Integer value1, Integer value2) {
            addCriterion("property_type between", value1, value2, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("property_type not between", value1, value2, "propertyType");
            return (Criteria) this;
        }

        public Criteria andFloorCountIsNull() {
            addCriterion("floor_count is null");
            return (Criteria) this;
        }

        public Criteria andFloorCountIsNotNull() {
            addCriterion("floor_count is not null");
            return (Criteria) this;
        }

        public Criteria andFloorCountEqualTo(Integer value) {
            addCriterion("floor_count =", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountNotEqualTo(Integer value) {
            addCriterion("floor_count <>", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountGreaterThan(Integer value) {
            addCriterion("floor_count >", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("floor_count >=", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountLessThan(Integer value) {
            addCriterion("floor_count <", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountLessThanOrEqualTo(Integer value) {
            addCriterion("floor_count <=", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountIn(List<Integer> values) {
            addCriterion("floor_count in", values, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountNotIn(List<Integer> values) {
            addCriterion("floor_count not in", values, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountBetween(Integer value1, Integer value2) {
            addCriterion("floor_count between", value1, value2, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountNotBetween(Integer value1, Integer value2) {
            addCriterion("floor_count not between", value1, value2, "floorCount");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalIsNull() {
            addCriterion("unit_interval is null");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalIsNotNull() {
            addCriterion("unit_interval is not null");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalEqualTo(String value) {
            addCriterion("unit_interval =", value, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalNotEqualTo(String value) {
            addCriterion("unit_interval <>", value, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalGreaterThan(String value) {
            addCriterion("unit_interval >", value, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalGreaterThanOrEqualTo(String value) {
            addCriterion("unit_interval >=", value, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalLessThan(String value) {
            addCriterion("unit_interval <", value, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalLessThanOrEqualTo(String value) {
            addCriterion("unit_interval <=", value, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalLike(String value) {
            addCriterion("unit_interval like", value, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalNotLike(String value) {
            addCriterion("unit_interval not like", value, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalIn(List<String> values) {
            addCriterion("unit_interval in", values, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalNotIn(List<String> values) {
            addCriterion("unit_interval not in", values, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalBetween(String value1, String value2) {
            addCriterion("unit_interval between", value1, value2, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andUnitIntervalNotBetween(String value1, String value2) {
            addCriterion("unit_interval not between", value1, value2, "unitInterval");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeIsNull() {
            addCriterion("property_fee is null");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeIsNotNull() {
            addCriterion("property_fee is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeEqualTo(BigDecimal value) {
            addCriterion("property_fee =", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeNotEqualTo(BigDecimal value) {
            addCriterion("property_fee <>", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeGreaterThan(BigDecimal value) {
            addCriterion("property_fee >", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("property_fee >=", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeLessThan(BigDecimal value) {
            addCriterion("property_fee <", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("property_fee <=", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeIn(List<BigDecimal> values) {
            addCriterion("property_fee in", values, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeNotIn(List<BigDecimal> values) {
            addCriterion("property_fee not in", values, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("property_fee between", value1, value2, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("property_fee not between", value1, value2, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeIsNull() {
            addCriterion("facilities_use_fee is null");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeIsNotNull() {
            addCriterion("facilities_use_fee is not null");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeEqualTo(BigDecimal value) {
            addCriterion("facilities_use_fee =", value, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeNotEqualTo(BigDecimal value) {
            addCriterion("facilities_use_fee <>", value, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeGreaterThan(BigDecimal value) {
            addCriterion("facilities_use_fee >", value, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("facilities_use_fee >=", value, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeLessThan(BigDecimal value) {
            addCriterion("facilities_use_fee <", value, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("facilities_use_fee <=", value, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeIn(List<BigDecimal> values) {
            addCriterion("facilities_use_fee in", values, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeNotIn(List<BigDecimal> values) {
            addCriterion("facilities_use_fee not in", values, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("facilities_use_fee between", value1, value2, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFacilitiesUseFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("facilities_use_fee not between", value1, value2, "facilitiesUseFee");
            return (Criteria) this;
        }

        public Criteria andFloorStartIsNull() {
            addCriterion("floor_start is null");
            return (Criteria) this;
        }

        public Criteria andFloorStartIsNotNull() {
            addCriterion("floor_start is not null");
            return (Criteria) this;
        }

        public Criteria andFloorStartEqualTo(Integer value) {
            addCriterion("floor_start =", value, "floorStart");
            return (Criteria) this;
        }

        public Criteria andFloorStartNotEqualTo(Integer value) {
            addCriterion("floor_start <>", value, "floorStart");
            return (Criteria) this;
        }

        public Criteria andFloorStartGreaterThan(Integer value) {
            addCriterion("floor_start >", value, "floorStart");
            return (Criteria) this;
        }

        public Criteria andFloorStartGreaterThanOrEqualTo(Integer value) {
            addCriterion("floor_start >=", value, "floorStart");
            return (Criteria) this;
        }

        public Criteria andFloorStartLessThan(Integer value) {
            addCriterion("floor_start <", value, "floorStart");
            return (Criteria) this;
        }

        public Criteria andFloorStartLessThanOrEqualTo(Integer value) {
            addCriterion("floor_start <=", value, "floorStart");
            return (Criteria) this;
        }

        public Criteria andFloorStartIn(List<Integer> values) {
            addCriterion("floor_start in", values, "floorStart");
            return (Criteria) this;
        }

        public Criteria andFloorStartNotIn(List<Integer> values) {
            addCriterion("floor_start not in", values, "floorStart");
            return (Criteria) this;
        }

        public Criteria andFloorStartBetween(Integer value1, Integer value2) {
            addCriterion("floor_start between", value1, value2, "floorStart");
            return (Criteria) this;
        }

        public Criteria andFloorStartNotBetween(Integer value1, Integer value2) {
            addCriterion("floor_start not between", value1, value2, "floorStart");
            return (Criteria) this;
        }

        public Criteria andFloorEndIsNull() {
            addCriterion("floor_end is null");
            return (Criteria) this;
        }

        public Criteria andFloorEndIsNotNull() {
            addCriterion("floor_end is not null");
            return (Criteria) this;
        }

        public Criteria andFloorEndEqualTo(Integer value) {
            addCriterion("floor_end =", value, "floorEnd");
            return (Criteria) this;
        }

        public Criteria andFloorEndNotEqualTo(Integer value) {
            addCriterion("floor_end <>", value, "floorEnd");
            return (Criteria) this;
        }

        public Criteria andFloorEndGreaterThan(Integer value) {
            addCriterion("floor_end >", value, "floorEnd");
            return (Criteria) this;
        }

        public Criteria andFloorEndGreaterThanOrEqualTo(Integer value) {
            addCriterion("floor_end >=", value, "floorEnd");
            return (Criteria) this;
        }

        public Criteria andFloorEndLessThan(Integer value) {
            addCriterion("floor_end <", value, "floorEnd");
            return (Criteria) this;
        }

        public Criteria andFloorEndLessThanOrEqualTo(Integer value) {
            addCriterion("floor_end <=", value, "floorEnd");
            return (Criteria) this;
        }

        public Criteria andFloorEndIn(List<Integer> values) {
            addCriterion("floor_end in", values, "floorEnd");
            return (Criteria) this;
        }

        public Criteria andFloorEndNotIn(List<Integer> values) {
            addCriterion("floor_end not in", values, "floorEnd");
            return (Criteria) this;
        }

        public Criteria andFloorEndBetween(Integer value1, Integer value2) {
            addCriterion("floor_end between", value1, value2, "floorEnd");
            return (Criteria) this;
        }

        public Criteria andFloorEndNotBetween(Integer value1, Integer value2) {
            addCriterion("floor_end not between", value1, value2, "floorEnd");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightIsNull() {
            addCriterion("building_height is null");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightIsNotNull() {
            addCriterion("building_height is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightEqualTo(BigDecimal value) {
            addCriterion("building_height =", value, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightNotEqualTo(BigDecimal value) {
            addCriterion("building_height <>", value, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightGreaterThan(BigDecimal value) {
            addCriterion("building_height >", value, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("building_height >=", value, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLessThan(BigDecimal value) {
            addCriterion("building_height <", value, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("building_height <=", value, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightIn(List<BigDecimal> values) {
            addCriterion("building_height in", values, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightNotIn(List<BigDecimal> values) {
            addCriterion("building_height not in", values, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("building_height between", value1, value2, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("building_height not between", value1, value2, "buildingHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaIsNull() {
            addCriterion("building_area is null");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaIsNotNull() {
            addCriterion("building_area is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaEqualTo(BigDecimal value) {
            addCriterion("building_area =", value, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaNotEqualTo(BigDecimal value) {
            addCriterion("building_area <>", value, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaGreaterThan(BigDecimal value) {
            addCriterion("building_area >", value, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("building_area >=", value, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaLessThan(BigDecimal value) {
            addCriterion("building_area <", value, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("building_area <=", value, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaIn(List<BigDecimal> values) {
            addCriterion("building_area in", values, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaNotIn(List<BigDecimal> values) {
            addCriterion("building_area not in", values, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("building_area between", value1, value2, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("building_area not between", value1, value2, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaIsNull() {
            addCriterion("cover_an_area is null");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaIsNotNull() {
            addCriterion("cover_an_area is not null");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaEqualTo(BigDecimal value) {
            addCriterion("cover_an_area =", value, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaNotEqualTo(BigDecimal value) {
            addCriterion("cover_an_area <>", value, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaGreaterThan(BigDecimal value) {
            addCriterion("cover_an_area >", value, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cover_an_area >=", value, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaLessThan(BigDecimal value) {
            addCriterion("cover_an_area <", value, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cover_an_area <=", value, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaIn(List<BigDecimal> values) {
            addCriterion("cover_an_area in", values, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaNotIn(List<BigDecimal> values) {
            addCriterion("cover_an_area not in", values, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cover_an_area between", value1, value2, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cover_an_area not between", value1, value2, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andBuildingCategoryIsNull() {
            addCriterion("building_category is null");
            return (Criteria) this;
        }

        public Criteria andBuildingCategoryIsNotNull() {
            addCriterion("building_category is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingCategoryEqualTo(Integer value) {
            addCriterion("building_category =", value, "buildingCategory");
            return (Criteria) this;
        }

        public Criteria andBuildingCategoryNotEqualTo(Integer value) {
            addCriterion("building_category <>", value, "buildingCategory");
            return (Criteria) this;
        }

        public Criteria andBuildingCategoryGreaterThan(Integer value) {
            addCriterion("building_category >", value, "buildingCategory");
            return (Criteria) this;
        }

        public Criteria andBuildingCategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_category >=", value, "buildingCategory");
            return (Criteria) this;
        }

        public Criteria andBuildingCategoryLessThan(Integer value) {
            addCriterion("building_category <", value, "buildingCategory");
            return (Criteria) this;
        }

        public Criteria andBuildingCategoryLessThanOrEqualTo(Integer value) {
            addCriterion("building_category <=", value, "buildingCategory");
            return (Criteria) this;
        }

        public Criteria andBuildingCategoryIn(List<Integer> values) {
            addCriterion("building_category in", values, "buildingCategory");
            return (Criteria) this;
        }

        public Criteria andBuildingCategoryNotIn(List<Integer> values) {
            addCriterion("building_category not in", values, "buildingCategory");
            return (Criteria) this;
        }

        public Criteria andBuildingCategoryBetween(Integer value1, Integer value2) {
            addCriterion("building_category between", value1, value2, "buildingCategory");
            return (Criteria) this;
        }

        public Criteria andBuildingCategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("building_category not between", value1, value2, "buildingCategory");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIsNull() {
            addCriterion("open_time is null");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIsNotNull() {
            addCriterion("open_time is not null");
            return (Criteria) this;
        }

        public Criteria andOpenTimeEqualTo(Date value) {
            addCriterion("open_time =", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotEqualTo(Date value) {
            addCriterion("open_time <>", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeGreaterThan(Date value) {
            addCriterion("open_time >", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("open_time >=", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLessThan(Date value) {
            addCriterion("open_time <", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLessThanOrEqualTo(Date value) {
            addCriterion("open_time <=", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIn(List<Date> values) {
            addCriterion("open_time in", values, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotIn(List<Date> values) {
            addCriterion("open_time not in", values, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeBetween(Date value1, Date value2) {
            addCriterion("open_time between", value1, value2, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotBetween(Date value1, Date value2) {
            addCriterion("open_time not between", value1, value2, "openTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeIsNull() {
            addCriterion("room_time is null");
            return (Criteria) this;
        }

        public Criteria andRoomTimeIsNotNull() {
            addCriterion("room_time is not null");
            return (Criteria) this;
        }

        public Criteria andRoomTimeEqualTo(Date value) {
            addCriterion("room_time =", value, "roomTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeNotEqualTo(Date value) {
            addCriterion("room_time <>", value, "roomTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeGreaterThan(Date value) {
            addCriterion("room_time >", value, "roomTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("room_time >=", value, "roomTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeLessThan(Date value) {
            addCriterion("room_time <", value, "roomTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeLessThanOrEqualTo(Date value) {
            addCriterion("room_time <=", value, "roomTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeIn(List<Date> values) {
            addCriterion("room_time in", values, "roomTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeNotIn(List<Date> values) {
            addCriterion("room_time not in", values, "roomTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeBetween(Date value1, Date value2) {
            addCriterion("room_time between", value1, value2, "roomTime");
            return (Criteria) this;
        }

        public Criteria andRoomTimeNotBetween(Date value1, Date value2) {
            addCriterion("room_time not between", value1, value2, "roomTime");
            return (Criteria) this;
        }

        public Criteria andFloorHeightIsNull() {
            addCriterion("floor_height is null");
            return (Criteria) this;
        }

        public Criteria andFloorHeightIsNotNull() {
            addCriterion("floor_height is not null");
            return (Criteria) this;
        }

        public Criteria andFloorHeightEqualTo(BigDecimal value) {
            addCriterion("floor_height =", value, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightNotEqualTo(BigDecimal value) {
            addCriterion("floor_height <>", value, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightGreaterThan(BigDecimal value) {
            addCriterion("floor_height >", value, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_height >=", value, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightLessThan(BigDecimal value) {
            addCriterion("floor_height <", value, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_height <=", value, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightIn(List<BigDecimal> values) {
            addCriterion("floor_height in", values, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightNotIn(List<BigDecimal> values) {
            addCriterion("floor_height not in", values, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_height between", value1, value2, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andFloorHeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_height not between", value1, value2, "floorHeight");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthIsNull() {
            addCriterion("diameter_depth is null");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthIsNotNull() {
            addCriterion("diameter_depth is not null");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthEqualTo(BigDecimal value) {
            addCriterion("diameter_depth =", value, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthNotEqualTo(BigDecimal value) {
            addCriterion("diameter_depth <>", value, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthGreaterThan(BigDecimal value) {
            addCriterion("diameter_depth >", value, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("diameter_depth >=", value, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthLessThan(BigDecimal value) {
            addCriterion("diameter_depth <", value, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthLessThanOrEqualTo(BigDecimal value) {
            addCriterion("diameter_depth <=", value, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthIn(List<BigDecimal> values) {
            addCriterion("diameter_depth in", values, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthNotIn(List<BigDecimal> values) {
            addCriterion("diameter_depth not in", values, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("diameter_depth between", value1, value2, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andDiameterDepthNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("diameter_depth not between", value1, value2, "diameterDepth");
            return (Criteria) this;
        }

        public Criteria andLandUseYearIsNull() {
            addCriterion("land_use_year is null");
            return (Criteria) this;
        }

        public Criteria andLandUseYearIsNotNull() {
            addCriterion("land_use_year is not null");
            return (Criteria) this;
        }

        public Criteria andLandUseYearEqualTo(Integer value) {
            addCriterion("land_use_year =", value, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLandUseYearNotEqualTo(Integer value) {
            addCriterion("land_use_year <>", value, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLandUseYearGreaterThan(Integer value) {
            addCriterion("land_use_year >", value, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLandUseYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("land_use_year >=", value, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLandUseYearLessThan(Integer value) {
            addCriterion("land_use_year <", value, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLandUseYearLessThanOrEqualTo(Integer value) {
            addCriterion("land_use_year <=", value, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLandUseYearIn(List<Integer> values) {
            addCriterion("land_use_year in", values, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLandUseYearNotIn(List<Integer> values) {
            addCriterion("land_use_year not in", values, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLandUseYearBetween(Integer value1, Integer value2) {
            addCriterion("land_use_year between", value1, value2, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLandUseYearNotBetween(Integer value1, Integer value2) {
            addCriterion("land_use_year not between", value1, value2, "landUseYear");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andNetHeightIsNull() {
            addCriterion("net_height is null");
            return (Criteria) this;
        }

        public Criteria andNetHeightIsNotNull() {
            addCriterion("net_height is not null");
            return (Criteria) this;
        }

        public Criteria andNetHeightEqualTo(BigDecimal value) {
            addCriterion("net_height =", value, "netHeight");
            return (Criteria) this;
        }

        public Criteria andNetHeightNotEqualTo(BigDecimal value) {
            addCriterion("net_height <>", value, "netHeight");
            return (Criteria) this;
        }

        public Criteria andNetHeightGreaterThan(BigDecimal value) {
            addCriterion("net_height >", value, "netHeight");
            return (Criteria) this;
        }

        public Criteria andNetHeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("net_height >=", value, "netHeight");
            return (Criteria) this;
        }

        public Criteria andNetHeightLessThan(BigDecimal value) {
            addCriterion("net_height <", value, "netHeight");
            return (Criteria) this;
        }

        public Criteria andNetHeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("net_height <=", value, "netHeight");
            return (Criteria) this;
        }

        public Criteria andNetHeightIn(List<BigDecimal> values) {
            addCriterion("net_height in", values, "netHeight");
            return (Criteria) this;
        }

        public Criteria andNetHeightNotIn(List<BigDecimal> values) {
            addCriterion("net_height not in", values, "netHeight");
            return (Criteria) this;
        }

        public Criteria andNetHeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("net_height between", value1, value2, "netHeight");
            return (Criteria) this;
        }

        public Criteria andNetHeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("net_height not between", value1, value2, "netHeight");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureIsNull() {
            addCriterion("building_structure is null");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureIsNotNull() {
            addCriterion("building_structure is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureEqualTo(Integer value) {
            addCriterion("building_structure =", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureNotEqualTo(Integer value) {
            addCriterion("building_structure <>", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureGreaterThan(Integer value) {
            addCriterion("building_structure >", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_structure >=", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLessThan(Integer value) {
            addCriterion("building_structure <", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLessThanOrEqualTo(Integer value) {
            addCriterion("building_structure <=", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureIn(List<Integer> values) {
            addCriterion("building_structure in", values, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureNotIn(List<Integer> values) {
            addCriterion("building_structure not in", values, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureBetween(Integer value1, Integer value2) {
            addCriterion("building_structure between", value1, value2, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureNotBetween(Integer value1, Integer value2) {
            addCriterion("building_structure not between", value1, value2, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLowerIsNull() {
            addCriterion("building_structure_lower is null");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLowerIsNotNull() {
            addCriterion("building_structure_lower is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLowerEqualTo(Integer value) {
            addCriterion("building_structure_lower =", value, "buildingStructureLower");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLowerNotEqualTo(Integer value) {
            addCriterion("building_structure_lower <>", value, "buildingStructureLower");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLowerGreaterThan(Integer value) {
            addCriterion("building_structure_lower >", value, "buildingStructureLower");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLowerGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_structure_lower >=", value, "buildingStructureLower");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLowerLessThan(Integer value) {
            addCriterion("building_structure_lower <", value, "buildingStructureLower");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLowerLessThanOrEqualTo(Integer value) {
            addCriterion("building_structure_lower <=", value, "buildingStructureLower");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLowerIn(List<Integer> values) {
            addCriterion("building_structure_lower in", values, "buildingStructureLower");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLowerNotIn(List<Integer> values) {
            addCriterion("building_structure_lower not in", values, "buildingStructureLower");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLowerBetween(Integer value1, Integer value2) {
            addCriterion("building_structure_lower between", value1, value2, "buildingStructureLower");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLowerNotBetween(Integer value1, Integer value2) {
            addCriterion("building_structure_lower not between", value1, value2, "buildingStructureLower");
            return (Criteria) this;
        }

        public Criteria andJsonContentIsNull() {
            addCriterion("json_content is null");
            return (Criteria) this;
        }

        public Criteria andJsonContentIsNotNull() {
            addCriterion("json_content is not null");
            return (Criteria) this;
        }

        public Criteria andJsonContentEqualTo(String value) {
            addCriterion("json_content =", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentNotEqualTo(String value) {
            addCriterion("json_content <>", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentGreaterThan(String value) {
            addCriterion("json_content >", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentGreaterThanOrEqualTo(String value) {
            addCriterion("json_content >=", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentLessThan(String value) {
            addCriterion("json_content <", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentLessThanOrEqualTo(String value) {
            addCriterion("json_content <=", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentLike(String value) {
            addCriterion("json_content like", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentNotLike(String value) {
            addCriterion("json_content not like", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentIn(List<String> values) {
            addCriterion("json_content in", values, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentNotIn(List<String> values) {
            addCriterion("json_content not in", values, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentBetween(String value1, String value2) {
            addCriterion("json_content between", value1, value2, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentNotBetween(String value1, String value2) {
            addCriterion("json_content not between", value1, value2, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
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