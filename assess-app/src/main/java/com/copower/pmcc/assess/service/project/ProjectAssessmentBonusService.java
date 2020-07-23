package com.copower.pmcc.assess.service.project;


import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectAssessmentBonusDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPhaseDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonus;
import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonusItem;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dto.output.project.ProjectPhaseVo;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.CommonService;
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


@Service
public class ProjectAssessmentBonusService {
    private final static Logger logger = LoggerFactory.getLogger(ProjectAssessmentBonusService.class);
    @Autowired
    private ProjectAssessmentBonusDao projectAssessmentBonusDao;
    @Autowired
    private CommonService commonService;

    public void saveAssessmentBonus(ProjectAssessmentBonus assessmentBonus) {
        if (assessmentBonus.getId() != null || assessmentBonus.getId() > 0) {
            projectAssessmentBonusDao.updateAssessmentBonus(assessmentBonus);
        } else {
            assessmentBonus.setCreator(commonService.thisUserAccount());
            projectAssessmentBonusDao.addAssessmentBonus(assessmentBonus);
        }
    }

    public void saveAssessmentBonusItem(ProjectAssessmentBonusItem assessmentBonusItem) {
        if (assessmentBonusItem.getId() != null || assessmentBonusItem.getId() > 0) {
            projectAssessmentBonusDao.updateAssessmentBonusItem(assessmentBonusItem);
        } else {
            assessmentBonusItem.setCreator(commonService.thisUserAccount());
            projectAssessmentBonusDao.addAssessmentBonusItem(assessmentBonusItem);
        }
    }

    /**
     * 根据项目id获取数量
     * @param projectId
     * @return
     */
    public Long getAssessmentBonusItemCount(Integer projectId){
        return projectAssessmentBonusDao.getAssessmentBonusItemCount(projectId);
    }
}
