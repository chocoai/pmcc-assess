package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectMemberHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectMemberHistoryExample() {
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

        public Criteria andUserAccountManagerNewIsNull() {
            addCriterion("user_account_manager_new is null");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerNewIsNotNull() {
            addCriterion("user_account_manager_new is not null");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerNewEqualTo(String value) {
            addCriterion("user_account_manager_new =", value, "userAccountManagerNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerNewNotEqualTo(String value) {
            addCriterion("user_account_manager_new <>", value, "userAccountManagerNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerNewGreaterThan(String value) {
            addCriterion("user_account_manager_new >", value, "userAccountManagerNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerNewGreaterThanOrEqualTo(String value) {
            addCriterion("user_account_manager_new >=", value, "userAccountManagerNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerNewLessThan(String value) {
            addCriterion("user_account_manager_new <", value, "userAccountManagerNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerNewLessThanOrEqualTo(String value) {
            addCriterion("user_account_manager_new <=", value, "userAccountManagerNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerNewLike(String value) {
            addCriterion("user_account_manager_new like", value, "userAccountManagerNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerNewNotLike(String value) {
            addCriterion("user_account_manager_new not like", value, "userAccountManagerNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerNewIn(List<String> values) {
            addCriterion("user_account_manager_new in", values, "userAccountManagerNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerNewNotIn(List<String> values) {
            addCriterion("user_account_manager_new not in", values, "userAccountManagerNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerNewBetween(String value1, String value2) {
            addCriterion("user_account_manager_new between", value1, value2, "userAccountManagerNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerNewNotBetween(String value1, String value2) {
            addCriterion("user_account_manager_new not between", value1, value2, "userAccountManagerNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberNewIsNull() {
            addCriterion("user_account_member_new is null");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberNewIsNotNull() {
            addCriterion("user_account_member_new is not null");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberNewEqualTo(String value) {
            addCriterion("user_account_member_new =", value, "userAccountMemberNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberNewNotEqualTo(String value) {
            addCriterion("user_account_member_new <>", value, "userAccountMemberNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberNewGreaterThan(String value) {
            addCriterion("user_account_member_new >", value, "userAccountMemberNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberNewGreaterThanOrEqualTo(String value) {
            addCriterion("user_account_member_new >=", value, "userAccountMemberNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberNewLessThan(String value) {
            addCriterion("user_account_member_new <", value, "userAccountMemberNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberNewLessThanOrEqualTo(String value) {
            addCriterion("user_account_member_new <=", value, "userAccountMemberNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberNewLike(String value) {
            addCriterion("user_account_member_new like", value, "userAccountMemberNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberNewNotLike(String value) {
            addCriterion("user_account_member_new not like", value, "userAccountMemberNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberNewIn(List<String> values) {
            addCriterion("user_account_member_new in", values, "userAccountMemberNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberNewNotIn(List<String> values) {
            addCriterion("user_account_member_new not in", values, "userAccountMemberNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberNewBetween(String value1, String value2) {
            addCriterion("user_account_member_new between", value1, value2, "userAccountMemberNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberNewNotBetween(String value1, String value2) {
            addCriterion("user_account_member_new not between", value1, value2, "userAccountMemberNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityNewIsNull() {
            addCriterion("user_account_quality_new is null");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityNewIsNotNull() {
            addCriterion("user_account_quality_new is not null");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityNewEqualTo(String value) {
            addCriterion("user_account_quality_new =", value, "userAccountQualityNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityNewNotEqualTo(String value) {
            addCriterion("user_account_quality_new <>", value, "userAccountQualityNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityNewGreaterThan(String value) {
            addCriterion("user_account_quality_new >", value, "userAccountQualityNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityNewGreaterThanOrEqualTo(String value) {
            addCriterion("user_account_quality_new >=", value, "userAccountQualityNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityNewLessThan(String value) {
            addCriterion("user_account_quality_new <", value, "userAccountQualityNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityNewLessThanOrEqualTo(String value) {
            addCriterion("user_account_quality_new <=", value, "userAccountQualityNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityNewLike(String value) {
            addCriterion("user_account_quality_new like", value, "userAccountQualityNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityNewNotLike(String value) {
            addCriterion("user_account_quality_new not like", value, "userAccountQualityNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityNewIn(List<String> values) {
            addCriterion("user_account_quality_new in", values, "userAccountQualityNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityNewNotIn(List<String> values) {
            addCriterion("user_account_quality_new not in", values, "userAccountQualityNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityNewBetween(String value1, String value2) {
            addCriterion("user_account_quality_new between", value1, value2, "userAccountQualityNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityNewNotBetween(String value1, String value2) {
            addCriterion("user_account_quality_new not between", value1, value2, "userAccountQualityNew");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerOldIsNull() {
            addCriterion("user_account_manager_old is null");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerOldIsNotNull() {
            addCriterion("user_account_manager_old is not null");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerOldEqualTo(String value) {
            addCriterion("user_account_manager_old =", value, "userAccountManagerOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerOldNotEqualTo(String value) {
            addCriterion("user_account_manager_old <>", value, "userAccountManagerOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerOldGreaterThan(String value) {
            addCriterion("user_account_manager_old >", value, "userAccountManagerOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerOldGreaterThanOrEqualTo(String value) {
            addCriterion("user_account_manager_old >=", value, "userAccountManagerOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerOldLessThan(String value) {
            addCriterion("user_account_manager_old <", value, "userAccountManagerOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerOldLessThanOrEqualTo(String value) {
            addCriterion("user_account_manager_old <=", value, "userAccountManagerOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerOldLike(String value) {
            addCriterion("user_account_manager_old like", value, "userAccountManagerOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerOldNotLike(String value) {
            addCriterion("user_account_manager_old not like", value, "userAccountManagerOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerOldIn(List<String> values) {
            addCriterion("user_account_manager_old in", values, "userAccountManagerOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerOldNotIn(List<String> values) {
            addCriterion("user_account_manager_old not in", values, "userAccountManagerOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerOldBetween(String value1, String value2) {
            addCriterion("user_account_manager_old between", value1, value2, "userAccountManagerOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountManagerOldNotBetween(String value1, String value2) {
            addCriterion("user_account_manager_old not between", value1, value2, "userAccountManagerOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberOldIsNull() {
            addCriterion("user_account_member_old is null");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberOldIsNotNull() {
            addCriterion("user_account_member_old is not null");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberOldEqualTo(String value) {
            addCriterion("user_account_member_old =", value, "userAccountMemberOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberOldNotEqualTo(String value) {
            addCriterion("user_account_member_old <>", value, "userAccountMemberOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberOldGreaterThan(String value) {
            addCriterion("user_account_member_old >", value, "userAccountMemberOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberOldGreaterThanOrEqualTo(String value) {
            addCriterion("user_account_member_old >=", value, "userAccountMemberOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberOldLessThan(String value) {
            addCriterion("user_account_member_old <", value, "userAccountMemberOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberOldLessThanOrEqualTo(String value) {
            addCriterion("user_account_member_old <=", value, "userAccountMemberOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberOldLike(String value) {
            addCriterion("user_account_member_old like", value, "userAccountMemberOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberOldNotLike(String value) {
            addCriterion("user_account_member_old not like", value, "userAccountMemberOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberOldIn(List<String> values) {
            addCriterion("user_account_member_old in", values, "userAccountMemberOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberOldNotIn(List<String> values) {
            addCriterion("user_account_member_old not in", values, "userAccountMemberOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberOldBetween(String value1, String value2) {
            addCriterion("user_account_member_old between", value1, value2, "userAccountMemberOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountMemberOldNotBetween(String value1, String value2) {
            addCriterion("user_account_member_old not between", value1, value2, "userAccountMemberOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityOldIsNull() {
            addCriterion("user_account_quality_old is null");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityOldIsNotNull() {
            addCriterion("user_account_quality_old is not null");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityOldEqualTo(String value) {
            addCriterion("user_account_quality_old =", value, "userAccountQualityOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityOldNotEqualTo(String value) {
            addCriterion("user_account_quality_old <>", value, "userAccountQualityOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityOldGreaterThan(String value) {
            addCriterion("user_account_quality_old >", value, "userAccountQualityOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityOldGreaterThanOrEqualTo(String value) {
            addCriterion("user_account_quality_old >=", value, "userAccountQualityOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityOldLessThan(String value) {
            addCriterion("user_account_quality_old <", value, "userAccountQualityOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityOldLessThanOrEqualTo(String value) {
            addCriterion("user_account_quality_old <=", value, "userAccountQualityOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityOldLike(String value) {
            addCriterion("user_account_quality_old like", value, "userAccountQualityOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityOldNotLike(String value) {
            addCriterion("user_account_quality_old not like", value, "userAccountQualityOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityOldIn(List<String> values) {
            addCriterion("user_account_quality_old in", values, "userAccountQualityOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityOldNotIn(List<String> values) {
            addCriterion("user_account_quality_old not in", values, "userAccountQualityOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityOldBetween(String value1, String value2) {
            addCriterion("user_account_quality_old between", value1, value2, "userAccountQualityOld");
            return (Criteria) this;
        }

        public Criteria andUserAccountQualityOldNotBetween(String value1, String value2) {
            addCriterion("user_account_quality_old not between", value1, value2, "userAccountQualityOld");
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

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
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