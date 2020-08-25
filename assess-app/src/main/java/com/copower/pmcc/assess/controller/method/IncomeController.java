package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeForecastDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeIncomeApplyDto;
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
import java.util.List;

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
    @Autowired
    private MdIncomeForecastDao mdIncomeForecastDao;


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
    public BootstrapTableVo getHistoryList(MdIncomeHistory mdIncomeHistory) {
        if (StringUtils.isEmpty(mdIncomeHistory.getSecondLevelNumber())) {
            mdIncomeHistory.setSecondLevelNumber(null);
        }
        return mdIncomeService.getHistoryList(mdIncomeHistory);
    }

    @ResponseBody
    @RequestMapping(value = "/saveHistory", method = {RequestMethod.POST}, name = "增加与修改")
    public HttpResult saveHistory(String formData) {
        try {
            MdIncomeHistory mdIncomeHistory = JSON.parseObject(formData, MdIncomeHistory.class);
            mdIncomeService.saveHistory(mdIncomeHistory);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/historyToForecast", method = {RequestMethod.POST}, name = "历史数据添加到预测数据")
    public HttpResult historyToForecast(String ids, Integer year, String formType) {
        try {
            if (StringUtils.isNotBlank(ids))
                mdIncomeService.historyToForecast(FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids)), year, formType);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/forecastToHistory", method = {RequestMethod.POST}, name = "预测数据还原为历史数据")
    public HttpResult forecastToHistory(String ids, Integer incomeId, Integer type, Integer formType) {
        try {
            if (StringUtils.isNotBlank(ids)) {
                List<Integer> idList = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
                mdIncomeService.forecastToHistory(idList, incomeId, type, formType);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/startAnalyse", method = {RequestMethod.POST}, name = "开始分析")
    public HttpResult startAnalyse(Integer incomeId, Integer type, Integer formType) {
        try {
            mdIncomeService.startAnalyse(incomeId, type, formType);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/deleteHistory", name = "删除", method = RequestMethod.POST)
    public HttpResult deleteHistory(@RequestParam(value = "ids") String ids) {
        try {
            mdIncomeService.deleteHistory(ids);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("删除历史数据异常");
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
    @RequestMapping(value = "/getForecastAnalyseList", name = "显示历史数据分析列表", method = RequestMethod.GET)
    public BootstrapTableVo getForecastAnalyseList(Integer incomeId, Integer type, Integer formType, Boolean bisParticipateIn) {
        return mdIncomeService.getForecastAnalyseList(incomeId, type, formType, bisParticipateIn);
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
    @RequestMapping(value = "/createForecastIncomeYear", method = {RequestMethod.POST}, name = "生成年度预测")
    public HttpResult createForecastIncomeYear(Integer incomeForecastId) {
        try {
            mdIncomeService.createForecastIncomeYear(incomeForecastId);
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
    @RequestMapping(value = "/updateLease", method = {RequestMethod.POST}, name = "修改毛收入")
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
    @RequestMapping(value = "/updateLeaseCost", method = {RequestMethod.POST}, name = "修改成本")
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

    @ResponseBody
    @RequestMapping(value = "/saveIncome", method = {RequestMethod.POST}, name = "保存收益法信息")
    public HttpResult saveIncome(String formData) {
        try {
            SchemeIncomeApplyDto schemeIncomeApplyDto = JSON.parseObject(formData, SchemeIncomeApplyDto.class);
            mdIncomeService.saveResult(schemeIncomeApplyDto.getIncomeInfo());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("保存收益法信息异常");
        }
        return HttpResult.newCorrectResult();
    }


    @ResponseBody
    @RequestMapping(value = "/getAnalyseCountByYear", name = "显示预测列表通过年份分类", method = RequestMethod.GET)
    public HttpResult getAnalyseCountByYear(Integer incomeId, Integer type, Integer formType) {
        try {
            List<Integer> years = mdIncomeService.getAnalyseCountByYear(incomeId, type, formType);
            return HttpResult.newCorrectResult(years);
        } catch (Exception e) {
            return HttpResult.newErrorResult("获取失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getForecastAnalyseItemList", name = "显示预测分析明细列表", method = RequestMethod.GET)
    public BootstrapTableVo getForecastAnalyseItemList(Integer forecastAnalyseId) {
        return mdIncomeService.getForecastAnalyseItemList(forecastAnalyseId);
    }


    @ResponseBody
    @RequestMapping(value = "/loadIncomeForecastItemList", name = "显示有效毛收入数据列表", method = RequestMethod.GET)
    public BootstrapTableVo loadIncomeForecastItemList(Integer incomeForecastId) {
        return mdIncomeService.loadIncomeForecastItemList(incomeForecastId);
    }


    @ResponseBody
    @RequestMapping(value = "/saveIncomeForecastItem", method = {RequestMethod.POST}, name = "修改")
    public HttpResult saveIncomeForecastItem(MdIncomeForecastItem mdIncomeForecastItem) {
        try {
            mdIncomeService.saveIncomeForecastItem(mdIncomeForecastItem);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/deleteForecastIncomeItem", name = "删除有效毛收入明细", method = RequestMethod.POST)
    public HttpResult deleteForecastIncomeItem(@RequestParam(value = "id") Integer id) {
        try {
            mdIncomeService.deleteForecastIncomeItem(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }


    @ResponseBody
    @RequestMapping(value = "/forecastIncomeItemQuoteData", name = "引用数据", method = RequestMethod.POST)
    public HttpResult forecastIncomeItemQuoteData(Integer incomeId, Integer formType, Integer incomeForecastId) {
        try {
            mdIncomeService.forecastIncomeItemQuoteData(incomeId, formType, incomeForecastId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getMdIncomePriceInvestigationList", name = "商品调查价格列表", method = RequestMethod.GET)
    public BootstrapTableVo getMdIncomePriceInvestigationList(Integer incomeId) {
        return mdIncomeService.getMdIncomePriceInvestigationList(incomeId);
    }

    @ResponseBody
    @RequestMapping(value = "/removeMdIncomePriceInvestigation", name = "删除一条商品调查价格", method = RequestMethod.POST)
    public HttpResult removeMdIncomePriceInvestigation(@RequestParam(value = "id") Integer id) {
        try {
            mdIncomeService.removeMdIncomePriceInvestigation(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/saveMdIncomePriceInvestigation", method = {RequestMethod.POST}, name = "保存商品调查价格")
    public HttpResult saveMdIncomePriceInvestigation(MdIncomePriceInvestigation mdIncomePriceInvestigation) {
        try {
            mdIncomeService.saveMdIncomePriceInvestigation(mdIncomePriceInvestigation);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getMdIncomePriceInvestigationById", name = "获取一条商品调查价格", method = RequestMethod.GET)
    public HttpResult getMdIncomePriceInvestigationById(Integer id) {
        try {
            MdIncomePriceInvestigation priceInvestigationById = mdIncomeService.getMdIncomePriceInvestigationById(id);
            return HttpResult.newCorrectResult(priceInvestigationById);
        } catch (Exception e) {
            return HttpResult.newErrorResult("获取失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getSameNameHistoryList", name = "同类物品历史数据列表", method = RequestMethod.GET)
    public BootstrapTableVo getSameNameHistoryList(Integer historyId, Integer formType, Integer incomeId) {
        return mdIncomeService.getSameNameHistoryList(historyId, formType, incomeId);
    }

    @ResponseBody
    @RequestMapping(value = "/affirmQuoteMoney", method = {RequestMethod.POST}, name = "计算引用的历史金额")
    public HttpResult affirmQuoteMoney(@RequestParam(value = "ids") String ids, Integer historyId) {
        try {
            if (StringUtils.isNotBlank(ids))
                mdIncomeService.affirmQuoteMoney(FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids)), historyId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/saveOperatingCostItem", method = {RequestMethod.POST}, name = "保存经营成本明细")
    public HttpResult saveOperatingCostItem(Integer id, String operatingCostItem) {
        try {
            MdIncomeForecast forecast = mdIncomeForecastDao.getForecastById(id);
            forecast.setOperatingCostItem(operatingCostItem);
            mdIncomeForecastDao.updateForecast(forecast);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getForecastById", name = "获取一条数据", method = {RequestMethod.POST})
    public HttpResult getForecastById(Integer id) {
        try {
            MdIncomeForecast forecast = mdIncomeForecastDao.getForecastById(id);
            return HttpResult.newCorrectResult(forecast);
        } catch (Exception e) {
            return HttpResult.newErrorResult("获取失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/pasteMdIncome", method = {RequestMethod.POST}, name = "复制收益法")
    public HttpResult pasteMdIncome(Integer sourceId, Integer targetId) {
        try {
            mdIncomeService.pasteMdIncome(sourceId, targetId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/pasteLeaseIncome", method = {RequestMethod.POST}, name = "复制收入")
    public HttpResult pasteLeaseIncome(Integer sourceId, Integer targetId) {
        try {
            mdIncomeService.pasteLeaseIncome(sourceId, targetId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/pasteLeaseCost", method = {RequestMethod.POST}, name = "复制成本")
    public HttpResult pasteLeaseCost(Integer sourceId, Integer targetId) {
        try {
            mdIncomeService.pasteLeaseCost(sourceId, targetId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
