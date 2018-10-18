package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.common.DateHelp;
import com.copower.pmcc.assess.dal.basis.entity.Infrastructure;
import com.copower.pmcc.assess.dto.input.data.InfrastructureDto;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.data.DataInfrastructureService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAreaDto;
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

import java.util.List;

/**
 * @author liuwei
 * 2018-10-18 重写
 */
@RequestMapping(value = "/infrastructure")
@Controller
public class DataInfrastructureController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataInfrastructureService dataInfrastructureService;

    @Autowired
    private ErpAreaService erpAreaService;

    @RequestMapping(value = "/Index", name = "发文单位查看")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/dataInfrastructure");
        //获取省
        List<SysAreaDto> provinceList = erpAreaService.getProvinceList();
        modelAndView.addObject("provinceList", provinceList);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/getInfrastructureById", method = {RequestMethod.GET}, name = "获取基础设施维护")
    public HttpResult getById(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(dataInfrastructureService.get(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newErrorResult(String.format("异常! %s", "null point"));
    }

    @ResponseBody
    @RequestMapping(value = "/getInfrastructureList", method = {RequestMethod.GET}, name = "获取基础设施维护列表")
    public BootstrapTableVo getExamineEstateNetworkList(String dispatchUnit) {
        Infrastructure infrastructure = new Infrastructure();
        BootstrapTableVo vo = null;
        if (!StringUtils.isEmpty(dispatchUnit)) {
            infrastructure.setDispatchUnit(dispatchUnit);
        }
        try {
            vo = dataInfrastructureService.getInfrastructure(infrastructure);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteInfrastructureById", method = {RequestMethod.POST}, name = "删除基础设施维护")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                dataInfrastructureService.deleteInfrastructure(id);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateInfrastructure", method = {RequestMethod.POST}, name = "更新基础设施维护")
    public HttpResult saveAndUpdate(InfrastructureDto infrastructureDto) {
        try {
            dataInfrastructureService.saveAndUpdateInfrastructure(infrastructureDto);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/listInfrastructure", method = {RequestMethod.GET}, name = "基础设施维护 list")
    public HttpResult list(String province, String city, String district,String startDate,String endDate,String projectType) {
        try {
            Infrastructure infrastructure = new Infrastructure();
            if (!StringUtils.isEmpty(province)) {
                infrastructure.setProvince(province);
            }
            if (!StringUtils.isEmpty(city)) {
                infrastructure.setCity(city);
            }
            if (!StringUtils.isEmpty(district)) {
                infrastructure.setDistrict(district);
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(startDate)){
                infrastructure.setStartDate(DateHelp.getDateHelp().parse(startDate,null));
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(endDate)){
                infrastructure.setEndDate(DateHelp.getDateHelp().parse(endDate,null));
            }
            if (!StringUtils.isEmpty(projectType)){
                infrastructure.setProjectType(projectType);
            }
            return HttpResult.newCorrectResult(dataInfrastructureService.infrastructureList(infrastructure));
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("异常");
        }
    }


}
