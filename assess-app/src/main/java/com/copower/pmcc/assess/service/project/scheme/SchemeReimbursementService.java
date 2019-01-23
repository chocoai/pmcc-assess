package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReimbursementDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReimbursement;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SchemeReimbursementService {
    @Autowired
    private SchemeReimbursementDao schemeReimbursementDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

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
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(SchemeReimbursement.class),schemeReimbursement.getId());
        }
    }

    public List<SchemeReimbursement> getSchemeReimbursements(List<Integer> judgeObjectIds){
        return schemeReimbursementDao.getSchemeReimbursements(judgeObjectIds);
    }

    public SchemeReimbursement getSingleObject(Integer id) {
        return schemeReimbursementDao.getSchemeReimbursement(id);
    }


}
