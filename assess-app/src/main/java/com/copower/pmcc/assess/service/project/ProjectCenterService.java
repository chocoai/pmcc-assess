package com.copower.pmcc.assess.service.project;


import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectInfoDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dto.input.project.QueryProjectInfo;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectMemberVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.initiate.InitiateUnitInformationService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
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
    private BaseService baseService;
    @Autowired
    private ProjectPlanDao projectPlanDao;
    @Autowired
    private ProjectInfoDao projectInfoDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private InitiateUnitInformationService initiateUnitInformationService;
    @Autowired
    private ErpRpcDepartmentService erpRpcDepartmentService;

    /**
     * 获取项目列表
     *
     * @return
     */
    public BootstrapTableVo getProjectList(QueryProjectInfo queryName) throws Exception {

        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        queryName.setUserAccount(null);
        List<ProjectInfo> projectInfoList = projectInfoDao.getProjectListByUserAccount(queryName);
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
                try {
                    projectInfoVo.setId(item.getId());
                    projectInfoVo.setProjectName(item.getProjectName());
                    ProjectMemberVo projectMember = projectMemberService.getProjectMember(item.getId());
                    if (projectMember != null) {
                        projectInfoVo.setUserAccountManagerName(projectMember.getUserAccountManagerName());
                        projectInfoVo.setUserAccountMemberName(projectMember.getUserAccountMemberName());
                    }
                    InitiateUnitInformationVo initiateUnitInformation = initiateUnitInformationService.getDataByProjectId(item.getId());
                    if (initiateUnitInformation != null) {
                        projectInfoVo.setUseUnitName(initiateUnitInformation.getuUseUnitName());
                    }
                    if (item.getDepartmentId() != null) {
                        projectInfoVo.setDepartmentName(erpRpcDepartmentService.getDepartmentById(item.getDepartmentId()).getName());
                    }
                    projectInfoVo.setProjectClassName(baseProjectClassifyService.getNameById(item.getProjectClassId()));
                    projectInfoVo.setProjectTypeName(baseProjectClassifyService.getNameById(item.getProjectTypeId()));
                    projectInfoVo.setEntrustPurposeName(baseDataDicService.getNameById(item.getEntrustPurpose()));
                    projectInfoVo.setLoanTypeName(baseDataDicService.getNameById(item.getLoanType()));
                    projectInfoVo.setProjectCategoryName(baseProjectClassifyService.getNameById(item.getProjectCategoryId()));
                    if (item.getProjectStatus() != null)
                        projectInfoVo.setProjectStatus(ProjectStatusEnum.getNameByKey(item.getProjectStatus()));

                    if (CollectionUtils.isNotEmpty(projectPlans)) {
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
                                projectInfoVo.setFinishPre(finishCount / filter.size() * 100);
                            } else {
                                projectInfoVo.setFinishPre((int) (finishWeight / totalWeight * 100));
                            }
                        } else {
                            projectInfoVo.setFinishPre(0);
                        }
                    }
                } catch (Exception e) {
                    baseService.writeExceptionInfo(e);
                }
                projectInfoVos.add(projectInfoVo);
            }
        }
        return projectInfoVos;
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
    public BootstrapTableVo getParticipationProject(QueryProjectInfo queryProjectInfo) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        queryProjectInfo.setUserAccount(processControllerComponent.getThisUser());
        queryProjectInfo.setQueryEstateName(null);
        List<ProjectInfo> list = projectInfoDao.getProjectListByUserAccount(queryProjectInfo);
        List<ProjectInfoVo> projectInfoVos = getProjectInfoVos(list);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(projectInfoVos) ? Lists.newArrayList() : projectInfoVos);
        return vo;
    }
}
