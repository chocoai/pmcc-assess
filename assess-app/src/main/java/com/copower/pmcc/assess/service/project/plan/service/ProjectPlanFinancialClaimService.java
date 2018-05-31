package com.copower.pmcc.assess.service.project.plan.service;

import com.copower.pmcc.assess.dal.dao.ProjectPlanDao;
import com.copower.pmcc.assess.dal.dao.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.project.ProjectPlanDetailsDto;
import com.copower.pmcc.assess.dto.input.project.ProjectPlanFinancialClaimFastDto;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.csr.CsrBorrowerService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.regexp.RE;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/31
 * @time: 10:42
 */
@Service
public class ProjectPlanFinancialClaimService {
    @Autowired
    private CsrBorrowerService csrBorrowerService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectPlanDao projectPlanDao;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    public List<ProjectPlanDetailsVo> getProjectPlanDetailsByProjectId(Integer projectId, Integer planId) {
        List<ProjectPlanDetailsVo> projectPlanDetailsVos = projectPlanDetailsService.getProjectPlanDetailsByPlanApply(planId);
        if (CollectionUtils.isEmpty(projectPlanDetailsVos)) {
            InitProjectPlanDetails(projectId, planId);
            projectPlanDetailsVos = projectPlanDetailsService.getProjectPlanDetailsByPlanApply(planId);
        }

        return projectPlanDetailsVos;
    }

    private void InitProjectPlanDetails(Integer projectId, Integer planId) {
        //取得当前项目的所有客户信息
        List<CsrBorrower> csrBorrowers = csrBorrowerService.getCsrBorrowerByProjectId(projectId);
        //对每一个客户添加相应的默认工作任务事项

        ProjectPlan projectPlan = projectPlanDao.getProjectplanById(planId);

        List<ProjectPhase> projectPhases = projectPhaseService.getCacheProjectPhaseByCategoryId(projectPlan.getCategoryId());
        List<ProjectPhase> filter = LangUtils.filter(projectPhases, o -> {
            return o.getWorkStageId().equals(projectPlan.getWorkStageId());
        });
        if (CollectionUtils.isNotEmpty(csrBorrowers)) {
            for (CsrBorrower item : csrBorrowers) {
                ProjectPlanDetails projectPlanDetailsPid = new ProjectPlanDetails();
                projectPlanDetailsPid.setProjectPhaseName(item.getName());
                projectPlanDetailsPid.setPlanId(projectPlan.getId());
                projectPlanDetailsPid.setProjectId(projectPlan.getProjectId());
                projectPlanDetailsPid.setProjectPhaseId(0);
                projectPlanDetailsPid.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                projectPlanDetailsPid.setSorting(0);
                projectPlanDetailsPid.setProjectWorkStageId(projectPlan.getWorkStageId());
                projectPlanDetailsPid.setFirstPid(1);
                projectPlanDetailsPid.setPid(0);
                projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetailsPid);

                if (CollectionUtils.isNotEmpty(filter)) {
                    for (ProjectPhase projectPhase : filter) {
                        ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
                        projectPlanDetails.setProjectPhaseName(projectPhase.getProjectPhaseName());
                        projectPlanDetails.setPlanHours(projectPhase.getPhaseTime());
                        projectPlanDetails.setPlanId(projectPlan.getId());
                        projectPlanDetails.setProjectId(projectPlan.getProjectId());
                        projectPlanDetails.setProjectPhaseId(projectPhase.getId());
                        projectPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                        projectPlanDetails.setSorting(projectPhase.getPhaseSort());
                        projectPlanDetails.setProjectWorkStageId(projectPlan.getWorkStageId());
                        projectPlanDetails.setFirstPid(0);
                        projectPlanDetails.setPid(projectPlanDetailsPid.getId());
                        projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);
                    }
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void fastSetPlan(ProjectPlanFinancialClaimFastDto projectPlanFinancialClaimFastDto) throws BusinessException {
        if (projectPlanFinancialClaimFastDto == null) {
            throw new BusinessException("没有要保存的数据");
        }
        List<ProjectPlanDetails> projectPlanDetails = projectPlanDetailsService.getPlanDetailsByPlanId(projectPlanFinancialClaimFastDto.getPlanId());
        if (StringUtils.isNotBlank(projectPlanFinancialClaimFastDto.getCustomerList())) {
            List<String> strings = FormatUtils.transformString2List(projectPlanFinancialClaimFastDto.getCustomerList());
            projectPlanDetails = LangUtils.filter(projectPlanDetails, o -> {
                return strings.contains(String.valueOf(o.getPid()));
            });
        }
        if (StringUtils.isNotBlank(projectPlanFinancialClaimFastDto.getPhaseList())) {
            List<String> strings = FormatUtils.transformString2List(projectPlanFinancialClaimFastDto.getPhaseList());
            projectPlanDetails = LangUtils.filter(projectPlanDetails, o -> {
                return strings.contains(String.valueOf(o.getProjectPhaseId()));
            });
        }
        if (CollectionUtils.isNotEmpty(projectPlanDetails)) {
            for (ProjectPlanDetails item : projectPlanDetails) {
                item.setPlanStartDate(DateUtils.parse(projectPlanFinancialClaimFastDto.getPlanStartDate()));
                item.setPlanEndDate(DateUtils.parse(projectPlanFinancialClaimFastDto.getPlanEndDate()));
                item.setPlanHours(projectPlanFinancialClaimFastDto.getPlanHours());
                item.setExecuteUserAccount(projectPlanFinancialClaimFastDto.getExecuteUserAccount());
                try {
                    projectPlanDetailsDao.updateProjectPlanDetails(item);
                } catch (Exception e) {
                    throw new BusinessException(e.getMessage());
                }
            }
        }

    }

    public void updateProjectPlanDetails(ProjectPlanDetails projectPlanDetails) throws BusinessException {
        projectPlanDetailsDao.updateProjectPlanDetails(projectPlanDetails);
    }
}
