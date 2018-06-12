package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.List;

public class FuniHousesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FuniHousesExample() {
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

        public Criteria andLpmcIsNull() {
            addCriterion("lpmc is null");
            return (Criteria) this;
        }

        public Criteria andLpmcIsNotNull() {
            addCriterion("lpmc is not null");
            return (Criteria) this;
        }

        public Criteria andLpmcEqualTo(String value) {
            addCriterion("lpmc =", value, "lpmc");
            return (Criteria) this;
        }

        public Criteria andLpmcNotEqualTo(String value) {
            addCriterion("lpmc <>", value, "lpmc");
            return (Criteria) this;
        }

        public Criteria andLpmcGreaterThan(String value) {
            addCriterion("lpmc >", value, "lpmc");
            return (Criteria) this;
        }

        public Criteria andLpmcGreaterThanOrEqualTo(String value) {
            addCriterion("lpmc >=", value, "lpmc");
            return (Criteria) this;
        }

        public Criteria andLpmcLessThan(String value) {
            addCriterion("lpmc <", value, "lpmc");
            return (Criteria) this;
        }

        public Criteria andLpmcLessThanOrEqualTo(String value) {
            addCriterion("lpmc <=", value, "lpmc");
            return (Criteria) this;
        }

        public Criteria andLpmcLike(String value) {
            addCriterion("lpmc like", value, "lpmc");
            return (Criteria) this;
        }

        public Criteria andLpmcNotLike(String value) {
            addCriterion("lpmc not like", value, "lpmc");
            return (Criteria) this;
        }

        public Criteria andLpmcIn(List<String> values) {
            addCriterion("lpmc in", values, "lpmc");
            return (Criteria) this;
        }

        public Criteria andLpmcNotIn(List<String> values) {
            addCriterion("lpmc not in", values, "lpmc");
            return (Criteria) this;
        }

        public Criteria andLpmcBetween(String value1, String value2) {
            addCriterion("lpmc between", value1, value2, "lpmc");
            return (Criteria) this;
        }

        public Criteria andLpmcNotBetween(String value1, String value2) {
            addCriterion("lpmc not between", value1, value2, "lpmc");
            return (Criteria) this;
        }

        public Criteria andJzmjIsNull() {
            addCriterion("jzmj is null");
            return (Criteria) this;
        }

        public Criteria andJzmjIsNotNull() {
            addCriterion("jzmj is not null");
            return (Criteria) this;
        }

        public Criteria andJzmjEqualTo(String value) {
            addCriterion("jzmj =", value, "jzmj");
            return (Criteria) this;
        }

        public Criteria andJzmjNotEqualTo(String value) {
            addCriterion("jzmj <>", value, "jzmj");
            return (Criteria) this;
        }

        public Criteria andJzmjGreaterThan(String value) {
            addCriterion("jzmj >", value, "jzmj");
            return (Criteria) this;
        }

        public Criteria andJzmjGreaterThanOrEqualTo(String value) {
            addCriterion("jzmj >=", value, "jzmj");
            return (Criteria) this;
        }

        public Criteria andJzmjLessThan(String value) {
            addCriterion("jzmj <", value, "jzmj");
            return (Criteria) this;
        }

        public Criteria andJzmjLessThanOrEqualTo(String value) {
            addCriterion("jzmj <=", value, "jzmj");
            return (Criteria) this;
        }

        public Criteria andJzmjLike(String value) {
            addCriterion("jzmj like", value, "jzmj");
            return (Criteria) this;
        }

        public Criteria andJzmjNotLike(String value) {
            addCriterion("jzmj not like", value, "jzmj");
            return (Criteria) this;
        }

        public Criteria andJzmjIn(List<String> values) {
            addCriterion("jzmj in", values, "jzmj");
            return (Criteria) this;
        }

        public Criteria andJzmjNotIn(List<String> values) {
            addCriterion("jzmj not in", values, "jzmj");
            return (Criteria) this;
        }

