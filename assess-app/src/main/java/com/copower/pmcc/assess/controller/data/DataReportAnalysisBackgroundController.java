package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysis;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataReportAnalysisBackgroundService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RequestMapping(value = "/reportAnalysisBackground")
@Controller
public class DataReportAnalysisBackgroundController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataReportAnalysisService dataReportAnalysisService;
    @Autowired
    private DataReportAnalysisBackgroundService dataReportAnalysisBackgroundService;
    @Autowired
    private DataReportTemplateItemService dataReportTemplateItemService;


    @RequestMapping(value = "/view", name = "转到index页面")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/dataReportAnalysisBackground");
        List<BaseDataDic> setUseList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.WORK_PROGRAMME_SET_USE);
        modelAndView.addObject("setUseList", setUseList);//所有省份
        List<BaseDataDic> purposeDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        modelAndView.addObject("purposeDicList", purposeDicList);
        List<BaseDataDic> types = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND);
        modelAndView.addObject("types", types);
        dataReportTemplateItemService.initClean();
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", name = "显示列表", method = RequestMethod.GET)
    public BootstrapTableVo list(String name,Integer type) {
        BaseDataDic cacheDataDicByFieldName = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND);
        return dataReportAnalysisService.getReportAnalysisList(name,type,cacheDataDicByFieldName.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.POST}, name = "增加与修改")
    public HttpResult save(DataReportAnalysis evaluationBasis) {
        try {
            dataReportAnalysisBackgroundService.saveAndUpdate(evaluationBasis);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

}
