package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ToolMapHandleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ToolMapHandleExample() {
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

        public Criteria andLatIsNull() {
            addCriterion("lat is null");
            return (Criteria) this;
        }

        public Criteria andLatIsNotNull() {
            addCriterion("lat is not null");
            return (Criteria) this;
        }

        public Criteria andLatEqualTo(BigDecimal value) {
            addCriterion("lat =", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotEqualTo(BigDecimal value) {
            addCriterion("lat <>", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThan(BigDecimal value) {
            addCriterion("lat >", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("lat >=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThan(BigDecimal value) {
            addCriterion("lat <", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThanOrEqualTo(BigDecimal value) {
            addCriterion("lat <=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatIn(List<BigDecimal> values) {
            addCriterion("lat in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotIn(List<BigDecimal> values) {
            addCriterion("lat not in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lat between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lat not between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andViewModeIsNull() {
            addCriterion("view_mode is null");
            return (Criteria) this;
        }

        public Criteria andViewModeIsNotNull() {
            addCriterion("view_mode is not null");
            return (Criteria) this;
        }

        public Criteria andViewModeEqualTo(String value) {
            addCriterion("view_mode =", value, "viewMode");
            return (Criteria) this;
        }

        public Criteria andViewModeNotEqualTo(String value) {
            addCriterion("view_mode <>", value, "viewMode");
            return (Criteria) this;
        }

        public Criteria andViewModeGreaterThan(String value) {
            addCriterion("view_mode >", value, "viewMode");
            return (Criteria) this;
        }

        public Criteria andViewModeGreaterThanOrEqualTo(String value) {
            addCriterion("view_mode >=", value, "viewMode");
            return (Criteria) this;
        }

        public Criteria andViewModeLessThan(String value) {
            addCriterion("view_mode <", value, "viewMode");
            return (Criteria) this;
        }

        public Criteria andViewModeLessThanOrEqualTo(String value) {
            addCriterion("view_mode <=", value, "viewMode");
            return (Criteria) this;
        }

        public Criteria andViewModeLike(String value) {
            addCriterion("view_mode like", value, "viewMode");
            return (Criteria) this;
        }

        public Criteria andViewModeNotLike(String value) {
            addCriterion("view_mode not like", value, "viewMode");
            return (Criteria) this;
        }

        public Criteria andViewModeIn(List<String> values) {
            addCriterion("view_mode in", values, "viewMode");
            return (Criteria) this;
        }

        public Criteria andViewModeNotIn(List<String> values) {
            addCriterion("view_mode not in", values, "viewMode");
            return (Criteria) this;
        }

        public Criteria andViewModeBetween(String value1, String value2) {
            addCriterion("view_mode between", value1, value2, "viewMode");
            return (Criteria) this;
        }

        public Criteria andViewModeNotBetween(String value1, String value2) {
            addCriterion("view_mode not between", value1, value2, "viewMode");
            return (Criteria) this;
        }

        public Criteria andRadiusIsNull() {
            addCriterion("radius is null");
            return (Criteria) this;
        }

        public Criteria andRadiusIsNotNull() {
            addCriterion("radius is not null");
            return (Criteria) this;
        }

        public Criteria andRadiusEqualTo(BigDecimal value) {
            addCriterion("radius =", value, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusNotEqualTo(BigDecimal value) {
            addCriterion("radius <>", value, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusGreaterThan(BigDecimal value) {
            addCriterion("radius >", value, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("radius >=", value, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusLessThan(BigDecimal value) {
            addCriterion("radius <", value, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusLessThanOrEqualTo(BigDecimal value) {
            addCriterion("radius <=", value, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusIn(List<BigDecimal> values) {
            addCriterion("radius in", values, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusNotIn(List<BigDecimal> values) {
            addCriterion("radius not in", values, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("radius between", value1, value2, "radius");
            return (Criteria) this;
        }

        public Criteria andRadiusNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("radius not between", value1, value2, "radius");
            return (Criteria) this;
        }

        public Criteria andLngIsNull() {
            addCriterion("lng is null");
            return (Criteria) this;
        }

        public Criteria andLngIsNotNull() {
            addCriterion("lng is not null");
            return (Criteria) this;
        }

        public Criteria andLngEqualTo(BigDecimal value) {
            addCriterion("lng =", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotEqualTo(BigDecimal value) {
            addCriterion("lng <>", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThan(BigDecimal value) {
            addCriterion("lng >", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("lng >=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThan(BigDecimal value) {
            addCriterion("lng <", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThanOrEqualTo(BigDecimal value) {
            addCriterion("lng <=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngIn(List<BigDecimal> values) {
            addCriterion("lng in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotIn(List<BigDecimal> values) {
            addCriterion("lng not in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lng between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lng not between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andZoomIsNull() {
            addCriterion("zoom is null");
            return (Criteria) this;
        }

        public Criteria andZoomIsNotNull() {
            addCriterion("zoom is not null");
            return (Criteria) this;
        }

        public Criteria andZoomEqualTo(Integer value) {
            addCriterion("zoom =", value, "zoom");
            return (Criteria) this;
        }

        public Criteria andZoomNotEqualTo(Integer value) {
            addCriterion("zoom <>", value, "zoom");
            return (Criteria) this;
        }

        public Criteria andZoomGreaterThan(Integer value) {
            addCriterion("zoom >", value, "zoom");
            return (Criteria) this;
        }

        public Criteria andZoomGreaterThanOrEqualTo(Integer value) {
            addCriterion("zoom >=", value, "zoom");
            return (Criteria) this;
        }

        public Criteria andZoomLessThan(Integer value) {
            addCriterion("zoom <", value, "zoom");
            return (Criteria) this;
        }

        public Criteria andZoomLessThanOrEqualTo(Integer value) {
            addCriterion("zoom <=", value, "zoom");
            return (Criteria) this;
        }

        public Criteria andZoomIn(List<Integer> values) {
            addCriterion("zoom in", values, "zoom");
            return (Criteria) this;
        }

        public Criteria andZoomNotIn(List<Integer> values) {
            addCriterion("zoom not in", values, "zoom");
            return (Criteria) this;
        }

        public Criteria andZoomBetween(Integer value1, Integer value2) {
            addCriterion("zoom between", value1, value2, "zoom");
            return (Criteria) this;
        }

        public Criteria andZoomNotBetween(Integer value1, Integer value2) {
            addCriterion("zoom not between", value1, value2, "zoom");
            return (Criteria) this;
        }

        public Criteria andDegIsNull() {
            addCriterion("deg is null");
            return (Criteria) this;
        }

        public Criteria andDegIsNotNull() {
            addCriterion("deg is not null");
            return (Criteria) this;
        }

        public Criteria andDegEqualTo(BigDecimal value) {
            addCriterion("deg =", value, "deg");
            return (Criteria) this;
        }

        public Criteria andDegNotEqualTo(BigDecimal value) {
            addCriterion("deg <>", value, "deg");
            return (Criteria) this;
        }

        public Criteria andDegGreaterThan(BigDecimal value) {
            addCriterion("deg >", value, "deg");
            return (Criteria) this;
        }

        public Criteria andDegGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deg >=", value, "deg");
            return (Criteria) this;
        }

        public Criteria andDegLessThan(BigDecimal value) {
            addCriterion("deg <", value, "deg");
            return (Criteria) this;
        }

        public Criteria andDegLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deg <=", value, "deg");
            return (Criteria) this;
        }

        public Criteria andDegIn(List<BigDecimal> values) {
            addCriterion("deg in", values, "deg");
            return (Criteria) this;
        }

        public Criteria andDegNotIn(List<BigDecimal> values) {
            addCriterion("deg not in", values, "deg");
            return (Criteria) this;
        }

        public Criteria andDegBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deg between", value1, value2, "deg");
            return (Criteria) this;
        }

        public Criteria andDegNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deg not between", value1, value2, "deg");
            return (Criteria) this;
        }

        public Criteria andCenterLatIsNull() {
            addCriterion("center_lat is null");
            return (Criteria) this;
        }

        public Criteria andCenterLatIsNotNull() {
            addCriterion("center_lat is not null");
            return (Criteria) this;
        }

        public Criteria andCenterLatEqualTo(BigDecimal value) {
            addCriterion("center_lat =", value, "centerLat");
            return (Criteria) this;
        }

        public Criteria andCenterLatNotEqualTo(BigDecimal value) {
            addCriterion("center_lat <>", value, "centerLat");
            return (Criteria) this;
        }

        public Criteria andCenterLatGreaterThan(BigDecimal value) {
            addCriterion("center_lat >", value, "centerLat");
            return (Criteria) this;
        }

        public Criteria andCenterLatGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("center_lat >=", value, "centerLat");
            return (Criteria) this;
        }

        public Criteria andCenterLatLessThan(BigDecimal value) {
            addCriterion("center_lat <", value, "centerLat");
            return (Criteria) this;
        }

        public Criteria andCenterLatLessThanOrEqualTo(BigDecimal value) {
            addCriterion("center_lat <=", value, "centerLat");
            return (Criteria) this;
        }

        public Criteria andCenterLatIn(List<BigDecimal> values) {
            addCriterion("center_lat in", values, "centerLat");
            return (Criteria) this;
        }

        public Criteria andCenterLatNotIn(List<BigDecimal> values) {
            addCriterion("center_lat not in", values, "centerLat");
            return (Criteria) this;
        }

        public Criteria andCenterLatBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("center_lat between", value1, value2, "centerLat");
            return (Criteria) this;
        }

        public Criteria andCenterLatNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("center_lat not between", value1, value2, "centerLat");
            return (Criteria) this;
        }

        public Criteria andCenterLngIsNull() {
            addCriterion("center_lng is null");
            return (Criteria) this;
        }

        public Criteria andCenterLngIsNotNull() {
            addCriterion("center_lng is not null");
            return (Criteria) this;
        }

        public Criteria andCenterLngEqualTo(BigDecimal value) {
            addCriterion("center_lng =", value, "centerLng");
            return (Criteria) this;
        }

        public Criteria andCenterLngNotEqualTo(BigDecimal value) {
            addCriterion("center_lng <>", value, "centerLng");
            return (Criteria) this;
        }

        public Criteria andCenterLngGreaterThan(BigDecimal value) {
            addCriterion("center_lng >", value, "centerLng");
            return (Criteria) this;
        }

        public Criteria andCenterLngGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("center_lng >=", value, "centerLng");
            return (Criteria) this;
        }

        public Criteria andCenterLngLessThan(BigDecimal value) {
            addCriterion("center_lng <", value, "centerLng");
            return (Criteria) this;
        }

        public Criteria andCenterLngLessThanOrEqualTo(BigDecimal value) {
            addCriterion("center_lng <=", value, "centerLng");
            return (Criteria) this;
        }

        public Criteria andCenterLngIn(List<BigDecimal> values) {
            addCriterion("center_lng in", values, "centerLng");
            return (Criteria) this;
        }

        public Criteria andCenterLngNotIn(List<BigDecimal> values) {
            addCriterion("center_lng not in", values, "centerLng");
            return (Criteria) this;
        }

        public Criteria andCenterLngBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("center_lng between", value1, value2, "centerLng");
            return (Criteria) this;
        }

        public Criteria andCenterLngNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("center_lng not between", value1, value2, "centerLng");
            return (Criteria) this;
        }

        public Criteria andInstantaneousLifeDataIsNull() {
            addCriterion("instantaneous_life_data is null");
            return (Criteria) this;
        }

        public Criteria andInstantaneousLifeDataIsNotNull() {
            addCriterion("instantaneous_life_data is not null");
            return (Criteria) this;
        }

        public Criteria andInstantaneousLifeDataEqualTo(String value) {
            addCriterion("instantaneous_life_data =", value, "instantaneousLifeData");
            return (Criteria) this;
        }

        public Criteria andInstantaneousLifeDataNotEqualTo(String value) {
            addCriterion("instantaneous_life_data <>", value, "instantaneousLifeData");
            return (Criteria) this;
        }

        public Criteria andInstantaneousLifeDataGreaterThan(String value) {
            addCriterion("instantaneous_life_data >", value, "instantaneousLifeData");
            return (Criteria) this;
        }

        public Criteria andInstantaneousLifeDataGreaterThanOrEqualTo(String value) {
            addCriterion("instantaneous_life_data >=", value, "instantaneousLifeData");
            return (Criteria) this;
        }

        public Criteria andInstantaneousLifeDataLessThan(String value) {
            addCriterion("instantaneous_life_data <", value, "instantaneousLifeData");
            return (Criteria) this;
        }

        public Criteria andInstantaneousLifeDataLessThanOrEqualTo(String value) {
            addCriterion("instantaneous_life_data <=", value, "instantaneousLifeData");
            return (Criteria) this;
        }

        public Criteria andInstantaneousLifeDataLike(String value) {
            addCriterion("instantaneous_life_data like", value, "instantaneousLifeData");
            return (Criteria) this;
        }

        public Criteria andInstantaneousLifeDataNotLike(String value) {
            addCriterion("instantaneous_life_data not like", value, "instantaneousLifeData");
            return (Criteria) this;
        }

        public Criteria andInstantaneousLifeDataIn(List<String> values) {
            addCriterion("instantaneous_life_data in", values, "instantaneousLifeData");
            return (Criteria) this;
        }

        public Criteria andInstantaneousLifeDataNotIn(List<String> values) {
            addCriterion("instantaneous_life_data not in", values, "instantaneousLifeData");
            return (Criteria) this;
        }

        public Criteria andInstantaneousLifeDataBetween(String value1, String value2) {
            addCriterion("instantaneous_life_data between", value1, value2, "instantaneousLifeData");
            return (Criteria) this;
        }

        public Criteria andInstantaneousLifeDataNotBetween(String value1, String value2) {
            addCriterion("instantaneous_life_data not between", value1, value2, "instantaneousLifeData");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdIsNull() {
            addCriterion("attachment_id is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdIsNotNull() {
            addCriterion("attachment_id is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdEqualTo(Integer value) {
            addCriterion("attachment_id =", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdNotEqualTo(Integer value) {
            addCriterion("attachment_id <>", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdGreaterThan(Integer value) {
            addCriterion("attachment_id >", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("attachment_id >=", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdLessThan(Integer value) {
            addCriterion("attachment_id <", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("attachment_id <=", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdIn(List<Integer> values) {
            addCriterion("attachment_id in", values, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdNotIn(List<Integer> values) {
            addCriterion("attachment_id not in", values, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdBetween(Integer value1, Integer value2) {
            addCriterion("attachment_id between", value1, value2, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("attachment_id not between", value1, value2, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andDrawStateIsNull() {
            addCriterion("draw_state is null");
            return (Criteria) this;
        }

        public Criteria andDrawStateIsNotNull() {
            addCriterion("draw_state is not null");
            return (Criteria) this;
        }

        public Criteria andDrawStateEqualTo(String value) {
            addCriterion("draw_state =", value, "drawState");
            return (Criteria) this;
        }

        public Criteria andDrawStateNotEqualTo(String value) {
            addCriterion("draw_state <>", value, "drawState");
            return (Criteria) this;
        }

        public Criteria andDrawStateGreaterThan(String value) {
            addCriterion("draw_state >", value, "drawState");
            return (Criteria) this;
        }

        public Criteria andDrawStateGreaterThanOrEqualTo(String value) {
            addCriterion("draw_state >=", value, "drawState");
            return (Criteria) this;
        }

        public Criteria andDrawStateLessThan(String value) {
            addCriterion("draw_state <", value, "drawState");
            return (Criteria) this;
        }

        public Criteria andDrawStateLessThanOrEqualTo(String value) {
            addCriterion("draw_state <=", value, "drawState");
            return (Criteria) this;
        }

        public Criteria andDrawStateLike(String value) {
            addCriterion("draw_state like", value, "drawState");
            return (Criteria) this;
        }

        public Criteria andDrawStateNotLike(String value) {
            addCriterion("draw_state not like", value, "drawState");
            return (Criteria) this;
        }

        public Criteria andDrawStateIn(List<String> values) {
            addCriterion("draw_state in", values, "drawState");
            return (Criteria) this;
        }

        public Criteria andDrawStateNotIn(List<String> values) {
            addCriterion("draw_state not in", values, "drawState");
            return (Criteria) this;
        }

        public Criteria andDrawStateBetween(String value1, String value2) {
            addCriterion("draw_state between", value1, value2, "drawState");
            return (Criteria) this;
        }

        public Criteria andDrawStateNotBetween(String value1, String value2) {
            addCriterion("draw_state not between", value1, value2, "drawState");
            return (Criteria) this;
        }

        public Criteria andViewStateIsNull() {
            addCriterion("view_state is null");
            return (Criteria) this;
        }

        public Criteria andViewStateIsNotNull() {
            addCriterion("view_state is not null");
            return (Criteria) this;
        }

        public Criteria andViewStateEqualTo(String value) {
            addCriterion("view_state =", value, "viewState");
            return (Criteria) this;
        }

        public Criteria andViewStateNotEqualTo(String value) {
            addCriterion("view_state <>", value, "viewState");
            return (Criteria) this;
        }

        public Criteria andViewStateGreaterThan(String value) {
            addCriterion("view_state >", value, "viewState");
            return (Criteria) this;
        }

        public Criteria andViewStateGreaterThanOrEqualTo(String value) {
            addCriterion("view_state >=", value, "viewState");
            return (Criteria) this;
        }

        public Criteria andViewStateLessThan(String value) {
            addCriterion("view_state <", value, "viewState");
            return (Criteria) this;
        }

        public Criteria andViewStateLessThanOrEqualTo(String value) {
            addCriterion("view_state <=", value, "viewState");
            return (Criteria) this;
        }

        public Criteria andViewStateLike(String value) {
            addCriterion("view_state like", value, "viewState");
            return (Criteria) this;
        }

        public Criteria andViewStateNotLike(String value) {
            addCriterion("view_state not like", value, "viewState");
            return (Criteria) this;
        }

        public Criteria andViewStateIn(List<String> values) {
            addCriterion("view_state in", values, "viewState");
            return (Criteria) this;
        }

        public Criteria andViewStateNotIn(List<String> values) {
            addCriterion("view_state not in", values, "viewState");
            return (Criteria) this;
        }

        public Criteria andViewStateBetween(String value1, String value2) {
            addCriterion("view_state between", value1, value2, "viewState");
            return (Criteria) this;
        }

        public Criteria andViewStateNotBetween(String value1, String value2) {
            addCriterion("view_state not between", value1, value2, "viewState");
            return (Criteria) this;
        }

        public Criteria andStorageStateIsNull() {
            addCriterion("storage_state is null");
            return (Criteria) this;
        }

        public Criteria andStorageStateIsNotNull() {
            addCriterion("storage_state is not null");
            return (Criteria) this;
        }

        public Criteria andStorageStateEqualTo(String value) {
            addCriterion("storage_state =", value, "storageState");
            return (Criteria) this;
        }

        public Criteria andStorageStateNotEqualTo(String value) {
            addCriterion("storage_state <>", value, "storageState");
            return (Criteria) this;
        }

        public Criteria andStorageStateGreaterThan(String value) {
            addCriterion("storage_state >", value, "storageState");
            return (Criteria) this;
        }

        public Criteria andStorageStateGreaterThanOrEqualTo(String value) {
            addCriterion("storage_state >=", value, "storageState");
            return (Criteria) this;
        }

        public Criteria andStorageStateLessThan(String value) {
            addCriterion("storage_state <", value, "storageState");
            return (Criteria) this;
        }

        public Criteria andStorageStateLessThanOrEqualTo(String value) {
            addCriterion("storage_state <=", value, "storageState");
            return (Criteria) this;
        }

        public Criteria andStorageStateLike(String value) {
            addCriterion("storage_state like", value, "storageState");
            return (Criteria) this;
        }

        public Criteria andStorageStateNotLike(String value) {
            addCriterion("storage_state not like", value, "storageState");
            return (Criteria) this;
        }

        public Criteria andStorageStateIn(List<String> values) {
            addCriterion("storage_state in", values, "storageState");
            return (Criteria) this;
        }

        public Criteria andStorageStateNotIn(List<String> values) {
            addCriterion("storage_state not in", values, "storageState");
            return (Criteria) this;
        }

        public Criteria andStorageStateBetween(String value1, String value2) {
            addCriterion("storage_state between", value1, value2, "storageState");
            return (Criteria) this;
        }

        public Criteria andStorageStateNotBetween(String value1, String value2) {
            addCriterion("storage_state not between", value1, value2, "storageState");
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

        public Criteria andTableIdIsNull() {
            addCriterion("table_id is null");
            return (Criteria) this;
        }

        public Criteria andTableIdIsNotNull() {
            addCriterion("table_id is not null");
            return (Criteria) this;
        }

        public Criteria andTableIdEqualTo(Integer value) {
            addCriterion("table_id =", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotEqualTo(Integer value) {
            addCriterion("table_id <>", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdGreaterThan(Integer value) {
            addCriterion("table_id >", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("table_id >=", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLessThan(Integer value) {
            addCriterion("table_id <", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLessThanOrEqualTo(Integer value) {
            addCriterion("table_id <=", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdIn(List<Integer> values) {
            addCriterion("table_id in", values, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotIn(List<Integer> values) {
            addCriterion("table_id not in", values, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdBetween(Integer value1, Integer value2) {
            addCriterion("table_id between", value1, value2, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("table_id not between", value1, value2, "tableId");
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