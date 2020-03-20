package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.common.enums.ProjectChangeTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxCommissionRatio;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxCommissionRatioExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectXlxCommissionRatioMapper;
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
public class ProjectXlxCommissionRatioDao {
    @Autowired
    private ProjectXlxCommissionRatioMapper projectXlxCommissionRatioMapper;

    /**
     * @param where
     * @return
     */
    public List<ProjectXlxCommissionRatio> getProjectXlxCommissionRatio(ProjectXlxCommissionRatio where) {
        ProjectXlxCommissionRatioExample example = new ProjectXlxCommissionRatioExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectXlxCommissionRatioMapper.selectByExample(example);
    }


    /**
     * 新增数据
     * @param record
     * @return
     */
    public boolean addProjectXlxCommissionRatio(ProjectXlxCommissionRatio record) {
        return projectXlxCommissionRatioMapper.insertSelective(record) == 1;
    }

    /**
     * 更新数据
     * @param record
     * @return
     */
    public boolean modifyProjectXlxCommissionRatio(ProjectXlxCommissionRatio record) {
        return projectXlxCommissionRatioMapper.updateByPrimaryKeySelective(record) == 1;
    }

    /**
     * 根据条件更新
     * @param record
     * @param where
     * @return
     */
    public int modifyProjectXlxCommissionRatio(ProjectXlxCommissionRatio record, ProjectXlxCommissionRatio where) {
        ProjectXlxCommissionRatioExample example = new ProjectXlxCommissionRatioExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectXlxCommissionRatioMapper.updateByExampleSelective(record, example);
    }

    public boolean deleteProjectXlxCommissionRatio(Integer id) {
        int i = projectXlxCommissionRatioMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public ProjectXlxCommissionRatio getDataById(Integer id) {
        return projectXlxCommissionRatioMapper.selectByPrimaryKey(id);
    }

}
