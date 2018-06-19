package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsrBorrowerMortgageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CsrBorrowerMortgageExample() {
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

        public Criteria andCsrProjectIdIsNull() {
            addCriterion("csr_project_id is null");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdIsNotNull() {
            addCriterion("csr_project_id is not null");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdEqualTo(Integer value) {
            addCriterion("csr_project_id =", value, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdNotEqualTo(Integer value) {
            addCriterion("csr_project_id <>", value, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdGreaterThan(Integer value) {
            addCriterion("csr_project_id >", value, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("csr_project_id >=", value, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdLessThan(Integer value) {
            addCriterion("csr_project_id <", value, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("csr_project_id <=", value, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdIn(List<Integer> values) {
            addCriterion("csr_project_id in", values, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdNotIn(List<Integer> values) {
            addCriterion("csr_project_id not in", values, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("csr_project_id between", value1, value2, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andCsrProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("csr_project_id not between", value1, value2, "csrProjectId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdIsNull() {
            addCriterion("borrower_id is null");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdIsNotNull() {
            addCriterion("borrower_id is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdEqualTo(String value) {
            addCriterion("borrower_id =", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdNotEqualTo(String value) {
            addCriterion("borrower_id <>", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdGreaterThan(String value) {
            addCriterion("borrower_id >", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdGreaterThanOrEqualTo(String value) {
            addCriterion("borrower_id >=", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdLessThan(String value) {
            addCriterion("borrower_id <", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdLessThanOrEqualTo(String value) {
            addCriterion("borrower_id <=", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdLike(String value) {
            addCriterion("borrower_id like", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdNotLike(String value) {
            addCriterion("borrower_id not like", value, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdIn(List<String> values) {
            addCriterion("borrower_id in", values, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdNotIn(List<String> values) {
            addCriterion("borrower_id not in", values, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdBetween(String value1, String value2) {
            addCriterion("borrower_id between", value1, value2, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andBorrowerIdNotBetween(String value1, String value2) {
            addCriterion("borrower_id not between", value1, value2, "borrowerId");
            return (Criteria) this;
        }

        public Criteria andContractNumberIsNull() {
            addCriterion("contract_number is null");
            return (Criteria) this;
        }

        public Criteria andContractNumberIsNotNull() {
            addCriterion("contract_number is not null");
            return (Criteria) this;
        }

        public Criteria andContractNumberEqualTo(String value) {
            addCriterion("contract_number =", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberNotEqualTo(String value) {
            addCriterion("contract_number <>", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberGreaterThan(String value) {
            addCriterion("contract_number >", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberGreaterThanOrEqualTo(String value) {
            addCriterion("contract_number >=", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberLessThan(String value) {
            addCriterion("contract_number <", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberLessThanOrEqualTo(String value) {
            addCriterion("contract_number <=", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberLike(String value) {
            addCriterion("contract_number like", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberNotLike(String value) {
            addCriterion("contract_number not like", value, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberIn(List<String> values) {
            addCriterion("contract_number in", values, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberNotIn(List<String> values) {
            addCriterion("contract_number not in", values, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberBetween(String value1, String value2) {
            addCriterion("contract_number between", value1, value2, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andContractNumberNotBetween(String value1, String value2) {
            addCriterion("contract_number not between", value1, value2, "contractNumber");
            return (Criteria) this;
        }

        public Criteria andSignDataIsNull() {
            addCriterion("sign_data is null");
            return (Criteria) this;
        }

        public Criteria andSignDataIsNotNull() {
            addCriterion("sign_data is not null");
            return (Criteria) this;
        }

        public Criteria andSignDataEqualTo(String value) {
            addCriterion("sign_data =", value, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataNotEqualTo(String value) {
            addCriterion("sign_data <>", value, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataGreaterThan(String value) {
            addCriterion("sign_data >", value, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataGreaterThanOrEqualTo(String value) {
            addCriterion("sign_data >=", value, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataLessThan(String value) {
            addCriterion("sign_data <", value, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataLessThanOrEqualTo(String value) {
            addCriterion("sign_data <=", value, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataLike(String value) {
            addCriterion("sign_data like", value, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataNotLike(String value) {
            addCriterion("sign_data not like", value, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataIn(List<String> values) {
            addCriterion("sign_data in", values, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataNotIn(List<String> values) {
            addCriterion("sign_data not in", values, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataBetween(String value1, String value2) {
            addCriterion("sign_data between", value1, value2, "signData");
            return (Criteria) this;
        }

        public Criteria andSignDataNotBetween(String value1, String value2) {
            addCriterion("sign_data not between", value1, value2, "signData");
            return (Criteria) this;
        }

        public Criteria andMortgagorIsNull() {
            addCriterion("mortgagor is null");
            return (Criteria) this;
        }

        public Criteria andMortgagorIsNotNull() {
            addCriterion("mortgagor is not null");
            return (Criteria) this;
        }

        public Criteria andMortgagorEqualTo(String value) {
            addCriterion("mortgagor =", value, "mortgagor");
            return (Criteria) this;
        }

        public Criteria andMortgagorNotEqualTo(String value) {
            addCriterion("mortgagor <>", value, "mortgagor");
            return (Criteria) this;
        }

        public Criteria andMortgagorGreaterThan(String value) {
            addCriterion("mortgagor >", value, "mortgagor");
            return (Criteria) this;
        }

        public Criteria andMortgagorGreaterThanOrEqualTo(String value) {
            addCriterion("mortgagor >=", value, "mortgagor");
            return (Criteria) this;
        }

        public Criteria andMortgagorLessThan(String value) {
            addCriterion("mortgagor <", value, "mortgagor");
            return (Criteria) this;
        }

        public Criteria andMortgagorLessThanOrEqualTo(String value) {
            addCriterion("mortgagor <=", value, "mortgagor");
            return (Criteria) this;
        }

        public Criteria andMortgagorLike(String value) {
            addCriterion("mortgagor like", value, "mortgagor");
            return (Criteria) this;
        }

        public Criteria andMortgagorNotLike(String value) {
            addCriterion("mortgagor not like", value, "mortgagor");
            return (Criteria) this;
        }

        public Criteria andMortgagorIn(List<String> values) {
            addCriterion("mortgagor in", values, "mortgagor");
            return (Criteria) this;
        }

        public Criteria andMortgagorNotIn(List<String> values) {
            addCriterion("mortgagor not in", values, "mortgagor");
            return (Criteria) this;
        }

        public Criteria andMortgagorBetween(String value1, String value2) {
            addCriterion("mortgagor between", value1, value2, "mortgagor");
            return (Criteria) this;
        }

        public Criteria andMortgagorNotBetween(String value1, String value2) {
            addCriterion("mortgagor not between", value1, value2, "mortgagor");
            return (Criteria) this;
        }

        public Criteria andMortgageCompanyIsNull() {
            addCriterion("mortgage_company is null");
            return (Criteria) this;
        }

        public Criteria andMortgageCompanyIsNotNull() {
            addCriterion("mortgage_company is not null");
            return (Criteria) this;
        }

        public Criteria andMortgageCompanyEqualTo(String value) {
            addCriterion("mortgage_company =", value, "mortgageCompany");
            return (Criteria) this;
        }

        public Criteria andMortgageCompanyNotEqualTo(String value) {
            addCriterion("mortgage_company <>", value, "mortgageCompany");
            return (Criteria) this;
        }

        public Criteria andMortgageCompanyGreaterThan(String value) {
            addCriterion("mortgage_company >", value, "mortgageCompany");
            return (Criteria) this;
        }

        public Criteria andMortgageCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("mortgage_company >=", value, "mortgageCompany");
            return (Criteria) this;
        }

        public Criteria andMortgageCompanyLessThan(String value) {
            addCriterion("mortgage_company <", value, "mortgageCompany");
            return (Criteria) this;
        }

        public Criteria andMortgageCompanyLessThanOrEqualTo(String value) {
            addCriterion("mortgage_company <=", value, "mortgageCompany");
            return (Criteria) this;
        }

        public Criteria andMortgageCompanyLike(String value) {
            addCriterion("mortgage_company like", value, "mortgageCompany");
            return (Criteria) this;
        }

        public Criteria andMortgageCompanyNotLike(String value) {
            addCriterion("mortgage_company not like", value, "mortgageCompany");
            return (Criteria) this;
        }

        public Criteria andMortgageCompanyIn(List<String> values) {
            addCriterion("mortgage_company in", values, "mortgageCompany");
            return (Criteria) this;
        }

        public Criteria andMortgageCompanyNotIn(List<String> values) {
            addCriterion("mortgage_company not in", values, "mortgageCompany");
            return (Criteria) this;
        }

        public Criteria andMortgageCompanyBetween(String value1, String value2) {
            addCriterion("mortgage_company between", value1, value2, "mortgageCompany");
            return (Criteria) this;
        }

        public Criteria andMortgageCompanyNotBetween(String value1, String value2) {
            addCriterion("mortgage_company not between", value1, value2, "mortgageCompany");
            return (Criteria) this;
        }

        public Criteria andLoanAmountIsNull() {
            addCriterion("loan_amount is null");
            return (Criteria) this;
        }

        public Criteria andLoanAmountIsNotNull() {
            addCriterion("loan_amount is not null");
            return (Criteria) this;
        }

        public Criteria andLoanAmountEqualTo(String value) {
            addCriterion("loan_amount =", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountNotEqualTo(String value) {
            addCriterion("loan_amount <>", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountGreaterThan(String value) {
            addCriterion("loan_amount >", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountGreaterThanOrEqualTo(String value) {
            addCriterion("loan_amount >=", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountLessThan(String value) {
            addCriterion("loan_amount <", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountLessThanOrEqualTo(String value) {
            addCriterion("loan_amount <=", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountLike(String value) {
            addCriterion("loan_amount like", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountNotLike(String value) {
            addCriterion("loan_amount not like", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountIn(List<String> values) {
            addCriterion("loan_amount in", values, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountNotIn(List<String> values) {
            addCriterion("loan_amount not in", values, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountBetween(String value1, String value2) {
            addCriterion("loan_amount between", value1, value2, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountNotBetween(String value1, String value2) {
            addCriterion("loan_amount not between", value1, value2, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andMortgageAddressIsNull() {
            addCriterion("mortgage_address is null");
            return (Criteria) this;
        }

        public Criteria andMortgageAddressIsNotNull() {
            addCriterion("mortgage_address is not null");
            return (Criteria) this;
        }

        public Criteria andMortgageAddressEqualTo(String value) {
            addCriterion("mortgage_address =", value, "mortgageAddress");
            return (Criteria) this;
        }

        public Criteria andMortgageAddressNotEqualTo(String value) {
            addCriterion("mortgage_address <>", value, "mortgageAddress");
            return (Criteria) this;
        }

        public Criteria andMortgageAddressGreaterThan(String value) {
            addCriterion("mortgage_address >", value, "mortgageAddress");
            return (Criteria) this;
        }

        public Criteria andMortgageAddressGreaterThanOrEqualTo(String value) {
            addCriterion("mortgage_address >=", value, "mortgageAddress");
            return (Criteria) this;
        }

        public Criteria andMortgageAddressLessThan(String value) {
            addCriterion("mortgage_address <", value, "mortgageAddress");
            return (Criteria) this;
        }

        public Criteria andMortgageAddressLessThanOrEqualTo(String value) {
            addCriterion("mortgage_address <=", value, "mortgageAddress");
            return (Criteria) this;
        }

        public Criteria andMortgageAddressLike(String value) {
            addCriterion("mortgage_address like", value, "mortgageAddress");
            return (Criteria) this;
        }

        public Criteria andMortgageAddressNotLike(String value) {
            addCriterion("mortgage_address not like", value, "mortgageAddress");
            return (Criteria) this;
        }

        public Criteria andMortgageAddressIn(List<String> values) {
            addCriterion("mortgage_address in", values, "mortgageAddress");
            return (Criteria) this;
        }

        public Criteria andMortgageAddressNotIn(List<String> values) {
            addCriterion("mortgage_address not in", values, "mortgageAddress");
            return (Criteria) this;
        }

        public Criteria andMortgageAddressBetween(String value1, String value2) {
            addCriterion("mortgage_address between", value1, value2, "mortgageAddress");
            return (Criteria) this;
        }

        public Criteria andMortgageAddressNotBetween(String value1, String value2) {
            addCriterion("mortgage_address not between", value1, value2, "mortgageAddress");
            return (Criteria) this;
        }

        public Criteria andLandNatureIsNull() {
            addCriterion("land_nature is null");
            return (Criteria) this;
        }

        public Criteria andLandNatureIsNotNull() {
            addCriterion("land_nature is not null");
            return (Criteria) this;
        }

        public Criteria andLandNatureEqualTo(String value) {
            addCriterion("land_nature =", value, "landNature");
            return (Criteria) this;
        }

        public Criteria andLandNatureNotEqualTo(String value) {
            addCriterion("land_nature <>", value, "landNature");
            return (Criteria) this;
        }

        public Criteria andLandNatureGreaterThan(String value) {
            addCriterion("land_nature >", value, "landNature");
            return (Criteria) this;
        }

        public Criteria andLandNatureGreaterThanOrEqualTo(String value) {
            addCriterion("land_nature >=", value, "landNature");
            return (Criteria) this;
        }

        public Criteria andLandNatureLessThan(String value) {
            addCriterion("land_nature <", value, "landNature");
            return (Criteria) this;
        }

        public Criteria andLandNatureLessThanOrEqualTo(String value) {
            addCriterion("land_nature <=", value, "landNature");
            return (Criteria) this;
        }

        public Criteria andLandNatureLike(String value) {
            addCriterion("land_nature like", value, "landNature");
            return (Criteria) this;
        }

        public Criteria andLandNatureNotLike(String value) {
            addCriterion("land_nature not like", value, "landNature");
            return (Criteria) this;
        }

        public Criteria andLandNatureIn(List<String> values) {
            addCriterion("land_nature in", values, "landNature");
            return (Criteria) this;
        }

        public Criteria andLandNatureNotIn(List<String> values) {
            addCriterion("land_nature not in", values, "landNature");
            return (Criteria) this;
        }

        public Criteria andLandNatureBetween(String value1, String value2) {
            addCriterion("land_nature between", value1, value2, "landNature");
            return (Criteria) this;
        }

        public Criteria andLandNatureNotBetween(String value1, String value2) {
            addCriterion("land_nature not between", value1, value2, "landNature");
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

        public Criteria andLandAreaEqualTo(String value) {
            addCriterion("land_area =", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotEqualTo(String value) {
            addCriterion("land_area <>", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaGreaterThan(String value) {
            addCriterion("land_area >", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaGreaterThanOrEqualTo(String value) {
            addCriterion("land_area >=", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaLessThan(String value) {
            addCriterion("land_area <", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaLessThanOrEqualTo(String value) {
            addCriterion("land_area <=", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaLike(String value) {
            addCriterion("land_area like", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotLike(String value) {
            addCriterion("land_area not like", value, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaIn(List<String> values) {
            addCriterion("land_area in", values, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotIn(List<String> values) {
            addCriterion("land_area not in", values, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaBetween(String value1, String value2) {
            addCriterion("land_area between", value1, value2, "landArea");
            return (Criteria) this;
        }

        public Criteria andLandAreaNotBetween(String value1, String value2) {
            addCriterion("land_area not between", value1, value2, "landArea");
            return (Criteria) this;
        }

        public Criteria andHouseNatureIsNull() {
            addCriterion("house_nature is null");
            return (Criteria) this;
        }

        public Criteria andHouseNatureIsNotNull() {
            addCriterion("house_nature is not null");
            return (Criteria) this;
        }

        public Criteria andHouseNatureEqualTo(String value) {
            addCriterion("house_nature =", value, "houseNature");
            return (Criteria) this;
        }

        public Criteria andHouseNatureNotEqualTo(String value) {
            addCriterion("house_nature <>", value, "houseNature");
            return (Criteria) this;
        }

        public Criteria andHouseNatureGreaterThan(String value) {
            addCriterion("house_nature >", value, "houseNature");
            return (Criteria) this;
        }

        public Criteria andHouseNatureGreaterThanOrEqualTo(String value) {
            addCriterion("house_nature >=", value, "houseNature");
            return (Criteria) this;
        }

        public Criteria andHouseNatureLessThan(String value) {
            addCriterion("house_nature <", value, "houseNature");
            return (Criteria) this;
        }

        public Criteria andHouseNatureLessThanOrEqualTo(String value) {
            addCriterion("house_nature <=", value, "houseNature");
            return (Criteria) this;
        }

        public Criteria andHouseNatureLike(String value) {
            addCriterion("house_nature like", value, "houseNature");
            return (Criteria) this;
        }

        public Criteria andHouseNatureNotLike(String value) {
            addCriterion("house_nature not like", value, "houseNature");
            return (Criteria) this;
        }

        public Criteria andHouseNatureIn(List<String> values) {
            addCriterion("house_nature in", values, "houseNature");
            return (Criteria) this;
        }

        public Criteria andHouseNatureNotIn(List<String> values) {
            addCriterion("house_nature not in", values, "houseNature");
            return (Criteria) this;
        }

        public Criteria andHouseNatureBetween(String value1, String value2) {
            addCriterion("house_nature between", value1, value2, "houseNature");
            return (Criteria) this;
        }

        public Criteria andHouseNatureNotBetween(String value1, String value2) {
            addCriterion("house_nature not between", value1, value2, "houseNature");
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

        public Criteria andAreaEqualTo(String value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(String value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(String value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLike(String value) {
            addCriterion("area like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotLike(String value) {
            addCriterion("area not like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<String> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andEvaluationValueIsNull() {
            addCriterion("evaluation_value is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationValueIsNotNull() {
            addCriterion("evaluation_value is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationValueEqualTo(String value) {
            addCriterion("evaluation_value =", value, "evaluationValue");
            return (Criteria) this;
        }

        public Criteria andEvaluationValueNotEqualTo(String value) {
            addCriterion("evaluation_value <>", value, "evaluationValue");
            return (Criteria) this;
        }

        public Criteria andEvaluationValueGreaterThan(String value) {
            addCriterion("evaluation_value >", value, "evaluationValue");
            return (Criteria) this;
        }

        public Criteria andEvaluationValueGreaterThanOrEqualTo(String value) {
            addCriterion("evaluation_value >=", value, "evaluationValue");
            return (Criteria) this;
        }

        public Criteria andEvaluationValueLessThan(String value) {
            addCriterion("evaluation_value <", value, "evaluationValue");
            return (Criteria) this;
        }

        public Criteria andEvaluationValueLessThanOrEqualTo(String value) {
            addCriterion("evaluation_value <=", value, "evaluationValue");
            return (Criteria) this;
        }

        public Criteria andEvaluationValueLike(String value) {
            addCriterion("evaluation_value like", value, "evaluationValue");
            return (Criteria) this;
        }

        public Criteria andEvaluationValueNotLike(String value) {
            addCriterion("evaluation_value not like", value, "evaluationValue");
            return (Criteria) this;
        }

        public Criteria andEvaluationValueIn(List<String> values) {
            addCriterion("evaluation_value in", values, "evaluationValue");
            return (Criteria) this;
        }

        public Criteria andEvaluationValueNotIn(List<String> values) {
            addCriterion("evaluation_value not in", values, "evaluationValue");
            return (Criteria) this;
        }

        public Criteria andEvaluationValueBetween(String value1, String value2) {
            addCriterion("evaluation_value between", value1, value2, "evaluationValue");
            return (Criteria) this;
        }

        public Criteria andEvaluationValueNotBetween(String value1, String value2) {
            addCriterion("evaluation_value not between", value1, value2, "evaluationValue");
            return (Criteria) this;
        }

        public Criteria andPrincipalBalanceIsNull() {
            addCriterion("principal_balance is null");
            return (Criteria) this;
        }

        public Criteria andPrincipalBalanceIsNotNull() {
            addCriterion("principal_balance is not null");
            return (Criteria) this;
        }

        public Criteria andPrincipalBalanceEqualTo(String value) {
            addCriterion("principal_balance =", value, "principalBalance");
            return (Criteria) this;
        }

        public Criteria andPrincipalBalanceNotEqualTo(String value) {
            addCriterion("principal_balance <>", value, "principalBalance");
            return (Criteria) this;
        }

        public Criteria andPrincipalBalanceGreaterThan(String value) {
            addCriterion("principal_balance >", value, "principalBalance");
            return (Criteria) this;
        }

        public Criteria andPrincipalBalanceGreaterThanOrEqualTo(String value) {
            addCriterion("principal_balance >=", value, "principalBalance");
            return (Criteria) this;
        }

        public Criteria andPrincipalBalanceLessThan(String value) {
            addCriterion("principal_balance <", value, "principalBalance");
            return (Criteria) this;
        }

        public Criteria andPrincipalBalanceLessThanOrEqualTo(String value) {
            addCriterion("principal_balance <=", value, "principalBalance");
            return (Criteria) this;
        }

        public Criteria andPrincipalBalanceLike(String value) {
            addCriterion("principal_balance like", value, "principalBalance");
            return (Criteria) this;
        }

        public Criteria andPrincipalBalanceNotLike(String value) {
            addCriterion("principal_balance not like", value, "principalBalance");
            return (Criteria) this;
        }

        public Criteria andPrincipalBalanceIn(List<String> values) {
            addCriterion("principal_balance in", values, "principalBalance");
            return (Criteria) this;
        }

        public Criteria andPrincipalBalanceNotIn(List<String> values) {
            addCriterion("principal_balance not in", values, "principalBalance");
            return (Criteria) this;
        }

        public Criteria andPrincipalBalanceBetween(String value1, String value2) {
            addCriterion("principal_balance between", value1, value2, "principalBalance");
            return (Criteria) this;
        }

        public Criteria andPrincipalBalanceNotBetween(String value1, String value2) {
            addCriterion("principal_balance not between", value1, value2, "principalBalance");
            return (Criteria) this;
        }

        public Criteria andMortgageStatusIsNull() {
            addCriterion("mortgage_status is null");
            return (Criteria) this;
        }

        public Criteria andMortgageStatusIsNotNull() {
            addCriterion("mortgage_status is not null");
            return (Criteria) this;
        }

        public Criteria andMortgageStatusEqualTo(String value) {
            addCriterion("mortgage_status =", value, "mortgageStatus");
            return (Criteria) this;
        }

        public Criteria andMortgageStatusNotEqualTo(String value) {
            addCriterion("mortgage_status <>", value, "mortgageStatus");
            return (Criteria) this;
        }

        public Criteria andMortgageStatusGreaterThan(String value) {
            addCriterion("mortgage_status >", value, "mortgageStatus");
            return (Criteria) this;
        }

        public Criteria andMortgageStatusGreaterThanOrEqualTo(String value) {
            addCriterion("mortgage_status >=", value, "mortgageStatus");
            return (Criteria) this;
        }

        public Criteria andMortgageStatusLessThan(String value) {
            addCriterion("mortgage_status <", value, "mortgageStatus");
            return (Criteria) this;
        }

        public Criteria andMortgageStatusLessThanOrEqualTo(String value) {
            addCriterion("mortgage_status <=", value, "mortgageStatus");
            return (Criteria) this;
        }

        public Criteria andMortgageStatusLike(String value) {
            addCriterion("mortgage_status like", value, "mortgageStatus");
            return (Criteria) this;
        }

        public Criteria andMortgageStatusNotLike(String value) {
            addCriterion("mortgage_status not like", value, "mortgageStatus");
            return (Criteria) this;
        }

        public Criteria andMortgageStatusIn(List<String> values) {
            addCriterion("mortgage_status in", values, "mortgageStatus");
            return (Criteria) this;
        }

        public Criteria andMortgageStatusNotIn(List<String> values) {
            addCriterion("mortgage_status not in", values, "mortgageStatus");
            return (Criteria) this;
        }

        public Criteria andMortgageStatusBetween(String value1, String value2) {
            addCriterion("mortgage_status between", value1, value2, "mortgageStatus");
            return (Criteria) this;
        }

        public Criteria andMortgageStatusNotBetween(String value1, String value2) {
            addCriterion("mortgage_status not between", value1, value2, "mortgageStatus");
            return (Criteria) this;
        }

        public Criteria andBisSealUpIsNull() {
            addCriterion("bis_seal_up is null");
            return (Criteria) this;
        }

        public Criteria andBisSealUpIsNotNull() {
            addCriterion("bis_seal_up is not null");
            return (Criteria) this;
        }

        public Criteria andBisSealUpEqualTo(String value) {
            addCriterion("bis_seal_up =", value, "bisSealUp");
            return (Criteria) this;
        }

        public Criteria andBisSealUpNotEqualTo(String value) {
            addCriterion("bis_seal_up <>", value, "bisSealUp");
            return (Criteria) this;
        }

        public Criteria andBisSealUpGreaterThan(String value) {
            addCriterion("bis_seal_up >", value, "bisSealUp");
            return (Criteria) this;
        }

        public Criteria andBisSealUpGreaterThanOrEqualTo(String value) {
            addCriterion("bis_seal_up >=", value, "bisSealUp");
            return (Criteria) this;
        }

        public Criteria andBisSealUpLessThan(String value) {
            addCriterion("bis_seal_up <", value, "bisSealUp");
            return (Criteria) this;
        }

        public Criteria andBisSealUpLessThanOrEqualTo(String value) {
            addCriterion("bis_seal_up <=", value, "bisSealUp");
            return (Criteria) this;
        }

        public Criteria andBisSealUpLike(String value) {
            addCriterion("bis_seal_up like", value, "bisSealUp");
            return (Criteria) this;
        }

        public Criteria andBisSealUpNotLike(String value) {
            addCriterion("bis_seal_up not like", value, "bisSealUp");
            return (Criteria) this;
        }

        public Criteria andBisSealUpIn(List<String> values) {
            addCriterion("bis_seal_up in", values, "bisSealUp");
            return (Criteria) this;
        }

        public Criteria andBisSealUpNotIn(List<String> values) {
            addCriterion("bis_seal_up not in", values, "bisSealUp");
            return (Criteria) this;
        }

        public Criteria andBisSealUpBetween(String value1, String value2) {
            addCriterion("bis_seal_up between", value1, value2, "bisSealUp");
            return (Criteria) this;
        }

        public Criteria andBisSealUpNotBetween(String value1, String value2) {
            addCriterion("bis_seal_up not between", value1, value2, "bisSealUp");
            return (Criteria) this;
        }

        public Criteria andExcelRowIndexIsNull() {
            addCriterion("excel_row_index is null");
            return (Criteria) this;
        }

        public Criteria andExcelRowIndexIsNotNull() {
            addCriterion("excel_row_index is not null");
            return (Criteria) this;
        }

        public Criteria andExcelRowIndexEqualTo(Integer value) {
            addCriterion("excel_row_index =", value, "excelRowIndex");
            return (Criteria) this;
        }

        public Criteria andExcelRowIndexNotEqualTo(Integer value) {
            addCriterion("excel_row_index <>", value, "excelRowIndex");
            return (Criteria) this;
        }

        public Criteria andExcelRowIndexGreaterThan(Integer value) {
            addCriterion("excel_row_index >", value, "excelRowIndex");
            return (Criteria) this;
        }

        public Criteria andExcelRowIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("excel_row_index >=", value, "excelRowIndex");
            return (Criteria) this;
        }

        public Criteria andExcelRowIndexLessThan(Integer value) {
            addCriterion("excel_row_index <", value, "excelRowIndex");
            return (Criteria) this;
        }

        public Criteria andExcelRowIndexLessThanOrEqualTo(Integer value) {
            addCriterion("excel_row_index <=", value, "excelRowIndex");
            return (Criteria) this;
        }

        public Criteria andExcelRowIndexIn(List<Integer> values) {
            addCriterion("excel_row_index in", values, "excelRowIndex");
            return (Criteria) this;
        }

        public Criteria andExcelRowIndexNotIn(List<Integer> values) {
            addCriterion("excel_row_index not in", values, "excelRowIndex");
            return (Criteria) this;
        }

        public Criteria andExcelRowIndexBetween(Integer value1, Integer value2) {
            addCriterion("excel_row_index between", value1, value2, "excelRowIndex");
            return (Criteria) this;
        }

        public Criteria andExcelRowIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("excel_row_index not between", value1, value2, "excelRowIndex");
            return (Criteria) this;
        }

        public Criteria andBisImportIsNull() {
            addCriterion("bis_import is null");
            return (Criteria) this;
        }

        public Criteria andBisImportIsNotNull() {
            addCriterion("bis_import is not null");
            return (Criteria) this;
        }

        public Criteria andBisImportEqualTo(Boolean value) {
            addCriterion("bis_import =", value, "bisImport");
            return (Criteria) this;
        }

        public Criteria andBisImportNotEqualTo(Boolean value) {
            addCriterion("bis_import <>", value, "bisImport");
            return (Criteria) this;
        }

        public Criteria andBisImportGreaterThan(Boolean value) {
            addCriterion("bis_import >", value, "bisImport");
            return (Criteria) this;
        }

        public Criteria andBisImportGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_import >=", value, "bisImport");
            return (Criteria) this;
        }

        public Criteria andBisImportLessThan(Boolean value) {
            addCriterion("bis_import <", value, "bisImport");
            return (Criteria) this;
        }

        public Criteria andBisImportLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_import <=", value, "bisImport");
            return (Criteria) this;
        }

        public Criteria andBisImportIn(List<Boolean> values) {
            addCriterion("bis_import in", values, "bisImport");
            return (Criteria) this;
        }

        public Criteria andBisImportNotIn(List<Boolean> values) {
            addCriterion("bis_import not in", values, "bisImport");
            return (Criteria) this;
        }

        public Criteria andBisImportBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_import between", value1, value2, "bisImport");
            return (Criteria) this;
        }

        public Criteria andBisImportNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_import not between", value1, value2, "bisImport");
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