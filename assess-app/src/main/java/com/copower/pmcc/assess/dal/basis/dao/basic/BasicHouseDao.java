package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomCaseMapper;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouse;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseExample;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnit;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicHouseMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:21
 * @Description:案例基础数据
 */
@Repository
public class BasicHouseDao {

    @Autowired
    private BasicHouseMapper basicHouseMapper;
    @Autowired
    private CustomCaseMapper customCaseMapper;

    public BasicHouse getBasicHouseById(Integer id) {
        return basicHouseMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicHouse(BasicHouse basicHouse) {
        basicHouseMapper.insertSelective(basicHouse);
        return basicHouse.getId();
    }

    public boolean updateBasicHouse(BasicHouse basicHouse, boolean updateNull) {
        basicHouse.setBisDelete(false);
        return updateNull ? basicHouseMapper.updateByPrimaryKey(basicHouse) == 1 : basicHouseMapper.updateByPrimaryKeySelective(basicHouse) == 1;
    }

    public boolean deleteBasicHouse(Integer id) {
        BasicHouse basicHouse = getBasicHouseById(id);
        if (basicHouse == null) return false;
        return basicHouseMapper.updateByPrimaryKeySelective(basicHouse) == 1;
    }

    public List<BasicHouse> getBasicHouseIds(List<Integer> ids) {
        BasicHouseExample example = new BasicHouseExample();
        BasicHouseExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        criteria.andIdIn(ids);
        return basicHouseMapper.selectByExample(example);
    }

    public List<BasicHouse> getBasicHouseList(BasicHouse basicHouse) {
        BasicHouseExample example = new BasicHouseExample();
        BasicHouseExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicHouse, criteria);
        return basicHouseMapper.selectByExample(example);
    }

    public Integer getCountByBasicHouse(BasicHouse basicHouse) {
        BasicHouseExample example = new BasicHouseExample();
        BasicHouseExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicHouse, criteria);
        return basicHouseMapper.countByExample(example);
    }

    /**
     * 获取最新版本房屋信息
     *
     * @param unitId
     * @return
     */
    public List<CustomCaseEntity> getLatestVersionHouseList(String houseNumber, Integer unitId) {
        return customCaseMapper.getCaseHouseList(houseNumber, unitId);
    }

    public BasicHouse getLatestVersionHouseByFullName(String fullName, Integer estateId) {
        if (StringUtils.isBlank(fullName) || estateId == null) return null;
        BasicHouseExample example = new BasicHouseExample();
        BasicHouseExample.Criteria criteria = example.createCriteria();
        criteria.andBisDeleteEqualTo(false).andBisCaseEqualTo(true).andEstateIdEqualTo(estateId).andFullNameEqualTo(fullName);
        example.setOrderByClause("version desc");
        List<BasicHouse> basicHouseList = basicHouseMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(basicHouseList)) return null;
        return basicHouseList.get(0);
    }
}
