package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.List;

public class NetResultAnnouncementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NetResultAnnouncementExample() {
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

        public Criteria andZdbhIsNull() {
            addCriterion("zdbh is null");
            return (Criteria) this;
        }

        public Criteria andZdbhIsNotNull() {
            addCriterion("zdbh is not null");
            return (Criteria) this;
        }

        public Criteria andZdbhEqualTo(String value) {
            addCriterion("zdbh =", value, "zdbh");
            return (Criteria) this;
        }

        public Criteria andZdbhNotEqualTo(String value) {
            addCriterion("zdbh <>", value, "zdbh");
            return (Criteria) this;
        }

        public Criteria andZdbhGreaterThan(String value) {
            addCriterion("zdbh >", value, "zdbh");
            return (Criteria) this;
        }

        public Criteria andZdbhGreaterThanOrEqualTo(String value) {
            addCriterion("zdbh >=", value, "zdbh");
            return (Criteria) this;
        }

        public Criteria andZdbhLessThan(String value) {
            addCriterion("zdbh <", value, "zdbh");
            return (Criteria) this;
        }

        public Criteria andZdbhLessThanOrEqualTo(String value) {
            addCriterion("zdbh <=", value, "zdbh");
            return (Criteria) this;
        }

        public Criteria andZdbhLike(String value) {
            addCriterion("zdbh like", value, "zdbh");
            return (Criteria) this;
        }

        public Criteria andZdbhNotLike(String value) {
            addCriterion("zdbh not like", value, "zdbh");
            return (Criteria) this;
        }

        public Criteria andZdbhIn(List<String> values) {
            addCriterion("zdbh in", values, "zdbh");
            return (Criteria) this;
        }

        public Criteria andZdbhNotIn(List<String> values) {
            addCriterion("zdbh not in", values, "zdbh");
            return (Criteria) this;
        }

        public Criteria andZdbhBetween(String value1, String value2) {
            addCriterion("zdbh between", value1, value2, "zdbh");
            return (Criteria) this;
        }

        public Criteria andZdbhNotBetween(String value1, String value2) {
            addCriterion("zdbh not between", value1, value2, "zdbh");
            return (Criteria) this;
        }

        public Criteria andZdwzIsNull() {
            addCriterion("zdwz is null");
            return (Criteria) this;
        }

        public Criteria andZdwzIsNotNull() {
            addCriterion("zdwz is not null");
            return (Criteria) this;
        }

        public Criteria andZdwzEqualTo(String value) {
            addCriterion("zdwz =", value, "zdwz");
            return (Criteria) this;
        }

        public Criteria andZdwzNotEqualTo(String value) {
            addCriterion("zdwz <>", value, "zdwz");
            return (Criteria) this;
        }

        public Criteria andZdwzGreaterThan(String value) {
            addCriterion("zdwz >", value, "zdwz");
            return (Criteria) this;
        }

        public Criteria andZdwzGreaterThanOrEqualTo(String value) {
            addCriterion("zdwz >=", value, "zdwz");
            return (Criteria) this;
        }

        public Criteria andZdwzLessThan(String value) {
            addCriterion("zdwz <", value, "zdwz");
            return (Criteria) this;
        }

        public Criteria andZdwzLessThanOrEqualTo(String value) {
            addCriterion("zdwz <=", value, "zdwz");
            return (Criteria) this;
        }

        public Criteria andZdwzLike(String value) {
            addCriterion("zdwz like", value, "zdwz");
            return (Criteria) this;
        }

        public Criteria andZdwzNotLike(String value) {
            addCriterion("zdwz not like", value, "zdwz");
            return (Criteria) this;
        }

        public Criteria andZdwzIn(List<String> values) {
            addCriterion("zdwz in", values, "zdwz");
            return (Criteria) this;
        }

        public Criteria andZdwzNotIn(List<String> values) {
            addCriterion("zdwz not in", values, "zdwz");
            return (Criteria) this;
        }

        public Criteria andZdwzBetween(String value1, String value2) {
            addCriterion("zdwz between", value1, value2, "zdwz");
            return (Criteria) this;
        }

