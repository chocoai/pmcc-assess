package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBuildingNewRateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataBuildingNewRateExample() {
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

        public Criteria andBuildingStructureIsNull() {
            addCriterion("building_structure is null");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureIsNotNull() {
            addCriterion("building_structure is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureEqualTo(String value) {
            addCriterion("building_structure =", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureNotEqualTo(String value) {
            addCriterion("building_structure <>", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureGreaterThan(String value) {
            addCriterion("building_structure >", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureGreaterThanOrEqualTo(String value) {
            addCriterion("building_structure >=", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLessThan(String value) {
            addCriterion("building_structure <", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLessThanOrEqualTo(String value) {
            addCriterion("building_structure <=", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureLike(String value) {
            addCriterion("building_structure like", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureNotLike(String value) {
            addCriterion("building_structure not like", value, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureIn(List<String> values) {
            addCriterion("building_structure in", values, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureNotIn(List<String> values) {
            addCriterion("building_structure not in", values, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureBetween(String value1, String value2) {
            addCriterion("building_structure between", value1, value2, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingStructureNotBetween(String value1, String value2) {
            addCriterion("building_structure not between", value1, value2, "buildingStructure");
            return (Criteria) this;
        }

        public Criteria andBuildingUseIsNull() {
            addCriterion("building_use is null");
            return (Criteria) this;
        }

        public Criteria andBuildingUseIsNotNull() {
            addCriterion("building_use is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingUseEqualTo(Integer value) {
            addCriterion("building_use =", value, "buildingUse");
            return (Criteria) this;
        }

        public Criteria andBuildingUseNotEqualTo(Integer value) {
            addCriterion("building_use <>", value, "buildingUse");
            return (Criteria) this;
        }

        public Criteria andBuildingUseGreaterThan(Integer value) {
            addCriterion("building_use >", value, "buildingUse");
            return (Criteria) this;
        }

        public Criteria andBuildingUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_use >=", value, "buildingUse");
            return (Criteria) this;
        }

        public Criteria andBuildingUseLessThan(Integer value) {
            addCriterion("building_use <", value, "buildingUse");
            return (Criteria) this;
        }

        public Criteria andBuildingUseLessThanOrEqualTo(Integer value) {
            addCriterion("building_use <=", value, "buildingUse");
            return (Criteria) this;
        }

        public Criteria andBuildingUseIn(List<Integer> values) {
            addCriterion("building_use in", values, "buildingUse");
            return (Criteria) this;
        }

        public Criteria andBuildingUseNotIn(List<Integer> values) {
            addCriterion("building_use not in", values, "buildingUse");
            return (Criteria) this;
        }

        public Criteria andBuildingUseBetween(Integer value1, Integer value2) {
            addCriterion("building_use between", value1, value2, "buildingUse");
            return (Criteria) this;
        }

        public Criteria andBuildingUseNotBetween(Integer value1, Integer value2) {
            addCriterion("building_use not between", value1, value2, "buildingUse");
            return (Criteria) this;
        }

        public Criteria andDurableLifeIsNull() {
            addCriterion("durable_life is null");
            return (Criteria) this;
        }

        public Criteria andDurableLifeIsNotNull() {
            addCriterion("durable_life is not null");
            return (Criteria) this;
        }

        public Criteria andDurableLifeEqualTo(Integer value) {
            addCriterion("durable_life =", value, "durableLife");
            return (Criteria) this;
        }

        public Criteria andDurableLifeNotEqualTo(Integer value) {
            addCriterion("durable_life <>", value, "durableLife");
            return (Criteria) this;
        }

        public Criteria andDurableLifeGreaterThan(Integer value) {
            addCriterion("durable_life >", value, "durableLife");
            return (Criteria) this;
        }

        public Criteria andDurableLifeGreaterThanOrEqualTo(Integer value) {
            addCriterion("durable_life >=", value, "durableLife");
            return (Criteria) this;
        }

        public Criteria andDurableLifeLessThan(Integer value) {
            addCriterion("durable_life <", value, "durableLife");
            return (Criteria) this;
        }

        public Criteria andDurableLifeLessThanOrEqualTo(Integer value) {
            addCriterion("durable_life <=", value, "durableLife");
            return (Criteria) this;
        }

        public Criteria andDurableLifeIn(List<Integer> values) {
            addCriterion("durable_life in", values, "durableLife");
            return (Criteria) this;
        }

        public Criteria andDurableLifeNotIn(List<Integer> values) {
            addCriterion("durable_life not in", values, "durableLife");
            return (Criteria) this;
        }

        public Criteria andDurableLifeBetween(Integer value1, Integer value2) {
            addCriterion("durable_life between", value1, value2, "durableLife");
            return (Criteria) this;
        }

        public Criteria andDurableLifeNotBetween(Integer value1, Integer value2) {
            addCriterion("durable_life not between", value1, value2, "durableLife");
            return (Criteria) this;
        }

        public Criteria andResidualValueIsNull() {
            addCriterion("residual_value is null");
            return (Criteria) this;
        }

        public Criteria andResidualValueIsNotNull() {
            addCriterion("residual_value is not null");
            return (Criteria) this;
        }

        public Criteria andResidualValueEqualTo(String value) {
            addCriterion("residual_value =", value, "residualValue");
            return (Criteria) this;
        }

        public Criteria andResidualValueNotEqualTo(String value) {
            addCriterion("residual_value <>", value, "residualValue");
            return (Criteria) this;
        }

        public Criteria andResidualValueGreaterThan(String value) {
            addCriterion("residual_value >", value, "residualValue");
            return (Criteria) this;
        }

        public Criteria andResidualValueGreaterThanOrEqualTo(String value) {
            addCriterion("residual_value >=", value, "residualValue");
            return (Criteria) this;
        }

        public Criteria andResidualValueLessThan(String value) {
            addCriterion("residual_value <", value, "residualValue");
            return (Criteria) this;
        }

        public Criteria andResidualValueLessThanOrEqualTo(String value) {
            addCriterion("residual_value <=", value, "residualValue");
            return (Criteria) this;
        }

        public Criteria andResidualValueLike(String value) {
            addCriterion("residual_value like", value, "residualValue");
            return (Criteria) this;
        }

        public Criteria andResidualValueNotLike(String value) {
            addCriterion("residual_value not like", value, "residualValue");
            return (Criteria) this;
        }

        public Criteria andResidualValueIn(List<String> values) {
            addCriterion("residual_value in", values, "residualValue");
            return (Criteria) this;
        }

        public Criteria andResidualValueNotIn(List<String> values) {
            addCriterion("residual_value not in", values, "residualValue");
            return (Criteria) this;
        }

        public Criteria andResidualValueBetween(String value1, String value2) {
            addCriterion("residual_value between", value1, value2, "residualValue");
            return (Criteria) this;
        }

        public Criteria andResidualValueNotBetween(String value1, String value2) {
            addCriterion("residual_value not between", value1, value2, "residualValue");
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