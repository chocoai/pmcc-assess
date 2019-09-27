package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdEconomicIndicatorsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MdEconomicIndicatorsExample() {
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

        public Criteria andParcelSettingOuterIsNull() {
            addCriterion("parcel_setting_outer is null");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterIsNotNull() {
            addCriterion("parcel_setting_outer is not null");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterEqualTo(String value) {
            addCriterion("parcel_setting_outer =", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterNotEqualTo(String value) {
            addCriterion("parcel_setting_outer <>", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterGreaterThan(String value) {
            addCriterion("parcel_setting_outer >", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterGreaterThanOrEqualTo(String value) {
            addCriterion("parcel_setting_outer >=", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterLessThan(String value) {
            addCriterion("parcel_setting_outer <", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterLessThanOrEqualTo(String value) {
            addCriterion("parcel_setting_outer <=", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterLike(String value) {
            addCriterion("parcel_setting_outer like", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterNotLike(String value) {
            addCriterion("parcel_setting_outer not like", value, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterIn(List<String> values) {
            addCriterion("parcel_setting_outer in", values, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterNotIn(List<String> values) {
            addCriterion("parcel_setting_outer not in", values, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterBetween(String value1, String value2) {
            addCriterion("parcel_setting_outer between", value1, value2, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingOuterNotBetween(String value1, String value2) {
            addCriterion("parcel_setting_outer not between", value1, value2, "parcelSettingOuter");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerIsNull() {
            addCriterion("parcel_setting_inner is null");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerIsNotNull() {
            addCriterion("parcel_setting_inner is not null");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerEqualTo(String value) {
            addCriterion("parcel_setting_inner =", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerNotEqualTo(String value) {
            addCriterion("parcel_setting_inner <>", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerGreaterThan(String value) {
            addCriterion("parcel_setting_inner >", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerGreaterThanOrEqualTo(String value) {
            addCriterion("parcel_setting_inner >=", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerLessThan(String value) {
            addCriterion("parcel_setting_inner <", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerLessThanOrEqualTo(String value) {
            addCriterion("parcel_setting_inner <=", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerLike(String value) {
            addCriterion("parcel_setting_inner like", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerNotLike(String value) {
            addCriterion("parcel_setting_inner not like", value, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerIn(List<String> values) {
            addCriterion("parcel_setting_inner in", values, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerNotIn(List<String> values) {
            addCriterion("parcel_setting_inner not in", values, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerBetween(String value1, String value2) {
            addCriterion("parcel_setting_inner between", value1, value2, "parcelSettingInner");
            return (Criteria) this;
        }

        public Criteria andParcelSettingInnerNotBetween(String value1, String value2) {
            addCriterion("parcel_setting_inner not between", value1, value2, "parcelSettingInner");
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

        public Criteria andProjectFileNameIsNull() {
            addCriterion("project_file_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectFileNameIsNotNull() {
            addCriterion("project_file_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectFileNameEqualTo(String value) {
            addCriterion("project_file_name =", value, "projectFileName");
            return (Criteria) this;
        }

        public Criteria andProjectFileNameNotEqualTo(String value) {
            addCriterion("project_file_name <>", value, "projectFileName");
            return (Criteria) this;
        }

        public Criteria andProjectFileNameGreaterThan(String value) {
            addCriterion("project_file_name >", value, "projectFileName");
            return (Criteria) this;
        }

        public Criteria andProjectFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_file_name >=", value, "projectFileName");
            return (Criteria) this;
        }

        public Criteria andProjectFileNameLessThan(String value) {
            addCriterion("project_file_name <", value, "projectFileName");
            return (Criteria) this;
        }

        public Criteria andProjectFileNameLessThanOrEqualTo(String value) {
            addCriterion("project_file_name <=", value, "projectFileName");
            return (Criteria) this;
        }

        public Criteria andProjectFileNameLike(String value) {
            addCriterion("project_file_name like", value, "projectFileName");
            return (Criteria) this;
        }

        public Criteria andProjectFileNameNotLike(String value) {
            addCriterion("project_file_name not like", value, "projectFileName");
            return (Criteria) this;
        }

        public Criteria andProjectFileNameIn(List<String> values) {
            addCriterion("project_file_name in", values, "projectFileName");
            return (Criteria) this;
        }

        public Criteria andProjectFileNameNotIn(List<String> values) {
            addCriterion("project_file_name not in", values, "projectFileName");
            return (Criteria) this;
        }

        public Criteria andProjectFileNameBetween(String value1, String value2) {
            addCriterion("project_file_name between", value1, value2, "projectFileName");
            return (Criteria) this;
        }

        public Criteria andProjectFileNameNotBetween(String value1, String value2) {
            addCriterion("project_file_name not between", value1, value2, "projectFileName");
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

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(String value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(String value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(String value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(String value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(String value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(String value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLike(String value) {
            addCriterion("grade like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotLike(String value) {
            addCriterion("grade not like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<String> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<String> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(String value1, String value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(String value1, String value2) {
            addCriterion("grade not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andCertUseIsNull() {
            addCriterion("cert_use is null");
            return (Criteria) this;
        }

        public Criteria andCertUseIsNotNull() {
            addCriterion("cert_use is not null");
            return (Criteria) this;
        }

        public Criteria andCertUseEqualTo(String value) {
            addCriterion("cert_use =", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotEqualTo(String value) {
            addCriterion("cert_use <>", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseGreaterThan(String value) {
            addCriterion("cert_use >", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseGreaterThanOrEqualTo(String value) {
            addCriterion("cert_use >=", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseLessThan(String value) {
            addCriterion("cert_use <", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseLessThanOrEqualTo(String value) {
            addCriterion("cert_use <=", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseLike(String value) {
            addCriterion("cert_use like", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotLike(String value) {
            addCriterion("cert_use not like", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseIn(List<String> values) {
            addCriterion("cert_use in", values, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotIn(List<String> values) {
            addCriterion("cert_use not in", values, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseBetween(String value1, String value2) {
            addCriterion("cert_use between", value1, value2, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotBetween(String value1, String value2) {
            addCriterion("cert_use not between", value1, value2, "certUse");
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

        public Criteria andBuildingStructureEqualTo(String value) {
            addCriterion("building_structure =", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureNotEqualTo(String value) {
            addCriterion("building_structure <>", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureGreaterThan(String value) {
            addCriterion("building_structure >", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureGreaterThanOrEqualTo(String value) {
            addCriterion("building_structure >=", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLessThan(String value) {
            addCriterion("building_structure <", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLessThanOrEqualTo(String value) {
            addCriterion("building_structure <=", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLike(String value) {
            addCriterion("building_structure like", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureNotLike(String value) {
            addCriterion("building_structure not like", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureIn(List<String> values) {
            addCriterion("building_structure in", values, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureNotIn(List<String> values) {
            addCriterion("building_structure not in", values, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureBetween(String value1, String value2) {
            addCriterion("building_structure between", value1, value2, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureNotBetween(String value1, String value2) {
            addCriterion("building_structure not between", value1, value2, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingBaseCoverageIsNull() {
            addCriterion("building_base_coverage is null");
            return (Criteria) this;
        }

        public Criteria andBuildingBaseCoverageIsNotNull() {
            addCriterion("building_base_coverage is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingBaseCoverageEqualTo(BigDecimal value) {
            addCriterion("building_base_coverage =", value, "buildingBaseCoverage");
            return (Criteria) this;
        }

        public Criteria andBuildingBaseCoverageNotEqualTo(BigDecimal value) {
            addCriterion("building_base_coverage <>", value, "buildingBaseCoverage");
            return (Criteria) this;
        }

        public Criteria andBuildingBaseCoverageGreaterThan(BigDecimal value) {
            addCriterion("building_base_coverage >", value, "buildingBaseCoverage");
            return (Criteria) this;
        }

        public Criteria andBuildingBaseCoverageGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("building_base_coverage >=", value, "buildingBaseCoverage");
            return (Criteria) this;
        }

        public Criteria andBuildingBaseCoverageLessThan(BigDecimal value) {
            addCriterion("building_base_coverage <", value, "buildingBaseCoverage");
            return (Criteria) this;
        }

        public Criteria andBuildingBaseCoverageLessThanOrEqualTo(BigDecimal value) {
            addCriterion("building_base_coverage <=", value, "buildingBaseCoverage");
            return (Criteria) this;
        }

        public Criteria andBuildingBaseCoverageIn(List<BigDecimal> values) {
            addCriterion("building_base_coverage in", values, "buildingBaseCoverage");
            return (Criteria) this;
        }

        public Criteria andBuildingBaseCoverageNotIn(List<BigDecimal> values) {
            addCriterion("building_base_coverage not in", values, "buildingBaseCoverage");
            return (Criteria) this;
        }

        public Criteria andBuildingBaseCoverageBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("building_base_coverage between", value1, value2, "buildingBaseCoverage");
            return (Criteria) this;
        }

        public Criteria andBuildingBaseCoverageNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("building_base_coverage not between", value1, value2, "buildingBaseCoverage");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitIsNull() {
            addCriterion("building_height_limit is null");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitIsNotNull() {
            addCriterion("building_height_limit is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitEqualTo(BigDecimal value) {
            addCriterion("building_height_limit =", value, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitNotEqualTo(BigDecimal value) {
            addCriterion("building_height_limit <>", value, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitGreaterThan(BigDecimal value) {
            addCriterion("building_height_limit >", value, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("building_height_limit >=", value, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitLessThan(BigDecimal value) {
            addCriterion("building_height_limit <", value, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("building_height_limit <=", value, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitIn(List<BigDecimal> values) {
            addCriterion("building_height_limit in", values, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitNotIn(List<BigDecimal> values) {
            addCriterion("building_height_limit not in", values, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("building_height_limit between", value1, value2, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andBuildingHeightLimitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("building_height_limit not between", value1, value2, "buildingHeightLimit");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateIsNull() {
            addCriterion("volumetric_rate is null");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateIsNotNull() {
            addCriterion("volumetric_rate is not null");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateEqualTo(String value) {
            addCriterion("volumetric_rate =", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateNotEqualTo(String value) {
            addCriterion("volumetric_rate <>", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateGreaterThan(String value) {
            addCriterion("volumetric_rate >", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateGreaterThanOrEqualTo(String value) {
            addCriterion("volumetric_rate >=", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateLessThan(String value) {
            addCriterion("volumetric_rate <", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateLessThanOrEqualTo(String value) {
            addCriterion("volumetric_rate <=", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateLike(String value) {
            addCriterion("volumetric_rate like", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateNotLike(String value) {
            addCriterion("volumetric_rate not like", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateIn(List<String> values) {
            addCriterion("volumetric_rate in", values, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateNotIn(List<String> values) {
            addCriterion("volumetric_rate not in", values, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateBetween(String value1, String value2) {
            addCriterion("volumetric_rate between", value1, value2, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateNotBetween(String value1, String value2) {
            addCriterion("volumetric_rate not between", value1, value2, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityIsNull() {
            addCriterion("building_density is null");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityIsNotNull() {
            addCriterion("building_density is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityEqualTo(String value) {
            addCriterion("building_density =", value, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityNotEqualTo(String value) {
            addCriterion("building_density <>", value, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityGreaterThan(String value) {
            addCriterion("building_density >", value, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityGreaterThanOrEqualTo(String value) {
            addCriterion("building_density >=", value, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityLessThan(String value) {
            addCriterion("building_density <", value, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityLessThanOrEqualTo(String value) {
            addCriterion("building_density <=", value, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityLike(String value) {
            addCriterion("building_density like", value, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityNotLike(String value) {
            addCriterion("building_density not like", value, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityIn(List<String> values) {
            addCriterion("building_density in", values, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityNotIn(List<String> values) {
            addCriterion("building_density not in", values, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityBetween(String value1, String value2) {
            addCriterion("building_density between", value1, value2, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andBuildingDensityNotBetween(String value1, String value2) {
            addCriterion("building_density not between", value1, value2, "buildingDensity");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateIsNull() {
            addCriterion("green_space_rate is null");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateIsNotNull() {
            addCriterion("green_space_rate is not null");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateEqualTo(String value) {
            addCriterion("green_space_rate =", value, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateNotEqualTo(String value) {
            addCriterion("green_space_rate <>", value, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateGreaterThan(String value) {
            addCriterion("green_space_rate >", value, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateGreaterThanOrEqualTo(String value) {
            addCriterion("green_space_rate >=", value, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateLessThan(String value) {
            addCriterion("green_space_rate <", value, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateLessThanOrEqualTo(String value) {
            addCriterion("green_space_rate <=", value, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateLike(String value) {
            addCriterion("green_space_rate like", value, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateNotLike(String value) {
            addCriterion("green_space_rate not like", value, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateIn(List<String> values) {
            addCriterion("green_space_rate in", values, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateNotIn(List<String> values) {
            addCriterion("green_space_rate not in", values, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateBetween(String value1, String value2) {
            addCriterion("green_space_rate between", value1, value2, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andGreenSpaceRateNotBetween(String value1, String value2) {
            addCriterion("green_space_rate not between", value1, value2, "greenSpaceRate");
            return (Criteria) this;
        }

        public Criteria andPlanNetConstructionLandAreaIsNull() {
            addCriterion("plan_net_construction_land_area is null");
            return (Criteria) this;
        }

        public Criteria andPlanNetConstructionLandAreaIsNotNull() {
            addCriterion("plan_net_construction_land_area is not null");
            return (Criteria) this;
        }

        public Criteria andPlanNetConstructionLandAreaEqualTo(BigDecimal value) {
            addCriterion("plan_net_construction_land_area =", value, "planNetConstructionLandArea");
            return (Criteria) this;
        }

        public Criteria andPlanNetConstructionLandAreaNotEqualTo(BigDecimal value) {
            addCriterion("plan_net_construction_land_area <>", value, "planNetConstructionLandArea");
            return (Criteria) this;
        }

        public Criteria andPlanNetConstructionLandAreaGreaterThan(BigDecimal value) {
            addCriterion("plan_net_construction_land_area >", value, "planNetConstructionLandArea");
            return (Criteria) this;
        }

        public Criteria andPlanNetConstructionLandAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("plan_net_construction_land_area >=", value, "planNetConstructionLandArea");
            return (Criteria) this;
        }

        public Criteria andPlanNetConstructionLandAreaLessThan(BigDecimal value) {
            addCriterion("plan_net_construction_land_area <", value, "planNetConstructionLandArea");
            return (Criteria) this;
        }

        public Criteria andPlanNetConstructionLandAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("plan_net_construction_land_area <=", value, "planNetConstructionLandArea");
            return (Criteria) this;
        }

        public Criteria andPlanNetConstructionLandAreaIn(List<BigDecimal> values) {
            addCriterion("plan_net_construction_land_area in", values, "planNetConstructionLandArea");
            return (Criteria) this;
        }

        public Criteria andPlanNetConstructionLandAreaNotIn(List<BigDecimal> values) {
            addCriterion("plan_net_construction_land_area not in", values, "planNetConstructionLandArea");
            return (Criteria) this;
        }

        public Criteria andPlanNetConstructionLandAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plan_net_construction_land_area between", value1, value2, "planNetConstructionLandArea");
            return (Criteria) this;
        }

        public Criteria andPlanNetConstructionLandAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plan_net_construction_land_area not between", value1, value2, "planNetConstructionLandArea");
            return (Criteria) this;
        }

        public Criteria andPlanTotalBuildAreaIsNull() {
            addCriterion("plan_total_build_area is null");
            return (Criteria) this;
        }

        public Criteria andPlanTotalBuildAreaIsNotNull() {
            addCriterion("plan_total_build_area is not null");
            return (Criteria) this;
        }

        public Criteria andPlanTotalBuildAreaEqualTo(BigDecimal value) {
            addCriterion("plan_total_build_area =", value, "planTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andPlanTotalBuildAreaNotEqualTo(BigDecimal value) {
            addCriterion("plan_total_build_area <>", value, "planTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andPlanTotalBuildAreaGreaterThan(BigDecimal value) {
            addCriterion("plan_total_build_area >", value, "planTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andPlanTotalBuildAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("plan_total_build_area >=", value, "planTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andPlanTotalBuildAreaLessThan(BigDecimal value) {
            addCriterion("plan_total_build_area <", value, "planTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andPlanTotalBuildAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("plan_total_build_area <=", value, "planTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andPlanTotalBuildAreaIn(List<BigDecimal> values) {
            addCriterion("plan_total_build_area in", values, "planTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andPlanTotalBuildAreaNotIn(List<BigDecimal> values) {
            addCriterion("plan_total_build_area not in", values, "planTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andPlanTotalBuildAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plan_total_build_area between", value1, value2, "planTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andPlanTotalBuildAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plan_total_build_area not between", value1, value2, "planTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andSetVolumetricRateIsNull() {
            addCriterion("set_volumetric_rate is null");
            return (Criteria) this;
        }

        public Criteria andSetVolumetricRateIsNotNull() {
            addCriterion("set_volumetric_rate is not null");
            return (Criteria) this;
        }

        public Criteria andSetVolumetricRateEqualTo(String value) {
            addCriterion("set_volumetric_rate =", value, "setVolumetricRate");
            return (Criteria) this;
        }

        public Criteria andSetVolumetricRateNotEqualTo(String value) {
            addCriterion("set_volumetric_rate <>", value, "setVolumetricRate");
            return (Criteria) this;
        }

        public Criteria andSetVolumetricRateGreaterThan(String value) {
            addCriterion("set_volumetric_rate >", value, "setVolumetricRate");
            return (Criteria) this;
        }

        public Criteria andSetVolumetricRateGreaterThanOrEqualTo(String value) {
            addCriterion("set_volumetric_rate >=", value, "setVolumetricRate");
            return (Criteria) this;
        }

        public Criteria andSetVolumetricRateLessThan(String value) {
            addCriterion("set_volumetric_rate <", value, "setVolumetricRate");
            return (Criteria) this;
        }

        public Criteria andSetVolumetricRateLessThanOrEqualTo(String value) {
            addCriterion("set_volumetric_rate <=", value, "setVolumetricRate");
            return (Criteria) this;
        }

        public Criteria andSetVolumetricRateLike(String value) {
            addCriterion("set_volumetric_rate like", value, "setVolumetricRate");
            return (Criteria) this;
        }

        public Criteria andSetVolumetricRateNotLike(String value) {
            addCriterion("set_volumetric_rate not like", value, "setVolumetricRate");
            return (Criteria) this;
        }

        public Criteria andSetVolumetricRateIn(List<String> values) {
            addCriterion("set_volumetric_rate in", values, "setVolumetricRate");
            return (Criteria) this;
        }

        public Criteria andSetVolumetricRateNotIn(List<String> values) {
            addCriterion("set_volumetric_rate not in", values, "setVolumetricRate");
            return (Criteria) this;
        }

        public Criteria andSetVolumetricRateBetween(String value1, String value2) {
            addCriterion("set_volumetric_rate between", value1, value2, "setVolumetricRate");
            return (Criteria) this;
        }

        public Criteria andSetVolumetricRateNotBetween(String value1, String value2) {
            addCriterion("set_volumetric_rate not between", value1, value2, "setVolumetricRate");
            return (Criteria) this;
        }

        public Criteria andAssessUseLandAreaIsNull() {
            addCriterion("assess_use_land_area is null");
            return (Criteria) this;
        }

        public Criteria andAssessUseLandAreaIsNotNull() {
            addCriterion("assess_use_land_area is not null");
            return (Criteria) this;
        }

        public Criteria andAssessUseLandAreaEqualTo(BigDecimal value) {
            addCriterion("assess_use_land_area =", value, "assessUseLandArea");
            return (Criteria) this;
        }

        public Criteria andAssessUseLandAreaNotEqualTo(BigDecimal value) {
            addCriterion("assess_use_land_area <>", value, "assessUseLandArea");
            return (Criteria) this;
        }

        public Criteria andAssessUseLandAreaGreaterThan(BigDecimal value) {
            addCriterion("assess_use_land_area >", value, "assessUseLandArea");
            return (Criteria) this;
        }

        public Criteria andAssessUseLandAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_use_land_area >=", value, "assessUseLandArea");
            return (Criteria) this;
        }

        public Criteria andAssessUseLandAreaLessThan(BigDecimal value) {
            addCriterion("assess_use_land_area <", value, "assessUseLandArea");
            return (Criteria) this;
        }

        public Criteria andAssessUseLandAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_use_land_area <=", value, "assessUseLandArea");
            return (Criteria) this;
        }

        public Criteria andAssessUseLandAreaIn(List<BigDecimal> values) {
            addCriterion("assess_use_land_area in", values, "assessUseLandArea");
            return (Criteria) this;
        }

        public Criteria andAssessUseLandAreaNotIn(List<BigDecimal> values) {
            addCriterion("assess_use_land_area not in", values, "assessUseLandArea");
            return (Criteria) this;
        }

        public Criteria andAssessUseLandAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_use_land_area between", value1, value2, "assessUseLandArea");
            return (Criteria) this;
        }

        public Criteria andAssessUseLandAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_use_land_area not between", value1, value2, "assessUseLandArea");
            return (Criteria) this;
        }

        public Criteria andAssessTotalBuildAreaIsNull() {
            addCriterion("assess_total_build_area is null");
            return (Criteria) this;
        }

        public Criteria andAssessTotalBuildAreaIsNotNull() {
            addCriterion("assess_total_build_area is not null");
            return (Criteria) this;
        }

        public Criteria andAssessTotalBuildAreaEqualTo(BigDecimal value) {
            addCriterion("assess_total_build_area =", value, "assessTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andAssessTotalBuildAreaNotEqualTo(BigDecimal value) {
            addCriterion("assess_total_build_area <>", value, "assessTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andAssessTotalBuildAreaGreaterThan(BigDecimal value) {
            addCriterion("assess_total_build_area >", value, "assessTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andAssessTotalBuildAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_total_build_area >=", value, "assessTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andAssessTotalBuildAreaLessThan(BigDecimal value) {
            addCriterion("assess_total_build_area <", value, "assessTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andAssessTotalBuildAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_total_build_area <=", value, "assessTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andAssessTotalBuildAreaIn(List<BigDecimal> values) {
            addCriterion("assess_total_build_area in", values, "assessTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andAssessTotalBuildAreaNotIn(List<BigDecimal> values) {
            addCriterion("assess_total_build_area not in", values, "assessTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andAssessTotalBuildAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_total_build_area between", value1, value2, "assessTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andAssessTotalBuildAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_total_build_area not between", value1, value2, "assessTotalBuildArea");
            return (Criteria) this;
        }

        public Criteria andPlanDateIsNull() {
            addCriterion("plan_date is null");
            return (Criteria) this;
        }

        public Criteria andPlanDateIsNotNull() {
            addCriterion("plan_date is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDateEqualTo(Date value) {
            addCriterion("plan_date =", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateNotEqualTo(Date value) {
            addCriterion("plan_date <>", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateGreaterThan(Date value) {
            addCriterion("plan_date >", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateGreaterThanOrEqualTo(Date value) {
            addCriterion("plan_date >=", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateLessThan(Date value) {
            addCriterion("plan_date <", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateLessThanOrEqualTo(Date value) {
            addCriterion("plan_date <=", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateIn(List<Date> values) {
            addCriterion("plan_date in", values, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateNotIn(List<Date> values) {
            addCriterion("plan_date not in", values, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateBetween(Date value1, Date value2) {
            addCriterion("plan_date between", value1, value2, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateNotBetween(Date value1, Date value2) {
            addCriterion("plan_date not between", value1, value2, "planDate");
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