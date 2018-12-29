package com.copower.pmcc.assess.service.project;


import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectInfoDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectMemberDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectMemberVo;
import com.copower.pmcc.assess.dto.output.project.ProjectProgressVo;
import com.copower.pmcc.assess.dto.output.project.ProjectProgressWorkStageVo;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.csr.CsrProjectInfoService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcToolsService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.ErpUserDataRoleEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/19
 * @time: 11:57
 */
@Service
public class ProjectCenterService {
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private ProjectPlanDao projectPlanDao;
    @Autowired
    private ProjectInfoDao projectInfoDao;
    @Autowired
    private ProjectMemberDao projectMemberDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private CsrProjectInfoService csrProjectInfoService;
    @Autowired
    private BpmRpcToolsService bpmRpcToolsService;
    @Autowired
    private ErpRpcDepartmentService erpRpcDepartmentService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    public Integer getTodayTaskCount() {
        Date date = new Date();
        Date dates = DateUtils.parse(DateUtils.format(date, DateUtils.DATE_PATTERN));
        Date datee = DateUtils.parse(DateUtils.format(date, DateUtils.DATE_PATTERN));
        String users = processControllerComponent.getThisUser();
        if (processControllerComponent.userIsAdmin(users)) {
            users = "";
        }
        return projectPlanDetailsDao.getProjectDetailsCount(users, dates, datee);
    }

    public Integer getTheWeekTaskCount() {
        Date date = new Date();
        Date dates = DateUtils.parse(DateUtils.format(date, DateUtils.DATE_PATTERN));
        Date datee = DateUtils.parse(DateUtils.format(DateUtils.addDay(date, 7), DateUtils.DATE_PATTERN));
        String users = processControllerComponent.getThisUser();
        if (processControllerComponent.userIsAdmin(users)) {
            users = "";
        }
        return projectPlanDetailsDao.getProjectDetailsCount(users, dates, datee);
    }

    public Integer getUserJoinProjectCount() {
        List<ProjectInfo> projectInfoList = projectInfoDao.getProjectInfoList(ProcessStatusEnum.RUN.getValue(), "");
        if (CollectionUtils.isNotEmpty(projectInfoList)) {
            List<Integer> projectIds = LangUtils.transform(projectInfoList, projectInfo -> projectInfo.getId());
            String users = String.format("%%%s%%", processControllerComponent.getThisUser());
            if (processControllerComponent.userIsAdmin(users)) {
                users = String.format("%%%%", "");
            }
            return projectMemberDao.getUserAccountProjectCount(users, projectIds);
        } else {
            return 0;
        }
    }

    public BootstrapTableVo getProjectProgressVo() {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ProjectInfo> projectInfoList = projectInfoDao.getProjectInfoList(ProcessStatusEnum.RUN.getValue(), requestBaseParam.getSearch());
        List<Integer> projectIds = LangUtils.transform(projectInfoList, projectInfo -> projectInfo.getId());//取得项目的项目编号列表
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setRows(getProjectProgressVOS(projectInfoList, projectIds));
        bootstrapTableVo.setTotal(page.getTotal());
        return bootstrapTableVo;
    }

    public List<ProjectProgressVo> getProjectProgressVOS(List<ProjectInfo> projectInfoList, List<Integer> projectIds) {
        List<ProjectProgressVo> projectProgressVos = new ArrayList<>();//返回值列表
        if (CollectionUtils.isEmpty(projectInfoList)) {
            return projectProgressVos;
        }
        List<ProjectPlan> projectPlans = projectPlanDao.getProjectplanByProjectIds(projectIds);
        ProjectProgressVo projectProgressVo;
        for (ProjectInfo item : projectInfoList) {
            projectProgressVo = new ProjectProgressVo();
            projectProgressVo.setProjectId(item.getId());
            projectProgressVo.setProjectName(item.getProjectName());
            projectProgressVo.setProcessInsId(item.getProcessInsId());
            projectProgressVo.setProjectStatus(item.getProjectStatus());
            projectProgressVo.setProjectStatusFlog(item.getProjectStatus().equals(ProjectStatusEnum.NORMAL.getName()) ? "1" : "0");

            List<ProjectPlan> planFilter = LangUtils.filter(projectPlans, input -> {
                if (input.getProjectId().equals(item.getId()) && input.getBisRestart() == false) {
                    return true;
                } else {
                    return false;
                }
            });

            List<ProjectProgressWorkStageVo> projectProgressWorkStageVos = new ArrayList<>();
            ProjectProgressWorkStageVo projectProgressWorkStageVo;
            for (ProjectPlan p : planFilter) {
                projectProgressWorkStageVo = new ProjectProgressWorkStageVo();
                ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(p.getWorkStageId());
                if (projectWorkStage != null) {
                    projectProgressWorkStageVo.setWorkStageName(projectWorkStage.getWorkStageName());
                }

                ProjectStatusEnum enumByName = ProjectStatusEnum.getEnumByName(p.getProjectStatus());
                if (enumByName != null) {
                    switch (enumByName) {
                        case FINISH:
                        case CLOSE: {
                            projectProgressWorkStageVo.setWorkStageStatus(1);
                            break;
                        }
                        case WAIT: {
                            projectProgressWorkStageVo.setWorkStageStatus(0);
                            break;
                        }
                        default: {
                            projectProgressWorkStageVo.setWorkStageStatus(2);
                            break;
                        }
                    }
                }

                if (p.getCreator() != null) {
                    SysUserDto sysUser = erpRpcUserService.getSysUser(p.getCreator());
                    projectProgressWorkStageVo.setStageUserName(sysUser.getUserName());
                }
                projectProgressWorkStageVo.setStagePlanEndDate(p.getFinishDate() == null ? "" : DateUtils.formatDate(p.getFinishDate(), "MM-dd"));
                projectProgressWorkStageVos.add(projectProgressWorkStageVo);
            }
            projectProgressVo.setProjectProgressWorkStageVos(projectProgressWorkStageVos);
            projectProgressVos.add(projectProgressVo);
        }
        return projectProgressVos;
    }

