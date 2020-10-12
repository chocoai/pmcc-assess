package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InitiatePossessorExample {
    /**
     * tb_initiate_possessor
     */
    protected String orderByClause;

    /**
     * tb_initiate_possessor
     */
    protected boolean distinct;

    /**
     * tb_initiate_possessor
     */
    protected List<Criteria> oredCriteria;

    public InitiatePossessorExample() {
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
     * tb_initiate_possessor
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

        public Criteria andPTypeIsNull() {
            addCriterion("p_type is null");
            return (Criteria) this;
        }

        public Criteria andPTypeIsNotNull() {
            addCriterion("p_type is not null");
            return (Criteria) this;
        }

        public Criteria andPTypeEqualTo(Integer value) {
            addCriterion("p_type =", value, "pType");
            return (Criteria) this;
        }

        public Criteria andPTypeNotEqualTo(Integer value) {
            addCriterion("p_type <>", value, "pType");
            return (Criteria) this;
        }

        public Criteria andPTypeGreaterThan(Integer value) {
            addCriterion("p_type >", value, "pType");
            return (Criteria) this;
        }

        public Criteria andPTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("p_type >=", value, "pType");
            return (Criteria) this;
        }

        public Criteria andPTypeLessThan(Integer value) {
            addCriterion("p_type <", value, "pType");
            return (Criteria) this;
        }

        public Criteria andPTypeLessThanOrEqualTo(Integer value) {
            addCriterion("p_type <=", value, "pType");
            return (Criteria) this;
        }

        public Criteria andPTypeIn(List<Integer> values) {
            addCriterion("p_type in", values, "pType");
            return (Criteria) this;
        }

        public Criteria andPTypeNotIn(List<Integer> values) {
            addCriterion("p_type not in", values, "pType");
            return (Criteria) this;
        }

        public Criteria andPTypeBetween(Integer value1, Integer value2) {
            addCriterion("p_type between", value1, value2, "pType");
            return (Criteria) this;
        }

        public Criteria andPTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("p_type not between", value1, value2, "pType");
            return (Criteria) this;
        }

        public Criteria andPEntrustmentUnitIsNull() {
            addCriterion("p_entrustment_unit is null");
            return (Criteria) this;
        }

        public Criteria andPEntrustmentUnitIsNotNull() {
            addCriterion("p_entrustment_unit is not null");
            return (Criteria) this;
        }

        public Criteria andPEntrustmentUnitEqualTo(String value) {
            addCriterion("p_entrustment_unit =", value, "pEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andPEntrustmentUnitNotEqualTo(String value) {
            addCriterion("p_entrustment_unit <>", value, "pEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andPEntrustmentUnitGreaterThan(String value) {
            addCriterion("p_entrustment_unit >", value, "pEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andPEntrustmentUnitGreaterThanOrEqualTo(String value) {
            addCriterion("p_entrustment_unit >=", value, "pEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andPEntrustmentUnitLessThan(String value) {
            addCriterion("p_entrustment_unit <", value, "pEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andPEntrustmentUnitLessThanOrEqualTo(String value) {
            addCriterion("p_entrustment_unit <=", value, "pEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andPEntrustmentUnitLike(String value) {
            addCriterion("p_entrustment_unit like", value, "pEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andPEntrustmentUnitNotLike(String value) {
            addCriterion("p_entrustment_unit not like", value, "pEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andPEntrustmentUnitIn(List<String> values) {
            addCriterion("p_entrustment_unit in", values, "pEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andPEntrustmentUnitNotIn(List<String> values) {
            addCriterion("p_entrustment_unit not in", values, "pEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andPEntrustmentUnitBetween(String value1, String value2) {
            addCriterion("p_entrustment_unit between", value1, value2, "pEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andPEntrustmentUnitNotBetween(String value1, String value2) {
            addCriterion("p_entrustment_unit not between", value1, value2, "pEntrustmentUnit");
            return (Criteria) this;
        }

        public Criteria andPLegalRepresentativeIsNull() {
            addCriterion("p_legal_representative is null");
            return (Criteria) this;
        }

        public Criteria andPLegalRepresentativeIsNotNull() {
            addCriterion("p_legal_representative is not null");
            return (Criteria) this;
        }

        public Criteria andPLegalRepresentativeEqualTo(String value) {
            addCriterion("p_legal_representative =", value, "pLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andPLegalRepresentativeNotEqualTo(String value) {
            addCriterion("p_legal_representative <>", value, "pLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andPLegalRepresentativeGreaterThan(String value) {
            addCriterion("p_legal_representative >", value, "pLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andPLegalRepresentativeGreaterThanOrEqualTo(String value) {
            addCriterion("p_legal_representative >=", value, "pLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andPLegalRepresentativeLessThan(String value) {
            addCriterion("p_legal_representative <", value, "pLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andPLegalRepresentativeLessThanOrEqualTo(String value) {
            addCriterion("p_legal_representative <=", value, "pLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andPLegalRepresentativeLike(String value) {
            addCriterion("p_legal_representative like", value, "pLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andPLegalRepresentativeNotLike(String value) {
            addCriterion("p_legal_representative not like", value, "pLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andPLegalRepresentativeIn(List<String> values) {
            addCriterion("p_legal_representative in", values, "pLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andPLegalRepresentativeNotIn(List<String> values) {
            addCriterion("p_legal_representative not in", values, "pLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andPLegalRepresentativeBetween(String value1, String value2) {
            addCriterion("p_legal_representative between", value1, value2, "pLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andPLegalRepresentativeNotBetween(String value1, String value2) {
            addCriterion("p_legal_representative not between", value1, value2, "pLegalRepresentative");
            return (Criteria) this;
        }

        public Criteria andPSociologyCodeIsNull() {
            addCriterion("p_sociology_code is null");
            return (Criteria) this;
        }

        public Criteria andPSociologyCodeIsNotNull() {
            addCriterion("p_sociology_code is not null");
            return (Criteria) this;
        }

        public Criteria andPSociologyCodeEqualTo(String value) {
            addCriterion("p_sociology_code =", value, "pSociologyCode");
            return (Criteria) this;
        }

        public Criteria andPSociologyCodeNotEqualTo(String value) {
            addCriterion("p_sociology_code <>", value, "pSociologyCode");
            return (Criteria) this;
        }

        public Criteria andPSociologyCodeGreaterThan(String value) {
            addCriterion("p_sociology_code >", value, "pSociologyCode");
            return (Criteria) this;
        }

        public Criteria andPSociologyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("p_sociology_code >=", value, "pSociologyCode");
            return (Criteria) this;
        }

        public Criteria andPSociologyCodeLessThan(String value) {
            addCriterion("p_sociology_code <", value, "pSociologyCode");
            return (Criteria) this;
        }

        public Criteria andPSociologyCodeLessThanOrEqualTo(String value) {
            addCriterion("p_sociology_code <=", value, "pSociologyCode");
            return (Criteria) this;
        }

        public Criteria andPSociologyCodeLike(String value) {
            addCriterion("p_sociology_code like", value, "pSociologyCode");
            return (Criteria) this;
        }

        public Criteria andPSociologyCodeNotLike(String value) {
            addCriterion("p_sociology_code not like", value, "pSociologyCode");
            return (Criteria) this;
        }

        public Criteria andPSociologyCodeIn(List<String> values) {
            addCriterion("p_sociology_code in", values, "pSociologyCode");
            return (Criteria) this;
        }

        public Criteria andPSociologyCodeNotIn(List<String> values) {
            addCriterion("p_sociology_code not in", values, "pSociologyCode");
            return (Criteria) this;
        }

        public Criteria andPSociologyCodeBetween(String value1, String value2) {
            addCriterion("p_sociology_code between", value1, value2, "pSociologyCode");
            return (Criteria) this;
        }

        public Criteria andPSociologyCodeNotBetween(String value1, String value2) {
            addCriterion("p_sociology_code not between", value1, value2, "pSociologyCode");
            return (Criteria) this;
        }

        public Criteria andPAddressIsNull() {
            addCriterion("p_address is null");
            return (Criteria) this;
        }

        public Criteria andPAddressIsNotNull() {
            addCriterion("p_address is not null");
            return (Criteria) this;
        }

        public Criteria andPAddressEqualTo(String value) {
            addCriterion("p_address =", value, "pAddress");
            return (Criteria) this;
        }

        public Criteria andPAddressNotEqualTo(String value) {
            addCriterion("p_address <>", value, "pAddress");
            return (Criteria) this;
        }

        public Criteria andPAddressGreaterThan(String value) {
            addCriterion("p_address >", value, "pAddress");
            return (Criteria) this;
        }

        public Criteria andPAddressGreaterThanOrEqualTo(String value) {
            addCriterion("p_address >=", value, "pAddress");
            return (Criteria) this;
        }

        public Criteria andPAddressLessThan(String value) {
            addCriterion("p_address <", value, "pAddress");
            return (Criteria) this;
        }

        public Criteria andPAddressLessThanOrEqualTo(String value) {
            addCriterion("p_address <=", value, "pAddress");
            return (Criteria) this;
        }

        public Criteria andPAddressLike(String value) {
            addCriterion("p_address like", value, "pAddress");
            return (Criteria) this;
        }

        public Criteria andPAddressNotLike(String value) {
            addCriterion("p_address not like", value, "pAddress");
            return (Criteria) this;
        }

        public Criteria andPAddressIn(List<String> values) {
            addCriterion("p_address in", values, "pAddress");
            return (Criteria) this;
        }

        public Criteria andPAddressNotIn(List<String> values) {
            addCriterion("p_address not in", values, "pAddress");
            return (Criteria) this;
        }

        public Criteria andPAddressBetween(String value1, String value2) {
            addCriterion("p_address between", value1, value2, "pAddress");
            return (Criteria) this;
        }

        public Criteria andPAddressNotBetween(String value1, String value2) {
            addCriterion("p_address not between", value1, value2, "pAddress");
            return (Criteria) this;
        }

        public Criteria andPScopeOperationIsNull() {
            addCriterion("p_scope_operation is null");
            return (Criteria) this;
        }

        public Criteria andPScopeOperationIsNotNull() {
            addCriterion("p_scope_operation is not null");
            return (Criteria) this;
        }

        public Criteria andPScopeOperationEqualTo(String value) {
            addCriterion("p_scope_operation =", value, "pScopeOperation");
            return (Criteria) this;
        }

        public Criteria andPScopeOperationNotEqualTo(String value) {
            addCriterion("p_scope_operation <>", value, "pScopeOperation");
            return (Criteria) this;
        }

        public Criteria andPScopeOperationGreaterThan(String value) {
            addCriterion("p_scope_operation >", value, "pScopeOperation");
            return (Criteria) this;
        }

        public Criteria andPScopeOperationGreaterThanOrEqualTo(String value) {
            addCriterion("p_scope_operation >=", value, "pScopeOperation");
            return (Criteria) this;
        }

        public Criteria andPScopeOperationLessThan(String value) {
            addCriterion("p_scope_operation <", value, "pScopeOperation");
            return (Criteria) this;
        }

        public Criteria andPScopeOperationLessThanOrEqualTo(String value) {
            addCriterion("p_scope_operation <=", value, "pScopeOperation");
            return (Criteria) this;
        }

        public Criteria andPScopeOperationLike(String value) {
            addCriterion("p_scope_operation like", value, "pScopeOperation");
            return (Criteria) this;
        }

        public Criteria andPScopeOperationNotLike(String value) {
            addCriterion("p_scope_operation not like", value, "pScopeOperation");
            return (Criteria) this;
        }

        public Criteria andPScopeOperationIn(List<String> values) {
            addCriterion("p_scope_operation in", values, "pScopeOperation");
            return (Criteria) this;
        }

        public Criteria andPScopeOperationNotIn(List<String> values) {
            addCriterion("p_scope_operation not in", values, "pScopeOperation");
            return (Criteria) this;
        }

        public Criteria andPScopeOperationBetween(String value1, String value2) {
            addCriterion("p_scope_operation between", value1, value2, "pScopeOperation");
            return (Criteria) this;
        }

        public Criteria andPScopeOperationNotBetween(String value1, String value2) {
            addCriterion("p_scope_operation not between", value1, value2, "pScopeOperation");
            return (Criteria) this;
        }

        public Criteria andPUnitPropertiesIsNull() {
            addCriterion("p_unit_properties is null");
            return (Criteria) this;
        }

        public Criteria andPUnitPropertiesIsNotNull() {
            addCriterion("p_unit_properties is not null");
            return (Criteria) this;
        }

        public Criteria andPUnitPropertiesEqualTo(String value) {
            addCriterion("p_unit_properties =", value, "pUnitProperties");
            return (Criteria) this;
        }

        public Criteria andPUnitPropertiesNotEqualTo(String value) {
            addCriterion("p_unit_properties <>", value, "pUnitProperties");
            return (Criteria) this;
        }

        public Criteria andPUnitPropertiesGreaterThan(String value) {
            addCriterion("p_unit_properties >", value, "pUnitProperties");
            return (Criteria) this;
        }

        public Criteria andPUnitPropertiesGreaterThanOrEqualTo(String value) {
            addCriterion("p_unit_properties >=", value, "pUnitProperties");
            return (Criteria) this;
        }

        public Criteria andPUnitPropertiesLessThan(String value) {
            addCriterion("p_unit_properties <", value, "pUnitProperties");
            return (Criteria) this;
        }

        public Criteria andPUnitPropertiesLessThanOrEqualTo(String value) {
            addCriterion("p_unit_properties <=", value, "pUnitProperties");
            return (Criteria) this;
        }

        public Criteria andPUnitPropertiesLike(String value) {
            addCriterion("p_unit_properties like", value, "pUnitProperties");
            return (Criteria) this;
        }

        public Criteria andPUnitPropertiesNotLike(String value) {
            addCriterion("p_unit_properties not like", value, "pUnitProperties");
            return (Criteria) this;
        }

        public Criteria andPUnitPropertiesIn(List<String> values) {
            addCriterion("p_unit_properties in", values, "pUnitProperties");
            return (Criteria) this;
        }

        public Criteria andPUnitPropertiesNotIn(List<String> values) {
            addCriterion("p_unit_properties not in", values, "pUnitProperties");
            return (Criteria) this;
        }

        public Criteria andPUnitPropertiesBetween(String value1, String value2) {
            addCriterion("p_unit_properties between", value1, value2, "pUnitProperties");
            return (Criteria) this;
        }

        public Criteria andPUnitPropertiesNotBetween(String value1, String value2) {
            addCriterion("p_unit_properties not between", value1, value2, "pUnitProperties");
            return (Criteria) this;
        }

        public Criteria andPNameIsNull() {
            addCriterion("p_name is null");
            return (Criteria) this;
        }

        public Criteria andPNameIsNotNull() {
            addCriterion("p_name is not null");
            return (Criteria) this;
        }

        public Criteria andPNameEqualTo(String value) {
            addCriterion("p_name =", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameNotEqualTo(String value) {
            addCriterion("p_name <>", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameGreaterThan(String value) {
            addCriterion("p_name >", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameGreaterThanOrEqualTo(String value) {
            addCriterion("p_name >=", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameLessThan(String value) {
            addCriterion("p_name <", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameLessThanOrEqualTo(String value) {
            addCriterion("p_name <=", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameLike(String value) {
            addCriterion("p_name like", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameNotLike(String value) {
            addCriterion("p_name not like", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameIn(List<String> values) {
            addCriterion("p_name in", values, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameNotIn(List<String> values) {
            addCriterion("p_name not in", values, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameBetween(String value1, String value2) {
            addCriterion("p_name between", value1, value2, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameNotBetween(String value1, String value2) {
            addCriterion("p_name not between", value1, value2, "pName");
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

        public Criteria andPIdcardIsNull() {
            addCriterion("p_idcard is null");
            return (Criteria) this;
        }

        public Criteria andPIdcardIsNotNull() {
            addCriterion("p_idcard is not null");
            return (Criteria) this;
        }

        public Criteria andPIdcardEqualTo(String value) {
            addCriterion("p_idcard =", value, "pIdcard");
            return (Criteria) this;
        }

        public Criteria andPIdcardNotEqualTo(String value) {
            addCriterion("p_idcard <>", value, "pIdcard");
            return (Criteria) this;
        }

        public Criteria andPIdcardGreaterThan(String value) {
            addCriterion("p_idcard >", value, "pIdcard");
            return (Criteria) this;
        }

        public Criteria andPIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("p_idcard >=", value, "pIdcard");
            return (Criteria) this;
        }

        public Criteria andPIdcardLessThan(String value) {
            addCriterion("p_idcard <", value, "pIdcard");
            return (Criteria) this;
        }

        public Criteria andPIdcardLessThanOrEqualTo(String value) {
            addCriterion("p_idcard <=", value, "pIdcard");
            return (Criteria) this;
        }

        public Criteria andPIdcardLike(String value) {
            addCriterion("p_idcard like", value, "pIdcard");
            return (Criteria) this;
        }

        public Criteria andPIdcardNotLike(String value) {
            addCriterion("p_idcard not like", value, "pIdcard");
            return (Criteria) this;
        }

        public Criteria andPIdcardIn(List<String> values) {
            addCriterion("p_idcard in", values, "pIdcard");
            return (Criteria) this;
        }

        public Criteria andPIdcardNotIn(List<String> values) {
            addCriterion("p_idcard not in", values, "pIdcard");
            return (Criteria) this;
        }

        public Criteria andPIdcardBetween(String value1, String value2) {
            addCriterion("p_idcard between", value1, value2, "pIdcard");
            return (Criteria) this;
        }

        public Criteria andPIdcardNotBetween(String value1, String value2) {
            addCriterion("p_idcard not between", value1, value2, "pIdcard");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * tb_initiate_possessor
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