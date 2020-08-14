package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomCaseMapper;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuilding;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingExample;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstate;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicBuildingMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:21
 * @Description:案例基础数据
 */
@Repository
public class BasicBuildingDao {

    @Autowired
    private BasicBuildingMapper basicBuildingMapper;
    @Autowired
    private CustomCaseMapper customCaseMapper;

    public BasicBuilding getBasicBuildingById(Integer id) {
        return basicBuildingMapper.selectByPrimaryKey(id);
    }

    public List<BasicBuilding> getBasicBuildingIds(List<Integer> ids) {
        BasicBuildingExample example = new BasicBuildingExample();
        BasicBuildingExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        criteria.andIdIn(ids);
        return basicBuildingMapper.selectByExample(example);
    }

    public Integer addBasicBuilding(BasicBuilding basicBuilding) {
        basicBuildingMapper.insertSelective(basicBuilding);
        return basicBuilding.getId();
    }

    public boolean updateBasicBuilding(BasicBuilding basicBuilding, boolean updateNull) {
        return updateNull ? basicBuildingMapper.updateByPrimaryKey(basicBuilding) == 1 : basicBuildingMapper.updateByPrimaryKeySelective(basicBuilding) == 1;
    }

    public boolean deleteBasicBuilding(Integer id) {
        BasicBuilding basicBuilding = getBasicBuildingById(id);
        if (basicBuilding == null) return false;
        basicBuilding.setBisDelete(true);
        return basicBuildingMapper.updateByPrimaryKeySelective(basicBuilding) == 1;
    }

    public List<BasicBuilding> getBasicBuildingList(BasicBuilding basicBuilding) {
        BasicBuildingExample example = new BasicBuildingExample();
        BasicBuildingExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicBuilding, criteria);
        return basicBuildingMapper.selectByExample(example);
    }
}
