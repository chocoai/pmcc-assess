package com.copower.pmcc.assess.controller.csr;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.entity.BaseProjectCategory;
import com.copower.pmcc.assess.dal.entity.CsrProjectInfo;
import com.copower.pmcc.assess.dal.entity.CsrProjectInfoGroup;
import com.copower.pmcc.assess.dto.input.project.csr.CsrProjectInfoGroupSubmitDto;
import com.copower.pmcc.assess.dto.output.project.csr.CsrProjectInfoGroupVo;
import com.copower.pmcc.assess.dto.output.project.csr.CsrProjectInfoVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectCategoryService;
import com.copower.pmcc.assess.service.csr.CsrBorrowerService;
import com.copower.pmcc.assess.service.csr.CsrProjectInfoGroupService;
import com.copower.pmcc.assess.service.csr.CsrProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by kings on 2018-5-31.
 */
@Controller
@RequestMapping("/csrProjectInfo")
public class CsrProjectInfoController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private CsrProjectInfoService csrProjectInfoService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CsrProjectInfoGroupService projectInfoGroupService;
    @Autowired
    private CsrBorrowerService service;

    @RequestMapping(value = "/projectIndex", name = "项目立项", method = RequestMethod.GET)
    public ModelAndView view(Integer projectClassId, Integer projectTypeId, Integer projectCategoryId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/csr/projectIndex", "0", 0, "0", "");
        modelAndView.addObject("boxCnName", "项目立项");
        modelAndView.addObject("thisTitle", "项目立项");
        modelAndView.addObject("boxprocessIcon", "fa-bookmark-o");
        modelAndView.addObject("list_entrustment_purpose", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ENTRUSTMENT_PURPOSE));//委托目的

        CsrProjectInfo csrProjectInfo=new CsrProjectInfo();
        csrProjectInfo.setId(0);
        csrProjectInfo.setProjectClassId(projectClassId);
        csrProjectInfo.setProjectTypeId(projectTypeId);
        csrProjectInfo.setProjectCategoryId(projectCategoryId);
        CsrProjectInfoVo csrProjectInfoVo = csrProjectInfoService.getCsrProjectInfoVo(csrProjectInfo);
        modelAndView.addObject("csrProjectInfo", csrProjectInfoVo);
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
        modelAndView.addObject("listClass_assess", projectInfoService.listClass_assess());//大类
        modelAndView.addObject("list_entrustment_purpose", projectInfoService.list_entrustment_purpose());//委托目的
        modelAndView.addObject("project_initiate_urgency", projectInfoService.project_initiate_urgency());//紧急程度
        modelAndView.addObject("value_type", projectInfoService.value_type());//价值类型
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
    public HttpResult projectApprovalSubmit(ApprovalModelDto approvalModelDto, Integer csrProjectInfoID) {
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
    public ModelAndView projectDetails(Integer csrProjectInfoID) throws BusinessException {
        ModelAndView modelAndView = new ModelAndView("/project/projectCsrDetails");
        CsrProjectInfoVo csrProjectInfo = csrProjectInfoService.getById(csrProjectInfoID);
        modelAndView.addObject("csrProjectInfo",csrProjectInfo);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/borrowerLists", name = "显示列表 客户信息", method = {RequestMethod.GET})
    public BootstrapTableVo list(String secondLevelBranch, String firstLevelBranch, Integer csrProjectInfoID,Integer csrProjectInfoGroupID) {
        BootstrapTableVo vo = service.borrowerLists(secondLevelBranch, firstLevelBranch, csrProjectInfoID,csrProjectInfoGroupID);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/checkCsrBorrower", name = "客户信息 分派检测", method = {RequestMethod.POST})
    public HttpResult checkCsrBorrower(@RequestParam(value = "csrProjectInfoID") String csrProjectInfoID) {
        if (!StringUtils.isEmpty(csrProjectInfoID)) {
            int size = service.checkCsrBorrower(Integer.parseInt(csrProjectInfoID));
            boolean flag = projectInfoGroupService.checkGroup(Integer.parseInt(csrProjectInfoID));
            if (flag){
                if (size > 0) {
                    return HttpResult.newErrorResult("没有分派完成!");
                } else {
                    return HttpResult.newCorrectResult("分派完成!");
                }
            }else {
                return HttpResult.newErrorResult("项目组中有项目没有分派客户!");
            }
        } else {
            return HttpResult.newErrorResult("数据异常!!");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/groupVoList", name = "显示列表 项目组信息", method = {RequestMethod.GET})
    public BootstrapTableVo groupVoList(Integer projectID, String projectName) {
        BootstrapTableVo vo = projectInfoGroupService.groupVoList(projectID, projectName);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateGroupProject", method = {RequestMethod.POST}, name = "项目组 增加与修改")
    public HttpResult saveAndUpdateGroupProject(CsrProjectInfoGroup csrProjectInfoGroup) {
        try {
            if (!ObjectUtils.isEmpty(csrProjectInfoGroup)) {
                projectInfoGroupService.saveAndUpdate(csrProjectInfoGroup);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/groupProject/get", name = "项目组 删除", method = RequestMethod.GET)
    public Object getGroupProjectByID(Integer id) {
        try {
            CsrProjectInfoGroupVo csrProjectInfoGroup = projectInfoGroupService.getByID(id);
            if (!ObjectUtils.isEmpty(csrProjectInfoGroup)) {
                return csrProjectInfoGroup;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/groupProject/delete", name = "项目组 删除", method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            projectInfoGroupService.delete(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/submitGroupProject", method = {RequestMethod.POST}, name = "项目组 分派")
    public HttpResult submitGroupProject(CsrProjectInfoGroupSubmitDto submitDto) {
        try {
            if (!ObjectUtils.isEmpty(submitDto)) {
                projectInfoGroupService.submitGroup(submitDto);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/cancelCsrBorrower", method = {RequestMethod.POST}, name = "项目组 分派取消 借款人")
    public HttpResult cancelCsrBorrower(Integer csrProjectId,String csrBorrowerID,Integer csrProjectInfoGroupID) {
        try {
            service.cancel(csrProjectId,csrBorrowerID,csrProjectInfoGroupID);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/generateReport", method = {RequestMethod.POST}, name = "生成报告")
    public HttpResult generateReport(Integer csrProjectId,String borrowerIds) {
        try {
            csrProjectInfoService.generateReport(csrProjectId,borrowerIds);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
