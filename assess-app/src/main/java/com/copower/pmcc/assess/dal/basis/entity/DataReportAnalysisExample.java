package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataReportAnalysisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataReportAnalysisExample() {
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

        public Criteria andMarketBackgroundTypeIsNull() {
            addCriterion("market_background_type is null");
            return (Criteria) this;
        }

        public Criteria andMarketBackgroundTypeIsNotNull() {
            addCriterion("market_background_type is not null");
            return (Criteria) this;
        }

        public Criteria andMarketBackgroundTypeEqualTo(Integer value) {
            addCriterion("market_background_type =", value, "marketBackgroundType");
            return (Criteria) this;
        }

        public Criteria andMarketBackgroundTypeNotEqualTo(Integer value) {
            addCriterion("market_background_type <>", value, "marketBackgroundType");
            return (Criteria) this;
        }

        public Criteria andMarketBackgroundTypeGreaterThan(Integer value) {
            addCriterion("market_background_type >", value, "marketBackgroundType");
            return (Criteria) this;
        }

        public Criteria andMarketBackgroundTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("market_background_type >=", value, "marketBackgroundType");
            return (Criteria) this;
        }

        public Criteria andMarketBackgroundTypeLessThan(Integer value) {
            addCriterion("market_background_type <", value, "marketBackgroundType");
            return (Criteria) this;
        }

        public Criteria andMarketBackgroundTypeLessThanOrEqualTo(Integer value) {
            addCriterion("market_background_type <=", value, "marketBackgroundType");
            return (Criteria) this;
        }

        public Criteria andMarketBackgroundTypeIn(List<Integer> values) {
            addCriterion("market_background_type in", values, "marketBackgroundType");
            return (Criteria) this;
        }

        public Criteria andMarketBackgroundTypeNotIn(List<Integer> values) {
            addCriterion("market_background_type not in", values, "marketBackgroundType");
            return (Criteria) this;
        }

        public Criteria andMarketBackgroundTypeBetween(Integer value1, Integer value2) {
            addCriterion("market_background_type between", value1, value2, "marketBackgroundType");
            return (Criteria) this;
        }

        public Criteria andMarketBackgroundTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("market_background_type not between", value1, value2, "marketBackgroundType");
            return (Criteria) this;
        }

        public Criteria andReportAnalysisTypeIsNull() {
            addCriterion("report_analysis_type is null");
            return (Criteria) this;
        }

        public Criteria andReportAnalysisTypeIsNotNull() {
            addCriterion("report_analysis_type is not null");
            return (Criteria) this;
        }

        public Criteria andReportAnalysisTypeEqualTo(Integer value) {
            addCriterion("report_analysis_type =", value, "reportAnalysisType");
            return (Criteria) this;
        }

        public Criteria andReportAnalysisTypeNotEqualTo(Integer value) {
            addCriterion("report_analysis_type <>", value, "reportAnalysisType");
            return (Criteria) this;
        }

        public Criteria andReportAnalysisTypeGreaterThan(Integer value) {
            addCriterion("report_analysis_type >", value, "reportAnalysisType");
            return (Criteria) this;
        }

        public Criteria andReportAnalysisTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("report_analysis_type >=", value, "reportAnalysisType");
            return (Criteria) this;
        }

        public Criteria andReportAnalysisTypeLessThan(Integer value) {
            addCriterion("report_analysis_type <", value, "reportAnalysisType");
            return (Criteria) this;
        }

        public Criteria andReportAnalysisTypeLessThanOrEqualTo(Integer value) {
            addCriterion("report_analysis_type <=", value, "reportAnalysisType");
            return (Criteria) this;
        }

        public Criteria andReportAnalysisTypeIn(List<Integer> values) {
            addCriterion("report_analysis_type in", values, "reportAnalysisType");
            return (Criteria) this;
        }

        public Criteria andReportAnalysisTypeNotIn(List<Integer> values) {
            addCriterion("report_analysis_type not in", values, "reportAnalysisType");
            return (Criteria) this;
        }

        public Criteria andReportAnalysisTypeBetween(Integer value1, Integer value2) {
            addCriterion("report_analysis_type between", value1, value2, "reportAnalysisType");
            return (Criteria) this;
        }

        public Criteria andReportAnalysisTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("report_analysis_type not between", value1, value2, "reportAnalysisType");
            return (Criteria) this;
        }

        public Criteria andTemplateIsNull() {
            addCriterion("template is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIsNotNull() {
            addCriterion("template is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateEqualTo(String value) {
            addCriterion("template =", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotEqualTo(String value) {
            addCriterion("template <>", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateGreaterThan(String value) {
            addCriterion("template >", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("template >=", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateLessThan(String value) {
            addCriterion("template <", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateLessThanOrEqualTo(String value) {
            addCriterion("template <=", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateLike(String value) {
            addCriterion("template like", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotLike(String value) {
            addCriterion("template not like", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateIn(List<String> values) {
            addCriterion("template in", values, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotIn(List<String> values) {
            addCriterion("template not in", values, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateBetween(String value1, String value2) {
            addCriterion("template between", value1, value2, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotBetween(String value1, String value2) {
            addCriterion("template not between", value1, value2, "template");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeIsNull() {
            addCriterion("entrustment_purpose is null");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeIsNotNull() {
            addCriterion("entrustment_purpose is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeEqualTo(String value) {
            addCriterion("entrustment_purpose =", value, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeNotEqualTo(String value) {
            addCriterion("entrustment_purpose <>", value, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeGreaterThan(String value) {
            addCriterion("entrustment_purpose >", value, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeGreaterThanOrEqualTo(String value) {
            addCriterion("entrustment_purpose >=", value, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeLessThan(String value) {
            addCriterion("entrustment_purpose <", value, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeLessThanOrEqualTo(String value) {
            addCriterion("entrustment_purpose <=", value, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeLike(String value) {
            addCriterion("entrustment_purpose like", value, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeNotLike(String value) {
            addCriterion("entrustment_purpose not like", value, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeIn(List<String> values) {
            addCriterion("entrustment_purpose in", values, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeNotIn(List<String> values) {
            addCriterion("entrustment_purpose not in", values, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeBetween(String value1, String value2) {
            addCriterion("entrustment_purpose between", value1, value2, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andEntrustmentPurposeNotBetween(String value1, String value2) {
            addCriterion("entrustment_purpose not between", value1, value2, "entrustmentPurpose");
            return (Criteria) this;
        }

        public Criteria andSetUseIsNull() {
            addCriterion("set_use is null");
            return (Criteria) this;
        }

        public Criteria andSetUseIsNotNull() {
            addCriterion("set_use is not null");
            return (Criteria) this;
        }

        public Criteria andSetUseEqualTo(Integer value) {
            addCriterion("set_use =", value, "setUse");
            return (Criteria) this;
        }

        public Criteria andSetUseNotEqualTo(Integer value) {
            addCriterion("set_use <>", value, "setUse");
            return (Criteria) this;
        }

        public Criteria andSetUseGreaterThan(Integer value) {
            addCriterion("set_use >", value, "setUse");
            return (Criteria) this;
        }

        public Criteria andSetUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("set_use >=", value, "setUse");
            return (Criteria) this;
        }

        public Criteria andSetUseLessThan(Integer value) {
            addCriterion("set_use <", value, "setUse");
            return (Criteria) this;
        }

        public Criteria andSetUseLessThanOrEqualTo(Integer value) {
            addCriterion("set_use <=", value, "setUse");
            return (Criteria) this;
        }

        public Criteria andSetUseIn(List<Integer> values) {
            addCriterion("set_use in", values, "setUse");
            return (Criteria) this;
        }

        public Criteria andSetUseNotIn(List<Integer> values) {
            addCriterion("set_use not in", values, "setUse");
            return (Criteria) this;
        }

        public Criteria andSetUseBetween(Integer value1, Integer value2) {
            addCriterion("set_use between", value1, value2, "setUse");
            return (Criteria) this;
        }

        public Criteria andSetUseNotBetween(Integer value1, Integer value2) {
            addCriterion("set_use not between", value1, value2, "setUse");
            return (Criteria) this;
        }

        public Criteria andRelYearIsNull() {
            addCriterion("rel_year is null");
            return (Criteria) this;
        }

        public Criteria andRelYearIsNotNull() {
            addCriterion("rel_year is not null");
            return (Criteria) this;
        }

        public Criteria andRelYearEqualTo(Integer value) {
            addCriterion("rel_year =", value, "relYear");
            return (Criteria) this;
        }

        public Criteria andRelYearNotEqualTo(Integer value) {
            addCriterion("rel_year <>", value, "relYear");
            return (Criteria) this;
        }

        public Criteria andRelYearGreaterThan(Integer value) {
            addCriterion("rel_year >", value, "relYear");
            return (Criteria) this;
        }

        public Criteria andRelYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("rel_year >=", value, "relYear");
            return (Criteria) this;
        }

        public Criteria andRelYearLessThan(Integer value) {
            addCriterion("rel_year <", value, "relYear");
            return (Criteria) this;
        }

        public Criteria andRelYearLessThanOrEqualTo(Integer value) {
            addCriterion("rel_year <=", value, "relYear");
            return (Criteria) this;
        }

        public Criteria andRelYearIn(List<Integer> values) {
            addCriterion("rel_year in", values, "relYear");
            return (Criteria) this;
        }

        public Criteria andRelYearNotIn(List<Integer> values) {
            addCriterion("rel_year not in", values, "relYear");
            return (Criteria) this;
        }

        public Criteria andRelYearBetween(Integer value1, Integer value2) {
            addCriterion("rel_year between", value1, value2, "relYear");
            return (Criteria) this;
        }

        public Criteria andRelYearNotBetween(Integer value1, Integer value2) {
            addCriterion("rel_year not between", value1, value2, "relYear");
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

        public Criteria andFieldNameIsNull() {
            addCriterion("field_name is null");
            return (Criteria) this;
        }

        public Criteria andFieldNameIsNotNull() {
            addCriterion("field_name is not null");
            return (Criteria) this;
        }

        public Criteria andFieldNameEqualTo(String value) {
            addCriterion("field_name =", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotEqualTo(String value) {
            addCriterion("field_name <>", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameGreaterThan(String value) {
            addCriterion("field_name >", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameGreaterThanOrEqualTo(String value) {
            addCriterion("field_name >=", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameLessThan(String value) {
            addCriterion("field_name <", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameLessThanOrEqualTo(String value) {
            addCriterion("field_name <=", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameLike(String value) {
            addCriterion("field_name like", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotLike(String value) {
            addCriterion("field_name not like", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameIn(List<String> values) {
            addCriterion("field_name in", values, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotIn(List<String> values) {
            addCriterion("field_name not in", values, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameBetween(String value1, String value2) {
            addCriterion("field_name between", value1, value2, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotBetween(String value1, String value2) {
            addCriterion("field_name not between", value1, value2, "fieldName");
            return (Criteria) this;
        }

        public Criteria andSortingIsNull() {
            addCriterion("sorting is null");
            return (Criteria) this;
        }

        public Criteria andSortingIsNotNull() {
            addCriterion("sorting is not null");
            return (Criteria) this;
        }

        public Criteria andSortingEqualTo(Integer value) {
            addCriterion("sorting =", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotEqualTo(Integer value) {
            addCriterion("sorting <>", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingGreaterThan(Integer value) {
            addCriterion("sorting >", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingGreaterThanOrEqualTo(Integer value) {
            addCriterion("sorting >=", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingLessThan(Integer value) {
            addCriterion("sorting <", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingLessThanOrEqualTo(Integer value) {
            addCriterion("sorting <=", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingIn(List<Integer> values) {
            addCriterion("sorting in", values, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotIn(List<Integer> values) {
            addCriterion("sorting not in", values, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingBetween(Integer value1, Integer value2) {
            addCriterion("sorting between", value1, value2, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotBetween(Integer value1, Integer value2) {
            addCriterion("sorting not between", value1, value2, "sorting");
            return (Criteria) this;
        }

        public Criteria andBisModifiableIsNull() {
            addCriterion("bis_modifiable is null");
            return (Criteria) this;
        }

        public Criteria andBisModifiableIsNotNull() {
            addCriterion("bis_modifiable is not null");
            return (Criteria) this;
        }

        public Criteria andBisModifiableEqualTo(Boolean value) {
            addCriterion("bis_modifiable =", value, "bisModifiable");
            return (Criteria) this;
        }

        public Criteria andBisModifiableNotEqualTo(Boolean value) {
            addCriterion("bis_modifiable <>", value, "bisModifiable");
            return (Criteria) this;
        }

        public Criteria andBisModifiableGreaterThan(Boolean value) {
            addCriterion("bis_modifiable >", value, "bisModifiable");
            return (Criteria) this;
        }

        public Criteria andBisModifiableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_modifiable >=", value, "bisModifiable");
            return (Criteria) this;
        }

        public Criteria andBisModifiableLessThan(Boolean value) {
            addCriterion("bis_modifiable <", value, "bisModifiable");
            return (Criteria) this;
        }

        public Criteria andBisModifiableLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_modifiable <=", value, "bisModifiable");
            return (Criteria) this;
        }

        public Criteria andBisModifiableIn(List<Boolean> values) {
            addCriterion("bis_modifiable in", values, "bisModifiable");
            return (Criteria) this;
        }

        public Criteria andBisModifiableNotIn(List<Boolean> values) {
            addCriterion("bis_modifiable not in", values, "bisModifiable");
            return (Criteria) this;
        }

        public Criteria andBisModifiableBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_modifiable between", value1, value2, "bisModifiable");
            return (Criteria) this;
        }

        public Criteria andBisModifiableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_modifiable not between", value1, value2, "bisModifiable");
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