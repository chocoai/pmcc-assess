package com.copower.pmcc.assess.dal.basis.dao.csr;

import com.copower.pmcc.assess.dal.basis.entity.CsrBorrower;
import com.copower.pmcc.assess.dal.basis.entity.CsrProjectInfoGroup;
import com.copower.pmcc.assess.dal.basis.entity.CsrProjectInfoGroupExample;
import com.copower.pmcc.assess.dal.basis.mapper.CsrProjectInfoGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class CsrProjectInfoGroupDao {

    @Autowired
    private CsrProjectInfoGroupMapper projectInfoGroupMapper;

    @Autowired
    private CsrBorrowerDao csrBorrowerDao;

    public boolean add(CsrProjectInfoGroup csrProjectInfoGroup){
        return projectInfoGroupMapper.insertSelective(csrProjectInfoGroup)==1;
    }

    public boolean update(CsrProjectInfoGroup csrProjectInfoGroup){
        return projectInfoGroupMapper.updateByPrimaryKey(csrProjectInfoGroup)==1;
    }

    public boolean checkGroup(Integer csrProjectId){
        boolean flag = true;
        List<CsrProjectInfoGroup> groups = groupList(csrProjectId);
        for (CsrProjectInfoGroup csrProjectInfoGroup:groups){
            List<CsrBorrower> csrBorrowers = csrBorrowerDao.borrowerListsA(null,null,null,csrProjectInfoGroup.getId());
            if (csrBorrowers.size()==0){//每一个项目组都必须有客户 并且至少有一个人
                flag = false;
            }
        }
        return flag;

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
