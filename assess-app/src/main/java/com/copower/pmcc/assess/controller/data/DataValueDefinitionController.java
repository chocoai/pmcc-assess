package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataValueDefinition;
import com.copower.pmcc.assess.dto.output.data.DataValueDefinitionVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataValueDefinitionService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/dataValueDefinition")
public class DataValueDefinitionController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DataValueDefinitionService dataValueDefinitionService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataValueDefinition";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        List<BaseDataDic> purposeDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        List<BaseDataDic> valueTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.VALUE_TYPE);
        List<BaseDataDic> propertyScopes = baseDataDicService.getCacheDataDicList("examine.house.scope.property");
        modelAndView.addObject("purposeDicList", purposeDicList);
        modelAndView.addObject("valueTypeList", valueTypeList);
        modelAndView.addObject("propertyScopes", propertyScopes);
        return modelAndView;
    }

    @RequestMapping(value = "/getDataValueDefinitionById", method = {RequestMethod.GET}, name = "通过id获取信息")
    public HttpResult getById(Integer id) {
        DataValueDefinitionVo dataValueDefinitionVo = null;
        try {
            if (id != null) {
                dataValueDefinitionVo = dataValueDefinitionService.getByDataValueDefinitionId(id);
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult(dataValueDefinitionVo);
    }

    @RequestMapping(value = "/getDataValueDefinitionList", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getExamineEstateNetworkList() {
        BootstrapTableVo vo = null;
        try {
            vo = dataValueDefinitionService.getListVos();
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return null;
        }
        return vo;
    }

    @RequestMapping(value = "/deleteDataValueDefinitionById", method = {RequestMethod.POST}, name = "删除")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(dataValueDefinitionService.deleteDataValueDefinition(id));
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return null;
    }

    @RequestMapping(value = "/saveAndUpdateDataValueDefinition", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveAndUpdate(DataValueDefinition dataValueDefinition) {
        try {
            dataValueDefinitionService.saveData(dataValueDefinition);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


}
