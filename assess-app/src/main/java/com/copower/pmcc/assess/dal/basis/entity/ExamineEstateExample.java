package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamineEstateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamineEstateExample() {
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

        public Criteria andPlanDetailsIdIsNull() {
            addCriterion("plan_details_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdIsNotNull() {
            addCriterion("plan_details_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdEqualTo(Integer value) {
            addCriterion("plan_details_id =", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdNotEqualTo(Integer value) {
            addCriterion("plan_details_id <>", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdGreaterThan(Integer value) {
            addCriterion("plan_details_id >", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_details_id >=", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdLessThan(Integer value) {
            addCriterion("plan_details_id <", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdLessThanOrEqualTo(Integer value) {
            addCriterion("plan_details_id <=", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdIn(List<Integer> values) {
            addCriterion("plan_details_id in", values, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdNotIn(List<Integer> values) {
            addCriterion("plan_details_id not in", values, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdBetween(Integer value1, Integer value2) {
            addCriterion("plan_details_id between", value1, value2, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_details_id not between", value1, value2, "planDetailsId");
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

        public Criteria andDeveloperIdIsNull() {
            addCriterion("developer_id is null");
            return (Criteria) this;
        }

        public Criteria andDeveloperIdIsNotNull() {
            addCriterion("developer_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeveloperIdEqualTo(Integer value) {
            addCriterion("developer_id =", value, "developerId");
            return (Criteria) this;
        }

        public Criteria andDeveloperIdNotEqualTo(Integer value) {
            addCriterion("developer_id <>", value, "developerId");
            return (Criteria) this;
        }

        public Criteria andDeveloperIdGreaterThan(Integer value) {
            addCriterion("developer_id >", value, "developerId");
            return (Criteria) this;
        }

        public Criteria andDeveloperIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("developer_id >=", value, "developerId");
            return (Criteria) this;
        }

        public Criteria andDeveloperIdLessThan(Integer value) {
            addCriterion("developer_id <", value, "developerId");
            return (Criteria) this;
        }

        public Criteria andDeveloperIdLessThanOrEqualTo(Integer value) {
            addCriterion("developer_id <=", value, "developerId");
            return (Criteria) this;
        }

        public Criteria andDeveloperIdIn(List<Integer> values) {
            addCriterion("developer_id in", values, "developerId");
            return (Criteria) this;
        }

        public Criteria andDeveloperIdNotIn(List<Integer> values) {
            addCriterion("developer_id not in", values, "developerId");
            return (Criteria) this;
        }

        public Criteria andDeveloperIdBetween(Integer value1, Integer value2) {
            addCriterion("developer_id between", value1, value2, "developerId");
            return (Criteria) this;
        }

        public Criteria andDeveloperIdNotBetween(Integer value1, Integer value2) {
            addCriterion("developer_id not between", value1, value2, "developerId");
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

        public Criteria andStreetIsNull() {
            addCriterion("street is null");
            return (Criteria) this;
        }

        public Criteria andStreetIsNotNull() {
            addCriterion("street is not null");
            return (Criteria) this;
        }

        public Criteria andStreetEqualTo(String value) {
            addCriterion("street =", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotEqualTo(String value) {
            addCriterion("street <>", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetGreaterThan(String value) {
            addCriterion("street >", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetGreaterThanOrEqualTo(String value) {
            addCriterion("street >=", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetLessThan(String value) {
            addCriterion("street <", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetLessThanOrEqualTo(String value) {
            addCriterion("street <=", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetLike(String value) {
            addCriterion("street like", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotLike(String value) {
            addCriterion("street not like", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetIn(List<String> values) {
            addCriterion("street in", values, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotIn(List<String> values) {
            addCriterion("street not in", values, "street");
            return (Criteria) this;
        }

        public Criteria andStreetBetween(String value1, String value2) {
            addCriterion("street between", value1, value2, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotBetween(String value1, String value2) {
            addCriterion("street not between", value1, value2, "street");
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

        public Criteria andNumberEqualTo(String value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(String value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(String value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(String value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(String value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(String value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLike(String value) {
            addCriterion("number like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotLike(String value) {
            addCriterion("number not like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<String> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<String> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(String value1, String value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(String value1, String value2) {
            addCriterion("number not between", value1, value2, "number");
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

        public Criteria andAttachNumberIsNull() {
            addCriterion("attach_number is null");
            return (Criteria) this;
        }

        public Criteria andAttachNumberIsNotNull() {
            addCriterion("attach_number is not null");
            return (Criteria) this;
        }

        public Criteria andAttachNumberEqualTo(String value) {
            addCriterion("attach_number =", value, "attachNumber");
            return (Criteria) this;
        }

        public Criteria andAttachNumberNotEqualTo(String value) {
            addCriterion("attach_number <>", value, "attachNumber");
            return (Criteria) this;
        }

        public Criteria andAttachNumberGreaterThan(String value) {
            addCriterion("attach_number >", value, "attachNumber");
            return (Criteria) this;
        }

        public Criteria andAttachNumberGreaterThanOrEqualTo(String value) {
            addCriterion("attach_number >=", value, "attachNumber");
            return (Criteria) this;
        }

        public Criteria andAttachNumberLessThan(String value) {
            addCriterion("attach_number <", value, "attachNumber");
            return (Criteria) this;
        }

        public Criteria andAttachNumberLessThanOrEqualTo(String value) {
            addCriterion("attach_number <=", value, "attachNumber");
            return (Criteria) this;
        }

        public Criteria andAttachNumberLike(String value) {
            addCriterion("attach_number like", value, "attachNumber");
            return (Criteria) this;
        }

        public Criteria andAttachNumberNotLike(String value) {
            addCriterion("attach_number not like", value, "attachNumber");
            return (Criteria) this;
        }

        public Criteria andAttachNumberIn(List<String> values) {
            addCriterion("attach_number in", values, "attachNumber");
            return (Criteria) this;
        }

        public Criteria andAttachNumberNotIn(List<String> values) {
            addCriterion("attach_number not in", values, "attachNumber");
            return (Criteria) this;
        }

        public Criteria andAttachNumberBetween(String value1, String value2) {
            addCriterion("attach_number between", value1, value2, "attachNumber");
            return (Criteria) this;
        }

        public Criteria andAttachNumberNotBetween(String value1, String value2) {
            addCriterion("attach_number not between", value1, value2, "attachNumber");
            return (Criteria) this;
        }

        public Criteria andFloorAreaIsNull() {
            addCriterion("floor_area is null");
            return (Criteria) this;
        }

        public Criteria andFloorAreaIsNotNull() {
            addCriterion("floor_area is not null");
            return (Criteria) this;
        }

        public Criteria andFloorAreaEqualTo(BigDecimal value) {
            addCriterion("floor_area =", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotEqualTo(BigDecimal value) {
            addCriterion("floor_area <>", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaGreaterThan(BigDecimal value) {
            addCriterion("floor_area >", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_area >=", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaLessThan(BigDecimal value) {
            addCriterion("floor_area <", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_area <=", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaIn(List<BigDecimal> values) {
            addCriterion("floor_area in", values, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotIn(List<BigDecimal> values) {
            addCriterion("floor_area not in", values, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_area between", value1, value2, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_area not between", value1, value2, "floorArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaIsNull() {
            addCriterion("cover_an_area is null");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaIsNotNull() {
            addCriterion("cover_an_area is not null");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaEqualTo(BigDecimal value) {
            addCriterion("cover_an_area =", value, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaNotEqualTo(BigDecimal value) {
            addCriterion("cover_an_area <>", value, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaGreaterThan(BigDecimal value) {
            addCriterion("cover_an_area >", value, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cover_an_area >=", value, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaLessThan(BigDecimal value) {
            addCriterion("cover_an_area <", value, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cover_an_area <=", value, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaIn(List<BigDecimal> values) {
            addCriterion("cover_an_area in", values, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaNotIn(List<BigDecimal> values) {
            addCriterion("cover_an_area not in", values, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cover_an_area between", value1, value2, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cover_an_area not between", value1, value2, "coverAnArea");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateIsNull() {
            addCriterion("volumetric_rate is null");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateIsNotNull() {
            addCriterion("volumetric_rate is not null");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateEqualTo(String value) {
            addCriterion("volumetric_rate =", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateNotEqualTo(String value) {
            addCriterion("volumetric_rate <>", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateGreaterThan(String value) {
            addCriterion("volumetric_rate >", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateGreaterThanOrEqualTo(String value) {
            addCriterion("volumetric_rate >=", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateLessThan(String value) {
            addCriterion("volumetric_rate <", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateLessThanOrEqualTo(String value) {
            addCriterion("volumetric_rate <=", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateLike(String value) {
            addCriterion("volumetric_rate like", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateNotLike(String value) {
            addCriterion("volumetric_rate not like", value, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateIn(List<String> values) {
            addCriterion("volumetric_rate in", values, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateNotIn(List<String> values) {
            addCriterion("volumetric_rate not in", values, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateBetween(String value1, String value2) {
            addCriterion("volumetric_rate between", value1, value2, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andVolumetricRateNotBetween(String value1, String value2) {
            addCriterion("volumetric_rate not between", value1, value2, "volumetricRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateIsNull() {
            addCriterion("greening_rate is null");
            return (Criteria) this;
        }

        public Criteria andGreeningRateIsNotNull() {
            addCriterion("greening_rate is not null");
            return (Criteria) this;
        }

        public Criteria andGreeningRateEqualTo(String value) {
            addCriterion("greening_rate =", value, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateNotEqualTo(String value) {
            addCriterion("greening_rate <>", value, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateGreaterThan(String value) {
            addCriterion("greening_rate >", value, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateGreaterThanOrEqualTo(String value) {
            addCriterion("greening_rate >=", value, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateLessThan(String value) {
            addCriterion("greening_rate <", value, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateLessThanOrEqualTo(String value) {
            addCriterion("greening_rate <=", value, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateLike(String value) {
            addCriterion("greening_rate like", value, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateNotLike(String value) {
            addCriterion("greening_rate not like", value, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateIn(List<String> values) {
            addCriterion("greening_rate in", values, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateNotIn(List<String> values) {
            addCriterion("greening_rate not in", values, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateBetween(String value1, String value2) {
            addCriterion("greening_rate between", value1, value2, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andGreeningRateNotBetween(String value1, String value2) {
            addCriterion("greening_rate not between", value1, value2, "greeningRate");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberIsNull() {
            addCriterion("building_number is null");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberIsNotNull() {
            addCriterion("building_number is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberEqualTo(Integer value) {
            addCriterion("building_number =", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberNotEqualTo(Integer value) {
            addCriterion("building_number <>", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberGreaterThan(Integer value) {
            addCriterion("building_number >", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_number >=", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberLessThan(Integer value) {
            addCriterion("building_number <", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberLessThanOrEqualTo(Integer value) {
            addCriterion("building_number <=", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberIn(List<Integer> values) {
            addCriterion("building_number in", values, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberNotIn(List<Integer> values) {
            addCriterion("building_number not in", values, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberBetween(Integer value1, Integer value2) {
            addCriterion("building_number between", value1, value2, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("building_number not between", value1, value2, "buildingNumber");
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

        public Criteria andPositionEqualTo(String value) {
            addCriterion("position =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(String value) {
            addCriterion("position <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(String value) {
            addCriterion("position >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(String value) {
            addCriterion("position >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(String value) {
            addCriterion("position <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(String value) {
            addCriterion("position <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLike(String value) {
            addCriterion("position like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotLike(String value) {
            addCriterion("position not like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<String> values) {
            addCriterion("position in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<String> values) {
            addCriterion("position not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(String value1, String value2) {
            addCriterion("position between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(String value1, String value2) {
            addCriterion("position not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
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