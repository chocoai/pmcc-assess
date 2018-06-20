package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.List;

public class FuniHousesMatingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FuniHousesMatingExample() {
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

        public Criteria andGjIsNull() {
            addCriterion("gj is null");
            return (Criteria) this;
        }

        public Criteria andGjIsNotNull() {
            addCriterion("gj is not null");
            return (Criteria) this;
        }

        public Criteria andGjEqualTo(String value) {
            addCriterion("gj =", value, "gj");
            return (Criteria) this;
        }

        public Criteria andGjNotEqualTo(String value) {
            addCriterion("gj <>", value, "gj");
            return (Criteria) this;
        }

        public Criteria andGjGreaterThan(String value) {
            addCriterion("gj >", value, "gj");
            return (Criteria) this;
        }

        public Criteria andGjGreaterThanOrEqualTo(String value) {
            addCriterion("gj >=", value, "gj");
            return (Criteria) this;
        }

        public Criteria andGjLessThan(String value) {
            addCriterion("gj <", value, "gj");
            return (Criteria) this;
        }

        public Criteria andGjLessThanOrEqualTo(String value) {
            addCriterion("gj <=", value, "gj");
            return (Criteria) this;
        }

        public Criteria andGjLike(String value) {
            addCriterion("gj like", value, "gj");
            return (Criteria) this;
        }

        public Criteria andGjNotLike(String value) {
            addCriterion("gj not like", value, "gj");
            return (Criteria) this;
        }

        public Criteria andGjIn(List<String> values) {
            addCriterion("gj in", values, "gj");
            return (Criteria) this;
        }

        public Criteria andGjNotIn(List<String> values) {
            addCriterion("gj not in", values, "gj");
            return (Criteria) this;
        }

        public Criteria andGjBetween(String value1, String value2) {
            addCriterion("gj between", value1, value2, "gj");
            return (Criteria) this;
        }

        public Criteria andGjNotBetween(String value1, String value2) {
            addCriterion("gj not between", value1, value2, "gj");
            return (Criteria) this;
        }

        public Criteria andDtIsNull() {
            addCriterion("dt is null");
            return (Criteria) this;
        }

        public Criteria andDtIsNotNull() {
            addCriterion("dt is not null");
            return (Criteria) this;
        }

        public Criteria andDtEqualTo(String value) {
            addCriterion("dt =", value, "dt");
            return (Criteria) this;
        }

        public Criteria andDtNotEqualTo(String value) {
            addCriterion("dt <>", value, "dt");
            return (Criteria) this;
        }

        public Criteria andDtGreaterThan(String value) {
            addCriterion("dt >", value, "dt");
            return (Criteria) this;
        }

        public Criteria andDtGreaterThanOrEqualTo(String value) {
            addCriterion("dt >=", value, "dt");
            return (Criteria) this;
        }

        public Criteria andDtLessThan(String value) {
            addCriterion("dt <", value, "dt");
            return (Criteria) this;
        }

        public Criteria andDtLessThanOrEqualTo(String value) {
            addCriterion("dt <=", value, "dt");
            return (Criteria) this;
        }

        public Criteria andDtLike(String value) {
            addCriterion("dt like", value, "dt");
            return (Criteria) this;
        }

        public Criteria andDtNotLike(String value) {
            addCriterion("dt not like", value, "dt");
            return (Criteria) this;
        }

        public Criteria andDtIn(List<String> values) {
            addCriterion("dt in", values, "dt");
            return (Criteria) this;
        }

        public Criteria andDtNotIn(List<String> values) {
            addCriterion("dt not in", values, "dt");
            return (Criteria) this;
        }

        public Criteria andDtBetween(String value1, String value2) {
            addCriterion("dt between", value1, value2, "dt");
            return (Criteria) this;
        }

        public Criteria andDtNotBetween(String value1, String value2) {
            addCriterion("dt not between", value1, value2, "dt");
            return (Criteria) this;
        }

        public Criteria andYeyIsNull() {
            addCriterion("yey is null");
            return (Criteria) this;
        }

        public Criteria andYeyIsNotNull() {
            addCriterion("yey is not null");
            return (Criteria) this;
        }

        public Criteria andYeyEqualTo(String value) {
            addCriterion("yey =", value, "yey");
            return (Criteria) this;
        }

