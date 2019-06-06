package com.copower.pmcc.assess.service.project.change;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectChangeTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectChangeLogDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectMemberDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectChangeLog;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectMember;
import com.copower.pmcc.assess.dto.input.project.MemberChangeDto;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.project.ProjectMemberChangeProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectMemberService;
import com.copower.pmcc.bpm.api.dto.ActivitiTaskNodeDto;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2018/09/06 10:24
 */
@Service
public class ProjectMemberChangeService {
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectChangeLogDao projectChangeLogDao;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private ProjectMemberDao projectMemberDao;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;

    private String buildUserName(String name, String account) {
        return String.format("%s_%s", name, account);
    }


    /**
     * 获取变更申请成员变更信息
     *
     * @param projectId
     * @param processInsId
     * @return
     */
    private ProjectMember getMemberChangeLog(Integer projectId, String processInsId) {
        if(processInsId==null)processInsId = "0";
        ProjectChangeLog costsProjectChangeLog = projectChangeLogDao.getProjectChangeLog(projectId, processInsId, ProjectChangeTypeEnum.MEMBER_CHANGE);

        if (costsProjectChangeLog != null && StringUtils.isNotBlank(costsProjectChangeLog.getNewRecord())) {
            String newRecord = costsProjectChangeLog.getNewRecord();

            return JSON.parseObject(newRecord, ProjectMember.class);
        }

        return null;
    }

    /**
     * 构建项目经理数据
     *
     * @param oldManager
     * @param newManager
     * @param userMap
     * @param memberChangeDto
     */
    private void buildManager(String oldManager, String newManager, Map<String, SysUserDto> userMap, MemberChangeDto memberChangeDto) {
        memberChangeDto.setOriginalManagerAccount(oldManager);
        memberChangeDto.setOriginalManagerName(buildUserName(userMap.get(oldManager).getUserName(), oldManager));

        //如果新经理有则直接获取信息，否则为原始项目经理信息
        if (StringUtils.isNotBlank(newManager)) {
            memberChangeDto.setNewManagerAccount(newManager);
            memberChangeDto.setNewManagerName(buildUserName(userMap.get(newManager).getUserName(), newManager));
        } else {
            memberChangeDto.setNewManagerAccount(oldManager);
            memberChangeDto.setNewManagerName(buildUserName(userMap.get(oldManager).getUserName(), oldManager));
        }
    }

    public ProjectChangeLog getProjectChangeLog(String processInsId) {
        ProjectChangeLog where = new ProjectChangeLog();
        where.setChangeType(ProjectChangeTypeEnum.MEMBER_CHANGE.getValue());
        where.setProcessInsId(processInsId);

        List<ProjectChangeLog> changeLogs = projectChangeLogDao.getProjectChangeLog(where);
        if (CollectionUtils.isNotEmpty(changeLogs)) {
            return changeLogs.get(0);
        }

        return null;
    }


