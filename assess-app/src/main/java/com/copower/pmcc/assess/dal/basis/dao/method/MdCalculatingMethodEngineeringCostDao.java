package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdCalculatingMethodEngineeringCost;
import com.copower.pmcc.assess.dal.basis.entity.MdCalculatingMethodEngineeringCostExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdCalculatingMethodEngineeringCostMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019-8-28.
 */
@Repository
public class MdCalculatingMethodEngineeringCostDao {

    @Autowired
    private MdCalculatingMethodEngineeringCostMapper mapper;

    public boolean addMdCalculatingMethodEngineeringCost(MdCalculatingMethodEngineeringCost oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean updateMdCalculatingMethodEngineeringCost(MdCalculatingMethodEngineeringCost oo){
        return mapper.updateByPrimaryKeySelective(oo)==1;
    }

    public MdCalculatingMethodEngineeringCost getMdCalculatingMethodEngineeringCostById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public boolean deleteMdCalculatingMethodEngineeringCostById(Integer id){
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public void removeMdCalculatingMethodEngineeringCost(MdCalculatingMethodEngineeringCost oo){
        MdCalculatingMethodEngineeringCostExample example = new MdCalculatingMethodEngineeringCostExample();
        MybatisUtils.convertObj2Example(oo, example);
        mapper.deleteByExample(example) ;
    }

    public List<MdCalculatingMethodEngineeringCost> getMdCalculatingMethodEngineeringCostListByExample(MdCalculatingMethodEngineeringCost oo){
        MdCalculatingMethodEngineeringCostExample example = new MdCalculatingMethodEngineeringCostExample();
        MybatisUtils.convertObj2Example(oo, example);
        return mapper.selectByExample(example) ;
    }
    
}
