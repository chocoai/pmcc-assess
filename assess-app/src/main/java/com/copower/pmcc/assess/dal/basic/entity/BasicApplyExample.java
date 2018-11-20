package com.copower.pmcc.assess.dal.basic.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasicApplyExample() {
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

        public Criteria andProcessInsIdIsNull() {
            addCriterion("process_ins_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdIsNotNull() {
            addCriterion("process_ins_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdEqualTo(String value) {
            addCriterion("process_ins_id =", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotEqualTo(String value) {
            addCriterion("process_ins_id <>", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdGreaterThan(String value) {
            addCriterion("process_ins_id >", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdGreaterThanOrEqualTo(String value) {
            addCriterion("process_ins_id >=", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLessThan(String value) {
            addCriterion("process_ins_id <", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLessThanOrEqualTo(String value) {
            addCriterion("process_ins_id <=", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLike(String value) {
            addCriterion("process_ins_id like", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotLike(String value) {
            addCriterion("process_ins_id not like", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdIn(List<String> values) {
            addCriterion("process_ins_id in", values, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotIn(List<String> values) {
            addCriterion("process_ins_id not in", values, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdBetween(String value1, String value2) {
            addCriterion("process_ins_id between", value1, value2, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotBetween(String value1, String value2) {
            addCriterion("process_ins_id not between", value1, value2, "processInsId");
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

        public Criteria andUnitNumberIsNull() {
            addCriterion("unit_number is null");
            return (Criteria) this;
        }

        public Criteria andUnitNumberIsNotNull() {
            addCriterion("unit_number is not null");
            return (Criteria) this;
        }

        public Criteria andUnitNumberEqualTo(String value) {
            addCriterion("unit_number =", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberNotEqualTo(String value) {
            addCriterion("unit_number <>", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberGreaterThan(String value) {
            addCriterion("unit_number >", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberGreaterThanOrEqualTo(String value) {
            addCriterion("unit_number >=", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberLessThan(String value) {
            addCriterion("unit_number <", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberLessThanOrEqualTo(String value) {
            addCriterion("unit_number <=", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberLike(String value) {
            addCriterion("unit_number like", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberNotLike(String value) {
            addCriterion("unit_number not like", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberIn(List<String> values) {
            addCriterion("unit_number in", values, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberNotIn(List<String> values) {
            addCriterion("unit_number not in", values, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberBetween(String value1, String value2) {
            addCriterion("unit_number between", value1, value2, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberNotBetween(String value1, String value2) {
            addCriterion("unit_number not between", value1, value2, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andBuildIdentifierIsNull() {
            addCriterion("build_identifier is null");
            return (Criteria) this;
        }

        public Criteria andBuildIdentifierIsNotNull() {
            addCriterion("build_identifier is not null");
            return (Criteria) this;
        }

        public Criteria andBuildIdentifierEqualTo(String value) {
            addCriterion("build_identifier =", value, "buildIdentifier");
            return (Criteria) this;
        }

        public Criteria andBuildIdentifierNotEqualTo(String value) {
            addCriterion("build_identifier <>", value, "buildIdentifier");
            return (Criteria) this;
        }

        public Criteria andBuildIdentifierGreaterThan(String value) {
            addCriterion("build_identifier >", value, "buildIdentifier");
            return (Criteria) this;
        }

        public Criteria andBuildIdentifierGreaterThanOrEqualTo(String value) {
            addCriterion("build_identifier >=", value, "buildIdentifier");
            return (Criteria) this;
        }

        public Criteria andBuildIdentifierLessThan(String value) {
            addCriterion("build_identifier <", value, "buildIdentifier");
            return (Criteria) this;
        }

        public Criteria andBuildIdentifierLessThanOrEqualTo(String value) {
            addCriterion("build_identifier <=", value, "buildIdentifier");
            return (Criteria) this;
        }

        public Criteria andBuildIdentifierLike(String value) {
            addCriterion("build_identifier like", value, "buildIdentifier");
            return (Criteria) this;
        }

        public Criteria andBuildIdentifierNotLike(String value) {
            addCriterion("build_identifier not like", value, "buildIdentifier");
            return (Criteria) this;
        }

        public Criteria andBuildIdentifierIn(List<String> values) {
            addCriterion("build_identifier in", values, "buildIdentifier");
            return (Criteria) this;
        }

        public Criteria andBuildIdentifierNotIn(List<String> values) {
            addCriterion("build_identifier not in", values, "buildIdentifier");
            return (Criteria) this;
        }

        public Criteria andBuildIdentifierBetween(String value1, String value2) {
            addCriterion("build_identifier between", value1, value2, "buildIdentifier");
            return (Criteria) this;
        }

        public Criteria andBuildIdentifierNotBetween(String value1, String value2) {
            addCriterion("build_identifier not between", value1, value2, "buildIdentifier");
            return (Criteria) this;
        }

        public Criteria andEstateNameIsNull() {
            addCriterion("estate_name is null");
            return (Criteria) this;
        }

        public Criteria andEstateNameIsNotNull() {
            addCriterion("estate_name is not null");
            return (Criteria) this;
        }

        public Criteria andEstateNameEqualTo(String value) {
            addCriterion("estate_name =", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameNotEqualTo(String value) {
            addCriterion("estate_name <>", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameGreaterThan(String value) {
            addCriterion("estate_name >", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameGreaterThanOrEqualTo(String value) {
            addCriterion("estate_name >=", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameLessThan(String value) {
            addCriterion("estate_name <", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameLessThanOrEqualTo(String value) {
            addCriterion("estate_name <=", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameLike(String value) {
            addCriterion("estate_name like", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameNotLike(String value) {
            addCriterion("estate_name not like", value, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameIn(List<String> values) {
            addCriterion("estate_name in", values, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameNotIn(List<String> values) {
            addCriterion("estate_name not in", values, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameBetween(String value1, String value2) {
            addCriterion("estate_name between", value1, value2, "estateName");
            return (Criteria) this;
        }

        public Criteria andEstateNameNotBetween(String value1, String value2) {
            addCriterion("estate_name not between", value1, value2, "estateName");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTemporaryIsNull() {
            addCriterion("temporary is null");
            return (Criteria) this;
        }

        public Criteria andTemporaryIsNotNull() {
            addCriterion("temporary is not null");
            return (Criteria) this;
        }

        public Criteria andTemporaryEqualTo(Boolean value) {
            addCriterion("temporary =", value, "temporary");
            return (Criteria) this;
        }

        public Criteria andTemporaryNotEqualTo(Boolean value) {
            addCriterion("temporary <>", value, "temporary");
            return (Criteria) this;
        }

        public Criteria andTemporaryGreaterThan(Boolean value) {
            addCriterion("temporary >", value, "temporary");
            return (Criteria) this;
        }

        public Criteria andTemporaryGreaterThanOrEqualTo(Boolean value) {
            addCriterion("temporary >=", value, "temporary");
            return (Criteria) this;
        }

        public Criteria andTemporaryLessThan(Boolean value) {
            addCriterion("temporary <", value, "temporary");
            return (Criteria) this;
        }

        public Criteria andTemporaryLessThanOrEqualTo(Boolean value) {
            addCriterion("temporary <=", value, "temporary");
            return (Criteria) this;
        }

        public Criteria andTemporaryIn(List<Boolean> values) {
            addCriterion("temporary in", values, "temporary");
            return (Criteria) this;
        }

        public Criteria andTemporaryNotIn(List<Boolean> values) {
            addCriterion("temporary not in", values, "temporary");
            return (Criteria) this;
        }

        public Criteria andTemporaryBetween(Boolean value1, Boolean value2) {
            addCriterion("temporary between", value1, value2, "temporary");
            return (Criteria) this;
        }

        public Criteria andTemporaryNotBetween(Boolean value1, Boolean value2) {
            addCriterion("temporary not between", value1, value2, "temporary");
            return (Criteria) this;
        }

        public Criteria andIndustryIsNull() {
            addCriterion("industry is null");
            return (Criteria) this;
        }

        public Criteria andIndustryIsNotNull() {
            addCriterion("industry is not null");
            return (Criteria) this;
        }

        public Criteria andIndustryEqualTo(String value) {
            addCriterion("industry =", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotEqualTo(String value) {
            addCriterion("industry <>", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryGreaterThan(String value) {
            addCriterion("industry >", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryGreaterThanOrEqualTo(String value) {
            addCriterion("industry >=", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLessThan(String value) {
            addCriterion("industry <", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLessThanOrEqualTo(String value) {
            addCriterion("industry <=", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLike(String value) {
            addCriterion("industry like", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotLike(String value) {
            addCriterion("industry not like", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryIn(List<String> values) {
            addCriterion("industry in", values, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotIn(List<String> values) {
            addCriterion("industry not in", values, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryBetween(String value1, String value2) {
            addCriterion("industry between", value1, value2, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotBetween(String value1, String value2) {
            addCriterion("industry not between", value1, value2, "industry");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdIsNull() {
            addCriterion("case_estate_id is null");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdIsNotNull() {
            addCriterion("case_estate_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdEqualTo(Integer value) {
            addCriterion("case_estate_id =", value, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdNotEqualTo(Integer value) {
            addCriterion("case_estate_id <>", value, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdGreaterThan(Integer value) {
            addCriterion("case_estate_id >", value, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_estate_id >=", value, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdLessThan(Integer value) {
            addCriterion("case_estate_id <", value, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdLessThanOrEqualTo(Integer value) {
            addCriterion("case_estate_id <=", value, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdIn(List<Integer> values) {
            addCriterion("case_estate_id in", values, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdNotIn(List<Integer> values) {
            addCriterion("case_estate_id not in", values, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdBetween(Integer value1, Integer value2) {
            addCriterion("case_estate_id between", value1, value2, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseEstateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("case_estate_id not between", value1, value2, "caseEstateId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdIsNull() {
            addCriterion("case_building_main_id is null");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdIsNotNull() {
            addCriterion("case_building_main_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdEqualTo(Integer value) {
            addCriterion("case_building_main_id =", value, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdNotEqualTo(Integer value) {
            addCriterion("case_building_main_id <>", value, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdGreaterThan(Integer value) {
            addCriterion("case_building_main_id >", value, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_building_main_id >=", value, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdLessThan(Integer value) {
            addCriterion("case_building_main_id <", value, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdLessThanOrEqualTo(Integer value) {
            addCriterion("case_building_main_id <=", value, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdIn(List<Integer> values) {
            addCriterion("case_building_main_id in", values, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdNotIn(List<Integer> values) {
            addCriterion("case_building_main_id not in", values, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdBetween(Integer value1, Integer value2) {
            addCriterion("case_building_main_id between", value1, value2, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseBuildingMainIdNotBetween(Integer value1, Integer value2) {
            addCriterion("case_building_main_id not between", value1, value2, "caseBuildingMainId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdIsNull() {
            addCriterion("case_unit_id is null");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdIsNotNull() {
            addCriterion("case_unit_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdEqualTo(Integer value) {
            addCriterion("case_unit_id =", value, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdNotEqualTo(Integer value) {
            addCriterion("case_unit_id <>", value, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdGreaterThan(Integer value) {
            addCriterion("case_unit_id >", value, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_unit_id >=", value, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdLessThan(Integer value) {
            addCriterion("case_unit_id <", value, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdLessThanOrEqualTo(Integer value) {
            addCriterion("case_unit_id <=", value, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdIn(List<Integer> values) {
            addCriterion("case_unit_id in", values, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdNotIn(List<Integer> values) {
            addCriterion("case_unit_id not in", values, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdBetween(Integer value1, Integer value2) {
            addCriterion("case_unit_id between", value1, value2, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseUnitIdNotBetween(Integer value1, Integer value2) {
            addCriterion("case_unit_id not between", value1, value2, "caseUnitId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdIsNull() {
            addCriterion("case_house_id is null");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdIsNotNull() {
            addCriterion("case_house_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdEqualTo(Integer value) {
            addCriterion("case_house_id =", value, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdNotEqualTo(Integer value) {
            addCriterion("case_house_id <>", value, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdGreaterThan(Integer value) {
            addCriterion("case_house_id >", value, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_house_id >=", value, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdLessThan(Integer value) {
            addCriterion("case_house_id <", value, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("case_house_id <=", value, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdIn(List<Integer> values) {
            addCriterion("case_house_id in", values, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdNotIn(List<Integer> values) {
            addCriterion("case_house_id not in", values, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdBetween(Integer value1, Integer value2) {
            addCriterion("case_house_id between", value1, value2, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andCaseHouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("case_house_id not between", value1, value2, "caseHouseId");
            return (Criteria) this;
        }

        public Criteria andEstatePartInFlagIsNull() {
            addCriterion("estate_part_in_flag is null");
            return (Criteria) this;
        }

        public Criteria andEstatePartInFlagIsNotNull() {
            addCriterion("estate_part_in_flag is not null");
            return (Criteria) this;
        }

        public Criteria andEstatePartInFlagEqualTo(Boolean value) {
            addCriterion("estate_part_in_flag =", value, "estatePartInFlag");
            return (Criteria) this;
        }

        public Criteria andEstatePartInFlagNotEqualTo(Boolean value) {
            addCriterion("estate_part_in_flag <>", value, "estatePartInFlag");
            return (Criteria) this;
        }

        public Criteria andEstatePartInFlagGreaterThan(Boolean value) {
            addCriterion("estate_part_in_flag >", value, "estatePartInFlag");
            return (Criteria) this;
        }

        public Criteria andEstatePartInFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("estate_part_in_flag >=", value, "estatePartInFlag");
            return (Criteria) this;
        }

        public Criteria andEstatePartInFlagLessThan(Boolean value) {
            addCriterion("estate_part_in_flag <", value, "estatePartInFlag");
            return (Criteria) this;
        }

        public Criteria andEstatePartInFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("estate_part_in_flag <=", value, "estatePartInFlag");
            return (Criteria) this;
        }

        public Criteria andEstatePartInFlagIn(List<Boolean> values) {
            addCriterion("estate_part_in_flag in", values, "estatePartInFlag");
            return (Criteria) this;
        }

        public Criteria andEstatePartInFlagNotIn(List<Boolean> values) {
            addCriterion("estate_part_in_flag not in", values, "estatePartInFlag");
            return (Criteria) this;
        }

        public Criteria andEstatePartInFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("estate_part_in_flag between", value1, value2, "estatePartInFlag");
            return (Criteria) this;
        }

        public Criteria andEstatePartInFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("estate_part_in_flag not between", value1, value2, "estatePartInFlag");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInFlagIsNull() {
            addCriterion("building_part_in_flag is null");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInFlagIsNotNull() {
            addCriterion("building_part_in_flag is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInFlagEqualTo(Boolean value) {
            addCriterion("building_part_in_flag =", value, "buildingPartInFlag");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInFlagNotEqualTo(Boolean value) {
            addCriterion("building_part_in_flag <>", value, "buildingPartInFlag");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInFlagGreaterThan(Boolean value) {
            addCriterion("building_part_in_flag >", value, "buildingPartInFlag");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("building_part_in_flag >=", value, "buildingPartInFlag");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInFlagLessThan(Boolean value) {
            addCriterion("building_part_in_flag <", value, "buildingPartInFlag");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("building_part_in_flag <=", value, "buildingPartInFlag");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInFlagIn(List<Boolean> values) {
            addCriterion("building_part_in_flag in", values, "buildingPartInFlag");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInFlagNotIn(List<Boolean> values) {
            addCriterion("building_part_in_flag not in", values, "buildingPartInFlag");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("building_part_in_flag between", value1, value2, "buildingPartInFlag");
            return (Criteria) this;
        }

        public Criteria andBuildingPartInFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("building_part_in_flag not between", value1, value2, "buildingPartInFlag");
            return (Criteria) this;
        }

        public Criteria andUnitPartInFlagIsNull() {
            addCriterion("unit_part_in_flag is null");
            return (Criteria) this;
        }

        public Criteria andUnitPartInFlagIsNotNull() {
            addCriterion("unit_part_in_flag is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPartInFlagEqualTo(Boolean value) {
            addCriterion("unit_part_in_flag =", value, "unitPartInFlag");
            return (Criteria) this;
        }

        public Criteria andUnitPartInFlagNotEqualTo(Boolean value) {
            addCriterion("unit_part_in_flag <>", value, "unitPartInFlag");
            return (Criteria) this;
        }

        public Criteria andUnitPartInFlagGreaterThan(Boolean value) {
            addCriterion("unit_part_in_flag >", value, "unitPartInFlag");
            return (Criteria) this;
        }

        public Criteria andUnitPartInFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("unit_part_in_flag >=", value, "unitPartInFlag");
            return (Criteria) this;
        }

        public Criteria andUnitPartInFlagLessThan(Boolean value) {
            addCriterion("unit_part_in_flag <", value, "unitPartInFlag");
            return (Criteria) this;
        }

        public Criteria andUnitPartInFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("unit_part_in_flag <=", value, "unitPartInFlag");
            return (Criteria) this;
        }

        public Criteria andUnitPartInFlagIn(List<Boolean> values) {
            addCriterion("unit_part_in_flag in", values, "unitPartInFlag");
            return (Criteria) this;
        }

        public Criteria andUnitPartInFlagNotIn(List<Boolean> values) {
            addCriterion("unit_part_in_flag not in", values, "unitPartInFlag");
            return (Criteria) this;
        }

        public Criteria andUnitPartInFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("unit_part_in_flag between", value1, value2, "unitPartInFlag");
            return (Criteria) this;
        }

        public Criteria andUnitPartInFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("unit_part_in_flag not between", value1, value2, "unitPartInFlag");
            return (Criteria) this;
        }

        public Criteria andHousePartInFlagIsNull() {
            addCriterion("house_part_in_flag is null");
            return (Criteria) this;
        }

        public Criteria andHousePartInFlagIsNotNull() {
            addCriterion("house_part_in_flag is not null");
            return (Criteria) this;
        }

        public Criteria andHousePartInFlagEqualTo(Boolean value) {
            addCriterion("house_part_in_flag =", value, "housePartInFlag");
            return (Criteria) this;
        }

        public Criteria andHousePartInFlagNotEqualTo(Boolean value) {
            addCriterion("house_part_in_flag <>", value, "housePartInFlag");
            return (Criteria) this;
        }

        public Criteria andHousePartInFlagGreaterThan(Boolean value) {
            addCriterion("house_part_in_flag >", value, "housePartInFlag");
            return (Criteria) this;
        }

        public Criteria andHousePartInFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("house_part_in_flag >=", value, "housePartInFlag");
            return (Criteria) this;
        }

        public Criteria andHousePartInFlagLessThan(Boolean value) {
            addCriterion("house_part_in_flag <", value, "housePartInFlag");
            return (Criteria) this;
        }

        public Criteria andHousePartInFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("house_part_in_flag <=", value, "housePartInFlag");
            return (Criteria) this;
        }

        public Criteria andHousePartInFlagIn(List<Boolean> values) {
            addCriterion("house_part_in_flag in", values, "housePartInFlag");
            return (Criteria) this;
        }

        public Criteria andHousePartInFlagNotIn(List<Boolean> values) {
            addCriterion("house_part_in_flag not in", values, "housePartInFlag");
            return (Criteria) this;
        }

        public Criteria andHousePartInFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("house_part_in_flag between", value1, value2, "housePartInFlag");
            return (Criteria) this;
        }

        public Criteria andHousePartInFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("house_part_in_flag not between", value1, value2, "housePartInFlag");
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