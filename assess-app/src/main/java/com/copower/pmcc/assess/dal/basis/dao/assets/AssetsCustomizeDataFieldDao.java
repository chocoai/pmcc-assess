package com.copower.pmcc.assess.dal.basis.dao.assets;

import com.copower.pmcc.assess.dal.basis.entity.AssetsCustomizeDataField;
import com.copower.pmcc.assess.dal.basis.entity.AssetsCustomizeDataFieldExample;
import com.copower.pmcc.assess.dal.basis.mapper.AssetsCustomizeDataFieldMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019-9-23.
 */
@Repository
public class AssetsCustomizeDataFieldDao {

    @Autowired
    private AssetsCustomizeDataFieldMapper mapper;

    public boolean addAssetsCustomizeDataField(AssetsCustomizeDataField oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean updateAssetsCustomizeDataField(AssetsCustomizeDataField oo){
        return mapper.updateByPrimaryKeySelective(oo)==1;
    }

    public AssetsCustomizeDataField getAssetsCustomizeDataFieldById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public void deleteAssetsCustomizeDataField(List<Integer> ids){
        AssetsCustomizeDataFieldExample example = new AssetsCustomizeDataFieldExample();
        example.createCriteria().andIdIn(ids);
        mapper.deleteByExample(example) ;
    }

    public boolean deleteAssetsCustomizeDataFieldById(Integer id){
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public void removeAssetsCustomizeDataField(AssetsCustomizeDataField oo){
        AssetsCustomizeDataFieldExample example = new AssetsCustomizeDataFieldExample();
        MybatisUtils.convertObj2Example(oo, example);
        mapper.deleteByExample(example) ;
    }

    public List<AssetsCustomizeDataField> getAssetsCustomizeDataFieldListByExample(AssetsCustomizeDataField oo){
        AssetsCustomizeDataFieldExample example = new AssetsCustomizeDataFieldExample();
        MybatisUtils.convertObj2Example(oo, example);
        example.setOrderByClause("sorting");
        return mapper.selectByExample(example) ;
    }

    public List<AssetsCustomizeDataField> getAssetsCustomizeDataFieldListByTypeAndPlanDetailsId(Integer planDetailsId,String type){
        AssetsCustomizeDataFieldExample example = new AssetsCustomizeDataFieldExample();
        example.createCriteria().andTypeEqualTo(type).andPlanDetailsIdEqualTo(planDetailsId);
        example.setOrderByClause("sorting");
        return mapper.selectByExample(example) ;
    }

    public List<AssetsCustomizeDataField> getAssetsCustomizeDataFieldListByType(String type){
        AssetsCustomizeDataFieldExample example = new AssetsCustomizeDataFieldExample();
        example.createCriteria().andTypeEqualTo(type);
        example.setOrderByClause("sorting");
        return mapper.selectByExample(example) ;
    }
}
