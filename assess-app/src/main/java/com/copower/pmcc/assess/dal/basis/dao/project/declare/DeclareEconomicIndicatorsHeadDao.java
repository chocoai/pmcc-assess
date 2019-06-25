package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareEconomicIndicatorsHead;
import com.copower.pmcc.assess.dal.basis.entity.DeclareEconomicIndicatorsHeadExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareEconomicIndicatorsHeadMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019/6/25.
 */
@Repository
public class DeclareEconomicIndicatorsHeadDao {
    
    @Autowired
    private DeclareEconomicIndicatorsHeadMapper mapper;

    public boolean updateDeclareEconomicIndicatorsHead(DeclareEconomicIndicatorsHead oo) {
        int i = mapper.updateByPrimaryKeySelective(oo);
        return i > 0;
    }


    public boolean addDeclareEconomicIndicatorsHead(DeclareEconomicIndicatorsHead oo) {
        int i = mapper.insertSelective(oo);
        return i > 0;
    }

    public boolean deleteDeclareEconomicIndicatorsHead(Integer id) {
        int i = mapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public DeclareEconomicIndicatorsHead getByDeclareEconomicIndicatorsHeadId(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }


    public List<DeclareEconomicIndicatorsHead> getDeclareEconomicIndicatorsHeadList(DeclareEconomicIndicatorsHead oo) {
        DeclareEconomicIndicatorsHeadExample example = new DeclareEconomicIndicatorsHeadExample();
        MybatisUtils.convertObj2Example(oo, example);
        example.setOrderByClause("id desc");
        List<DeclareEconomicIndicatorsHead> declareEconomicIndicatorsHeads = mapper.selectByExample(example);
        return declareEconomicIndicatorsHeads;
    }
    
}
