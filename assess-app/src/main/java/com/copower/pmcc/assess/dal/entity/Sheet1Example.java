package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.List;

public class Sheet1Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Sheet1Example() {
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

        public Criteria andF1IsNull() {
            addCriterion("F1 is null");
            return (Criteria) this;
        }

        public Criteria andF1IsNotNull() {
            addCriterion("F1 is not null");
            return (Criteria) this;
        }

        public Criteria andF1EqualTo(String value) {
            addCriterion("F1 =", value, "f1");
            return (Criteria) this;
        }

        public Criteria andF1NotEqualTo(String value) {
            addCriterion("F1 <>", value, "f1");
            return (Criteria) this;
        }

        public Criteria andF1GreaterThan(String value) {
            addCriterion("F1 >", value, "f1");
            return (Criteria) this;
        }

        public Criteria andF1GreaterThanOrEqualTo(String value) {
            addCriterion("F1 >=", value, "f1");
            return (Criteria) this;
        }

        public Criteria andF1LessThan(String value) {
            addCriterion("F1 <", value, "f1");
            return (Criteria) this;
        }

        public Criteria andF1LessThanOrEqualTo(String value) {
            addCriterion("F1 <=", value, "f1");
            return (Criteria) this;
        }

        public Criteria andF1Like(String value) {
            addCriterion("F1 like", value, "f1");
            return (Criteria) this;
        }

        public Criteria andF1NotLike(String value) {
            addCriterion("F1 not like", value, "f1");
            return (Criteria) this;
        }

        public Criteria andF1In(List<String> values) {
            addCriterion("F1 in", values, "f1");
            return (Criteria) this;
        }

        public Criteria andF1NotIn(List<String> values) {
            addCriterion("F1 not in", values, "f1");
            return (Criteria) this;
        }

        public Criteria andF1Between(String value1, String value2) {
            addCriterion("F1 between", value1, value2, "f1");
            return (Criteria) this;
        }

        public Criteria andF1NotBetween(String value1, String value2) {
            addCriterion("F1 not between", value1, value2, "f1");
            return (Criteria) this;
        }

        public Criteria andF2IsNull() {
            addCriterion("F2 is null");
            return (Criteria) this;
        }

        public Criteria andF2IsNotNull() {
            addCriterion("F2 is not null");
            return (Criteria) this;
        }

        public Criteria andF2EqualTo(String value) {
            addCriterion("F2 =", value, "f2");
            return (Criteria) this;
        }

        public Criteria andF2NotEqualTo(String value) {
            addCriterion("F2 <>", value, "f2");
            return (Criteria) this;
        }

        public Criteria andF2GreaterThan(String value) {
            addCriterion("F2 >", value, "f2");
            return (Criteria) this;
        }

        public Criteria andF2GreaterThanOrEqualTo(String value) {
            addCriterion("F2 >=", value, "f2");
            return (Criteria) this;
        }

        public Criteria andF2LessThan(String value) {
            addCriterion("F2 <", value, "f2");
            return (Criteria) this;
        }

        public Criteria andF2LessThanOrEqualTo(String value) {
            addCriterion("F2 <=", value, "f2");
            return (Criteria) this;
        }

        public Criteria andF2Like(String value) {
            addCriterion("F2 like", value, "f2");
            return (Criteria) this;
        }

        public Criteria andF2NotLike(String value) {
            addCriterion("F2 not like", value, "f2");
            return (Criteria) this;
        }

        public Criteria andF2In(List<String> values) {
            addCriterion("F2 in", values, "f2");
            return (Criteria) this;
        }

        public Criteria andF2NotIn(List<String> values) {
            addCriterion("F2 not in", values, "f2");
            return (Criteria) this;
        }

        public Criteria andF2Between(String value1, String value2) {
            addCriterion("F2 between", value1, value2, "f2");
            return (Criteria) this;
        }

        public Criteria andF2NotBetween(String value1, String value2) {
            addCriterion("F2 not between", value1, value2, "f2");
            return (Criteria) this;
        }

        public Criteria andF3IsNull() {
            addCriterion("F3 is null");
            return (Criteria) this;
        }

        public Criteria andF3IsNotNull() {
            addCriterion("F3 is not null");
            return (Criteria) this;
        }

        public Criteria andF3EqualTo(String value) {
            addCriterion("F3 =", value, "f3");
            return (Criteria) this;
        }

        public Criteria andF3NotEqualTo(String value) {
            addCriterion("F3 <>", value, "f3");
            return (Criteria) this;
        }

        public Criteria andF3GreaterThan(String value) {
            addCriterion("F3 >", value, "f3");
            return (Criteria) this;
        }

        public Criteria andF3GreaterThanOrEqualTo(String value) {
            addCriterion("F3 >=", value, "f3");
            return (Criteria) this;
        }

        public Criteria andF3LessThan(String value) {
            addCriterion("F3 <", value, "f3");
            return (Criteria) this;
        }

        public Criteria andF3LessThanOrEqualTo(String value) {
            addCriterion("F3 <=", value, "f3");
            return (Criteria) this;
        }

        public Criteria andF3Like(String value) {
            addCriterion("F3 like", value, "f3");
            return (Criteria) this;
        }

        public Criteria andF3NotLike(String value) {
            addCriterion("F3 not like", value, "f3");
            return (Criteria) this;
        }

        public Criteria andF3In(List<String> values) {
            addCriterion("F3 in", values, "f3");
            return (Criteria) this;
        }

        public Criteria andF3NotIn(List<String> values) {
            addCriterion("F3 not in", values, "f3");
            return (Criteria) this;
        }

        public Criteria andF3Between(String value1, String value2) {
            addCriterion("F3 between", value1, value2, "f3");
            return (Criteria) this;
        }

        public Criteria andF3NotBetween(String value1, String value2) {
            addCriterion("F3 not between", value1, value2, "f3");
            return (Criteria) this;
        }

        public Criteria andPoDyhIsNull() {
            addCriterion("PO_dyh is null");
            return (Criteria) this;
        }

        public Criteria andPoDyhIsNotNull() {
            addCriterion("PO_dyh is not null");
            return (Criteria) this;
        }

        public Criteria andPoDyhEqualTo(String value) {
            addCriterion("PO_dyh =", value, "poDyh");
            return (Criteria) this;
        }

        public Criteria andPoDyhNotEqualTo(String value) {
            addCriterion("PO_dyh <>", value, "poDyh");
            return (Criteria) this;
        }

        public Criteria andPoDyhGreaterThan(String value) {
            addCriterion("PO_dyh >", value, "poDyh");
            return (Criteria) this;
        }

        public Criteria andPoDyhGreaterThanOrEqualTo(String value) {
            addCriterion("PO_dyh >=", value, "poDyh");
            return (Criteria) this;
        }

        public Criteria andPoDyhLessThan(String value) {
            addCriterion("PO_dyh <", value, "poDyh");
            return (Criteria) this;
        }

        public Criteria andPoDyhLessThanOrEqualTo(String value) {
            addCriterion("PO_dyh <=", value, "poDyh");
            return (Criteria) this;
        }

        public Criteria andPoDyhLike(String value) {
            addCriterion("PO_dyh like", value, "poDyh");
            return (Criteria) this;
        }

        public Criteria andPoDyhNotLike(String value) {
            addCriterion("PO_dyh not like", value, "poDyh");
            return (Criteria) this;
        }

        public Criteria andPoDyhIn(List<String> values) {
            addCriterion("PO_dyh in", values, "poDyh");
            return (Criteria) this;
        }

        public Criteria andPoDyhNotIn(List<String> values) {
            addCriterion("PO_dyh not in", values, "poDyh");
            return (Criteria) this;
        }

        public Criteria andPoDyhBetween(String value1, String value2) {
            addCriterion("PO_dyh between", value1, value2, "poDyh");
            return (Criteria) this;
        }

        public Criteria andPoDyhNotBetween(String value1, String value2) {
            addCriterion("PO_dyh not between", value1, value2, "poDyh");
            return (Criteria) this;
        }

        public Criteria andPoJkrIsNull() {
            addCriterion("PO_jkr is null");
            return (Criteria) this;
        }

        public Criteria andPoJkrIsNotNull() {
            addCriterion("PO_jkr is not null");
            return (Criteria) this;
        }

        public Criteria andPoJkrEqualTo(String value) {
            addCriterion("PO_jkr =", value, "poJkr");
            return (Criteria) this;
        }

        public Criteria andPoJkrNotEqualTo(String value) {
            addCriterion("PO_jkr <>", value, "poJkr");
            return (Criteria) this;
        }

        public Criteria andPoJkrGreaterThan(String value) {
            addCriterion("PO_jkr >", value, "poJkr");
            return (Criteria) this;
        }

        public Criteria andPoJkrGreaterThanOrEqualTo(String value) {
            addCriterion("PO_jkr >=", value, "poJkr");
            return (Criteria) this;
        }

        public Criteria andPoJkrLessThan(String value) {
            addCriterion("PO_jkr <", value, "poJkr");
            return (Criteria) this;
        }

        public Criteria andPoJkrLessThanOrEqualTo(String value) {
            addCriterion("PO_jkr <=", value, "poJkr");
            return (Criteria) this;
        }

        public Criteria andPoJkrLike(String value) {
            addCriterion("PO_jkr like", value, "poJkr");
            return (Criteria) this;
        }

        public Criteria andPoJkrNotLike(String value) {
            addCriterion("PO_jkr not like", value, "poJkr");
            return (Criteria) this;
        }

        public Criteria andPoJkrIn(List<String> values) {
            addCriterion("PO_jkr in", values, "poJkr");
            return (Criteria) this;
        }

        public Criteria andPoJkrNotIn(List<String> values) {
            addCriterion("PO_jkr not in", values, "poJkr");
            return (Criteria) this;
        }

        public Criteria andPoJkrBetween(String value1, String value2) {
            addCriterion("PO_jkr between", value1, value2, "poJkr");
            return (Criteria) this;
        }

        public Criteria andPoJkrNotBetween(String value1, String value2) {
            addCriterion("PO_jkr not between", value1, value2, "poJkr");
            return (Criteria) this;
        }

        public Criteria andF6IsNull() {
            addCriterion("F6 is null");
            return (Criteria) this;
        }

        public Criteria andF6IsNotNull() {
            addCriterion("F6 is not null");
            return (Criteria) this;
        }

        public Criteria andF6EqualTo(String value) {
            addCriterion("F6 =", value, "f6");
            return (Criteria) this;
        }

        public Criteria andF6NotEqualTo(String value) {
            addCriterion("F6 <>", value, "f6");
            return (Criteria) this;
        }

        public Criteria andF6GreaterThan(String value) {
            addCriterion("F6 >", value, "f6");
            return (Criteria) this;
        }

        public Criteria andF6GreaterThanOrEqualTo(String value) {
            addCriterion("F6 >=", value, "f6");
            return (Criteria) this;
        }

        public Criteria andF6LessThan(String value) {
            addCriterion("F6 <", value, "f6");
            return (Criteria) this;
        }

        public Criteria andF6LessThanOrEqualTo(String value) {
            addCriterion("F6 <=", value, "f6");
            return (Criteria) this;
        }

        public Criteria andF6Like(String value) {
            addCriterion("F6 like", value, "f6");
            return (Criteria) this;
        }

        public Criteria andF6NotLike(String value) {
            addCriterion("F6 not like", value, "f6");
            return (Criteria) this;
        }

        public Criteria andF6In(List<String> values) {
            addCriterion("F6 in", values, "f6");
            return (Criteria) this;
        }

        public Criteria andF6NotIn(List<String> values) {
            addCriterion("F6 not in", values, "f6");
            return (Criteria) this;
        }

        public Criteria andF6Between(String value1, String value2) {
            addCriterion("F6 between", value1, value2, "f6");
            return (Criteria) this;
        }

        public Criteria andF6NotBetween(String value1, String value2) {
            addCriterion("F6 not between", value1, value2, "f6");
            return (Criteria) this;
        }

        public Criteria andPoSfzhmIsNull() {
            addCriterion("PO_sfzhm is null");
            return (Criteria) this;
        }

        public Criteria andPoSfzhmIsNotNull() {
            addCriterion("PO_sfzhm is not null");
            return (Criteria) this;
        }

        public Criteria andPoSfzhmEqualTo(String value) {
            addCriterion("PO_sfzhm =", value, "poSfzhm");
            return (Criteria) this;
        }

        public Criteria andPoSfzhmNotEqualTo(String value) {
            addCriterion("PO_sfzhm <>", value, "poSfzhm");
            return (Criteria) this;
        }

        public Criteria andPoSfzhmGreaterThan(String value) {
            addCriterion("PO_sfzhm >", value, "poSfzhm");
            return (Criteria) this;
        }

        public Criteria andPoSfzhmGreaterThanOrEqualTo(String value) {
            addCriterion("PO_sfzhm >=", value, "poSfzhm");
            return (Criteria) this;
        }

        public Criteria andPoSfzhmLessThan(String value) {
            addCriterion("PO_sfzhm <", value, "poSfzhm");
            return (Criteria) this;
        }

        public Criteria andPoSfzhmLessThanOrEqualTo(String value) {
            addCriterion("PO_sfzhm <=", value, "poSfzhm");
            return (Criteria) this;
        }

        public Criteria andPoSfzhmLike(String value) {
            addCriterion("PO_sfzhm like", value, "poSfzhm");
            return (Criteria) this;
        }

        public Criteria andPoSfzhmNotLike(String value) {
            addCriterion("PO_sfzhm not like", value, "poSfzhm");
            return (Criteria) this;
        }

        public Criteria andPoSfzhmIn(List<String> values) {
            addCriterion("PO_sfzhm in", values, "poSfzhm");
            return (Criteria) this;
        }

        public Criteria andPoSfzhmNotIn(List<String> values) {
            addCriterion("PO_sfzhm not in", values, "poSfzhm");
            return (Criteria) this;
        }

        public Criteria andPoSfzhmBetween(String value1, String value2) {
            addCriterion("PO_sfzhm between", value1, value2, "poSfzhm");
            return (Criteria) this;
        }

        public Criteria andPoSfzhmNotBetween(String value1, String value2) {
            addCriterion("PO_sfzhm not between", value1, value2, "poSfzhm");
            return (Criteria) this;
        }

        public Criteria andF8IsNull() {
            addCriterion("F8 is null");
            return (Criteria) this;
        }

        public Criteria andF8IsNotNull() {
            addCriterion("F8 is not null");
            return (Criteria) this;
        }

        public Criteria andF8EqualTo(String value) {
            addCriterion("F8 =", value, "f8");
            return (Criteria) this;
        }

        public Criteria andF8NotEqualTo(String value) {
            addCriterion("F8 <>", value, "f8");
            return (Criteria) this;
        }

        public Criteria andF8GreaterThan(String value) {
            addCriterion("F8 >", value, "f8");
            return (Criteria) this;
        }

        public Criteria andF8GreaterThanOrEqualTo(String value) {
            addCriterion("F8 >=", value, "f8");
            return (Criteria) this;
        }

        public Criteria andF8LessThan(String value) {
            addCriterion("F8 <", value, "f8");
            return (Criteria) this;
        }

        public Criteria andF8LessThanOrEqualTo(String value) {
            addCriterion("F8 <=", value, "f8");
            return (Criteria) this;
        }

        public Criteria andF8Like(String value) {
            addCriterion("F8 like", value, "f8");
            return (Criteria) this;
        }

        public Criteria andF8NotLike(String value) {
            addCriterion("F8 not like", value, "f8");
            return (Criteria) this;
        }

        public Criteria andF8In(List<String> values) {
            addCriterion("F8 in", values, "f8");
            return (Criteria) this;
        }

        public Criteria andF8NotIn(List<String> values) {
            addCriterion("F8 not in", values, "f8");
            return (Criteria) this;
        }

        public Criteria andF8Between(String value1, String value2) {
            addCriterion("F8 between", value1, value2, "f8");
            return (Criteria) this;
        }

        public Criteria andF8NotBetween(String value1, String value2) {
            addCriterion("F8 not between", value1, value2, "f8");
            return (Criteria) this;
        }

        public Criteria andPoHyzkIsNull() {
            addCriterion("PO_hyzk is null");
            return (Criteria) this;
        }

        public Criteria andPoHyzkIsNotNull() {
            addCriterion("PO_hyzk is not null");
            return (Criteria) this;
        }

        public Criteria andPoHyzkEqualTo(String value) {
            addCriterion("PO_hyzk =", value, "poHyzk");
            return (Criteria) this;
        }

        public Criteria andPoHyzkNotEqualTo(String value) {
            addCriterion("PO_hyzk <>", value, "poHyzk");
            return (Criteria) this;
        }

        public Criteria andPoHyzkGreaterThan(String value) {
            addCriterion("PO_hyzk >", value, "poHyzk");
            return (Criteria) this;
        }

        public Criteria andPoHyzkGreaterThanOrEqualTo(String value) {
            addCriterion("PO_hyzk >=", value, "poHyzk");
            return (Criteria) this;
        }

        public Criteria andPoHyzkLessThan(String value) {
            addCriterion("PO_hyzk <", value, "poHyzk");
            return (Criteria) this;
        }

        public Criteria andPoHyzkLessThanOrEqualTo(String value) {
            addCriterion("PO_hyzk <=", value, "poHyzk");
            return (Criteria) this;
        }

        public Criteria andPoHyzkLike(String value) {
            addCriterion("PO_hyzk like", value, "poHyzk");
            return (Criteria) this;
        }

        public Criteria andPoHyzkNotLike(String value) {
            addCriterion("PO_hyzk not like", value, "poHyzk");
            return (Criteria) this;
        }

        public Criteria andPoHyzkIn(List<String> values) {
            addCriterion("PO_hyzk in", values, "poHyzk");
            return (Criteria) this;
        }

        public Criteria andPoHyzkNotIn(List<String> values) {
            addCriterion("PO_hyzk not in", values, "poHyzk");
            return (Criteria) this;
        }

        public Criteria andPoHyzkBetween(String value1, String value2) {
            addCriterion("PO_hyzk between", value1, value2, "poHyzk");
            return (Criteria) this;
        }

        public Criteria andPoHyzkNotBetween(String value1, String value2) {
            addCriterion("PO_hyzk not between", value1, value2, "poHyzk");
            return (Criteria) this;
        }

        public Criteria andPoSjhthIsNull() {
            addCriterion("PO_sjhth is null");
            return (Criteria) this;
        }

        public Criteria andPoSjhthIsNotNull() {
            addCriterion("PO_sjhth is not null");
            return (Criteria) this;
        }

        public Criteria andPoSjhthEqualTo(String value) {
            addCriterion("PO_sjhth =", value, "poSjhth");
            return (Criteria) this;
        }

        public Criteria andPoSjhthNotEqualTo(String value) {
            addCriterion("PO_sjhth <>", value, "poSjhth");
            return (Criteria) this;
        }

        public Criteria andPoSjhthGreaterThan(String value) {
            addCriterion("PO_sjhth >", value, "poSjhth");
            return (Criteria) this;
        }

        public Criteria andPoSjhthGreaterThanOrEqualTo(String value) {
            addCriterion("PO_sjhth >=", value, "poSjhth");
            return (Criteria) this;
        }

        public Criteria andPoSjhthLessThan(String value) {
            addCriterion("PO_sjhth <", value, "poSjhth");
            return (Criteria) this;
        }

        public Criteria andPoSjhthLessThanOrEqualTo(String value) {
            addCriterion("PO_sjhth <=", value, "poSjhth");
            return (Criteria) this;
        }

        public Criteria andPoSjhthLike(String value) {
            addCriterion("PO_sjhth like", value, "poSjhth");
            return (Criteria) this;
        }

        public Criteria andPoSjhthNotLike(String value) {
            addCriterion("PO_sjhth not like", value, "poSjhth");
            return (Criteria) this;
        }

        public Criteria andPoSjhthIn(List<String> values) {
            addCriterion("PO_sjhth in", values, "poSjhth");
            return (Criteria) this;
        }

        public Criteria andPoSjhthNotIn(List<String> values) {
            addCriterion("PO_sjhth not in", values, "poSjhth");
            return (Criteria) this;
        }

        public Criteria andPoSjhthBetween(String value1, String value2) {
            addCriterion("PO_sjhth between", value1, value2, "poSjhth");
            return (Criteria) this;
        }

        public Criteria andPoSjhthNotBetween(String value1, String value2) {
            addCriterion("PO_sjhth not between", value1, value2, "poSjhth");
            return (Criteria) this;
        }

        public Criteria andF11IsNull() {
            addCriterion("F11 is null");
            return (Criteria) this;
        }