    /**
     * 构建成员列表
     *
     * @param projectId
     * @param processInsId
     * @return
     */
    public List<MemberChangeDto> buildMemberList(Integer projectId, String processInsId) {
        List<MemberChangeDto> memberChangeDtos = Lists.newArrayList();

        //1.成员变更是否有申请信息
        ProjectMember memberChangeLog = getMemberChangeLog(projectId, processInsId);


        //获取项目成员
        ProjectMember projectMember = projectMemberDao.getProjectMemberItem(projectId);

        String oldManager = projectMember.getUserAccountManager(); //原始项目经理
        String newManager = ""; //新的项目经理


        String oldMember = projectMember.getUserAccountMember();  //项目旧成员(数据为逗号分隔的数据)
        String newMember = "";
        if (memberChangeLog != null) {
            newManager = memberChangeLog.getUserAccountManager(); //新经理
            newMember = memberChangeLog.getUserAccountMember(); //新成员
        }

        Set<String> unionMembers = Sets.newHashSet(); //新旧成员集合
        List<String> oldMembers = Lists.newArrayList();
        List<String> newMembers = Lists.newArrayList();
        if (StringUtils.isNotBlank(oldMember)) {
            oldMembers = FormatUtils.transformString2List(oldMember);
            unionMembers.addAll(oldMembers);
        }
        if (StringUtils.isNotBlank(newMember)) {
            newMembers = FormatUtils.transformString2List(newMember);
            unionMembers.addAll(newMembers);
        }


        //获取新旧成员用户信息
        ArrayList<String> allUsers = Lists.newArrayList(unionMembers);
        allUsers.add(oldManager);
        if (StringUtils.isNotBlank(newManager)) {
            allUsers.add(newManager);
        }

        List<SysUserDto> allUserInfo = erpRpcUserService.getSysUserList(allUsers);
        Map<String, SysUserDto> userMap = projectMemberService.relationUserMap(allUserInfo); //map化关系，方便读取


        if (CollectionUtils.isNotEmpty(unionMembers)) {
            for (String account : unionMembers) {
                MemberChangeDto memberChangeDto = new MemberChangeDto();

                buildManager(oldManager, newManager, userMap, memberChangeDto);


                if (oldMembers.contains(account)) {
                    memberChangeDto.setOriginalMemberAccount(account);
                    memberChangeDto.setOriginalMemberName(buildUserName(userMap.get(account).getUserName(), account));
                }

                //检查新成员是否有数据
                if (CollectionUtils.isNotEmpty(newMembers)) {
                    //校验
                    if (newMembers.contains(account)) {
                        memberChangeDto.setNewMemberAccount(account);
                        memberChangeDto.setNewMemberName(buildUserName(userMap.get(account).getUserName(), account));
                    }
                } else {
                    memberChangeDto.setNewMemberAccount(account);
                    memberChangeDto.setNewMemberName(buildUserName(userMap.get(account).getUserName(), account));
                }


                memberChangeDtos.add(memberChangeDto);
            }

        } else {
            //如果没有成员，只构建项目经理到list中
            MemberChangeDto managerMemberChangeDto = new MemberChangeDto();

            buildManager(oldManager, newManager, userMap, managerMemberChangeDto);

            memberChangeDtos.add(managerMemberChangeDto);
        }

        return memberChangeDtos;

    }


    /**
     * 移除项目成员
     *
     * @param projectId
     * @param processInsId
     * @param member
     */
    public void removeMember(Integer projectId, String processInsId, String member) {

        ProjectMember projectMember = projectMemberDao.getProjectMemberItem(projectId);

        String oldRecord = JSON.toJSONString(projectMember);
        String newRecord = oldRecord; //默认为old

        ProjectChangeLog costsProjectChangeLog = projectChangeLogDao.getProjectChangeLog(projectId, processInsId, ProjectChangeTypeEnum.MEMBER_CHANGE);
        if (costsProjectChangeLog == null) {
            costsProjectChangeLog = new ProjectChangeLog();
        } else {
            newRecord = costsProjectChangeLog.getNewRecord();
        }

        costsProjectChangeLog.setOldRecord(oldRecord);

        //处理新成员移除
        ProjectMember newProjectMember = JSON.parseObject(newRecord, ProjectMember.class);
        String newMemberStr = newProjectMember.getUserAccountMember();
        List<String> members = FormatUtils.transformString2List(newMemberStr);
        members.remove(member);
        newProjectMember.setUserAccountMember(FormatUtils.transformListString(members));
        newRecord = JSON.toJSONString(newProjectMember);

        costsProjectChangeLog.setNewRecord(newRecord);

        costsProjectChangeLog.setProcessInsId(processInsId);
        costsProjectChangeLog.setProjectId(projectId);
        costsProjectChangeLog.setChangeType(ProjectChangeTypeEnum.MEMBER_CHANGE.getValue());
        if (costsProjectChangeLog.getId() == null) {
            projectChangeLogDao.addProjectChangeLog(costsProjectChangeLog);
        } else {
            projectChangeLogDao.modifyProjectChangeLog(costsProjectChangeLog);
        }

    }

