package com.copower.pmcc.assess.service.project;


import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectReviewScoreDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonus;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScore;
import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScoreItem;
import com.copower.pmcc.assess.dto.output.project.ProjectReviewScoreItemVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.project.ProjectAssessmentBonusEvent;
import com.copower.pmcc.assess.service.event.project.ProjectReviewScoreEvent;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
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

    public ProjectReviewScore getReviewScoreById(Integer id) {
        return projectReviewScoreDao.getProjectReviewScoreById(id);
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

        reviewScore.setProcessInsId(processUserDto.getProcessInsId());
        saveReviewScore(reviewScore);

        ProjectReviewScoreItem projectReviewScoreItem = JSON.parseObject(formData, ProjectReviewScoreItem.class);
        projectReviewScoreItem.setMasterId(reviewScore.getId());
        projectReviewScoreItem.setBisEnable(true);
        addReviewScoreItem(projectReviewScoreItem);
    }

    //----------------------------------------------------------------------------------------------------

    public void addReviewScoreItem(ProjectReviewScoreItem reviewScoreItem) {
        //先将上一条数据置为无效状态
        ProjectReviewScoreItem scoreItem = getEnableReviewScoreItemsByMasterId(reviewScoreItem.getMasterId());
        if (scoreItem != null) {
            scoreItem.setBisEnable(false);
            projectReviewScoreDao.modifyProjectReviewScoreItem(scoreItem);
        }
        reviewScoreItem.setBisEnable(true);
        reviewScoreItem.setCreator(commonService.thisUserAccount());
        projectReviewScoreDao.addProjectReviewScoreItem(reviewScoreItem);
    }

    public ProjectReviewScoreItem getEnableReviewScoreItemsByMasterId(Integer masterId) {
        if (masterId == null) return null;
        ProjectReviewScoreItem where = new ProjectReviewScoreItem();
        where.setBisEnable(true);
        where.setMasterId(masterId);
        List<ProjectReviewScoreItem> list = projectReviewScoreDao.getProjectReviewScoreItemList(where);
        if (CollectionUtils.isEmpty(list)) return null;
        return list.get(0);
    }

    public List<ProjectReviewScoreItem> getHistoryReviewScoreItemsByMasterId(Integer masterId) {
        if (masterId == null) return null;
        ProjectReviewScoreItem where = new ProjectReviewScoreItem();
        where.setBisEnable(false);
        where.setMasterId(masterId);
        List<ProjectReviewScoreItem> list = projectReviewScoreDao.getProjectReviewScoreItemList(where);
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, o -> getReviewScoreItemVo(o));
    }

    public ProjectReviewScoreItemVo getReviewScoreItemVo(ProjectReviewScoreItem reviewScoreItem) {
        if (reviewScoreItem == null) return null;
        ProjectReviewScoreItemVo vo = new ProjectReviewScoreItemVo();
        BeanUtils.copyProperties(reviewScoreItem, vo);
        vo.setCreatorName(publicService.getUserNameByAccount(reviewScoreItem.getCreator()));
        if (StringUtils.isNotBlank(reviewScoreItem.getContent())) {
            vo.setKeyValueDtos(JSON.parseArray(reviewScoreItem.getContent(), KeyValueDto.class));
        }
        return vo;
    }
}
