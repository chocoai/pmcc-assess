package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataNumberRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataNumberRuleExample() {
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

        public Criteria andAssessClassIsNull() {
            addCriterion("assess_class is null");
            return (Criteria) this;
        }

        public Criteria andAssessClassIsNotNull() {
            addCriterion("assess_class is not null");
            return (Criteria) this;
        }

        public Criteria andAssessClassEqualTo(Integer value) {
            addCriterion("assess_class =", value, "assessClass");
            return (Criteria) this;
        }

        public Criteria andAssessClassNotEqualTo(Integer value) {
            addCriterion("assess_class <>", value, "assessClass");
            return (Criteria) this;
        }

        public Criteria andAssessClassGreaterThan(Integer value) {
            addCriterion("assess_class >", value, "assessClass");
            return (Criteria) this;
        }

        public Criteria andAssessClassGreaterThanOrEqualTo(Integer value) {
            addCriterion("assess_class >=", value, "assessClass");
            return (Criteria) this;
        }

        public Criteria andAssessClassLessThan(Integer value) {
            addCriterion("assess_class <", value, "assessClass");
            return (Criteria) this;
        }

        public Criteria andAssessClassLessThanOrEqualTo(Integer value) {
            addCriterion("assess_class <=", value, "assessClass");
            return (Criteria) this;
        }

        public Criteria andAssessClassIn(List<Integer> values) {
            addCriterion("assess_class in", values, "assessClass");
            return (Criteria) this;
        }

        public Criteria andAssessClassNotIn(List<Integer> values) {
            addCriterion("assess_class not in", values, "assessClass");
            return (Criteria) this;
        }

        public Criteria andAssessClassBetween(Integer value1, Integer value2) {
            addCriterion("assess_class between", value1, value2, "assessClass");
            return (Criteria) this;
        }

        public Criteria andAssessClassNotBetween(Integer value1, Integer value2) {
            addCriterion("assess_class not between", value1, value2, "assessClass");
            return (Criteria) this;
        }

        public Criteria andReportTypeIsNull() {
            addCriterion("report_type is null");
            return (Criteria) this;
        }

        public Criteria andReportTypeIsNotNull() {
            addCriterion("report_type is not null");
            return (Criteria) this;
        }

        public Criteria andReportTypeEqualTo(Integer value) {
            addCriterion("report_type =", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotEqualTo(Integer value) {
            addCriterion("report_type <>", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeGreaterThan(Integer value) {
            addCriterion("report_type >", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("report_type >=", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeLessThan(Integer value) {
            addCriterion("report_type <", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeLessThanOrEqualTo(Integer value) {
            addCriterion("report_type <=", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeIn(List<Integer> values) {
            addCriterion("report_type in", values, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotIn(List<Integer> values) {
            addCriterion("report_type not in", values, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeBetween(Integer value1, Integer value2) {
            addCriterion("report_type between", value1, value2, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("report_type not between", value1, value2, "reportType");
            return (Criteria) this;
        }

        public Criteria andPrefixIsNull() {
            addCriterion("prefix is null");
            return (Criteria) this;
        }

        public Criteria andPrefixIsNotNull() {
            addCriterion("prefix is not null");
            return (Criteria) this;
        }

        public Criteria andPrefixEqualTo(String value) {
            addCriterion("prefix =", value, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixNotEqualTo(String value) {
            addCriterion("prefix <>", value, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixGreaterThan(String value) {
            addCriterion("prefix >", value, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixGreaterThanOrEqualTo(String value) {
            addCriterion("prefix >=", value, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixLessThan(String value) {
            addCriterion("prefix <", value, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixLessThanOrEqualTo(String value) {
            addCriterion("prefix <=", value, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixLike(String value) {
            addCriterion("prefix like", value, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixNotLike(String value) {
            addCriterion("prefix not like", value, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixIn(List<String> values) {
            addCriterion("prefix in", values, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixNotIn(List<String> values) {
            addCriterion("prefix not in", values, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixBetween(String value1, String value2) {
            addCriterion("prefix between", value1, value2, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixNotBetween(String value1, String value2) {
            addCriterion("prefix not between", value1, value2, "prefix");
            return (Criteria) this;
        }

        public Criteria andDateRuleIsNull() {
            addCriterion("date_rule is null");
            return (Criteria) this;
        }

        public Criteria andDateRuleIsNotNull() {
            addCriterion("date_rule is not null");
            return (Criteria) this;
        }

        public Criteria andDateRuleEqualTo(String value) {
            addCriterion("date_rule =", value, "dateRule");
            return (Criteria) this;
        }

        public Criteria andDateRuleNotEqualTo(String value) {
            addCriterion("date_rule <>", value, "dateRule");
            return (Criteria) this;
        }

        public Criteria andDateRuleGreaterThan(String value) {
            addCriterion("date_rule >", value, "dateRule");
            return (Criteria) this;
        }

        public Criteria andDateRuleGreaterThanOrEqualTo(String value) {
            addCriterion("date_rule >=", value, "dateRule");
            return (Criteria) this;
        }

        public Criteria andDateRuleLessThan(String value) {
            addCriterion("date_rule <", value, "dateRule");
            return (Criteria) this;
        }

        public Criteria andDateRuleLessThanOrEqualTo(String value) {
            addCriterion("date_rule <=", value, "dateRule");
            return (Criteria) this;
        }

        public Criteria andDateRuleLike(String value) {
            addCriterion("date_rule like", value, "dateRule");
            return (Criteria) this;
        }

        public Criteria andDateRuleNotLike(String value) {
            addCriterion("date_rule not like", value, "dateRule");
            return (Criteria) this;
        }

        public Criteria andDateRuleIn(List<String> values) {
            addCriterion("date_rule in", values, "dateRule");
            return (Criteria) this;
        }

        public Criteria andDateRuleNotIn(List<String> values) {
            addCriterion("date_rule not in", values, "dateRule");
            return (Criteria) this;
        }

        public Criteria andDateRuleBetween(String value1, String value2) {
            addCriterion("date_rule between", value1, value2, "dateRule");
            return (Criteria) this;
        }

        public Criteria andDateRuleNotBetween(String value1, String value2) {
            addCriterion("date_rule not between", value1, value2, "dateRule");
            return (Criteria) this;
        }

        public Criteria andFiguresIsNull() {
            addCriterion("figures is null");
            return (Criteria) this;
        }

        public Criteria andFiguresIsNotNull() {
            addCriterion("figures is not null");
            return (Criteria) this;
        }

        public Criteria andFiguresEqualTo(Integer value) {
            addCriterion("figures =", value, "figures");
            return (Criteria) this;
        }

        public Criteria andFiguresNotEqualTo(Integer value) {
            addCriterion("figures <>", value, "figures");
            return (Criteria) this;
        }

        public Criteria andFiguresGreaterThan(Integer value) {
            addCriterion("figures >", value, "figures");
            return (Criteria) this;
        }

        public Criteria andFiguresGreaterThanOrEqualTo(Integer value) {
            addCriterion("figures >=", value, "figures");
            return (Criteria) this;
        }

        public Criteria andFiguresLessThan(Integer value) {
            addCriterion("figures <", value, "figures");
            return (Criteria) this;
        }

        public Criteria andFiguresLessThanOrEqualTo(Integer value) {
            addCriterion("figures <=", value, "figures");
            return (Criteria) this;
        }

        public Criteria andFiguresIn(List<Integer> values) {
            addCriterion("figures in", values, "figures");
            return (Criteria) this;
        }

        public Criteria andFiguresNotIn(List<Integer> values) {
            addCriterion("figures not in", values, "figures");
            return (Criteria) this;
        }

        public Criteria andFiguresBetween(Integer value1, Integer value2) {
            addCriterion("figures between", value1, value2, "figures");
            return (Criteria) this;
        }

        public Criteria andFiguresNotBetween(Integer value1, Integer value2) {
            addCriterion("figures not between", value1, value2, "figures");
            return (Criteria) this;
        }

        public Criteria andStartNumberIsNull() {
            addCriterion("start_number is null");
            return (Criteria) this;
        }

        public Criteria andStartNumberIsNotNull() {
            addCriterion("start_number is not null");
            return (Criteria) this;
        }

        public Criteria andStartNumberEqualTo(Integer value) {
            addCriterion("start_number =", value, "startNumber");
            return (Criteria) this;
        }

        public Criteria andStartNumberNotEqualTo(Integer value) {
            addCriterion("start_number <>", value, "startNumber");
            return (Criteria) this;
        }

        public Criteria andStartNumberGreaterThan(Integer value) {
            addCriterion("start_number >", value, "startNumber");
            return (Criteria) this;
        }

        public Criteria andStartNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("start_number >=", value, "startNumber");
            return (Criteria) this;
        }

        public Criteria andStartNumberLessThan(Integer value) {
            addCriterion("start_number <", value, "startNumber");
            return (Criteria) this;
        }

        public Criteria andStartNumberLessThanOrEqualTo(Integer value) {
            addCriterion("start_number <=", value, "startNumber");
            return (Criteria) this;
        }

        public Criteria andStartNumberIn(List<Integer> values) {
            addCriterion("start_number in", values, "startNumber");
            return (Criteria) this;
        }

        public Criteria andStartNumberNotIn(List<Integer> values) {
            addCriterion("start_number not in", values, "startNumber");
            return (Criteria) this;
        }

        public Criteria andStartNumberBetween(Integer value1, Integer value2) {
            addCriterion("start_number between", value1, value2, "startNumber");
            return (Criteria) this;
        }

        public Criteria andStartNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("start_number not between", value1, value2, "startNumber");
            return (Criteria) this;
        }

        public Criteria andSameReportTypeIsNull() {
            addCriterion("same_report_type is null");
            return (Criteria) this;
        }

        public Criteria andSameReportTypeIsNotNull() {
            addCriterion("same_report_type is not null");
            return (Criteria) this;
        }

        public Criteria andSameReportTypeEqualTo(Integer value) {
            addCriterion("same_report_type =", value, "sameReportType");
            return (Criteria) this;
        }

        public Criteria andSameReportTypeNotEqualTo(Integer value) {
            addCriterion("same_report_type <>", value, "sameReportType");
            return (Criteria) this;
        }

        public Criteria andSameReportTypeGreaterThan(Integer value) {
            addCriterion("same_report_type >", value, "sameReportType");
            return (Criteria) this;
        }

        public Criteria andSameReportTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("same_report_type >=", value, "sameReportType");
            return (Criteria) this;
        }

        public Criteria andSameReportTypeLessThan(Integer value) {
            addCriterion("same_report_type <", value, "sameReportType");
            return (Criteria) this;
        }

        public Criteria andSameReportTypeLessThanOrEqualTo(Integer value) {
            addCriterion("same_report_type <=", value, "sameReportType");
            return (Criteria) this;
        }

        public Criteria andSameReportTypeIn(List<Integer> values) {
            addCriterion("same_report_type in", values, "sameReportType");
            return (Criteria) this;
        }

        public Criteria andSameReportTypeNotIn(List<Integer> values) {
            addCriterion("same_report_type not in", values, "sameReportType");
            return (Criteria) this;
        }

        public Criteria andSameReportTypeBetween(Integer value1, Integer value2) {
            addCriterion("same_report_type between", value1, value2, "sameReportType");
            return (Criteria) this;
        }

        public Criteria andSameReportTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("same_report_type not between", value1, value2, "sameReportType");
            return (Criteria) this;
        }

        public Criteria andRecountIsNull() {
            addCriterion("recount is null");
            return (Criteria) this;
        }

        public Criteria andRecountIsNotNull() {
            addCriterion("recount is not null");
            return (Criteria) this;
        }

        public Criteria andRecountEqualTo(Integer value) {
            addCriterion("recount =", value, "recount");
            return (Criteria) this;
        }

        public Criteria andRecountNotEqualTo(Integer value) {
            addCriterion("recount <>", value, "recount");
            return (Criteria) this;
        }

        public Criteria andRecountGreaterThan(Integer value) {
            addCriterion("recount >", value, "recount");
            return (Criteria) this;
        }

        public Criteria andRecountGreaterThanOrEqualTo(Integer value) {
            addCriterion("recount >=", value, "recount");
            return (Criteria) this;
        }

        public Criteria andRecountLessThan(Integer value) {
            addCriterion("recount <", value, "recount");
            return (Criteria) this;
        }

        public Criteria andRecountLessThanOrEqualTo(Integer value) {
            addCriterion("recount <=", value, "recount");
            return (Criteria) this;
        }

        public Criteria andRecountIn(List<Integer> values) {
            addCriterion("recount in", values, "recount");
            return (Criteria) this;
        }

        public Criteria andRecountNotIn(List<Integer> values) {
            addCriterion("recount not in", values, "recount");
            return (Criteria) this;
        }

        public Criteria andRecountBetween(Integer value1, Integer value2) {
            addCriterion("recount between", value1, value2, "recount");
            return (Criteria) this;
        }

        public Criteria andRecountNotBetween(Integer value1, Integer value2) {
            addCriterion("recount not between", value1, value2, "recount");
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