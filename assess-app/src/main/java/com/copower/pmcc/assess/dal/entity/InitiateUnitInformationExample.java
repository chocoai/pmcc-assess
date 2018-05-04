package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InitiateUnitInformationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InitiateUnitInformationExample() {
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

        public Criteria andUUnitPropertiesIsNull() {
            addCriterion("u_unit_properties is null");
            return (Criteria) this;
        }

        public Criteria andUUnitPropertiesIsNotNull() {
            addCriterion("u_unit_properties is not null");
            return (Criteria) this;
        }

        public Criteria andUUnitPropertiesEqualTo(String value) {
            addCriterion("u_unit_properties =", value, "uUnitProperties");
            return (Criteria) this;
        }

        public Criteria andUUnitPropertiesNotEqualTo(String value) {
            addCriterion("u_unit_properties <>", value, "uUnitProperties");
            return (Criteria) this;
        }

        public Criteria andUUnitPropertiesGreaterThan(String value) {
            addCriterion("u_unit_properties >", value, "uUnitProperties");
            return (Criteria) this;
        }

        public Criteria andUUnitPropertiesGreaterThanOrEqualTo(String value) {
            addCriterion("u_unit_properties >=", value, "uUnitProperties");
            return (Criteria) this;
        }

        public Criteria andUUnitPropertiesLessThan(String value) {
            addCriterion("u_unit_properties <", value, "uUnitProperties");
            return (Criteria) this;
        }

        public Criteria andUUnitPropertiesLessThanOrEqualTo(String value) {
            addCriterion("u_unit_properties <=", value, "uUnitProperties");
            return (Criteria) this;
        }

        public Criteria andUUnitPropertiesLike(String value) {
            addCriterion("u_unit_properties like", value, "uUnitProperties");
            return (Criteria) this;
        }

        public Criteria andUUnitPropertiesNotLike(String value) {
            addCriterion("u_unit_properties not like", value, "uUnitProperties");
            return (Criteria) this;
        }

        public Criteria andUUnitPropertiesIn(List<String> values) {
            addCriterion("u_unit_properties in", values, "uUnitProperties");
            return (Criteria) this;
        }

        public Criteria andUUnitPropertiesNotIn(List<String> values) {
            addCriterion("u_unit_properties not in", values, "uUnitProperties");
            return (Criteria) this;
        }

        public Criteria andUUnitPropertiesBetween(String value1, String value2) {
            addCriterion("u_unit_properties between", value1, value2, "uUnitProperties");
            return (Criteria) this;
        }

        public Criteria andUUnitPropertiesNotBetween(String value1, String value2) {
            addCriterion("u_unit_properties not between", value1, value2, "uUnitProperties");
            return (Criteria) this;
        }

        public Criteria andUScopeOperationIsNull() {
            addCriterion("u_scope_operation is null");
            return (Criteria) this;
        }

        public Criteria andUScopeOperationIsNotNull() {
            addCriterion("u_scope_operation is not null");
            return (Criteria) this;
        }

        public Criteria andUScopeOperationEqualTo(String value) {
            addCriterion("u_scope_operation =", value, "uScopeOperation");
            return (Criteria) this;
        }

        public Criteria andUScopeOperationNotEqualTo(String value) {
            addCriterion("u_scope_operation <>", value, "uScopeOperation");
            return (Criteria) this;
        }

        public Criteria andUScopeOperationGreaterThan(String value) {
            addCriterion("u_scope_operation >", value, "uScopeOperation");
            return (Criteria) this;
        }

        public Criteria andUScopeOperationGreaterThanOrEqualTo(String value) {
            addCriterion("u_scope_operation >=", value, "uScopeOperation");
            return (Criteria) this;
        }

        public Criteria andUScopeOperationLessThan(String value) {
            addCriterion("u_scope_operation <", value, "uScopeOperation");
            return (Criteria) this;
        }

        public Criteria andUScopeOperationLessThanOrEqualTo(String value) {
            addCriterion("u_scope_operation <=", value, "uScopeOperation");
            return (Criteria) this;
        }

        public Criteria andUScopeOperationLike(String value) {
            addCriterion("u_scope_operation like", value, "uScopeOperation");
            return (Criteria) this;
        }

        public Criteria andUScopeOperationNotLike(String value) {
            addCriterion("u_scope_operation not like", value, "uScopeOperation");
            return (Criteria) this;
        }

        public Criteria andUScopeOperationIn(List<String> values) {
            addCriterion("u_scope_operation in", values, "uScopeOperation");
            return (Criteria) this;
        }

        public Criteria andUScopeOperationNotIn(List<String> values) {
            addCriterion("u_scope_operation not in", values, "uScopeOperation");
            return (Criteria) this;
        }

        public Criteria andUScopeOperationBetween(String value1, String value2) {
            addCriterion("u_scope_operation between", value1, value2, "uScopeOperation");
            return (Criteria) this;
        }

        public Criteria andUScopeOperationNotBetween(String value1, String value2) {
            addCriterion("u_scope_operation not between", value1, value2, "uScopeOperation");
            return (Criteria) this;
        }

        public Criteria andUAddressIsNull() {
            addCriterion("u_address is null");
            return (Criteria) this;
        }

        public Criteria andUAddressIsNotNull() {
            addCriterion("u_address is not null");
            return (Criteria) this;
        }

        public Criteria andUAddressEqualTo(String value) {
            addCriterion("u_address =", value, "uAddress");
            return (Criteria) this;
        }

        public Criteria andUAddressNotEqualTo(String value) {
            addCriterion("u_address <>", value, "uAddress");
            return (Criteria) this;
        }

        public Criteria andUAddressGreaterThan(String value) {
            addCriterion("u_address >", value, "uAddress");
            return (Criteria) this;
        }

        public Criteria andUAddressGreaterThanOrEqualTo(String value) {
            addCriterion("u_address >=", value, "uAddress");
            return (Criteria) this;
        }

        public Criteria andUAddressLessThan(String value) {
            addCriterion("u_address <", value, "uAddress");
            return (Criteria) this;
        }

        public Criteria andUAddressLessThanOrEqualTo(String value) {
            addCriterion("u_address <=", value, "uAddress");
            return (Criteria) this;
        }

        public Criteria andUAddressLike(String value) {
            addCriterion("u_address like", value, "uAddress");
            return (Criteria) this;
        }

        public Criteria andUAddressNotLike(String value) {
            addCriterion("u_address not like", value, "uAddress");
            return (Criteria) this;
        }

        public Criteria andUAddressIn(List<String> values) {
            addCriterion("u_address in", values, "uAddress");
            return (Criteria) this;
        }

        public Criteria andUAddressNotIn(List<String> values) {
            addCriterion("u_address not in", values, "uAddress");
            return (Criteria) this;
        }

        public Criteria andUAddressBetween(String value1, String value2) {
            addCriterion("u_address between", value1, value2, "uAddress");
            return (Criteria) this;
        }

        public Criteria andUAddressNotBetween(String value1, String value2) {
            addCriterion("u_address not between", value1, value2, "uAddress");
            return (Criteria) this;
        }

        public Criteria andUCertificateNumberIsNull() {
            addCriterion("u_certificate_number is null");
            return (Criteria) this;
        }

        public Criteria andUCertificateNumberIsNotNull() {
            addCriterion("u_certificate_number is not null");
            return (Criteria) this;
        }

        public Criteria andUCertificateNumberEqualTo(String value) {
            addCriterion("u_certificate_number =", value, "uCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andUCertificateNumberNotEqualTo(String value) {
            addCriterion("u_certificate_number <>", value, "uCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andUCertificateNumberGreaterThan(String value) {
            addCriterion("u_certificate_number >", value, "uCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andUCertificateNumberGreaterThanOrEqualTo(String value) {
            addCriterion("u_certificate_number >=", value, "uCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andUCertificateNumberLessThan(String value) {
            addCriterion("u_certificate_number <", value, "uCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andUCertificateNumberLessThanOrEqualTo(String value) {
            addCriterion("u_certificate_number <=", value, "uCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andUCertificateNumberLike(String value) {
            addCriterion("u_certificate_number like", value, "uCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andUCertificateNumberNotLike(String value) {
            addCriterion("u_certificate_number not like", value, "uCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andUCertificateNumberIn(List<String> values) {
            addCriterion("u_certificate_number in", values, "uCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andUCertificateNumberNotIn(List<String> values) {
            addCriterion("u_certificate_number not in", values, "uCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andUCertificateNumberBetween(String value1, String value2) {
            addCriterion("u_certificate_number between", value1, value2, "uCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andUCertificateNumberNotBetween(String value1, String value2) {
            addCriterion("u_certificate_number not between", value1, value2, "uCertificateNumber");
            return (Criteria) this;
        }

        public Criteria andULegalRepresentativeIsNull() {
            addCriterion("u_legal_representative is null");
            return (Criteria) this;
        }

        public Criteria andULegalRepresentativeIsNotNull() {
            addCriterion("u_legal_representative is not null");
            return (Criteria) this;
        }

        public Criteria andULegalRepresentativeEqualTo(String value) {
            addCriterion("u_legal_representative =", value, "uLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andULegalRepresentativeNotEqualTo(String value) {
            addCriterion("u_legal_representative <>", value, "uLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andULegalRepresentativeGreaterThan(String value) {
            addCriterion("u_legal_representative >", value, "uLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andULegalRepresentativeGreaterThanOrEqualTo(String value) {
            addCriterion("u_legal_representative >=", value, "uLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andULegalRepresentativeLessThan(String value) {
            addCriterion("u_legal_representative <", value, "uLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andULegalRepresentativeLessThanOrEqualTo(String value) {
            addCriterion("u_legal_representative <=", value, "uLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andULegalRepresentativeLike(String value) {
            addCriterion("u_legal_representative like", value, "uLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andULegalRepresentativeNotLike(String value) {
            addCriterion("u_legal_representative not like", value, "uLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andULegalRepresentativeIn(List<String> values) {
            addCriterion("u_legal_representative in", values, "uLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andULegalRepresentativeNotIn(List<String> values) {
            addCriterion("u_legal_representative not in", values, "uLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andULegalRepresentativeBetween(String value1, String value2) {
            addCriterion("u_legal_representative between", value1, value2, "uLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andULegalRepresentativeNotBetween(String value1, String value2) {
            addCriterion("u_legal_representative not between", value1, value2, "uLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andUUseUnitIsNull() {
            addCriterion("u_use_unit is null");
            return (Criteria) this;
        }

        public Criteria andUUseUnitIsNotNull() {
            addCriterion("u_use_unit is not null");
            return (Criteria) this;
        }

        public Criteria andUUseUnitEqualTo(String value) {
            addCriterion("u_use_unit =", value, "uUseUnit");
            return (Criteria) this;
        }

        public Criteria andUUseUnitNotEqualTo(String value) {
            addCriterion("u_use_unit <>", value, "uUseUnit");
            return (Criteria) this;
        }

        public Criteria andUUseUnitGreaterThan(String value) {
            addCriterion("u_use_unit >", value, "uUseUnit");
            return (Criteria) this;
        }

        public Criteria andUUseUnitGreaterThanOrEqualTo(String value) {
            addCriterion("u_use_unit >=", value, "uUseUnit");
            return (Criteria) this;
        }

        public Criteria andUUseUnitLessThan(String value) {
            addCriterion("u_use_unit <", value, "uUseUnit");
            return (Criteria) this;
        }

        public Criteria andUUseUnitLessThanOrEqualTo(String value) {
            addCriterion("u_use_unit <=", value, "uUseUnit");
            return (Criteria) this;
        }

        public Criteria andUUseUnitLike(String value) {
            addCriterion("u_use_unit like", value, "uUseUnit");
            return (Criteria) this;
        }

        public Criteria andUUseUnitNotLike(String value) {
            addCriterion("u_use_unit not like", value, "uUseUnit");
            return (Criteria) this;
        }

        public Criteria andUUseUnitIn(List<String> values) {
            addCriterion("u_use_unit in", values, "uUseUnit");
            return (Criteria) this;
        }

        public Criteria andUUseUnitNotIn(List<String> values) {
            addCriterion("u_use_unit not in", values, "uUseUnit");
            return (Criteria) this;
        }

        public Criteria andUUseUnitBetween(String value1, String value2) {
            addCriterion("u_use_unit between", value1, value2, "uUseUnit");
            return (Criteria) this;
        }

        public Criteria andUUseUnitNotBetween(String value1, String value2) {
            addCriterion("u_use_unit not between", value1, value2, "uUseUnit");
            return (Criteria) this;
        }

        public Criteria andSpareFieldIsNull() {
            addCriterion("spare_field is null");
            return (Criteria) this;
        }

        public Criteria andSpareFieldIsNotNull() {
            addCriterion("spare_field is not null");
            return (Criteria) this;
        }

        public Criteria andSpareFieldEqualTo(String value) {
            addCriterion("spare_field =", value, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldNotEqualTo(String value) {
            addCriterion("spare_field <>", value, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldGreaterThan(String value) {
            addCriterion("spare_field >", value, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldGreaterThanOrEqualTo(String value) {
            addCriterion("spare_field >=", value, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldLessThan(String value) {
            addCriterion("spare_field <", value, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldLessThanOrEqualTo(String value) {
            addCriterion("spare_field <=", value, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldLike(String value) {
            addCriterion("spare_field like", value, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldNotLike(String value) {
            addCriterion("spare_field not like", value, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldIn(List<String> values) {
            addCriterion("spare_field in", values, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldNotIn(List<String> values) {
            addCriterion("spare_field not in", values, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldBetween(String value1, String value2) {
            addCriterion("spare_field between", value1, value2, "spareField");
            return (Criteria) this;
        }

        public Criteria andSpareFieldNotBetween(String value1, String value2) {
            addCriterion("spare_field not between", value1, value2, "spareField");
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