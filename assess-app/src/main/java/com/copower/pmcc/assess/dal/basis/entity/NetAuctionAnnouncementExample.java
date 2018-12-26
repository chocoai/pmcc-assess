package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.List;

public class NetAuctionAnnouncementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NetAuctionAnnouncementExample() {
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

        public Criteria andTdytjsyqxIsNull() {
            addCriterion("tdytjsyqx is null");
            return (Criteria) this;
        }

        public Criteria andTdytjsyqxIsNotNull() {
            addCriterion("tdytjsyqx is not null");
            return (Criteria) this;
        }

        public Criteria andTdytjsyqxEqualTo(String value) {
            addCriterion("tdytjsyqx =", value, "tdytjsyqx");
            return (Criteria) this;
        }

        public Criteria andTdytjsyqxNotEqualTo(String value) {
            addCriterion("tdytjsyqx <>", value, "tdytjsyqx");
            return (Criteria) this;
        }

        public Criteria andTdytjsyqxGreaterThan(String value) {
            addCriterion("tdytjsyqx >", value, "tdytjsyqx");
            return (Criteria) this;
        }

        public Criteria andTdytjsyqxGreaterThanOrEqualTo(String value) {
            addCriterion("tdytjsyqx >=", value, "tdytjsyqx");
            return (Criteria) this;
        }

        public Criteria andTdytjsyqxLessThan(String value) {
            addCriterion("tdytjsyqx <", value, "tdytjsyqx");
            return (Criteria) this;
        }

        public Criteria andTdytjsyqxLessThanOrEqualTo(String value) {
            addCriterion("tdytjsyqx <=", value, "tdytjsyqx");
            return (Criteria) this;
        }

        public Criteria andTdytjsyqxLike(String value) {
            addCriterion("tdytjsyqx like", value, "tdytjsyqx");
            return (Criteria) this;
        }

        public Criteria andTdytjsyqxNotLike(String value) {
            addCriterion("tdytjsyqx not like", value, "tdytjsyqx");
            return (Criteria) this;
        }

        public Criteria andTdytjsyqxIn(List<String> values) {
            addCriterion("tdytjsyqx in", values, "tdytjsyqx");
            return (Criteria) this;
        }

        public Criteria andTdytjsyqxNotIn(List<String> values) {
            addCriterion("tdytjsyqx not in", values, "tdytjsyqx");
            return (Criteria) this;
        }

        public Criteria andTdytjsyqxBetween(String value1, String value2) {
            addCriterion("tdytjsyqx between", value1, value2, "tdytjsyqx");
            return (Criteria) this;
        }

        public Criteria andTdytjsyqxNotBetween(String value1, String value2) {
            addCriterion("tdytjsyqx not between", value1, value2, "tdytjsyqx");
            return (Criteria) this;
        }

        public Criteria andQpjjIsNull() {
            addCriterion("qpjj is null");
            return (Criteria) this;
        }

        public Criteria andQpjjIsNotNull() {
            addCriterion("qpjj is not null");
            return (Criteria) this;
        }

        public Criteria andQpjjEqualTo(String value) {
            addCriterion("qpjj =", value, "qpjj");
            return (Criteria) this;
        }

        public Criteria andQpjjNotEqualTo(String value) {
            addCriterion("qpjj <>", value, "qpjj");
            return (Criteria) this;
        }

        public Criteria andQpjjGreaterThan(String value) {
            addCriterion("qpjj >", value, "qpjj");
            return (Criteria) this;
        }

        public Criteria andQpjjGreaterThanOrEqualTo(String value) {
            addCriterion("qpjj >=", value, "qpjj");
            return (Criteria) this;
        }

        public Criteria andQpjjLessThan(String value) {
            addCriterion("qpjj <", value, "qpjj");
            return (Criteria) this;
        }

        public Criteria andQpjjLessThanOrEqualTo(String value) {
            addCriterion("qpjj <=", value, "qpjj");
            return (Criteria) this;
        }

