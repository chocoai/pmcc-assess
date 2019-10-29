package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingSell;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingSellExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicHouseTradingSellMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/2 09:57
 * @Description:
 */
@Repository
public class BasicHouseTradingSellDao {
    @Autowired
    private BasicHouseTradingSellMapper basicHouseTradingSellMapper;

    public BasicHouseTradingSell getBasicHouseTradingSellById(Integer id) throws SQLException {
        return basicHouseTradingSellMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseTradingSell(BasicHouseTradingSell basicHouseTradingSell) throws SQLException {
        basicHouseTradingSellMapper.insertSelective(basicHouseTradingSell);
        return basicHouseTradingSell.getId();
    }

    public boolean updateBasicHouseTradingSell(BasicHouseTradingSell basicHouseTradingSell, boolean updateNull) throws SQLException {
        return updateNull?basicHouseTradingSellMapper.updateByPrimaryKey(basicHouseTradingSell) == 1:basicHouseTradingSellMapper.updateByPrimaryKeySelective(basicHouseTradingSell) == 1;
    }

    public boolean deleteBasicHouseTradingSell(Integer id) throws SQLException {
        return basicHouseTradingSellMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean deleteBasicHouseTradingSell(BasicHouseTradingSell basicHouseTradingSell) throws SQLException {
        BasicHouseTradingSellExample example = new BasicHouseTradingSellExample();
        MybatisUtils.convertObj2Example(basicHouseTradingSell, example);
        return basicHouseTradingSellMapper.deleteByExample(example) > 0;
    }


    public List<BasicHouseTradingSell> basicHouseTradingSellList(BasicHouseTradingSell basicHouseTradingSell) throws SQLException {
        BasicHouseTradingSellExample example = new BasicHouseTradingSellExample();
        MybatisUtils.convertObj2Example(basicHouseTradingSell, example);
        return basicHouseTradingSellMapper.selectByExample(example);
    }
}
