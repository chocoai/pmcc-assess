package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.List;

public class NetBeforehandAnnouncementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NetBeforehandAnnouncementExample() {
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

        public Criteria andMainIdIsNull() {
            addCriterion("main_id is null");
            return (Criteria) this;
        }

        public Criteria andMainIdIsNotNull() {
            addCriterion("main_id is not null");
            return (Criteria) this;
        }

        public Criteria andMainIdEqualTo(Integer value) {
            addCriterion("main_id =", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdNotEqualTo(Integer value) {
            addCriterion("main_id <>", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdGreaterThan(Integer value) {
            addCriterion("main_id >", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("main_id >=", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdLessThan(Integer value) {
            addCriterion("main_id <", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdLessThanOrEqualTo(Integer value) {
            addCriterion("main_id <=", value, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdIn(List<Integer> values) {
            addCriterion("main_id in", values, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdNotIn(List<Integer> values) {
            addCriterion("main_id not in", values, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdBetween(Integer value1, Integer value2) {
            addCriterion("main_id between", value1, value2, "mainId");
            return (Criteria) this;
        }

        public Criteria andMainIdNotBetween(Integer value1, Integer value2) {
            addCriterion("main_id not between", value1, value2, "mainId");
            return (Criteria) this;
        }

        public Criteria andDkwzIsNull() {
            addCriterion("dkwz is null");
            return (Criteria) this;
        }

        public Criteria andDkwzIsNotNull() {
            addCriterion("dkwz is not null");
            return (Criteria) this;
        }

        public Criteria andDkwzEqualTo(String value) {
            addCriterion("dkwz =", value, "dkwz");
            return (Criteria) this;
        }

        public Criteria andDkwzNotEqualTo(String value) {
            addCriterion("dkwz <>", value, "dkwz");
            return (Criteria) this;
        }

        public Criteria andDkwzGreaterThan(String value) {
            addCriterion("dkwz >", value, "dkwz");
            return (Criteria) this;
        }

        public Criteria andDkwzGreaterThanOrEqualTo(String value) {
            addCriterion("dkwz >=", value, "dkwz");
            return (Criteria) this;
        }

        public Criteria andDkwzLessThan(String value) {
            addCriterion("dkwz <", value, "dkwz");
            return (Criteria) this;
        }

        public Criteria andDkwzLessThanOrEqualTo(String value) {
            addCriterion("dkwz <=", value, "dkwz");
            return (Criteria) this;
        }

        public Criteria andDkwzLike(String value) {
            addCriterion("dkwz like", value, "dkwz");
            return (Criteria) this;
        }

        public Criteria andDkwzNotLike(String value) {
            addCriterion("dkwz not like", value, "dkwz");
            return (Criteria) this;
        }

        public Criteria andDkwzIn(List<String> values) {
            addCriterion("dkwz in", values, "dkwz");
            return (Criteria) this;
        }

        public Criteria andDkwzNotIn(List<String> values) {
            addCriterion("dkwz not in", values, "dkwz");
            return (Criteria) this;
        }

        public Criteria andDkwzBetween(String value1, String value2) {
            addCriterion("dkwz between", value1, value2, "dkwz");
            return (Criteria) this;
        }

        public Criteria andDkwzNotBetween(String value1, String value2) {
            addCriterion("dkwz not between", value1, value2, "dkwz");
            return (Criteria) this;
        }

        public Criteria andJydmjIsNull() {
            addCriterion("jydmj is null");
            return (Criteria) this;
        }

        public Criteria andJydmjIsNotNull() {
            addCriterion("jydmj is not null");
            return (Criteria) this;
        }

        public Criteria andJydmjEqualTo(String value) {
            addCriterion("jydmj =", value, "jydmj");
            return (Criteria) this;
        }

        public Criteria andJydmjNotEqualTo(String value) {
            addCriterion("jydmj <>", value, "jydmj");
            return (Criteria) this;
        }

        public Criteria andJydmjGreaterThan(String value) {
            addCriterion("jydmj >", value, "jydmj");
            return (Criteria) this;
        }

        public Criteria andJydmjGreaterThanOrEqualTo(String value) {
            addCriterion("jydmj >=", value, "jydmj");
            return (Criteria) this;
        }

        public Criteria andJydmjLessThan(String value) {
            addCriterion("jydmj <", value, "jydmj");
            return (Criteria) this;
        }

        public Criteria andJydmjLessThanOrEqualTo(String value) {
            addCriterion("jydmj <=", value, "jydmj");
            return (Criteria) this;
        }

        public Criteria andJydmjLike(String value) {
            addCriterion("jydmj like", value, "jydmj");
            return (Criteria) this;
        }

        public Criteria andJydmjNotLike(String value) {
            addCriterion("jydmj not like", value, "jydmj");
            return (Criteria) this;
        }

        public Criteria andJydmjIn(List<String> values) {
            addCriterion("jydmj in", values, "jydmj");
            return (Criteria) this;
        }

        public Criteria andJydmjNotIn(List<String> values) {
            addCriterion("jydmj not in", values, "jydmj");
            return (Criteria) this;
        }

        public Criteria andJydmjBetween(String value1, String value2) {
            addCriterion("jydmj between", value1, value2, "jydmj");
            return (Criteria) this;
        }

        public Criteria andJydmjNotBetween(String value1, String value2) {
            addCriterion("jydmj not between", value1, value2, "jydmj");
            return (Criteria) this;
        }

        public Criteria andRjlIsNull() {
            addCriterion("rjl is null");
            return (Criteria) this;
        }

        public Criteria andRjlIsNotNull() {
            addCriterion("rjl is not null");
            return (Criteria) this;
        }

        public Criteria andRjlEqualTo(String value) {
            addCriterion("rjl =", value, "rjl");
            return (Criteria) this;
        }

        public Criteria andRjlNotEqualTo(String value) {
            addCriterion("rjl <>", value, "rjl");
            return (Criteria) this;
        }

        public Criteria andRjlGreaterThan(String value) {
            addCriterion("rjl >", value, "rjl");
            return (Criteria) this;
        }

        public Criteria andRjlGreaterThanOrEqualTo(String value) {
            addCriterion("rjl >=", value, "rjl");
            return (Criteria) this;
        }

        public Criteria andRjlLessThan(String value) {
            addCriterion("rjl <", value, "rjl");
            return (Criteria) this;
        }

        public Criteria andRjlLessThanOrEqualTo(String value) {
            addCriterion("rjl <=", value, "rjl");
            return (Criteria) this;
        }

        public Criteria andRjlLike(String value) {
            addCriterion("rjl like", value, "rjl");
            return (Criteria) this;
        }

        public Criteria andRjlNotLike(String value) {
            addCriterion("rjl not like", value, "rjl");
            return (Criteria) this;
        }

        public Criteria andRjlIn(List<String> values) {
            addCriterion("rjl in", values, "rjl");
            return (Criteria) this;
        }

        public Criteria andRjlNotIn(List<String> values) {
            addCriterion("rjl not in", values, "rjl");
            return (Criteria) this;
        }

        public Criteria andRjlBetween(String value1, String value2) {
            addCriterion("rjl between", value1, value2, "rjl");
            return (Criteria) this;
        }

        public Criteria andRjlNotBetween(String value1, String value2) {
            addCriterion("rjl not between", value1, value2, "rjl");
            return (Criteria) this;
        }

        public Criteria andJzmdIsNull() {
            addCriterion("jzmd is null");
            return (Criteria) this;
        }

        public Criteria andJzmdIsNotNull() {
            addCriterion("jzmd is not null");
            return (Criteria) this;
        }

        public Criteria andJzmdEqualTo(String value) {
            addCriterion("jzmd =", value, "jzmd");
            return (Criteria) this;
        }

        public Criteria andJzmdNotEqualTo(String value) {
            addCriterion("jzmd <>", value, "jzmd");
            return (Criteria) this;
        }

        public Criteria andJzmdGreaterThan(String value) {
            addCriterion("jzmd >", value, "jzmd");
            return (Criteria) this;
        }

        public Criteria andJzmdGreaterThanOrEqualTo(String value) {
            addCriterion("jzmd >=", value, "jzmd");
            return (Criteria) this;
        }

        public Criteria andJzmdLessThan(String value) {
            addCriterion("jzmd <", value, "jzmd");
            return (Criteria) this;
        }

        public Criteria andJzmdLessThanOrEqualTo(String value) {
            addCriterion("jzmd <=", value, "jzmd");
            return (Criteria) this;
        }

        public Criteria andJzmdLike(String value) {
            addCriterion("jzmd like", value, "jzmd");
            return (Criteria) this;
        }

        public Criteria andJzmdNotLike(String value) {
            addCriterion("jzmd not like", value, "jzmd");
            return (Criteria) this;
        }

        public Criteria andJzmdIn(List<String> values) {
            addCriterion("jzmd in", values, "jzmd");
            return (Criteria) this;
        }

        public Criteria andJzmdNotIn(List<String> values) {
            addCriterion("jzmd not in", values, "jzmd");
            return (Criteria) this;
        }

        public Criteria andJzmdBetween(String value1, String value2) {
            addCriterion("jzmd between", value1, value2, "jzmd");
            return (Criteria) this;
        }

        public Criteria andJzmdNotBetween(String value1, String value2) {
            addCriterion("jzmd not between", value1, value2, "jzmd");
            return (Criteria) this;
        }

        public Criteria andJzgdIsNull() {
            addCriterion("jzgd is null");
            return (Criteria) this;
        }

        public Criteria andJzgdIsNotNull() {
            addCriterion("jzgd is not null");
            return (Criteria) this;
        }

        public Criteria andJzgdEqualTo(String value) {
            addCriterion("jzgd =", value, "jzgd");
            return (Criteria) this;
        }

        public Criteria andJzgdNotEqualTo(String value) {
            addCriterion("jzgd <>", value, "jzgd");
            return (Criteria) this;
        }

        public Criteria andJzgdGreaterThan(String value) {
            addCriterion("jzgd >", value, "jzgd");
            return (Criteria) this;
        }

        public Criteria andJzgdGreaterThanOrEqualTo(String value) {
            addCriterion("jzgd >=", value, "jzgd");
            return (Criteria) this;
        }

        public Criteria andJzgdLessThan(String value) {
            addCriterion("jzgd <", value, "jzgd");
            return (Criteria) this;
        }

        public Criteria andJzgdLessThanOrEqualTo(String value) {
            addCriterion("jzgd <=", value, "jzgd");
            return (Criteria) this;
        }

        public Criteria andJzgdLike(String value) {
            addCriterion("jzgd like", value, "jzgd");
            return (Criteria) this;
        }

        public Criteria andJzgdNotLike(String value) {
            addCriterion("jzgd not like", value, "jzgd");
            return (Criteria) this;
        }

        public Criteria andJzgdIn(List<String> values) {
            addCriterion("jzgd in", values, "jzgd");
            return (Criteria) this;
        }

        public Criteria andJzgdNotIn(List<String> values) {
            addCriterion("jzgd not in", values, "jzgd");
            return (Criteria) this;
        }

        public Criteria andJzgdBetween(String value1, String value2) {
            addCriterion("jzgd between", value1, value2, "jzgd");
            return (Criteria) this;
        }

        public Criteria andJzgdNotBetween(String value1, String value2) {
            addCriterion("jzgd not between", value1, value2, "jzgd");
            return (Criteria) this;
        }

        public Criteria andLdlIsNull() {
            addCriterion("ldl is null");
            return (Criteria) this;
        }

        public Criteria andLdlIsNotNull() {
            addCriterion("ldl is not null");
            return (Criteria) this;
        }

        public Criteria andLdlEqualTo(String value) {
            addCriterion("ldl =", value, "ldl");
            return (Criteria) this;
        }

        public Criteria andLdlNotEqualTo(String value) {
            addCriterion("ldl <>", value, "ldl");
            return (Criteria) this;
        }

        public Criteria andLdlGreaterThan(String value) {
            addCriterion("ldl >", value, "ldl");
            return (Criteria) this;
        }

        public Criteria andLdlGreaterThanOrEqualTo(String value) {
            addCriterion("ldl >=", value, "ldl");
            return (Criteria) this;
        }

        public Criteria andLdlLessThan(String value) {
            addCriterion("ldl <", value, "ldl");
            return (Criteria) this;
        }

        public Criteria andLdlLessThanOrEqualTo(String value) {
            addCriterion("ldl <=", value, "ldl");
            return (Criteria) this;
        }

        public Criteria andLdlLike(String value) {
            addCriterion("ldl like", value, "ldl");
            return (Criteria) this;
        }

        public Criteria andLdlNotLike(String value) {
            addCriterion("ldl not like", value, "ldl");
            return (Criteria) this;
        }

        public Criteria andLdlIn(List<String> values) {
            addCriterion("ldl in", values, "ldl");
            return (Criteria) this;
        }

        public Criteria andLdlNotIn(List<String> values) {
            addCriterion("ldl not in", values, "ldl");
            return (Criteria) this;
        }

        public Criteria andLdlBetween(String value1, String value2) {
            addCriterion("ldl between", value1, value2, "ldl");
            return (Criteria) this;
        }

        public Criteria andLdlNotBetween(String value1, String value2) {
            addCriterion("ldl not between", value1, value2, "ldl");
            return (Criteria) this;
        }

        public Criteria andYdxzIsNull() {
            addCriterion("ydxz is null");
            return (Criteria) this;
        }

        public Criteria andYdxzIsNotNull() {
            addCriterion("ydxz is not null");
            return (Criteria) this;
        }

        public Criteria andYdxzEqualTo(String value) {
            addCriterion("ydxz =", value, "ydxz");
            return (Criteria) this;
        }

        public Criteria andYdxzNotEqualTo(String value) {
            addCriterion("ydxz <>", value, "ydxz");
            return (Criteria) this;
        }

        public Criteria andYdxzGreaterThan(String value) {
            addCriterion("ydxz >", value, "ydxz");
            return (Criteria) this;
        }

        public Criteria andYdxzGreaterThanOrEqualTo(String value) {
            addCriterion("ydxz >=", value, "ydxz");
            return (Criteria) this;
        }

        public Criteria andYdxzLessThan(String value) {
            addCriterion("ydxz <", value, "ydxz");
            return (Criteria) this;
        }

        public Criteria andYdxzLessThanOrEqualTo(String value) {
            addCriterion("ydxz <=", value, "ydxz");
            return (Criteria) this;
        }

        public Criteria andYdxzLike(String value) {
            addCriterion("ydxz like", value, "ydxz");
            return (Criteria) this;
        }

        public Criteria andYdxzNotLike(String value) {
            addCriterion("ydxz not like", value, "ydxz");
            return (Criteria) this;
        }

        public Criteria andYdxzIn(List<String> values) {
            addCriterion("ydxz in", values, "ydxz");
            return (Criteria) this;
        }

        public Criteria andYdxzNotIn(List<String> values) {
            addCriterion("ydxz not in", values, "ydxz");
            return (Criteria) this;
        }

        public Criteria andYdxzBetween(String value1, String value2) {
            addCriterion("ydxz between", value1, value2, "ydxz");
            return (Criteria) this;
        }

        public Criteria andYdxzNotBetween(String value1, String value2) {
            addCriterion("ydxz not between", value1, value2, "ydxz");
            return (Criteria) this;
        }

        public Criteria andYjsssjIsNull() {
            addCriterion("yjsssj is null");
            return (Criteria) this;
        }

        public Criteria andYjsssjIsNotNull() {
            addCriterion("yjsssj is not null");
            return (Criteria) this;
        }

        public Criteria andYjsssjEqualTo(String value) {
            addCriterion("yjsssj =", value, "yjsssj");
            return (Criteria) this;
        }

        public Criteria andYjsssjNotEqualTo(String value) {
            addCriterion("yjsssj <>", value, "yjsssj");
            return (Criteria) this;
        }

        public Criteria andYjsssjGreaterThan(String value) {
            addCriterion("yjsssj >", value, "yjsssj");
            return (Criteria) this;
        }

        public Criteria andYjsssjGreaterThanOrEqualTo(String value) {
            addCriterion("yjsssj >=", value, "yjsssj");
            return (Criteria) this;
        }

        public Criteria andYjsssjLessThan(String value) {
            addCriterion("yjsssj <", value, "yjsssj");
            return (Criteria) this;
        }

        public Criteria andYjsssjLessThanOrEqualTo(String value) {
            addCriterion("yjsssj <=", value, "yjsssj");
            return (Criteria) this;
        }

        public Criteria andYjsssjLike(String value) {
            addCriterion("yjsssj like", value, "yjsssj");
            return (Criteria) this;
        }

        public Criteria andYjsssjNotLike(String value) {
            addCriterion("yjsssj not like", value, "yjsssj");
            return (Criteria) this;
        }

        public Criteria andYjsssjIn(List<String> values) {
            addCriterion("yjsssj in", values, "yjsssj");
            return (Criteria) this;
        }

        public Criteria andYjsssjNotIn(List<String> values) {
            addCriterion("yjsssj not in", values, "yjsssj");
            return (Criteria) this;
        }

        public Criteria andYjsssjBetween(String value1, String value2) {
            addCriterion("yjsssj between", value1, value2, "yjsssj");
            return (Criteria) this;
        }

        public Criteria andYjsssjNotBetween(String value1, String value2) {
            addCriterion("yjsssj not between", value1, value2, "yjsssj");
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