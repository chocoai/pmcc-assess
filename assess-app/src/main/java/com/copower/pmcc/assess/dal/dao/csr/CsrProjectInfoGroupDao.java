package com.copower.pmcc.assess.dal.dao.Csr;

import com.copower.pmcc.assess.dal.entity.CsrProjectInfoGroup;
import com.copower.pmcc.assess.dal.entity.CsrProjectInfoGroupExample;
import com.copower.pmcc.assess.dal.mapper.CsrProjectInfoGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CsrProjectInfoGroupDao {

    @Autowired
    private CsrProjectInfoGroupMapper projectInfoGroupMapper;

    public boolean add(CsrProjectInfoGroup csrProjectInfoGroup){
        return projectInfoGroupMapper.insertSelective(csrProjectInfoGroup)==1;
    }

    public List<CsrProjectInfoGroup> groupList(Integer projectID){
        CsrProjectInfoGroupExample example = new CsrProjectInfoGroupExample();
        example.createCriteria().andIdIsNotNull().andCsrProjectIdEqualTo(projectID);
        return projectInfoGroupMapper.selectByExample(example);
    }
}
