package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataImgTwoDimensionalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataImgTwoDimensionalExample() {
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

        public Criteria andHeightIsNull() {
            addCriterion("height is null");
            return (Criteria) this;
        }

        public Criteria andHeightIsNotNull() {
            addCriterion("height is not null");
            return (Criteria) this;
        }

        public Criteria andHeightEqualTo(BigDecimal value) {
            addCriterion("height =", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotEqualTo(BigDecimal value) {
            addCriterion("height <>", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThan(BigDecimal value) {
            addCriterion("height >", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("height >=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThan(BigDecimal value) {
            addCriterion("height <", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("height <=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightIn(List<BigDecimal> values) {
            addCriterion("height in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotIn(List<BigDecimal> values) {
            addCriterion("height not in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("height between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("height not between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andWidthIsNull() {
            addCriterion("width is null");
            return (Criteria) this;
        }

        public Criteria andWidthIsNotNull() {
            addCriterion("width is not null");
            return (Criteria) this;
        }

        public Criteria andWidthEqualTo(BigDecimal value) {
            addCriterion("width =", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotEqualTo(BigDecimal value) {
            addCriterion("width <>", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThan(BigDecimal value) {
            addCriterion("width >", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("width >=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThan(BigDecimal value) {
            addCriterion("width <", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThanOrEqualTo(BigDecimal value) {
            addCriterion("width <=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthIn(List<BigDecimal> values) {
            addCriterion("width in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotIn(List<BigDecimal> values) {
            addCriterion("width not in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("width between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("width not between", value1, value2, "width");
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

        public Criteria andImgIdIsNull() {
            addCriterion("img_id is null");
            return (Criteria) this;
        }

        public Criteria andImgIdIsNotNull() {
            addCriterion("img_id is not null");
            return (Criteria) this;
        }

        public Criteria andImgIdEqualTo(Integer value) {
            addCriterion("img_id =", value, "imgId");
            return (Criteria) this;
        }

        public Criteria andImgIdNotEqualTo(Integer value) {
            addCriterion("img_id <>", value, "imgId");
            return (Criteria) this;
        }

        public Criteria andImgIdGreaterThan(Integer value) {
            addCriterion("img_id >", value, "imgId");
            return (Criteria) this;
        }

        public Criteria andImgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("img_id >=", value, "imgId");
            return (Criteria) this;
        }

        public Criteria andImgIdLessThan(Integer value) {
            addCriterion("img_id <", value, "imgId");
            return (Criteria) this;
        }

        public Criteria andImgIdLessThanOrEqualTo(Integer value) {
            addCriterion("img_id <=", value, "imgId");
            return (Criteria) this;
        }

        public Criteria andImgIdIn(List<Integer> values) {
            addCriterion("img_id in", values, "imgId");
            return (Criteria) this;
        }

        public Criteria andImgIdNotIn(List<Integer> values) {
            addCriterion("img_id not in", values, "imgId");
            return (Criteria) this;
        }

        public Criteria andImgIdBetween(Integer value1, Integer value2) {
            addCriterion("img_id between", value1, value2, "imgId");
            return (Criteria) this;
        }

        public Criteria andImgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("img_id not between", value1, value2, "imgId");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNull() {
            addCriterion("img_url is null");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNotNull() {
            addCriterion("img_url is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrlEqualTo(String value) {
            addCriterion("img_url =", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotEqualTo(String value) {
            addCriterion("img_url <>", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThan(String value) {
            addCriterion("img_url >", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("img_url >=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThan(String value) {
            addCriterion("img_url <", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThanOrEqualTo(String value) {
            addCriterion("img_url <=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLike(String value) {
            addCriterion("img_url like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotLike(String value) {
            addCriterion("img_url not like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlIn(List<String> values) {
            addCriterion("img_url in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotIn(List<String> values) {
            addCriterion("img_url not in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlBetween(String value1, String value2) {
            addCriterion("img_url between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotBetween(String value1, String value2) {
            addCriterion("img_url not between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundIdIsNull() {
            addCriterion("background_id is null");
            return (Criteria) this;
        }

        public Criteria andBackgroundIdIsNotNull() {
            addCriterion("background_id is not null");
            return (Criteria) this;
        }

        public Criteria andBackgroundIdEqualTo(Integer value) {
            addCriterion("background_id =", value, "backgroundId");
            return (Criteria) this;
        }

        public Criteria andBackgroundIdNotEqualTo(Integer value) {
            addCriterion("background_id <>", value, "backgroundId");
            return (Criteria) this;
        }

        public Criteria andBackgroundIdGreaterThan(Integer value) {
            addCriterion("background_id >", value, "backgroundId");
            return (Criteria) this;
        }

        public Criteria andBackgroundIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("background_id >=", value, "backgroundId");
            return (Criteria) this;
        }

        public Criteria andBackgroundIdLessThan(Integer value) {
            addCriterion("background_id <", value, "backgroundId");
            return (Criteria) this;
        }

        public Criteria andBackgroundIdLessThanOrEqualTo(Integer value) {
            addCriterion("background_id <=", value, "backgroundId");
            return (Criteria) this;
        }

        public Criteria andBackgroundIdIn(List<Integer> values) {
            addCriterion("background_id in", values, "backgroundId");
            return (Criteria) this;
        }

        public Criteria andBackgroundIdNotIn(List<Integer> values) {
            addCriterion("background_id not in", values, "backgroundId");
            return (Criteria) this;
        }

        public Criteria andBackgroundIdBetween(Integer value1, Integer value2) {
            addCriterion("background_id between", value1, value2, "backgroundId");
            return (Criteria) this;
        }

        public Criteria andBackgroundIdNotBetween(Integer value1, Integer value2) {
            addCriterion("background_id not between", value1, value2, "backgroundId");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlIsNull() {
            addCriterion("background_url is null");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlIsNotNull() {
            addCriterion("background_url is not null");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlEqualTo(String value) {
            addCriterion("background_url =", value, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlNotEqualTo(String value) {
            addCriterion("background_url <>", value, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlGreaterThan(String value) {
            addCriterion("background_url >", value, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlGreaterThanOrEqualTo(String value) {
            addCriterion("background_url >=", value, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlLessThan(String value) {
            addCriterion("background_url <", value, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlLessThanOrEqualTo(String value) {
            addCriterion("background_url <=", value, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlLike(String value) {
            addCriterion("background_url like", value, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlNotLike(String value) {
            addCriterion("background_url not like", value, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlIn(List<String> values) {
            addCriterion("background_url in", values, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlNotIn(List<String> values) {
            addCriterion("background_url not in", values, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlBetween(String value1, String value2) {
            addCriterion("background_url between", value1, value2, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlNotBetween(String value1, String value2) {
            addCriterion("background_url not between", value1, value2, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andLeftNIsNull() {
            addCriterion("left_N is null");
            return (Criteria) this;
        }

        public Criteria andLeftNIsNotNull() {
            addCriterion("left_N is not null");
            return (Criteria) this;
        }

        public Criteria andLeftNEqualTo(BigDecimal value) {
            addCriterion("left_N =", value, "leftN");
            return (Criteria) this;
        }

        public Criteria andLeftNNotEqualTo(BigDecimal value) {
            addCriterion("left_N <>", value, "leftN");
            return (Criteria) this;
        }

        public Criteria andLeftNGreaterThan(BigDecimal value) {
            addCriterion("left_N >", value, "leftN");
            return (Criteria) this;
        }

        public Criteria andLeftNGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("left_N >=", value, "leftN");
            return (Criteria) this;
        }

        public Criteria andLeftNLessThan(BigDecimal value) {
            addCriterion("left_N <", value, "leftN");
            return (Criteria) this;
        }

        public Criteria andLeftNLessThanOrEqualTo(BigDecimal value) {
            addCriterion("left_N <=", value, "leftN");
            return (Criteria) this;
        }

        public Criteria andLeftNIn(List<BigDecimal> values) {
            addCriterion("left_N in", values, "leftN");
            return (Criteria) this;
        }

        public Criteria andLeftNNotIn(List<BigDecimal> values) {
            addCriterion("left_N not in", values, "leftN");
            return (Criteria) this;
        }

        public Criteria andLeftNBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("left_N between", value1, value2, "leftN");
            return (Criteria) this;
        }

        public Criteria andLeftNNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("left_N not between", value1, value2, "leftN");
            return (Criteria) this;
        }

        public Criteria andTopNIsNull() {
            addCriterion("top_N is null");
            return (Criteria) this;
        }

        public Criteria andTopNIsNotNull() {
            addCriterion("top_N is not null");
            return (Criteria) this;
        }

        public Criteria andTopNEqualTo(BigDecimal value) {
            addCriterion("top_N =", value, "topN");
            return (Criteria) this;
        }

        public Criteria andTopNNotEqualTo(BigDecimal value) {
            addCriterion("top_N <>", value, "topN");
            return (Criteria) this;
        }

        public Criteria andTopNGreaterThan(BigDecimal value) {
            addCriterion("top_N >", value, "topN");
            return (Criteria) this;
        }

        public Criteria andTopNGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("top_N >=", value, "topN");
            return (Criteria) this;
        }

        public Criteria andTopNLessThan(BigDecimal value) {
            addCriterion("top_N <", value, "topN");
            return (Criteria) this;
        }

        public Criteria andTopNLessThanOrEqualTo(BigDecimal value) {
            addCriterion("top_N <=", value, "topN");
            return (Criteria) this;
        }

        public Criteria andTopNIn(List<BigDecimal> values) {
            addCriterion("top_N in", values, "topN");
            return (Criteria) this;
        }

        public Criteria andTopNNotIn(List<BigDecimal> values) {
            addCriterion("top_N not in", values, "topN");
            return (Criteria) this;
        }

        public Criteria andTopNBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("top_N between", value1, value2, "topN");
            return (Criteria) this;
        }

        public Criteria andTopNNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("top_N not between", value1, value2, "topN");
            return (Criteria) this;
        }

        public Criteria andDegIsNull() {
            addCriterion("deg is null");
            return (Criteria) this;
        }

        public Criteria andDegIsNotNull() {
            addCriterion("deg is not null");
            return (Criteria) this;
        }

        public Criteria andDegEqualTo(BigDecimal value) {
            addCriterion("deg =", value, "deg");
            return (Criteria) this;
        }

        public Criteria andDegNotEqualTo(BigDecimal value) {
            addCriterion("deg <>", value, "deg");
            return (Criteria) this;
        }

        public Criteria andDegGreaterThan(BigDecimal value) {
            addCriterion("deg >", value, "deg");
            return (Criteria) this;
        }

        public Criteria andDegGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deg >=", value, "deg");
            return (Criteria) this;
        }

        public Criteria andDegLessThan(BigDecimal value) {
            addCriterion("deg <", value, "deg");
            return (Criteria) this;
        }

        public Criteria andDegLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deg <=", value, "deg");
            return (Criteria) this;
        }

        public Criteria andDegIn(List<BigDecimal> values) {
            addCriterion("deg in", values, "deg");
            return (Criteria) this;
        }

        public Criteria andDegNotIn(List<BigDecimal> values) {
            addCriterion("deg not in", values, "deg");
            return (Criteria) this;
        }

        public Criteria andDegBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deg between", value1, value2, "deg");
            return (Criteria) this;
        }

        public Criteria andDegNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deg not between", value1, value2, "deg");
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