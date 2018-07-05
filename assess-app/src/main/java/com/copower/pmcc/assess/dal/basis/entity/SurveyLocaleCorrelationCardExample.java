package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.List;

public class SurveyLocaleCorrelationCardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SurveyLocaleCorrelationCardExample() {
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

        public Criteria andCorrelationCardIsNull() {
            addCriterion("correlation_card is null");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardIsNotNull() {
            addCriterion("correlation_card is not null");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardEqualTo(String value) {
            addCriterion("correlation_card =", value, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardNotEqualTo(String value) {
            addCriterion("correlation_card <>", value, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardGreaterThan(String value) {
            addCriterion("correlation_card >", value, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardGreaterThanOrEqualTo(String value) {
            addCriterion("correlation_card >=", value, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardLessThan(String value) {
            addCriterion("correlation_card <", value, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardLessThanOrEqualTo(String value) {
            addCriterion("correlation_card <=", value, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardLike(String value) {
            addCriterion("correlation_card like", value, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardNotLike(String value) {
            addCriterion("correlation_card not like", value, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardIn(List<String> values) {
            addCriterion("correlation_card in", values, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardNotIn(List<String> values) {
            addCriterion("correlation_card not in", values, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardBetween(String value1, String value2) {
            addCriterion("correlation_card between", value1, value2, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardNotBetween(String value1, String value2) {
            addCriterion("correlation_card not between", value1, value2, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andHouseNameIsNull() {
            addCriterion("house_name is null");
            return (Criteria) this;
        }

        public Criteria andHouseNameIsNotNull() {
            addCriterion("house_name is not null");
            return (Criteria) this;
        }

        public Criteria andHouseNameEqualTo(String value) {
            addCriterion("house_name =", value, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameNotEqualTo(String value) {
            addCriterion("house_name <>", value, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameGreaterThan(String value) {
            addCriterion("house_name >", value, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameGreaterThanOrEqualTo(String value) {
            addCriterion("house_name >=", value, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameLessThan(String value) {
            addCriterion("house_name <", value, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameLessThanOrEqualTo(String value) {
            addCriterion("house_name <=", value, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameLike(String value) {
            addCriterion("house_name like", value, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameNotLike(String value) {
            addCriterion("house_name not like", value, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameIn(List<String> values) {
            addCriterion("house_name in", values, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameNotIn(List<String> values) {
            addCriterion("house_name not in", values, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameBetween(String value1, String value2) {
            addCriterion("house_name between", value1, value2, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameNotBetween(String value1, String value2) {
            addCriterion("house_name not between", value1, value2, "houseName");
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