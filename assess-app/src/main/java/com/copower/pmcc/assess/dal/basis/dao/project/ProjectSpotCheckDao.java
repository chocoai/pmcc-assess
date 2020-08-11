package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.basis.mapper.*;
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
    private ProjectSpotCheckItemScoreMapper projectSpotCheckItemScoreMapper;

    public ProjectSpotCheck getProjectSpotCheckById(Integer id) {
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
     *
     * @param record
     * @return
     */
    public boolean addProjectSpotCheck(ProjectSpotCheck record) {
        return projectSpotCheckMapper.insertSelective(record) == 1;
    }

    /**
     * 更新数据
     *
     * @param record
     * @return
     */
    public boolean modifyProjectSpotCheck(ProjectSpotCheck record) {
        return projectSpotCheckMapper.updateByPrimaryKeySelective(record) == 1;
    }

    /**
     * 根据条件更新
     *
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

    public ProjectSpotCheckItem getProjectSpotCheckItemById(Integer id) {
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
     *
     * @param record
     * @return
     */
    public boolean addProjectSpotCheckItem(ProjectSpotCheckItem record) {
        return projectSpotCheckItemMapper.insertSelective(record) == 1;
    }

    /**
     * 更新数据
     *
     * @param record
     * @return
     */
    public boolean modifyProjectSpotCheckItem(ProjectSpotCheckItem record) {
        return projectSpotCheckItemMapper.updateByPrimaryKeySelective(record) == 1;
    }

    /**
     * 根据条件更新
     *
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

    public ProjectSpotCheckItemScore getProjectSpotCheckItemScoreById(Integer id) {
        return projectSpotCheckItemScoreMapper.selectByPrimaryKey(id);
    }

    /**
     * @param where
     * @return
     */
    public List<ProjectSpotCheckItemScore> getProjectSpotCheckItemScoreList(ProjectSpotCheckItemScore where) {
        ProjectSpotCheckItemScoreExample example = new ProjectSpotCheckItemScoreExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectSpotCheckItemScoreMapper.selectByExampleWithBLOBs(example);
    }


    /**
     * 新增数据
     *
     * @param record
     * @return
     */
    public boolean addProjectSpotCheckItemScore(ProjectSpotCheckItemScore record) {
        return projectSpotCheckItemScoreMapper.insertSelective(record) == 1;
    }

    /**
     * 更新数据
     *
     * @param record
     * @return
     */
    public boolean modifyProjectSpotCheckItemScore(ProjectSpotCheckItemScore record) {
        return projectSpotCheckItemScoreMapper.updateByPrimaryKeySelective(record) == 1;
    }

    public List<ProjectSpotCheckItemScore> getSpotCheckItemScoreListByItemIds(List<Integer> itemIds){
        ProjectSpotCheckItemScoreExample example = new ProjectSpotCheckItemScoreExample();
        example.createCriteria().andItemIdIn(itemIds);
        return projectSpotCheckItemScoreMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 根据条件更新
     *
     * @param record
     * @param where
     * @return
     */
    public int modifyProjectSpotCheckItemScore(ProjectSpotCheckItemScore record, ProjectSpotCheckItemScore where) {
        ProjectSpotCheckItemScoreExample example = new ProjectSpotCheckItemScoreExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectSpotCheckItemScoreMapper.updateByExampleSelective(record, example);
    }
}
