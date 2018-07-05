package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.List;

public class FuniHousesSitingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FuniHousesSitingExample() {
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

        public Criteria andLcIsNull() {
            addCriterion("lc is null");
            return (Criteria) this;
        }

        public Criteria andLcIsNotNull() {
            addCriterion("lc is not null");
            return (Criteria) this;
        }

        public Criteria andLcEqualTo(String value) {
            addCriterion("lc =", value, "lc");
            return (Criteria) this;
        }

        public Criteria andLcNotEqualTo(String value) {
            addCriterion("lc <>", value, "lc");
            return (Criteria) this;
        }

        public Criteria andLcGreaterThan(String value) {
            addCriterion("lc >", value, "lc");
            return (Criteria) this;
        }

        public Criteria andLcGreaterThanOrEqualTo(String value) {
            addCriterion("lc >=", value, "lc");
            return (Criteria) this;
        }

        public Criteria andLcLessThan(String value) {
            addCriterion("lc <", value, "lc");
            return (Criteria) this;
        }

        public Criteria andLcLessThanOrEqualTo(String value) {
            addCriterion("lc <=", value, "lc");
            return (Criteria) this;
        }

        public Criteria andLcLike(String value) {
            addCriterion("lc like", value, "lc");
            return (Criteria) this;
        }

        public Criteria andLcNotLike(String value) {
            addCriterion("lc not like", value, "lc");
            return (Criteria) this;
        }

        public Criteria andLcIn(List<String> values) {
            addCriterion("lc in", values, "lc");
            return (Criteria) this;
        }

        public Criteria andLcNotIn(List<String> values) {
            addCriterion("lc not in", values, "lc");
            return (Criteria) this;
        }

        public Criteria andLcBetween(String value1, String value2) {
            addCriterion("lc between", value1, value2, "lc");
            return (Criteria) this;
        }

        public Criteria andLcNotBetween(String value1, String value2) {
            addCriterion("lc not between", value1, value2, "lc");
            return (Criteria) this;
        }

        public Criteria andMphIsNull() {
            addCriterion("mph is null");
            return (Criteria) this;
        }

        public Criteria andMphIsNotNull() {
            addCriterion("mph is not null");
            return (Criteria) this;
        }

        public Criteria andMphEqualTo(String value) {
            addCriterion("mph =", value, "mph");
            return (Criteria) this;
        }

        public Criteria andMphNotEqualTo(String value) {
            addCriterion("mph <>", value, "mph");
            return (Criteria) this;
        }

        public Criteria andMphGreaterThan(String value) {
            addCriterion("mph >", value, "mph");
            return (Criteria) this;
        }

        public Criteria andMphGreaterThanOrEqualTo(String value) {
            addCriterion("mph >=", value, "mph");
            return (Criteria) this;
        }

        public Criteria andMphLessThan(String value) {
            addCriterion("mph <", value, "mph");
            return (Criteria) this;
        }

        public Criteria andMphLessThanOrEqualTo(String value) {
            addCriterion("mph <=", value, "mph");
            return (Criteria) this;
        }

        public Criteria andMphLike(String value) {
            addCriterion("mph like", value, "mph");
            return (Criteria) this;
        }

        public Criteria andMphNotLike(String value) {
            addCriterion("mph not like", value, "mph");
            return (Criteria) this;
        }

        public Criteria andMphIn(List<String> values) {
            addCriterion("mph in", values, "mph");
            return (Criteria) this;
        }

        public Criteria andMphNotIn(List<String> values) {
            addCriterion("mph not in", values, "mph");
            return (Criteria) this;
        }

        public Criteria andMphBetween(String value1, String value2) {
            addCriterion("mph between", value1, value2, "mph");
            return (Criteria) this;
        }

        public Criteria andMphNotBetween(String value1, String value2) {
            addCriterion("mph not between", value1, value2, "mph");
            return (Criteria) this;
        }

        public Criteria andFxbhIsNull() {
            addCriterion("fxbh is null");
            return (Criteria) this;
        }

        public Criteria andFxbhIsNotNull() {
            addCriterion("fxbh is not null");
            return (Criteria) this;
        }

        public Criteria andFxbhEqualTo(Integer value) {
            addCriterion("fxbh =", value, "fxbh");
            return (Criteria) this;
        }

        public Criteria andFxbhNotEqualTo(Integer value) {
            addCriterion("fxbh <>", value, "fxbh");
            return (Criteria) this;
        }

        public Criteria andFxbhGreaterThan(Integer value) {
            addCriterion("fxbh >", value, "fxbh");
            return (Criteria) this;
        }

