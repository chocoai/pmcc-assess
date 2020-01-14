package com.copower.pmcc.assess.controller.project.report;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.report.CustomReportJianSheBankService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/customReportJianSheBank")
public class CustomReportJianSheBankController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CustomReportJianSheBankService customReportJianSheBankService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/report/customReportJianSheBank";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        List<BaseDataDic> reportTypeList = Lists.newArrayList();
        //预评报告
        BaseDataDic preauditReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT);
        //结果报告
        BaseDataDic resultReport = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_TYPE_RESULT);
        reportTypeList.add(preauditReport);
        reportTypeList.add(resultReport);
        modelAndView.addObject("reportTypeList", reportTypeList);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getCustomReportJianSheBankList", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getCustomReportJianSheBankList(String numberValue, String unitName, Integer reportType,
                                                           String queryPreviewsStartDate, String queryPreviewsEndDate, String queryResultStartDate, String queryResultEndDate) {
        BootstrapTableVo vo = null;
        try {
            vo = customReportJianSheBankService.getCustomReportJianSheBankList(numberValue, unitName, reportType,queryPreviewsStartDate,queryPreviewsEndDate,queryResultStartDate,queryResultEndDate);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @RequestMapping(value = "/export", name = "导出")
    public void export(HttpServletResponse response, String numberValue, String unitName, Integer reportType,
                       String queryPreviewsStartDate, String queryPreviewsEndDate, String queryResultStartDate, String queryResultEndDate) throws Exception {
        try {
            customReportJianSheBankService.export(response, numberValue, unitName, reportType,queryPreviewsStartDate,queryPreviewsEndDate,queryResultStartDate,queryResultEndDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
