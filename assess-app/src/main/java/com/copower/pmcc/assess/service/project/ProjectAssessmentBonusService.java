package com.copower.pmcc.assess.service.project;


import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectAssessmentBonusDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPhaseDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonus;
import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonusItem;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dto.output.project.ProjectAssessmentBonusItemVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPhaseVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    @Autowired
    private ProjectAssessmentBonusDao projectAssessmentBonusDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private PublicService publicService;

    public ProjectAssessmentBonus getAssessmentBonusById(Integer id) {
        return projectAssessmentBonusDao.getAssessmentBonusById(id);
    }

    public ProjectAssessmentBonus getAssessmentBonusByProcessInsId(String processInsId) {
        return projectAssessmentBonusDao.getAssessmentBonusByProcessInsId(processInsId);
    }

    public void saveAssessmentBonus(ProjectAssessmentBonus assessmentBonus) {
        if (assessmentBonus.getId() != null && assessmentBonus.getId() > 0) {
            projectAssessmentBonusDao.updateAssessmentBonus(assessmentBonus);
        } else {
            assessmentBonus.setCreator(commonService.thisUserAccount());
            projectAssessmentBonusDao.addAssessmentBonus(assessmentBonus);
        }
    }

    public void saveAssessmentBonusItem(ProjectAssessmentBonusItem assessmentBonusItem) {
        if (assessmentBonusItem.getId() != null && assessmentBonusItem.getId() > 0) {
            projectAssessmentBonusDao.updateAssessmentBonusItem(assessmentBonusItem);
        } else {
            assessmentBonusItem.setCreator(commonService.thisUserAccount());
            projectAssessmentBonusDao.addAssessmentBonusItem(assessmentBonusItem);
        }
    }

    /**
     * 根据项目id获取数量
     *
     * @param projectId
     * @return
     */
    public Long getAssessmentBonusItemCount(Integer projectId) {
        return projectAssessmentBonusDao.getAssessmentBonusItemCount(projectId);
    }

    /**
     * 获取数据
     *
     * @param bonusId
     * @param projectManager
     * @return
     */
    public BootstrapTableVo getAssessmentBonusItems(Integer bonusId, String projectManager) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ProjectAssessmentBonusItem> bonusItemList = projectAssessmentBonusDao.getAssessmentBonusItemList(bonusId, projectManager);
        List<ProjectAssessmentBonusItemVo> itemVos = LangUtils.transform(bonusItemList, o -> getAssessmentBonusItemVo(o));
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(itemVos);
        return bootstrapTableVo;
    }

    public List<ProjectAssessmentBonusItem> getAssessmentBonusItemsByMasterId(Integer masterId) {
        if (masterId == null) return null;
        return projectAssessmentBonusDao.getAssessmentBonusItemList(masterId, null);
    }

    public List<ProjectAssessmentBonusItem> getAssessmentBonusItemsByManager(Integer masterId, String projectManager) {
        if (masterId == null || StringUtils.isBlank(projectManager)) return null;
        return projectAssessmentBonusDao.getAssessmentBonusItemList(masterId, projectManager);
    }

    public ProjectAssessmentBonusItemVo getAssessmentBonusItemVo(ProjectAssessmentBonusItem assessmentBonusItem) {
        if (assessmentBonusItem == null) return null;
        ProjectAssessmentBonusItemVo vo = new ProjectAssessmentBonusItemVo();
        BeanUtils.copyProperties(assessmentBonusItem, vo);
        vo.setProjectManagerName(publicService.getUserNameByAccount(assessmentBonusItem.getProjectManager()));
        return vo;
    }

    /**
     * 是否全部完成
     * @param masterId
     * @return
     */
    public Boolean isAllFinish(Integer masterId) {
        Long count = projectAssessmentBonusDao.getAssessmentBonusItemCountByStatus(masterId, ProjectStatusEnum.WAIT.getKey());
        return count <= 0;
    }
}
