package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.ProjectPlanHistory;
import com.copower.pmcc.assess.dal.entity.ProjectPlanHistoryExample;
import com.copower.pmcc.assess.dal.mapper.ProjectPlanHistoryMapper;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/28
 * @time: 9:20
 */
@Repository
public class ProjectPlanHistoryDao {
    @Autowired
    private ProjectPlanHistoryMapper projectPlanHistoryMapper;

    public Boolean addPlanHistory(ProjectPlanHistory projectPlanHistory)
    {
        int i = projectPlanHistoryMapper.insertSelective(projectPlanHistory);
        return i==1;
    }

    public Boolean editPlanHistory(ProjectPlanHistory projectPlanHistory)
    {
        int i = projectPlanHistoryMapper.updateByPrimaryKeySelective(projectPlanHistory);
        return i>=0;
    }

    public Boolean editPlanHistoryByProcessInsId(String processInsId,ProjectPlanHistory projectPlanHistory)
    {
        ProjectPlanHistoryExample example=new ProjectPlanHistoryExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        int i = projectPlanHistoryMapper.updateByExampleSelective(projectPlanHistory,example);
        return i>=0;
    }

    public List<ProjectPlanHistory> getProjectPlanHistoryByProcessInsId(String processInsId)
    {
        ProjectPlanHistoryExample example=new ProjectPlanHistoryExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        return projectPlanHistoryMapper.selectByExample(example);
    }

    public List<ProjectPlanHistory> getProjectPlanHistoryByProjectId(Integer projectId)
    {
        ProjectPlanHistoryExample example=new ProjectPlanHistoryExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andStatusEqualTo(ProcessStatusEnum.RUN.getValue());
        return projectPlanHistoryMapper.selectByExample(example);
    }

}
