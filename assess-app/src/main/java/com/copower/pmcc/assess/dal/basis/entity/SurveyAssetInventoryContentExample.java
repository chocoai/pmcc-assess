package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SurveyAssetInventoryContentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SurveyAssetInventoryContentExample() {
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

        public Criteria andInventoryContentIsNull() {
            addCriterion("inventory_content is null");
            return (Criteria) this;
        }

        public Criteria andInventoryContentIsNotNull() {
            addCriterion("inventory_content is not null");
            return (Criteria) this;
        }

        public Criteria andInventoryContentEqualTo(Integer value) {
            addCriterion("inventory_content =", value, "inventoryContent");
            return (Criteria) this;
        }

        public Criteria andInventoryContentNotEqualTo(Integer value) {
            addCriterion("inventory_content <>", value, "inventoryContent");
            return (Criteria) this;
        }

        public Criteria andInventoryContentGreaterThan(Integer value) {
            addCriterion("inventory_content >", value, "inventoryContent");
            return (Criteria) this;
        }

        public Criteria andInventoryContentGreaterThanOrEqualTo(Integer value) {
            addCriterion("inventory_content >=", value, "inventoryContent");
            return (Criteria) this;
        }

        public Criteria andInventoryContentLessThan(Integer value) {
            addCriterion("inventory_content <", value, "inventoryContent");
            return (Criteria) this;
        }

        public Criteria andInventoryContentLessThanOrEqualTo(Integer value) {
            addCriterion("inventory_content <=", value, "inventoryContent");
            return (Criteria) this;
        }

        public Criteria andInventoryContentIn(List<Integer> values) {
            addCriterion("inventory_content in", values, "inventoryContent");
            return (Criteria) this;
        }

        public Criteria andInventoryContentNotIn(List<Integer> values) {
            addCriterion("inventory_content not in", values, "inventoryContent");
            return (Criteria) this;
        }

        public Criteria andInventoryContentBetween(Integer value1, Integer value2) {
            addCriterion("inventory_content between", value1, value2, "inventoryContent");
            return (Criteria) this;
        }

        public Criteria andInventoryContentNotBetween(Integer value1, Integer value2) {
            addCriterion("inventory_content not between", value1, value2, "inventoryContent");
            return (Criteria) this;
        }

        public Criteria andAreConsistentIsNull() {
            addCriterion("are_consistent is null");
            return (Criteria) this;
        }

        public Criteria andAreConsistentIsNotNull() {
            addCriterion("are_consistent is not null");
            return (Criteria) this;
        }

        public Criteria andAreConsistentEqualTo(String value) {
            addCriterion("are_consistent =", value, "areConsistent");
            return (Criteria) this;
        }

        public Criteria andAreConsistentNotEqualTo(String value) {
            addCriterion("are_consistent <>", value, "areConsistent");
            return (Criteria) this;
        }

        public Criteria andAreConsistentGreaterThan(String value) {
            addCriterion("are_consistent >", value, "areConsistent");
            return (Criteria) this;
        }

        public Criteria andAreConsistentGreaterThanOrEqualTo(String value) {
            addCriterion("are_consistent >=", value, "areConsistent");
            return (Criteria) this;
        }

        public Criteria andAreConsistentLessThan(String value) {
            addCriterion("are_consistent <", value, "areConsistent");
            return (Criteria) this;
        }

        public Criteria andAreConsistentLessThanOrEqualTo(String value) {
            addCriterion("are_consistent <=", value, "areConsistent");
            return (Criteria) this;
        }

        public Criteria andAreConsistentLike(String value) {
            addCriterion("are_consistent like", value, "areConsistent");
            return (Criteria) this;
        }

        public Criteria andAreConsistentNotLike(String value) {
            addCriterion("are_consistent not like", value, "areConsistent");
            return (Criteria) this;
        }

        public Criteria andAreConsistentIn(List<String> values) {
            addCriterion("are_consistent in", values, "areConsistent");
            return (Criteria) this;
        }

        public Criteria andAreConsistentNotIn(List<String> values) {
            addCriterion("are_consistent not in", values, "areConsistent");
            return (Criteria) this;
        }

        public Criteria andAreConsistentBetween(String value1, String value2) {
            addCriterion("are_consistent between", value1, value2, "areConsistent");
            return (Criteria) this;
        }

        public Criteria andAreConsistentNotBetween(String value1, String value2) {
            addCriterion("are_consistent not between", value1, value2, "areConsistent");
            return (Criteria) this;
        }

        public Criteria andRegistrationAddressIsNull() {
            addCriterion("registration_address is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationAddressIsNotNull() {
            addCriterion("registration_address is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationAddressEqualTo(String value) {
            addCriterion("registration_address =", value, "registrationAddress");
            return (Criteria) this;
        }

        public Criteria andRegistrationAddressNotEqualTo(String value) {
            addCriterion("registration_address <>", value, "registrationAddress");
            return (Criteria) this;
        }

        public Criteria andRegistrationAddressGreaterThan(String value) {
            addCriterion("registration_address >", value, "registrationAddress");
            return (Criteria) this;
        }

        public Criteria andRegistrationAddressGreaterThanOrEqualTo(String value) {
            addCriterion("registration_address >=", value, "registrationAddress");
            return (Criteria) this;
        }

        public Criteria andRegistrationAddressLessThan(String value) {
            addCriterion("registration_address <", value, "registrationAddress");
            return (Criteria) this;
        }

        public Criteria andRegistrationAddressLessThanOrEqualTo(String value) {
            addCriterion("registration_address <=", value, "registrationAddress");
            return (Criteria) this;
        }

        public Criteria andRegistrationAddressLike(String value) {
            addCriterion("registration_address like", value, "registrationAddress");
            return (Criteria) this;
        }

        public Criteria andRegistrationAddressNotLike(String value) {
            addCriterion("registration_address not like", value, "registrationAddress");
            return (Criteria) this;
        }

        public Criteria andRegistrationAddressIn(List<String> values) {
            addCriterion("registration_address in", values, "registrationAddress");
            return (Criteria) this;
        }

        public Criteria andRegistrationAddressNotIn(List<String> values) {
            addCriterion("registration_address not in", values, "registrationAddress");
            return (Criteria) this;
        }

        public Criteria andRegistrationAddressBetween(String value1, String value2) {
            addCriterion("registration_address between", value1, value2, "registrationAddress");
            return (Criteria) this;
        }

        public Criteria andRegistrationAddressNotBetween(String value1, String value2) {
            addCriterion("registration_address not between", value1, value2, "registrationAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressIsNull() {
            addCriterion("actual_address is null");
            return (Criteria) this;
        }

        public Criteria andActualAddressIsNotNull() {
            addCriterion("actual_address is not null");
            return (Criteria) this;
        }

        public Criteria andActualAddressEqualTo(String value) {
            addCriterion("actual_address =", value, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressNotEqualTo(String value) {
            addCriterion("actual_address <>", value, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressGreaterThan(String value) {
            addCriterion("actual_address >", value, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressGreaterThanOrEqualTo(String value) {
            addCriterion("actual_address >=", value, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressLessThan(String value) {
            addCriterion("actual_address <", value, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressLessThanOrEqualTo(String value) {
            addCriterion("actual_address <=", value, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressLike(String value) {
            addCriterion("actual_address like", value, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressNotLike(String value) {
            addCriterion("actual_address not like", value, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressIn(List<String> values) {
            addCriterion("actual_address in", values, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressNotIn(List<String> values) {
            addCriterion("actual_address not in", values, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressBetween(String value1, String value2) {
            addCriterion("actual_address between", value1, value2, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andActualAddressNotBetween(String value1, String value2) {
            addCriterion("actual_address not between", value1, value2, "actualAddress");
            return (Criteria) this;
        }

        public Criteria andDifferenceReasonIsNull() {
            addCriterion("difference_reason is null");
            return (Criteria) this;
        }

        public Criteria andDifferenceReasonIsNotNull() {
            addCriterion("difference_reason is not null");
            return (Criteria) this;
        }

        public Criteria andDifferenceReasonEqualTo(String value) {
            addCriterion("difference_reason =", value, "differenceReason");
            return (Criteria) this;
        }

        public Criteria andDifferenceReasonNotEqualTo(String value) {
            addCriterion("difference_reason <>", value, "differenceReason");
            return (Criteria) this;
        }

        public Criteria andDifferenceReasonGreaterThan(String value) {
            addCriterion("difference_reason >", value, "differenceReason");
            return (Criteria) this;
        }

        public Criteria andDifferenceReasonGreaterThanOrEqualTo(String value) {
            addCriterion("difference_reason >=", value, "differenceReason");
            return (Criteria) this;
        }

        public Criteria andDifferenceReasonLessThan(String value) {
            addCriterion("difference_reason <", value, "differenceReason");
            return (Criteria) this;
        }

        public Criteria andDifferenceReasonLessThanOrEqualTo(String value) {
            addCriterion("difference_reason <=", value, "differenceReason");
            return (Criteria) this;
        }

        public Criteria andDifferenceReasonLike(String value) {
            addCriterion("difference_reason like", value, "differenceReason");
            return (Criteria) this;
        }

        public Criteria andDifferenceReasonNotLike(String value) {
            addCriterion("difference_reason not like", value, "differenceReason");
            return (Criteria) this;
        }

        public Criteria andDifferenceReasonIn(List<String> values) {
            addCriterion("difference_reason in", values, "differenceReason");
            return (Criteria) this;
        }

        public Criteria andDifferenceReasonNotIn(List<String> values) {
            addCriterion("difference_reason not in", values, "differenceReason");
            return (Criteria) this;
        }

        public Criteria andDifferenceReasonBetween(String value1, String value2) {
            addCriterion("difference_reason between", value1, value2, "differenceReason");
            return (Criteria) this;
        }

        public Criteria andDifferenceReasonNotBetween(String value1, String value2) {
            addCriterion("difference_reason not between", value1, value2, "differenceReason");
            return (Criteria) this;
        }

        public Criteria andCredentialIsNull() {
            addCriterion("credential is null");
            return (Criteria) this;
        }

        public Criteria andCredentialIsNotNull() {
            addCriterion("credential is not null");
            return (Criteria) this;
        }

        public Criteria andCredentialEqualTo(String value) {
            addCriterion("credential =", value, "credential");
            return (Criteria) this;
        }

        public Criteria andCredentialNotEqualTo(String value) {
            addCriterion("credential <>", value, "credential");
            return (Criteria) this;
        }

        public Criteria andCredentialGreaterThan(String value) {
            addCriterion("credential >", value, "credential");
            return (Criteria) this;
        }

        public Criteria andCredentialGreaterThanOrEqualTo(String value) {
            addCriterion("credential >=", value, "credential");
            return (Criteria) this;
        }

        public Criteria andCredentialLessThan(String value) {
            addCriterion("credential <", value, "credential");
            return (Criteria) this;
        }

        public Criteria andCredentialLessThanOrEqualTo(String value) {
            addCriterion("credential <=", value, "credential");
            return (Criteria) this;
        }

        public Criteria andCredentialLike(String value) {
            addCriterion("credential like", value, "credential");
            return (Criteria) this;
        }

        public Criteria andCredentialNotLike(String value) {
            addCriterion("credential not like", value, "credential");
            return (Criteria) this;
        }

        public Criteria andCredentialIn(List<String> values) {
            addCriterion("credential in", values, "credential");
            return (Criteria) this;
        }

        public Criteria andCredentialNotIn(List<String> values) {
            addCriterion("credential not in", values, "credential");
            return (Criteria) this;
        }

        public Criteria andCredentialBetween(String value1, String value2) {
            addCriterion("credential between", value1, value2, "credential");
            return (Criteria) this;
        }

        public Criteria andCredentialNotBetween(String value1, String value2) {
            addCriterion("credential not between", value1, value2, "credential");
            return (Criteria) this;
        }

        public Criteria andCredentialAccessoryIsNull() {
            addCriterion("credential_accessory is null");
            return (Criteria) this;
        }

        public Criteria andCredentialAccessoryIsNotNull() {
            addCriterion("credential_accessory is not null");
            return (Criteria) this;
        }

        public Criteria andCredentialAccessoryEqualTo(String value) {
            addCriterion("credential_accessory =", value, "credentialAccessory");
            return (Criteria) this;
        }

        public Criteria andCredentialAccessoryNotEqualTo(String value) {
            addCriterion("credential_accessory <>", value, "credentialAccessory");
            return (Criteria) this;
        }

        public Criteria andCredentialAccessoryGreaterThan(String value) {
            addCriterion("credential_accessory >", value, "credentialAccessory");
            return (Criteria) this;
        }

        public Criteria andCredentialAccessoryGreaterThanOrEqualTo(String value) {
            addCriterion("credential_accessory >=", value, "credentialAccessory");
            return (Criteria) this;
        }

        public Criteria andCredentialAccessoryLessThan(String value) {
            addCriterion("credential_accessory <", value, "credentialAccessory");
            return (Criteria) this;
        }

        public Criteria andCredentialAccessoryLessThanOrEqualTo(String value) {
            addCriterion("credential_accessory <=", value, "credentialAccessory");
            return (Criteria) this;
        }

        public Criteria andCredentialAccessoryLike(String value) {
            addCriterion("credential_accessory like", value, "credentialAccessory");
            return (Criteria) this;
        }

        public Criteria andCredentialAccessoryNotLike(String value) {
            addCriterion("credential_accessory not like", value, "credentialAccessory");
            return (Criteria) this;
        }

        public Criteria andCredentialAccessoryIn(List<String> values) {
            addCriterion("credential_accessory in", values, "credentialAccessory");
            return (Criteria) this;
        }

        public Criteria andCredentialAccessoryNotIn(List<String> values) {
            addCriterion("credential_accessory not in", values, "credentialAccessory");
            return (Criteria) this;
        }

        public Criteria andCredentialAccessoryBetween(String value1, String value2) {
            addCriterion("credential_accessory between", value1, value2, "credentialAccessory");
            return (Criteria) this;
        }

        public Criteria andCredentialAccessoryNotBetween(String value1, String value2) {
            addCriterion("credential_accessory not between", value1, value2, "credentialAccessory");
            return (Criteria) this;
        }

        public Criteria andVoucherIsNull() {
            addCriterion("voucher is null");
            return (Criteria) this;
        }

        public Criteria andVoucherIsNotNull() {
            addCriterion("voucher is not null");
            return (Criteria) this;
        }

        public Criteria andVoucherEqualTo(String value) {
            addCriterion("voucher =", value, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherNotEqualTo(String value) {
            addCriterion("voucher <>", value, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherGreaterThan(String value) {
            addCriterion("voucher >", value, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherGreaterThanOrEqualTo(String value) {
            addCriterion("voucher >=", value, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherLessThan(String value) {
            addCriterion("voucher <", value, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherLessThanOrEqualTo(String value) {
            addCriterion("voucher <=", value, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherLike(String value) {
            addCriterion("voucher like", value, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherNotLike(String value) {
            addCriterion("voucher not like", value, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherIn(List<String> values) {
            addCriterion("voucher in", values, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherNotIn(List<String> values) {
            addCriterion("voucher not in", values, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherBetween(String value1, String value2) {
            addCriterion("voucher between", value1, value2, "voucher");
            return (Criteria) this;
        }

        public Criteria andVoucherNotBetween(String value1, String value2) {
            addCriterion("voucher not between", value1, value2, "voucher");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeIsNull() {
            addCriterion("survey_time is null");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeIsNotNull() {
            addCriterion("survey_time is not null");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeEqualTo(Date value) {
            addCriterion("survey_time =", value, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeNotEqualTo(Date value) {
            addCriterion("survey_time <>", value, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeGreaterThan(Date value) {
            addCriterion("survey_time >", value, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("survey_time >=", value, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeLessThan(Date value) {
            addCriterion("survey_time <", value, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeLessThanOrEqualTo(Date value) {
            addCriterion("survey_time <=", value, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeIn(List<Date> values) {
            addCriterion("survey_time in", values, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeNotIn(List<Date> values) {
            addCriterion("survey_time not in", values, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeBetween(Date value1, Date value2) {
            addCriterion("survey_time between", value1, value2, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeNotBetween(Date value1, Date value2) {
            addCriterion("survey_time not between", value1, value2, "surveyTime");
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