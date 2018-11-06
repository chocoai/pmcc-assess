package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstateSupply;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstateSupplyExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicEstateSupplyMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:52
 * @Description:
 */
@Repository
public class BasicEstateSupplyDao {

    @Autowired
    private BasicEstateSupplyMapper basicEstateSupplyMapper;

    public BasicEstateSupply getBasicEstateSupplyById(Integer id)throws SQLException {
        return basicEstateSupplyMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicEstateSupply(BasicEstateSupply basicEstateSupply)throws SQLException{
        basicEstateSupplyMapper.insertSelective(basicEstateSupply);
        return basicEstateSupply.getId();
    }

    public boolean updateBasicEstateSupply(BasicEstateSupply basicEstateSupply)throws SQLException{
        return basicEstateSupplyMapper.updateByPrimaryKeySelective(basicEstateSupply)==1;
    }

    public void removeBasicEstateSupply(BasicEstateSupply basicEstateSupply)throws SQLException{
        BasicEstateSupplyExample example = new BasicEstateSupplyExample();
        MybatisUtils.convertObj2Example(basicEstateSupply, example);
        basicEstateSupplyMapper.deleteByExample(example);
    }

    public boolean deleteBasicEstateSupply(Integer id)throws SQLException{
        return  basicEstateSupplyMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicEstateSupply> basicEstateSupplyList(BasicEstateSupply basicEstateSupply)throws SQLException{
        BasicEstateSupplyExample example = new BasicEstateSupplyExample();
        MybatisUtils.convertObj2Example(basicEstateSupply, example);
        return basicEstateSupplyMapper.selectByExample(example);
    }
    
}
