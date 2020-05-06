package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataAverageWageUrbanEmployeesItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataAverageWageUrbanEmployeesItemExample() {
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

        public Criteria andMasterIdIsNull() {
            addCriterion("master_id is null");
            return (Criteria) this;
        }

        public Criteria andMasterIdIsNotNull() {
            addCriterion("master_id is not null");
            return (Criteria) this;
        }

        public Criteria andMasterIdEqualTo(Integer value) {
            addCriterion("master_id =", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotEqualTo(Integer value) {
            addCriterion("master_id <>", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdGreaterThan(Integer value) {
            addCriterion("master_id >", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("master_id >=", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdLessThan(Integer value) {
            addCriterion("master_id <", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdLessThanOrEqualTo(Integer value) {
            addCriterion("master_id <=", value, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdIn(List<Integer> values) {
            addCriterion("master_id in", values, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotIn(List<Integer> values) {
            addCriterion("master_id not in", values, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdBetween(Integer value1, Integer value2) {
            addCriterion("master_id between", value1, value2, "masterId");
            return (Criteria) this;
        }

        public Criteria andMasterIdNotBetween(Integer value1, Integer value2) {
            addCriterion("master_id not between", value1, value2, "masterId");
            return (Criteria) this;
        }

        public Criteria andStateOwnedEconomyIsNull() {
            addCriterion("state_owned_economy is null");
            return (Criteria) this;
        }

        public Criteria andStateOwnedEconomyIsNotNull() {
            addCriterion("state_owned_economy is not null");
            return (Criteria) this;
        }

        public Criteria andStateOwnedEconomyEqualTo(BigDecimal value) {
            addCriterion("state_owned_economy =", value, "stateOwnedEconomy");
            return (Criteria) this;
        }

        public Criteria andStateOwnedEconomyNotEqualTo(BigDecimal value) {
            addCriterion("state_owned_economy <>", value, "stateOwnedEconomy");
            return (Criteria) this;
        }

        public Criteria andStateOwnedEconomyGreaterThan(BigDecimal value) {
            addCriterion("state_owned_economy >", value, "stateOwnedEconomy");
            return (Criteria) this;
        }

        public Criteria andStateOwnedEconomyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("state_owned_economy >=", value, "stateOwnedEconomy");
            return (Criteria) this;
        }

        public Criteria andStateOwnedEconomyLessThan(BigDecimal value) {
            addCriterion("state_owned_economy <", value, "stateOwnedEconomy");
            return (Criteria) this;
        }

        public Criteria andStateOwnedEconomyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("state_owned_economy <=", value, "stateOwnedEconomy");
            return (Criteria) this;
        }

        public Criteria andStateOwnedEconomyIn(List<BigDecimal> values) {
            addCriterion("state_owned_economy in", values, "stateOwnedEconomy");
            return (Criteria) this;
        }

        public Criteria andStateOwnedEconomyNotIn(List<BigDecimal> values) {
            addCriterion("state_owned_economy not in", values, "stateOwnedEconomy");
            return (Criteria) this;
        }

        public Criteria andStateOwnedEconomyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("state_owned_economy between", value1, value2, "stateOwnedEconomy");
            return (Criteria) this;
        }

        public Criteria andStateOwnedEconomyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("state_owned_economy not between", value1, value2, "stateOwnedEconomy");
            return (Criteria) this;
        }

        public Criteria andCollectiveEconomyIsNull() {
            addCriterion("collective_economy is null");
            return (Criteria) this;
        }

        public Criteria andCollectiveEconomyIsNotNull() {
            addCriterion("collective_economy is not null");
            return (Criteria) this;
        }

        public Criteria andCollectiveEconomyEqualTo(BigDecimal value) {
            addCriterion("collective_economy =", value, "collectiveEconomy");
            return (Criteria) this;
        }

        public Criteria andCollectiveEconomyNotEqualTo(BigDecimal value) {
            addCriterion("collective_economy <>", value, "collectiveEconomy");
            return (Criteria) this;
        }

        public Criteria andCollectiveEconomyGreaterThan(BigDecimal value) {
            addCriterion("collective_economy >", value, "collectiveEconomy");
            return (Criteria) this;
        }

        public Criteria andCollectiveEconomyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("collective_economy >=", value, "collectiveEconomy");
            return (Criteria) this;
        }

        public Criteria andCollectiveEconomyLessThan(BigDecimal value) {
            addCriterion("collective_economy <", value, "collectiveEconomy");
            return (Criteria) this;
        }

        public Criteria andCollectiveEconomyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("collective_economy <=", value, "collectiveEconomy");
            return (Criteria) this;
        }

        public Criteria andCollectiveEconomyIn(List<BigDecimal> values) {
            addCriterion("collective_economy in", values, "collectiveEconomy");
            return (Criteria) this;
        }

        public Criteria andCollectiveEconomyNotIn(List<BigDecimal> values) {
            addCriterion("collective_economy not in", values, "collectiveEconomy");
            return (Criteria) this;
        }

        public Criteria andCollectiveEconomyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("collective_economy between", value1, value2, "collectiveEconomy");
            return (Criteria) this;
        }

        public Criteria andCollectiveEconomyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("collective_economy not between", value1, value2, "collectiveEconomy");
            return (Criteria) this;
        }

        public Criteria andPrivateEconomyIsNull() {
            addCriterion("private_economy is null");
            return (Criteria) this;
        }

        public Criteria andPrivateEconomyIsNotNull() {
            addCriterion("private_economy is not null");
            return (Criteria) this;
        }

        public Criteria andPrivateEconomyEqualTo(BigDecimal value) {
            addCriterion("private_economy =", value, "privateEconomy");
            return (Criteria) this;
        }

        public Criteria andPrivateEconomyNotEqualTo(BigDecimal value) {
            addCriterion("private_economy <>", value, "privateEconomy");
            return (Criteria) this;
        }

        public Criteria andPrivateEconomyGreaterThan(BigDecimal value) {
            addCriterion("private_economy >", value, "privateEconomy");
            return (Criteria) this;
        }

        public Criteria andPrivateEconomyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("private_economy >=", value, "privateEconomy");
            return (Criteria) this;
        }

        public Criteria andPrivateEconomyLessThan(BigDecimal value) {
            addCriterion("private_economy <", value, "privateEconomy");
            return (Criteria) this;
        }

        public Criteria andPrivateEconomyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("private_economy <=", value, "privateEconomy");
            return (Criteria) this;
        }

        public Criteria andPrivateEconomyIn(List<BigDecimal> values) {
            addCriterion("private_economy in", values, "privateEconomy");
            return (Criteria) this;
        }

        public Criteria andPrivateEconomyNotIn(List<BigDecimal> values) {
            addCriterion("private_economy not in", values, "privateEconomy");
            return (Criteria) this;
        }

        public Criteria andPrivateEconomyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("private_economy between", value1, value2, "privateEconomy");
            return (Criteria) this;
        }

        public Criteria andPrivateEconomyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("private_economy not between", value1, value2, "privateEconomy");
            return (Criteria) this;
        }

        public Criteria andOtherEconomyIsNull() {
            addCriterion("other_economy is null");
            return (Criteria) this;
        }

        public Criteria andOtherEconomyIsNotNull() {
            addCriterion("other_economy is not null");
            return (Criteria) this;
        }

        public Criteria andOtherEconomyEqualTo(BigDecimal value) {
            addCriterion("other_economy =", value, "otherEconomy");
            return (Criteria) this;
        }

        public Criteria andOtherEconomyNotEqualTo(BigDecimal value) {
            addCriterion("other_economy <>", value, "otherEconomy");
            return (Criteria) this;
        }

        public Criteria andOtherEconomyGreaterThan(BigDecimal value) {
            addCriterion("other_economy >", value, "otherEconomy");
            return (Criteria) this;
        }

        public Criteria andOtherEconomyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("other_economy >=", value, "otherEconomy");
            return (Criteria) this;
        }

        public Criteria andOtherEconomyLessThan(BigDecimal value) {
            addCriterion("other_economy <", value, "otherEconomy");
            return (Criteria) this;
        }

        public Criteria andOtherEconomyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("other_economy <=", value, "otherEconomy");
            return (Criteria) this;
        }

        public Criteria andOtherEconomyIn(List<BigDecimal> values) {
            addCriterion("other_economy in", values, "otherEconomy");
            return (Criteria) this;
        }

        public Criteria andOtherEconomyNotIn(List<BigDecimal> values) {
            addCriterion("other_economy not in", values, "otherEconomy");
            return (Criteria) this;
        }

        public Criteria andOtherEconomyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_economy between", value1, value2, "otherEconomy");
            return (Criteria) this;
        }

        public Criteria andOtherEconomyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("other_economy not between", value1, value2, "otherEconomy");
            return (Criteria) this;
        }

        public Criteria andTotalIsNull() {
            addCriterion("total is null");
            return (Criteria) this;
        }

        public Criteria andTotalIsNotNull() {
            addCriterion("total is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEqualTo(BigDecimal value) {
            addCriterion("total =", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotEqualTo(BigDecimal value) {
            addCriterion("total <>", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThan(BigDecimal value) {
            addCriterion("total >", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total >=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThan(BigDecimal value) {
            addCriterion("total <", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total <=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalIn(List<BigDecimal> values) {
            addCriterion("total in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotIn(List<BigDecimal> values) {
            addCriterion("total not in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total not between", value1, value2, "total");
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

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
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