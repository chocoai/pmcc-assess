package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.List;

public class FuniHousesBuildUnitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FuniHousesBuildUnitExample() {
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

        public Criteria andLpbhIsNull() {
            addCriterion("lpbh is null");
            return (Criteria) this;
        }

        public Criteria andLpbhIsNotNull() {
            addCriterion("lpbh is not null");
            return (Criteria) this;
        }

        public Criteria andLpbhEqualTo(Integer value) {
            addCriterion("lpbh =", value, "lpbh");
            return (Criteria) this;
        }

        public Criteria andLpbhNotEqualTo(Integer value) {
            addCriterion("lpbh <>", value, "lpbh");
            return (Criteria) this;
        }

        public Criteria andLpbhGreaterThan(Integer value) {
            addCriterion("lpbh >", value, "lpbh");
            return (Criteria) this;
        }

        public Criteria andLpbhGreaterThanOrEqualTo(Integer value) {
            addCriterion("lpbh >=", value, "lpbh");
            return (Criteria) this;
        }

        public Criteria andLpbhLessThan(Integer value) {
            addCriterion("lpbh <", value, "lpbh");
            return (Criteria) this;
        }

        public Criteria andLpbhLessThanOrEqualTo(Integer value) {
            addCriterion("lpbh <=", value, "lpbh");
            return (Criteria) this;
        }

        public Criteria andLpbhIn(List<Integer> values) {
            addCriterion("lpbh in", values, "lpbh");
            return (Criteria) this;
        }

        public Criteria andLpbhNotIn(List<Integer> values) {
            addCriterion("lpbh not in", values, "lpbh");
            return (Criteria) this;
        }

        public Criteria andLpbhBetween(Integer value1, Integer value2) {
            addCriterion("lpbh between", value1, value2, "lpbh");
            return (Criteria) this;
        }

        public Criteria andLpbhNotBetween(Integer value1, Integer value2) {
            addCriterion("lpbh not between", value1, value2, "lpbh");
            return (Criteria) this;
        }

        public Criteria andLdbhIsNull() {
            addCriterion("ldbh is null");
            return (Criteria) this;
        }

        public Criteria andLdbhIsNotNull() {
            addCriterion("ldbh is not null");
            return (Criteria) this;
        }

        public Criteria andLdbhEqualTo(Integer value) {
            addCriterion("ldbh =", value, "ldbh");
            return (Criteria) this;
        }

        public Criteria andLdbhNotEqualTo(Integer value) {
            addCriterion("ldbh <>", value, "ldbh");
            return (Criteria) this;
        }

        public Criteria andLdbhGreaterThan(Integer value) {
            addCriterion("ldbh >", value, "ldbh");
            return (Criteria) this;
        }

        public Criteria andLdbhGreaterThanOrEqualTo(Integer value) {
            addCriterion("ldbh >=", value, "ldbh");
            return (Criteria) this;
        }

        public Criteria andLdbhLessThan(Integer value) {
            addCriterion("ldbh <", value, "ldbh");
            return (Criteria) this;
        }

        public Criteria andLdbhLessThanOrEqualTo(Integer value) {
            addCriterion("ldbh <=", value, "ldbh");
            return (Criteria) this;
        }

        public Criteria andLdbhIn(List<Integer> values) {
            addCriterion("ldbh in", values, "ldbh");
            return (Criteria) this;
        }

        public Criteria andLdbhNotIn(List<Integer> values) {
            addCriterion("ldbh not in", values, "ldbh");
            return (Criteria) this;
        }

        public Criteria andLdbhBetween(Integer value1, Integer value2) {
            addCriterion("ldbh between", value1, value2, "ldbh");
            return (Criteria) this;
        }

        public Criteria andLdbhNotBetween(Integer value1, Integer value2) {
            addCriterion("ldbh not between", value1, value2, "ldbh");
            return (Criteria) this;
        }

        public Criteria andDymcIsNull() {
            addCriterion("dymc is null");
            return (Criteria) this;
        }

        public Criteria andDymcIsNotNull() {
            addCriterion("dymc is not null");
            return (Criteria) this;
        }

        public Criteria andDymcEqualTo(String value) {
            addCriterion("dymc =", value, "dymc");
            return (Criteria) this;
        }

        public Criteria andDymcNotEqualTo(String value) {
            addCriterion("dymc <>", value, "dymc");
            return (Criteria) this;
        }

        public Criteria andDymcGreaterThan(String value) {
            addCriterion("dymc >", value, "dymc");
            return (Criteria) this;
        }

        public Criteria andDymcGreaterThanOrEqualTo(String value) {
            addCriterion("dymc >=", value, "dymc");
            return (Criteria) this;
        }

        public Criteria andDymcLessThan(String value) {
            addCriterion("dymc <", value, "dymc");
            return (Criteria) this;
        }

        public Criteria andDymcLessThanOrEqualTo(String value) {
            addCriterion("dymc <=", value, "dymc");
            return (Criteria) this;
        }

        public Criteria andDymcLike(String value) {
            addCriterion("dymc like", value, "dymc");
            return (Criteria) this;
        }

        public Criteria andDymcNotLike(String value) {
            addCriterion("dymc not like", value, "dymc");
            return (Criteria) this;
        }

        public Criteria andDymcIn(List<String> values) {
            addCriterion("dymc in", values, "dymc");
            return (Criteria) this;
        }

        public Criteria andDymcNotIn(List<String> values) {
            addCriterion("dymc not in", values, "dymc");
            return (Criteria) this;
        }

        public Criteria andDymcBetween(String value1, String value2) {
            addCriterion("dymc between", value1, value2, "dymc");
            return (Criteria) this;
        }

        public Criteria andDymcNotBetween(String value1, String value2) {
            addCriterion("dymc not between", value1, value2, "dymc");
            return (Criteria) this;
        }

        public Criteria andThbIsNull() {
            addCriterion("thb is null");
            return (Criteria) this;
        }

        public Criteria andThbIsNotNull() {
            addCriterion("thb is not null");
            return (Criteria) this;
        }

        public Criteria andThbEqualTo(String value) {
            addCriterion("thb =", value, "thb");
            return (Criteria) this;
        }

        public Criteria andThbNotEqualTo(String value) {
            addCriterion("thb <>", value, "thb");
            return (Criteria) this;
        }

        public Criteria andThbGreaterThan(String value) {
            addCriterion("thb >", value, "thb");
            return (Criteria) this;
        }

        public Criteria andThbGreaterThanOrEqualTo(String value) {
            addCriterion("thb >=", value, "thb");
            return (Criteria) this;
        }

        public Criteria andThbLessThan(String value) {
            addCriterion("thb <", value, "thb");
            return (Criteria) this;
        }

        public Criteria andThbLessThanOrEqualTo(String value) {
            addCriterion("thb <=", value, "thb");
            return (Criteria) this;
        }

        public Criteria andThbLike(String value) {
            addCriterion("thb like", value, "thb");
            return (Criteria) this;
        }

        public Criteria andThbNotLike(String value) {
            addCriterion("thb not like", value, "thb");
            return (Criteria) this;
        }

        public Criteria andThbIn(List<String> values) {
            addCriterion("thb in", values, "thb");
            return (Criteria) this;
        }

        public Criteria andThbNotIn(List<String> values) {
            addCriterion("thb not in", values, "thb");
            return (Criteria) this;
        }

        public Criteria andThbBetween(String value1, String value2) {
            addCriterion("thb between", value1, value2, "thb");
            return (Criteria) this;
        }

        public Criteria andThbNotBetween(String value1, String value2) {
            addCriterion("thb not between", value1, value2, "thb");
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