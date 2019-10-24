package com.copower.pmcc.assess.service.project;


import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPhaseDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dto.output.project.ProjectPhaseVo;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2017/08/31 11:20
 */
@Service
public class ProjectPhaseService {
    private final static Logger logger = LoggerFactory.getLogger(ProjectPhaseService.class);
    @Autowired
    private ProjectPhaseDao projectPhaseDao;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    public void updateProjectPhaseById(ProjectPhase projectPhase) {
        processControllerComponent.removeRedisKeyValues(AssessCacheConstant.PMCC_ASSESS_WORK_PHASE, "");
        projectPhaseDao.updateProjectPhaseById(projectPhase);
    }

    public ProjectPhaseVo getProjectPhaseVo(ProjectPhase projectPhase){
        if (projectPhase == null){
            return null;
        }
        ProjectPhaseVo vo = new ProjectPhaseVo() ;
        BeanUtils.copyProperties(projectPhase,vo);
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPhase.getWorkStageId());
        vo.setWorkStageName(projectWorkStage.getWorkStageName());
        return vo;
    }

    public List<ProjectPhaseVo> queryProjectPhaseByCategory(Integer typeId, Integer categoryId, String search) {
        ProjectPhase projectPhase = new ProjectPhase();
        projectPhase.setBisEnable(true); //没有删除的

        if (typeId != null) {
            projectPhase.setProjectTypeId(typeId);

        }

        if (categoryId != null) {
            projectPhase.setProjectCategoryId(categoryId);
        }
        if (StringUtils.isNotBlank(search)) {
            projectPhase.setProjectPhaseName(search);
        }

        List<ProjectPhase> customProjectPhase = projectPhaseDao.getCustomProjectPhase(projectPhase);
        List<ProjectPhaseVo> projectPhaseVos = LangUtils.transform(customProjectPhase, o -> {
            return getProjectPhaseVo(o);
        });
        return projectPhaseVos;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createOrUpdateProjectPhase(ProjectPhase projectPhase) {
        if (projectPhase == null)
            return;

        if (projectPhase.getId() == null) {
            //新增一项
            projectPhaseDao.createProjectPhase(projectPhase);
        } else {
            //修改数据
            if (projectPhase.getBoxName() == null)
                projectPhase.setBoxName("");
            projectPhaseDao.updateProjectPhaseById(projectPhase);
        }
        processControllerComponent.removeRedisKeyValues(AssessCacheConstant.PMCC_ASSESS_WORK_PHASE, "");
    }

    public ProjectPhase getProjectPhaseById(Integer id) {
        return projectPhaseDao.getProjectPhaseById(id);
    }


    public ProjectPhase getCacheProjectPhaseById(Integer id) {
        String cacheKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_WORK_PHASE_ID, String.valueOf(id));
        try {
            ProjectPhase projectPhase = LangUtils.singleCache(cacheKey, id, ProjectPhase.class, input -> projectPhaseDao.getProjectPhaseById(input));
            return projectPhase;
        } catch (Exception e) {
            logger.error("getCacheProjectPhaseById error", e);
            return projectPhaseDao.getProjectPhaseById(id);
        }
    }

    public ProjectPhase getCacheProjectPhaseByKey(String key) {
        if (StringUtils.isBlank(key)) return null;
        String cacheKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_WORK_PHASE_KEY, key);
        try {
            ProjectPhase projectPhase = LangUtils.singleCache(cacheKey, key, ProjectPhase.class, input -> projectPhaseDao.getProjectPhaseByKey(input));
            return projectPhase;
        } catch (Exception e) {
            logger.error("getCacheProjectPhaseByKey error", e);
            return projectPhaseDao.getProjectPhaseByKey(key);
        }
    }

    /**
     * 根据key和类别的引用id
     * @param key
     * @param categoryId
     * @return
     */
    public ProjectPhase getCacheProjectPhaseByReferenceId(String key, Integer categoryId) {
        return getCacheProjectPhaseByKey(key, baseProjectClassifyService.getReferenceId(categoryId));
    }

    private ProjectPhase getCacheProjectPhaseByKey(String key, Integer categoryId) {
        if (StringUtils.isBlank(key)) return null;
        String fullKey = String.format("%s:%s", key, categoryId);
        String cacheKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_WORK_PHASE_KEY, fullKey);
        try {
            ProjectPhase projectPhase = LangUtils.singleCache(cacheKey, key, ProjectPhase.class, input -> projectPhaseDao.getProjectPhaseByKey(input, categoryId));
            return projectPhase;
        } catch (Exception e) {
            logger.error("getCacheProjectPhaseByKey error", e);
            return projectPhaseDao.getProjectPhaseByKey(key, categoryId);
        }
    }

    public List<ProjectPhase> getCacheProjectPhaseByCategoryId(Integer categoryId) {
        String cacheKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_WORK_PHASE_CATEGORYID, String.valueOf(categoryId));
        try {
            List<ProjectPhase> projectPhases = LangUtils.listCache(cacheKey, categoryId, ProjectPhase.class, input -> projectPhaseDao.getProjectPhaseByCategoryId(input));
            return projectPhases;
        } catch (Exception e) {
            logger.error("getCacheProjectPhaseByCategoryId error", e);
            return projectPhaseDao.getProjectPhaseByCategoryId(categoryId);
        }
    }

    /**
     * 缓存中获取工作事项
     *
     * @param categoryId
     * @param workStageId
     * @return
     */
    public List<ProjectPhase> getCacheProjectPhaseByCategoryId(Integer categoryId, Integer workStageId) {
        //1.先取得自身工作事项 2.如果有引用则取得引用的工作事项 3.融合事项
        List<ProjectPhase> projectPhaseList = getCacheProjectPhaseByCategoryId(categoryId);
        List<ProjectPhase> selfPhases = LangUtils.filter(projectPhaseList, o -> o.getWorkStageId().equals(workStageId));
        Integer referenceId = baseProjectClassifyService.getReferenceId(categoryId);
        if (referenceId.equals(categoryId)) return selfPhases;
        List<ProjectPhase> referencePhaseList = getCacheProjectPhaseByCategoryId(referenceId);
        if (CollectionUtils.isEmpty(referencePhaseList)) return selfPhases;
        referencePhaseList = LangUtils.filter(referencePhaseList, o -> o.getWorkStageId().equals(workStageId));
        HashSet<String> keys = Sets.newHashSet();
        selfPhases.forEach(o -> keys.add(o.getPhaseKey()));
        List<ProjectPhase> resultPhaseList = Lists.newArrayList();
        for (ProjectPhase projectPhase : referencePhaseList) {
            if (StringUtils.isNotBlank(projectPhase.getPhaseKey()) && keys.contains(projectPhase.getPhaseKey())) {
                for (ProjectPhase selfPhase : selfPhases) {
                    if (StringUtils.equals(projectPhase.getPhaseKey(), selfPhase.getPhaseKey())) {
                        resultPhaseList.add(selfPhase);
                    }
                }
            } else {
                resultPhaseList.add(projectPhase);
            }
        }
        return resultPhaseList;
    }

    public List<ProjectPhase> getProjectPhaseList(ProjectPhase projectPhase){
        return projectPhaseDao.getProjectPhaseList(projectPhase) ;
    }
}
