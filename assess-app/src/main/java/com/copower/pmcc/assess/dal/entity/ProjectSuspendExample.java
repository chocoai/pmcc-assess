package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectSuspendExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectSuspendExample() {
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

        public Criteria andSuspendUserAccountIsNull() {
            addCriterion("suspend_user_account is null");
            return (Criteria) this;
        }

        public Criteria andSuspendUserAccountIsNotNull() {
            addCriterion("suspend_user_account is not null");
            return (Criteria) this;
        }

        public Criteria andSuspendUserAccountEqualTo(String value) {
            addCriterion("suspend_user_account =", value, "suspendUserAccount");
            return (Criteria) this;
        }

        public Criteria andSuspendUserAccountNotEqualTo(String value) {
            addCriterion("suspend_user_account <>", value, "suspendUserAccount");
            return (Criteria) this;
        }

        public Criteria andSuspendUserAccountGreaterThan(String value) {
            addCriterion("suspend_user_account >", value, "suspendUserAccount");
            return (Criteria) this;
        }

        public Criteria andSuspendUserAccountGreaterThanOrEqualTo(String value) {
            addCriterion("suspend_user_account >=", value, "suspendUserAccount");
            return (Criteria) this;
        }

        public Criteria andSuspendUserAccountLessThan(String value) {
            addCriterion("suspend_user_account <", value, "suspendUserAccount");
            return (Criteria) this;
        }

        public Criteria andSuspendUserAccountLessThanOrEqualTo(String value) {
            addCriterion("suspend_user_account <=", value, "suspendUserAccount");
            return (Criteria) this;
        }

        public Criteria andSuspendUserAccountLike(String value) {
            addCriterion("suspend_user_account like", value, "suspendUserAccount");
            return (Criteria) this;
        }

        public Criteria andSuspendUserAccountNotLike(String value) {
            addCriterion("suspend_user_account not like", value, "suspendUserAccount");
            return (Criteria) this;
        }

        public Criteria andSuspendUserAccountIn(List<String> values) {
            addCriterion("suspend_user_account in", values, "suspendUserAccount");
            return (Criteria) this;
        }

        public Criteria andSuspendUserAccountNotIn(List<String> values) {
            addCriterion("suspend_user_account not in", values, "suspendUserAccount");
            return (Criteria) this;
        }

        public Criteria andSuspendUserAccountBetween(String value1, String value2) {
            addCriterion("suspend_user_account between", value1, value2, "suspendUserAccount");
            return (Criteria) this;
        }

        public Criteria andSuspendUserAccountNotBetween(String value1, String value2) {
            addCriterion("suspend_user_account not between", value1, value2, "suspendUserAccount");
            return (Criteria) this;
        }

        public Criteria andSupendDateIsNull() {
            addCriterion("supend_date is null");
            return (Criteria) this;
        }

        public Criteria andSupendDateIsNotNull() {
            addCriterion("supend_date is not null");
            return (Criteria) this;
        }

        public Criteria andSupendDateEqualTo(Date value) {
            addCriterion("supend_date =", value, "supendDate");
            return (Criteria) this;
        }

        public Criteria andSupendDateNotEqualTo(Date value) {
            addCriterion("supend_date <>", value, "supendDate");
            return (Criteria) this;
        }

        public Criteria andSupendDateGreaterThan(Date value) {
            addCriterion("supend_date >", value, "supendDate");
            return (Criteria) this;
        }

        public Criteria andSupendDateGreaterThanOrEqualTo(Date value) {
            addCriterion("supend_date >=", value, "supendDate");
            return (Criteria) this;
        }

        public Criteria andSupendDateLessThan(Date value) {
            addCriterion("supend_date <", value, "supendDate");
            return (Criteria) this;
        }

        public Criteria andSupendDateLessThanOrEqualTo(Date value) {
            addCriterion("supend_date <=", value, "supendDate");
            return (Criteria) this;
        }

        public Criteria andSupendDateIn(List<Date> values) {
            addCriterion("supend_date in", values, "supendDate");
            return (Criteria) this;
        }

        public Criteria andSupendDateNotIn(List<Date> values) {
            addCriterion("supend_date not in", values, "supendDate");
            return (Criteria) this;
        }

        public Criteria andSupendDateBetween(Date value1, Date value2) {
            addCriterion("supend_date between", value1, value2, "supendDate");
            return (Criteria) this;
        }

        public Criteria andSupendDateNotBetween(Date value1, Date value2) {
            addCriterion("supend_date not between", value1, value2, "supendDate");
            return (Criteria) this;
        }

        public Criteria andSuspendReasonIsNull() {
            addCriterion("suspend_reason is null");
            return (Criteria) this;
        }

        public Criteria andSuspendReasonIsNotNull() {
            addCriterion("suspend_reason is not null");
            return (Criteria) this;
        }

        public Criteria andSuspendReasonEqualTo(String value) {
            addCriterion("suspend_reason =", value, "suspendReason");
            return (Criteria) this;
        }

        public Criteria andSuspendReasonNotEqualTo(String value) {
            addCriterion("suspend_reason <>", value, "suspendReason");
            return (Criteria) this;
        }

        public Criteria andSuspendReasonGreaterThan(String value) {
            addCriterion("suspend_reason >", value, "suspendReason");
            return (Criteria) this;
        }

        public Criteria andSuspendReasonGreaterThanOrEqualTo(String value) {
            addCriterion("suspend_reason >=", value, "suspendReason");
            return (Criteria) this;
        }

        public Criteria andSuspendReasonLessThan(String value) {
            addCriterion("suspend_reason <", value, "suspendReason");
            return (Criteria) this;
        }

        public Criteria andSuspendReasonLessThanOrEqualTo(String value) {
            addCriterion("suspend_reason <=", value, "suspendReason");
            return (Criteria) this;
        }

        public Criteria andSuspendReasonLike(String value) {
            addCriterion("suspend_reason like", value, "suspendReason");
            return (Criteria) this;
        }

        public Criteria andSuspendReasonNotLike(String value) {
            addCriterion("suspend_reason not like", value, "suspendReason");
            return (Criteria) this;
        }

        public Criteria andSuspendReasonIn(List<String> values) {
            addCriterion("suspend_reason in", values, "suspendReason");
            return (Criteria) this;
        }

        public Criteria andSuspendReasonNotIn(List<String> values) {
            addCriterion("suspend_reason not in", values, "suspendReason");
            return (Criteria) this;
        }

        public Criteria andSuspendReasonBetween(String value1, String value2) {
            addCriterion("suspend_reason between", value1, value2, "suspendReason");
            return (Criteria) this;
        }

        public Criteria andSuspendReasonNotBetween(String value1, String value2) {
            addCriterion("suspend_reason not between", value1, value2, "suspendReason");
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

        public Criteria andRestartDateIsNull() {
            addCriterion("restart_date is null");
            return (Criteria) this;
        }

        public Criteria andRestartDateIsNotNull() {
            addCriterion("restart_date is not null");
            return (Criteria) this;
        }

        public Criteria andRestartDateEqualTo(Date value) {
            addCriterion("restart_date =", value, "restartDate");
            return (Criteria) this;
        }

        public Criteria andRestartDateNotEqualTo(Date value) {
            addCriterion("restart_date <>", value, "restartDate");
            return (Criteria) this;
        }

        public Criteria andRestartDateGreaterThan(Date value) {
            addCriterion("restart_date >", value, "restartDate");
            return (Criteria) this;
        }

        public Criteria andRestartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("restart_date >=", value, "restartDate");
            return (Criteria) this;
        }

        public Criteria andRestartDateLessThan(Date value) {
            addCriterion("restart_date <", value, "restartDate");
            return (Criteria) this;
        }

        public Criteria andRestartDateLessThanOrEqualTo(Date value) {
            addCriterion("restart_date <=", value, "restartDate");
            return (Criteria) this;
        }

        public Criteria andRestartDateIn(List<Date> values) {
            addCriterion("restart_date in", values, "restartDate");
            return (Criteria) this;
        }

        public Criteria andRestartDateNotIn(List<Date> values) {
            addCriterion("restart_date not in", values, "restartDate");
            return (Criteria) this;
        }

        public Criteria andRestartDateBetween(Date value1, Date value2) {
            addCriterion("restart_date between", value1, value2, "restartDate");
            return (Criteria) this;
        }

        public Criteria andRestartDateNotBetween(Date value1, Date value2) {
            addCriterion("restart_date not between", value1, value2, "restartDate");
            return (Criteria) this;
        }

        public Criteria andRestartUserAccountIsNull() {
            addCriterion("restart_user_account is null");
            return (Criteria) this;
        }

        public Criteria andRestartUserAccountIsNotNull() {
            addCriterion("restart_user_account is not null");
            return (Criteria) this;
        }

        public Criteria andRestartUserAccountEqualTo(String value) {
            addCriterion("restart_user_account =", value, "restartUserAccount");
            return (Criteria) this;
        }

        public Criteria andRestartUserAccountNotEqualTo(String value) {
            addCriterion("restart_user_account <>", value, "restartUserAccount");
            return (Criteria) this;
        }

        public Criteria andRestartUserAccountGreaterThan(String value) {
            addCriterion("restart_user_account >", value, "restartUserAccount");
            return (Criteria) this;
        }

        public Criteria andRestartUserAccountGreaterThanOrEqualTo(String value) {
            addCriterion("restart_user_account >=", value, "restartUserAccount");
            return (Criteria) this;
        }

        public Criteria andRestartUserAccountLessThan(String value) {
            addCriterion("restart_user_account <", value, "restartUserAccount");
            return (Criteria) this;
        }

        public Criteria andRestartUserAccountLessThanOrEqualTo(String value) {
            addCriterion("restart_user_account <=", value, "restartUserAccount");
            return (Criteria) this;
        }

        public Criteria andRestartUserAccountLike(String value) {
            addCriterion("restart_user_account like", value, "restartUserAccount");
            return (Criteria) this;
        }

        public Criteria andRestartUserAccountNotLike(String value) {
            addCriterion("restart_user_account not like", value, "restartUserAccount");
            return (Criteria) this;
        }

        public Criteria andRestartUserAccountIn(List<String> values) {
            addCriterion("restart_user_account in", values, "restartUserAccount");
            return (Criteria) this;
        }

        public Criteria andRestartUserAccountNotIn(List<String> values) {
            addCriterion("restart_user_account not in", values, "restartUserAccount");
            return (Criteria) this;
        }

        public Criteria andRestartUserAccountBetween(String value1, String value2) {
            addCriterion("restart_user_account between", value1, value2, "restartUserAccount");
            return (Criteria) this;
        }

        public Criteria andRestartUserAccountNotBetween(String value1, String value2) {
            addCriterion("restart_user_account not between", value1, value2, "restartUserAccount");
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