package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingConstructionPermit;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingConstructionPermitExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareBuildingConstructionPermitMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/27 18:00
 * @Description:
 */
@Repository
public class DeclareBuildingConstructionPermitDao {
    @Autowired
    private DeclareBuildingConstructionPermitMapper declareBuildingConstructionPermitMapper;

    public Integer addDeclareBuildingConstructionPermit(DeclareBuildingConstructionPermit declareBuildingConstructionPermit){
        declareBuildingConstructionPermitMapper.insertSelective(declareBuildingConstructionPermit);
        return declareBuildingConstructionPermit.getId();
    }

    public DeclareBuildingConstructionPermit getDeclareBuildingConstructionPermitById(Integer id){
        return declareBuildingConstructionPermitMapper.selectByPrimaryKey(id);
    }

    public boolean updateDeclareBuildingConstructionPermit(DeclareBuildingConstructionPermit declareBuildingConstructionPermit){
        return declareBuildingConstructionPermitMapper.updateByPrimaryKeySelective(declareBuildingConstructionPermit)==1;
    }

    public void removeDeclareBuildingConstructionPermit(DeclareBuildingConstructionPermit declareBuildingConstructionPermit){
        DeclareBuildingConstructionPermitExample example = new DeclareBuildingConstructionPermitExample();
        MybatisUtils.convertObj2Example(declareBuildingConstructionPermit, example);
        declareBuildingConstructionPermitMapper.deleteByExample(example);
    }

    public List<DeclareBuildingConstructionPermit> getDeclareBuildingConstructionPermitList(DeclareBuildingConstructionPermit declareBuildingConstructionPermit){
        DeclareBuildingConstructionPermitExample example = new DeclareBuildingConstructionPermitExample();
        MybatisUtils.convertObj2Example(declareBuildingConstructionPermit, example);
        return declareBuildingConstructionPermitMapper.selectByExample(example);
    }
}
