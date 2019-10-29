package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitDecorate;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitDecorateExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicUnitDecorateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/5 16:20
 * @Description:
 */
@Repository
public class BasicUnitDecorateDao {
    @Autowired
    private BasicUnitDecorateMapper basicUnitDecorateMapper;

    public BasicUnitDecorate getBasicUnitDecorateById(Integer id) throws SQLException {
        return basicUnitDecorateMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicUnitDecorate(BasicUnitDecorate basicUnitDecorate) throws SQLException {
        basicUnitDecorateMapper.insertSelective(basicUnitDecorate);
        return basicUnitDecorate.getId();
    }

    public boolean updateBasicUnitDecorate(BasicUnitDecorate basicUnitDecorate, boolean updateNull) throws SQLException {
        return updateNull ? basicUnitDecorateMapper.updateByPrimaryKey(basicUnitDecorate) == 1 : basicUnitDecorateMapper.updateByPrimaryKeySelective(basicUnitDecorate) == 1;
    }

    public void removeBasicUnitDecorate(BasicUnitDecorate basicUnitDecorate) throws SQLException {
        BasicUnitDecorateExample example = new BasicUnitDecorateExample();
        MybatisUtils.convertObj2Example(basicUnitDecorate, example);
        basicUnitDecorateMapper.deleteByExample(example);
    }

    public boolean deleteBasicUnitDecorate(Integer id) throws SQLException {
        return basicUnitDecorateMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicUnitDecorate> basicUnitDecorateList(BasicUnitDecorate basicUnitDecorate) throws SQLException {
        BasicUnitDecorateExample example = new BasicUnitDecorateExample();
        MybatisUtils.convertObj2Example(basicUnitDecorate, example);
        return basicUnitDecorateMapper.selectByExample(example);
    }
}
