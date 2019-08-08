package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.common.enums.ProjectChangeTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSubsequent;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSubsequentExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectSubsequentMapper;
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
public class ProjectSubsequentDao {
    @Autowired
    private ProjectSubsequentMapper projectSubsequentMapper;

    /**
     * @param where
     * @return
     */
    public List<ProjectSubsequent> getProjectSubsequent(ProjectSubsequent where) {
        ProjectSubsequentExample example = new ProjectSubsequentExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectSubsequentMapper.selectByExample(example);
    }


    /**
     * 新增数据
     * @param record
     * @return
     */
    public boolean addProjectSubsequent(ProjectSubsequent record) {
        return projectSubsequentMapper.insertSelective(record) == 1;
    }

    /**
     * 更新数据
     * @param record
     * @return
     */
    public boolean modifyProjectSubsequent(ProjectSubsequent record) {
        return projectSubsequentMapper.updateByPrimaryKeySelective(record) == 1;
    }

    /**
     * 根据条件更新
     * @param record
     * @param where
     * @return
     */
    public int modifyProjectSubsequent(ProjectSubsequent record, ProjectSubsequent where) {
        ProjectSubsequentExample example = new ProjectSubsequentExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectSubsequentMapper.updateByExampleSelective(record, example);
    }
}
