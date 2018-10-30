package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingOutfit;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingOutfitExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicBuildingOutfitMapper;
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

    public BasicBuildingOutfit getBasicBuildingOutfitById(Integer id)throws SQLException {
        return basicBuildingOutfitMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicBuildingOutfit(BasicBuildingOutfit basicBuildingOutfit)throws SQLException{
        basicBuildingOutfitMapper.insertSelective(basicBuildingOutfit);
        return basicBuildingOutfit.getId();
    }

    public boolean updateBasicBuildingOutfit(BasicBuildingOutfit basicBuildingOutfit)throws SQLException{
        return basicBuildingOutfitMapper.updateByPrimaryKeySelective(basicBuildingOutfit)==1;
    }

    public void removeBasicBuildingOutfit(BasicBuildingOutfit basicBuildingOutfit)throws SQLException{
        BasicBuildingOutfitExample example = new BasicBuildingOutfitExample();
        MybatisUtils.convertObj2Example(basicBuildingOutfit, example);
        basicBuildingOutfitMapper.deleteByExample(example);
    }

    public boolean deleteBasicBuildingOutfit(Integer id)throws SQLException{
        return  basicBuildingOutfitMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicBuildingOutfit> basicBuildingOutfitList(BasicBuildingOutfit basicBuildingOutfit)throws SQLException{
        BasicBuildingOutfitExample example = new BasicBuildingOutfitExample();
        MybatisUtils.convertObj2Example(basicBuildingOutfit, example);
        return basicBuildingOutfitMapper.selectByExample(example);
    }
}
