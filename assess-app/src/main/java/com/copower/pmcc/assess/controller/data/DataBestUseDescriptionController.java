package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value = "/bestUse")
@Controller
public class DataBestUseDescriptionController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ControllerComponent controllerComponent;

    @RequestMapping(value="/Index" , name="最佳利用描述查看")
    public ModelAndView index(){
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/data/bestUseDescription");
        return modelAndView;
    }

//    @RequestMapping(value="/getBestUseDescription" , name="取得最佳利用描述" , method = RequestMethod.GET)
//    public BootstrapTableVo

}
