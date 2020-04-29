package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataLandLevelDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataLandLevelDetailExample() {
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

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
            addCriterion("pid not between", value1, value2, "pid");
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

        public Criteria andLandLevelIdIsNull() {
            addCriterion("land_level_id is null");
            return (Criteria) this;
        }

        public Criteria andLandLevelIdIsNotNull() {
            addCriterion("land_level_id is not null");
            return (Criteria) this;
        }

        public Criteria andLandLevelIdEqualTo(Integer value) {
            addCriterion("land_level_id =", value, "landLevelId");
            return (Criteria) this;
        }

        public Criteria andLandLevelIdNotEqualTo(Integer value) {
            addCriterion("land_level_id <>", value, "landLevelId");
            return (Criteria) this;
        }

        public Criteria andLandLevelIdGreaterThan(Integer value) {
            addCriterion("land_level_id >", value, "landLevelId");
            return (Criteria) this;
        }

        public Criteria andLandLevelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("land_level_id >=", value, "landLevelId");
            return (Criteria) this;
        }

        public Criteria andLandLevelIdLessThan(Integer value) {
            addCriterion("land_level_id <", value, "landLevelId");
            return (Criteria) this;
        }

        public Criteria andLandLevelIdLessThanOrEqualTo(Integer value) {
            addCriterion("land_level_id <=", value, "landLevelId");
            return (Criteria) this;
        }

        public Criteria andLandLevelIdIn(List<Integer> values) {
            addCriterion("land_level_id in", values, "landLevelId");
            return (Criteria) this;
        }

        public Criteria andLandLevelIdNotIn(List<Integer> values) {
            addCriterion("land_level_id not in", values, "landLevelId");
            return (Criteria) this;
        }

        public Criteria andLandLevelIdBetween(Integer value1, Integer value2) {
            addCriterion("land_level_id between", value1, value2, "landLevelId");
            return (Criteria) this;
        }

        public Criteria andLandLevelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("land_level_id not between", value1, value2, "landLevelId");
            return (Criteria) this;
        }

        public Criteria andClassifyIsNull() {
            addCriterion("classify is null");
            return (Criteria) this;
        }

        public Criteria andClassifyIsNotNull() {
            addCriterion("classify is not null");
            return (Criteria) this;
        }

        public Criteria andClassifyEqualTo(String value) {
            addCriterion("classify =", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotEqualTo(String value) {
            addCriterion("classify <>", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyGreaterThan(String value) {
            addCriterion("classify >", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyGreaterThanOrEqualTo(String value) {
            addCriterion("classify >=", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyLessThan(String value) {
            addCriterion("classify <", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyLessThanOrEqualTo(String value) {
            addCriterion("classify <=", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyLike(String value) {
            addCriterion("classify like", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotLike(String value) {
            addCriterion("classify not like", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyIn(List<String> values) {
            addCriterion("classify in", values, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotIn(List<String> values) {
            addCriterion("classify not in", values, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyBetween(String value1, String value2) {
            addCriterion("classify between", value1, value2, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotBetween(String value1, String value2) {
            addCriterion("classify not between", value1, value2, "classify");
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

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
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

        public Criteria andMuPriceIsNull() {
            addCriterion("mu_price is null");
            return (Criteria) this;
        }

        public Criteria andMuPriceIsNotNull() {
            addCriterion("mu_price is not null");
            return (Criteria) this;
        }

        public Criteria andMuPriceEqualTo(BigDecimal value) {
            addCriterion("mu_price =", value, "muPrice");
            return (Criteria) this;
        }

        public Criteria andMuPriceNotEqualTo(BigDecimal value) {
            addCriterion("mu_price <>", value, "muPrice");
            return (Criteria) this;
        }

        public Criteria andMuPriceGreaterThan(BigDecimal value) {
            addCriterion("mu_price >", value, "muPrice");
            return (Criteria) this;
        }

        public Criteria andMuPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("mu_price >=", value, "muPrice");
            return (Criteria) this;
        }

        public Criteria andMuPriceLessThan(BigDecimal value) {
            addCriterion("mu_price <", value, "muPrice");
            return (Criteria) this;
        }

        public Criteria andMuPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("mu_price <=", value, "muPrice");
            return (Criteria) this;
        }

        public Criteria andMuPriceIn(List<BigDecimal> values) {
            addCriterion("mu_price in", values, "muPrice");
            return (Criteria) this;
        }

        public Criteria andMuPriceNotIn(List<BigDecimal> values) {
            addCriterion("mu_price not in", values, "muPrice");
            return (Criteria) this;
        }

        public Criteria andMuPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mu_price between", value1, value2, "muPrice");
            return (Criteria) this;
        }

        public Criteria andMuPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mu_price not between", value1, value2, "muPrice");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionProportionIsNull() {
            addCriterion("land_acquisition_proportion is null");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionProportionIsNotNull() {
            addCriterion("land_acquisition_proportion is not null");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionProportionEqualTo(String value) {
            addCriterion("land_acquisition_proportion =", value, "landAcquisitionProportion");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionProportionNotEqualTo(String value) {
            addCriterion("land_acquisition_proportion <>", value, "landAcquisitionProportion");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionProportionGreaterThan(String value) {
            addCriterion("land_acquisition_proportion >", value, "landAcquisitionProportion");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionProportionGreaterThanOrEqualTo(String value) {
            addCriterion("land_acquisition_proportion >=", value, "landAcquisitionProportion");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionProportionLessThan(String value) {
            addCriterion("land_acquisition_proportion <", value, "landAcquisitionProportion");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionProportionLessThanOrEqualTo(String value) {
            addCriterion("land_acquisition_proportion <=", value, "landAcquisitionProportion");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionProportionLike(String value) {
            addCriterion("land_acquisition_proportion like", value, "landAcquisitionProportion");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionProportionNotLike(String value) {
            addCriterion("land_acquisition_proportion not like", value, "landAcquisitionProportion");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionProportionIn(List<String> values) {
            addCriterion("land_acquisition_proportion in", values, "landAcquisitionProportion");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionProportionNotIn(List<String> values) {
            addCriterion("land_acquisition_proportion not in", values, "landAcquisitionProportion");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionProportionBetween(String value1, String value2) {
            addCriterion("land_acquisition_proportion between", value1, value2, "landAcquisitionProportion");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionProportionNotBetween(String value1, String value2) {
            addCriterion("land_acquisition_proportion not between", value1, value2, "landAcquisitionProportion");
            return (Criteria) this;
        }

        public Criteria andVolumeRateIsNull() {
            addCriterion("volume_rate is null");
            return (Criteria) this;
        }

        public Criteria andVolumeRateIsNotNull() {
            addCriterion("volume_rate is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeRateEqualTo(BigDecimal value) {
            addCriterion("volume_rate =", value, "volumeRate");
            return (Criteria) this;
        }

        public Criteria andVolumeRateNotEqualTo(BigDecimal value) {
            addCriterion("volume_rate <>", value, "volumeRate");
            return (Criteria) this;
        }

        public Criteria andVolumeRateGreaterThan(BigDecimal value) {
            addCriterion("volume_rate >", value, "volumeRate");
            return (Criteria) this;
        }

        public Criteria andVolumeRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("volume_rate >=", value, "volumeRate");
            return (Criteria) this;
        }

        public Criteria andVolumeRateLessThan(BigDecimal value) {
            addCriterion("volume_rate <", value, "volumeRate");
            return (Criteria) this;
        }

        public Criteria andVolumeRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("volume_rate <=", value, "volumeRate");
            return (Criteria) this;
        }

        public Criteria andVolumeRateIn(List<BigDecimal> values) {
            addCriterion("volume_rate in", values, "volumeRate");
            return (Criteria) this;
        }

        public Criteria andVolumeRateNotIn(List<BigDecimal> values) {
            addCriterion("volume_rate not in", values, "volumeRate");
            return (Criteria) this;
        }

        public Criteria andVolumeRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("volume_rate between", value1, value2, "volumeRate");
            return (Criteria) this;
        }

        public Criteria andVolumeRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("volume_rate not between", value1, value2, "volumeRate");
            return (Criteria) this;
        }

        public Criteria andLegalAgeIsNull() {
            addCriterion("legal_age is null");
            return (Criteria) this;
        }

        public Criteria andLegalAgeIsNotNull() {
            addCriterion("legal_age is not null");
            return (Criteria) this;
        }

        public Criteria andLegalAgeEqualTo(BigDecimal value) {
            addCriterion("legal_age =", value, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeNotEqualTo(BigDecimal value) {
            addCriterion("legal_age <>", value, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeGreaterThan(BigDecimal value) {
            addCriterion("legal_age >", value, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("legal_age >=", value, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeLessThan(BigDecimal value) {
            addCriterion("legal_age <", value, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("legal_age <=", value, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeIn(List<BigDecimal> values) {
            addCriterion("legal_age in", values, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeNotIn(List<BigDecimal> values) {
            addCriterion("legal_age not in", values, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("legal_age between", value1, value2, "legalAge");
            return (Criteria) this;
        }

        public Criteria andLegalAgeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("legal_age not between", value1, value2, "legalAge");
            return (Criteria) this;
        }

        public Criteria andMainStreetIsNull() {
            addCriterion("main_street is null");
            return (Criteria) this;
        }

        public Criteria andMainStreetIsNotNull() {
            addCriterion("main_street is not null");
            return (Criteria) this;
        }

        public Criteria andMainStreetEqualTo(String value) {
            addCriterion("main_street =", value, "mainStreet");
            return (Criteria) this;
        }

        public Criteria andMainStreetNotEqualTo(String value) {
            addCriterion("main_street <>", value, "mainStreet");
            return (Criteria) this;
        }

        public Criteria andMainStreetGreaterThan(String value) {
            addCriterion("main_street >", value, "mainStreet");
            return (Criteria) this;
        }

        public Criteria andMainStreetGreaterThanOrEqualTo(String value) {
            addCriterion("main_street >=", value, "mainStreet");
            return (Criteria) this;
        }

        public Criteria andMainStreetLessThan(String value) {
            addCriterion("main_street <", value, "mainStreet");
            return (Criteria) this;
        }

        public Criteria andMainStreetLessThanOrEqualTo(String value) {
            addCriterion("main_street <=", value, "mainStreet");
            return (Criteria) this;
        }

        public Criteria andMainStreetLike(String value) {
            addCriterion("main_street like", value, "mainStreet");
            return (Criteria) this;
        }

        public Criteria andMainStreetNotLike(String value) {
            addCriterion("main_street not like", value, "mainStreet");
            return (Criteria) this;
        }

        public Criteria andMainStreetIn(List<String> values) {
            addCriterion("main_street in", values, "mainStreet");
            return (Criteria) this;
        }

        public Criteria andMainStreetNotIn(List<String> values) {
            addCriterion("main_street not in", values, "mainStreet");
            return (Criteria) this;
        }

        public Criteria andMainStreetBetween(String value1, String value2) {
            addCriterion("main_street between", value1, value2, "mainStreet");
            return (Criteria) this;
        }

        public Criteria andMainStreetNotBetween(String value1, String value2) {
            addCriterion("main_street not between", value1, value2, "mainStreet");
            return (Criteria) this;
        }

        public Criteria andLevelRangeIsNull() {
            addCriterion("level_range is null");
            return (Criteria) this;
        }

        public Criteria andLevelRangeIsNotNull() {
            addCriterion("level_range is not null");
            return (Criteria) this;
        }

        public Criteria andLevelRangeEqualTo(String value) {
            addCriterion("level_range =", value, "levelRange");
            return (Criteria) this;
        }

        public Criteria andLevelRangeNotEqualTo(String value) {
            addCriterion("level_range <>", value, "levelRange");
            return (Criteria) this;
        }

        public Criteria andLevelRangeGreaterThan(String value) {
            addCriterion("level_range >", value, "levelRange");
            return (Criteria) this;
        }

        public Criteria andLevelRangeGreaterThanOrEqualTo(String value) {
            addCriterion("level_range >=", value, "levelRange");
            return (Criteria) this;
        }

        public Criteria andLevelRangeLessThan(String value) {
            addCriterion("level_range <", value, "levelRange");
            return (Criteria) this;
        }

        public Criteria andLevelRangeLessThanOrEqualTo(String value) {
            addCriterion("level_range <=", value, "levelRange");
            return (Criteria) this;
        }

        public Criteria andLevelRangeLike(String value) {
            addCriterion("level_range like", value, "levelRange");
            return (Criteria) this;
        }

        public Criteria andLevelRangeNotLike(String value) {
            addCriterion("level_range not like", value, "levelRange");
            return (Criteria) this;
        }

        public Criteria andLevelRangeIn(List<String> values) {
            addCriterion("level_range in", values, "levelRange");
            return (Criteria) this;
        }

        public Criteria andLevelRangeNotIn(List<String> values) {
            addCriterion("level_range not in", values, "levelRange");
            return (Criteria) this;
        }

        public Criteria andLevelRangeBetween(String value1, String value2) {
            addCriterion("level_range between", value1, value2, "levelRange");
            return (Criteria) this;
        }

        public Criteria andLevelRangeNotBetween(String value1, String value2) {
            addCriterion("level_range not between", value1, value2, "levelRange");
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