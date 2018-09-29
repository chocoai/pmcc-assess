package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.basis.entity.DataTaxRateAllocation;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataTaxRateAllocationService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: zch
 * @Date: 2018/9/7 10:00
 * @Description:税率配置
 */
@RequestMapping(value = "/dataTaxRateAllocation")
@Controller
public class DataTaxRateAllocationController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataTaxRateAllocationService dataTaxRateAllocationService;
    @Autowired
    private ErpAreaService erpAreaService;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataTaxRateAllocationView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());//所有省份
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataTaxRateAllocationById", method = {RequestMethod.GET}, name = "获取税率配置")
    public HttpResult getById(Integer id) {
        DataTaxRateAllocation dataTaxRateAllocation = null;
        try {
            if (id != null) {
                dataTaxRateAllocation = dataTaxRateAllocationService.getDataTaxRateAllocationById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(dataTaxRateAllocation);
    }

    @ResponseBody
    @RequestMapping(value = "/getDataTaxRateAllocationList", method = {RequestMethod.GET}, name = "获取税率配置列表")
    public BootstrapTableVo getExamineEstateNetworkList(String type,String nationalUnity) {
        BootstrapTableVo vo = null;
        DataTaxRateAllocation dataTaxRateAllocation = new DataTaxRateAllocation();
        if (!StringUtils.isEmpty(type)) {
            dataTaxRateAllocation.setType(type);
        }
        if (!StringUtils.isEmpty(nationalUnity)){
            dataTaxRateAllocation.setNationalUnity(nationalUnity);
        }
        try {
            vo = dataTaxRateAllocationService.getDataTaxRateAllocationListVos(dataTaxRateAllocation);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDataTaxRateAllocationById", method = {RequestMethod.POST}, name = "删除税率配置")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                DataTaxRateAllocation dataTaxRateAllocation = new DataTaxRateAllocation();
                dataTaxRateAllocation.setId(id);
                dataTaxRateAllocationService.removeDataTaxRateAllocation(dataTaxRateAllocation);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDataTaxRateAllocation", method = {RequestMethod.POST}, name = "更新税率配置")
    public HttpResult saveAndUpdate(DataTaxRateAllocation dataTaxRateAllocation) {
        try {
            dataTaxRateAllocationService.saveAndUpdateDataTaxRateAllocation(dataTaxRateAllocation);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }
}
