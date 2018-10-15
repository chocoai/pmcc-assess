package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdCostConstructionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MdCostConstructionExample() {
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

        public Criteria andConstructionassessmentpriceIsNull() {
            addCriterion("constructionAssessmentPrice is null");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentpriceIsNotNull() {
            addCriterion("constructionAssessmentPrice is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentpriceEqualTo(BigDecimal value) {
            addCriterion("constructionAssessmentPrice =", value, "constructionassessmentprice");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentpriceNotEqualTo(BigDecimal value) {
            addCriterion("constructionAssessmentPrice <>", value, "constructionassessmentprice");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentpriceGreaterThan(BigDecimal value) {
            addCriterion("constructionAssessmentPrice >", value, "constructionassessmentprice");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("constructionAssessmentPrice >=", value, "constructionassessmentprice");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentpriceLessThan(BigDecimal value) {
            addCriterion("constructionAssessmentPrice <", value, "constructionassessmentprice");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("constructionAssessmentPrice <=", value, "constructionassessmentprice");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentpriceIn(List<BigDecimal> values) {
            addCriterion("constructionAssessmentPrice in", values, "constructionassessmentprice");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentpriceNotIn(List<BigDecimal> values) {
            addCriterion("constructionAssessmentPrice not in", values, "constructionassessmentprice");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("constructionAssessmentPrice between", value1, value2, "constructionassessmentprice");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("constructionAssessmentPrice not between", value1, value2, "constructionassessmentprice");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentvalueIsNull() {
            addCriterion("constructionAssessmentValue is null");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentvalueIsNotNull() {
            addCriterion("constructionAssessmentValue is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentvalueEqualTo(String value) {
            addCriterion("constructionAssessmentValue =", value, "constructionassessmentvalue");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentvalueNotEqualTo(String value) {
            addCriterion("constructionAssessmentValue <>", value, "constructionassessmentvalue");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentvalueGreaterThan(String value) {
            addCriterion("constructionAssessmentValue >", value, "constructionassessmentvalue");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentvalueGreaterThanOrEqualTo(String value) {
            addCriterion("constructionAssessmentValue >=", value, "constructionassessmentvalue");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentvalueLessThan(String value) {
            addCriterion("constructionAssessmentValue <", value, "constructionassessmentvalue");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentvalueLessThanOrEqualTo(String value) {
            addCriterion("constructionAssessmentValue <=", value, "constructionassessmentvalue");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentvalueLike(String value) {
            addCriterion("constructionAssessmentValue like", value, "constructionassessmentvalue");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentvalueNotLike(String value) {
            addCriterion("constructionAssessmentValue not like", value, "constructionassessmentvalue");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentvalueIn(List<String> values) {
            addCriterion("constructionAssessmentValue in", values, "constructionassessmentvalue");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentvalueNotIn(List<String> values) {
            addCriterion("constructionAssessmentValue not in", values, "constructionassessmentvalue");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentvalueBetween(String value1, String value2) {
            addCriterion("constructionAssessmentValue between", value1, value2, "constructionassessmentvalue");
            return (Criteria) this;
        }

        public Criteria andConstructionassessmentvalueNotBetween(String value1, String value2) {
            addCriterion("constructionAssessmentValue not between", value1, value2, "constructionassessmentvalue");
            return (Criteria) this;
        }

        public Criteria andInvestmentprofitIsNull() {
            addCriterion("investmentProfit is null");
            return (Criteria) this;
        }

        public Criteria andInvestmentprofitIsNotNull() {
            addCriterion("investmentProfit is not null");
            return (Criteria) this;
        }

        public Criteria andInvestmentprofitEqualTo(String value) {
            addCriterion("investmentProfit =", value, "investmentprofit");
            return (Criteria) this;
        }

        public Criteria andInvestmentprofitNotEqualTo(String value) {
            addCriterion("investmentProfit <>", value, "investmentprofit");
            return (Criteria) this;
        }

        public Criteria andInvestmentprofitGreaterThan(String value) {
            addCriterion("investmentProfit >", value, "investmentprofit");
            return (Criteria) this;
        }

        public Criteria andInvestmentprofitGreaterThanOrEqualTo(String value) {
            addCriterion("investmentProfit >=", value, "investmentprofit");
            return (Criteria) this;
        }

        public Criteria andInvestmentprofitLessThan(String value) {
            addCriterion("investmentProfit <", value, "investmentprofit");
            return (Criteria) this;
        }

        public Criteria andInvestmentprofitLessThanOrEqualTo(String value) {
            addCriterion("investmentProfit <=", value, "investmentprofit");
            return (Criteria) this;
        }

        public Criteria andInvestmentprofitLike(String value) {
            addCriterion("investmentProfit like", value, "investmentprofit");
            return (Criteria) this;
        }

        public Criteria andInvestmentprofitNotLike(String value) {
            addCriterion("investmentProfit not like", value, "investmentprofit");
            return (Criteria) this;
        }

        public Criteria andInvestmentprofitIn(List<String> values) {
            addCriterion("investmentProfit in", values, "investmentprofit");
            return (Criteria) this;
        }

        public Criteria andInvestmentprofitNotIn(List<String> values) {
            addCriterion("investmentProfit not in", values, "investmentprofit");
            return (Criteria) this;
        }

        public Criteria andInvestmentprofitBetween(String value1, String value2) {
            addCriterion("investmentProfit between", value1, value2, "investmentprofit");
            return (Criteria) this;
        }

        public Criteria andInvestmentprofitNotBetween(String value1, String value2) {
            addCriterion("investmentProfit not between", value1, value2, "investmentprofit");
            return (Criteria) this;
        }

        public Criteria andInterestinvestmentIsNull() {
            addCriterion("interestInvestment is null");
            return (Criteria) this;
        }

        public Criteria andInterestinvestmentIsNotNull() {
            addCriterion("interestInvestment is not null");
            return (Criteria) this;
        }

        public Criteria andInterestinvestmentEqualTo(String value) {
            addCriterion("interestInvestment =", value, "interestinvestment");
            return (Criteria) this;
        }

        public Criteria andInterestinvestmentNotEqualTo(String value) {
            addCriterion("interestInvestment <>", value, "interestinvestment");
            return (Criteria) this;
        }

        public Criteria andInterestinvestmentGreaterThan(String value) {
            addCriterion("interestInvestment >", value, "interestinvestment");
            return (Criteria) this;
        }

        public Criteria andInterestinvestmentGreaterThanOrEqualTo(String value) {
            addCriterion("interestInvestment >=", value, "interestinvestment");
            return (Criteria) this;
        }

        public Criteria andInterestinvestmentLessThan(String value) {
            addCriterion("interestInvestment <", value, "interestinvestment");
            return (Criteria) this;
        }

        public Criteria andInterestinvestmentLessThanOrEqualTo(String value) {
            addCriterion("interestInvestment <=", value, "interestinvestment");
            return (Criteria) this;
        }

        public Criteria andInterestinvestmentLike(String value) {
            addCriterion("interestInvestment like", value, "interestinvestment");
            return (Criteria) this;
        }

        public Criteria andInterestinvestmentNotLike(String value) {
            addCriterion("interestInvestment not like", value, "interestinvestment");
            return (Criteria) this;
        }

        public Criteria andInterestinvestmentIn(List<String> values) {
            addCriterion("interestInvestment in", values, "interestinvestment");
            return (Criteria) this;
        }

        public Criteria andInterestinvestmentNotIn(List<String> values) {
            addCriterion("interestInvestment not in", values, "interestinvestment");
            return (Criteria) this;
        }

        public Criteria andInterestinvestmentBetween(String value1, String value2) {
            addCriterion("interestInvestment between", value1, value2, "interestinvestment");
            return (Criteria) this;
        }

        public Criteria andInterestinvestmentNotBetween(String value1, String value2) {
            addCriterion("interestInvestment not between", value1, value2, "interestinvestment");
            return (Criteria) this;
        }

        public Criteria andConstructionsubtotalIsNull() {
            addCriterion("constructionSubtotal is null");
            return (Criteria) this;
        }

        public Criteria andConstructionsubtotalIsNotNull() {
            addCriterion("constructionSubtotal is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionsubtotalEqualTo(String value) {
            addCriterion("constructionSubtotal =", value, "constructionsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionsubtotalNotEqualTo(String value) {
            addCriterion("constructionSubtotal <>", value, "constructionsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionsubtotalGreaterThan(String value) {
            addCriterion("constructionSubtotal >", value, "constructionsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionsubtotalGreaterThanOrEqualTo(String value) {
            addCriterion("constructionSubtotal >=", value, "constructionsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionsubtotalLessThan(String value) {
            addCriterion("constructionSubtotal <", value, "constructionsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionsubtotalLessThanOrEqualTo(String value) {
            addCriterion("constructionSubtotal <=", value, "constructionsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionsubtotalLike(String value) {
            addCriterion("constructionSubtotal like", value, "constructionsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionsubtotalNotLike(String value) {
            addCriterion("constructionSubtotal not like", value, "constructionsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionsubtotalIn(List<String> values) {
            addCriterion("constructionSubtotal in", values, "constructionsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionsubtotalNotIn(List<String> values) {
            addCriterion("constructionSubtotal not in", values, "constructionsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionsubtotalBetween(String value1, String value2) {
            addCriterion("constructionSubtotal between", value1, value2, "constructionsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructionsubtotalNotBetween(String value1, String value2) {
            addCriterion("constructionSubtotal not between", value1, value2, "constructionsubtotal");
            return (Criteria) this;
        }

        public Criteria andLandgetcosttotalIsNull() {
            addCriterion("landGetCostTotal is null");
            return (Criteria) this;
        }

        public Criteria andLandgetcosttotalIsNotNull() {
            addCriterion("landGetCostTotal is not null");
            return (Criteria) this;
        }

        public Criteria andLandgetcosttotalEqualTo(String value) {
            addCriterion("landGetCostTotal =", value, "landgetcosttotal");
            return (Criteria) this;
        }

        public Criteria andLandgetcosttotalNotEqualTo(String value) {
            addCriterion("landGetCostTotal <>", value, "landgetcosttotal");
            return (Criteria) this;
        }

        public Criteria andLandgetcosttotalGreaterThan(String value) {
            addCriterion("landGetCostTotal >", value, "landgetcosttotal");
            return (Criteria) this;
        }

        public Criteria andLandgetcosttotalGreaterThanOrEqualTo(String value) {
            addCriterion("landGetCostTotal >=", value, "landgetcosttotal");
            return (Criteria) this;
        }

        public Criteria andLandgetcosttotalLessThan(String value) {
            addCriterion("landGetCostTotal <", value, "landgetcosttotal");
            return (Criteria) this;
        }

        public Criteria andLandgetcosttotalLessThanOrEqualTo(String value) {
            addCriterion("landGetCostTotal <=", value, "landgetcosttotal");
            return (Criteria) this;
        }

        public Criteria andLandgetcosttotalLike(String value) {
            addCriterion("landGetCostTotal like", value, "landgetcosttotal");
            return (Criteria) this;
        }

        public Criteria andLandgetcosttotalNotLike(String value) {
            addCriterion("landGetCostTotal not like", value, "landgetcosttotal");
            return (Criteria) this;
        }

        public Criteria andLandgetcosttotalIn(List<String> values) {
            addCriterion("landGetCostTotal in", values, "landgetcosttotal");
            return (Criteria) this;
        }

        public Criteria andLandgetcosttotalNotIn(List<String> values) {
            addCriterion("landGetCostTotal not in", values, "landgetcosttotal");
            return (Criteria) this;
        }

        public Criteria andLandgetcosttotalBetween(String value1, String value2) {
            addCriterion("landGetCostTotal between", value1, value2, "landgetcosttotal");
            return (Criteria) this;
        }

        public Criteria andLandgetcosttotalNotBetween(String value1, String value2) {
            addCriterion("landGetCostTotal not between", value1, value2, "landgetcosttotal");
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

        public Criteria andEngineeringIdIsNull() {
            addCriterion("engineering_id is null");
            return (Criteria) this;
        }

        public Criteria andEngineeringIdIsNotNull() {
            addCriterion("engineering_id is not null");
            return (Criteria) this;
        }

        public Criteria andEngineeringIdEqualTo(Integer value) {
            addCriterion("engineering_id =", value, "engineeringId");
            return (Criteria) this;
        }

        public Criteria andEngineeringIdNotEqualTo(Integer value) {
            addCriterion("engineering_id <>", value, "engineeringId");
            return (Criteria) this;
        }

        public Criteria andEngineeringIdGreaterThan(Integer value) {
            addCriterion("engineering_id >", value, "engineeringId");
            return (Criteria) this;
        }

        public Criteria andEngineeringIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("engineering_id >=", value, "engineeringId");
            return (Criteria) this;
        }

        public Criteria andEngineeringIdLessThan(Integer value) {
            addCriterion("engineering_id <", value, "engineeringId");
            return (Criteria) this;
        }

        public Criteria andEngineeringIdLessThanOrEqualTo(Integer value) {
            addCriterion("engineering_id <=", value, "engineeringId");
            return (Criteria) this;
        }

        public Criteria andEngineeringIdIn(List<Integer> values) {
            addCriterion("engineering_id in", values, "engineeringId");
            return (Criteria) this;
        }

        public Criteria andEngineeringIdNotIn(List<Integer> values) {
            addCriterion("engineering_id not in", values, "engineeringId");
            return (Criteria) this;
        }

        public Criteria andEngineeringIdBetween(Integer value1, Integer value2) {
            addCriterion("engineering_id between", value1, value2, "engineeringId");
            return (Criteria) this;
        }

        public Criteria andEngineeringIdNotBetween(Integer value1, Integer value2) {
            addCriterion("engineering_id not between", value1, value2, "engineeringId");
            return (Criteria) this;
        }

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
            addCriterion("pid not between", value1, value2, "pid");
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