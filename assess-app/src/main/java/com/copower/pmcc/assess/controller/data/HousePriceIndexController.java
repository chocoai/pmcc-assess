package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.entity.HousePriceIndex;
import com.copower.pmcc.assess.dto.input.data.HousePriceIndexDto;
import com.copower.pmcc.assess.service.data.HousePriceIndexService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@RequestMapping(value = "/housePriceIndex")
@Controller
public class HousePriceIndexController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @Autowired
    private HousePriceIndexService housePriceIndexService;

    @RequestMapping(value = "/view")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/housePriceIndex");
        return modelAndView;
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public@ResponseBody

    BootstrapTableVo list(@RequestParam(value = "startTime") String startTime, @RequestParam(value = "endTime") String endTime) {
        BootstrapTableVo vo = null;
        try {
            if ((startTime == "" ) && (endTime == "" )) {
                return housePriceIndexService.getListVo(null, null);
            } else {
                return housePriceIndexService.getListVo(housePriceIndexService.change(startTime), housePriceIndexService.change(endTime));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return vo;
    }

    @RequestMapping(value = "/get")
    public Object get(@RequestParam(value = "id") Integer id) {
        HousePriceIndex housePriceIndex = null;
        try {
            housePriceIndex = housePriceIndexService.get(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return housePriceIndex;
    }

    @RequestMapping(value = "/save",method = {RequestMethod.POST,RequestMethod.GET})
    public
    @ResponseBody
    HttpResult add(HousePriceIndexDto housePriceIndexDto) {
        try {
            if (housePriceIndexDto.getId() != null && housePriceIndexDto.getId() != 0) {//不再使用专门的 update controller
                housePriceIndexService.update(housePriceIndexDto);
            } else {
                housePriceIndexService.addHousePriceIndex(housePriceIndexDto);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/delete")
    public
    @ResponseBody
    HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            housePriceIndexService.removeHousePriceIndeX(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
