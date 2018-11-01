package com.copower.pmcc.assess.dal.basic.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andUnitIdIsNull() {
            addCriterion("unit_id is null");
            return (Criteria) this;
        }

        public Criteria andUnitIdIsNotNull() {
            addCriterion("unit_id is not null");
            return (Criteria) this;
        }

        public Criteria andUnitIdEqualTo(Integer value) {
            addCriterion("unit_id =", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotEqualTo(Integer value) {
            addCriterion("unit_id <>", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdGreaterThan(Integer value) {
            addCriterion("unit_id >", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("unit_id >=", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdLessThan(Integer value) {
            addCriterion("unit_id <", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdLessThanOrEqualTo(Integer value) {
            addCriterion("unit_id <=", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdIn(List<Integer> values) {
            addCriterion("unit_id in", values, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotIn(List<Integer> values) {
            addCriterion("unit_id not in", values, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdBetween(Integer value1, Integer value2) {
            addCriterion("unit_id between", value1, value2, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotBetween(Integer value1, Integer value2) {
            addCriterion("unit_id not between", value1, value2, "unitId");
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

        public Criteria andFloorEqualTo(Integer value) {
            addCriterion("floor =", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotEqualTo(Integer value) {
            addCriterion("floor <>", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorGreaterThan(Integer value) {
            addCriterion("floor >", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorGreaterThanOrEqualTo(Integer value) {
            addCriterion("floor >=", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLessThan(Integer value) {
            addCriterion("floor <", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLessThanOrEqualTo(Integer value) {
            addCriterion("floor <=", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorIn(List<Integer> values) {
            addCriterion("floor in", values, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotIn(List<Integer> values) {
            addCriterion("floor not in", values, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorBetween(Integer value1, Integer value2) {
            addCriterion("floor between", value1, value2, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotBetween(Integer value1, Integer value2) {
            addCriterion("floor not between", value1, value2, "floor");
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

        public Criteria andNewsHuxingIsNull() {
            addCriterion("news_huxing is null");
            return (Criteria) this;
        }

        public Criteria andNewsHuxingIsNotNull() {
            addCriterion("news_huxing is not null");
            return (Criteria) this;
        }

        public Criteria andNewsHuxingEqualTo(Integer value) {
            addCriterion("news_huxing =", value, "newsHuxing");
            return (Criteria) this;
        }

        public Criteria andNewsHuxingNotEqualTo(Integer value) {
            addCriterion("news_huxing <>", value, "newsHuxing");
            return (Criteria) this;
        }

        public Criteria andNewsHuxingGreaterThan(Integer value) {
            addCriterion("news_huxing >", value, "newsHuxing");
            return (Criteria) this;
        }

        public Criteria andNewsHuxingGreaterThanOrEqualTo(Integer value) {
            addCriterion("news_huxing >=", value, "newsHuxing");
            return (Criteria) this;
        }

        public Criteria andNewsHuxingLessThan(Integer value) {
            addCriterion("news_huxing <", value, "newsHuxing");
            return (Criteria) this;
        }

        public Criteria andNewsHuxingLessThanOrEqualTo(Integer value) {
            addCriterion("news_huxing <=", value, "newsHuxing");
            return (Criteria) this;
        }

        public Criteria andNewsHuxingIn(List<Integer> values) {
            addCriterion("news_huxing in", values, "newsHuxing");
            return (Criteria) this;
        }

        public Criteria andNewsHuxingNotIn(List<Integer> values) {
            addCriterion("news_huxing not in", values, "newsHuxing");
            return (Criteria) this;
        }

        public Criteria andNewsHuxingBetween(Integer value1, Integer value2) {
            addCriterion("news_huxing between", value1, value2, "newsHuxing");
            return (Criteria) this;
        }

        public Criteria andNewsHuxingNotBetween(Integer value1, Integer value2) {
            addCriterion("news_huxing not between", value1, value2, "newsHuxing");
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

        public Criteria andCertUseEqualTo(Integer value) {
            addCriterion("cert_use =", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotEqualTo(Integer value) {
            addCriterion("cert_use <>", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseGreaterThan(Integer value) {
            addCriterion("cert_use >", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("cert_use >=", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseLessThan(Integer value) {
            addCriterion("cert_use <", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseLessThanOrEqualTo(Integer value) {
            addCriterion("cert_use <=", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseIn(List<Integer> values) {
            addCriterion("cert_use in", values, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotIn(List<Integer> values) {
            addCriterion("cert_use not in", values, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseBetween(Integer value1, Integer value2) {
            addCriterion("cert_use between", value1, value2, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotBetween(Integer value1, Integer value2) {
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

        public Criteria andPracticalUseEqualTo(Integer value) {
            addCriterion("practical_use =", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseNotEqualTo(Integer value) {
            addCriterion("practical_use <>", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseGreaterThan(Integer value) {
            addCriterion("practical_use >", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("practical_use >=", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseLessThan(Integer value) {
            addCriterion("practical_use <", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseLessThanOrEqualTo(Integer value) {
            addCriterion("practical_use <=", value, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseIn(List<Integer> values) {
            addCriterion("practical_use in", values, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseNotIn(List<Integer> values) {
            addCriterion("practical_use not in", values, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseBetween(Integer value1, Integer value2) {
            addCriterion("practical_use between", value1, value2, "practicalUse");
            return (Criteria) this;
        }

        public Criteria andPracticalUseNotBetween(Integer value1, Integer value2) {
            addCriterion("practical_use not between", value1, value2, "practicalUse");
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

        public Criteria andOrientationIsNull() {
            addCriterion("orientation is null");
            return (Criteria) this;
        }

        public Criteria andOrientationIsNotNull() {
            addCriterion("orientation is not null");
            return (Criteria) this;
        }

        public Criteria andOrientationEqualTo(String value) {
            addCriterion("orientation =", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationNotEqualTo(String value) {
            addCriterion("orientation <>", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationGreaterThan(String value) {
            addCriterion("orientation >", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationGreaterThanOrEqualTo(String value) {
            addCriterion("orientation >=", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationLessThan(String value) {
            addCriterion("orientation <", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationLessThanOrEqualTo(String value) {
            addCriterion("orientation <=", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationLike(String value) {
            addCriterion("orientation like", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationNotLike(String value) {
            addCriterion("orientation not like", value, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationIn(List<String> values) {
            addCriterion("orientation in", values, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationNotIn(List<String> values) {
            addCriterion("orientation not in", values, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationBetween(String value1, String value2) {
            addCriterion("orientation between", value1, value2, "orientation");
            return (Criteria) this;
        }

        public Criteria andOrientationNotBetween(String value1, String value2) {
            addCriterion("orientation not between", value1, value2, "orientation");
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
            addCriterionForJDBCDate("case_date =", value, "caseDate");
            return (Criteria) this;
        }

        public Criteria andCaseDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("case_date <>", value, "caseDate");
            return (Criteria) this;
        }

        public Criteria andCaseDateGreaterThan(Date value) {
            addCriterionForJDBCDate("case_date >", value, "caseDate");
            return (Criteria) this;
        }

        public Criteria andCaseDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("case_date >=", value, "caseDate");
            return (Criteria) this;
        }

        public Criteria andCaseDateLessThan(Date value) {
            addCriterionForJDBCDate("case_date <", value, "caseDate");
            return (Criteria) this;
        }

        public Criteria andCaseDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("case_date <=", value, "caseDate");
            return (Criteria) this;
        }

        public Criteria andCaseDateIn(List<Date> values) {
            addCriterionForJDBCDate("case_date in", values, "caseDate");
            return (Criteria) this;
        }

        public Criteria andCaseDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("case_date not in", values, "caseDate");
            return (Criteria) this;
        }

        public Criteria andCaseDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("case_date between", value1, value2, "caseDate");
            return (Criteria) this;
        }

        public Criteria andCaseDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("case_date not between", value1, value2, "caseDate");
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