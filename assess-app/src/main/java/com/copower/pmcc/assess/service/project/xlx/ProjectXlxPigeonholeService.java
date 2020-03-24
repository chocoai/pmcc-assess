package com.copower.pmcc.assess.service.project.xlx;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.xlx.ProjectXlxPigeonholeDao;
import com.copower.pmcc.assess.dal.basis.dao.project.xlx.ProjectXlxPigeonholeRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonhole;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
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
import java.util.Date;
import java.util.List;

@Service
public class ProjectXlxPigeonholeService {
    @Autowired
    private ProjectXlxPigeonholeDao projectXlxPigeonholeDao;
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
    private ProjectXlxPigeonholeRecordDao projectXlxPigeonholeRecordDao;


    private final Logger log = LoggerFactory.getLogger(getClass());

    @Transactional(rollbackFor = Exception.class)
    public void applyCommit(ProjectXlxPigeonhole projectXlxPigeonhole, BaseParameterEnum baseParameterEnum) throws BusinessException {
        try {
            projectXlxPigeonhole.setCreator(commonService.thisUserAccount());
            projectXlxPigeonhole.setStatus(ProcessStatusEnum.RUN.getValue());
            //流程
            ProcessUserDto processUserDto = submitTask(projectXlxPigeonhole, baseParameterEnum);
            if (processUserDto != null) projectXlxPigeonhole.setProcessInsId(processUserDto.getProcessInsId());
            editData(projectXlxPigeonhole);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private ProcessUserDto submitTask(ProjectXlxPigeonhole projectXlxPigeonhole, BaseParameterEnum baseParameterEnum) throws BusinessException {
        ProcessUserDto processUserDto = new ProcessUserDto();
        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setProjectId(projectXlxPigeonhole.getProjectId());
        String boxName = baseParameterService.getBaseParameter(baseParameterEnum);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        ProjectInfo costsProjectInfo = projectInfoService.getProjectInfoById(projectXlxPigeonhole.getProjectId());
        processInfo.setFolio(String.format("%s【项目归档】", costsProjectInfo.getProjectName()));//流程描述
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectXlxPigeonhole.class));
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setTableId(projectXlxPigeonhole.getId());
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getName());
        processInfo.setStartUser(projectXlxPigeonhole.getCreator());
        try {
            processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, commonService.thisUserAccount(), false);
        } catch (Exception e) {
            log.error(String.format("流程发起失败: %s", e.getMessage()), e);
            throw new BusinessException(String.format("流程发起失败: %s", e.getMessage()));
        }
        return processUserDto;
    }

    public ProjectXlxPigeonhole getDataByProcessInsId(String processInsId) {
        ProjectXlxPigeonhole projectXlxPigeonhole = new ProjectXlxPigeonhole();
        projectXlxPigeonhole.setProcessInsId(processInsId);
        List<ProjectXlxPigeonhole> projectXlxPigeonholes = projectXlxPigeonholeDao.getProjectXlxPigeonhole(projectXlxPigeonhole);
        if (CollectionUtils.isNotEmpty(projectXlxPigeonholes))
            projectXlxPigeonhole = projectXlxPigeonholes.get(0);
        return projectXlxPigeonhole;
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


    public void editData(ProjectXlxPigeonhole projectXlxPigeonhole) {
        projectXlxPigeonholeDao.modifyProjectXlxPigeonhole(projectXlxPigeonhole);
    }

    public void addData(ProjectXlxPigeonhole projectXlxPigeonhole) {
        projectXlxPigeonholeDao.addProjectXlxPigeonhole(projectXlxPigeonhole);
    }

    /**
     * 获取数据列表
     *
     * @param projectId
     * @return
     */
    public BootstrapTableVo getXlxPigeonholeList(Integer projectId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ProjectXlxPigeonhole Pigeonhole = new ProjectXlxPigeonhole();
        Pigeonhole.setProjectId(projectId);
        Pigeonhole.setStatus(ProcessStatusEnum.FINISH.getValue());
        List<ProjectXlxPigeonhole> list = projectXlxPigeonholeDao.getProjectXlxPigeonhole(Pigeonhole);
        vo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<ProjectXlxPigeonhole>() : list);
        vo.setTotal(page.getTotal());
        return vo;
    }


    //清空无效数据
    @Transactional(rollbackFor = Exception.class)
    public void clean() {
        List<ProjectXlxPigeonhole> invalidList = projectXlxPigeonholeDao.getInvalidList();
        if (CollectionUtils.isNotEmpty(invalidList)) {
            for (ProjectXlxPigeonhole item : invalidList) {
                projectXlxPigeonholeRecordDao.deleteProjectXlxPigeonholeRecordByMasterId(item.getId());
                projectXlxPigeonholeDao.deleteProjectXlxPigeonhole(item.getId());
            }
        }
    }

    /**
     * 获取项目归档日期
     * @param projectId
     * @return
     */
    public Date getPigeonholeDateByProjectId(Integer projectId){
        ProjectXlxPigeonhole Pigeonhole = new ProjectXlxPigeonhole();
        Pigeonhole.setProjectId(projectId);
        Pigeonhole.setStatus(ProcessStatusEnum.FINISH.getValue());
        List<ProjectXlxPigeonhole> list = projectXlxPigeonholeDao.getProjectXlxPigeonhole(Pigeonhole);
        if(CollectionUtils.isEmpty(list)) return null;
        return list.get(0).getFilingDate();
    }

}
