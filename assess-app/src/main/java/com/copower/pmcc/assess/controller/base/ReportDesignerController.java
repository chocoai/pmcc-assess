package com.copower.pmcc.assess.controller.base;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.ureport.ProjectDebtService;
import com.copower.pmcc.assess.service.ureport.WorkLogService;
import com.copower.pmcc.erp.api.dto.ReportProviderFileDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcReportProviderFileService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
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
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectDebtService projectDebtService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private WorkLogService workLogService;


    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView modelAndView = commonService.baseView("/report/configuration");
        return modelAndView;
    }

    @GetMapping(value = "/reportHome/{uuid}", name = "报表页面")
    public ModelAndView reportHome(@PathVariable("uuid") String uuid) {
        ModelAndView modelAndView = commonService.baseView("/report/vacationReport");
        ReportProviderFileDto reportProviderFile = erpFileReportProvider.getOneReportProviderFileByUuid(applicationConstant.getAppKey(), uuid);
        modelAndView.addObject("reportProviderFile", reportProviderFile);
        return modelAndView;
    }

    @GetMapping(value = "/fetchReportProvider", name = "获取报表信息集合")
    @ResponseBody
    public HttpResult fetchReportProvider() {
        try {
            List<ReportProviderFileDto> reportProviderFile = erpFileReportProvider.getReportProviderFile(applicationConstant.getAppKey());
            return HttpResult.newCorrectResult(reportProviderFile);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e);
        }
    }

    @GetMapping(value = "/deleteReport", name = "删除报表")
    @ResponseBody
    public HttpResult deleteReport(String reportName) {
        try {
            if (StringUtils.isNotBlank(reportName)) {
                erpFileReportProvider.deleteReportProviderFile(applicationConstant.getAppKey(), reportName);
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(e);
        }
    }

    @GetMapping(value = "/workLog", name = "工作日志报表页面")
    public ModelAndView summary() {
        ModelAndView modelAndView = commonService.baseView("/report/workLog");
        return modelAndView;
    }

    @ResponseBody
    @GetMapping(value = "/getWorkLogList",name = "日志列表")
    public BootstrapTableVo getWorkLogList(String queryUserAccountName, String queryProjectName, String queryTitle, String queryStartTime, String queryEndTime) {
        Date start = null;
        Date end = null;
        if (StringUtils.isNotBlank(queryStartTime)) {
            start = DateUtils.convertDate(queryEndTime);
        }
        if (StringUtils.isNotBlank(queryEndTime)) {
            end = DateUtils.convertDate(queryEndTime);
        }
        return workLogService.getBootstrapTableVo(queryUserAccountName, queryProjectName, queryTitle, start, end);
    }

    @GetMapping(value = "/projectFinance", name = "项目收款报表页面")
    public ModelAndView projectFinance() {
        ModelAndView modelAndView = commonService.baseView("/report/projectFinance");
        List<BaseDataDic> entrustmentList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        List<BaseDataDic> loanTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_LOAN_TYPE);
        modelAndView.addObject("entrustmentList", entrustmentList);
        modelAndView.addObject("loanTypeList", loanTypeList);
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
        return modelAndView;
    }

    @GetMapping(value = "/projectWorkItem", name = "项目工作事项报表页面")
    public ModelAndView projectWorkItem() {
        ModelAndView modelAndView = commonService.baseView("/report/projectWorkItem");
        return modelAndView;
    }

    @GetMapping(value = "/myProjectFinance", name = "项目收款报表页面")
    public ModelAndView MyProjectFinance() {
        ModelAndView modelAndView = commonService.baseView("/report/myProjectFinance");
        List<BaseDataDic> entrustmentList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        List<BaseDataDic> loanTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_LOAN_TYPE);
        modelAndView.addObject("entrustmentList", entrustmentList);
        modelAndView.addObject("loanTypeList", loanTypeList);
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
        return modelAndView;
    }

    @GetMapping(value = "/projectDebt", name = "项目欠款报表页面")
    public ModelAndView projectDebt() {
        //数据初始化
        projectDebtService.init();
        ModelAndView modelAndView = commonService.baseView("/report/projectDebt");
        List<BaseDataDic> entrustmentList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        List<BaseDataDic> loanTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_LOAN_TYPE);
        modelAndView.addObject("entrustmentList", entrustmentList);
        modelAndView.addObject("loanTypeList", loanTypeList);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/projectDebtRefresh", name = "刷新欠款数据", method = RequestMethod.POST)
    public HttpResult projectDebtRefresh() {
        try {
            projectDebtService.init();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
