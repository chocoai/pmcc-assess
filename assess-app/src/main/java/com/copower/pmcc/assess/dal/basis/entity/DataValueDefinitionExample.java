package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataValueDefinitionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataValueDefinitionExample() {
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

        public Criteria andEntrustmentPurposeIsNull() {
            addCriterion("entrustment_purpose is null");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeIsNotNull() {
            addCriterion("entrustment_purpose is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeEqualTo(String value) {
            addCriterion("entrustment_purpose =", value, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeNotEqualTo(String value) {
            addCriterion("entrustment_purpose <>", value, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeGreaterThan(String value) {
            addCriterion("entrustment_purpose >", value, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeGreaterThanOrEqualTo(String value) {
            addCriterion("entrustment_purpose >=", value, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeLessThan(String value) {
            addCriterion("entrustment_purpose <", value, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeLessThanOrEqualTo(String value) {
            addCriterion("entrustment_purpose <=", value, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeLike(String value) {
            addCriterion("entrustment_purpose like", value, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeNotLike(String value) {
            addCriterion("entrustment_purpose not like", value, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeIn(List<String> values) {
            addCriterion("entrustment_purpose in", values, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeNotIn(List<String> values) {
            addCriterion("entrustment_purpose not in", values, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeBetween(String value1, String value2) {
            addCriterion("entrustment_purpose between", value1, value2, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeNotBetween(String value1, String value2) {
            addCriterion("entrustment_purpose not between", value1, value2, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andValueTypeIsNull() {
            addCriterion("value_type is null");
            return (Criteria) this;
        }

        public Criteria andValueTypeIsNotNull() {
            addCriterion("value_type is not null");
            return (Criteria) this;
        }

        public Criteria andValueTypeEqualTo(String value) {
            addCriterion("value_type =", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotEqualTo(String value) {
            addCriterion("value_type <>", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeGreaterThan(String value) {
            addCriterion("value_type >", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeGreaterThanOrEqualTo(String value) {
            addCriterion("value_type >=", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLessThan(String value) {
            addCriterion("value_type <", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLessThanOrEqualTo(String value) {
            addCriterion("value_type <=", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLike(String value) {
            addCriterion("value_type like", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotLike(String value) {
            addCriterion("value_type not like", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeIn(List<String> values) {
            addCriterion("value_type in", values, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotIn(List<String> values) {
            addCriterion("value_type not in", values, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeBetween(String value1, String value2) {
            addCriterion("value_type between", value1, value2, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotBetween(String value1, String value2) {
            addCriterion("value_type not between", value1, value2, "valueType");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeIsNull() {
            addCriterion("property_scope is null");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeIsNotNull() {
            addCriterion("property_scope is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeEqualTo(String value) {
            addCriterion("property_scope =", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNotEqualTo(String value) {
            addCriterion("property_scope <>", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeGreaterThan(String value) {
            addCriterion("property_scope >", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeGreaterThanOrEqualTo(String value) {
            addCriterion("property_scope >=", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeLessThan(String value) {
            addCriterion("property_scope <", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeLessThanOrEqualTo(String value) {
            addCriterion("property_scope <=", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeLike(String value) {
            addCriterion("property_scope like", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNotLike(String value) {
            addCriterion("property_scope not like", value, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeIn(List<String> values) {
            addCriterion("property_scope in", values, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNotIn(List<String> values) {
            addCriterion("property_scope not in", values, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeBetween(String value1, String value2) {
            addCriterion("property_scope between", value1, value2, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andPropertyScopeNotBetween(String value1, String value2) {
            addCriterion("property_scope not between", value1, value2, "propertyScope");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeIsNull() {
            addCriterion("scope_include is null");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeIsNotNull() {
            addCriterion("scope_include is not null");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeEqualTo(String value) {
            addCriterion("scope_include =", value, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeNotEqualTo(String value) {
            addCriterion("scope_include <>", value, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeGreaterThan(String value) {
            addCriterion("scope_include >", value, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeGreaterThanOrEqualTo(String value) {
            addCriterion("scope_include >=", value, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeLessThan(String value) {
            addCriterion("scope_include <", value, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeLessThanOrEqualTo(String value) {
            addCriterion("scope_include <=", value, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeLike(String value) {
            addCriterion("scope_include like", value, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeNotLike(String value) {
            addCriterion("scope_include not like", value, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeIn(List<String> values) {
            addCriterion("scope_include in", values, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeNotIn(List<String> values) {
            addCriterion("scope_include not in", values, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeBetween(String value1, String value2) {
            addCriterion("scope_include between", value1, value2, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeIncludeNotBetween(String value1, String value2) {
            addCriterion("scope_include not between", value1, value2, "scopeInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeIsNull() {
            addCriterion("scope_not_include is null");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeIsNotNull() {
            addCriterion("scope_not_include is not null");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeEqualTo(String value) {
            addCriterion("scope_not_include =", value, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeNotEqualTo(String value) {
            addCriterion("scope_not_include <>", value, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeGreaterThan(String value) {
            addCriterion("scope_not_include >", value, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeGreaterThanOrEqualTo(String value) {
            addCriterion("scope_not_include >=", value, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeLessThan(String value) {
            addCriterion("scope_not_include <", value, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeLessThanOrEqualTo(String value) {
            addCriterion("scope_not_include <=", value, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeLike(String value) {
            addCriterion("scope_not_include like", value, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeNotLike(String value) {
            addCriterion("scope_not_include not like", value, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeIn(List<String> values) {
            addCriterion("scope_not_include in", values, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeNotIn(List<String> values) {
            addCriterion("scope_not_include not in", values, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeBetween(String value1, String value2) {
            addCriterion("scope_not_include between", value1, value2, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andScopeNotIncludeNotBetween(String value1, String value2) {
            addCriterion("scope_not_include not between", value1, value2, "scopeNotInclude");
            return (Criteria) this;
        }

        public Criteria andTemplateIsNull() {
            addCriterion("template is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIsNotNull() {
            addCriterion("template is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateEqualTo(String value) {
            addCriterion("template =", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotEqualTo(String value) {
            addCriterion("template <>", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateGreaterThan(String value) {
            addCriterion("template >", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("template >=", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateLessThan(String value) {
            addCriterion("template <", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateLessThanOrEqualTo(String value) {
            addCriterion("template <=", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateLike(String value) {
            addCriterion("template like", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotLike(String value) {
            addCriterion("template not like", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateIn(List<String> values) {
            addCriterion("template in", values, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotIn(List<String> values) {
            addCriterion("template not in", values, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateBetween(String value1, String value2) {
            addCriterion("template between", value1, value2, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotBetween(String value1, String value2) {
            addCriterion("template not between", value1, value2, "template");
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

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andBisModifiableIsNull() {
            addCriterion("bis_modifiable is null");
            return (Criteria) this;
        }

        public Criteria andBisModifiableIsNotNull() {
            addCriterion("bis_modifiable is not null");
            return (Criteria) this;
        }

        public Criteria andBisModifiableEqualTo(Boolean value) {
            addCriterion("bis_modifiable =", value, "bisModifiable");
            return (Criteria) this;
        }

        public Criteria andBisModifiableNotEqualTo(Boolean value) {
            addCriterion("bis_modifiable <>", value, "bisModifiable");
            return (Criteria) this;
        }

        public Criteria andBisModifiableGreaterThan(Boolean value) {
            addCriterion("bis_modifiable >", value, "bisModifiable");
            return (Criteria) this;
        }

        public Criteria andBisModifiableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_modifiable >=", value, "bisModifiable");
            return (Criteria) this;
        }

        public Criteria andBisModifiableLessThan(Boolean value) {
            addCriterion("bis_modifiable <", value, "bisModifiable");
            return (Criteria) this;
        }

        public Criteria andBisModifiableLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_modifiable <=", value, "bisModifiable");
            return (Criteria) this;
        }

        public Criteria andBisModifiableIn(List<Boolean> values) {
            addCriterion("bis_modifiable in", values, "bisModifiable");
            return (Criteria) this;
        }

        public Criteria andBisModifiableNotIn(List<Boolean> values) {
            addCriterion("bis_modifiable not in", values, "bisModifiable");
            return (Criteria) this;
        }

        public Criteria andBisModifiableBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_modifiable between", value1, value2, "bisModifiable");
            return (Criteria) this;
        }

        public Criteria andBisModifiableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_modifiable not between", value1, value2, "bisModifiable");
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