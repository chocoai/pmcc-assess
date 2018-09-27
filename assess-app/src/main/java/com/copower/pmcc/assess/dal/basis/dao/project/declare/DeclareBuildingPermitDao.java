package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingPermit;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingPermitExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareBuildingPermitMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/27 18:01
 * @Description:
 */
@Repository
public class DeclareBuildingPermitDao {
    @Autowired
    private DeclareBuildingPermitMapper declareBuildingPermitMapper;

    public Integer addDeclareBuildingPermit(DeclareBuildingPermit declareBuildingPermit){
        declareBuildingPermitMapper.insertSelective(declareBuildingPermit);
        return declareBuildingPermit.getId();
    }

    public DeclareBuildingPermit getDeclareBuildingPermitById(Integer id){
        return declareBuildingPermitMapper.selectByPrimaryKey(id);
    }

    public boolean updateDeclareBuildingPermit(DeclareBuildingPermit declareBuildingPermit){
        return declareBuildingPermitMapper.updateByPrimaryKeySelective(declareBuildingPermit)==1;
    }

    public void removeDeclareBuildingPermit(DeclareBuildingPermit declareBuildingPermit){
        DeclareBuildingPermitExample example = new DeclareBuildingPermitExample();
        MybatisUtils.convertObj2Example(declareBuildingPermit, example);
        declareBuildingPermitMapper.deleteByExample(example);
    }

    public List<DeclareBuildingPermit> getDeclareBuildingPermitList(DeclareBuildingPermit declareBuildingPermit){
        DeclareBuildingPermitExample example = new DeclareBuildingPermitExample();
        MybatisUtils.convertObj2Example(declareBuildingPermit, example);
        return declareBuildingPermitMapper.selectByExample(example);
    }
}
