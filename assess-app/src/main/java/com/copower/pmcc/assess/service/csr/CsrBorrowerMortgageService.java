package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.dao.csr.CsrBorrowerMortgageDao;
import com.copower.pmcc.assess.dal.entity.CsrBorrowerMortgage;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.csr.CsrBorrowerMortgageVo;
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
public class CsrBorrowerMortgageService {
    @Autowired
    private CsrBorrowerMortgageDao csrBorrowerMortgageDao;
    @Autowired
    private ProjectPlanFinancialClaimService projectPlanFinancialClaimService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    public CsrBorrowerMortgage saveCsrBorrowerMortgage(CsrBorrowerMortgage csrBorrowerMortgage, Integer detailsId, String taskRemarks, String actualHours) throws BusinessException {
        try {
            if (csrBorrowerMortgage.getId() == null || csrBorrowerMortgage.getId() <= 0) {
                csrBorrowerMortgageDao.addCsrBorrowerMortgage(csrBorrowerMortgage);
            } else {
                csrBorrowerMortgageDao.updateCsrBorrowerMortgage(csrBorrowerMortgage);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        projectPlanFinancialClaimService.updatePlanTaskActualHours(detailsId, taskRemarks, actualHours);
        return csrBorrowerMortgage;
    }

    public List<CsrBorrowerMortgageVo> getCsrBorrowerMortgage(Integer borrowerId, Integer detailsId) {
        CsrBorrowerMortgage csrBorrowerMortgage = new CsrBorrowerMortgage();
        csrBorrowerMortgage.setBisImport(false);
        csrBorrowerMortgage.setBorrowerId(borrowerId);
        List<CsrBorrowerMortgage> csrBorrowerMortgageList = csrBorrowerMortgageDao.getCsrBorrowerMortgageList(csrBorrowerMortgage);

        List<CsrBorrowerMortgageVo> csrBorrowerMortgageVos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(csrBorrowerMortgageList)) {
            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(detailsId);
            csrBorrowerMortgageVos = LangUtils.transform(csrBorrowerMortgageList, o -> {
                CsrBorrowerMortgageVo csrBorrowerMortgageVo = new CsrBorrowerMortgageVo();
                BeanUtils.copyProperties(o, csrBorrowerMortgageVo);
                if (projectPlanDetails.getActualHours() != null) {
                    csrBorrowerMortgageVo.setActualHours(projectPlanDetails.getActualHours().toString());
                }
                csrBorrowerMortgageVo.setTaskRemarks(projectPlanDetails.getTaskRemarks());
                return csrBorrowerMortgageVo;
            });
        }

        return csrBorrowerMortgageVos;

    }
}
