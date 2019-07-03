package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.DataInfrastructureEnum;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructure;
import com.copower.pmcc.assess.dto.output.data.InfrastructureVo;
import com.copower.pmcc.assess.service.data.DataInfrastructureService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * @author zch
 */
@RequestMapping(value = "/infrastructure")
@Controller
public class DataInfrastructureController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataInfrastructureService dataInfrastructureService;

    @RequestMapping(value = "/Index", name = "发文单位查看")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/dataInfrastructure");
        List<KeyValueDto> keyValueDtos = Lists.newArrayList();
        Arrays.asList(DataInfrastructureEnum.values()).forEach(oo ->{
            keyValueDtos.add(new KeyValueDto(oo.getName(),oo.getRemark())) ;
        });
        modelAndView.addObject("keyValueDtos",keyValueDtos) ;
        modelAndView.addObject("JSONkeyValueDtos",JSONObject.toJSONString(keyValueDtos)) ;
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/getInfrastructureById", method = {RequestMethod.GET}, name = "获取基础设施维护")
    public HttpResult getById(Integer id) {
        try {
            return HttpResult.newCorrectResult(dataInfrastructureService.getDataInfrastructure(id));
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getInfrastructureList", method = {RequestMethod.GET}, name = "获取基础设施维护列表")
    public BootstrapTableVo getInfrastructureList(DataInfrastructure infrastructure) {
        BootstrapTableVo vo = new BootstrapTableVo();
        try {
            vo = dataInfrastructureService.getInfrastructure(infrastructure);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteInfrastructureById", method = {RequestMethod.POST}, name = "删除基础设施维护")
    public HttpResult delete(Integer id) {
        try {
            dataInfrastructureService.deleteInfrastructure(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateInfrastructure", method = {RequestMethod.POST}, name = "更新基础设施维护")
    public HttpResult saveAndUpdate(String formData) {
        try {
            DataInfrastructure infrastructure = JSONObject.parseObject(formData,DataInfrastructure.class) ;
            dataInfrastructureService.saveAndUpdateInfrastructure(infrastructure);
            return HttpResult.newCorrectResult(infrastructure);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/listInfrastructure", method = {RequestMethod.GET}, name = "基础设施维护 list")
    public HttpResult list(String formData) {
        try {
            DataInfrastructure infrastructure = JSONObject.parseObject(formData,DataInfrastructure.class) ;
            return HttpResult.newCorrectResult(dataInfrastructureService.infrastructureList(infrastructure));
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/calculatingMethod", method = {RequestMethod.GET}, name = "测算方法使用")
    public HttpResult calculatingMethod(String province,String city,String district) {
        try {
            List<InfrastructureVo> infrastructureVoList = dataInfrastructureService.calculatingMethod(province, city, district) ;
            return HttpResult.newCorrectResult(infrastructureVoList);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("异常");
        }
    }


}
