package com.copower.pmcc.assess.controller.report;


import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.ReportMergeRule;
import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.ReportMergeRuleService;
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

/**
 * Created by kings on 2018-3-5.
 */
@Controller
@RequestMapping("/reportMergeRule")
public class ReportMergeRuleController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReportMergeRuleService reportMergeRuleService;
    @Autowired
    private ControllerComponent controllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ServiceComponent serviceComponent;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView homeMain() {
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/base/reportMergeRule");
        List<BaseDataDic> reportMergeRuleTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_TYPE);
        modelAndView.addObject("reportTypeList",reportMergeRuleTypeList);
        return modelAndView;
    }

    /**
     * 获取模板列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMergeRuleList", method = RequestMethod.GET)
    public BootstrapTableVo getMergeRuleList(Integer type) {
        return reportMergeRuleService.getMergeRuleList(type);
    }

    /**
     * 保存模板
     *
     * @param reportMergeRule
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveMergeRule", method = RequestMethod.POST)
    public HttpResult saveMergeRule(ReportMergeRule reportMergeRule) {
        try {
            reportMergeRuleService.saveMergeRule(reportMergeRule);
        } catch (Exception e) {
            LOGGER.error("保存模板异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 删除合同模板
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteMergeRule", method = RequestMethod.POST)
    public HttpResult deleteMergeRule(Integer id) {
        try {
            reportMergeRuleService.deleteMergeRule(id);
        } catch (Exception e) {
            LOGGER.error("删除模板异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }



}
