package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyCheckList;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyCheckListExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareRealtyCheckListMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2020-3-12.
 */
@Repository
public class DeclareRealtyCheckListDao {

    @Autowired
    private DeclareRealtyCheckListMapper mapper;

    public boolean updateDeclareRealtyCheckList(DeclareRealtyCheckList oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteDeclareRealtyCheckListById(Integer id){
        return mapper.deleteByPrimaryKey(id)==1 ;
    }

    public void deleteDeclareRealtyCheckListByIds(List<Integer> ids){
        DeclareRealtyCheckListExample example = new DeclareRealtyCheckListExample();
        example.createCriteria().andIdIn(ids) ;
        mapper.deleteByExample(example);
    }

    public DeclareRealtyCheckList getDeclareRealtyCheckListById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public List<DeclareRealtyCheckList> getDeclareRealtyCheckLists(Integer marsterId){
        DeclareRealtyCheckList query = new DeclareRealtyCheckList();
        query.setMarsterId(marsterId);
        return getDeclareRealtyCheckListListByExample(query) ;
    }

    public boolean saveDeclareRealtyCheckList(DeclareRealtyCheckList oo){
        return mapper.insertSelective(oo) ==1;
    }

    public List<DeclareRealtyCheckList> getDeclareRealtyCheckListByIds(List<Integer> ids){
        DeclareRealtyCheckListExample example = new DeclareRealtyCheckListExample();
        example.createCriteria().andIdIn(ids) ;
        return mapper.selectByExample(example) ;
    }

    public List<DeclareRealtyCheckList> getDeclareRealtyCheckListListByExample(DeclareRealtyCheckList oo){
        DeclareRealtyCheckListExample example = new DeclareRealtyCheckListExample();
        MybatisUtils.convertObj2Example(oo, example);
        example.setOrderByClause("id");
        return mapper.selectByExample(example) ;
    }

    public Integer getCount(Integer autoInitNumber,Integer marsterId,Integer planDetailsId){
        DeclareRealtyCheckListExample example = new DeclareRealtyCheckListExample();
        DeclareRealtyCheckListExample.Criteria criteria = example.createCriteria();
        criteria.andAutoInitNumberEqualTo(autoInitNumber);
        criteria.andPlanDetailsIdEqualTo(planDetailsId) ;
        criteria.andMarsterIdEqualTo(marsterId) ;
        long count = mapper.countByExample(example);
        return (int)count ;
    }
    
}
