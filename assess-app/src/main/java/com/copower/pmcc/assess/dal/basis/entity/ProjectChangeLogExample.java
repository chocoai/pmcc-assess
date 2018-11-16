package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectChangeLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectChangeLogExample() {
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

        public Criteria andOldRecordIsNull() {
            addCriterion("old_record is null");
            return (Criteria) this;
        }

        public Criteria andOldRecordIsNotNull() {
            addCriterion("old_record is not null");
            return (Criteria) this;
        }

        public Criteria andOldRecordEqualTo(String value) {
            addCriterion("old_record =", value, "oldRecord");
            return (Criteria) this;
        }

        public Criteria andOldRecordNotEqualTo(String value) {
            addCriterion("old_record <>", value, "oldRecord");
            return (Criteria) this;
        }

        public Criteria andOldRecordGreaterThan(String value) {
            addCriterion("old_record >", value, "oldRecord");
            return (Criteria) this;
        }

        public Criteria andOldRecordGreaterThanOrEqualTo(String value) {
            addCriterion("old_record >=", value, "oldRecord");
            return (Criteria) this;
        }

        public Criteria andOldRecordLessThan(String value) {
            addCriterion("old_record <", value, "oldRecord");
            return (Criteria) this;
        }

        public Criteria andOldRecordLessThanOrEqualTo(String value) {
            addCriterion("old_record <=", value, "oldRecord");
            return (Criteria) this;
        }

        public Criteria andOldRecordLike(String value) {
            addCriterion("old_record like", value, "oldRecord");
            return (Criteria) this;
        }

        public Criteria andOldRecordNotLike(String value) {
            addCriterion("old_record not like", value, "oldRecord");
            return (Criteria) this;
        }

        public Criteria andOldRecordIn(List<String> values) {
            addCriterion("old_record in", values, "oldRecord");
            return (Criteria) this;
        }

        public Criteria andOldRecordNotIn(List<String> values) {
            addCriterion("old_record not in", values, "oldRecord");
            return (Criteria) this;
        }

        public Criteria andOldRecordBetween(String value1, String value2) {
            addCriterion("old_record between", value1, value2, "oldRecord");
            return (Criteria) this;
        }

        public Criteria andOldRecordNotBetween(String value1, String value2) {
            addCriterion("old_record not between", value1, value2, "oldRecord");
            return (Criteria) this;
        }

        public Criteria andNewRecordIsNull() {
            addCriterion("new_record is null");
            return (Criteria) this;
        }

        public Criteria andNewRecordIsNotNull() {
            addCriterion("new_record is not null");
            return (Criteria) this;
        }

        public Criteria andNewRecordEqualTo(String value) {
            addCriterion("new_record =", value, "newRecord");
            return (Criteria) this;
        }

        public Criteria andNewRecordNotEqualTo(String value) {
            addCriterion("new_record <>", value, "newRecord");
            return (Criteria) this;
        }

        public Criteria andNewRecordGreaterThan(String value) {
            addCriterion("new_record >", value, "newRecord");
            return (Criteria) this;
        }

        public Criteria andNewRecordGreaterThanOrEqualTo(String value) {
            addCriterion("new_record >=", value, "newRecord");
            return (Criteria) this;
        }

        public Criteria andNewRecordLessThan(String value) {
            addCriterion("new_record <", value, "newRecord");
            return (Criteria) this;
        }

        public Criteria andNewRecordLessThanOrEqualTo(String value) {
            addCriterion("new_record <=", value, "newRecord");
            return (Criteria) this;
        }

        public Criteria andNewRecordLike(String value) {
            addCriterion("new_record like", value, "newRecord");
            return (Criteria) this;
        }

        public Criteria andNewRecordNotLike(String value) {
            addCriterion("new_record not like", value, "newRecord");
            return (Criteria) this;
        }

        public Criteria andNewRecordIn(List<String> values) {
            addCriterion("new_record in", values, "newRecord");
            return (Criteria) this;
        }

        public Criteria andNewRecordNotIn(List<String> values) {
            addCriterion("new_record not in", values, "newRecord");
            return (Criteria) this;
        }

        public Criteria andNewRecordBetween(String value1, String value2) {
            addCriterion("new_record between", value1, value2, "newRecord");
            return (Criteria) this;
        }

        public Criteria andNewRecordNotBetween(String value1, String value2) {
            addCriterion("new_record not between", value1, value2, "newRecord");
            return (Criteria) this;
        }

        public Criteria andChangeReasonIsNull() {
            addCriterion("change_reason is null");
            return (Criteria) this;
        }

        public Criteria andChangeReasonIsNotNull() {
            addCriterion("change_reason is not null");
            return (Criteria) this;
        }

        public Criteria andChangeReasonEqualTo(String value) {
            addCriterion("change_reason =", value, "changeReason");
            return (Criteria) this;
        }

        public Criteria andChangeReasonNotEqualTo(String value) {
            addCriterion("change_reason <>", value, "changeReason");
            return (Criteria) this;
        }

        public Criteria andChangeReasonGreaterThan(String value) {
            addCriterion("change_reason >", value, "changeReason");
            return (Criteria) this;
        }

        public Criteria andChangeReasonGreaterThanOrEqualTo(String value) {
            addCriterion("change_reason >=", value, "changeReason");
            return (Criteria) this;
        }

        public Criteria andChangeReasonLessThan(String value) {
            addCriterion("change_reason <", value, "changeReason");
            return (Criteria) this;
        }

        public Criteria andChangeReasonLessThanOrEqualTo(String value) {
            addCriterion("change_reason <=", value, "changeReason");
            return (Criteria) this;
        }

        public Criteria andChangeReasonLike(String value) {
            addCriterion("change_reason like", value, "changeReason");
            return (Criteria) this;
        }

        public Criteria andChangeReasonNotLike(String value) {
            addCriterion("change_reason not like", value, "changeReason");
            return (Criteria) this;
        }

        public Criteria andChangeReasonIn(List<String> values) {
            addCriterion("change_reason in", values, "changeReason");
            return (Criteria) this;
        }

        public Criteria andChangeReasonNotIn(List<String> values) {
            addCriterion("change_reason not in", values, "changeReason");
            return (Criteria) this;
        }

        public Criteria andChangeReasonBetween(String value1, String value2) {
            addCriterion("change_reason between", value1, value2, "changeReason");
            return (Criteria) this;
        }

        public Criteria andChangeReasonNotBetween(String value1, String value2) {
            addCriterion("change_reason not between", value1, value2, "changeReason");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIsNull() {
            addCriterion("change_type is null");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIsNotNull() {
            addCriterion("change_type is not null");
            return (Criteria) this;
        }

        public Criteria andChangeTypeEqualTo(String value) {
            addCriterion("change_type =", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotEqualTo(String value) {
            addCriterion("change_type <>", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeGreaterThan(String value) {
            addCriterion("change_type >", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("change_type >=", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeLessThan(String value) {
            addCriterion("change_type <", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeLessThanOrEqualTo(String value) {
            addCriterion("change_type <=", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeLike(String value) {
            addCriterion("change_type like", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotLike(String value) {
            addCriterion("change_type not like", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIn(List<String> values) {
            addCriterion("change_type in", values, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotIn(List<String> values) {
            addCriterion("change_type not in", values, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeBetween(String value1, String value2) {
            addCriterion("change_type between", value1, value2, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotBetween(String value1, String value2) {
            addCriterion("change_type not between", value1, value2, "changeType");
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

        public Criteria andModifiedIsNull() {
            addCriterion("modified is null");
            return (Criteria) this;
        }

        public Criteria andModifiedIsNotNull() {
            addCriterion("modified is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedEqualTo(Date value) {
            addCriterion("modified =", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotEqualTo(Date value) {
            addCriterion("modified <>", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedGreaterThan(Date value) {
            addCriterion("modified >", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("modified >=", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedLessThan(Date value) {
            addCriterion("modified <", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedLessThanOrEqualTo(Date value) {
            addCriterion("modified <=", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedIn(List<Date> values) {
            addCriterion("modified in", values, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotIn(List<Date> values) {
            addCriterion("modified not in", values, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedBetween(Date value1, Date value2) {
            addCriterion("modified between", value1, value2, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotBetween(Date value1, Date value2) {
            addCriterion("modified not between", value1, value2, "modified");
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