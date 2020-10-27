package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScriptTemplateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ScriptTemplateExample() {
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

        public Criteria andTemplateKeyIsNull() {
            addCriterion("template_key is null");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyIsNotNull() {
            addCriterion("template_key is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyEqualTo(String value) {
            addCriterion("template_key =", value, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyNotEqualTo(String value) {
            addCriterion("template_key <>", value, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyGreaterThan(String value) {
            addCriterion("template_key >", value, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyGreaterThanOrEqualTo(String value) {
            addCriterion("template_key >=", value, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyLessThan(String value) {
            addCriterion("template_key <", value, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyLessThanOrEqualTo(String value) {
            addCriterion("template_key <=", value, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyLike(String value) {
            addCriterion("template_key like", value, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyNotLike(String value) {
            addCriterion("template_key not like", value, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyIn(List<String> values) {
            addCriterion("template_key in", values, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyNotIn(List<String> values) {
            addCriterion("template_key not in", values, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyBetween(String value1, String value2) {
            addCriterion("template_key between", value1, value2, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateKeyNotBetween(String value1, String value2) {
            addCriterion("template_key not between", value1, value2, "templateKey");
            return (Criteria) this;
        }

        public Criteria andTemplateOriginalTextIsNull() {
            addCriterion("template_original_text is null");
            return (Criteria) this;
        }

        public Criteria andTemplateOriginalTextIsNotNull() {
            addCriterion("template_original_text is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateOriginalTextEqualTo(String value) {
            addCriterion("template_original_text =", value, "templateOriginalText");
            return (Criteria) this;
        }

        public Criteria andTemplateOriginalTextNotEqualTo(String value) {
            addCriterion("template_original_text <>", value, "templateOriginalText");
            return (Criteria) this;
        }

        public Criteria andTemplateOriginalTextGreaterThan(String value) {
            addCriterion("template_original_text >", value, "templateOriginalText");
            return (Criteria) this;
        }

        public Criteria andTemplateOriginalTextGreaterThanOrEqualTo(String value) {
            addCriterion("template_original_text >=", value, "templateOriginalText");
            return (Criteria) this;
        }

        public Criteria andTemplateOriginalTextLessThan(String value) {
            addCriterion("template_original_text <", value, "templateOriginalText");
            return (Criteria) this;
        }

        public Criteria andTemplateOriginalTextLessThanOrEqualTo(String value) {
            addCriterion("template_original_text <=", value, "templateOriginalText");
            return (Criteria) this;
        }

        public Criteria andTemplateOriginalTextLike(String value) {
            addCriterion("template_original_text like", value, "templateOriginalText");
            return (Criteria) this;
        }

        public Criteria andTemplateOriginalTextNotLike(String value) {
            addCriterion("template_original_text not like", value, "templateOriginalText");
            return (Criteria) this;
        }

        public Criteria andTemplateOriginalTextIn(List<String> values) {
            addCriterion("template_original_text in", values, "templateOriginalText");
            return (Criteria) this;
        }

        public Criteria andTemplateOriginalTextNotIn(List<String> values) {
            addCriterion("template_original_text not in", values, "templateOriginalText");
            return (Criteria) this;
        }

        public Criteria andTemplateOriginalTextBetween(String value1, String value2) {
            addCriterion("template_original_text between", value1, value2, "templateOriginalText");
            return (Criteria) this;
        }

        public Criteria andTemplateOriginalTextNotBetween(String value1, String value2) {
            addCriterion("template_original_text not between", value1, value2, "templateOriginalText");
            return (Criteria) this;
        }

        public Criteria andScriptTemplateIsNull() {
            addCriterion("script_template is null");
            return (Criteria) this;
        }

        public Criteria andScriptTemplateIsNotNull() {
            addCriterion("script_template is not null");
            return (Criteria) this;
        }

        public Criteria andScriptTemplateEqualTo(String value) {
            addCriterion("script_template =", value, "scriptTemplate");
            return (Criteria) this;
        }

        public Criteria andScriptTemplateNotEqualTo(String value) {
            addCriterion("script_template <>", value, "scriptTemplate");
            return (Criteria) this;
        }

        public Criteria andScriptTemplateGreaterThan(String value) {
            addCriterion("script_template >", value, "scriptTemplate");
            return (Criteria) this;
        }

        public Criteria andScriptTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("script_template >=", value, "scriptTemplate");
            return (Criteria) this;
        }

        public Criteria andScriptTemplateLessThan(String value) {
            addCriterion("script_template <", value, "scriptTemplate");
            return (Criteria) this;
        }

        public Criteria andScriptTemplateLessThanOrEqualTo(String value) {
            addCriterion("script_template <=", value, "scriptTemplate");
            return (Criteria) this;
        }

        public Criteria andScriptTemplateLike(String value) {
            addCriterion("script_template like", value, "scriptTemplate");
            return (Criteria) this;
        }

        public Criteria andScriptTemplateNotLike(String value) {
            addCriterion("script_template not like", value, "scriptTemplate");
            return (Criteria) this;
        }

        public Criteria andScriptTemplateIn(List<String> values) {
            addCriterion("script_template in", values, "scriptTemplate");
            return (Criteria) this;
        }

        public Criteria andScriptTemplateNotIn(List<String> values) {
            addCriterion("script_template not in", values, "scriptTemplate");
            return (Criteria) this;
        }

        public Criteria andScriptTemplateBetween(String value1, String value2) {
            addCriterion("script_template between", value1, value2, "scriptTemplate");
            return (Criteria) this;
        }

        public Criteria andScriptTemplateNotBetween(String value1, String value2) {
            addCriterion("script_template not between", value1, value2, "scriptTemplate");
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