package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.common.enums.ProjectChangeTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.ProjectChangeLog;
import com.copower.pmcc.assess.dal.basis.entity.ProjectChangeLogExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectChangeLogMapper;
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
public class ProjectChangeLogDao {
    @Autowired
    private ProjectChangeLogMapper projectChangeLogMapper;

    /**
     * 根据条件查询项目变更日志记录
     * @param where
     * @return
     */
    public List<ProjectChangeLog> getProjectChangeLog(ProjectChangeLog where) {
        ProjectChangeLogExample example = new ProjectChangeLogExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectChangeLogMapper.selectByExample(example);
    }

    /**
     * 通过项目id和变更类型获取单条数据
     * @param projectId
     * @param changeTypeEnum
     * @return
     */
    public ProjectChangeLog getProjectChangeLog(Integer projectId, ProjectChangeTypeEnum changeTypeEnum){
        ProjectChangeLog where = new ProjectChangeLog();
        where.setProjectId(projectId);
        where.setChangeType(changeTypeEnum.getValue());

        List<ProjectChangeLog> changeLogs = getProjectChangeLog(where);
        if (CollectionUtils.isNotEmpty(changeLogs)) {
            return changeLogs.get(0);
        }

        return null;
    }


    /**
     * 根据项目id，流程实例id，变更类型获取数据
     * @param projectId
     * @param processInsId
     * @param changeTypeEnum
     * @return
     */
    public ProjectChangeLog getProjectChangeLog(Integer projectId, String processInsId, ProjectChangeTypeEnum changeTypeEnum){
        ProjectChangeLog where = new ProjectChangeLog();
        where.setProjectId(projectId);
        where.setChangeType(changeTypeEnum.getValue());
        where.setProcessInsId(processInsId);

        List<ProjectChangeLog> changeLogs = getProjectChangeLog(where);
        if (CollectionUtils.isNotEmpty(changeLogs)) {
            return changeLogs.get(0);
        }

        return null;
    }


    /**
     * 新增数据
     * @param record
     * @return
     */
    public boolean addProjectChangeLog(ProjectChangeLog record) {
        return projectChangeLogMapper.insertSelective(record) == 1;
    }

    /**
     * 根据主键更新数据
     * @param record
     * @return
     */
    public boolean modifyProjectChangeLog(ProjectChangeLog record) {
        return projectChangeLogMapper.updateByPrimaryKeySelective(record) == 1;
    }

    /**
     * 根据条件更新记录数据
     * @param record
     * @param where
     * @return
     */
    public int modifyProjectChangeLog(ProjectChangeLog record, ProjectChangeLog where) {
        ProjectChangeLogExample example = new ProjectChangeLogExample();
        MybatisUtils.convertObj2Example(where, example);

        return projectChangeLogMapper.updateByExampleSelective(record, example);
    }
}
