package com.copower.pmcc.assess.controller.report;

import com.copower.pmcc.assess.dal.entity.BaseReportBatchTemplate;
import com.copower.pmcc.assess.service.base.BaseReportBatchService;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:批量报告
 * 处理过程
 * 1、上传报告模板，并存入数据库，存储内容至少包括银行名称、模板、批次
 * 2、上传尽调报告和评估结果报告，两个数据之间通过身份证号码进行关联
 * 3、生成报告，通过选择的模板和批次生成相应的报告
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/28
 * @time: 9:24
 */
@Controller
@RequestMapping(value = "/BatchReport", name = "批量报告")
public class BatchReportController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseReportBatchService baseReportBatchService;

    @RequestMapping(value = "/pageIndex", name = "初始页")
    public ModelAndView pageIndex() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/report/pageIndex");
        return modelAndView;
    }

    /**
     * 导入考勤数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveProjectInfo", method = RequestMethod.POST)
    public HttpResult importAttendanceData(BaseReportBatchTemplate baseReportBatchTemplate) {
        try {
            baseReportBatchService.importAttendanceData(baseReportBatchTemplate);
            return HttpResult.newCorrectResult();
        } catch (BpmException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
    //    //报告模板处理
    //
    //    public HttpResult uploadReportTemplate()
    //    {
    //
    //    }

}
