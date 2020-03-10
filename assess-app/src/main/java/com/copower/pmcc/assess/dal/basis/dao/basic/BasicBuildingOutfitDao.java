package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingOutfit;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingOutfitExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicBuildingOutfitMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/30 11:44
 * @Description:
 */
@Repository
public class BasicBuildingOutfitDao {

    @Autowired
    private BasicBuildingOutfitMapper basicBuildingOutfitMapper;

    public BasicBuildingOutfit getBasicBuildingOutfitById(Integer id) {
        return basicBuildingOutfitMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicBuildingOutfit(BasicBuildingOutfit basicBuildingOutfit) {
        basicBuildingOutfitMapper.insertSelective(basicBuildingOutfit);
        return basicBuildingOutfit.getId();
    }

    public boolean updateBasicBuildingOutfit(BasicBuildingOutfit basicBuildingOutfit, boolean updateNull) {
        return updateNull ? basicBuildingOutfitMapper.updateByPrimaryKey(basicBuildingOutfit) == 1 : basicBuildingOutfitMapper.updateByPrimaryKeySelective(basicBuildingOutfit) == 1;
    }

    public void removeBasicBuildingOutfit(BasicBuildingOutfit basicBuildingOutfit) {
        BasicBuildingOutfitExample example = new BasicBuildingOutfitExample();
        BasicBuildingOutfitExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicBuildingOutfit, criteria);
        basicBuildingOutfitMapper.deleteByExample(example);
    }

    public boolean deleteBasicBuildingOutfit(Integer id) {
        BasicBuildingOutfit basicBuildingOutfit = getBasicBuildingOutfitById(id);
        if (basicBuildingOutfit == null) return false;
        basicBuildingOutfit.setBisDelete(true);
        return basicBuildingOutfitMapper.updateByPrimaryKeySelective(basicBuildingOutfit) == 1;
    }

    public List<BasicBuildingOutfit> basicBuildingOutfitList(BasicBuildingOutfit basicBuildingOutfit) {
        BasicBuildingOutfitExample example = new BasicBuildingOutfitExample();
        BasicBuildingOutfitExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicBuildingOutfit, criteria);
        return basicBuildingOutfitMapper.selectByExample(example);
    }
}
