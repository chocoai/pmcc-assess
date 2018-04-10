package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.List;

public class ChksApprovalBusinessExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChksApprovalBusinessExample() {
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

        public Criteria andAppkeyIsNull() {
            addCriterion("appkey is null");
            return (Criteria) this;
        }

        public Criteria andAppkeyIsNotNull() {
            addCriterion("appkey is not null");
            return (Criteria) this;
        }

        public Criteria andAppkeyEqualTo(String value) {
            addCriterion("appkey =", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotEqualTo(String value) {
            addCriterion("appkey <>", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyGreaterThan(String value) {
            addCriterion("appkey >", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyGreaterThanOrEqualTo(String value) {
            addCriterion("appkey >=", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyLessThan(String value) {
            addCriterion("appkey <", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyLessThanOrEqualTo(String value) {
            addCriterion("appkey <=", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyLike(String value) {
            addCriterion("appkey like", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotLike(String value) {
            addCriterion("appkey not like", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyIn(List<String> values) {
            addCriterion("appkey in", values, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotIn(List<String> values) {
            addCriterion("appkey not in", values, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyBetween(String value1, String value2) {
            addCriterion("appkey between", value1, value2, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotBetween(String value1, String value2) {
            addCriterion("appkey not between", value1, value2, "appkey");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdIsNull() {
            addCriterion("process_ins_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdIsNotNull() {
            addCriterion("process_ins_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdEqualTo(String value) {
            addCriterion("process_ins_id =", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotEqualTo(String value) {
            addCriterion("process_ins_id <>", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdGreaterThan(String value) {
            addCriterion("process_ins_id >", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdGreaterThanOrEqualTo(String value) {
            addCriterion("process_ins_id >=", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLessThan(String value) {
            addCriterion("process_ins_id <", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLessThanOrEqualTo(String value) {
            addCriterion("process_ins_id <=", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLike(String value) {
            addCriterion("process_ins_id like", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotLike(String value) {
            addCriterion("process_ins_id not like", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdIn(List<String> values) {
            addCriterion("process_ins_id in", values, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotIn(List<String> values) {
            addCriterion("process_ins_id not in", values, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdBetween(String value1, String value2) {
            addCriterion("process_ins_id between", value1, value2, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotBetween(String value1, String value2) {
            addCriterion("process_ins_id not between", value1, value2, "processInsId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andBusinessProcessInsIdIsNull() {
            addCriterion("business_process_ins_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessProcessInsIdIsNotNull() {
            addCriterion("business_process_ins_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessProcessInsIdEqualTo(String value) {
            addCriterion("business_process_ins_id =", value, "businessProcessInsId");
            return (Criteria) this;
        }

        public Criteria andBusinessProcessInsIdNotEqualTo(String value) {
            addCriterion("business_process_ins_id <>", value, "businessProcessInsId");
            return (Criteria) this;
        }

        public Criteria andBusinessProcessInsIdGreaterThan(String value) {
            addCriterion("business_process_ins_id >", value, "businessProcessInsId");
            return (Criteria) this;
        }

        public Criteria andBusinessProcessInsIdGreaterThanOrEqualTo(String value) {
            addCriterion("business_process_ins_id >=", value, "businessProcessInsId");
            return (Criteria) this;
        }

        public Criteria andBusinessProcessInsIdLessThan(String value) {
            addCriterion("business_process_ins_id <", value, "businessProcessInsId");
            return (Criteria) this;
        }

        public Criteria andBusinessProcessInsIdLessThanOrEqualTo(String value) {
            addCriterion("business_process_ins_id <=", value, "businessProcessInsId");
            return (Criteria) this;
        }

        public Criteria andBusinessProcessInsIdLike(String value) {
            addCriterion("business_process_ins_id like", value, "businessProcessInsId");
            return (Criteria) this;
        }

        public Criteria andBusinessProcessInsIdNotLike(String value) {
            addCriterion("business_process_ins_id not like", value, "businessProcessInsId");
            return (Criteria) this;
        }

        public Criteria andBusinessProcessInsIdIn(List<String> values) {
            addCriterion("business_process_ins_id in", values, "businessProcessInsId");
            return (Criteria) this;
        }

        public Criteria andBusinessProcessInsIdNotIn(List<String> values) {
            addCriterion("business_process_ins_id not in", values, "businessProcessInsId");
            return (Criteria) this;
        }

        public Criteria andBusinessProcessInsIdBetween(String value1, String value2) {
            addCriterion("business_process_ins_id between", value1, value2, "businessProcessInsId");
            return (Criteria) this;
        }

        public Criteria andBusinessProcessInsIdNotBetween(String value1, String value2) {
            addCriterion("business_process_ins_id not between", value1, value2, "businessProcessInsId");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailsUrlIsNull() {
            addCriterion("business_details_url is null");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailsUrlIsNotNull() {
            addCriterion("business_details_url is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailsUrlEqualTo(String value) {
            addCriterion("business_details_url =", value, "businessDetailsUrl");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailsUrlNotEqualTo(String value) {
            addCriterion("business_details_url <>", value, "businessDetailsUrl");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailsUrlGreaterThan(String value) {
            addCriterion("business_details_url >", value, "businessDetailsUrl");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailsUrlGreaterThanOrEqualTo(String value) {
            addCriterion("business_details_url >=", value, "businessDetailsUrl");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailsUrlLessThan(String value) {
            addCriterion("business_details_url <", value, "businessDetailsUrl");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailsUrlLessThanOrEqualTo(String value) {
            addCriterion("business_details_url <=", value, "businessDetailsUrl");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailsUrlLike(String value) {
            addCriterion("business_details_url like", value, "businessDetailsUrl");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailsUrlNotLike(String value) {
            addCriterion("business_details_url not like", value, "businessDetailsUrl");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailsUrlIn(List<String> values) {
            addCriterion("business_details_url in", values, "businessDetailsUrl");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailsUrlNotIn(List<String> values) {
            addCriterion("business_details_url not in", values, "businessDetailsUrl");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailsUrlBetween(String value1, String value2) {
            addCriterion("business_details_url between", value1, value2, "businessDetailsUrl");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailsUrlNotBetween(String value1, String value2) {
            addCriterion("business_details_url not between", value1, value2, "businessDetailsUrl");
            return (Criteria) this;
        }

        public Criteria andBusinessBoxIdIsNull() {
            addCriterion("business_box_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessBoxIdIsNotNull() {
            addCriterion("business_box_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessBoxIdEqualTo(Integer value) {
            addCriterion("business_box_id =", value, "businessBoxId");
            return (Criteria) this;
        }

        public Criteria andBusinessBoxIdNotEqualTo(Integer value) {
            addCriterion("business_box_id <>", value, "businessBoxId");
            return (Criteria) this;
        }

        public Criteria andBusinessBoxIdGreaterThan(Integer value) {
            addCriterion("business_box_id >", value, "businessBoxId");
            return (Criteria) this;
        }

        public Criteria andBusinessBoxIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("business_box_id >=", value, "businessBoxId");
            return (Criteria) this;
        }

        public Criteria andBusinessBoxIdLessThan(Integer value) {
            addCriterion("business_box_id <", value, "businessBoxId");
            return (Criteria) this;
        }

        public Criteria andBusinessBoxIdLessThanOrEqualTo(Integer value) {
            addCriterion("business_box_id <=", value, "businessBoxId");
            return (Criteria) this;
        }

        public Criteria andBusinessBoxIdIn(List<Integer> values) {
            addCriterion("business_box_id in", values, "businessBoxId");
            return (Criteria) this;
        }

        public Criteria andBusinessBoxIdNotIn(List<Integer> values) {
            addCriterion("business_box_id not in", values, "businessBoxId");
            return (Criteria) this;
        }

        public Criteria andBusinessBoxIdBetween(Integer value1, Integer value2) {
            addCriterion("business_box_id between", value1, value2, "businessBoxId");
            return (Criteria) this;
        }

        public Criteria andBusinessBoxIdNotBetween(Integer value1, Integer value2) {
            addCriterion("business_box_id not between", value1, value2, "businessBoxId");
            return (Criteria) this;
        }

        public Criteria andBisCheckIsNull() {
            addCriterion("bis_check is null");
            return (Criteria) this;
        }

        public Criteria andBisCheckIsNotNull() {
            addCriterion("bis_check is not null");
            return (Criteria) this;
        }

        public Criteria andBisCheckEqualTo(Boolean value) {
            addCriterion("bis_check =", value, "bisCheck");
            return (Criteria) this;
        }

        public Criteria andBisCheckNotEqualTo(Boolean value) {
            addCriterion("bis_check <>", value, "bisCheck");
            return (Criteria) this;
        }

        public Criteria andBisCheckGreaterThan(Boolean value) {
            addCriterion("bis_check >", value, "bisCheck");
            return (Criteria) this;
        }

        public Criteria andBisCheckGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_check >=", value, "bisCheck");
            return (Criteria) this;
        }

        public Criteria andBisCheckLessThan(Boolean value) {
            addCriterion("bis_check <", value, "bisCheck");
            return (Criteria) this;
        }

        public Criteria andBisCheckLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_check <=", value, "bisCheck");
            return (Criteria) this;
        }

        public Criteria andBisCheckIn(List<Boolean> values) {
            addCriterion("bis_check in", values, "bisCheck");
            return (Criteria) this;
        }

        public Criteria andBisCheckNotIn(List<Boolean> values) {
            addCriterion("bis_check not in", values, "bisCheck");
            return (Criteria) this;
        }

        public Criteria andBisCheckBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_check between", value1, value2, "bisCheck");
            return (Criteria) this;
        }

        public Criteria andBisCheckNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_check not between", value1, value2, "bisCheck");
            return (Criteria) this;
        }

        public Criteria andBisEnableIsNull() {
            addCriterion("bis_enable is null");
            return (Criteria) this;
        }

        public Criteria andBisEnableIsNotNull() {
            addCriterion("bis_enable is not null");
            return (Criteria) this;
        }

        public Criteria andBisEnableEqualTo(Boolean value) {
            addCriterion("bis_enable =", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableNotEqualTo(Boolean value) {
            addCriterion("bis_enable <>", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableGreaterThan(Boolean value) {
            addCriterion("bis_enable >", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_enable >=", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableLessThan(Boolean value) {
            addCriterion("bis_enable <", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_enable <=", value, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableIn(List<Boolean> values) {
            addCriterion("bis_enable in", values, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableNotIn(List<Boolean> values) {
            addCriterion("bis_enable not in", values, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_enable between", value1, value2, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andBisEnableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_enable not between", value1, value2, "bisEnable");
            return (Criteria) this;
        }

        public Criteria andFirstChksIsNull() {
            addCriterion("first_chks is null");
            return (Criteria) this;
        }

        public Criteria andFirstChksIsNotNull() {
            addCriterion("first_chks is not null");
            return (Criteria) this;
        }

        public Criteria andFirstChksEqualTo(String value) {
            addCriterion("first_chks =", value, "firstChks");
            return (Criteria) this;
        }

        public Criteria andFirstChksNotEqualTo(String value) {
            addCriterion("first_chks <>", value, "firstChks");
            return (Criteria) this;
        }

        public Criteria andFirstChksGreaterThan(String value) {
            addCriterion("first_chks >", value, "firstChks");
            return (Criteria) this;
        }

        public Criteria andFirstChksGreaterThanOrEqualTo(String value) {
            addCriterion("first_chks >=", value, "firstChks");
            return (Criteria) this;
        }

        public Criteria andFirstChksLessThan(String value) {
            addCriterion("first_chks <", value, "firstChks");
            return (Criteria) this;
        }

        public Criteria andFirstChksLessThanOrEqualTo(String value) {
            addCriterion("first_chks <=", value, "firstChks");
            return (Criteria) this;
        }

        public Criteria andFirstChksLike(String value) {
            addCriterion("first_chks like", value, "firstChks");
            return (Criteria) this;
        }

        public Criteria andFirstChksNotLike(String value) {
            addCriterion("first_chks not like", value, "firstChks");
            return (Criteria) this;
        }

        public Criteria andFirstChksIn(List<String> values) {
            addCriterion("first_chks in", values, "firstChks");
            return (Criteria) this;
        }

        public Criteria andFirstChksNotIn(List<String> values) {
            addCriterion("first_chks not in", values, "firstChks");
            return (Criteria) this;
        }

        public Criteria andFirstChksBetween(String value1, String value2) {
            addCriterion("first_chks between", value1, value2, "firstChks");
            return (Criteria) this;
        }

        public Criteria andFirstChksNotBetween(String value1, String value2) {
            addCriterion("first_chks not between", value1, value2, "firstChks");
            return (Criteria) this;
        }

        public Criteria andProcessCnNameIsNull() {
            addCriterion("process_cn_name is null");
            return (Criteria) this;
        }

        public Criteria andProcessCnNameIsNotNull() {
            addCriterion("process_cn_name is not null");
            return (Criteria) this;
        }

        public Criteria andProcessCnNameEqualTo(String value) {
            addCriterion("process_cn_name =", value, "processCnName");
            return (Criteria) this;
        }

        public Criteria andProcessCnNameNotEqualTo(String value) {
            addCriterion("process_cn_name <>", value, "processCnName");
            return (Criteria) this;
        }

        public Criteria andProcessCnNameGreaterThan(String value) {
            addCriterion("process_cn_name >", value, "processCnName");
            return (Criteria) this;
        }

        public Criteria andProcessCnNameGreaterThanOrEqualTo(String value) {
            addCriterion("process_cn_name >=", value, "processCnName");
            return (Criteria) this;
        }

        public Criteria andProcessCnNameLessThan(String value) {
            addCriterion("process_cn_name <", value, "processCnName");
            return (Criteria) this;
        }

        public Criteria andProcessCnNameLessThanOrEqualTo(String value) {
            addCriterion("process_cn_name <=", value, "processCnName");
            return (Criteria) this;
        }

        public Criteria andProcessCnNameLike(String value) {
            addCriterion("process_cn_name like", value, "processCnName");
            return (Criteria) this;
        }

        public Criteria andProcessCnNameNotLike(String value) {
            addCriterion("process_cn_name not like", value, "processCnName");
            return (Criteria) this;
        }

        public Criteria andProcessCnNameIn(List<String> values) {
            addCriterion("process_cn_name in", values, "processCnName");
            return (Criteria) this;
        }

        public Criteria andProcessCnNameNotIn(List<String> values) {
            addCriterion("process_cn_name not in", values, "processCnName");
            return (Criteria) this;
        }

        public Criteria andProcessCnNameBetween(String value1, String value2) {
            addCriterion("process_cn_name between", value1, value2, "processCnName");
            return (Criteria) this;
        }

        public Criteria andProcessCnNameNotBetween(String value1, String value2) {
            addCriterion("process_cn_name not between", value1, value2, "processCnName");
            return (Criteria) this;
        }

        public Criteria andProcessTitleIsNull() {
            addCriterion("process_title is null");
            return (Criteria) this;
        }

        public Criteria andProcessTitleIsNotNull() {
            addCriterion("process_title is not null");
            return (Criteria) this;
        }

        public Criteria andProcessTitleEqualTo(String value) {
            addCriterion("process_title =", value, "processTitle");
            return (Criteria) this;
        }

        public Criteria andProcessTitleNotEqualTo(String value) {
            addCriterion("process_title <>", value, "processTitle");
            return (Criteria) this;
        }

        public Criteria andProcessTitleGreaterThan(String value) {
            addCriterion("process_title >", value, "processTitle");
            return (Criteria) this;
        }

        public Criteria andProcessTitleGreaterThanOrEqualTo(String value) {
            addCriterion("process_title >=", value, "processTitle");
            return (Criteria) this;
        }

        public Criteria andProcessTitleLessThan(String value) {
            addCriterion("process_title <", value, "processTitle");
            return (Criteria) this;
        }

        public Criteria andProcessTitleLessThanOrEqualTo(String value) {
            addCriterion("process_title <=", value, "processTitle");
            return (Criteria) this;
        }

        public Criteria andProcessTitleLike(String value) {
            addCriterion("process_title like", value, "processTitle");
            return (Criteria) this;
        }

        public Criteria andProcessTitleNotLike(String value) {
            addCriterion("process_title not like", value, "processTitle");
            return (Criteria) this;
        }

        public Criteria andProcessTitleIn(List<String> values) {
            addCriterion("process_title in", values, "processTitle");
            return (Criteria) this;
        }

        public Criteria andProcessTitleNotIn(List<String> values) {
            addCriterion("process_title not in", values, "processTitle");
            return (Criteria) this;
        }

        public Criteria andProcessTitleBetween(String value1, String value2) {
            addCriterion("process_title between", value1, value2, "processTitle");
            return (Criteria) this;
        }

        public Criteria andProcessTitleNotBetween(String value1, String value2) {
            addCriterion("process_title not between", value1, value2, "processTitle");
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