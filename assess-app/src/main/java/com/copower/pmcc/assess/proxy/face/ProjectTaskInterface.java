package com.copower.pmcc.assess.proxy.face;

import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/25
 * @time: 16:44
 */
public interface ProjectTaskInterface {
    ModelAndView applyView(ProjectPlanDetails projectPlanDetails);//提交工作成果首页

    ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount);

    ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount);

    ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId);

    void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException;//保存草稿

   void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException;//保存业务数据

    void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException;//保存业务数据

    void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException;//保存业务数据
}
