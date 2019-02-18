package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicUnitElevatorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicUnitElevatorExample() {
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

        public Criteria andMaintenanceIsNull() {
            addCriterion("maintenance is null");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIsNotNull() {
            addCriterion("maintenance is not null");
            return (Criteria) this;
        }

        public Criteria andMaintenanceEqualTo(Integer value) {
            addCriterion("maintenance =", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceNotEqualTo(Integer value) {
            addCriterion("maintenance <>", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceGreaterThan(Integer value) {
            addCriterion("maintenance >", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("maintenance >=", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceLessThan(Integer value) {
            addCriterion("maintenance <", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceLessThanOrEqualTo(Integer value) {
            addCriterion("maintenance <=", value, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIn(List<Integer> values) {
            addCriterion("maintenance in", values, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceNotIn(List<Integer> values) {
            addCriterion("maintenance not in", values, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceBetween(Integer value1, Integer value2) {
            addCriterion("maintenance between", value1, value2, "maintenance");
            return (Criteria) this;
        }

        public Criteria andMaintenanceNotBetween(Integer value1, Integer value2) {
            addCriterion("maintenance not between", value1, value2, "maintenance");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andBrandIsNull() {
            addCriterion("brand is null");
            return (Criteria) this;
        }

        public Criteria andBrandIsNotNull() {
            addCriterion("brand is not null");
            return (Criteria) this;
        }

        public Criteria andBrandEqualTo(String value) {
            addCriterion("brand =", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotEqualTo(String value) {
            addCriterion("brand <>", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThan(String value) {
            addCriterion("brand >", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThanOrEqualTo(String value) {
            addCriterion("brand >=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThan(String value) {
            addCriterion("brand <", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThanOrEqualTo(String value) {
            addCriterion("brand <=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLike(String value) {
            addCriterion("brand like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotLike(String value) {
            addCriterion("brand not like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandIn(List<String> values) {
            addCriterion("brand in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotIn(List<String> values) {
            addCriterion("brand not in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandBetween(String value1, String value2) {
            addCriterion("brand between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotBetween(String value1, String value2) {
            addCriterion("brand not between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Integer value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Integer value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Integer value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Integer value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Integer value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Integer> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Integer> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Integer value1, Integer value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadNumberIsNull() {
            addCriterion("quasi_load_number is null");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadNumberIsNotNull() {
            addCriterion("quasi_load_number is not null");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadNumberEqualTo(Integer value) {
            addCriterion("quasi_load_number =", value, "quasiLoadNumber");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadNumberNotEqualTo(Integer value) {
            addCriterion("quasi_load_number <>", value, "quasiLoadNumber");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadNumberGreaterThan(Integer value) {
            addCriterion("quasi_load_number >", value, "quasiLoadNumber");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("quasi_load_number >=", value, "quasiLoadNumber");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadNumberLessThan(Integer value) {
            addCriterion("quasi_load_number <", value, "quasiLoadNumber");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadNumberLessThanOrEqualTo(Integer value) {
            addCriterion("quasi_load_number <=", value, "quasiLoadNumber");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadNumberIn(List<Integer> values) {
            addCriterion("quasi_load_number in", values, "quasiLoadNumber");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadNumberNotIn(List<Integer> values) {
            addCriterion("quasi_load_number not in", values, "quasiLoadNumber");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadNumberBetween(Integer value1, Integer value2) {
            addCriterion("quasi_load_number between", value1, value2, "quasiLoadNumber");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("quasi_load_number not between", value1, value2, "quasiLoadNumber");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadWeightIsNull() {
            addCriterion("quasi_load_weight is null");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadWeightIsNotNull() {
            addCriterion("quasi_load_weight is not null");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadWeightEqualTo(String value) {
            addCriterion("quasi_load_weight =", value, "quasiLoadWeight");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadWeightNotEqualTo(String value) {
            addCriterion("quasi_load_weight <>", value, "quasiLoadWeight");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadWeightGreaterThan(String value) {
            addCriterion("quasi_load_weight >", value, "quasiLoadWeight");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadWeightGreaterThanOrEqualTo(String value) {
            addCriterion("quasi_load_weight >=", value, "quasiLoadWeight");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadWeightLessThan(String value) {
            addCriterion("quasi_load_weight <", value, "quasiLoadWeight");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadWeightLessThanOrEqualTo(String value) {
            addCriterion("quasi_load_weight <=", value, "quasiLoadWeight");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadWeightLike(String value) {
            addCriterion("quasi_load_weight like", value, "quasiLoadWeight");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadWeightNotLike(String value) {
            addCriterion("quasi_load_weight not like", value, "quasiLoadWeight");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadWeightIn(List<String> values) {
            addCriterion("quasi_load_weight in", values, "quasiLoadWeight");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadWeightNotIn(List<String> values) {
            addCriterion("quasi_load_weight not in", values, "quasiLoadWeight");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadWeightBetween(String value1, String value2) {
            addCriterion("quasi_load_weight between", value1, value2, "quasiLoadWeight");
            return (Criteria) this;
        }

        public Criteria andQuasiLoadWeightNotBetween(String value1, String value2) {
            addCriterion("quasi_load_weight not between", value1, value2, "quasiLoadWeight");
            return (Criteria) this;
        }

        public Criteria andRunningSpeedIsNull() {
            addCriterion("running_speed is null");
            return (Criteria) this;
        }

        public Criteria andRunningSpeedIsNotNull() {
            addCriterion("running_speed is not null");
            return (Criteria) this;
        }

        public Criteria andRunningSpeedEqualTo(String value) {
            addCriterion("running_speed =", value, "runningSpeed");
            return (Criteria) this;
        }

        public Criteria andRunningSpeedNotEqualTo(String value) {
            addCriterion("running_speed <>", value, "runningSpeed");
            return (Criteria) this;
        }

        public Criteria andRunningSpeedGreaterThan(String value) {
            addCriterion("running_speed >", value, "runningSpeed");
            return (Criteria) this;
        }

        public Criteria andRunningSpeedGreaterThanOrEqualTo(String value) {
            addCriterion("running_speed >=", value, "runningSpeed");
            return (Criteria) this;
        }

        public Criteria andRunningSpeedLessThan(String value) {
            addCriterion("running_speed <", value, "runningSpeed");
            return (Criteria) this;
        }

        public Criteria andRunningSpeedLessThanOrEqualTo(String value) {
            addCriterion("running_speed <=", value, "runningSpeed");
            return (Criteria) this;
        }

        public Criteria andRunningSpeedLike(String value) {
            addCriterion("running_speed like", value, "runningSpeed");
            return (Criteria) this;
        }

        public Criteria andRunningSpeedNotLike(String value) {
            addCriterion("running_speed not like", value, "runningSpeed");
            return (Criteria) this;
        }

        public Criteria andRunningSpeedIn(List<String> values) {
            addCriterion("running_speed in", values, "runningSpeed");
            return (Criteria) this;
        }

        public Criteria andRunningSpeedNotIn(List<String> values) {
            addCriterion("running_speed not in", values, "runningSpeed");
            return (Criteria) this;
        }

        public Criteria andRunningSpeedBetween(String value1, String value2) {
            addCriterion("running_speed between", value1, value2, "runningSpeed");
            return (Criteria) this;
        }

        public Criteria andRunningSpeedNotBetween(String value1, String value2) {
            addCriterion("running_speed not between", value1, value2, "runningSpeed");
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