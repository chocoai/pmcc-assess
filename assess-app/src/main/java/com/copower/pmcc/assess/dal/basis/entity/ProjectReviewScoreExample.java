package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectReviewScoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectReviewScoreExample() {
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

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationIsNull() {
            addCriterion("contract_negotiation is null");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationIsNotNull() {
            addCriterion("contract_negotiation is not null");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationEqualTo(BigDecimal value) {
            addCriterion("contract_negotiation =", value, "contractNegotiation");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationNotEqualTo(BigDecimal value) {
            addCriterion("contract_negotiation <>", value, "contractNegotiation");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationGreaterThan(BigDecimal value) {
            addCriterion("contract_negotiation >", value, "contractNegotiation");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("contract_negotiation >=", value, "contractNegotiation");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationLessThan(BigDecimal value) {
            addCriterion("contract_negotiation <", value, "contractNegotiation");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationLessThanOrEqualTo(BigDecimal value) {
            addCriterion("contract_negotiation <=", value, "contractNegotiation");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationIn(List<BigDecimal> values) {
            addCriterion("contract_negotiation in", values, "contractNegotiation");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationNotIn(List<BigDecimal> values) {
            addCriterion("contract_negotiation not in", values, "contractNegotiation");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("contract_negotiation between", value1, value2, "contractNegotiation");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("contract_negotiation not between", value1, value2, "contractNegotiation");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationExplainIsNull() {
            addCriterion("contract_negotiation_explain is null");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationExplainIsNotNull() {
            addCriterion("contract_negotiation_explain is not null");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationExplainEqualTo(String value) {
            addCriterion("contract_negotiation_explain =", value, "contractNegotiationExplain");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationExplainNotEqualTo(String value) {
            addCriterion("contract_negotiation_explain <>", value, "contractNegotiationExplain");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationExplainGreaterThan(String value) {
            addCriterion("contract_negotiation_explain >", value, "contractNegotiationExplain");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationExplainGreaterThanOrEqualTo(String value) {
            addCriterion("contract_negotiation_explain >=", value, "contractNegotiationExplain");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationExplainLessThan(String value) {
            addCriterion("contract_negotiation_explain <", value, "contractNegotiationExplain");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationExplainLessThanOrEqualTo(String value) {
            addCriterion("contract_negotiation_explain <=", value, "contractNegotiationExplain");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationExplainLike(String value) {
            addCriterion("contract_negotiation_explain like", value, "contractNegotiationExplain");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationExplainNotLike(String value) {
            addCriterion("contract_negotiation_explain not like", value, "contractNegotiationExplain");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationExplainIn(List<String> values) {
            addCriterion("contract_negotiation_explain in", values, "contractNegotiationExplain");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationExplainNotIn(List<String> values) {
            addCriterion("contract_negotiation_explain not in", values, "contractNegotiationExplain");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationExplainBetween(String value1, String value2) {
            addCriterion("contract_negotiation_explain between", value1, value2, "contractNegotiationExplain");
            return (Criteria) this;
        }

        public Criteria andContractNegotiationExplainNotBetween(String value1, String value2) {
            addCriterion("contract_negotiation_explain not between", value1, value2, "contractNegotiationExplain");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesIsNull() {
            addCriterion("customer_activities is null");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesIsNotNull() {
            addCriterion("customer_activities is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesEqualTo(BigDecimal value) {
            addCriterion("customer_activities =", value, "customerActivities");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesNotEqualTo(BigDecimal value) {
            addCriterion("customer_activities <>", value, "customerActivities");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesGreaterThan(BigDecimal value) {
            addCriterion("customer_activities >", value, "customerActivities");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("customer_activities >=", value, "customerActivities");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesLessThan(BigDecimal value) {
            addCriterion("customer_activities <", value, "customerActivities");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesLessThanOrEqualTo(BigDecimal value) {
            addCriterion("customer_activities <=", value, "customerActivities");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesIn(List<BigDecimal> values) {
            addCriterion("customer_activities in", values, "customerActivities");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesNotIn(List<BigDecimal> values) {
            addCriterion("customer_activities not in", values, "customerActivities");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("customer_activities between", value1, value2, "customerActivities");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("customer_activities not between", value1, value2, "customerActivities");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesExplainIsNull() {
            addCriterion("customer_activities_explain is null");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesExplainIsNotNull() {
            addCriterion("customer_activities_explain is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesExplainEqualTo(String value) {
            addCriterion("customer_activities_explain =", value, "customerActivitiesExplain");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesExplainNotEqualTo(String value) {
            addCriterion("customer_activities_explain <>", value, "customerActivitiesExplain");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesExplainGreaterThan(String value) {
            addCriterion("customer_activities_explain >", value, "customerActivitiesExplain");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesExplainGreaterThanOrEqualTo(String value) {
            addCriterion("customer_activities_explain >=", value, "customerActivitiesExplain");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesExplainLessThan(String value) {
            addCriterion("customer_activities_explain <", value, "customerActivitiesExplain");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesExplainLessThanOrEqualTo(String value) {
            addCriterion("customer_activities_explain <=", value, "customerActivitiesExplain");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesExplainLike(String value) {
            addCriterion("customer_activities_explain like", value, "customerActivitiesExplain");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesExplainNotLike(String value) {
            addCriterion("customer_activities_explain not like", value, "customerActivitiesExplain");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesExplainIn(List<String> values) {
            addCriterion("customer_activities_explain in", values, "customerActivitiesExplain");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesExplainNotIn(List<String> values) {
            addCriterion("customer_activities_explain not in", values, "customerActivitiesExplain");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesExplainBetween(String value1, String value2) {
            addCriterion("customer_activities_explain between", value1, value2, "customerActivitiesExplain");
            return (Criteria) this;
        }

        public Criteria andCustomerActivitiesExplainNotBetween(String value1, String value2) {
            addCriterion("customer_activities_explain not between", value1, value2, "customerActivitiesExplain");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionIsNull() {
            addCriterion("work_division is null");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionIsNotNull() {
            addCriterion("work_division is not null");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionEqualTo(BigDecimal value) {
            addCriterion("work_division =", value, "workDivision");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionNotEqualTo(BigDecimal value) {
            addCriterion("work_division <>", value, "workDivision");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionGreaterThan(BigDecimal value) {
            addCriterion("work_division >", value, "workDivision");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("work_division >=", value, "workDivision");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionLessThan(BigDecimal value) {
            addCriterion("work_division <", value, "workDivision");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("work_division <=", value, "workDivision");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionIn(List<BigDecimal> values) {
            addCriterion("work_division in", values, "workDivision");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionNotIn(List<BigDecimal> values) {
            addCriterion("work_division not in", values, "workDivision");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("work_division between", value1, value2, "workDivision");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("work_division not between", value1, value2, "workDivision");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionExplainIsNull() {
            addCriterion("work_division_explain is null");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionExplainIsNotNull() {
            addCriterion("work_division_explain is not null");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionExplainEqualTo(String value) {
            addCriterion("work_division_explain =", value, "workDivisionExplain");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionExplainNotEqualTo(String value) {
            addCriterion("work_division_explain <>", value, "workDivisionExplain");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionExplainGreaterThan(String value) {
            addCriterion("work_division_explain >", value, "workDivisionExplain");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionExplainGreaterThanOrEqualTo(String value) {
            addCriterion("work_division_explain >=", value, "workDivisionExplain");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionExplainLessThan(String value) {
            addCriterion("work_division_explain <", value, "workDivisionExplain");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionExplainLessThanOrEqualTo(String value) {
            addCriterion("work_division_explain <=", value, "workDivisionExplain");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionExplainLike(String value) {
            addCriterion("work_division_explain like", value, "workDivisionExplain");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionExplainNotLike(String value) {
            addCriterion("work_division_explain not like", value, "workDivisionExplain");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionExplainIn(List<String> values) {
            addCriterion("work_division_explain in", values, "workDivisionExplain");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionExplainNotIn(List<String> values) {
            addCriterion("work_division_explain not in", values, "workDivisionExplain");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionExplainBetween(String value1, String value2) {
            addCriterion("work_division_explain between", value1, value2, "workDivisionExplain");
            return (Criteria) this;
        }

        public Criteria andWorkDivisionExplainNotBetween(String value1, String value2) {
            addCriterion("work_division_explain not between", value1, value2, "workDivisionExplain");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionIsNull() {
            addCriterion("invoice_collection is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionIsNotNull() {
            addCriterion("invoice_collection is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionEqualTo(BigDecimal value) {
            addCriterion("invoice_collection =", value, "invoiceCollection");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionNotEqualTo(BigDecimal value) {
            addCriterion("invoice_collection <>", value, "invoiceCollection");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionGreaterThan(BigDecimal value) {
            addCriterion("invoice_collection >", value, "invoiceCollection");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("invoice_collection >=", value, "invoiceCollection");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionLessThan(BigDecimal value) {
            addCriterion("invoice_collection <", value, "invoiceCollection");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("invoice_collection <=", value, "invoiceCollection");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionIn(List<BigDecimal> values) {
            addCriterion("invoice_collection in", values, "invoiceCollection");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionNotIn(List<BigDecimal> values) {
            addCriterion("invoice_collection not in", values, "invoiceCollection");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("invoice_collection between", value1, value2, "invoiceCollection");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("invoice_collection not between", value1, value2, "invoiceCollection");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionExplainIsNull() {
            addCriterion("invoice_collection_explain is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionExplainIsNotNull() {
            addCriterion("invoice_collection_explain is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionExplainEqualTo(String value) {
            addCriterion("invoice_collection_explain =", value, "invoiceCollectionExplain");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionExplainNotEqualTo(String value) {
            addCriterion("invoice_collection_explain <>", value, "invoiceCollectionExplain");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionExplainGreaterThan(String value) {
            addCriterion("invoice_collection_explain >", value, "invoiceCollectionExplain");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionExplainGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_collection_explain >=", value, "invoiceCollectionExplain");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionExplainLessThan(String value) {
            addCriterion("invoice_collection_explain <", value, "invoiceCollectionExplain");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionExplainLessThanOrEqualTo(String value) {
            addCriterion("invoice_collection_explain <=", value, "invoiceCollectionExplain");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionExplainLike(String value) {
            addCriterion("invoice_collection_explain like", value, "invoiceCollectionExplain");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionExplainNotLike(String value) {
            addCriterion("invoice_collection_explain not like", value, "invoiceCollectionExplain");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionExplainIn(List<String> values) {
            addCriterion("invoice_collection_explain in", values, "invoiceCollectionExplain");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionExplainNotIn(List<String> values) {
            addCriterion("invoice_collection_explain not in", values, "invoiceCollectionExplain");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionExplainBetween(String value1, String value2) {
            addCriterion("invoice_collection_explain between", value1, value2, "invoiceCollectionExplain");
            return (Criteria) this;
        }

        public Criteria andInvoiceCollectionExplainNotBetween(String value1, String value2) {
            addCriterion("invoice_collection_explain not between", value1, value2, "invoiceCollectionExplain");
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

        public Criteria andProcessInsIdIsNull() {
            addCriterion("process_ins_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdIsNotNull() {
            addCriterion("process_ins_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdEqualTo(String value) {
            addCriterion("process_ins_id =", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotEqualTo(String value) {
            addCriterion("process_ins_id <>", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdGreaterThan(String value) {
            addCriterion("process_ins_id >", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdGreaterThanOrEqualTo(String value) {
            addCriterion("process_ins_id >=", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLessThan(String value) {
            addCriterion("process_ins_id <", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLessThanOrEqualTo(String value) {
            addCriterion("process_ins_id <=", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLike(String value) {
            addCriterion("process_ins_id like", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotLike(String value) {
            addCriterion("process_ins_id not like", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdIn(List<String> values) {
            addCriterion("process_ins_id in", values, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotIn(List<String> values) {
            addCriterion("process_ins_id not in", values, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdBetween(String value1, String value2) {
            addCriterion("process_ins_id between", value1, value2, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotBetween(String value1, String value2) {
            addCriterion("process_ins_id not between", value1, value2, "processInsId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
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