        public Criteria andF11IsNotNull() {
            addCriterion("F11 is not null");
            return (Criteria) this;
        }

        public Criteria andF11EqualTo(String value) {
            addCriterion("F11 =", value, "f11");
            return (Criteria) this;
        }

        public Criteria andF11NotEqualTo(String value) {
            addCriterion("F11 <>", value, "f11");
            return (Criteria) this;
        }

        public Criteria andF11GreaterThan(String value) {
            addCriterion("F11 >", value, "f11");
            return (Criteria) this;
        }

        public Criteria andF11GreaterThanOrEqualTo(String value) {
            addCriterion("F11 >=", value, "f11");
            return (Criteria) this;
        }

        public Criteria andF11LessThan(String value) {
            addCriterion("F11 <", value, "f11");
            return (Criteria) this;
        }

        public Criteria andF11LessThanOrEqualTo(String value) {
            addCriterion("F11 <=", value, "f11");
            return (Criteria) this;
        }

        public Criteria andF11Like(String value) {
            addCriterion("F11 like", value, "f11");
            return (Criteria) this;
        }

        public Criteria andF11NotLike(String value) {
            addCriterion("F11 not like", value, "f11");
            return (Criteria) this;
        }

        public Criteria andF11In(List<String> values) {
            addCriterion("F11 in", values, "f11");
            return (Criteria) this;
        }

        public Criteria andF11NotIn(List<String> values) {
            addCriterion("F11 not in", values, "f11");
            return (Criteria) this;
        }

        public Criteria andF11Between(String value1, String value2) {
            addCriterion("F11 between", value1, value2, "f11");
            return (Criteria) this;
        }

        public Criteria andF11NotBetween(String value1, String value2) {
            addCriterion("F11 not between", value1, value2, "f11");
            return (Criteria) this;
        }

        public Criteria andPoDkpzIsNull() {
            addCriterion("PO_dkpz is null");
            return (Criteria) this;
        }

        public Criteria andPoDkpzIsNotNull() {
            addCriterion("PO_dkpz is not null");
            return (Criteria) this;
        }

        public Criteria andPoDkpzEqualTo(String value) {
            addCriterion("PO_dkpz =", value, "poDkpz");
            return (Criteria) this;
        }

        public Criteria andPoDkpzNotEqualTo(String value) {
            addCriterion("PO_dkpz <>", value, "poDkpz");
            return (Criteria) this;
        }

        public Criteria andPoDkpzGreaterThan(String value) {
            addCriterion("PO_dkpz >", value, "poDkpz");
            return (Criteria) this;
        }

        public Criteria andPoDkpzGreaterThanOrEqualTo(String value) {
            addCriterion("PO_dkpz >=", value, "poDkpz");
            return (Criteria) this;
        }

        public Criteria andPoDkpzLessThan(String value) {
            addCriterion("PO_dkpz <", value, "poDkpz");
            return (Criteria) this;
        }

        public Criteria andPoDkpzLessThanOrEqualTo(String value) {
            addCriterion("PO_dkpz <=", value, "poDkpz");
            return (Criteria) this;
        }

        public Criteria andPoDkpzLike(String value) {
            addCriterion("PO_dkpz like", value, "poDkpz");
            return (Criteria) this;
        }

        public Criteria andPoDkpzNotLike(String value) {
            addCriterion("PO_dkpz not like", value, "poDkpz");
            return (Criteria) this;
        }

        public Criteria andPoDkpzIn(List<String> values) {
            addCriterion("PO_dkpz in", values, "poDkpz");
            return (Criteria) this;
        }

        public Criteria andPoDkpzNotIn(List<String> values) {
            addCriterion("PO_dkpz not in", values, "poDkpz");
            return (Criteria) this;
        }

        public Criteria andPoDkpzBetween(String value1, String value2) {
            addCriterion("PO_dkpz between", value1, value2, "poDkpz");
            return (Criteria) this;
        }

        public Criteria andPoDkpzNotBetween(String value1, String value2) {
            addCriterion("PO_dkpz not between", value1, value2, "poDkpz");
            return (Criteria) this;
        }

        public Criteria andF13IsNull() {
            addCriterion("F13 is null");
            return (Criteria) this;
        }

        public Criteria andF13IsNotNull() {
            addCriterion("F13 is not null");
            return (Criteria) this;
        }

        public Criteria andF13EqualTo(String value) {
            addCriterion("F13 =", value, "f13");
            return (Criteria) this;
        }

        public Criteria andF13NotEqualTo(String value) {
            addCriterion("F13 <>", value, "f13");
            return (Criteria) this;
        }

        public Criteria andF13GreaterThan(String value) {
            addCriterion("F13 >", value, "f13");
            return (Criteria) this;
        }

        public Criteria andF13GreaterThanOrEqualTo(String value) {
            addCriterion("F13 >=", value, "f13");
            return (Criteria) this;
        }

        public Criteria andF13LessThan(String value) {
            addCriterion("F13 <", value, "f13");
            return (Criteria) this;
        }

        public Criteria andF13LessThanOrEqualTo(String value) {
            addCriterion("F13 <=", value, "f13");
            return (Criteria) this;
        }

        public Criteria andF13Like(String value) {
            addCriterion("F13 like", value, "f13");
            return (Criteria) this;
        }

        public Criteria andF13NotLike(String value) {
            addCriterion("F13 not like", value, "f13");
            return (Criteria) this;
        }

        public Criteria andF13In(List<String> values) {
            addCriterion("F13 in", values, "f13");
            return (Criteria) this;
        }

        public Criteria andF13NotIn(List<String> values) {
            addCriterion("F13 not in", values, "f13");
            return (Criteria) this;
        }

        public Criteria andF13Between(String value1, String value2) {
            addCriterion("F13 between", value1, value2, "f13");
            return (Criteria) this;
        }

        public Criteria andF13NotBetween(String value1, String value2) {
            addCriterion("F13 not between", value1, value2, "f13");
            return (Criteria) this;
        }

        public Criteria andPoDkjeIsNull() {
            addCriterion("PO_dkje is null");
            return (Criteria) this;
        }

        public Criteria andPoDkjeIsNotNull() {
            addCriterion("PO_dkje is not null");
            return (Criteria) this;
        }

        public Criteria andPoDkjeEqualTo(String value) {
            addCriterion("PO_dkje =", value, "poDkje");
            return (Criteria) this;
        }

        public Criteria andPoDkjeNotEqualTo(String value) {
            addCriterion("PO_dkje <>", value, "poDkje");
            return (Criteria) this;
        }

        public Criteria andPoDkjeGreaterThan(String value) {
            addCriterion("PO_dkje >", value, "poDkje");
            return (Criteria) this;
        }

        public Criteria andPoDkjeGreaterThanOrEqualTo(String value) {
            addCriterion("PO_dkje >=", value, "poDkje");
            return (Criteria) this;
        }

        public Criteria andPoDkjeLessThan(String value) {
            addCriterion("PO_dkje <", value, "poDkje");
            return (Criteria) this;
        }

        public Criteria andPoDkjeLessThanOrEqualTo(String value) {
            addCriterion("PO_dkje <=", value, "poDkje");
            return (Criteria) this;
        }

        public Criteria andPoDkjeLike(String value) {
            addCriterion("PO_dkje like", value, "poDkje");
            return (Criteria) this;
        }

        public Criteria andPoDkjeNotLike(String value) {
            addCriterion("PO_dkje not like", value, "poDkje");
            return (Criteria) this;
        }

        public Criteria andPoDkjeIn(List<String> values) {
            addCriterion("PO_dkje in", values, "poDkje");
            return (Criteria) this;
        }

        public Criteria andPoDkjeNotIn(List<String> values) {
            addCriterion("PO_dkje not in", values, "poDkje");
            return (Criteria) this;
        }

        public Criteria andPoDkjeBetween(String value1, String value2) {
            addCriterion("PO_dkje between", value1, value2, "poDkje");
            return (Criteria) this;
        }

        public Criteria andPoDkjeNotBetween(String value1, String value2) {
            addCriterion("PO_dkje not between", value1, value2, "poDkje");
            return (Criteria) this;
        }

        public Criteria andPoLxIsNull() {
            addCriterion("PO_lx is null");
            return (Criteria) this;
        }

        public Criteria andPoLxIsNotNull() {
            addCriterion("PO_lx is not null");
            return (Criteria) this;
        }

        public Criteria andPoLxEqualTo(String value) {
            addCriterion("PO_lx =", value, "poLx");
            return (Criteria) this;
        }

        public Criteria andPoLxNotEqualTo(String value) {
            addCriterion("PO_lx <>", value, "poLx");
            return (Criteria) this;
        }

        public Criteria andPoLxGreaterThan(String value) {
            addCriterion("PO_lx >", value, "poLx");
            return (Criteria) this;
        }

        public Criteria andPoLxGreaterThanOrEqualTo(String value) {
            addCriterion("PO_lx >=", value, "poLx");
            return (Criteria) this;
        }

        public Criteria andPoLxLessThan(String value) {
            addCriterion("PO_lx <", value, "poLx");
            return (Criteria) this;
        }

        public Criteria andPoLxLessThanOrEqualTo(String value) {
            addCriterion("PO_lx <=", value, "poLx");
            return (Criteria) this;
        }

        public Criteria andPoLxLike(String value) {
            addCriterion("PO_lx like", value, "poLx");
            return (Criteria) this;
        }

        public Criteria andPoLxNotLike(String value) {
            addCriterion("PO_lx not like", value, "poLx");
            return (Criteria) this;
        }

        public Criteria andPoLxIn(List<String> values) {
            addCriterion("PO_lx in", values, "poLx");
            return (Criteria) this;
        }

        public Criteria andPoLxNotIn(List<String> values) {
            addCriterion("PO_lx not in", values, "poLx");
            return (Criteria) this;
        }

        public Criteria andPoLxBetween(String value1, String value2) {
            addCriterion("PO_lx between", value1, value2, "poLx");
            return (Criteria) this;
        }

        public Criteria andPoLxNotBetween(String value1, String value2) {
            addCriterion("PO_lx not between", value1, value2, "poLx");
            return (Criteria) this;
        }

        public Criteria andF16IsNull() {
            addCriterion("F16 is null");
            return (Criteria) this;
        }

        public Criteria andF16IsNotNull() {
            addCriterion("F16 is not null");
            return (Criteria) this;
        }

        public Criteria andF16EqualTo(String value) {
            addCriterion("F16 =", value, "f16");
            return (Criteria) this;
        }

        public Criteria andF16NotEqualTo(String value) {
            addCriterion("F16 <>", value, "f16");
            return (Criteria) this;
        }

        public Criteria andF16GreaterThan(String value) {
            addCriterion("F16 >", value, "f16");
            return (Criteria) this;
        }

        public Criteria andF16GreaterThanOrEqualTo(String value) {
            addCriterion("F16 >=", value, "f16");
            return (Criteria) this;
        }

        public Criteria andF16LessThan(String value) {
            addCriterion("F16 <", value, "f16");
            return (Criteria) this;
        }

        public Criteria andF16LessThanOrEqualTo(String value) {
            addCriterion("F16 <=", value, "f16");
            return (Criteria) this;
        }

        public Criteria andF16Like(String value) {
            addCriterion("F16 like", value, "f16");
            return (Criteria) this;
        }

        public Criteria andF16NotLike(String value) {
            addCriterion("F16 not like", value, "f16");
            return (Criteria) this;
        }

        public Criteria andF16In(List<String> values) {
            addCriterion("F16 in", values, "f16");
            return (Criteria) this;
        }

        public Criteria andF16NotIn(List<String> values) {
            addCriterion("F16 not in", values, "f16");
            return (Criteria) this;
        }

        public Criteria andF16Between(String value1, String value2) {
            addCriterion("F16 between", value1, value2, "f16");
            return (Criteria) this;
        }

        public Criteria andF16NotBetween(String value1, String value2) {
            addCriterion("F16 not between", value1, value2, "f16");
            return (Criteria) this;
        }

        public Criteria andF17IsNull() {
            addCriterion("F17 is null");
            return (Criteria) this;
        }

        public Criteria andF17IsNotNull() {
            addCriterion("F17 is not null");
            return (Criteria) this;
        }

        public Criteria andF17EqualTo(String value) {
            addCriterion("F17 =", value, "f17");
            return (Criteria) this;
        }

        public Criteria andF17NotEqualTo(String value) {
            addCriterion("F17 <>", value, "f17");
            return (Criteria) this;
        }

        public Criteria andF17GreaterThan(String value) {
            addCriterion("F17 >", value, "f17");
            return (Criteria) this;
        }

        public Criteria andF17GreaterThanOrEqualTo(String value) {
            addCriterion("F17 >=", value, "f17");
            return (Criteria) this;
        }

        public Criteria andF17LessThan(String value) {
            addCriterion("F17 <", value, "f17");
            return (Criteria) this;
        }

        public Criteria andF17LessThanOrEqualTo(String value) {
            addCriterion("F17 <=", value, "f17");
            return (Criteria) this;
        }

        public Criteria andF17Like(String value) {
            addCriterion("F17 like", value, "f17");
            return (Criteria) this;
        }

        public Criteria andF17NotLike(String value) {
            addCriterion("F17 not like", value, "f17");
            return (Criteria) this;
        }

        public Criteria andF17In(List<String> values) {
            addCriterion("F17 in", values, "f17");
            return (Criteria) this;
        }

        public Criteria andF17NotIn(List<String> values) {
            addCriterion("F17 not in", values, "f17");
            return (Criteria) this;
        }

        public Criteria andF17Between(String value1, String value2) {
            addCriterion("F17 between", value1, value2, "f17");
            return (Criteria) this;
        }

        public Criteria andF17NotBetween(String value1, String value2) {
            addCriterion("F17 not between", value1, value2, "f17");
            return (Criteria) this;
        }

        public Criteria andPoDbfsIsNull() {
            addCriterion("PO_dbfs is null");
            return (Criteria) this;
        }

        public Criteria andPoDbfsIsNotNull() {
            addCriterion("PO_dbfs is not null");
            return (Criteria) this;
        }

        public Criteria andPoDbfsEqualTo(String value) {
            addCriterion("PO_dbfs =", value, "poDbfs");
            return (Criteria) this;
        }

        public Criteria andPoDbfsNotEqualTo(String value) {
            addCriterion("PO_dbfs <>", value, "poDbfs");
            return (Criteria) this;
        }

        public Criteria andPoDbfsGreaterThan(String value) {
            addCriterion("PO_dbfs >", value, "poDbfs");
            return (Criteria) this;
        }

        public Criteria andPoDbfsGreaterThanOrEqualTo(String value) {
            addCriterion("PO_dbfs >=", value, "poDbfs");
            return (Criteria) this;
        }

        public Criteria andPoDbfsLessThan(String value) {
            addCriterion("PO_dbfs <", value, "poDbfs");
            return (Criteria) this;
        }

        public Criteria andPoDbfsLessThanOrEqualTo(String value) {
            addCriterion("PO_dbfs <=", value, "poDbfs");
            return (Criteria) this;
        }

        public Criteria andPoDbfsLike(String value) {
            addCriterion("PO_dbfs like", value, "poDbfs");
            return (Criteria) this;
        }

        public Criteria andPoDbfsNotLike(String value) {
            addCriterion("PO_dbfs not like", value, "poDbfs");
            return (Criteria) this;
        }

        public Criteria andPoDbfsIn(List<String> values) {
            addCriterion("PO_dbfs in", values, "poDbfs");
            return (Criteria) this;
        }

        public Criteria andPoDbfsNotIn(List<String> values) {
            addCriterion("PO_dbfs not in", values, "poDbfs");
            return (Criteria) this;
        }

        public Criteria andPoDbfsBetween(String value1, String value2) {
            addCriterion("PO_dbfs between", value1, value2, "poDbfs");
            return (Criteria) this;
        }

        public Criteria andPoDbfsNotBetween(String value1, String value2) {
            addCriterion("PO_dbfs not between", value1, value2, "poDbfs");
            return (Criteria) this;
        }

        public Criteria andF19IsNull() {
            addCriterion("F19 is null");
            return (Criteria) this;
        }

        public Criteria andF19IsNotNull() {
            addCriterion("F19 is not null");
            return (Criteria) this;
        }

        public Criteria andF19EqualTo(String value) {
            addCriterion("F19 =", value, "f19");
            return (Criteria) this;
        }

        public Criteria andF19NotEqualTo(String value) {
            addCriterion("F19 <>", value, "f19");
            return (Criteria) this;
        }

        public Criteria andF19GreaterThan(String value) {
            addCriterion("F19 >", value, "f19");
            return (Criteria) this;
        }

        public Criteria andF19GreaterThanOrEqualTo(String value) {
            addCriterion("F19 >=", value, "f19");
            return (Criteria) this;
        }

        public Criteria andF19LessThan(String value) {
            addCriterion("F19 <", value, "f19");
            return (Criteria) this;
        }

        public Criteria andF19LessThanOrEqualTo(String value) {
            addCriterion("F19 <=", value, "f19");
            return (Criteria) this;
        }

        public Criteria andF19Like(String value) {
            addCriterion("F19 like", value, "f19");
            return (Criteria) this;
        }

        public Criteria andF19NotLike(String value) {
            addCriterion("F19 not like", value, "f19");
            return (Criteria) this;
        }

        public Criteria andF19In(List<String> values) {
            addCriterion("F19 in", values, "f19");
            return (Criteria) this;
        }

        public Criteria andF19NotIn(List<String> values) {
            addCriterion("F19 not in", values, "f19");
            return (Criteria) this;
        }

        public Criteria andF19Between(String value1, String value2) {
            addCriterion("F19 between", value1, value2, "f19");
            return (Criteria) this;
        }

        public Criteria andF19NotBetween(String value1, String value2) {
            addCriterion("F19 not between", value1, value2, "f19");
            return (Criteria) this;
        }

        public Criteria andF20IsNull() {
            addCriterion("F20 is null");
            return (Criteria) this;
        }

        public Criteria andF20IsNotNull() {
            addCriterion("F20 is not null");
            return (Criteria) this;
        }

        public Criteria andF20EqualTo(String value) {
            addCriterion("F20 =", value, "f20");
            return (Criteria) this;
        }

        public Criteria andF20NotEqualTo(String value) {
            addCriterion("F20 <>", value, "f20");
            return (Criteria) this;
        }

        public Criteria andF20GreaterThan(String value) {
            addCriterion("F20 >", value, "f20");
            return (Criteria) this;
        }

        public Criteria andF20GreaterThanOrEqualTo(String value) {
            addCriterion("F20 >=", value, "f20");
            return (Criteria) this;
        }

        public Criteria andF20LessThan(String value) {
            addCriterion("F20 <", value, "f20");
            return (Criteria) this;
        }

        public Criteria andF20LessThanOrEqualTo(String value) {
            addCriterion("F20 <=", value, "f20");
            return (Criteria) this;
        }

        public Criteria andF20Like(String value) {
            addCriterion("F20 like", value, "f20");
            return (Criteria) this;
        }

        public Criteria andF20NotLike(String value) {
            addCriterion("F20 not like", value, "f20");
            return (Criteria) this;
        }

        public Criteria andF20In(List<String> values) {
            addCriterion("F20 in", values, "f20");
            return (Criteria) this;
        }

        public Criteria andF20NotIn(List<String> values) {
            addCriterion("F20 not in", values, "f20");
            return (Criteria) this;
        }

        public Criteria andF20Between(String value1, String value2) {
            addCriterion("F20 between", value1, value2, "f20");
            return (Criteria) this;
        }

        public Criteria andF20NotBetween(String value1, String value2) {
            addCriterion("F20 not between", value1, value2, "f20");
            return (Criteria) this;
        }

        public Criteria andPoDywdzIsNull() {
            addCriterion("PO_dywdz is null");
            return (Criteria) this;
        }

        public Criteria andPoDywdzIsNotNull() {
            addCriterion("PO_dywdz is not null");
            return (Criteria) this;
        }

        public Criteria andPoDywdzEqualTo(String value) {
            addCriterion("PO_dywdz =", value, "poDywdz");
            return (Criteria) this;
        }

        public Criteria andPoDywdzNotEqualTo(String value) {
            addCriterion("PO_dywdz <>", value, "poDywdz");
            return (Criteria) this;
        }

        public Criteria andPoDywdzGreaterThan(String value) {
            addCriterion("PO_dywdz >", value, "poDywdz");
            return (Criteria) this;
        }

        public Criteria andPoDywdzGreaterThanOrEqualTo(String value) {
            addCriterion("PO_dywdz >=", value, "poDywdz");
            return (Criteria) this;
        }

