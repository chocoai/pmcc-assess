package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamineEstateLandStateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamineEstateLandStateExample() {
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

        public Criteria andDeclareIdIsNull() {
            addCriterion("declare_id is null");
            return (Criteria) this;
        }

        public Criteria andDeclareIdIsNotNull() {
            addCriterion("declare_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeclareIdEqualTo(Integer value) {
            addCriterion("declare_id =", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdNotEqualTo(Integer value) {
            addCriterion("declare_id <>", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdGreaterThan(Integer value) {
            addCriterion("declare_id >", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("declare_id >=", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdLessThan(Integer value) {
            addCriterion("declare_id <", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdLessThanOrEqualTo(Integer value) {
            addCriterion("declare_id <=", value, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdIn(List<Integer> values) {
            addCriterion("declare_id in", values, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdNotIn(List<Integer> values) {
            addCriterion("declare_id not in", values, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdBetween(Integer value1, Integer value2) {
            addCriterion("declare_id between", value1, value2, "declareId");
            return (Criteria) this;
        }

        public Criteria andDeclareIdNotBetween(Integer value1, Integer value2) {
            addCriterion("declare_id not between", value1, value2, "declareId");
            return (Criteria) this;
        }

        public Criteria andExamineTypeIsNull() {
            addCriterion("examine_type is null");
            return (Criteria) this;
        }

        public Criteria andExamineTypeIsNotNull() {
            addCriterion("examine_type is not null");
            return (Criteria) this;
        }

        public Criteria andExamineTypeEqualTo(Integer value) {
            addCriterion("examine_type =", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeNotEqualTo(Integer value) {
            addCriterion("examine_type <>", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeGreaterThan(Integer value) {
            addCriterion("examine_type >", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("examine_type >=", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeLessThan(Integer value) {
            addCriterion("examine_type <", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeLessThanOrEqualTo(Integer value) {
            addCriterion("examine_type <=", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeIn(List<Integer> values) {
            addCriterion("examine_type in", values, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeNotIn(List<Integer> values) {
            addCriterion("examine_type not in", values, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeBetween(Integer value1, Integer value2) {
            addCriterion("examine_type between", value1, value2, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("examine_type not between", value1, value2, "examineType");
            return (Criteria) this;
        }

        public Criteria andEstateIdIsNull() {
            addCriterion("estate_id is null");
            return (Criteria) this;
        }

        public Criteria andEstateIdIsNotNull() {
            addCriterion("estate_id is not null");
            return (Criteria) this;
        }

        public Criteria andEstateIdEqualTo(Integer value) {
            addCriterion("estate_id =", value, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdNotEqualTo(Integer value) {
            addCriterion("estate_id <>", value, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdGreaterThan(Integer value) {
            addCriterion("estate_id >", value, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("estate_id >=", value, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdLessThan(Integer value) {
            addCriterion("estate_id <", value, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdLessThanOrEqualTo(Integer value) {
            addCriterion("estate_id <=", value, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdIn(List<Integer> values) {
            addCriterion("estate_id in", values, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdNotIn(List<Integer> values) {
            addCriterion("estate_id not in", values, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdBetween(Integer value1, Integer value2) {
            addCriterion("estate_id between", value1, value2, "estateId");
            return (Criteria) this;
        }

        public Criteria andEstateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("estate_id not between", value1, value2, "estateId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andLandUseIsNull() {
            addCriterion("land_use is null");
            return (Criteria) this;
        }

        public Criteria andLandUseIsNotNull() {
            addCriterion("land_use is not null");
            return (Criteria) this;
        }

        public Criteria andLandUseEqualTo(Integer value) {
            addCriterion("land_use =", value, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseNotEqualTo(Integer value) {
            addCriterion("land_use <>", value, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseGreaterThan(Integer value) {
            addCriterion("land_use >", value, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("land_use >=", value, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseLessThan(Integer value) {
            addCriterion("land_use <", value, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseLessThanOrEqualTo(Integer value) {
            addCriterion("land_use <=", value, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseIn(List<Integer> values) {
            addCriterion("land_use in", values, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseNotIn(List<Integer> values) {
            addCriterion("land_use not in", values, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseBetween(Integer value1, Integer value2) {
            addCriterion("land_use between", value1, value2, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseNotBetween(Integer value1, Integer value2) {
            addCriterion("land_use not between", value1, value2, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandLevelIsNull() {
            addCriterion("land_level is null");
            return (Criteria) this;
        }

        public Criteria andLandLevelIsNotNull() {
            addCriterion("land_level is not null");
            return (Criteria) this;
        }

        public Criteria andLandLevelEqualTo(Integer value) {
            addCriterion("land_level =", value, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandLevelNotEqualTo(Integer value) {
            addCriterion("land_level <>", value, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandLevelGreaterThan(Integer value) {
            addCriterion("land_level >", value, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("land_level >=", value, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandLevelLessThan(Integer value) {
            addCriterion("land_level <", value, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandLevelLessThanOrEqualTo(Integer value) {
            addCriterion("land_level <=", value, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandLevelIn(List<Integer> values) {
            addCriterion("land_level in", values, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandLevelNotIn(List<Integer> values) {
            addCriterion("land_level not in", values, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandLevelBetween(Integer value1, Integer value2) {
            addCriterion("land_level between", value1, value2, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("land_level not between", value1, value2, "landLevel");
            return (Criteria) this;
        }

        public Criteria andLandAreaIsNull() {
            addCriterion("land_area is null");
            return (Criteria) this;
        }

        public Criteria andLandAreaIsNotNull() {
            addCriterion("land_area is not null");
            return (Criteria) this;
        }

        public Criteria andLandAreaEqualTo(String value) {
            addCriterion("land_area =", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotEqualTo(String value) {
            addCriterion("land_area <>", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaGreaterThan(String value) {
            addCriterion("land_area >", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaGreaterThanOrEqualTo(String value) {
            addCriterion("land_area >=", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaLessThan(String value) {
            addCriterion("land_area <", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaLessThanOrEqualTo(String value) {
            addCriterion("land_area <=", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaLike(String value) {
            addCriterion("land_area like", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotLike(String value) {
            addCriterion("land_area not like", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaIn(List<String> values) {
            addCriterion("land_area in", values, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotIn(List<String> values) {
            addCriterion("land_area not in", values, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaBetween(String value1, String value2) {
            addCriterion("land_area between", value1, value2, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotBetween(String value1, String value2) {
            addCriterion("land_area not between", value1, value2, "landArea");
            return (Criteria) this;
        }

        public Criteria andEastToIsNull() {
            addCriterion("east_to is null");
            return (Criteria) this;
        }

        public Criteria andEastToIsNotNull() {
            addCriterion("east_to is not null");
            return (Criteria) this;
        }

        public Criteria andEastToEqualTo(String value) {
            addCriterion("east_to =", value, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToNotEqualTo(String value) {
            addCriterion("east_to <>", value, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToGreaterThan(String value) {
            addCriterion("east_to >", value, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToGreaterThanOrEqualTo(String value) {
            addCriterion("east_to >=", value, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToLessThan(String value) {
            addCriterion("east_to <", value, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToLessThanOrEqualTo(String value) {
            addCriterion("east_to <=", value, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToLike(String value) {
            addCriterion("east_to like", value, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToNotLike(String value) {
            addCriterion("east_to not like", value, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToIn(List<String> values) {
            addCriterion("east_to in", values, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToNotIn(List<String> values) {
            addCriterion("east_to not in", values, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToBetween(String value1, String value2) {
            addCriterion("east_to between", value1, value2, "eastTo");
            return (Criteria) this;
        }

        public Criteria andEastToNotBetween(String value1, String value2) {
            addCriterion("east_to not between", value1, value2, "eastTo");
            return (Criteria) this;
        }

        public Criteria andSouthToIsNull() {
            addCriterion("south_to is null");
            return (Criteria) this;
        }

        public Criteria andSouthToIsNotNull() {
            addCriterion("south_to is not null");
            return (Criteria) this;
        }

        public Criteria andSouthToEqualTo(String value) {
            addCriterion("south_to =", value, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToNotEqualTo(String value) {
            addCriterion("south_to <>", value, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToGreaterThan(String value) {
            addCriterion("south_to >", value, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToGreaterThanOrEqualTo(String value) {
            addCriterion("south_to >=", value, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToLessThan(String value) {
            addCriterion("south_to <", value, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToLessThanOrEqualTo(String value) {
            addCriterion("south_to <=", value, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToLike(String value) {
            addCriterion("south_to like", value, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToNotLike(String value) {
            addCriterion("south_to not like", value, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToIn(List<String> values) {
            addCriterion("south_to in", values, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToNotIn(List<String> values) {
            addCriterion("south_to not in", values, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToBetween(String value1, String value2) {
            addCriterion("south_to between", value1, value2, "southTo");
            return (Criteria) this;
        }

        public Criteria andSouthToNotBetween(String value1, String value2) {
            addCriterion("south_to not between", value1, value2, "southTo");
            return (Criteria) this;
        }

        public Criteria andWestToIsNull() {
            addCriterion("west_to is null");
            return (Criteria) this;
        }

        public Criteria andWestToIsNotNull() {
            addCriterion("west_to is not null");
            return (Criteria) this;
        }

        public Criteria andWestToEqualTo(String value) {
            addCriterion("west_to =", value, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToNotEqualTo(String value) {
            addCriterion("west_to <>", value, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToGreaterThan(String value) {
            addCriterion("west_to >", value, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToGreaterThanOrEqualTo(String value) {
            addCriterion("west_to >=", value, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToLessThan(String value) {
            addCriterion("west_to <", value, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToLessThanOrEqualTo(String value) {
            addCriterion("west_to <=", value, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToLike(String value) {
            addCriterion("west_to like", value, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToNotLike(String value) {
            addCriterion("west_to not like", value, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToIn(List<String> values) {
            addCriterion("west_to in", values, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToNotIn(List<String> values) {
            addCriterion("west_to not in", values, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToBetween(String value1, String value2) {
            addCriterion("west_to between", value1, value2, "westTo");
            return (Criteria) this;
        }

        public Criteria andWestToNotBetween(String value1, String value2) {
            addCriterion("west_to not between", value1, value2, "westTo");
            return (Criteria) this;
        }

        public Criteria andNorthToIsNull() {
            addCriterion("north_to is null");
            return (Criteria) this;
        }

        public Criteria andNorthToIsNotNull() {
            addCriterion("north_to is not null");
            return (Criteria) this;
        }

        public Criteria andNorthToEqualTo(String value) {
            addCriterion("north_to =", value, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToNotEqualTo(String value) {
            addCriterion("north_to <>", value, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToGreaterThan(String value) {
            addCriterion("north_to >", value, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToGreaterThanOrEqualTo(String value) {
            addCriterion("north_to >=", value, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToLessThan(String value) {
            addCriterion("north_to <", value, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToLessThanOrEqualTo(String value) {
            addCriterion("north_to <=", value, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToLike(String value) {
            addCriterion("north_to like", value, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToNotLike(String value) {
            addCriterion("north_to not like", value, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToIn(List<String> values) {
            addCriterion("north_to in", values, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToNotIn(List<String> values) {
            addCriterion("north_to not in", values, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToBetween(String value1, String value2) {
            addCriterion("north_to between", value1, value2, "northTo");
            return (Criteria) this;
        }

        public Criteria andNorthToNotBetween(String value1, String value2) {
            addCriterion("north_to not between", value1, value2, "northTo");
            return (Criteria) this;
        }

        public Criteria andShapeStateIsNull() {
            addCriterion("shape_state is null");
            return (Criteria) this;
        }

        public Criteria andShapeStateIsNotNull() {
            addCriterion("shape_state is not null");
            return (Criteria) this;
        }

        public Criteria andShapeStateEqualTo(String value) {
            addCriterion("shape_state =", value, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateNotEqualTo(String value) {
            addCriterion("shape_state <>", value, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateGreaterThan(String value) {
            addCriterion("shape_state >", value, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateGreaterThanOrEqualTo(String value) {
            addCriterion("shape_state >=", value, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateLessThan(String value) {
            addCriterion("shape_state <", value, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateLessThanOrEqualTo(String value) {
            addCriterion("shape_state <=", value, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateLike(String value) {
            addCriterion("shape_state like", value, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateNotLike(String value) {
            addCriterion("shape_state not like", value, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateIn(List<String> values) {
            addCriterion("shape_state in", values, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateNotIn(List<String> values) {
            addCriterion("shape_state not in", values, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateBetween(String value1, String value2) {
            addCriterion("shape_state between", value1, value2, "shapeState");
            return (Criteria) this;
        }

        public Criteria andShapeStateNotBetween(String value1, String value2) {
            addCriterion("shape_state not between", value1, value2, "shapeState");
            return (Criteria) this;
        }

        public Criteria andPlanenessIsNull() {
            addCriterion("planeness is null");
            return (Criteria) this;
        }

        public Criteria andPlanenessIsNotNull() {
            addCriterion("planeness is not null");
            return (Criteria) this;
        }

        public Criteria andPlanenessEqualTo(String value) {
            addCriterion("planeness =", value, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessNotEqualTo(String value) {
            addCriterion("planeness <>", value, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessGreaterThan(String value) {
            addCriterion("planeness >", value, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessGreaterThanOrEqualTo(String value) {
            addCriterion("planeness >=", value, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessLessThan(String value) {
            addCriterion("planeness <", value, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessLessThanOrEqualTo(String value) {
            addCriterion("planeness <=", value, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessLike(String value) {
            addCriterion("planeness like", value, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessNotLike(String value) {
            addCriterion("planeness not like", value, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessIn(List<String> values) {
            addCriterion("planeness in", values, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessNotIn(List<String> values) {
            addCriterion("planeness not in", values, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessBetween(String value1, String value2) {
            addCriterion("planeness between", value1, value2, "planeness");
            return (Criteria) this;
        }

        public Criteria andPlanenessNotBetween(String value1, String value2) {
            addCriterion("planeness not between", value1, value2, "planeness");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeIsNull() {
            addCriterion("development_degree is null");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeIsNotNull() {
            addCriterion("development_degree is not null");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeEqualTo(String value) {
            addCriterion("development_degree =", value, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeNotEqualTo(String value) {
            addCriterion("development_degree <>", value, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeGreaterThan(String value) {
            addCriterion("development_degree >", value, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeGreaterThanOrEqualTo(String value) {
            addCriterion("development_degree >=", value, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeLessThan(String value) {
            addCriterion("development_degree <", value, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeLessThanOrEqualTo(String value) {
            addCriterion("development_degree <=", value, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeLike(String value) {
            addCriterion("development_degree like", value, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeNotLike(String value) {
            addCriterion("development_degree not like", value, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeIn(List<String> values) {
            addCriterion("development_degree in", values, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeNotIn(List<String> values) {
            addCriterion("development_degree not in", values, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeBetween(String value1, String value2) {
            addCriterion("development_degree between", value1, value2, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andDevelopmentDegreeNotBetween(String value1, String value2) {
            addCriterion("development_degree not between", value1, value2, "developmentDegree");
            return (Criteria) this;
        }

        public Criteria andRestrictiveConditionIsNull() {
            addCriterion("restrictive_condition is null");
            return (Criteria) this;
        }

        public Criteria andRestrictiveConditionIsNotNull() {
            addCriterion("restrictive_condition is not null");
            return (Criteria) this;
        }

        public Criteria andRestrictiveConditionEqualTo(String value) {
            addCriterion("restrictive_condition =", value, "restrictiveCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictiveConditionNotEqualTo(String value) {
            addCriterion("restrictive_condition <>", value, "restrictiveCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictiveConditionGreaterThan(String value) {
            addCriterion("restrictive_condition >", value, "restrictiveCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictiveConditionGreaterThanOrEqualTo(String value) {
            addCriterion("restrictive_condition >=", value, "restrictiveCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictiveConditionLessThan(String value) {
            addCriterion("restrictive_condition <", value, "restrictiveCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictiveConditionLessThanOrEqualTo(String value) {
            addCriterion("restrictive_condition <=", value, "restrictiveCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictiveConditionLike(String value) {
            addCriterion("restrictive_condition like", value, "restrictiveCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictiveConditionNotLike(String value) {
            addCriterion("restrictive_condition not like", value, "restrictiveCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictiveConditionIn(List<String> values) {
            addCriterion("restrictive_condition in", values, "restrictiveCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictiveConditionNotIn(List<String> values) {
            addCriterion("restrictive_condition not in", values, "restrictiveCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictiveConditionBetween(String value1, String value2) {
            addCriterion("restrictive_condition between", value1, value2, "restrictiveCondition");
            return (Criteria) this;
        }

        public Criteria andRestrictiveConditionNotBetween(String value1, String value2) {
            addCriterion("restrictive_condition not between", value1, value2, "restrictiveCondition");
            return (Criteria) this;
        }

        public Criteria andSoilIsNull() {
            addCriterion("soil is null");
            return (Criteria) this;
        }

        public Criteria andSoilIsNotNull() {
            addCriterion("soil is not null");
            return (Criteria) this;
        }

        public Criteria andSoilEqualTo(String value) {
            addCriterion("soil =", value, "soil");
            return (Criteria) this;
        }

        public Criteria andSoilNotEqualTo(String value) {
            addCriterion("soil <>", value, "soil");
            return (Criteria) this;
        }

        public Criteria andSoilGreaterThan(String value) {
            addCriterion("soil >", value, "soil");
            return (Criteria) this;
        }

        public Criteria andSoilGreaterThanOrEqualTo(String value) {
            addCriterion("soil >=", value, "soil");
            return (Criteria) this;
        }

        public Criteria andSoilLessThan(String value) {
            addCriterion("soil <", value, "soil");
            return (Criteria) this;
        }

        public Criteria andSoilLessThanOrEqualTo(String value) {
            addCriterion("soil <=", value, "soil");
            return (Criteria) this;
        }

        public Criteria andSoilLike(String value) {
            addCriterion("soil like", value, "soil");
            return (Criteria) this;
        }

        public Criteria andSoilNotLike(String value) {
            addCriterion("soil not like", value, "soil");
            return (Criteria) this;
        }

        public Criteria andSoilIn(List<String> values) {
            addCriterion("soil in", values, "soil");
            return (Criteria) this;
        }

        public Criteria andSoilNotIn(List<String> values) {
            addCriterion("soil not in", values, "soil");
            return (Criteria) this;
        }

        public Criteria andSoilBetween(String value1, String value2) {
            addCriterion("soil between", value1, value2, "soil");
            return (Criteria) this;
        }

        public Criteria andSoilNotBetween(String value1, String value2) {
            addCriterion("soil not between", value1, value2, "soil");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainIsNull() {
            addCriterion("topographic_terrain is null");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainIsNotNull() {
            addCriterion("topographic_terrain is not null");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainEqualTo(String value) {
            addCriterion("topographic_terrain =", value, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainNotEqualTo(String value) {
            addCriterion("topographic_terrain <>", value, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainGreaterThan(String value) {
            addCriterion("topographic_terrain >", value, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainGreaterThanOrEqualTo(String value) {
            addCriterion("topographic_terrain >=", value, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainLessThan(String value) {
            addCriterion("topographic_terrain <", value, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainLessThanOrEqualTo(String value) {
            addCriterion("topographic_terrain <=", value, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainLike(String value) {
            addCriterion("topographic_terrain like", value, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainNotLike(String value) {
            addCriterion("topographic_terrain not like", value, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainIn(List<String> values) {
            addCriterion("topographic_terrain in", values, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainNotIn(List<String> values) {
            addCriterion("topographic_terrain not in", values, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainBetween(String value1, String value2) {
            addCriterion("topographic_terrain between", value1, value2, "topographicTerrain");
            return (Criteria) this;
        }

        public Criteria andTopographicTerrainNotBetween(String value1, String value2) {
            addCriterion("topographic_terrain not between", value1, value2, "topographicTerrain");
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