package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.HousePriceIndex;
import com.copower.pmcc.assess.service.data.HousePriceIndexService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value = "/housePriceIndex")
@Controller
public class HousePriceIndexAction {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ControllerComponent controllerComponent;

    @Autowired
    private HousePriceIndexService housePriceIndexService;

    @RequestMapping(value = "/view")
    public ModelAndView index(){
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/data/housePriceIndex");
        return modelAndView;
    }

    @RequestMapping(value = "/list")
    public @ResponseBody BootstrapTableVo list(@RequestParam(value = "indexCalendar",defaultValue = "")String indexCalendar){
        BootstrapTableVo vo = null;
        try {
            if (indexCalendar!=null&&indexCalendar!=""){
                return housePriceIndexService.getListVo(indexCalendar);
            }
            vo = housePriceIndexService.getListVo(null);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return vo;
    }

    @RequestMapping(value = "/get")
    public Object get(@RequestParam(value = "id")Integer id){
        HousePriceIndex housePriceIndex = null;
        try {
            housePriceIndex = housePriceIndexService.get(id);
        }catch (Exception e){
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return housePriceIndex;
    }

    @RequestMapping(value = "/save")
    public @ResponseBody HttpResult add(HousePriceIndex housePriceIndex){
        try {
            if (housePriceIndex.getId()!=null&&housePriceIndex.getId()!=0){//不再使用专门的 update controller
                housePriceIndexService.update(housePriceIndex);
            }else {
                housePriceIndexService.addHousePriceIndex(housePriceIndex);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/delete")
    public @ResponseBody HttpResult delete(@RequestParam(value = "id")Integer id){
        try {
            housePriceIndexService.removeHousePriceIndeX(id);
        }catch (Exception e){
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
