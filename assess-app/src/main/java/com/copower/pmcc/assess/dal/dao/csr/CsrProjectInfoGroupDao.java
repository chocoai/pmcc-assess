package com.copower.pmcc.assess.dal.dao.csr;

import com.copower.pmcc.assess.dal.entity.CsrProjectInfoGroup;
import com.copower.pmcc.assess.dal.entity.CsrProjectInfoGroupExample;
import com.copower.pmcc.assess.dal.mapper.CsrProjectInfoGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class CsrProjectInfoGroupDao {

    @Autowired
    private CsrProjectInfoGroupMapper projectInfoGroupMapper;

    public boolean add(CsrProjectInfoGroup csrProjectInfoGroup){
        return projectInfoGroupMapper.insertSelective(csrProjectInfoGroup)==1;
    }

    public boolean update(CsrProjectInfoGroup csrProjectInfoGroup){
        return projectInfoGroupMapper.updateByPrimaryKey(csrProjectInfoGroup)==1;
    }

    public CsrProjectInfoGroup getByID(Integer id){
        return projectInfoGroupMapper.selectByPrimaryKey(id);
    }

    public boolean removeByID(Integer id){
        return projectInfoGroupMapper.deleteByPrimaryKey(id)==1;
    }

    public List<CsrProjectInfoGroup> groupList(Integer csrProjectId, String projectName){
        CsrProjectInfoGroupExample example = new CsrProjectInfoGroupExample();
        if (StringUtils.isEmpty(projectName)){
            example.createCriteria().andIdIsNotNull().andCsrProjectIdEqualTo(csrProjectId);
            return projectInfoGroupMapper.selectByExample(example);
        }else {
            example.createCriteria().andIdIsNotNull().andCsrProjectIdEqualTo(csrProjectId).andProjectNameLike("%"+projectName+"%");
            return projectInfoGroupMapper.selectByExample(example);
        }
    }

    public List<CsrProjectInfoGroup> groupList(Integer csrProjectId){
        CsrProjectInfoGroupExample example = new CsrProjectInfoGroupExample();
        example.createCriteria().andIdIsNotNull().andCsrProjectIdEqualTo(csrProjectId);
        return projectInfoGroupMapper.selectByExample(example);
    }

}
