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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2017/9/19
 * @time: 11:57
 */
@Service
public class ProjectCenterService {
    @Autowired
    private ProjectPlanDao projectPlanDao;
    @Autowired
    private ProjectInfoDao projectInfoDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CsrProjectInfoService csrProjectInfoService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private ErpRpcDepartmentService erpRpcDepartmentService;

    /**
     * 获取项目列表
     *
     * @return
     */
    public BootstrapTableVo getProjectList(String projectName, String projectStatus) {

        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        List<Integer> orgIds = null;
        if (!processControllerComponent.userIsAdmin(processControllerComponent.getThisUser())) {
            //如果是超级管理员，跳过权限过滤
            //根据当前登录人所处部门确定，只能查看当前部门及下级部门的项目数据
            Integer currDepartmentId = processControllerComponent.getThisUserInfo().getDepartmentId();
            orgIds = Lists.newArrayList(currDepartmentId);
            List<Integer> departmentChildenIds = erpRpcDepartmentService.getDepartmentChildenIds(currDepartmentId);
            orgIds.addAll(departmentChildenIds);
        }
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ProjectInfo> projectInfoList = projectInfoDao.getProjectListByOrgIds(orgIds, projectName, projectStatus);
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
        if (CollectionUtils.isNotEmpty(projectInfoList)) {
            for (ProjectInfo item : projectInfoList) {
                projectInfoVo = new ProjectInfoVo();
                BeanUtils.copyProperties(item, projectInfoVo);
                projectInfoVo.setId(item.getId());
                projectInfoVo.setProjectName(item.getProjectName());
                ProjectMemberVo projectMember = projectMemberService.getProjectMember(item.getId());
                projectInfoVo.setUserAccountManagerName(projectMember.getUserAccountManagerName());
                projectInfoVo.setUserAccountMemberName(projectMember.getUserAccountMemberName());
                projectInfoVo.setProjectClassName(baseProjectClassifyService.getNameById(item.getProjectClassId()));
                projectInfoVo.setProjectTypeName(baseProjectClassifyService.getNameById(item.getProjectTypeId()));
                projectInfoVo.setProjectCategoryName(baseProjectClassifyService.getNameById(item.getProjectCategoryId()));
                projectInfoVo.setProjectStatus(ProjectStatusEnum.getNameByKey(item.getProjectStatus()));

                List<ProjectPlan> filter = LangUtils.filter(projectPlans, o -> {
                    return o.getProjectId().equals(item.getId());
                });
                if (CollectionUtils.isNotEmpty(filter)) {
                    int finishCount = 0;
                    double finishWeight = 0L;
                    double totalWeight = 0L;
                    for (ProjectPlan plan : filter) {
                        if (plan.getProjectStatus().equals(ProjectStatusEnum.FINISH.getKey())) {
                            finishCount++;
                            finishWeight += plan.getSpecificGravity();
                        }
                        totalWeight += plan.getSpecificGravity();
                    }
                    if (totalWeight <= 0) {
                        projectInfoVo.setFinishPre(finishCount/filter.size() * 100);
                    } else {
                        projectInfoVo.setFinishPre((int)(finishWeight/totalWeight * 100));
                    }
                } else {
                    projectInfoVo.setFinishPre(0);
                }
                projectInfoVos.add(projectInfoVo);
            }
        }
        return projectInfoVos;
    }

    public BootstrapTableVo csrProjectInfoList(String name) {
        return csrProjectInfoService.csrProjectInfoList(name);
    }


    /**
     * 获取我的立项
     *
     * @return
     */
    public BootstrapTableVo getMyProjectList(String queryName, String projectStatus) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setCreator(processControllerComponent.getThisUser());
        projectInfo.setProjectName(queryName);
        projectInfo.setProjectStatus(projectStatus);
        List<ProjectInfo> myProjectList = projectInfoDao.getMyProjectList(projectInfo);
        List<ProjectInfoVo> projectInfoVos = getProjectInfoVos(myProjectList);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(projectInfoVos) ? new ArrayList<ProjectInfoVo>(10) : projectInfoVos);
        return vo;
    }

    /**
     * 获取我的参与项目
     *
     * @return
     */
    public BootstrapTableVo getParticipationProject(String projectName, String projectStatus) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ProjectInfo> list = projectInfoDao.getProjectListByUserAccount(processControllerComponent.getThisUser(), projectName, projectStatus);
        List<ProjectInfoVo> projectInfoVos = getProjectInfoVos(list);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(projectInfoVos) ? Lists.newArrayList() : projectInfoVos);
        return vo;
    }
}
