package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureMatchingCost;
import com.copower.pmcc.assess.service.data.DataInfrastructureMatchingCostService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.lang.StringUtils;
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
    public BootstrapTableVo list(Integer pid,String name){
        DataInfrastructureMatchingCost dataInfrastructureMatchingCost = new DataInfrastructureMatchingCost();
        if (pid != null){
            dataInfrastructureMatchingCost.setPid(pid);
        }
        if (StringUtils.isNotBlank(name)){
            dataInfrastructureMatchingCost.setName(name);
        }
        return dataInfrastructureMatchingCostService.getDataInfrastructureCost(dataInfrastructureMatchingCost);
    }

    @ResponseBody
    @RequestMapping(value = "/addAndEdit",name = "新增或者编辑",method = RequestMethod.POST)
    public HttpResult addAndEdit(DataInfrastructureMatchingCost infrastructureMatchingCost){

        try {
            if (infrastructureMatchingCost.getId() != null && infrastructureMatchingCost.getId().intValue() != 0){
                dataInfrastructureMatchingCostService.editDataInfrastructureCost(infrastructureMatchingCost);
            }
            else {
                dataInfrastructureMatchingCostService.addDataInfrastructureCost(infrastructureMatchingCost);
            }

        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(infrastructureMatchingCost.getPid());
    }

    @ResponseBody
    @RequestMapping(value = "/delete",name = "删除公共配套设施费用",method = RequestMethod.POST)
    public HttpResult delete(Integer id){
        DataInfrastructureMatchingCost infrastructureMatchingCost = dataInfrastructureMatchingCostService.getByDataInfrastructureMatchingCost(id);
        dataInfrastructureMatchingCostService.deleteInfrastructure(id);
        return  HttpResult.newCorrectResult(infrastructureMatchingCost.getPid());
    }
}
