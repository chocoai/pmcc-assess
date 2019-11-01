package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicBuildingOutfitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicBuildingOutfitExample() {
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

        public Criteria andDecorationPartEqualTo(String value) {
            addCriterion("decoration_part =", value, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartNotEqualTo(String value) {
            addCriterion("decoration_part <>", value, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartGreaterThan(String value) {
            addCriterion("decoration_part >", value, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartGreaterThanOrEqualTo(String value) {
            addCriterion("decoration_part >=", value, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartLessThan(String value) {
            addCriterion("decoration_part <", value, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartLessThanOrEqualTo(String value) {
            addCriterion("decoration_part <=", value, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartLike(String value) {
            addCriterion("decoration_part like", value, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartNotLike(String value) {
            addCriterion("decoration_part not like", value, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartIn(List<String> values) {
            addCriterion("decoration_part in", values, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartNotIn(List<String> values) {
            addCriterion("decoration_part not in", values, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartBetween(String value1, String value2) {
            addCriterion("decoration_part between", value1, value2, "decorationPart");
            return (Criteria) this;
        }

        public Criteria andDecorationPartNotBetween(String value1, String value2) {
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

        public Criteria andMaterialGradeIsNull() {
            addCriterion("material_grade is null");
            return (Criteria) this;
        }

        public Criteria andMaterialGradeIsNotNull() {
            addCriterion("material_grade is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialGradeEqualTo(Integer value) {
            addCriterion("material_grade =", value, "materialGrade");
            return (Criteria) this;
        }

        public Criteria andMaterialGradeNotEqualTo(Integer value) {
            addCriterion("material_grade <>", value, "materialGrade");
            return (Criteria) this;
        }

        public Criteria andMaterialGradeGreaterThan(Integer value) {
            addCriterion("material_grade >", value, "materialGrade");
            return (Criteria) this;
        }

        public Criteria andMaterialGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("material_grade >=", value, "materialGrade");
            return (Criteria) this;
        }

        public Criteria andMaterialGradeLessThan(Integer value) {
            addCriterion("material_grade <", value, "materialGrade");
            return (Criteria) this;
        }

        public Criteria andMaterialGradeLessThanOrEqualTo(Integer value) {
            addCriterion("material_grade <=", value, "materialGrade");
            return (Criteria) this;
        }

        public Criteria andMaterialGradeIn(List<Integer> values) {
            addCriterion("material_grade in", values, "materialGrade");
            return (Criteria) this;
        }

        public Criteria andMaterialGradeNotIn(List<Integer> values) {
            addCriterion("material_grade not in", values, "materialGrade");
            return (Criteria) this;
        }

        public Criteria andMaterialGradeBetween(Integer value1, Integer value2) {
            addCriterion("material_grade between", value1, value2, "materialGrade");
            return (Criteria) this;
        }

        public Criteria andMaterialGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("material_grade not between", value1, value2, "materialGrade");
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