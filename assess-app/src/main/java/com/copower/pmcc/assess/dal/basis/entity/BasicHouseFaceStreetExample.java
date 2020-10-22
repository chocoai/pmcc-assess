package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicHouseFaceStreetExample {
    /**
     * tb_basic_house_face_street
     */
    protected String orderByClause;

    /**
     * tb_basic_house_face_street
     */
    protected boolean distinct;

    /**
     * tb_basic_house_face_street
     */
    protected List<Criteria> oredCriteria;

    public BasicHouseFaceStreetExample() {
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

    /**
     * tb_basic_house_face_street
     */
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

        public Criteria andStreetNameIsNull() {
            addCriterion("street_name is null");
            return (Criteria) this;
        }

        public Criteria andStreetNameIsNotNull() {
            addCriterion("street_name is not null");
            return (Criteria) this;
        }

        public Criteria andStreetNameEqualTo(String value) {
            addCriterion("street_name =", value, "streetName");
            return (Criteria) this;
        }

        public Criteria andStreetNameNotEqualTo(String value) {
            addCriterion("street_name <>", value, "streetName");
            return (Criteria) this;
        }

        public Criteria andStreetNameGreaterThan(String value) {
            addCriterion("street_name >", value, "streetName");
            return (Criteria) this;
        }

        public Criteria andStreetNameGreaterThanOrEqualTo(String value) {
            addCriterion("street_name >=", value, "streetName");
            return (Criteria) this;
        }

        public Criteria andStreetNameLessThan(String value) {
            addCriterion("street_name <", value, "streetName");
            return (Criteria) this;
        }

        public Criteria andStreetNameLessThanOrEqualTo(String value) {
            addCriterion("street_name <=", value, "streetName");
            return (Criteria) this;
        }

        public Criteria andStreetNameLike(String value) {
            addCriterion("street_name like", value, "streetName");
            return (Criteria) this;
        }

        public Criteria andStreetNameNotLike(String value) {
            addCriterion("street_name not like", value, "streetName");
            return (Criteria) this;
        }

        public Criteria andStreetNameIn(List<String> values) {
            addCriterion("street_name in", values, "streetName");
            return (Criteria) this;
        }

        public Criteria andStreetNameNotIn(List<String> values) {
            addCriterion("street_name not in", values, "streetName");
            return (Criteria) this;
        }

        public Criteria andStreetNameBetween(String value1, String value2) {
            addCriterion("street_name between", value1, value2, "streetName");
            return (Criteria) this;
        }

        public Criteria andStreetNameNotBetween(String value1, String value2) {
            addCriterion("street_name not between", value1, value2, "streetName");
            return (Criteria) this;
        }

        public Criteria andStreetLevelIsNull() {
            addCriterion("street_level is null");
            return (Criteria) this;
        }

        public Criteria andStreetLevelIsNotNull() {
            addCriterion("street_level is not null");
            return (Criteria) this;
        }

        public Criteria andStreetLevelEqualTo(Integer value) {
            addCriterion("street_level =", value, "streetLevel");
            return (Criteria) this;
        }

        public Criteria andStreetLevelNotEqualTo(Integer value) {
            addCriterion("street_level <>", value, "streetLevel");
            return (Criteria) this;
        }

        public Criteria andStreetLevelGreaterThan(Integer value) {
            addCriterion("street_level >", value, "streetLevel");
            return (Criteria) this;
        }

        public Criteria andStreetLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("street_level >=", value, "streetLevel");
            return (Criteria) this;
        }

        public Criteria andStreetLevelLessThan(Integer value) {
            addCriterion("street_level <", value, "streetLevel");
            return (Criteria) this;
        }

        public Criteria andStreetLevelLessThanOrEqualTo(Integer value) {
            addCriterion("street_level <=", value, "streetLevel");
            return (Criteria) this;
        }

        public Criteria andStreetLevelIn(List<Integer> values) {
            addCriterion("street_level in", values, "streetLevel");
            return (Criteria) this;
        }

        public Criteria andStreetLevelNotIn(List<Integer> values) {
            addCriterion("street_level not in", values, "streetLevel");
            return (Criteria) this;
        }

        public Criteria andStreetLevelBetween(Integer value1, Integer value2) {
            addCriterion("street_level between", value1, value2, "streetLevel");
            return (Criteria) this;
        }

        public Criteria andStreetLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("street_level not between", value1, value2, "streetLevel");
            return (Criteria) this;
        }

        public Criteria andDistanceIsNull() {
            addCriterion("distance is null");
            return (Criteria) this;
        }

        public Criteria andDistanceIsNotNull() {
            addCriterion("distance is not null");
            return (Criteria) this;
        }

        public Criteria andDistanceEqualTo(String value) {
            addCriterion("distance =", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotEqualTo(String value) {
            addCriterion("distance <>", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceGreaterThan(String value) {
            addCriterion("distance >", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceGreaterThanOrEqualTo(String value) {
            addCriterion("distance >=", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLessThan(String value) {
            addCriterion("distance <", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLessThanOrEqualTo(String value) {
            addCriterion("distance <=", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLike(String value) {
            addCriterion("distance like", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotLike(String value) {
            addCriterion("distance not like", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceIn(List<String> values) {
            addCriterion("distance in", values, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotIn(List<String> values) {
            addCriterion("distance not in", values, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceBetween(String value1, String value2) {
            addCriterion("distance between", value1, value2, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotBetween(String value1, String value2) {
            addCriterion("distance not between", value1, value2, "distance");
            return (Criteria) this;
        }

        public Criteria andTrafficFlowIsNull() {
            addCriterion("traffic_flow is null");
            return (Criteria) this;
        }

        public Criteria andTrafficFlowIsNotNull() {
            addCriterion("traffic_flow is not null");
            return (Criteria) this;
        }

        public Criteria andTrafficFlowEqualTo(Integer value) {
            addCriterion("traffic_flow =", value, "trafficFlow");
            return (Criteria) this;
        }

        public Criteria andTrafficFlowNotEqualTo(Integer value) {
            addCriterion("traffic_flow <>", value, "trafficFlow");
            return (Criteria) this;
        }

        public Criteria andTrafficFlowGreaterThan(Integer value) {
            addCriterion("traffic_flow >", value, "trafficFlow");
            return (Criteria) this;
        }

        public Criteria andTrafficFlowGreaterThanOrEqualTo(Integer value) {
            addCriterion("traffic_flow >=", value, "trafficFlow");
            return (Criteria) this;
        }

        public Criteria andTrafficFlowLessThan(Integer value) {
            addCriterion("traffic_flow <", value, "trafficFlow");
            return (Criteria) this;
        }

        public Criteria andTrafficFlowLessThanOrEqualTo(Integer value) {
            addCriterion("traffic_flow <=", value, "trafficFlow");
            return (Criteria) this;
        }

        public Criteria andTrafficFlowIn(List<Integer> values) {
            addCriterion("traffic_flow in", values, "trafficFlow");
            return (Criteria) this;
        }

        public Criteria andTrafficFlowNotIn(List<Integer> values) {
            addCriterion("traffic_flow not in", values, "trafficFlow");
            return (Criteria) this;
        }

        public Criteria andTrafficFlowBetween(Integer value1, Integer value2) {
            addCriterion("traffic_flow between", value1, value2, "trafficFlow");
            return (Criteria) this;
        }

        public Criteria andTrafficFlowNotBetween(Integer value1, Integer value2) {
            addCriterion("traffic_flow not between", value1, value2, "trafficFlow");
            return (Criteria) this;
        }

        public Criteria andVisitorsFlowrateIsNull() {
            addCriterion("visitors_flowrate is null");
            return (Criteria) this;
        }

        public Criteria andVisitorsFlowrateIsNotNull() {
            addCriterion("visitors_flowrate is not null");
            return (Criteria) this;
        }

        public Criteria andVisitorsFlowrateEqualTo(Integer value) {
            addCriterion("visitors_flowrate =", value, "visitorsFlowrate");
            return (Criteria) this;
        }

        public Criteria andVisitorsFlowrateNotEqualTo(Integer value) {
            addCriterion("visitors_flowrate <>", value, "visitorsFlowrate");
            return (Criteria) this;
        }

        public Criteria andVisitorsFlowrateGreaterThan(Integer value) {
            addCriterion("visitors_flowrate >", value, "visitorsFlowrate");
            return (Criteria) this;
        }

        public Criteria andVisitorsFlowrateGreaterThanOrEqualTo(Integer value) {
            addCriterion("visitors_flowrate >=", value, "visitorsFlowrate");
            return (Criteria) this;
        }

        public Criteria andVisitorsFlowrateLessThan(Integer value) {
            addCriterion("visitors_flowrate <", value, "visitorsFlowrate");
            return (Criteria) this;
        }

        public Criteria andVisitorsFlowrateLessThanOrEqualTo(Integer value) {
            addCriterion("visitors_flowrate <=", value, "visitorsFlowrate");
            return (Criteria) this;
        }

        public Criteria andVisitorsFlowrateIn(List<Integer> values) {
            addCriterion("visitors_flowrate in", values, "visitorsFlowrate");
            return (Criteria) this;
        }

        public Criteria andVisitorsFlowrateNotIn(List<Integer> values) {
            addCriterion("visitors_flowrate not in", values, "visitorsFlowrate");
            return (Criteria) this;
        }

        public Criteria andVisitorsFlowrateBetween(Integer value1, Integer value2) {
            addCriterion("visitors_flowrate between", value1, value2, "visitorsFlowrate");
            return (Criteria) this;
        }

        public Criteria andVisitorsFlowrateNotBetween(Integer value1, Integer value2) {
            addCriterion("visitors_flowrate not between", value1, value2, "visitorsFlowrate");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("position is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("position is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(Integer value) {
            addCriterion("position =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(Integer value) {
            addCriterion("position <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(Integer value) {
            addCriterion("position >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(Integer value) {
            addCriterion("position >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(Integer value) {
            addCriterion("position <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(Integer value) {
            addCriterion("position <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<Integer> values) {
            addCriterion("position in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<Integer> values) {
            addCriterion("position not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(Integer value1, Integer value2) {
            addCriterion("position between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(Integer value1, Integer value2) {
            addCriterion("position not between", value1, value2, "position");
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

    /**
     * tb_basic_house_face_street
     */
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