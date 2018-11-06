package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingFinance;
import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingFinanceExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicMatchingFinanceMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:03
 * @Description:
 */
@Repository
public class BasicMatchingFinanceDao {

    @Autowired
    private BasicMatchingFinanceMapper basicMatchingFinanceMapper;

    public BasicMatchingFinance getBasicMatchingFinanceById(Integer id)throws SQLException {
        return basicMatchingFinanceMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicMatchingFinance(BasicMatchingFinance basicMatchingFinance)throws SQLException{
        basicMatchingFinanceMapper.insertSelective(basicMatchingFinance);
        return basicMatchingFinance.getId();
    }

    public boolean updateBasicMatchingFinance(BasicMatchingFinance basicMatchingFinance)throws SQLException{
        return basicMatchingFinanceMapper.updateByPrimaryKeySelective(basicMatchingFinance)==1;
    }

    public void removeBasicMatchingFinance(BasicMatchingFinance basicMatchingFinance)throws SQLException{
        BasicMatchingFinanceExample example = new BasicMatchingFinanceExample();
        MybatisUtils.convertObj2Example(basicMatchingFinance, example);
        basicMatchingFinanceMapper.deleteByExample(example);
    }

    public boolean deleteBasicMatchingFinance(Integer id)throws SQLException{
        return  basicMatchingFinanceMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicMatchingFinance> basicMatchingFinanceList(BasicMatchingFinance basicMatchingFinance)throws SQLException{
        BasicMatchingFinanceExample example = new BasicMatchingFinanceExample();
        MybatisUtils.convertObj2Example(basicMatchingFinance, example);
        return basicMatchingFinanceMapper.selectByExample(example);
    }
    
}
