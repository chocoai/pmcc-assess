package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.List;

public class CsrBorrowerEnteringExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CsrBorrowerEnteringExample() {
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

        public Criteria andBorrowerIdIsNull() {
            addCriterion("borrower_id is null");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdIsNotNull() {
            addCriterion("borrower_id is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdEqualTo(String value) {
            addCriterion("borrower_id =", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdNotEqualTo(String value) {
            addCriterion("borrower_id <>", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdGreaterThan(String value) {
            addCriterion("borrower_id >", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdGreaterThanOrEqualTo(String value) {
            addCriterion("borrower_id >=", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdLessThan(String value) {
            addCriterion("borrower_id <", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdLessThanOrEqualTo(String value) {
            addCriterion("borrower_id <=", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdLike(String value) {
            addCriterion("borrower_id like", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdNotLike(String value) {
            addCriterion("borrower_id not like", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdIn(List<String> values) {
            addCriterion("borrower_id in", values, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdNotIn(List<String> values) {
            addCriterion("borrower_id not in", values, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdBetween(String value1, String value2) {
            addCriterion("borrower_id between", value1, value2, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdNotBetween(String value1, String value2) {
            addCriterion("borrower_id not between", value1, value2, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelBranchIsNull() {
            addCriterion("first_level_branch is null");
            return (Criteria) this;
        }

        public Criteria andFirstLevelBranchIsNotNull() {
            addCriterion("first_level_branch is not null");
            return (Criteria) this;
        }

        public Criteria andFirstLevelBranchEqualTo(String value) {
            addCriterion("first_level_branch =", value, "firstLevelBranch");
            return (Criteria) this;
        }

        public Criteria andFirstLevelBranchNotEqualTo(String value) {
            addCriterion("first_level_branch <>", value, "firstLevelBranch");
            return (Criteria) this;
        }

        public Criteria andFirstLevelBranchGreaterThan(String value) {
            addCriterion("first_level_branch >", value, "firstLevelBranch");
            return (Criteria) this;
        }

        public Criteria andFirstLevelBranchGreaterThanOrEqualTo(String value) {
            addCriterion("first_level_branch >=", value, "firstLevelBranch");
            return (Criteria) this;
        }

        public Criteria andFirstLevelBranchLessThan(String value) {
            addCriterion("first_level_branch <", value, "firstLevelBranch");
            return (Criteria) this;
        }

        public Criteria andFirstLevelBranchLessThanOrEqualTo(String value) {
            addCriterion("first_level_branch <=", value, "firstLevelBranch");
            return (Criteria) this;
        }

        public Criteria andFirstLevelBranchLike(String value) {
            addCriterion("first_level_branch like", value, "firstLevelBranch");
            return (Criteria) this;
        }

        public Criteria andFirstLevelBranchNotLike(String value) {
            addCriterion("first_level_branch not like", value, "firstLevelBranch");
            return (Criteria) this;
        }

        public Criteria andFirstLevelBranchIn(List<String> values) {
            addCriterion("first_level_branch in", values, "firstLevelBranch");
            return (Criteria) this;
        }

        public Criteria andFirstLevelBranchNotIn(List<String> values) {
            addCriterion("first_level_branch not in", values, "firstLevelBranch");
            return (Criteria) this;
        }

        public Criteria andFirstLevelBranchBetween(String value1, String value2) {
            addCriterion("first_level_branch between", value1, value2, "firstLevelBranch");
            return (Criteria) this;
        }

        public Criteria andFirstLevelBranchNotBetween(String value1, String value2) {
            addCriterion("first_level_branch not between", value1, value2, "firstLevelBranch");
            return (Criteria) this;
        }

        public Criteria andSecondLevelBranchIsNull() {
            addCriterion("second_level_branch is null");
            return (Criteria) this;
        }

        public Criteria andSecondLevelBranchIsNotNull() {
            addCriterion("second_level_branch is not null");
            return (Criteria) this;
        }

        public Criteria andSecondLevelBranchEqualTo(String value) {
            addCriterion("second_level_branch =", value, "secondLevelBranch");
            return (Criteria) this;
        }

        public Criteria andSecondLevelBranchNotEqualTo(String value) {
            addCriterion("second_level_branch <>", value, "secondLevelBranch");
            return (Criteria) this;
        }

        public Criteria andSecondLevelBranchGreaterThan(String value) {
            addCriterion("second_level_branch >", value, "secondLevelBranch");
            return (Criteria) this;
        }

        public Criteria andSecondLevelBranchGreaterThanOrEqualTo(String value) {
            addCriterion("second_level_branch >=", value, "secondLevelBranch");
            return (Criteria) this;
        }

        public Criteria andSecondLevelBranchLessThan(String value) {
            addCriterion("second_level_branch <", value, "secondLevelBranch");
            return (Criteria) this;
        }

        public Criteria andSecondLevelBranchLessThanOrEqualTo(String value) {
            addCriterion("second_level_branch <=", value, "secondLevelBranch");
            return (Criteria) this;
        }

        public Criteria andSecondLevelBranchLike(String value) {
            addCriterion("second_level_branch like", value, "secondLevelBranch");
            return (Criteria) this;
        }

        public Criteria andSecondLevelBranchNotLike(String value) {
            addCriterion("second_level_branch not like", value, "secondLevelBranch");
            return (Criteria) this;
        }

        public Criteria andSecondLevelBranchIn(List<String> values) {
            addCriterion("second_level_branch in", values, "secondLevelBranch");
            return (Criteria) this;
        }

        public Criteria andSecondLevelBranchNotIn(List<String> values) {
            addCriterion("second_level_branch not in", values, "secondLevelBranch");
            return (Criteria) this;
        }

        public Criteria andSecondLevelBranchBetween(String value1, String value2) {
            addCriterion("second_level_branch between", value1, value2, "secondLevelBranch");
            return (Criteria) this;
        }

        public Criteria andSecondLevelBranchNotBetween(String value1, String value2) {
            addCriterion("second_level_branch not between", value1, value2, "secondLevelBranch");
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

        public Criteria andIdNumberIsNull() {
            addCriterion("id_number is null");
            return (Criteria) this;
        }

        public Criteria andIdNumberIsNotNull() {
            addCriterion("id_number is not null");
            return (Criteria) this;
        }

        public Criteria andIdNumberEqualTo(String value) {
            addCriterion("id_number =", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotEqualTo(String value) {
            addCriterion("id_number <>", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberGreaterThan(String value) {
            addCriterion("id_number >", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberGreaterThanOrEqualTo(String value) {
            addCriterion("id_number >=", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLessThan(String value) {
            addCriterion("id_number <", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLessThanOrEqualTo(String value) {
            addCriterion("id_number <=", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLike(String value) {
            addCriterion("id_number like", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotLike(String value) {
            addCriterion("id_number not like", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberIn(List<String> values) {
            addCriterion("id_number in", values, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotIn(List<String> values) {
            addCriterion("id_number not in", values, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberBetween(String value1, String value2) {
            addCriterion("id_number between", value1, value2, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotBetween(String value1, String value2) {
            addCriterion("id_number not between", value1, value2, "idNumber");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusIsNull() {
            addCriterion("marital_status is null");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusIsNotNull() {
            addCriterion("marital_status is not null");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusEqualTo(String value) {
            addCriterion("marital_status =", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusNotEqualTo(String value) {
            addCriterion("marital_status <>", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusGreaterThan(String value) {
            addCriterion("marital_status >", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusGreaterThanOrEqualTo(String value) {
            addCriterion("marital_status >=", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusLessThan(String value) {
            addCriterion("marital_status <", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusLessThanOrEqualTo(String value) {
            addCriterion("marital_status <=", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusLike(String value) {
            addCriterion("marital_status like", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusNotLike(String value) {
            addCriterion("marital_status not like", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusIn(List<String> values) {
            addCriterion("marital_status in", values, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusNotIn(List<String> values) {
            addCriterion("marital_status not in", values, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusBetween(String value1, String value2) {
            addCriterion("marital_status between", value1, value2, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusNotBetween(String value1, String value2) {
            addCriterion("marital_status not between", value1, value2, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andEducationIsNull() {
            addCriterion("education is null");
            return (Criteria) this;
        }

        public Criteria andEducationIsNotNull() {
            addCriterion("education is not null");
            return (Criteria) this;
        }

        public Criteria andEducationEqualTo(String value) {
            addCriterion("education =", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotEqualTo(String value) {
            addCriterion("education <>", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThan(String value) {
            addCriterion("education >", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThanOrEqualTo(String value) {
            addCriterion("education >=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThan(String value) {
            addCriterion("education <", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThanOrEqualTo(String value) {
            addCriterion("education <=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLike(String value) {
            addCriterion("education like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotLike(String value) {
            addCriterion("education not like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationIn(List<String> values) {
            addCriterion("education in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotIn(List<String> values) {
            addCriterion("education not in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationBetween(String value1, String value2) {
            addCriterion("education between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotBetween(String value1, String value2) {
            addCriterion("education not between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andWorkUnitIsNull() {
            addCriterion("work_unit is null");
            return (Criteria) this;
        }

        public Criteria andWorkUnitIsNotNull() {
            addCriterion("work_unit is not null");
            return (Criteria) this;
        }

        public Criteria andWorkUnitEqualTo(String value) {
            addCriterion("work_unit =", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotEqualTo(String value) {
            addCriterion("work_unit <>", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitGreaterThan(String value) {
            addCriterion("work_unit >", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitGreaterThanOrEqualTo(String value) {
            addCriterion("work_unit >=", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitLessThan(String value) {
            addCriterion("work_unit <", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitLessThanOrEqualTo(String value) {
            addCriterion("work_unit <=", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitLike(String value) {
            addCriterion("work_unit like", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotLike(String value) {
            addCriterion("work_unit not like", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitIn(List<String> values) {
            addCriterion("work_unit in", values, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotIn(List<String> values) {
            addCriterion("work_unit not in", values, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitBetween(String value1, String value2) {
            addCriterion("work_unit between", value1, value2, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotBetween(String value1, String value2) {
            addCriterion("work_unit not between", value1, value2, "workUnit");
            return (Criteria) this;
        }

        public Criteria andPostIsNull() {
            addCriterion("post is null");
            return (Criteria) this;
        }

        public Criteria andPostIsNotNull() {
            addCriterion("post is not null");
            return (Criteria) this;
        }

        public Criteria andPostEqualTo(String value) {
            addCriterion("post =", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotEqualTo(String value) {
            addCriterion("post <>", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostGreaterThan(String value) {
            addCriterion("post >", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostGreaterThanOrEqualTo(String value) {
            addCriterion("post >=", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostLessThan(String value) {
            addCriterion("post <", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostLessThanOrEqualTo(String value) {
            addCriterion("post <=", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostLike(String value) {
            addCriterion("post like", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotLike(String value) {
            addCriterion("post not like", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostIn(List<String> values) {
            addCriterion("post in", values, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotIn(List<String> values) {
            addCriterion("post not in", values, "post");
            return (Criteria) this;
        }

        public Criteria andPostBetween(String value1, String value2) {
            addCriterion("post between", value1, value2, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotBetween(String value1, String value2) {
            addCriterion("post not between", value1, value2, "post");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIsNull() {
            addCriterion("domicile_place is null");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIsNotNull() {
            addCriterion("domicile_place is not null");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceEqualTo(String value) {
            addCriterion("domicile_place =", value, "domicilePlace");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNotEqualTo(String value) {
            addCriterion("domicile_place <>", value, "domicilePlace");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceGreaterThan(String value) {
            addCriterion("domicile_place >", value, "domicilePlace");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceGreaterThanOrEqualTo(String value) {
            addCriterion("domicile_place >=", value, "domicilePlace");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceLessThan(String value) {
            addCriterion("domicile_place <", value, "domicilePlace");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceLessThanOrEqualTo(String value) {
            addCriterion("domicile_place <=", value, "domicilePlace");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceLike(String value) {
            addCriterion("domicile_place like", value, "domicilePlace");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNotLike(String value) {
            addCriterion("domicile_place not like", value, "domicilePlace");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIn(List<String> values) {
            addCriterion("domicile_place in", values, "domicilePlace");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNotIn(List<String> values) {
            addCriterion("domicile_place not in", values, "domicilePlace");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceBetween(String value1, String value2) {
            addCriterion("domicile_place between", value1, value2, "domicilePlace");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNotBetween(String value1, String value2) {
            addCriterion("domicile_place not between", value1, value2, "domicilePlace");
            return (Criteria) this;
        }

        public Criteria andPresentAddressIsNull() {
            addCriterion("present_address is null");
            return (Criteria) this;
        }

        public Criteria andPresentAddressIsNotNull() {
            addCriterion("present_address is not null");
            return (Criteria) this;
        }

        public Criteria andPresentAddressEqualTo(String value) {
            addCriterion("present_address =", value, "presentAddress");
            return (Criteria) this;
        }

        public Criteria andPresentAddressNotEqualTo(String value) {
            addCriterion("present_address <>", value, "presentAddress");
            return (Criteria) this;
        }

        public Criteria andPresentAddressGreaterThan(String value) {
            addCriterion("present_address >", value, "presentAddress");
            return (Criteria) this;
        }

        public Criteria andPresentAddressGreaterThanOrEqualTo(String value) {
            addCriterion("present_address >=", value, "presentAddress");
            return (Criteria) this;
        }

        public Criteria andPresentAddressLessThan(String value) {
            addCriterion("present_address <", value, "presentAddress");
            return (Criteria) this;
        }

        public Criteria andPresentAddressLessThanOrEqualTo(String value) {
            addCriterion("present_address <=", value, "presentAddress");
            return (Criteria) this;
        }

        public Criteria andPresentAddressLike(String value) {
            addCriterion("present_address like", value, "presentAddress");
            return (Criteria) this;
        }

        public Criteria andPresentAddressNotLike(String value) {
            addCriterion("present_address not like", value, "presentAddress");
            return (Criteria) this;
        }

        public Criteria andPresentAddressIn(List<String> values) {
            addCriterion("present_address in", values, "presentAddress");
            return (Criteria) this;
        }

        public Criteria andPresentAddressNotIn(List<String> values) {
            addCriterion("present_address not in", values, "presentAddress");
            return (Criteria) this;
        }

        public Criteria andPresentAddressBetween(String value1, String value2) {
            addCriterion("present_address between", value1, value2, "presentAddress");
            return (Criteria) this;
        }

        public Criteria andPresentAddressNotBetween(String value1, String value2) {
            addCriterion("present_address not between", value1, value2, "presentAddress");
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