package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.entity.ProjectInfoExample;
import com.copower.pmcc.assess.dal.mapper.ProjectInfoMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 描述: 项目基础数据DAO
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/8/31
 * @time: 16:00
 */
@Repository
public class ProjectInfoDao {
    @Autowired
    private ProjectInfoMapper projectInfoMapper;

    public Boolean saveProjectInfo(ProjectInfo projectInfo)
    {
        int i = projectInfoMapper.insertSelective(projectInfo);
        if(i>0)
        {
            return true;
        }
        return false;
    }

<<<<<<< Updated upstream
=======
    @Transactional
    public int saveProjectInfo_returnID(ProjectInfo projectInfo){
        projectInfoMapper.insertSelective(projectInfo);
        return  projectInfo.getId();
    }

>>>>>>> Stashed changes
    public Boolean updateProjectInfo(ProjectInfo projectInfo)
    {
        int i = projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
        if(i>0)
        {
            return true;
        }
        return false;
    }

    public List<ProjectInfo> getProjectInfoByProjectIds(List<Integer> integers)
    {
        ProjectInfoExample example=new ProjectInfoExample();
        example.createCriteria().andIdIn(integers);
        return projectInfoMapper.selectByExample(example);
    }

    public ProjectInfo getProjectInfoById(Integer id)
    {
        return projectInfoMapper.selectByPrimaryKey(id);
    }

    public ProjectInfo getProjectInfoByProcessInsId(String processInsId)
    {
        ProjectInfoExample example=new ProjectInfoExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        List<ProjectInfo> projectInfos = projectInfoMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(projectInfos))
        {
            return projectInfos.get(0);
        }
        return null;

    }

    public List<ProjectInfo> getProjectInfoList(String status, String projectName)
    {
        ProjectInfoExample example=new ProjectInfoExample();
        ProjectInfoExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(projectName))
        {
            criteria.andProjectNameLike(String.format("%%%s%%",projectName));
        }
        if(StringUtils.isNotBlank(status))
        {
            criteria.andStatusEqualTo(status);
        }
        example.setOrderByClause("id desc");
        return projectInfoMapper.selectByExample(example);
    }

    public List<ProjectInfo> getProjectInfoListByCompleteDatePlan(Date dates,Date datee,String projectName)
    {
        ProjectInfoExample example=new ProjectInfoExample();
        ProjectInfoExample.Criteria criteria = example.createCriteria().
                andCompleteDatePlanGreaterThanOrEqualTo(dates).
                andCompleteDatePlanLessThanOrEqualTo(datee);
        if(StringUtils.isNotBlank(projectName))
        {
            criteria.andProjectNameLike(String.format("%%%s%%",projectName));
        }
        return projectInfoMapper.selectByExample(example);
    }



}
