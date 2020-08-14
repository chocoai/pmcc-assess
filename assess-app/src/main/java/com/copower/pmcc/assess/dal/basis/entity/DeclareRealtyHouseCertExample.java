package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeclareRealtyHouseCertExample {
    /**
     * tb_declare_realty_house_cert
     */
    protected String orderByClause;

    /**
     * tb_declare_realty_house_cert
     */
    protected boolean distinct;

    /**
     * tb_declare_realty_house_cert
     */
    protected List<Criteria> oredCriteria;

    public DeclareRealtyHouseCertExample() {
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
     * tb_declare_realty_house_cert
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

        public Criteria andAutoInitNumberIsNull() {
            addCriterion("auto_init_number is null");
            return (Criteria) this;
        }

        public Criteria andAutoInitNumberIsNotNull() {
            addCriterion("auto_init_number is not null");
            return (Criteria) this;
        }

        public Criteria andAutoInitNumberEqualTo(Integer value) {
            addCriterion("auto_init_number =", value, "autoInitNumber");
            return (Criteria) this;
        }

        public Criteria andAutoInitNumberNotEqualTo(Integer value) {
            addCriterion("auto_init_number <>", value, "autoInitNumber");
            return (Criteria) this;
        }

        public Criteria andAutoInitNumberGreaterThan(Integer value) {
            addCriterion("auto_init_number >", value, "autoInitNumber");
            return (Criteria) this;
        }

        public Criteria andAutoInitNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("auto_init_number >=", value, "autoInitNumber");
            return (Criteria) this;
        }

        public Criteria andAutoInitNumberLessThan(Integer value) {
            addCriterion("auto_init_number <", value, "autoInitNumber");
            return (Criteria) this;
        }

        public Criteria andAutoInitNumberLessThanOrEqualTo(Integer value) {
            addCriterion("auto_init_number <=", value, "autoInitNumber");
            return (Criteria) this;
        }

        public Criteria andAutoInitNumberIn(List<Integer> values) {
            addCriterion("auto_init_number in", values, "autoInitNumber");
            return (Criteria) this;
        }

        public Criteria andAutoInitNumberNotIn(List<Integer> values) {
            addCriterion("auto_init_number not in", values, "autoInitNumber");
            return (Criteria) this;
        }

        public Criteria andAutoInitNumberBetween(Integer value1, Integer value2) {
            addCriterion("auto_init_number between", value1, value2, "autoInitNumber");
            return (Criteria) this;
        }

        public Criteria andAutoInitNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("auto_init_number not between", value1, value2, "autoInitNumber");
            return (Criteria) this;
        }

        public Criteria andCertNameIsNull() {
            addCriterion("cert_name is null");
            return (Criteria) this;
        }

        public Criteria andCertNameIsNotNull() {
            addCriterion("cert_name is not null");
            return (Criteria) this;
        }

        public Criteria andCertNameEqualTo(String value) {
            addCriterion("cert_name =", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameNotEqualTo(String value) {
            addCriterion("cert_name <>", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameGreaterThan(String value) {
            addCriterion("cert_name >", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameGreaterThanOrEqualTo(String value) {
            addCriterion("cert_name >=", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameLessThan(String value) {
            addCriterion("cert_name <", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameLessThanOrEqualTo(String value) {
            addCriterion("cert_name <=", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameLike(String value) {
            addCriterion("cert_name like", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameNotLike(String value) {
            addCriterion("cert_name not like", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameIn(List<String> values) {
            addCriterion("cert_name in", values, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameNotIn(List<String> values) {
            addCriterion("cert_name not in", values, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameBetween(String value1, String value2) {
            addCriterion("cert_name between", value1, value2, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameNotBetween(String value1, String value2) {
            addCriterion("cert_name not between", value1, value2, "certName");
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

        public Criteria andPublicSituationIsNull() {
            addCriterion("public_situation is null");
            return (Criteria) this;
        }

        public Criteria andPublicSituationIsNotNull() {
            addCriterion("public_situation is not null");
            return (Criteria) this;
        }

        public Criteria andPublicSituationEqualTo(Integer value) {
            addCriterion("public_situation =", value, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationNotEqualTo(Integer value) {
            addCriterion("public_situation <>", value, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationGreaterThan(Integer value) {
            addCriterion("public_situation >", value, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationGreaterThanOrEqualTo(Integer value) {
            addCriterion("public_situation >=", value, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationLessThan(Integer value) {
            addCriterion("public_situation <", value, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationLessThanOrEqualTo(Integer value) {
            addCriterion("public_situation <=", value, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationIn(List<Integer> values) {
            addCriterion("public_situation in", values, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationNotIn(List<Integer> values) {
            addCriterion("public_situation not in", values, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationBetween(Integer value1, Integer value2) {
            addCriterion("public_situation between", value1, value2, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationNotBetween(Integer value1, Integer value2) {
            addCriterion("public_situation not between", value1, value2, "publicSituation");
            return (Criteria) this;
        }

        public Criteria andPublicSituationRemarkIsNull() {
            addCriterion("public_situation_remark is null");
            return (Criteria) this;
        }

        public Criteria andPublicSituationRemarkIsNotNull() {
            addCriterion("public_situation_remark is not null");
            return (Criteria) this;
        }

        public Criteria andPublicSituationRemarkEqualTo(String value) {
            addCriterion("public_situation_remark =", value, "publicSituationRemark");
            return (Criteria) this;
        }

        public Criteria andPublicSituationRemarkNotEqualTo(String value) {
            addCriterion("public_situation_remark <>", value, "publicSituationRemark");
            return (Criteria) this;
        }

        public Criteria andPublicSituationRemarkGreaterThan(String value) {
            addCriterion("public_situation_remark >", value, "publicSituationRemark");
            return (Criteria) this;
        }

        public Criteria andPublicSituationRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("public_situation_remark >=", value, "publicSituationRemark");
            return (Criteria) this;
        }

        public Criteria andPublicSituationRemarkLessThan(String value) {
            addCriterion("public_situation_remark <", value, "publicSituationRemark");
            return (Criteria) this;
        }

        public Criteria andPublicSituationRemarkLessThanOrEqualTo(String value) {
            addCriterion("public_situation_remark <=", value, "publicSituationRemark");
            return (Criteria) this;
        }

        public Criteria andPublicSituationRemarkLike(String value) {
            addCriterion("public_situation_remark like", value, "publicSituationRemark");
            return (Criteria) this;
        }

        public Criteria andPublicSituationRemarkNotLike(String value) {
            addCriterion("public_situation_remark not like", value, "publicSituationRemark");
            return (Criteria) this;
        }

        public Criteria andPublicSituationRemarkIn(List<String> values) {
            addCriterion("public_situation_remark in", values, "publicSituationRemark");
            return (Criteria) this;
        }

        public Criteria andPublicSituationRemarkNotIn(List<String> values) {
            addCriterion("public_situation_remark not in", values, "publicSituationRemark");
            return (Criteria) this;
        }

        public Criteria andPublicSituationRemarkBetween(String value1, String value2) {
            addCriterion("public_situation_remark between", value1, value2, "publicSituationRemark");
            return (Criteria) this;
        }

        public Criteria andPublicSituationRemarkNotBetween(String value1, String value2) {
            addCriterion("public_situation_remark not between", value1, value2, "publicSituationRemark");
            return (Criteria) this;
        }

        public Criteria andBeLocatedIsNull() {
            addCriterion("be_located is null");
            return (Criteria) this;
        }

        public Criteria andBeLocatedIsNotNull() {
            addCriterion("be_located is not null");
            return (Criteria) this;
        }

        public Criteria andBeLocatedEqualTo(String value) {
            addCriterion("be_located =", value, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedNotEqualTo(String value) {
            addCriterion("be_located <>", value, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedGreaterThan(String value) {
            addCriterion("be_located >", value, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedGreaterThanOrEqualTo(String value) {
            addCriterion("be_located >=", value, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedLessThan(String value) {
            addCriterion("be_located <", value, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedLessThanOrEqualTo(String value) {
            addCriterion("be_located <=", value, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedLike(String value) {
            addCriterion("be_located like", value, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedNotLike(String value) {
            addCriterion("be_located not like", value, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedIn(List<String> values) {
            addCriterion("be_located in", values, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedNotIn(List<String> values) {
            addCriterion("be_located not in", values, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedBetween(String value1, String value2) {
            addCriterion("be_located between", value1, value2, "beLocated");
            return (Criteria) this;
        }

        public Criteria andBeLocatedNotBetween(String value1, String value2) {
            addCriterion("be_located not between", value1, value2, "beLocated");
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

        public Criteria andRegistrationTimeIsNull() {
            addCriterion("registration_time is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationTimeIsNotNull() {
            addCriterion("registration_time is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationTimeEqualTo(Date value) {
            addCriterion("registration_time =", value, "registrationTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationTimeNotEqualTo(Date value) {
            addCriterion("registration_time <>", value, "registrationTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationTimeGreaterThan(Date value) {
            addCriterion("registration_time >", value, "registrationTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("registration_time >=", value, "registrationTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationTimeLessThan(Date value) {
            addCriterion("registration_time <", value, "registrationTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationTimeLessThanOrEqualTo(Date value) {
            addCriterion("registration_time <=", value, "registrationTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationTimeIn(List<Date> values) {
            addCriterion("registration_time in", values, "registrationTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationTimeNotIn(List<Date> values) {
            addCriterion("registration_time not in", values, "registrationTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationTimeBetween(Date value1, Date value2) {
            addCriterion("registration_time between", value1, value2, "registrationTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationTimeNotBetween(Date value1, Date value2) {
            addCriterion("registration_time not between", value1, value2, "registrationTime");
            return (Criteria) this;
        }

        public Criteria andNatureIsNull() {
            addCriterion("nature is null");
            return (Criteria) this;
        }

        public Criteria andNatureIsNotNull() {
            addCriterion("nature is not null");
            return (Criteria) this;
        }

        public Criteria andNatureEqualTo(Integer value) {
            addCriterion("nature =", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureNotEqualTo(Integer value) {
            addCriterion("nature <>", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureGreaterThan(Integer value) {
            addCriterion("nature >", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureGreaterThanOrEqualTo(Integer value) {
            addCriterion("nature >=", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureLessThan(Integer value) {
            addCriterion("nature <", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureLessThanOrEqualTo(Integer value) {
            addCriterion("nature <=", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureIn(List<Integer> values) {
            addCriterion("nature in", values, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureNotIn(List<Integer> values) {
            addCriterion("nature not in", values, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureBetween(Integer value1, Integer value2) {
            addCriterion("nature between", value1, value2, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureNotBetween(Integer value1, Integer value2) {
            addCriterion("nature not between", value1, value2, "nature");
            return (Criteria) this;
        }

        public Criteria andFloorCountIsNull() {
            addCriterion("floor_count is null");
            return (Criteria) this;
        }

        public Criteria andFloorCountIsNotNull() {
            addCriterion("floor_count is not null");
            return (Criteria) this;
        }

        public Criteria andFloorCountEqualTo(String value) {
            addCriterion("floor_count =", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountNotEqualTo(String value) {
            addCriterion("floor_count <>", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountGreaterThan(String value) {
            addCriterion("floor_count >", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountGreaterThanOrEqualTo(String value) {
            addCriterion("floor_count >=", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountLessThan(String value) {
            addCriterion("floor_count <", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountLessThanOrEqualTo(String value) {
            addCriterion("floor_count <=", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountLike(String value) {
            addCriterion("floor_count like", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountNotLike(String value) {
            addCriterion("floor_count not like", value, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountIn(List<String> values) {
            addCriterion("floor_count in", values, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountNotIn(List<String> values) {
            addCriterion("floor_count not in", values, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountBetween(String value1, String value2) {
            addCriterion("floor_count between", value1, value2, "floorCount");
            return (Criteria) this;
        }

        public Criteria andFloorCountNotBetween(String value1, String value2) {
            addCriterion("floor_count not between", value1, value2, "floorCount");
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

        public Criteria andCertUseCategoryIsNull() {
            addCriterion("cert_use_category is null");
            return (Criteria) this;
        }

        public Criteria andCertUseCategoryIsNotNull() {
            addCriterion("cert_use_category is not null");
            return (Criteria) this;
        }

        public Criteria andCertUseCategoryEqualTo(String value) {
            addCriterion("cert_use_category =", value, "certUseCategory");
            return (Criteria) this;
        }

        public Criteria andCertUseCategoryNotEqualTo(String value) {
            addCriterion("cert_use_category <>", value, "certUseCategory");
            return (Criteria) this;
        }

        public Criteria andCertUseCategoryGreaterThan(String value) {
            addCriterion("cert_use_category >", value, "certUseCategory");
            return (Criteria) this;
        }

        public Criteria andCertUseCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("cert_use_category >=", value, "certUseCategory");
            return (Criteria) this;
        }

        public Criteria andCertUseCategoryLessThan(String value) {
            addCriterion("cert_use_category <", value, "certUseCategory");
            return (Criteria) this;
        }

        public Criteria andCertUseCategoryLessThanOrEqualTo(String value) {
            addCriterion("cert_use_category <=", value, "certUseCategory");
            return (Criteria) this;
        }

        public Criteria andCertUseCategoryLike(String value) {
            addCriterion("cert_use_category like", value, "certUseCategory");
            return (Criteria) this;
        }

        public Criteria andCertUseCategoryNotLike(String value) {
            addCriterion("cert_use_category not like", value, "certUseCategory");
            return (Criteria) this;
        }

        public Criteria andCertUseCategoryIn(List<String> values) {
            addCriterion("cert_use_category in", values, "certUseCategory");
            return (Criteria) this;
        }

        public Criteria andCertUseCategoryNotIn(List<String> values) {
            addCriterion("cert_use_category not in", values, "certUseCategory");
            return (Criteria) this;
        }

        public Criteria andCertUseCategoryBetween(String value1, String value2) {
            addCriterion("cert_use_category between", value1, value2, "certUseCategory");
            return (Criteria) this;
        }

        public Criteria andCertUseCategoryNotBetween(String value1, String value2) {
            addCriterion("cert_use_category not between", value1, value2, "certUseCategory");
            return (Criteria) this;
        }

        public Criteria andHousingStructureIsNull() {
            addCriterion("housing_structure is null");
            return (Criteria) this;
        }

        public Criteria andHousingStructureIsNotNull() {
            addCriterion("housing_structure is not null");
            return (Criteria) this;
        }

        public Criteria andHousingStructureEqualTo(String value) {
            addCriterion("housing_structure =", value, "housingStructure");
            return (Criteria) this;
        }

        public Criteria andHousingStructureNotEqualTo(String value) {
            addCriterion("housing_structure <>", value, "housingStructure");
            return (Criteria) this;
        }

        public Criteria andHousingStructureGreaterThan(String value) {
            addCriterion("housing_structure >", value, "housingStructure");
            return (Criteria) this;
        }

        public Criteria andHousingStructureGreaterThanOrEqualTo(String value) {
            addCriterion("housing_structure >=", value, "housingStructure");
            return (Criteria) this;
        }

        public Criteria andHousingStructureLessThan(String value) {
            addCriterion("housing_structure <", value, "housingStructure");
            return (Criteria) this;
        }

        public Criteria andHousingStructureLessThanOrEqualTo(String value) {
            addCriterion("housing_structure <=", value, "housingStructure");
            return (Criteria) this;
        }

        public Criteria andHousingStructureLike(String value) {
            addCriterion("housing_structure like", value, "housingStructure");
            return (Criteria) this;
        }

        public Criteria andHousingStructureNotLike(String value) {
            addCriterion("housing_structure not like", value, "housingStructure");
            return (Criteria) this;
        }

        public Criteria andHousingStructureIn(List<String> values) {
            addCriterion("housing_structure in", values, "housingStructure");
            return (Criteria) this;
        }

        public Criteria andHousingStructureNotIn(List<String> values) {
            addCriterion("housing_structure not in", values, "housingStructure");
            return (Criteria) this;
        }

        public Criteria andHousingStructureBetween(String value1, String value2) {
            addCriterion("housing_structure between", value1, value2, "housingStructure");
            return (Criteria) this;
        }

        public Criteria andHousingStructureNotBetween(String value1, String value2) {
            addCriterion("housing_structure not between", value1, value2, "housingStructure");
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

        public Criteria andFloorAreaEqualTo(String value) {
            addCriterion("floor_area =", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotEqualTo(String value) {
            addCriterion("floor_area <>", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaGreaterThan(String value) {
            addCriterion("floor_area >", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaGreaterThanOrEqualTo(String value) {
            addCriterion("floor_area >=", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaLessThan(String value) {
            addCriterion("floor_area <", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaLessThanOrEqualTo(String value) {
            addCriterion("floor_area <=", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaLike(String value) {
            addCriterion("floor_area like", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotLike(String value) {
            addCriterion("floor_area not like", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaIn(List<String> values) {
            addCriterion("floor_area in", values, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotIn(List<String> values) {
            addCriterion("floor_area not in", values, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaBetween(String value1, String value2) {
            addCriterion("floor_area between", value1, value2, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotBetween(String value1, String value2) {
            addCriterion("floor_area not between", value1, value2, "floorArea");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andInnerAreaIsNull() {
            addCriterion("inner_area is null");
            return (Criteria) this;
        }

        public Criteria andInnerAreaIsNotNull() {
            addCriterion("inner_area is not null");
            return (Criteria) this;
        }

        public Criteria andInnerAreaEqualTo(BigDecimal value) {
            addCriterion("inner_area =", value, "innerArea");
            return (Criteria) this;
        }

        public Criteria andInnerAreaNotEqualTo(BigDecimal value) {
            addCriterion("inner_area <>", value, "innerArea");
            return (Criteria) this;
        }

        public Criteria andInnerAreaGreaterThan(BigDecimal value) {
            addCriterion("inner_area >", value, "innerArea");
            return (Criteria) this;
        }

        public Criteria andInnerAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("inner_area >=", value, "innerArea");
            return (Criteria) this;
        }

        public Criteria andInnerAreaLessThan(BigDecimal value) {
            addCriterion("inner_area <", value, "innerArea");
            return (Criteria) this;
        }

        public Criteria andInnerAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("inner_area <=", value, "innerArea");
            return (Criteria) this;
        }

        public Criteria andInnerAreaIn(List<BigDecimal> values) {
            addCriterion("inner_area in", values, "innerArea");
            return (Criteria) this;
        }

        public Criteria andInnerAreaNotIn(List<BigDecimal> values) {
            addCriterion("inner_area not in", values, "innerArea");
            return (Criteria) this;
        }

        public Criteria andInnerAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("inner_area between", value1, value2, "innerArea");
            return (Criteria) this;
        }

        public Criteria andInnerAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("inner_area not between", value1, value2, "innerArea");
            return (Criteria) this;
        }

        public Criteria andEvidenceAreaIsNull() {
            addCriterion("evidence_area is null");
            return (Criteria) this;
        }

        public Criteria andEvidenceAreaIsNotNull() {
            addCriterion("evidence_area is not null");
            return (Criteria) this;
        }

        public Criteria andEvidenceAreaEqualTo(BigDecimal value) {
            addCriterion("evidence_area =", value, "evidenceArea");
            return (Criteria) this;
        }

        public Criteria andEvidenceAreaNotEqualTo(BigDecimal value) {
            addCriterion("evidence_area <>", value, "evidenceArea");
            return (Criteria) this;
        }

        public Criteria andEvidenceAreaGreaterThan(BigDecimal value) {
            addCriterion("evidence_area >", value, "evidenceArea");
            return (Criteria) this;
        }

        public Criteria andEvidenceAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("evidence_area >=", value, "evidenceArea");
            return (Criteria) this;
        }

        public Criteria andEvidenceAreaLessThan(BigDecimal value) {
            addCriterion("evidence_area <", value, "evidenceArea");
            return (Criteria) this;
        }

        public Criteria andEvidenceAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("evidence_area <=", value, "evidenceArea");
            return (Criteria) this;
        }

        public Criteria andEvidenceAreaIn(List<BigDecimal> values) {
            addCriterion("evidence_area in", values, "evidenceArea");
            return (Criteria) this;
        }

        public Criteria andEvidenceAreaNotIn(List<BigDecimal> values) {
            addCriterion("evidence_area not in", values, "evidenceArea");
            return (Criteria) this;
        }

        public Criteria andEvidenceAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("evidence_area between", value1, value2, "evidenceArea");
            return (Criteria) this;
        }

        public Criteria andEvidenceAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("evidence_area not between", value1, value2, "evidenceArea");
            return (Criteria) this;
        }

        public Criteria andOtherIsNull() {
            addCriterion("other is null");
            return (Criteria) this;
        }

        public Criteria andOtherIsNotNull() {
            addCriterion("other is not null");
            return (Criteria) this;
        }

        public Criteria andOtherEqualTo(String value) {
            addCriterion("other =", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotEqualTo(String value) {
            addCriterion("other <>", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherGreaterThan(String value) {
            addCriterion("other >", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherGreaterThanOrEqualTo(String value) {
            addCriterion("other >=", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLessThan(String value) {
            addCriterion("other <", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLessThanOrEqualTo(String value) {
            addCriterion("other <=", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLike(String value) {
            addCriterion("other like", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotLike(String value) {
            addCriterion("other not like", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherIn(List<String> values) {
            addCriterion("other in", values, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotIn(List<String> values) {
            addCriterion("other not in", values, "other");
            return (Criteria) this;
        }

        public Criteria andOtherBetween(String value1, String value2) {
            addCriterion("other between", value1, value2, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotBetween(String value1, String value2) {
            addCriterion("other not between", value1, value2, "other");
            return (Criteria) this;
        }

        public Criteria andLandNumberIsNull() {
            addCriterion("land_number is null");
            return (Criteria) this;
        }

        public Criteria andLandNumberIsNotNull() {
            addCriterion("land_number is not null");
            return (Criteria) this;
        }

        public Criteria andLandNumberEqualTo(String value) {
            addCriterion("land_number =", value, "landNumber");
            return (Criteria) this;
        }

        public Criteria andLandNumberNotEqualTo(String value) {
            addCriterion("land_number <>", value, "landNumber");
            return (Criteria) this;
        }

        public Criteria andLandNumberGreaterThan(String value) {
            addCriterion("land_number >", value, "landNumber");
            return (Criteria) this;
        }

        public Criteria andLandNumberGreaterThanOrEqualTo(String value) {
            addCriterion("land_number >=", value, "landNumber");
            return (Criteria) this;
        }

        public Criteria andLandNumberLessThan(String value) {
            addCriterion("land_number <", value, "landNumber");
            return (Criteria) this;
        }

        public Criteria andLandNumberLessThanOrEqualTo(String value) {
            addCriterion("land_number <=", value, "landNumber");
            return (Criteria) this;
        }

        public Criteria andLandNumberLike(String value) {
            addCriterion("land_number like", value, "landNumber");
            return (Criteria) this;
        }

        public Criteria andLandNumberNotLike(String value) {
            addCriterion("land_number not like", value, "landNumber");
            return (Criteria) this;
        }

        public Criteria andLandNumberIn(List<String> values) {
            addCriterion("land_number in", values, "landNumber");
            return (Criteria) this;
        }

        public Criteria andLandNumberNotIn(List<String> values) {
            addCriterion("land_number not in", values, "landNumber");
            return (Criteria) this;
        }

        public Criteria andLandNumberBetween(String value1, String value2) {
            addCriterion("land_number between", value1, value2, "landNumber");
            return (Criteria) this;
        }

        public Criteria andLandNumberNotBetween(String value1, String value2) {
            addCriterion("land_number not between", value1, value2, "landNumber");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionIsNull() {
            addCriterion("land_acquisition is null");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionIsNotNull() {
            addCriterion("land_acquisition is not null");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionEqualTo(String value) {
            addCriterion("land_acquisition =", value, "landAcquisition");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionNotEqualTo(String value) {
            addCriterion("land_acquisition <>", value, "landAcquisition");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionGreaterThan(String value) {
            addCriterion("land_acquisition >", value, "landAcquisition");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionGreaterThanOrEqualTo(String value) {
            addCriterion("land_acquisition >=", value, "landAcquisition");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionLessThan(String value) {
            addCriterion("land_acquisition <", value, "landAcquisition");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionLessThanOrEqualTo(String value) {
            addCriterion("land_acquisition <=", value, "landAcquisition");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionLike(String value) {
            addCriterion("land_acquisition like", value, "landAcquisition");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionNotLike(String value) {
            addCriterion("land_acquisition not like", value, "landAcquisition");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionIn(List<String> values) {
            addCriterion("land_acquisition in", values, "landAcquisition");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionNotIn(List<String> values) {
            addCriterion("land_acquisition not in", values, "landAcquisition");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionBetween(String value1, String value2) {
            addCriterion("land_acquisition between", value1, value2, "landAcquisition");
            return (Criteria) this;
        }

        public Criteria andLandAcquisitionNotBetween(String value1, String value2) {
            addCriterion("land_acquisition not between", value1, value2, "landAcquisition");
            return (Criteria) this;
        }

        public Criteria andUseStartDateIsNull() {
            addCriterion("use_start_date is null");
            return (Criteria) this;
        }

        public Criteria andUseStartDateIsNotNull() {
            addCriterion("use_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andUseStartDateEqualTo(Date value) {
            addCriterion("use_start_date =", value, "useStartDate");
            return (Criteria) this;
        }

        public Criteria andUseStartDateNotEqualTo(Date value) {
            addCriterion("use_start_date <>", value, "useStartDate");
            return (Criteria) this;
        }

        public Criteria andUseStartDateGreaterThan(Date value) {
            addCriterion("use_start_date >", value, "useStartDate");
            return (Criteria) this;
        }

        public Criteria andUseStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("use_start_date >=", value, "useStartDate");
            return (Criteria) this;
        }

        public Criteria andUseStartDateLessThan(Date value) {
            addCriterion("use_start_date <", value, "useStartDate");
            return (Criteria) this;
        }

        public Criteria andUseStartDateLessThanOrEqualTo(Date value) {
            addCriterion("use_start_date <=", value, "useStartDate");
            return (Criteria) this;
        }

        public Criteria andUseStartDateIn(List<Date> values) {
            addCriterion("use_start_date in", values, "useStartDate");
            return (Criteria) this;
        }

        public Criteria andUseStartDateNotIn(List<Date> values) {
            addCriterion("use_start_date not in", values, "useStartDate");
            return (Criteria) this;
        }

        public Criteria andUseStartDateBetween(Date value1, Date value2) {
            addCriterion("use_start_date between", value1, value2, "useStartDate");
            return (Criteria) this;
        }

        public Criteria andUseStartDateNotBetween(Date value1, Date value2) {
            addCriterion("use_start_date not between", value1, value2, "useStartDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateIsNull() {
            addCriterion("use_end_date is null");
            return (Criteria) this;
        }

        public Criteria andUseEndDateIsNotNull() {
            addCriterion("use_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andUseEndDateEqualTo(Date value) {
            addCriterion("use_end_date =", value, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateNotEqualTo(Date value) {
            addCriterion("use_end_date <>", value, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateGreaterThan(Date value) {
            addCriterion("use_end_date >", value, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("use_end_date >=", value, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateLessThan(Date value) {
            addCriterion("use_end_date <", value, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateLessThanOrEqualTo(Date value) {
            addCriterion("use_end_date <=", value, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateIn(List<Date> values) {
            addCriterion("use_end_date in", values, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateNotIn(List<Date> values) {
            addCriterion("use_end_date not in", values, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateBetween(Date value1, Date value2) {
            addCriterion("use_end_date between", value1, value2, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateNotBetween(Date value1, Date value2) {
            addCriterion("use_end_date not between", value1, value2, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andPublicAreaIsNull() {
            addCriterion("public_area is null");
            return (Criteria) this;
        }

        public Criteria andPublicAreaIsNotNull() {
            addCriterion("public_area is not null");
            return (Criteria) this;
        }

        public Criteria andPublicAreaEqualTo(BigDecimal value) {
            addCriterion("public_area =", value, "publicArea");
            return (Criteria) this;
        }

        public Criteria andPublicAreaNotEqualTo(BigDecimal value) {
            addCriterion("public_area <>", value, "publicArea");
            return (Criteria) this;
        }

        public Criteria andPublicAreaGreaterThan(BigDecimal value) {
            addCriterion("public_area >", value, "publicArea");
            return (Criteria) this;
        }

        public Criteria andPublicAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("public_area >=", value, "publicArea");
            return (Criteria) this;
        }

        public Criteria andPublicAreaLessThan(BigDecimal value) {
            addCriterion("public_area <", value, "publicArea");
            return (Criteria) this;
        }

        public Criteria andPublicAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("public_area <=", value, "publicArea");
            return (Criteria) this;
        }

        public Criteria andPublicAreaIn(List<BigDecimal> values) {
            addCriterion("public_area in", values, "publicArea");
            return (Criteria) this;
        }

        public Criteria andPublicAreaNotIn(List<BigDecimal> values) {
            addCriterion("public_area not in", values, "publicArea");
            return (Criteria) this;
        }

        public Criteria andPublicAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("public_area between", value1, value2, "publicArea");
            return (Criteria) this;
        }

        public Criteria andPublicAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("public_area not between", value1, value2, "publicArea");
            return (Criteria) this;
        }

        public Criteria andOtherNoteIsNull() {
            addCriterion("other_note is null");
            return (Criteria) this;
        }

        public Criteria andOtherNoteIsNotNull() {
            addCriterion("other_note is not null");
            return (Criteria) this;
        }

        public Criteria andOtherNoteEqualTo(String value) {
            addCriterion("other_note =", value, "otherNote");
            return (Criteria) this;
        }

        public Criteria andOtherNoteNotEqualTo(String value) {
            addCriterion("other_note <>", value, "otherNote");
            return (Criteria) this;
        }

        public Criteria andOtherNoteGreaterThan(String value) {
            addCriterion("other_note >", value, "otherNote");
            return (Criteria) this;
        }

        public Criteria andOtherNoteGreaterThanOrEqualTo(String value) {
            addCriterion("other_note >=", value, "otherNote");
            return (Criteria) this;
        }

        public Criteria andOtherNoteLessThan(String value) {
            addCriterion("other_note <", value, "otherNote");
            return (Criteria) this;
        }

        public Criteria andOtherNoteLessThanOrEqualTo(String value) {
            addCriterion("other_note <=", value, "otherNote");
            return (Criteria) this;
        }

        public Criteria andOtherNoteLike(String value) {
            addCriterion("other_note like", value, "otherNote");
            return (Criteria) this;
        }

        public Criteria andOtherNoteNotLike(String value) {
            addCriterion("other_note not like", value, "otherNote");
            return (Criteria) this;
        }

        public Criteria andOtherNoteIn(List<String> values) {
            addCriterion("other_note in", values, "otherNote");
            return (Criteria) this;
        }

        public Criteria andOtherNoteNotIn(List<String> values) {
            addCriterion("other_note not in", values, "otherNote");
            return (Criteria) this;
        }

        public Criteria andOtherNoteBetween(String value1, String value2) {
            addCriterion("other_note between", value1, value2, "otherNote");
            return (Criteria) this;
        }

        public Criteria andOtherNoteNotBetween(String value1, String value2) {
            addCriterion("other_note not between", value1, value2, "otherNote");
            return (Criteria) this;
        }

        public Criteria andRegistrationAuthorityIsNull() {
            addCriterion("registration_authority is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationAuthorityIsNotNull() {
            addCriterion("registration_authority is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationAuthorityEqualTo(String value) {
            addCriterion("registration_authority =", value, "registrationAuthority");
            return (Criteria) this;
        }

        public Criteria andRegistrationAuthorityNotEqualTo(String value) {
            addCriterion("registration_authority <>", value, "registrationAuthority");
            return (Criteria) this;
        }

        public Criteria andRegistrationAuthorityGreaterThan(String value) {
            addCriterion("registration_authority >", value, "registrationAuthority");
            return (Criteria) this;
        }

        public Criteria andRegistrationAuthorityGreaterThanOrEqualTo(String value) {
            addCriterion("registration_authority >=", value, "registrationAuthority");
            return (Criteria) this;
        }

        public Criteria andRegistrationAuthorityLessThan(String value) {
            addCriterion("registration_authority <", value, "registrationAuthority");
            return (Criteria) this;
        }

        public Criteria andRegistrationAuthorityLessThanOrEqualTo(String value) {
            addCriterion("registration_authority <=", value, "registrationAuthority");
            return (Criteria) this;
        }

        public Criteria andRegistrationAuthorityLike(String value) {
            addCriterion("registration_authority like", value, "registrationAuthority");
            return (Criteria) this;
        }

        public Criteria andRegistrationAuthorityNotLike(String value) {
            addCriterion("registration_authority not like", value, "registrationAuthority");
            return (Criteria) this;
        }

        public Criteria andRegistrationAuthorityIn(List<String> values) {
            addCriterion("registration_authority in", values, "registrationAuthority");
            return (Criteria) this;
        }

        public Criteria andRegistrationAuthorityNotIn(List<String> values) {
            addCriterion("registration_authority not in", values, "registrationAuthority");
            return (Criteria) this;
        }

        public Criteria andRegistrationAuthorityBetween(String value1, String value2) {
            addCriterion("registration_authority between", value1, value2, "registrationAuthority");
            return (Criteria) this;
        }

        public Criteria andRegistrationAuthorityNotBetween(String value1, String value2) {
            addCriterion("registration_authority not between", value1, value2, "registrationAuthority");
            return (Criteria) this;
        }

        public Criteria andLandRegistrationDateIsNull() {
            addCriterion("land_registration_date is null");
            return (Criteria) this;
        }

        public Criteria andLandRegistrationDateIsNotNull() {
            addCriterion("land_registration_date is not null");
            return (Criteria) this;
        }

        public Criteria andLandRegistrationDateEqualTo(Date value) {
            addCriterion("land_registration_date =", value, "landRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andLandRegistrationDateNotEqualTo(Date value) {
            addCriterion("land_registration_date <>", value, "landRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andLandRegistrationDateGreaterThan(Date value) {
            addCriterion("land_registration_date >", value, "landRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andLandRegistrationDateGreaterThanOrEqualTo(Date value) {
            addCriterion("land_registration_date >=", value, "landRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andLandRegistrationDateLessThan(Date value) {
            addCriterion("land_registration_date <", value, "landRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andLandRegistrationDateLessThanOrEqualTo(Date value) {
            addCriterion("land_registration_date <=", value, "landRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andLandRegistrationDateIn(List<Date> values) {
            addCriterion("land_registration_date in", values, "landRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andLandRegistrationDateNotIn(List<Date> values) {
            addCriterion("land_registration_date not in", values, "landRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andLandRegistrationDateBetween(Date value1, Date value2) {
            addCriterion("land_registration_date between", value1, value2, "landRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andLandRegistrationDateNotBetween(Date value1, Date value2) {
            addCriterion("land_registration_date not between", value1, value2, "landRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateIsNull() {
            addCriterion("registration_date is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateIsNotNull() {
            addCriterion("registration_date is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateEqualTo(Date value) {
            addCriterion("registration_date =", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateNotEqualTo(Date value) {
            addCriterion("registration_date <>", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateGreaterThan(Date value) {
            addCriterion("registration_date >", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateGreaterThanOrEqualTo(Date value) {
            addCriterion("registration_date >=", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateLessThan(Date value) {
            addCriterion("registration_date <", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateLessThanOrEqualTo(Date value) {
            addCriterion("registration_date <=", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateIn(List<Date> values) {
            addCriterion("registration_date in", values, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateNotIn(List<Date> values) {
            addCriterion("registration_date not in", values, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateBetween(Date value1, Date value2) {
            addCriterion("registration_date between", value1, value2, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateNotBetween(Date value1, Date value2) {
            addCriterion("registration_date not between", value1, value2, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIsNull() {
            addCriterion("declare_type is null");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIsNotNull() {
            addCriterion("declare_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeEqualTo(String value) {
            addCriterion("declare_type =", value, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNotEqualTo(String value) {
            addCriterion("declare_type <>", value, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeGreaterThan(String value) {
            addCriterion("declare_type >", value, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeGreaterThanOrEqualTo(String value) {
            addCriterion("declare_type >=", value, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeLessThan(String value) {
            addCriterion("declare_type <", value, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeLessThanOrEqualTo(String value) {
            addCriterion("declare_type <=", value, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeLike(String value) {
            addCriterion("declare_type like", value, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNotLike(String value) {
            addCriterion("declare_type not like", value, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeIn(List<String> values) {
            addCriterion("declare_type in", values, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNotIn(List<String> values) {
            addCriterion("declare_type not in", values, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeBetween(String value1, String value2) {
            addCriterion("declare_type between", value1, value2, "declareType");
            return (Criteria) this;
        }

        public Criteria andDeclareTypeNotBetween(String value1, String value2) {
            addCriterion("declare_type not between", value1, value2, "declareType");
            return (Criteria) this;
        }

        public Criteria andGroundNumIsNull() {
            addCriterion("ground_num is null");
            return (Criteria) this;
        }

        public Criteria andGroundNumIsNotNull() {
            addCriterion("ground_num is not null");
            return (Criteria) this;
        }

        public Criteria andGroundNumEqualTo(String value) {
            addCriterion("ground_num =", value, "groundNum");
            return (Criteria) this;
        }

        public Criteria andGroundNumNotEqualTo(String value) {
            addCriterion("ground_num <>", value, "groundNum");
            return (Criteria) this;
        }

        public Criteria andGroundNumGreaterThan(String value) {
            addCriterion("ground_num >", value, "groundNum");
            return (Criteria) this;
        }

        public Criteria andGroundNumGreaterThanOrEqualTo(String value) {
            addCriterion("ground_num >=", value, "groundNum");
            return (Criteria) this;
        }

        public Criteria andGroundNumLessThan(String value) {
            addCriterion("ground_num <", value, "groundNum");
            return (Criteria) this;
        }

        public Criteria andGroundNumLessThanOrEqualTo(String value) {
            addCriterion("ground_num <=", value, "groundNum");
            return (Criteria) this;
        }

        public Criteria andGroundNumLike(String value) {
            addCriterion("ground_num like", value, "groundNum");
            return (Criteria) this;
        }

        public Criteria andGroundNumNotLike(String value) {
            addCriterion("ground_num not like", value, "groundNum");
            return (Criteria) this;
        }

        public Criteria andGroundNumIn(List<String> values) {
            addCriterion("ground_num in", values, "groundNum");
            return (Criteria) this;
        }

        public Criteria andGroundNumNotIn(List<String> values) {
            addCriterion("ground_num not in", values, "groundNum");
            return (Criteria) this;
        }

        public Criteria andGroundNumBetween(String value1, String value2) {
            addCriterion("ground_num between", value1, value2, "groundNum");
            return (Criteria) this;
        }

        public Criteria andGroundNumNotBetween(String value1, String value2) {
            addCriterion("ground_num not between", value1, value2, "groundNum");
            return (Criteria) this;
        }

        public Criteria andApportionmentAreaIsNull() {
            addCriterion("apportionment_area is null");
            return (Criteria) this;
        }

        public Criteria andApportionmentAreaIsNotNull() {
            addCriterion("apportionment_area is not null");
            return (Criteria) this;
        }

        public Criteria andApportionmentAreaEqualTo(BigDecimal value) {
            addCriterion("apportionment_area =", value, "apportionmentArea");
            return (Criteria) this;
        }

        public Criteria andApportionmentAreaNotEqualTo(BigDecimal value) {
            addCriterion("apportionment_area <>", value, "apportionmentArea");
            return (Criteria) this;
        }

        public Criteria andApportionmentAreaGreaterThan(BigDecimal value) {
            addCriterion("apportionment_area >", value, "apportionmentArea");
            return (Criteria) this;
        }

        public Criteria andApportionmentAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("apportionment_area >=", value, "apportionmentArea");
            return (Criteria) this;
        }

        public Criteria andApportionmentAreaLessThan(BigDecimal value) {
            addCriterion("apportionment_area <", value, "apportionmentArea");
            return (Criteria) this;
        }

        public Criteria andApportionmentAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("apportionment_area <=", value, "apportionmentArea");
            return (Criteria) this;
        }

        public Criteria andApportionmentAreaIn(List<BigDecimal> values) {
            addCriterion("apportionment_area in", values, "apportionmentArea");
            return (Criteria) this;
        }

        public Criteria andApportionmentAreaNotIn(List<BigDecimal> values) {
            addCriterion("apportionment_area not in", values, "apportionmentArea");
            return (Criteria) this;
        }

        public Criteria andApportionmentAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("apportionment_area between", value1, value2, "apportionmentArea");
            return (Criteria) this;
        }

        public Criteria andApportionmentAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("apportionment_area not between", value1, value2, "apportionmentArea");
            return (Criteria) this;
        }

        public Criteria andEnableIsNull() {
            addCriterion("enable is null");
            return (Criteria) this;
        }

        public Criteria andEnableIsNotNull() {
            addCriterion("enable is not null");
            return (Criteria) this;
        }

        public Criteria andEnableEqualTo(String value) {
            addCriterion("enable =", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotEqualTo(String value) {
            addCriterion("enable <>", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThan(String value) {
            addCriterion("enable >", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThanOrEqualTo(String value) {
            addCriterion("enable >=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThan(String value) {
            addCriterion("enable <", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThanOrEqualTo(String value) {
            addCriterion("enable <=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLike(String value) {
            addCriterion("enable like", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotLike(String value) {
            addCriterion("enable not like", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableIn(List<String> values) {
            addCriterion("enable in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotIn(List<String> values) {
            addCriterion("enable not in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableBetween(String value1, String value2) {
            addCriterion("enable between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotBetween(String value1, String value2) {
            addCriterion("enable not between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andBisRecordIsNull() {
            addCriterion("bis_record is null");
            return (Criteria) this;
        }

        public Criteria andBisRecordIsNotNull() {
            addCriterion("bis_record is not null");
            return (Criteria) this;
        }

        public Criteria andBisRecordEqualTo(Boolean value) {
            addCriterion("bis_record =", value, "bisRecord");
            return (Criteria) this;
        }

        public Criteria andBisRecordNotEqualTo(Boolean value) {
            addCriterion("bis_record <>", value, "bisRecord");
            return (Criteria) this;
        }

        public Criteria andBisRecordGreaterThan(Boolean value) {
            addCriterion("bis_record >", value, "bisRecord");
            return (Criteria) this;
        }

        public Criteria andBisRecordGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_record >=", value, "bisRecord");
            return (Criteria) this;
        }

        public Criteria andBisRecordLessThan(Boolean value) {
            addCriterion("bis_record <", value, "bisRecord");
            return (Criteria) this;
        }

        public Criteria andBisRecordLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_record <=", value, "bisRecord");
            return (Criteria) this;
        }

        public Criteria andBisRecordIn(List<Boolean> values) {
            addCriterion("bis_record in", values, "bisRecord");
            return (Criteria) this;
        }

        public Criteria andBisRecordNotIn(List<Boolean> values) {
            addCriterion("bis_record not in", values, "bisRecord");
            return (Criteria) this;
        }

        public Criteria andBisRecordBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_record between", value1, value2, "bisRecord");
            return (Criteria) this;
        }

        public Criteria andBisRecordNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_record not between", value1, value2, "bisRecord");
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
     * tb_declare_realty_house_cert
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