package com.copower.pmcc.assess.service.project;


import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectSpotCheckDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.*;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.event.project.ProjectSpotCheckEvent;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.chks.api.dto.AssessmentPerformanceDto;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentPerformanceService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
public class ProjectSpotCheckService {
    @Autowired
    private ProjectSpotCheckDao projectSpotCheckDao;
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
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private ChksRpcAssessmentPerformanceService performanceService;


    public ProjectSpotCheckVo getSpotCheckVoById(Integer id) {
        return getSpotCheckVo(projectSpotCheckDao.getProjectSpotCheckById(id));
    }

    public ProjectSpotCheckVo getSpotCheckVo(ProjectSpotCheck projectSpotCheck) {
        if (projectSpotCheck == null) return null;
        ProjectSpotCheckVo vo = new ProjectSpotCheckVo();
        BeanUtils.copyProperties(projectSpotCheck, vo);
        vo.setBySpotUserName(publicService.getUserNameByAccount(projectSpotCheck.getBySpotUser()));
        return vo;
    }

    /**
     * 获取抽查数据列表
     *
     * @return
     */
    public BootstrapTableVo getProjectSpotCheckList() {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ProjectSpotCheck projectSpotCheck = new ProjectSpotCheck();
        List<ProjectSpotCheck> spotChecks = projectSpotCheckDao.getProjectSpotCheckList(projectSpotCheck);
        List<ProjectSpotCheckVo> vos = LangUtils.transform(spotChecks, o -> getSpotCheckVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public ProjectSpotCheck getSpotCheckByProcessInsId(String processInsId) {
        if (StringUtils.isBlank(processInsId)) return null;
        ProjectSpotCheck where = new ProjectSpotCheck();
        where.setProcessInsId(processInsId);
        List<ProjectSpotCheck> projectSpotCheckList = projectSpotCheckDao.getProjectSpotCheckList(where);
        if (CollectionUtils.isEmpty(projectSpotCheckList)) return null;
        return projectSpotCheckList.get(0);
    }

    /**
     * 获取当月完成的批次
     *
     * @param month
     * @return
     */
    public List<ProjectSpotCheck> getFinishSpotCheckListByMonth(String userAccount, String month) {
        ProjectSpotCheck where = new ProjectSpotCheck();
        where.setStatus(ProcessStatusEnum.FINISH.getValue());
        where.setBySpotUser(userAccount);
        where.setSpotMonth(month);
        List<ProjectSpotCheck> projectSpotCheckList = projectSpotCheckDao.getProjectSpotCheckList(where);
        return projectSpotCheckList;
    }

    /**
     * 获取运行中的批次
     *
     * @return
     */
    public List<ProjectSpotCheck> getRuningSpotCheckList() {
        List<ProjectSpotCheck> resultList = Lists.newArrayList();
        ProjectSpotCheck where = new ProjectSpotCheck();
        where.setStatus(ProjectStatusEnum.DRAFT.getKey());//草稿状态
        List<ProjectSpotCheck> projectSpotCheckList = projectSpotCheckDao.getProjectSpotCheckList(where);
        if (CollectionUtils.isNotEmpty(projectSpotCheckList))
            resultList.addAll(projectSpotCheckList);

        where.setStatus(ProjectStatusEnum.RUNING.getKey());//运行状态
        projectSpotCheckList = projectSpotCheckDao.getProjectSpotCheckList(where);
        if (CollectionUtils.isNotEmpty(projectSpotCheckList))
            resultList.addAll(projectSpotCheckList);
        return resultList;
    }

    public void saveSpotCheck(ProjectSpotCheck spotCheck) {
        if (spotCheck.getId() != null && spotCheck.getId() > 0) {
            projectSpotCheckDao.modifyProjectSpotCheck(spotCheck);
        } else {
            spotCheck.setCreator(commonService.thisUserAccount());
            projectSpotCheckDao.addProjectSpotCheck(spotCheck);
        }
    }

    /**
     * 选择项目
     *
     * @param projectIds
     */
    @Transactional(rollbackFor = Exception.class)
    public void selectProject(String projectIds, Integer spotId) throws BusinessException {
        if (StringUtils.isBlank(projectIds)) return;
        List<Integer> list = FormatUtils.transformString2Integer(projectIds);
        //1.同一个项目只能抽查一次，同一个楼盘一个月不超过3次
        ProjectSpotCheck spotCheck = projectSpotCheckDao.getProjectSpotCheckById(spotId);
        Date startDate = DateUtils.convertDate(spotCheck.getSpotMonth() + "-01");
        Date endDate = DateUtils.convertDate(DateUtils.getLastDayOfMonth(DateUtils.getYear(startDate), DateUtils.getMonth(startDate)));
        for (Integer projectId : list) {
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
            if (projectSpotCheckDao.getSpotCheckItemCountByProjectId(projectId) > 0) {
                throw new BusinessException(String.format("【%s】已被抽查过，同一个项目不能重复抽查", projectInfo.getProjectName()));
            }
            ProjectSpotCheckItem spotCheckItem = new ProjectSpotCheckItem();
            List<String> estateNameList = basicApplyBatchService.getEstateNameListByProjectId(projectId);
            if (CollectionUtils.isNotEmpty(estateNameList)) {
                for (String estateName : estateNameList) {
                    long count = projectSpotCheckDao.getSpotCheckItemCountByEstateName(startDate, endDate, estateName);
                    if (count > 3) {
                        throw new BusinessException(String.format("【%s】该楼盘当月已被多次抽查，请选择其他楼盘", estateName));
                    }
                }
                spotCheckItem.setEstateName(FormatUtils.transformListString(estateNameList));
            }
            spotCheckItem.setSpotId(spotId);
            spotCheckItem.setProjectId(projectId);
            spotCheckItem.setProjectName(projectInfo.getProjectName());
            spotCheckItem.setCreator(commonService.thisUserAccount());
            projectSpotCheckDao.addProjectSpotCheckItem(spotCheckItem);
        }
    }

    /**
     * 获取抽查项目数据列表
     *
     * @return
     */
    public BootstrapTableVo getProjectSpotCheckItemList(Integer spotId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ProjectSpotCheckItem where = new ProjectSpotCheckItem();
        if (spotId != null && spotId > 0) {
            where.setSpotId(spotId);
        } else {
            where.setSpotId(0);
            where.setCreator(commonService.thisUserAccount());
        }
        List<ProjectSpotCheckItem> spotChecks = projectSpotCheckDao.getProjectSpotCheckItemList(where);
        List<ProjectSpotCheckItemVo> spotCheckVos = LangUtils.transform(spotChecks, o -> getProjectSpotCheckItemVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(spotCheckVos);
        return vo;
    }

    public ProjectSpotCheckItem getProjectSpotCheckItemById(Integer id) {
        return projectSpotCheckDao.getProjectSpotCheckItemById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteSpotCheckById(Integer spotId) {
        if (spotId == null || spotId <= 0) return;
        ProjectSpotCheckItem itenWhere = new ProjectSpotCheckItem();
        itenWhere.setSpotId(spotId);
        List<ProjectSpotCheckItem> itemList = projectSpotCheckDao.getProjectSpotCheckItemList(itenWhere);
        if (CollectionUtils.isNotEmpty(itemList)) {
            List<ProjectSpotCheckItemScore> scores = projectSpotCheckDao.getSpotCheckItemScoreListByItemIds(LangUtils.transform(itemList, o -> o.getId()));
            if (CollectionUtils.isNotEmpty(scores)) {
                scores.forEach(o -> projectSpotCheckDao.deleteSpotCheckItemScoreById(o.getId()));
            }
            itemList.forEach(o -> projectSpotCheckDao.deleteSpotCheckItemById(o.getId()));
        }
        projectSpotCheckDao.deleteSpotCheckById(spotId);

        //删除关联到该批次的考核抽查数据
        List<AssessmentPerformanceDto> performanceDtos = performanceService.getPerformancesBySpotBatchIds(Lists.newArrayList(spotId));
        if(CollectionUtils.isNotEmpty(performanceDtos)){
            performanceService.deletePerformanceByIds(LangUtils.transform(performanceDtos,o->o.getId()));
        }
    }

    /**
     * 获取该项目下的阶段
     *
     * @param projectId
     * @param itemId
     * @return
     */
    public List<ProjectPlanVo> getProjectPlanListByProjectId(Integer projectId, Integer itemId) {
        if (projectId == null) return null;
        List<ProjectPlanVo> projectPlanList = projectInfoService.getProjectPlanList(projectId);
        if (CollectionUtils.isEmpty(projectPlanList)) return null;
        ProjectSpotCheckItem spotCheckItem = projectSpotCheckDao.getProjectSpotCheckItemById(itemId);
        if (spotCheckItem != null && StringUtils.isNotBlank(spotCheckItem.getPlanId())) {
            List<Integer> list = FormatUtils.transformString2Integer(spotCheckItem.getPlanId());
            for (ProjectPlanVo projectPlanVo : projectPlanList) {
                projectPlanVo.setChecked(list.contains(projectPlanVo.getId()));
            }
        } else {
            projectPlanList.forEach(o -> o.setChecked(true));
        }
        return projectPlanList;
    }

    /**
     * 保存数据
     *
     * @param spotCheckItem
     */
    public void saveSpotCheckItem(ProjectSpotCheckItem spotCheckItem) {
        if (spotCheckItem == null) return;
        if (spotCheckItem.getId() != null && spotCheckItem.getId() > 0) {
            projectSpotCheckDao.modifyProjectSpotCheckItem(spotCheckItem);
        } else {
            spotCheckItem.setCreator(commonService.thisUserAccount());
            projectSpotCheckDao.addProjectSpotCheckItem(spotCheckItem);
        }
    }

    public List<ProjectSpotCheckItem> getProjectSpotCheckItemsBySpotId(Integer spotId) {
        if (spotId == null) return null;
        ProjectSpotCheckItem where = new ProjectSpotCheckItem();
        where.setSpotId(spotId);
        return projectSpotCheckDao.getProjectSpotCheckItemList(where);
    }

    public ProjectSpotCheckItemVo getProjectSpotCheckItemVo(ProjectSpotCheckItem projectSpotCheckItem) {
        if (projectSpotCheckItem == null) return null;
        ProjectSpotCheckItemVo vo = new ProjectSpotCheckItemVo();
        BeanUtils.copyProperties(projectSpotCheckItem, vo);
        return vo;
    }


    /**
     * 申请提交
     *
     * @param formData
     */
    public void applyCommit(String formData) throws BusinessException, BpmException {
        if (StringUtils.isBlank(formData))
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        ProjectSpotCheck spotCheck = JSON.parseObject(formData, ProjectSpotCheck.class);
        spotCheck.setStatus(ProcessStatusEnum.RUN.getValue());
        saveSpotCheck(spotCheck);

        ProcessInfo processInfo = new ProcessInfo();
        String boxName = baseParameterService.getBaseParameter(BaseParameterEnum.PROJECT_SPOT_CHECK_PROCESS_KEY);
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        processInfo.setFolio(String.format("%s【抽查考核】", spotCheck.getTitle()));//流程描述
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectSpotCheck.class));
        processInfo.setProcessEventExecutor(ProjectSpotCheckEvent.class);
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setTableId(spotCheck.getId());
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getName());
        processInfo.setStartUser(commonService.thisUserAccount());
        ProcessUserDto processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, commonService.thisUserAccount(), false);

        spotCheck.setProcessInsId(processUserDto.getProcessInsId());
        saveSpotCheck(spotCheck);
    }

