package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectChangeTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectChangeLogDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectChangeLog;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.project.ProjectPauseChangeEvent;
import com.copower.pmcc.assess.service.event.project.ProjectRestartChangeEvent;
import com.copower.pmcc.assess.service.event.project.ProjectStopChangeEvent;
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
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProjectStateChangeService extends BaseService {
    @Autowired
    private ProjectChangeLogDao projectChangeLogMapper;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    /**
     * 通过项目id和变更类型获取数据
     * @param projectId
     * @param changeTypeEnum
     * @return
     */
    public List<ProjectChangeLog> getProjectChangeLog(Integer projectId, ProjectChangeTypeEnum changeTypeEnum){
        ProjectChangeLog where = new ProjectChangeLog();
        where.setProjectId(projectId);
        where.setChangeType(changeTypeEnum.getValue());

        List<ProjectChangeLog> changeLogs = projectChangeLogMapper.getProjectChangeLog(where);
        if (CollectionUtils.isNotEmpty(changeLogs)) {
            return changeLogs;
        }

        return null;
    }

    /**
     * 获取变更列表
     * @return
     */
    public BootstrapTableVo getProjectChangeHistory(Integer projectId, ProjectChangeTypeEnum changeTypeEnum) throws Exception{
        //Bootstrap表格对象
        BootstrapTableVo vo = new BootstrapTableVo();
        //分页参数对象
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ProjectChangeLog> costsProjectChangeLogs = getProjectChangeLog(projectId,changeTypeEnum);
        List<Map<String,String>> maps = new ArrayList<>();
        //遍历集合
        for (int i = 0; i < costsProjectChangeLogs.size(); i++) {
            //将动态表单元素字段转换为JSONArray
            JSONArray arry = new JSONArray("[" + costsProjectChangeLogs.get(i).getNewRecord() + "]");
            for (int j = 0; j < arry.length(); j++) {
                JSONObject jsonObject = arry.getJSONObject(j);
                String a= costsProjectChangeLogs.get(i).getStatus();
                jsonObject.put("status", costsProjectChangeLogs.get(i).getStatus());
                jsonObject.put("processInsId", costsProjectChangeLogs.get(i).getProcessInsId());
                /**
                 * 开始封装map到list中，完成数据组装
                 */
                Map<String, String> map = new HashMap<String, String>();
                for (Iterator<?> iter = jsonObject.keys(); iter.hasNext(); ) {
                    String key = (String) iter.next();
                    String value = jsonObject.get(key).toString();
                    map.put(key, value);
                }
                maps.add(map);
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(maps);
        return vo;
    }

    @Transactional(rollbackFor = Exception.class)
    public void applyCommit(ProjectChangeLog costsProjectChangeLog, BaseParameterEnum baseParameterEnum, ProjectChangeTypeEnum projectChangeTypeEnum) throws BusinessException {
        try {
            //流程
            ProcessUserDto processUserDto = submitTask(costsProjectChangeLog, baseParameterEnum,projectChangeTypeEnum);
            if (processUserDto != null) costsProjectChangeLog.setProcessInsId(processUserDto.getProcessInsId());
            costsProjectChangeLog.setChangeType(projectChangeTypeEnum.getValue());
            costsProjectChangeLog.setCreator(commonService.thisUserAccount());
            costsProjectChangeLog.setStatus(ProcessStatusEnum.RUN.getValue());
            projectChangeLogMapper.addProjectChangeLog(costsProjectChangeLog);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }
    private ProcessUserDto submitTask(ProjectChangeLog costsProjectChangeLog, BaseParameterEnum baseParameterEnum, ProjectChangeTypeEnum projectChangeTypeEnum) throws BusinessException {
        ProcessUserDto processUserDto = new ProcessUserDto();
        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setProjectId(costsProjectChangeLog.getProjectId());
        String boxName = baseParameterService.getBaseParameter(baseParameterEnum);
        BoxReDto boxReDto =  bpmRpcBoxService.getBoxReByBoxName(boxName);;
        ProjectInfo costsProjectInfo = projectInfoService.getProjectInfoById(costsProjectChangeLog.getProjectId());

        processInfo.setFolio(String.format("%s【变更提交】%s",projectChangeTypeEnum.getName(), costsProjectInfo.getProjectName()));//流程描述
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectChangeLog.class));
        //监听器判断
        if(projectChangeTypeEnum.getValue().equals("pause_change")){
            processInfo.setProcessEventExecutor(ProjectPauseChangeEvent.class);
        }else if(projectChangeTypeEnum.getValue().equals("stop_change")){
            processInfo.setProcessEventExecutor(ProjectStopChangeEvent.class);
        }else if(projectChangeTypeEnum.getValue().equals("restart_change")){
            processInfo.setProcessEventExecutor(ProjectRestartChangeEvent.class);
        }
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setTableId(costsProjectChangeLog.getId());
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getName());
        processInfo.setWorkStage(projectChangeTypeEnum.getName());
        processInfo.setStartUser(costsProjectChangeLog.getCreator());
        try {
            processUserDto = processControllerComponent.processStart(processInfo, commonService.thisUserAccount(), false);
        } catch (Exception e) {
            log.error(String.format("流程发起失败: %s", e.getMessage()), e);
        }
        return processUserDto;
    }

    public ProjectChangeLog getDataByProcessInsId(String processInsId) {
        ProjectChangeLog costsProjectChangeLog = new ProjectChangeLog();
        costsProjectChangeLog.setProcessInsId(processInsId);
        List<ProjectChangeLog> costsProjectChangeLogs = projectChangeLogMapper.getProjectChangeLog(costsProjectChangeLog);
        if (com.alibaba.dubbo.common.utils.CollectionUtils.isNotEmpty(costsProjectChangeLogs)) costsProjectChangeLog = costsProjectChangeLogs.get(0);
        return costsProjectChangeLog;
    }

    @Deprecated
    @Transactional(rollbackFor = Exception.class)
    public void editCommit(ProjectChangeLog costsProjectChangeLog, ApprovalModelDto approvalModelDto, ProjectChangeTypeEnum projectChangeTypeEnum) throws BpmException {
        //1.更新数据
        projectChangeLogMapper.modifyProjectChangeLog(costsProjectChangeLog);
        //2.提交任务
        approvalModelDto.setWorkStage(projectChangeTypeEnum.getName());
        processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateCommit(ProjectChangeLog costsProjectChangeLog, ApprovalModelDto approvalModelDto,ProjectChangeTypeEnum projectChangeTypeEnum)throws Exception{
        //1.更新数据
        projectChangeLogMapper.modifyProjectChangeLog(costsProjectChangeLog);
        //2.提交任务
        approvalModelDto.setWorkStage(projectChangeTypeEnum.getName());
        publicService.getEditApprovalModel(approvalModelDto);
        processControllerComponent.processSubmitLoopTaskNodeArg(publicService.getEditApprovalModel(approvalModelDto), false);
    }

    @Transactional(rollbackFor = Exception.class)
    public void approvalCommit(ApprovalModelDto approvalModelDto) {
        ProjectChangeLog costsProjectChangeLog = getDataByProcessInsId(approvalModelDto.getProcessInsId());
        approvalModelDto.setWorkStageId(costsProjectChangeLog.getId());
        approvalModelDto.setWorkStage(ProjectChangeTypeEnum.create(costsProjectChangeLog.getChangeType()).getName());
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            e.printStackTrace();
            log.error("提交失败",e);
        }
    }

    //变更审批中
    public boolean isChanging(Integer projectId,ProjectChangeTypeEnum projectChangeTypeEnum) {
        ProjectChangeLog costsProjectChangeLog = new ProjectChangeLog();
        costsProjectChangeLog.setStatus(ProcessStatusEnum.RUN.getValue());
        costsProjectChangeLog.setProjectId(projectId);
        costsProjectChangeLog.setChangeType(projectChangeTypeEnum.getValue());
        List<ProjectChangeLog> costsProjectChangeLogs = projectChangeLogMapper.getProjectChangeLog(costsProjectChangeLog);
        if(CollectionUtils.isNotEmpty(costsProjectChangeLogs)){
            return false;
        }else{
            return true;
        }
    }
}
