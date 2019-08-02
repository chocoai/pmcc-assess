package com.copower.pmcc.assess.controller.base;

import com.copower.pmcc.erp.api.dto.ReportProviderFileDto;
import com.copower.pmcc.erp.api.provider.ErpRpcReportProviderFileService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/assessReport")
public class ReportDesignerController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ErpRpcReportProviderFileService erpFileReportProvider;


    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView modelAndView = commonService.baseView("/reportManage/configuration");
        return modelAndView;
    }

    @GetMapping(value = "/reportHome/{uuid}",name = "报表页面")
    public ModelAndView reportHome(@PathVariable("uuid") String uuid) {
        ModelAndView modelAndView = commonService.baseView("/reportManage/vacationReport");
        ReportProviderFileDto reportProviderFile = erpFileReportProvider.getOneReportProviderFileByUuid(applicationConstant.getAppKey(), uuid);
        modelAndView.addObject("reportProviderFile",reportProviderFile);
        return modelAndView;
    }

    @GetMapping(value = "/fetchReportProvider",name = "获取报表信息集合")
    @ResponseBody
    public HttpResult fetchReportProvider() {
        try {
            List<ReportProviderFileDto> reportProviderFile = erpFileReportProvider.getReportProviderFile(applicationConstant.getAppKey());
            return HttpResult.newCorrectResult(reportProviderFile);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e);
        }
    }

    @GetMapping(value = "/deleteReport",name = "删除报表")
    @ResponseBody
    public HttpResult deleteReport(String reportName) {
        try {
            if(StringUtils.isNotBlank(reportName)){
                erpFileReportProvider.deleteReportProviderFile(applicationConstant.getAppKey(),reportName);
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(e);
        }
    }

    @GetMapping(value = "/workLog",name = "工作日志报表页面")
    public ModelAndView summary() {
        ModelAndView modelAndView = commonService.baseView("/reportManage/workLog");
        return modelAndView;
    }

    @GetMapping(value = "/projectFinance",name = "项目收款报表页面")
    public ModelAndView projectFinance() {
        ModelAndView modelAndView = commonService.baseView("/reportManage/projectFinance");
        return modelAndView;
    }
}
