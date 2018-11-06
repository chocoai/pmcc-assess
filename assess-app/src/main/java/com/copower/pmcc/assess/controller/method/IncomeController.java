package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.method.MdIncomeDateSectionService;
import com.copower.pmcc.assess.service.method.MdIncomeService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

/**
 * 评估原则
 * Created by 13426 on 2018/4/28.
 */
@RequestMapping(value = "/income", name = "收益法")
@Controller
public class IncomeController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MdIncomeService mdIncomeService;
    @Autowired
    private MdIncomeDateSectionService mdIncomeDateSectionService;

    @ResponseBody
    @GetMapping(value = "/getDateSectionList", name = "获取日期分段列表")
    public BootstrapTableVo getDateSectionList(Integer incomeId, Integer operationMode) {
        return mdIncomeDateSectionService.getDateSectionList(incomeId, operationMode);
    }

    @ResponseBody
    @PostMapping(value = "/saveDateSection", name = "保存分段信息")
    public HttpResult saveDateSection(String formData) {
        try {
            MdIncomeDateSection mdIncomeDateSection = JSON.parseObject(formData, MdIncomeDateSection.class);
            mdIncomeDateSectionService.saveDateSection(mdIncomeDateSection);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @PostMapping(value = "/updateDateSection", name = "更新分段信息")
    public HttpResult updateDateSection(String formData) {
        try {
            MdIncomeDateSection mdIncomeDateSection = JSON.parseObject(formData, MdIncomeDateSection.class);
            mdIncomeDateSectionService.updateDateSection(mdIncomeDateSection);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @PostMapping(value = "/deleteDateSection", name = "删除分段信息")
    public HttpResult deleteDateSection(@RequestParam(value = "id") Integer id) {
        try {
            mdIncomeDateSectionService.deleteDateSection(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getHistoryList", name = "显示列表", method = RequestMethod.GET)
    public BootstrapTableVo getHistoryList(Integer incomeId, Integer type, Boolean bisForecast) {
        return mdIncomeService.getHistoryList(incomeId, type, bisForecast);
    }

    @ResponseBody
    @RequestMapping(value = "/saveHistory", method = {RequestMethod.POST}, name = "增加与修改")
    public HttpResult saveHistory(MdIncomeHistory mdIncomeHistory) {
        try {
            mdIncomeService.saveHistory(mdIncomeHistory);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/historyToForecast", method = {RequestMethod.POST}, name = "历史数据添加到预测数据")
    public HttpResult historyToForecast(String ids,Integer forecastAnalyseId) {
        try {
            if (StringUtils.isNotBlank(ids))
                mdIncomeService.historyToForecast(FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids)),forecastAnalyseId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/deleteHistory", name = "删除", method = RequestMethod.POST)
    public HttpResult deleteHistory(@RequestParam(value = "id") Integer id) {
        try {
            mdIncomeService.deleteHistory(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/importHistory", name = "导入历史数据", method = RequestMethod.POST)
    public HttpResult importHistory(MdIncomeHistory history, HttpServletRequest request) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty())
                return HttpResult.newErrorResult("上传的文件不能为空");
            String s = mdIncomeService.importHistory(history, multipartFile);
            return HttpResult.newCorrectResult(s);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error(String.format("导入历史数据-%s", e.getMessage()), e);
            return HttpResult.newErrorResult("导入历史数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getForecastAnalyseList", name = "显示预测分析数据列表", method = RequestMethod.GET)
    public BootstrapTableVo getForecastAnalyseList(Integer incomeId, Integer type) {
        return mdIncomeService.getForecastAnalyseList(incomeId, type);
    }

    @ResponseBody
    @RequestMapping(value = "/getForecastList", name = "显示列表", method = RequestMethod.GET)
    public BootstrapTableVo getForecastList(Integer incomeId, Integer type) {
        return mdIncomeService.getForecastList(incomeId, type);
    }

    @ResponseBody
    @RequestMapping(value = "/getForecastYearList", name = "显示年度预测列表", method = RequestMethod.GET)
    public BootstrapTableVo getForecastYearList(Integer forecastId) {
        return mdIncomeService.getForecastYearList(forecastId);
    }

    @ResponseBody
    @RequestMapping(value = "/saveForecast", method = {RequestMethod.POST}, name = "修改")
    public HttpResult saveForecast(MdIncomeForecast mdIncomeForecast) {
        try {
            mdIncomeService.updateForecast(mdIncomeForecast);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/deleteForecast", name = "删除", method = RequestMethod.POST)
    public HttpResult deleteForecast(@RequestParam(value = "id") Integer id) {
        try {
            mdIncomeService.deleteForecast(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getForecastMonthList", name = "显示列表", method = RequestMethod.GET)
    public BootstrapTableVo getForecastMonthList(Integer yearId, Integer type) {
        return mdIncomeService.getForecastMonthList(yearId, type);
    }

    @ResponseBody
    @RequestMapping(value = "/saveForecastMonth", method = {RequestMethod.POST}, name = "增加与修改")
    public HttpResult saveForecastMonth(MdIncomeForecastMonth mdIncomeForecastMonth) {
        try {
            mdIncomeService.saveForecastMonth(mdIncomeForecastMonth);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/deleteForecastMonth", name = "删除", method = RequestMethod.POST)
    public HttpResult deleteForecastMonth(@RequestParam(value = "id") Integer id) {
        try {
            mdIncomeService.deleteForecastMonth(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }


    @ResponseBody
    @RequestMapping(value = "/getLeaseList", name = "显示列表", method = RequestMethod.GET)
    public BootstrapTableVo getLeaseList(Integer incomeId) {
        return mdIncomeService.getLeaseList(incomeId);
    }

    @ResponseBody
    @RequestMapping(value = "/updateLease", method = {RequestMethod.POST}, name = "修改")
    public HttpResult updateLease(MdIncomeLease mdIncomeLease) {
        try {
            mdIncomeService.updateLease(mdIncomeLease);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }


    @ResponseBody
    @RequestMapping(value = "/getLeaseCostList", name = "显示列表", method = RequestMethod.GET)
    public BootstrapTableVo getLeaseCostList(Integer incomeId) {
        return mdIncomeService.getLeaseCostList(incomeId);
    }

    @ResponseBody
    @RequestMapping(value = "/updateLeaseCost", method = {RequestMethod.POST}, name = "修改")
    public HttpResult updateLeaseCost(MdIncomeLeaseCost mdIncomeLeaseCost) {
        try {
            mdIncomeService.updateLeaseCost(mdIncomeLeaseCost);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("保存异常");
        }
        return HttpResult.newCorrectResult();
    }
}
