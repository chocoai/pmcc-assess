package com.copower.pmcc.assess.service.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectChangeTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectChangeLogDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.ProjectInfoChangeVo;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateConsignorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiatePossessorVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.project.*;
import com.copower.pmcc.assess.service.project.initiate.InitiateConsignorService;
import com.copower.pmcc.assess.service.project.initiate.InitiatePossessorService;
import com.copower.pmcc.assess.service.project.initiate.InitiateUnitInformationService;
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
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
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
    @Autowired
    private InitiateConsignorService consignorService;
    @Autowired
    private InitiateUnitInformationService unitInformationService;
    @Autowired
    private InitiatePossessorService possessorService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ErpRpcAttachmentService erpRpcAttachmentService;

    /**
     * 通过项目id和变更类型获取数据
     *
     * @param projectId
     * @param changeTypeEnum
     * @return
     */
    public List<ProjectChangeLog> getProjectChangeLog(Integer projectId, ProjectChangeTypeEnum changeTypeEnum) {
        ProjectChangeLog where = new ProjectChangeLog();
        where.setProjectId(projectId);
        where.setChangeType(changeTypeEnum.getValue());
        where.setStatus(ProcessStatusEnum.FINISH.getValue());
        List<ProjectChangeLog> changeLogs = projectChangeLogMapper.getProjectChangeLog(where);
        if (CollectionUtils.isNotEmpty(changeLogs)) {
            return changeLogs;
        }
        return null;
    }

    /**
     * 获取变更列表
     *
     * @return
     */
    public BootstrapTableVo getProjectChangeHistory(Integer projectId, ProjectChangeTypeEnum changeTypeEnum) throws Exception {
        //Bootstrap表格对象
        BootstrapTableVo vo = new BootstrapTableVo();
        //分页参数对象
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ProjectChangeLog> costsProjectChangeLogs = getProjectChangeLog(projectId, changeTypeEnum);
        List<Map<String, String>> maps = new ArrayList<>();
        //遍历集合
        for (int i = 0; i < costsProjectChangeLogs.size(); i++) {
            //将动态表单元素字段转换为JSONArray
            JSONArray arry = new JSONArray("[" + costsProjectChangeLogs.get(i).getNewRecord() + "]");
            for (int j = 0; j < arry.length(); j++) {
                JSONObject jsonObject = arry.getJSONObject(j);
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
            ProcessUserDto processUserDto = submitTask(costsProjectChangeLog, baseParameterEnum, projectChangeTypeEnum);
            if (processUserDto != null) costsProjectChangeLog.setProcessInsId(processUserDto.getProcessInsId());
            costsProjectChangeLog.setChangeType(projectChangeTypeEnum.getValue());
            costsProjectChangeLog.setCreator(commonService.thisUserAccount());
            costsProjectChangeLog.setStatus(ProcessStatusEnum.RUN.getValue());
            projectChangeLogMapper.addProjectChangeLog(costsProjectChangeLog);
            //更新附件id
            SysAttachmentDto queryParam = new SysAttachmentDto();
            queryParam.setTableName(FormatUtils.entityNameConvertToTableName(ProjectChangeLog.class));
            queryParam.setTableId(0);
            queryParam.setCreater(commonService.thisUserAccount());
            queryParam.setAppKey(applicationConstant.getAppKey());
            SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
            sysAttachmentDto.setTableId(costsProjectChangeLog.getId());
            erpRpcAttachmentService.updateAttachmentByParam(queryParam, sysAttachmentDto);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private ProcessUserDto submitTask(ProjectChangeLog costsProjectChangeLog, BaseParameterEnum baseParameterEnum, ProjectChangeTypeEnum projectChangeTypeEnum) throws BusinessException {
        ProcessUserDto processUserDto = new ProcessUserDto();
        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setProjectId(costsProjectChangeLog.getProjectId());
        String boxName = baseParameterService.getBaseParameter(baseParameterEnum);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        ;
        ProjectInfo costsProjectInfo = projectInfoService.getProjectInfoById(costsProjectChangeLog.getProjectId());

        processInfo.setFolio(String.format("%s【变更提交】%s", projectChangeTypeEnum.getName(), costsProjectInfo.getProjectName()));//流程描述
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectChangeLog.class));
        //监听器判断
        if (projectChangeTypeEnum.equals(ProjectChangeTypeEnum.PAUSE_CHANGE)) {
            processInfo.setProcessEventExecutor(ProjectPauseChangeEvent.class);
        } else if (projectChangeTypeEnum.equals(ProjectChangeTypeEnum.STOP_CHANGE)) {
            processInfo.setProcessEventExecutor(ProjectStopChangeEvent.class);
        } else if (projectChangeTypeEnum.equals(ProjectChangeTypeEnum.RESTART_CHANGE)) {
            processInfo.setProcessEventExecutor(ProjectRestartChangeEvent.class);
        } else if (projectChangeTypeEnum.equals(ProjectChangeTypeEnum.INFO_CHANGE)) {
            processInfo.setProcessEventExecutor(ProjectInfoChangeEvent.class);
        }else if(projectChangeTypeEnum.equals(ProjectChangeTypeEnum.SCHEME_CHANGE)){
            processInfo.setProcessEventExecutor(ProjectSchemeChangeEvent.class);
        }
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setTableId(costsProjectChangeLog.getId());
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getName());
        processInfo.setWorkStage(projectChangeTypeEnum.getName());
        processInfo.setStartUser(costsProjectChangeLog.getCreator());
        try {
            processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(),processInfo, commonService.thisUserAccount(), false);
        } catch (Exception e) {
            log.error(String.format("流程发起失败: %s", e.getMessage()), e);
            throw new BusinessException(String.format("流程发起失败: %s", e.getMessage()));
        }
        return processUserDto;
    }

    public ProjectChangeLog getDataByProcessInsId(String processInsId) {
        ProjectChangeLog costsProjectChangeLog = new ProjectChangeLog();
        costsProjectChangeLog.setProcessInsId(processInsId);
        List<ProjectChangeLog> costsProjectChangeLogs = projectChangeLogMapper.getProjectChangeLog(costsProjectChangeLog);
        if (CollectionUtils.isNotEmpty(costsProjectChangeLogs))
            costsProjectChangeLog = costsProjectChangeLogs.get(0);
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
    public void updateCommit(ProjectChangeLog costsProjectChangeLog, ApprovalModelDto approvalModelDto, ProjectChangeTypeEnum projectChangeTypeEnum) throws Exception {
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
            log.error("提交失败", e);
        }
    }

    //变更审批中
    public boolean isChanging(Integer projectId, ProjectChangeTypeEnum projectChangeTypeEnum) {
        ProjectChangeLog costsProjectChangeLog = new ProjectChangeLog();
        costsProjectChangeLog.setStatus(ProcessStatusEnum.RUN.getValue());
        costsProjectChangeLog.setProjectId(projectId);
        costsProjectChangeLog.setChangeType(projectChangeTypeEnum.getValue());
        List<ProjectChangeLog> costsProjectChangeLogs = projectChangeLogMapper.getProjectChangeLog(costsProjectChangeLog);
        if (CollectionUtils.isNotEmpty(costsProjectChangeLogs)) {
            return false;
        } else {
            return true;
        }
    }

    public ProjectInfoVo getSimpleProjectInfoVo(ProjectInfoChangeVo projectInfoChangeVo) {
        ProjectInfoVo projectInfoVo = new ProjectInfoVo();
        ProjectInfo projectInfo = JSON.parseObject(projectInfoChangeVo.getProjectInfo(), ProjectInfo.class);
        projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfo);
        InitiateConsignor initiateConsignor = JSON.parseObject(projectInfoChangeVo.getConsignor(), InitiateConsignor.class);
        InitiateConsignorVo initiateConsignorVo = consignorService.getInitiateConsignorVo(initiateConsignor);
        InitiatePossessor initiatePossessor = JSON.parseObject(projectInfoChangeVo.getPossessor(), InitiatePossessor.class);
        InitiatePossessorVo initiatePossessorVo = possessorService.getInitiatePossessorVo(initiatePossessor);
        InitiateUnitInformation initiateUnitInformation = JSON.parseObject(projectInfoChangeVo.getUnitInformation(), InitiateUnitInformation.class);
        InitiateUnitInformationVo initiateUnitInformationVo = unitInformationService.getInitiateUnitInformationVo(initiateUnitInformation);

        projectInfoVo.setPossessorVo(initiatePossessorVo);
        projectInfoVo.setConsignorVo(initiateConsignorVo);
        projectInfoVo.setUnitInformationVo(initiateUnitInformationVo);
        return projectInfoVo;
    }

    /**
     * 获取两个对象同名属性内容不相同的列表
     *
     * @param class1 对象1
     * @param class2 对象2
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     */
    public static List<Map<String, Object>> compareTwoClass(Object class1, Object class2) throws ClassNotFoundException, IllegalAccessException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        //获取对象的class
        Class<?> clazz1 = class1.getClass();
        Class<?> clazz2 = class2.getClass();
        //获取对象的属性列表
        Field[] field1 = clazz1.getDeclaredFields();
        Field[] field2 = clazz2.getDeclaredFields();
        //遍历属性列表field1
        for (int i = 0; i < field1.length; i++) {
            //遍历属性列表field2
            for (int j = 0; j < field2.length; j++) {
                //如果field1[i]属性名与field2[j]属性名内容相同
                if (field1[i].getName().equals(field2[j].getName())) {
                    if (field1[i].getName().equals(field2[j].getName())) {
                        field1[i].setAccessible(true);
                        field2[j].setAccessible(true);
                        //如果field1[i]属性值与field2[j]属性值内容不相同
                        if (!compareTwo(field1[i].get(class1), field2[j].get(class2))) {
                            Map<String, Object> map2 = new HashMap<String, Object>();
                            if (field1[i].getName().equals("valuationDate")) {
                                map2.put(field1[i].getName(), field1[i].get(class1) + "-->" + field2[j].get(class2));
                            } else {
                                map2.put(field1[i].getName(), field1[i].get(class1) + "-->" + field2[j].get(class2));
                            }
                            list.add(map2);
                        }
                        break;
                    }
                }
            }
        }
        return list;

    }

    public static boolean compareTwo(Object object1, Object object2) {

        if (object1 == null && object2 == null) {
            return true;
        }
        if (object1 == null && object2 != null) {
            return false;
        }
        if (object1.equals(object2)) {
            return true;
        }
        return false;

    }

    public String getChangeFields(List<Map<String, Object>> maps) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map<String, Object> map : maps) {
            for (Map.Entry<String, Object> item : map.entrySet()) {
                switch (item.getKey()) {
                    case "urgencyName":
                        stringBuilder.append("紧急程度发生改变:" + item.getValue() + "；");
                        break;
                    case "entrustPurposeName":
                        stringBuilder.append("委托目的发生改变:" + item.getValue() + "；");
                        break;
                    case "entrustAimTypeName":
                        stringBuilder.append("委托目的类别发生改变:" + item.getValue() + "；");
                        break;
                    case "remarkEntrustPurpose":
                        stringBuilder.append("委托目的描述发生改变:" + item.getValue() + "；");
                        break;
                    case "valueTypeName":
                        stringBuilder.append("价值类型发生改变:" + item.getValue() + "；");
                        break;
                    case "remarkValueType":
                        stringBuilder.append("价值类型描述发生改变:" + item.getValue() + "；");
                        break;
                    case "departmentName":
                        stringBuilder.append("执业部门发生改变:" + item.getValue() + "；");
                        break;
                    case "propertyScopeName":
                        stringBuilder.append("评估范围发生改变:" + item.getValue() + "；");
                        break;
                    case "scopeInclude":
                        stringBuilder.append("评估包括发生改变:" + item.getValue() + "；");
                        break;
                    case "scopeNotInclude":
                        stringBuilder.append("评估不包括发生改变:" + item.getValue() + "；");
                        break;
                    case "valuationDate":
                        stringBuilder.append("评估基准日发生改变:" + item.getValue() + "；");
                        break;
                    case "loanTypeName":
                        stringBuilder.append("贷款类型发生改变:" + item.getValue() + "；");
                        break;
                    case "contractName":
                        stringBuilder.append("项目合同发生改变:" + item.getValue() + "；");
                        break;
                    case "contractPrice":
                        stringBuilder.append("合同金额发生改变:" + item.getValue() + "；");
                        break;
                    case "remarks":
                        stringBuilder.append("项目说明发生改变:" + item.getValue() + "；");
                        break;
                }

            }
        }
        return stringBuilder.toString();
    }
}
