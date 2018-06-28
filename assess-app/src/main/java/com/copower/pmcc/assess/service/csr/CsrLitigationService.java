package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.dao.csr.CsrLitigationDao;
import com.copower.pmcc.assess.dal.entity.CsrLitigation;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.csr.CsrLitigationVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanFinancialClaimService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
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
public class CsrLitigationService {
    @Autowired
    private CsrLitigationDao csrLitigationDao;
    @Autowired
    private ProjectPlanFinancialClaimService projectPlanFinancialClaimService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public CsrLitigation saveCsrLitigation(CsrLitigation csrLitigation, Integer detailsId, String taskRemarks, String actualHours) throws BusinessException {
        try {
            if (csrLitigation.getId() == null || csrLitigation.getId() <= 0) {
                csrLitigationDao.addCsrLitigation(csrLitigation);
                //更新附件信息
                SysAttachmentDto baseAttachment = new SysAttachmentDto();
                baseAttachment.setTableName(FormatUtils.entityNameConvertToTableName(CsrLitigation.class));
                baseAttachment.setTableId(0);
                baseAttachment.setCreater(processControllerComponent.getThisUser());

                SysAttachmentDto baseAttachmentNew = new SysAttachmentDto();
                baseAttachmentNew.setTableId(csrLitigation.getId());

                baseAttachmentService.updateAttachementByExample(baseAttachment, baseAttachmentNew);
            } else {
                csrLitigationDao.updateCsrLitigation(csrLitigation);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        projectPlanFinancialClaimService.updatePlanTaskActualHours(detailsId, taskRemarks, actualHours);
        return csrLitigation;
    }

    public CsrLitigationVo loadLoanLitigation(String borrowerId, Integer detailsId) throws BusinessException {
        CsrLitigation csrContractReturnReturn = null;
        CsrLitigation csrLitigation = new CsrLitigation();
        csrLitigation.setBorrowerId(borrowerId);
        csrLitigation.setBisImport(false);
        List<CsrLitigation> csrLitigationList = csrLitigationDao.getCsrLitigationList(csrLitigation);
        if (CollectionUtils.isEmpty(csrLitigationList)) {
            csrLitigation = new CsrLitigation();
            csrLitigation.setBorrowerId(borrowerId);
            csrLitigationList = csrLitigationDao.getCsrLitigationList(csrLitigation);
            if (!CollectionUtils.isEmpty(csrLitigationList)) {
                csrContractReturnReturn = csrLitigationList.get(0);
                csrContractReturnReturn.setId(0);
                csrContractReturnReturn.setBisImport(false);
                try {
                    csrLitigationDao.addCsrLitigation(csrContractReturnReturn);
                } catch (Exception e) {
                    throw new BusinessException(e.getMessage());
                }
            }
        } else {
            csrContractReturnReturn = csrLitigationList.get(0);
        }
        CsrLitigationVo csrLitigationVo = new CsrLitigationVo();
        if (csrContractReturnReturn != null) {
            BeanUtils.copyProperties(csrContractReturnReturn, csrLitigationVo);
            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(detailsId);
            if (projectPlanDetails != null) {
                if(projectPlanDetails.getActualHours()!=null) {
                    csrLitigationVo.setActualHours(projectPlanDetails.getActualHours().toString());
                }
                csrLitigationVo.setTaskRemarks(projectPlanDetails.getTaskRemarks());
            }
        }
        return csrLitigationVo;
    }
}
