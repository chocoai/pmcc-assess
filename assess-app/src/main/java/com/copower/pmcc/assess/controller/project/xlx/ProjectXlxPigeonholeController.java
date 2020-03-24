package com.copower.pmcc.assess.controller.project.xlx;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonhole;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectNumberRecordService;
import com.copower.pmcc.assess.service.project.xlx.ProjectXlxPigeonholeRecordService;
import com.copower.pmcc.assess.service.project.xlx.ProjectXlxPigeonholeService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.BoxRuDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author: huhao
 * @data: 2018-9-3
 */
@Controller
@RequestMapping(value = "/projectXlxPigeonhole")
public class ProjectXlxPigeonholeController extends BaseController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectXlxPigeonholeService projectXlxPigeonholeService;
    @Autowired
    private ProjectXlxPigeonholeRecordService projectXlxPigeonholeRecordService;

    @RequestMapping(value = "/apply", name = "项目归档申请")
    public ModelAndView apply(Integer projectId) throws BusinessException {
        //清除无效数据
        projectXlxPigeonholeService.clean();

        //获取流程模型
        String boxName = baseParameterService.getBaseParameter(BaseParameterEnum.PROJECT_PIGEONHOLE_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("customer/xlx/pigeonhole/apply", boxReDto.getId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
        modelAndView.addObject("projectId", projectInfo.getId());
        ProjectXlxPigeonhole projectXlxPigeonhole = new ProjectXlxPigeonhole();
        projectXlxPigeonhole.setProjectId(projectId);
        projectXlxPigeonholeService.addData(projectXlxPigeonhole);
        projectXlxPigeonholeRecordService.init(projectId,projectXlxPigeonhole.getId());
        modelAndView.addObject("projectXlxPigeonhole", projectXlxPigeonhole);
        return modelAndView;
    }

    @ResponseBody
    @PostMapping(value = "/applyCommit", name = "项目归档申请提交")
    public HttpResult applyCommit(String formData) {
        try {
            ProjectXlxPigeonhole projectXlxPigeonhole = JSON.parseObject(formData, ProjectXlxPigeonhole.class);
            projectXlxPigeonholeService.applyCommit(projectXlxPigeonhole, BaseParameterEnum.PROJECT_PIGEONHOLE_PROCESS_KEY);
        } catch (BusinessException e) {
            logger.error("修改项目信息异常", e);
            e.printStackTrace();
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/approvalView", name = "项目归档审批页")
    public ModelAndView approvalView(Integer boxId, String processInsId, String taskId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("customer/xlx/pigeonhole/approval", processInsId, boxId, taskId, agentUserAccount);
        ProjectXlxPigeonhole data = projectXlxPigeonholeService.getDataByProcessInsId(processInsId);
        modelAndView.addObject("projectXlxPigeonhole", data);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(data.getProjectId());
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
        return modelAndView;
    }

    @RequestMapping(value = "/detailsView", name = "详情页", method = RequestMethod.GET)
    public ModelAndView detailsView(Integer boxId, String processInsId) {
        return approvalView(boxId, processInsId, "-1", "");
    }

    @RequestMapping(value = "/approvalCommit", name = "审批提交", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult approvalCommit(ApprovalModelDto approvalModelDto) {
        try {
            projectXlxPigeonholeService.approvalCommit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("提交失败", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @RequestMapping(value = "/editView", name = "返回修改视图", method = RequestMethod.GET)
    public ModelAndView editView(Integer boxId, String processInsId, String taskId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("customer/xlx/pigeonhole/apply", processInsId, boxId, taskId, agentUserAccount);
        ProjectXlxPigeonhole data = projectXlxPigeonholeService.getDataByProcessInsId(processInsId);
        modelAndView.addObject("projectXlxPigeonhole", data);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(data.getProjectId());
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
        modelAndView.addObject("projectId", projectInfo.getId());

        return modelAndView;
    }

    @RequestMapping(value = "/editCommit", name = "修改提交", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult editCommit(String businessDataJson, ApprovalModelDto approvalModelDto) {
        try {
            ProjectXlxPigeonhole projectXlxPigeonhole = JSON.parseObject(businessDataJson, ProjectXlxPigeonhole.class);
            projectXlxPigeonholeService.editData(projectXlxPigeonhole);
            projectXlxPigeonholeService.processEditSubmit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("修改失败", e);
            return HttpResult.newErrorResult(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getXlxPigeonholeList", name = "归档列表", method = RequestMethod.GET)
    public BootstrapTableVo getXlxPigeonholeList(Integer projectId) {
        return projectXlxPigeonholeService.getXlxPigeonholeList(projectId);
    }

    @RequestMapping(value = "/detailsIndex", name = "进入详情页面")
    public ModelAndView detailsIndex(String processInsId) {
        BoxRuDto boxRuDto = bpmRpcBoxService.getBoxRuByProcessInstId(processInsId);
        ProjectXlxPigeonhole projectXlxPigeonhole = projectXlxPigeonholeService.getDataByProcessInsId(processInsId);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/customer/xlx/pigeonhole/approval", processInsId, boxRuDto.getBoxId(), "-1", "");
        modelAndView.addObject("projectXlxPigeonhole", projectXlxPigeonhole);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectXlxPigeonhole.getProjectId());
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfo));
        return modelAndView;
    }

}
