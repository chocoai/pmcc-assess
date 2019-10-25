package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NetInfoRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NetInfoRecordExample() {
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

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
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

        public Criteria andNoticeDateIsNull() {
            addCriterion("notice_date is null");
            return (Criteria) this;
        }

        public Criteria andNoticeDateIsNotNull() {
            addCriterion("notice_date is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeDateEqualTo(String value) {
            addCriterion("notice_date =", value, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateNotEqualTo(String value) {
            addCriterion("notice_date <>", value, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateGreaterThan(String value) {
            addCriterion("notice_date >", value, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateGreaterThanOrEqualTo(String value) {
            addCriterion("notice_date >=", value, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateLessThan(String value) {
            addCriterion("notice_date <", value, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateLessThanOrEqualTo(String value) {
            addCriterion("notice_date <=", value, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateLike(String value) {
            addCriterion("notice_date like", value, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateNotLike(String value) {
            addCriterion("notice_date not like", value, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateIn(List<String> values) {
            addCriterion("notice_date in", values, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateNotIn(List<String> values) {
            addCriterion("notice_date not in", values, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateBetween(String value1, String value2) {
            addCriterion("notice_date between", value1, value2, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateNotBetween(String value1, String value2) {
            addCriterion("notice_date not between", value1, value2, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("begin_time is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterion("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterion("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterion("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterion("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterion("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterion("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterion("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("begin_time not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andSourceSiteNameIsNull() {
            addCriterion("source_site_name is null");
            return (Criteria) this;
        }

        public Criteria andSourceSiteNameIsNotNull() {
            addCriterion("source_site_name is not null");
            return (Criteria) this;
        }

        public Criteria andSourceSiteNameEqualTo(String value) {
            addCriterion("source_site_name =", value, "sourceSiteName");
            return (Criteria) this;
        }

        public Criteria andSourceSiteNameNotEqualTo(String value) {
            addCriterion("source_site_name <>", value, "sourceSiteName");
            return (Criteria) this;
        }

        public Criteria andSourceSiteNameGreaterThan(String value) {
            addCriterion("source_site_name >", value, "sourceSiteName");
            return (Criteria) this;
        }

        public Criteria andSourceSiteNameGreaterThanOrEqualTo(String value) {
            addCriterion("source_site_name >=", value, "sourceSiteName");
            return (Criteria) this;
        }

        public Criteria andSourceSiteNameLessThan(String value) {
            addCriterion("source_site_name <", value, "sourceSiteName");
            return (Criteria) this;
        }

        public Criteria andSourceSiteNameLessThanOrEqualTo(String value) {
            addCriterion("source_site_name <=", value, "sourceSiteName");
            return (Criteria) this;
        }

        public Criteria andSourceSiteNameLike(String value) {
            addCriterion("source_site_name like", value, "sourceSiteName");
            return (Criteria) this;
        }

        public Criteria andSourceSiteNameNotLike(String value) {
            addCriterion("source_site_name not like", value, "sourceSiteName");
            return (Criteria) this;
        }

        public Criteria andSourceSiteNameIn(List<String> values) {
            addCriterion("source_site_name in", values, "sourceSiteName");
            return (Criteria) this;
        }

        public Criteria andSourceSiteNameNotIn(List<String> values) {
            addCriterion("source_site_name not in", values, "sourceSiteName");
            return (Criteria) this;
        }

        public Criteria andSourceSiteNameBetween(String value1, String value2) {
            addCriterion("source_site_name between", value1, value2, "sourceSiteName");
            return (Criteria) this;
        }

        public Criteria andSourceSiteNameNotBetween(String value1, String value2) {
            addCriterion("source_site_name not between", value1, value2, "sourceSiteName");
            return (Criteria) this;
        }

        public Criteria andSourceSiteUrlIsNull() {
            addCriterion("source_site_url is null");
            return (Criteria) this;
        }

        public Criteria andSourceSiteUrlIsNotNull() {
            addCriterion("source_site_url is not null");
            return (Criteria) this;
        }

        public Criteria andSourceSiteUrlEqualTo(String value) {
            addCriterion("source_site_url =", value, "sourceSiteUrl");
            return (Criteria) this;
        }

        public Criteria andSourceSiteUrlNotEqualTo(String value) {
            addCriterion("source_site_url <>", value, "sourceSiteUrl");
            return (Criteria) this;
        }

        public Criteria andSourceSiteUrlGreaterThan(String value) {
            addCriterion("source_site_url >", value, "sourceSiteUrl");
            return (Criteria) this;
        }

        public Criteria andSourceSiteUrlGreaterThanOrEqualTo(String value) {
            addCriterion("source_site_url >=", value, "sourceSiteUrl");
            return (Criteria) this;
        }

        public Criteria andSourceSiteUrlLessThan(String value) {
            addCriterion("source_site_url <", value, "sourceSiteUrl");
            return (Criteria) this;
        }

        public Criteria andSourceSiteUrlLessThanOrEqualTo(String value) {
            addCriterion("source_site_url <=", value, "sourceSiteUrl");
            return (Criteria) this;
        }

        public Criteria andSourceSiteUrlLike(String value) {
            addCriterion("source_site_url like", value, "sourceSiteUrl");
            return (Criteria) this;
        }

        public Criteria andSourceSiteUrlNotLike(String value) {
            addCriterion("source_site_url not like", value, "sourceSiteUrl");
            return (Criteria) this;
        }

        public Criteria andSourceSiteUrlIn(List<String> values) {
            addCriterion("source_site_url in", values, "sourceSiteUrl");
            return (Criteria) this;
        }

        public Criteria andSourceSiteUrlNotIn(List<String> values) {
            addCriterion("source_site_url not in", values, "sourceSiteUrl");
            return (Criteria) this;
        }

        public Criteria andSourceSiteUrlBetween(String value1, String value2) {
            addCriterion("source_site_url between", value1, value2, "sourceSiteUrl");
            return (Criteria) this;
        }

        public Criteria andSourceSiteUrlNotBetween(String value1, String value2) {
            addCriterion("source_site_url not between", value1, value2, "sourceSiteUrl");
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

        public Criteria andCurrentPriceIsNull() {
            addCriterion("current_price is null");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceIsNotNull() {
            addCriterion("current_price is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceEqualTo(String value) {
            addCriterion("current_price =", value, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceNotEqualTo(String value) {
            addCriterion("current_price <>", value, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceGreaterThan(String value) {
            addCriterion("current_price >", value, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceGreaterThanOrEqualTo(String value) {
            addCriterion("current_price >=", value, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceLessThan(String value) {
            addCriterion("current_price <", value, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceLessThanOrEqualTo(String value) {
            addCriterion("current_price <=", value, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceLike(String value) {
            addCriterion("current_price like", value, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceNotLike(String value) {
            addCriterion("current_price not like", value, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceIn(List<String> values) {
            addCriterion("current_price in", values, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceNotIn(List<String> values) {
            addCriterion("current_price not in", values, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceBetween(String value1, String value2) {
            addCriterion("current_price between", value1, value2, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andCurrentPriceNotBetween(String value1, String value2) {
            addCriterion("current_price not between", value1, value2, "currentPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceIsNull() {
            addCriterion("consult_price is null");
            return (Criteria) this;
        }

        public Criteria andConsultPriceIsNotNull() {
            addCriterion("consult_price is not null");
            return (Criteria) this;
        }

        public Criteria andConsultPriceEqualTo(String value) {
            addCriterion("consult_price =", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceNotEqualTo(String value) {
            addCriterion("consult_price <>", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceGreaterThan(String value) {
            addCriterion("consult_price >", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceGreaterThanOrEqualTo(String value) {
            addCriterion("consult_price >=", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceLessThan(String value) {
            addCriterion("consult_price <", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceLessThanOrEqualTo(String value) {
            addCriterion("consult_price <=", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceLike(String value) {
            addCriterion("consult_price like", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceNotLike(String value) {
            addCriterion("consult_price not like", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceIn(List<String> values) {
            addCriterion("consult_price in", values, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceNotIn(List<String> values) {
            addCriterion("consult_price not in", values, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceBetween(String value1, String value2) {
            addCriterion("consult_price between", value1, value2, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceNotBetween(String value1, String value2) {
            addCriterion("consult_price not between", value1, value2, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andInitPriceIsNull() {
            addCriterion("init_price is null");
            return (Criteria) this;
        }

        public Criteria andInitPriceIsNotNull() {
            addCriterion("init_price is not null");
            return (Criteria) this;
        }

        public Criteria andInitPriceEqualTo(String value) {
            addCriterion("init_price =", value, "initPrice");
            return (Criteria) this;
        }

        public Criteria andInitPriceNotEqualTo(String value) {
            addCriterion("init_price <>", value, "initPrice");
            return (Criteria) this;
        }

        public Criteria andInitPriceGreaterThan(String value) {
            addCriterion("init_price >", value, "initPrice");
            return (Criteria) this;
        }

        public Criteria andInitPriceGreaterThanOrEqualTo(String value) {
            addCriterion("init_price >=", value, "initPrice");
            return (Criteria) this;
        }

        public Criteria andInitPriceLessThan(String value) {
            addCriterion("init_price <", value, "initPrice");
            return (Criteria) this;
        }

        public Criteria andInitPriceLessThanOrEqualTo(String value) {
            addCriterion("init_price <=", value, "initPrice");
            return (Criteria) this;
        }

        public Criteria andInitPriceLike(String value) {
            addCriterion("init_price like", value, "initPrice");
            return (Criteria) this;
        }

        public Criteria andInitPriceNotLike(String value) {
            addCriterion("init_price not like", value, "initPrice");
            return (Criteria) this;
        }

        public Criteria andInitPriceIn(List<String> values) {
            addCriterion("init_price in", values, "initPrice");
            return (Criteria) this;
        }

        public Criteria andInitPriceNotIn(List<String> values) {
            addCriterion("init_price not in", values, "initPrice");
            return (Criteria) this;
        }

        public Criteria andInitPriceBetween(String value1, String value2) {
            addCriterion("init_price between", value1, value2, "initPrice");
            return (Criteria) this;
        }

        public Criteria andInitPriceNotBetween(String value1, String value2) {
            addCriterion("init_price not between", value1, value2, "initPrice");
            return (Criteria) this;
        }

        public Criteria andLiquidRatiosIsNull() {
            addCriterion("liquid_ratios is null");
            return (Criteria) this;
        }

        public Criteria andLiquidRatiosIsNotNull() {
            addCriterion("liquid_ratios is not null");
            return (Criteria) this;
        }

        public Criteria andLiquidRatiosEqualTo(String value) {
            addCriterion("liquid_ratios =", value, "liquidRatios");
            return (Criteria) this;
        }

        public Criteria andLiquidRatiosNotEqualTo(String value) {
            addCriterion("liquid_ratios <>", value, "liquidRatios");
            return (Criteria) this;
        }

        public Criteria andLiquidRatiosGreaterThan(String value) {
            addCriterion("liquid_ratios >", value, "liquidRatios");
            return (Criteria) this;
        }

        public Criteria andLiquidRatiosGreaterThanOrEqualTo(String value) {
            addCriterion("liquid_ratios >=", value, "liquidRatios");
            return (Criteria) this;
        }

        public Criteria andLiquidRatiosLessThan(String value) {
            addCriterion("liquid_ratios <", value, "liquidRatios");
            return (Criteria) this;
        }

        public Criteria andLiquidRatiosLessThanOrEqualTo(String value) {
            addCriterion("liquid_ratios <=", value, "liquidRatios");
            return (Criteria) this;
        }

        public Criteria andLiquidRatiosLike(String value) {
            addCriterion("liquid_ratios like", value, "liquidRatios");
            return (Criteria) this;
        }

        public Criteria andLiquidRatiosNotLike(String value) {
            addCriterion("liquid_ratios not like", value, "liquidRatios");
            return (Criteria) this;
        }

        public Criteria andLiquidRatiosIn(List<String> values) {
            addCriterion("liquid_ratios in", values, "liquidRatios");
            return (Criteria) this;
        }

        public Criteria andLiquidRatiosNotIn(List<String> values) {
            addCriterion("liquid_ratios not in", values, "liquidRatios");
            return (Criteria) this;
        }

        public Criteria andLiquidRatiosBetween(String value1, String value2) {
            addCriterion("liquid_ratios between", value1, value2, "liquidRatios");
            return (Criteria) this;
        }

        public Criteria andLiquidRatiosNotBetween(String value1, String value2) {
            addCriterion("liquid_ratios not between", value1, value2, "liquidRatios");
            return (Criteria) this;
        }

        public Criteria andUnitNameIsNull() {
            addCriterion("unit_name is null");
            return (Criteria) this;
        }

        public Criteria andUnitNameIsNotNull() {
            addCriterion("unit_name is not null");
            return (Criteria) this;
        }

        public Criteria andUnitNameEqualTo(String value) {
            addCriterion("unit_name =", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotEqualTo(String value) {
            addCriterion("unit_name <>", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameGreaterThan(String value) {
            addCriterion("unit_name >", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameGreaterThanOrEqualTo(String value) {
            addCriterion("unit_name >=", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameLessThan(String value) {
            addCriterion("unit_name <", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameLessThanOrEqualTo(String value) {
            addCriterion("unit_name <=", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameLike(String value) {
            addCriterion("unit_name like", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotLike(String value) {
            addCriterion("unit_name not like", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameIn(List<String> values) {
            addCriterion("unit_name in", values, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotIn(List<String> values) {
            addCriterion("unit_name not in", values, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameBetween(String value1, String value2) {
            addCriterion("unit_name between", value1, value2, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotBetween(String value1, String value2) {
            addCriterion("unit_name not between", value1, value2, "unitName");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAssessBaseDateIsNull() {
            addCriterion("assess_base_date is null");
            return (Criteria) this;
        }

        public Criteria andAssessBaseDateIsNotNull() {
            addCriterion("assess_base_date is not null");
            return (Criteria) this;
        }

        public Criteria andAssessBaseDateEqualTo(Date value) {
            addCriterion("assess_base_date =", value, "assessBaseDate");
            return (Criteria) this;
        }

        public Criteria andAssessBaseDateNotEqualTo(Date value) {
            addCriterion("assess_base_date <>", value, "assessBaseDate");
            return (Criteria) this;
        }

        public Criteria andAssessBaseDateGreaterThan(Date value) {
            addCriterion("assess_base_date >", value, "assessBaseDate");
            return (Criteria) this;
        }

        public Criteria andAssessBaseDateGreaterThanOrEqualTo(Date value) {
            addCriterion("assess_base_date >=", value, "assessBaseDate");
            return (Criteria) this;
        }

        public Criteria andAssessBaseDateLessThan(Date value) {
            addCriterion("assess_base_date <", value, "assessBaseDate");
            return (Criteria) this;
        }

        public Criteria andAssessBaseDateLessThanOrEqualTo(Date value) {
            addCriterion("assess_base_date <=", value, "assessBaseDate");
            return (Criteria) this;
        }

        public Criteria andAssessBaseDateIn(List<Date> values) {
            addCriterion("assess_base_date in", values, "assessBaseDate");
            return (Criteria) this;
        }

        public Criteria andAssessBaseDateNotIn(List<Date> values) {
            addCriterion("assess_base_date not in", values, "assessBaseDate");
            return (Criteria) this;
        }

        public Criteria andAssessBaseDateBetween(Date value1, Date value2) {
            addCriterion("assess_base_date between", value1, value2, "assessBaseDate");
            return (Criteria) this;
        }

        public Criteria andAssessBaseDateNotBetween(Date value1, Date value2) {
            addCriterion("assess_base_date not between", value1, value2, "assessBaseDate");
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

        public Criteria andNegotiatedTotalPriceIsNull() {
            addCriterion("negotiated_total_price is null");
            return (Criteria) this;
        }

        public Criteria andNegotiatedTotalPriceIsNotNull() {
            addCriterion("negotiated_total_price is not null");
            return (Criteria) this;
        }

        public Criteria andNegotiatedTotalPriceEqualTo(BigDecimal value) {
            addCriterion("negotiated_total_price =", value, "negotiatedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNegotiatedTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("negotiated_total_price <>", value, "negotiatedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNegotiatedTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("negotiated_total_price >", value, "negotiatedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNegotiatedTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("negotiated_total_price >=", value, "negotiatedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNegotiatedTotalPriceLessThan(BigDecimal value) {
            addCriterion("negotiated_total_price <", value, "negotiatedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNegotiatedTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("negotiated_total_price <=", value, "negotiatedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNegotiatedTotalPriceIn(List<BigDecimal> values) {
            addCriterion("negotiated_total_price in", values, "negotiatedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNegotiatedTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("negotiated_total_price not in", values, "negotiatedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNegotiatedTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("negotiated_total_price between", value1, value2, "negotiatedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNegotiatedTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("negotiated_total_price not between", value1, value2, "negotiatedTotalPrice");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateIsNull() {
            addCriterion("negotiated_date is null");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateIsNotNull() {
            addCriterion("negotiated_date is not null");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateEqualTo(Date value) {
            addCriterion("negotiated_date =", value, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateNotEqualTo(Date value) {
            addCriterion("negotiated_date <>", value, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateGreaterThan(Date value) {
            addCriterion("negotiated_date >", value, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("negotiated_date >=", value, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateLessThan(Date value) {
            addCriterion("negotiated_date <", value, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateLessThanOrEqualTo(Date value) {
            addCriterion("negotiated_date <=", value, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateIn(List<Date> values) {
            addCriterion("negotiated_date in", values, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateNotIn(List<Date> values) {
            addCriterion("negotiated_date not in", values, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateBetween(Date value1, Date value2) {
            addCriterion("negotiated_date between", value1, value2, "negotiatedDate");
            return (Criteria) this;
        }

        public Criteria andNegotiatedDateNotBetween(Date value1, Date value2) {
            addCriterion("negotiated_date not between", value1, value2, "negotiatedDate");
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