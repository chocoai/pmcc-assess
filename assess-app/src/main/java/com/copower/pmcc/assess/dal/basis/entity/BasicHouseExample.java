package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicHouseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicHouseExample() {
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

        public Criteria andApplyIdIsNull() {
            addCriterion("apply_id is null");
            return (Criteria) this;
        }

        public Criteria andApplyIdIsNotNull() {
            addCriterion("apply_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplyIdEqualTo(Integer value) {
            addCriterion("apply_id =", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotEqualTo(Integer value) {
            addCriterion("apply_id <>", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThan(Integer value) {
            addCriterion("apply_id >", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("apply_id >=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThan(Integer value) {
            addCriterion("apply_id <", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThanOrEqualTo(Integer value) {
            addCriterion("apply_id <=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdIn(List<Integer> values) {
            addCriterion("apply_id in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotIn(List<Integer> values) {
            addCriterion("apply_id not in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdBetween(Integer value1, Integer value2) {
            addCriterion("apply_id between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("apply_id not between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdIsNull() {
            addCriterion("quote_id is null");
            return (Criteria) this;
        }

        public Criteria andQuoteIdIsNotNull() {
            addCriterion("quote_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuoteIdEqualTo(Integer value) {
            addCriterion("quote_id =", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdNotEqualTo(Integer value) {
            addCriterion("quote_id <>", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdGreaterThan(Integer value) {
            addCriterion("quote_id >", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("quote_id >=", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdLessThan(Integer value) {
            addCriterion("quote_id <", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdLessThanOrEqualTo(Integer value) {
            addCriterion("quote_id <=", value, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdIn(List<Integer> values) {
            addCriterion("quote_id in", values, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdNotIn(List<Integer> values) {
            addCriterion("quote_id not in", values, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdBetween(Integer value1, Integer value2) {
            addCriterion("quote_id between", value1, value2, "quoteId");
            return (Criteria) this;
        }

        public Criteria andQuoteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("quote_id not between", value1, value2, "quoteId");
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

        public Criteria andHouseNumberIsNull() {
            addCriterion("house_number is null");
            return (Criteria) this;
        }

        public Criteria andHouseNumberIsNotNull() {
            addCriterion("house_number is not null");
            return (Criteria) this;
        }

        public Criteria andHouseNumberEqualTo(String value) {
            addCriterion("house_number =", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberNotEqualTo(String value) {
            addCriterion("house_number <>", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberGreaterThan(String value) {
            addCriterion("house_number >", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberGreaterThanOrEqualTo(String value) {
            addCriterion("house_number >=", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberLessThan(String value) {
            addCriterion("house_number <", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberLessThanOrEqualTo(String value) {
            addCriterion("house_number <=", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberLike(String value) {
            addCriterion("house_number like", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberNotLike(String value) {
            addCriterion("house_number not like", value, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberIn(List<String> values) {
            addCriterion("house_number in", values, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberNotIn(List<String> values) {
            addCriterion("house_number not in", values, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberBetween(String value1, String value2) {
            addCriterion("house_number between", value1, value2, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andHouseNumberNotBetween(String value1, String value2) {
            addCriterion("house_number not between", value1, value2, "houseNumber");
            return (Criteria) this;
        }

        public Criteria andFloorIsNull() {
            addCriterion("floor is null");
            return (Criteria) this;
        }

        public Criteria andFloorIsNotNull() {
            addCriterion("floor is not null");
            return (Criteria) this;
        }

        public Criteria andFloorEqualTo(String value) {
            addCriterion("floor =", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotEqualTo(String value) {
            addCriterion("floor <>", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorGreaterThan(String value) {
            addCriterion("floor >", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorGreaterThanOrEqualTo(String value) {
            addCriterion("floor >=", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLessThan(String value) {
            addCriterion("floor <", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLessThanOrEqualTo(String value) {
            addCriterion("floor <=", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLike(String value) {
            addCriterion("floor like", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotLike(String value) {
            addCriterion("floor not like", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorIn(List<String> values) {
            addCriterion("floor in", values, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotIn(List<String> values) {
            addCriterion("floor not in", values, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorBetween(String value1, String value2) {
            addCriterion("floor between", value1, value2, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotBetween(String value1, String value2) {
            addCriterion("floor not between", value1, value2, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorDescIsNull() {
            addCriterion("floor_desc is null");
            return (Criteria) this;
        }

        public Criteria andFloorDescIsNotNull() {
            addCriterion("floor_desc is not null");
            return (Criteria) this;
        }

        public Criteria andFloorDescEqualTo(String value) {
            addCriterion("floor_desc =", value, "floorDesc");
            return (Criteria) this;
        }

        public Criteria andFloorDescNotEqualTo(String value) {
            addCriterion("floor_desc <>", value, "floorDesc");
            return (Criteria) this;
        }

        public Criteria andFloorDescGreaterThan(String value) {
            addCriterion("floor_desc >", value, "floorDesc");
            return (Criteria) this;
        }

        public Criteria andFloorDescGreaterThanOrEqualTo(String value) {
            addCriterion("floor_desc >=", value, "floorDesc");
            return (Criteria) this;
        }

        public Criteria andFloorDescLessThan(String value) {
            addCriterion("floor_desc <", value, "floorDesc");
            return (Criteria) this;
        }

        public Criteria andFloorDescLessThanOrEqualTo(String value) {
            addCriterion("floor_desc <=", value, "floorDesc");
            return (Criteria) this;
        }

        public Criteria andFloorDescLike(String value) {
            addCriterion("floor_desc like", value, "floorDesc");
            return (Criteria) this;
        }

        public Criteria andFloorDescNotLike(String value) {
            addCriterion("floor_desc not like", value, "floorDesc");
            return (Criteria) this;
        }

        public Criteria andFloorDescIn(List<String> values) {
            addCriterion("floor_desc in", values, "floorDesc");
            return (Criteria) this;
        }

        public Criteria andFloorDescNotIn(List<String> values) {
            addCriterion("floor_desc not in", values, "floorDesc");
            return (Criteria) this;
        }

        public Criteria andFloorDescBetween(String value1, String value2) {
            addCriterion("floor_desc between", value1, value2, "floorDesc");
            return (Criteria) this;
        }

        public Criteria andFloorDescNotBetween(String value1, String value2) {
            addCriterion("floor_desc not between", value1, value2, "floorDesc");
            return (Criteria) this;
        }

        public Criteria andHuxingIdIsNull() {
            addCriterion("huxing_id is null");
            return (Criteria) this;
        }

        public Criteria andHuxingIdIsNotNull() {
            addCriterion("huxing_id is not null");
            return (Criteria) this;
        }

        public Criteria andHuxingIdEqualTo(Integer value) {
            addCriterion("huxing_id =", value, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingIdNotEqualTo(Integer value) {
            addCriterion("huxing_id <>", value, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingIdGreaterThan(Integer value) {
            addCriterion("huxing_id >", value, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("huxing_id >=", value, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingIdLessThan(Integer value) {
            addCriterion("huxing_id <", value, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingIdLessThanOrEqualTo(Integer value) {
            addCriterion("huxing_id <=", value, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingIdIn(List<Integer> values) {
            addCriterion("huxing_id in", values, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingIdNotIn(List<Integer> values) {
            addCriterion("huxing_id not in", values, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingIdBetween(Integer value1, Integer value2) {
            addCriterion("huxing_id between", value1, value2, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingIdNotBetween(Integer value1, Integer value2) {
            addCriterion("huxing_id not between", value1, value2, "huxingId");
            return (Criteria) this;
        }

        public Criteria andHuxingNameIsNull() {
            addCriterion("huxing_name is null");
            return (Criteria) this;
        }

        public Criteria andHuxingNameIsNotNull() {
            addCriterion("huxing_name is not null");
            return (Criteria) this;
        }

        public Criteria andHuxingNameEqualTo(String value) {
            addCriterion("huxing_name =", value, "huxingName");
            return (Criteria) this;
        }

        public Criteria andHuxingNameNotEqualTo(String value) {
            addCriterion("huxing_name <>", value, "huxingName");
            return (Criteria) this;
        }

        public Criteria andHuxingNameGreaterThan(String value) {
            addCriterion("huxing_name >", value, "huxingName");
            return (Criteria) this;
        }

        public Criteria andHuxingNameGreaterThanOrEqualTo(String value) {
            addCriterion("huxing_name >=", value, "huxingName");
            return (Criteria) this;
        }

        public Criteria andHuxingNameLessThan(String value) {
            addCriterion("huxing_name <", value, "huxingName");
            return (Criteria) this;
        }

        public Criteria andHuxingNameLessThanOrEqualTo(String value) {
            addCriterion("huxing_name <=", value, "huxingName");
            return (Criteria) this;
        }

        public Criteria andHuxingNameLike(String value) {
            addCriterion("huxing_name like", value, "huxingName");
            return (Criteria) this;
        }

        public Criteria andHuxingNameNotLike(String value) {
            addCriterion("huxing_name not like", value, "huxingName");
            return (Criteria) this;
        }

        public Criteria andHuxingNameIn(List<String> values) {
            addCriterion("huxing_name in", values, "huxingName");
            return (Criteria) this;
        }

        public Criteria andHuxingNameNotIn(List<String> values) {
            addCriterion("huxing_name not in", values, "huxingName");
            return (Criteria) this;
        }

        public Criteria andHuxingNameBetween(String value1, String value2) {
            addCriterion("huxing_name between", value1, value2, "huxingName");
            return (Criteria) this;
        }

        public Criteria andHuxingNameNotBetween(String value1, String value2) {
            addCriterion("huxing_name not between", value1, value2, "huxingName");
            return (Criteria) this;
        }

        public Criteria andNewHuxingNameIsNull() {
            addCriterion("new_huxing_name is null");
            return (Criteria) this;
        }

        public Criteria andNewHuxingNameIsNotNull() {
            addCriterion("new_huxing_name is not null");
            return (Criteria) this;
        }

        public Criteria andNewHuxingNameEqualTo(String value) {
            addCriterion("new_huxing_name =", value, "newHuxingName");
            return (Criteria) this;
        }

        public Criteria andNewHuxingNameNotEqualTo(String value) {
            addCriterion("new_huxing_name <>", value, "newHuxingName");
            return (Criteria) this;
        }

        public Criteria andNewHuxingNameGreaterThan(String value) {
            addCriterion("new_huxing_name >", value, "newHuxingName");
            return (Criteria) this;
        }

        public Criteria andNewHuxingNameGreaterThanOrEqualTo(String value) {
            addCriterion("new_huxing_name >=", value, "newHuxingName");
            return (Criteria) this;
        }

        public Criteria andNewHuxingNameLessThan(String value) {
            addCriterion("new_huxing_name <", value, "newHuxingName");
            return (Criteria) this;
        }

        public Criteria andNewHuxingNameLessThanOrEqualTo(String value) {
            addCriterion("new_huxing_name <=", value, "newHuxingName");
            return (Criteria) this;
        }

        public Criteria andNewHuxingNameLike(String value) {
            addCriterion("new_huxing_name like", value, "newHuxingName");
            return (Criteria) this;
        }

        public Criteria andNewHuxingNameNotLike(String value) {
            addCriterion("new_huxing_name not like", value, "newHuxingName");
            return (Criteria) this;
        }

        public Criteria andNewHuxingNameIn(List<String> values) {
            addCriterion("new_huxing_name in", values, "newHuxingName");
            return (Criteria) this;
        }

        public Criteria andNewHuxingNameNotIn(List<String> values) {
            addCriterion("new_huxing_name not in", values, "newHuxingName");
            return (Criteria) this;
        }

        public Criteria andNewHuxingNameBetween(String value1, String value2) {
            addCriterion("new_huxing_name between", value1, value2, "newHuxingName");
            return (Criteria) this;
        }

        public Criteria andNewHuxingNameNotBetween(String value1, String value2) {
            addCriterion("new_huxing_name not between", value1, value2, "newHuxingName");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionIsNull() {
            addCriterion("spatial_distribution is null");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionIsNotNull() {
            addCriterion("spatial_distribution is not null");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionEqualTo(Integer value) {
            addCriterion("spatial_distribution =", value, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionNotEqualTo(Integer value) {
            addCriterion("spatial_distribution <>", value, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionGreaterThan(Integer value) {
            addCriterion("spatial_distribution >", value, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionGreaterThanOrEqualTo(Integer value) {
            addCriterion("spatial_distribution >=", value, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionLessThan(Integer value) {
            addCriterion("spatial_distribution <", value, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionLessThanOrEqualTo(Integer value) {
            addCriterion("spatial_distribution <=", value, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionIn(List<Integer> values) {
            addCriterion("spatial_distribution in", values, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionNotIn(List<Integer> values) {
            addCriterion("spatial_distribution not in", values, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionBetween(Integer value1, Integer value2) {
            addCriterion("spatial_distribution between", value1, value2, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionNotBetween(Integer value1, Integer value2) {
            addCriterion("spatial_distribution not between", value1, value2, "spatialDistribution");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionDescIsNull() {
            addCriterion("spatial_distribution_desc is null");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionDescIsNotNull() {
            addCriterion("spatial_distribution_desc is not null");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionDescEqualTo(String value) {
            addCriterion("spatial_distribution_desc =", value, "spatialDistributionDesc");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionDescNotEqualTo(String value) {
            addCriterion("spatial_distribution_desc <>", value, "spatialDistributionDesc");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionDescGreaterThan(String value) {
            addCriterion("spatial_distribution_desc >", value, "spatialDistributionDesc");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionDescGreaterThanOrEqualTo(String value) {
            addCriterion("spatial_distribution_desc >=", value, "spatialDistributionDesc");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionDescLessThan(String value) {
            addCriterion("spatial_distribution_desc <", value, "spatialDistributionDesc");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionDescLessThanOrEqualTo(String value) {
            addCriterion("spatial_distribution_desc <=", value, "spatialDistributionDesc");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionDescLike(String value) {
            addCriterion("spatial_distribution_desc like", value, "spatialDistributionDesc");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionDescNotLike(String value) {
            addCriterion("spatial_distribution_desc not like", value, "spatialDistributionDesc");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionDescIn(List<String> values) {
            addCriterion("spatial_distribution_desc in", values, "spatialDistributionDesc");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionDescNotIn(List<String> values) {
            addCriterion("spatial_distribution_desc not in", values, "spatialDistributionDesc");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionDescBetween(String value1, String value2) {
            addCriterion("spatial_distribution_desc between", value1, value2, "spatialDistributionDesc");
            return (Criteria) this;
        }

        public Criteria andSpatialDistributionDescNotBetween(String value1, String value2) {
            addCriterion("spatial_distribution_desc not between", value1, value2, "spatialDistributionDesc");
            return (Criteria) this;
        }

        public Criteria andCertUseIsNull() {
            addCriterion("cert_use is null");
            return (Criteria) this;
        }

        public Criteria andCertUseIsNotNull() {
            addCriterion("cert_use is not null");
            return (Criteria) this;
        }

        public Criteria andCertUseEqualTo(String value) {
            addCriterion("cert_use =", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotEqualTo(String value) {
            addCriterion("cert_use <>", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseGreaterThan(String value) {
            addCriterion("cert_use >", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseGreaterThanOrEqualTo(String value) {
            addCriterion("cert_use >=", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseLessThan(String value) {
            addCriterion("cert_use <", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseLessThanOrEqualTo(String value) {
            addCriterion("cert_use <=", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseLike(String value) {
            addCriterion("cert_use like", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotLike(String value) {
            addCriterion("cert_use not like", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseIn(List<String> values) {
            addCriterion("cert_use in", values, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotIn(List<String> values) {
            addCriterion("cert_use not in", values, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseBetween(String value1, String value2) {
            addCriterion("cert_use between", value1, value2, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotBetween(String value1, String value2) {
            addCriterion("cert_use not between", value1, value2, "certUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseIsNull() {
            addCriterion("practical_use is null");
            return (Criteria) this;
        }

        public Criteria andPracticalUseIsNotNull() {
            addCriterion("practical_use is not null");
            return (Criteria) this;
        }

        public Criteria andPracticalUseEqualTo(String value) {
            addCriterion("practical_use =", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseNotEqualTo(String value) {
            addCriterion("practical_use <>", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseGreaterThan(String value) {
            addCriterion("practical_use >", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseGreaterThanOrEqualTo(String value) {
            addCriterion("practical_use >=", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseLessThan(String value) {
            addCriterion("practical_use <", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseLessThanOrEqualTo(String value) {
            addCriterion("practical_use <=", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseLike(String value) {
            addCriterion("practical_use like", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseNotLike(String value) {
            addCriterion("practical_use not like", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseIn(List<String> values) {
            addCriterion("practical_use in", values, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseNotIn(List<String> values) {
            addCriterion("practical_use not in", values, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseBetween(String value1, String value2) {
            addCriterion("practical_use between", value1, value2, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseNotBetween(String value1, String value2) {
            addCriterion("practical_use not between", value1, value2, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andOrientationIsNull() {
            addCriterion("orientation is null");
            return (Criteria) this;
        }

        public Criteria andOrientationIsNotNull() {
            addCriterion("orientation is not null");
            return (Criteria) this;
        }

        public Criteria andOrientationEqualTo(Integer value) {
            addCriterion("orientation =", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationNotEqualTo(Integer value) {
            addCriterion("orientation <>", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationGreaterThan(Integer value) {
            addCriterion("orientation >", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationGreaterThanOrEqualTo(Integer value) {
            addCriterion("orientation >=", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationLessThan(Integer value) {
            addCriterion("orientation <", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationLessThanOrEqualTo(Integer value) {
            addCriterion("orientation <=", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationIn(List<Integer> values) {
            addCriterion("orientation in", values, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationNotIn(List<Integer> values) {
            addCriterion("orientation not in", values, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationBetween(Integer value1, Integer value2) {
            addCriterion("orientation between", value1, value2, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationNotBetween(Integer value1, Integer value2) {
            addCriterion("orientation not between", value1, value2, "orientation");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(BigDecimal value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(BigDecimal value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(BigDecimal value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(BigDecimal value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<BigDecimal> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<BigDecimal> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaDescIsNull() {
            addCriterion("area_desc is null");
            return (Criteria) this;
        }

        public Criteria andAreaDescIsNotNull() {
            addCriterion("area_desc is not null");
            return (Criteria) this;
        }

        public Criteria andAreaDescEqualTo(String value) {
            addCriterion("area_desc =", value, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescNotEqualTo(String value) {
            addCriterion("area_desc <>", value, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescGreaterThan(String value) {
            addCriterion("area_desc >", value, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescGreaterThanOrEqualTo(String value) {
            addCriterion("area_desc >=", value, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescLessThan(String value) {
            addCriterion("area_desc <", value, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescLessThanOrEqualTo(String value) {
            addCriterion("area_desc <=", value, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescLike(String value) {
            addCriterion("area_desc like", value, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescNotLike(String value) {
            addCriterion("area_desc not like", value, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescIn(List<String> values) {
            addCriterion("area_desc in", values, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescNotIn(List<String> values) {
            addCriterion("area_desc not in", values, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescBetween(String value1, String value2) {
            addCriterion("area_desc between", value1, value2, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andAreaDescNotBetween(String value1, String value2) {
            addCriterion("area_desc not between", value1, value2, "areaDesc");
            return (Criteria) this;
        }

        public Criteria andResearchTypeIsNull() {
            addCriterion("research_type is null");
            return (Criteria) this;
        }

        public Criteria andResearchTypeIsNotNull() {
            addCriterion("research_type is not null");
            return (Criteria) this;
        }

        public Criteria andResearchTypeEqualTo(Integer value) {
            addCriterion("research_type =", value, "researchType");
            return (Criteria) this;
        }

        public Criteria andResearchTypeNotEqualTo(Integer value) {
            addCriterion("research_type <>", value, "researchType");
            return (Criteria) this;
        }

        public Criteria andResearchTypeGreaterThan(Integer value) {
            addCriterion("research_type >", value, "researchType");
            return (Criteria) this;
        }

        public Criteria andResearchTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("research_type >=", value, "researchType");
            return (Criteria) this;
        }

        public Criteria andResearchTypeLessThan(Integer value) {
            addCriterion("research_type <", value, "researchType");
            return (Criteria) this;
        }

        public Criteria andResearchTypeLessThanOrEqualTo(Integer value) {
            addCriterion("research_type <=", value, "researchType");
            return (Criteria) this;
        }

        public Criteria andResearchTypeIn(List<Integer> values) {
            addCriterion("research_type in", values, "researchType");
            return (Criteria) this;
        }

        public Criteria andResearchTypeNotIn(List<Integer> values) {
            addCriterion("research_type not in", values, "researchType");
            return (Criteria) this;
        }

        public Criteria andResearchTypeBetween(Integer value1, Integer value2) {
            addCriterion("research_type between", value1, value2, "researchType");
            return (Criteria) this;
        }

        public Criteria andResearchTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("research_type not between", value1, value2, "researchType");
            return (Criteria) this;
        }

        public Criteria andRightInterestsRestrictionIsNull() {
            addCriterion("right_interests_restriction is null");
            return (Criteria) this;
        }

        public Criteria andRightInterestsRestrictionIsNotNull() {
            addCriterion("right_interests_restriction is not null");
            return (Criteria) this;
        }

        public Criteria andRightInterestsRestrictionEqualTo(String value) {
            addCriterion("right_interests_restriction =", value, "rightInterestsRestriction");
            return (Criteria) this;
        }

        public Criteria andRightInterestsRestrictionNotEqualTo(String value) {
            addCriterion("right_interests_restriction <>", value, "rightInterestsRestriction");
            return (Criteria) this;
        }

        public Criteria andRightInterestsRestrictionGreaterThan(String value) {
            addCriterion("right_interests_restriction >", value, "rightInterestsRestriction");
            return (Criteria) this;
        }

        public Criteria andRightInterestsRestrictionGreaterThanOrEqualTo(String value) {
            addCriterion("right_interests_restriction >=", value, "rightInterestsRestriction");
            return (Criteria) this;
        }

        public Criteria andRightInterestsRestrictionLessThan(String value) {
            addCriterion("right_interests_restriction <", value, "rightInterestsRestriction");
            return (Criteria) this;
        }

        public Criteria andRightInterestsRestrictionLessThanOrEqualTo(String value) {
            addCriterion("right_interests_restriction <=", value, "rightInterestsRestriction");
            return (Criteria) this;
        }

        public Criteria andRightInterestsRestrictionLike(String value) {
            addCriterion("right_interests_restriction like", value, "rightInterestsRestriction");
            return (Criteria) this;
        }

        public Criteria andRightInterestsRestrictionNotLike(String value) {
            addCriterion("right_interests_restriction not like", value, "rightInterestsRestriction");
            return (Criteria) this;
        }

        public Criteria andRightInterestsRestrictionIn(List<String> values) {
            addCriterion("right_interests_restriction in", values, "rightInterestsRestriction");
            return (Criteria) this;
        }

        public Criteria andRightInterestsRestrictionNotIn(List<String> values) {
            addCriterion("right_interests_restriction not in", values, "rightInterestsRestriction");
            return (Criteria) this;
        }

        public Criteria andRightInterestsRestrictionBetween(String value1, String value2) {
            addCriterion("right_interests_restriction between", value1, value2, "rightInterestsRestriction");
            return (Criteria) this;
        }

        public Criteria andRightInterestsRestrictionNotBetween(String value1, String value2) {
            addCriterion("right_interests_restriction not between", value1, value2, "rightInterestsRestriction");
            return (Criteria) this;
        }

        public Criteria andUseEnvironmentIsNull() {
            addCriterion("use_environment is null");
            return (Criteria) this;
        }

        public Criteria andUseEnvironmentIsNotNull() {
            addCriterion("use_environment is not null");
            return (Criteria) this;
        }

        public Criteria andUseEnvironmentEqualTo(Integer value) {
            addCriterion("use_environment =", value, "useEnvironment");
            return (Criteria) this;
        }

        public Criteria andUseEnvironmentNotEqualTo(Integer value) {
            addCriterion("use_environment <>", value, "useEnvironment");
            return (Criteria) this;
        }

        public Criteria andUseEnvironmentGreaterThan(Integer value) {
            addCriterion("use_environment >", value, "useEnvironment");
            return (Criteria) this;
        }

        public Criteria andUseEnvironmentGreaterThanOrEqualTo(Integer value) {
            addCriterion("use_environment >=", value, "useEnvironment");
            return (Criteria) this;
        }

        public Criteria andUseEnvironmentLessThan(Integer value) {
            addCriterion("use_environment <", value, "useEnvironment");
            return (Criteria) this;
        }

        public Criteria andUseEnvironmentLessThanOrEqualTo(Integer value) {
            addCriterion("use_environment <=", value, "useEnvironment");
            return (Criteria) this;
        }

        public Criteria andUseEnvironmentIn(List<Integer> values) {
            addCriterion("use_environment in", values, "useEnvironment");
            return (Criteria) this;
        }

        public Criteria andUseEnvironmentNotIn(List<Integer> values) {
            addCriterion("use_environment not in", values, "useEnvironment");
            return (Criteria) this;
        }

        public Criteria andUseEnvironmentBetween(Integer value1, Integer value2) {
            addCriterion("use_environment between", value1, value2, "useEnvironment");
            return (Criteria) this;
        }

        public Criteria andUseEnvironmentNotBetween(Integer value1, Integer value2) {
            addCriterion("use_environment not between", value1, value2, "useEnvironment");
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

        public Criteria andCaseDateIsNull() {
            addCriterion("case_date is null");
            return (Criteria) this;
        }

        public Criteria andCaseDateIsNotNull() {
            addCriterion("case_date is not null");
            return (Criteria) this;
        }

        public Criteria andCaseDateEqualTo(Date value) {
            addCriterion("case_date =", value, "caseDate");
            return (Criteria) this;
        }

        public Criteria andCaseDateNotEqualTo(Date value) {
            addCriterion("case_date <>", value, "caseDate");
            return (Criteria) this;
        }

        public Criteria andCaseDateGreaterThan(Date value) {
            addCriterion("case_date >", value, "caseDate");
            return (Criteria) this;
        }

        public Criteria andCaseDateGreaterThanOrEqualTo(Date value) {
            addCriterion("case_date >=", value, "caseDate");
            return (Criteria) this;
        }

        public Criteria andCaseDateLessThan(Date value) {
            addCriterion("case_date <", value, "caseDate");
            return (Criteria) this;
        }

        public Criteria andCaseDateLessThanOrEqualTo(Date value) {
            addCriterion("case_date <=", value, "caseDate");
            return (Criteria) this;
        }

        public Criteria andCaseDateIn(List<Date> values) {
            addCriterion("case_date in", values, "caseDate");
            return (Criteria) this;
        }

        public Criteria andCaseDateNotIn(List<Date> values) {
            addCriterion("case_date not in", values, "caseDate");
            return (Criteria) this;
        }

        public Criteria andCaseDateBetween(Date value1, Date value2) {
            addCriterion("case_date between", value1, value2, "caseDate");
            return (Criteria) this;
        }

        public Criteria andCaseDateNotBetween(Date value1, Date value2) {
            addCriterion("case_date not between", value1, value2, "caseDate");
            return (Criteria) this;
        }

        public Criteria andUseYearIsNull() {
            addCriterion("use_year is null");
            return (Criteria) this;
        }

        public Criteria andUseYearIsNotNull() {
            addCriterion("use_year is not null");
            return (Criteria) this;
        }

        public Criteria andUseYearEqualTo(Integer value) {
            addCriterion("use_year =", value, "useYear");
            return (Criteria) this;
        }

        public Criteria andUseYearNotEqualTo(Integer value) {
            addCriterion("use_year <>", value, "useYear");
            return (Criteria) this;
        }

        public Criteria andUseYearGreaterThan(Integer value) {
            addCriterion("use_year >", value, "useYear");
            return (Criteria) this;
        }

        public Criteria andUseYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("use_year >=", value, "useYear");
            return (Criteria) this;
        }

        public Criteria andUseYearLessThan(Integer value) {
            addCriterion("use_year <", value, "useYear");
            return (Criteria) this;
        }

        public Criteria andUseYearLessThanOrEqualTo(Integer value) {
            addCriterion("use_year <=", value, "useYear");
            return (Criteria) this;
        }

        public Criteria andUseYearIn(List<Integer> values) {
            addCriterion("use_year in", values, "useYear");
            return (Criteria) this;
        }

        public Criteria andUseYearNotIn(List<Integer> values) {
            addCriterion("use_year not in", values, "useYear");
            return (Criteria) this;
        }

        public Criteria andUseYearBetween(Integer value1, Integer value2) {
            addCriterion("use_year between", value1, value2, "useYear");
            return (Criteria) this;
        }

        public Criteria andUseYearNotBetween(Integer value1, Integer value2) {
            addCriterion("use_year not between", value1, value2, "useYear");
            return (Criteria) this;
        }

        public Criteria andFloorPriceIsNull() {
            addCriterion("floor_price is null");
            return (Criteria) this;
        }

        public Criteria andFloorPriceIsNotNull() {
            addCriterion("floor_price is not null");
            return (Criteria) this;
        }

        public Criteria andFloorPriceEqualTo(BigDecimal value) {
            addCriterion("floor_price =", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceNotEqualTo(BigDecimal value) {
            addCriterion("floor_price <>", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceGreaterThan(BigDecimal value) {
            addCriterion("floor_price >", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_price >=", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceLessThan(BigDecimal value) {
            addCriterion("floor_price <", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_price <=", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceIn(List<BigDecimal> values) {
            addCriterion("floor_price in", values, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceNotIn(List<BigDecimal> values) {
            addCriterion("floor_price not in", values, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_price between", value1, value2, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_price not between", value1, value2, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andLandLocationIsNull() {
            addCriterion("land_location is null");
            return (Criteria) this;
        }

        public Criteria andLandLocationIsNotNull() {
            addCriterion("land_location is not null");
            return (Criteria) this;
        }

        public Criteria andLandLocationEqualTo(String value) {
            addCriterion("land_location =", value, "landLocation");
            return (Criteria) this;
        }

        public Criteria andLandLocationNotEqualTo(String value) {
            addCriterion("land_location <>", value, "landLocation");
            return (Criteria) this;
        }

        public Criteria andLandLocationGreaterThan(String value) {
            addCriterion("land_location >", value, "landLocation");
            return (Criteria) this;
        }

        public Criteria andLandLocationGreaterThanOrEqualTo(String value) {
            addCriterion("land_location >=", value, "landLocation");
            return (Criteria) this;
        }

        public Criteria andLandLocationLessThan(String value) {
            addCriterion("land_location <", value, "landLocation");
            return (Criteria) this;
        }

        public Criteria andLandLocationLessThanOrEqualTo(String value) {
            addCriterion("land_location <=", value, "landLocation");
            return (Criteria) this;
        }

        public Criteria andLandLocationLike(String value) {
            addCriterion("land_location like", value, "landLocation");
            return (Criteria) this;
        }

        public Criteria andLandLocationNotLike(String value) {
            addCriterion("land_location not like", value, "landLocation");
            return (Criteria) this;
        }

        public Criteria andLandLocationIn(List<String> values) {
            addCriterion("land_location in", values, "landLocation");
            return (Criteria) this;
        }

        public Criteria andLandLocationNotIn(List<String> values) {
            addCriterion("land_location not in", values, "landLocation");
            return (Criteria) this;
        }

        public Criteria andLandLocationBetween(String value1, String value2) {
            addCriterion("land_location between", value1, value2, "landLocation");
            return (Criteria) this;
        }

        public Criteria andLandLocationNotBetween(String value1, String value2) {
            addCriterion("land_location not between", value1, value2, "landLocation");
            return (Criteria) this;
        }

        public Criteria andNewDegreeIsNull() {
            addCriterion("new_degree is null");
            return (Criteria) this;
        }

        public Criteria andNewDegreeIsNotNull() {
            addCriterion("new_degree is not null");
            return (Criteria) this;
        }

        public Criteria andNewDegreeEqualTo(String value) {
            addCriterion("new_degree =", value, "newDegree");
            return (Criteria) this;
        }

        public Criteria andNewDegreeNotEqualTo(String value) {
            addCriterion("new_degree <>", value, "newDegree");
            return (Criteria) this;
        }

        public Criteria andNewDegreeGreaterThan(String value) {
            addCriterion("new_degree >", value, "newDegree");
            return (Criteria) this;
        }

        public Criteria andNewDegreeGreaterThanOrEqualTo(String value) {
            addCriterion("new_degree >=", value, "newDegree");
            return (Criteria) this;
        }

        public Criteria andNewDegreeLessThan(String value) {
            addCriterion("new_degree <", value, "newDegree");
            return (Criteria) this;
        }

        public Criteria andNewDegreeLessThanOrEqualTo(String value) {
            addCriterion("new_degree <=", value, "newDegree");
            return (Criteria) this;
        }

        public Criteria andNewDegreeLike(String value) {
            addCriterion("new_degree like", value, "newDegree");
            return (Criteria) this;
        }

        public Criteria andNewDegreeNotLike(String value) {
            addCriterion("new_degree not like", value, "newDegree");
            return (Criteria) this;
        }

        public Criteria andNewDegreeIn(List<String> values) {
            addCriterion("new_degree in", values, "newDegree");
            return (Criteria) this;
        }

        public Criteria andNewDegreeNotIn(List<String> values) {
            addCriterion("new_degree not in", values, "newDegree");
            return (Criteria) this;
        }

        public Criteria andNewDegreeBetween(String value1, String value2) {
            addCriterion("new_degree between", value1, value2, "newDegree");
            return (Criteria) this;
        }

        public Criteria andNewDegreeNotBetween(String value1, String value2) {
            addCriterion("new_degree not between", value1, value2, "newDegree");
            return (Criteria) this;
        }

        public Criteria andUseConditionIsNull() {
            addCriterion("use_condition is null");
            return (Criteria) this;
        }

        public Criteria andUseConditionIsNotNull() {
            addCriterion("use_condition is not null");
            return (Criteria) this;
        }

        public Criteria andUseConditionEqualTo(Integer value) {
            addCriterion("use_condition =", value, "useCondition");
            return (Criteria) this;
        }

        public Criteria andUseConditionNotEqualTo(Integer value) {
            addCriterion("use_condition <>", value, "useCondition");
            return (Criteria) this;
        }

        public Criteria andUseConditionGreaterThan(Integer value) {
            addCriterion("use_condition >", value, "useCondition");
            return (Criteria) this;
        }

        public Criteria andUseConditionGreaterThanOrEqualTo(Integer value) {
            addCriterion("use_condition >=", value, "useCondition");
            return (Criteria) this;
        }

        public Criteria andUseConditionLessThan(Integer value) {
            addCriterion("use_condition <", value, "useCondition");
            return (Criteria) this;
        }

        public Criteria andUseConditionLessThanOrEqualTo(Integer value) {
            addCriterion("use_condition <=", value, "useCondition");
            return (Criteria) this;
        }

        public Criteria andUseConditionIn(List<Integer> values) {
            addCriterion("use_condition in", values, "useCondition");
            return (Criteria) this;
        }

        public Criteria andUseConditionNotIn(List<Integer> values) {
            addCriterion("use_condition not in", values, "useCondition");
            return (Criteria) this;
        }

        public Criteria andUseConditionBetween(Integer value1, Integer value2) {
            addCriterion("use_condition between", value1, value2, "useCondition");
            return (Criteria) this;
        }

        public Criteria andUseConditionNotBetween(Integer value1, Integer value2) {
            addCriterion("use_condition not between", value1, value2, "useCondition");
            return (Criteria) this;
        }

        public Criteria andUseConditionDescriptionIsNull() {
            addCriterion("use_condition_description is null");
            return (Criteria) this;
        }

        public Criteria andUseConditionDescriptionIsNotNull() {
            addCriterion("use_condition_description is not null");
            return (Criteria) this;
        }

        public Criteria andUseConditionDescriptionEqualTo(String value) {
            addCriterion("use_condition_description =", value, "useConditionDescription");
            return (Criteria) this;
        }

        public Criteria andUseConditionDescriptionNotEqualTo(String value) {
            addCriterion("use_condition_description <>", value, "useConditionDescription");
            return (Criteria) this;
        }

        public Criteria andUseConditionDescriptionGreaterThan(String value) {
            addCriterion("use_condition_description >", value, "useConditionDescription");
            return (Criteria) this;
        }

        public Criteria andUseConditionDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("use_condition_description >=", value, "useConditionDescription");
            return (Criteria) this;
        }

        public Criteria andUseConditionDescriptionLessThan(String value) {
            addCriterion("use_condition_description <", value, "useConditionDescription");
            return (Criteria) this;
        }

        public Criteria andUseConditionDescriptionLessThanOrEqualTo(String value) {
            addCriterion("use_condition_description <=", value, "useConditionDescription");
            return (Criteria) this;
        }

        public Criteria andUseConditionDescriptionLike(String value) {
            addCriterion("use_condition_description like", value, "useConditionDescription");
            return (Criteria) this;
        }

        public Criteria andUseConditionDescriptionNotLike(String value) {
            addCriterion("use_condition_description not like", value, "useConditionDescription");
            return (Criteria) this;
        }

        public Criteria andUseConditionDescriptionIn(List<String> values) {
            addCriterion("use_condition_description in", values, "useConditionDescription");
            return (Criteria) this;
        }

        public Criteria andUseConditionDescriptionNotIn(List<String> values) {
            addCriterion("use_condition_description not in", values, "useConditionDescription");
            return (Criteria) this;
        }

        public Criteria andUseConditionDescriptionBetween(String value1, String value2) {
            addCriterion("use_condition_description between", value1, value2, "useConditionDescription");
            return (Criteria) this;
        }

        public Criteria andUseConditionDescriptionNotBetween(String value1, String value2) {
            addCriterion("use_condition_description not between", value1, value2, "useConditionDescription");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationIsNull() {
            addCriterion("decorate_situation is null");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationIsNotNull() {
            addCriterion("decorate_situation is not null");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationEqualTo(Integer value) {
            addCriterion("decorate_situation =", value, "decorateSituation");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationNotEqualTo(Integer value) {
            addCriterion("decorate_situation <>", value, "decorateSituation");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationGreaterThan(Integer value) {
            addCriterion("decorate_situation >", value, "decorateSituation");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationGreaterThanOrEqualTo(Integer value) {
            addCriterion("decorate_situation >=", value, "decorateSituation");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationLessThan(Integer value) {
            addCriterion("decorate_situation <", value, "decorateSituation");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationLessThanOrEqualTo(Integer value) {
            addCriterion("decorate_situation <=", value, "decorateSituation");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationIn(List<Integer> values) {
            addCriterion("decorate_situation in", values, "decorateSituation");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationNotIn(List<Integer> values) {
            addCriterion("decorate_situation not in", values, "decorateSituation");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationBetween(Integer value1, Integer value2) {
            addCriterion("decorate_situation between", value1, value2, "decorateSituation");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationNotBetween(Integer value1, Integer value2) {
            addCriterion("decorate_situation not between", value1, value2, "decorateSituation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationIsNull() {
            addCriterion("price_connotation is null");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationIsNotNull() {
            addCriterion("price_connotation is not null");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationEqualTo(Integer value) {
            addCriterion("price_connotation =", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationNotEqualTo(Integer value) {
            addCriterion("price_connotation <>", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationGreaterThan(Integer value) {
            addCriterion("price_connotation >", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationGreaterThanOrEqualTo(Integer value) {
            addCriterion("price_connotation >=", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationLessThan(Integer value) {
            addCriterion("price_connotation <", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationLessThanOrEqualTo(Integer value) {
            addCriterion("price_connotation <=", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationIn(List<Integer> values) {
            addCriterion("price_connotation in", values, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationNotIn(List<Integer> values) {
            addCriterion("price_connotation not in", values, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationBetween(Integer value1, Integer value2) {
            addCriterion("price_connotation between", value1, value2, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationNotBetween(Integer value1, Integer value2) {
            addCriterion("price_connotation not between", value1, value2, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationDescriptionIsNull() {
            addCriterion("decorate_situation_description is null");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationDescriptionIsNotNull() {
            addCriterion("decorate_situation_description is not null");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationDescriptionEqualTo(String value) {
            addCriterion("decorate_situation_description =", value, "decorateSituationDescription");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationDescriptionNotEqualTo(String value) {
            addCriterion("decorate_situation_description <>", value, "decorateSituationDescription");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationDescriptionGreaterThan(String value) {
            addCriterion("decorate_situation_description >", value, "decorateSituationDescription");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("decorate_situation_description >=", value, "decorateSituationDescription");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationDescriptionLessThan(String value) {
            addCriterion("decorate_situation_description <", value, "decorateSituationDescription");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationDescriptionLessThanOrEqualTo(String value) {
            addCriterion("decorate_situation_description <=", value, "decorateSituationDescription");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationDescriptionLike(String value) {
            addCriterion("decorate_situation_description like", value, "decorateSituationDescription");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationDescriptionNotLike(String value) {
            addCriterion("decorate_situation_description not like", value, "decorateSituationDescription");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationDescriptionIn(List<String> values) {
            addCriterion("decorate_situation_description in", values, "decorateSituationDescription");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationDescriptionNotIn(List<String> values) {
            addCriterion("decorate_situation_description not in", values, "decorateSituationDescription");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationDescriptionBetween(String value1, String value2) {
            addCriterion("decorate_situation_description between", value1, value2, "decorateSituationDescription");
            return (Criteria) this;
        }

        public Criteria andDecorateSituationDescriptionNotBetween(String value1, String value2) {
            addCriterion("decorate_situation_description not between", value1, value2, "decorateSituationDescription");
            return (Criteria) this;
        }

        public Criteria andMapIdIsNull() {
            addCriterion("map_id is null");
            return (Criteria) this;
        }

        public Criteria andMapIdIsNotNull() {
            addCriterion("map_id is not null");
            return (Criteria) this;
        }

        public Criteria andMapIdEqualTo(Integer value) {
            addCriterion("map_id =", value, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdNotEqualTo(Integer value) {
            addCriterion("map_id <>", value, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdGreaterThan(Integer value) {
            addCriterion("map_id >", value, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("map_id >=", value, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdLessThan(Integer value) {
            addCriterion("map_id <", value, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdLessThanOrEqualTo(Integer value) {
            addCriterion("map_id <=", value, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdIn(List<Integer> values) {
            addCriterion("map_id in", values, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdNotIn(List<Integer> values) {
            addCriterion("map_id not in", values, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdBetween(Integer value1, Integer value2) {
            addCriterion("map_id between", value1, value2, "mapId");
            return (Criteria) this;
        }

        public Criteria andMapIdNotBetween(Integer value1, Integer value2) {
            addCriterion("map_id not between", value1, value2, "mapId");
            return (Criteria) this;
        }

        public Criteria andHuxingDataIsNull() {
            addCriterion("huxing_data is null");
            return (Criteria) this;
        }

        public Criteria andHuxingDataIsNotNull() {
            addCriterion("huxing_data is not null");
            return (Criteria) this;
        }

        public Criteria andHuxingDataEqualTo(String value) {
            addCriterion("huxing_data =", value, "huxingData");
            return (Criteria) this;
        }

        public Criteria andHuxingDataNotEqualTo(String value) {
            addCriterion("huxing_data <>", value, "huxingData");
            return (Criteria) this;
        }

        public Criteria andHuxingDataGreaterThan(String value) {
            addCriterion("huxing_data >", value, "huxingData");
            return (Criteria) this;
        }

        public Criteria andHuxingDataGreaterThanOrEqualTo(String value) {
            addCriterion("huxing_data >=", value, "huxingData");
            return (Criteria) this;
        }

        public Criteria andHuxingDataLessThan(String value) {
            addCriterion("huxing_data <", value, "huxingData");
            return (Criteria) this;
        }

        public Criteria andHuxingDataLessThanOrEqualTo(String value) {
            addCriterion("huxing_data <=", value, "huxingData");
            return (Criteria) this;
        }

        public Criteria andHuxingDataLike(String value) {
            addCriterion("huxing_data like", value, "huxingData");
            return (Criteria) this;
        }

        public Criteria andHuxingDataNotLike(String value) {
            addCriterion("huxing_data not like", value, "huxingData");
            return (Criteria) this;
        }

        public Criteria andHuxingDataIn(List<String> values) {
            addCriterion("huxing_data in", values, "huxingData");
            return (Criteria) this;
        }

        public Criteria andHuxingDataNotIn(List<String> values) {
            addCriterion("huxing_data not in", values, "huxingData");
            return (Criteria) this;
        }

        public Criteria andHuxingDataBetween(String value1, String value2) {
            addCriterion("huxing_data between", value1, value2, "huxingData");
            return (Criteria) this;
        }

        public Criteria andHuxingDataNotBetween(String value1, String value2) {
            addCriterion("huxing_data not between", value1, value2, "huxingData");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdIsNull() {
            addCriterion("relevance_id is null");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdIsNotNull() {
            addCriterion("relevance_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdEqualTo(Integer value) {
            addCriterion("relevance_id =", value, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdNotEqualTo(Integer value) {
            addCriterion("relevance_id <>", value, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdGreaterThan(Integer value) {
            addCriterion("relevance_id >", value, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("relevance_id >=", value, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdLessThan(Integer value) {
            addCriterion("relevance_id <", value, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdLessThanOrEqualTo(Integer value) {
            addCriterion("relevance_id <=", value, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdIn(List<Integer> values) {
            addCriterion("relevance_id in", values, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdNotIn(List<Integer> values) {
            addCriterion("relevance_id not in", values, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdBetween(Integer value1, Integer value2) {
            addCriterion("relevance_id between", value1, value2, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("relevance_id not between", value1, value2, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdIsNull() {
            addCriterion("display_case_id is null");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdIsNotNull() {
            addCriterion("display_case_id is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdEqualTo(Integer value) {
            addCriterion("display_case_id =", value, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdNotEqualTo(Integer value) {
            addCriterion("display_case_id <>", value, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdGreaterThan(Integer value) {
            addCriterion("display_case_id >", value, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("display_case_id >=", value, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdLessThan(Integer value) {
            addCriterion("display_case_id <", value, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdLessThanOrEqualTo(Integer value) {
            addCriterion("display_case_id <=", value, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdIn(List<Integer> values) {
            addCriterion("display_case_id in", values, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdNotIn(List<Integer> values) {
            addCriterion("display_case_id not in", values, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdBetween(Integer value1, Integer value2) {
            addCriterion("display_case_id between", value1, value2, "displayCaseId");
            return (Criteria) this;
        }

        public Criteria andDisplayCaseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("display_case_id not between", value1, value2, "displayCaseId");
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

        public Criteria andBisCaseIsNull() {
            addCriterion("bis_case is null");
            return (Criteria) this;
        }

        public Criteria andBisCaseIsNotNull() {
            addCriterion("bis_case is not null");
            return (Criteria) this;
        }

        public Criteria andBisCaseEqualTo(Boolean value) {
            addCriterion("bis_case =", value, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseNotEqualTo(Boolean value) {
            addCriterion("bis_case <>", value, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseGreaterThan(Boolean value) {
            addCriterion("bis_case >", value, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_case >=", value, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseLessThan(Boolean value) {
            addCriterion("bis_case <", value, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_case <=", value, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseIn(List<Boolean> values) {
            addCriterion("bis_case in", values, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseNotIn(List<Boolean> values) {
            addCriterion("bis_case not in", values, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_case between", value1, value2, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisCaseNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_case not between", value1, value2, "bisCase");
            return (Criteria) this;
        }

        public Criteria andBisEnableIsNull() {
            addCriterion("bis_enable is null");
            return (Criteria) this;
        }

        public Criteria andBisEnableIsNotNull() {
            addCriterion("bis_enable is not null");
            return (Criteria) this;
        }

        public Criteria andBisEnableEqualTo(Boolean value) {
            addCriterion("bis_enable =", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableNotEqualTo(Boolean value) {
            addCriterion("bis_enable <>", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableGreaterThan(Boolean value) {
            addCriterion("bis_enable >", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_enable >=", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableLessThan(Boolean value) {
            addCriterion("bis_enable <", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_enable <=", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableIn(List<Boolean> values) {
            addCriterion("bis_enable in", values, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableNotIn(List<Boolean> values) {
            addCriterion("bis_enable not in", values, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_enable between", value1, value2, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_enable not between", value1, value2, "bisEnable");
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