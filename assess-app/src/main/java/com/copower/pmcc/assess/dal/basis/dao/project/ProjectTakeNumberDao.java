package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.common.enums.ProjectChangeTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectTakeNumber;
import com.copower.pmcc.assess.dal.basis.entity.ProjectTakeNumberExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectTakeNumberMapper;
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
public class ProjectTakeNumberDao {
    @Autowired
    private ProjectTakeNumberMapper projectTakeNumberMapper;

    /**
     * @param where
     * @return
     */
    public List<ProjectTakeNumber> getProjectTakeNumber(ProjectTakeNumber where) {
        ProjectTakeNumberExample example = new ProjectTakeNumberExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectTakeNumberMapper.selectByExample(example);
    }


    /**
     * 新增数据
     * @param record
     * @return
     */
    public boolean addProjectTakeNumber(ProjectTakeNumber record) {
        return projectTakeNumberMapper.insertSelective(record) == 1;
    }

    /**
     * 更新数据
     * @param record
     * @return
     */
    public boolean modifyProjectTakeNumber(ProjectTakeNumber record) {
        return projectTakeNumberMapper.updateByPrimaryKeySelective(record) == 1;
    }

    /**
     * 根据条件更新
     * @param record
     * @param where
     * @return
     */
    public int modifyProjectTakeNumber(ProjectTakeNumber record, ProjectTakeNumber where) {
        ProjectTakeNumberExample example = new ProjectTakeNumberExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectTakeNumberMapper.updateByExampleSelective(record, example);
    }
}
