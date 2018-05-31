package com.copower.pmcc.assess.controller.project.plan;

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

//    @ResponseBody
//    @RequestMapping(value = "/getProjectPlanDetailsByPlanApply", name = "取得项目详情计划", method = RequestMethod.GET)
//    public BootstrapTableVo getProjectPlanDetailsByPlanApply(Integer projectId) {
//
//        List<ProjectPlanDetailsVo> projectPlanDetailsVos = projectPlanFinancialClaimService.getProjectPlanDetailsByPlanApply(planId);
//        if (CollectionUtils.isEmpty(projectPlanDetailsVos)) {
//            try {
//                Boolean aBoolean = projectPlanService.InitProjectPlanDetails(planId);
//                if (aBoolean)//如果加载默认成功，则再添加详情
//                {
//                    projectPlanDetailsVos = projectPlanDetailsService.getProjectPlanDetailsByPlanApply(planId);
//                } else {
//                    projectPlanDetailsVos = new ArrayList<>();
//                }
//            } catch (BusinessException e) {
//                e.printStackTrace();
//            }
//        }
//        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
//        bootstrapTableVo.setTotal((long) projectPlanDetailsVos.size());
//        bootstrapTableVo.setRows(projectPlanDetailsVos);
//        return bootstrapTableVo;
//
//    }
}
