package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseFormModuleFieldExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaseFormModuleFieldExample() {
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

        public Criteria andFormModuleIdIsNull() {
            addCriterion("form_module_id is null");
            return (Criteria) this;
        }

        public Criteria andFormModuleIdIsNotNull() {
            addCriterion("form_module_id is not null");
            return (Criteria) this;
        }

        public Criteria andFormModuleIdEqualTo(Integer value) {
            addCriterion("form_module_id =", value, "formModuleId");
            return (Criteria) this;
        }

        public Criteria andFormModuleIdNotEqualTo(Integer value) {
            addCriterion("form_module_id <>", value, "formModuleId");
            return (Criteria) this;
        }

        public Criteria andFormModuleIdGreaterThan(Integer value) {
            addCriterion("form_module_id >", value, "formModuleId");
            return (Criteria) this;
        }

        public Criteria andFormModuleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("form_module_id >=", value, "formModuleId");
            return (Criteria) this;
        }

        public Criteria andFormModuleIdLessThan(Integer value) {
            addCriterion("form_module_id <", value, "formModuleId");
            return (Criteria) this;
        }

        public Criteria andFormModuleIdLessThanOrEqualTo(Integer value) {
            addCriterion("form_module_id <=", value, "formModuleId");
            return (Criteria) this;
        }

        public Criteria andFormModuleIdIn(List<Integer> values) {
            addCriterion("form_module_id in", values, "formModuleId");
            return (Criteria) this;
        }

        public Criteria andFormModuleIdNotIn(List<Integer> values) {
            addCriterion("form_module_id not in", values, "formModuleId");
            return (Criteria) this;
        }

        public Criteria andFormModuleIdBetween(Integer value1, Integer value2) {
            addCriterion("form_module_id between", value1, value2, "formModuleId");
            return (Criteria) this;
        }

        public Criteria andFormModuleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("form_module_id not between", value1, value2, "formModuleId");
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

        public Criteria andJsonNameIsNull() {
            addCriterion("json_name is null");
            return (Criteria) this;
        }

        public Criteria andJsonNameIsNotNull() {
            addCriterion("json_name is not null");
            return (Criteria) this;
        }

        public Criteria andJsonNameEqualTo(String value) {
            addCriterion("json_name =", value, "jsonName");
            return (Criteria) this;
        }

        public Criteria andJsonNameNotEqualTo(String value) {
            addCriterion("json_name <>", value, "jsonName");
            return (Criteria) this;
        }

        public Criteria andJsonNameGreaterThan(String value) {
            addCriterion("json_name >", value, "jsonName");
            return (Criteria) this;
        }

        public Criteria andJsonNameGreaterThanOrEqualTo(String value) {
            addCriterion("json_name >=", value, "jsonName");
            return (Criteria) this;
        }

        public Criteria andJsonNameLessThan(String value) {
            addCriterion("json_name <", value, "jsonName");
            return (Criteria) this;
        }

        public Criteria andJsonNameLessThanOrEqualTo(String value) {
            addCriterion("json_name <=", value, "jsonName");
            return (Criteria) this;
        }

        public Criteria andJsonNameLike(String value) {
            addCriterion("json_name like", value, "jsonName");
            return (Criteria) this;
        }

        public Criteria andJsonNameNotLike(String value) {
            addCriterion("json_name not like", value, "jsonName");
            return (Criteria) this;
        }

        public Criteria andJsonNameIn(List<String> values) {
            addCriterion("json_name in", values, "jsonName");
            return (Criteria) this;
        }

        public Criteria andJsonNameNotIn(List<String> values) {
            addCriterion("json_name not in", values, "jsonName");
            return (Criteria) this;
        }

        public Criteria andJsonNameBetween(String value1, String value2) {
            addCriterion("json_name between", value1, value2, "jsonName");
            return (Criteria) this;
        }

        public Criteria andJsonNameNotBetween(String value1, String value2) {
            addCriterion("json_name not between", value1, value2, "jsonName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("group_name is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("group_name is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("group_name =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("group_name <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("group_name >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("group_name >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("group_name <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("group_name <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("group_name like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("group_name not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("group_name in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("group_name not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("group_name between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("group_name not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupDisplayNameIsNull() {
            addCriterion("group_display_name is null");
            return (Criteria) this;
        }

        public Criteria andGroupDisplayNameIsNotNull() {
            addCriterion("group_display_name is not null");
            return (Criteria) this;
        }

        public Criteria andGroupDisplayNameEqualTo(String value) {
            addCriterion("group_display_name =", value, "groupDisplayName");
            return (Criteria) this;
        }

        public Criteria andGroupDisplayNameNotEqualTo(String value) {
            addCriterion("group_display_name <>", value, "groupDisplayName");
            return (Criteria) this;
        }

        public Criteria andGroupDisplayNameGreaterThan(String value) {
            addCriterion("group_display_name >", value, "groupDisplayName");
            return (Criteria) this;
        }

        public Criteria andGroupDisplayNameGreaterThanOrEqualTo(String value) {
            addCriterion("group_display_name >=", value, "groupDisplayName");
            return (Criteria) this;
        }

        public Criteria andGroupDisplayNameLessThan(String value) {
            addCriterion("group_display_name <", value, "groupDisplayName");
            return (Criteria) this;
        }

        public Criteria andGroupDisplayNameLessThanOrEqualTo(String value) {
            addCriterion("group_display_name <=", value, "groupDisplayName");
            return (Criteria) this;
        }

        public Criteria andGroupDisplayNameLike(String value) {
            addCriterion("group_display_name like", value, "groupDisplayName");
            return (Criteria) this;
        }

        public Criteria andGroupDisplayNameNotLike(String value) {
            addCriterion("group_display_name not like", value, "groupDisplayName");
            return (Criteria) this;
        }

        public Criteria andGroupDisplayNameIn(List<String> values) {
            addCriterion("group_display_name in", values, "groupDisplayName");
            return (Criteria) this;
        }

        public Criteria andGroupDisplayNameNotIn(List<String> values) {
            addCriterion("group_display_name not in", values, "groupDisplayName");
            return (Criteria) this;
        }

        public Criteria andGroupDisplayNameBetween(String value1, String value2) {
            addCriterion("group_display_name between", value1, value2, "groupDisplayName");
            return (Criteria) this;
        }

        public Criteria andGroupDisplayNameNotBetween(String value1, String value2) {
            addCriterion("group_display_name not between", value1, value2, "groupDisplayName");
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

        public Criteria andFieldTypeIsNull() {
            addCriterion("field_type is null");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIsNotNull() {
            addCriterion("field_type is not null");
            return (Criteria) this;
        }

        public Criteria andFieldTypeEqualTo(Integer value) {
            addCriterion("field_type =", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotEqualTo(Integer value) {
            addCriterion("field_type <>", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeGreaterThan(Integer value) {
            addCriterion("field_type >", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("field_type >=", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeLessThan(Integer value) {
            addCriterion("field_type <", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeLessThanOrEqualTo(Integer value) {
            addCriterion("field_type <=", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIn(List<Integer> values) {
            addCriterion("field_type in", values, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotIn(List<Integer> values) {
            addCriterion("field_type not in", values, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeBetween(Integer value1, Integer value2) {
            addCriterion("field_type between", value1, value2, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("field_type not between", value1, value2, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldLengthIsNull() {
            addCriterion("field_length is null");
            return (Criteria) this;
        }

        public Criteria andFieldLengthIsNotNull() {
            addCriterion("field_length is not null");
            return (Criteria) this;
        }

        public Criteria andFieldLengthEqualTo(Integer value) {
            addCriterion("field_length =", value, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthNotEqualTo(Integer value) {
            addCriterion("field_length <>", value, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthGreaterThan(Integer value) {
            addCriterion("field_length >", value, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("field_length >=", value, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthLessThan(Integer value) {
            addCriterion("field_length <", value, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthLessThanOrEqualTo(Integer value) {
            addCriterion("field_length <=", value, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthIn(List<Integer> values) {
            addCriterion("field_length in", values, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthNotIn(List<Integer> values) {
            addCriterion("field_length not in", values, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthBetween(Integer value1, Integer value2) {
            addCriterion("field_length between", value1, value2, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("field_length not between", value1, value2, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIsNull() {
            addCriterion("default_value is null");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIsNotNull() {
            addCriterion("default_value is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultValueEqualTo(String value) {
            addCriterion("default_value =", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotEqualTo(String value) {
            addCriterion("default_value <>", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThan(String value) {
            addCriterion("default_value >", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThanOrEqualTo(String value) {
            addCriterion("default_value >=", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThan(String value) {
            addCriterion("default_value <", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThanOrEqualTo(String value) {
            addCriterion("default_value <=", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLike(String value) {
            addCriterion("default_value like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotLike(String value) {
            addCriterion("default_value not like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIn(List<String> values) {
            addCriterion("default_value in", values, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotIn(List<String> values) {
            addCriterion("default_value not in", values, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueBetween(String value1, String value2) {
            addCriterion("default_value between", value1, value2, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotBetween(String value1, String value2) {
            addCriterion("default_value not between", value1, value2, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDataSourceSqlIsNull() {
            addCriterion("data_source_sql is null");
            return (Criteria) this;
        }

        public Criteria andDataSourceSqlIsNotNull() {
            addCriterion("data_source_sql is not null");
            return (Criteria) this;
        }

        public Criteria andDataSourceSqlEqualTo(String value) {
            addCriterion("data_source_sql =", value, "dataSourceSql");
            return (Criteria) this;
        }

        public Criteria andDataSourceSqlNotEqualTo(String value) {
            addCriterion("data_source_sql <>", value, "dataSourceSql");
            return (Criteria) this;
        }

        public Criteria andDataSourceSqlGreaterThan(String value) {
            addCriterion("data_source_sql >", value, "dataSourceSql");
            return (Criteria) this;
        }

        public Criteria andDataSourceSqlGreaterThanOrEqualTo(String value) {
            addCriterion("data_source_sql >=", value, "dataSourceSql");
            return (Criteria) this;
        }

        public Criteria andDataSourceSqlLessThan(String value) {
            addCriterion("data_source_sql <", value, "dataSourceSql");
            return (Criteria) this;
        }

        public Criteria andDataSourceSqlLessThanOrEqualTo(String value) {
            addCriterion("data_source_sql <=", value, "dataSourceSql");
            return (Criteria) this;
        }

        public Criteria andDataSourceSqlLike(String value) {
            addCriterion("data_source_sql like", value, "dataSourceSql");
            return (Criteria) this;
        }

        public Criteria andDataSourceSqlNotLike(String value) {
            addCriterion("data_source_sql not like", value, "dataSourceSql");
            return (Criteria) this;
        }

        public Criteria andDataSourceSqlIn(List<String> values) {
            addCriterion("data_source_sql in", values, "dataSourceSql");
            return (Criteria) this;
        }

        public Criteria andDataSourceSqlNotIn(List<String> values) {
            addCriterion("data_source_sql not in", values, "dataSourceSql");
            return (Criteria) this;
        }

        public Criteria andDataSourceSqlBetween(String value1, String value2) {
            addCriterion("data_source_sql between", value1, value2, "dataSourceSql");
            return (Criteria) this;
        }

        public Criteria andDataSourceSqlNotBetween(String value1, String value2) {
            addCriterion("data_source_sql not between", value1, value2, "dataSourceSql");
            return (Criteria) this;
        }

        public Criteria andSortingIsNull() {
            addCriterion("sorting is null");
            return (Criteria) this;
        }

        public Criteria andSortingIsNotNull() {
            addCriterion("sorting is not null");
            return (Criteria) this;
        }

        public Criteria andSortingEqualTo(Integer value) {
            addCriterion("sorting =", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotEqualTo(Integer value) {
            addCriterion("sorting <>", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingGreaterThan(Integer value) {
            addCriterion("sorting >", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingGreaterThanOrEqualTo(Integer value) {
            addCriterion("sorting >=", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingLessThan(Integer value) {
            addCriterion("sorting <", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingLessThanOrEqualTo(Integer value) {
            addCriterion("sorting <=", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingIn(List<Integer> values) {
            addCriterion("sorting in", values, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotIn(List<Integer> values) {
            addCriterion("sorting not in", values, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingBetween(Integer value1, Integer value2) {
            addCriterion("sorting between", value1, value2, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotBetween(Integer value1, Integer value2) {
            addCriterion("sorting not between", value1, value2, "sorting");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataSourceIsNull() {
            addCriterion("bis_cache_data_source is null");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataSourceIsNotNull() {
            addCriterion("bis_cache_data_source is not null");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataSourceEqualTo(Boolean value) {
            addCriterion("bis_cache_data_source =", value, "bisCacheDataSource");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataSourceNotEqualTo(Boolean value) {
            addCriterion("bis_cache_data_source <>", value, "bisCacheDataSource");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataSourceGreaterThan(Boolean value) {
            addCriterion("bis_cache_data_source >", value, "bisCacheDataSource");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataSourceGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_cache_data_source >=", value, "bisCacheDataSource");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataSourceLessThan(Boolean value) {
            addCriterion("bis_cache_data_source <", value, "bisCacheDataSource");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataSourceLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_cache_data_source <=", value, "bisCacheDataSource");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataSourceIn(List<Boolean> values) {
            addCriterion("bis_cache_data_source in", values, "bisCacheDataSource");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataSourceNotIn(List<Boolean> values) {
            addCriterion("bis_cache_data_source not in", values, "bisCacheDataSource");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataSourceBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_cache_data_source between", value1, value2, "bisCacheDataSource");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataSourceNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_cache_data_source not between", value1, value2, "bisCacheDataSource");
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

        public Criteria andCustomDisplayUrlIsNull() {
            addCriterion("custom_display_url is null");
            return (Criteria) this;
        }

        public Criteria andCustomDisplayUrlIsNotNull() {
            addCriterion("custom_display_url is not null");
            return (Criteria) this;
        }

        public Criteria andCustomDisplayUrlEqualTo(String value) {
            addCriterion("custom_display_url =", value, "customDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomDisplayUrlNotEqualTo(String value) {
            addCriterion("custom_display_url <>", value, "customDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomDisplayUrlGreaterThan(String value) {
            addCriterion("custom_display_url >", value, "customDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomDisplayUrlGreaterThanOrEqualTo(String value) {
            addCriterion("custom_display_url >=", value, "customDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomDisplayUrlLessThan(String value) {
            addCriterion("custom_display_url <", value, "customDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomDisplayUrlLessThanOrEqualTo(String value) {
            addCriterion("custom_display_url <=", value, "customDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomDisplayUrlLike(String value) {
            addCriterion("custom_display_url like", value, "customDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomDisplayUrlNotLike(String value) {
            addCriterion("custom_display_url not like", value, "customDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomDisplayUrlIn(List<String> values) {
            addCriterion("custom_display_url in", values, "customDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomDisplayUrlNotIn(List<String> values) {
            addCriterion("custom_display_url not in", values, "customDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomDisplayUrlBetween(String value1, String value2) {
            addCriterion("custom_display_url between", value1, value2, "customDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andCustomDisplayUrlNotBetween(String value1, String value2) {
            addCriterion("custom_display_url not between", value1, value2, "customDisplayUrl");
            return (Criteria) this;
        }

        public Criteria andDataViewSqlIsNull() {
            addCriterion("data_view_sql is null");
            return (Criteria) this;
        }

        public Criteria andDataViewSqlIsNotNull() {
            addCriterion("data_view_sql is not null");
            return (Criteria) this;
        }

        public Criteria andDataViewSqlEqualTo(String value) {
            addCriterion("data_view_sql =", value, "dataViewSql");
            return (Criteria) this;
        }

        public Criteria andDataViewSqlNotEqualTo(String value) {
            addCriterion("data_view_sql <>", value, "dataViewSql");
            return (Criteria) this;
        }

        public Criteria andDataViewSqlGreaterThan(String value) {
            addCriterion("data_view_sql >", value, "dataViewSql");
            return (Criteria) this;
        }

        public Criteria andDataViewSqlGreaterThanOrEqualTo(String value) {
            addCriterion("data_view_sql >=", value, "dataViewSql");
            return (Criteria) this;
        }

        public Criteria andDataViewSqlLessThan(String value) {
            addCriterion("data_view_sql <", value, "dataViewSql");
            return (Criteria) this;
        }

        public Criteria andDataViewSqlLessThanOrEqualTo(String value) {
            addCriterion("data_view_sql <=", value, "dataViewSql");
            return (Criteria) this;
        }

        public Criteria andDataViewSqlLike(String value) {
            addCriterion("data_view_sql like", value, "dataViewSql");
            return (Criteria) this;
        }

        public Criteria andDataViewSqlNotLike(String value) {
            addCriterion("data_view_sql not like", value, "dataViewSql");
            return (Criteria) this;
        }

        public Criteria andDataViewSqlIn(List<String> values) {
            addCriterion("data_view_sql in", values, "dataViewSql");
            return (Criteria) this;
        }

        public Criteria andDataViewSqlNotIn(List<String> values) {
            addCriterion("data_view_sql not in", values, "dataViewSql");
            return (Criteria) this;
        }

        public Criteria andDataViewSqlBetween(String value1, String value2) {
            addCriterion("data_view_sql between", value1, value2, "dataViewSql");
            return (Criteria) this;
        }

        public Criteria andDataViewSqlNotBetween(String value1, String value2) {
            addCriterion("data_view_sql not between", value1, value2, "dataViewSql");
            return (Criteria) this;
        }

        public Criteria andWidthIsNull() {
            addCriterion("width is null");
            return (Criteria) this;
        }

        public Criteria andWidthIsNotNull() {
            addCriterion("width is not null");
            return (Criteria) this;
        }

        public Criteria andWidthEqualTo(Integer value) {
            addCriterion("width =", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotEqualTo(Integer value) {
            addCriterion("width <>", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThan(Integer value) {
            addCriterion("width >", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThanOrEqualTo(Integer value) {
            addCriterion("width >=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThan(Integer value) {
            addCriterion("width <", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThanOrEqualTo(Integer value) {
            addCriterion("width <=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthIn(List<Integer> values) {
            addCriterion("width in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotIn(List<Integer> values) {
            addCriterion("width not in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthBetween(Integer value1, Integer value2) {
            addCriterion("width between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotBetween(Integer value1, Integer value2) {
            addCriterion("width not between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andListWidthIsNull() {
            addCriterion("list_width is null");
            return (Criteria) this;
        }

        public Criteria andListWidthIsNotNull() {
            addCriterion("list_width is not null");
            return (Criteria) this;
        }

        public Criteria andListWidthEqualTo(Integer value) {
            addCriterion("list_width =", value, "listWidth");
            return (Criteria) this;
        }

        public Criteria andListWidthNotEqualTo(Integer value) {
            addCriterion("list_width <>", value, "listWidth");
            return (Criteria) this;
        }

        public Criteria andListWidthGreaterThan(Integer value) {
            addCriterion("list_width >", value, "listWidth");
            return (Criteria) this;
        }

        public Criteria andListWidthGreaterThanOrEqualTo(Integer value) {
            addCriterion("list_width >=", value, "listWidth");
            return (Criteria) this;
        }

        public Criteria andListWidthLessThan(Integer value) {
            addCriterion("list_width <", value, "listWidth");
            return (Criteria) this;
        }

        public Criteria andListWidthLessThanOrEqualTo(Integer value) {
            addCriterion("list_width <=", value, "listWidth");
            return (Criteria) this;
        }

        public Criteria andListWidthIn(List<Integer> values) {
            addCriterion("list_width in", values, "listWidth");
            return (Criteria) this;
        }

        public Criteria andListWidthNotIn(List<Integer> values) {
            addCriterion("list_width not in", values, "listWidth");
            return (Criteria) this;
        }

        public Criteria andListWidthBetween(Integer value1, Integer value2) {
            addCriterion("list_width between", value1, value2, "listWidth");
            return (Criteria) this;
        }

        public Criteria andListWidthNotBetween(Integer value1, Integer value2) {
            addCriterion("list_width not between", value1, value2, "listWidth");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataViewIsNull() {
            addCriterion("bis_cache_data_view is null");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataViewIsNotNull() {
            addCriterion("bis_cache_data_view is not null");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataViewEqualTo(Boolean value) {
            addCriterion("bis_cache_data_view =", value, "bisCacheDataView");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataViewNotEqualTo(Boolean value) {
            addCriterion("bis_cache_data_view <>", value, "bisCacheDataView");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataViewGreaterThan(Boolean value) {
            addCriterion("bis_cache_data_view >", value, "bisCacheDataView");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataViewGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_cache_data_view >=", value, "bisCacheDataView");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataViewLessThan(Boolean value) {
            addCriterion("bis_cache_data_view <", value, "bisCacheDataView");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataViewLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_cache_data_view <=", value, "bisCacheDataView");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataViewIn(List<Boolean> values) {
            addCriterion("bis_cache_data_view in", values, "bisCacheDataView");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataViewNotIn(List<Boolean> values) {
            addCriterion("bis_cache_data_view not in", values, "bisCacheDataView");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataViewBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_cache_data_view between", value1, value2, "bisCacheDataView");
            return (Criteria) this;
        }

        public Criteria andBisCacheDataViewNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_cache_data_view not between", value1, value2, "bisCacheDataView");
            return (Criteria) this;
        }

        public Criteria andBisJsonIsNull() {
            addCriterion("bis_json is null");
            return (Criteria) this;
        }

        public Criteria andBisJsonIsNotNull() {
            addCriterion("bis_json is not null");
            return (Criteria) this;
        }

        public Criteria andBisJsonEqualTo(Boolean value) {
            addCriterion("bis_json =", value, "bisJson");
            return (Criteria) this;
        }

        public Criteria andBisJsonNotEqualTo(Boolean value) {
            addCriterion("bis_json <>", value, "bisJson");
            return (Criteria) this;
        }

        public Criteria andBisJsonGreaterThan(Boolean value) {
            addCriterion("bis_json >", value, "bisJson");
            return (Criteria) this;
        }

        public Criteria andBisJsonGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_json >=", value, "bisJson");
            return (Criteria) this;
        }

        public Criteria andBisJsonLessThan(Boolean value) {
            addCriterion("bis_json <", value, "bisJson");
            return (Criteria) this;
        }

        public Criteria andBisJsonLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_json <=", value, "bisJson");
            return (Criteria) this;
        }

        public Criteria andBisJsonIn(List<Boolean> values) {
            addCriterion("bis_json in", values, "bisJson");
            return (Criteria) this;
        }

        public Criteria andBisJsonNotIn(List<Boolean> values) {
            addCriterion("bis_json not in", values, "bisJson");
            return (Criteria) this;
        }

        public Criteria andBisJsonBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_json between", value1, value2, "bisJson");
            return (Criteria) this;
        }

        public Criteria andBisJsonNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_json not between", value1, value2, "bisJson");
            return (Criteria) this;
        }

        public Criteria andBisRequiredIsNull() {
            addCriterion("bis_required is null");
            return (Criteria) this;
        }

        public Criteria andBisRequiredIsNotNull() {
            addCriterion("bis_required is not null");
            return (Criteria) this;
        }

        public Criteria andBisRequiredEqualTo(Boolean value) {
            addCriterion("bis_required =", value, "bisRequired");
            return (Criteria) this;
        }

        public Criteria andBisRequiredNotEqualTo(Boolean value) {
            addCriterion("bis_required <>", value, "bisRequired");
            return (Criteria) this;
        }

        public Criteria andBisRequiredGreaterThan(Boolean value) {
            addCriterion("bis_required >", value, "bisRequired");
            return (Criteria) this;
        }

        public Criteria andBisRequiredGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_required >=", value, "bisRequired");
            return (Criteria) this;
        }

        public Criteria andBisRequiredLessThan(Boolean value) {
            addCriterion("bis_required <", value, "bisRequired");
            return (Criteria) this;
        }

        public Criteria andBisRequiredLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_required <=", value, "bisRequired");
            return (Criteria) this;
        }

        public Criteria andBisRequiredIn(List<Boolean> values) {
            addCriterion("bis_required in", values, "bisRequired");
            return (Criteria) this;
        }

        public Criteria andBisRequiredNotIn(List<Boolean> values) {
            addCriterion("bis_required not in", values, "bisRequired");
            return (Criteria) this;
        }

        public Criteria andBisRequiredBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_required between", value1, value2, "bisRequired");
            return (Criteria) this;
        }

        public Criteria andBisRequiredNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_required not between", value1, value2, "bisRequired");
            return (Criteria) this;
        }

        public Criteria andBisShowIsNull() {
            addCriterion("bis_show is null");
            return (Criteria) this;
        }

        public Criteria andBisShowIsNotNull() {
            addCriterion("bis_show is not null");
            return (Criteria) this;
        }

        public Criteria andBisShowEqualTo(Boolean value) {
            addCriterion("bis_show =", value, "bisShow");
            return (Criteria) this;
        }

        public Criteria andBisShowNotEqualTo(Boolean value) {
            addCriterion("bis_show <>", value, "bisShow");
            return (Criteria) this;
        }

        public Criteria andBisShowGreaterThan(Boolean value) {
            addCriterion("bis_show >", value, "bisShow");
            return (Criteria) this;
        }

        public Criteria andBisShowGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_show >=", value, "bisShow");
            return (Criteria) this;
        }

        public Criteria andBisShowLessThan(Boolean value) {
            addCriterion("bis_show <", value, "bisShow");
            return (Criteria) this;
        }

        public Criteria andBisShowLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_show <=", value, "bisShow");
            return (Criteria) this;
        }

        public Criteria andBisShowIn(List<Boolean> values) {
            addCriterion("bis_show in", values, "bisShow");
            return (Criteria) this;
        }

        public Criteria andBisShowNotIn(List<Boolean> values) {
            addCriterion("bis_show not in", values, "bisShow");
            return (Criteria) this;
        }

        public Criteria andBisShowBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_show between", value1, value2, "bisShow");
            return (Criteria) this;
        }

        public Criteria andBisShowNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_show not between", value1, value2, "bisShow");
            return (Criteria) this;
        }

        public Criteria andBisListShowIsNull() {
            addCriterion("bis_list_show is null");
            return (Criteria) this;
        }

        public Criteria andBisListShowIsNotNull() {
            addCriterion("bis_list_show is not null");
            return (Criteria) this;
        }

        public Criteria andBisListShowEqualTo(Boolean value) {
            addCriterion("bis_list_show =", value, "bisListShow");
            return (Criteria) this;
        }

        public Criteria andBisListShowNotEqualTo(Boolean value) {
            addCriterion("bis_list_show <>", value, "bisListShow");
            return (Criteria) this;
        }

        public Criteria andBisListShowGreaterThan(Boolean value) {
            addCriterion("bis_list_show >", value, "bisListShow");
            return (Criteria) this;
        }

        public Criteria andBisListShowGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_list_show >=", value, "bisListShow");
            return (Criteria) this;
        }

        public Criteria andBisListShowLessThan(Boolean value) {
            addCriterion("bis_list_show <", value, "bisListShow");
            return (Criteria) this;
        }

        public Criteria andBisListShowLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_list_show <=", value, "bisListShow");
            return (Criteria) this;
        }

        public Criteria andBisListShowIn(List<Boolean> values) {
            addCriterion("bis_list_show in", values, "bisListShow");
            return (Criteria) this;
        }

        public Criteria andBisListShowNotIn(List<Boolean> values) {
            addCriterion("bis_list_show not in", values, "bisListShow");
            return (Criteria) this;
        }

        public Criteria andBisListShowBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_list_show between", value1, value2, "bisListShow");
            return (Criteria) this;
        }

        public Criteria andBisListShowNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_list_show not between", value1, value2, "bisListShow");
            return (Criteria) this;
        }

        public Criteria andBisAloneLineIsNull() {
            addCriterion("bis_alone_line is null");
            return (Criteria) this;
        }

        public Criteria andBisAloneLineIsNotNull() {
            addCriterion("bis_alone_line is not null");
            return (Criteria) this;
        }

        public Criteria andBisAloneLineEqualTo(Boolean value) {
            addCriterion("bis_alone_line =", value, "bisAloneLine");
            return (Criteria) this;
        }

        public Criteria andBisAloneLineNotEqualTo(Boolean value) {
            addCriterion("bis_alone_line <>", value, "bisAloneLine");
            return (Criteria) this;
        }

        public Criteria andBisAloneLineGreaterThan(Boolean value) {
            addCriterion("bis_alone_line >", value, "bisAloneLine");
            return (Criteria) this;
        }

        public Criteria andBisAloneLineGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_alone_line >=", value, "bisAloneLine");
            return (Criteria) this;
        }

        public Criteria andBisAloneLineLessThan(Boolean value) {
            addCriterion("bis_alone_line <", value, "bisAloneLine");
            return (Criteria) this;
        }

        public Criteria andBisAloneLineLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_alone_line <=", value, "bisAloneLine");
            return (Criteria) this;
        }

        public Criteria andBisAloneLineIn(List<Boolean> values) {
            addCriterion("bis_alone_line in", values, "bisAloneLine");
            return (Criteria) this;
        }

        public Criteria andBisAloneLineNotIn(List<Boolean> values) {
            addCriterion("bis_alone_line not in", values, "bisAloneLine");
            return (Criteria) this;
        }

        public Criteria andBisAloneLineBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_alone_line between", value1, value2, "bisAloneLine");
            return (Criteria) this;
        }

        public Criteria andBisAloneLineNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_alone_line not between", value1, value2, "bisAloneLine");
            return (Criteria) this;
        }

        public Criteria andBisQueryIsNull() {
            addCriterion("bis_query is null");
            return (Criteria) this;
        }

        public Criteria andBisQueryIsNotNull() {
            addCriterion("bis_query is not null");
            return (Criteria) this;
        }

        public Criteria andBisQueryEqualTo(Boolean value) {
            addCriterion("bis_query =", value, "bisQuery");
            return (Criteria) this;
        }

        public Criteria andBisQueryNotEqualTo(Boolean value) {
            addCriterion("bis_query <>", value, "bisQuery");
            return (Criteria) this;
        }

        public Criteria andBisQueryGreaterThan(Boolean value) {
            addCriterion("bis_query >", value, "bisQuery");
            return (Criteria) this;
        }

        public Criteria andBisQueryGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_query >=", value, "bisQuery");
            return (Criteria) this;
        }

        public Criteria andBisQueryLessThan(Boolean value) {
            addCriterion("bis_query <", value, "bisQuery");
            return (Criteria) this;
        }

        public Criteria andBisQueryLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_query <=", value, "bisQuery");
            return (Criteria) this;
        }

        public Criteria andBisQueryIn(List<Boolean> values) {
            addCriterion("bis_query in", values, "bisQuery");
            return (Criteria) this;
        }

        public Criteria andBisQueryNotIn(List<Boolean> values) {
            addCriterion("bis_query not in", values, "bisQuery");
            return (Criteria) this;
        }

        public Criteria andBisQueryBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_query between", value1, value2, "bisQuery");
            return (Criteria) this;
        }

        public Criteria andBisQueryNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_query not between", value1, value2, "bisQuery");
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