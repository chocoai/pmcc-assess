package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectNumberRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectNumberRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectTakeNumber;
import com.copower.pmcc.assess.dto.output.project.ProjectTakeNumberVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.ProjectTakeNumberService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.ProjectQrcodeRecordService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.SysSymbolListDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:
 *
 * @author: huhao
 * @data: 2018-9-3
 */
@RestController
@RequestMapping(value = "/projectTakeNumber")
public class ProjectTakeNumberController extends BaseController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectNumberRecordDao projectNumberRecordDao;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectTakeNumberService projectTakeNumberService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private ProjectQrcodeRecordService projectQrcodeRecordService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ProjectNumberRecordService projectNumberRecordService;

    @RequestMapping(value = "/apply", name = "项目拿号申请")
    public ModelAndView apply(Integer projectId) throws BusinessException {
        //获取流程模型
        String boxName = baseParameterService.getBaseParameter(BaseParameterEnum.PROJECT_TAKE_NUMBER_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("project/takeNumber/apply", boxReDto.getId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
        modelAndView.addObject("projectId", projectInfo.getId());

        return modelAndView;
    }

    @PostMapping(value = "/applyCommit", name = "项目拿号申请提交")
    public HttpResult applyCommit(ProjectTakeNumber projectTakeNumber) {
        try {
            projectTakeNumberService.applyCommit(projectTakeNumber, BaseParameterEnum.PROJECT_TAKE_NUMBER_PROCESS_KEY);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            baseService.writeExceptionInfo(e, "修改项目信息异常");
            return HttpResult.newErrorResult(e);
        }
    }

    @RequestMapping(value = "/approvalView", name = "项目拿号审批页")
    public ModelAndView approvalView(Integer boxId, String processInsId, String taskId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("project/takeNumber/approval", processInsId, boxId, taskId, agentUserAccount);
        ProjectTakeNumber data = projectTakeNumberService.getDataByProcessInsId(processInsId);
        ProjectTakeNumberVo projectTakeNumberVo = projectTakeNumberService.getProjectTakeNumberVo(data, null);
        modelAndView.addObject("projectTakeNumber", projectTakeNumberVo);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(data.getProjectId());
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
        //详情页
        if (taskId.equals("-1")) {
            if (data.getNumberRecordId() != null) {
                ProjectNumberRecord projectNumberRecord = projectNumberRecordDao.getProjectNumberRecord(data.getNumberRecordId());
                modelAndView.addObject("numberValue", projectNumberRecord.getNumberValue());
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/detailView", name = "详情页", method = RequestMethod.GET)
    public ModelAndView detailsView(Integer boxId, String processInsId) {
        return approvalView(boxId, processInsId, "-1", "");
    }

    @RequestMapping(value = "/approvalCommit", name = "审批提交", method = RequestMethod.POST)
    public HttpResult approvalCommit(ApprovalModelDto approvalModelDto) {
        try {
            projectTakeNumberService.approvalCommit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "提交失败");
            return HttpResult.newErrorResult(e);
        }
    }

    @RequestMapping(value = "/editView", name = "返回修改视图", method = RequestMethod.GET)
    public ModelAndView editView(Integer boxId, String processInsId, String taskId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("project/takeNumber/apply", processInsId, boxId, taskId, agentUserAccount);
        ProjectTakeNumber data = projectTakeNumberService.getDataByProcessInsId(processInsId);
        modelAndView.addObject("projectTakeNumber", data);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(data.getProjectId());
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
        modelAndView.addObject("projectId", projectInfo.getId());

        return modelAndView;
    }

    @RequestMapping(value = "/editCommit", name = "修改提交", method = RequestMethod.POST)
    public HttpResult editCommit(String businessDataJson, ApprovalModelDto approvalModelDto) {
        try {
            ProjectTakeNumber projectTakeNumber = JSON.parseObject(businessDataJson, ProjectTakeNumber.class);
            projectTakeNumberService.editData(projectTakeNumber);
            projectTakeNumberService.processEditSubmit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "修改失败");
            return HttpResult.newErrorResult(e);
        }
    }

    @RequestMapping(value = "/getTakeNumberList", name = "拿号列表", method = RequestMethod.GET)
    public BootstrapTableVo getDocumentSendVoList(Integer projectId) {
        return projectTakeNumberService.getDocumentSendVoList(projectId);
    }

    @GetMapping(value = "/getProjectWordNumber", name = "文号拿取")
    public HttpResult getProjectWordNumber(@RequestParam(name = "areaId", defaultValue = "0") Integer areaId, String formData) {
        try {
            ProjectTakeNumber projectTakeNumber = JSONObject.parseObject(formData, ProjectTakeNumber.class);
            SysSymbolListDto sysSymbolListDto = projectNumberRecordService.getSysSymbolListDto(areaId, projectTakeNumber.getProjectId(), projectTakeNumber.getReportType(), projectTakeNumber.getAssessProjectType());
            projectTakeNumber.setNumberValue(sysSymbolListDto.getSymbol());
            projectTakeNumberService.saveAndUpdateProjectTakeNumber(projectTakeNumber, false);
            return HttpResult.newCorrectResult(200, projectTakeNumber);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/toolBaseOrCode", name = "二维码 拿取")
    public HttpResult toolBaseOrCode(Integer takeNumberId) {
        try {
            ProjectTakeNumber projectTakeNumber = projectTakeNumberService.getProjectTakeNumberById(takeNumberId) ;
            SysAttachmentDto sysAttachmentDto = projectQrcodeRecordService.toolBaseOrCode(projectTakeNumber);
            sysAttachmentDto.setFilePath(baseAttachmentService.getViewImageUrl(sysAttachmentDto.getId()));
            return HttpResult.newCorrectResult(200, sysAttachmentDto);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

}
