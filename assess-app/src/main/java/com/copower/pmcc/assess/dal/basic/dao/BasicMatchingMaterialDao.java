package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingMaterial;
import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingMaterialExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicMatchingMaterialMapper;
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

    public BasicMatchingMaterial getBasicMatchingMaterialById(Integer id)throws SQLException {
        return basicMatchingMaterialMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicMatchingMaterial(BasicMatchingMaterial basicMatchingMaterial)throws SQLException{
        basicMatchingMaterialMapper.insertSelective(basicMatchingMaterial);
        return basicMatchingMaterial.getId();
    }

    public boolean updateBasicMatchingMaterial(BasicMatchingMaterial basicMatchingMaterial)throws SQLException{
        return basicMatchingMaterialMapper.updateByPrimaryKeySelective(basicMatchingMaterial)==1;
    }

    public void removeBasicMatchingMaterial(BasicMatchingMaterial basicMatchingMaterial)throws SQLException{
        BasicMatchingMaterialExample example = new BasicMatchingMaterialExample();
        MybatisUtils.convertObj2Example(basicMatchingMaterial, example);
        basicMatchingMaterialMapper.deleteByExample(example);
    }

    public boolean deleteBasicMatchingMaterial(Integer id)throws SQLException{
        return  basicMatchingMaterialMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicMatchingMaterial> basicMatchingMaterialList(BasicMatchingMaterial basicMatchingMaterial)throws SQLException{
        BasicMatchingMaterialExample example = new BasicMatchingMaterialExample();
        MybatisUtils.convertObj2Example(basicMatchingMaterial, example);
        return basicMatchingMaterialMapper.selectByExample(example);
    }
    
}
