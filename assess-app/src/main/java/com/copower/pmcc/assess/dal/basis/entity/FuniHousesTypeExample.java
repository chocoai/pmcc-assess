package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.List;

public class FuniHousesTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FuniHousesTypeExample() {
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

        public Criteria andDybhIsNull() {
            addCriterion("dybh is null");
            return (Criteria) this;
        }

        public Criteria andDybhIsNotNull() {
            addCriterion("dybh is not null");
            return (Criteria) this;
        }

        public Criteria andDybhEqualTo(Integer value) {
            addCriterion("dybh =", value, "dybh");
            return (Criteria) this;
        }

        public Criteria andDybhNotEqualTo(Integer value) {
            addCriterion("dybh <>", value, "dybh");
            return (Criteria) this;
        }

        public Criteria andDybhGreaterThan(Integer value) {
            addCriterion("dybh >", value, "dybh");
            return (Criteria) this;
        }

        public Criteria andDybhGreaterThanOrEqualTo(Integer value) {
            addCriterion("dybh >=", value, "dybh");
            return (Criteria) this;
        }

        public Criteria andDybhLessThan(Integer value) {
            addCriterion("dybh <", value, "dybh");
            return (Criteria) this;
        }

        public Criteria andDybhLessThanOrEqualTo(Integer value) {
            addCriterion("dybh <=", value, "dybh");
            return (Criteria) this;
        }

        public Criteria andDybhIn(List<Integer> values) {
            addCriterion("dybh in", values, "dybh");
            return (Criteria) this;
        }

        public Criteria andDybhNotIn(List<Integer> values) {
            addCriterion("dybh not in", values, "dybh");
            return (Criteria) this;
        }

        public Criteria andDybhBetween(Integer value1, Integer value2) {
            addCriterion("dybh between", value1, value2, "dybh");
            return (Criteria) this;
        }

        public Criteria andDybhNotBetween(Integer value1, Integer value2) {
            addCriterion("dybh not between", value1, value2, "dybh");
            return (Criteria) this;
        }

        public Criteria andHxIsNull() {
            addCriterion("hx is null");
            return (Criteria) this;
        }

        public Criteria andHxIsNotNull() {
            addCriterion("hx is not null");
            return (Criteria) this;
        }

        public Criteria andHxEqualTo(String value) {
            addCriterion("hx =", value, "hx");
            return (Criteria) this;
        }

        public Criteria andHxNotEqualTo(String value) {
            addCriterion("hx <>", value, "hx");
            return (Criteria) this;
        }

        public Criteria andHxGreaterThan(String value) {
            addCriterion("hx >", value, "hx");
            return (Criteria) this;
        }

        public Criteria andHxGreaterThanOrEqualTo(String value) {
            addCriterion("hx >=", value, "hx");
            return (Criteria) this;
        }

        public Criteria andHxLessThan(String value) {
            addCriterion("hx <", value, "hx");
            return (Criteria) this;
        }

        public Criteria andHxLessThanOrEqualTo(String value) {
            addCriterion("hx <=", value, "hx");
            return (Criteria) this;
        }

        public Criteria andHxLike(String value) {
            addCriterion("hx like", value, "hx");
            return (Criteria) this;
        }

        public Criteria andHxNotLike(String value) {
            addCriterion("hx not like", value, "hx");
            return (Criteria) this;
        }

        public Criteria andHxIn(List<String> values) {
            addCriterion("hx in", values, "hx");
            return (Criteria) this;
        }

        public Criteria andHxNotIn(List<String> values) {
            addCriterion("hx not in", values, "hx");
            return (Criteria) this;
        }

        public Criteria andHxBetween(String value1, String value2) {
            addCriterion("hx between", value1, value2, "hx");
            return (Criteria) this;
        }

        public Criteria andHxNotBetween(String value1, String value2) {
            addCriterion("hx not between", value1, value2, "hx");
            return (Criteria) this;
        }

        public Criteria andMjIsNull() {
            addCriterion("mj is null");
            return (Criteria) this;
        }

        public Criteria andMjIsNotNull() {
            addCriterion("mj is not null");
            return (Criteria) this;
        }

        public Criteria andMjEqualTo(String value) {
            addCriterion("mj =", value, "mj");
            return (Criteria) this;
        }

        public Criteria andMjNotEqualTo(String value) {
            addCriterion("mj <>", value, "mj");
            return (Criteria) this;
        }

        public Criteria andMjGreaterThan(String value) {
            addCriterion("mj >", value, "mj");
            return (Criteria) this;
        }

