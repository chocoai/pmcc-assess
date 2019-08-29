package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectTaskReturnRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectTaskReturnRecordExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectTaskReturnRecordMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 09:55
 * @Description:
 */
@Repository
public class ProjectTaskReturnRecordDao {
    @Autowired
    private ProjectTaskReturnRecordMapper projectTaskReturnRecordMapper;

    public Integer addProjectTaskReturnRecord(ProjectTaskReturnRecord projectTaskReturnRecord) {
        try {
            projectTaskReturnRecordMapper.insertSelective(projectTaskReturnRecord);
        } catch (Exception e1) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
        return projectTaskReturnRecord.getId();
    }

    public ProjectTaskReturnRecord getProjectTaskReturnRecordById(Integer id) {
        if (id == null) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
        return projectTaskReturnRecordMapper.selectByPrimaryKey(id);
    }

    public boolean updateProjectTaskReturnRecord(ProjectTaskReturnRecord projectTaskReturnRecord) {
        try {
            return projectTaskReturnRecordMapper.updateByPrimaryKeySelective(projectTaskReturnRecord) == 1;
        } catch (Exception e1) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
        return false;
    }

    public void removeProjectTaskReturnRecord(ProjectTaskReturnRecord projectTaskReturnRecord) {
        ProjectTaskReturnRecordExample example = new ProjectTaskReturnRecordExample();
        MybatisUtils.convertObj2Example(projectTaskReturnRecord, example);
        try {
            projectTaskReturnRecordMapper.deleteByExample(example);
        } catch (Exception e1) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
    }

    public List<ProjectTaskReturnRecord> getProjectTaskReturnRecordList(ProjectTaskReturnRecord projectTaskReturnRecord) {
        ProjectTaskReturnRecordExample example = new ProjectTaskReturnRecordExample();
        if (projectTaskReturnRecord == null) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
        MybatisUtils.convertObj2Example(projectTaskReturnRecord, example);
        return projectTaskReturnRecordMapper.selectByExample(example);
    }

}
