package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.dao.base.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.csr.CsrContractDao;
import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.dal.entity.CsrContract;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.csr.CsrContractVo;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanFinancialClaimService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
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
public class CsrContractService {
    @Autowired
    private CsrContractDao csrContractDao;
    @Autowired
    private ProjectPlanFinancialClaimService projectPlanFinancialClaimService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseAttachmentDao baseAttachmentDao;
    public CsrContract saveCsrContract(CsrContract csrContract, Integer detailsId, String taskRemarks, String actualHours) throws BusinessException {
        try {
            if (csrContract.getId() == null || csrContract.getId() <= 0) {
                csrContractDao.addCsrContract(csrContract);
                //更新附件信息
                BaseAttachment baseAttachment = new BaseAttachment();
                baseAttachment.setTableName("tb_csr_contract");
                baseAttachment.setTableId(0);
                baseAttachment.setCreater(processControllerComponent.getThisUser());

                BaseAttachment baseAttachmentNew = new BaseAttachment();
                baseAttachmentNew.setTableId(csrContract.getId());

                baseAttachmentDao.updateAttachementByExample(baseAttachment, baseAttachmentNew);
            } else {
                csrContractDao.updateCsrContract(csrContract);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

        projectPlanFinancialClaimService.updatePlanTaskActualHours(detailsId, taskRemarks, actualHours);
        return csrContract;
    }

    public CsrContractVo loadCsrContract(Integer borrowerId, Integer detailsId) throws BusinessException {
        CsrContract csrContractReturn = null;
        CsrContract csrContract = new CsrContract();
        csrContract.setBorrowerId(borrowerId);
        csrContract.setBisImport(false);
        List<CsrContract> csrContractList = csrContractDao.getCsrContractList(csrContract);
        if (CollectionUtils.isEmpty(csrContractList)) {
            csrContract = new CsrContract();
            csrContract.setBorrowerId(borrowerId);
            csrContractList = csrContractDao.getCsrContractList(csrContract);
            if (!CollectionUtils.isEmpty(csrContractList)) {
                csrContractReturn = csrContractList.get(0);
                csrContractReturn.setId(0);
                csrContractReturn.setBisImport(false);
                try {
                    csrContractDao.addCsrContract(csrContractReturn);
                } catch (Exception e) {
                    throw new BusinessException(e.getMessage());
                }
            }
        } else {
            csrContractReturn = csrContractList.get(0);
        }
        CsrContractVo csrContractVo = new CsrContractVo();
        if (csrContractReturn != null) {
            BeanUtils.copyProperties(csrContractReturn, csrContractVo);
            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(detailsId);
            if (projectPlanDetails != null) {
                if(projectPlanDetails.getActualHours()!=null) {
                    csrContractVo.setActualHours(projectPlanDetails.getActualHours().toString());
                }
                csrContractVo.setTaskRemarks(projectPlanDetails.getTaskRemarks());
            }
        }
        return csrContractVo;
    }
}
