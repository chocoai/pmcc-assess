package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseBaseHouse;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouse;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTrading;
import com.copower.pmcc.assess.service.cases.CaseBaseHouseService;
import com.copower.pmcc.assess.service.cases.CaseHouseService;
import com.copower.pmcc.assess.service.cases.CaseHouseTradingService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.utils.DateUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.util.List;

/**
 * Created by kings on 2018-7-6.
 */
@Controller
@RequestMapping(value = "/case", name = "案例 基础")
public class CaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CaseBaseHouseService caseBaseHouseService;
    @Autowired
    private CaseHouseService caseHouseService;
    @Autowired
    private CaseHouseTradingService caseHouseTradingService;

    @RequestMapping(value = "/estateSearch", name = "楼盘案例查询", method = {RequestMethod.GET})
    public ModelAndView estateSearch() {
        String view = "/case/estateSearch";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @RequestMapping(value = "/houseSearch", name = "房屋案例查询", method = {RequestMethod.GET})
    public ModelAndView houseSearch() {
        String view = "/case/houseSearch";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @RequestMapping(value = "/findCaseBaseHouse", name = "房屋查询到的详情", method = {RequestMethod.GET})
    public ModelAndView findHouse(Integer id) {
        String view = "/case/caseBaseHouseView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        if (id != null) {
            CaseBaseHouse caseBaseHouse = caseBaseHouseService.getBaseHouseById(id);
            if (caseBaseHouse != null) {
                modelAndView.addObject("caseBaseHouse",caseBaseHouse);
                if (caseBaseHouse.getCaseHouseId() != null){
                    CaseHouse caseHouse = caseHouseService.getCaseHouseById(caseBaseHouse.getCaseHouseId());
                    if (caseHouse != null){
                        modelAndView.addObject("caseHouse",caseHouseService.getCaseHouseVo(caseHouse));
                        CaseHouseTrading query = new CaseHouseTrading();
                        query.setHouseId(caseHouse.getId());
                        List<CaseHouseTrading> caseHouseTradingList = caseHouseTradingService.caseHouseTradingList(query);
                        if (!org.springframework.util.ObjectUtils.isEmpty(caseHouseTradingList)){
                            modelAndView.addObject("caseHouseTrading",caseHouseTradingService.getCaseHouseTradingVo(caseHouseTradingList.get(0)));
                        }
                    }
                }
            }
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableCaseBaseHouseVo", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getBootstrapTableVo(BigDecimal tradingUnitPriceStart, BigDecimal tradingUnitPriceEnd, String tradingTimeStart, String tradingTimeEnd, CaseBaseHouse caseBaseHouse) {
        BootstrapTableVo vo = null;
        try {
            Date dateA = null;
            Date dateB = null;
            if (StringUtils.isNotBlank(tradingTimeStart)) {
                dateA = DateUtils.parse(tradingTimeStart);
            }
            if (StringUtils.isNotBlank(tradingTimeEnd)) {
                dateB = DateUtils.parse(tradingTimeEnd);
            }
            vo = caseBaseHouseService.getBootstrapTableVo(tradingUnitPriceStart, tradingUnitPriceEnd, dateA, dateB, caseBaseHouse);
        } catch (Exception e1) {
            logger.error("", e1);
        }
        return vo;
    }
}