        public Criteria andYeyNotEqualTo(String value) {
            addCriterion("yey <>", value, "yey");
            return (Criteria) this;
        }

        public Criteria andYeyGreaterThan(String value) {
            addCriterion("yey >", value, "yey");
            return (Criteria) this;
        }

        public Criteria andYeyGreaterThanOrEqualTo(String value) {
            addCriterion("yey >=", value, "yey");
            return (Criteria) this;
        }

        public Criteria andYeyLessThan(String value) {
            addCriterion("yey <", value, "yey");
            return (Criteria) this;
        }

        public Criteria andYeyLessThanOrEqualTo(String value) {
            addCriterion("yey <=", value, "yey");
            return (Criteria) this;
        }

        public Criteria andYeyLike(String value) {
            addCriterion("yey like", value, "yey");
            return (Criteria) this;
        }

        public Criteria andYeyNotLike(String value) {
            addCriterion("yey not like", value, "yey");
            return (Criteria) this;
        }

        public Criteria andYeyIn(List<String> values) {
            addCriterion("yey in", values, "yey");
            return (Criteria) this;
        }

        public Criteria andYeyNotIn(List<String> values) {
            addCriterion("yey not in", values, "yey");
            return (Criteria) this;
        }

        public Criteria andYeyBetween(String value1, String value2) {
            addCriterion("yey between", value1, value2, "yey");
            return (Criteria) this;
        }

        public Criteria andYeyNotBetween(String value1, String value2) {
            addCriterion("yey not between", value1, value2, "yey");
            return (Criteria) this;
        }

        public Criteria andXxIsNull() {
            addCriterion("xx is null");
            return (Criteria) this;
        }

        public Criteria andXxIsNotNull() {
            addCriterion("xx is not null");
            return (Criteria) this;
        }

        public Criteria andXxEqualTo(String value) {
            addCriterion("xx =", value, "xx");
            return (Criteria) this;
        }

        public Criteria andXxNotEqualTo(String value) {
            addCriterion("xx <>", value, "xx");
            return (Criteria) this;
        }

        public Criteria andXxGreaterThan(String value) {
            addCriterion("xx >", value, "xx");
            return (Criteria) this;
        }

        public Criteria andXxGreaterThanOrEqualTo(String value) {
            addCriterion("xx >=", value, "xx");
            return (Criteria) this;
        }

        public Criteria andXxLessThan(String value) {
            addCriterion("xx <", value, "xx");
            return (Criteria) this;
        }

        public Criteria andXxLessThanOrEqualTo(String value) {
            addCriterion("xx <=", value, "xx");
            return (Criteria) this;
        }

        public Criteria andXxLike(String value) {
            addCriterion("xx like", value, "xx");
            return (Criteria) this;
        }

        public Criteria andXxNotLike(String value) {
            addCriterion("xx not like", value, "xx");
            return (Criteria) this;
        }

        public Criteria andXxIn(List<String> values) {
            addCriterion("xx in", values, "xx");
            return (Criteria) this;
        }

        public Criteria andXxNotIn(List<String> values) {
            addCriterion("xx not in", values, "xx");
            return (Criteria) this;
        }

        public Criteria andXxBetween(String value1, String value2) {
            addCriterion("xx between", value1, value2, "xx");
            return (Criteria) this;
        }

        public Criteria andXxNotBetween(String value1, String value2) {
            addCriterion("xx not between", value1, value2, "xx");
            return (Criteria) this;
        }

        public Criteria andZxIsNull() {
            addCriterion("zx is null");
            return (Criteria) this;
        }

        public Criteria andZxIsNotNull() {
            addCriterion("zx is not null");
            return (Criteria) this;
        }

        public Criteria andZxEqualTo(String value) {
            addCriterion("zx =", value, "zx");
            return (Criteria) this;
        }

        public Criteria andZxNotEqualTo(String value) {
            addCriterion("zx <>", value, "zx");
            return (Criteria) this;
        }

        public Criteria andZxGreaterThan(String value) {
            addCriterion("zx >", value, "zx");
            return (Criteria) this;
        }

        public Criteria andZxGreaterThanOrEqualTo(String value) {
            addCriterion("zx >=", value, "zx");
            return (Criteria) this;
        }

        public Criteria andZxLessThan(String value) {
            addCriterion("zx <", value, "zx");
            return (Criteria) this;
        }

