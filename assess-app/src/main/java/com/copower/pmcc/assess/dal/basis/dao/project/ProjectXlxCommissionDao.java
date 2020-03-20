package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.common.enums.ProjectChangeTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxCommission;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxCommissionExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectXlxCommissionMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
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
public class ProjectXlxCommissionDao {
    @Autowired
    private ProjectXlxCommissionMapper projectXlxCommissionMapper;

    /**
     * @param where
     * @return
     */
    public List<ProjectXlxCommission> getProjectXlxCommission(ProjectXlxCommission where) {
        ProjectXlxCommissionExample example = new ProjectXlxCommissionExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectXlxCommissionMapper.selectByExample(example);
    }


    /**
     * 新增数据
     * @param record
     * @return
     */
    public boolean addProjectXlxCommission(ProjectXlxCommission record) {
        return projectXlxCommissionMapper.insertSelective(record) == 1;
    }

    /**
     * 更新数据
     * @param record
     * @return
     */
    public boolean modifyProjectXlxCommission(ProjectXlxCommission record) {
        return projectXlxCommissionMapper.updateByPrimaryKeySelective(record) == 1;
    }

    /**
     * 根据条件更新
     * @param record
     * @param where
     * @return
     */
    public int modifyProjectXlxCommission(ProjectXlxCommission record, ProjectXlxCommission where) {
        ProjectXlxCommissionExample example = new ProjectXlxCommissionExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectXlxCommissionMapper.updateByExampleSelective(record, example);
    }

    public boolean deleteProjectXlxCommission(Integer id) {
        int i = projectXlxCommissionMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public List<ProjectXlxCommission> getInvalidList() {
        ProjectXlxCommissionExample example = new ProjectXlxCommissionExample();
        ProjectXlxCommissionExample.Criteria criteria = example.createCriteria();
        criteria.andProcessInsIdIsNull();
        return projectXlxCommissionMapper.selectByExample(example);
    }
}
