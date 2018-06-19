package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.List;

public class FuniHousesPropertyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FuniHousesPropertyExample() {
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

        public Criteria andGlfsIsNull() {
            addCriterion("glfs is null");
            return (Criteria) this;
        }

        public Criteria andGlfsIsNotNull() {
            addCriterion("glfs is not null");
            return (Criteria) this;
        }

        public Criteria andGlfsEqualTo(String value) {
            addCriterion("glfs =", value, "glfs");
            return (Criteria) this;
        }

        public Criteria andGlfsNotEqualTo(String value) {
            addCriterion("glfs <>", value, "glfs");
            return (Criteria) this;
        }

        public Criteria andGlfsGreaterThan(String value) {
            addCriterion("glfs >", value, "glfs");
            return (Criteria) this;
        }

        public Criteria andGlfsGreaterThanOrEqualTo(String value) {
            addCriterion("glfs >=", value, "glfs");
            return (Criteria) this;
        }

        public Criteria andGlfsLessThan(String value) {
            addCriterion("glfs <", value, "glfs");
            return (Criteria) this;
        }

        public Criteria andGlfsLessThanOrEqualTo(String value) {
            addCriterion("glfs <=", value, "glfs");
            return (Criteria) this;
        }

        public Criteria andGlfsLike(String value) {
            addCriterion("glfs like", value, "glfs");
            return (Criteria) this;
        }

        public Criteria andGlfsNotLike(String value) {
            addCriterion("glfs not like", value, "glfs");
            return (Criteria) this;
        }

        public Criteria andGlfsIn(List<String> values) {
            addCriterion("glfs in", values, "glfs");
            return (Criteria) this;
        }

        public Criteria andGlfsNotIn(List<String> values) {
            addCriterion("glfs not in", values, "glfs");
            return (Criteria) this;
        }

        public Criteria andGlfsBetween(String value1, String value2) {
            addCriterion("glfs between", value1, value2, "glfs");
            return (Criteria) this;
        }

        public Criteria andGlfsNotBetween(String value1, String value2) {
            addCriterion("glfs not between", value1, value2, "glfs");
            return (Criteria) this;
        }

        public Criteria andGsfsIsNull() {
            addCriterion("gsfs is null");
            return (Criteria) this;
        }

        public Criteria andGsfsIsNotNull() {
            addCriterion("gsfs is not null");
            return (Criteria) this;
        }

        public Criteria andGsfsEqualTo(String value) {
            addCriterion("gsfs =", value, "gsfs");
            return (Criteria) this;
        }

        public Criteria andGsfsNotEqualTo(String value) {
            addCriterion("gsfs <>", value, "gsfs");
            return (Criteria) this;
        }

        public Criteria andGsfsGreaterThan(String value) {
            addCriterion("gsfs >", value, "gsfs");
            return (Criteria) this;
        }

        public Criteria andGsfsGreaterThanOrEqualTo(String value) {
            addCriterion("gsfs >=", value, "gsfs");
            return (Criteria) this;
        }

        public Criteria andGsfsLessThan(String value) {
            addCriterion("gsfs <", value, "gsfs");
            return (Criteria) this;
        }

        public Criteria andGsfsLessThanOrEqualTo(String value) {
            addCriterion("gsfs <=", value, "gsfs");
            return (Criteria) this;
        }

        public Criteria andGsfsLike(String value) {
            addCriterion("gsfs like", value, "gsfs");
            return (Criteria) this;
        }

        public Criteria andGsfsNotLike(String value) {
            addCriterion("gsfs not like", value, "gsfs");
            return (Criteria) this;
        }

        public Criteria andGsfsIn(List<String> values) {
            addCriterion("gsfs in", values, "gsfs");
            return (Criteria) this;
        }

        public Criteria andGsfsNotIn(List<String> values) {
            addCriterion("gsfs not in", values, "gsfs");
            return (Criteria) this;
        }

