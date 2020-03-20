package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectXlxCommissionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectXlxCommissionExample() {
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

        public Criteria andInvoiceNumberIsNull() {
            addCriterion("invoice_number is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberIsNotNull() {
            addCriterion("invoice_number is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberEqualTo(String value) {
            addCriterion("invoice_number =", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberNotEqualTo(String value) {
            addCriterion("invoice_number <>", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberGreaterThan(String value) {
            addCriterion("invoice_number >", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_number >=", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberLessThan(String value) {
            addCriterion("invoice_number <", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberLessThanOrEqualTo(String value) {
            addCriterion("invoice_number <=", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberLike(String value) {
            addCriterion("invoice_number like", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberNotLike(String value) {
            addCriterion("invoice_number not like", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberIn(List<String> values) {
            addCriterion("invoice_number in", values, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberNotIn(List<String> values) {
            addCriterion("invoice_number not in", values, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberBetween(String value1, String value2) {
            addCriterion("invoice_number between", value1, value2, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberNotBetween(String value1, String value2) {
            addCriterion("invoice_number not between", value1, value2, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceTotalMoneyIsNull() {
            addCriterion("invoice_total_money is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTotalMoneyIsNotNull() {
            addCriterion("invoice_total_money is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTotalMoneyEqualTo(BigDecimal value) {
            addCriterion("invoice_total_money =", value, "invoiceTotalMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceTotalMoneyNotEqualTo(BigDecimal value) {
            addCriterion("invoice_total_money <>", value, "invoiceTotalMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceTotalMoneyGreaterThan(BigDecimal value) {
            addCriterion("invoice_total_money >", value, "invoiceTotalMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceTotalMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("invoice_total_money >=", value, "invoiceTotalMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceTotalMoneyLessThan(BigDecimal value) {
            addCriterion("invoice_total_money <", value, "invoiceTotalMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceTotalMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("invoice_total_money <=", value, "invoiceTotalMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceTotalMoneyIn(List<BigDecimal> values) {
            addCriterion("invoice_total_money in", values, "invoiceTotalMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceTotalMoneyNotIn(List<BigDecimal> values) {
            addCriterion("invoice_total_money not in", values, "invoiceTotalMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceTotalMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("invoice_total_money between", value1, value2, "invoiceTotalMoney");
            return (Criteria) this;
        }

        public Criteria andInvoiceTotalMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("invoice_total_money not between", value1, value2, "invoiceTotalMoney");
            return (Criteria) this;
        }

        public Criteria andProjectMoneyIsNull() {
            addCriterion("project_money is null");
            return (Criteria) this;
        }

        public Criteria andProjectMoneyIsNotNull() {
            addCriterion("project_money is not null");
            return (Criteria) this;
        }

        public Criteria andProjectMoneyEqualTo(BigDecimal value) {
            addCriterion("project_money =", value, "projectMoney");
            return (Criteria) this;
        }

        public Criteria andProjectMoneyNotEqualTo(BigDecimal value) {
            addCriterion("project_money <>", value, "projectMoney");
            return (Criteria) this;
        }

        public Criteria andProjectMoneyGreaterThan(BigDecimal value) {
            addCriterion("project_money >", value, "projectMoney");
            return (Criteria) this;
        }

        public Criteria andProjectMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("project_money >=", value, "projectMoney");
            return (Criteria) this;
        }

        public Criteria andProjectMoneyLessThan(BigDecimal value) {
            addCriterion("project_money <", value, "projectMoney");
            return (Criteria) this;
        }

        public Criteria andProjectMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("project_money <=", value, "projectMoney");
            return (Criteria) this;
        }

        public Criteria andProjectMoneyIn(List<BigDecimal> values) {
            addCriterion("project_money in", values, "projectMoney");
            return (Criteria) this;
        }

        public Criteria andProjectMoneyNotIn(List<BigDecimal> values) {
            addCriterion("project_money not in", values, "projectMoney");
            return (Criteria) this;
        }

        public Criteria andProjectMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("project_money between", value1, value2, "projectMoney");
            return (Criteria) this;
        }

        public Criteria andProjectMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("project_money not between", value1, value2, "projectMoney");
            return (Criteria) this;
        }

        public Criteria andPaymentConfirmationIsNull() {
            addCriterion("payment_confirmation is null");
            return (Criteria) this;
        }

        public Criteria andPaymentConfirmationIsNotNull() {
            addCriterion("payment_confirmation is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentConfirmationEqualTo(String value) {
            addCriterion("payment_confirmation =", value, "paymentConfirmation");
            return (Criteria) this;
        }

        public Criteria andPaymentConfirmationNotEqualTo(String value) {
            addCriterion("payment_confirmation <>", value, "paymentConfirmation");
            return (Criteria) this;
        }

        public Criteria andPaymentConfirmationGreaterThan(String value) {
            addCriterion("payment_confirmation >", value, "paymentConfirmation");
            return (Criteria) this;
        }

        public Criteria andPaymentConfirmationGreaterThanOrEqualTo(String value) {
            addCriterion("payment_confirmation >=", value, "paymentConfirmation");
            return (Criteria) this;
        }

        public Criteria andPaymentConfirmationLessThan(String value) {
            addCriterion("payment_confirmation <", value, "paymentConfirmation");
            return (Criteria) this;
        }

        public Criteria andPaymentConfirmationLessThanOrEqualTo(String value) {
            addCriterion("payment_confirmation <=", value, "paymentConfirmation");
            return (Criteria) this;
        }

        public Criteria andPaymentConfirmationLike(String value) {
            addCriterion("payment_confirmation like", value, "paymentConfirmation");
            return (Criteria) this;
        }

        public Criteria andPaymentConfirmationNotLike(String value) {
            addCriterion("payment_confirmation not like", value, "paymentConfirmation");
            return (Criteria) this;
        }

        public Criteria andPaymentConfirmationIn(List<String> values) {
            addCriterion("payment_confirmation in", values, "paymentConfirmation");
            return (Criteria) this;
        }

        public Criteria andPaymentConfirmationNotIn(List<String> values) {
            addCriterion("payment_confirmation not in", values, "paymentConfirmation");
            return (Criteria) this;
        }

        public Criteria andPaymentConfirmationBetween(String value1, String value2) {
            addCriterion("payment_confirmation between", value1, value2, "paymentConfirmation");
            return (Criteria) this;
        }

        public Criteria andPaymentConfirmationNotBetween(String value1, String value2) {
            addCriterion("payment_confirmation not between", value1, value2, "paymentConfirmation");
            return (Criteria) this;
        }

        public Criteria andReportNumberIsNull() {
            addCriterion("report_number is null");
            return (Criteria) this;
        }

        public Criteria andReportNumberIsNotNull() {
            addCriterion("report_number is not null");
            return (Criteria) this;
        }

        public Criteria andReportNumberEqualTo(String value) {
            addCriterion("report_number =", value, "reportNumber");
            return (Criteria) this;
        }

        public Criteria andReportNumberNotEqualTo(String value) {
            addCriterion("report_number <>", value, "reportNumber");
            return (Criteria) this;
        }

        public Criteria andReportNumberGreaterThan(String value) {
            addCriterion("report_number >", value, "reportNumber");
            return (Criteria) this;
        }

        public Criteria andReportNumberGreaterThanOrEqualTo(String value) {
            addCriterion("report_number >=", value, "reportNumber");
            return (Criteria) this;
        }

        public Criteria andReportNumberLessThan(String value) {
            addCriterion("report_number <", value, "reportNumber");
            return (Criteria) this;
        }

        public Criteria andReportNumberLessThanOrEqualTo(String value) {
            addCriterion("report_number <=", value, "reportNumber");
            return (Criteria) this;
        }

        public Criteria andReportNumberLike(String value) {
            addCriterion("report_number like", value, "reportNumber");
            return (Criteria) this;
        }

        public Criteria andReportNumberNotLike(String value) {
            addCriterion("report_number not like", value, "reportNumber");
            return (Criteria) this;
        }

        public Criteria andReportNumberIn(List<String> values) {
            addCriterion("report_number in", values, "reportNumber");
            return (Criteria) this;
        }

        public Criteria andReportNumberNotIn(List<String> values) {
            addCriterion("report_number not in", values, "reportNumber");
            return (Criteria) this;
        }

        public Criteria andReportNumberBetween(String value1, String value2) {
            addCriterion("report_number between", value1, value2, "reportNumber");
            return (Criteria) this;
        }

        public Criteria andReportNumberNotBetween(String value1, String value2) {
            addCriterion("report_number not between", value1, value2, "reportNumber");
            return (Criteria) this;
        }

        public Criteria andNotCommissionConfirmIsNull() {
            addCriterion("not_commission_confirm is null");
            return (Criteria) this;
        }

        public Criteria andNotCommissionConfirmIsNotNull() {
            addCriterion("not_commission_confirm is not null");
            return (Criteria) this;
        }

        public Criteria andNotCommissionConfirmEqualTo(String value) {
            addCriterion("not_commission_confirm =", value, "notCommissionConfirm");
            return (Criteria) this;
        }

        public Criteria andNotCommissionConfirmNotEqualTo(String value) {
            addCriterion("not_commission_confirm <>", value, "notCommissionConfirm");
            return (Criteria) this;
        }

        public Criteria andNotCommissionConfirmGreaterThan(String value) {
            addCriterion("not_commission_confirm >", value, "notCommissionConfirm");
            return (Criteria) this;
        }

        public Criteria andNotCommissionConfirmGreaterThanOrEqualTo(String value) {
            addCriterion("not_commission_confirm >=", value, "notCommissionConfirm");
            return (Criteria) this;
        }

        public Criteria andNotCommissionConfirmLessThan(String value) {
            addCriterion("not_commission_confirm <", value, "notCommissionConfirm");
            return (Criteria) this;
        }

        public Criteria andNotCommissionConfirmLessThanOrEqualTo(String value) {
            addCriterion("not_commission_confirm <=", value, "notCommissionConfirm");
            return (Criteria) this;
        }

        public Criteria andNotCommissionConfirmLike(String value) {
            addCriterion("not_commission_confirm like", value, "notCommissionConfirm");
            return (Criteria) this;
        }

        public Criteria andNotCommissionConfirmNotLike(String value) {
            addCriterion("not_commission_confirm not like", value, "notCommissionConfirm");
            return (Criteria) this;
        }

        public Criteria andNotCommissionConfirmIn(List<String> values) {
            addCriterion("not_commission_confirm in", values, "notCommissionConfirm");
            return (Criteria) this;
        }

        public Criteria andNotCommissionConfirmNotIn(List<String> values) {
            addCriterion("not_commission_confirm not in", values, "notCommissionConfirm");
            return (Criteria) this;
        }

        public Criteria andNotCommissionConfirmBetween(String value1, String value2) {
            addCriterion("not_commission_confirm between", value1, value2, "notCommissionConfirm");
            return (Criteria) this;
        }

        public Criteria andNotCommissionConfirmNotBetween(String value1, String value2) {
            addCriterion("not_commission_confirm not between", value1, value2, "notCommissionConfirm");
            return (Criteria) this;
        }

        public Criteria andNotRebateConfirmIsNull() {
            addCriterion("not_rebate_confirm is null");
            return (Criteria) this;
        }

        public Criteria andNotRebateConfirmIsNotNull() {
            addCriterion("not_rebate_confirm is not null");
            return (Criteria) this;
        }

        public Criteria andNotRebateConfirmEqualTo(String value) {
            addCriterion("not_rebate_confirm =", value, "notRebateConfirm");
            return (Criteria) this;
        }

        public Criteria andNotRebateConfirmNotEqualTo(String value) {
            addCriterion("not_rebate_confirm <>", value, "notRebateConfirm");
            return (Criteria) this;
        }

        public Criteria andNotRebateConfirmGreaterThan(String value) {
            addCriterion("not_rebate_confirm >", value, "notRebateConfirm");
            return (Criteria) this;
        }

        public Criteria andNotRebateConfirmGreaterThanOrEqualTo(String value) {
            addCriterion("not_rebate_confirm >=", value, "notRebateConfirm");
            return (Criteria) this;
        }

        public Criteria andNotRebateConfirmLessThan(String value) {
            addCriterion("not_rebate_confirm <", value, "notRebateConfirm");
            return (Criteria) this;
        }

        public Criteria andNotRebateConfirmLessThanOrEqualTo(String value) {
            addCriterion("not_rebate_confirm <=", value, "notRebateConfirm");
            return (Criteria) this;
        }

        public Criteria andNotRebateConfirmLike(String value) {
            addCriterion("not_rebate_confirm like", value, "notRebateConfirm");
            return (Criteria) this;
        }

        public Criteria andNotRebateConfirmNotLike(String value) {
            addCriterion("not_rebate_confirm not like", value, "notRebateConfirm");
            return (Criteria) this;
        }

        public Criteria andNotRebateConfirmIn(List<String> values) {
            addCriterion("not_rebate_confirm in", values, "notRebateConfirm");
            return (Criteria) this;
        }

        public Criteria andNotRebateConfirmNotIn(List<String> values) {
            addCriterion("not_rebate_confirm not in", values, "notRebateConfirm");
            return (Criteria) this;
        }

        public Criteria andNotRebateConfirmBetween(String value1, String value2) {
            addCriterion("not_rebate_confirm between", value1, value2, "notRebateConfirm");
            return (Criteria) this;
        }

        public Criteria andNotRebateConfirmNotBetween(String value1, String value2) {
            addCriterion("not_rebate_confirm not between", value1, value2, "notRebateConfirm");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeIsNull() {
            addCriterion("confirm_time is null");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeIsNotNull() {
            addCriterion("confirm_time is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeEqualTo(Date value) {
            addCriterion("confirm_time =", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotEqualTo(Date value) {
            addCriterion("confirm_time <>", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeGreaterThan(Date value) {
            addCriterion("confirm_time >", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("confirm_time >=", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeLessThan(Date value) {
            addCriterion("confirm_time <", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeLessThanOrEqualTo(Date value) {
            addCriterion("confirm_time <=", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeIn(List<Date> values) {
            addCriterion("confirm_time in", values, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotIn(List<Date> values) {
            addCriterion("confirm_time not in", values, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeBetween(Date value1, Date value2) {
            addCriterion("confirm_time between", value1, value2, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotBetween(Date value1, Date value2) {
            addCriterion("confirm_time not between", value1, value2, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andReportBindDateIsNull() {
            addCriterion("report_bind_date is null");
            return (Criteria) this;
        }

        public Criteria andReportBindDateIsNotNull() {
            addCriterion("report_bind_date is not null");
            return (Criteria) this;
        }

        public Criteria andReportBindDateEqualTo(Date value) {
            addCriterion("report_bind_date =", value, "reportBindDate");
            return (Criteria) this;
        }

        public Criteria andReportBindDateNotEqualTo(Date value) {
            addCriterion("report_bind_date <>", value, "reportBindDate");
            return (Criteria) this;
        }

        public Criteria andReportBindDateGreaterThan(Date value) {
            addCriterion("report_bind_date >", value, "reportBindDate");
            return (Criteria) this;
        }

        public Criteria andReportBindDateGreaterThanOrEqualTo(Date value) {
            addCriterion("report_bind_date >=", value, "reportBindDate");
            return (Criteria) this;
        }

        public Criteria andReportBindDateLessThan(Date value) {
            addCriterion("report_bind_date <", value, "reportBindDate");
            return (Criteria) this;
        }

        public Criteria andReportBindDateLessThanOrEqualTo(Date value) {
            addCriterion("report_bind_date <=", value, "reportBindDate");
            return (Criteria) this;
        }

        public Criteria andReportBindDateIn(List<Date> values) {
            addCriterion("report_bind_date in", values, "reportBindDate");
            return (Criteria) this;
        }

        public Criteria andReportBindDateNotIn(List<Date> values) {
            addCriterion("report_bind_date not in", values, "reportBindDate");
            return (Criteria) this;
        }

        public Criteria andReportBindDateBetween(Date value1, Date value2) {
            addCriterion("report_bind_date between", value1, value2, "reportBindDate");
            return (Criteria) this;
        }

        public Criteria andReportBindDateNotBetween(Date value1, Date value2) {
            addCriterion("report_bind_date not between", value1, value2, "reportBindDate");
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

        public Criteria andPigeonholeDateEqualTo(Date value) {
            addCriterion("pigeonhole_date =", value, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateNotEqualTo(Date value) {
            addCriterion("pigeonhole_date <>", value, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateGreaterThan(Date value) {
            addCriterion("pigeonhole_date >", value, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateGreaterThanOrEqualTo(Date value) {
            addCriterion("pigeonhole_date >=", value, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateLessThan(Date value) {
            addCriterion("pigeonhole_date <", value, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateLessThanOrEqualTo(Date value) {
            addCriterion("pigeonhole_date <=", value, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateIn(List<Date> values) {
            addCriterion("pigeonhole_date in", values, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateNotIn(List<Date> values) {
            addCriterion("pigeonhole_date not in", values, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateBetween(Date value1, Date value2) {
            addCriterion("pigeonhole_date between", value1, value2, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andPigeonholeDateNotBetween(Date value1, Date value2) {
            addCriterion("pigeonhole_date not between", value1, value2, "pigeonholeDate");
            return (Criteria) this;
        }

        public Criteria andOverduePigeonholeIsNull() {
            addCriterion("overdue_pigeonhole is null");
            return (Criteria) this;
        }

        public Criteria andOverduePigeonholeIsNotNull() {
            addCriterion("overdue_pigeonhole is not null");
            return (Criteria) this;
        }

        public Criteria andOverduePigeonholeEqualTo(String value) {
            addCriterion("overdue_pigeonhole =", value, "overduePigeonhole");
            return (Criteria) this;
        }

        public Criteria andOverduePigeonholeNotEqualTo(String value) {
            addCriterion("overdue_pigeonhole <>", value, "overduePigeonhole");
            return (Criteria) this;
        }

        public Criteria andOverduePigeonholeGreaterThan(String value) {
            addCriterion("overdue_pigeonhole >", value, "overduePigeonhole");
            return (Criteria) this;
        }

        public Criteria andOverduePigeonholeGreaterThanOrEqualTo(String value) {
            addCriterion("overdue_pigeonhole >=", value, "overduePigeonhole");
            return (Criteria) this;
        }

        public Criteria andOverduePigeonholeLessThan(String value) {
            addCriterion("overdue_pigeonhole <", value, "overduePigeonhole");
            return (Criteria) this;
        }

        public Criteria andOverduePigeonholeLessThanOrEqualTo(String value) {
            addCriterion("overdue_pigeonhole <=", value, "overduePigeonhole");
            return (Criteria) this;
        }

        public Criteria andOverduePigeonholeLike(String value) {
            addCriterion("overdue_pigeonhole like", value, "overduePigeonhole");
            return (Criteria) this;
        }

        public Criteria andOverduePigeonholeNotLike(String value) {
            addCriterion("overdue_pigeonhole not like", value, "overduePigeonhole");
            return (Criteria) this;
        }

        public Criteria andOverduePigeonholeIn(List<String> values) {
            addCriterion("overdue_pigeonhole in", values, "overduePigeonhole");
            return (Criteria) this;
        }

        public Criteria andOverduePigeonholeNotIn(List<String> values) {
            addCriterion("overdue_pigeonhole not in", values, "overduePigeonhole");
            return (Criteria) this;
        }

        public Criteria andOverduePigeonholeBetween(String value1, String value2) {
            addCriterion("overdue_pigeonhole between", value1, value2, "overduePigeonhole");
            return (Criteria) this;
        }

        public Criteria andOverduePigeonholeNotBetween(String value1, String value2) {
            addCriterion("overdue_pigeonhole not between", value1, value2, "overduePigeonhole");
            return (Criteria) this;
        }

        public Criteria andPigeonholeConfirmIsNull() {
            addCriterion("pigeonhole_confirm is null");
            return (Criteria) this;
        }

        public Criteria andPigeonholeConfirmIsNotNull() {
            addCriterion("pigeonhole_confirm is not null");
            return (Criteria) this;
        }

        public Criteria andPigeonholeConfirmEqualTo(String value) {
            addCriterion("pigeonhole_confirm =", value, "pigeonholeConfirm");
            return (Criteria) this;
        }

        public Criteria andPigeonholeConfirmNotEqualTo(String value) {
            addCriterion("pigeonhole_confirm <>", value, "pigeonholeConfirm");
            return (Criteria) this;
        }

        public Criteria andPigeonholeConfirmGreaterThan(String value) {
            addCriterion("pigeonhole_confirm >", value, "pigeonholeConfirm");
            return (Criteria) this;
        }

        public Criteria andPigeonholeConfirmGreaterThanOrEqualTo(String value) {
            addCriterion("pigeonhole_confirm >=", value, "pigeonholeConfirm");
            return (Criteria) this;
        }

        public Criteria andPigeonholeConfirmLessThan(String value) {
            addCriterion("pigeonhole_confirm <", value, "pigeonholeConfirm");
            return (Criteria) this;
        }

        public Criteria andPigeonholeConfirmLessThanOrEqualTo(String value) {
            addCriterion("pigeonhole_confirm <=", value, "pigeonholeConfirm");
            return (Criteria) this;
        }

        public Criteria andPigeonholeConfirmLike(String value) {
            addCriterion("pigeonhole_confirm like", value, "pigeonholeConfirm");
            return (Criteria) this;
        }

        public Criteria andPigeonholeConfirmNotLike(String value) {
            addCriterion("pigeonhole_confirm not like", value, "pigeonholeConfirm");
            return (Criteria) this;
        }

        public Criteria andPigeonholeConfirmIn(List<String> values) {
            addCriterion("pigeonhole_confirm in", values, "pigeonholeConfirm");
            return (Criteria) this;
        }

        public Criteria andPigeonholeConfirmNotIn(List<String> values) {
            addCriterion("pigeonhole_confirm not in", values, "pigeonholeConfirm");
            return (Criteria) this;
        }

        public Criteria andPigeonholeConfirmBetween(String value1, String value2) {
            addCriterion("pigeonhole_confirm between", value1, value2, "pigeonholeConfirm");
            return (Criteria) this;
        }

        public Criteria andPigeonholeConfirmNotBetween(String value1, String value2) {
            addCriterion("pigeonhole_confirm not between", value1, value2, "pigeonholeConfirm");
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

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andModifiedIsNull() {
            addCriterion("modified is null");
            return (Criteria) this;
        }

        public Criteria andModifiedIsNotNull() {
            addCriterion("modified is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedEqualTo(Date value) {
            addCriterion("modified =", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotEqualTo(Date value) {
            addCriterion("modified <>", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedGreaterThan(Date value) {
            addCriterion("modified >", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("modified >=", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedLessThan(Date value) {
            addCriterion("modified <", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedLessThanOrEqualTo(Date value) {
            addCriterion("modified <=", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedIn(List<Date> values) {
            addCriterion("modified in", values, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotIn(List<Date> values) {
            addCriterion("modified not in", values, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedBetween(Date value1, Date value2) {
            addCriterion("modified between", value1, value2, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotBetween(Date value1, Date value2) {
            addCriterion("modified not between", value1, value2, "modified");
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