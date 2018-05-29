package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.entity.InfrastructureMatchingCost;
import com.copower.pmcc.assess.service.data.DataInfrastructureMatchingCostService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liuwei
 */
@Controller
@RequestMapping(value = "/infrastructureMatchingCost")
public class DataInfrastructureMatchingCostController {
    @Autowired
    private DataInfrastructureMatchingCostService dataInfrastructureMatchingCostService;

    @ResponseBody
    @RequestMapping(value = "/list",name = "获取公共配套设施费用列表",method = {RequestMethod.POST,RequestMethod.GET})
    public BootstrapTableVo list(String name){
        return dataInfrastructureMatchingCostService.getInfrastructureCost(name);
    }

    @ResponseBody
    @RequestMapping(value = "/addAndEdit",name = "新增或者编辑",method = RequestMethod.POST)
    public HttpResult addAndEdit(InfrastructureMatchingCost infrastructureMatchingCost){

        try {
            if (infrastructureMatchingCost.getId() != null && infrastructureMatchingCost.getId() > 0){
                dataInfrastructureMatchingCostService.editInfrastructureCost(infrastructureMatchingCost);
            }
            else {
                dataInfrastructureMatchingCostService.addInfrastructureCost(infrastructureMatchingCost);
            }
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/delete",name = "删除公共配套设施费用",method = RequestMethod.POST)
    public HttpResult delete(Integer id){
        dataInfrastructureMatchingCostService.deleteInfrastructure(id);
        return  HttpResult.newCorrectResult();
    }
}