        public Criteria andMjGreaterThanOrEqualTo(String value) {
            addCriterion("mj >=", value, "mj");
            return (Criteria) this;
        }

        public Criteria andMjLessThan(String value) {
            addCriterion("mj <", value, "mj");
            return (Criteria) this;
        }

        public Criteria andMjLessThanOrEqualTo(String value) {
            addCriterion("mj <=", value, "mj");
            return (Criteria) this;
        }

        public Criteria andMjLike(String value) {
            addCriterion("mj like", value, "mj");
            return (Criteria) this;
        }

        public Criteria andMjNotLike(String value) {
            addCriterion("mj not like", value, "mj");
            return (Criteria) this;
        }

        public Criteria andMjIn(List<String> values) {
            addCriterion("mj in", values, "mj");
            return (Criteria) this;
        }

        public Criteria andMjNotIn(List<String> values) {
            addCriterion("mj not in", values, "mj");
            return (Criteria) this;
        }

        public Criteria andMjBetween(String value1, String value2) {
            addCriterion("mj between", value1, value2, "mj");
            return (Criteria) this;
        }

        public Criteria andMjNotBetween(String value1, String value2) {
            addCriterion("mj not between", value1, value2, "mj");
            return (Criteria) this;
        }

        public Criteria andCxIsNull() {
            addCriterion("cx is null");
            return (Criteria) this;
        }

        public Criteria andCxIsNotNull() {
            addCriterion("cx is not null");
            return (Criteria) this;
        }

        public Criteria andCxEqualTo(String value) {
            addCriterion("cx =", value, "cx");
            return (Criteria) this;
        }

        public Criteria andCxNotEqualTo(String value) {
            addCriterion("cx <>", value, "cx");
            return (Criteria) this;
        }

        public Criteria andCxGreaterThan(String value) {
            addCriterion("cx >", value, "cx");
            return (Criteria) this;
        }

        public Criteria andCxGreaterThanOrEqualTo(String value) {
            addCriterion("cx >=", value, "cx");
            return (Criteria) this;
        }

        public Criteria andCxLessThan(String value) {
            addCriterion("cx <", value, "cx");
            return (Criteria) this;
        }

        public Criteria andCxLessThanOrEqualTo(String value) {
            addCriterion("cx <=", value, "cx");
            return (Criteria) this;
        }

        public Criteria andCxLike(String value) {
            addCriterion("cx like", value, "cx");
            return (Criteria) this;
        }

        public Criteria andCxNotLike(String value) {
            addCriterion("cx not like", value, "cx");
            return (Criteria) this;
        }

        public Criteria andCxIn(List<String> values) {
            addCriterion("cx in", values, "cx");
            return (Criteria) this;
        }

        public Criteria andCxNotIn(List<String> values) {
            addCriterion("cx not in", values, "cx");
            return (Criteria) this;
        }

        public Criteria andCxBetween(String value1, String value2) {
            addCriterion("cx between", value1, value2, "cx");
            return (Criteria) this;
        }

        public Criteria andCxNotBetween(String value1, String value2) {
            addCriterion("cx not between", value1, value2, "cx");
            return (Criteria) this;
        }

        public Criteria andChIsNull() {
            addCriterion("ch is null");
            return (Criteria) this;
        }

        public Criteria andChIsNotNull() {
            addCriterion("ch is not null");
            return (Criteria) this;
        }

        public Criteria andChEqualTo(String value) {
            addCriterion("ch =", value, "ch");
            return (Criteria) this;
        }

        public Criteria andChNotEqualTo(String value) {
            addCriterion("ch <>", value, "ch");
            return (Criteria) this;
        }

        public Criteria andChGreaterThan(String value) {
            addCriterion("ch >", value, "ch");
            return (Criteria) this;
        }

        public Criteria andChGreaterThanOrEqualTo(String value) {
            addCriterion("ch >=", value, "ch");
            return (Criteria) this;
        }

        public Criteria andChLessThan(String value) {
            addCriterion("ch <", value, "ch");
            return (Criteria) this;
        }

        public Criteria andChLessThanOrEqualTo(String value) {
            addCriterion("ch <=", value, "ch");
            return (Criteria) this;
        }

        public Criteria andChLike(String value) {
            addCriterion("ch like", value, "ch");
            return (Criteria) this;
        }

        public Criteria andChNotLike(String value) {
            addCriterion("ch not like", value, "ch");
            return (Criteria) this;
        }

        public Criteria andChIn(List<String> values) {
            addCriterion("ch in", values, "ch");
            return (Criteria) this;
        }

