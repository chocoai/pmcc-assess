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

        public Criteria andSupplyModeIsNull() {
            addCriterion("supply_mode is null");
            return (Criteria) this;
        }

        public Criteria andSupplyModeIsNotNull() {
            addCriterion("supply_mode is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyModeEqualTo(Integer value) {
            addCriterion("supply_mode =", value, "supplyMode");
            return (Criteria) this;
        }

        public Criteria andSupplyModeNotEqualTo(Integer value) {
            addCriterion("supply_mode <>", value, "supplyMode");
            return (Criteria) this;
        }

        public Criteria andSupplyModeGreaterThan(Integer value) {
            addCriterion("supply_mode >", value, "supplyMode");
            return (Criteria) this;
        }

        public Criteria andSupplyModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("supply_mode >=", value, "supplyMode");
            return (Criteria) this;
        }

        public Criteria andSupplyModeLessThan(Integer value) {
            addCriterion("supply_mode <", value, "supplyMode");
            return (Criteria) this;
        }

        public Criteria andSupplyModeLessThanOrEqualTo(Integer value) {
            addCriterion("supply_mode <=", value, "supplyMode");
            return (Criteria) this;
        }

        public Criteria andSupplyModeIn(List<Integer> values) {
            addCriterion("supply_mode in", values, "supplyMode");
            return (Criteria) this;
        }

        public Criteria andSupplyModeNotIn(List<Integer> values) {
            addCriterion("supply_mode not in", values, "supplyMode");
            return (Criteria) this;
        }

        public Criteria andSupplyModeBetween(Integer value1, Integer value2) {
            addCriterion("supply_mode between", value1, value2, "supplyMode");
            return (Criteria) this;
        }

        public Criteria andSupplyModeNotBetween(Integer value1, Integer value2) {
            addCriterion("supply_mode not between", value1, value2, "supplyMode");
            return (Criteria) this;
        }

        public Criteria andPipingLayoutIsNull() {
            addCriterion("piping_layout is null");
            return (Criteria) this;
        }

        public Criteria andPipingLayoutIsNotNull() {
            addCriterion("piping_layout is not null");
            return (Criteria) this;
        }

        public Criteria andPipingLayoutEqualTo(Integer value) {
            addCriterion("piping_layout =", value, "pipingLayout");
            return (Criteria) this;
        }

        public Criteria andPipingLayoutNotEqualTo(Integer value) {
            addCriterion("piping_layout <>", value, "pipingLayout");
            return (Criteria) this;
        }

        public Criteria andPipingLayoutGreaterThan(Integer value) {
            addCriterion("piping_layout >", value, "pipingLayout");
            return (Criteria) this;
        }

        public Criteria andPipingLayoutGreaterThanOrEqualTo(Integer value) {
            addCriterion("piping_layout >=", value, "pipingLayout");
            return (Criteria) this;
        }

        public Criteria andPipingLayoutLessThan(Integer value) {
            addCriterion("piping_layout <", value, "pipingLayout");
            return (Criteria) this;
        }

        public Criteria andPipingLayoutLessThanOrEqualTo(Integer value) {
            addCriterion("piping_layout <=", value, "pipingLayout");
            return (Criteria) this;
        }

        public Criteria andPipingLayoutIn(List<Integer> values) {
            addCriterion("piping_layout in", values, "pipingLayout");
            return (Criteria) this;
        }

        public Criteria andPipingLayoutNotIn(List<Integer> values) {
            addCriterion("piping_layout not in", values, "pipingLayout");
            return (Criteria) this;
        }

        public Criteria andPipingLayoutBetween(Integer value1, Integer value2) {
            addCriterion("piping_layout between", value1, value2, "pipingLayout");
            return (Criteria) this;
        }

        public Criteria andPipingLayoutNotBetween(Integer value1, Integer value2) {
            addCriterion("piping_layout not between", value1, value2, "pipingLayout");
            return (Criteria) this;
        }

        public Criteria andPipeMaterialIsNull() {
            addCriterion("pipe_material is null");
            return (Criteria) this;
        }

        public Criteria andPipeMaterialIsNotNull() {
            addCriterion("pipe_material is not null");
            return (Criteria) this;
        }

        public Criteria andPipeMaterialEqualTo(Integer value) {
            addCriterion("pipe_material =", value, "pipeMaterial");
            return (Criteria) this;
        }

        public Criteria andPipeMaterialNotEqualTo(Integer value) {
            addCriterion("pipe_material <>", value, "pipeMaterial");
            return (Criteria) this;
        }

        public Criteria andPipeMaterialGreaterThan(Integer value) {
            addCriterion("pipe_material >", value, "pipeMaterial");
            return (Criteria) this;
        }

        public Criteria andPipeMaterialGreaterThanOrEqualTo(Integer value) {
            addCriterion("pipe_material >=", value, "pipeMaterial");
            return (Criteria) this;
        }

        public Criteria andPipeMaterialLessThan(Integer value) {
            addCriterion("pipe_material <", value, "pipeMaterial");
            return (Criteria) this;
        }

        public Criteria andPipeMaterialLessThanOrEqualTo(Integer value) {
            addCriterion("pipe_material <=", value, "pipeMaterial");
            return (Criteria) this;
        }

        public Criteria andPipeMaterialIn(List<Integer> values) {
            addCriterion("pipe_material in", values, "pipeMaterial");
            return (Criteria) this;
        }

        public Criteria andPipeMaterialNotIn(List<Integer> values) {
            addCriterion("pipe_material not in", values, "pipeMaterial");
            return (Criteria) this;
        }

        public Criteria andPipeMaterialBetween(Integer value1, Integer value2) {
            addCriterion("pipe_material between", value1, value2, "pipeMaterial");
            return (Criteria) this;
        }

        public Criteria andPipeMaterialNotBetween(Integer value1, Integer value2) {
            addCriterion("pipe_material not between", value1, value2, "pipeMaterial");
            return (Criteria) this;
        }

        public Criteria andBoosterEquipmentIsNull() {
            addCriterion("booster_equipment is null");
            return (Criteria) this;
        }

        public Criteria andBoosterEquipmentIsNotNull() {
            addCriterion("booster_equipment is not null");
            return (Criteria) this;
        }

        public Criteria andBoosterEquipmentEqualTo(Integer value) {
            addCriterion("booster_equipment =", value, "boosterEquipment");
            return (Criteria) this;
        }

        public Criteria andBoosterEquipmentNotEqualTo(Integer value) {
            addCriterion("booster_equipment <>", value, "boosterEquipment");
            return (Criteria) this;
        }

        public Criteria andBoosterEquipmentGreaterThan(Integer value) {
            addCriterion("booster_equipment >", value, "boosterEquipment");
            return (Criteria) this;
        }

        public Criteria andBoosterEquipmentGreaterThanOrEqualTo(Integer value) {
            addCriterion("booster_equipment >=", value, "boosterEquipment");
            return (Criteria) this;
        }

        public Criteria andBoosterEquipmentLessThan(Integer value) {
            addCriterion("booster_equipment <", value, "boosterEquipment");
            return (Criteria) this;
        }

        public Criteria andBoosterEquipmentLessThanOrEqualTo(Integer value) {
            addCriterion("booster_equipment <=", value, "boosterEquipment");
            return (Criteria) this;
        }

        public Criteria andBoosterEquipmentIn(List<Integer> values) {
            addCriterion("booster_equipment in", values, "boosterEquipment");
            return (Criteria) this;
        }

        public Criteria andBoosterEquipmentNotIn(List<Integer> values) {
            addCriterion("booster_equipment not in", values, "boosterEquipment");
            return (Criteria) this;
        }

        public Criteria andBoosterEquipmentBetween(Integer value1, Integer value2) {
            addCriterion("booster_equipment between", value1, value2, "boosterEquipment");
            return (Criteria) this;
        }

        public Criteria andBoosterEquipmentNotBetween(Integer value1, Integer value2) {
            addCriterion("booster_equipment not between", value1, value2, "boosterEquipment");
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

        public Criteria andPurificationEquipmentPriceIsNull() {
            addCriterion("purification_equipment_price is null");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceIsNotNull() {
            addCriterion("purification_equipment_price is not null");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceEqualTo(Integer value) {
            addCriterion("purification_equipment_price =", value, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceNotEqualTo(Integer value) {
            addCriterion("purification_equipment_price <>", value, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceGreaterThan(Integer value) {
            addCriterion("purification_equipment_price >", value, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("purification_equipment_price >=", value, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceLessThan(Integer value) {
            addCriterion("purification_equipment_price <", value, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceLessThanOrEqualTo(Integer value) {
            addCriterion("purification_equipment_price <=", value, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceIn(List<Integer> values) {
            addCriterion("purification_equipment_price in", values, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceNotIn(List<Integer> values) {
            addCriterion("purification_equipment_price not in", values, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceBetween(Integer value1, Integer value2) {
            addCriterion("purification_equipment_price between", value1, value2, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andPurificationEquipmentPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("purification_equipment_price not between", value1, value2, "purificationEquipmentPrice");
            return (Criteria) this;
        }

        public Criteria andFireWaterSupplyIsNull() {
            addCriterion("fire_water_supply is null");
            return (Criteria) this;
        }

        public Criteria andFireWaterSupplyIsNotNull() {
            addCriterion("fire_water_supply is not null");
            return (Criteria) this;
        }

        public Criteria andFireWaterSupplyEqualTo(Integer value) {
            addCriterion("fire_water_supply =", value, "fireWaterSupply");
            return (Criteria) this;
        }

        public Criteria andFireWaterSupplyNotEqualTo(Integer value) {
            addCriterion("fire_water_supply <>", value, "fireWaterSupply");
            return (Criteria) this;
        }

        public Criteria andFireWaterSupplyGreaterThan(Integer value) {
            addCriterion("fire_water_supply >", value, "fireWaterSupply");
            return (Criteria) this;
        }

        public Criteria andFireWaterSupplyGreaterThanOrEqualTo(Integer value) {
            addCriterion("fire_water_supply >=", value, "fireWaterSupply");
            return (Criteria) this;
        }

        public Criteria andFireWaterSupplyLessThan(Integer value) {
            addCriterion("fire_water_supply <", value, "fireWaterSupply");
            return (Criteria) this;
        }

        public Criteria andFireWaterSupplyLessThanOrEqualTo(Integer value) {
            addCriterion("fire_water_supply <=", value, "fireWaterSupply");
            return (Criteria) this;
        }

        public Criteria andFireWaterSupplyIn(List<Integer> values) {
            addCriterion("fire_water_supply in", values, "fireWaterSupply");
            return (Criteria) this;
        }

        public Criteria andFireWaterSupplyNotIn(List<Integer> values) {
            addCriterion("fire_water_supply not in", values, "fireWaterSupply");
            return (Criteria) this;
        }

        public Criteria andFireWaterSupplyBetween(Integer value1, Integer value2) {
            addCriterion("fire_water_supply between", value1, value2, "fireWaterSupply");
            return (Criteria) this;
        }

        public Criteria andFireWaterSupplyNotBetween(Integer value1, Integer value2) {
            addCriterion("fire_water_supply not between", value1, value2, "fireWaterSupply");
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