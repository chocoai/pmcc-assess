package com.copower.pmcc.assess.controller.cases;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.basic.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.dal.cases.entity.CaseBaseHouse;
import com.copower.pmcc.assess.dto.input.cases.CaseEstateTaggingDto;
import com.copower.pmcc.assess.service.cases.*;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
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
    private CaseEstateService caseEstateService;
    @Autowired
    private CaseEstateTaggingService caseEstateTaggingService;

    @RequestMapping(value = "/areaCaseMap", name = "案例地图", method = {RequestMethod.GET})
    public ModelAndView areaEstateCaseMap() {
        String view = "/case/areaCaseMap";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        try {
            modelAndView.addObject("mapList", JSON.toJSONString(caseEstateTaggingService.mapDtoList(null, EstateTaggingTypeEnum.ESTATE.getKey())));
        } catch (Exception e1) {
            logger.error("区域楼盘案例获取经度和纬度出错!", e1);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/estateCaseMap", name = "区域楼盘案例-", method = {RequestMethod.GET})
    public ModelAndView estateCaseMap(Integer estateId) {
        String view = "/case/estateCaseMap";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        try {
            if (estateId != null) {
                CaseEstateTaggingDto dto = caseEstateTaggingService.getCaseEstateTagging(estateId,EstateTaggingTypeEnum.ESTATE.getKey()) ;
                if (dto != null){
                    List<CaseEstateTaggingDto> list = caseEstateTaggingService.queryCaseEstateTagging(dto.getDataId(),EstateTaggingTypeEnum.ESTATE.getKey()) ;
                    if (!ObjectUtils.isEmpty(list)){
                        for (CaseEstateTaggingDto caseEstateTaggingDto:list){
                            dto.getChildren().add(caseEstateTaggingDto);
                        }
                    }
                }
                modelAndView.addObject("mapTree", JSON.toJSONString(dto));
                modelAndView.addObject("caseEstate",caseEstateService.getCaseEstateById(estateId));
            }
        } catch (Exception e1) {
            logger.error("区域楼盘案例获取经度和纬度出错!", e1);
        }
        return modelAndView;
    }

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
