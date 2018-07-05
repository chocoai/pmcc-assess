package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStageExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectWorkStageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2017/09/05 09:27
 */
@Repository
public class ProjectWorkStageDao {
    @Autowired
    private ProjectWorkStageMapper projectWorkStageMapper;



    public ProjectWorkStage getProjectWorkStageById(Integer id) {
        return projectWorkStageMapper.selectByPrimaryKey(id);
    }


    public List<ProjectWorkStage> getWorkStageByTypeId(Integer typeId) {
        ProjectWorkStageExample example = new ProjectWorkStageExample();
        example.createCriteria().andBisEnableEqualTo(true).andProjectTypeIdEqualTo(typeId);
        example.setOrderByClause(" stage_sort");
        return projectWorkStageMapper.selectByExample(example);
    }

    public boolean createWorkStage(ProjectWorkStage workStage) {
        return projectWorkStageMapper.insertSelective(workStage) == 1;
    }

    public boolean updateWorkStageById(ProjectWorkStage workStage) {
        return projectWorkStageMapper.updateByPrimaryKeySelective(workStage) == 1;
    }

}
