package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReimbursementDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReimbursement;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcAttachmentService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SchemeReimbursementService {
    @Autowired
    private SchemeReimbursementDao schemeReimbursementDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ErpRpcAttachmentService erpRpcAttachmentService;

    public SchemeReimbursement getDataByPlanDetailsId(Integer planDetailsId) {
        SchemeReimbursement where = new SchemeReimbursement();
        where.setPlanDetailsId(planDetailsId);
        return schemeReimbursementDao.getSchemeReimbursement(where);
    }


    public void saveSchemeReimbursement(SchemeReimbursement schemeReimbursement, ProjectPlanDetails projectPlanDetails, String processInsId) throws BusinessException {
        if (schemeReimbursement.getId() > 0) {
            if (!schemeReimbursementDao.editSchemeReimbursement(schemeReimbursement)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        } else {
            schemeReimbursement.setProjectId(projectPlanDetails.getProjectId());
            schemeReimbursement.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
            schemeReimbursement.setPlanDetailsId(projectPlanDetails.getId());
            schemeReimbursement.setCreator(processControllerComponent.getThisUser());
            schemeReimbursement.setProcessInsId(processInsId);
            schemeReimbursement.setStatus(ProcessStatusEnum.RUN.getValue());
            if (!schemeReimbursementDao.addSchemeReimbursement(schemeReimbursement)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
            //更新附件
            SysAttachmentDto sysAttachment = new SysAttachmentDto();
            sysAttachment.setTableId(0);
            sysAttachment.setFieldsName("apply");
            sysAttachment.setTableName(FormatUtils.entityNameConvertToTableName(SchemeReimbursement.class));

            SysAttachmentDto sysAttachmentNew = new SysAttachmentDto();
            sysAttachmentNew.setTableId(schemeReimbursement.getId());
            erpRpcAttachmentService.updateAttachmentByParam(sysAttachment, sysAttachmentNew);
        }
    }

    public SchemeReimbursement getSingleObject(Integer id) {
        return schemeReimbursementDao.getSchemeReimbursement(id);
    }


}
