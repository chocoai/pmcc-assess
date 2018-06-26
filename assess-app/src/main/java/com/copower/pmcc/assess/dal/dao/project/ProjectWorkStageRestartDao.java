package com.copower.pmcc.assess.dal.dao.project;

import com.copower.pmcc.assess.dal.entity.ProjectWorkStageRestart;
import com.copower.pmcc.assess.dal.entity.ProjectWorkStageRestartExample;
import com.copower.pmcc.assess.dal.mapper.ProjectWorkStageRestartMapper;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:重启项目阶段
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/10/31
 * @time: 13:55
 */
@Repository
public class ProjectWorkStageRestartDao {
    @Autowired
    private ProjectWorkStageRestartMapper projectWorkStageRestartMapper;

    public Boolean addProjectWorkStageRestart(ProjectWorkStageRestart projectWorkStageRestart) {
        int i = projectWorkStageRestartMapper.insertSelective(projectWorkStageRestart);
        return i == 1;
    }

    public Boolean editProjectWorkStageRestart(ProjectWorkStageRestart projectWorkStageRestart) {
        int i = projectWorkStageRestartMapper.updateByPrimaryKeySelective(projectWorkStageRestart);
        return i >= 0;
    }

    public ProjectWorkStageRestart getProjectWorkStageRestartItem(String processInsId) {
        ProjectWorkStageRestartExample example = new ProjectWorkStageRestartExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        List<ProjectWorkStageRestart> projectWorkStageRestarts = projectWorkStageRestartMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(projectWorkStageRestarts)) {
            return projectWorkStageRestarts.get(0);
        }
        return null;
    }
    public ProjectWorkStageRestart getProjectWorkStageRestartByProjectId(Integer projectId) {
        ProjectWorkStageRestartExample example = new ProjectWorkStageRestartExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andStatusEqualTo(ProcessStatusEnum.RUN.getValue());
        List<ProjectWorkStageRestart> projectWorkStageRestarts = projectWorkStageRestartMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(projectWorkStageRestarts)) {
            return projectWorkStageRestarts.get(0);
        }
        return null;
    }
}
