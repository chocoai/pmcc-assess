package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseBaseHouse;
import com.copower.pmcc.assess.service.cases.CaseBaseHouseService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kings on 2018-7-6.
 */
@Controller
@RequestMapping(value = "/case",name = "案例 基础")
public class CaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CaseBaseHouseService caseBaseHouseService;

    @RequestMapping(value = "/estateSearch", name = "楼盘案例查询",method = {RequestMethod.GET})
    public ModelAndView estateSearch() {
        String view = "/case/estateSearch" ;
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @RequestMapping(value = "/houseSearch", name = "房屋案例查询",method = {RequestMethod.GET})
    public ModelAndView houseSearch() {
        String view = "/case/houseSearch" ;
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableCaseBaseHouseVo",method = {RequestMethod.GET},name = "获取列表")
    public BootstrapTableVo getBootstrapTableVo(BigDecimal tradingUnitPriceStart, BigDecimal tradingUnitPriceEnd, Date tradingTimeStart, Date tradingTimeEnd, CaseBaseHouse caseBaseHouse){
        BootstrapTableVo vo = null;
        try {
            vo = caseBaseHouseService.getBootstrapTableVo(tradingUnitPriceStart, tradingUnitPriceEnd, tradingTimeStart, tradingTimeEnd, caseBaseHouse);
        } catch (Exception e1) {
            logger.error("",e1);
        }
        return vo;
    }
}
