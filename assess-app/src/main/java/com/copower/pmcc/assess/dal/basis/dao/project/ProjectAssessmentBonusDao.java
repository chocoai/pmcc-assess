package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectAssessmentBonusItemHistoryMapper;
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
    @Autowired
    private ProjectAssessmentBonusItemHistoryMapper projectAssessmentBonusItemHistoryMapper;

    public Long getProjectAssessmentBonusByCount( String title ,Integer year,Integer month){
        ProjectAssessmentBonusExample example = new ProjectAssessmentBonusExample();
        ProjectAssessmentBonusExample.Criteria criteria = example.createCriteria();
        if (year != null) {
            criteria.andYearEqualTo(year);
        }
        if (month != null) {
            criteria.andMonthEqualTo(month);
        }
        if (StringUtils.isNotBlank(title)){
            criteria.andTitleEqualTo(title);
        }
        return projectAssessmentBonusMapper.countByExample(example) ;
    }


    public List<ProjectAssessmentBonus> getProjectAssessmentBonusByWhere(String processInsId, String title, String status, String creator, Integer year, Integer month) {
        ProjectAssessmentBonusExample example = new ProjectAssessmentBonusExample();
        ProjectAssessmentBonusExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(processInsId)) {
            criteria.andProcessInsIdEqualTo(processInsId);
        }
        if (StringUtils.isNotBlank(status)) {
            criteria.andStatusEqualTo(status);
        }
        if (StringUtils.isNotBlank(creator)) {
            criteria.andCreatorEqualTo(creator);
        }
        if (year != null) {
            criteria.andYearEqualTo(year);
        }
        if (month != null) {
            criteria.andMonthEqualTo(month);
        }
        if (StringUtils.isNotBlank(title)) {
            criteria.andTitleLike(String.format("%%%s%%", title));
        }
        return projectAssessmentBonusMapper.selectByExample(example) ;
    }

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

    public boolean deleteProjectAssessmentBonusItemByMasterId(Integer masterId){
        ProjectAssessmentBonusItemExample example = new ProjectAssessmentBonusItemExample();
        ProjectAssessmentBonusItemExample.Criteria criteria = example.createCriteria();
        criteria.andMasterIdEqualTo(masterId) ;
        return projectAssessmentBonusItemMapper.deleteByExample(example) > 0;
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
        return projectAssessmentBonusItemMapper.selectByExample(example);
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

    //-------------------------------------------------------------------------


    public Boolean addAssessmentBonusItemHistory(ProjectAssessmentBonusItemHistory projectAssessmentBonusItemHistory) {
        int i = projectAssessmentBonusItemHistoryMapper.insertSelective(projectAssessmentBonusItemHistory);
        return i == 1;
    }

    public ProjectAssessmentBonusItemHistory getAssessmentBonusItemHistoryById(Integer id) {
        return projectAssessmentBonusItemHistoryMapper.selectByPrimaryKey(id);
    }

    public List<ProjectAssessmentBonusItemHistory> getAssessmentBonusItemHistoryList(Integer itemId) {
        ProjectAssessmentBonusItemHistoryExample example = new ProjectAssessmentBonusItemHistoryExample();
        ProjectAssessmentBonusItemHistoryExample.Criteria criteria = example.createCriteria();
        if (itemId != null) {
            criteria.andItemIdEqualTo(itemId);
        }
        return projectAssessmentBonusItemHistoryMapper.selectByExample(example);
    }

    public boolean deleteProjectAssessmentBonusItemHistoryByItemIds(List<Integer> itemIds){
        ProjectAssessmentBonusItemHistoryExample example = new ProjectAssessmentBonusItemHistoryExample();
        ProjectAssessmentBonusItemHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdIn(itemIds);
        return projectAssessmentBonusItemHistoryMapper.deleteByExample(example) > 0 ;
    }
}
