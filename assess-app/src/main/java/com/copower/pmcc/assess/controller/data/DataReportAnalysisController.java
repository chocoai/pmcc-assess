package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysis;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataReportAnalysisService;
import com.copower.pmcc.assess.service.data.DataReportTemplateItemService;
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

import java.util.List;


@RequestMapping(value = "/reportAnalysis")
@Controller
public class DataReportAnalysisController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataReportAnalysisService dataReportAnalysisService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private DataReportTemplateItemService dataReportTemplateItemService;


    @RequestMapping(value = "/view", name = "转到index页面")
    public ModelAndView index() {
        List<BaseDataDic> reportAnalysisTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY);
        List<BaseDataDic> purposeDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/dataReportAnalysis");
        modelAndView.addObject("reportAnalysisTypeList", reportAnalysisTypeList);
        modelAndView.addObject("purposeDicList", purposeDicList);//所有省份
        dataReportTemplateItemService.initClean();
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", name = "显示列表", method = RequestMethod.GET)
    public BootstrapTableVo list(String name,Integer reportAnalysisType) {
        return dataReportAnalysisService.getReportAnalysisList(name,reportAnalysisType);
    }

    @ResponseBody
    @RequestMapping(value = "/get", name = "获取", method = {RequestMethod.GET})
    public HttpResult get(@RequestParam(value = "id") Integer id) {
        try {
            return HttpResult.newCorrectResult(dataReportAnalysisService.getReportAnalysis(id));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.POST}, name = "增加与修改")
    public HttpResult save(DataReportAnalysis evaluationBasis) {
        try {
            dataReportAnalysisService.saveAndUpdate(evaluationBasis);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除", method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            dataReportAnalysisService.removeReportAnalysis(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