        public Criteria andJzmjBetween(String value1, String value2) {
            addCriterion("jzmj between", value1, value2, "jzmj");
            return (Criteria) this;
        }

        public Criteria andJzmjNotBetween(String value1, String value2) {
            addCriterion("jzmj not between", value1, value2, "jzmj");
            return (Criteria) this;
        }

        public Criteria andZdmjIsNull() {
            addCriterion("zdmj is null");
            return (Criteria) this;
        }

        public Criteria andZdmjIsNotNull() {
            addCriterion("zdmj is not null");
            return (Criteria) this;
        }

        public Criteria andZdmjEqualTo(String value) {
            addCriterion("zdmj =", value, "zdmj");
            return (Criteria) this;
        }

        public Criteria andZdmjNotEqualTo(String value) {
            addCriterion("zdmj <>", value, "zdmj");
            return (Criteria) this;
        }

        public Criteria andZdmjGreaterThan(String value) {
            addCriterion("zdmj >", value, "zdmj");
            return (Criteria) this;
        }

        public Criteria andZdmjGreaterThanOrEqualTo(String value) {
            addCriterion("zdmj >=", value, "zdmj");
            return (Criteria) this;
        }

        public Criteria andZdmjLessThan(String value) {
            addCriterion("zdmj <", value, "zdmj");
            return (Criteria) this;
        }

        public Criteria andZdmjLessThanOrEqualTo(String value) {
            addCriterion("zdmj <=", value, "zdmj");
            return (Criteria) this;
        }

        public Criteria andZdmjLike(String value) {
            addCriterion("zdmj like", value, "zdmj");
            return (Criteria) this;
        }

        public Criteria andZdmjNotLike(String value) {
            addCriterion("zdmj not like", value, "zdmj");
            return (Criteria) this;
        }

        public Criteria andZdmjIn(List<String> values) {
            addCriterion("zdmj in", values, "zdmj");
            return (Criteria) this;
        }

        public Criteria andZdmjNotIn(List<String> values) {
            addCriterion("zdmj not in", values, "zdmj");
            return (Criteria) this;
        }

        public Criteria andZdmjBetween(String value1, String value2) {
            addCriterion("zdmj between", value1, value2, "zdmj");
            return (Criteria) this;
        }

