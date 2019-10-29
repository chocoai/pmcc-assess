package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseDamagedDegree;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseDamagedDegreeExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicHouseDamagedDegreeMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/2 09:50
 * @Description:
 */
@Repository
public class BasicHouseDamagedDegreeDao {
    @Autowired
    private BasicHouseDamagedDegreeMapper basicHouseDamagedDegreeMapper;

    public BasicHouseDamagedDegree getBasicHouseDamagedDegreeById(Integer id) {
        return basicHouseDamagedDegreeMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseDamagedDegree(BasicHouseDamagedDegree basicHouseDamagedDegree) {
        basicHouseDamagedDegreeMapper.insertSelective(basicHouseDamagedDegree);
        return basicHouseDamagedDegree.getId();
    }

    public boolean updateBasicHouseDamagedDegree(BasicHouseDamagedDegree basicHouseDamagedDegree, boolean updateNull) {
        return updateNull ? basicHouseDamagedDegreeMapper.updateByPrimaryKey(basicHouseDamagedDegree) == 1 : basicHouseDamagedDegreeMapper.updateByPrimaryKeySelective(basicHouseDamagedDegree) == 1;
    }

    public boolean deleteBasicHouseDamagedDegree(Integer id) {
        return basicHouseDamagedDegreeMapper.deleteByPrimaryKey(id) == 1;
    }

    public Boolean deleteBasicHouseDamagedDegree(BasicHouseDamagedDegree basicHouseDamagedDegree) {
        BasicHouseDamagedDegreeExample example = new BasicHouseDamagedDegreeExample();
        MybatisUtils.convertObj2Example(basicHouseDamagedDegree, example);
        return basicHouseDamagedDegreeMapper.deleteByExample(example) > 0;
    }


    public List<BasicHouseDamagedDegree> getDamagedDegreeList(BasicHouseDamagedDegree basicHouseDamagedDegree) {
        BasicHouseDamagedDegreeExample example = new BasicHouseDamagedDegreeExample();
        MybatisUtils.convertObj2Example(basicHouseDamagedDegree, example);
        return basicHouseDamagedDegreeMapper.selectByExample(example);
    }


    /**
     * 获取数据条数
     *
     * @param houseId
     * @return
     */
    public int countByHouseId(Integer houseId, Integer type) {
        BasicHouseDamagedDegreeExample example = new BasicHouseDamagedDegreeExample();
        BasicHouseDamagedDegreeExample.Criteria criteria = example.createCriteria();
        criteria.andHouseIdEqualTo(houseId);
        criteria.andTypeEqualTo(type);
        criteria.andEntityConditionContentNotEqualTo("");
        criteria.andEntityConditionNotEqualTo("");
        return basicHouseDamagedDegreeMapper.countByExample(example);
    }

    public List<BasicHouseDamagedDegree> getValueDamagedDegreeList(Integer houseId, Integer type) {
        BasicHouseDamagedDegreeExample example = new BasicHouseDamagedDegreeExample();
        BasicHouseDamagedDegreeExample.Criteria criteria = example.createCriteria();
        criteria.andHouseIdEqualTo(houseId);
        criteria.andTypeEqualTo(type);
        return basicHouseDamagedDegreeMapper.selectByExample(example);
    }
}
