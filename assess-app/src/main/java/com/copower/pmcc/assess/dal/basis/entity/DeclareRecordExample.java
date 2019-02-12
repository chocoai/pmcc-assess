package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeclareRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeclareRecordExample() {
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

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Integer value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Integer value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Integer value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Integer value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Integer> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Integer> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andDataTableNameIsNull() {
            addCriterion("data_table_name is null");
            return (Criteria) this;
        }

        public Criteria andDataTableNameIsNotNull() {
            addCriterion("data_table_name is not null");
            return (Criteria) this;
        }

        public Criteria andDataTableNameEqualTo(String value) {
            addCriterion("data_table_name =", value, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameNotEqualTo(String value) {
            addCriterion("data_table_name <>", value, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameGreaterThan(String value) {
            addCriterion("data_table_name >", value, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("data_table_name >=", value, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameLessThan(String value) {
            addCriterion("data_table_name <", value, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameLessThanOrEqualTo(String value) {
            addCriterion("data_table_name <=", value, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameLike(String value) {
            addCriterion("data_table_name like", value, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameNotLike(String value) {
            addCriterion("data_table_name not like", value, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameIn(List<String> values) {
            addCriterion("data_table_name in", values, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameNotIn(List<String> values) {
            addCriterion("data_table_name not in", values, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameBetween(String value1, String value2) {
            addCriterion("data_table_name between", value1, value2, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameNotBetween(String value1, String value2) {
            addCriterion("data_table_name not between", value1, value2, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableIdIsNull() {
            addCriterion("data_table_id is null");
            return (Criteria) this;
        }

        public Criteria andDataTableIdIsNotNull() {
            addCriterion("data_table_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataTableIdEqualTo(Integer value) {
            addCriterion("data_table_id =", value, "dataTableId");
            return (Criteria) this;
        }

        public Criteria andDataTableIdNotEqualTo(Integer value) {
            addCriterion("data_table_id <>", value, "dataTableId");
            return (Criteria) this;
        }

        public Criteria andDataTableIdGreaterThan(Integer value) {
            addCriterion("data_table_id >", value, "dataTableId");
            return (Criteria) this;
        }

        public Criteria andDataTableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_table_id >=", value, "dataTableId");
            return (Criteria) this;
        }

        public Criteria andDataTableIdLessThan(Integer value) {
            addCriterion("data_table_id <", value, "dataTableId");
            return (Criteria) this;
        }

        public Criteria andDataTableIdLessThanOrEqualTo(Integer value) {
            addCriterion("data_table_id <=", value, "dataTableId");
            return (Criteria) this;
        }

        public Criteria andDataTableIdIn(List<Integer> values) {
            addCriterion("data_table_id in", values, "dataTableId");
            return (Criteria) this;
        }

        public Criteria andDataTableIdNotIn(List<Integer> values) {
            addCriterion("data_table_id not in", values, "dataTableId");
            return (Criteria) this;
        }

        public Criteria andDataTableIdBetween(Integer value1, Integer value2) {
            addCriterion("data_table_id between", value1, value2, "dataTableId");
            return (Criteria) this;
        }

        public Criteria andDataTableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("data_table_id not between", value1, value2, "dataTableId");
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

        public Criteria andOwnershipIsNull() {
            addCriterion("ownership is null");
            return (Criteria) this;
        }

        public Criteria andOwnershipIsNotNull() {
            addCriterion("ownership is not null");
            return (Criteria) this;
        }

        public Criteria andOwnershipEqualTo(String value) {
            addCriterion("ownership =", value, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipNotEqualTo(String value) {
            addCriterion("ownership <>", value, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipGreaterThan(String value) {
            addCriterion("ownership >", value, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipGreaterThanOrEqualTo(String value) {
            addCriterion("ownership >=", value, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipLessThan(String value) {
            addCriterion("ownership <", value, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipLessThanOrEqualTo(String value) {
            addCriterion("ownership <=", value, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipLike(String value) {
            addCriterion("ownership like", value, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipNotLike(String value) {
            addCriterion("ownership not like", value, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipIn(List<String> values) {
            addCriterion("ownership in", values, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipNotIn(List<String> values) {
            addCriterion("ownership not in", values, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipBetween(String value1, String value2) {
            addCriterion("ownership between", value1, value2, "ownership");
            return (Criteria) this;
        }

        public Criteria andOwnershipNotBetween(String value1, String value2) {
            addCriterion("ownership not between", value1, value2, "ownership");
            return (Criteria) this;
        }

        public Criteria andSeatIsNull() {
            addCriterion("seat is null");
            return (Criteria) this;
        }

        public Criteria andSeatIsNotNull() {
            addCriterion("seat is not null");
            return (Criteria) this;
        }

        public Criteria andSeatEqualTo(String value) {
            addCriterion("seat =", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatNotEqualTo(String value) {
            addCriterion("seat <>", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatGreaterThan(String value) {
            addCriterion("seat >", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatGreaterThanOrEqualTo(String value) {
            addCriterion("seat >=", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatLessThan(String value) {
            addCriterion("seat <", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatLessThanOrEqualTo(String value) {
            addCriterion("seat <=", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatLike(String value) {
            addCriterion("seat like", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatNotLike(String value) {
            addCriterion("seat not like", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatIn(List<String> values) {
            addCriterion("seat in", values, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatNotIn(List<String> values) {
            addCriterion("seat not in", values, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatBetween(String value1, String value2) {
            addCriterion("seat between", value1, value2, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatNotBetween(String value1, String value2) {
            addCriterion("seat not between", value1, value2, "seat");
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

        public Criteria andAttachedNumberIsNull() {
            addCriterion("attached_number is null");
            return (Criteria) this;
        }

        public Criteria andAttachedNumberIsNotNull() {
            addCriterion("attached_number is not null");
            return (Criteria) this;
        }

        public Criteria andAttachedNumberEqualTo(String value) {
            addCriterion("attached_number =", value, "attachedNumber");
            return (Criteria) this;
        }

        public Criteria andAttachedNumberNotEqualTo(String value) {
            addCriterion("attached_number <>", value, "attachedNumber");
            return (Criteria) this;
        }

        public Criteria andAttachedNumberGreaterThan(String value) {
            addCriterion("attached_number >", value, "attachedNumber");
            return (Criteria) this;
        }

        public Criteria andAttachedNumberGreaterThanOrEqualTo(String value) {
            addCriterion("attached_number >=", value, "attachedNumber");
            return (Criteria) this;
        }

        public Criteria andAttachedNumberLessThan(String value) {
            addCriterion("attached_number <", value, "attachedNumber");
            return (Criteria) this;
        }

        public Criteria andAttachedNumberLessThanOrEqualTo(String value) {
            addCriterion("attached_number <=", value, "attachedNumber");
            return (Criteria) this;
        }

        public Criteria andAttachedNumberLike(String value) {
            addCriterion("attached_number like", value, "attachedNumber");
            return (Criteria) this;
        }

        public Criteria andAttachedNumberNotLike(String value) {
            addCriterion("attached_number not like", value, "attachedNumber");
            return (Criteria) this;
        }

        public Criteria andAttachedNumberIn(List<String> values) {
            addCriterion("attached_number in", values, "attachedNumber");
            return (Criteria) this;
        }

        public Criteria andAttachedNumberNotIn(List<String> values) {
            addCriterion("attached_number not in", values, "attachedNumber");
            return (Criteria) this;
        }

        public Criteria andAttachedNumberBetween(String value1, String value2) {
            addCriterion("attached_number between", value1, value2, "attachedNumber");
            return (Criteria) this;
        }

        public Criteria andAttachedNumberNotBetween(String value1, String value2) {
            addCriterion("attached_number not between", value1, value2, "attachedNumber");
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

        public Criteria andBuildingNumberEqualTo(String value) {
            addCriterion("building_number =", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberNotEqualTo(String value) {
            addCriterion("building_number <>", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberGreaterThan(String value) {
            addCriterion("building_number >", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberGreaterThanOrEqualTo(String value) {
            addCriterion("building_number >=", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberLessThan(String value) {
            addCriterion("building_number <", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberLessThanOrEqualTo(String value) {
            addCriterion("building_number <=", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberLike(String value) {
            addCriterion("building_number like", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberNotLike(String value) {
            addCriterion("building_number not like", value, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberIn(List<String> values) {
            addCriterion("building_number in", values, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberNotIn(List<String> values) {
            addCriterion("building_number not in", values, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberBetween(String value1, String value2) {
            addCriterion("building_number between", value1, value2, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andBuildingNumberNotBetween(String value1, String value2) {
            addCriterion("building_number not between", value1, value2, "buildingNumber");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
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

        public Criteria andRoomNumberIsNull() {
            addCriterion("room_number is null");
            return (Criteria) this;
        }

        public Criteria andRoomNumberIsNotNull() {
            addCriterion("room_number is not null");
            return (Criteria) this;
        }

        public Criteria andRoomNumberEqualTo(String value) {
            addCriterion("room_number =", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberNotEqualTo(String value) {
            addCriterion("room_number <>", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberGreaterThan(String value) {
            addCriterion("room_number >", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberGreaterThanOrEqualTo(String value) {
            addCriterion("room_number >=", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberLessThan(String value) {
            addCriterion("room_number <", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberLessThanOrEqualTo(String value) {
            addCriterion("room_number <=", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberLike(String value) {
            addCriterion("room_number like", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberNotLike(String value) {
            addCriterion("room_number not like", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberIn(List<String> values) {
            addCriterion("room_number in", values, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberNotIn(List<String> values) {
            addCriterion("room_number not in", values, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberBetween(String value1, String value2) {
            addCriterion("room_number between", value1, value2, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberNotBetween(String value1, String value2) {
            addCriterion("room_number not between", value1, value2, "roomNumber");
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

        public Criteria andPublicSituationIsNull() {
            addCriterion("public_situation is null");
            return (Criteria) this;
        }

        public Criteria andPublicSituationIsNotNull() {
            addCriterion("public_situation is not null");
            return (Criteria) this;
        }

        public Criteria andPublicSituationEqualTo(String value) {
            addCriterion("public_situation =", value, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationNotEqualTo(String value) {
            addCriterion("public_situation <>", value, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationGreaterThan(String value) {
            addCriterion("public_situation >", value, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationGreaterThanOrEqualTo(String value) {
            addCriterion("public_situation >=", value, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationLessThan(String value) {
            addCriterion("public_situation <", value, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationLessThanOrEqualTo(String value) {
            addCriterion("public_situation <=", value, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationLike(String value) {
            addCriterion("public_situation like", value, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationNotLike(String value) {
            addCriterion("public_situation not like", value, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationIn(List<String> values) {
            addCriterion("public_situation in", values, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationNotIn(List<String> values) {
            addCriterion("public_situation not in", values, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationBetween(String value1, String value2) {
            addCriterion("public_situation between", value1, value2, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationNotBetween(String value1, String value2) {
            addCriterion("public_situation not between", value1, value2, "publicSituation");
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

        public Criteria andAreaGroupIdIsNull() {
            addCriterion("area_group_id is null");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdIsNotNull() {
            addCriterion("area_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdEqualTo(Integer value) {
            addCriterion("area_group_id =", value, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdNotEqualTo(Integer value) {
            addCriterion("area_group_id <>", value, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdGreaterThan(Integer value) {
            addCriterion("area_group_id >", value, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("area_group_id >=", value, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdLessThan(Integer value) {
            addCriterion("area_group_id <", value, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("area_group_id <=", value, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdIn(List<Integer> values) {
            addCriterion("area_group_id in", values, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdNotIn(List<Integer> values) {
            addCriterion("area_group_id not in", values, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("area_group_id between", value1, value2, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andAreaGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("area_group_id not between", value1, value2, "areaGroupId");
            return (Criteria) this;
        }

        public Criteria andHouseUseEndDateIsNull() {
            addCriterion("house_use_end_date is null");
            return (Criteria) this;
        }

        public Criteria andHouseUseEndDateIsNotNull() {
            addCriterion("house_use_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andHouseUseEndDateEqualTo(Date value) {
            addCriterion("house_use_end_date =", value, "houseUseEndDate");
            return (Criteria) this;
        }

        public Criteria andHouseUseEndDateNotEqualTo(Date value) {
            addCriterion("house_use_end_date <>", value, "houseUseEndDate");
            return (Criteria) this;
        }

        public Criteria andHouseUseEndDateGreaterThan(Date value) {
            addCriterion("house_use_end_date >", value, "houseUseEndDate");
            return (Criteria) this;
        }

        public Criteria andHouseUseEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("house_use_end_date >=", value, "houseUseEndDate");
            return (Criteria) this;
        }

        public Criteria andHouseUseEndDateLessThan(Date value) {
            addCriterion("house_use_end_date <", value, "houseUseEndDate");
            return (Criteria) this;
        }

        public Criteria andHouseUseEndDateLessThanOrEqualTo(Date value) {
            addCriterion("house_use_end_date <=", value, "houseUseEndDate");
            return (Criteria) this;
        }

        public Criteria andHouseUseEndDateIn(List<Date> values) {
            addCriterion("house_use_end_date in", values, "houseUseEndDate");
            return (Criteria) this;
        }

        public Criteria andHouseUseEndDateNotIn(List<Date> values) {
            addCriterion("house_use_end_date not in", values, "houseUseEndDate");
            return (Criteria) this;
        }

        public Criteria andHouseUseEndDateBetween(Date value1, Date value2) {
            addCriterion("house_use_end_date between", value1, value2, "houseUseEndDate");
            return (Criteria) this;
        }

        public Criteria andHouseUseEndDateNotBetween(Date value1, Date value2) {
            addCriterion("house_use_end_date not between", value1, value2, "houseUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateIsNull() {
            addCriterion("land_use_end_date is null");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateIsNotNull() {
            addCriterion("land_use_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateEqualTo(Date value) {
            addCriterion("land_use_end_date =", value, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateNotEqualTo(Date value) {
            addCriterion("land_use_end_date <>", value, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateGreaterThan(Date value) {
            addCriterion("land_use_end_date >", value, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("land_use_end_date >=", value, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateLessThan(Date value) {
            addCriterion("land_use_end_date <", value, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateLessThanOrEqualTo(Date value) {
            addCriterion("land_use_end_date <=", value, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateIn(List<Date> values) {
            addCriterion("land_use_end_date in", values, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateNotIn(List<Date> values) {
            addCriterion("land_use_end_date not in", values, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateBetween(Date value1, Date value2) {
            addCriterion("land_use_end_date between", value1, value2, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andLandUseEndDateNotBetween(Date value1, Date value2) {
            addCriterion("land_use_end_date not between", value1, value2, "landUseEndDate");
            return (Criteria) this;
        }

        public Criteria andInventoryContentKeyIsNull() {
            addCriterion("inventory_content_key is null");
            return (Criteria) this;
        }

        public Criteria andInventoryContentKeyIsNotNull() {
            addCriterion("inventory_content_key is not null");
            return (Criteria) this;
        }

        public Criteria andInventoryContentKeyEqualTo(String value) {
            addCriterion("inventory_content_key =", value, "inventoryContentKey");
            return (Criteria) this;
        }

        public Criteria andInventoryContentKeyNotEqualTo(String value) {
            addCriterion("inventory_content_key <>", value, "inventoryContentKey");
            return (Criteria) this;
        }

        public Criteria andInventoryContentKeyGreaterThan(String value) {
            addCriterion("inventory_content_key >", value, "inventoryContentKey");
            return (Criteria) this;
        }

        public Criteria andInventoryContentKeyGreaterThanOrEqualTo(String value) {
            addCriterion("inventory_content_key >=", value, "inventoryContentKey");
            return (Criteria) this;
        }

        public Criteria andInventoryContentKeyLessThan(String value) {
            addCriterion("inventory_content_key <", value, "inventoryContentKey");
            return (Criteria) this;
        }

        public Criteria andInventoryContentKeyLessThanOrEqualTo(String value) {
            addCriterion("inventory_content_key <=", value, "inventoryContentKey");
            return (Criteria) this;
        }

        public Criteria andInventoryContentKeyLike(String value) {
            addCriterion("inventory_content_key like", value, "inventoryContentKey");
            return (Criteria) this;
        }

        public Criteria andInventoryContentKeyNotLike(String value) {
            addCriterion("inventory_content_key not like", value, "inventoryContentKey");
            return (Criteria) this;
        }

        public Criteria andInventoryContentKeyIn(List<String> values) {
            addCriterion("inventory_content_key in", values, "inventoryContentKey");
            return (Criteria) this;
        }

        public Criteria andInventoryContentKeyNotIn(List<String> values) {
            addCriterion("inventory_content_key not in", values, "inventoryContentKey");
            return (Criteria) this;
        }

        public Criteria andInventoryContentKeyBetween(String value1, String value2) {
            addCriterion("inventory_content_key between", value1, value2, "inventoryContentKey");
            return (Criteria) this;
        }

        public Criteria andInventoryContentKeyNotBetween(String value1, String value2) {
            addCriterion("inventory_content_key not between", value1, value2, "inventoryContentKey");
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

        public Criteria andAssessAreaIsNull() {
            addCriterion("assess_area is null");
            return (Criteria) this;
        }

        public Criteria andAssessAreaIsNotNull() {
            addCriterion("assess_area is not null");
            return (Criteria) this;
        }

        public Criteria andAssessAreaEqualTo(BigDecimal value) {
            addCriterion("assess_area =", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaNotEqualTo(BigDecimal value) {
            addCriterion("assess_area <>", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaGreaterThan(BigDecimal value) {
            addCriterion("assess_area >", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_area >=", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaLessThan(BigDecimal value) {
            addCriterion("assess_area <", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_area <=", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaIn(List<BigDecimal> values) {
            addCriterion("assess_area in", values, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaNotIn(List<BigDecimal> values) {
            addCriterion("assess_area not in", values, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_area between", value1, value2, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_area not between", value1, value2, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAlreadyOutAreaIsNull() {
            addCriterion("already_out_area is null");
            return (Criteria) this;
        }

        public Criteria andAlreadyOutAreaIsNotNull() {
            addCriterion("already_out_area is not null");
            return (Criteria) this;
        }

        public Criteria andAlreadyOutAreaEqualTo(BigDecimal value) {
            addCriterion("already_out_area =", value, "alreadyOutArea");
            return (Criteria) this;
        }

        public Criteria andAlreadyOutAreaNotEqualTo(BigDecimal value) {
            addCriterion("already_out_area <>", value, "alreadyOutArea");
            return (Criteria) this;
        }

        public Criteria andAlreadyOutAreaGreaterThan(BigDecimal value) {
            addCriterion("already_out_area >", value, "alreadyOutArea");
            return (Criteria) this;
        }

        public Criteria andAlreadyOutAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("already_out_area >=", value, "alreadyOutArea");
            return (Criteria) this;
        }

        public Criteria andAlreadyOutAreaLessThan(BigDecimal value) {
            addCriterion("already_out_area <", value, "alreadyOutArea");
            return (Criteria) this;
        }

        public Criteria andAlreadyOutAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("already_out_area <=", value, "alreadyOutArea");
            return (Criteria) this;
        }

        public Criteria andAlreadyOutAreaIn(List<BigDecimal> values) {
            addCriterion("already_out_area in", values, "alreadyOutArea");
            return (Criteria) this;
        }

        public Criteria andAlreadyOutAreaNotIn(List<BigDecimal> values) {
            addCriterion("already_out_area not in", values, "alreadyOutArea");
            return (Criteria) this;
        }

        public Criteria andAlreadyOutAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("already_out_area between", value1, value2, "alreadyOutArea");
            return (Criteria) this;
        }

        public Criteria andAlreadyOutAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("already_out_area not between", value1, value2, "alreadyOutArea");
            return (Criteria) this;
        }

        public Criteria andBisGenerateAllIsNull() {
            addCriterion("bis_generate_all is null");
            return (Criteria) this;
        }

        public Criteria andBisGenerateAllIsNotNull() {
            addCriterion("bis_generate_all is not null");
            return (Criteria) this;
        }

        public Criteria andBisGenerateAllEqualTo(Boolean value) {
            addCriterion("bis_generate_all =", value, "bisGenerateAll");
            return (Criteria) this;
        }

        public Criteria andBisGenerateAllNotEqualTo(Boolean value) {
            addCriterion("bis_generate_all <>", value, "bisGenerateAll");
            return (Criteria) this;
        }

        public Criteria andBisGenerateAllGreaterThan(Boolean value) {
            addCriterion("bis_generate_all >", value, "bisGenerateAll");
            return (Criteria) this;
        }

        public Criteria andBisGenerateAllGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_generate_all >=", value, "bisGenerateAll");
            return (Criteria) this;
        }

        public Criteria andBisGenerateAllLessThan(Boolean value) {
            addCriterion("bis_generate_all <", value, "bisGenerateAll");
            return (Criteria) this;
        }

        public Criteria andBisGenerateAllLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_generate_all <=", value, "bisGenerateAll");
            return (Criteria) this;
        }

        public Criteria andBisGenerateAllIn(List<Boolean> values) {
            addCriterion("bis_generate_all in", values, "bisGenerateAll");
            return (Criteria) this;
        }

        public Criteria andBisGenerateAllNotIn(List<Boolean> values) {
            addCriterion("bis_generate_all not in", values, "bisGenerateAll");
            return (Criteria) this;
        }

        public Criteria andBisGenerateAllBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_generate_all between", value1, value2, "bisGenerateAll");
            return (Criteria) this;
        }

        public Criteria andBisGenerateAllNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_generate_all not between", value1, value2, "bisGenerateAll");
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