        public Criteria andZxLessThanOrEqualTo(String value) {
            addCriterion("zx <=", value, "zx");
            return (Criteria) this;
        }

        public Criteria andZxLike(String value) {
            addCriterion("zx like", value, "zx");
            return (Criteria) this;
        }

        public Criteria andZxNotLike(String value) {
            addCriterion("zx not like", value, "zx");
            return (Criteria) this;
        }

        public Criteria andZxIn(List<String> values) {
            addCriterion("zx in", values, "zx");
            return (Criteria) this;
        }

        public Criteria andZxNotIn(List<String> values) {
            addCriterion("zx not in", values, "zx");
            return (Criteria) this;
        }

        public Criteria andZxBetween(String value1, String value2) {
            addCriterion("zx between", value1, value2, "zx");
            return (Criteria) this;
        }

        public Criteria andZxNotBetween(String value1, String value2) {
            addCriterion("zx not between", value1, value2, "zx");
            return (Criteria) this;
        }

        public Criteria andDxIsNull() {
            addCriterion("dx is null");
            return (Criteria) this;
        }

        public Criteria andDxIsNotNull() {
            addCriterion("dx is not null");
            return (Criteria) this;
        }

        public Criteria andDxEqualTo(String value) {
            addCriterion("dx =", value, "dx");
            return (Criteria) this;
        }

        public Criteria andDxNotEqualTo(String value) {
            addCriterion("dx <>", value, "dx");
            return (Criteria) this;
        }

        public Criteria andDxGreaterThan(String value) {
            addCriterion("dx >", value, "dx");
            return (Criteria) this;
        }

        public Criteria andDxGreaterThanOrEqualTo(String value) {
            addCriterion("dx >=", value, "dx");
            return (Criteria) this;
        }

        public Criteria andDxLessThan(String value) {
            addCriterion("dx <", value, "dx");
            return (Criteria) this;
        }

        public Criteria andDxLessThanOrEqualTo(String value) {
            addCriterion("dx <=", value, "dx");
            return (Criteria) this;
        }

        public Criteria andDxLike(String value) {
            addCriterion("dx like", value, "dx");
            return (Criteria) this;
        }

        public Criteria andDxNotLike(String value) {
            addCriterion("dx not like", value, "dx");
            return (Criteria) this;
        }

        public Criteria andDxIn(List<String> values) {
            addCriterion("dx in", values, "dx");
            return (Criteria) this;
        }

        public Criteria andDxNotIn(List<String> values) {
            addCriterion("dx not in", values, "dx");
            return (Criteria) this;
        }

        public Criteria andDxBetween(String value1, String value2) {
            addCriterion("dx between", value1, value2, "dx");
            return (Criteria) this;
        }

        public Criteria andDxNotBetween(String value1, String value2) {
            addCriterion("dx not between", value1, value2, "dx");
            return (Criteria) this;
        }

        public Criteria andYyIsNull() {
            addCriterion("yy is null");
            return (Criteria) this;
        }

        public Criteria andYyIsNotNull() {
            addCriterion("yy is not null");
            return (Criteria) this;
        }

        public Criteria andYyEqualTo(String value) {
            addCriterion("yy =", value, "yy");
            return (Criteria) this;
        }

        public Criteria andYyNotEqualTo(String value) {
            addCriterion("yy <>", value, "yy");
            return (Criteria) this;
        }

        public Criteria andYyGreaterThan(String value) {
            addCriterion("yy >", value, "yy");
            return (Criteria) this;
        }

        public Criteria andYyGreaterThanOrEqualTo(String value) {
            addCriterion("yy >=", value, "yy");
            return (Criteria) this;
        }

        public Criteria andYyLessThan(String value) {
            addCriterion("yy <", value, "yy");
            return (Criteria) this;
        }

        public Criteria andYyLessThanOrEqualTo(String value) {
            addCriterion("yy <=", value, "yy");
            return (Criteria) this;
        }

        public Criteria andYyLike(String value) {
            addCriterion("yy like", value, "yy");
            return (Criteria) this;
        }

        public Criteria andYyNotLike(String value) {
            addCriterion("yy not like", value, "yy");
            return (Criteria) this;
        }

