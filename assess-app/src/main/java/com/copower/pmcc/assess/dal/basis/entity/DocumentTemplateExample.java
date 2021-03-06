package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DocumentTemplateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DocumentTemplateExample() {
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

        public Criteria andTemplateNameIsNull() {
            addCriterion("template_name is null");
            return (Criteria) this;
        }

        public Criteria andTemplateNameIsNotNull() {
            addCriterion("template_name is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateNameEqualTo(String value) {
            addCriterion("template_name =", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotEqualTo(String value) {
            addCriterion("template_name <>", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameGreaterThan(String value) {
            addCriterion("template_name >", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameGreaterThanOrEqualTo(String value) {
            addCriterion("template_name >=", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLessThan(String value) {
            addCriterion("template_name <", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLessThanOrEqualTo(String value) {
            addCriterion("template_name <=", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLike(String value) {
            addCriterion("template_name like", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotLike(String value) {
            addCriterion("template_name not like", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameIn(List<String> values) {
            addCriterion("template_name in", values, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotIn(List<String> values) {
            addCriterion("template_name not in", values, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameBetween(String value1, String value2) {
            addCriterion("template_name between", value1, value2, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotBetween(String value1, String value2) {
            addCriterion("template_name not between", value1, value2, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeIsNull() {
            addCriterion("template_type is null");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeIsNotNull() {
            addCriterion("template_type is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeEqualTo(Integer value) {
            addCriterion("template_type =", value, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeNotEqualTo(Integer value) {
            addCriterion("template_type <>", value, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeGreaterThan(Integer value) {
            addCriterion("template_type >", value, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("template_type >=", value, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeLessThan(Integer value) {
            addCriterion("template_type <", value, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeLessThanOrEqualTo(Integer value) {
            addCriterion("template_type <=", value, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeIn(List<Integer> values) {
            addCriterion("template_type in", values, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeNotIn(List<Integer> values) {
            addCriterion("template_type not in", values, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeBetween(Integer value1, Integer value2) {
            addCriterion("template_type between", value1, value2, "templateType");
            return (Criteria) this;
        }

        public Criteria andTemplateTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("template_type not between", value1, value2, "templateType");
            return (Criteria) this;
        }

        public Criteria andProvideDateIsNull() {
            addCriterion("provide_date is null");
            return (Criteria) this;
        }

        public Criteria andProvideDateIsNotNull() {
            addCriterion("provide_date is not null");
            return (Criteria) this;
        }

        public Criteria andProvideDateEqualTo(Date value) {
            addCriterion("provide_date =", value, "provideDate");
            return (Criteria) this;
        }

        public Criteria andProvideDateNotEqualTo(Date value) {
            addCriterion("provide_date <>", value, "provideDate");
            return (Criteria) this;
        }

        public Criteria andProvideDateGreaterThan(Date value) {
            addCriterion("provide_date >", value, "provideDate");
            return (Criteria) this;
        }

        public Criteria andProvideDateGreaterThanOrEqualTo(Date value) {
            addCriterion("provide_date >=", value, "provideDate");
            return (Criteria) this;
        }

        public Criteria andProvideDateLessThan(Date value) {
            addCriterion("provide_date <", value, "provideDate");
            return (Criteria) this;
        }

        public Criteria andProvideDateLessThanOrEqualTo(Date value) {
            addCriterion("provide_date <=", value, "provideDate");
            return (Criteria) this;
        }

        public Criteria andProvideDateIn(List<Date> values) {
            addCriterion("provide_date in", values, "provideDate");
            return (Criteria) this;
        }

        public Criteria andProvideDateNotIn(List<Date> values) {
            addCriterion("provide_date not in", values, "provideDate");
            return (Criteria) this;
        }

        public Criteria andProvideDateBetween(Date value1, Date value2) {
            addCriterion("provide_date between", value1, value2, "provideDate");
            return (Criteria) this;
        }

        public Criteria andProvideDateNotBetween(Date value1, Date value2) {
            addCriterion("provide_date not between", value1, value2, "provideDate");
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

        public Criteria andAssessProjectTypeIsNull() {
            addCriterion("assess_project_type is null");
            return (Criteria) this;
        }

        public Criteria andAssessProjectTypeIsNotNull() {
            addCriterion("assess_project_type is not null");
            return (Criteria) this;
        }

        public Criteria andAssessProjectTypeEqualTo(String value) {
            addCriterion("assess_project_type =", value, "assessProjectType");
            return (Criteria) this;
        }

        public Criteria andAssessProjectTypeNotEqualTo(String value) {
            addCriterion("assess_project_type <>", value, "assessProjectType");
            return (Criteria) this;
        }

        public Criteria andAssessProjectTypeGreaterThan(String value) {
            addCriterion("assess_project_type >", value, "assessProjectType");
            return (Criteria) this;
        }

        public Criteria andAssessProjectTypeGreaterThanOrEqualTo(String value) {
            addCriterion("assess_project_type >=", value, "assessProjectType");
            return (Criteria) this;
        }

        public Criteria andAssessProjectTypeLessThan(String value) {
            addCriterion("assess_project_type <", value, "assessProjectType");
            return (Criteria) this;
        }

        public Criteria andAssessProjectTypeLessThanOrEqualTo(String value) {
            addCriterion("assess_project_type <=", value, "assessProjectType");
            return (Criteria) this;
        }

        public Criteria andAssessProjectTypeLike(String value) {
            addCriterion("assess_project_type like", value, "assessProjectType");
            return (Criteria) this;
        }

        public Criteria andAssessProjectTypeNotLike(String value) {
            addCriterion("assess_project_type not like", value, "assessProjectType");
            return (Criteria) this;
        }

        public Criteria andAssessProjectTypeIn(List<String> values) {
            addCriterion("assess_project_type in", values, "assessProjectType");
            return (Criteria) this;
        }

        public Criteria andAssessProjectTypeNotIn(List<String> values) {
            addCriterion("assess_project_type not in", values, "assessProjectType");
            return (Criteria) this;
        }

        public Criteria andAssessProjectTypeBetween(String value1, String value2) {
            addCriterion("assess_project_type between", value1, value2, "assessProjectType");
            return (Criteria) this;
        }

        public Criteria andAssessProjectTypeNotBetween(String value1, String value2) {
            addCriterion("assess_project_type not between", value1, value2, "assessProjectType");
            return (Criteria) this;
        }

        public Criteria andNumbetRuleIdIsNull() {
            addCriterion("numbet_rule_id is null");
            return (Criteria) this;
        }

        public Criteria andNumbetRuleIdIsNotNull() {
            addCriterion("numbet_rule_id is not null");
            return (Criteria) this;
        }

        public Criteria andNumbetRuleIdEqualTo(Integer value) {
            addCriterion("numbet_rule_id =", value, "numbetRuleId");
            return (Criteria) this;
        }

        public Criteria andNumbetRuleIdNotEqualTo(Integer value) {
            addCriterion("numbet_rule_id <>", value, "numbetRuleId");
            return (Criteria) this;
        }

        public Criteria andNumbetRuleIdGreaterThan(Integer value) {
            addCriterion("numbet_rule_id >", value, "numbetRuleId");
            return (Criteria) this;
        }

        public Criteria andNumbetRuleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("numbet_rule_id >=", value, "numbetRuleId");
            return (Criteria) this;
        }

        public Criteria andNumbetRuleIdLessThan(Integer value) {
            addCriterion("numbet_rule_id <", value, "numbetRuleId");
            return (Criteria) this;
        }

        public Criteria andNumbetRuleIdLessThanOrEqualTo(Integer value) {
            addCriterion("numbet_rule_id <=", value, "numbetRuleId");
            return (Criteria) this;
        }

        public Criteria andNumbetRuleIdIn(List<Integer> values) {
            addCriterion("numbet_rule_id in", values, "numbetRuleId");
            return (Criteria) this;
        }

        public Criteria andNumbetRuleIdNotIn(List<Integer> values) {
            addCriterion("numbet_rule_id not in", values, "numbetRuleId");
            return (Criteria) this;
        }

        public Criteria andNumbetRuleIdBetween(Integer value1, Integer value2) {
            addCriterion("numbet_rule_id between", value1, value2, "numbetRuleId");
            return (Criteria) this;
        }

        public Criteria andNumbetRuleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("numbet_rule_id not between", value1, value2, "numbetRuleId");
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