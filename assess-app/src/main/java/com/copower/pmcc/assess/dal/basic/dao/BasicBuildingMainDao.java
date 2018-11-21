package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingMain;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingMainExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicBuildingMainMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:21
 * @Description:案例基础数据
 */
@Repository
public class BasicBuildingMainDao {

    @Autowired
    private BasicBuildingMainMapper basicBuildingMainMapper;

    public BasicBuildingMain getBasicBuildingMainById(Integer id)throws SQLException{
        return basicBuildingMainMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicBuildingMain(BasicBuildingMain basicBuildingMain)throws SQLException{
        basicBuildingMainMapper.insertSelective(basicBuildingMain);
        return basicBuildingMain.getId();
    }

    public boolean updateBasicBuildingMain(BasicBuildingMain basicBuildingMain)throws SQLException{
        return basicBuildingMainMapper.updateByPrimaryKeySelective(basicBuildingMain)==1;
    }

    public void removeBasicBuildingMain(BasicBuildingMain basicBuildingMain)throws SQLException{
        BasicBuildingMainExample example = new BasicBuildingMainExample();
        MybatisUtils.convertObj2Example(basicBuildingMain, example);
        basicBuildingMainMapper.deleteByExample(example);
    }

    public boolean deleteBasicBuildingMain(Integer id)throws SQLException{
        return  basicBuildingMainMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicBuildingMain> basicBuildingMainList(BasicBuildingMain basicBuildingMain)throws SQLException{
        BasicBuildingMainExample example = new BasicBuildingMainExample();
        MybatisUtils.convertObj2Example(basicBuildingMain, example);
        return basicBuildingMainMapper.selectByExample(example);
    }

    public List<BasicBuildingMain> autoComplete(BasicBuildingMain basicBuildingMain)throws SQLException{
        BasicBuildingMainExample example = new BasicBuildingMainExample();
        BasicBuildingMainExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (basicBuildingMain.getEstateId() != null){
            criteria.andEstateIdEqualTo(basicBuildingMain.getEstateId());
        }
        if (StringUtils.isNotBlank(basicBuildingMain.getBuildingNumber())){
            criteria.andBuildingNumberLike(new StringBuilder("%").append(basicBuildingMain.getBuildingNumber()).append("%").toString());
        }
        return basicBuildingMainMapper.selectByExample(example);
    }
}
