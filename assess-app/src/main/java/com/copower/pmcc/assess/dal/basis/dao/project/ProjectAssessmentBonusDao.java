package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonus;
import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonusExample;
import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonusItem;
import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonusItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectAssessmentBonusItemMapper;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectAssessmentBonusMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectAssessmentBonusDao {
    @Autowired
    private ProjectAssessmentBonusMapper projectAssessmentBonusMapper;
    @Autowired
    private ProjectAssessmentBonusItemMapper projectAssessmentBonusItemMapper;

    public Boolean addAssessmentBonus(ProjectAssessmentBonus projectAssessmentBonus) {
        int i = projectAssessmentBonusMapper.insertSelective(projectAssessmentBonus);
        return i == 1;
    }

    public ProjectAssessmentBonus getAssessmentBonusById(Integer id) {
        return projectAssessmentBonusMapper.selectByPrimaryKey(id);
    }

    public Boolean updateAssessmentBonus(ProjectAssessmentBonus projectAssessmentBonus) {
        int i = projectAssessmentBonusMapper.updateByPrimaryKeySelective(projectAssessmentBonus);
        return i >= 0;
    }

    public ProjectAssessmentBonus getAssessmentBonusByProcessInsId(String processInsId) {
        ProjectAssessmentBonusExample example = new ProjectAssessmentBonusExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        List<ProjectAssessmentBonus> projectAssessmentBonuss = projectAssessmentBonusMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(projectAssessmentBonuss)) {
            return projectAssessmentBonuss.get(0);
        }
        return null;

    }

    //--------------------------------------------------------------------------

    public Boolean addAssessmentBonusItem(ProjectAssessmentBonusItem projectAssessmentBonusItem) {
        int i = projectAssessmentBonusItemMapper.insertSelective(projectAssessmentBonusItem);
        return i == 1;
    }

    public ProjectAssessmentBonusItem getAssessmentBonusItemById(Integer id) {
        return projectAssessmentBonusItemMapper.selectByPrimaryKey(id);
    }

    public Boolean updateAssessmentBonusItem(ProjectAssessmentBonusItem projectAssessmentBonusItem) {
        int i = projectAssessmentBonusItemMapper.updateByPrimaryKeySelective(projectAssessmentBonusItem);
        return i >= 0;
    }

    public List<ProjectAssessmentBonusItem> getAssessmentBonusItemList(Integer bonusId, String projectManager) {
        ProjectAssessmentBonusItemExample example = new ProjectAssessmentBonusItemExample();
        ProjectAssessmentBonusItemExample.Criteria criteria = example.createCriteria();
        if (bonusId != null) {
            criteria.andMasterIdEqualTo(bonusId);
        }
        if (StringUtils.isNotBlank(projectManager)) {
            criteria.andProjectManagerEqualTo(projectManager);
        }
        return null;
    }

    public Long getAssessmentBonusItemCount(Integer projectId) {
        ProjectAssessmentBonusItemExample example = new ProjectAssessmentBonusItemExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        return projectAssessmentBonusItemMapper.countByExample(example);
    }

    public Long getAssessmentBonusItemCountByStatus(Integer masterId, String status) {
        ProjectAssessmentBonusItemExample example = new ProjectAssessmentBonusItemExample();
        ProjectAssessmentBonusItemExample.Criteria criteria = example.createCriteria().andMasterIdEqualTo(masterId);
        criteria.andStatusEqualTo(status);
        return projectAssessmentBonusItemMapper.countByExample(example);
    }
}
