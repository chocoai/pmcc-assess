package com.copower.pmcc.assess.service.project;


import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPhaseDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dto.output.project.ProjectPhaseVo;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            ProjectPhaseVo projectPhaseVo = new ProjectPhaseVo();
            BeanUtils.copyProperties(o, projectPhaseVo);
            ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(o.getWorkStageId());
            projectPhaseVo.setWorkStageName(projectWorkStage.getWorkStageName());
            return projectPhaseVo;
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
        if(StringUtils.isBlank(key)) return null;
        String cacheKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_WORK_PHASE_KEY, key);
        try {
            ProjectPhase projectPhase = LangUtils.singleCache(cacheKey, key, ProjectPhase.class, input -> projectPhaseDao.getProjectPhaseByKey(input));
            return projectPhase;
        } catch (Exception e) {
            logger.error("getCacheProjectPhaseByKey error", e);
            return projectPhaseDao.getProjectPhaseByKey(key);
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
     * 获取类别下的默认事项
     * @param typeId
     * @return
     */
    public List<ProjectPhase> getDefaultProjectPhaseByTypeId(Integer typeId) {
        BaseProjectClassify defaultCategory = baseProjectClassifyService.getDefaultCategory(typeId);
        if(defaultCategory==null) return null;
        return getCacheProjectPhaseByCategoryId(defaultCategory.getId());
    }
}