        public Criteria andPoDywdzLessThan(String value) {
            addCriterion("PO_dywdz <", value, "poDywdz");
            return (Criteria) this;
        }

        public Criteria andPoDywdzLessThanOrEqualTo(String value) {
            addCriterion("PO_dywdz <=", value, "poDywdz");
            return (Criteria) this;
        }

        public Criteria andPoDywdzLike(String value) {
            addCriterion("PO_dywdz like", value, "poDywdz");
            return (Criteria) this;
        }

        public Criteria andPoDywdzNotLike(String value) {
            addCriterion("PO_dywdz not like", value, "poDywdz");
            return (Criteria) this;
        }

        public Criteria andPoDywdzIn(List<String> values) {
            addCriterion("PO_dywdz in", values, "poDywdz");
            return (Criteria) this;
        }

        public Criteria andPoDywdzNotIn(List<String> values) {
            addCriterion("PO_dywdz not in", values, "poDywdz");
            return (Criteria) this;
        }

        public Criteria andPoDywdzBetween(String value1, String value2) {
            addCriterion("PO_dywdz between", value1, value2, "poDywdz");
            return (Criteria) this;
        }

        public Criteria andPoDywdzNotBetween(String value1, String value2) {
            addCriterion("PO_dywdz not between", value1, value2, "poDywdz");
            return (Criteria) this;
        }

        public Criteria andF22IsNull() {
            addCriterion("F22 is null");
            return (Criteria) this;
        }

        public Criteria andF22IsNotNull() {
            addCriterion("F22 is not null");
            return (Criteria) this;
        }

        public Criteria andF22EqualTo(String value) {
            addCriterion("F22 =", value, "f22");
            return (Criteria) this;
        }

        public Criteria andF22NotEqualTo(String value) {
            addCriterion("F22 <>", value, "f22");
            return (Criteria) this;
        }

        public Criteria andF22GreaterThan(String value) {
            addCriterion("F22 >", value, "f22");
            return (Criteria) this;
        }

        public Criteria andF22GreaterThanOrEqualTo(String value) {
            addCriterion("F22 >=", value, "f22");
            return (Criteria) this;
        }

        public Criteria andF22LessThan(String value) {
            addCriterion("F22 <", value, "f22");
            return (Criteria) this;
        }

        public Criteria andF22LessThanOrEqualTo(String value) {
            addCriterion("F22 <=", value, "f22");
            return (Criteria) this;
        }

        public Criteria andF22Like(String value) {
            addCriterion("F22 like", value, "f22");
            return (Criteria) this;
        }

        public Criteria andF22NotLike(String value) {
            addCriterion("F22 not like", value, "f22");
            return (Criteria) this;
        }

        public Criteria andF22In(List<String> values) {
            addCriterion("F22 in", values, "f22");
            return (Criteria) this;
        }

        public Criteria andF22NotIn(List<String> values) {
            addCriterion("F22 not in", values, "f22");
            return (Criteria) this;
        }

        public Criteria andF22Between(String value1, String value2) {
            addCriterion("F22 between", value1, value2, "f22");
            return (Criteria) this;
        }

        public Criteria andF22NotBetween(String value1, String value2) {
            addCriterion("F22 not between", value1, value2, "f22");
            return (Criteria) this;
        }

        public Criteria andPoFcxzIsNull() {
            addCriterion("PO_fcxz is null");
            return (Criteria) this;
        }

        public Criteria andPoFcxzIsNotNull() {
            addCriterion("PO_fcxz is not null");
            return (Criteria) this;
        }

        public Criteria andPoFcxzEqualTo(String value) {
            addCriterion("PO_fcxz =", value, "poFcxz");
            return (Criteria) this;
        }

        public Criteria andPoFcxzNotEqualTo(String value) {
            addCriterion("PO_fcxz <>", value, "poFcxz");
            return (Criteria) this;
        }

        public Criteria andPoFcxzGreaterThan(String value) {
            addCriterion("PO_fcxz >", value, "poFcxz");
            return (Criteria) this;
        }

        public Criteria andPoFcxzGreaterThanOrEqualTo(String value) {
            addCriterion("PO_fcxz >=", value, "poFcxz");
            return (Criteria) this;
        }

        public Criteria andPoFcxzLessThan(String value) {
            addCriterion("PO_fcxz <", value, "poFcxz");
            return (Criteria) this;
        }

        public Criteria andPoFcxzLessThanOrEqualTo(String value) {
            addCriterion("PO_fcxz <=", value, "poFcxz");
            return (Criteria) this;
        }

        public Criteria andPoFcxzLike(String value) {
            addCriterion("PO_fcxz like", value, "poFcxz");
            return (Criteria) this;
        }

        public Criteria andPoFcxzNotLike(String value) {
            addCriterion("PO_fcxz not like", value, "poFcxz");
            return (Criteria) this;
        }

        public Criteria andPoFcxzIn(List<String> values) {
            addCriterion("PO_fcxz in", values, "poFcxz");
            return (Criteria) this;
        }

        public Criteria andPoFcxzNotIn(List<String> values) {
            addCriterion("PO_fcxz not in", values, "poFcxz");
            return (Criteria) this;
        }

        public Criteria andPoFcxzBetween(String value1, String value2) {
            addCriterion("PO_fcxz between", value1, value2, "poFcxz");
            return (Criteria) this;
        }

        public Criteria andPoFcxzNotBetween(String value1, String value2) {
            addCriterion("PO_fcxz not between", value1, value2, "poFcxz");
            return (Criteria) this;
        }

        public Criteria andF24IsNull() {
            addCriterion("F24 is null");
            return (Criteria) this;
        }

        public Criteria andF24IsNotNull() {
            addCriterion("F24 is not null");
            return (Criteria) this;
        }

        public Criteria andF24EqualTo(String value) {
            addCriterion("F24 =", value, "f24");
            return (Criteria) this;
        }

        public Criteria andF24NotEqualTo(String value) {
            addCriterion("F24 <>", value, "f24");
            return (Criteria) this;
        }

        public Criteria andF24GreaterThan(String value) {
            addCriterion("F24 >", value, "f24");
            return (Criteria) this;
        }

        public Criteria andF24GreaterThanOrEqualTo(String value) {
            addCriterion("F24 >=", value, "f24");
            return (Criteria) this;
        }

        public Criteria andF24LessThan(String value) {
            addCriterion("F24 <", value, "f24");
            return (Criteria) this;
        }

        public Criteria andF24LessThanOrEqualTo(String value) {
            addCriterion("F24 <=", value, "f24");
            return (Criteria) this;
        }

        public Criteria andF24Like(String value) {
            addCriterion("F24 like", value, "f24");
            return (Criteria) this;
        }

        public Criteria andF24NotLike(String value) {
            addCriterion("F24 not like", value, "f24");
            return (Criteria) this;
        }

        public Criteria andF24In(List<String> values) {
            addCriterion("F24 in", values, "f24");
            return (Criteria) this;
        }

        public Criteria andF24NotIn(List<String> values) {
            addCriterion("F24 not in", values, "f24");
            return (Criteria) this;
        }

        public Criteria andF24Between(String value1, String value2) {
            addCriterion("F24 between", value1, value2, "f24");
            return (Criteria) this;
        }

        public Criteria andF24NotBetween(String value1, String value2) {
            addCriterion("F24 not between", value1, value2, "f24");
            return (Criteria) this;
        }

        public Criteria andF25IsNull() {
            addCriterion("F25 is null");
            return (Criteria) this;
        }

        public Criteria andF25IsNotNull() {
            addCriterion("F25 is not null");
            return (Criteria) this;
        }

        public Criteria andF25EqualTo(String value) {
            addCriterion("F25 =", value, "f25");
            return (Criteria) this;
        }

        public Criteria andF25NotEqualTo(String value) {
            addCriterion("F25 <>", value, "f25");
            return (Criteria) this;
        }

        public Criteria andF25GreaterThan(String value) {
            addCriterion("F25 >", value, "f25");
            return (Criteria) this;
        }

        public Criteria andF25GreaterThanOrEqualTo(String value) {
            addCriterion("F25 >=", value, "f25");
            return (Criteria) this;
        }

        public Criteria andF25LessThan(String value) {
            addCriterion("F25 <", value, "f25");
            return (Criteria) this;
        }

        public Criteria andF25LessThanOrEqualTo(String value) {
            addCriterion("F25 <=", value, "f25");
            return (Criteria) this;
        }

        public Criteria andF25Like(String value) {
            addCriterion("F25 like", value, "f25");
            return (Criteria) this;
        }

        public Criteria andF25NotLike(String value) {
            addCriterion("F25 not like", value, "f25");
            return (Criteria) this;
        }

        public Criteria andF25In(List<String> values) {
            addCriterion("F25 in", values, "f25");
            return (Criteria) this;
        }

        public Criteria andF25NotIn(List<String> values) {
            addCriterion("F25 not in", values, "f25");
            return (Criteria) this;
        }

        public Criteria andF25Between(String value1, String value2) {
            addCriterion("F25 between", value1, value2, "f25");
            return (Criteria) this;
        }

        public Criteria andF25NotBetween(String value1, String value2) {
            addCriterion("F25 not between", value1, value2, "f25");
            return (Criteria) this;
        }

        public Criteria andF26IsNull() {
            addCriterion("F26 is null");
            return (Criteria) this;
        }

        public Criteria andF26IsNotNull() {
            addCriterion("F26 is not null");
            return (Criteria) this;
        }

        public Criteria andF26EqualTo(String value) {
            addCriterion("F26 =", value, "f26");
            return (Criteria) this;
        }

        public Criteria andF26NotEqualTo(String value) {
            addCriterion("F26 <>", value, "f26");
            return (Criteria) this;
        }

        public Criteria andF26GreaterThan(String value) {
            addCriterion("F26 >", value, "f26");
            return (Criteria) this;
        }

        public Criteria andF26GreaterThanOrEqualTo(String value) {
            addCriterion("F26 >=", value, "f26");
            return (Criteria) this;
        }

        public Criteria andF26LessThan(String value) {
            addCriterion("F26 <", value, "f26");
            return (Criteria) this;
        }

        public Criteria andF26LessThanOrEqualTo(String value) {
            addCriterion("F26 <=", value, "f26");
            return (Criteria) this;
        }

        public Criteria andF26Like(String value) {
            addCriterion("F26 like", value, "f26");
            return (Criteria) this;
        }

        public Criteria andF26NotLike(String value) {
            addCriterion("F26 not like", value, "f26");
            return (Criteria) this;
        }

        public Criteria andF26In(List<String> values) {
            addCriterion("F26 in", values, "f26");
            return (Criteria) this;
        }

        public Criteria andF26NotIn(List<String> values) {
            addCriterion("F26 not in", values, "f26");
            return (Criteria) this;
        }

        public Criteria andF26Between(String value1, String value2) {
            addCriterion("F26 between", value1, value2, "f26");
            return (Criteria) this;
        }

        public Criteria andF26NotBetween(String value1, String value2) {
            addCriterion("F26 not between", value1, value2, "f26");
            return (Criteria) this;
        }

        public Criteria andPoFcmjIsNull() {
            addCriterion("PO_fcmj is null");
            return (Criteria) this;
        }

        public Criteria andPoFcmjIsNotNull() {
            addCriterion("PO_fcmj is not null");
            return (Criteria) this;
        }

        public Criteria andPoFcmjEqualTo(String value) {
            addCriterion("PO_fcmj =", value, "poFcmj");
            return (Criteria) this;
        }

        public Criteria andPoFcmjNotEqualTo(String value) {
            addCriterion("PO_fcmj <>", value, "poFcmj");
            return (Criteria) this;
        }

        public Criteria andPoFcmjGreaterThan(String value) {
            addCriterion("PO_fcmj >", value, "poFcmj");
            return (Criteria) this;
        }

        public Criteria andPoFcmjGreaterThanOrEqualTo(String value) {
            addCriterion("PO_fcmj >=", value, "poFcmj");
            return (Criteria) this;
        }

        public Criteria andPoFcmjLessThan(String value) {
            addCriterion("PO_fcmj <", value, "poFcmj");
            return (Criteria) this;
        }

        public Criteria andPoFcmjLessThanOrEqualTo(String value) {
            addCriterion("PO_fcmj <=", value, "poFcmj");
            return (Criteria) this;
        }

        public Criteria andPoFcmjLike(String value) {
            addCriterion("PO_fcmj like", value, "poFcmj");
            return (Criteria) this;
        }

        public Criteria andPoFcmjNotLike(String value) {
            addCriterion("PO_fcmj not like", value, "poFcmj");
            return (Criteria) this;
        }

        public Criteria andPoFcmjIn(List<String> values) {
            addCriterion("PO_fcmj in", values, "poFcmj");
            return (Criteria) this;
        }

        public Criteria andPoFcmjNotIn(List<String> values) {
            addCriterion("PO_fcmj not in", values, "poFcmj");
            return (Criteria) this;
        }

        public Criteria andPoFcmjBetween(String value1, String value2) {
            addCriterion("PO_fcmj between", value1, value2, "poFcmj");
            return (Criteria) this;
        }

        public Criteria andPoFcmjNotBetween(String value1, String value2) {
            addCriterion("PO_fcmj not between", value1, value2, "poFcmj");
            return (Criteria) this;
        }

        public Criteria andPoDywscdjIsNull() {
            addCriterion("PO_dywscdj is null");
            return (Criteria) this;
        }

        public Criteria andPoDywscdjIsNotNull() {
            addCriterion("PO_dywscdj is not null");
            return (Criteria) this;
        }

        public Criteria andPoDywscdjEqualTo(String value) {
            addCriterion("PO_dywscdj =", value, "poDywscdj");
            return (Criteria) this;
        }

        public Criteria andPoDywscdjNotEqualTo(String value) {
            addCriterion("PO_dywscdj <>", value, "poDywscdj");
            return (Criteria) this;
        }

        public Criteria andPoDywscdjGreaterThan(String value) {
            addCriterion("PO_dywscdj >", value, "poDywscdj");
            return (Criteria) this;
        }

        public Criteria andPoDywscdjGreaterThanOrEqualTo(String value) {
            addCriterion("PO_dywscdj >=", value, "poDywscdj");
            return (Criteria) this;
        }

        public Criteria andPoDywscdjLessThan(String value) {
            addCriterion("PO_dywscdj <", value, "poDywscdj");
            return (Criteria) this;
        }

        public Criteria andPoDywscdjLessThanOrEqualTo(String value) {
            addCriterion("PO_dywscdj <=", value, "poDywscdj");
            return (Criteria) this;
        }

        public Criteria andPoDywscdjLike(String value) {
            addCriterion("PO_dywscdj like", value, "poDywscdj");
            return (Criteria) this;
        }

        public Criteria andPoDywscdjNotLike(String value) {
            addCriterion("PO_dywscdj not like", value, "poDywscdj");
            return (Criteria) this;
        }

        public Criteria andPoDywscdjIn(List<String> values) {
            addCriterion("PO_dywscdj in", values, "poDywscdj");
            return (Criteria) this;
        }

        public Criteria andPoDywscdjNotIn(List<String> values) {
            addCriterion("PO_dywscdj not in", values, "poDywscdj");
            return (Criteria) this;
        }

        public Criteria andPoDywscdjBetween(String value1, String value2) {
            addCriterion("PO_dywscdj between", value1, value2, "poDywscdj");
            return (Criteria) this;
        }

        public Criteria andPoDywscdjNotBetween(String value1, String value2) {
            addCriterion("PO_dywscdj not between", value1, value2, "poDywscdj");
            return (Criteria) this;
        }

        public Criteria andPoDywsczjIsNull() {
            addCriterion("PO_dywsczj is null");
            return (Criteria) this;
        }

        public Criteria andPoDywsczjIsNotNull() {
            addCriterion("PO_dywsczj is not null");
            return (Criteria) this;
        }

        public Criteria andPoDywsczjEqualTo(String value) {
            addCriterion("PO_dywsczj =", value, "poDywsczj");
            return (Criteria) this;
        }

        public Criteria andPoDywsczjNotEqualTo(String value) {
            addCriterion("PO_dywsczj <>", value, "poDywsczj");
            return (Criteria) this;
        }

        public Criteria andPoDywsczjGreaterThan(String value) {
            addCriterion("PO_dywsczj >", value, "poDywsczj");
            return (Criteria) this;
        }

        public Criteria andPoDywsczjGreaterThanOrEqualTo(String value) {
            addCriterion("PO_dywsczj >=", value, "poDywsczj");
            return (Criteria) this;
        }

        public Criteria andPoDywsczjLessThan(String value) {
            addCriterion("PO_dywsczj <", value, "poDywsczj");
            return (Criteria) this;
        }

        public Criteria andPoDywsczjLessThanOrEqualTo(String value) {
            addCriterion("PO_dywsczj <=", value, "poDywsczj");
            return (Criteria) this;
        }

        public Criteria andPoDywsczjLike(String value) {
            addCriterion("PO_dywsczj like", value, "poDywsczj");
            return (Criteria) this;
        }

        public Criteria andPoDywsczjNotLike(String value) {
            addCriterion("PO_dywsczj not like", value, "poDywsczj");
            return (Criteria) this;
        }

        public Criteria andPoDywsczjIn(List<String> values) {
            addCriterion("PO_dywsczj in", values, "poDywsczj");
            return (Criteria) this;
        }

        public Criteria andPoDywsczjNotIn(List<String> values) {
            addCriterion("PO_dywsczj not in", values, "poDywsczj");
            return (Criteria) this;
        }

        public Criteria andPoDywsczjBetween(String value1, String value2) {
            addCriterion("PO_dywsczj between", value1, value2, "poDywsczj");
            return (Criteria) this;
        }

        public Criteria andPoDywsczjNotBetween(String value1, String value2) {
            addCriterion("PO_dywsczj not between", value1, value2, "poDywsczj");
            return (Criteria) this;
        }

        public Criteria andPoDywbxlIsNull() {
            addCriterion("PO_dywbxl is null");
            return (Criteria) this;
        }

        public Criteria andPoDywbxlIsNotNull() {
            addCriterion("PO_dywbxl is not null");
            return (Criteria) this;
        }

        public Criteria andPoDywbxlEqualTo(String value) {
            addCriterion("PO_dywbxl =", value, "poDywbxl");
            return (Criteria) this;
        }

        public Criteria andPoDywbxlNotEqualTo(String value) {
            addCriterion("PO_dywbxl <>", value, "poDywbxl");
            return (Criteria) this;
        }

        public Criteria andPoDywbxlGreaterThan(String value) {
            addCriterion("PO_dywbxl >", value, "poDywbxl");
            return (Criteria) this;
        }

        public Criteria andPoDywbxlGreaterThanOrEqualTo(String value) {
            addCriterion("PO_dywbxl >=", value, "poDywbxl");
            return (Criteria) this;
        }

        public Criteria andPoDywbxlLessThan(String value) {
            addCriterion("PO_dywbxl <", value, "poDywbxl");
            return (Criteria) this;
        }

        public Criteria andPoDywbxlLessThanOrEqualTo(String value) {
            addCriterion("PO_dywbxl <=", value, "poDywbxl");
            return (Criteria) this;
        }

        public Criteria andPoDywbxlLike(String value) {
            addCriterion("PO_dywbxl like", value, "poDywbxl");
            return (Criteria) this;
        }

        public Criteria andPoDywbxlNotLike(String value) {
            addCriterion("PO_dywbxl not like", value, "poDywbxl");
            return (Criteria) this;
        }

        public Criteria andPoDywbxlIn(List<String> values) {
            addCriterion("PO_dywbxl in", values, "poDywbxl");
            return (Criteria) this;
        }

        public Criteria andPoDywbxlNotIn(List<String> values) {
            addCriterion("PO_dywbxl not in", values, "poDywbxl");
            return (Criteria) this;
        }

        public Criteria andPoDywbxlBetween(String value1, String value2) {
            addCriterion("PO_dywbxl between", value1, value2, "poDywbxl");
            return (Criteria) this;
        }

        public Criteria andPoDywbxlNotBetween(String value1, String value2) {
            addCriterion("PO_dywbxl not between", value1, value2, "poDywbxl");
            return (Criteria) this;
        }

        public Criteria andPoDywbxjzIsNull() {
            addCriterion("PO_dywbxjz is null");
            return (Criteria) this;
        }

        public Criteria andPoDywbxjzIsNotNull() {
            addCriterion("PO_dywbxjz is not null");
            return (Criteria) this;
        }

        public Criteria andPoDywbxjzEqualTo(String value) {
            addCriterion("PO_dywbxjz =", value, "poDywbxjz");
            return (Criteria) this;
        }

