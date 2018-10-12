package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MdDevelopmentArchitecturalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MdDevelopmentArchitecturalExample() {
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

        public Criteria andEstimateunitpricelandc33IsNull() {
            addCriterion("estimateUnitPriceLandC33 is null");
            return (Criteria) this;
        }

        public Criteria andEstimateunitpricelandc33IsNotNull() {
            addCriterion("estimateUnitPriceLandC33 is not null");
            return (Criteria) this;
        }

        public Criteria andEstimateunitpricelandc33EqualTo(String value) {
            addCriterion("estimateUnitPriceLandC33 =", value, "estimateunitpricelandc33");
            return (Criteria) this;
        }

        public Criteria andEstimateunitpricelandc33NotEqualTo(String value) {
            addCriterion("estimateUnitPriceLandC33 <>", value, "estimateunitpricelandc33");
            return (Criteria) this;
        }

        public Criteria andEstimateunitpricelandc33GreaterThan(String value) {
            addCriterion("estimateUnitPriceLandC33 >", value, "estimateunitpricelandc33");
            return (Criteria) this;
        }

        public Criteria andEstimateunitpricelandc33GreaterThanOrEqualTo(String value) {
            addCriterion("estimateUnitPriceLandC33 >=", value, "estimateunitpricelandc33");
            return (Criteria) this;
        }

        public Criteria andEstimateunitpricelandc33LessThan(String value) {
            addCriterion("estimateUnitPriceLandC33 <", value, "estimateunitpricelandc33");
            return (Criteria) this;
        }

        public Criteria andEstimateunitpricelandc33LessThanOrEqualTo(String value) {
            addCriterion("estimateUnitPriceLandC33 <=", value, "estimateunitpricelandc33");
            return (Criteria) this;
        }

        public Criteria andEstimateunitpricelandc33Like(String value) {
            addCriterion("estimateUnitPriceLandC33 like", value, "estimateunitpricelandc33");
            return (Criteria) this;
        }

        public Criteria andEstimateunitpricelandc33NotLike(String value) {
            addCriterion("estimateUnitPriceLandC33 not like", value, "estimateunitpricelandc33");
            return (Criteria) this;
        }

        public Criteria andEstimateunitpricelandc33In(List<String> values) {
            addCriterion("estimateUnitPriceLandC33 in", values, "estimateunitpricelandc33");
            return (Criteria) this;
        }

        public Criteria andEstimateunitpricelandc33NotIn(List<String> values) {
            addCriterion("estimateUnitPriceLandC33 not in", values, "estimateunitpricelandc33");
            return (Criteria) this;
        }

        public Criteria andEstimateunitpricelandc33Between(String value1, String value2) {
            addCriterion("estimateUnitPriceLandC33 between", value1, value2, "estimateunitpricelandc33");
            return (Criteria) this;
        }

        public Criteria andEstimateunitpricelandc33NotBetween(String value1, String value2) {
            addCriterion("estimateUnitPriceLandC33 not between", value1, value2, "estimateunitpricelandc33");
            return (Criteria) this;
        }

        public Criteria andLandpricecorrectingIsNull() {
            addCriterion("landPriceCorrecting is null");
            return (Criteria) this;
        }

        public Criteria andLandpricecorrectingIsNotNull() {
            addCriterion("landPriceCorrecting is not null");
            return (Criteria) this;
        }

        public Criteria andLandpricecorrectingEqualTo(String value) {
            addCriterion("landPriceCorrecting =", value, "landpricecorrecting");
            return (Criteria) this;
        }

        public Criteria andLandpricecorrectingNotEqualTo(String value) {
            addCriterion("landPriceCorrecting <>", value, "landpricecorrecting");
            return (Criteria) this;
        }

        public Criteria andLandpricecorrectingGreaterThan(String value) {
            addCriterion("landPriceCorrecting >", value, "landpricecorrecting");
            return (Criteria) this;
        }

        public Criteria andLandpricecorrectingGreaterThanOrEqualTo(String value) {
            addCriterion("landPriceCorrecting >=", value, "landpricecorrecting");
            return (Criteria) this;
        }

        public Criteria andLandpricecorrectingLessThan(String value) {
            addCriterion("landPriceCorrecting <", value, "landpricecorrecting");
            return (Criteria) this;
        }

        public Criteria andLandpricecorrectingLessThanOrEqualTo(String value) {
            addCriterion("landPriceCorrecting <=", value, "landpricecorrecting");
            return (Criteria) this;
        }

        public Criteria andLandpricecorrectingLike(String value) {
            addCriterion("landPriceCorrecting like", value, "landpricecorrecting");
            return (Criteria) this;
        }

        public Criteria andLandpricecorrectingNotLike(String value) {
            addCriterion("landPriceCorrecting not like", value, "landpricecorrecting");
            return (Criteria) this;
        }

        public Criteria andLandpricecorrectingIn(List<String> values) {
            addCriterion("landPriceCorrecting in", values, "landpricecorrecting");
            return (Criteria) this;
        }

        public Criteria andLandpricecorrectingNotIn(List<String> values) {
            addCriterion("landPriceCorrecting not in", values, "landpricecorrecting");
            return (Criteria) this;
        }

        public Criteria andLandpricecorrectingBetween(String value1, String value2) {
            addCriterion("landPriceCorrecting between", value1, value2, "landpricecorrecting");
            return (Criteria) this;
        }

        public Criteria andLandpricecorrectingNotBetween(String value1, String value2) {
            addCriterion("landPriceCorrecting not between", value1, value2, "landpricecorrecting");
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

        public Criteria andUnforeseenexpensesIsNull() {
            addCriterion("unforeseenExpenses is null");
            return (Criteria) this;
        }

        public Criteria andUnforeseenexpensesIsNotNull() {
            addCriterion("unforeseenExpenses is not null");
            return (Criteria) this;
        }

        public Criteria andUnforeseenexpensesEqualTo(String value) {
            addCriterion("unforeseenExpenses =", value, "unforeseenexpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenexpensesNotEqualTo(String value) {
            addCriterion("unforeseenExpenses <>", value, "unforeseenexpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenexpensesGreaterThan(String value) {
            addCriterion("unforeseenExpenses >", value, "unforeseenexpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenexpensesGreaterThanOrEqualTo(String value) {
            addCriterion("unforeseenExpenses >=", value, "unforeseenexpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenexpensesLessThan(String value) {
            addCriterion("unforeseenExpenses <", value, "unforeseenexpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenexpensesLessThanOrEqualTo(String value) {
            addCriterion("unforeseenExpenses <=", value, "unforeseenexpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenexpensesLike(String value) {
            addCriterion("unforeseenExpenses like", value, "unforeseenexpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenexpensesNotLike(String value) {
            addCriterion("unforeseenExpenses not like", value, "unforeseenexpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenexpensesIn(List<String> values) {
            addCriterion("unforeseenExpenses in", values, "unforeseenexpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenexpensesNotIn(List<String> values) {
            addCriterion("unforeseenExpenses not in", values, "unforeseenexpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenexpensesBetween(String value1, String value2) {
            addCriterion("unforeseenExpenses between", value1, value2, "unforeseenexpenses");
            return (Criteria) this;
        }

        public Criteria andUnforeseenexpensesNotBetween(String value1, String value2) {
            addCriterion("unforeseenExpenses not between", value1, value2, "unforeseenexpenses");
            return (Criteria) this;
        }

        public Criteria andConstructioncostsubtotalIsNull() {
            addCriterion("constructionCostSubtotal is null");
            return (Criteria) this;
        }

        public Criteria andConstructioncostsubtotalIsNotNull() {
            addCriterion("constructionCostSubtotal is not null");
            return (Criteria) this;
        }

        public Criteria andConstructioncostsubtotalEqualTo(String value) {
            addCriterion("constructionCostSubtotal =", value, "constructioncostsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructioncostsubtotalNotEqualTo(String value) {
            addCriterion("constructionCostSubtotal <>", value, "constructioncostsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructioncostsubtotalGreaterThan(String value) {
            addCriterion("constructionCostSubtotal >", value, "constructioncostsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructioncostsubtotalGreaterThanOrEqualTo(String value) {
            addCriterion("constructionCostSubtotal >=", value, "constructioncostsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructioncostsubtotalLessThan(String value) {
            addCriterion("constructionCostSubtotal <", value, "constructioncostsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructioncostsubtotalLessThanOrEqualTo(String value) {
            addCriterion("constructionCostSubtotal <=", value, "constructioncostsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructioncostsubtotalLike(String value) {
            addCriterion("constructionCostSubtotal like", value, "constructioncostsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructioncostsubtotalNotLike(String value) {
            addCriterion("constructionCostSubtotal not like", value, "constructioncostsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructioncostsubtotalIn(List<String> values) {
            addCriterion("constructionCostSubtotal in", values, "constructioncostsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructioncostsubtotalNotIn(List<String> values) {
            addCriterion("constructionCostSubtotal not in", values, "constructioncostsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructioncostsubtotalBetween(String value1, String value2) {
            addCriterion("constructionCostSubtotal between", value1, value2, "constructioncostsubtotal");
            return (Criteria) this;
        }

        public Criteria andConstructioncostsubtotalNotBetween(String value1, String value2) {
            addCriterion("constructionCostSubtotal not between", value1, value2, "constructioncostsubtotal");
            return (Criteria) this;
        }

        public Criteria andTotalgrossfloorareaIsNull() {
            addCriterion("totalGrossFloorArea is null");
            return (Criteria) this;
        }

        public Criteria andTotalgrossfloorareaIsNotNull() {
            addCriterion("totalGrossFloorArea is not null");
            return (Criteria) this;
        }

        public Criteria andTotalgrossfloorareaEqualTo(String value) {
            addCriterion("totalGrossFloorArea =", value, "totalgrossfloorarea");
            return (Criteria) this;
        }

        public Criteria andTotalgrossfloorareaNotEqualTo(String value) {
            addCriterion("totalGrossFloorArea <>", value, "totalgrossfloorarea");
            return (Criteria) this;
        }

        public Criteria andTotalgrossfloorareaGreaterThan(String value) {
            addCriterion("totalGrossFloorArea >", value, "totalgrossfloorarea");
            return (Criteria) this;
        }

        public Criteria andTotalgrossfloorareaGreaterThanOrEqualTo(String value) {
            addCriterion("totalGrossFloorArea >=", value, "totalgrossfloorarea");
            return (Criteria) this;
        }

        public Criteria andTotalgrossfloorareaLessThan(String value) {
            addCriterion("totalGrossFloorArea <", value, "totalgrossfloorarea");
            return (Criteria) this;
        }

        public Criteria andTotalgrossfloorareaLessThanOrEqualTo(String value) {
            addCriterion("totalGrossFloorArea <=", value, "totalgrossfloorarea");
            return (Criteria) this;
        }

        public Criteria andTotalgrossfloorareaLike(String value) {
            addCriterion("totalGrossFloorArea like", value, "totalgrossfloorarea");
            return (Criteria) this;
        }

        public Criteria andTotalgrossfloorareaNotLike(String value) {
            addCriterion("totalGrossFloorArea not like", value, "totalgrossfloorarea");
            return (Criteria) this;
        }

        public Criteria andTotalgrossfloorareaIn(List<String> values) {
            addCriterion("totalGrossFloorArea in", values, "totalgrossfloorarea");
            return (Criteria) this;
        }

        public Criteria andTotalgrossfloorareaNotIn(List<String> values) {
            addCriterion("totalGrossFloorArea not in", values, "totalgrossfloorarea");
            return (Criteria) this;
        }

        public Criteria andTotalgrossfloorareaBetween(String value1, String value2) {
            addCriterion("totalGrossFloorArea between", value1, value2, "totalgrossfloorarea");
            return (Criteria) this;
        }

        public Criteria andTotalgrossfloorareaNotBetween(String value1, String value2) {
            addCriterion("totalGrossFloorArea not between", value1, value2, "totalgrossfloorarea");
            return (Criteria) this;
        }

        public Criteria andEstimatesaletotalIsNull() {
            addCriterion("estimateSaleTotal is null");
            return (Criteria) this;
        }

        public Criteria andEstimatesaletotalIsNotNull() {
            addCriterion("estimateSaleTotal is not null");
            return (Criteria) this;
        }

        public Criteria andEstimatesaletotalEqualTo(String value) {
            addCriterion("estimateSaleTotal =", value, "estimatesaletotal");
            return (Criteria) this;
        }

        public Criteria andEstimatesaletotalNotEqualTo(String value) {
            addCriterion("estimateSaleTotal <>", value, "estimatesaletotal");
            return (Criteria) this;
        }

        public Criteria andEstimatesaletotalGreaterThan(String value) {
            addCriterion("estimateSaleTotal >", value, "estimatesaletotal");
            return (Criteria) this;
        }

        public Criteria andEstimatesaletotalGreaterThanOrEqualTo(String value) {
            addCriterion("estimateSaleTotal >=", value, "estimatesaletotal");
            return (Criteria) this;
        }

        public Criteria andEstimatesaletotalLessThan(String value) {
            addCriterion("estimateSaleTotal <", value, "estimatesaletotal");
            return (Criteria) this;
        }

        public Criteria andEstimatesaletotalLessThanOrEqualTo(String value) {
            addCriterion("estimateSaleTotal <=", value, "estimatesaletotal");
            return (Criteria) this;
        }

        public Criteria andEstimatesaletotalLike(String value) {
            addCriterion("estimateSaleTotal like", value, "estimatesaletotal");
            return (Criteria) this;
        }

        public Criteria andEstimatesaletotalNotLike(String value) {
            addCriterion("estimateSaleTotal not like", value, "estimatesaletotal");
            return (Criteria) this;
        }

        public Criteria andEstimatesaletotalIn(List<String> values) {
            addCriterion("estimateSaleTotal in", values, "estimatesaletotal");
            return (Criteria) this;
        }

        public Criteria andEstimatesaletotalNotIn(List<String> values) {
            addCriterion("estimateSaleTotal not in", values, "estimatesaletotal");
            return (Criteria) this;
        }

        public Criteria andEstimatesaletotalBetween(String value1, String value2) {
            addCriterion("estimateSaleTotal between", value1, value2, "estimatesaletotal");
            return (Criteria) this;
        }

        public Criteria andEstimatesaletotalNotBetween(String value1, String value2) {
            addCriterion("estimateSaleTotal not between", value1, value2, "estimatesaletotal");
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