package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.List;

public class FuniHousesBuildExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FuniHousesBuildExample() {
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

        public Criteria andLdIsNull() {
            addCriterion("ld is null");
            return (Criteria) this;
        }

        public Criteria andLdIsNotNull() {
            addCriterion("ld is not null");
            return (Criteria) this;
        }

        public Criteria andLdEqualTo(String value) {
            addCriterion("ld =", value, "ld");
            return (Criteria) this;
        }

        public Criteria andLdNotEqualTo(String value) {
            addCriterion("ld <>", value, "ld");
            return (Criteria) this;
        }

        public Criteria andLdGreaterThan(String value) {
            addCriterion("ld >", value, "ld");
            return (Criteria) this;
        }

        public Criteria andLdGreaterThanOrEqualTo(String value) {
            addCriterion("ld >=", value, "ld");
            return (Criteria) this;
        }

        public Criteria andLdLessThan(String value) {
            addCriterion("ld <", value, "ld");
            return (Criteria) this;
        }

        public Criteria andLdLessThanOrEqualTo(String value) {
            addCriterion("ld <=", value, "ld");
            return (Criteria) this;
        }

        public Criteria andLdLike(String value) {
            addCriterion("ld like", value, "ld");
            return (Criteria) this;
        }

        public Criteria andLdNotLike(String value) {
            addCriterion("ld not like", value, "ld");
            return (Criteria) this;
        }

        public Criteria andLdIn(List<String> values) {
            addCriterion("ld in", values, "ld");
            return (Criteria) this;
        }

        public Criteria andLdNotIn(List<String> values) {
            addCriterion("ld not in", values, "ld");
            return (Criteria) this;
        }

        public Criteria andLdBetween(String value1, String value2) {
            addCriterion("ld between", value1, value2, "ld");
            return (Criteria) this;
        }

