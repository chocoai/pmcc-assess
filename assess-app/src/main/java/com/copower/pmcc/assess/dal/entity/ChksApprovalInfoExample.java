package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.List;

public class ChksApprovalInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChksApprovalInfoExample() {
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

        public Criteria andBoxIdIsNull() {
            addCriterion("box_id is null");
            return (Criteria) this;
        }

        public Criteria andBoxIdIsNotNull() {
            addCriterion("box_id is not null");
            return (Criteria) this;
        }

        public Criteria andBoxIdEqualTo(Integer value) {
            addCriterion("box_id =", value, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxIdNotEqualTo(Integer value) {
            addCriterion("box_id <>", value, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxIdGreaterThan(Integer value) {
            addCriterion("box_id >", value, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("box_id >=", value, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxIdLessThan(Integer value) {
            addCriterion("box_id <", value, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxIdLessThanOrEqualTo(Integer value) {
            addCriterion("box_id <=", value, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxIdIn(List<Integer> values) {
            addCriterion("box_id in", values, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxIdNotIn(List<Integer> values) {
            addCriterion("box_id not in", values, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxIdBetween(Integer value1, Integer value2) {
            addCriterion("box_id between", value1, value2, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxIdNotBetween(Integer value1, Integer value2) {
            addCriterion("box_id not between", value1, value2, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxActivityIdIsNull() {
            addCriterion("box_activity_id is null");
            return (Criteria) this;
        }

        public Criteria andBoxActivityIdIsNotNull() {
            addCriterion("box_activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andBoxActivityIdEqualTo(Integer value) {
            addCriterion("box_activity_id =", value, "boxActivityId");
            return (Criteria) this;
        }

        public Criteria andBoxActivityIdNotEqualTo(Integer value) {
            addCriterion("box_activity_id <>", value, "boxActivityId");
            return (Criteria) this;
        }

        public Criteria andBoxActivityIdGreaterThan(Integer value) {
            addCriterion("box_activity_id >", value, "boxActivityId");
            return (Criteria) this;
        }

        public Criteria andBoxActivityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("box_activity_id >=", value, "boxActivityId");
            return (Criteria) this;
        }

        public Criteria andBoxActivityIdLessThan(Integer value) {
            addCriterion("box_activity_id <", value, "boxActivityId");
            return (Criteria) this;
        }

        public Criteria andBoxActivityIdLessThanOrEqualTo(Integer value) {
            addCriterion("box_activity_id <=", value, "boxActivityId");
            return (Criteria) this;
        }

        public Criteria andBoxActivityIdIn(List<Integer> values) {
            addCriterion("box_activity_id in", values, "boxActivityId");
            return (Criteria) this;
        }

        public Criteria andBoxActivityIdNotIn(List<Integer> values) {
            addCriterion("box_activity_id not in", values, "boxActivityId");
            return (Criteria) this;
        }

        public Criteria andBoxActivityIdBetween(Integer value1, Integer value2) {
            addCriterion("box_activity_id between", value1, value2, "boxActivityId");
            return (Criteria) this;
        }

        public Criteria andBoxActivityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("box_activity_id not between", value1, value2, "boxActivityId");
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

        public Criteria andPersonStringIsNull() {
            addCriterion("person_string is null");
            return (Criteria) this;
        }

        public Criteria andPersonStringIsNotNull() {
            addCriterion("person_string is not null");
            return (Criteria) this;
        }

        public Criteria andPersonStringEqualTo(String value) {
            addCriterion("person_string =", value, "personString");
            return (Criteria) this;
        }

        public Criteria andPersonStringNotEqualTo(String value) {
            addCriterion("person_string <>", value, "personString");
            return (Criteria) this;
        }

        public Criteria andPersonStringGreaterThan(String value) {
            addCriterion("person_string >", value, "personString");
            return (Criteria) this;
        }

        public Criteria andPersonStringGreaterThanOrEqualTo(String value) {
            addCriterion("person_string >=", value, "personString");
            return (Criteria) this;
        }

        public Criteria andPersonStringLessThan(String value) {
            addCriterion("person_string <", value, "personString");
            return (Criteria) this;
        }

        public Criteria andPersonStringLessThanOrEqualTo(String value) {
            addCriterion("person_string <=", value, "personString");
            return (Criteria) this;
        }

        public Criteria andPersonStringLike(String value) {
            addCriterion("person_string like", value, "personString");
            return (Criteria) this;
        }

        public Criteria andPersonStringNotLike(String value) {
            addCriterion("person_string not like", value, "personString");
            return (Criteria) this;
        }

        public Criteria andPersonStringIn(List<String> values) {
            addCriterion("person_string in", values, "personString");
            return (Criteria) this;
        }

        public Criteria andPersonStringNotIn(List<String> values) {
            addCriterion("person_string not in", values, "personString");
            return (Criteria) this;
        }

        public Criteria andPersonStringBetween(String value1, String value2) {
            addCriterion("person_string between", value1, value2, "personString");
            return (Criteria) this;
        }

        public Criteria andPersonStringNotBetween(String value1, String value2) {
            addCriterion("person_string not between", value1, value2, "personString");
            return (Criteria) this;
        }

        public Criteria andBisChksIsNull() {
            addCriterion("bis_chks is null");
            return (Criteria) this;
        }

        public Criteria andBisChksIsNotNull() {
            addCriterion("bis_chks is not null");
            return (Criteria) this;
        }

        public Criteria andBisChksEqualTo(Boolean value) {
            addCriterion("bis_chks =", value, "bisChks");
            return (Criteria) this;
        }

        public Criteria andBisChksNotEqualTo(Boolean value) {
            addCriterion("bis_chks <>", value, "bisChks");
            return (Criteria) this;
        }

        public Criteria andBisChksGreaterThan(Boolean value) {
            addCriterion("bis_chks >", value, "bisChks");
            return (Criteria) this;
        }

        public Criteria andBisChksGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_chks >=", value, "bisChks");
            return (Criteria) this;
        }

        public Criteria andBisChksLessThan(Boolean value) {
            addCriterion("bis_chks <", value, "bisChks");
            return (Criteria) this;
        }

        public Criteria andBisChksLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_chks <=", value, "bisChks");
            return (Criteria) this;
        }

        public Criteria andBisChksIn(List<Boolean> values) {
            addCriterion("bis_chks in", values, "bisChks");
            return (Criteria) this;
        }

        public Criteria andBisChksNotIn(List<Boolean> values) {
            addCriterion("bis_chks not in", values, "bisChks");
            return (Criteria) this;
        }

        public Criteria andBisChksBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_chks between", value1, value2, "bisChks");
            return (Criteria) this;
        }

        public Criteria andBisChksNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_chks not between", value1, value2, "bisChks");
            return (Criteria) this;
        }

        public Criteria andAppKeyIsNull() {
            addCriterion("app_key is null");
            return (Criteria) this;
        }

        public Criteria andAppKeyIsNotNull() {
            addCriterion("app_key is not null");
            return (Criteria) this;
        }

        public Criteria andAppKeyEqualTo(String value) {
            addCriterion("app_key =", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyNotEqualTo(String value) {
            addCriterion("app_key <>", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyGreaterThan(String value) {
            addCriterion("app_key >", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyGreaterThanOrEqualTo(String value) {
            addCriterion("app_key >=", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyLessThan(String value) {
            addCriterion("app_key <", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyLessThanOrEqualTo(String value) {
            addCriterion("app_key <=", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyLike(String value) {
            addCriterion("app_key like", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyNotLike(String value) {
            addCriterion("app_key not like", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyIn(List<String> values) {
            addCriterion("app_key in", values, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyNotIn(List<String> values) {
            addCriterion("app_key not in", values, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyBetween(String value1, String value2) {
            addCriterion("app_key between", value1, value2, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyNotBetween(String value1, String value2) {
            addCriterion("app_key not between", value1, value2, "appKey");
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