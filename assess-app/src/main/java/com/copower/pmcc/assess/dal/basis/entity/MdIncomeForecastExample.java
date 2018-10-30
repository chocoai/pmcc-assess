package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdIncomeForecastExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MdIncomeForecastExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andInitialAmountIsNull() {
            addCriterion("initial_amount is null");
            return (Criteria) this;
        }

        public Criteria andInitialAmountIsNotNull() {
            addCriterion("initial_amount is not null");
            return (Criteria) this;
        }

        public Criteria andInitialAmountEqualTo(BigDecimal value) {
            addCriterion("initial_amount =", value, "initialAmount");
            return (Criteria) this;
        }

        public Criteria andInitialAmountNotEqualTo(BigDecimal value) {
            addCriterion("initial_amount <>", value, "initialAmount");
            return (Criteria) this;
        }

        public Criteria andInitialAmountGreaterThan(BigDecimal value) {
            addCriterion("initial_amount >", value, "initialAmount");
            return (Criteria) this;
        }

        public Criteria andInitialAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("initial_amount >=", value, "initialAmount");
            return (Criteria) this;
        }

        public Criteria andInitialAmountLessThan(BigDecimal value) {
            addCriterion("initial_amount <", value, "initialAmount");
            return (Criteria) this;
        }

        public Criteria andInitialAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("initial_amount <=", value, "initialAmount");
            return (Criteria) this;
        }

        public Criteria andInitialAmountIn(List<BigDecimal> values) {
            addCriterion("initial_amount in", values, "initialAmount");
            return (Criteria) this;
        }

        public Criteria andInitialAmountNotIn(List<BigDecimal> values) {
            addCriterion("initial_amount not in", values, "initialAmount");
            return (Criteria) this;
        }

        public Criteria andInitialAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("initial_amount between", value1, value2, "initialAmount");
            return (Criteria) this;
        }

        public Criteria andInitialAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("initial_amount not between", value1, value2, "initialAmount");
            return (Criteria) this;
        }

        public Criteria andOperatingCostIsNull() {
            addCriterion("operating_cost is null");
            return (Criteria) this;
        }

        public Criteria andOperatingCostIsNotNull() {
            addCriterion("operating_cost is not null");
            return (Criteria) this;
        }

        public Criteria andOperatingCostEqualTo(BigDecimal value) {
            addCriterion("operating_cost =", value, "operatingCost");
            return (Criteria) this;
        }

        public Criteria andOperatingCostNotEqualTo(BigDecimal value) {
            addCriterion("operating_cost <>", value, "operatingCost");
            return (Criteria) this;
        }

        public Criteria andOperatingCostGreaterThan(BigDecimal value) {
            addCriterion("operating_cost >", value, "operatingCost");
            return (Criteria) this;
        }

        public Criteria andOperatingCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("operating_cost >=", value, "operatingCost");
            return (Criteria) this;
        }

        public Criteria andOperatingCostLessThan(BigDecimal value) {
            addCriterion("operating_cost <", value, "operatingCost");
            return (Criteria) this;
        }

        public Criteria andOperatingCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("operating_cost <=", value, "operatingCost");
            return (Criteria) this;
        }

        public Criteria andOperatingCostIn(List<BigDecimal> values) {
            addCriterion("operating_cost in", values, "operatingCost");
            return (Criteria) this;
        }

        public Criteria andOperatingCostNotIn(List<BigDecimal> values) {
            addCriterion("operating_cost not in", values, "operatingCost");
            return (Criteria) this;
        }

        public Criteria andOperatingCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("operating_cost between", value1, value2, "operatingCost");
            return (Criteria) this;
        }

        public Criteria andOperatingCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("operating_cost not between", value1, value2, "operatingCost");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesIsNull() {
            addCriterion("operating_expenses is null");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesIsNotNull() {
            addCriterion("operating_expenses is not null");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesEqualTo(BigDecimal value) {
            addCriterion("operating_expenses =", value, "operatingExpenses");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesNotEqualTo(BigDecimal value) {
            addCriterion("operating_expenses <>", value, "operatingExpenses");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesGreaterThan(BigDecimal value) {
            addCriterion("operating_expenses >", value, "operatingExpenses");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("operating_expenses >=", value, "operatingExpenses");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesLessThan(BigDecimal value) {
            addCriterion("operating_expenses <", value, "operatingExpenses");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesLessThanOrEqualTo(BigDecimal value) {
            addCriterion("operating_expenses <=", value, "operatingExpenses");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesIn(List<BigDecimal> values) {
            addCriterion("operating_expenses in", values, "operatingExpenses");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesNotIn(List<BigDecimal> values) {
            addCriterion("operating_expenses not in", values, "operatingExpenses");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("operating_expenses between", value1, value2, "operatingExpenses");
            return (Criteria) this;
        }

        public Criteria andOperatingExpensesNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("operating_expenses not between", value1, value2, "operatingExpenses");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxIsNull() {
            addCriterion("operating_tax is null");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxIsNotNull() {
            addCriterion("operating_tax is not null");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxEqualTo(BigDecimal value) {
            addCriterion("operating_tax =", value, "operatingTax");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxNotEqualTo(BigDecimal value) {
            addCriterion("operating_tax <>", value, "operatingTax");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxGreaterThan(BigDecimal value) {
            addCriterion("operating_tax >", value, "operatingTax");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("operating_tax >=", value, "operatingTax");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxLessThan(BigDecimal value) {
            addCriterion("operating_tax <", value, "operatingTax");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("operating_tax <=", value, "operatingTax");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxIn(List<BigDecimal> values) {
            addCriterion("operating_tax in", values, "operatingTax");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxNotIn(List<BigDecimal> values) {
            addCriterion("operating_tax not in", values, "operatingTax");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("operating_tax between", value1, value2, "operatingTax");
            return (Criteria) this;
        }

        public Criteria andOperatingTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("operating_tax not between", value1, value2, "operatingTax");
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

        public Criteria andManagementCostEqualTo(BigDecimal value) {
            addCriterion("management_cost =", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostNotEqualTo(BigDecimal value) {
            addCriterion("management_cost <>", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostGreaterThan(BigDecimal value) {
            addCriterion("management_cost >", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("management_cost >=", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostLessThan(BigDecimal value) {
            addCriterion("management_cost <", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("management_cost <=", value, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostIn(List<BigDecimal> values) {
            addCriterion("management_cost in", values, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostNotIn(List<BigDecimal> values) {
            addCriterion("management_cost not in", values, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("management_cost between", value1, value2, "managementCost");
            return (Criteria) this;
        }

        public Criteria andManagementCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("management_cost not between", value1, value2, "managementCost");
            return (Criteria) this;
        }

        public Criteria andFinancialCostIsNull() {
            addCriterion("financial_cost is null");
            return (Criteria) this;
        }

        public Criteria andFinancialCostIsNotNull() {
            addCriterion("financial_cost is not null");
            return (Criteria) this;
        }

        public Criteria andFinancialCostEqualTo(BigDecimal value) {
            addCriterion("financial_cost =", value, "financialCost");
            return (Criteria) this;
        }

        public Criteria andFinancialCostNotEqualTo(BigDecimal value) {
            addCriterion("financial_cost <>", value, "financialCost");
            return (Criteria) this;
        }

        public Criteria andFinancialCostGreaterThan(BigDecimal value) {
            addCriterion("financial_cost >", value, "financialCost");
            return (Criteria) this;
        }

        public Criteria andFinancialCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("financial_cost >=", value, "financialCost");
            return (Criteria) this;
        }

        public Criteria andFinancialCostLessThan(BigDecimal value) {
            addCriterion("financial_cost <", value, "financialCost");
            return (Criteria) this;
        }

        public Criteria andFinancialCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("financial_cost <=", value, "financialCost");
            return (Criteria) this;
        }

        public Criteria andFinancialCostIn(List<BigDecimal> values) {
            addCriterion("financial_cost in", values, "financialCost");
            return (Criteria) this;
        }

        public Criteria andFinancialCostNotIn(List<BigDecimal> values) {
            addCriterion("financial_cost not in", values, "financialCost");
            return (Criteria) this;
        }

        public Criteria andFinancialCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("financial_cost between", value1, value2, "financialCost");
            return (Criteria) this;
        }

        public Criteria andFinancialCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("financial_cost not between", value1, value2, "financialCost");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitIsNull() {
            addCriterion("operating_profit is null");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitIsNotNull() {
            addCriterion("operating_profit is not null");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitEqualTo(BigDecimal value) {
            addCriterion("operating_profit =", value, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitNotEqualTo(BigDecimal value) {
            addCriterion("operating_profit <>", value, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitGreaterThan(BigDecimal value) {
            addCriterion("operating_profit >", value, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("operating_profit >=", value, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitLessThan(BigDecimal value) {
            addCriterion("operating_profit <", value, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("operating_profit <=", value, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitIn(List<BigDecimal> values) {
            addCriterion("operating_profit in", values, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitNotIn(List<BigDecimal> values) {
            addCriterion("operating_profit not in", values, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("operating_profit between", value1, value2, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andOperatingProfitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("operating_profit not between", value1, value2, "operatingProfit");
            return (Criteria) this;
        }

        public Criteria andExcessProfitIsNull() {
            addCriterion("excess_profit is null");
            return (Criteria) this;
        }

        public Criteria andExcessProfitIsNotNull() {
            addCriterion("excess_profit is not null");
            return (Criteria) this;
        }

        public Criteria andExcessProfitEqualTo(BigDecimal value) {
            addCriterion("excess_profit =", value, "excessProfit");
            return (Criteria) this;
        }

        public Criteria andExcessProfitNotEqualTo(BigDecimal value) {
            addCriterion("excess_profit <>", value, "excessProfit");
            return (Criteria) this;
        }

        public Criteria andExcessProfitGreaterThan(BigDecimal value) {
            addCriterion("excess_profit >", value, "excessProfit");
            return (Criteria) this;
        }

        public Criteria andExcessProfitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("excess_profit >=", value, "excessProfit");
            return (Criteria) this;
        }

        public Criteria andExcessProfitLessThan(BigDecimal value) {
            addCriterion("excess_profit <", value, "excessProfit");
            return (Criteria) this;
        }

        public Criteria andExcessProfitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("excess_profit <=", value, "excessProfit");
            return (Criteria) this;
        }

        public Criteria andExcessProfitIn(List<BigDecimal> values) {
            addCriterion("excess_profit in", values, "excessProfit");
            return (Criteria) this;
        }

        public Criteria andExcessProfitNotIn(List<BigDecimal> values) {
            addCriterion("excess_profit not in", values, "excessProfit");
            return (Criteria) this;
        }

        public Criteria andExcessProfitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("excess_profit between", value1, value2, "excessProfit");
            return (Criteria) this;
        }

        public Criteria andExcessProfitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("excess_profit not between", value1, value2, "excessProfit");
            return (Criteria) this;
        }

        public Criteria andGrowthRateIsNull() {
            addCriterion("growth_rate is null");
            return (Criteria) this;
        }

        public Criteria andGrowthRateIsNotNull() {
            addCriterion("growth_rate is not null");
            return (Criteria) this;
        }

        public Criteria andGrowthRateEqualTo(BigDecimal value) {
            addCriterion("growth_rate =", value, "growthRate");
            return (Criteria) this;
        }

        public Criteria andGrowthRateNotEqualTo(BigDecimal value) {
            addCriterion("growth_rate <>", value, "growthRate");
            return (Criteria) this;
        }

        public Criteria andGrowthRateGreaterThan(BigDecimal value) {
            addCriterion("growth_rate >", value, "growthRate");
            return (Criteria) this;
        }

        public Criteria andGrowthRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("growth_rate >=", value, "growthRate");
            return (Criteria) this;
        }

        public Criteria andGrowthRateLessThan(BigDecimal value) {
            addCriterion("growth_rate <", value, "growthRate");
            return (Criteria) this;
        }

        public Criteria andGrowthRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("growth_rate <=", value, "growthRate");
            return (Criteria) this;
        }

        public Criteria andGrowthRateIn(List<BigDecimal> values) {
            addCriterion("growth_rate in", values, "growthRate");
            return (Criteria) this;
        }

        public Criteria andGrowthRateNotIn(List<BigDecimal> values) {
            addCriterion("growth_rate not in", values, "growthRate");
            return (Criteria) this;
        }

        public Criteria andGrowthRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("growth_rate between", value1, value2, "growthRate");
            return (Criteria) this;
        }

        public Criteria andGrowthRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("growth_rate not between", value1, value2, "growthRate");
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