package com.copower.pmcc.assess.service.project;


import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectInfoDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectMemberDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectProgressVo;
import com.copower.pmcc.assess.dto.output.project.ProjectProgressWorkStageVo;
import com.copower.pmcc.assess.service.csr.CsrProjectInfoService;
import com.copower.pmcc.assess.service.project.manage.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
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
    private ProjectInfoService projectInfoService;
    @Autowired
    private CsrProjectInfoService csrProjectInfoService;

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

    public List<ProjectProgressVo> getProjectProgressVOS(List<ProjectInfo> projectInfoList, List<Integer> projectIds) {
        List<ProjectProgressVo> projectProgressVOS = new ArrayList<>();//返回值列表
        if (CollectionUtils.isEmpty(projectInfoList)) {
            return projectProgressVOS;
        }
        List<ProjectPlan> projectPlans = projectPlanDao.getProjectplanByProjectIds(projectIds);
        ProjectProgressVo projectProgressVO;
        for (ProjectInfo item : projectInfoList) {
            projectProgressVO = new ProjectProgressVo();
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

    /**
     * 获取项目列表
     * @return
     */
    public BootstrapTableVo getProjectList() {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        if (StringUtils.isNotBlank(requestBaseParam.getSort())) {
            page.setOrderBy(FormatUtils.camelToUnderline(requestBaseParam.getSort()) + " " + requestBaseParam.getOrder());
        }
        String projectName = requestBaseParam.getSearch();
        List<ProjectInfo> projectInfoList = projectInfoDao.getProjectInfoList("", projectName);
        List<ProjectInfoVo> projectInfoVos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(projectInfoList)) {
            projectInfoVos = LangUtils.transform(projectInfoList, p -> projectInfoService.getSimpleProjectInfoVo(p));
        }
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(projectInfoVos);
        return bootstrapTableVo;
    }

    public List<ProjectInfo> getProjectListByMonth(String dates, String datee) {
        List<ProjectInfo> projectInfos = projectInfoDao.getProjectInfoListByCompleteDatePlan(DateUtils.parse(dates), DateUtils.parse(datee), "");
        return projectInfos;
    }

    public BootstrapTableVo csrProjectInfoList(String name) {
        return csrProjectInfoService.csrProjectInfoList(name);
    }

}