        public Criteria andFxbhGreaterThanOrEqualTo(Integer value) {
            addCriterion("fxbh >=", value, "fxbh");
            return (Criteria) this;
        }

        public Criteria andFxbhLessThan(Integer value) {
            addCriterion("fxbh <", value, "fxbh");
            return (Criteria) this;
        }

        public Criteria andFxbhLessThanOrEqualTo(Integer value) {
            addCriterion("fxbh <=", value, "fxbh");
            return (Criteria) this;
        }

        public Criteria andFxbhIn(List<Integer> values) {
            addCriterion("fxbh in", values, "fxbh");
            return (Criteria) this;
        }

        public Criteria andFxbhNotIn(List<Integer> values) {
            addCriterion("fxbh not in", values, "fxbh");
            return (Criteria) this;
        }

        public Criteria andFxbhBetween(Integer value1, Integer value2) {
            addCriterion("fxbh between", value1, value2, "fxbh");
            return (Criteria) this;
        }

        public Criteria andFxbhNotBetween(Integer value1, Integer value2) {
            addCriterion("fxbh not between", value1, value2, "fxbh");
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

        public Criteria andGpsqkIsNull() {
            addCriterion("gpsqk is null");
            return (Criteria) this;
        }

        public Criteria andGpsqkIsNotNull() {
            addCriterion("gpsqk is not null");
            return (Criteria) this;
        }

        public Criteria andGpsqkEqualTo(String value) {
            addCriterion("gpsqk =", value, "gpsqk");
            return (Criteria) this;
        }

        public Criteria andGpsqkNotEqualTo(String value) {
            addCriterion("gpsqk <>", value, "gpsqk");
            return (Criteria) this;
        }

        public Criteria andGpsqkGreaterThan(String value) {
            addCriterion("gpsqk >", value, "gpsqk");
            return (Criteria) this;
        }

        public Criteria andGpsqkGreaterThanOrEqualTo(String value) {
            addCriterion("gpsqk >=", value, "gpsqk");
            return (Criteria) this;
        }

        public Criteria andGpsqkLessThan(String value) {
            addCriterion("gpsqk <", value, "gpsqk");
            return (Criteria) this;
        }

        public Criteria andGpsqkLessThanOrEqualTo(String value) {
            addCriterion("gpsqk <=", value, "gpsqk");
            return (Criteria) this;
        }

        public Criteria andGpsqkLike(String value) {
            addCriterion("gpsqk like", value, "gpsqk");
            return (Criteria) this;
        }

        public Criteria andGpsqkNotLike(String value) {
            addCriterion("gpsqk not like", value, "gpsqk");
            return (Criteria) this;
        }

        public Criteria andGpsqkIn(List<String> values) {
            addCriterion("gpsqk in", values, "gpsqk");
            return (Criteria) this;
        }

        public Criteria andGpsqkNotIn(List<String> values) {
            addCriterion("gpsqk not in", values, "gpsqk");
            return (Criteria) this;
        }

        public Criteria andGpsqkBetween(String value1, String value2) {
            addCriterion("gpsqk between", value1, value2, "gpsqk");
            return (Criteria) this;
        }

        public Criteria andGpsqkNotBetween(String value1, String value2) {
            addCriterion("gpsqk not between", value1, value2, "gpsqk");
            return (Criteria) this;
        }

        public Criteria andFwsjytIsNull() {
            addCriterion("fwsjyt is null");
            return (Criteria) this;
        }

        public Criteria andFwsjytIsNotNull() {
            addCriterion("fwsjyt is not null");
            return (Criteria) this;
        }

        public Criteria andFwsjytEqualTo(String value) {
            addCriterion("fwsjyt =", value, "fwsjyt");
            return (Criteria) this;
        }

        public Criteria andFwsjytNotEqualTo(String value) {
            addCriterion("fwsjyt <>", value, "fwsjyt");
            return (Criteria) this;
        }

        public Criteria andFwsjytGreaterThan(String value) {
            addCriterion("fwsjyt >", value, "fwsjyt");
            return (Criteria) this;
        }

        public Criteria andFwsjytGreaterThanOrEqualTo(String value) {
            addCriterion("fwsjyt >=", value, "fwsjyt");
            return (Criteria) this;
        }

        public Criteria andFwsjytLessThan(String value) {
            addCriterion("fwsjyt <", value, "fwsjyt");
            return (Criteria) this;
        }

        public Criteria andFwsjytLessThanOrEqualTo(String value) {
            addCriterion("fwsjyt <=", value, "fwsjyt");
            return (Criteria) this;
        }

        public Criteria andFwsjytLike(String value) {
            addCriterion("fwsjyt like", value, "fwsjyt");
            return (Criteria) this;
        }

        public Criteria andFwsjytNotLike(String value) {
            addCriterion("fwsjyt not like", value, "fwsjyt");
            return (Criteria) this;
        }

        public Criteria andFwsjytIn(List<String> values) {
            addCriterion("fwsjyt in", values, "fwsjyt");
            return (Criteria) this;
        }

        public Criteria andFwsjytNotIn(List<String> values) {
            addCriterion("fwsjyt not in", values, "fwsjyt");
            return (Criteria) this;
        }

        public Criteria andFwsjytBetween(String value1, String value2) {
            addCriterion("fwsjyt between", value1, value2, "fwsjyt");
            return (Criteria) this;
        }

        public Criteria andFwsjytNotBetween(String value1, String value2) {
            addCriterion("fwsjyt not between", value1, value2, "fwsjyt");
            return (Criteria) this;
        }

        public Criteria andDlIsNull() {
            addCriterion("dl is null");
            return (Criteria) this;
        }

        public Criteria andDlIsNotNull() {
            addCriterion("dl is not null");
            return (Criteria) this;
        }

        public Criteria andDlEqualTo(String value) {
            addCriterion("dl =", value, "dl");
            return (Criteria) this;
        }

        public Criteria andDlNotEqualTo(String value) {
            addCriterion("dl <>", value, "dl");
            return (Criteria) this;
        }

        public Criteria andDlGreaterThan(String value) {
            addCriterion("dl >", value, "dl");
            return (Criteria) this;
        }

        public Criteria andDlGreaterThanOrEqualTo(String value) {
            addCriterion("dl >=", value, "dl");
            return (Criteria) this;
        }

        public Criteria andDlLessThan(String value) {
            addCriterion("dl <", value, "dl");
            return (Criteria) this;
        }

        public Criteria andDlLessThanOrEqualTo(String value) {
            addCriterion("dl <=", value, "dl");
            return (Criteria) this;
        }

        public Criteria andDlLike(String value) {
            addCriterion("dl like", value, "dl");
            return (Criteria) this;
        }

        public Criteria andDlNotLike(String value) {
            addCriterion("dl not like", value, "dl");
            return (Criteria) this;
        }

        public Criteria andDlIn(List<String> values) {
            addCriterion("dl in", values, "dl");
            return (Criteria) this;
        }

        public Criteria andDlNotIn(List<String> values) {
            addCriterion("dl not in", values, "dl");
            return (Criteria) this;
        }

        public Criteria andDlBetween(String value1, String value2) {
            addCriterion("dl between", value1, value2, "dl");
            return (Criteria) this;
        }

        public Criteria andDlNotBetween(String value1, String value2) {
            addCriterion("dl not between", value1, value2, "dl");
            return (Criteria) this;
        }

        public Criteria andTxIsNull() {
            addCriterion("tx is null");
            return (Criteria) this;
        }

        public Criteria andTxIsNotNull() {
            addCriterion("tx is not null");
            return (Criteria) this;
        }

        public Criteria andTxEqualTo(String value) {
            addCriterion("tx =", value, "tx");
            return (Criteria) this;
        }

        public Criteria andTxNotEqualTo(String value) {
            addCriterion("tx <>", value, "tx");
            return (Criteria) this;
        }

        public Criteria andTxGreaterThan(String value) {
            addCriterion("tx >", value, "tx");
            return (Criteria) this;
        }

        public Criteria andTxGreaterThanOrEqualTo(String value) {
            addCriterion("tx >=", value, "tx");
            return (Criteria) this;
        }

        public Criteria andTxLessThan(String value) {
            addCriterion("tx <", value, "tx");
            return (Criteria) this;
        }

        public Criteria andTxLessThanOrEqualTo(String value) {
            addCriterion("tx <=", value, "tx");
            return (Criteria) this;
        }

        public Criteria andTxLike(String value) {
            addCriterion("tx like", value, "tx");
            return (Criteria) this;
        }

        public Criteria andTxNotLike(String value) {
            addCriterion("tx not like", value, "tx");
            return (Criteria) this;
        }

        public Criteria andTxIn(List<String> values) {
            addCriterion("tx in", values, "tx");
            return (Criteria) this;
        }

        public Criteria andTxNotIn(List<String> values) {
            addCriterion("tx not in", values, "tx");
            return (Criteria) this;
        }

        public Criteria andTxBetween(String value1, String value2) {
            addCriterion("tx between", value1, value2, "tx");
            return (Criteria) this;
        }

        public Criteria andTxNotBetween(String value1, String value2) {
            addCriterion("tx not between", value1, value2, "tx");
            return (Criteria) this;
        }

        public Criteria andWlIsNull() {
            addCriterion("wl is null");
            return (Criteria) this;
        }

        public Criteria andWlIsNotNull() {
            addCriterion("wl is not null");
            return (Criteria) this;
        }

        public Criteria andWlEqualTo(String value) {
            addCriterion("wl =", value, "wl");
            return (Criteria) this;
        }

        public Criteria andWlNotEqualTo(String value) {
            addCriterion("wl <>", value, "wl");
            return (Criteria) this;
        }

        public Criteria andWlGreaterThan(String value) {
            addCriterion("wl >", value, "wl");
            return (Criteria) this;
        }

        public Criteria andWlGreaterThanOrEqualTo(String value) {
            addCriterion("wl >=", value, "wl");
            return (Criteria) this;
        }

        public Criteria andWlLessThan(String value) {
            addCriterion("wl <", value, "wl");
            return (Criteria) this;
        }

        public Criteria andWlLessThanOrEqualTo(String value) {
            addCriterion("wl <=", value, "wl");
            return (Criteria) this;
        }

        public Criteria andWlLike(String value) {
            addCriterion("wl like", value, "wl");
            return (Criteria) this;
        }

        public Criteria andWlNotLike(String value) {
            addCriterion("wl not like", value, "wl");
            return (Criteria) this;
        }

        public Criteria andWlIn(List<String> values) {
            addCriterion("wl in", values, "wl");
            return (Criteria) this;
        }

        public Criteria andWlNotIn(List<String> values) {
            addCriterion("wl not in", values, "wl");
            return (Criteria) this;
        }

        public Criteria andWlBetween(String value1, String value2) {
            addCriterion("wl between", value1, value2, "wl");
            return (Criteria) this;
        }

        public Criteria andWlNotBetween(String value1, String value2) {
            addCriterion("wl not between", value1, value2, "wl");
            return (Criteria) this;
        }

        public Criteria andJtznxtIsNull() {
            addCriterion("jtznxt is null");
            return (Criteria) this;
        }

        public Criteria andJtznxtIsNotNull() {
            addCriterion("jtznxt is not null");
            return (Criteria) this;
        }

        public Criteria andJtznxtEqualTo(String value) {
            addCriterion("jtznxt =", value, "jtznxt");
            return (Criteria) this;
        }

        public Criteria andJtznxtNotEqualTo(String value) {
            addCriterion("jtznxt <>", value, "jtznxt");
            return (Criteria) this;
        }

        public Criteria andJtznxtGreaterThan(String value) {
            addCriterion("jtznxt >", value, "jtznxt");
            return (Criteria) this;
        }

        public Criteria andJtznxtGreaterThanOrEqualTo(String value) {
            addCriterion("jtznxt >=", value, "jtznxt");
            return (Criteria) this;
        }

        public Criteria andJtznxtLessThan(String value) {
            addCriterion("jtznxt <", value, "jtznxt");
            return (Criteria) this;
        }

        public Criteria andJtznxtLessThanOrEqualTo(String value) {
            addCriterion("jtznxt <=", value, "jtznxt");
            return (Criteria) this;
        }

        public Criteria andJtznxtLike(String value) {
            addCriterion("jtznxt like", value, "jtznxt");
            return (Criteria) this;
        }

        public Criteria andJtznxtNotLike(String value) {
            addCriterion("jtznxt not like", value, "jtznxt");
            return (Criteria) this;
        }

        public Criteria andJtznxtIn(List<String> values) {
            addCriterion("jtznxt in", values, "jtznxt");
            return (Criteria) this;
        }

        public Criteria andJtznxtNotIn(List<String> values) {
            addCriterion("jtznxt not in", values, "jtznxt");
            return (Criteria) this;
        }

        public Criteria andJtznxtBetween(String value1, String value2) {
            addCriterion("jtznxt between", value1, value2, "jtznxt");
            return (Criteria) this;
        }

        public Criteria andJtznxtNotBetween(String value1, String value2) {
            addCriterion("jtznxt not between", value1, value2, "jtznxt");
            return (Criteria) this;
        }

        public Criteria andZjycjysjIsNull() {
            addCriterion("zjycjysj is null");
            return (Criteria) this;
        }

        public Criteria andZjycjysjIsNotNull() {
            addCriterion("zjycjysj is not null");
            return (Criteria) this;
        }

        public Criteria andZjycjysjEqualTo(String value) {
            addCriterion("zjycjysj =", value, "zjycjysj");
            return (Criteria) this;
        }

        public Criteria andZjycjysjNotEqualTo(String value) {
            addCriterion("zjycjysj <>", value, "zjycjysj");
            return (Criteria) this;
        }

        public Criteria andZjycjysjGreaterThan(String value) {
            addCriterion("zjycjysj >", value, "zjycjysj");
            return (Criteria) this;
        }

        public Criteria andZjycjysjGreaterThanOrEqualTo(String value) {
            addCriterion("zjycjysj >=", value, "zjycjysj");
            return (Criteria) this;
        }

        public Criteria andZjycjysjLessThan(String value) {
            addCriterion("zjycjysj <", value, "zjycjysj");
            return (Criteria) this;
        }

        public Criteria andZjycjysjLessThanOrEqualTo(String value) {
            addCriterion("zjycjysj <=", value, "zjycjysj");
            return (Criteria) this;
        }

        public Criteria andZjycjysjLike(String value) {
            addCriterion("zjycjysj like", value, "zjycjysj");
            return (Criteria) this;
        }

        public Criteria andZjycjysjNotLike(String value) {
            addCriterion("zjycjysj not like", value, "zjycjysj");
            return (Criteria) this;
        }

        public Criteria andZjycjysjIn(List<String> values) {
            addCriterion("zjycjysj in", values, "zjycjysj");
            return (Criteria) this;
        }

        public Criteria andZjycjysjNotIn(List<String> values) {
            addCriterion("zjycjysj not in", values, "zjycjysj");
            return (Criteria) this;
        }

        public Criteria andZjycjysjBetween(String value1, String value2) {
            addCriterion("zjycjysj between", value1, value2, "zjycjysj");
            return (Criteria) this;
        }

        public Criteria andZjycjysjNotBetween(String value1, String value2) {
            addCriterion("zjycjysj not between", value1, value2, "zjycjysj");
            return (Criteria) this;
        }

        public Criteria andZjycjyjgIsNull() {
            addCriterion("zjycjyjg is null");
            return (Criteria) this;
        }

        public Criteria andZjycjyjgIsNotNull() {
            addCriterion("zjycjyjg is not null");
            return (Criteria) this;
        }

        public Criteria andZjycjyjgEqualTo(String value) {
            addCriterion("zjycjyjg =", value, "zjycjyjg");
            return (Criteria) this;
        }

        public Criteria andZjycjyjgNotEqualTo(String value) {
            addCriterion("zjycjyjg <>", value, "zjycjyjg");
            return (Criteria) this;
        }

        public Criteria andZjycjyjgGreaterThan(String value) {
            addCriterion("zjycjyjg >", value, "zjycjyjg");
            return (Criteria) this;
        }

        public Criteria andZjycjyjgGreaterThanOrEqualTo(String value) {
            addCriterion("zjycjyjg >=", value, "zjycjyjg");
            return (Criteria) this;
        }

        public Criteria andZjycjyjgLessThan(String value) {
            addCriterion("zjycjyjg <", value, "zjycjyjg");
            return (Criteria) this;
        }

        public Criteria andZjycjyjgLessThanOrEqualTo(String value) {
            addCriterion("zjycjyjg <=", value, "zjycjyjg");
            return (Criteria) this;
        }

        public Criteria andZjycjyjgLike(String value) {
            addCriterion("zjycjyjg like", value, "zjycjyjg");
            return (Criteria) this;
        }

        public Criteria andZjycjyjgNotLike(String value) {
            addCriterion("zjycjyjg not like", value, "zjycjyjg");
            return (Criteria) this;
        }

        public Criteria andZjycjyjgIn(List<String> values) {
            addCriterion("zjycjyjg in", values, "zjycjyjg");
            return (Criteria) this;
        }

        public Criteria andZjycjyjgNotIn(List<String> values) {
            addCriterion("zjycjyjg not in", values, "zjycjyjg");
            return (Criteria) this;
        }

        public Criteria andZjycjyjgBetween(String value1, String value2) {
            addCriterion("zjycjyjg between", value1, value2, "zjycjyjg");
            return (Criteria) this;
        }

        public Criteria andZjycjyjgNotBetween(String value1, String value2) {
            addCriterion("zjycjyjg not between", value1, value2, "zjycjyjg");
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