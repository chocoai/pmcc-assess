package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.dao.csr.CsrPrincipalInterestDao;
import com.copower.pmcc.assess.dal.entity.CsrPrincipalInterest;
import com.copower.pmcc.assess.dal.entity.CsrPrincipalInterest;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.csr.CsrPrincipalInterestVo;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanFinancialClaimService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/1
 * @time: 17:11
 */
@Service
public class CsrPrincipalInterestService {
    @Autowired
    private CsrPrincipalInterestDao csrPrincipalInterestDao;
    @Autowired
    private ProjectPlanFinancialClaimService projectPlanFinancialClaimService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    public CsrPrincipalInterest saveCsrPrincipalInterest(CsrPrincipalInterest csrPrincipalInterest, Integer detailsId, String taskRemarks, String actualHours) throws BusinessException {
        try {
            if (csrPrincipalInterest.getId() == null || csrPrincipalInterest.getId() <= 0) {
                csrPrincipalInterestDao.addCsrPrincipalInterest(csrPrincipalInterest);
            } else {
                csrPrincipalInterestDao.updateCsrPrincipalInterest(csrPrincipalInterest);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        projectPlanFinancialClaimService.updatePlanTaskActualHours(detailsId, taskRemarks, actualHours);
        return csrPrincipalInterest;
    }

    public CsrPrincipalInterestVo loadLoanPrincipalInterest(Integer borrowerId, Integer detailsId) throws BusinessException {
        CsrPrincipalInterest csrContractReturnReturn = null;
        CsrPrincipalInterest csrPrincipalInterest = new CsrPrincipalInterest();
        csrPrincipalInterest.setBorrowerId(borrowerId);
        csrPrincipalInterest.setBisImport(false);
        List<CsrPrincipalInterest> csrPrincipalInterestList = csrPrincipalInterestDao.getCsrPrincipalInterestList(csrPrincipalInterest);
        if (CollectionUtils.isEmpty(csrPrincipalInterestList)) {
            csrPrincipalInterest = new CsrPrincipalInterest();
            csrPrincipalInterest.setBorrowerId(borrowerId);
            csrPrincipalInterestList = csrPrincipalInterestDao.getCsrPrincipalInterestList(csrPrincipalInterest);
            if (!CollectionUtils.isEmpty(csrPrincipalInterestList)) {
                csrContractReturnReturn = csrPrincipalInterestList.get(0);
                csrContractReturnReturn.setId(0);
                csrContractReturnReturn.setBisImport(false);
                try {
                    csrPrincipalInterestDao.addCsrPrincipalInterest(csrContractReturnReturn);
                } catch (Exception e) {
                    throw new BusinessException(e.getMessage());
                }
            }
        } else {
            csrContractReturnReturn = csrPrincipalInterestList.get(0);
        }
        CsrPrincipalInterestVo csrPrincipalInterestVo = new CsrPrincipalInterestVo();
        if (csrContractReturnReturn != null) {
            BeanUtils.copyProperties(csrContractReturnReturn, csrPrincipalInterestVo);
            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(detailsId);
            if (projectPlanDetails != null) {
                if (projectPlanDetails.getActualHours() != null) {
                    csrPrincipalInterestVo.setActualHours(projectPlanDetails.getActualHours().toString());
                }
                csrPrincipalInterestVo.setTaskRemarks(projectPlanDetails.getTaskRemarks());
            }
        }
        return csrPrincipalInterestVo;
    }
}
