package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectReviewScoreItemMapper;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectReviewScoreMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2018/09/03 10:29
 */

@Repository
public class ProjectReviewScoreDao {
    @Autowired
    private ProjectReviewScoreMapper projectReviewScoreMapper;
    @Autowired
    private ProjectReviewScoreItemMapper projectReviewScoreItemMapper;

    public ProjectReviewScore getProjectReviewScoreById(Integer id){
        return projectReviewScoreMapper.selectByPrimaryKey(id);
    }

    /**
     * @param where
     * @return
     */
    public List<ProjectReviewScore> getProjectReviewScoreList(ProjectReviewScore where) {
        ProjectReviewScoreExample example = new ProjectReviewScoreExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectReviewScoreMapper.selectByExample(example);
    }


    /**
     * 新增数据
     * @param record
     * @return
     */
    public boolean addProjectReviewScore(ProjectReviewScore record) {
        return projectReviewScoreMapper.insertSelective(record) == 1;
    }

    /**
     * 更新数据
     * @param record
     * @return
     */
    public boolean modifyProjectReviewScore(ProjectReviewScore record) {
        return projectReviewScoreMapper.updateByPrimaryKeySelective(record) == 1;
    }

    /**
     * 根据条件更新
     * @param record
     * @param where
     * @return
     */
    public int modifyProjectReviewScore(ProjectReviewScore record, ProjectReviewScore where) {
        ProjectReviewScoreExample example = new ProjectReviewScoreExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectReviewScoreMapper.updateByExampleSelective(record, example);
    }

    public Long getProjectReviewScoreCount(Integer projectId) {
        ProjectReviewScoreExample example = new ProjectReviewScoreExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andStatusIsNotNull();
        return projectReviewScoreMapper.countByExample(example);
    }

    //----------------------------------------------------------------------------------

    public ProjectReviewScoreItem getProjectReviewScoreItemById(Integer id){
        return projectReviewScoreItemMapper.selectByPrimaryKey(id);
    }

    /**
     * @param where
     * @return
     */
    public List<ProjectReviewScoreItem> getProjectReviewScoreItemList(ProjectReviewScoreItem where) {
        ProjectReviewScoreItemExample example = new ProjectReviewScoreItemExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectReviewScoreItemMapper.selectByExampleWithBLOBs(example);
    }


    /**
     * 新增数据
     * @param record
     * @return
     */
    public boolean addProjectReviewScoreItem(ProjectReviewScoreItem record) {
        return projectReviewScoreItemMapper.insertSelective(record) == 1;
    }

    /**
     * 更新数据
     * @param record
     * @return
     */
    public boolean modifyProjectReviewScoreItem(ProjectReviewScoreItem record) {
        return projectReviewScoreItemMapper.updateByPrimaryKeySelective(record) == 1;
    }

    /**
     * 根据条件更新
     * @param record
     * @param where
     * @return
     */
    public int modifyProjectReviewScoreItem(ProjectReviewScoreItem record, ProjectReviewScoreItem where) {
        ProjectReviewScoreItemExample example = new ProjectReviewScoreItemExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectReviewScoreItemMapper.updateByExampleSelective(record, example);
    }
}
