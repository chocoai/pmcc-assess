package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.List;

public class ReportTemplateBookmarkExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReportTemplateBookmarkExample() {
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

        public Criteria andTemplateIdIsNull() {
            addCriterion("template_id is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNotNull() {
            addCriterion("template_id is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdEqualTo(Integer value) {
            addCriterion("template_id =", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotEqualTo(Integer value) {
            addCriterion("template_id <>", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThan(Integer value) {
            addCriterion("template_id >", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("template_id >=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThan(Integer value) {
            addCriterion("template_id <", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThanOrEqualTo(Integer value) {
            addCriterion("template_id <=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIn(List<Integer> values) {
            addCriterion("template_id in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotIn(List<Integer> values) {
            addCriterion("template_id not in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdBetween(Integer value1, Integer value2) {
            addCriterion("template_id between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("template_id not between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeIsNull() {
            addCriterion("template_type is null");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeIsNotNull() {
            addCriterion("template_type is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeEqualTo(Integer value) {
            addCriterion("template_type =", value, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeNotEqualTo(Integer value) {
            addCriterion("template_type <>", value, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeGreaterThan(Integer value) {
            addCriterion("template_type >", value, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("template_type >=", value, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeLessThan(Integer value) {
            addCriterion("template_type <", value, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeLessThanOrEqualTo(Integer value) {
            addCriterion("template_type <=", value, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeIn(List<Integer> values) {
            addCriterion("template_type in", values, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeNotIn(List<Integer> values) {
            addCriterion("template_type not in", values, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeBetween(Integer value1, Integer value2) {
            addCriterion("template_type between", value1, value2, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("template_type not between", value1, value2, "templateType");
            return (Criteria) this;
        }

        public Criteria andBookmarkNameIsNull() {
            addCriterion("bookmark_name is null");
            return (Criteria) this;
        }

        public Criteria andBookmarkNameIsNotNull() {
            addCriterion("bookmark_name is not null");
            return (Criteria) this;
        }

        public Criteria andBookmarkNameEqualTo(String value) {
            addCriterion("bookmark_name =", value, "bookmarkName");
            return (Criteria) this;
        }

        public Criteria andBookmarkNameNotEqualTo(String value) {
            addCriterion("bookmark_name <>", value, "bookmarkName");
            return (Criteria) this;
        }

        public Criteria andBookmarkNameGreaterThan(String value) {
            addCriterion("bookmark_name >", value, "bookmarkName");
            return (Criteria) this;
        }

        public Criteria andBookmarkNameGreaterThanOrEqualTo(String value) {
            addCriterion("bookmark_name >=", value, "bookmarkName");
            return (Criteria) this;
        }

        public Criteria andBookmarkNameLessThan(String value) {
            addCriterion("bookmark_name <", value, "bookmarkName");
            return (Criteria) this;
        }

        public Criteria andBookmarkNameLessThanOrEqualTo(String value) {
            addCriterion("bookmark_name <=", value, "bookmarkName");
            return (Criteria) this;
        }

        public Criteria andBookmarkNameLike(String value) {
            addCriterion("bookmark_name like", value, "bookmarkName");
            return (Criteria) this;
        }

        public Criteria andBookmarkNameNotLike(String value) {
            addCriterion("bookmark_name not like", value, "bookmarkName");
            return (Criteria) this;
        }

        public Criteria andBookmarkNameIn(List<String> values) {
            addCriterion("bookmark_name in", values, "bookmarkName");
            return (Criteria) this;
        }

        public Criteria andBookmarkNameNotIn(List<String> values) {
            addCriterion("bookmark_name not in", values, "bookmarkName");
            return (Criteria) this;
        }

        public Criteria andBookmarkNameBetween(String value1, String value2) {
            addCriterion("bookmark_name between", value1, value2, "bookmarkName");
            return (Criteria) this;
        }

        public Criteria andBookmarkNameNotBetween(String value1, String value2) {
            addCriterion("bookmark_name not between", value1, value2, "bookmarkName");
            return (Criteria) this;
        }

        public Criteria andDataPoolTypeIsNull() {
            addCriterion("data_pool_type is null");
            return (Criteria) this;
        }

        public Criteria andDataPoolTypeIsNotNull() {
            addCriterion("data_pool_type is not null");
            return (Criteria) this;
        }

        public Criteria andDataPoolTypeEqualTo(Integer value) {
            addCriterion("data_pool_type =", value, "dataPoolType");
            return (Criteria) this;
        }

        public Criteria andDataPoolTypeNotEqualTo(Integer value) {
            addCriterion("data_pool_type <>", value, "dataPoolType");
            return (Criteria) this;
        }

        public Criteria andDataPoolTypeGreaterThan(Integer value) {
            addCriterion("data_pool_type >", value, "dataPoolType");
            return (Criteria) this;
        }

        public Criteria andDataPoolTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_pool_type >=", value, "dataPoolType");
            return (Criteria) this;
        }

        public Criteria andDataPoolTypeLessThan(Integer value) {
            addCriterion("data_pool_type <", value, "dataPoolType");
            return (Criteria) this;
        }

        public Criteria andDataPoolTypeLessThanOrEqualTo(Integer value) {
            addCriterion("data_pool_type <=", value, "dataPoolType");
            return (Criteria) this;
        }

        public Criteria andDataPoolTypeIn(List<Integer> values) {
            addCriterion("data_pool_type in", values, "dataPoolType");
            return (Criteria) this;
        }

        public Criteria andDataPoolTypeNotIn(List<Integer> values) {
            addCriterion("data_pool_type not in", values, "dataPoolType");
            return (Criteria) this;
        }

        public Criteria andDataPoolTypeBetween(Integer value1, Integer value2) {
            addCriterion("data_pool_type between", value1, value2, "dataPoolType");
            return (Criteria) this;
        }

        public Criteria andDataPoolTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("data_pool_type not between", value1, value2, "dataPoolType");
            return (Criteria) this;
        }

        public Criteria andDataPoolTableIdIsNull() {
            addCriterion("data_pool_table_id is null");
            return (Criteria) this;
        }

        public Criteria andDataPoolTableIdIsNotNull() {
            addCriterion("data_pool_table_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataPoolTableIdEqualTo(Integer value) {
            addCriterion("data_pool_table_id =", value, "dataPoolTableId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTableIdNotEqualTo(Integer value) {
            addCriterion("data_pool_table_id <>", value, "dataPoolTableId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTableIdGreaterThan(Integer value) {
            addCriterion("data_pool_table_id >", value, "dataPoolTableId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_pool_table_id >=", value, "dataPoolTableId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTableIdLessThan(Integer value) {
            addCriterion("data_pool_table_id <", value, "dataPoolTableId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTableIdLessThanOrEqualTo(Integer value) {
            addCriterion("data_pool_table_id <=", value, "dataPoolTableId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTableIdIn(List<Integer> values) {
            addCriterion("data_pool_table_id in", values, "dataPoolTableId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTableIdNotIn(List<Integer> values) {
            addCriterion("data_pool_table_id not in", values, "dataPoolTableId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTableIdBetween(Integer value1, Integer value2) {
            addCriterion("data_pool_table_id between", value1, value2, "dataPoolTableId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("data_pool_table_id not between", value1, value2, "dataPoolTableId");
            return (Criteria) this;
        }

        public Criteria andDataPoolColumnsIdIsNull() {
            addCriterion("data_pool_columns_id is null");
            return (Criteria) this;
        }

        public Criteria andDataPoolColumnsIdIsNotNull() {
            addCriterion("data_pool_columns_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataPoolColumnsIdEqualTo(Integer value) {
            addCriterion("data_pool_columns_id =", value, "dataPoolColumnsId");
            return (Criteria) this;
        }

        public Criteria andDataPoolColumnsIdNotEqualTo(Integer value) {
            addCriterion("data_pool_columns_id <>", value, "dataPoolColumnsId");
            return (Criteria) this;
        }

        public Criteria andDataPoolColumnsIdGreaterThan(Integer value) {
            addCriterion("data_pool_columns_id >", value, "dataPoolColumnsId");
            return (Criteria) this;
        }

        public Criteria andDataPoolColumnsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_pool_columns_id >=", value, "dataPoolColumnsId");
            return (Criteria) this;
        }

        public Criteria andDataPoolColumnsIdLessThan(Integer value) {
            addCriterion("data_pool_columns_id <", value, "dataPoolColumnsId");
            return (Criteria) this;
        }

        public Criteria andDataPoolColumnsIdLessThanOrEqualTo(Integer value) {
            addCriterion("data_pool_columns_id <=", value, "dataPoolColumnsId");
            return (Criteria) this;
        }

        public Criteria andDataPoolColumnsIdIn(List<Integer> values) {
            addCriterion("data_pool_columns_id in", values, "dataPoolColumnsId");
            return (Criteria) this;
        }

        public Criteria andDataPoolColumnsIdNotIn(List<Integer> values) {
            addCriterion("data_pool_columns_id not in", values, "dataPoolColumnsId");
            return (Criteria) this;
        }

        public Criteria andDataPoolColumnsIdBetween(Integer value1, Integer value2) {
            addCriterion("data_pool_columns_id between", value1, value2, "dataPoolColumnsId");
            return (Criteria) this;
        }

        public Criteria andDataPoolColumnsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("data_pool_columns_id not between", value1, value2, "dataPoolColumnsId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTemplateIdIsNull() {
            addCriterion("data_pool_template_id is null");
            return (Criteria) this;
        }

        public Criteria andDataPoolTemplateIdIsNotNull() {
            addCriterion("data_pool_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataPoolTemplateIdEqualTo(Integer value) {
            addCriterion("data_pool_template_id =", value, "dataPoolTemplateId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTemplateIdNotEqualTo(Integer value) {
            addCriterion("data_pool_template_id <>", value, "dataPoolTemplateId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTemplateIdGreaterThan(Integer value) {
            addCriterion("data_pool_template_id >", value, "dataPoolTemplateId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTemplateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_pool_template_id >=", value, "dataPoolTemplateId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTemplateIdLessThan(Integer value) {
            addCriterion("data_pool_template_id <", value, "dataPoolTemplateId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTemplateIdLessThanOrEqualTo(Integer value) {
            addCriterion("data_pool_template_id <=", value, "dataPoolTemplateId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTemplateIdIn(List<Integer> values) {
            addCriterion("data_pool_template_id in", values, "dataPoolTemplateId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTemplateIdNotIn(List<Integer> values) {
            addCriterion("data_pool_template_id not in", values, "dataPoolTemplateId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTemplateIdBetween(Integer value1, Integer value2) {
            addCriterion("data_pool_template_id between", value1, value2, "dataPoolTemplateId");
            return (Criteria) this;
        }

        public Criteria andDataPoolTemplateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("data_pool_template_id not between", value1, value2, "dataPoolTemplateId");
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