        public Criteria andQpjjLike(String value) {
            addCriterion("qpjj like", value, "qpjj");
            return (Criteria) this;
        }

        public Criteria andQpjjNotLike(String value) {
            addCriterion("qpjj not like", value, "qpjj");
            return (Criteria) this;
        }

        public Criteria andQpjjIn(List<String> values) {
            addCriterion("qpjj in", values, "qpjj");
            return (Criteria) this;
        }

        public Criteria andQpjjNotIn(List<String> values) {
            addCriterion("qpjj not in", values, "qpjj");
            return (Criteria) this;
        }

        public Criteria andQpjjBetween(String value1, String value2) {
            addCriterion("qpjj between", value1, value2, "qpjj");
            return (Criteria) this;
        }

        public Criteria andQpjjNotBetween(String value1, String value2) {
            addCriterion("qpjj not between", value1, value2, "qpjj");
            return (Criteria) this;
        }

        public Criteria andJmbxjIsNull() {
            addCriterion("jmbxj is null");
            return (Criteria) this;
        }

        public Criteria andJmbxjIsNotNull() {
            addCriterion("jmbxj is not null");
            return (Criteria) this;
        }

        public Criteria andJmbxjEqualTo(String value) {
            addCriterion("jmbxj =", value, "jmbxj");
            return (Criteria) this;
        }

        public Criteria andJmbxjNotEqualTo(String value) {
            addCriterion("jmbxj <>", value, "jmbxj");
            return (Criteria) this;
        }

        public Criteria andJmbxjGreaterThan(String value) {
            addCriterion("jmbxj >", value, "jmbxj");
            return (Criteria) this;
        }

        public Criteria andJmbxjGreaterThanOrEqualTo(String value) {
            addCriterion("jmbxj >=", value, "jmbxj");
            return (Criteria) this;
        }

        public Criteria andJmbxjLessThan(String value) {
            addCriterion("jmbxj <", value, "jmbxj");
            return (Criteria) this;
        }

        public Criteria andJmbxjLessThanOrEqualTo(String value) {
            addCriterion("jmbxj <=", value, "jmbxj");
            return (Criteria) this;
        }

        public Criteria andJmbxjLike(String value) {
            addCriterion("jmbxj like", value, "jmbxj");
            return (Criteria) this;
        }

        public Criteria andJmbxjNotLike(String value) {
            addCriterion("jmbxj not like", value, "jmbxj");
            return (Criteria) this;
        }

        public Criteria andJmbxjIn(List<String> values) {
            addCriterion("jmbxj in", values, "jmbxj");
            return (Criteria) this;
        }

        public Criteria andJmbxjNotIn(List<String> values) {
            addCriterion("jmbxj not in", values, "jmbxj");
            return (Criteria) this;
        }

        public Criteria andJmbxjBetween(String value1, String value2) {
            addCriterion("jmbxj between", value1, value2, "jmbxj");
            return (Criteria) this;
        }

        public Criteria andJmbxjNotBetween(String value1, String value2) {
            addCriterion("jmbxj not between", value1, value2, "jmbxj");
            return (Criteria) this;
        }

        public Criteria andPmcrsjIsNull() {
            addCriterion("pmcrsj is null");
            return (Criteria) this;
        }

        public Criteria andPmcrsjIsNotNull() {
            addCriterion("pmcrsj is not null");
            return (Criteria) this;
        }

        public Criteria andPmcrsjEqualTo(String value) {
            addCriterion("pmcrsj =", value, "pmcrsj");
            return (Criteria) this;
        }

        public Criteria andPmcrsjNotEqualTo(String value) {
            addCriterion("pmcrsj <>", value, "pmcrsj");
            return (Criteria) this;
        }

        public Criteria andPmcrsjGreaterThan(String value) {
            addCriterion("pmcrsj >", value, "pmcrsj");
            return (Criteria) this;
        }

        public Criteria andPmcrsjGreaterThanOrEqualTo(String value) {
            addCriterion("pmcrsj >=", value, "pmcrsj");
            return (Criteria) this;
        }