        public Criteria andYyIn(List<String> values) {
            addCriterion("yy in", values, "yy");
            return (Criteria) this;
        }

        public Criteria andYyNotIn(List<String> values) {
            addCriterion("yy not in", values, "yy");
            return (Criteria) this;
        }

        public Criteria andYyBetween(String value1, String value2) {
            addCriterion("yy between", value1, value2, "yy");
            return (Criteria) this;
        }

        public Criteria andYyNotBetween(String value1, String value2) {
            addCriterion("yy not between", value1, value2, "yy");
            return (Criteria) this;
        }

        public Criteria andYdIsNull() {
            addCriterion("yd is null");
            return (Criteria) this;
        }

        public Criteria andYdIsNotNull() {
            addCriterion("yd is not null");
            return (Criteria) this;
        }

        public Criteria andYdEqualTo(String value) {
            addCriterion("yd =", value, "yd");
            return (Criteria) this;
        }

        public Criteria andYdNotEqualTo(String value) {
            addCriterion("yd <>", value, "yd");
            return (Criteria) this;
        }

        public Criteria andYdGreaterThan(String value) {
            addCriterion("yd >", value, "yd");
            return (Criteria) this;
        }

        public Criteria andYdGreaterThanOrEqualTo(String value) {
            addCriterion("yd >=", value, "yd");
            return (Criteria) this;
        }

        public Criteria andYdLessThan(String value) {
            addCriterion("yd <", value, "yd");
            return (Criteria) this;
        }

        public Criteria andYdLessThanOrEqualTo(String value) {
            addCriterion("yd <=", value, "yd");
            return (Criteria) this;
        }

        public Criteria andYdLike(String value) {
            addCriterion("yd like", value, "yd");
            return (Criteria) this;
        }

        public Criteria andYdNotLike(String value) {
            addCriterion("yd not like", value, "yd");
            return (Criteria) this;
        }

        public Criteria andYdIn(List<String> values) {
            addCriterion("yd in", values, "yd");
            return (Criteria) this;
        }

        public Criteria andYdNotIn(List<String> values) {
            addCriterion("yd not in", values, "yd");
            return (Criteria) this;
        }

        public Criteria andYdBetween(String value1, String value2) {
            addCriterion("yd between", value1, value2, "yd");
            return (Criteria) this;
        }

        public Criteria andYdNotBetween(String value1, String value2) {
            addCriterion("yd not between", value1, value2, "yd");
            return (Criteria) this;
        }

        public Criteria andScIsNull() {
            addCriterion("sc is null");
            return (Criteria) this;
        }

        public Criteria andScIsNotNull() {
            addCriterion("sc is not null");
            return (Criteria) this;
        }

        public Criteria andScEqualTo(String value) {
            addCriterion("sc =", value, "sc");
            return (Criteria) this;
        }

        public Criteria andScNotEqualTo(String value) {
            addCriterion("sc <>", value, "sc");
            return (Criteria) this;
        }

        public Criteria andScGreaterThan(String value) {
            addCriterion("sc >", value, "sc");
            return (Criteria) this;
        }

        public Criteria andScGreaterThanOrEqualTo(String value) {
            addCriterion("sc >=", value, "sc");
            return (Criteria) this;
        }

        public Criteria andScLessThan(String value) {
            addCriterion("sc <", value, "sc");
            return (Criteria) this;
        }

        public Criteria andScLessThanOrEqualTo(String value) {
            addCriterion("sc <=", value, "sc");
            return (Criteria) this;
        }

        public Criteria andScLike(String value) {
            addCriterion("sc like", value, "sc");
            return (Criteria) this;
        }

        public Criteria andScNotLike(String value) {
            addCriterion("sc not like", value, "sc");
            return (Criteria) this;
        }

        public Criteria andScIn(List<String> values) {
            addCriterion("sc in", values, "sc");
            return (Criteria) this;
        }

        public Criteria andScNotIn(List<String> values) {
            addCriterion("sc not in", values, "sc");
            return (Criteria) this;
        }

        public Criteria andScBetween(String value1, String value2) {
            addCriterion("sc between", value1, value2, "sc");
            return (Criteria) this;
        }

        public Criteria andScNotBetween(String value1, String value2) {
            addCriterion("sc not between", value1, value2, "sc");
            return (Criteria) this;
        }

