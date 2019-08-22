package com.copower.pmcc.assess.controller.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.BasicApply;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnit;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.PublicBasicService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/projectTaskCIP")
public class ProjectTaskCIPController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private PublicBasicService publicBasicService;

    @ResponseBody
    @RequestMapping(value = "/saveApplyInfo", method = {RequestMethod.POST}, name = "保存")
    public HttpResult save(String formData, Integer planDetailsId) {
        try {
            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
            Map<String, Object> objectMap = Maps.newHashMap();
            BasicApplyBatch applyBatch = JSON.parseObject(formData, BasicApplyBatch.class);
            applyBatch.setPlanDetailsId(projectPlanDetails.getPid());
            //显示引用案列按钮
            applyBatch.setShowTab(true);
            basicApplyBatchService.saveApplyInfo(applyBatch);
            objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicApplyBatch.class.getSimpleName()), applyBatch);
            return HttpResult.newCorrectResult(objectMap);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getStandardCount", name = "获取标准对象数量", method = RequestMethod.POST)
    public HttpResult getStandardCount(Integer planDetailsId) {
        try {
            List<BasicApply> basicApplyList = basicApplyService.getBasicApplyListByPlanDetailsId(planDetailsId);
            return HttpResult.newCorrectResult(CollectionUtils.isEmpty(basicApplyList) ? 0 : basicApplyList.size());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("获取标准对象数量");
        }
    }

    @RequestMapping(value = "/informationDetail", name = "信息详情页面", method = RequestMethod.GET)
    public ModelAndView informationDetail(Integer id, String buildingType, Integer estateId, Integer type) throws Exception {
        String view = "/basic/informationDetail";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        if (id == 0) {
            modelAndView.addObject("tableId", estateId);
            BasicEstateVo basicEstateVo = publicBasicService.getBasicEstateById(estateId);
            BasicEstateLandStateVo basicEstateLandStateVo = publicBasicService.getEstateLandStateByEstateId(estateId);
            modelAndView.addObject("basicEstate", basicEstateVo);
            modelAndView.addObject("basicEstateLandState", basicEstateLandStateVo);
        } else {
            switch (buildingType) {
                case "building":
                    BasicBuildingVo basicBuilding = publicBasicService.getBasicBuildingById(id);
                    modelAndView.addObject("basicBuilding", basicBuilding);
                    break;
                case "unit":
                    BasicUnit basicUnit = publicBasicService.getBasicUnitById(id);
                    modelAndView.addObject("basicUnit", basicUnit);
                    break;
                case "house":
                    BasicHouseVo basicHouseVo = publicBasicService.getBasicHouseVoById(id);
                    modelAndView.addObject("basicHouse", basicHouseVo);
                    BasicHouseTradingVo basicHouseTradingVo = publicBasicService.getBasicHouseTradingByHouseId(id);
                    modelAndView.addObject("basicHouseTrading", basicHouseTradingVo);
                    break;
            }
            modelAndView.addObject("tableId", id);
        }
        modelAndView.addObject("buildingType", buildingType);
        BasicApply basicApply = new BasicApply();
        basicApply.setType(type);
        modelAndView.addObject("type", type);
        modelAndView.addObject("basicApply", basicApply);
        return modelAndView;

    }

}
