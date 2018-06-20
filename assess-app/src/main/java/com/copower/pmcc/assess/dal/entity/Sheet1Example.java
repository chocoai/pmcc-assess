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

        public Criteria andKhxmIsNull() {
            addCriterion("khxm is null");
            return (Criteria) this;
        }

        public Criteria andKhxmIsNotNull() {
            addCriterion("khxm is not null");
            return (Criteria) this;
        }

        public Criteria andKhxmEqualTo(String value) {
            addCriterion("khxm =", value, "khxm");
            return (Criteria) this;
        }

        public Criteria andKhxmNotEqualTo(String value) {
            addCriterion("khxm <>", value, "khxm");
            return (Criteria) this;
        }

        public Criteria andKhxmGreaterThan(String value) {
            addCriterion("khxm >", value, "khxm");
            return (Criteria) this;
        }

        public Criteria andKhxmGreaterThanOrEqualTo(String value) {
            addCriterion("khxm >=", value, "khxm");
            return (Criteria) this;
        }

        public Criteria andKhxmLessThan(String value) {
            addCriterion("khxm <", value, "khxm");
            return (Criteria) this;
        }

        public Criteria andKhxmLessThanOrEqualTo(String value) {
            addCriterion("khxm <=", value, "khxm");
            return (Criteria) this;
        }

        public Criteria andKhxmLike(String value) {
            addCriterion("khxm like", value, "khxm");
            return (Criteria) this;
        }

        public Criteria andKhxmNotLike(String value) {
            addCriterion("khxm not like", value, "khxm");
            return (Criteria) this;
        }

        public Criteria andKhxmIn(List<String> values) {
            addCriterion("khxm in", values, "khxm");
            return (Criteria) this;
        }

        public Criteria andKhxmNotIn(List<String> values) {
            addCriterion("khxm not in", values, "khxm");
            return (Criteria) this;
        }

        public Criteria andKhxmBetween(String value1, String value2) {
            addCriterion("khxm between", value1, value2, "khxm");
            return (Criteria) this;
        }

        public Criteria andKhxmNotBetween(String value1, String value2) {
            addCriterion("khxm not between", value1, value2, "khxm");
            return (Criteria) this;
        }

        public Criteria andSfzhmIsNull() {
            addCriterion("sfzhm is null");
            return (Criteria) this;
        }

        public Criteria andSfzhmIsNotNull() {
            addCriterion("sfzhm is not null");
            return (Criteria) this;
        }

        public Criteria andSfzhmEqualTo(String value) {
            addCriterion("sfzhm =", value, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmNotEqualTo(String value) {
            addCriterion("sfzhm <>", value, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmGreaterThan(String value) {
            addCriterion("sfzhm >", value, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmGreaterThanOrEqualTo(String value) {
            addCriterion("sfzhm >=", value, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmLessThan(String value) {
            addCriterion("sfzhm <", value, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmLessThanOrEqualTo(String value) {
            addCriterion("sfzhm <=", value, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmLike(String value) {
            addCriterion("sfzhm like", value, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmNotLike(String value) {
            addCriterion("sfzhm not like", value, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmIn(List<String> values) {
            addCriterion("sfzhm in", values, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmNotIn(List<String> values) {
            addCriterion("sfzhm not in", values, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmBetween(String value1, String value2) {
            addCriterion("sfzhm between", value1, value2, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andSfzhmNotBetween(String value1, String value2) {
            addCriterion("sfzhm not between", value1, value2, "sfzhm");
            return (Criteria) this;
        }

        public Criteria andEjfhIsNull() {
            addCriterion("ejfh is null");
            return (Criteria) this;
        }

        public Criteria andEjfhIsNotNull() {
            addCriterion("ejfh is not null");
            return (Criteria) this;
        }

        public Criteria andEjfhEqualTo(String value) {
            addCriterion("ejfh =", value, "ejfh");
            return (Criteria) this;
        }

        public Criteria andEjfhNotEqualTo(String value) {
            addCriterion("ejfh <>", value, "ejfh");
            return (Criteria) this;
        }

        public Criteria andEjfhGreaterThan(String value) {
            addCriterion("ejfh >", value, "ejfh");
            return (Criteria) this;
        }

        public Criteria andEjfhGreaterThanOrEqualTo(String value) {
            addCriterion("ejfh >=", value, "ejfh");
            return (Criteria) this;
        }

        public Criteria andEjfhLessThan(String value) {
            addCriterion("ejfh <", value, "ejfh");
            return (Criteria) this;
        }

        public Criteria andEjfhLessThanOrEqualTo(String value) {
            addCriterion("ejfh <=", value, "ejfh");
            return (Criteria) this;
        }

        public Criteria andEjfhLike(String value) {
            addCriterion("ejfh like", value, "ejfh");
            return (Criteria) this;
        }

        public Criteria andEjfhNotLike(String value) {
            addCriterion("ejfh not like", value, "ejfh");
            return (Criteria) this;
        }

        public Criteria andEjfhIn(List<String> values) {
            addCriterion("ejfh in", values, "ejfh");
            return (Criteria) this;
        }

        public Criteria andEjfhNotIn(List<String> values) {
            addCriterion("ejfh not in", values, "ejfh");
            return (Criteria) this;
        }

        public Criteria andEjfhBetween(String value1, String value2) {
            addCriterion("ejfh between", value1, value2, "ejfh");
            return (Criteria) this;
        }

        public Criteria andEjfhNotBetween(String value1, String value2) {
            addCriterion("ejfh not between", value1, value2, "ejfh");
            return (Criteria) this;
        }

        public Criteria andSjhthIsNull() {
            addCriterion("sjhth is null");
            return (Criteria) this;
        }

        public Criteria andSjhthIsNotNull() {
            addCriterion("sjhth is not null");
            return (Criteria) this;
        }

        public Criteria andSjhthEqualTo(String value) {
            addCriterion("sjhth =", value, "sjhth");
            return (Criteria) this;
        }

        public Criteria andSjhthNotEqualTo(String value) {
            addCriterion("sjhth <>", value, "sjhth");
            return (Criteria) this;
        }

        public Criteria andSjhthGreaterThan(String value) {
            addCriterion("sjhth >", value, "sjhth");
            return (Criteria) this;
        }

        public Criteria andSjhthGreaterThanOrEqualTo(String value) {
            addCriterion("sjhth >=", value, "sjhth");
            return (Criteria) this;
        }

        public Criteria andSjhthLessThan(String value) {
            addCriterion("sjhth <", value, "sjhth");
            return (Criteria) this;
        }

        public Criteria andSjhthLessThanOrEqualTo(String value) {
            addCriterion("sjhth <=", value, "sjhth");
            return (Criteria) this;
        }

        public Criteria andSjhthLike(String value) {
            addCriterion("sjhth like", value, "sjhth");
            return (Criteria) this;
        }

        public Criteria andSjhthNotLike(String value) {
            addCriterion("sjhth not like", value, "sjhth");
            return (Criteria) this;
        }

        public Criteria andSjhthIn(List<String> values) {
            addCriterion("sjhth in", values, "sjhth");
            return (Criteria) this;
        }

        public Criteria andSjhthNotIn(List<String> values) {
            addCriterion("sjhth not in", values, "sjhth");
            return (Criteria) this;
        }

        public Criteria andSjhthBetween(String value1, String value2) {
            addCriterion("sjhth between", value1, value2, "sjhth");
            return (Criteria) this;
        }

        public Criteria andSjhthNotBetween(String value1, String value2) {
            addCriterion("sjhth not between", value1, value2, "sjhth");
            return (Criteria) this;
        }

        public Criteria andDkffsjIsNull() {
            addCriterion("dkffsj is null");
            return (Criteria) this;
        }

        public Criteria andDkffsjIsNotNull() {
            addCriterion("dkffsj is not null");
            return (Criteria) this;
        }

        public Criteria andDkffsjEqualTo(String value) {
            addCriterion("dkffsj =", value, "dkffsj");
            return (Criteria) this;
        }

        public Criteria andDkffsjNotEqualTo(String value) {
            addCriterion("dkffsj <>", value, "dkffsj");
            return (Criteria) this;
        }

        public Criteria andDkffsjGreaterThan(String value) {
            addCriterion("dkffsj >", value, "dkffsj");
            return (Criteria) this;
        }

        public Criteria andDkffsjGreaterThanOrEqualTo(String value) {
            addCriterion("dkffsj >=", value, "dkffsj");
            return (Criteria) this;
        }

        public Criteria andDkffsjLessThan(String value) {
            addCriterion("dkffsj <", value, "dkffsj");
            return (Criteria) this;
        }

        public Criteria andDkffsjLessThanOrEqualTo(String value) {
            addCriterion("dkffsj <=", value, "dkffsj");
            return (Criteria) this;
        }

        public Criteria andDkffsjLike(String value) {
            addCriterion("dkffsj like", value, "dkffsj");
            return (Criteria) this;
        }

        public Criteria andDkffsjNotLike(String value) {
            addCriterion("dkffsj not like", value, "dkffsj");
            return (Criteria) this;
        }

        public Criteria andDkffsjIn(List<String> values) {
            addCriterion("dkffsj in", values, "dkffsj");
            return (Criteria) this;
        }

        public Criteria andDkffsjNotIn(List<String> values) {
            addCriterion("dkffsj not in", values, "dkffsj");
            return (Criteria) this;
        }

        public Criteria andDkffsjBetween(String value1, String value2) {
            addCriterion("dkffsj between", value1, value2, "dkffsj");
            return (Criteria) this;
        }

        public Criteria andDkffsjNotBetween(String value1, String value2) {
            addCriterion("dkffsj not between", value1, value2, "dkffsj");
            return (Criteria) this;
        }

        public Criteria andDkjeIsNull() {
            addCriterion("dkje is null");
            return (Criteria) this;
        }

        public Criteria andDkjeIsNotNull() {
            addCriterion("dkje is not null");
            return (Criteria) this;
        }

        public Criteria andDkjeEqualTo(String value) {
            addCriterion("dkje =", value, "dkje");
            return (Criteria) this;
        }

        public Criteria andDkjeNotEqualTo(String value) {
            addCriterion("dkje <>", value, "dkje");
            return (Criteria) this;
        }

        public Criteria andDkjeGreaterThan(String value) {
            addCriterion("dkje >", value, "dkje");
            return (Criteria) this;
        }

        public Criteria andDkjeGreaterThanOrEqualTo(String value) {
            addCriterion("dkje >=", value, "dkje");
            return (Criteria) this;
        }

        public Criteria andDkjeLessThan(String value) {
            addCriterion("dkje <", value, "dkje");
            return (Criteria) this;
        }

        public Criteria andDkjeLessThanOrEqualTo(String value) {
            addCriterion("dkje <=", value, "dkje");
            return (Criteria) this;
        }

        public Criteria andDkjeLike(String value) {
            addCriterion("dkje like", value, "dkje");
            return (Criteria) this;
        }

        public Criteria andDkjeNotLike(String value) {
            addCriterion("dkje not like", value, "dkje");
            return (Criteria) this;
        }

        public Criteria andDkjeIn(List<String> values) {
            addCriterion("dkje in", values, "dkje");
            return (Criteria) this;
        }

        public Criteria andDkjeNotIn(List<String> values) {
            addCriterion("dkje not in", values, "dkje");
            return (Criteria) this;
        }

        public Criteria andDkjeBetween(String value1, String value2) {
            addCriterion("dkje between", value1, value2, "dkje");
            return (Criteria) this;
        }

        public Criteria andDkjeNotBetween(String value1, String value2) {
            addCriterion("dkje not between", value1, value2, "dkje");
            return (Criteria) this;
        }

        public Criteria andDkpzIsNull() {
            addCriterion("dkpz is null");
            return (Criteria) this;
        }

        public Criteria andDkpzIsNotNull() {
            addCriterion("dkpz is not null");
            return (Criteria) this;
        }

        public Criteria andDkpzEqualTo(String value) {
            addCriterion("dkpz =", value, "dkpz");
            return (Criteria) this;
        }

        public Criteria andDkpzNotEqualTo(String value) {
            addCriterion("dkpz <>", value, "dkpz");
            return (Criteria) this;
        }

        public Criteria andDkpzGreaterThan(String value) {
            addCriterion("dkpz >", value, "dkpz");
            return (Criteria) this;
        }

        public Criteria andDkpzGreaterThanOrEqualTo(String value) {
            addCriterion("dkpz >=", value, "dkpz");
            return (Criteria) this;
        }

        public Criteria andDkpzLessThan(String value) {
            addCriterion("dkpz <", value, "dkpz");
            return (Criteria) this;
        }

        public Criteria andDkpzLessThanOrEqualTo(String value) {
            addCriterion("dkpz <=", value, "dkpz");
            return (Criteria) this;
        }

        public Criteria andDkpzLike(String value) {
            addCriterion("dkpz like", value, "dkpz");
            return (Criteria) this;
        }

        public Criteria andDkpzNotLike(String value) {
            addCriterion("dkpz not like", value, "dkpz");
            return (Criteria) this;
        }

        public Criteria andDkpzIn(List<String> values) {
            addCriterion("dkpz in", values, "dkpz");
            return (Criteria) this;
        }

        public Criteria andDkpzNotIn(List<String> values) {
            addCriterion("dkpz not in", values, "dkpz");
            return (Criteria) this;
        }

        public Criteria andDkpzBetween(String value1, String value2) {
            addCriterion("dkpz between", value1, value2, "dkpz");
            return (Criteria) this;
        }

        public Criteria andDkpzNotBetween(String value1, String value2) {
            addCriterion("dkpz not between", value1, value2, "dkpz");
            return (Criteria) this;
        }

        public Criteria andDkqxIsNull() {
            addCriterion("dkqx is null");
            return (Criteria) this;
        }

        public Criteria andDkqxIsNotNull() {
            addCriterion("dkqx is not null");
            return (Criteria) this;
        }

        public Criteria andDkqxEqualTo(String value) {
            addCriterion("dkqx =", value, "dkqx");
            return (Criteria) this;
        }

        public Criteria andDkqxNotEqualTo(String value) {
            addCriterion("dkqx <>", value, "dkqx");
            return (Criteria) this;
        }

        public Criteria andDkqxGreaterThan(String value) {
            addCriterion("dkqx >", value, "dkqx");
            return (Criteria) this;
        }

        public Criteria andDkqxGreaterThanOrEqualTo(String value) {
            addCriterion("dkqx >=", value, "dkqx");
            return (Criteria) this;
        }

        public Criteria andDkqxLessThan(String value) {
            addCriterion("dkqx <", value, "dkqx");
            return (Criteria) this;
        }

        public Criteria andDkqxLessThanOrEqualTo(String value) {
            addCriterion("dkqx <=", value, "dkqx");
            return (Criteria) this;
        }

        public Criteria andDkqxLike(String value) {
            addCriterion("dkqx like", value, "dkqx");
            return (Criteria) this;
        }

        public Criteria andDkqxNotLike(String value) {
            addCriterion("dkqx not like", value, "dkqx");
            return (Criteria) this;
        }

        public Criteria andDkqxIn(List<String> values) {
            addCriterion("dkqx in", values, "dkqx");
            return (Criteria) this;
        }

        public Criteria andDkqxNotIn(List<String> values) {
            addCriterion("dkqx not in", values, "dkqx");
            return (Criteria) this;
        }

        public Criteria andDkqxBetween(String value1, String value2) {
            addCriterion("dkqx between", value1, value2, "dkqx");
            return (Criteria) this;
        }

        public Criteria andDkqxNotBetween(String value1, String value2) {
            addCriterion("dkqx not between", value1, value2, "dkqx");
            return (Criteria) this;
        }

        public Criteria andDbfsIsNull() {
            addCriterion("dbfs is null");
            return (Criteria) this;
        }

        public Criteria andDbfsIsNotNull() {
            addCriterion("dbfs is not null");
            return (Criteria) this;
        }

        public Criteria andDbfsEqualTo(String value) {
            addCriterion("dbfs =", value, "dbfs");
            return (Criteria) this;
        }

        public Criteria andDbfsNotEqualTo(String value) {
            addCriterion("dbfs <>", value, "dbfs");
            return (Criteria) this;
        }

        public Criteria andDbfsGreaterThan(String value) {
            addCriterion("dbfs >", value, "dbfs");
            return (Criteria) this;
        }

        public Criteria andDbfsGreaterThanOrEqualTo(String value) {
            addCriterion("dbfs >=", value, "dbfs");
            return (Criteria) this;
        }

        public Criteria andDbfsLessThan(String value) {
            addCriterion("dbfs <", value, "dbfs");
            return (Criteria) this;
        }

        public Criteria andDbfsLessThanOrEqualTo(String value) {
            addCriterion("dbfs <=", value, "dbfs");
            return (Criteria) this;
        }

        public Criteria andDbfsLike(String value) {
            addCriterion("dbfs like", value, "dbfs");
            return (Criteria) this;
        }

        public Criteria andDbfsNotLike(String value) {
            addCriterion("dbfs not like", value, "dbfs");
            return (Criteria) this;
        }

        public Criteria andDbfsIn(List<String> values) {
            addCriterion("dbfs in", values, "dbfs");
            return (Criteria) this;
        }

        public Criteria andDbfsNotIn(List<String> values) {
            addCriterion("dbfs not in", values, "dbfs");
            return (Criteria) this;
        }

        public Criteria andDbfsBetween(String value1, String value2) {
            addCriterion("dbfs between", value1, value2, "dbfs");
            return (Criteria) this;
        }

        public Criteria andDbfsNotBetween(String value1, String value2) {
            addCriterion("dbfs not between", value1, value2, "dbfs");
            return (Criteria) this;
        }

        public Criteria andHyzkIsNull() {
            addCriterion("hyzk is null");
            return (Criteria) this;
        }

        public Criteria andHyzkIsNotNull() {
            addCriterion("hyzk is not null");
            return (Criteria) this;
        }

        public Criteria andHyzkEqualTo(String value) {
            addCriterion("hyzk =", value, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkNotEqualTo(String value) {
            addCriterion("hyzk <>", value, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkGreaterThan(String value) {
            addCriterion("hyzk >", value, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkGreaterThanOrEqualTo(String value) {
            addCriterion("hyzk >=", value, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkLessThan(String value) {
            addCriterion("hyzk <", value, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkLessThanOrEqualTo(String value) {
            addCriterion("hyzk <=", value, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkLike(String value) {
            addCriterion("hyzk like", value, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkNotLike(String value) {
            addCriterion("hyzk not like", value, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkIn(List<String> values) {
            addCriterion("hyzk in", values, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkNotIn(List<String> values) {
            addCriterion("hyzk not in", values, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkBetween(String value1, String value2) {
            addCriterion("hyzk between", value1, value2, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkNotBetween(String value1, String value2) {
            addCriterion("hyzk not between", value1, value2, "hyzk");
            return (Criteria) this;
        }

        public Criteria andXlqkIsNull() {
            addCriterion("xlqk is null");
            return (Criteria) this;
        }

        public Criteria andXlqkIsNotNull() {
            addCriterion("xlqk is not null");
            return (Criteria) this;
        }

        public Criteria andXlqkEqualTo(String value) {
            addCriterion("xlqk =", value, "xlqk");
            return (Criteria) this;
        }

        public Criteria andXlqkNotEqualTo(String value) {
            addCriterion("xlqk <>", value, "xlqk");
            return (Criteria) this;
        }

        public Criteria andXlqkGreaterThan(String value) {
            addCriterion("xlqk >", value, "xlqk");
            return (Criteria) this;
        }

        public Criteria andXlqkGreaterThanOrEqualTo(String value) {
            addCriterion("xlqk >=", value, "xlqk");
            return (Criteria) this;
        }

        public Criteria andXlqkLessThan(String value) {
            addCriterion("xlqk <", value, "xlqk");
            return (Criteria) this;
        }

        public Criteria andXlqkLessThanOrEqualTo(String value) {
            addCriterion("xlqk <=", value, "xlqk");
            return (Criteria) this;
        }

        public Criteria andXlqkLike(String value) {
            addCriterion("xlqk like", value, "xlqk");
            return (Criteria) this;
        }

        public Criteria andXlqkNotLike(String value) {
            addCriterion("xlqk not like", value, "xlqk");
            return (Criteria) this;
        }

        public Criteria andXlqkIn(List<String> values) {
            addCriterion("xlqk in", values, "xlqk");
            return (Criteria) this;
        }

        public Criteria andXlqkNotIn(List<String> values) {
            addCriterion("xlqk not in", values, "xlqk");
            return (Criteria) this;
        }

        public Criteria andXlqkBetween(String value1, String value2) {
            addCriterion("xlqk between", value1, value2, "xlqk");
            return (Criteria) this;
        }

        public Criteria andXlqkNotBetween(String value1, String value2) {
            addCriterion("xlqk not between", value1, value2, "xlqk");
            return (Criteria) this;
        }

        public Criteria andGzdwIsNull() {
            addCriterion("gzdw is null");
            return (Criteria) this;
        }

        public Criteria andGzdwIsNotNull() {
            addCriterion("gzdw is not null");
            return (Criteria) this;
        }

        public Criteria andGzdwEqualTo(String value) {
            addCriterion("gzdw =", value, "gzdw");
            return (Criteria) this;
        }

        public Criteria andGzdwNotEqualTo(String value) {
            addCriterion("gzdw <>", value, "gzdw");
            return (Criteria) this;
        }

        public Criteria andGzdwGreaterThan(String value) {
            addCriterion("gzdw >", value, "gzdw");
            return (Criteria) this;
        }

        public Criteria andGzdwGreaterThanOrEqualTo(String value) {
            addCriterion("gzdw >=", value, "gzdw");
            return (Criteria) this;
        }

        public Criteria andGzdwLessThan(String value) {
            addCriterion("gzdw <", value, "gzdw");
            return (Criteria) this;
        }

        public Criteria andGzdwLessThanOrEqualTo(String value) {
            addCriterion("gzdw <=", value, "gzdw");
            return (Criteria) this;
        }

        public Criteria andGzdwLike(String value) {
            addCriterion("gzdw like", value, "gzdw");
            return (Criteria) this;
        }

        public Criteria andGzdwNotLike(String value) {
            addCriterion("gzdw not like", value, "gzdw");
            return (Criteria) this;
        }

        public Criteria andGzdwIn(List<String> values) {
            addCriterion("gzdw in", values, "gzdw");
            return (Criteria) this;
        }

        public Criteria andGzdwNotIn(List<String> values) {
            addCriterion("gzdw not in", values, "gzdw");
            return (Criteria) this;
        }

        public Criteria andGzdwBetween(String value1, String value2) {
            addCriterion("gzdw between", value1, value2, "gzdw");
            return (Criteria) this;
        }

        public Criteria andGzdwNotBetween(String value1, String value2) {
            addCriterion("gzdw not between", value1, value2, "gzdw");
            return (Criteria) this;
        }

        public Criteria andJkrzwIsNull() {
            addCriterion("jkrzw is null");
            return (Criteria) this;
        }

        public Criteria andJkrzwIsNotNull() {
            addCriterion("jkrzw is not null");
            return (Criteria) this;
        }

        public Criteria andJkrzwEqualTo(String value) {
            addCriterion("jkrzw =", value, "jkrzw");
            return (Criteria) this;
        }

        public Criteria andJkrzwNotEqualTo(String value) {
            addCriterion("jkrzw <>", value, "jkrzw");
            return (Criteria) this;
        }

        public Criteria andJkrzwGreaterThan(String value) {
            addCriterion("jkrzw >", value, "jkrzw");
            return (Criteria) this;
        }

        public Criteria andJkrzwGreaterThanOrEqualTo(String value) {
            addCriterion("jkrzw >=", value, "jkrzw");
            return (Criteria) this;
        }

        public Criteria andJkrzwLessThan(String value) {
            addCriterion("jkrzw <", value, "jkrzw");
            return (Criteria) this;
        }

        public Criteria andJkrzwLessThanOrEqualTo(String value) {
            addCriterion("jkrzw <=", value, "jkrzw");
            return (Criteria) this;
        }

        public Criteria andJkrzwLike(String value) {
            addCriterion("jkrzw like", value, "jkrzw");
            return (Criteria) this;
        }

        public Criteria andJkrzwNotLike(String value) {
            addCriterion("jkrzw not like", value, "jkrzw");
            return (Criteria) this;
        }

        public Criteria andJkrzwIn(List<String> values) {
            addCriterion("jkrzw in", values, "jkrzw");
            return (Criteria) this;
        }

        public Criteria andJkrzwNotIn(List<String> values) {
            addCriterion("jkrzw not in", values, "jkrzw");
            return (Criteria) this;
        }

        public Criteria andJkrzwBetween(String value1, String value2) {
            addCriterion("jkrzw between", value1, value2, "jkrzw");
            return (Criteria) this;
        }

        public Criteria andJkrzwNotBetween(String value1, String value2) {
            addCriterion("jkrzw not between", value1, value2, "jkrzw");
            return (Criteria) this;
        }

        public Criteria andHjdzIsNull() {
            addCriterion("hjdz is null");
            return (Criteria) this;
        }

        public Criteria andHjdzIsNotNull() {
            addCriterion("hjdz is not null");
            return (Criteria) this;
        }

        public Criteria andHjdzEqualTo(String value) {
            addCriterion("hjdz =", value, "hjdz");
            return (Criteria) this;
        }

        public Criteria andHjdzNotEqualTo(String value) {
            addCriterion("hjdz <>", value, "hjdz");
            return (Criteria) this;
        }

        public Criteria andHjdzGreaterThan(String value) {
            addCriterion("hjdz >", value, "hjdz");
            return (Criteria) this;
        }

        public Criteria andHjdzGreaterThanOrEqualTo(String value) {
            addCriterion("hjdz >=", value, "hjdz");
            return (Criteria) this;
        }

        public Criteria andHjdzLessThan(String value) {
            addCriterion("hjdz <", value, "hjdz");
            return (Criteria) this;
        }

        public Criteria andHjdzLessThanOrEqualTo(String value) {
            addCriterion("hjdz <=", value, "hjdz");
            return (Criteria) this;
        }

        public Criteria andHjdzLike(String value) {
            addCriterion("hjdz like", value, "hjdz");
            return (Criteria) this;
        }

        public Criteria andHjdzNotLike(String value) {
            addCriterion("hjdz not like", value, "hjdz");
            return (Criteria) this;
        }

        public Criteria andHjdzIn(List<String> values) {
            addCriterion("hjdz in", values, "hjdz");
            return (Criteria) this;
        }

        public Criteria andHjdzNotIn(List<String> values) {
            addCriterion("hjdz not in", values, "hjdz");
            return (Criteria) this;
        }

        public Criteria andHjdzBetween(String value1, String value2) {
            addCriterion("hjdz between", value1, value2, "hjdz");
            return (Criteria) this;
        }

        public Criteria andHjdzNotBetween(String value1, String value2) {
            addCriterion("hjdz not between", value1, value2, "hjdz");
            return (Criteria) this;
        }

        public Criteria andXjzdzIsNull() {
            addCriterion("xjzdz is null");
            return (Criteria) this;
        }

        public Criteria andXjzdzIsNotNull() {
            addCriterion("xjzdz is not null");
            return (Criteria) this;
        }

        public Criteria andXjzdzEqualTo(String value) {
            addCriterion("xjzdz =", value, "xjzdz");
            return (Criteria) this;
        }

        public Criteria andXjzdzNotEqualTo(String value) {
            addCriterion("xjzdz <>", value, "xjzdz");
            return (Criteria) this;
        }

        public Criteria andXjzdzGreaterThan(String value) {
            addCriterion("xjzdz >", value, "xjzdz");
            return (Criteria) this;
        }

        public Criteria andXjzdzGreaterThanOrEqualTo(String value) {
            addCriterion("xjzdz >=", value, "xjzdz");
            return (Criteria) this;
        }

        public Criteria andXjzdzLessThan(String value) {
            addCriterion("xjzdz <", value, "xjzdz");
            return (Criteria) this;
        }

        public Criteria andXjzdzLessThanOrEqualTo(String value) {
            addCriterion("xjzdz <=", value, "xjzdz");
            return (Criteria) this;
        }

        public Criteria andXjzdzLike(String value) {
            addCriterion("xjzdz like", value, "xjzdz");
            return (Criteria) this;
        }

        public Criteria andXjzdzNotLike(String value) {
            addCriterion("xjzdz not like", value, "xjzdz");
            return (Criteria) this;
        }

        public Criteria andXjzdzIn(List<String> values) {
            addCriterion("xjzdz in", values, "xjzdz");
            return (Criteria) this;
        }

        public Criteria andXjzdzNotIn(List<String> values) {
            addCriterion("xjzdz not in", values, "xjzdz");
            return (Criteria) this;
        }

        public Criteria andXjzdzBetween(String value1, String value2) {
            addCriterion("xjzdz between", value1, value2, "xjzdz");
            return (Criteria) this;
        }

        public Criteria andXjzdzNotBetween(String value1, String value2) {
            addCriterion("xjzdz not between", value1, value2, "xjzdz");
            return (Criteria) this;
        }

        public Criteria andSfhtqxnIsNull() {
            addCriterion("sfhtqxn is null");
            return (Criteria) this;
        }

        public Criteria andSfhtqxnIsNotNull() {
            addCriterion("sfhtqxn is not null");
            return (Criteria) this;
        }

        public Criteria andSfhtqxnEqualTo(String value) {
            addCriterion("sfhtqxn =", value, "sfhtqxn");
            return (Criteria) this;
        }

        public Criteria andSfhtqxnNotEqualTo(String value) {
            addCriterion("sfhtqxn <>", value, "sfhtqxn");
            return (Criteria) this;
        }

        public Criteria andSfhtqxnGreaterThan(String value) {
            addCriterion("sfhtqxn >", value, "sfhtqxn");
            return (Criteria) this;
        }

        public Criteria andSfhtqxnGreaterThanOrEqualTo(String value) {
            addCriterion("sfhtqxn >=", value, "sfhtqxn");
            return (Criteria) this;
        }

        public Criteria andSfhtqxnLessThan(String value) {
            addCriterion("sfhtqxn <", value, "sfhtqxn");
            return (Criteria) this;
        }

        public Criteria andSfhtqxnLessThanOrEqualTo(String value) {
            addCriterion("sfhtqxn <=", value, "sfhtqxn");
            return (Criteria) this;
        }

        public Criteria andSfhtqxnLike(String value) {
            addCriterion("sfhtqxn like", value, "sfhtqxn");
            return (Criteria) this;
        }

        public Criteria andSfhtqxnNotLike(String value) {
            addCriterion("sfhtqxn not like", value, "sfhtqxn");
            return (Criteria) this;
        }

        public Criteria andSfhtqxnIn(List<String> values) {
            addCriterion("sfhtqxn in", values, "sfhtqxn");
            return (Criteria) this;
        }

        public Criteria andSfhtqxnNotIn(List<String> values) {
            addCriterion("sfhtqxn not in", values, "sfhtqxn");
            return (Criteria) this;
        }

        public Criteria andSfhtqxnBetween(String value1, String value2) {
            addCriterion("sfhtqxn between", value1, value2, "sfhtqxn");
            return (Criteria) this;
        }

        public Criteria andSfhtqxnNotBetween(String value1, String value2) {
            addCriterion("sfhtqxn not between", value1, value2, "sfhtqxn");
            return (Criteria) this;
        }

        public Criteria andBzqxnmsxgnrIsNull() {
            addCriterion("bzqxnmsxgnr is null");
            return (Criteria) this;
        }

        public Criteria andBzqxnmsxgnrIsNotNull() {
            addCriterion("bzqxnmsxgnr is not null");
            return (Criteria) this;
        }

        public Criteria andBzqxnmsxgnrEqualTo(String value) {
            addCriterion("bzqxnmsxgnr =", value, "bzqxnmsxgnr");
            return (Criteria) this;
        }

        public Criteria andBzqxnmsxgnrNotEqualTo(String value) {
            addCriterion("bzqxnmsxgnr <>", value, "bzqxnmsxgnr");
            return (Criteria) this;
        }

        public Criteria andBzqxnmsxgnrGreaterThan(String value) {
            addCriterion("bzqxnmsxgnr >", value, "bzqxnmsxgnr");
            return (Criteria) this;
        }

        public Criteria andBzqxnmsxgnrGreaterThanOrEqualTo(String value) {
            addCriterion("bzqxnmsxgnr >=", value, "bzqxnmsxgnr");
            return (Criteria) this;
        }

        public Criteria andBzqxnmsxgnrLessThan(String value) {
            addCriterion("bzqxnmsxgnr <", value, "bzqxnmsxgnr");
            return (Criteria) this;
        }

        public Criteria andBzqxnmsxgnrLessThanOrEqualTo(String value) {
            addCriterion("bzqxnmsxgnr <=", value, "bzqxnmsxgnr");
            return (Criteria) this;
        }

        public Criteria andBzqxnmsxgnrLike(String value) {
            addCriterion("bzqxnmsxgnr like", value, "bzqxnmsxgnr");
            return (Criteria) this;
        }

        public Criteria andBzqxnmsxgnrNotLike(String value) {
            addCriterion("bzqxnmsxgnr not like", value, "bzqxnmsxgnr");
            return (Criteria) this;
        }

        public Criteria andBzqxnmsxgnrIn(List<String> values) {
            addCriterion("bzqxnmsxgnr in", values, "bzqxnmsxgnr");
            return (Criteria) this;
        }

        public Criteria andBzqxnmsxgnrNotIn(List<String> values) {
            addCriterion("bzqxnmsxgnr not in", values, "bzqxnmsxgnr");
            return (Criteria) this;
        }

        public Criteria andBzqxnmsxgnrBetween(String value1, String value2) {
            addCriterion("bzqxnmsxgnr between", value1, value2, "bzqxnmsxgnr");
            return (Criteria) this;
        }

        public Criteria andBzqxnmsxgnrNotBetween(String value1, String value2) {
            addCriterion("bzqxnmsxgnr not between", value1, value2, "bzqxnmsxgnr");
            return (Criteria) this;
        }

        public Criteria andHtqdrIsNull() {
            addCriterion("htqdr is null");
            return (Criteria) this;
        }

        public Criteria andHtqdrIsNotNull() {
            addCriterion("htqdr is not null");
            return (Criteria) this;
        }

        public Criteria andHtqdrEqualTo(String value) {
            addCriterion("htqdr =", value, "htqdr");
            return (Criteria) this;
        }

        public Criteria andHtqdrNotEqualTo(String value) {
            addCriterion("htqdr <>", value, "htqdr");
            return (Criteria) this;
        }

        public Criteria andHtqdrGreaterThan(String value) {
            addCriterion("htqdr >", value, "htqdr");
            return (Criteria) this;
        }

        public Criteria andHtqdrGreaterThanOrEqualTo(String value) {
            addCriterion("htqdr >=", value, "htqdr");
            return (Criteria) this;
        }

        public Criteria andHtqdrLessThan(String value) {
            addCriterion("htqdr <", value, "htqdr");
            return (Criteria) this;
        }

        public Criteria andHtqdrLessThanOrEqualTo(String value) {
            addCriterion("htqdr <=", value, "htqdr");
            return (Criteria) this;
        }

        public Criteria andHtqdrLike(String value) {
            addCriterion("htqdr like", value, "htqdr");
            return (Criteria) this;
        }

        public Criteria andHtqdrNotLike(String value) {
            addCriterion("htqdr not like", value, "htqdr");
            return (Criteria) this;
        }

        public Criteria andHtqdrIn(List<String> values) {
            addCriterion("htqdr in", values, "htqdr");
            return (Criteria) this;
        }

        public Criteria andHtqdrNotIn(List<String> values) {
            addCriterion("htqdr not in", values, "htqdr");
            return (Criteria) this;
        }

        public Criteria andHtqdrBetween(String value1, String value2) {
            addCriterion("htqdr between", value1, value2, "htqdr");
            return (Criteria) this;
        }

        public Criteria andHtqdrNotBetween(String value1, String value2) {
            addCriterion("htqdr not between", value1, value2, "htqdr");
            return (Criteria) this;
        }

        public Criteria andDywdzIsNull() {
            addCriterion("dywdz is null");
            return (Criteria) this;
        }

        public Criteria andDywdzIsNotNull() {
            addCriterion("dywdz is not null");
            return (Criteria) this;
        }

        public Criteria andDywdzEqualTo(String value) {
            addCriterion("dywdz =", value, "dywdz");
            return (Criteria) this;
        }

        public Criteria andDywdzNotEqualTo(String value) {
            addCriterion("dywdz <>", value, "dywdz");
            return (Criteria) this;
        }

        public Criteria andDywdzGreaterThan(String value) {
            addCriterion("dywdz >", value, "dywdz");
            return (Criteria) this;
        }

        public Criteria andDywdzGreaterThanOrEqualTo(String value) {
            addCriterion("dywdz >=", value, "dywdz");
            return (Criteria) this;
        }

        public Criteria andDywdzLessThan(String value) {
            addCriterion("dywdz <", value, "dywdz");
            return (Criteria) this;
        }

        public Criteria andDywdzLessThanOrEqualTo(String value) {
            addCriterion("dywdz <=", value, "dywdz");
            return (Criteria) this;
        }

        public Criteria andDywdzLike(String value) {
            addCriterion("dywdz like", value, "dywdz");
            return (Criteria) this;
        }

        public Criteria andDywdzNotLike(String value) {
            addCriterion("dywdz not like", value, "dywdz");
            return (Criteria) this;
        }

        public Criteria andDywdzIn(List<String> values) {
            addCriterion("dywdz in", values, "dywdz");
            return (Criteria) this;
        }

        public Criteria andDywdzNotIn(List<String> values) {
            addCriterion("dywdz not in", values, "dywdz");
            return (Criteria) this;
        }

        public Criteria andDywdzBetween(String value1, String value2) {
            addCriterion("dywdz between", value1, value2, "dywdz");
            return (Criteria) this;
        }

        public Criteria andDywdzNotBetween(String value1, String value2) {
            addCriterion("dywdz not between", value1, value2, "dywdz");
            return (Criteria) this;
        }

        public Criteria andTdxzIsNull() {
            addCriterion("tdxz is null");
            return (Criteria) this;
        }

        public Criteria andTdxzIsNotNull() {
            addCriterion("tdxz is not null");
            return (Criteria) this;
        }

        public Criteria andTdxzEqualTo(String value) {
            addCriterion("tdxz =", value, "tdxz");
            return (Criteria) this;
        }

        public Criteria andTdxzNotEqualTo(String value) {
            addCriterion("tdxz <>", value, "tdxz");
            return (Criteria) this;
        }

        public Criteria andTdxzGreaterThan(String value) {
            addCriterion("tdxz >", value, "tdxz");
            return (Criteria) this;
        }

        public Criteria andTdxzGreaterThanOrEqualTo(String value) {
            addCriterion("tdxz >=", value, "tdxz");
            return (Criteria) this;
        }

        public Criteria andTdxzLessThan(String value) {
            addCriterion("tdxz <", value, "tdxz");
            return (Criteria) this;
        }

        public Criteria andTdxzLessThanOrEqualTo(String value) {
            addCriterion("tdxz <=", value, "tdxz");
            return (Criteria) this;
        }

        public Criteria andTdxzLike(String value) {
            addCriterion("tdxz like", value, "tdxz");
            return (Criteria) this;
        }

        public Criteria andTdxzNotLike(String value) {
            addCriterion("tdxz not like", value, "tdxz");
            return (Criteria) this;
        }

        public Criteria andTdxzIn(List<String> values) {
            addCriterion("tdxz in", values, "tdxz");
            return (Criteria) this;
        }

        public Criteria andTdxzNotIn(List<String> values) {
            addCriterion("tdxz not in", values, "tdxz");
            return (Criteria) this;
        }

        public Criteria andTdxzBetween(String value1, String value2) {
            addCriterion("tdxz between", value1, value2, "tdxz");
            return (Criteria) this;
        }

        public Criteria andTdxzNotBetween(String value1, String value2) {
            addCriterion("tdxz not between", value1, value2, "tdxz");
            return (Criteria) this;
        }

        public Criteria andTdmjIsNull() {
            addCriterion("tdmj is null");
            return (Criteria) this;
        }

        public Criteria andTdmjIsNotNull() {
            addCriterion("tdmj is not null");
            return (Criteria) this;
        }

        public Criteria andTdmjEqualTo(String value) {
            addCriterion("tdmj =", value, "tdmj");
            return (Criteria) this;
        }

        public Criteria andTdmjNotEqualTo(String value) {
            addCriterion("tdmj <>", value, "tdmj");
            return (Criteria) this;
        }

        public Criteria andTdmjGreaterThan(String value) {
            addCriterion("tdmj >", value, "tdmj");
            return (Criteria) this;
        }

        public Criteria andTdmjGreaterThanOrEqualTo(String value) {
            addCriterion("tdmj >=", value, "tdmj");
            return (Criteria) this;
        }

        public Criteria andTdmjLessThan(String value) {
            addCriterion("tdmj <", value, "tdmj");
            return (Criteria) this;
        }

        public Criteria andTdmjLessThanOrEqualTo(String value) {
            addCriterion("tdmj <=", value, "tdmj");
            return (Criteria) this;
        }

        public Criteria andTdmjLike(String value) {
            addCriterion("tdmj like", value, "tdmj");
            return (Criteria) this;
        }

        public Criteria andTdmjNotLike(String value) {
            addCriterion("tdmj not like", value, "tdmj");
            return (Criteria) this;
        }

        public Criteria andTdmjIn(List<String> values) {
            addCriterion("tdmj in", values, "tdmj");
            return (Criteria) this;
        }

        public Criteria andTdmjNotIn(List<String> values) {
            addCriterion("tdmj not in", values, "tdmj");
            return (Criteria) this;
        }

        public Criteria andTdmjBetween(String value1, String value2) {
            addCriterion("tdmj between", value1, value2, "tdmj");
            return (Criteria) this;
        }

        public Criteria andTdmjNotBetween(String value1, String value2) {
            addCriterion("tdmj not between", value1, value2, "tdmj");
            return (Criteria) this;
        }

        public Criteria andFcxzIsNull() {
            addCriterion("fcxz is null");
            return (Criteria) this;
        }

        public Criteria andFcxzIsNotNull() {
            addCriterion("fcxz is not null");
            return (Criteria) this;
        }

        public Criteria andFcxzEqualTo(String value) {
            addCriterion("fcxz =", value, "fcxz");
            return (Criteria) this;
        }

        public Criteria andFcxzNotEqualTo(String value) {
            addCriterion("fcxz <>", value, "fcxz");
            return (Criteria) this;
        }

        public Criteria andFcxzGreaterThan(String value) {
            addCriterion("fcxz >", value, "fcxz");
            return (Criteria) this;
        }

        public Criteria andFcxzGreaterThanOrEqualTo(String value) {
            addCriterion("fcxz >=", value, "fcxz");
            return (Criteria) this;
        }

        public Criteria andFcxzLessThan(String value) {
            addCriterion("fcxz <", value, "fcxz");
            return (Criteria) this;
        }

        public Criteria andFcxzLessThanOrEqualTo(String value) {
            addCriterion("fcxz <=", value, "fcxz");
            return (Criteria) this;
        }

        public Criteria andFcxzLike(String value) {
            addCriterion("fcxz like", value, "fcxz");
            return (Criteria) this;
        }

        public Criteria andFcxzNotLike(String value) {
            addCriterion("fcxz not like", value, "fcxz");
            return (Criteria) this;
        }

        public Criteria andFcxzIn(List<String> values) {
            addCriterion("fcxz in", values, "fcxz");
            return (Criteria) this;
        }

        public Criteria andFcxzNotIn(List<String> values) {
            addCriterion("fcxz not in", values, "fcxz");
            return (Criteria) this;
        }

        public Criteria andFcxzBetween(String value1, String value2) {
            addCriterion("fcxz between", value1, value2, "fcxz");
            return (Criteria) this;
        }

        public Criteria andFcxzNotBetween(String value1, String value2) {
            addCriterion("fcxz not between", value1, value2, "fcxz");
            return (Criteria) this;
        }

        public Criteria andFcmjIsNull() {
            addCriterion("fcmj is null");
            return (Criteria) this;
        }

        public Criteria andFcmjIsNotNull() {
            addCriterion("fcmj is not null");
            return (Criteria) this;
        }

        public Criteria andFcmjEqualTo(String value) {
            addCriterion("fcmj =", value, "fcmj");
            return (Criteria) this;
        }

        public Criteria andFcmjNotEqualTo(String value) {
            addCriterion("fcmj <>", value, "fcmj");
            return (Criteria) this;
        }

        public Criteria andFcmjGreaterThan(String value) {
            addCriterion("fcmj >", value, "fcmj");
            return (Criteria) this;
        }

        public Criteria andFcmjGreaterThanOrEqualTo(String value) {
            addCriterion("fcmj >=", value, "fcmj");
            return (Criteria) this;
        }

        public Criteria andFcmjLessThan(String value) {
            addCriterion("fcmj <", value, "fcmj");
            return (Criteria) this;
        }

        public Criteria andFcmjLessThanOrEqualTo(String value) {
            addCriterion("fcmj <=", value, "fcmj");
            return (Criteria) this;
        }

        public Criteria andFcmjLike(String value) {
            addCriterion("fcmj like", value, "fcmj");
            return (Criteria) this;
        }

        public Criteria andFcmjNotLike(String value) {
            addCriterion("fcmj not like", value, "fcmj");
            return (Criteria) this;
        }

        public Criteria andFcmjIn(List<String> values) {
            addCriterion("fcmj in", values, "fcmj");
            return (Criteria) this;
        }

        public Criteria andFcmjNotIn(List<String> values) {
            addCriterion("fcmj not in", values, "fcmj");
            return (Criteria) this;
        }

        public Criteria andFcmjBetween(String value1, String value2) {
            addCriterion("fcmj between", value1, value2, "fcmj");
            return (Criteria) this;
        }

        public Criteria andFcmjNotBetween(String value1, String value2) {
            addCriterion("fcmj not between", value1, value2, "fcmj");
            return (Criteria) this;
        }

        public Criteria andDywwpjzIsNull() {
            addCriterion("dywwpjz is null");
            return (Criteria) this;
        }

        public Criteria andDywwpjzIsNotNull() {
            addCriterion("dywwpjz is not null");
            return (Criteria) this;
        }

        public Criteria andDywwpjzEqualTo(String value) {
            addCriterion("dywwpjz =", value, "dywwpjz");
            return (Criteria) this;
        }

        public Criteria andDywwpjzNotEqualTo(String value) {
            addCriterion("dywwpjz <>", value, "dywwpjz");
            return (Criteria) this;
        }

        public Criteria andDywwpjzGreaterThan(String value) {
            addCriterion("dywwpjz >", value, "dywwpjz");
            return (Criteria) this;
        }

        public Criteria andDywwpjzGreaterThanOrEqualTo(String value) {
            addCriterion("dywwpjz >=", value, "dywwpjz");
            return (Criteria) this;
        }

        public Criteria andDywwpjzLessThan(String value) {
            addCriterion("dywwpjz <", value, "dywwpjz");
            return (Criteria) this;
        }

        public Criteria andDywwpjzLessThanOrEqualTo(String value) {
            addCriterion("dywwpjz <=", value, "dywwpjz");
            return (Criteria) this;
        }

        public Criteria andDywwpjzLike(String value) {
            addCriterion("dywwpjz like", value, "dywwpjz");
            return (Criteria) this;
        }

        public Criteria andDywwpjzNotLike(String value) {
            addCriterion("dywwpjz not like", value, "dywwpjz");
            return (Criteria) this;
        }

        public Criteria andDywwpjzIn(List<String> values) {
            addCriterion("dywwpjz in", values, "dywwpjz");
            return (Criteria) this;
        }

        public Criteria andDywwpjzNotIn(List<String> values) {
            addCriterion("dywwpjz not in", values, "dywwpjz");
            return (Criteria) this;
        }

        public Criteria andDywwpjzBetween(String value1, String value2) {
            addCriterion("dywwpjz between", value1, value2, "dywwpjz");
            return (Criteria) this;
        }

        public Criteria andDywwpjzNotBetween(String value1, String value2) {
            addCriterion("dywwpjz not between", value1, value2, "dywwpjz");
            return (Criteria) this;
        }

        public Criteria andDywxzIsNull() {
            addCriterion("dywxz is null");
            return (Criteria) this;
        }

        public Criteria andDywxzIsNotNull() {
            addCriterion("dywxz is not null");
            return (Criteria) this;
        }

        public Criteria andDywxzEqualTo(String value) {
            addCriterion("dywxz =", value, "dywxz");
            return (Criteria) this;
        }

        public Criteria andDywxzNotEqualTo(String value) {
            addCriterion("dywxz <>", value, "dywxz");
            return (Criteria) this;
        }

        public Criteria andDywxzGreaterThan(String value) {
            addCriterion("dywxz >", value, "dywxz");
            return (Criteria) this;
        }

        public Criteria andDywxzGreaterThanOrEqualTo(String value) {
            addCriterion("dywxz >=", value, "dywxz");
            return (Criteria) this;
        }

        public Criteria andDywxzLessThan(String value) {
            addCriterion("dywxz <", value, "dywxz");
            return (Criteria) this;
        }

        public Criteria andDywxzLessThanOrEqualTo(String value) {
            addCriterion("dywxz <=", value, "dywxz");
            return (Criteria) this;
        }

        public Criteria andDywxzLike(String value) {
            addCriterion("dywxz like", value, "dywxz");
            return (Criteria) this;
        }

        public Criteria andDywxzNotLike(String value) {
            addCriterion("dywxz not like", value, "dywxz");
            return (Criteria) this;
        }

        public Criteria andDywxzIn(List<String> values) {
            addCriterion("dywxz in", values, "dywxz");
            return (Criteria) this;
        }

        public Criteria andDywxzNotIn(List<String> values) {
            addCriterion("dywxz not in", values, "dywxz");
            return (Criteria) this;
        }

        public Criteria andDywxzBetween(String value1, String value2) {
            addCriterion("dywxz between", value1, value2, "dywxz");
            return (Criteria) this;
        }

        public Criteria andDywxzNotBetween(String value1, String value2) {
            addCriterion("dywxz not between", value1, value2, "dywxz");
            return (Criteria) this;
        }

        public Criteria andSfqscfIsNull() {
            addCriterion("sfqscf is null");
            return (Criteria) this;
        }

        public Criteria andSfqscfIsNotNull() {
            addCriterion("sfqscf is not null");
            return (Criteria) this;
        }

        public Criteria andSfqscfEqualTo(String value) {
            addCriterion("sfqscf =", value, "sfqscf");
            return (Criteria) this;
        }

        public Criteria andSfqscfNotEqualTo(String value) {
            addCriterion("sfqscf <>", value, "sfqscf");
            return (Criteria) this;
        }

        public Criteria andSfqscfGreaterThan(String value) {
            addCriterion("sfqscf >", value, "sfqscf");
            return (Criteria) this;
        }

        public Criteria andSfqscfGreaterThanOrEqualTo(String value) {
            addCriterion("sfqscf >=", value, "sfqscf");
            return (Criteria) this;
        }

        public Criteria andSfqscfLessThan(String value) {
            addCriterion("sfqscf <", value, "sfqscf");
            return (Criteria) this;
        }

        public Criteria andSfqscfLessThanOrEqualTo(String value) {
            addCriterion("sfqscf <=", value, "sfqscf");
            return (Criteria) this;
        }

        public Criteria andSfqscfLike(String value) {
            addCriterion("sfqscf like", value, "sfqscf");
            return (Criteria) this;
        }

        public Criteria andSfqscfNotLike(String value) {
            addCriterion("sfqscf not like", value, "sfqscf");
            return (Criteria) this;
        }

        public Criteria andSfqscfIn(List<String> values) {
            addCriterion("sfqscf in", values, "sfqscf");
            return (Criteria) this;
        }

        public Criteria andSfqscfNotIn(List<String> values) {
            addCriterion("sfqscf not in", values, "sfqscf");
            return (Criteria) this;
        }

        public Criteria andSfqscfBetween(String value1, String value2) {
            addCriterion("sfqscf between", value1, value2, "sfqscf");
            return (Criteria) this;
        }

        public Criteria andSfqscfNotBetween(String value1, String value2) {
            addCriterion("sfqscf not between", value1, value2, "sfqscf");
            return (Criteria) this;
        }

        public Criteria andQszkIsNull() {
            addCriterion("qszk is null");
            return (Criteria) this;
        }

        public Criteria andQszkIsNotNull() {
            addCriterion("qszk is not null");
            return (Criteria) this;
        }

        public Criteria andQszkEqualTo(String value) {
            addCriterion("qszk =", value, "qszk");
            return (Criteria) this;
        }

        public Criteria andQszkNotEqualTo(String value) {
            addCriterion("qszk <>", value, "qszk");
            return (Criteria) this;
        }

        public Criteria andQszkGreaterThan(String value) {
            addCriterion("qszk >", value, "qszk");
            return (Criteria) this;
        }

        public Criteria andQszkGreaterThanOrEqualTo(String value) {
            addCriterion("qszk >=", value, "qszk");
            return (Criteria) this;
        }

        public Criteria andQszkLessThan(String value) {
            addCriterion("qszk <", value, "qszk");
            return (Criteria) this;
        }

        public Criteria andQszkLessThanOrEqualTo(String value) {
            addCriterion("qszk <=", value, "qszk");
            return (Criteria) this;
        }

        public Criteria andQszkLike(String value) {
            addCriterion("qszk like", value, "qszk");
            return (Criteria) this;
        }

        public Criteria andQszkNotLike(String value) {
            addCriterion("qszk not like", value, "qszk");
            return (Criteria) this;
        }

        public Criteria andQszkIn(List<String> values) {
            addCriterion("qszk in", values, "qszk");
            return (Criteria) this;
        }

        public Criteria andQszkNotIn(List<String> values) {
            addCriterion("qszk not in", values, "qszk");
            return (Criteria) this;
        }

        public Criteria andQszkBetween(String value1, String value2) {
            addCriterion("qszk between", value1, value2, "qszk");
            return (Criteria) this;
        }

        public Criteria andQszkNotBetween(String value1, String value2) {
            addCriterion("qszk not between", value1, value2, "qszk");
            return (Criteria) this;
        }

        public Criteria andSfybzrIsNull() {
            addCriterion("sfybzr is null");
            return (Criteria) this;
        }

        public Criteria andSfybzrIsNotNull() {
            addCriterion("sfybzr is not null");
            return (Criteria) this;
        }

        public Criteria andSfybzrEqualTo(String value) {
            addCriterion("sfybzr =", value, "sfybzr");
            return (Criteria) this;
        }

        public Criteria andSfybzrNotEqualTo(String value) {
            addCriterion("sfybzr <>", value, "sfybzr");
            return (Criteria) this;
        }

        public Criteria andSfybzrGreaterThan(String value) {
            addCriterion("sfybzr >", value, "sfybzr");
            return (Criteria) this;
        }

        public Criteria andSfybzrGreaterThanOrEqualTo(String value) {
            addCriterion("sfybzr >=", value, "sfybzr");
            return (Criteria) this;
        }

        public Criteria andSfybzrLessThan(String value) {
            addCriterion("sfybzr <", value, "sfybzr");
            return (Criteria) this;
        }

        public Criteria andSfybzrLessThanOrEqualTo(String value) {
            addCriterion("sfybzr <=", value, "sfybzr");
            return (Criteria) this;
        }

        public Criteria andSfybzrLike(String value) {
            addCriterion("sfybzr like", value, "sfybzr");
            return (Criteria) this;
        }

        public Criteria andSfybzrNotLike(String value) {
            addCriterion("sfybzr not like", value, "sfybzr");
            return (Criteria) this;
        }

        public Criteria andSfybzrIn(List<String> values) {
            addCriterion("sfybzr in", values, "sfybzr");
            return (Criteria) this;
        }

        public Criteria andSfybzrNotIn(List<String> values) {
            addCriterion("sfybzr not in", values, "sfybzr");
            return (Criteria) this;
        }

        public Criteria andSfybzrBetween(String value1, String value2) {
            addCriterion("sfybzr between", value1, value2, "sfybzr");
            return (Criteria) this;
        }

        public Criteria andSfybzrNotBetween(String value1, String value2) {
            addCriterion("sfybzr not between", value1, value2, "sfybzr");
            return (Criteria) this;
        }

        public Criteria andBzrqkIsNull() {
            addCriterion("bzrqk is null");
            return (Criteria) this;
        }

        public Criteria andBzrqkIsNotNull() {
            addCriterion("bzrqk is not null");
            return (Criteria) this;
        }

        public Criteria andBzrqkEqualTo(String value) {
            addCriterion("bzrqk =", value, "bzrqk");
            return (Criteria) this;
        }

        public Criteria andBzrqkNotEqualTo(String value) {
            addCriterion("bzrqk <>", value, "bzrqk");
            return (Criteria) this;
        }

        public Criteria andBzrqkGreaterThan(String value) {
            addCriterion("bzrqk >", value, "bzrqk");
            return (Criteria) this;
        }

        public Criteria andBzrqkGreaterThanOrEqualTo(String value) {
            addCriterion("bzrqk >=", value, "bzrqk");
            return (Criteria) this;
        }

        public Criteria andBzrqkLessThan(String value) {
            addCriterion("bzrqk <", value, "bzrqk");
            return (Criteria) this;
        }

        public Criteria andBzrqkLessThanOrEqualTo(String value) {
            addCriterion("bzrqk <=", value, "bzrqk");
            return (Criteria) this;
        }

        public Criteria andBzrqkLike(String value) {
            addCriterion("bzrqk like", value, "bzrqk");
            return (Criteria) this;
        }

        public Criteria andBzrqkNotLike(String value) {
            addCriterion("bzrqk not like", value, "bzrqk");
            return (Criteria) this;
        }

        public Criteria andBzrqkIn(List<String> values) {
            addCriterion("bzrqk in", values, "bzrqk");
            return (Criteria) this;
        }

        public Criteria andBzrqkNotIn(List<String> values) {
            addCriterion("bzrqk not in", values, "bzrqk");
            return (Criteria) this;
        }

        public Criteria andBzrqkBetween(String value1, String value2) {
            addCriterion("bzrqk between", value1, value2, "bzrqk");
            return (Criteria) this;
        }

        public Criteria andBzrqkNotBetween(String value1, String value2) {
            addCriterion("bzrqk not between", value1, value2, "bzrqk");
            return (Criteria) this;
        }

        public Criteria andSsbqIsNull() {
            addCriterion("ssbq is null");
            return (Criteria) this;
        }

        public Criteria andSsbqIsNotNull() {
            addCriterion("ssbq is not null");
            return (Criteria) this;
        }

        public Criteria andSsbqEqualTo(String value) {
            addCriterion("ssbq =", value, "ssbq");
            return (Criteria) this;
        }

        public Criteria andSsbqNotEqualTo(String value) {
            addCriterion("ssbq <>", value, "ssbq");
            return (Criteria) this;
        }

        public Criteria andSsbqGreaterThan(String value) {
            addCriterion("ssbq >", value, "ssbq");
            return (Criteria) this;
        }

        public Criteria andSsbqGreaterThanOrEqualTo(String value) {
            addCriterion("ssbq >=", value, "ssbq");
            return (Criteria) this;
        }

        public Criteria andSsbqLessThan(String value) {
            addCriterion("ssbq <", value, "ssbq");
            return (Criteria) this;
        }

        public Criteria andSsbqLessThanOrEqualTo(String value) {
            addCriterion("ssbq <=", value, "ssbq");
            return (Criteria) this;
        }

        public Criteria andSsbqLike(String value) {
            addCriterion("ssbq like", value, "ssbq");
            return (Criteria) this;
        }

        public Criteria andSsbqNotLike(String value) {
            addCriterion("ssbq not like", value, "ssbq");
            return (Criteria) this;
        }

        public Criteria andSsbqIn(List<String> values) {
            addCriterion("ssbq in", values, "ssbq");
            return (Criteria) this;
        }

        public Criteria andSsbqNotIn(List<String> values) {
            addCriterion("ssbq not in", values, "ssbq");
            return (Criteria) this;
        }

        public Criteria andSsbqBetween(String value1, String value2) {
            addCriterion("ssbq between", value1, value2, "ssbq");
            return (Criteria) this;
        }

        public Criteria andSsbqNotBetween(String value1, String value2) {
            addCriterion("ssbq not between", value1, value2, "ssbq");
            return (Criteria) this;
        }

        public Criteria andSsbqqkIsNull() {
            addCriterion("ssbqqk is null");
            return (Criteria) this;
        }

        public Criteria andSsbqqkIsNotNull() {
            addCriterion("ssbqqk is not null");
            return (Criteria) this;
        }

        public Criteria andSsbqqkEqualTo(String value) {
            addCriterion("ssbqqk =", value, "ssbqqk");
            return (Criteria) this;
        }

        public Criteria andSsbqqkNotEqualTo(String value) {
            addCriterion("ssbqqk <>", value, "ssbqqk");
            return (Criteria) this;
        }

        public Criteria andSsbqqkGreaterThan(String value) {
            addCriterion("ssbqqk >", value, "ssbqqk");
            return (Criteria) this;
        }

        public Criteria andSsbqqkGreaterThanOrEqualTo(String value) {
            addCriterion("ssbqqk >=", value, "ssbqqk");
            return (Criteria) this;
        }

        public Criteria andSsbqqkLessThan(String value) {
            addCriterion("ssbqqk <", value, "ssbqqk");
            return (Criteria) this;
        }

        public Criteria andSsbqqkLessThanOrEqualTo(String value) {
            addCriterion("ssbqqk <=", value, "ssbqqk");
            return (Criteria) this;
        }

        public Criteria andSsbqqkLike(String value) {
            addCriterion("ssbqqk like", value, "ssbqqk");
            return (Criteria) this;
        }

        public Criteria andSsbqqkNotLike(String value) {
            addCriterion("ssbqqk not like", value, "ssbqqk");
            return (Criteria) this;
        }

        public Criteria andSsbqqkIn(List<String> values) {
            addCriterion("ssbqqk in", values, "ssbqqk");
            return (Criteria) this;
        }

        public Criteria andSsbqqkNotIn(List<String> values) {
            addCriterion("ssbqqk not in", values, "ssbqqk");
            return (Criteria) this;
        }

        public Criteria andSsbqqkBetween(String value1, String value2) {
            addCriterion("ssbqqk between", value1, value2, "ssbqqk");
            return (Criteria) this;
        }

        public Criteria andSsbqqkNotBetween(String value1, String value2) {
            addCriterion("ssbqqk not between", value1, value2, "ssbqqk");
            return (Criteria) this;
        }

        public Criteria andFxjzrIsNull() {
            addCriterion("fxjzr is null");
            return (Criteria) this;
        }

        public Criteria andFxjzrIsNotNull() {
            addCriterion("fxjzr is not null");
            return (Criteria) this;
        }

        public Criteria andFxjzrEqualTo(String value) {
            addCriterion("fxjzr =", value, "fxjzr");
            return (Criteria) this;
        }

        public Criteria andFxjzrNotEqualTo(String value) {
            addCriterion("fxjzr <>", value, "fxjzr");
            return (Criteria) this;
        }

        public Criteria andFxjzrGreaterThan(String value) {
            addCriterion("fxjzr >", value, "fxjzr");
            return (Criteria) this;
        }

        public Criteria andFxjzrGreaterThanOrEqualTo(String value) {
            addCriterion("fxjzr >=", value, "fxjzr");
            return (Criteria) this;
        }

        public Criteria andFxjzrLessThan(String value) {
            addCriterion("fxjzr <", value, "fxjzr");
            return (Criteria) this;
        }

        public Criteria andFxjzrLessThanOrEqualTo(String value) {
            addCriterion("fxjzr <=", value, "fxjzr");
            return (Criteria) this;
        }

        public Criteria andFxjzrLike(String value) {
            addCriterion("fxjzr like", value, "fxjzr");
            return (Criteria) this;
        }

        public Criteria andFxjzrNotLike(String value) {
            addCriterion("fxjzr not like", value, "fxjzr");
            return (Criteria) this;
        }

        public Criteria andFxjzrIn(List<String> values) {
            addCriterion("fxjzr in", values, "fxjzr");
            return (Criteria) this;
        }

        public Criteria andFxjzrNotIn(List<String> values) {
            addCriterion("fxjzr not in", values, "fxjzr");
            return (Criteria) this;
        }

        public Criteria andFxjzrBetween(String value1, String value2) {
            addCriterion("fxjzr between", value1, value2, "fxjzr");
            return (Criteria) this;
        }

        public Criteria andFxjzrNotBetween(String value1, String value2) {
            addCriterion("fxjzr not between", value1, value2, "fxjzr");
            return (Criteria) this;
        }

        public Criteria andBjlxhIsNull() {
            addCriterion("bjlxh is null");
            return (Criteria) this;
        }

        public Criteria andBjlxhIsNotNull() {
            addCriterion("bjlxh is not null");
            return (Criteria) this;
        }

        public Criteria andBjlxhEqualTo(String value) {
            addCriterion("bjlxh =", value, "bjlxh");
            return (Criteria) this;
        }

        public Criteria andBjlxhNotEqualTo(String value) {
            addCriterion("bjlxh <>", value, "bjlxh");
            return (Criteria) this;
        }

        public Criteria andBjlxhGreaterThan(String value) {
            addCriterion("bjlxh >", value, "bjlxh");
            return (Criteria) this;
        }

        public Criteria andBjlxhGreaterThanOrEqualTo(String value) {
            addCriterion("bjlxh >=", value, "bjlxh");
            return (Criteria) this;
        }

        public Criteria andBjlxhLessThan(String value) {
            addCriterion("bjlxh <", value, "bjlxh");
            return (Criteria) this;
        }

        public Criteria andBjlxhLessThanOrEqualTo(String value) {
            addCriterion("bjlxh <=", value, "bjlxh");
            return (Criteria) this;
        }

        public Criteria andBjlxhLike(String value) {
            addCriterion("bjlxh like", value, "bjlxh");
            return (Criteria) this;
        }

        public Criteria andBjlxhNotLike(String value) {
            addCriterion("bjlxh not like", value, "bjlxh");
            return (Criteria) this;
        }

        public Criteria andBjlxhIn(List<String> values) {
            addCriterion("bjlxh in", values, "bjlxh");
            return (Criteria) this;
        }

        public Criteria andBjlxhNotIn(List<String> values) {
            addCriterion("bjlxh not in", values, "bjlxh");
            return (Criteria) this;
        }

        public Criteria andBjlxhBetween(String value1, String value2) {
            addCriterion("bjlxh between", value1, value2, "bjlxh");
            return (Criteria) this;
        }

        public Criteria andBjlxhNotBetween(String value1, String value2) {
            addCriterion("bjlxh not between", value1, value2, "bjlxh");
            return (Criteria) this;
        }

        public Criteria andBjyeIsNull() {
            addCriterion("bjye is null");
            return (Criteria) this;
        }

        public Criteria andBjyeIsNotNull() {
            addCriterion("bjye is not null");
            return (Criteria) this;
        }

        public Criteria andBjyeEqualTo(String value) {
            addCriterion("bjye =", value, "bjye");
            return (Criteria) this;
        }

        public Criteria andBjyeNotEqualTo(String value) {
            addCriterion("bjye <>", value, "bjye");
            return (Criteria) this;
        }

        public Criteria andBjyeGreaterThan(String value) {
            addCriterion("bjye >", value, "bjye");
            return (Criteria) this;
        }

        public Criteria andBjyeGreaterThanOrEqualTo(String value) {
            addCriterion("bjye >=", value, "bjye");
            return (Criteria) this;
        }

        public Criteria andBjyeLessThan(String value) {
            addCriterion("bjye <", value, "bjye");
            return (Criteria) this;
        }

        public Criteria andBjyeLessThanOrEqualTo(String value) {
            addCriterion("bjye <=", value, "bjye");
            return (Criteria) this;
        }

        public Criteria andBjyeLike(String value) {
            addCriterion("bjye like", value, "bjye");
            return (Criteria) this;
        }

        public Criteria andBjyeNotLike(String value) {
            addCriterion("bjye not like", value, "bjye");
            return (Criteria) this;
        }

        public Criteria andBjyeIn(List<String> values) {
            addCriterion("bjye in", values, "bjye");
            return (Criteria) this;
        }

        public Criteria andBjyeNotIn(List<String> values) {
            addCriterion("bjye not in", values, "bjye");
            return (Criteria) this;
        }

        public Criteria andBjyeBetween(String value1, String value2) {
            addCriterion("bjye between", value1, value2, "bjye");
            return (Criteria) this;
        }

        public Criteria andBjyeNotBetween(String value1, String value2) {
            addCriterion("bjye not between", value1, value2, "bjye");
            return (Criteria) this;
        }

        public Criteria andLxyeIsNull() {
            addCriterion("lxye is null");
            return (Criteria) this;
        }

        public Criteria andLxyeIsNotNull() {
            addCriterion("lxye is not null");
            return (Criteria) this;
        }

        public Criteria andLxyeEqualTo(String value) {
            addCriterion("lxye =", value, "lxye");
            return (Criteria) this;
        }

        public Criteria andLxyeNotEqualTo(String value) {
            addCriterion("lxye <>", value, "lxye");
            return (Criteria) this;
        }

        public Criteria andLxyeGreaterThan(String value) {
            addCriterion("lxye >", value, "lxye");
            return (Criteria) this;
        }

        public Criteria andLxyeGreaterThanOrEqualTo(String value) {
            addCriterion("lxye >=", value, "lxye");
            return (Criteria) this;
        }

        public Criteria andLxyeLessThan(String value) {
            addCriterion("lxye <", value, "lxye");
            return (Criteria) this;
        }

        public Criteria andLxyeLessThanOrEqualTo(String value) {
            addCriterion("lxye <=", value, "lxye");
            return (Criteria) this;
        }

        public Criteria andLxyeLike(String value) {
            addCriterion("lxye like", value, "lxye");
            return (Criteria) this;
        }

        public Criteria andLxyeNotLike(String value) {
            addCriterion("lxye not like", value, "lxye");
            return (Criteria) this;
        }

        public Criteria andLxyeIn(List<String> values) {
            addCriterion("lxye in", values, "lxye");
            return (Criteria) this;
        }

        public Criteria andLxyeNotIn(List<String> values) {
            addCriterion("lxye not in", values, "lxye");
            return (Criteria) this;
        }

        public Criteria andLxyeBetween(String value1, String value2) {
            addCriterion("lxye between", value1, value2, "lxye");
            return (Criteria) this;
        }

        public Criteria andLxyeNotBetween(String value1, String value2) {
            addCriterion("lxye not between", value1, value2, "lxye");
            return (Criteria) this;
        }

        public Criteria andWjflIsNull() {
            addCriterion("wjfl is null");
            return (Criteria) this;
        }

        public Criteria andWjflIsNotNull() {
            addCriterion("wjfl is not null");
            return (Criteria) this;
        }

        public Criteria andWjflEqualTo(String value) {
            addCriterion("wjfl =", value, "wjfl");
            return (Criteria) this;
        }

        public Criteria andWjflNotEqualTo(String value) {
            addCriterion("wjfl <>", value, "wjfl");
            return (Criteria) this;
        }

        public Criteria andWjflGreaterThan(String value) {
            addCriterion("wjfl >", value, "wjfl");
            return (Criteria) this;
        }

        public Criteria andWjflGreaterThanOrEqualTo(String value) {
            addCriterion("wjfl >=", value, "wjfl");
            return (Criteria) this;
        }

        public Criteria andWjflLessThan(String value) {
            addCriterion("wjfl <", value, "wjfl");
            return (Criteria) this;
        }

        public Criteria andWjflLessThanOrEqualTo(String value) {
            addCriterion("wjfl <=", value, "wjfl");
            return (Criteria) this;
        }

        public Criteria andWjflLike(String value) {
            addCriterion("wjfl like", value, "wjfl");
            return (Criteria) this;
        }

        public Criteria andWjflNotLike(String value) {
            addCriterion("wjfl not like", value, "wjfl");
            return (Criteria) this;
        }

        public Criteria andWjflIn(List<String> values) {
            addCriterion("wjfl in", values, "wjfl");
            return (Criteria) this;
        }

        public Criteria andWjflNotIn(List<String> values) {
            addCriterion("wjfl not in", values, "wjfl");
            return (Criteria) this;
        }

        public Criteria andWjflBetween(String value1, String value2) {
            addCriterion("wjfl between", value1, value2, "wjfl");
            return (Criteria) this;
        }

        public Criteria andWjflNotBetween(String value1, String value2) {
            addCriterion("wjfl not between", value1, value2, "wjfl");
            return (Criteria) this;
        }

        public Criteria andBlzcscjeIsNull() {
            addCriterion("blzcscje is null");
            return (Criteria) this;
        }

        public Criteria andBlzcscjeIsNotNull() {
            addCriterion("blzcscje is not null");
            return (Criteria) this;
        }

        public Criteria andBlzcscjeEqualTo(String value) {
            addCriterion("blzcscje =", value, "blzcscje");
            return (Criteria) this;
        }

        public Criteria andBlzcscjeNotEqualTo(String value) {
            addCriterion("blzcscje <>", value, "blzcscje");
            return (Criteria) this;
        }

        public Criteria andBlzcscjeGreaterThan(String value) {
            addCriterion("blzcscje >", value, "blzcscje");
            return (Criteria) this;
        }

        public Criteria andBlzcscjeGreaterThanOrEqualTo(String value) {
            addCriterion("blzcscje >=", value, "blzcscje");
            return (Criteria) this;
        }

        public Criteria andBlzcscjeLessThan(String value) {
            addCriterion("blzcscje <", value, "blzcscje");
            return (Criteria) this;
        }

        public Criteria andBlzcscjeLessThanOrEqualTo(String value) {
            addCriterion("blzcscje <=", value, "blzcscje");
            return (Criteria) this;
        }

        public Criteria andBlzcscjeLike(String value) {
            addCriterion("blzcscje like", value, "blzcscje");
            return (Criteria) this;
        }

        public Criteria andBlzcscjeNotLike(String value) {
            addCriterion("blzcscje not like", value, "blzcscje");
            return (Criteria) this;
        }

        public Criteria andBlzcscjeIn(List<String> values) {
            addCriterion("blzcscje in", values, "blzcscje");
            return (Criteria) this;
        }

        public Criteria andBlzcscjeNotIn(List<String> values) {
            addCriterion("blzcscje not in", values, "blzcscje");
            return (Criteria) this;
        }

        public Criteria andBlzcscjeBetween(String value1, String value2) {
            addCriterion("blzcscje between", value1, value2, "blzcscje");
            return (Criteria) this;
        }

        public Criteria andBlzcscjeNotBetween(String value1, String value2) {
            addCriterion("blzcscje not between", value1, value2, "blzcscje");
            return (Criteria) this;
        }

        public Criteria andBjscblIsNull() {
            addCriterion("bjscbl is null");
            return (Criteria) this;
        }

        public Criteria andBjscblIsNotNull() {
            addCriterion("bjscbl is not null");
            return (Criteria) this;
        }

        public Criteria andBjscblEqualTo(String value) {
            addCriterion("bjscbl =", value, "bjscbl");
            return (Criteria) this;
        }

        public Criteria andBjscblNotEqualTo(String value) {
            addCriterion("bjscbl <>", value, "bjscbl");
            return (Criteria) this;
        }

        public Criteria andBjscblGreaterThan(String value) {
            addCriterion("bjscbl >", value, "bjscbl");
            return (Criteria) this;
        }

        public Criteria andBjscblGreaterThanOrEqualTo(String value) {
            addCriterion("bjscbl >=", value, "bjscbl");
            return (Criteria) this;
        }

        public Criteria andBjscblLessThan(String value) {
            addCriterion("bjscbl <", value, "bjscbl");
            return (Criteria) this;
        }

        public Criteria andBjscblLessThanOrEqualTo(String value) {
            addCriterion("bjscbl <=", value, "bjscbl");
            return (Criteria) this;
        }

        public Criteria andBjscblLike(String value) {
            addCriterion("bjscbl like", value, "bjscbl");
            return (Criteria) this;
        }

        public Criteria andBjscblNotLike(String value) {
            addCriterion("bjscbl not like", value, "bjscbl");
            return (Criteria) this;
        }

        public Criteria andBjscblIn(List<String> values) {
            addCriterion("bjscbl in", values, "bjscbl");
            return (Criteria) this;
        }

        public Criteria andBjscblNotIn(List<String> values) {
            addCriterion("bjscbl not in", values, "bjscbl");
            return (Criteria) this;
        }

        public Criteria andBjscblBetween(String value1, String value2) {
            addCriterion("bjscbl between", value1, value2, "bjscbl");
            return (Criteria) this;
        }

        public Criteria andBjscblNotBetween(String value1, String value2) {
            addCriterion("bjscbl not between", value1, value2, "bjscbl");
            return (Criteria) this;
        }

        public Criteria andBxscblIsNull() {
            addCriterion("bxscbl is null");
            return (Criteria) this;
        }

        public Criteria andBxscblIsNotNull() {
            addCriterion("bxscbl is not null");
            return (Criteria) this;
        }

        public Criteria andBxscblEqualTo(String value) {
            addCriterion("bxscbl =", value, "bxscbl");
            return (Criteria) this;
        }

        public Criteria andBxscblNotEqualTo(String value) {
            addCriterion("bxscbl <>", value, "bxscbl");
            return (Criteria) this;
        }

        public Criteria andBxscblGreaterThan(String value) {
            addCriterion("bxscbl >", value, "bxscbl");
            return (Criteria) this;
        }

        public Criteria andBxscblGreaterThanOrEqualTo(String value) {
            addCriterion("bxscbl >=", value, "bxscbl");
            return (Criteria) this;
        }

        public Criteria andBxscblLessThan(String value) {
            addCriterion("bxscbl <", value, "bxscbl");
            return (Criteria) this;
        }

        public Criteria andBxscblLessThanOrEqualTo(String value) {
            addCriterion("bxscbl <=", value, "bxscbl");
            return (Criteria) this;
        }

        public Criteria andBxscblLike(String value) {
            addCriterion("bxscbl like", value, "bxscbl");
            return (Criteria) this;
        }

        public Criteria andBxscblNotLike(String value) {
            addCriterion("bxscbl not like", value, "bxscbl");
            return (Criteria) this;
        }

        public Criteria andBxscblIn(List<String> values) {
            addCriterion("bxscbl in", values, "bxscbl");
            return (Criteria) this;
        }

        public Criteria andBxscblNotIn(List<String> values) {
            addCriterion("bxscbl not in", values, "bxscbl");
            return (Criteria) this;
        }

        public Criteria andBxscblBetween(String value1, String value2) {
            addCriterion("bxscbl between", value1, value2, "bxscbl");
            return (Criteria) this;
        }

        public Criteria andBxscblNotBetween(String value1, String value2) {
            addCriterion("bxscbl not between", value1, value2, "bxscbl");
            return (Criteria) this;
        }

        public Criteria andScsjIsNull() {
            addCriterion("scsj is null");
            return (Criteria) this;
        }

        public Criteria andScsjIsNotNull() {
            addCriterion("scsj is not null");
            return (Criteria) this;
        }

        public Criteria andScsjEqualTo(String value) {
            addCriterion("scsj =", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjNotEqualTo(String value) {
            addCriterion("scsj <>", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjGreaterThan(String value) {
            addCriterion("scsj >", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjGreaterThanOrEqualTo(String value) {
            addCriterion("scsj >=", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjLessThan(String value) {
            addCriterion("scsj <", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjLessThanOrEqualTo(String value) {
            addCriterion("scsj <=", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjLike(String value) {
            addCriterion("scsj like", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjNotLike(String value) {
            addCriterion("scsj not like", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjIn(List<String> values) {
            addCriterion("scsj in", values, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjNotIn(List<String> values) {
            addCriterion("scsj not in", values, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjBetween(String value1, String value2) {
            addCriterion("scsj between", value1, value2, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjNotBetween(String value1, String value2) {
            addCriterion("scsj not between", value1, value2, "scsj");
            return (Criteria) this;
        }

        public Criteria andBgyxqIsNull() {
            addCriterion("bgyxq is null");
            return (Criteria) this;
        }

        public Criteria andBgyxqIsNotNull() {
            addCriterion("bgyxq is not null");
            return (Criteria) this;
        }

        public Criteria andBgyxqEqualTo(String value) {
            addCriterion("bgyxq =", value, "bgyxq");
            return (Criteria) this;
        }

        public Criteria andBgyxqNotEqualTo(String value) {
            addCriterion("bgyxq <>", value, "bgyxq");
            return (Criteria) this;
        }

        public Criteria andBgyxqGreaterThan(String value) {
            addCriterion("bgyxq >", value, "bgyxq");
            return (Criteria) this;
        }

        public Criteria andBgyxqGreaterThanOrEqualTo(String value) {
            addCriterion("bgyxq >=", value, "bgyxq");
            return (Criteria) this;
        }

        public Criteria andBgyxqLessThan(String value) {
            addCriterion("bgyxq <", value, "bgyxq");
            return (Criteria) this;
        }

        public Criteria andBgyxqLessThanOrEqualTo(String value) {
            addCriterion("bgyxq <=", value, "bgyxq");
            return (Criteria) this;
        }

        public Criteria andBgyxqLike(String value) {
            addCriterion("bgyxq like", value, "bgyxq");
            return (Criteria) this;
        }

        public Criteria andBgyxqNotLike(String value) {
            addCriterion("bgyxq not like", value, "bgyxq");
            return (Criteria) this;
        }

        public Criteria andBgyxqIn(List<String> values) {
            addCriterion("bgyxq in", values, "bgyxq");
            return (Criteria) this;
        }

        public Criteria andBgyxqNotIn(List<String> values) {
            addCriterion("bgyxq not in", values, "bgyxq");
            return (Criteria) this;
        }

        public Criteria andBgyxqBetween(String value1, String value2) {
            addCriterion("bgyxq between", value1, value2, "bgyxq");
            return (Criteria) this;
        }

        public Criteria andBgyxqNotBetween(String value1, String value2) {
            addCriterion("bgyxq not between", value1, value2, "bgyxq");
            return (Criteria) this;
        }

        public Criteria andDywscdjIsNull() {
            addCriterion("dywscdj is null");
            return (Criteria) this;
        }

        public Criteria andDywscdjIsNotNull() {
            addCriterion("dywscdj is not null");
            return (Criteria) this;
        }

        public Criteria andDywscdjEqualTo(String value) {
            addCriterion("dywscdj =", value, "dywscdj");
            return (Criteria) this;
        }

        public Criteria andDywscdjNotEqualTo(String value) {
            addCriterion("dywscdj <>", value, "dywscdj");
            return (Criteria) this;
        }

        public Criteria andDywscdjGreaterThan(String value) {
            addCriterion("dywscdj >", value, "dywscdj");
            return (Criteria) this;
        }

        public Criteria andDywscdjGreaterThanOrEqualTo(String value) {
            addCriterion("dywscdj >=", value, "dywscdj");
            return (Criteria) this;
        }

        public Criteria andDywscdjLessThan(String value) {
            addCriterion("dywscdj <", value, "dywscdj");
            return (Criteria) this;
        }

        public Criteria andDywscdjLessThanOrEqualTo(String value) {
            addCriterion("dywscdj <=", value, "dywscdj");
            return (Criteria) this;
        }

        public Criteria andDywscdjLike(String value) {
            addCriterion("dywscdj like", value, "dywscdj");
            return (Criteria) this;
        }

        public Criteria andDywscdjNotLike(String value) {
            addCriterion("dywscdj not like", value, "dywscdj");
            return (Criteria) this;
        }

        public Criteria andDywscdjIn(List<String> values) {
            addCriterion("dywscdj in", values, "dywscdj");
            return (Criteria) this;
        }

        public Criteria andDywscdjNotIn(List<String> values) {
            addCriterion("dywscdj not in", values, "dywscdj");
            return (Criteria) this;
        }

        public Criteria andDywscdjBetween(String value1, String value2) {
            addCriterion("dywscdj between", value1, value2, "dywscdj");
            return (Criteria) this;
        }

        public Criteria andDywscdjNotBetween(String value1, String value2) {
            addCriterion("dywscdj not between", value1, value2, "dywscdj");
            return (Criteria) this;
        }

        public Criteria andDywsczjIsNull() {
            addCriterion("dywsczj is null");
            return (Criteria) this;
        }

        public Criteria andDywsczjIsNotNull() {
            addCriterion("dywsczj is not null");
            return (Criteria) this;
        }

        public Criteria andDywsczjEqualTo(String value) {
            addCriterion("dywsczj =", value, "dywsczj");
            return (Criteria) this;
        }

        public Criteria andDywsczjNotEqualTo(String value) {
            addCriterion("dywsczj <>", value, "dywsczj");
            return (Criteria) this;
        }

        public Criteria andDywsczjGreaterThan(String value) {
            addCriterion("dywsczj >", value, "dywsczj");
            return (Criteria) this;
        }

        public Criteria andDywsczjGreaterThanOrEqualTo(String value) {
            addCriterion("dywsczj >=", value, "dywsczj");
            return (Criteria) this;
        }

        public Criteria andDywsczjLessThan(String value) {
            addCriterion("dywsczj <", value, "dywsczj");
            return (Criteria) this;
        }

        public Criteria andDywsczjLessThanOrEqualTo(String value) {
            addCriterion("dywsczj <=", value, "dywsczj");
            return (Criteria) this;
        }

        public Criteria andDywsczjLike(String value) {
            addCriterion("dywsczj like", value, "dywsczj");
            return (Criteria) this;
        }

        public Criteria andDywsczjNotLike(String value) {
            addCriterion("dywsczj not like", value, "dywsczj");
            return (Criteria) this;
        }

        public Criteria andDywsczjIn(List<String> values) {
            addCriterion("dywsczj in", values, "dywsczj");
            return (Criteria) this;
        }

        public Criteria andDywsczjNotIn(List<String> values) {
            addCriterion("dywsczj not in", values, "dywsczj");
            return (Criteria) this;
        }

        public Criteria andDywsczjBetween(String value1, String value2) {
            addCriterion("dywsczj between", value1, value2, "dywsczj");
            return (Criteria) this;
        }

        public Criteria andDywsczjNotBetween(String value1, String value2) {
            addCriterion("dywsczj not between", value1, value2, "dywsczj");
            return (Criteria) this;
        }

        public Criteria andDywbxlIsNull() {
            addCriterion("dywbxl is null");
            return (Criteria) this;
        }

        public Criteria andDywbxlIsNotNull() {
            addCriterion("dywbxl is not null");
            return (Criteria) this;
        }

        public Criteria andDywbxlEqualTo(String value) {
            addCriterion("dywbxl =", value, "dywbxl");
            return (Criteria) this;
        }

        public Criteria andDywbxlNotEqualTo(String value) {
            addCriterion("dywbxl <>", value, "dywbxl");
            return (Criteria) this;
        }

        public Criteria andDywbxlGreaterThan(String value) {
            addCriterion("dywbxl >", value, "dywbxl");
            return (Criteria) this;
        }

        public Criteria andDywbxlGreaterThanOrEqualTo(String value) {
            addCriterion("dywbxl >=", value, "dywbxl");
            return (Criteria) this;
        }

        public Criteria andDywbxlLessThan(String value) {
            addCriterion("dywbxl <", value, "dywbxl");
            return (Criteria) this;
        }

        public Criteria andDywbxlLessThanOrEqualTo(String value) {
            addCriterion("dywbxl <=", value, "dywbxl");
            return (Criteria) this;
        }

        public Criteria andDywbxlLike(String value) {
            addCriterion("dywbxl like", value, "dywbxl");
            return (Criteria) this;
        }

        public Criteria andDywbxlNotLike(String value) {
            addCriterion("dywbxl not like", value, "dywbxl");
            return (Criteria) this;
        }

        public Criteria andDywbxlIn(List<String> values) {
            addCriterion("dywbxl in", values, "dywbxl");
            return (Criteria) this;
        }

        public Criteria andDywbxlNotIn(List<String> values) {
            addCriterion("dywbxl not in", values, "dywbxl");
            return (Criteria) this;
        }

        public Criteria andDywbxlBetween(String value1, String value2) {
            addCriterion("dywbxl between", value1, value2, "dywbxl");
            return (Criteria) this;
        }

        public Criteria andDywbxlNotBetween(String value1, String value2) {
            addCriterion("dywbxl not between", value1, value2, "dywbxl");
            return (Criteria) this;
        }

        public Criteria andDywbxjzIsNull() {
            addCriterion("dywbxjz is null");
            return (Criteria) this;
        }

        public Criteria andDywbxjzIsNotNull() {
            addCriterion("dywbxjz is not null");
            return (Criteria) this;
        }

        public Criteria andDywbxjzEqualTo(String value) {
            addCriterion("dywbxjz =", value, "dywbxjz");
            return (Criteria) this;
        }

        public Criteria andDywbxjzNotEqualTo(String value) {
            addCriterion("dywbxjz <>", value, "dywbxjz");
            return (Criteria) this;
        }

        public Criteria andDywbxjzGreaterThan(String value) {
            addCriterion("dywbxjz >", value, "dywbxjz");
            return (Criteria) this;
        }

        public Criteria andDywbxjzGreaterThanOrEqualTo(String value) {
            addCriterion("dywbxjz >=", value, "dywbxjz");
            return (Criteria) this;
        }

        public Criteria andDywbxjzLessThan(String value) {
            addCriterion("dywbxjz <", value, "dywbxjz");
            return (Criteria) this;
        }

        public Criteria andDywbxjzLessThanOrEqualTo(String value) {
            addCriterion("dywbxjz <=", value, "dywbxjz");
            return (Criteria) this;
        }

        public Criteria andDywbxjzLike(String value) {
            addCriterion("dywbxjz like", value, "dywbxjz");
            return (Criteria) this;
        }

        public Criteria andDywbxjzNotLike(String value) {
            addCriterion("dywbxjz not like", value, "dywbxjz");
            return (Criteria) this;
        }

        public Criteria andDywbxjzIn(List<String> values) {
            addCriterion("dywbxjz in", values, "dywbxjz");
            return (Criteria) this;
        }

        public Criteria andDywbxjzNotIn(List<String> values) {
            addCriterion("dywbxjz not in", values, "dywbxjz");
            return (Criteria) this;
        }

        public Criteria andDywbxjzBetween(String value1, String value2) {
            addCriterion("dywbxjz between", value1, value2, "dywbxjz");
            return (Criteria) this;
        }

        public Criteria andDywbxjzNotBetween(String value1, String value2) {
            addCriterion("dywbxjz not between", value1, value2, "dywbxjz");
            return (Criteria) this;
        }

        public Criteria andPmfblIsNull() {
            addCriterion("pmfbl is null");
            return (Criteria) this;
        }

        public Criteria andPmfblIsNotNull() {
            addCriterion("pmfbl is not null");
            return (Criteria) this;
        }

        public Criteria andPmfblEqualTo(String value) {
            addCriterion("pmfbl =", value, "pmfbl");
            return (Criteria) this;
        }

        public Criteria andPmfblNotEqualTo(String value) {
            addCriterion("pmfbl <>", value, "pmfbl");
            return (Criteria) this;
        }

        public Criteria andPmfblGreaterThan(String value) {
            addCriterion("pmfbl >", value, "pmfbl");
            return (Criteria) this;
        }

        public Criteria andPmfblGreaterThanOrEqualTo(String value) {
            addCriterion("pmfbl >=", value, "pmfbl");
            return (Criteria) this;
        }

        public Criteria andPmfblLessThan(String value) {
            addCriterion("pmfbl <", value, "pmfbl");
            return (Criteria) this;
        }

        public Criteria andPmfblLessThanOrEqualTo(String value) {
            addCriterion("pmfbl <=", value, "pmfbl");
            return (Criteria) this;
        }

        public Criteria andPmfblLike(String value) {
            addCriterion("pmfbl like", value, "pmfbl");
            return (Criteria) this;
        }

        public Criteria andPmfblNotLike(String value) {
            addCriterion("pmfbl not like", value, "pmfbl");
            return (Criteria) this;
        }

        public Criteria andPmfblIn(List<String> values) {
            addCriterion("pmfbl in", values, "pmfbl");
            return (Criteria) this;
        }

        public Criteria andPmfblNotIn(List<String> values) {
            addCriterion("pmfbl not in", values, "pmfbl");
            return (Criteria) this;
        }

        public Criteria andPmfblBetween(String value1, String value2) {
            addCriterion("pmfbl between", value1, value2, "pmfbl");
            return (Criteria) this;
        }

        public Criteria andPmfblNotBetween(String value1, String value2) {
            addCriterion("pmfbl not between", value1, value2, "pmfbl");
            return (Criteria) this;
        }

        public Criteria andPmfjeIsNull() {
            addCriterion("pmfje is null");
            return (Criteria) this;
        }

        public Criteria andPmfjeIsNotNull() {
            addCriterion("pmfje is not null");
            return (Criteria) this;
        }

        public Criteria andPmfjeEqualTo(String value) {
            addCriterion("pmfje =", value, "pmfje");
            return (Criteria) this;
        }

        public Criteria andPmfjeNotEqualTo(String value) {
            addCriterion("pmfje <>", value, "pmfje");
            return (Criteria) this;
        }

        public Criteria andPmfjeGreaterThan(String value) {
            addCriterion("pmfje >", value, "pmfje");
            return (Criteria) this;
        }

        public Criteria andPmfjeGreaterThanOrEqualTo(String value) {
            addCriterion("pmfje >=", value, "pmfje");
            return (Criteria) this;
        }

        public Criteria andPmfjeLessThan(String value) {
            addCriterion("pmfje <", value, "pmfje");
            return (Criteria) this;
        }

        public Criteria andPmfjeLessThanOrEqualTo(String value) {
            addCriterion("pmfje <=", value, "pmfje");
            return (Criteria) this;
        }

        public Criteria andPmfjeLike(String value) {
            addCriterion("pmfje like", value, "pmfje");
            return (Criteria) this;
        }

        public Criteria andPmfjeNotLike(String value) {
            addCriterion("pmfje not like", value, "pmfje");
            return (Criteria) this;
        }

        public Criteria andPmfjeIn(List<String> values) {
            addCriterion("pmfje in", values, "pmfje");
            return (Criteria) this;
        }

        public Criteria andPmfjeNotIn(List<String> values) {
            addCriterion("pmfje not in", values, "pmfje");
            return (Criteria) this;
        }

        public Criteria andPmfjeBetween(String value1, String value2) {
            addCriterion("pmfje between", value1, value2, "pmfje");
            return (Criteria) this;
        }

        public Criteria andPmfjeNotBetween(String value1, String value2) {
            addCriterion("pmfje not between", value1, value2, "pmfje");
            return (Criteria) this;
        }

        public Criteria andSsfblIsNull() {
            addCriterion("ssfbl is null");
            return (Criteria) this;
        }

        public Criteria andSsfblIsNotNull() {
            addCriterion("ssfbl is not null");
            return (Criteria) this;
        }

        public Criteria andSsfblEqualTo(String value) {
            addCriterion("ssfbl =", value, "ssfbl");
            return (Criteria) this;
        }

        public Criteria andSsfblNotEqualTo(String value) {
            addCriterion("ssfbl <>", value, "ssfbl");
            return (Criteria) this;
        }

        public Criteria andSsfblGreaterThan(String value) {
            addCriterion("ssfbl >", value, "ssfbl");
            return (Criteria) this;
        }

        public Criteria andSsfblGreaterThanOrEqualTo(String value) {
            addCriterion("ssfbl >=", value, "ssfbl");
            return (Criteria) this;
        }

        public Criteria andSsfblLessThan(String value) {
            addCriterion("ssfbl <", value, "ssfbl");
            return (Criteria) this;
        }

        public Criteria andSsfblLessThanOrEqualTo(String value) {
            addCriterion("ssfbl <=", value, "ssfbl");
            return (Criteria) this;
        }

        public Criteria andSsfblLike(String value) {
            addCriterion("ssfbl like", value, "ssfbl");
            return (Criteria) this;
        }

        public Criteria andSsfblNotLike(String value) {
            addCriterion("ssfbl not like", value, "ssfbl");
            return (Criteria) this;
        }

        public Criteria andSsfblIn(List<String> values) {
            addCriterion("ssfbl in", values, "ssfbl");
            return (Criteria) this;
        }

        public Criteria andSsfblNotIn(List<String> values) {
            addCriterion("ssfbl not in", values, "ssfbl");
            return (Criteria) this;
        }

        public Criteria andSsfblBetween(String value1, String value2) {
            addCriterion("ssfbl between", value1, value2, "ssfbl");
            return (Criteria) this;
        }

        public Criteria andSsfblNotBetween(String value1, String value2) {
            addCriterion("ssfbl not between", value1, value2, "ssfbl");
            return (Criteria) this;
        }

        public Criteria andSsfjeIsNull() {
            addCriterion("ssfje is null");
            return (Criteria) this;
        }

        public Criteria andSsfjeIsNotNull() {
            addCriterion("ssfje is not null");
            return (Criteria) this;
        }

        public Criteria andSsfjeEqualTo(String value) {
            addCriterion("ssfje =", value, "ssfje");
            return (Criteria) this;
        }

        public Criteria andSsfjeNotEqualTo(String value) {
            addCriterion("ssfje <>", value, "ssfje");
            return (Criteria) this;
        }

        public Criteria andSsfjeGreaterThan(String value) {
            addCriterion("ssfje >", value, "ssfje");
            return (Criteria) this;
        }

        public Criteria andSsfjeGreaterThanOrEqualTo(String value) {
            addCriterion("ssfje >=", value, "ssfje");
            return (Criteria) this;
        }

        public Criteria andSsfjeLessThan(String value) {
            addCriterion("ssfje <", value, "ssfje");
            return (Criteria) this;
        }

        public Criteria andSsfjeLessThanOrEqualTo(String value) {
            addCriterion("ssfje <=", value, "ssfje");
            return (Criteria) this;
        }

        public Criteria andSsfjeLike(String value) {
            addCriterion("ssfje like", value, "ssfje");
            return (Criteria) this;
        }

        public Criteria andSsfjeNotLike(String value) {
            addCriterion("ssfje not like", value, "ssfje");
            return (Criteria) this;
        }

        public Criteria andSsfjeIn(List<String> values) {
            addCriterion("ssfje in", values, "ssfje");
            return (Criteria) this;
        }

        public Criteria andSsfjeNotIn(List<String> values) {
            addCriterion("ssfje not in", values, "ssfje");
            return (Criteria) this;
        }

        public Criteria andSsfjeBetween(String value1, String value2) {
            addCriterion("ssfje between", value1, value2, "ssfje");
            return (Criteria) this;
        }

        public Criteria andSsfjeNotBetween(String value1, String value2) {
            addCriterion("ssfje not between", value1, value2, "ssfje");
            return (Criteria) this;
        }

        public Criteria andZxfblIsNull() {
            addCriterion("zxfbl is null");
            return (Criteria) this;
        }

        public Criteria andZxfblIsNotNull() {
            addCriterion("zxfbl is not null");
            return (Criteria) this;
        }

        public Criteria andZxfblEqualTo(String value) {
            addCriterion("zxfbl =", value, "zxfbl");
            return (Criteria) this;
        }

        public Criteria andZxfblNotEqualTo(String value) {
            addCriterion("zxfbl <>", value, "zxfbl");
            return (Criteria) this;
        }

        public Criteria andZxfblGreaterThan(String value) {
            addCriterion("zxfbl >", value, "zxfbl");
            return (Criteria) this;
        }

        public Criteria andZxfblGreaterThanOrEqualTo(String value) {
            addCriterion("zxfbl >=", value, "zxfbl");
            return (Criteria) this;
        }

        public Criteria andZxfblLessThan(String value) {
            addCriterion("zxfbl <", value, "zxfbl");
            return (Criteria) this;
        }

        public Criteria andZxfblLessThanOrEqualTo(String value) {
            addCriterion("zxfbl <=", value, "zxfbl");
            return (Criteria) this;
        }

        public Criteria andZxfblLike(String value) {
            addCriterion("zxfbl like", value, "zxfbl");
            return (Criteria) this;
        }

        public Criteria andZxfblNotLike(String value) {
            addCriterion("zxfbl not like", value, "zxfbl");
            return (Criteria) this;
        }

        public Criteria andZxfblIn(List<String> values) {
            addCriterion("zxfbl in", values, "zxfbl");
            return (Criteria) this;
        }

        public Criteria andZxfblNotIn(List<String> values) {
            addCriterion("zxfbl not in", values, "zxfbl");
            return (Criteria) this;
        }

        public Criteria andZxfblBetween(String value1, String value2) {
            addCriterion("zxfbl between", value1, value2, "zxfbl");
            return (Criteria) this;
        }

        public Criteria andZxfblNotBetween(String value1, String value2) {
            addCriterion("zxfbl not between", value1, value2, "zxfbl");
            return (Criteria) this;
        }

        public Criteria andZxfjeIsNull() {
            addCriterion("zxfje is null");
            return (Criteria) this;
        }

        public Criteria andZxfjeIsNotNull() {
            addCriterion("zxfje is not null");
            return (Criteria) this;
        }

        public Criteria andZxfjeEqualTo(String value) {
            addCriterion("zxfje =", value, "zxfje");
            return (Criteria) this;
        }

        public Criteria andZxfjeNotEqualTo(String value) {
            addCriterion("zxfje <>", value, "zxfje");
            return (Criteria) this;
        }

        public Criteria andZxfjeGreaterThan(String value) {
            addCriterion("zxfje >", value, "zxfje");
            return (Criteria) this;
        }

        public Criteria andZxfjeGreaterThanOrEqualTo(String value) {
            addCriterion("zxfje >=", value, "zxfje");
            return (Criteria) this;
        }

        public Criteria andZxfjeLessThan(String value) {
            addCriterion("zxfje <", value, "zxfje");
            return (Criteria) this;
        }

        public Criteria andZxfjeLessThanOrEqualTo(String value) {
            addCriterion("zxfje <=", value, "zxfje");
            return (Criteria) this;
        }

        public Criteria andZxfjeLike(String value) {
            addCriterion("zxfje like", value, "zxfje");
            return (Criteria) this;
        }

        public Criteria andZxfjeNotLike(String value) {
            addCriterion("zxfje not like", value, "zxfje");
            return (Criteria) this;
        }

        public Criteria andZxfjeIn(List<String> values) {
            addCriterion("zxfje in", values, "zxfje");
            return (Criteria) this;
        }

        public Criteria andZxfjeNotIn(List<String> values) {
            addCriterion("zxfje not in", values, "zxfje");
            return (Criteria) this;
        }

        public Criteria andZxfjeBetween(String value1, String value2) {
            addCriterion("zxfje between", value1, value2, "zxfje");
            return (Criteria) this;
        }

        public Criteria andZxfjeNotBetween(String value1, String value2) {
            addCriterion("zxfje not between", value1, value2, "zxfje");
            return (Criteria) this;
        }

        public Criteria andSfjdfblIsNull() {
            addCriterion("sfjdfbl is null");
            return (Criteria) this;
        }

        public Criteria andSfjdfblIsNotNull() {
            addCriterion("sfjdfbl is not null");
            return (Criteria) this;
        }

        public Criteria andSfjdfblEqualTo(String value) {
            addCriterion("sfjdfbl =", value, "sfjdfbl");
            return (Criteria) this;
        }

        public Criteria andSfjdfblNotEqualTo(String value) {
            addCriterion("sfjdfbl <>", value, "sfjdfbl");
            return (Criteria) this;
        }

        public Criteria andSfjdfblGreaterThan(String value) {
            addCriterion("sfjdfbl >", value, "sfjdfbl");
            return (Criteria) this;
        }

        public Criteria andSfjdfblGreaterThanOrEqualTo(String value) {
            addCriterion("sfjdfbl >=", value, "sfjdfbl");
            return (Criteria) this;
        }

        public Criteria andSfjdfblLessThan(String value) {
            addCriterion("sfjdfbl <", value, "sfjdfbl");
            return (Criteria) this;
        }

        public Criteria andSfjdfblLessThanOrEqualTo(String value) {
            addCriterion("sfjdfbl <=", value, "sfjdfbl");
            return (Criteria) this;
        }

        public Criteria andSfjdfblLike(String value) {
            addCriterion("sfjdfbl like", value, "sfjdfbl");
            return (Criteria) this;
        }

        public Criteria andSfjdfblNotLike(String value) {
            addCriterion("sfjdfbl not like", value, "sfjdfbl");
            return (Criteria) this;
        }

        public Criteria andSfjdfblIn(List<String> values) {
            addCriterion("sfjdfbl in", values, "sfjdfbl");
            return (Criteria) this;
        }

        public Criteria andSfjdfblNotIn(List<String> values) {
            addCriterion("sfjdfbl not in", values, "sfjdfbl");
            return (Criteria) this;
        }

        public Criteria andSfjdfblBetween(String value1, String value2) {
            addCriterion("sfjdfbl between", value1, value2, "sfjdfbl");
            return (Criteria) this;
        }

        public Criteria andSfjdfblNotBetween(String value1, String value2) {
            addCriterion("sfjdfbl not between", value1, value2, "sfjdfbl");
            return (Criteria) this;
        }

        public Criteria andSfjdfjeIsNull() {
            addCriterion("sfjdfje is null");
            return (Criteria) this;
        }

        public Criteria andSfjdfjeIsNotNull() {
            addCriterion("sfjdfje is not null");
            return (Criteria) this;
        }

        public Criteria andSfjdfjeEqualTo(String value) {
            addCriterion("sfjdfje =", value, "sfjdfje");
            return (Criteria) this;
        }

        public Criteria andSfjdfjeNotEqualTo(String value) {
            addCriterion("sfjdfje <>", value, "sfjdfje");
            return (Criteria) this;
        }

        public Criteria andSfjdfjeGreaterThan(String value) {
            addCriterion("sfjdfje >", value, "sfjdfje");
            return (Criteria) this;
        }

        public Criteria andSfjdfjeGreaterThanOrEqualTo(String value) {
            addCriterion("sfjdfje >=", value, "sfjdfje");
            return (Criteria) this;
        }

        public Criteria andSfjdfjeLessThan(String value) {
            addCriterion("sfjdfje <", value, "sfjdfje");
            return (Criteria) this;
        }

        public Criteria andSfjdfjeLessThanOrEqualTo(String value) {
            addCriterion("sfjdfje <=", value, "sfjdfje");
            return (Criteria) this;
        }

        public Criteria andSfjdfjeLike(String value) {
            addCriterion("sfjdfje like", value, "sfjdfje");
            return (Criteria) this;
        }

        public Criteria andSfjdfjeNotLike(String value) {
            addCriterion("sfjdfje not like", value, "sfjdfje");
            return (Criteria) this;
        }

        public Criteria andSfjdfjeIn(List<String> values) {
            addCriterion("sfjdfje in", values, "sfjdfje");
            return (Criteria) this;
        }

        public Criteria andSfjdfjeNotIn(List<String> values) {
            addCriterion("sfjdfje not in", values, "sfjdfje");
            return (Criteria) this;
        }

        public Criteria andSfjdfjeBetween(String value1, String value2) {
            addCriterion("sfjdfje between", value1, value2, "sfjdfje");
            return (Criteria) this;
        }

        public Criteria andSfjdfjeNotBetween(String value1, String value2) {
            addCriterion("sfjdfje not between", value1, value2, "sfjdfje");
            return (Criteria) this;
        }

        public Criteria andGhsfjeIsNull() {
            addCriterion("ghsfje is null");
            return (Criteria) this;
        }

        public Criteria andGhsfjeIsNotNull() {
            addCriterion("ghsfje is not null");
            return (Criteria) this;
        }

        public Criteria andGhsfjeEqualTo(String value) {
            addCriterion("ghsfje =", value, "ghsfje");
            return (Criteria) this;
        }

        public Criteria andGhsfjeNotEqualTo(String value) {
            addCriterion("ghsfje <>", value, "ghsfje");
            return (Criteria) this;
        }

        public Criteria andGhsfjeGreaterThan(String value) {
            addCriterion("ghsfje >", value, "ghsfje");
            return (Criteria) this;
        }

        public Criteria andGhsfjeGreaterThanOrEqualTo(String value) {
            addCriterion("ghsfje >=", value, "ghsfje");
            return (Criteria) this;
        }

        public Criteria andGhsfjeLessThan(String value) {
            addCriterion("ghsfje <", value, "ghsfje");
            return (Criteria) this;
        }

        public Criteria andGhsfjeLessThanOrEqualTo(String value) {
            addCriterion("ghsfje <=", value, "ghsfje");
            return (Criteria) this;
        }

        public Criteria andGhsfjeLike(String value) {
            addCriterion("ghsfje like", value, "ghsfje");
            return (Criteria) this;
        }

        public Criteria andGhsfjeNotLike(String value) {
            addCriterion("ghsfje not like", value, "ghsfje");
            return (Criteria) this;
        }

        public Criteria andGhsfjeIn(List<String> values) {
            addCriterion("ghsfje in", values, "ghsfje");
            return (Criteria) this;
        }

        public Criteria andGhsfjeNotIn(List<String> values) {
            addCriterion("ghsfje not in", values, "ghsfje");
            return (Criteria) this;
        }

        public Criteria andGhsfjeBetween(String value1, String value2) {
            addCriterion("ghsfje between", value1, value2, "ghsfje");
            return (Criteria) this;
        }

        public Criteria andGhsfjeNotBetween(String value1, String value2) {
            addCriterion("ghsfje not between", value1, value2, "ghsfje");
            return (Criteria) this;
        }

        public Criteria andXgsfhjIsNull() {
            addCriterion("xgsfhj is null");
            return (Criteria) this;
        }

        public Criteria andXgsfhjIsNotNull() {
            addCriterion("xgsfhj is not null");
            return (Criteria) this;
        }

        public Criteria andXgsfhjEqualTo(String value) {
            addCriterion("xgsfhj =", value, "xgsfhj");
            return (Criteria) this;
        }

        public Criteria andXgsfhjNotEqualTo(String value) {
            addCriterion("xgsfhj <>", value, "xgsfhj");
            return (Criteria) this;
        }

        public Criteria andXgsfhjGreaterThan(String value) {
            addCriterion("xgsfhj >", value, "xgsfhj");
            return (Criteria) this;
        }

        public Criteria andXgsfhjGreaterThanOrEqualTo(String value) {
            addCriterion("xgsfhj >=", value, "xgsfhj");
            return (Criteria) this;
        }

        public Criteria andXgsfhjLessThan(String value) {
            addCriterion("xgsfhj <", value, "xgsfhj");
            return (Criteria) this;
        }

        public Criteria andXgsfhjLessThanOrEqualTo(String value) {
            addCriterion("xgsfhj <=", value, "xgsfhj");
            return (Criteria) this;
        }

        public Criteria andXgsfhjLike(String value) {
            addCriterion("xgsfhj like", value, "xgsfhj");
            return (Criteria) this;
        }

        public Criteria andXgsfhjNotLike(String value) {
            addCriterion("xgsfhj not like", value, "xgsfhj");
            return (Criteria) this;
        }

        public Criteria andXgsfhjIn(List<String> values) {
            addCriterion("xgsfhj in", values, "xgsfhj");
            return (Criteria) this;
        }

        public Criteria andXgsfhjNotIn(List<String> values) {
            addCriterion("xgsfhj not in", values, "xgsfhj");
            return (Criteria) this;
        }

        public Criteria andXgsfhjBetween(String value1, String value2) {
            addCriterion("xgsfhj between", value1, value2, "xgsfhj");
            return (Criteria) this;
        }

        public Criteria andXgsfhjNotBetween(String value1, String value2) {
            addCriterion("xgsfhj not between", value1, value2, "xgsfhj");
            return (Criteria) this;
        }

        public Criteria andBxjsrIsNull() {
            addCriterion("bxjsr is null");
            return (Criteria) this;
        }

        public Criteria andBxjsrIsNotNull() {
            addCriterion("bxjsr is not null");
            return (Criteria) this;
        }

        public Criteria andBxjsrEqualTo(String value) {
            addCriterion("bxjsr =", value, "bxjsr");
            return (Criteria) this;
        }

        public Criteria andBxjsrNotEqualTo(String value) {
            addCriterion("bxjsr <>", value, "bxjsr");
            return (Criteria) this;
        }

        public Criteria andBxjsrGreaterThan(String value) {
            addCriterion("bxjsr >", value, "bxjsr");
            return (Criteria) this;
        }

        public Criteria andBxjsrGreaterThanOrEqualTo(String value) {
            addCriterion("bxjsr >=", value, "bxjsr");
            return (Criteria) this;
        }

        public Criteria andBxjsrLessThan(String value) {
            addCriterion("bxjsr <", value, "bxjsr");
            return (Criteria) this;
        }

        public Criteria andBxjsrLessThanOrEqualTo(String value) {
            addCriterion("bxjsr <=", value, "bxjsr");
            return (Criteria) this;
        }

        public Criteria andBxjsrLike(String value) {
            addCriterion("bxjsr like", value, "bxjsr");
            return (Criteria) this;
        }

        public Criteria andBxjsrNotLike(String value) {
            addCriterion("bxjsr not like", value, "bxjsr");
            return (Criteria) this;
        }

        public Criteria andBxjsrIn(List<String> values) {
            addCriterion("bxjsr in", values, "bxjsr");
            return (Criteria) this;
        }

        public Criteria andBxjsrNotIn(List<String> values) {
            addCriterion("bxjsr not in", values, "bxjsr");
            return (Criteria) this;
        }

        public Criteria andBxjsrBetween(String value1, String value2) {
            addCriterion("bxjsr between", value1, value2, "bxjsr");
            return (Criteria) this;
        }

        public Criteria andBxjsrNotBetween(String value1, String value2) {
            addCriterion("bxjsr not between", value1, value2, "bxjsr");
            return (Criteria) this;
        }

        public Criteria andBgcjsjIsNull() {
            addCriterion("bgcjsj is null");
            return (Criteria) this;
        }

        public Criteria andBgcjsjIsNotNull() {
            addCriterion("bgcjsj is not null");
            return (Criteria) this;
        }

        public Criteria andBgcjsjEqualTo(String value) {
            addCriterion("bgcjsj =", value, "bgcjsj");
            return (Criteria) this;
        }

        public Criteria andBgcjsjNotEqualTo(String value) {
            addCriterion("bgcjsj <>", value, "bgcjsj");
            return (Criteria) this;
        }

        public Criteria andBgcjsjGreaterThan(String value) {
            addCriterion("bgcjsj >", value, "bgcjsj");
            return (Criteria) this;
        }

        public Criteria andBgcjsjGreaterThanOrEqualTo(String value) {
            addCriterion("bgcjsj >=", value, "bgcjsj");
            return (Criteria) this;
        }

        public Criteria andBgcjsjLessThan(String value) {
            addCriterion("bgcjsj <", value, "bgcjsj");
            return (Criteria) this;
        }

        public Criteria andBgcjsjLessThanOrEqualTo(String value) {
            addCriterion("bgcjsj <=", value, "bgcjsj");
            return (Criteria) this;
        }

        public Criteria andBgcjsjLike(String value) {
            addCriterion("bgcjsj like", value, "bgcjsj");
            return (Criteria) this;
        }

        public Criteria andBgcjsjNotLike(String value) {
            addCriterion("bgcjsj not like", value, "bgcjsj");
            return (Criteria) this;
        }

        public Criteria andBgcjsjIn(List<String> values) {
            addCriterion("bgcjsj in", values, "bgcjsj");
            return (Criteria) this;
        }

        public Criteria andBgcjsjNotIn(List<String> values) {
            addCriterion("bgcjsj not in", values, "bgcjsj");
            return (Criteria) this;
        }

        public Criteria andBgcjsjBetween(String value1, String value2) {
            addCriterion("bgcjsj between", value1, value2, "bgcjsj");
            return (Criteria) this;
        }

        public Criteria andBgcjsjNotBetween(String value1, String value2) {
            addCriterion("bgcjsj not between", value1, value2, "bgcjsj");
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

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("version like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("version not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andRoleIsNull() {
            addCriterion("role is null");
            return (Criteria) this;
        }

        public Criteria andRoleIsNotNull() {
            addCriterion("role is not null");
            return (Criteria) this;
        }

        public Criteria andRoleEqualTo(String value) {
            addCriterion("role =", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotEqualTo(String value) {
            addCriterion("role <>", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThan(String value) {
            addCriterion("role >", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThanOrEqualTo(String value) {
            addCriterion("role >=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThan(String value) {
            addCriterion("role <", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThanOrEqualTo(String value) {
            addCriterion("role <=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLike(String value) {
            addCriterion("role like", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotLike(String value) {
            addCriterion("role not like", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleIn(List<String> values) {
            addCriterion("role in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotIn(List<String> values) {
            addCriterion("role not in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleBetween(String value1, String value2) {
            addCriterion("role between", value1, value2, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotBetween(String value1, String value2) {
            addCriterion("role not between", value1, value2, "role");
            return (Criteria) this;
        }

        public Criteria andQtsmIsNull() {
            addCriterion("qtsm is null");
            return (Criteria) this;
        }

        public Criteria andQtsmIsNotNull() {
            addCriterion("qtsm is not null");
            return (Criteria) this;
        }

        public Criteria andQtsmEqualTo(String value) {
            addCriterion("qtsm =", value, "qtsm");
            return (Criteria) this;
        }

        public Criteria andQtsmNotEqualTo(String value) {
            addCriterion("qtsm <>", value, "qtsm");
            return (Criteria) this;
        }

        public Criteria andQtsmGreaterThan(String value) {
            addCriterion("qtsm >", value, "qtsm");
            return (Criteria) this;
        }

        public Criteria andQtsmGreaterThanOrEqualTo(String value) {
            addCriterion("qtsm >=", value, "qtsm");
            return (Criteria) this;
        }

        public Criteria andQtsmLessThan(String value) {
            addCriterion("qtsm <", value, "qtsm");
            return (Criteria) this;
        }

        public Criteria andQtsmLessThanOrEqualTo(String value) {
            addCriterion("qtsm <=", value, "qtsm");
            return (Criteria) this;
        }

        public Criteria andQtsmLike(String value) {
            addCriterion("qtsm like", value, "qtsm");
            return (Criteria) this;
        }

        public Criteria andQtsmNotLike(String value) {
            addCriterion("qtsm not like", value, "qtsm");
            return (Criteria) this;
        }

        public Criteria andQtsmIn(List<String> values) {
            addCriterion("qtsm in", values, "qtsm");
            return (Criteria) this;
        }

        public Criteria andQtsmNotIn(List<String> values) {
            addCriterion("qtsm not in", values, "qtsm");
            return (Criteria) this;
        }

        public Criteria andQtsmBetween(String value1, String value2) {
            addCriterion("qtsm between", value1, value2, "qtsm");
            return (Criteria) this;
        }

        public Criteria andQtsmNotBetween(String value1, String value2) {
            addCriterion("qtsm not between", value1, value2, "qtsm");
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