        public Criteria andChNotIn(List<String> values) {
            addCriterion("ch not in", values, "ch");
            return (Criteria) this;
        }

        public Criteria andChBetween(String value1, String value2) {
            addCriterion("ch between", value1, value2, "ch");
            return (Criteria) this;
        }

        public Criteria andChNotBetween(String value1, String value2) {
            addCriterion("ch not between", value1, value2, "ch");
            return (Criteria) this;
        }

        public Criteria andHxtIsNull() {
            addCriterion("hxt is null");
            return (Criteria) this;
        }

        public Criteria andHxtIsNotNull() {
            addCriterion("hxt is not null");
            return (Criteria) this;
        }

        public Criteria andHxtEqualTo(String value) {
            addCriterion("hxt =", value, "hxt");
            return (Criteria) this;
        }

        public Criteria andHxtNotEqualTo(String value) {
            addCriterion("hxt <>", value, "hxt");
            return (Criteria) this;
        }

        public Criteria andHxtGreaterThan(String value) {
            addCriterion("hxt >", value, "hxt");
            return (Criteria) this;
        }

        public Criteria andHxtGreaterThanOrEqualTo(String value) {
            addCriterion("hxt >=", value, "hxt");
            return (Criteria) this;
        }

        public Criteria andHxtLessThan(String value) {
            addCriterion("hxt <", value, "hxt");
            return (Criteria) this;
        }

        public Criteria andHxtLessThanOrEqualTo(String value) {
            addCriterion("hxt <=", value, "hxt");
            return (Criteria) this;
        }

        public Criteria andHxtLike(String value) {
            addCriterion("hxt like", value, "hxt");
            return (Criteria) this;
        }

        public Criteria andHxtNotLike(String value) {
            addCriterion("hxt not like", value, "hxt");
            return (Criteria) this;
        }

        public Criteria andHxtIn(List<String> values) {
            addCriterion("hxt in", values, "hxt");
            return (Criteria) this;
        }

        public Criteria andHxtNotIn(List<String> values) {
            addCriterion("hxt not in", values, "hxt");
            return (Criteria) this;
        }

        public Criteria andHxtBetween(String value1, String value2) {
            addCriterion("hxt between", value1, value2, "hxt");
            return (Criteria) this;
        }

        public Criteria andHxtNotBetween(String value1, String value2) {
            addCriterion("hxt not between", value1, value2, "hxt");
            return (Criteria) this;
        }

        public Criteria andTfIsNull() {
            addCriterion("tf is null");
            return (Criteria) this;
        }

        public Criteria andTfIsNotNull() {
            addCriterion("tf is not null");
            return (Criteria) this;
        }

        public Criteria andTfEqualTo(String value) {
            addCriterion("tf =", value, "tf");
            return (Criteria) this;
        }

        public Criteria andTfNotEqualTo(String value) {
            addCriterion("tf <>", value, "tf");
            return (Criteria) this;
        }

        public Criteria andTfGreaterThan(String value) {
            addCriterion("tf >", value, "tf");
            return (Criteria) this;
        }

        public Criteria andTfGreaterThanOrEqualTo(String value) {
            addCriterion("tf >=", value, "tf");
            return (Criteria) this;
        }

        public Criteria andTfLessThan(String value) {
            addCriterion("tf <", value, "tf");
            return (Criteria) this;
        }

        public Criteria andTfLessThanOrEqualTo(String value) {
            addCriterion("tf <=", value, "tf");
            return (Criteria) this;
        }

        public Criteria andTfLike(String value) {
            addCriterion("tf like", value, "tf");
            return (Criteria) this;
        }

        public Criteria andTfNotLike(String value) {
            addCriterion("tf not like", value, "tf");
            return (Criteria) this;
        }

        public Criteria andTfIn(List<String> values) {
            addCriterion("tf in", values, "tf");
            return (Criteria) this;
        }

        public Criteria andTfNotIn(List<String> values) {
            addCriterion("tf not in", values, "tf");
            return (Criteria) this;
        }

        public Criteria andTfBetween(String value1, String value2) {
            addCriterion("tf between", value1, value2, "tf");
            return (Criteria) this;
        }

        public Criteria andTfNotBetween(String value1, String value2) {
            addCriterion("tf not between", value1, value2, "tf");
            return (Criteria) this;
        }

        public Criteria andGzIsNull() {
            addCriterion("gz is null");
            return (Criteria) this;
        }

        public Criteria andGzIsNotNull() {
            addCriterion("gz is not null");
            return (Criteria) this;
        }

