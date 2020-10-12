package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InitiateConsignorExample {
    /**
     * tb_initiate_consignor
     */
    protected String orderByClause;

    /**
     * tb_initiate_consignor
     */
    protected boolean distinct;

    /**
     * tb_initiate_consignor
     */
    protected List<Criteria> oredCriteria;

    public InitiateConsignorExample() {
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
     * tb_initiate_consignor
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

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Integer value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Integer value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Integer value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Integer value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Integer> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Integer> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andCsTypeIsNull() {
            addCriterion("cs_type is null");
            return (Criteria) this;
        }

        public Criteria andCsTypeIsNotNull() {
            addCriterion("cs_type is not null");
            return (Criteria) this;
        }

        public Criteria andCsTypeEqualTo(Integer value) {
            addCriterion("cs_type =", value, "csType");
            return (Criteria) this;
        }

        public Criteria andCsTypeNotEqualTo(Integer value) {
            addCriterion("cs_type <>", value, "csType");
            return (Criteria) this;
        }

        public Criteria andCsTypeGreaterThan(Integer value) {
            addCriterion("cs_type >", value, "csType");
            return (Criteria) this;
        }

        public Criteria andCsTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("cs_type >=", value, "csType");
            return (Criteria) this;
        }

        public Criteria andCsTypeLessThan(Integer value) {
            addCriterion("cs_type <", value, "csType");
            return (Criteria) this;
        }

        public Criteria andCsTypeLessThanOrEqualTo(Integer value) {
            addCriterion("cs_type <=", value, "csType");
            return (Criteria) this;
        }

        public Criteria andCsTypeIn(List<Integer> values) {
            addCriterion("cs_type in", values, "csType");
            return (Criteria) this;
        }

        public Criteria andCsTypeNotIn(List<Integer> values) {
            addCriterion("cs_type not in", values, "csType");
            return (Criteria) this;
        }

        public Criteria andCsTypeBetween(Integer value1, Integer value2) {
            addCriterion("cs_type between", value1, value2, "csType");
            return (Criteria) this;
        }

        public Criteria andCsTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("cs_type not between", value1, value2, "csType");
            return (Criteria) this;
        }

        public Criteria andCsEntrustmentUnitIsNull() {
            addCriterion("cs_entrustment_unit is null");
            return (Criteria) this;
        }

        public Criteria andCsEntrustmentUnitIsNotNull() {
            addCriterion("cs_entrustment_unit is not null");
            return (Criteria) this;
        }

        public Criteria andCsEntrustmentUnitEqualTo(String value) {
            addCriterion("cs_entrustment_unit =", value, "csEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andCsEntrustmentUnitNotEqualTo(String value) {
            addCriterion("cs_entrustment_unit <>", value, "csEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andCsEntrustmentUnitGreaterThan(String value) {
            addCriterion("cs_entrustment_unit >", value, "csEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andCsEntrustmentUnitGreaterThanOrEqualTo(String value) {
            addCriterion("cs_entrustment_unit >=", value, "csEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andCsEntrustmentUnitLessThan(String value) {
            addCriterion("cs_entrustment_unit <", value, "csEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andCsEntrustmentUnitLessThanOrEqualTo(String value) {
            addCriterion("cs_entrustment_unit <=", value, "csEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andCsEntrustmentUnitLike(String value) {
            addCriterion("cs_entrustment_unit like", value, "csEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andCsEntrustmentUnitNotLike(String value) {
            addCriterion("cs_entrustment_unit not like", value, "csEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andCsEntrustmentUnitIn(List<String> values) {
            addCriterion("cs_entrustment_unit in", values, "csEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andCsEntrustmentUnitNotIn(List<String> values) {
            addCriterion("cs_entrustment_unit not in", values, "csEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andCsEntrustmentUnitBetween(String value1, String value2) {
            addCriterion("cs_entrustment_unit between", value1, value2, "csEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andCsEntrustmentUnitNotBetween(String value1, String value2) {
            addCriterion("cs_entrustment_unit not between", value1, value2, "csEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andCsLegalRepresentativeIsNull() {
            addCriterion("cs_legal_representative is null");
            return (Criteria) this;
        }

        public Criteria andCsLegalRepresentativeIsNotNull() {
            addCriterion("cs_legal_representative is not null");
            return (Criteria) this;
        }

        public Criteria andCsLegalRepresentativeEqualTo(String value) {
            addCriterion("cs_legal_representative =", value, "csLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andCsLegalRepresentativeNotEqualTo(String value) {
            addCriterion("cs_legal_representative <>", value, "csLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andCsLegalRepresentativeGreaterThan(String value) {
            addCriterion("cs_legal_representative >", value, "csLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andCsLegalRepresentativeGreaterThanOrEqualTo(String value) {
            addCriterion("cs_legal_representative >=", value, "csLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andCsLegalRepresentativeLessThan(String value) {
            addCriterion("cs_legal_representative <", value, "csLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andCsLegalRepresentativeLessThanOrEqualTo(String value) {
            addCriterion("cs_legal_representative <=", value, "csLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andCsLegalRepresentativeLike(String value) {
            addCriterion("cs_legal_representative like", value, "csLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andCsLegalRepresentativeNotLike(String value) {
            addCriterion("cs_legal_representative not like", value, "csLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andCsLegalRepresentativeIn(List<String> values) {
            addCriterion("cs_legal_representative in", values, "csLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andCsLegalRepresentativeNotIn(List<String> values) {
            addCriterion("cs_legal_representative not in", values, "csLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andCsLegalRepresentativeBetween(String value1, String value2) {
            addCriterion("cs_legal_representative between", value1, value2, "csLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andCsLegalRepresentativeNotBetween(String value1, String value2) {
            addCriterion("cs_legal_representative not between", value1, value2, "csLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andCsSociologyCodeIsNull() {
            addCriterion("cs_sociology_code is null");
            return (Criteria) this;
        }

        public Criteria andCsSociologyCodeIsNotNull() {
            addCriterion("cs_sociology_code is not null");
            return (Criteria) this;
        }

        public Criteria andCsSociologyCodeEqualTo(String value) {
            addCriterion("cs_sociology_code =", value, "csSociologyCode");
            return (Criteria) this;
        }

        public Criteria andCsSociologyCodeNotEqualTo(String value) {
            addCriterion("cs_sociology_code <>", value, "csSociologyCode");
            return (Criteria) this;
        }

        public Criteria andCsSociologyCodeGreaterThan(String value) {
            addCriterion("cs_sociology_code >", value, "csSociologyCode");
            return (Criteria) this;
        }

        public Criteria andCsSociologyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("cs_sociology_code >=", value, "csSociologyCode");
            return (Criteria) this;
        }

        public Criteria andCsSociologyCodeLessThan(String value) {
            addCriterion("cs_sociology_code <", value, "csSociologyCode");
            return (Criteria) this;
        }

        public Criteria andCsSociologyCodeLessThanOrEqualTo(String value) {
            addCriterion("cs_sociology_code <=", value, "csSociologyCode");
            return (Criteria) this;
        }

        public Criteria andCsSociologyCodeLike(String value) {
            addCriterion("cs_sociology_code like", value, "csSociologyCode");
            return (Criteria) this;
        }

        public Criteria andCsSociologyCodeNotLike(String value) {
            addCriterion("cs_sociology_code not like", value, "csSociologyCode");
            return (Criteria) this;
        }

        public Criteria andCsSociologyCodeIn(List<String> values) {
            addCriterion("cs_sociology_code in", values, "csSociologyCode");
            return (Criteria) this;
        }

        public Criteria andCsSociologyCodeNotIn(List<String> values) {
            addCriterion("cs_sociology_code not in", values, "csSociologyCode");
            return (Criteria) this;
        }

        public Criteria andCsSociologyCodeBetween(String value1, String value2) {
            addCriterion("cs_sociology_code between", value1, value2, "csSociologyCode");
            return (Criteria) this;
        }

        public Criteria andCsSociologyCodeNotBetween(String value1, String value2) {
            addCriterion("cs_sociology_code not between", value1, value2, "csSociologyCode");
            return (Criteria) this;
        }

        public Criteria andCsAddressIsNull() {
            addCriterion("cs_address is null");
            return (Criteria) this;
        }

        public Criteria andCsAddressIsNotNull() {
            addCriterion("cs_address is not null");
            return (Criteria) this;
        }

        public Criteria andCsAddressEqualTo(String value) {
            addCriterion("cs_address =", value, "csAddress");
            return (Criteria) this;
        }

        public Criteria andCsAddressNotEqualTo(String value) {
            addCriterion("cs_address <>", value, "csAddress");
            return (Criteria) this;
        }

        public Criteria andCsAddressGreaterThan(String value) {
            addCriterion("cs_address >", value, "csAddress");
            return (Criteria) this;
        }

        public Criteria andCsAddressGreaterThanOrEqualTo(String value) {
            addCriterion("cs_address >=", value, "csAddress");
            return (Criteria) this;
        }

        public Criteria andCsAddressLessThan(String value) {
            addCriterion("cs_address <", value, "csAddress");
            return (Criteria) this;
        }

        public Criteria andCsAddressLessThanOrEqualTo(String value) {
            addCriterion("cs_address <=", value, "csAddress");
            return (Criteria) this;
        }

        public Criteria andCsAddressLike(String value) {
            addCriterion("cs_address like", value, "csAddress");
            return (Criteria) this;
        }

        public Criteria andCsAddressNotLike(String value) {
            addCriterion("cs_address not like", value, "csAddress");
            return (Criteria) this;
        }

        public Criteria andCsAddressIn(List<String> values) {
            addCriterion("cs_address in", values, "csAddress");
            return (Criteria) this;
        }

        public Criteria andCsAddressNotIn(List<String> values) {
            addCriterion("cs_address not in", values, "csAddress");
            return (Criteria) this;
        }

        public Criteria andCsAddressBetween(String value1, String value2) {
            addCriterion("cs_address between", value1, value2, "csAddress");
            return (Criteria) this;
        }

        public Criteria andCsAddressNotBetween(String value1, String value2) {
            addCriterion("cs_address not between", value1, value2, "csAddress");
            return (Criteria) this;
        }

        public Criteria andCsScopeOperationIsNull() {
            addCriterion("cs_scope_operation is null");
            return (Criteria) this;
        }

        public Criteria andCsScopeOperationIsNotNull() {
            addCriterion("cs_scope_operation is not null");
            return (Criteria) this;
        }

        public Criteria andCsScopeOperationEqualTo(String value) {
            addCriterion("cs_scope_operation =", value, "csScopeOperation");
            return (Criteria) this;
        }

        public Criteria andCsScopeOperationNotEqualTo(String value) {
            addCriterion("cs_scope_operation <>", value, "csScopeOperation");
            return (Criteria) this;
        }

        public Criteria andCsScopeOperationGreaterThan(String value) {
            addCriterion("cs_scope_operation >", value, "csScopeOperation");
            return (Criteria) this;
        }

        public Criteria andCsScopeOperationGreaterThanOrEqualTo(String value) {
            addCriterion("cs_scope_operation >=", value, "csScopeOperation");
            return (Criteria) this;
        }

        public Criteria andCsScopeOperationLessThan(String value) {
            addCriterion("cs_scope_operation <", value, "csScopeOperation");
            return (Criteria) this;
        }

        public Criteria andCsScopeOperationLessThanOrEqualTo(String value) {
            addCriterion("cs_scope_operation <=", value, "csScopeOperation");
            return (Criteria) this;
        }

        public Criteria andCsScopeOperationLike(String value) {
            addCriterion("cs_scope_operation like", value, "csScopeOperation");
            return (Criteria) this;
        }

        public Criteria andCsScopeOperationNotLike(String value) {
            addCriterion("cs_scope_operation not like", value, "csScopeOperation");
            return (Criteria) this;
        }

        public Criteria andCsScopeOperationIn(List<String> values) {
            addCriterion("cs_scope_operation in", values, "csScopeOperation");
            return (Criteria) this;
        }

        public Criteria andCsScopeOperationNotIn(List<String> values) {
            addCriterion("cs_scope_operation not in", values, "csScopeOperation");
            return (Criteria) this;
        }

        public Criteria andCsScopeOperationBetween(String value1, String value2) {
            addCriterion("cs_scope_operation between", value1, value2, "csScopeOperation");
            return (Criteria) this;
        }

        public Criteria andCsScopeOperationNotBetween(String value1, String value2) {
            addCriterion("cs_scope_operation not between", value1, value2, "csScopeOperation");
            return (Criteria) this;
        }

        public Criteria andCsUnitPropertiesIsNull() {
            addCriterion("cs_unit_properties is null");
            return (Criteria) this;
        }

        public Criteria andCsUnitPropertiesIsNotNull() {
            addCriterion("cs_unit_properties is not null");
            return (Criteria) this;
        }

        public Criteria andCsUnitPropertiesEqualTo(String value) {
            addCriterion("cs_unit_properties =", value, "csUnitProperties");
            return (Criteria) this;
        }

        public Criteria andCsUnitPropertiesNotEqualTo(String value) {
            addCriterion("cs_unit_properties <>", value, "csUnitProperties");
            return (Criteria) this;
        }

        public Criteria andCsUnitPropertiesGreaterThan(String value) {
            addCriterion("cs_unit_properties >", value, "csUnitProperties");
            return (Criteria) this;
        }

        public Criteria andCsUnitPropertiesGreaterThanOrEqualTo(String value) {
            addCriterion("cs_unit_properties >=", value, "csUnitProperties");
            return (Criteria) this;
        }

        public Criteria andCsUnitPropertiesLessThan(String value) {
            addCriterion("cs_unit_properties <", value, "csUnitProperties");
            return (Criteria) this;
        }

        public Criteria andCsUnitPropertiesLessThanOrEqualTo(String value) {
            addCriterion("cs_unit_properties <=", value, "csUnitProperties");
            return (Criteria) this;
        }

        public Criteria andCsUnitPropertiesLike(String value) {
            addCriterion("cs_unit_properties like", value, "csUnitProperties");
            return (Criteria) this;
        }

        public Criteria andCsUnitPropertiesNotLike(String value) {
            addCriterion("cs_unit_properties not like", value, "csUnitProperties");
            return (Criteria) this;
        }

        public Criteria andCsUnitPropertiesIn(List<String> values) {
            addCriterion("cs_unit_properties in", values, "csUnitProperties");
            return (Criteria) this;
        }

        public Criteria andCsUnitPropertiesNotIn(List<String> values) {
            addCriterion("cs_unit_properties not in", values, "csUnitProperties");
            return (Criteria) this;
        }

        public Criteria andCsUnitPropertiesBetween(String value1, String value2) {
            addCriterion("cs_unit_properties between", value1, value2, "csUnitProperties");
            return (Criteria) this;
        }

        public Criteria andCsUnitPropertiesNotBetween(String value1, String value2) {
            addCriterion("cs_unit_properties not between", value1, value2, "csUnitProperties");
            return (Criteria) this;
        }

        public Criteria andCsNameIsNull() {
            addCriterion("cs_name is null");
            return (Criteria) this;
        }

        public Criteria andCsNameIsNotNull() {
            addCriterion("cs_name is not null");
            return (Criteria) this;
        }

        public Criteria andCsNameEqualTo(String value) {
            addCriterion("cs_name =", value, "csName");
            return (Criteria) this;
        }

        public Criteria andCsNameNotEqualTo(String value) {
            addCriterion("cs_name <>", value, "csName");
            return (Criteria) this;
        }

        public Criteria andCsNameGreaterThan(String value) {
            addCriterion("cs_name >", value, "csName");
            return (Criteria) this;
        }

        public Criteria andCsNameGreaterThanOrEqualTo(String value) {
            addCriterion("cs_name >=", value, "csName");
            return (Criteria) this;
        }

        public Criteria andCsNameLessThan(String value) {
            addCriterion("cs_name <", value, "csName");
            return (Criteria) this;
        }

        public Criteria andCsNameLessThanOrEqualTo(String value) {
            addCriterion("cs_name <=", value, "csName");
            return (Criteria) this;
        }

        public Criteria andCsNameLike(String value) {
            addCriterion("cs_name like", value, "csName");
            return (Criteria) this;
        }

        public Criteria andCsNameNotLike(String value) {
            addCriterion("cs_name not like", value, "csName");
            return (Criteria) this;
        }

        public Criteria andCsNameIn(List<String> values) {
            addCriterion("cs_name in", values, "csName");
            return (Criteria) this;
        }

        public Criteria andCsNameNotIn(List<String> values) {
            addCriterion("cs_name not in", values, "csName");
            return (Criteria) this;
        }

        public Criteria andCsNameBetween(String value1, String value2) {
            addCriterion("cs_name between", value1, value2, "csName");
            return (Criteria) this;
        }

        public Criteria andCsNameNotBetween(String value1, String value2) {
            addCriterion("cs_name not between", value1, value2, "csName");
            return (Criteria) this;
        }

        public Criteria andCsSpareFieldIsNull() {
            addCriterion("cs_spare_field is null");
            return (Criteria) this;
        }

        public Criteria andCsSpareFieldIsNotNull() {
            addCriterion("cs_spare_field is not null");
            return (Criteria) this;
        }

        public Criteria andCsSpareFieldEqualTo(String value) {
            addCriterion("cs_spare_field =", value, "csSpareField");
            return (Criteria) this;
        }

        public Criteria andCsSpareFieldNotEqualTo(String value) {
            addCriterion("cs_spare_field <>", value, "csSpareField");
            return (Criteria) this;
        }

        public Criteria andCsSpareFieldGreaterThan(String value) {
            addCriterion("cs_spare_field >", value, "csSpareField");
            return (Criteria) this;
        }

        public Criteria andCsSpareFieldGreaterThanOrEqualTo(String value) {
            addCriterion("cs_spare_field >=", value, "csSpareField");
            return (Criteria) this;
        }

        public Criteria andCsSpareFieldLessThan(String value) {
            addCriterion("cs_spare_field <", value, "csSpareField");
            return (Criteria) this;
        }

        public Criteria andCsSpareFieldLessThanOrEqualTo(String value) {
            addCriterion("cs_spare_field <=", value, "csSpareField");
            return (Criteria) this;
        }

        public Criteria andCsSpareFieldLike(String value) {
            addCriterion("cs_spare_field like", value, "csSpareField");
            return (Criteria) this;
        }

        public Criteria andCsSpareFieldNotLike(String value) {
            addCriterion("cs_spare_field not like", value, "csSpareField");
            return (Criteria) this;
        }

        public Criteria andCsSpareFieldIn(List<String> values) {
            addCriterion("cs_spare_field in", values, "csSpareField");
            return (Criteria) this;
        }

        public Criteria andCsSpareFieldNotIn(List<String> values) {
            addCriterion("cs_spare_field not in", values, "csSpareField");
            return (Criteria) this;
        }

        public Criteria andCsSpareFieldBetween(String value1, String value2) {
            addCriterion("cs_spare_field between", value1, value2, "csSpareField");
            return (Criteria) this;
        }

        public Criteria andCsSpareFieldNotBetween(String value1, String value2) {
            addCriterion("cs_spare_field not between", value1, value2, "csSpareField");
            return (Criteria) this;
        }

        public Criteria andCsIdcardIsNull() {
            addCriterion("cs_idcard is null");
            return (Criteria) this;
        }

        public Criteria andCsIdcardIsNotNull() {
            addCriterion("cs_idcard is not null");
            return (Criteria) this;
        }

        public Criteria andCsIdcardEqualTo(String value) {
            addCriterion("cs_idcard =", value, "csIdcard");
            return (Criteria) this;
        }

        public Criteria andCsIdcardNotEqualTo(String value) {
            addCriterion("cs_idcard <>", value, "csIdcard");
            return (Criteria) this;
        }

        public Criteria andCsIdcardGreaterThan(String value) {
            addCriterion("cs_idcard >", value, "csIdcard");
            return (Criteria) this;
        }

        public Criteria andCsIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("cs_idcard >=", value, "csIdcard");
            return (Criteria) this;
        }

        public Criteria andCsIdcardLessThan(String value) {
            addCriterion("cs_idcard <", value, "csIdcard");
            return (Criteria) this;
        }

        public Criteria andCsIdcardLessThanOrEqualTo(String value) {
            addCriterion("cs_idcard <=", value, "csIdcard");
            return (Criteria) this;
        }

        public Criteria andCsIdcardLike(String value) {
            addCriterion("cs_idcard like", value, "csIdcard");
            return (Criteria) this;
        }

        public Criteria andCsIdcardNotLike(String value) {
            addCriterion("cs_idcard not like", value, "csIdcard");
            return (Criteria) this;
        }

        public Criteria andCsIdcardIn(List<String> values) {
            addCriterion("cs_idcard in", values, "csIdcard");
            return (Criteria) this;
        }

        public Criteria andCsIdcardNotIn(List<String> values) {
            addCriterion("cs_idcard not in", values, "csIdcard");
            return (Criteria) this;
        }

        public Criteria andCsIdcardBetween(String value1, String value2) {
            addCriterion("cs_idcard between", value1, value2, "csIdcard");
            return (Criteria) this;
        }

        public Criteria andCsIdcardNotBetween(String value1, String value2) {
            addCriterion("cs_idcard not between", value1, value2, "csIdcard");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * tb_initiate_consignor
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