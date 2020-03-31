package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicUnitCommonPartExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicUnitCommonPartExample() {
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

        public Criteria andUnitIdIsNull() {
            addCriterion("unit_id is null");
            return (Criteria) this;
        }

        public Criteria andUnitIdIsNotNull() {
            addCriterion("unit_id is not null");
            return (Criteria) this;
        }

        public Criteria andUnitIdEqualTo(Integer value) {
            addCriterion("unit_id =", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotEqualTo(Integer value) {
            addCriterion("unit_id <>", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdGreaterThan(Integer value) {
            addCriterion("unit_id >", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("unit_id >=", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdLessThan(Integer value) {
            addCriterion("unit_id <", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdLessThanOrEqualTo(Integer value) {
            addCriterion("unit_id <=", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdIn(List<Integer> values) {
            addCriterion("unit_id in", values, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotIn(List<Integer> values) {
            addCriterion("unit_id not in", values, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdBetween(Integer value1, Integer value2) {
            addCriterion("unit_id between", value1, value2, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotBetween(Integer value1, Integer value2) {
            addCriterion("unit_id not between", value1, value2, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitCommonPartIsNull() {
            addCriterion("unit_common_part is null");
            return (Criteria) this;
        }

        public Criteria andUnitCommonPartIsNotNull() {
            addCriterion("unit_common_part is not null");
            return (Criteria) this;
        }

        public Criteria andUnitCommonPartEqualTo(String value) {
            addCriterion("unit_common_part =", value, "unitCommonPart");
            return (Criteria) this;
        }

        public Criteria andUnitCommonPartNotEqualTo(String value) {
            addCriterion("unit_common_part <>", value, "unitCommonPart");
            return (Criteria) this;
        }

        public Criteria andUnitCommonPartGreaterThan(String value) {
            addCriterion("unit_common_part >", value, "unitCommonPart");
            return (Criteria) this;
        }

        public Criteria andUnitCommonPartGreaterThanOrEqualTo(String value) {
            addCriterion("unit_common_part >=", value, "unitCommonPart");
            return (Criteria) this;
        }

        public Criteria andUnitCommonPartLessThan(String value) {
            addCriterion("unit_common_part <", value, "unitCommonPart");
            return (Criteria) this;
        }

        public Criteria andUnitCommonPartLessThanOrEqualTo(String value) {
            addCriterion("unit_common_part <=", value, "unitCommonPart");
            return (Criteria) this;
        }

        public Criteria andUnitCommonPartLike(String value) {
            addCriterion("unit_common_part like", value, "unitCommonPart");
            return (Criteria) this;
        }

        public Criteria andUnitCommonPartNotLike(String value) {
            addCriterion("unit_common_part not like", value, "unitCommonPart");
            return (Criteria) this;
        }

        public Criteria andUnitCommonPartIn(List<String> values) {
            addCriterion("unit_common_part in", values, "unitCommonPart");
            return (Criteria) this;
        }

        public Criteria andUnitCommonPartNotIn(List<String> values) {
            addCriterion("unit_common_part not in", values, "unitCommonPart");
            return (Criteria) this;
        }

        public Criteria andUnitCommonPartBetween(String value1, String value2) {
            addCriterion("unit_common_part between", value1, value2, "unitCommonPart");
            return (Criteria) this;
        }

        public Criteria andUnitCommonPartNotBetween(String value1, String value2) {
            addCriterion("unit_common_part not between", value1, value2, "unitCommonPart");
            return (Criteria) this;
        }

        public Criteria andUnitLocationIsNull() {
            addCriterion("unit_location is null");
            return (Criteria) this;
        }

        public Criteria andUnitLocationIsNotNull() {
            addCriterion("unit_location is not null");
            return (Criteria) this;
        }

        public Criteria andUnitLocationEqualTo(String value) {
            addCriterion("unit_location =", value, "unitLocation");
            return (Criteria) this;
        }

        public Criteria andUnitLocationNotEqualTo(String value) {
            addCriterion("unit_location <>", value, "unitLocation");
            return (Criteria) this;
        }

        public Criteria andUnitLocationGreaterThan(String value) {
            addCriterion("unit_location >", value, "unitLocation");
            return (Criteria) this;
        }

        public Criteria andUnitLocationGreaterThanOrEqualTo(String value) {
            addCriterion("unit_location >=", value, "unitLocation");
            return (Criteria) this;
        }

        public Criteria andUnitLocationLessThan(String value) {
            addCriterion("unit_location <", value, "unitLocation");
            return (Criteria) this;
        }

        public Criteria andUnitLocationLessThanOrEqualTo(String value) {
            addCriterion("unit_location <=", value, "unitLocation");
            return (Criteria) this;
        }

        public Criteria andUnitLocationLike(String value) {
            addCriterion("unit_location like", value, "unitLocation");
            return (Criteria) this;
        }

        public Criteria andUnitLocationNotLike(String value) {
            addCriterion("unit_location not like", value, "unitLocation");
            return (Criteria) this;
        }

        public Criteria andUnitLocationIn(List<String> values) {
            addCriterion("unit_location in", values, "unitLocation");
            return (Criteria) this;
        }

        public Criteria andUnitLocationNotIn(List<String> values) {
            addCriterion("unit_location not in", values, "unitLocation");
            return (Criteria) this;
        }

        public Criteria andUnitLocationBetween(String value1, String value2) {
            addCriterion("unit_location between", value1, value2, "unitLocation");
            return (Criteria) this;
        }

        public Criteria andUnitLocationNotBetween(String value1, String value2) {
            addCriterion("unit_location not between", value1, value2, "unitLocation");
            return (Criteria) this;
        }

        public Criteria andUnitMonadIsNull() {
            addCriterion("unit_monad is null");
            return (Criteria) this;
        }

        public Criteria andUnitMonadIsNotNull() {
            addCriterion("unit_monad is not null");
            return (Criteria) this;
        }

        public Criteria andUnitMonadEqualTo(Integer value) {
            addCriterion("unit_monad =", value, "unitMonad");
            return (Criteria) this;
        }

        public Criteria andUnitMonadNotEqualTo(Integer value) {
            addCriterion("unit_monad <>", value, "unitMonad");
            return (Criteria) this;
        }

        public Criteria andUnitMonadGreaterThan(Integer value) {
            addCriterion("unit_monad >", value, "unitMonad");
            return (Criteria) this;
        }

        public Criteria andUnitMonadGreaterThanOrEqualTo(Integer value) {
            addCriterion("unit_monad >=", value, "unitMonad");
            return (Criteria) this;
        }

        public Criteria andUnitMonadLessThan(Integer value) {
            addCriterion("unit_monad <", value, "unitMonad");
            return (Criteria) this;
        }

        public Criteria andUnitMonadLessThanOrEqualTo(Integer value) {
            addCriterion("unit_monad <=", value, "unitMonad");
            return (Criteria) this;
        }

        public Criteria andUnitMonadIn(List<Integer> values) {
            addCriterion("unit_monad in", values, "unitMonad");
            return (Criteria) this;
        }

        public Criteria andUnitMonadNotIn(List<Integer> values) {
            addCriterion("unit_monad not in", values, "unitMonad");
            return (Criteria) this;
        }

        public Criteria andUnitMonadBetween(Integer value1, Integer value2) {
            addCriterion("unit_monad between", value1, value2, "unitMonad");
            return (Criteria) this;
        }

        public Criteria andUnitMonadNotBetween(Integer value1, Integer value2) {
            addCriterion("unit_monad not between", value1, value2, "unitMonad");
            return (Criteria) this;
        }

        public Criteria andUnitQuantityIsNull() {
            addCriterion("unit_quantity is null");
            return (Criteria) this;
        }

        public Criteria andUnitQuantityIsNotNull() {
            addCriterion("unit_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andUnitQuantityEqualTo(Integer value) {
            addCriterion("unit_quantity =", value, "unitQuantity");
            return (Criteria) this;
        }

        public Criteria andUnitQuantityNotEqualTo(Integer value) {
            addCriterion("unit_quantity <>", value, "unitQuantity");
            return (Criteria) this;
        }

        public Criteria andUnitQuantityGreaterThan(Integer value) {
            addCriterion("unit_quantity >", value, "unitQuantity");
            return (Criteria) this;
        }

        public Criteria andUnitQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("unit_quantity >=", value, "unitQuantity");
            return (Criteria) this;
        }

        public Criteria andUnitQuantityLessThan(Integer value) {
            addCriterion("unit_quantity <", value, "unitQuantity");
            return (Criteria) this;
        }

        public Criteria andUnitQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("unit_quantity <=", value, "unitQuantity");
            return (Criteria) this;
        }

        public Criteria andUnitQuantityIn(List<Integer> values) {
            addCriterion("unit_quantity in", values, "unitQuantity");
            return (Criteria) this;
        }

        public Criteria andUnitQuantityNotIn(List<Integer> values) {
            addCriterion("unit_quantity not in", values, "unitQuantity");
            return (Criteria) this;
        }

        public Criteria andUnitQuantityBetween(Integer value1, Integer value2) {
            addCriterion("unit_quantity between", value1, value2, "unitQuantity");
            return (Criteria) this;
        }

        public Criteria andUnitQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("unit_quantity not between", value1, value2, "unitQuantity");
            return (Criteria) this;
        }

        public Criteria andBisDeleteIsNull() {
            addCriterion("bis_delete is null");
            return (Criteria) this;
        }

        public Criteria andBisDeleteIsNotNull() {
            addCriterion("bis_delete is not null");
            return (Criteria) this;
        }

        public Criteria andBisDeleteEqualTo(Boolean value) {
            addCriterion("bis_delete =", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteNotEqualTo(Boolean value) {
            addCriterion("bis_delete <>", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteGreaterThan(Boolean value) {
            addCriterion("bis_delete >", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_delete >=", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteLessThan(Boolean value) {
            addCriterion("bis_delete <", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_delete <=", value, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteIn(List<Boolean> values) {
            addCriterion("bis_delete in", values, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteNotIn(List<Boolean> values) {
            addCriterion("bis_delete not in", values, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_delete between", value1, value2, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andBisDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_delete not between", value1, value2, "bisDelete");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIsNull() {
            addCriterion("gmt_created is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIsNotNull() {
            addCriterion("gmt_created is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedEqualTo(Date value) {
            addCriterion("gmt_created =", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotEqualTo(Date value) {
            addCriterion("gmt_created <>", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedGreaterThan(Date value) {
            addCriterion("gmt_created >", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_created >=", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedLessThan(Date value) {
            addCriterion("gmt_created <", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_created <=", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIn(List<Date> values) {
            addCriterion("gmt_created in", values, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotIn(List<Date> values) {
            addCriterion("gmt_created not in", values, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedBetween(Date value1, Date value2) {
            addCriterion("gmt_created between", value1, value2, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_created not between", value1, value2, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
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