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

    public BasicMatchingMaterial getBasicMatchingMaterialById(Integer id) throws SQLException {
        return basicMatchingMaterialMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicMatchingMaterial(BasicMatchingMaterial basicMatchingMaterial) throws SQLException {
        basicMatchingMaterialMapper.insertSelective(basicMatchingMaterial);
        return basicMatchingMaterial.getId();
    }

    public boolean updateBasicMatchingMaterial(BasicMatchingMaterial basicMatchingMaterial, boolean updateNull) throws SQLException {
        return updateNull ? basicMatchingMaterialMapper.updateByPrimaryKey(basicMatchingMaterial) == 1 : basicMatchingMaterialMapper.updateByPrimaryKeySelective(basicMatchingMaterial) == 1;
    }

    public void removeBasicMatchingMaterial(BasicMatchingMaterial basicMatchingMaterial) throws SQLException {
        BasicMatchingMaterialExample example = new BasicMatchingMaterialExample();
        MybatisUtils.convertObj2Example(basicMatchingMaterial, example);
        basicMatchingMaterialMapper.deleteByExample(example);
    }

    public boolean deleteBasicMatchingMaterial(Integer id) throws SQLException {
        return basicMatchingMaterialMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicMatchingMaterial> basicMatchingMaterialList(BasicMatchingMaterial basicMatchingMaterial) throws SQLException {
        BasicMatchingMaterialExample example = new BasicMatchingMaterialExample();
        MybatisUtils.convertObj2Example(basicMatchingMaterial, example);
        return basicMatchingMaterialMapper.selectByExample(example);
    }

}
