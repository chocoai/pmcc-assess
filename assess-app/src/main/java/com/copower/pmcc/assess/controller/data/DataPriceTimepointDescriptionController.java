package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.basis.entity.DataPriceTimepointDescription;
import com.copower.pmcc.assess.service.data.DataPriceTimepointDescriptionService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
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

@RequestMapping(value = "/priceTimepoint")
@Controller
public class
DataPriceTimepointDescriptionController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @Autowired
    private DataPriceTimepointDescriptionService dataPriceTimepointDescriptionService;

    @RequestMapping(value = "/Index", name = "价值时点描述查看")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/dataPriceTimepoint");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getPriceTimepointDescription", name = "取得价值时点描述", method = RequestMethod.GET)
    public BootstrapTableVo getpriceTimepointDescription(@RequestParam(value = "name") String name) {
        return dataPriceTimepointDescriptionService.getPriceTimepointListVo(name);
    }

    @ResponseBody
    @RequestMapping(value = "/addPriceTimepointDescription", name = "增加价值时点描述", method = RequestMethod.POST)
    public HttpResult addPriceTimepointDescription(DataPriceTimepointDescription dataPriceTimepointDescription) {
        try {
            if (dataPriceTimepointDescription.getId() != null && dataPriceTimepointDescription.getId() != 0) {
                dataPriceTimepointDescriptionService.editPriceTimepointDescription(dataPriceTimepointDescription);
            } else {
                if(dataPriceTimepointDescriptionService.addPriceTimepointDescription(dataPriceTimepointDescription) == false){
                    return HttpResult.newErrorResult("添加内容已存在!");
                }
            }
        } catch (BusinessException e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/deletePriceTimepointDescription", name = "删除价值时点描述", method = RequestMethod.POST)
    public HttpResult deletePriceTimepointDescription(@RequestParam(value = "id") Integer id) {
        dataPriceTimepointDescriptionService.deletePriceTimepointDescription(id);
        return HttpResult.newCorrectResult();
    }
}
