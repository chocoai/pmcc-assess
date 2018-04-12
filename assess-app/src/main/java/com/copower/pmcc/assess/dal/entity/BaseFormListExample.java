package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.List;

public class BaseFormListExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaseFormListExample() {
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

        public Criteria andFormIdIsNull() {
            addCriterion("form_id is null");
            return (Criteria) this;
        }

        public Criteria andFormIdIsNotNull() {
            addCriterion("form_id is not null");
            return (Criteria) this;
        }

        public Criteria andFormIdEqualTo(Integer value) {
            addCriterion("form_id =", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotEqualTo(Integer value) {
            addCriterion("form_id <>", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdGreaterThan(Integer value) {
            addCriterion("form_id >", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("form_id >=", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLessThan(Integer value) {
            addCriterion("form_id <", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdLessThanOrEqualTo(Integer value) {
            addCriterion("form_id <=", value, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdIn(List<Integer> values) {
            addCriterion("form_id in", values, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotIn(List<Integer> values) {
            addCriterion("form_id not in", values, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdBetween(Integer value1, Integer value2) {
            addCriterion("form_id between", value1, value2, "formId");
            return (Criteria) this;
        }

        public Criteria andFormIdNotBetween(Integer value1, Integer value2) {
            addCriterion("form_id not between", value1, value2, "formId");
            return (Criteria) this;
        }

        public Criteria andCnNameIsNull() {
            addCriterion("cn_name is null");
            return (Criteria) this;
        }

        public Criteria andCnNameIsNotNull() {
            addCriterion("cn_name is not null");
            return (Criteria) this;
        }

        public Criteria andCnNameEqualTo(String value) {
            addCriterion("cn_name =", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameNotEqualTo(String value) {
            addCriterion("cn_name <>", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameGreaterThan(String value) {
            addCriterion("cn_name >", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameGreaterThanOrEqualTo(String value) {
            addCriterion("cn_name >=", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameLessThan(String value) {
            addCriterion("cn_name <", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameLessThanOrEqualTo(String value) {
            addCriterion("cn_name <=", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameLike(String value) {
            addCriterion("cn_name like", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameNotLike(String value) {
            addCriterion("cn_name not like", value, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameIn(List<String> values) {
            addCriterion("cn_name in", values, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameNotIn(List<String> values) {
            addCriterion("cn_name not in", values, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameBetween(String value1, String value2) {
            addCriterion("cn_name between", value1, value2, "cnName");
            return (Criteria) this;
        }

        public Criteria andCnNameNotBetween(String value1, String value2) {
            addCriterion("cn_name not between", value1, value2, "cnName");
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

        public Criteria andFormNameIsNull() {
            addCriterion("form_name is null");
            return (Criteria) this;
        }

        public Criteria andFormNameIsNotNull() {
            addCriterion("form_name is not null");
            return (Criteria) this;
        }

        public Criteria andFormNameEqualTo(String value) {
            addCriterion("form_name =", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameNotEqualTo(String value) {
            addCriterion("form_name <>", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameGreaterThan(String value) {
            addCriterion("form_name >", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameGreaterThanOrEqualTo(String value) {
            addCriterion("form_name >=", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameLessThan(String value) {
            addCriterion("form_name <", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameLessThanOrEqualTo(String value) {
            addCriterion("form_name <=", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameLike(String value) {
            addCriterion("form_name like", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameNotLike(String value) {
            addCriterion("form_name not like", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameIn(List<String> values) {
            addCriterion("form_name in", values, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameNotIn(List<String> values) {
            addCriterion("form_name not in", values, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameBetween(String value1, String value2) {
            addCriterion("form_name between", value1, value2, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameNotBetween(String value1, String value2) {
            addCriterion("form_name not between", value1, value2, "formName");
            return (Criteria) this;
        }

        public Criteria andBisEnableIsNull() {
            addCriterion("bis_enable is null");
            return (Criteria) this;
        }

        public Criteria andBisEnableIsNotNull() {
            addCriterion("bis_enable is not null");
            return (Criteria) this;
        }

        public Criteria andBisEnableEqualTo(Boolean value) {
            addCriterion("bis_enable =", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableNotEqualTo(Boolean value) {
            addCriterion("bis_enable <>", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableGreaterThan(Boolean value) {
            addCriterion("bis_enable >", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_enable >=", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableLessThan(Boolean value) {
            addCriterion("bis_enable <", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_enable <=", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableIn(List<Boolean> values) {
            addCriterion("bis_enable in", values, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableNotIn(List<Boolean> values) {
            addCriterion("bis_enable not in", values, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_enable between", value1, value2, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_enable not between", value1, value2, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisConfigureIsNull() {
            addCriterion("bis_configure is null");
            return (Criteria) this;
        }

        public Criteria andBisConfigureIsNotNull() {
            addCriterion("bis_configure is not null");
            return (Criteria) this;
        }

        public Criteria andBisConfigureEqualTo(Boolean value) {
            addCriterion("bis_configure =", value, "bisConfigure");
            return (Criteria) this;
        }

        public Criteria andBisConfigureNotEqualTo(Boolean value) {
            addCriterion("bis_configure <>", value, "bisConfigure");
            return (Criteria) this;
        }

        public Criteria andBisConfigureGreaterThan(Boolean value) {
            addCriterion("bis_configure >", value, "bisConfigure");
            return (Criteria) this;
        }

        public Criteria andBisConfigureGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_configure >=", value, "bisConfigure");
            return (Criteria) this;
        }

        public Criteria andBisConfigureLessThan(Boolean value) {
            addCriterion("bis_configure <", value, "bisConfigure");
            return (Criteria) this;
        }

        public Criteria andBisConfigureLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_configure <=", value, "bisConfigure");
            return (Criteria) this;
        }

        public Criteria andBisConfigureIn(List<Boolean> values) {
            addCriterion("bis_configure in", values, "bisConfigure");
            return (Criteria) this;
        }

        public Criteria andBisConfigureNotIn(List<Boolean> values) {
            addCriterion("bis_configure not in", values, "bisConfigure");
            return (Criteria) this;
        }

        public Criteria andBisConfigureBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_configure between", value1, value2, "bisConfigure");
            return (Criteria) this;
        }

        public Criteria andBisConfigureNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_configure not between", value1, value2, "bisConfigure");
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

        public Criteria andForeignKeyNameIsNull() {
            addCriterion("foreign_key_name is null");
            return (Criteria) this;
        }

        public Criteria andForeignKeyNameIsNotNull() {
            addCriterion("foreign_key_name is not null");
            return (Criteria) this;
        }

        public Criteria andForeignKeyNameEqualTo(String value) {
            addCriterion("foreign_key_name =", value, "foreignKeyName");
            return (Criteria) this;
        }

        public Criteria andForeignKeyNameNotEqualTo(String value) {
            addCriterion("foreign_key_name <>", value, "foreignKeyName");
            return (Criteria) this;
        }

        public Criteria andForeignKeyNameGreaterThan(String value) {
            addCriterion("foreign_key_name >", value, "foreignKeyName");
            return (Criteria) this;
        }

        public Criteria andForeignKeyNameGreaterThanOrEqualTo(String value) {
            addCriterion("foreign_key_name >=", value, "foreignKeyName");
            return (Criteria) this;
        }

        public Criteria andForeignKeyNameLessThan(String value) {
            addCriterion("foreign_key_name <", value, "foreignKeyName");
            return (Criteria) this;
        }

        public Criteria andForeignKeyNameLessThanOrEqualTo(String value) {
            addCriterion("foreign_key_name <=", value, "foreignKeyName");
            return (Criteria) this;
        }

        public Criteria andForeignKeyNameLike(String value) {
            addCriterion("foreign_key_name like", value, "foreignKeyName");
            return (Criteria) this;
        }

        public Criteria andForeignKeyNameNotLike(String value) {
            addCriterion("foreign_key_name not like", value, "foreignKeyName");
            return (Criteria) this;
        }

        public Criteria andForeignKeyNameIn(List<String> values) {
            addCriterion("foreign_key_name in", values, "foreignKeyName");
            return (Criteria) this;
        }

        public Criteria andForeignKeyNameNotIn(List<String> values) {
            addCriterion("foreign_key_name not in", values, "foreignKeyName");
            return (Criteria) this;
        }

        public Criteria andForeignKeyNameBetween(String value1, String value2) {
            addCriterion("foreign_key_name between", value1, value2, "foreignKeyName");
            return (Criteria) this;
        }

        public Criteria andForeignKeyNameNotBetween(String value1, String value2) {
            addCriterion("foreign_key_name not between", value1, value2, "foreignKeyName");
            return (Criteria) this;
        }

        public Criteria andBisMultipleIsNull() {
            addCriterion("bis_multiple is null");
            return (Criteria) this;
        }

        public Criteria andBisMultipleIsNotNull() {
            addCriterion("bis_multiple is not null");
            return (Criteria) this;
        }

        public Criteria andBisMultipleEqualTo(Boolean value) {
            addCriterion("bis_multiple =", value, "bisMultiple");
            return (Criteria) this;
        }

        public Criteria andBisMultipleNotEqualTo(Boolean value) {
            addCriterion("bis_multiple <>", value, "bisMultiple");
            return (Criteria) this;
        }

        public Criteria andBisMultipleGreaterThan(Boolean value) {
            addCriterion("bis_multiple >", value, "bisMultiple");
            return (Criteria) this;
        }

        public Criteria andBisMultipleGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_multiple >=", value, "bisMultiple");
            return (Criteria) this;
        }

        public Criteria andBisMultipleLessThan(Boolean value) {
            addCriterion("bis_multiple <", value, "bisMultiple");
            return (Criteria) this;
        }

        public Criteria andBisMultipleLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_multiple <=", value, "bisMultiple");
            return (Criteria) this;
        }

        public Criteria andBisMultipleIn(List<Boolean> values) {
            addCriterion("bis_multiple in", values, "bisMultiple");
            return (Criteria) this;
        }

        public Criteria andBisMultipleNotIn(List<Boolean> values) {
            addCriterion("bis_multiple not in", values, "bisMultiple");
            return (Criteria) this;
        }

        public Criteria andBisMultipleBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_multiple between", value1, value2, "bisMultiple");
            return (Criteria) this;
        }

        public Criteria andBisMultipleNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_multiple not between", value1, value2, "bisMultiple");
            return (Criteria) this;
        }

        public Criteria andCustomUrlIsNull() {
            addCriterion("custom_url is null");
            return (Criteria) this;
        }

        public Criteria andCustomUrlIsNotNull() {
            addCriterion("custom_url is not null");
            return (Criteria) this;
        }

        public Criteria andCustomUrlEqualTo(String value) {
            addCriterion("custom_url =", value, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNotEqualTo(String value) {
            addCriterion("custom_url <>", value, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlGreaterThan(String value) {
            addCriterion("custom_url >", value, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlGreaterThanOrEqualTo(String value) {
            addCriterion("custom_url >=", value, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlLessThan(String value) {
            addCriterion("custom_url <", value, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlLessThanOrEqualTo(String value) {
            addCriterion("custom_url <=", value, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlLike(String value) {
            addCriterion("custom_url like", value, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNotLike(String value) {
            addCriterion("custom_url not like", value, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlIn(List<String> values) {
            addCriterion("custom_url in", values, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNotIn(List<String> values) {
            addCriterion("custom_url not in", values, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlBetween(String value1, String value2) {
            addCriterion("custom_url between", value1, value2, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomUrlNotBetween(String value1, String value2) {
            addCriterion("custom_url not between", value1, value2, "customUrl");
            return (Criteria) this;
        }

        public Criteria andCustomeDisplayUrlIsNull() {
            addCriterion("custome_display_url is null");
            return (Criteria) this;
        }

        public Criteria andCustomeDisplayUrlIsNotNull() {
            addCriterion("custome_display_url is not null");
            return (Criteria) this;
        }

        public Criteria andCustomeDisplayUrlEqualTo(String value) {
            addCriterion("custome_display_url =", value, "customeDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomeDisplayUrlNotEqualTo(String value) {
            addCriterion("custome_display_url <>", value, "customeDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomeDisplayUrlGreaterThan(String value) {
            addCriterion("custome_display_url >", value, "customeDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomeDisplayUrlGreaterThanOrEqualTo(String value) {
            addCriterion("custome_display_url >=", value, "customeDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomeDisplayUrlLessThan(String value) {
            addCriterion("custome_display_url <", value, "customeDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomeDisplayUrlLessThanOrEqualTo(String value) {
            addCriterion("custome_display_url <=", value, "customeDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomeDisplayUrlLike(String value) {
            addCriterion("custome_display_url like", value, "customeDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomeDisplayUrlNotLike(String value) {
            addCriterion("custome_display_url not like", value, "customeDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomeDisplayUrlIn(List<String> values) {
            addCriterion("custome_display_url in", values, "customeDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomeDisplayUrlNotIn(List<String> values) {
            addCriterion("custome_display_url not in", values, "customeDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomeDisplayUrlBetween(String value1, String value2) {
            addCriterion("custome_display_url between", value1, value2, "customeDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomeDisplayUrlNotBetween(String value1, String value2) {
            addCriterion("custome_display_url not between", value1, value2, "customeDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andBisCardIsNull() {
            addCriterion("bis_card is null");
            return (Criteria) this;
        }

        public Criteria andBisCardIsNotNull() {
            addCriterion("bis_card is not null");
            return (Criteria) this;
        }

        public Criteria andBisCardEqualTo(Boolean value) {
            addCriterion("bis_card =", value, "bisCard");
            return (Criteria) this;
        }

        public Criteria andBisCardNotEqualTo(Boolean value) {
            addCriterion("bis_card <>", value, "bisCard");
            return (Criteria) this;
        }

        public Criteria andBisCardGreaterThan(Boolean value) {
            addCriterion("bis_card >", value, "bisCard");
            return (Criteria) this;
        }

        public Criteria andBisCardGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_card >=", value, "bisCard");
            return (Criteria) this;
        }

        public Criteria andBisCardLessThan(Boolean value) {
            addCriterion("bis_card <", value, "bisCard");
            return (Criteria) this;
        }

        public Criteria andBisCardLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_card <=", value, "bisCard");
            return (Criteria) this;
        }

        public Criteria andBisCardIn(List<Boolean> values) {
            addCriterion("bis_card in", values, "bisCard");
            return (Criteria) this;
        }

        public Criteria andBisCardNotIn(List<Boolean> values) {
            addCriterion("bis_card not in", values, "bisCard");
            return (Criteria) this;
        }

        public Criteria andBisCardBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_card between", value1, value2, "bisCard");
            return (Criteria) this;
        }

        public Criteria andBisCardNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_card not between", value1, value2, "bisCard");
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