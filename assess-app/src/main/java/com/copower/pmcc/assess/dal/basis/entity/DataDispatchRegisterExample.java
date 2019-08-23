package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataDispatchRegisterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataDispatchRegisterExample() {
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

        public Criteria andDispatchDateIsNull() {
            addCriterion("dispatch_date is null");
            return (Criteria) this;
        }

        public Criteria andDispatchDateIsNotNull() {
            addCriterion("dispatch_date is not null");
            return (Criteria) this;
        }

        public Criteria andDispatchDateEqualTo(String value) {
            addCriterion("dispatch_date =", value, "dispatchDate");
            return (Criteria) this;
        }

        public Criteria andDispatchDateNotEqualTo(String value) {
            addCriterion("dispatch_date <>", value, "dispatchDate");
            return (Criteria) this;
        }

        public Criteria andDispatchDateGreaterThan(String value) {
            addCriterion("dispatch_date >", value, "dispatchDate");
            return (Criteria) this;
        }

        public Criteria andDispatchDateGreaterThanOrEqualTo(String value) {
            addCriterion("dispatch_date >=", value, "dispatchDate");
            return (Criteria) this;
        }

        public Criteria andDispatchDateLessThan(String value) {
            addCriterion("dispatch_date <", value, "dispatchDate");
            return (Criteria) this;
        }

        public Criteria andDispatchDateLessThanOrEqualTo(String value) {
            addCriterion("dispatch_date <=", value, "dispatchDate");
            return (Criteria) this;
        }

        public Criteria andDispatchDateLike(String value) {
            addCriterion("dispatch_date like", value, "dispatchDate");
            return (Criteria) this;
        }

        public Criteria andDispatchDateNotLike(String value) {
            addCriterion("dispatch_date not like", value, "dispatchDate");
            return (Criteria) this;
        }

        public Criteria andDispatchDateIn(List<String> values) {
            addCriterion("dispatch_date in", values, "dispatchDate");
            return (Criteria) this;
        }

        public Criteria andDispatchDateNotIn(List<String> values) {
            addCriterion("dispatch_date not in", values, "dispatchDate");
            return (Criteria) this;
        }

        public Criteria andDispatchDateBetween(String value1, String value2) {
            addCriterion("dispatch_date between", value1, value2, "dispatchDate");
            return (Criteria) this;
        }

        public Criteria andDispatchDateNotBetween(String value1, String value2) {
            addCriterion("dispatch_date not between", value1, value2, "dispatchDate");
            return (Criteria) this;
        }

        public Criteria andDispatchNumberIsNull() {
            addCriterion("dispatch_number is null");
            return (Criteria) this;
        }

        public Criteria andDispatchNumberIsNotNull() {
            addCriterion("dispatch_number is not null");
            return (Criteria) this;
        }

        public Criteria andDispatchNumberEqualTo(String value) {
            addCriterion("dispatch_number =", value, "dispatchNumber");
            return (Criteria) this;
        }

        public Criteria andDispatchNumberNotEqualTo(String value) {
            addCriterion("dispatch_number <>", value, "dispatchNumber");
            return (Criteria) this;
        }

        public Criteria andDispatchNumberGreaterThan(String value) {
            addCriterion("dispatch_number >", value, "dispatchNumber");
            return (Criteria) this;
        }

        public Criteria andDispatchNumberGreaterThanOrEqualTo(String value) {
            addCriterion("dispatch_number >=", value, "dispatchNumber");
            return (Criteria) this;
        }

        public Criteria andDispatchNumberLessThan(String value) {
            addCriterion("dispatch_number <", value, "dispatchNumber");
            return (Criteria) this;
        }

        public Criteria andDispatchNumberLessThanOrEqualTo(String value) {
            addCriterion("dispatch_number <=", value, "dispatchNumber");
            return (Criteria) this;
        }

        public Criteria andDispatchNumberLike(String value) {
            addCriterion("dispatch_number like", value, "dispatchNumber");
            return (Criteria) this;
        }

        public Criteria andDispatchNumberNotLike(String value) {
            addCriterion("dispatch_number not like", value, "dispatchNumber");
            return (Criteria) this;
        }

        public Criteria andDispatchNumberIn(List<String> values) {
            addCriterion("dispatch_number in", values, "dispatchNumber");
            return (Criteria) this;
        }

        public Criteria andDispatchNumberNotIn(List<String> values) {
            addCriterion("dispatch_number not in", values, "dispatchNumber");
            return (Criteria) this;
        }

        public Criteria andDispatchNumberBetween(String value1, String value2) {
            addCriterion("dispatch_number between", value1, value2, "dispatchNumber");
            return (Criteria) this;
        }

        public Criteria andDispatchNumberNotBetween(String value1, String value2) {
            addCriterion("dispatch_number not between", value1, value2, "dispatchNumber");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIsNull() {
            addCriterion("business_type is null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIsNotNull() {
            addCriterion("business_type is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeEqualTo(String value) {
            addCriterion("business_type =", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotEqualTo(String value) {
            addCriterion("business_type <>", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeGreaterThan(String value) {
            addCriterion("business_type >", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeGreaterThanOrEqualTo(String value) {
            addCriterion("business_type >=", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLessThan(String value) {
            addCriterion("business_type <", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLessThanOrEqualTo(String value) {
            addCriterion("business_type <=", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLike(String value) {
            addCriterion("business_type like", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotLike(String value) {
            addCriterion("business_type not like", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIn(List<String> values) {
            addCriterion("business_type in", values, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotIn(List<String> values) {
            addCriterion("business_type not in", values, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeBetween(String value1, String value2) {
            addCriterion("business_type between", value1, value2, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotBetween(String value1, String value2) {
            addCriterion("business_type not between", value1, value2, "businessType");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeIsNull() {
            addCriterion("entrust_purpose is null");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeIsNotNull() {
            addCriterion("entrust_purpose is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeEqualTo(String value) {
            addCriterion("entrust_purpose =", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNotEqualTo(String value) {
            addCriterion("entrust_purpose <>", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeGreaterThan(String value) {
            addCriterion("entrust_purpose >", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeGreaterThanOrEqualTo(String value) {
            addCriterion("entrust_purpose >=", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLessThan(String value) {
            addCriterion("entrust_purpose <", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLessThanOrEqualTo(String value) {
            addCriterion("entrust_purpose <=", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeLike(String value) {
            addCriterion("entrust_purpose like", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNotLike(String value) {
            addCriterion("entrust_purpose not like", value, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeIn(List<String> values) {
            addCriterion("entrust_purpose in", values, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNotIn(List<String> values) {
            addCriterion("entrust_purpose not in", values, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeBetween(String value1, String value2) {
            addCriterion("entrust_purpose between", value1, value2, "entrustPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustPurposeNotBetween(String value1, String value2) {
            addCriterion("entrust_purpose not between", value1, value2, "entrustPurpose");
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

        public Criteria andClientCompanyIsNull() {
            addCriterion("client_company is null");
            return (Criteria) this;
        }

        public Criteria andClientCompanyIsNotNull() {
            addCriterion("client_company is not null");
            return (Criteria) this;
        }

        public Criteria andClientCompanyEqualTo(String value) {
            addCriterion("client_company =", value, "clientCompany");
            return (Criteria) this;
        }

        public Criteria andClientCompanyNotEqualTo(String value) {
            addCriterion("client_company <>", value, "clientCompany");
            return (Criteria) this;
        }

        public Criteria andClientCompanyGreaterThan(String value) {
            addCriterion("client_company >", value, "clientCompany");
            return (Criteria) this;
        }

        public Criteria andClientCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("client_company >=", value, "clientCompany");
            return (Criteria) this;
        }

        public Criteria andClientCompanyLessThan(String value) {
            addCriterion("client_company <", value, "clientCompany");
            return (Criteria) this;
        }

        public Criteria andClientCompanyLessThanOrEqualTo(String value) {
            addCriterion("client_company <=", value, "clientCompany");
            return (Criteria) this;
        }

        public Criteria andClientCompanyLike(String value) {
            addCriterion("client_company like", value, "clientCompany");
            return (Criteria) this;
        }

        public Criteria andClientCompanyNotLike(String value) {
            addCriterion("client_company not like", value, "clientCompany");
            return (Criteria) this;
        }

        public Criteria andClientCompanyIn(List<String> values) {
            addCriterion("client_company in", values, "clientCompany");
            return (Criteria) this;
        }

        public Criteria andClientCompanyNotIn(List<String> values) {
            addCriterion("client_company not in", values, "clientCompany");
            return (Criteria) this;
        }

        public Criteria andClientCompanyBetween(String value1, String value2) {
            addCriterion("client_company between", value1, value2, "clientCompany");
            return (Criteria) this;
        }

        public Criteria andClientCompanyNotBetween(String value1, String value2) {
            addCriterion("client_company not between", value1, value2, "clientCompany");
            return (Criteria) this;
        }

        public Criteria andEntrustUnitIsNull() {
            addCriterion("entrust_unit is null");
            return (Criteria) this;
        }

        public Criteria andEntrustUnitIsNotNull() {
            addCriterion("entrust_unit is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustUnitEqualTo(String value) {
            addCriterion("entrust_unit =", value, "entrustUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustUnitNotEqualTo(String value) {
            addCriterion("entrust_unit <>", value, "entrustUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustUnitGreaterThan(String value) {
            addCriterion("entrust_unit >", value, "entrustUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustUnitGreaterThanOrEqualTo(String value) {
            addCriterion("entrust_unit >=", value, "entrustUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustUnitLessThan(String value) {
            addCriterion("entrust_unit <", value, "entrustUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustUnitLessThanOrEqualTo(String value) {
            addCriterion("entrust_unit <=", value, "entrustUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustUnitLike(String value) {
            addCriterion("entrust_unit like", value, "entrustUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustUnitNotLike(String value) {
            addCriterion("entrust_unit not like", value, "entrustUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustUnitIn(List<String> values) {
            addCriterion("entrust_unit in", values, "entrustUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustUnitNotIn(List<String> values) {
            addCriterion("entrust_unit not in", values, "entrustUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustUnitBetween(String value1, String value2) {
            addCriterion("entrust_unit between", value1, value2, "entrustUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustUnitNotBetween(String value1, String value2) {
            addCriterion("entrust_unit not between", value1, value2, "entrustUnit");
            return (Criteria) this;
        }

        public Criteria andAssessAreaIsNull() {
            addCriterion("assess_area is null");
            return (Criteria) this;
        }

        public Criteria andAssessAreaIsNotNull() {
            addCriterion("assess_area is not null");
            return (Criteria) this;
        }

        public Criteria andAssessAreaEqualTo(String value) {
            addCriterion("assess_area =", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaNotEqualTo(String value) {
            addCriterion("assess_area <>", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaGreaterThan(String value) {
            addCriterion("assess_area >", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaGreaterThanOrEqualTo(String value) {
            addCriterion("assess_area >=", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaLessThan(String value) {
            addCriterion("assess_area <", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaLessThanOrEqualTo(String value) {
            addCriterion("assess_area <=", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaLike(String value) {
            addCriterion("assess_area like", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaNotLike(String value) {
            addCriterion("assess_area not like", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaIn(List<String> values) {
            addCriterion("assess_area in", values, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaNotIn(List<String> values) {
            addCriterion("assess_area not in", values, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaBetween(String value1, String value2) {
            addCriterion("assess_area between", value1, value2, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaNotBetween(String value1, String value2) {
            addCriterion("assess_area not between", value1, value2, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAmountIsNull() {
            addCriterion("assess_amount is null");
            return (Criteria) this;
        }

        public Criteria andAssessAmountIsNotNull() {
            addCriterion("assess_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAssessAmountEqualTo(String value) {
            addCriterion("assess_amount =", value, "assessAmount");
            return (Criteria) this;
        }

        public Criteria andAssessAmountNotEqualTo(String value) {
            addCriterion("assess_amount <>", value, "assessAmount");
            return (Criteria) this;
        }

        public Criteria andAssessAmountGreaterThan(String value) {
            addCriterion("assess_amount >", value, "assessAmount");
            return (Criteria) this;
        }

        public Criteria andAssessAmountGreaterThanOrEqualTo(String value) {
            addCriterion("assess_amount >=", value, "assessAmount");
            return (Criteria) this;
        }

        public Criteria andAssessAmountLessThan(String value) {
            addCriterion("assess_amount <", value, "assessAmount");
            return (Criteria) this;
        }

        public Criteria andAssessAmountLessThanOrEqualTo(String value) {
            addCriterion("assess_amount <=", value, "assessAmount");
            return (Criteria) this;
        }

        public Criteria andAssessAmountLike(String value) {
            addCriterion("assess_amount like", value, "assessAmount");
            return (Criteria) this;
        }

        public Criteria andAssessAmountNotLike(String value) {
            addCriterion("assess_amount not like", value, "assessAmount");
            return (Criteria) this;
        }

        public Criteria andAssessAmountIn(List<String> values) {
            addCriterion("assess_amount in", values, "assessAmount");
            return (Criteria) this;
        }

        public Criteria andAssessAmountNotIn(List<String> values) {
            addCriterion("assess_amount not in", values, "assessAmount");
            return (Criteria) this;
        }

        public Criteria andAssessAmountBetween(String value1, String value2) {
            addCriterion("assess_amount between", value1, value2, "assessAmount");
            return (Criteria) this;
        }

        public Criteria andAssessAmountNotBetween(String value1, String value2) {
            addCriterion("assess_amount not between", value1, value2, "assessAmount");
            return (Criteria) this;
        }

        public Criteria andSendNumberIsNull() {
            addCriterion("send_number is null");
            return (Criteria) this;
        }

        public Criteria andSendNumberIsNotNull() {
            addCriterion("send_number is not null");
            return (Criteria) this;
        }

        public Criteria andSendNumberEqualTo(String value) {
            addCriterion("send_number =", value, "sendNumber");
            return (Criteria) this;
        }

        public Criteria andSendNumberNotEqualTo(String value) {
            addCriterion("send_number <>", value, "sendNumber");
            return (Criteria) this;
        }

        public Criteria andSendNumberGreaterThan(String value) {
            addCriterion("send_number >", value, "sendNumber");
            return (Criteria) this;
        }

        public Criteria andSendNumberGreaterThanOrEqualTo(String value) {
            addCriterion("send_number >=", value, "sendNumber");
            return (Criteria) this;
        }

        public Criteria andSendNumberLessThan(String value) {
            addCriterion("send_number <", value, "sendNumber");
            return (Criteria) this;
        }

        public Criteria andSendNumberLessThanOrEqualTo(String value) {
            addCriterion("send_number <=", value, "sendNumber");
            return (Criteria) this;
        }

        public Criteria andSendNumberLike(String value) {
            addCriterion("send_number like", value, "sendNumber");
            return (Criteria) this;
        }

        public Criteria andSendNumberNotLike(String value) {
            addCriterion("send_number not like", value, "sendNumber");
            return (Criteria) this;
        }

        public Criteria andSendNumberIn(List<String> values) {
            addCriterion("send_number in", values, "sendNumber");
            return (Criteria) this;
        }

        public Criteria andSendNumberNotIn(List<String> values) {
            addCriterion("send_number not in", values, "sendNumber");
            return (Criteria) this;
        }

        public Criteria andSendNumberBetween(String value1, String value2) {
            addCriterion("send_number between", value1, value2, "sendNumber");
            return (Criteria) this;
        }

        public Criteria andSendNumberNotBetween(String value1, String value2) {
            addCriterion("send_number not between", value1, value2, "sendNumber");
            return (Criteria) this;
        }

        public Criteria andRemainNumberIsNull() {
            addCriterion("remain_number is null");
            return (Criteria) this;
        }

        public Criteria andRemainNumberIsNotNull() {
            addCriterion("remain_number is not null");
            return (Criteria) this;
        }

        public Criteria andRemainNumberEqualTo(String value) {
            addCriterion("remain_number =", value, "remainNumber");
            return (Criteria) this;
        }

        public Criteria andRemainNumberNotEqualTo(String value) {
            addCriterion("remain_number <>", value, "remainNumber");
            return (Criteria) this;
        }

        public Criteria andRemainNumberGreaterThan(String value) {
            addCriterion("remain_number >", value, "remainNumber");
            return (Criteria) this;
        }

        public Criteria andRemainNumberGreaterThanOrEqualTo(String value) {
            addCriterion("remain_number >=", value, "remainNumber");
            return (Criteria) this;
        }

        public Criteria andRemainNumberLessThan(String value) {
            addCriterion("remain_number <", value, "remainNumber");
            return (Criteria) this;
        }

        public Criteria andRemainNumberLessThanOrEqualTo(String value) {
            addCriterion("remain_number <=", value, "remainNumber");
            return (Criteria) this;
        }

        public Criteria andRemainNumberLike(String value) {
            addCriterion("remain_number like", value, "remainNumber");
            return (Criteria) this;
        }

        public Criteria andRemainNumberNotLike(String value) {
            addCriterion("remain_number not like", value, "remainNumber");
            return (Criteria) this;
        }

        public Criteria andRemainNumberIn(List<String> values) {
            addCriterion("remain_number in", values, "remainNumber");
            return (Criteria) this;
        }

        public Criteria andRemainNumberNotIn(List<String> values) {
            addCriterion("remain_number not in", values, "remainNumber");
            return (Criteria) this;
        }

        public Criteria andRemainNumberBetween(String value1, String value2) {
            addCriterion("remain_number between", value1, value2, "remainNumber");
            return (Criteria) this;
        }

        public Criteria andRemainNumberNotBetween(String value1, String value2) {
            addCriterion("remain_number not between", value1, value2, "remainNumber");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andApproverIsNull() {
            addCriterion("approver is null");
            return (Criteria) this;
        }

        public Criteria andApproverIsNotNull() {
            addCriterion("approver is not null");
            return (Criteria) this;
        }

        public Criteria andApproverEqualTo(String value) {
            addCriterion("approver =", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverNotEqualTo(String value) {
            addCriterion("approver <>", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverGreaterThan(String value) {
            addCriterion("approver >", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverGreaterThanOrEqualTo(String value) {
            addCriterion("approver >=", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverLessThan(String value) {
            addCriterion("approver <", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverLessThanOrEqualTo(String value) {
            addCriterion("approver <=", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverLike(String value) {
            addCriterion("approver like", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverNotLike(String value) {
            addCriterion("approver not like", value, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverIn(List<String> values) {
            addCriterion("approver in", values, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverNotIn(List<String> values) {
            addCriterion("approver not in", values, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverBetween(String value1, String value2) {
            addCriterion("approver between", value1, value2, "approver");
            return (Criteria) this;
        }

        public Criteria andApproverNotBetween(String value1, String value2) {
            addCriterion("approver not between", value1, value2, "approver");
            return (Criteria) this;
        }

        public Criteria andDepositPersonIsNull() {
            addCriterion("deposit_person is null");
            return (Criteria) this;
        }

        public Criteria andDepositPersonIsNotNull() {
            addCriterion("deposit_person is not null");
            return (Criteria) this;
        }

        public Criteria andDepositPersonEqualTo(String value) {
            addCriterion("deposit_person =", value, "depositPerson");
            return (Criteria) this;
        }

        public Criteria andDepositPersonNotEqualTo(String value) {
            addCriterion("deposit_person <>", value, "depositPerson");
            return (Criteria) this;
        }

        public Criteria andDepositPersonGreaterThan(String value) {
            addCriterion("deposit_person >", value, "depositPerson");
            return (Criteria) this;
        }

        public Criteria andDepositPersonGreaterThanOrEqualTo(String value) {
            addCriterion("deposit_person >=", value, "depositPerson");
            return (Criteria) this;
        }

        public Criteria andDepositPersonLessThan(String value) {
            addCriterion("deposit_person <", value, "depositPerson");
            return (Criteria) this;
        }

        public Criteria andDepositPersonLessThanOrEqualTo(String value) {
            addCriterion("deposit_person <=", value, "depositPerson");
            return (Criteria) this;
        }

        public Criteria andDepositPersonLike(String value) {
            addCriterion("deposit_person like", value, "depositPerson");
            return (Criteria) this;
        }

        public Criteria andDepositPersonNotLike(String value) {
            addCriterion("deposit_person not like", value, "depositPerson");
            return (Criteria) this;
        }

        public Criteria andDepositPersonIn(List<String> values) {
            addCriterion("deposit_person in", values, "depositPerson");
            return (Criteria) this;
        }

        public Criteria andDepositPersonNotIn(List<String> values) {
            addCriterion("deposit_person not in", values, "depositPerson");
            return (Criteria) this;
        }

        public Criteria andDepositPersonBetween(String value1, String value2) {
            addCriterion("deposit_person between", value1, value2, "depositPerson");
            return (Criteria) this;
        }

        public Criteria andDepositPersonNotBetween(String value1, String value2) {
            addCriterion("deposit_person not between", value1, value2, "depositPerson");
            return (Criteria) this;
        }

        public Criteria andRedactPersonIsNull() {
            addCriterion("redact_person is null");
            return (Criteria) this;
        }

        public Criteria andRedactPersonIsNotNull() {
            addCriterion("redact_person is not null");
            return (Criteria) this;
        }

        public Criteria andRedactPersonEqualTo(String value) {
            addCriterion("redact_person =", value, "redactPerson");
            return (Criteria) this;
        }

        public Criteria andRedactPersonNotEqualTo(String value) {
            addCriterion("redact_person <>", value, "redactPerson");
            return (Criteria) this;
        }

        public Criteria andRedactPersonGreaterThan(String value) {
            addCriterion("redact_person >", value, "redactPerson");
            return (Criteria) this;
        }

        public Criteria andRedactPersonGreaterThanOrEqualTo(String value) {
            addCriterion("redact_person >=", value, "redactPerson");
            return (Criteria) this;
        }

        public Criteria andRedactPersonLessThan(String value) {
            addCriterion("redact_person <", value, "redactPerson");
            return (Criteria) this;
        }

        public Criteria andRedactPersonLessThanOrEqualTo(String value) {
            addCriterion("redact_person <=", value, "redactPerson");
            return (Criteria) this;
        }

        public Criteria andRedactPersonLike(String value) {
            addCriterion("redact_person like", value, "redactPerson");
            return (Criteria) this;
        }

        public Criteria andRedactPersonNotLike(String value) {
            addCriterion("redact_person not like", value, "redactPerson");
            return (Criteria) this;
        }

        public Criteria andRedactPersonIn(List<String> values) {
            addCriterion("redact_person in", values, "redactPerson");
            return (Criteria) this;
        }

        public Criteria andRedactPersonNotIn(List<String> values) {
            addCriterion("redact_person not in", values, "redactPerson");
            return (Criteria) this;
        }

        public Criteria andRedactPersonBetween(String value1, String value2) {
            addCriterion("redact_person between", value1, value2, "redactPerson");
            return (Criteria) this;
        }

        public Criteria andRedactPersonNotBetween(String value1, String value2) {
            addCriterion("redact_person not between", value1, value2, "redactPerson");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateIsNull() {
            addCriterion("pigeonhole_date is null");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateIsNotNull() {
            addCriterion("pigeonhole_date is not null");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateEqualTo(String value) {
            addCriterion("pigeonhole_date =", value, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateNotEqualTo(String value) {
            addCriterion("pigeonhole_date <>", value, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateGreaterThan(String value) {
            addCriterion("pigeonhole_date >", value, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateGreaterThanOrEqualTo(String value) {
            addCriterion("pigeonhole_date >=", value, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateLessThan(String value) {
            addCriterion("pigeonhole_date <", value, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateLessThanOrEqualTo(String value) {
            addCriterion("pigeonhole_date <=", value, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateLike(String value) {
            addCriterion("pigeonhole_date like", value, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateNotLike(String value) {
            addCriterion("pigeonhole_date not like", value, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateIn(List<String> values) {
            addCriterion("pigeonhole_date in", values, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateNotIn(List<String> values) {
            addCriterion("pigeonhole_date not in", values, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateBetween(String value1, String value2) {
            addCriterion("pigeonhole_date between", value1, value2, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateNotBetween(String value1, String value2) {
            addCriterion("pigeonhole_date not between", value1, value2, "pigeonholeDate");
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