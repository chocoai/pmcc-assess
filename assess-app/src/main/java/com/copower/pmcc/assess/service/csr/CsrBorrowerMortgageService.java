package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.basis.dao.csr.CsrBorrowerMortgageDao;
import com.copower.pmcc.assess.dal.basis.entity.CsrBorrowerMortgage;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.csr.CsrBorrowerMortgageVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanFinancialClaimService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
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
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseAttachmentService baseAttachmentService;


    public CsrBorrowerMortgage saveCsrBorrowerMortgage(CsrBorrowerMortgage csrBorrowerMortgage, Integer detailsId, String taskRemarks, String actualHours) throws BusinessException {
        try {
            if (csrBorrowerMortgage.getId() == null || csrBorrowerMortgage.getId() <= 0) {
                csrBorrowerMortgageDao.addCsrBorrowerMortgage(csrBorrowerMortgage);
                //更新附件信息
                SysAttachmentDto baseAttachment = new SysAttachmentDto();
                baseAttachment.setTableName(FormatUtils.entityNameConvertToTableName(CsrBorrowerMortgage.class));
                baseAttachment.setTableId(0);
                baseAttachment.setCreater(processControllerComponent.getThisUser());

                SysAttachmentDto baseAttachmentNew = new SysAttachmentDto();
                baseAttachmentNew.setTableId(csrBorrowerMortgage.getId());

                baseAttachmentService.updateAttachementByExample(baseAttachment, baseAttachmentNew);
            } else {
                csrBorrowerMortgageDao.updateCsrBorrowerMortgage(csrBorrowerMortgage);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        projectPlanFinancialClaimService.updatePlanTaskActualHours(detailsId, taskRemarks, actualHours);
        return csrBorrowerMortgage;
    }

    public void deleteCsrBorrowerMortgage(Integer id) throws BusinessException
    {
        try {
            csrBorrowerMortgageDao.deleteCsrBorrowerMortgage(id);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public List<CsrBorrowerMortgageVo> getCsrBorrowerMortgage(String borrowerId, Integer detailsId) {
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

    public List<CsrBorrowerMortgage> getCsrProjectMortgages(Integer projectID){
        return csrBorrowerMortgageDao.getCsrProjectMortgages(projectID);
    }
}