        public Criteria andLdNotBetween(String value1, String value2) {
            addCriterion("ld not between", value1, value2, "ld");
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

        public Criteria andWzqkIsNull() {
            addCriterion("wzqk is null");
            return (Criteria) this;
        }

        public Criteria andWzqkIsNotNull() {
            addCriterion("wzqk is not null");
            return (Criteria) this;
        }

        public Criteria andWzqkEqualTo(String value) {
            addCriterion("wzqk =", value, "wzqk");
            return (Criteria) this;
        }

        public Criteria andWzqkNotEqualTo(String value) {
            addCriterion("wzqk <>", value, "wzqk");
            return (Criteria) this;
        }

        public Criteria andWzqkGreaterThan(String value) {
            addCriterion("wzqk >", value, "wzqk");
            return (Criteria) this;
        }

        public Criteria andWzqkGreaterThanOrEqualTo(String value) {
            addCriterion("wzqk >=", value, "wzqk");
            return (Criteria) this;
        }

        public Criteria andWzqkLessThan(String value) {
            addCriterion("wzqk <", value, "wzqk");
            return (Criteria) this;
        }

        public Criteria andWzqkLessThanOrEqualTo(String value) {
            addCriterion("wzqk <=", value, "wzqk");
            return (Criteria) this;
        }

        public Criteria andWzqkLike(String value) {
            addCriterion("wzqk like", value, "wzqk");
            return (Criteria) this;
        }

        public Criteria andWzqkNotLike(String value) {
            addCriterion("wzqk not like", value, "wzqk");
            return (Criteria) this;
        }

        public Criteria andWzqkIn(List<String> values) {
            addCriterion("wzqk in", values, "wzqk");
            return (Criteria) this;
        }

        public Criteria andWzqkNotIn(List<String> values) {
            addCriterion("wzqk not in", values, "wzqk");
            return (Criteria) this;
        }

        public Criteria andWzqkBetween(String value1, String value2) {
            addCriterion("wzqk between", value1, value2, "wzqk");
            return (Criteria) this;
        }

        public Criteria andWzqkNotBetween(String value1, String value2) {
            addCriterion("wzqk not between", value1, value2, "wzqk");
            return (Criteria) this;
        }

        public Criteria andGgzxqkIsNull() {
            addCriterion("ggzxqk is null");
            return (Criteria) this;
        }

        public Criteria andGgzxqkIsNotNull() {
            addCriterion("ggzxqk is not null");
            return (Criteria) this;
        }

        public Criteria andGgzxqkEqualTo(String value) {
            addCriterion("ggzxqk =", value, "ggzxqk");
            return (Criteria) this;
        }

        public Criteria andGgzxqkNotEqualTo(String value) {
            addCriterion("ggzxqk <>", value, "ggzxqk");
            return (Criteria) this;
        }

        public Criteria andGgzxqkGreaterThan(String value) {
            addCriterion("ggzxqk >", value, "ggzxqk");
            return (Criteria) this;
        }

        public Criteria andGgzxqkGreaterThanOrEqualTo(String value) {
            addCriterion("ggzxqk >=", value, "ggzxqk");
            return (Criteria) this;
        }

        public Criteria andGgzxqkLessThan(String value) {
            addCriterion("ggzxqk <", value, "ggzxqk");
            return (Criteria) this;
        }

        public Criteria andGgzxqkLessThanOrEqualTo(String value) {
            addCriterion("ggzxqk <=", value, "ggzxqk");
            return (Criteria) this;
        }

        public Criteria andGgzxqkLike(String value) {
            addCriterion("ggzxqk like", value, "ggzxqk");
            return (Criteria) this;
        }

        public Criteria andGgzxqkNotLike(String value) {
            addCriterion("ggzxqk not like", value, "ggzxqk");
            return (Criteria) this;
        }

        public Criteria andGgzxqkIn(List<String> values) {
            addCriterion("ggzxqk in", values, "ggzxqk");
            return (Criteria) this;
        }

        public Criteria andGgzxqkNotIn(List<String> values) {
            addCriterion("ggzxqk not in", values, "ggzxqk");
            return (Criteria) this;
        }

        public Criteria andGgzxqkBetween(String value1, String value2) {
            addCriterion("ggzxqk between", value1, value2, "ggzxqk");
            return (Criteria) this;
        }

        public Criteria andGgzxqkNotBetween(String value1, String value2) {
            addCriterion("ggzxqk not between", value1, value2, "ggzxqk");
            return (Criteria) this;
        }

        public Criteria andPbdtIsNull() {
            addCriterion("pbdt is null");
            return (Criteria) this;
        }

        public Criteria andPbdtIsNotNull() {
            addCriterion("pbdt is not null");
            return (Criteria) this;
        }

        public Criteria andPbdtEqualTo(String value) {
            addCriterion("pbdt =", value, "pbdt");
            return (Criteria) this;
        }

        public Criteria andPbdtNotEqualTo(String value) {
            addCriterion("pbdt <>", value, "pbdt");
            return (Criteria) this;
        }

        public Criteria andPbdtGreaterThan(String value) {
            addCriterion("pbdt >", value, "pbdt");
            return (Criteria) this;
        }

        public Criteria andPbdtGreaterThanOrEqualTo(String value) {
            addCriterion("pbdt >=", value, "pbdt");
            return (Criteria) this;
        }

        public Criteria andPbdtLessThan(String value) {
            addCriterion("pbdt <", value, "pbdt");
            return (Criteria) this;
        }

        public Criteria andPbdtLessThanOrEqualTo(String value) {
            addCriterion("pbdt <=", value, "pbdt");
            return (Criteria) this;
        }

        public Criteria andPbdtLike(String value) {
            addCriterion("pbdt like", value, "pbdt");
            return (Criteria) this;
        }

        public Criteria andPbdtNotLike(String value) {
            addCriterion("pbdt not like", value, "pbdt");
            return (Criteria) this;
        }

        public Criteria andPbdtIn(List<String> values) {
            addCriterion("pbdt in", values, "pbdt");
            return (Criteria) this;
        }

        public Criteria andPbdtNotIn(List<String> values) {
            addCriterion("pbdt not in", values, "pbdt");
            return (Criteria) this;
        }

        public Criteria andPbdtBetween(String value1, String value2) {
            addCriterion("pbdt between", value1, value2, "pbdt");
            return (Criteria) this;
        }

        public Criteria andPbdtNotBetween(String value1, String value2) {
            addCriterion("pbdt not between", value1, value2, "pbdt");
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