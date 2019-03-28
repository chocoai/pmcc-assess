package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataBuilder;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBuilderService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
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
 * @Date: 2018/7/18 18:55
 * @Description:建造商
 */
@Controller
@RequestMapping(value = "/dataBuilder")
public class DataBuilderController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataBuilderService dataBuilderService;
    @Autowired
    private ProjectInfoService projectInfoService;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataBuilderView";
        List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_BUILDER_QUALIFICATION_LEVEL);
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject("baseList", baseDataDic);
        List<CrmBaseDataDicDto> unitPropertiesList = projectInfoService.getUnitPropertiesList();
        modelAndView.addObject("unitPropertiesList", unitPropertiesList);
        List<BaseDataDic> reputations = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_COMPANY_REPUTATION);
        modelAndView.addObject("reputations", reputations);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataBuilderById", method = {RequestMethod.GET}, name = "获取建造商")
    public HttpResult getById(Integer id) {
        DataBuilder dataBuilder = null;
        try {
            if (id != null) {
                dataBuilder = dataBuilderService.getByDataBuilderId(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(dataBuilder);
    }

    @ResponseBody
    @RequestMapping(value = "/getDataBuilderList", method = {RequestMethod.GET}, name = "获取建造商列表")
    public BootstrapTableVo getExamineEstateNetworkList(String name) {
        BootstrapTableVo vo = null;
        try {
            vo = dataBuilderService.getListVos(name);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDataBuilderById", method = {RequestMethod.POST}, name = "删除建造商")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(dataBuilderService.deleteDataBuilder(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDataBuilder", method = {RequestMethod.POST}, name = "更新建造商")
    public HttpResult saveAndUpdate(DataBuilder dataBuilder) {
        try {
            if (dataBuilder.getId() == null || dataBuilder.getId().equals(0)) {
                dataBuilderService.addDataBuilderReturnId(dataBuilder);
            } else {
                dataBuilderService.updateDataBuilder(dataBuilder);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/dataBuilderList", method = {RequestMethod.GET}, name = "获取建造商 list")
    public HttpResult dataBuilderList(String name) {
        try {
            if (StringUtils.isNotBlank(name)) {
                return HttpResult.newCorrectResult(dataBuilderService.getDataBuilderList(name));
            }else {
                return HttpResult.newCorrectResult(dataBuilderService.getDataBuilderList(null));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasisList", method = {RequestMethod.GET}, name = "获取资质等级")
    public HttpResult getBasisList(Integer id) {
        List<BaseDataDic> baseDataDic = null;
        try {
            if (id != null) {
                baseDataDic = baseDataDicService.getCacheDataDicListByPid(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(baseDataDic);
    }
}
