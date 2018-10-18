package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureDevTax;
import com.copower.pmcc.assess.service.data.DataInfrastructureDevTaxService;
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

/**
 * @Auther: zch
 * @Date: 2018/10/18 11:54
 * @Description:
 */
@RequestMapping(value = "/dataInfrastructureDevTax")
@Controller
public class DataInfrastructureDevTaxController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DataInfrastructureDevTaxService dataInfrastructureDevTaxService;

    @ResponseBody
    @RequestMapping(value = "/getDataInfrastructureDevTaxById",method = {RequestMethod.GET},name = "获取开发期间税费费用")
    public HttpResult getById(Integer id) {
        DataInfrastructureDevTax dataInfrastructureDevTax = null;
        try {
            if (id!=null){
                dataInfrastructureDevTax = dataInfrastructureDevTaxService.getDataInfrastructureDevTaxById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(dataInfrastructureDevTax);
    }

    @ResponseBody
    @RequestMapping(value = "/getDataInfrastructureDevTaxList",method = {RequestMethod.GET},name = "获取开发期间税费费用列表")
    public BootstrapTableVo getExamineEstateNetworkList(String name) {
        DataInfrastructureDevTax dataInfrastructureDevTax = new DataInfrastructureDevTax();
        if (!StringUtils.isEmpty(name)){
            dataInfrastructureDevTax.setName(name);
        }
        BootstrapTableVo vo = null;
        try {
            vo = dataInfrastructureDevTaxService.getDataInfrastructureDevTaxListVos(dataInfrastructureDevTax);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDataInfrastructureDevTaxById",method = {RequestMethod.POST},name = "删除开发期间税费费用")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                DataInfrastructureDevTax oo = dataInfrastructureDevTaxService.getDataInfrastructureDevTaxById(id);
                DataInfrastructureDevTax dataInfrastructureDevTax = new DataInfrastructureDevTax();
                dataInfrastructureDevTax.setId(id);
                dataInfrastructureDevTaxService.removeDataInfrastructureDevTax(dataInfrastructureDevTax);
                return HttpResult.newCorrectResult(oo.getPid());
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDataInfrastructureDevTax",method = {RequestMethod.POST},name = "更新开发期间税费费用")
    public HttpResult saveAndUpdate(DataInfrastructureDevTax dataInfrastructureDevTax){
        try {
            dataInfrastructureDevTaxService.saveAndUpdateDataInfrastructureDevTax(dataInfrastructureDevTax);
            return HttpResult.newCorrectResult(dataInfrastructureDevTax.getPid());
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/listDataInfrastructureDevTax",method = {RequestMethod.GET},name = "开发期间税费费用 list")
    public HttpResult list(String name){
        try {
            DataInfrastructureDevTax dataInfrastructureDevTax = new DataInfrastructureDevTax() ;
            if (!StringUtils.isEmpty(name)){
                dataInfrastructureDevTax.setName(name);
            }
            return HttpResult.newCorrectResult(dataInfrastructureDevTaxService.dataInfrastructureDevTaxes(dataInfrastructureDevTax));
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("异常");
        }
    }
}
