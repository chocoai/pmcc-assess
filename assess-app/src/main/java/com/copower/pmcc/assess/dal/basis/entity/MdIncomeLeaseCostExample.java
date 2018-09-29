package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdIncomeLeaseCostExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MdIncomeLeaseCostExample() {
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

        public Criteria andIncomeIdIsNull() {
            addCriterion("income_id is null");
            return (Criteria) this;
        }

        public Criteria andIncomeIdIsNotNull() {
            addCriterion("income_id is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeIdEqualTo(Integer value) {
            addCriterion("income_id =", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdNotEqualTo(Integer value) {
            addCriterion("income_id <>", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdGreaterThan(Integer value) {
            addCriterion("income_id >", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("income_id >=", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdLessThan(Integer value) {
            addCriterion("income_id <", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdLessThanOrEqualTo(Integer value) {
            addCriterion("income_id <=", value, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdIn(List<Integer> values) {
            addCriterion("income_id in", values, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdNotIn(List<Integer> values) {
            addCriterion("income_id not in", values, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdBetween(Integer value1, Integer value2) {
            addCriterion("income_id between", value1, value2, "incomeId");
            return (Criteria) this;
        }

        public Criteria andIncomeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("income_id not between", value1, value2, "incomeId");
            return (Criteria) this;
        }

        public Criteria andSectionIdIsNull() {
            addCriterion("section_id is null");
            return (Criteria) this;
        }

        public Criteria andSectionIdIsNotNull() {
            addCriterion("section_id is not null");
            return (Criteria) this;
        }

        public Criteria andSectionIdEqualTo(Integer value) {
            addCriterion("section_id =", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdNotEqualTo(Integer value) {
            addCriterion("section_id <>", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdGreaterThan(Integer value) {
            addCriterion("section_id >", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("section_id >=", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdLessThan(Integer value) {
            addCriterion("section_id <", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdLessThanOrEqualTo(Integer value) {
            addCriterion("section_id <=", value, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdIn(List<Integer> values) {
            addCriterion("section_id in", values, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdNotIn(List<Integer> values) {
            addCriterion("section_id not in", values, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdBetween(Integer value1, Integer value2) {
            addCriterion("section_id between", value1, value2, "sectionId");
            return (Criteria) this;
        }

        public Criteria andSectionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("section_id not between", value1, value2, "sectionId");
            return (Criteria) this;
        }

        public Criteria andReplacementValueIsNull() {
            addCriterion("replacement_value is null");
            return (Criteria) this;
        }

        public Criteria andReplacementValueIsNotNull() {
            addCriterion("replacement_value is not null");
            return (Criteria) this;
        }

        public Criteria andReplacementValueEqualTo(BigDecimal value) {
            addCriterion("replacement_value =", value, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueNotEqualTo(BigDecimal value) {
            addCriterion("replacement_value <>", value, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueGreaterThan(BigDecimal value) {
            addCriterion("replacement_value >", value, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("replacement_value >=", value, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueLessThan(BigDecimal value) {
            addCriterion("replacement_value <", value, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("replacement_value <=", value, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueIn(List<BigDecimal> values) {
            addCriterion("replacement_value in", values, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueNotIn(List<BigDecimal> values) {
            addCriterion("replacement_value not in", values, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("replacement_value between", value1, value2, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andReplacementValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("replacement_value not between", value1, value2, "replacementValue");
            return (Criteria) this;
        }

        public Criteria andManagementCostIsNull() {
            addCriterion("management_cost is null");
            return (Criteria) this;
        }

        public Criteria andManagementCostIsNotNull() {
            addCriterion("management_cost is not null");
            return (Criteria) this;
        }

        public Criteria andManagementCostEqualTo(String value) {
            addCriterion("management_cost =", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostNotEqualTo(String value) {
            addCriterion("management_cost <>", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostGreaterThan(String value) {
            addCriterion("management_cost >", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostGreaterThanOrEqualTo(String value) {
            addCriterion("management_cost >=", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostLessThan(String value) {
            addCriterion("management_cost <", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostLessThanOrEqualTo(String value) {
            addCriterion("management_cost <=", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostLike(String value) {
            addCriterion("management_cost like", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostNotLike(String value) {
            addCriterion("management_cost not like", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostIn(List<String> values) {
            addCriterion("management_cost in", values, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostNotIn(List<String> values) {
            addCriterion("management_cost not in", values, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostBetween(String value1, String value2) {
            addCriterion("management_cost between", value1, value2, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostNotBetween(String value1, String value2) {
            addCriterion("management_cost not between", value1, value2, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioIsNull() {
            addCriterion("management_cost_ratio is null");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioIsNotNull() {
            addCriterion("management_cost_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioEqualTo(BigDecimal value) {
            addCriterion("management_cost_ratio =", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioNotEqualTo(BigDecimal value) {
            addCriterion("management_cost_ratio <>", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioGreaterThan(BigDecimal value) {
            addCriterion("management_cost_ratio >", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("management_cost_ratio >=", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioLessThan(BigDecimal value) {
            addCriterion("management_cost_ratio <", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("management_cost_ratio <=", value, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioIn(List<BigDecimal> values) {
            addCriterion("management_cost_ratio in", values, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioNotIn(List<BigDecimal> values) {
            addCriterion("management_cost_ratio not in", values, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("management_cost_ratio between", value1, value2, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andManagementCostRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("management_cost_ratio not between", value1, value2, "managementCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIsNull() {
            addCriterion("maintenance is null");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIsNotNull() {
            addCriterion("maintenance is not null");
            return (Criteria) this;
        }

        public Criteria andMaintenanceEqualTo(String value) {
            addCriterion("maintenance =", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceNotEqualTo(String value) {
            addCriterion("maintenance <>", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceGreaterThan(String value) {
            addCriterion("maintenance >", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceGreaterThanOrEqualTo(String value) {
            addCriterion("maintenance >=", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceLessThan(String value) {
            addCriterion("maintenance <", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceLessThanOrEqualTo(String value) {
            addCriterion("maintenance <=", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceLike(String value) {
            addCriterion("maintenance like", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceNotLike(String value) {
            addCriterion("maintenance not like", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIn(List<String> values) {
            addCriterion("maintenance in", values, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceNotIn(List<String> values) {
            addCriterion("maintenance not in", values, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceBetween(String value1, String value2) {
            addCriterion("maintenance between", value1, value2, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceNotBetween(String value1, String value2) {
            addCriterion("maintenance not between", value1, value2, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioIsNull() {
            addCriterion("maintenance_cost_ratio is null");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioIsNotNull() {
            addCriterion("maintenance_cost_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioEqualTo(BigDecimal value) {
            addCriterion("maintenance_cost_ratio =", value, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioNotEqualTo(BigDecimal value) {
            addCriterion("maintenance_cost_ratio <>", value, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioGreaterThan(BigDecimal value) {
            addCriterion("maintenance_cost_ratio >", value, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("maintenance_cost_ratio >=", value, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioLessThan(BigDecimal value) {
            addCriterion("maintenance_cost_ratio <", value, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("maintenance_cost_ratio <=", value, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioIn(List<BigDecimal> values) {
            addCriterion("maintenance_cost_ratio in", values, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioNotIn(List<BigDecimal> values) {
            addCriterion("maintenance_cost_ratio not in", values, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("maintenance_cost_ratio between", value1, value2, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andMaintenanceCostRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("maintenance_cost_ratio not between", value1, value2, "maintenanceCostRatio");
            return (Criteria) this;
        }

        public Criteria andAdditionalIsNull() {
            addCriterion("additional is null");
            return (Criteria) this;
        }

        public Criteria andAdditionalIsNotNull() {
            addCriterion("additional is not null");
            return (Criteria) this;
        }

        public Criteria andAdditionalEqualTo(String value) {
            addCriterion("additional =", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalNotEqualTo(String value) {
            addCriterion("additional <>", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalGreaterThan(String value) {
            addCriterion("additional >", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalGreaterThanOrEqualTo(String value) {
            addCriterion("additional >=", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalLessThan(String value) {
            addCriterion("additional <", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalLessThanOrEqualTo(String value) {
            addCriterion("additional <=", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalLike(String value) {
            addCriterion("additional like", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalNotLike(String value) {
            addCriterion("additional not like", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalIn(List<String> values) {
            addCriterion("additional in", values, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalNotIn(List<String> values) {
            addCriterion("additional not in", values, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalBetween(String value1, String value2) {
            addCriterion("additional between", value1, value2, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalNotBetween(String value1, String value2) {
            addCriterion("additional not between", value1, value2, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalRatioIsNull() {
            addCriterion("additional_ratio is null");
            return (Criteria) this;
        }

        public Criteria andAdditionalRatioIsNotNull() {
            addCriterion("additional_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andAdditionalRatioEqualTo(BigDecimal value) {
            addCriterion("additional_ratio =", value, "additionalRatio");
            return (Criteria) this;
        }

        public Criteria andAdditionalRatioNotEqualTo(BigDecimal value) {
            addCriterion("additional_ratio <>", value, "additionalRatio");
            return (Criteria) this;
        }

        public Criteria andAdditionalRatioGreaterThan(BigDecimal value) {
            addCriterion("additional_ratio >", value, "additionalRatio");
            return (Criteria) this;
        }

        public Criteria andAdditionalRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("additional_ratio >=", value, "additionalRatio");
            return (Criteria) this;
        }

        public Criteria andAdditionalRatioLessThan(BigDecimal value) {
            addCriterion("additional_ratio <", value, "additionalRatio");
            return (Criteria) this;
        }

        public Criteria andAdditionalRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("additional_ratio <=", value, "additionalRatio");
            return (Criteria) this;
        }

        public Criteria andAdditionalRatioIn(List<BigDecimal> values) {
            addCriterion("additional_ratio in", values, "additionalRatio");
            return (Criteria) this;
        }

        public Criteria andAdditionalRatioNotIn(List<BigDecimal> values) {
            addCriterion("additional_ratio not in", values, "additionalRatio");
            return (Criteria) this;
        }

        public Criteria andAdditionalRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("additional_ratio between", value1, value2, "additionalRatio");
            return (Criteria) this;
        }

        public Criteria andAdditionalRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("additional_ratio not between", value1, value2, "additionalRatio");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumIsNull() {
            addCriterion("insurance_premium is null");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumIsNotNull() {
            addCriterion("insurance_premium is not null");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumEqualTo(String value) {
            addCriterion("insurance_premium =", value, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumNotEqualTo(String value) {
            addCriterion("insurance_premium <>", value, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumGreaterThan(String value) {
            addCriterion("insurance_premium >", value, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumGreaterThanOrEqualTo(String value) {
            addCriterion("insurance_premium >=", value, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumLessThan(String value) {
            addCriterion("insurance_premium <", value, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumLessThanOrEqualTo(String value) {
            addCriterion("insurance_premium <=", value, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumLike(String value) {
            addCriterion("insurance_premium like", value, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumNotLike(String value) {
            addCriterion("insurance_premium not like", value, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumIn(List<String> values) {
            addCriterion("insurance_premium in", values, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumNotIn(List<String> values) {
            addCriterion("insurance_premium not in", values, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumBetween(String value1, String value2) {
            addCriterion("insurance_premium between", value1, value2, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumNotBetween(String value1, String value2) {
            addCriterion("insurance_premium not between", value1, value2, "insurancePremium");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumRatioIsNull() {
            addCriterion("insurance_premium_ratio is null");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumRatioIsNotNull() {
            addCriterion("insurance_premium_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumRatioEqualTo(BigDecimal value) {
            addCriterion("insurance_premium_ratio =", value, "insurancePremiumRatio");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumRatioNotEqualTo(BigDecimal value) {
            addCriterion("insurance_premium_ratio <>", value, "insurancePremiumRatio");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumRatioGreaterThan(BigDecimal value) {
            addCriterion("insurance_premium_ratio >", value, "insurancePremiumRatio");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("insurance_premium_ratio >=", value, "insurancePremiumRatio");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumRatioLessThan(BigDecimal value) {
            addCriterion("insurance_premium_ratio <", value, "insurancePremiumRatio");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("insurance_premium_ratio <=", value, "insurancePremiumRatio");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumRatioIn(List<BigDecimal> values) {
            addCriterion("insurance_premium_ratio in", values, "insurancePremiumRatio");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumRatioNotIn(List<BigDecimal> values) {
            addCriterion("insurance_premium_ratio not in", values, "insurancePremiumRatio");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("insurance_premium_ratio between", value1, value2, "insurancePremiumRatio");
            return (Criteria) this;
        }

        public Criteria andInsurancePremiumRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("insurance_premium_ratio not between", value1, value2, "insurancePremiumRatio");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxIsNull() {
            addCriterion("land_use_tax is null");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxIsNotNull() {
            addCriterion("land_use_tax is not null");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxEqualTo(String value) {
            addCriterion("land_use_tax =", value, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxNotEqualTo(String value) {
            addCriterion("land_use_tax <>", value, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxGreaterThan(String value) {
            addCriterion("land_use_tax >", value, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxGreaterThanOrEqualTo(String value) {
            addCriterion("land_use_tax >=", value, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxLessThan(String value) {
            addCriterion("land_use_tax <", value, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxLessThanOrEqualTo(String value) {
            addCriterion("land_use_tax <=", value, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxLike(String value) {
            addCriterion("land_use_tax like", value, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxNotLike(String value) {
            addCriterion("land_use_tax not like", value, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxIn(List<String> values) {
            addCriterion("land_use_tax in", values, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxNotIn(List<String> values) {
            addCriterion("land_use_tax not in", values, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxBetween(String value1, String value2) {
            addCriterion("land_use_tax between", value1, value2, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andLandUseTaxNotBetween(String value1, String value2) {
            addCriterion("land_use_tax not between", value1, value2, "landUseTax");
            return (Criteria) this;
        }

        public Criteria andUseTaxParameterIsNull() {
            addCriterion("use_tax_parameter is null");
            return (Criteria) this;
        }

        public Criteria andUseTaxParameterIsNotNull() {
            addCriterion("use_tax_parameter is not null");
            return (Criteria) this;
        }

        public Criteria andUseTaxParameterEqualTo(String value) {
            addCriterion("use_tax_parameter =", value, "useTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUseTaxParameterNotEqualTo(String value) {
            addCriterion("use_tax_parameter <>", value, "useTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUseTaxParameterGreaterThan(String value) {
            addCriterion("use_tax_parameter >", value, "useTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUseTaxParameterGreaterThanOrEqualTo(String value) {
            addCriterion("use_tax_parameter >=", value, "useTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUseTaxParameterLessThan(String value) {
            addCriterion("use_tax_parameter <", value, "useTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUseTaxParameterLessThanOrEqualTo(String value) {
            addCriterion("use_tax_parameter <=", value, "useTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUseTaxParameterLike(String value) {
            addCriterion("use_tax_parameter like", value, "useTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUseTaxParameterNotLike(String value) {
            addCriterion("use_tax_parameter not like", value, "useTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUseTaxParameterIn(List<String> values) {
            addCriterion("use_tax_parameter in", values, "useTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUseTaxParameterNotIn(List<String> values) {
            addCriterion("use_tax_parameter not in", values, "useTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUseTaxParameterBetween(String value1, String value2) {
            addCriterion("use_tax_parameter between", value1, value2, "useTaxParameter");
            return (Criteria) this;
        }

        public Criteria andUseTaxParameterNotBetween(String value1, String value2) {
            addCriterion("use_tax_parameter not between", value1, value2, "useTaxParameter");
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