package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.entity.InfrastructureCost;
import com.copower.pmcc.assess.service.data.DataInfrastructureCostService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author liuwei
 */
@Controller
@RequestMapping(value = "/infrastructureCost")
public class DataInfrastructureCostController {
    @Autowired
    private DataInfrastructureCostService dataInfrastructureCostService;

    @ResponseBody
    @RequestMapping(value = "/list",name = "获取基础设施费用列表",method = {RequestMethod.POST,RequestMethod.GET})
    public BootstrapTableVo list(String name){
        return dataInfrastructureCostService.getInfrastructureCost(name);
    }

    @ResponseBody
    @RequestMapping(value = "/addAndEdit",name = "新增或者编辑",method = RequestMethod.POST)
    public HttpResult addAndEdit(InfrastructureCost infrastructureCost){

        try {
            if (infrastructureCost.getId() != null && infrastructureCost.getId() != 0){
                dataInfrastructureCostService.editInfrastructureCost(infrastructureCost);
            }
            else {
                dataInfrastructureCostService.addInfrastructureCost(infrastructureCost);
            }
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/delete",name = "删除基础设施费用",method = RequestMethod.POST)
    public HttpResult delete(Integer id){
         dataInfrastructureCostService.deleteInfrastructure(id);
         return HttpResult.newCorrectResult();
    }

}
