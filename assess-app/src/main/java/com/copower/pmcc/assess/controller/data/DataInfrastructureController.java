package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.Infrastructure;
import com.copower.pmcc.assess.dto.input.data.InfrastructureDto;
import com.copower.pmcc.assess.service.data.DataInfrastructureService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liuwei
 */
@RequestMapping(value = "/infrastructure")
@Controller
public class DataInfrastructureController {
    @Autowired
    private ControllerComponent controllerComponent;
    @Autowired
    private DataInfrastructureService dataInfrastructureService;

    @RequestMapping(value = "/Index" , name = "发文单位查看")
    public ModelAndView index(){
       ModelAndView modelAndView =  controllerComponent.baseModelAndView("/data/dataInfrastructure");
       return  modelAndView;
    }
    @ResponseBody
    @RequestMapping(value = "/getInfrastructure",name = "获取发文单位信息",method = RequestMethod.GET)
    public BootstrapTableVo getInfrastructure(@RequestParam(value = "name") String name){
        return dataInfrastructureService.getInfrastructure(name);
    }

    @ResponseBody
    @RequestMapping(value = "/addInfrastructure",name = "添加发文单位",method = RequestMethod.POST)
    public HttpResult addInfrastructure(InfrastructureDto infrastructure) {
        try {
            if (infrastructure.getId() != null && infrastructure.getId() != 0){
                dataInfrastructureService.editInfrastructure(infrastructure);
            } else {
                dataInfrastructureService.addInfrastructure(infrastructure);
            }
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/deleteInfrastructure",name = "删除发文单位",method = RequestMethod.POST)
    public HttpResult deleteInfrastructure(@RequestParam("id") Integer id){
        dataInfrastructureService.deleteInfrastructure(id);
        return HttpResult.newCorrectResult();
    }

}
