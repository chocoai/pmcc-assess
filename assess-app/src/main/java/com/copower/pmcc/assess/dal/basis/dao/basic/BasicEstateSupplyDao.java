package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateSupply;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateSupplyExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicEstateSupplyMapper;
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

    public BasicEstateSupply getBasicEstateSupplyById(Integer id)  {
        return basicEstateSupplyMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicEstateSupply(BasicEstateSupply basicEstateSupply)  {
        basicEstateSupplyMapper.insertSelective(basicEstateSupply);
        return basicEstateSupply.getId();
    }

    public boolean updateBasicEstateSupply(BasicEstateSupply basicEstateSupply, boolean updateNull)  {
        return updateNull ? basicEstateSupplyMapper.updateByPrimaryKey(basicEstateSupply) == 1 : basicEstateSupplyMapper.updateByPrimaryKeySelective(basicEstateSupply) == 1;
    }

    public void removeBasicEstateSupply(BasicEstateSupply basicEstateSupply)  {
        BasicEstateSupplyExample example = new BasicEstateSupplyExample();
        MybatisUtils.convertObj2Example(basicEstateSupply, example);
        basicEstateSupplyMapper.deleteByExample(example);
    }

    public boolean deleteBasicEstateSupply(Integer id)  {
        return basicEstateSupplyMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicEstateSupply> basicEstateSupplyList(BasicEstateSupply basicEstateSupply) {
        BasicEstateSupplyExample example = new BasicEstateSupplyExample();
        MybatisUtils.convertObj2Example(basicEstateSupply, example);
        return basicEstateSupplyMapper.selectByExample(example);
    }

}
