package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.ProjectClose;
import com.copower.pmcc.assess.dal.basis.entity.ProjectCloseExample;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectCloseMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/25
 * @time: 14:46
 */
@Repository
public class ProjectCloseDao {
    @Autowired
    private ProjectCloseMapper projectCloseMapper;

    public Boolean addProjectClose(ProjectClose projectClose)
    {
        int i = projectCloseMapper.insertSelective(projectClose);
        return i==1;
    }

    public Boolean editProjectClose(ProjectClose projectClose)
    {
        int i = projectCloseMapper.updateByPrimaryKeySelective(projectClose);
        return i>=1;
    }

    public ProjectClose getProjectClose(String processInsId)
    {
        ProjectCloseExample example=new ProjectCloseExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        List<ProjectClose> projectCloses = projectCloseMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(projectCloses))
        {
            return projectCloses.get(0);
        }
        return null;
    }

    public List<ProjectClose> getProjectCloseList(ProjectClose projectClose)
    {
        ProjectCloseExample example=new ProjectCloseExample();
        MybatisUtils.convertObj2Example(projectClose,example);
        return projectCloseMapper.selectByExample(example);
    }

}
