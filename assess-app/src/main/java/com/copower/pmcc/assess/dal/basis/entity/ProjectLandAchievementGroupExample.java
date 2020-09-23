package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectLandAchievementGroupExample {
    /**
     * tb_project_land_achievement_group
     */
    protected String orderByClause;

    /**
     * tb_project_land_achievement_group
     */
    protected boolean distinct;

    /**
     * tb_project_land_achievement_group
     */
    protected List<Criteria> oredCriteria;

    public ProjectLandAchievementGroupExample() {
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
     * tb_project_land_achievement_group
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

        public Criteria andDataTableNameIsNull() {
            addCriterion("data_table_name is null");
            return (Criteria) this;
        }

        public Criteria andDataTableNameIsNotNull() {
            addCriterion("data_table_name is not null");
            return (Criteria) this;
        }

        public Criteria andDataTableNameEqualTo(String value) {
            addCriterion("data_table_name =", value, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameNotEqualTo(String value) {
            addCriterion("data_table_name <>", value, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameGreaterThan(String value) {
            addCriterion("data_table_name >", value, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("data_table_name >=", value, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameLessThan(String value) {
            addCriterion("data_table_name <", value, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameLessThanOrEqualTo(String value) {
            addCriterion("data_table_name <=", value, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameLike(String value) {
            addCriterion("data_table_name like", value, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameNotLike(String value) {
            addCriterion("data_table_name not like", value, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameIn(List<String> values) {
            addCriterion("data_table_name in", values, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameNotIn(List<String> values) {
            addCriterion("data_table_name not in", values, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameBetween(String value1, String value2) {
            addCriterion("data_table_name between", value1, value2, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableNameNotBetween(String value1, String value2) {
            addCriterion("data_table_name not between", value1, value2, "dataTableName");
            return (Criteria) this;
        }

        public Criteria andDataTableIdIsNull() {
            addCriterion("data_table_id is null");
            return (Criteria) this;
        }

        public Criteria andDataTableIdIsNotNull() {
            addCriterion("data_table_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataTableIdEqualTo(Integer value) {
            addCriterion("data_table_id =", value, "dataTableId");
            return (Criteria) this;
        }

        public Criteria andDataTableIdNotEqualTo(Integer value) {
            addCriterion("data_table_id <>", value, "dataTableId");
            return (Criteria) this;
        }

        public Criteria andDataTableIdGreaterThan(Integer value) {
            addCriterion("data_table_id >", value, "dataTableId");
            return (Criteria) this;
        }

        public Criteria andDataTableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_table_id >=", value, "dataTableId");
            return (Criteria) this;
        }

        public Criteria andDataTableIdLessThan(Integer value) {
            addCriterion("data_table_id <", value, "dataTableId");
            return (Criteria) this;
        }

        public Criteria andDataTableIdLessThanOrEqualTo(Integer value) {
            addCriterion("data_table_id <=", value, "dataTableId");
            return (Criteria) this;
        }

        public Criteria andDataTableIdIn(List<Integer> values) {
            addCriterion("data_table_id in", values, "dataTableId");
            return (Criteria) this;
        }

        public Criteria andDataTableIdNotIn(List<Integer> values) {
            addCriterion("data_table_id not in", values, "dataTableId");
            return (Criteria) this;
        }

        public Criteria andDataTableIdBetween(Integer value1, Integer value2) {
            addCriterion("data_table_id between", value1, value2, "dataTableId");
            return (Criteria) this;
        }

        public Criteria andDataTableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("data_table_id not between", value1, value2, "dataTableId");
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

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andClassificationIsNull() {
            addCriterion("classification is null");
            return (Criteria) this;
        }

        public Criteria andClassificationIsNotNull() {
            addCriterion("classification is not null");
            return (Criteria) this;
        }

        public Criteria andClassificationEqualTo(String value) {
            addCriterion("classification =", value, "classification");
            return (Criteria) this;
        }

        public Criteria andClassificationNotEqualTo(String value) {
            addCriterion("classification <>", value, "classification");
            return (Criteria) this;
        }

        public Criteria andClassificationGreaterThan(String value) {
            addCriterion("classification >", value, "classification");
            return (Criteria) this;
        }

        public Criteria andClassificationGreaterThanOrEqualTo(String value) {
            addCriterion("classification >=", value, "classification");
            return (Criteria) this;
        }

        public Criteria andClassificationLessThan(String value) {
            addCriterion("classification <", value, "classification");
            return (Criteria) this;
        }

        public Criteria andClassificationLessThanOrEqualTo(String value) {
            addCriterion("classification <=", value, "classification");
            return (Criteria) this;
        }

        public Criteria andClassificationLike(String value) {
            addCriterion("classification like", value, "classification");
            return (Criteria) this;
        }

        public Criteria andClassificationNotLike(String value) {
            addCriterion("classification not like", value, "classification");
            return (Criteria) this;
        }

        public Criteria andClassificationIn(List<String> values) {
            addCriterion("classification in", values, "classification");
            return (Criteria) this;
        }

        public Criteria andClassificationNotIn(List<String> values) {
            addCriterion("classification not in", values, "classification");
            return (Criteria) this;
        }

        public Criteria andClassificationBetween(String value1, String value2) {
            addCriterion("classification between", value1, value2, "classification");
            return (Criteria) this;
        }

        public Criteria andClassificationNotBetween(String value1, String value2) {
            addCriterion("classification not between", value1, value2, "classification");
            return (Criteria) this;
        }

        public Criteria andSelectIdIsNull() {
            addCriterion("select_id is null");
            return (Criteria) this;
        }

        public Criteria andSelectIdIsNotNull() {
            addCriterion("select_id is not null");
            return (Criteria) this;
        }

        public Criteria andSelectIdEqualTo(Integer value) {
            addCriterion("select_id =", value, "selectId");
            return (Criteria) this;
        }

        public Criteria andSelectIdNotEqualTo(Integer value) {
            addCriterion("select_id <>", value, "selectId");
            return (Criteria) this;
        }

        public Criteria andSelectIdGreaterThan(Integer value) {
            addCriterion("select_id >", value, "selectId");
            return (Criteria) this;
        }

        public Criteria andSelectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("select_id >=", value, "selectId");
            return (Criteria) this;
        }

        public Criteria andSelectIdLessThan(Integer value) {
            addCriterion("select_id <", value, "selectId");
            return (Criteria) this;
        }

        public Criteria andSelectIdLessThanOrEqualTo(Integer value) {
            addCriterion("select_id <=", value, "selectId");
            return (Criteria) this;
        }

        public Criteria andSelectIdIn(List<Integer> values) {
            addCriterion("select_id in", values, "selectId");
            return (Criteria) this;
        }

        public Criteria andSelectIdNotIn(List<Integer> values) {
            addCriterion("select_id not in", values, "selectId");
            return (Criteria) this;
        }

        public Criteria andSelectIdBetween(Integer value1, Integer value2) {
            addCriterion("select_id between", value1, value2, "selectId");
            return (Criteria) this;
        }

        public Criteria andSelectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("select_id not between", value1, value2, "selectId");
            return (Criteria) this;
        }

        public Criteria andSelectValueIsNull() {
            addCriterion("select_value is null");
            return (Criteria) this;
        }

        public Criteria andSelectValueIsNotNull() {
            addCriterion("select_value is not null");
            return (Criteria) this;
        }

        public Criteria andSelectValueEqualTo(String value) {
            addCriterion("select_value =", value, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueNotEqualTo(String value) {
            addCriterion("select_value <>", value, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueGreaterThan(String value) {
            addCriterion("select_value >", value, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueGreaterThanOrEqualTo(String value) {
            addCriterion("select_value >=", value, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueLessThan(String value) {
            addCriterion("select_value <", value, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueLessThanOrEqualTo(String value) {
            addCriterion("select_value <=", value, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueLike(String value) {
            addCriterion("select_value like", value, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueNotLike(String value) {
            addCriterion("select_value not like", value, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueIn(List<String> values) {
            addCriterion("select_value in", values, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueNotIn(List<String> values) {
            addCriterion("select_value not in", values, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueBetween(String value1, String value2) {
            addCriterion("select_value between", value1, value2, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueNotBetween(String value1, String value2) {
            addCriterion("select_value not between", value1, value2, "selectValue");
            return (Criteria) this;
        }

        public Criteria andAchievementIdsIsNull() {
            addCriterion("achievement_ids is null");
            return (Criteria) this;
        }

        public Criteria andAchievementIdsIsNotNull() {
            addCriterion("achievement_ids is not null");
            return (Criteria) this;
        }

        public Criteria andAchievementIdsEqualTo(String value) {
            addCriterion("achievement_ids =", value, "achievementIds");
            return (Criteria) this;
        }

        public Criteria andAchievementIdsNotEqualTo(String value) {
            addCriterion("achievement_ids <>", value, "achievementIds");
            return (Criteria) this;
        }

        public Criteria andAchievementIdsGreaterThan(String value) {
            addCriterion("achievement_ids >", value, "achievementIds");
            return (Criteria) this;
        }

        public Criteria andAchievementIdsGreaterThanOrEqualTo(String value) {
            addCriterion("achievement_ids >=", value, "achievementIds");
            return (Criteria) this;
        }

        public Criteria andAchievementIdsLessThan(String value) {
            addCriterion("achievement_ids <", value, "achievementIds");
            return (Criteria) this;
        }

        public Criteria andAchievementIdsLessThanOrEqualTo(String value) {
            addCriterion("achievement_ids <=", value, "achievementIds");
            return (Criteria) this;
        }

        public Criteria andAchievementIdsLike(String value) {
            addCriterion("achievement_ids like", value, "achievementIds");
            return (Criteria) this;
        }

        public Criteria andAchievementIdsNotLike(String value) {
            addCriterion("achievement_ids not like", value, "achievementIds");
            return (Criteria) this;
        }

        public Criteria andAchievementIdsIn(List<String> values) {
            addCriterion("achievement_ids in", values, "achievementIds");
            return (Criteria) this;
        }

        public Criteria andAchievementIdsNotIn(List<String> values) {
            addCriterion("achievement_ids not in", values, "achievementIds");
            return (Criteria) this;
        }

        public Criteria andAchievementIdsBetween(String value1, String value2) {
            addCriterion("achievement_ids between", value1, value2, "achievementIds");
            return (Criteria) this;
        }

        public Criteria andAchievementIdsNotBetween(String value1, String value2) {
            addCriterion("achievement_ids not between", value1, value2, "achievementIds");
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
     * tb_project_land_achievement_group
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