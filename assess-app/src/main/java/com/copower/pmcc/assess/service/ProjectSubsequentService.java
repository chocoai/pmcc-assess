package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectSubsequentDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSubsequent;
import com.copower.pmcc.assess.dto.output.project.ProjectSubsequentVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.project.ProjectSubsequentServiceEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcAttachmentService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ProjectSubsequentService {
    @Autowired
    private ProjectSubsequentDao projectSubsequentDao;
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
    private ApplicationConstant applicationConstant;
    @Autowired
    private ErpRpcAttachmentService erpRpcAttachmentService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Transactional(rollbackFor = Exception.class)
    public void applyCommit(ProjectSubsequent projectSubsequent, BaseParameterEnum baseParameterEnum) throws BusinessException {
        try {
            projectSubsequent.setCreator(commonService.thisUserAccount());
            projectSubsequent.setStatus(ProcessStatusEnum.RUN.getValue());
            //流程
            ProcessUserDto processUserDto = submitTask(projectSubsequent, baseParameterEnum);
            if (processUserDto != null) projectSubsequent.setProcessInsId(processUserDto.getProcessInsId());
            projectSubsequentDao.addProjectSubsequent(projectSubsequent);
            //更新附件id
            SysAttachmentDto queryParam = new SysAttachmentDto();
            queryParam.setTableName(FormatUtils.entityNameConvertToTableName(ProjectSubsequent.class));
            queryParam.setTableId(0);
            queryParam.setCreater(commonService.thisUserAccount());
            queryParam.setAppKey(applicationConstant.getAppKey());
            SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
            sysAttachmentDto.setTableId(projectSubsequent.getId());
            erpRpcAttachmentService.updateAttachmentByParam(queryParam, sysAttachmentDto);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private ProcessUserDto submitTask(ProjectSubsequent projectSubsequent, BaseParameterEnum baseParameterEnum) throws BusinessException {
        ProcessUserDto processUserDto = new ProcessUserDto();
        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setProjectId(projectSubsequent.getProjectId());
        String boxName = baseParameterService.getBaseParameter(baseParameterEnum);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        ;
        ProjectInfo costsProjectInfo = projectInfoService.getProjectInfoById(projectSubsequent.getProjectId());

        processInfo.setFolio(String.format("%s【后续项目申请】", costsProjectInfo.getProjectName()));//流程描述
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectSubsequent.class));
        processInfo.setProcessEventExecutor(ProjectSubsequentServiceEvent.class);
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setTableId(projectSubsequent.getId());
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getName());
        processInfo.setStartUser(projectSubsequent.getCreator());
        try {
            processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, commonService.thisUserAccount(), false);
        } catch (Exception e) {
            log.error(String.format("流程发起失败: %s", e.getMessage()), e);
            throw new BusinessException(String.format("流程发起失败: %s", e.getMessage()));
        }
        return processUserDto;
    }

    public ProjectSubsequent getDataByProcessInsId(String processInsId) {
        ProjectSubsequent projectSubsequent = new ProjectSubsequent();
        projectSubsequent.setProcessInsId(processInsId);
        List<ProjectSubsequent> projectSubsequents = projectSubsequentDao.getProjectSubsequent(projectSubsequent);
        if (CollectionUtils.isNotEmpty(projectSubsequents))
            projectSubsequent = projectSubsequents.get(0);
        return projectSubsequent;
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


    public void editData(ProjectSubsequent projectSubsequent) {
        projectSubsequentDao.modifyProjectSubsequent(projectSubsequent);
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
        ProjectSubsequent subsequent = new ProjectSubsequent();
        subsequent.setProjectId(projectId);
        List<ProjectSubsequent> list = projectSubsequentDao.getProjectSubsequent(subsequent);
        List<ProjectSubsequentVo> vos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(list)) {
            vos = LangUtils.transform(list, o -> getProjectSubsequentVo(o));
        }
        vo.setRows(vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public ProjectSubsequentVo getProjectSubsequentVo(ProjectSubsequent projectSubsequent) {
        if (projectSubsequent == null) {
            return null;
        }
        ProjectSubsequentVo vo = new ProjectSubsequentVo();
        BeanUtils.copyProperties(projectSubsequent, vo);
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(projectSubsequent.getId(), null, FormatUtils.entityNameConvertToTableName(ProjectSubsequent.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto)).append(" ");
            }
            vo.setFileViewName(builder.toString());
        }
        return vo;
    }

}
