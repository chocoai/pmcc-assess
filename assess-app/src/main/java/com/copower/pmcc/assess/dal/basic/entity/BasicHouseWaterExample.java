package com.copower.pmcc.assess.dal.basic.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicHouseWaterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicHouseWaterExample() {
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

        public Criteria andSupplyErectionMethodIsNull() {
            addCriterion("supply_erection_method is null");
            return (Criteria) this;
        }

        public Criteria andSupplyErectionMethodIsNotNull() {
            addCriterion("supply_erection_method is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyErectionMethodEqualTo(Integer value) {
            addCriterion("supply_erection_method =", value, "supplyErectionMethod");
            return (Criteria) this;
        }

        public Criteria andSupplyErectionMethodNotEqualTo(Integer value) {
            addCriterion("supply_erection_method <>", value, "supplyErectionMethod");
            return (Criteria) this;
        }

        public Criteria andSupplyErectionMethodGreaterThan(Integer value) {
            addCriterion("supply_erection_method >", value, "supplyErectionMethod");
            return (Criteria) this;
        }

        public Criteria andSupplyErectionMethodGreaterThanOrEqualTo(Integer value) {
            addCriterion("supply_erection_method >=", value, "supplyErectionMethod");
            return (Criteria) this;
        }

        public Criteria andSupplyErectionMethodLessThan(Integer value) {
            addCriterion("supply_erection_method <", value, "supplyErectionMethod");
            return (Criteria) this;
        }

        public Criteria andSupplyErectionMethodLessThanOrEqualTo(Integer value) {
            addCriterion("supply_erection_method <=", value, "supplyErectionMethod");
            return (Criteria) this;
        }

        public Criteria andSupplyErectionMethodIn(List<Integer> values) {
            addCriterion("supply_erection_method in", values, "supplyErectionMethod");
            return (Criteria) this;
        }

        public Criteria andSupplyErectionMethodNotIn(List<Integer> values) {
            addCriterion("supply_erection_method not in", values, "supplyErectionMethod");
            return (Criteria) this;
        }

        public Criteria andSupplyErectionMethodBetween(Integer value1, Integer value2) {
            addCriterion("supply_erection_method between", value1, value2, "supplyErectionMethod");
            return (Criteria) this;
        }

        public Criteria andSupplyErectionMethodNotBetween(Integer value1, Integer value2) {
            addCriterion("supply_erection_method not between", value1, value2, "supplyErectionMethod");
            return (Criteria) this;
        }

        public Criteria andIntakePointNumberIsNull() {
            addCriterion("intake_point_number is null");
            return (Criteria) this;
        }

        public Criteria andIntakePointNumberIsNotNull() {
            addCriterion("intake_point_number is not null");
            return (Criteria) this;
        }

        public Criteria andIntakePointNumberEqualTo(String value) {
            addCriterion("intake_point_number =", value, "intakePointNumber");
            return (Criteria) this;
        }

        public Criteria andIntakePointNumberNotEqualTo(String value) {
            addCriterion("intake_point_number <>", value, "intakePointNumber");
            return (Criteria) this;
        }

        public Criteria andIntakePointNumberGreaterThan(String value) {
            addCriterion("intake_point_number >", value, "intakePointNumber");
            return (Criteria) this;
        }

        public Criteria andIntakePointNumberGreaterThanOrEqualTo(String value) {
            addCriterion("intake_point_number >=", value, "intakePointNumber");
            return (Criteria) this;
        }

        public Criteria andIntakePointNumberLessThan(String value) {
            addCriterion("intake_point_number <", value, "intakePointNumber");
            return (Criteria) this;
        }

        public Criteria andIntakePointNumberLessThanOrEqualTo(String value) {
            addCriterion("intake_point_number <=", value, "intakePointNumber");
            return (Criteria) this;
        }

        public Criteria andIntakePointNumberLike(String value) {
            addCriterion("intake_point_number like", value, "intakePointNumber");
            return (Criteria) this;
        }

        public Criteria andIntakePointNumberNotLike(String value) {
            addCriterion("intake_point_number not like", value, "intakePointNumber");
            return (Criteria) this;
        }

        public Criteria andIntakePointNumberIn(List<String> values) {
            addCriterion("intake_point_number in", values, "intakePointNumber");
            return (Criteria) this;
        }

        public Criteria andIntakePointNumberNotIn(List<String> values) {
            addCriterion("intake_point_number not in", values, "intakePointNumber");
            return (Criteria) this;
        }

        public Criteria andIntakePointNumberBetween(String value1, String value2) {
            addCriterion("intake_point_number between", value1, value2, "intakePointNumber");
            return (Criteria) this;
        }

        public Criteria andIntakePointNumberNotBetween(String value1, String value2) {
            addCriterion("intake_point_number not between", value1, value2, "intakePointNumber");
            return (Criteria) this;
        }

        public Criteria andPretreatedWaterIsNull() {
            addCriterion("pretreated_water is null");
            return (Criteria) this;
        }

        public Criteria andPretreatedWaterIsNotNull() {
            addCriterion("pretreated_water is not null");
            return (Criteria) this;
        }

        public Criteria andPretreatedWaterEqualTo(Integer value) {
            addCriterion("pretreated_water =", value, "pretreatedWater");
            return (Criteria) this;
        }

        public Criteria andPretreatedWaterNotEqualTo(Integer value) {
            addCriterion("pretreated_water <>", value, "pretreatedWater");
            return (Criteria) this;
        }

        public Criteria andPretreatedWaterGreaterThan(Integer value) {
            addCriterion("pretreated_water >", value, "pretreatedWater");
            return (Criteria) this;
        }

        public Criteria andPretreatedWaterGreaterThanOrEqualTo(Integer value) {
            addCriterion("pretreated_water >=", value, "pretreatedWater");
            return (Criteria) this;
        }

        public Criteria andPretreatedWaterLessThan(Integer value) {
            addCriterion("pretreated_water <", value, "pretreatedWater");
            return (Criteria) this;
        }

        public Criteria andPretreatedWaterLessThanOrEqualTo(Integer value) {
            addCriterion("pretreated_water <=", value, "pretreatedWater");
            return (Criteria) this;
        }

        public Criteria andPretreatedWaterIn(List<Integer> values) {
            addCriterion("pretreated_water in", values, "pretreatedWater");
            return (Criteria) this;
        }

        public Criteria andPretreatedWaterNotIn(List<Integer> values) {
            addCriterion("pretreated_water not in", values, "pretreatedWater");
            return (Criteria) this;
        }

        public Criteria andPretreatedWaterBetween(Integer value1, Integer value2) {
            addCriterion("pretreated_water between", value1, value2, "pretreatedWater");
            return (Criteria) this;
        }

        public Criteria andPretreatedWaterNotBetween(Integer value1, Integer value2) {
            addCriterion("pretreated_water not between", value1, value2, "pretreatedWater");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentIsNull() {
            addCriterion("purification_equipment is null");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentIsNotNull() {
            addCriterion("purification_equipment is not null");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentEqualTo(String value) {
            addCriterion("purification_equipment =", value, "purificationEquipment");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentNotEqualTo(String value) {
            addCriterion("purification_equipment <>", value, "purificationEquipment");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentGreaterThan(String value) {
            addCriterion("purification_equipment >", value, "purificationEquipment");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentGreaterThanOrEqualTo(String value) {
            addCriterion("purification_equipment >=", value, "purificationEquipment");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentLessThan(String value) {
            addCriterion("purification_equipment <", value, "purificationEquipment");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentLessThanOrEqualTo(String value) {
            addCriterion("purification_equipment <=", value, "purificationEquipment");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentLike(String value) {
            addCriterion("purification_equipment like", value, "purificationEquipment");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentNotLike(String value) {
            addCriterion("purification_equipment not like", value, "purificationEquipment");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentIn(List<String> values) {
            addCriterion("purification_equipment in", values, "purificationEquipment");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentNotIn(List<String> values) {
            addCriterion("purification_equipment not in", values, "purificationEquipment");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentBetween(String value1, String value2) {
            addCriterion("purification_equipment between", value1, value2, "purificationEquipment");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentNotBetween(String value1, String value2) {
            addCriterion("purification_equipment not between", value1, value2, "purificationEquipment");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceIsNull() {
            addCriterion("purification_equipment_price is null");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceIsNotNull() {
            addCriterion("purification_equipment_price is not null");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceEqualTo(String value) {
            addCriterion("purification_equipment_price =", value, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceNotEqualTo(String value) {
            addCriterion("purification_equipment_price <>", value, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceGreaterThan(String value) {
            addCriterion("purification_equipment_price >", value, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceGreaterThanOrEqualTo(String value) {
            addCriterion("purification_equipment_price >=", value, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceLessThan(String value) {
            addCriterion("purification_equipment_price <", value, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceLessThanOrEqualTo(String value) {
            addCriterion("purification_equipment_price <=", value, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceLike(String value) {
            addCriterion("purification_equipment_price like", value, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceNotLike(String value) {
            addCriterion("purification_equipment_price not like", value, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceIn(List<String> values) {
            addCriterion("purification_equipment_price in", values, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceNotIn(List<String> values) {
            addCriterion("purification_equipment_price not in", values, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceBetween(String value1, String value2) {
            addCriterion("purification_equipment_price between", value1, value2, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceNotBetween(String value1, String value2) {
            addCriterion("purification_equipment_price not between", value1, value2, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andNatrueIntakePointNumberIsNull() {
            addCriterion("natrue_intake_point_number is null");
            return (Criteria) this;
        }

        public Criteria andNatrueIntakePointNumberIsNotNull() {
            addCriterion("natrue_intake_point_number is not null");
            return (Criteria) this;
        }

        public Criteria andNatrueIntakePointNumberEqualTo(String value) {
            addCriterion("natrue_intake_point_number =", value, "natrueIntakePointNumber");
            return (Criteria) this;
        }

        public Criteria andNatrueIntakePointNumberNotEqualTo(String value) {
            addCriterion("natrue_intake_point_number <>", value, "natrueIntakePointNumber");
            return (Criteria) this;
        }

        public Criteria andNatrueIntakePointNumberGreaterThan(String value) {
            addCriterion("natrue_intake_point_number >", value, "natrueIntakePointNumber");
            return (Criteria) this;
        }

        public Criteria andNatrueIntakePointNumberGreaterThanOrEqualTo(String value) {
            addCriterion("natrue_intake_point_number >=", value, "natrueIntakePointNumber");
            return (Criteria) this;
        }

        public Criteria andNatrueIntakePointNumberLessThan(String value) {
            addCriterion("natrue_intake_point_number <", value, "natrueIntakePointNumber");
            return (Criteria) this;
        }

        public Criteria andNatrueIntakePointNumberLessThanOrEqualTo(String value) {
            addCriterion("natrue_intake_point_number <=", value, "natrueIntakePointNumber");
            return (Criteria) this;
        }

        public Criteria andNatrueIntakePointNumberLike(String value) {
            addCriterion("natrue_intake_point_number like", value, "natrueIntakePointNumber");
            return (Criteria) this;
        }

        public Criteria andNatrueIntakePointNumberNotLike(String value) {
            addCriterion("natrue_intake_point_number not like", value, "natrueIntakePointNumber");
            return (Criteria) this;
        }

        public Criteria andNatrueIntakePointNumberIn(List<String> values) {
            addCriterion("natrue_intake_point_number in", values, "natrueIntakePointNumber");
            return (Criteria) this;
        }

        public Criteria andNatrueIntakePointNumberNotIn(List<String> values) {
            addCriterion("natrue_intake_point_number not in", values, "natrueIntakePointNumber");
            return (Criteria) this;
        }

        public Criteria andNatrueIntakePointNumberBetween(String value1, String value2) {
            addCriterion("natrue_intake_point_number between", value1, value2, "natrueIntakePointNumber");
            return (Criteria) this;
        }

        public Criteria andNatrueIntakePointNumberNotBetween(String value1, String value2) {
            addCriterion("natrue_intake_point_number not between", value1, value2, "natrueIntakePointNumber");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentIsNull() {
            addCriterion("water_intake_equipment is null");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentIsNotNull() {
            addCriterion("water_intake_equipment is not null");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentEqualTo(String value) {
            addCriterion("water_intake_equipment =", value, "waterIntakeEquipment");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentNotEqualTo(String value) {
            addCriterion("water_intake_equipment <>", value, "waterIntakeEquipment");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentGreaterThan(String value) {
            addCriterion("water_intake_equipment >", value, "waterIntakeEquipment");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentGreaterThanOrEqualTo(String value) {
            addCriterion("water_intake_equipment >=", value, "waterIntakeEquipment");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentLessThan(String value) {
            addCriterion("water_intake_equipment <", value, "waterIntakeEquipment");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentLessThanOrEqualTo(String value) {
            addCriterion("water_intake_equipment <=", value, "waterIntakeEquipment");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentLike(String value) {
            addCriterion("water_intake_equipment like", value, "waterIntakeEquipment");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentNotLike(String value) {
            addCriterion("water_intake_equipment not like", value, "waterIntakeEquipment");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentIn(List<String> values) {
            addCriterion("water_intake_equipment in", values, "waterIntakeEquipment");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentNotIn(List<String> values) {
            addCriterion("water_intake_equipment not in", values, "waterIntakeEquipment");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentBetween(String value1, String value2) {
            addCriterion("water_intake_equipment between", value1, value2, "waterIntakeEquipment");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentNotBetween(String value1, String value2) {
            addCriterion("water_intake_equipment not between", value1, value2, "waterIntakeEquipment");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentPriceIsNull() {
            addCriterion("water_intake_equipment_price is null");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentPriceIsNotNull() {
            addCriterion("water_intake_equipment_price is not null");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentPriceEqualTo(String value) {
            addCriterion("water_intake_equipment_price =", value, "waterIntakeEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentPriceNotEqualTo(String value) {
            addCriterion("water_intake_equipment_price <>", value, "waterIntakeEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentPriceGreaterThan(String value) {
            addCriterion("water_intake_equipment_price >", value, "waterIntakeEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentPriceGreaterThanOrEqualTo(String value) {
            addCriterion("water_intake_equipment_price >=", value, "waterIntakeEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentPriceLessThan(String value) {
            addCriterion("water_intake_equipment_price <", value, "waterIntakeEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentPriceLessThanOrEqualTo(String value) {
            addCriterion("water_intake_equipment_price <=", value, "waterIntakeEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentPriceLike(String value) {
            addCriterion("water_intake_equipment_price like", value, "waterIntakeEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentPriceNotLike(String value) {
            addCriterion("water_intake_equipment_price not like", value, "waterIntakeEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentPriceIn(List<String> values) {
            addCriterion("water_intake_equipment_price in", values, "waterIntakeEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentPriceNotIn(List<String> values) {
            addCriterion("water_intake_equipment_price not in", values, "waterIntakeEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentPriceBetween(String value1, String value2) {
            addCriterion("water_intake_equipment_price between", value1, value2, "waterIntakeEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andWaterIntakeEquipmentPriceNotBetween(String value1, String value2) {
            addCriterion("water_intake_equipment_price not between", value1, value2, "waterIntakeEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andDrainageErectionMethodIsNull() {
            addCriterion("drainage_erection_method is null");
            return (Criteria) this;
        }

        public Criteria andDrainageErectionMethodIsNotNull() {
            addCriterion("drainage_erection_method is not null");
            return (Criteria) this;
        }

        public Criteria andDrainageErectionMethodEqualTo(String value) {
            addCriterion("drainage_erection_method =", value, "drainageErectionMethod");
            return (Criteria) this;
        }

        public Criteria andDrainageErectionMethodNotEqualTo(String value) {
            addCriterion("drainage_erection_method <>", value, "drainageErectionMethod");
            return (Criteria) this;
        }

        public Criteria andDrainageErectionMethodGreaterThan(String value) {
            addCriterion("drainage_erection_method >", value, "drainageErectionMethod");
            return (Criteria) this;
        }

        public Criteria andDrainageErectionMethodGreaterThanOrEqualTo(String value) {
            addCriterion("drainage_erection_method >=", value, "drainageErectionMethod");
            return (Criteria) this;
        }

        public Criteria andDrainageErectionMethodLessThan(String value) {
            addCriterion("drainage_erection_method <", value, "drainageErectionMethod");
            return (Criteria) this;
        }

        public Criteria andDrainageErectionMethodLessThanOrEqualTo(String value) {
            addCriterion("drainage_erection_method <=", value, "drainageErectionMethod");
            return (Criteria) this;
        }

        public Criteria andDrainageErectionMethodLike(String value) {
            addCriterion("drainage_erection_method like", value, "drainageErectionMethod");
            return (Criteria) this;
        }

        public Criteria andDrainageErectionMethodNotLike(String value) {
            addCriterion("drainage_erection_method not like", value, "drainageErectionMethod");
            return (Criteria) this;
        }

        public Criteria andDrainageErectionMethodIn(List<String> values) {
            addCriterion("drainage_erection_method in", values, "drainageErectionMethod");
            return (Criteria) this;
        }

        public Criteria andDrainageErectionMethodNotIn(List<String> values) {
            addCriterion("drainage_erection_method not in", values, "drainageErectionMethod");
            return (Criteria) this;
        }

        public Criteria andDrainageErectionMethodBetween(String value1, String value2) {
            addCriterion("drainage_erection_method between", value1, value2, "drainageErectionMethod");
            return (Criteria) this;
        }

        public Criteria andDrainageErectionMethodNotBetween(String value1, String value2) {
            addCriterion("drainage_erection_method not between", value1, value2, "drainageErectionMethod");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitIsNull() {
            addCriterion("drainage_circuit is null");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitIsNotNull() {
            addCriterion("drainage_circuit is not null");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitEqualTo(Integer value) {
            addCriterion("drainage_circuit =", value, "drainageCircuit");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitNotEqualTo(Integer value) {
            addCriterion("drainage_circuit <>", value, "drainageCircuit");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitGreaterThan(Integer value) {
            addCriterion("drainage_circuit >", value, "drainageCircuit");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitGreaterThanOrEqualTo(Integer value) {
            addCriterion("drainage_circuit >=", value, "drainageCircuit");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitLessThan(Integer value) {
            addCriterion("drainage_circuit <", value, "drainageCircuit");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitLessThanOrEqualTo(Integer value) {
            addCriterion("drainage_circuit <=", value, "drainageCircuit");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitIn(List<Integer> values) {
            addCriterion("drainage_circuit in", values, "drainageCircuit");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitNotIn(List<Integer> values) {
            addCriterion("drainage_circuit not in", values, "drainageCircuit");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitBetween(Integer value1, Integer value2) {
            addCriterion("drainage_circuit between", value1, value2, "drainageCircuit");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitNotBetween(Integer value1, Integer value2) {
            addCriterion("drainage_circuit not between", value1, value2, "drainageCircuit");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitCountIsNull() {
            addCriterion("drainage_circuit_count is null");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitCountIsNotNull() {
            addCriterion("drainage_circuit_count is not null");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitCountEqualTo(Integer value) {
            addCriterion("drainage_circuit_count =", value, "drainageCircuitCount");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitCountNotEqualTo(Integer value) {
            addCriterion("drainage_circuit_count <>", value, "drainageCircuitCount");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitCountGreaterThan(Integer value) {
            addCriterion("drainage_circuit_count >", value, "drainageCircuitCount");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("drainage_circuit_count >=", value, "drainageCircuitCount");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitCountLessThan(Integer value) {
            addCriterion("drainage_circuit_count <", value, "drainageCircuitCount");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitCountLessThanOrEqualTo(Integer value) {
            addCriterion("drainage_circuit_count <=", value, "drainageCircuitCount");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitCountIn(List<Integer> values) {
            addCriterion("drainage_circuit_count in", values, "drainageCircuitCount");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitCountNotIn(List<Integer> values) {
            addCriterion("drainage_circuit_count not in", values, "drainageCircuitCount");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitCountBetween(Integer value1, Integer value2) {
            addCriterion("drainage_circuit_count between", value1, value2, "drainageCircuitCount");
            return (Criteria) this;
        }

        public Criteria andDrainageCircuitCountNotBetween(Integer value1, Integer value2) {
            addCriterion("drainage_circuit_count not between", value1, value2, "drainageCircuitCount");
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