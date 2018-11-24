package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuilding;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMain;
import com.copower.pmcc.assess.dto.output.cases.CaseBuildingVo;
import com.copower.pmcc.assess.service.cases.CaseBuildingMainService;
import com.copower.pmcc.assess.service.cases.CaseBuildingService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zch
 * @Date: 2018/10/28 14:29
 * @Description:
 */
@RequestMapping(value = "/caseBuildingMain")
@Controller
public class CaseBuildingMainController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CaseBuildingMainService caseBuildingMainService;
    @Autowired
    private CaseBuildingService caseBuildingService;

    @RequestMapping(value = "/detailView", name = "详情页面 ", method = RequestMethod.GET)
    public ModelAndView editView(Integer id) {
        String view = "/case/caseBuild/caseBuildingView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        CaseBuildingMain caseBuildingMain = caseBuildingMainService.getCaseBuildingMainById(id);
        modelAndView.addObject("caseBuildingMain", caseBuildingMain);
        if(caseBuildingMain!=null){
            List<CaseBuildingVo> caseBuildingList = caseBuildingService.getCaseBuildingListByMainId(caseBuildingMain.getId());
            if(CollectionUtils.isEmpty(caseBuildingList)){
                modelAndView.addObject("caseBuilding", new CaseBuildingVo());
            }else {
                modelAndView.addObject("caseBuilding", caseBuildingList.get(0));
            }
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getcaseBuildingMainById", method = {RequestMethod.GET}, name = "获取案例 楼栋--")
    public HttpResult getCaseBuildingById(Integer id) {
        CaseBuildingMain caseBuildingMain = null;
        List<CaseBuildingVo>  caseBuildingList = null;
        try {
            if (id != null) {
                CaseBuilding query = new CaseBuilding();
                query.setCaseBuildingMainId(id);
                caseBuildingList = caseBuildingService.caseBuildingVoList(query);
                caseBuildingMain = caseBuildingMainService.getCaseBuildingMainById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        Map<String, Object> objectMap = new HashMap<String, Object>(2);
        if (caseBuildingMain != null) {
            objectMap.put("caseBuildingMain", caseBuildingMain);
        }
        if (!ObjectUtils.isEmpty(caseBuildingList)) {
            objectMap.put("caseBuildingList", caseBuildingList);
        }
        return HttpResult.newCorrectResult(objectMap);
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET}, name = "楼栋--列表")
    public BootstrapTableVo getBootstrapTableVo(CaseBuildingMain caseBuildingMain) {
        BootstrapTableVo vo = caseBuildingMainService.getBootstrapTableVo(caseBuildingMain);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdatecaseBuildingMain", method = {RequestMethod.POST}, name = "更新案例 楼栋--")
    public HttpResult saveAndUpdateCaseBuilding(CaseBuildingMain caseBuildingMain) {
        try {
            caseBuildingMainService.saveAndUpdate(caseBuildingMain);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/autoCompleteCaseBuilding", method = {RequestMethod.GET}, name = "楼栋-- 信息自动补全")
    public HttpResult autoCompleteCaseEstate(String identifier, Integer estateId, Integer maxRows) {
        List<KeyValueDto> keyValueDtos = Lists.newArrayList();
        if (StringUtils.isBlank(identifier)) {
            return HttpResult.newCorrectResult(keyValueDtos);
        }
        if (estateId == null) {
            return HttpResult.newCorrectResult(keyValueDtos);
        }
        try {
            List<CaseBuildingMain> buildingMains = caseBuildingMainService.autoCompleteCaseBuildingMain(identifier, estateId, maxRows);
            if (!ObjectUtils.isEmpty(buildingMains)) {
                for (CaseBuildingMain caseBuilding : buildingMains) {
                    KeyValueDto keyValueDto = new KeyValueDto();
                    keyValueDto.setKey(String.valueOf(caseBuilding.getId()));
                    keyValueDto.setValue(caseBuilding.getBuildingNumber());
                    keyValueDtos.add(keyValueDto);
                }
            }
            return HttpResult.newCorrectResult(keyValueDtos);
        } catch (Exception e1) {
            return HttpResult.newErrorResult("异常");
        }
    }
}