        public Criteria andGzEqualTo(String value) {
            addCriterion("gz =", value, "gz");
            return (Criteria) this;
        }

        public Criteria andGzNotEqualTo(String value) {
            addCriterion("gz <>", value, "gz");
            return (Criteria) this;
        }

        public Criteria andGzGreaterThan(String value) {
            addCriterion("gz >", value, "gz");
            return (Criteria) this;
        }

        public Criteria andGzGreaterThanOrEqualTo(String value) {
            addCriterion("gz >=", value, "gz");
            return (Criteria) this;
        }

        public Criteria andGzLessThan(String value) {
            addCriterion("gz <", value, "gz");
            return (Criteria) this;
        }

        public Criteria andGzLessThanOrEqualTo(String value) {
            addCriterion("gz <=", value, "gz");
            return (Criteria) this;
        }

        public Criteria andGzLike(String value) {
            addCriterion("gz like", value, "gz");
            return (Criteria) this;
        }

        public Criteria andGzNotLike(String value) {
            addCriterion("gz not like", value, "gz");
            return (Criteria) this;
        }

        public Criteria andGzIn(List<String> values) {
            addCriterion("gz in", values, "gz");
            return (Criteria) this;
        }

        public Criteria andGzNotIn(List<String> values) {
            addCriterion("gz not in", values, "gz");
            return (Criteria) this;
        }

        public Criteria andGzBetween(String value1, String value2) {
            addCriterion("gz between", value1, value2, "gz");
            return (Criteria) this;
        }

        public Criteria andGzNotBetween(String value1, String value2) {
            addCriterion("gz not between", value1, value2, "gz");
            return (Criteria) this;
        }

        public Criteria andZxqkIsNull() {
            addCriterion("zxqk is null");
            return (Criteria) this;
        }

        public Criteria andZxqkIsNotNull() {
            addCriterion("zxqk is not null");
            return (Criteria) this;
        }

        public Criteria andZxqkEqualTo(String value) {
            addCriterion("zxqk =", value, "zxqk");
            return (Criteria) this;
        }

        public Criteria andZxqkNotEqualTo(String value) {
            addCriterion("zxqk <>", value, "zxqk");
            return (Criteria) this;
        }

        public Criteria andZxqkGreaterThan(String value) {
            addCriterion("zxqk >", value, "zxqk");
            return (Criteria) this;
        }

        public Criteria andZxqkGreaterThanOrEqualTo(String value) {
            addCriterion("zxqk >=", value, "zxqk");
            return (Criteria) this;
        }

        public Criteria andZxqkLessThan(String value) {
            addCriterion("zxqk <", value, "zxqk");
            return (Criteria) this;
        }

        public Criteria andZxqkLessThanOrEqualTo(String value) {
            addCriterion("zxqk <=", value, "zxqk");
            return (Criteria) this;
        }

        public Criteria andZxqkLike(String value) {
            addCriterion("zxqk like", value, "zxqk");
            return (Criteria) this;
        }

        public Criteria andZxqkNotLike(String value) {
            addCriterion("zxqk not like", value, "zxqk");
            return (Criteria) this;
        }

        public Criteria andZxqkIn(List<String> values) {
            addCriterion("zxqk in", values, "zxqk");
            return (Criteria) this;
        }

        public Criteria andZxqkNotIn(List<String> values) {
            addCriterion("zxqk not in", values, "zxqk");
            return (Criteria) this;
        }

        public Criteria andZxqkBetween(String value1, String value2) {
            addCriterion("zxqk between", value1, value2, "zxqk");
            return (Criteria) this;
        }

        public Criteria andZxqkNotBetween(String value1, String value2) {
            addCriterion("zxqk not between", value1, value2, "zxqk");
            return (Criteria) this;
        }

        public Criteria andCfIsNull() {
            addCriterion("cf is null");
            return (Criteria) this;
        }

        public Criteria andCfIsNotNull() {
            addCriterion("cf is not null");
            return (Criteria) this;
        }

        public Criteria andCfEqualTo(String value) {
            addCriterion("cf =", value, "cf");
            return (Criteria) this;
        }

        public Criteria andCfNotEqualTo(String value) {
            addCriterion("cf <>", value, "cf");
            return (Criteria) this;
        }

        public Criteria andCfGreaterThan(String value) {
            addCriterion("cf >", value, "cf");
            return (Criteria) this;
        }

        public Criteria andCfGreaterThanOrEqualTo(String value) {
            addCriterion("cf >=", value, "cf");
            return (Criteria) this;
        }

