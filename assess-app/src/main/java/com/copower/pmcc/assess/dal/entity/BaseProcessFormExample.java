package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.List;

public class BaseProcessFormExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaseProcessFormExample() {
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

        public Criteria andProcessIdIsNull() {
            addCriterion("process_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessIdIsNotNull() {
            addCriterion("process_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessIdEqualTo(Integer value) {
            addCriterion("process_id =", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotEqualTo(Integer value) {
            addCriterion("process_id <>", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdGreaterThan(Integer value) {
            addCriterion("process_id >", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("process_id >=", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdLessThan(Integer value) {
            addCriterion("process_id <", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdLessThanOrEqualTo(Integer value) {
            addCriterion("process_id <=", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdIn(List<Integer> values) {
            addCriterion("process_id in", values, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotIn(List<Integer> values) {
            addCriterion("process_id not in", values, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdBetween(Integer value1, Integer value2) {
            addCriterion("process_id between", value1, value2, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotBetween(Integer value1, Integer value2) {
            addCriterion("process_id not between", value1, value2, "processId");
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

        public Criteria andFormModuleNameIsNull() {
            addCriterion("form_module_name is null");
            return (Criteria) this;
        }

        public Criteria andFormModuleNameIsNotNull() {
            addCriterion("form_module_name is not null");
            return (Criteria) this;
        }

        public Criteria andFormModuleNameEqualTo(String value) {
            addCriterion("form_module_name =", value, "formModuleName");
            return (Criteria) this;
        }

        public Criteria andFormModuleNameNotEqualTo(String value) {
            addCriterion("form_module_name <>", value, "formModuleName");
            return (Criteria) this;
        }

        public Criteria andFormModuleNameGreaterThan(String value) {
            addCriterion("form_module_name >", value, "formModuleName");
            return (Criteria) this;
        }

        public Criteria andFormModuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("form_module_name >=", value, "formModuleName");
            return (Criteria) this;
        }

        public Criteria andFormModuleNameLessThan(String value) {
            addCriterion("form_module_name <", value, "formModuleName");
            return (Criteria) this;
        }

        public Criteria andFormModuleNameLessThanOrEqualTo(String value) {
            addCriterion("form_module_name <=", value, "formModuleName");
            return (Criteria) this;
        }

        public Criteria andFormModuleNameLike(String value) {
            addCriterion("form_module_name like", value, "formModuleName");
            return (Criteria) this;
        }

        public Criteria andFormModuleNameNotLike(String value) {
            addCriterion("form_module_name not like", value, "formModuleName");
            return (Criteria) this;
        }

        public Criteria andFormModuleNameIn(List<String> values) {
            addCriterion("form_module_name in", values, "formModuleName");
            return (Criteria) this;
        }

        public Criteria andFormModuleNameNotIn(List<String> values) {
            addCriterion("form_module_name not in", values, "formModuleName");
            return (Criteria) this;
        }

        public Criteria andFormModuleNameBetween(String value1, String value2) {
            addCriterion("form_module_name between", value1, value2, "formModuleName");
            return (Criteria) this;
        }

        public Criteria andFormModuleNameNotBetween(String value1, String value2) {
            addCriterion("form_module_name not between", value1, value2, "formModuleName");
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

        public Criteria andBoxReActivityNameIsNull() {
            addCriterion("box_re_activity_name is null");
            return (Criteria) this;
        }

        public Criteria andBoxReActivityNameIsNotNull() {
            addCriterion("box_re_activity_name is not null");
            return (Criteria) this;
        }

        public Criteria andBoxReActivityNameEqualTo(String value) {
            addCriterion("box_re_activity_name =", value, "boxReActivityName");
            return (Criteria) this;
        }

        public Criteria andBoxReActivityNameNotEqualTo(String value) {
            addCriterion("box_re_activity_name <>", value, "boxReActivityName");
            return (Criteria) this;
        }

        public Criteria andBoxReActivityNameGreaterThan(String value) {
            addCriterion("box_re_activity_name >", value, "boxReActivityName");
            return (Criteria) this;
        }

        public Criteria andBoxReActivityNameGreaterThanOrEqualTo(String value) {
            addCriterion("box_re_activity_name >=", value, "boxReActivityName");
            return (Criteria) this;
        }

        public Criteria andBoxReActivityNameLessThan(String value) {
            addCriterion("box_re_activity_name <", value, "boxReActivityName");
            return (Criteria) this;
        }

        public Criteria andBoxReActivityNameLessThanOrEqualTo(String value) {
            addCriterion("box_re_activity_name <=", value, "boxReActivityName");
            return (Criteria) this;
        }

        public Criteria andBoxReActivityNameLike(String value) {
            addCriterion("box_re_activity_name like", value, "boxReActivityName");
            return (Criteria) this;
        }

        public Criteria andBoxReActivityNameNotLike(String value) {
            addCriterion("box_re_activity_name not like", value, "boxReActivityName");
            return (Criteria) this;
        }

        public Criteria andBoxReActivityNameIn(List<String> values) {
            addCriterion("box_re_activity_name in", values, "boxReActivityName");
            return (Criteria) this;
        }

        public Criteria andBoxReActivityNameNotIn(List<String> values) {
            addCriterion("box_re_activity_name not in", values, "boxReActivityName");
            return (Criteria) this;
        }

        public Criteria andBoxReActivityNameBetween(String value1, String value2) {
            addCriterion("box_re_activity_name between", value1, value2, "boxReActivityName");
            return (Criteria) this;
        }

        public Criteria andBoxReActivityNameNotBetween(String value1, String value2) {
            addCriterion("box_re_activity_name not between", value1, value2, "boxReActivityName");
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