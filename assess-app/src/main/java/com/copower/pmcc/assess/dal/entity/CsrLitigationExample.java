package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsrLitigationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CsrLitigationExample() {
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

        public Criteria andCsrProjectIdIsNull() {
            addCriterion("csr_project_id is null");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdIsNotNull() {
            addCriterion("csr_project_id is not null");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdEqualTo(Integer value) {
            addCriterion("csr_project_id =", value, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdNotEqualTo(Integer value) {
            addCriterion("csr_project_id <>", value, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdGreaterThan(Integer value) {
            addCriterion("csr_project_id >", value, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("csr_project_id >=", value, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdLessThan(Integer value) {
            addCriterion("csr_project_id <", value, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("csr_project_id <=", value, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdIn(List<Integer> values) {
            addCriterion("csr_project_id in", values, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdNotIn(List<Integer> values) {
            addCriterion("csr_project_id not in", values, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("csr_project_id between", value1, value2, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("csr_project_id not between", value1, value2, "csrProjectId");
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

        public Criteria andLitigationPreservationIsNull() {
            addCriterion("litigation_preservation is null");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationIsNotNull() {
            addCriterion("litigation_preservation is not null");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationEqualTo(String value) {
            addCriterion("litigation_preservation =", value, "litigationPreservation");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationNotEqualTo(String value) {
            addCriterion("litigation_preservation <>", value, "litigationPreservation");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationGreaterThan(String value) {
            addCriterion("litigation_preservation >", value, "litigationPreservation");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationGreaterThanOrEqualTo(String value) {
            addCriterion("litigation_preservation >=", value, "litigationPreservation");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationLessThan(String value) {
            addCriterion("litigation_preservation <", value, "litigationPreservation");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationLessThanOrEqualTo(String value) {
            addCriterion("litigation_preservation <=", value, "litigationPreservation");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationLike(String value) {
            addCriterion("litigation_preservation like", value, "litigationPreservation");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationNotLike(String value) {
            addCriterion("litigation_preservation not like", value, "litigationPreservation");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationIn(List<String> values) {
            addCriterion("litigation_preservation in", values, "litigationPreservation");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationNotIn(List<String> values) {
            addCriterion("litigation_preservation not in", values, "litigationPreservation");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationBetween(String value1, String value2) {
            addCriterion("litigation_preservation between", value1, value2, "litigationPreservation");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationNotBetween(String value1, String value2) {
            addCriterion("litigation_preservation not between", value1, value2, "litigationPreservation");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationInfoIsNull() {
            addCriterion("litigation_preservation_info is null");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationInfoIsNotNull() {
            addCriterion("litigation_preservation_info is not null");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationInfoEqualTo(String value) {
            addCriterion("litigation_preservation_info =", value, "litigationPreservationInfo");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationInfoNotEqualTo(String value) {
            addCriterion("litigation_preservation_info <>", value, "litigationPreservationInfo");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationInfoGreaterThan(String value) {
            addCriterion("litigation_preservation_info >", value, "litigationPreservationInfo");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationInfoGreaterThanOrEqualTo(String value) {
            addCriterion("litigation_preservation_info >=", value, "litigationPreservationInfo");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationInfoLessThan(String value) {
            addCriterion("litigation_preservation_info <", value, "litigationPreservationInfo");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationInfoLessThanOrEqualTo(String value) {
            addCriterion("litigation_preservation_info <=", value, "litigationPreservationInfo");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationInfoLike(String value) {
            addCriterion("litigation_preservation_info like", value, "litigationPreservationInfo");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationInfoNotLike(String value) {
            addCriterion("litigation_preservation_info not like", value, "litigationPreservationInfo");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationInfoIn(List<String> values) {
            addCriterion("litigation_preservation_info in", values, "litigationPreservationInfo");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationInfoNotIn(List<String> values) {
            addCriterion("litigation_preservation_info not in", values, "litigationPreservationInfo");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationInfoBetween(String value1, String value2) {
            addCriterion("litigation_preservation_info between", value1, value2, "litigationPreservationInfo");
            return (Criteria) this;
        }

        public Criteria andLitigationPreservationInfoNotBetween(String value1, String value2) {
            addCriterion("litigation_preservation_info not between", value1, value2, "litigationPreservationInfo");
            return (Criteria) this;
        }

        public Criteria andBisImportIsNull() {
            addCriterion("bis_import is null");
            return (Criteria) this;
        }

        public Criteria andBisImportIsNotNull() {
            addCriterion("bis_import is not null");
            return (Criteria) this;
        }

        public Criteria andBisImportEqualTo(Boolean value) {
            addCriterion("bis_import =", value, "bisImport");
            return (Criteria) this;
        }

        public Criteria andBisImportNotEqualTo(Boolean value) {
            addCriterion("bis_import <>", value, "bisImport");
            return (Criteria) this;
        }

        public Criteria andBisImportGreaterThan(Boolean value) {
            addCriterion("bis_import >", value, "bisImport");
            return (Criteria) this;
        }

        public Criteria andBisImportGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_import >=", value, "bisImport");
            return (Criteria) this;
        }

        public Criteria andBisImportLessThan(Boolean value) {
            addCriterion("bis_import <", value, "bisImport");
            return (Criteria) this;
        }

        public Criteria andBisImportLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_import <=", value, "bisImport");
            return (Criteria) this;
        }

        public Criteria andBisImportIn(List<Boolean> values) {
            addCriterion("bis_import in", values, "bisImport");
            return (Criteria) this;
        }

        public Criteria andBisImportNotIn(List<Boolean> values) {
            addCriterion("bis_import not in", values, "bisImport");
            return (Criteria) this;
        }

        public Criteria andBisImportBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_import between", value1, value2, "bisImport");
            return (Criteria) this;
        }

        public Criteria andBisImportNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_import not between", value1, value2, "bisImport");
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