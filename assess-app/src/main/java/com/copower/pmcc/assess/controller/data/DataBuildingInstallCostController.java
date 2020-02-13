package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.data.DataBuildingInstallCostTypeEnum;
import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.entity.DataBuildingInstallCost;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBuildingInstallCostService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 10:08
 * @Description:基础版块维护
 */
@RequestMapping(value = "/dataBuildingInstallCost")
@Controller
public class DataBuildingInstallCostController extends BaseController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataBuildingInstallCostService dataBuildingInstallCostService;
    @Autowired
    private ErpAreaService erpAreaService;


    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataBuildingInstallCostView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        //所有省份
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());
        List<KeyValueDto> dataBuildingInstallCostType = Lists.newArrayList();
        for (DataBuildingInstallCostTypeEnum typeEnum: DataBuildingInstallCostTypeEnum.values()){
            dataBuildingInstallCostType.add(new KeyValueDto(typeEnum.getKey(),typeEnum.getValue())) ;
        }
        modelAndView.addObject("dataBuildingInstallCostType",dataBuildingInstallCostType) ;
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataBuildingInstallCostById", method = {RequestMethod.GET}, name = "获取基础版块维护")
    public HttpResult getDataBuildingInstallCostById(Integer id) {
        DataBuildingInstallCost dataBuildingInstallCost = null;
        try {
            if (id != null) {
                dataBuildingInstallCost = dataBuildingInstallCostService.getDataBuildingInstallCostById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(dataBuildingInstallCost);
    }

    @ResponseBody
    @RequestMapping(value = "/getDataBuildingInstallCostList", method = {RequestMethod.GET}, name = "获取基础版块维护列表")
    public BootstrapTableVo getDataBuildingInstallCostList(String province, String city, String district) {
        BootstrapTableVo vo = null;
        try {
            vo = dataBuildingInstallCostService.getDataBuildingInstallCostListVos(province, city, district);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDataBuildingInstallCostById", method = {RequestMethod.POST}, name = "删除基础版块维护")
    public HttpResult deleteDataBuildingInstallCostById(Integer id) {
        try {
            if (id != null) {
                DataBuildingInstallCost dataBuildingInstallCost = new DataBuildingInstallCost();
                dataBuildingInstallCost.setId(id);
                dataBuildingInstallCostService.removeDataBuildingInstallCost(dataBuildingInstallCost);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDataBuildingInstallCost", method = {RequestMethod.POST}, name = "更新基础版块维护")
    public HttpResult saveAndUpdateDataBuildingInstallCost(String formData) {
        DataBuildingInstallCost dataBuildingInstallCost = null;
        try {
            dataBuildingInstallCost = JSONObject.parseObject(formData, DataBuildingInstallCost.class);
            dataBuildingInstallCostService.saveAndUpdateDataBuildingInstallCost(dataBuildingInstallCost);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

}
