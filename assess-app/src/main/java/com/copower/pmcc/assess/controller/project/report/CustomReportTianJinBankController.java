package com.copower.pmcc.assess.controller.project.report;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.report.CustomReportTianJinBankService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
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
@RequestMapping(value = "/customReportTianJinBank")
public class CustomReportTianJinBankController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CustomReportTianJinBankService customReportTianJinBankService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/report/customReportTianJinBank";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        List<BaseDataDic> reportTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_TYPE);
        modelAndView.addObject("reportTypeList", reportTypeList);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getCustomReportTianJinBankList", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getCustomReportTianJinBankList(String numberValue, String unitName, Integer reportType) {
        BootstrapTableVo vo = null;
        try {
            vo = customReportTianJinBankService.getCustomReportTianJinBankList(numberValue, unitName, reportType);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @RequestMapping(value = "/export", name = "导出")
    public void export(HttpServletResponse response, String numberValue, String unitName) throws Exception {
        try {
            customReportTianJinBankService.export(response, numberValue, unitName, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
