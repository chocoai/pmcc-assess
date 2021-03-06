package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeclareBuildEngineeringExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeclareBuildEngineeringExample() {
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

        public Criteria andStructureIsNull() {
            addCriterion("structure is null");
            return (Criteria) this;
        }

        public Criteria andStructureIsNotNull() {
            addCriterion("structure is not null");
            return (Criteria) this;
        }

        public Criteria andStructureEqualTo(String value) {
            addCriterion("structure =", value, "structure");
            return (Criteria) this;
        }

        public Criteria andStructureNotEqualTo(String value) {
            addCriterion("structure <>", value, "structure");
            return (Criteria) this;
        }

        public Criteria andStructureGreaterThan(String value) {
            addCriterion("structure >", value, "structure");
            return (Criteria) this;
        }

        public Criteria andStructureGreaterThanOrEqualTo(String value) {
            addCriterion("structure >=", value, "structure");
            return (Criteria) this;
        }

        public Criteria andStructureLessThan(String value) {
            addCriterion("structure <", value, "structure");
            return (Criteria) this;
        }

        public Criteria andStructureLessThanOrEqualTo(String value) {
            addCriterion("structure <=", value, "structure");
            return (Criteria) this;
        }

        public Criteria andStructureLike(String value) {
            addCriterion("structure like", value, "structure");
            return (Criteria) this;
        }

        public Criteria andStructureNotLike(String value) {
            addCriterion("structure not like", value, "structure");
            return (Criteria) this;
        }

        public Criteria andStructureIn(List<String> values) {
            addCriterion("structure in", values, "structure");
            return (Criteria) this;
        }

        public Criteria andStructureNotIn(List<String> values) {
            addCriterion("structure not in", values, "structure");
            return (Criteria) this;
        }

        public Criteria andStructureBetween(String value1, String value2) {
            addCriterion("structure between", value1, value2, "structure");
            return (Criteria) this;
        }

        public Criteria andStructureNotBetween(String value1, String value2) {
            addCriterion("structure not between", value1, value2, "structure");
            return (Criteria) this;
        }

        public Criteria andBuildAreaIsNull() {
            addCriterion("build_area is null");
            return (Criteria) this;
        }

        public Criteria andBuildAreaIsNotNull() {
            addCriterion("build_area is not null");
            return (Criteria) this;
        }

        public Criteria andBuildAreaEqualTo(BigDecimal value) {
            addCriterion("build_area =", value, "buildArea");
            return (Criteria) this;
        }

        public Criteria andBuildAreaNotEqualTo(BigDecimal value) {
            addCriterion("build_area <>", value, "buildArea");
            return (Criteria) this;
        }

        public Criteria andBuildAreaGreaterThan(BigDecimal value) {
            addCriterion("build_area >", value, "buildArea");
            return (Criteria) this;
        }

        public Criteria andBuildAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("build_area >=", value, "buildArea");
            return (Criteria) this;
        }

        public Criteria andBuildAreaLessThan(BigDecimal value) {
            addCriterion("build_area <", value, "buildArea");
            return (Criteria) this;
        }

        public Criteria andBuildAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("build_area <=", value, "buildArea");
            return (Criteria) this;
        }

        public Criteria andBuildAreaIn(List<BigDecimal> values) {
            addCriterion("build_area in", values, "buildArea");
            return (Criteria) this;
        }

        public Criteria andBuildAreaNotIn(List<BigDecimal> values) {
            addCriterion("build_area not in", values, "buildArea");
            return (Criteria) this;
        }

        public Criteria andBuildAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("build_area between", value1, value2, "buildArea");
            return (Criteria) this;
        }

        public Criteria andBuildAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("build_area not between", value1, value2, "buildArea");
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

        public Criteria andSpeedProgressIsNull() {
            addCriterion("speed_progress is null");
            return (Criteria) this;
        }

        public Criteria andSpeedProgressIsNotNull() {
            addCriterion("speed_progress is not null");
            return (Criteria) this;
        }

        public Criteria andSpeedProgressEqualTo(String value) {
            addCriterion("speed_progress =", value, "speedProgress");
            return (Criteria) this;
        }

        public Criteria andSpeedProgressNotEqualTo(String value) {
            addCriterion("speed_progress <>", value, "speedProgress");
            return (Criteria) this;
        }

        public Criteria andSpeedProgressGreaterThan(String value) {
            addCriterion("speed_progress >", value, "speedProgress");
            return (Criteria) this;
        }

        public Criteria andSpeedProgressGreaterThanOrEqualTo(String value) {
            addCriterion("speed_progress >=", value, "speedProgress");
            return (Criteria) this;
        }

        public Criteria andSpeedProgressLessThan(String value) {
            addCriterion("speed_progress <", value, "speedProgress");
            return (Criteria) this;
        }

        public Criteria andSpeedProgressLessThanOrEqualTo(String value) {
            addCriterion("speed_progress <=", value, "speedProgress");
            return (Criteria) this;
        }

        public Criteria andSpeedProgressLike(String value) {
            addCriterion("speed_progress like", value, "speedProgress");
            return (Criteria) this;
        }

        public Criteria andSpeedProgressNotLike(String value) {
            addCriterion("speed_progress not like", value, "speedProgress");
            return (Criteria) this;
        }

        public Criteria andSpeedProgressIn(List<String> values) {
            addCriterion("speed_progress in", values, "speedProgress");
            return (Criteria) this;
        }

        public Criteria andSpeedProgressNotIn(List<String> values) {
            addCriterion("speed_progress not in", values, "speedProgress");
            return (Criteria) this;
        }

        public Criteria andSpeedProgressBetween(String value1, String value2) {
            addCriterion("speed_progress between", value1, value2, "speedProgress");
            return (Criteria) this;
        }

        public Criteria andSpeedProgressNotBetween(String value1, String value2) {
            addCriterion("speed_progress not between", value1, value2, "speedProgress");
            return (Criteria) this;
        }

        public Criteria andPaymentRatioIsNull() {
            addCriterion("payment_ratio is null");
            return (Criteria) this;
        }

        public Criteria andPaymentRatioIsNotNull() {
            addCriterion("payment_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentRatioEqualTo(String value) {
            addCriterion("payment_ratio =", value, "paymentRatio");
            return (Criteria) this;
        }

        public Criteria andPaymentRatioNotEqualTo(String value) {
            addCriterion("payment_ratio <>", value, "paymentRatio");
            return (Criteria) this;
        }

        public Criteria andPaymentRatioGreaterThan(String value) {
            addCriterion("payment_ratio >", value, "paymentRatio");
            return (Criteria) this;
        }

        public Criteria andPaymentRatioGreaterThanOrEqualTo(String value) {
            addCriterion("payment_ratio >=", value, "paymentRatio");
            return (Criteria) this;
        }

        public Criteria andPaymentRatioLessThan(String value) {
            addCriterion("payment_ratio <", value, "paymentRatio");
            return (Criteria) this;
        }

        public Criteria andPaymentRatioLessThanOrEqualTo(String value) {
            addCriterion("payment_ratio <=", value, "paymentRatio");
            return (Criteria) this;
        }

        public Criteria andPaymentRatioLike(String value) {
            addCriterion("payment_ratio like", value, "paymentRatio");
            return (Criteria) this;
        }

        public Criteria andPaymentRatioNotLike(String value) {
            addCriterion("payment_ratio not like", value, "paymentRatio");
            return (Criteria) this;
        }

        public Criteria andPaymentRatioIn(List<String> values) {
            addCriterion("payment_ratio in", values, "paymentRatio");
            return (Criteria) this;
        }

        public Criteria andPaymentRatioNotIn(List<String> values) {
            addCriterion("payment_ratio not in", values, "paymentRatio");
            return (Criteria) this;
        }

        public Criteria andPaymentRatioBetween(String value1, String value2) {
            addCriterion("payment_ratio between", value1, value2, "paymentRatio");
            return (Criteria) this;
        }

        public Criteria andPaymentRatioNotBetween(String value1, String value2) {
            addCriterion("payment_ratio not between", value1, value2, "paymentRatio");
            return (Criteria) this;
        }

        public Criteria andBookNetValueIsNull() {
            addCriterion("book_net_value is null");
            return (Criteria) this;
        }

        public Criteria andBookNetValueIsNotNull() {
            addCriterion("book_net_value is not null");
            return (Criteria) this;
        }

        public Criteria andBookNetValueEqualTo(String value) {
            addCriterion("book_net_value =", value, "bookNetValue");
            return (Criteria) this;
        }

        public Criteria andBookNetValueNotEqualTo(String value) {
            addCriterion("book_net_value <>", value, "bookNetValue");
            return (Criteria) this;
        }

        public Criteria andBookNetValueGreaterThan(String value) {
            addCriterion("book_net_value >", value, "bookNetValue");
            return (Criteria) this;
        }

        public Criteria andBookNetValueGreaterThanOrEqualTo(String value) {
            addCriterion("book_net_value >=", value, "bookNetValue");
            return (Criteria) this;
        }

        public Criteria andBookNetValueLessThan(String value) {
            addCriterion("book_net_value <", value, "bookNetValue");
            return (Criteria) this;
        }

        public Criteria andBookNetValueLessThanOrEqualTo(String value) {
            addCriterion("book_net_value <=", value, "bookNetValue");
            return (Criteria) this;
        }

        public Criteria andBookNetValueLike(String value) {
            addCriterion("book_net_value like", value, "bookNetValue");
            return (Criteria) this;
        }

        public Criteria andBookNetValueNotLike(String value) {
            addCriterion("book_net_value not like", value, "bookNetValue");
            return (Criteria) this;
        }

        public Criteria andBookNetValueIn(List<String> values) {
            addCriterion("book_net_value in", values, "bookNetValue");
            return (Criteria) this;
        }

        public Criteria andBookNetValueNotIn(List<String> values) {
            addCriterion("book_net_value not in", values, "bookNetValue");
            return (Criteria) this;
        }

        public Criteria andBookNetValueBetween(String value1, String value2) {
            addCriterion("book_net_value between", value1, value2, "bookNetValue");
            return (Criteria) this;
        }

        public Criteria andBookNetValueNotBetween(String value1, String value2) {
            addCriterion("book_net_value not between", value1, value2, "bookNetValue");
            return (Criteria) this;
        }

        public Criteria andBookValueIsNull() {
            addCriterion("book_value is null");
            return (Criteria) this;
        }

        public Criteria andBookValueIsNotNull() {
            addCriterion("book_value is not null");
            return (Criteria) this;
        }

        public Criteria andBookValueEqualTo(String value) {
            addCriterion("book_value =", value, "bookValue");
            return (Criteria) this;
        }

        public Criteria andBookValueNotEqualTo(String value) {
            addCriterion("book_value <>", value, "bookValue");
            return (Criteria) this;
        }

        public Criteria andBookValueGreaterThan(String value) {
            addCriterion("book_value >", value, "bookValue");
            return (Criteria) this;
        }

        public Criteria andBookValueGreaterThanOrEqualTo(String value) {
            addCriterion("book_value >=", value, "bookValue");
            return (Criteria) this;
        }

        public Criteria andBookValueLessThan(String value) {
            addCriterion("book_value <", value, "bookValue");
            return (Criteria) this;
        }

        public Criteria andBookValueLessThanOrEqualTo(String value) {
            addCriterion("book_value <=", value, "bookValue");
            return (Criteria) this;
        }

        public Criteria andBookValueLike(String value) {
            addCriterion("book_value like", value, "bookValue");
            return (Criteria) this;
        }

        public Criteria andBookValueNotLike(String value) {
            addCriterion("book_value not like", value, "bookValue");
            return (Criteria) this;
        }

        public Criteria andBookValueIn(List<String> values) {
            addCriterion("book_value in", values, "bookValue");
            return (Criteria) this;
        }

        public Criteria andBookValueNotIn(List<String> values) {
            addCriterion("book_value not in", values, "bookValue");
            return (Criteria) this;
        }

        public Criteria andBookValueBetween(String value1, String value2) {
            addCriterion("book_value between", value1, value2, "bookValue");
            return (Criteria) this;
        }

        public Criteria andBookValueNotBetween(String value1, String value2) {
            addCriterion("book_value not between", value1, value2, "bookValue");
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

        public Criteria andBisRecordIsNull() {
            addCriterion("bis_record is null");
            return (Criteria) this;
        }

        public Criteria andBisRecordIsNotNull() {
            addCriterion("bis_record is not null");
            return (Criteria) this;
        }

        public Criteria andBisRecordEqualTo(Boolean value) {
            addCriterion("bis_record =", value, "bisRecord");
            return (Criteria) this;
        }

        public Criteria andBisRecordNotEqualTo(Boolean value) {
            addCriterion("bis_record <>", value, "bisRecord");
            return (Criteria) this;
        }

        public Criteria andBisRecordGreaterThan(Boolean value) {
            addCriterion("bis_record >", value, "bisRecord");
            return (Criteria) this;
        }

        public Criteria andBisRecordGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_record >=", value, "bisRecord");
            return (Criteria) this;
        }

        public Criteria andBisRecordLessThan(Boolean value) {
            addCriterion("bis_record <", value, "bisRecord");
            return (Criteria) this;
        }

        public Criteria andBisRecordLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_record <=", value, "bisRecord");
            return (Criteria) this;
        }

        public Criteria andBisRecordIn(List<Boolean> values) {
            addCriterion("bis_record in", values, "bisRecord");
            return (Criteria) this;
        }

        public Criteria andBisRecordNotIn(List<Boolean> values) {
            addCriterion("bis_record not in", values, "bisRecord");
            return (Criteria) this;
        }

        public Criteria andBisRecordBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_record between", value1, value2, "bisRecord");
            return (Criteria) this;
        }

        public Criteria andBisRecordNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_record not between", value1, value2, "bisRecord");
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