package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeclareBuildEngineeringAndEquipmentCenterExample {
    /**
     * tb_declare_build_engineering_and_equipment_center
     */
    protected String orderByClause;

    /**
     * tb_declare_build_engineering_and_equipment_center
     */
    protected boolean distinct;

    /**
     * tb_declare_build_engineering_and_equipment_center
     */
    protected List<Criteria> oredCriteria;

    public DeclareBuildEngineeringAndEquipmentCenterExample() {
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
     * tb_declare_build_engineering_and_equipment_center
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

        public Criteria andHouseIdIsNull() {
            addCriterion("house_id is null");
            return (Criteria) this;
        }

        public Criteria andHouseIdIsNotNull() {
            addCriterion("house_id is not null");
            return (Criteria) this;
        }

        public Criteria andHouseIdEqualTo(Integer value) {
            addCriterion("house_id =", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotEqualTo(Integer value) {
            addCriterion("house_id <>", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdGreaterThan(Integer value) {
            addCriterion("house_id >", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("house_id >=", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdLessThan(Integer value) {
            addCriterion("house_id <", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("house_id <=", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdIn(List<Integer> values) {
            addCriterion("house_id in", values, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotIn(List<Integer> values) {
            addCriterion("house_id not in", values, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdBetween(Integer value1, Integer value2) {
            addCriterion("house_id between", value1, value2, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("house_id not between", value1, value2, "houseId");
            return (Criteria) this;
        }

        public Criteria andIndicatorIdIsNull() {
            addCriterion("indicator_id is null");
            return (Criteria) this;
        }

        public Criteria andIndicatorIdIsNotNull() {
            addCriterion("indicator_id is not null");
            return (Criteria) this;
        }

        public Criteria andIndicatorIdEqualTo(Integer value) {
            addCriterion("indicator_id =", value, "indicatorId");
            return (Criteria) this;
        }

        public Criteria andIndicatorIdNotEqualTo(Integer value) {
            addCriterion("indicator_id <>", value, "indicatorId");
            return (Criteria) this;
        }

        public Criteria andIndicatorIdGreaterThan(Integer value) {
            addCriterion("indicator_id >", value, "indicatorId");
            return (Criteria) this;
        }

        public Criteria andIndicatorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("indicator_id >=", value, "indicatorId");
            return (Criteria) this;
        }

        public Criteria andIndicatorIdLessThan(Integer value) {
            addCriterion("indicator_id <", value, "indicatorId");
            return (Criteria) this;
        }

        public Criteria andIndicatorIdLessThanOrEqualTo(Integer value) {
            addCriterion("indicator_id <=", value, "indicatorId");
            return (Criteria) this;
        }

        public Criteria andIndicatorIdIn(List<Integer> values) {
            addCriterion("indicator_id in", values, "indicatorId");
            return (Criteria) this;
        }

        public Criteria andIndicatorIdNotIn(List<Integer> values) {
            addCriterion("indicator_id not in", values, "indicatorId");
            return (Criteria) this;
        }

        public Criteria andIndicatorIdBetween(Integer value1, Integer value2) {
            addCriterion("indicator_id between", value1, value2, "indicatorId");
            return (Criteria) this;
        }

        public Criteria andIndicatorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("indicator_id not between", value1, value2, "indicatorId");
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

        public Criteria andBuildEngineeringIdIsNull() {
            addCriterion("build_engineering_id is null");
            return (Criteria) this;
        }

        public Criteria andBuildEngineeringIdIsNotNull() {
            addCriterion("build_engineering_id is not null");
            return (Criteria) this;
        }

        public Criteria andBuildEngineeringIdEqualTo(Integer value) {
            addCriterion("build_engineering_id =", value, "buildEngineeringId");
            return (Criteria) this;
        }

        public Criteria andBuildEngineeringIdNotEqualTo(Integer value) {
            addCriterion("build_engineering_id <>", value, "buildEngineeringId");
            return (Criteria) this;
        }

        public Criteria andBuildEngineeringIdGreaterThan(Integer value) {
            addCriterion("build_engineering_id >", value, "buildEngineeringId");
            return (Criteria) this;
        }

        public Criteria andBuildEngineeringIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("build_engineering_id >=", value, "buildEngineeringId");
            return (Criteria) this;
        }

        public Criteria andBuildEngineeringIdLessThan(Integer value) {
            addCriterion("build_engineering_id <", value, "buildEngineeringId");
            return (Criteria) this;
        }

        public Criteria andBuildEngineeringIdLessThanOrEqualTo(Integer value) {
            addCriterion("build_engineering_id <=", value, "buildEngineeringId");
            return (Criteria) this;
        }

        public Criteria andBuildEngineeringIdIn(List<Integer> values) {
            addCriterion("build_engineering_id in", values, "buildEngineeringId");
            return (Criteria) this;
        }

        public Criteria andBuildEngineeringIdNotIn(List<Integer> values) {
            addCriterion("build_engineering_id not in", values, "buildEngineeringId");
            return (Criteria) this;
        }

        public Criteria andBuildEngineeringIdBetween(Integer value1, Integer value2) {
            addCriterion("build_engineering_id between", value1, value2, "buildEngineeringId");
            return (Criteria) this;
        }

        public Criteria andBuildEngineeringIdNotBetween(Integer value1, Integer value2) {
            addCriterion("build_engineering_id not between", value1, value2, "buildEngineeringId");
            return (Criteria) this;
        }

        public Criteria andBuildEquipmentIdIsNull() {
            addCriterion("build_equipment_id is null");
            return (Criteria) this;
        }

        public Criteria andBuildEquipmentIdIsNotNull() {
            addCriterion("build_equipment_id is not null");
            return (Criteria) this;
        }

        public Criteria andBuildEquipmentIdEqualTo(Integer value) {
            addCriterion("build_equipment_id =", value, "buildEquipmentId");
            return (Criteria) this;
        }

        public Criteria andBuildEquipmentIdNotEqualTo(Integer value) {
            addCriterion("build_equipment_id <>", value, "buildEquipmentId");
            return (Criteria) this;
        }

        public Criteria andBuildEquipmentIdGreaterThan(Integer value) {
            addCriterion("build_equipment_id >", value, "buildEquipmentId");
            return (Criteria) this;
        }

        public Criteria andBuildEquipmentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("build_equipment_id >=", value, "buildEquipmentId");
            return (Criteria) this;
        }

        public Criteria andBuildEquipmentIdLessThan(Integer value) {
            addCriterion("build_equipment_id <", value, "buildEquipmentId");
            return (Criteria) this;
        }

        public Criteria andBuildEquipmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("build_equipment_id <=", value, "buildEquipmentId");
            return (Criteria) this;
        }

        public Criteria andBuildEquipmentIdIn(List<Integer> values) {
            addCriterion("build_equipment_id in", values, "buildEquipmentId");
            return (Criteria) this;
        }

        public Criteria andBuildEquipmentIdNotIn(List<Integer> values) {
            addCriterion("build_equipment_id not in", values, "buildEquipmentId");
            return (Criteria) this;
        }

        public Criteria andBuildEquipmentIdBetween(Integer value1, Integer value2) {
            addCriterion("build_equipment_id between", value1, value2, "buildEquipmentId");
            return (Criteria) this;
        }

        public Criteria andBuildEquipmentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("build_equipment_id not between", value1, value2, "buildEquipmentId");
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

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
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
     * tb_declare_build_engineering_and_equipment_center
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