    /**
     * 获取项目列表
     * @return
     */
    public BootstrapTableVo getProjectList() {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ProjectInfo queryParam=new ProjectInfo();
        queryParam.setProjectName(requestBaseParam.getSearch());
        List<ProjectInfo> projectInfoList = Lists.newArrayList();
        //如果是超级管理员，跳过权限过滤
        if(!processControllerComponent.userIsAdmin(processControllerComponent.getThisUser())){
            //获取当前登陆人的角色
            KeyValueDto keyValueDto = bpmRpcToolsService.getUserAccountDataRole(processControllerComponent.getThisUser());
            ErpUserDataRoleEnum erpUserDataRoleEnum = ErpUserDataRoleEnum.create(keyValueDto.getKey());
            switch (erpUserDataRoleEnum) {
                case CREATOR: { //表示普通项目成员
                    //1、先查询menber表获取参与的项目id
                    ProjectMember projectMember = new ProjectMember();
                    projectMember.setUserAccountMember(processControllerComponent.getThisUser());
                    List<ProjectMember> projectMembers = projectMemberDao.getProjectMemberList(projectMember);
                    List<Integer> projectIds = new ArrayList<Integer>();
                    if(CollectionUtils.isNotEmpty(projectMembers)){
                        for(ProjectMember obj : projectMembers){
                            projectIds.add(obj.getProjectId());
                        }
                    }
                    //2、再根据项目id获取项目信息列表
                    projectInfoList = projectInfoDao.getProjectInfoList(queryParam, projectIds);
                    break;
                }
                case DEPARTMENT: { //表示部门领导
                    //取得部门下的所有子部门
                    List<String> ids = FormatUtils.transformString2List(keyValueDto.getValue());
                    List<String> departmentIds = FormatUtils.transformString2List(keyValueDto.getValue());
                    List<Integer> conditions = new ArrayList<>();
                    for (String item : ids) {
                        List<Integer> departmentChildenIds = erpRpcDepartmentService.getDepartmentChildenIds(Integer.valueOf(item));
                        if (CollectionUtils.isNotEmpty(departmentChildenIds)) {
                            for (Integer i : departmentChildenIds) {
                                departmentIds.add(String.valueOf(i));
                            }
                        }
                    }
                    if(CollectionUtils.isNotEmpty(departmentIds)){
                        for (String item : departmentIds){
                            conditions.add(Integer.valueOf(item));
                        }
                    }
                    projectInfoList = projectInfoDao.getProjectInfoList(queryParam, conditions);
                    break;
                }
                case USERACCOUNTS: {
                    //1、先查询menber表获取参与的项目id
                    ProjectMember projectMember = new ProjectMember();
                    projectMember.setUserAccountMember(processControllerComponent.getThisUser());
                    List<ProjectMember> projectMembers = projectMemberDao.getProjectMemberList(projectMember);
                    List<Integer> projectIds = new ArrayList<Integer>();
                    if(CollectionUtils.isNotEmpty(projectMembers)){
                        for(ProjectMember obj : projectMembers){
                            projectIds.add(obj.getProjectId());
                        }
                    }
                    //2、再根据项目id获取项目信息列表
                    projectInfoList = projectInfoDao.getProjectInfoList(queryParam, projectIds);
                    break;
                }
            }
        }else{
            projectInfoList = projectInfoDao.getProjectInfoList(queryParam,null);
        }
        List<ProjectInfoVo> projectInfoVos = getProjectInfoVos(projectInfoList);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(projectInfoVos);
        return bootstrapTableVo;
    }

