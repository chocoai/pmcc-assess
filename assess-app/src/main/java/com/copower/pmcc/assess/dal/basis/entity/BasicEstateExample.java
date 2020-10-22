package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicEstateExample {
    /**
     * tb_basic_estate
     */
    protected String orderByClause;

    /**
     * tb_basic_estate
     */
    protected boolean distinct;

    /**
     * tb_basic_estate
     */
    protected List<Criteria> oredCriteria;

    public BasicEstateExample() {
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
     * tb_basic_estate
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

        public Criteria andClassifyIsNull() {
            addCriterion("classify is null");
            return (Criteria) this;
        }

        public Criteria andClassifyIsNotNull() {
            addCriterion("classify is not null");
            return (Criteria) this;
        }

        public Criteria andClassifyEqualTo(Integer value) {
            addCriterion("classify =", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotEqualTo(Integer value) {
            addCriterion("classify <>", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyGreaterThan(Integer value) {
            addCriterion("classify >", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyGreaterThanOrEqualTo(Integer value) {
            addCriterion("classify >=", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyLessThan(Integer value) {
            addCriterion("classify <", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyLessThanOrEqualTo(Integer value) {
            addCriterion("classify <=", value, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyIn(List<Integer> values) {
            addCriterion("classify in", values, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotIn(List<Integer> values) {
            addCriterion("classify not in", values, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyBetween(Integer value1, Integer value2) {
            addCriterion("classify between", value1, value2, "classify");
            return (Criteria) this;
        }

        public Criteria andClassifyNotBetween(Integer value1, Integer value2) {
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

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNull() {
            addCriterion("district is null");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNotNull() {
            addCriterion("district is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictEqualTo(String value) {
            addCriterion("district =", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotEqualTo(String value) {
            addCriterion("district <>", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThan(String value) {
            addCriterion("district >", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThanOrEqualTo(String value) {
            addCriterion("district >=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThan(String value) {
            addCriterion("district <", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThanOrEqualTo(String value) {
            addCriterion("district <=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLike(String value) {
            addCriterion("district like", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotLike(String value) {
            addCriterion("district not like", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictIn(List<String> values) {
            addCriterion("district in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotIn(List<String> values) {
            addCriterion("district not in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictBetween(String value1, String value2) {
            addCriterion("district between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotBetween(String value1, String value2) {
            addCriterion("district not between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andBlockIdIsNull() {
            addCriterion("block_id is null");
            return (Criteria) this;
        }

        public Criteria andBlockIdIsNotNull() {
            addCriterion("block_id is not null");
            return (Criteria) this;
        }

        public Criteria andBlockIdEqualTo(Integer value) {
            addCriterion("block_id =", value, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockIdNotEqualTo(Integer value) {
            addCriterion("block_id <>", value, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockIdGreaterThan(Integer value) {
            addCriterion("block_id >", value, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("block_id >=", value, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockIdLessThan(Integer value) {
            addCriterion("block_id <", value, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockIdLessThanOrEqualTo(Integer value) {
            addCriterion("block_id <=", value, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockIdIn(List<Integer> values) {
            addCriterion("block_id in", values, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockIdNotIn(List<Integer> values) {
            addCriterion("block_id not in", values, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockIdBetween(Integer value1, Integer value2) {
            addCriterion("block_id between", value1, value2, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockIdNotBetween(Integer value1, Integer value2) {
            addCriterion("block_id not between", value1, value2, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockNameIsNull() {
            addCriterion("block_name is null");
            return (Criteria) this;
        }

        public Criteria andBlockNameIsNotNull() {
            addCriterion("block_name is not null");
            return (Criteria) this;
        }

        public Criteria andBlockNameEqualTo(String value) {
            addCriterion("block_name =", value, "blockName");
            return (Criteria) this;
        }

        public Criteria andBlockNameNotEqualTo(String value) {
            addCriterion("block_name <>", value, "blockName");
            return (Criteria) this;
        }

        public Criteria andBlockNameGreaterThan(String value) {
            addCriterion("block_name >", value, "blockName");
            return (Criteria) this;
        }

        public Criteria andBlockNameGreaterThanOrEqualTo(String value) {
            addCriterion("block_name >=", value, "blockName");
            return (Criteria) this;
        }

        public Criteria andBlockNameLessThan(String value) {
            addCriterion("block_name <", value, "blockName");
            return (Criteria) this;
        }

        public Criteria andBlockNameLessThanOrEqualTo(String value) {
            addCriterion("block_name <=", value, "blockName");
            return (Criteria) this;
        }

        public Criteria andBlockNameLike(String value) {
            addCriterion("block_name like", value, "blockName");
            return (Criteria) this;
        }

        public Criteria andBlockNameNotLike(String value) {
            addCriterion("block_name not like", value, "blockName");
            return (Criteria) this;
        }

        public Criteria andBlockNameIn(List<String> values) {
            addCriterion("block_name in", values, "blockName");
            return (Criteria) this;
        }

        public Criteria andBlockNameNotIn(List<String> values) {
            addCriterion("block_name not in", values, "blockName");
            return (Criteria) this;
        }

        public Criteria andBlockNameBetween(String value1, String value2) {
            addCriterion("block_name between", value1, value2, "blockName");
            return (Criteria) this;
        }

        public Criteria andBlockNameNotBetween(String value1, String value2) {
            addCriterion("block_name not between", value1, value2, "blockName");
            return (Criteria) this;
        }

        public Criteria andBlockDescriptionIsNull() {
            addCriterion("block_description is null");
            return (Criteria) this;
        }

        public Criteria andBlockDescriptionIsNotNull() {
            addCriterion("block_description is not null");
            return (Criteria) this;
        }

        public Criteria andBlockDescriptionEqualTo(String value) {
            addCriterion("block_description =", value, "blockDescription");
            return (Criteria) this;
        }

        public Criteria andBlockDescriptionNotEqualTo(String value) {
            addCriterion("block_description <>", value, "blockDescription");
            return (Criteria) this;
        }

        public Criteria andBlockDescriptionGreaterThan(String value) {
            addCriterion("block_description >", value, "blockDescription");
            return (Criteria) this;
        }

        public Criteria andBlockDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("block_description >=", value, "blockDescription");
            return (Criteria) this;
        }

        public Criteria andBlockDescriptionLessThan(String value) {
            addCriterion("block_description <", value, "blockDescription");
            return (Criteria) this;
        }

        public Criteria andBlockDescriptionLessThanOrEqualTo(String value) {
            addCriterion("block_description <=", value, "blockDescription");
            return (Criteria) this;
        }

        public Criteria andBlockDescriptionLike(String value) {
            addCriterion("block_description like", value, "blockDescription");
            return (Criteria) this;
        }

        public Criteria andBlockDescriptionNotLike(String value) {
            addCriterion("block_description not like", value, "blockDescription");
            return (Criteria) this;
        }

        public Criteria andBlockDescriptionIn(List<String> values) {
            addCriterion("block_description in", values, "blockDescription");
            return (Criteria) this;
        }

        public Criteria andBlockDescriptionNotIn(List<String> values) {
            addCriterion("block_description not in", values, "blockDescription");
            return (Criteria) this;
        }

        public Criteria andBlockDescriptionBetween(String value1, String value2) {
            addCriterion("block_description between", value1, value2, "blockDescription");
            return (Criteria) this;
        }

        public Criteria andBlockDescriptionNotBetween(String value1, String value2) {
            addCriterion("block_description not between", value1, value2, "blockDescription");
            return (Criteria) this;
        }

        public Criteria andDeveloperIsNull() {
            addCriterion("developer is null");
            return (Criteria) this;
        }

        public Criteria andDeveloperIsNotNull() {
            addCriterion("developer is not null");
            return (Criteria) this;
        }

        public Criteria andDeveloperEqualTo(String value) {
            addCriterion("developer =", value, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperNotEqualTo(String value) {
            addCriterion("developer <>", value, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperGreaterThan(String value) {
            addCriterion("developer >", value, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperGreaterThanOrEqualTo(String value) {
            addCriterion("developer >=", value, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperLessThan(String value) {
            addCriterion("developer <", value, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperLessThanOrEqualTo(String value) {
            addCriterion("developer <=", value, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperLike(String value) {
            addCriterion("developer like", value, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperNotLike(String value) {
            addCriterion("developer not like", value, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperIn(List<String> values) {
            addCriterion("developer in", values, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperNotIn(List<String> values) {
            addCriterion("developer not in", values, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperBetween(String value1, String value2) {
            addCriterion("developer between", value1, value2, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperNotBetween(String value1, String value2) {
            addCriterion("developer not between", value1, value2, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameIsNull() {
            addCriterion("developer_name is null");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameIsNotNull() {
            addCriterion("developer_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameEqualTo(String value) {
            addCriterion("developer_name =", value, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameNotEqualTo(String value) {
            addCriterion("developer_name <>", value, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameGreaterThan(String value) {
            addCriterion("developer_name >", value, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameGreaterThanOrEqualTo(String value) {
            addCriterion("developer_name >=", value, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameLessThan(String value) {
            addCriterion("developer_name <", value, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameLessThanOrEqualTo(String value) {
            addCriterion("developer_name <=", value, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameLike(String value) {
            addCriterion("developer_name like", value, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameNotLike(String value) {
            addCriterion("developer_name not like", value, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameIn(List<String> values) {
            addCriterion("developer_name in", values, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameNotIn(List<String> values) {
            addCriterion("developer_name not in", values, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameBetween(String value1, String value2) {
            addCriterion("developer_name between", value1, value2, "developerName");
            return (Criteria) this;
        }

        public Criteria andDeveloperNameNotBetween(String value1, String value2) {
            addCriterion("developer_name not between", value1, value2, "developerName");
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

        public Criteria andStreetNumberIsNull() {
            addCriterion("street_number is null");
            return (Criteria) this;
        }

        public Criteria andStreetNumberIsNotNull() {
            addCriterion("street_number is not null");
            return (Criteria) this;
        }

        public Criteria andStreetNumberEqualTo(String value) {
            addCriterion("street_number =", value, "streetNumber");
            return (Criteria) this;
        }

        public Criteria andStreetNumberNotEqualTo(String value) {
            addCriterion("street_number <>", value, "streetNumber");
            return (Criteria) this;
        }

        public Criteria andStreetNumberGreaterThan(String value) {
            addCriterion("street_number >", value, "streetNumber");
            return (Criteria) this;
        }

        public Criteria andStreetNumberGreaterThanOrEqualTo(String value) {
            addCriterion("street_number >=", value, "streetNumber");
            return (Criteria) this;
        }

        public Criteria andStreetNumberLessThan(String value) {
            addCriterion("street_number <", value, "streetNumber");
            return (Criteria) this;
        }

        public Criteria andStreetNumberLessThanOrEqualTo(String value) {
            addCriterion("street_number <=", value, "streetNumber");
            return (Criteria) this;
        }

        public Criteria andStreetNumberLike(String value) {
            addCriterion("street_number like", value, "streetNumber");
            return (Criteria) this;
        }

        public Criteria andStreetNumberNotLike(String value) {
            addCriterion("street_number not like", value, "streetNumber");
            return (Criteria) this;
        }

        public Criteria andStreetNumberIn(List<String> values) {
            addCriterion("street_number in", values, "streetNumber");
            return (Criteria) this;
        }

        public Criteria andStreetNumberNotIn(List<String> values) {
            addCriterion("street_number not in", values, "streetNumber");
            return (Criteria) this;
        }

        public Criteria andStreetNumberBetween(String value1, String value2) {
            addCriterion("street_number between", value1, value2, "streetNumber");
            return (Criteria) this;
        }

        public Criteria andStreetNumberNotBetween(String value1, String value2) {
            addCriterion("street_number not between", value1, value2, "streetNumber");
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

        public Criteria andOpenTimeIsNull() {
            addCriterion("open_time is null");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIsNotNull() {
            addCriterion("open_time is not null");
            return (Criteria) this;
        }

        public Criteria andOpenTimeEqualTo(Date value) {
            addCriterion("open_time =", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotEqualTo(Date value) {
            addCriterion("open_time <>", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeGreaterThan(Date value) {
            addCriterion("open_time >", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("open_time >=", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLessThan(Date value) {
            addCriterion("open_time <", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLessThanOrEqualTo(Date value) {
            addCriterion("open_time <=", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIn(List<Date> values) {
            addCriterion("open_time in", values, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotIn(List<Date> values) {
            addCriterion("open_time not in", values, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeBetween(Date value1, Date value2) {
            addCriterion("open_time between", value1, value2, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotBetween(Date value1, Date value2) {
            addCriterion("open_time not between", value1, value2, "openTime");
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

        public Criteria andAveragePriceIsNull() {
            addCriterion("average_price is null");
            return (Criteria) this;
        }

        public Criteria andAveragePriceIsNotNull() {
            addCriterion("average_price is not null");
            return (Criteria) this;
        }

        public Criteria andAveragePriceEqualTo(String value) {
            addCriterion("average_price =", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceNotEqualTo(String value) {
            addCriterion("average_price <>", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceGreaterThan(String value) {
            addCriterion("average_price >", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceGreaterThanOrEqualTo(String value) {
            addCriterion("average_price >=", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceLessThan(String value) {
            addCriterion("average_price <", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceLessThanOrEqualTo(String value) {
            addCriterion("average_price <=", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceLike(String value) {
            addCriterion("average_price like", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceNotLike(String value) {
            addCriterion("average_price not like", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceIn(List<String> values) {
            addCriterion("average_price in", values, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceNotIn(List<String> values) {
            addCriterion("average_price not in", values, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceBetween(String value1, String value2) {
            addCriterion("average_price between", value1, value2, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceNotBetween(String value1, String value2) {
            addCriterion("average_price not between", value1, value2, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andPriceRangeIsNull() {
            addCriterion("price_range is null");
            return (Criteria) this;
        }

        public Criteria andPriceRangeIsNotNull() {
            addCriterion("price_range is not null");
            return (Criteria) this;
        }

        public Criteria andPriceRangeEqualTo(String value) {
            addCriterion("price_range =", value, "priceRange");
            return (Criteria) this;
        }

        public Criteria andPriceRangeNotEqualTo(String value) {
            addCriterion("price_range <>", value, "priceRange");
            return (Criteria) this;
        }

        public Criteria andPriceRangeGreaterThan(String value) {
            addCriterion("price_range >", value, "priceRange");
            return (Criteria) this;
        }

        public Criteria andPriceRangeGreaterThanOrEqualTo(String value) {
            addCriterion("price_range >=", value, "priceRange");
            return (Criteria) this;
        }

        public Criteria andPriceRangeLessThan(String value) {
            addCriterion("price_range <", value, "priceRange");
            return (Criteria) this;
        }

        public Criteria andPriceRangeLessThanOrEqualTo(String value) {
            addCriterion("price_range <=", value, "priceRange");
            return (Criteria) this;
        }

        public Criteria andPriceRangeLike(String value) {
            addCriterion("price_range like", value, "priceRange");
            return (Criteria) this;
        }

        public Criteria andPriceRangeNotLike(String value) {
            addCriterion("price_range not like", value, "priceRange");
            return (Criteria) this;
        }

        public Criteria andPriceRangeIn(List<String> values) {
            addCriterion("price_range in", values, "priceRange");
            return (Criteria) this;
        }

        public Criteria andPriceRangeNotIn(List<String> values) {
            addCriterion("price_range not in", values, "priceRange");
            return (Criteria) this;
        }

        public Criteria andPriceRangeBetween(String value1, String value2) {
            addCriterion("price_range between", value1, value2, "priceRange");
            return (Criteria) this;
        }

        public Criteria andPriceRangeNotBetween(String value1, String value2) {
            addCriterion("price_range not between", value1, value2, "priceRange");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingIsNull() {
            addCriterion("supply_heating is null");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingIsNotNull() {
            addCriterion("supply_heating is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingEqualTo(Integer value) {
            addCriterion("supply_heating =", value, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingNotEqualTo(Integer value) {
            addCriterion("supply_heating <>", value, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingGreaterThan(Integer value) {
            addCriterion("supply_heating >", value, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingGreaterThanOrEqualTo(Integer value) {
            addCriterion("supply_heating >=", value, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingLessThan(Integer value) {
            addCriterion("supply_heating <", value, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingLessThanOrEqualTo(Integer value) {
            addCriterion("supply_heating <=", value, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingIn(List<Integer> values) {
            addCriterion("supply_heating in", values, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingNotIn(List<Integer> values) {
            addCriterion("supply_heating not in", values, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingBetween(Integer value1, Integer value2) {
            addCriterion("supply_heating between", value1, value2, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyHeatingNotBetween(Integer value1, Integer value2) {
            addCriterion("supply_heating not between", value1, value2, "supplyHeating");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerIsNull() {
            addCriterion("supply_power is null");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerIsNotNull() {
            addCriterion("supply_power is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerEqualTo(Integer value) {
            addCriterion("supply_power =", value, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerNotEqualTo(Integer value) {
            addCriterion("supply_power <>", value, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerGreaterThan(Integer value) {
            addCriterion("supply_power >", value, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerGreaterThanOrEqualTo(Integer value) {
            addCriterion("supply_power >=", value, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerLessThan(Integer value) {
            addCriterion("supply_power <", value, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerLessThanOrEqualTo(Integer value) {
            addCriterion("supply_power <=", value, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerIn(List<Integer> values) {
            addCriterion("supply_power in", values, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerNotIn(List<Integer> values) {
            addCriterion("supply_power not in", values, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerBetween(Integer value1, Integer value2) {
            addCriterion("supply_power between", value1, value2, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyPowerNotBetween(Integer value1, Integer value2) {
            addCriterion("supply_power not between", value1, value2, "supplyPower");
            return (Criteria) this;
        }

        public Criteria andSupplyCommunicationIsNull() {
            addCriterion("supply_communication is null");
            return (Criteria) this;
        }

        public Criteria andSupplyCommunicationIsNotNull() {
            addCriterion("supply_communication is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyCommunicationEqualTo(Integer value) {
            addCriterion("supply_communication =", value, "supplyCommunication");
            return (Criteria) this;
        }

        public Criteria andSupplyCommunicationNotEqualTo(Integer value) {
            addCriterion("supply_communication <>", value, "supplyCommunication");
            return (Criteria) this;
        }

        public Criteria andSupplyCommunicationGreaterThan(Integer value) {
            addCriterion("supply_communication >", value, "supplyCommunication");
            return (Criteria) this;
        }

        public Criteria andSupplyCommunicationGreaterThanOrEqualTo(Integer value) {
            addCriterion("supply_communication >=", value, "supplyCommunication");
            return (Criteria) this;
        }

        public Criteria andSupplyCommunicationLessThan(Integer value) {
            addCriterion("supply_communication <", value, "supplyCommunication");
            return (Criteria) this;
        }

        public Criteria andSupplyCommunicationLessThanOrEqualTo(Integer value) {
            addCriterion("supply_communication <=", value, "supplyCommunication");
            return (Criteria) this;
        }

        public Criteria andSupplyCommunicationIn(List<Integer> values) {
            addCriterion("supply_communication in", values, "supplyCommunication");
            return (Criteria) this;
        }

        public Criteria andSupplyCommunicationNotIn(List<Integer> values) {
            addCriterion("supply_communication not in", values, "supplyCommunication");
            return (Criteria) this;
        }

        public Criteria andSupplyCommunicationBetween(Integer value1, Integer value2) {
            addCriterion("supply_communication between", value1, value2, "supplyCommunication");
            return (Criteria) this;
        }

        public Criteria andSupplyCommunicationNotBetween(Integer value1, Integer value2) {
            addCriterion("supply_communication not between", value1, value2, "supplyCommunication");
            return (Criteria) this;
        }

        public Criteria andSupplyRoadIsNull() {
            addCriterion("supply_road is null");
            return (Criteria) this;
        }

        public Criteria andSupplyRoadIsNotNull() {
            addCriterion("supply_road is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyRoadEqualTo(Integer value) {
            addCriterion("supply_road =", value, "supplyRoad");
            return (Criteria) this;
        }

        public Criteria andSupplyRoadNotEqualTo(Integer value) {
            addCriterion("supply_road <>", value, "supplyRoad");
            return (Criteria) this;
        }

        public Criteria andSupplyRoadGreaterThan(Integer value) {
            addCriterion("supply_road >", value, "supplyRoad");
            return (Criteria) this;
        }

        public Criteria andSupplyRoadGreaterThanOrEqualTo(Integer value) {
            addCriterion("supply_road >=", value, "supplyRoad");
            return (Criteria) this;
        }

        public Criteria andSupplyRoadLessThan(Integer value) {
            addCriterion("supply_road <", value, "supplyRoad");
            return (Criteria) this;
        }

        public Criteria andSupplyRoadLessThanOrEqualTo(Integer value) {
            addCriterion("supply_road <=", value, "supplyRoad");
            return (Criteria) this;
        }

        public Criteria andSupplyRoadIn(List<Integer> values) {
            addCriterion("supply_road in", values, "supplyRoad");
            return (Criteria) this;
        }

        public Criteria andSupplyRoadNotIn(List<Integer> values) {
            addCriterion("supply_road not in", values, "supplyRoad");
            return (Criteria) this;
        }

        public Criteria andSupplyRoadBetween(Integer value1, Integer value2) {
            addCriterion("supply_road between", value1, value2, "supplyRoad");
            return (Criteria) this;
        }

        public Criteria andSupplyRoadNotBetween(Integer value1, Integer value2) {
            addCriterion("supply_road not between", value1, value2, "supplyRoad");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterIsNull() {
            addCriterion("supply_water is null");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterIsNotNull() {
            addCriterion("supply_water is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterEqualTo(Integer value) {
            addCriterion("supply_water =", value, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterNotEqualTo(Integer value) {
            addCriterion("supply_water <>", value, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterGreaterThan(Integer value) {
            addCriterion("supply_water >", value, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterGreaterThanOrEqualTo(Integer value) {
            addCriterion("supply_water >=", value, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterLessThan(Integer value) {
            addCriterion("supply_water <", value, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterLessThanOrEqualTo(Integer value) {
            addCriterion("supply_water <=", value, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterIn(List<Integer> values) {
            addCriterion("supply_water in", values, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterNotIn(List<Integer> values) {
            addCriterion("supply_water not in", values, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterBetween(Integer value1, Integer value2) {
            addCriterion("supply_water between", value1, value2, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andSupplyWaterNotBetween(Integer value1, Integer value2) {
            addCriterion("supply_water not between", value1, value2, "supplyWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterIsNull() {
            addCriterion("drain_water is null");
            return (Criteria) this;
        }

        public Criteria andDrainWaterIsNotNull() {
            addCriterion("drain_water is not null");
            return (Criteria) this;
        }

        public Criteria andDrainWaterEqualTo(Integer value) {
            addCriterion("drain_water =", value, "drainWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterNotEqualTo(Integer value) {
            addCriterion("drain_water <>", value, "drainWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterGreaterThan(Integer value) {
            addCriterion("drain_water >", value, "drainWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterGreaterThanOrEqualTo(Integer value) {
            addCriterion("drain_water >=", value, "drainWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterLessThan(Integer value) {
            addCriterion("drain_water <", value, "drainWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterLessThanOrEqualTo(Integer value) {
            addCriterion("drain_water <=", value, "drainWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterIn(List<Integer> values) {
            addCriterion("drain_water in", values, "drainWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterNotIn(List<Integer> values) {
            addCriterion("drain_water not in", values, "drainWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterBetween(Integer value1, Integer value2) {
            addCriterion("drain_water between", value1, value2, "drainWater");
            return (Criteria) this;
        }

        public Criteria andDrainWaterNotBetween(Integer value1, Integer value2) {
            addCriterion("drain_water not between", value1, value2, "drainWater");
            return (Criteria) this;
        }

        public Criteria andSupplyGasIsNull() {
            addCriterion("supply_gas is null");
            return (Criteria) this;
        }

        public Criteria andSupplyGasIsNotNull() {
            addCriterion("supply_gas is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyGasEqualTo(Integer value) {
            addCriterion("supply_gas =", value, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andSupplyGasNotEqualTo(Integer value) {
            addCriterion("supply_gas <>", value, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andSupplyGasGreaterThan(Integer value) {
            addCriterion("supply_gas >", value, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andSupplyGasGreaterThanOrEqualTo(Integer value) {
            addCriterion("supply_gas >=", value, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andSupplyGasLessThan(Integer value) {
            addCriterion("supply_gas <", value, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andSupplyGasLessThanOrEqualTo(Integer value) {
            addCriterion("supply_gas <=", value, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andSupplyGasIn(List<Integer> values) {
            addCriterion("supply_gas in", values, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andSupplyGasNotIn(List<Integer> values) {
            addCriterion("supply_gas not in", values, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andSupplyGasBetween(Integer value1, Integer value2) {
            addCriterion("supply_gas between", value1, value2, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andSupplyGasNotBetween(Integer value1, Integer value2) {
            addCriterion("supply_gas not between", value1, value2, "supplyGas");
            return (Criteria) this;
        }

        public Criteria andInfrastructureIsNull() {
            addCriterion("infrastructure is null");
            return (Criteria) this;
        }

        public Criteria andInfrastructureIsNotNull() {
            addCriterion("infrastructure is not null");
            return (Criteria) this;
        }

        public Criteria andInfrastructureEqualTo(String value) {
            addCriterion("infrastructure =", value, "infrastructure");
            return (Criteria) this;
        }

        public Criteria andInfrastructureNotEqualTo(String value) {
            addCriterion("infrastructure <>", value, "infrastructure");
            return (Criteria) this;
        }

        public Criteria andInfrastructureGreaterThan(String value) {
            addCriterion("infrastructure >", value, "infrastructure");
            return (Criteria) this;
        }

        public Criteria andInfrastructureGreaterThanOrEqualTo(String value) {
            addCriterion("infrastructure >=", value, "infrastructure");
            return (Criteria) this;
        }

        public Criteria andInfrastructureLessThan(String value) {
            addCriterion("infrastructure <", value, "infrastructure");
            return (Criteria) this;
        }

        public Criteria andInfrastructureLessThanOrEqualTo(String value) {
            addCriterion("infrastructure <=", value, "infrastructure");
            return (Criteria) this;
        }

        public Criteria andInfrastructureLike(String value) {
            addCriterion("infrastructure like", value, "infrastructure");
            return (Criteria) this;
        }

        public Criteria andInfrastructureNotLike(String value) {
            addCriterion("infrastructure not like", value, "infrastructure");
            return (Criteria) this;
        }

        public Criteria andInfrastructureIn(List<String> values) {
            addCriterion("infrastructure in", values, "infrastructure");
            return (Criteria) this;
        }

        public Criteria andInfrastructureNotIn(List<String> values) {
            addCriterion("infrastructure not in", values, "infrastructure");
            return (Criteria) this;
        }

        public Criteria andInfrastructureBetween(String value1, String value2) {
            addCriterion("infrastructure between", value1, value2, "infrastructure");
            return (Criteria) this;
        }

        public Criteria andInfrastructureNotBetween(String value1, String value2) {
            addCriterion("infrastructure not between", value1, value2, "infrastructure");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessIsNull() {
            addCriterion("infrastructure_completeness is null");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessIsNotNull() {
            addCriterion("infrastructure_completeness is not null");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessEqualTo(Integer value) {
            addCriterion("infrastructure_completeness =", value, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessNotEqualTo(Integer value) {
            addCriterion("infrastructure_completeness <>", value, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessGreaterThan(Integer value) {
            addCriterion("infrastructure_completeness >", value, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessGreaterThanOrEqualTo(Integer value) {
            addCriterion("infrastructure_completeness >=", value, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessLessThan(Integer value) {
            addCriterion("infrastructure_completeness <", value, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessLessThanOrEqualTo(Integer value) {
            addCriterion("infrastructure_completeness <=", value, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessIn(List<Integer> values) {
            addCriterion("infrastructure_completeness in", values, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessNotIn(List<Integer> values) {
            addCriterion("infrastructure_completeness not in", values, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessBetween(Integer value1, Integer value2) {
            addCriterion("infrastructure_completeness between", value1, value2, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andInfrastructureCompletenessNotBetween(Integer value1, Integer value2) {
            addCriterion("infrastructure_completeness not between", value1, value2, "infrastructureCompleteness");
            return (Criteria) this;
        }

        public Criteria andLocationDescribeIsNull() {
            addCriterion("location_describe is null");
            return (Criteria) this;
        }

        public Criteria andLocationDescribeIsNotNull() {
            addCriterion("location_describe is not null");
            return (Criteria) this;
        }

        public Criteria andLocationDescribeEqualTo(String value) {
            addCriterion("location_describe =", value, "locationDescribe");
            return (Criteria) this;
        }

        public Criteria andLocationDescribeNotEqualTo(String value) {
            addCriterion("location_describe <>", value, "locationDescribe");
            return (Criteria) this;
        }

        public Criteria andLocationDescribeGreaterThan(String value) {
            addCriterion("location_describe >", value, "locationDescribe");
            return (Criteria) this;
        }

        public Criteria andLocationDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("location_describe >=", value, "locationDescribe");
            return (Criteria) this;
        }

        public Criteria andLocationDescribeLessThan(String value) {
            addCriterion("location_describe <", value, "locationDescribe");
            return (Criteria) this;
        }

        public Criteria andLocationDescribeLessThanOrEqualTo(String value) {
            addCriterion("location_describe <=", value, "locationDescribe");
            return (Criteria) this;
        }

        public Criteria andLocationDescribeLike(String value) {
            addCriterion("location_describe like", value, "locationDescribe");
            return (Criteria) this;
        }

        public Criteria andLocationDescribeNotLike(String value) {
            addCriterion("location_describe not like", value, "locationDescribe");
            return (Criteria) this;
        }

        public Criteria andLocationDescribeIn(List<String> values) {
            addCriterion("location_describe in", values, "locationDescribe");
            return (Criteria) this;
        }

        public Criteria andLocationDescribeNotIn(List<String> values) {
            addCriterion("location_describe not in", values, "locationDescribe");
            return (Criteria) this;
        }

        public Criteria andLocationDescribeBetween(String value1, String value2) {
            addCriterion("location_describe between", value1, value2, "locationDescribe");
            return (Criteria) this;
        }

        public Criteria andLocationDescribeNotBetween(String value1, String value2) {
            addCriterion("location_describe not between", value1, value2, "locationDescribe");
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

        public Criteria andAveragePriceUnitIsNull() {
            addCriterion("average_price_unit is null");
            return (Criteria) this;
        }

        public Criteria andAveragePriceUnitIsNotNull() {
            addCriterion("average_price_unit is not null");
            return (Criteria) this;
        }

        public Criteria andAveragePriceUnitEqualTo(String value) {
            addCriterion("average_price_unit =", value, "averagePriceUnit");
            return (Criteria) this;
        }

        public Criteria andAveragePriceUnitNotEqualTo(String value) {
            addCriterion("average_price_unit <>", value, "averagePriceUnit");
            return (Criteria) this;
        }

        public Criteria andAveragePriceUnitGreaterThan(String value) {
            addCriterion("average_price_unit >", value, "averagePriceUnit");
            return (Criteria) this;
        }

        public Criteria andAveragePriceUnitGreaterThanOrEqualTo(String value) {
            addCriterion("average_price_unit >=", value, "averagePriceUnit");
            return (Criteria) this;
        }

        public Criteria andAveragePriceUnitLessThan(String value) {
            addCriterion("average_price_unit <", value, "averagePriceUnit");
            return (Criteria) this;
        }

        public Criteria andAveragePriceUnitLessThanOrEqualTo(String value) {
            addCriterion("average_price_unit <=", value, "averagePriceUnit");
            return (Criteria) this;
        }

        public Criteria andAveragePriceUnitLike(String value) {
            addCriterion("average_price_unit like", value, "averagePriceUnit");
            return (Criteria) this;
        }

        public Criteria andAveragePriceUnitNotLike(String value) {
            addCriterion("average_price_unit not like", value, "averagePriceUnit");
            return (Criteria) this;
        }

        public Criteria andAveragePriceUnitIn(List<String> values) {
            addCriterion("average_price_unit in", values, "averagePriceUnit");
            return (Criteria) this;
        }

        public Criteria andAveragePriceUnitNotIn(List<String> values) {
            addCriterion("average_price_unit not in", values, "averagePriceUnit");
            return (Criteria) this;
        }

        public Criteria andAveragePriceUnitBetween(String value1, String value2) {
            addCriterion("average_price_unit between", value1, value2, "averagePriceUnit");
            return (Criteria) this;
        }

        public Criteria andAveragePriceUnitNotBetween(String value1, String value2) {
            addCriterion("average_price_unit not between", value1, value2, "averagePriceUnit");
            return (Criteria) this;
        }

        public Criteria andPriceRangeUnitIsNull() {
            addCriterion("price_range_unit is null");
            return (Criteria) this;
        }

        public Criteria andPriceRangeUnitIsNotNull() {
            addCriterion("price_range_unit is not null");
            return (Criteria) this;
        }

        public Criteria andPriceRangeUnitEqualTo(String value) {
            addCriterion("price_range_unit =", value, "priceRangeUnit");
            return (Criteria) this;
        }

        public Criteria andPriceRangeUnitNotEqualTo(String value) {
            addCriterion("price_range_unit <>", value, "priceRangeUnit");
            return (Criteria) this;
        }

        public Criteria andPriceRangeUnitGreaterThan(String value) {
            addCriterion("price_range_unit >", value, "priceRangeUnit");
            return (Criteria) this;
        }

        public Criteria andPriceRangeUnitGreaterThanOrEqualTo(String value) {
            addCriterion("price_range_unit >=", value, "priceRangeUnit");
            return (Criteria) this;
        }

        public Criteria andPriceRangeUnitLessThan(String value) {
            addCriterion("price_range_unit <", value, "priceRangeUnit");
            return (Criteria) this;
        }

        public Criteria andPriceRangeUnitLessThanOrEqualTo(String value) {
            addCriterion("price_range_unit <=", value, "priceRangeUnit");
            return (Criteria) this;
        }

        public Criteria andPriceRangeUnitLike(String value) {
            addCriterion("price_range_unit like", value, "priceRangeUnit");
            return (Criteria) this;
        }

        public Criteria andPriceRangeUnitNotLike(String value) {
            addCriterion("price_range_unit not like", value, "priceRangeUnit");
            return (Criteria) this;
        }

        public Criteria andPriceRangeUnitIn(List<String> values) {
            addCriterion("price_range_unit in", values, "priceRangeUnit");
            return (Criteria) this;
        }

        public Criteria andPriceRangeUnitNotIn(List<String> values) {
            addCriterion("price_range_unit not in", values, "priceRangeUnit");
            return (Criteria) this;
        }

        public Criteria andPriceRangeUnitBetween(String value1, String value2) {
            addCriterion("price_range_unit between", value1, value2, "priceRangeUnit");
            return (Criteria) this;
        }

        public Criteria andPriceRangeUnitNotBetween(String value1, String value2) {
            addCriterion("price_range_unit not between", value1, value2, "priceRangeUnit");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaUnitIsNull() {
            addCriterion("cover_an_area_unit is null");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaUnitIsNotNull() {
            addCriterion("cover_an_area_unit is not null");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaUnitEqualTo(String value) {
            addCriterion("cover_an_area_unit =", value, "coverAnAreaUnit");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaUnitNotEqualTo(String value) {
            addCriterion("cover_an_area_unit <>", value, "coverAnAreaUnit");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaUnitGreaterThan(String value) {
            addCriterion("cover_an_area_unit >", value, "coverAnAreaUnit");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaUnitGreaterThanOrEqualTo(String value) {
            addCriterion("cover_an_area_unit >=", value, "coverAnAreaUnit");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaUnitLessThan(String value) {
            addCriterion("cover_an_area_unit <", value, "coverAnAreaUnit");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaUnitLessThanOrEqualTo(String value) {
            addCriterion("cover_an_area_unit <=", value, "coverAnAreaUnit");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaUnitLike(String value) {
            addCriterion("cover_an_area_unit like", value, "coverAnAreaUnit");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaUnitNotLike(String value) {
            addCriterion("cover_an_area_unit not like", value, "coverAnAreaUnit");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaUnitIn(List<String> values) {
            addCriterion("cover_an_area_unit in", values, "coverAnAreaUnit");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaUnitNotIn(List<String> values) {
            addCriterion("cover_an_area_unit not in", values, "coverAnAreaUnit");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaUnitBetween(String value1, String value2) {
            addCriterion("cover_an_area_unit between", value1, value2, "coverAnAreaUnit");
            return (Criteria) this;
        }

        public Criteria andCoverAnAreaUnitNotBetween(String value1, String value2) {
            addCriterion("cover_an_area_unit not between", value1, value2, "coverAnAreaUnit");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTypeIsNull() {
            addCriterion("acquisition_type is null");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTypeIsNotNull() {
            addCriterion("acquisition_type is not null");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTypeEqualTo(String value) {
            addCriterion("acquisition_type =", value, "acquisitionType");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTypeNotEqualTo(String value) {
            addCriterion("acquisition_type <>", value, "acquisitionType");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTypeGreaterThan(String value) {
            addCriterion("acquisition_type >", value, "acquisitionType");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("acquisition_type >=", value, "acquisitionType");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTypeLessThan(String value) {
            addCriterion("acquisition_type <", value, "acquisitionType");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTypeLessThanOrEqualTo(String value) {
            addCriterion("acquisition_type <=", value, "acquisitionType");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTypeLike(String value) {
            addCriterion("acquisition_type like", value, "acquisitionType");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTypeNotLike(String value) {
            addCriterion("acquisition_type not like", value, "acquisitionType");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTypeIn(List<String> values) {
            addCriterion("acquisition_type in", values, "acquisitionType");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTypeNotIn(List<String> values) {
            addCriterion("acquisition_type not in", values, "acquisitionType");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTypeBetween(String value1, String value2) {
            addCriterion("acquisition_type between", value1, value2, "acquisitionType");
            return (Criteria) this;
        }

        public Criteria andAcquisitionTypeNotBetween(String value1, String value2) {
            addCriterion("acquisition_type not between", value1, value2, "acquisitionType");
            return (Criteria) this;
        }

        public Criteria andLandRightNatureIsNull() {
            addCriterion("land_right_nature is null");
            return (Criteria) this;
        }

        public Criteria andLandRightNatureIsNotNull() {
            addCriterion("land_right_nature is not null");
            return (Criteria) this;
        }

        public Criteria andLandRightNatureEqualTo(String value) {
            addCriterion("land_right_nature =", value, "landRightNature");
            return (Criteria) this;
        }

        public Criteria andLandRightNatureNotEqualTo(String value) {
            addCriterion("land_right_nature <>", value, "landRightNature");
            return (Criteria) this;
        }

        public Criteria andLandRightNatureGreaterThan(String value) {
            addCriterion("land_right_nature >", value, "landRightNature");
            return (Criteria) this;
        }

        public Criteria andLandRightNatureGreaterThanOrEqualTo(String value) {
            addCriterion("land_right_nature >=", value, "landRightNature");
            return (Criteria) this;
        }

        public Criteria andLandRightNatureLessThan(String value) {
            addCriterion("land_right_nature <", value, "landRightNature");
            return (Criteria) this;
        }

        public Criteria andLandRightNatureLessThanOrEqualTo(String value) {
            addCriterion("land_right_nature <=", value, "landRightNature");
            return (Criteria) this;
        }

        public Criteria andLandRightNatureLike(String value) {
            addCriterion("land_right_nature like", value, "landRightNature");
            return (Criteria) this;
        }

        public Criteria andLandRightNatureNotLike(String value) {
            addCriterion("land_right_nature not like", value, "landRightNature");
            return (Criteria) this;
        }

        public Criteria andLandRightNatureIn(List<String> values) {
            addCriterion("land_right_nature in", values, "landRightNature");
            return (Criteria) this;
        }

        public Criteria andLandRightNatureNotIn(List<String> values) {
            addCriterion("land_right_nature not in", values, "landRightNature");
            return (Criteria) this;
        }

        public Criteria andLandRightNatureBetween(String value1, String value2) {
            addCriterion("land_right_nature between", value1, value2, "landRightNature");
            return (Criteria) this;
        }

        public Criteria andLandRightNatureNotBetween(String value1, String value2) {
            addCriterion("land_right_nature not between", value1, value2, "landRightNature");
            return (Criteria) this;
        }

        public Criteria andLandRightTypeIsNull() {
            addCriterion("land_right_type is null");
            return (Criteria) this;
        }

        public Criteria andLandRightTypeIsNotNull() {
            addCriterion("land_right_type is not null");
            return (Criteria) this;
        }

        public Criteria andLandRightTypeEqualTo(String value) {
            addCriterion("land_right_type =", value, "landRightType");
            return (Criteria) this;
        }

        public Criteria andLandRightTypeNotEqualTo(String value) {
            addCriterion("land_right_type <>", value, "landRightType");
            return (Criteria) this;
        }

        public Criteria andLandRightTypeGreaterThan(String value) {
            addCriterion("land_right_type >", value, "landRightType");
            return (Criteria) this;
        }

        public Criteria andLandRightTypeGreaterThanOrEqualTo(String value) {
            addCriterion("land_right_type >=", value, "landRightType");
            return (Criteria) this;
        }

        public Criteria andLandRightTypeLessThan(String value) {
            addCriterion("land_right_type <", value, "landRightType");
            return (Criteria) this;
        }

        public Criteria andLandRightTypeLessThanOrEqualTo(String value) {
            addCriterion("land_right_type <=", value, "landRightType");
            return (Criteria) this;
        }

        public Criteria andLandRightTypeLike(String value) {
            addCriterion("land_right_type like", value, "landRightType");
            return (Criteria) this;
        }

        public Criteria andLandRightTypeNotLike(String value) {
            addCriterion("land_right_type not like", value, "landRightType");
            return (Criteria) this;
        }

        public Criteria andLandRightTypeIn(List<String> values) {
            addCriterion("land_right_type in", values, "landRightType");
            return (Criteria) this;
        }

        public Criteria andLandRightTypeNotIn(List<String> values) {
            addCriterion("land_right_type not in", values, "landRightType");
            return (Criteria) this;
        }

        public Criteria andLandRightTypeBetween(String value1, String value2) {
            addCriterion("land_right_type between", value1, value2, "landRightType");
            return (Criteria) this;
        }

        public Criteria andLandRightTypeNotBetween(String value1, String value2) {
            addCriterion("land_right_type not between", value1, value2, "landRightType");
            return (Criteria) this;
        }

        public Criteria andLandPieceNumberingIsNull() {
            addCriterion("land_piece_numbering is null");
            return (Criteria) this;
        }

        public Criteria andLandPieceNumberingIsNotNull() {
            addCriterion("land_piece_numbering is not null");
            return (Criteria) this;
        }

        public Criteria andLandPieceNumberingEqualTo(String value) {
            addCriterion("land_piece_numbering =", value, "landPieceNumbering");
            return (Criteria) this;
        }

        public Criteria andLandPieceNumberingNotEqualTo(String value) {
            addCriterion("land_piece_numbering <>", value, "landPieceNumbering");
            return (Criteria) this;
        }

        public Criteria andLandPieceNumberingGreaterThan(String value) {
            addCriterion("land_piece_numbering >", value, "landPieceNumbering");
            return (Criteria) this;
        }

        public Criteria andLandPieceNumberingGreaterThanOrEqualTo(String value) {
            addCriterion("land_piece_numbering >=", value, "landPieceNumbering");
            return (Criteria) this;
        }

        public Criteria andLandPieceNumberingLessThan(String value) {
            addCriterion("land_piece_numbering <", value, "landPieceNumbering");
            return (Criteria) this;
        }

        public Criteria andLandPieceNumberingLessThanOrEqualTo(String value) {
            addCriterion("land_piece_numbering <=", value, "landPieceNumbering");
            return (Criteria) this;
        }

        public Criteria andLandPieceNumberingLike(String value) {
            addCriterion("land_piece_numbering like", value, "landPieceNumbering");
            return (Criteria) this;
        }

        public Criteria andLandPieceNumberingNotLike(String value) {
            addCriterion("land_piece_numbering not like", value, "landPieceNumbering");
            return (Criteria) this;
        }

        public Criteria andLandPieceNumberingIn(List<String> values) {
            addCriterion("land_piece_numbering in", values, "landPieceNumbering");
            return (Criteria) this;
        }

        public Criteria andLandPieceNumberingNotIn(List<String> values) {
            addCriterion("land_piece_numbering not in", values, "landPieceNumbering");
            return (Criteria) this;
        }

        public Criteria andLandPieceNumberingBetween(String value1, String value2) {
            addCriterion("land_piece_numbering between", value1, value2, "landPieceNumbering");
            return (Criteria) this;
        }

        public Criteria andLandPieceNumberingNotBetween(String value1, String value2) {
            addCriterion("land_piece_numbering not between", value1, value2, "landPieceNumbering");
            return (Criteria) this;
        }

        public Criteria andRegionalPlanningIsNull() {
            addCriterion("regional_planning is null");
            return (Criteria) this;
        }

        public Criteria andRegionalPlanningIsNotNull() {
            addCriterion("regional_planning is not null");
            return (Criteria) this;
        }

        public Criteria andRegionalPlanningEqualTo(String value) {
            addCriterion("regional_planning =", value, "regionalPlanning");
            return (Criteria) this;
        }

        public Criteria andRegionalPlanningNotEqualTo(String value) {
            addCriterion("regional_planning <>", value, "regionalPlanning");
            return (Criteria) this;
        }

        public Criteria andRegionalPlanningGreaterThan(String value) {
            addCriterion("regional_planning >", value, "regionalPlanning");
            return (Criteria) this;
        }

        public Criteria andRegionalPlanningGreaterThanOrEqualTo(String value) {
            addCriterion("regional_planning >=", value, "regionalPlanning");
            return (Criteria) this;
        }

        public Criteria andRegionalPlanningLessThan(String value) {
            addCriterion("regional_planning <", value, "regionalPlanning");
            return (Criteria) this;
        }

        public Criteria andRegionalPlanningLessThanOrEqualTo(String value) {
            addCriterion("regional_planning <=", value, "regionalPlanning");
            return (Criteria) this;
        }

        public Criteria andRegionalPlanningLike(String value) {
            addCriterion("regional_planning like", value, "regionalPlanning");
            return (Criteria) this;
        }

        public Criteria andRegionalPlanningNotLike(String value) {
            addCriterion("regional_planning not like", value, "regionalPlanning");
            return (Criteria) this;
        }

        public Criteria andRegionalPlanningIn(List<String> values) {
            addCriterion("regional_planning in", values, "regionalPlanning");
            return (Criteria) this;
        }

        public Criteria andRegionalPlanningNotIn(List<String> values) {
            addCriterion("regional_planning not in", values, "regionalPlanning");
            return (Criteria) this;
        }

        public Criteria andRegionalPlanningBetween(String value1, String value2) {
            addCriterion("regional_planning between", value1, value2, "regionalPlanning");
            return (Criteria) this;
        }

        public Criteria andRegionalPlanningNotBetween(String value1, String value2) {
            addCriterion("regional_planning not between", value1, value2, "regionalPlanning");
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
     * tb_basic_estate
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