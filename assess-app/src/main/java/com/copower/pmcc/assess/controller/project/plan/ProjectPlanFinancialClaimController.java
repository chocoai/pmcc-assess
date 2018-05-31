package com.copower.pmcc.assess.controller.project.plan;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.input.project.ProjectPlanFinancialClaimFastDto;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanFinancialClaimService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/31
 * @time: 10:37
 */
@Controller
@RequestMapping(value = "/planFinancialClaim", name = "债权计划控制类")
public class ProjectPlanFinancialClaimController {
    @Autowired
    private ProjectPlanFinancialClaimService projectPlanFinancialClaimService;

    @ResponseBody
    @RequestMapping(value = "/getProjectPlanDetailsByPlanApply", name = "取得项目详情计划", method = RequestMethod.GET)
    public BootstrapTableVo getProjectPlanDetailsByPlanApply(Integer projectId, Integer planId) {

        List<ProjectPlanDetailsVo> projectPlanDetailsVos = projectPlanFinancialClaimService.getProjectPlanDetailsByProjectId(projectId, planId);
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setTotal((long) projectPlanDetailsVos.size());
        bootstrapTableVo.setRows(projectPlanDetailsVos == null ? new ArrayList<ProjectPlanDetailsVo>() : projectPlanDetailsVos);
        return bootstrapTableVo;

    }

    @ResponseBody
    @RequestMapping(value = "/fastSetPlan", name = "批量保存计划内容", method = RequestMethod.POST)
    public HttpResult fastSetPlan(String formData) {
        try {
            ProjectPlanFinancialClaimFastDto projectPlanFinancialClaimFastDto = JSON.parseObject(formData, ProjectPlanFinancialClaimFastDto.class);
            projectPlanFinancialClaimService.fastSetPlan(projectPlanFinancialClaimFastDto);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/updateProjectPlanDetails", name = "保存更新", method = RequestMethod.POST)
    public HttpResult updateProjectPlanDetails(String formData) {
        try {
            ProjectPlanDetails projectPlanDetails = JSON.parseObject(formData, ProjectPlanDetails.class);
            projectPlanFinancialClaimService.updateProjectPlanDetails(projectPlanDetails);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
