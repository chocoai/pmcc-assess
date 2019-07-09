package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdArchitecturalObj;
import com.copower.pmcc.assess.dal.basis.entity.MdArchitecturalObjExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdArchitecturalObjMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019/7/8.
 */
@Repository
public class MdArchitecturalObjDao {

    @Autowired
    private MdArchitecturalObjMapper mapper;

    public boolean addMdArchitecturalObj(MdArchitecturalObj oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean updateMdArchitecturalObj(MdArchitecturalObj oo){
        return mapper.updateByPrimaryKeySelective(oo)==1;
    }

    public MdArchitecturalObj getMdArchitecturalObjById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public boolean deleteMdArchitecturalObjById(Integer id){
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public void removeMdArchitecturalObj(String type,Integer pid,String databaseName,Integer planDetailsId){
        MdArchitecturalObjExample example = new MdArchitecturalObjExample();
        MdArchitecturalObjExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(type)){
            criteria.andTypeEqualTo(type) ;
        }
        if (StringUtils.isNotEmpty(databaseName)){
            criteria.andDatabaseNameEqualTo(databaseName) ;
        }
        if (pid != null){
            criteria.andPidEqualTo(pid) ;
        }
        if (planDetailsId != null){
            criteria.andPlanDetailsIdEqualTo(planDetailsId) ;
        }
        mapper.deleteByExample(example) ;
    }

    public void removeMdArchitecturalObj(MdArchitecturalObj oo){
        MdArchitecturalObjExample example = new MdArchitecturalObjExample();
        MybatisUtils.convertObj2Example(oo, example);
        mapper.deleteByExample(example) ;
    }

    public List<MdArchitecturalObj> getMdArchitecturalObjListByExample(MdArchitecturalObj oo){
        MdArchitecturalObjExample example = new MdArchitecturalObjExample();
        MybatisUtils.convertObj2Example(oo, example);
        return mapper.selectByExample(example) ;
    }
}
