package com.copower.pmcc.assess.controller.project.report;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.report.CustomReportHuaXiaBankService;
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
@RequestMapping(value = "/customReportHuaXiaBank")
public class CustomReportHuaXiaBankController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CustomReportHuaXiaBankService customReportHuaXiaBankService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/report/customReportHuaXiaBank";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        List<BaseDataDic> reportTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_TYPE);
        modelAndView.addObject("reportTypeList", reportTypeList);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getCustomReportHuaXiaBankList", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getCustomReportHuaXiaBankList(String numberValue, String unitName) {
        BootstrapTableVo vo = null;
        try {
            vo = customReportHuaXiaBankService.getCustomReportHuaXiaBankList(numberValue, unitName);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @RequestMapping(value = "/export", name = "导出")
    public void export(HttpServletResponse response, String numberValue, String unitName) throws Exception {
        try {
            customReportHuaXiaBankService.export(response, numberValue, unitName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