        public Criteria andCfLessThan(String value) {
            addCriterion("cf <", value, "cf");
            return (Criteria) this;
        }

        public Criteria andCfLessThanOrEqualTo(String value) {
            addCriterion("cf <=", value, "cf");
            return (Criteria) this;
        }

        public Criteria andCfLike(String value) {
            addCriterion("cf like", value, "cf");
            return (Criteria) this;
        }

        public Criteria andCfNotLike(String value) {
            addCriterion("cf not like", value, "cf");
            return (Criteria) this;
        }

        public Criteria andCfIn(List<String> values) {
            addCriterion("cf in", values, "cf");
            return (Criteria) this;
        }

        public Criteria andCfNotIn(List<String> values) {
            addCriterion("cf not in", values, "cf");
            return (Criteria) this;
        }

        public Criteria andCfBetween(String value1, String value2) {
            addCriterion("cf between", value1, value2, "cf");
            return (Criteria) this;
        }

        public Criteria andCfNotBetween(String value1, String value2) {
            addCriterion("cf not between", value1, value2, "cf");
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

        public Criteria andFlggqyIsNull() {
            addCriterion("flggqy is null");
            return (Criteria) this;
        }

        public Criteria andFlggqyIsNotNull() {
            addCriterion("flggqy is not null");
            return (Criteria) this;
        }

        public Criteria andFlggqyEqualTo(String value) {
            addCriterion("flggqy =", value, "flggqy");
            return (Criteria) this;
        }

        public Criteria andFlggqyNotEqualTo(String value) {
            addCriterion("flggqy <>", value, "flggqy");
            return (Criteria) this;
        }

        public Criteria andFlggqyGreaterThan(String value) {
            addCriterion("flggqy >", value, "flggqy");
            return (Criteria) this;
        }

        public Criteria andFlggqyGreaterThanOrEqualTo(String value) {
            addCriterion("flggqy >=", value, "flggqy");
            return (Criteria) this;
        }

        public Criteria andFlggqyLessThan(String value) {
            addCriterion("flggqy <", value, "flggqy");
            return (Criteria) this;
        }

        public Criteria andFlggqyLessThanOrEqualTo(String value) {
            addCriterion("flggqy <=", value, "flggqy");
            return (Criteria) this;
        }

        public Criteria andFlggqyLike(String value) {
            addCriterion("flggqy like", value, "flggqy");
            return (Criteria) this;
        }

        public Criteria andFlggqyNotLike(String value) {
            addCriterion("flggqy not like", value, "flggqy");
            return (Criteria) this;
        }

        public Criteria andFlggqyIn(List<String> values) {
            addCriterion("flggqy in", values, "flggqy");
            return (Criteria) this;
        }

        public Criteria andFlggqyNotIn(List<String> values) {
            addCriterion("flggqy not in", values, "flggqy");
            return (Criteria) this;
        }

        public Criteria andFlggqyBetween(String value1, String value2) {
            addCriterion("flggqy between", value1, value2, "flggqy");
            return (Criteria) this;
        }

        public Criteria andFlggqyNotBetween(String value1, String value2) {
            addCriterion("flggqy not between", value1, value2, "flggqy");
            return (Criteria) this;
        }

        public Criteria andFjIsNull() {
            addCriterion("fj is null");
            return (Criteria) this;
        }

        public Criteria andFjIsNotNull() {
            addCriterion("fj is not null");
            return (Criteria) this;
        }

        public Criteria andFjEqualTo(String value) {
            addCriterion("fj =", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjNotEqualTo(String value) {
            addCriterion("fj <>", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjGreaterThan(String value) {
            addCriterion("fj >", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjGreaterThanOrEqualTo(String value) {
            addCriterion("fj >=", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjLessThan(String value) {
            addCriterion("fj <", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjLessThanOrEqualTo(String value) {
            addCriterion("fj <=", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjLike(String value) {
            addCriterion("fj like", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjNotLike(String value) {
            addCriterion("fj not like", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjIn(List<String> values) {
            addCriterion("fj in", values, "fj");
            return (Criteria) this;
        }

        public Criteria andFjNotIn(List<String> values) {
            addCriterion("fj not in", values, "fj");
            return (Criteria) this;
        }

        public Criteria andFjBetween(String value1, String value2) {
            addCriterion("fj between", value1, value2, "fj");
            return (Criteria) this;
        }

        public Criteria andFjNotBetween(String value1, String value2) {
            addCriterion("fj not between", value1, value2, "fj");
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