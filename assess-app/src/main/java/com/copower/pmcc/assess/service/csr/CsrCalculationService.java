package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.dao.csr.CsrcalculationDao;
import com.copower.pmcc.assess.dal.entity.CsrCalculation;
import com.copower.pmcc.assess.dal.entity.CsrGuarantor;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.csr.CsrBorrowerMortgageVo;
import com.copower.pmcc.assess.dto.output.project.csr.CsrCalculationVo;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanFinancialClaimService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/1
 * @time: 17:11
 */
@Service
public class CsrCalculationService {
    @Autowired
    private CsrcalculationDao csrcalculationDao;
    @Autowired
    private ProjectPlanFinancialClaimService projectPlanFinancialClaimService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    public CsrCalculation saveCsrCalculation(CsrCalculation csrCalculation, Integer detailsId, String taskRemarks, String actualHours) throws BusinessException {
        try {
            if (csrCalculation.getId() == null || csrCalculation.getId() <= 0) {
                csrcalculationDao.addCsrCalculation(csrCalculation);
            } else {
                csrcalculationDao.updateCsrCalculation(csrCalculation);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        projectPlanFinancialClaimService.updatePlanTaskActualHours(detailsId, taskRemarks, actualHours);
        return csrCalculation;
    }

    public List<CsrCalculationVo> getCsrCalculation(Integer borrowerId, Integer detailsId) {
        List<CsrCalculationVo> csrCalculationVos = new ArrayList<>();
        CsrCalculation csrCalculation = new CsrCalculation();
        csrCalculation.setBorrowerId(borrowerId);
        List<CsrCalculation> csrCalculationList = csrcalculationDao.getCsrCalculationList(csrCalculation);
        if (CollectionUtils.isNotEmpty(csrCalculationList)) {
            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(detailsId);
            csrCalculationVos = LangUtils.transform(csrCalculationList, o -> {
                CsrCalculationVo csrCalculationVo = new CsrCalculationVo();
                BeanUtils.copyProperties(o, csrCalculationVo);
                if (projectPlanDetails.getActualHours() != null) {
                    csrCalculationVo.setActualHours(projectPlanDetails.getActualHours().toString());
                }
                csrCalculationVo.setTaskRemarks(projectPlanDetails.getTaskRemarks());
                return csrCalculationVo;
            });
        }

        return csrCalculationVos;

    }
}
