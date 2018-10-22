package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeclareBuildEquipmentInstallExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeclareBuildEquipmentInstallExample() {
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

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
            addCriterion("pid not between", value1, value2, "pid");
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

        public Criteria andDistrictIsNull() {
            addCriterion("district is null");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNotNull() {
            addCriterion("district is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictEqualTo(String value) {
            addCriterion("district =", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotEqualTo(String value) {
            addCriterion("district <>", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThan(String value) {
            addCriterion("district >", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThanOrEqualTo(String value) {
            addCriterion("district >=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThan(String value) {
            addCriterion("district <", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThanOrEqualTo(String value) {
            addCriterion("district <=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLike(String value) {
            addCriterion("district like", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotLike(String value) {
            addCriterion("district not like", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictIn(List<String> values) {
            addCriterion("district in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotIn(List<String> values) {
            addCriterion("district not in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictBetween(String value1, String value2) {
            addCriterion("district between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotBetween(String value1, String value2) {
            addCriterion("district not between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andOccupancyUnitIsNull() {
            addCriterion("occupancy_unit is null");
            return (Criteria) this;
        }

        public Criteria andOccupancyUnitIsNotNull() {
            addCriterion("occupancy_unit is not null");
            return (Criteria) this;
        }

        public Criteria andOccupancyUnitEqualTo(String value) {
            addCriterion("occupancy_unit =", value, "occupancyUnit");
            return (Criteria) this;
        }

        public Criteria andOccupancyUnitNotEqualTo(String value) {
            addCriterion("occupancy_unit <>", value, "occupancyUnit");
            return (Criteria) this;
        }

        public Criteria andOccupancyUnitGreaterThan(String value) {
            addCriterion("occupancy_unit >", value, "occupancyUnit");
            return (Criteria) this;
        }

        public Criteria andOccupancyUnitGreaterThanOrEqualTo(String value) {
            addCriterion("occupancy_unit >=", value, "occupancyUnit");
            return (Criteria) this;
        }

        public Criteria andOccupancyUnitLessThan(String value) {
            addCriterion("occupancy_unit <", value, "occupancyUnit");
            return (Criteria) this;
        }

        public Criteria andOccupancyUnitLessThanOrEqualTo(String value) {
            addCriterion("occupancy_unit <=", value, "occupancyUnit");
            return (Criteria) this;
        }

        public Criteria andOccupancyUnitLike(String value) {
            addCriterion("occupancy_unit like", value, "occupancyUnit");
            return (Criteria) this;
        }

        public Criteria andOccupancyUnitNotLike(String value) {
            addCriterion("occupancy_unit not like", value, "occupancyUnit");
            return (Criteria) this;
        }

        public Criteria andOccupancyUnitIn(List<String> values) {
            addCriterion("occupancy_unit in", values, "occupancyUnit");
            return (Criteria) this;
        }

        public Criteria andOccupancyUnitNotIn(List<String> values) {
            addCriterion("occupancy_unit not in", values, "occupancyUnit");
            return (Criteria) this;
        }

        public Criteria andOccupancyUnitBetween(String value1, String value2) {
            addCriterion("occupancy_unit between", value1, value2, "occupancyUnit");
            return (Criteria) this;
        }

        public Criteria andOccupancyUnitNotBetween(String value1, String value2) {
            addCriterion("occupancy_unit not between", value1, value2, "occupancyUnit");
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

        public Criteria andBeLocatedIsNull() {
            addCriterion("be_located is null");
            return (Criteria) this;
        }

        public Criteria andBeLocatedIsNotNull() {
            addCriterion("be_located is not null");
            return (Criteria) this;
        }

        public Criteria andBeLocatedEqualTo(String value) {
            addCriterion("be_located =", value, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedNotEqualTo(String value) {
            addCriterion("be_located <>", value, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedGreaterThan(String value) {
            addCriterion("be_located >", value, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedGreaterThanOrEqualTo(String value) {
            addCriterion("be_located >=", value, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedLessThan(String value) {
            addCriterion("be_located <", value, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedLessThanOrEqualTo(String value) {
            addCriterion("be_located <=", value, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedLike(String value) {
            addCriterion("be_located like", value, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedNotLike(String value) {
            addCriterion("be_located not like", value, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedIn(List<String> values) {
            addCriterion("be_located in", values, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedNotIn(List<String> values) {
            addCriterion("be_located not in", values, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedBetween(String value1, String value2) {
            addCriterion("be_located between", value1, value2, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedNotBetween(String value1, String value2) {
            addCriterion("be_located not between", value1, value2, "beLocated");
            return (Criteria) this;
        }

        public Criteria andSpecificationModelIsNull() {
            addCriterion("specification_model is null");
            return (Criteria) this;
        }

        public Criteria andSpecificationModelIsNotNull() {
            addCriterion("specification_model is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificationModelEqualTo(String value) {
            addCriterion("specification_model =", value, "specificationModel");
            return (Criteria) this;
        }

        public Criteria andSpecificationModelNotEqualTo(String value) {
            addCriterion("specification_model <>", value, "specificationModel");
            return (Criteria) this;
        }

        public Criteria andSpecificationModelGreaterThan(String value) {
            addCriterion("specification_model >", value, "specificationModel");
            return (Criteria) this;
        }

        public Criteria andSpecificationModelGreaterThanOrEqualTo(String value) {
            addCriterion("specification_model >=", value, "specificationModel");
            return (Criteria) this;
        }

        public Criteria andSpecificationModelLessThan(String value) {
            addCriterion("specification_model <", value, "specificationModel");
            return (Criteria) this;
        }

        public Criteria andSpecificationModelLessThanOrEqualTo(String value) {
            addCriterion("specification_model <=", value, "specificationModel");
            return (Criteria) this;
        }

        public Criteria andSpecificationModelLike(String value) {
            addCriterion("specification_model like", value, "specificationModel");
            return (Criteria) this;
        }

        public Criteria andSpecificationModelNotLike(String value) {
            addCriterion("specification_model not like", value, "specificationModel");
            return (Criteria) this;
        }

        public Criteria andSpecificationModelIn(List<String> values) {
            addCriterion("specification_model in", values, "specificationModel");
            return (Criteria) this;
        }

        public Criteria andSpecificationModelNotIn(List<String> values) {
            addCriterion("specification_model not in", values, "specificationModel");
            return (Criteria) this;
        }

        public Criteria andSpecificationModelBetween(String value1, String value2) {
            addCriterion("specification_model between", value1, value2, "specificationModel");
            return (Criteria) this;
        }

        public Criteria andSpecificationModelNotBetween(String value1, String value2) {
            addCriterion("specification_model not between", value1, value2, "specificationModel");
            return (Criteria) this;
        }

        public Criteria andManufacturerIsNull() {
            addCriterion("manufacturer is null");
            return (Criteria) this;
        }

        public Criteria andManufacturerIsNotNull() {
            addCriterion("manufacturer is not null");
            return (Criteria) this;
        }

        public Criteria andManufacturerEqualTo(String value) {
            addCriterion("manufacturer =", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotEqualTo(String value) {
            addCriterion("manufacturer <>", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerGreaterThan(String value) {
            addCriterion("manufacturer >", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerGreaterThanOrEqualTo(String value) {
            addCriterion("manufacturer >=", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLessThan(String value) {
            addCriterion("manufacturer <", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLessThanOrEqualTo(String value) {
            addCriterion("manufacturer <=", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLike(String value) {
            addCriterion("manufacturer like", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotLike(String value) {
            addCriterion("manufacturer not like", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerIn(List<String> values) {
            addCriterion("manufacturer in", values, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotIn(List<String> values) {
            addCriterion("manufacturer not in", values, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerBetween(String value1, String value2) {
            addCriterion("manufacturer between", value1, value2, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotBetween(String value1, String value2) {
            addCriterion("manufacturer not between", value1, value2, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andMeasurementUnitIsNull() {
            addCriterion("measurement_unit is null");
            return (Criteria) this;
        }

        public Criteria andMeasurementUnitIsNotNull() {
            addCriterion("measurement_unit is not null");
            return (Criteria) this;
        }

        public Criteria andMeasurementUnitEqualTo(String value) {
            addCriterion("measurement_unit =", value, "measurementUnit");
            return (Criteria) this;
        }

        public Criteria andMeasurementUnitNotEqualTo(String value) {
            addCriterion("measurement_unit <>", value, "measurementUnit");
            return (Criteria) this;
        }

        public Criteria andMeasurementUnitGreaterThan(String value) {
            addCriterion("measurement_unit >", value, "measurementUnit");
            return (Criteria) this;
        }

        public Criteria andMeasurementUnitGreaterThanOrEqualTo(String value) {
            addCriterion("measurement_unit >=", value, "measurementUnit");
            return (Criteria) this;
        }

        public Criteria andMeasurementUnitLessThan(String value) {
            addCriterion("measurement_unit <", value, "measurementUnit");
            return (Criteria) this;
        }

        public Criteria andMeasurementUnitLessThanOrEqualTo(String value) {
            addCriterion("measurement_unit <=", value, "measurementUnit");
            return (Criteria) this;
        }

        public Criteria andMeasurementUnitLike(String value) {
            addCriterion("measurement_unit like", value, "measurementUnit");
            return (Criteria) this;
        }

        public Criteria andMeasurementUnitNotLike(String value) {
            addCriterion("measurement_unit not like", value, "measurementUnit");
            return (Criteria) this;
        }

        public Criteria andMeasurementUnitIn(List<String> values) {
            addCriterion("measurement_unit in", values, "measurementUnit");
            return (Criteria) this;
        }

        public Criteria andMeasurementUnitNotIn(List<String> values) {
            addCriterion("measurement_unit not in", values, "measurementUnit");
            return (Criteria) this;
        }

        public Criteria andMeasurementUnitBetween(String value1, String value2) {
            addCriterion("measurement_unit between", value1, value2, "measurementUnit");
            return (Criteria) this;
        }

        public Criteria andMeasurementUnitNotBetween(String value1, String value2) {
            addCriterion("measurement_unit not between", value1, value2, "measurementUnit");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Integer value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Integer value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Integer value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Integer value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Integer value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Integer> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Integer> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Integer value1, Integer value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andExpectedCompletionDateIsNull() {
            addCriterion("expected_completion_date is null");
            return (Criteria) this;
        }

        public Criteria andExpectedCompletionDateIsNotNull() {
            addCriterion("expected_completion_date is not null");
            return (Criteria) this;
        }

        public Criteria andExpectedCompletionDateEqualTo(Date value) {
            addCriterion("expected_completion_date =", value, "expectedCompletionDate");
            return (Criteria) this;
        }

        public Criteria andExpectedCompletionDateNotEqualTo(Date value) {
            addCriterion("expected_completion_date <>", value, "expectedCompletionDate");
            return (Criteria) this;
        }

        public Criteria andExpectedCompletionDateGreaterThan(Date value) {
            addCriterion("expected_completion_date >", value, "expectedCompletionDate");
            return (Criteria) this;
        }

        public Criteria andExpectedCompletionDateGreaterThanOrEqualTo(Date value) {
            addCriterion("expected_completion_date >=", value, "expectedCompletionDate");
            return (Criteria) this;
        }

        public Criteria andExpectedCompletionDateLessThan(Date value) {
            addCriterion("expected_completion_date <", value, "expectedCompletionDate");
            return (Criteria) this;
        }

        public Criteria andExpectedCompletionDateLessThanOrEqualTo(Date value) {
            addCriterion("expected_completion_date <=", value, "expectedCompletionDate");
            return (Criteria) this;
        }

        public Criteria andExpectedCompletionDateIn(List<Date> values) {
            addCriterion("expected_completion_date in", values, "expectedCompletionDate");
            return (Criteria) this;
        }

        public Criteria andExpectedCompletionDateNotIn(List<Date> values) {
            addCriterion("expected_completion_date not in", values, "expectedCompletionDate");
            return (Criteria) this;
        }

        public Criteria andExpectedCompletionDateBetween(Date value1, Date value2) {
            addCriterion("expected_completion_date between", value1, value2, "expectedCompletionDate");
            return (Criteria) this;
        }

        public Criteria andExpectedCompletionDateNotBetween(Date value1, Date value2) {
            addCriterion("expected_completion_date not between", value1, value2, "expectedCompletionDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterion("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterion("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterion("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterion("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterion("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterion("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterion("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterion("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterion("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andBookEquipmentFeeIsNull() {
            addCriterion("book_equipment_fee is null");
            return (Criteria) this;
        }

        public Criteria andBookEquipmentFeeIsNotNull() {
            addCriterion("book_equipment_fee is not null");
            return (Criteria) this;
        }

        public Criteria andBookEquipmentFeeEqualTo(String value) {
            addCriterion("book_equipment_fee =", value, "bookEquipmentFee");
            return (Criteria) this;
        }

        public Criteria andBookEquipmentFeeNotEqualTo(String value) {
            addCriterion("book_equipment_fee <>", value, "bookEquipmentFee");
            return (Criteria) this;
        }

        public Criteria andBookEquipmentFeeGreaterThan(String value) {
            addCriterion("book_equipment_fee >", value, "bookEquipmentFee");
            return (Criteria) this;
        }

        public Criteria andBookEquipmentFeeGreaterThanOrEqualTo(String value) {
            addCriterion("book_equipment_fee >=", value, "bookEquipmentFee");
            return (Criteria) this;
        }

        public Criteria andBookEquipmentFeeLessThan(String value) {
            addCriterion("book_equipment_fee <", value, "bookEquipmentFee");
            return (Criteria) this;
        }

        public Criteria andBookEquipmentFeeLessThanOrEqualTo(String value) {
            addCriterion("book_equipment_fee <=", value, "bookEquipmentFee");
            return (Criteria) this;
        }

        public Criteria andBookEquipmentFeeLike(String value) {
            addCriterion("book_equipment_fee like", value, "bookEquipmentFee");
            return (Criteria) this;
        }

        public Criteria andBookEquipmentFeeNotLike(String value) {
            addCriterion("book_equipment_fee not like", value, "bookEquipmentFee");
            return (Criteria) this;
        }

        public Criteria andBookEquipmentFeeIn(List<String> values) {
            addCriterion("book_equipment_fee in", values, "bookEquipmentFee");
            return (Criteria) this;
        }

        public Criteria andBookEquipmentFeeNotIn(List<String> values) {
            addCriterion("book_equipment_fee not in", values, "bookEquipmentFee");
            return (Criteria) this;
        }

        public Criteria andBookEquipmentFeeBetween(String value1, String value2) {
            addCriterion("book_equipment_fee between", value1, value2, "bookEquipmentFee");
            return (Criteria) this;
        }

        public Criteria andBookEquipmentFeeNotBetween(String value1, String value2) {
            addCriterion("book_equipment_fee not between", value1, value2, "bookEquipmentFee");
            return (Criteria) this;
        }

        public Criteria andBookCapitalCostIsNull() {
            addCriterion("book_capital_cost is null");
            return (Criteria) this;
        }

        public Criteria andBookCapitalCostIsNotNull() {
            addCriterion("book_capital_cost is not null");
            return (Criteria) this;
        }

        public Criteria andBookCapitalCostEqualTo(String value) {
            addCriterion("book_capital_cost =", value, "bookCapitalCost");
            return (Criteria) this;
        }

        public Criteria andBookCapitalCostNotEqualTo(String value) {
            addCriterion("book_capital_cost <>", value, "bookCapitalCost");
            return (Criteria) this;
        }

        public Criteria andBookCapitalCostGreaterThan(String value) {
            addCriterion("book_capital_cost >", value, "bookCapitalCost");
            return (Criteria) this;
        }

        public Criteria andBookCapitalCostGreaterThanOrEqualTo(String value) {
            addCriterion("book_capital_cost >=", value, "bookCapitalCost");
            return (Criteria) this;
        }

        public Criteria andBookCapitalCostLessThan(String value) {
            addCriterion("book_capital_cost <", value, "bookCapitalCost");
            return (Criteria) this;
        }

        public Criteria andBookCapitalCostLessThanOrEqualTo(String value) {
            addCriterion("book_capital_cost <=", value, "bookCapitalCost");
            return (Criteria) this;
        }

        public Criteria andBookCapitalCostLike(String value) {
            addCriterion("book_capital_cost like", value, "bookCapitalCost");
            return (Criteria) this;
        }

        public Criteria andBookCapitalCostNotLike(String value) {
            addCriterion("book_capital_cost not like", value, "bookCapitalCost");
            return (Criteria) this;
        }

        public Criteria andBookCapitalCostIn(List<String> values) {
            addCriterion("book_capital_cost in", values, "bookCapitalCost");
            return (Criteria) this;
        }

        public Criteria andBookCapitalCostNotIn(List<String> values) {
            addCriterion("book_capital_cost not in", values, "bookCapitalCost");
            return (Criteria) this;
        }

        public Criteria andBookCapitalCostBetween(String value1, String value2) {
            addCriterion("book_capital_cost between", value1, value2, "bookCapitalCost");
            return (Criteria) this;
        }

        public Criteria andBookCapitalCostNotBetween(String value1, String value2) {
            addCriterion("book_capital_cost not between", value1, value2, "bookCapitalCost");
            return (Criteria) this;
        }

        public Criteria andBookInstallationFeeIsNull() {
            addCriterion("book_installation_fee is null");
            return (Criteria) this;
        }

        public Criteria andBookInstallationFeeIsNotNull() {
            addCriterion("book_installation_fee is not null");
            return (Criteria) this;
        }

        public Criteria andBookInstallationFeeEqualTo(String value) {
            addCriterion("book_installation_fee =", value, "bookInstallationFee");
            return (Criteria) this;
        }

        public Criteria andBookInstallationFeeNotEqualTo(String value) {
            addCriterion("book_installation_fee <>", value, "bookInstallationFee");
            return (Criteria) this;
        }

        public Criteria andBookInstallationFeeGreaterThan(String value) {
            addCriterion("book_installation_fee >", value, "bookInstallationFee");
            return (Criteria) this;
        }

        public Criteria andBookInstallationFeeGreaterThanOrEqualTo(String value) {
            addCriterion("book_installation_fee >=", value, "bookInstallationFee");
            return (Criteria) this;
        }

        public Criteria andBookInstallationFeeLessThan(String value) {
            addCriterion("book_installation_fee <", value, "bookInstallationFee");
            return (Criteria) this;
        }

        public Criteria andBookInstallationFeeLessThanOrEqualTo(String value) {
            addCriterion("book_installation_fee <=", value, "bookInstallationFee");
            return (Criteria) this;
        }

        public Criteria andBookInstallationFeeLike(String value) {
            addCriterion("book_installation_fee like", value, "bookInstallationFee");
            return (Criteria) this;
        }

        public Criteria andBookInstallationFeeNotLike(String value) {
            addCriterion("book_installation_fee not like", value, "bookInstallationFee");
            return (Criteria) this;
        }

        public Criteria andBookInstallationFeeIn(List<String> values) {
            addCriterion("book_installation_fee in", values, "bookInstallationFee");
            return (Criteria) this;
        }

        public Criteria andBookInstallationFeeNotIn(List<String> values) {
            addCriterion("book_installation_fee not in", values, "bookInstallationFee");
            return (Criteria) this;
        }

        public Criteria andBookInstallationFeeBetween(String value1, String value2) {
            addCriterion("book_installation_fee between", value1, value2, "bookInstallationFee");
            return (Criteria) this;
        }

        public Criteria andBookInstallationFeeNotBetween(String value1, String value2) {
            addCriterion("book_installation_fee not between", value1, value2, "bookInstallationFee");
            return (Criteria) this;
        }

        public Criteria andOtherIsNull() {
            addCriterion("other is null");
            return (Criteria) this;
        }

        public Criteria andOtherIsNotNull() {
            addCriterion("other is not null");
            return (Criteria) this;
        }

        public Criteria andOtherEqualTo(String value) {
            addCriterion("other =", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotEqualTo(String value) {
            addCriterion("other <>", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherGreaterThan(String value) {
            addCriterion("other >", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherGreaterThanOrEqualTo(String value) {
            addCriterion("other >=", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLessThan(String value) {
            addCriterion("other <", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLessThanOrEqualTo(String value) {
            addCriterion("other <=", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLike(String value) {
            addCriterion("other like", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotLike(String value) {
            addCriterion("other not like", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherIn(List<String> values) {
            addCriterion("other in", values, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotIn(List<String> values) {
            addCriterion("other not in", values, "other");
            return (Criteria) this;
        }

        public Criteria andOtherBetween(String value1, String value2) {
            addCriterion("other between", value1, value2, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotBetween(String value1, String value2) {
            addCriterion("other not between", value1, value2, "other");
            return (Criteria) this;
        }

        public Criteria andDeclarationDateIsNull() {
            addCriterion("declaration_date is null");
            return (Criteria) this;
        }

        public Criteria andDeclarationDateIsNotNull() {
            addCriterion("declaration_date is not null");
            return (Criteria) this;
        }

        public Criteria andDeclarationDateEqualTo(Date value) {
            addCriterion("declaration_date =", value, "declarationDate");
            return (Criteria) this;
        }

        public Criteria andDeclarationDateNotEqualTo(Date value) {
            addCriterion("declaration_date <>", value, "declarationDate");
            return (Criteria) this;
        }

        public Criteria andDeclarationDateGreaterThan(Date value) {
            addCriterion("declaration_date >", value, "declarationDate");
            return (Criteria) this;
        }

        public Criteria andDeclarationDateGreaterThanOrEqualTo(Date value) {
            addCriterion("declaration_date >=", value, "declarationDate");
            return (Criteria) this;
        }

        public Criteria andDeclarationDateLessThan(Date value) {
            addCriterion("declaration_date <", value, "declarationDate");
            return (Criteria) this;
        }

        public Criteria andDeclarationDateLessThanOrEqualTo(Date value) {
            addCriterion("declaration_date <=", value, "declarationDate");
            return (Criteria) this;
        }

        public Criteria andDeclarationDateIn(List<Date> values) {
            addCriterion("declaration_date in", values, "declarationDate");
            return (Criteria) this;
        }

        public Criteria andDeclarationDateNotIn(List<Date> values) {
            addCriterion("declaration_date not in", values, "declarationDate");
            return (Criteria) this;
        }

        public Criteria andDeclarationDateBetween(Date value1, Date value2) {
            addCriterion("declaration_date between", value1, value2, "declarationDate");
            return (Criteria) this;
        }

        public Criteria andDeclarationDateNotBetween(Date value1, Date value2) {
            addCriterion("declaration_date not between", value1, value2, "declarationDate");
            return (Criteria) this;
        }

        public Criteria andDeclarerIsNull() {
            addCriterion("declarer is null");
            return (Criteria) this;
        }

        public Criteria andDeclarerIsNotNull() {
            addCriterion("declarer is not null");
            return (Criteria) this;
        }

        public Criteria andDeclarerEqualTo(String value) {
            addCriterion("declarer =", value, "declarer");
            return (Criteria) this;
        }

        public Criteria andDeclarerNotEqualTo(String value) {
            addCriterion("declarer <>", value, "declarer");
            return (Criteria) this;
        }

        public Criteria andDeclarerGreaterThan(String value) {
            addCriterion("declarer >", value, "declarer");
            return (Criteria) this;
        }

        public Criteria andDeclarerGreaterThanOrEqualTo(String value) {
            addCriterion("declarer >=", value, "declarer");
            return (Criteria) this;
        }

        public Criteria andDeclarerLessThan(String value) {
            addCriterion("declarer <", value, "declarer");
            return (Criteria) this;
        }

        public Criteria andDeclarerLessThanOrEqualTo(String value) {
            addCriterion("declarer <=", value, "declarer");
            return (Criteria) this;
        }

        public Criteria andDeclarerLike(String value) {
            addCriterion("declarer like", value, "declarer");
            return (Criteria) this;
        }

        public Criteria andDeclarerNotLike(String value) {
            addCriterion("declarer not like", value, "declarer");
            return (Criteria) this;
        }

        public Criteria andDeclarerIn(List<String> values) {
            addCriterion("declarer in", values, "declarer");
            return (Criteria) this;
        }

        public Criteria andDeclarerNotIn(List<String> values) {
            addCriterion("declarer not in", values, "declarer");
            return (Criteria) this;
        }

        public Criteria andDeclarerBetween(String value1, String value2) {
            addCriterion("declarer between", value1, value2, "declarer");
            return (Criteria) this;
        }

        public Criteria andDeclarerNotBetween(String value1, String value2) {
            addCriterion("declarer not between", value1, value2, "declarer");
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

        public Criteria andEnableIsNull() {
            addCriterion("enable is null");
            return (Criteria) this;
        }

        public Criteria andEnableIsNotNull() {
            addCriterion("enable is not null");
            return (Criteria) this;
        }

        public Criteria andEnableEqualTo(String value) {
            addCriterion("enable =", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotEqualTo(String value) {
            addCriterion("enable <>", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThan(String value) {
            addCriterion("enable >", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThanOrEqualTo(String value) {
            addCriterion("enable >=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThan(String value) {
            addCriterion("enable <", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThanOrEqualTo(String value) {
            addCriterion("enable <=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLike(String value) {
            addCriterion("enable like", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotLike(String value) {
            addCriterion("enable not like", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableIn(List<String> values) {
            addCriterion("enable in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotIn(List<String> values) {
            addCriterion("enable not in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableBetween(String value1, String value2) {
            addCriterion("enable between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotBetween(String value1, String value2) {
            addCriterion("enable not between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andBuildingConstructionPermitIdIsNull() {
            addCriterion("building_construction_permit_id is null");
            return (Criteria) this;
        }

        public Criteria andBuildingConstructionPermitIdIsNotNull() {
            addCriterion("building_construction_permit_id is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingConstructionPermitIdEqualTo(Integer value) {
            addCriterion("building_construction_permit_id =", value, "buildingConstructionPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingConstructionPermitIdNotEqualTo(Integer value) {
            addCriterion("building_construction_permit_id <>", value, "buildingConstructionPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingConstructionPermitIdGreaterThan(Integer value) {
            addCriterion("building_construction_permit_id >", value, "buildingConstructionPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingConstructionPermitIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_construction_permit_id >=", value, "buildingConstructionPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingConstructionPermitIdLessThan(Integer value) {
            addCriterion("building_construction_permit_id <", value, "buildingConstructionPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingConstructionPermitIdLessThanOrEqualTo(Integer value) {
            addCriterion("building_construction_permit_id <=", value, "buildingConstructionPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingConstructionPermitIdIn(List<Integer> values) {
            addCriterion("building_construction_permit_id in", values, "buildingConstructionPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingConstructionPermitIdNotIn(List<Integer> values) {
            addCriterion("building_construction_permit_id not in", values, "buildingConstructionPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingConstructionPermitIdBetween(Integer value1, Integer value2) {
            addCriterion("building_construction_permit_id between", value1, value2, "buildingConstructionPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingConstructionPermitIdNotBetween(Integer value1, Integer value2) {
            addCriterion("building_construction_permit_id not between", value1, value2, "buildingConstructionPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingPermitIdIsNull() {
            addCriterion("building_permit_id is null");
            return (Criteria) this;
        }

        public Criteria andBuildingPermitIdIsNotNull() {
            addCriterion("building_permit_id is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingPermitIdEqualTo(Integer value) {
            addCriterion("building_permit_id =", value, "buildingPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingPermitIdNotEqualTo(Integer value) {
            addCriterion("building_permit_id <>", value, "buildingPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingPermitIdGreaterThan(Integer value) {
            addCriterion("building_permit_id >", value, "buildingPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingPermitIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_permit_id >=", value, "buildingPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingPermitIdLessThan(Integer value) {
            addCriterion("building_permit_id <", value, "buildingPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingPermitIdLessThanOrEqualTo(Integer value) {
            addCriterion("building_permit_id <=", value, "buildingPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingPermitIdIn(List<Integer> values) {
            addCriterion("building_permit_id in", values, "buildingPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingPermitIdNotIn(List<Integer> values) {
            addCriterion("building_permit_id not in", values, "buildingPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingPermitIdBetween(Integer value1, Integer value2) {
            addCriterion("building_permit_id between", value1, value2, "buildingPermitId");
            return (Criteria) this;
        }

        public Criteria andBuildingPermitIdNotBetween(Integer value1, Integer value2) {
            addCriterion("building_permit_id not between", value1, value2, "buildingPermitId");
            return (Criteria) this;
        }

        public Criteria andLandUsePermitIdIsNull() {
            addCriterion("land_use_permit_id is null");
            return (Criteria) this;
        }

        public Criteria andLandUsePermitIdIsNotNull() {
            addCriterion("land_use_permit_id is not null");
            return (Criteria) this;
        }

        public Criteria andLandUsePermitIdEqualTo(Integer value) {
            addCriterion("land_use_permit_id =", value, "landUsePermitId");
            return (Criteria) this;
        }

        public Criteria andLandUsePermitIdNotEqualTo(Integer value) {
            addCriterion("land_use_permit_id <>", value, "landUsePermitId");
            return (Criteria) this;
        }

        public Criteria andLandUsePermitIdGreaterThan(Integer value) {
            addCriterion("land_use_permit_id >", value, "landUsePermitId");
            return (Criteria) this;
        }

        public Criteria andLandUsePermitIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("land_use_permit_id >=", value, "landUsePermitId");
            return (Criteria) this;
        }

        public Criteria andLandUsePermitIdLessThan(Integer value) {
            addCriterion("land_use_permit_id <", value, "landUsePermitId");
            return (Criteria) this;
        }

        public Criteria andLandUsePermitIdLessThanOrEqualTo(Integer value) {
            addCriterion("land_use_permit_id <=", value, "landUsePermitId");
            return (Criteria) this;
        }

        public Criteria andLandUsePermitIdIn(List<Integer> values) {
            addCriterion("land_use_permit_id in", values, "landUsePermitId");
            return (Criteria) this;
        }

        public Criteria andLandUsePermitIdNotIn(List<Integer> values) {
            addCriterion("land_use_permit_id not in", values, "landUsePermitId");
            return (Criteria) this;
        }

        public Criteria andLandUsePermitIdBetween(Integer value1, Integer value2) {
            addCriterion("land_use_permit_id between", value1, value2, "landUsePermitId");
            return (Criteria) this;
        }

        public Criteria andLandUsePermitIdNotBetween(Integer value1, Integer value2) {
            addCriterion("land_use_permit_id not between", value1, value2, "landUsePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdIsNull() {
            addCriterion("pre_sale_permit_id is null");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdIsNotNull() {
            addCriterion("pre_sale_permit_id is not null");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdEqualTo(Integer value) {
            addCriterion("pre_sale_permit_id =", value, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdNotEqualTo(Integer value) {
            addCriterion("pre_sale_permit_id <>", value, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdGreaterThan(Integer value) {
            addCriterion("pre_sale_permit_id >", value, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pre_sale_permit_id >=", value, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdLessThan(Integer value) {
            addCriterion("pre_sale_permit_id <", value, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdLessThanOrEqualTo(Integer value) {
            addCriterion("pre_sale_permit_id <=", value, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdIn(List<Integer> values) {
            addCriterion("pre_sale_permit_id in", values, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdNotIn(List<Integer> values) {
            addCriterion("pre_sale_permit_id not in", values, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdBetween(Integer value1, Integer value2) {
            addCriterion("pre_sale_permit_id between", value1, value2, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pre_sale_permit_id not between", value1, value2, "preSalePermitId");
            return (Criteria) this;
        }

        public Criteria andLandIdIsNull() {
            addCriterion("land_id is null");
            return (Criteria) this;
        }

        public Criteria andLandIdIsNotNull() {
            addCriterion("land_id is not null");
            return (Criteria) this;
        }

        public Criteria andLandIdEqualTo(Integer value) {
            addCriterion("land_id =", value, "landId");
            return (Criteria) this;
        }

        public Criteria andLandIdNotEqualTo(Integer value) {
            addCriterion("land_id <>", value, "landId");
            return (Criteria) this;
        }

        public Criteria andLandIdGreaterThan(Integer value) {
            addCriterion("land_id >", value, "landId");
            return (Criteria) this;
        }

        public Criteria andLandIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("land_id >=", value, "landId");
            return (Criteria) this;
        }

        public Criteria andLandIdLessThan(Integer value) {
            addCriterion("land_id <", value, "landId");
            return (Criteria) this;
        }

        public Criteria andLandIdLessThanOrEqualTo(Integer value) {
            addCriterion("land_id <=", value, "landId");
            return (Criteria) this;
        }

        public Criteria andLandIdIn(List<Integer> values) {
            addCriterion("land_id in", values, "landId");
            return (Criteria) this;
        }

        public Criteria andLandIdNotIn(List<Integer> values) {
            addCriterion("land_id not in", values, "landId");
            return (Criteria) this;
        }

        public Criteria andLandIdBetween(Integer value1, Integer value2) {
            addCriterion("land_id between", value1, value2, "landId");
            return (Criteria) this;
        }

        public Criteria andLandIdNotBetween(Integer value1, Integer value2) {
            addCriterion("land_id not between", value1, value2, "landId");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIsNull() {
            addCriterion("declare_type is null");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIsNotNull() {
            addCriterion("declare_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeEqualTo(String value) {
            addCriterion("declare_type =", value, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNotEqualTo(String value) {
            addCriterion("declare_type <>", value, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeGreaterThan(String value) {
            addCriterion("declare_type >", value, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeGreaterThanOrEqualTo(String value) {
            addCriterion("declare_type >=", value, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeLessThan(String value) {
            addCriterion("declare_type <", value, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeLessThanOrEqualTo(String value) {
            addCriterion("declare_type <=", value, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeLike(String value) {
            addCriterion("declare_type like", value, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNotLike(String value) {
            addCriterion("declare_type not like", value, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIn(List<String> values) {
            addCriterion("declare_type in", values, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNotIn(List<String> values) {
            addCriterion("declare_type not in", values, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeBetween(String value1, String value2) {
            addCriterion("declare_type between", value1, value2, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNotBetween(String value1, String value2) {
            addCriterion("declare_type not between", value1, value2, "declareType");
            return (Criteria) this;
        }

        public Criteria andRealEstateIdIsNull() {
            addCriterion("real_estate_id is null");
            return (Criteria) this;
        }

        public Criteria andRealEstateIdIsNotNull() {
            addCriterion("real_estate_id is not null");
            return (Criteria) this;
        }

        public Criteria andRealEstateIdEqualTo(Integer value) {
            addCriterion("real_estate_id =", value, "realEstateId");
            return (Criteria) this;
        }

        public Criteria andRealEstateIdNotEqualTo(Integer value) {
            addCriterion("real_estate_id <>", value, "realEstateId");
            return (Criteria) this;
        }

        public Criteria andRealEstateIdGreaterThan(Integer value) {
            addCriterion("real_estate_id >", value, "realEstateId");
            return (Criteria) this;
        }

        public Criteria andRealEstateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("real_estate_id >=", value, "realEstateId");
            return (Criteria) this;
        }

        public Criteria andRealEstateIdLessThan(Integer value) {
            addCriterion("real_estate_id <", value, "realEstateId");
            return (Criteria) this;
        }

        public Criteria andRealEstateIdLessThanOrEqualTo(Integer value) {
            addCriterion("real_estate_id <=", value, "realEstateId");
            return (Criteria) this;
        }

        public Criteria andRealEstateIdIn(List<Integer> values) {
            addCriterion("real_estate_id in", values, "realEstateId");
            return (Criteria) this;
        }

        public Criteria andRealEstateIdNotIn(List<Integer> values) {
            addCriterion("real_estate_id not in", values, "realEstateId");
            return (Criteria) this;
        }

        public Criteria andRealEstateIdBetween(Integer value1, Integer value2) {
            addCriterion("real_estate_id between", value1, value2, "realEstateId");
            return (Criteria) this;
        }

        public Criteria andRealEstateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("real_estate_id not between", value1, value2, "realEstateId");
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