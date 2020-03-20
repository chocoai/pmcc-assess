package com.copower.pmcc.assess.dal.basis.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectXlxReportIndividualExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectXlxReportIndividualExample() {
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

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andEntrustedUnitIsNull() {
            addCriterion("entrusted_unit is null");
            return (Criteria) this;
        }

        public Criteria andEntrustedUnitIsNotNull() {
            addCriterion("entrusted_unit is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustedUnitEqualTo(String value) {
            addCriterion("entrusted_unit =", value, "entrustedUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustedUnitNotEqualTo(String value) {
            addCriterion("entrusted_unit <>", value, "entrustedUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustedUnitGreaterThan(String value) {
            addCriterion("entrusted_unit >", value, "entrustedUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustedUnitGreaterThanOrEqualTo(String value) {
            addCriterion("entrusted_unit >=", value, "entrustedUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustedUnitLessThan(String value) {
            addCriterion("entrusted_unit <", value, "entrustedUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustedUnitLessThanOrEqualTo(String value) {
            addCriterion("entrusted_unit <=", value, "entrustedUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustedUnitLike(String value) {
            addCriterion("entrusted_unit like", value, "entrustedUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustedUnitNotLike(String value) {
            addCriterion("entrusted_unit not like", value, "entrustedUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustedUnitIn(List<String> values) {
            addCriterion("entrusted_unit in", values, "entrustedUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustedUnitNotIn(List<String> values) {
            addCriterion("entrusted_unit not in", values, "entrustedUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustedUnitBetween(String value1, String value2) {
            addCriterion("entrusted_unit between", value1, value2, "entrustedUnit");
            return (Criteria) this;
        }

        public Criteria andEntrustedUnitNotBetween(String value1, String value2) {
            addCriterion("entrusted_unit not between", value1, value2, "entrustedUnit");
            return (Criteria) this;
        }

        public Criteria andReportScoreIsNull() {
            addCriterion("report_score is null");
            return (Criteria) this;
        }

        public Criteria andReportScoreIsNotNull() {
            addCriterion("report_score is not null");
            return (Criteria) this;
        }

        public Criteria andReportScoreEqualTo(Integer value) {
            addCriterion("report_score =", value, "reportScore");
            return (Criteria) this;
        }

        public Criteria andReportScoreNotEqualTo(Integer value) {
            addCriterion("report_score <>", value, "reportScore");
            return (Criteria) this;
        }

        public Criteria andReportScoreGreaterThan(Integer value) {
            addCriterion("report_score >", value, "reportScore");
            return (Criteria) this;
        }

        public Criteria andReportScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("report_score >=", value, "reportScore");
            return (Criteria) this;
        }

        public Criteria andReportScoreLessThan(Integer value) {
            addCriterion("report_score <", value, "reportScore");
            return (Criteria) this;
        }

        public Criteria andReportScoreLessThanOrEqualTo(Integer value) {
            addCriterion("report_score <=", value, "reportScore");
            return (Criteria) this;
        }

        public Criteria andReportScoreIn(List<Integer> values) {
            addCriterion("report_score in", values, "reportScore");
            return (Criteria) this;
        }

        public Criteria andReportScoreNotIn(List<Integer> values) {
            addCriterion("report_score not in", values, "reportScore");
            return (Criteria) this;
        }

        public Criteria andReportScoreBetween(Integer value1, Integer value2) {
            addCriterion("report_score between", value1, value2, "reportScore");
            return (Criteria) this;
        }

        public Criteria andReportScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("report_score not between", value1, value2, "reportScore");
            return (Criteria) this;
        }

        public Criteria andReportModificationDateIsNull() {
            addCriterion("report_modification_date is null");
            return (Criteria) this;
        }

        public Criteria andReportModificationDateIsNotNull() {
            addCriterion("report_modification_date is not null");
            return (Criteria) this;
        }

        public Criteria andReportModificationDateEqualTo(Date value) {
            addCriterion("report_modification_date =", value, "reportModificationDate");
            return (Criteria) this;
        }

        public Criteria andReportModificationDateNotEqualTo(Date value) {
            addCriterion("report_modification_date <>", value, "reportModificationDate");
            return (Criteria) this;
        }

        public Criteria andReportModificationDateGreaterThan(Date value) {
            addCriterion("report_modification_date >", value, "reportModificationDate");
            return (Criteria) this;
        }

        public Criteria andReportModificationDateGreaterThanOrEqualTo(Date value) {
            addCriterion("report_modification_date >=", value, "reportModificationDate");
            return (Criteria) this;
        }

        public Criteria andReportModificationDateLessThan(Date value) {
            addCriterion("report_modification_date <", value, "reportModificationDate");
            return (Criteria) this;
        }

        public Criteria andReportModificationDateLessThanOrEqualTo(Date value) {
            addCriterion("report_modification_date <=", value, "reportModificationDate");
            return (Criteria) this;
        }

        public Criteria andReportModificationDateIn(List<Date> values) {
            addCriterion("report_modification_date in", values, "reportModificationDate");
            return (Criteria) this;
        }

        public Criteria andReportModificationDateNotIn(List<Date> values) {
            addCriterion("report_modification_date not in", values, "reportModificationDate");
            return (Criteria) this;
        }

        public Criteria andReportModificationDateBetween(Date value1, Date value2) {
            addCriterion("report_modification_date between", value1, value2, "reportModificationDate");
            return (Criteria) this;
        }

        public Criteria andReportModificationDateNotBetween(Date value1, Date value2) {
            addCriterion("report_modification_date not between", value1, value2, "reportModificationDate");
            return (Criteria) this;
        }

        public Criteria andReportBindingDateIsNull() {
            addCriterion("report_binding_date is null");
            return (Criteria) this;
        }

        public Criteria andReportBindingDateIsNotNull() {
            addCriterion("report_binding_date is not null");
            return (Criteria) this;
        }

        public Criteria andReportBindingDateEqualTo(Date value) {
            addCriterion("report_binding_date =", value, "reportBindingDate");
            return (Criteria) this;
        }

        public Criteria andReportBindingDateNotEqualTo(Date value) {
            addCriterion("report_binding_date <>", value, "reportBindingDate");
            return (Criteria) this;
        }

        public Criteria andReportBindingDateGreaterThan(Date value) {
            addCriterion("report_binding_date >", value, "reportBindingDate");
            return (Criteria) this;
        }

        public Criteria andReportBindingDateGreaterThanOrEqualTo(Date value) {
            addCriterion("report_binding_date >=", value, "reportBindingDate");
            return (Criteria) this;
        }

        public Criteria andReportBindingDateLessThan(Date value) {
            addCriterion("report_binding_date <", value, "reportBindingDate");
            return (Criteria) this;
        }

        public Criteria andReportBindingDateLessThanOrEqualTo(Date value) {
            addCriterion("report_binding_date <=", value, "reportBindingDate");
            return (Criteria) this;
        }

        public Criteria andReportBindingDateIn(List<Date> values) {
            addCriterion("report_binding_date in", values, "reportBindingDate");
            return (Criteria) this;
        }

        public Criteria andReportBindingDateNotIn(List<Date> values) {
            addCriterion("report_binding_date not in", values, "reportBindingDate");
            return (Criteria) this;
        }

        public Criteria andReportBindingDateBetween(Date value1, Date value2) {
            addCriterion("report_binding_date between", value1, value2, "reportBindingDate");
            return (Criteria) this;
        }

        public Criteria andReportBindingDateNotBetween(Date value1, Date value2) {
            addCriterion("report_binding_date not between", value1, value2, "reportBindingDate");
            return (Criteria) this;
        }

        public Criteria andFilingDateIsNull() {
            addCriterion("filing_date is null");
            return (Criteria) this;
        }

        public Criteria andFilingDateIsNotNull() {
            addCriterion("filing_date is not null");
            return (Criteria) this;
        }

        public Criteria andFilingDateEqualTo(Date value) {
            addCriterion("filing_date =", value, "filingDate");
            return (Criteria) this;
        }

        public Criteria andFilingDateNotEqualTo(Date value) {
            addCriterion("filing_date <>", value, "filingDate");
            return (Criteria) this;
        }

        public Criteria andFilingDateGreaterThan(Date value) {
            addCriterion("filing_date >", value, "filingDate");
            return (Criteria) this;
        }

        public Criteria andFilingDateGreaterThanOrEqualTo(Date value) {
            addCriterion("filing_date >=", value, "filingDate");
            return (Criteria) this;
        }

        public Criteria andFilingDateLessThan(Date value) {
            addCriterion("filing_date <", value, "filingDate");
            return (Criteria) this;
        }

        public Criteria andFilingDateLessThanOrEqualTo(Date value) {
            addCriterion("filing_date <=", value, "filingDate");
            return (Criteria) this;
        }

        public Criteria andFilingDateIn(List<Date> values) {
            addCriterion("filing_date in", values, "filingDate");
            return (Criteria) this;
        }

        public Criteria andFilingDateNotIn(List<Date> values) {
            addCriterion("filing_date not in", values, "filingDate");
            return (Criteria) this;
        }

        public Criteria andFilingDateBetween(Date value1, Date value2) {
            addCriterion("filing_date between", value1, value2, "filingDate");
            return (Criteria) this;
        }

        public Criteria andFilingDateNotBetween(Date value1, Date value2) {
            addCriterion("filing_date not between", value1, value2, "filingDate");
            return (Criteria) this;
        }

        public Criteria andLandPlotsNumberIsNull() {
            addCriterion("land_plots_number is null");
            return (Criteria) this;
        }

        public Criteria andLandPlotsNumberIsNotNull() {
            addCriterion("land_plots_number is not null");
            return (Criteria) this;
        }

        public Criteria andLandPlotsNumberEqualTo(BigDecimal value) {
            addCriterion("land_plots_number =", value, "landPlotsNumber");
            return (Criteria) this;
        }

        public Criteria andLandPlotsNumberNotEqualTo(BigDecimal value) {
            addCriterion("land_plots_number <>", value, "landPlotsNumber");
            return (Criteria) this;
        }

        public Criteria andLandPlotsNumberGreaterThan(BigDecimal value) {
            addCriterion("land_plots_number >", value, "landPlotsNumber");
            return (Criteria) this;
        }

        public Criteria andLandPlotsNumberGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_plots_number >=", value, "landPlotsNumber");
            return (Criteria) this;
        }

        public Criteria andLandPlotsNumberLessThan(BigDecimal value) {
            addCriterion("land_plots_number <", value, "landPlotsNumber");
            return (Criteria) this;
        }

        public Criteria andLandPlotsNumberLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_plots_number <=", value, "landPlotsNumber");
            return (Criteria) this;
        }

        public Criteria andLandPlotsNumberIn(List<BigDecimal> values) {
            addCriterion("land_plots_number in", values, "landPlotsNumber");
            return (Criteria) this;
        }

        public Criteria andLandPlotsNumberNotIn(List<BigDecimal> values) {
            addCriterion("land_plots_number not in", values, "landPlotsNumber");
            return (Criteria) this;
        }

        public Criteria andLandPlotsNumberBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_plots_number between", value1, value2, "landPlotsNumber");
            return (Criteria) this;
        }

        public Criteria andLandPlotsNumberNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_plots_number not between", value1, value2, "landPlotsNumber");
            return (Criteria) this;
        }

        public Criteria andLandPlotsLocationIsNull() {
            addCriterion("land_plots_location is null");
            return (Criteria) this;
        }

        public Criteria andLandPlotsLocationIsNotNull() {
            addCriterion("land_plots_location is not null");
            return (Criteria) this;
        }

        public Criteria andLandPlotsLocationEqualTo(String value) {
            addCriterion("land_plots_location =", value, "landPlotsLocation");
            return (Criteria) this;
        }

        public Criteria andLandPlotsLocationNotEqualTo(String value) {
            addCriterion("land_plots_location <>", value, "landPlotsLocation");
            return (Criteria) this;
        }

        public Criteria andLandPlotsLocationGreaterThan(String value) {
            addCriterion("land_plots_location >", value, "landPlotsLocation");
            return (Criteria) this;
        }

        public Criteria andLandPlotsLocationGreaterThanOrEqualTo(String value) {
            addCriterion("land_plots_location >=", value, "landPlotsLocation");
            return (Criteria) this;
        }

        public Criteria andLandPlotsLocationLessThan(String value) {
            addCriterion("land_plots_location <", value, "landPlotsLocation");
            return (Criteria) this;
        }

        public Criteria andLandPlotsLocationLessThanOrEqualTo(String value) {
            addCriterion("land_plots_location <=", value, "landPlotsLocation");
            return (Criteria) this;
        }

        public Criteria andLandPlotsLocationLike(String value) {
            addCriterion("land_plots_location like", value, "landPlotsLocation");
            return (Criteria) this;
        }

        public Criteria andLandPlotsLocationNotLike(String value) {
            addCriterion("land_plots_location not like", value, "landPlotsLocation");
            return (Criteria) this;
        }

        public Criteria andLandPlotsLocationIn(List<String> values) {
            addCriterion("land_plots_location in", values, "landPlotsLocation");
            return (Criteria) this;
        }

        public Criteria andLandPlotsLocationNotIn(List<String> values) {
            addCriterion("land_plots_location not in", values, "landPlotsLocation");
            return (Criteria) this;
        }

        public Criteria andLandPlotsLocationBetween(String value1, String value2) {
            addCriterion("land_plots_location between", value1, value2, "landPlotsLocation");
            return (Criteria) this;
        }

        public Criteria andLandPlotsLocationNotBetween(String value1, String value2) {
            addCriterion("land_plots_location not between", value1, value2, "landPlotsLocation");
            return (Criteria) this;
        }

        public Criteria andEntrustedBankIsNull() {
            addCriterion("entrusted_bank is null");
            return (Criteria) this;
        }

        public Criteria andEntrustedBankIsNotNull() {
            addCriterion("entrusted_bank is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustedBankEqualTo(String value) {
            addCriterion("entrusted_bank =", value, "entrustedBank");
            return (Criteria) this;
        }

        public Criteria andEntrustedBankNotEqualTo(String value) {
            addCriterion("entrusted_bank <>", value, "entrustedBank");
            return (Criteria) this;
        }

        public Criteria andEntrustedBankGreaterThan(String value) {
            addCriterion("entrusted_bank >", value, "entrustedBank");
            return (Criteria) this;
        }

        public Criteria andEntrustedBankGreaterThanOrEqualTo(String value) {
            addCriterion("entrusted_bank >=", value, "entrustedBank");
            return (Criteria) this;
        }

        public Criteria andEntrustedBankLessThan(String value) {
            addCriterion("entrusted_bank <", value, "entrustedBank");
            return (Criteria) this;
        }

        public Criteria andEntrustedBankLessThanOrEqualTo(String value) {
            addCriterion("entrusted_bank <=", value, "entrustedBank");
            return (Criteria) this;
        }

        public Criteria andEntrustedBankLike(String value) {
            addCriterion("entrusted_bank like", value, "entrustedBank");
            return (Criteria) this;
        }

        public Criteria andEntrustedBankNotLike(String value) {
            addCriterion("entrusted_bank not like", value, "entrustedBank");
            return (Criteria) this;
        }

        public Criteria andEntrustedBankIn(List<String> values) {
            addCriterion("entrusted_bank in", values, "entrustedBank");
            return (Criteria) this;
        }

        public Criteria andEntrustedBankNotIn(List<String> values) {
            addCriterion("entrusted_bank not in", values, "entrustedBank");
            return (Criteria) this;
        }

        public Criteria andEntrustedBankBetween(String value1, String value2) {
            addCriterion("entrusted_bank between", value1, value2, "entrustedBank");
            return (Criteria) this;
        }

        public Criteria andEntrustedBankNotBetween(String value1, String value2) {
            addCriterion("entrusted_bank not between", value1, value2, "entrustedBank");
            return (Criteria) this;
        }

        public Criteria andPhonePrincipalIsNull() {
            addCriterion("phone_principal is null");
            return (Criteria) this;
        }

        public Criteria andPhonePrincipalIsNotNull() {
            addCriterion("phone_principal is not null");
            return (Criteria) this;
        }

        public Criteria andPhonePrincipalEqualTo(String value) {
            addCriterion("phone_principal =", value, "phonePrincipal");
            return (Criteria) this;
        }

        public Criteria andPhonePrincipalNotEqualTo(String value) {
            addCriterion("phone_principal <>", value, "phonePrincipal");
            return (Criteria) this;
        }

        public Criteria andPhonePrincipalGreaterThan(String value) {
            addCriterion("phone_principal >", value, "phonePrincipal");
            return (Criteria) this;
        }

        public Criteria andPhonePrincipalGreaterThanOrEqualTo(String value) {
            addCriterion("phone_principal >=", value, "phonePrincipal");
            return (Criteria) this;
        }

        public Criteria andPhonePrincipalLessThan(String value) {
            addCriterion("phone_principal <", value, "phonePrincipal");
            return (Criteria) this;
        }

        public Criteria andPhonePrincipalLessThanOrEqualTo(String value) {
            addCriterion("phone_principal <=", value, "phonePrincipal");
            return (Criteria) this;
        }

        public Criteria andPhonePrincipalLike(String value) {
            addCriterion("phone_principal like", value, "phonePrincipal");
            return (Criteria) this;
        }

        public Criteria andPhonePrincipalNotLike(String value) {
            addCriterion("phone_principal not like", value, "phonePrincipal");
            return (Criteria) this;
        }

        public Criteria andPhonePrincipalIn(List<String> values) {
            addCriterion("phone_principal in", values, "phonePrincipal");
            return (Criteria) this;
        }

        public Criteria andPhonePrincipalNotIn(List<String> values) {
            addCriterion("phone_principal not in", values, "phonePrincipal");
            return (Criteria) this;
        }

        public Criteria andPhonePrincipalBetween(String value1, String value2) {
            addCriterion("phone_principal between", value1, value2, "phonePrincipal");
            return (Criteria) this;
        }

        public Criteria andPhonePrincipalNotBetween(String value1, String value2) {
            addCriterion("phone_principal not between", value1, value2, "phonePrincipal");
            return (Criteria) this;
        }

        public Criteria andAddressPrincipalIsNull() {
            addCriterion("address_principal is null");
            return (Criteria) this;
        }

        public Criteria andAddressPrincipalIsNotNull() {
            addCriterion("address_principal is not null");
            return (Criteria) this;
        }

        public Criteria andAddressPrincipalEqualTo(String value) {
            addCriterion("address_principal =", value, "addressPrincipal");
            return (Criteria) this;
        }

        public Criteria andAddressPrincipalNotEqualTo(String value) {
            addCriterion("address_principal <>", value, "addressPrincipal");
            return (Criteria) this;
        }

        public Criteria andAddressPrincipalGreaterThan(String value) {
            addCriterion("address_principal >", value, "addressPrincipal");
            return (Criteria) this;
        }

        public Criteria andAddressPrincipalGreaterThanOrEqualTo(String value) {
            addCriterion("address_principal >=", value, "addressPrincipal");
            return (Criteria) this;
        }

        public Criteria andAddressPrincipalLessThan(String value) {
            addCriterion("address_principal <", value, "addressPrincipal");
            return (Criteria) this;
        }

        public Criteria andAddressPrincipalLessThanOrEqualTo(String value) {
            addCriterion("address_principal <=", value, "addressPrincipal");
            return (Criteria) this;
        }

        public Criteria andAddressPrincipalLike(String value) {
            addCriterion("address_principal like", value, "addressPrincipal");
            return (Criteria) this;
        }

        public Criteria andAddressPrincipalNotLike(String value) {
            addCriterion("address_principal not like", value, "addressPrincipal");
            return (Criteria) this;
        }

        public Criteria andAddressPrincipalIn(List<String> values) {
            addCriterion("address_principal in", values, "addressPrincipal");
            return (Criteria) this;
        }

        public Criteria andAddressPrincipalNotIn(List<String> values) {
            addCriterion("address_principal not in", values, "addressPrincipal");
            return (Criteria) this;
        }

        public Criteria andAddressPrincipalBetween(String value1, String value2) {
            addCriterion("address_principal between", value1, value2, "addressPrincipal");
            return (Criteria) this;
        }

        public Criteria andAddressPrincipalNotBetween(String value1, String value2) {
            addCriterion("address_principal not between", value1, value2, "addressPrincipal");
            return (Criteria) this;
        }

        public Criteria andContractSigningDateIsNull() {
            addCriterion("contract_signing_date is null");
            return (Criteria) this;
        }

        public Criteria andContractSigningDateIsNotNull() {
            addCriterion("contract_signing_date is not null");
            return (Criteria) this;
        }

        public Criteria andContractSigningDateEqualTo(Date value) {
            addCriterion("contract_signing_date =", value, "contractSigningDate");
            return (Criteria) this;
        }

        public Criteria andContractSigningDateNotEqualTo(Date value) {
            addCriterion("contract_signing_date <>", value, "contractSigningDate");
            return (Criteria) this;
        }

        public Criteria andContractSigningDateGreaterThan(Date value) {
            addCriterion("contract_signing_date >", value, "contractSigningDate");
            return (Criteria) this;
        }

        public Criteria andContractSigningDateGreaterThanOrEqualTo(Date value) {
            addCriterion("contract_signing_date >=", value, "contractSigningDate");
            return (Criteria) this;
        }

        public Criteria andContractSigningDateLessThan(Date value) {
            addCriterion("contract_signing_date <", value, "contractSigningDate");
            return (Criteria) this;
        }

        public Criteria andContractSigningDateLessThanOrEqualTo(Date value) {
            addCriterion("contract_signing_date <=", value, "contractSigningDate");
            return (Criteria) this;
        }

        public Criteria andContractSigningDateIn(List<Date> values) {
            addCriterion("contract_signing_date in", values, "contractSigningDate");
            return (Criteria) this;
        }

        public Criteria andContractSigningDateNotIn(List<Date> values) {
            addCriterion("contract_signing_date not in", values, "contractSigningDate");
            return (Criteria) this;
        }

        public Criteria andContractSigningDateBetween(Date value1, Date value2) {
            addCriterion("contract_signing_date between", value1, value2, "contractSigningDate");
            return (Criteria) this;
        }

        public Criteria andContractSigningDateNotBetween(Date value1, Date value2) {
            addCriterion("contract_signing_date not between", value1, value2, "contractSigningDate");
            return (Criteria) this;
        }

        public Criteria andSignerPeopleIsNull() {
            addCriterion("signer_people is null");
            return (Criteria) this;
        }

        public Criteria andSignerPeopleIsNotNull() {
            addCriterion("signer_people is not null");
            return (Criteria) this;
        }

        public Criteria andSignerPeopleEqualTo(String value) {
            addCriterion("signer_people =", value, "signerPeople");
            return (Criteria) this;
        }

        public Criteria andSignerPeopleNotEqualTo(String value) {
            addCriterion("signer_people <>", value, "signerPeople");
            return (Criteria) this;
        }

        public Criteria andSignerPeopleGreaterThan(String value) {
            addCriterion("signer_people >", value, "signerPeople");
            return (Criteria) this;
        }

        public Criteria andSignerPeopleGreaterThanOrEqualTo(String value) {
            addCriterion("signer_people >=", value, "signerPeople");
            return (Criteria) this;
        }

        public Criteria andSignerPeopleLessThan(String value) {
            addCriterion("signer_people <", value, "signerPeople");
            return (Criteria) this;
        }

        public Criteria andSignerPeopleLessThanOrEqualTo(String value) {
            addCriterion("signer_people <=", value, "signerPeople");
            return (Criteria) this;
        }

        public Criteria andSignerPeopleLike(String value) {
            addCriterion("signer_people like", value, "signerPeople");
            return (Criteria) this;
        }

        public Criteria andSignerPeopleNotLike(String value) {
            addCriterion("signer_people not like", value, "signerPeople");
            return (Criteria) this;
        }

        public Criteria andSignerPeopleIn(List<String> values) {
            addCriterion("signer_people in", values, "signerPeople");
            return (Criteria) this;
        }

        public Criteria andSignerPeopleNotIn(List<String> values) {
            addCriterion("signer_people not in", values, "signerPeople");
            return (Criteria) this;
        }

        public Criteria andSignerPeopleBetween(String value1, String value2) {
            addCriterion("signer_people between", value1, value2, "signerPeople");
            return (Criteria) this;
        }

        public Criteria andSignerPeopleNotBetween(String value1, String value2) {
            addCriterion("signer_people not between", value1, value2, "signerPeople");
            return (Criteria) this;
        }

        public Criteria andFloorAreaIsNull() {
            addCriterion("floor_area is null");
            return (Criteria) this;
        }

        public Criteria andFloorAreaIsNotNull() {
            addCriterion("floor_area is not null");
            return (Criteria) this;
        }

        public Criteria andFloorAreaEqualTo(BigDecimal value) {
            addCriterion("floor_area =", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotEqualTo(BigDecimal value) {
            addCriterion("floor_area <>", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaGreaterThan(BigDecimal value) {
            addCriterion("floor_area >", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_area >=", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaLessThan(BigDecimal value) {
            addCriterion("floor_area <", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_area <=", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaIn(List<BigDecimal> values) {
            addCriterion("floor_area in", values, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotIn(List<BigDecimal> values) {
            addCriterion("floor_area not in", values, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_area between", value1, value2, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_area not between", value1, value2, "floorArea");
            return (Criteria) this;
        }

        public Criteria andActualChargesIsNull() {
            addCriterion("actual_charges is null");
            return (Criteria) this;
        }

        public Criteria andActualChargesIsNotNull() {
            addCriterion("actual_charges is not null");
            return (Criteria) this;
        }

        public Criteria andActualChargesEqualTo(String value) {
            addCriterion("actual_charges =", value, "actualCharges");
            return (Criteria) this;
        }

        public Criteria andActualChargesNotEqualTo(String value) {
            addCriterion("actual_charges <>", value, "actualCharges");
            return (Criteria) this;
        }

        public Criteria andActualChargesGreaterThan(String value) {
            addCriterion("actual_charges >", value, "actualCharges");
            return (Criteria) this;
        }

        public Criteria andActualChargesGreaterThanOrEqualTo(String value) {
            addCriterion("actual_charges >=", value, "actualCharges");
            return (Criteria) this;
        }

        public Criteria andActualChargesLessThan(String value) {
            addCriterion("actual_charges <", value, "actualCharges");
            return (Criteria) this;
        }

        public Criteria andActualChargesLessThanOrEqualTo(String value) {
            addCriterion("actual_charges <=", value, "actualCharges");
            return (Criteria) this;
        }

        public Criteria andActualChargesLike(String value) {
            addCriterion("actual_charges like", value, "actualCharges");
            return (Criteria) this;
        }

        public Criteria andActualChargesNotLike(String value) {
            addCriterion("actual_charges not like", value, "actualCharges");
            return (Criteria) this;
        }

        public Criteria andActualChargesIn(List<String> values) {
            addCriterion("actual_charges in", values, "actualCharges");
            return (Criteria) this;
        }

        public Criteria andActualChargesNotIn(List<String> values) {
            addCriterion("actual_charges not in", values, "actualCharges");
            return (Criteria) this;
        }

        public Criteria andActualChargesBetween(String value1, String value2) {
            addCriterion("actual_charges between", value1, value2, "actualCharges");
            return (Criteria) this;
        }

        public Criteria andActualChargesNotBetween(String value1, String value2) {
            addCriterion("actual_charges not between", value1, value2, "actualCharges");
            return (Criteria) this;
        }

        public Criteria andStandardChargeIsNull() {
            addCriterion("standard_charge is null");
            return (Criteria) this;
        }

        public Criteria andStandardChargeIsNotNull() {
            addCriterion("standard_charge is not null");
            return (Criteria) this;
        }

        public Criteria andStandardChargeEqualTo(String value) {
            addCriterion("standard_charge =", value, "standardCharge");
            return (Criteria) this;
        }

        public Criteria andStandardChargeNotEqualTo(String value) {
            addCriterion("standard_charge <>", value, "standardCharge");
            return (Criteria) this;
        }

        public Criteria andStandardChargeGreaterThan(String value) {
            addCriterion("standard_charge >", value, "standardCharge");
            return (Criteria) this;
        }

        public Criteria andStandardChargeGreaterThanOrEqualTo(String value) {
            addCriterion("standard_charge >=", value, "standardCharge");
            return (Criteria) this;
        }

        public Criteria andStandardChargeLessThan(String value) {
            addCriterion("standard_charge <", value, "standardCharge");
            return (Criteria) this;
        }

        public Criteria andStandardChargeLessThanOrEqualTo(String value) {
            addCriterion("standard_charge <=", value, "standardCharge");
            return (Criteria) this;
        }

        public Criteria andStandardChargeLike(String value) {
            addCriterion("standard_charge like", value, "standardCharge");
            return (Criteria) this;
        }

        public Criteria andStandardChargeNotLike(String value) {
            addCriterion("standard_charge not like", value, "standardCharge");
            return (Criteria) this;
        }

        public Criteria andStandardChargeIn(List<String> values) {
            addCriterion("standard_charge in", values, "standardCharge");
            return (Criteria) this;
        }

        public Criteria andStandardChargeNotIn(List<String> values) {
            addCriterion("standard_charge not in", values, "standardCharge");
            return (Criteria) this;
        }

        public Criteria andStandardChargeBetween(String value1, String value2) {
            addCriterion("standard_charge between", value1, value2, "standardCharge");
            return (Criteria) this;
        }

        public Criteria andStandardChargeNotBetween(String value1, String value2) {
            addCriterion("standard_charge not between", value1, value2, "standardCharge");
            return (Criteria) this;
        }

        public Criteria andValuationMethodIsNull() {
            addCriterion("valuation_method is null");
            return (Criteria) this;
        }

        public Criteria andValuationMethodIsNotNull() {
            addCriterion("valuation_method is not null");
            return (Criteria) this;
        }

        public Criteria andValuationMethodEqualTo(String value) {
            addCriterion("valuation_method =", value, "valuationMethod");
            return (Criteria) this;
        }

        public Criteria andValuationMethodNotEqualTo(String value) {
            addCriterion("valuation_method <>", value, "valuationMethod");
            return (Criteria) this;
        }

        public Criteria andValuationMethodGreaterThan(String value) {
            addCriterion("valuation_method >", value, "valuationMethod");
            return (Criteria) this;
        }

        public Criteria andValuationMethodGreaterThanOrEqualTo(String value) {
            addCriterion("valuation_method >=", value, "valuationMethod");
            return (Criteria) this;
        }

        public Criteria andValuationMethodLessThan(String value) {
            addCriterion("valuation_method <", value, "valuationMethod");
            return (Criteria) this;
        }

        public Criteria andValuationMethodLessThanOrEqualTo(String value) {
            addCriterion("valuation_method <=", value, "valuationMethod");
            return (Criteria) this;
        }

        public Criteria andValuationMethodLike(String value) {
            addCriterion("valuation_method like", value, "valuationMethod");
            return (Criteria) this;
        }

        public Criteria andValuationMethodNotLike(String value) {
            addCriterion("valuation_method not like", value, "valuationMethod");
            return (Criteria) this;
        }

        public Criteria andValuationMethodIn(List<String> values) {
            addCriterion("valuation_method in", values, "valuationMethod");
            return (Criteria) this;
        }

        public Criteria andValuationMethodNotIn(List<String> values) {
            addCriterion("valuation_method not in", values, "valuationMethod");
            return (Criteria) this;
        }

        public Criteria andValuationMethodBetween(String value1, String value2) {
            addCriterion("valuation_method between", value1, value2, "valuationMethod");
            return (Criteria) this;
        }

        public Criteria andValuationMethodNotBetween(String value1, String value2) {
            addCriterion("valuation_method not between", value1, value2, "valuationMethod");
            return (Criteria) this;
        }

        public Criteria andValuationPurposeIsNull() {
            addCriterion("valuation_purpose is null");
            return (Criteria) this;
        }

        public Criteria andValuationPurposeIsNotNull() {
            addCriterion("valuation_purpose is not null");
            return (Criteria) this;
        }

        public Criteria andValuationPurposeEqualTo(String value) {
            addCriterion("valuation_purpose =", value, "valuationPurpose");
            return (Criteria) this;
        }

        public Criteria andValuationPurposeNotEqualTo(String value) {
            addCriterion("valuation_purpose <>", value, "valuationPurpose");
            return (Criteria) this;
        }

        public Criteria andValuationPurposeGreaterThan(String value) {
            addCriterion("valuation_purpose >", value, "valuationPurpose");
            return (Criteria) this;
        }

        public Criteria andValuationPurposeGreaterThanOrEqualTo(String value) {
            addCriterion("valuation_purpose >=", value, "valuationPurpose");
            return (Criteria) this;
        }

        public Criteria andValuationPurposeLessThan(String value) {
            addCriterion("valuation_purpose <", value, "valuationPurpose");
            return (Criteria) this;
        }

        public Criteria andValuationPurposeLessThanOrEqualTo(String value) {
            addCriterion("valuation_purpose <=", value, "valuationPurpose");
            return (Criteria) this;
        }

        public Criteria andValuationPurposeLike(String value) {
            addCriterion("valuation_purpose like", value, "valuationPurpose");
            return (Criteria) this;
        }

        public Criteria andValuationPurposeNotLike(String value) {
            addCriterion("valuation_purpose not like", value, "valuationPurpose");
            return (Criteria) this;
        }

        public Criteria andValuationPurposeIn(List<String> values) {
            addCriterion("valuation_purpose in", values, "valuationPurpose");
            return (Criteria) this;
        }

        public Criteria andValuationPurposeNotIn(List<String> values) {
            addCriterion("valuation_purpose not in", values, "valuationPurpose");
            return (Criteria) this;
        }

        public Criteria andValuationPurposeBetween(String value1, String value2) {
            addCriterion("valuation_purpose between", value1, value2, "valuationPurpose");
            return (Criteria) this;
        }

        public Criteria andValuationPurposeNotBetween(String value1, String value2) {
            addCriterion("valuation_purpose not between", value1, value2, "valuationPurpose");
            return (Criteria) this;
        }

        public Criteria andAssessMethodIsNull() {
            addCriterion("assess_method is null");
            return (Criteria) this;
        }

        public Criteria andAssessMethodIsNotNull() {
            addCriterion("assess_method is not null");
            return (Criteria) this;
        }

        public Criteria andAssessMethodEqualTo(String value) {
            addCriterion("assess_method =", value, "assessMethod");
            return (Criteria) this;
        }

        public Criteria andAssessMethodNotEqualTo(String value) {
            addCriterion("assess_method <>", value, "assessMethod");
            return (Criteria) this;
        }

        public Criteria andAssessMethodGreaterThan(String value) {
            addCriterion("assess_method >", value, "assessMethod");
            return (Criteria) this;
        }

        public Criteria andAssessMethodGreaterThanOrEqualTo(String value) {
            addCriterion("assess_method >=", value, "assessMethod");
            return (Criteria) this;
        }

        public Criteria andAssessMethodLessThan(String value) {
            addCriterion("assess_method <", value, "assessMethod");
            return (Criteria) this;
        }

        public Criteria andAssessMethodLessThanOrEqualTo(String value) {
            addCriterion("assess_method <=", value, "assessMethod");
            return (Criteria) this;
        }

        public Criteria andAssessMethodLike(String value) {
            addCriterion("assess_method like", value, "assessMethod");
            return (Criteria) this;
        }

        public Criteria andAssessMethodNotLike(String value) {
            addCriterion("assess_method not like", value, "assessMethod");
            return (Criteria) this;
        }

        public Criteria andAssessMethodIn(List<String> values) {
            addCriterion("assess_method in", values, "assessMethod");
            return (Criteria) this;
        }

        public Criteria andAssessMethodNotIn(List<String> values) {
            addCriterion("assess_method not in", values, "assessMethod");
            return (Criteria) this;
        }

        public Criteria andAssessMethodBetween(String value1, String value2) {
            addCriterion("assess_method between", value1, value2, "assessMethod");
            return (Criteria) this;
        }

        public Criteria andAssessMethodNotBetween(String value1, String value2) {
            addCriterion("assess_method not between", value1, value2, "assessMethod");
            return (Criteria) this;
        }

        public Criteria andAssessPurposeIsNull() {
            addCriterion("assess_purpose is null");
            return (Criteria) this;
        }

        public Criteria andAssessPurposeIsNotNull() {
            addCriterion("assess_purpose is not null");
            return (Criteria) this;
        }

        public Criteria andAssessPurposeEqualTo(String value) {
            addCriterion("assess_purpose =", value, "assessPurpose");
            return (Criteria) this;
        }

        public Criteria andAssessPurposeNotEqualTo(String value) {
            addCriterion("assess_purpose <>", value, "assessPurpose");
            return (Criteria) this;
        }

        public Criteria andAssessPurposeGreaterThan(String value) {
            addCriterion("assess_purpose >", value, "assessPurpose");
            return (Criteria) this;
        }

        public Criteria andAssessPurposeGreaterThanOrEqualTo(String value) {
            addCriterion("assess_purpose >=", value, "assessPurpose");
            return (Criteria) this;
        }

        public Criteria andAssessPurposeLessThan(String value) {
            addCriterion("assess_purpose <", value, "assessPurpose");
            return (Criteria) this;
        }

        public Criteria andAssessPurposeLessThanOrEqualTo(String value) {
            addCriterion("assess_purpose <=", value, "assessPurpose");
            return (Criteria) this;
        }

        public Criteria andAssessPurposeLike(String value) {
            addCriterion("assess_purpose like", value, "assessPurpose");
            return (Criteria) this;
        }

        public Criteria andAssessPurposeNotLike(String value) {
            addCriterion("assess_purpose not like", value, "assessPurpose");
            return (Criteria) this;
        }

        public Criteria andAssessPurposeIn(List<String> values) {
            addCriterion("assess_purpose in", values, "assessPurpose");
            return (Criteria) this;
        }

        public Criteria andAssessPurposeNotIn(List<String> values) {
            addCriterion("assess_purpose not in", values, "assessPurpose");
            return (Criteria) this;
        }

        public Criteria andAssessPurposeBetween(String value1, String value2) {
            addCriterion("assess_purpose between", value1, value2, "assessPurpose");
            return (Criteria) this;
        }

        public Criteria andAssessPurposeNotBetween(String value1, String value2) {
            addCriterion("assess_purpose not between", value1, value2, "assessPurpose");
            return (Criteria) this;
        }

        public Criteria andSignedByIsNull() {
            addCriterion("signed_by is null");
            return (Criteria) this;
        }

        public Criteria andSignedByIsNotNull() {
            addCriterion("signed_by is not null");
            return (Criteria) this;
        }

        public Criteria andSignedByEqualTo(String value) {
            addCriterion("signed_by =", value, "signedBy");
            return (Criteria) this;
        }

        public Criteria andSignedByNotEqualTo(String value) {
            addCriterion("signed_by <>", value, "signedBy");
            return (Criteria) this;
        }

        public Criteria andSignedByGreaterThan(String value) {
            addCriterion("signed_by >", value, "signedBy");
            return (Criteria) this;
        }

        public Criteria andSignedByGreaterThanOrEqualTo(String value) {
            addCriterion("signed_by >=", value, "signedBy");
            return (Criteria) this;
        }

        public Criteria andSignedByLessThan(String value) {
            addCriterion("signed_by <", value, "signedBy");
            return (Criteria) this;
        }

        public Criteria andSignedByLessThanOrEqualTo(String value) {
            addCriterion("signed_by <=", value, "signedBy");
            return (Criteria) this;
        }

        public Criteria andSignedByLike(String value) {
            addCriterion("signed_by like", value, "signedBy");
            return (Criteria) this;
        }

        public Criteria andSignedByNotLike(String value) {
            addCriterion("signed_by not like", value, "signedBy");
            return (Criteria) this;
        }

        public Criteria andSignedByIn(List<String> values) {
            addCriterion("signed_by in", values, "signedBy");
            return (Criteria) this;
        }

        public Criteria andSignedByNotIn(List<String> values) {
            addCriterion("signed_by not in", values, "signedBy");
            return (Criteria) this;
        }

        public Criteria andSignedByBetween(String value1, String value2) {
            addCriterion("signed_by between", value1, value2, "signedBy");
            return (Criteria) this;
        }

        public Criteria andSignedByNotBetween(String value1, String value2) {
            addCriterion("signed_by not between", value1, value2, "signedBy");
            return (Criteria) this;
        }

        public Criteria andDraftAuthorIsNull() {
            addCriterion("draft_author is null");
            return (Criteria) this;
        }

        public Criteria andDraftAuthorIsNotNull() {
            addCriterion("draft_author is not null");
            return (Criteria) this;
        }

        public Criteria andDraftAuthorEqualTo(String value) {
            addCriterion("draft_author =", value, "draftAuthor");
            return (Criteria) this;
        }

        public Criteria andDraftAuthorNotEqualTo(String value) {
            addCriterion("draft_author <>", value, "draftAuthor");
            return (Criteria) this;
        }

        public Criteria andDraftAuthorGreaterThan(String value) {
            addCriterion("draft_author >", value, "draftAuthor");
            return (Criteria) this;
        }

        public Criteria andDraftAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("draft_author >=", value, "draftAuthor");
            return (Criteria) this;
        }

        public Criteria andDraftAuthorLessThan(String value) {
            addCriterion("draft_author <", value, "draftAuthor");
            return (Criteria) this;
        }

        public Criteria andDraftAuthorLessThanOrEqualTo(String value) {
            addCriterion("draft_author <=", value, "draftAuthor");
            return (Criteria) this;
        }

        public Criteria andDraftAuthorLike(String value) {
            addCriterion("draft_author like", value, "draftAuthor");
            return (Criteria) this;
        }

        public Criteria andDraftAuthorNotLike(String value) {
            addCriterion("draft_author not like", value, "draftAuthor");
            return (Criteria) this;
        }

        public Criteria andDraftAuthorIn(List<String> values) {
            addCriterion("draft_author in", values, "draftAuthor");
            return (Criteria) this;
        }

        public Criteria andDraftAuthorNotIn(List<String> values) {
            addCriterion("draft_author not in", values, "draftAuthor");
            return (Criteria) this;
        }

        public Criteria andDraftAuthorBetween(String value1, String value2) {
            addCriterion("draft_author between", value1, value2, "draftAuthor");
            return (Criteria) this;
        }

        public Criteria andDraftAuthorNotBetween(String value1, String value2) {
            addCriterion("draft_author not between", value1, value2, "draftAuthor");
            return (Criteria) this;
        }

        public Criteria andAppraisalPeriodIsNull() {
            addCriterion("appraisal_period is null");
            return (Criteria) this;
        }

        public Criteria andAppraisalPeriodIsNotNull() {
            addCriterion("appraisal_period is not null");
            return (Criteria) this;
        }

        public Criteria andAppraisalPeriodEqualTo(Date value) {
            addCriterion("appraisal_period =", value, "appraisalPeriod");
            return (Criteria) this;
        }

        public Criteria andAppraisalPeriodNotEqualTo(Date value) {
            addCriterion("appraisal_period <>", value, "appraisalPeriod");
            return (Criteria) this;
        }

        public Criteria andAppraisalPeriodGreaterThan(Date value) {
            addCriterion("appraisal_period >", value, "appraisalPeriod");
            return (Criteria) this;
        }

        public Criteria andAppraisalPeriodGreaterThanOrEqualTo(Date value) {
            addCriterion("appraisal_period >=", value, "appraisalPeriod");
            return (Criteria) this;
        }

        public Criteria andAppraisalPeriodLessThan(Date value) {
            addCriterion("appraisal_period <", value, "appraisalPeriod");
            return (Criteria) this;
        }

        public Criteria andAppraisalPeriodLessThanOrEqualTo(Date value) {
            addCriterion("appraisal_period <=", value, "appraisalPeriod");
            return (Criteria) this;
        }

        public Criteria andAppraisalPeriodIn(List<Date> values) {
            addCriterion("appraisal_period in", values, "appraisalPeriod");
            return (Criteria) this;
        }

        public Criteria andAppraisalPeriodNotIn(List<Date> values) {
            addCriterion("appraisal_period not in", values, "appraisalPeriod");
            return (Criteria) this;
        }

        public Criteria andAppraisalPeriodBetween(Date value1, Date value2) {
            addCriterion("appraisal_period between", value1, value2, "appraisalPeriod");
            return (Criteria) this;
        }

        public Criteria andAppraisalPeriodNotBetween(Date value1, Date value2) {
            addCriterion("appraisal_period not between", value1, value2, "appraisalPeriod");
            return (Criteria) this;
        }

        public Criteria andProjectAcquisitionMethodIsNull() {
            addCriterion("project_acquisition_method is null");
            return (Criteria) this;
        }

        public Criteria andProjectAcquisitionMethodIsNotNull() {
            addCriterion("project_acquisition_method is not null");
            return (Criteria) this;
        }

        public Criteria andProjectAcquisitionMethodEqualTo(String value) {
            addCriterion("project_acquisition_method =", value, "projectAcquisitionMethod");
            return (Criteria) this;
        }

        public Criteria andProjectAcquisitionMethodNotEqualTo(String value) {
            addCriterion("project_acquisition_method <>", value, "projectAcquisitionMethod");
            return (Criteria) this;
        }

        public Criteria andProjectAcquisitionMethodGreaterThan(String value) {
            addCriterion("project_acquisition_method >", value, "projectAcquisitionMethod");
            return (Criteria) this;
        }

        public Criteria andProjectAcquisitionMethodGreaterThanOrEqualTo(String value) {
            addCriterion("project_acquisition_method >=", value, "projectAcquisitionMethod");
            return (Criteria) this;
        }

        public Criteria andProjectAcquisitionMethodLessThan(String value) {
            addCriterion("project_acquisition_method <", value, "projectAcquisitionMethod");
            return (Criteria) this;
        }

        public Criteria andProjectAcquisitionMethodLessThanOrEqualTo(String value) {
            addCriterion("project_acquisition_method <=", value, "projectAcquisitionMethod");
            return (Criteria) this;
        }

        public Criteria andProjectAcquisitionMethodLike(String value) {
            addCriterion("project_acquisition_method like", value, "projectAcquisitionMethod");
            return (Criteria) this;
        }

        public Criteria andProjectAcquisitionMethodNotLike(String value) {
            addCriterion("project_acquisition_method not like", value, "projectAcquisitionMethod");
            return (Criteria) this;
        }

        public Criteria andProjectAcquisitionMethodIn(List<String> values) {
            addCriterion("project_acquisition_method in", values, "projectAcquisitionMethod");
            return (Criteria) this;
        }

        public Criteria andProjectAcquisitionMethodNotIn(List<String> values) {
            addCriterion("project_acquisition_method not in", values, "projectAcquisitionMethod");
            return (Criteria) this;
        }

        public Criteria andProjectAcquisitionMethodBetween(String value1, String value2) {
            addCriterion("project_acquisition_method between", value1, value2, "projectAcquisitionMethod");
            return (Criteria) this;
        }

        public Criteria andProjectAcquisitionMethodNotBetween(String value1, String value2) {
            addCriterion("project_acquisition_method not between", value1, value2, "projectAcquisitionMethod");
            return (Criteria) this;
        }

        public Criteria andProjectLeaderIsNull() {
            addCriterion("project_Leader is null");
            return (Criteria) this;
        }

        public Criteria andProjectLeaderIsNotNull() {
            addCriterion("project_Leader is not null");
            return (Criteria) this;
        }

        public Criteria andProjectLeaderEqualTo(String value) {
            addCriterion("project_Leader =", value, "projectLeader");
            return (Criteria) this;
        }

        public Criteria andProjectLeaderNotEqualTo(String value) {
            addCriterion("project_Leader <>", value, "projectLeader");
            return (Criteria) this;
        }

        public Criteria andProjectLeaderGreaterThan(String value) {
            addCriterion("project_Leader >", value, "projectLeader");
            return (Criteria) this;
        }

        public Criteria andProjectLeaderGreaterThanOrEqualTo(String value) {
            addCriterion("project_Leader >=", value, "projectLeader");
            return (Criteria) this;
        }

        public Criteria andProjectLeaderLessThan(String value) {
            addCriterion("project_Leader <", value, "projectLeader");
            return (Criteria) this;
        }

        public Criteria andProjectLeaderLessThanOrEqualTo(String value) {
            addCriterion("project_Leader <=", value, "projectLeader");
            return (Criteria) this;
        }

        public Criteria andProjectLeaderLike(String value) {
            addCriterion("project_Leader like", value, "projectLeader");
            return (Criteria) this;
        }

        public Criteria andProjectLeaderNotLike(String value) {
            addCriterion("project_Leader not like", value, "projectLeader");
            return (Criteria) this;
        }

        public Criteria andProjectLeaderIn(List<String> values) {
            addCriterion("project_Leader in", values, "projectLeader");
            return (Criteria) this;
        }

        public Criteria andProjectLeaderNotIn(List<String> values) {
            addCriterion("project_Leader not in", values, "projectLeader");
            return (Criteria) this;
        }

        public Criteria andProjectLeaderBetween(String value1, String value2) {
            addCriterion("project_Leader between", value1, value2, "projectLeader");
            return (Criteria) this;
        }

        public Criteria andProjectLeaderNotBetween(String value1, String value2) {
            addCriterion("project_Leader not between", value1, value2, "projectLeader");
            return (Criteria) this;
        }

        public Criteria andHousingUseIsNull() {
            addCriterion("housing_use is null");
            return (Criteria) this;
        }

        public Criteria andHousingUseIsNotNull() {
            addCriterion("housing_use is not null");
            return (Criteria) this;
        }

        public Criteria andHousingUseEqualTo(String value) {
            addCriterion("housing_use =", value, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseNotEqualTo(String value) {
            addCriterion("housing_use <>", value, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseGreaterThan(String value) {
            addCriterion("housing_use >", value, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseGreaterThanOrEqualTo(String value) {
            addCriterion("housing_use >=", value, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseLessThan(String value) {
            addCriterion("housing_use <", value, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseLessThanOrEqualTo(String value) {
            addCriterion("housing_use <=", value, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseLike(String value) {
            addCriterion("housing_use like", value, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseNotLike(String value) {
            addCriterion("housing_use not like", value, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseIn(List<String> values) {
            addCriterion("housing_use in", values, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseNotIn(List<String> values) {
            addCriterion("housing_use not in", values, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseBetween(String value1, String value2) {
            addCriterion("housing_use between", value1, value2, "housingUse");
            return (Criteria) this;
        }

        public Criteria andHousingUseNotBetween(String value1, String value2) {
            addCriterion("housing_use not between", value1, value2, "housingUse");
            return (Criteria) this;
        }

        public Criteria andLandUseIsNull() {
            addCriterion("land_use is null");
            return (Criteria) this;
        }

        public Criteria andLandUseIsNotNull() {
            addCriterion("land_use is not null");
            return (Criteria) this;
        }

        public Criteria andLandUseEqualTo(String value) {
            addCriterion("land_use =", value, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseNotEqualTo(String value) {
            addCriterion("land_use <>", value, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseGreaterThan(String value) {
            addCriterion("land_use >", value, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseGreaterThanOrEqualTo(String value) {
            addCriterion("land_use >=", value, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseLessThan(String value) {
            addCriterion("land_use <", value, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseLessThanOrEqualTo(String value) {
            addCriterion("land_use <=", value, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseLike(String value) {
            addCriterion("land_use like", value, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseNotLike(String value) {
            addCriterion("land_use not like", value, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseIn(List<String> values) {
            addCriterion("land_use in", values, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseNotIn(List<String> values) {
            addCriterion("land_use not in", values, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseBetween(String value1, String value2) {
            addCriterion("land_use between", value1, value2, "landUse");
            return (Criteria) this;
        }

        public Criteria andLandUseNotBetween(String value1, String value2) {
            addCriterion("land_use not between", value1, value2, "landUse");
            return (Criteria) this;
        }

        public Criteria andCertUseIsNull() {
            addCriterion("cert_use is null");
            return (Criteria) this;
        }

        public Criteria andCertUseIsNotNull() {
            addCriterion("cert_use is not null");
            return (Criteria) this;
        }

        public Criteria andCertUseEqualTo(String value) {
            addCriterion("cert_use =", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotEqualTo(String value) {
            addCriterion("cert_use <>", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseGreaterThan(String value) {
            addCriterion("cert_use >", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseGreaterThanOrEqualTo(String value) {
            addCriterion("cert_use >=", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseLessThan(String value) {
            addCriterion("cert_use <", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseLessThanOrEqualTo(String value) {
            addCriterion("cert_use <=", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseLike(String value) {
            addCriterion("cert_use like", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotLike(String value) {
            addCriterion("cert_use not like", value, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseIn(List<String> values) {
            addCriterion("cert_use in", values, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotIn(List<String> values) {
            addCriterion("cert_use not in", values, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseBetween(String value1, String value2) {
            addCriterion("cert_use between", value1, value2, "certUse");
            return (Criteria) this;
        }

        public Criteria andCertUseNotBetween(String value1, String value2) {
            addCriterion("cert_use not between", value1, value2, "certUse");
            return (Criteria) this;
        }

        public Criteria andValueTimePointIsNull() {
            addCriterion("value_time_point is null");
            return (Criteria) this;
        }

        public Criteria andValueTimePointIsNotNull() {
            addCriterion("value_time_point is not null");
            return (Criteria) this;
        }

        public Criteria andValueTimePointEqualTo(Date value) {
            addCriterion("value_time_point =", value, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andValueTimePointNotEqualTo(Date value) {
            addCriterion("value_time_point <>", value, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andValueTimePointGreaterThan(Date value) {
            addCriterion("value_time_point >", value, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andValueTimePointGreaterThanOrEqualTo(Date value) {
            addCriterion("value_time_point >=", value, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andValueTimePointLessThan(Date value) {
            addCriterion("value_time_point <", value, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andValueTimePointLessThanOrEqualTo(Date value) {
            addCriterion("value_time_point <=", value, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andValueTimePointIn(List<Date> values) {
            addCriterion("value_time_point in", values, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andValueTimePointNotIn(List<Date> values) {
            addCriterion("value_time_point not in", values, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andValueTimePointBetween(Date value1, Date value2) {
            addCriterion("value_time_point between", value1, value2, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andValueTimePointNotBetween(Date value1, Date value2) {
            addCriterion("value_time_point not between", value1, value2, "valueTimePoint");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainIsNull() {
            addCriterion("time_point_explain is null");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainIsNotNull() {
            addCriterion("time_point_explain is not null");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainEqualTo(String value) {
            addCriterion("time_point_explain =", value, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainNotEqualTo(String value) {
            addCriterion("time_point_explain <>", value, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainGreaterThan(String value) {
            addCriterion("time_point_explain >", value, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainGreaterThanOrEqualTo(String value) {
            addCriterion("time_point_explain >=", value, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainLessThan(String value) {
            addCriterion("time_point_explain <", value, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainLessThanOrEqualTo(String value) {
            addCriterion("time_point_explain <=", value, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainLike(String value) {
            addCriterion("time_point_explain like", value, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainNotLike(String value) {
            addCriterion("time_point_explain not like", value, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainIn(List<String> values) {
            addCriterion("time_point_explain in", values, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainNotIn(List<String> values) {
            addCriterion("time_point_explain not in", values, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainBetween(String value1, String value2) {
            addCriterion("time_point_explain between", value1, value2, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andTimePointExplainNotBetween(String value1, String value2) {
            addCriterion("time_point_explain not between", value1, value2, "timePointExplain");
            return (Criteria) this;
        }

        public Criteria andReportPeopleIsNull() {
            addCriterion("report_people is null");
            return (Criteria) this;
        }

        public Criteria andReportPeopleIsNotNull() {
            addCriterion("report_people is not null");
            return (Criteria) this;
        }

        public Criteria andReportPeopleEqualTo(String value) {
            addCriterion("report_people =", value, "reportPeople");
            return (Criteria) this;
        }

        public Criteria andReportPeopleNotEqualTo(String value) {
            addCriterion("report_people <>", value, "reportPeople");
            return (Criteria) this;
        }

        public Criteria andReportPeopleGreaterThan(String value) {
            addCriterion("report_people >", value, "reportPeople");
            return (Criteria) this;
        }

        public Criteria andReportPeopleGreaterThanOrEqualTo(String value) {
            addCriterion("report_people >=", value, "reportPeople");
            return (Criteria) this;
        }

        public Criteria andReportPeopleLessThan(String value) {
            addCriterion("report_people <", value, "reportPeople");
            return (Criteria) this;
        }

        public Criteria andReportPeopleLessThanOrEqualTo(String value) {
            addCriterion("report_people <=", value, "reportPeople");
            return (Criteria) this;
        }

        public Criteria andReportPeopleLike(String value) {
            addCriterion("report_people like", value, "reportPeople");
            return (Criteria) this;
        }

        public Criteria andReportPeopleNotLike(String value) {
            addCriterion("report_people not like", value, "reportPeople");
            return (Criteria) this;
        }

        public Criteria andReportPeopleIn(List<String> values) {
            addCriterion("report_people in", values, "reportPeople");
            return (Criteria) this;
        }

        public Criteria andReportPeopleNotIn(List<String> values) {
            addCriterion("report_people not in", values, "reportPeople");
            return (Criteria) this;
        }

        public Criteria andReportPeopleBetween(String value1, String value2) {
            addCriterion("report_people between", value1, value2, "reportPeople");
            return (Criteria) this;
        }

        public Criteria andReportPeopleNotBetween(String value1, String value2) {
            addCriterion("report_people not between", value1, value2, "reportPeople");
            return (Criteria) this;
        }

        public Criteria andNumberValueIsNull() {
            addCriterion("number_value is null");
            return (Criteria) this;
        }

        public Criteria andNumberValueIsNotNull() {
            addCriterion("number_value is not null");
            return (Criteria) this;
        }

        public Criteria andNumberValueEqualTo(String value) {
            addCriterion("number_value =", value, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueNotEqualTo(String value) {
            addCriterion("number_value <>", value, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueGreaterThan(String value) {
            addCriterion("number_value >", value, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueGreaterThanOrEqualTo(String value) {
            addCriterion("number_value >=", value, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueLessThan(String value) {
            addCriterion("number_value <", value, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueLessThanOrEqualTo(String value) {
            addCriterion("number_value <=", value, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueLike(String value) {
            addCriterion("number_value like", value, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueNotLike(String value) {
            addCriterion("number_value not like", value, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueIn(List<String> values) {
            addCriterion("number_value in", values, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueNotIn(List<String> values) {
            addCriterion("number_value not in", values, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueBetween(String value1, String value2) {
            addCriterion("number_value between", value1, value2, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueNotBetween(String value1, String value2) {
            addCriterion("number_value not between", value1, value2, "numberValue");
            return (Criteria) this;
        }

        public Criteria andLandAreaIsNull() {
            addCriterion("land_area is null");
            return (Criteria) this;
        }

        public Criteria andLandAreaIsNotNull() {
            addCriterion("land_area is not null");
            return (Criteria) this;
        }

        public Criteria andLandAreaEqualTo(BigDecimal value) {
            addCriterion("land_area =", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotEqualTo(BigDecimal value) {
            addCriterion("land_area <>", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaGreaterThan(BigDecimal value) {
            addCriterion("land_area >", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("land_area >=", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaLessThan(BigDecimal value) {
            addCriterion("land_area <", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("land_area <=", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaIn(List<BigDecimal> values) {
            addCriterion("land_area in", values, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotIn(List<BigDecimal> values) {
            addCriterion("land_area not in", values, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_area between", value1, value2, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("land_area not between", value1, value2, "landArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaIsNull() {
            addCriterion("assess_area is null");
            return (Criteria) this;
        }

        public Criteria andAssessAreaIsNotNull() {
            addCriterion("assess_area is not null");
            return (Criteria) this;
        }

        public Criteria andAssessAreaEqualTo(BigDecimal value) {
            addCriterion("assess_area =", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaNotEqualTo(BigDecimal value) {
            addCriterion("assess_area <>", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaGreaterThan(BigDecimal value) {
            addCriterion("assess_area >", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_area >=", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaLessThan(BigDecimal value) {
            addCriterion("assess_area <", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_area <=", value, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaIn(List<BigDecimal> values) {
            addCriterion("assess_area in", values, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaNotIn(List<BigDecimal> values) {
            addCriterion("assess_area not in", values, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_area between", value1, value2, "assessArea");
            return (Criteria) this;
        }

        public Criteria andAssessAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_area not between", value1, value2, "assessArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectLandAreaIsNull() {
            addCriterion("judge_object_land_area is null");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectLandAreaIsNotNull() {
            addCriterion("judge_object_land_area is not null");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectLandAreaEqualTo(BigDecimal value) {
            addCriterion("judge_object_land_area =", value, "judgeObjectLandArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectLandAreaNotEqualTo(BigDecimal value) {
            addCriterion("judge_object_land_area <>", value, "judgeObjectLandArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectLandAreaGreaterThan(BigDecimal value) {
            addCriterion("judge_object_land_area >", value, "judgeObjectLandArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectLandAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("judge_object_land_area >=", value, "judgeObjectLandArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectLandAreaLessThan(BigDecimal value) {
            addCriterion("judge_object_land_area <", value, "judgeObjectLandArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectLandAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("judge_object_land_area <=", value, "judgeObjectLandArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectLandAreaIn(List<BigDecimal> values) {
            addCriterion("judge_object_land_area in", values, "judgeObjectLandArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectLandAreaNotIn(List<BigDecimal> values) {
            addCriterion("judge_object_land_area not in", values, "judgeObjectLandArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectLandAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("judge_object_land_area between", value1, value2, "judgeObjectLandArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectLandAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("judge_object_land_area not between", value1, value2, "judgeObjectLandArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectFloorAreaIsNull() {
            addCriterion("judge_object_floor_area is null");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectFloorAreaIsNotNull() {
            addCriterion("judge_object_floor_area is not null");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectFloorAreaEqualTo(BigDecimal value) {
            addCriterion("judge_object_floor_area =", value, "judgeObjectFloorArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectFloorAreaNotEqualTo(BigDecimal value) {
            addCriterion("judge_object_floor_area <>", value, "judgeObjectFloorArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectFloorAreaGreaterThan(BigDecimal value) {
            addCriterion("judge_object_floor_area >", value, "judgeObjectFloorArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectFloorAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("judge_object_floor_area >=", value, "judgeObjectFloorArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectFloorAreaLessThan(BigDecimal value) {
            addCriterion("judge_object_floor_area <", value, "judgeObjectFloorArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectFloorAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("judge_object_floor_area <=", value, "judgeObjectFloorArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectFloorAreaIn(List<BigDecimal> values) {
            addCriterion("judge_object_floor_area in", values, "judgeObjectFloorArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectFloorAreaNotIn(List<BigDecimal> values) {
            addCriterion("judge_object_floor_area not in", values, "judgeObjectFloorArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectFloorAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("judge_object_floor_area between", value1, value2, "judgeObjectFloorArea");
            return (Criteria) this;
        }

        public Criteria andJudgeObjectFloorAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("judge_object_floor_area not between", value1, value2, "judgeObjectFloorArea");
            return (Criteria) this;
        }

        public Criteria andAssessPriceIsNull() {
            addCriterion("assess_price is null");
            return (Criteria) this;
        }

        public Criteria andAssessPriceIsNotNull() {
            addCriterion("assess_price is not null");
            return (Criteria) this;
        }

        public Criteria andAssessPriceEqualTo(BigDecimal value) {
            addCriterion("assess_price =", value, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessPriceNotEqualTo(BigDecimal value) {
            addCriterion("assess_price <>", value, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessPriceGreaterThan(BigDecimal value) {
            addCriterion("assess_price >", value, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_price >=", value, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessPriceLessThan(BigDecimal value) {
            addCriterion("assess_price <", value, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_price <=", value, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessPriceIn(List<BigDecimal> values) {
            addCriterion("assess_price in", values, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessPriceNotIn(List<BigDecimal> values) {
            addCriterion("assess_price not in", values, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_price between", value1, value2, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_price not between", value1, value2, "assessPrice");
            return (Criteria) this;
        }

        public Criteria andAssessTotalPriceIsNull() {
            addCriterion("assess_total_price is null");
            return (Criteria) this;
        }

        public Criteria andAssessTotalPriceIsNotNull() {
            addCriterion("assess_total_price is not null");
            return (Criteria) this;
        }

        public Criteria andAssessTotalPriceEqualTo(BigDecimal value) {
            addCriterion("assess_total_price =", value, "assessTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAssessTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("assess_total_price <>", value, "assessTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAssessTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("assess_total_price >", value, "assessTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAssessTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_total_price >=", value, "assessTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAssessTotalPriceLessThan(BigDecimal value) {
            addCriterion("assess_total_price <", value, "assessTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAssessTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_total_price <=", value, "assessTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAssessTotalPriceIn(List<BigDecimal> values) {
            addCriterion("assess_total_price in", values, "assessTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAssessTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("assess_total_price not in", values, "assessTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAssessTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_total_price between", value1, value2, "assessTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAssessTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_total_price not between", value1, value2, "assessTotalPrice");
            return (Criteria) this;
        }

        public Criteria andUndertakeSheetIsNull() {
            addCriterion("undertake_sheet is null");
            return (Criteria) this;
        }

        public Criteria andUndertakeSheetIsNotNull() {
            addCriterion("undertake_sheet is not null");
            return (Criteria) this;
        }

        public Criteria andUndertakeSheetEqualTo(String value) {
            addCriterion("undertake_sheet =", value, "undertakeSheet");
            return (Criteria) this;
        }

        public Criteria andUndertakeSheetNotEqualTo(String value) {
            addCriterion("undertake_sheet <>", value, "undertakeSheet");
            return (Criteria) this;
        }

        public Criteria andUndertakeSheetGreaterThan(String value) {
            addCriterion("undertake_sheet >", value, "undertakeSheet");
            return (Criteria) this;
        }

        public Criteria andUndertakeSheetGreaterThanOrEqualTo(String value) {
            addCriterion("undertake_sheet >=", value, "undertakeSheet");
            return (Criteria) this;
        }

        public Criteria andUndertakeSheetLessThan(String value) {
            addCriterion("undertake_sheet <", value, "undertakeSheet");
            return (Criteria) this;
        }

        public Criteria andUndertakeSheetLessThanOrEqualTo(String value) {
            addCriterion("undertake_sheet <=", value, "undertakeSheet");
            return (Criteria) this;
        }

        public Criteria andUndertakeSheetLike(String value) {
            addCriterion("undertake_sheet like", value, "undertakeSheet");
            return (Criteria) this;
        }

        public Criteria andUndertakeSheetNotLike(String value) {
            addCriterion("undertake_sheet not like", value, "undertakeSheet");
            return (Criteria) this;
        }

        public Criteria andUndertakeSheetIn(List<String> values) {
            addCriterion("undertake_sheet in", values, "undertakeSheet");
            return (Criteria) this;
        }

        public Criteria andUndertakeSheetNotIn(List<String> values) {
            addCriterion("undertake_sheet not in", values, "undertakeSheet");
            return (Criteria) this;
        }

        public Criteria andUndertakeSheetBetween(String value1, String value2) {
            addCriterion("undertake_sheet between", value1, value2, "undertakeSheet");
            return (Criteria) this;
        }

        public Criteria andUndertakeSheetNotBetween(String value1, String value2) {
            addCriterion("undertake_sheet not between", value1, value2, "undertakeSheet");
            return (Criteria) this;
        }

        public Criteria andAssessTotalTollIsNull() {
            addCriterion("assess_total_toll is null");
            return (Criteria) this;
        }

        public Criteria andAssessTotalTollIsNotNull() {
            addCriterion("assess_total_toll is not null");
            return (Criteria) this;
        }

        public Criteria andAssessTotalTollEqualTo(BigDecimal value) {
            addCriterion("assess_total_toll =", value, "assessTotalToll");
            return (Criteria) this;
        }

        public Criteria andAssessTotalTollNotEqualTo(BigDecimal value) {
            addCriterion("assess_total_toll <>", value, "assessTotalToll");
            return (Criteria) this;
        }

        public Criteria andAssessTotalTollGreaterThan(BigDecimal value) {
            addCriterion("assess_total_toll >", value, "assessTotalToll");
            return (Criteria) this;
        }

        public Criteria andAssessTotalTollGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_total_toll >=", value, "assessTotalToll");
            return (Criteria) this;
        }

        public Criteria andAssessTotalTollLessThan(BigDecimal value) {
            addCriterion("assess_total_toll <", value, "assessTotalToll");
            return (Criteria) this;
        }

        public Criteria andAssessTotalTollLessThanOrEqualTo(BigDecimal value) {
            addCriterion("assess_total_toll <=", value, "assessTotalToll");
            return (Criteria) this;
        }

        public Criteria andAssessTotalTollIn(List<BigDecimal> values) {
            addCriterion("assess_total_toll in", values, "assessTotalToll");
            return (Criteria) this;
        }

        public Criteria andAssessTotalTollNotIn(List<BigDecimal> values) {
            addCriterion("assess_total_toll not in", values, "assessTotalToll");
            return (Criteria) this;
        }

        public Criteria andAssessTotalTollBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_total_toll between", value1, value2, "assessTotalToll");
            return (Criteria) this;
        }

        public Criteria andAssessTotalTollNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("assess_total_toll not between", value1, value2, "assessTotalToll");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andWorthIsNull() {
            addCriterion("worth is null");
            return (Criteria) this;
        }

        public Criteria andWorthIsNotNull() {
            addCriterion("worth is not null");
            return (Criteria) this;
        }

        public Criteria andWorthEqualTo(BigDecimal value) {
            addCriterion("worth =", value, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthNotEqualTo(BigDecimal value) {
            addCriterion("worth <>", value, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthGreaterThan(BigDecimal value) {
            addCriterion("worth >", value, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("worth >=", value, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthLessThan(BigDecimal value) {
            addCriterion("worth <", value, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthLessThanOrEqualTo(BigDecimal value) {
            addCriterion("worth <=", value, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthIn(List<BigDecimal> values) {
            addCriterion("worth in", values, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthNotIn(List<BigDecimal> values) {
            addCriterion("worth not in", values, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("worth between", value1, value2, "worth");
            return (Criteria) this;
        }

        public Criteria andWorthNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("worth not between", value1, value2, "worth");
            return (Criteria) this;
        }

        public Criteria andAccountValueOriginalIsNull() {
            addCriterion("account_value_original is null");
            return (Criteria) this;
        }

        public Criteria andAccountValueOriginalIsNotNull() {
            addCriterion("account_value_original is not null");
            return (Criteria) this;
        }

        public Criteria andAccountValueOriginalEqualTo(BigDecimal value) {
            addCriterion("account_value_original =", value, "accountValueOriginal");
            return (Criteria) this;
        }

        public Criteria andAccountValueOriginalNotEqualTo(BigDecimal value) {
            addCriterion("account_value_original <>", value, "accountValueOriginal");
            return (Criteria) this;
        }

        public Criteria andAccountValueOriginalGreaterThan(BigDecimal value) {
            addCriterion("account_value_original >", value, "accountValueOriginal");
            return (Criteria) this;
        }

        public Criteria andAccountValueOriginalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("account_value_original >=", value, "accountValueOriginal");
            return (Criteria) this;
        }

        public Criteria andAccountValueOriginalLessThan(BigDecimal value) {
            addCriterion("account_value_original <", value, "accountValueOriginal");
            return (Criteria) this;
        }

        public Criteria andAccountValueOriginalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("account_value_original <=", value, "accountValueOriginal");
            return (Criteria) this;
        }

        public Criteria andAccountValueOriginalIn(List<BigDecimal> values) {
            addCriterion("account_value_original in", values, "accountValueOriginal");
            return (Criteria) this;
        }

        public Criteria andAccountValueOriginalNotIn(List<BigDecimal> values) {
            addCriterion("account_value_original not in", values, "accountValueOriginal");
            return (Criteria) this;
        }

        public Criteria andAccountValueOriginalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_value_original between", value1, value2, "accountValueOriginal");
            return (Criteria) this;
        }

        public Criteria andAccountValueOriginalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_value_original not between", value1, value2, "accountValueOriginal");
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

        public Criteria andAssessTypeIsNull() {
            addCriterion("assess_type is null");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIsNotNull() {
            addCriterion("assess_type is not null");
            return (Criteria) this;
        }

        public Criteria andAssessTypeEqualTo(String value) {
            addCriterion("assess_type =", value, "assessType");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNotEqualTo(String value) {
            addCriterion("assess_type <>", value, "assessType");
            return (Criteria) this;
        }

        public Criteria andAssessTypeGreaterThan(String value) {
            addCriterion("assess_type >", value, "assessType");
            return (Criteria) this;
        }

        public Criteria andAssessTypeGreaterThanOrEqualTo(String value) {
            addCriterion("assess_type >=", value, "assessType");
            return (Criteria) this;
        }

        public Criteria andAssessTypeLessThan(String value) {
            addCriterion("assess_type <", value, "assessType");
            return (Criteria) this;
        }

        public Criteria andAssessTypeLessThanOrEqualTo(String value) {
            addCriterion("assess_type <=", value, "assessType");
            return (Criteria) this;
        }

        public Criteria andAssessTypeLike(String value) {
            addCriterion("assess_type like", value, "assessType");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNotLike(String value) {
            addCriterion("assess_type not like", value, "assessType");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIn(List<String> values) {
            addCriterion("assess_type in", values, "assessType");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNotIn(List<String> values) {
            addCriterion("assess_type not in", values, "assessType");
            return (Criteria) this;
        }

        public Criteria andAssessTypeBetween(String value1, String value2) {
            addCriterion("assess_type between", value1, value2, "assessType");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNotBetween(String value1, String value2) {
            addCriterion("assess_type not between", value1, value2, "assessType");
            return (Criteria) this;
        }

        public Criteria andSortingIsNull() {
            addCriterion("sorting is null");
            return (Criteria) this;
        }

        public Criteria andSortingIsNotNull() {
            addCriterion("sorting is not null");
            return (Criteria) this;
        }

        public Criteria andSortingEqualTo(Integer value) {
            addCriterion("sorting =", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotEqualTo(Integer value) {
            addCriterion("sorting <>", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingGreaterThan(Integer value) {
            addCriterion("sorting >", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingGreaterThanOrEqualTo(Integer value) {
            addCriterion("sorting >=", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingLessThan(Integer value) {
            addCriterion("sorting <", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingLessThanOrEqualTo(Integer value) {
            addCriterion("sorting <=", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingIn(List<Integer> values) {
            addCriterion("sorting in", values, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotIn(List<Integer> values) {
            addCriterion("sorting not in", values, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingBetween(Integer value1, Integer value2) {
            addCriterion("sorting between", value1, value2, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotBetween(Integer value1, Integer value2) {
            addCriterion("sorting not between", value1, value2, "sorting");
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