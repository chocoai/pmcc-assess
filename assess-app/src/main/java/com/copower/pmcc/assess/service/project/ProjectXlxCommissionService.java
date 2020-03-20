package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectXlxCommissionDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxCommission;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.project.ProjectXlxCommissionEvent;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectXlxCommissionService {
    @Autowired
    private ProjectXlxCommissionDao projectXlxCommissionDao;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectXlxCommissionRatioService projectXlxCommissionRatioService;
    @Autowired
    private ProjectXlxRebateRatioService projectXlxRebateRatioService;


    private final Logger log = LoggerFactory.getLogger(getClass());

    @Transactional(rollbackFor = Exception.class)
    public void applyCommit(ProjectXlxCommission projectXlxCommission, BaseParameterEnum baseParameterEnum) throws BusinessException {
        try {
            projectXlxCommission.setCreator(commonService.thisUserAccount());
            projectXlxCommission.setStatus(ProcessStatusEnum.RUN.getValue());
            //流程
            ProcessUserDto processUserDto = submitTask(projectXlxCommission, baseParameterEnum);
            if (processUserDto != null) projectXlxCommission.setProcessInsId(processUserDto.getProcessInsId());
            editData(projectXlxCommission);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private ProcessUserDto submitTask(ProjectXlxCommission projectXlxCommission, BaseParameterEnum baseParameterEnum) throws BusinessException {
        ProcessUserDto processUserDto = new ProcessUserDto();
        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setProjectId(projectXlxCommission.getProjectId());
        String boxName = baseParameterService.getBaseParameter(baseParameterEnum);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        ProjectInfo costsProjectInfo = projectInfoService.getProjectInfoById(projectXlxCommission.getProjectId());
        processInfo.setFolio(String.format("%s【项目提升】", costsProjectInfo.getProjectName()));//流程描述
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectXlxCommission.class));
        processInfo.setProcessEventExecutor(ProjectXlxCommissionEvent.class);
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setTableId(projectXlxCommission.getId());
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getName());
        processInfo.setStartUser(projectXlxCommission.getCreator());
        try {
            processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, commonService.thisUserAccount(), false);
        } catch (Exception e) {
            log.error(String.format("流程发起失败: %s", e.getMessage()), e);
            throw new BusinessException(String.format("流程发起失败: %s", e.getMessage()));
        }
        return processUserDto;
    }

    public ProjectXlxCommission getDataByProcessInsId(String processInsId) {
        ProjectXlxCommission projectXlxCommission = new ProjectXlxCommission();
        projectXlxCommission.setProcessInsId(processInsId);
        List<ProjectXlxCommission> projectXlxCommissions = projectXlxCommissionDao.getProjectXlxCommission(projectXlxCommission);
        if (CollectionUtils.isNotEmpty(projectXlxCommissions))
            projectXlxCommission = projectXlxCommissions.get(0);
        return projectXlxCommission;
    }

    public void approvalCommit(ApprovalModelDto approvalModelDto) {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            e.printStackTrace();
            log.error("提交失败", e);
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
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }


    public void editData(ProjectXlxCommission projectXlxCommission) {
        projectXlxCommissionDao.modifyProjectXlxCommission(projectXlxCommission);
    }

    public void addData(ProjectXlxCommission projectXlxCommission) {
        projectXlxCommissionDao.addProjectXlxCommission(projectXlxCommission);
    }

    /**
     * 获取数据列表
     *
     * @param projectId
     * @return
     */
    public BootstrapTableVo getDocumentSendVoList(Integer projectId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ProjectXlxCommission commission = new ProjectXlxCommission();
        commission.setProjectId(projectId);
        List<ProjectXlxCommission> list = projectXlxCommissionDao.getProjectXlxCommission(commission);
        vo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<ProjectXlxCommission>() : list);
        vo.setTotal(page.getTotal());
        return vo;
    }


    //清空无效数据
    public void clean() {
        List<ProjectXlxCommission> invalidList = projectXlxCommissionDao.getInvalidList();
        if (CollectionUtils.isNotEmpty(invalidList)) {
            for (ProjectXlxCommission item : invalidList) {
                projectXlxCommissionRatioService.deleteByMasterId(item.getId());
                projectXlxRebateRatioService.deleteByMasterId(item.getId());
                projectXlxCommissionDao.deleteProjectXlxCommission(item.getId());
            }
        }
    }

}
