package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.dao.csr.CsrGuarantorDao;
import com.copower.pmcc.assess.dal.entity.CsrGuarantor;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.csr.CsrGuarantorVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
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
public class CsrGuarantorService {
    @Autowired
    private CsrGuarantorDao csrGuarantorDao;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public CsrGuarantor saveCsrGuarantor(CsrGuarantor csrGuarantor, Integer detailsId, String taskRemarks, String actualHours) throws BusinessException {
        try {
            if (csrGuarantor.getId() == null || csrGuarantor.getId() <= 0) {
                csrGuarantorDao.addCsrGuarantor(csrGuarantor);
                //更新附件信息
                SysAttachmentDto baseAttachment = new SysAttachmentDto();
                baseAttachment.setTableName(FormatUtils.entityNameConvertToTableName(CsrGuarantor.class));
                baseAttachment.setTableId(0);
                baseAttachment.setCreater(processControllerComponent.getThisUser());

                SysAttachmentDto baseAttachmentNew = new SysAttachmentDto();
                baseAttachmentNew.setTableId(csrGuarantor.getId());

                baseAttachmentService.updateAttachementByExample(baseAttachment, baseAttachmentNew);
            } else {
                csrGuarantorDao.updateCsrGuarantor(csrGuarantor);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return csrGuarantor;
    }

    public List<CsrGuarantorVo> getCsrGuarantor(String borrowerId, Integer detailsId) {
        List<CsrGuarantorVo> csrGuarantorVos = new ArrayList<>();
        CsrGuarantor csrGuarantor = new CsrGuarantor();
        csrGuarantor.setBorrowerId(borrowerId);
        csrGuarantor.setBisImport(false);
        List<CsrGuarantor> csrBorrowerMortgageList = csrGuarantorDao.getCsrGuarantorList(csrGuarantor);
        if (CollectionUtils.isNotEmpty(csrBorrowerMortgageList)) {
            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(detailsId);
            csrGuarantorVos = LangUtils.transform(csrBorrowerMortgageList, o -> {
                CsrGuarantorVo csrGuarantorVo = new CsrGuarantorVo();
                BeanUtils.copyProperties(o, csrGuarantorVo);
                if (projectPlanDetails.getActualHours() != null) {
                    csrGuarantorVo.setActualHours(projectPlanDetails.getActualHours().toString());
                }
                csrGuarantorVo.setTaskRemarks(projectPlanDetails.getTaskRemarks());
                return csrGuarantorVo;
            });
        }

        return csrGuarantorVos;

    }

    public void deleteCsrGuarantor(Integer id) throws BusinessException
    {
        try {
            csrGuarantorDao.deleteCsrGuarantor(id);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
