package com.copower.pmcc.assess.controller.csr;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.entity.CsrProjectInfo;
import com.copower.pmcc.assess.dto.input.project.ProjectInfoDto;
import com.copower.pmcc.assess.dto.output.project.csr.CsrProjectInfoVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.csr.CsrProjectInfoService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kings on 2018-5-31.
 */
@Controller
@RequestMapping("/csrProjectInfo")
public class CsrProjectInfoController {
    @Autowired
    private CsrProjectInfoService csrProjectInfoService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/projectIndex", name = "项目立项", method = RequestMethod.GET)
    public ModelAndView view() {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/csr/projectIndex", "0", 0, "0", "");
        modelAndView.addObject("boxCnName", "项目立项");
        modelAndView.addObject("thisTitle", "项目立项");
        modelAndView.addObject("boxprocessIcon", "fa-bookmark-o");
        modelAndView.addObject("list_entrustment_purpose", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ENTRUSTMENT_PURPOSE));//委托目的

        CsrProjectInfoVo csrProjectInfo = new CsrProjectInfoVo();
        csrProjectInfo.setId(0);
        modelAndView.addObject("csrProjectInfo", csrProjectInfo);
        return modelAndView;
    }

    @RequestMapping(value = "/projectInfoEdit", name = "项目返回修改 页面")
    public ModelAndView projectInfoEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/csr/projectIndex", processInsId, boxId, taskId, agentUserAccount);
        CsrProjectInfoVo csrProjectInfo = csrProjectInfoService.getCsrProjectInfoVo(processInsId);
        modelAndView.addObject("csrProjectInfo", csrProjectInfo);
        modelAndView.addObject("list_entrustment_purpose", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ENTRUSTMENT_PURPOSE));//委托目的
        return modelAndView;
    }

    @RequestMapping(value = "/projectApproval", name = "项目审批页面")
    public ModelAndView projectApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/csr/projectApproval", processInsId, boxId, taskId, agentUserAccount);
        CsrProjectInfoVo csrProjectInfo = csrProjectInfoService.getCsrProjectInfoVo(processInsId);
        modelAndView.addObject("csrProjectInfo", csrProjectInfo);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/projectApplySubmit", name = "保存项目", method = RequestMethod.POST)
    public HttpResult projectApplySubmit(String formData) {
        try {
            CsrProjectInfo csrProjectInfo = JSON.parseObject(formData, CsrProjectInfo.class);
            csrProjectInfoService.csrProjectApply(csrProjectInfo);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 审批提交
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/projectApprovalSubmit", method = RequestMethod.POST)
    public HttpResult projectApprovalSubmit(ApprovalModelDto approvalModelDto) {
        try {
            csrProjectInfoService.crsProjectApproval(approvalModelDto);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 返回修改
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/projectEditSubmit", method = RequestMethod.POST)
    public HttpResult projectEditSubmit(String formData, ApprovalModelDto approvalModelDto) {
        try {
            CsrProjectInfo csrProjectInfo = JSON.parseObject(formData, CsrProjectInfo.class);
            csrProjectInfoService.crsProjectEdit(csrProjectInfo, approvalModelDto);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/projectDetails", name = "项目详情")
    public ModelAndView projectDetails(Integer projectId) throws BusinessException {
        ModelAndView modelAndView = new ModelAndView("/project/projectDetails");

        return modelAndView;
    }
}
