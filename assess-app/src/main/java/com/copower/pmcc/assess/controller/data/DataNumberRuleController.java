package com.copower.pmcc.assess.controller.data;


import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.service.data.DataNumberRuleService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@RequestMapping(value="/numberRule")
@Controller
public class DataNumberRuleController {

    @Autowired
    private ControllerComponent controllerComponent;

    @Autowired
    private DataNumberRuleService dataNumberRuleService;

    @RequestMapping(value="/Index" , name="文号规则视图")
    public ModelAndView index(){
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/data/dataNumberRule");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value="getDataNumberRule",name="取得文号规则",method = RequestMethod.GET)
    public BootstrapTableVo getDataNumberRule(@RequestParam(value="name") String name){
        BootstrapTableVo vo = dataNumberRuleService.getDataNumberRuleListVo(name);
                return vo;
    }

}