    //----------------------------------------------------------------------------------------------------


    /**
     * 保存数据
     *
     * @param spotCheckItemScore
     */
    public void saveSpotCheckItemScore(ProjectSpotCheckItemScore spotCheckItemScore) {
        if (spotCheckItemScore == null) return;
        if (spotCheckItemScore.getId() == null || spotCheckItemScore.getId() <= 0) {
            spotCheckItemScore.setCreator(commonService.thisUserAccount());
            spotCheckItemScore.setBisChecked(true);
            projectSpotCheckDao.addProjectSpotCheckItemScore(spotCheckItemScore);
        } else {
            //先将上个版本数据存档一份
            ProjectSpotCheckItemScore checkItemScore = projectSpotCheckDao.getProjectSpotCheckItemScoreById(spotCheckItemScore.getId());
            checkItemScore.setId(null);
            checkItemScore.setBisChecked(false);
            projectSpotCheckDao.addProjectSpotCheckItemScore(checkItemScore);

            projectSpotCheckDao.modifyProjectSpotCheckItemScore(spotCheckItemScore);//更新
        }
    }

    public ProjectSpotCheckItemScore getSpotCheckItemScoreById(Integer id) {
        return projectSpotCheckDao.getProjectSpotCheckItemScoreById(id);
    }

