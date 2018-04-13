package com.copower.pmcc.assess.proxy.face;

import com.copower.pmcc.assess.dal.entity.ProjectPlan;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/25
 * @time: 16:44
 */
public interface ProjectPlanInterface {
    ModelAndView applyView(ProjectPlan projectPlan);

    ModelAndView approvalView(ProjectPlan projectPlan, String taskId, Integer boxId, String agentUserAccount);

    ModelAndView approvalEdit(ProjectPlan projectPlan, String taskId, Integer boxId, String agentUserAccount);

    ModelAndView detailsView(ProjectPlan projectPlan, Integer boxId);
}
