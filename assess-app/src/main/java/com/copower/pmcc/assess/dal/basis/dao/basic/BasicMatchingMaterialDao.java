package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingMaterial;
import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingMaterialExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicMatchingMaterialMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:10
 * @Description:
 */
@Repository
public class BasicMatchingMaterialDao {

    @Autowired
    private BasicMatchingMaterialMapper basicMatchingMaterialMapper;

    public BasicMatchingMaterial getBasicMatchingMaterialById(Integer id)  {
        return basicMatchingMaterialMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicMatchingMaterial(BasicMatchingMaterial basicMatchingMaterial)  {
        basicMatchingMaterialMapper.insertSelective(basicMatchingMaterial);
        return basicMatchingMaterial.getId();
    }

    public boolean updateBasicMatchingMaterial(BasicMatchingMaterial basicMatchingMaterial, boolean updateNull)  {
        return updateNull ? basicMatchingMaterialMapper.updateByPrimaryKey(basicMatchingMaterial) == 1 : basicMatchingMaterialMapper.updateByPrimaryKeySelective(basicMatchingMaterial) == 1;
    }

    public boolean deleteBasicMatchingMaterial(Integer id)  {
        BasicMatchingMaterial basicMatchingMaterial = getBasicMatchingMaterialById(id);
        if(basicMatchingMaterial==null) return false;
        basicMatchingMaterial.setBisDelete(true);
        return basicMatchingMaterialMapper.updateByPrimaryKeySelective(basicMatchingMaterial) == 1;
    }

    public List<BasicMatchingMaterial> basicMatchingMaterialList(BasicMatchingMaterial basicMatchingMaterial)  {
        BasicMatchingMaterialExample example = new BasicMatchingMaterialExample();
        BasicMatchingMaterialExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicMatchingMaterial, criteria);
        return basicMatchingMaterialMapper.selectByExample(example);
    }

}
