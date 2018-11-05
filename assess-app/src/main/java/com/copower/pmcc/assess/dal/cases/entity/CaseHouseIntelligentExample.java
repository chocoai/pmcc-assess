package com.copower.pmcc.assess.dal.cases.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaseHouseIntelligentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CaseHouseIntelligentExample() {
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

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
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

        public Criteria andWireErectionIsNull() {
            addCriterion("wire_erection is null");
            return (Criteria) this;
        }

        public Criteria andWireErectionIsNotNull() {
            addCriterion("wire_erection is not null");
            return (Criteria) this;
        }

        public Criteria andWireErectionEqualTo(Integer value) {
            addCriterion("wire_erection =", value, "wireErection");
            return (Criteria) this;
        }

        public Criteria andWireErectionNotEqualTo(Integer value) {
            addCriterion("wire_erection <>", value, "wireErection");
            return (Criteria) this;
        }

        public Criteria andWireErectionGreaterThan(Integer value) {
            addCriterion("wire_erection >", value, "wireErection");
            return (Criteria) this;
        }

        public Criteria andWireErectionGreaterThanOrEqualTo(Integer value) {
            addCriterion("wire_erection >=", value, "wireErection");
            return (Criteria) this;
        }

        public Criteria andWireErectionLessThan(Integer value) {
            addCriterion("wire_erection <", value, "wireErection");
            return (Criteria) this;
        }

        public Criteria andWireErectionLessThanOrEqualTo(Integer value) {
            addCriterion("wire_erection <=", value, "wireErection");
            return (Criteria) this;
        }

        public Criteria andWireErectionIn(List<Integer> values) {
            addCriterion("wire_erection in", values, "wireErection");
            return (Criteria) this;
        }

        public Criteria andWireErectionNotIn(List<Integer> values) {
            addCriterion("wire_erection not in", values, "wireErection");
            return (Criteria) this;
        }

        public Criteria andWireErectionBetween(Integer value1, Integer value2) {
            addCriterion("wire_erection between", value1, value2, "wireErection");
            return (Criteria) this;
        }

        public Criteria andWireErectionNotBetween(Integer value1, Integer value2) {
            addCriterion("wire_erection not between", value1, value2, "wireErection");
            return (Criteria) this;
        }

        public Criteria andSwitchCircuitIsNull() {
            addCriterion("switch_circuit is null");
            return (Criteria) this;
        }

        public Criteria andSwitchCircuitIsNotNull() {
            addCriterion("switch_circuit is not null");
            return (Criteria) this;
        }

        public Criteria andSwitchCircuitEqualTo(Integer value) {
            addCriterion("switch_circuit =", value, "switchCircuit");
            return (Criteria) this;
        }

        public Criteria andSwitchCircuitNotEqualTo(Integer value) {
            addCriterion("switch_circuit <>", value, "switchCircuit");
            return (Criteria) this;
        }

        public Criteria andSwitchCircuitGreaterThan(Integer value) {
            addCriterion("switch_circuit >", value, "switchCircuit");
            return (Criteria) this;
        }

        public Criteria andSwitchCircuitGreaterThanOrEqualTo(Integer value) {
            addCriterion("switch_circuit >=", value, "switchCircuit");
            return (Criteria) this;
        }

        public Criteria andSwitchCircuitLessThan(Integer value) {
            addCriterion("switch_circuit <", value, "switchCircuit");
            return (Criteria) this;
        }

        public Criteria andSwitchCircuitLessThanOrEqualTo(Integer value) {
            addCriterion("switch_circuit <=", value, "switchCircuit");
            return (Criteria) this;
        }

        public Criteria andSwitchCircuitIn(List<Integer> values) {
            addCriterion("switch_circuit in", values, "switchCircuit");
            return (Criteria) this;
        }

        public Criteria andSwitchCircuitNotIn(List<Integer> values) {
            addCriterion("switch_circuit not in", values, "switchCircuit");
            return (Criteria) this;
        }

        public Criteria andSwitchCircuitBetween(Integer value1, Integer value2) {
            addCriterion("switch_circuit between", value1, value2, "switchCircuit");
            return (Criteria) this;
        }

        public Criteria andSwitchCircuitNotBetween(Integer value1, Integer value2) {
            addCriterion("switch_circuit not between", value1, value2, "switchCircuit");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsIsNull() {
            addCriterion("lamps_lanterns is null");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsIsNotNull() {
            addCriterion("lamps_lanterns is not null");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsEqualTo(Integer value) {
            addCriterion("lamps_lanterns =", value, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsNotEqualTo(Integer value) {
            addCriterion("lamps_lanterns <>", value, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsGreaterThan(Integer value) {
            addCriterion("lamps_lanterns >", value, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsGreaterThanOrEqualTo(Integer value) {
            addCriterion("lamps_lanterns >=", value, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsLessThan(Integer value) {
            addCriterion("lamps_lanterns <", value, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsLessThanOrEqualTo(Integer value) {
            addCriterion("lamps_lanterns <=", value, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsIn(List<Integer> values) {
            addCriterion("lamps_lanterns in", values, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsNotIn(List<Integer> values) {
            addCriterion("lamps_lanterns not in", values, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsBetween(Integer value1, Integer value2) {
            addCriterion("lamps_lanterns between", value1, value2, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsNotBetween(Integer value1, Integer value2) {
            addCriterion("lamps_lanterns not between", value1, value2, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andInternalCommunicationIsNull() {
            addCriterion("internal_communication is null");
            return (Criteria) this;
        }

        public Criteria andInternalCommunicationIsNotNull() {
            addCriterion("internal_communication is not null");
            return (Criteria) this;
        }

        public Criteria andInternalCommunicationEqualTo(Integer value) {
            addCriterion("internal_communication =", value, "internalCommunication");
            return (Criteria) this;
        }

        public Criteria andInternalCommunicationNotEqualTo(Integer value) {
            addCriterion("internal_communication <>", value, "internalCommunication");
            return (Criteria) this;
        }

        public Criteria andInternalCommunicationGreaterThan(Integer value) {
            addCriterion("internal_communication >", value, "internalCommunication");
            return (Criteria) this;
        }

        public Criteria andInternalCommunicationGreaterThanOrEqualTo(Integer value) {
            addCriterion("internal_communication >=", value, "internalCommunication");
            return (Criteria) this;
        }

        public Criteria andInternalCommunicationLessThan(Integer value) {
            addCriterion("internal_communication <", value, "internalCommunication");
            return (Criteria) this;
        }

        public Criteria andInternalCommunicationLessThanOrEqualTo(Integer value) {
            addCriterion("internal_communication <=", value, "internalCommunication");
            return (Criteria) this;
        }

        public Criteria andInternalCommunicationIn(List<Integer> values) {
            addCriterion("internal_communication in", values, "internalCommunication");
            return (Criteria) this;
        }

        public Criteria andInternalCommunicationNotIn(List<Integer> values) {
            addCriterion("internal_communication not in", values, "internalCommunication");
            return (Criteria) this;
        }

        public Criteria andInternalCommunicationBetween(Integer value1, Integer value2) {
            addCriterion("internal_communication between", value1, value2, "internalCommunication");
            return (Criteria) this;
        }

        public Criteria andInternalCommunicationNotBetween(Integer value1, Integer value2) {
            addCriterion("internal_communication not between", value1, value2, "internalCommunication");
            return (Criteria) this;
        }

        public Criteria andMonitoringSystemIsNull() {
            addCriterion("monitoring_system is null");
            return (Criteria) this;
        }

        public Criteria andMonitoringSystemIsNotNull() {
            addCriterion("monitoring_system is not null");
            return (Criteria) this;
        }

        public Criteria andMonitoringSystemEqualTo(Integer value) {
            addCriterion("monitoring_system =", value, "monitoringSystem");
            return (Criteria) this;
        }

        public Criteria andMonitoringSystemNotEqualTo(Integer value) {
            addCriterion("monitoring_system <>", value, "monitoringSystem");
            return (Criteria) this;
        }

        public Criteria andMonitoringSystemGreaterThan(Integer value) {
            addCriterion("monitoring_system >", value, "monitoringSystem");
            return (Criteria) this;
        }

        public Criteria andMonitoringSystemGreaterThanOrEqualTo(Integer value) {
            addCriterion("monitoring_system >=", value, "monitoringSystem");
            return (Criteria) this;
        }

        public Criteria andMonitoringSystemLessThan(Integer value) {
            addCriterion("monitoring_system <", value, "monitoringSystem");
            return (Criteria) this;
        }

        public Criteria andMonitoringSystemLessThanOrEqualTo(Integer value) {
            addCriterion("monitoring_system <=", value, "monitoringSystem");
            return (Criteria) this;
        }

        public Criteria andMonitoringSystemIn(List<Integer> values) {
            addCriterion("monitoring_system in", values, "monitoringSystem");
            return (Criteria) this;
        }

        public Criteria andMonitoringSystemNotIn(List<Integer> values) {
            addCriterion("monitoring_system not in", values, "monitoringSystem");
            return (Criteria) this;
        }

        public Criteria andMonitoringSystemBetween(Integer value1, Integer value2) {
            addCriterion("monitoring_system between", value1, value2, "monitoringSystem");
            return (Criteria) this;
        }

        public Criteria andMonitoringSystemNotBetween(Integer value1, Integer value2) {
            addCriterion("monitoring_system not between", value1, value2, "monitoringSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemIsNull() {
            addCriterion("intelligent_system is null");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemIsNotNull() {
            addCriterion("intelligent_system is not null");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemEqualTo(Integer value) {
            addCriterion("intelligent_system =", value, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemNotEqualTo(Integer value) {
            addCriterion("intelligent_system <>", value, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemGreaterThan(Integer value) {
            addCriterion("intelligent_system >", value, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemGreaterThanOrEqualTo(Integer value) {
            addCriterion("intelligent_system >=", value, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemLessThan(Integer value) {
            addCriterion("intelligent_system <", value, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemLessThanOrEqualTo(Integer value) {
            addCriterion("intelligent_system <=", value, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemIn(List<Integer> values) {
            addCriterion("intelligent_system in", values, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemNotIn(List<Integer> values) {
            addCriterion("intelligent_system not in", values, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemBetween(Integer value1, Integer value2) {
            addCriterion("intelligent_system between", value1, value2, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemNotBetween(Integer value1, Integer value2) {
            addCriterion("intelligent_system not between", value1, value2, "intelligentSystem");
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