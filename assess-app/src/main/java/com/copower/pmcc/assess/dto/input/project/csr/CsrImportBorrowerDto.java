package com.copower.pmcc.assess.dto.input.project.csr;

import com.copower.pmcc.assess.dal.entity.*;

import java.util.List;

/**
 * Created by kings on 2018-6-7.
 */
public class CsrImportBorrowerDto {
    private CsrBorrower csrBorrower;
    private List<CsrBorrowerMortgage> csrBorrowerMortgageList;
    private List<CsrContract> csrContractList;
    private List<CsrGuarantor> csrGuarantorList;
    private List<CsrLitigation> csrLitigationList;
    private List<CsrPrincipalInterest> csrPrincipalInterestList;

    public CsrBorrower getCsrBorrower() {
        return csrBorrower;
    }

    public void setCsrBorrower(CsrBorrower csrBorrower) {
        this.csrBorrower = csrBorrower;
    }

    public List<CsrBorrowerMortgage> getCsrBorrowerMortgageList() {
        return csrBorrowerMortgageList;
    }

    public void setCsrBorrowerMortgageList(List<CsrBorrowerMortgage> csrBorrowerMortgageList) {
        this.csrBorrowerMortgageList = csrBorrowerMortgageList;
    }

    public List<CsrContract> getCsrContractList() {
        return csrContractList;
    }

    public void setCsrContractList(List<CsrContract> csrContractList) {
        this.csrContractList = csrContractList;
    }

    public List<CsrGuarantor> getCsrGuarantorList() {
        return csrGuarantorList;
    }

    public void setCsrGuarantorList(List<CsrGuarantor> csrGuarantorList) {
        this.csrGuarantorList = csrGuarantorList;
    }

    public List<CsrLitigation> getCsrLitigationList() {
        return csrLitigationList;
    }

    public void setCsrLitigationList(List<CsrLitigation> csrLitigationList) {
        this.csrLitigationList = csrLitigationList;
    }

    public List<CsrPrincipalInterest> getCsrPrincipalInterestList() {
        return csrPrincipalInterestList;
    }

    public void setCsrPrincipalInterestList(List<CsrPrincipalInterest> csrPrincipalInterestList) {
        this.csrPrincipalInterestList = csrPrincipalInterestList;
    }
}