        public Criteria andPmcrsjLessThan(String value) {
            addCriterion("pmcrsj <", value, "pmcrsj");
            return (Criteria) this;
        }

        public Criteria andPmcrsjLessThanOrEqualTo(String value) {
            addCriterion("pmcrsj <=", value, "pmcrsj");
            return (Criteria) this;
        }

        public Criteria andPmcrsjLike(String value) {
            addCriterion("pmcrsj like", value, "pmcrsj");
            return (Criteria) this;
        }

        public Criteria andPmcrsjNotLike(String value) {
            addCriterion("pmcrsj not like", value, "pmcrsj");
            return (Criteria) this;
        }

        public Criteria andPmcrsjIn(List<String> values) {
            addCriterion("pmcrsj in", values, "pmcrsj");
            return (Criteria) this;
        }

        public Criteria andPmcrsjNotIn(List<String> values) {
            addCriterion("pmcrsj not in", values, "pmcrsj");
            return (Criteria) this;
        }

        public Criteria andPmcrsjBetween(String value1, String value2) {
            addCriterion("pmcrsj between", value1, value2, "pmcrsj");
            return (Criteria) this;
        }

        public Criteria andPmcrsjNotBetween(String value1, String value2) {
            addCriterion("pmcrsj not between", value1, value2, "pmcrsj");
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

        public Criteria andGhydsyxzIsNull() {
            addCriterion("ghydsyxz is null");
            return (Criteria) this;
        }

        public Criteria andGhydsyxzIsNotNull() {
            addCriterion("ghydsyxz is not null");
            return (Criteria) this;
        }

        public Criteria andGhydsyxzEqualTo(String value) {
            addCriterion("ghydsyxz =", value, "ghydsyxz");
            return (Criteria) this;
        }

        public Criteria andGhydsyxzNotEqualTo(String value) {
            addCriterion("ghydsyxz <>", value, "ghydsyxz");
            return (Criteria) this;
        }

        public Criteria andGhydsyxzGreaterThan(String value) {
            addCriterion("ghydsyxz >", value, "ghydsyxz");
            return (Criteria) this;
        }

        public Criteria andGhydsyxzGreaterThanOrEqualTo(String value) {
            addCriterion("ghydsyxz >=", value, "ghydsyxz");
            return (Criteria) this;
        }

        public Criteria andGhydsyxzLessThan(String value) {
            addCriterion("ghydsyxz <", value, "ghydsyxz");
            return (Criteria) this;
        }

        public Criteria andGhydsyxzLessThanOrEqualTo(String value) {
            addCriterion("ghydsyxz <=", value, "ghydsyxz");
            return (Criteria) this;
        }

        public Criteria andGhydsyxzLike(String value) {
            addCriterion("ghydsyxz like", value, "ghydsyxz");
            return (Criteria) this;
        }

        public Criteria andGhydsyxzNotLike(String value) {
            addCriterion("ghydsyxz not like", value, "ghydsyxz");
            return (Criteria) this;
        }

        public Criteria andGhydsyxzIn(List<String> values) {
            addCriterion("ghydsyxz in", values, "ghydsyxz");
            return (Criteria) this;
        }

        public Criteria andGhydsyxzNotIn(List<String> values) {
            addCriterion("ghydsyxz not in", values, "ghydsyxz");
            return (Criteria) this;
        }

        public Criteria andGhydsyxzBetween(String value1, String value2) {
            addCriterion("ghydsyxz between", value1, value2, "ghydsyxz");
            return (Criteria) this;
        }

        public Criteria andGhydsyxzNotBetween(String value1, String value2) {
            addCriterion("ghydsyxz not between", value1, value2, "ghydsyxz");
            return (Criteria) this;
        }

        public Criteria andCzzymjjfsIsNull() {
            addCriterion("czzymjjfs is null");
            return (Criteria) this;
        }

        public Criteria andCzzymjjfsIsNotNull() {
            addCriterion("czzymjjfs is not null");
            return (Criteria) this;
        }

        public Criteria andCzzymjjfsEqualTo(String value) {
            addCriterion("czzymjjfs =", value, "czzymjjfs");
            return (Criteria) this;
        }

        public Criteria andCzzymjjfsNotEqualTo(String value) {
            addCriterion("czzymjjfs <>", value, "czzymjjfs");
            return (Criteria) this;
        }

        public Criteria andCzzymjjfsGreaterThan(String value) {
            addCriterion("czzymjjfs >", value, "czzymjjfs");
            return (Criteria) this;
        }

        public Criteria andCzzymjjfsGreaterThanOrEqualTo(String value) {
            addCriterion("czzymjjfs >=", value, "czzymjjfs");
            return (Criteria) this;
        }

        public Criteria andCzzymjjfsLessThan(String value) {
            addCriterion("czzymjjfs <", value, "czzymjjfs");
            return (Criteria) this;
        }

        public Criteria andCzzymjjfsLessThanOrEqualTo(String value) {
            addCriterion("czzymjjfs <=", value, "czzymjjfs");
            return (Criteria) this;
        }

        public Criteria andCzzymjjfsLike(String value) {
            addCriterion("czzymjjfs like", value, "czzymjjfs");
            return (Criteria) this;
        }

        public Criteria andCzzymjjfsNotLike(String value) {
            addCriterion("czzymjjfs not like", value, "czzymjjfs");
            return (Criteria) this;
        }

        public Criteria andCzzymjjfsIn(List<String> values) {
            addCriterion("czzymjjfs in", values, "czzymjjfs");
            return (Criteria) this;
        }

        public Criteria andCzzymjjfsNotIn(List<String> values) {
            addCriterion("czzymjjfs not in", values, "czzymjjfs");
            return (Criteria) this;
        }

        public Criteria andCzzymjjfsBetween(String value1, String value2) {
            addCriterion("czzymjjfs between", value1, value2, "czzymjjfs");
            return (Criteria) this;
        }

        public Criteria andCzzymjjfsNotBetween(String value1, String value2) {
            addCriterion("czzymjjfs not between", value1, value2, "czzymjjfs");
            return (Criteria) this;
        }

        public Criteria andCrrIsNull() {
            addCriterion("crr is null");
            return (Criteria) this;
        }

        public Criteria andCrrIsNotNull() {
            addCriterion("crr is not null");
            return (Criteria) this;
        }

        public Criteria andCrrEqualTo(String value) {
            addCriterion("crr =", value, "crr");
            return (Criteria) this;
        }

        public Criteria andCrrNotEqualTo(String value) {
            addCriterion("crr <>", value, "crr");
            return (Criteria) this;
        }

        public Criteria andCrrGreaterThan(String value) {
            addCriterion("crr >", value, "crr");
            return (Criteria) this;
        }

        public Criteria andCrrGreaterThanOrEqualTo(String value) {
            addCriterion("crr >=", value, "crr");
            return (Criteria) this;
        }

        public Criteria andCrrLessThan(String value) {
            addCriterion("crr <", value, "crr");
            return (Criteria) this;
        }

        public Criteria andCrrLessThanOrEqualTo(String value) {
            addCriterion("crr <=", value, "crr");
            return (Criteria) this;
        }

        public Criteria andCrrLike(String value) {
            addCriterion("crr like", value, "crr");
            return (Criteria) this;
        }

        public Criteria andCrrNotLike(String value) {
            addCriterion("crr not like", value, "crr");
            return (Criteria) this;
        }

        public Criteria andCrrIn(List<String> values) {
            addCriterion("crr in", values, "crr");
            return (Criteria) this;
        }

        public Criteria andCrrNotIn(List<String> values) {
            addCriterion("crr not in", values, "crr");
            return (Criteria) this;
        }

        public Criteria andCrrBetween(String value1, String value2) {
            addCriterion("crr between", value1, value2, "crr");
            return (Criteria) this;
        }

        public Criteria andCrrNotBetween(String value1, String value2) {
            addCriterion("crr not between", value1, value2, "crr");
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