package com.copower.pmcc.assess.service.project;


import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectReviewScoreDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectReviewScoreItemVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.project.ProjectReviewScoreEvent;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@Service
public class ProjectReviewScoreService {
    @Autowired
    private ProjectReviewScoreDao projectReviewScoreDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectMemberService projectMemberService;

    public ProjectReviewScore getReviewScoreById(Integer id) {
        return projectReviewScoreDao.getProjectReviewScoreById(id);
    }

    public ProjectReviewScore getReviewScoreByProjectId(Integer projectId) {
        if (projectId == null) return null;
        ProjectReviewScore where = new ProjectReviewScore();
        where.setProjectId(projectId);
        List<ProjectReviewScore> projectReviewScoreList = projectReviewScoreDao.getProjectReviewScoreList(where);
        if (CollectionUtils.isEmpty(projectReviewScoreList)) return null;
        return projectReviewScoreList.get(0);
    }

    public ProjectReviewScore getReviewScoreByProcessInsId(String processInsId) {
        if (StringUtils.isBlank(processInsId)) return null;
        ProjectReviewScore where = new ProjectReviewScore();
        where.setProcessInsId(processInsId);
        List<ProjectReviewScore> projectReviewScoreList = projectReviewScoreDao.getProjectReviewScoreList(where);
        if (CollectionUtils.isEmpty(projectReviewScoreList)) return null;
        return projectReviewScoreList.get(0);
    }

    public void saveReviewScore(ProjectReviewScore reviewScore) {
        if (reviewScore.getId() != null && reviewScore.getId() > 0) {
            projectReviewScoreDao.modifyProjectReviewScore(reviewScore);
        } else {
            reviewScore.setCreator(commonService.thisUserAccount());
            projectReviewScoreDao.addProjectReviewScore(reviewScore);
        }
    }

    public Long getProjectReviewScoreCount(Integer projectId) {
        return projectReviewScoreDao.getProjectReviewScoreCount(projectId);
    }

