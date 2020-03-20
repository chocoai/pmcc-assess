package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.common.enums.ProjectChangeTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxRebateRatio;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxRebateRatioExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectXlxRebateRatioMapper;
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
public class ProjectXlxRebateRatioDao {
    @Autowired
    private ProjectXlxRebateRatioMapper projectXlxRebateRatioMapper;

    /**
     * @param where
     * @return
     */
    public List<ProjectXlxRebateRatio> getProjectXlxRebateRatio(ProjectXlxRebateRatio where) {
        ProjectXlxRebateRatioExample example = new ProjectXlxRebateRatioExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectXlxRebateRatioMapper.selectByExample(example);
    }


    /**
     * 新增数据
     * @param record
     * @return
     */
    public boolean addProjectXlxRebateRatio(ProjectXlxRebateRatio record) {
        return projectXlxRebateRatioMapper.insertSelective(record) == 1;
    }

    /**
     * 更新数据
     * @param record
     * @return
     */
    public boolean modifyProjectXlxRebateRatio(ProjectXlxRebateRatio record) {
        return projectXlxRebateRatioMapper.updateByPrimaryKeySelective(record) == 1;
    }

    /**
     * 根据条件更新
     * @param record
     * @param where
     * @return
     */
    public int modifyProjectXlxRebateRatio(ProjectXlxRebateRatio record, ProjectXlxRebateRatio where) {
        ProjectXlxRebateRatioExample example = new ProjectXlxRebateRatioExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectXlxRebateRatioMapper.updateByExampleSelective(record, example);
    }

    public boolean deleteProjectXlxRebateRatio(Integer id) {
        int i = projectXlxRebateRatioMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public ProjectXlxRebateRatio getDataById(Integer id) {
        return projectXlxRebateRatioMapper.selectByPrimaryKey(id);
    }
}
