package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectSpotCheckItemMapper;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectSpotCheckMapper;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectSpotCheckScoreMapper;
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
public class ProjectSpotCheckDao {
    @Autowired
    private ProjectSpotCheckMapper projectSpotCheckMapper;
    @Autowired
    private ProjectSpotCheckItemMapper projectSpotCheckItemMapper;
    @Autowired
    private ProjectSpotCheckScoreMapper projectSpotCheckScoreMapper;

    public ProjectSpotCheck getProjectSpotCheckById(Integer id){
        return projectSpotCheckMapper.selectByPrimaryKey(id);
    }

    /**
     * @param where
     * @return
     */
    public List<ProjectSpotCheck> getProjectSpotCheckList(ProjectSpotCheck where) {
        ProjectSpotCheckExample example = new ProjectSpotCheckExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectSpotCheckMapper.selectByExample(example);
    }


    /**
     * 新增数据
     * @param record
     * @return
     */
    public boolean addProjectSpotCheck(ProjectSpotCheck record) {
        return projectSpotCheckMapper.insertSelective(record) == 1;
    }

    /**
     * 更新数据
     * @param record
     * @return
     */
    public boolean modifyProjectSpotCheck(ProjectSpotCheck record) {
        return projectSpotCheckMapper.updateByPrimaryKeySelective(record) == 1;
    }

    /**
     * 根据条件更新
     * @param record
     * @param where
     * @return
     */
    public int modifyProjectSpotCheck(ProjectSpotCheck record, ProjectSpotCheck where) {
        ProjectSpotCheckExample example = new ProjectSpotCheckExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectSpotCheckMapper.updateByExampleSelective(record, example);
    }

    //----------------------------------------------------------------------------------

    public ProjectSpotCheckItem getProjectSpotCheckItemById(Integer id){
        return projectSpotCheckItemMapper.selectByPrimaryKey(id);
    }

    /**
     * @param where
     * @return
     */
    public List<ProjectSpotCheckItem> getProjectSpotCheckItemList(ProjectSpotCheckItem where) {
        ProjectSpotCheckItemExample example = new ProjectSpotCheckItemExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectSpotCheckItemMapper.selectByExample(example);
    }


    /**
     * 新增数据
     * @param record
     * @return
     */
    public boolean addProjectSpotCheckItem(ProjectSpotCheckItem record) {
        return projectSpotCheckItemMapper.insertSelective(record) == 1;
    }

    /**
     * 更新数据
     * @param record
     * @return
     */
    public boolean modifyProjectSpotCheckItem(ProjectSpotCheckItem record) {
        return projectSpotCheckItemMapper.updateByPrimaryKeySelective(record) == 1;
    }

    /**
     * 根据条件更新
     * @param record
     * @param where
     * @return
     */
    public int modifyProjectSpotCheckItem(ProjectSpotCheckItem record, ProjectSpotCheckItem where) {
        ProjectSpotCheckItemExample example = new ProjectSpotCheckItemExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectSpotCheckItemMapper.updateByExampleSelective(record, example);
    }

    //------------------------------------------------------------------------------------------------

    public ProjectSpotCheckScore getProjectSpotCheckScoreById(Integer id){
        return projectSpotCheckScoreMapper.selectByPrimaryKey(id);
    }

    /**
     * @param where
     * @return
     */
    public List<ProjectSpotCheckScore> getProjectSpotCheckScoreList(ProjectSpotCheckScore where) {
        ProjectSpotCheckScoreExample example = new ProjectSpotCheckScoreExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectSpotCheckScoreMapper.selectByExample(example);
    }


    /**
     * 新增数据
     * @param record
     * @return
     */
    public boolean addProjectSpotCheckScore(ProjectSpotCheckScore record) {
        return projectSpotCheckScoreMapper.insertSelective(record) == 1;
    }

    /**
     * 更新数据
     * @param record
     * @return
     */
    public boolean modifyProjectSpotCheckScore(ProjectSpotCheckScore record) {
        return projectSpotCheckScoreMapper.updateByPrimaryKeySelective(record) == 1;
    }

    /**
     * 根据条件更新
     * @param record
     * @param where
     * @return
     */
    public int modifyProjectSpotCheckScore(ProjectSpotCheckScore record, ProjectSpotCheckScore where) {
        ProjectSpotCheckScoreExample example = new ProjectSpotCheckScoreExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectSpotCheckScoreMapper.updateByExampleSelective(record, example);
    }
}
