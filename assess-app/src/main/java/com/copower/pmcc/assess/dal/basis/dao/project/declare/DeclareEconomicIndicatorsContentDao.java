package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareEconomicIndicatorsContent;
import com.copower.pmcc.assess.dal.basis.entity.DeclareEconomicIndicatorsContentExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareEconomicIndicatorsContentMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019/6/25.
 */
@Repository
public class DeclareEconomicIndicatorsContentDao {
    
    @Autowired
    private DeclareEconomicIndicatorsContentMapper mapper;

    public boolean updateDeclareEconomicIndicatorsContent(DeclareEconomicIndicatorsContent oo) {
        int i = mapper.updateByPrimaryKeySelective(oo);
        return i > 0;
    }

    public void removeDeclareEconomicIndicatorsContentIds(List<Integer> ids){
        DeclareEconomicIndicatorsContentExample example = new DeclareEconomicIndicatorsContentExample();
        example.createCriteria().andIdIn(ids) ;
        mapper.deleteByExample(example) ;
    }


    public boolean addDeclareEconomicIndicatorsContent(DeclareEconomicIndicatorsContent oo) {
        int i = mapper.insertSelective(oo);
        return i > 0;
    }

    public boolean deleteDeclareEconomicIndicatorsContent(Integer id) {
        int i = mapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public DeclareEconomicIndicatorsContent getByDeclareEconomicIndicatorsContentId(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }


    public List<DeclareEconomicIndicatorsContent> getDeclareEconomicIndicatorsContentList(DeclareEconomicIndicatorsContent oo) {
        DeclareEconomicIndicatorsContentExample example = new DeclareEconomicIndicatorsContentExample();
        MybatisUtils.convertObj2Example(oo, example);
        example.setOrderByClause("id desc");
        List<DeclareEconomicIndicatorsContent> declareEconomicIndicatorsHeads = mapper.selectByExample(example);
        return declareEconomicIndicatorsHeads;
    }

    public List<DeclareEconomicIndicatorsContent> getDeclareEconomicIndicatorsContentListByHeadId(Integer headId){
        DeclareEconomicIndicatorsContentExample example = new DeclareEconomicIndicatorsContentExample();
        example.createCriteria().andIdIsNotNull().andIndicatorsHeadIdEqualTo(headId);
        List<DeclareEconomicIndicatorsContent> declareEconomicIndicatorsHeads = mapper.selectByExample(example);
        return declareEconomicIndicatorsHeads;
    }
    
}