        public Criteria andCsIsNull() {
            addCriterion("cs is null");
            return (Criteria) this;
        }

        public Criteria andCsIsNotNull() {
            addCriterion("cs is not null");
            return (Criteria) this;
        }

        public Criteria andCsEqualTo(String value) {
            addCriterion("cs =", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotEqualTo(String value) {
            addCriterion("cs <>", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsGreaterThan(String value) {
            addCriterion("cs >", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsGreaterThanOrEqualTo(String value) {
            addCriterion("cs >=", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsLessThan(String value) {
            addCriterion("cs <", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsLessThanOrEqualTo(String value) {
            addCriterion("cs <=", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsLike(String value) {
            addCriterion("cs like", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotLike(String value) {
            addCriterion("cs not like", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsIn(List<String> values) {
            addCriterion("cs in", values, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotIn(List<String> values) {
            addCriterion("cs not in", values, "cs");
            return (Criteria) this;
        }

        public Criteria andCsBetween(String value1, String value2) {
            addCriterion("cs between", value1, value2, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotBetween(String value1, String value2) {
            addCriterion("cs not between", value1, value2, "cs");
            return (Criteria) this;
        }

        public Criteria andCscIsNull() {
            addCriterion("csc is null");
            return (Criteria) this;
        }

        public Criteria andCscIsNotNull() {
            addCriterion("csc is not null");
            return (Criteria) this;
        }

        public Criteria andCscEqualTo(String value) {
            addCriterion("csc =", value, "csc");
            return (Criteria) this;
        }

        public Criteria andCscNotEqualTo(String value) {
            addCriterion("csc <>", value, "csc");
            return (Criteria) this;
        }

        public Criteria andCscGreaterThan(String value) {
            addCriterion("csc >", value, "csc");
            return (Criteria) this;
        }

        public Criteria andCscGreaterThanOrEqualTo(String value) {
            addCriterion("csc >=", value, "csc");
            return (Criteria) this;
        }

        public Criteria andCscLessThan(String value) {
            addCriterion("csc <", value, "csc");
            return (Criteria) this;
        }

        public Criteria andCscLessThanOrEqualTo(String value) {
            addCriterion("csc <=", value, "csc");
            return (Criteria) this;
        }

        public Criteria andCscLike(String value) {
            addCriterion("csc like", value, "csc");
            return (Criteria) this;
        }

        public Criteria andCscNotLike(String value) {
            addCriterion("csc not like", value, "csc");
            return (Criteria) this;
        }

        public Criteria andCscIn(List<String> values) {
            addCriterion("csc in", values, "csc");
            return (Criteria) this;
        }

        public Criteria andCscNotIn(List<String> values) {
            addCriterion("csc not in", values, "csc");
            return (Criteria) this;
        }

        public Criteria andCscBetween(String value1, String value2) {
            addCriterion("csc between", value1, value2, "csc");
            return (Criteria) this;
        }

        public Criteria andCscNotBetween(String value1, String value2) {
            addCriterion("csc not between", value1, value2, "csc");
            return (Criteria) this;
        }

        public Criteria andYhIsNull() {
            addCriterion("yh is null");
            return (Criteria) this;
        }

        public Criteria andYhIsNotNull() {
            addCriterion("yh is not null");
            return (Criteria) this;
        }

        public Criteria andYhEqualTo(String value) {
            addCriterion("yh =", value, "yh");
            return (Criteria) this;
        }

        public Criteria andYhNotEqualTo(String value) {
            addCriterion("yh <>", value, "yh");
            return (Criteria) this;
        }

        public Criteria andYhGreaterThan(String value) {
            addCriterion("yh >", value, "yh");
            return (Criteria) this;
        }

        public Criteria andYhGreaterThanOrEqualTo(String value) {
            addCriterion("yh >=", value, "yh");
            return (Criteria) this;
        }

        public Criteria andYhLessThan(String value) {
            addCriterion("yh <", value, "yh");
            return (Criteria) this;
        }

        public Criteria andYhLessThanOrEqualTo(String value) {
            addCriterion("yh <=", value, "yh");
            return (Criteria) this;
        }

        public Criteria andYhLike(String value) {
            addCriterion("yh like", value, "yh");
            return (Criteria) this;
        }

        public Criteria andYhNotLike(String value) {
            addCriterion("yh not like", value, "yh");
            return (Criteria) this;
        }

        public Criteria andYhIn(List<String> values) {
            addCriterion("yh in", values, "yh");
            return (Criteria) this;
        }

        public Criteria andYhNotIn(List<String> values) {
            addCriterion("yh not in", values, "yh");
            return (Criteria) this;
        }

        public Criteria andYhBetween(String value1, String value2) {
            addCriterion("yh between", value1, value2, "yh");
            return (Criteria) this;
        }

        public Criteria andYhNotBetween(String value1, String value2) {
            addCriterion("yh not between", value1, value2, "yh");
            return (Criteria) this;
        }

        public Criteria andAtmIsNull() {
            addCriterion("atm is null");
            return (Criteria) this;
        }

        public Criteria andAtmIsNotNull() {
            addCriterion("atm is not null");
            return (Criteria) this;
        }

        public Criteria andAtmEqualTo(String value) {
            addCriterion("atm =", value, "atm");
            return (Criteria) this;
        }

        public Criteria andAtmNotEqualTo(String value) {
            addCriterion("atm <>", value, "atm");
            return (Criteria) this;
        }

        public Criteria andAtmGreaterThan(String value) {
            addCriterion("atm >", value, "atm");
            return (Criteria) this;
        }

        public Criteria andAtmGreaterThanOrEqualTo(String value) {
            addCriterion("atm >=", value, "atm");
            return (Criteria) this;
        }

        public Criteria andAtmLessThan(String value) {
            addCriterion("atm <", value, "atm");
            return (Criteria) this;
        }

        public Criteria andAtmLessThanOrEqualTo(String value) {
            addCriterion("atm <=", value, "atm");
            return (Criteria) this;
        }

        public Criteria andAtmLike(String value) {
            addCriterion("atm like", value, "atm");
            return (Criteria) this;
        }

        public Criteria andAtmNotLike(String value) {
            addCriterion("atm not like", value, "atm");
            return (Criteria) this;
        }

        public Criteria andAtmIn(List<String> values) {
            addCriterion("atm in", values, "atm");
            return (Criteria) this;
        }

        public Criteria andAtmNotIn(List<String> values) {
            addCriterion("atm not in", values, "atm");
            return (Criteria) this;
        }

        public Criteria andAtmBetween(String value1, String value2) {
            addCriterion("atm between", value1, value2, "atm");
            return (Criteria) this;
        }

        public Criteria andAtmNotBetween(String value1, String value2) {
            addCriterion("atm not between", value1, value2, "atm");
            return (Criteria) this;
        }

        public Criteria andCtIsNull() {
            addCriterion("ct is null");
            return (Criteria) this;
        }

        public Criteria andCtIsNotNull() {
            addCriterion("ct is not null");
            return (Criteria) this;
        }

        public Criteria andCtEqualTo(String value) {
            addCriterion("ct =", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtNotEqualTo(String value) {
            addCriterion("ct <>", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtGreaterThan(String value) {
            addCriterion("ct >", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtGreaterThanOrEqualTo(String value) {
            addCriterion("ct >=", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtLessThan(String value) {
            addCriterion("ct <", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtLessThanOrEqualTo(String value) {
            addCriterion("ct <=", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtLike(String value) {
            addCriterion("ct like", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtNotLike(String value) {
            addCriterion("ct not like", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtIn(List<String> values) {
            addCriterion("ct in", values, "ct");
            return (Criteria) this;
        }

        public Criteria andCtNotIn(List<String> values) {
            addCriterion("ct not in", values, "ct");
            return (Criteria) this;
        }

        public Criteria andCtBetween(String value1, String value2) {
            addCriterion("ct between", value1, value2, "ct");
            return (Criteria) this;
        }

        public Criteria andCtNotBetween(String value1, String value2) {
            addCriterion("ct not between", value1, value2, "ct");
            return (Criteria) this;
        }

        public Criteria andKfgIsNull() {
            addCriterion("kfg is null");
            return (Criteria) this;
        }

        public Criteria andKfgIsNotNull() {
            addCriterion("kfg is not null");
            return (Criteria) this;
        }

        public Criteria andKfgEqualTo(String value) {
            addCriterion("kfg =", value, "kfg");
            return (Criteria) this;
        }

        public Criteria andKfgNotEqualTo(String value) {
            addCriterion("kfg <>", value, "kfg");
            return (Criteria) this;
        }

        public Criteria andKfgGreaterThan(String value) {
            addCriterion("kfg >", value, "kfg");
            return (Criteria) this;
        }

        public Criteria andKfgGreaterThanOrEqualTo(String value) {
            addCriterion("kfg >=", value, "kfg");
            return (Criteria) this;
        }

        public Criteria andKfgLessThan(String value) {
            addCriterion("kfg <", value, "kfg");
            return (Criteria) this;
        }

        public Criteria andKfgLessThanOrEqualTo(String value) {
            addCriterion("kfg <=", value, "kfg");
            return (Criteria) this;
        }

        public Criteria andKfgLike(String value) {
            addCriterion("kfg like", value, "kfg");
            return (Criteria) this;
        }

        public Criteria andKfgNotLike(String value) {
            addCriterion("kfg not like", value, "kfg");
            return (Criteria) this;
        }

        public Criteria andKfgIn(List<String> values) {
            addCriterion("kfg in", values, "kfg");
            return (Criteria) this;
        }

        public Criteria andKfgNotIn(List<String> values) {
            addCriterion("kfg not in", values, "kfg");
            return (Criteria) this;
        }

        public Criteria andKfgBetween(String value1, String value2) {
            addCriterion("kfg between", value1, value2, "kfg");
            return (Criteria) this;
        }

        public Criteria andKfgNotBetween(String value1, String value2) {
            addCriterion("kfg not between", value1, value2, "kfg");
            return (Criteria) this;
        }

        public Criteria andGyIsNull() {
            addCriterion("gy is null");
            return (Criteria) this;
        }

        public Criteria andGyIsNotNull() {
            addCriterion("gy is not null");
            return (Criteria) this;
        }

        public Criteria andGyEqualTo(String value) {
            addCriterion("gy =", value, "gy");
            return (Criteria) this;
        }

        public Criteria andGyNotEqualTo(String value) {
            addCriterion("gy <>", value, "gy");
            return (Criteria) this;
        }

        public Criteria andGyGreaterThan(String value) {
            addCriterion("gy >", value, "gy");
            return (Criteria) this;
        }

        public Criteria andGyGreaterThanOrEqualTo(String value) {
            addCriterion("gy >=", value, "gy");
            return (Criteria) this;
        }

        public Criteria andGyLessThan(String value) {
            addCriterion("gy <", value, "gy");
            return (Criteria) this;
        }

        public Criteria andGyLessThanOrEqualTo(String value) {
            addCriterion("gy <=", value, "gy");
            return (Criteria) this;
        }

        public Criteria andGyLike(String value) {
            addCriterion("gy like", value, "gy");
            return (Criteria) this;
        }

        public Criteria andGyNotLike(String value) {
            addCriterion("gy not like", value, "gy");
            return (Criteria) this;
        }

        public Criteria andGyIn(List<String> values) {
            addCriterion("gy in", values, "gy");
            return (Criteria) this;
        }

        public Criteria andGyNotIn(List<String> values) {
            addCriterion("gy not in", values, "gy");
            return (Criteria) this;
        }

        public Criteria andGyBetween(String value1, String value2) {
            addCriterion("gy between", value1, value2, "gy");
            return (Criteria) this;
        }

        public Criteria andGyNotBetween(String value1, String value2) {
            addCriterion("gy not between", value1, value2, "gy");
            return (Criteria) this;
        }

        public Criteria andDyyIsNull() {
            addCriterion("dyy is null");
            return (Criteria) this;
        }

        public Criteria andDyyIsNotNull() {
            addCriterion("dyy is not null");
            return (Criteria) this;
        }

        public Criteria andDyyEqualTo(String value) {
            addCriterion("dyy =", value, "dyy");
            return (Criteria) this;
        }

        public Criteria andDyyNotEqualTo(String value) {
            addCriterion("dyy <>", value, "dyy");
            return (Criteria) this;
        }

        public Criteria andDyyGreaterThan(String value) {
            addCriterion("dyy >", value, "dyy");
            return (Criteria) this;
        }

        public Criteria andDyyGreaterThanOrEqualTo(String value) {
            addCriterion("dyy >=", value, "dyy");
            return (Criteria) this;
        }

        public Criteria andDyyLessThan(String value) {
            addCriterion("dyy <", value, "dyy");
            return (Criteria) this;
        }

        public Criteria andDyyLessThanOrEqualTo(String value) {
            addCriterion("dyy <=", value, "dyy");
            return (Criteria) this;
        }

        public Criteria andDyyLike(String value) {
            addCriterion("dyy like", value, "dyy");
            return (Criteria) this;
        }

        public Criteria andDyyNotLike(String value) {
            addCriterion("dyy not like", value, "dyy");
            return (Criteria) this;
        }

        public Criteria andDyyIn(List<String> values) {
            addCriterion("dyy in", values, "dyy");
            return (Criteria) this;
        }

        public Criteria andDyyNotIn(List<String> values) {
            addCriterion("dyy not in", values, "dyy");
            return (Criteria) this;
        }

        public Criteria andDyyBetween(String value1, String value2) {
            addCriterion("dyy between", value1, value2, "dyy");
            return (Criteria) this;
        }

        public Criteria andDyyNotBetween(String value1, String value2) {
            addCriterion("dyy not between", value1, value2, "dyy");
            return (Criteria) this;
        }

        public Criteria andJsfIsNull() {
            addCriterion("jsf is null");
            return (Criteria) this;
        }

        public Criteria andJsfIsNotNull() {
            addCriterion("jsf is not null");
            return (Criteria) this;
        }

        public Criteria andJsfEqualTo(String value) {
            addCriterion("jsf =", value, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfNotEqualTo(String value) {
            addCriterion("jsf <>", value, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfGreaterThan(String value) {
            addCriterion("jsf >", value, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfGreaterThanOrEqualTo(String value) {
            addCriterion("jsf >=", value, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfLessThan(String value) {
            addCriterion("jsf <", value, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfLessThanOrEqualTo(String value) {
            addCriterion("jsf <=", value, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfLike(String value) {
            addCriterion("jsf like", value, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfNotLike(String value) {
            addCriterion("jsf not like", value, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfIn(List<String> values) {
            addCriterion("jsf in", values, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfNotIn(List<String> values) {
            addCriterion("jsf not in", values, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfBetween(String value1, String value2) {
            addCriterion("jsf between", value1, value2, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfNotBetween(String value1, String value2) {
            addCriterion("jsf not between", value1, value2, "jsf");
            return (Criteria) this;
        }

        public Criteria andTygIsNull() {
            addCriterion("tyg is null");
            return (Criteria) this;
        }

        public Criteria andTygIsNotNull() {
            addCriterion("tyg is not null");
            return (Criteria) this;
        }

        public Criteria andTygEqualTo(String value) {
            addCriterion("tyg =", value, "tyg");
            return (Criteria) this;
        }

        public Criteria andTygNotEqualTo(String value) {
            addCriterion("tyg <>", value, "tyg");
            return (Criteria) this;
        }

        public Criteria andTygGreaterThan(String value) {
            addCriterion("tyg >", value, "tyg");
            return (Criteria) this;
        }

        public Criteria andTygGreaterThanOrEqualTo(String value) {
            addCriterion("tyg >=", value, "tyg");
            return (Criteria) this;
        }

        public Criteria andTygLessThan(String value) {
            addCriterion("tyg <", value, "tyg");
            return (Criteria) this;
        }

        public Criteria andTygLessThanOrEqualTo(String value) {
            addCriterion("tyg <=", value, "tyg");
            return (Criteria) this;
        }

        public Criteria andTygLike(String value) {
            addCriterion("tyg like", value, "tyg");
            return (Criteria) this;
        }

        public Criteria andTygNotLike(String value) {
            addCriterion("tyg not like", value, "tyg");
            return (Criteria) this;
        }

        public Criteria andTygIn(List<String> values) {
            addCriterion("tyg in", values, "tyg");
            return (Criteria) this;
        }

        public Criteria andTygNotIn(List<String> values) {
            addCriterion("tyg not in", values, "tyg");
            return (Criteria) this;
        }

        public Criteria andTygBetween(String value1, String value2) {
            addCriterion("tyg between", value1, value2, "tyg");
            return (Criteria) this;
        }

        public Criteria andTygNotBetween(String value1, String value2) {
            addCriterion("tyg not between", value1, value2, "tyg");
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