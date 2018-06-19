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

        public Criteria andKfsIsNull() {
            addCriterion("kfs is null");
            return (Criteria) this;
        }

        public Criteria andKfsIsNotNull() {
            addCriterion("kfs is not null");
            return (Criteria) this;
        }

        public Criteria andKfsEqualTo(String value) {
            addCriterion("kfs =", value, "kfs");
            return (Criteria) this;
        }

        public Criteria andKfsNotEqualTo(String value) {
            addCriterion("kfs <>", value, "kfs");
            return (Criteria) this;
        }

        public Criteria andKfsGreaterThan(String value) {
            addCriterion("kfs >", value, "kfs");
            return (Criteria) this;
        }

        public Criteria andKfsGreaterThanOrEqualTo(String value) {
            addCriterion("kfs >=", value, "kfs");
            return (Criteria) this;
        }

        public Criteria andKfsLessThan(String value) {
            addCriterion("kfs <", value, "kfs");
            return (Criteria) this;
        }

        public Criteria andKfsLessThanOrEqualTo(String value) {
            addCriterion("kfs <=", value, "kfs");
            return (Criteria) this;
        }

        public Criteria andKfsLike(String value) {
            addCriterion("kfs like", value, "kfs");
            return (Criteria) this;
        }

        public Criteria andKfsNotLike(String value) {
            addCriterion("kfs not like", value, "kfs");
            return (Criteria) this;
        }

        public Criteria andKfsIn(List<String> values) {
            addCriterion("kfs in", values, "kfs");
            return (Criteria) this;
        }

        public Criteria andKfsNotIn(List<String> values) {
            addCriterion("kfs not in", values, "kfs");
            return (Criteria) this;
        }

        public Criteria andKfsBetween(String value1, String value2) {
            addCriterion("kfs between", value1, value2, "kfs");
            return (Criteria) this;
        }

        public Criteria andKfsNotBetween(String value1, String value2) {
            addCriterion("kfs not between", value1, value2, "kfs");
            return (Criteria) this;
        }

        public Criteria andXsxkzIsNull() {
            addCriterion("xsxkz is null");
            return (Criteria) this;
        }

        public Criteria andXsxkzIsNotNull() {
            addCriterion("xsxkz is not null");
            return (Criteria) this;
        }

        public Criteria andXsxkzEqualTo(String value) {
            addCriterion("xsxkz =", value, "xsxkz");
            return (Criteria) this;
        }

        public Criteria andXsxkzNotEqualTo(String value) {
            addCriterion("xsxkz <>", value, "xsxkz");
            return (Criteria) this;
        }

        public Criteria andXsxkzGreaterThan(String value) {
            addCriterion("xsxkz >", value, "xsxkz");
            return (Criteria) this;
        }

        public Criteria andXsxkzGreaterThanOrEqualTo(String value) {
            addCriterion("xsxkz >=", value, "xsxkz");
            return (Criteria) this;
        }

        public Criteria andXsxkzLessThan(String value) {
            addCriterion("xsxkz <", value, "xsxkz");
            return (Criteria) this;
        }

        public Criteria andXsxkzLessThanOrEqualTo(String value) {
            addCriterion("xsxkz <=", value, "xsxkz");
            return (Criteria) this;
        }

        public Criteria andXsxkzLike(String value) {
            addCriterion("xsxkz like", value, "xsxkz");
            return (Criteria) this;
        }

        public Criteria andXsxkzNotLike(String value) {
            addCriterion("xsxkz not like", value, "xsxkz");
            return (Criteria) this;
        }

        public Criteria andXsxkzIn(List<String> values) {
            addCriterion("xsxkz in", values, "xsxkz");
            return (Criteria) this;
        }

        public Criteria andXsxkzNotIn(List<String> values) {
            addCriterion("xsxkz not in", values, "xsxkz");
            return (Criteria) this;
        }

        public Criteria andXsxkzBetween(String value1, String value2) {
            addCriterion("xsxkz between", value1, value2, "xsxkz");
            return (Criteria) this;
        }

        public Criteria andXsxkzNotBetween(String value1, String value2) {
            addCriterion("xsxkz not between", value1, value2, "xsxkz");
            return (Criteria) this;
        }

        public Criteria andLpjsIsNull() {
            addCriterion("lpjs is null");
            return (Criteria) this;
        }

        public Criteria andLpjsIsNotNull() {
            addCriterion("lpjs is not null");
            return (Criteria) this;
        }

        public Criteria andLpjsEqualTo(String value) {
            addCriterion("lpjs =", value, "lpjs");
            return (Criteria) this;
        }

        public Criteria andLpjsNotEqualTo(String value) {
            addCriterion("lpjs <>", value, "lpjs");
            return (Criteria) this;
        }

        public Criteria andLpjsGreaterThan(String value) {
            addCriterion("lpjs >", value, "lpjs");
            return (Criteria) this;
        }

        public Criteria andLpjsGreaterThanOrEqualTo(String value) {
            addCriterion("lpjs >=", value, "lpjs");
            return (Criteria) this;
        }

        public Criteria andLpjsLessThan(String value) {
            addCriterion("lpjs <", value, "lpjs");
            return (Criteria) this;
        }

        public Criteria andLpjsLessThanOrEqualTo(String value) {
            addCriterion("lpjs <=", value, "lpjs");
            return (Criteria) this;
        }

        public Criteria andLpjsLike(String value) {
            addCriterion("lpjs like", value, "lpjs");
            return (Criteria) this;
        }

        public Criteria andLpjsNotLike(String value) {
            addCriterion("lpjs not like", value, "lpjs");
            return (Criteria) this;
        }

        public Criteria andLpjsIn(List<String> values) {
            addCriterion("lpjs in", values, "lpjs");
            return (Criteria) this;
        }

        public Criteria andLpjsNotIn(List<String> values) {
            addCriterion("lpjs not in", values, "lpjs");
            return (Criteria) this;
        }

        public Criteria andLpjsBetween(String value1, String value2) {
            addCriterion("lpjs between", value1, value2, "lpjs");
            return (Criteria) this;
        }

        public Criteria andLpjsNotBetween(String value1, String value2) {
            addCriterion("lpjs not between", value1, value2, "lpjs");
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

        public Criteria andQybhIsNull() {
            addCriterion("qybh is null");
            return (Criteria) this;
        }

        public Criteria andQybhIsNotNull() {
            addCriterion("qybh is not null");
            return (Criteria) this;
        }

        public Criteria andQybhEqualTo(Integer value) {
            addCriterion("qybh =", value, "qybh");
            return (Criteria) this;
        }

        public Criteria andQybhNotEqualTo(Integer value) {
            addCriterion("qybh <>", value, "qybh");
            return (Criteria) this;
        }

        public Criteria andQybhGreaterThan(Integer value) {
            addCriterion("qybh >", value, "qybh");
            return (Criteria) this;
        }

        public Criteria andQybhGreaterThanOrEqualTo(Integer value) {
            addCriterion("qybh >=", value, "qybh");
            return (Criteria) this;
        }

        public Criteria andQybhLessThan(Integer value) {
            addCriterion("qybh <", value, "qybh");
            return (Criteria) this;
        }

        public Criteria andQybhLessThanOrEqualTo(Integer value) {
            addCriterion("qybh <=", value, "qybh");
            return (Criteria) this;
        }

        public Criteria andQybhIn(List<Integer> values) {
            addCriterion("qybh in", values, "qybh");
            return (Criteria) this;
        }

        public Criteria andQybhNotIn(List<Integer> values) {
            addCriterion("qybh not in", values, "qybh");
            return (Criteria) this;
        }

        public Criteria andQybhBetween(Integer value1, Integer value2) {
            addCriterion("qybh between", value1, value2, "qybh");
            return (Criteria) this;
        }

        public Criteria andQybhNotBetween(Integer value1, Integer value2) {
            addCriterion("qybh not between", value1, value2, "qybh");
            return (Criteria) this;
        }

        public Criteria andQyIsNull() {
            addCriterion("qy is null");
            return (Criteria) this;
        }

        public Criteria andQyIsNotNull() {
            addCriterion("qy is not null");
            return (Criteria) this;
        }

        public Criteria andQyEqualTo(String value) {
            addCriterion("qy =", value, "qy");
            return (Criteria) this;
        }

        public Criteria andQyNotEqualTo(String value) {
            addCriterion("qy <>", value, "qy");
            return (Criteria) this;
        }

        public Criteria andQyGreaterThan(String value) {
            addCriterion("qy >", value, "qy");
            return (Criteria) this;
        }

        public Criteria andQyGreaterThanOrEqualTo(String value) {
            addCriterion("qy >=", value, "qy");
            return (Criteria) this;
        }

        public Criteria andQyLessThan(String value) {
            addCriterion("qy <", value, "qy");
            return (Criteria) this;
        }

        public Criteria andQyLessThanOrEqualTo(String value) {
            addCriterion("qy <=", value, "qy");
            return (Criteria) this;
        }

        public Criteria andQyLike(String value) {
            addCriterion("qy like", value, "qy");
            return (Criteria) this;
        }

        public Criteria andQyNotLike(String value) {
            addCriterion("qy not like", value, "qy");
            return (Criteria) this;
        }

        public Criteria andQyIn(List<String> values) {
            addCriterion("qy in", values, "qy");
            return (Criteria) this;
        }

        public Criteria andQyNotIn(List<String> values) {
            addCriterion("qy not in", values, "qy");
            return (Criteria) this;
        }

        public Criteria andQyBetween(String value1, String value2) {
            addCriterion("qy between", value1, value2, "qy");
            return (Criteria) this;
        }

        public Criteria andQyNotBetween(String value1, String value2) {
            addCriterion("qy not between", value1, value2, "qy");
            return (Criteria) this;
        }

        public Criteria andSqbhIsNull() {
            addCriterion("sqbh is null");
            return (Criteria) this;
        }

        public Criteria andSqbhIsNotNull() {
            addCriterion("sqbh is not null");
            return (Criteria) this;
        }

        public Criteria andSqbhEqualTo(Integer value) {
            addCriterion("sqbh =", value, "sqbh");
            return (Criteria) this;
        }

        public Criteria andSqbhNotEqualTo(Integer value) {
            addCriterion("sqbh <>", value, "sqbh");
            return (Criteria) this;
        }

        public Criteria andSqbhGreaterThan(Integer value) {
            addCriterion("sqbh >", value, "sqbh");
            return (Criteria) this;
        }

        public Criteria andSqbhGreaterThanOrEqualTo(Integer value) {
            addCriterion("sqbh >=", value, "sqbh");
            return (Criteria) this;
        }

        public Criteria andSqbhLessThan(Integer value) {
            addCriterion("sqbh <", value, "sqbh");
            return (Criteria) this;
        }

        public Criteria andSqbhLessThanOrEqualTo(Integer value) {
            addCriterion("sqbh <=", value, "sqbh");
            return (Criteria) this;
        }

        public Criteria andSqbhIn(List<Integer> values) {
            addCriterion("sqbh in", values, "sqbh");
            return (Criteria) this;
        }

        public Criteria andSqbhNotIn(List<Integer> values) {
            addCriterion("sqbh not in", values, "sqbh");
            return (Criteria) this;
        }

        public Criteria andSqbhBetween(Integer value1, Integer value2) {
            addCriterion("sqbh between", value1, value2, "sqbh");
            return (Criteria) this;
        }

        public Criteria andSqbhNotBetween(Integer value1, Integer value2) {
            addCriterion("sqbh not between", value1, value2, "sqbh");
            return (Criteria) this;
        }

        public Criteria andSqIsNull() {
            addCriterion("sq is null");
            return (Criteria) this;
        }

        public Criteria andSqIsNotNull() {
            addCriterion("sq is not null");
            return (Criteria) this;
        }

        public Criteria andSqEqualTo(String value) {
            addCriterion("sq =", value, "sq");
            return (Criteria) this;
        }

        public Criteria andSqNotEqualTo(String value) {
            addCriterion("sq <>", value, "sq");
            return (Criteria) this;
        }

        public Criteria andSqGreaterThan(String value) {
            addCriterion("sq >", value, "sq");
            return (Criteria) this;
        }

        public Criteria andSqGreaterThanOrEqualTo(String value) {
            addCriterion("sq >=", value, "sq");
            return (Criteria) this;
        }

        public Criteria andSqLessThan(String value) {
            addCriterion("sq <", value, "sq");
            return (Criteria) this;
        }

        public Criteria andSqLessThanOrEqualTo(String value) {
            addCriterion("sq <=", value, "sq");
            return (Criteria) this;
        }

        public Criteria andSqLike(String value) {
            addCriterion("sq like", value, "sq");
            return (Criteria) this;
        }

        public Criteria andSqNotLike(String value) {
            addCriterion("sq not like", value, "sq");
            return (Criteria) this;
        }

        public Criteria andSqIn(List<String> values) {
            addCriterion("sq in", values, "sq");
            return (Criteria) this;
        }

        public Criteria andSqNotIn(List<String> values) {
            addCriterion("sq not in", values, "sq");
            return (Criteria) this;
        }

        public Criteria andSqBetween(String value1, String value2) {
            addCriterion("sq between", value1, value2, "sq");
            return (Criteria) this;
        }

        public Criteria andSqNotBetween(String value1, String value2) {
            addCriterion("sq not between", value1, value2, "sq");
            return (Criteria) this;
        }

        public Criteria andSfbhIsNull() {
            addCriterion("sfbh is null");
            return (Criteria) this;
        }

        public Criteria andSfbhIsNotNull() {
            addCriterion("sfbh is not null");
            return (Criteria) this;
        }

        public Criteria andSfbhEqualTo(Integer value) {
            addCriterion("sfbh =", value, "sfbh");
            return (Criteria) this;
        }

        public Criteria andSfbhNotEqualTo(Integer value) {
            addCriterion("sfbh <>", value, "sfbh");
            return (Criteria) this;
        }

        public Criteria andSfbhGreaterThan(Integer value) {
            addCriterion("sfbh >", value, "sfbh");
            return (Criteria) this;
        }

        public Criteria andSfbhGreaterThanOrEqualTo(Integer value) {
            addCriterion("sfbh >=", value, "sfbh");
            return (Criteria) this;
        }

        public Criteria andSfbhLessThan(Integer value) {
            addCriterion("sfbh <", value, "sfbh");
            return (Criteria) this;
        }

        public Criteria andSfbhLessThanOrEqualTo(Integer value) {
            addCriterion("sfbh <=", value, "sfbh");
            return (Criteria) this;
        }

        public Criteria andSfbhIn(List<Integer> values) {
            addCriterion("sfbh in", values, "sfbh");
            return (Criteria) this;
        }

        public Criteria andSfbhNotIn(List<Integer> values) {
            addCriterion("sfbh not in", values, "sfbh");
            return (Criteria) this;
        }

        public Criteria andSfbhBetween(Integer value1, Integer value2) {
            addCriterion("sfbh between", value1, value2, "sfbh");
            return (Criteria) this;
        }

        public Criteria andSfbhNotBetween(Integer value1, Integer value2) {
            addCriterion("sfbh not between", value1, value2, "sfbh");
            return (Criteria) this;
        }

        public Criteria andSfIsNull() {
            addCriterion("sf is null");
            return (Criteria) this;
        }

        public Criteria andSfIsNotNull() {
            addCriterion("sf is not null");
            return (Criteria) this;
        }

        public Criteria andSfEqualTo(String value) {
            addCriterion("sf =", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfNotEqualTo(String value) {
            addCriterion("sf <>", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfGreaterThan(String value) {
            addCriterion("sf >", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfGreaterThanOrEqualTo(String value) {
            addCriterion("sf >=", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfLessThan(String value) {
            addCriterion("sf <", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfLessThanOrEqualTo(String value) {
            addCriterion("sf <=", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfLike(String value) {
            addCriterion("sf like", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfNotLike(String value) {
            addCriterion("sf not like", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfIn(List<String> values) {
            addCriterion("sf in", values, "sf");
            return (Criteria) this;
        }

        public Criteria andSfNotIn(List<String> values) {
            addCriterion("sf not in", values, "sf");
            return (Criteria) this;
        }

        public Criteria andSfBetween(String value1, String value2) {
            addCriterion("sf between", value1, value2, "sf");
            return (Criteria) this;
        }

        public Criteria andSfNotBetween(String value1, String value2) {
            addCriterion("sf not between", value1, value2, "sf");
            return (Criteria) this;
        }

        public Criteria andCsbhIsNull() {
            addCriterion("csbh is null");
            return (Criteria) this;
        }

        public Criteria andCsbhIsNotNull() {
            addCriterion("csbh is not null");
            return (Criteria) this;
        }

        public Criteria andCsbhEqualTo(Integer value) {
            addCriterion("csbh =", value, "csbh");
            return (Criteria) this;
        }

        public Criteria andCsbhNotEqualTo(Integer value) {
            addCriterion("csbh <>", value, "csbh");
            return (Criteria) this;
        }

        public Criteria andCsbhGreaterThan(Integer value) {
            addCriterion("csbh >", value, "csbh");
            return (Criteria) this;
        }

        public Criteria andCsbhGreaterThanOrEqualTo(Integer value) {
            addCriterion("csbh >=", value, "csbh");
            return (Criteria) this;
        }

        public Criteria andCsbhLessThan(Integer value) {
            addCriterion("csbh <", value, "csbh");
            return (Criteria) this;
        }

        public Criteria andCsbhLessThanOrEqualTo(Integer value) {
            addCriterion("csbh <=", value, "csbh");
            return (Criteria) this;
        }

        public Criteria andCsbhIn(List<Integer> values) {
            addCriterion("csbh in", values, "csbh");
            return (Criteria) this;
        }

        public Criteria andCsbhNotIn(List<Integer> values) {
            addCriterion("csbh not in", values, "csbh");
            return (Criteria) this;
        }

        public Criteria andCsbhBetween(Integer value1, Integer value2) {
            addCriterion("csbh between", value1, value2, "csbh");
            return (Criteria) this;
        }

        public Criteria andCsbhNotBetween(Integer value1, Integer value2) {
            addCriterion("csbh not between", value1, value2, "csbh");
            return (Criteria) this;
        }

        public Criteria andCsmcIsNull() {
            addCriterion("csmc is null");
            return (Criteria) this;
        }

        public Criteria andCsmcIsNotNull() {
            addCriterion("csmc is not null");
            return (Criteria) this;
        }

        public Criteria andCsmcEqualTo(String value) {
            addCriterion("csmc =", value, "csmc");
            return (Criteria) this;
        }

        public Criteria andCsmcNotEqualTo(String value) {
            addCriterion("csmc <>", value, "csmc");
            return (Criteria) this;
        }

        public Criteria andCsmcGreaterThan(String value) {
            addCriterion("csmc >", value, "csmc");
            return (Criteria) this;
        }

        public Criteria andCsmcGreaterThanOrEqualTo(String value) {
            addCriterion("csmc >=", value, "csmc");
            return (Criteria) this;
        }

        public Criteria andCsmcLessThan(String value) {
            addCriterion("csmc <", value, "csmc");
            return (Criteria) this;
        }

        public Criteria andCsmcLessThanOrEqualTo(String value) {
            addCriterion("csmc <=", value, "csmc");
            return (Criteria) this;
        }

        public Criteria andCsmcLike(String value) {
            addCriterion("csmc like", value, "csmc");
            return (Criteria) this;
        }

        public Criteria andCsmcNotLike(String value) {
            addCriterion("csmc not like", value, "csmc");
            return (Criteria) this;
        }

        public Criteria andCsmcIn(List<String> values) {
            addCriterion("csmc in", values, "csmc");
            return (Criteria) this;
        }

        public Criteria andCsmcNotIn(List<String> values) {
            addCriterion("csmc not in", values, "csmc");
            return (Criteria) this;
        }

        public Criteria andCsmcBetween(String value1, String value2) {
            addCriterion("csmc between", value1, value2, "csmc");
            return (Criteria) this;
        }

        public Criteria andCsmcNotBetween(String value1, String value2) {
            addCriterion("csmc not between", value1, value2, "csmc");
            return (Criteria) this;
        }

        public Criteria andBisEditIsNull() {
            addCriterion("bis_edit is null");
            return (Criteria) this;
        }

        public Criteria andBisEditIsNotNull() {
            addCriterion("bis_edit is not null");
            return (Criteria) this;
        }

        public Criteria andBisEditEqualTo(Boolean value) {
            addCriterion("bis_edit =", value, "bisEdit");
            return (Criteria) this;
        }

        public Criteria andBisEditNotEqualTo(Boolean value) {
            addCriterion("bis_edit <>", value, "bisEdit");
            return (Criteria) this;
        }

        public Criteria andBisEditGreaterThan(Boolean value) {
            addCriterion("bis_edit >", value, "bisEdit");
            return (Criteria) this;
        }

        public Criteria andBisEditGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_edit >=", value, "bisEdit");
            return (Criteria) this;
        }

        public Criteria andBisEditLessThan(Boolean value) {
            addCriterion("bis_edit <", value, "bisEdit");
            return (Criteria) this;
        }

        public Criteria andBisEditLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_edit <=", value, "bisEdit");
            return (Criteria) this;
        }

        public Criteria andBisEditIn(List<Boolean> values) {
            addCriterion("bis_edit in", values, "bisEdit");
            return (Criteria) this;
        }

        public Criteria andBisEditNotIn(List<Boolean> values) {
            addCriterion("bis_edit not in", values, "bisEdit");
            return (Criteria) this;
        }

        public Criteria andBisEditBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_edit between", value1, value2, "bisEdit");
            return (Criteria) this;
        }

        public Criteria andBisEditNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_edit not between", value1, value2, "bisEdit");
            return (Criteria) this;
        }

        public Criteria andXmdzIsNull() {
            addCriterion("xmdz is null");
            return (Criteria) this;
        }

        public Criteria andXmdzIsNotNull() {
            addCriterion("xmdz is not null");
            return (Criteria) this;
        }

        public Criteria andXmdzEqualTo(String value) {
            addCriterion("xmdz =", value, "xmdz");
            return (Criteria) this;
        }

        public Criteria andXmdzNotEqualTo(String value) {
            addCriterion("xmdz <>", value, "xmdz");
            return (Criteria) this;
        }

        public Criteria andXmdzGreaterThan(String value) {
            addCriterion("xmdz >", value, "xmdz");
            return (Criteria) this;
        }

        public Criteria andXmdzGreaterThanOrEqualTo(String value) {
            addCriterion("xmdz >=", value, "xmdz");
            return (Criteria) this;
        }

        public Criteria andXmdzLessThan(String value) {
            addCriterion("xmdz <", value, "xmdz");
            return (Criteria) this;
        }

        public Criteria andXmdzLessThanOrEqualTo(String value) {
            addCriterion("xmdz <=", value, "xmdz");
            return (Criteria) this;
        }

        public Criteria andXmdzLike(String value) {
            addCriterion("xmdz like", value, "xmdz");
            return (Criteria) this;
        }

        public Criteria andXmdzNotLike(String value) {
            addCriterion("xmdz not like", value, "xmdz");
            return (Criteria) this;
        }

        public Criteria andXmdzIn(List<String> values) {
            addCriterion("xmdz in", values, "xmdz");
            return (Criteria) this;
        }

        public Criteria andXmdzNotIn(List<String> values) {
            addCriterion("xmdz not in", values, "xmdz");
            return (Criteria) this;
        }

        public Criteria andXmdzBetween(String value1, String value2) {
            addCriterion("xmdz between", value1, value2, "xmdz");
            return (Criteria) this;
        }

        public Criteria andXmdzNotBetween(String value1, String value2) {
            addCriterion("xmdz not between", value1, value2, "xmdz");
            return (Criteria) this;
        }

        public Criteria andLptpIsNull() {
            addCriterion("lptp is null");
            return (Criteria) this;
        }

        public Criteria andLptpIsNotNull() {
            addCriterion("lptp is not null");
            return (Criteria) this;
        }

        public Criteria andLptpEqualTo(String value) {
            addCriterion("lptp =", value, "lptp");
            return (Criteria) this;
        }

        public Criteria andLptpNotEqualTo(String value) {
            addCriterion("lptp <>", value, "lptp");
            return (Criteria) this;
        }

        public Criteria andLptpGreaterThan(String value) {
            addCriterion("lptp >", value, "lptp");
            return (Criteria) this;
        }

        public Criteria andLptpGreaterThanOrEqualTo(String value) {
            addCriterion("lptp >=", value, "lptp");
            return (Criteria) this;
        }

        public Criteria andLptpLessThan(String value) {
            addCriterion("lptp <", value, "lptp");
            return (Criteria) this;
        }

        public Criteria andLptpLessThanOrEqualTo(String value) {
            addCriterion("lptp <=", value, "lptp");
            return (Criteria) this;
        }

        public Criteria andLptpLike(String value) {
            addCriterion("lptp like", value, "lptp");
            return (Criteria) this;
        }

        public Criteria andLptpNotLike(String value) {
            addCriterion("lptp not like", value, "lptp");
            return (Criteria) this;
        }

        public Criteria andLptpIn(List<String> values) {
            addCriterion("lptp in", values, "lptp");
            return (Criteria) this;
        }

        public Criteria andLptpNotIn(List<String> values) {
            addCriterion("lptp not in", values, "lptp");
            return (Criteria) this;
        }

        public Criteria andLptpBetween(String value1, String value2) {
            addCriterion("lptp between", value1, value2, "lptp");
            return (Criteria) this;
        }

        public Criteria andLptpNotBetween(String value1, String value2) {
            addCriterion("lptp not between", value1, value2, "lptp");
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