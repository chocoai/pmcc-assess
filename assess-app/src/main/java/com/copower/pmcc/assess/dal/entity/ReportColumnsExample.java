package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.List;

public class ReportColumnsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReportColumnsExample() {
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

        public Criteria andColumnsNameIsNull() {
            addCriterion("columns_name is null");
            return (Criteria) this;
        }

        public Criteria andColumnsNameIsNotNull() {
            addCriterion("columns_name is not null");
            return (Criteria) this;
        }

        public Criteria andColumnsNameEqualTo(String value) {
            addCriterion("columns_name =", value, "columnsName");
            return (Criteria) this;
        }

        public Criteria andColumnsNameNotEqualTo(String value) {
            addCriterion("columns_name <>", value, "columnsName");
            return (Criteria) this;
        }

        public Criteria andColumnsNameGreaterThan(String value) {
            addCriterion("columns_name >", value, "columnsName");
            return (Criteria) this;
        }

        public Criteria andColumnsNameGreaterThanOrEqualTo(String value) {
            addCriterion("columns_name >=", value, "columnsName");
            return (Criteria) this;
        }

        public Criteria andColumnsNameLessThan(String value) {
            addCriterion("columns_name <", value, "columnsName");
            return (Criteria) this;
        }

        public Criteria andColumnsNameLessThanOrEqualTo(String value) {
            addCriterion("columns_name <=", value, "columnsName");
            return (Criteria) this;
        }

        public Criteria andColumnsNameLike(String value) {
            addCriterion("columns_name like", value, "columnsName");
            return (Criteria) this;
        }

        public Criteria andColumnsNameNotLike(String value) {
            addCriterion("columns_name not like", value, "columnsName");
            return (Criteria) this;
        }

        public Criteria andColumnsNameIn(List<String> values) {
            addCriterion("columns_name in", values, "columnsName");
            return (Criteria) this;
        }

        public Criteria andColumnsNameNotIn(List<String> values) {
            addCriterion("columns_name not in", values, "columnsName");
            return (Criteria) this;
        }

        public Criteria andColumnsNameBetween(String value1, String value2) {
            addCriterion("columns_name between", value1, value2, "columnsName");
            return (Criteria) this;
        }

        public Criteria andColumnsNameNotBetween(String value1, String value2) {
            addCriterion("columns_name not between", value1, value2, "columnsName");
            return (Criteria) this;
        }

        public Criteria andColumnsCnNameIsNull() {
            addCriterion("columns_cn_name is null");
            return (Criteria) this;
        }

        public Criteria andColumnsCnNameIsNotNull() {
            addCriterion("columns_cn_name is not null");
            return (Criteria) this;
        }

        public Criteria andColumnsCnNameEqualTo(String value) {
            addCriterion("columns_cn_name =", value, "columnsCnName");
            return (Criteria) this;
        }

        public Criteria andColumnsCnNameNotEqualTo(String value) {
            addCriterion("columns_cn_name <>", value, "columnsCnName");
            return (Criteria) this;
        }

        public Criteria andColumnsCnNameGreaterThan(String value) {
            addCriterion("columns_cn_name >", value, "columnsCnName");
            return (Criteria) this;
        }

        public Criteria andColumnsCnNameGreaterThanOrEqualTo(String value) {
            addCriterion("columns_cn_name >=", value, "columnsCnName");
            return (Criteria) this;
        }

        public Criteria andColumnsCnNameLessThan(String value) {
            addCriterion("columns_cn_name <", value, "columnsCnName");
            return (Criteria) this;
        }

        public Criteria andColumnsCnNameLessThanOrEqualTo(String value) {
            addCriterion("columns_cn_name <=", value, "columnsCnName");
            return (Criteria) this;
        }

        public Criteria andColumnsCnNameLike(String value) {
            addCriterion("columns_cn_name like", value, "columnsCnName");
            return (Criteria) this;
        }

        public Criteria andColumnsCnNameNotLike(String value) {
            addCriterion("columns_cn_name not like", value, "columnsCnName");
            return (Criteria) this;
        }

        public Criteria andColumnsCnNameIn(List<String> values) {
            addCriterion("columns_cn_name in", values, "columnsCnName");
            return (Criteria) this;
        }

        public Criteria andColumnsCnNameNotIn(List<String> values) {
            addCriterion("columns_cn_name not in", values, "columnsCnName");
            return (Criteria) this;
        }

        public Criteria andColumnsCnNameBetween(String value1, String value2) {
            addCriterion("columns_cn_name between", value1, value2, "columnsCnName");
            return (Criteria) this;
        }

        public Criteria andColumnsCnNameNotBetween(String value1, String value2) {
            addCriterion("columns_cn_name not between", value1, value2, "columnsCnName");
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