        public Criteria andPoDywbxjzNotEqualTo(String value) {
            addCriterion("PO_dywbxjz <>", value, "poDywbxjz");
            return (Criteria) this;
        }

        public Criteria andPoDywbxjzGreaterThan(String value) {
            addCriterion("PO_dywbxjz >", value, "poDywbxjz");
            return (Criteria) this;
        }

        public Criteria andPoDywbxjzGreaterThanOrEqualTo(String value) {
            addCriterion("PO_dywbxjz >=", value, "poDywbxjz");
            return (Criteria) this;
        }

        public Criteria andPoDywbxjzLessThan(String value) {
            addCriterion("PO_dywbxjz <", value, "poDywbxjz");
            return (Criteria) this;
        }

        public Criteria andPoDywbxjzLessThanOrEqualTo(String value) {
            addCriterion("PO_dywbxjz <=", value, "poDywbxjz");
            return (Criteria) this;
        }

        public Criteria andPoDywbxjzLike(String value) {
            addCriterion("PO_dywbxjz like", value, "poDywbxjz");
            return (Criteria) this;
        }

        public Criteria andPoDywbxjzNotLike(String value) {
            addCriterion("PO_dywbxjz not like", value, "poDywbxjz");
            return (Criteria) this;
        }

        public Criteria andPoDywbxjzIn(List<String> values) {
            addCriterion("PO_dywbxjz in", values, "poDywbxjz");
            return (Criteria) this;
        }

        public Criteria andPoDywbxjzNotIn(List<String> values) {
            addCriterion("PO_dywbxjz not in", values, "poDywbxjz");
            return (Criteria) this;
        }

        public Criteria andPoDywbxjzBetween(String value1, String value2) {
            addCriterion("PO_dywbxjz between", value1, value2, "poDywbxjz");
            return (Criteria) this;
        }

        public Criteria andPoDywbxjzNotBetween(String value1, String value2) {
            addCriterion("PO_dywbxjz not between", value1, value2, "poDywbxjz");
            return (Criteria) this;
        }

        public Criteria andF32IsNull() {
            addCriterion("F32 is null");
            return (Criteria) this;
        }

        public Criteria andF32IsNotNull() {
            addCriterion("F32 is not null");
            return (Criteria) this;
        }

        public Criteria andF32EqualTo(String value) {
            addCriterion("F32 =", value, "f32");
            return (Criteria) this;
        }

        public Criteria andF32NotEqualTo(String value) {
            addCriterion("F32 <>", value, "f32");
            return (Criteria) this;
        }

        public Criteria andF32GreaterThan(String value) {
            addCriterion("F32 >", value, "f32");
            return (Criteria) this;
        }

        public Criteria andF32GreaterThanOrEqualTo(String value) {
            addCriterion("F32 >=", value, "f32");
            return (Criteria) this;
        }

        public Criteria andF32LessThan(String value) {
            addCriterion("F32 <", value, "f32");
            return (Criteria) this;
        }

        public Criteria andF32LessThanOrEqualTo(String value) {
            addCriterion("F32 <=", value, "f32");
            return (Criteria) this;
        }

        public Criteria andF32Like(String value) {
            addCriterion("F32 like", value, "f32");
            return (Criteria) this;
        }

        public Criteria andF32NotLike(String value) {
            addCriterion("F32 not like", value, "f32");
            return (Criteria) this;
        }

        public Criteria andF32In(List<String> values) {
            addCriterion("F32 in", values, "f32");
            return (Criteria) this;
        }

        public Criteria andF32NotIn(List<String> values) {
            addCriterion("F32 not in", values, "f32");
            return (Criteria) this;
        }

        public Criteria andF32Between(String value1, String value2) {
            addCriterion("F32 between", value1, value2, "f32");
            return (Criteria) this;
        }

        public Criteria andF32NotBetween(String value1, String value2) {
            addCriterion("F32 not between", value1, value2, "f32");
            return (Criteria) this;
        }

        public Criteria andPoPmfblIsNull() {
            addCriterion("PO_pmfbl is null");
            return (Criteria) this;
        }

        public Criteria andPoPmfblIsNotNull() {
            addCriterion("PO_pmfbl is not null");
            return (Criteria) this;
        }

        public Criteria andPoPmfblEqualTo(String value) {
            addCriterion("PO_pmfbl =", value, "poPmfbl");
            return (Criteria) this;
        }

        public Criteria andPoPmfblNotEqualTo(String value) {
            addCriterion("PO_pmfbl <>", value, "poPmfbl");
            return (Criteria) this;
        }

        public Criteria andPoPmfblGreaterThan(String value) {
            addCriterion("PO_pmfbl >", value, "poPmfbl");
            return (Criteria) this;
        }

        public Criteria andPoPmfblGreaterThanOrEqualTo(String value) {
            addCriterion("PO_pmfbl >=", value, "poPmfbl");
            return (Criteria) this;
        }

        public Criteria andPoPmfblLessThan(String value) {
            addCriterion("PO_pmfbl <", value, "poPmfbl");
            return (Criteria) this;
        }

        public Criteria andPoPmfblLessThanOrEqualTo(String value) {
            addCriterion("PO_pmfbl <=", value, "poPmfbl");
            return (Criteria) this;
        }

        public Criteria andPoPmfblLike(String value) {
            addCriterion("PO_pmfbl like", value, "poPmfbl");
            return (Criteria) this;
        }

        public Criteria andPoPmfblNotLike(String value) {
            addCriterion("PO_pmfbl not like", value, "poPmfbl");
            return (Criteria) this;
        }

        public Criteria andPoPmfblIn(List<String> values) {
            addCriterion("PO_pmfbl in", values, "poPmfbl");
            return (Criteria) this;
        }

        public Criteria andPoPmfblNotIn(List<String> values) {
            addCriterion("PO_pmfbl not in", values, "poPmfbl");
            return (Criteria) this;
        }

        public Criteria andPoPmfblBetween(String value1, String value2) {
            addCriterion("PO_pmfbl between", value1, value2, "poPmfbl");
            return (Criteria) this;
        }

        public Criteria andPoPmfblNotBetween(String value1, String value2) {
            addCriterion("PO_pmfbl not between", value1, value2, "poPmfbl");
            return (Criteria) this;
        }

        public Criteria andPoPmfjeIsNull() {
            addCriterion("PO_pmfje is null");
            return (Criteria) this;
        }

        public Criteria andPoPmfjeIsNotNull() {
            addCriterion("PO_pmfje is not null");
            return (Criteria) this;
        }

        public Criteria andPoPmfjeEqualTo(String value) {
            addCriterion("PO_pmfje =", value, "poPmfje");
            return (Criteria) this;
        }

        public Criteria andPoPmfjeNotEqualTo(String value) {
            addCriterion("PO_pmfje <>", value, "poPmfje");
            return (Criteria) this;
        }

        public Criteria andPoPmfjeGreaterThan(String value) {
            addCriterion("PO_pmfje >", value, "poPmfje");
            return (Criteria) this;
        }

        public Criteria andPoPmfjeGreaterThanOrEqualTo(String value) {
            addCriterion("PO_pmfje >=", value, "poPmfje");
            return (Criteria) this;
        }

        public Criteria andPoPmfjeLessThan(String value) {
            addCriterion("PO_pmfje <", value, "poPmfje");
            return (Criteria) this;
        }

        public Criteria andPoPmfjeLessThanOrEqualTo(String value) {
            addCriterion("PO_pmfje <=", value, "poPmfje");
            return (Criteria) this;
        }

        public Criteria andPoPmfjeLike(String value) {
            addCriterion("PO_pmfje like", value, "poPmfje");
            return (Criteria) this;
        }

        public Criteria andPoPmfjeNotLike(String value) {
            addCriterion("PO_pmfje not like", value, "poPmfje");
            return (Criteria) this;
        }

        public Criteria andPoPmfjeIn(List<String> values) {
            addCriterion("PO_pmfje in", values, "poPmfje");
            return (Criteria) this;
        }

        public Criteria andPoPmfjeNotIn(List<String> values) {
            addCriterion("PO_pmfje not in", values, "poPmfje");
            return (Criteria) this;
        }

        public Criteria andPoPmfjeBetween(String value1, String value2) {
            addCriterion("PO_pmfje between", value1, value2, "poPmfje");
            return (Criteria) this;
        }

        public Criteria andPoPmfjeNotBetween(String value1, String value2) {
            addCriterion("PO_pmfje not between", value1, value2, "poPmfje");
            return (Criteria) this;
        }

        public Criteria andPoSsfblIsNull() {
            addCriterion("PO_ssfbl is null");
            return (Criteria) this;
        }

        public Criteria andPoSsfblIsNotNull() {
            addCriterion("PO_ssfbl is not null");
            return (Criteria) this;
        }

        public Criteria andPoSsfblEqualTo(String value) {
            addCriterion("PO_ssfbl =", value, "poSsfbl");
            return (Criteria) this;
        }

        public Criteria andPoSsfblNotEqualTo(String value) {
            addCriterion("PO_ssfbl <>", value, "poSsfbl");
            return (Criteria) this;
        }

        public Criteria andPoSsfblGreaterThan(String value) {
            addCriterion("PO_ssfbl >", value, "poSsfbl");
            return (Criteria) this;
        }

        public Criteria andPoSsfblGreaterThanOrEqualTo(String value) {
            addCriterion("PO_ssfbl >=", value, "poSsfbl");
            return (Criteria) this;
        }

        public Criteria andPoSsfblLessThan(String value) {
            addCriterion("PO_ssfbl <", value, "poSsfbl");
            return (Criteria) this;
        }

        public Criteria andPoSsfblLessThanOrEqualTo(String value) {
            addCriterion("PO_ssfbl <=", value, "poSsfbl");
            return (Criteria) this;
        }

        public Criteria andPoSsfblLike(String value) {
            addCriterion("PO_ssfbl like", value, "poSsfbl");
            return (Criteria) this;
        }

        public Criteria andPoSsfblNotLike(String value) {
            addCriterion("PO_ssfbl not like", value, "poSsfbl");
            return (Criteria) this;
        }

        public Criteria andPoSsfblIn(List<String> values) {
            addCriterion("PO_ssfbl in", values, "poSsfbl");
            return (Criteria) this;
        }

        public Criteria andPoSsfblNotIn(List<String> values) {
            addCriterion("PO_ssfbl not in", values, "poSsfbl");
            return (Criteria) this;
        }

        public Criteria andPoSsfblBetween(String value1, String value2) {
            addCriterion("PO_ssfbl between", value1, value2, "poSsfbl");
            return (Criteria) this;
        }

        public Criteria andPoSsfblNotBetween(String value1, String value2) {
            addCriterion("PO_ssfbl not between", value1, value2, "poSsfbl");
            return (Criteria) this;
        }

        public Criteria andPoSsfjeIsNull() {
            addCriterion("PO_ssfje is null");
            return (Criteria) this;
        }

        public Criteria andPoSsfjeIsNotNull() {
            addCriterion("PO_ssfje is not null");
            return (Criteria) this;
        }

        public Criteria andPoSsfjeEqualTo(String value) {
            addCriterion("PO_ssfje =", value, "poSsfje");
            return (Criteria) this;
        }

        public Criteria andPoSsfjeNotEqualTo(String value) {
            addCriterion("PO_ssfje <>", value, "poSsfje");
            return (Criteria) this;
        }

        public Criteria andPoSsfjeGreaterThan(String value) {
            addCriterion("PO_ssfje >", value, "poSsfje");
            return (Criteria) this;
        }

        public Criteria andPoSsfjeGreaterThanOrEqualTo(String value) {
            addCriterion("PO_ssfje >=", value, "poSsfje");
            return (Criteria) this;
        }

        public Criteria andPoSsfjeLessThan(String value) {
            addCriterion("PO_ssfje <", value, "poSsfje");
            return (Criteria) this;
        }

        public Criteria andPoSsfjeLessThanOrEqualTo(String value) {
            addCriterion("PO_ssfje <=", value, "poSsfje");
            return (Criteria) this;
        }

        public Criteria andPoSsfjeLike(String value) {
            addCriterion("PO_ssfje like", value, "poSsfje");
            return (Criteria) this;
        }

        public Criteria andPoSsfjeNotLike(String value) {
            addCriterion("PO_ssfje not like", value, "poSsfje");
            return (Criteria) this;
        }

        public Criteria andPoSsfjeIn(List<String> values) {
            addCriterion("PO_ssfje in", values, "poSsfje");
            return (Criteria) this;
        }

        public Criteria andPoSsfjeNotIn(List<String> values) {
            addCriterion("PO_ssfje not in", values, "poSsfje");
            return (Criteria) this;
        }

        public Criteria andPoSsfjeBetween(String value1, String value2) {
            addCriterion("PO_ssfje between", value1, value2, "poSsfje");
            return (Criteria) this;
        }

        public Criteria andPoSsfjeNotBetween(String value1, String value2) {
            addCriterion("PO_ssfje not between", value1, value2, "poSsfje");
            return (Criteria) this;
        }

        public Criteria andPoZxfblIsNull() {
            addCriterion("PO_zxfbl is null");
            return (Criteria) this;
        }

        public Criteria andPoZxfblIsNotNull() {
            addCriterion("PO_zxfbl is not null");
            return (Criteria) this;
        }

        public Criteria andPoZxfblEqualTo(String value) {
            addCriterion("PO_zxfbl =", value, "poZxfbl");
            return (Criteria) this;
        }

        public Criteria andPoZxfblNotEqualTo(String value) {
            addCriterion("PO_zxfbl <>", value, "poZxfbl");
            return (Criteria) this;
        }

        public Criteria andPoZxfblGreaterThan(String value) {
            addCriterion("PO_zxfbl >", value, "poZxfbl");
            return (Criteria) this;
        }

        public Criteria andPoZxfblGreaterThanOrEqualTo(String value) {
            addCriterion("PO_zxfbl >=", value, "poZxfbl");
            return (Criteria) this;
        }

        public Criteria andPoZxfblLessThan(String value) {
            addCriterion("PO_zxfbl <", value, "poZxfbl");
            return (Criteria) this;
        }

        public Criteria andPoZxfblLessThanOrEqualTo(String value) {
            addCriterion("PO_zxfbl <=", value, "poZxfbl");
            return (Criteria) this;
        }

        public Criteria andPoZxfblLike(String value) {
            addCriterion("PO_zxfbl like", value, "poZxfbl");
            return (Criteria) this;
        }

        public Criteria andPoZxfblNotLike(String value) {
            addCriterion("PO_zxfbl not like", value, "poZxfbl");
            return (Criteria) this;
        }

        public Criteria andPoZxfblIn(List<String> values) {
            addCriterion("PO_zxfbl in", values, "poZxfbl");
            return (Criteria) this;
        }

        public Criteria andPoZxfblNotIn(List<String> values) {
            addCriterion("PO_zxfbl not in", values, "poZxfbl");
            return (Criteria) this;
        }

        public Criteria andPoZxfblBetween(String value1, String value2) {
            addCriterion("PO_zxfbl between", value1, value2, "poZxfbl");
            return (Criteria) this;
        }

        public Criteria andPoZxfblNotBetween(String value1, String value2) {
            addCriterion("PO_zxfbl not between", value1, value2, "poZxfbl");
            return (Criteria) this;
        }

        public Criteria andPoZxfjeIsNull() {
            addCriterion("PO_zxfje is null");
            return (Criteria) this;
        }

        public Criteria andPoZxfjeIsNotNull() {
            addCriterion("PO_zxfje is not null");
            return (Criteria) this;
        }

        public Criteria andPoZxfjeEqualTo(String value) {
            addCriterion("PO_zxfje =", value, "poZxfje");
            return (Criteria) this;
        }

        public Criteria andPoZxfjeNotEqualTo(String value) {
            addCriterion("PO_zxfje <>", value, "poZxfje");
            return (Criteria) this;
        }

        public Criteria andPoZxfjeGreaterThan(String value) {
            addCriterion("PO_zxfje >", value, "poZxfje");
            return (Criteria) this;
        }

        public Criteria andPoZxfjeGreaterThanOrEqualTo(String value) {
            addCriterion("PO_zxfje >=", value, "poZxfje");
            return (Criteria) this;
        }

        public Criteria andPoZxfjeLessThan(String value) {
            addCriterion("PO_zxfje <", value, "poZxfje");
            return (Criteria) this;
        }

        public Criteria andPoZxfjeLessThanOrEqualTo(String value) {
            addCriterion("PO_zxfje <=", value, "poZxfje");
            return (Criteria) this;
        }

        public Criteria andPoZxfjeLike(String value) {
            addCriterion("PO_zxfje like", value, "poZxfje");
            return (Criteria) this;
        }

        public Criteria andPoZxfjeNotLike(String value) {
            addCriterion("PO_zxfje not like", value, "poZxfje");
            return (Criteria) this;
        }

        public Criteria andPoZxfjeIn(List<String> values) {
            addCriterion("PO_zxfje in", values, "poZxfje");
            return (Criteria) this;
        }

        public Criteria andPoZxfjeNotIn(List<String> values) {
            addCriterion("PO_zxfje not in", values, "poZxfje");
            return (Criteria) this;
        }

        public Criteria andPoZxfjeBetween(String value1, String value2) {
            addCriterion("PO_zxfje between", value1, value2, "poZxfje");
            return (Criteria) this;
        }

