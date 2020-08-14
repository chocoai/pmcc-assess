package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeclarePreSalePermitExample {
    /**
     * tb_declare_pre_sale_permit
     */
    protected String orderByClause;

    /**
     * tb_declare_pre_sale_permit
     */
    protected boolean distinct;

    /**
     * tb_declare_pre_sale_permit
     */
    protected List<Criteria> oredCriteria;

    public DeclarePreSalePermitExample() {
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
     * tb_declare_pre_sale_permit
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

        public Criteria andSalesUnitIsNull() {
            addCriterion("sales_unit is null");
            return (Criteria) this;
        }

        public Criteria andSalesUnitIsNotNull() {
            addCriterion("sales_unit is not null");
            return (Criteria) this;
        }

        public Criteria andSalesUnitEqualTo(String value) {
            addCriterion("sales_unit =", value, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitNotEqualTo(String value) {
            addCriterion("sales_unit <>", value, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitGreaterThan(String value) {
            addCriterion("sales_unit >", value, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitGreaterThanOrEqualTo(String value) {
            addCriterion("sales_unit >=", value, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitLessThan(String value) {
            addCriterion("sales_unit <", value, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitLessThanOrEqualTo(String value) {
            addCriterion("sales_unit <=", value, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitLike(String value) {
            addCriterion("sales_unit like", value, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitNotLike(String value) {
            addCriterion("sales_unit not like", value, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitIn(List<String> values) {
            addCriterion("sales_unit in", values, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitNotIn(List<String> values) {
            addCriterion("sales_unit not in", values, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitBetween(String value1, String value2) {
            addCriterion("sales_unit between", value1, value2, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andSalesUnitNotBetween(String value1, String value2) {
            addCriterion("sales_unit not between", value1, value2, "salesUnit");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeIsNull() {
            addCriterion("legal_representative is null");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeIsNotNull() {
            addCriterion("legal_representative is not null");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeEqualTo(String value) {
            addCriterion("legal_representative =", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotEqualTo(String value) {
            addCriterion("legal_representative <>", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeGreaterThan(String value) {
            addCriterion("legal_representative >", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeGreaterThanOrEqualTo(String value) {
            addCriterion("legal_representative >=", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeLessThan(String value) {
            addCriterion("legal_representative <", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeLessThanOrEqualTo(String value) {
            addCriterion("legal_representative <=", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeLike(String value) {
            addCriterion("legal_representative like", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotLike(String value) {
            addCriterion("legal_representative not like", value, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeIn(List<String> values) {
            addCriterion("legal_representative in", values, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotIn(List<String> values) {
            addCriterion("legal_representative not in", values, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeBetween(String value1, String value2) {
            addCriterion("legal_representative between", value1, value2, "legalRepresentative");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeNotBetween(String value1, String value2) {
            addCriterion("legal_representative not between", value1, value2, "legalRepresentative");
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

        public Criteria andPreSaleAreaIsNull() {
            addCriterion("pre_sale_area is null");
            return (Criteria) this;
        }

        public Criteria andPreSaleAreaIsNotNull() {
            addCriterion("pre_sale_area is not null");
            return (Criteria) this;
        }

        public Criteria andPreSaleAreaEqualTo(BigDecimal value) {
            addCriterion("pre_sale_area =", value, "preSaleArea");
            return (Criteria) this;
        }

        public Criteria andPreSaleAreaNotEqualTo(BigDecimal value) {
            addCriterion("pre_sale_area <>", value, "preSaleArea");
            return (Criteria) this;
        }

        public Criteria andPreSaleAreaGreaterThan(BigDecimal value) {
            addCriterion("pre_sale_area >", value, "preSaleArea");
            return (Criteria) this;
        }

        public Criteria andPreSaleAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pre_sale_area >=", value, "preSaleArea");
            return (Criteria) this;
        }

        public Criteria andPreSaleAreaLessThan(BigDecimal value) {
            addCriterion("pre_sale_area <", value, "preSaleArea");
            return (Criteria) this;
        }

        public Criteria andPreSaleAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pre_sale_area <=", value, "preSaleArea");
            return (Criteria) this;
        }

        public Criteria andPreSaleAreaIn(List<BigDecimal> values) {
            addCriterion("pre_sale_area in", values, "preSaleArea");
            return (Criteria) this;
        }

        public Criteria andPreSaleAreaNotIn(List<BigDecimal> values) {
            addCriterion("pre_sale_area not in", values, "preSaleArea");
            return (Criteria) this;
        }

        public Criteria andPreSaleAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pre_sale_area between", value1, value2, "preSaleArea");
            return (Criteria) this;
        }

        public Criteria andPreSaleAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pre_sale_area not between", value1, value2, "preSaleArea");
            return (Criteria) this;
        }

        public Criteria andPreSaleScopeIsNull() {
            addCriterion("pre_sale_scope is null");
            return (Criteria) this;
        }

        public Criteria andPreSaleScopeIsNotNull() {
            addCriterion("pre_sale_scope is not null");
            return (Criteria) this;
        }

        public Criteria andPreSaleScopeEqualTo(String value) {
            addCriterion("pre_sale_scope =", value, "preSaleScope");
            return (Criteria) this;
        }

        public Criteria andPreSaleScopeNotEqualTo(String value) {
            addCriterion("pre_sale_scope <>", value, "preSaleScope");
            return (Criteria) this;
        }

        public Criteria andPreSaleScopeGreaterThan(String value) {
            addCriterion("pre_sale_scope >", value, "preSaleScope");
            return (Criteria) this;
        }

        public Criteria andPreSaleScopeGreaterThanOrEqualTo(String value) {
            addCriterion("pre_sale_scope >=", value, "preSaleScope");
            return (Criteria) this;
        }

        public Criteria andPreSaleScopeLessThan(String value) {
            addCriterion("pre_sale_scope <", value, "preSaleScope");
            return (Criteria) this;
        }

        public Criteria andPreSaleScopeLessThanOrEqualTo(String value) {
            addCriterion("pre_sale_scope <=", value, "preSaleScope");
            return (Criteria) this;
        }

        public Criteria andPreSaleScopeLike(String value) {
            addCriterion("pre_sale_scope like", value, "preSaleScope");
            return (Criteria) this;
        }

        public Criteria andPreSaleScopeNotLike(String value) {
            addCriterion("pre_sale_scope not like", value, "preSaleScope");
            return (Criteria) this;
        }

        public Criteria andPreSaleScopeIn(List<String> values) {
            addCriterion("pre_sale_scope in", values, "preSaleScope");
            return (Criteria) this;
        }

        public Criteria andPreSaleScopeNotIn(List<String> values) {
            addCriterion("pre_sale_scope not in", values, "preSaleScope");
            return (Criteria) this;
        }

        public Criteria andPreSaleScopeBetween(String value1, String value2) {
            addCriterion("pre_sale_scope between", value1, value2, "preSaleScope");
            return (Criteria) this;
        }

        public Criteria andPreSaleScopeNotBetween(String value1, String value2) {
            addCriterion("pre_sale_scope not between", value1, value2, "preSaleScope");
            return (Criteria) this;
        }

        public Criteria andHousingUseIsNull() {
            addCriterion("housing_use is null");
            return (Criteria) this;
        }

        public Criteria andHousingUseIsNotNull() {
            addCriterion("housing_use is not null");
            return (Criteria) this;
        }

        public Criteria andHousingUseEqualTo(String value) {
            addCriterion("housing_use =", value, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseNotEqualTo(String value) {
            addCriterion("housing_use <>", value, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseGreaterThan(String value) {
            addCriterion("housing_use >", value, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseGreaterThanOrEqualTo(String value) {
            addCriterion("housing_use >=", value, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseLessThan(String value) {
            addCriterion("housing_use <", value, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseLessThanOrEqualTo(String value) {
            addCriterion("housing_use <=", value, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseLike(String value) {
            addCriterion("housing_use like", value, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseNotLike(String value) {
            addCriterion("housing_use not like", value, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseIn(List<String> values) {
            addCriterion("housing_use in", values, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseNotIn(List<String> values) {
            addCriterion("housing_use not in", values, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseBetween(String value1, String value2) {
            addCriterion("housing_use between", value1, value2, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseNotBetween(String value1, String value2) {
            addCriterion("housing_use not between", value1, value2, "housingUse");
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

        public Criteria andPreSaleSupervisionInformationIsNull() {
            addCriterion("pre_sale_supervision_information is null");
            return (Criteria) this;
        }

        public Criteria andPreSaleSupervisionInformationIsNotNull() {
            addCriterion("pre_sale_supervision_information is not null");
            return (Criteria) this;
        }

        public Criteria andPreSaleSupervisionInformationEqualTo(String value) {
            addCriterion("pre_sale_supervision_information =", value, "preSaleSupervisionInformation");
            return (Criteria) this;
        }

        public Criteria andPreSaleSupervisionInformationNotEqualTo(String value) {
            addCriterion("pre_sale_supervision_information <>", value, "preSaleSupervisionInformation");
            return (Criteria) this;
        }

        public Criteria andPreSaleSupervisionInformationGreaterThan(String value) {
            addCriterion("pre_sale_supervision_information >", value, "preSaleSupervisionInformation");
            return (Criteria) this;
        }

        public Criteria andPreSaleSupervisionInformationGreaterThanOrEqualTo(String value) {
            addCriterion("pre_sale_supervision_information >=", value, "preSaleSupervisionInformation");
            return (Criteria) this;
        }

        public Criteria andPreSaleSupervisionInformationLessThan(String value) {
            addCriterion("pre_sale_supervision_information <", value, "preSaleSupervisionInformation");
            return (Criteria) this;
        }

        public Criteria andPreSaleSupervisionInformationLessThanOrEqualTo(String value) {
            addCriterion("pre_sale_supervision_information <=", value, "preSaleSupervisionInformation");
            return (Criteria) this;
        }

        public Criteria andPreSaleSupervisionInformationLike(String value) {
            addCriterion("pre_sale_supervision_information like", value, "preSaleSupervisionInformation");
            return (Criteria) this;
        }

        public Criteria andPreSaleSupervisionInformationNotLike(String value) {
            addCriterion("pre_sale_supervision_information not like", value, "preSaleSupervisionInformation");
            return (Criteria) this;
        }

        public Criteria andPreSaleSupervisionInformationIn(List<String> values) {
            addCriterion("pre_sale_supervision_information in", values, "preSaleSupervisionInformation");
            return (Criteria) this;
        }

        public Criteria andPreSaleSupervisionInformationNotIn(List<String> values) {
            addCriterion("pre_sale_supervision_information not in", values, "preSaleSupervisionInformation");
            return (Criteria) this;
        }

        public Criteria andPreSaleSupervisionInformationBetween(String value1, String value2) {
            addCriterion("pre_sale_supervision_information between", value1, value2, "preSaleSupervisionInformation");
            return (Criteria) this;
        }

        public Criteria andPreSaleSupervisionInformationNotBetween(String value1, String value2) {
            addCriterion("pre_sale_supervision_information not between", value1, value2, "preSaleSupervisionInformation");
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

        public Criteria andMortgageSituationIsNull() {
            addCriterion("mortgage_situation is null");
            return (Criteria) this;
        }

        public Criteria andMortgageSituationIsNotNull() {
            addCriterion("mortgage_situation is not null");
            return (Criteria) this;
        }

        public Criteria andMortgageSituationEqualTo(String value) {
            addCriterion("mortgage_situation =", value, "mortgageSituation");
            return (Criteria) this;
        }

        public Criteria andMortgageSituationNotEqualTo(String value) {
            addCriterion("mortgage_situation <>", value, "mortgageSituation");
            return (Criteria) this;
        }

        public Criteria andMortgageSituationGreaterThan(String value) {
            addCriterion("mortgage_situation >", value, "mortgageSituation");
            return (Criteria) this;
        }

        public Criteria andMortgageSituationGreaterThanOrEqualTo(String value) {
            addCriterion("mortgage_situation >=", value, "mortgageSituation");
            return (Criteria) this;
        }

        public Criteria andMortgageSituationLessThan(String value) {
            addCriterion("mortgage_situation <", value, "mortgageSituation");
            return (Criteria) this;
        }

        public Criteria andMortgageSituationLessThanOrEqualTo(String value) {
            addCriterion("mortgage_situation <=", value, "mortgageSituation");
            return (Criteria) this;
        }

        public Criteria andMortgageSituationLike(String value) {
            addCriterion("mortgage_situation like", value, "mortgageSituation");
            return (Criteria) this;
        }

        public Criteria andMortgageSituationNotLike(String value) {
            addCriterion("mortgage_situation not like", value, "mortgageSituation");
            return (Criteria) this;
        }

        public Criteria andMortgageSituationIn(List<String> values) {
            addCriterion("mortgage_situation in", values, "mortgageSituation");
            return (Criteria) this;
        }

        public Criteria andMortgageSituationNotIn(List<String> values) {
            addCriterion("mortgage_situation not in", values, "mortgageSituation");
            return (Criteria) this;
        }

        public Criteria andMortgageSituationBetween(String value1, String value2) {
            addCriterion("mortgage_situation between", value1, value2, "mortgageSituation");
            return (Criteria) this;
        }

        public Criteria andMortgageSituationNotBetween(String value1, String value2) {
            addCriterion("mortgage_situation not between", value1, value2, "mortgageSituation");
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
     * tb_declare_pre_sale_permit
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