    /**
     * 申请提交
     *
     * @param projectId
     */
    @Transactional(rollbackFor = Exception.class)
    public void applyCommit(Integer projectId, ProjectReviewScore reviewScore) throws BusinessException, BpmException {
        if (projectId == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        reviewScore.setStatus(ProcessStatusEnum.RUN.getValue());
        saveReviewScore(reviewScore);

        ProcessInfo processInfo = new ProcessInfo();
        String boxName = baseParameterService.getBaseParameter(BaseParameterEnum.PROJECT_REVIEW_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        SysUserDto thisUserInfo = processControllerComponent.getThisUserInfo();
        processInfo.setFolio(String.format("%s【复核工时考核】%s", thisUserInfo.getUserName(), projectInfo.getProjectName()));//流程描述
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectReviewScore.class));
        processInfo.setProcessEventExecutor(ProjectReviewScoreEvent.class);
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setTableId(reviewScore.getId());
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getName());
        processInfo.setStartUser(commonService.thisUserAccount());
        ProcessUserDto processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, commonService.thisUserAccount(), false);
        try {
            reviewScore.setProcessInsId(processUserDto.getProcessInsId());
            saveReviewScore(reviewScore);
        } catch (Exception ex) {//关闭无效流程
            bpmRpcActivitiProcessManageService.closeProcess(processUserDto.getProcessInsId());
            throw ex;
        }
    }

    //----------------------------------------------------------------------------------------------------

    public ProjectReviewScoreItem getReviewScoreItemById(Integer id) {
        return projectReviewScoreDao.getProjectReviewScoreItemById(id);
    }

    public List<ProjectReviewScoreItemVo> getEnableItemsByReviewId(Integer reviewId) {
        if (reviewId == null) return null;
        ProjectReviewScoreItem where = new ProjectReviewScoreItem();
        where.setBisEnable(true);
        where.setReviewId(reviewId);
        List<ProjectReviewScoreItem> list = projectReviewScoreDao.getProjectReviewScoreItemList(where);
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, o -> getReviewScoreItemVo(o));
    }

    public List<ProjectReviewScoreItemVo> getHistoryItemsByProjectPhaseId(Integer reviewId, Integer projectPhaseId) {
        if (reviewId == null) return null;
        ProjectReviewScoreItem where = new ProjectReviewScoreItem();
        where.setBisEnable(false);
        where.setReviewId(reviewId);
        where.setProjectPhaseId(projectPhaseId);
        List<ProjectReviewScoreItem> list = projectReviewScoreDao.getProjectReviewScoreItemList(where);
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, o -> getReviewScoreItemVo(o));
    }

    public ProjectReviewScoreItemVo getReviewScoreItemVo(ProjectReviewScoreItem reviewScoreItem) {
        if (reviewScoreItem == null) return null;
        ProjectReviewScoreItemVo vo = new ProjectReviewScoreItemVo();
        BeanUtils.copyProperties(reviewScoreItem, vo);
        vo.setCreatorName(publicService.getUserNameByAccount(reviewScoreItem.getCreator()));
        return vo;
    }

    public List<ProjectReviewScoreItemVo> getReviewScoreItemVoListByPlanId(Integer reviewId, Integer planId) {
        ProjectReviewScoreItem where = new ProjectReviewScoreItem();
        where.setBisEnable(true);
        where.setReviewId(reviewId);
        where.setPlanId(planId);
        List<ProjectReviewScoreItem> list = projectReviewScoreDao.getProjectReviewScoreItemList(where);
        List<ProjectReviewScoreItem> resultList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(list)) {
            resultList.addAll(list);
        }
        ProjectReviewScore reviewScore = getReviewScoreById(reviewId);
        String projectManager = projectMemberService.getProjectManager(reviewScore.getProjectId());
        List<Integer> projectPhaseIds = LangUtils.transform(resultList, o -> o.getProjectPhaseId());
        List<ProjectPhase> projectPhaseList = projectPlanDetailsService.getProjectPhaseListByPlanId(planId);
        if (CollectionUtils.isNotEmpty(projectPhaseList)) {
            for (ProjectPhase projectPhase : projectPhaseList) {
                if (CollectionUtils.isNotEmpty(projectPhaseIds) && projectPhaseIds.contains(projectPhase.getId()))
                    continue;
                if (isMatchAboutUser(planId, projectPhase.getId(), projectManager)) continue;
                ProjectReviewScoreItem item = new ProjectReviewScoreItem();
                item.setPlanId(planId);
                item.setProjectPhaseId(projectPhase.getId());
                item.setProjectPhaseName(projectPhase.getProjectPhaseName());
                item.setStandard(projectPhase.getManagerReviewStandard());
                item.setStandardScore(projectPhase.getManagerReviewScore());
                item.setSorting(projectPhase.getPhaseSort());
                resultList.add(item);
            }
        }
        Collections.sort(resultList, (p1, p2) -> {
            return p1.getSorting() - p2.getSorting();
        });
        return LangUtils.transform(resultList, o -> getReviewScoreItemVo(o));
    }

    //确定该对应工作事项的任务是否都为项目经理处理的，如果是则项目经理不能获得复核工分
    private Boolean isMatchAboutUser(Integer planId, Integer projectPhaseId, String projectManager) {
        if (planId == null || projectPhaseId == null || StringUtils.isBlank(projectManager)) return false;
        ProjectPlanDetails where = new ProjectPlanDetails();
        where.setPlanId(planId);
        where.setProjectPhaseId(projectPhaseId);
        List<ProjectPlanDetails> detailsList = projectPlanDetailsService.getProjectDetails(where);
        if (CollectionUtils.isEmpty(detailsList)) return false;
        for (ProjectPlanDetails projectPlanDetails : detailsList) {
            if (!projectManager.equalsIgnoreCase(projectPlanDetails.getExecuteUserAccount()))
                return false;
        }
        return true;
    }

    /**
     * 保存数据
     *
     * @param reviewScoreItem
     */
    public void saveReviewScoreItem(ProjectReviewScoreItem reviewScoreItem) {
        if (reviewScoreItem == null) return;
        if (reviewScoreItem.getId() == null || reviewScoreItem.getId() <= 0) {
            reviewScoreItem.setCreator(commonService.thisUserAccount());
            reviewScoreItem.setBisEnable(true);
            projectReviewScoreDao.addProjectReviewScoreItem(reviewScoreItem);
        } else {
            //先将上个版本数据存档一份
            ProjectReviewScoreItem scoreItem = projectReviewScoreDao.getProjectReviewScoreItemById(reviewScoreItem.getId());
            scoreItem.setId(null);
            scoreItem.setBisEnable(false);
            projectReviewScoreDao.addProjectReviewScoreItem(scoreItem);

            projectReviewScoreDao.modifyProjectReviewScoreItem(reviewScoreItem);//更新
        }
    }
}