        public Criteria andPoZxfjeNotBetween(String value1, String value2) {
            addCriterion("PO_zxfje not between", value1, value2, "poZxfje");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfblIsNull() {
            addCriterion("PO_sfjdfbl is null");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfblIsNotNull() {
            addCriterion("PO_sfjdfbl is not null");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfblEqualTo(String value) {
            addCriterion("PO_sfjdfbl =", value, "poSfjdfbl");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfblNotEqualTo(String value) {
            addCriterion("PO_sfjdfbl <>", value, "poSfjdfbl");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfblGreaterThan(String value) {
            addCriterion("PO_sfjdfbl >", value, "poSfjdfbl");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfblGreaterThanOrEqualTo(String value) {
            addCriterion("PO_sfjdfbl >=", value, "poSfjdfbl");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfblLessThan(String value) {
            addCriterion("PO_sfjdfbl <", value, "poSfjdfbl");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfblLessThanOrEqualTo(String value) {
            addCriterion("PO_sfjdfbl <=", value, "poSfjdfbl");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfblLike(String value) {
            addCriterion("PO_sfjdfbl like", value, "poSfjdfbl");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfblNotLike(String value) {
            addCriterion("PO_sfjdfbl not like", value, "poSfjdfbl");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfblIn(List<String> values) {
            addCriterion("PO_sfjdfbl in", values, "poSfjdfbl");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfblNotIn(List<String> values) {
            addCriterion("PO_sfjdfbl not in", values, "poSfjdfbl");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfblBetween(String value1, String value2) {
            addCriterion("PO_sfjdfbl between", value1, value2, "poSfjdfbl");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfblNotBetween(String value1, String value2) {
            addCriterion("PO_sfjdfbl not between", value1, value2, "poSfjdfbl");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfjeIsNull() {
            addCriterion("PO_sfjdfje is null");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfjeIsNotNull() {
            addCriterion("PO_sfjdfje is not null");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfjeEqualTo(String value) {
            addCriterion("PO_sfjdfje =", value, "poSfjdfje");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfjeNotEqualTo(String value) {
            addCriterion("PO_sfjdfje <>", value, "poSfjdfje");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfjeGreaterThan(String value) {
            addCriterion("PO_sfjdfje >", value, "poSfjdfje");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfjeGreaterThanOrEqualTo(String value) {
            addCriterion("PO_sfjdfje >=", value, "poSfjdfje");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfjeLessThan(String value) {
            addCriterion("PO_sfjdfje <", value, "poSfjdfje");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfjeLessThanOrEqualTo(String value) {
            addCriterion("PO_sfjdfje <=", value, "poSfjdfje");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfjeLike(String value) {
            addCriterion("PO_sfjdfje like", value, "poSfjdfje");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfjeNotLike(String value) {
            addCriterion("PO_sfjdfje not like", value, "poSfjdfje");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfjeIn(List<String> values) {
            addCriterion("PO_sfjdfje in", values, "poSfjdfje");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfjeNotIn(List<String> values) {
            addCriterion("PO_sfjdfje not in", values, "poSfjdfje");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfjeBetween(String value1, String value2) {
            addCriterion("PO_sfjdfje between", value1, value2, "poSfjdfje");
            return (Criteria) this;
        }

        public Criteria andPoSfjdfjeNotBetween(String value1, String value2) {
            addCriterion("PO_sfjdfje not between", value1, value2, "poSfjdfje");
            return (Criteria) this;
        }

        public Criteria andF41IsNull() {
            addCriterion("F41 is null");
            return (Criteria) this;
        }

        public Criteria andF41IsNotNull() {
            addCriterion("F41 is not null");
            return (Criteria) this;
        }

        public Criteria andF41EqualTo(String value) {
            addCriterion("F41 =", value, "f41");
            return (Criteria) this;
        }

        public Criteria andF41NotEqualTo(String value) {
            addCriterion("F41 <>", value, "f41");
            return (Criteria) this;
        }

        public Criteria andF41GreaterThan(String value) {
            addCriterion("F41 >", value, "f41");
            return (Criteria) this;
        }

        public Criteria andF41GreaterThanOrEqualTo(String value) {
            addCriterion("F41 >=", value, "f41");
            return (Criteria) this;
        }

        public Criteria andF41LessThan(String value) {
            addCriterion("F41 <", value, "f41");
            return (Criteria) this;
        }

        public Criteria andF41LessThanOrEqualTo(String value) {
            addCriterion("F41 <=", value, "f41");
            return (Criteria) this;
        }

        public Criteria andF41Like(String value) {
            addCriterion("F41 like", value, "f41");
            return (Criteria) this;
        }

        public Criteria andF41NotLike(String value) {
            addCriterion("F41 not like", value, "f41");
            return (Criteria) this;
        }

        public Criteria andF41In(List<String> values) {
            addCriterion("F41 in", values, "f41");
            return (Criteria) this;
        }

        public Criteria andF41NotIn(List<String> values) {
            addCriterion("F41 not in", values, "f41");
            return (Criteria) this;
        }

        public Criteria andF41Between(String value1, String value2) {
            addCriterion("F41 between", value1, value2, "f41");
            return (Criteria) this;
        }

        public Criteria andF41NotBetween(String value1, String value2) {
            addCriterion("F41 not between", value1, value2, "f41");
            return (Criteria) this;
        }

        public Criteria andF42IsNull() {
            addCriterion("F42 is null");
            return (Criteria) this;
        }

        public Criteria andF42IsNotNull() {
            addCriterion("F42 is not null");
            return (Criteria) this;
        }

        public Criteria andF42EqualTo(String value) {
            addCriterion("F42 =", value, "f42");
            return (Criteria) this;
        }

        public Criteria andF42NotEqualTo(String value) {
            addCriterion("F42 <>", value, "f42");
            return (Criteria) this;
        }

        public Criteria andF42GreaterThan(String value) {
            addCriterion("F42 >", value, "f42");
            return (Criteria) this;
        }

        public Criteria andF42GreaterThanOrEqualTo(String value) {
            addCriterion("F42 >=", value, "f42");
            return (Criteria) this;
        }

        public Criteria andF42LessThan(String value) {
            addCriterion("F42 <", value, "f42");
            return (Criteria) this;
        }

        public Criteria andF42LessThanOrEqualTo(String value) {
            addCriterion("F42 <=", value, "f42");
            return (Criteria) this;
        }

        public Criteria andF42Like(String value) {
            addCriterion("F42 like", value, "f42");
            return (Criteria) this;
        }

        public Criteria andF42NotLike(String value) {
            addCriterion("F42 not like", value, "f42");
            return (Criteria) this;
        }

        public Criteria andF42In(List<String> values) {
            addCriterion("F42 in", values, "f42");
            return (Criteria) this;
        }

        public Criteria andF42NotIn(List<String> values) {
            addCriterion("F42 not in", values, "f42");
            return (Criteria) this;
        }

        public Criteria andF42Between(String value1, String value2) {
            addCriterion("F42 between", value1, value2, "f42");
            return (Criteria) this;
        }

        public Criteria andF42NotBetween(String value1, String value2) {
            addCriterion("F42 not between", value1, value2, "f42");
            return (Criteria) this;
        }

        public Criteria andPoXgsfhjIsNull() {
            addCriterion("PO_xgsfhj is null");
            return (Criteria) this;
        }

        public Criteria andPoXgsfhjIsNotNull() {
            addCriterion("PO_xgsfhj is not null");
            return (Criteria) this;
        }

        public Criteria andPoXgsfhjEqualTo(String value) {
            addCriterion("PO_xgsfhj =", value, "poXgsfhj");
            return (Criteria) this;
        }

        public Criteria andPoXgsfhjNotEqualTo(String value) {
            addCriterion("PO_xgsfhj <>", value, "poXgsfhj");
            return (Criteria) this;
        }

        public Criteria andPoXgsfhjGreaterThan(String value) {
            addCriterion("PO_xgsfhj >", value, "poXgsfhj");
            return (Criteria) this;
        }

        public Criteria andPoXgsfhjGreaterThanOrEqualTo(String value) {
            addCriterion("PO_xgsfhj >=", value, "poXgsfhj");
            return (Criteria) this;
        }

        public Criteria andPoXgsfhjLessThan(String value) {
            addCriterion("PO_xgsfhj <", value, "poXgsfhj");
            return (Criteria) this;
        }

        public Criteria andPoXgsfhjLessThanOrEqualTo(String value) {
            addCriterion("PO_xgsfhj <=", value, "poXgsfhj");
            return (Criteria) this;
        }

        public Criteria andPoXgsfhjLike(String value) {
            addCriterion("PO_xgsfhj like", value, "poXgsfhj");
            return (Criteria) this;
        }

        public Criteria andPoXgsfhjNotLike(String value) {
            addCriterion("PO_xgsfhj not like", value, "poXgsfhj");
            return (Criteria) this;
        }

        public Criteria andPoXgsfhjIn(List<String> values) {
            addCriterion("PO_xgsfhj in", values, "poXgsfhj");
            return (Criteria) this;
        }

        public Criteria andPoXgsfhjNotIn(List<String> values) {
            addCriterion("PO_xgsfhj not in", values, "poXgsfhj");
            return (Criteria) this;
        }

        public Criteria andPoXgsfhjBetween(String value1, String value2) {
            addCriterion("PO_xgsfhj between", value1, value2, "poXgsfhj");
            return (Criteria) this;
        }

        public Criteria andPoXgsfhjNotBetween(String value1, String value2) {
            addCriterion("PO_xgsfhj not between", value1, value2, "poXgsfhj");
            return (Criteria) this;
        }

        public Criteria andF44IsNull() {
            addCriterion("F44 is null");
            return (Criteria) this;
        }

        public Criteria andF44IsNotNull() {
            addCriterion("F44 is not null");
            return (Criteria) this;
        }

        public Criteria andF44EqualTo(String value) {
            addCriterion("F44 =", value, "f44");
            return (Criteria) this;
        }

        public Criteria andF44NotEqualTo(String value) {
            addCriterion("F44 <>", value, "f44");
            return (Criteria) this;
        }

        public Criteria andF44GreaterThan(String value) {
            addCriterion("F44 >", value, "f44");
            return (Criteria) this;
        }

        public Criteria andF44GreaterThanOrEqualTo(String value) {
            addCriterion("F44 >=", value, "f44");
            return (Criteria) this;
        }

        public Criteria andF44LessThan(String value) {
            addCriterion("F44 <", value, "f44");
            return (Criteria) this;
        }

        public Criteria andF44LessThanOrEqualTo(String value) {
            addCriterion("F44 <=", value, "f44");
            return (Criteria) this;
        }

        public Criteria andF44Like(String value) {
            addCriterion("F44 like", value, "f44");
            return (Criteria) this;
        }

        public Criteria andF44NotLike(String value) {
            addCriterion("F44 not like", value, "f44");
            return (Criteria) this;
        }

        public Criteria andF44In(List<String> values) {
            addCriterion("F44 in", values, "f44");
            return (Criteria) this;
        }

        public Criteria andF44NotIn(List<String> values) {
            addCriterion("F44 not in", values, "f44");
            return (Criteria) this;
        }

        public Criteria andF44Between(String value1, String value2) {
            addCriterion("F44 between", value1, value2, "f44");
            return (Criteria) this;
        }

        public Criteria andF44NotBetween(String value1, String value2) {
            addCriterion("F44 not between", value1, value2, "f44");
            return (Criteria) this;
        }

        public Criteria andF45IsNull() {
            addCriterion("F45 is null");
            return (Criteria) this;
        }

        public Criteria andF45IsNotNull() {
            addCriterion("F45 is not null");
            return (Criteria) this;
        }

        public Criteria andF45EqualTo(String value) {
            addCriterion("F45 =", value, "f45");
            return (Criteria) this;
        }

        public Criteria andF45NotEqualTo(String value) {
            addCriterion("F45 <>", value, "f45");
            return (Criteria) this;
        }

        public Criteria andF45GreaterThan(String value) {
            addCriterion("F45 >", value, "f45");
            return (Criteria) this;
        }

        public Criteria andF45GreaterThanOrEqualTo(String value) {
            addCriterion("F45 >=", value, "f45");
            return (Criteria) this;
        }

        public Criteria andF45LessThan(String value) {
            addCriterion("F45 <", value, "f45");
            return (Criteria) this;
        }

        public Criteria andF45LessThanOrEqualTo(String value) {
            addCriterion("F45 <=", value, "f45");
            return (Criteria) this;
        }

        public Criteria andF45Like(String value) {
            addCriterion("F45 like", value, "f45");
            return (Criteria) this;
        }

        public Criteria andF45NotLike(String value) {
            addCriterion("F45 not like", value, "f45");
            return (Criteria) this;
        }

        public Criteria andF45In(List<String> values) {
            addCriterion("F45 in", values, "f45");
            return (Criteria) this;
        }

        public Criteria andF45NotIn(List<String> values) {
            addCriterion("F45 not in", values, "f45");
            return (Criteria) this;
        }

        public Criteria andF45Between(String value1, String value2) {
            addCriterion("F45 between", value1, value2, "f45");
            return (Criteria) this;
        }

        public Criteria andF45NotBetween(String value1, String value2) {
            addCriterion("F45 not between", value1, value2, "f45");
            return (Criteria) this;
        }

        public Criteria andF46IsNull() {
            addCriterion("F46 is null");
            return (Criteria) this;
        }

        public Criteria andF46IsNotNull() {
            addCriterion("F46 is not null");
            return (Criteria) this;
        }

        public Criteria andF46EqualTo(String value) {
            addCriterion("F46 =", value, "f46");
            return (Criteria) this;
        }

        public Criteria andF46NotEqualTo(String value) {
            addCriterion("F46 <>", value, "f46");
            return (Criteria) this;
        }

        public Criteria andF46GreaterThan(String value) {
            addCriterion("F46 >", value, "f46");
            return (Criteria) this;
        }

        public Criteria andF46GreaterThanOrEqualTo(String value) {
            addCriterion("F46 >=", value, "f46");
            return (Criteria) this;
        }

        public Criteria andF46LessThan(String value) {
            addCriterion("F46 <", value, "f46");
            return (Criteria) this;
        }

        public Criteria andF46LessThanOrEqualTo(String value) {
            addCriterion("F46 <=", value, "f46");
            return (Criteria) this;
        }

        public Criteria andF46Like(String value) {
            addCriterion("F46 like", value, "f46");
            return (Criteria) this;
        }

        public Criteria andF46NotLike(String value) {
            addCriterion("F46 not like", value, "f46");
            return (Criteria) this;
        }

        public Criteria andF46In(List<String> values) {
            addCriterion("F46 in", values, "f46");
            return (Criteria) this;
        }

        public Criteria andF46NotIn(List<String> values) {
            addCriterion("F46 not in", values, "f46");
            return (Criteria) this;
        }

        public Criteria andF46Between(String value1, String value2) {
            addCriterion("F46 between", value1, value2, "f46");
            return (Criteria) this;
        }

        public Criteria andF46NotBetween(String value1, String value2) {
            addCriterion("F46 not between", value1, value2, "f46");
            return (Criteria) this;
        }

        public Criteria andF47IsNull() {
            addCriterion("F47 is null");
            return (Criteria) this;
        }

        public Criteria andF47IsNotNull() {
            addCriterion("F47 is not null");
            return (Criteria) this;
        }

        public Criteria andF47EqualTo(String value) {
            addCriterion("F47 =", value, "f47");
            return (Criteria) this;
        }

        public Criteria andF47NotEqualTo(String value) {
            addCriterion("F47 <>", value, "f47");
            return (Criteria) this;
        }

        public Criteria andF47GreaterThan(String value) {
            addCriterion("F47 >", value, "f47");
            return (Criteria) this;
        }

        public Criteria andF47GreaterThanOrEqualTo(String value) {
            addCriterion("F47 >=", value, "f47");
            return (Criteria) this;
        }

        public Criteria andF47LessThan(String value) {
            addCriterion("F47 <", value, "f47");
            return (Criteria) this;
        }

        public Criteria andF47LessThanOrEqualTo(String value) {
            addCriterion("F47 <=", value, "f47");
            return (Criteria) this;
        }

        public Criteria andF47Like(String value) {
            addCriterion("F47 like", value, "f47");
            return (Criteria) this;
        }

        public Criteria andF47NotLike(String value) {
            addCriterion("F47 not like", value, "f47");
            return (Criteria) this;
        }

        public Criteria andF47In(List<String> values) {
            addCriterion("F47 in", values, "f47");
            return (Criteria) this;
        }

        public Criteria andF47NotIn(List<String> values) {
            addCriterion("F47 not in", values, "f47");
            return (Criteria) this;
        }

        public Criteria andF47Between(String value1, String value2) {
            addCriterion("F47 between", value1, value2, "f47");
            return (Criteria) this;
        }

        public Criteria andF47NotBetween(String value1, String value2) {
            addCriterion("F47 not between", value1, value2, "f47");
            return (Criteria) this;
        }

        public Criteria andF48IsNull() {
            addCriterion("F48 is null");
            return (Criteria) this;
        }

        public Criteria andF48IsNotNull() {
            addCriterion("F48 is not null");
            return (Criteria) this;
        }

        public Criteria andF48EqualTo(String value) {
            addCriterion("F48 =", value, "f48");
            return (Criteria) this;
        }

        public Criteria andF48NotEqualTo(String value) {
            addCriterion("F48 <>", value, "f48");
            return (Criteria) this;
        }

        public Criteria andF48GreaterThan(String value) {
            addCriterion("F48 >", value, "f48");
            return (Criteria) this;
        }

        public Criteria andF48GreaterThanOrEqualTo(String value) {
            addCriterion("F48 >=", value, "f48");
            return (Criteria) this;
        }

        public Criteria andF48LessThan(String value) {
            addCriterion("F48 <", value, "f48");
            return (Criteria) this;
        }

        public Criteria andF48LessThanOrEqualTo(String value) {
            addCriterion("F48 <=", value, "f48");
            return (Criteria) this;
        }

        public Criteria andF48Like(String value) {
            addCriterion("F48 like", value, "f48");
            return (Criteria) this;
        }

        public Criteria andF48NotLike(String value) {
            addCriterion("F48 not like", value, "f48");
            return (Criteria) this;
        }

        public Criteria andF48In(List<String> values) {
            addCriterion("F48 in", values, "f48");
            return (Criteria) this;
        }

        public Criteria andF48NotIn(List<String> values) {
            addCriterion("F48 not in", values, "f48");
            return (Criteria) this;
        }

        public Criteria andF48Between(String value1, String value2) {
            addCriterion("F48 between", value1, value2, "f48");
            return (Criteria) this;
        }

        public Criteria andF48NotBetween(String value1, String value2) {
            addCriterion("F48 not between", value1, value2, "f48");
            return (Criteria) this;
        }

        public Criteria andF49IsNull() {
            addCriterion("F49 is null");
            return (Criteria) this;
        }

        public Criteria andF49IsNotNull() {
            addCriterion("F49 is not null");
            return (Criteria) this;
        }

        public Criteria andF49EqualTo(String value) {
            addCriterion("F49 =", value, "f49");
            return (Criteria) this;
        }

        public Criteria andF49NotEqualTo(String value) {
            addCriterion("F49 <>", value, "f49");
            return (Criteria) this;
        }

        public Criteria andF49GreaterThan(String value) {
            addCriterion("F49 >", value, "f49");
            return (Criteria) this;
        }

        public Criteria andF49GreaterThanOrEqualTo(String value) {
            addCriterion("F49 >=", value, "f49");
            return (Criteria) this;
        }

        public Criteria andF49LessThan(String value) {
            addCriterion("F49 <", value, "f49");
            return (Criteria) this;
        }

        public Criteria andF49LessThanOrEqualTo(String value) {
            addCriterion("F49 <=", value, "f49");
            return (Criteria) this;
        }

        public Criteria andF49Like(String value) {
            addCriterion("F49 like", value, "f49");
            return (Criteria) this;
        }

        public Criteria andF49NotLike(String value) {
            addCriterion("F49 not like", value, "f49");
            return (Criteria) this;
        }

        public Criteria andF49In(List<String> values) {
            addCriterion("F49 in", values, "f49");
            return (Criteria) this;
        }

        public Criteria andF49NotIn(List<String> values) {
            addCriterion("F49 not in", values, "f49");
            return (Criteria) this;
        }

        public Criteria andF49Between(String value1, String value2) {
            addCriterion("F49 between", value1, value2, "f49");
            return (Criteria) this;
        }

        public Criteria andF49NotBetween(String value1, String value2) {
            addCriterion("F49 not between", value1, value2, "f49");
            return (Criteria) this;
        }

        public Criteria andF50IsNull() {
            addCriterion("F50 is null");
            return (Criteria) this;
        }

        public Criteria andF50IsNotNull() {
            addCriterion("F50 is not null");
            return (Criteria) this;
        }

        public Criteria andF50EqualTo(String value) {
            addCriterion("F50 =", value, "f50");
            return (Criteria) this;
        }

        public Criteria andF50NotEqualTo(String value) {
            addCriterion("F50 <>", value, "f50");
            return (Criteria) this;
        }

        public Criteria andF50GreaterThan(String value) {
            addCriterion("F50 >", value, "f50");
            return (Criteria) this;
        }

        public Criteria andF50GreaterThanOrEqualTo(String value) {
            addCriterion("F50 >=", value, "f50");
            return (Criteria) this;
        }

        public Criteria andF50LessThan(String value) {
            addCriterion("F50 <", value, "f50");
            return (Criteria) this;
        }

        public Criteria andF50LessThanOrEqualTo(String value) {
            addCriterion("F50 <=", value, "f50");
            return (Criteria) this;
        }

        public Criteria andF50Like(String value) {
            addCriterion("F50 like", value, "f50");
            return (Criteria) this;
        }

        public Criteria andF50NotLike(String value) {
            addCriterion("F50 not like", value, "f50");
            return (Criteria) this;
        }

        public Criteria andF50In(List<String> values) {
            addCriterion("F50 in", values, "f50");
            return (Criteria) this;
        }

        public Criteria andF50NotIn(List<String> values) {
            addCriterion("F50 not in", values, "f50");
            return (Criteria) this;
        }

        public Criteria andF50Between(String value1, String value2) {
            addCriterion("F50 between", value1, value2, "f50");
            return (Criteria) this;
        }

        public Criteria andF50NotBetween(String value1, String value2) {
            addCriterion("F50 not between", value1, value2, "f50");
            return (Criteria) this;
        }

        public Criteria andF51IsNull() {
            addCriterion("F51 is null");
            return (Criteria) this;
        }

        public Criteria andF51IsNotNull() {
            addCriterion("F51 is not null");
            return (Criteria) this;
        }

        public Criteria andF51EqualTo(String value) {
            addCriterion("F51 =", value, "f51");
            return (Criteria) this;
        }

        public Criteria andF51NotEqualTo(String value) {
            addCriterion("F51 <>", value, "f51");
            return (Criteria) this;
        }

        public Criteria andF51GreaterThan(String value) {
            addCriterion("F51 >", value, "f51");
            return (Criteria) this;
        }

        public Criteria andF51GreaterThanOrEqualTo(String value) {
            addCriterion("F51 >=", value, "f51");
            return (Criteria) this;
        }

        public Criteria andF51LessThan(String value) {
            addCriterion("F51 <", value, "f51");
            return (Criteria) this;
        }

        public Criteria andF51LessThanOrEqualTo(String value) {
            addCriterion("F51 <=", value, "f51");
            return (Criteria) this;
        }

        public Criteria andF51Like(String value) {
            addCriterion("F51 like", value, "f51");
            return (Criteria) this;
        }

        public Criteria andF51NotLike(String value) {
            addCriterion("F51 not like", value, "f51");
            return (Criteria) this;
        }

        public Criteria andF51In(List<String> values) {
            addCriterion("F51 in", values, "f51");
            return (Criteria) this;
        }

        public Criteria andF51NotIn(List<String> values) {
            addCriterion("F51 not in", values, "f51");
            return (Criteria) this;
        }

        public Criteria andF51Between(String value1, String value2) {
            addCriterion("F51 between", value1, value2, "f51");
            return (Criteria) this;
        }

        public Criteria andF51NotBetween(String value1, String value2) {
            addCriterion("F51 not between", value1, value2, "f51");
            return (Criteria) this;
        }

        public Criteria andF52IsNull() {
            addCriterion("F52 is null");
            return (Criteria) this;
        }

        public Criteria andF52IsNotNull() {
            addCriterion("F52 is not null");
            return (Criteria) this;
        }

        public Criteria andF52EqualTo(String value) {
            addCriterion("F52 =", value, "f52");
            return (Criteria) this;
        }

        public Criteria andF52NotEqualTo(String value) {
            addCriterion("F52 <>", value, "f52");
            return (Criteria) this;
        }

        public Criteria andF52GreaterThan(String value) {
            addCriterion("F52 >", value, "f52");
            return (Criteria) this;
        }

        public Criteria andF52GreaterThanOrEqualTo(String value) {
            addCriterion("F52 >=", value, "f52");
            return (Criteria) this;
        }

        public Criteria andF52LessThan(String value) {
            addCriterion("F52 <", value, "f52");
            return (Criteria) this;
        }

        public Criteria andF52LessThanOrEqualTo(String value) {
            addCriterion("F52 <=", value, "f52");
            return (Criteria) this;
        }

        public Criteria andF52Like(String value) {
            addCriterion("F52 like", value, "f52");
            return (Criteria) this;
        }

        public Criteria andF52NotLike(String value) {
            addCriterion("F52 not like", value, "f52");
            return (Criteria) this;
        }

        public Criteria andF52In(List<String> values) {
            addCriterion("F52 in", values, "f52");
            return (Criteria) this;
        }

        public Criteria andF52NotIn(List<String> values) {
            addCriterion("F52 not in", values, "f52");
            return (Criteria) this;
        }

        public Criteria andF52Between(String value1, String value2) {
            addCriterion("F52 between", value1, value2, "f52");
            return (Criteria) this;
        }

        public Criteria andF52NotBetween(String value1, String value2) {
            addCriterion("F52 not between", value1, value2, "f52");
            return (Criteria) this;
        }

        public Criteria andF53IsNull() {
            addCriterion("F53 is null");
            return (Criteria) this;
        }

        public Criteria andF53IsNotNull() {
            addCriterion("F53 is not null");
            return (Criteria) this;
        }

        public Criteria andF53EqualTo(String value) {
            addCriterion("F53 =", value, "f53");
            return (Criteria) this;
        }

        public Criteria andF53NotEqualTo(String value) {
            addCriterion("F53 <>", value, "f53");
            return (Criteria) this;
        }

        public Criteria andF53GreaterThan(String value) {
            addCriterion("F53 >", value, "f53");
            return (Criteria) this;
        }

        public Criteria andF53GreaterThanOrEqualTo(String value) {
            addCriterion("F53 >=", value, "f53");
            return (Criteria) this;
        }

        public Criteria andF53LessThan(String value) {
            addCriterion("F53 <", value, "f53");
            return (Criteria) this;
        }

        public Criteria andF53LessThanOrEqualTo(String value) {
            addCriterion("F53 <=", value, "f53");
            return (Criteria) this;
        }

        public Criteria andF53Like(String value) {
            addCriterion("F53 like", value, "f53");
            return (Criteria) this;
        }

        public Criteria andF53NotLike(String value) {
            addCriterion("F53 not like", value, "f53");
            return (Criteria) this;
        }

        public Criteria andF53In(List<String> values) {
            addCriterion("F53 in", values, "f53");
            return (Criteria) this;
        }

        public Criteria andF53NotIn(List<String> values) {
            addCriterion("F53 not in", values, "f53");
            return (Criteria) this;
        }

        public Criteria andF53Between(String value1, String value2) {
            addCriterion("F53 between", value1, value2, "f53");
            return (Criteria) this;
        }

        public Criteria andF53NotBetween(String value1, String value2) {
            addCriterion("F53 not between", value1, value2, "f53");
            return (Criteria) this;
        }

        public Criteria andF54IsNull() {
            addCriterion("F54 is null");
            return (Criteria) this;
        }

        public Criteria andF54IsNotNull() {
            addCriterion("F54 is not null");
            return (Criteria) this;
        }

        public Criteria andF54EqualTo(String value) {
            addCriterion("F54 =", value, "f54");
            return (Criteria) this;
        }

        public Criteria andF54NotEqualTo(String value) {
            addCriterion("F54 <>", value, "f54");
            return (Criteria) this;
        }

        public Criteria andF54GreaterThan(String value) {
            addCriterion("F54 >", value, "f54");
            return (Criteria) this;
        }

        public Criteria andF54GreaterThanOrEqualTo(String value) {
            addCriterion("F54 >=", value, "f54");
            return (Criteria) this;
        }

        public Criteria andF54LessThan(String value) {
            addCriterion("F54 <", value, "f54");
            return (Criteria) this;
        }

        public Criteria andF54LessThanOrEqualTo(String value) {
            addCriterion("F54 <=", value, "f54");
            return (Criteria) this;
        }

        public Criteria andF54Like(String value) {
            addCriterion("F54 like", value, "f54");
            return (Criteria) this;
        }

        public Criteria andF54NotLike(String value) {
            addCriterion("F54 not like", value, "f54");
            return (Criteria) this;
        }

        public Criteria andF54In(List<String> values) {
            addCriterion("F54 in", values, "f54");
            return (Criteria) this;
        }

        public Criteria andF54NotIn(List<String> values) {
            addCriterion("F54 not in", values, "f54");
            return (Criteria) this;
        }

        public Criteria andF54Between(String value1, String value2) {
            addCriterion("F54 between", value1, value2, "f54");
            return (Criteria) this;
        }

        public Criteria andF54NotBetween(String value1, String value2) {
            addCriterion("F54 not between", value1, value2, "f54");
            return (Criteria) this;
        }

        public Criteria andF55IsNull() {
            addCriterion("F55 is null");
            return (Criteria) this;
        }

        public Criteria andF55IsNotNull() {
            addCriterion("F55 is not null");
            return (Criteria) this;
        }

        public Criteria andF55EqualTo(String value) {
            addCriterion("F55 =", value, "f55");
            return (Criteria) this;
        }

        public Criteria andF55NotEqualTo(String value) {
            addCriterion("F55 <>", value, "f55");
            return (Criteria) this;
        }

        public Criteria andF55GreaterThan(String value) {
            addCriterion("F55 >", value, "f55");
            return (Criteria) this;
        }

        public Criteria andF55GreaterThanOrEqualTo(String value) {
            addCriterion("F55 >=", value, "f55");
            return (Criteria) this;
        }

        public Criteria andF55LessThan(String value) {
            addCriterion("F55 <", value, "f55");
            return (Criteria) this;
        }

        public Criteria andF55LessThanOrEqualTo(String value) {
            addCriterion("F55 <=", value, "f55");
            return (Criteria) this;
        }

        public Criteria andF55Like(String value) {
            addCriterion("F55 like", value, "f55");
            return (Criteria) this;
        }

        public Criteria andF55NotLike(String value) {
            addCriterion("F55 not like", value, "f55");
            return (Criteria) this;
        }

        public Criteria andF55In(List<String> values) {
            addCriterion("F55 in", values, "f55");
            return (Criteria) this;
        }

        public Criteria andF55NotIn(List<String> values) {
            addCriterion("F55 not in", values, "f55");
            return (Criteria) this;
        }

        public Criteria andF55Between(String value1, String value2) {
            addCriterion("F55 between", value1, value2, "f55");
            return (Criteria) this;
        }

        public Criteria andF55NotBetween(String value1, String value2) {
            addCriterion("F55 not between", value1, value2, "f55");
            return (Criteria) this;
        }

        public Criteria andF56IsNull() {
            addCriterion("F56 is null");
            return (Criteria) this;
        }

        public Criteria andF56IsNotNull() {
            addCriterion("F56 is not null");
            return (Criteria) this;
        }

        public Criteria andF56EqualTo(String value) {
            addCriterion("F56 =", value, "f56");
            return (Criteria) this;
        }

        public Criteria andF56NotEqualTo(String value) {
            addCriterion("F56 <>", value, "f56");
            return (Criteria) this;
        }

        public Criteria andF56GreaterThan(String value) {
            addCriterion("F56 >", value, "f56");
            return (Criteria) this;
        }

        public Criteria andF56GreaterThanOrEqualTo(String value) {
            addCriterion("F56 >=", value, "f56");
            return (Criteria) this;
        }

        public Criteria andF56LessThan(String value) {
            addCriterion("F56 <", value, "f56");
            return (Criteria) this;
        }

        public Criteria andF56LessThanOrEqualTo(String value) {
            addCriterion("F56 <=", value, "f56");
            return (Criteria) this;
        }

        public Criteria andF56Like(String value) {
            addCriterion("F56 like", value, "f56");
            return (Criteria) this;
        }

        public Criteria andF56NotLike(String value) {
            addCriterion("F56 not like", value, "f56");
            return (Criteria) this;
        }

        public Criteria andF56In(List<String> values) {
            addCriterion("F56 in", values, "f56");
            return (Criteria) this;
        }

        public Criteria andF56NotIn(List<String> values) {
            addCriterion("F56 not in", values, "f56");
            return (Criteria) this;
        }

        public Criteria andF56Between(String value1, String value2) {
            addCriterion("F56 between", value1, value2, "f56");
            return (Criteria) this;
        }

        public Criteria andF56NotBetween(String value1, String value2) {
            addCriterion("F56 not between", value1, value2, "f56");
            return (Criteria) this;
        }

        public Criteria andPoBxjsrIsNull() {
            addCriterion("PO_bxjsr is null");
            return (Criteria) this;
        }

        public Criteria andPoBxjsrIsNotNull() {
            addCriterion("PO_bxjsr is not null");
            return (Criteria) this;
        }

        public Criteria andPoBxjsrEqualTo(String value) {
            addCriterion("PO_bxjsr =", value, "poBxjsr");
            return (Criteria) this;
        }

        public Criteria andPoBxjsrNotEqualTo(String value) {
            addCriterion("PO_bxjsr <>", value, "poBxjsr");
            return (Criteria) this;
        }

        public Criteria andPoBxjsrGreaterThan(String value) {
            addCriterion("PO_bxjsr >", value, "poBxjsr");
            return (Criteria) this;
        }

        public Criteria andPoBxjsrGreaterThanOrEqualTo(String value) {
            addCriterion("PO_bxjsr >=", value, "poBxjsr");
            return (Criteria) this;
        }

        public Criteria andPoBxjsrLessThan(String value) {
            addCriterion("PO_bxjsr <", value, "poBxjsr");
            return (Criteria) this;
        }

        public Criteria andPoBxjsrLessThanOrEqualTo(String value) {
            addCriterion("PO_bxjsr <=", value, "poBxjsr");
            return (Criteria) this;
        }

        public Criteria andPoBxjsrLike(String value) {
            addCriterion("PO_bxjsr like", value, "poBxjsr");
            return (Criteria) this;
        }

        public Criteria andPoBxjsrNotLike(String value) {
            addCriterion("PO_bxjsr not like", value, "poBxjsr");
            return (Criteria) this;
        }

        public Criteria andPoBxjsrIn(List<String> values) {
            addCriterion("PO_bxjsr in", values, "poBxjsr");
            return (Criteria) this;
        }

        public Criteria andPoBxjsrNotIn(List<String> values) {
            addCriterion("PO_bxjsr not in", values, "poBxjsr");
            return (Criteria) this;
        }

        public Criteria andPoBxjsrBetween(String value1, String value2) {
            addCriterion("PO_bxjsr between", value1, value2, "poBxjsr");
            return (Criteria) this;
        }

        public Criteria andPoBxjsrNotBetween(String value1, String value2) {
            addCriterion("PO_bxjsr not between", value1, value2, "poBxjsr");
            return (Criteria) this;
        }

        public Criteria andF58IsNull() {
            addCriterion("F58 is null");
            return (Criteria) this;
        }

        public Criteria andF58IsNotNull() {
            addCriterion("F58 is not null");
            return (Criteria) this;
        }

        public Criteria andF58EqualTo(String value) {
            addCriterion("F58 =", value, "f58");
            return (Criteria) this;
        }

        public Criteria andF58NotEqualTo(String value) {
            addCriterion("F58 <>", value, "f58");
            return (Criteria) this;
        }

        public Criteria andF58GreaterThan(String value) {
            addCriterion("F58 >", value, "f58");
            return (Criteria) this;
        }

        public Criteria andF58GreaterThanOrEqualTo(String value) {
            addCriterion("F58 >=", value, "f58");
            return (Criteria) this;
        }

        public Criteria andF58LessThan(String value) {
            addCriterion("F58 <", value, "f58");
            return (Criteria) this;
        }

        public Criteria andF58LessThanOrEqualTo(String value) {
            addCriterion("F58 <=", value, "f58");
            return (Criteria) this;
        }

        public Criteria andF58Like(String value) {
            addCriterion("F58 like", value, "f58");
            return (Criteria) this;
        }

        public Criteria andF58NotLike(String value) {
            addCriterion("F58 not like", value, "f58");
            return (Criteria) this;
        }

        public Criteria andF58In(List<String> values) {
            addCriterion("F58 in", values, "f58");
            return (Criteria) this;
        }

        public Criteria andF58NotIn(List<String> values) {
            addCriterion("F58 not in", values, "f58");
            return (Criteria) this;
        }

        public Criteria andF58Between(String value1, String value2) {
            addCriterion("F58 between", value1, value2, "f58");
            return (Criteria) this;
        }

        public Criteria andF58NotBetween(String value1, String value2) {
            addCriterion("F58 not between", value1, value2, "f58");
            return (Criteria) this;
        }

        public Criteria andF59IsNull() {
            addCriterion("F59 is null");
            return (Criteria) this;
        }

        public Criteria andF59IsNotNull() {
            addCriterion("F59 is not null");
            return (Criteria) this;
        }

        public Criteria andF59EqualTo(String value) {
            addCriterion("F59 =", value, "f59");
            return (Criteria) this;
        }

        public Criteria andF59NotEqualTo(String value) {
            addCriterion("F59 <>", value, "f59");
            return (Criteria) this;
        }

        public Criteria andF59GreaterThan(String value) {
            addCriterion("F59 >", value, "f59");
            return (Criteria) this;
        }

        public Criteria andF59GreaterThanOrEqualTo(String value) {
            addCriterion("F59 >=", value, "f59");
            return (Criteria) this;
        }

        public Criteria andF59LessThan(String value) {
            addCriterion("F59 <", value, "f59");
            return (Criteria) this;
        }

        public Criteria andF59LessThanOrEqualTo(String value) {
            addCriterion("F59 <=", value, "f59");
            return (Criteria) this;
        }

        public Criteria andF59Like(String value) {
            addCriterion("F59 like", value, "f59");
            return (Criteria) this;
        }

        public Criteria andF59NotLike(String value) {
            addCriterion("F59 not like", value, "f59");
            return (Criteria) this;
        }

        public Criteria andF59In(List<String> values) {
            addCriterion("F59 in", values, "f59");
            return (Criteria) this;
        }

        public Criteria andF59NotIn(List<String> values) {
            addCriterion("F59 not in", values, "f59");
            return (Criteria) this;
        }

        public Criteria andF59Between(String value1, String value2) {
            addCriterion("F59 between", value1, value2, "f59");
            return (Criteria) this;
        }

        public Criteria andF59NotBetween(String value1, String value2) {
            addCriterion("F59 not between", value1, value2, "f59");
            return (Criteria) this;
        }

        public Criteria andPoBjscblIsNull() {
            addCriterion("PO_bjscbl is null");
            return (Criteria) this;
        }

        public Criteria andPoBjscblIsNotNull() {
            addCriterion("PO_bjscbl is not null");
            return (Criteria) this;
        }

        public Criteria andPoBjscblEqualTo(String value) {
            addCriterion("PO_bjscbl =", value, "poBjscbl");
            return (Criteria) this;
        }

        public Criteria andPoBjscblNotEqualTo(String value) {
            addCriterion("PO_bjscbl <>", value, "poBjscbl");
            return (Criteria) this;
        }

        public Criteria andPoBjscblGreaterThan(String value) {
            addCriterion("PO_bjscbl >", value, "poBjscbl");
            return (Criteria) this;
        }

        public Criteria andPoBjscblGreaterThanOrEqualTo(String value) {
            addCriterion("PO_bjscbl >=", value, "poBjscbl");
            return (Criteria) this;
        }

        public Criteria andPoBjscblLessThan(String value) {
            addCriterion("PO_bjscbl <", value, "poBjscbl");
            return (Criteria) this;
        }

        public Criteria andPoBjscblLessThanOrEqualTo(String value) {
            addCriterion("PO_bjscbl <=", value, "poBjscbl");
            return (Criteria) this;
        }

        public Criteria andPoBjscblLike(String value) {
            addCriterion("PO_bjscbl like", value, "poBjscbl");
            return (Criteria) this;
        }

        public Criteria andPoBjscblNotLike(String value) {
            addCriterion("PO_bjscbl not like", value, "poBjscbl");
            return (Criteria) this;
        }

        public Criteria andPoBjscblIn(List<String> values) {
            addCriterion("PO_bjscbl in", values, "poBjscbl");
            return (Criteria) this;
        }

        public Criteria andPoBjscblNotIn(List<String> values) {
            addCriterion("PO_bjscbl not in", values, "poBjscbl");
            return (Criteria) this;
        }

        public Criteria andPoBjscblBetween(String value1, String value2) {
            addCriterion("PO_bjscbl between", value1, value2, "poBjscbl");
            return (Criteria) this;
        }

        public Criteria andPoBjscblNotBetween(String value1, String value2) {
            addCriterion("PO_bjscbl not between", value1, value2, "poBjscbl");
            return (Criteria) this;
        }

        public Criteria andPoBxscblIsNull() {
            addCriterion("PO_bxscbl is null");
            return (Criteria) this;
        }

        public Criteria andPoBxscblIsNotNull() {
            addCriterion("PO_bxscbl is not null");
            return (Criteria) this;
        }

        public Criteria andPoBxscblEqualTo(String value) {
            addCriterion("PO_bxscbl =", value, "poBxscbl");
            return (Criteria) this;
        }

        public Criteria andPoBxscblNotEqualTo(String value) {
            addCriterion("PO_bxscbl <>", value, "poBxscbl");
            return (Criteria) this;
        }

        public Criteria andPoBxscblGreaterThan(String value) {
            addCriterion("PO_bxscbl >", value, "poBxscbl");
            return (Criteria) this;
        }

        public Criteria andPoBxscblGreaterThanOrEqualTo(String value) {
            addCriterion("PO_bxscbl >=", value, "poBxscbl");
            return (Criteria) this;
        }

        public Criteria andPoBxscblLessThan(String value) {
            addCriterion("PO_bxscbl <", value, "poBxscbl");
            return (Criteria) this;
        }

        public Criteria andPoBxscblLessThanOrEqualTo(String value) {
            addCriterion("PO_bxscbl <=", value, "poBxscbl");
            return (Criteria) this;
        }

        public Criteria andPoBxscblLike(String value) {
            addCriterion("PO_bxscbl like", value, "poBxscbl");
            return (Criteria) this;
        }

        public Criteria andPoBxscblNotLike(String value) {
            addCriterion("PO_bxscbl not like", value, "poBxscbl");
            return (Criteria) this;
        }

        public Criteria andPoBxscblIn(List<String> values) {
            addCriterion("PO_bxscbl in", values, "poBxscbl");
            return (Criteria) this;
        }

        public Criteria andPoBxscblNotIn(List<String> values) {
            addCriterion("PO_bxscbl not in", values, "poBxscbl");
            return (Criteria) this;
        }

        public Criteria andPoBxscblBetween(String value1, String value2) {
            addCriterion("PO_bxscbl between", value1, value2, "poBxscbl");
            return (Criteria) this;
        }

        public Criteria andPoBxscblNotBetween(String value1, String value2) {
            addCriterion("PO_bxscbl not between", value1, value2, "poBxscbl");
            return (Criteria) this;
        }

        public Criteria andF62IsNull() {
            addCriterion("F62 is null");
            return (Criteria) this;
        }

        public Criteria andF62IsNotNull() {
            addCriterion("F62 is not null");
            return (Criteria) this;
        }

        public Criteria andF62EqualTo(String value) {
            addCriterion("F62 =", value, "f62");
            return (Criteria) this;
        }

        public Criteria andF62NotEqualTo(String value) {
            addCriterion("F62 <>", value, "f62");
            return (Criteria) this;
        }

        public Criteria andF62GreaterThan(String value) {
            addCriterion("F62 >", value, "f62");
            return (Criteria) this;
        }

        public Criteria andF62GreaterThanOrEqualTo(String value) {
            addCriterion("F62 >=", value, "f62");
            return (Criteria) this;
        }

        public Criteria andF62LessThan(String value) {
            addCriterion("F62 <", value, "f62");
            return (Criteria) this;
        }

        public Criteria andF62LessThanOrEqualTo(String value) {
            addCriterion("F62 <=", value, "f62");
            return (Criteria) this;
        }

        public Criteria andF62Like(String value) {
            addCriterion("F62 like", value, "f62");
            return (Criteria) this;
        }

        public Criteria andF62NotLike(String value) {
            addCriterion("F62 not like", value, "f62");
            return (Criteria) this;
        }

        public Criteria andF62In(List<String> values) {
            addCriterion("F62 in", values, "f62");
            return (Criteria) this;
        }

        public Criteria andF62NotIn(List<String> values) {
            addCriterion("F62 not in", values, "f62");
            return (Criteria) this;
        }

        public Criteria andF62Between(String value1, String value2) {
            addCriterion("F62 between", value1, value2, "f62");
            return (Criteria) this;
        }

        public Criteria andF62NotBetween(String value1, String value2) {
            addCriterion("F62 not between", value1, value2, "f62");
            return (Criteria) this;
        }

        public Criteria andF63IsNull() {
            addCriterion("F63 is null");
            return (Criteria) this;
        }

        public Criteria andF63IsNotNull() {
            addCriterion("F63 is not null");
            return (Criteria) this;
        }

        public Criteria andF63EqualTo(String value) {
            addCriterion("F63 =", value, "f63");
            return (Criteria) this;
        }

        public Criteria andF63NotEqualTo(String value) {
            addCriterion("F63 <>", value, "f63");
            return (Criteria) this;
        }

        public Criteria andF63GreaterThan(String value) {
            addCriterion("F63 >", value, "f63");
            return (Criteria) this;
        }

        public Criteria andF63GreaterThanOrEqualTo(String value) {
            addCriterion("F63 >=", value, "f63");
            return (Criteria) this;
        }

        public Criteria andF63LessThan(String value) {
            addCriterion("F63 <", value, "f63");
            return (Criteria) this;
        }

        public Criteria andF63LessThanOrEqualTo(String value) {
            addCriterion("F63 <=", value, "f63");
            return (Criteria) this;
        }

        public Criteria andF63Like(String value) {
            addCriterion("F63 like", value, "f63");
            return (Criteria) this;
        }

        public Criteria andF63NotLike(String value) {
            addCriterion("F63 not like", value, "f63");
            return (Criteria) this;
        }

        public Criteria andF63In(List<String> values) {
            addCriterion("F63 in", values, "f63");
            return (Criteria) this;
        }

        public Criteria andF63NotIn(List<String> values) {
            addCriterion("F63 not in", values, "f63");
            return (Criteria) this;
        }

        public Criteria andF63Between(String value1, String value2) {
            addCriterion("F63 between", value1, value2, "f63");
            return (Criteria) this;
        }

        public Criteria andF63NotBetween(String value1, String value2) {
            addCriterion("F63 not between", value1, value2, "f63");
            return (Criteria) this;
        }

        public Criteria andF64IsNull() {
            addCriterion("F64 is null");
            return (Criteria) this;
        }

        public Criteria andF64IsNotNull() {
            addCriterion("F64 is not null");
            return (Criteria) this;
        }

        public Criteria andF64EqualTo(String value) {
            addCriterion("F64 =", value, "f64");
            return (Criteria) this;
        }

        public Criteria andF64NotEqualTo(String value) {
            addCriterion("F64 <>", value, "f64");
            return (Criteria) this;
        }

        public Criteria andF64GreaterThan(String value) {
            addCriterion("F64 >", value, "f64");
            return (Criteria) this;
        }

        public Criteria andF64GreaterThanOrEqualTo(String value) {
            addCriterion("F64 >=", value, "f64");
            return (Criteria) this;
        }

        public Criteria andF64LessThan(String value) {
            addCriterion("F64 <", value, "f64");
            return (Criteria) this;
        }

        public Criteria andF64LessThanOrEqualTo(String value) {
            addCriterion("F64 <=", value, "f64");
            return (Criteria) this;
        }

        public Criteria andF64Like(String value) {
            addCriterion("F64 like", value, "f64");
            return (Criteria) this;
        }

        public Criteria andF64NotLike(String value) {
            addCriterion("F64 not like", value, "f64");
            return (Criteria) this;
        }

        public Criteria andF64In(List<String> values) {
            addCriterion("F64 in", values, "f64");
            return (Criteria) this;
        }

        public Criteria andF64NotIn(List<String> values) {
            addCriterion("F64 not in", values, "f64");
            return (Criteria) this;
        }

        public Criteria andF64Between(String value1, String value2) {
            addCriterion("F64 between", value1, value2, "f64");
            return (Criteria) this;
        }

        public Criteria andF64NotBetween(String value1, String value2) {
            addCriterion("F64 not between", value1, value2, "f64");
            return (Criteria) this;
        }

        public Criteria andF65IsNull() {
            addCriterion("F65 is null");
            return (Criteria) this;
        }

        public Criteria andF65IsNotNull() {
            addCriterion("F65 is not null");
            return (Criteria) this;
        }

        public Criteria andF65EqualTo(String value) {
            addCriterion("F65 =", value, "f65");
            return (Criteria) this;
        }

        public Criteria andF65NotEqualTo(String value) {
            addCriterion("F65 <>", value, "f65");
            return (Criteria) this;
        }

        public Criteria andF65GreaterThan(String value) {
            addCriterion("F65 >", value, "f65");
            return (Criteria) this;
        }

        public Criteria andF65GreaterThanOrEqualTo(String value) {
            addCriterion("F65 >=", value, "f65");
            return (Criteria) this;
        }

        public Criteria andF65LessThan(String value) {
            addCriterion("F65 <", value, "f65");
            return (Criteria) this;
        }

        public Criteria andF65LessThanOrEqualTo(String value) {
            addCriterion("F65 <=", value, "f65");
            return (Criteria) this;
        }

        public Criteria andF65Like(String value) {
            addCriterion("F65 like", value, "f65");
            return (Criteria) this;
        }

        public Criteria andF65NotLike(String value) {
            addCriterion("F65 not like", value, "f65");
            return (Criteria) this;
        }

        public Criteria andF65In(List<String> values) {
            addCriterion("F65 in", values, "f65");
            return (Criteria) this;
        }

        public Criteria andF65NotIn(List<String> values) {
            addCriterion("F65 not in", values, "f65");
            return (Criteria) this;
        }

        public Criteria andF65Between(String value1, String value2) {
            addCriterion("F65 between", value1, value2, "f65");
            return (Criteria) this;
        }

        public Criteria andF65NotBetween(String value1, String value2) {
            addCriterion("F65 not between", value1, value2, "f65");
            return (Criteria) this;
        }

        public Criteria andF66IsNull() {
            addCriterion("F66 is null");
            return (Criteria) this;
        }

        public Criteria andF66IsNotNull() {
            addCriterion("F66 is not null");
            return (Criteria) this;
        }

        public Criteria andF66EqualTo(String value) {
            addCriterion("F66 =", value, "f66");
            return (Criteria) this;
        }

        public Criteria andF66NotEqualTo(String value) {
            addCriterion("F66 <>", value, "f66");
            return (Criteria) this;
        }

        public Criteria andF66GreaterThan(String value) {
            addCriterion("F66 >", value, "f66");
            return (Criteria) this;
        }

        public Criteria andF66GreaterThanOrEqualTo(String value) {
            addCriterion("F66 >=", value, "f66");
            return (Criteria) this;
        }

        public Criteria andF66LessThan(String value) {
            addCriterion("F66 <", value, "f66");
            return (Criteria) this;
        }

        public Criteria andF66LessThanOrEqualTo(String value) {
            addCriterion("F66 <=", value, "f66");
            return (Criteria) this;
        }

        public Criteria andF66Like(String value) {
            addCriterion("F66 like", value, "f66");
            return (Criteria) this;
        }

        public Criteria andF66NotLike(String value) {
            addCriterion("F66 not like", value, "f66");
            return (Criteria) this;
        }

        public Criteria andF66In(List<String> values) {
            addCriterion("F66 in", values, "f66");
            return (Criteria) this;
        }

        public Criteria andF66NotIn(List<String> values) {
            addCriterion("F66 not in", values, "f66");
            return (Criteria) this;
        }

        public Criteria andF66Between(String value1, String value2) {
            addCriterion("F66 between", value1, value2, "f66");
            return (Criteria) this;
        }

        public Criteria andF66NotBetween(String value1, String value2) {
            addCriterion("F66 not between", value1, value2, "f66");
            return (Criteria) this;
        }

        public Criteria andF67IsNull() {
            addCriterion("F67 is null");
            return (Criteria) this;
        }

        public Criteria andF67IsNotNull() {
            addCriterion("F67 is not null");
            return (Criteria) this;
        }

        public Criteria andF67EqualTo(String value) {
            addCriterion("F67 =", value, "f67");
            return (Criteria) this;
        }

        public Criteria andF67NotEqualTo(String value) {
            addCriterion("F67 <>", value, "f67");
            return (Criteria) this;
        }

        public Criteria andF67GreaterThan(String value) {
            addCriterion("F67 >", value, "f67");
            return (Criteria) this;
        }

        public Criteria andF67GreaterThanOrEqualTo(String value) {
            addCriterion("F67 >=", value, "f67");
            return (Criteria) this;
        }

        public Criteria andF67LessThan(String value) {
            addCriterion("F67 <", value, "f67");
            return (Criteria) this;
        }

        public Criteria andF67LessThanOrEqualTo(String value) {
            addCriterion("F67 <=", value, "f67");
            return (Criteria) this;
        }

        public Criteria andF67Like(String value) {
            addCriterion("F67 like", value, "f67");
            return (Criteria) this;
        }

        public Criteria andF67NotLike(String value) {
            addCriterion("F67 not like", value, "f67");
            return (Criteria) this;
        }

        public Criteria andF67In(List<String> values) {
            addCriterion("F67 in", values, "f67");
            return (Criteria) this;
        }

        public Criteria andF67NotIn(List<String> values) {
            addCriterion("F67 not in", values, "f67");
            return (Criteria) this;
        }

        public Criteria andF67Between(String value1, String value2) {
            addCriterion("F67 between", value1, value2, "f67");
            return (Criteria) this;
        }

        public Criteria andF67NotBetween(String value1, String value2) {
            addCriterion("F67 not between", value1, value2, "f67");
            return (Criteria) this;
        }

        public Criteria andF68IsNull() {
            addCriterion("F68 is null");
            return (Criteria) this;
        }

        public Criteria andF68IsNotNull() {
            addCriterion("F68 is not null");
            return (Criteria) this;
        }

        public Criteria andF68EqualTo(String value) {
            addCriterion("F68 =", value, "f68");
            return (Criteria) this;
        }

        public Criteria andF68NotEqualTo(String value) {
            addCriterion("F68 <>", value, "f68");
            return (Criteria) this;
        }

        public Criteria andF68GreaterThan(String value) {
            addCriterion("F68 >", value, "f68");
            return (Criteria) this;
        }

        public Criteria andF68GreaterThanOrEqualTo(String value) {
            addCriterion("F68 >=", value, "f68");
            return (Criteria) this;
        }

        public Criteria andF68LessThan(String value) {
            addCriterion("F68 <", value, "f68");
            return (Criteria) this;
        }

        public Criteria andF68LessThanOrEqualTo(String value) {
            addCriterion("F68 <=", value, "f68");
            return (Criteria) this;
        }

        public Criteria andF68Like(String value) {
            addCriterion("F68 like", value, "f68");
            return (Criteria) this;
        }

        public Criteria andF68NotLike(String value) {
            addCriterion("F68 not like", value, "f68");
            return (Criteria) this;
        }

        public Criteria andF68In(List<String> values) {
            addCriterion("F68 in", values, "f68");
            return (Criteria) this;
        }

        public Criteria andF68NotIn(List<String> values) {
            addCriterion("F68 not in", values, "f68");
            return (Criteria) this;
        }

        public Criteria andF68Between(String value1, String value2) {
            addCriterion("F68 between", value1, value2, "f68");
            return (Criteria) this;
        }

        public Criteria andF68NotBetween(String value1, String value2) {
            addCriterion("F68 not between", value1, value2, "f68");
            return (Criteria) this;
        }

        public Criteria andF69IsNull() {
            addCriterion("F69 is null");
            return (Criteria) this;
        }

        public Criteria andF69IsNotNull() {
            addCriterion("F69 is not null");
            return (Criteria) this;
        }

        public Criteria andF69EqualTo(String value) {
            addCriterion("F69 =", value, "f69");
            return (Criteria) this;
        }

        public Criteria andF69NotEqualTo(String value) {
            addCriterion("F69 <>", value, "f69");
            return (Criteria) this;
        }

        public Criteria andF69GreaterThan(String value) {
            addCriterion("F69 >", value, "f69");
            return (Criteria) this;
        }

        public Criteria andF69GreaterThanOrEqualTo(String value) {
            addCriterion("F69 >=", value, "f69");
            return (Criteria) this;
        }

        public Criteria andF69LessThan(String value) {
            addCriterion("F69 <", value, "f69");
            return (Criteria) this;
        }

        public Criteria andF69LessThanOrEqualTo(String value) {
            addCriterion("F69 <=", value, "f69");
            return (Criteria) this;
        }

        public Criteria andF69Like(String value) {
            addCriterion("F69 like", value, "f69");
            return (Criteria) this;
        }

        public Criteria andF69NotLike(String value) {
            addCriterion("F69 not like", value, "f69");
            return (Criteria) this;
        }

        public Criteria andF69In(List<String> values) {
            addCriterion("F69 in", values, "f69");
            return (Criteria) this;
        }

        public Criteria andF69NotIn(List<String> values) {
            addCriterion("F69 not in", values, "f69");
            return (Criteria) this;
        }

        public Criteria andF69Between(String value1, String value2) {
            addCriterion("F69 between", value1, value2, "f69");
            return (Criteria) this;
        }

        public Criteria andF69NotBetween(String value1, String value2) {
            addCriterion("F69 not between", value1, value2, "f69");
            return (Criteria) this;
        }

        public Criteria andF70IsNull() {
            addCriterion("F70 is null");
            return (Criteria) this;
        }

        public Criteria andF70IsNotNull() {
            addCriterion("F70 is not null");
            return (Criteria) this;
        }

        public Criteria andF70EqualTo(String value) {
            addCriterion("F70 =", value, "f70");
            return (Criteria) this;
        }

        public Criteria andF70NotEqualTo(String value) {
            addCriterion("F70 <>", value, "f70");
            return (Criteria) this;
        }

        public Criteria andF70GreaterThan(String value) {
            addCriterion("F70 >", value, "f70");
            return (Criteria) this;
        }

        public Criteria andF70GreaterThanOrEqualTo(String value) {
            addCriterion("F70 >=", value, "f70");
            return (Criteria) this;
        }

        public Criteria andF70LessThan(String value) {
            addCriterion("F70 <", value, "f70");
            return (Criteria) this;
        }

        public Criteria andF70LessThanOrEqualTo(String value) {
            addCriterion("F70 <=", value, "f70");
            return (Criteria) this;
        }

        public Criteria andF70Like(String value) {
            addCriterion("F70 like", value, "f70");
            return (Criteria) this;
        }

        public Criteria andF70NotLike(String value) {
            addCriterion("F70 not like", value, "f70");
            return (Criteria) this;
        }

        public Criteria andF70In(List<String> values) {
            addCriterion("F70 in", values, "f70");
            return (Criteria) this;
        }

        public Criteria andF70NotIn(List<String> values) {
            addCriterion("F70 not in", values, "f70");
            return (Criteria) this;
        }

        public Criteria andF70Between(String value1, String value2) {
            addCriterion("F70 between", value1, value2, "f70");
            return (Criteria) this;
        }

        public Criteria andF70NotBetween(String value1, String value2) {
            addCriterion("F70 not between", value1, value2, "f70");
            return (Criteria) this;
        }

        public Criteria andF71IsNull() {
            addCriterion("F71 is null");
            return (Criteria) this;
        }

        public Criteria andF71IsNotNull() {
            addCriterion("F71 is not null");
            return (Criteria) this;
        }

        public Criteria andF71EqualTo(String value) {
            addCriterion("F71 =", value, "f71");
            return (Criteria) this;
        }

        public Criteria andF71NotEqualTo(String value) {
            addCriterion("F71 <>", value, "f71");
            return (Criteria) this;
        }

        public Criteria andF71GreaterThan(String value) {
            addCriterion("F71 >", value, "f71");
            return (Criteria) this;
        }

        public Criteria andF71GreaterThanOrEqualTo(String value) {
            addCriterion("F71 >=", value, "f71");
            return (Criteria) this;
        }

        public Criteria andF71LessThan(String value) {
            addCriterion("F71 <", value, "f71");
            return (Criteria) this;
        }

        public Criteria andF71LessThanOrEqualTo(String value) {
            addCriterion("F71 <=", value, "f71");
            return (Criteria) this;
        }

        public Criteria andF71Like(String value) {
            addCriterion("F71 like", value, "f71");
            return (Criteria) this;
        }

        public Criteria andF71NotLike(String value) {
            addCriterion("F71 not like", value, "f71");
            return (Criteria) this;
        }

        public Criteria andF71In(List<String> values) {
            addCriterion("F71 in", values, "f71");
            return (Criteria) this;
        }

        public Criteria andF71NotIn(List<String> values) {
            addCriterion("F71 not in", values, "f71");
            return (Criteria) this;
        }

        public Criteria andF71Between(String value1, String value2) {
            addCriterion("F71 between", value1, value2, "f71");
            return (Criteria) this;
        }

        public Criteria andF71NotBetween(String value1, String value2) {
            addCriterion("F71 not between", value1, value2, "f71");
            return (Criteria) this;
        }

        public Criteria andF72IsNull() {
            addCriterion("F72 is null");
            return (Criteria) this;
        }

        public Criteria andF72IsNotNull() {
            addCriterion("F72 is not null");
            return (Criteria) this;
        }

        public Criteria andF72EqualTo(String value) {
            addCriterion("F72 =", value, "f72");
            return (Criteria) this;
        }

        public Criteria andF72NotEqualTo(String value) {
            addCriterion("F72 <>", value, "f72");
            return (Criteria) this;
        }

        public Criteria andF72GreaterThan(String value) {
            addCriterion("F72 >", value, "f72");
            return (Criteria) this;
        }

        public Criteria andF72GreaterThanOrEqualTo(String value) {
            addCriterion("F72 >=", value, "f72");
            return (Criteria) this;
        }

        public Criteria andF72LessThan(String value) {
            addCriterion("F72 <", value, "f72");
            return (Criteria) this;
        }

        public Criteria andF72LessThanOrEqualTo(String value) {
            addCriterion("F72 <=", value, "f72");
            return (Criteria) this;
        }

        public Criteria andF72Like(String value) {
            addCriterion("F72 like", value, "f72");
            return (Criteria) this;
        }

        public Criteria andF72NotLike(String value) {
            addCriterion("F72 not like", value, "f72");
            return (Criteria) this;
        }

        public Criteria andF72In(List<String> values) {
            addCriterion("F72 in", values, "f72");
            return (Criteria) this;
        }

        public Criteria andF72NotIn(List<String> values) {
            addCriterion("F72 not in", values, "f72");
            return (Criteria) this;
        }

        public Criteria andF72Between(String value1, String value2) {
            addCriterion("F72 between", value1, value2, "f72");
            return (Criteria) this;
        }

        public Criteria andF72NotBetween(String value1, String value2) {
            addCriterion("F72 not between", value1, value2, "f72");
            return (Criteria) this;
        }

        public Criteria andF73IsNull() {
            addCriterion("F73 is null");
            return (Criteria) this;
        }

        public Criteria andF73IsNotNull() {
            addCriterion("F73 is not null");
            return (Criteria) this;
        }

        public Criteria andF73EqualTo(String value) {
            addCriterion("F73 =", value, "f73");
            return (Criteria) this;
        }

        public Criteria andF73NotEqualTo(String value) {
            addCriterion("F73 <>", value, "f73");
            return (Criteria) this;
        }

        public Criteria andF73GreaterThan(String value) {
            addCriterion("F73 >", value, "f73");
            return (Criteria) this;
        }

        public Criteria andF73GreaterThanOrEqualTo(String value) {
            addCriterion("F73 >=", value, "f73");
            return (Criteria) this;
        }

        public Criteria andF73LessThan(String value) {
            addCriterion("F73 <", value, "f73");
            return (Criteria) this;
        }

        public Criteria andF73LessThanOrEqualTo(String value) {
            addCriterion("F73 <=", value, "f73");
            return (Criteria) this;
        }

        public Criteria andF73Like(String value) {
            addCriterion("F73 like", value, "f73");
            return (Criteria) this;
        }

        public Criteria andF73NotLike(String value) {
            addCriterion("F73 not like", value, "f73");
            return (Criteria) this;
        }

        public Criteria andF73In(List<String> values) {
            addCriterion("F73 in", values, "f73");
            return (Criteria) this;
        }

        public Criteria andF73NotIn(List<String> values) {
            addCriterion("F73 not in", values, "f73");
            return (Criteria) this;
        }

        public Criteria andF73Between(String value1, String value2) {
            addCriterion("F73 between", value1, value2, "f73");
            return (Criteria) this;
        }

        public Criteria andF73NotBetween(String value1, String value2) {
            addCriterion("F73 not between", value1, value2, "f73");
            return (Criteria) this;
        }

        public Criteria andF74IsNull() {
            addCriterion("F74 is null");
            return (Criteria) this;
        }

        public Criteria andF74IsNotNull() {
            addCriterion("F74 is not null");
            return (Criteria) this;
        }

        public Criteria andF74EqualTo(String value) {
            addCriterion("F74 =", value, "f74");
            return (Criteria) this;
        }

        public Criteria andF74NotEqualTo(String value) {
            addCriterion("F74 <>", value, "f74");
            return (Criteria) this;
        }

        public Criteria andF74GreaterThan(String value) {
            addCriterion("F74 >", value, "f74");
            return (Criteria) this;
        }

        public Criteria andF74GreaterThanOrEqualTo(String value) {
            addCriterion("F74 >=", value, "f74");
            return (Criteria) this;
        }

        public Criteria andF74LessThan(String value) {
            addCriterion("F74 <", value, "f74");
            return (Criteria) this;
        }

        public Criteria andF74LessThanOrEqualTo(String value) {
            addCriterion("F74 <=", value, "f74");
            return (Criteria) this;
        }

        public Criteria andF74Like(String value) {
            addCriterion("F74 like", value, "f74");
            return (Criteria) this;
        }

        public Criteria andF74NotLike(String value) {
            addCriterion("F74 not like", value, "f74");
            return (Criteria) this;
        }

        public Criteria andF74In(List<String> values) {
            addCriterion("F74 in", values, "f74");
            return (Criteria) this;
        }

        public Criteria andF74NotIn(List<String> values) {
            addCriterion("F74 not in", values, "f74");
            return (Criteria) this;
        }

        public Criteria andF74Between(String value1, String value2) {
            addCriterion("F74 between", value1, value2, "f74");
            return (Criteria) this;
        }

        public Criteria andF74NotBetween(String value1, String value2) {
            addCriterion("F74 not between", value1, value2, "f74");
            return (Criteria) this;
        }

        public Criteria andF75IsNull() {
            addCriterion("F75 is null");
            return (Criteria) this;
        }

        public Criteria andF75IsNotNull() {
            addCriterion("F75 is not null");
            return (Criteria) this;
        }

        public Criteria andF75EqualTo(String value) {
            addCriterion("F75 =", value, "f75");
            return (Criteria) this;
        }

        public Criteria andF75NotEqualTo(String value) {
            addCriterion("F75 <>", value, "f75");
            return (Criteria) this;
        }

        public Criteria andF75GreaterThan(String value) {
            addCriterion("F75 >", value, "f75");
            return (Criteria) this;
        }

        public Criteria andF75GreaterThanOrEqualTo(String value) {
            addCriterion("F75 >=", value, "f75");
            return (Criteria) this;
        }

        public Criteria andF75LessThan(String value) {
            addCriterion("F75 <", value, "f75");
            return (Criteria) this;
        }

        public Criteria andF75LessThanOrEqualTo(String value) {
            addCriterion("F75 <=", value, "f75");
            return (Criteria) this;
        }

        public Criteria andF75Like(String value) {
            addCriterion("F75 like", value, "f75");
            return (Criteria) this;
        }

        public Criteria andF75NotLike(String value) {
            addCriterion("F75 not like", value, "f75");
            return (Criteria) this;
        }

        public Criteria andF75In(List<String> values) {
            addCriterion("F75 in", values, "f75");
            return (Criteria) this;
        }

        public Criteria andF75NotIn(List<String> values) {
            addCriterion("F75 not in", values, "f75");
            return (Criteria) this;
        }

        public Criteria andF75Between(String value1, String value2) {
            addCriterion("F75 between", value1, value2, "f75");
            return (Criteria) this;
        }

        public Criteria andF75NotBetween(String value1, String value2) {
            addCriterion("F75 not between", value1, value2, "f75");
            return (Criteria) this;
        }

        public Criteria andF76IsNull() {
            addCriterion("F76 is null");
            return (Criteria) this;
        }

        public Criteria andF76IsNotNull() {
            addCriterion("F76 is not null");
            return (Criteria) this;
        }

        public Criteria andF76EqualTo(String value) {
            addCriterion("F76 =", value, "f76");
            return (Criteria) this;
        }

        public Criteria andF76NotEqualTo(String value) {
            addCriterion("F76 <>", value, "f76");
            return (Criteria) this;
        }

        public Criteria andF76GreaterThan(String value) {
            addCriterion("F76 >", value, "f76");
            return (Criteria) this;
        }

        public Criteria andF76GreaterThanOrEqualTo(String value) {
            addCriterion("F76 >=", value, "f76");
            return (Criteria) this;
        }

        public Criteria andF76LessThan(String value) {
            addCriterion("F76 <", value, "f76");
            return (Criteria) this;
        }

        public Criteria andF76LessThanOrEqualTo(String value) {
            addCriterion("F76 <=", value, "f76");
            return (Criteria) this;
        }

        public Criteria andF76Like(String value) {
            addCriterion("F76 like", value, "f76");
            return (Criteria) this;
        }

        public Criteria andF76NotLike(String value) {
            addCriterion("F76 not like", value, "f76");
            return (Criteria) this;
        }

        public Criteria andF76In(List<String> values) {
            addCriterion("F76 in", values, "f76");
            return (Criteria) this;
        }

        public Criteria andF76NotIn(List<String> values) {
            addCriterion("F76 not in", values, "f76");
            return (Criteria) this;
        }

        public Criteria andF76Between(String value1, String value2) {
            addCriterion("F76 between", value1, value2, "f76");
            return (Criteria) this;
        }

        public Criteria andF76NotBetween(String value1, String value2) {
            addCriterion("F76 not between", value1, value2, "f76");
            return (Criteria) this;
        }

        public Criteria andF77IsNull() {
            addCriterion("F77 is null");
            return (Criteria) this;
        }

        public Criteria andF77IsNotNull() {
            addCriterion("F77 is not null");
            return (Criteria) this;
        }

        public Criteria andF77EqualTo(String value) {
            addCriterion("F77 =", value, "f77");
            return (Criteria) this;
        }

        public Criteria andF77NotEqualTo(String value) {
            addCriterion("F77 <>", value, "f77");
            return (Criteria) this;
        }

        public Criteria andF77GreaterThan(String value) {
            addCriterion("F77 >", value, "f77");
            return (Criteria) this;
        }

        public Criteria andF77GreaterThanOrEqualTo(String value) {
            addCriterion("F77 >=", value, "f77");
            return (Criteria) this;
        }

        public Criteria andF77LessThan(String value) {
            addCriterion("F77 <", value, "f77");
            return (Criteria) this;
        }

        public Criteria andF77LessThanOrEqualTo(String value) {
            addCriterion("F77 <=", value, "f77");
            return (Criteria) this;
        }

        public Criteria andF77Like(String value) {
            addCriterion("F77 like", value, "f77");
            return (Criteria) this;
        }

        public Criteria andF77NotLike(String value) {
            addCriterion("F77 not like", value, "f77");
            return (Criteria) this;
        }

        public Criteria andF77In(List<String> values) {
            addCriterion("F77 in", values, "f77");
            return (Criteria) this;
        }

        public Criteria andF77NotIn(List<String> values) {
            addCriterion("F77 not in", values, "f77");
            return (Criteria) this;
        }

        public Criteria andF77Between(String value1, String value2) {
            addCriterion("F77 between", value1, value2, "f77");
            return (Criteria) this;
        }

        public Criteria andF77NotBetween(String value1, String value2) {
            addCriterion("F77 not between", value1, value2, "f77");
            return (Criteria) this;
        }

        public Criteria andF78IsNull() {
            addCriterion("F78 is null");
            return (Criteria) this;
        }

        public Criteria andF78IsNotNull() {
            addCriterion("F78 is not null");
            return (Criteria) this;
        }

        public Criteria andF78EqualTo(String value) {
            addCriterion("F78 =", value, "f78");
            return (Criteria) this;
        }

        public Criteria andF78NotEqualTo(String value) {
            addCriterion("F78 <>", value, "f78");
            return (Criteria) this;
        }

        public Criteria andF78GreaterThan(String value) {
            addCriterion("F78 >", value, "f78");
            return (Criteria) this;
        }

        public Criteria andF78GreaterThanOrEqualTo(String value) {
            addCriterion("F78 >=", value, "f78");
            return (Criteria) this;
        }

        public Criteria andF78LessThan(String value) {
            addCriterion("F78 <", value, "f78");
            return (Criteria) this;
        }

        public Criteria andF78LessThanOrEqualTo(String value) {
            addCriterion("F78 <=", value, "f78");
            return (Criteria) this;
        }

        public Criteria andF78Like(String value) {
            addCriterion("F78 like", value, "f78");
            return (Criteria) this;
        }

        public Criteria andF78NotLike(String value) {
            addCriterion("F78 not like", value, "f78");
            return (Criteria) this;
        }

        public Criteria andF78In(List<String> values) {
            addCriterion("F78 in", values, "f78");
            return (Criteria) this;
        }

        public Criteria andF78NotIn(List<String> values) {
            addCriterion("F78 not in", values, "f78");
            return (Criteria) this;
        }

        public Criteria andF78Between(String value1, String value2) {
            addCriterion("F78 between", value1, value2, "f78");
            return (Criteria) this;
        }

        public Criteria andF78NotBetween(String value1, String value2) {
            addCriterion("F78 not between", value1, value2, "f78");
            return (Criteria) this;
        }

        public Criteria andF79IsNull() {
            addCriterion("F79 is null");
            return (Criteria) this;
        }

        public Criteria andF79IsNotNull() {
            addCriterion("F79 is not null");
            return (Criteria) this;
        }

        public Criteria andF79EqualTo(String value) {
            addCriterion("F79 =", value, "f79");
            return (Criteria) this;
        }

        public Criteria andF79NotEqualTo(String value) {
            addCriterion("F79 <>", value, "f79");
            return (Criteria) this;
        }

        public Criteria andF79GreaterThan(String value) {
            addCriterion("F79 >", value, "f79");
            return (Criteria) this;
        }

        public Criteria andF79GreaterThanOrEqualTo(String value) {
            addCriterion("F79 >=", value, "f79");
            return (Criteria) this;
        }

        public Criteria andF79LessThan(String value) {
            addCriterion("F79 <", value, "f79");
            return (Criteria) this;
        }

        public Criteria andF79LessThanOrEqualTo(String value) {
            addCriterion("F79 <=", value, "f79");
            return (Criteria) this;
        }

        public Criteria andF79Like(String value) {
            addCriterion("F79 like", value, "f79");
            return (Criteria) this;
        }

        public Criteria andF79NotLike(String value) {
            addCriterion("F79 not like", value, "f79");
            return (Criteria) this;
        }

        public Criteria andF79In(List<String> values) {
            addCriterion("F79 in", values, "f79");
            return (Criteria) this;
        }

        public Criteria andF79NotIn(List<String> values) {
            addCriterion("F79 not in", values, "f79");
            return (Criteria) this;
        }

        public Criteria andF79Between(String value1, String value2) {
            addCriterion("F79 between", value1, value2, "f79");
            return (Criteria) this;
        }

        public Criteria andF79NotBetween(String value1, String value2) {
            addCriterion("F79 not between", value1, value2, "f79");
            return (Criteria) this;
        }

        public Criteria andF80IsNull() {
            addCriterion("F80 is null");
            return (Criteria) this;
        }

        public Criteria andF80IsNotNull() {
            addCriterion("F80 is not null");
            return (Criteria) this;
        }

        public Criteria andF80EqualTo(String value) {
            addCriterion("F80 =", value, "f80");
            return (Criteria) this;
        }

        public Criteria andF80NotEqualTo(String value) {
            addCriterion("F80 <>", value, "f80");
            return (Criteria) this;
        }

        public Criteria andF80GreaterThan(String value) {
            addCriterion("F80 >", value, "f80");
            return (Criteria) this;
        }

        public Criteria andF80GreaterThanOrEqualTo(String value) {
            addCriterion("F80 >=", value, "f80");
            return (Criteria) this;
        }

        public Criteria andF80LessThan(String value) {
            addCriterion("F80 <", value, "f80");
            return (Criteria) this;
        }

        public Criteria andF80LessThanOrEqualTo(String value) {
            addCriterion("F80 <=", value, "f80");
            return (Criteria) this;
        }

        public Criteria andF80Like(String value) {
            addCriterion("F80 like", value, "f80");
            return (Criteria) this;
        }

        public Criteria andF80NotLike(String value) {
            addCriterion("F80 not like", value, "f80");
            return (Criteria) this;
        }

        public Criteria andF80In(List<String> values) {
            addCriterion("F80 in", values, "f80");
            return (Criteria) this;
        }

        public Criteria andF80NotIn(List<String> values) {
            addCriterion("F80 not in", values, "f80");
            return (Criteria) this;
        }

        public Criteria andF80Between(String value1, String value2) {
            addCriterion("F80 between", value1, value2, "f80");
            return (Criteria) this;
        }

        public Criteria andF80NotBetween(String value1, String value2) {
            addCriterion("F80 not between", value1, value2, "f80");
            return (Criteria) this;
        }

        public Criteria andF81IsNull() {
            addCriterion("F81 is null");
            return (Criteria) this;
        }

        public Criteria andF81IsNotNull() {
            addCriterion("F81 is not null");
            return (Criteria) this;
        }

        public Criteria andF81EqualTo(String value) {
            addCriterion("F81 =", value, "f81");
            return (Criteria) this;
        }

        public Criteria andF81NotEqualTo(String value) {
            addCriterion("F81 <>", value, "f81");
            return (Criteria) this;
        }

        public Criteria andF81GreaterThan(String value) {
            addCriterion("F81 >", value, "f81");
            return (Criteria) this;
        }

        public Criteria andF81GreaterThanOrEqualTo(String value) {
            addCriterion("F81 >=", value, "f81");
            return (Criteria) this;
        }

        public Criteria andF81LessThan(String value) {
            addCriterion("F81 <", value, "f81");
            return (Criteria) this;
        }

        public Criteria andF81LessThanOrEqualTo(String value) {
            addCriterion("F81 <=", value, "f81");
            return (Criteria) this;
        }

        public Criteria andF81Like(String value) {
            addCriterion("F81 like", value, "f81");
            return (Criteria) this;
        }

        public Criteria andF81NotLike(String value) {
            addCriterion("F81 not like", value, "f81");
            return (Criteria) this;
        }

        public Criteria andF81In(List<String> values) {
            addCriterion("F81 in", values, "f81");
            return (Criteria) this;
        }

        public Criteria andF81NotIn(List<String> values) {
            addCriterion("F81 not in", values, "f81");
            return (Criteria) this;
        }

        public Criteria andF81Between(String value1, String value2) {
            addCriterion("F81 between", value1, value2, "f81");
            return (Criteria) this;
        }

        public Criteria andF81NotBetween(String value1, String value2) {
            addCriterion("F81 not between", value1, value2, "f81");
            return (Criteria) this;
        }

        public Criteria andF82IsNull() {
            addCriterion("F82 is null");
            return (Criteria) this;
        }

        public Criteria andF82IsNotNull() {
            addCriterion("F82 is not null");
            return (Criteria) this;
        }

        public Criteria andF82EqualTo(String value) {
            addCriterion("F82 =", value, "f82");
            return (Criteria) this;
        }

        public Criteria andF82NotEqualTo(String value) {
            addCriterion("F82 <>", value, "f82");
            return (Criteria) this;
        }

        public Criteria andF82GreaterThan(String value) {
            addCriterion("F82 >", value, "f82");
            return (Criteria) this;
        }

        public Criteria andF82GreaterThanOrEqualTo(String value) {
            addCriterion("F82 >=", value, "f82");
            return (Criteria) this;
        }

        public Criteria andF82LessThan(String value) {
            addCriterion("F82 <", value, "f82");
            return (Criteria) this;
        }

        public Criteria andF82LessThanOrEqualTo(String value) {
            addCriterion("F82 <=", value, "f82");
            return (Criteria) this;
        }

        public Criteria andF82Like(String value) {
            addCriterion("F82 like", value, "f82");
            return (Criteria) this;
        }

        public Criteria andF82NotLike(String value) {
            addCriterion("F82 not like", value, "f82");
            return (Criteria) this;
        }

        public Criteria andF82In(List<String> values) {
            addCriterion("F82 in", values, "f82");
            return (Criteria) this;
        }

        public Criteria andF82NotIn(List<String> values) {
            addCriterion("F82 not in", values, "f82");
            return (Criteria) this;
        }

        public Criteria andF82Between(String value1, String value2) {
            addCriterion("F82 between", value1, value2, "f82");
            return (Criteria) this;
        }

        public Criteria andF82NotBetween(String value1, String value2) {
            addCriterion("F82 not between", value1, value2, "f82");
            return (Criteria) this;
        }

        public Criteria andF83IsNull() {
            addCriterion("F83 is null");
            return (Criteria) this;
        }

        public Criteria andF83IsNotNull() {
            addCriterion("F83 is not null");
            return (Criteria) this;
        }

        public Criteria andF83EqualTo(String value) {
            addCriterion("F83 =", value, "f83");
            return (Criteria) this;
        }

        public Criteria andF83NotEqualTo(String value) {
            addCriterion("F83 <>", value, "f83");
            return (Criteria) this;
        }

        public Criteria andF83GreaterThan(String value) {
            addCriterion("F83 >", value, "f83");
            return (Criteria) this;
        }

        public Criteria andF83GreaterThanOrEqualTo(String value) {
            addCriterion("F83 >=", value, "f83");
            return (Criteria) this;
        }

        public Criteria andF83LessThan(String value) {
            addCriterion("F83 <", value, "f83");
            return (Criteria) this;
        }

        public Criteria andF83LessThanOrEqualTo(String value) {
            addCriterion("F83 <=", value, "f83");
            return (Criteria) this;
        }

        public Criteria andF83Like(String value) {
            addCriterion("F83 like", value, "f83");
            return (Criteria) this;
        }

        public Criteria andF83NotLike(String value) {
            addCriterion("F83 not like", value, "f83");
            return (Criteria) this;
        }

        public Criteria andF83In(List<String> values) {
            addCriterion("F83 in", values, "f83");
            return (Criteria) this;
        }

        public Criteria andF83NotIn(List<String> values) {
            addCriterion("F83 not in", values, "f83");
            return (Criteria) this;
        }

        public Criteria andF83Between(String value1, String value2) {
            addCriterion("F83 between", value1, value2, "f83");
            return (Criteria) this;
        }

        public Criteria andF83NotBetween(String value1, String value2) {
            addCriterion("F83 not between", value1, value2, "f83");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdIsNull() {
            addCriterion("attachment_id is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdIsNotNull() {
            addCriterion("attachment_id is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdEqualTo(Integer value) {
            addCriterion("attachment_id =", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdNotEqualTo(Integer value) {
            addCriterion("attachment_id <>", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdGreaterThan(Integer value) {
            addCriterion("attachment_id >", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("attachment_id >=", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdLessThan(Integer value) {
            addCriterion("attachment_id <", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("attachment_id <=", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdIn(List<Integer> values) {
            addCriterion("attachment_id in", values, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdNotIn(List<Integer> values) {
            addCriterion("attachment_id not in", values, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdBetween(Integer value1, Integer value2) {
            addCriterion("attachment_id between", value1, value2, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("attachment_id not between", value1, value2, "attachmentId");
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