package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomCaseMapper;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuilding;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingExample;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnit;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicUnitMapper;
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
public class BasicUnitDao {

    @Autowired
    private BasicUnitMapper basicUnitMapper;
    @Autowired
    private CustomCaseMapper customCaseMapper;

    public List<BasicUnit> getBasicUnitListByIds(List<Integer> ids){
        BasicUnitExample example = new BasicUnitExample();
        BasicUnitExample.Criteria criteria = example.createCriteria();
        criteria.andBisDeleteEqualTo(false);
        criteria.andIdIn(ids);
        return basicUnitMapper.selectByExample(example);
    }

    public BasicUnit getBasicUnitById(Integer id) {
        return basicUnitMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicUnit(BasicUnit basicUnit) {
        basicUnitMapper.insertSelective(basicUnit);
        return basicUnit.getId();
    }

    public boolean updateBasicUnit(BasicUnit basicUnit, boolean updateNull) {
        basicUnit.setBisDelete(false);
        return updateNull ? basicUnitMapper.updateByPrimaryKey(basicUnit) == 1 : basicUnitMapper.updateByPrimaryKeySelective(basicUnit) == 1;
    }

    public boolean deleteBasicUnit(Integer id) {
        BasicUnit basicUnit = getBasicUnitById(id);
        if (basicUnit == null) return false;
        basicUnit.setBisDelete(true);
        return basicUnitMapper.updateByPrimaryKeySelective(basicUnit) == 1;
    }

    public List<BasicUnit> getBasicUnitList(BasicUnit basicUnit) {
        BasicUnitExample example = new BasicUnitExample();
        BasicUnitExample.Criteria criteria = example.createCriteria();
        criteria.andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicUnit, criteria);
        return basicUnitMapper.selectByExample(example);
    }

    /**
     * 获取最新版单元信息
     *
     * @param buildingId
     * @return
     */
    public List<CustomCaseEntity> getLatestVersionUnitList(String unitNumber, Integer buildingId) {
        return customCaseMapper.getCaseUnitList(unitNumber, buildingId);
    }

    public BasicUnit getLatestVersionUnitByFullName(String fullName,Integer estateId) {
        if (StringUtils.isBlank(fullName) || estateId == null) return null;
        BasicUnitExample example = new BasicUnitExample();
        BasicUnitExample.Criteria criteria = example.createCriteria();
        criteria.andBisDeleteEqualTo(false).andBisCaseEqualTo(true).andFullNameEqualTo(fullName).andEstateIdEqualTo(estateId);
        example.setOrderByClause("version desc");
        List<BasicUnit> basicUnitList = basicUnitMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(basicUnitList)) return null;
        return basicUnitList.get(0);
    }
}
