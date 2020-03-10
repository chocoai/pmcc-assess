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

    public BasicHouseTradingSell getBasicHouseTradingSellById(Integer id)  {
        return basicHouseTradingSellMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicHouseTradingSell(BasicHouseTradingSell basicHouseTradingSell)  {
        basicHouseTradingSellMapper.insertSelective(basicHouseTradingSell);
        return basicHouseTradingSell.getId();
    }

    public boolean updateBasicHouseTradingSell(BasicHouseTradingSell basicHouseTradingSell, boolean updateNull)  {
        return updateNull?basicHouseTradingSellMapper.updateByPrimaryKey(basicHouseTradingSell) == 1:basicHouseTradingSellMapper.updateByPrimaryKeySelective(basicHouseTradingSell) == 1;
    }

    public boolean deleteBasicHouseTradingSell(Integer id)  {
        BasicHouseTradingSell basicHouseTradingSell = getBasicHouseTradingSellById(id);
        if(basicHouseTradingSell==null) return false;
        basicHouseTradingSell.setBisDelete(true);
        return basicHouseTradingSellMapper.updateByPrimaryKeySelective(basicHouseTradingSell) == 1;
    }


    public List<BasicHouseTradingSell> basicHouseTradingSellList(BasicHouseTradingSell basicHouseTradingSell)  {
        BasicHouseTradingSellExample example = new BasicHouseTradingSellExample();
        BasicHouseTradingSellExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicHouseTradingSell, criteria);
        return basicHouseTradingSellMapper.selectByExample(example);
    }
}
