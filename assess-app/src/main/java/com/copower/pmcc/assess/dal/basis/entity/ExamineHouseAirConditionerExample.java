package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamineHouseAirConditionerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamineHouseAirConditionerExample() {
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

        public Criteria andHouseIdIsNull() {
            addCriterion("house_id is null");
            return (Criteria) this;
        }

        public Criteria andHouseIdIsNotNull() {
            addCriterion("house_id is not null");
            return (Criteria) this;
        }

        public Criteria andHouseIdEqualTo(Integer value) {
            addCriterion("house_id =", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotEqualTo(Integer value) {
            addCriterion("house_id <>", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdGreaterThan(Integer value) {
            addCriterion("house_id >", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("house_id >=", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdLessThan(Integer value) {
            addCriterion("house_id <", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("house_id <=", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdIn(List<Integer> values) {
            addCriterion("house_id in", values, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotIn(List<Integer> values) {
            addCriterion("house_id not in", values, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdBetween(Integer value1, Integer value2) {
            addCriterion("house_id between", value1, value2, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("house_id not between", value1, value2, "houseId");
            return (Criteria) this;
        }

        public Criteria andAirConditioningSystemIsNull() {
            addCriterion("air_conditioning_system is null");
            return (Criteria) this;
        }

        public Criteria andAirConditioningSystemIsNotNull() {
            addCriterion("air_conditioning_system is not null");
            return (Criteria) this;
        }

        public Criteria andAirConditioningSystemEqualTo(String value) {
            addCriterion("air_conditioning_system =", value, "airConditioningSystem");
            return (Criteria) this;
        }

        public Criteria andAirConditioningSystemNotEqualTo(String value) {
            addCriterion("air_conditioning_system <>", value, "airConditioningSystem");
            return (Criteria) this;
        }

        public Criteria andAirConditioningSystemGreaterThan(String value) {
            addCriterion("air_conditioning_system >", value, "airConditioningSystem");
            return (Criteria) this;
        }

        public Criteria andAirConditioningSystemGreaterThanOrEqualTo(String value) {
            addCriterion("air_conditioning_system >=", value, "airConditioningSystem");
            return (Criteria) this;
        }

        public Criteria andAirConditioningSystemLessThan(String value) {
            addCriterion("air_conditioning_system <", value, "airConditioningSystem");
            return (Criteria) this;
        }

        public Criteria andAirConditioningSystemLessThanOrEqualTo(String value) {
            addCriterion("air_conditioning_system <=", value, "airConditioningSystem");
            return (Criteria) this;
        }

        public Criteria andAirConditioningSystemLike(String value) {
            addCriterion("air_conditioning_system like", value, "airConditioningSystem");
            return (Criteria) this;
        }

        public Criteria andAirConditioningSystemNotLike(String value) {
            addCriterion("air_conditioning_system not like", value, "airConditioningSystem");
            return (Criteria) this;
        }

        public Criteria andAirConditioningSystemIn(List<String> values) {
            addCriterion("air_conditioning_system in", values, "airConditioningSystem");
            return (Criteria) this;
        }

        public Criteria andAirConditioningSystemNotIn(List<String> values) {
            addCriterion("air_conditioning_system not in", values, "airConditioningSystem");
            return (Criteria) this;
        }

        public Criteria andAirConditioningSystemBetween(String value1, String value2) {
            addCriterion("air_conditioning_system between", value1, value2, "airConditioningSystem");
            return (Criteria) this;
        }

        public Criteria andAirConditioningSystemNotBetween(String value1, String value2) {
            addCriterion("air_conditioning_system not between", value1, value2, "airConditioningSystem");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentIsNull() {
            addCriterion("air_conditioning_equipment is null");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentIsNotNull() {
            addCriterion("air_conditioning_equipment is not null");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentEqualTo(String value) {
            addCriterion("air_conditioning_equipment =", value, "airConditioningEquipment");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentNotEqualTo(String value) {
            addCriterion("air_conditioning_equipment <>", value, "airConditioningEquipment");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentGreaterThan(String value) {
            addCriterion("air_conditioning_equipment >", value, "airConditioningEquipment");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentGreaterThanOrEqualTo(String value) {
            addCriterion("air_conditioning_equipment >=", value, "airConditioningEquipment");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentLessThan(String value) {
            addCriterion("air_conditioning_equipment <", value, "airConditioningEquipment");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentLessThanOrEqualTo(String value) {
            addCriterion("air_conditioning_equipment <=", value, "airConditioningEquipment");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentLike(String value) {
            addCriterion("air_conditioning_equipment like", value, "airConditioningEquipment");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentNotLike(String value) {
            addCriterion("air_conditioning_equipment not like", value, "airConditioningEquipment");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentIn(List<String> values) {
            addCriterion("air_conditioning_equipment in", values, "airConditioningEquipment");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentNotIn(List<String> values) {
            addCriterion("air_conditioning_equipment not in", values, "airConditioningEquipment");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentBetween(String value1, String value2) {
            addCriterion("air_conditioning_equipment between", value1, value2, "airConditioningEquipment");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentNotBetween(String value1, String value2) {
            addCriterion("air_conditioning_equipment not between", value1, value2, "airConditioningEquipment");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentPriceIsNull() {
            addCriterion("air_conditioning_equipment_price is null");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentPriceIsNotNull() {
            addCriterion("air_conditioning_equipment_price is not null");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentPriceEqualTo(String value) {
            addCriterion("air_conditioning_equipment_price =", value, "airConditioningEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentPriceNotEqualTo(String value) {
            addCriterion("air_conditioning_equipment_price <>", value, "airConditioningEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentPriceGreaterThan(String value) {
            addCriterion("air_conditioning_equipment_price >", value, "airConditioningEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentPriceGreaterThanOrEqualTo(String value) {
            addCriterion("air_conditioning_equipment_price >=", value, "airConditioningEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentPriceLessThan(String value) {
            addCriterion("air_conditioning_equipment_price <", value, "airConditioningEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentPriceLessThanOrEqualTo(String value) {
            addCriterion("air_conditioning_equipment_price <=", value, "airConditioningEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentPriceLike(String value) {
            addCriterion("air_conditioning_equipment_price like", value, "airConditioningEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentPriceNotLike(String value) {
            addCriterion("air_conditioning_equipment_price not like", value, "airConditioningEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentPriceIn(List<String> values) {
            addCriterion("air_conditioning_equipment_price in", values, "airConditioningEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentPriceNotIn(List<String> values) {
            addCriterion("air_conditioning_equipment_price not in", values, "airConditioningEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentPriceBetween(String value1, String value2) {
            addCriterion("air_conditioning_equipment_price between", value1, value2, "airConditioningEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andAirConditioningEquipmentPriceNotBetween(String value1, String value2) {
            addCriterion("air_conditioning_equipment_price not between", value1, value2, "airConditioningEquipmentPrice");
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