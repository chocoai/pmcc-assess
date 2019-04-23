package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataProperty;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataPropertyService;
import com.copower.pmcc.assess.service.data.DataPropertyServiceItemService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.api.dto.ProcessInfoDto;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcProcessInsManagerService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.crm.api.dto.CrmBaseDataDicDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/18 18:54
 * @Description:物业
 */
@Controller
@RequestMapping(value = "/dataProperty")
public class DataPropertyController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataPropertyService dataPropertyService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private DataPropertyServiceItemService dataPropertyServiceItemService;
    @Autowired
    private BpmRpcProcessInsManagerService bpmRpcProcessInsManagerService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataPropertyView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        List<CrmBaseDataDicDto> unitPropertiesList = projectInfoService.getUnitPropertiesList();
        modelAndView.addObject("unitPropertiesList", unitPropertiesList);
        List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_COMPANY_REPUTATION);
        modelAndView.addObject("reputations", baseDataDic);
        List<BaseDataDic> serviceContent = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_SERVICE_CONTENT);
        modelAndView.addObject("serviceContent", serviceContent);
        dataPropertyServiceItemService.initClean();

        try {
            List<ProcessInfoDto> list = bpmRpcProcessInsManagerService.getProcessListByUserAccount("wangpc", 1, 100);
            List<ProjectResponsibilityDto> responsibilityDtoList = bpmRpcProjectTaskService.getProjectTasksByUserAccount("wangpc", 1, 100);
        }catch (Exception e){

        }

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataPropertyById", method = {RequestMethod.GET}, name = "获取物业")
    public HttpResult getById(Integer id) {
        DataProperty dataProperty = null;
        try {
            if (id != null) {
                dataProperty = dataPropertyService.getByDataPropertyId(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(dataProperty);
    }

    @ResponseBody
    @RequestMapping(value = "/getDataPropertyList", method = {RequestMethod.GET}, name = "获取物业列表")
    public BootstrapTableVo getExamineEstateNetworkList(String name) {
        BootstrapTableVo vo = null;
        try {
            vo = dataPropertyService.getListVos(name);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDataPropertyById", method = {RequestMethod.POST}, name = "删除物业")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(dataPropertyService.deleteDataProperty(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDataProperty", method = {RequestMethod.POST}, name = "更新物业")
    public HttpResult saveAndUpdate(DataProperty dataProperty) {
        try {
            dataPropertyService.saveAndUpdate(dataProperty);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/dataPropertyList", method = {RequestMethod.GET}, name = "物业 list")
    public HttpResult dataPropertyList(String name) {
        try {
            DataProperty dataProperty = new DataProperty();
            if (StringUtils.isNotBlank(name)) {
                dataProperty.setName(name);
            }
            return HttpResult.newCorrectResult(dataPropertyService.dataPropertyList(dataProperty));
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }
}