        public Criteria andZdmjNotBetween(String value1, String value2) {
            addCriterion("zdmj not between", value1, value2, "zdmj");
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

        public Criteria andLhlIsNull() {
            addCriterion("lhl is null");
            return (Criteria) this;
        }

        public Criteria andLhlIsNotNull() {
            addCriterion("lhl is not null");
            return (Criteria) this;
        }

        public Criteria andLhlEqualTo(String value) {
            addCriterion("lhl =", value, "lhl");
            return (Criteria) this;
        }

        public Criteria andLhlNotEqualTo(String value) {
            addCriterion("lhl <>", value, "lhl");
            return (Criteria) this;
        }

        public Criteria andLhlGreaterThan(String value) {
            addCriterion("lhl >", value, "lhl");
            return (Criteria) this;
        }

        public Criteria andLhlGreaterThanOrEqualTo(String value) {
            addCriterion("lhl >=", value, "lhl");
            return (Criteria) this;
        }

        public Criteria andLhlLessThan(String value) {
            addCriterion("lhl <", value, "lhl");
            return (Criteria) this;
        }

        public Criteria andLhlLessThanOrEqualTo(String value) {
            addCriterion("lhl <=", value, "lhl");
            return (Criteria) this;
        }

        public Criteria andLhlLike(String value) {
            addCriterion("lhl like", value, "lhl");
            return (Criteria) this;
        }

        public Criteria andLhlNotLike(String value) {
            addCriterion("lhl not like", value, "lhl");
            return (Criteria) this;
        }

        public Criteria andLhlIn(List<String> values) {
            addCriterion("lhl in", values, "lhl");
            return (Criteria) this;
        }

        public Criteria andLhlNotIn(List<String> values) {
            addCriterion("lhl not in", values, "lhl");
            return (Criteria) this;
        }

        public Criteria andLhlBetween(String value1, String value2) {
            addCriterion("lhl between", value1, value2, "lhl");
            return (Criteria) this;
        }

        public Criteria andLhlNotBetween(String value1, String value2) {
            addCriterion("lhl not between", value1, value2, "lhl");
            return (Criteria) this;
        }

        public Criteria andCwxxIsNull() {
            addCriterion("cwxx is null");
            return (Criteria) this;
        }

        public Criteria andCwxxIsNotNull() {
            addCriterion("cwxx is not null");
            return (Criteria) this;
        }

        public Criteria andCwxxEqualTo(String value) {
            addCriterion("cwxx =", value, "cwxx");
            return (Criteria) this;
        }

        public Criteria andCwxxNotEqualTo(String value) {
            addCriterion("cwxx <>", value, "cwxx");
            return (Criteria) this;
        }

        public Criteria andCwxxGreaterThan(String value) {
            addCriterion("cwxx >", value, "cwxx");
            return (Criteria) this;
        }

        public Criteria andCwxxGreaterThanOrEqualTo(String value) {
            addCriterion("cwxx >=", value, "cwxx");
            return (Criteria) this;
        }

        public Criteria andCwxxLessThan(String value) {
            addCriterion("cwxx <", value, "cwxx");
            return (Criteria) this;
        }

        public Criteria andCwxxLessThanOrEqualTo(String value) {
            addCriterion("cwxx <=", value, "cwxx");
            return (Criteria) this;
        }

        public Criteria andCwxxLike(String value) {
            addCriterion("cwxx like", value, "cwxx");
            return (Criteria) this;
        }

        public Criteria andCwxxNotLike(String value) {
            addCriterion("cwxx not like", value, "cwxx");
            return (Criteria) this;
        }

        public Criteria andCwxxIn(List<String> values) {
            addCriterion("cwxx in", values, "cwxx");
            return (Criteria) this;
        }

        public Criteria andCwxxNotIn(List<String> values) {
            addCriterion("cwxx not in", values, "cwxx");
            return (Criteria) this;
        }

        public Criteria andCwxxBetween(String value1, String value2) {
            addCriterion("cwxx between", value1, value2, "cwxx");
            return (Criteria) this;
        }

        public Criteria andCwxxNotBetween(String value1, String value2) {
            addCriterion("cwxx not between", value1, value2, "cwxx");
            return (Criteria) this;
        }

        public Criteria andLpdzIsNull() {
            addCriterion("lpdz is null");
            return (Criteria) this;
        }

        public Criteria andLpdzIsNotNull() {
            addCriterion("lpdz is not null");
            return (Criteria) this;
        }

        public Criteria andLpdzEqualTo(String value) {
            addCriterion("lpdz =", value, "lpdz");
            return (Criteria) this;
        }

        public Criteria andLpdzNotEqualTo(String value) {
            addCriterion("lpdz <>", value, "lpdz");
            return (Criteria) this;
        }

        public Criteria andLpdzGreaterThan(String value) {
            addCriterion("lpdz >", value, "lpdz");
            return (Criteria) this;
        }

        public Criteria andLpdzGreaterThanOrEqualTo(String value) {
            addCriterion("lpdz >=", value, "lpdz");
            return (Criteria) this;
        }

        public Criteria andLpdzLessThan(String value) {
            addCriterion("lpdz <", value, "lpdz");
            return (Criteria) this;
        }

        public Criteria andLpdzLessThanOrEqualTo(String value) {
            addCriterion("lpdz <=", value, "lpdz");
            return (Criteria) this;
        }

        public Criteria andLpdzLike(String value) {
            addCriterion("lpdz like", value, "lpdz");
            return (Criteria) this;
        }

        public Criteria andLpdzNotLike(String value) {
            addCriterion("lpdz not like", value, "lpdz");
            return (Criteria) this;
        }

        public Criteria andLpdzIn(List<String> values) {
            addCriterion("lpdz in", values, "lpdz");
            return (Criteria) this;
        }

        public Criteria andLpdzNotIn(List<String> values) {
            addCriterion("lpdz not in", values, "lpdz");
            return (Criteria) this;
        }

        public Criteria andLpdzBetween(String value1, String value2) {
            addCriterion("lpdz between", value1, value2, "lpdz");
            return (Criteria) this;
        }

        public Criteria andLpdzNotBetween(String value1, String value2) {
            addCriterion("lpdz not between", value1, value2, "lpdz");
            return (Criteria) this;
        }

        public Criteria andSldzIsNull() {
            addCriterion("sldz is null");
            return (Criteria) this;
        }

        public Criteria andSldzIsNotNull() {
            addCriterion("sldz is not null");
            return (Criteria) this;
        }

        public Criteria andSldzEqualTo(String value) {
            addCriterion("sldz =", value, "sldz");
            return (Criteria) this;
        }

        public Criteria andSldzNotEqualTo(String value) {
            addCriterion("sldz <>", value, "sldz");
            return (Criteria) this;
        }

        public Criteria andSldzGreaterThan(String value) {
            addCriterion("sldz >", value, "sldz");
            return (Criteria) this;
        }

        public Criteria andSldzGreaterThanOrEqualTo(String value) {
            addCriterion("sldz >=", value, "sldz");
            return (Criteria) this;
        }

        public Criteria andSldzLessThan(String value) {
            addCriterion("sldz <", value, "sldz");
            return (Criteria) this;
        }

        public Criteria andSldzLessThanOrEqualTo(String value) {
            addCriterion("sldz <=", value, "sldz");
            return (Criteria) this;
        }

        public Criteria andSldzLike(String value) {
            addCriterion("sldz like", value, "sldz");
            return (Criteria) this;
        }

        public Criteria andSldzNotLike(String value) {
            addCriterion("sldz not like", value, "sldz");
            return (Criteria) this;
        }

        public Criteria andSldzIn(List<String> values) {
            addCriterion("sldz in", values, "sldz");
            return (Criteria) this;
        }

        public Criteria andSldzNotIn(List<String> values) {
            addCriterion("sldz not in", values, "sldz");
            return (Criteria) this;
        }

        public Criteria andSldzBetween(String value1, String value2) {
            addCriterion("sldz between", value1, value2, "sldz");
            return (Criteria) this;
        }

        public Criteria andSldzNotBetween(String value1, String value2) {
            addCriterion("sldz not between", value1, value2, "sldz");
            return (Criteria) this;
        }

        public Criteria andKfsbhIsNull() {
            addCriterion("kfsbh is null");
            return (Criteria) this;
        }

        public Criteria andKfsbhIsNotNull() {
            addCriterion("kfsbh is not null");
            return (Criteria) this;
        }

        public Criteria andKfsbhEqualTo(Integer value) {
            addCriterion("kfsbh =", value, "kfsbh");
            return (Criteria) this;
        }

        public Criteria andKfsbhNotEqualTo(Integer value) {
            addCriterion("kfsbh <>", value, "kfsbh");
            return (Criteria) this;
        }

        public Criteria andKfsbhGreaterThan(Integer value) {
            addCriterion("kfsbh >", value, "kfsbh");
            return (Criteria) this;
        }

        public Criteria andKfsbhGreaterThanOrEqualTo(Integer value) {
            addCriterion("kfsbh >=", value, "kfsbh");
            return (Criteria) this;
        }

        public Criteria andKfsbhLessThan(Integer value) {
            addCriterion("kfsbh <", value, "kfsbh");
            return (Criteria) this;
        }

        public Criteria andKfsbhLessThanOrEqualTo(Integer value) {
            addCriterion("kfsbh <=", value, "kfsbh");
            return (Criteria) this;
        }

        public Criteria andKfsbhIn(List<Integer> values) {
            addCriterion("kfsbh in", values, "kfsbh");
            return (Criteria) this;
        }

        public Criteria andKfsbhNotIn(List<Integer> values) {
            addCriterion("kfsbh not in", values, "kfsbh");
            return (Criteria) this;
        }

        public Criteria andKfsbhBetween(Integer value1, Integer value2) {
            addCriterion("kfsbh between", value1, value2, "kfsbh");
            return (Criteria) this;
        }

        public Criteria andKfsbhNotBetween(Integer value1, Integer value2) {
            addCriterion("kfsbh not between", value1, value2, "kfsbh");
            return (Criteria) this;
        }

        public Criteria andSsskzhIsNull() {
            addCriterion("ssskzh is null");
            return (Criteria) this;
        }

        public Criteria andSsskzhIsNotNull() {
            addCriterion("ssskzh is not null");
            return (Criteria) this;
        }

        public Criteria andSsskzhEqualTo(String value) {
            addCriterion("ssskzh =", value, "ssskzh");
            return (Criteria) this;
        }

        public Criteria andSsskzhNotEqualTo(String value) {
            addCriterion("ssskzh <>", value, "ssskzh");
            return (Criteria) this;
        }

        public Criteria andSsskzhGreaterThan(String value) {
            addCriterion("ssskzh >", value, "ssskzh");
            return (Criteria) this;
        }

        public Criteria andSsskzhGreaterThanOrEqualTo(String value) {
            addCriterion("ssskzh >=", value, "ssskzh");
            return (Criteria) this;
        }

        public Criteria andSsskzhLessThan(String value) {
            addCriterion("ssskzh <", value, "ssskzh");
            return (Criteria) this;
        }

        public Criteria andSsskzhLessThanOrEqualTo(String value) {
            addCriterion("ssskzh <=", value, "ssskzh");
            return (Criteria) this;
        }

        public Criteria andSsskzhLike(String value) {
            addCriterion("ssskzh like", value, "ssskzh");
            return (Criteria) this;
        }

        public Criteria andSsskzhNotLike(String value) {
            addCriterion("ssskzh not like", value, "ssskzh");
            return (Criteria) this;
        }

        public Criteria andSsskzhIn(List<String> values) {
            addCriterion("ssskzh in", values, "ssskzh");
            return (Criteria) this;
        }

        public Criteria andSsskzhNotIn(List<String> values) {
            addCriterion("ssskzh not in", values, "ssskzh");
            return (Criteria) this;
        }

        public Criteria andSsskzhBetween(String value1, String value2) {
            addCriterion("ssskzh between", value1, value2, "ssskzh");
            return (Criteria) this;
        }

        public Criteria andSsskzhNotBetween(String value1, String value2) {
            addCriterion("ssskzh not between", value1, value2, "ssskzh");
            return (Criteria) this;
        }

        public Criteria andJdIsNull() {
            addCriterion("jd is null");
            return (Criteria) this;
        }

        public Criteria andJdIsNotNull() {
            addCriterion("jd is not null");
            return (Criteria) this;
        }

        public Criteria andJdEqualTo(String value) {
            addCriterion("jd =", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdNotEqualTo(String value) {
            addCriterion("jd <>", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdGreaterThan(String value) {
            addCriterion("jd >", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdGreaterThanOrEqualTo(String value) {
            addCriterion("jd >=", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdLessThan(String value) {
            addCriterion("jd <", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdLessThanOrEqualTo(String value) {
            addCriterion("jd <=", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdLike(String value) {
            addCriterion("jd like", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdNotLike(String value) {
            addCriterion("jd not like", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdIn(List<String> values) {
            addCriterion("jd in", values, "jd");
            return (Criteria) this;
        }

        public Criteria andJdNotIn(List<String> values) {
            addCriterion("jd not in", values, "jd");
            return (Criteria) this;
        }

        public Criteria andJdBetween(String value1, String value2) {
            addCriterion("jd between", value1, value2, "jd");
            return (Criteria) this;
        }

        public Criteria andJdNotBetween(String value1, String value2) {
            addCriterion("jd not between", value1, value2, "jd");
            return (Criteria) this;
        }

        public Criteria andWdIsNull() {
            addCriterion("wd is null");
            return (Criteria) this;
        }

        public Criteria andWdIsNotNull() {
            addCriterion("wd is not null");
            return (Criteria) this;
        }

        public Criteria andWdEqualTo(String value) {
            addCriterion("wd =", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdNotEqualTo(String value) {
            addCriterion("wd <>", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdGreaterThan(String value) {
            addCriterion("wd >", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdGreaterThanOrEqualTo(String value) {
            addCriterion("wd >=", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdLessThan(String value) {
            addCriterion("wd <", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdLessThanOrEqualTo(String value) {
            addCriterion("wd <=", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdLike(String value) {
            addCriterion("wd like", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdNotLike(String value) {
            addCriterion("wd not like", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdIn(List<String> values) {
            addCriterion("wd in", values, "wd");
            return (Criteria) this;
        }

        public Criteria andWdNotIn(List<String> values) {
            addCriterion("wd not in", values, "wd");
            return (Criteria) this;
        }

        public Criteria andWdBetween(String value1, String value2) {
            addCriterion("wd between", value1, value2, "wd");
            return (Criteria) this;
        }

        public Criteria andWdNotBetween(String value1, String value2) {
            addCriterion("wd not between", value1, value2, "wd");
            return (Criteria) this;
        }

        public Criteria andFuniwebIsNull() {
            addCriterion("funiweb is null");
            return (Criteria) this;
        }

        public Criteria andFuniwebIsNotNull() {
            addCriterion("funiweb is not null");
            return (Criteria) this;
        }

        public Criteria andFuniwebEqualTo(String value) {
            addCriterion("funiweb =", value, "funiweb");
            return (Criteria) this;
        }

        public Criteria andFuniwebNotEqualTo(String value) {
            addCriterion("funiweb <>", value, "funiweb");
            return (Criteria) this;
        }

        public Criteria andFuniwebGreaterThan(String value) {
            addCriterion("funiweb >", value, "funiweb");
            return (Criteria) this;
        }

        public Criteria andFuniwebGreaterThanOrEqualTo(String value) {
            addCriterion("funiweb >=", value, "funiweb");
            return (Criteria) this;
        }

        public Criteria andFuniwebLessThan(String value) {
            addCriterion("funiweb <", value, "funiweb");
            return (Criteria) this;
        }

        public Criteria andFuniwebLessThanOrEqualTo(String value) {
            addCriterion("funiweb <=", value, "funiweb");
            return (Criteria) this;
        }

        public Criteria andFuniwebLike(String value) {
            addCriterion("funiweb like", value, "funiweb");
            return (Criteria) this;
        }

        public Criteria andFuniwebNotLike(String value) {
            addCriterion("funiweb not like", value, "funiweb");
            return (Criteria) this;
        }

        public Criteria andFuniwebIn(List<String> values) {
            addCriterion("funiweb in", values, "funiweb");
            return (Criteria) this;
        }

        public Criteria andFuniwebNotIn(List<String> values) {
            addCriterion("funiweb not in", values, "funiweb");
            return (Criteria) this;
        }

        public Criteria andFuniwebBetween(String value1, String value2) {
            addCriterion("funiweb between", value1, value2, "funiweb");
            return (Criteria) this;
        }

        public Criteria andFuniwebNotBetween(String value1, String value2) {
            addCriterion("funiweb not between", value1, value2, "funiweb");
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