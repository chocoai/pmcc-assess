package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.event.project.ProjectAssessmentBonusEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.ProjectSpotCheckService;
import com.copower.pmcc.assess.service.project.ProjectSpotCheckService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/projectSpotCheck")
public class ProjectSpotCheckController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProjectSpotCheckService projectSpotCheckService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    @RequestMapping(value = "/index", name = "项目抽查信息页面", method = {RequestMethod.GET})
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/assessment/spotCheckIndex");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getProjectSpotCheckList", name = "获取抽查记录数据列表")
    public BootstrapTableVo getProjectSpotCheckList() {
        return projectSpotCheckService.getProjectSpotCheckList();
    }

    @RequestMapping(value = "/apply", name = "申请页面")
    public ModelAndView apply() throws BusinessException {
        String boxKey = baseParameterService.getBaseParameter(BaseParameterEnum.PROJECT_SPOT_CHECK_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxKey);
        if (boxReDto == null || boxReDto.getId() == null) {
            throw new BusinessException("流程模型参数未配置!");
        }
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/spotCheckApply", boxReDto.getId());

        List<KeyValueDto> statusEnumList = ProjectStatusEnum.getProjectStatusEnumList(ProjectStatusEnum.NORMAL.getKey(),
                ProjectStatusEnum.FINISH.getKey(), ProjectStatusEnum.CLOSE.getKey(), ProjectStatusEnum.DRAFT.getKey());
        modelAndView.addObject("statusEnumList", statusEnumList);
        //委托目的
        List<BaseDataDic> entrustPurposeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        modelAndView.addObject("entrustPurposeList", entrustPurposeList);
        //贷款类型
        List<BaseDataDic> loanTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_LOAN_TYPE);
        modelAndView.addObject("loanTypeList", loanTypeList);
        modelAndView.addObject("companyId", publicService.getCurrentCompany().getCompanyId());
        List<KeyValueDto> keyValueDtoList = baseProjectClassifyService.getProjectInitClassify();
        modelAndView.addObject("projectCategoryList", keyValueDtoList);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", name = "返回修改页面")
    public ModelAndView edit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/spotCheckApply", processInsId, boxId, taskId, agentUserAccount);
        setModelViewParam(modelAndView, processInsId);
        return modelAndView;
    }

    @RequestMapping(value = "/approval", name = "审核页面")
    public ModelAndView approval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/spotCheckApproval", processInsId, boxId, taskId, agentUserAccount);
        setModelViewParam(modelAndView, processInsId);
        return modelAndView;
    }

    @RequestMapping(value = "/detail", name = "详情页面")
    public ModelAndView detail(String processInsId, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/assessment/spotCheckDetail", processInsId, boxId, "-1", "agentUserAccount");
        setModelViewParam(modelAndView, processInsId);
        return modelAndView;
    }

    private void setModelViewParam(ModelAndView modelAndView, String processInsId) {
        ProjectSpotCheck spotCheck = projectSpotCheckService.getSpotCheckByProcessInsId(processInsId);
        ProjectSpotCheckItem spotCheckItem = projectSpotCheckService.getEnableSpotCheckItemsByMasterId(spotCheck.getId());
        if (spotCheckItem != null) {
            modelAndView.addObject("keyValueDtos", projectSpotCheckService.getSpotCheckItemVo(spotCheckItem).getKeyValueDtos());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getHistroyList", name = "获取历史数据列表")
    public BootstrapTableVo getHistroyList(Integer reviewId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        List<ProjectSpotCheckItem> scoreItems = projectSpotCheckService.getHistorySpotCheckItemsByMasterId(reviewId);
        if (CollectionUtils.isNotEmpty(scoreItems)) {
            vo.setTotal((long) scoreItems.size());
            vo.setRows(scoreItems);
        }
        return vo;
    }

    @ResponseBody
    @PostMapping(value = "/applyCommit", name = "申请提交")
    public HttpResult applyCommit(String formData, Integer projectId) {
        try {
            projectSpotCheckService.applyCommit(formData, projectId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @PostMapping(value = "/editCommit", name = "返回修改提交")
    public HttpResult editCommit(String formData, ApprovalModelDto approvalModelDto) {
        try {
            ProjectSpotCheckItem projectSpotCheckItem = JSON.parseObject(formData, ProjectSpotCheckItem.class);
            projectSpotCheckService.addSpotCheckItem(projectSpotCheckItem);
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/approvalCommit", name = "审批提交")
    @ResponseBody
    public HttpResult approvalCommit(ApprovalModelDto approvalModelDto) {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
            return HttpResult.newCorrectResult();
        } catch (BpmException e) {
            logger.error("提交审批失败", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @PostMapping(value = "/saveSpotCheckItem", name = "保存抽查明细数据")
    @ResponseBody
    public HttpResult saveSpotCheckItem(String formData) {
        try {
            ProjectSpotCheckItem projectSpotCheckItem = JSON.parseObject(formData, ProjectSpotCheckItem.class);
            projectSpotCheckService.addSpotCheckItem(projectSpotCheckItem);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("保存抽查明细数据失败", e);
            return HttpResult.newErrorResult(e);
        }
    }
}
