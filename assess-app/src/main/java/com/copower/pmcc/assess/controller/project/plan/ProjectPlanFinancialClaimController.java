package com.copower.pmcc.assess.controller.project.plan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.input.project.ProjectPlanFinancialClaimFastDto;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.dto.output.project.csr.CsrBorrowerVo;
import com.copower.pmcc.assess.service.csr.CsrBorrowerService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanFinancialClaimService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private CsrBorrowerService csrBorrowerService;

    @ResponseBody
    @RequestMapping(value = "/getProjectPlanDetailsByPlanApply", name = "取得项目详情计划", method = RequestMethod.GET)
    public BootstrapTableVo getProjectPlanDetailsByPlanApply(Integer projectId, Integer planId) {

        BootstrapTableVo bootstrapTableVo = projectPlanFinancialClaimService.getProjectPlanDetailsByProjectId(projectId, planId);
        return bootstrapTableVo;

    }

    @ResponseBody
    @RequestMapping(value = "/getProjectPlanCustomer", name = "取得项目的客户", method = RequestMethod.GET)
    public HttpResult getProjectPlanCustomer(Integer projectId) {
        try {
            List<CsrBorrowerVo> csrBorrowerVos = csrBorrowerService.getCsrBorrowerByProjectId(projectId);
            return HttpResult.newCorrectResult(csrBorrowerVos);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

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

    @ResponseBody
    @RequestMapping(value = "/saveProjectPlan", name = "保存项目计划", method = RequestMethod.POST)
    public HttpResult saveProjectPlan(String formData) {
        try {
            projectPlanFinancialClaimService.saveFinancialClaimProjectPlan(formData);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/submitTask", name = "提交工作成果", method = RequestMethod.POST)
    public HttpResult submitTask(String projectDetailsIds, Integer projectDetailsId) {
        try {
            projectPlanFinancialClaimService.submitTask(projectDetailsIds, projectDetailsId);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
