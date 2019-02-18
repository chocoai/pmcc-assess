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

    public BasicHouseIntelligent getBasicHouseIntelligentById(Integer id) throws SQLException {
        return basicHouseIntelligentMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseIntelligent(BasicHouseIntelligent basicHouseIntelligent) throws SQLException {
        basicHouseIntelligentMapper.insertSelective(basicHouseIntelligent);
        return basicHouseIntelligent.getId();
    }

    public boolean updateBasicHouseIntelligent(BasicHouseIntelligent basicHouseIntelligent) throws SQLException {
        return basicHouseIntelligentMapper.updateByPrimaryKeySelective(basicHouseIntelligent) == 1;
    }

    public boolean deleteBasicHouseIntelligent(BasicHouseIntelligent basicHouseIntelligent) throws SQLException {
        BasicHouseIntelligentExample example = new BasicHouseIntelligentExample();
        MybatisUtils.convertObj2Example(basicHouseIntelligent, example);
        return basicHouseIntelligentMapper.deleteByExample(example) > 0;
    }

    public boolean deleteBasicHouseIntelligent(Integer id) throws SQLException {
        return basicHouseIntelligentMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicHouseIntelligent> basicHouseIntelligentList(BasicHouseIntelligent basicHouseIntelligent) {
        BasicHouseIntelligentExample example = new BasicHouseIntelligentExample();
        MybatisUtils.convertObj2Example(basicHouseIntelligent, example);
        return basicHouseIntelligentMapper.selectByExample(example);
    }

    /**
     * 获取数据条数
     * @param houseId
     * @return
     */
    public int countByHouseId(Integer houseId){
        BasicHouseIntelligentExample example = new BasicHouseIntelligentExample();
        example.createCriteria().andHouseIdEqualTo(houseId);
        return basicHouseIntelligentMapper.countByExample(example);
    }
}
