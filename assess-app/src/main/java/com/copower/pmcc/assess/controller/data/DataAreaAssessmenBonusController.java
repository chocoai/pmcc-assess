package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.entity.DataAreaAssessmentBonus;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.NetUrlConfigService;
import com.copower.pmcc.assess.service.data.DataAreaAssessmentBonusService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value = "/dataAreaAssessmentBonus")
@Controller
public class DataAreaAssessmenBonusController extends BaseController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataAreaAssessmentBonusService dataAreaAssessmentBonusService;
    @Autowired
    private ErpAreaService erpAreaService;


    @RequestMapping(value = "/index", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataAreaAssessmentBonusView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());//所有省份
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataAreaAssessmentBonusById", method = {RequestMethod.GET}, name = "获取配置信息")
    public HttpResult getDataAreaAssessmentBonusById(Integer id) {
        DataAreaAssessmentBonus dataAreaAssessmentBonus = null;
        try {
            if (id != null) {
                dataAreaAssessmentBonus = dataAreaAssessmentBonusService.getDataAreaAssessmentBonusById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(dataAreaAssessmentBonus);
    }

    @ResponseBody
    @RequestMapping(value = "/getDataAreaAssessmentBonusList", method = {RequestMethod.GET}, name = "获取配置信息列表")
    public BootstrapTableVo getDataAreaAssessmentBonusList(String province, String city, String district) {
        BootstrapTableVo vo = null;
        try {
            vo = dataAreaAssessmentBonusService.getDataAreaAssessmentBonusListVos(province, city, district);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDataAreaAssessmentBonusById", method = {RequestMethod.POST}, name = "删除配置信息")
    public HttpResult deleteDataAreaAssessmentBonusById(Integer id) {
        try {
            if (id != null) {
                DataAreaAssessmentBonus dataAreaAssessmentBonus = new DataAreaAssessmentBonus();
                dataAreaAssessmentBonus.setId(id);
                dataAreaAssessmentBonusService.removeDataAreaAssessmentBonus(dataAreaAssessmentBonus);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDataAreaAssessmentBonus", method = {RequestMethod.POST}, name = "更新配置信息")
    public HttpResult saveAndUpdateDataAreaAssessmentBonus(DataAreaAssessmentBonus dataAreaAssessmentBonus) {
        try {
            dataAreaAssessmentBonusService.saveAndUpdateDataAreaAssessmentBonus(dataAreaAssessmentBonus);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/dataAreaAssessmentBonusList", method = {RequestMethod.GET}, name = "获取配置信息 list")
    public HttpResult dataAreaAssessmentBonusList(String province, String city, String district) {
        try {
            DataAreaAssessmentBonus dataAreaAssessmentBonus = new DataAreaAssessmentBonus();
            if (StringUtils.isNotBlank(province)) {
                dataAreaAssessmentBonus.setProvince(province);
            }
            if (StringUtils.isNotBlank(city)) {
                dataAreaAssessmentBonus.setCity(city);
            }
            if (StringUtils.isNotBlank(district)) {
                dataAreaAssessmentBonus.setDistrict(district);
            }
            return HttpResult.newCorrectResult(dataAreaAssessmentBonusService.dataAreaAssessmentBonusVos(dataAreaAssessmentBonus));
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("获取配置信息 list exception");
        }
    }
}
