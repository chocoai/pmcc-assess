package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectNumberRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectTakeNumberDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectNumberRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectTakeNumber;
import com.copower.pmcc.assess.dto.output.project.ProjectTakeNumberVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.project.ProjectTakeNumberServiceEvent;
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

import java.util.List;

@Service
public class ProjectTakeNumberService {
    @Autowired
    private ProjectTakeNumberDao projectTakeNumberDao;
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
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectNumberRecordDao projectNumberRecordDao;
    @Autowired
    private PublicService publicService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Transactional(rollbackFor = Exception.class)
    public void applyCommit(ProjectTakeNumber projectTakeNumber, BaseParameterEnum baseParameterEnum) throws BusinessException {
        try {
            projectTakeNumber.setCreator(commonService.thisUserAccount());
            projectTakeNumber.setStatus(ProcessStatusEnum.RUN.getValue());
            //流程
            ProcessUserDto processUserDto = submitTask(projectTakeNumber, baseParameterEnum);
            if (processUserDto != null) projectTakeNumber.setProcessInsId(processUserDto.getProcessInsId());
            projectTakeNumberDao.addProjectTakeNumber(projectTakeNumber);
            //更新附件id
            SysAttachmentDto queryParam = new SysAttachmentDto();
            queryParam.setTableName(FormatUtils.entityNameConvertToTableName(ProjectTakeNumber.class));
            queryParam.setTableId(0);
            queryParam.setCreater(commonService.thisUserAccount());
            queryParam.setAppKey(applicationConstant.getAppKey());
            SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
            sysAttachmentDto.setTableId(projectTakeNumber.getId());
            erpRpcAttachmentService.updateAttachmentByParam(queryParam, sysAttachmentDto);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private ProcessUserDto submitTask(ProjectTakeNumber projectTakeNumber, BaseParameterEnum baseParameterEnum) throws BusinessException {
        ProcessUserDto processUserDto = new ProcessUserDto();
        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setProjectId(projectTakeNumber.getProjectId());
        String boxName = baseParameterService.getBaseParameter(baseParameterEnum);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        ;
        ProjectInfo costsProjectInfo = projectInfoService.getProjectInfoById(projectTakeNumber.getProjectId());

        processInfo.setFolio(String.format("%s【项目拿号申请】", costsProjectInfo.getProjectName()));//流程描述
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectTakeNumber.class));
        processInfo.setProcessEventExecutor(ProjectTakeNumberServiceEvent.class);
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setTableId(projectTakeNumber.getId());
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getName());
        processInfo.setStartUser(projectTakeNumber.getCreator());
        try {
            processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, commonService.thisUserAccount(), false);
        } catch (Exception e) {
            log.error(String.format("流程发起失败: %s", e.getMessage()), e);
            throw new BusinessException(String.format("流程发起失败: %s", e.getMessage()));
        }
        return processUserDto;
    }

    public ProjectTakeNumber getDataByProcessInsId(String processInsId) {
        ProjectTakeNumber projectTakeNumber = new ProjectTakeNumber();
        projectTakeNumber.setProcessInsId(processInsId);
        List<ProjectTakeNumber> projectTakeNumbers = projectTakeNumberDao.getProjectTakeNumber(projectTakeNumber);
        if (CollectionUtils.isNotEmpty(projectTakeNumbers))
            projectTakeNumber = projectTakeNumbers.get(0);
        return projectTakeNumber;
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


    public void editData(ProjectTakeNumber projectTakeNumber) {
        projectTakeNumberDao.modifyProjectTakeNumber(projectTakeNumber);
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
        ProjectTakeNumber takeNumber = new ProjectTakeNumber();
        takeNumber.setProjectId(projectId);
        takeNumber.setStatus(ProcessStatusEnum.FINISH.getValue());
        List<ProjectTakeNumber> list = projectTakeNumberDao.getProjectTakeNumber(takeNumber);
        List<ProjectTakeNumberVo> vos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(list)) {
            vos = LangUtils.transform(list, o -> getProjectTakeNumberVo(o, o.getNumberRecordId()));
        }
        vo.setRows(vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public ProjectTakeNumberVo getProjectTakeNumberVo(ProjectTakeNumber projectTakeNumber, Integer numberRecordId) {
        if (projectTakeNumber == null) {
            return null;
        }
        ProjectTakeNumberVo vo = new ProjectTakeNumberVo();
        BeanUtils.copyProperties(projectTakeNumber, vo);
        if (projectTakeNumber.getReportType() != null) {
            vo.setReportTypeName(baseDataDicService.getNameById(projectTakeNumber.getReportType()));
            vo.setCreatorName(publicService.getUserNameByAccount(projectTakeNumber.getCreator()));
        }
        if (numberRecordId != null) {
            ProjectNumberRecord projectNumberRecord = projectNumberRecordDao.getProjectNumberRecord(numberRecordId);
            vo.setTakeTime(projectNumberRecord.getGmtCreated());
            vo.setNumberValue(projectNumberRecord.getNumberValue());
        }
        return vo;
    }

}
