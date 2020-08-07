package com.copower.pmcc.assess.service.project;


import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectReviewScoreDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScore;
import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScoreGroup;
import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScoreItem;
import com.copower.pmcc.assess.dto.output.project.ProjectReviewScoreGroupVo;
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
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public ProjectReviewScore getReviewScoreById(Integer id) {
        return projectReviewScoreDao.getProjectReviewScoreById(id);
    }

    public ProjectReviewScore getReviewScoreByProjectId(Integer projectId) {
        if (projectId==null) return null;
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
     * @param formData
     * @param projectId
     */
    @Transactional(rollbackFor = Exception.class)
    public void applyCommit(String formData, Integer projectId) throws BusinessException, BpmException {
        if (StringUtils.isBlank(formData) || projectId == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        ProjectReviewScore reviewScore = new ProjectReviewScore();
        reviewScore.setProjectId(projectId);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        reviewScore.setProjectName(projectInfo.getProjectName());
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

            ProjectReviewScoreGroupVo projectReviewScoreGroup = JSON.parseObject(formData, ProjectReviewScoreGroupVo.class);
            projectReviewScoreGroup.setReviewId(reviewScore.getId());
            projectReviewScoreGroup.setBisEnable(true);
            addReviewScoreGroup(projectReviewScoreGroup);
        } catch (Exception ex) {//关闭无效流程
            bpmRpcActivitiProcessManageService.closeProcess(processUserDto.getProcessInsId());
            throw ex;
        }
    }

    //----------------------------------------------------------------------------------------------------

    public void addReviewScoreGroup(ProjectReviewScoreGroupVo reviewScoreGroupVo) {
        //先将上一条数据置为无效状态
        ProjectReviewScoreGroup scoreGroup = getEnableReviewScoreGroupByReviewId(reviewScoreGroupVo.getReviewId());
        if (scoreGroup != null) {
            scoreGroup.setBisEnable(false);
            projectReviewScoreDao.modifyProjectReviewScoreGroup(scoreGroup);
        }
        reviewScoreGroupVo.setBisEnable(true);
        reviewScoreGroupVo.setCreator(commonService.thisUserAccount());
        projectReviewScoreDao.addProjectReviewScoreGroup(reviewScoreGroupVo);

        //并添加子项数据
        if (CollectionUtils.isNotEmpty(reviewScoreGroupVo.getReviewScoreItemList())) {
            for (ProjectReviewScoreItem scoreItem : reviewScoreGroupVo.getReviewScoreItemList()) {
                scoreItem.setGroupId(reviewScoreGroupVo.getId());
                scoreItem.setCreator(commonService.thisUserAccount());
                projectReviewScoreDao.addProjectReviewScoreItem(scoreItem);
            }
        }
    }

    public ProjectReviewScoreGroupVo getEnableReviewScoreGroupByReviewId(Integer reviewId) {
        if (reviewId == null) return null;
        ProjectReviewScoreGroup where = new ProjectReviewScoreGroup();
        where.setBisEnable(true);
        where.setReviewId(reviewId);
        List<ProjectReviewScoreGroup> list = projectReviewScoreDao.getProjectReviewScoreGroupList(where);
        if (CollectionUtils.isEmpty(list)) return null;
        return getReviewScoreGroupVo(list.get(0));
    }

    public List<ProjectReviewScoreGroup> getHistoryReviewScoreItemsByReviewId(Integer reviewId) {
        if (reviewId == null) return null;
        ProjectReviewScoreGroup where = new ProjectReviewScoreGroup();
        where.setBisEnable(false);
        where.setReviewId(reviewId);
        List<ProjectReviewScoreGroup> list = projectReviewScoreDao.getProjectReviewScoreGroupList(where);
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, o -> getReviewScoreGroupVo(o));
    }

    public ProjectReviewScoreGroupVo getReviewScoreGroupVo(ProjectReviewScoreGroup reviewScoreGroup) {
        if (reviewScoreGroup == null) return null;
        ProjectReviewScoreGroupVo vo = new ProjectReviewScoreGroupVo();
        BeanUtils.copyProperties(reviewScoreGroup, vo);
        vo.setCreatorName(publicService.getUserNameByAccount(reviewScoreGroup.getCreator()));
        ProjectReviewScoreItem where = new ProjectReviewScoreItem();
        where.setGroupId(reviewScoreGroup.getId());
        vo.setReviewScoreItemList(projectReviewScoreDao.getProjectReviewScoreItemList(where));
        return vo;
    }
}
