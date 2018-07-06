package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingRestaurant;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingRestaurantExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseMatchingRestaurantMapper {
    int countByExample(CaseMatchingRestaurantExample example);

    int deleteByExample(CaseMatchingRestaurantExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseMatchingRestaurant record);

    int insertSelective(CaseMatchingRestaurant record);

    List<CaseMatchingRestaurant> selectByExample(CaseMatchingRestaurantExample example);

    CaseMatchingRestaurant selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseMatchingRestaurant record, @Param("example") CaseMatchingRestaurantExample example);

    int updateByExample(@Param("record") CaseMatchingRestaurant record, @Param("example") CaseMatchingRestaurantExample example);

    int updateByPrimaryKeySelective(CaseMatchingRestaurant record);

    int updateByPrimaryKey(CaseMatchingRestaurant record);
}