        public Criteria andZdwzNotBetween(String value1, String value2) {
            addCriterion("zdwz not between", value1, value2, "zdwz");
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

        public Criteria andTdytIsNull() {
            addCriterion("tdyt is null");
            return (Criteria) this;
        }

        public Criteria andTdytIsNotNull() {
            addCriterion("tdyt is not null");
            return (Criteria) this;
        }

        public Criteria andTdytEqualTo(String value) {
            addCriterion("tdyt =", value, "tdyt");
            return (Criteria) this;
        }

        public Criteria andTdytNotEqualTo(String value) {
            addCriterion("tdyt <>", value, "tdyt");
            return (Criteria) this;
        }

        public Criteria andTdytGreaterThan(String value) {
            addCriterion("tdyt >", value, "tdyt");
            return (Criteria) this;
        }

        public Criteria andTdytGreaterThanOrEqualTo(String value) {
            addCriterion("tdyt >=", value, "tdyt");
            return (Criteria) this;
        }

        public Criteria andTdytLessThan(String value) {
            addCriterion("tdyt <", value, "tdyt");
            return (Criteria) this;
        }

        public Criteria andTdytLessThanOrEqualTo(String value) {
            addCriterion("tdyt <=", value, "tdyt");
            return (Criteria) this;
        }

        public Criteria andTdytLike(String value) {
            addCriterion("tdyt like", value, "tdyt");
            return (Criteria) this;
        }

        public Criteria andTdytNotLike(String value) {
            addCriterion("tdyt not like", value, "tdyt");
            return (Criteria) this;
        }

        public Criteria andTdytIn(List<String> values) {
            addCriterion("tdyt in", values, "tdyt");
            return (Criteria) this;
        }

        public Criteria andTdytNotIn(List<String> values) {
            addCriterion("tdyt not in", values, "tdyt");
            return (Criteria) this;
        }

        public Criteria andTdytBetween(String value1, String value2) {
            addCriterion("tdyt between", value1, value2, "tdyt");
            return (Criteria) this;
        }

        public Criteria andTdytNotBetween(String value1, String value2) {
            addCriterion("tdyt not between", value1, value2, "tdyt");
            return (Criteria) this;
        }

        public Criteria andQsjIsNull() {
            addCriterion("qsj is null");
            return (Criteria) this;
        }

        public Criteria andQsjIsNotNull() {
            addCriterion("qsj is not null");
            return (Criteria) this;
        }

        public Criteria andQsjEqualTo(String value) {
            addCriterion("qsj =", value, "qsj");
            return (Criteria) this;
        }

        public Criteria andQsjNotEqualTo(String value) {
            addCriterion("qsj <>", value, "qsj");
            return (Criteria) this;
        }

        public Criteria andQsjGreaterThan(String value) {
            addCriterion("qsj >", value, "qsj");
            return (Criteria) this;
        }

        public Criteria andQsjGreaterThanOrEqualTo(String value) {
            addCriterion("qsj >=", value, "qsj");
            return (Criteria) this;
        }

        public Criteria andQsjLessThan(String value) {
            addCriterion("qsj <", value, "qsj");
            return (Criteria) this;
        }

        public Criteria andQsjLessThanOrEqualTo(String value) {
            addCriterion("qsj <=", value, "qsj");
            return (Criteria) this;
        }

        public Criteria andQsjLike(String value) {
            addCriterion("qsj like", value, "qsj");
            return (Criteria) this;
        }

        public Criteria andQsjNotLike(String value) {
            addCriterion("qsj not like", value, "qsj");
            return (Criteria) this;
        }

        public Criteria andQsjIn(List<String> values) {
            addCriterion("qsj in", values, "qsj");
            return (Criteria) this;
        }

        public Criteria andQsjNotIn(List<String> values) {
            addCriterion("qsj not in", values, "qsj");
            return (Criteria) this;
        }

        public Criteria andQsjBetween(String value1, String value2) {
            addCriterion("qsj between", value1, value2, "qsj");
            return (Criteria) this;
        }

        public Criteria andQsjNotBetween(String value1, String value2) {
            addCriterion("qsj not between", value1, value2, "qsj");
            return (Criteria) this;
        }

        public Criteria andCjsjIsNull() {
            addCriterion("cjsj is null");
            return (Criteria) this;
        }

        public Criteria andCjsjIsNotNull() {
            addCriterion("cjsj is not null");
            return (Criteria) this;
        }

        public Criteria andCjsjEqualTo(String value) {
            addCriterion("cjsj =", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjNotEqualTo(String value) {
            addCriterion("cjsj <>", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjGreaterThan(String value) {
            addCriterion("cjsj >", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjGreaterThanOrEqualTo(String value) {
            addCriterion("cjsj >=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThan(String value) {
            addCriterion("cjsj <", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThanOrEqualTo(String value) {
            addCriterion("cjsj <=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLike(String value) {
            addCriterion("cjsj like", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjNotLike(String value) {
            addCriterion("cjsj not like", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjIn(List<String> values) {
            addCriterion("cjsj in", values, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjNotIn(List<String> values) {
            addCriterion("cjsj not in", values, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjBetween(String value1, String value2) {
            addCriterion("cjsj between", value1, value2, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjNotBetween(String value1, String value2) {
            addCriterion("cjsj not between", value1, value2, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCcjIsNull() {
            addCriterion("ccj is null");
            return (Criteria) this;
        }

        public Criteria andCcjIsNotNull() {
            addCriterion("ccj is not null");
            return (Criteria) this;
        }

        public Criteria andCcjEqualTo(String value) {
            addCriterion("ccj =", value, "ccj");
            return (Criteria) this;
        }

        public Criteria andCcjNotEqualTo(String value) {
            addCriterion("ccj <>", value, "ccj");
            return (Criteria) this;
        }

        public Criteria andCcjGreaterThan(String value) {
            addCriterion("ccj >", value, "ccj");
            return (Criteria) this;
        }

        public Criteria andCcjGreaterThanOrEqualTo(String value) {
            addCriterion("ccj >=", value, "ccj");
            return (Criteria) this;
        }

        public Criteria andCcjLessThan(String value) {
            addCriterion("ccj <", value, "ccj");
            return (Criteria) this;
        }

        public Criteria andCcjLessThanOrEqualTo(String value) {
            addCriterion("ccj <=", value, "ccj");
            return (Criteria) this;
        }

        public Criteria andCcjLike(String value) {
            addCriterion("ccj like", value, "ccj");
            return (Criteria) this;
        }

        public Criteria andCcjNotLike(String value) {
            addCriterion("ccj not like", value, "ccj");
            return (Criteria) this;
        }

        public Criteria andCcjIn(List<String> values) {
            addCriterion("ccj in", values, "ccj");
            return (Criteria) this;
        }

        public Criteria andCcjNotIn(List<String> values) {
            addCriterion("ccj not in", values, "ccj");
            return (Criteria) this;
        }

        public Criteria andCcjBetween(String value1, String value2) {
            addCriterion("ccj between", value1, value2, "ccj");
            return (Criteria) this;
        }

        public Criteria andCcjNotBetween(String value1, String value2) {
            addCriterion("ccj not between", value1, value2, "ccj");
            return (Criteria) this;
        }

        public Criteria andJdrIsNull() {
            addCriterion("jdr is null");
            return (Criteria) this;
        }

        public Criteria andJdrIsNotNull() {
            addCriterion("jdr is not null");
            return (Criteria) this;
        }

        public Criteria andJdrEqualTo(String value) {
            addCriterion("jdr =", value, "jdr");
            return (Criteria) this;
        }

        public Criteria andJdrNotEqualTo(String value) {
            addCriterion("jdr <>", value, "jdr");
            return (Criteria) this;
        }

        public Criteria andJdrGreaterThan(String value) {
            addCriterion("jdr >", value, "jdr");
            return (Criteria) this;
        }

        public Criteria andJdrGreaterThanOrEqualTo(String value) {
            addCriterion("jdr >=", value, "jdr");
            return (Criteria) this;
        }

        public Criteria andJdrLessThan(String value) {
            addCriterion("jdr <", value, "jdr");
            return (Criteria) this;
        }

        public Criteria andJdrLessThanOrEqualTo(String value) {
            addCriterion("jdr <=", value, "jdr");
            return (Criteria) this;
        }

        public Criteria andJdrLike(String value) {
            addCriterion("jdr like", value, "jdr");
            return (Criteria) this;
        }

        public Criteria andJdrNotLike(String value) {
            addCriterion("jdr not like", value, "jdr");
            return (Criteria) this;
        }

        public Criteria andJdrIn(List<String> values) {
            addCriterion("jdr in", values, "jdr");
            return (Criteria) this;
        }

        public Criteria andJdrNotIn(List<String> values) {
            addCriterion("jdr not in", values, "jdr");
            return (Criteria) this;
        }

        public Criteria andJdrBetween(String value1, String value2) {
            addCriterion("jdr between", value1, value2, "jdr");
            return (Criteria) this;
        }

        public Criteria andJdrNotBetween(String value1, String value2) {
            addCriterion("jdr not between", value1, value2, "jdr");
            return (Criteria) this;
        }

        public Criteria andBdmcIsNull() {
            addCriterion("bdmc is null");
            return (Criteria) this;
        }

        public Criteria andBdmcIsNotNull() {
            addCriterion("bdmc is not null");
            return (Criteria) this;
        }

        public Criteria andBdmcEqualTo(String value) {
            addCriterion("bdmc =", value, "bdmc");
            return (Criteria) this;
        }

        public Criteria andBdmcNotEqualTo(String value) {
            addCriterion("bdmc <>", value, "bdmc");
            return (Criteria) this;
        }

        public Criteria andBdmcGreaterThan(String value) {
            addCriterion("bdmc >", value, "bdmc");
            return (Criteria) this;
        }

        public Criteria andBdmcGreaterThanOrEqualTo(String value) {
            addCriterion("bdmc >=", value, "bdmc");
            return (Criteria) this;
        }

        public Criteria andBdmcLessThan(String value) {
            addCriterion("bdmc <", value, "bdmc");
            return (Criteria) this;
        }

        public Criteria andBdmcLessThanOrEqualTo(String value) {
            addCriterion("bdmc <=", value, "bdmc");
            return (Criteria) this;
        }

        public Criteria andBdmcLike(String value) {
            addCriterion("bdmc like", value, "bdmc");
            return (Criteria) this;
        }

        public Criteria andBdmcNotLike(String value) {
            addCriterion("bdmc not like", value, "bdmc");
            return (Criteria) this;
        }

        public Criteria andBdmcIn(List<String> values) {
            addCriterion("bdmc in", values, "bdmc");
            return (Criteria) this;
        }

        public Criteria andBdmcNotIn(List<String> values) {
            addCriterion("bdmc not in", values, "bdmc");
            return (Criteria) this;
        }

        public Criteria andBdmcBetween(String value1, String value2) {
            addCriterion("bdmc between", value1, value2, "bdmc");
            return (Criteria) this;
        }

        public Criteria andBdmcNotBetween(String value1, String value2) {
            addCriterion("bdmc not between", value1, value2, "bdmc");
            return (Criteria) this;
        }

        public Criteria andCrfsIsNull() {
            addCriterion("crfs is null");
            return (Criteria) this;
        }

        public Criteria andCrfsIsNotNull() {
            addCriterion("crfs is not null");
            return (Criteria) this;
        }

        public Criteria andCrfsEqualTo(String value) {
            addCriterion("crfs =", value, "crfs");
            return (Criteria) this;
        }

        public Criteria andCrfsNotEqualTo(String value) {
            addCriterion("crfs <>", value, "crfs");
            return (Criteria) this;
        }

        public Criteria andCrfsGreaterThan(String value) {
            addCriterion("crfs >", value, "crfs");
            return (Criteria) this;
        }

        public Criteria andCrfsGreaterThanOrEqualTo(String value) {
            addCriterion("crfs >=", value, "crfs");
            return (Criteria) this;
        }

        public Criteria andCrfsLessThan(String value) {
            addCriterion("crfs <", value, "crfs");
            return (Criteria) this;
        }

        public Criteria andCrfsLessThanOrEqualTo(String value) {
            addCriterion("crfs <=", value, "crfs");
            return (Criteria) this;
        }

        public Criteria andCrfsLike(String value) {
            addCriterion("crfs like", value, "crfs");
            return (Criteria) this;
        }

        public Criteria andCrfsNotLike(String value) {
            addCriterion("crfs not like", value, "crfs");
            return (Criteria) this;
        }

        public Criteria andCrfsIn(List<String> values) {
            addCriterion("crfs in", values, "crfs");
            return (Criteria) this;
        }

        public Criteria andCrfsNotIn(List<String> values) {
            addCriterion("crfs not in", values, "crfs");
            return (Criteria) this;
        }

        public Criteria andCrfsBetween(String value1, String value2) {
            addCriterion("crfs between", value1, value2, "crfs");
            return (Criteria) this;
        }

        public Criteria andCrfsNotBetween(String value1, String value2) {
            addCriterion("crfs not between", value1, value2, "crfs");
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