    public List<ProjectSpotCheckItemScoreVo> getHistoryItemScoresByProjectPhaseId(Integer itemId, Integer projectPhaseId) {
        if (itemId == null) return null;
        ProjectSpotCheckItemScore where = new ProjectSpotCheckItemScore();
        where.setBisChecked(false);
        where.setItemId(itemId);
        where.setProjectPhaseId(projectPhaseId);
        List<ProjectSpotCheckItemScore> list = projectSpotCheckDao.getProjectSpotCheckItemScoreList(where);
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, o -> getSpotCheckItemScoreVo(o));
    }

    public ProjectSpotCheckItemScoreVo getSpotCheckItemScoreVo(ProjectSpotCheckItemScore spotCheckItemScore) {
        if (spotCheckItemScore == null) return null;
        ProjectSpotCheckItemScoreVo vo = new ProjectSpotCheckItemScoreVo();
        BeanUtils.copyProperties(spotCheckItemScore, vo);
        vo.setCreatorName(publicService.getUserNameByAccount(spotCheckItemScore.getCreator()));
        return vo;
    }

    public List<ProjectSpotCheckItemScoreVo> getSpotCheckItemScoreVoListByPlanId(Integer itemId, Integer planId) {
        ProjectSpotCheckItemScore where = new ProjectSpotCheckItemScore();
        where.setBisChecked(true);
        where.setItemId(itemId);
        where.setPlanId(planId);
        List<ProjectSpotCheckItemScore> list = projectSpotCheckDao.getProjectSpotCheckItemScoreList(where);
        List<ProjectSpotCheckItemScore> resultList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(list)) {
            resultList.addAll(list);
        }
        List<Integer> projectPhaseIds = LangUtils.transform(resultList, o -> o.getProjectPhaseId());
        List<ProjectPhase> projectPhaseList = projectPlanDetailsService.getProjectPhaseListByPlanId(planId);
        if (CollectionUtils.isNotEmpty(projectPhaseList)) {
            for (ProjectPhase projectPhase : projectPhaseList) {
                if (CollectionUtils.isNotEmpty(projectPhaseIds) && projectPhaseIds.contains(projectPhase.getId()))
                    continue;
                ProjectSpotCheckItemScore item = new ProjectSpotCheckItemScore();
                item.setPlanId(planId);
                item.setProjectPhaseId(projectPhase.getId());
                item.setProjectPhaseName(projectPhase.getProjectPhaseName());
                item.setStandard(projectPhase.getCeReviewStandard());
                item.setStandardScore(projectPhase.getCeReviewScore());
                item.setSorting(projectPhase.getPhaseSort());
                resultList.add(item);
            }
        }
        Collections.sort(resultList, (p1, p2) -> {
            return p1.getSorting() - p2.getSorting();
        });
        return LangUtils.transform(resultList, o -> getSpotCheckItemScoreVo(o));
    }

    public List<ProjectSpotCheckItemScore> getSpotCheckItemScoreListByItemIds(List<Integer> itemIds) {
        if (CollectionUtils.isEmpty(itemIds)) return null;
        return projectSpotCheckDao.getSpotCheckItemScoreListByItemIds(itemIds);
    }
}
