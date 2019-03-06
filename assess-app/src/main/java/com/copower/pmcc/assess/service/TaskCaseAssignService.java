package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.funi.FuniHousesDao;
import com.copower.pmcc.assess.dal.basis.dao.funi.TaskCaseAssignDao;
import com.copower.pmcc.assess.dal.basis.entity.FuniHouses;
import com.copower.pmcc.assess.dal.basis.entity.TaskCaseAssign;
import com.copower.pmcc.assess.dto.output.TaskCaseAssignVo;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class TaskCaseAssignService {
    @Autowired
    private TaskCaseAssignDao taskCaseAssignDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private FuniHousesDao funiHousesDao;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private PublicService publicService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public TaskCaseAssignVo getTaskCaseAssignByProcessInsId(String processInsId) {
        TaskCaseAssign taskCaseAssign = new TaskCaseAssign();
        taskCaseAssign.setProcessInsId(processInsId);
        List<TaskCaseAssign> taskCaseAssigns = taskCaseAssignDao.getListObject(taskCaseAssign);
        if (!ObjectUtils.isEmpty(taskCaseAssigns)) {
            return getTaskCaseAssignVo(taskCaseAssigns.get(0));
        } else {
            return null;
        }
    }

    public TaskCaseAssignVo getByTaskCaseAssignId(Integer id) {
        TaskCaseAssign object = taskCaseAssignDao.getSingleObject(id);
        return getTaskCaseAssignVo(object);
    }

    /**
     * 获取列表
     *
     * @return
     */
    public BootstrapTableVo getListVos(String executor) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ArrayList<TaskCaseAssignVo> vos = new ArrayList<>();
        List<TaskCaseAssign> taskCaseAssignList = taskCaseAssignDao.getTaskCaseAssignList(executor);
        if (CollectionUtils.isNotEmpty(taskCaseAssignList)) {
            for (TaskCaseAssign item : taskCaseAssignList) {
                vos.add(getTaskCaseAssignVo(item));
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<TaskCaseAssignVo>() : vos);
        return vo;
    }

    public List<TaskCaseAssign> getTaskCaseAssignList(String executor) {
        return taskCaseAssignDao.getTaskCaseAssignList(executor);
    }


    public TaskCaseAssignVo getTaskCaseAssignVo(TaskCaseAssign taskCaseAssign) {
        if (taskCaseAssign == null) return null;
        TaskCaseAssignVo taskCaseAssignVo = new TaskCaseAssignVo();
        BeanUtils.copyProperties(taskCaseAssign, taskCaseAssignVo);
        taskCaseAssignVo.setExecutorName(erpRpcUserService.getSysUser(taskCaseAssign.getExecutor()).getUserName());
        String lpbhIds = taskCaseAssign.getLpbh();
        if (StringUtils.isNotBlank(lpbhIds)) {
            String[] ids = lpbhIds.split(",");
            StringBuilder lpmc = new StringBuilder();
            if (ids != null && ids.length > 0) {
                for (String FuniHousesId : ids) {
                    lpmc.append(funiHousesDao.getFuniHouses(Integer.valueOf(FuniHousesId)).getLpmc());
                    lpmc.append("/");
                }
            }
            taskCaseAssignVo.setLpmc(lpmc.toString().substring(0, lpmc.length() - 1));
        }
        return taskCaseAssignVo;
    }


    /**
     * 保存
     *
     * @param taskCaseAssign
     * @throws BusinessException
     */
    public void addTaskCaseAssignReturnId(TaskCaseAssign taskCaseAssign) {
        taskCaseAssign.setCreator(processControllerComponent.getThisUser());
        if (taskCaseAssignDao.addObject(taskCaseAssign)) {
            TaskCaseAssignVo vo = getTaskCaseAssignVo(taskCaseAssign);
            String projectName = vo.getLpmc();
            //添加任务
            ProjectResponsibilityDto projectTask = new ProjectResponsibilityDto();
            projectTask.setProjectName(projectName + "信息录入");
            projectTask.setUserAccount(taskCaseAssign.getExecutor());
            projectTask.setCreator(processControllerComponent.getThisUser());
            projectTask.setAppKey(applicationConstant.getAppKey());
            projectTask.setProjectId(taskCaseAssign.getId());
            projectTask.setUrl(String.format("/%s/%s", applicationConstant.getAppKey(), "taskCaseAssign/applyIndex?id=" + taskCaseAssign.getId()));
            bpmRpcProjectTaskService.saveProjectTaskExtend(projectTask);
        }
    }

    public boolean updateTaskCaseAssign(TaskCaseAssign taskCaseAssign) {
        return taskCaseAssignDao.updateObject(taskCaseAssign);
    }

    /**
     * 删除
     *
     * @param id
     * @throws BusinessException
     */
    public boolean deleteTaskCaseAssign(Integer id) throws BusinessException {
        //删除待提交任务
        bpmRpcProjectTaskService.deleteProjectTaskByProjectid(applicationConstant.getAppKey(), id);
        return taskCaseAssignDao.deleteObject(id);
    }


    public BootstrapTableVo getApplyHousesList(String lpbh) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ArrayList<FuniHouses> funiHousesList = new ArrayList<>();
        if (StringUtils.isNotBlank(lpbh)) {
            String[] ids = lpbh.split(",");
            StringBuilder lpmc = new StringBuilder();
            if (ids != null && ids.length > 0) {
                for (String FuniHousesId : ids) {
                    funiHousesList.add(funiHousesDao.getFuniHouses(Integer.valueOf(FuniHousesId)));
                }
            }
        }
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(funiHousesList) ? new ArrayList<FuniHouses>() : funiHousesList);
        return bootstrapTableVo;
    }

    /**
     * 流程发起
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ProcessUserDto processStartSubmit(Integer id) throws Exception {
        ProcessUserDto processUserDto = null;
        ProcessInfo processInfo = new ProcessInfo();
        TaskCaseAssign assign = taskCaseAssignDao.getSingleObject(id);
        TaskCaseAssignVo vo = getTaskCaseAssignVo(assign);
        //流程描述
        processInfo.setFolio(vo.getLpmc());
        final String boxName = baseParameterService.getParameterValues(BaseParameterEnum.CASE_ASSIGN_APPLY_KEY.getParameterKey());
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(TaskCaseAssign.class));
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setProcessEventExecutor(BaseProcessEvent.class);
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getKey());
        processInfo.setProcessEventExecutorName(BaseProcessEvent.class.getSimpleName());
        processInfo.setTableId(id);
        try {
            processUserDto = processControllerComponent.processStart(processInfo, processControllerComponent.getThisUser(), false);
            assign.setProcessInsId(processUserDto.getProcessInsId());
            assign.setStatus(ProjectStatusEnum.RUNING.getKey());
            this.updateTaskCaseAssign(assign);
            //删除待提交任务
            bpmRpcProjectTaskService.deleteProjectTaskByProjectid(applicationConstant.getAppKey(), assign.getId());
        } catch (Exception e) {
            logger.error(String.format("流程发起失败: %s", e.getMessage()), e);
            throw e;
        }
        return processUserDto;
    }

    /**
     * 审批
     *
     * @param approvalModelDto
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void processApprovalSubmit(ApprovalModelDto approvalModelDto) throws Exception {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 返回修改 提交
     *
     * @param approvalModelDto
     * @throws Exception
     */
    public void processEditSubmit(ApprovalModelDto approvalModelDto) throws Exception {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(publicService.getEditApprovalModel(approvalModelDto), false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}
