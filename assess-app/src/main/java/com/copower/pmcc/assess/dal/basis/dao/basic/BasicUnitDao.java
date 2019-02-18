package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnit;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicUnitMapper;
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
public class BasicUnitDao {

    @Autowired
    private BasicUnitMapper basicUnitMapper;

    public BasicUnit getBasicUnitById(Integer id)throws SQLException{
        return basicUnitMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicUnit(BasicUnit basicUnit)throws SQLException{
        basicUnitMapper.insertSelective(basicUnit);
        return basicUnit.getId();
    }

    public boolean updateBasicUnit(BasicUnit basicUnit)throws SQLException{
        return basicUnitMapper.updateByPrimaryKeySelective(basicUnit)==1;
    }

    public boolean deleteBasicUnit(Integer id)throws SQLException{
        return  basicUnitMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicUnit> basicUnitList(BasicUnit basicUnit)throws SQLException{
        BasicUnitExample example = new BasicUnitExample();
        BasicUnitExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (basicUnit.getApplyId() != null){
            criteria.andApplyIdEqualTo(basicUnit.getApplyId());
        }
        if (StringUtils.isNotBlank(basicUnit.getUnitNumber())){
            criteria.andUnitNumberLike(new StringBuilder("%").append(basicUnit.getUnitNumber()).append("%").toString());
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(basicUnit.getElevatorHouseholdRatio())){
            criteria.andElevatorHouseholdRatioEqualTo(basicUnit.getElevatorHouseholdRatio()) ;
        }
        if (basicUnit.getBuildingId() != null){
            criteria.andBuildingIdEqualTo(basicUnit.getBuildingId());
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(basicUnit.getCreator())){
            criteria.andCreatorEqualTo(basicUnit.getCreator()) ;
        }
        example.setOrderByClause("id desc");
        return basicUnitMapper.selectByExample(example);
    }

    public List<BasicUnit> autoComplete(BasicUnit basicUnit)throws SQLException{
       return this.basicUnitList(basicUnit);
    }
}