    /**
     * 添加成员
     *
     * @param projectId
     * @param processInsId
     * @param addMembers
     */
    public void addMember(Integer projectId, String processInsId, String addMembers) {
        ProjectMember projectMember = projectMemberDao.getProjectMemberItem(projectId);

        String oldRecord = JSON.toJSONString(projectMember);
        String newRecord = oldRecord; //默认为old

        ProjectChangeLog costsProjectChangeLog = projectChangeLogDao.getProjectChangeLog(projectId, processInsId, ProjectChangeTypeEnum.MEMBER_CHANGE);
        if (costsProjectChangeLog == null) {
            costsProjectChangeLog = new ProjectChangeLog();
        } else {
            newRecord = costsProjectChangeLog.getNewRecord();
        }

        costsProjectChangeLog.setOldRecord(oldRecord);

        //处理新成员
        ProjectMember newProjectMember = JSON.parseObject(newRecord, ProjectMember.class);
        String newMemberStr = newProjectMember.getUserAccountMember();
        List<String> members = FormatUtils.transformString2List(newMemberStr);
        members = CollectionUtils.isEmpty(members) ? Lists.newArrayList() : members;
        //判断添加的成员是否在成员中
        List<String> addUsers = FormatUtils.transformString2List(addMembers);
        for (String user : addUsers) {
            if (!members.contains(user)) {
                members.add(user);
            }
        }

        newProjectMember.setUserAccountMember(FormatUtils.transformListString(members));
        newRecord = JSON.toJSONString(newProjectMember);

        costsProjectChangeLog.setNewRecord(newRecord);

        costsProjectChangeLog.setProcessInsId(processInsId);
        costsProjectChangeLog.setProjectId(projectId);
        costsProjectChangeLog.setChangeType(ProjectChangeTypeEnum.MEMBER_CHANGE.getValue());
        if (costsProjectChangeLog.getId() == null) {
            projectChangeLogDao.addProjectChangeLog(costsProjectChangeLog);
        } else {
            projectChangeLogDao.modifyProjectChangeLog(costsProjectChangeLog);
        }
    }

    /**
     * 替换项目成员
     *
     * @param projectId
     * @param processInsId
     * @param oldMember
     * @param newMember
     */
    public void replaceMember(Integer projectId, String processInsId, String oldMember, String newMember) {
        ProjectMember projectMember = projectMemberDao.getProjectMemberItem(projectId);

        String oldRecord = JSON.toJSONString(projectMember);
        String newRecord = oldRecord; //默认为old

        ProjectChangeLog costsProjectChangeLog = projectChangeLogDao.getProjectChangeLog(projectId, processInsId, ProjectChangeTypeEnum.MEMBER_CHANGE);
        if (costsProjectChangeLog == null) {
            costsProjectChangeLog = new ProjectChangeLog();
        } else {
            newRecord = costsProjectChangeLog.getNewRecord();
        }

        costsProjectChangeLog.setOldRecord(oldRecord);

        //处理新成员
        ProjectMember newProjectMember = JSON.parseObject(newRecord, ProjectMember.class);
        String newMemberStr = newProjectMember.getUserAccountMember();
        List<String> members = FormatUtils.transformString2List(newMemberStr);

        //替换成员
        if (members.contains(oldMember)) {
            members.remove(oldMember);  //移除旧成员
        }
        members.add(newMember); //添加新成员

        newProjectMember.setUserAccountMember(FormatUtils.transformListString(members));
        newRecord = JSON.toJSONString(newProjectMember);

        costsProjectChangeLog.setNewRecord(newRecord);

        costsProjectChangeLog.setProcessInsId(processInsId);
        costsProjectChangeLog.setProjectId(projectId);
        costsProjectChangeLog.setChangeType(ProjectChangeTypeEnum.MEMBER_CHANGE.getValue());
        if (costsProjectChangeLog.getId() == null) {
            projectChangeLogDao.addProjectChangeLog(costsProjectChangeLog);
        } else {
            projectChangeLogDao.modifyProjectChangeLog(costsProjectChangeLog);
        }
    }


