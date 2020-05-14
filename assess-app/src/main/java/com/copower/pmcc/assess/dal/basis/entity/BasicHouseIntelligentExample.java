package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicHouseIntelligentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicHouseIntelligentExample() {
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

        public Criteria andLayingMethodIsNull() {
            addCriterion("laying_method is null");
            return (Criteria) this;
        }

        public Criteria andLayingMethodIsNotNull() {
            addCriterion("laying_method is not null");
            return (Criteria) this;
        }

        public Criteria andLayingMethodEqualTo(Integer value) {
            addCriterion("laying_method =", value, "layingMethod");
            return (Criteria) this;
        }

        public Criteria andLayingMethodNotEqualTo(Integer value) {
            addCriterion("laying_method <>", value, "layingMethod");
            return (Criteria) this;
        }

        public Criteria andLayingMethodGreaterThan(Integer value) {
            addCriterion("laying_method >", value, "layingMethod");
            return (Criteria) this;
        }

        public Criteria andLayingMethodGreaterThanOrEqualTo(Integer value) {
            addCriterion("laying_method >=", value, "layingMethod");
            return (Criteria) this;
        }

        public Criteria andLayingMethodLessThan(Integer value) {
            addCriterion("laying_method <", value, "layingMethod");
            return (Criteria) this;
        }

        public Criteria andLayingMethodLessThanOrEqualTo(Integer value) {
            addCriterion("laying_method <=", value, "layingMethod");
            return (Criteria) this;
        }

        public Criteria andLayingMethodIn(List<Integer> values) {
            addCriterion("laying_method in", values, "layingMethod");
            return (Criteria) this;
        }

        public Criteria andLayingMethodNotIn(List<Integer> values) {
            addCriterion("laying_method not in", values, "layingMethod");
            return (Criteria) this;
        }

        public Criteria andLayingMethodBetween(Integer value1, Integer value2) {
            addCriterion("laying_method between", value1, value2, "layingMethod");
            return (Criteria) this;
        }

        public Criteria andLayingMethodNotBetween(Integer value1, Integer value2) {
            addCriterion("laying_method not between", value1, value2, "layingMethod");
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

        public Criteria andLampsLanternsEqualTo(String value) {
            addCriterion("lamps_lanterns =", value, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsNotEqualTo(String value) {
            addCriterion("lamps_lanterns <>", value, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsGreaterThan(String value) {
            addCriterion("lamps_lanterns >", value, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsGreaterThanOrEqualTo(String value) {
            addCriterion("lamps_lanterns >=", value, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsLessThan(String value) {
            addCriterion("lamps_lanterns <", value, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsLessThanOrEqualTo(String value) {
            addCriterion("lamps_lanterns <=", value, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsLike(String value) {
            addCriterion("lamps_lanterns like", value, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsNotLike(String value) {
            addCriterion("lamps_lanterns not like", value, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsIn(List<String> values) {
            addCriterion("lamps_lanterns in", values, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsNotIn(List<String> values) {
            addCriterion("lamps_lanterns not in", values, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsBetween(String value1, String value2) {
            addCriterion("lamps_lanterns between", value1, value2, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andLampsLanternsNotBetween(String value1, String value2) {
            addCriterion("lamps_lanterns not between", value1, value2, "lampsLanterns");
            return (Criteria) this;
        }

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(Integer value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(Integer value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(Integer value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(Integer value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(Integer value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<Integer> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<Integer> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(Integer value1, Integer value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("grade not between", value1, value2, "grade");
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

        public Criteria andIntelligentSystemEqualTo(String value) {
            addCriterion("intelligent_system =", value, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemNotEqualTo(String value) {
            addCriterion("intelligent_system <>", value, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemGreaterThan(String value) {
            addCriterion("intelligent_system >", value, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemGreaterThanOrEqualTo(String value) {
            addCriterion("intelligent_system >=", value, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemLessThan(String value) {
            addCriterion("intelligent_system <", value, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemLessThanOrEqualTo(String value) {
            addCriterion("intelligent_system <=", value, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemLike(String value) {
            addCriterion("intelligent_system like", value, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemNotLike(String value) {
            addCriterion("intelligent_system not like", value, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemIn(List<String> values) {
            addCriterion("intelligent_system in", values, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemNotIn(List<String> values) {
            addCriterion("intelligent_system not in", values, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemBetween(String value1, String value2) {
            addCriterion("intelligent_system between", value1, value2, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andIntelligentSystemNotBetween(String value1, String value2) {
            addCriterion("intelligent_system not between", value1, value2, "intelligentSystem");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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

        public Criteria andSystemDescribeIsNull() {
            addCriterion("system_describe is null");
            return (Criteria) this;
        }

        public Criteria andSystemDescribeIsNotNull() {
            addCriterion("system_describe is not null");
            return (Criteria) this;
        }

        public Criteria andSystemDescribeEqualTo(String value) {
            addCriterion("system_describe =", value, "systemDescribe");
            return (Criteria) this;
        }

        public Criteria andSystemDescribeNotEqualTo(String value) {
            addCriterion("system_describe <>", value, "systemDescribe");
            return (Criteria) this;
        }

        public Criteria andSystemDescribeGreaterThan(String value) {
            addCriterion("system_describe >", value, "systemDescribe");
            return (Criteria) this;
        }

        public Criteria andSystemDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("system_describe >=", value, "systemDescribe");
            return (Criteria) this;
        }

        public Criteria andSystemDescribeLessThan(String value) {
            addCriterion("system_describe <", value, "systemDescribe");
            return (Criteria) this;
        }

        public Criteria andSystemDescribeLessThanOrEqualTo(String value) {
            addCriterion("system_describe <=", value, "systemDescribe");
            return (Criteria) this;
        }

        public Criteria andSystemDescribeLike(String value) {
            addCriterion("system_describe like", value, "systemDescribe");
            return (Criteria) this;
        }

        public Criteria andSystemDescribeNotLike(String value) {
            addCriterion("system_describe not like", value, "systemDescribe");
            return (Criteria) this;
        }

        public Criteria andSystemDescribeIn(List<String> values) {
            addCriterion("system_describe in", values, "systemDescribe");
            return (Criteria) this;
        }

        public Criteria andSystemDescribeNotIn(List<String> values) {
            addCriterion("system_describe not in", values, "systemDescribe");
            return (Criteria) this;
        }

        public Criteria andSystemDescribeBetween(String value1, String value2) {
            addCriterion("system_describe between", value1, value2, "systemDescribe");
            return (Criteria) this;
        }

        public Criteria andSystemDescribeNotBetween(String value1, String value2) {
            addCriterion("system_describe not between", value1, value2, "systemDescribe");
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