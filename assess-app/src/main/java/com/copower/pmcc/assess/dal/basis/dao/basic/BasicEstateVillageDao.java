package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateVillage;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateVillageExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicEstateVillageMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:46
 * @Description:
 */
@Repository
public class BasicEstateVillageDao {
    @Autowired
    private BasicEstateVillageMapper basicEstateVillageMapper;

    public BasicEstateVillage getBasicEstateVillageById(Integer id) {
        return basicEstateVillageMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicEstateVillage(BasicEstateVillage basicEstateVillage) {
        basicEstateVillageMapper.insertSelective(basicEstateVillage);
        return basicEstateVillage.getId();
    }

    public boolean updateBasicEstateVillage(BasicEstateVillage basicEstateVillage, boolean updateNull) {
        return updateNull ? basicEstateVillageMapper.updateByPrimaryKey(basicEstateVillage) == 1 : basicEstateVillageMapper.updateByPrimaryKeySelective(basicEstateVillage) == 1;
    }

    public boolean deleteBasicEstateVillage(Integer id) {
        BasicEstateVillage basicEstateVillage = getBasicEstateVillageById(id);
        if (basicEstateVillage == null) return false;
        basicEstateVillage.setBisDelete(true);
        return basicEstateVillageMapper.updateByPrimaryKeySelective(basicEstateVillage) == 1;
    }

    public List<BasicEstateVillage> basicEstateVillageList(BasicEstateVillage basicEstateVillage) {
        BasicEstateVillageExample example = new BasicEstateVillageExample();
        BasicEstateVillageExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicEstateVillage, criteria);
        return basicEstateVillageMapper.selectByExample(example);
    }
}