    /**
     * 替换项目经理
     *
     * @param projectId
     * @param processInsId
     * @param newManage
     */
    public void replaceManage(Integer projectId, String processInsId, String newManage) {
        ProjectMember projectMember = projectMemberDao.getProjectMemberItem(projectId);

        String oldRecord = JSON.toJSONString(projectMember);

        ProjectChangeLog costsProjectChangeLog = projectChangeLogDao.getProjectChangeLog(projectId, processInsId, ProjectChangeTypeEnum.MEMBER_CHANGE);
        if (costsProjectChangeLog == null) {
            costsProjectChangeLog = new ProjectChangeLog();
        }
        costsProjectChangeLog.setOldRecord(oldRecord);

        //替换项目经理
        projectMember.setUserAccountManager(newManage);
        String newRecord = JSON.toJSONString(projectMember);
        costsProjectChangeLog.setNewRecord(newRecord);
        //end

        costsProjectChangeLog.setProcessInsId(processInsId);
        costsProjectChangeLog.setProjectId(projectId);
        costsProjectChangeLog.setChangeType(ProjectChangeTypeEnum.MEMBER_CHANGE.getValue());
        if (costsProjectChangeLog.getId() == null) {
            projectChangeLogDao.addProjectChangeLog(costsProjectChangeLog);
        } else {
            projectChangeLogDao.modifyProjectChangeLog(costsProjectChangeLog);
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public void apply(Integer projectId, String processInsId, String changeReason) throws BusinessException {
        String currentUser = commonService.thisUserAccount();
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);

        ProjectChangeLog costsProjectChangeLog = projectChangeLogDao.getProjectChangeLog(projectId, processInsId, ProjectChangeTypeEnum.MEMBER_CHANGE);
        if (costsProjectChangeLog == null) {
            costsProjectChangeLog = new ProjectChangeLog();
        }
        costsProjectChangeLog.setProcessInsId(processInsId);
        costsProjectChangeLog.setProjectId(projectId);
        costsProjectChangeLog.setChangeType(ProjectChangeTypeEnum.MEMBER_CHANGE.getValue());
        costsProjectChangeLog.setChangeReason(changeReason);
        costsProjectChangeLog.setCreator(currentUser);
        if (costsProjectChangeLog.getId() == null) {
            projectChangeLogDao.addProjectChangeLog(costsProjectChangeLog);
        } else {
            projectChangeLogDao.modifyProjectChangeLog(costsProjectChangeLog);
        }

        //发流程
        String boxKey = baseParameterService.getBaseParameter(BaseParameterEnum.PROJECT_MEMBER_CHANGE_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxKey);

        String folio = String.format("【成员变更审批】%s", projectInfo.getProjectName());

        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setProjectId(projectId);
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setFolio(folio);//流程描述
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectChangeLog.class));
        processInfo.setTableId(costsProjectChangeLog.getId());
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setProcessEventExecutor(ProjectMemberChangeProcessEvent.class);
        processInfo.setStartUser(currentUser);


        ProcessUserDto processUserDto = new ProcessUserDto();

        try {
            processUserDto = processControllerComponent.processStart(processInfo, currentUser, false);

        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }


        costsProjectChangeLog.setProcessInsId(processUserDto.getProcessInsId());
        costsProjectChangeLog.setStatus(ProcessStatusEnum.RUN.getValue());
        projectChangeLogDao.modifyProjectChangeLog(costsProjectChangeLog);
    }


    public void editCommit(Integer projectId, String changeReason, ApprovalModelDto approvalModelDto) throws BpmException {
        ProjectChangeLog costsProjectChangeLog = projectChangeLogDao.getProjectChangeLog(projectId, approvalModelDto.getProcessInsId(), ProjectChangeTypeEnum.MEMBER_CHANGE);
        costsProjectChangeLog.setChangeReason(changeReason);
        projectChangeLogDao.modifyProjectChangeLog(costsProjectChangeLog);

        processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
    }

    /**
     * 检查用户任务个数
     *
     * @throws BusinessException
     */
    public void checkUserTask(Integer projectId, String oldMember) throws Exception {
        SysUserDto sysUser = erpRpcUserService.getSysUser(oldMember);
        //被更改人在当前项目是否有未提交任务
        ProjectResponsibilityDto projectResponsibilityDto = new ProjectResponsibilityDto();
        projectResponsibilityDto.setProjectId(projectId);
        projectResponsibilityDto.setUserAccount(oldMember);
        projectResponsibilityDto.setAppKey(applicationConstant.getAppKey());
        List<ProjectResponsibilityDto> taskList = bpmRpcProjectTaskService.getProjectTaskList(projectResponsibilityDto);

        if (taskList.size() > 0) {
            throw new BusinessException(String.format("成员【%s】还有%s个任务未处理，不能对其变更", sysUser.getUserName(), taskList.size()));
        }
        ProjectInfo info = projectInfoService.getProjectInfoById(projectId);
        List<ActivitiTaskNodeDto> ActivitiTaskList= bpmRpcActivitiProcessManageService.queryProcessCurrentTask(info.getProcessInsId());
        if(CollectionUtils.isNotEmpty(ActivitiTaskList)){
            ActivitiTaskNodeDto activitiTaskNodeDto = ActivitiTaskList.get(0);
            List<String> users = activitiTaskNodeDto.getUsers();
            if(users.contains(oldMember)){
                throw new BusinessException("成员存在未审批任务，不能对其变更");
            }
        }
    }
}
