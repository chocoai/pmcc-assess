package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdMarketCompareItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MdMarketCompareItemExample() {
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

        public Criteria andMcIdIsNull() {
            addCriterion("mc_id is null");
            return (Criteria) this;
        }

        public Criteria andMcIdIsNotNull() {
            addCriterion("mc_id is not null");
            return (Criteria) this;
        }

        public Criteria andMcIdEqualTo(Integer value) {
            addCriterion("mc_id =", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdNotEqualTo(Integer value) {
            addCriterion("mc_id <>", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdGreaterThan(Integer value) {
            addCriterion("mc_id >", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mc_id >=", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdLessThan(Integer value) {
            addCriterion("mc_id <", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdLessThanOrEqualTo(Integer value) {
            addCriterion("mc_id <=", value, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdIn(List<Integer> values) {
            addCriterion("mc_id in", values, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdNotIn(List<Integer> values) {
            addCriterion("mc_id not in", values, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdBetween(Integer value1, Integer value2) {
            addCriterion("mc_id between", value1, value2, "mcId");
            return (Criteria) this;
        }

        public Criteria andMcIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mc_id not between", value1, value2, "mcId");
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdIsNull() {
            addCriterion("plan_details_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdIsNotNull() {
            addCriterion("plan_details_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdEqualTo(Integer value) {
            addCriterion("plan_details_id =", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdNotEqualTo(Integer value) {
            addCriterion("plan_details_id <>", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdGreaterThan(Integer value) {
            addCriterion("plan_details_id >", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_details_id >=", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdLessThan(Integer value) {
            addCriterion("plan_details_id <", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdLessThanOrEqualTo(Integer value) {
            addCriterion("plan_details_id <=", value, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdIn(List<Integer> values) {
            addCriterion("plan_details_id in", values, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdNotIn(List<Integer> values) {
            addCriterion("plan_details_id not in", values, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdBetween(Integer value1, Integer value2) {
            addCriterion("plan_details_id between", value1, value2, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andPlanDetailsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_details_id not between", value1, value2, "planDetailsId");
            return (Criteria) this;
        }

        public Criteria andBasicApplyIdIsNull() {
            addCriterion("basic_apply_id is null");
            return (Criteria) this;
        }

        public Criteria andBasicApplyIdIsNotNull() {
            addCriterion("basic_apply_id is not null");
            return (Criteria) this;
        }

        public Criteria andBasicApplyIdEqualTo(Integer value) {
            addCriterion("basic_apply_id =", value, "basicApplyId");
            return (Criteria) this;
        }

        public Criteria andBasicApplyIdNotEqualTo(Integer value) {
            addCriterion("basic_apply_id <>", value, "basicApplyId");
            return (Criteria) this;
        }

        public Criteria andBasicApplyIdGreaterThan(Integer value) {
            addCriterion("basic_apply_id >", value, "basicApplyId");
            return (Criteria) this;
        }

        public Criteria andBasicApplyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("basic_apply_id >=", value, "basicApplyId");
            return (Criteria) this;
        }

        public Criteria andBasicApplyIdLessThan(Integer value) {
            addCriterion("basic_apply_id <", value, "basicApplyId");
            return (Criteria) this;
        }

        public Criteria andBasicApplyIdLessThanOrEqualTo(Integer value) {
            addCriterion("basic_apply_id <=", value, "basicApplyId");
            return (Criteria) this;
        }

        public Criteria andBasicApplyIdIn(List<Integer> values) {
            addCriterion("basic_apply_id in", values, "basicApplyId");
            return (Criteria) this;
        }

        public Criteria andBasicApplyIdNotIn(List<Integer> values) {
            addCriterion("basic_apply_id not in", values, "basicApplyId");
            return (Criteria) this;
        }

        public Criteria andBasicApplyIdBetween(Integer value1, Integer value2) {
            addCriterion("basic_apply_id between", value1, value2, "basicApplyId");
            return (Criteria) this;
        }

        public Criteria andBasicApplyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("basic_apply_id not between", value1, value2, "basicApplyId");
            return (Criteria) this;
        }

        public Criteria andJsonContentIsNull() {
            addCriterion("json_content is null");
            return (Criteria) this;
        }

        public Criteria andJsonContentIsNotNull() {
            addCriterion("json_content is not null");
            return (Criteria) this;
        }

        public Criteria andJsonContentEqualTo(String value) {
            addCriterion("json_content =", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentNotEqualTo(String value) {
            addCriterion("json_content <>", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentGreaterThan(String value) {
            addCriterion("json_content >", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentGreaterThanOrEqualTo(String value) {
            addCriterion("json_content >=", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentLessThan(String value) {
            addCriterion("json_content <", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentLessThanOrEqualTo(String value) {
            addCriterion("json_content <=", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentLike(String value) {
            addCriterion("json_content like", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentNotLike(String value) {
            addCriterion("json_content not like", value, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentIn(List<String> values) {
            addCriterion("json_content in", values, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentNotIn(List<String> values) {
            addCriterion("json_content not in", values, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentBetween(String value1, String value2) {
            addCriterion("json_content between", value1, value2, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andJsonContentNotBetween(String value1, String value2) {
            addCriterion("json_content not between", value1, value2, "jsonContent");
            return (Criteria) this;
        }

        public Criteria andTradingTimeExplainIsNull() {
            addCriterion("trading_time_explain is null");
            return (Criteria) this;
        }

        public Criteria andTradingTimeExplainIsNotNull() {
            addCriterion("trading_time_explain is not null");
            return (Criteria) this;
        }

        public Criteria andTradingTimeExplainEqualTo(String value) {
            addCriterion("trading_time_explain =", value, "tradingTimeExplain");
            return (Criteria) this;
        }

        public Criteria andTradingTimeExplainNotEqualTo(String value) {
            addCriterion("trading_time_explain <>", value, "tradingTimeExplain");
            return (Criteria) this;
        }

        public Criteria andTradingTimeExplainGreaterThan(String value) {
            addCriterion("trading_time_explain >", value, "tradingTimeExplain");
            return (Criteria) this;
        }

        public Criteria andTradingTimeExplainGreaterThanOrEqualTo(String value) {
            addCriterion("trading_time_explain >=", value, "tradingTimeExplain");
            return (Criteria) this;
        }

        public Criteria andTradingTimeExplainLessThan(String value) {
            addCriterion("trading_time_explain <", value, "tradingTimeExplain");
            return (Criteria) this;
        }

        public Criteria andTradingTimeExplainLessThanOrEqualTo(String value) {
            addCriterion("trading_time_explain <=", value, "tradingTimeExplain");
            return (Criteria) this;
        }

        public Criteria andTradingTimeExplainLike(String value) {
            addCriterion("trading_time_explain like", value, "tradingTimeExplain");
            return (Criteria) this;
        }

        public Criteria andTradingTimeExplainNotLike(String value) {
            addCriterion("trading_time_explain not like", value, "tradingTimeExplain");
            return (Criteria) this;
        }

        public Criteria andTradingTimeExplainIn(List<String> values) {
            addCriterion("trading_time_explain in", values, "tradingTimeExplain");
            return (Criteria) this;
        }

        public Criteria andTradingTimeExplainNotIn(List<String> values) {
            addCriterion("trading_time_explain not in", values, "tradingTimeExplain");
            return (Criteria) this;
        }

        public Criteria andTradingTimeExplainBetween(String value1, String value2) {
            addCriterion("trading_time_explain between", value1, value2, "tradingTimeExplain");
            return (Criteria) this;
        }

        public Criteria andTradingTimeExplainNotBetween(String value1, String value2) {
            addCriterion("trading_time_explain not between", value1, value2, "tradingTimeExplain");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdIsNull() {
            addCriterion("residue_ratio_id is null");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdIsNotNull() {
            addCriterion("residue_ratio_id is not null");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdEqualTo(Integer value) {
            addCriterion("residue_ratio_id =", value, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdNotEqualTo(Integer value) {
            addCriterion("residue_ratio_id <>", value, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdGreaterThan(Integer value) {
            addCriterion("residue_ratio_id >", value, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("residue_ratio_id >=", value, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdLessThan(Integer value) {
            addCriterion("residue_ratio_id <", value, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdLessThanOrEqualTo(Integer value) {
            addCriterion("residue_ratio_id <=", value, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdIn(List<Integer> values) {
            addCriterion("residue_ratio_id in", values, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdNotIn(List<Integer> values) {
            addCriterion("residue_ratio_id not in", values, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdBetween(Integer value1, Integer value2) {
            addCriterion("residue_ratio_id between", value1, value2, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andResidueRatioIdNotBetween(Integer value1, Integer value2) {
            addCriterion("residue_ratio_id not between", value1, value2, "residueRatioId");
            return (Criteria) this;
        }

        public Criteria andUsedYearIsNull() {
            addCriterion("used_year is null");
            return (Criteria) this;
        }

        public Criteria andUsedYearIsNotNull() {
            addCriterion("used_year is not null");
            return (Criteria) this;
        }

        public Criteria andUsedYearEqualTo(Integer value) {
            addCriterion("used_year =", value, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsedYearNotEqualTo(Integer value) {
            addCriterion("used_year <>", value, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsedYearGreaterThan(Integer value) {
            addCriterion("used_year >", value, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsedYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("used_year >=", value, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsedYearLessThan(Integer value) {
            addCriterion("used_year <", value, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsedYearLessThanOrEqualTo(Integer value) {
            addCriterion("used_year <=", value, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsedYearIn(List<Integer> values) {
            addCriterion("used_year in", values, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsedYearNotIn(List<Integer> values) {
            addCriterion("used_year not in", values, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsedYearBetween(Integer value1, Integer value2) {
            addCriterion("used_year between", value1, value2, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsedYearNotBetween(Integer value1, Integer value2) {
            addCriterion("used_year not between", value1, value2, "usedYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearIsNull() {
            addCriterion("usable_year is null");
            return (Criteria) this;
        }

        public Criteria andUsableYearIsNotNull() {
            addCriterion("usable_year is not null");
            return (Criteria) this;
        }

        public Criteria andUsableYearEqualTo(Integer value) {
            addCriterion("usable_year =", value, "usableYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearNotEqualTo(Integer value) {
            addCriterion("usable_year <>", value, "usableYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearGreaterThan(Integer value) {
            addCriterion("usable_year >", value, "usableYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("usable_year >=", value, "usableYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearLessThan(Integer value) {
            addCriterion("usable_year <", value, "usableYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearLessThanOrEqualTo(Integer value) {
            addCriterion("usable_year <=", value, "usableYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearIn(List<Integer> values) {
            addCriterion("usable_year in", values, "usableYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearNotIn(List<Integer> values) {
            addCriterion("usable_year not in", values, "usableYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearBetween(Integer value1, Integer value2) {
            addCriterion("usable_year between", value1, value2, "usableYear");
            return (Criteria) this;
        }

        public Criteria andUsableYearNotBetween(Integer value1, Integer value2) {
            addCriterion("usable_year not between", value1, value2, "usableYear");
            return (Criteria) this;
        }

        public Criteria andHouseIdIsNull() {
            addCriterion("house_id is null");
            return (Criteria) this;
        }

        public Criteria andHouseIdIsNotNull() {
            addCriterion("house_id is not null");
            return (Criteria) this;
        }

        public Criteria andHouseIdEqualTo(Integer value) {
            addCriterion("house_id =", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotEqualTo(Integer value) {
            addCriterion("house_id <>", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdGreaterThan(Integer value) {
            addCriterion("house_id >", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("house_id >=", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdLessThan(Integer value) {
            addCriterion("house_id <", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("house_id <=", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdIn(List<Integer> values) {
            addCriterion("house_id in", values, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotIn(List<Integer> values) {
            addCriterion("house_id not in", values, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdBetween(Integer value1, Integer value2) {
            addCriterion("house_id between", value1, value2, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("house_id not between", value1, value2, "houseId");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(BigDecimal value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(BigDecimal value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(BigDecimal value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(BigDecimal value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<BigDecimal> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<BigDecimal> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andInitialPriceIsNull() {
            addCriterion("initial_price is null");
            return (Criteria) this;
        }

        public Criteria andInitialPriceIsNotNull() {
            addCriterion("initial_price is not null");
            return (Criteria) this;
        }

        public Criteria andInitialPriceEqualTo(BigDecimal value) {
            addCriterion("initial_price =", value, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andInitialPriceNotEqualTo(BigDecimal value) {
            addCriterion("initial_price <>", value, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andInitialPriceGreaterThan(BigDecimal value) {
            addCriterion("initial_price >", value, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andInitialPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("initial_price >=", value, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andInitialPriceLessThan(BigDecimal value) {
            addCriterion("initial_price <", value, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andInitialPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("initial_price <=", value, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andInitialPriceIn(List<BigDecimal> values) {
            addCriterion("initial_price in", values, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andInitialPriceNotIn(List<BigDecimal> values) {
            addCriterion("initial_price not in", values, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andInitialPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("initial_price between", value1, value2, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andInitialPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("initial_price not between", value1, value2, "initialPrice");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationIsNull() {
            addCriterion("price_connotation is null");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationIsNotNull() {
            addCriterion("price_connotation is not null");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationEqualTo(String value) {
            addCriterion("price_connotation =", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationNotEqualTo(String value) {
            addCriterion("price_connotation <>", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationGreaterThan(String value) {
            addCriterion("price_connotation >", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationGreaterThanOrEqualTo(String value) {
            addCriterion("price_connotation >=", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationLessThan(String value) {
            addCriterion("price_connotation <", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationLessThanOrEqualTo(String value) {
            addCriterion("price_connotation <=", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationLike(String value) {
            addCriterion("price_connotation like", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationNotLike(String value) {
            addCriterion("price_connotation not like", value, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationIn(List<String> values) {
            addCriterion("price_connotation in", values, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationNotIn(List<String> values) {
            addCriterion("price_connotation not in", values, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationBetween(String value1, String value2) {
            addCriterion("price_connotation between", value1, value2, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andPriceConnotationNotBetween(String value1, String value2) {
            addCriterion("price_connotation not between", value1, value2, "priceConnotation");
            return (Criteria) this;
        }

        public Criteria andMustAdjustPriceIsNull() {
            addCriterion("must_adjust_price is null");
            return (Criteria) this;
        }

        public Criteria andMustAdjustPriceIsNotNull() {
            addCriterion("must_adjust_price is not null");
            return (Criteria) this;
        }

        public Criteria andMustAdjustPriceEqualTo(Boolean value) {
            addCriterion("must_adjust_price =", value, "mustAdjustPrice");
            return (Criteria) this;
        }

        public Criteria andMustAdjustPriceNotEqualTo(Boolean value) {
            addCriterion("must_adjust_price <>", value, "mustAdjustPrice");
            return (Criteria) this;
        }

        public Criteria andMustAdjustPriceGreaterThan(Boolean value) {
            addCriterion("must_adjust_price >", value, "mustAdjustPrice");
            return (Criteria) this;
        }

        public Criteria andMustAdjustPriceGreaterThanOrEqualTo(Boolean value) {
            addCriterion("must_adjust_price >=", value, "mustAdjustPrice");
            return (Criteria) this;
        }

        public Criteria andMustAdjustPriceLessThan(Boolean value) {
            addCriterion("must_adjust_price <", value, "mustAdjustPrice");
            return (Criteria) this;
        }

        public Criteria andMustAdjustPriceLessThanOrEqualTo(Boolean value) {
            addCriterion("must_adjust_price <=", value, "mustAdjustPrice");
            return (Criteria) this;
        }

        public Criteria andMustAdjustPriceIn(List<Boolean> values) {
            addCriterion("must_adjust_price in", values, "mustAdjustPrice");
            return (Criteria) this;
        }

        public Criteria andMustAdjustPriceNotIn(List<Boolean> values) {
            addCriterion("must_adjust_price not in", values, "mustAdjustPrice");
            return (Criteria) this;
        }

        public Criteria andMustAdjustPriceBetween(Boolean value1, Boolean value2) {
            addCriterion("must_adjust_price between", value1, value2, "mustAdjustPrice");
            return (Criteria) this;
        }

        public Criteria andMustAdjustPriceNotBetween(Boolean value1, Boolean value2) {
            addCriterion("must_adjust_price not between", value1, value2, "mustAdjustPrice");
            return (Criteria) this;
        }

        public Criteria andAnnualCoefficientIsNull() {
            addCriterion("annual_coefficient is null");
            return (Criteria) this;
        }

        public Criteria andAnnualCoefficientIsNotNull() {
            addCriterion("annual_coefficient is not null");
            return (Criteria) this;
        }

        public Criteria andAnnualCoefficientEqualTo(BigDecimal value) {
            addCriterion("annual_coefficient =", value, "annualCoefficient");
            return (Criteria) this;
        }

        public Criteria andAnnualCoefficientNotEqualTo(BigDecimal value) {
            addCriterion("annual_coefficient <>", value, "annualCoefficient");
            return (Criteria) this;
        }

        public Criteria andAnnualCoefficientGreaterThan(BigDecimal value) {
            addCriterion("annual_coefficient >", value, "annualCoefficient");
            return (Criteria) this;
        }

        public Criteria andAnnualCoefficientGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("annual_coefficient >=", value, "annualCoefficient");
            return (Criteria) this;
        }

        public Criteria andAnnualCoefficientLessThan(BigDecimal value) {
            addCriterion("annual_coefficient <", value, "annualCoefficient");
            return (Criteria) this;
        }

        public Criteria andAnnualCoefficientLessThanOrEqualTo(BigDecimal value) {
            addCriterion("annual_coefficient <=", value, "annualCoefficient");
            return (Criteria) this;
        }

        public Criteria andAnnualCoefficientIn(List<BigDecimal> values) {
            addCriterion("annual_coefficient in", values, "annualCoefficient");
            return (Criteria) this;
        }

        public Criteria andAnnualCoefficientNotIn(List<BigDecimal> values) {
            addCriterion("annual_coefficient not in", values, "annualCoefficient");
            return (Criteria) this;
        }

        public Criteria andAnnualCoefficientBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("annual_coefficient between", value1, value2, "annualCoefficient");
            return (Criteria) this;
        }

        public Criteria andAnnualCoefficientNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("annual_coefficient not between", value1, value2, "annualCoefficient");
            return (Criteria) this;
        }

        public Criteria andVolumeRatioCoefficientIsNull() {
            addCriterion("volume_ratio_coefficient is null");
            return (Criteria) this;
        }

        public Criteria andVolumeRatioCoefficientIsNotNull() {
            addCriterion("volume_ratio_coefficient is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeRatioCoefficientEqualTo(BigDecimal value) {
            addCriterion("volume_ratio_coefficient =", value, "volumeRatioCoefficient");
            return (Criteria) this;
        }

        public Criteria andVolumeRatioCoefficientNotEqualTo(BigDecimal value) {
            addCriterion("volume_ratio_coefficient <>", value, "volumeRatioCoefficient");
            return (Criteria) this;
        }

        public Criteria andVolumeRatioCoefficientGreaterThan(BigDecimal value) {
            addCriterion("volume_ratio_coefficient >", value, "volumeRatioCoefficient");
            return (Criteria) this;
        }

        public Criteria andVolumeRatioCoefficientGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("volume_ratio_coefficient >=", value, "volumeRatioCoefficient");
            return (Criteria) this;
        }

        public Criteria andVolumeRatioCoefficientLessThan(BigDecimal value) {
            addCriterion("volume_ratio_coefficient <", value, "volumeRatioCoefficient");
            return (Criteria) this;
        }

        public Criteria andVolumeRatioCoefficientLessThanOrEqualTo(BigDecimal value) {
            addCriterion("volume_ratio_coefficient <=", value, "volumeRatioCoefficient");
            return (Criteria) this;
        }

        public Criteria andVolumeRatioCoefficientIn(List<BigDecimal> values) {
            addCriterion("volume_ratio_coefficient in", values, "volumeRatioCoefficient");
            return (Criteria) this;
        }

        public Criteria andVolumeRatioCoefficientNotIn(List<BigDecimal> values) {
            addCriterion("volume_ratio_coefficient not in", values, "volumeRatioCoefficient");
            return (Criteria) this;
        }

        public Criteria andVolumeRatioCoefficientBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("volume_ratio_coefficient between", value1, value2, "volumeRatioCoefficient");
            return (Criteria) this;
        }

        public Criteria andVolumeRatioCoefficientNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("volume_ratio_coefficient not between", value1, value2, "volumeRatioCoefficient");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceIsNull() {
            addCriterion("specific_price is null");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceIsNotNull() {
            addCriterion("specific_price is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceEqualTo(String value) {
            addCriterion("specific_price =", value, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceNotEqualTo(String value) {
            addCriterion("specific_price <>", value, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceGreaterThan(String value) {
            addCriterion("specific_price >", value, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceGreaterThanOrEqualTo(String value) {
            addCriterion("specific_price >=", value, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceLessThan(String value) {
            addCriterion("specific_price <", value, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceLessThanOrEqualTo(String value) {
            addCriterion("specific_price <=", value, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceLike(String value) {
            addCriterion("specific_price like", value, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceNotLike(String value) {
            addCriterion("specific_price not like", value, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceIn(List<String> values) {
            addCriterion("specific_price in", values, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceNotIn(List<String> values) {
            addCriterion("specific_price not in", values, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceBetween(String value1, String value2) {
            addCriterion("specific_price between", value1, value2, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andSpecificPriceNotBetween(String value1, String value2) {
            addCriterion("specific_price not between", value1, value2, "specificPrice");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceIsNull() {
            addCriterion("correction_difference is null");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceIsNotNull() {
            addCriterion("correction_difference is not null");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceEqualTo(String value) {
            addCriterion("correction_difference =", value, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceNotEqualTo(String value) {
            addCriterion("correction_difference <>", value, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceGreaterThan(String value) {
            addCriterion("correction_difference >", value, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceGreaterThanOrEqualTo(String value) {
            addCriterion("correction_difference >=", value, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceLessThan(String value) {
            addCriterion("correction_difference <", value, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceLessThanOrEqualTo(String value) {
            addCriterion("correction_difference <=", value, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceLike(String value) {
            addCriterion("correction_difference like", value, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceNotLike(String value) {
            addCriterion("correction_difference not like", value, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceIn(List<String> values) {
            addCriterion("correction_difference in", values, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceNotIn(List<String> values) {
            addCriterion("correction_difference not in", values, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceBetween(String value1, String value2) {
            addCriterion("correction_difference between", value1, value2, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCorrectionDifferenceNotBetween(String value1, String value2) {
            addCriterion("correction_difference not between", value1, value2, "correctionDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceIsNull() {
            addCriterion("case_difference is null");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceIsNotNull() {
            addCriterion("case_difference is not null");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceEqualTo(String value) {
            addCriterion("case_difference =", value, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceNotEqualTo(String value) {
            addCriterion("case_difference <>", value, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceGreaterThan(String value) {
            addCriterion("case_difference >", value, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceGreaterThanOrEqualTo(String value) {
            addCriterion("case_difference >=", value, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceLessThan(String value) {
            addCriterion("case_difference <", value, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceLessThanOrEqualTo(String value) {
            addCriterion("case_difference <=", value, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceLike(String value) {
            addCriterion("case_difference like", value, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceNotLike(String value) {
            addCriterion("case_difference not like", value, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceIn(List<String> values) {
            addCriterion("case_difference in", values, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceNotIn(List<String> values) {
            addCriterion("case_difference not in", values, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceBetween(String value1, String value2) {
            addCriterion("case_difference between", value1, value2, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andCaseDifferenceNotBetween(String value1, String value2) {
            addCriterion("case_difference not between", value1, value2, "caseDifference");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(String value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(String value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(String value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(String value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(String value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(String value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLike(String value) {
            addCriterion("weight like", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotLike(String value) {
            addCriterion("weight not like", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<String> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<String> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(String value1, String value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(String value1, String value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionIsNull() {
            addCriterion("weight_description is null");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionIsNotNull() {
            addCriterion("weight_description is not null");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionEqualTo(String value) {
            addCriterion("weight_description =", value, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionNotEqualTo(String value) {
            addCriterion("weight_description <>", value, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionGreaterThan(String value) {
            addCriterion("weight_description >", value, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("weight_description >=", value, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionLessThan(String value) {
            addCriterion("weight_description <", value, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionLessThanOrEqualTo(String value) {
            addCriterion("weight_description <=", value, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionLike(String value) {
            addCriterion("weight_description like", value, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionNotLike(String value) {
            addCriterion("weight_description not like", value, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionIn(List<String> values) {
            addCriterion("weight_description in", values, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionNotIn(List<String> values) {
            addCriterion("weight_description not in", values, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionBetween(String value1, String value2) {
            addCriterion("weight_description between", value1, value2, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andWeightDescriptionNotBetween(String value1, String value2) {
            addCriterion("weight_description not between", value1, value2, "weightDescription");
            return (Criteria) this;
        }

        public Criteria andAveragePriceIsNull() {
            addCriterion("average_price is null");
            return (Criteria) this;
        }

        public Criteria andAveragePriceIsNotNull() {
            addCriterion("average_price is not null");
            return (Criteria) this;
        }

        public Criteria andAveragePriceEqualTo(BigDecimal value) {
            addCriterion("average_price =", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceNotEqualTo(BigDecimal value) {
            addCriterion("average_price <>", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceGreaterThan(BigDecimal value) {
            addCriterion("average_price >", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("average_price >=", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceLessThan(BigDecimal value) {
            addCriterion("average_price <", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("average_price <=", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceIn(List<BigDecimal> values) {
            addCriterion("average_price in", values, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceNotIn(List<BigDecimal> values) {
            addCriterion("average_price not in", values, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("average_price between", value1, value2, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("average_price not between", value1, value2, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andDeveDegreeIsNull() {
            addCriterion("deve_degree is null");
            return (Criteria) this;
        }

        public Criteria andDeveDegreeIsNotNull() {
            addCriterion("deve_degree is not null");
            return (Criteria) this;
        }

        public Criteria andDeveDegreeEqualTo(BigDecimal value) {
            addCriterion("deve_degree =", value, "deveDegree");
            return (Criteria) this;
        }

        public Criteria andDeveDegreeNotEqualTo(BigDecimal value) {
            addCriterion("deve_degree <>", value, "deveDegree");
            return (Criteria) this;
        }

        public Criteria andDeveDegreeGreaterThan(BigDecimal value) {
            addCriterion("deve_degree >", value, "deveDegree");
            return (Criteria) this;
        }

        public Criteria andDeveDegreeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deve_degree >=", value, "deveDegree");
            return (Criteria) this;
        }

        public Criteria andDeveDegreeLessThan(BigDecimal value) {
            addCriterion("deve_degree <", value, "deveDegree");
            return (Criteria) this;
        }

        public Criteria andDeveDegreeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deve_degree <=", value, "deveDegree");
            return (Criteria) this;
        }

        public Criteria andDeveDegreeIn(List<BigDecimal> values) {
            addCriterion("deve_degree in", values, "deveDegree");
            return (Criteria) this;
        }

        public Criteria andDeveDegreeNotIn(List<BigDecimal> values) {
            addCriterion("deve_degree not in", values, "deveDegree");
            return (Criteria) this;
        }

        public Criteria andDeveDegreeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deve_degree between", value1, value2, "deveDegree");
            return (Criteria) this;
        }

        public Criteria andDeveDegreeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deve_degree not between", value1, value2, "deveDegree");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceIsNull() {
            addCriterion("evaluate_price is null");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceIsNotNull() {
            addCriterion("evaluate_price is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceEqualTo(BigDecimal value) {
            addCriterion("evaluate_price =", value, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceNotEqualTo(BigDecimal value) {
            addCriterion("evaluate_price <>", value, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceGreaterThan(BigDecimal value) {
            addCriterion("evaluate_price >", value, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("evaluate_price >=", value, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceLessThan(BigDecimal value) {
            addCriterion("evaluate_price <", value, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("evaluate_price <=", value, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceIn(List<BigDecimal> values) {
            addCriterion("evaluate_price in", values, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceNotIn(List<BigDecimal> values) {
            addCriterion("evaluate_price not in", values, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("evaluate_price between", value1, value2, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("evaluate_price not between", value1, value2, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andLocationFactorRatioIsNull() {
            addCriterion("location_factor_ratio is null");
            return (Criteria) this;
        }

        public Criteria andLocationFactorRatioIsNotNull() {
            addCriterion("location_factor_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andLocationFactorRatioEqualTo(BigDecimal value) {
            addCriterion("location_factor_ratio =", value, "locationFactorRatio");
            return (Criteria) this;
        }

        public Criteria andLocationFactorRatioNotEqualTo(BigDecimal value) {
            addCriterion("location_factor_ratio <>", value, "locationFactorRatio");
            return (Criteria) this;
        }

        public Criteria andLocationFactorRatioGreaterThan(BigDecimal value) {
            addCriterion("location_factor_ratio >", value, "locationFactorRatio");
            return (Criteria) this;
        }

        public Criteria andLocationFactorRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("location_factor_ratio >=", value, "locationFactorRatio");
            return (Criteria) this;
        }

        public Criteria andLocationFactorRatioLessThan(BigDecimal value) {
            addCriterion("location_factor_ratio <", value, "locationFactorRatio");
            return (Criteria) this;
        }

        public Criteria andLocationFactorRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("location_factor_ratio <=", value, "locationFactorRatio");
            return (Criteria) this;
        }

        public Criteria andLocationFactorRatioIn(List<BigDecimal> values) {
            addCriterion("location_factor_ratio in", values, "locationFactorRatio");
            return (Criteria) this;
        }

        public Criteria andLocationFactorRatioNotIn(List<BigDecimal> values) {
            addCriterion("location_factor_ratio not in", values, "locationFactorRatio");
            return (Criteria) this;
        }

        public Criteria andLocationFactorRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("location_factor_ratio between", value1, value2, "locationFactorRatio");
            return (Criteria) this;
        }

        public Criteria andLocationFactorRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("location_factor_ratio not between", value1, value2, "locationFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEquityFactorRatioIsNull() {
            addCriterion("equity_factor_ratio is null");
            return (Criteria) this;
        }

        public Criteria andEquityFactorRatioIsNotNull() {
            addCriterion("equity_factor_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andEquityFactorRatioEqualTo(BigDecimal value) {
            addCriterion("equity_factor_ratio =", value, "equityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEquityFactorRatioNotEqualTo(BigDecimal value) {
            addCriterion("equity_factor_ratio <>", value, "equityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEquityFactorRatioGreaterThan(BigDecimal value) {
            addCriterion("equity_factor_ratio >", value, "equityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEquityFactorRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("equity_factor_ratio >=", value, "equityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEquityFactorRatioLessThan(BigDecimal value) {
            addCriterion("equity_factor_ratio <", value, "equityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEquityFactorRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("equity_factor_ratio <=", value, "equityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEquityFactorRatioIn(List<BigDecimal> values) {
            addCriterion("equity_factor_ratio in", values, "equityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEquityFactorRatioNotIn(List<BigDecimal> values) {
            addCriterion("equity_factor_ratio not in", values, "equityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEquityFactorRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("equity_factor_ratio between", value1, value2, "equityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEquityFactorRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("equity_factor_ratio not between", value1, value2, "equityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEntityFactorRatioIsNull() {
            addCriterion("entity_factor_ratio is null");
            return (Criteria) this;
        }

        public Criteria andEntityFactorRatioIsNotNull() {
            addCriterion("entity_factor_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andEntityFactorRatioEqualTo(BigDecimal value) {
            addCriterion("entity_factor_ratio =", value, "entityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEntityFactorRatioNotEqualTo(BigDecimal value) {
            addCriterion("entity_factor_ratio <>", value, "entityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEntityFactorRatioGreaterThan(BigDecimal value) {
            addCriterion("entity_factor_ratio >", value, "entityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEntityFactorRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("entity_factor_ratio >=", value, "entityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEntityFactorRatioLessThan(BigDecimal value) {
            addCriterion("entity_factor_ratio <", value, "entityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEntityFactorRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("entity_factor_ratio <=", value, "entityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEntityFactorRatioIn(List<BigDecimal> values) {
            addCriterion("entity_factor_ratio in", values, "entityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEntityFactorRatioNotIn(List<BigDecimal> values) {
            addCriterion("entity_factor_ratio not in", values, "entityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEntityFactorRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("entity_factor_ratio between", value1, value2, "entityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andEntityFactorRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("entity_factor_ratio not between", value1, value2, "entityFactorRatio");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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