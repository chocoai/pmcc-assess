package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseIntelligent;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseIntelligentExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicHouseIntelligentMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:22
 * @Description:
 */
@Repository
public class BasicHouseIntelligentDao {

    @Autowired
    private BasicHouseIntelligentMapper basicHouseIntelligentMapper;

    public BasicHouseIntelligent getBasicHouseIntelligentById(Integer id) {
        return basicHouseIntelligentMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicHouseIntelligent(BasicHouseIntelligent basicHouseIntelligent) {
        basicHouseIntelligentMapper.insertSelective(basicHouseIntelligent);
        return basicHouseIntelligent.getId();
    }

    public boolean updateBasicHouseIntelligent(BasicHouseIntelligent basicHouseIntelligent, boolean updateNull) {
        return updateNull ? basicHouseIntelligentMapper.updateByPrimaryKey(basicHouseIntelligent) == 1 : basicHouseIntelligentMapper.updateByPrimaryKeySelective(basicHouseIntelligent) == 1;
    }

    public boolean deleteBasicHouseIntelligent(Integer id) {
        BasicHouseIntelligent basicHouseIntelligent = getBasicHouseIntelligentById(id);
        if (basicHouseIntelligent == null) return false;
        basicHouseIntelligent.setBisDelete(true);
        return basicHouseIntelligentMapper.updateByPrimaryKeySelective(basicHouseIntelligent) == 1;
    }

    public List<BasicHouseIntelligent> basicHouseIntelligentList(BasicHouseIntelligent basicHouseIntelligent) {
        BasicHouseIntelligentExample example = new BasicHouseIntelligentExample();
        BasicHouseIntelligentExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicHouseIntelligent, criteria);
        return basicHouseIntelligentMapper.selectByExample(example);
    }

    /**
     * 获取数据条数
     *
     * @param houseId
     * @return
     */
    public int countByHouseId(Integer houseId) {
        BasicHouseIntelligentExample example = new BasicHouseIntelligentExample();
        example.createCriteria().andBisDeleteEqualTo(false).andHouseIdEqualTo(houseId);
        return basicHouseIntelligentMapper.countByExample(example);
    }
}
