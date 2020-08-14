package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeclareBuildingConstructionPermitExample {
    /**
     * tb_declare_building_construction_permit
     */
    protected String orderByClause;

    /**
     * tb_declare_building_construction_permit
     */
    protected boolean distinct;

    /**
     * tb_declare_building_construction_permit
     */
    protected List<Criteria> oredCriteria;

    public DeclareBuildingConstructionPermitExample() {
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
     * tb_declare_building_construction_permit
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

        public Criteria andCertificateNumberIsNull() {
            addCriterion("certificate_number is null");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberIsNotNull() {
            addCriterion("certificate_number is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberEqualTo(String value) {
            addCriterion("certificate_number =", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberNotEqualTo(String value) {
            addCriterion("certificate_number <>", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberGreaterThan(String value) {
            addCriterion("certificate_number >", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberGreaterThanOrEqualTo(String value) {
            addCriterion("certificate_number >=", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberLessThan(String value) {
            addCriterion("certificate_number <", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberLessThanOrEqualTo(String value) {
            addCriterion("certificate_number <=", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberLike(String value) {
            addCriterion("certificate_number like", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberNotLike(String value) {
            addCriterion("certificate_number not like", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberIn(List<String> values) {
            addCriterion("certificate_number in", values, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberNotIn(List<String> values) {
            addCriterion("certificate_number not in", values, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberBetween(String value1, String value2) {
            addCriterion("certificate_number between", value1, value2, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberNotBetween(String value1, String value2) {
            addCriterion("certificate_number not between", value1, value2, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andIssuingOrganIsNull() {
            addCriterion("issuing_organ is null");
            return (Criteria) this;
        }

        public Criteria andIssuingOrganIsNotNull() {
            addCriterion("issuing_organ is not null");
            return (Criteria) this;
        }

        public Criteria andIssuingOrganEqualTo(String value) {
            addCriterion("issuing_organ =", value, "issuingOrgan");
            return (Criteria) this;
        }

        public Criteria andIssuingOrganNotEqualTo(String value) {
            addCriterion("issuing_organ <>", value, "issuingOrgan");
            return (Criteria) this;
        }

        public Criteria andIssuingOrganGreaterThan(String value) {
            addCriterion("issuing_organ >", value, "issuingOrgan");
            return (Criteria) this;
        }

        public Criteria andIssuingOrganGreaterThanOrEqualTo(String value) {
            addCriterion("issuing_organ >=", value, "issuingOrgan");
            return (Criteria) this;
        }

        public Criteria andIssuingOrganLessThan(String value) {
            addCriterion("issuing_organ <", value, "issuingOrgan");
            return (Criteria) this;
        }

        public Criteria andIssuingOrganLessThanOrEqualTo(String value) {
            addCriterion("issuing_organ <=", value, "issuingOrgan");
            return (Criteria) this;
        }

        public Criteria andIssuingOrganLike(String value) {
            addCriterion("issuing_organ like", value, "issuingOrgan");
            return (Criteria) this;
        }

        public Criteria andIssuingOrganNotLike(String value) {
            addCriterion("issuing_organ not like", value, "issuingOrgan");
            return (Criteria) this;
        }

        public Criteria andIssuingOrganIn(List<String> values) {
            addCriterion("issuing_organ in", values, "issuingOrgan");
            return (Criteria) this;
        }

        public Criteria andIssuingOrganNotIn(List<String> values) {
            addCriterion("issuing_organ not in", values, "issuingOrgan");
            return (Criteria) this;
        }

        public Criteria andIssuingOrganBetween(String value1, String value2) {
            addCriterion("issuing_organ between", value1, value2, "issuingOrgan");
            return (Criteria) this;
        }

        public Criteria andIssuingOrganNotBetween(String value1, String value2) {
            addCriterion("issuing_organ not between", value1, value2, "issuingOrgan");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(Date value) {
            addCriterion("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(Date value) {
            addCriterion("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(Date value) {
            addCriterion("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(Date value) {
            addCriterion("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(Date value) {
            addCriterion("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(Date value) {
            addCriterion("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<Date> values) {
            addCriterion("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<Date> values) {
            addCriterion("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(Date value1, Date value2) {
            addCriterion("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(Date value1, Date value2) {
            addCriterion("date not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andBuildUnitIsNull() {
            addCriterion("build_unit is null");
            return (Criteria) this;
        }

        public Criteria andBuildUnitIsNotNull() {
            addCriterion("build_unit is not null");
            return (Criteria) this;
        }

        public Criteria andBuildUnitEqualTo(String value) {
            addCriterion("build_unit =", value, "buildUnit");
            return (Criteria) this;
        }

        public Criteria andBuildUnitNotEqualTo(String value) {
            addCriterion("build_unit <>", value, "buildUnit");
            return (Criteria) this;
        }

        public Criteria andBuildUnitGreaterThan(String value) {
            addCriterion("build_unit >", value, "buildUnit");
            return (Criteria) this;
        }

        public Criteria andBuildUnitGreaterThanOrEqualTo(String value) {
            addCriterion("build_unit >=", value, "buildUnit");
            return (Criteria) this;
        }

        public Criteria andBuildUnitLessThan(String value) {
            addCriterion("build_unit <", value, "buildUnit");
            return (Criteria) this;
        }

        public Criteria andBuildUnitLessThanOrEqualTo(String value) {
            addCriterion("build_unit <=", value, "buildUnit");
            return (Criteria) this;
        }

        public Criteria andBuildUnitLike(String value) {
            addCriterion("build_unit like", value, "buildUnit");
            return (Criteria) this;
        }

        public Criteria andBuildUnitNotLike(String value) {
            addCriterion("build_unit not like", value, "buildUnit");
            return (Criteria) this;
        }

        public Criteria andBuildUnitIn(List<String> values) {
            addCriterion("build_unit in", values, "buildUnit");
            return (Criteria) this;
        }

        public Criteria andBuildUnitNotIn(List<String> values) {
            addCriterion("build_unit not in", values, "buildUnit");
            return (Criteria) this;
        }

        public Criteria andBuildUnitBetween(String value1, String value2) {
            addCriterion("build_unit between", value1, value2, "buildUnit");
            return (Criteria) this;
        }

        public Criteria andBuildUnitNotBetween(String value1, String value2) {
            addCriterion("build_unit not between", value1, value2, "buildUnit");
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

        public Criteria andBuildAddressIsNull() {
            addCriterion("build_address is null");
            return (Criteria) this;
        }

        public Criteria andBuildAddressIsNotNull() {
            addCriterion("build_address is not null");
            return (Criteria) this;
        }

        public Criteria andBuildAddressEqualTo(String value) {
            addCriterion("build_address =", value, "buildAddress");
            return (Criteria) this;
        }

        public Criteria andBuildAddressNotEqualTo(String value) {
            addCriterion("build_address <>", value, "buildAddress");
            return (Criteria) this;
        }

        public Criteria andBuildAddressGreaterThan(String value) {
            addCriterion("build_address >", value, "buildAddress");
            return (Criteria) this;
        }

        public Criteria andBuildAddressGreaterThanOrEqualTo(String value) {
            addCriterion("build_address >=", value, "buildAddress");
            return (Criteria) this;
        }

        public Criteria andBuildAddressLessThan(String value) {
            addCriterion("build_address <", value, "buildAddress");
            return (Criteria) this;
        }

        public Criteria andBuildAddressLessThanOrEqualTo(String value) {
            addCriterion("build_address <=", value, "buildAddress");
            return (Criteria) this;
        }

        public Criteria andBuildAddressLike(String value) {
            addCriterion("build_address like", value, "buildAddress");
            return (Criteria) this;
        }

        public Criteria andBuildAddressNotLike(String value) {
            addCriterion("build_address not like", value, "buildAddress");
            return (Criteria) this;
        }

        public Criteria andBuildAddressIn(List<String> values) {
            addCriterion("build_address in", values, "buildAddress");
            return (Criteria) this;
        }

        public Criteria andBuildAddressNotIn(List<String> values) {
            addCriterion("build_address not in", values, "buildAddress");
            return (Criteria) this;
        }

        public Criteria andBuildAddressBetween(String value1, String value2) {
            addCriterion("build_address between", value1, value2, "buildAddress");
            return (Criteria) this;
        }

        public Criteria andBuildAddressNotBetween(String value1, String value2) {
            addCriterion("build_address not between", value1, value2, "buildAddress");
            return (Criteria) this;
        }

        public Criteria andScaleConstructionIsNull() {
            addCriterion("scale_construction is null");
            return (Criteria) this;
        }

        public Criteria andScaleConstructionIsNotNull() {
            addCriterion("scale_construction is not null");
            return (Criteria) this;
        }

        public Criteria andScaleConstructionEqualTo(String value) {
            addCriterion("scale_construction =", value, "scaleConstruction");
            return (Criteria) this;
        }

        public Criteria andScaleConstructionNotEqualTo(String value) {
            addCriterion("scale_construction <>", value, "scaleConstruction");
            return (Criteria) this;
        }

        public Criteria andScaleConstructionGreaterThan(String value) {
            addCriterion("scale_construction >", value, "scaleConstruction");
            return (Criteria) this;
        }

        public Criteria andScaleConstructionGreaterThanOrEqualTo(String value) {
            addCriterion("scale_construction >=", value, "scaleConstruction");
            return (Criteria) this;
        }

        public Criteria andScaleConstructionLessThan(String value) {
            addCriterion("scale_construction <", value, "scaleConstruction");
            return (Criteria) this;
        }

        public Criteria andScaleConstructionLessThanOrEqualTo(String value) {
            addCriterion("scale_construction <=", value, "scaleConstruction");
            return (Criteria) this;
        }

        public Criteria andScaleConstructionLike(String value) {
            addCriterion("scale_construction like", value, "scaleConstruction");
            return (Criteria) this;
        }

        public Criteria andScaleConstructionNotLike(String value) {
            addCriterion("scale_construction not like", value, "scaleConstruction");
            return (Criteria) this;
        }

        public Criteria andScaleConstructionIn(List<String> values) {
            addCriterion("scale_construction in", values, "scaleConstruction");
            return (Criteria) this;
        }

        public Criteria andScaleConstructionNotIn(List<String> values) {
            addCriterion("scale_construction not in", values, "scaleConstruction");
            return (Criteria) this;
        }

        public Criteria andScaleConstructionBetween(String value1, String value2) {
            addCriterion("scale_construction between", value1, value2, "scaleConstruction");
            return (Criteria) this;
        }

        public Criteria andScaleConstructionNotBetween(String value1, String value2) {
            addCriterion("scale_construction not between", value1, value2, "scaleConstruction");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitIsNull() {
            addCriterion("reconnaissance_unit is null");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitIsNotNull() {
            addCriterion("reconnaissance_unit is not null");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitEqualTo(String value) {
            addCriterion("reconnaissance_unit =", value, "reconnaissanceUnit");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitNotEqualTo(String value) {
            addCriterion("reconnaissance_unit <>", value, "reconnaissanceUnit");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitGreaterThan(String value) {
            addCriterion("reconnaissance_unit >", value, "reconnaissanceUnit");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitGreaterThanOrEqualTo(String value) {
            addCriterion("reconnaissance_unit >=", value, "reconnaissanceUnit");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitLessThan(String value) {
            addCriterion("reconnaissance_unit <", value, "reconnaissanceUnit");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitLessThanOrEqualTo(String value) {
            addCriterion("reconnaissance_unit <=", value, "reconnaissanceUnit");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitLike(String value) {
            addCriterion("reconnaissance_unit like", value, "reconnaissanceUnit");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitNotLike(String value) {
            addCriterion("reconnaissance_unit not like", value, "reconnaissanceUnit");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitIn(List<String> values) {
            addCriterion("reconnaissance_unit in", values, "reconnaissanceUnit");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitNotIn(List<String> values) {
            addCriterion("reconnaissance_unit not in", values, "reconnaissanceUnit");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitBetween(String value1, String value2) {
            addCriterion("reconnaissance_unit between", value1, value2, "reconnaissanceUnit");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitNotBetween(String value1, String value2) {
            addCriterion("reconnaissance_unit not between", value1, value2, "reconnaissanceUnit");
            return (Criteria) this;
        }

        public Criteria andDesignUnitIsNull() {
            addCriterion("design_unit is null");
            return (Criteria) this;
        }

        public Criteria andDesignUnitIsNotNull() {
            addCriterion("design_unit is not null");
            return (Criteria) this;
        }

        public Criteria andDesignUnitEqualTo(String value) {
            addCriterion("design_unit =", value, "designUnit");
            return (Criteria) this;
        }

        public Criteria andDesignUnitNotEqualTo(String value) {
            addCriterion("design_unit <>", value, "designUnit");
            return (Criteria) this;
        }

        public Criteria andDesignUnitGreaterThan(String value) {
            addCriterion("design_unit >", value, "designUnit");
            return (Criteria) this;
        }

        public Criteria andDesignUnitGreaterThanOrEqualTo(String value) {
            addCriterion("design_unit >=", value, "designUnit");
            return (Criteria) this;
        }

        public Criteria andDesignUnitLessThan(String value) {
            addCriterion("design_unit <", value, "designUnit");
            return (Criteria) this;
        }

        public Criteria andDesignUnitLessThanOrEqualTo(String value) {
            addCriterion("design_unit <=", value, "designUnit");
            return (Criteria) this;
        }

        public Criteria andDesignUnitLike(String value) {
            addCriterion("design_unit like", value, "designUnit");
            return (Criteria) this;
        }

        public Criteria andDesignUnitNotLike(String value) {
            addCriterion("design_unit not like", value, "designUnit");
            return (Criteria) this;
        }

        public Criteria andDesignUnitIn(List<String> values) {
            addCriterion("design_unit in", values, "designUnit");
            return (Criteria) this;
        }

        public Criteria andDesignUnitNotIn(List<String> values) {
            addCriterion("design_unit not in", values, "designUnit");
            return (Criteria) this;
        }

        public Criteria andDesignUnitBetween(String value1, String value2) {
            addCriterion("design_unit between", value1, value2, "designUnit");
            return (Criteria) this;
        }

        public Criteria andDesignUnitNotBetween(String value1, String value2) {
            addCriterion("design_unit not between", value1, value2, "designUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitIsNull() {
            addCriterion("construction_unit is null");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitIsNotNull() {
            addCriterion("construction_unit is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitEqualTo(String value) {
            addCriterion("construction_unit =", value, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitNotEqualTo(String value) {
            addCriterion("construction_unit <>", value, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitGreaterThan(String value) {
            addCriterion("construction_unit >", value, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitGreaterThanOrEqualTo(String value) {
            addCriterion("construction_unit >=", value, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitLessThan(String value) {
            addCriterion("construction_unit <", value, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitLessThanOrEqualTo(String value) {
            addCriterion("construction_unit <=", value, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitLike(String value) {
            addCriterion("construction_unit like", value, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitNotLike(String value) {
            addCriterion("construction_unit not like", value, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitIn(List<String> values) {
            addCriterion("construction_unit in", values, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitNotIn(List<String> values) {
            addCriterion("construction_unit not in", values, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitBetween(String value1, String value2) {
            addCriterion("construction_unit between", value1, value2, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitNotBetween(String value1, String value2) {
            addCriterion("construction_unit not between", value1, value2, "constructionUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionControlUnitIsNull() {
            addCriterion("construction_control_unit is null");
            return (Criteria) this;
        }

        public Criteria andConstructionControlUnitIsNotNull() {
            addCriterion("construction_control_unit is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionControlUnitEqualTo(String value) {
            addCriterion("construction_control_unit =", value, "constructionControlUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionControlUnitNotEqualTo(String value) {
            addCriterion("construction_control_unit <>", value, "constructionControlUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionControlUnitGreaterThan(String value) {
            addCriterion("construction_control_unit >", value, "constructionControlUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionControlUnitGreaterThanOrEqualTo(String value) {
            addCriterion("construction_control_unit >=", value, "constructionControlUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionControlUnitLessThan(String value) {
            addCriterion("construction_control_unit <", value, "constructionControlUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionControlUnitLessThanOrEqualTo(String value) {
            addCriterion("construction_control_unit <=", value, "constructionControlUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionControlUnitLike(String value) {
            addCriterion("construction_control_unit like", value, "constructionControlUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionControlUnitNotLike(String value) {
            addCriterion("construction_control_unit not like", value, "constructionControlUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionControlUnitIn(List<String> values) {
            addCriterion("construction_control_unit in", values, "constructionControlUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionControlUnitNotIn(List<String> values) {
            addCriterion("construction_control_unit not in", values, "constructionControlUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionControlUnitBetween(String value1, String value2) {
            addCriterion("construction_control_unit between", value1, value2, "constructionControlUnit");
            return (Criteria) this;
        }

        public Criteria andConstructionControlUnitNotBetween(String value1, String value2) {
            addCriterion("construction_control_unit not between", value1, value2, "constructionControlUnit");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitPersonIsNull() {
            addCriterion("reconnaissance_unit_person is null");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitPersonIsNotNull() {
            addCriterion("reconnaissance_unit_person is not null");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitPersonEqualTo(String value) {
            addCriterion("reconnaissance_unit_person =", value, "reconnaissanceUnitPerson");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitPersonNotEqualTo(String value) {
            addCriterion("reconnaissance_unit_person <>", value, "reconnaissanceUnitPerson");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitPersonGreaterThan(String value) {
            addCriterion("reconnaissance_unit_person >", value, "reconnaissanceUnitPerson");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitPersonGreaterThanOrEqualTo(String value) {
            addCriterion("reconnaissance_unit_person >=", value, "reconnaissanceUnitPerson");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitPersonLessThan(String value) {
            addCriterion("reconnaissance_unit_person <", value, "reconnaissanceUnitPerson");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitPersonLessThanOrEqualTo(String value) {
            addCriterion("reconnaissance_unit_person <=", value, "reconnaissanceUnitPerson");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitPersonLike(String value) {
            addCriterion("reconnaissance_unit_person like", value, "reconnaissanceUnitPerson");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitPersonNotLike(String value) {
            addCriterion("reconnaissance_unit_person not like", value, "reconnaissanceUnitPerson");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitPersonIn(List<String> values) {
            addCriterion("reconnaissance_unit_person in", values, "reconnaissanceUnitPerson");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitPersonNotIn(List<String> values) {
            addCriterion("reconnaissance_unit_person not in", values, "reconnaissanceUnitPerson");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitPersonBetween(String value1, String value2) {
            addCriterion("reconnaissance_unit_person between", value1, value2, "reconnaissanceUnitPerson");
            return (Criteria) this;
        }

        public Criteria andReconnaissanceUnitPersonNotBetween(String value1, String value2) {
            addCriterion("reconnaissance_unit_person not between", value1, value2, "reconnaissanceUnitPerson");
            return (Criteria) this;
        }

        public Criteria andDesignUnitPersonIsNull() {
            addCriterion("design_unit_person is null");
            return (Criteria) this;
        }

        public Criteria andDesignUnitPersonIsNotNull() {
            addCriterion("design_unit_person is not null");
            return (Criteria) this;
        }

        public Criteria andDesignUnitPersonEqualTo(String value) {
            addCriterion("design_unit_person =", value, "designUnitPerson");
            return (Criteria) this;
        }

        public Criteria andDesignUnitPersonNotEqualTo(String value) {
            addCriterion("design_unit_person <>", value, "designUnitPerson");
            return (Criteria) this;
        }

        public Criteria andDesignUnitPersonGreaterThan(String value) {
            addCriterion("design_unit_person >", value, "designUnitPerson");
            return (Criteria) this;
        }

        public Criteria andDesignUnitPersonGreaterThanOrEqualTo(String value) {
            addCriterion("design_unit_person >=", value, "designUnitPerson");
            return (Criteria) this;
        }

        public Criteria andDesignUnitPersonLessThan(String value) {
            addCriterion("design_unit_person <", value, "designUnitPerson");
            return (Criteria) this;
        }

        public Criteria andDesignUnitPersonLessThanOrEqualTo(String value) {
            addCriterion("design_unit_person <=", value, "designUnitPerson");
            return (Criteria) this;
        }

        public Criteria andDesignUnitPersonLike(String value) {
            addCriterion("design_unit_person like", value, "designUnitPerson");
            return (Criteria) this;
        }

        public Criteria andDesignUnitPersonNotLike(String value) {
            addCriterion("design_unit_person not like", value, "designUnitPerson");
            return (Criteria) this;
        }

        public Criteria andDesignUnitPersonIn(List<String> values) {
            addCriterion("design_unit_person in", values, "designUnitPerson");
            return (Criteria) this;
        }

        public Criteria andDesignUnitPersonNotIn(List<String> values) {
            addCriterion("design_unit_person not in", values, "designUnitPerson");
            return (Criteria) this;
        }

        public Criteria andDesignUnitPersonBetween(String value1, String value2) {
            addCriterion("design_unit_person between", value1, value2, "designUnitPerson");
            return (Criteria) this;
        }

        public Criteria andDesignUnitPersonNotBetween(String value1, String value2) {
            addCriterion("design_unit_person not between", value1, value2, "designUnitPerson");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitPersonIsNull() {
            addCriterion("construction_unit_person is null");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitPersonIsNotNull() {
            addCriterion("construction_unit_person is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitPersonEqualTo(String value) {
            addCriterion("construction_unit_person =", value, "constructionUnitPerson");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitPersonNotEqualTo(String value) {
            addCriterion("construction_unit_person <>", value, "constructionUnitPerson");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitPersonGreaterThan(String value) {
            addCriterion("construction_unit_person >", value, "constructionUnitPerson");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitPersonGreaterThanOrEqualTo(String value) {
            addCriterion("construction_unit_person >=", value, "constructionUnitPerson");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitPersonLessThan(String value) {
            addCriterion("construction_unit_person <", value, "constructionUnitPerson");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitPersonLessThanOrEqualTo(String value) {
            addCriterion("construction_unit_person <=", value, "constructionUnitPerson");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitPersonLike(String value) {
            addCriterion("construction_unit_person like", value, "constructionUnitPerson");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitPersonNotLike(String value) {
            addCriterion("construction_unit_person not like", value, "constructionUnitPerson");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitPersonIn(List<String> values) {
            addCriterion("construction_unit_person in", values, "constructionUnitPerson");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitPersonNotIn(List<String> values) {
            addCriterion("construction_unit_person not in", values, "constructionUnitPerson");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitPersonBetween(String value1, String value2) {
            addCriterion("construction_unit_person between", value1, value2, "constructionUnitPerson");
            return (Criteria) this;
        }

        public Criteria andConstructionUnitPersonNotBetween(String value1, String value2) {
            addCriterion("construction_unit_person not between", value1, value2, "constructionUnitPerson");
            return (Criteria) this;
        }

        public Criteria andChiefEngineerConstructionInspectionIsNull() {
            addCriterion("chief_engineer_construction_inspection is null");
            return (Criteria) this;
        }

        public Criteria andChiefEngineerConstructionInspectionIsNotNull() {
            addCriterion("chief_engineer_construction_inspection is not null");
            return (Criteria) this;
        }

        public Criteria andChiefEngineerConstructionInspectionEqualTo(String value) {
            addCriterion("chief_engineer_construction_inspection =", value, "chiefEngineerConstructionInspection");
            return (Criteria) this;
        }

        public Criteria andChiefEngineerConstructionInspectionNotEqualTo(String value) {
            addCriterion("chief_engineer_construction_inspection <>", value, "chiefEngineerConstructionInspection");
            return (Criteria) this;
        }

        public Criteria andChiefEngineerConstructionInspectionGreaterThan(String value) {
            addCriterion("chief_engineer_construction_inspection >", value, "chiefEngineerConstructionInspection");
            return (Criteria) this;
        }

        public Criteria andChiefEngineerConstructionInspectionGreaterThanOrEqualTo(String value) {
            addCriterion("chief_engineer_construction_inspection >=", value, "chiefEngineerConstructionInspection");
            return (Criteria) this;
        }

        public Criteria andChiefEngineerConstructionInspectionLessThan(String value) {
            addCriterion("chief_engineer_construction_inspection <", value, "chiefEngineerConstructionInspection");
            return (Criteria) this;
        }

        public Criteria andChiefEngineerConstructionInspectionLessThanOrEqualTo(String value) {
            addCriterion("chief_engineer_construction_inspection <=", value, "chiefEngineerConstructionInspection");
            return (Criteria) this;
        }

        public Criteria andChiefEngineerConstructionInspectionLike(String value) {
            addCriterion("chief_engineer_construction_inspection like", value, "chiefEngineerConstructionInspection");
            return (Criteria) this;
        }

        public Criteria andChiefEngineerConstructionInspectionNotLike(String value) {
            addCriterion("chief_engineer_construction_inspection not like", value, "chiefEngineerConstructionInspection");
            return (Criteria) this;
        }

        public Criteria andChiefEngineerConstructionInspectionIn(List<String> values) {
            addCriterion("chief_engineer_construction_inspection in", values, "chiefEngineerConstructionInspection");
            return (Criteria) this;
        }

        public Criteria andChiefEngineerConstructionInspectionNotIn(List<String> values) {
            addCriterion("chief_engineer_construction_inspection not in", values, "chiefEngineerConstructionInspection");
            return (Criteria) this;
        }

        public Criteria andChiefEngineerConstructionInspectionBetween(String value1, String value2) {
            addCriterion("chief_engineer_construction_inspection between", value1, value2, "chiefEngineerConstructionInspection");
            return (Criteria) this;
        }

        public Criteria andChiefEngineerConstructionInspectionNotBetween(String value1, String value2) {
            addCriterion("chief_engineer_construction_inspection not between", value1, value2, "chiefEngineerConstructionInspection");
            return (Criteria) this;
        }

        public Criteria andContractPeriodIsNull() {
            addCriterion("contract_period is null");
            return (Criteria) this;
        }

        public Criteria andContractPeriodIsNotNull() {
            addCriterion("contract_period is not null");
            return (Criteria) this;
        }

        public Criteria andContractPeriodEqualTo(Date value) {
            addCriterion("contract_period =", value, "contractPeriod");
            return (Criteria) this;
        }

        public Criteria andContractPeriodNotEqualTo(Date value) {
            addCriterion("contract_period <>", value, "contractPeriod");
            return (Criteria) this;
        }

        public Criteria andContractPeriodGreaterThan(Date value) {
            addCriterion("contract_period >", value, "contractPeriod");
            return (Criteria) this;
        }

        public Criteria andContractPeriodGreaterThanOrEqualTo(Date value) {
            addCriterion("contract_period >=", value, "contractPeriod");
            return (Criteria) this;
        }

        public Criteria andContractPeriodLessThan(Date value) {
            addCriterion("contract_period <", value, "contractPeriod");
            return (Criteria) this;
        }

        public Criteria andContractPeriodLessThanOrEqualTo(Date value) {
            addCriterion("contract_period <=", value, "contractPeriod");
            return (Criteria) this;
        }

        public Criteria andContractPeriodIn(List<Date> values) {
            addCriterion("contract_period in", values, "contractPeriod");
            return (Criteria) this;
        }

        public Criteria andContractPeriodNotIn(List<Date> values) {
            addCriterion("contract_period not in", values, "contractPeriod");
            return (Criteria) this;
        }

        public Criteria andContractPeriodBetween(Date value1, Date value2) {
            addCriterion("contract_period between", value1, value2, "contractPeriod");
            return (Criteria) this;
        }

        public Criteria andContractPeriodNotBetween(Date value1, Date value2) {
            addCriterion("contract_period not between", value1, value2, "contractPeriod");
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

        public Criteria andMasterIdIsNull() {
            addCriterion("master_id is null");
            return (Criteria) this;
        }

        public Criteria andMasterIdIsNotNull() {
            addCriterion("master_id is not null");
            return (Criteria) this;
        }

        public Criteria andMasterIdEqualTo(Integer value) {
            addCriterion("master_id =", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotEqualTo(Integer value) {
            addCriterion("master_id <>", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdGreaterThan(Integer value) {
            addCriterion("master_id >", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("master_id >=", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdLessThan(Integer value) {
            addCriterion("master_id <", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdLessThanOrEqualTo(Integer value) {
            addCriterion("master_id <=", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdIn(List<Integer> values) {
            addCriterion("master_id in", values, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotIn(List<Integer> values) {
            addCriterion("master_id not in", values, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdBetween(Integer value1, Integer value2) {
            addCriterion("master_id between", value1, value2, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotBetween(Integer value1, Integer value2) {
            addCriterion("master_id not between", value1, value2, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterTypeIsNull() {
            addCriterion("master_type is null");
            return (Criteria) this;
        }

        public Criteria andMasterTypeIsNotNull() {
            addCriterion("master_type is not null");
            return (Criteria) this;
        }

        public Criteria andMasterTypeEqualTo(String value) {
            addCriterion("master_type =", value, "masterType");
            return (Criteria) this;
        }

        public Criteria andMasterTypeNotEqualTo(String value) {
            addCriterion("master_type <>", value, "masterType");
            return (Criteria) this;
        }

        public Criteria andMasterTypeGreaterThan(String value) {
            addCriterion("master_type >", value, "masterType");
            return (Criteria) this;
        }

        public Criteria andMasterTypeGreaterThanOrEqualTo(String value) {
            addCriterion("master_type >=", value, "masterType");
            return (Criteria) this;
        }

        public Criteria andMasterTypeLessThan(String value) {
            addCriterion("master_type <", value, "masterType");
            return (Criteria) this;
        }

        public Criteria andMasterTypeLessThanOrEqualTo(String value) {
            addCriterion("master_type <=", value, "masterType");
            return (Criteria) this;
        }

        public Criteria andMasterTypeLike(String value) {
            addCriterion("master_type like", value, "masterType");
            return (Criteria) this;
        }

        public Criteria andMasterTypeNotLike(String value) {
            addCriterion("master_type not like", value, "masterType");
            return (Criteria) this;
        }

        public Criteria andMasterTypeIn(List<String> values) {
            addCriterion("master_type in", values, "masterType");
            return (Criteria) this;
        }

        public Criteria andMasterTypeNotIn(List<String> values) {
            addCriterion("master_type not in", values, "masterType");
            return (Criteria) this;
        }

        public Criteria andMasterTypeBetween(String value1, String value2) {
            addCriterion("master_type between", value1, value2, "masterType");
            return (Criteria) this;
        }

        public Criteria andMasterTypeNotBetween(String value1, String value2) {
            addCriterion("master_type not between", value1, value2, "masterType");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * tb_declare_building_construction_permit
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