    public List<ProjectInfoVo> getProjectInfoVos(List<ProjectInfo> projectInfoList) {
        List<Integer> projectIds = LangUtils.transform(projectInfoList, o -> o.getId());
        List<ProjectPlan> projectPlans = projectPlanDao.getProjectPlanByStatus(projectIds, null);

        List<ProjectInfoVo> projectInfoVos = new ArrayList<>();
        ProjectInfoVo projectInfoVo = null;
        String currUser = processControllerComponent.getThisUser();
        //TASK(0, "责任人提交工作成果"), PLAN(1, "部门负责人细分计划"), ALLTASK(2, "整体复核工作成果"), NEWPLAN(3, "细分下级计划责任人");
        if (CollectionUtils.isNotEmpty(projectInfoList)) {
            for (ProjectInfo item : projectInfoList) {
                projectInfoVo = new ProjectInfoVo();
                BeanUtils.copyProperties(item, projectInfoVo);
                projectInfoVo.setId(item.getId());
                projectInfoVo.setProjectName(item.getProjectName());
                ProjectMemberVo projectMember = projectMemberService.getProjectMember(item.getId());
                projectInfoVo.setUserAccountManagerName(projectMember.getUserAccountManagerName());
                projectInfoVo.setUserAccountMemberName(projectMember.getUserAccountMemberName());

                //项目类型
                if (item.getProjectClassId() != null) {
                    BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyById(item.getProjectClassId());
                    if (projectClassify != null) {
                        projectInfoVo.setProjectClassName(projectClassify.getName());
                    }
                }
                //项目类别
                if (item.getProjectTypeId() != null) {
                    BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyById(item.getProjectTypeId());
                    if (projectClassify != null) {
                        projectInfoVo.setProjectTypeName(projectClassify.getName());
                    }
                }
                //项目范围
                if (item.getProjectCategoryId() != null) {
                    BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyById(item.getProjectCategoryId());
                    if (projectClassify != null) {
                        projectInfoVo.setProjectCategoryName(projectClassify.getName());
                    }
                }

                List<ProjectPlan> filter = LangUtils.filter(projectPlans, o -> {
                    return o.getProjectId().equals(item.getId());
                });
                if (CollectionUtils.isNotEmpty(filter)) {
                    double finishCount = 0;
                    double rungingCount = 0;
                    for (ProjectPlan plan : filter) {
                        if (plan.getProjectStatus().equals(ProjectStatusEnum.FINISH.getName())) {
                            finishCount += plan.getSpecificGravity();
                        } else {
                            rungingCount += plan.getSpecificGravity();
                        }
                    }
                    if (finishCount + rungingCount == 0)//如果没有设置权重，则设置未完成数量为1 ，以避免除法分母为0的情况
                    {
                        rungingCount = 1;
                    }
                    double v = finishCount / (finishCount + rungingCount);
                    projectInfoVo.setFinishPre((int) (v * 100));

                } else {
                    projectInfoVo.setFinishPre(0);
                }
                projectInfoVos.add(projectInfoVo);
            }
        }
        return projectInfoVos;
    }



    public List<ProjectInfo> getProjectListByMonth(String dates, String datee) {
        List<ProjectInfo> projectInfos = projectInfoDao.getProjectInfoListByCompleteDatePlan(DateUtils.parse(dates), DateUtils.parse(datee), "");
        return projectInfos;
    }

    public BootstrapTableVo csrProjectInfoList(String name) {
        return csrProjectInfoService.csrProjectInfoList(name);
    }


    /**
     * 获取我的项目
     * @return
     */
    public BootstrapTableVo getMyProjectList(String queryName, String projectStatus) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ProjectInfo projectInfo=new ProjectInfo();
        projectInfo.setCreator(processControllerComponent.getThisUser());
        projectInfo.setProjectName(queryName);
        if("--请选择--".equals(projectStatus)){
            projectStatus = ProjectStatusEnum.NORMAL.getName();
        }
        projectInfo.setProjectStatus(projectStatus);
        List<ProjectInfo> myProjectList = projectInfoDao.getMyProjectList(projectInfo);
        List<ProjectInfoVo> projectInfoVos = getProjectInfoVos(myProjectList);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(projectInfoVos) ? new ArrayList<ProjectInfoVo>(10) : projectInfoVos);
        return vo;
    }
}
