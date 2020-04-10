package com.copower.pmcc.assess.controller.baisc;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCaseSummary;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseCaseSummaryVo;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.basic.BasicEstateTaggingService;
import com.copower.pmcc.assess.service.basic.BasicHouseCaseSummaryService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.DateUtils;
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

/**
 * Created by kings on 2018-7-6.
 */
@Controller
@RequestMapping(value = "/basic", name = "案例 基础")
public class BasicController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BasicHouseCaseSummaryService basicHouseCaseSummaryService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;

    @RequestMapping(value = "/areaCaseMap", name = "案例地图", method = {RequestMethod.GET})
    public ModelAndView areaEstateCaseMap() {
        String view = "/case/areaCaseMap";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        try {
            modelAndView.addObject("mapList", JSON.toJSONString(basicEstateTaggingService.mapDtoList(null, BasicFormClassifyEnum.ESTATE.getKey())));
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
                modelAndView.addObject("caseEstate", basicEstateService.getBasicEstateById(estateId));
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
    public BootstrapTableVo getBootstrapTableVo(BigDecimal areaStart, BigDecimal areaEnd, String tradingTimeStart, String tradingTimeEnd, BasicHouseCaseSummary basicHouseCaseSummary) {
        BootstrapTableVo vo = null;
        try {
            Date start = null;
            Date end = null;
            if (StringUtils.isNotBlank(tradingTimeStart)) {
                start = DateUtils.parse(tradingTimeStart);
            }
            if (StringUtils.isNotBlank(tradingTimeEnd)) {
                end = DateUtils.parse(tradingTimeEnd);
            }
            vo = basicHouseCaseSummaryService.getBootstrapTableVo(areaStart, areaEnd, start, end, basicHouseCaseSummary);
        } catch (Exception e1) {
            logger.error("", e1);
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataById", method = {RequestMethod.GET}, name = "通过id获取信息")
    public HttpResult getDataById(Integer id) {
        try {
            if (id != null) {
                BasicHouseCaseSummaryVo vo = basicHouseCaseSummaryService.getBasicHouseCaseSummaryVo(basicHouseCaseSummaryService.getBasicHouseCaseSummaryById(id));
                return HttpResult.newCorrectResult(vo);
            }
        } catch (Exception e) {
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/checkCaseDetail", name = "查看详情", method = RequestMethod.GET)
    public ModelAndView checkDetail(Integer estateId) {
        String view = "/case/caseDetail";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        try {
            BasicApplyBatch basicApplyBatch = new BasicApplyBatch();
            basicApplyBatch.setEstateId(estateId);
            basicApplyBatch.setBisCase(true);
            BasicApplyBatch caseData = basicApplyBatchService.getSingleData(basicApplyBatch);
            if (caseData != null) {
                modelAndView.addObject("applyBatch", caseData);
            } else {
                //创建树结构
                BasicApplyBatch applyBatch = basicApplyBatchService.generateTree(estateId);
                modelAndView.addObject("applyBatch", applyBatch);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return modelAndView;
    }
}
