package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomProjectInfoMapper;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfoExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectInfoMapper;
import com.copower.pmcc.assess.dto.input.project.QueryProjectInfo;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 描述: 项目基础数据DAO
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2017/8/31
 * @time: 16:00
 */
@Repository
public class ProjectInfoDao {
    @Autowired
    private ProjectInfoMapper projectInfoMapper;
    @Autowired
    private CustomProjectInfoMapper customProjectInfoMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取当年的项目的项目个数
     * @param startDate
     * @param endDate
     * @return
     */
    public Integer getSerialNumber(String startDate,String endDate){
        StringBuilder stringBuilder = new StringBuilder() ;
        stringBuilder.append("SELECT max(serial_number)  from ").append(FormatUtils.entityNameConvertToTableName(ProjectInfo.class)) ;
        stringBuilder.append(" where 1=1 ") ;
        stringBuilder.append(" and gmt_created >  ").append(" '").append(startDate).append("' ") ;
        stringBuilder.append(" and gmt_created <=  ").append(" '").append(endDate).append("' ") ;
        Integer integer = jdbcTemplate.queryForObject(stringBuilder.toString(), Integer.class);
        if (integer == null) {
            integer = 1;
        }else {
            integer += 1;
        }
        return integer;
    }

    public boolean deleteByProjectInfoId(Integer id) {
        return projectInfoMapper.deleteByPrimaryKey(id) == 1;
    }

    public Boolean addProjectInfo(ProjectInfo projectInfo) {
        return projectInfoMapper.insertSelective(projectInfo) > 0;
    }

    public int saveProjectInfo_returnID(ProjectInfo projectInfo) {
        projectInfoMapper.insertSelective(projectInfo);
        return projectInfo.getId();
    }

    public Boolean updateProjectInfo(ProjectInfo projectInfo) {
        return projectInfoMapper.updateByPrimaryKeySelective(projectInfo) > 0;
    }

    public List<ProjectInfo> getProjectInfoList(ProjectInfo projectInfo) {
        ProjectInfoExample example = new ProjectInfoExample();
        MybatisUtils.convertObj2Example(projectInfo, example);
        example.setOrderByClause("id desc");
        return projectInfoMapper.selectByExample(example);
    }

    public List<ProjectInfo> getProjectInfoByProjectIds(List<Integer> integers) {
        ProjectInfoExample example = new ProjectInfoExample();
        example.createCriteria().andIdIn(integers);
        example.setOrderByClause("id desc");
        return projectInfoMapper.selectByExample(example);
    }

    public ProjectInfo getProjectInfoById(Integer id) {
        return projectInfoMapper.selectByPrimaryKey(id);
    }

    public ProjectInfo getProjectInfoByProcessInsId(String processInsId) {
        ProjectInfoExample example = new ProjectInfoExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        List<ProjectInfo> projectInfos = projectInfoMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(projectInfos)) {
            return projectInfos.get(0);
        }
        return null;

    }

    /**
     * 根据部门id查询项目数据
     *
     * @param orgIds
     * @param projectName
     * @param projectStatus
     * @return
     */
    public List<ProjectInfo> getProjectListByOrgIds(List<Integer> orgIds, String projectName, String projectStatus) {
        ProjectInfoExample example = new ProjectInfoExample();
        ProjectInfoExample.Criteria criteria = example.createCriteria();
        if (CollectionUtils.isNotEmpty(orgIds)) {
            criteria.andDepartmentIdIn(orgIds);
        }
        if (StringUtils.isNotEmpty(projectName)) {
            criteria.andProjectNameLike(String.format("%%%s%%", projectName));
        }
        if (StringUtils.isNotEmpty(projectStatus)) {
            criteria.andProjectStatusEqualTo(projectStatus);
        }
        example.setOrderByClause("id desc");
        return projectInfoMapper.selectByExample(example);
    }

    public List<ProjectInfo> getMyProjectList(ProjectInfo projectInfo) {
        ProjectInfoExample example = new ProjectInfoExample();
        ProjectInfoExample.Criteria criteria = example.createCriteria();
        criteria.andCreatorEqualTo(projectInfo.getCreator());
        //项目名称模糊查询
        if (StringUtils.isNotEmpty(projectInfo.getProjectName())) {
            criteria.andProjectNameLike(String.format("%%%s%%", projectInfo.getProjectName()));
        }
        //项目状态查询
        if (StringUtils.isNotEmpty(projectInfo.getProjectStatus())) {
            criteria.andProjectStatusEqualTo(projectInfo.getProjectStatus());
        }
        example.setOrderByClause("id desc");
        return projectInfoMapper.selectByExample(example);
    }

    public List<ProjectInfo> getProjectListByUserAccount(QueryProjectInfo queryProjectInfo) {
        return customProjectInfoMapper.getProjectListByUserAccount(queryProjectInfo);
    }


}
