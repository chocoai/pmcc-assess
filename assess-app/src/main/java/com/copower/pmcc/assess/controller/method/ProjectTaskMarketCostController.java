package com.copower.pmcc.assess.controller.method;

import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.data.InfrastructureVo;
import com.copower.pmcc.assess.service.method.MdCostAndDevelopmentOtherService;
import com.copower.pmcc.assess.service.method.MdMarketCostService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Auther: zch
 * @Date: 2018/8/8 11:38
 * @Description:市场成本法以及假设开发法数据传递
 */
@RequestMapping(value = "/marketCost")
@Controller
public class ProjectTaskMarketCostController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private MdMarketCostService mdMarketCostService;
    @Autowired
    private MdCostAndDevelopmentOtherService mdCostAndDevelopmentOtherService;

    @ResponseBody
    @RequestMapping(value = "/getBaseDicTree", name = "获取", method = RequestMethod.GET)
    public BootstrapTableVo getBaseDicTree() {
        BootstrapTableVo vo = mdMarketCostService.getBaseDicTree();
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateMdCostAndDevelopmentOther", name = "建筑安装工程费或者成新率或者其他什么的", method = RequestMethod.POST)
    public HttpResult saveAndUpdateMdCostAndDevelopmentOther(MdCostAndDevelopmentOther mdCostAndDevelopmentOther){
        try {
            mdCostAndDevelopmentOtherService.saveAndUpdateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
        } catch (Exception e1) {
            return HttpResult.newErrorResult(e1.getMessage());
        }
        return HttpResult.newCorrectResult("success");
    }

    @ResponseBody
    @RequestMapping(value = "/listCostAndMatchingCost", name = "获取基础设施费用列表和公共配套设施费用以及基础设施维护", method = RequestMethod.GET)
    public HttpResult listDataInfrastructureCostAndInfrastructureMatchingCost(Integer projectId) {
        Map<Object, Object> map = Maps.newHashMap();
        map.put(DataInfrastructureCost.class.getSimpleName(), mdMarketCostService.infrastructureCostList());
        map.put(DataInfrastructureMatchingCost.class.getSimpleName(), mdMarketCostService.infrastructureMatchingCosts());
        if (projectId == null) {
            map.put(InfrastructureVo.class.getSimpleName(), mdMarketCostService.infrastructureList(null));
        }else {
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
            map.put(InfrastructureVo.class.getSimpleName(), mdMarketCostService.infrastructureList(projectInfo));
        }
        return HttpResult.newCorrectResult(map);
    }

    @ResponseBody
    @RequestMapping(value = "/getAddedValueAdditionalTaxRate", name = "增值及附加税率", method = RequestMethod.GET)
    public HttpResult getAddedValueAdditionalTaxRate() {
        return HttpResult.newCorrectResult(mdMarketCostService.getAddedValueAdditionalTaxRate());
    }

    @ResponseBody
    @RequestMapping(value = "/dataBuildingNewRateList", name = "建筑成新率率", method = RequestMethod.GET)
    public HttpResult dataBuildingNewRateList() {
        return HttpResult.newCorrectResult(mdMarketCostService.dataBuildingNewRateList());
    }
}
