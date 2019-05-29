package com.copower.pmcc.assess.controller.project.generate;

import com.copower.pmcc.assess.service.project.generate.GenerateService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kings on 2019-2-12.
 */
@RequestMapping("/generate")
@Controller
public class GenerateController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private GenerateService generateService;

    @ResponseBody
    @RequestMapping(value = "/submitApply", name = "提交申请", method = RequestMethod.POST)
    public HttpResult submitApply(Integer planId,Integer areaGroupId) {
        try {
            generateService.submitApply(planId,areaGroupId);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return HttpResult.newErrorResult("提交申请异常");
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/submitApproval", name = "流程审批", method = RequestMethod.POST)
    public HttpResult submitApproval(ApprovalModelDto approvalModelDto) {
        try {
            generateService.submitApproval(approvalModelDto);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return HttpResult.newErrorResult("审批异常");
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/submitEditApproval", name = "返回修改", method = RequestMethod.POST)
    public HttpResult submitEditApproval(ApprovalModelDto approvalModelDto) {
        try {
            generateService.submitEditApproval(approvalModelDto);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return HttpResult.newErrorResult("返回修改提交异常");
        }
        return HttpResult.newCorrectResult();
    }
}
