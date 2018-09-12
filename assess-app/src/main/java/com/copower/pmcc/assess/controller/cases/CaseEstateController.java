package com.copower.pmcc.assess.controller.cases;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuilding;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstate;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateLandState;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.cases.CaseBuildingService;
import com.copower.pmcc.assess.service.cases.CaseEstateLandStateService;
import com.copower.pmcc.assess.service.cases.CaseEstateService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 17:13
 * @Description:
 */
@RequestMapping(value = "/caseEstate")
@Controller
public class CaseEstateController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CaseEstateService caseEstateService;
    @Autowired
    private CaseEstateLandStateService caseEstateLandStateService;
    @Autowired
    private CaseBuildingService caseBuildingService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    @RequestMapping(value = "/appView", name = "转到新增页面 ", method = RequestMethod.GET)
    public ModelAndView appView(Integer blockId) {
        String view = "/case/caseEstate/apply/caseEstateView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject("blockId", blockId);
        return modelAndView;
    }

    @RequestMapping(value = "/editView", name = "转到编辑页面 ", method = RequestMethod.GET)
    public ModelAndView editView(Integer id) {
        String view = "/case/caseEstate/apply/caseEstateView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        if (id != null && id.intValue() != 0) {
            CaseEstateLandState caseEstateLandState = new CaseEstateLandState();
            caseEstateLandState.setEstateId(id);
            List<CaseEstateLandState> caseEstateLandStateList = caseEstateLandStateService.getCaseEstateLandStateList(caseEstateLandState);
            if (!ObjectUtils.isEmpty(caseEstateLandStateList)) {
                modelAndView.addObject("caseEstateLandState", caseEstateLandStateList.get(0));
            }
            modelAndView.addObject("caseEstate", caseEstateService.getCaseEstateById(id));
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseEstateById", method = {RequestMethod.GET}, name = "获取案例 楼盘")
    public HttpResult getCaseEstateById(Integer id) {
        CaseEstate caseEstate = null;
        try {
            if (id != null) {
                caseEstate = caseEstateService.getCaseEstateById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseEstate);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseEstateById", method = {RequestMethod.POST}, name = "删除案例 楼盘")
    public HttpResult deleteCaseEstateById(Integer id) {
        List<CaseBuilding> caseBuildingList = null;
        CaseBuilding caseBuilding = new CaseBuilding();
        try {
            if (id != null && id.intValue() != 0) {
                caseBuilding.setEstateId(id);
                caseBuildingList = caseBuildingService.getCaseBuildingList(caseBuilding);
                if (caseBuildingList.size() >= 1) {
                    return HttpResult.newCorrectResult("请删除此楼盘下的楼栋之后在删除此楼盘! remove fail");
                } else {
                    caseEstateService.deleteCaseEstate(id);
                    return HttpResult.newCorrectResult("remove success");
                }
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseEstate", method = {RequestMethod.POST}, name = "更新案例 楼盘")
    public HttpResult saveAndUpdateCaseEstate(String formData) {
        JSONObject jsonObject = JSON.parseObject(formData);
        CaseEstate caseEstate = null;
        CaseEstateLandState caseEstateLandState = null;
        String jsonContent = null;
        try {
            try {
                jsonContent = jsonObject.getString(CaseEstate.class.getSimpleName());
                caseEstate = JSONObject.parseObject(jsonContent, CaseEstate.class);
                jsonContent = null;
                jsonContent = jsonObject.getString(CaseEstateLandState.class.getSimpleName());
                caseEstateLandState = JSONObject.parseObject(jsonContent, CaseEstateLandState.class);
            } catch (Exception e1) {
                logger.error(String.format("exception: %s", e1.getMessage()), e1);
                return HttpResult.newErrorResult("解析异常");
            }
            Integer estateId = null;
            if (caseEstate != null) {
                if (caseEstate.getId() == null) {
                    if (caseEstate.getBlockId() == null) {
                        caseEstate.setBlockId(0);
                    }
                    estateId = caseEstateService.saveAndUpdateCaseEstate(caseEstate);
                } else {
                    caseEstateService.saveAndUpdateCaseEstate(caseEstate);
                }
            }
            if (estateId != null && estateId.intValue() != 0) {
                caseEstateLandState.setEstateId(estateId);
                //更新附件
                baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseEstate.class), estateId);
            }
            caseEstateLandStateService.saveAndUpdateCaseEstateLandState(caseEstateLandState);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseEstateList", method = {RequestMethod.GET}, name = "获取案例 楼盘列表")
    public HttpResult caseEstateList(String name) {
        CaseEstate caseEstate = new CaseEstate();
        if (!StringUtils.isEmpty(name)) {
            caseEstate.setName(name);
        }
        List<CaseEstate> caseEstateList = caseEstateService.getCaseEstateList(caseEstate);
        return HttpResult.newCorrectResult(caseEstateList);
    }

    @ResponseBody
    @RequestMapping(value = "/autoCompleteCaseEstate", method = {RequestMethod.GET}, name = "楼盘 信息自动补全")
    public HttpResult autoCompleteCaseEstate(String name, Integer maxRows) {
        List<KeyValueDto> keyValueDtos = Lists.newArrayList();
        KeyValueDto keyValueDto = new KeyValueDto();
        try {
            List<CaseEstate> caseEstateList = caseEstateService.autoCompleteCaseEstate(name, maxRows);
            for (CaseEstate caseEstate : caseEstateList) {
                keyValueDto.setKey(String.valueOf(caseEstate.getId()));
                keyValueDto.setValue(caseEstate.getName());
                keyValueDtos.add(keyValueDto);
            }
            return HttpResult.newCorrectResult(keyValueDtos);
        } catch (Exception e1) {
            return HttpResult.newErrorResult("异常");
        }
    }

}
