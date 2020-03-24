package com.copower.pmcc.assess.dal.basis.dao.project.xlx;

import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonholeConfig;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonholeConfigExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectXlxPigeonholeConfigMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评估技术思路
 * Created by 13426 on 2018/4/26.
 */
@Repository
public class ProjectXlxPigeonholeConfigDao {

    @Autowired
    private ProjectXlxPigeonholeConfigMapper projectXlxPigeonholeConfigMapper;

    public boolean addProjectXlxPigeonholeConfig(ProjectXlxPigeonholeConfig projectXlxPigeonholeConfig) {
        return projectXlxPigeonholeConfigMapper.insertSelective(projectXlxPigeonholeConfig) == 1;
    }


    public boolean updateProjectXlxPigeonholeConfig(ProjectXlxPigeonholeConfig projectXlxPigeonholeConfig) {
        return projectXlxPigeonholeConfigMapper.updateByPrimaryKeySelective(projectXlxPigeonholeConfig) == 1;
    }

    public List<ProjectXlxPigeonholeConfig> getProjectXlxPigeonholeConfigList(String name,Integer type) {
        ProjectXlxPigeonholeConfigExample example = new ProjectXlxPigeonholeConfigExample();
        ProjectXlxPigeonholeConfigExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(name)) {
            criteria.andFileNameLike(String.format("%%%s%%", name));
        }
        if (type !=null) {
            criteria.andProjectTypeEqualTo(type);
        }
        example.setOrderByClause("project_type,sorting");
        return projectXlxPigeonholeConfigMapper.selectByExample(example);
    }
    
    public boolean removeProjectXlxPigeonholeConfig(Integer id) {
        return projectXlxPigeonholeConfigMapper.deleteByPrimaryKey(id) == 1;
    }

    public ProjectXlxPigeonholeConfig getProjectXlxPigeonholeConfig(Integer id) {
        return projectXlxPigeonholeConfigMapper.selectByPrimaryKey(id);
    }

    /**
     * @param where
     * @return
     */
    public List<ProjectXlxPigeonholeConfig> getProjectXlxPigeonholeConfigList(ProjectXlxPigeonholeConfig where) {
        ProjectXlxPigeonholeConfigExample example = new ProjectXlxPigeonholeConfigExample();
        MybatisUtils.convertObj2Example(where, example);
        return projectXlxPigeonholeConfigMapper.selectByExample(example);
    }
}
