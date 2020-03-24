package com.copower.pmcc.assess.dal.basis.dao.project.xlx;

import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonhole;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonholeExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectXlxPigeonholeMapper;
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
public class ProjectXlxPigeonholeDao {
    @Autowired
    private ProjectXlxPigeonholeMapper projectXlxPigeonholeMapper;

    /**
     * @param where
     * @return
     */
    public List<ProjectXlxPigeonhole> getProjectXlxPigeonhole(ProjectXlxPigeonhole where) {
        ProjectXlxPigeonholeExample example = new ProjectXlxPigeonholeExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectXlxPigeonholeMapper.selectByExample(example);
    }


    /**
     * 新增数据
     * @param record
     * @return
     */
    public boolean addProjectXlxPigeonhole(ProjectXlxPigeonhole record) {
        return projectXlxPigeonholeMapper.insertSelective(record) == 1;
    }

    /**
     * 更新数据
     * @param record
     * @return
     */
    public boolean modifyProjectXlxPigeonhole(ProjectXlxPigeonhole record) {
        return projectXlxPigeonholeMapper.updateByPrimaryKeySelective(record) == 1;
    }

    /**
     * 根据条件更新
     * @param record
     * @param where
     * @return
     */
    public int modifyProjectXlxPigeonhole(ProjectXlxPigeonhole record, ProjectXlxPigeonhole where) {
        ProjectXlxPigeonholeExample example = new ProjectXlxPigeonholeExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectXlxPigeonholeMapper.updateByExampleSelective(record, example);
    }

    public boolean deleteProjectXlxPigeonhole(Integer id) {
        int i = projectXlxPigeonholeMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public List<ProjectXlxPigeonhole> getInvalidList() {
        ProjectXlxPigeonholeExample example = new ProjectXlxPigeonholeExample();
        ProjectXlxPigeonholeExample.Criteria criteria = example.createCriteria();
        criteria.andProcessInsIdIsNull();
        return projectXlxPigeonholeMapper.selectByExample(example);
    }
}
