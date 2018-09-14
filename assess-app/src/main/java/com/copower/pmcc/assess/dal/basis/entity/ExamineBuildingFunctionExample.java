package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamineBuildingFunctionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamineBuildingFunctionExample() {
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

        public Criteria andDeclareIdIsNull() {
            addCriterion("declare_id is null");
            return (Criteria) this;
        }

        public Criteria andDeclareIdIsNotNull() {
            addCriterion("declare_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeclareIdEqualTo(Integer value) {
            addCriterion("declare_id =", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdNotEqualTo(Integer value) {
            addCriterion("declare_id <>", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdGreaterThan(Integer value) {
            addCriterion("declare_id >", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("declare_id >=", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdLessThan(Integer value) {
            addCriterion("declare_id <", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdLessThanOrEqualTo(Integer value) {
            addCriterion("declare_id <=", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdIn(List<Integer> values) {
            addCriterion("declare_id in", values, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdNotIn(List<Integer> values) {
            addCriterion("declare_id not in", values, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdBetween(Integer value1, Integer value2) {
            addCriterion("declare_id between", value1, value2, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdNotBetween(Integer value1, Integer value2) {
            addCriterion("declare_id not between", value1, value2, "declareId");
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

        public Criteria andExamineTypeIsNull() {
            addCriterion("examine_type is null");
            return (Criteria) this;
        }

        public Criteria andExamineTypeIsNotNull() {
            addCriterion("examine_type is not null");
            return (Criteria) this;
        }

        public Criteria andExamineTypeEqualTo(Integer value) {
            addCriterion("examine_type =", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeNotEqualTo(Integer value) {
            addCriterion("examine_type <>", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeGreaterThan(Integer value) {
            addCriterion("examine_type >", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("examine_type >=", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeLessThan(Integer value) {
            addCriterion("examine_type <", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeLessThanOrEqualTo(Integer value) {
            addCriterion("examine_type <=", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeIn(List<Integer> values) {
            addCriterion("examine_type in", values, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeNotIn(List<Integer> values) {
            addCriterion("examine_type not in", values, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeBetween(Integer value1, Integer value2) {
            addCriterion("examine_type between", value1, value2, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("examine_type not between", value1, value2, "examineType");
            return (Criteria) this;
        }

        public Criteria andBuildingIdIsNull() {
            addCriterion("building_id is null");
            return (Criteria) this;
        }

        public Criteria andBuildingIdIsNotNull() {
            addCriterion("building_id is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingIdEqualTo(Integer value) {
            addCriterion("building_id =", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdNotEqualTo(Integer value) {
            addCriterion("building_id <>", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdGreaterThan(Integer value) {
            addCriterion("building_id >", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_id >=", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdLessThan(Integer value) {
            addCriterion("building_id <", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdLessThanOrEqualTo(Integer value) {
            addCriterion("building_id <=", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdIn(List<Integer> values) {
            addCriterion("building_id in", values, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdNotIn(List<Integer> values) {
            addCriterion("building_id not in", values, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdBetween(Integer value1, Integer value2) {
            addCriterion("building_id between", value1, value2, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdNotBetween(Integer value1, Integer value2) {
            addCriterion("building_id not between", value1, value2, "buildingId");
            return (Criteria) this;
        }

        public Criteria andDecorationPartIsNull() {
            addCriterion("decoration_part is null");
            return (Criteria) this;
        }

        public Criteria andDecorationPartIsNotNull() {
            addCriterion("decoration_part is not null");
            return (Criteria) this;
        }

        public Criteria andDecorationPartEqualTo(Integer value) {
            addCriterion("decoration_part =", value, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartNotEqualTo(Integer value) {
            addCriterion("decoration_part <>", value, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartGreaterThan(Integer value) {
            addCriterion("decoration_part >", value, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartGreaterThanOrEqualTo(Integer value) {
            addCriterion("decoration_part >=", value, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartLessThan(Integer value) {
            addCriterion("decoration_part <", value, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartLessThanOrEqualTo(Integer value) {
            addCriterion("decoration_part <=", value, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartIn(List<Integer> values) {
            addCriterion("decoration_part in", values, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartNotIn(List<Integer> values) {
            addCriterion("decoration_part not in", values, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartBetween(Integer value1, Integer value2) {
            addCriterion("decoration_part between", value1, value2, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartNotBetween(Integer value1, Integer value2) {
            addCriterion("decoration_part not between", value1, value2, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecoratingMaterialIsNull() {
            addCriterion("decorating_material is null");
            return (Criteria) this;
        }

        public Criteria andDecoratingMaterialIsNotNull() {
            addCriterion("decorating_material is not null");
            return (Criteria) this;
        }

        public Criteria andDecoratingMaterialEqualTo(Integer value) {
            addCriterion("decorating_material =", value, "decoratingMaterial");
            return (Criteria) this;
        }

        public Criteria andDecoratingMaterialNotEqualTo(Integer value) {
            addCriterion("decorating_material <>", value, "decoratingMaterial");
            return (Criteria) this;
        }

        public Criteria andDecoratingMaterialGreaterThan(Integer value) {
            addCriterion("decorating_material >", value, "decoratingMaterial");
            return (Criteria) this;
        }

        public Criteria andDecoratingMaterialGreaterThanOrEqualTo(Integer value) {
            addCriterion("decorating_material >=", value, "decoratingMaterial");
            return (Criteria) this;
        }

        public Criteria andDecoratingMaterialLessThan(Integer value) {
            addCriterion("decorating_material <", value, "decoratingMaterial");
            return (Criteria) this;
        }

        public Criteria andDecoratingMaterialLessThanOrEqualTo(Integer value) {
            addCriterion("decorating_material <=", value, "decoratingMaterial");
            return (Criteria) this;
        }

        public Criteria andDecoratingMaterialIn(List<Integer> values) {
            addCriterion("decorating_material in", values, "decoratingMaterial");
            return (Criteria) this;
        }

        public Criteria andDecoratingMaterialNotIn(List<Integer> values) {
            addCriterion("decorating_material not in", values, "decoratingMaterial");
            return (Criteria) this;
        }

        public Criteria andDecoratingMaterialBetween(Integer value1, Integer value2) {
            addCriterion("decorating_material between", value1, value2, "decoratingMaterial");
            return (Criteria) this;
        }

        public Criteria andDecoratingMaterialNotBetween(Integer value1, Integer value2) {
            addCriterion("decorating_material not between", value1, value2, "decoratingMaterial");
            return (Criteria) this;
        }

        public Criteria andMaterialPriceIsNull() {
            addCriterion("material_price is null");
            return (Criteria) this;
        }

        public Criteria andMaterialPriceIsNotNull() {
            addCriterion("material_price is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialPriceEqualTo(Integer value) {
            addCriterion("material_price =", value, "materialPrice");
            return (Criteria) this;
        }

        public Criteria andMaterialPriceNotEqualTo(Integer value) {
            addCriterion("material_price <>", value, "materialPrice");
            return (Criteria) this;
        }

        public Criteria andMaterialPriceGreaterThan(Integer value) {
            addCriterion("material_price >", value, "materialPrice");
            return (Criteria) this;
        }

        public Criteria andMaterialPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("material_price >=", value, "materialPrice");
            return (Criteria) this;
        }

        public Criteria andMaterialPriceLessThan(Integer value) {
            addCriterion("material_price <", value, "materialPrice");
            return (Criteria) this;
        }

        public Criteria andMaterialPriceLessThanOrEqualTo(Integer value) {
            addCriterion("material_price <=", value, "materialPrice");
            return (Criteria) this;
        }

        public Criteria andMaterialPriceIn(List<Integer> values) {
            addCriterion("material_price in", values, "materialPrice");
            return (Criteria) this;
        }

        public Criteria andMaterialPriceNotIn(List<Integer> values) {
            addCriterion("material_price not in", values, "materialPrice");
            return (Criteria) this;
        }

        public Criteria andMaterialPriceBetween(Integer value1, Integer value2) {
            addCriterion("material_price between", value1, value2, "materialPrice");
            return (Criteria) this;
        }

        public Criteria andMaterialPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("material_price not between", value1, value2, "materialPrice");
            return (Criteria) this;
        }

        public Criteria andConstructionTechnologyIsNull() {
            addCriterion("construction_technology is null");
            return (Criteria) this;
        }

        public Criteria andConstructionTechnologyIsNotNull() {
            addCriterion("construction_technology is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionTechnologyEqualTo(Integer value) {
            addCriterion("construction_technology =", value, "constructionTechnology");
            return (Criteria) this;
        }

        public Criteria andConstructionTechnologyNotEqualTo(Integer value) {
            addCriterion("construction_technology <>", value, "constructionTechnology");
            return (Criteria) this;
        }

        public Criteria andConstructionTechnologyGreaterThan(Integer value) {
            addCriterion("construction_technology >", value, "constructionTechnology");
            return (Criteria) this;
        }

        public Criteria andConstructionTechnologyGreaterThanOrEqualTo(Integer value) {
            addCriterion("construction_technology >=", value, "constructionTechnology");
            return (Criteria) this;
        }

        public Criteria andConstructionTechnologyLessThan(Integer value) {
            addCriterion("construction_technology <", value, "constructionTechnology");
            return (Criteria) this;
        }

        public Criteria andConstructionTechnologyLessThanOrEqualTo(Integer value) {
            addCriterion("construction_technology <=", value, "constructionTechnology");
            return (Criteria) this;
        }

        public Criteria andConstructionTechnologyIn(List<Integer> values) {
            addCriterion("construction_technology in", values, "constructionTechnology");
            return (Criteria) this;
        }

        public Criteria andConstructionTechnologyNotIn(List<Integer> values) {
            addCriterion("construction_technology not in", values, "constructionTechnology");
            return (Criteria) this;
        }

        public Criteria andConstructionTechnologyBetween(Integer value1, Integer value2) {
            addCriterion("construction_technology between", value1, value2, "constructionTechnology");
            return (Criteria) this;
        }

        public Criteria andConstructionTechnologyNotBetween(Integer value1, Integer value2) {
            addCriterion("construction_technology not between", value1, value2, "constructionTechnology");
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

        public Criteria andWaterProofIsNull() {
            addCriterion("water_proof is null");
            return (Criteria) this;
        }

        public Criteria andWaterProofIsNotNull() {
            addCriterion("water_proof is not null");
            return (Criteria) this;
        }

        public Criteria andWaterProofEqualTo(String value) {
            addCriterion("water_proof =", value, "waterProof");
            return (Criteria) this;
        }

        public Criteria andWaterProofNotEqualTo(String value) {
            addCriterion("water_proof <>", value, "waterProof");
            return (Criteria) this;
        }

        public Criteria andWaterProofGreaterThan(String value) {
            addCriterion("water_proof >", value, "waterProof");
            return (Criteria) this;
        }

        public Criteria andWaterProofGreaterThanOrEqualTo(String value) {
            addCriterion("water_proof >=", value, "waterProof");
            return (Criteria) this;
        }

        public Criteria andWaterProofLessThan(String value) {
            addCriterion("water_proof <", value, "waterProof");
            return (Criteria) this;
        }

        public Criteria andWaterProofLessThanOrEqualTo(String value) {
            addCriterion("water_proof <=", value, "waterProof");
            return (Criteria) this;
        }

        public Criteria andWaterProofLike(String value) {
            addCriterion("water_proof like", value, "waterProof");
            return (Criteria) this;
        }

        public Criteria andWaterProofNotLike(String value) {
            addCriterion("water_proof not like", value, "waterProof");
            return (Criteria) this;
        }

        public Criteria andWaterProofIn(List<String> values) {
            addCriterion("water_proof in", values, "waterProof");
            return (Criteria) this;
        }

        public Criteria andWaterProofNotIn(List<String> values) {
            addCriterion("water_proof not in", values, "waterProof");
            return (Criteria) this;
        }

        public Criteria andWaterProofBetween(String value1, String value2) {
            addCriterion("water_proof between", value1, value2, "waterProof");
            return (Criteria) this;
        }

        public Criteria andWaterProofNotBetween(String value1, String value2) {
            addCriterion("water_proof not between", value1, value2, "waterProof");
            return (Criteria) this;
        }

        public Criteria andHeatPreservationIsNull() {
            addCriterion("heat_preservation is null");
            return (Criteria) this;
        }

        public Criteria andHeatPreservationIsNotNull() {
            addCriterion("heat_preservation is not null");
            return (Criteria) this;
        }

        public Criteria andHeatPreservationEqualTo(String value) {
            addCriterion("heat_preservation =", value, "heatPreservation");
            return (Criteria) this;
        }

        public Criteria andHeatPreservationNotEqualTo(String value) {
            addCriterion("heat_preservation <>", value, "heatPreservation");
            return (Criteria) this;
        }

        public Criteria andHeatPreservationGreaterThan(String value) {
            addCriterion("heat_preservation >", value, "heatPreservation");
            return (Criteria) this;
        }

        public Criteria andHeatPreservationGreaterThanOrEqualTo(String value) {
            addCriterion("heat_preservation >=", value, "heatPreservation");
            return (Criteria) this;
        }

        public Criteria andHeatPreservationLessThan(String value) {
            addCriterion("heat_preservation <", value, "heatPreservation");
            return (Criteria) this;
        }

        public Criteria andHeatPreservationLessThanOrEqualTo(String value) {
            addCriterion("heat_preservation <=", value, "heatPreservation");
            return (Criteria) this;
        }

        public Criteria andHeatPreservationLike(String value) {
            addCriterion("heat_preservation like", value, "heatPreservation");
            return (Criteria) this;
        }

        public Criteria andHeatPreservationNotLike(String value) {
            addCriterion("heat_preservation not like", value, "heatPreservation");
            return (Criteria) this;
        }

        public Criteria andHeatPreservationIn(List<String> values) {
            addCriterion("heat_preservation in", values, "heatPreservation");
            return (Criteria) this;
        }

        public Criteria andHeatPreservationNotIn(List<String> values) {
            addCriterion("heat_preservation not in", values, "heatPreservation");
            return (Criteria) this;
        }

        public Criteria andHeatPreservationBetween(String value1, String value2) {
            addCriterion("heat_preservation between", value1, value2, "heatPreservation");
            return (Criteria) this;
        }

        public Criteria andHeatPreservationNotBetween(String value1, String value2) {
            addCriterion("heat_preservation not between", value1, value2, "heatPreservation");
            return (Criteria) this;
        }

        public Criteria andBuildNumberIsNull() {
            addCriterion("build_number is null");
            return (Criteria) this;
        }

        public Criteria andBuildNumberIsNotNull() {
            addCriterion("build_number is not null");
            return (Criteria) this;
        }

        public Criteria andBuildNumberEqualTo(String value) {
            addCriterion("build_number =", value, "buildNumber");
            return (Criteria) this;
        }

        public Criteria andBuildNumberNotEqualTo(String value) {
            addCriterion("build_number <>", value, "buildNumber");
            return (Criteria) this;
        }

        public Criteria andBuildNumberGreaterThan(String value) {
            addCriterion("build_number >", value, "buildNumber");
            return (Criteria) this;
        }

        public Criteria andBuildNumberGreaterThanOrEqualTo(String value) {
            addCriterion("build_number >=", value, "buildNumber");
            return (Criteria) this;
        }

        public Criteria andBuildNumberLessThan(String value) {
            addCriterion("build_number <", value, "buildNumber");
            return (Criteria) this;
        }

        public Criteria andBuildNumberLessThanOrEqualTo(String value) {
            addCriterion("build_number <=", value, "buildNumber");
            return (Criteria) this;
        }

        public Criteria andBuildNumberLike(String value) {
            addCriterion("build_number like", value, "buildNumber");
            return (Criteria) this;
        }

        public Criteria andBuildNumberNotLike(String value) {
            addCriterion("build_number not like", value, "buildNumber");
            return (Criteria) this;
        }

        public Criteria andBuildNumberIn(List<String> values) {
            addCriterion("build_number in", values, "buildNumber");
            return (Criteria) this;
        }

        public Criteria andBuildNumberNotIn(List<String> values) {
            addCriterion("build_number not in", values, "buildNumber");
            return (Criteria) this;
        }

        public Criteria andBuildNumberBetween(String value1, String value2) {
            addCriterion("build_number between", value1, value2, "buildNumber");
            return (Criteria) this;
        }

        public Criteria andBuildNumberNotBetween(String value1, String value2) {
            addCriterion("build_number not between", value1, value2, "buildNumber");
            return (Criteria) this;
        }

        public Criteria andHeatInsulationIsNull() {
            addCriterion("heat_insulation is null");
            return (Criteria) this;
        }

        public Criteria andHeatInsulationIsNotNull() {
            addCriterion("heat_insulation is not null");
            return (Criteria) this;
        }

        public Criteria andHeatInsulationEqualTo(String value) {
            addCriterion("heat_insulation =", value, "heatInsulation");
            return (Criteria) this;
        }

        public Criteria andHeatInsulationNotEqualTo(String value) {
            addCriterion("heat_insulation <>", value, "heatInsulation");
            return (Criteria) this;
        }

        public Criteria andHeatInsulationGreaterThan(String value) {
            addCriterion("heat_insulation >", value, "heatInsulation");
            return (Criteria) this;
        }

        public Criteria andHeatInsulationGreaterThanOrEqualTo(String value) {
            addCriterion("heat_insulation >=", value, "heatInsulation");
            return (Criteria) this;
        }

        public Criteria andHeatInsulationLessThan(String value) {
            addCriterion("heat_insulation <", value, "heatInsulation");
            return (Criteria) this;
        }

        public Criteria andHeatInsulationLessThanOrEqualTo(String value) {
            addCriterion("heat_insulation <=", value, "heatInsulation");
            return (Criteria) this;
        }

        public Criteria andHeatInsulationLike(String value) {
            addCriterion("heat_insulation like", value, "heatInsulation");
            return (Criteria) this;
        }

        public Criteria andHeatInsulationNotLike(String value) {
            addCriterion("heat_insulation not like", value, "heatInsulation");
            return (Criteria) this;
        }

        public Criteria andHeatInsulationIn(List<String> values) {
            addCriterion("heat_insulation in", values, "heatInsulation");
            return (Criteria) this;
        }

        public Criteria andHeatInsulationNotIn(List<String> values) {
            addCriterion("heat_insulation not in", values, "heatInsulation");
            return (Criteria) this;
        }

        public Criteria andHeatInsulationBetween(String value1, String value2) {
            addCriterion("heat_insulation between", value1, value2, "heatInsulation");
            return (Criteria) this;
        }

        public Criteria andHeatInsulationNotBetween(String value1, String value2) {
            addCriterion("heat_insulation not between", value1, value2, "heatInsulation");
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