        public Criteria andGsfsBetween(String value1, String value2) {
            addCriterion("gsfs between", value1, value2, "gsfs");
            return (Criteria) this;
        }

        public Criteria andGsfsNotBetween(String value1, String value2) {
            addCriterion("gsfs not between", value1, value2, "gsfs");
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

        public Criteria andWylxIsNull() {
            addCriterion("wylx is null");
            return (Criteria) this;
        }

        public Criteria andWylxIsNotNull() {
            addCriterion("wylx is not null");
            return (Criteria) this;
        }

        public Criteria andWylxEqualTo(String value) {
            addCriterion("wylx =", value, "wylx");
            return (Criteria) this;
        }

        public Criteria andWylxNotEqualTo(String value) {
            addCriterion("wylx <>", value, "wylx");
            return (Criteria) this;
        }

        public Criteria andWylxGreaterThan(String value) {
            addCriterion("wylx >", value, "wylx");
            return (Criteria) this;
        }

        public Criteria andWylxGreaterThanOrEqualTo(String value) {
            addCriterion("wylx >=", value, "wylx");
            return (Criteria) this;
        }

        public Criteria andWylxLessThan(String value) {
            addCriterion("wylx <", value, "wylx");
            return (Criteria) this;
        }

        public Criteria andWylxLessThanOrEqualTo(String value) {
            addCriterion("wylx <=", value, "wylx");
            return (Criteria) this;
        }

        public Criteria andWylxLike(String value) {
            addCriterion("wylx like", value, "wylx");
            return (Criteria) this;
        }

        public Criteria andWylxNotLike(String value) {
            addCriterion("wylx not like", value, "wylx");
            return (Criteria) this;
        }

        public Criteria andWylxIn(List<String> values) {
            addCriterion("wylx in", values, "wylx");
            return (Criteria) this;
        }

        public Criteria andWylxNotIn(List<String> values) {
            addCriterion("wylx not in", values, "wylx");
            return (Criteria) this;
        }

        public Criteria andWylxBetween(String value1, String value2) {
            addCriterion("wylx between", value1, value2, "wylx");
            return (Criteria) this;
        }

        public Criteria andWylxNotBetween(String value1, String value2) {
            addCriterion("wylx not between", value1, value2, "wylx");
            return (Criteria) this;
        }

        public Criteria andJzlbIsNull() {
            addCriterion("jzlb is null");
            return (Criteria) this;
        }

        public Criteria andJzlbIsNotNull() {
            addCriterion("jzlb is not null");
            return (Criteria) this;
        }

        public Criteria andJzlbEqualTo(String value) {
            addCriterion("jzlb =", value, "jzlb");
            return (Criteria) this;
        }

        public Criteria andJzlbNotEqualTo(String value) {
            addCriterion("jzlb <>", value, "jzlb");
            return (Criteria) this;
        }

        public Criteria andJzlbGreaterThan(String value) {
            addCriterion("jzlb >", value, "jzlb");
            return (Criteria) this;
        }

        public Criteria andJzlbGreaterThanOrEqualTo(String value) {
            addCriterion("jzlb >=", value, "jzlb");
            return (Criteria) this;
        }

        public Criteria andJzlbLessThan(String value) {
            addCriterion("jzlb <", value, "jzlb");
            return (Criteria) this;
        }

        public Criteria andJzlbLessThanOrEqualTo(String value) {
            addCriterion("jzlb <=", value, "jzlb");
            return (Criteria) this;
        }

        public Criteria andJzlbLike(String value) {
            addCriterion("jzlb like", value, "jzlb");
            return (Criteria) this;
        }

        public Criteria andJzlbNotLike(String value) {
            addCriterion("jzlb not like", value, "jzlb");
            return (Criteria) this;
        }

        public Criteria andJzlbIn(List<String> values) {
            addCriterion("jzlb in", values, "jzlb");
            return (Criteria) this;
        }

        public Criteria andJzlbNotIn(List<String> values) {
            addCriterion("jzlb not in", values, "jzlb");
            return (Criteria) this;
        }

        public Criteria andJzlbBetween(String value1, String value2) {
            addCriterion("jzlb between", value1, value2, "jzlb");
            return (Criteria) this;
        }

        public Criteria andJzlbNotBetween(String value1, String value2) {
            addCriterion("jzlb not between", value1, value2, "jzlb");
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

        public Criteria andTdsynxIsNull() {
            addCriterion("tdsynx is null");
            return (Criteria) this;
        }

        public Criteria andTdsynxIsNotNull() {
            addCriterion("tdsynx is not null");
            return (Criteria) this;
        }

        public Criteria andTdsynxEqualTo(String value) {
            addCriterion("tdsynx =", value, "tdsynx");
            return (Criteria) this;
        }

        public Criteria andTdsynxNotEqualTo(String value) {
            addCriterion("tdsynx <>", value, "tdsynx");
            return (Criteria) this;
        }

        public Criteria andTdsynxGreaterThan(String value) {
            addCriterion("tdsynx >", value, "tdsynx");
            return (Criteria) this;
        }

        public Criteria andTdsynxGreaterThanOrEqualTo(String value) {
            addCriterion("tdsynx >=", value, "tdsynx");
            return (Criteria) this;
        }

        public Criteria andTdsynxLessThan(String value) {
            addCriterion("tdsynx <", value, "tdsynx");
            return (Criteria) this;
        }

        public Criteria andTdsynxLessThanOrEqualTo(String value) {
            addCriterion("tdsynx <=", value, "tdsynx");
            return (Criteria) this;
        }

        public Criteria andTdsynxLike(String value) {
            addCriterion("tdsynx like", value, "tdsynx");
            return (Criteria) this;
        }

        public Criteria andTdsynxNotLike(String value) {
            addCriterion("tdsynx not like", value, "tdsynx");
            return (Criteria) this;
        }

        public Criteria andTdsynxIn(List<String> values) {
            addCriterion("tdsynx in", values, "tdsynx");
            return (Criteria) this;
        }

        public Criteria andTdsynxNotIn(List<String> values) {
            addCriterion("tdsynx not in", values, "tdsynx");
            return (Criteria) this;
        }

        public Criteria andTdsynxBetween(String value1, String value2) {
            addCriterion("tdsynx between", value1, value2, "tdsynx");
            return (Criteria) this;
        }

        public Criteria andTdsynxNotBetween(String value1, String value2) {
            addCriterion("tdsynx not between", value1, value2, "tdsynx");
            return (Criteria) this;
        }

        public Criteria andZhsIsNull() {
            addCriterion("zhs is null");
            return (Criteria) this;
        }

        public Criteria andZhsIsNotNull() {
            addCriterion("zhs is not null");
            return (Criteria) this;
        }

        public Criteria andZhsEqualTo(String value) {
            addCriterion("zhs =", value, "zhs");
            return (Criteria) this;
        }

        public Criteria andZhsNotEqualTo(String value) {
            addCriterion("zhs <>", value, "zhs");
            return (Criteria) this;
        }

        public Criteria andZhsGreaterThan(String value) {
            addCriterion("zhs >", value, "zhs");
            return (Criteria) this;
        }

        public Criteria andZhsGreaterThanOrEqualTo(String value) {
            addCriterion("zhs >=", value, "zhs");
            return (Criteria) this;
        }

        public Criteria andZhsLessThan(String value) {
            addCriterion("zhs <", value, "zhs");
            return (Criteria) this;
        }

        public Criteria andZhsLessThanOrEqualTo(String value) {
            addCriterion("zhs <=", value, "zhs");
            return (Criteria) this;
        }

        public Criteria andZhsLike(String value) {
            addCriterion("zhs like", value, "zhs");
            return (Criteria) this;
        }

        public Criteria andZhsNotLike(String value) {
            addCriterion("zhs not like", value, "zhs");
            return (Criteria) this;
        }

        public Criteria andZhsIn(List<String> values) {
            addCriterion("zhs in", values, "zhs");
            return (Criteria) this;
        }

        public Criteria andZhsNotIn(List<String> values) {
            addCriterion("zhs not in", values, "zhs");
            return (Criteria) this;
        }

        public Criteria andZhsBetween(String value1, String value2) {
            addCriterion("zhs between", value1, value2, "zhs");
            return (Criteria) this;
        }

        public Criteria andZhsNotBetween(String value1, String value2) {
            addCriterion("zhs not between", value1, value2, "zhs");
            return (Criteria) this;
        }

        public Criteria andWyfIsNull() {
            addCriterion("wyf is null");
            return (Criteria) this;
        }

        public Criteria andWyfIsNotNull() {
            addCriterion("wyf is not null");
            return (Criteria) this;
        }

        public Criteria andWyfEqualTo(String value) {
            addCriterion("wyf =", value, "wyf");
            return (Criteria) this;
        }

        public Criteria andWyfNotEqualTo(String value) {
            addCriterion("wyf <>", value, "wyf");
            return (Criteria) this;
        }

        public Criteria andWyfGreaterThan(String value) {
            addCriterion("wyf >", value, "wyf");
            return (Criteria) this;
        }

        public Criteria andWyfGreaterThanOrEqualTo(String value) {
            addCriterion("wyf >=", value, "wyf");
            return (Criteria) this;
        }

        public Criteria andWyfLessThan(String value) {
            addCriterion("wyf <", value, "wyf");
            return (Criteria) this;
        }

        public Criteria andWyfLessThanOrEqualTo(String value) {
            addCriterion("wyf <=", value, "wyf");
            return (Criteria) this;
        }

        public Criteria andWyfLike(String value) {
            addCriterion("wyf like", value, "wyf");
            return (Criteria) this;
        }

        public Criteria andWyfNotLike(String value) {
            addCriterion("wyf not like", value, "wyf");
            return (Criteria) this;
        }

        public Criteria andWyfIn(List<String> values) {
            addCriterion("wyf in", values, "wyf");
            return (Criteria) this;
        }

        public Criteria andWyfNotIn(List<String> values) {
            addCriterion("wyf not in", values, "wyf");
            return (Criteria) this;
        }

        public Criteria andWyfBetween(String value1, String value2) {
            addCriterion("wyf between", value1, value2, "wyf");
            return (Criteria) this;
        }

        public Criteria andWyfNotBetween(String value1, String value2) {
            addCriterion("wyf not between", value1, value2, "wyf");
            return (Criteria) this;
        }

        public Criteria andWygsbhIsNull() {
            addCriterion("wygsbh is null");
            return (Criteria) this;
        }

        public Criteria andWygsbhIsNotNull() {
            addCriterion("wygsbh is not null");
            return (Criteria) this;
        }

        public Criteria andWygsbhEqualTo(Integer value) {
            addCriterion("wygsbh =", value, "wygsbh");
            return (Criteria) this;
        }

        public Criteria andWygsbhNotEqualTo(Integer value) {
            addCriterion("wygsbh <>", value, "wygsbh");
            return (Criteria) this;
        }

        public Criteria andWygsbhGreaterThan(Integer value) {
            addCriterion("wygsbh >", value, "wygsbh");
            return (Criteria) this;
        }

        public Criteria andWygsbhGreaterThanOrEqualTo(Integer value) {
            addCriterion("wygsbh >=", value, "wygsbh");
            return (Criteria) this;
        }

        public Criteria andWygsbhLessThan(Integer value) {
            addCriterion("wygsbh <", value, "wygsbh");
            return (Criteria) this;
        }

        public Criteria andWygsbhLessThanOrEqualTo(Integer value) {
            addCriterion("wygsbh <=", value, "wygsbh");
            return (Criteria) this;
        }

        public Criteria andWygsbhIn(List<Integer> values) {
            addCriterion("wygsbh in", values, "wygsbh");
            return (Criteria) this;
        }

        public Criteria andWygsbhNotIn(List<Integer> values) {
            addCriterion("wygsbh not in", values, "wygsbh");
            return (Criteria) this;
        }

        public Criteria andWygsbhBetween(Integer value1, Integer value2) {
            addCriterion("wygsbh between", value1, value2, "wygsbh");
            return (Criteria) this;
        }

        public Criteria andWygsbhNotBetween(Integer value1, Integer value2) {
            addCriterion("wygsbh not between", value1, value2, "wygsbh");
            return (Criteria) this;
        }

        public Criteria andWygsIsNull() {
            addCriterion("wygs is null");
            return (Criteria) this;
        }

        public Criteria andWygsIsNotNull() {
            addCriterion("wygs is not null");
            return (Criteria) this;
        }

        public Criteria andWygsEqualTo(String value) {
            addCriterion("wygs =", value, "wygs");
            return (Criteria) this;
        }

        public Criteria andWygsNotEqualTo(String value) {
            addCriterion("wygs <>", value, "wygs");
            return (Criteria) this;
        }

        public Criteria andWygsGreaterThan(String value) {
            addCriterion("wygs >", value, "wygs");
            return (Criteria) this;
        }

        public Criteria andWygsGreaterThanOrEqualTo(String value) {
            addCriterion("wygs >=", value, "wygs");
            return (Criteria) this;
        }

        public Criteria andWygsLessThan(String value) {
            addCriterion("wygs <", value, "wygs");
            return (Criteria) this;
        }

        public Criteria andWygsLessThanOrEqualTo(String value) {
            addCriterion("wygs <=", value, "wygs");
            return (Criteria) this;
        }

        public Criteria andWygsLike(String value) {
            addCriterion("wygs like", value, "wygs");
            return (Criteria) this;
        }

        public Criteria andWygsNotLike(String value) {
            addCriterion("wygs not like", value, "wygs");
            return (Criteria) this;
        }

        public Criteria andWygsIn(List<String> values) {
            addCriterion("wygs in", values, "wygs");
            return (Criteria) this;
        }

        public Criteria andWygsNotIn(List<String> values) {
            addCriterion("wygs not in", values, "wygs");
            return (Criteria) this;
        }

        public Criteria andWygsBetween(String value1, String value2) {
            addCriterion("wygs between", value1, value2, "wygs");
            return (Criteria) this;
        }

        public Criteria andWygsNotBetween(String value1, String value2) {
            addCriterion("wygs not between", value1, value2, "wygs");
            return (Criteria) this;
        }

        public Criteria andYxdlIsNull() {
            addCriterion("yxdl is null");
            return (Criteria) this;
        }

        public Criteria andYxdlIsNotNull() {
            addCriterion("yxdl is not null");
            return (Criteria) this;
        }

        public Criteria andYxdlEqualTo(String value) {
            addCriterion("yxdl =", value, "yxdl");
            return (Criteria) this;
        }

        public Criteria andYxdlNotEqualTo(String value) {
            addCriterion("yxdl <>", value, "yxdl");
            return (Criteria) this;
        }

        public Criteria andYxdlGreaterThan(String value) {
            addCriterion("yxdl >", value, "yxdl");
            return (Criteria) this;
        }

        public Criteria andYxdlGreaterThanOrEqualTo(String value) {
            addCriterion("yxdl >=", value, "yxdl");
            return (Criteria) this;
        }

        public Criteria andYxdlLessThan(String value) {
            addCriterion("yxdl <", value, "yxdl");
            return (Criteria) this;
        }

        public Criteria andYxdlLessThanOrEqualTo(String value) {
            addCriterion("yxdl <=", value, "yxdl");
            return (Criteria) this;
        }

        public Criteria andYxdlLike(String value) {
            addCriterion("yxdl like", value, "yxdl");
            return (Criteria) this;
        }

        public Criteria andYxdlNotLike(String value) {
            addCriterion("yxdl not like", value, "yxdl");
            return (Criteria) this;
        }

        public Criteria andYxdlIn(List<String> values) {
            addCriterion("yxdl in", values, "yxdl");
            return (Criteria) this;
        }

        public Criteria andYxdlNotIn(List<String> values) {
            addCriterion("yxdl not in", values, "yxdl");
            return (Criteria) this;
        }

        public Criteria andYxdlBetween(String value1, String value2) {
            addCriterion("yxdl between", value1, value2, "yxdl");
            return (Criteria) this;
        }

        public Criteria andYxdlNotBetween(String value1, String value2) {
            addCriterion("yxdl not between", value1, value2, "yxdl");
            return (Criteria) this;
        }

        public Criteria andKpsjIsNull() {
            addCriterion("kpsj is null");
            return (Criteria) this;
        }

        public Criteria andKpsjIsNotNull() {
            addCriterion("kpsj is not null");
            return (Criteria) this;
        }

        public Criteria andKpsjEqualTo(String value) {
            addCriterion("kpsj =", value, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjNotEqualTo(String value) {
            addCriterion("kpsj <>", value, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjGreaterThan(String value) {
            addCriterion("kpsj >", value, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjGreaterThanOrEqualTo(String value) {
            addCriterion("kpsj >=", value, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjLessThan(String value) {
            addCriterion("kpsj <", value, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjLessThanOrEqualTo(String value) {
            addCriterion("kpsj <=", value, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjLike(String value) {
            addCriterion("kpsj like", value, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjNotLike(String value) {
            addCriterion("kpsj not like", value, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjIn(List<String> values) {
            addCriterion("kpsj in", values, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjNotIn(List<String> values) {
            addCriterion("kpsj not in", values, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjBetween(String value1, String value2) {
            addCriterion("kpsj between", value1, value2, "kpsj");
            return (Criteria) this;
        }

        public Criteria andKpsjNotBetween(String value1, String value2) {
            addCriterion("kpsj not between", value1, value2, "kpsj");
            return (Criteria) this;
        }

        public Criteria andJfsjIsNull() {
            addCriterion("jfsj is null");
            return (Criteria) this;
        }

        public Criteria andJfsjIsNotNull() {
            addCriterion("jfsj is not null");
            return (Criteria) this;
        }

        public Criteria andJfsjEqualTo(String value) {
            addCriterion("jfsj =", value, "jfsj");
            return (Criteria) this;
        }

        public Criteria andJfsjNotEqualTo(String value) {
            addCriterion("jfsj <>", value, "jfsj");
            return (Criteria) this;
        }

        public Criteria andJfsjGreaterThan(String value) {
            addCriterion("jfsj >", value, "jfsj");
            return (Criteria) this;
        }

        public Criteria andJfsjGreaterThanOrEqualTo(String value) {
            addCriterion("jfsj >=", value, "jfsj");
            return (Criteria) this;
        }

        public Criteria andJfsjLessThan(String value) {
            addCriterion("jfsj <", value, "jfsj");
            return (Criteria) this;
        }

        public Criteria andJfsjLessThanOrEqualTo(String value) {
            addCriterion("jfsj <=", value, "jfsj");
            return (Criteria) this;
        }

        public Criteria andJfsjLike(String value) {
            addCriterion("jfsj like", value, "jfsj");
            return (Criteria) this;
        }

        public Criteria andJfsjNotLike(String value) {
            addCriterion("jfsj not like", value, "jfsj");
            return (Criteria) this;
        }

        public Criteria andJfsjIn(List<String> values) {
            addCriterion("jfsj in", values, "jfsj");
            return (Criteria) this;
        }

        public Criteria andJfsjNotIn(List<String> values) {
            addCriterion("jfsj not in", values, "jfsj");
            return (Criteria) this;
        }

        public Criteria andJfsjBetween(String value1, String value2) {
            addCriterion("jfsj between", value1, value2, "jfsj");
            return (Criteria) this;
        }

        public Criteria andJfsjNotBetween(String value1, String value2) {
            addCriterion("jfsj not between", value1, value2, "jfsj");
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

        public Criteria andHxqjIsNull() {
            addCriterion("hxqj is null");
            return (Criteria) this;
        }

        public Criteria andHxqjIsNotNull() {
            addCriterion("hxqj is not null");
            return (Criteria) this;
        }

        public Criteria andHxqjEqualTo(String value) {
            addCriterion("hxqj =", value, "hxqj");
            return (Criteria) this;
        }

        public Criteria andHxqjNotEqualTo(String value) {
            addCriterion("hxqj <>", value, "hxqj");
            return (Criteria) this;
        }

        public Criteria andHxqjGreaterThan(String value) {
            addCriterion("hxqj >", value, "hxqj");
            return (Criteria) this;
        }

        public Criteria andHxqjGreaterThanOrEqualTo(String value) {
            addCriterion("hxqj >=", value, "hxqj");
            return (Criteria) this;
        }

        public Criteria andHxqjLessThan(String value) {
            addCriterion("hxqj <", value, "hxqj");
            return (Criteria) this;
        }

        public Criteria andHxqjLessThanOrEqualTo(String value) {
            addCriterion("hxqj <=", value, "hxqj");
            return (Criteria) this;
        }

        public Criteria andHxqjLike(String value) {
            addCriterion("hxqj like", value, "hxqj");
            return (Criteria) this;
        }

        public Criteria andHxqjNotLike(String value) {
            addCriterion("hxqj not like", value, "hxqj");
            return (Criteria) this;
        }

        public Criteria andHxqjIn(List<String> values) {
            addCriterion("hxqj in", values, "hxqj");
            return (Criteria) this;
        }

        public Criteria andHxqjNotIn(List<String> values) {
            addCriterion("hxqj not in", values, "hxqj");
            return (Criteria) this;
        }

        public Criteria andHxqjBetween(String value1, String value2) {
            addCriterion("hxqj between", value1, value2, "hxqj");
            return (Criteria) this;
        }

        public Criteria andHxqjNotBetween(String value1, String value2) {
            addCriterion("hxqj not between", value1, value2, "hxqj");
            return (Criteria) this;
        }

        public Criteria andKtsIsNull() {
            addCriterion("kts is null");
            return (Criteria) this;
        }

        public Criteria andKtsIsNotNull() {
            addCriterion("kts is not null");
            return (Criteria) this;
        }

        public Criteria andKtsEqualTo(String value) {
            addCriterion("kts =", value, "kts");
            return (Criteria) this;
        }

        public Criteria andKtsNotEqualTo(String value) {
            addCriterion("kts <>", value, "kts");
            return (Criteria) this;
        }

        public Criteria andKtsGreaterThan(String value) {
            addCriterion("kts >", value, "kts");
            return (Criteria) this;
        }

        public Criteria andKtsGreaterThanOrEqualTo(String value) {
            addCriterion("kts >=", value, "kts");
            return (Criteria) this;
        }

        public Criteria andKtsLessThan(String value) {
            addCriterion("kts <", value, "kts");
            return (Criteria) this;
        }

        public Criteria andKtsLessThanOrEqualTo(String value) {
            addCriterion("kts <=", value, "kts");
            return (Criteria) this;
        }

        public Criteria andKtsLike(String value) {
            addCriterion("kts like", value, "kts");
            return (Criteria) this;
        }

        public Criteria andKtsNotLike(String value) {
            addCriterion("kts not like", value, "kts");
            return (Criteria) this;
        }

        public Criteria andKtsIn(List<String> values) {
            addCriterion("kts in", values, "kts");
            return (Criteria) this;
        }

        public Criteria andKtsNotIn(List<String> values) {
            addCriterion("kts not in", values, "kts");
            return (Criteria) this;
        }

        public Criteria andKtsBetween(String value1, String value2) {
            addCriterion("kts between", value1, value2, "kts");
            return (Criteria) this;
        }

        public Criteria andKtsNotBetween(String value1, String value2) {
            addCriterion("kts not between", value1, value2, "kts");
            return (Criteria) this;
        }

        public Criteria andHtsIsNull() {
            addCriterion("hts is null");
            return (Criteria) this;
        }

        public Criteria andHtsIsNotNull() {
            addCriterion("hts is not null");
            return (Criteria) this;
        }

        public Criteria andHtsEqualTo(String value) {
            addCriterion("hts =", value, "hts");
            return (Criteria) this;
        }

        public Criteria andHtsNotEqualTo(String value) {
            addCriterion("hts <>", value, "hts");
            return (Criteria) this;
        }

        public Criteria andHtsGreaterThan(String value) {
            addCriterion("hts >", value, "hts");
            return (Criteria) this;
        }

        public Criteria andHtsGreaterThanOrEqualTo(String value) {
            addCriterion("hts >=", value, "hts");
            return (Criteria) this;
        }

        public Criteria andHtsLessThan(String value) {
            addCriterion("hts <", value, "hts");
            return (Criteria) this;
        }

        public Criteria andHtsLessThanOrEqualTo(String value) {
            addCriterion("hts <=", value, "hts");
            return (Criteria) this;
        }

        public Criteria andHtsLike(String value) {
            addCriterion("hts like", value, "hts");
            return (Criteria) this;
        }

        public Criteria andHtsNotLike(String value) {
            addCriterion("hts not like", value, "hts");
            return (Criteria) this;
        }

        public Criteria andHtsIn(List<String> values) {
            addCriterion("hts in", values, "hts");
            return (Criteria) this;
        }

        public Criteria andHtsNotIn(List<String> values) {
            addCriterion("hts not in", values, "hts");
            return (Criteria) this;
        }

        public Criteria andHtsBetween(String value1, String value2) {
            addCriterion("hts between", value1, value2, "hts");
            return (Criteria) this;
        }

        public Criteria andHtsNotBetween(String value1, String value2) {
            addCriterion("hts not between", value1, value2, "hts");
            return (Criteria) this;
        }

        public Criteria andCgIsNull() {
            addCriterion("cg is null");
            return (Criteria) this;
        }

        public Criteria andCgIsNotNull() {
            addCriterion("cg is not null");
            return (Criteria) this;
        }

        public Criteria andCgEqualTo(String value) {
            addCriterion("cg =", value, "cg");
            return (Criteria) this;
        }

        public Criteria andCgNotEqualTo(String value) {
            addCriterion("cg <>", value, "cg");
            return (Criteria) this;
        }

        public Criteria andCgGreaterThan(String value) {
            addCriterion("cg >", value, "cg");
            return (Criteria) this;
        }

        public Criteria andCgGreaterThanOrEqualTo(String value) {
            addCriterion("cg >=", value, "cg");
            return (Criteria) this;
        }

        public Criteria andCgLessThan(String value) {
            addCriterion("cg <", value, "cg");
            return (Criteria) this;
        }

        public Criteria andCgLessThanOrEqualTo(String value) {
            addCriterion("cg <=", value, "cg");
            return (Criteria) this;
        }

        public Criteria andCgLike(String value) {
            addCriterion("cg like", value, "cg");
            return (Criteria) this;
        }

        public Criteria andCgNotLike(String value) {
            addCriterion("cg not like", value, "cg");
            return (Criteria) this;
        }

        public Criteria andCgIn(List<String> values) {
            addCriterion("cg in", values, "cg");
            return (Criteria) this;
        }

        public Criteria andCgNotIn(List<String> values) {
            addCriterion("cg not in", values, "cg");
            return (Criteria) this;
        }

        public Criteria andCgBetween(String value1, String value2) {
            addCriterion("cg between", value1, value2, "cg");
            return (Criteria) this;
        }

        public Criteria andCgNotBetween(String value1, String value2) {
            addCriterion("cg not between", value1, value2, "cg");
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