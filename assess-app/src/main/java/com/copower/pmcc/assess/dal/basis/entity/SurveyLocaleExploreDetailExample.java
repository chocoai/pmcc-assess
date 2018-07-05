package com.copower.pmcc.assess.dal.basis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SurveyLocaleExploreDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SurveyLocaleExploreDetailExample() {
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

        public Criteria andSurveySheetNumberIsNull() {
            addCriterion("survey_sheet_number is null");
            return (Criteria) this;
        }

        public Criteria andSurveySheetNumberIsNotNull() {
            addCriterion("survey_sheet_number is not null");
            return (Criteria) this;
        }

        public Criteria andSurveySheetNumberEqualTo(Integer value) {
            addCriterion("survey_sheet_number =", value, "surveySheetNumber");
            return (Criteria) this;
        }

        public Criteria andSurveySheetNumberNotEqualTo(Integer value) {
            addCriterion("survey_sheet_number <>", value, "surveySheetNumber");
            return (Criteria) this;
        }

        public Criteria andSurveySheetNumberGreaterThan(Integer value) {
            addCriterion("survey_sheet_number >", value, "surveySheetNumber");
            return (Criteria) this;
        }

        public Criteria andSurveySheetNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("survey_sheet_number >=", value, "surveySheetNumber");
            return (Criteria) this;
        }

        public Criteria andSurveySheetNumberLessThan(Integer value) {
            addCriterion("survey_sheet_number <", value, "surveySheetNumber");
            return (Criteria) this;
        }

        public Criteria andSurveySheetNumberLessThanOrEqualTo(Integer value) {
            addCriterion("survey_sheet_number <=", value, "surveySheetNumber");
            return (Criteria) this;
        }

        public Criteria andSurveySheetNumberIn(List<Integer> values) {
            addCriterion("survey_sheet_number in", values, "surveySheetNumber");
            return (Criteria) this;
        }

        public Criteria andSurveySheetNumberNotIn(List<Integer> values) {
            addCriterion("survey_sheet_number not in", values, "surveySheetNumber");
            return (Criteria) this;
        }

        public Criteria andSurveySheetNumberBetween(Integer value1, Integer value2) {
            addCriterion("survey_sheet_number between", value1, value2, "surveySheetNumber");
            return (Criteria) this;
        }

        public Criteria andSurveySheetNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("survey_sheet_number not between", value1, value2, "surveySheetNumber");
            return (Criteria) this;
        }

        public Criteria andSurveyPeopleIsNull() {
            addCriterion("survey_people is null");
            return (Criteria) this;
        }

        public Criteria andSurveyPeopleIsNotNull() {
            addCriterion("survey_people is not null");
            return (Criteria) this;
        }

        public Criteria andSurveyPeopleEqualTo(String value) {
            addCriterion("survey_people =", value, "surveyPeople");
            return (Criteria) this;
        }

        public Criteria andSurveyPeopleNotEqualTo(String value) {
            addCriterion("survey_people <>", value, "surveyPeople");
            return (Criteria) this;
        }

        public Criteria andSurveyPeopleGreaterThan(String value) {
            addCriterion("survey_people >", value, "surveyPeople");
            return (Criteria) this;
        }

        public Criteria andSurveyPeopleGreaterThanOrEqualTo(String value) {
            addCriterion("survey_people >=", value, "surveyPeople");
            return (Criteria) this;
        }

        public Criteria andSurveyPeopleLessThan(String value) {
            addCriterion("survey_people <", value, "surveyPeople");
            return (Criteria) this;
        }

        public Criteria andSurveyPeopleLessThanOrEqualTo(String value) {
            addCriterion("survey_people <=", value, "surveyPeople");
            return (Criteria) this;
        }

        public Criteria andSurveyPeopleLike(String value) {
            addCriterion("survey_people like", value, "surveyPeople");
            return (Criteria) this;
        }

        public Criteria andSurveyPeopleNotLike(String value) {
            addCriterion("survey_people not like", value, "surveyPeople");
            return (Criteria) this;
        }

        public Criteria andSurveyPeopleIn(List<String> values) {
            addCriterion("survey_people in", values, "surveyPeople");
            return (Criteria) this;
        }

        public Criteria andSurveyPeopleNotIn(List<String> values) {
            addCriterion("survey_people not in", values, "surveyPeople");
            return (Criteria) this;
        }

        public Criteria andSurveyPeopleBetween(String value1, String value2) {
            addCriterion("survey_people between", value1, value2, "surveyPeople");
            return (Criteria) this;
        }

        public Criteria andSurveyPeopleNotBetween(String value1, String value2) {
            addCriterion("survey_people not between", value1, value2, "surveyPeople");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeIsNull() {
            addCriterion("survey_time is null");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeIsNotNull() {
            addCriterion("survey_time is not null");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeEqualTo(Date value) {
            addCriterion("survey_time =", value, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeNotEqualTo(Date value) {
            addCriterion("survey_time <>", value, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeGreaterThan(Date value) {
            addCriterion("survey_time >", value, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("survey_time >=", value, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeLessThan(Date value) {
            addCriterion("survey_time <", value, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeLessThanOrEqualTo(Date value) {
            addCriterion("survey_time <=", value, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeIn(List<Date> values) {
            addCriterion("survey_time in", values, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeNotIn(List<Date> values) {
            addCriterion("survey_time not in", values, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeBetween(Date value1, Date value2) {
            addCriterion("survey_time between", value1, value2, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andSurveyTimeNotBetween(Date value1, Date value2) {
            addCriterion("survey_time not between", value1, value2, "surveyTime");
            return (Criteria) this;
        }

        public Criteria andBelongWarrantIsNull() {
            addCriterion("belong_warrant is null");
            return (Criteria) this;
        }

        public Criteria andBelongWarrantIsNotNull() {
            addCriterion("belong_warrant is not null");
            return (Criteria) this;
        }

        public Criteria andBelongWarrantEqualTo(String value) {
            addCriterion("belong_warrant =", value, "belongWarrant");
            return (Criteria) this;
        }

        public Criteria andBelongWarrantNotEqualTo(String value) {
            addCriterion("belong_warrant <>", value, "belongWarrant");
            return (Criteria) this;
        }

        public Criteria andBelongWarrantGreaterThan(String value) {
            addCriterion("belong_warrant >", value, "belongWarrant");
            return (Criteria) this;
        }

        public Criteria andBelongWarrantGreaterThanOrEqualTo(String value) {
            addCriterion("belong_warrant >=", value, "belongWarrant");
            return (Criteria) this;
        }

        public Criteria andBelongWarrantLessThan(String value) {
            addCriterion("belong_warrant <", value, "belongWarrant");
            return (Criteria) this;
        }

        public Criteria andBelongWarrantLessThanOrEqualTo(String value) {
            addCriterion("belong_warrant <=", value, "belongWarrant");
            return (Criteria) this;
        }

        public Criteria andBelongWarrantLike(String value) {
            addCriterion("belong_warrant like", value, "belongWarrant");
            return (Criteria) this;
        }

        public Criteria andBelongWarrantNotLike(String value) {
            addCriterion("belong_warrant not like", value, "belongWarrant");
            return (Criteria) this;
        }

        public Criteria andBelongWarrantIn(List<String> values) {
            addCriterion("belong_warrant in", values, "belongWarrant");
            return (Criteria) this;
        }

        public Criteria andBelongWarrantNotIn(List<String> values) {
            addCriterion("belong_warrant not in", values, "belongWarrant");
            return (Criteria) this;
        }

        public Criteria andBelongWarrantBetween(String value1, String value2) {
            addCriterion("belong_warrant between", value1, value2, "belongWarrant");
            return (Criteria) this;
        }

        public Criteria andBelongWarrantNotBetween(String value1, String value2) {
            addCriterion("belong_warrant not between", value1, value2, "belongWarrant");
            return (Criteria) this;
        }

        public Criteria andLedLuminousPeopleIsNull() {
            addCriterion("led_luminous_people is null");
            return (Criteria) this;
        }

        public Criteria andLedLuminousPeopleIsNotNull() {
            addCriterion("led_luminous_people is not null");
            return (Criteria) this;
        }

        public Criteria andLedLuminousPeopleEqualTo(String value) {
            addCriterion("led_luminous_people =", value, "ledLuminousPeople");
            return (Criteria) this;
        }

        public Criteria andLedLuminousPeopleNotEqualTo(String value) {
            addCriterion("led_luminous_people <>", value, "ledLuminousPeople");
            return (Criteria) this;
        }

        public Criteria andLedLuminousPeopleGreaterThan(String value) {
            addCriterion("led_luminous_people >", value, "ledLuminousPeople");
            return (Criteria) this;
        }

        public Criteria andLedLuminousPeopleGreaterThanOrEqualTo(String value) {
            addCriterion("led_luminous_people >=", value, "ledLuminousPeople");
            return (Criteria) this;
        }

        public Criteria andLedLuminousPeopleLessThan(String value) {
            addCriterion("led_luminous_people <", value, "ledLuminousPeople");
            return (Criteria) this;
        }

        public Criteria andLedLuminousPeopleLessThanOrEqualTo(String value) {
            addCriterion("led_luminous_people <=", value, "ledLuminousPeople");
            return (Criteria) this;
        }

        public Criteria andLedLuminousPeopleLike(String value) {
            addCriterion("led_luminous_people like", value, "ledLuminousPeople");
            return (Criteria) this;
        }

        public Criteria andLedLuminousPeopleNotLike(String value) {
            addCriterion("led_luminous_people not like", value, "ledLuminousPeople");
            return (Criteria) this;
        }

        public Criteria andLedLuminousPeopleIn(List<String> values) {
            addCriterion("led_luminous_people in", values, "ledLuminousPeople");
            return (Criteria) this;
        }

        public Criteria andLedLuminousPeopleNotIn(List<String> values) {
            addCriterion("led_luminous_people not in", values, "ledLuminousPeople");
            return (Criteria) this;
        }

        public Criteria andLedLuminousPeopleBetween(String value1, String value2) {
            addCriterion("led_luminous_people between", value1, value2, "ledLuminousPeople");
            return (Criteria) this;
        }

        public Criteria andLedLuminousPeopleNotBetween(String value1, String value2) {
            addCriterion("led_luminous_people not between", value1, value2, "ledLuminousPeople");
            return (Criteria) this;
        }

        public Criteria andSurveyImageIsNull() {
            addCriterion("survey_image is null");
            return (Criteria) this;
        }

        public Criteria andSurveyImageIsNotNull() {
            addCriterion("survey_image is not null");
            return (Criteria) this;
        }

        public Criteria andSurveyImageEqualTo(String value) {
            addCriterion("survey_image =", value, "surveyImage");
            return (Criteria) this;
        }

        public Criteria andSurveyImageNotEqualTo(String value) {
            addCriterion("survey_image <>", value, "surveyImage");
            return (Criteria) this;
        }

        public Criteria andSurveyImageGreaterThan(String value) {
            addCriterion("survey_image >", value, "surveyImage");
            return (Criteria) this;
        }

        public Criteria andSurveyImageGreaterThanOrEqualTo(String value) {
            addCriterion("survey_image >=", value, "surveyImage");
            return (Criteria) this;
        }

        public Criteria andSurveyImageLessThan(String value) {
            addCriterion("survey_image <", value, "surveyImage");
            return (Criteria) this;
        }

        public Criteria andSurveyImageLessThanOrEqualTo(String value) {
            addCriterion("survey_image <=", value, "surveyImage");
            return (Criteria) this;
        }

        public Criteria andSurveyImageLike(String value) {
            addCriterion("survey_image like", value, "surveyImage");
            return (Criteria) this;
        }

        public Criteria andSurveyImageNotLike(String value) {
            addCriterion("survey_image not like", value, "surveyImage");
            return (Criteria) this;
        }

        public Criteria andSurveyImageIn(List<String> values) {
            addCriterion("survey_image in", values, "surveyImage");
            return (Criteria) this;
        }

        public Criteria andSurveyImageNotIn(List<String> values) {
            addCriterion("survey_image not in", values, "surveyImage");
            return (Criteria) this;
        }

        public Criteria andSurveyImageBetween(String value1, String value2) {
            addCriterion("survey_image between", value1, value2, "surveyImage");
            return (Criteria) this;
        }

        public Criteria andSurveyImageNotBetween(String value1, String value2) {
            addCriterion("survey_image not between", value1, value2, "surveyImage");
            return (Criteria) this;
        }

        public Criteria andSurveyVideoIsNull() {
            addCriterion("survey_video is null");
            return (Criteria) this;
        }

        public Criteria andSurveyVideoIsNotNull() {
            addCriterion("survey_video is not null");
            return (Criteria) this;
        }

        public Criteria andSurveyVideoEqualTo(String value) {
            addCriterion("survey_video =", value, "surveyVideo");
            return (Criteria) this;
        }

        public Criteria andSurveyVideoNotEqualTo(String value) {
            addCriterion("survey_video <>", value, "surveyVideo");
            return (Criteria) this;
        }

        public Criteria andSurveyVideoGreaterThan(String value) {
            addCriterion("survey_video >", value, "surveyVideo");
            return (Criteria) this;
        }

        public Criteria andSurveyVideoGreaterThanOrEqualTo(String value) {
            addCriterion("survey_video >=", value, "surveyVideo");
            return (Criteria) this;
        }

        public Criteria andSurveyVideoLessThan(String value) {
            addCriterion("survey_video <", value, "surveyVideo");
            return (Criteria) this;
        }

        public Criteria andSurveyVideoLessThanOrEqualTo(String value) {
            addCriterion("survey_video <=", value, "surveyVideo");
            return (Criteria) this;
        }

        public Criteria andSurveyVideoLike(String value) {
            addCriterion("survey_video like", value, "surveyVideo");
            return (Criteria) this;
        }

        public Criteria andSurveyVideoNotLike(String value) {
            addCriterion("survey_video not like", value, "surveyVideo");
            return (Criteria) this;
        }

        public Criteria andSurveyVideoIn(List<String> values) {
            addCriterion("survey_video in", values, "surveyVideo");
            return (Criteria) this;
        }

        public Criteria andSurveyVideoNotIn(List<String> values) {
            addCriterion("survey_video not in", values, "surveyVideo");
            return (Criteria) this;
        }

        public Criteria andSurveyVideoBetween(String value1, String value2) {
            addCriterion("survey_video between", value1, value2, "surveyVideo");
            return (Criteria) this;
        }

        public Criteria andSurveyVideoNotBetween(String value1, String value2) {
            addCriterion("survey_video not between", value1, value2, "surveyVideo");
            return (Criteria) this;
        }

        public Criteria andLocationPictureIsNull() {
            addCriterion("location_picture is null");
            return (Criteria) this;
        }

        public Criteria andLocationPictureIsNotNull() {
            addCriterion("location_picture is not null");
            return (Criteria) this;
        }

        public Criteria andLocationPictureEqualTo(String value) {
            addCriterion("location_picture =", value, "locationPicture");
            return (Criteria) this;
        }

        public Criteria andLocationPictureNotEqualTo(String value) {
            addCriterion("location_picture <>", value, "locationPicture");
            return (Criteria) this;
        }

        public Criteria andLocationPictureGreaterThan(String value) {
            addCriterion("location_picture >", value, "locationPicture");
            return (Criteria) this;
        }

        public Criteria andLocationPictureGreaterThanOrEqualTo(String value) {
            addCriterion("location_picture >=", value, "locationPicture");
            return (Criteria) this;
        }

        public Criteria andLocationPictureLessThan(String value) {
            addCriterion("location_picture <", value, "locationPicture");
            return (Criteria) this;
        }

        public Criteria andLocationPictureLessThanOrEqualTo(String value) {
            addCriterion("location_picture <=", value, "locationPicture");
            return (Criteria) this;
        }

        public Criteria andLocationPictureLike(String value) {
            addCriterion("location_picture like", value, "locationPicture");
            return (Criteria) this;
        }

        public Criteria andLocationPictureNotLike(String value) {
            addCriterion("location_picture not like", value, "locationPicture");
            return (Criteria) this;
        }

        public Criteria andLocationPictureIn(List<String> values) {
            addCriterion("location_picture in", values, "locationPicture");
            return (Criteria) this;
        }

        public Criteria andLocationPictureNotIn(List<String> values) {
            addCriterion("location_picture not in", values, "locationPicture");
            return (Criteria) this;
        }

        public Criteria andLocationPictureBetween(String value1, String value2) {
            addCriterion("location_picture between", value1, value2, "locationPicture");
            return (Criteria) this;
        }

        public Criteria andLocationPictureNotBetween(String value1, String value2) {
            addCriterion("location_picture not between", value1, value2, "locationPicture");
            return (Criteria) this;
        }

        public Criteria andSurveyLocaltionIsNull() {
            addCriterion("survey_localtion is null");
            return (Criteria) this;
        }

        public Criteria andSurveyLocaltionIsNotNull() {
            addCriterion("survey_localtion is not null");
            return (Criteria) this;
        }

        public Criteria andSurveyLocaltionEqualTo(String value) {
            addCriterion("survey_localtion =", value, "surveyLocaltion");
            return (Criteria) this;
        }

        public Criteria andSurveyLocaltionNotEqualTo(String value) {
            addCriterion("survey_localtion <>", value, "surveyLocaltion");
            return (Criteria) this;
        }

        public Criteria andSurveyLocaltionGreaterThan(String value) {
            addCriterion("survey_localtion >", value, "surveyLocaltion");
            return (Criteria) this;
        }

        public Criteria andSurveyLocaltionGreaterThanOrEqualTo(String value) {
            addCriterion("survey_localtion >=", value, "surveyLocaltion");
            return (Criteria) this;
        }

        public Criteria andSurveyLocaltionLessThan(String value) {
            addCriterion("survey_localtion <", value, "surveyLocaltion");
            return (Criteria) this;
        }

        public Criteria andSurveyLocaltionLessThanOrEqualTo(String value) {
            addCriterion("survey_localtion <=", value, "surveyLocaltion");
            return (Criteria) this;
        }

        public Criteria andSurveyLocaltionLike(String value) {
            addCriterion("survey_localtion like", value, "surveyLocaltion");
            return (Criteria) this;
        }

        public Criteria andSurveyLocaltionNotLike(String value) {
            addCriterion("survey_localtion not like", value, "surveyLocaltion");
            return (Criteria) this;
        }

        public Criteria andSurveyLocaltionIn(List<String> values) {
            addCriterion("survey_localtion in", values, "surveyLocaltion");
            return (Criteria) this;
        }

        public Criteria andSurveyLocaltionNotIn(List<String> values) {
            addCriterion("survey_localtion not in", values, "surveyLocaltion");
            return (Criteria) this;
        }

        public Criteria andSurveyLocaltionBetween(String value1, String value2) {
            addCriterion("survey_localtion between", value1, value2, "surveyLocaltion");
            return (Criteria) this;
        }

        public Criteria andSurveyLocaltionNotBetween(String value1, String value2) {
            addCriterion("survey_localtion not between", value1, value2, "surveyLocaltion");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardIsNull() {
            addCriterion("correlation_card is null");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardIsNotNull() {
            addCriterion("correlation_card is not null");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardEqualTo(String value) {
            addCriterion("correlation_card =", value, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardNotEqualTo(String value) {
            addCriterion("correlation_card <>", value, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardGreaterThan(String value) {
            addCriterion("correlation_card >", value, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardGreaterThanOrEqualTo(String value) {
            addCriterion("correlation_card >=", value, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardLessThan(String value) {
            addCriterion("correlation_card <", value, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardLessThanOrEqualTo(String value) {
            addCriterion("correlation_card <=", value, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardLike(String value) {
            addCriterion("correlation_card like", value, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardNotLike(String value) {
            addCriterion("correlation_card not like", value, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardIn(List<String> values) {
            addCriterion("correlation_card in", values, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardNotIn(List<String> values) {
            addCriterion("correlation_card not in", values, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardBetween(String value1, String value2) {
            addCriterion("correlation_card between", value1, value2, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andCorrelationCardNotBetween(String value1, String value2) {
            addCriterion("correlation_card not between", value1, value2, "correlationCard");
            return (Criteria) this;
        }

        public Criteria andHouseNameIsNull() {
            addCriterion("house_name is null");
            return (Criteria) this;
        }

        public Criteria andHouseNameIsNotNull() {
            addCriterion("house_name is not null");
            return (Criteria) this;
        }

        public Criteria andHouseNameEqualTo(String value) {
            addCriterion("house_name =", value, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameNotEqualTo(String value) {
            addCriterion("house_name <>", value, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameGreaterThan(String value) {
            addCriterion("house_name >", value, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameGreaterThanOrEqualTo(String value) {
            addCriterion("house_name >=", value, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameLessThan(String value) {
            addCriterion("house_name <", value, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameLessThanOrEqualTo(String value) {
            addCriterion("house_name <=", value, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameLike(String value) {
            addCriterion("house_name like", value, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameNotLike(String value) {
            addCriterion("house_name not like", value, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameIn(List<String> values) {
            addCriterion("house_name in", values, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameNotIn(List<String> values) {
            addCriterion("house_name not in", values, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameBetween(String value1, String value2) {
            addCriterion("house_name between", value1, value2, "houseName");
            return (Criteria) this;
        }

        public Criteria andHouseNameNotBetween(String value1, String value2) {
            addCriterion("house_name not between", value1, value2, "houseName");
            return (Criteria) this;
        }

        public Criteria andDynamicFormIdIsNull() {
            addCriterion("dynamic_form_id is null");
            return (Criteria) this;
        }

        public Criteria andDynamicFormIdIsNotNull() {
            addCriterion("dynamic_form_id is not null");
            return (Criteria) this;
        }

        public Criteria andDynamicFormIdEqualTo(Integer value) {
            addCriterion("dynamic_form_id =", value, "dynamicFormId");
            return (Criteria) this;
        }

        public Criteria andDynamicFormIdNotEqualTo(Integer value) {
            addCriterion("dynamic_form_id <>", value, "dynamicFormId");
            return (Criteria) this;
        }

        public Criteria andDynamicFormIdGreaterThan(Integer value) {
            addCriterion("dynamic_form_id >", value, "dynamicFormId");
            return (Criteria) this;
        }

        public Criteria andDynamicFormIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dynamic_form_id >=", value, "dynamicFormId");
            return (Criteria) this;
        }

        public Criteria andDynamicFormIdLessThan(Integer value) {
            addCriterion("dynamic_form_id <", value, "dynamicFormId");
            return (Criteria) this;
        }

        public Criteria andDynamicFormIdLessThanOrEqualTo(Integer value) {
            addCriterion("dynamic_form_id <=", value, "dynamicFormId");
            return (Criteria) this;
        }

        public Criteria andDynamicFormIdIn(List<Integer> values) {
            addCriterion("dynamic_form_id in", values, "dynamicFormId");
            return (Criteria) this;
        }

        public Criteria andDynamicFormIdNotIn(List<Integer> values) {
            addCriterion("dynamic_form_id not in", values, "dynamicFormId");
            return (Criteria) this;
        }

        public Criteria andDynamicFormIdBetween(Integer value1, Integer value2) {
            addCriterion("dynamic_form_id between", value1, value2, "dynamicFormId");
            return (Criteria) this;
        }

        public Criteria andDynamicFormIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dynamic_form_id not between", value1, value2, "dynamicFormId");
            return (Criteria) this;
        }

        public Criteria andDynamicTableIdIsNull() {
            addCriterion("dynamic_table_id is null");
            return (Criteria) this;
        }

        public Criteria andDynamicTableIdIsNotNull() {
            addCriterion("dynamic_table_id is not null");
            return (Criteria) this;
        }

        public Criteria andDynamicTableIdEqualTo(Integer value) {
            addCriterion("dynamic_table_id =", value, "dynamicTableId");
            return (Criteria) this;
        }

        public Criteria andDynamicTableIdNotEqualTo(Integer value) {
            addCriterion("dynamic_table_id <>", value, "dynamicTableId");
            return (Criteria) this;
        }

        public Criteria andDynamicTableIdGreaterThan(Integer value) {
            addCriterion("dynamic_table_id >", value, "dynamicTableId");
            return (Criteria) this;
        }

        public Criteria andDynamicTableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dynamic_table_id >=", value, "dynamicTableId");
            return (Criteria) this;
        }

        public Criteria andDynamicTableIdLessThan(Integer value) {
            addCriterion("dynamic_table_id <", value, "dynamicTableId");
            return (Criteria) this;
        }

        public Criteria andDynamicTableIdLessThanOrEqualTo(Integer value) {
            addCriterion("dynamic_table_id <=", value, "dynamicTableId");
            return (Criteria) this;
        }

        public Criteria andDynamicTableIdIn(List<Integer> values) {
            addCriterion("dynamic_table_id in", values, "dynamicTableId");
            return (Criteria) this;
        }

        public Criteria andDynamicTableIdNotIn(List<Integer> values) {
            addCriterion("dynamic_table_id not in", values, "dynamicTableId");
            return (Criteria) this;
        }

        public Criteria andDynamicTableIdBetween(Integer value1, Integer value2) {
            addCriterion("dynamic_table_id between", value1, value2, "dynamicTableId");
            return (Criteria) this;
        }

        public Criteria andDynamicTableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dynamic_table_id not between", value1, value2, "dynamicTableId");
            return (Criteria) this;
        }

        public Criteria andDynamicTableNameIsNull() {
            addCriterion("dynamic_table_name is null");
            return (Criteria) this;
        }

        public Criteria andDynamicTableNameIsNotNull() {
            addCriterion("dynamic_table_name is not null");
            return (Criteria) this;
        }

        public Criteria andDynamicTableNameEqualTo(String value) {
            addCriterion("dynamic_table_name =", value, "dynamicTableName");
            return (Criteria) this;
        }

        public Criteria andDynamicTableNameNotEqualTo(String value) {
            addCriterion("dynamic_table_name <>", value, "dynamicTableName");
            return (Criteria) this;
        }

        public Criteria andDynamicTableNameGreaterThan(String value) {
            addCriterion("dynamic_table_name >", value, "dynamicTableName");
            return (Criteria) this;
        }

        public Criteria andDynamicTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("dynamic_table_name >=", value, "dynamicTableName");
            return (Criteria) this;
        }

        public Criteria andDynamicTableNameLessThan(String value) {
            addCriterion("dynamic_table_name <", value, "dynamicTableName");
            return (Criteria) this;
        }

        public Criteria andDynamicTableNameLessThanOrEqualTo(String value) {
            addCriterion("dynamic_table_name <=", value, "dynamicTableName");
            return (Criteria) this;
        }

        public Criteria andDynamicTableNameLike(String value) {
            addCriterion("dynamic_table_name like", value, "dynamicTableName");
            return (Criteria) this;
        }

        public Criteria andDynamicTableNameNotLike(String value) {
            addCriterion("dynamic_table_name not like", value, "dynamicTableName");
            return (Criteria) this;
        }

        public Criteria andDynamicTableNameIn(List<String> values) {
            addCriterion("dynamic_table_name in", values, "dynamicTableName");
            return (Criteria) this;
        }

        public Criteria andDynamicTableNameNotIn(List<String> values) {
            addCriterion("dynamic_table_name not in", values, "dynamicTableName");
            return (Criteria) this;
        }

        public Criteria andDynamicTableNameBetween(String value1, String value2) {
            addCriterion("dynamic_table_name between", value1, value2, "dynamicTableName");
            return (Criteria) this;
        }

        public Criteria andDynamicTableNameNotBetween(String value1, String value2) {
            addCriterion("dynamic_table_name not between", value1, value2, "dynamicTableName");
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

        public Criteria andDefaultLocaltionIsNull() {
            addCriterion("default_localtion is null");
            return (Criteria) this;
        }

        public Criteria andDefaultLocaltionIsNotNull() {
            addCriterion("default_localtion is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultLocaltionEqualTo(String value) {
            addCriterion("default_localtion =", value, "defaultLocaltion");
            return (Criteria) this;
        }

        public Criteria andDefaultLocaltionNotEqualTo(String value) {
            addCriterion("default_localtion <>", value, "defaultLocaltion");
            return (Criteria) this;
        }

        public Criteria andDefaultLocaltionGreaterThan(String value) {
            addCriterion("default_localtion >", value, "defaultLocaltion");
            return (Criteria) this;
        }

        public Criteria andDefaultLocaltionGreaterThanOrEqualTo(String value) {
            addCriterion("default_localtion >=", value, "defaultLocaltion");
            return (Criteria) this;
        }

        public Criteria andDefaultLocaltionLessThan(String value) {
            addCriterion("default_localtion <", value, "defaultLocaltion");
            return (Criteria) this;
        }

        public Criteria andDefaultLocaltionLessThanOrEqualTo(String value) {
            addCriterion("default_localtion <=", value, "defaultLocaltion");
            return (Criteria) this;
        }

        public Criteria andDefaultLocaltionLike(String value) {
            addCriterion("default_localtion like", value, "defaultLocaltion");
            return (Criteria) this;
        }

        public Criteria andDefaultLocaltionNotLike(String value) {
            addCriterion("default_localtion not like", value, "defaultLocaltion");
            return (Criteria) this;
        }

        public Criteria andDefaultLocaltionIn(List<String> values) {
            addCriterion("default_localtion in", values, "defaultLocaltion");
            return (Criteria) this;
        }

        public Criteria andDefaultLocaltionNotIn(List<String> values) {
            addCriterion("default_localtion not in", values, "defaultLocaltion");
            return (Criteria) this;
        }

        public Criteria andDefaultLocaltionBetween(String value1, String value2) {
            addCriterion("default_localtion between", value1, value2, "defaultLocaltion");
            return (Criteria) this;
        }

        public Criteria andDefaultLocaltionNotBetween(String value1, String value2) {
            addCriterion("default_localtion not between", value1, value2, "defaultLocaltion");
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