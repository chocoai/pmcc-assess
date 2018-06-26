package com.copower.pmcc.assess.service.project;


import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.dao.project.ProjectInfoDao;
import com.copower.pmcc.assess.dal.dao.project.ProjectMemberDao;
import com.copower.pmcc.assess.dal.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dal.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectProgressVO;
import com.copower.pmcc.assess.dto.output.project.ProjectProgressWorkStageVo;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.csr.CsrProjectInfoService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private CsrProjectInfoService csrProjectInfoService;
    @Autowired
    private BaseProjectClassifyService projectClassifyService;

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

    public BootstrapTableVo getProjectProgressVO() {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ProjectInfo> projectInfoList = projectInfoDao.getProjectInfoList(ProcessStatusEnum.RUN.getValue(), requestBaseParam.getSearch());

        List<Integer> projectIds = LangUtils.transform(projectInfoList, projectInfo -> projectInfo.getId());//取得项目的项目编号列表
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setRows(getProjectProgressVOS(projectInfoList, projectIds));
        bootstrapTableVo.setTotal(page.getTotal());
        return bootstrapTableVo;
    }

    public List<ProjectProgressVO> getProjectProgressVOS(List<ProjectInfo> projectInfoList, List<Integer> projectIds) {
        List<ProjectProgressVO> projectProgressVOS = new ArrayList<>();//返回值列表
        if (CollectionUtils.isEmpty(projectInfoList)) {
            return projectProgressVOS;
        }
        List<ProjectPlan> projectPlans = projectPlanDao.getProjectplanByProjectIds(projectIds);
        ProjectProgressVO projectProgressVO;
        for (ProjectInfo item : projectInfoList) {
            projectProgressVO = new ProjectProgressVO();
            projectProgressVO.setProjectId(item.getId());
            projectProgressVO.setProjectName(item.getProjectName());
            projectProgressVO.setProcessInsId(item.getProcessInsId());
            projectProgressVO.setProjectStatus(item.getProjectStatus());
            projectProgressVO.setProjectStatusFlog(item.getProjectStatus().equals(ProjectStatusEnum.NORMAL.getName()) ? "1" : "0");

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
            projectProgressVO.setProjectProgressWorkStageVos(projectProgressWorkStageVos);
            projectProgressVOS.add(projectProgressVO);
        }
        return projectProgressVOS;
    }

    public BootstrapTableVo getProjectList() {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        if (StringUtils.isNotBlank(requestBaseParam.getSort())) {
            page.setOrderBy(FormatUtils.camelToUnderline(requestBaseParam.getSort()) + " " + requestBaseParam.getOrder());
        }
        String projectName = requestBaseParam.getSearch();
        //todo直接取数据信息，没有别的东西了
        List<ProjectInfo> projectInfoList = projectInfoDao.getProjectInfoList("", projectName);

        List<Integer> projectIds = LangUtils.transform(projectInfoList, o -> o.getId());
        List<ProjectPlan> projectPlans = projectPlanDao.getProjectPlanByStatus(projectIds, ProjectStatusEnum.PLAN.getName());
        //项目所有工作状态
        List<ProjectResponsibilityDto> projectPlanResponsibilityList = bpmRpcProjectTaskService.getProjectTaskList(projectIds);
        List<ProjectInfoVo> projectInfoVos = new ArrayList<>();
        ProjectInfoVo projectInfoVo = null;
        String currUser = processControllerComponent.getThisUser();
        //TASK(0, "责任人提交工作成果"), PLAN(1, "部门负责人细分计划"), ALLTASK(2, "整体复核工作成果"), NEWPLAN(3, "细分下级计划责任人");
        if (CollectionUtils.isNotEmpty(projectInfoList)) {
            for (ProjectInfo item : projectInfoList) {
                projectInfoVo = new ProjectInfoVo();
                BeanUtils.copyProperties(item,projectInfoVo);
                projectInfoVo.setId(item.getId());
                projectInfoVo.setProjectName(item.getProjectName());
                BaseProjectClassify baseProjectClassify = projectClassifyService.getProjectInfoByClassify(projectInfoVo);
                projectInfoVo.setBaseProjectClassify(baseProjectClassify);

//                BidProjectCategory bidProjectCategory = bidProjectCategoryService.getBidProjectCategoryById(item.getProjectCategoryId());
//                if(bidProjectCategory!=null){
//                    projectInfoVo.setProjectCategoryName(bidProjectCategory.getName());
//                }

                //取得成果工作项
                List<ProjectResponsibilityDto> projectPlanResponsibilityTask = LangUtils.filter(projectPlanResponsibilityList, p -> {
                    return p.getModel() == 0 && p.getUserAccount().equals(currUser) && p.getProjectId() == item.getId();
                });
                if (CollectionUtils.isNotEmpty(projectPlanResponsibilityTask)) {
                    projectInfoVo.setTaskWorkStages(projectPlanResponsibilityTask);
                }
                //取得整体复核成果工作项
                List<ProjectResponsibilityDto> projectPlanResponsibilityAllTask = LangUtils.filter(projectPlanResponsibilityList, p -> {
                    return p.getModel() == 2 && p.getUserAccount().equals(currUser) && p.getProjectId() == item.getId();
                });
                if (CollectionUtils.isNotEmpty(projectPlanResponsibilityAllTask)) {
                    projectInfoVo.setTaskAllWorkStages(projectPlanResponsibilityAllTask);
                }
                // 查看计划任务工作项
                List<ProjectResponsibilityDto> projectPlanResponsibilityPlan = LangUtils.filter(projectPlanResponsibilityList, p -> {
                    return p.getModel() == 3 && p.getUserAccount().equals(currUser) && p.getProjectId() == item.getId();
                });
                if (CollectionUtils.isEmpty(projectPlanResponsibilityPlan)) {
                    projectPlanResponsibilityPlan = new ArrayList<>();
                }
                //取项
                List<ProjectResponsibilityDto> filter1 = LangUtils.filter(projectPlanResponsibilityList, p -> {
                    return p.getModel() == 1 && p.getUserAccount().equals(currUser) && p.getProjectId() == item.getId();
                });
                if (CollectionUtils.isNotEmpty(filter1)) {
                    for (ProjectResponsibilityDto planItem : filter1) {
                        projectPlanResponsibilityPlan.add(planItem);
                    }
                }
                projectInfoVo.setPlanWorkStages(projectPlanResponsibilityPlan);
                projectInfoVos.add(projectInfoVo);
            }
        }

        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(projectInfoVos);
        return bootstrapTableVo;
    }

    public List<ProjectInfo> getProjectListByMonth(String dates, String datee) {
        List<ProjectInfo> projectInfos = projectInfoDao.getProjectInfoListByCompleteDatePlan(DateUtils.parse(dates), DateUtils.parse(datee), "");
        return projectInfos;
    }

    public BootstrapTableVo csrProjectInfoListA(String name){
        return csrProjectInfoService.csrProjectInfoListA(name);
    }

}
