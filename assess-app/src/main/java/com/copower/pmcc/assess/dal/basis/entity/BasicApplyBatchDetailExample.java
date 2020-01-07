package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicApplyBatchDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicApplyBatchDetailExample() {
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

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
            addCriterion("pid not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdIsNull() {
            addCriterion("apply_batch_id is null");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdIsNotNull() {
            addCriterion("apply_batch_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdEqualTo(Integer value) {
            addCriterion("apply_batch_id =", value, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdNotEqualTo(Integer value) {
            addCriterion("apply_batch_id <>", value, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdGreaterThan(Integer value) {
            addCriterion("apply_batch_id >", value, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("apply_batch_id >=", value, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdLessThan(Integer value) {
            addCriterion("apply_batch_id <", value, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdLessThanOrEqualTo(Integer value) {
            addCriterion("apply_batch_id <=", value, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdIn(List<Integer> values) {
            addCriterion("apply_batch_id in", values, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdNotIn(List<Integer> values) {
            addCriterion("apply_batch_id not in", values, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdBetween(Integer value1, Integer value2) {
            addCriterion("apply_batch_id between", value1, value2, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andApplyBatchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("apply_batch_id not between", value1, value2, "applyBatchId");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("table_name is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("table_name is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("table_name =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("table_name <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("table_name >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("table_name >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("table_name <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("table_name <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("table_name like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("table_name not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("table_name in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("table_name not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("table_name between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("table_name not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableIdIsNull() {
            addCriterion("table_id is null");
            return (Criteria) this;
        }

        public Criteria andTableIdIsNotNull() {
            addCriterion("table_id is not null");
            return (Criteria) this;
        }

        public Criteria andTableIdEqualTo(Integer value) {
            addCriterion("table_id =", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotEqualTo(Integer value) {
            addCriterion("table_id <>", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdGreaterThan(Integer value) {
            addCriterion("table_id >", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("table_id >=", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLessThan(Integer value) {
            addCriterion("table_id <", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLessThanOrEqualTo(Integer value) {
            addCriterion("table_id <=", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdIn(List<Integer> values) {
            addCriterion("table_id in", values, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotIn(List<Integer> values) {
            addCriterion("table_id not in", values, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdBetween(Integer value1, Integer value2) {
            addCriterion("table_id between", value1, value2, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("table_id not between", value1, value2, "tableId");
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

        public Criteria andDisplayNameIsNull() {
            addCriterion("display_name is null");
            return (Criteria) this;
        }

        public Criteria andDisplayNameIsNotNull() {
            addCriterion("display_name is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayNameEqualTo(String value) {
            addCriterion("display_name =", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameNotEqualTo(String value) {
            addCriterion("display_name <>", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameGreaterThan(String value) {
            addCriterion("display_name >", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameGreaterThanOrEqualTo(String value) {
            addCriterion("display_name >=", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameLessThan(String value) {
            addCriterion("display_name <", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameLessThanOrEqualTo(String value) {
            addCriterion("display_name <=", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameLike(String value) {
            addCriterion("display_name like", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameNotLike(String value) {
            addCriterion("display_name not like", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameIn(List<String> values) {
            addCriterion("display_name in", values, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameNotIn(List<String> values) {
            addCriterion("display_name not in", values, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameBetween(String value1, String value2) {
            addCriterion("display_name between", value1, value2, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameNotBetween(String value1, String value2) {
            addCriterion("display_name not between", value1, value2, "displayName");
            return (Criteria) this;
        }

        public Criteria andBisStandardIsNull() {
            addCriterion("bis_standard is null");
            return (Criteria) this;
        }

        public Criteria andBisStandardIsNotNull() {
            addCriterion("bis_standard is not null");
            return (Criteria) this;
        }

        public Criteria andBisStandardEqualTo(Boolean value) {
            addCriterion("bis_standard =", value, "bisStandard");
            return (Criteria) this;
        }

        public Criteria andBisStandardNotEqualTo(Boolean value) {
            addCriterion("bis_standard <>", value, "bisStandard");
            return (Criteria) this;
        }

        public Criteria andBisStandardGreaterThan(Boolean value) {
            addCriterion("bis_standard >", value, "bisStandard");
            return (Criteria) this;
        }

        public Criteria andBisStandardGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_standard >=", value, "bisStandard");
            return (Criteria) this;
        }

        public Criteria andBisStandardLessThan(Boolean value) {
            addCriterion("bis_standard <", value, "bisStandard");
            return (Criteria) this;
        }

        public Criteria andBisStandardLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_standard <=", value, "bisStandard");
            return (Criteria) this;
        }

        public Criteria andBisStandardIn(List<Boolean> values) {
            addCriterion("bis_standard in", values, "bisStandard");
            return (Criteria) this;
        }

        public Criteria andBisStandardNotIn(List<Boolean> values) {
            addCriterion("bis_standard not in", values, "bisStandard");
            return (Criteria) this;
        }

        public Criteria andBisStandardBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_standard between", value1, value2, "bisStandard");
            return (Criteria) this;
        }

        public Criteria andBisStandardNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_standard not between", value1, value2, "bisStandard");
            return (Criteria) this;
        }

        public Criteria andQuoteIdIsNull() {
            addCriterion("quote_id is null");
            return (Criteria) this;
        }

        public Criteria andQuoteIdIsNotNull() {
            addCriterion("quote_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuoteIdEqualTo(Integer value) {
            addCriterion("quote_id =", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdNotEqualTo(Integer value) {
            addCriterion("quote_id <>", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdGreaterThan(Integer value) {
            addCriterion("quote_id >", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("quote_id >=", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdLessThan(Integer value) {
            addCriterion("quote_id <", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdLessThanOrEqualTo(Integer value) {
            addCriterion("quote_id <=", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdIn(List<Integer> values) {
            addCriterion("quote_id in", values, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdNotIn(List<Integer> values) {
            addCriterion("quote_id not in", values, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdBetween(Integer value1, Integer value2) {
            addCriterion("quote_id between", value1, value2, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("quote_id not between", value1, value2, "quoteId");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIsNull() {
            addCriterion("base_type is null");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIsNotNull() {
            addCriterion("base_type is not null");
            return (Criteria) this;
        }

        public Criteria andBaseTypeEqualTo(String value) {
            addCriterion("base_type =", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNotEqualTo(String value) {
            addCriterion("base_type <>", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeGreaterThan(String value) {
            addCriterion("base_type >", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeGreaterThanOrEqualTo(String value) {
            addCriterion("base_type >=", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeLessThan(String value) {
            addCriterion("base_type <", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeLessThanOrEqualTo(String value) {
            addCriterion("base_type <=", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeLike(String value) {
            addCriterion("base_type like", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNotLike(String value) {
            addCriterion("base_type not like", value, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIn(List<String> values) {
            addCriterion("base_type in", values, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNotIn(List<String> values) {
            addCriterion("base_type not in", values, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeBetween(String value1, String value2) {
            addCriterion("base_type between", value1, value2, "baseType");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNotBetween(String value1, String value2) {
            addCriterion("base_type not between", value1, value2, "baseType");
            return (Criteria) this;
        }

        public Criteria andCaseTablePidIsNull() {
            addCriterion("case_table_pid is null");
            return (Criteria) this;
        }

        public Criteria andCaseTablePidIsNotNull() {
            addCriterion("case_table_pid is not null");
            return (Criteria) this;
        }

        public Criteria andCaseTablePidEqualTo(Integer value) {
            addCriterion("case_table_pid =", value, "caseTablePid");
            return (Criteria) this;
        }

        public Criteria andCaseTablePidNotEqualTo(Integer value) {
            addCriterion("case_table_pid <>", value, "caseTablePid");
            return (Criteria) this;
        }

        public Criteria andCaseTablePidGreaterThan(Integer value) {
            addCriterion("case_table_pid >", value, "caseTablePid");
            return (Criteria) this;
        }

        public Criteria andCaseTablePidGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_table_pid >=", value, "caseTablePid");
            return (Criteria) this;
        }

        public Criteria andCaseTablePidLessThan(Integer value) {
            addCriterion("case_table_pid <", value, "caseTablePid");
            return (Criteria) this;
        }

        public Criteria andCaseTablePidLessThanOrEqualTo(Integer value) {
            addCriterion("case_table_pid <=", value, "caseTablePid");
            return (Criteria) this;
        }

        public Criteria andCaseTablePidIn(List<Integer> values) {
            addCriterion("case_table_pid in", values, "caseTablePid");
            return (Criteria) this;
        }

        public Criteria andCaseTablePidNotIn(List<Integer> values) {
            addCriterion("case_table_pid not in", values, "caseTablePid");
            return (Criteria) this;
        }

        public Criteria andCaseTablePidBetween(Integer value1, Integer value2) {
            addCriterion("case_table_pid between", value1, value2, "caseTablePid");
            return (Criteria) this;
        }

        public Criteria andCaseTablePidNotBetween(Integer value1, Integer value2) {
            addCriterion("case_table_pid not between", value1, value2, "caseTablePid");
            return (Criteria) this;
        }

        public Criteria andUpgradeTableIdIsNull() {
            addCriterion("upgrade_table_id is null");
            return (Criteria) this;
        }

        public Criteria andUpgradeTableIdIsNotNull() {
            addCriterion("upgrade_table_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpgradeTableIdEqualTo(Integer value) {
            addCriterion("upgrade_table_id =", value, "upgradeTableId");
            return (Criteria) this;
        }

        public Criteria andUpgradeTableIdNotEqualTo(Integer value) {
            addCriterion("upgrade_table_id <>", value, "upgradeTableId");
            return (Criteria) this;
        }

        public Criteria andUpgradeTableIdGreaterThan(Integer value) {
            addCriterion("upgrade_table_id >", value, "upgradeTableId");
            return (Criteria) this;
        }

        public Criteria andUpgradeTableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("upgrade_table_id >=", value, "upgradeTableId");
            return (Criteria) this;
        }

        public Criteria andUpgradeTableIdLessThan(Integer value) {
            addCriterion("upgrade_table_id <", value, "upgradeTableId");
            return (Criteria) this;
        }

        public Criteria andUpgradeTableIdLessThanOrEqualTo(Integer value) {
            addCriterion("upgrade_table_id <=", value, "upgradeTableId");
            return (Criteria) this;
        }

        public Criteria andUpgradeTableIdIn(List<Integer> values) {
            addCriterion("upgrade_table_id in", values, "upgradeTableId");
            return (Criteria) this;
        }

        public Criteria andUpgradeTableIdNotIn(List<Integer> values) {
            addCriterion("upgrade_table_id not in", values, "upgradeTableId");
            return (Criteria) this;
        }

        public Criteria andUpgradeTableIdBetween(Integer value1, Integer value2) {
            addCriterion("upgrade_table_id between", value1, value2, "upgradeTableId");
            return (Criteria) this;
        }

        public Criteria andUpgradeTableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("upgrade_table_id not between", value1, value2, "upgradeTableId");
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

        public Criteria andBisDeleteIsNull() {
            addCriterion("bis_delete is null");
            return (Criteria) this;
        }

        public Criteria andBisDeleteIsNotNull() {
            addCriterion("bis_delete is not null");
            return (Criteria) this;
        }

        public Criteria andBisDeleteEqualTo(Boolean value) {
            addCriterion("bis_delete =", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteNotEqualTo(Boolean value) {
            addCriterion("bis_delete <>", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteGreaterThan(Boolean value) {
            addCriterion("bis_delete >", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_delete >=", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteLessThan(Boolean value) {
            addCriterion("bis_delete <", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_delete <=", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteIn(List<Boolean> values) {
            addCriterion("bis_delete in", values, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteNotIn(List<Boolean> values) {
            addCriterion("bis_delete not in", values, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_delete between", value1, value2, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_delete not between", value1, value2, "bisDelete");
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

        public Criteria andExecutorIsNull() {
            addCriterion("executor is null");
            return (Criteria) this;
        }

        public Criteria andExecutorIsNotNull() {
            addCriterion("executor is not null");
            return (Criteria) this;
        }

        public Criteria andExecutorEqualTo(String value) {
            addCriterion("executor =", value, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorNotEqualTo(String value) {
            addCriterion("executor <>", value, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorGreaterThan(String value) {
            addCriterion("executor >", value, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorGreaterThanOrEqualTo(String value) {
            addCriterion("executor >=", value, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorLessThan(String value) {
            addCriterion("executor <", value, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorLessThanOrEqualTo(String value) {
            addCriterion("executor <=", value, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorLike(String value) {
            addCriterion("executor like", value, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorNotLike(String value) {
            addCriterion("executor not like", value, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorIn(List<String> values) {
            addCriterion("executor in", values, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorNotIn(List<String> values) {
            addCriterion("executor not in", values, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorBetween(String value1, String value2) {
            addCriterion("executor between", value1, value2, "executor");
            return (Criteria) this;
        }

        public Criteria andExecutorNotBetween(String value1, String value2) {
            addCriterion("executor not between", value1, value2, "executor");
            return (Criteria) this;
        }

        public Criteria andBisStructureIsNull() {
            addCriterion("bis_structure is null");
            return (Criteria) this;
        }

        public Criteria andBisStructureIsNotNull() {
            addCriterion("bis_structure is not null");
            return (Criteria) this;
        }

        public Criteria andBisStructureEqualTo(Boolean value) {
            addCriterion("bis_structure =", value, "bisStructure");
            return (Criteria) this;
        }

        public Criteria andBisStructureNotEqualTo(Boolean value) {
            addCriterion("bis_structure <>", value, "bisStructure");
            return (Criteria) this;
        }

        public Criteria andBisStructureGreaterThan(Boolean value) {
            addCriterion("bis_structure >", value, "bisStructure");
            return (Criteria) this;
        }

        public Criteria andBisStructureGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_structure >=", value, "bisStructure");
            return (Criteria) this;
        }

        public Criteria andBisStructureLessThan(Boolean value) {
            addCriterion("bis_structure <", value, "bisStructure");
            return (Criteria) this;
        }

        public Criteria andBisStructureLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_structure <=", value, "bisStructure");
            return (Criteria) this;
        }

        public Criteria andBisStructureIn(List<Boolean> values) {
            addCriterion("bis_structure in", values, "bisStructure");
            return (Criteria) this;
        }

        public Criteria andBisStructureNotIn(List<Boolean> values) {
            addCriterion("bis_structure not in", values, "bisStructure");
            return (Criteria) this;
        }

        public Criteria andBisStructureBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_structure between", value1, value2, "bisStructure");
            return (Criteria) this;
        }

        public Criteria andBisStructureNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_structure not between", value1, value2, "bisStructure");
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